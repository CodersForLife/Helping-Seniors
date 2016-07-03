package com.android.framework.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * The class used to manage hardware interactions
 * 
 */
public class DeviceManager {

	/**
	 * Plays the media file
	 * 
	 * @param afd
	 */
	public static void beep(AssetFileDescriptor afd) {
		try {
			MediaPlayer mMediaplayer = new MediaPlayer();
			mMediaplayer.setDataSource(afd.getFileDescriptor(),
					afd.getStartOffset(), afd.getLength());
			afd.close();
			mMediaplayer.prepare();
			mMediaplayer.start();
			mMediaplayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer mMediaPlayer) {
					mMediaPlayer.stop();
					mMediaPlayer.release();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// beep

	/**
	 * Returns true if device network is offline
	 * 
	 * @return
	 */
	public static boolean isDeviceOffline(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
		return (activeNetworkInfo == null || !activeNetworkInfo
				.isConnectedOrConnecting());
	}// isDeviceOffline

	/**
	 * Get detailed network information about the device
	 * 
	 * @return
	 */
	public static NetworkInfo getNetworkInfo(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo();
	}// getNetworkInfo

	/**
	 * Gets the current device location Device Network is used to get the
	 * coordinates
	 * 
	 * @return
	 */
	public static Location getCurrentLocation(Context context) {
		LocationManager lm = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		return lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	}// getCurrentLocation

	/**
	 * LocationListener is used for receiving notifications from the
	 * LocationManager when the location has changed. This method registers the
	 * listener and starts listening
	 * 
	 * @param listener
	 */
	public static void registerLocationListener(LocationListener listener,Context context) {
		LocationManager lm = (LocationManager)context
				.getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
				listener);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
	}//registerLocationListener

	
	/**
	 * Stops listening for location changes
	 * @param listener
	 */
	public static void unregisterLocationListener(LocationListener listener,Context context) {
		LocationManager lm = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		lm.removeUpdates(listener);
	}//unregisterLocationListener

	
	/**
	 * Returns the better location among the two. The selection algorithm is
	 * based on location time (newer than two minutes), accuracy and network
	 * provider
	 * 
	 * @param newLocation
	 * @param currentBestLocation
	 * @return
	 */
	public Location selectBetterLocation(Location newLocation,
			Location currentBestLocation) {
		return isBetterLocation(newLocation, currentBestLocation) ? newLocation
				: currentBestLocation;
	}//selectBetterLocation

	private boolean isBetterLocation(Location newLocation,
			Location currentBestLocation) {
		int TWO_MINUTES = 1000 * 60 * 2;

		if (currentBestLocation == null) {
			return true;
		}

		// Check whether the new location fix is newer or older
		long timeDelta = newLocation.getTime() - currentBestLocation.getTime();
		boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
		boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
		boolean isNewer = timeDelta > 0;

		if (isSignificantlyNewer) {
			return true;
			// If the new location is more than two minutes older, it must be
			// worse
		} else if (isSignificantlyOlder) {
			return false;
		}

		// Check whether the new location fix is more or less accurate
		int accuracyDelta = (int) (newLocation.getAccuracy() - currentBestLocation
				.getAccuracy());
		boolean isLessAccurate = accuracyDelta > 0;
		boolean isMoreAccurate = accuracyDelta < 0;
		boolean isSignificantlyLessAccurate = accuracyDelta > 200;

		// Check if the old and new location are from the same provider
		boolean isFromSameProvider = isSameLocationProvider(
				newLocation.getProvider(), currentBestLocation.getProvider());

		// Determine location quality using a combination of timeliness and
		// accuracy
		if (isMoreAccurate) {
			return true;
		} else if (isNewer && !isLessAccurate) {
			return true;
		} else if (isNewer && !isSignificantlyLessAccurate
				&& isFromSameProvider) {
			return true;
		}
		return false;
	}//isBetterLocation

	/**
	 * Compares and confirms if the location providers are the same
	 * @param provider1
	 * @param provider2
	 * @return
	 */
	public boolean isSameLocationProvider(String provider1, String provider2) {
		if (provider1 == null) {
			return provider2 == null;
		}
		return provider1.equals(provider2);
	}//isSameLocationProvider
}