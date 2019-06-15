package com.facebook.ads.internal.d;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class a {
    public static final String a = "a";
    private final String b;
    private final boolean c;
    private final c d;

    private static final class a implements IInterface {
        private IBinder a;

        a(IBinder iBinder) {
            this.a = iBinder;
        }

        public String a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                return readString;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public IBinder asBinder() {
            return this.a;
        }

        public boolean b() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean z = true;
                obtain.writeInt(1);
                this.a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z = false;
                }
                obtain2.recycle();
                obtain.recycle();
                return z;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    private static final class b implements ServiceConnection {
        private AtomicBoolean a;
        private final BlockingQueue<IBinder> b;

        private b() {
            this.a = new AtomicBoolean(false);
            this.b = new LinkedBlockingDeque();
        }

        public IBinder a() {
            if (!this.a.compareAndSet(true, true)) {
                return (IBinder) this.b.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public enum c {
        SHARED_PREFS,
        FB4A,
        DIRECT,
        REFLECTION,
        SERVICE
    }

    private a(String str, boolean z, c cVar) {
        this.b = str;
        this.c = z;
        this.d = cVar;
    }

    private static a a(Context context) {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo != null) {
                return new a(advertisingIdInfo.getId(), advertisingIdInfo.isLimitAdTrackingEnabled(), c.DIRECT);
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static a a(Context context, com.facebook.ads.internal.d.c.a aVar) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot get advertising info on main thread.");
        } else if (com.facebook.ads.internal.s.a.b.a() && com.facebook.ads.internal.s.a.b.b("idfa_override")) {
            return new a(com.facebook.ads.internal.s.a.b.a("idfa_override"), false, c.DIRECT);
        } else {
            if (aVar != null && !TextUtils.isEmpty(aVar.b)) {
                return new a(aVar.b, aVar.c, c.FB4A);
            }
            a a = a(context);
            if (a == null || TextUtils.isEmpty(a.a())) {
                a = b(context);
            }
            if (a == null || TextUtils.isEmpty(a.a())) {
                a = c(context);
            }
            return a;
        }
    }

    private static a b(Context context) {
        Method a = d.a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
        a aVar = null;
        if (a == null) {
            return null;
        }
        Object a2 = d.a(null, a, context);
        if (a2 != null) {
            if (((Integer) a2).intValue() != 0) {
                return null;
            }
            a = d.a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
            if (a == null) {
                return null;
            }
            Object a3 = d.a(null, a, context);
            if (a3 == null) {
                return null;
            }
            a = d.a(a3.getClass(), "getId", new Class[0]);
            Method a4 = d.a(a3.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
            if (a != null) {
                if (a4 == null) {
                    return null;
                }
                aVar = new a((String) d.a(a3, a, new Object[0]), ((Boolean) d.a(a3, a4, new Object[0])).booleanValue(), c.REFLECTION);
            }
        }
        return aVar;
    }

    private static a c(Context context) {
        b bVar = new b();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        a aVar = true;
        if (context.bindService(intent, bVar, 1)) {
            try {
                a aVar2 = new a(bVar.a());
                aVar = new a(aVar2.a(), aVar2.b(), c.SERVICE);
                return aVar;
            } catch (Exception unused) {
            } finally {
                context.unbindService(bVar);
            }
        }
        return null;
    }

    public String a() {
        return this.b;
    }

    public boolean b() {
        return this.c;
    }

    public c c() {
        return this.d;
    }
}
