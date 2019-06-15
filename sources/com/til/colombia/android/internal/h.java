package com.til.colombia.android.internal;

import android.os.Build;
import android.os.Build.VERSION;
import com.til.colombia.android.internal.Log.INTERNAL_LOG_LEVEL;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import java.util.Locale;
import java.util.TimeZone;

public final class h {
    public static final int A = 1;
    public static final int B = 5000;
    public static final String C = "";
    public static final int D = 10;
    public static final int E = 1;
    public static final int F = ITEM_TYPE.AUDIO_BANNER.ordinal();
    public static final int G = 200;
    public static int H = 0;
    public static int I = 0;
    public static final Integer J = Integer.valueOf(2);
    public static final int K = 3;
    private static h W = new h();
    public static final String a = "https://recade.clmbtech.com";
    public static final String b = "cfp";
    public static final String c = "feed.htm";
    public static final String d = "https://ade.clmbtech.com/cde/mnotify.htm";
    public static final String e = "https://ade.clmbtech.com";
    public static final String f = "cde/data/v5.htm";
    public static final String g = "cde/sdk/config/rootConfig.htm";
    public static final String h = ".clmbtech.com";
    public static final String i = "Set-Cookie";
    public static final String j = "cde/networkDims.htm";
    public static final INTERNAL_LOG_LEVEL k = INTERNAL_LOG_LEVEL.NONE;
    public static final String l = "all";
    public static final int m = 15000;
    public static final int n = 15000;
    public static final int o = 4;
    public static final int p = 15;
    public static final int q = 4;
    public static final int r = 5;
    public static final int s = 1000;
    public static final int t = 0;
    public static final long u = 432000;
    public static final int v = 3;
    public static final int w = 15000;
    public static final String x = "http://static.clmbtech.com/ad/commons/js/leadForm.js";
    public static final int y = 3;
    public static final int z = 1000;
    public final String L = Build.MANUFACTURER;
    public final String M = Build.MODEL;
    public final String N = VERSION.RELEASE;
    public final String O = TimeZone.getDefault().getDisplayName(true, 0);
    public String P = null;
    public String Q = null;
    public String R = null;
    public String S = null;
    public String T = null;
    public int U;
    String V = null;
    private String X = null;

    private static String k() {
        return "https://ade.clmbtech.com";
    }

    private static String l() {
        return f;
    }

    public static String a() {
        try {
            if (!com.til.colombia.android.internal.a.h.a(Locale.getDefault().toString())) {
                return Locale.getDefault().toString();
            }
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
        return Locale.getDefault().getLanguage();
    }

    public final String b() {
        return this.P;
    }

    /* Access modifiers changed, original: final */
    public final void a(String str) {
        this.P = str;
    }

    public final String c() {
        if (this.V == null) {
            return a.e();
        }
        return this.V;
    }

    /* Access modifiers changed, original: final */
    public final void b(String str) {
        this.V = str;
    }

    public final Integer d() {
        return Integer.valueOf(this.U);
    }

    /* Access modifiers changed, original: final */
    public final void a(int i) {
        this.U = i;
    }

    public final String e() {
        return this.T;
    }

    /* Access modifiers changed, original: final */
    public final void c(String str) {
        this.T = str;
    }

    public final String f() {
        return this.R;
    }

    public final String g() {
        return this.S;
    }

    /* Access modifiers changed, original: final */
    public final void d(String str) {
        this.R = str;
    }

    /* Access modifiers changed, original: final */
    public final void e(String str) {
        this.S = str;
    }

    public final String h() {
        return this.Q;
    }

    public final void f(String str) {
        this.Q = str;
    }

    public static h i() {
        return W;
    }

    public static String j() {
        return W.X;
    }

    static void g(String str) {
        W.X = str;
    }
}
