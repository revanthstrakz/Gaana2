package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

final class zaao extends zabf {
    private final /* synthetic */ ConnectionResult zagl;
    private final /* synthetic */ zaan zagm;

    zaao(zaan zaan, zabd zabd, ConnectionResult connectionResult) {
        this.zagm = zaan;
        this.zagl = connectionResult;
        super(zabd);
    }

    public final void zaan() {
        this.zagm.zagi.zae(this.zagl);
    }
}
