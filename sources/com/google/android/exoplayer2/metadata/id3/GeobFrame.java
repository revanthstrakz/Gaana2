package com.google.android.exoplayer2.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class GeobFrame extends Id3Frame {
    public static final Creator<GeobFrame> CREATOR = new Creator<GeobFrame>() {
        public GeobFrame createFromParcel(Parcel parcel) {
            return new GeobFrame(parcel);
        }

        public GeobFrame[] newArray(int i) {
            return new GeobFrame[i];
        }
    };
    public static final String ID = "GEOB";
    public final byte[] data;
    public final String description;
    public final String filename;
    public final String mimeType;

    public GeobFrame(String str, String str2, String str3, byte[] bArr) {
        super(ID);
        this.mimeType = str;
        this.filename = str2;
        this.description = str3;
        this.data = bArr;
    }

    GeobFrame(Parcel parcel) {
        super(ID);
        this.mimeType = (String) Util.castNonNull(parcel.readString());
        this.filename = (String) Util.castNonNull(parcel.readString());
        this.description = (String) Util.castNonNull(parcel.readString());
        this.data = (byte[]) Util.castNonNull(parcel.createByteArray());
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeobFrame geobFrame = (GeobFrame) obj;
        if (!(Util.areEqual(this.mimeType, geobFrame.mimeType) && Util.areEqual(this.filename, geobFrame.filename) && Util.areEqual(this.description, geobFrame.description) && Arrays.equals(this.data, geobFrame.data))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((527 + (this.mimeType != null ? this.mimeType.hashCode() : 0)) * 31) + (this.filename != null ? this.filename.hashCode() : 0)) * 31;
        if (this.description != null) {
            i = this.description.hashCode();
        }
        return (31 * (hashCode + i)) + Arrays.hashCode(this.data);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.id);
        stringBuilder.append(": mimeType=");
        stringBuilder.append(this.mimeType);
        stringBuilder.append(", filename=");
        stringBuilder.append(this.filename);
        stringBuilder.append(", description=");
        stringBuilder.append(this.description);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mimeType);
        parcel.writeString(this.filename);
        parcel.writeString(this.description);
        parcel.writeByteArray(this.data);
    }
}
