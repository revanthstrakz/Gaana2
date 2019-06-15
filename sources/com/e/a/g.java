package com.e.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.e.a.e.i;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.services.n;
import java.io.Serializable;

public class g extends a {
    private static g a;

    public g(Context context) {
        super(context);
    }

    public static g a() {
        if (a == null) {
            a = new g(GaanaApplication.getContext());
        }
        return a;
    }

    public void a(Track track) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = getDB();
        try {
            db.beginTransaction();
            if (a(track.getBusinessObjId())) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("update ");
                stringBuilder.append(i.a);
                stringBuilder.append(" set ");
                stringBuilder.append("time_stamp");
                stringBuilder.append(" = ");
                stringBuilder.append(System.currentTimeMillis());
                stringBuilder.append(" where ");
                stringBuilder.append("track_id");
                stringBuilder.append(" = ");
                stringBuilder.append(track.getBusinessObjId());
                db.execSQL(stringBuilder.toString());
                db.setTransactionSuccessful();
            } else {
                contentValues.put("track_id", track.getBusinessObjId());
                contentValues.put("track_metadata", n.a((Serializable) track));
                contentValues.put("time_stamp", Long.valueOf(System.currentTimeMillis()));
                db.insert(i.a, null, contentValues);
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0085  */
    public java.util.ArrayList b() {
        /*
        r4 = this;
        r0 = java.util.Calendar.getInstance();
        r1 = 5;
        r2 = -1;
        r0.set(r1, r2);
        r0 = r0.getTimeInMillis();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "select track_metadata from ";
        r2.append(r3);
        r3 = com.e.a.e.i.a;
        r2.append(r3);
        r3 = " where ";
        r2.append(r3);
        r3 = "time_stamp";
        r2.append(r3);
        r3 = " > ";
        r2.append(r3);
        r2.append(r0);
        r0 = " order by ";
        r2.append(r0);
        r0 = "time_stamp";
        r2.append(r0);
        r0 = " DESC LIMIT 100";
        r2.append(r0);
        r0 = r2.toString();
        r1 = new java.util.ArrayList;
        r1.<init>();
        r2 = 0;
        r3 = r4.getDB();	 Catch:{ all -> 0x0081 }
        r0 = r3.rawQuery(r0, r2);	 Catch:{ all -> 0x0081 }
        r2 = r0.moveToFirst();	 Catch:{ all -> 0x007f }
        if (r2 == 0) goto L_0x0079;
    L_0x0055:
        r2 = "track_metadata";
        r2 = r0.getColumnIndex(r2);	 Catch:{ all -> 0x007f }
        r2 = r0.getString(r2);	 Catch:{ all -> 0x007f }
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ all -> 0x007f }
        if (r3 != 0) goto L_0x0073;
    L_0x0065:
        r2 = com.services.n.a(r2);	 Catch:{ all -> 0x007f }
        r2 = (com.gaana.models.Tracks.Track) r2;	 Catch:{ all -> 0x007f }
        r3 = com.managers.URLManager.BusinessObjectType.Tracks;	 Catch:{ all -> 0x007f }
        r2.setBusinessObjType(r3);	 Catch:{ all -> 0x007f }
        r1.add(r2);	 Catch:{ all -> 0x007f }
    L_0x0073:
        r2 = r0.moveToNext();	 Catch:{ all -> 0x007f }
        if (r2 != 0) goto L_0x0055;
    L_0x0079:
        if (r0 == 0) goto L_0x007e;
    L_0x007b:
        r0.close();
    L_0x007e:
        return r1;
    L_0x007f:
        r1 = move-exception;
        goto L_0x0083;
    L_0x0081:
        r1 = move-exception;
        r0 = r2;
    L_0x0083:
        if (r0 == 0) goto L_0x0088;
    L_0x0085:
        r0.close();
    L_0x0088:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.g.b():java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b  */
    private boolean a(java.lang.String r4) {
        /*
        r3 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Select track_id From ";
        r0.append(r1);
        r1 = com.e.a.e.i.a;
        r0.append(r1);
        r1 = " where ";
        r0.append(r1);
        r1 = "track_id";
        r0.append(r1);
        r1 = " = ";
        r0.append(r1);
        r0.append(r4);
        r4 = r0.toString();
        r0 = 0;
        r1 = r3.getDB();	 Catch:{ Exception -> 0x004f, all -> 0x0048 }
        r4 = r1.rawQuery(r4, r0);	 Catch:{ Exception -> 0x004f, all -> 0x0048 }
        r0 = r4.getCount();	 Catch:{ Exception -> 0x0046, all -> 0x0041 }
        r1 = 1;
        if (r0 < r1) goto L_0x003b;
    L_0x0035:
        if (r4 == 0) goto L_0x003a;
    L_0x0037:
        r4.close();
    L_0x003a:
        return r1;
    L_0x003b:
        if (r4 == 0) goto L_0x0054;
    L_0x003d:
        r4.close();
        goto L_0x0054;
    L_0x0041:
        r0 = move-exception;
        r2 = r0;
        r0 = r4;
        r4 = r2;
        goto L_0x0049;
    L_0x0046:
        r0 = r4;
        goto L_0x004f;
    L_0x0048:
        r4 = move-exception;
    L_0x0049:
        if (r0 == 0) goto L_0x004e;
    L_0x004b:
        r0.close();
    L_0x004e:
        throw r4;
    L_0x004f:
        if (r0 == 0) goto L_0x0054;
    L_0x0051:
        r0.close();
    L_0x0054:
        r4 = 0;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.g.a(java.lang.String):boolean");
    }
}
