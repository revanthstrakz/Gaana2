package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzwp extends zzwo<FieldDescriptorType, Object> {
    zzwp(int i) {
        super(i, null);
    }

    public final void zzsw() {
        if (!isImmutable()) {
            Entry zzbx;
            for (int i = 0; i < zzyc(); i++) {
                zzbx = zzbx(i);
                if (((zzuh) zzbx.getKey()).zzwb()) {
                    zzbx.setValue(Collections.unmodifiableList((List) zzbx.getValue()));
                }
            }
            for (Entry zzbx2 : zzyd()) {
                if (((zzuh) zzbx2.getKey()).zzwb()) {
                    zzbx2.setValue(Collections.unmodifiableList((List) zzbx2.getValue()));
                }
            }
        }
        super.zzsw();
    }
}
