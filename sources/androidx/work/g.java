package androidx.work;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class g extends l {

    public static final class a extends androidx.work.l.a<a, g> {
        /* Access modifiers changed, original: 0000 */
        @NonNull
        /* renamed from: b */
        public a c() {
            return this;
        }

        public a(@NonNull Class<? extends ListenableWorker> cls) {
            super(cls);
        }

        /* Access modifiers changed, original: 0000 */
        @NonNull
        /* renamed from: a */
        public g d() {
            if (!this.a || VERSION.SDK_INT < 23 || !this.c.j.c()) {
                return new g(this);
            }
            throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
        }
    }

    g(a aVar) {
        super(aVar.b, aVar.c, aVar.d);
    }
}
