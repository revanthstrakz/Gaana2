package com.playercache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.e.a.a;
import com.e.a.e.j;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.models.PlayerTrack;
import java.util.HashMap;

public class d extends a {
    private static d b;
    private Gson a = new GsonBuilder().excludeFieldsWithModifiers(8, 4).create();

    private d(Context context) {
        super(context);
    }

    public static d a() {
        if (b == null) {
            b = new d(GaanaApplication.getContext());
        }
        return b;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        super.onCreate(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        super.onUpgrade(sQLiteDatabase, i, i2);
    }

    public long a(Track track, boolean z) {
        SQLiteDatabase db = getDB();
        long j = 0;
        try {
            long insert;
            db.beginTransaction();
            if (z) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("track_id", track.getBusinessObjId());
                contentValues.put("track_cache_behaviour", Integer.valueOf(track.getCachingBehaviour()));
                contentValues.put("track_metadata", this.a.toJson((Object) track));
                insert = db.insert(j.a, null, contentValues);
            } else {
                String[] strArr = new String[]{String.valueOf(track.getBusinessObjId())};
                insert = (long) db.delete(j.a, "track_id= ?", strArr);
            }
            j = insert;
            db.setTransactionSuccessful();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
        return j;
    }

    public long a(String str) {
        SQLiteDatabase db = getDB();
        long j = 0;
        try {
            db.beginTransaction();
            String[] strArr = new String[]{String.valueOf(str)};
            j = (long) db.delete(j.a, "track_id= ?", strArr);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
        return j;
    }

    public HashMap<String, PlayerTrack> b() {
        HashMap hashMap = new HashMap();
        getDB();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from ");
        stringBuilder.append(j.a);
        Cursor rawQuery = getDB().rawQuery(stringBuilder.toString(), null);
        try {
            if (rawQuery.moveToFirst()) {
                do {
                    String string = rawQuery.getString(rawQuery.getColumnIndex("track_id"));
                    rawQuery.getInt(rawQuery.getColumnIndex("track_cache_behaviour"));
                    hashMap.put(string, new PlayerTrack((Track) this.a.fromJson(rawQuery.getString(rawQuery.getColumnIndex("track_metadata")), Track.class), null, -1, null));
                } while (rawQuery.moveToNext());
            }
        } catch (SQLException unused) {
        } catch (Throwable th) {
            rawQuery.close();
        }
        rawQuery.close();
        return hashMap;
    }
}
