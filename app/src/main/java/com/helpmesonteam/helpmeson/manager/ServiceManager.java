package com.helpmesonteam.helpmeson.manager;

import android.content.Context;
import android.text.TextUtils;

import com.android.framework.utils.Constants;
import com.android.framework.utils.ServiceConstants;
import com.android.framework.utils.Utils;
import com.android.network.beans.HttpVerb;
import com.android.network.beans.ServiceRequest;
import com.android.network.listener.ResponseListener;
import com.android.network.listener.ServiceResponseListener;
import com.android.network.manager.NetworkManager;
import com.helpmesonteam.helpmeson.model.RequestHelp;
import com.loopj.android.http.RequestParams;

/**
 * Created by karthikeyan on 04-07-2016.
 */
public class ServiceManager {


    //register

    public static void registerRequest(Context context, RequestParams requestParams, final ServiceResponseListener<RequestHelp> listener) {

        String targetUrl = getServiceAPIUrl(ServiceConstants.RegisterRequest);

        ServiceRequest request = new ServiceRequest.Builder(targetUrl).verb(HttpVerb.POST).addRequestParams(requestParams).build();
        try {
            NetworkManager.executeJsonAsync(context, request, RequestHelp.class, new ResponseListener<RequestHelp>() {

                @Override
                public void onSuccess(RequestHelp response) {
                    if (listener != null) {
                        listener.onSuccess(response);
                    }
                }

                @Override
                public void onFailure(Throwable throwable, String errorResponse) {
                    if (listener != null) {
                        listener.onFailure(throwable, errorResponse);
                    }
                }

                @Override
                public Class<RequestHelp> getTargetType() {
                    // TODO Auto-generated method stub
                    return null;
                }

                @Override
                public void onFinish() {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onStart() {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onSuccess(String arg0) {
                    // TODO Auto-generated method stub
                    listener.onSuccess(null);

                }
            });

        } catch (Exception e) {
            listener.onFailure(e.getCause(), "");
        }
    }

    //getting service url
    public static String getServiceAPIUrl(String key) {

        if (TextUtils.isEmpty(key)) {
            return Constants.EMPTY;
        }

        return Utils.getResponse().getdata().getApiservice().get(ServiceConstants.SERVER_URL_KEY) +
                Utils.getResponse().getdata().getApiservice().get(ServiceConstants.API_URL_ENDPOINT) +
                Utils.getResponse().getdata().getApiservice().get(key);

    }
}
