package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.cast.zzdk;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "AdBreakInfoCreator")
@Reserved({1})
public class AdBreakInfo extends AbstractSafeParcelable {
    public static final Creator<AdBreakInfo> CREATOR = new zzb();
    @Field(getter = "getId", id = 3)
    private final String zze;
    @Field(getter = "getDurationInMs", id = 4)
    private final long zzg;
    @Field(getter = "getPlaybackPositionInMs", id = 2)
    private final long zzq;
    @Field(getter = "isWatched", id = 5)
    private final boolean zzr;
    @Field(getter = "getBreakClipIds", id = 6)
    private String[] zzs;
    @Field(getter = "isEmbedded", id = 7)
    private final boolean zzt;

    public static class Builder {
        private String zze = null;
        private long zzg = 0;
        private long zzq = 0;
        private boolean zzr = false;
        private String[] zzs = null;
        private boolean zzt = false;

        public Builder(long j) {
            this.zzq = j;
        }

        public Builder setId(String str) {
            this.zze = str;
            return this;
        }

        public Builder setDurationInMs(long j) {
            this.zzg = j;
            return this;
        }

        public Builder setIsWatched(boolean z) {
            this.zzr = z;
            return this;
        }

        public Builder setIsEmbedded(boolean z) {
            this.zzt = z;
            return this;
        }

        public Builder setBreakClipIds(String[] strArr) {
            this.zzs = strArr;
            return this;
        }

        public AdBreakInfo build() {
            return new AdBreakInfo(this.zzq, this.zze, this.zzg, this.zzr, this.zzs, this.zzt);
        }
    }

    @Constructor
    public AdBreakInfo(@Param(id = 2) long j, @Param(id = 3) String str, @Param(id = 4) long j2, @Param(id = 5) boolean z, @Param(id = 6) String[] strArr, @Param(id = 7) boolean z2) {
        this.zzq = j;
        this.zze = str;
        this.zzg = j2;
        this.zzr = z;
        this.zzs = strArr;
        this.zzt = z2;
    }

    public long getPlaybackPositionInMs() {
        return this.zzq;
    }

    public String getId() {
        return this.zze;
    }

    public long getDurationInMs() {
        return this.zzg;
    }

    public boolean isWatched() {
        return this.zzr;
    }

    public boolean isEmbedded() {
        return this.zzt;
    }

    public String[] getBreakClipIds() {
        return this.zzs;
    }

    public void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getPlaybackPositionInMs());
        SafeParcelWriter.writeString(parcel, 3, getId(), false);
        SafeParcelWriter.writeLong(parcel, 4, getDurationInMs());
        SafeParcelWriter.writeBoolean(parcel, 5, isWatched());
        SafeParcelWriter.writeStringArray(parcel, 6, getBreakClipIds(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, isEmbedded());
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    public int hashCode() {
        return this.zze.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakInfo)) {
            return false;
        }
        AdBreakInfo adBreakInfo = (AdBreakInfo) obj;
        return zzdk.zza(this.zze, adBreakInfo.zze) && this.zzq == adBreakInfo.zzq && this.zzg == adBreakInfo.zzg && this.zzr == adBreakInfo.zzr && Arrays.equals(this.zzs, adBreakInfo.zzs) && this.zzt == adBreakInfo.zzt;
    }

    static AdBreakInfo zzb(JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.has("id") || !jSONObject.has("position")) {
            return null;
        }
        try {
            String[] strArr;
            String string = jSONObject.getString("id");
            long j = (long) (((double) jSONObject.getLong("position")) * 1000.0d);
            boolean optBoolean = jSONObject.optBoolean("isWatched");
            long optLong = (long) (((double) jSONObject.optLong("duration")) * 1000.0d);
            JSONArray optJSONArray = jSONObject.optJSONArray("breakClipIds");
            if (optJSONArray != null) {
                String[] strArr2 = new String[optJSONArray.length()];
                for (int i = 0; i < optJSONArray.length(); i++) {
                    strArr2[i] = optJSONArray.getString(i);
                }
                strArr = strArr2;
            } else {
                strArr = null;
            }
            return new AdBreakInfo(j, string, optLong, optBoolean, strArr, jSONObject.optBoolean("isEmbedded"));
        } catch (JSONException e) {
            Log.d("AdBreakInfo", String.format(Locale.ROOT, "Error while creating an AdBreakInfo from JSON: %s", new Object[]{e.getMessage()}));
            return null;
        }
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.zze);
            jSONObject.put("position", ((double) this.zzq) / 1000.0d);
            jSONObject.put("isWatched", this.zzr);
            jSONObject.put("isEmbedded", this.zzt);
            jSONObject.put("duration", ((double) this.zzg) / 1000.0d);
            if (this.zzs != null) {
                JSONArray jSONArray = new JSONArray();
                for (Object put : this.zzs) {
                    jSONArray.put(put);
                }
                jSONObject.put("breakClipIds", jSONArray);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
