package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableLong extends b implements Parcelable, Serializable {
    public static final Creator<ObservableLong> CREATOR = new Creator<ObservableLong>() {
        /* renamed from: a */
        public ObservableLong createFromParcel(Parcel parcel) {
            return new ObservableLong(parcel.readLong());
        }

        /* renamed from: a */
        public ObservableLong[] newArray(int i) {
            return new ObservableLong[i];
        }
    };
    static final long serialVersionUID = 1;
    private long a;

    public int describeContents() {
        return 0;
    }

    public ObservableLong(long j) {
        this.a = j;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.a);
    }
}
