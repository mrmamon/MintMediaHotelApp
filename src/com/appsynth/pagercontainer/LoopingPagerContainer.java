package com.appsynth.pagercontainer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class LoopingPagerContainer extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ViewPager mPager;
    boolean mNeedsRedraw = false;

    public LoopingPagerContainer(Context context) {
        super(context);
        init();
    }

    public LoopingPagerContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoopingPagerContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @SuppressLint("NewApi")
	private void init() {
        //Disable clipping of children so non-selected pages are visible
        setClipChildren(false);

        //Child clipping doesn't work with hardware acceleration in Android 3.x/4.x
        //You need to set this value here if using hardware acceleration in an
        // application targeted at these releases.
		if (Build.VERSION.SDK_INT >= 11)
		{
			setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
    }

    @Override
    protected void onFinishInflate() {
        try {
            mPager = (ViewPager) getChildAt(0);
            mPager.setOnPageChangeListener(this);
            mPager.setClipChildren(false);
        } catch (Exception e) {
            throw new IllegalStateException("The root child of PagerContainer must be a ViewPager");
        }
    }

    public ViewPager getViewPager() {
        return mPager;
    }

    private Point mCenter = new Point();
    private Point mInitialTouch = new Point();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenter.x = w / 2;
        mCenter.y = h / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //We capture any touches not already handled by the ViewPager
        // to implement scrolling from a touch outside the pager bounds.
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mInitialTouch.x = (int)ev.getX();
                mInitialTouch.y = (int)ev.getY();
            default:
                ev.offsetLocation(mCenter.x - mInitialTouch.x, mCenter.y - mInitialTouch.y);
                break;
        }

        return mPager.dispatchTouchEvent(ev);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Force the container to redraw on scrolling.
        //Without this the outer pages render initially and then stay static
        if (mNeedsRedraw) invalidate();
    }

    @Override
    public void onPageSelected(int position) { }

    @Override
    public void onPageScrollStateChanged(int state) {
    	final int DUMMY_NUM = 3;
        mNeedsRedraw = (state != ViewPager.SCROLL_STATE_IDLE);
		if(state == ViewPager.SCROLL_STATE_IDLE) {
			int currentItem = mPager.getCurrentItem();
			
	    	//Log.i("scroll", "currentItem: " + currentItem);
	    	int firstRealItem = DUMMY_NUM;
			int lastRealItem = mPager.getAdapter().getCount() - 1 - DUMMY_NUM;
	    	//Log.i("scroll", "lastRealItem: " + lastRealItem);
			if(currentItem < firstRealItem) {
				mPager.setCurrentItem(lastRealItem - (firstRealItem - currentItem - 1), false);
				//Log.i("scroll", "Scroll to " + (lastRealItem - (firstRealItem - currentItem - 1)));
				invalidate();
			} else if(currentItem > lastRealItem) {
				mPager.setCurrentItem(firstRealItem + (currentItem - lastRealItem - 1), false);
				//Log.i("scroll", "Scroll to " + (firstRealItem + (currentItem - lastRealItem - 1)));
				invalidate();
			}
		}
    }
}