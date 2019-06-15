package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.facebook.FacebookException;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class AttributionIdentifiers {
    private static final String ANDROID_ID_COLUMN_NAME = "androidid";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER = "com.facebook.katana.provider.AttributionIdProvider";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI = "com.facebook.wakizashi.provider.AttributionIdProvider";
    private static final int CONNECTION_RESULT_SUCCESS = 0;
    private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000;
    private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String androidInstallerPackage;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;

    private static final class GoogleAdInfo implements IInterface {
        private static final int FIRST_TRANSACTION_CODE = 1;
        private static final int SECOND_TRANSACTION_CODE = 2;
        private IBinder binder;

        GoogleAdInfo(IBinder iBinder) {
            this.binder = iBinder;
        }

        public IBinder asBinder() {
            return this.binder;
        }

        public String getAdvertiserId() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                return readString;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isTrackingLimited() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                boolean z = true;
                obtain.writeInt(1);
                this.binder.transact(2, obtain, obtain2, 0);
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

    private static final class GoogleAdServiceConnection implements ServiceConnection {
        private AtomicBoolean consumed;
        private final BlockingQueue<IBinder> queue;

        public void onServiceDisconnected(ComponentName componentName) {
        }

        private GoogleAdServiceConnection() {
            this.consumed = new AtomicBoolean(false);
            this.queue = new LinkedBlockingDeque();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.queue.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        public IBinder getBinder() throws InterruptedException {
            if (!this.consumed.compareAndSet(true, true)) {
                return (IBinder) this.queue.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }
    }

    private static AttributionIdentifiers getAndroidId(Context context) {
        AttributionIdentifiers androidIdViaReflection = getAndroidIdViaReflection(context);
        if (androidIdViaReflection != null) {
            return androidIdViaReflection;
        }
        androidIdViaReflection = getAndroidIdViaService(context);
        return androidIdViaReflection == null ? new AttributionIdentifiers() : androidIdViaReflection;
    }

    private static AttributionIdentifiers getAndroidIdViaReflection(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new FacebookException("getAndroidId cannot be called on the main thread.");
            }
            Method methodQuietly = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
            if (methodQuietly == null) {
                return null;
            }
            Object invokeMethodQuietly = Utility.invokeMethodQuietly(null, methodQuietly, context);
            if (invokeMethodQuietly instanceof Integer) {
                if (((Integer) invokeMethodQuietly).intValue() == 0) {
                    methodQuietly = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
                    if (methodQuietly == null) {
                        return null;
                    }
                    Object invokeMethodQuietly2 = Utility.invokeMethodQuietly(null, methodQuietly, context);
                    if (invokeMethodQuietly2 == null) {
                        return null;
                    }
                    methodQuietly = Utility.getMethodQuietly(invokeMethodQuietly2.getClass(), "getId", new Class[0]);
                    Method methodQuietly2 = Utility.getMethodQuietly(invokeMethodQuietly2.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
                    if (methodQuietly != null) {
                        if (methodQuietly2 != null) {
                            AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                            attributionIdentifiers.androidAdvertiserId = (String) Utility.invokeMethodQuietly(invokeMethodQuietly2, methodQuietly, new Object[0]);
                            attributionIdentifiers.limitTracking = ((Boolean) Utility.invokeMethodQuietly(invokeMethodQuietly2, methodQuietly2, new Object[0])).booleanValue();
                            return attributionIdentifiers;
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            Utility.logd("android_id", e);
            return null;
        }
    }

    private static AttributionIdentifiers getAndroidIdViaService(Context context) {
        GoogleAdServiceConnection googleAdServiceConnection = new GoogleAdServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        AttributionIdentifiers attributionIdentifiers = true;
        if (context.bindService(intent, googleAdServiceConnection, 1)) {
            try {
                GoogleAdInfo googleAdInfo = new GoogleAdInfo(googleAdServiceConnection.getBinder());
                attributionIdentifiers = new AttributionIdentifiers();
                attributionIdentifiers.androidAdvertiserId = googleAdInfo.getAdvertiserId();
                attributionIdentifiers.limitTracking = googleAdInfo.isTrackingLimited();
                return attributionIdentifiers;
            } catch (Exception e) {
                attributionIdentifiers = "android_id";
                Utility.logd((String) attributionIdentifiers, e);
            } finally {
                context.unbindService(googleAdServiceConnection);
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x006e A:{Catch:{ Exception -> 0x00dc, all -> 0x00da }} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0077 A:{Catch:{ Exception -> 0x00dc, all -> 0x00da }} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0072 A:{Catch:{ Exception -> 0x00dc, all -> 0x00da }} */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0102  */
    public static com.facebook.internal.AttributionIdentifiers getAttributionIdentifiers(android.content.Context r10) {
        /*
        r0 = android.os.Looper.myLooper();
        r1 = android.os.Looper.getMainLooper();
        if (r0 != r1) goto L_0x0011;
    L_0x000a:
        r0 = TAG;
        r1 = "getAttributionIdentifiers should not be called from the main thread";
        android.util.Log.e(r0, r1);
    L_0x0011:
        r0 = recentlyFetchedIdentifiers;
        if (r0 == 0) goto L_0x0029;
    L_0x0015:
        r0 = java.lang.System.currentTimeMillis();
        r2 = recentlyFetchedIdentifiers;
        r2 = r2.fetchTime;
        r4 = r0 - r2;
        r0 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
        r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r2 >= 0) goto L_0x0029;
    L_0x0026:
        r10 = recentlyFetchedIdentifiers;
        return r10;
    L_0x0029:
        r0 = getAndroidId(r10);
        r1 = 3;
        r2 = 0;
        r5 = new java.lang.String[r1];	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
        r1 = "aid";
        r3 = 0;
        r5[r3] = r1;	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
        r1 = 1;
        r4 = "androidid";
        r5[r1] = r4;	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
        r1 = 2;
        r4 = "limit_tracking";
        r5[r1] = r4;	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
        r1 = r10.getPackageManager();	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
        r4 = "com.facebook.katana.provider.AttributionIdProvider";
        r1 = r1.resolveContentProvider(r4, r3);	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
        if (r1 == 0) goto L_0x0054;
    L_0x004c:
        r1 = "content://com.facebook.katana.provider.AttributionIdProvider";
        r1 = android.net.Uri.parse(r1);	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
    L_0x0052:
        r4 = r1;
        goto L_0x0068;
    L_0x0054:
        r1 = r10.getPackageManager();	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
        r4 = "com.facebook.wakizashi.provider.AttributionIdProvider";
        r1 = r1.resolveContentProvider(r4, r3);	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
        if (r1 == 0) goto L_0x0067;
    L_0x0060:
        r1 = "content://com.facebook.wakizashi.provider.AttributionIdProvider";
        r1 = android.net.Uri.parse(r1);	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
        goto L_0x0052;
    L_0x0067:
        r4 = r2;
    L_0x0068:
        r1 = getInstallerPackageName(r10);	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
        if (r1 == 0) goto L_0x0070;
    L_0x006e:
        r0.androidInstallerPackage = r1;	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
    L_0x0070:
        if (r4 != 0) goto L_0x0077;
    L_0x0072:
        r10 = cacheAndReturnIdentifiers(r0);	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
        return r10;
    L_0x0077:
        r3 = r10.getContentResolver();	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r10 = r3.query(r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00dc, all -> 0x00da }
        if (r10 == 0) goto L_0x00d0;
    L_0x0084:
        r1 = r10.moveToFirst();	 Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
        if (r1 != 0) goto L_0x008b;
    L_0x008a:
        goto L_0x00d0;
    L_0x008b:
        r1 = "aid";
        r1 = r10.getColumnIndex(r1);	 Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
        r3 = "androidid";
        r3 = r10.getColumnIndex(r3);	 Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
        r4 = "limit_tracking";
        r4 = r10.getColumnIndex(r4);	 Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
        r1 = r10.getString(r1);	 Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
        r0.attributionId = r1;	 Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
        if (r3 <= 0) goto L_0x00bd;
    L_0x00a5:
        if (r4 <= 0) goto L_0x00bd;
    L_0x00a7:
        r1 = r0.getAndroidAdvertiserId();	 Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
        if (r1 != 0) goto L_0x00bd;
    L_0x00ad:
        r1 = r10.getString(r3);	 Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
        r0.androidAdvertiserId = r1;	 Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
        r1 = r10.getString(r4);	 Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
        r1 = java.lang.Boolean.parseBoolean(r1);	 Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
        r0.limitTracking = r1;	 Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
    L_0x00bd:
        if (r10 == 0) goto L_0x00c2;
    L_0x00bf:
        r10.close();
    L_0x00c2:
        r10 = cacheAndReturnIdentifiers(r0);
        return r10;
    L_0x00c7:
        r0 = move-exception;
        r2 = r10;
        r10 = r0;
        goto L_0x0100;
    L_0x00cb:
        r0 = move-exception;
        r9 = r0;
        r0 = r10;
        r10 = r9;
        goto L_0x00de;
    L_0x00d0:
        r0 = cacheAndReturnIdentifiers(r0);	 Catch:{ Exception -> 0x00cb, all -> 0x00c7 }
        if (r10 == 0) goto L_0x00d9;
    L_0x00d6:
        r10.close();
    L_0x00d9:
        return r0;
    L_0x00da:
        r10 = move-exception;
        goto L_0x0100;
    L_0x00dc:
        r10 = move-exception;
        r0 = r2;
    L_0x00de:
        r1 = TAG;	 Catch:{ all -> 0x00fe }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00fe }
        r3.<init>();	 Catch:{ all -> 0x00fe }
        r4 = "Caught unexpected exception in getAttributionId(): ";
        r3.append(r4);	 Catch:{ all -> 0x00fe }
        r10 = r10.toString();	 Catch:{ all -> 0x00fe }
        r3.append(r10);	 Catch:{ all -> 0x00fe }
        r10 = r3.toString();	 Catch:{ all -> 0x00fe }
        android.util.Log.d(r1, r10);	 Catch:{ all -> 0x00fe }
        if (r0 == 0) goto L_0x00fd;
    L_0x00fa:
        r0.close();
    L_0x00fd:
        return r2;
    L_0x00fe:
        r10 = move-exception;
        r2 = r0;
    L_0x0100:
        if (r2 == 0) goto L_0x0105;
    L_0x0102:
        r2.close();
    L_0x0105:
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(android.content.Context):com.facebook.internal.AttributionIdentifiers");
    }

    private static AttributionIdentifiers cacheAndReturnIdentifiers(AttributionIdentifiers attributionIdentifiers) {
        attributionIdentifiers.fetchTime = System.currentTimeMillis();
        recentlyFetchedIdentifiers = attributionIdentifiers;
        return attributionIdentifiers;
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public String getAndroidAdvertiserId() {
        return this.androidAdvertiserId;
    }

    public String getAndroidInstallerPackage() {
        return this.androidInstallerPackage;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }

    @Nullable
    private static String getInstallerPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null ? packageManager.getInstallerPackageName(context.getPackageName()) : null;
    }
}
