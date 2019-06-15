package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.t.a;
import java.util.Map;

public class s extends b {
    private final c c;
    private final w d;
    private k e;

    public s(Context context, c cVar, a aVar, w wVar, c cVar2) {
        super(context, cVar2, aVar);
        this.c = cVar;
        this.d = wVar;
    }

    public void a(k kVar) {
        this.e = kVar;
    }

    /* Access modifiers changed, original: protected */
    public void a(Map<String, String> map) {
        if (this.e != null && !TextUtils.isEmpty(this.e.g())) {
            map.put("touch", com.facebook.ads.internal.s.a.k.a(this.d.e()));
            this.c.a(this.e.g(), map);
        }
    }
}
