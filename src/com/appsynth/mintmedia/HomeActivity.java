package com.appsynth.mintmedia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class HomeActivity extends Activity implements OnCheckedChangeListener {

	protected ImageView imshow_fadeout;
	protected ImageView imshow_fadein;
	private int currentImageIndex;
	public RadioGroup radGr;

	int[] imageArray = { R.drawable.conrad1, R.drawable.conrad2,
			R.drawable.conrad3, R.drawable.conrad4,R.drawable.conrad5,R.drawable.conrad6};

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


		radGr = (RadioGroup) findViewById(R.id.radioGr_slideImage);

		radGr.setOnCheckedChangeListener(HomeActivity.this);

		imshow_fadeout = (ImageView) findViewById(R.id.imviewSlide1);
		imshow_fadein = (ImageView) findViewById(R.id.imviewSlide2);


		initAnimations();
		animateImage();


		Runnable runnable = new Runnable() {
			int i = 0;

			@Override
			public void run() {

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

			}
		};


		personalBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this,
						PersonalPage.class);
				startActivity(intent);
			}
		});

		infoBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this, InfoPage.class);
				startActivity(intent);

			}
		});

		enterBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this,
						EntertainPage.class);
				startActivity(intent);
			}
		});

		nearbyBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this, NearbyPage.class);
				startActivity(intent);
			}
		});

		bookingBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this,
						BookingPage.class);
				startActivity(intent);
			}
		});

		promotBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this,
						PromotionPage.class);
				startActivity(intent);
			}
		});

		otploginBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this, OtpLogin.class);
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

	private void animateImage(){
		imshow_fadeout.setImageResource(imageArray[currentImageIndex]);
		imshow_fadein.setVisibility(ImageView.GONE);
		imshow_fadeout.startAnimation(fadeOut);

		switch (imageArray[currentImageIndex]) {
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
	}

	Animation fadeIn, fadeOut;

	private void initAnimations(){
		currentImageIndex = 0;
		fadeIn = new AlphaAnimation(0f, 1f); 
		fadeIn.setStartOffset(500);
	    fadeIn.setDuration(3000);

		fadeOut = new AlphaAnimation(1f, 0.1f); 
		fadeOut.setDuration(3500);
		fadeIn.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				imshow_fadein.setVisibility(ImageView.VISIBLE);

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {

			}
		});

		fadeOut.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				if(currentImageIndex+1 < imageArray.length){
					imshow_fadein.setImageResource(imageArray[currentImageIndex+1]);	
				}else{
					imshow_fadein.setImageResource(imageArray[0]);
				}
				imshow_fadein.startAnimation(fadeIn);

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				if(currentImageIndex+1 < imageArray.length){
					currentImageIndex++;	
				}else{
					currentImageIndex = 0;
				}

				animateImage();
			}
		});
	}

	private PopupWindow pw;

	public void initiatePopupWindow(ImageButton button) {
		try {
	        LayoutInflater inflater = (LayoutInflater) HomeActivity.this
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	        View layout = inflater.inflate(R.layout.setting,null);
	        pw = new PopupWindow(layout, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, true);
	        pw.setBackgroundDrawable(new BitmapDrawable());
	        pw.setOutsideTouchable(true);
	        pw.showAsDropDown(button, 0, 0);

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