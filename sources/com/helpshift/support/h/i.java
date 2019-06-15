package com.helpshift.support.h;

import android.content.ContentValues;
import android.database.Cursor;
import com.helpshift.support.FaqTagFilter;
import com.helpshift.support.Section;
import com.helpshift.util.l;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class i implements h {
    private final c a = c.a();
    private b b = new d();

    private static Section a(Cursor cursor) {
        return new Section(cursor.getLong(0), cursor.getString(1), cursor.getString(3), cursor.getString(2));
    }

    private static ContentValues a(JSONObject jSONObject) throws JSONException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", jSONObject.getString("title"));
        contentValues.put("publish_id", jSONObject.getString("publish_id"));
        contentValues.put("section_id", jSONObject.getString("id"));
        return contentValues;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:14:0x003a, B:24:0x0050] */
    /* JADX WARNING: Missing block: B:18:0x0044, code skipped:
            r8 = e;
     */
    /* JADX WARNING: Missing block: B:20:?, code skipped:
            r1 = "HelpShiftDebug";
            r2 = "Error in storeSections inside finally block";
     */
    /* JADX WARNING: Missing block: B:36:0x006b, code skipped:
            if (r1 != null) goto L_0x006d;
     */
    /* JADX WARNING: Missing block: B:39:0x0071, code skipped:
            if (r1.inTransaction() != false) goto L_0x0073;
     */
    /* JADX WARNING: Missing block: B:40:0x0073, code skipped:
            r1.endTransaction();
     */
    /* JADX WARNING: Missing block: B:41:0x0077, code skipped:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:43:?, code skipped:
            com.helpshift.util.l.c("HelpShiftDebug", "Error in storeSections inside finally block", r1);
     */
    public void a(org.json.JSONArray r8) {
        /*
        r7 = this;
        r0 = r7.a;
        monitor-enter(r0);
        r1 = r7.a;	 Catch:{ all -> 0x0080 }
        r1 = r1.getWritableDatabase();	 Catch:{ all -> 0x0080 }
        r1.beginTransaction();	 Catch:{ JSONException -> 0x004f }
        r2 = 0;
    L_0x000d:
        r3 = r8.length();	 Catch:{ JSONException -> 0x004f }
        if (r2 >= r3) goto L_0x0035;
    L_0x0013:
        r3 = r8.getJSONObject(r2);	 Catch:{ JSONException -> 0x004f }
        r4 = "sections";
        r5 = 0;
        r6 = a(r3);	 Catch:{ JSONException -> 0x004f }
        r1.insert(r4, r5, r6);	 Catch:{ JSONException -> 0x004f }
        r4 = "faqs";
        r4 = r3.optJSONArray(r4);	 Catch:{ JSONException -> 0x004f }
        if (r4 == 0) goto L_0x0032;
    L_0x0029:
        r5 = "publish_id";
        r3 = r3.getString(r5);	 Catch:{ JSONException -> 0x004f }
        com.helpshift.support.h.d.a(r1, r3, r4);	 Catch:{ JSONException -> 0x004f }
    L_0x0032:
        r2 = r2 + 1;
        goto L_0x000d;
    L_0x0035:
        r1.setTransactionSuccessful();	 Catch:{ JSONException -> 0x004f }
        if (r1 == 0) goto L_0x0069;
    L_0x003a:
        r8 = r1.inTransaction();	 Catch:{ Exception -> 0x0044 }
        if (r8 == 0) goto L_0x0069;
    L_0x0040:
        r1.endTransaction();	 Catch:{ Exception -> 0x0044 }
        goto L_0x0069;
    L_0x0044:
        r8 = move-exception;
        r1 = "HelpShiftDebug";
        r2 = "Error in storeSections inside finally block";
    L_0x0049:
        com.helpshift.util.l.c(r1, r2, r8);	 Catch:{ all -> 0x0080 }
        goto L_0x0069;
    L_0x004d:
        r8 = move-exception;
        goto L_0x006b;
    L_0x004f:
        r8 = move-exception;
        r2 = "HelpShiftDebug";
        r3 = "Error in storeSections";
        com.helpshift.util.l.c(r2, r3, r8);	 Catch:{ all -> 0x004d }
        if (r1 == 0) goto L_0x0069;
    L_0x0059:
        r8 = r1.inTransaction();	 Catch:{ Exception -> 0x0063 }
        if (r8 == 0) goto L_0x0069;
    L_0x005f:
        r1.endTransaction();	 Catch:{ Exception -> 0x0063 }
        goto L_0x0069;
    L_0x0063:
        r8 = move-exception;
        r1 = "HelpShiftDebug";
        r2 = "Error in storeSections inside finally block";
        goto L_0x0049;
    L_0x0069:
        monitor-exit(r0);	 Catch:{ all -> 0x0080 }
        return;
    L_0x006b:
        if (r1 == 0) goto L_0x007f;
    L_0x006d:
        r2 = r1.inTransaction();	 Catch:{ Exception -> 0x0077 }
        if (r2 == 0) goto L_0x007f;
    L_0x0073:
        r1.endTransaction();	 Catch:{ Exception -> 0x0077 }
        goto L_0x007f;
    L_0x0077:
        r1 = move-exception;
        r2 = "HelpShiftDebug";
        r3 = "Error in storeSections inside finally block";
        com.helpshift.util.l.c(r2, r3, r1);	 Catch:{ all -> 0x0080 }
    L_0x007f:
        throw r8;	 Catch:{ all -> 0x0080 }
    L_0x0080:
        r8 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0080 }
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.h.i.a(org.json.JSONArray):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0053 A:{Catch:{ all -> 0x004d, all -> 0x0057 }} */
    /* JADX WARNING: Missing block: B:13:0x0035, code skipped:
            if (r13 != null) goto L_0x0037;
     */
    /* JADX WARNING: Missing block: B:15:?, code skipped:
            r13.close();
     */
    /* JADX WARNING: Missing block: B:22:0x0048, code skipped:
            if (r13 != null) goto L_0x0037;
     */
    public com.helpshift.support.Section a(java.lang.String r13) {
        /*
        r12 = this;
        if (r13 == 0) goto L_0x005c;
    L_0x0002:
        r0 = "";
        r0 = r13.equals(r0);
        if (r0 == 0) goto L_0x000b;
    L_0x000a:
        goto L_0x005c;
    L_0x000b:
        r0 = r12.a;
        monitor-enter(r0);
        r1 = 0;
        r2 = r12.a;	 Catch:{ Exception -> 0x003f, all -> 0x003d }
        r3 = r2.getReadableDatabase();	 Catch:{ Exception -> 0x003f, all -> 0x003d }
        r4 = "sections";
        r5 = 0;
        r6 = "publish_id = ?";
        r2 = 1;
        r7 = new java.lang.String[r2];	 Catch:{ Exception -> 0x003f, all -> 0x003d }
        r2 = 0;
        r7[r2] = r13;	 Catch:{ Exception -> 0x003f, all -> 0x003d }
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r13 = r3.query(r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Exception -> 0x003f, all -> 0x003d }
        r13.moveToFirst();	 Catch:{ Exception -> 0x003b }
        r2 = r13.isAfterLast();	 Catch:{ Exception -> 0x003b }
        if (r2 != 0) goto L_0x0035;
    L_0x0030:
        r2 = a(r13);	 Catch:{ Exception -> 0x003b }
        r1 = r2;
    L_0x0035:
        if (r13 == 0) goto L_0x004b;
    L_0x0037:
        r13.close();	 Catch:{ all -> 0x0057 }
        goto L_0x004b;
    L_0x003b:
        r2 = move-exception;
        goto L_0x0041;
    L_0x003d:
        r13 = move-exception;
        goto L_0x0051;
    L_0x003f:
        r2 = move-exception;
        r13 = r1;
    L_0x0041:
        r3 = "HelpShiftDebug";
        r4 = "Error in getSection";
        com.helpshift.util.l.c(r3, r4, r2);	 Catch:{ all -> 0x004d }
        if (r13 == 0) goto L_0x004b;
    L_0x004a:
        goto L_0x0037;
    L_0x004b:
        monitor-exit(r0);	 Catch:{ all -> 0x0057 }
        return r1;
    L_0x004d:
        r1 = move-exception;
        r11 = r1;
        r1 = r13;
        r13 = r11;
    L_0x0051:
        if (r1 == 0) goto L_0x0059;
    L_0x0053:
        r1.close();	 Catch:{ all -> 0x0057 }
        goto L_0x0059;
    L_0x0057:
        r13 = move-exception;
        goto L_0x005a;
    L_0x0059:
        throw r13;	 Catch:{ all -> 0x0057 }
    L_0x005a:
        monitor-exit(r0);	 Catch:{ all -> 0x0057 }
        throw r13;
    L_0x005c:
        r13 = new com.helpshift.support.Section;
        r13.<init>();
        return r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.h.i.a(java.lang.String):com.helpshift.support.Section");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x004d A:{Catch:{ all -> 0x004a, all -> 0x0051 }} */
    /* JADX WARNING: Missing block: B:11:0x002f, code skipped:
            if (r3 != null) goto L_0x0031;
     */
    /* JADX WARNING: Missing block: B:13:?, code skipped:
            r3.close();
     */
    /* JADX WARNING: Missing block: B:21:0x0045, code skipped:
            if (r3 != null) goto L_0x0031;
     */
    public java.util.List<com.helpshift.support.Section> a() {
        /*
        r13 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = r13.a;
        monitor-enter(r1);
        r2 = 0;
        r3 = r13.a;	 Catch:{ Exception -> 0x003a, all -> 0x0037 }
        r4 = r3.getReadableDatabase();	 Catch:{ Exception -> 0x003a, all -> 0x0037 }
        r5 = "sections";
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r11 = 0;
        r3 = r4.query(r5, r6, r7, r8, r9, r10, r11);	 Catch:{ Exception -> 0x003a, all -> 0x0037 }
        r3.moveToFirst();	 Catch:{ Exception -> 0x0035 }
    L_0x001e:
        r2 = r3.isAfterLast();	 Catch:{ Exception -> 0x0035 }
        if (r2 != 0) goto L_0x002f;
    L_0x0024:
        r2 = a(r3);	 Catch:{ Exception -> 0x0035 }
        r0.add(r2);	 Catch:{ Exception -> 0x0035 }
        r3.moveToNext();	 Catch:{ Exception -> 0x0035 }
        goto L_0x001e;
    L_0x002f:
        if (r3 == 0) goto L_0x0048;
    L_0x0031:
        r3.close();	 Catch:{ all -> 0x0051 }
        goto L_0x0048;
    L_0x0035:
        r2 = move-exception;
        goto L_0x003e;
    L_0x0037:
        r0 = move-exception;
        r3 = r2;
        goto L_0x004b;
    L_0x003a:
        r3 = move-exception;
        r12 = r3;
        r3 = r2;
        r2 = r12;
    L_0x003e:
        r4 = "HelpShiftDebug";
        r5 = "Error in getAllSections";
        com.helpshift.util.l.c(r4, r5, r2);	 Catch:{ all -> 0x004a }
        if (r3 == 0) goto L_0x0048;
    L_0x0047:
        goto L_0x0031;
    L_0x0048:
        monitor-exit(r1);	 Catch:{ all -> 0x0051 }
        return r0;
    L_0x004a:
        r0 = move-exception;
    L_0x004b:
        if (r3 == 0) goto L_0x0053;
    L_0x004d:
        r3.close();	 Catch:{ all -> 0x0051 }
        goto L_0x0053;
    L_0x0051:
        r0 = move-exception;
        goto L_0x0054;
    L_0x0053:
        throw r0;	 Catch:{ all -> 0x0051 }
    L_0x0054:
        monitor-exit(r1);	 Catch:{ all -> 0x0051 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.h.i.a():java.util.List");
    }

    public List<Section> a(FaqTagFilter faqTagFilter) {
        List<Section> a = a();
        if (faqTagFilter == null) {
            return a;
        }
        ArrayList arrayList = new ArrayList();
        for (Section section : a) {
            if (!this.b.a(section.a(), faqTagFilter).isEmpty()) {
                arrayList.add(section);
            }
        }
        return arrayList;
    }

    public void b() {
        synchronized (this.a) {
            try {
                this.a.a(this.a.getWritableDatabase());
            } catch (Exception e) {
                l.c("HelpShiftDebug", "Error in clearSectionsData", e);
            }
        }
    }
}
