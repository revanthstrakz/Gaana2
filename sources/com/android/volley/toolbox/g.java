package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView.ScaleType;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.h;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class g {
    private final h a;
    private int b;
    private final b c;
    private final HashMap<String, a> d;
    private final HashMap<String, a> e;
    private final Handler f;
    private Runnable g;

    public interface d extends com.android.volley.i.a {
        void a(c cVar, boolean z);
    }

    private class a {
        private final Request<?> b;
        private Bitmap c;
        private VolleyError d;
        private final LinkedList<c> e = new LinkedList();

        public a(Request<?> request, c cVar) {
            this.b = request;
            this.e.add(cVar);
        }

        public void a(VolleyError volleyError) {
            this.d = volleyError;
        }

        public VolleyError a() {
            return this.d;
        }

        public void a(c cVar) {
            this.e.add(cVar);
        }

        public boolean b(c cVar) {
            this.e.remove(cVar);
            if (this.e.size() != 0) {
                return false;
            }
            this.b.cancel();
            return true;
        }
    }

    public interface b {
        Bitmap a(String str);

        void a(String str, Bitmap bitmap);
    }

    public class c {
        private Bitmap b;
        private final d c;
        private final String d;
        private final String e;

        public c(Bitmap bitmap, String str, String str2, d dVar) {
            this.b = bitmap;
            this.e = str;
            this.d = str2;
            this.c = dVar;
        }

        public void a() {
            if (this.c != null) {
                a aVar = (a) g.this.d.get(this.d);
                if (aVar == null) {
                    aVar = (a) g.this.e.get(this.d);
                    if (aVar != null) {
                        aVar.b(this);
                        if (aVar.e.size() == 0) {
                            g.this.e.remove(this.d);
                        }
                    }
                } else if (aVar.b(this)) {
                    g.this.d.remove(this.d);
                }
            }
        }

        public Bitmap b() {
            return this.b;
        }

        public String c() {
            return this.e;
        }
    }

    public c a(String str, d dVar, int i, int i2, ScaleType scaleType, boolean z) {
        return a(str, dVar, i, i2, scaleType, z, true);
    }

    public c a(String str, d dVar, int i, int i2, ScaleType scaleType, boolean z, boolean z2) {
        d dVar2 = dVar;
        a();
        String str2 = str;
        int i3 = i;
        int i4 = i2;
        ScaleType scaleType2 = scaleType;
        String a = a(str2, i3, i4, scaleType2);
        Bitmap a2 = this.c.a(a);
        c cVar;
        if (a2 != null) {
            cVar = new c(a2, str2, null, null);
            dVar2.a(cVar, true);
            return cVar;
        }
        cVar = new c(null, str2, a, dVar2);
        dVar2.a(cVar, true);
        a aVar = (a) this.d.get(a);
        if (aVar != null) {
            aVar.a(cVar);
            return cVar;
        }
        Request a3 = a(str2, i3, i4, scaleType2, a);
        a3.setCacheOnly(z);
        a3.setShouldCache(z2);
        this.a.a(a3);
        this.d.put(a, new a(a3, cVar));
        return cVar;
    }

    /* Access modifiers changed, original: protected */
    public Request<Bitmap> a(String str, int i, int i2, ScaleType scaleType, final String str2) {
        return new h(str, new com.android.volley.i.b<Bitmap>() {
            /* renamed from: a */
            public void onResponse(Bitmap bitmap) {
                g.this.a(str2, bitmap);
            }
        }, i, i2, scaleType, Config.RGB_565, new com.android.volley.i.a() {
            public void onErrorResponse(VolleyError volleyError) {
                g.this.a(str2, volleyError);
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void a(String str, Bitmap bitmap) {
        this.c.a(str, bitmap);
        a aVar = (a) this.d.remove(str);
        if (aVar != null) {
            aVar.c = bitmap;
            a(str, aVar);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(String str, VolleyError volleyError) {
        a aVar = (a) this.d.remove(str);
        if (aVar != null) {
            aVar.a(volleyError);
            a(str, aVar);
        }
    }

    private void a(String str, a aVar) {
        this.e.put(str, aVar);
        if (this.g == null) {
            this.g = new Runnable() {
                public void run() {
                    for (a aVar : g.this.e.values()) {
                        Iterator it = aVar.e.iterator();
                        while (it.hasNext()) {
                            c cVar = (c) it.next();
                            if (cVar.c != null) {
                                if (aVar.a() == null) {
                                    cVar.b = aVar.c;
                                    cVar.c.a(cVar, false);
                                } else {
                                    cVar.c.onErrorResponse(aVar.a());
                                }
                            }
                        }
                    }
                    g.this.e.clear();
                    g.this.g = null;
                }
            };
            this.f.postDelayed(this.g, (long) this.b);
        }
    }

    private void a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
        }
    }

    private static String a(String str, int i, int i2, ScaleType scaleType) {
        StringBuilder stringBuilder = new StringBuilder(str.length() + 12);
        stringBuilder.append("#W");
        stringBuilder.append(i);
        stringBuilder.append("#H");
        stringBuilder.append(i2);
        stringBuilder.append("#S");
        stringBuilder.append(scaleType.ordinal());
        stringBuilder.append(str);
        return stringBuilder.toString();
    }
}
