package com.google.android.gms.internal.ads;

import com.facebook.internal.NativeProtocol;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.til.colombia.android.internal.e;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
public class zzaok {
    private final zzbgg zzdin;
    private final String zzdqm;

    public zzaok(zzbgg zzbgg) {
        this(zzbgg, "");
    }

    public zzaok(zzbgg zzbgg, String str) {
        this.zzdin = zzbgg;
        this.zzdqm = str;
    }

    public final void zzda(String str) {
        try {
            this.zzdin.zza("onError", new JSONObject().put("message", str).put(NativeProtocol.WEB_DIALOG_ACTION, this.zzdqm));
        } catch (JSONException e) {
            zzbbd.zzb("Error occurred while dispatching error event.", e);
        }
    }

    public final void zzdb(String str) {
        try {
            this.zzdin.zza("onReadyEventReceived", new JSONObject().put("js", str));
        } catch (JSONException e) {
            zzbbd.zzb("Error occured while dispatching ready Event.", e);
        }
    }

    public final void zzb(int i, int i2, int i3, int i4) {
        try {
            this.zzdin.zza("onSizeChanged", new JSONObject().put(AvidJSONUtil.KEY_X, i).put(AvidJSONUtil.KEY_Y, i2).put("width", i3).put("height", i4));
        } catch (JSONException e) {
            zzbbd.zzb("Error occured while dispatching size change.", e);
        }
    }

    public final void zzc(int i, int i2, int i3, int i4) {
        try {
            this.zzdin.zza("onDefaultPositionReceived", new JSONObject().put(AvidJSONUtil.KEY_X, i).put(AvidJSONUtil.KEY_Y, i2).put("width", i3).put("height", i4));
        } catch (JSONException e) {
            zzbbd.zzb("Error occured while dispatching default position.", e);
        }
    }

    public final void zzdc(String str) {
        try {
            this.zzdin.zza("onStateChanged", new JSONObject().put("state", str));
        } catch (JSONException e) {
            zzbbd.zzb("Error occured while dispatching state change.", e);
        }
    }

    public final void zza(int i, int i2, int i3, int i4, float f, int i5) {
        try {
            this.zzdin.zza("onScreenInfoChanged", new JSONObject().put("width", i).put("height", i2).put("maxSizeWidth", i3).put("maxSizeHeight", i4).put(e.D, (double) f).put("rotation", i5));
        } catch (JSONException e) {
            zzbbd.zzb("Error occured while obtaining screen information.", e);
        }
    }
}
