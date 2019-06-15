package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableDouble extends b implements Parcelable, Serializable {
    public static final Creator<ObservableDouble> CREATOR = new Creator<ObservableDouble>() {
        /* renamed from: a */
        public ObservableDouble createFromParcel(Parcel parcel) {
            return new ObservableDouble(parcel.readDouble());
        }

        /* renamed from: a */
        public ObservableDouble[] newArray(int i) {
            return new ObservableDouble[i];
        }
    };
    static final long serialVersionUID = 1;
    private double a;

    public int describeContents() {
        return 0;
    }

    public ObservableDouble(double d) {
        this.a = d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.a);
    }
}
