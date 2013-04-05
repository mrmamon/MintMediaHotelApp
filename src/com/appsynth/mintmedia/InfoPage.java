package com.appsynth.mintmedia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.appsynth.pagercontainer.PagerContainer;

public class InfoPage extends Activity {

	PagerContainer topContainer;
	private int imgArra[] = {0, 1, 2};
	int temp1;
	ImageButton BackButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.information);
		Typeface trajanProFont = Typeface.createFromAsset(getAssets(), "fonts/TrajanPro-Regular.otf");
		
		topContainer = (PagerContainer)findViewById(R.id.pager_container);
		ViewPagerAdapter2 adapter = new ViewPagerAdapter2(this, imgArra, trajanProFont);
		ViewPager pager = topContainer.getViewPager();
		pager.setAdapter(adapter);
		pager.setOffscreenPageLimit(adapter.getCount());
		pager.setCurrentItem(2);
		pager.setPageTransformer(true, new DepthPageTransformer());
		pager.setCurrentItem(0);
		
		
		
		final Button newReleaseButton = (Button)findViewById(R.id.newReleasesButton);
		newReleaseButton.setTypeface(trajanProFont);
		newReleaseButton.setTextSize(26);
		newReleaseButton.setTextColor(Color.argb(255, 169, 154, 111));
		Button popularButton = (Button)findViewById(R.id.popularButton);
		popularButton.setTypeface(trajanProFont);
		popularButton.setTextSize(26);
		popularButton.setTextColor(Color.argb(255, 169, 154, 111));
		Button purchasedButton = (Button)findViewById(R.id.purchasedButton);
		purchasedButton.setTypeface(trajanProFont);
		purchasedButton.setTextSize(26);
		purchasedButton.setTextColor(Color.argb(255, 169, 154, 111));
		Button genreButton = (Button)findViewById(R.id.genreButton);
		genreButton.setTypeface(trajanProFont);
		genreButton.setTextSize(26);
		genreButton.setTextColor(Color.argb(255, 169, 154, 111));
		BackButton = (ImageButton)findViewById(R.id.BackButton);
		BackButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		temp1=newReleaseButton.getWidth();
		newReleaseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initiateRestautantPopupWindow(newReleaseButton);
				newReleaseButton.setBackgroundResource(R.drawable.bg_select);
			}
		});
		
		final ImageButton SettingButton = (ImageButton)findViewById(R.id.SettingButton);
		SettingButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initiateSettingPopupWindow(SettingButton);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.screen_slide, menu);
		return true;
	}
	
	public class DepthPageTransformer implements ViewPager.PageTransformer {
		@SuppressLint("NewApi")
		@Override
		public void transformPage(View view, float position) {
			// TODO Auto-generated method stub
			
			if(position < -1) {
				view.setAlpha(0.5f);
			} else if(position <= -1) {
				view.setTranslationX(+100);
				view.setAlpha(0.5f);
			} else if(position >-1&&position<1) {
				view.setAlpha(1);
				view.setTranslationX((position*100)*(-1));
				view.setScaleX(1);
				view.setScaleY(1);
			} else if(position >= 1) {
				view.setTranslationX(-100);
				view.setAlpha(0.5f);
			} else {
				view.setAlpha(0.5f);
			}
		}
	}


	private PopupWindow pw;
	
	private void initiateRestautantPopupWindow(Button button) {
		try {
	        LayoutInflater inflater = (LayoutInflater) InfoPage.this
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        View layout = inflater.inflate(R.layout.popup_restautant,null);
	        pw = new PopupWindow(layout, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, true);
	        pw.setBackgroundDrawable(new BitmapDrawable());
	        pw.setOutsideTouchable(true);
	        pw.setOnDismissListener(new OnDismissListener() {
				@Override
				public void onDismiss() {
					// TODO Auto-generated method stub
					final Button newReleaseButton = (Button)findViewById(R.id.newReleasesButton);
					newReleaseButton.setBackgroundResource(R.drawable.bg_non_select);
				}
			});
	        Button subbutt1 = (Button)findViewById(R.id.BayViewButton);
	        pw.showAsDropDown(button, 0, 5);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initiateSettingPopupWindow(ImageButton button) {
		PopupWindow pw1;
		try {
	        LayoutInflater inflater = (LayoutInflater) InfoPage.this
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        View layout = inflater.inflate(R.layout.popup_setting,null);
	        pw1 = new PopupWindow(layout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
	        pw1.setBackgroundDrawable(new BitmapDrawable());
	        pw1.setOutsideTouchable(true);
	        pw1.setOnDismissListener(new OnDismissListener() {
				@Override
				public void onDismiss() {
					// TODO Auto-generated method stub
				}
			});
	        pw1.showAsDropDown(button,0,-55);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
