package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

public abstract class zzcv<R extends Result> extends ApiMethodImpl<R, zzdd> {
    public zzcv(GoogleApiClient googleApiClient) {
        super(Cast.API, googleApiClient);
    }

    public final void zzp(int i) {
        setResult(createFailedResult(new Status(2001)));
    }
}
