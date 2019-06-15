package com.google.android.gms.internal.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.Wallet.zzb;

final class zzz extends zzb {
    private final /* synthetic */ int val$requestCode;

    zzz(zzy zzy, GoogleApiClient googleApiClient, int i) {
        this.val$requestCode = i;
        super(googleApiClient);
    }

    /* Access modifiers changed, original: protected|final */
    public final void zza(zzaf zzaf) {
        zzaf.zzb(this.val$requestCode);
        setResult(Status.RESULT_SUCCESS);
    }

    /* Access modifiers changed, original: protected|final|synthetic */
    public final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        doExecute((zzaf) anyClient);
    }
}
