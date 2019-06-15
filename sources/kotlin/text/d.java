package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.c.c;
import kotlin.jvm.a.b;

final class d implements kotlin.e.a<c> {
    private final CharSequence a;
    private final int b;
    private final int c;
    private final b<CharSequence, Integer, Pair<Integer, Integer>> d;

    public static final class a implements Iterator<c> {
        final /* synthetic */ d a;
        private int b = -1;
        private int c;
        private int d;
        private c e;
        private int f;

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        a(d dVar) {
            this.a = dVar;
            this.c = g.a(dVar.b, 0, dVar.a.length());
            this.d = this.c;
        }

        /* JADX WARNING: Missing block: B:6:0x0025, code skipped:
            if (r6.f < kotlin.text.d.a(r6.a)) goto L_0x0027;
     */
        private final void b() {
            /*
            r6 = this;
            r0 = r6.d;
            r1 = 0;
            if (r0 >= 0) goto L_0x000e;
        L_0x0005:
            r6.b = r1;
            r0 = 0;
            r0 = (kotlin.c.c) r0;
            r6.e = r0;
            goto L_0x00a4;
        L_0x000e:
            r0 = r6.a;
            r0 = r0.c;
            r2 = -1;
            r3 = 1;
            if (r0 <= 0) goto L_0x0027;
        L_0x0018:
            r0 = r6.f;
            r0 = r0 + r3;
            r6.f = r0;
            r0 = r6.f;
            r4 = r6.a;
            r4 = r4.c;
            if (r0 >= r4) goto L_0x0035;
        L_0x0027:
            r0 = r6.d;
            r4 = r6.a;
            r4 = r4.a;
            r4 = r4.length();
            if (r0 <= r4) goto L_0x004b;
        L_0x0035:
            r0 = r6.c;
            r1 = new kotlin.c.c;
            r4 = r6.a;
            r4 = r4.a;
            r4 = kotlin.text.n.a(r4);
            r1.<init>(r0, r4);
            r6.e = r1;
            r6.d = r2;
            goto L_0x00a2;
        L_0x004b:
            r0 = r6.a;
            r0 = r0.d;
            r4 = r6.a;
            r4 = r4.a;
            r5 = r6.d;
            r5 = java.lang.Integer.valueOf(r5);
            r0 = r0.a(r4, r5);
            r0 = (kotlin.Pair) r0;
            if (r0 != 0) goto L_0x007b;
        L_0x0065:
            r0 = r6.c;
            r1 = new kotlin.c.c;
            r4 = r6.a;
            r4 = r4.a;
            r4 = kotlin.text.n.a(r4);
            r1.<init>(r0, r4);
            r6.e = r1;
            r6.d = r2;
            goto L_0x00a2;
        L_0x007b:
            r2 = r0.c();
            r2 = (java.lang.Number) r2;
            r2 = r2.intValue();
            r0 = r0.d();
            r0 = (java.lang.Number) r0;
            r0 = r0.intValue();
            r4 = r6.c;
            r4 = kotlin.c.g.b(r4, r2);
            r6.e = r4;
            r2 = r2 + r0;
            r6.c = r2;
            r2 = r6.c;
            if (r0 != 0) goto L_0x009f;
        L_0x009e:
            r1 = r3;
        L_0x009f:
            r2 = r2 + r1;
            r6.d = r2;
        L_0x00a2:
            r6.b = r3;
        L_0x00a4:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.text.d$a.b():void");
        }

        /* renamed from: a */
        public c next() {
            if (this.b == -1) {
                b();
            }
            if (this.b == 0) {
                throw new NoSuchElementException();
            }
            c cVar = this.e;
            if (cVar == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.ranges.IntRange");
            }
            this.e = (c) null;
            this.b = -1;
            return cVar;
        }

        public boolean hasNext() {
            if (this.b == -1) {
                b();
            }
            return this.b == 1;
        }
    }

    public d(CharSequence charSequence, int i, int i2, b<? super CharSequence, ? super Integer, Pair<Integer, Integer>> bVar) {
        kotlin.jvm.internal.c.b(charSequence, "input");
        kotlin.jvm.internal.c.b(bVar, "getNextMatch");
        this.a = charSequence;
        this.b = i;
        this.c = i2;
        this.d = bVar;
    }

    public Iterator<c> a() {
        return new a(this);
    }
}
