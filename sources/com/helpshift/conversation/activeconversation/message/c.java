package com.helpshift.conversation.activeconversation.message;

import java.io.File;
import java.util.Locale;

public abstract class c extends j {
    public String c;
    public String d;
    public String e;
    public int f;
    public String g;

    c(String str, String str2, String str3, int i, String str4, String str5, String str6, boolean z, MessageType messageType) {
        super(str, str2, str3, z, messageType);
        this.f = i;
        this.c = str4;
        this.e = str5;
        this.d = str6;
    }

    public String c() {
        return a((double) this.f);
    }

    /* Access modifiers changed, original: 0000 */
    public String a(double d) {
        Object obj;
        String str = " MB";
        if (d < 1024.0d) {
            obj = " B";
        } else if (d < 1048576.0d) {
            d /= 1024.0d;
            obj = " KB";
        } else {
            d /= 1048576.0d;
            obj = str;
        }
        StringBuilder stringBuilder;
        if (str.equals(obj)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(d)}));
            stringBuilder.append(obj);
            return stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(Locale.US, "%.0f", new Object[]{Double.valueOf(d)}));
        stringBuilder.append(obj);
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(String str) {
        boolean z = false;
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.canRead()) {
            z = true;
        }
        return z;
    }

    public void a(j jVar) {
        super.a(jVar);
        if (jVar instanceof c) {
            c cVar = (c) jVar;
            this.c = cVar.c;
            this.d = cVar.d;
            this.e = cVar.e;
            this.f = cVar.f;
        }
    }
}
