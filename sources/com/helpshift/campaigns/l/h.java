package com.helpshift.campaigns.l;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.helpshift.campaigns.models.PropertyValue;
import com.helpshift.util.f;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.payu.custombrowser.util.CBConstant;
import java.util.HashMap;

public class h implements i {
    private final g a = new g(o.b());

    public void a(String str) {
        str = f(str);
        synchronized (this.a) {
            try {
                this.a.a(this.a.getWritableDatabase(), str);
            } catch (Exception e) {
                l.c("Helpshift_PropertyDB", "Error initStorage", e);
            }
        }
    }

    public void b(String str) {
        a(e(f(str)));
    }

    public void a(String str, PropertyValue propertyValue, String str2) {
        if (!TextUtils.isEmpty(str) && propertyValue != null && !TextUtils.isEmpty(str2)) {
            str2 = f(str2);
            synchronized (this.a) {
                try {
                    SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                    str2 = this.a.a(str2);
                    String str3 = "key=?";
                    String[] strArr = new String[]{str};
                    if (f.a(writableDatabase, str2, str3, strArr)) {
                        writableDatabase.update(str2, a(str, propertyValue), str3, strArr);
                    } else {
                        writableDatabase.insert(str2, null, a(str, propertyValue));
                    }
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Error setProperty key: ");
                    stringBuilder.append(str);
                    stringBuilder.append(", value : ");
                    stringBuilder.append(propertyValue);
                    l.c("Helpshift_PropertyDB", stringBuilder.toString(), e);
                }
            }
        }
    }

