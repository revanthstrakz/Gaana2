package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@zzark
@Class(creator = "AdRequestInfoParcelCreator")
public final class zzasi extends AbstractSafeParcelable {
    public static final Creator<zzasi> CREATOR = new zzask();
    @Field(id = 6)
    public final ApplicationInfo applicationInfo;
    @Field(id = 1)
    public final int versionCode;
    @Field(id = 28)
    public final String zzbsm;
    @Field(id = 5)
    public final String zzbsn;
    @Field(id = 11)
    public final zzbbi zzbsp;
    @Field(id = 4)
    public final zzwf zzbst;
    @Field(id = 29)
    public final zzacp zzbti;
    @Field(id = 63)
    public final zzafz zzbtk;
    @Nullable
    @Field(id = 46)
    public final zzyv zzbtl;
    @Field(id = 53)
    public final List<Integer> zzbtn;
    @Field(id = 14)
    public final List<String> zzbtt;
    @Field(id = 20)
    public final float zzbwv;
    @Field(id = 10)
    public final String zzclm;
    @Field(id = 42)
    public final boolean zzdlv;
    @Nullable
    @Field(id = 2)
    public final Bundle zzdwf;
    @Field(id = 3)
    public final zzwb zzdwg;
    @Nullable
    @Field(id = 7)
    public final PackageInfo zzdwh;
    @Field(id = 8)
    public final String zzdwi;
    @Field(id = 9)
    public final String zzdwj;
    @Field(id = 12)
    public final Bundle zzdwk;
    @Field(id = 13)
    public final int zzdwl;
    @Field(id = 15)
    public final Bundle zzdwm;
    @Field(id = 16)
    public final boolean zzdwn;
    @Field(id = 18)
    public final int zzdwo;
    @Field(id = 19)
    public final int zzdwp;
    @Field(id = 21)
    public final String zzdwq;
    @Field(id = 25)
    public final long zzdwr;
    @Field(id = 26)
    public final String zzdws;
    @Nullable
    @Field(id = 27)
    public final List<String> zzdwt;
    @Field(id = 30)
    public final List<String> zzdwu;
    @Field(id = 31)
    public final long zzdwv;
    @Field(id = 33)
    public final String zzdww;
    @Field(id = 34)
    public final float zzdwx;
    @Field(id = 35)
    public final int zzdwy;
    @Field(id = 36)
    public final int zzdwz;
    @Field(id = 37)
    public final boolean zzdxa;
    @Field(id = 38)
    public final boolean zzdxb;
    @Field(id = 39)
    public final String zzdxc;
    @Field(id = 40)
    public final boolean zzdxd;
    @Field(id = 41)
    public final String zzdxe;
    @Field(id = 43)
    public final int zzdxf;
    @Field(id = 44)
    public final Bundle zzdxg;
    @Field(id = 45)
    public final String zzdxh;
    @Field(id = 47)
    public final boolean zzdxi;
    @Field(id = 48)
    public final Bundle zzdxj;
    @Nullable
    @Field(id = 49)
    public final String zzdxk;
    @Nullable
    @Field(id = 50)
    public final String zzdxl;
    @Nullable
    @Field(id = 51)
    public final String zzdxm;
    @Field(id = 52)
    public final boolean zzdxn;
    @Field(id = 54)
    public final String zzdxo;
    @Field(id = 55)
    public final List<String> zzdxp;
    @Field(id = 56)
    public final int zzdxq;
    @Field(id = 57)
    public final boolean zzdxr;
    @Field(id = 58)
    public final boolean zzdxs;
    @Field(id = 59)
    public final boolean zzdxt;
    @Field(id = 60)
    public final ArrayList<String> zzdxu;
    @Field(id = 61)
    public final String zzdxv;
    @Nullable
    @Field(id = 64)
    public final String zzdxw;
    @Field(id = 65)
    public final Bundle zzdxx;

