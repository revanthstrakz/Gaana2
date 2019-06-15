package com.facebook.ads.internal.s.c;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.settings.a.a;
import com.google.android.exoplayer2.C;

public class g {
    private Intent a(Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setComponent(null);
        if (VERSION.SDK_INT >= 15) {
            intent.setSelector(null);
        }
        return intent;
    }

    public static void a(g gVar, Context context, Uri uri, String str) {
        Object obj = (a(uri.getScheme()) && uri.getHost().equals("play.google.com")) ? 1 : null;
        if (uri.getScheme().equals("market") || obj != null) {
            try {
                gVar.a(context, uri);
                return;
            } catch (c unused) {
            }
        }
        gVar.a(context, uri, str);
    }

    private boolean a(Context context) {
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/")), 0)) {
            if (resolveInfo.activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(String str) {
        return "http".equalsIgnoreCase(str) || "https".equalsIgnoreCase(str);
    }

    private void b(Context context, Uri uri) {
        context.startActivity(c(context, uri));
    }

    private void b(Context context, Uri uri, String str) {
        Intent intent = new Intent();
        intent.setClassName(context.getPackageName(), "com.facebook.ads.AudienceNetworkActivity");
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, a.BROWSER);
        intent.putExtra(AudienceNetworkActivity.BROWSER_URL, uri.toString());
        intent.putExtra(AudienceNetworkActivity.CLIENT_TOKEN, str);
        intent.putExtra(AudienceNetworkActivity.HANDLER_TIME, System.currentTimeMillis());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            intent.setClassName(context.getPackageName(), "com.facebook.ads.InterstitialAdActivity");
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException unused2) {
                b(context, uri);
            }
        }
    }

    private Intent c(Context context, Uri uri) {
        Intent a = a(uri);
        a.addCategory("android.intent.category.BROWSABLE");
        a.addFlags(C.ENCODING_PCM_MU_LAW);
        a.putExtra("com.android.browser.application_id", context.getPackageName());
        a.putExtra("create_new_tab", false);
        return a;
    }

    public void a(Context context, Uri uri) {
        if (a(context)) {
            Intent c = c(context, uri);
            c.setPackage("com.android.vending");
            context.startActivity(c);
            return;
        }
        throw new c();
    }

    public void a(Context context, Uri uri, String str) {
        if (a(uri.getScheme()) && com.facebook.ads.internal.n.a.i(context)) {
            b(context, uri, str);
        } else {
            b(context, uri);
        }
    }
}
