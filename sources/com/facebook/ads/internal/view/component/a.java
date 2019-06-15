package com.facebook.ads.internal.view.component;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.e;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.k;
import com.facebook.ads.internal.s.a.w;
import java.util.Map;

public class a extends c {
    private final String b;
    private final com.facebook.ads.internal.t.a c;
    private final w d;
    @Nullable
    private final c e;
    @Nullable
    private final com.facebook.ads.internal.view.a.a f;

    public a(Context context, boolean z, boolean z2, String str, d dVar, c cVar, com.facebook.ads.internal.view.a.a aVar, com.facebook.ads.internal.t.a aVar2, w wVar) {
        super(context, z, z2, dVar);
        this.e = cVar;
        this.f = aVar;
        this.b = str;
        this.c = aVar2;
        this.d = wVar;
    }

    @Nullable
    private b a(Uri uri, String str, Map<String, String> map, boolean z) {
        return com.facebook.ads.internal.a.c.a(getContext(), this.e, str, uri, map, z);
    }

    private void a(String str, String str2, String str3, Map<String, String> map, boolean z) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || this.e == null) {
            setVisibility(8);
            return;
        }
        setText(str);
        final String str4 = str2;
        final Map<String, String> map2 = map;
        final String str5 = str3;
        final boolean z2 = z;
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Throwable e;
                String valueOf;
                String stringBuilder;
                if (!com.facebook.ads.internal.n.a.d(a.this.getContext()) || a.this.d.b()) {
                    try {
                        Uri parse = Uri.parse(str4);
                        a.this.c.a(map2);
                        map2.put("touch", k.a(a.this.d.e()));
                        b a = a.this.a(parse, str5, map2, z2);
                        if (a != null) {
                            a.b();
                        }
                        if (a.this.f != null) {
                            a.this.f.a(a.this.b);
                        }
                    } catch (ActivityNotFoundException e2) {
                        e = e2;
                        valueOf = String.valueOf(a.class);
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Error while opening ");
                        stringBuilder2.append(str4);
                        stringBuilder = stringBuilder2.toString();
                        Log.e(valueOf, stringBuilder, e);
                    } catch (Exception e3) {
                        e = e3;
                        valueOf = String.valueOf(a.class);
                        stringBuilder = "Error executing action";
                        Log.e(valueOf, stringBuilder, e);
                    }
                }
            }
        });
    }

    public void a(e eVar, String str, Map<String, String> map) {
        a(eVar.b(), eVar.a(), str, (Map) map, false);
    }

    public void a(e eVar, String str, Map<String, String> map, boolean z) {
        a(eVar.b(), eVar.a(), str, (Map) map, z);
    }

    public void b(e eVar, String str, Map<String, String> map) {
        Uri parse = Uri.parse(eVar.a());
        this.c.a((Map) map);
        map.put("touch", k.a(this.d.e()));
        b a = a(parse, str, (Map) map, false);
        if (a != null) {
            a.c();
        }
    }
}
