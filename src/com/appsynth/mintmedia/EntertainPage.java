package com.appsynth.mintmedia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.appsynth.pagercontainer.LoopingPagerContainer;
import com.appsynth.pagercontainer.PagerContainer;

public class EntertainPage extends Activity {

	FrameLayout outerLayout;
	PagerContainer topContainer;
	LoopingPagerContainer centerContainer;
	ImageView movieSelectView;
	boolean touching;

	private final int DUMMY_NUM = 3;
	private int imgArra[] = {0, 1, 2};
	private int midImgArra[] = {R.drawable.hungergame_l, R.drawable.lifeofpi_l, R.drawable.cloudatlas_l, R.drawable.stoker_l};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entertain);
		Typeface trajanProFont = Typeface.createFromAsset(getAssets(), "fonts/TrajanPro-Bold.otf");
		outerLayout = (FrameLayout)findViewById(R.id.content);
		movieSelectView = (ImageView)findViewById(R.id.movie_select_view);
		touching = false;
		
		topContainer = (PagerContainer)findViewById(R.id.pager_container);
		ViewPager pager = topContainer.getViewPager();
		ViewPagerAdapter adapter = new ViewPagerAdapter(this, imgArra, trajanProFont);
		pager.setAdapter(adapter);
		pager.setOffscreenPageLimit(adapter.getCount());
		pager.setPageMargin(30);
		pager.setClipChildren(false);
		pager.setCurrentItem(1);
		
		centerContainer = (LoopingPagerContainer)findViewById(R.id.midContainer);
		ViewPager contentPager = centerContainer.getViewPager();
		ViewPagerAdapter midAdapter = new ViewPagerAdapter(this, makeDummy(midImgArra), true);
		contentPager.setAdapter(midAdapter);
		contentPager.setOffscreenPageLimit(adapter.getCount());
		contentPager.setPageMargin(65);
		contentPager.setClipChildren(false);
		contentPager.setCurrentItem(DUMMY_NUM);
		contentPager.setPageTransformer(true, new DepthPageTransformer());
		contentPager.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_UP) {
					touching = false;
					Animation blank = new TranslateAnimation(0, 0, 0, 0);
					blank.setDuration(1500);
					Animation fadeIn = new AlphaAnimation(0, 1);
					fadeIn.setInterpolator(new DecelerateInterpolator());
					fadeIn.setDuration(1000);
					AnimationSet animation = new AnimationSet(false);
					animation.addAnimation(blank);
					animation.addAnimation(fadeIn);
					animation.setAnimationListener(new AnimationListener() {
						
						@Override
						public void onAnimationStart(Animation animation) {
							
						}
						
						@Override
						public void onAnimationRepeat(Animation animation) {

						}
						
						@Override
						public void onAnimationEnd(Animation animation) {
							movieSelectView.setVisibility(View.VISIBLE);
						}
					});
					movieSelectView.setAnimation(animation);
				} else if(event.getAction() == MotionEvent.ACTION_DOWN) {
					movieSelectView.clearAnimation();
					movieSelectView.setVisibility(View.INVISIBLE);
				} else if(event.getAction() == MotionEvent.ACTION_MOVE) {
					movieSelectView.clearAnimation();
					movieSelectView.setVisibility(View.INVISIBLE);
				}
				return false;
			}
		});
		
		Button newReleaseButton = (Button)findViewById(R.id.newReleasesButton);
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
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.screen_slide, menu);
		return true;
	}
	
	public class DepthPageTransformer implements ViewPager.PageTransformer {
		private static final float MIN_SCALE = 0.75f;
		@SuppressLint("NewApi")
		@Override
		public void transformPage(View view, float position) {
			float pageTranslateY = (view.getHeight() - (view.getHeight() * MIN_SCALE)) / 4;

			if(position < -1) {
				view.setAlpha(0.5f);
				view.setScaleX(MIN_SCALE);
				view.setScaleY(MIN_SCALE);
			} else if(position < 0) { // [-1, 0)
				view.setAlpha(1 + position / 2);
				//view.setTranslationX(pageWidth * -position);
				
				float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
				view.setScaleX(scaleFactor);
				view.setScaleY(scaleFactor);
				//view.setTranslationY(position * pageTranslateY);
			} else if(position == 0) {
				view.setAlpha(1);
				//view.setTranslationX(0);
				view.setScaleX(1);
				view.setScaleY(1);
				//view.setTranslationY(0);
				
			} else if(position <= 1) { // (0, 1]
				view.setAlpha(1 - position / 2);
				//view.setTranslationX(pageWidth * -position);
				
				float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
				view.setScaleX(scaleFactor);
				view.setScaleY(scaleFactor);
				//view.setTranslationY(-position * pageTranslateY);
			} else {
				view.setAlpha(0.5f);
				view.setScaleX(MIN_SCALE);
				view.setScaleY(MIN_SCALE);
			}
		}
	}
	
	private int[] makeDummy(int[] inputArray) {
		
		int count = inputArray.length;
		if(DUMMY_NUM > count) {
			return inputArray;
		}
		int[] outputArray = new int[count + DUMMY_NUM * 2];
		for(int i = 0; i < count + DUMMY_NUM * 2; i++) {
			if(i < DUMMY_NUM) {
				Log.i("123", "" + (count + i - DUMMY_NUM));
				outputArray[i] = inputArray[count + i - DUMMY_NUM];
			} else if(i < count + DUMMY_NUM) {
				Log.i("123", "" + (i - DUMMY_NUM));
				outputArray[i] = inputArray[i - DUMMY_NUM];
			} else {
				Log.i("123", "" + (i - count - DUMMY_NUM));
				outputArray[i] = inputArray[i - count - DUMMY_NUM];
			}
		}
		return outputArray;
	}
	
	public void backToHome(View view) {
		finish();
	}
}