    @VisibleForTesting
    private zzasi(@Nullable Bundle bundle, zzwb zzwb, zzwf zzwf, String str, ApplicationInfo applicationInfo, @Nullable PackageInfo packageInfo, String str2, String str3, String str4, zzbbi zzbbi, Bundle bundle2, int i, List<String> list, List<String> list2, Bundle bundle3, boolean z, int i2, int i3, float f, String str5, long j, String str6, @Nullable List<String> list3, String str7, zzacp zzacp, long j2, String str8, float f2, boolean z2, int i4, int i5, boolean z3, boolean z4, String str9, String str10, boolean z5, int i6, Bundle bundle4, String str11, @Nullable zzyv zzyv, boolean z6, Bundle bundle5, @Nullable String str12, @Nullable String str13, @Nullable String str14, boolean z7, List<Integer> list4, String str15, List<String> list5, int i7, boolean z8, boolean z9, boolean z10, ArrayList<String> arrayList, String str16, zzafz zzafz, @Nullable String str17, Bundle bundle6) {
        this(24, bundle, zzwb, zzwf, str, applicationInfo, packageInfo, str2, str3, str4, zzbbi, bundle2, i, list, bundle3, z, i2, i3, f, str5, j, str6, list3, str7, zzacp, list2, j2, str8, f2, z2, i4, i5, z3, z4, str9, str10, z5, i6, bundle4, str11, zzyv, z6, bundle5, str12, str13, str14, z7, list4, str15, list5, i7, z8, z9, z10, arrayList, str16, zzafz, str17, bundle6);
    }

    public zzasi(zzasj zzasj, long j, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        zzasj zzasj2 = zzasj;
        Bundle bundle = zzasj2.zzdwf;
        zzwb zzwb = zzasj2.zzdwg;
        zzwf zzwf = zzasj2.zzbst;
        String str5 = zzasj2.zzbsn;
        ApplicationInfo applicationInfo = zzasj2.applicationInfo;
        PackageInfo packageInfo = zzasj2.zzdwh;
        String str6 = (String) zzbbq.zza(zzasj2.zzdxz, (Object) "");
        String str7 = zzasj2.zzdwj;
        String str8 = zzasj2.zzclm;
        zzbbi zzbbi = zzasj2.zzbsp;
        Bundle bundle2 = zzasj2.zzdwk;
        int i = zzasj2.zzdwl;
        List list = zzasj2.zzbtt;
        List list2 = zzasj2.zzdwu;
        Bundle bundle3 = zzasj2.zzdwm;
        boolean z = zzasj2.zzdwn;
        int i2 = zzasj2.zzdwo;
        int i3 = zzasj2.zzdwp;
        float f = zzasj2.zzbwv;
        List list3 = list2;
        String str9 = zzasj2.zzdwq;
        long j2 = zzasj2.zzdwr;
        String str10 = zzasj2.zzdws;
        list2 = zzasj2.zzdwt;
        String str11 = str10;
        String str12 = zzasj2.zzbsm;
        zzacp zzacp = zzasj2.zzbti;
        String str13 = zzasj2.zzdww;
        float f2 = zzasj2.zzdwx;
        boolean z2 = zzasj2.zzdxd;
        int i4 = zzasj2.zzdwy;
        int i5 = zzasj2.zzdwz;
        boolean z3 = zzasj2.zzdxa;
        List list4 = list2;
        boolean z4 = zzasj2.zzdxb;
        List list5 = list;
        Bundle bundle4 = bundle2;
        int i6 = i;
        String str14 = (String) zzbbq.zza(zzasj2.zzdxy, (Object) "", 1, TimeUnit.SECONDS);
        str10 = zzasj2.zzdxe;
        boolean z5 = zzasj2.zzdlv;
        int i7 = zzasj2.zzdxf;
        Bundle bundle5 = zzasj2.zzdxg;
        String str15 = zzasj2.zzdxh;
        String str16 = str10;
        zzyv zzyv = zzasj2.zzbtl;
        boolean z6 = zzasj2.zzdxi;
        Bundle bundle6 = zzasj2.zzdxj;
        boolean z7 = zzasj2.zzdxn;
        List list6 = zzasj2.zzbtn;
        String str17 = zzasj2.zzdxo;
        List list7 = zzasj2.zzdxp;
        int i8 = zzasj2.zzdxq;
        boolean z8 = zzasj2.zzdxr;
        boolean z9 = zzasj2.zzdxs;
        boolean z10 = zzasj2.zzdxt;
        ArrayList arrayList = zzasj2.zzdxu;
        String str18 = zzasj2.zzdxv;
        zzafz zzafz = zzasj2.zzbtk;
        Bundle bundle7 = zzasj2.zzdxx;
        Bundle bundle8 = bundle5;
        int i9 = i7;
        boolean z11 = z5;
        list2 = list3;
        zzafz zzafz2 = zzafz;
        boolean z12 = z;
        int i10 = i2;
        int i11 = i3;
        float f3 = f;
        String str19 = str9;
        String str20 = str11;
        Bundle bundle9 = bundle3;
        long j3 = j;
        this(bundle, zzwb, zzwf, str5, applicationInfo, packageInfo, str6, str7, str8, zzbbi, bundle4, i6, list5, list2, bundle9, z12, i10, i11, f3, str19, j2, str20, list4, str12, zzacp, j3, str13, f2, z2, i4, i5, z3, z4, str14, str16, z11, i9, bundle8, str15, zzyv, z6, bundle6, str, str2, str3, z7, list6, str17, list7, i8, z8, z9, z10, arrayList, str18, zzafz2, str4, bundle7);
    }

