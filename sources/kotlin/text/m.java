package kotlin.text;

import com.facebook.internal.FacebookRequestErrorClassification;
import kotlin.jvm.internal.c;

class m extends l {
    public static final boolean a(String str, String str2, boolean z) {
        if (str == null) {
            return str2 == null;
        }
        boolean equalsIgnoreCase;
        if (z) {
            equalsIgnoreCase = str.equalsIgnoreCase(str2);
        } else {
            equalsIgnoreCase = str.equals(str2);
        }
        return equalsIgnoreCase;
    }

    public static final String a(String str, String str2, String str3, boolean z) {
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        c.b(str4, "receiver$0");
        c.b(str5, "oldValue");
        c.b(str6, "newValue");
        return g.a(n.a((CharSequence) str4, new String[]{str5}, z, 0, 4, null), str6, null, null, 0, null, null, 62, null);
    }

    public static /* synthetic */ boolean a(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return b(str, str2, z);
    }

    public static final boolean b(String str, String str2, boolean z) {
        c.b(str, "receiver$0");
        c.b(str2, "prefix");
        if (!z) {
            return str.startsWith(str2);
        }
        return a(str, 0, str2, 0, str2.length(), z);
    }

    public static final boolean a(String str, int i, String str2, int i2, int i3, boolean z) {
        c.b(str, "receiver$0");
        c.b(str2, FacebookRequestErrorClassification.KEY_OTHER);
        if (z) {
            return str.regionMatches(z, i, str2, i2, i3);
        }
        return str.regionMatches(i, str2, i2, i3);
    }
}
