package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzp;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import java.util.Map;

final class zzem extends zzbq {
    private static final String ID = zza.RESOLUTION.toString();
    private final Context zzri;

    public zzem(Context context) {
        super(ID, new String[0]);
        this.zzri = context;
    }

    public final boolean zznk() {
        return true;
    }

    public final zzp zzc(Map<String, zzp> map) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.zzri.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        StringBuilder stringBuilder = new StringBuilder(23);
        stringBuilder.append(i);
        stringBuilder.append(AvidJSONUtil.KEY_X);
        stringBuilder.append(i2);
        return zzgj.zzj(stringBuilder.toString());
    }
}
