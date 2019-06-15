package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.plus.PlusShare;
import java.util.HashMap;

@ShowFirstParty
public final class zzae extends zzi<zzae> {
    private String category;
    private String label;
    private long value;
    private String zzul;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("category", this.category);
        hashMap.put(NativeProtocol.WEB_DIALOG_ACTION, this.zzul);
        hashMap.put(PlusShare.KEY_CALL_TO_ACTION_LABEL, this.label);
        hashMap.put("value", Long.valueOf(this.value));
        return zzi.zza((Object) hashMap);
    }

    public final String zzbb() {
        return this.category;
    }

    public final String getAction() {
        return this.zzul;
    }

    public final String getLabel() {
        return this.label;
    }

    public final long getValue() {
        return this.value;
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzae zzae = (zzae) zzi;
        if (!TextUtils.isEmpty(this.category)) {
            zzae.category = this.category;
        }
        if (!TextUtils.isEmpty(this.zzul)) {
            zzae.zzul = this.zzul;
        }
        if (!TextUtils.isEmpty(this.label)) {
            zzae.label = this.label;
        }
        if (this.value != 0) {
            zzae.value = this.value;
        }
    }
}
