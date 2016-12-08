package com.itheima.rickoschina.main;


import android.support.v4.app.FragmentManager;

import com.itheima.rickoschina.R;
import com.itheima.rickoschina.base.BaseActivity;
import com.itheima.rickoschina.main.nav.NavFragment;

/**
 * Created by Rick Ge on 2016/12/4.
 */

public class MainActivity extends BaseActivity {
    private NavFragment mNavBar;

    @Override
    protected int getContentView() {
        return R.layout.activity_main_ui;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mNavBar = (NavFragment) fragmentManager.findFragmentById(R.id.fag_nav);
        mNavBar.setup(this, fragmentManager, R.id.main_container);
    }
}
