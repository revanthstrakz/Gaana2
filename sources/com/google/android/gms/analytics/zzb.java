package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzaa;
import com.google.android.gms.internal.measurement.zzab;
import com.google.android.gms.internal.measurement.zzac;
import com.google.android.gms.internal.measurement.zzad;
import com.google.android.gms.internal.measurement.zzae;
import com.google.android.gms.internal.measurement.zzaf;
import com.google.android.gms.internal.measurement.zzag;
import com.google.android.gms.internal.measurement.zzah;
import com.google.android.gms.internal.measurement.zzai;
import com.google.android.gms.internal.measurement.zzaj;
import com.google.android.gms.internal.measurement.zzat;
import com.google.android.gms.internal.measurement.zzav;
import com.google.android.gms.internal.measurement.zzaw;
import com.google.android.gms.internal.measurement.zzaz;
import com.google.android.gms.internal.measurement.zzck;
import com.google.android.gms.internal.measurement.zzdg;
import com.google.android.gms.internal.measurement.zzx;
import com.google.android.gms.internal.measurement.zzy;
import com.google.android.gms.internal.measurement.zzz;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.til.colombia.android.internal.e;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzb extends zzat implements zzo {
    private static DecimalFormat zzrb;
    private final zzaw zzqx;
    private final String zzrc;
    private final Uri zzrd;

    public zzb(zzaw zzaw, String str) {
        this(zzaw, str, true, false);
    }

    private zzb(zzaw zzaw, String str, boolean z, boolean z2) {
        super(zzaw);
        Preconditions.checkNotEmpty(str);
        this.zzqx = zzaw;
        this.zzrc = str;
        this.zzrd = zzb(this.zzrc);
    }

    static Uri zzb(String str) {
        Preconditions.checkNotEmpty(str);
        Builder builder = new Builder();
        builder.scheme("uri");
        builder.authority("google-analytics.com");
        builder.path(str);
        return builder.build();
    }

    public final Uri zzo() {
        return this.zzrd;
    }

    public final void zzb(zzg zzg) {
        Preconditions.checkNotNull(zzg);
        Preconditions.checkArgument(zzg.zzx(), "Can't deliver not submitted measurement");
        Preconditions.checkNotMainThread("deliver should be called on worker thread");
        zzg zzs = zzg.zzs();
        zzag zzag = (zzag) zzs.zzb(zzag.class);
        if (TextUtils.isEmpty(zzag.zzbc())) {
            zzby().zza(zzc(zzs), "Ignoring measurement without type");
        } else if (TextUtils.isEmpty(zzag.zzbd())) {
            zzby().zza(zzc(zzs), "Ignoring measurement without client id");
        } else if (!this.zzqx.zzco().getAppOptOut()) {
            double zzbj = zzag.zzbj();
            if (zzdg.zza(zzbj, zzag.zzbd())) {
                zzb("Sampling enabled. Hit sampled out. sampling rate", Double.valueOf(zzbj));
                return;
            }
            Map zzc = zzc(zzs);
            zzc.put("v", "1");
            zzc.put(e.N, zzav.zzwa);
            zzc.put("tid", this.zzrc);
            if (this.zzqx.zzco().isDryRunEnabled()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Entry entry : zzc.entrySet()) {
                    if (stringBuilder.length() != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append((String) entry.getKey());
                    stringBuilder.append("=");
                    stringBuilder.append((String) entry.getValue());
                }
                zzc("Dry run is enabled. GoogleAnalytics would have sent", stringBuilder.toString());
                return;
            }
            Map hashMap = new HashMap();
            zzdg.zzb(hashMap, "uid", zzag.zzbe());
            zzx zzx = (zzx) zzg.zza(zzx.class);
            if (zzx != null) {
                zzdg.zzb(hashMap, "an", zzx.zzaj());
                zzdg.zzb(hashMap, "aid", zzx.zzal());
                zzdg.zzb(hashMap, "av", zzx.zzak());
                zzdg.zzb(hashMap, "aiid", zzx.zzam());
            }
            zzc.put("_s", String.valueOf(zzcc().zza(new zzaz(0, zzag.zzbd(), this.zzrc, TextUtils.isEmpty(zzag.zzbf()) ^ 1, 0, hashMap))));
            zzcc().zza(new zzck(zzby(), zzc, zzg.zzv(), true));
        }
    }

    @VisibleForTesting
    private static Map<String, String> zzc(zzg zzg) {
        String str;
        Map hashMap = new HashMap();
        zzab zzab = (zzab) zzg.zza(zzab.class);
        if (zzab != null) {
            for (Entry entry : zzab.zzaw().entrySet()) {
                Boolean value = entry.getValue();
                Object obj = null;
                if (value != null) {
                    if (value instanceof String) {
                        str = (String) value;
                        if (!TextUtils.isEmpty(str)) {
                            obj = str;
                        }
                    } else if (value instanceof Double) {
                        Double d = (Double) value;
                        if (d.doubleValue() != 0.0d) {
                            obj = zza(d.doubleValue());
                        }
                    } else if (!(value instanceof Boolean)) {
                        obj = String.valueOf(value);
                    } else if (value != Boolean.FALSE) {
                        obj = "1";
                    }
                }
                if (obj != null) {
                    hashMap.put((String) entry.getKey(), obj);
                }
            }
        }
        zzag zzag = (zzag) zzg.zza(zzag.class);
        if (zzag != null) {
            zza(hashMap, "t", zzag.zzbc());
            zza(hashMap, "cid", zzag.zzbd());
            zza(hashMap, "uid", zzag.zzbe());
            zza(hashMap, "sc", zzag.zzbh());
            zza(hashMap, "sf", zzag.zzbj());
            zza(hashMap, "ni", zzag.zzbi());
            zza(hashMap, "adid", zzag.zzbf());
            zza(hashMap, "ate", zzag.zzbg());
        }
        zzah zzah = (zzah) zzg.zza(zzah.class);
        if (zzah != null) {
            zza(hashMap, "cd", zzah.zzbk());
            zza(hashMap, "a", (double) zzah.zzbl());
            zza(hashMap, "dr", zzah.zzbm());
        }
        zzae zzae = (zzae) zzg.zza(zzae.class);
        if (zzae != null) {
            zza(hashMap, "ec", zzae.zzbb());
            zza(hashMap, "ea", zzae.getAction());
            zza(hashMap, "el", zzae.getLabel());
            zza(hashMap, "ev", (double) zzae.getValue());
        }
        zzy zzy = (zzy) zzg.zza(zzy.class);
        if (zzy != null) {
            zza(hashMap, "cn", zzy.getName());
            zza(hashMap, "cs", zzy.getSource());
            zza(hashMap, "cm", zzy.zzan());
            zza(hashMap, "ck", zzy.zzao());
            zza(hashMap, "cc", zzy.zzap());
            zza(hashMap, "ci", zzy.getId());
            zza(hashMap, "anid", zzy.zzaq());
            zza(hashMap, "gclid", zzy.zzar());
            zza(hashMap, "dclid", zzy.zzas());
            zza(hashMap, "aclid", zzy.zzat());
        }
        zzaf zzaf = (zzaf) zzg.zza(zzaf.class);
        if (zzaf != null) {
            zza(hashMap, "exd", zzaf.zzum);
            zza(hashMap, "exf", zzaf.zzun);
        }
        zzai zzai = (zzai) zzg.zza(zzai.class);
        if (zzai != null) {
            zza(hashMap, "sn", zzai.zzvd);
            zza(hashMap, e.ak, zzai.zzve);
            zza(hashMap, "st", zzai.zzvf);
        }
        zzaj zzaj = (zzaj) zzg.zza(zzaj.class);
        if (zzaj != null) {
            zza(hashMap, "utv", zzaj.zzvg);
            zza(hashMap, "utt", (double) zzaj.zzvh);
            zza(hashMap, "utc", zzaj.mCategory);
            zza(hashMap, "utl", zzaj.zzvi);
        }
        zzz zzz = (zzz) zzg.zza(zzz.class);
        if (zzz != null) {
            for (Entry entry2 : zzz.zzau().entrySet()) {
                str = zzd.zze(((Integer) entry2.getKey()).intValue());
                if (!TextUtils.isEmpty(str)) {
                    hashMap.put(str, (String) entry2.getValue());
                }
            }
        }
        zzaa zzaa = (zzaa) zzg.zza(zzaa.class);
        if (zzaa != null) {
            for (Entry entry22 : zzaa.zzav().entrySet()) {
                str = zzd.zzg(((Integer) entry22.getKey()).intValue());
                if (!TextUtils.isEmpty(str)) {
                    hashMap.put(str, zza(((Double) entry22.getValue()).doubleValue()));
                }
            }
        }
        zzad zzad = (zzad) zzg.zza(zzad.class);
        if (zzad != null) {
            ProductAction zzax = zzad.zzax();
            if (zzax != null) {
                for (Entry entry3 : zzax.build().entrySet()) {
                    if (((String) entry3.getKey()).startsWith("&")) {
                        hashMap.put(((String) entry3.getKey()).substring(1), (String) entry3.getValue());
                    } else {
                        hashMap.put((String) entry3.getKey(), (String) entry3.getValue());
                    }
                }
            }
            int i = 1;
            for (Promotion zzn : zzad.zzba()) {
                hashMap.putAll(zzn.zzn(zzd.zzk(i)));
                i++;
            }
            i = 1;
            for (Product zzn2 : zzad.zzay()) {
                hashMap.putAll(zzn2.zzn(zzd.zzi(i)));
                i++;
            }
            int i2 = 1;
            for (Entry entry32 : zzad.zzaz().entrySet()) {
                List<Product> list = (List) entry32.getValue();
                String zzn3 = zzd.zzn(i2);
                int i3 = 1;
                for (Product product : list) {
                    String valueOf = String.valueOf(zzn3);
                    String valueOf2 = String.valueOf(zzd.zzl(i3));
                    hashMap.putAll(product.zzn(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)));
                    i3++;
                }
                if (!TextUtils.isEmpty((CharSequence) entry32.getKey())) {
                    String valueOf3 = String.valueOf(zzn3);
                    zzn3 = String.valueOf("nm");
                    hashMap.put(zzn3.length() != 0 ? valueOf3.concat(zzn3) : new String(valueOf3), (String) entry32.getKey());
                }
                i2++;
            }
        }
        zzac zzac = (zzac) zzg.zza(zzac.class);
        if (zzac != null) {
            zza(hashMap, "ul", zzac.getLanguage());
            zza(hashMap, "sd", (double) zzac.zzug);
            zza(hashMap, "sr", zzac.zzuh, zzac.zzui);
            zza(hashMap, "vp", zzac.zzuj, zzac.zzuk);
        }
        zzx zzx = (zzx) zzg.zza(zzx.class);
        if (zzx != null) {
            zza(hashMap, "an", zzx.zzaj());
            zza(hashMap, "aid", zzx.zzal());
            zza(hashMap, "aiid", zzx.zzam());
            zza(hashMap, "av", zzx.zzak());
        }
        return hashMap;
    }

    private static void zza(Map<String, String> map, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            map.put(str, str2);
        }
    }

    private static String zza(double d) {
        if (zzrb == null) {
            zzrb = new DecimalFormat("0.######");
        }
        return zzrb.format(d);
    }

    private static void zza(Map<String, String> map, String str, double d) {
        if (d != 0.0d) {
            map.put(str, zza(d));
        }
    }

    private static void zza(Map<String, String> map, String str, boolean z) {
        if (z) {
            map.put(str, "1");
        }
    }

    private static void zza(Map<String, String> map, String str, int i, int i2) {
        if (i > 0 && i2 > 0) {
            StringBuilder stringBuilder = new StringBuilder(23);
            stringBuilder.append(i);
            stringBuilder.append(AvidJSONUtil.KEY_X);
            stringBuilder.append(i2);
            map.put(str, stringBuilder.toString());
        }
    }
}
