package com.magicbricks.AsyncDbKafka.dao;


import com.magicbricks.AsyncDbKafka.domain.TppmtCounts;

import java.util.Date;

public interface TppmtCountsDao {
	 TppmtCounts findByDate(Date date, Long pmtrfnum);
	 boolean updatePmtCounts(TppmtCounts pmtCounts);
	 boolean savePmtCounts(TppmtCounts pmtCounts);
	
}
