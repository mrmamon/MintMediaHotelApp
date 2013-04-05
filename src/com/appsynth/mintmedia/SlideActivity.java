package com.appsynth.mintmedia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SlideActivity extends Activity implements OnCheckedChangeListener {

	protected ImageView imshow;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.home_test);

		ImageButton personalBtn = (ImageButton) findViewById(R.id.personalBtn);
		ImageButton infoBtn = (ImageButton) findViewById(R.id.infoBtn);
		ImageButton enterBtn = (ImageButton) findViewById(R.id.entertainBtn);
		ImageButton nearbyBtn = (ImageButton) findViewById(R.id.nearbyBtn);
		ImageButton bookingBtn = (ImageButton) findViewById(R.id.bookingBtn);
		ImageButton promotBtn = (ImageButton) findViewById(R.id.promotionBtn);
		ImageButton otploginBtn = (ImageButton) findViewById(R.id.otp_loginBtn);
		final ImageButton settingBtn = (ImageButton) findViewById(R.id.settingBtn);
		
		
		final RadioGroup radGr = (RadioGroup) findViewById(R.id.radioGr_slideImage);

		radGr.setOnCheckedChangeListener(this);
		imshow = (ImageView) findViewById(R.id.imageSlideshow);
		final int[] imageArray = { R.drawable.conrad1, R.drawable.conrad2,
				R.drawable.conrad3, R.drawable.conrad4,R.drawable.conrad5,R.drawable.conrad6};
		final Handler handler = new Handler();
		Runnable runnable = new Runnable() {
			int i = 0;

			@Override
			public void run() {
				imshow.setImageResource(imageArray[i]);
				switch (imageArray[i]) {
				case R.drawable.conrad1:
					radGr.check(R.id.radbtn_image1);
					break;
				case R.drawable.conrad2:
					radGr.check(R.id.radbtn_image2);
					break;
				case R.drawable.conrad3:
					radGr.check(R.id.radbtn_image3);
					break;
				case R.drawable.conrad4:
					radGr.check(R.id.radbtn_image4);
					break;
				case R.drawable.conrad5:
					radGr.check(R.id.radbtn_image5);
					break;
				case R.drawable.conrad6:
					radGr.check(R.id.radbtn_image6);
					break;
				default:
					break;
				}
				i++;
				if (i > imageArray.length - 1) {
					i = 0;
				}
				handler.postDelayed(this, 2000); // for interval...
			}
		};
		handler.postDelayed(runnable, 2000); // for initial delay..

		personalBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SlideActivity.this,
						PersonalPage.class);
				startActivity(intent);
			}
		});

		infoBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SlideActivity.this, InfoPage.class);
				startActivity(intent);
				
			}
		});

		enterBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SlideActivity.this,
						EntertainPage.class);
				startActivity(intent);
			}
		});

		nearbyBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SlideActivity.this, NearbyPage.class);
				startActivity(intent);
			}
		});

		bookingBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SlideActivity.this,
						BookingPage.class);
				startActivity(intent);
			}
		});

		promotBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SlideActivity.this,
						PromotionPage.class);
				startActivity(intent);
			}
		});

		otploginBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SlideActivity.this, OtpLogin.class);
				startActivity(intent);
			}
		});
		
		
		settingBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initiatePopupWindow(settingBtn);
			}
		});
	}

	private PopupWindow pw;
	
	public void initiatePopupWindow(ImageButton button) {
		try {
	        LayoutInflater inflater = (LayoutInflater) SlideActivity.this
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	     
	        View layout = inflater.inflate(R.layout.popup_setting,null);
	        pw = new PopupWindow(layout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
	        pw.setBackgroundDrawable(new BitmapDrawable());
	        pw.setOutsideTouchable(true);
	        pw.showAsDropDown(button,-((button.getWidth())/2),0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		/*
		switch (checkedId) {
		case R.id.radbtn_image1:
			imshow.setImageResource(R.drawable.main_pic_01);
			break;
		case R.id.radbtn_image2:
			imshow.setImageResource(R.drawable.main_pic_02);
			break;
		case R.id.radbtn_image3:
			imshow.setImageResource(R.drawable.image1);
			break;
		case R.id.radbtn_image4:
			imshow.setImageResource(R.drawable.image3);
			break;
		case R.id.radbtn_image5:
			break;
	
		}*/
	}
	

		
}
