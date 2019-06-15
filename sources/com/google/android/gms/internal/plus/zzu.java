package com.google.android.gms.internal.plus;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.internal.plus.zzr.zzb;
import com.google.android.gms.internal.plus.zzr.zzb.zza;
import java.util.HashSet;

public final class zzu implements Creator<zzb> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        zza zza = null;
        zzb.zzb zzb = zza;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    readHeader = 1;
                    break;
                case 2:
                    zza = (zza) SafeParcelReader.createParcelable(parcel, readHeader, zza.CREATOR);
                    readHeader = 2;
                    break;
                case 3:
                    zzb = (zzb.zzb) SafeParcelReader.createParcelable(parcel, readHeader, zzb.zzb.CREATOR);
                    readHeader = 3;
                    break;
                case 4:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    readHeader = 4;
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    continue;
            }
            hashSet.add(Integer.valueOf(readHeader));
        }
        if (parcel.dataPosition() == validateObjectHeader) {
            return new zzb(hashSet, i, zza, zzb, i2);
        }
        StringBuilder stringBuilder = new StringBuilder(37);
        stringBuilder.append("Overread allowed size end=");
        stringBuilder.append(validateObjectHeader);
        throw new ParseException(stringBuilder.toString(), parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzb[i];
    }
}
