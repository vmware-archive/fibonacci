package org.cloudfoundry.samples.fibonacci.domain;

public class FibonacciRequest {
	
	private Integer value;
	private Integer id;
	private String sessionId;
	
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Override
	public String toString() {
		return "FibonacciRequest [value=" + value + ", id=" + id + ", sessionId=" + sessionId + "]";
	}
	
}
