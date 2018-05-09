package com.magicbricks.AsyncDbKafka.consumer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.magicbricks.AsyncDbKafka.service.CountUpdateService;

/**
 * @author Divya Gupta
 */
@Component
public class KafkaConsumerLoopClient  {

    private static final String BOOTSTARP_SERVERS = "115.112.207.195:9047";
    private static final String CONSUMER_GROUP="VIEW_COUNT_CONSUMER";
    private KafkaConsumer<String, String> consumer;
    private Map<String, Integer> tppmtMap = null;
    private Map<String, Integer> tppsmMap = null;
    private static final int batchSize = 50;
    private int batchCount = 0;
    @Autowired
    private CountUpdateService countUpdateService;

    public void setCountUpdateService(CountUpdateService countUpdateService) {
        this.countUpdateService = countUpdateService;
    }

    public CountUpdateService getCountUpdateService() {
        return countUpdateService;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerLoopClient.class);
    private static Properties getProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTARP_SERVERS);
        props.put("group.id", CONSUMER_GROUP);
        props.put("enable.auto.commit", "false");
        //props.put("max.partition.fetch.bytes", 204800);
        props.put("max.partition.fetch.bytes", 2000);
       // props.put("auto.commit.interval.ms", "2000");
        props.put("session.timeout.ms", "30000");
        
        props.put("auto.offset.reset", "earliest");
        props.put("heartbeat.interval.ms", "5000");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        return props;
    }

    public void consumeView() {
        try {
        	consumer = new KafkaConsumer<>(getProperties());
            consumer.subscribe(Arrays.asList("VIEW_COUNT"));
            String[] viewCountIdentifier;
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                for (ConsumerRecord<String, String> record : records) {
                	batchCount++;
                	String propDateUtmStr = record.value();
                    LOGGER.debug("[AG] MESSAGE Received: " + propDateUtmStr);
                    viewCountIdentifier = propDateUtmStr.split(":");
                    switch (viewCountIdentifier[0]) {
                        case "tppmtCounts": {
                            LOGGER.debug("[AG] Kafka received converted data: " + viewCountIdentifier[1]);

                            //Create a map for each property id and keep adding the count from last count. Then
                            // update db once for all
                            if (null == tppmtMap)
                                tppmtMap = new HashMap<>();
                            if (null == tppmtMap.get(viewCountIdentifier[1])) {
                                tppmtMap.put(viewCountIdentifier[1], 1);
                            } else {
                                int updatedValue = tppmtMap.get(viewCountIdentifier[1]) + 1;
                                tppmtMap.put(viewCountIdentifier[1], updatedValue);
                            }
                            break;
                        }
                        case "tppsmCounts": {
                            LOGGER.debug("[AG] Kafka received converted data: " + viewCountIdentifier[1]);

                            //Create a map for each property id and keep adding the count from last count. Then
                            // update db once for all
                            if (null == tppsmMap)
                                tppsmMap = new HashMap<>();
                            if (null == tppsmMap.get(viewCountIdentifier[1])) {
                                tppsmMap.put(viewCountIdentifier[1], 1);
                            } else {
                                int updatedValue = tppsmMap.get(viewCountIdentifier[1]) + 1;
                                tppsmMap.put(viewCountIdentifier[1], updatedValue);
                            }
                        }
                        break;
                        default:
                            LOGGER.error("[AG] Not supported topic: " + record.topic());
                    }
                }
                if (batchCount >= batchSize) {
                	System.out.println("Batch Count -> "+batchCount);
                   // boolean success = getCountUpdateService().updateCount(tppmtMap, tppsmMap);
                	 boolean success = countUpdateService.updateCount(tppmtMap, tppsmMap);
                    if (success) {
                        batchCount = 0;            //reset counter
                        tppmtMap = null;
                        tppsmMap = null;
                        consumer.commitSync();
                        
                    }
                }
            }
        } catch (WakeupException e) {
            LOGGER.error("[AG] Async WakeupException==" + e);
        } catch (Exception e) {
            LOGGER.error("[AG] Async Exception==" + e);
            e.printStackTrace();
        } finally {
            LOGGER.error("[AG] Async Consumer is CLOSED==");
            consumer.close();
        }
    }

    public void shutdown() {
        LOGGER.error("[AG] Async Consumer SHUTDOWN==");
        consumer.wakeup();
    }
}
