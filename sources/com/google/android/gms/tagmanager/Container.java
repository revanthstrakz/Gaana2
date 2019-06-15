package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzl;
import com.google.android.gms.internal.measurement.zzn;
import com.google.android.gms.internal.measurement.zzo;
import com.google.android.gms.internal.measurement.zzp;
import com.google.android.gms.internal.measurement.zzrg;
import com.google.android.gms.internal.measurement.zzrk;
import com.google.android.gms.internal.measurement.zzro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
public class Container {
    private final String zzazo;
    private final DataLayer zzazp;
    private zzfb zzazq;
    private Map<String, FunctionCallMacroCallback> zzazr = new HashMap();
    private Map<String, FunctionCallTagCallback> zzazs = new HashMap();
    private volatile long zzazt;
    private volatile String zzazu = "";
    private final Context zzri;

    public interface FunctionCallMacroCallback {
        Object getValue(String str, Map<String, Object> map);
    }

    public interface FunctionCallTagCallback {
        void execute(String str, Map<String, Object> map);
    }

    class zza implements zzan {
        private zza() {
        }

        public final Object zza(String str, Map<String, Object> map) {
            FunctionCallMacroCallback zzdc = Container.this.zzdc(str);
            if (zzdc == null) {
                return null;
            }
            return zzdc.getValue(str, map);
        }

        /* synthetic */ zza(Container container, zzu zzu) {
            this();
        }
    }

    class zzb implements zzan {
        private zzb() {
        }

        public final Object zza(String str, Map<String, Object> map) {
            FunctionCallTagCallback zzdd = Container.this.zzdd(str);
            if (zzdd != null) {
                zzdd.execute(str, map);
            }
            return zzgj.zzqp();
        }

        /* synthetic */ zzb(Container container, zzu zzu) {
            this();
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzrk zzrk) {
        this.zzri = context;
        this.zzazp = dataLayer;
        this.zzazo = str;
        this.zzazt = 0;
        zza(zzrk);
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzo zzo) {
        this.zzri = context;
        this.zzazp = dataLayer;
        this.zzazo = str;
        this.zzazt = j;
        zzl zzl = zzo.zzqg;
        if (zzl == null) {
            throw new NullPointerException();
        }
        try {
            zza(zzrg.zza(zzl));
        } catch (zzro e) {
            String valueOf = String.valueOf(zzl);
            String zzro = e.toString();
            StringBuilder stringBuilder = new StringBuilder((46 + String.valueOf(valueOf).length()) + String.valueOf(zzro).length());
            stringBuilder.append("Not loading resource: ");
            stringBuilder.append(valueOf);
            stringBuilder.append(" because it is invalid: ");
            stringBuilder.append(zzro);
            zzdi.e(stringBuilder.toString());
        }
        if (zzo.zzqf != null) {
            zzn[] zznArr = zzo.zzqf;
            ArrayList arrayList = new ArrayList();
            for (Object add : zznArr) {
                arrayList.add(add);
            }
            zznp().zzg(arrayList);
        }
    }

    public String getContainerId() {
        return this.zzazo;
    }

    public boolean getBoolean(String str) {
        zzfb zznp = zznp();
        if (zznp == null) {
            zzdi.e("getBoolean called for closed container.");
            return zzgj.zzqn().booleanValue();
        }
        try {
            return zzgj.zzg((zzp) zznp.zzdz(str).getObject()).booleanValue();
        } catch (Exception e) {
            str = e.getMessage();
            StringBuilder stringBuilder = new StringBuilder(66 + String.valueOf(str).length());
            stringBuilder.append("Calling getBoolean() threw an exception: ");
            stringBuilder.append(str);
            stringBuilder.append(" Returning default value.");
            zzdi.e(stringBuilder.toString());
            return zzgj.zzqn().booleanValue();
        }
    }

