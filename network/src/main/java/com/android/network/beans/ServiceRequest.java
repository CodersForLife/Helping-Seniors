package com.android.network.beans;

import com.loopj.android.http.RequestParams;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The model class for HTTP/HTTPS requests. Implements {Cacheable} annotation to
 * generate unique cache key and cache response
 * 
 */
public class ServiceRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	private int port;
	private HttpVerb verb;
	private Map<String, String> params;
	private String userId;
	private String password;
	private String body;
	private String customContentType;
	private Map<String, Class<?>> attributesMap;
	private Map<String, Class<?>> aliasMap;
	private int timeout;
	private int cacheTimeoutMinutes = 0;
	private Map<String, String> customHeaderMap;
	private String encodingType;
	private boolean utfEncodingRequired = false;
	private RequestParams requestParams;
	
	private ServiceRequest(Builder builder) {
		this.url = builder.URL;
		this.port = builder.port;
		this.verb = builder.verb;
		this.params = builder.params;
		this.body = builder.body;
		this.timeout = builder.timeout;
		this.cacheTimeoutMinutes = builder.cacheTimeoutMinutes;
		this.attributesMap = builder.attributesMap;
		this.aliasMap = builder.aliasMap;
		this.customContentType = builder.customContentType;
		this.userId = builder.userId;
		this.password = builder.password;
		this.customHeaderMap = builder.customHeaderMap;
		this.encodingType = builder.encodingType;
		this.utfEncodingRequired = builder.utfEncodingRequired;
		this.requestParams=builder.requestParams;
	}
	
	public int getCacheTimeout(){
		return this.cacheTimeoutMinutes;
	}



	public static class Builder {
		private String URL;
		private int port;
		private HttpVerb verb = HttpVerb.GET;
		private Map<String, String> params = Collections.emptyMap();
		private Map<String, Class<?>> attributesMap;
		private Map<String, Class<?>> aliasMap;
		private String encodingType="UTF8";
		private String body;
		private int timeout;
		private String customContentType; 
		private int cacheTimeoutMinutes = 0;
		private Map<String, String> customHeaderMap;
		private String userId;
		private String password;
		private boolean utfEncodingRequired = false;
		private RequestParams requestParams;
		public Builder(String url) {
			this.URL = url;
		}

		public Builder utfEncodingRequired(boolean state){
			this.utfEncodingRequired = state;
			
			return this;
		}
		
		public Builder port(int p) {
			this.port = p;
			return this;
		}

		public Builder verb(HttpVerb verb)
		{
			this.verb = verb;
			return this;
		}
		
		public Builder parameters(HashMap<String, String> params) {
			this.params = params;
			return this;
		}

		public Builder body(String s) {
			this.body = s;
			return this;
		}

		public Builder addAttributes(Map<String, Class<?>> atts) {
			this.attributesMap = atts;
			return this;
		}
		
		public Builder addAttribute(String name, Class<?> type) {
			if (this.attributesMap == null) {
				this.attributesMap = new HashMap<String, Class<?>>();
			}
			this.attributesMap.put(name, type);
			return this;
		}

		public Builder addAlias(String aliasName, Class<?> converter) {
			if (this.aliasMap == null) {
				this.aliasMap = new HashMap<String, Class<?>>();
			}
			this.aliasMap.put(aliasName, converter);
			return this;
		}

		public Builder timeout(int t) {
			this.timeout = t;
			return this;
		}

		public Builder cacheTimeout(int cacheTimeoutMinutes){
			this.cacheTimeoutMinutes = cacheTimeoutMinutes;
			return this;
		}
		
		public Builder customContentType(String type){
			this.customContentType = type;
			return this;
		}
		
		public Builder encodingType(String encodingType){
			this.encodingType = encodingType;
			return this;
		}

		public Builder basicAuthentication(String userid,String password) {
			this.userId = userid;
			this.password=password;
			return this;
		}
		
		public Builder addHeader(String name,String value){
			if(customHeaderMap == null) {
				customHeaderMap = new HashMap<String,String>();
			}
			customHeaderMap.put(name, value);
			return this;
		}
		public Builder oauthRequest(String clientId, String clientSecret,
				String authToken) {
			params.put("client_id", clientId);
			params.put("client_secret", clientSecret);
			params.put("client_secret", "OAuth " + authToken);
			return this;
		}
		
		public Builder addRequestParams(RequestParams requestParams){
			this.requestParams=requestParams;
			return this;
		}

		public ServiceRequest build() {
			if(this.port <= 0 && this.URL != null) {
				this.port = this.URL.toLowerCase().startsWith("https") ? 443 : 80;
			}
			return new ServiceRequest(this);
		}
	}

	public String getURL() {
		return url;
	}
	
	public int getPort() {
		return port;
	}

	public HttpVerb getVerb() {
		return verb;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public String getBody() {
		return body;
	}

	public Map<String, Class<?>> getAliasMap() {
		return aliasMap;
	}
	
	public Map<String, Class<?>> getAttributesMap() {
		return attributesMap;
	}

	

	public int getTimeout() {
		return this.timeout;
	}


	public String getCustomContentType() {
		return customContentType;
	}
	
	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	
	public Map<String, String> getCustomHeaderMap() {
		return customHeaderMap;
	}

	// TODO implement using NIO
	@Override
	public String toString() {
		return super.toString();
	}

	public String getEncodingType() {
		return encodingType;
	}

	public boolean isUtfEncodingRequired() {
		return utfEncodingRequired;
	}

	public RequestParams getRequestParams() {
		return requestParams;
	}
	
	
}