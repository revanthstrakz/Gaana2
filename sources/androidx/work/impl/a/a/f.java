package androidx.work.impl.a.a;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import androidx.work.NetworkType;
import androidx.work.impl.a.b;
import androidx.work.impl.a.b.g;
import androidx.work.impl.b.j;

public class f extends c<b> {
    private static final String a = androidx.work.f.a("NetworkNotRoamingCtrlr");

    public f(Context context) {
        super(g.a(context).c());
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(@NonNull j jVar) {
        return jVar.j.a() == NetworkType.NOT_ROAMING;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public boolean b(@NonNull b bVar) {
        boolean z = true;
        if (VERSION.SDK_INT < 24) {
            androidx.work.f.a().b(a, "Not-roaming network constraint is not supported before API 24, only checking for connected state.", new Throwable[0]);
            return bVar.a() ^ 1;
        }
        if (bVar.a() && bVar.d()) {
            z = false;
        }
        return z;
    }
}
