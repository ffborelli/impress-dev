package br.ufabc.impress.mqtt;

public class MqttPublishModel {
	
	private String uri, clientId, topic, messageSend;
	
	public MqttPublishModel(String uri, String clientId, String topic, String messageSend){
		this.uri = uri;
		this.clientId = clientId;
		this.topic = topic;
		this.messageSend = messageSend;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMessageSend() {
		return messageSend;
	}

	public void setMessageSend(String messageSend) {
		this.messageSend = messageSend;
	}
	
	

}
