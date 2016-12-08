package com.itheima.rickoschina.main.tabs;

import android.view.View;
import android.widget.Toast;

import com.itheima.rickoschina.R;
import com.itheima.rickoschina.base.BaseTitleFragment;

/**
 * Created by Rick Ge on 2016/12/8.
 */

public class TweetViewPagerFragment extends BaseTitleFragment {
    @Override
    protected int getTitleRes() {
        return R.string.main_tab_name_tweet;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_tweet;
    }

    @Override
    protected int getIconRes() {
        return R.mipmap.btn_search_normal;
    }

    @Override
    protected View.OnClickListener getIconOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Search", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
