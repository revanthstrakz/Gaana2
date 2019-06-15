package kotlin.e;

import java.util.Iterator;
import kotlin.jvm.internal.c;

class g extends f {

    public static final class a implements Iterable<T> {
        final /* synthetic */ a a;

        public a(a aVar) {
            this.a = aVar;
        }

        public Iterator<T> iterator() {
            return this.a.a();
        }
    }

    public static final <T, R> a<R> a(a<? extends T> aVar, kotlin.jvm.a.a<? super T, ? extends R> aVar2) {
        c.b(aVar, "receiver$0");
        c.b(aVar2, "transform");
        return new h(aVar, aVar2);
    }

    public static final <T, A extends Appendable> A a(a<? extends T> aVar, A a, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, kotlin.jvm.a.a<? super T, ? extends CharSequence> aVar2) {
        c.b(aVar, "receiver$0");
        c.b(a, "buffer");
        c.b(charSequence, "separator");
        c.b(charSequence2, "prefix");
        c.b(charSequence3, "postfix");
        c.b(charSequence4, "truncated");
        a.append(charSequence2);
        Iterator a2 = aVar.a();
        int i2 = 0;
        while (a2.hasNext()) {
            Object next = a2.next();
            i2++;
            if (i2 > 1) {
                a.append(charSequence);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            j.a(a, next, aVar2);
        }
        if (i >= 0 && r5 > i) {
            a.append(charSequence4);
        }
        a.append(charSequence3);
        return a;
    }

    public static /* synthetic */ String a(a aVar, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, kotlin.jvm.a.a aVar2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charSequence = ", ";
        }
        if ((i2 & 2) != 0) {
            charSequence2 = "";
        }
        CharSequence charSequence5 = charSequence2;
        if ((i2 & 4) != 0) {
            charSequence3 = "";
        }
        CharSequence charSequence6 = charSequence3;
        if ((i2 & 8) != 0) {
            i = -1;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence7 = charSequence4;
        if ((i2 & 32) != 0) {
            aVar2 = (kotlin.jvm.a.a) null;
        }
        return a(aVar, charSequence, charSequence5, charSequence6, i3, charSequence7, aVar2);
    }

    public static final <T> String a(a<? extends T> aVar, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, kotlin.jvm.a.a<? super T, ? extends CharSequence> aVar2) {
        c.b(aVar, "receiver$0");
        c.b(charSequence, "separator");
        c.b(charSequence2, "prefix");
        c.b(charSequence3, "postfix");
        c.b(charSequence4, "truncated");
        Object stringBuilder = ((StringBuilder) a(aVar, new StringBuilder(), charSequence, charSequence2, charSequence3, i, charSequence4, aVar2)).toString();
        c.a(stringBuilder, "joinTo(StringBuilder(), …ed, transform).toString()");
        return stringBuilder;
    }

    public static final <T> Iterable<T> a(a<? extends T> aVar) {
        c.b(aVar, "receiver$0");
        return new a(aVar);
    }
}
