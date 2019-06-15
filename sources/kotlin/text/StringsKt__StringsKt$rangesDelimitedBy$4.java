package kotlin.text;

import java.util.List;
import kotlin.Pair;
import kotlin.a;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.c;

final class StringsKt__StringsKt$rangesDelimitedBy$4 extends Lambda implements b<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>> {
    final /* synthetic */ List a;
    final /* synthetic */ boolean b;

    StringsKt__StringsKt$rangesDelimitedBy$4(List list, boolean z) {
        this.a = list;
        this.b = z;
        super(2);
    }

    public final Pair<Integer, Integer> a(CharSequence charSequence, int i) {
        c.b(charSequence, "receiver$0");
        Pair a = n.b(charSequence, this.a, i, this.b, false);
        return a != null ? a.a(a.a(), Integer.valueOf(((String) a.b()).length())) : null;
    }
}
