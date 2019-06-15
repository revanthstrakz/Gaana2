package com.android.b.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.finsky.externalreferrer.IGetInstallReferrerService;
import com.google.android.finsky.externalreferrer.IGetInstallReferrerService.Stub;
import java.util.List;

class b extends a {
    private int a = 0;
    private final Context b;
    private IGetInstallReferrerService c;
    private ServiceConnection d;

    private final class a implements ServiceConnection {
        private final c b;

        private a(c cVar) {
            if (cVar == null) {
                throw new RuntimeException("Please specify a listener to know when setup is done.");
            }
            this.b = cVar;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            com.android.b.b.a.a("InstallReferrerClient", "Install Referrer service connected.");
            b.this.c = Stub.asInterface(iBinder);
            b.this.a = 2;
            this.b.onInstallReferrerSetupFinished(0);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            com.android.b.b.a.b("InstallReferrerClient", "Install Referrer service disconnected.");
            b.this.c = null;
            b.this.a = 0;
            this.b.onInstallReferrerServiceDisconnected();
        }
    }

    public b(@NonNull Context context) {
        this.b = context.getApplicationContext();
    }

    public boolean a() {
        return (this.a != 2 || this.c == null || this.d == null) ? false : true;
    }

    public void a(@NonNull c cVar) {
        if (a()) {
            com.android.b.b.a.a("InstallReferrerClient", "Service connection is valid. No need to re-initialize.");
            cVar.onInstallReferrerSetupFinished(0);
        } else if (this.a == 1) {
            com.android.b.b.a.b("InstallReferrerClient", "Client is already in the process of connecting to the service.");
            cVar.onInstallReferrerSetupFinished(3);
        } else if (this.a == 3) {
            com.android.b.b.a.b("InstallReferrerClient", "Client was already closed and can't be reused. Please create another instance.");
            cVar.onInstallReferrerSetupFinished(3);
        } else {
            com.android.b.b.a.a("InstallReferrerClient", "Starting install referrer service setup.");
            this.d = new a(cVar);
            Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
            intent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
            List queryIntentServices = this.b.getPackageManager().queryIntentServices(intent, 0);
            if (!(queryIntentServices == null || queryIntentServices.isEmpty())) {
                ResolveInfo resolveInfo = (ResolveInfo) queryIntentServices.get(0);
                if (resolveInfo.serviceInfo != null) {
                    String str = resolveInfo.serviceInfo.packageName;
                    String str2 = resolveInfo.serviceInfo.name;
                    if ("com.android.vending".equals(str) && str2 != null && d()) {
                        if (this.b.bindService(new Intent(intent), this.d, 1)) {
                            com.android.b.b.a.a("InstallReferrerClient", "Service was bonded successfully.");
                            return;
                        }
                        com.android.b.b.a.b("InstallReferrerClient", "Connection to service is blocked.");
                        this.a = 0;
                        cVar.onInstallReferrerSetupFinished(1);
                        return;
                    }
                    com.android.b.b.a.b("InstallReferrerClient", "Play Store missing or incompatible. Version 8.3.73 or later required.");
                    this.a = 0;
                    cVar.onInstallReferrerSetupFinished(2);
                    return;
                }
            }
            this.a = 0;
            com.android.b.b.a.a("InstallReferrerClient", "Install Referrer service unavailable on device.");
            cVar.onInstallReferrerSetupFinished(2);
        }
    }

    public void b() {
        this.a = 3;
        if (this.d != null) {
            com.android.b.b.a.a("InstallReferrerClient", "Unbinding from service.");
            this.b.unbindService(this.d);
            this.d = null;
        }
        this.c = null;
    }

    public d c() throws RemoteException {
        if (a()) {
            Bundle bundle = new Bundle();
            bundle.putString(InMobiNetworkValues.PACKAGE_NAME, this.b.getPackageName());
            try {
                return new d(this.c.getInstallReferrer(bundle));
            } catch (RemoteException e) {
                com.android.b.b.a.b("InstallReferrerClient", "RemoteException getting install referrer information");
                this.a = 0;
                throw e;
            }
        }
        throw new IllegalStateException("Service not connected. Please start a connection before using the service.");
    }

    private boolean d() {
        boolean z = false;
        try {
            if (this.b.getPackageManager().getPackageInfo("com.android.vending", 128).versionCode >= 80837300) {
                z = true;
            }
            return z;
        } catch (NameNotFoundException unused) {
            return false;
        }
    }
}
