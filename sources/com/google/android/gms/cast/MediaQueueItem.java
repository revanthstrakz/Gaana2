package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzdk;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "MediaQueueItemCreator")
@Reserved({1})
public class MediaQueueItem extends AbstractSafeParcelable {
    public static final Creator<MediaQueueItem> CREATOR = new zzao();
    public static final double DEFAULT_PLAYBACK_DURATION = Double.POSITIVE_INFINITY;
    public static final int INVALID_ITEM_ID = 0;
    @Field(getter = "getAutoplay", id = 4)
    private boolean zzdp;
    @Field(getter = "getActiveTrackIds", id = 8)
    private long[] zzds;
    @Field(getter = "getMedia", id = 2)
    private MediaInfo zzed;
    @Field(getter = "getItemId", id = 3)
    private int zzee;
    @Field(getter = "getStartTime", id = 5)
    private double zzef;
    @Field(getter = "getPlaybackDuration", id = 6)
    private double zzeg;
    @Field(getter = "getPreloadTime", id = 7)
    private double zzeh;
    @Field(id = 9)
    private String zzj;
    private JSONObject zzp;

    @VisibleForTesting
    public static class Builder {
        private final MediaQueueItem zzei;

        public Builder(MediaInfo mediaInfo) throws IllegalArgumentException {
            this.zzei = new MediaQueueItem(mediaInfo, null);
        }

        public Builder(JSONObject jSONObject) throws JSONException {
            this.zzei = new MediaQueueItem(jSONObject);
        }

        public Builder(MediaQueueItem mediaQueueItem) throws IllegalArgumentException {
            this.zzei = new MediaQueueItem(mediaQueueItem, null);
        }

        public Builder clearItemId() {
            this.zzei.zza(0);
            return this;
        }

        public Builder setAutoplay(boolean z) {
            this.zzei.zze(z);
            return this;
        }

        public Builder setStartTime(double d) throws IllegalArgumentException {
            this.zzei.zza(d);
            return this;
        }

        public Builder setPlaybackDuration(double d) {
            this.zzei.zzb(d);
            return this;
        }

        public Builder setPreloadTime(double d) throws IllegalArgumentException {
            this.zzei.zzc(d);
            return this;
        }

        public Builder setActiveTrackIds(long[] jArr) {
            this.zzei.zza(jArr);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzei.setCustomData(jSONObject);
            return this;
        }

        public MediaQueueItem build() {
            this.zzei.zzk();
            return this.zzei;
        }
    }

    @Constructor
    MediaQueueItem(@Param(id = 2) MediaInfo mediaInfo, @Param(id = 3) int i, @Param(id = 4) boolean z, @Param(id = 5) double d, @Param(id = 6) double d2, @Param(id = 7) double d3, @Param(id = 8) long[] jArr, @Param(id = 9) String str) {
        this.zzed = mediaInfo;
        this.zzee = i;
        this.zzdp = z;
        this.zzef = d;
        this.zzeg = d2;
        this.zzeh = d3;
        this.zzds = jArr;
        this.zzj = str;
        if (this.zzj != null) {
            try {
                this.zzp = new JSONObject(this.zzj);
                return;
            } catch (JSONException unused) {
                this.zzp = null;
                this.zzj = null;
                return;
            }
        }
        this.zzp = null;
    }

    private MediaQueueItem(MediaInfo mediaInfo) throws IllegalArgumentException {
        this(mediaInfo, 0, true, 0.0d, Double.POSITIVE_INFINITY, 0.0d, null, null);
        if (mediaInfo == null) {
            throw new IllegalArgumentException("media cannot be null.");
        }
    }

    MediaQueueItem(JSONObject jSONObject) throws JSONException {
        this(null, 0, true, 0.0d, Double.POSITIVE_INFINITY, 0.0d, null, null);
        zzf(jSONObject);
    }

