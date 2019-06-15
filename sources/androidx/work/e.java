package androidx.work;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.List;

public abstract class e {
    private static final String a = f.a("InputMerger");

    @NonNull
    public abstract d a(@NonNull List<d> list);

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static e a(String str) {
        try {
            return (e) Class.forName(str).newInstance();
        } catch (Exception e) {
            f a = f.a();
            String str2 = a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Trouble instantiating + ");
            stringBuilder.append(str);
            a.e(str2, stringBuilder.toString(), e);
            return null;
        }
    }
}
