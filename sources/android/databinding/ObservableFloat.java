package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableFloat extends b implements Parcelable, Serializable {
    public static final Creator<ObservableFloat> CREATOR = new Creator<ObservableFloat>() {
        /* renamed from: a */
        public ObservableFloat createFromParcel(Parcel parcel) {
            return new ObservableFloat(parcel.readFloat());
        }

        /* renamed from: a */
        public ObservableFloat[] newArray(int i) {
            return new ObservableFloat[i];
        }
    };
    static final long serialVersionUID = 1;
    private float a;

    public int describeContents() {
        return 0;
    }

    public ObservableFloat(float f) {
        this.a = f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.a);
    }
}
