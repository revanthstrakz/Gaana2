package com.helpshift.campaigns.l;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.helpshift.campaigns.models.f;
import com.helpshift.campaigns.models.g;
import com.helpshift.util.c;
import com.helpshift.util.l;
import com.helpshift.util.o;
import java.io.IOException;
import java.util.ArrayList;

public class j implements l {
    private final k a = new k(o.b());

    public void a(f fVar) {
        if (fVar != null) {
            synchronized (this.a) {
                try {
                    String str = "identifier=?";
                    String[] strArr = new String[]{fVar.a};
                    SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                    if (com.helpshift.util.f.a(writableDatabase, "sessions", str, strArr)) {
                        writableDatabase.update("sessions", c(fVar), str, strArr);
                    } else {
                        writableDatabase.insert("sessions", null, c(fVar));
                    }
                } catch (Exception e) {
                    l.c("Helpshift_SessionDB", "Error storing sessions", e);
                }
            }
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004d */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:7|(1:9)|10|11|12|13|14|15) */
    public void b(com.helpshift.campaigns.models.f r12) {
        /*
        r11 = this;
        if (r12 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r0 = r11.a;
        monitor-enter(r0);
        r1 = "identifier=?";
        r2 = 1;
        r2 = new java.lang.String[r2];	 Catch:{ Exception -> 0x005c }
        r3 = 0;
        r4 = r12.a;	 Catch:{ Exception -> 0x005c }
        r2[r3] = r4;	 Catch:{ Exception -> 0x005c }
        r3 = r11.a;	 Catch:{ Exception -> 0x005c }
        r3 = r3.getWritableDatabase();	 Catch:{ Exception -> 0x005c }
        r4 = "sessions";
        r4 = com.helpshift.util.f.a(r3, r4, r1, r2);	 Catch:{ Exception -> 0x005c }
        if (r4 == 0) goto L_0x0064;
    L_0x001e:
        r4 = new android.content.ContentValues;	 Catch:{ Exception -> 0x005c }
        r4.<init>();	 Catch:{ Exception -> 0x005c }
        r5 = "start_time";
        r6 = r12.d;	 Catch:{ Exception -> 0x005c }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x005c }
        r4.put(r5, r6);	 Catch:{ Exception -> 0x005c }
        r5 = "end_time";
        r6 = r12.e;	 Catch:{ Exception -> 0x005c }
        r8 = 0;
        r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r10 <= 0) goto L_0x003a;
    L_0x0038:
        r8 = r12.e;	 Catch:{ Exception -> 0x005c }
    L_0x003a:
        r6 = java.lang.Long.valueOf(r8);	 Catch:{ Exception -> 0x005c }
        r4.put(r5, r6);	 Catch:{ Exception -> 0x005c }
        r5 = "durations";
        r12 = r12.f;	 Catch:{ IOException -> 0x004d }
        r12 = com.helpshift.util.c.a(r12);	 Catch:{ IOException -> 0x004d }
        r4.put(r5, r12);	 Catch:{ IOException -> 0x004d }
        goto L_0x0054;
    L_0x004d:
        r12 = "durations";
        r5 = "";
        r4.put(r12, r5);	 Catch:{ Exception -> 0x005c }
    L_0x0054:
        r12 = "sessions";
        r3.update(r12, r4, r1, r2);	 Catch:{ Exception -> 0x005c }
        goto L_0x0064;
    L_0x005a:
        r12 = move-exception;
        goto L_0x0066;
    L_0x005c:
        r12 = move-exception;
        r1 = "Helpshift_SessionDB";
        r2 = "Error updating session";
        com.helpshift.util.l.c(r1, r2, r12);	 Catch:{ all -> 0x005a }
    L_0x0064:
        monitor-exit(r0);	 Catch:{ all -> 0x005a }
        return;
    L_0x0066:
        monitor-exit(r0);	 Catch:{ all -> 0x005a }
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.l.j.b(com.helpshift.campaigns.models.f):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x008b A:{SYNTHETIC, Splitter:B:31:0x008b} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009f A:{SYNTHETIC, Splitter:B:41:0x009f} */
    public void a(java.lang.Integer r6, java.lang.String[] r7) {
        /*
        r5 = this;
        if (r7 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r0 = r5.a;
        monitor-enter(r0);
        r1 = 0;
        r2 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0081 }
        r2.<init>();	 Catch:{ Exception -> 0x0081 }
        r3 = "sync_status";
        r2.put(r3, r6);	 Catch:{ Exception -> 0x0081 }
        r6 = 900; // 0x384 float:1.261E-42 double:4.447E-321;
        r7 = java.util.Arrays.asList(r7);	 Catch:{ Exception -> 0x0081 }
        r6 = com.helpshift.util.f.a(r6, r7);	 Catch:{ Exception -> 0x0081 }
        r7 = r5.a;	 Catch:{ Exception -> 0x0081 }
        r7 = r7.getWritableDatabase();	 Catch:{ Exception -> 0x0081 }
        r7.beginTransaction();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r6 = r6.iterator();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
    L_0x0028:
        r1 = r6.hasNext();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        if (r1 == 0) goto L_0x0061;
    L_0x002e:
        r1 = r6.next();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r1 = (java.util.List) r1;	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3 = r1.size();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r1 = r1.toArray(r3);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r1 = (java.lang.String[]) r1;	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3.<init>();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r4 = "identifier in (";
        r3.append(r4);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r4 = r1.length;	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r4 = com.helpshift.util.f.a(r4);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3.append(r4);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r4 = ")";
        r3.append(r4);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        r4 = "sessions";
        r7.update(r4, r2, r3, r1);	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        goto L_0x0028;
    L_0x0061:
        r7.setTransactionSuccessful();	 Catch:{ Exception -> 0x007b, all -> 0x0079 }
        if (r7 == 0) goto L_0x009b;
    L_0x0066:
        r6 = r7.inTransaction();	 Catch:{ Exception -> 0x0070 }
        if (r6 == 0) goto L_0x009b;
    L_0x006c:
        r7.endTransaction();	 Catch:{ Exception -> 0x0070 }
        goto L_0x009b;
    L_0x0070:
        r6 = move-exception;
        r7 = "Helpshift_SessionDB";
        r1 = "Error in setting sync status inside finally block, ";
    L_0x0075:
        com.helpshift.util.l.c(r7, r1, r6);	 Catch:{ all -> 0x00a9 }
        goto L_0x009b;
    L_0x0079:
        r6 = move-exception;
        goto L_0x009d;
    L_0x007b:
        r6 = move-exception;
        r1 = r7;
        goto L_0x0082;
    L_0x007e:
        r6 = move-exception;
        r7 = r1;
        goto L_0x009d;
    L_0x0081:
        r6 = move-exception;
    L_0x0082:
        r7 = "Helpshift_SessionDB";
        r2 = "Error in setting sync status";
        com.helpshift.util.l.c(r7, r2, r6);	 Catch:{ all -> 0x007e }
        if (r1 == 0) goto L_0x009b;
    L_0x008b:
        r6 = r1.inTransaction();	 Catch:{ Exception -> 0x0095 }
        if (r6 == 0) goto L_0x009b;
    L_0x0091:
        r1.endTransaction();	 Catch:{ Exception -> 0x0095 }
        goto L_0x009b;
    L_0x0095:
        r6 = move-exception;
        r7 = "Helpshift_SessionDB";
        r1 = "Error in setting sync status inside finally block, ";
        goto L_0x0075;
    L_0x009b:
        monitor-exit(r0);	 Catch:{ all -> 0x00a9 }
        return;
    L_0x009d:
        if (r7 == 0) goto L_0x00b3;
    L_0x009f:
        r1 = r7.inTransaction();	 Catch:{ Exception -> 0x00ab }
        if (r1 == 0) goto L_0x00b3;
    L_0x00a5:
        r7.endTransaction();	 Catch:{ Exception -> 0x00ab }
        goto L_0x00b3;
    L_0x00a9:
        r6 = move-exception;
        goto L_0x00b4;
    L_0x00ab:
        r7 = move-exception;
        r1 = "Helpshift_SessionDB";
        r2 = "Error in setting sync status inside finally block, ";
        com.helpshift.util.l.c(r1, r2, r7);	 Catch:{ all -> 0x00a9 }
    L_0x00b3:
        throw r6;	 Catch:{ all -> 0x00a9 }
    L_0x00b4:
        monitor-exit(r0);	 Catch:{ all -> 0x00a9 }
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.l.j.a(java.lang.Integer, java.lang.String[]):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0081 A:{SYNTHETIC, Splitter:B:31:0x0081} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0095 A:{SYNTHETIC, Splitter:B:41:0x0095} */
    public void a(java.lang.String[] r6) {
        /*
        r5 = this;
        if (r6 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r0 = r5.a;
        monitor-enter(r0);
        r1 = 0;
        r2 = 900; // 0x384 float:1.261E-42 double:4.447E-321;
        r6 = java.util.Arrays.asList(r6);	 Catch:{ Exception -> 0x0077 }
        r6 = com.helpshift.util.f.a(r2, r6);	 Catch:{ Exception -> 0x0077 }
        r2 = r5.a;	 Catch:{ Exception -> 0x0077 }
        r2 = r2.getWritableDatabase();	 Catch:{ Exception -> 0x0077 }
        r2.beginTransaction();	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r6 = r6.iterator();	 Catch:{ Exception -> 0x0071, all -> 0x006f }
    L_0x001e:
        r1 = r6.hasNext();	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        if (r1 == 0) goto L_0x0057;
    L_0x0024:
        r1 = r6.next();	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r1 = (java.util.List) r1;	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r3 = r1.size();	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r3 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r1 = r1.toArray(r3);	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r1 = (java.lang.String[]) r1;	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r3.<init>();	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r4 = "identifier in (";
        r3.append(r4);	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r4 = r1.length;	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r4 = com.helpshift.util.f.a(r4);	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r3.append(r4);	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r4 = ")";
        r3.append(r4);	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        r4 = "sessions";
        r2.delete(r4, r3, r1);	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        goto L_0x001e;
    L_0x0057:
        r2.setTransactionSuccessful();	 Catch:{ Exception -> 0x0071, all -> 0x006f }
        if (r2 == 0) goto L_0x0091;
    L_0x005c:
        r6 = r2.inTransaction();	 Catch:{ Exception -> 0x0066 }
        if (r6 == 0) goto L_0x0091;
    L_0x0062:
        r2.endTransaction();	 Catch:{ Exception -> 0x0066 }
        goto L_0x0091;
    L_0x0066:
        r6 = move-exception;
        r1 = "Helpshift_SessionDB";
        r2 = "Error removing sessions inside finally block, ";
    L_0x006b:
        com.helpshift.util.l.c(r1, r2, r6);	 Catch:{ all -> 0x009f }
        goto L_0x0091;
    L_0x006f:
        r6 = move-exception;
        goto L_0x0093;
    L_0x0071:
        r6 = move-exception;
        r1 = r2;
        goto L_0x0078;
    L_0x0074:
        r6 = move-exception;
        r2 = r1;
        goto L_0x0093;
    L_0x0077:
        r6 = move-exception;
    L_0x0078:
        r2 = "Helpshift_SessionDB";
        r3 = "Error removing sessions";
        com.helpshift.util.l.c(r2, r3, r6);	 Catch:{ all -> 0x0074 }
        if (r1 == 0) goto L_0x0091;
    L_0x0081:
        r6 = r1.inTransaction();	 Catch:{ Exception -> 0x008b }
        if (r6 == 0) goto L_0x0091;
    L_0x0087:
        r1.endTransaction();	 Catch:{ Exception -> 0x008b }
        goto L_0x0091;
    L_0x008b:
        r6 = move-exception;
        r1 = "Helpshift_SessionDB";
        r2 = "Error removing sessions inside finally block, ";
        goto L_0x006b;
    L_0x0091:
        monitor-exit(r0);	 Catch:{ all -> 0x009f }
        return;
    L_0x0093:
        if (r2 == 0) goto L_0x00a9;
    L_0x0095:
        r1 = r2.inTransaction();	 Catch:{ Exception -> 0x00a1 }
        if (r1 == 0) goto L_0x00a9;
    L_0x009b:
        r2.endTransaction();	 Catch:{ Exception -> 0x00a1 }
        goto L_0x00a9;
    L_0x009f:
        r6 = move-exception;
        goto L_0x00aa;
    L_0x00a1:
        r1 = move-exception;
        r2 = "Helpshift_SessionDB";
        r3 = "Error removing sessions inside finally block, ";
        com.helpshift.util.l.c(r2, r3, r1);	 Catch:{ all -> 0x009f }
    L_0x00a9:
        throw r6;	 Catch:{ all -> 0x009f }
    L_0x00aa:
        monitor-exit(r0);	 Catch:{ all -> 0x009f }
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.l.j.a(java.lang.String[]):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0064 A:{Catch:{ all -> 0x0068 }} */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005d A:{SYNTHETIC, Splitter:B:24:0x005d} */
    public java.util.ArrayList<com.helpshift.campaigns.models.f> a(java.lang.Integer r14) {
        /*
        r13 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = r13.a;
        monitor-enter(r1);
        r2 = 0;
        r3 = r13.a;	 Catch:{ Exception -> 0x0053 }
        r4 = r3.getReadableDatabase();	 Catch:{ Exception -> 0x0053 }
        r7 = "sync_status=? AND end_time>?";
        r3 = 2;
        r8 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0053 }
        r14 = java.lang.String.valueOf(r14);	 Catch:{ Exception -> 0x0053 }
        r3 = 0;
        r8[r3] = r14;	 Catch:{ Exception -> 0x0053 }
        r14 = 1;
        r3 = java.lang.String.valueOf(r3);	 Catch:{ Exception -> 0x0053 }
        r8[r14] = r3;	 Catch:{ Exception -> 0x0053 }
        r5 = "sessions";
        r6 = 0;
        r9 = 0;
        r10 = 0;
        r11 = 0;
        r14 = r4.query(r5, r6, r7, r8, r9, r10, r11);	 Catch:{ Exception -> 0x0053 }
        r2 = r14.moveToFirst();	 Catch:{ Exception -> 0x004c, all -> 0x0049 }
        if (r2 == 0) goto L_0x0043;
    L_0x0032:
        r2 = r14.isAfterLast();	 Catch:{ Exception -> 0x004c, all -> 0x0049 }
        if (r2 != 0) goto L_0x0043;
    L_0x0038:
        r2 = r13.a(r14);	 Catch:{ Exception -> 0x004c, all -> 0x0049 }
        r0.add(r2);	 Catch:{ Exception -> 0x004c, all -> 0x0049 }
        r14.moveToNext();	 Catch:{ Exception -> 0x004c, all -> 0x0049 }
        goto L_0x0032;
    L_0x0043:
        if (r14 == 0) goto L_0x0060;
    L_0x0045:
        r14.close();	 Catch:{ all -> 0x0068 }
        goto L_0x0060;
    L_0x0049:
        r0 = move-exception;
        r2 = r14;
        goto L_0x0062;
    L_0x004c:
        r2 = move-exception;
        r12 = r2;
        r2 = r14;
        r14 = r12;
        goto L_0x0054;
    L_0x0051:
        r0 = move-exception;
        goto L_0x0062;
    L_0x0053:
        r14 = move-exception;
    L_0x0054:
        r3 = "Helpshift_SessionDB";
        r4 = "Error getting all sessions";
        com.helpshift.util.l.c(r3, r4, r14);	 Catch:{ all -> 0x0051 }
        if (r2 == 0) goto L_0x0060;
    L_0x005d:
        r2.close();	 Catch:{ all -> 0x0068 }
    L_0x0060:
        monitor-exit(r1);	 Catch:{ all -> 0x0068 }
        return r0;
    L_0x0062:
        if (r2 == 0) goto L_0x006a;
    L_0x0064:
        r2.close();	 Catch:{ all -> 0x0068 }
        goto L_0x006a;
    L_0x0068:
        r14 = move-exception;
        goto L_0x006b;
    L_0x006a:
        throw r0;	 Catch:{ all -> 0x0068 }
    L_0x006b:
        monitor-exit(r1);	 Catch:{ all -> 0x0068 }
        throw r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.l.j.a(java.lang.Integer):java.util.ArrayList");
    }

    public int a() {
        int delete;
        synchronized (this.a) {
            try {
                delete = this.a.getWritableDatabase().delete("sessions", "end_time=0", null);
            } catch (Exception e) {
                l.c("Helpshift_SessionDB", "Error cleaning up invalid sessions", e);
                delete = 0;
            }
        }
        return delete;
    }

    private ContentValues c(f fVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("identifier", fVar.a);
        contentValues.put("device_identifier", fVar.b);
        contentValues.put("user_identifier", fVar.c);
        contentValues.put("start_time", Long.valueOf(fVar.d));
        String str = "end_time";
        long j = 0;
        if (fVar.e > 0) {
            j = fVar.e;
        }
        contentValues.put(str, Long.valueOf(j));
        try {
            contentValues.put("durations", c.a(fVar.f));
        } catch (IOException unused) {
            contentValues.put("durations", "");
        }
        contentValues.put("sync_status", fVar.g);
        contentValues.put("extras", "");
        return contentValues;
    }

    private f a(Cursor cursor) {
        g a = new g(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getLong(3)).a(cursor.getLong(4)).a(Integer.valueOf(cursor.getInt(6)));
        try {
            a = a.a((ArrayList) c.a(cursor.getBlob(5)));
        } catch (IOException e) {
            a = a.a(null);
            l.c("Helpshift_SessionDB", "IO Exception in retrieving session duration :", e);
        } catch (ClassNotFoundException e2) {
            a = a.a(null);
            l.c("Helpshift_SessionDB", "Class not found Exception in retrieving session duration :", e2);
        } catch (ClassCastException e3) {
            a = a.a(null);
            l.c("Helpshift_SessionDB", "Class cast Exception in retrieving session duration :", e3);
        }
        return a.a();
    }
}
