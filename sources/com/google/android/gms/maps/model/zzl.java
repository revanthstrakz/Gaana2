package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzl implements Creator<PolylineOptions> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        float f = 0.0f;
        float f2 = f;
        List list = null;
        Cap cap = list;
        Cap cap2 = cap;
        List list2 = cap2;
        int i = 0;
        boolean z = i;
        boolean z2 = z;
        boolean z3 = z2;
        int i2 = z3;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    list = SafeParcelReader.createTypedList(parcel2, readHeader, LatLng.CREATOR);
                    break;
                case 3:
                    f = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 5:
                    f2 = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 7:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 8:
                    z3 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 9:
                    cap = (Cap) SafeParcelReader.createParcelable(parcel2, readHeader, Cap.CREATOR);
                    break;
                case 10:
                    cap2 = (Cap) SafeParcelReader.createParcelable(parcel2, readHeader, Cap.CREATOR);
                    break;
                case 11:
                    i2 = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 12:
                    list2 = SafeParcelReader.createTypedList(parcel2, readHeader, PatternItem.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new PolylineOptions(list, f, i, f2, z, z2, z3, cap, cap2, i2, list2);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PolylineOptions[i];
    }
}
