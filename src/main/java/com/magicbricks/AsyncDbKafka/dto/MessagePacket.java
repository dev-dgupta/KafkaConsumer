package com.magicbricks.AsyncDbKafka.dto;

import java.io.Serializable;

/**
 * @author Ankur.Goel
 *
 */
public class MessagePacket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long msgId;
	private Long referenceId;
	private String name;
	private String mobileNo;
	private String smsBody;
	private String eventType;

	public MessagePacket(Long msgId, Long referenceId, String name,
			String mobileNo, String smsBody, String eventType) {
		super();
		this.msgId = msgId;
		this.referenceId = referenceId;
		this.name = name;
		this.mobileNo = mobileNo;
		this.smsBody = smsBody;
		this.eventType = eventType;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getSmsBody() {
		return smsBody;
	}

	public void setSmsBody(String smsBody) {
		this.smsBody = smsBody;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((mobileNo == null) ? 0 : mobileNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessagePacket other = (MessagePacket) obj;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (mobileNo == null) {
			if (other.mobileNo != null)
				return false;
		} else if (!mobileNo.equals(other.mobileNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MessagePacket [msgId=" + msgId + ", referenceId=" + referenceId
				+ ", name=" + name + ", mobileNo=" + mobileNo + ", smsBody="
				+ smsBody + ", eventType=" + eventType + "]";
	}

}
