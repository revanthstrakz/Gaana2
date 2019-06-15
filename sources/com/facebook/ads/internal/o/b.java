package com.facebook.ads.internal.o;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.ads.internal.r.a.n;
import com.facebook.ads.internal.r.a.p;
import com.facebook.ads.internal.s.c.d;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

public class b {
    private static final String a = "b";
    private final a b;
    private final ThreadPoolExecutor c;
    private final ConnectivityManager d;
    private final com.facebook.ads.internal.r.a.a e;
    private final Handler f;
    private final long g;
    private final long h;
    private final Context i;
    private final Runnable j = new Runnable() {
        public void run() {
            b.a(b.this);
            if (b.this.n > 0) {
                try {
                    Thread.sleep(b.this.n);
                } catch (InterruptedException unused) {
                }
            }
            b.this.c();
        }
    };
    private final Runnable k = new Runnable() {
        public void run() {
            b.this.l = false;
            if (b.this.c.getQueue().isEmpty()) {
                b.this.c.execute(b.this.j);
            }
        }
    };
    private volatile boolean l;
    private int m;
    private long n;

    public interface a {
        JSONObject a();

        boolean a(JSONArray jSONArray);

        void b();

        void b(JSONArray jSONArray);

        void c();

        boolean d();
    }

    @UiThread
    b(Context context, a aVar) {
        this.b = aVar;
        this.i = context;
        this.c = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        this.d = (ConnectivityManager) context.getSystemService("connectivity");
        this.e = d.b(context);
        this.f = new Handler(Looper.getMainLooper());
        this.g = com.facebook.ads.internal.n.a.n(context);
        this.h = com.facebook.ads.internal.n.a.o(context);
    }

    private void a(long j) {
        this.f.postDelayed(this.k, j);
    }

    private void d() {
        if (this.m >= 5) {
            e();
            b();
            return;
        }
        this.n = this.m == 1 ? AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS : this.n * 2;
        a();
    }

    private void e() {
        this.m = 0;
        this.n = 0;
        if (this.c.getQueue().size() == 0) {
            this.b.b();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        this.l = true;
        this.f.removeCallbacks(this.k);
        a(this.g);
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        if (!this.l) {
            this.l = true;
            this.f.removeCallbacks(this.k);
            a(this.h);
        }
    }

    /* Access modifiers changed, original: 0000 */
    @WorkerThread
    public void c() {
        try {
            NetworkInfo activeNetworkInfo = this.d.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnectedOrConnecting()) {
                    JSONObject a = this.b.a();
                    if (a == null) {
                        e();
                        return;
                    }
                    a aVar;
                    JSONArray jSONArray;
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("attempt", String.valueOf(this.m));
                    a.put("data", jSONObject);
                    p pVar = new p();
                    pVar.put("payload", a.toString());
                    n b = this.e.b(com.facebook.ads.internal.q.d.a(this.i), pVar);
                    CharSequence e = b != null ? b.e() : null;
                    if (TextUtils.isEmpty(e)) {
                        if (a.has("events")) {
                            aVar = this.b;
                            jSONArray = a.getJSONArray("events");
                        }
                        d();
                        return;
                    }
                    if (b.a() == 200) {
                        if (this.b.a(new JSONArray(e))) {
                            if (this.b.d()) {
                            }
                        }
                        d();
                        return;
                    } else if (b.a() == 413 && com.facebook.ads.internal.n.a.w(this.i)) {
                        this.b.c();
                    } else {
                        if (a.has("events")) {
                            aVar = this.b;
                            jSONArray = a.getJSONArray("events");
                        }
                        d();
                        return;
                    }
                    e();
                    return;
                    aVar.b(jSONArray);
                    d();
                    return;
                }
            }
            a(this.h);
        } catch (Exception unused) {
            d();
        }
    }
}
