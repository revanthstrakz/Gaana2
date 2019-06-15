package com.google.android.gms.cast;

import com.google.android.gms.internal.cast.zzdz;

final class zzax implements zzdz {
    private final /* synthetic */ RemoteMediaPlayer zzfl;

    zzax(RemoteMediaPlayer remoteMediaPlayer) {
        this.zzfl = remoteMediaPlayer;
    }

    public final void onAdBreakStatusUpdated() {
    }

    public final void zza(int[] iArr) {
    }

    public final void zza(int[] iArr, int i) {
    }

    public final void zzb(int[] iArr) {
    }

    public final void zzb(MediaQueueItem[] mediaQueueItemArr) {
    }

    public final void zzc(int[] iArr) {
    }

    public final void onStatusUpdated() {
        this.zzfl.onStatusUpdated();
    }

    public final void onMetadataUpdated() {
        this.zzfl.onMetadataUpdated();
    }

    public final void onQueueStatusUpdated() {
        this.zzfl.onQueueStatusUpdated();
    }

    public final void onPreloadStatusUpdated() {
        this.zzfl.onPreloadStatusUpdated();
    }
}
