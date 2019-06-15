package com.google.android.exoplayer2.video;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class ColorInfo implements Parcelable {
    public static final Creator<ColorInfo> CREATOR = new Creator<ColorInfo>() {
        public ColorInfo createFromParcel(Parcel parcel) {
            return new ColorInfo(parcel);
        }

        public ColorInfo[] newArray(int i) {
            return new ColorInfo[0];
        }
    };
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    private int hashCode;
    @Nullable
    public final byte[] hdrStaticInfo;

    public int describeContents() {
        return 0;
    }

    public ColorInfo(int i, int i2, int i3, @Nullable byte[] bArr) {
        this.colorSpace = i;
        this.colorRange = i2;
        this.colorTransfer = i3;
        this.hdrStaticInfo = bArr;
    }

    ColorInfo(Parcel parcel) {
        this.colorSpace = parcel.readInt();
        this.colorRange = parcel.readInt();
        this.colorTransfer = parcel.readInt();
        this.hdrStaticInfo = Util.readBoolean(parcel) ? parcel.createByteArray() : null;
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorInfo colorInfo = (ColorInfo) obj;
        if (!(this.colorSpace == colorInfo.colorSpace && this.colorRange == colorInfo.colorRange && this.colorTransfer == colorInfo.colorTransfer && Arrays.equals(this.hdrStaticInfo, colorInfo.hdrStaticInfo))) {
            z = false;
        }
        return z;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ColorInfo(");
        stringBuilder.append(this.colorSpace);
        stringBuilder.append(", ");
        stringBuilder.append(this.colorRange);
        stringBuilder.append(", ");
        stringBuilder.append(this.colorTransfer);
        stringBuilder.append(", ");
        stringBuilder.append(this.hdrStaticInfo != null);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = (31 * (((((527 + this.colorSpace) * 31) + this.colorRange) * 31) + this.colorTransfer)) + Arrays.hashCode(this.hdrStaticInfo);
        }
        return this.hashCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.colorSpace);
        parcel.writeInt(this.colorRange);
        parcel.writeInt(this.colorTransfer);
        Util.writeBoolean(parcel, this.hdrStaticInfo != null);
        if (this.hdrStaticInfo != null) {
            parcel.writeByteArray(this.hdrStaticInfo);
        }
    }
}
