package com.helpshift.util;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class f {
    public static boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT COUNT(*) FROM ");
        stringBuilder.append(str);
        stringBuilder.append(" WHERE ");
        stringBuilder.append(str2);
        stringBuilder.append(" LIMIT 1");
        return DatabaseUtils.longForQuery(sQLiteDatabase, stringBuilder.toString(), strArr) > 0;
    }

    public static String a(int i) {
        int i2 = 1;
        if (i < 1) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder((i * 2) - 1);
        stringBuilder.append("?");
        while (i2 < i) {
            stringBuilder.append(",?");
            i2++;
        }
        return stringBuilder.toString();
    }

    public static <T> List<List<T>> a(int i, List<T> list) {
        ArrayList arrayList = new ArrayList();
        if (i > list.size()) {
            arrayList.add(list);
        } else {
            int i2 = 0;
            int i3 = i;
            while (i3 <= list.size() && i2 <= i3) {
                List subList = list.subList(i2, i3);
                i2 += i;
                i3 = Math.min(subList.size() + i3, list.size());
                if (subList.size() > 0) {
                    arrayList.add(subList);
                }
            }
        }
        return arrayList;
    }
}
