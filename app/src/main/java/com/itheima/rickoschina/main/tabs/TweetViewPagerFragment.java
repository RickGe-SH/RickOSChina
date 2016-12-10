package com.itheima.rickoschina.main.tabs;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.itheima.rickoschina.R;
import com.itheima.rickoschina.base.BaseTitleFragment;
import com.itheima.rickoschina.base.BaseViewPagerFragment;
import com.itheima.rickoschina.tweet.fragments.TweetFragment;

/**
 * Created by Rick Ge on 2016/12/8.
 */

public class TweetViewPagerFragment extends BaseViewPagerFragment {
    @Override
    protected int getTitleRes() {
        return R.string.main_tab_name_tweet;
    }

    @Override
    protected PagerInfo[] getPagers() {
        PagerInfo[] infoList = new PagerInfo[3];

        infoList[2] = new PagerInfo("我的动弹", TweetFragment.class,
                getBundle(TweetFragment.CATEGORY_USER, 0));
        infoList[1] = new PagerInfo("热门动弹", TweetFragment.class,
                getBundle(TweetFragment.CATEGORY_TYPE, TweetFragment.TWEET_TYPE_HOT));
        infoList[0] = new PagerInfo("最新动弹", TweetFragment.class,
                getBundle(TweetFragment.CATEGORY_TYPE, TweetFragment.TWEET_TYPE_NEW));

        return infoList;
    }

    /**
     * @param requestCategory 请求类型，1为普通动弹，2用户动弹
     * @param tweetType       1最新，2最热
     * @return Bundle
     */
    private Bundle getBundle(int requestCategory, int tweetType) {
        Bundle bundle = new Bundle();
        bundle.putInt("requestCategory", requestCategory);
        bundle.putInt("tweetType", tweetType);
        return bundle;
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
