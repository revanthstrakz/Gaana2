package com.inmobi.ads.b;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.a.b;
import com.inmobi.ads.d;
import com.inmobi.ads.f;
import com.inmobi.ads.h;
import com.inmobi.ads.i;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

public final class a {
    @NonNull
    public i a;
    @NonNull
    public String b;
    public long c;
    @Nullable
    public b d;

    public a(@NonNull i iVar, @NonNull String str) {
        this.a = iVar;
        this.b = str;
    }

    public final byte[] a() throws b {
        List d;
        List list;
        this.a.d("AdCacheImpressionRequested");
        this.a.i();
        h.a();
        h i = this.a.i();
        long j = this.a.d;
        this.a.b();
        String c = this.a.c();
        MonetizationContext l = this.a.l();
        String str = this.b;
        h.c();
        if (i.d.e) {
            d = i.b.d(j, c, l, str);
        } else {
            d = i.b.c(j, c, l, str);
        }
        Object obj = d.size() == 0 ? null : (com.inmobi.ads.a) d.get(0);
        f t = this.a.t();
        if (obj == null) {
            list = null;
        } else {
            list = Collections.singletonList(obj);
        }
        this.d = new b(t, list);
        if (obj != null) {
            Map hashMap = new HashMap();
            hashMap.put("impId", obj.g);
            this.a.c("AdCacheImpressionOffered", hashMap);
        }
        if (obj != null) {
            h i2 = this.a.i();
            String str2 = obj.g;
            d dVar = i2.b;
            d.b(str2);
        }
        this.a.i().a(this.a.u());
        this.c = System.currentTimeMillis();
        try {
            return this.d.a();
        } catch (JSONException unused) {
            return null;
        }
    }
}
