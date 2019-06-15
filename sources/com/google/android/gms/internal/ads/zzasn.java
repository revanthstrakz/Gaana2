package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzasn implements Creator<zzasm> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzasm[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = j;
        long j3 = j2;
        long j4 = j3;
        int i = 0;
        int i2 = i;
        boolean z = i2;
        int i3 = z;
        boolean z2 = i3;
        boolean z3 = z2;
        boolean z4 = z3;
        boolean z5 = z4;
        boolean z6 = z5;
        boolean z7 = z6;
        boolean z8 = z7;
        boolean z9 = z8;
        boolean z10 = z9;
        boolean z11 = z10;
        boolean z12 = z11;
        boolean z13 = z12;
        boolean z14 = z13;
        boolean z15 = z14;
        int i4 = z15;
        boolean z16 = i4;
        boolean z17 = z16;
        boolean z18 = z17;
        String str = null;
        String str2 = str;
        List list = str2;
        List list2 = list;
        List list3 = list2;
        String str3 = list3;
        String str4 = str3;
        String str5 = str4;
        String str6 = str5;
        zzasy zzasy = str6;
        String str7 = zzasy;
        String str8 = str7;
        zzawd zzawd = str8;
        List list4 = zzawd;
        List list5 = list4;
        zzaso zzaso = list5;
        String str9 = zzaso;
        List list6 = str9;
        String str10 = list6;
        zzawo zzawo = str10;
        String str11 = zzawo;
        Bundle bundle = str11;
        List list7 = bundle;
        String str12 = list7;
        String str13 = str12;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 4:
                    list = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 5:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 6:
                    list2 = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 7:
                    j = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 8:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 9:
                    j2 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 10:
                    list3 = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 11:
                    j3 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 12:
                    i3 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 13:
                    str3 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 14:
                    j4 = SafeParcelReader.readLong(parcel2, readHeader);
                    break;
                case 15:
                    str4 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 18:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 19:
                    str5 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 21:
                    str6 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 22:
                    z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 23:
                    z4 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 24:
                    z5 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 25:
                    z6 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 26:
                    z7 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 28:
                    zzasy = (zzasy) SafeParcelReader.createParcelable(parcel2, readHeader, zzasy.CREATOR);
                    break;
                case 29:
                    str7 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 30:
                    str8 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 31:
                    z8 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 32:
                    z9 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 33:
                    zzawd = (zzawd) SafeParcelReader.createParcelable(parcel2, readHeader, zzawd.CREATOR);
                    break;
                case 34:
                    list4 = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 35:
                    list5 = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 36:
                    z10 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 37:
                    zzaso = (zzaso) SafeParcelReader.createParcelable(parcel2, readHeader, zzaso.CREATOR);
                    break;
                case 38:
                    z11 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 39:
                    str9 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 40:
                    list6 = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 42:
                    z12 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 43:
                    str10 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 44:
                    zzawo = (zzawo) SafeParcelReader.createParcelable(parcel2, readHeader, zzawo.CREATOR);
                    break;
                case 45:
                    str11 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 46:
                    z13 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 47:
                    z14 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 48:
                    bundle = SafeParcelReader.createBundle(parcel2, readHeader);
                    break;
                case 49:
                    z15 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 50:
                    i4 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 51:
                    z16 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 52:
                    list7 = SafeParcelReader.createStringList(parcel2, readHeader);
                    break;
                case 53:
                    z17 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 54:
                    str12 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 55:
                    str13 = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                case 56:
                    z18 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new zzasm(i, str, str2, list, i2, list2, j, z, j2, list3, j3, i3, str3, j4, str4, z2, str5, str6, z3, z4, z5, z6, z7, zzasy, str7, str8, z8, z9, zzawd, list4, list5, z10, zzaso, z11, str9, list6, z12, str10, zzawo, str11, z13, z14, bundle, z15, i4, z16, list7, z17, str12, str13, z18);
    }
}
