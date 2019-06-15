package kotlin.text;

import com.facebook.internal.FacebookRequestErrorClassification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.c.a;
import kotlin.jvm.internal.c;

class n extends m {
    public static final int a(CharSequence charSequence) {
        c.b(charSequence, "receiver$0");
        return charSequence.length() - 1;
    }

    public static final String a(CharSequence charSequence, kotlin.c.c cVar) {
        c.b(charSequence, "receiver$0");
        c.b(cVar, "range");
        return charSequence.subSequence(cVar.f().intValue(), cVar.g().intValue() + 1).toString();
    }

    public static final boolean a(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3, boolean z) {
        c.b(charSequence, "receiver$0");
        c.b(charSequence2, FacebookRequestErrorClassification.KEY_OTHER);
        if (i2 < 0 || i < 0 || i > charSequence.length() - i3 || i2 > charSequence2.length() - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!c.a(charSequence.charAt(i + i4), charSequence2.charAt(i2 + i4), z)) {
                return false;
            }
        }
        return true;
    }

    static /* synthetic */ int a(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            z2 = false;
        }
        return a(charSequence, charSequence2, i, i2, z, z2);
    }

    private static final int a(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        a a;
        if (z2) {
            a = g.a(g.d(i, a(charSequence)), g.c(i2, 0));
        } else {
            a = new kotlin.c.c(g.c(i, 0), g.d(i2, charSequence.length()));
        }
        int c;
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            i = a.a();
            i2 = a.b();
            c = a.c();
            if (c <= 0 ? i < i2 : i > i2) {
                while (true) {
                    if (!m.a((String) charSequence2, 0, (String) charSequence, i, charSequence2.length(), z)) {
                        if (i == i2) {
                            break;
                        }
                        i += c;
                    } else {
                        return i;
                    }
                }
            }
        } else {
            i = a.a();
            i2 = a.b();
            c = a.c();
            if (c <= 0 ? i < i2 : i > i2) {
                while (true) {
                    if (!a(charSequence2, 0, charSequence, i, charSequence2.length(), z)) {
                        if (i == i2) {
                            break;
                        }
                        i += c;
                    } else {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    private static final Pair<Integer, String> b(CharSequence charSequence, Collection<String> collection, int i, boolean z, boolean z2) {
        Pair<Integer, String> pair = null;
        if (z || collection.size() != 1) {
            a cVar = !z2 ? new kotlin.c.c(g.c(i, 0), charSequence.length()) : g.a(g.d(i, a(charSequence)), 0);
            int b;
            int c;
            String str;
            Object obj;
            String str2;
            if (charSequence instanceof String) {
                i = cVar.a();
                b = cVar.b();
                c = cVar.c();
                if (c <= 0 ? i < b : i > b) {
                    while (true) {
                        for (Object obj2 : collection) {
                            str = (String) obj2;
                            if (m.a(str, 0, (String) charSequence, i, str.length(), z)) {
                                break;
                            }
                        }
                        obj2 = null;
                        str2 = (String) obj2;
                        if (str2 == null) {
                            if (i == b) {
                                break;
                            }
                            i += c;
                        } else {
                            return kotlin.a.a(Integer.valueOf(i), str2);
                        }
                    }
                }
            } else {
                i = cVar.a();
                b = cVar.b();
                c = cVar.c();
                if (c <= 0 ? i < b : i > b) {
                    while (true) {
                        for (Object obj22 : collection) {
                            str = (String) obj22;
                            if (a((CharSequence) str, 0, charSequence, i, str.length(), z)) {
                                break;
                            }
                        }
                        obj22 = null;
                        str2 = (String) obj22;
                        if (str2 == null) {
                            if (i == b) {
                                break;
                            }
                            i += c;
                        } else {
                            return kotlin.a.a(Integer.valueOf(i), str2);
                        }
                    }
                }
            }
            return null;
        }
        String str3 = (String) q.a((Iterable) collection);
        int a = !z2 ? a(charSequence, str3, i, false, 4, null) : b(charSequence, str3, i, false, 4, null);
        if (a >= 0) {
            pair = kotlin.a.a(Integer.valueOf(a), str3);
        }
        return pair;
    }

    public static final int a(CharSequence charSequence, String str, int i, boolean z) {
        c.b(charSequence, "receiver$0");
        c.b(str, "string");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(str, i);
        }
        return a(charSequence, str, i, charSequence.length(), z, false, 16, null);
    }

    public static final int b(CharSequence charSequence, String str, int i, boolean z) {
        c.b(charSequence, "receiver$0");
        c.b(str, "string");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(str, i);
        }
        return a(charSequence, (CharSequence) str, i, 0, z, true);
    }

    public static final boolean a(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        c.b(charSequence, "receiver$0");
        c.b(charSequence2, FacebookRequestErrorClassification.KEY_OTHER);
        if (charSequence2 instanceof String) {
            if (a(charSequence, (String) charSequence2, 0, z, 2, null) < 0) {
                return false;
            }
        }
        if (a(charSequence, charSequence2, 0, charSequence.length(), z, false, 16, null) < 0) {
            return false;
        }
        return true;
    }

    static /* synthetic */ kotlin.e.a a(CharSequence charSequence, String[] strArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return a(charSequence, strArr, i, z, i2);
    }

    private static final kotlin.e.a<kotlin.c.c> a(CharSequence charSequence, String[] strArr, int i, boolean z, int i2) {
        if ((i2 >= 0 ? 1 : null) != null) {
            return new d(charSequence, i, i2, new StringsKt__StringsKt$rangesDelimitedBy$4(d.a(strArr), z));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Limit must be non-negative, but was ");
        stringBuilder.append(i2);
        stringBuilder.append('.');
        throw new IllegalArgumentException(stringBuilder.toString().toString());
    }

    public static final kotlin.e.a<String> a(CharSequence charSequence, String[] strArr, boolean z, int i) {
        c.b(charSequence, "receiver$0");
        c.b(strArr, "delimiters");
        return g.a(a(charSequence, strArr, 0, z, i, 2, null), new StringsKt__StringsKt$splitToSequence$1(charSequence));
    }

    public static final List<String> b(CharSequence charSequence, String[] strArr, boolean z, int i) {
        c.b(charSequence, "receiver$0");
        c.b(strArr, "delimiters");
        int i2 = 1;
        if (strArr.length == 1) {
            String str = strArr[0];
            if (str.length() != 0) {
                i2 = 0;
            }
            if (i2 == 0) {
                return a(charSequence, str, z, i);
            }
        }
        Iterable<kotlin.c.c> a = g.a(a(charSequence, strArr, 0, z, i, 2, null));
        Collection arrayList = new ArrayList(j.a(a, 10));
        for (kotlin.c.c a2 : a) {
            arrayList.add(a(charSequence, a2));
        }
        return (List) arrayList;
    }

    private static final List<String> a(CharSequence charSequence, String str, boolean z, int i) {
        int i2 = 0;
        if ((i >= 0 ? 1 : 0) == 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Limit must be non-negative, but was ");
            stringBuilder.append(i);
            stringBuilder.append('.');
            throw new IllegalArgumentException(stringBuilder.toString().toString());
        }
        int a = a(charSequence, str, 0, z);
        if (a == -1 || i == 1) {
            return h.a(charSequence.toString());
        }
        int i3 = i > 0 ? 1 : 0;
        int i4 = 10;
        if (i3 != 0) {
            i4 = g.d(i, 10);
        }
        ArrayList arrayList = new ArrayList(i4);
        do {
            arrayList.add(charSequence.subSequence(i2, a).toString());
            i2 = str.length() + a;
            if (i3 != 0 && arrayList.size() == i - 1) {
                break;
            }
            a = a(charSequence, str, i2, z);
        } while (a != -1);
        arrayList.add(charSequence.subSequence(i2, charSequence.length()).toString());
        return arrayList;
    }
}
