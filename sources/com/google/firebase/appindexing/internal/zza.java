package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.firebase.appindexing.Action;

@Class(creator = "ActionImplCreator")
@Reserved({1000})
public final class zza extends AbstractSafeParcelable implements Action {
    public static final Creator<zza> CREATOR = new zzc();
    @Field(getter = "getActionType", id = 1)
    private final String zzar;
    @Field(getter = "getPropertyBundle", id = 7)
    private final Bundle zzax;
    @Field(getter = "getObjectName", id = 2)
    private final String zzea;
    @Field(getter = "getObjectUrl", id = 3)
    private final String zzeb;
    @Field(getter = "getObjectSameAs", id = 4)
    private final String zzec;
    @Field(getter = "getMetadata", id = 5)
    private final zzb zzed;
    @Field(getter = "getActionStatus", id = 6)
    private final String zzee;

    @Constructor
    public zza(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) String str3, @Param(id = 4) String str4, @Param(id = 5) zzb zzb, @Param(id = 6) String str5, @Param(id = 7) Bundle bundle) {
        this.zzar = str;
        this.zzea = str2;
        this.zzeb = str3;
        this.zzec = str4;
        this.zzed = zzb;
        this.zzee = str5;
        if (bundle != null) {
            this.zzax = bundle;
        } else {
            this.zzax = Bundle.EMPTY;
        }
        this.zzax.setClassLoader(getClass().getClassLoader());
    }

    public final zzb zzad() {
        return this.zzed;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzar, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzea, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzeb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzec, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzed, i, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzee, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzax, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ActionImpl { ");
        stringBuilder.append("{ actionType: '");
        stringBuilder.append(this.zzar);
        stringBuilder.append("' } ");
        stringBuilder.append("{ objectName: '");
        stringBuilder.append(this.zzea);
        stringBuilder.append("' } ");
        stringBuilder.append("{ objectUrl: '");
        stringBuilder.append(this.zzeb);
        stringBuilder.append("' } ");
        if (this.zzec != null) {
            stringBuilder.append("{ objectSameAs: '");
            stringBuilder.append(this.zzec);
            stringBuilder.append("' } ");
        }
        if (this.zzed != null) {
            stringBuilder.append("{ metadata: '");
            stringBuilder.append(this.zzed.toString());
            stringBuilder.append("' } ");
        }
        if (this.zzee != null) {
            stringBuilder.append("{ actionStatus: '");
            stringBuilder.append(this.zzee);
            stringBuilder.append("' } ");
        }
        if (!this.zzax.isEmpty()) {
            stringBuilder.append("{ ");
            stringBuilder.append(this.zzax);
            stringBuilder.append(" } ");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
