package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.mediation.customevent.CustomEventAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import java.util.Map;

@zzark
public final class zzalf extends zzalh {
    private static final zzanl zzdnu = new zzanl();
    private Map<Class<? extends NetworkExtras>, NetworkExtras> zzdnt;

    public final zzalj zzcp(String str) throws RemoteException {
        return zzcr(str);
    }

    public final boolean zzcq(String str) throws RemoteException {
        try {
            return CustomEvent.class.isAssignableFrom(Class.forName(str, false, zzalf.class.getClassLoader()));
        } catch (Throwable unused) {
            StringBuilder stringBuilder = new StringBuilder(80 + String.valueOf(str).length());
            stringBuilder.append("Could not load custom event implementation class: ");
            stringBuilder.append(str);
            stringBuilder.append(", assuming old implementation.");
            zzbbd.zzeo(stringBuilder.toString());
            return false;
        }
    }

    public final void zzj(Map<Class<? extends NetworkExtras>, NetworkExtras> map) {
        this.zzdnt = map;
    }

    private final <NetworkExtrasT extends com.google.ads.mediation.NetworkExtras, ServerParametersT extends MediationServerParameters> zzalj zzcr(String str) throws RemoteException {
        try {
            Class cls = Class.forName(str, false, zzalf.class.getClassLoader());
            if (MediationAdapter.class.isAssignableFrom(cls)) {
                MediationAdapter mediationAdapter = (MediationAdapter) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                return new zzami(mediationAdapter, (com.google.ads.mediation.NetworkExtras) this.zzdnt.get(mediationAdapter.getAdditionalParametersType()));
            } else if (com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom(cls)) {
                return new zzamd((com.google.android.gms.ads.mediation.MediationAdapter) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
            } else {
                StringBuilder stringBuilder = new StringBuilder(64 + String.valueOf(str).length());
                stringBuilder.append("Could not instantiate mediation adapter: ");
                stringBuilder.append(str);
                stringBuilder.append(" (not a valid adapter).");
                zzbbd.zzeo(stringBuilder.toString());
                throw new RemoteException();
            }
        } catch (Throwable unused) {
            return zzcs(str);
        }
    }

    private final zzalj zzcs(String str) throws RemoteException {
        try {
            zzbbd.zzdn("Reflection failed, retrying using direct instantiation");
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(str)) {
                return new zzamd(new AdMobAdapter());
            }
            if ("com.google.ads.mediation.AdUrlAdapter".equals(str)) {
                return new zzamd(new AdUrlAdapter());
            }
            if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                return new zzamd(new CustomEventAdapter());
            }
            if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
                com.google.ads.mediation.customevent.CustomEventAdapter customEventAdapter = new com.google.ads.mediation.customevent.CustomEventAdapter();
                return new zzami(customEventAdapter, (CustomEventExtras) this.zzdnt.get(customEventAdapter.getAdditionalParametersType()));
            }
            throw new RemoteException();
        } catch (Throwable th) {
            StringBuilder stringBuilder = new StringBuilder(43 + String.valueOf(str).length());
            stringBuilder.append("Could not instantiate mediation adapter: ");
            stringBuilder.append(str);
            stringBuilder.append(". ");
            zzbbd.zzc(stringBuilder.toString(), th);
        }
    }

    public final zzang zzct(String str) throws RemoteException {
        return zzanl.zzcv(str);
    }
}
