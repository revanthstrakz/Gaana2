package androidx.work;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

public abstract class m {
    private static final String a = f.a("WorkerFactory");

    @Nullable
    public abstract ListenableWorker a(@NonNull Context context, @NonNull String str, @NonNull WorkerParameters workerParameters);

    @Nullable
    @RestrictTo({Scope.LIBRARY_GROUP})
    public final ListenableWorker b(@NonNull Context context, @NonNull String str, @NonNull WorkerParameters workerParameters) {
        ListenableWorker a = a(context, str, workerParameters);
        if (a != null) {
            return a;
        }
        try {
            try {
                return (ListenableWorker) Class.forName(str).asSubclass(ListenableWorker.class).getDeclaredConstructor(new Class[]{Context.class, WorkerParameters.class}).newInstance(new Object[]{context, workerParameters});
            } catch (Exception e) {
                f a2 = f.a();
                String str2 = a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Could not instantiate ");
                stringBuilder.append(str);
                a2.e(str2, stringBuilder.toString(), e);
                return null;
            }
        } catch (ClassNotFoundException unused) {
            f a3 = f.a();
            String str3 = a;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Class not found: ");
            stringBuilder2.append(str);
            a3.e(str3, stringBuilder2.toString(), new Throwable[0]);
            return null;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static m a() {
        return new m() {
            @Nullable
            public ListenableWorker a(@NonNull Context context, @NonNull String str, @NonNull WorkerParameters workerParameters) {
                return null;
            }
        };
    }
}
