package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.cast.zzdk;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "RequestItemCreator")
@Reserved({1})
public final class zzbx extends AbstractSafeParcelable {
    public static final Creator<zzbx> CREATOR = new zzby();
    @Field(getter = "getUrl", id = 2)
    private final String url;
    @Field(getter = "getProtocolType", id = 3)
    private final int zzgr;
    @Field(defaultValue = "0", getter = "getInitialTime", id = 4)
    private final int zzgs;
    @Field(getter = "getHlsSegmentFormat", id = 5)
    private final String zzn;

    @Constructor
    public zzbx(@Param(id = 2) String str, @Param(id = 3) int i, @Param(id = 4) int i2, @HlsSegmentFormat @Param(id = 5) String str2) {
        this.url = str;
        this.zzgr = i;
        this.zzgs = i2;
        this.zzn = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbx)) {
            return false;
        }
        zzbx zzbx = (zzbx) obj;
        return zzdk.zza(this.url, zzbx.url) && zzdk.zza(Integer.valueOf(this.zzgr), Integer.valueOf(zzbx.zzgr)) && zzdk.zza(Integer.valueOf(this.zzgs), Integer.valueOf(zzbx.zzgs)) && zzdk.zza(zzbx.zzn, this.zzn);
    }

    public final JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("url", this.url);
        jSONObject.put("protocolType", this.zzgr);
        jSONObject.put("initialTime", this.zzgs);
        jSONObject.put("hlsSegmentFormat", this.zzn);
        return jSONObject;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.url, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzgr);
        SafeParcelWriter.writeInt(parcel, 4, this.zzgs);
        SafeParcelWriter.writeString(parcel, 5, this.zzn, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    @VisibleForTesting
    public final int hashCode() {
        return Objects.hashCode(this.url, Integer.valueOf(this.zzgr), Integer.valueOf(this.zzgs), this.zzn);
    }
}
