package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Arrays;
import java.util.Comparator;

@Class(creator = "FlagCreator")
@Reserved({1})
public final class zzi extends AbstractSafeParcelable implements Comparable<zzi> {
    public static final Creator<zzi> CREATOR = new zzk();
    private static final Comparator<zzi> zzai = new zzj();
    @Field(id = 2)
    public final String name;
    @Field(id = 3)
    private final long zzab;
    @Field(id = 4)
    private final boolean zzac;
    @Field(id = 5)
    private final double zzad;
    @Field(id = 6)
    private final String zzae;
    @Field(id = 7)
    private final byte[] zzaf;
    @Field(id = 8)
    private final int zzag;
    @Field(id = 9)
    public final int zzah;

    @Constructor
    public zzi(@Param(id = 2) String str, @Param(id = 3) long j, @Param(id = 4) boolean z, @Param(id = 5) double d, @Param(id = 6) String str2, @Param(id = 7) byte[] bArr, @Param(id = 8) int i, @Param(id = 9) int i2) {
        this.name = str;
        this.zzab = j;
        this.zzac = z;
        this.zzad = d;
        this.zzae = str2;
        this.zzaf = bArr;
        this.zzag = i;
        this.zzah = i2;
    }

    private static int compare(int i, int i2) {
        return i < i2 ? -1 : i == i2 ? 0 : 1;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        zzi zzi = (zzi) obj;
        int compareTo = this.name.compareTo(zzi.name);
        if (compareTo != 0) {
            return compareTo;
        }
        compareTo = compare(this.zzag, zzi.zzag);
        if (compareTo != 0) {
            return compareTo;
        }
        int i = 0;
        switch (this.zzag) {
            case 1:
                long j = this.zzab;
                long j2 = zzi.zzab;
                return j < j2 ? -1 : j == j2 ? 0 : 1;
            case 2:
                boolean z = this.zzac;
                return z == zzi.zzac ? 0 : z ? 1 : -1;
            case 3:
                return Double.compare(this.zzad, zzi.zzad);
            case 4:
                String str = this.zzae;
                String str2 = zzi.zzae;
                return str == str2 ? 0 : str == null ? -1 : str2 == null ? 1 : str.compareTo(str2);
            case 5:
                if (this.zzaf == zzi.zzaf) {
                    return 0;
                }
                if (this.zzaf == null) {
                    return -1;
                }
                if (zzi.zzaf == null) {
                    return 1;
                }
                while (i < Math.min(this.zzaf.length, zzi.zzaf.length)) {
                    compareTo = this.zzaf[i] - zzi.zzaf[i];
                    if (compareTo != 0) {
                        return compareTo;
                    }
                    i++;
                }
                return compare(this.zzaf.length, zzi.zzaf.length);
            default:
                compareTo = this.zzag;
                StringBuilder stringBuilder = new StringBuilder(31);
                stringBuilder.append("Invalid enum value: ");
                stringBuilder.append(compareTo);
                throw new AssertionError(stringBuilder.toString());
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzi) {
            zzi zzi = (zzi) obj;
            if (!zzn.equals(this.name, zzi.name) || this.zzag != zzi.zzag || this.zzah != zzi.zzah) {
                return false;
            }
            switch (this.zzag) {
                case 1:
                    if (this.zzab == zzi.zzab) {
                        return true;
                    }
                    break;
                case 2:
                    return this.zzac == zzi.zzac;
                case 3:
                    return this.zzad == zzi.zzad;
                case 4:
                    return zzn.equals(this.zzae, zzi.zzae);
                case 5:
                    return Arrays.equals(this.zzaf, zzi.zzaf);
                default:
                    int i = this.zzag;
                    StringBuilder stringBuilder = new StringBuilder(31);
                    stringBuilder.append("Invalid enum value: ");
                    stringBuilder.append(i);
                    throw new AssertionError(stringBuilder.toString());
            }
        }
        return false;
    }

    /* JADX WARNING: Missing block: B:9:0x0062, code skipped:
            r0.append(r1);
            r1 = "'";
     */
    /* JADX WARNING: Missing block: B:10:0x0067, code skipped:
            r0.append(r1);
     */
    /* JADX WARNING: Missing block: B:14:0x007c, code skipped:
            r0.append(", ");
            r0.append(r5.zzag);
            r0.append(", ");
            r0.append(r5.zzah);
            r0.append(")");
     */
    /* JADX WARNING: Missing block: B:15:0x0099, code skipped:
            return r0.toString();
     */
    public final java.lang.String toString() {
        /*
        r5 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Flag(";
        r0.append(r1);
        r1 = r5.name;
        r0.append(r1);
        r1 = ", ";
        r0.append(r1);
        r1 = r5.zzag;
        switch(r1) {
            case 1: goto L_0x0077;
            case 2: goto L_0x0071;
            case 3: goto L_0x006b;
            case 4: goto L_0x005b;
            case 5: goto L_0x0047;
            default: goto L_0x0019;
        };
    L_0x0019:
        r0 = new java.lang.AssertionError;
        r1 = r5.name;
        r2 = r5.zzag;
        r3 = 27;
        r4 = java.lang.String.valueOf(r1);
        r4 = r4.length();
        r3 = r3 + r4;
        r4 = new java.lang.StringBuilder;
        r4.<init>(r3);
        r3 = "Invalid type: ";
        r4.append(r3);
        r4.append(r1);
        r1 = ", ";
        r4.append(r1);
        r4.append(r2);
        r1 = r4.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0047:
        r1 = r5.zzaf;
        if (r1 != 0) goto L_0x004e;
    L_0x004b:
        r1 = "null";
        goto L_0x0067;
    L_0x004e:
        r1 = "'";
        r0.append(r1);
        r1 = r5.zzaf;
        r2 = 3;
        r1 = android.util.Base64.encodeToString(r1, r2);
        goto L_0x0062;
    L_0x005b:
        r1 = "'";
        r0.append(r1);
        r1 = r5.zzae;
    L_0x0062:
        r0.append(r1);
        r1 = "'";
    L_0x0067:
        r0.append(r1);
        goto L_0x007c;
    L_0x006b:
        r1 = r5.zzad;
        r0.append(r1);
        goto L_0x007c;
    L_0x0071:
        r1 = r5.zzac;
        r0.append(r1);
        goto L_0x007c;
    L_0x0077:
        r1 = r5.zzab;
        r0.append(r1);
    L_0x007c:
        r1 = ", ";
        r0.append(r1);
        r1 = r5.zzag;
        r0.append(r1);
        r1 = ", ";
        r0.append(r1);
        r1 = r5.zzah;
        r0.append(r1);
        r1 = ")";
        r0.append(r1);
        r0 = r0.toString();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.phenotype.zzi.toString():java.lang.String");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzab);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzac);
        SafeParcelWriter.writeDouble(parcel, 5, this.zzad);
        SafeParcelWriter.writeString(parcel, 6, this.zzae, false);
        SafeParcelWriter.writeByteArray(parcel, 7, this.zzaf, false);
        SafeParcelWriter.writeInt(parcel, 8, this.zzag);
        SafeParcelWriter.writeInt(parcel, 9, this.zzah);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }
}
