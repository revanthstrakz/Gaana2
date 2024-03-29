package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzd implements Creator<GroundOverlayOptions> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        boolean z2 = z;
        IBinder iBinder = null;
        LatLng latLng = iBinder;
        LatLngBounds latLngBounds = latLng;
        float f = 0.0f;
        float f2 = f;
        float f3 = f2;
        float f4 = f3;
        float f5 = f4;
        float f6 = f5;
        float f7 = f6;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    iBinder = SafeParcelReader.readIBinder(parcel2, readHeader);
                    break;
                case 3:
                    latLng = (LatLng) SafeParcelReader.createParcelable(parcel2, readHeader, LatLng.CREATOR);
                    break;
                case 4:
                    f = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 5:
                    f2 = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) SafeParcelReader.createParcelable(parcel2, readHeader, LatLngBounds.CREATOR);
                    break;
                case 7:
                    f3 = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 8:
                    f4 = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 9:
                    z = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                case 10:
                    f5 = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 11:
                    f6 = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 12:
                    f7 = SafeParcelReader.readFloat(parcel2, readHeader);
                    break;
                case 13:
                    z2 = SafeParcelReader.readBoolean(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new GroundOverlayOptions(iBinder, latLng, f, f2, latLngBounds, f3, f4, z, f5, f6, f7, z2);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new GroundOverlayOptions[i];
    }
}
