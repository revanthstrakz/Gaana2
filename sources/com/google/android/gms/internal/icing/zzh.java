package com.google.android.gms.internal.icing;

import android.accounts.Account;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.ArrayList;
import java.util.List;

@ShowFirstParty
public final class zzh {
    private Account account;
    private String zzj;
    private boolean zzk;
    private List<zzl> zzl;

    public final zzh zza(zzl zzl) {
        if (this.zzl == null && zzl != null) {
            this.zzl = new ArrayList();
        }
        if (zzl != null) {
            this.zzl.add(zzl);
        }
        return this;
    }

    public final zzh zza(String str) {
        this.zzj = str;
        return this;
    }

    public final zzh zza(boolean z) {
        this.zzk = true;
        return this;
    }

    public final zzh zza(Account account) {
        this.account = account;
        return this;
    }

    public final zzg zzb() {
        return new zzg(this.zzj, this.zzk, this.account, this.zzl != null ? (zzl[]) this.zzl.toArray(new zzl[this.zzl.size()]) : null);
    }
}
