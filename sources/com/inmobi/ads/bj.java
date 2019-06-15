package com.inmobi.ads;

import android.content.ContentValues;
import com.inmobi.commons.core.d.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class bj {
    public static final String[] a = new String[]{"id", "placement_id", "tp_key", "last_accessed_ts", "ad_type", "m10_context"};
    private static final String b = "bj";
    private static bj c;
    private static final Object d = new Object();

    public static bj a() {
        bj bjVar = c;
        if (bjVar == null) {
            synchronized (d) {
                bjVar = c;
                if (bjVar == null) {
                    bjVar = new bj();
                    c = bjVar;
                }
            }
        }
        return bjVar;
    }

    private bj() {
        b a = b.a();
        a.a("placement", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, placement_id INTEGER NOT NULL,tp_key TEXT,last_accessed_ts INTEGER NOT NULL,ad_type TEXT NOT NULL,m10_context TEXT NOT NULL,UNIQUE(placement_id,m10_context,tp_key))");
        a.b();
    }

    public static int a(long j, String str) {
        b a = b.a();
        long currentTimeMillis = System.currentTimeMillis() - (j * 1000);
        int a2 = a.a("placement", "ad_type=? AND last_accessed_ts<?", new String[]{str, String.valueOf(currentTimeMillis)});
        StringBuilder stringBuilder = new StringBuilder("Deleted ");
        stringBuilder.append(a2);
        stringBuilder.append(" expired pids from cache");
        a.b();
        return a2;
    }

    public final synchronized int a(List<bi> list, int i) {
        int i2 = 0;
        if (list.size() == 0) {
            return 0;
        }
        b a = b.a();
        for (int i3 = 0; i3 < list.size(); i3++) {
            bi biVar = (bi) list.get(i3);
            String[] strArr = new String[]{String.valueOf(biVar.a), biVar.f.toString(), biVar.b};
            ContentValues contentValues = new ContentValues();
            contentValues.put("placement_id", Long.valueOf(biVar.a));
            contentValues.put("last_accessed_ts", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("tp_key", biVar.b);
            contentValues.put("ad_type", biVar.e);
            contentValues.put("m10_context", biVar.f.toString());
            a.a("placement", contentValues, "placement_id = ? AND m10_context = ? AND tp_key=?", strArr);
        }
        int a2 = a.a("placement") - i;
        if (a2 > 0) {
            b bVar = a;
            List a3 = bVar.a("placement", new String[]{"id"}, null, null, null, null, "last_accessed_ts ASC", String.valueOf(a2));
            String[] strArr2 = new String[a3.size()];
            while (i2 < a3.size()) {
                strArr2[i2] = String.valueOf(((ContentValues) a3.get(i2)).getAsInteger("id"));
                i2++;
            }
            String replace = Arrays.toString(strArr2).replace("[", "(").replace("]", ")");
            StringBuilder stringBuilder = new StringBuilder("id IN ");
            stringBuilder.append(replace);
            a.a("placement", stringBuilder.toString(), null);
        }
        a.b();
        return a2;
    }

    public static List<bi> a(String str) {
        ArrayList arrayList = new ArrayList();
        b a = b.a();
        b bVar = a;
        List<ContentValues> a2 = bVar.a("placement", a, "ad_type=? ", new String[]{str}, null, null, null, null);
        a.b();
        for (ContentValues biVar : a2) {
            arrayList.add(new bi(biVar));
        }
        return arrayList;
    }
}
