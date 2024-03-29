package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public final class zzqi implements Parcelable {
    public static final Creator<zzqi> CREATOR = new zzqj();
    private int zzaac;
    public final int zzakc;
    public final int zzakd;
    public final int zzake;
    public final byte[] zzbii;

    public zzqi(int i, int i2, int i3, byte[] bArr) {
        this.zzakc = i;
        this.zzake = i2;
        this.zzakd = i3;
        this.zzbii = bArr;
    }

    public final int describeContents() {
        return 0;
    }

    zzqi(Parcel parcel) {
        this.zzakc = parcel.readInt();
        this.zzake = parcel.readInt();
        this.zzakd = parcel.readInt();
        this.zzbii = (parcel.readInt() != 0 ? 1 : null) != null ? parcel.createByteArray() : null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzqi zzqi = (zzqi) obj;
        return this.zzakc == zzqi.zzakc && this.zzake == zzqi.zzake && this.zzakd == zzqi.zzakd && Arrays.equals(this.zzbii, zzqi.zzbii);
    }

    public final String toString() {
        int i = this.zzakc;
        int i2 = this.zzake;
        int i3 = this.zzakd;
        boolean z = this.zzbii != null;
        StringBuilder stringBuilder = new StringBuilder(55);
        stringBuilder.append("ColorInfo(");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(i2);
        stringBuilder.append(", ");
        stringBuilder.append(i3);
        stringBuilder.append(", ");
        stringBuilder.append(z);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public final int hashCode() {
        if (this.zzaac == 0) {
            this.zzaac = ((((((527 + this.zzakc) * 31) + this.zzake) * 31) + this.zzakd) * 31) + Arrays.hashCode(this.zzbii);
        }
        return this.zzaac;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.zzakc);
        parcel.writeInt(this.zzake);
        parcel.writeInt(this.zzakd);
        parcel.writeInt(this.zzbii != null ? 1 : 0);
        if (this.zzbii != null) {
            parcel.writeByteArray(this.zzbii);
        }
    }
}
