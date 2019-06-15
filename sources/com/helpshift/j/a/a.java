package com.helpshift.j.a;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class a {
    public static ContentValues a(com.helpshift.j.d.a aVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TIMESTAMP", aVar.a);
        contentValues.put("MESSAGE", aVar.b);
        contentValues.put("LEVEL", aVar.d);
        contentValues.put("EXTRAS", aVar.e);
        contentValues.put("STACKTRACE", aVar.c);
        return contentValues;
    }

    public static List<com.helpshift.j.d.a> a(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                arrayList.add(new com.helpshift.j.d.a(cursor.getString(cursor.getColumnIndex("TIMESTAMP")), cursor.getString(cursor.getColumnIndex("LEVEL")), cursor.getString(cursor.getColumnIndex("MESSAGE")), cursor.getString(cursor.getColumnIndex("STACKTRACE")), cursor.getString(cursor.getColumnIndex("EXTRAS"))));
                cursor.moveToNext();
            }
        }
        return arrayList;
    }
}
