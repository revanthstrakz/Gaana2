package com.google.android.gms.cast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Display;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzdn;
import com.google.android.gms.internal.cast.zzdv;
import com.google.android.gms.internal.cast.zzeh;
import com.google.android.gms.internal.cast.zzer;

public final class CastRemoteDisplay {
    @Deprecated
    public static final Api<CastRemoteDisplayOptions> API = new Api("CastRemoteDisplay.API", zzad, zzdv.zzzg);
    public static final int CONFIGURATION_INTERACTIVE_NONREALTIME = 2;
    public static final int CONFIGURATION_INTERACTIVE_REALTIME = 1;
    public static final int CONFIGURATION_NONINTERACTIVE = 3;
    @Deprecated
    public static final CastRemoteDisplayApi CastRemoteDisplayApi = new zzeh(API);
    public static final String EXTRA_INT_SESSION_ENDED_STATUS_CODE = "extra_int_session_ended_status_code";
    private static final AbstractClientBuilder<zzer, CastRemoteDisplayOptions> zzad = new zzo();

    public @interface Configuration {
    }

    @Deprecated
    public static final class CastRemoteDisplayOptions implements HasOptions {
        final CastDevice zzaj;
        final CastRemoteDisplaySessionCallbacks zzbc;
        final int zzbd;

        @Deprecated
        public static final class Builder {
            CastDevice zzaj;
            int zzbd = 2;
            CastRemoteDisplaySessionCallbacks zzbe;

            public Builder(CastDevice castDevice, CastRemoteDisplaySessionCallbacks castRemoteDisplaySessionCallbacks) {
                Preconditions.checkNotNull(castDevice, "CastDevice parameter cannot be null");
                this.zzaj = castDevice;
                this.zzbe = castRemoteDisplaySessionCallbacks;
            }

            public final Builder setConfigPreset(@Configuration int i) {
                this.zzbd = i;
                return this;
            }

            public final CastRemoteDisplayOptions build() {
                return new CastRemoteDisplayOptions(this, null);
            }
        }

        private CastRemoteDisplayOptions(Builder builder) {
            this.zzaj = builder.zzaj;
            this.zzbc = builder.zzbe;
            this.zzbd = builder.zzbd;
        }

        /* synthetic */ CastRemoteDisplayOptions(Builder builder, zzo zzo) {
            this(builder);
        }
    }

    @Deprecated
    public interface CastRemoteDisplaySessionCallbacks {
        void onRemoteDisplayEnded(Status status);
    }

    @Deprecated
    public interface CastRemoteDisplaySessionResult extends Result {
        Display getPresentationDisplay();
    }

    public static final boolean isRemoteDisplaySdkSupported(Context context) {
        zzdn.initialize(context);
        return ((Boolean) zzdn.zzze.get()).booleanValue();
    }

    private CastRemoteDisplay() {
    }

    public static CastRemoteDisplayClient getClient(@NonNull Context context) {
        return new CastRemoteDisplayClient(context);
    }
}
