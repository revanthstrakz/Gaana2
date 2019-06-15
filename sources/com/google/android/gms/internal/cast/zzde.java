package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast.ApplicationConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzde implements ApplicationConnectionResult {
    private final Status zzgq;
    private final ApplicationMetadata zzyt;
    private final String zzyu;
    private final String zzyv;
    private final boolean zzyw;

    public zzde(Status status, ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        this.zzgq = status;
        this.zzyt = applicationMetadata;
        this.zzyu = str;
        this.zzyv = str2;
        this.zzyw = z;
    }

    public zzde(Status status) {
        this(status, null, null, null, false);
    }

    public final Status getStatus() {
        return this.zzgq;
    }

    public final ApplicationMetadata getApplicationMetadata() {
        return this.zzyt;
    }

    public final String getApplicationStatus() {
        return this.zzyu;
    }

    public final String getSessionId() {
        return this.zzyv;
    }

    public final boolean getWasLaunched() {
        return this.zzyw;
    }
}
