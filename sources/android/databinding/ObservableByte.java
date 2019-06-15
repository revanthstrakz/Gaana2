package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableByte extends b implements Parcelable, Serializable {
    public static final Creator<ObservableByte> CREATOR = new Creator<ObservableByte>() {
        /* renamed from: a */
        public ObservableByte createFromParcel(Parcel parcel) {
            return new ObservableByte(parcel.readByte());
        }

        /* renamed from: a */
        public ObservableByte[] newArray(int i) {
            return new ObservableByte[i];
        }
    };
    static final long serialVersionUID = 1;
    private byte a;

    public int describeContents() {
        return 0;
    }

    public ObservableByte(byte b) {
        this.a = b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.a);
    }
}
