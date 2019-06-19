package com.google.android.gms.cast;

import android.annotation.SuppressLint;
import com.google.android.gms.cast.Cast.MessageReceivedCallback;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzcv;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzdx;
import com.google.android.gms.internal.cast.zzea;
import com.google.android.gms.internal.cast.zzeb;
import com.google.android.gms.internal.cast.zzec;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

@SuppressLint({"MissingRemoteException"})
@Deprecated
public class RemoteMediaPlayer implements MessageReceivedCallback {
    public static final String NAMESPACE = zzdx.NAMESPACE;
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_CANCELED = 2101;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    public static final int STATUS_TIMED_OUT = 2102;
    private final Object lock;
    private final zzdx zzff;
    private final zza zzfg;
    private OnPreloadStatusUpdatedListener zzfh;
    private OnQueueStatusUpdatedListener zzfi;
    private OnMetadataUpdatedListener zzfj;
    private OnStatusUpdatedListener zzfk;

    @Deprecated
    public interface MediaChannelResult extends Result {
        JSONObject getCustomData();
    }

    @Deprecated
    public interface OnMetadataUpdatedListener {
        void onMetadataUpdated();
    }

    @Deprecated
    public interface OnPreloadStatusUpdatedListener {
        void onPreloadStatusUpdated();
    }

    @Deprecated
    public interface OnQueueStatusUpdatedListener {
        void onQueueStatusUpdated();
    }

    @Deprecated
    public interface OnStatusUpdatedListener {
        void onStatusUpdated();
    }

    private class zza implements zzeb {
        private GoogleApiClient zzgi;
        private long zzgj = 0;

        public final void zza(GoogleApiClient googleApiClient) {
            this.zzgi = googleApiClient;
        }

        public final void zza(String str, String str2, long j, String str3) {
            if (this.zzgi == null) {
                throw new IllegalStateException("No GoogleApiClient available");
            }
            Cast.CastApi.sendMessage(this.zzgi, str, str2).setResultCallback(new zzbu(this, j));
        }

        public final long zzr() {
            long j = this.zzgj + 1;
            this.zzgj = j;
            return j;
        }
    }

    @VisibleForTesting
    abstract class zzb extends zzcv<MediaChannelResult> {
        zzec zzgm;
        private final WeakReference<GoogleApiClient> zzgn;

        zzb(GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.zzgn = new WeakReference(googleApiClient);
            this.zzgm = new zzbv(this, RemoteMediaPlayer.this);
        }

        public abstract void zzb(zzdd zzdd) throws zzea;

        /* Access modifiers changed, original: protected|synthetic */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
        public /* synthetic */ void doExecute(com.google.android.gms.common.api.Api.AnyClient r5) throws android.os.RemoteException {
            /*
            r4 = this;
            r5 = (com.google.android.gms.internal.cast.zzdd) r5;
            r0 = com.google.android.gms.cast.RemoteMediaPlayer.this;
            r0 = r0.lock;
            monitor-enter(r0);
            r1 = r4.zzgn;	 Catch:{ all -> 0x004e }
            r1 = r1.get();	 Catch:{ all -> 0x004e }
            r1 = (com.google.android.gms.common.api.GoogleApiClient) r1;	 Catch:{ all -> 0x004e }
            r2 = 2100; // 0x834 float:2.943E-42 double:1.0375E-320;
            if (r1 != 0) goto L_0x0025;
        L_0x0015:
            r5 = new com.google.android.gms.common.api.Status;	 Catch:{ all -> 0x004e }
            r5.<init>(r2);	 Catch:{ all -> 0x004e }
            r5 = r4.createFailedResult(r5);	 Catch:{ all -> 0x004e }
            r5 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r5;	 Catch:{ all -> 0x004e }
            r4.setResult(r5);	 Catch:{ all -> 0x004e }
            monitor-exit(r0);	 Catch:{ all -> 0x004e }
            return;
        L_0x0025:
            r3 = com.google.android.gms.cast.RemoteMediaPlayer.this;	 Catch:{ all -> 0x004e }
            r3 = r3.zzfg;	 Catch:{ all -> 0x004e }
            r3.zza(r1);	 Catch:{ all -> 0x004e }
            r4.zzb(r5);	 Catch:{ IllegalArgumentException -> 0x004c, Throwable -> 0x0032 }
            goto L_0x0040;
        L_0x0032:
            r5 = new com.google.android.gms.common.api.Status;	 Catch:{ all -> 0x004e }
            r5.<init>(r2);	 Catch:{ all -> 0x004e }
            r5 = r4.createFailedResult(r5);	 Catch:{ all -> 0x004e }
            r5 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r5;	 Catch:{ all -> 0x004e }
            r4.setResult(r5);	 Catch:{ all -> 0x004e }
        L_0x0040:
            r5 = com.google.android.gms.cast.RemoteMediaPlayer.this;	 Catch:{ all -> 0x004e }
            r5 = r5.zzfg;	 Catch:{ all -> 0x004e }
            r1 = 0;
            r5.zza(r1);	 Catch:{ all -> 0x004e }
            monitor-exit(r0);	 Catch:{ all -> 0x004e }
            return;
        L_0x004c:
            r5 = move-exception;
            throw r5;	 Catch:{ all -> 0x004e }
        L_0x004e:
            r5 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x004e }
            throw r5;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.RemoteMediaPlayer$zzb.doExecute(com.google.android.gms.common.api.Api$AnyClient):void");
        }

        public /* synthetic */ Result createFailedResult(Status status) {
            return new zzbw(this, status);
        }
    }

