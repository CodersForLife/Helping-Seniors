package com.android.theme.widget;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.KeyEvent;

import java.util.List;

public class CustomAlertDialog extends AlertDialog.Builder implements OnKeyListener{

	public static class CustomAlertDialogDetails {
		private Drawable dialogIcon;
		private String dialogTitle;
		private String dialogMessage;
		private List<DDAlertButton> dialogButton;

		public Drawable getDialogIcon() {
			return dialogIcon;
		}
		public void setDialogIcon(Drawable dialogIcon) {
			this.dialogIcon = dialogIcon;
		}
		public String getDialogTitle() {
			return dialogTitle;
		}
		public void setDialogTitle(String dialogTitle) {
			this.dialogTitle = dialogTitle;
		}
		public String getDialogMessage() {
			return dialogMessage;
		}
		public void setDialogMessage(String dialogMessage) {
			this.dialogMessage = dialogMessage;
		}
		public List<DDAlertButton> getDialogButton() {
			return dialogButton;
		}
		public void setDialogButton(List<DDAlertButton> dialogButton) {
			this.dialogButton = dialogButton;
		}



	}

	public static class DDAlertButton {
		public static final int LEFT = 1;
		public static final int RIGHT = 2;
		private final String mBtnName;
		private final int mBtnPosition;
		private DialogInterface.OnClickListener onClickListener;

		public DialogInterface.OnClickListener getOnClickListener() {
			return onClickListener;
		}
		public void setOnClickListener(DialogInterface.OnClickListener onClickListener) {
			this.onClickListener = onClickListener;
		}
		public DDAlertButton(String btnName , int btnPositon) {
			mBtnName = btnName;
			mBtnPosition = btnPositon;
		}
		public DDAlertButton(String btnName) {
			mBtnName = btnName;
			mBtnPosition = LEFT;
		}
	}

	public CustomAlertDialog(Context context, CustomAlertDialogDetails aeoDialogDetails) {
		super(context);
		buildAEODialog(aeoDialogDetails);
		setOnKeyListener(this);
	}

	private void buildAEODialog(CustomAlertDialogDetails aeoDialogDetails) {

		if (aeoDialogDetails == null) {
			return;
		}

		if (aeoDialogDetails.dialogIcon != null) {
			setIcon(aeoDialogDetails.dialogIcon);
		}

		if (!TextUtils.isEmpty(aeoDialogDetails.dialogTitle)) {
			setTitle(aeoDialogDetails.dialogTitle);
		}

		if (!TextUtils.isEmpty(aeoDialogDetails.dialogMessage)) {
			setMessage(aeoDialogDetails.dialogMessage);
		}

		if (aeoDialogDetails.dialogButton != null) {
			for (int i = 0; i < aeoDialogDetails.dialogButton.size(); i++) {

				switch (aeoDialogDetails.dialogButton.get(i).mBtnPosition) {

				case DDAlertButton.RIGHT:

					setNegativeButton(aeoDialogDetails.dialogButton.get(i).mBtnName,
							aeoDialogDetails.dialogButton.get(i).onClickListener);


					break;
				case DDAlertButton.LEFT:

					setPositiveButton(aeoDialogDetails.dialogButton.get(i).mBtnName,
							aeoDialogDetails.dialogButton.get(i).onClickListener);


					break;
				default:
					break;
				}
			}
		}
	}

	@Override
	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		boolean flag=false;
		if(keyCode == KeyEvent.KEYCODE_SEARCH)
		{
			flag=true;
			return flag;
		}
		return flag;
	}
}

