package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import java.util.Arrays;

public final class zzkk extends zzkp {
    public static final Creator<zzkk> CREATOR = new zzkl();
    private final String description;
    private final String mimeType;
    private final int zzavs;
    private final byte[] zzavt;

    public zzkk(String str, String str2, int i, byte[] bArr) {
        super(ApicFrame.ID);
        this.mimeType = str;
        this.description = null;
        this.zzavs = 3;
        this.zzavt = bArr;
    }

    zzkk(Parcel parcel) {
        super(ApicFrame.ID);
        this.mimeType = parcel.readString();
        this.description = parcel.readString();
        this.zzavs = parcel.readInt();
        this.zzavt = parcel.createByteArray();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzkk zzkk = (zzkk) obj;
        return this.zzavs == zzkk.zzavs && zzqe.zza(this.mimeType, zzkk.mimeType) && zzqe.zza(this.description, zzkk.description) && Arrays.equals(this.zzavt, zzkk.zzavt);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((527 + this.zzavs) * 31) + (this.mimeType != null ? this.mimeType.hashCode() : 0)) * 31;
        if (this.description != null) {
            i = this.description.hashCode();
        }
        return ((hashCode + i) * 31) + Arrays.hashCode(this.zzavt);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mimeType);
        parcel.writeString(this.description);
        parcel.writeInt(this.zzavs);
        parcel.writeByteArray(this.zzavt);
    }
}
