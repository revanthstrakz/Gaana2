package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zze implements Creator<CredentialPickerConfig> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new CredentialPickerConfig[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        boolean z = i;
        boolean z2 = z;
        boolean z3 = z2;
        int i2 = z3;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId != 1000) {
                switch (fieldId) {
                    case 1:
                        z = SafeParcelReader.readBoolean(parcel, readHeader);
                        break;
                    case 2:
                        z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                        break;
                    case 3:
                        z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                        break;
                    case 4:
                        i2 = SafeParcelReader.readInt(parcel, readHeader);
                        break;
                    default:
                        SafeParcelReader.skipUnknownField(parcel, readHeader);
                        break;
                }
            }
            i = SafeParcelReader.readInt(parcel, readHeader);
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new CredentialPickerConfig(i, z, z2, z3, i2);
    }
}
