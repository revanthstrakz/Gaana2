package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.IOUtils;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;

@zzark
@Class(creator = "LargeParcelTeleporterCreator")
@Reserved({1})
public final class zzasy extends AbstractSafeParcelable {
    public static final Creator<zzasy> CREATOR = new zzata();
    @Field(id = 2)
    private ParcelFileDescriptor zzdzi;
    private Parcelable zzdzj;
    private boolean zzdzk;

    @Constructor
    public zzasy(@Param(id = 2) ParcelFileDescriptor parcelFileDescriptor) {
        this.zzdzi = parcelFileDescriptor;
        this.zzdzj = null;
        this.zzdzk = true;
    }

    public zzasy(SafeParcelable safeParcelable) {
        this.zzdzi = null;
        this.zzdzj = safeParcelable;
        this.zzdzk = false;
    }

    public final <T extends SafeParcelable> T zza(Creator<T> creator) {
        if (this.zzdzk) {
            if (this.zzdzi == null) {
                zzbbd.e("File descriptor is empty, returning null.");
                return null;
            }
            byte[] autoCloseInputStream = new AutoCloseInputStream(this.zzdzi);
            Closeable dataInputStream = new DataInputStream(autoCloseInputStream);
            Creator creator2;
            try {
                autoCloseInputStream = new byte[dataInputStream.readInt()];
                boolean z = false;
                dataInputStream.readFully(autoCloseInputStream, z, autoCloseInputStream.length);
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.unmarshall(autoCloseInputStream, z, autoCloseInputStream.length);
                    obtain.setDataPosition(z);
                    this.zzdzj = (SafeParcelable) creator2.createFromParcel(obtain);
                    this.zzdzk = z;
                } finally {
                    obtain.recycle();
                }
            } catch (IOException e) {
                creator2 = e;
                autoCloseInputStream = "Could not read from parcel file descriptor";
                zzbbd.zzb(autoCloseInputStream, creator2);
                return null;
            } finally {
                IOUtils.closeQuietly(dataInputStream);
            }
        }
        return (SafeParcelable) this.zzdzj;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        zzwl();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdzi, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    private final ParcelFileDescriptor zzwl() {
        if (this.zzdzi == null) {
            Parcel obtain = Parcel.obtain();
            try {
                this.zzdzj.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                this.zzdzi = zzh(marshall);
            } finally {
                obtain.recycle();
            }
        }
        return this.zzdzi;
    }

    private final <T> ParcelFileDescriptor zzh(byte[] bArr) {
        Throwable e;
        Closeable autoCloseOutputStream;
        try {
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            autoCloseOutputStream = new AutoCloseOutputStream(createPipe[1]);
            try {
                new Thread(new zzasz(this, autoCloseOutputStream, bArr)).start();
                return createPipe[0];
            } catch (IOException e2) {
                e = e2;
                zzbbd.zzb("Error transporting the ad response", e);
                zzbv.zzlj().zza(e, "LargeParcelTeleporter.pipeData.2");
                IOUtils.closeQuietly(autoCloseOutputStream);
                return null;
            }
        } catch (IOException e3) {
            e = e3;
            autoCloseOutputStream = null;
            zzbbd.zzb("Error transporting the ad response", e);
            zzbv.zzlj().zza(e, "LargeParcelTeleporter.pipeData.2");
            IOUtils.closeQuietly(autoCloseOutputStream);
            return null;
        }
    }
}
