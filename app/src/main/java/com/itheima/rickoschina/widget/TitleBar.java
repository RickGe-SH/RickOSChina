package com.itheima.rickoschina.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.rickoschina.R;

/**
 * Created by Rick Ge on 2016/12/7.
 */

public class TitleBar extends FrameLayout {
    private TextView mTitle;
    private ImageView mIcon;

    public TitleBar(Context context) {
        super(context);
        init(null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        Context context = getContext();
        LayoutInflater.from(context).inflate(R.layout.lay_title_bar, this, true);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mIcon = (ImageView) findViewById(R.id.iv_icon);

        if(attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            String title = a.getString(R.styleable.TitleBar_aTitle);
            Drawable icon = a.getDrawable(R.styleable.TitleBar_aIcon);
            a.recycle();

            mTitle.setText(title);
            mIcon.setImageDrawable(icon);
        }
        else{
            mIcon.setVisibility(GONE);
        }

        setBackgroundColor(getResources().getColor(R.color.main_green));
    }

    public void setTitle(@StringRes int titleRes){
        if(titleRes > 0){
            mTitle.setText(titleRes);
        }
    }

    public void setIcon(@DrawableRes int iconRes){
        if(iconRes > 0){
            mIcon.setImageResource(iconRes);
            mIcon.setVisibility(VISIBLE);
        }
        else{
            mIcon.setVisibility(GONE);
        }
    }

    public void setIconOnClickListener(OnClickListener listener) {
        mIcon.setOnClickListener(listener);
    }
}
