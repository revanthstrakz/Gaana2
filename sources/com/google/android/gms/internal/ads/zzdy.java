package com.google.android.gms.internal.ads;

import android.provider.Settings.SettingNotFoundException;
import java.lang.reflect.InvocationTargetException;

public final class zzdy extends zzeu {
    public zzdy(zzdl zzdl, String str, String str2, zzbl zzbl, int i, int i2) {
        super(zzdl, str, str2, zzbl, i, 49);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zzas() throws IllegalAccessException, InvocationTargetException {
        this.zzun.zzfg = Integer.valueOf(2);
        try {
            boolean booleanValue = ((Boolean) this.zzuw.invoke(null, new Object[]{this.zzqo.getContext()})).booleanValue();
            this.zzun.zzfg = Integer.valueOf(booleanValue);
        } catch (InvocationTargetException e) {
            if (!(e.getTargetException() instanceof SettingNotFoundException)) {
                throw e;
            }
        }
    }
}
