package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import org.json.JSONObject;

@zzark
public final class zzahv extends zzaig<zzajr> implements zzaic, zzaii {
    private final zzbig zzdid;
    private zzaij zzdie;

    public zzahv(Context context, zzbbi zzbbi) throws zzbgq {
        try {
            this.zzdid = new zzbig(context, new zzaib(this, null));
            this.zzdid.setWillNotDraw(true);
            this.zzdid.addJavascriptInterface(new zzaia(this, null), "GoogleJsInterface");
            zzbv.zzlf().zza(context, zzbbi.zzdp, this.zzdid.getSettings());
            super.zzi(this);
        } catch (Throwable th) {
            zzbgq zzbgq = new zzbgq("Init failed.", th);
        }
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

    public final void zzcd(String str) {
        zzce(String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head></html>", new Object[]{str}));
    }

    public final void zzce(String str) {
        zzbcg.zzepo.execute(new zzahw(this, str));
    }

    public final void zzcf(String str) {
        zzbcg.zzepo.execute(new zzahx(this, str));
    }

    public final void zza(zzaij zzaij) {
        this.zzdie = zzaij;
    }

    public final void destroy() {
        this.zzdid.destroy();
    }

    public final boolean isDestroyed() {
        return this.zzdid.isDestroyed();
    }

    public final zzajs zzua() {
        return new zzajt(this);
    }

    public final void zzcg(String str) {
        zzbcg.zzepo.execute(new zzahy(this, str));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzch(String str) {
        this.zzdid.zzcg(str);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzci(String str) {
        this.zzdid.loadUrl(str);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzcj(String str) {
        this.zzdid.loadData(str, "text/html", "UTF-8");
    }
}
