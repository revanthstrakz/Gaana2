package com.google.android.gms.internal.cast;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.cast.zzbx;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import java.util.List;

public final class zzcx extends GoogleApi<NoOptions> {
    private static final Api<NoOptions> API = new Api("CastApi.API", zzad, CLIENT_KEY);
    private static final ClientKey<zzdb> CLIENT_KEY = new ClientKey();
    private static final AbstractClientBuilder<zzdb, NoOptions> zzad = new zzcy();

    public zzcx(@NonNull Context context) {
        super(context, API, null, Settings.DEFAULT_SETTINGS);
    }

    public final Task<Void> zza(@NonNull String[] strArr, String str, List<zzbx> list) {
        return doWrite((TaskApiCall) new zzcz(this, strArr, str, null));
    }
}
