package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions.Builder;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzcv;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzdv;
import java.io.IOException;

public final class Cast {
    public static final int ACTIVE_INPUT_STATE_NO = 0;
    public static final int ACTIVE_INPUT_STATE_UNKNOWN = -1;
    public static final int ACTIVE_INPUT_STATE_YES = 1;
    public static final Api<CastOptions> API = new Api("Cast.API", zzad, zzdv.zzzf);
    public static final CastApi CastApi = new zza();
    public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
    public static final int MAX_MESSAGE_LENGTH = 65536;
    public static final int MAX_NAMESPACE_LENGTH = 128;
    public static final int STANDBY_STATE_NO = 0;
    public static final int STANDBY_STATE_UNKNOWN = -1;
    public static final int STANDBY_STATE_YES = 1;
    @VisibleForTesting
    private static final AbstractClientBuilder<zzdd, CastOptions> zzad = new zze();

    public interface ApplicationConnectionResult extends Result {
        ApplicationMetadata getApplicationMetadata();

        String getApplicationStatus();

        String getSessionId();

        boolean getWasLaunched();
    }

    @Deprecated
    public interface CastApi {

        public static final class zza implements CastApi {
            public final void requestStatus(GoogleApiClient googleApiClient) throws IOException, IllegalStateException {
                try {
                    ((zzdd) googleApiClient.getClient(zzdv.zzzf)).requestStatus();
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }

            public final PendingResult<Status> sendMessage(GoogleApiClient googleApiClient, String str, String str2) {
                return googleApiClient.execute(new zzf(this, googleApiClient, str, str2));
            }

            public final PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str) {
                return googleApiClient.execute(new zzg(this, googleApiClient, str));
            }

            public final PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions) {
                return googleApiClient.execute(new zzh(this, googleApiClient, str, launchOptions));
            }

            @Deprecated
            public final PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, boolean z) {
                return launchApplication(googleApiClient, str, new Builder().setRelaunchIfRunning(z).build());
            }

            @ShowFirstParty
            private final PendingResult<ApplicationConnectionResult> zza(GoogleApiClient googleApiClient, String str, String str2, zzag zzag) {
                return googleApiClient.execute(new zzi(this, googleApiClient, str, str2, null));
            }

