package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public final class aw implements Parcelable {
    public static final Creator<aw> e = new Creator<aw>() {
        /* renamed from: a */
        public aw createFromParcel(Parcel parcel) {
            return new aw(parcel);
        }

        /* renamed from: a */
        public aw[] newArray(int i) {
            return new aw[0];
        }
    };
    public final int a;
    public final int b;
    public final int c;
    public final byte[] d;
    private int f;

    public aw(int i, int i2, int i3, byte[] bArr) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = bArr;
    }

    public int describeContents() {
        return 0;
    }

    aw(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = (parcel.readInt() != 0 ? 1 : null) != null ? parcel.createByteArray() : null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        aw awVar = (aw) obj;
        return this.a == awVar.a && this.b == awVar.b && this.c == awVar.c && Arrays.equals(this.d, awVar.d);
    }

    public String toString() {
        int i = this.a;
        int i2 = this.b;
        int i3 = this.c;
        boolean z = this.d != null;
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

    public int hashCode() {
        if (this.f == 0) {
            this.f = (31 * (((((527 + this.a) * 31) + this.b) * 31) + this.c)) + Arrays.hashCode(this.d);
        }
        return this.f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d != null ? 1 : 0);
        if (this.d != null) {
            parcel.writeByteArray(this.d);
        }
    }
}
