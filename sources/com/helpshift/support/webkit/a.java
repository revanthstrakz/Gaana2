package com.helpshift.support.webkit;

import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;

public class a extends WebChromeClient {
    private final View a;
    private final View b;
    private final ViewGroup c;
    private CustomViewCallback d;
    private View e;

    public a(View view, View view2) {
        this.a = view;
        this.b = view2;
        this.c = (ViewGroup) view.findViewById(16908290);
    }

    public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        if (this.e != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        this.c.addView(view);
        this.e = view;
        this.e.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.d = customViewCallback;
        this.b.setVisibility(8);
        a();
    }

    public void onHideCustomView() {
        if (this.e != null) {
            this.e.setVisibility(8);
            this.c.removeView(this.e);
            this.e = null;
            if (this.d != null) {
                this.d.onCustomViewHidden();
            }
            this.b.setVisibility(0);
            b();
        }
    }

    private void a() {
        int systemUiVisibility = this.a.getSystemUiVisibility();
        if (VERSION.SDK_INT >= 14) {
            systemUiVisibility |= 2;
        }
        if (VERSION.SDK_INT >= 16) {
            systemUiVisibility |= 4;
        }
        if (VERSION.SDK_INT >= 18) {
            systemUiVisibility |= 4096;
        }
        this.a.setSystemUiVisibility(systemUiVisibility);
    }

    private void b() {
        int systemUiVisibility = this.a.getSystemUiVisibility();
        if (VERSION.SDK_INT >= 14) {
            systemUiVisibility &= -3;
        }
        if (VERSION.SDK_INT >= 16) {
            systemUiVisibility &= -5;
        }
        if (VERSION.SDK_INT >= 18) {
            systemUiVisibility &= -4097;
        }
        this.a.setSystemUiVisibility(systemUiVisibility);
    }
}
