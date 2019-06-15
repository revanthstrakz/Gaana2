package com.e.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import com.e.a.e.a;
import com.e.a.e.c;
import com.gaana.models.BusinessObject;
import com.gaana.models.DownloadSyncArrays;
import java.util.ArrayList;
import java.util.Iterator;

public class b extends a {
    public b(Context context) {
        super(context);
    }

    private void a(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        SQLiteDatabase db = getDB();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(a.a);
        stringBuilder.append(" WHERE ");
        stringBuilder.append("business_id");
        stringBuilder.append("=?");
        Cursor rawQuery = db.rawQuery(stringBuilder.toString(), new String[]{String.valueOf(i)});
        if (rawQuery.moveToFirst()) {
            i5 = rawQuery.getInt(rawQuery.getColumnIndex("sync_status"));
            i6 = 1;
        } else {
            i5 = 0;
            i6 = i5;
        }
        rawQuery.close();
        if (i5 == 1 && i4 == 0) {
            try {
                db.delete(a.a, "business_id=?", new String[]{String.valueOf(i)});
            } catch (Exception unused) {
            }
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("business_id", Integer.valueOf(i));
            contentValues.put("entity_type", Integer.valueOf(i2));
            contentValues.put("sync_type", Integer.valueOf(i3));
            contentValues.put("sync_status", Integer.valueOf(i4));
            if (i6 != 0) {
                db.update(a.a, contentValues, "business_id=?", new String[]{String.valueOf(i)});
            } else {
                db.insert(a.a, null, contentValues);
            }
        }
    }

    public DownloadSyncArrays a() {
        DownloadSyncArrays downloadSyncArrays = new DownloadSyncArrays();
        getDB();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from ");
        stringBuilder.append(a.a);
        stringBuilder.append(" where ");
        stringBuilder.append("sync_status");
        stringBuilder.append("=");
        stringBuilder.append(0);
        Cursor rawQuery = getDB().rawQuery(stringBuilder.toString(), null);
        try {
            if (rawQuery.moveToFirst()) {
                do {
                    downloadSyncArrays.addToSync(rawQuery.getInt(rawQuery.getColumnIndex("business_id")), rawQuery.getInt(rawQuery.getColumnIndex("sync_type")), rawQuery.getInt(rawQuery.getColumnIndex("entity_type")));
                } while (rawQuery.moveToNext());
            }
        } catch (SQLException unused) {
        } catch (Throwable th) {
            rawQuery.close();
        }
        rawQuery.close();
        a(0, 2);
        return downloadSyncArrays;
    }

    public void a(int i, int i2, int i3) {
        a(i, i2, i3, 0);
    }

    public void b() {
        SQLiteDatabase db = getDB();
        try {
            db.beginTransaction();
            db.delete(a.a, "sync_status=?", new String[]{String.valueOf(2)});
            db.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
    }

    public void c() {
        a(2, 0);
    }

    private void a(int i, int i2) {
        SQLiteDatabase db = getDB();
        try {
            db.beginTransaction();
            String[] strArr = new String[]{String.valueOf(i)};
            ContentValues contentValues = new ContentValues();
            contentValues.put("sync_status", Integer.valueOf(i2));
            db.update(a.a, contentValues, "sync_status=?", strArr);
            db.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
    }

    public void a(ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        Iterator it;
        if (arrayList != null) {
            it = arrayList.iterator();
            while (it.hasNext()) {
                a(Integer.parseInt((String) it.next()), c.c, 1, 1);
            }
        }
        if (arrayList2 != null) {
            it = arrayList2.iterator();
            while (it.hasNext()) {
                a(Integer.parseInt((String) it.next()), c.a, 1, 1);
            }
        }
        if (arrayList3 != null) {
            it = arrayList3.iterator();
            while (it.hasNext()) {
                a(Integer.parseInt((String) it.next()), c.b, 1, 1);
            }
        }
    }

    public void a(ArrayList<BusinessObject> arrayList) {
        SQLiteDatabase db = getDB();
        try {
            db.beginTransaction();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                db.delete(a.a, "business_id=?", new String[]{((BusinessObject) it.next()).getBusinessObjId()});
            }
            db.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
    }

    public void b(ArrayList<String> arrayList) {
        SQLiteDatabase db = getDB();
        try {
            db.beginTransaction();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                db.delete(a.a, "business_id=?", new String[]{(String) it.next()});
            }
            db.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
    }

    public void a(String str) {
        SQLiteDatabase db = getDB();
        try {
            db.beginTransaction();
            db.delete(a.a, "business_id=?", new String[]{String.valueOf(str)});
            db.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
    }

    public int a(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select COUNT(*) from ");
        stringBuilder.append(a.a);
        stringBuilder.append(" where ");
        stringBuilder.append("sync_status");
        stringBuilder.append("=");
        stringBuilder.append(1);
        stringBuilder.append(" AND ");
        stringBuilder.append("entity_type");
        stringBuilder.append("=");
        stringBuilder.append(i);
        stringBuilder.append(" AND ");
        stringBuilder.append("sync_type");
        stringBuilder.append("=");
        stringBuilder.append(1);
        try {
            return (int) getDB().compileStatement(stringBuilder.toString()).simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return 0;
        }
    }

    public ArrayList<String> b(int i) {
        SQLiteDatabase db = getDB();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select business_id from ");
        stringBuilder.append(a.a);
        stringBuilder.append(" where ");
        stringBuilder.append("sync_status");
        stringBuilder.append("=");
        stringBuilder.append(1);
        stringBuilder.append(" AND ");
        stringBuilder.append("entity_type");
        stringBuilder.append("=");
        stringBuilder.append(i);
        stringBuilder.append(" AND ");
        stringBuilder.append("sync_type");
        stringBuilder.append("=");
        stringBuilder.append(1);
        stringBuilder.append(" LIMIT 30");
        Cursor rawQuery = db.rawQuery(stringBuilder.toString(), null);
        ArrayList arrayList = new ArrayList();
        try {
            if (rawQuery.moveToFirst()) {
                do {
                    arrayList.add(String.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("business_id"))));
                } while (rawQuery.moveToNext());
            }
        } catch (SQLException unused) {
        } catch (Throwable th) {
            rawQuery.close();
        }
        rawQuery.close();
        return arrayList;
    }

    public void d() {
        SQLiteDatabase db = getDB();
        try {
            db.beginTransaction();
            db.delete(a.a, null, null);
            db.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
    }
}
