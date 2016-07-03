package com.android.network.beans;

import java.io.Serializable;

public class ServiceResponse<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private T targetType;
	
	private int httpStatusCode;
	
	private String cacheKey;
	
	private String cookie;
	
	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatus) {
		this.httpStatusCode = httpStatus;
	}

	public T getTargetType() {
		return targetType;
	}

	public void setTargetType(T targetType) {
		this.targetType = targetType;
	}

	public String getCacheKey() {
		return cacheKey;
	}

	public void setCacheKey(String cacheKey) {
		this.cacheKey = cacheKey;
	}
}