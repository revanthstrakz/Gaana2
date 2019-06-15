package com.helpshift.l.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.helpshift.common.a.b;

public class a extends SQLiteOpenHelper {
    private final b a;

    public a(Context context, b bVar) {
        super(context, "__hs__db_issues", null, b.a.intValue());
        this.a = bVar;
    }

    /* JADX WARNING: Failed to extract finally block: empty outs */
    /* JADX WARNING: Failed to extract finally block: empty outs */
    public void onCreate(android.database.sqlite.SQLiteDatabase r5) {
        /*
        r4 = this;
        r0 = r5.isOpen();
        if (r0 == 0) goto L_0x0079;
    L_0x0006:
        r5.beginTransaction();	 Catch:{ Exception -> 0x003b }
        r0 = r4.a;	 Catch:{ Exception -> 0x003b }
        r0 = r0.a();	 Catch:{ Exception -> 0x003b }
        r0 = r0.iterator();	 Catch:{ Exception -> 0x003b }
    L_0x0013:
        r1 = r0.hasNext();	 Catch:{ Exception -> 0x003b }
        if (r1 == 0) goto L_0x0023;
    L_0x0019:
        r1 = r0.next();	 Catch:{ Exception -> 0x003b }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x003b }
        r5.execSQL(r1);	 Catch:{ Exception -> 0x003b }
        goto L_0x0013;
    L_0x0023:
        r5.setTransactionSuccessful();	 Catch:{ Exception -> 0x003b }
        r0 = r5.inTransaction();	 Catch:{ Exception -> 0x0030 }
        if (r0 == 0) goto L_0x0079;
    L_0x002c:
        r5.endTransaction();	 Catch:{ Exception -> 0x0030 }
        goto L_0x0079;
    L_0x0030:
        r5 = move-exception;
        r0 = "Helpshift_ConversationDB";
        r1 = "Error in onCreate inside finally block, ";
        com.helpshift.util.l.c(r0, r1, r5);
        goto L_0x0079;
    L_0x0039:
        r0 = move-exception;
        goto L_0x0066;
    L_0x003b:
        r0 = move-exception;
        r1 = "Helpshift_ConversationDB";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0039 }
        r2.<init>();	 Catch:{ all -> 0x0039 }
        r3 = "Exception while creating tables: ";
        r2.append(r3);	 Catch:{ all -> 0x0039 }
        r2.append(r0);	 Catch:{ all -> 0x0039 }
        r0 = ", version: ";
        r2.append(r0);	 Catch:{ all -> 0x0039 }
        r0 = com.helpshift.common.a.b.a;	 Catch:{ all -> 0x0039 }
        r2.append(r0);	 Catch:{ all -> 0x0039 }
        r0 = r2.toString();	 Catch:{ all -> 0x0039 }
        com.helpshift.util.l.c(r1, r0);	 Catch:{ all -> 0x0039 }
        r0 = r5.inTransaction();	 Catch:{ Exception -> 0x0030 }
        if (r0 == 0) goto L_0x0079;
    L_0x0062:
        r5.endTransaction();	 Catch:{ Exception -> 0x0030 }
        goto L_0x0079;
    L_0x0066:
        r1 = r5.inTransaction();	 Catch:{ Exception -> 0x0070 }
        if (r1 == 0) goto L_0x0078;
    L_0x006c:
        r5.endTransaction();	 Catch:{ Exception -> 0x0070 }
        goto L_0x0078;
    L_0x0070:
        r5 = move-exception;
        r1 = "Helpshift_ConversationDB";
        r2 = "Error in onCreate inside finally block, ";
        com.helpshift.util.l.c(r1, r2, r5);
    L_0x0078:
        throw r0;
    L_0x0079:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.l.a.a.onCreate(android.database.sqlite.SQLiteDatabase):void");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        a(sQLiteDatabase);
    }

    /* JADX WARNING: Failed to extract finally block: empty outs */
    /* JADX WARNING: Failed to extract finally block: empty outs */
    public void a(android.database.sqlite.SQLiteDatabase r5) {
        /*
        r4 = this;
        r0 = r5.isOpen();
        if (r0 == 0) goto L_0x0093;
    L_0x0006:
        r5.beginTransaction();	 Catch:{ Exception -> 0x004c }
        r0 = r4.a;	 Catch:{ Exception -> 0x004c }
        r0 = r0.b();	 Catch:{ Exception -> 0x004c }
        r0 = r0.iterator();	 Catch:{ Exception -> 0x004c }
    L_0x0013:
        r1 = r0.hasNext();	 Catch:{ Exception -> 0x004c }
        if (r1 == 0) goto L_0x0023;
    L_0x0019:
        r1 = r0.next();	 Catch:{ Exception -> 0x004c }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x004c }
        r5.execSQL(r1);	 Catch:{ Exception -> 0x004c }
        goto L_0x0013;
    L_0x0023:
        r0 = r4.a;	 Catch:{ Exception -> 0x004c }
        r0 = r0.a();	 Catch:{ Exception -> 0x004c }
        r0 = r0.iterator();	 Catch:{ Exception -> 0x004c }
    L_0x002d:
        r1 = r0.hasNext();	 Catch:{ Exception -> 0x004c }
        if (r1 == 0) goto L_0x003d;
    L_0x0033:
        r1 = r0.next();	 Catch:{ Exception -> 0x004c }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x004c }
        r5.execSQL(r1);	 Catch:{ Exception -> 0x004c }
        goto L_0x002d;
    L_0x003d:
        r5.setTransactionSuccessful();	 Catch:{ Exception -> 0x004c }
        r0 = r5.inTransaction();	 Catch:{ Exception -> 0x0077 }
        if (r0 == 0) goto L_0x0093;
    L_0x0046:
        r5.endTransaction();	 Catch:{ Exception -> 0x0077 }
        goto L_0x0093;
    L_0x004a:
        r0 = move-exception;
        goto L_0x0080;
    L_0x004c:
        r0 = move-exception;
        r1 = "Helpshift_ConversationDB";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004a }
        r2.<init>();	 Catch:{ all -> 0x004a }
        r3 = "Exception while upgrading tables: ";
        r2.append(r3);	 Catch:{ all -> 0x004a }
        r2.append(r0);	 Catch:{ all -> 0x004a }
        r0 = ", version: ";
        r2.append(r0);	 Catch:{ all -> 0x004a }
        r0 = com.helpshift.common.a.b.a;	 Catch:{ all -> 0x004a }
        r2.append(r0);	 Catch:{ all -> 0x004a }
        r0 = r2.toString();	 Catch:{ all -> 0x004a }
        com.helpshift.util.l.c(r1, r0);	 Catch:{ all -> 0x004a }
        r0 = r5.inTransaction();	 Catch:{ Exception -> 0x0077 }
        if (r0 == 0) goto L_0x0093;
    L_0x0073:
        r5.endTransaction();	 Catch:{ Exception -> 0x0077 }
        goto L_0x0093;
    L_0x0077:
        r5 = move-exception;
        r0 = "Helpshift_ConversationDB";
        r1 = "Error in dropAndCreateDatabase inside finally block, ";
        com.helpshift.util.l.c(r0, r1, r5);
        goto L_0x0093;
    L_0x0080:
        r1 = r5.inTransaction();	 Catch:{ Exception -> 0x008a }
        if (r1 == 0) goto L_0x0092;
    L_0x0086:
        r5.endTransaction();	 Catch:{ Exception -> 0x008a }
        goto L_0x0092;
    L_0x008a:
        r5 = move-exception;
        r1 = "Helpshift_ConversationDB";
        r2 = "Error in dropAndCreateDatabase inside finally block, ";
        com.helpshift.util.l.c(r1, r2, r5);
    L_0x0092:
        throw r0;
    L_0x0093:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.helpshift.l.a.a.a(android.database.sqlite.SQLiteDatabase):void");
    }
}
