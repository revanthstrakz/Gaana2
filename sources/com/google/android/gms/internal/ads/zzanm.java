package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.mediation.zzb;
import com.google.android.gms.ads.zzc;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzanm extends zzanh {
    private final zzbit zzdon;

    public zzanm(zzbit zzbit) {
        this.zzdon = zzbit;
    }

    public final void zza(String str, String str2, Bundle bundle, IObjectWrapper iObjectWrapper, zzamy zzamy, zzalm zzalm, zzwf zzwf) throws RemoteException {
        try {
            zzann zzann = new zzann(this, zzamy, zzalm);
            zzbit zzbit = this.zzdon;
            zzbis zzbis = new zzbis((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzcx(str2), bundle);
            zzc.zza(zzwf.width, zzwf.height, zzwf.zzckk);
            zzann.zzbw(String.valueOf(zzbit.getClass().getSimpleName()).concat(" does not support banner ads."));
        } catch (Throwable th) {
            zzbbd.zzb("Adapter failed to render banner ad.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void zza(String str, String str2, Bundle bundle, IObjectWrapper iObjectWrapper, zzana zzana, zzalm zzalm) throws RemoteException {
        try {
            zzano zzano = new zzano(this, zzana, zzalm);
            zzbit zzbit = this.zzdon;
            zzbis zzbis = new zzbis((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzcx(str2), bundle);
            zzano.zzbw(String.valueOf(zzbit.getClass().getSimpleName()).concat(" does not support interstitial ads."));
        } catch (Throwable th) {
            zzbbd.zzb("Adapter failed to render interstitial ad.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void zza(String str, String str2, Bundle bundle, IObjectWrapper iObjectWrapper, zzane zzane, zzalm zzalm) throws RemoteException {
        try {
            zzanp zzanp = new zzanp(this, zzane, zzalm);
            zzbit zzbit = this.zzdon;
            zzbis zzbis = new zzbis((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzcx(str2), bundle);
            zzanp.zzbw(String.valueOf(zzbit.getClass().getSimpleName()).concat(" does not support rewarded ads."));
        } catch (Throwable th) {
            zzbbd.zzb("Adapter failed to render rewarded ad.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void zza(String str, String str2, Bundle bundle, IObjectWrapper iObjectWrapper, zzanc zzanc, zzalm zzalm) throws RemoteException {
        try {
            zzanq zzanq = new zzanq(this, zzanc, zzalm);
            zzbit zzbit = this.zzdon;
            zzbis zzbis = new zzbis((Context) ObjectWrapper.unwrap(iObjectWrapper), str, zzcx(str2), bundle);
            zzanq.zzbw(String.valueOf(zzbit.getClass().getSimpleName()).concat(" does not support native ads."));
        } catch (Throwable th) {
            zzbbd.zzb("Adapter failed to render rewarded ad.", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void showInterstitial() throws RemoteException {
        zzbiq zzbiq = null;
        try {
            zzbiq.zzxh();
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final void zzvk() throws RemoteException {
        zzbir zzbir = null;
        try {
            zzbir.zzxh();
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final zzyp getVideoController() {
        if (!(this.zzdon instanceof zzb)) {
            return null;
        }
        try {
            return ((zzb) this.zzdon).getVideoController();
        } catch (Throwable th) {
            zzbbd.zzb("", th);
            return null;
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, String str, Bundle bundle, Bundle bundle2, zzwf zzwf, zzanj zzanj) throws RemoteException {
        try {
            zzanr zzanr = new zzanr(this, zzanj);
            this.zzdon.zza(new zzbiu((Context) ObjectWrapper.unwrap(iObjectWrapper), new zza(zzcw(str), bundle2), bundle, zzc.zza(zzwf.width, zzwf.height, zzwf.zzckk)), zzanr);
        } catch (Throwable th) {
            zzbbd.zzb("Error generating signals for RTB", th);
            RemoteException remoteException = new RemoteException();
        }
    }

    public final zzans zzvi() throws RemoteException {
        return zzans.zza(this.zzdon.zzafj());
    }

    public final zzans zzvj() throws RemoteException {
        return zzans.zza(this.zzdon.zzafi());
    }

    public final void zzn(IObjectWrapper iObjectWrapper) throws RemoteException {
        this.zzdon.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zza(String[] strArr, Bundle[] bundleArr) throws RemoteException {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < strArr.length) {
            try {
                arrayList.add(new zza(zzcw(strArr[i]), bundleArr[i]));
                i++;
            } catch (IndexOutOfBoundsException unused) {
                throw new RemoteException();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004d  */
    private static int zzcw(java.lang.String r2) {
        /*
        r0 = r2.hashCode();
        r1 = -1396342996; // 0xffffffffacc57f2c float:-5.6131957E-12 double:NaN;
        if (r0 == r1) goto L_0x0037;
    L_0x0009:
        r1 = -1052618729; // 0xffffffffc1425017 float:-12.144553 double:NaN;
        if (r0 == r1) goto L_0x002d;
    L_0x000e:
        r1 = -239580146; // 0xfffffffff1b84c0e float:-1.82519E30 double:NaN;
        if (r0 == r1) goto L_0x0023;
    L_0x0013:
        r1 = 604727084; // 0x240b672c float:3.022821E-17 double:2.987748773E-315;
        if (r0 == r1) goto L_0x0019;
    L_0x0018:
        goto L_0x0041;
    L_0x0019:
        r0 = "interstitial";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0041;
    L_0x0021:
        r2 = 1;
        goto L_0x0042;
    L_0x0023:
        r0 = "rewarded";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0041;
    L_0x002b:
        r2 = 2;
        goto L_0x0042;
    L_0x002d:
        r0 = "native";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0041;
    L_0x0035:
        r2 = 3;
        goto L_0x0042;
    L_0x0037:
        r0 = "banner";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0041;
    L_0x003f:
        r2 = 0;
        goto L_0x0042;
    L_0x0041:
        r2 = -1;
    L_0x0042:
        switch(r2) {
            case 0: goto L_0x0056;
            case 1: goto L_0x0053;
            case 2: goto L_0x0050;
            case 3: goto L_0x004d;
            default: goto L_0x0045;
        };
    L_0x0045:
        r2 = new java.lang.IllegalArgumentException;
        r0 = "Internal Error";
        r2.<init>(r0);
        throw r2;
    L_0x004d:
        r2 = com.google.android.gms.ads.zza.zzvl;
        return r2;
    L_0x0050:
        r2 = com.google.android.gms.ads.zza.zzvk;
        return r2;
    L_0x0053:
        r2 = com.google.android.gms.ads.zza.zzvj;
        return r2;
    L_0x0056:
        r2 = com.google.android.gms.ads.zza.zzvi;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzanm.zzcw(java.lang.String):int");
    }

    private static Bundle zzcx(String str) throws RemoteException {
        String str2 = "Server parameters: ";
        String valueOf = String.valueOf(str);
        zzbbd.zzeo(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        try {
            Bundle bundle = new Bundle();
            if (str == null) {
                return bundle;
            }
            JSONObject jSONObject = new JSONObject(str);
            Bundle bundle2 = new Bundle();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                bundle2.putString(str3, jSONObject.getString(str3));
            }
            return bundle2;
        } catch (JSONException e) {
            zzbbd.zzb("", e);
            throw new RemoteException();
        }
    }
}
