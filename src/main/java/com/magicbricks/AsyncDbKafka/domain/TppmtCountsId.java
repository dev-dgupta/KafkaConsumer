package com.magicbricks.AsyncDbKafka.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Embeddable
public class TppmtCountsId implements Serializable{
	@Column(name="PMTCOUNTSRFNUM")
	private Long pmtcountsrfnum;
	@Column(name="COUNTDATE",length = 26)
	@Temporal(TemporalType.DATE)
	private Date countdate;
	
	public TppmtCountsId(){
	}
	
	public TppmtCountsId(Long pmtcountsrfnum,Date countdate){
		this.pmtcountsrfnum=pmtcountsrfnum;
		this.countdate=countdate;
	}
	
	public Long getPmtcountsrfnum() {
		return pmtcountsrfnum;
	}
	public void setPmtcountsrfnum(Long pmtcountsrfnum) {
		this.pmtcountsrfnum = pmtcountsrfnum;
	}
	public Date getCountdate() {
		return countdate;
	}
	public void setCountdate(Date countdate) {
		this.countdate = countdate;
	}
	

}
