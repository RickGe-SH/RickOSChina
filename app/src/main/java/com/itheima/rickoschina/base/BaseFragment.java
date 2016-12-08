package com.itheima.rickoschina.base;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Rick Ge on 2016/12/6.
 */

public abstract class BaseFragment extends Fragment {
    protected View mRoot;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRoot = inflater.inflate(getLayoutId(), container, false);
        // Do something
        onBindViewBefore(mRoot);
        // Bind view
        ButterKnife.bind(this, mRoot);
        // Get savedInstanceState
        if(savedInstanceState != null){
            onRestartInstance(savedInstanceState);
        }

        initWidget(mRoot);
        initData();

        return mRoot;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected abstract int getLayoutId();

    protected void onRestartInstance(Bundle bundle) {
    }

    protected void onBindViewBefore(View root) {
    }


    protected void initData() {
    }

    protected void initWidget(View root) {
    }
}
