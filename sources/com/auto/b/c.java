package com.auto.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.XmlResourceParser;
import com.gaana.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

public class c {
    private static final String a = "c";
    private final Map<String, ArrayList<a>> b;

    private static final class a {
        final String a;
        final String b;
        final boolean c;

        public a(String str, String str2, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = z;
        }
    }

    public c(Context context) {
        this.b = a(context.getResources().getXml(R.xml.allowed_media_browser_callers));
    }

    private Map<String, ArrayList<a>> a(XmlResourceParser xmlResourceParser) {
        HashMap hashMap = new HashMap();
        try {
            int next = xmlResourceParser.next();
            while (next != 1) {
                if (next == 2 && xmlResourceParser.getName().equals("signing_certificate")) {
                    String attributeValue = xmlResourceParser.getAttributeValue(null, "name");
                    String attributeValue2 = xmlResourceParser.getAttributeValue(null, "package");
                    boolean attributeBooleanValue = xmlResourceParser.getAttributeBooleanValue(null, "release", false);
                    String replaceAll = xmlResourceParser.nextText().replaceAll("\\s|\\n", "");
                    a aVar = new a(attributeValue, attributeValue2, attributeBooleanValue);
                    ArrayList arrayList = (ArrayList) hashMap.get(replaceAll);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        hashMap.put(replaceAll, arrayList);
                    }
                    arrayList.add(aVar);
                }
                next = xmlResourceParser.next();
            }
        } catch (IOException | XmlPullParserException unused) {
        }
        return hashMap;
    }

    /* JADX WARNING: Missing block: B:25:0x0068, code skipped:
            return true;
     */
    public boolean a(android.content.Context r5, java.lang.String r6, int r7) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        if (r1 == r7) goto L_0x0068;
    L_0x0005:
        r1 = android.os.Process.myUid();
        if (r1 != r7) goto L_0x000c;
    L_0x000b:
        goto L_0x0068;
    L_0x000c:
        r7 = r4.a(r5, r6);
        if (r7 == 0) goto L_0x0013;
    L_0x0012:
        return r0;
    L_0x0013:
        r5 = r4.b(r5, r6);
        r7 = 0;
        if (r5 != 0) goto L_0x001b;
    L_0x001a:
        return r7;
    L_0x001b:
        r1 = r5.signatures;
        r1 = r1.length;
        if (r1 == r0) goto L_0x0021;
    L_0x0020:
        return r7;
    L_0x0021:
        r5 = r5.signatures;
        r5 = r5[r7];
        r5 = r5.toByteArray();
        r1 = 2;
        r5 = android.util.Base64.encodeToString(r5, r1);
        r1 = r4.b;
        r5 = r1.get(r5);
        r5 = (java.util.ArrayList) r5;
        if (r5 != 0) goto L_0x003e;
    L_0x0038:
        r5 = r4.b;
        r5.isEmpty();
        return r7;
    L_0x003e:
        r1 = new java.lang.StringBuffer;
        r1.<init>();
        r5 = r5.iterator();
    L_0x0047:
        r2 = r5.hasNext();
        if (r2 == 0) goto L_0x0067;
    L_0x004d:
        r2 = r5.next();
        r2 = (com.auto.b.c.a) r2;
        r3 = r2.b;
        r3 = r6.equals(r3);
        if (r3 == 0) goto L_0x005c;
    L_0x005b:
        return r0;
    L_0x005c:
        r2 = r2.b;
        r1.append(r2);
        r2 = 32;
        r1.append(r2);
        goto L_0x0047;
    L_0x0067:
        return r7;
    L_0x0068:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.auto.b.c.a(android.content.Context, java.lang.String, int):boolean");
    }

    private boolean a(Context context, String str) {
        PackageInfo b = b(context, "android");
        boolean z = false;
        if (b == null || b.signatures == null || b.signatures.length == 0) {
            return false;
        }
        PackageInfo b2 = b(context, str);
        if (b2 != null && b2.signatures != null && b2.signatures.length > 0 && b.signatures[0].equals(b2.signatures[0])) {
            z = true;
        }
        return z;
    }

    private PackageInfo b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 64);
        } catch (NameNotFoundException unused) {
            return null;
        }
    }
}
