package com.google.android.gms.internal.ads;

import java.io.ByteArrayInputStream;
import org.json.JSONObject;

final /* synthetic */ class zzakb implements zzajx {
    static final zzajx zzdkd = new zzakb();

    private zzakb() {
    }

    public final Object zze(JSONObject jSONObject) {
        return new ByteArrayInputStream(jSONObject.toString().getBytes(zzaka.UTF_8));
    }
}
