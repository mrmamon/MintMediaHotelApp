package com.appsynth.mintmedia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewPagerAdapter extends PagerAdapter {

	Activity activity;
	int imageArray[];
	boolean isImage;
	Typeface font;
	
	public ViewPagerAdapter(Activity act, int[] imgArra, Typeface font) {
		imageArray = imgArra;
		activity = act;
		isImage = false;
		this.font = font;
	}
	
	public ViewPagerAdapter(Activity act, int[] imgArra, boolean isImage) {
		imageArray = imgArra;
		activity = act;
		this.isImage = isImage;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageArray.length;
	}

	@SuppressLint("NewApi")
	@Override
	public Object instantiateItem(View collection, int position) {
		if(isImage) { 
			LinearLayout lLayout = new LinearLayout(activity);
			lLayout.setOrientation(LinearLayout.VERTICAL);
			lLayout.setGravity(Gravity.TOP); 
			LinearLayout dummyLayout = new LinearLayout(activity);
			dummyLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 0, 3));
			ImageView view = new ImageView(activity);
			view.setBackgroundResource(imageArray[position]);
			//view.setLayoutParams(new LinearLayout.LayoutParams(310, 460, 3)); 
			view.setLayoutParams(new LinearLayout.LayoutParams(310, 0, 3)); 
			ImageView mirrorView = new ImageView(activity);
			mirrorView.setBackgroundResource(imageArray[position]);
			//mirrorView.setLayoutParams(new LinearLayout.LayoutParams(310, 460, 3)); 
			mirrorView.setLayoutParams(new LinearLayout.LayoutParams(310, 0, 3)); 
			if(position != 0) { 
				lLayout.setScaleX(0.75f);
				lLayout.setScaleY(0.75f);
				lLayout.setAlpha(0.5f); /*
				view.setScaleX(0.75f);
				view.setScaleY(0.75f);
				view.setAlpha(0.5f); */
			} 
			mirrorView.setScaleY(-1f);
			mirrorView.setAlpha(0.1f);
			lLayout.addView(dummyLayout);
			lLayout.addView(view);
			lLayout.addView(mirrorView);
			((ViewPager) collection).addView(lLayout, 0);
			return lLayout; /*
			((ViewPager) collection).addView(view, 0);
			return view; */
		} else {
			TextView view = new TextView(activity);
			//Button view = new Button(activity);
			switch (position) {
			case 0:
				view.setText("Game");
				break;
			case 1:
				view.setText("Movie");
				break;
			case 2:
				view.setText("Music");
				break;
	
			default:
				break;
			}
			view.setTypeface(font);
			view.setTextSize(36);
			view.setGravity(Gravity.CENTER);
			view.setBackgroundColor(Color.TRANSPARENT);
			view.setTextColor(Color.argb(255, 169, 154, 111));
			view.setShadowLayer(3f, 0f, 0f, Color.parseColor("#BF000000"));
			view.setLayoutParams(new Gallery.LayoutParams(190, 50));
			((ViewPager) collection).addView(view, 0);
			return view;
		}
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == ((View)arg1);
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager)arg0).removeView((View)arg2);
	}
	
	@Override
	public float getPageWidth(int position) {
		// TODO Auto-generated method stub
		return super.getPageWidth(position);
	}
	
	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.setPrimaryItem(container, position, object);
		View view = (View)object;
		view.setScaleX(1);
		view.setScaleY(1);
		view.setAlpha(1f);
	}
}
