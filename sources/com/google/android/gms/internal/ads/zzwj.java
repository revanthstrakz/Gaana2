package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import com.facebook.internal.NativeProtocol;
import java.util.HashMap;

@zzark
public class zzwj {
    private final zzvz zzckr;
    private final zzvy zzcks;
    private final zzzg zzckt;
    private final zzafa zzcku;
    private final zzavf zzckv;
    private final zzawf zzckw;
    private final zzaoo zzckx;
    private final zzafb zzcky;

    public zzwj(zzvz zzvz, zzvy zzvy, zzzg zzzg, zzafa zzafa, zzavf zzavf, zzawf zzawf, zzaoo zzaoo, zzafb zzafb) {
        this.zzckr = zzvz;
        this.zzcks = zzvy;
        this.zzckt = zzzg;
        this.zzcku = zzafa;
        this.zzckv = zzavf;
        this.zzckw = zzawf;
        this.zzckx = zzaoo;
        this.zzcky = zzafb;
    }

    private static void zza(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "no_ads_fallback");
        bundle.putString("flow", str);
        zzwu.zzpv().zza(context, zzwu.zzqb().zzdp, "gmob-apps", bundle, true);
    }

    public final zzxg zzb(Context context, String str, zzalg zzalg) {
        return (zzxg) new zzwo(this, context, str, zzalg).zzd(context, false);
    }

    public final zzadf zza(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        return (zzadf) new zzwq(this, frameLayout, frameLayout2, context).zzd(context, false);
    }

    public final zzadk zza(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        return (zzadk) new zzwr(this, view, hashMap, hashMap2).zzd(view.getContext(), false);
    }

    @Nullable
    public final zzaop zzb(Activity activity) {
        zzwl zzwl = new zzwl(this, activity);
        String str = "com.google.android.gms.ads.internal.overlay.useClientJar";
        Intent intent = activity.getIntent();
        boolean z = false;
        if (intent.hasExtra(str)) {
            z = intent.getBooleanExtra(str, false);
        } else {
            zzbbd.e("useClientJar flag not found in activity intent extras.");
        }
        return (zzaop) zzwl.zzd(activity, z);
    }
}
