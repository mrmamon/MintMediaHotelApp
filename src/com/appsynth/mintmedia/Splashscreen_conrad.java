package com.appsynth.mintmedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Splashscreen_conrad extends Activity {
	protected int _splashTime = 2000;

	private Handler myHanler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splashscreen_conrad);

		myHanler = new Handler();
		myHanler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				finish();
				Intent intent = new Intent(Splashscreen_conrad.this,Splashscreen_mintmedia.class);
				startActivity(intent);
			}
		}, _splashTime);
	}
}
