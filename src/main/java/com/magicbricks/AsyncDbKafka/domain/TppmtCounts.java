package com.magicbricks.AsyncDbKafka.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TPPMTCOUNTS")
public class TppmtCounts implements Serializable{
	private static final long serialVersionUID = -691805740133973556L;
	@EmbeddedId
	private TppmtCountsId id;
	@Column(name="DETAILVIEWS")
	private int detailviews;
	@Column(name="CONTACTVIEWS")
	private int contactviews;
	@Column(name="IMPRESSIONS")
	private int impressions;
	@Column(name="SPAMSCORE")
	private int spamCore;
	@Column(name="SHORTLISTCOUNT")
	private int shortlistcount;
	@Column(name="EFFIMPRESSIONS")
	private double effimpressions;
	@Column(name="ALERTDETAILVIEWS")
	private int alertdetailviews;
	
	@Column(name = "CREATEDATE", nullable = false, length = 26)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdate;
	@Column(name = "MODIDATE", nullable = false, length = 26)
	@Temporal(TemporalType.TIMESTAMP)
    private Date modidate;

	public TppmtCounts(){
		
	}
	
	public int getShortlistcount() {
		return shortlistcount;
	}
	public void setShortlistcount(int shortlistcount) {
		this.shortlistcount = shortlistcount;
	}
	public double getEffimpressions() {
		return effimpressions;
	}
	public void setEffimpressions(double effimpressions) {
		this.effimpressions = effimpressions;
	}
	public TppmtCountsId getId() {
		return id;
	}
	public void setId(TppmtCountsId id) {
		this.id = id;
	}
	public int getDetailviews() {
		return detailviews;
	}
	public void setDetailviews(int detailviews) {
		this.detailviews = detailviews;
	}
	public int getContactviews() {
		return contactviews;
	}
	public void setContactviews(int contactviews) {
		this.contactviews = contactviews;
	}
	public int getImpressions() {
		return impressions;
	}
	public void setImpressions(int impressions) {
		this.impressions = impressions;
	}
	public int getSpamCore() {
		return spamCore;
	}
	public void setSpamCore(int spamCore) {
		this.spamCore = spamCore;
	}

	public int getAlertdetailviews() {
		return alertdetailviews;
	}

	public void setAlertdetailviews(int alertdetailviews) {
		this.alertdetailviews = alertdetailviews;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getModidate() {
		return modidate;
	}

	public void setModidate(Date modidate) {
		this.modidate = modidate;
	}
	
	
}
