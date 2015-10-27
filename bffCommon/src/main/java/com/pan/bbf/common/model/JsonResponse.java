package com.pan.bbf.common.model;

/**
 * 
 * 与前台交互基础类
 * 
 */
public class JsonResponse {

	/**
     * 状态码
     */
    private String status;

    /**
     * 反馈信息
     */
    private Object result;

    private String location;

    /**
     * 错误信息
     */
    private String errorMessage;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
