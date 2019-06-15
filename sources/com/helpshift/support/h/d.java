package com.helpshift.support.h;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.helpshift.support.Faq;
import com.helpshift.support.FaqTagFilter;
import com.helpshift.util.f;
import com.helpshift.util.j;
import com.helpshift.util.l;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d implements b {
    private final c a = c.a();

    public static void a(SQLiteDatabase sQLiteDatabase, String str, JSONArray jSONArray) {
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                sQLiteDatabase.insert("faqs", null, a(str, jSONArray.getJSONObject(i)));
                i++;
            } catch (JSONException e) {
                l.a("HelpShiftDebug", "addFaqsUnsafe", e);
                return;
            }
        }
    }

    private static Faq a(Cursor cursor) {
        long j = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID));
        String string = cursor.getString(cursor.getColumnIndex("question_id"));
        String string2 = cursor.getString(cursor.getColumnIndex("publish_id"));
        String string3 = cursor.getString(cursor.getColumnIndex("section_id"));
        String string4 = cursor.getString(cursor.getColumnIndex("title"));
        String string5 = cursor.getString(cursor.getColumnIndex("body"));
        int i = cursor.getInt(cursor.getColumnIndex("helpful"));
        boolean z = true;
        if (cursor.getInt(cursor.getColumnIndex("rtl")) != 1) {
            z = false;
        }
        return new Faq(j, string, string2, string3, string4, string5, i, Boolean.valueOf(z), j.a(cursor.getString(cursor.getColumnIndex("tags"))), j.a(cursor.getString(cursor.getColumnIndex("c_tags"))));
    }

    private static ContentValues b(Faq faq) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("question_id", faq.a());
        contentValues.put("publish_id", faq.c);
        contentValues.put("section_id", faq.d);
        contentValues.put("title", faq.b);
        contentValues.put("body", faq.e);
        contentValues.put("helpful", Integer.valueOf(faq.f));
        contentValues.put("rtl", faq.g);
        contentValues.put("tags", String.valueOf(new JSONArray(faq.b())));
        contentValues.put("c_tags", String.valueOf(new JSONArray(faq.c())));
        return contentValues;
    }

    private static ContentValues a(String str, JSONObject jSONObject) throws JSONException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("question_id", jSONObject.getString("id"));
        contentValues.put("publish_id", jSONObject.getString("publish_id"));
        contentValues.put("section_id", str);
        contentValues.put("title", jSONObject.getString("title"));
        contentValues.put("body", jSONObject.getString("body"));
        contentValues.put("helpful", Integer.valueOf(0));
        contentValues.put("rtl", Boolean.valueOf(jSONObject.getString("is_rtl").equals("true")));
        contentValues.put("tags", (jSONObject.has("stags") ? jSONObject.optJSONArray("stags") : new JSONArray()).toString());
        contentValues.put("c_tags", (jSONObject.has("issue_tags") ? jSONObject.optJSONArray("issue_tags") : new JSONArray()).toString());
        return contentValues;
    }

    public void b() {
        synchronized (this.a) {
            try {
                this.a.a(this.a.getWritableDatabase());
            } catch (Exception e) {
                l.c("HelpShiftDebug", "Error in clearDB", e);
            }
        }
    }

    public void a(Faq faq) {
        ContentValues b = b(faq);
        String str = "question_id=?";
        String[] strArr = new String[]{faq.a()};
        synchronized (this.a) {
            try {
                SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
                if (f.a(writableDatabase, "faqs", str, strArr)) {
                    writableDatabase.update("faqs", b, str, strArr);
                } else {
                    writableDatabase.insert("faqs", null, b);
                }
            } catch (Exception e) {
                l.c("HelpShiftDebug", "Error in addFaq", e);
            }
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.a) {
                try {
                    String[] strArr = new String[]{str};
                    this.a.getWritableDatabase().delete("faqs", "publish_id=?", strArr);
                } catch (Exception e) {
                    l.c("HelpShiftDebug", "Error in removeFaq", e);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0051 A:{Catch:{ all -> 0x004b, all -> 0x0055 }} */
    /* JADX WARNING: Missing block: B:14:0x0033, code skipped:
            if (r13 != null) goto L_0x0035;
     */
    /* JADX WARNING: Missing block: B:16:?, code skipped:
            r13.close();
     */
    /* JADX WARNING: Missing block: B:23:0x0046, code skipped:
            if (r13 != null) goto L_0x0035;
     */
    public com.helpshift.support.Faq b(java.lang.String r13) {
        /*
        r12 = this;
        r0 = android.text.TextUtils.isEmpty(r13);
        if (r0 == 0) goto L_0x000c;
    L_0x0006:
        r13 = new com.helpshift.support.Faq;
        r13.<init>();
        return r13;
    L_0x000c:
        r0 = r12.a;
        monitor-enter(r0);
        r1 = 0;
        r2 = r12.a;	 Catch:{ Exception -> 0x003d, all -> 0x003b }
        r3 = r2.getReadableDatabase();	 Catch:{ Exception -> 0x003d, all -> 0x003b }
        r4 = "faqs";
        r5 = 0;
        r6 = "publish_id = ?";
        r2 = 1;
        r7 = new java.lang.String[r2];	 Catch:{ Exception -> 0x003d, all -> 0x003b }
        r2 = 0;
        r7[r2] = r13;	 Catch:{ Exception -> 0x003d, all -> 0x003b }
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r13 = r3.query(r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Exception -> 0x003d, all -> 0x003b }
        r2 = r13.moveToFirst();	 Catch:{ Exception -> 0x0039 }
        if (r2 == 0) goto L_0x0033;
    L_0x002e:
        r2 = a(r13);	 Catch:{ Exception -> 0x0039 }
        r1 = r2;
    L_0x0033:
        if (r13 == 0) goto L_0x0049;
    L_0x0035:
        r13.close();	 Catch:{ all -> 0x0055 }
        goto L_0x0049;
    L_0x0039:
        r2 = move-exception;
        goto L_0x003f;
    L_0x003b:
        r13 = move-exception;
        goto L_0x004f;
    L_0x003d:
        r2 = move-exception;
        r13 = r1;
    L_0x003f:
        r3 = "HelpShiftDebug";
        r4 = "Error in getFaq";
        com.helpshift.util.l.c(r3, r4, r2);	 Catch:{ all -> 0x004b }
        if (r13 == 0) goto L_0x0049;
    L_0x0048:
        goto L_0x0035;
    L_0x0049:
        monitor-exit(r0);	 Catch:{ all -> 0x0055 }
        return r1;
    L_0x004b:
        r1 = move-exception;
        r11 = r1;
        r1 = r13;
        r13 = r11;
    L_0x004f:
        if (r1 == 0) goto L_0x0057;
    L_0x0051:
        r1.close();	 Catch:{ all -> 0x0055 }
        goto L_0x0057;
    L_0x0055:
        r13 = move-exception;
        goto L_0x0058;
    L_0x0057:
        throw r13;	 Catch:{ all -> 0x0055 }
    L_0x0058:
        monitor-exit(r0);	 Catch:{ all -> 0x0055 }
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.h.d.b(java.lang.String):com.helpshift.support.Faq");
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0065 A:{Catch:{ all -> 0x0069 }} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005e A:{SYNTHETIC, Splitter:B:28:0x005e} */
    public java.util.List<com.helpshift.support.Faq> c(java.lang.String r14) {
        /*
        r13 = this;
        r0 = android.text.TextUtils.isEmpty(r14);
        if (r0 == 0) goto L_0x000c;
    L_0x0006:
        r14 = new java.util.ArrayList;
        r14.<init>();
        return r14;
    L_0x000c:
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = r13.a;
        monitor-enter(r1);
        r2 = 0;
        r3 = r13.a;	 Catch:{ Exception -> 0x0054 }
        r4 = r3.getReadableDatabase();	 Catch:{ Exception -> 0x0054 }
        r5 = "faqs";
        r6 = 0;
        r7 = "section_id = ?";
        r3 = 1;
        r8 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0054 }
        r3 = 0;
        r8[r3] = r14;	 Catch:{ Exception -> 0x0054 }
        r9 = 0;
        r10 = 0;
        r11 = 0;
        r14 = r4.query(r5, r6, r7, r8, r9, r10, r11);	 Catch:{ Exception -> 0x0054 }
        r2 = r14.moveToFirst();	 Catch:{ Exception -> 0x004d, all -> 0x004a }
        if (r2 == 0) goto L_0x0044;
    L_0x0033:
        r2 = r14.isAfterLast();	 Catch:{ Exception -> 0x004d, all -> 0x004a }
        if (r2 != 0) goto L_0x0044;
    L_0x0039:
        r2 = a(r14);	 Catch:{ Exception -> 0x004d, all -> 0x004a }
        r0.add(r2);	 Catch:{ Exception -> 0x004d, all -> 0x004a }
        r14.moveToNext();	 Catch:{ Exception -> 0x004d, all -> 0x004a }
        goto L_0x0033;
    L_0x0044:
        if (r14 == 0) goto L_0x0061;
    L_0x0046:
        r14.close();	 Catch:{ all -> 0x0069 }
        goto L_0x0061;
    L_0x004a:
        r0 = move-exception;
        r2 = r14;
        goto L_0x0063;
    L_0x004d:
        r2 = move-exception;
        r12 = r2;
        r2 = r14;
        r14 = r12;
        goto L_0x0055;
    L_0x0052:
        r0 = move-exception;
        goto L_0x0063;
    L_0x0054:
        r14 = move-exception;
    L_0x0055:
        r3 = "HelpShiftDebug";
        r4 = "Error in getFaqsDataForSection";
        com.helpshift.util.l.c(r3, r4, r14);	 Catch:{ all -> 0x0052 }
        if (r2 == 0) goto L_0x0061;
    L_0x005e:
        r2.close();	 Catch:{ all -> 0x0069 }
    L_0x0061:
        monitor-exit(r1);	 Catch:{ all -> 0x0069 }
        return r0;
    L_0x0063:
        if (r2 == 0) goto L_0x006b;
    L_0x0065:
        r2.close();	 Catch:{ all -> 0x0069 }
        goto L_0x006b;
    L_0x0069:
        r14 = move-exception;
        goto L_0x006c;
    L_0x006b:
        throw r0;	 Catch:{ all -> 0x0069 }
    L_0x006c:
        monitor-exit(r1);	 Catch:{ all -> 0x0069 }
        throw r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.h.d.c(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x005d A:{Catch:{ all -> 0x005a, all -> 0x0061 }} */
    /* JADX WARNING: Missing block: B:12:0x003f, code skipped:
            if (r3 != null) goto L_0x0041;
     */
    /* JADX WARNING: Missing block: B:14:?, code skipped:
            r3.close();
     */
    /* JADX WARNING: Missing block: B:22:0x0055, code skipped:
            if (r3 != null) goto L_0x0041;
     */
    public java.util.List<java.lang.String> a() {
        /*
        r13 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = r13.a;
        monitor-enter(r1);
        r2 = 0;
        r3 = 1;
        r6 = new java.lang.String[r3];	 Catch:{ Exception -> 0x004a, all -> 0x0047 }
        r3 = 0;
        r4 = "publish_id";
        r6[r3] = r4;	 Catch:{ Exception -> 0x004a, all -> 0x0047 }
        r3 = r13.a;	 Catch:{ Exception -> 0x004a, all -> 0x0047 }
        r4 = r3.getReadableDatabase();	 Catch:{ Exception -> 0x004a, all -> 0x0047 }
        r5 = "faqs";
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r11 = 0;
        r3 = r4.query(r5, r6, r7, r8, r9, r10, r11);	 Catch:{ Exception -> 0x004a, all -> 0x0047 }
        r2 = r3.moveToFirst();	 Catch:{ Exception -> 0x0045 }
        if (r2 == 0) goto L_0x003f;
    L_0x0028:
        r2 = r3.isAfterLast();	 Catch:{ Exception -> 0x0045 }
        if (r2 != 0) goto L_0x003f;
    L_0x002e:
        r2 = "publish_id";
        r2 = r3.getColumnIndex(r2);	 Catch:{ Exception -> 0x0045 }
        r2 = r3.getString(r2);	 Catch:{ Exception -> 0x0045 }
        r0.add(r2);	 Catch:{ Exception -> 0x0045 }
        r3.moveToNext();	 Catch:{ Exception -> 0x0045 }
        goto L_0x0028;
    L_0x003f:
        if (r3 == 0) goto L_0x0058;
    L_0x0041:
        r3.close();	 Catch:{ all -> 0x0061 }
        goto L_0x0058;
    L_0x0045:
        r2 = move-exception;
        goto L_0x004e;
    L_0x0047:
        r0 = move-exception;
        r3 = r2;
        goto L_0x005b;
    L_0x004a:
        r3 = move-exception;
        r12 = r3;
        r3 = r2;
        r2 = r12;
    L_0x004e:
        r4 = "HelpShiftDebug";
        r5 = "Error in getFaqsDataForSection";
        com.helpshift.util.l.c(r4, r5, r2);	 Catch:{ all -> 0x005a }
        if (r3 == 0) goto L_0x0058;
    L_0x0057:
        goto L_0x0041;
    L_0x0058:
        monitor-exit(r1);	 Catch:{ all -> 0x0061 }
        return r0;
    L_0x005a:
        r0 = move-exception;
    L_0x005b:
        if (r3 == 0) goto L_0x0063;
    L_0x005d:
        r3.close();	 Catch:{ all -> 0x0061 }
        goto L_0x0063;
    L_0x0061:
        r0 = move-exception;
        goto L_0x0064;
    L_0x0063:
        throw r0;	 Catch:{ all -> 0x0061 }
    L_0x0064:
        monitor-exit(r1);	 Catch:{ all -> 0x0061 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.h.d.a():java.util.List");
    }

    public List<Faq> a(List<Faq> list, FaqTagFilter faqTagFilter) {
        if (faqTagFilter == null) {
            return list;
        }
        String a = faqTagFilter.a();
        Object obj = -1;
        int hashCode = a.hashCode();
        if (hashCode != -1038130864) {
            if (hashCode != 3555) {
                if (hashCode != 96727) {
                    if (hashCode == 109267 && a.equals("not")) {
                        obj = 2;
                    }
                } else if (a.equals("and")) {
                    obj = null;
                }
            } else if (a.equals("or")) {
                obj = 1;
            }
        } else if (a.equals("undefined")) {
            obj = 3;
        }
        switch (obj) {
            case null:
                return b(list, faqTagFilter);
            case 1:
                return c(list, faqTagFilter);
            case 2:
                return d(list, faqTagFilter);
            case 3:
                return list;
            default:
                return list;
        }
    }

    public List<Faq> a(String str, FaqTagFilter faqTagFilter) {
        return a(c(str), faqTagFilter);
    }

    private List<Faq> b(List<Faq> list, FaqTagFilter faqTagFilter) {
        ArrayList arrayList = new ArrayList();
        for (Faq faq : list) {
            ArrayList arrayList2 = new ArrayList(Arrays.asList(faqTagFilter.b()));
            arrayList2.removeAll(faq.c());
            if (arrayList2.isEmpty()) {
                arrayList.add(faq);
            }
        }
        return arrayList;
    }

    private List<Faq> c(List<Faq> list, FaqTagFilter faqTagFilter) {
        ArrayList arrayList = new ArrayList();
        for (Faq faq : list) {
            if (new ArrayList(Arrays.asList(faqTagFilter.b())).removeAll(faq.c())) {
                arrayList.add(faq);
            }
        }
        return arrayList;
    }

    private List<Faq> d(List<Faq> list, FaqTagFilter faqTagFilter) {
        ArrayList arrayList = new ArrayList();
        for (Faq faq : list) {
            if (!new ArrayList(Arrays.asList(faqTagFilter.b())).removeAll(faq.c())) {
                arrayList.add(faq);
            }
        }
        return arrayList;
    }

    public int a(String str, Boolean bool) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int update;
        ContentValues contentValues = new ContentValues();
        contentValues.put("helpful", Integer.valueOf(bool.booleanValue() ? 1 : -1));
        synchronized (this.a) {
            try {
                update = this.a.getWritableDatabase().update("faqs", contentValues, "question_id = ?", new String[]{str});
            } catch (Exception e) {
                l.c("HelpShiftDebug", "Error in setIsHelpful", e);
                update = 0;
            }
        }
        return update;
    }
}
