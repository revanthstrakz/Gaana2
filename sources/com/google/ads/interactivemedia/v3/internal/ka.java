package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ka extends jz {
    private static AdvertisingIdClient e;
    private static CountDownLatch f = new CountDownLatch(1);
    private static volatile boolean g;
    private boolean h;

    class a {
        private String b;
        private boolean c;

        public a(String str, boolean z) {
            this.b = str;
            this.c = z;
        }

        public String a() {
            return this.b;
        }

        public boolean b() {
            return this.c;
        }
    }

    private static final class b implements Runnable {
        private Context a;

        public b(Context context) {
            this.a = context.getApplicationContext();
            if (this.a == null) {
                this.a = context;
            }
        }

        public void run() {
            synchronized (ka.class) {
                try {
                    if (ka.e == null) {
                        AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.a);
                        advertisingIdClient.start();
                        ka.e = advertisingIdClient;
                    }
                    ka.f.countDown();
                } catch (GooglePlayServicesNotAvailableException unused) {
                    try {
                        ka.g = true;
                        ka.f.countDown();
                    } catch (Throwable th) {
                        ka.f.countDown();
                    }
                } catch (IOException unused2) {
                    ka.f.countDown();
                } catch (GooglePlayServicesRepairableException unused3) {
                    ka.f.countDown();
                }
            }
        }
    }

    public static ka a(String str, Context context) {
        return a(str, context, true);
    }

    public static ka a(String str, Context context, boolean z) {
        jv jvVar = new jv();
        jz.a(str, context, jvVar);
        if (z) {
            synchronized (ka.class) {
                if (e == null) {
                    new Thread(new b(context)).start();
                }
            }
        }
        return new ka(context, jvVar, new kg(239), z);
    }

    protected ka(Context context, kd kdVar, ke keVar, boolean z) {
        super(context, kdVar, keVar);
        this.h = z;
    }

    /* Access modifiers changed, original: 0000 */
    public a e() throws IOException {
        try {
            if (!f.await(2, TimeUnit.SECONDS)) {
                return new a(null, false);
            }
            synchronized (ka.class) {
                if (e == null) {
                    a aVar = new a(null, false);
                    return aVar;
                }
                Info info = e.getInfo();
                return new a(a(info.getId()), info.isLimitAdTrackingEnabled());
            }
        } catch (InterruptedException unused) {
            return new a(null, false);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(Context context) {
        super.b(context);
        try {
            if (!g) {
                if (this.h) {
                    a e = e();
                    String a = e.a();
                    if (a != null) {
                        a(28, e.b() ? 1 : 0);
                        a(26, 5);
                        a(24, a);
                    }
                }
            }
            a(24, jz.d(context));
        } catch (IOException unused) {
        } catch (a unused2) {
        }
    }
}