    @Constructor
    zzasi(@Param(id = 1) int i, @Param(id = 2) Bundle bundle, @Param(id = 3) zzwb zzwb, @Param(id = 4) zzwf zzwf, @Param(id = 5) String str, @Param(id = 6) ApplicationInfo applicationInfo, @Param(id = 7) PackageInfo packageInfo, @Param(id = 8) String str2, @Param(id = 9) String str3, @Param(id = 10) String str4, @Param(id = 11) zzbbi zzbbi, @Param(id = 12) Bundle bundle2, @Param(id = 13) int i2, @Param(id = 14) List<String> list, @Param(id = 15) Bundle bundle3, @Param(id = 16) boolean z, @Param(id = 18) int i3, @Param(id = 19) int i4, @Param(id = 20) float f, @Param(id = 21) String str5, @Param(id = 25) long j, @Param(id = 26) String str6, @Param(id = 27) List<String> list2, @Param(id = 28) String str7, @Param(id = 29) zzacp zzacp, @Param(id = 30) List<String> list3, @Param(id = 31) long j2, @Param(id = 33) String str8, @Param(id = 34) float f2, @Param(id = 40) boolean z2, @Param(id = 35) int i5, @Param(id = 36) int i6, @Param(id = 37) boolean z3, @Param(id = 38) boolean z4, @Param(id = 39) String str9, @Param(id = 41) String str10, @Param(id = 42) boolean z5, @Param(id = 43) int i7, @Param(id = 44) Bundle bundle4, @Param(id = 45) String str11, @Param(id = 46) zzyv zzyv, @Param(id = 47) boolean z6, @Param(id = 48) Bundle bundle5, @Nullable @Param(id = 49) String str12, @Nullable @Param(id = 50) String str13, @Nullable @Param(id = 51) String str14, @Param(id = 52) boolean z7, @Param(id = 53) List<Integer> list4, @Param(id = 54) String str15, @Param(id = 55) List<String> list5, @Param(id = 56) int i8, @Param(id = 57) boolean z8, @Param(id = 58) boolean z9, @Param(id = 59) boolean z10, @Param(id = 60) ArrayList<String> arrayList, @Param(id = 61) String str16, @Param(id = 63) zzafz zzafz, @Nullable @Param(id = 64) String str17, @Param(id = 65) Bundle bundle6) {
        List emptyList;
        this.versionCode = i;
        this.zzdwf = bundle;
        this.zzdwg = zzwb;
        this.zzbst = zzwf;
        this.zzbsn = str;
        this.applicationInfo = applicationInfo;
        this.zzdwh = packageInfo;
        this.zzdwi = str2;
        this.zzdwj = str3;
        this.zzclm = str4;
        this.zzbsp = zzbbi;
        this.zzdwk = bundle2;
        this.zzdwl = i2;
        this.zzbtt = list;
        if (list3 == null) {
            emptyList = Collections.emptyList();
        } else {
            emptyList = Collections.unmodifiableList(list3);
        }
        this.zzdwu = emptyList;
        this.zzdwm = bundle3;
        this.zzdwn = z;
        this.zzdwo = i3;
        this.zzdwp = i4;
        this.zzbwv = f;
        this.zzdwq = str5;
        this.zzdwr = j;
        this.zzdws = str6;
        if (list2 == null) {
            emptyList = Collections.emptyList();
        } else {
            emptyList = Collections.unmodifiableList(list2);
        }
        this.zzdwt = emptyList;
        this.zzbsm = str7;
        this.zzbti = zzacp;
        this.zzdwv = j2;
        this.zzdww = str8;
        this.zzdwx = f2;
        this.zzdxd = z2;
        this.zzdwy = i5;
        this.zzdwz = i6;
        this.zzdxa = z3;
        this.zzdxb = z4;
        this.zzdxc = str9;
        this.zzdxe = str10;
        this.zzdlv = z5;
        this.zzdxf = i7;
        this.zzdxg = bundle4;
        this.zzdxh = str11;
        this.zzbtl = zzyv;
        this.zzdxi = z6;
        this.zzdxj = bundle5;
        this.zzdxk = str12;
        this.zzdxl = str13;
        this.zzdxm = str14;
        this.zzdxn = z7;
        this.zzbtn = list4;
        this.zzdxo = str15;
        this.zzdxp = list5;
        this.zzdxq = i8;
        this.zzdxr = z8;
        this.zzdxs = z9;
        this.zzdxt = z10;
        this.zzdxu = arrayList;
        this.zzdxv = str16;
        this.zzbtk = zzafz;
        this.zzdxw = str17;
        this.zzdxx = bundle6;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzdwf, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdwg, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbst, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbsn, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.applicationInfo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzdwh, i, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzdwi, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzdwj, false);
        SafeParcelWriter.writeString(parcel, 10, this.zzclm, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzbsp, i, false);
        SafeParcelWriter.writeBundle(parcel, 12, this.zzdwk, false);
        SafeParcelWriter.writeInt(parcel, 13, this.zzdwl);
        SafeParcelWriter.writeStringList(parcel, 14, this.zzbtt, false);
        SafeParcelWriter.writeBundle(parcel, 15, this.zzdwm, false);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzdwn);
        SafeParcelWriter.writeInt(parcel, 18, this.zzdwo);
        SafeParcelWriter.writeInt(parcel, 19, this.zzdwp);
        SafeParcelWriter.writeFloat(parcel, 20, this.zzbwv);
        SafeParcelWriter.writeString(parcel, 21, this.zzdwq, false);
        SafeParcelWriter.writeLong(parcel, 25, this.zzdwr);
        SafeParcelWriter.writeString(parcel, 26, this.zzdws, false);
        SafeParcelWriter.writeStringList(parcel, 27, this.zzdwt, false);
        SafeParcelWriter.writeString(parcel, 28, this.zzbsm, false);
        SafeParcelWriter.writeParcelable(parcel, 29, this.zzbti, i, false);
        SafeParcelWriter.writeStringList(parcel, 30, this.zzdwu, false);
        SafeParcelWriter.writeLong(parcel, 31, this.zzdwv);
        SafeParcelWriter.writeString(parcel, 33, this.zzdww, false);
        SafeParcelWriter.writeFloat(parcel, 34, this.zzdwx);
        SafeParcelWriter.writeInt(parcel, 35, this.zzdwy);
        SafeParcelWriter.writeInt(parcel, 36, this.zzdwz);
        SafeParcelWriter.writeBoolean(parcel, 37, this.zzdxa);
        SafeParcelWriter.writeBoolean(parcel, 38, this.zzdxb);
        SafeParcelWriter.writeString(parcel, 39, this.zzdxc, false);
        SafeParcelWriter.writeBoolean(parcel, 40, this.zzdxd);
        SafeParcelWriter.writeString(parcel, 41, this.zzdxe, false);
        SafeParcelWriter.writeBoolean(parcel, 42, this.zzdlv);
        SafeParcelWriter.writeInt(parcel, 43, this.zzdxf);
        SafeParcelWriter.writeBundle(parcel, 44, this.zzdxg, false);
        SafeParcelWriter.writeString(parcel, 45, this.zzdxh, false);
        SafeParcelWriter.writeParcelable(parcel, 46, this.zzbtl, i, false);
        SafeParcelWriter.writeBoolean(parcel, 47, this.zzdxi);
        SafeParcelWriter.writeBundle(parcel, 48, this.zzdxj, false);
        SafeParcelWriter.writeString(parcel, 49, this.zzdxk, false);
        SafeParcelWriter.writeString(parcel, 50, this.zzdxl, false);
        SafeParcelWriter.writeString(parcel, 51, this.zzdxm, false);
        SafeParcelWriter.writeBoolean(parcel, 52, this.zzdxn);
        SafeParcelWriter.writeIntegerList(parcel, 53, this.zzbtn, false);
        SafeParcelWriter.writeString(parcel, 54, this.zzdxo, false);
        SafeParcelWriter.writeStringList(parcel, 55, this.zzdxp, false);
        SafeParcelWriter.writeInt(parcel, 56, this.zzdxq);
        SafeParcelWriter.writeBoolean(parcel, 57, this.zzdxr);
        SafeParcelWriter.writeBoolean(parcel, 58, this.zzdxs);
        SafeParcelWriter.writeBoolean(parcel, 59, this.zzdxt);
        SafeParcelWriter.writeStringList(parcel, 60, this.zzdxu, false);
        SafeParcelWriter.writeString(parcel, 61, this.zzdxv, false);
        SafeParcelWriter.writeParcelable(parcel, 63, this.zzbtk, i, false);
        SafeParcelWriter.writeString(parcel, 64, this.zzdxw, false);
        SafeParcelWriter.writeBundle(parcel, 65, this.zzdxx, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