    private MediaQueueItem(MediaQueueItem mediaQueueItem) throws IllegalArgumentException {
        this(mediaQueueItem.getMedia(), mediaQueueItem.getItemId(), mediaQueueItem.getAutoplay(), mediaQueueItem.getStartTime(), mediaQueueItem.getPlaybackDuration(), mediaQueueItem.getPreloadTime(), mediaQueueItem.getActiveTrackIds(), null);
        if (this.zzed == null) {
            throw new IllegalArgumentException("media cannot be null.");
        }
        this.zzp = mediaQueueItem.getCustomData();
    }

    public final boolean zzf(JSONObject jSONObject) throws JSONException {
        boolean z;
        int i;
        double d;
        long[] jArr;
        Object obj = null;
        if (jSONObject.has(ShareConstants.WEB_DIALOG_PARAM_MEDIA)) {
            this.zzed = new MediaInfo(jSONObject.getJSONObject(ShareConstants.WEB_DIALOG_PARAM_MEDIA));
            z = true;
        } else {
            z = false;
        }
        if (jSONObject.has(bm.b)) {
            i = jSONObject.getInt(bm.b);
            if (this.zzee != i) {
                this.zzee = i;
                z = true;
            }
        }
        if (jSONObject.has("autoplay")) {
            boolean z2 = jSONObject.getBoolean("autoplay");
            if (this.zzdp != z2) {
                this.zzdp = z2;
                z = true;
            }
        }
        if (jSONObject.has("startTime")) {
            d = jSONObject.getDouble("startTime");
            if (Math.abs(d - this.zzef) > 1.0E-7d) {
                this.zzef = d;
                z = true;
            }
        }
        if (jSONObject.has("playbackDuration")) {
            d = jSONObject.getDouble("playbackDuration");
            if (Math.abs(d - this.zzeg) > 1.0E-7d) {
                this.zzeg = d;
                z = true;
            }
        }
        if (jSONObject.has("preloadTime")) {
            d = jSONObject.getDouble("preloadTime");
            if (Math.abs(d - this.zzeh) > 1.0E-7d) {
                this.zzeh = d;
                z = true;
            }
        }
        if (jSONObject.has("activeTrackIds")) {
            JSONArray jSONArray = jSONObject.getJSONArray("activeTrackIds");
            int length = jSONArray.length();
            jArr = new long[length];
            for (int i2 = 0; i2 < length; i2++) {
                jArr[i2] = jSONArray.getLong(i2);
            }
            if (this.zzds != null && this.zzds.length == length) {
                i = 0;
                while (i < length) {
                    if (this.zzds[i] == jArr[i]) {
                        i++;
                    }
                }
            }
            obj = 1;
            break;
        }
        jArr = null;
        if (obj != null) {
            this.zzds = jArr;
            z = true;
        }
        if (!jSONObject.has("customData")) {
            return z;
        }
        this.zzp = jSONObject.getJSONObject("customData");
        return true;
    }

    public MediaInfo getMedia() {
        return this.zzed;
    }

    public int getItemId() {
        return this.zzee;
    }

    /* Access modifiers changed, original: final */
    public final void zza(int i) {
        this.zzee = 0;
    }

    public boolean getAutoplay() {
        return this.zzdp;
    }

    /* Access modifiers changed, original: final */
    public final void zze(boolean z) {
        this.zzdp = z;
    }

    public double getStartTime() {
        return this.zzef;
    }

    /* Access modifiers changed, original: final */
    public final void zza(double d) throws IllegalArgumentException {
        if (Double.isNaN(d) || d < 0.0d) {
            throw new IllegalArgumentException("startTime cannot be negative or NaN.");
        }
        this.zzef = d;
    }

    public double getPlaybackDuration() {
        return this.zzeg;
    }

    /* Access modifiers changed, original: final */
    public final void zzb(double d) throws IllegalArgumentException {
        if (Double.isNaN(d)) {
            throw new IllegalArgumentException("playbackDuration cannot be NaN.");
        }
        this.zzeg = d;
    }

