package com.magicbricks.AsyncDbKafka.service;

import com.magicbricks.AsyncDbKafka.dao.TppmtCountsDao;
import com.magicbricks.AsyncDbKafka.dao.TppsmCountsDao;
import com.magicbricks.AsyncDbKafka.domain.TppmtCounts;
import com.magicbricks.AsyncDbKafka.domain.TppmtCountsId;
import com.magicbricks.AsyncDbKafka.domain.TppsmCounts;
import com.magicbricks.AsyncDbKafka.domain.TppsmCountsId;
import com.magicbricks.AsyncDbKafka.utils.CommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class CountUpdateService {

    @Autowired
    private TppmtCountsDao pmtCountsDao;

    @Autowired
    private TppsmCountsDao psmCountsDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(CountUpdateService.class);

    public boolean updateCount(Map<String, Integer> mapTppmt, Map<String, Integer> mapTppsm) {
        if (mapTppmt != null)
            insertTppmtObj(mapTppmt);
        if (mapTppsm != null)
            insertTppsmObj(mapTppsm);
        return true;
    }

    private void insertTppsmObj(Map<String, Integer> tppsmMap) {
        String[] propDate;
        Long propertyId;
        Date countDate;
        TppsmCounts tppsmCountsDb;
        TppsmCountsId tppsmCountsIdObj;
        for (String propDateStr1 : tppsmMap.keySet()) {
            propDate = propDateStr1.split("##");
            propertyId = Long.valueOf(propDate[0]);
            countDate = CommonUtility.getDateFromStrng(propDate[1]);

            tppsmCountsDb = psmCountsDao.findByDate(countDate, propertyId);

            if (null == tppsmCountsDb) {
                tppsmCountsIdObj = new TppsmCountsId(propertyId, countDate);
                tppsmCountsDb = new TppsmCounts();
                tppsmCountsDb.setId(tppsmCountsIdObj);
                tppsmCountsDb.setContactviews(0);
                tppsmCountsDb.setDetailviews(tppsmMap.get(propDateStr1));
                tppsmCountsDb.setImpressions(0);
                psmCountsDao.saveTppsmCounts(tppsmCountsDb);

            } else {
                tppsmCountsDb.setDetailviews(tppsmCountsDb.getDetailviews() + tppsmMap.get(propDateStr1));
                psmCountsDao.updateTppsmCounts(tppsmCountsDb);
            }
            LOGGER.debug("Added/Updated: " + propDateStr1);
        }
    }

    /**
     * insert Tppmt view count
     */
    private void insertTppmtObj(Map<String, Integer> tppmtMap) {
        String[] propDateUtm;
        Long propertyId;
        TppmtCounts tppmtCountsDb;
        Date countDate;
        String utmSrc = "";
        TppmtCountsId tppmtCountsIdObj;
        for (String propDateUtmStr1 : tppmtMap.keySet()) {
            propDateUtm = propDateUtmStr1.split("##");
            propertyId = Long.valueOf(propDateUtm[0]);
            countDate = CommonUtility.getDateFromStrng(propDateUtm[1]);

            if (propDateUtm.length == 3)
                utmSrc = propDateUtm[2];

            tppmtCountsDb = pmtCountsDao.findByDate(countDate, propertyId);

            if (null == tppmtCountsDb) {
                tppmtCountsIdObj = new TppmtCountsId(propertyId, countDate);
                tppmtCountsDb = new TppmtCounts();
                tppmtCountsDb.setId(tppmtCountsIdObj);
                tppmtCountsDb.setContactviews(0);
                tppmtCountsDb.setDetailviews(tppmtMap.get(propDateUtmStr1));
                tppmtCountsDb.setImpressions(0);
                if ("mailer".equalsIgnoreCase(utmSrc))
                    tppmtCountsDb.setAlertdetailviews(1);
                pmtCountsDao.savePmtCounts(tppmtCountsDb);
            } else {
                tppmtCountsDb.setDetailviews(tppmtCountsDb.getDetailviews() + tppmtMap.get(propDateUtmStr1));
                if ("mailer".equalsIgnoreCase(utmSrc))
                    tppmtCountsDb.setAlertdetailviews(tppmtCountsDb.getAlertdetailviews() + tppmtMap.get(propDateUtmStr1));
                pmtCountsDao.updatePmtCounts(tppmtCountsDb);
            }
            LOGGER.debug("Added/Updated: " + propDateUtmStr1);
        }
    }
}
