package com.auto;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.MediaBrowserServiceCompat.BrowserRoot;
import android.support.v4.media.MediaBrowserServiceCompat.Result;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import com.auto.a.a;
import com.auto.b.b;
import com.auto.b.c;
import com.managers.u;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoMediaBrowserService extends MediaBrowserServiceCompat {
    public static String a = " ";
    private c b;
    private a c;
    private MediaSessionCompat d;
    private Bundle e = null;
    private a f;

    public void onCreate() {
        super.onCreate();
        this.c = new a();
        this.b = new c(this);
        this.d = new MediaSessionCompat((Context) this, "AutoMediaBrowserService");
        this.f = new a(this.d, this.c);
        this.f.a();
        setSessionToken(this.d.getSessionToken());
        if (this.f != null) {
            this.f.b();
        }
        u.a().b("Auto", "App open");
    }

    @Nullable
    public BrowserRoot onGetRoot(@NonNull String str, int i, @Nullable Bundle bundle) {
        if (!this.b.a(this, str, i)) {
            return new BrowserRoot("_empty_", null);
        }
        a = str;
        return new BrowserRoot("_parent_", null);
    }

    public void onLoadChildren(@NonNull final String str, @NonNull final Result<List<MediaItem>> result) {
        if (TextUtils.isEmpty(str) || str.equals("_empty_")) {
            result.sendResult(new ArrayList());
        } else if (str.equals("Top Charts") || str.equals("Trending Songs") || str.equals("New Releases") || str.equals("Gaana Radio") || str.equals("Radio Mirchi") || b.a.contains(str)) {
            result.detach();
            this.c.a(new a.a() {
                public void a(boolean z, String str) {
                    if (z && str.equals(str)) {
                        AutoMediaBrowserService.this.a(str, result);
                    } else {
                        result.sendResult(Collections.emptyList());
                    }
                }
            }, str);
        } else {
            a(str, result);
        }
    }

    @TargetApi(21)
    private void a(String str, Result<List<MediaItem>> result) {
        try {
            result.sendResult(this.c.a(str));
        } catch (IllegalStateException unused) {
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.f != null) {
            this.f.c();
            this.f = null;
        }
    }

    public static boolean a() {
        if (TextUtils.isEmpty(a)) {
            return false;
        }
        return a.equals("com.sec.android.automotive.drivelink");
    }
}
