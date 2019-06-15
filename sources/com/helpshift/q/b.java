package com.helpshift.q;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.helpshift.common.c;
import com.helpshift.util.e;
import com.helpshift.util.f;
import com.helpshift.util.l;
import com.payu.custombrowser.util.CBConstant;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

public class b implements d {
    private SQLiteOpenHelper a;
    private HashMap<String, Serializable> b;
    private String c;

    public b(SQLiteOpenHelper sQLiteOpenHelper, String str) {
        this.a = sQLiteOpenHelper;
        this.c = str;
        if (!c.a(str)) {
            this.b = e.e(str);
            if (this.b == null) {
                this.b = new HashMap();
                return;
            }
            for (Entry entry : this.b.entrySet()) {
                a((String) entry.getKey(), (Serializable) entry.getValue());
            }
        }
    }

    public void b(String str, Serializable serializable) {
        if (a(str, serializable) && !c.a(this.c)) {
            this.b.put(str, serializable);
            e.a(this.c, this.b);
        }
    }

    public boolean a(String str, Serializable serializable) {
        if (TextUtils.isEmpty(str) || serializable == null) {
            return false;
        }
        SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
        String str2 = "key=?";
        String[] strArr = new String[]{str};
        ContentValues contentValues = new ContentValues();
        contentValues.put(CBConstant.KEY, str);
        try {
            contentValues.put("value", com.helpshift.util.c.a((Object) serializable));
            if (f.a(writableDatabase, "key_value_store", str2, strArr)) {
                writableDatabase.update("key_value_store", contentValues, str2, strArr);
            } else if (writableDatabase.insert("key_value_store", null, contentValues) == -1) {
                throw new SQLiteException("DB insert failed and return -1");
            }
            return true;
        } catch (IOException e) {
            l.c("HS_KeyValueDB", "Error in serializing value", e);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003b  */
    public java.lang.Object a(java.lang.String r11) {
        /*
        r10 = this;
        r0 = android.text.TextUtils.isEmpty(r11);
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = r10.a;	 Catch:{ all -> 0x0038 }
        r2 = r0.getReadableDatabase();	 Catch:{ all -> 0x0038 }
        r5 = "key=?";
        r0 = 1;
        r6 = new java.lang.String[r0];	 Catch:{ all -> 0x0038 }
        r3 = 0;
        r6[r3] = r11;	 Catch:{ all -> 0x0038 }
        r3 = "key_value_store";
        r4 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r11 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ all -> 0x0038 }
        r2 = r11.moveToFirst();	 Catch:{ all -> 0x0035 }
        if (r2 == 0) goto L_0x002f;
    L_0x0026:
        r0 = r11.getBlob(r0);	 Catch:{ Exception -> 0x002f }
        r0 = com.helpshift.util.c.a(r0);	 Catch:{ Exception -> 0x002f }
        r1 = r0;
    L_0x002f:
        if (r11 == 0) goto L_0x0034;
    L_0x0031:
        r11.close();
    L_0x0034:
        return r1;
    L_0x0035:
        r0 = move-exception;
        r1 = r11;
        goto L_0x0039;
    L_0x0038:
        r0 = move-exception;
    L_0x0039:
        if (r1 == 0) goto L_0x003e;
    L_0x003b:
        r1.close();
    L_0x003e:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.q.b.a(java.lang.String):java.lang.Object");
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] strArr = new String[]{str};
            this.a.getWritableDatabase().delete("key_value_store", "key=?", strArr);
        }
    }

    public void a() {
        this.a.getWritableDatabase().delete("key_value_store", null, null);
    }
}
