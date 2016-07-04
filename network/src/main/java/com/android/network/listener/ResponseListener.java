package com.android.network.listener;


/**
 * A callback interface to handle asynchronous service invocation
 * 
 * @param <ResponseEntity>
 */
public interface ResponseListener<ResponseEntity> {

	// Fired when a request fails to complete
	public void onFailure(Throwable error, String response);

	// Fired in all cases when the request is finished, after both success and
	// failure
	public void onFinish();

	// Fired when the request is started
	public void onStart();

	// Fired when a request returns successfully
	public void onSuccess(ResponseEntity response);

	// Fired when a request returns successfully
	public void onSuccess(String response);
	
	public Class<ResponseEntity> getTargetType();
	
}