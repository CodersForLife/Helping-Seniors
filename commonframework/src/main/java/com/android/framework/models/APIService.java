package com.android.framework.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

public class APIService implements Serializable {

	private static final long serialVersionUID = 1L;

	@SerializedName("apiservice")
	private Map<String, String> apiservice;

	@SerializedName("apikey")
	private Map<String, String> apikey;

	@SerializedName("googleAPIService")
	private Map<String, String> googleAPIService;


	public Map<String, String> getApiservice() {
		return apiservice;
	}

	public Map<String, String> getApikey() {
		return apikey;
	}

	public Map<String, String> getGoogleAPIService() {
		return googleAPIService;
	}
}
