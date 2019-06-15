package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableShort extends b implements Parcelable, Serializable {
    public static final Creator<ObservableShort> CREATOR = new Creator<ObservableShort>() {
        /* renamed from: a */
        public ObservableShort createFromParcel(Parcel parcel) {
            return new ObservableShort((short) parcel.readInt());
        }

        /* renamed from: a */
        public ObservableShort[] newArray(int i) {
            return new ObservableShort[i];
        }
    };
    static final long serialVersionUID = 1;
    private short a;

    public int describeContents() {
        return 0;
    }

    public ObservableShort(short s) {
        this.a = s;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
    }
}
