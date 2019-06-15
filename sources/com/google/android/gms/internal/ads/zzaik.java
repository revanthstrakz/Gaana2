package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzv;
import java.util.Map;
import org.json.JSONObject;

@zzark
public final class zzaik implements zzaic, zzaii {
    private final Context mContext;
    private final zzbgg zzdin;

    public zzaik(Context context, zzbbi zzbbi, @Nullable zzcu zzcu, zzv zzv) throws zzbgq {
        this.mContext = context;
        zzbv.zzlg();
        this.zzdin = zzbgm.zza(context, zzbht.zzaey(), "", false, false, zzcu, zzbbi, null, null, null, zzum.zzoi());
        this.zzdin.getView().setWillNotDraw(true);
    }

    public final void zza(String str, Map map) {
        zzaid.zza((zzaic) this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzaid.zzb(this, str, jSONObject);
    }

    public final void zzb(String str, JSONObject jSONObject) {
        zzaid.zza((zzaic) this, str, jSONObject);
    }

    public final void zzh(String str, String str2) {
        zzaid.zza((zzaic) this, str, str2);
    }

    private static void runOnUiThread(Runnable runnable) {
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            runnable.run();
        } else {
            zzayh.zzelc.post(runnable);
        }
    }

    public final void zzcg(String str) {
        runOnUiThread(new zzail(this, str));
    }

    public final void zzcd(String str) {
        runOnUiThread(new zzaio(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", new Object[]{str})));
    }

    public final void zzce(String str) {
        runOnUiThread(new zzaip(this, str));
    }

    public final void zzcf(String str) {
        runOnUiThread(new zzaiq(this, str));
    }

    public final void zza(String str, zzu<? super zzajr> zzu) {
        this.zzdin.zza(str, new zzair(this, zzu));
    }

    public final void zzb(String str, zzu<? super zzajr> zzu) {
        this.zzdin.zza(str, new zzaim(zzu));
    }

    public final void zza(zzaij zzaij) {
        zzbhn zzadl = this.zzdin.zzadl();
        zzaij.getClass();
        zzadl.zza(zzain.zzb(zzaij));
    }

    public final zzajs zzua() {
        return new zzajt(this);
    }

    public final void destroy() {
        this.zzdin.destroy();
    }

    public final boolean isDestroyed() {
        return this.zzdin.isDestroyed();
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzck(String str) {
        this.zzdin.zzcg(str);
    }
}
