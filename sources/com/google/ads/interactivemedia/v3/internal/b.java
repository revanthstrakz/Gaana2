package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;

public class b {
    private boolean a;

    /* Access modifiers changed, original: 0000 */
    public String a() {
        return "1.2.4-google_20180831";
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(String str) {
        return b(a()) == b(str);
    }

    /* Access modifiers changed, original: 0000 */
    public int b(String str) {
        c(str);
        return Integer.parseInt(str.split("\\.", 2)[0]);
    }

    private void c(String str) {
        af.a((Object) str, "Version cannot be null");
        if (!str.matches("[0-9]+\\.[0-9]+\\.[0-9]+.*")) {
            String str2 = "Invalid version format : ";
            str = String.valueOf(str);
            throw new IllegalArgumentException(str.length() != 0 ? str2.concat(str) : new String(str2));
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean b() {
        return this.a;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(boolean z) {
        this.a = z;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(String str, Context context) {
        b(str, context);
        if (!a(str)) {
            return false;
        }
        if (!b()) {
            a(true);
            t.a().a(context);
            q.a().a(context);
            ac.a(context);
            r.a().a(context);
        }
        return true;
    }

    private void b(String str, Context context) {
        c(str);
        af.a((Object) context, "Application Context cannot be null");
    }
}
