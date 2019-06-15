package com.google.ads.interactivemedia.v3.impl.data;

import android.support.v4.view.ViewCompat;
import android.view.View;
import com.google.ads.interactivemedia.v3.internal.ki;
import com.google.ads.interactivemedia.v3.internal.la;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ki(a = i.class)
public abstract class q {

    public static abstract class a {
        public abstract q build();

        public abstract a obstructions(List<b> list);

        public a views(Collection<View> collection) {
            ArrayList arrayList = new ArrayList();
            for (View view : collection) {
                arrayList.add(b.builder().view(view).build());
            }
            return obstructions(arrayList);
        }
    }

    @ki(a = j.class)
    public static abstract class b {

        public static abstract class a {
            public abstract a attached(boolean z);

            public abstract a bounds(m mVar);

            public abstract b build();

            public abstract a hidden(boolean z);

            public abstract a type(String str);

            /* Access modifiers changed, original: 0000 */
            public a view(View view) {
                return attached(ViewCompat.isAttachedToWindow(view)).bounds(m.builder().locationOnScreenOfView(view).build()).hidden(view.isShown() ^ 1).type(view.getClass().getCanonicalName());
            }
        }

        public abstract boolean attached();

        public abstract m bounds();

        public abstract boolean hidden();

        public abstract String type();

        public static a builder() {
            return new a();
        }
    }

    public abstract la<b> obstructions();

    public static a builder() {
        return new a();
    }
}
