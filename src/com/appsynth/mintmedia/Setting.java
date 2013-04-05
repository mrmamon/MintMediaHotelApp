package com.appsynth.mintmedia;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class Setting extends Activity{
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			setContentView(R.layout.setting);

			Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/TrajanPro-Bold.otf");
			Button hotelListBtn = (Button)findViewById(R.id.hotelListBtn);
			Button editProfileBtn = (Button)findViewById(R.id.EditProfileBtn);
			Button logoutBtn = (Button)findViewById(R.id.LogOutBtn);

			hotelListBtn.setTextColor(Color.parseColor("#9B7C3B"));
			editProfileBtn.setTypeface(tf);
			logoutBtn.setTypeface(tf);


		}

}