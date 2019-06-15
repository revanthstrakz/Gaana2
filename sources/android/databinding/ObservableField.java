package android.databinding;

import android.support.annotation.Nullable;
import java.io.Serializable;

public class ObservableField<T> extends b implements Serializable {
    static final long serialVersionUID = 1;
    private T a;

    public ObservableField(T t) {
        this.a = t;
    }

    @Nullable
    public T a() {
        return this.a;
    }
}
