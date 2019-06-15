package com.simpl.android.fingerprint.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.concurrent.LinkedBlockingQueue;

final class a {

    static final class a implements ServiceConnection {
        boolean a;
        final LinkedBlockingQueue<IBinder> b;

        private a() {
            this.a = false;
            this.b = new LinkedBlockingQueue(1);
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        public final void onServiceDisconnected(ComponentName componentName) {
        }
    }

    static class b extends AsyncTask<Void, Void, String> {
        private c a;
        private Context b;

        b(Context context, c cVar) {
            this.b = context;
            this.a = cVar;
        }

        private String a() {
            a aVar = new a();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            if (!this.b.bindService(intent, aVar, 1)) {
                return "p_disabled/p_not_avail";
            }
            String a;
            try {
                if (aVar.a) {
                    throw new IllegalStateException();
                }
                aVar.a = true;
                a = new d((IBinder) aVar.b.take()).a();
                this.b.unbindService(aVar);
                return a;
            } catch (Exception e) {
                a = e.getMessage();
            } catch (Throwable th) {
                this.b.unbindService(aVar);
            }
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            super.onPostExecute(str);
            this.a.a(str);
        }
    }

    interface c {
        void a(String str);
    }

    static final class d implements IInterface {
        private IBinder a;

        public d(IBinder iBinder) {
            this.a = iBinder;
        }

        public final String a() {
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

        public final IBinder asBinder() {
            return this.a;
        }
    }
}
