package com.android.theme.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.framework.utils.DeviceManager;
import com.android.theme.R;
import com.android.theme.utils.Constants;
import com.android.theme.widget.CustomAlertDialog;
import com.android.theme.widget.CustomProgressDialog;

import java.util.ArrayList;

public abstract class BaseActivity extends ToolbarActivity {
	private CustomProgressDialog progressDialog;
	private android.support.v7.app.ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

    public void setActionBar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
    }


	public void setHeaderHide() {
		actionBar.hide();
	}

	public void setHomeIcon(int imageResource) {
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setIcon(imageResource);
	}

	public boolean isDeviceOffline(boolean isToast) {
		boolean isDeviceOffline = DeviceManager.isDeviceOffline(this);
		if (isDeviceOffline && isToast)
			Toast.makeText(this, getString(R.string.internet_failed_toast),
                    Toast.LENGTH_SHORT).show();
		return isDeviceOffline;

	}
	
	@SuppressLint("HandlerLeak")
	protected Handler mainCategoryHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constants.SHOW_PROGRESS_DIALOG:
				showProgressDialog();
				break;
			case Constants.DISMISS_PROGRESS_DIALOG:
				dismissProgressDialog();
				break;

			default:
				break;
			}
		};
	};
	
	protected void showProgressDialog() {
		if (progressDialog == null) {
			progressDialog = new CustomProgressDialog(this,
					getString(R.string.please_wait),
					getString(R.string.loading), false, false);
			if (!isFinishing()) {
				progressDialog.show();
			}
			return;
		}

		if (!progressDialog.isShowing()) {
			if (!isFinishing()) {
				progressDialog.show();
			}
		}
	}
	

	private void dismissProgressDialog() {

		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
	
	public static void showAlert(Context context, String title, String message,
			String possitiveBtnName,
			DialogInterface.OnClickListener possitiveBtnOnClickListener,
			String negativeBtnName,
			DialogInterface.OnClickListener negativeBtnOnClickListener) {
        CustomAlertDialog.CustomAlertDialogDetails dialogDetails = new CustomAlertDialog.CustomAlertDialogDetails();
		if (title != null) {
			dialogDetails.setDialogTitle(title);
		}
		if (message != null) {
			dialogDetails.setDialogMessage(message);
		}
		ArrayList<CustomAlertDialog.DDAlertButton> buttonList = new ArrayList<CustomAlertDialog.DDAlertButton>();
		if (possitiveBtnName != null) {
			CustomAlertDialog.DDAlertButton button = new CustomAlertDialog.DDAlertButton(possitiveBtnName);
			button.setOnClickListener(possitiveBtnOnClickListener);
			buttonList.add(button);
		}
		if (negativeBtnName != null) {
			CustomAlertDialog.DDAlertButton button = new CustomAlertDialog.DDAlertButton(negativeBtnName, CustomAlertDialog.DDAlertButton.RIGHT);
			button.setOnClickListener(negativeBtnOnClickListener);
			buttonList.add(button);
		}
		dialogDetails.setDialogButton(buttonList);
		CustomAlertDialog jumptuitDialog = new CustomAlertDialog(context,
				dialogDetails);
		jumptuitDialog.setCancelable(false);
		jumptuitDialog.show();
	}
}
