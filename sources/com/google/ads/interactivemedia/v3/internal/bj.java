package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class bj implements Parcelable {
    public static final Creator<bj> x = new Creator<bj>() {
        /* renamed from: a */
        public bj createFromParcel(Parcel parcel) {
            return new bj(parcel);
        }

        /* renamed from: a */
        public bj[] newArray(int i) {
            return new bj[i];
        }
    };
    public final String a;
    public final String b;
    public final int c;
    public final int d;
    public final long e;
    public final List<byte[]> f;
    public final boolean g;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    public final int l;
    public final float m;
    public final int n;
    public final byte[] o;
    public final aw p;
    public final int q;
    public final int r;
    public final int s;
    public final int t;
    public final int u;
    public final String v;
    public final long w;
    private int y;
    private MediaFormat z;

    public static bj a(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list, int i5, float f) {
        return new bj(str, str2, i, i2, j, i3, i4, i5, f, -1, -1, null, Long.MAX_VALUE, list, false, -1, -1, -1, -1, -1, null, -1, null);
    }

    public int describeContents() {
        return 0;
    }

    public static bj a(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list, int i5, float f, byte[] bArr, int i6, aw awVar) {
        return new bj(str, str2, i, i2, j, i3, i4, i5, f, -1, -1, null, Long.MAX_VALUE, list, false, -1, -1, -1, -1, -1, bArr, i6, awVar);
    }

    public static bj a(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list, String str3) {
        return a(str, str2, i, i2, j, i3, i4, (List) list, str3, -1);
    }

    public static bj a(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list, String str3, int i5) {
        return new bj(str, str2, i, i2, j, -1, -1, -1, -1.0f, i3, i4, str3, Long.MAX_VALUE, list, false, -1, -1, i5, -1, -1, null, -1, null);
    }

    public static bj a(String str, String str2, int i, long j, String str3) {
        return a(str, str2, i, j, str3, Long.MAX_VALUE);
    }

    public static bj a(String str, String str2, int i, long j, String str3, long j2) {
        return new bj(str, str2, i, -1, j, -1, -1, -1, -1.0f, -1, -1, str3, j2, null, false, -1, -1, -1, -1, -1, null, -1, null);
    }

    public static bj a(String str, String str2, int i, long j, List<byte[]> list, String str3) {
        return new bj(str, str2, i, -1, j, -1, -1, -1, -1.0f, -1, -1, str3, Long.MAX_VALUE, list, false, -1, -1, -1, -1, -1, null, -1, null);
    }

    public static bj a(String str, String str2, int i, long j) {
        return new bj(str, str2, i, -1, j, -1, -1, -1, -1.0f, -1, -1, null, Long.MAX_VALUE, null, false, -1, -1, -1, -1, -1, null, -1, null);
    }

    public static bj a() {
        return a(null, MimeTypes.APPLICATION_ID3, -1, -1);
    }

    bj(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readLong();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readFloat();
        this.q = parcel.readInt();
        this.r = parcel.readInt();
        this.v = parcel.readString();
        this.w = parcel.readLong();
        this.f = new ArrayList();
        byte[] bArr = null;
        parcel.readList(this.f, null);
        Object obj = null;
        this.g = parcel.readInt() == 1;
        this.j = parcel.readInt();
        this.k = parcel.readInt();
        this.s = parcel.readInt();
        this.t = parcel.readInt();
        this.u = parcel.readInt();
        if (parcel.readInt() != 0) {
            obj = 1;
        }
        if (obj != null) {
            bArr = parcel.createByteArray();
        }
        this.o = bArr;
        this.n = parcel.readInt();
        this.p = (aw) parcel.readParcelable(aw.class.getClassLoader());
    }

    bj(String str, String str2, int i, int i2, long j, int i3, int i4, int i5, float f, int i6, int i7, String str3, long j2, List<byte[]> list, boolean z, int i8, int i9, int i10, int i11, int i12, byte[] bArr, int i13, aw awVar) {
        this.a = str;
        this.b = fe.a(str2);
        this.c = i;
        this.d = i2;
        this.e = j;
        this.h = i3;
        this.i = i4;
        this.l = i5;
        this.m = f;
        this.q = i6;
        this.r = i7;
        this.v = str3;
        this.w = j2;
        this.f = list == null ? Collections.emptyList() : list;
        this.g = z;
        this.j = i8;
        this.k = i9;
        this.s = i10;
        this.t = i11;
        this.u = i12;
        this.o = bArr;
        this.n = i13;
        this.p = awVar;
    }

    public bj a(int i) {
        String str = this.a;
        String str2 = this.b;
        int i2 = this.c;
        long j = this.e;
        int i3 = this.h;
        int i4 = this.i;
        int i5 = this.l;
        float f = this.m;
        int i6 = this.q;
        int i7 = this.r;
        String str3 = this.v;
        long j2 = this.w;
        List list = this.f;
        boolean z = this.g;
        long j3 = j2;
        int i8 = this.j;
        int i9 = this.k;
        int i10 = i8;
        List list2 = list;
        int i11 = i9;
        long j4 = j3;
        return new bj(str, str2, i2, i, j, i3, i4, i5, f, i6, i7, str3, j4, list2, z, i10, i11, this.s, this.t, this.u, this.o, this.n, this.p);
    }

    public bj a(int i, int i2) {
        String str = this.a;
        String str2 = this.b;
        int i3 = this.c;
        int i4 = this.d;
        long j = this.e;
        int i5 = this.h;
        int i6 = this.i;
        int i7 = this.l;
        float f = this.m;
        int i8 = this.q;
        int i9 = this.r;
        String str3 = this.v;
        long j2 = this.w;
        List list = this.f;
        long j3 = j2;
        boolean z = this.g;
        int i10 = this.j;
        boolean z2 = z;
        int i11 = this.k;
        int i12 = this.s;
        int i13 = i10;
        return new bj(str, str2, i3, i4, j, i5, i6, i7, f, i8, i9, str3, j3, list, z2, i13, i11, i12, i, i2, this.o, this.n, this.p);
    }

    @SuppressLint({"InlinedApi"})
    @TargetApi(16)
    public final MediaFormat b() {
        if (this.z == null) {
            MediaFormat mediaFormat = new MediaFormat();
            mediaFormat.setString("mime", this.b);
            a(mediaFormat, "language", this.v);
            a(mediaFormat, "max-input-size", this.d);
            a(mediaFormat, "width", this.h);
            a(mediaFormat, "height", this.i);
            a(mediaFormat, "rotation-degrees", this.l);
            a(mediaFormat, "max-width", this.j);
            a(mediaFormat, "max-height", this.k);
            a(mediaFormat, "channel-count", this.q);
            a(mediaFormat, "sample-rate", this.r);
            a(mediaFormat, "encoder-delay", this.t);
            a(mediaFormat, "encoder-padding", this.u);
            for (int i = 0; i < this.f.size(); i++) {
                StringBuilder stringBuilder = new StringBuilder(15);
                stringBuilder.append("csd-");
                stringBuilder.append(i);
                mediaFormat.setByteBuffer(stringBuilder.toString(), ByteBuffer.wrap((byte[]) this.f.get(i)));
            }
            if (this.e != -1) {
                mediaFormat.setLong("durationUs", this.e);
            }
            a(mediaFormat, this.p);
            this.z = mediaFormat;
        }
        return this.z;
    }

    public String toString() {
        String str = this.a;
        String str2 = this.b;
        int i = this.c;
        int i2 = this.d;
        int i3 = this.h;
        int i4 = this.i;
        int i5 = this.l;
        float f = this.m;
        int i6 = this.q;
        int i7 = this.r;
        String str3 = this.v;
        long j = this.e;
        boolean z = this.g;
        int i8 = this.j;
        int i9 = this.k;
        int i10 = this.s;
        int i11 = this.t;
        int i12 = this.u;
        StringBuilder stringBuilder = new StringBuilder(((219 + String.valueOf(str).length()) + String.valueOf(str2).length()) + String.valueOf(str3).length());
        stringBuilder.append("MediaFormat(");
        stringBuilder.append(str);
        stringBuilder.append(", ");
        stringBuilder.append(str2);
        stringBuilder.append(", ");
        stringBuilder.append(i);
        stringBuilder.append(", ");
        stringBuilder.append(i2);
        stringBuilder.append(", ");
        stringBuilder.append(i3);
        stringBuilder.append(", ");
        stringBuilder.append(i4);
        stringBuilder.append(", ");
        stringBuilder.append(i5);
        stringBuilder.append(", ");
        stringBuilder.append(f);
        stringBuilder.append(", ");
        stringBuilder.append(i6);
        stringBuilder.append(", ");
        stringBuilder.append(i7);
        stringBuilder.append(", ");
        stringBuilder.append(str3);
        stringBuilder.append(", ");
        stringBuilder.append(j);
        stringBuilder.append(", ");
        stringBuilder.append(z);
        stringBuilder.append(", ");
        stringBuilder.append(i8);
        stringBuilder.append(", ");
        stringBuilder.append(i9);
        stringBuilder.append(", ");
        stringBuilder.append(i10);
        stringBuilder.append(", ");
        stringBuilder.append(i11);
        stringBuilder.append(", ");
        stringBuilder.append(i12);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public int hashCode() {
        if (this.y == 0) {
            int i = 0;
            int hashCode = ((((((((((((((((((((((((((((((((((((527 + (this.a == null ? 0 : this.a.hashCode())) * 31) + (this.b == null ? 0 : this.b.hashCode())) * 31) + this.c) * 31) + this.d) * 31) + this.h) * 31) + this.i) * 31) + this.l) * 31) + Float.floatToRawIntBits(this.m)) * 31) + ((int) this.e)) * 31) + (this.g ? 1231 : 1237)) * 31) + this.j) * 31) + this.k) * 31) + this.q) * 31) + this.r) * 31) + this.s) * 31) + this.t) * 31) + this.u) * 31) + (this.v == null ? 0 : this.v.hashCode())) * 31) + ((int) this.w);
            while (i < this.f.size()) {
                hashCode = (hashCode * 31) + Arrays.hashCode((byte[]) this.f.get(i));
                i++;
            }
            this.y = (31 * ((hashCode * 31) + Arrays.hashCode(this.o))) + this.n;
        }
        return this.y;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bj bjVar = (bj) obj;
        if (this.g != bjVar.g || this.c != bjVar.c || this.d != bjVar.d || this.e != bjVar.e || this.h != bjVar.h || this.i != bjVar.i || this.l != bjVar.l || this.m != bjVar.m || this.j != bjVar.j || this.k != bjVar.k || this.q != bjVar.q || this.r != bjVar.r || this.s != bjVar.s || this.t != bjVar.t || this.u != bjVar.u || this.w != bjVar.w || !ft.a(this.a, bjVar.a) || !ft.a(this.v, bjVar.v) || !ft.a(this.b, bjVar.b) || this.f.size() != bjVar.f.size() || !ft.a(this.p, bjVar.p) || !Arrays.equals(this.o, bjVar.o) || this.n != bjVar.n) {
            return false;
        }
        for (int i = 0; i < this.f.size(); i++) {
            if (!Arrays.equals((byte[]) this.f.get(i), (byte[]) bjVar.f.get(i))) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(24)
    private static void a(MediaFormat mediaFormat, aw awVar) {
        if (awVar != null) {
            a(mediaFormat, "color-transfer", awVar.c);
            a(mediaFormat, "color-standard", awVar.a);
            a(mediaFormat, "color-range", awVar.b);
            a(mediaFormat, "hdr-static-info", awVar.d);
        }
    }

    @TargetApi(16)
    private static final void a(MediaFormat mediaFormat, String str, String str2) {
        if (str2 != null) {
            mediaFormat.setString(str, str2);
        }
    }

    @TargetApi(16)
    private static final void a(MediaFormat mediaFormat, String str, int i) {
        if (i != -1) {
            mediaFormat.setInteger(str, i);
        }
    }

    @TargetApi(16)
    private static void a(MediaFormat mediaFormat, String str, byte[] bArr) {
        if (bArr != null) {
            mediaFormat.setByteBuffer(str, ByteBuffer.wrap(bArr));
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeLong(this.e);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.l);
        parcel.writeFloat(this.m);
        parcel.writeInt(this.q);
        parcel.writeInt(this.r);
        parcel.writeString(this.v);
        parcel.writeLong(this.w);
        parcel.writeList(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
        parcel.writeInt(this.s);
        parcel.writeInt(this.t);
        parcel.writeInt(this.u);
        parcel.writeInt(this.o != null ? 1 : 0);
        if (this.o != null) {
            parcel.writeByteArray(this.o);
        }
        parcel.writeInt(this.n);
        parcel.writeParcelable(this.p, i);
    }
}
