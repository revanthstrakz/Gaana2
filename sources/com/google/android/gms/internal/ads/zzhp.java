package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public final class zzhp implements Parcelable, Comparator<zza> {
    public static final Creator<zzhp> CREATOR = new zzhq();
    private int zzaac;
    private final zza[] zzagq;
    public final int zzagr;

    public static final class zza implements Parcelable {
        public static final Creator<zza> CREATOR = new zzhr();
        private final byte[] data;
        private final String mimeType;
        private final UUID uuid;
        private int zzaac;
        public final boolean zzags;

        public zza(UUID uuid, String str, byte[] bArr) {
            this(uuid, str, bArr, false);
        }

        public final int describeContents() {
            return 0;
        }

        public zza(UUID uuid, String str, byte[] bArr, boolean z) {
            this.uuid = (UUID) zzpo.checkNotNull(uuid);
            this.mimeType = (String) zzpo.checkNotNull(str);
            this.data = (byte[]) zzpo.checkNotNull(bArr);
            this.zzags = z;
        }

        zza(Parcel parcel) {
            this.uuid = new UUID(parcel.readLong(), parcel.readLong());
            this.mimeType = parcel.readString();
            this.data = parcel.createByteArray();
            this.zzags = parcel.readByte() != (byte) 0;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            zza zza = (zza) obj;
            return this.mimeType.equals(zza.mimeType) && zzqe.zza(this.uuid, zza.uuid) && Arrays.equals(this.data, zza.data);
        }

        public final int hashCode() {
            if (this.zzaac == 0) {
                this.zzaac = (((this.uuid.hashCode() * 31) + this.mimeType.hashCode()) * 31) + Arrays.hashCode(this.data);
            }
            return this.zzaac;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.uuid.getMostSignificantBits());
            parcel.writeLong(this.uuid.getLeastSignificantBits());
            parcel.writeString(this.mimeType);
            parcel.writeByteArray(this.data);
            parcel.writeByte((byte) this.zzags);
        }
    }

    public zzhp(List<zza> list) {
        this(false, (zza[]) list.toArray(new zza[list.size()]));
    }

    public final int describeContents() {
        return 0;
    }

    public zzhp(zza... zzaArr) {
        this(true, zzaArr);
    }

    private zzhp(boolean z, zza... zzaArr) {
        Object[] zzaArr2;
        if (z) {
            zzaArr2 = (zza[]) zzaArr2.clone();
        }
        Arrays.sort(zzaArr2, this);
        for (int i = 1; i < zzaArr2.length; i++) {
            if (zzaArr2[i - 1].uuid.equals(zzaArr2[i].uuid)) {
                String valueOf = String.valueOf(zzaArr2[i].uuid);
                StringBuilder stringBuilder = new StringBuilder(25 + String.valueOf(valueOf).length());
                stringBuilder.append("Duplicate data for uuid: ");
                stringBuilder.append(valueOf);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
        }
        this.zzagq = zzaArr2;
        this.zzagr = zzaArr2.length;
    }

    zzhp(Parcel parcel) {
        this.zzagq = (zza[]) parcel.createTypedArray(zza.CREATOR);
        this.zzagr = this.zzagq.length;
    }

    public final zza zzu(int i) {
        return this.zzagq[i];
    }

    public final int hashCode() {
        if (this.zzaac == 0) {
            this.zzaac = Arrays.hashCode(this.zzagq);
        }
        return this.zzaac;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj == null || getClass() != obj.getClass()) ? false : Arrays.equals(this.zzagq, ((zzhp) obj).zzagq);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.zzagq, 0);
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zza zza = (zza) obj;
        zza zza2 = (zza) obj2;
        if (zzfe.zzwm.equals(zza.uuid)) {
            return zzfe.zzwm.equals(zza2.uuid) ? 0 : 1;
        } else {
            return zza.uuid.compareTo(zza2.uuid);
        }
    }
}