    public double getDouble(String str) {
        zzfb zznp = zznp();
        if (zznp == null) {
            zzdi.e("getDouble called for closed container.");
            return zzgj.zzqm().doubleValue();
        }
        try {
            return zzgj.zzf((zzp) zznp.zzdz(str).getObject()).doubleValue();
        } catch (Exception e) {
            str = e.getMessage();
            StringBuilder stringBuilder = new StringBuilder(65 + String.valueOf(str).length());
            stringBuilder.append("Calling getDouble() threw an exception: ");
            stringBuilder.append(str);
            stringBuilder.append(" Returning default value.");
            zzdi.e(stringBuilder.toString());
            return zzgj.zzqm().doubleValue();
        }
    }

    public long getLong(String str) {
        zzfb zznp = zznp();
        if (zznp == null) {
            zzdi.e("getLong called for closed container.");
            return zzgj.zzql().longValue();
        }
        try {
            return zzgj.zze((zzp) zznp.zzdz(str).getObject()).longValue();
        } catch (Exception e) {
            str = e.getMessage();
            StringBuilder stringBuilder = new StringBuilder(63 + String.valueOf(str).length());
            stringBuilder.append("Calling getLong() threw an exception: ");
            stringBuilder.append(str);
            stringBuilder.append(" Returning default value.");
            zzdi.e(stringBuilder.toString());
            return zzgj.zzql().longValue();
        }
    }

    public String getString(String str) {
        zzfb zznp = zznp();
        if (zznp == null) {
            zzdi.e("getString called for closed container.");
            return zzgj.zzqp();
        }
        try {
            return zzgj.zzc((zzp) zznp.zzdz(str).getObject());
        } catch (Exception e) {
            str = e.getMessage();
            StringBuilder stringBuilder = new StringBuilder(65 + String.valueOf(str).length());
            stringBuilder.append("Calling getString() threw an exception: ");
            stringBuilder.append(str);
            stringBuilder.append(" Returning default value.");
            zzdi.e(stringBuilder.toString());
            return zzgj.zzqp();
        }
    }

    public long getLastRefreshTime() {
        return this.zzazt;
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    public void registerFunctionCallMacroCallback(String str, FunctionCallMacroCallback functionCallMacroCallback) {
        if (functionCallMacroCallback == null) {
            throw new NullPointerException("Macro handler must be non-null");
        }
        synchronized (this.zzazr) {
            this.zzazr.put(str, functionCallMacroCallback);
        }
    }

    public void unregisterFunctionCallMacroCallback(String str) {
        synchronized (this.zzazr) {
            this.zzazr.remove(str);
        }
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final FunctionCallMacroCallback zzdc(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.zzazr) {
            functionCallMacroCallback = (FunctionCallMacroCallback) this.zzazr.get(str);
        }
        return functionCallMacroCallback;
    }

    public void registerFunctionCallTagCallback(String str, FunctionCallTagCallback functionCallTagCallback) {
        if (functionCallTagCallback == null) {
            throw new NullPointerException("Tag callback must be non-null");
        }
        synchronized (this.zzazs) {
            this.zzazs.put(str, functionCallTagCallback);
        }
    }

    public void unregisterFunctionCallTagCallback(String str) {
        synchronized (this.zzazs) {
            this.zzazs.remove(str);
        }
    }

    @VisibleForTesting
    public final FunctionCallTagCallback zzdd(String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.zzazs) {
            functionCallTagCallback = (FunctionCallTagCallback) this.zzazs.get(str);
        }
        return functionCallTagCallback;
    }

    @VisibleForTesting
    public final void zzde(String str) {
        zznp().zzde(str);
    }

    @VisibleForTesting
    public final String zzno() {
        return this.zzazu;
    }

    private final void zza(zzrk zzrk) {
        this.zzazu = zzrk.getVersion();
        String str = this.zzazu;
        zzeh.zzpm().zzpn().equals(zza.CONTAINER_DEBUG);
        zzrk zzrk2 = zzrk;
        zza(new zzfb(this.zzri, zzrk2, this.zzazp, new zza(this, null), new zzb(this, null), new zzdq()));
        if (getBoolean("_gtm.loadEventEnabled")) {
            this.zzazp.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.zzazo));
        }
    }

    private final synchronized void zza(zzfb zzfb) {
        this.zzazq = zzfb;
    }

    private final synchronized zzfb zznp() {
        return this.zzazq;
    }

    /* Access modifiers changed, original: final */
    public final void release() {
        this.zzazq = null;
    }
}
