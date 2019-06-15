package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzark
public final class zzald implements zzakp {
    private final Context mContext;
    private final Object mLock = new Object();
    private final long mStartTime;
    private final zzaba zzbln;
    private final zzalg zzbma;
    private final boolean zzbum;
    private final zzakr zzdmn;
    private final boolean zzdms;
    private final boolean zzdmt;
    private final zzasi zzdnh;
    private final long zzdni;
    private boolean zzdnk = false;
    private final String zzdnm;
    private List<zzakx> zzdnn = new ArrayList();
    private zzaku zzdnr;

    public zzald(Context context, zzasi zzasi, zzalg zzalg, zzakr zzakr, boolean z, boolean z2, String str, long j, long j2, zzaba zzaba, boolean z3) {
        this.mContext = context;
        this.zzdnh = zzasi;
        this.zzbma = zzalg;
        this.zzdmn = zzakr;
        this.zzbum = z;
        this.zzdms = z2;
        this.zzdnm = str;
        this.mStartTime = j;
        this.zzdni = j2;
        this.zzbln = zzaba;
        this.zzdmt = z3;
    }

    public final zzakx zzh(List<zzakq> list) {
        int i;
        int i2;
        Throwable th;
        Throwable th2;
        Object obj;
        ArrayList arrayList;
        zzbbd.zzdn("Starting mediation.");
        ArrayList arrayList2 = new ArrayList();
        zzaay zzrg = this.zzbln.zzrg();
        zzwf zzwf = this.zzdnh.zzbst;
        int[] iArr = new int[2];
        if (zzwf.zzckm != null) {
            zzbv.zzlz();
            if (zzakz.zza(this.zzdnm, iArr)) {
                i = iArr[0];
                int i3 = iArr[1];
                for (zzwf zzwf2 : zzwf.zzckm) {
                    if (i == zzwf2.width && i3 == zzwf2.height) {
                        zzwf = zzwf2;
                        break;
                    }
                }
            }
        }
        Iterator it = list.iterator();
        i = 1;
        while (it.hasNext()) {
            zzakq zzakq = (zzakq) it.next();
            String str = "Trying mediation network: ";
            String valueOf = String.valueOf(zzakq.zzdkv);
            zzbbd.zzen(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            Iterator it2 = zzakq.zzdkw.iterator();
            while (it2.hasNext()) {
                String str2 = (String) it2.next();
                zzaay zzrg2 = this.zzbln.zzrg();
                Object obj2 = this.mLock;
                synchronized (obj2) {
                    try {
                        if (this.zzdnk) {
                            try {
                            } catch (Throwable th22) {
                                th = th22;
                                obj = obj2;
                                throw th;
                            }
                        }
                        Iterator it3 = it;
                        Iterator it4 = it2;
                        zzaay zzaay = zzrg;
                        zzaay zzaay2 = zzrg2;
                        zzaku zzaku = zzaku;
                        ArrayList arrayList3 = arrayList2;
                        zzaku zzaku2 = zzaku;
                        obj = obj2;
                        try {
                            zzaku = new zzaku(this.mContext, str2, this.zzbma, this.zzdmn, zzakq, this.zzdnh.zzdwg, zzwf, this.zzdnh.zzbsp, this.zzbum, this.zzdms, this.zzdnh.zzbti, this.zzdnh.zzbtt, this.zzdnh.zzdwu, this.zzdnh.zzdxp, this.zzdmt);
                            this.zzdnr = zzaku2;
                            zzakx zzj = this.zzdnr.zzj(this.mStartTime, this.zzdni);
                            this.zzdnn.add(zzj);
                            if (zzj.zzdna == 0) {
                                zzbbd.zzdn("Adapter succeeded.");
                                this.zzbln.zzg("mediation_network_succeed", str2);
                                arrayList = arrayList3;
                                if (!arrayList.isEmpty()) {
                                    this.zzbln.zzg("mediation_networks_fail", TextUtils.join(",", arrayList));
                                }
                                this.zzbln.zza(zzaay2, "mls");
                                this.zzbln.zza(zzaay, "ttm");
                                return zzj;
                            }
                            zzaay zzaay3 = zzaay;
                            zzaay zzaay4 = zzaay2;
                            arrayList = arrayList3;
                            i2 = zzj.zzdna;
                            arrayList.add(str2);
                            this.zzbln.zza(zzaay4, "mlf");
                            if (zzj.zzdnc != null) {
                                zzayh.zzelc.post(new zzale(this, zzj));
                            }
                            arrayList2 = arrayList;
                            zzrg = zzaay3;
                            i = i2;
                            it = it3;
                            it2 = it4;
                        } catch (Throwable th3) {
                            th22 = th3;
                            th = th22;
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th22 = th4;
                        obj = obj2;
                        th = th22;
                        throw th;
                    }
                }
                return new zzakx(-1);
            }
        }
        arrayList = arrayList2;
        if (!arrayList.isEmpty()) {
            this.zzbln.zzg("mediation_networks_fail", TextUtils.join(",", arrayList));
        }
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcxm)).booleanValue()) {
            return new zzakx(i);
        }
        return new zzakx(1);
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.zzdnk = true;
            if (this.zzdnr != null) {
                this.zzdnr.cancel();
            }
        }
    }

    public final List<zzakx> zzui() {
        return this.zzdnn;
    }
}
