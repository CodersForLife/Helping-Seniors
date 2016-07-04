package com.android.theme.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.android.theme.R;

public class CustomProgressDialog extends AlertDialog implements OnKeyListener {

	public CustomProgressDialog(Context context, String title, String message, boolean indeterminate, boolean cancelable) {
		super(context);
		setCanceledOnTouchOutside(false);
		setCancelable(false);
		setOnKeyListener(this);
		View view= LayoutInflater.from(getContext()).inflate(R.layout.emp_dialog,null);
		setView(view);
	}

	@Override
	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
