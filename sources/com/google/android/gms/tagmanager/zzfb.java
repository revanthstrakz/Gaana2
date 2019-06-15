package com.google.android.gms.tagmanager;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import com.google.android.gms.internal.measurement.zzrg;
import com.google.android.gms.internal.measurement.zzri;
import com.google.android.gms.internal.measurement.zzrk;
import com.google.android.gms.internal.measurement.zzrm;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@VisibleForTesting
final class zzfb {
    private static final zzdz<zzp> zzbez = new zzdz(zzgj.zzqq(), true);
    private final DataLayer zzazp;
    private final zzrk zzbfa;
    private final zzbo zzbfb;
    private final Map<String, zzbq> zzbfc;
    private final Map<String, zzbq> zzbfd;
    private final Map<String, zzbq> zzbfe;
    private final zzp<zzri, zzdz<zzp>> zzbff;
    private final zzp<String, zzfh> zzbfg;
    private final Set<zzrm> zzbfh;
    private final Map<String, zzfi> zzbfi;
    private volatile String zzbfj;
    private int zzbfk;

    public zzfb(Context context, zzrk zzrk, DataLayer dataLayer, zzan zzan, zzan zzan2, zzbo zzbo) {
        if (zzrk == null) {
            throw new NullPointerException("resource cannot be null");
        }
        zzri zzri;
        this.zzbfa = zzrk;
        this.zzbfh = new HashSet(zzrk.zzsg());
        this.zzazp = dataLayer;
        this.zzbfb = zzbo;
        zzfc zzfc = new zzfc(this);
        zzq zzq = new zzq();
        this.zzbff = zzq.zza(1048576, zzfc);
        zzfd zzfd = new zzfd(this);
        zzq zzq2 = new zzq();
        this.zzbfg = zzq.zza(1048576, zzfd);
        this.zzbfc = new HashMap();
        zzb(new zzm(context));
        zzb(new zzam(zzan2));
        zzb(new zzaz(dataLayer));
        zzb(new zzgk(context, dataLayer));
        this.zzbfd = new HashMap();
        zzc(new zzak());
        zzc(new zzbl());
        zzc(new zzbm());
        zzc(new zzbs());
        zzc(new zzbt());
        zzc(new zzde());
        zzc(new zzdf());
        zzc(new zzel());
        zzc(new zzfy());
        this.zzbfe = new HashMap();
        zza(new zze(context));
        zza(new zzf(context));
        zza(new zzh(context));
        zza(new zzi(context));
        zza(new zzj(context));
        zza(new zzk(context));
        zza(new zzl(context));
        zza(new zzt());
        zza(new zzaj(this.zzbfa.getVersion()));
        zza(new zzam(zzan));
        zza(new zzas(dataLayer));
        zza(new zzbc(context));
        zza(new zzbd());
        zza(new zzbk());
        zza(new zzbp(this));
        zza(new zzbu());
        zza(new zzbv());
        zza(new zzcv(context));
        zza(new zzcx());
        zza(new zzdd());
        zza(new zzdk());
        zza(new zzdm(context));
        zza(new zzea());
        zza(new zzee());
        zza(new zzei());
        zza(new zzek());
        zza(new zzem(context));
        zza(new zzfj());
        zza(new zzfk());
        zza(new zzge());
        zza(new zzgl());
        this.zzbfi = new HashMap();
        for (zzrm zzrm : this.zzbfh) {
            int i = 0;
            for (int i2 = 0; i2 < zzrm.zzte().size(); i2++) {
                zzri zzri2 = (zzri) zzrm.zzte().get(i2);
                String str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                zzfi zzb = zzb(this.zzbfi, zza(zzri2));
                zzb.zza(zzrm);
                zzb.zza(zzrm, zzri2);
                zzb.zza(zzrm, str);
            }
            while (i < zzrm.zztf().size()) {
                zzri = (zzri) zzrm.zztf().get(i);
                String str2 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                zzfi zzb2 = zzb(this.zzbfi, zza(zzri));
                zzb2.zza(zzrm);
                zzb2.zzb(zzrm, zzri);
                zzb2.zzb(zzrm, str2);
                i++;
            }
        }
        for (Entry entry : this.zzbfa.zztc().entrySet()) {
            for (zzri zzri3 : (List) entry.getValue()) {
                if (!zzgj.zzg((zzp) zzri3.zzsi().get(zzb.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    zzb(this.zzbfi, (String) entry.getKey()).zzb(zzri3);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x000d A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0087 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0084 A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004b A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0084 A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0087 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x008a A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0055 A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0087 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0084 A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004b A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0143 A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0101 A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x000d A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0177 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x000d A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0084 A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0087 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x008a A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x007f A:{SYNTHETIC, IGNORE_EDGES: B:63:0x017a} */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0087 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0084 A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004b A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0092 A:{Catch:{ all -> 0x0179 }} */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x000d A:{Catch:{ all -> 0x0179 }} */
    public final synchronized void zzg(java.util.List<com.google.android.gms.internal.measurement.zzn> r20) {
        /*
        r19 = this;
        r1 = r19;
        monitor-enter(r19);
        r2 = r20.iterator();	 Catch:{ all -> 0x0179 }
    L_0x0007:
        r3 = r2.hasNext();	 Catch:{ all -> 0x0179 }
        if (r3 == 0) goto L_0x0177;
    L_0x000d:
        r3 = r2.next();	 Catch:{ all -> 0x0179 }
        r3 = (com.google.android.gms.internal.measurement.zzn) r3;	 Catch:{ all -> 0x0179 }
        r4 = r3.name;	 Catch:{ all -> 0x0179 }
        if (r4 == 0) goto L_0x0152;
    L_0x0017:
        r4 = r3.name;	 Catch:{ all -> 0x0179 }
        r5 = "gaExperiment:";
        r4 = r4.startsWith(r5);	 Catch:{ all -> 0x0179 }
        if (r4 != 0) goto L_0x0023;
    L_0x0021:
        goto L_0x0152;
    L_0x0023:
        r4 = r1.zzazp;	 Catch:{ all -> 0x0179 }
        r5 = r3.zzqe;	 Catch:{ all -> 0x0179 }
        if (r5 != 0) goto L_0x002f;
    L_0x0029:
        r3 = "supplemental missing experimentSupplemental";
        com.google.android.gms.tagmanager.zzdi.zzab(r3);	 Catch:{ all -> 0x0179 }
        goto L_0x0007;
    L_0x002f:
        r5 = r3.zzqe;	 Catch:{ all -> 0x0179 }
        r5 = r5.zzop;	 Catch:{ all -> 0x0179 }
        r6 = r5.length;	 Catch:{ all -> 0x0179 }
        r8 = 0;
    L_0x0035:
        if (r8 >= r6) goto L_0x0043;
    L_0x0037:
        r9 = r5[r8];	 Catch:{ all -> 0x0179 }
        r9 = com.google.android.gms.tagmanager.zzgj.zzc(r9);	 Catch:{ all -> 0x0179 }
        r4.zzdh(r9);	 Catch:{ all -> 0x0179 }
        r8 = r8 + 1;
        goto L_0x0035;
    L_0x0043:
        r5 = r3.zzqe;	 Catch:{ all -> 0x0179 }
        r5 = r5.zzoo;	 Catch:{ all -> 0x0179 }
        r6 = r5.length;	 Catch:{ all -> 0x0179 }
        r8 = 0;
    L_0x0049:
        if (r8 >= r6) goto L_0x008a;
    L_0x004b:
        r10 = r5[r8];	 Catch:{ all -> 0x0179 }
        r10 = com.google.android.gms.tagmanager.zzgj.zzh(r10);	 Catch:{ all -> 0x0179 }
        r11 = r10 instanceof java.util.Map;	 Catch:{ all -> 0x0179 }
        if (r11 != 0) goto L_0x007f;
    L_0x0055:
        r10 = java.lang.String.valueOf(r10);	 Catch:{ all -> 0x0179 }
        r11 = 36;
        r12 = java.lang.String.valueOf(r10);	 Catch:{ all -> 0x0179 }
        r12 = r12.length();	 Catch:{ all -> 0x0179 }
        r11 = r11 + r12;
        r12 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0179 }
        r12.<init>(r11);	 Catch:{ all -> 0x0179 }
        r11 = "value: ";
        r12.append(r11);	 Catch:{ all -> 0x0179 }
        r12.append(r10);	 Catch:{ all -> 0x0179 }
        r10 = " is not a map value, ignored.";
        r12.append(r10);	 Catch:{ all -> 0x0179 }
        r10 = r12.toString();	 Catch:{ all -> 0x0179 }
        com.google.android.gms.tagmanager.zzdi.zzab(r10);	 Catch:{ all -> 0x0179 }
        r9 = 0;
        goto L_0x0082;
    L_0x007f:
        r9 = r10;
        r9 = (java.util.Map) r9;	 Catch:{ all -> 0x0179 }
    L_0x0082:
        if (r9 == 0) goto L_0x0087;
    L_0x0084:
        r4.push(r9);	 Catch:{ all -> 0x0179 }
    L_0x0087:
        r8 = r8 + 1;
        goto L_0x0049;
    L_0x008a:
        r3 = r3.zzqe;	 Catch:{ all -> 0x0179 }
        r3 = r3.zzoq;	 Catch:{ all -> 0x0179 }
        r5 = r3.length;	 Catch:{ all -> 0x0179 }
        r6 = 0;
    L_0x0090:
        if (r6 >= r5) goto L_0x0007;
    L_0x0092:
        r8 = r3[r6];	 Catch:{ all -> 0x0179 }
        r10 = r8.zzoj;	 Catch:{ all -> 0x0179 }
        if (r10 != 0) goto L_0x00a0;
    L_0x0098:
        r8 = "GaExperimentRandom: No key";
        com.google.android.gms.tagmanager.zzdi.zzab(r8);	 Catch:{ all -> 0x0179 }
        r12 = 0;
        goto L_0x014e;
    L_0x00a0:
        r10 = r8.zzoj;	 Catch:{ all -> 0x0179 }
        r10 = r4.get(r10);	 Catch:{ all -> 0x0179 }
        r11 = r10 instanceof java.lang.Number;	 Catch:{ all -> 0x0179 }
        if (r11 != 0) goto L_0x00ac;
    L_0x00aa:
        r11 = 0;
        goto L_0x00b7;
    L_0x00ac:
        r11 = r10;
        r11 = (java.lang.Number) r11;	 Catch:{ all -> 0x0179 }
        r11 = r11.longValue();	 Catch:{ all -> 0x0179 }
        r11 = java.lang.Long.valueOf(r11);	 Catch:{ all -> 0x0179 }
    L_0x00b7:
        r12 = r8.zzok;	 Catch:{ all -> 0x0179 }
        r14 = r8.zzol;	 Catch:{ all -> 0x0179 }
        r9 = r8.zzom;	 Catch:{ all -> 0x0179 }
        if (r9 == 0) goto L_0x00d4;
    L_0x00bf:
        if (r11 == 0) goto L_0x00d4;
    L_0x00c1:
        r16 = r11.longValue();	 Catch:{ all -> 0x0179 }
        r9 = (r16 > r12 ? 1 : (r16 == r12 ? 0 : -1));
        if (r9 < 0) goto L_0x00d4;
    L_0x00c9:
        r16 = r11.longValue();	 Catch:{ all -> 0x0179 }
        r9 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1));
        if (r9 <= 0) goto L_0x00d2;
    L_0x00d1:
        goto L_0x00d4;
    L_0x00d2:
        r7 = r8;
        goto L_0x00ee;
    L_0x00d4:
        r9 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
        if (r9 > 0) goto L_0x0148;
    L_0x00d8:
        r9 = java.lang.Math.random();	 Catch:{ all -> 0x0179 }
        r18 = r8;
        r7 = r14 - r12;
        r7 = (double) r7;	 Catch:{ all -> 0x0179 }
        r9 = r9 * r7;
        r7 = (double) r12;	 Catch:{ all -> 0x0179 }
        r9 = r9 + r7;
        r7 = java.lang.Math.round(r9);	 Catch:{ all -> 0x0179 }
        r10 = java.lang.Long.valueOf(r7);	 Catch:{ all -> 0x0179 }
        r7 = r18;
    L_0x00ee:
        r8 = r7.zzoj;	 Catch:{ all -> 0x0179 }
        r4.zzdh(r8);	 Catch:{ all -> 0x0179 }
        r8 = r7.zzoj;	 Catch:{ all -> 0x0179 }
        r8 = com.google.android.gms.tagmanager.DataLayer.zzk(r8, r10);	 Catch:{ all -> 0x0179 }
        r9 = r7.zzon;	 Catch:{ all -> 0x0179 }
        r11 = 0;
        r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1));
        if (r13 <= 0) goto L_0x0143;
    L_0x0101:
        r9 = "gtm";
        r9 = r8.containsKey(r9);	 Catch:{ all -> 0x0179 }
        if (r9 != 0) goto L_0x0124;
    L_0x0109:
        r9 = "gtm";
        r10 = 2;
        r10 = new java.lang.Object[r10];	 Catch:{ all -> 0x0179 }
        r11 = "lifetime";
        r12 = 0;
        r10[r12] = r11;	 Catch:{ all -> 0x0179 }
        r13 = r7.zzon;	 Catch:{ all -> 0x0179 }
        r7 = java.lang.Long.valueOf(r13);	 Catch:{ all -> 0x0179 }
        r11 = 1;
        r10[r11] = r7;	 Catch:{ all -> 0x0179 }
        r7 = com.google.android.gms.tagmanager.DataLayer.mapOf(r10);	 Catch:{ all -> 0x0179 }
        r8.put(r9, r7);	 Catch:{ all -> 0x0179 }
        goto L_0x0144;
    L_0x0124:
        r12 = 0;
        r9 = "gtm";
        r9 = r8.get(r9);	 Catch:{ all -> 0x0179 }
        r10 = r9 instanceof java.util.Map;	 Catch:{ all -> 0x0179 }
        if (r10 == 0) goto L_0x013d;
    L_0x012f:
        r9 = (java.util.Map) r9;	 Catch:{ all -> 0x0179 }
        r10 = "lifetime";
        r13 = r7.zzon;	 Catch:{ all -> 0x0179 }
        r7 = java.lang.Long.valueOf(r13);	 Catch:{ all -> 0x0179 }
        r9.put(r10, r7);	 Catch:{ all -> 0x0179 }
        goto L_0x0144;
    L_0x013d:
        r7 = "GaExperimentRandom: gtm not a map";
        com.google.android.gms.tagmanager.zzdi.zzab(r7);	 Catch:{ all -> 0x0179 }
        goto L_0x0144;
    L_0x0143:
        r12 = 0;
    L_0x0144:
        r4.push(r8);	 Catch:{ all -> 0x0179 }
        goto L_0x014e;
    L_0x0148:
        r12 = 0;
        r7 = "GaExperimentRandom: random range invalid";
        com.google.android.gms.tagmanager.zzdi.zzab(r7);	 Catch:{ all -> 0x0179 }
    L_0x014e:
        r6 = r6 + 1;
        goto L_0x0090;
    L_0x0152:
        r3 = java.lang.String.valueOf(r3);	 Catch:{ all -> 0x0179 }
        r4 = 22;
        r5 = java.lang.String.valueOf(r3);	 Catch:{ all -> 0x0179 }
        r5 = r5.length();	 Catch:{ all -> 0x0179 }
        r4 = r4 + r5;
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0179 }
        r5.<init>(r4);	 Catch:{ all -> 0x0179 }
        r4 = "Ignored supplemental: ";
        r5.append(r4);	 Catch:{ all -> 0x0179 }
        r5.append(r3);	 Catch:{ all -> 0x0179 }
        r3 = r5.toString();	 Catch:{ all -> 0x0179 }
        com.google.android.gms.tagmanager.zzdi.v(r3);	 Catch:{ all -> 0x0179 }
        goto L_0x0007;
    L_0x0177:
        monitor-exit(r19);
        return;
    L_0x0179:
        r0 = move-exception;
        r2 = r0;
        monitor-exit(r19);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzfb.zzg(java.util.List):void");
    }

    public final synchronized void zzde(String str) {
        zzea(str);
        zzar zzor = this.zzbfb.zzdq(str).zzor();
        for (zzri zza : (Set) zza(this.zzbfh, new HashSet(), new zzff(this), zzor.zzog()).getObject()) {
            zza(this.zzbfc, zza, new HashSet(), zzor.zzof());
        }
        zzea(null);
    }

    public final zzdz<zzp> zzdz(String str) {
        this.zzbfk = 0;
        return zza(str, new HashSet(), this.zzbfb.zzdp(str).zzoq());
    }

    @VisibleForTesting
    private final synchronized void zzea(String str) {
        this.zzbfj = str;
    }

    /* Access modifiers changed, original: final|declared_synchronized */
    public final synchronized String zzpt() {
        return this.zzbfj;
    }

    private static zzfi zzb(Map<String, zzfi> map, String str) {
        zzfi zzfi = (zzfi) map.get(str);
        if (zzfi != null) {
            return zzfi;
        }
        zzfi = new zzfi();
        map.put(str, zzfi);
        return zzfi;
    }

    private final zzdz<Set<zzri>> zza(Set<zzrm> set, Set<String> set2, zzfg zzfg, zzfa zzfa) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        while (true) {
            boolean z = true;
            for (zzrm zzrm : set) {
                zzdz zzdz;
                zzeq zzpg = zzfa.zzpg();
                while (true) {
                    zzdz zza;
                    boolean z2 = true;
                    for (zzri zza2 : zzrm.zzsl()) {
                        zza = zza(zza2, (Set) set2, zzpg.zzpa());
                        if (((Boolean) zza.getObject()).booleanValue()) {
                            zzgj.zzj(Boolean.valueOf(false));
                            zzdz = new zzdz(Boolean.valueOf(false), zza.zzpi());
                            break;
                        } else if (!z2 || !zza.zzpi()) {
                            z2 = false;
                        }
                    }
                    for (zzri zza22 : zzrm.zzsk()) {
                        zza = zza(zza22, (Set) set2, zzpg.zzpb());
                        if (!((Boolean) zza.getObject()).booleanValue()) {
                            zzgj.zzj(Boolean.valueOf(false));
                            zzdz = new zzdz(Boolean.valueOf(false), zza.zzpi());
                            break;
                        }
                        z2 = z2 && zza.zzpi();
                    }
                    zzgj.zzj(Boolean.valueOf(true));
                    zzdz = new zzdz(Boolean.valueOf(true), z2);
                }
                if (((Boolean) zzdz.getObject()).booleanValue()) {
                    zzfg.zza(zzrm, hashSet, hashSet2, zzpg);
                }
                if (!z || !zzdz.zzpi()) {
                    z = false;
                }
            }
            hashSet.removeAll(hashSet2);
            zzfa.zzb(hashSet);
            return new zzdz(hashSet, z);
        }
    }

    private static String zza(zzri zzri) {
        return zzgj.zzc((zzp) zzri.zzsi().get(zzb.INSTANCE_NAME.toString()));
    }

    private static void zza(Map<String, zzbq> map, zzbq zzbq) {
        if (map.containsKey(zzbq.zzot())) {
            String str = "Duplicate function type name: ";
            String valueOf = String.valueOf(zzbq.zzot());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        map.put(zzbq.zzot(), zzbq);
    }

    @VisibleForTesting
    private final void zza(zzbq zzbq) {
        zza(this.zzbfe, zzbq);
    }

    @VisibleForTesting
    private final void zzb(zzbq zzbq) {
        zza(this.zzbfc, zzbq);
    }

    @VisibleForTesting
    private final void zzc(zzbq zzbq) {
        zza(this.zzbfd, zzbq);
    }

    @VisibleForTesting
    private final zzdz<Boolean> zza(zzri zzri, Set<String> set, zzen zzen) {
        zzdz zza = zza(this.zzbfd, zzri, (Set) set, zzen);
        Boolean zzg = zzgj.zzg((zzp) zza.getObject());
        zzen.zza(zzgj.zzj(zzg));
        return new zzdz(zzg, zza.zzpi());
    }

    private final String zzpu() {
        if (this.zzbfk <= 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Integer.toString(this.zzbfk));
        for (int i = 2; i < this.zzbfk; i++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append(": ");
        return stringBuilder.toString();
    }

    private final zzdz<zzp> zza(String str, Set<String> set, zzdl zzdl) {
        this.zzbfk++;
        zzfh zzfh = (zzfh) this.zzbfg.get(str);
        if (zzfh != null) {
            this.zzbfb.zzos();
            zza(zzfh.zzpw(), (Set) set);
            this.zzbfk--;
            return zzfh.zzpv();
        }
        zzfi zzfi = (zzfi) this.zzbfi.get(str);
        if (zzfi == null) {
            String zzpu = zzpu();
            StringBuilder stringBuilder = new StringBuilder((15 + String.valueOf(zzpu).length()) + String.valueOf(str).length());
            stringBuilder.append(zzpu);
            stringBuilder.append("Invalid macro: ");
            stringBuilder.append(str);
            zzdi.e(stringBuilder.toString());
            this.zzbfk--;
            return zzbez;
        }
        zzri zzqc;
        zzdz zza = zza(zzfi.zzpx(), (Set) set, new zzfe(this, zzfi.zzpy(), zzfi.zzpz(), zzfi.zzqb(), zzfi.zzqa()), zzdl.zzog());
        if (((Set) zza.getObject()).isEmpty()) {
            zzqc = zzfi.zzqc();
        } else {
            if (((Set) zza.getObject()).size() > 1) {
                String zzpu2 = zzpu();
                StringBuilder stringBuilder2 = new StringBuilder((37 + String.valueOf(zzpu2).length()) + String.valueOf(str).length());
                stringBuilder2.append(zzpu2);
                stringBuilder2.append("Multiple macros active for macroName ");
                stringBuilder2.append(str);
                zzdi.zzab(stringBuilder2.toString());
            }
            zzqc = (zzri) ((Set) zza.getObject()).iterator().next();
        }
        if (zzqc == null) {
            this.zzbfk--;
            return zzbez;
        }
        zzdz zza2 = zza(this.zzbfe, zzqc, (Set) set, zzdl.zzoz());
        boolean z = zza.zzpi() && zza2.zzpi();
        if (zza2 == zzbez) {
            zza2 = zzbez;
        } else {
            zza2 = new zzdz((zzp) zza2.getObject(), z);
        }
        zzp zzpw = zzqc.zzpw();
        if (zza2.zzpi()) {
            this.zzbfg.zza(str, new zzfh(zza2, zzpw));
        }
        zza(zzpw, (Set) set);
        this.zzbfk--;
        return zza2;
    }

    private final void zza(zzp zzp, Set<String> set) {
        if (zzp != null) {
            zzdz zza = zza(zzp, (Set) set, new zzdx());
            if (zza != zzbez) {
                Object zzh = zzgj.zzh((zzp) zza.getObject());
                if (zzh instanceof Map) {
                    this.zzazp.push((Map) zzh);
                } else if (zzh instanceof List) {
                    for (Object next : (List) zzh) {
                        if (next instanceof Map) {
                            this.zzazp.push((Map) next);
                        } else {
                            zzdi.zzab("pushAfterEvaluate: value not a Map");
                        }
                    }
                } else {
                    zzdi.zzab("pushAfterEvaluate: value not a Map or List");
                }
            }
        }
    }

    private final zzdz<zzp> zza(zzp zzp, Set<String> set, zzgm zzgm) {
        if (!zzp.zzqs) {
            return new zzdz(zzp, true);
        }
        int i = zzp.type;
        zzp zzk;
        int i2;
        zzdz zza;
        if (i != 7) {
            String str;
            String valueOf;
            switch (i) {
                case 2:
                    zzk = zzrg.zzk(zzp);
                    zzk.zzqj = new zzp[zzp.zzqj.length];
                    for (i2 = 0; i2 < zzp.zzqj.length; i2++) {
                        zza = zza(zzp.zzqj[i2], (Set) set, zzgm.zzx(i2));
                        if (zza == zzbez) {
                            return zzbez;
                        }
                        zzk.zzqj[i2] = (zzp) zza.getObject();
                    }
                    return new zzdz(zzk, false);
                case 3:
                    zzk = zzrg.zzk(zzp);
                    if (zzp.zzqk.length != zzp.zzql.length) {
                        str = "Invalid serving value: ";
                        valueOf = String.valueOf(zzp.toString());
                        zzdi.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                        return zzbez;
                    }
                    zzk.zzqk = new zzp[zzp.zzqk.length];
                    zzk.zzql = new zzp[zzp.zzqk.length];
                    for (i2 = 0; i2 < zzp.zzqk.length; i2++) {
                        zza = zza(zzp.zzqk[i2], (Set) set, zzgm.zzy(i2));
                        zzdz zza2 = zza(zzp.zzql[i2], (Set) set, zzgm.zzz(i2));
                        if (zza == zzbez || zza2 == zzbez) {
                            return zzbez;
                        }
                        zzk.zzqk[i2] = (zzp) zza.getObject();
                        zzk.zzql[i2] = (zzp) zza2.getObject();
                    }
                    return new zzdz(zzk, false);
                case 4:
                    if (set.contains(zzp.zzqm)) {
                        valueOf = zzp.zzqm;
                        str = set.toString();
                        StringBuilder stringBuilder = new StringBuilder((79 + String.valueOf(valueOf).length()) + String.valueOf(str).length());
                        stringBuilder.append("Macro cycle detected.  Current macro reference: ");
                        stringBuilder.append(valueOf);
                        stringBuilder.append(".  Previous macro references: ");
                        stringBuilder.append(str);
                        stringBuilder.append(".");
                        zzdi.e(stringBuilder.toString());
                        return zzbez;
                    }
                    set.add(zzp.zzqm);
                    zzdz zza3 = zzgn.zza(zza(zzp.zzqm, (Set) set, zzgm.zzph()), zzp.zzqr);
                    set.remove(zzp.zzqm);
                    return zza3;
                default:
                    int i3 = zzp.type;
                    StringBuilder stringBuilder2 = new StringBuilder(25);
                    stringBuilder2.append("Unknown type: ");
                    stringBuilder2.append(i3);
                    zzdi.e(stringBuilder2.toString());
                    return zzbez;
            }
        }
        zzk = zzrg.zzk(zzp);
        zzk.zzqq = new zzp[zzp.zzqq.length];
        for (i2 = 0; i2 < zzp.zzqq.length; i2++) {
            zza = zza(zzp.zzqq[i2], (Set) set, zzgm.zzaa(i2));
            if (zza == zzbez) {
                return zzbez;
            }
            zzk.zzqq[i2] = (zzp) zza.getObject();
        }
        return new zzdz(zzk, false);
    }

    private final zzdz<zzp> zza(Map<String, zzbq> map, zzri zzri, Set<String> set, zzen zzen) {
        zzp zzp = (zzp) zzri.zzsi().get(zzb.FUNCTION.toString());
        if (zzp == null) {
            zzdi.e("No function id in properties");
            return zzbez;
        }
        String str = zzp.zzqn;
        zzbq zzbq = (zzbq) map.get(str);
        if (zzbq == null) {
            zzdi.e(String.valueOf(str).concat(" has no backing implementation."));
            return zzbez;
        }
        zzdz zzdz = (zzdz) this.zzbff.get(zzri);
        if (zzdz != null) {
            this.zzbfb.zzos();
            return zzdz;
        }
        HashMap hashMap = new HashMap();
        boolean z = true;
        boolean z2 = true;
        for (Entry entry : zzri.zzsi().entrySet()) {
            zzdz zza = zza((zzp) entry.getValue(), (Set) set, zzen.zzdw((String) entry.getKey()).zzb((zzp) entry.getValue()));
            if (zza == zzbez) {
                return zzbez;
            }
            if (zza.zzpi()) {
                zzri.zza((String) entry.getKey(), (zzp) zza.getObject());
            } else {
                z2 = false;
            }
            hashMap.put((String) entry.getKey(), (zzp) zza.getObject());
        }
        if (zzbq.zza(hashMap.keySet())) {
            if (!(z2 && zzbq.zznk())) {
                z = false;
            }
            zzdz zzdz2 = new zzdz(zzbq.zzc(hashMap), z);
            if (z) {
                this.zzbff.zza(zzri, zzdz2);
            }
            zzen.zza((zzp) zzdz2.getObject());
            return zzdz2;
        }
        String valueOf = String.valueOf(zzbq.zzou());
        String valueOf2 = String.valueOf(hashMap.keySet());
        StringBuilder stringBuilder = new StringBuilder(((43 + String.valueOf(str).length()) + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length());
        stringBuilder.append("Incorrect keys for function ");
        stringBuilder.append(str);
        stringBuilder.append(" required ");
        stringBuilder.append(valueOf);
        stringBuilder.append(" had ");
        stringBuilder.append(valueOf2);
        zzdi.e(stringBuilder.toString());
        return zzbez;
    }
}
