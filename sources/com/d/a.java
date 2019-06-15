package com.d;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.R;
import com.google.android.gms.auth.api.credentials.IdentityProviders;
import com.managers.PurchasePaypalManager;

public class a extends CustomTabsCallback {
    private static a b;
    int a = 1;
    private c c;
    private CustomTabsIntent d;
    private String e = IdentityProviders.PAYPAL;
    private Context f;

    public interface a {
        void a(CustomTabsClient customTabsClient);
    }

    public interface b {
        void a();
    }

    private a() {
    }

    public static a a() {
        if (b == null) {
            b = new a();
        }
        return b;
    }

    public void a(Context context, final b bVar) {
        this.f = context;
        if (TextUtils.isEmpty(b.a(this.f))) {
            this.c = new c();
            bVar.a();
            return;
        }
        a(this.f, new a() {
            public void a(CustomTabsClient customTabsClient) {
                if (customTabsClient == null) {
                    a.this.c = new c();
                    bVar.a();
                    return;
                }
                Resources resources;
                int i;
                customTabsClient.warmup(0);
                CustomTabsSession newSession = customTabsClient.newSession(a.this);
                newSession.mayLaunchUrl(Uri.parse(a.this.e), null, null);
                Bitmap a = a.this.a(a.this.f, Constants.l ? R.drawable.vector_ab_back : R.drawable.vector_ab_back_white);
                Builder builder = new Builder(newSession);
                Builder addDefaultShareMenuItem = builder.addDefaultShareMenuItem();
                if (Constants.l) {
                    resources = a.this.f.getResources();
                    i = R.color.tab_layout_background_white;
                } else {
                    resources = a.this.f.getResources();
                    i = R.color.tab_layout_background;
                }
                addDefaultShareMenuItem.setToolbarColor(resources.getColor(i)).setShowTitle(true).setCloseButtonIcon(a);
                a.this.a(builder.build());
                bVar.a();
            }
        });
    }

    private void a(Context context, final a aVar) {
        CustomTabsClient.bindCustomTabsService(context, b.a(context), new CustomTabsServiceConnection() {
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                aVar.a(customTabsClient);
            }

            public void onServiceDisconnected(ComponentName componentName) {
                aVar.a(null);
            }
        });
    }

    private Bitmap a(Context context, @DrawableRes int i) {
        Drawable drawable = AppCompatResources.getDrawable(context, i);
        if (drawable == null) {
            return null;
        }
        if (VERSION.SDK_INT < 21) {
            drawable = DrawableCompat.wrap(drawable).mutate();
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public void onNavigationEvent(int i, Bundle bundle) {
        if (i == 6) {
            int i2 = this.a;
        }
        this.a = i;
    }

    private void a(CustomTabsIntent customTabsIntent) {
        customTabsIntent.intent.addFlags(67108864);
        this.d = customTabsIntent;
    }

    public void a(Context context, String str) {
        this.f = context;
        if (this.d == null || this.d.intent == null) {
            if (this.c == null) {
                this.c = new c();
            }
            this.c.a(this.f, Uri.parse(str));
            return;
        }
        this.d.intent.setData(Uri.parse(str));
        ((Activity) this.f).startActivityForResult(this.d.intent, 712);
    }

    public void a(int i, int i2, Intent intent) {
        if (i == 712) {
            PurchasePaypalManager.a(this.f).a(i, i2, intent);
        }
    }
}