    public void b(String str, PropertyValue propertyValue, String str2) {
        a(str, propertyValue, e(f(str2)));
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0067 A:{Catch:{ all -> 0x0064, all -> 0x006b }} */
    /* JADX WARNING: Missing block: B:13:0x003c, code skipped:
            if (r13 != null) goto L_0x003e;
     */
    /* JADX WARNING: Missing block: B:15:?, code skipped:
            r13.close();
     */
    /* JADX WARNING: Missing block: B:23:0x005f, code skipped:
            if (r13 != null) goto L_0x003e;
     */
    public com.helpshift.campaigns.models.PropertyValue a(java.lang.String r12, java.lang.String r13) {
        /*
        r11 = this;
        r0 = android.text.TextUtils.isEmpty(r12);
        r1 = 0;
        if (r0 != 0) goto L_0x0070;
    L_0x0007:
        r0 = android.text.TextUtils.isEmpty(r13);
        if (r0 == 0) goto L_0x000e;
    L_0x000d:
        goto L_0x0070;
    L_0x000e:
        r13 = r11.f(r13);
        r0 = r11.a;
        monitor-enter(r0);
        r2 = r11.a;	 Catch:{ Exception -> 0x0047, all -> 0x0044 }
        r3 = r2.getReadableDatabase();	 Catch:{ Exception -> 0x0047, all -> 0x0044 }
        r6 = "key=?";
        r2 = 1;
        r7 = new java.lang.String[r2];	 Catch:{ Exception -> 0x0047, all -> 0x0044 }
        r2 = 0;
        r7[r2] = r12;	 Catch:{ Exception -> 0x0047, all -> 0x0044 }
        r2 = r11.a;	 Catch:{ Exception -> 0x0047, all -> 0x0044 }
        r4 = r2.a(r13);	 Catch:{ Exception -> 0x0047, all -> 0x0044 }
        r5 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r13 = r3.query(r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Exception -> 0x0047, all -> 0x0044 }
        r2 = r13.moveToFirst();	 Catch:{ Exception -> 0x0042 }
        if (r2 == 0) goto L_0x003c;
    L_0x0037:
        r2 = r11.a(r13);	 Catch:{ Exception -> 0x0042 }
        r1 = r2;
    L_0x003c:
        if (r13 == 0) goto L_0x0062;
    L_0x003e:
        r13.close();	 Catch:{ all -> 0x006b }
        goto L_0x0062;
    L_0x0042:
        r2 = move-exception;
        goto L_0x0049;
    L_0x0044:
        r12 = move-exception;
        r13 = r1;
        goto L_0x0065;
    L_0x0047:
        r2 = move-exception;
        r13 = r1;
    L_0x0049:
        r3 = "Helpshift_PropertyDB";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0064 }
        r4.<init>();	 Catch:{ all -> 0x0064 }
        r5 = "Error getProperty key: ";
        r4.append(r5);	 Catch:{ all -> 0x0064 }
        r4.append(r12);	 Catch:{ all -> 0x0064 }
        r12 = r4.toString();	 Catch:{ all -> 0x0064 }
        com.helpshift.util.l.c(r3, r12, r2);	 Catch:{ all -> 0x0064 }
        if (r13 == 0) goto L_0x0062;
    L_0x0061:
        goto L_0x003e;
    L_0x0062:
        monitor-exit(r0);	 Catch:{ all -> 0x006b }
        return r1;
    L_0x0064:
        r12 = move-exception;
    L_0x0065:
        if (r13 == 0) goto L_0x006d;
    L_0x0067:
        r13.close();	 Catch:{ all -> 0x006b }
        goto L_0x006d;
    L_0x006b:
        r12 = move-exception;
        goto L_0x006e;
    L_0x006d:
        throw r12;	 Catch:{ all -> 0x006b }
    L_0x006e:
        monitor-exit(r0);	 Catch:{ all -> 0x006b }
        throw r12;
    L_0x0070:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.l.h.a(java.lang.String, java.lang.String):com.helpshift.campaigns.models.PropertyValue");
    }

    public PropertyValue b(String str, String str2) {
        return a(str, e(f(str2)));
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0076 A:{SYNTHETIC, Splitter:B:30:0x0076} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x008a A:{SYNTHETIC, Splitter:B:40:0x008a} */
    public void a(java.lang.Integer r6, java.lang.String[] r7, java.lang.String r8) {
        /*
        r5 = this;
        if (r7 == 0) goto L_0x00a1;
    L_0x0002:
        r0 = r7.length;
        if (r0 == 0) goto L_0x00a1;
    L_0x0005:
        r0 = android.text.TextUtils.isEmpty(r8);
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        goto L_0x00a1;
    L_0x000d:
        r8 = r5.f(r8);
        r0 = r5.a;
        monitor-enter(r0);
        r1 = 0;
        r2 = r5.a;	 Catch:{ Exception -> 0x006c }
        r2 = r2.getWritableDatabase();	 Catch:{ Exception -> 0x006c }
        r2.beginTransaction();	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r1.<init>();	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r3 = "key in (";
        r1.append(r3);	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r3 = r7.length;	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r3 = com.helpshift.util.f.a(r3);	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r1.append(r3);	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r3 = ")";
        r1.append(r3);	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r3 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r3.<init>();	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r4 = "sync_status";
        r3.put(r4, r6);	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r6 = r5.a;	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r6 = r6.a(r8);	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r2.update(r6, r3, r1, r7);	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        r2.setTransactionSuccessful();	 Catch:{ Exception -> 0x0066, all -> 0x0064 }
        if (r2 == 0) goto L_0x0086;
    L_0x0051:
        r6 = r2.inTransaction();	 Catch:{ Exception -> 0x005b }
        if (r6 == 0) goto L_0x0086;
    L_0x0057:
        r2.endTransaction();	 Catch:{ Exception -> 0x005b }
        goto L_0x0086;
    L_0x005b:
        r6 = move-exception;
        r7 = "Helpshift_PropertyDB";
        r8 = "Error setSyncStatus for multiple keys inside finally block";
    L_0x0060:
        com.helpshift.util.l.c(r7, r8, r6);	 Catch:{ all -> 0x0094 }
        goto L_0x0086;
    L_0x0064:
        r6 = move-exception;
        goto L_0x0088;
    L_0x0066:
        r6 = move-exception;
        r1 = r2;
        goto L_0x006d;
    L_0x0069:
        r6 = move-exception;
        r2 = r1;
        goto L_0x0088;
    L_0x006c:
        r6 = move-exception;
    L_0x006d:
        r7 = "Helpshift_PropertyDB";
        r8 = "Error setSyncStatus for multiple keys";
        com.helpshift.util.l.c(r7, r8, r6);	 Catch:{ all -> 0x0069 }
        if (r1 == 0) goto L_0x0086;
    L_0x0076:
        r6 = r1.inTransaction();	 Catch:{ Exception -> 0x0080 }
        if (r6 == 0) goto L_0x0086;
    L_0x007c:
        r1.endTransaction();	 Catch:{ Exception -> 0x0080 }
        goto L_0x0086;
    L_0x0080:
        r6 = move-exception;
        r7 = "Helpshift_PropertyDB";
        r8 = "Error setSyncStatus for multiple keys inside finally block";
        goto L_0x0060;
    L_0x0086:
        monitor-exit(r0);	 Catch:{ all -> 0x0094 }
        return;
    L_0x0088:
        if (r2 == 0) goto L_0x009e;
    L_0x008a:
        r7 = r2.inTransaction();	 Catch:{ Exception -> 0x0096 }
        if (r7 == 0) goto L_0x009e;
    L_0x0090:
        r2.endTransaction();	 Catch:{ Exception -> 0x0096 }
        goto L_0x009e;
    L_0x0094:
        r6 = move-exception;
        goto L_0x009f;
    L_0x0096:
        r7 = move-exception;
        r8 = "Helpshift_PropertyDB";
        r1 = "Error setSyncStatus for multiple keys inside finally block";
        com.helpshift.util.l.c(r8, r1, r7);	 Catch:{ all -> 0x0094 }
    L_0x009e:
        throw r6;	 Catch:{ all -> 0x0094 }
    L_0x009f:
        monitor-exit(r0);	 Catch:{ all -> 0x0094 }
        throw r6;
    L_0x00a1:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.l.h.a(java.lang.Integer, java.lang.String[], java.lang.String):void");
    }

    public void b(Integer num, String[] strArr, String str) {
        a(num, strArr, e(f(str)));
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0078 A:{SYNTHETIC, Splitter:B:32:0x0078} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0078 A:{SYNTHETIC, Splitter:B:32:0x0078} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A:{Splitter:B:7:0x0025, ExcHandler: all (th java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0080 A:{Catch:{ all -> 0x0084 }} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:17:0x0048, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:18:0x0049, code skipped:
            r12 = r1;
            r1 = r0;
            r0 = r12;
     */
    /* JADX WARNING: Missing block: B:22:0x0053, code skipped:
            r14 = th;
     */
    /* JADX WARNING: Missing block: B:23:0x0054, code skipped:
            r1 = r0;
     */
    /* JADX WARNING: Missing block: B:38:0x0080, code skipped:
            r1.close();
     */
    public java.util.HashMap<java.lang.String, com.helpshift.campaigns.models.PropertyValue> c(java.lang.String r14) {
        /*
        r13 = this;
        r0 = android.text.TextUtils.isEmpty(r14);
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = r13.f(r14);
        r2 = r13.a;
        monitor-enter(r2);
        r3 = r13.a;	 Catch:{ Exception -> 0x005e }
        r4 = r3.getReadableDatabase();	 Catch:{ Exception -> 0x005e }
        r3 = r13.a;	 Catch:{ Exception -> 0x005e }
        r5 = r3.a(r0);	 Catch:{ Exception -> 0x005e }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r11 = 0;
        r0 = r4.query(r5, r6, r7, r8, r9, r10, r11);	 Catch:{ Exception -> 0x005e }
        r3 = r0.moveToFirst();	 Catch:{ Exception -> 0x0056, all -> 0x0053 }
        if (r3 == 0) goto L_0x004d;
    L_0x002b:
        r3 = new java.util.HashMap;	 Catch:{ Exception -> 0x0056, all -> 0x0053 }
        r3.<init>();	 Catch:{ Exception -> 0x0056, all -> 0x0053 }
    L_0x0030:
        r1 = r0.isAfterLast();	 Catch:{ Exception -> 0x0048, all -> 0x0053 }
        if (r1 != 0) goto L_0x0046;
    L_0x0036:
        r1 = r13.a(r0);	 Catch:{ Exception -> 0x0048, all -> 0x0053 }
        r4 = 0;
        r4 = r0.getString(r4);	 Catch:{ Exception -> 0x0048, all -> 0x0053 }
        r3.put(r4, r1);	 Catch:{ Exception -> 0x0048, all -> 0x0053 }
        r0.moveToNext();	 Catch:{ Exception -> 0x0048, all -> 0x0053 }
        goto L_0x0030;
    L_0x0046:
        r1 = r3;
        goto L_0x004d;
    L_0x0048:
        r1 = move-exception;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x0060;
    L_0x004d:
        if (r0 == 0) goto L_0x007c;
    L_0x004f:
        r0.close();	 Catch:{ all -> 0x0084 }
        goto L_0x007c;
    L_0x0053:
        r14 = move-exception;
        r1 = r0;
        goto L_0x007e;
    L_0x0056:
        r3 = move-exception;
        r12 = r1;
        r1 = r0;
        r0 = r3;
        r3 = r12;
        goto L_0x0060;
    L_0x005c:
        r14 = move-exception;
        goto L_0x007e;
    L_0x005e:
        r0 = move-exception;
        r3 = r1;
    L_0x0060:
        r4 = "Helpshift_PropertyDB";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005c }
        r5.<init>();	 Catch:{ all -> 0x005c }
        r6 = "Error getAllProperties for identifier : ";
        r5.append(r6);	 Catch:{ all -> 0x005c }
        r5.append(r14);	 Catch:{ all -> 0x005c }
        r14 = r5.toString();	 Catch:{ all -> 0x005c }
        com.helpshift.util.l.c(r4, r14, r0);	 Catch:{ all -> 0x005c }
        if (r1 == 0) goto L_0x007b;
    L_0x0078:
        r1.close();	 Catch:{ all -> 0x0084 }
    L_0x007b:
        r1 = r3;
    L_0x007c:
        monitor-exit(r2);	 Catch:{ all -> 0x0084 }
        return r1;
    L_0x007e:
        if (r1 == 0) goto L_0x0086;
    L_0x0080:
        r1.close();	 Catch:{ all -> 0x0084 }
        goto L_0x0086;
    L_0x0084:
        r14 = move-exception;
        goto L_0x0087;
    L_0x0086:
        throw r14;	 Catch:{ all -> 0x0084 }
    L_0x0087:
        monitor-exit(r2);	 Catch:{ all -> 0x0084 }
        throw r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.l.h.c(java.lang.String):java.util.HashMap");
    }

    public HashMap<String, PropertyValue> d(String str) {
        return c(e(f(str)));
    }

    private ContentValues a(String str, PropertyValue propertyValue) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CBConstant.KEY, str);
        contentValues.put("value", propertyValue.toString());
        contentValues.put("type", propertyValue.b());
        contentValues.put("sync_status", propertyValue.c());
        contentValues.put("extras", "");
        return contentValues;
    }

    private PropertyValue a(Cursor cursor) {
        PropertyValue propertyValue = new PropertyValue(cursor.getString(2), cursor.getString(1));
        propertyValue.a(Integer.valueOf(cursor.getInt(3)));
        return propertyValue;
    }

    private String e(String str) {
        str = f(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("__hs_secondary_data");
        return stringBuilder.toString();
    }

    private String f(String str) {
        return str.replaceAll("'", "$");
    }
}
