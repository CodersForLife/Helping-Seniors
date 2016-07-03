package com.android.network.manager;

import android.content.Context;

import com.android.network.beans.ServiceRequest;
import com.android.network.listener.ResponseListener;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.Serializable;


public class NetworkManager {
	private static int timeout=20*1000;
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	public static <T extends Serializable> void executeJsonAsync(Context context,ServiceRequest request,final Class<T> entity,final ResponseListener<T> listener) throws Exception{
		try{
			client.setTimeout(timeout);
			switch (request.getVerb()) {
			case GET:
				
				client.get(context,request.getURL(), new AsyncHttpResponseHandler(){
					
					@Override
					public void onSuccess(int arg0, String arg1) {
						super.onSuccess(arg0, arg1);
						if(arg1!=null && arg1.length()>0){
							listener.onSuccess(prepareResponse(arg1, entity));
						}else{
							listener.onSuccess(arg1);
						}
						
					}
					
					@Override
					public void onFailure(Throwable arg0, String arg1) {
						super.onFailure(arg0, arg1);
						listener.onFailure(arg0, arg1);
					}
				});
				break;
			case POST:
				RequestParams requestParams = request.getRequestParams();
				client.post(context,request.getURL(), requestParams, new AsyncHttpResponseHandler(){
					@Override
					public void onSuccess(int arg0, String arg1) {
						super.onSuccess(arg0, arg1);
						listener.onSuccess(prepareResponse(arg1, entity));
					}
					
					@Override
					public void onFailure(Throwable arg0, String arg1) {
						super.onFailure(arg0, arg1);
						listener.onFailure(arg0, arg1);
					}
				});
				break;

			default:
				break;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	private static <T> T prepareResponse(String responseString,Class<T> clazz) {

		T t = null;

		Gson gson = new Gson();
		t = gson.fromJson(responseString, clazz);
		return t;
	}
	
	public static void cancelRequests(Context ctx) {
        client.cancelRequests(ctx,true);
    }

}
