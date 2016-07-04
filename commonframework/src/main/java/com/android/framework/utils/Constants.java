package com.android.framework.utils;

/**
 * Created by sathish on 18-12-2015.
 */
public class Constants {

    //api constants
    public static final int SERVICE_PROD = 3;
    public static final int SERVICE_DEMO = 2;
    public static final int SERVICE_LOCAL = 1;

    public static int SERVER_MODE=SERVICE_DEMO;

    //api constants
    public static String SERVICE_PROD_JSON = "service_prod.json";
    public static String SERVICE_DEMO_JSON = "service_demo.json";
    public static String SERVICE_LOCAL_JSON = "service_local.json";
    public static String EMPTY = "";

    public static final String ENCODING_TYPE="utf-8";

    public static int SERVICE_SUCCESS_STATUS=200;

    //LoginManager
    public static final String LOGIN = "Login";
    public static final String NULL = "null";
    // Secure Preferences
    public static final String AES_VALUE = "AES";
    public static final String AES_UTF = "UTF-8";
    public static final String AES_ENCODE_KEY = "PBEWithSHA256And256BitAES-CBC-BC";
    public static final String ENCRYPT_EXCEPTION = "Encrypt Exception";
    public static final String DECRYPT_EXCEPTION = "Decrypt Exception";

    public static final String DetailsIntent = "DetailsIntent";

    public static int loginRequest=300;
    public static int profileRequest=301;
    public static int serviceRequest=302;


    public static String SEND_LOGIN_SUCCESS="SEND_LOGIN_SUCCESS";
}
