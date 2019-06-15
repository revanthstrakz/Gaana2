package com.google.android.gms.internal.icing;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzfj extends zzfi<FieldDescriptorType, Object> {
    zzfj(int i) {
        super(i, null);
    }

    public final void zzaj() {
        if (!isImmutable()) {
            Entry zzaj;
            for (int i = 0; i < zzdh(); i++) {
                zzaj = zzaj(i);
                if (((zzde) zzaj.getKey()).zzbn()) {
                    zzaj.setValue(Collections.unmodifiableList((List) zzaj.getValue()));
                }
            }
            for (Entry zzaj2 : zzdi()) {
                if (((zzde) zzaj2.getKey()).zzbn()) {
                    zzaj2.setValue(Collections.unmodifiableList((List) zzaj2.getValue()));
                }
            }
        }
        super.zzaj();
    }
}
