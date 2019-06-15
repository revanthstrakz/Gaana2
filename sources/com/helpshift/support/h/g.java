package com.helpshift.support.h;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.helpshift.account.dao.ProfileDTO;
import com.helpshift.util.e;
import com.helpshift.util.f;
import com.helpshift.util.l;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;

public class g {
    private f a;

    public g(Context context) {
        this.a = new f(context);
    }

    public synchronized void a(ProfileDTO profileDTO) {
        String str = "IDENTIFIER=?";
        String[] strArr = new String[]{profileDTO.c};
        SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
        ContentValues b = b(profileDTO);
        try {
            if (f.a(writableDatabase, "profiles", str, strArr)) {
                writableDatabase.update("profiles", b, str, strArr);
            } else {
                writableDatabase.insert("profiles", null, b);
            }
        } catch (Exception e) {
            l.c("Helpshift_ProfileDB", "Error in addProfile", e);
        }
        e.a("__hs__db_profiles");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0043 A:{SYNTHETIC, Splitter:B:24:0x0043} */
    /* JADX WARNING: Missing block: B:9:0x0025, code skipped:
            if (r12 != null) goto L_0x0027;
     */
    /* JADX WARNING: Missing block: B:11:?, code skipped:
            r12.close();
     */
    /* JADX WARNING: Missing block: B:18:0x0038, code skipped:
            if (r12 != null) goto L_0x0027;
     */
    public synchronized com.helpshift.account.dao.ProfileDTO a(java.lang.String r12) {
        /*
        r11 = this;
        monitor-enter(r11);
        r0 = 0;
        r1 = r11.a;	 Catch:{ Exception -> 0x002f, all -> 0x002d }
        r2 = r1.getReadableDatabase();	 Catch:{ Exception -> 0x002f, all -> 0x002d }
        r5 = "IDENTIFIER=?";
        r1 = 1;
        r6 = new java.lang.String[r1];	 Catch:{ Exception -> 0x002f, all -> 0x002d }
        r1 = 0;
        r6[r1] = r12;	 Catch:{ Exception -> 0x002f, all -> 0x002d }
        r3 = "profiles";
        r4 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r12 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x002f, all -> 0x002d }
        r1 = r12.moveToFirst();	 Catch:{ Exception -> 0x002b }
        if (r1 == 0) goto L_0x0025;
    L_0x0020:
        r1 = r11.b(r12);	 Catch:{ Exception -> 0x002b }
        r0 = r1;
    L_0x0025:
        if (r12 == 0) goto L_0x003b;
    L_0x0027:
        r12.close();	 Catch:{ all -> 0x0047 }
        goto L_0x003b;
    L_0x002b:
        r1 = move-exception;
        goto L_0x0031;
    L_0x002d:
        r12 = move-exception;
        goto L_0x0041;
    L_0x002f:
        r1 = move-exception;
        r12 = r0;
    L_0x0031:
        r2 = "Helpshift_ProfileDB";
        r3 = "Error in getProfile";
        com.helpshift.util.l.c(r2, r3, r1);	 Catch:{ all -> 0x003d }
        if (r12 == 0) goto L_0x003b;
    L_0x003a:
        goto L_0x0027;
    L_0x003b:
        monitor-exit(r11);
        return r0;
    L_0x003d:
        r0 = move-exception;
        r10 = r0;
        r0 = r12;
        r12 = r10;
    L_0x0041:
        if (r0 == 0) goto L_0x0049;
    L_0x0043:
        r0.close();	 Catch:{ all -> 0x0047 }
        goto L_0x0049;
    L_0x0047:
        r12 = move-exception;
        goto L_0x004a;
    L_0x0049:
        throw r12;	 Catch:{ all -> 0x0047 }
    L_0x004a:
        monitor-exit(r11);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.support.h.g.a(java.lang.String):com.helpshift.account.dao.ProfileDTO");
    }

    private static int a(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("IDENTIFIER");
        return columnIndex == -1 ? cursor.getColumnIndex("IDENTIFIER".toLowerCase()) : columnIndex;
    }

    private ProfileDTO b(Cursor cursor) {
        return new ProfileDTO(Long.valueOf(cursor.getLong(cursor.getColumnIndex(BaseColumns._ID))), cursor.getString(a(cursor)), cursor.getString(cursor.getColumnIndex("profile_id")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("email")), cursor.getString(cursor.getColumnIndex("salt")), cursor.getString(cursor.getColumnIndex("uid")), cursor.getString(cursor.getColumnIndex("did")), cursor.getInt(cursor.getColumnIndex("push_token_sync")) == 1);
    }

    private ContentValues b(ProfileDTO profileDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDENTIFIER", profileDTO.c);
        contentValues.put("profile_id", profileDTO.b);
        contentValues.put("name", profileDTO.d);
        contentValues.put("email", profileDTO.e);
        contentValues.put("salt", profileDTO.f);
        contentValues.put("uid", profileDTO.g);
        contentValues.put("did", profileDTO.h);
        contentValues.put("push_token_sync", Boolean.valueOf(profileDTO.i));
        return contentValues;
    }
}
