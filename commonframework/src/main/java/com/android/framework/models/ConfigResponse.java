package com.android.framework.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConfigResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@SerializedName("api")
	private APIService data;

	public APIService getdata() {
		return data;
	}

}