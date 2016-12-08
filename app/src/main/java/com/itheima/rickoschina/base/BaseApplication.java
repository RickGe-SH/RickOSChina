package com.itheima.rickoschina.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.itheima.rickoschina.AppConfig;
import com.itheima.rickoschina.api.ApiHttpClient;

import java.util.Properties;

/**
 * Created by Rick Ge on 2016/12/4.
 */
public class BaseApplication extends Application {
    private static Context mContext;
    private static Resources mResources;
    public static final int PAGE_SIZE = 20; // 默认分页大小

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mResources = mContext.getResources();

        // 初始化网络请求
        ApiHttpClient.init(this);
        // Bitmap缓存地址
        //HttpConfig.CACHEPATH = "OSChina/ImageCache";
    }

    public static synchronized Context context(){
        return mContext;
    }

    public static Resources resources(){
        return mResources;
    }

    public String getProperty(String key) {
        return AppConfig.getAppConfig(this).get(key);
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }
}
