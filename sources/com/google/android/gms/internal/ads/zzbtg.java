package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzbtg extends zzbtf<FieldDescriptorType, Object> {
    zzbtg(int i) {
        super(i, null);
    }

    public final void zzakj() {
        if (!isImmutable()) {
            Entry zzfy;
            for (int i = 0; i < zzaop(); i++) {
                zzfy = zzfy(i);
                if (((zzbqw) zzfy.getKey()).zzamn()) {
                    zzfy.setValue(Collections.unmodifiableList((List) zzfy.getValue()));
                }
            }
            for (Entry zzfy2 : zzaoq()) {
                if (((zzbqw) zzfy2.getKey()).zzamn()) {
                    zzfy2.setValue(Collections.unmodifiableList((List) zzfy2.getValue()));
                }
            }
        }
        super.zzakj();
    }
}
