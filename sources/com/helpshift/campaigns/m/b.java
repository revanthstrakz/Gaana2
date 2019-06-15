package com.helpshift.campaigns.m;

import com.helpshift.campaigns.c.a;
import com.helpshift.campaigns.l.d;
import com.helpshift.campaigns.models.AnalyticsEvent;
import com.helpshift.util.a.c;
import com.helpshift.util.w;
import java.util.ArrayList;
import java.util.List;

public final class b {
    private static c a = new c(false);

    public static String a(String str) {
        String str2 = com.helpshift.campaigns.c.b.a().d.a().a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(":");
        stringBuilder.append(str2);
        return stringBuilder.toString();
    }

    public static List<com.helpshift.campaigns.models.b> a(d dVar, String str) {
        return (dVar == null || w.a(str)) ? null : a(dVar, dVar.c(str));
    }

    private static List<com.helpshift.campaigns.models.b> a(final d dVar, List<com.helpshift.campaigns.models.b> list) {
        ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (list != null) {
            for (com.helpshift.campaigns.models.b bVar : list) {
                long b = bVar.b();
                if (b == Long.MAX_VALUE || b > currentTimeMillis) {
                    arrayList.add(bVar);
                } else {
                    arrayList2.add(bVar.k());
                }
            }
        }
        if (arrayList2.size() > 0) {
            a.b(new Runnable() {
                public void run() {
                    dVar.a((String[]) arrayList2.toArray(new String[0]));
                    a aVar = com.helpshift.campaigns.c.b.a().e;
                    for (String str : arrayList2) {
                        com.helpshift.util.b.a(str);
                        aVar.a(AnalyticsEvent.a.f, str, Boolean.valueOf(false));
                    }
                }
            });
        }
        return arrayList;
    }
}
