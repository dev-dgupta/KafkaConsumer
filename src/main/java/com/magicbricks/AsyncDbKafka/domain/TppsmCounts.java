package com.magicbricks.AsyncDbKafka.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * @author Divya Gupta
 *
 */
@Entity
@Table(name = "TPPSMCOUNTS", schema = "PROPERTY")
public class TppsmCounts implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private TppsmCountsId id;
	@Column(name = "DETAILVIEWS")
	private Integer detailviews; 
	@Column(name = "CONTACTVIEWS")
	private Integer contactviews;
	@Column(name = "IMPRESSIONS")
	private Integer impressions;
	@Column(name = "SPAMSCORE")
	private Integer spamscore;
	
	@Column(name = "CREATEDATE", nullable = false, length = 26)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdate;
	@Column(name = "MODIDATE", nullable = false, length = 26)
	@Temporal(TemporalType.TIMESTAMP)
    private Date modidate;
	
	public Integer getDetailviews() {
		return detailviews;
	}
	public void setDetailviews(Integer detailviews) {
		this.detailviews = detailviews;
	}
	public Integer getContactviews() {
		return contactviews;
	}
	public void setContactviews(Integer contactviews) {
		this.contactviews = contactviews;
	}
	public Integer getImpressions() {
		return impressions;
	}
	public void setImpressions(Integer impressions) {
		this.impressions = impressions;
	}
	public Integer getSpamscore() {
		return spamscore;
	}
	public void setSpamscore(Integer spamscore) {
		this.spamscore = spamscore;
	}
	public TppsmCountsId getId() {
		return id;
	}
	public void setId(TppsmCountsId id) {
		this.id = id;
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
