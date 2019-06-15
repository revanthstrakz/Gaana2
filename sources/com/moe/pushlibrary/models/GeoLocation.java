package com.moe.pushlibrary.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GeoLocation implements Parcelable {
    public static final Creator<GeoLocation> CREATOR = new Creator<GeoLocation>() {
        public GeoLocation createFromParcel(Parcel parcel) {
            return new GeoLocation(parcel);
        }

        public GeoLocation[] newArray(int i) {
            return new GeoLocation[i];
        }
    };
    public double latitude;
    public double longitude;

    public int describeContents() {
        return 0;
    }

    public GeoLocation(double d, double d2) {
        this.latitude = d;
        this.longitude = d2;
    }

    public GeoLocation(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof GeoLocation)) {
            GeoLocation geoLocation = (GeoLocation) obj;
            if (geoLocation.longitude == this.longitude && geoLocation.latitude == this.latitude) {
                return true;
            }
        }
        return false;
    }
}
