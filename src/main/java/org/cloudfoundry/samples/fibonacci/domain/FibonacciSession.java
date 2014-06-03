package org.cloudfoundry.samples.fibonacci.domain;

public class FibonacciSession {

	private String id;
	private Integer min = 1;
	private Integer max = 10;
	private Integer count = 1;
	private Long startTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	@Override
	public String toString() {
		return "FibonacciSession [id=" + id + ", min=" + min + ", max=" + max + ", count=" + count + ", startTime=" + startTime + "]";
	}
	
}	
