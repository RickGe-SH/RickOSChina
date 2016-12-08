package com.itheima.rickoschina.api;

import android.content.Context;

import com.itheima.rickoschina.base.BaseApplication;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Locale;

import cz.msebera.android.httpclient.client.params.ClientPNames;


public class ApiHttpClient {

    public final static String HOST = "www.oschina.net";
    private static String API_URL = "https://www.oschina.net/%s";

    private static AsyncHttpClient CLIENT;

    private ApiHttpClient() {
    }


    public static void init(BaseApplication context) {
        CLIENT = null;
        AsyncHttpClient client = new AsyncHttpClient();
        ApiHttpClient.setHttpClient(client, context);

    }

    public static AsyncHttpClient getHttpClient() {
        return CLIENT;
    }

    public static void cancelAll(Context context) {
        CLIENT.cancelRequests(context, true);
    }

    public static void delete(String partUrl, AsyncHttpResponseHandler handler) {
        CLIENT.delete(getAbsoluteApiUrl(partUrl), handler);
    }

    public static void get(String partUrl, AsyncHttpResponseHandler handler) {
        CLIENT.get(getAbsoluteApiUrl(partUrl), handler);
    }

    public static void get(String partUrl, RequestParams params,
                           AsyncHttpResponseHandler handler) {
        CLIENT.get(getAbsoluteApiUrl(partUrl), params, handler);

    }

    public static String getAbsoluteApiUrl(String partUrl) {
        String url = partUrl;
        if (!partUrl.startsWith("http:") && !partUrl.startsWith("https:")) {
            url = String.format(API_URL, partUrl);
        }
        return url;
    }

    public static String getApiUrl() {
        return API_URL;
    }

    public static void getDirect(String url, AsyncHttpResponseHandler handler) {
        CLIENT.get(url, handler);
    }


    public static void post(String partUrl, AsyncHttpResponseHandler handler) {
        CLIENT.post(getAbsoluteApiUrl(partUrl), handler);
    }

    public static void post(String partUrl, RequestParams params,
                            AsyncHttpResponseHandler handler) {
        CLIENT.post(getAbsoluteApiUrl(partUrl), params, handler);
    }

    public static void put(String partUrl, AsyncHttpResponseHandler handler) {
        CLIENT.put(getAbsoluteApiUrl(partUrl), handler);
    }

    public static void put(String partUrl, RequestParams params,
                           AsyncHttpResponseHandler handler) {
        CLIENT.put(getAbsoluteApiUrl(partUrl), params, handler);
    }

    public static void setHttpClient(AsyncHttpClient c, BaseApplication context) {
        c.addHeader("Accept-Language", Locale.getDefault().toString());
        c.addHeader("Host", HOST);
        c.addHeader("Connection", "Keep-Alive");
        c.getHttpClient().getParams()
                .setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        // Set AppToken
        String appToken = ""; //Verifier.getPrivateToken(context);
        c.addHeader("AppToken", appToken);
        // setUserAgent
        c.setUserAgent(ApiClientHelper.getUserAgent(context));
        CLIENT = c;
    }

    private static void setCookieHeader(String cookie) {
        CLIENT.addHeader("Cookie", cookie);
    }

    public static void destroy(BaseApplication context) {
        CLIENT = null;
        init(context);
    }
}
