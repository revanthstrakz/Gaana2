package com.google.android.gms.internal.plus;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.People.LoadPeopleResult;
import com.google.android.gms.plus.Plus.zza;

abstract class zzp extends zza<LoadPeopleResult> {
    private zzp(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzp(GoogleApiClient googleApiClient, zzk zzk) {
        this(googleApiClient);
    }

    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzq(this, status);
    }
}