    private static final class zzc implements MediaChannelResult {
        private final Status zzgq;
        private final JSONObject zzp;

        zzc(Status status, JSONObject jSONObject) {
            this.zzgq = status;
            this.zzp = jSONObject;
        }

        public final Status getStatus() {
            return this.zzgq;
        }

        public final JSONObject getCustomData() {
            return this.zzp;
        }
    }

    public RemoteMediaPlayer() {
        this(new zzdx(null));
    }

    @VisibleForTesting
    private RemoteMediaPlayer(zzdx zzdx) {
        this.lock = new Object();
        this.zzff = zzdx;
        this.zzff.zza(new zzax(this));
        this.zzfg = new zza();
        this.zzff.zza(this.zzfg);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo) {
        return load(googleApiClient, mediaInfo, true, 0, null, null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z) {
        return load(googleApiClient, mediaInfo, z, 0, null, null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z, long j) {
        return load(googleApiClient, mediaInfo, z, j, null, null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z, long j, JSONObject jSONObject) {
        return load(googleApiClient, mediaInfo, z, j, null, jSONObject);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z, long j, long[] jArr, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbi(this, googleApiClient, z, j, jArr, jSONObject, mediaInfo));
    }

    public PendingResult<MediaChannelResult> pause(GoogleApiClient googleApiClient) {
        return pause(googleApiClient, null);
    }

    public PendingResult<MediaChannelResult> pause(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbn(this, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> stop(GoogleApiClient googleApiClient) {
        return stop(googleApiClient, null);
    }

    public PendingResult<MediaChannelResult> stop(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbo(this, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> play(GoogleApiClient googleApiClient) {
        return play(googleApiClient, null);
    }

    public PendingResult<MediaChannelResult> play(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbp(this, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient googleApiClient, long j) {
        return seek(googleApiClient, j, 0, null);
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient googleApiClient, long j, int i) {
        return seek(googleApiClient, j, i, null);
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient googleApiClient, long j, int i, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbq(this, googleApiClient, j, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient googleApiClient, double d) throws IllegalArgumentException {
        return setStreamVolume(googleApiClient, d, null);
    }

    public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient googleApiClient, double d, JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzbr(this, googleApiClient, d, jSONObject));
    }

    public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient googleApiClient, boolean z) {
        return setStreamMute(googleApiClient, z, null);
    }

    public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient googleApiClient, boolean z, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbs(this, googleApiClient, z, jSONObject));
    }

    public PendingResult<MediaChannelResult> requestStatus(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zzbt(this, googleApiClient));
    }

    public PendingResult<MediaChannelResult> setActiveMediaTracks(GoogleApiClient googleApiClient, long[] jArr) {
        return googleApiClient.execute(new zzay(this, googleApiClient, jArr));
    }

    public PendingResult<MediaChannelResult> setTextTrackStyle(GoogleApiClient googleApiClient, TextTrackStyle textTrackStyle) {
        return googleApiClient.execute(new zzaz(this, googleApiClient, textTrackStyle));
    }

    public PendingResult<MediaChannelResult> queueLoad(GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, int i2, JSONObject jSONObject) throws IllegalArgumentException {
        return queueLoad(googleApiClient, mediaQueueItemArr, i, i2, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueLoad(GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzba(this, googleApiClient, mediaQueueItemArr, i, i2, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueInsertItems(GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzbb(this, googleApiClient, mediaQueueItemArr, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueAppendItem(GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, JSONObject jSONObject) throws IllegalArgumentException {
        return queueInsertItems(googleApiClient, new MediaQueueItem[]{mediaQueueItem}, 0, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, int i, JSONObject jSONObject) {
        return queueInsertAndPlayItem(googleApiClient, mediaQueueItem, i, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, int i, long j, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbc(this, googleApiClient, mediaQueueItem, i, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueUpdateItems(GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbd(this, googleApiClient, mediaQueueItemArr, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueRemoveItems(GoogleApiClient googleApiClient, int[] iArr, JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzbe(this, googleApiClient, iArr, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueReorderItems(GoogleApiClient googleApiClient, int[] iArr, int i, JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzbf(this, googleApiClient, iArr, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queuePrev(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbg(this, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueNext(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbh(this, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueSetRepeatMode(GoogleApiClient googleApiClient, int i, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbj(this, googleApiClient, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueRemoveItem(GoogleApiClient googleApiClient, int i, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbk(this, googleApiClient, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(GoogleApiClient googleApiClient, int i, JSONObject jSONObject) {
        return queueJumpToItem(googleApiClient, i, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(GoogleApiClient googleApiClient, int i, long j, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbl(this, googleApiClient, i, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueMoveItemToNewIndex(GoogleApiClient googleApiClient, int i, int i2, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbm(this, googleApiClient, i, i2, jSONObject));
    }

    private final int zzf(int i) {
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return -1;
        }
        for (int i2 = 0; i2 < mediaStatus.getQueueItemCount(); i2++) {
            if (mediaStatus.getQueueItem(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    public long getApproximateStreamPosition() {
        long approximateStreamPosition;
        synchronized (this.lock) {
            approximateStreamPosition = this.zzff.getApproximateStreamPosition();
        }
        return approximateStreamPosition;
    }

    public long getStreamDuration() {
        long streamDuration;
        synchronized (this.lock) {
            streamDuration = this.zzff.getStreamDuration();
        }
        return streamDuration;
    }

    public MediaStatus getMediaStatus() {
        MediaStatus mediaStatus;
        synchronized (this.lock) {
            mediaStatus = this.zzff.getMediaStatus();
        }
        return mediaStatus;
    }

    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfo;
        synchronized (this.lock) {
            mediaInfo = this.zzff.getMediaInfo();
        }
        return mediaInfo;
    }

    public void setOnStatusUpdatedListener(OnStatusUpdatedListener onStatusUpdatedListener) {
        this.zzfk = onStatusUpdatedListener;
    }

    private final void onStatusUpdated() {
        if (this.zzfk != null) {
            this.zzfk.onStatusUpdated();
        }
    }

    public void setOnMetadataUpdatedListener(OnMetadataUpdatedListener onMetadataUpdatedListener) {
        this.zzfj = onMetadataUpdatedListener;
    }

    private final void onMetadataUpdated() {
        if (this.zzfj != null) {
            this.zzfj.onMetadataUpdated();
        }
    }

    public void setOnQueueStatusUpdatedListener(OnQueueStatusUpdatedListener onQueueStatusUpdatedListener) {
        this.zzfi = onQueueStatusUpdatedListener;
    }

    private final void onQueueStatusUpdated() {
        if (this.zzfi != null) {
            this.zzfi.onQueueStatusUpdated();
        }
    }

    public void setOnPreloadStatusUpdatedListener(OnPreloadStatusUpdatedListener onPreloadStatusUpdatedListener) {
        this.zzfh = onPreloadStatusUpdatedListener;
    }

    private final void onPreloadStatusUpdated() {
        if (this.zzfh != null) {
            this.zzfh.onPreloadStatusUpdated();
        }
    }

    public String getNamespace() {
        return this.zzff.getNamespace();
    }

    public void onMessageReceived(CastDevice castDevice, String str, String str2) {
        this.zzff.zzo(str2);
    }
}
