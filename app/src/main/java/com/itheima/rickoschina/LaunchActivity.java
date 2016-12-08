package com.itheima.rickoschina;

import android.content.Intent;
import com.itheima.rickoschina.base.BaseActivity;
import com.itheima.rickoschina.main.MainActivity;

public class LaunchActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.app_start;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        findViewById(R.id.app_start_view).postDelayed(new Runnable() {
            @Override
            public void run() {
                redirectTo();
            }
        }, 800);
    }

    private void redirectTo() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
