package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.zzc;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@zzark
@Class(creator = "AdSizeParcelCreator")
@Reserved({1})
public class zzwf extends AbstractSafeParcelable {
    public static final Creator<zzwf> CREATOR = new zzwg();
    @Field(id = 3)
    public final int height;
    @Field(id = 4)
    public final int heightPixels;
    @Field(id = 6)
    public final int width;
    @Field(id = 7)
    public final int widthPixels;
    @Field(id = 2)
    public final String zzckk;
    @Field(id = 5)
    public final boolean zzckl;
    @Field(id = 8)
    public final zzwf[] zzckm;
    @Field(id = 9)
    public final boolean zzckn;
    @Field(id = 10)
    public final boolean zzcko;
    @Field(id = 11)
    public boolean zzckp;

    public static int zzb(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static int zzc(DisplayMetrics displayMetrics) {
        return (int) (((float) zzd(displayMetrics)) * displayMetrics.density);
    }

    private static int zzd(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        return i <= 720 ? 50 : 90;
    }

    public static zzwf zzg(Context context) {
        return new zzwf("320x50_mb", 0, 0, false, 0, 0, null, true, false, false);
    }

    public static zzwf zzpo() {
        return new zzwf("reward_mb", 0, 0, true, 0, 0, null, false, false, false);
    }

    public zzwf() {
        this("interstitial_mb", 0, 0, true, 0, 0, null, false, false, false);
    }

    public zzwf(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0080  */
    public zzwf(android.content.Context r13, com.google.android.gms.ads.AdSize[] r14) {
        /*
        r12 = this;
        r12.<init>();
        r0 = 0;
        r1 = r14[r0];
        r12.zzckl = r0;
        r2 = r1.isFluid();
        r12.zzcko = r2;
        r2 = r12.zzcko;
        if (r2 == 0) goto L_0x0023;
    L_0x0012:
        r2 = com.google.android.gms.ads.AdSize.BANNER;
        r2 = r2.getWidth();
        r12.width = r2;
        r2 = com.google.android.gms.ads.AdSize.BANNER;
        r2 = r2.getHeight();
        r12.height = r2;
        goto L_0x002f;
    L_0x0023:
        r2 = r1.getWidth();
        r12.width = r2;
        r2 = r1.getHeight();
        r12.height = r2;
    L_0x002f:
        r2 = r12.width;
        r3 = -1;
        r4 = 1;
        if (r2 != r3) goto L_0x0037;
    L_0x0035:
        r2 = r4;
        goto L_0x0038;
    L_0x0037:
        r2 = r0;
    L_0x0038:
        r3 = r12.height;
        r5 = -2;
        if (r3 != r5) goto L_0x003f;
    L_0x003d:
        r3 = r4;
        goto L_0x0040;
    L_0x003f:
        r3 = r0;
    L_0x0040:
        r5 = r13.getResources();
        r5 = r5.getDisplayMetrics();
        if (r2 == 0) goto L_0x0083;
    L_0x004a:
        com.google.android.gms.internal.ads.zzwu.zzpv();
        r6 = com.google.android.gms.internal.ads.zzbat.zzbi(r13);
        if (r6 == 0) goto L_0x0069;
    L_0x0053:
        com.google.android.gms.internal.ads.zzwu.zzpv();
        r6 = com.google.android.gms.internal.ads.zzbat.zzbj(r13);
        if (r6 == 0) goto L_0x0069;
    L_0x005c:
        r6 = r5.widthPixels;
        com.google.android.gms.internal.ads.zzwu.zzpv();
        r7 = com.google.android.gms.internal.ads.zzbat.zzbk(r13);
        r6 = r6 - r7;
        r12.widthPixels = r6;
        goto L_0x006d;
    L_0x0069:
        r6 = r5.widthPixels;
        r12.widthPixels = r6;
    L_0x006d:
        r6 = r12.widthPixels;
        r6 = (float) r6;
        r7 = r5.density;
        r6 = r6 / r7;
        r6 = (double) r6;
        r8 = (int) r6;
        r9 = (double) r8;
        r6 = r6 - r9;
        r9 = 4576918229304087675; // 0x3f847ae147ae147b float:89128.96 double:0.01;
        r11 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1));
        if (r11 < 0) goto L_0x0090;
    L_0x0080:
        r8 = r8 + 1;
        goto L_0x0090;
    L_0x0083:
        r8 = r12.width;
        com.google.android.gms.internal.ads.zzwu.zzpv();
        r6 = r12.width;
        r6 = com.google.android.gms.internal.ads.zzbat.zza(r5, r6);
        r12.widthPixels = r6;
    L_0x0090:
        if (r3 == 0) goto L_0x0097;
    L_0x0092:
        r6 = zzd(r5);
        goto L_0x0099;
    L_0x0097:
        r6 = r12.height;
    L_0x0099:
        com.google.android.gms.internal.ads.zzwu.zzpv();
        r5 = com.google.android.gms.internal.ads.zzbat.zza(r5, r6);
        r12.heightPixels = r5;
        if (r2 != 0) goto L_0x00b7;
    L_0x00a4:
        if (r3 == 0) goto L_0x00a7;
    L_0x00a6:
        goto L_0x00b7;
    L_0x00a7:
        r2 = r12.zzcko;
        if (r2 == 0) goto L_0x00b0;
    L_0x00ab:
        r1 = "320x50_mb";
        r12.zzckk = r1;
        goto L_0x00d4;
    L_0x00b0:
        r1 = r1.toString();
        r12.zzckk = r1;
        goto L_0x00d4;
    L_0x00b7:
        r1 = 26;
        r2 = new java.lang.StringBuilder;
        r2.<init>(r1);
        r2.append(r8);
        r1 = "x";
        r2.append(r1);
        r2.append(r6);
        r1 = "_as";
        r2.append(r1);
        r1 = r2.toString();
        r12.zzckk = r1;
    L_0x00d4:
        r1 = r14.length;
        if (r1 <= r4) goto L_0x00ee;
    L_0x00d7:
        r1 = r14.length;
        r1 = new com.google.android.gms.internal.ads.zzwf[r1];
        r12.zzckm = r1;
        r1 = r0;
    L_0x00dd:
        r2 = r14.length;
        if (r1 >= r2) goto L_0x00f1;
    L_0x00e0:
        r2 = r12.zzckm;
        r3 = new com.google.android.gms.internal.ads.zzwf;
        r4 = r14[r1];
        r3.<init>(r13, r4);
        r2[r1] = r3;
        r1 = r1 + 1;
        goto L_0x00dd;
    L_0x00ee:
        r13 = 0;
        r12.zzckm = r13;
    L_0x00f1:
        r12.zzckn = r0;
        r12.zzckp = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzwf.<init>(android.content.Context, com.google.android.gms.ads.AdSize[]):void");
    }

    public zzwf(zzwf zzwf, zzwf[] zzwfArr) {
        this(zzwf.zzckk, zzwf.height, zzwf.heightPixels, zzwf.zzckl, zzwf.width, zzwf.widthPixels, zzwfArr, zzwf.zzckn, zzwf.zzcko, zzwf.zzckp);
    }

    @Constructor
    zzwf(@Param(id = 2) String str, @Param(id = 3) int i, @Param(id = 4) int i2, @Param(id = 5) boolean z, @Param(id = 6) int i3, @Param(id = 7) int i4, @Param(id = 8) zzwf[] zzwfArr, @Param(id = 9) boolean z2, @Param(id = 10) boolean z3, @Param(id = 11) boolean z4) {
        this.zzckk = str;
        this.height = i;
        this.heightPixels = i2;
        this.zzckl = z;
        this.width = i3;
        this.widthPixels = i4;
        this.zzckm = zzwfArr;
        this.zzckn = z2;
        this.zzcko = z3;
        this.zzckp = z4;
    }

    public final AdSize zzpp() {
        return zzc.zza(this.width, this.height, this.zzckk);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzckk, false);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 4, this.heightPixels);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzckl);
        SafeParcelWriter.writeInt(parcel, 6, this.width);
        SafeParcelWriter.writeInt(parcel, 7, this.widthPixels);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzckm, i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzckn);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzcko);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzckp);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