            public final PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str, String str2) {
                return zza(googleApiClient, str, str2, null);
            }

            public final PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str) {
                return zza(googleApiClient, str, null, null);
            }

            public final PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient) {
                return zza(googleApiClient, null, null, null);
            }

            public final PendingResult<Status> leaveApplication(GoogleApiClient googleApiClient) {
                return googleApiClient.execute(new zzj(this, googleApiClient));
            }

            public final PendingResult<Status> stopApplication(GoogleApiClient googleApiClient) {
                return googleApiClient.execute(new zzk(this, googleApiClient));
            }

            public final PendingResult<Status> stopApplication(GoogleApiClient googleApiClient, String str) {
                return googleApiClient.execute(new zzl(this, googleApiClient, str));
            }

            public final void setVolume(GoogleApiClient googleApiClient, double d) throws IOException, IllegalArgumentException, IllegalStateException {
                try {
                    ((zzdd) googleApiClient.getClient(zzdv.zzzf)).setVolume(d);
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }

            public final double getVolume(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzdd) googleApiClient.getClient(zzdv.zzzf)).getVolume();
            }

            public final void setMute(GoogleApiClient googleApiClient, boolean z) throws IOException, IllegalStateException {
                try {
                    ((zzdd) googleApiClient.getClient(zzdv.zzzf)).setMute(z);
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }

            public final boolean isMute(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzdd) googleApiClient.getClient(zzdv.zzzf)).isMute();
            }

            public final int getActiveInputState(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzdd) googleApiClient.getClient(zzdv.zzzf)).getActiveInputState();
            }

            public final int getStandbyState(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzdd) googleApiClient.getClient(zzdv.zzzf)).getStandbyState();
            }

            public final ApplicationMetadata getApplicationMetadata(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzdd) googleApiClient.getClient(zzdv.zzzf)).getApplicationMetadata();
            }

            public final String getApplicationStatus(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzdd) googleApiClient.getClient(zzdv.zzzf)).getApplicationStatus();
            }

            public final void setMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str, MessageReceivedCallback messageReceivedCallback) throws IOException, IllegalStateException {
                try {
                    ((zzdd) googleApiClient.getClient(zzdv.zzzf)).setMessageReceivedCallbacks(str, messageReceivedCallback);
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }

            public final void removeMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str) throws IOException, IllegalArgumentException {
                try {
                    ((zzdd) googleApiClient.getClient(zzdv.zzzf)).removeMessageReceivedCallbacks(str);
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }
        }

        int getActiveInputState(GoogleApiClient googleApiClient) throws IllegalStateException;

        ApplicationMetadata getApplicationMetadata(GoogleApiClient googleApiClient) throws IllegalStateException;

        String getApplicationStatus(GoogleApiClient googleApiClient) throws IllegalStateException;

        int getStandbyState(GoogleApiClient googleApiClient) throws IllegalStateException;

        double getVolume(GoogleApiClient googleApiClient) throws IllegalStateException;

        boolean isMute(GoogleApiClient googleApiClient) throws IllegalStateException;

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str, String str2);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions);

        @Deprecated
        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, boolean z);

        PendingResult<Status> leaveApplication(GoogleApiClient googleApiClient);

        void removeMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str) throws IOException, IllegalArgumentException;

        void requestStatus(GoogleApiClient googleApiClient) throws IOException, IllegalStateException;

        PendingResult<Status> sendMessage(GoogleApiClient googleApiClient, String str, String str2);

        void setMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str, MessageReceivedCallback messageReceivedCallback) throws IOException, IllegalStateException;

        void setMute(GoogleApiClient googleApiClient, boolean z) throws IOException, IllegalStateException;

        void setVolume(GoogleApiClient googleApiClient, double d) throws IOException, IllegalArgumentException, IllegalStateException;

        PendingResult<Status> stopApplication(GoogleApiClient googleApiClient);

        PendingResult<Status> stopApplication(GoogleApiClient googleApiClient, String str);
    }

    public static final class CastOptions implements HasOptions {
        final Bundle extras;
        final CastDevice zzaj;
        final Listener zzak;
        private final int zzal;

        public static final class Builder {
            private Bundle extras;
            CastDevice zzaj;
            Listener zzak;
            private int zzal = 0;

            public Builder(CastDevice castDevice, Listener listener) {
                Preconditions.checkNotNull(castDevice, "CastDevice parameter cannot be null");
                Preconditions.checkNotNull(listener, "CastListener parameter cannot be null");
                this.zzaj = castDevice;
                this.zzak = listener;
            }

            public final Builder setVerboseLoggingEnabled(boolean z) {
                if (z) {
                    this.zzal |= 1;
                } else {
                    this.zzal &= -2;
                }
                return this;
            }

            public final Builder zza(Bundle bundle) {
                this.extras = bundle;
                return this;
            }

            public final CastOptions build() {
                return new CastOptions(this, null);
            }
        }

        private CastOptions(Builder builder) {
            this.zzaj = builder.zzaj;
            this.zzak = builder.zzak;
            this.zzal = builder.zzal;
            this.extras = builder.extras;
        }

        @Deprecated
        public static Builder builder(CastDevice castDevice, Listener listener) {
            return new Builder(castDevice, listener);
        }

        /* synthetic */ CastOptions(Builder builder, zze zze) {
            this(builder);
        }
    }

    public static class Listener {
        public void onActiveInputStateChanged(int i) {
        }

        public void onApplicationDisconnected(int i) {
        }

        public void onApplicationMetadataChanged(ApplicationMetadata applicationMetadata) {
        }

        public void onApplicationStatusChanged() {
        }

        public void onStandbyStateChanged(int i) {
        }

        public void onVolumeChanged() {
        }
    }

    public interface MessageReceivedCallback {
        void onMessageReceived(CastDevice castDevice, String str, String str2);
    }

    @VisibleForTesting
    static abstract class zza extends zzcv<ApplicationConnectionResult> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* renamed from: zza */
        public void doExecute(zzdd zzdd) throws RemoteException {
        }

        public /* synthetic */ Result createFailedResult(Status status) {
            return new zzm(this, status);
        }
    }

    private Cast() {
    }
}
