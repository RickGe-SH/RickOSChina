package com.itheima.rickoschina.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Rick Ge on 2016/12/4.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private boolean mIsDestroy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(initBundle(getIntent().getExtras())){
            setContentView(getContentView());
            ButterKnife.bind(this);
            initWindow();
            initWidget();
            initData();
        }
        else{
            finish();
        }
    }

    protected abstract int getContentView();

    protected boolean initBundle(Bundle bundle) {
        return true;
    }

    protected void initWindow() {
    }

    protected void initData() {
    }

    protected void initWidget() {
    }

    public boolean IsDestroy() {
        return mIsDestroy;
    }

    @Override
    protected void onDestroy() {
        mIsDestroy = true;
        super.onDestroy();
    }
}
