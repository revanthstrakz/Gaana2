package com.gaana.localmedia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.e.a.a;
import com.e.a.e.e;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.UserActivities;
import com.gaana.models.UserActivities.UserActivity;
import java.util.ArrayList;

public class LocalMediaActivityDbHelper extends a {
    private static LocalMediaActivityDbHelper mLocalMediaActivityDbHelper;

    public LocalMediaActivityDbHelper(Context context) {
        super(context);
    }

    public static LocalMediaActivityDbHelper getInstance() {
        if (mLocalMediaActivityDbHelper == null) {
            mLocalMediaActivityDbHelper = new LocalMediaActivityDbHelper(GaanaApplication.getContext());
        }
        return mLocalMediaActivityDbHelper;
    }

    public void insertFeed(String str, String str2, String str3, String str4, String str5, String str6) {
        SQLiteDatabase db = getDB();
        try {
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("item_id", str);
            contentValues.put("item_name", str2);
            contentValues.put("item_parent_id", str4);
            contentValues.put("item_parent_type", str3);
            contentValues.put("item_parent_name", str5);
            contentValues.put("item_activity_type", str6);
            db.insert(e.a, "item_id", contentValues);
            db.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
    }

    public BusinessObject getActivity() {
        Cursor rawQuery;
        Throwable th;
        SQLiteDatabase db = getDB();
        UserActivities userActivities = new UserActivities();
        ArrayList arrayList = new ArrayList();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM ");
            stringBuilder.append(e.a);
            stringBuilder.append(" ORDER BY ");
            stringBuilder.append("item_time");
            stringBuilder.append(" DESC");
            rawQuery = db.rawQuery(stringBuilder.toString(), null);
            try {
                if (rawQuery.moveToFirst()) {
                    do {
                        String string = rawQuery.getString(rawQuery.getColumnIndex("item_time"));
                        String string2 = rawQuery.getString(rawQuery.getColumnIndex("item_id"));
                        String string3 = rawQuery.getString(rawQuery.getColumnIndex("item_parent_type"));
                        rawQuery.getString(rawQuery.getColumnIndex("item_parent_id"));
                        String string4 = rawQuery.getString(rawQuery.getColumnIndex("item_activity_type"));
                        String string5 = rawQuery.getString(rawQuery.getColumnIndex("item_name"));
                        String string6 = rawQuery.getString(rawQuery.getColumnIndex("item_artwork"));
                        UserActivity userActivity = new UserActivity();
                        userActivity.setItemId(string2);
                        userActivity.setItemName(string5);
                        userActivity.setItemArtwork(string6);
                        userActivity.setActivityTag(string4);
                        userActivity.setItemType(string3);
                        userActivity.setActivity_time(string);
                        userActivity.setActivityTag(string);
                        arrayList.add(userActivity);
                    } while (rawQuery.moveToNext());
                }
            } catch (Exception unused) {
            } catch (Throwable th2) {
                th = th2;
                rawQuery.close();
                throw th;
            }
        } catch (Exception unused2) {
            rawQuery = null;
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
            rawQuery.close();
            throw th;
        }
        rawQuery.close();
        userActivities.setArrListBusinessObj(arrayList);
        return userActivities;
    }
}
