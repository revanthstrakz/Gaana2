package com.facebook.ads.internal.view.g;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.facebook.ads.internal.s.a.r;
import com.facebook.ads.internal.s.a.x;
import com.facebook.internal.NativeProtocol;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class c implements r<Bundle> {
    private final String a;
    private boolean b;
    private final Context c;
    private final com.facebook.ads.internal.o.c d;
    private final a e;
    private final com.facebook.ads.internal.b.a f;
    private int g;
    private int h;
    private final e i;
    @Nullable
    private final Map<String, String> j;

    public interface a {
        int getCurrentPositionInMillis();

        boolean getGlobalVisibleRect(Rect rect);

        long getInitialBufferTime();

        int getMeasuredHeight();

        int getMeasuredWidth();

        com.facebook.ads.internal.view.g.a.a getVideoStartReason();

        float getVolume();

        boolean h();

        boolean i();
    }

    protected enum b {
        PLAY(0),
        SKIP(1),
        TIME(2),
        MRC(3),
        PAUSE(4),
        RESUME(5),
        MUTE(6),
        UNMUTE(7),
        VIEWABLE_IMPRESSION(10);
        
        public final int j;

        private b(int i) {
            this.j = i;
        }
    }

    public c(Context context, com.facebook.ads.internal.o.c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str) {
        this(context, cVar, aVar, list, str, null);
    }

    public c(Context context, com.facebook.ads.internal.o.c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str, @Nullable Bundle bundle) {
        this(context, cVar, aVar, list, str, bundle, null);
    }

    public c(Context context, com.facebook.ads.internal.o.c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str, @Nullable Bundle bundle, @Nullable Map<String, String> map) {
        a aVar2 = aVar;
        List<com.facebook.ads.internal.b.b> list2 = list;
        Bundle bundle2 = bundle;
        this.b = true;
        this.g = 0;
        this.h = 0;
        this.c = context;
        this.d = cVar;
        this.e = aVar2;
        this.a = str;
        this.j = map;
        list2.add(new com.facebook.ads.internal.b.b(this, 0.5d, -1.0d, 2.0d, true) {
            final /* synthetic */ c a;

            /* Access modifiers changed, original: protected */
            public void a(boolean z, boolean z2, com.facebook.ads.internal.b.c cVar) {
                if (z2) {
                    this.a.d.e(this.a.a, this.a.a(b.MRC));
                }
            }
        });
        list2.add(new com.facebook.ads.internal.b.b(this, 1.0E-7d, -1.0d, 0.001d, false) {
            final /* synthetic */ c a;

            /* Access modifiers changed, original: protected */
            public void a(boolean z, boolean z2, com.facebook.ads.internal.b.c cVar) {
                if (z2) {
                    this.a.d.e(this.a.a, this.a.a(b.VIEWABLE_IMPRESSION));
                }
            }
        });
        if (bundle2 != null) {
            this.f = new com.facebook.ads.internal.b.a((View) aVar2, list2, bundle2.getBundle("adQualityManager"));
            this.g = bundle2.getInt("lastProgressTimeMS");
            this.h = bundle2.getInt("lastBoundaryTimeMS");
        } else {
            this.f = new com.facebook.ads.internal.b.a((View) aVar2, list2);
        }
        this.i = new e(new Handler(), this);
    }

    private Map<String, String> a(b bVar) {
        return a(bVar, this.e.getCurrentPositionInMillis());
    }

    private Map<String, String> a(b bVar, int i) {
        Map c = c(i);
        c.put(NativeProtocol.WEB_DIALOG_ACTION, String.valueOf(bVar.j));
        return c;
    }

    private void a() {
        this.d.e(this.a, a(b.MUTE));
    }

    private void a(int i, boolean z) {
        if (((double) i) > 0.0d && i >= this.g) {
            if (i > this.g) {
                this.f.a((double) (((float) (i - this.g)) / 1000.0f), (double) d());
                this.g = i;
                if (i - this.h >= 5000) {
                    this.d.e(this.a, a(b.TIME, i));
                    this.h = this.g;
                    this.f.a();
                    return;
                }
            }
            if (z) {
                this.d.e(this.a, a(b.TIME, i));
            }
        }
    }

    private void a(HashMap<String, String> hashMap) {
        if (this.j != null) {
            hashMap.putAll(this.j);
        }
    }

    private void a(Map<String, String> map) {
        map.put("exoplayer", String.valueOf(this.e.h()));
        map.put("prep", Long.toString(this.e.getInitialBufferTime()));
    }

    private void a(Map<String, String> map, int i) {
        map.put("ptime", String.valueOf(((float) this.h) / 1000.0f));
        map.put("time", String.valueOf(((float) i) / 1000.0f));
    }

    private void b(Map<String, String> map) {
        com.facebook.ads.internal.b.c c = this.f.c();
        com.facebook.ads.internal.b.c.a c2 = c.c();
        map.put("vwa", String.valueOf(c2.d()));
        map.put("vwm", String.valueOf(c2.c()));
        map.put("vwmax", String.valueOf(c2.e()));
        map.put("vtime_ms", String.valueOf(c2.g() * 1000.0d));
        map.put("mcvt_ms", String.valueOf(c2.h() * 1000.0d));
        com.facebook.ads.internal.b.c.a d = c.d();
        map.put("vla", String.valueOf(d.d()));
        map.put("vlm", String.valueOf(d.c()));
        map.put("vlmax", String.valueOf(d.e()));
        map.put("atime_ms", String.valueOf(d.g() * 1000.0d));
        map.put("mcat_ms", String.valueOf(d.h() * 1000.0d));
    }

    private Map<String, String> c(int i) {
        HashMap hashMap = new HashMap();
        x.a(hashMap, this.e.getVideoStartReason() == com.facebook.ads.internal.view.g.a.a.AUTO_STARTED, this.e.i() ^ 1);
        a((Map) hashMap);
        b((Map) hashMap);
        a((Map) hashMap, i);
        c((Map) hashMap);
        a(hashMap);
        return hashMap;
    }

    private void c(Map<String, String> map) {
        Rect rect = new Rect();
        this.e.getGlobalVisibleRect(rect);
        map.put("pt", String.valueOf(rect.top));
        map.put("pl", String.valueOf(rect.left));
        map.put("ph", String.valueOf(this.e.getMeasuredHeight()));
        map.put("pw", String.valueOf(this.e.getMeasuredWidth()));
        WindowManager windowManager = (WindowManager) this.c.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        map.put("vph", String.valueOf(displayMetrics.heightPixels));
        map.put("vpw", String.valueOf(displayMetrics.widthPixels));
    }

    private void k() {
        this.d.e(this.a, a(b.UNMUTE));
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i) {
        a(i, false);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i, int i2) {
        a(i, true);
        this.h = i2;
        this.g = i2;
        this.f.a();
        this.f.b();
    }

    public void b() {
        this.c.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.i);
    }

    public void b(int i) {
        a(i, true);
        this.h = 0;
        this.g = 0;
        this.f.a();
        this.f.b();
    }

    public void c() {
        this.c.getContentResolver().unregisterContentObserver(this.i);
    }

    /* Access modifiers changed, original: protected */
    public float d() {
        return x.a(this.c) * this.e.getVolume();
    }

    /* Access modifiers changed, original: 0000 */
    public void e() {
        boolean z;
        if (((double) d()) < 0.05d) {
            if (this.b) {
                a();
                z = false;
            }
            return;
        }
        if (!this.b) {
            k();
            z = true;
        }
        return;
        this.b = z;
    }

    /* Access modifiers changed, original: 0000 */
    public void f() {
        this.d.e(this.a, a(b.SKIP));
    }

    public Bundle g() {
        a(j(), j());
        Bundle bundle = new Bundle();
        bundle.putInt("lastProgressTimeMS", this.g);
        bundle.putInt("lastBoundaryTimeMS", this.h);
        bundle.putBundle("adQualityManager", this.f.g());
        return bundle;
    }

    /* Access modifiers changed, original: 0000 */
    public void h() {
        this.d.e(this.a, a(b.PAUSE));
    }

    /* Access modifiers changed, original: 0000 */
    public void i() {
        this.d.e(this.a, a(b.RESUME));
    }

    public int j() {
        return this.g;
    }
}
