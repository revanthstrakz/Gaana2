package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.gaana.cardoption.AssetsHelper.CARD;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Comparator;

@Class(creator = "DetectedActivityCreator")
@Reserved({1000})
public class DetectedActivity extends AbstractSafeParcelable {
    public static final Creator<DetectedActivity> CREATOR = new zzi();
    public static final int IN_VEHICLE = 0;
    public static final int ON_BICYCLE = 1;
    public static final int ON_FOOT = 2;
    public static final int RUNNING = 8;
    public static final int STILL = 3;
    public static final int TILTING = 5;
    public static final int UNKNOWN = 4;
    public static final int WALKING = 7;
    private static final Comparator<DetectedActivity> zzo = new zzh();
    private static final int[] zzp = new int[]{9, 10};
    private static final int[] zzq = new int[]{0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 16, 17, 18, 19};
    private static final int[] zzr = new int[]{0, 1, 2, 3, 7, 8, 16, 17};
    @Field(id = 1)
    private int zzi;
    @Field(id = 2)
    private int zzs;

    @Constructor
    public DetectedActivity(@Param(id = 1) int i, @Param(id = 2) int i2) {
        this.zzi = i;
        this.zzs = i2;
    }

    public static void zzb(int i) {
        int[] iArr = zzr;
        int i2 = 0;
        int length = iArr.length;
        int i3 = 0;
        while (i2 < length) {
            if (iArr[i2] == i) {
                i3 = 1;
            }
            i2++;
        }
        if (i3 == 0) {
            StringBuilder stringBuilder = new StringBuilder(81);
            stringBuilder.append(i);
            stringBuilder.append(" is not a valid DetectedActivity supported by Activity Transition API.");
            Log.w("DetectedActivity", stringBuilder.toString());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DetectedActivity detectedActivity = (DetectedActivity) obj;
        return this.zzi == detectedActivity.zzi && this.zzs == detectedActivity.zzs;
    }

    public int getConfidence() {
        return this.zzs;
    }

    public int getType() {
        int i = this.zzi;
        return (i > 19 || i < 0) ? 4 : i;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzi), Integer.valueOf(this.zzs));
    }

    public String toString() {
        Object obj;
        int type = getType();
        switch (type) {
            case 0:
                obj = "IN_VEHICLE";
                break;
            case 1:
                obj = "ON_BICYCLE";
                break;
            case 2:
                obj = "ON_FOOT";
                break;
            case 3:
                obj = "STILL";
                break;
            case 4:
                obj = CARD.UNKNOWN;
                break;
            case 5:
                obj = "TILTING";
                break;
            default:
                switch (type) {
                    case 7:
                        obj = "WALKING";
                        break;
                    case 8:
                        obj = "RUNNING";
                        break;
                    default:
                        switch (type) {
                            case 16:
                                obj = "IN_ROAD_VEHICLE";
                                break;
                            case 17:
                                obj = "IN_RAIL_VEHICLE";
                                break;
                            case 18:
                                obj = "IN_TWO_WHEELER_VEHICLE";
                                break;
                            case 19:
                                obj = "IN_FOUR_WHEELER_VEHICLE";
                                break;
                            default:
                                obj = Integer.toString(type);
                                break;
                        }
                }
        }
        int i = this.zzs;
        StringBuilder stringBuilder = new StringBuilder(48 + String.valueOf(obj).length());
        stringBuilder.append("DetectedActivity [type=");
        stringBuilder.append(obj);
        stringBuilder.append(", confidence=");
        stringBuilder.append(i);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        i = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzi);
        SafeParcelWriter.writeInt(parcel, 2, this.zzs);
        SafeParcelWriter.finishObjectHeader(parcel, i);
    }
}
