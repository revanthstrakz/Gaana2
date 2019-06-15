package com.iabutils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

public class IabHelper {
    boolean a = false;
    String b = "IabHelper";
    boolean c = false;
    boolean d = false;
    boolean e = false;
    boolean f = false;
    boolean g = false;
    boolean h = false;
    String i = "";
    Context j;
    IInAppBillingService k;
    ServiceConnection l;
    int m;
    String n;
    String o = null;
    a p;
    private final Object q = new Object();

    public static class IabAsyncInProgressException extends Exception {
        public IabAsyncInProgressException(String str) {
            super(str);
        }
    }

    public interface a {
        void a(a aVar, c cVar);
    }

    public interface b {
        void a(a aVar);
    }

    public interface c {
        void a(a aVar, b bVar);
    }

    public void c(String str) {
    }

    public IabHelper(Context context, String str) {
        this.j = context.getApplicationContext();
        this.o = str;
        c("IAB helper created.");
    }

    public void a(boolean z) {
        d();
        this.a = z;
    }

    public void a(final b bVar) {
        d();
        if (this.c) {
            throw new IllegalStateException("IAB helper is already set up.");
        }
        c("Starting in-app billing setup.");
        this.l = new ServiceConnection() {
            public void onServiceDisconnected(ComponentName componentName) {
                IabHelper.this.c("Billing service disconnected.");
                IabHelper.this.k = null;
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                if (!IabHelper.this.d) {
                    IabHelper.this.c("Billing service connected.");
                    IabHelper.this.k = com.android.vending.billing.IInAppBillingService.a.a(iBinder);
                    String packageName = IabHelper.this.j.getPackageName();
                    try {
                        IabHelper.this.c("Checking for in-app billing 3 support.");
                        int a = IabHelper.this.k.a(3, packageName, "inapp");
                        if (a != 0) {
                            if (bVar != null) {
                                bVar.a(new a(a, "Error checking for billing v3 support."));
                            }
                            IabHelper.this.f = false;
                            IabHelper.this.g = false;
                            return;
                        }
                        IabHelper iabHelper = IabHelper.this;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("In-app billing version 3 supported for ");
                        stringBuilder.append(packageName);
                        iabHelper.c(stringBuilder.toString());
                        if (IabHelper.this.k.a(5, packageName, "subs") == 0) {
                            IabHelper.this.c("Subscription re-signup AVAILABLE.");
                            IabHelper.this.g = true;
                        } else {
                            IabHelper.this.c("Subscription re-signup not available.");
                            IabHelper.this.g = false;
                        }
                        if (IabHelper.this.g) {
                            IabHelper.this.f = true;
                        } else {
                            int a2 = IabHelper.this.k.a(3, packageName, "subs");
                            if (a2 == 0) {
                                IabHelper.this.c("Subscriptions AVAILABLE.");
                                IabHelper.this.f = true;
                            } else {
                                iabHelper = IabHelper.this;
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Subscriptions NOT AVAILABLE. Response: ");
                                stringBuilder2.append(a2);
                                iabHelper.c(stringBuilder2.toString());
                                IabHelper.this.f = false;
                                IabHelper.this.g = false;
                            }
                        }
                        IabHelper.this.c = true;
                        if (bVar != null) {
                            bVar.a(new a(0, "Setup successful."));
                        }
                    } catch (RemoteException e) {
                        if (bVar != null) {
                            bVar.a(new a(-1001, "RemoteException while setting up in-app billing."));
                        }
                        ThrowableExtension.printStackTrace(e);
                    }
                }
            }
        };
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        List queryIntentServices = this.j.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
            this.j.bindService(intent, this.l, 1);
        } else if (bVar != null) {
            bVar.a(new a(3, "Billing service unavailable on device."));
        }
    }

    public void a() throws IabAsyncInProgressException {
        synchronized (this.q) {
            if (this.h) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Can't dispose because an async operation (");
                stringBuilder.append(this.i);
                stringBuilder.append(") is in progress.");
                throw new IabAsyncInProgressException(stringBuilder.toString());
            }
        }
        c("Disposing.");
        this.c = false;
        if (this.l != null) {
            c("Unbinding from service.");
            if (this.j != null) {
                this.j.unbindService(this.l);
            }
        }
        this.d = true;
        this.j = null;
        this.l = null;
        this.k = null;
        this.p = null;
    }

    private void d() {
        if (this.d) {
            throw new IllegalStateException("IabHelper was disposed of, so it cannot be used.");
        }
    }

    public boolean b() {
        d();
        return this.f;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ee A:{Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c6 A:{Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }} */
    public void a(android.app.Activity r16, java.lang.String r17, java.lang.String r18, java.util.ArrayList<java.lang.String> r19, int r20, com.iabutils.IabHelper.a r21, java.lang.String r22) throws com.iabutils.IabHelper.IabAsyncInProgressException {
        /*
        r15 = this;
        r1 = r15;
        r9 = r17;
        r10 = r18;
        r11 = r19;
        r12 = r20;
        r13 = r21;
        r1.d();
        r2 = "launchPurchaseFlow";
        r1.a(r2);
        r2 = "launchPurchaseFlow";
        r1.b(r2);
        r2 = "subs";
        r2 = r10.equals(r2);
        r14 = 0;
        if (r2 == 0) goto L_0x0037;
    L_0x0021:
        r2 = r1.f;
        if (r2 != 0) goto L_0x0037;
    L_0x0025:
        r2 = new com.iabutils.a;
        r3 = -1009; // 0xfffffffffffffc0f float:NaN double:NaN;
        r4 = "Subscriptions are not available.";
        r2.<init>(r3, r4);
        r1.c();
        if (r13 == 0) goto L_0x0036;
    L_0x0033:
        r13.a(r2, r14);
    L_0x0036:
        return;
    L_0x0037:
        r2 = new java.lang.StringBuilder;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2.<init>();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3 = "Constructing buy intent for ";
        r2.append(r3);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2.append(r9);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3 = ", item type: ";
        r2.append(r3);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2.append(r10);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2 = r2.toString();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r1.c(r2);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        if (r11 == 0) goto L_0x00aa;
    L_0x0055:
        r2 = r19.isEmpty();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        if (r2 == 0) goto L_0x005c;
    L_0x005b:
        goto L_0x00aa;
    L_0x005c:
        r2 = r1.g;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        if (r2 != 0) goto L_0x0072;
    L_0x0060:
        r2 = new com.iabutils.a;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3 = -1011; // 0xfffffffffffffc0d float:NaN double:NaN;
        r4 = "Subscription updates are not available.";
        r2.<init>(r3, r4);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r1.c();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        if (r13 == 0) goto L_0x0071;
    L_0x006e:
        r13.a(r2, r14);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
    L_0x0071:
        return;
    L_0x0072:
        r8 = new android.os.Bundle;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r8.<init>();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2 = "skusToReplace";
        r8.putStringArrayList(r2, r11);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2 = r1.k;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3 = 7;
        r4 = r1.j;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r4 = r4.getPackageName();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r5 = r9;
        r6 = r10;
        r7 = r22;
        r2 = r2.a(r3, r4, r5, r6, r7, r8);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3 = new java.lang.StringBuilder;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3.<init>();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r4 = "Purchase upgrade/dpwn grade flow ";
        r3.append(r4);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        if (r11 != 0) goto L_0x009a;
    L_0x0099:
        goto L_0x009f;
    L_0x009a:
        r4 = r19.toString();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r11 = r4;
    L_0x009f:
        r3.append(r11);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3 = r3.toString();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r1.c(r3);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        goto L_0x00c0;
    L_0x00aa:
        r2 = r1.k;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3 = 3;
        r4 = r1.j;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r4 = r4.getPackageName();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r5 = r9;
        r6 = r10;
        r7 = r22;
        r2 = r2.a(r3, r4, r5, r6, r7);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3 = "Purchase fresh flow";
        r1.c(r3);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
    L_0x00c0:
        r3 = r1.a(r2);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        if (r3 == 0) goto L_0x00ee;
    L_0x00c6:
        r2 = new java.lang.StringBuilder;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2.<init>();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r4 = "Unable to buy item, Error response: ";
        r2.append(r4);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r4 = a(r3);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2.append(r4);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2 = r2.toString();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r1.d(r2);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r1.c();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2 = new com.iabutils.a;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r4 = "Unable to buy item";
        r2.<init>(r3, r4);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        if (r13 == 0) goto L_0x00ed;
    L_0x00ea:
        r13.a(r2, r14);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
    L_0x00ed:
        return;
    L_0x00ee:
        r3 = "BUY_INTENT";
        r2 = r2.getParcelable(r3);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2 = (android.app.PendingIntent) r2;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3 = new java.lang.StringBuilder;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3.<init>();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r4 = "Launching buy intent for ";
        r3.append(r4);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3.append(r9);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r4 = ". Request code: ";
        r3.append(r4);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3.append(r12);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3 = r3.toString();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r1.c(r3);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r1.m = r12;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r1.p = r13;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r1.n = r10;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r3 = r2.getIntentSender();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r5 = new android.content.Intent;	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r5.<init>();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2 = 0;
        r4 = java.lang.Integer.valueOf(r2);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r6 = r4.intValue();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r4 = java.lang.Integer.valueOf(r2);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r7 = r4.intValue();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r8 = r2.intValue();	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        r2 = r16;
        r4 = r12;
        r2.startIntentSenderForResult(r3, r4, r5, r6, r7, r8);	 Catch:{ SendIntentException -> 0x016c, RemoteException -> 0x0141 }
        goto L_0x0196;
    L_0x0141:
        r0 = move-exception;
        r2 = r0;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "RemoteException while launching purchase flow for sku ";
        r3.append(r4);
        r3.append(r9);
        r3 = r3.toString();
        r1.d(r3);
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);
        r1.c();
        r2 = new com.iabutils.a;
        r3 = -1001; // 0xfffffffffffffc17 float:NaN double:NaN;
        r4 = "Remote exception while starting purchase flow";
        r2.<init>(r3, r4);
        if (r13 == 0) goto L_0x0196;
    L_0x0168:
        r13.a(r2, r14);
        goto L_0x0196;
    L_0x016c:
        r0 = move-exception;
        r2 = r0;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "SendIntentException while launching purchase flow for sku ";
        r3.append(r4);
        r3.append(r9);
        r3 = r3.toString();
        r1.d(r3);
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);
        r1.c();
        r2 = new com.iabutils.a;
        r3 = -1004; // 0xfffffffffffffc14 float:NaN double:NaN;
        r4 = "Failed to send intent.";
        r2.<init>(r3, r4);
        if (r13 == 0) goto L_0x0196;
    L_0x0193:
        r13.a(r2, r14);
    L_0x0196:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iabutils.IabHelper.a(android.app.Activity, java.lang.String, java.lang.String, java.util.ArrayList, int, com.iabutils.IabHelper$a, java.lang.String):void");
    }

    public boolean a(int i, int i2, Intent intent) {
        if (i != this.m) {
            return false;
        }
        d();
        a("handleActivityResult");
        c();
        a aVar;
        if (intent == null) {
            d("Null data in IAB activity result.");
            aVar = new a(-1002, "Null data in IAB result");
            if (this.p != null) {
                this.p.a(aVar, null);
            }
            return true;
        }
        int a = a(intent);
        String stringExtra = intent.getStringExtra("INAPP_PURCHASE_DATA");
        String stringExtra2 = intent.getStringExtra("INAPP_DATA_SIGNATURE");
        StringBuilder stringBuilder;
        a aVar2;
        if (i2 == -1 && a == 0) {
            c("Successful resultcode from purchase activity.");
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Purchase data: ");
            stringBuilder2.append(stringExtra);
            c(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Data signature: ");
            stringBuilder2.append(stringExtra2);
            c(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Extras: ");
            stringBuilder2.append(intent.getExtras());
            c(stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Expected item type: ");
            stringBuilder2.append(this.n);
            c(stringBuilder2.toString());
            if (stringExtra == null || stringExtra2 == null) {
                d("BUG: either purchaseData or dataSignature is null.");
                stringBuilder = new StringBuilder();
                stringBuilder.append("Extras: ");
                stringBuilder.append(intent.getExtras().toString());
                c(stringBuilder.toString());
                aVar2 = new a(-1008, "IAB returned null purchaseData or dataSignature");
                if (this.p != null) {
                    this.p.a(aVar2, null);
                }
                return true;
            }
            try {
                c cVar = new c(this.n, stringExtra, stringExtra2);
                String c = cVar.c();
                if (d.a(this.o, stringExtra, stringExtra2)) {
                    c("Purchase signature successfully verified.");
                    if (this.p != null) {
                        this.p.a(new a(0, "Success"), cVar);
                    }
                } else {
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Purchase signature verification FAILED for sku ");
                    stringBuilder3.append(c);
                    d(stringBuilder3.toString());
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append("Signature verification failed for sku ");
                    stringBuilder4.append(c);
                    a aVar3 = new a(-1003, stringBuilder4.toString());
                    if (this.p != null) {
                        this.p.a(aVar3, cVar);
                    }
                    return true;
                }
            } catch (JSONException e) {
                d("Failed to parse purchase data.");
                ThrowableExtension.printStackTrace(e);
                aVar = new a(-1002, "Failed to parse purchase data.");
                if (this.p != null) {
                    this.p.a(aVar, null);
                }
                return true;
            }
        } else if (i2 == -1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Result code was OK but in-app billing response was not OK: ");
            stringBuilder.append(a(a));
            c(stringBuilder.toString());
            if (this.p != null) {
                this.p.a(new a(a, "Problem purchashing item."), null);
            }
        } else if (i2 == 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Purchase canceled - Response: ");
            stringBuilder.append(a(a));
            c(stringBuilder.toString());
            aVar2 = new a(-1005, "User canceled.");
            if (this.p != null) {
                this.p.a(aVar2, null);
            }
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Purchase failed. Result code: ");
            stringBuilder.append(Integer.toString(i2));
            stringBuilder.append(". Response: ");
            stringBuilder.append(a(a));
            d(stringBuilder.toString());
            aVar2 = new a(-1006, "Unknown purchase response.");
            if (this.p != null) {
                this.p.a(aVar2, null);
            }
        }
        return true;
    }

    public b a(boolean z, List<String> list, List<String> list2) throws IabException {
        d();
        a("queryInventory");
        try {
            b bVar = new b();
            int a = a(bVar, "inapp");
            if (a != 0) {
                throw new IabException(a, "Error refreshing inventory (querying owned items).");
            }
            int a2;
            if (z) {
                a2 = a("inapp", bVar, (List) list);
                if (a2 != 0) {
                    throw new IabException(a2, "Error refreshing inventory (querying prices of items).");
                }
            }
            if (this.f) {
                a2 = a(bVar, "subs");
                if (a2 != 0) {
                    throw new IabException(a2, "Error refreshing inventory (querying owned subscriptions).");
                } else if (z) {
                    int a3 = a("subs", bVar, (List) list2);
                    if (a3 != 0) {
                        throw new IabException(a3, "Error refreshing inventory (querying prices of subscriptions).");
                    }
                }
            }
            return bVar;
        } catch (RemoteException e) {
            throw new IabException(-1001, "Remote exception while refreshing inventory.", e);
        } catch (JSONException e2) {
            throw new IabException(-1002, "Error parsing JSON response while refreshing inventory.", e2);
        }
    }

    public void a(boolean z, List<String> list, List<String> list2, c cVar) throws IabAsyncInProgressException {
        final Handler handler = new Handler();
        d();
        a("queryInventory");
        b("refresh inventory");
        final boolean z2 = z;
        final List<String> list3 = list;
        final List<String> list4 = list2;
        final c cVar2 = cVar;
        new Thread(new Runnable() {
            public void run() {
                b a;
                a aVar = new a(0, "Inventory refresh successful.");
                try {
                    a = IabHelper.this.a(z2, list3, list4);
                } catch (IabException e) {
                    aVar = e.a();
                    a = null;
                }
                IabHelper.this.c();
                if (!IabHelper.this.d && cVar2 != null) {
                    handler.post(new Runnable() {
                        public void run() {
                            cVar2.a(aVar, a);
                        }
                    });
                }
            }
        }).start();
    }

    public static String a(int i) {
        String[] split = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split("/");
        String[] split2 = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt".split("/");
        StringBuilder stringBuilder;
        if (i <= -1000) {
            int i2 = -1000 - i;
            if (i2 >= 0 && i2 < split2.length) {
                return split2[i2];
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(i));
            stringBuilder.append(":Unknown IAB Helper Error");
            return stringBuilder.toString();
        } else if (i >= 0 && i < split.length) {
            return split[i];
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(i));
            stringBuilder.append(":Unknown");
            return stringBuilder.toString();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str) {
        if (!this.c) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Illegal state for operation (");
            stringBuilder.append(str);
            stringBuilder.append("): IAB helper is not set up.");
            d(stringBuilder.toString());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("IAB helper is not set up. Can't perform operation: ");
            stringBuilder2.append(str);
            throw new IllegalStateException(stringBuilder2.toString());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int a(Bundle bundle) {
        Object obj = bundle.get("RESPONSE_CODE");
        if (obj == null) {
            c("Bundle with null response code, assuming OK (known issue)");
            return 0;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            d("Unexpected type for bundle response code.");
            d(obj.getClass().getName());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected type for bundle response code: ");
            stringBuilder.append(obj.getClass().getName());
            throw new RuntimeException(stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public int a(Intent intent) {
        Object obj = intent.getExtras().get("RESPONSE_CODE");
        if (obj == null) {
            d("Intent with no response code, assuming OK (known issue)");
            return 0;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            d("Unexpected type for intent response code.");
            d(obj.getClass().getName());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected type for intent response code: ");
            stringBuilder.append(obj.getClass().getName());
            throw new RuntimeException(stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b(String str) throws IabAsyncInProgressException {
        synchronized (this.q) {
            if (this.h) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Can't start async operation (");
                stringBuilder.append(str);
                stringBuilder.append(") because another async operation (");
                stringBuilder.append(this.i);
                stringBuilder.append(") is in progress.");
                throw new IabAsyncInProgressException(stringBuilder.toString());
            }
            this.i = str;
            this.h = true;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Starting async operation: ");
            stringBuilder2.append(str);
            c(stringBuilder2.toString());
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0027 */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(2:5|6)|7|8) */
    public void c() {
        /*
        r3 = this;
        r0 = r3.q;
        monitor-enter(r0);
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0029 }
        r1.<init>();	 Catch:{ all -> 0x0029 }
        r2 = "Ending async operation: ";
        r1.append(r2);	 Catch:{ all -> 0x0029 }
        r2 = r3.i;	 Catch:{ all -> 0x0029 }
        r1.append(r2);	 Catch:{ all -> 0x0029 }
        r1 = r1.toString();	 Catch:{ all -> 0x0029 }
        r3.c(r1);	 Catch:{ all -> 0x0029 }
        r1 = "";
        r3.i = r1;	 Catch:{ all -> 0x0029 }
        r1 = 0;
        r3.h = r1;	 Catch:{ all -> 0x0029 }
        r1 = r3.e;	 Catch:{ all -> 0x0029 }
        if (r1 == 0) goto L_0x0027;
    L_0x0024:
        r3.a();	 Catch:{ IabAsyncInProgressException -> 0x0027 }
    L_0x0027:
        monitor-exit(r0);	 Catch:{ all -> 0x0029 }
        return;
    L_0x0029:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0029 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iabutils.IabHelper.c():void");
    }

    /* Access modifiers changed, original: 0000 */
    public int a(b bVar, String str) throws JSONException, RemoteException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Querying owned items, item type: ");
        stringBuilder.append(str);
        c(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("Package name: ");
        stringBuilder.append(this.j.getPackageName());
        c(stringBuilder.toString());
        int i = 0;
        String str2 = null;
        int i2 = 0;
        while (true) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Calling getPurchases with continuation token: ");
            stringBuilder2.append(str2);
            c(stringBuilder2.toString());
            Bundle a = this.k.a(3, this.j.getPackageName(), str, str2);
            int a2 = a(a);
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Owned items response: ");
            stringBuilder3.append(String.valueOf(a2));
            c(stringBuilder3.toString());
            if (a2 != 0) {
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append("getPurchases() failed: ");
                stringBuilder4.append(a(a2));
                c(stringBuilder4.toString());
                return a2;
            } else if (a.containsKey("INAPP_PURCHASE_ITEM_LIST") && a.containsKey("INAPP_PURCHASE_DATA_LIST") && a.containsKey("INAPP_DATA_SIGNATURE_LIST")) {
                ArrayList stringArrayList = a.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList stringArrayList2 = a.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList stringArrayList3 = a.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                int i3 = i2;
                for (i2 = 0; i2 < stringArrayList2.size(); i2++) {
                    String str3 = (String) stringArrayList2.get(i2);
                    String str4 = (String) stringArrayList3.get(i2);
                    String str5 = (String) stringArrayList.get(i2);
                    if (d.a(this.o, str3, str4)) {
                        StringBuilder stringBuilder5 = new StringBuilder();
                        stringBuilder5.append("Sku is owned: ");
                        stringBuilder5.append(str5);
                        c(stringBuilder5.toString());
                        c cVar = new c(str, str3, str4);
                        if (TextUtils.isEmpty(cVar.e())) {
                            e("BUG: empty/null token!");
                            StringBuilder stringBuilder6 = new StringBuilder();
                            stringBuilder6.append("Purchase data: ");
                            stringBuilder6.append(str3);
                            c(stringBuilder6.toString());
                        }
                        bVar.a(cVar);
                    } else {
                        e("Purchase signature verification **FAILED**. Not adding item.");
                        StringBuilder stringBuilder7 = new StringBuilder();
                        stringBuilder7.append("   Purchase data: ");
                        stringBuilder7.append(str3);
                        c(stringBuilder7.toString());
                        stringBuilder7 = new StringBuilder();
                        stringBuilder7.append("   Signature: ");
                        stringBuilder7.append(str4);
                        c(stringBuilder7.toString());
                        i3 = 1;
                    }
                }
                str2 = a.getString("INAPP_CONTINUATION_TOKEN");
                StringBuilder stringBuilder8 = new StringBuilder();
                stringBuilder8.append("Continuation token: ");
                stringBuilder8.append(str2);
                c(stringBuilder8.toString());
                if (TextUtils.isEmpty(str2)) {
                    if (i3 != 0) {
                        i = -1003;
                    }
                    return i;
                }
                i2 = i3;
            } else {
                d("Bundle returned from getPurchases() doesn't contain required fields.");
            }
        }
        d("Bundle returned from getPurchases() doesn't contain required fields.");
        return -1002;
    }

    /* Access modifiers changed, original: 0000 */
    public int a(String str, b bVar, List<String> list) throws RemoteException, JSONException {
        Iterator it;
        c("Querying SKU details.");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(bVar.d(str));
        if (list != null) {
            for (String str2 : list) {
                if (!arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        if (arrayList.size() == 0) {
            c("queryPrices: nothing to do because there are no SKUs.");
            return 0;
        }
        Iterator it2;
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size() / 20;
        int size2 = arrayList.size() % 20;
        for (int i = 0; i < size; i++) {
            ArrayList arrayList3 = new ArrayList();
            int i2 = i * 20;
            for (String add : arrayList.subList(i2, i2 + 20)) {
                arrayList3.add(add);
            }
            arrayList2.add(arrayList3);
        }
        if (size2 != 0) {
            ArrayList arrayList4 = new ArrayList();
            size *= 20;
            for (String add2 : arrayList.subList(size, size2 + size)) {
                arrayList4.add(add2);
            }
            arrayList2.add(arrayList4);
        }
        it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList = (ArrayList) it.next();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", arrayList);
            Bundle a = this.k.a(3, this.j.getPackageName(), str, bundle);
            if (a.containsKey("DETAILS_LIST")) {
                it2 = a.getStringArrayList("DETAILS_LIST").iterator();
                while (it2.hasNext()) {
                    e eVar = new e(str, (String) it2.next());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Got sku details: ");
                    stringBuilder.append(eVar);
                    c(stringBuilder.toString());
                    bVar.a(eVar);
                }
            } else {
                int a2 = a(a);
                if (a2 != 0) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("getSkuDetails() failed: ");
                    stringBuilder2.append(a(a2));
                    c(stringBuilder2.toString());
                    return a2;
                }
                d("getSkuDetails() returned a bundle with neither an error nor a detail list.");
                return -1002;
            }
        }
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public void d(String str) {
        String str2 = this.b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("In-app billing error: ");
        stringBuilder.append(str);
        Log.e(str2, stringBuilder.toString());
    }

    /* Access modifiers changed, original: 0000 */
    public void e(String str) {
        String str2 = this.b;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("In-app billing warning: ");
        stringBuilder.append(str);
        Log.w(str2, stringBuilder.toString());
    }
}
