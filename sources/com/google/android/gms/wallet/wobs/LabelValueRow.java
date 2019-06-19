package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.ArrayUtils;
import java.util.ArrayList;
import java.util.Collection;

@Class(creator = "LabelValueRowCreator")
@Reserved({1})
public final class LabelValueRow extends AbstractSafeParcelable {
    public static final Creator<LabelValueRow> CREATOR = new zze();
    @Field(id = 2)
    @Deprecated
    String zzgp;
    @Field(id = 3)
    @Deprecated
    String zzgq;
    @Field(defaultValueUnchecked = "com.google.android.gms.common.util.ArrayUtils.newArrayList()", id = 4)
    ArrayList<LabelValue> zzgr;

    public final class Builder {
        private Builder() {
        }

        @Deprecated
        public final Builder setHexFontColor(String str) {
            LabelValueRow.this.zzgp = str;
            return this;
        }

        @Deprecated
        public final Builder setHexBackgroundColor(String str) {
            LabelValueRow.this.zzgq = str;
            return this;
        }

        public final Builder addColumns(Collection<LabelValue> collection) {
            LabelValueRow.this.zzgr.addAll(collection);
            return this;
        }

        public final Builder addColumn(LabelValue labelValue) {
            LabelValueRow.this.zzgr.add(labelValue);
            return this;
        }

        public final LabelValueRow build() {
            return LabelValueRow.this;
        }

        /* synthetic */ Builder(LabelValueRow labelValueRow, zzd zzd) {
            this();
        }
    }

    public static Builder newBuilder() {
        return new Builder(new LabelValueRow(), null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzgp, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzgq, false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzgr, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    @Constructor
    LabelValueRow(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) ArrayList<LabelValue> arrayList) {
        this.zzgp = str;
        this.zzgq = str2;
        this.zzgr = arrayList;
    }

    LabelValueRow() {
        this.zzgr = ArrayUtils.newArrayList();
    }

    @Deprecated
    public final String getHexFontColor() {
        return this.zzgp;
    }

    @Deprecated
    public final String getHexBackgroundColor() {
        return this.zzgq;
    }

    public final ArrayList<LabelValue> getColumns() {
        return this.zzgr;
    }
}
