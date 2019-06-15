package kotlin.text;

import kotlin.jvm.a.a;
import kotlin.jvm.internal.c;

class j extends i {
    public static final <T> void a(Appendable appendable, T t, a<? super T, ? extends CharSequence> aVar) {
        c.b(appendable, "receiver$0");
        if (aVar != null) {
            appendable.append((CharSequence) aVar.a(t));
            return;
        }
        if (t != null ? t instanceof CharSequence : true) {
            appendable.append((CharSequence) t);
        } else if (t instanceof Character) {
            appendable.append(((Character) t).charValue());
        } else {
            appendable.append(String.valueOf(t));
        }
    }
}
