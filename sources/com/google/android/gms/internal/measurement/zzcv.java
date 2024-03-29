package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences.Editor;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.UUID;

public final class zzcv {
    private final String name;
    private final long zzabv;
    private final /* synthetic */ zzct zzabw;

    private zzcv(zzct zzct, String str, long j) {
        this.zzabw = zzct;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(j > 0);
        this.name = str;
        this.zzabv = j;
    }

    private final void zzfl() {
        long currentTimeMillis = this.zzabw.zzbx().currentTimeMillis();
        Editor edit = this.zzabw.zzabr.edit();
        edit.remove(zzfp());
        edit.remove(zzfq());
        edit.putLong(zzfo(), currentTimeMillis);
        edit.commit();
    }

    public final void zzad(String str) {
        if (zzfn() == 0) {
            zzfl();
        }
        if (str == null) {
            str = "";
        }
        synchronized (this) {
            long j = this.zzabw.zzabr.getLong(zzfp(), 0);
            if (j <= 0) {
                Editor edit = this.zzabw.zzabr.edit();
                edit.putString(zzfq(), str);
                edit.putLong(zzfp(), 1);
                edit.apply();
                return;
            }
            long j2 = j + 1;
            Object obj = (UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE) < Long.MAX_VALUE / j2 ? 1 : null;
            Editor edit2 = this.zzabw.zzabr.edit();
            if (obj != null) {
                edit2.putString(zzfq(), str);
            }
            edit2.putLong(zzfp(), j2);
            edit2.apply();
        }
    }

    public final Pair<String, Long> zzfm() {
        long zzfn = zzfn();
        if (zzfn == 0) {
            zzfn = 0;
        } else {
            zzfn = Math.abs(zzfn - this.zzabw.zzbx().currentTimeMillis());
        }
        if (zzfn < this.zzabv) {
            return null;
        }
        if (zzfn > (this.zzabv << 1)) {
            zzfl();
            return null;
        }
        String string = this.zzabw.zzabr.getString(zzfq(), null);
        long j = this.zzabw.zzabr.getLong(zzfp(), 0);
        zzfl();
        if (string == null || j <= 0) {
            return null;
        }
        return new Pair(string, Long.valueOf(j));
    }

    private final long zzfn() {
        return this.zzabw.zzabr.getLong(zzfo(), 0);
    }

    private final String zzfo() {
        return String.valueOf(this.name).concat(":start");
    }

    private final String zzfp() {
        return String.valueOf(this.name).concat(":count");
    }

    @VisibleForTesting
    private final String zzfq() {
        return String.valueOf(this.name).concat(":value");
    }

    /* synthetic */ zzcv(zzct zzct, String str, long j, zzcu zzcu) {
        this(zzct, str, j);
    }
}
