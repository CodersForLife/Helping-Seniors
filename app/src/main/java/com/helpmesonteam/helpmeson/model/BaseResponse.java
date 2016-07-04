package com.helpmesonteam.helpmeson.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @SerializedName("error")
    private boolean error;


    @SerializedName("error_code")
    private int error_code;


    @SerializedName("message")
    private String message;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
