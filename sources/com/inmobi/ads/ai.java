package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.View;
import com.inmobi.ads.c.k;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class ai {
    static final Map<Context, y> a = new WeakHashMap();
    private static final String b = "ai";
    private static final Map<Context, ce> c = new WeakHashMap();
    private static final Map<View, a> d = new HashMap();
    private static final a e = new a() {
        public final void a(View view, Object obj) {
            ((ah) obj).a(view);
        }
    };
    private static final a f = new a() {
        private final Rect a = new Rect();

        /* JADX WARNING: Missing block: B:29:0x0066, code skipped:
            return false;
     */
        public final boolean a(@android.support.annotation.Nullable android.view.View r7, @android.support.annotation.Nullable android.view.View r8, int r9, @android.support.annotation.NonNull java.lang.Object r10) {
            /*
            r6 = this;
            r0 = r10 instanceof com.inmobi.ads.ah;
            r1 = 0;
            if (r0 != 0) goto L_0x0006;
        L_0x0005:
            return r1;
        L_0x0006:
            r10 = (com.inmobi.ads.ah) r10;
            r10 = r10.l;
            if (r10 == 0) goto L_0x000d;
        L_0x000c:
            return r1;
        L_0x000d:
            r10 = r8 instanceof com.inmobi.ads.NativeVideoView;
            if (r10 == 0) goto L_0x0020;
        L_0x0011:
            r10 = r8;
            r10 = (com.inmobi.ads.NativeVideoView) r10;
            r10 = r10.getMediaPlayer();
            if (r10 == 0) goto L_0x0020;
        L_0x001a:
            r0 = 3;
            r10 = r10.a;
            if (r0 == r10) goto L_0x0020;
        L_0x001f:
            return r1;
        L_0x0020:
            if (r8 == 0) goto L_0x0066;
        L_0x0022:
            if (r7 == 0) goto L_0x0066;
        L_0x0024:
            r10 = r8.isShown();
            if (r10 == 0) goto L_0x0066;
        L_0x002a:
            r10 = r7.getParent();
            if (r10 != 0) goto L_0x0031;
        L_0x0030:
            goto L_0x0066;
        L_0x0031:
            r10 = r6.a;
            r8 = r8.getGlobalVisibleRect(r10);
            if (r8 != 0) goto L_0x003a;
        L_0x0039:
            return r1;
        L_0x003a:
            r8 = r6.a;
            r8 = r8.height();
            r2 = (long) r8;
            r8 = r6.a;
            r8 = r8.width();
            r4 = (long) r8;
            r2 = r2 * r4;
            r8 = r7.getWidth();
            r4 = (long) r8;
            r7 = r7.getHeight();
            r7 = (long) r7;
            r4 = r4 * r7;
            r7 = 0;
            r10 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1));
            if (r10 <= 0) goto L_0x0065;
        L_0x005a:
            r7 = 100;
            r7 = r7 * r2;
            r9 = (long) r9;
            r9 = r9 * r4;
            r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1));
            if (r0 < 0) goto L_0x0065;
        L_0x0063:
            r7 = 1;
            return r7;
        L_0x0065:
            return r1;
        L_0x0066:
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ai$AnonymousClass2.a(android.view.View, android.view.View, int, java.lang.Object):boolean");
        }
    };
    private boolean g;
    private int h;

    public interface a {
        void a(View view, boolean z);
    }

    ai(int i) {
        this.h = i;
    }

    /* Access modifiers changed, original: final */
    public final void a(@NonNull Context context, @NonNull ah ahVar) {
        y yVar = (y) a.get(context);
        if (yVar != null) {
            yVar.a((Object) ahVar);
            if ((yVar.a.isEmpty() ^ 1) == 0) {
                a(context);
            }
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(@NonNull Context context) {
        y yVar = (y) a.remove(context);
        if (yVar != null) {
            yVar.c();
        }
        if ((context instanceof Activity) && VERSION.SDK_INT >= 15 && a.isEmpty() && this.g) {
            this.g = false;
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(@NonNull Context context, View view, @NonNull ah ahVar) {
        ce ceVar = (ce) c.get(context);
        if (ceVar != null) {
            ceVar.a((Object) ahVar);
            if ((ceVar.b.isEmpty() ^ 1) == 0) {
                ce ceVar2 = (ce) c.remove(context);
                if (ceVar2 != null) {
                    ceVar2.e();
                }
                if ((context instanceof Activity) && VERSION.SDK_INT >= 15 && c.isEmpty() && this.g) {
                    this.g = false;
                }
            }
        }
        d.remove(view);
    }

    static void b(Context context) {
        y yVar = (y) a.get(context);
        if (yVar != null) {
            yVar.b();
        }
    }

    static void c(Context context) {
        y yVar = (y) a.get(context);
        if (yVar != null) {
            yVar.a();
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(@NonNull Context context, @NonNull View view, @NonNull ah ahVar, @NonNull k kVar) {
        y yVar = (y) a.get(context);
        if (yVar == null) {
            if (context instanceof Activity) {
                yVar = new y(kVar, new s(f, (Activity) context), e);
                if (VERSION.SDK_INT >= 15 && !this.g) {
                    this.g = true;
                }
            } else {
                yVar = new y(kVar, new bk(f, kVar), e);
            }
            a.put(context, yVar);
        }
        if (this.h != 0) {
            yVar.a(view, ahVar, kVar.a, kVar.b);
        } else {
            yVar.a(view, ahVar, kVar.f, kVar.g);
        }
    }

    /* Access modifiers changed, original: final */
    public final void a(@NonNull Context context, @NonNull View view, @NonNull ah ahVar, @NonNull a aVar, @NonNull k kVar) {
        ce ceVar = (ce) c.get(context);
        if (ceVar == null) {
            ce sVar;
            boolean z = context instanceof Activity;
            if (z) {
                sVar = new s(f, (Activity) context);
            } else {
                sVar = new bk(f, kVar);
            }
            sVar.c = new c() {
                public final void a(List<View> list, List<View> list2) {
                    for (View view : list) {
                        a aVar = (a) ai.d.get(view);
                        if (aVar != null) {
                            aVar.a(view, true);
                        }
                    }
                    for (View view2 : list2) {
                        a aVar2 = (a) ai.d.get(view2);
                        if (aVar2 != null) {
                            aVar2.a(view2, false);
                        }
                    }
                }
            };
            c.put(context, sVar);
            if (z && VERSION.SDK_INT >= 15 && !this.g) {
                this.g = true;
            }
            ceVar = sVar;
        }
        d.put(view, aVar);
        if (this.h != 0) {
            ceVar.a(view, ahVar, kVar.e);
        } else {
            ceVar.a(view, ahVar, kVar.h);
        }
    }
}
