package com.itheima.rickoschina.api;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import com.itheima.rickoschina.AppConfig;
import com.itheima.rickoschina.base.BaseApplication;


import java.util.UUID;

class ApiClientHelper {

    /**
     * 获得请求的服务端数据的userAgent
     * 客户端唯一标识
     *
     * @return
     */
    static String getUserAgent(BaseApplication context) {
        // WebSettings.getDefaultUserAgent(appContext)

        int vCode = getPackageInfo(context).versionCode;
        String version = Build.VERSION.RELEASE; // "1.0" or "3.4b5"
        String osVer = version.length() > 0 ? version : "1.0";

        String model = Build.MODEL;
        String id = Build.ID; // "MASTER" or "M4-rc20"
        if (id.length() > 0) {
            model += " Build/" + id;
        }

        String format = "OSChina.NET/1.0 (oscapp; %s; Android %s; %s; %s)";
        String ua = String.format(format, vCode, osVer, model, getAppId(context));
        return ua;
    }

    public static String getDefaultUserAgent() {
        StringBuilder result = new StringBuilder(64);
        result.append("Dalvik/");
        result.append(System.getProperty("java.vm.version")); // such as 1.1.0
        result.append(" (Linux; U; Android ");

        String version = Build.VERSION.RELEASE; // "1.0" or "3.4b5"
        result.append(version.length() > 0 ? version : "1.0");

        // add the model for the release build
        if ("REL".equals(Build.VERSION.CODENAME)) {
            String model = Build.MODEL;
            if (model.length() > 0) {
                result.append("; ");
                result.append(model);
            }
        }
        String id = Build.ID; // "MASTER" or "M4-rc20"
        if (id.length() > 0) {
            result.append(" Build/");
            result.append(id);
        }
        result.append(")");
        return result.toString();
    }

    private static String getAppId(BaseApplication context) {
        String uniqueID = context.getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (TextUtils.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            context.setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    private static PackageInfo getPackageInfo(BaseApplication context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }
}
