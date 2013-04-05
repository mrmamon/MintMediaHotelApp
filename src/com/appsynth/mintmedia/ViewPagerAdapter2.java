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
import android.widget.Button;
import android.widget.ImageView;

public class ViewPagerAdapter2 extends PagerAdapter {

	Activity activity;
	int imageArray[];
	boolean isImage;
	Typeface font;
	
	public ViewPagerAdapter2(Activity act, int[] imgArra, Typeface font) {
		imageArray = imgArra;
		activity = act;
		isImage = false;
		this.font = font;
	}
	
	public ViewPagerAdapter2(Activity act, int[] imgArra, boolean isImage) {
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
			ImageView view = new ImageView(activity);
			view.setBackgroundResource(imageArray[position]);
			if(position != 0) {
				view.setScaleX(0.75f);
				view.setScaleY(0.75f);
				view.setAlpha(0.5f);
			}
			((ViewPager) collection).addView(view, 0);
			return view;
		} else {
			Button view = new Button(activity);
			switch (position) {
			case 0:
				view.setText("Services");
				break;
			case 1:
				view.setText("Accommodations");
				break;
			case 2:
				view.setText("Facilities");
				break;
			case 3:
				view.setText("Dining");
				break;
	
			default:
				break;
			}
			view.setTypeface(font);
			view.setTextSize(36);
			view.setGravity(Gravity.CENTER);
			view.setBackgroundColor(Color.TRANSPARENT);
			view.setTextColor(Color.argb(255, 169, 154, 111));
			view.setLines(1);
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
	public void setPrimaryItem(ViewGroup container, int position, Object object){
		super.setPrimaryItem(container, position, object);
		  ViewPager pager = (ViewPager) container;
		  int width = 0;
		  switch(position){
		  case 0:
			  width = 195;
			  break;
		  case 1:
			  width = 380;
			  break;
		  case 2:
			  width = 200;
			  break;
		  case 3:
			  width= 190;
			  break;
		  }
		  
		  //int width = 100;
		  
		  //pager.setLayoutParams(new FrameLayout.LayoutParams(width, LayoutParams.FILL_PARENT,Gravity.CENTER));
		  //pager.setPageMargin(0);
	}
	@Override
	public float getPageWidth(int position) {
		// TODO Auto-generated method stub
		return super.getPageWidth(position);
	}
}
