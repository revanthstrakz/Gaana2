package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
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

@Class(creator = "VastAdsRequestCreator")
@Reserved({1})
public class VastAdsRequest extends AbstractSafeParcelable {
    public static final Creator<VastAdsRequest> CREATOR = new zzca();
    @Field(getter = "getAdTagUrl", id = 2)
    private final String zzgx;
    @Field(getter = "getAdsResponse", id = 3)
    private final String zzgy;

    public static class Builder {
        private String zzgx = null;
        private String zzgy = null;

        public Builder setAdTagUrl(String str) {
            this.zzgx = str;
            return this;
        }

        public Builder setAdsResponse(String str) {
            this.zzgy = str;
            return this;
        }

        public VastAdsRequest build() {
            return new VastAdsRequest(this.zzgx, this.zzgy);
        }
    }

    @Constructor
    VastAdsRequest(@Param(id = 2) String str, @Param(id = 3) String str2) {
        this.zzgx = str;
        this.zzgy = str2;
    }

    public String getAdTagUrl() {
        return this.zzgx;
    }

    public String getAdsResponse() {
        return this.zzgy;
    }

    public void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAdTagUrl(), false);
        SafeParcelWriter.writeString(parcel, 3, getAdsResponse(), false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzgx, this.zzgy);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VastAdsRequest)) {
            return false;
        }
        VastAdsRequest vastAdsRequest = (VastAdsRequest) obj;
        return zzdk.zza(this.zzgx, vastAdsRequest.zzgx) && zzdk.zza(this.zzgy, vastAdsRequest.zzgy);
    }

    public static VastAdsRequest fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new VastAdsRequest(jSONObject.optString("adTagUrl", null), jSONObject.optString("adsResponse", null));
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.zzgx != null) {
                jSONObject.put("adTagUrl", this.zzgx);
            }
            if (this.zzgy != null) {
                jSONObject.put("adsResponse", this.zzgy);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
