package com.mandreatti.cursomc.resources.exception;

import java.io.Serializable;

public class StandarError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String msg;
	private Long timeStump;
	public StandarError(Integer status, String msg, Long timeStump) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStump = timeStump;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getTimeStump() {
		return timeStump;
	}
	public void setTimeStump(Long timeStump) {
		this.timeStump = timeStump;
	}
	
	

}
