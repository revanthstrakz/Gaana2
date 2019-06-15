package com.til.colombia.android.vast;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.til.colombia.android.network.n;

public final class i extends WebView {
    public static final int a = 16;
    b b;

    private class a implements OnTouchListener {
        private a() {
        }

        /* synthetic */ a(i iVar, byte b) {
            this();
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            return motionEvent.getAction() == 2;
        }
    }

    interface b {
        void a();
    }

    private class c extends WebViewClient {
        public final void onPageFinished(WebView webView, String str) {
        }

        private c() {
        }

        /* synthetic */ c(i iVar, byte b) {
            this();
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            try {
                n.a(com.til.colombia.android.internal.a.a(), Uri.parse(str));
            } catch (Exception unused) {
            }
            return true;
        }
    }

    class d implements OnTouchListener {
        private boolean b;

        private d() {
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.b = true;
                    break;
                case 1:
                    if (this.b) {
                        this.b = false;
                        break;
                    }
                    return false;
            }
            return false;
        }
    }

    public i(Context context) {
        super(context.getApplicationContext());
        WebSettings settings = getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        if (VERSION.SDK_INT >= 11) {
            settings.setDisplayZoomControls(false);
        }
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(2);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        getSettings().setSupportZoom(false);
        setScrollBarStyle(0);
        setBackgroundColor(0);
        setWebViewClient(new c(this, (byte) 0));
        setOnTouchListener(new a(this, (byte) 0));
    }

    private void a() {
        WebSettings settings = getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        if (VERSION.SDK_INT >= 11) {
            settings.setDisplayZoomControls(false);
        }
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(2);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        getSettings().setSupportZoom(false);
        setScrollBarStyle(0);
    }

    /* Access modifiers changed, original: final */
    public final void a(String str) {
        loadDataWithBaseURL(null, str, "text/html", "UTF-8", null);
    }

    /* Access modifiers changed, original: final */
    public final void b(String str) {
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setUseWideViewPort(true);
        loadUrl(str);
    }

    private void a(b bVar) {
        this.b = bVar;
    }

    private void b() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        getSettings().setSupportZoom(false);
        setScrollBarStyle(0);
    }

    private static i a(Context context, VastCompanionResource vastCompanionResource) {
        if (context == null || vastCompanionResource == null) {
            return null;
        }
        i iVar = new i(context);
        vastCompanionResource.initializeVastResourceView(iVar);
        return iVar;
    }

    @Deprecated
    private b c() {
        return this.b;
    }
}
