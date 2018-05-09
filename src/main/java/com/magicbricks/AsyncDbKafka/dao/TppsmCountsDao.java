package com.magicbricks.AsyncDbKafka.dao;
import com.magicbricks.AsyncDbKafka.domain.TppsmCounts;

import java.util.Date;

/**
 * @author Divya Gupta
 *
 */
public interface TppsmCountsDao {
	 TppsmCounts findByDate(Date date, Long psmrfnum);
	 boolean updateTppsmCounts(TppsmCounts tppsmCounts);
	 boolean saveTppsmCounts(TppsmCounts tppsmCounts);
}
