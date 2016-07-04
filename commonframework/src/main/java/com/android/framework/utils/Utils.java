package com.android.framework.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.Settings.Secure;
import android.widget.Toast;

import com.android.framework.R;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import com.android.framework.models.ConfigResponse;


public class Utils {

	private static Toast mToast;

	private static ConfigResponse configResponse;
	
	public static ConfigResponse getResponse() {
		return configResponse;
	}
	
	public static void setResponse(ConfigResponse response) {
		Utils.configResponse = response;
	}
	

	public static void globalConfigurationIntitalize(Context context){
		try {
            String jsonFileName=Constants.SERVICE_LOCAL_JSON;

			switch (Constants.SERVER_MODE){
				case Constants.SERVICE_LOCAL:
					jsonFileName=Constants.SERVICE_LOCAL_JSON;
					break;
				case Constants.SERVICE_DEMO:
					jsonFileName=Constants.SERVICE_DEMO_JSON;
					break;
				case Constants.SERVICE_PROD:
					jsonFileName=Constants.SERVICE_PROD_JSON;
					break;

			}

			Utils.LoadApi(context, jsonFileName);

		} catch (Exception e) {
		}
	}

	public static void LoadApi(InputStream inputStream) {
		Gson gson = new Gson();
		Reader reader = new InputStreamReader(inputStream);
		configResponse = gson.fromJson(reader, ConfigResponse.class);
	}

	public synchronized static void LoadApi(Context context , String fileName) {
		InputStream inputStream=null;
		try {
			inputStream = context.getResources().getAssets().open(fileName);
			LoadApi(inputStream);
		} catch (Exception e) {
		} finally {
			if( inputStream != null  ) {
				try {
					inputStream.close();
				} catch (IOException ignore) {}
			}
		}
	}
	
	 public static int getResId(String variableName, Class<?> c) {

	        Field field = null;
	        int resId = 0;
	        try {
	            field = c.getField(variableName);
	            try {
	                resId = field.getInt(null);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return resId;

	    }
	 


	 public static String getAndroidSecureId(Context context){

		 String android_id = Secure.getString(context.getContentResolver(),
                 Secure.ANDROID_ID);
		  return android_id;
	 }
	 
	 public static PackageInfo getPackageInfo(Context context){
		
		 try {
			PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return pInfo;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	 }
	 
	 public static int getAppVersionCode(Context context){
		 if(getPackageInfo(context)!=null){
			 int versionCode=getPackageInfo(context).versionCode;
				return versionCode;
		 }
		 return 0;
	 }

    public static String getAppVersionname(Context context){
        if(getPackageInfo(context)!=null){
            String versionName=getPackageInfo(context).versionName;
            return versionName;
        }
        return Constants.EMPTY;
    }
	 
	 public static String getBaseImageUrl(Context context){
		String imageUrl=Utils.getResponse().getdata().getApiservice().get(ServiceConstants.SERVER_URL_KEY)+ Utils.getResponse().getdata().getApiservice().get(ServiceConstants.IMAGE_URL_ENDPOINT);
		if(imageUrl!=null){
			return imageUrl;
		}else{
			return null;
		}
	 }
	 
	 public static void ShareIntentMessage(Activity context ,String message){
		 Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT,message);
			sendIntent.setType("text/plain");
			sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(Intent.createChooser(sendIntent, "Share using"));
	 }

    public static String getEncodedUrl(String url){
        try{
           return URLEncoder.encode(url, Constants.ENCODING_TYPE);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return  Constants.EMPTY;

    }

	public static void callNumber(String no,Context context){

	}

	public static void launchMarket(Context context) {
		Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
		Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
		try {
			context.startActivity(myAppLinkToMarket);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(context, context.getResources().getString(R.string.app_not_available), Toast.LENGTH_LONG).show();
		}
	}

	public static int getAppVersion(Activity application) {
		try {
			PackageInfo packageInfo = application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			// should never happen
			throw new RuntimeException("Could not get package name: " + e);
		}
	}

	public static void displayToast(Context context, String text, int duration) {
		if (mToast != null) mToast.cancel();
		mToast = Toast.makeText(context, text, duration);
		mToast.show();
	}

	public static File getFileFromBitmap(Context context, Bitmap bitmap){
		File tempFile = null;
		try {
			tempFile = new File(context.getCacheDir(), "temp.jpg");
			tempFile.createNewFile();

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 20, bos);
			byte[] bitmapdata = bos.toByteArray();

			FileOutputStream fos = new FileOutputStream(tempFile);
			fos.write(bitmapdata);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempFile;
	}
}
