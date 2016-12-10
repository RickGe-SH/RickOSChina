package com.itheima.rickoschina.tweet.fragments;

import com.itheima.rickoschina.R;
import com.itheima.rickoschina.base.BaseFragment;

/**
 * Created by 10105 on 2016/12/10.
 */

public class TweetFragment extends BaseFragment{
    public static final int CATEGORY_TYPE = 1; //请求最新或者最热
    public static final int CATEGORY_USER = 2; //请求用户
    public static final int CATEGORY_FRIEND = 3;

    public static final int TWEET_TYPE_NEW = 1;
    public static final int TWEET_TYPE_HOT = 2;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_recycler_view;
    }
}
