package com.google.android.gms.ads;

import android.content.Context;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.ads.zzbat;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwu;
import com.google.api.client.http.HttpStatusCodes;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.moe.pushlibrary.utils.MoEHelperUtils;

@VisibleForTesting
public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER = new AdSize(ModuleDescriptor.MODULE_VERSION, 50, "320x50_mb");
    public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
    public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
    public static final int FULL_WIDTH = -1;
    public static final AdSize LARGE_BANNER = new AdSize(ModuleDescriptor.MODULE_VERSION, 100, "320x100_as");
    public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
    public static final AdSize MEDIUM_RECTANGLE = new AdSize(HttpStatusCodes.STATUS_CODE_MULTIPLE_CHOICES, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, "300x250_as");
    public static final AdSize SEARCH = new AdSize(-3, 0, "search_v2");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    public static final AdSize WIDE_SKYSCRAPER = new AdSize(MoEHelperUtils.BASELINE_SCREEN_DPI, 600, "160x600_as");
    public static final AdSize zzvs = new AdSize(50, 50, "50x50_mb");
    private final int zzvt;
    private final int zzvu;
    private final String zzvv;

    public AdSize(int i, int i2) {
        Object obj;
        Object valueOf = i == -1 ? "FULL" : String.valueOf(i);
        if (i2 == -2) {
            obj = "AUTO";
        } else {
            obj = String.valueOf(i2);
        }
        StringBuilder stringBuilder = new StringBuilder((4 + String.valueOf(valueOf).length()) + String.valueOf(obj).length());
        stringBuilder.append(valueOf);
        stringBuilder.append(AvidJSONUtil.KEY_X);
        stringBuilder.append(obj);
        stringBuilder.append("_as");
        this(i, i2, stringBuilder.toString());
    }

    AdSize(int i, int i2, String str) {
        StringBuilder stringBuilder;
        if (i < 0 && i != -1 && i != -3) {
            stringBuilder = new StringBuilder(37);
            stringBuilder.append("Invalid width for AdSize: ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i2 >= 0 || i2 == -2 || i2 == -4) {
            this.zzvt = i;
            this.zzvu = i2;
            this.zzvv = str;
        } else {
            stringBuilder = new StringBuilder(38);
            stringBuilder.append("Invalid height for AdSize: ");
            stringBuilder.append(i2);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.zzvt == adSize.zzvt && this.zzvu == adSize.zzvu && this.zzvv.equals(adSize.zzvv);
    }

    public final int getHeight() {
        return this.zzvu;
    }

    public final int getHeightInPixels(Context context) {
        switch (this.zzvu) {
            case -4:
            case -3:
                return -1;
            case -2:
                return zzwf.zzc(context.getResources().getDisplayMetrics());
            default:
                zzwu.zzpv();
                return zzbat.zza(context, this.zzvu);
        }
    }

    public final int getWidth() {
        return this.zzvt;
    }

    public final int getWidthInPixels(Context context) {
        int i = this.zzvt;
        if (i == -1) {
            return zzwf.zzb(context.getResources().getDisplayMetrics());
        }
        switch (i) {
            case -4:
            case -3:
                return -1;
            default:
                zzwu.zzpv();
                return zzbat.zza(context, this.zzvt);
        }
    }

    public final int hashCode() {
        return this.zzvv.hashCode();
    }

    public final boolean isAutoHeight() {
        return this.zzvu == -2;
    }

    public final boolean isFullWidth() {
        return this.zzvt == -1;
    }

    public final boolean isFluid() {
        return this.zzvt == -3 && this.zzvu == -4;
    }

    public final String toString() {
        return this.zzvv;
    }
}
