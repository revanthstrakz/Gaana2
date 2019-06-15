package com.helpshift.support.webkit;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.helpshift.util.h;
import com.helpshift.util.l;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

public class b extends WebViewClient {
    public static final String a = "b";
    private Context b;
    private final a c;

    public interface a {
        void c();

        void d();
    }

    public b(Context context, a aVar) {
        this.c = aVar;
        this.b = context;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Context context = webView.getContext();
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
                return true;
            }
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        this.c.c();
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.c.d();
    }

    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        URL url;
        File externalCacheDir = this.b.getExternalCacheDir();
        try {
            url = new URL(str);
        } catch (MalformedURLException e) {
            l.a(a, "MalformedURLException", e);
            url = null;
        }
        if (url != null) {
            File file = new File(externalCacheDir, str.replace("/", "_"));
            if (file.exists()) {
                try {
                    return new WebResourceResponse("", "", new FileInputStream(file));
                } catch (FileNotFoundException e2) {
                    l.b(a, "FileNotFoundException", e2);
                }
            } else if (h.a(url)) {
                h.a(url, file);
            }
        }
        return super.shouldInterceptRequest(webView, str);
    }
}
