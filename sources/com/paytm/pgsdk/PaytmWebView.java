package com.paytm.pgsdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Iterator;
import org.json.JSONObject;

@TargetApi(21)
public class PaytmWebView extends WebView {
    private static final String CALLBACK = "/CAS/Response";
    private static final String HTML_OUT = "HTMLOUT";
    private static final String JAVA_SCRIPT = "javascript:window.HTMLOUT.processResponse(document.getElementById('response').value);";
    private static final String SUCCESS = "01";
    private static final String Y = "Y";
    private final PaytmPGActivity mContext;
    private volatile boolean mbMerchantCallbackURLLoaded;

    private class PaytmJavaScriptInterface {
        private PaytmJavaScriptInterface() {
        }

        /* synthetic */ PaytmJavaScriptInterface(PaytmWebView paytmWebView, AnonymousClass1 anonymousClass1) {
            this();
        }

        @JavascriptInterface
        public synchronized void processResponse(String str) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Merchant Response is ");
                stringBuilder.append(str);
                PaytmUtility.debugLog(stringBuilder.toString());
                returnResponse(PaytmWebView.this.parseResponse(str));
            } catch (Exception e) {
                PaytmUtility.printStackTrace(e);
            }
            return;
        }

        private synchronized void returnResponse(final Bundle bundle) {
            try {
                ((Activity) PaytmWebView.this.getContext()).runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            ((Activity) PaytmWebView.this.getContext()).finish();
                            PaytmPGService.getService().mPaymentTransactionCallback.onTransactionResponse(bundle);
                        } catch (Exception e) {
                            PaytmUtility.printStackTrace(e);
                        }
                    }
                });
            } catch (Exception e) {
                PaytmUtility.printStackTrace(e);
            }
            return;
        }
    }

    private class PaytmWebViewClient extends WebViewClient {
        private PaytmWebViewClient() {
        }

        /* synthetic */ PaytmWebViewClient(PaytmWebView paytmWebView, AnonymousClass1 anonymousClass1) {
            this();
        }

        public synchronized void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Page started loading ");
            stringBuilder.append(str);
            PaytmUtility.debugLog(stringBuilder.toString());
            PaytmWebView.this.startProgressDialog();
        }

        public synchronized void onPageFinished(WebView webView, String str) {
            Intent intent;
            PaytmPGActivity access$500;
            try {
                super.onPageFinished(webView, str);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Page finished loading ");
                stringBuilder.append(str);
                PaytmUtility.debugLog(stringBuilder.toString());
                PaytmWebView.this.stopProgressDialog();
                if (str.equalsIgnoreCase(((String) PaytmPGService.getService().mOrder.getRequestParamMap().get("CALLBACK_URL")).toString())) {
                    PaytmUtility.debugLog("Merchant specific Callback Url is finished loading. Extract data now. ");
                    PaytmWebView.this.mbMerchantCallbackURLLoaded = true;
                    PaytmWebView.this.loadUrl(PaytmWebView.JAVA_SCRIPT);
                } else if (str.endsWith(PaytmWebView.CALLBACK)) {
                    PaytmUtility.debugLog("CAS Callback Url is finished loading. Extract data now. ");
                    PaytmWebView.this.loadUrl(PaytmWebView.JAVA_SCRIPT);
                }
                if (PaytmPGService.getService().mOrder.getRequestParamMap().get("postnotificationurl") != null) {
                    intent = new Intent(PaytmWebView.this.mContext, IntentServicePostNotification.class);
                    intent.putExtra("url", (String) PaytmPGService.getService().mOrder.getRequestParamMap().get("postnotificationurl"));
                    access$500 = PaytmWebView.this.mContext;
                    access$500.startService(intent);
                }
            } catch (Exception e) {
                try {
                    PaytmUtility.printStackTrace(e);
                    if (PaytmPGService.getService().mOrder.getRequestParamMap().get("postnotificationurl") != null) {
                        intent = new Intent(PaytmWebView.this.mContext, IntentServicePostNotification.class);
                        intent.putExtra("url", (String) PaytmPGService.getService().mOrder.getRequestParamMap().get("postnotificationurl"));
                        access$500 = PaytmWebView.this.mContext;
                    }
                } catch (Throwable th) {
                    if (PaytmPGService.getService().mOrder.getRequestParamMap().get("postnotificationurl") != null) {
                        Intent intent2 = new Intent(PaytmWebView.this.mContext, IntentServicePostNotification.class);
                        intent2.putExtra("url", (String) PaytmPGService.getService().mOrder.getRequestParamMap().get("postnotificationurl"));
                        PaytmWebView.this.mContext.startService(intent2);
                    }
                }
            }
        }

        public synchronized void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error occured while loading url ");
            stringBuilder.append(str2);
            PaytmUtility.debugLog(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("Error code is ");
            stringBuilder.append(i);
            stringBuilder.append("Description is ");
            stringBuilder.append(str);
            PaytmUtility.debugLog(stringBuilder.toString());
            if (i == -6) {
                ((Activity) PaytmWebView.this.getContext()).finish();
                PaytmPaymentTransactionCallback paytmPaymentTransactionCallback = PaytmPGService.getService().mPaymentTransactionCallback;
                if (paytmPaymentTransactionCallback != null) {
                    paytmPaymentTransactionCallback.onErrorLoadingWebPage(i, str, str2);
                }
            }
        }

        public synchronized void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SSL Error occured ");
            stringBuilder.append(sslError.toString());
            PaytmUtility.debugLog(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("SSL Handler is ");
            stringBuilder.append(sslErrorHandler);
            PaytmUtility.debugLog(stringBuilder.toString());
            if (sslErrorHandler != null) {
                sslErrorHandler.cancel();
            }
        }
    }

    public PaytmWebView(Context context, Bundle bundle) {
        super(context);
        this.mContext = (PaytmPGActivity) context;
        setWebViewClient(new PaytmWebViewClient(this, null));
        setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("JavaScript Alert ");
                stringBuilder.append(str);
                PaytmUtility.debugLog(stringBuilder.toString());
                return super.onJsAlert(webView, str, str2, jsResult);
            }
        });
        getSettings().setJavaScriptEnabled(true);
        if (VERSION.SDK_INT >= 21) {
            getSettings().setMixedContentMode(0);
        }
        addJavascriptInterface(new PaytmJavaScriptInterface(this, null), HTML_OUT);
    }

    private synchronized void startProgressDialog() {
        try {
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                public void run() {
                    PaytmWebView.this.mContext.mProgress.setVisibility(0);
                    PaytmUtility.debugLog("Progress dialog started");
                }
            });
        } catch (Exception e) {
            PaytmUtility.printStackTrace(e);
        }
        return;
    }

    private synchronized void stopProgressDialog() {
        try {
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                public void run() {
                    PaytmWebView.this.mContext.mProgress.setVisibility(8);
                    PaytmUtility.debugLog("Progress dialog ended");
                }
            });
        } catch (Exception e) {
            PaytmUtility.printStackTrace(e);
        }
        return;
    }

    private synchronized Bundle parseResponse(String str) {
        Bundle bundle;
        PaytmUtility.debugLog("Parsing the Merchant Response");
        bundle = new Bundle();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != null && jSONObject.length() > 0) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    String string = jSONObject.getString(str2);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str2);
                    stringBuilder.append(" = ");
                    stringBuilder.append(string);
                    PaytmUtility.debugLog(stringBuilder.toString());
                    bundle.putString(str2, string);
                }
            }
        } catch (Exception e) {
            PaytmUtility.debugLog("Error while parsing the Merchant Response");
            PaytmUtility.printStackTrace(e);
        }
        return bundle;
    }
}
