package com.google.android.gms.internal.cast;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.comscore.measurement.MeasurementDispatcher;
import com.comscore.streaming.Constants;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.AdBreakStatus;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.zzam;
import com.google.android.gms.cast.zzap;
import com.google.android.gms.cast.zzas;
import com.google.android.gms.cast.zzbx;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@VisibleForTesting
public final class zzdx extends zzcw {
    public static final String NAMESPACE = zzdk.zzq("com.google.cast.media");
    @VisibleForTesting
    private final zzed zzaaa = new zzed(Constants.HEARTBEAT_STAGE_ONE_INTERVAL);
    @VisibleForTesting
    private final zzed zzaab = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzaac = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzaad = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzaae = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzaaf = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzaag = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzaah = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzaai = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzaaj = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzaak = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzaal = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzaam = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzaan = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzaao = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    private long zzzs;
    private MediaStatus zzzt;
    private Long zzzu;
    private zzdz zzzv;
    @VisibleForTesting
    private final zzed zzzw = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzzx = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzzy = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);
    @VisibleForTesting
    private final zzed zzzz = new zzed(MeasurementDispatcher.MILLIS_PER_DAY);

    public zzdx(String str) {
        super(NAMESPACE, "MediaControlChannel", null);
        zza(this.zzzw);
        zza(this.zzzx);
        zza(this.zzzy);
        zza(this.zzzz);
        zza(this.zzaaa);
        zza(this.zzaab);
        zza(this.zzaac);
        zza(this.zzaad);
        zza(this.zzaae);
        zza(this.zzaaf);
        zza(this.zzaag);
        zza(this.zzaah);
        zza(this.zzaai);
        zza(this.zzaaj);
        zza(this.zzaak);
        zza(this.zzaam);
        zza(this.zzaam);
        zza(this.zzaan);
        zza(this.zzaao);
        zzfc();
    }

    public final void zza(zzdz zzdz) {
        this.zzzv = zzdz;
    }

    public final long zza(@NonNull zzec zzec, @Nullable MediaInfo mediaInfo, @Nullable zzam zzam, @NonNull MediaLoadOptions mediaLoadOptions) throws IllegalStateException, IllegalArgumentException {
        if (mediaInfo == null && zzam == null) {
            throw new IllegalArgumentException("MediaInfo should not be null");
        }
        JSONObject jSONObject = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject.put("requestId", zzes);
            jSONObject.put("type", "LOAD");
            if (mediaInfo != null) {
                jSONObject.put(ShareConstants.WEB_DIALOG_PARAM_MEDIA, mediaInfo.toJson());
            }
            if (zzam != null) {
                jSONObject.put("queueData", zzam.toJson());
            }
            jSONObject.put("autoplay", mediaLoadOptions.getAutoplay());
            jSONObject.put("currentTime", ((double) mediaLoadOptions.getPlayPosition()) / 1000.0d);
            jSONObject.put("playbackRate", mediaLoadOptions.getPlaybackRate());
            if (mediaLoadOptions.getCredentials() != null) {
                jSONObject.put("credentials", mediaLoadOptions.getCredentials());
            }
            if (mediaLoadOptions.getCredentialsType() != null) {
                jSONObject.put("credentialsType", mediaLoadOptions.getCredentialsType());
            }
            long[] activeTrackIds = mediaLoadOptions.getActiveTrackIds();
            if (activeTrackIds != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < activeTrackIds.length; i++) {
                    jSONArray.put(i, activeTrackIds[i]);
                }
                jSONObject.put("activeTrackIds", jSONArray);
            }
            JSONObject customData = mediaLoadOptions.getCustomData();
            if (customData != null) {
                jSONObject.put("customData", customData);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), zzes, null);
        this.zzzw.zza(zzes, zzec);
        return zzes;
    }

    public final long zza(zzec zzec, JSONObject jSONObject) throws IllegalStateException, zzea {
        JSONObject jSONObject2 = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject2.put("requestId", zzes);
            jSONObject2.put("type", "PAUSE");
            jSONObject2.put("mediaSessionId", zzp());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzes, null);
        this.zzzx.zza(zzes, zzec);
        return zzes;
    }

    public final long zzb(zzec zzec, JSONObject jSONObject) throws IllegalStateException, zzea {
        JSONObject jSONObject2 = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject2.put("requestId", zzes);
            jSONObject2.put("type", "STOP");
            jSONObject2.put("mediaSessionId", zzp());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzes, null);
        this.zzzz.zza(zzes, zzec);
        return zzes;
    }

    public final long zzc(zzec zzec, JSONObject jSONObject) throws IllegalStateException, zzea {
        JSONObject jSONObject2 = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject2.put("requestId", zzes);
            jSONObject2.put("type", "PLAY");
            jSONObject2.put("mediaSessionId", zzp());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzes, null);
        this.zzzy.zza(zzes, zzec);
        return zzes;
    }

    public final long zza(zzec zzec, zzas zzas) throws IllegalStateException, zzea {
        JSONObject jSONObject = new JSONObject();
        long zzes = zzes();
        long position = zzas.zzn() ? 4294967296000L : zzas.getPosition();
        try {
            jSONObject.put("requestId", zzes);
            jSONObject.put("type", "SEEK");
            jSONObject.put("mediaSessionId", zzp());
            jSONObject.put("currentTime", ((double) position) / 1000.0d);
            if (zzas.zzm() == 1) {
                jSONObject.put("resumeState", "PLAYBACK_START");
            } else if (zzas.zzm() == 2) {
                jSONObject.put("resumeState", "PLAYBACK_PAUSE");
            }
            if (zzas.getCustomData() != null) {
                jSONObject.put("customData", zzas.getCustomData());
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), zzes, null);
        this.zzzu = Long.valueOf(position);
        this.zzaaa.zza(zzes, new zzdy(this, zzec));
        return zzes;
    }

    public final long zza(zzec zzec) throws IllegalStateException, zzea {
        JSONObject jSONObject = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject.put("requestId", zzes);
            jSONObject.put("type", "SKIP_AD");
            jSONObject.put("mediaSessionId", zzp());
        } catch (JSONException e) {
            this.zzxw.w(String.format(Locale.ROOT, "Error creating SkipAd message: %s", new Object[]{e.getMessage()}), new Object[0]);
        }
        zza(jSONObject.toString(), zzes, null);
        this.zzaao.zza(zzes, zzec);
        return zzes;
    }

    public final long zza(zzec zzec, double d, JSONObject jSONObject) throws IllegalStateException, zzea, IllegalArgumentException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            StringBuilder stringBuilder = new StringBuilder(41);
            stringBuilder.append("Volume cannot be ");
            stringBuilder.append(d);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject2.put("requestId", zzes);
            jSONObject2.put("type", "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzp());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("level", d);
            jSONObject2.put("volume", jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzes, null);
        this.zzaab.zza(zzes, zzec);
        return zzes;
    }

    public final long zza(zzec zzec, boolean z, JSONObject jSONObject) throws IllegalStateException, zzea {
        JSONObject jSONObject2 = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject2.put("requestId", zzes);
            jSONObject2.put("type", "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzp());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("muted", z);
            jSONObject2.put("volume", jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzes, null);
        this.zzaac.zza(zzes, zzec);
        return zzes;
    }

    public final long zzb(zzec zzec, double d, JSONObject jSONObject) throws IllegalStateException, zzea {
        if (this.zzzt == null) {
            throw new zzea();
        }
        JSONObject jSONObject2 = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject2.put("requestId", zzes);
            jSONObject2.put("type", "SET_PLAYBACK_RATE");
            jSONObject2.put("playbackRate", d);
            jSONObject2.put("mediaSessionId", this.zzzt.zzp());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzes, null);
        this.zzaan.zza(zzes, zzec);
        return zzes;
    }

    public final long zzb(zzec zzec) throws IllegalStateException {
        JSONObject jSONObject = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject.put("requestId", zzes);
            jSONObject.put("type", "GET_STATUS");
            if (this.zzzt != null) {
                jSONObject.put("mediaSessionId", this.zzzt.zzp());
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), zzes, null);
        this.zzaad.zza(zzes, zzec);
        return zzes;
    }

    public final long zza(zzec zzec, long[] jArr) throws IllegalStateException, zzea {
        if (jArr == null) {
            throw new IllegalArgumentException("trackIds cannot be null");
        }
        JSONObject jSONObject = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject.put("requestId", zzes);
            jSONObject.put("type", "EDIT_TRACKS_INFO");
            jSONObject.put("mediaSessionId", zzp());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < jArr.length; i++) {
                jSONArray.put(i, jArr[i]);
            }
            jSONObject.put("activeTrackIds", jSONArray);
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), zzes, null);
        this.zzaae.zza(zzes, zzec);
        return zzes;
    }

    public final long zza(zzec zzec, TextTrackStyle textTrackStyle) throws IllegalStateException, zzea {
        if (textTrackStyle == null) {
            throw new IllegalArgumentException("trackStyle cannot be null");
        }
        JSONObject jSONObject = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject.put("requestId", zzes);
            jSONObject.put("type", "EDIT_TRACKS_INFO");
            if (textTrackStyle != null) {
                jSONObject.put("textTrackStyle", textTrackStyle.toJson());
            }
            jSONObject.put("mediaSessionId", zzp());
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), zzes, null);
        this.zzaaf.zza(zzes, zzec);
        return zzes;
    }

    public final long getApproximateStreamPosition() {
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo == null) {
            return 0;
        }
        if (this.zzzu != null) {
            return this.zzzu.longValue();
        }
        if (this.zzzs == 0) {
            return 0;
        }
        double playbackRate = this.zzzt.getPlaybackRate();
        long streamPosition = this.zzzt.getStreamPosition();
        int playerState = this.zzzt.getPlayerState();
        if (playbackRate == 0.0d || playerState != 2) {
            return streamPosition;
        }
        return zza(playbackRate, streamPosition, mediaInfo.getStreamDuration());
    }

    public final long getApproximateAdBreakClipPositionMs() {
        if (this.zzzs == 0 || this.zzzt == null) {
            return 0;
        }
        AdBreakStatus adBreakStatus = this.zzzt.getAdBreakStatus();
        if (adBreakStatus == null) {
            return 0;
        }
        AdBreakClipInfo currentAdBreakClip = this.zzzt.getCurrentAdBreakClip();
        if (currentAdBreakClip == null) {
            return 0;
        }
        double d = 0.0d;
        if (this.zzzt.getPlaybackRate() == 0.0d && this.zzzt.getPlayerState() == 2) {
            d = 1.0d;
        }
        return zza(d, adBreakStatus.getCurrentBreakClipTimeInMs(), currentAdBreakClip.getDurationInMs());
    }

    private final long zza(double d, long j, long j2) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.zzzs;
        if (elapsedRealtime < 0) {
            elapsedRealtime = 0;
        }
        if (elapsedRealtime == 0) {
            return j;
        }
        long j3 = j + ((long) (((double) elapsedRealtime) * d));
        if (j2 <= 0 || j3 <= j2) {
            j2 = j3 < 0 ? 0 : j3;
        }
        return j2;
    }

    public final long getStreamDuration() {
        MediaInfo mediaInfo = getMediaInfo();
        return mediaInfo != null ? mediaInfo.getStreamDuration() : 0;
    }

    public final MediaStatus getMediaStatus() {
        return this.zzzt;
    }

    public final MediaInfo getMediaInfo() {
        return this.zzzt == null ? null : this.zzzt.getMediaInfo();
    }

    public final long zza(zzec zzec, MediaQueueItem[] mediaQueueItemArr, zzam zzam, zzap zzap) throws IllegalStateException, IllegalArgumentException {
        if (mediaQueueItemArr == null || mediaQueueItemArr.length == 0) {
            throw new IllegalArgumentException("items must not be null or empty.");
        } else if (zzap.getStartIndex() < 0 || zzap.getStartIndex() >= mediaQueueItemArr.length) {
            int startIndex = zzap.getStartIndex();
            StringBuilder stringBuilder = new StringBuilder(31);
            stringBuilder.append("Invalid startIndex: ");
            stringBuilder.append(startIndex);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (zzap.getPlayPosition() == -1 || zzap.getPlayPosition() >= 0) {
            JSONObject jSONObject = new JSONObject();
            long zzes = zzes();
            try {
                jSONObject.put("requestId", zzes);
                jSONObject.put("type", "QUEUE_LOAD");
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < mediaQueueItemArr.length; i++) {
                    jSONArray.put(i, mediaQueueItemArr[i].toJson());
                }
                jSONObject.put("items", jSONArray);
                if (zzam != null) {
                    jSONObject.put("queueData", zzam.toJson());
                }
                String zza = zzef.zza(Integer.valueOf(zzap.getRepeatMode()));
                if (zza == null) {
                    int repeatMode = zzap.getRepeatMode();
                    StringBuilder stringBuilder2 = new StringBuilder(32);
                    stringBuilder2.append("Invalid repeat mode: ");
                    stringBuilder2.append(repeatMode);
                    throw new IllegalArgumentException(stringBuilder2.toString());
                }
                jSONObject.put("repeatMode", zza);
                jSONObject.put("startIndex", zzap.getStartIndex());
                if (zzap.getPlayPosition() != -1) {
                    jSONObject.put("currentTime", ((double) zzap.getPlayPosition()) / 1000.0d);
                }
                if (zzap.getCustomData() != null) {
                    jSONObject.put("customData", zzap.getCustomData());
                }
                zza(jSONObject.toString(), zzes, null);
                this.zzzw.zza(zzes, zzec);
                return zzes;
            } catch (JSONException unused) {
            }
        } else {
            long playPosition = zzap.getPlayPosition();
            StringBuilder stringBuilder3 = new StringBuilder(54);
            stringBuilder3.append("playPosition can not be negative: ");
            stringBuilder3.append(playPosition);
            throw new IllegalArgumentException(stringBuilder3.toString());
        }
    }

    public final long zza(zzec zzec, MediaQueueItem[] mediaQueueItemArr, int i, int i2, int i3, long j, JSONObject jSONObject) throws IllegalStateException, zzea, IllegalArgumentException {
        MediaQueueItem[] mediaQueueItemArr2 = mediaQueueItemArr;
        int i4 = i;
        int i5 = i3;
        long j2 = j;
        JSONObject jSONObject2 = jSONObject;
        if (mediaQueueItemArr2 == null || mediaQueueItemArr2.length == 0) {
            throw new IllegalArgumentException("itemsToInsert must not be null or empty.");
        }
        int i6 = 0;
        if (i5 != -1 && (i5 < 0 || i5 >= mediaQueueItemArr2.length)) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "currentItemIndexInItemsToInsert %d out of range [0, %d).", new Object[]{Integer.valueOf(i3), Integer.valueOf(mediaQueueItemArr2.length)}));
        } else if (j2 == -1 || j2 >= 0) {
            JSONObject jSONObject3 = new JSONObject();
            long zzes = zzes();
            try {
                jSONObject3.put("requestId", zzes);
                jSONObject3.put("type", "QUEUE_INSERT");
                jSONObject3.put("mediaSessionId", zzp());
                JSONArray jSONArray = new JSONArray();
                while (i6 < mediaQueueItemArr2.length) {
                    jSONArray.put(i6, mediaQueueItemArr2[i6].toJson());
                    i6++;
                }
                jSONObject3.put("items", jSONArray);
                if (i4 != 0) {
                    jSONObject3.put("insertBefore", i4);
                }
                if (i5 != -1) {
                    jSONObject3.put("currentItemIndex", i5);
                }
                if (j2 != -1) {
                    jSONObject3.put("currentTime", ((double) j2) / 1000.0d);
                }
                if (jSONObject2 != null) {
                    jSONObject3.put("customData", jSONObject2);
                }
            } catch (JSONException unused) {
            }
            zza(jSONObject3.toString(), zzes, null);
            this.zzaag.zza(zzes, zzec);
            return zzes;
        } else {
            StringBuilder stringBuilder = new StringBuilder(54);
            stringBuilder.append("playPosition can not be negative: ");
            stringBuilder.append(j2);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public final long zza(zzec zzec, int i, long j, MediaQueueItem[] mediaQueueItemArr, int i2, Integer num, JSONObject jSONObject) throws IllegalArgumentException, IllegalStateException, zzea {
        int i3 = i;
        long j2 = j;
        MediaQueueItem[] mediaQueueItemArr2 = mediaQueueItemArr;
        int i4 = i2;
        JSONObject jSONObject2 = jSONObject;
        if (j2 == -1 || j2 >= 0) {
            JSONObject jSONObject3 = new JSONObject();
            long zzes = zzes();
            try {
                jSONObject3.put("requestId", zzes);
                jSONObject3.put("type", "QUEUE_UPDATE");
                jSONObject3.put("mediaSessionId", zzp());
                if (i3 != 0) {
                    jSONObject3.put("currentItemId", i3);
                }
                if (i4 != 0) {
                    jSONObject3.put("jump", i4);
                }
                if (mediaQueueItemArr2 != null && mediaQueueItemArr2.length > 0) {
                    JSONArray jSONArray = new JSONArray();
                    for (i4 = 0; i4 < mediaQueueItemArr2.length; i4++) {
                        jSONArray.put(i4, mediaQueueItemArr2[i4].toJson());
                    }
                    jSONObject3.put("items", jSONArray);
                }
                String zza = zzef.zza(num);
                if (zza != null) {
                    jSONObject3.put("repeatMode", zza);
                }
                if (j2 != -1) {
                    jSONObject3.put("currentTime", ((double) j2) / 1000.0d);
                }
                if (jSONObject2 != null) {
                    jSONObject3.put("customData", jSONObject2);
                }
            } catch (JSONException unused) {
            }
            zza(jSONObject3.toString(), zzes, null);
            this.zzaah.zza(zzes, zzec);
            return zzes;
        }
        StringBuilder stringBuilder = new StringBuilder(53);
        stringBuilder.append("playPosition cannot be negative: ");
        stringBuilder.append(j2);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public final long zza(zzec zzec, int[] iArr, JSONObject jSONObject) throws IllegalStateException, zzea, IllegalArgumentException {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToRemove must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject2.put("requestId", zzes);
            jSONObject2.put("type", "QUEUE_REMOVE");
            jSONObject2.put("mediaSessionId", zzp());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < iArr.length; i++) {
                jSONArray.put(i, iArr[i]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzes, null);
        this.zzaai.zza(zzes, zzec);
        return zzes;
    }

    public final long zza(zzec zzec, int[] iArr, int i, JSONObject jSONObject) throws IllegalStateException, zzea, IllegalArgumentException {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToReorder must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject2.put("requestId", zzes);
            jSONObject2.put("type", "QUEUE_REORDER");
            jSONObject2.put("mediaSessionId", zzp());
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                jSONArray.put(i2, iArr[i2]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (i != 0) {
                jSONObject2.put("insertBefore", i);
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), zzes, null);
        this.zzaaj.zza(zzes, zzec);
        return zzes;
    }

    public final long zzc(zzec zzec) throws zzea, IllegalStateException {
        JSONObject jSONObject = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject.put("requestId", zzes);
            jSONObject.put("type", "QUEUE_GET_ITEM_IDS");
            jSONObject.put("mediaSessionId", zzp());
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), zzes, null);
        this.zzaak.zza(zzes, zzec);
        return zzes;
    }

    public final long zza(zzec zzec, int i, int i2, int i3) throws zzea, IllegalArgumentException {
        if ((i2 <= 0 || i3 != 0) && (i2 != 0 || i3 <= 0)) {
            throw new IllegalArgumentException("Exactly one of nextCount and prevCount must be positive and the other must be zero");
        }
        JSONObject jSONObject = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject.put("requestId", zzes);
            jSONObject.put("type", "QUEUE_GET_ITEM_RANGE");
            jSONObject.put("mediaSessionId", zzp());
            jSONObject.put(bm.b, i);
            if (i2 > 0) {
                jSONObject.put("nextCount", i2);
            }
            if (i3 > 0) {
                jSONObject.put("prevCount", i3);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), zzes, null);
        this.zzaam.zza(zzes, zzec);
        return zzes;
    }

    public final long zza(zzec zzec, int[] iArr) throws zzea, IllegalArgumentException {
        JSONObject jSONObject = new JSONObject();
        long zzes = zzes();
        try {
            jSONObject.put("requestId", zzes);
            jSONObject.put("type", "QUEUE_GET_ITEMS");
            jSONObject.put("mediaSessionId", zzp());
            JSONArray jSONArray = new JSONArray();
            for (int put : iArr) {
                jSONArray.put(put);
            }
            jSONObject.put("itemIds", jSONArray);
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), zzes, null);
        this.zzaal.zza(zzes, zzec);
        return zzes;
    }

    public final long zzb(String str, List<zzbx> list) throws IllegalStateException {
        long zzes = zzes();
        zza(zza(str, (List) list, zzes), zzes, null);
        return zzes;
    }

    private static String zza(String str, List<zzbx> list, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("requestId", j);
            jSONObject.put("type", "PRECACHE");
            if (str != null) {
                jSONObject.put("precacheData", str);
            }
            if (!(list == null || list.isEmpty())) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    jSONArray.put(i, ((zzbx) list.get(i)).toJson());
                }
                jSONObject.put("requestItems", jSONArray);
            }
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:113:0x0211 A:{Catch:{ JSONException -> 0x028f }} */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x021e A:{Catch:{ JSONException -> 0x028f }} */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x022b A:{Catch:{ JSONException -> 0x028f }} */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0235 A:{Catch:{ JSONException -> 0x028f }} */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x023c A:{Catch:{ JSONException -> 0x028f }} */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0243 A:{Catch:{ JSONException -> 0x028f }} */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x024a A:{Catch:{ JSONException -> 0x028f }} */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x025d A:{Catch:{ JSONException -> 0x028f }} */
    /* JADX WARNING: Missing block: B:62:0x0114, code skipped:
            r8 = -1;
     */
    /* JADX WARNING: Missing block: B:63:0x0115, code skipped:
            switch(r8) {
                case 0: goto L_0x012b;
                case 1: goto L_0x0125;
                case 2: goto L_0x011f;
                case 3: goto L_0x0119;
                default: goto L_0x0118;
            };
     */
    /* JADX WARNING: Missing block: B:65:0x0119, code skipped:
            r12.zzzv.zza(r5);
     */
    /* JADX WARNING: Missing block: B:66:0x011f, code skipped:
            r12.zzzv.zzc(r5);
     */
    /* JADX WARNING: Missing block: B:67:0x0124, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:68:0x0125, code skipped:
            r12.zzzv.zzb(r5);
     */
    /* JADX WARNING: Missing block: B:69:0x012a, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:70:0x012b, code skipped:
            r12.zzzv.zza(r5, r1);
     */
    /* JADX WARNING: Missing block: B:71:0x0130, code skipped:
            return;
     */
    public final void zzo(java.lang.String r13) {
        /*
        r12 = this;
        r0 = r12.zzxw;
        r1 = "message received: %s";
        r2 = 1;
        r3 = new java.lang.Object[r2];
        r4 = 0;
        r3[r4] = r13;
        r0.d(r1, r3);
        r0 = 2;
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x028f }
        r1.<init>(r13);	 Catch:{ JSONException -> 0x028f }
        r3 = "type";
        r3 = r1.getString(r3);	 Catch:{ JSONException -> 0x028f }
        r5 = "requestId";
        r6 = -1;
        r5 = r1.optLong(r5, r6);	 Catch:{ JSONException -> 0x028f }
        r7 = r3.hashCode();	 Catch:{ JSONException -> 0x028f }
        r8 = 3;
        r9 = -1;
        r10 = 4;
        switch(r7) {
            case -1830647528: goto L_0x0072;
            case -1790231854: goto L_0x0068;
            case -1125000185: goto L_0x005e;
            case -262628938: goto L_0x0054;
            case 154411710: goto L_0x004a;
            case 431600379: goto L_0x0040;
            case 823510221: goto L_0x0036;
            case 2107149050: goto L_0x002c;
            default: goto L_0x002b;
        };	 Catch:{ JSONException -> 0x028f }
    L_0x002b:
        goto L_0x007c;
    L_0x002c:
        r7 = "QUEUE_ITEM_IDS";
        r3 = r3.equals(r7);	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x007c;
    L_0x0034:
        r3 = 5;
        goto L_0x007d;
    L_0x0036:
        r7 = "MEDIA_STATUS";
        r3 = r3.equals(r7);	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x007c;
    L_0x003e:
        r3 = r4;
        goto L_0x007d;
    L_0x0040:
        r7 = "INVALID_PLAYER_STATE";
        r3 = r3.equals(r7);	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x007c;
    L_0x0048:
        r3 = r2;
        goto L_0x007d;
    L_0x004a:
        r7 = "QUEUE_CHANGE";
        r3 = r3.equals(r7);	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x007c;
    L_0x0052:
        r3 = 6;
        goto L_0x007d;
    L_0x0054:
        r7 = "LOAD_FAILED";
        r3 = r3.equals(r7);	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x007c;
    L_0x005c:
        r3 = r0;
        goto L_0x007d;
    L_0x005e:
        r7 = "INVALID_REQUEST";
        r3 = r3.equals(r7);	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x007c;
    L_0x0066:
        r3 = r10;
        goto L_0x007d;
    L_0x0068:
        r7 = "QUEUE_ITEMS";
        r3 = r3.equals(r7);	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x007c;
    L_0x0070:
        r3 = 7;
        goto L_0x007d;
    L_0x0072:
        r7 = "LOAD_CANCELLED";
        r3 = r3.equals(r7);	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x007c;
    L_0x007a:
        r3 = r8;
        goto L_0x007d;
    L_0x007c:
        r3 = r9;
    L_0x007d:
        r7 = 2100; // 0x834 float:2.943E-42 double:1.0375E-320;
        r11 = 0;
        switch(r3) {
            case 0: goto L_0x01b7;
            case 1: goto L_0x018f;
            case 2: goto L_0x0183;
            case 3: goto L_0x0175;
            case 4: goto L_0x014d;
            case 5: goto L_0x0132;
            case 6: goto L_0x00ba;
            case 7: goto L_0x0085;
            default: goto L_0x0083;
        };	 Catch:{ JSONException -> 0x028f }
    L_0x0083:
        goto L_0x028e;
    L_0x0085:
        r3 = r12.zzaal;	 Catch:{ JSONException -> 0x028f }
        r3.zzc(r5, r4, r11);	 Catch:{ JSONException -> 0x028f }
        r3 = r12.zzzv;	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x028e;
    L_0x008e:
        r3 = "items";
        r1 = r1.getJSONArray(r3);	 Catch:{ JSONException -> 0x028f }
        r3 = r1.length();	 Catch:{ JSONException -> 0x028f }
        r3 = new com.google.android.gms.cast.MediaQueueItem[r3];	 Catch:{ JSONException -> 0x028f }
        r5 = r4;
    L_0x009b:
        r6 = r1.length();	 Catch:{ JSONException -> 0x028f }
        if (r5 >= r6) goto L_0x00b3;
    L_0x00a1:
        r6 = new com.google.android.gms.cast.MediaQueueItem$Builder;	 Catch:{ JSONException -> 0x028f }
        r7 = r1.getJSONObject(r5);	 Catch:{ JSONException -> 0x028f }
        r6.<init>(r7);	 Catch:{ JSONException -> 0x028f }
        r6 = r6.build();	 Catch:{ JSONException -> 0x028f }
        r3[r5] = r6;	 Catch:{ JSONException -> 0x028f }
        r5 = r5 + 1;
        goto L_0x009b;
    L_0x00b3:
        r1 = r12.zzzv;	 Catch:{ JSONException -> 0x028f }
        r1.zzb(r3);	 Catch:{ JSONException -> 0x028f }
        goto L_0x028e;
    L_0x00ba:
        r3 = r12.zzaam;	 Catch:{ JSONException -> 0x028f }
        r3.zzc(r5, r4, r11);	 Catch:{ JSONException -> 0x028f }
        r3 = r12.zzzv;	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x0131;
    L_0x00c3:
        r3 = "changeType";
        r3 = r1.getString(r3);	 Catch:{ JSONException -> 0x028f }
        r5 = "itemIds";
        r5 = r1.getJSONArray(r5);	 Catch:{ JSONException -> 0x028f }
        r5 = zzb(r5);	 Catch:{ JSONException -> 0x028f }
        r6 = "insertBefore";
        r1 = r1.optInt(r6, r4);	 Catch:{ JSONException -> 0x028f }
        if (r5 == 0) goto L_0x0131;
    L_0x00db:
        r6 = r3.hashCode();	 Catch:{ JSONException -> 0x028f }
        switch(r6) {
            case -2130463047: goto L_0x010a;
            case -1881281404: goto L_0x0100;
            case -1785516855: goto L_0x00f7;
            case 1122976047: goto L_0x00ed;
            case 1395699694: goto L_0x00e3;
            default: goto L_0x00e2;
        };	 Catch:{ JSONException -> 0x028f }
    L_0x00e2:
        goto L_0x0114;
    L_0x00e3:
        r6 = "NO_CHANGE";
        r3 = r3.equals(r6);	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x0114;
    L_0x00eb:
        r8 = r10;
        goto L_0x0115;
    L_0x00ed:
        r6 = "ITEMS_CHANGE";
        r3 = r3.equals(r6);	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x0114;
    L_0x00f5:
        r8 = r2;
        goto L_0x0115;
    L_0x00f7:
        r6 = "UPDATE";
        r3 = r3.equals(r6);	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x0114;
    L_0x00ff:
        goto L_0x0115;
    L_0x0100:
        r6 = "REMOVE";
        r3 = r3.equals(r6);	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x0114;
    L_0x0108:
        r8 = r0;
        goto L_0x0115;
    L_0x010a:
        r6 = "INSERT";
        r3 = r3.equals(r6);	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x0114;
    L_0x0112:
        r8 = r4;
        goto L_0x0115;
    L_0x0114:
        r8 = r9;
    L_0x0115:
        switch(r8) {
            case 0: goto L_0x012b;
            case 1: goto L_0x0125;
            case 2: goto L_0x011f;
            case 3: goto L_0x0119;
            default: goto L_0x0118;
        };	 Catch:{ JSONException -> 0x028f }
    L_0x0118:
        goto L_0x0131;
    L_0x0119:
        r1 = r12.zzzv;	 Catch:{ JSONException -> 0x028f }
        r1.zza(r5);	 Catch:{ JSONException -> 0x028f }
        goto L_0x0131;
    L_0x011f:
        r1 = r12.zzzv;	 Catch:{ JSONException -> 0x028f }
        r1.zzc(r5);	 Catch:{ JSONException -> 0x028f }
        return;
    L_0x0125:
        r1 = r12.zzzv;	 Catch:{ JSONException -> 0x028f }
        r1.zzb(r5);	 Catch:{ JSONException -> 0x028f }
        return;
    L_0x012b:
        r3 = r12.zzzv;	 Catch:{ JSONException -> 0x028f }
        r3.zza(r5, r1);	 Catch:{ JSONException -> 0x028f }
        return;
    L_0x0131:
        return;
    L_0x0132:
        r3 = r12.zzaak;	 Catch:{ JSONException -> 0x028f }
        r3.zzc(r5, r4, r11);	 Catch:{ JSONException -> 0x028f }
        r3 = r12.zzzv;	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x014c;
    L_0x013b:
        r3 = "itemIds";
        r1 = r1.getJSONArray(r3);	 Catch:{ JSONException -> 0x028f }
        r1 = zzb(r1);	 Catch:{ JSONException -> 0x028f }
        if (r1 == 0) goto L_0x014c;
    L_0x0147:
        r3 = r12.zzzv;	 Catch:{ JSONException -> 0x028f }
        r3.zza(r1);	 Catch:{ JSONException -> 0x028f }
    L_0x014c:
        return;
    L_0x014d:
        r3 = r12.zzxw;	 Catch:{ JSONException -> 0x028f }
        r8 = "received unexpected error: Invalid Request.";
        r9 = new java.lang.Object[r4];	 Catch:{ JSONException -> 0x028f }
        r3.w(r8, r9);	 Catch:{ JSONException -> 0x028f }
        r3 = "customData";
        r1 = r1.optJSONObject(r3);	 Catch:{ JSONException -> 0x028f }
        r3 = r12.zzer();	 Catch:{ JSONException -> 0x028f }
        r3 = r3.iterator();	 Catch:{ JSONException -> 0x028f }
    L_0x0164:
        r8 = r3.hasNext();	 Catch:{ JSONException -> 0x028f }
        if (r8 == 0) goto L_0x0174;
    L_0x016a:
        r8 = r3.next();	 Catch:{ JSONException -> 0x028f }
        r8 = (com.google.android.gms.internal.cast.zzed) r8;	 Catch:{ JSONException -> 0x028f }
        r8.zzc(r5, r7, r1);	 Catch:{ JSONException -> 0x028f }
        goto L_0x0164;
    L_0x0174:
        return;
    L_0x0175:
        r3 = "customData";
        r1 = r1.optJSONObject(r3);	 Catch:{ JSONException -> 0x028f }
        r3 = r12.zzzw;	 Catch:{ JSONException -> 0x028f }
        r7 = 2101; // 0x835 float:2.944E-42 double:1.038E-320;
        r3.zzc(r5, r7, r1);	 Catch:{ JSONException -> 0x028f }
        return;
    L_0x0183:
        r3 = "customData";
        r1 = r1.optJSONObject(r3);	 Catch:{ JSONException -> 0x028f }
        r3 = r12.zzzw;	 Catch:{ JSONException -> 0x028f }
        r3.zzc(r5, r7, r1);	 Catch:{ JSONException -> 0x028f }
        return;
    L_0x018f:
        r3 = r12.zzxw;	 Catch:{ JSONException -> 0x028f }
        r8 = "received unexpected error: Invalid Player State.";
        r9 = new java.lang.Object[r4];	 Catch:{ JSONException -> 0x028f }
        r3.w(r8, r9);	 Catch:{ JSONException -> 0x028f }
        r3 = "customData";
        r1 = r1.optJSONObject(r3);	 Catch:{ JSONException -> 0x028f }
        r3 = r12.zzer();	 Catch:{ JSONException -> 0x028f }
        r3 = r3.iterator();	 Catch:{ JSONException -> 0x028f }
    L_0x01a6:
        r8 = r3.hasNext();	 Catch:{ JSONException -> 0x028f }
        if (r8 == 0) goto L_0x01b6;
    L_0x01ac:
        r8 = r3.next();	 Catch:{ JSONException -> 0x028f }
        r8 = (com.google.android.gms.internal.cast.zzed) r8;	 Catch:{ JSONException -> 0x028f }
        r8.zzc(r5, r7, r1);	 Catch:{ JSONException -> 0x028f }
        goto L_0x01a6;
    L_0x01b6:
        return;
    L_0x01b7:
        r3 = "status";
        r1 = r1.getJSONArray(r3);	 Catch:{ JSONException -> 0x028f }
        r3 = r1.length();	 Catch:{ JSONException -> 0x028f }
        if (r3 <= 0) goto L_0x0267;
    L_0x01c3:
        r1 = r1.getJSONObject(r4);	 Catch:{ JSONException -> 0x028f }
        r3 = r12.zzzw;	 Catch:{ JSONException -> 0x028f }
        r3 = r3.test(r5);	 Catch:{ JSONException -> 0x028f }
        r7 = r12.zzaab;	 Catch:{ JSONException -> 0x028f }
        r7 = r7.zzfd();	 Catch:{ JSONException -> 0x028f }
        if (r7 == 0) goto L_0x01dd;
    L_0x01d5:
        r7 = r12.zzaab;	 Catch:{ JSONException -> 0x028f }
        r7 = r7.test(r5);	 Catch:{ JSONException -> 0x028f }
        if (r7 == 0) goto L_0x01ed;
    L_0x01dd:
        r7 = r12.zzaac;	 Catch:{ JSONException -> 0x028f }
        r7 = r7.zzfd();	 Catch:{ JSONException -> 0x028f }
        if (r7 == 0) goto L_0x01ef;
    L_0x01e5:
        r7 = r12.zzaac;	 Catch:{ JSONException -> 0x028f }
        r7 = r7.test(r5);	 Catch:{ JSONException -> 0x028f }
        if (r7 != 0) goto L_0x01ef;
    L_0x01ed:
        r7 = r2;
        goto L_0x01f0;
    L_0x01ef:
        r7 = r4;
    L_0x01f0:
        if (r3 != 0) goto L_0x01fe;
    L_0x01f2:
        r3 = r12.zzzt;	 Catch:{ JSONException -> 0x028f }
        if (r3 != 0) goto L_0x01f7;
    L_0x01f6:
        goto L_0x01fe;
    L_0x01f7:
        r3 = r12.zzzt;	 Catch:{ JSONException -> 0x028f }
        r1 = r3.zza(r1, r7);	 Catch:{ JSONException -> 0x028f }
        goto L_0x020d;
    L_0x01fe:
        r3 = new com.google.android.gms.cast.MediaStatus;	 Catch:{ JSONException -> 0x028f }
        r3.<init>(r1);	 Catch:{ JSONException -> 0x028f }
        r12.zzzt = r3;	 Catch:{ JSONException -> 0x028f }
        r7 = android.os.SystemClock.elapsedRealtime();	 Catch:{ JSONException -> 0x028f }
        r12.zzzs = r7;	 Catch:{ JSONException -> 0x028f }
        r1 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
    L_0x020d:
        r3 = r1 & 1;
        if (r3 == 0) goto L_0x021a;
    L_0x0211:
        r7 = android.os.SystemClock.elapsedRealtime();	 Catch:{ JSONException -> 0x028f }
        r12.zzzs = r7;	 Catch:{ JSONException -> 0x028f }
        r12.onStatusUpdated();	 Catch:{ JSONException -> 0x028f }
    L_0x021a:
        r3 = r1 & 2;
        if (r3 == 0) goto L_0x0227;
    L_0x021e:
        r7 = android.os.SystemClock.elapsedRealtime();	 Catch:{ JSONException -> 0x028f }
        r12.zzzs = r7;	 Catch:{ JSONException -> 0x028f }
        r12.onStatusUpdated();	 Catch:{ JSONException -> 0x028f }
    L_0x0227:
        r3 = r1 & 128;
        if (r3 == 0) goto L_0x0231;
    L_0x022b:
        r7 = android.os.SystemClock.elapsedRealtime();	 Catch:{ JSONException -> 0x028f }
        r12.zzzs = r7;	 Catch:{ JSONException -> 0x028f }
    L_0x0231:
        r3 = r1 & 4;
        if (r3 == 0) goto L_0x0238;
    L_0x0235:
        r12.onMetadataUpdated();	 Catch:{ JSONException -> 0x028f }
    L_0x0238:
        r3 = r1 & 8;
        if (r3 == 0) goto L_0x023f;
    L_0x023c:
        r12.onQueueStatusUpdated();	 Catch:{ JSONException -> 0x028f }
    L_0x023f:
        r3 = r1 & 16;
        if (r3 == 0) goto L_0x0246;
    L_0x0243:
        r12.onPreloadStatusUpdated();	 Catch:{ JSONException -> 0x028f }
    L_0x0246:
        r3 = r1 & 32;
        if (r3 == 0) goto L_0x0259;
    L_0x024a:
        r7 = android.os.SystemClock.elapsedRealtime();	 Catch:{ JSONException -> 0x028f }
        r12.zzzs = r7;	 Catch:{ JSONException -> 0x028f }
        r3 = r12.zzzv;	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x0259;
    L_0x0254:
        r3 = r12.zzzv;	 Catch:{ JSONException -> 0x028f }
        r3.onAdBreakStatusUpdated();	 Catch:{ JSONException -> 0x028f }
    L_0x0259:
        r1 = r1 & 64;
        if (r1 == 0) goto L_0x0275;
    L_0x025d:
        r7 = android.os.SystemClock.elapsedRealtime();	 Catch:{ JSONException -> 0x028f }
        r12.zzzs = r7;	 Catch:{ JSONException -> 0x028f }
        r12.onStatusUpdated();	 Catch:{ JSONException -> 0x028f }
        goto L_0x0275;
    L_0x0267:
        r12.zzzt = r11;	 Catch:{ JSONException -> 0x028f }
        r12.onStatusUpdated();	 Catch:{ JSONException -> 0x028f }
        r12.onMetadataUpdated();	 Catch:{ JSONException -> 0x028f }
        r12.onQueueStatusUpdated();	 Catch:{ JSONException -> 0x028f }
        r12.onPreloadStatusUpdated();	 Catch:{ JSONException -> 0x028f }
    L_0x0275:
        r1 = r12.zzer();	 Catch:{ JSONException -> 0x028f }
        r1 = r1.iterator();	 Catch:{ JSONException -> 0x028f }
    L_0x027d:
        r3 = r1.hasNext();	 Catch:{ JSONException -> 0x028f }
        if (r3 == 0) goto L_0x028d;
    L_0x0283:
        r3 = r1.next();	 Catch:{ JSONException -> 0x028f }
        r3 = (com.google.android.gms.internal.cast.zzed) r3;	 Catch:{ JSONException -> 0x028f }
        r3.zzc(r5, r4, r11);	 Catch:{ JSONException -> 0x028f }
        goto L_0x027d;
    L_0x028d:
        return;
    L_0x028e:
        return;
    L_0x028f:
        r1 = move-exception;
        r3 = r12.zzxw;
        r5 = "Message is malformed (%s); ignoring: %s";
        r0 = new java.lang.Object[r0];
        r1 = r1.getMessage();
        r0[r4] = r1;
        r0[r2] = r13;
        r3.w(r5, r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzdx.zzo(java.lang.String):void");
    }

    private static int[] zzb(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        int[] iArr = new int[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            iArr[i] = jSONArray.getInt(i);
        }
        return iArr;
    }

    private final long zzp() throws zzea {
        if (this.zzzt != null) {
            return this.zzzt.zzp();
        }
        throw new zzea();
    }

    private final void onStatusUpdated() {
        if (this.zzzv != null) {
            this.zzzv.onStatusUpdated();
        }
    }

    private final void onMetadataUpdated() {
        if (this.zzzv != null) {
            this.zzzv.onMetadataUpdated();
        }
    }

    private final void onQueueStatusUpdated() {
        if (this.zzzv != null) {
            this.zzzv.onQueueStatusUpdated();
        }
    }

    private final void onPreloadStatusUpdated() {
        if (this.zzzv != null) {
            this.zzzv.onPreloadStatusUpdated();
        }
    }

    private final void zzfc() {
        this.zzzs = 0;
        this.zzzt = null;
        for (zzed zzv : zzer()) {
            zzv.zzv(2002);
        }
    }

    public final void zzeq() {
        super.zzeq();
        zzfc();
    }

    public final void zza(long j, int i) {
        for (zzed zzc : zzer()) {
            zzc.zzc(j, i, null);
        }
    }
}
