package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
import androidx.work.f;

public class d {
    private static final String a = f.a("PackageManagerHelper");

    public static void a(@NonNull Context context, @NonNull Class cls, boolean z) {
        String str;
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, cls.getName()), z ? 1 : 2, 1);
            f a = f.a();
            String str2 = a;
            str = "%s %s";
            Object[] objArr = new Object[2];
            objArr[0] = cls.getName();
            objArr[1] = z ? "enabled" : "disabled";
            a.b(str2, String.format(str, objArr), new Throwable[0]);
        } catch (Exception e) {
            f a2 = f.a();
            str = a;
            String str3 = "%s could not be %s";
            Object[] objArr2 = new Object[2];
            objArr2[0] = cls.getName();
            objArr2[1] = z ? "enabled" : "disabled";
            a2.b(str, String.format(str3, objArr2), e);
        }
    }
}
