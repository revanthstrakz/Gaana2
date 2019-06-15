package com.til.colombia.android.service;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.til.colombia.android.commons.CommonUtil;

@TargetApi(19)
public class ColombiaBannerView extends RelativeLayout {
    private final float CLICK_DISPLACEMENT = 8.0f;
    private WebView bannerWebView;
    float downX;
    float downY;
    private Item item;
    private String itemJson;
    private Context mContext;

    private class a extends WebViewClient {
        public final void onPageFinished(WebView webView, String str) {
        }

        private a() {
        }

        /* synthetic */ a(ColombiaBannerView colombiaBannerView, byte b) {
            this();
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return true;
        }
    }

    private class b implements OnTouchListener {
        private b() {
        }

        /* synthetic */ b(ColombiaBannerView colombiaBannerView, byte b) {
            this();
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    ColombiaBannerView.this.downX = motionEvent.getX();
                    ColombiaBannerView.this.downY = motionEvent.getY();
                    break;
                case 1:
                    float abs = Math.abs(motionEvent.getX() - ColombiaBannerView.this.downX);
                    float abs2 = Math.abs(motionEvent.getY() - ColombiaBannerView.this.downY);
                    if (abs <= 8.0f && abs2 <= 8.0f) {
                        bi.a();
                        bi.a(ColombiaBannerView.this.item, false);
                        break;
                    }
                case 2:
                    return true;
            }
            return false;
        }
    }

    private class c implements OnTouchListener {
        private c() {
        }

        /* synthetic */ c(ColombiaBannerView colombiaBannerView, byte b) {
            this();
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            return motionEvent.getAction() == 2;
        }
    }

    public ColombiaBannerView(Context context) {
        super(context);
        this.mContext = context;
    }

    public ColombiaBannerView(Context context, int i, int i2) {
        super(context);
        this.mContext = context;
        setLayoutParams(new LayoutParams(CommonUtil.b((float) i, this.mContext), CommonUtil.b((float) i2, this.mContext)));
    }

    public ColombiaBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public void initWebView() {
        String snippet = ((NativeItem) this.item).getSnippet();
        this.bannerWebView = new WebView(this.mContext);
        this.bannerWebView.setWebViewClient(new ak(this));
        this.bannerWebView.setVerticalScrollBarEnabled(false);
        this.bannerWebView.setHorizontalScrollBarEnabled(false);
        WebSettings settings = this.bannerWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultZoom(ZoomDensity.FAR);
        this.bannerWebView.setBackgroundColor(0);
        this.bannerWebView.setLayerType(1, null);
        this.bannerWebView.setWebChromeClient(new WebChromeClient());
        if (((NativeItem) this.item).getDataType() == 1) {
            this.bannerWebView.setWebViewClient(new a(this, (byte) 0));
            this.bannerWebView.setOnTouchListener(new c(this, (byte) 0));
        } else {
            this.bannerWebView.setOnTouchListener(new b(this, (byte) 0));
        }
        this.bannerWebView.loadData(snippet, "text/html", AudienceNetworkActivity.WEBVIEW_ENCODING);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(14);
        addView(this.bannerWebView, layoutParams);
    }

    public void setNativeAd(Item item) {
        this.item = item;
        this.itemJson = ((NativeItem) this.item).toJSONObjectString();
        initWebView();
    }
}
