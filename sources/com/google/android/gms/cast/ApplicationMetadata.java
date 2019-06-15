package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.internal.cast.zzdk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Class(creator = "ApplicationMetadataCreator")
@Reserved({1})
public class ApplicationMetadata extends AbstractSafeParcelable {
    public static final Creator<ApplicationMetadata> CREATOR = new zzd();
    @Field(getter = "getName", id = 3)
    private String name;
    @Field(getter = "getSenderAppIdentifier", id = 6)
    private String zzaa;
    @Field(getter = "getSenderAppLaunchUrl", id = 7)
    private Uri zzab;
    @Field(getter = "getIconUrl", id = 8)
    private String zzac;
    @Field(getter = "getApplicationId", id = 2)
    private String zzy;
    @Field(getter = "getSupportedNamespaces", id = 5)
    private List<String> zzz;

    @Constructor
    ApplicationMetadata(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) List<WebImage> list, @Param(id = 5) List<String> list2, @Param(id = 6) String str3, @Param(id = 7) Uri uri, @Param(id = 8) String str4) {
        this.zzy = str;
        this.name = str2;
        this.zzz = list2;
        this.zzaa = str3;
        this.zzab = uri;
        this.zzac = str4;
    }

    public List<WebImage> getImages() {
        return null;
    }

    private ApplicationMetadata() {
        this.zzz = new ArrayList();
    }

    public String getApplicationId() {
        return this.zzy;
    }

    public String getName() {
        return this.name;
    }

    public boolean isNamespaceSupported(String str) {
        return this.zzz != null && this.zzz.contains(str);
    }

    public List<String> getSupportedNamespaces() {
        return Collections.unmodifiableList(this.zzz);
    }

    public boolean areNamespacesSupported(List<String> list) {
        return this.zzz != null && this.zzz.containsAll(list);
    }

    public String getSenderAppIdentifier() {
        return this.zzaa;
    }

    public String toString() {
        String str = this.zzy;
        String str2 = this.name;
        int size = this.zzz == null ? 0 : this.zzz.size();
        String str3 = this.zzaa;
        String valueOf = String.valueOf(this.zzab);
        String str4 = this.zzac;
        StringBuilder stringBuilder = new StringBuilder(((((110 + String.valueOf(str).length()) + String.valueOf(str2).length()) + String.valueOf(str3).length()) + String.valueOf(valueOf).length()) + String.valueOf(str4).length());
        stringBuilder.append("applicationId: ");
        stringBuilder.append(str);
        stringBuilder.append(", name: ");
        stringBuilder.append(str2);
        stringBuilder.append(", namespaces.count: ");
        stringBuilder.append(size);
        stringBuilder.append(", senderAppIdentifier: ");
        stringBuilder.append(str3);
        stringBuilder.append(", senderAppLaunchUrl: ");
        stringBuilder.append(valueOf);
        stringBuilder.append(", iconUrl: ");
        stringBuilder.append(str4);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getApplicationId(), false);
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getImages(), false);
        SafeParcelWriter.writeStringList(parcel, 5, getSupportedNamespaces(), false);
        SafeParcelWriter.writeString(parcel, 6, getSenderAppIdentifier(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzab, i, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzac, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzy, this.name, this.zzz, this.zzaa, this.zzab, this.zzac);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApplicationMetadata)) {
            return false;
        }
        ApplicationMetadata applicationMetadata = (ApplicationMetadata) obj;
        return zzdk.zza(this.zzy, applicationMetadata.zzy) && zzdk.zza(this.name, applicationMetadata.name) && zzdk.zza(this.zzz, applicationMetadata.zzz) && zzdk.zza(this.zzaa, applicationMetadata.zzaa) && zzdk.zza(this.zzab, applicationMetadata.zzab) && zzdk.zza(this.zzac, applicationMetadata.zzac);
    }
}
