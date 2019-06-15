package com.payu.custombrowser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.payu.custombrowser.a.a;
import com.payu.custombrowser.bean.CustomBrowserConfig;
import com.payu.custombrowser.bean.b;
import com.payu.custombrowser.upiintent.Payment;
import com.payu.custombrowser.upiintent.PaymentResponseActivity;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.CBUtil;
import com.payu.custombrowser.util.PaymentOption;
import com.payu.custombrowser.util.f;

public class CustomBrowser {
    public void checkForPaymentAvailability(Activity activity, @NonNull PaymentOption paymentOption, @NonNull PayUCustomBrowserCallback payUCustomBrowserCallback, String str, String str2, String str3) {
        b.SINGLETON.setPayuCustomBrowserCallback(payUCustomBrowserCallback);
        switch (paymentOption) {
            case SAMSUNGPAY:
                if (CBUtil.isSPModuleAvailable()) {
                    e eVar = new e();
                    eVar.a(activity.getApplicationContext(), str, str2, str3);
                    b.SINGLETON.setSamsungPayWrapper(eVar);
                    return;
                }
                payUCustomBrowserCallback.onCBErrorReceived(CBConstant.DEVICE_NOT_SUPPORTED_OR_MODULE_NOT_IMPORTED, CBConstant.DEVICE_NOT_SUPPORTED_OR_MODULE_NOT_IMPORTED_MSG);
                return;
            case CC:
            case NB:
            case DC:
                f fVar = new f(activity, payUCustomBrowserCallback, str2, str, str3);
                return;
            default:
                return;
        }
    }

    public void addCustomBrowser(Activity activity, @NonNull CustomBrowserConfig customBrowserConfig, @NonNull PayUCustomBrowserCallback payUCustomBrowserCallback) {
        a a = a.a(activity.getApplicationContext(), "local_cache_analytics");
        b.SINGLETON.setPayuCustomBrowserCallback(payUCustomBrowserCallback);
        String str = (String) new CBUtil().getDataFromPostData(customBrowserConfig.getPayuPostData()).get("bankcode");
        if (str == null || !str.equalsIgnoreCase(PaymentOption.SAMSUNGPAY.getPaymentName())) {
            Intent intent;
            if (VERSION.SDK_INT < 16) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(VERSION.SDK_INT);
                stringBuilder.append("");
                a.a(CBUtil.getLogMessage(activity.getApplicationContext(), "os_not_supported", stringBuilder.toString(), null, customBrowserConfig.getMerchantKey(), customBrowserConfig.getTransactionID(), null));
                payUCustomBrowserCallback.onCBErrorReceived(101, CBConstant.OS_NOT_SUPPORTED);
            } else if (VERSION.SDK_INT >= 19 && (VERSION.SDK_INT != 19 || customBrowserConfig.getGmsProviderUpdatedStatus() != -1)) {
                if (customBrowserConfig.getPayuPostData() != null && customBrowserConfig.getEnableSurePay() > 0 && (customBrowserConfig.getPostURL().contentEquals("https://secure.payu.in/_payment") || customBrowserConfig.getPostURL().contentEquals("https://mobiletest.payu.in/_payment") || customBrowserConfig.getPostURL().contentEquals(CBConstant.MOBILE_TEST_PAYMENT_URL_SEAMLESS) || customBrowserConfig.getPostURL().contentEquals(CBConstant.PRODUCTION_PAYMENT_URL_SEAMLESS))) {
                    if (customBrowserConfig.getPayuPostData().trim().endsWith("&")) {
                        customBrowserConfig.setPayuPostData(customBrowserConfig.getPayuPostData().substring(0, customBrowserConfig.getPayuPostData().length() - 1));
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(customBrowserConfig.getPayuPostData());
                    stringBuilder2.append("&snooze=");
                    stringBuilder2.append(customBrowserConfig.getEnableSurePay());
                    customBrowserConfig.setPayuPostData(stringBuilder2.toString());
                }
                com.payu.custombrowser.upiintent.b bVar = new com.payu.custombrowser.upiintent.b();
                Payment a2 = bVar.a(customBrowserConfig.getPayuPostData());
                if (a2 != null && bVar.a((Context) activity, a2)) {
                    intent = new Intent(activity, PaymentResponseActivity.class);
                    intent.putExtra(CBConstant.POST_DATA, customBrowserConfig.getPayuPostData());
                    activity.startActivity(intent);
                } else if (a2 == null || a2.isWebFlowSupported()) {
                    CBActivity.a = customBrowserConfig.getCbMenuAdapter();
                    CBActivity.e = customBrowserConfig.getToolBarView();
                    intent = new Intent(activity, CBActivity.class);
                    intent.putExtra(CBConstant.CB_CONFIG, customBrowserConfig);
                    if (!(customBrowserConfig.getReviewOrderDefaultViewData() == null || customBrowserConfig.getReviewOrderDefaultViewData().getReviewOrderDatas() == null)) {
                        intent.putExtra(CBConstant.ORDER_DETAILS, customBrowserConfig.getReviewOrderDefaultViewData().getReviewOrderDatas());
                    }
                    activity.startActivity(intent);
                } else {
                    payUCustomBrowserCallback.onCBErrorReceived(1001, "DEVICE_NOT_SUPPORTED");
                }
            } else if (CBUtil.isCustomTabSupported(activity.getApplicationContext())) {
                a.a(CBUtil.getLogMessage(activity.getApplicationContext(), "custom_tabs", "custom_tabs_launched", null, customBrowserConfig.getMerchantKey(), customBrowserConfig.getTransactionID(), null));
                intent = new Intent(activity, PrePaymentsActivity.class);
                intent.putExtra("url", customBrowserConfig.getPostURL());
                intent.putExtra("html", customBrowserConfig.getHtmlData());
                intent.putExtra("postdata", customBrowserConfig.getPayuPostData());
                intent.putExtra(CBConstant.S2S_RETRY_URL, customBrowserConfig.getSurepayS2Surl());
                intent.putExtra(CBConstant.TXNID, customBrowserConfig.getTransactionID());
                intent.putExtra(CBConstant.KEY, customBrowserConfig.getMerchantKey());
                activity.startActivity(intent);
            } else {
                a.a(CBUtil.getLogMessage(activity.getApplicationContext(), "custom_tabs", "custom_tabs_launch_failed", null, customBrowserConfig.getMerchantKey(), customBrowserConfig.getTransactionID(), null));
                payUCustomBrowserCallback.onCBErrorReceived(103, CBConstant.CHROME_NOT_PRESENT);
            }
        } else if (b.SINGLETON.getSamsungPayWrapper() != null) {
            a.a(CBUtil.getLogMessage(activity.getApplicationContext(), "samsung_pay", "samsung_pay_launched", null, customBrowserConfig.getMerchantKey(), customBrowserConfig.getTransactionID(), null));
            b.SINGLETON.getSamsungPayWrapper().a(activity, customBrowserConfig);
        } else {
            a.a(CBUtil.getLogMessage(activity.getApplicationContext(), "samsung_pay", "samsung_pay_launch_failed", null, customBrowserConfig.getMerchantKey(), customBrowserConfig.getTransactionID(), null));
            payUCustomBrowserCallback.onCBErrorReceived(1021, CBConstant.CHECK_PAYMENT_NOT_CALLED_MSG);
        }
    }
}
