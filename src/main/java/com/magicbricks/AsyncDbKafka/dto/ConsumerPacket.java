package com.magicbricks.AsyncDbKafka.dto;

import java.util.List;

public class ConsumerPacket {

	private int id;
	private String groupId;
	private List<String> topics;

	public ConsumerPacket(int id, String groupId, List<String> topics) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.topics = topics;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}

	@Override
	public String toString() {
		return "ConsumerPacket [id=" + id + ", groupId=" + groupId
				+ ", topics=" + topics + "]";
	}

}
