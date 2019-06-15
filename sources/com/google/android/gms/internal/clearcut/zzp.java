package com.google.android.gms.internal.clearcut;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger.zza;
import com.google.android.gms.clearcut.zze;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.clearcut.zzgw.zza.zzb;
import com.google.android.gms.phenotype.Phenotype;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public final class zzp implements zza {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final zzao zzaq = new zzao(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).zzc("gms:playlog:service:samplingrules_").zzd("LogSamplingRules__");
    private static final zzao zzar = new zzao(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).zzc("gms:playlog:service:sampling_").zzd("LogSampling__");
    private static final ConcurrentHashMap<String, zzae<zzgw.zza>> zzas = new ConcurrentHashMap();
    private static final HashMap<String, zzae<String>> zzat = new HashMap();
    @VisibleForTesting
    private static Boolean zzau;
    @VisibleForTesting
    private static Long zzav;
    @VisibleForTesting
    private static final zzae<Boolean> zzaw = zzaq.zzc("enable_log_sampling_rules", false);
    private final Context zzh;

    public zzp(Context context) {
        this.zzh = context;
        if (this.zzh != null) {
            zzae.maybeInit(this.zzh);
        }
    }

    @VisibleForTesting
    private static long zza(String str, long j) {
        byte[] array;
        if (str == null || str.isEmpty()) {
            array = ByteBuffer.allocate(8).putLong(j).array();
        } else {
            array = str.getBytes(UTF_8);
            ByteBuffer allocate = ByteBuffer.allocate(array.length + 8);
            allocate.put(array);
            allocate.putLong(j);
            array = allocate.array();
        }
        return zzk.zza(array);
    }

    @VisibleForTesting
    private static zzb zza(String str) {
        if (str == null) {
            return null;
        }
        String str2 = "";
        int indexOf = str.indexOf(44);
        int i = 0;
        if (indexOf >= 0) {
            str2 = str.substring(0, indexOf);
            i = indexOf + 1;
        }
        indexOf = str.indexOf(47, i);
        String str3;
        if (indexOf <= 0) {
            str2 = "LogSamplerImpl";
            str3 = "Failed to parse the rule: ";
            str = String.valueOf(str);
            Log.e(str2, str.length() != 0 ? str3.concat(str) : new String(str3));
            return null;
        }
        try {
            long parseLong = Long.parseLong(str.substring(i, indexOf));
            long parseLong2 = Long.parseLong(str.substring(indexOf + 1));
            if (parseLong >= 0 && parseLong2 >= 0) {
                return (zzb) zzb.zzfz().zzn(str2).zzr(parseLong).zzs(parseLong2).zzbh();
            }
            StringBuilder stringBuilder = new StringBuilder(72);
            stringBuilder.append("negative values not supported: ");
            stringBuilder.append(parseLong);
            stringBuilder.append("/");
            stringBuilder.append(parseLong2);
            Log.e("LogSamplerImpl", stringBuilder.toString());
            return null;
        } catch (NumberFormatException e) {
            str3 = "LogSamplerImpl";
            String str4 = "parseLong() failed while parsing: ";
            str = String.valueOf(str);
            Log.e(str3, str.length() != 0 ? str4.concat(str) : new String(str4), e);
            return null;
        }
    }

    @VisibleForTesting
    private static boolean zzb(long j, long j2, long j3) {
        if (j2 >= 0 && j3 > 0) {
            if (j < 0) {
                j = ((Long.MAX_VALUE % j3) + 1) + ((j & Long.MAX_VALUE) % j3);
            }
            if (j % j3 >= j2) {
                return false;
            }
        }
        return true;
    }

    private static boolean zzc(Context context) {
        if (zzau == null) {
            zzau = Boolean.valueOf(Wrappers.packageManager(context).checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
        }
        return zzau.booleanValue();
    }

    @VisibleForTesting
    private static long zzd(Context context) {
        if (zzav == null) {
            long j = 0;
            if (context == null) {
                return 0;
            }
            if (zzc(context)) {
                j = zzy.getLong(context.getContentResolver(), "android_id", 0);
            }
            zzav = Long.valueOf(j);
        }
        return zzav.longValue();
    }

    public final boolean zza(zze zze) {
        Object obj = zze.zzag.zzj;
        int i = zze.zzag.zzk;
        int i2 = zze.zzaa != null ? zze.zzaa.zzbji : 0;
        String str = null;
        if (((Boolean) zzaw.get()).booleanValue()) {
            if (obj == null || obj.isEmpty()) {
                obj = i >= 0 ? String.valueOf(i) : null;
            }
            if (obj != null) {
                List emptyList;
                if (this.zzh == null) {
                    emptyList = Collections.emptyList();
                } else {
                    zzae zzae = (zzae) zzas.get(obj);
                    if (zzae == null) {
                        zzae = zzaq.zza(obj, zzgw.zza.zzft(), zzq.zzax);
                        zzae zzae2 = (zzae) zzas.putIfAbsent(obj, zzae);
                        if (zzae2 != null) {
                            zzae = zzae2;
                        }
                    }
                    emptyList = ((zzgw.zza) zzae.get()).zzfs();
                }
                for (zzb zzb : emptyList) {
                    if ((!zzb.zzfv() || zzb.getEventCode() == 0 || zzb.getEventCode() == i2) && !zzb(zza(zzb.zzfw(), zzd(this.zzh)), zzb.zzfx(), zzb.zzfy())) {
                        return false;
                    }
                }
            }
        } else {
            if (obj == null || obj.isEmpty()) {
                obj = i >= 0 ? String.valueOf(i) : null;
            }
            if (obj != null) {
                if (this.zzh != null && zzc(this.zzh)) {
                    zzae zzae3 = (zzae) zzat.get(obj);
                    if (zzae3 == null) {
                        zzae3 = zzar.zza(obj, null);
                        zzat.put(obj, zzae3);
                    }
                    str = (String) zzae3.get();
                }
                zzb zza = zza(str);
                if (zza != null) {
                    return zzb(zza(zza.zzfw(), zzd(this.zzh)), zza.zzfx(), zza.zzfy());
                }
            }
        }
        return true;
    }
}
