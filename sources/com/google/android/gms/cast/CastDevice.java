package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzdk;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Class(creator = "CastDeviceCreator")
@Reserved({1})
public class CastDevice extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final int CAPABILITY_AUDIO_IN = 8;
    public static final int CAPABILITY_AUDIO_OUT = 4;
    public static final int CAPABILITY_MULTIZONE_GROUP = 32;
    public static final int CAPABILITY_VIDEO_IN = 2;
    public static final int CAPABILITY_VIDEO_OUT = 1;
    public static final Creator<CastDevice> CREATOR = new zzn();
    @Field(defaultValue = "-1", getter = "getStatus", id = 10)
    private int status;
    @Field(getter = "getDeviceIdRaw", id = 2)
    private String zzan;
    @Field(id = 3)
    private String zzao;
    private InetAddress zzap;
    @Field(getter = "getFriendlyName", id = 4)
    private String zzaq;
    @Field(getter = "getModelName", id = 5)
    private String zzar;
    @Field(getter = "getDeviceVersion", id = 6)
    private String zzas;
    @Field(getter = "getServicePort", id = 7)
    private int zzat;
    @Field(getter = "getIcons", id = 8)
    private List<WebImage> zzau;
    @Field(getter = "getCapabilities", id = 9)
    private int zzav;
    @Field(getter = "getServiceInstanceName", id = 11)
    private String zzaw;
    @Field(getter = "getReceiverMetricsId", id = 12)
    private String zzax;
    @Field(getter = "getRcnEnabledStatus", id = 13)
    private int zzay;
    @Field(getter = "getHotspotBssid", id = 14)
    private String zzaz;
    @Field(getter = "getIpLowestTwoBytes", id = 15)
    private byte[] zzba;
    @Field(getter = "getCloudDeviceId", id = 16)
    private String zzbb;

    @Constructor
    CastDevice(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) String str3, @Param(id = 5) String str4, @Param(id = 6) String str5, @Param(id = 7) int i, @Param(id = 8) List<WebImage> list, @Param(id = 9) int i2, @Param(id = 10) int i3, @Param(id = 11) String str6, @Param(id = 12) String str7, @Param(id = 13) int i4, @Param(id = 14) String str8, @Param(id = 15) byte[] bArr, @Param(id = 16) String str9) {
        List list2;
        this.zzan = zza(str);
        this.zzao = zza(str2);
        if (!TextUtils.isEmpty(this.zzao)) {
            try {
                this.zzap = InetAddress.getByName(this.zzao);
            } catch (UnknownHostException e) {
                UnknownHostException unknownHostException = e;
                String str10 = this.zzao;
                String message = unknownHostException.getMessage();
                StringBuilder stringBuilder = new StringBuilder((48 + String.valueOf(str10).length()) + String.valueOf(message).length());
                stringBuilder.append("Unable to convert host address (");
                stringBuilder.append(str10);
                stringBuilder.append(") to ipaddress: ");
                stringBuilder.append(message);
                Log.i("CastDevice", stringBuilder.toString());
            }
        }
        this.zzaq = zza(str3);
        this.zzar = zza(str4);
        this.zzas = zza(str5);
        this.zzat = i;
        if (list != null) {
            list2 = list;
        } else {
            list2 = new ArrayList();
        }
        this.zzau = list2;
        this.zzav = i2;
        this.status = i3;
        this.zzaw = zza(str6);
        this.zzax = str7;
        this.zzay = i4;
        this.zzaz = str8;
        this.zzba = bArr;
        this.zzbb = str9;
    }

    private static String zza(String str) {
        return str == null ? "" : str;
    }

    public String getDeviceId() {
        if (this.zzan.startsWith("__cast_nearby__")) {
            return this.zzan.substring(16);
        }
        return this.zzan;
    }

    public boolean hasIPv4Address() {
        return getInetAddress() != null && (getInetAddress() instanceof Inet4Address);
    }

    public boolean hasIPv6Address() {
        return getInetAddress() != null && (getInetAddress() instanceof Inet6Address);
    }

    @Deprecated
    public Inet4Address getIpAddress() {
        return hasIPv4Address() ? (Inet4Address) this.zzap : null;
    }

    public InetAddress getInetAddress() {
        return this.zzap;
    }

    public String getFriendlyName() {
        return this.zzaq;
    }

    public String getModelName() {
        return this.zzar;
    }

    public String getDeviceVersion() {
        return this.zzas;
    }

    public int getServicePort() {
        return this.zzat;
    }

    public List<WebImage> getIcons() {
        return Collections.unmodifiableList(this.zzau);
    }

    public WebImage getIcon(int i, int i2) {
        WebImage webImage = null;
        if (this.zzau.isEmpty()) {
            return null;
        }
        if (i <= 0 || i2 <= 0) {
            return (WebImage) this.zzau.get(0);
        }
        WebImage webImage2 = null;
        for (WebImage webImage3 : this.zzau) {
            int width = webImage3.getWidth();
            int height = webImage3.getHeight();
            if (width < i || height < i2) {
                if (width < i && height < i2) {
                    if (webImage2 == null || (webImage2.getWidth() < width && webImage2.getHeight() < height)) {
                        webImage2 = webImage3;
                    }
                }
            } else if (webImage == null || (webImage.getWidth() > width && webImage.getHeight() > height)) {
                webImage = webImage3;
            }
        }
        if (webImage == null) {
            if (webImage2 != null) {
                webImage = webImage2;
            } else {
                webImage = (WebImage) this.zzau.get(0);
            }
        }
        return webImage;
    }

    @VisibleForTesting
    public boolean hasIcons() {
        return !this.zzau.isEmpty();
    }

    public boolean hasCapability(int i) {
        return (this.zzav & i) == i;
    }

    public boolean hasCapabilities(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        for (int hasCapability : iArr) {
            if (!hasCapability(hasCapability)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return String.format("\"%s\" (%s)", new Object[]{this.zzaq, this.zzan});
    }

    public void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzan, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzao, false);
        SafeParcelWriter.writeString(parcel, 4, getFriendlyName(), false);
        SafeParcelWriter.writeString(parcel, 5, getModelName(), false);
        SafeParcelWriter.writeString(parcel, 6, getDeviceVersion(), false);
        SafeParcelWriter.writeInt(parcel, 7, getServicePort());
        SafeParcelWriter.writeTypedList(parcel, 8, getIcons(), false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzav);
        SafeParcelWriter.writeInt(parcel, 10, this.status);
        SafeParcelWriter.writeString(parcel, 11, this.zzaw, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzax, false);
        SafeParcelWriter.writeInt(parcel, 13, this.zzay);
        SafeParcelWriter.writeString(parcel, 14, this.zzaz, false);
        SafeParcelWriter.writeByteArray(parcel, 15, this.zzba, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzbb, false);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CastDevice)) {
            return false;
        }
        CastDevice castDevice = (CastDevice) obj;
        return this.zzan == null ? castDevice.zzan == null : zzdk.zza(this.zzan, castDevice.zzan) && zzdk.zza(this.zzap, castDevice.zzap) && zzdk.zza(this.zzar, castDevice.zzar) && zzdk.zza(this.zzaq, castDevice.zzaq) && zzdk.zza(this.zzas, castDevice.zzas) && this.zzat == castDevice.zzat && zzdk.zza(this.zzau, castDevice.zzau) && this.zzav == castDevice.zzav && this.status == castDevice.status && zzdk.zza(this.zzaw, castDevice.zzaw) && zzdk.zza(Integer.valueOf(this.zzay), Integer.valueOf(castDevice.zzay)) && zzdk.zza(this.zzaz, castDevice.zzaz) && zzdk.zza(this.zzax, castDevice.zzax) && zzdk.zza(this.zzas, castDevice.getDeviceVersion()) && this.zzat == castDevice.getServicePort() && (((this.zzba == null && castDevice.zzba == null) || Arrays.equals(this.zzba, castDevice.zzba)) && zzdk.zza(this.zzbb, castDevice.zzbb));
    }

    @VisibleForTesting
    public boolean isSameDevice(CastDevice castDevice) {
        if (castDevice == null) {
            return false;
        }
        if (!TextUtils.isEmpty(getDeviceId()) && !getDeviceId().startsWith("__cast_ble__") && !TextUtils.isEmpty(castDevice.getDeviceId()) && !castDevice.getDeviceId().startsWith("__cast_ble__")) {
            return zzdk.zza(getDeviceId(), castDevice.getDeviceId());
        }
        if (TextUtils.isEmpty(this.zzaz) || TextUtils.isEmpty(castDevice.zzaz)) {
            return false;
        }
        return zzdk.zza(this.zzaz, castDevice.zzaz);
    }

    public int hashCode() {
        return this.zzan == null ? 0 : this.zzan.hashCode();
    }

    public void putInBundle(Bundle bundle) {
        if (bundle != null) {
            bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
        }
    }

    public static CastDevice getFromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(CastDevice.class.getClassLoader());
        return (CastDevice) bundle.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
    }

    public boolean isOnLocalNetwork() {
        return !this.zzan.startsWith("__cast_nearby__");
    }
}
