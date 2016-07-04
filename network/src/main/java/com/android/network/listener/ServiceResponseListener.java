package com.android.network.listener;

public interface ServiceResponseListener<RESULT> {


    void onSuccess(RESULT response);

    void onFailure(Throwable throwable, String errorResponse);

}