    public double getPreloadTime() {
        return this.zzeh;
    }

    /* Access modifiers changed, original: final */
    public final void zzc(double d) throws IllegalArgumentException {
        if (Double.isNaN(d) || d < 0.0d) {
            throw new IllegalArgumentException("preloadTime cannot be negative or NaN.");
        }
        this.zzeh = d;
    }

    public long[] getActiveTrackIds() {
        return this.zzds;
    }

    /* Access modifiers changed, original: final */
    public final void zza(long[] jArr) {
        this.zzds = jArr;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    /* Access modifiers changed, original: final */
    public final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    /* Access modifiers changed, original: final */
    public final void zzk() throws IllegalArgumentException {
        if (this.zzed == null) {
            throw new IllegalArgumentException("media cannot be null.");
        } else if (Double.isNaN(this.zzef) || this.zzef < 0.0d) {
            throw new IllegalArgumentException("startTime cannot be negative or NaN.");
        } else if (Double.isNaN(this.zzeg)) {
            throw new IllegalArgumentException("playbackDuration cannot be NaN.");
        } else if (Double.isNaN(this.zzeh) || this.zzeh < 0.0d) {
            throw new IllegalArgumentException("preloadTime cannot be negative or Nan.");
        }
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ShareConstants.WEB_DIALOG_PARAM_MEDIA, this.zzed.toJson());
            if (this.zzee != 0) {
                jSONObject.put(bm.b, this.zzee);
            }
            jSONObject.put("autoplay", this.zzdp);
            jSONObject.put("startTime", this.zzef);
            if (this.zzeg != Double.POSITIVE_INFINITY) {
                jSONObject.put("playbackDuration", this.zzeg);
            }
            jSONObject.put("preloadTime", this.zzeh);
            if (this.zzds != null) {
                JSONArray jSONArray = new JSONArray();
                for (long put : this.zzds) {
                    jSONArray.put(put);
                }
                jSONObject.put("activeTrackIds", jSONArray);
            }
            if (this.zzp != null) {
                jSONObject.put("customData", this.zzp);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaQueueItem)) {
            return false;
        }
        MediaQueueItem mediaQueueItem = (MediaQueueItem) obj;
        if ((this.zzp == null) != (mediaQueueItem.zzp == null)) {
            return false;
        }
        return (this.zzp == null || mediaQueueItem.zzp == null || JsonUtils.areJsonValuesEquivalent(this.zzp, mediaQueueItem.zzp)) && zzdk.zza(this.zzed, mediaQueueItem.zzed) && this.zzee == mediaQueueItem.zzee && this.zzdp == mediaQueueItem.zzdp && this.zzef == mediaQueueItem.zzef && this.zzeg == mediaQueueItem.zzeg && this.zzeh == mediaQueueItem.zzeh && Arrays.equals(this.zzds, mediaQueueItem.zzds);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzed, Integer.valueOf(this.zzee), Boolean.valueOf(this.zzdp), Double.valueOf(this.zzef), Double.valueOf(this.zzeg), Double.valueOf(this.zzeh), Integer.valueOf(Arrays.hashCode(this.zzds)), String.valueOf(this.zzp));
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.zzj = this.zzp == null ? null : this.zzp.toString();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getMedia(), i, false);
        SafeParcelWriter.writeInt(parcel, 3, getItemId());
        SafeParcelWriter.writeBoolean(parcel, 4, getAutoplay());
        SafeParcelWriter.writeDouble(parcel, 5, getStartTime());
        SafeParcelWriter.writeDouble(parcel, 6, getPlaybackDuration());
        SafeParcelWriter.writeDouble(parcel, 7, getPreloadTime());
        SafeParcelWriter.writeLongArray(parcel, 8, getActiveTrackIds(), false);
        SafeParcelWriter.writeString(parcel, 9, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
