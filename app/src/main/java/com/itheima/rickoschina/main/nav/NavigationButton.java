package com.itheima.rickoschina.main.nav;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.rickoschina.R;

/**
 * Created by Rick Ge on 2016/12/6.
 */

public class NavigationButton extends FrameLayout {
    private ImageView mIconView;
    private TextView mTitleView;
    private TextView mDotView;

    private Class<?> mClx;
    private Fragment mFragment = null;
    private String mTag;

    public NavigationButton(Context context) {
        super(context);
        init();
    }

    public NavigationButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NavigationButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_nav_item, this, true);
        mIconView = (ImageView) findViewById(R.id.nav_iv_icon);
        mTitleView = (TextView) findViewById(R.id.nav_tv_title);
        mDotView = (TextView) findViewById(R.id.nav_tv_dot);
    }

    public void init(@DrawableRes int resId, @StringRes int strId, Class<?> clx){
        mIconView.setImageResource(resId);
        mTitleView.setText(strId);
        mClx = clx;
        mTag = mClx.getName();
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        mIconView.setSelected(selected);
        mTitleView.setSelected(selected);
    }

    public void showRedDot(int count){
        mDotView.setVisibility(count > 0 ? VISIBLE : GONE);
        mDotView.setText(String.valueOf(count));
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }

    public Class<?> getClx() {
        return mClx;
    }

    public String getTag() {
        return mTag;
    }
}
