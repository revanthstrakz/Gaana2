package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzdk;
import java.util.Locale;

@Class(creator = "LaunchOptionsCreator")
@Reserved({1})
public class LaunchOptions extends AbstractSafeParcelable {
    public static final Creator<LaunchOptions> CREATOR = new zzai();
    @Field(getter = "getRelaunchIfRunning", id = 2)
    private boolean zzdb;
    @Field(getter = "getLanguage", id = 3)
    private String zzdc;

    @VisibleForTesting
    public static final class Builder {
        private LaunchOptions zzdd = new LaunchOptions();

        public final Builder setRelaunchIfRunning(boolean z) {
            this.zzdd.setRelaunchIfRunning(z);
            return this;
        }

        public final Builder setLocale(Locale locale) {
            this.zzdd.setLanguage(zzdk.zza(locale));
            return this;
        }

        public final LaunchOptions build() {
            return this.zzdd;
        }
    }

    @Constructor
    LaunchOptions(@Param(id = 2) boolean z, @Param(id = 3) String str) {
        this.zzdb = z;
        this.zzdc = str;
    }

    public LaunchOptions() {
        this(false, zzdk.zza(Locale.getDefault()));
    }

    public void setRelaunchIfRunning(boolean z) {
        this.zzdb = z;
    }

    public boolean getRelaunchIfRunning() {
        return this.zzdb;
    }

    public void setLanguage(String str) {
        this.zzdc = str;
    }

    public String getLanguage() {
        return this.zzdc;
    }

    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s)", new Object[]{Boolean.valueOf(this.zzdb), this.zzdc});
    }

    public void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, getRelaunchIfRunning());
        SafeParcelWriter.writeString(parcel, 3, getLanguage(), false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LaunchOptions)) {
            return false;
        }
        LaunchOptions launchOptions = (LaunchOptions) obj;
        return this.zzdb == launchOptions.zzdb && zzdk.zza(this.zzdc, launchOptions.zzdc);
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zzdb), this.zzdc);
    }
}
