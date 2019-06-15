package com.payu.custombrowser;

import android.app.AlertDialog.Builder;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.webkit.WebView;
import com.payu.custombrowser.bean.CustomBrowserResultData;
import com.payu.magicretry.MagicRetryFragment;

public class PayUCustomBrowserCallback {
    private String a;
    private String b;

    public void getNavigationDrawerObject(DrawerLayout drawerLayout) {
    }

    public void initializeMagicRetry(Bank bank, WebView webView, MagicRetryFragment magicRetryFragment) {
    }

    public void isPaymentOptionAvailable(CustomBrowserResultData customBrowserResultData) {
    }

    public void onBackApprove() {
    }

    public void onBackButton(Builder builder) {
    }

    public void onBackDismiss() {
    }

    public void onCBErrorReceived(int i, String str) {
    }

    public void onPaymentFailure(String str, String str2) {
    }

    public void onPaymentSuccess(String str, String str2) {
    }

    public void onPaymentTerminate() {
    }

    public void setCBProperties(WebView webView, Bank bank) {
    }

    public void setCBProperties(@NonNull String str, @NonNull String str2) {
        setPostData(str2);
        setPostURL(str);
    }

    public String getPostData() {
        return this.a;
    }

    public void setPostData(String str) {
        this.a = str;
    }

    public String getPostURL() {
        return this.b;
    }

    public void setPostURL(String str) {
        this.b = str;
    }
}
