package com.technoee.jmaghrebsched.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.technoee.jmaghrebsched.R;

public class Register {

	public static void showRegistrationView(Context context) {
		Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.activity_registration);
		dialog.show();
	}
}
