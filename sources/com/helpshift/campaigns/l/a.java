package com.helpshift.campaigns.l;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.helpshift.campaigns.i.f;
import com.helpshift.campaigns.models.b;
import com.helpshift.util.c;
import com.helpshift.util.l;
import com.helpshift.util.o;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class a implements d {
    private final b a = new b(o.b());
    private ConcurrentLinkedQueue<f> b = new ConcurrentLinkedQueue();

    public void a(b bVar) {
        if (bVar != null && !TextUtils.isEmpty(bVar.k()) && !TextUtils.isEmpty(bVar.a) && !TextUtils.isEmpty(bVar.j()) && !TextUtils.isEmpty(bVar.i()) && !TextUtils.isEmpty(bVar.c)) {
            synchronized (this.a) {
                int i = 1;
                try {
                    SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                    if (!com.helpshift.util.f.a(writableDatabase, "campaigns", "identifier=?", new String[]{bVar.k()})) {
                        writableDatabase.insert("campaigns", null, b(bVar));
                    }
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception in adding campaign with id ");
                    stringBuilder.append(bVar.k());
                    l.c("Helpshift_CampDBStore", stringBuilder.toString(), e);
                    i = 0;
                }
                if (i != 0) {
                    Iterator it = this.b.iterator();
                    while (it.hasNext()) {
                        ((f) it.next()).a(bVar);
                    }
                }
            }
        }
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.a) {
                int i = 1;
                try {
                    SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                    String str3 = "identifier=?";
                    String[] strArr = new String[]{str};
                    if (com.helpshift.util.f.a(writableDatabase, "campaigns", str3, strArr)) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("icon_image_file_path", str2);
                        writableDatabase.update("campaigns", contentValues, str3, strArr);
                    }
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception in updating icon image path for ");
                    stringBuilder.append(str);
                    l.c("Helpshift_CampDBStore", stringBuilder.toString(), e);
                    i = 0;
                }
                if (i != 0) {
                    Iterator it = this.b.iterator();
                    while (it.hasNext()) {
                        ((f) it.next()).a(str);
                    }
                }
            }
        }
    }

    public void b(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.a) {
                int i = 1;
                try {
                    SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                    String str3 = "identifier=?";
                    String[] strArr = new String[]{str};
                    if (com.helpshift.util.f.a(writableDatabase, "campaigns", str3, strArr)) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("cover_image_file_path", str2);
                        writableDatabase.update("campaigns", contentValues, str3, strArr);
                    }
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception in updating cover image path for ");
                    stringBuilder.append(str);
                    l.c("Helpshift_CampDBStore", stringBuilder.toString(), e);
                    i = 0;
                }
                if (i != 0) {
                    Iterator it = this.b.iterator();
                    while (it.hasNext()) {
                        ((f) it.next()).b(str);
                    }
                }
            }
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.a) {
                int i = 0;
                try {
                    SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                    String str2 = "identifier=?";
                    String[] strArr = new String[]{str};
                    if (com.helpshift.util.f.a(writableDatabase, "campaigns", str2, strArr)) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("read_status", Integer.valueOf(1));
                        writableDatabase.update("campaigns", contentValues, str2, strArr);
                    }
                    i = 1;
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception in marking campaign as read for id : ");
                    stringBuilder.append(str);
                    l.c("Helpshift_CampDBStore", stringBuilder.toString(), e);
                }
                if (i != 0) {
                    Iterator it = this.b.iterator();
                    while (it.hasNext()) {
                        ((f) it.next()).d(str);
                    }
                }
            }
        }
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.a) {
                int i = 0;
                try {
                    SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                    String str2 = "identifier=?";
                    String[] strArr = new String[]{str};
                    if (com.helpshift.util.f.a(writableDatabase, "campaigns", str2, strArr)) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("seen_status", Integer.valueOf(1));
                        contentValues.put("read_status", Integer.valueOf(1));
                        writableDatabase.update("campaigns", contentValues, str2, strArr);
                    }
                    i = 1;
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception in marking campaign as read for id : ");
                    stringBuilder.append(str);
                    l.c("Helpshift_CampDBStore", stringBuilder.toString(), e);
                }
                if (i != 0) {
                    Iterator it = this.b.iterator();
                    while (it.hasNext()) {
                        ((f) it.next()).e(str);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0065 A:{SYNTHETIC, Splitter:B:32:0x0065} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004d A:{ExcHandler: all (r1_7 'th' java.lang.Throwable), Splitter:B:7:0x0024} */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0065 A:{SYNTHETIC, Splitter:B:32:0x0065} */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x006d A:{Catch:{ all -> 0x0071 }} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:22:0x004d, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:23:0x004e, code skipped:
            r11 = r1;
            r1 = r13;
            r13 = r11;
     */
    /* JADX WARNING: Missing block: B:24:0x0052, code skipped:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:25:0x0053, code skipped:
            r1 = r13;
            r13 = r2;
            r2 = null;
     */
    /* JADX WARNING: Missing block: B:33:?, code skipped:
            r1.close();
     */
    /* JADX WARNING: Missing block: B:38:0x006d, code skipped:
            r1.close();
     */
    public java.util.List<com.helpshift.campaigns.models.b> c(java.lang.String r13) {
        /*
        r12 = this;
        r0 = android.text.TextUtils.isEmpty(r13);
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = r12.a;
        monitor-enter(r0);
        r2 = r12.a;	 Catch:{ Exception -> 0x005a }
        r3 = r2.getReadableDatabase();	 Catch:{ Exception -> 0x005a }
        r6 = "user_identifier=?";
        r2 = 1;
        r7 = new java.lang.String[r2];	 Catch:{ Exception -> 0x005a }
        r2 = 0;
        r7[r2] = r13;	 Catch:{ Exception -> 0x005a }
        r10 = "created_at DESC";
        r4 = "campaigns";
        r5 = 0;
        r8 = 0;
        r9 = 0;
        r13 = r3.query(r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Exception -> 0x005a }
        r2 = r13.moveToFirst();	 Catch:{ Exception -> 0x0052, all -> 0x004d }
        if (r2 == 0) goto L_0x0047;
    L_0x002a:
        r2 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0052, all -> 0x004d }
        r2.<init>();	 Catch:{ Exception -> 0x0052, all -> 0x004d }
    L_0x002f:
        r1 = r13.isAfterLast();	 Catch:{ Exception -> 0x0042, all -> 0x004d }
        if (r1 != 0) goto L_0x0040;
    L_0x0035:
        r1 = r12.a(r13);	 Catch:{ Exception -> 0x0042, all -> 0x004d }
        r2.add(r1);	 Catch:{ Exception -> 0x0042, all -> 0x004d }
        r13.moveToNext();	 Catch:{ Exception -> 0x0042, all -> 0x004d }
        goto L_0x002f;
    L_0x0040:
        r1 = r2;
        goto L_0x0047;
    L_0x0042:
        r1 = move-exception;
        r11 = r1;
        r1 = r13;
        r13 = r11;
        goto L_0x005c;
    L_0x0047:
        if (r13 == 0) goto L_0x0069;
    L_0x0049:
        r13.close();	 Catch:{ all -> 0x0071 }
        goto L_0x0069;
    L_0x004d:
        r1 = move-exception;
        r11 = r1;
        r1 = r13;
        r13 = r11;
        goto L_0x006b;
    L_0x0052:
        r2 = move-exception;
        r11 = r1;
        r1 = r13;
        r13 = r2;
        r2 = r11;
        goto L_0x005c;
    L_0x0058:
        r13 = move-exception;
        goto L_0x006b;
    L_0x005a:
        r13 = move-exception;
        r2 = r1;
    L_0x005c:
        r3 = "Helpshift_CampDBStore";
        r4 = "Exception in retrieving all the campaigns ";
        com.helpshift.util.l.c(r3, r4, r13);	 Catch:{ all -> 0x0058 }
        if (r1 == 0) goto L_0x0068;
    L_0x0065:
        r1.close();	 Catch:{ all -> 0x0071 }
    L_0x0068:
        r1 = r2;
    L_0x0069:
        monitor-exit(r0);	 Catch:{ all -> 0x0071 }
        return r1;
    L_0x006b:
        if (r1 == 0) goto L_0x0073;
    L_0x006d:
        r1.close();	 Catch:{ all -> 0x0071 }
        goto L_0x0073;
    L_0x0071:
        r13 = move-exception;
        goto L_0x0074;
    L_0x0073:
        throw r13;	 Catch:{ all -> 0x0071 }
    L_0x0074:
        monitor-exit(r0);	 Catch:{ all -> 0x0071 }
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.l.a.c(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0059 A:{Catch:{ all -> 0x0056, all -> 0x005d }} */
    /* JADX WARNING: Missing block: B:12:0x002e, code skipped:
            if (r2 != null) goto L_0x0030;
     */
    /* JADX WARNING: Missing block: B:14:?, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:22:0x0051, code skipped:
            if (r2 != null) goto L_0x0030;
     */
    public com.helpshift.campaigns.models.b d(java.lang.String r12) {
        /*
        r11 = this;
        r0 = android.text.TextUtils.isEmpty(r12);
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = r11.a;
        monitor-enter(r0);
        r2 = r11.a;	 Catch:{ Exception -> 0x0039, all -> 0x0036 }
        r3 = r2.getReadableDatabase();	 Catch:{ Exception -> 0x0039, all -> 0x0036 }
        r6 = "identifier=?";
        r2 = 1;
        r7 = new java.lang.String[r2];	 Catch:{ Exception -> 0x0039, all -> 0x0036 }
        r2 = 0;
        r7[r2] = r12;	 Catch:{ Exception -> 0x0039, all -> 0x0036 }
        r4 = "campaigns";
        r5 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r2 = r3.query(r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Exception -> 0x0039, all -> 0x0036 }
        r3 = r2.moveToFirst();	 Catch:{ Exception -> 0x0034 }
        if (r3 == 0) goto L_0x002e;
    L_0x0029:
        r3 = r11.a(r2);	 Catch:{ Exception -> 0x0034 }
        r1 = r3;
    L_0x002e:
        if (r2 == 0) goto L_0x0054;
    L_0x0030:
        r2.close();	 Catch:{ all -> 0x005d }
        goto L_0x0054;
    L_0x0034:
        r3 = move-exception;
        goto L_0x003b;
    L_0x0036:
        r12 = move-exception;
        r2 = r1;
        goto L_0x0057;
    L_0x0039:
        r3 = move-exception;
        r2 = r1;
    L_0x003b:
        r4 = "Helpshift_CampDBStore";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0056 }
        r5.<init>();	 Catch:{ all -> 0x0056 }
        r6 = "Exception while fetching campaign for id : ";
        r5.append(r6);	 Catch:{ all -> 0x0056 }
        r5.append(r12);	 Catch:{ all -> 0x0056 }
        r12 = r5.toString();	 Catch:{ all -> 0x0056 }
        com.helpshift.util.l.c(r4, r12, r3);	 Catch:{ all -> 0x0056 }
        if (r2 == 0) goto L_0x0054;
    L_0x0053:
        goto L_0x0030;
    L_0x0054:
        monitor-exit(r0);	 Catch:{ all -> 0x005d }
        return r1;
    L_0x0056:
        r12 = move-exception;
    L_0x0057:
        if (r2 == 0) goto L_0x005f;
    L_0x0059:
        r2.close();	 Catch:{ all -> 0x005d }
        goto L_0x005f;
    L_0x005d:
        r12 = move-exception;
        goto L_0x0060;
    L_0x005f:
        throw r12;	 Catch:{ all -> 0x005d }
    L_0x0060:
        monitor-exit(r0);	 Catch:{ all -> 0x005d }
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.l.a.d(java.lang.String):com.helpshift.campaigns.models.b");
    }

    public void e(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.a) {
                int i = 1;
                try {
                    String str2 = "campaigns";
                    this.a.getWritableDatabase().delete(str2, "identifier=?", new String[]{str});
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Exception in deleting campaign for id ");
                    stringBuilder.append(str);
                    l.c("Helpshift_CampDBStore", stringBuilder.toString(), e);
                    i = 0;
                }
                if (i != 0) {
                    Iterator it = this.b.iterator();
                    while (it.hasNext()) {
                        ((f) it.next()).c(str);
                    }
                }
            }
        }
    }

    public void a(String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            synchronized (this.a) {
                Object obj;
                try {
                    SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("identifier in (");
                    stringBuilder.append(com.helpshift.util.f.a(strArr.length));
                    stringBuilder.append(")");
                    writableDatabase.delete("campaigns", stringBuilder.toString(), strArr);
                    obj = 1;
                } catch (Exception e) {
                    l.c("Helpshift_CampDBStore", "Exception in deleting campaigns ", e);
                    obj = null;
                }
                if (obj != null) {
                    Iterator it = this.b.iterator();
                    while (it.hasNext()) {
                        f fVar = (f) it.next();
                        for (String c : strArr) {
                            fVar.c(c);
                        }
                    }
                }
            }
        }
    }

    public void a(f fVar) {
        if (fVar != null) {
            this.b.add(fVar);
        }
    }

    public void b(f fVar) {
        this.b.remove(fVar);
    }

    private ContentValues b(b bVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("identifier", bVar.k());
        contentValues.put("user_identifier", bVar.a);
        contentValues.put("title", bVar.j());
        contentValues.put("body", bVar.i());
        contentValues.put("cover_image_url", bVar.b);
        contentValues.put("cover_image_file_path", bVar.d);
        contentValues.put("icon_image_url", bVar.c);
        contentValues.put("icon_image_file_path", bVar.e);
        contentValues.put(FacebookAdapter.KEY_BACKGROUND_COLOR, bVar.h());
        contentValues.put("title_color", bVar.g());
        contentValues.put("text_color", bVar.f());
        try {
            contentValues.put("actions", c.a(bVar.f));
        } catch (IOException unused) {
            contentValues.put("actions", "");
        }
        try {
            contentValues.put("messages", c.a(bVar.g));
        } catch (IOException unused2) {
            contentValues.put("messages", "");
        }
        contentValues.put("read_status", Integer.valueOf(bVar.e()));
        contentValues.put("seen_status", Integer.valueOf(bVar.d()));
        contentValues.put("created_at", Long.valueOf(bVar.a()));
        contentValues.put("expiry_time_stamp", Long.valueOf(bVar.b()));
        contentValues.put("extra_data", "");
        return contentValues;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00f9  */
    private com.helpshift.campaigns.models.b a(android.database.Cursor r27) {
        /*
        r26 = this;
        r1 = r27;
        r2 = 0;
        r3 = "actions";
        r3 = r1.getColumnIndex(r3);	 Catch:{ IOException -> 0x002a, ClassNotFoundException -> 0x0020, ClassCastException -> 0x0016 }
        r3 = r1.getBlob(r3);	 Catch:{ IOException -> 0x002a, ClassNotFoundException -> 0x0020, ClassCastException -> 0x0016 }
        r3 = com.helpshift.util.c.a(r3);	 Catch:{ IOException -> 0x002a, ClassNotFoundException -> 0x0020, ClassCastException -> 0x0016 }
        r3 = (java.util.ArrayList) r3;	 Catch:{ IOException -> 0x002a, ClassNotFoundException -> 0x0020, ClassCastException -> 0x0016 }
        r24 = r3;
        goto L_0x0035;
    L_0x0016:
        r0 = move-exception;
        r3 = r0;
        r4 = "Helpshift_CampDBStore";
        r5 = "Class cast Exception in retrieving campaign actions :";
        com.helpshift.util.l.c(r4, r5, r3);
        goto L_0x0033;
    L_0x0020:
        r0 = move-exception;
        r3 = r0;
        r4 = "Helpshift_CampDBStore";
        r5 = "Class not found exception in retrieving campaign actions :";
        com.helpshift.util.l.c(r4, r5, r3);
        goto L_0x0033;
    L_0x002a:
        r0 = move-exception;
        r3 = r0;
        r4 = "Helpshift_CampDBStore";
        r5 = "IO exception in retrieving campaign actions :";
        com.helpshift.util.l.c(r4, r5, r3);
    L_0x0033:
        r24 = r2;
    L_0x0035:
        r3 = "messages";
        r3 = r1.getColumnIndex(r3);	 Catch:{ IOException -> 0x005c, ClassNotFoundException -> 0x0052, ClassCastException -> 0x0048 }
        r3 = r1.getBlob(r3);	 Catch:{ IOException -> 0x005c, ClassNotFoundException -> 0x0052, ClassCastException -> 0x0048 }
        r3 = com.helpshift.util.c.a(r3);	 Catch:{ IOException -> 0x005c, ClassNotFoundException -> 0x0052, ClassCastException -> 0x0048 }
        r3 = (java.util.ArrayList) r3;	 Catch:{ IOException -> 0x005c, ClassNotFoundException -> 0x0052, ClassCastException -> 0x0048 }
        r25 = r3;
        goto L_0x0067;
    L_0x0048:
        r0 = move-exception;
        r3 = r0;
        r4 = "Helpshift_CampDBStore";
        r5 = "Class cast Exception in retrieving campaign messages :";
        com.helpshift.util.l.c(r4, r5, r3);
        goto L_0x0065;
    L_0x0052:
        r0 = move-exception;
        r3 = r0;
        r4 = "Helpshift_CampDBStore";
        r5 = "Class not found exception in retrieving campaign messages :";
        com.helpshift.util.l.c(r4, r5, r3);
        goto L_0x0065;
    L_0x005c:
        r0 = move-exception;
        r3 = r0;
        r4 = "Helpshift_CampDBStore";
        r5 = "IO exception in retrieving campaign messages :";
        com.helpshift.util.l.c(r4, r5, r3);
    L_0x0065:
        r25 = r2;
    L_0x0067:
        r2 = new com.helpshift.campaigns.models.b;
        r3 = "identifier";
        r3 = r1.getColumnIndex(r3);
        r7 = r1.getString(r3);
        r3 = "user_identifier";
        r3 = r1.getColumnIndex(r3);
        r8 = r1.getString(r3);
        r3 = "title";
        r3 = r1.getColumnIndex(r3);
        r9 = r1.getString(r3);
        r3 = "body";
        r3 = r1.getColumnIndex(r3);
        r10 = r1.getString(r3);
        r3 = "cover_image_url";
        r3 = r1.getColumnIndex(r3);
        r11 = r1.getString(r3);
        r3 = "cover_image_file_path";
        r3 = r1.getColumnIndex(r3);
        r12 = r1.getString(r3);
        r3 = "icon_image_url";
        r3 = r1.getColumnIndex(r3);
        r13 = r1.getString(r3);
        r3 = "icon_image_file_path";
        r3 = r1.getColumnIndex(r3);
        r14 = r1.getString(r3);
        r3 = "background_color";
        r3 = r1.getColumnIndex(r3);
        r15 = r1.getString(r3);
        r3 = "title_color";
        r3 = r1.getColumnIndex(r3);
        r16 = r1.getString(r3);
        r3 = "text_color";
        r3 = r1.getColumnIndex(r3);
        r17 = r1.getString(r3);
        r3 = "read_status";
        r3 = r1.getColumnIndex(r3);
        r3 = r1.getInt(r3);
        r4 = 0;
        r5 = 1;
        if (r3 != r5) goto L_0x00e8;
    L_0x00e5:
        r18 = r5;
        goto L_0x00ea;
    L_0x00e8:
        r18 = r4;
    L_0x00ea:
        r3 = "seen_status";
        r3 = r1.getColumnIndex(r3);
        r3 = r1.getInt(r3);
        if (r3 != r5) goto L_0x00f9;
    L_0x00f6:
        r19 = r5;
        goto L_0x00fb;
    L_0x00f9:
        r19 = r4;
    L_0x00fb:
        r3 = "created_at";
        r3 = r1.getColumnIndex(r3);
        r20 = r1.getLong(r3);
        r3 = "expiry_time_stamp";
        r3 = r1.getColumnIndex(r3);
        r22 = r1.getLong(r3);
        r6 = r2;
        r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r22, r24, r25);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.campaigns.l.a.a(android.database.Cursor):com.helpshift.campaigns.models.b");
    }
}
