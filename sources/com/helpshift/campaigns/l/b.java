package com.helpshift.campaigns.l;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.helpshift.util.l;

class b extends SQLiteOpenHelper {
    b(Context context) {
        super(context, "__hs__db_campaigns", null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS campaigns(identifier text primary key, user_identifier text not null, title text not null, body text not null, cover_image_url text, cover_image_file_path text, icon_image_url text not null, icon_image_file_path text, background_color text, title_color text, text_color text, actions blob, messages blob, read_status int, seen_status int, created_at int, extra_data text, expiry_time_stamp int default 9223372036854775807);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen() && i < 2) {
            try {
                sQLiteDatabase.execSQL("ALTER TABLE campaigns ADD expiry_time_stamp INT DEFAULT 9223372036854775807");
            } catch (Exception e) {
                l.c("Helpshift_CampDBHelper", "Exception while upgrading campaigns database", e);
            }
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            try {
                a(sQLiteDatabase);
                onCreate(sQLiteDatabase);
            } catch (Exception e) {
                l.c("Helpshift_CampDBHelper", "Exception while downgrading campaigns database", e);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS campaigns;");
    }
}
