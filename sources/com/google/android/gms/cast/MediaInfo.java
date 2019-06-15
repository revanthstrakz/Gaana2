package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "MediaInfoCreator")
@Reserved({1})
public class MediaInfo extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<MediaInfo> CREATOR = new zzaj();
    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_INVALID = -1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE = 0;
    public static final long UNKNOWN_DURATION = -1;
    @Field(getter = "getStreamType", id = 3)
    private int streamType;
    @Field(getter = "getContentType", id = 4)
    private String zzde;
    @Field(getter = "getMetadata", id = 5)
    private MediaMetadata zzdf;
    @Field(getter = "getStreamDuration", id = 6)
    private long zzdg;
    @Field(getter = "getMediaTracks", id = 7)
    private List<MediaTrack> zzdh;
    @Field(getter = "getTextTrackStyle", id = 8)
    private TextTrackStyle zzdi;
    @Field(getter = "getAdBreaks", id = 10)
    private List<AdBreakInfo> zzdj;
    @Field(getter = "getAdBreakClips", id = 11)
    private List<AdBreakClipInfo> zzdk;
    @Field(getter = "getEntity", id = 12)
    private String zzdl;
    @Field(getter = "getVmapAdsRequest", id = 13)
    private VastAdsRequest zzdm;
    @Field(getter = "getStartAbsoluteTime", id = 14)
    private long zzdn;
    @Field(id = 9)
    private String zzj;
    @Field(getter = "getContentId", id = 2)
    private final String zzk;
    private JSONObject zzp;

    @VisibleForTesting
    public static class Builder {
        private final MediaInfo zzdo;

        public Builder(String str) throws IllegalArgumentException {
            this.zzdo = new MediaInfo(str);
        }

        public Builder(String str, String str2) throws IllegalArgumentException {
            this.zzdo = new MediaInfo(str, str2);
        }

        public Builder setStreamType(int i) throws IllegalArgumentException {
            this.zzdo.setStreamType(i);
            return this;
        }

        public Builder setContentType(String str) {
            this.zzdo.setContentType(str);
            return this;
        }

        public Builder setMetadata(MediaMetadata mediaMetadata) {
            this.zzdo.zza(mediaMetadata);
            return this;
        }

        public Builder setStreamDuration(long j) throws IllegalArgumentException {
            this.zzdo.zza(j);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzdo.setCustomData(jSONObject);
            return this;
        }

        public Builder setMediaTracks(List<MediaTrack> list) {
            this.zzdo.zza((List) list);
            return this;
        }

        public Builder setTextTrackStyle(TextTrackStyle textTrackStyle) {
            this.zzdo.setTextTrackStyle(textTrackStyle);
            return this;
        }

        public Builder setEntity(String str) {
            this.zzdo.zzd(str);
            return this;
        }

        public Builder setVmapAdsRequest(VastAdsRequest vastAdsRequest) {
            this.zzdo.zza(vastAdsRequest);
            return this;
        }

        public Builder setAdBreaks(List<AdBreakInfo> list) {
            this.zzdo.zzb(list);
            return this;
        }

        public Builder setAdBreakClips(List<AdBreakClipInfo> list) {
            this.zzdo.zzc(list);
            return this;
        }

        public MediaInfo build() {
            return this.zzdo;
        }
    }

    @Constructor
    MediaInfo(@Param(id = 2) @NonNull String str, @Param(id = 3) int i, @Param(id = 4) String str2, @Param(id = 5) MediaMetadata mediaMetadata, @Param(id = 6) long j, @Param(id = 7) List<MediaTrack> list, @Param(id = 8) TextTrackStyle textTrackStyle, @Param(id = 9) String str3, @Param(id = 10) List<AdBreakInfo> list2, @Param(id = 11) List<AdBreakClipInfo> list3, @Param(id = 12) String str4, @Param(id = 13) VastAdsRequest vastAdsRequest, @Param(id = 14) long j2) {
        this.zzk = str;
        this.streamType = i;
        this.zzde = str2;
        this.zzdf = mediaMetadata;
        this.zzdg = j;
        this.zzdh = list;
        this.zzdi = textTrackStyle;
        this.zzj = str3;
        if (this.zzj != null) {
            try {
                this.zzp = new JSONObject(this.zzj);
            } catch (JSONException unused) {
                this.zzp = null;
                this.zzj = null;
            }
        } else {
            this.zzp = null;
        }
        this.zzdj = list2;
        this.zzdk = list3;
        this.zzdl = str4;
        this.zzdm = vastAdsRequest;
        this.zzdn = j2;
    }

    MediaInfo(String str) throws IllegalArgumentException {
        this(str, -1, null, null, -1, null, null, null, null, null, null, null, -1);
        if (str == null) {
            throw new IllegalArgumentException("contentID cannot be null");
        }
    }

    MediaInfo(String str, String str2) throws IllegalArgumentException {
        this(str, -1, null, null, -1, null, null, null, null, null, str2, null, -1);
        if (str == null) {
            throw new IllegalArgumentException("contentID cannot be null");
        }
    }

    MediaInfo(JSONObject jSONObject) throws JSONException {
        MediaInfo mediaInfo;
        JSONObject jSONObject2;
        this(jSONObject.getString("contentId"), -1, null, null, -1, null, null, null, null, null, null, null, -1);
        JSONObject jSONObject3 = jSONObject;
        String string = jSONObject3.getString("streamType");
        int i = 0;
        if ("NONE".equals(string)) {
            mediaInfo = this;
            mediaInfo.streamType = 0;
        } else {
            mediaInfo = this;
            if ("BUFFERED".equals(string)) {
                mediaInfo.streamType = 1;
            } else if ("LIVE".equals(string)) {
                mediaInfo.streamType = 2;
            } else {
                mediaInfo.streamType = -1;
            }
        }
        mediaInfo.zzde = jSONObject3.optString("contentType", null);
        if (jSONObject3.has(TtmlNode.TAG_METADATA)) {
            jSONObject2 = jSONObject3.getJSONObject(TtmlNode.TAG_METADATA);
            mediaInfo.zzdf = new MediaMetadata(jSONObject2.getInt("metadataType"));
            mediaInfo.zzdf.zze(jSONObject2);
        }
        mediaInfo.zzdg = -1;
        if (jSONObject3.has("duration") && !jSONObject3.isNull("duration")) {
            double optDouble = jSONObject3.optDouble("duration", 0.0d);
            if (!(Double.isNaN(optDouble) || Double.isInfinite(optDouble))) {
                mediaInfo.zzdg = (long) (optDouble * 1000.0d);
            }
        }
        if (jSONObject3.has("tracks")) {
            mediaInfo.zzdh = new ArrayList();
            JSONArray jSONArray = jSONObject3.getJSONArray("tracks");
            while (i < jSONArray.length()) {
                mediaInfo.zzdh.add(new MediaTrack(jSONArray.getJSONObject(i)));
                i++;
            }
        } else {
            mediaInfo.zzdh = null;
        }
        if (jSONObject3.has("textTrackStyle")) {
            jSONObject2 = jSONObject3.getJSONObject("textTrackStyle");
            TextTrackStyle textTrackStyle = new TextTrackStyle();
            textTrackStyle.zze(jSONObject2);
            mediaInfo.zzdi = textTrackStyle;
        } else {
            mediaInfo.zzdi = null;
        }
        zzd(jSONObject);
        mediaInfo.zzp = jSONObject3.optJSONObject("customData");
        if (jSONObject3.has("entity")) {
            mediaInfo.zzdl = jSONObject3.getString("entity");
        }
        mediaInfo.zzdm = VastAdsRequest.fromJson(jSONObject3.optJSONObject("vmapAdsRequest"));
    }

    public String getContentId() {
        return this.zzk;
    }

    /* Access modifiers changed, original: final */
    public final void setStreamType(int i) throws IllegalArgumentException {
        if (i < -1 || i > 2) {
            throw new IllegalArgumentException("invalid stream type");
        }
        this.streamType = i;
    }

    public int getStreamType() {
        return this.streamType;
    }

    /* Access modifiers changed, original: final */
    public final void setContentType(String str) {
        this.zzde = str;
    }

    public String getContentType() {
        return this.zzde;
    }

    /* Access modifiers changed, original: final */
    public final void zza(MediaMetadata mediaMetadata) {
        this.zzdf = mediaMetadata;
    }

    public MediaMetadata getMetadata() {
        return this.zzdf;
    }

    /* Access modifiers changed, original: final */
    public final void zza(long j) throws IllegalArgumentException {
        if (j >= 0 || j == -1) {
            this.zzdg = j;
            return;
        }
        throw new IllegalArgumentException("Invalid stream duration");
    }

    public long getStreamDuration() {
        return this.zzdg;
    }

    /* Access modifiers changed, original: final */
    public final void zza(List<MediaTrack> list) {
        this.zzdh = list;
    }

    public List<MediaTrack> getMediaTracks() {
        return this.zzdh;
    }

    public void setTextTrackStyle(TextTrackStyle textTrackStyle) {
        this.zzdi = textTrackStyle;
    }

    public TextTrackStyle getTextTrackStyle() {
        return this.zzdi;
    }

    /* Access modifiers changed, original: final */
    public final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public List<AdBreakInfo> getAdBreaks() {
        return this.zzdj == null ? null : Collections.unmodifiableList(this.zzdj);
    }

    public List<AdBreakClipInfo> getAdBreakClips() {
        return this.zzdk == null ? null : Collections.unmodifiableList(this.zzdk);
    }

    @VisibleForTesting
    public final void zzb(List<AdBreakInfo> list) {
        this.zzdj = list;
    }

    /* Access modifiers changed, original: final */
    @VisibleForTesting
    public final void zzc(List<AdBreakClipInfo> list) {
        this.zzdk = list;
    }

    /* Access modifiers changed, original: final */
    public final void zzd(JSONObject jSONObject) throws JSONException {
        int i = 0;
        if (jSONObject.has("breaks")) {
            JSONArray jSONArray = jSONObject.getJSONArray("breaks");
            this.zzdj = new ArrayList(jSONArray.length());
            int i2 = 0;
            while (i2 < jSONArray.length()) {
                AdBreakInfo zzb = AdBreakInfo.zzb(jSONArray.getJSONObject(i2));
                if (zzb == null) {
                    this.zzdj.clear();
                    break;
                } else {
                    this.zzdj.add(zzb);
                    i2++;
                }
            }
        }
        if (jSONObject.has("breakClips")) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("breakClips");
            this.zzdk = new ArrayList(jSONArray2.length());
            while (i < jSONArray2.length()) {
                AdBreakClipInfo zza = AdBreakClipInfo.zza(jSONArray2.getJSONObject(i));
                if (zza != null) {
                    this.zzdk.add(zza);
                    i++;
                } else {
                    this.zzdk.clear();
                    return;
                }
            }
        }
    }

    public String getEntity() {
        return this.zzdl;
    }

    @VisibleForTesting
    public final void zzd(String str) {
        this.zzdl = str;
    }

    public VastAdsRequest getVmapAdsRequest() {
        return this.zzdm;
    }

    @VisibleForTesting
    public final void zza(VastAdsRequest vastAdsRequest) {
        this.zzdm = vastAdsRequest;
    }

    public final long zzj() {
        return this.zzdn;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            Object obj;
            JSONArray jSONArray;
            jSONObject.put("contentId", this.zzk);
            switch (this.streamType) {
                case 1:
                    obj = "BUFFERED";
                    break;
                case 2:
                    obj = "LIVE";
                    break;
                default:
                    obj = "NONE";
                    break;
            }
            jSONObject.put("streamType", obj);
            if (this.zzde != null) {
                jSONObject.put("contentType", this.zzde);
            }
            if (this.zzdf != null) {
                jSONObject.put(TtmlNode.TAG_METADATA, this.zzdf.toJson());
            }
            if (this.zzdg <= -1) {
                jSONObject.put("duration", JSONObject.NULL);
            } else {
                jSONObject.put("duration", ((double) this.zzdg) / 1000.0d);
            }
            if (this.zzdh != null) {
                jSONArray = new JSONArray();
                for (MediaTrack toJson : this.zzdh) {
                    jSONArray.put(toJson.toJson());
                }
                jSONObject.put("tracks", jSONArray);
            }
            if (this.zzdi != null) {
                jSONObject.put("textTrackStyle", this.zzdi.toJson());
            }
            if (this.zzp != null) {
                jSONObject.put("customData", this.zzp);
            }
            if (this.zzdl != null) {
                jSONObject.put("entity", this.zzdl);
            }
            if (this.zzdj != null) {
                jSONArray = new JSONArray();
                for (AdBreakInfo toJson2 : this.zzdj) {
                    jSONArray.put(toJson2.toJson());
                }
                jSONObject.put("breaks", jSONArray);
            }
            if (this.zzdk != null) {
                jSONArray = new JSONArray();
                for (AdBreakClipInfo toJson3 : this.zzdk) {
                    jSONArray.put(toJson3.toJson());
                }
                jSONObject.put("breakClips", jSONArray);
            }
            if (this.zzdm != null) {
                jSONObject.put("vmapAdsRequest", this.zzdm.toJson());
            }
            if (this.zzdn != -1) {
                jSONObject.put("startAbsoluteTime", ((double) this.zzdn) / 1000.0d);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaInfo)) {
            return false;
        }
        MediaInfo mediaInfo = (MediaInfo) obj;
        if ((this.zzp == null) != (mediaInfo.zzp == null)) {
            return false;
        }
        return (this.zzp == null || mediaInfo.zzp == null || JsonUtils.areJsonValuesEquivalent(this.zzp, mediaInfo.zzp)) && zzdk.zza(this.zzk, mediaInfo.zzk) && this.streamType == mediaInfo.streamType && zzdk.zza(this.zzde, mediaInfo.zzde) && zzdk.zza(this.zzdf, mediaInfo.zzdf) && this.zzdg == mediaInfo.zzdg && zzdk.zza(this.zzdh, mediaInfo.zzdh) && zzdk.zza(this.zzdi, mediaInfo.zzdi) && zzdk.zza(this.zzdj, mediaInfo.zzdj) && zzdk.zza(this.zzdk, mediaInfo.zzdk) && zzdk.zza(this.zzdl, mediaInfo.zzdl) && zzdk.zza(this.zzdm, mediaInfo.zzdm) && this.zzdn == mediaInfo.zzdn;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzk, Integer.valueOf(this.streamType), this.zzde, this.zzdf, Long.valueOf(this.zzdg), String.valueOf(this.zzp), this.zzdh, this.zzdi, this.zzdj, this.zzdk, this.zzdl, this.zzdm, Long.valueOf(this.zzdn));
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.zzj = this.zzp == null ? null : this.zzp.toString();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getContentId(), false);
        SafeParcelWriter.writeInt(parcel, 3, getStreamType());
        SafeParcelWriter.writeString(parcel, 4, getContentType(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getMetadata(), i, false);
        SafeParcelWriter.writeLong(parcel, 6, getStreamDuration());
        SafeParcelWriter.writeTypedList(parcel, 7, getMediaTracks(), false);
        SafeParcelWriter.writeParcelable(parcel, 8, getTextTrackStyle(), i, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzj, false);
        SafeParcelWriter.writeTypedList(parcel, 10, getAdBreaks(), false);
        SafeParcelWriter.writeTypedList(parcel, 11, getAdBreakClips(), false);
        SafeParcelWriter.writeString(parcel, 12, getEntity(), false);
        SafeParcelWriter.writeParcelable(parcel, 13, getVmapAdsRequest(), i, false);
        SafeParcelWriter.writeLong(parcel, 14, this.zzdn);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
