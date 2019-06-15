package com.e.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.e.a.e.b;
import com.e.a.e.d;
import com.e.a.e.e;
import com.e.a.e.f;
import com.e.a.e.g;
import com.e.a.e.h;
import com.e.a.e.i;
import com.e.a.e.j;
import com.e.a.e.k;
import com.e.a.e.l;
import com.e.a.e.m;
import com.e.a.e.n;

public class a extends SQLiteOpenHelper {
    private static final String DB_NAME = "GaanaDB";
    private static final int DB_VERSION = 18;
    private static SQLiteDatabase mDB;

    private void handleUpgradeForLocalMediaActivityTable(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public a(Context context) {
        super(context, DB_NAME, null, 18);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(b.a);
        stringBuilder.append(" (");
        stringBuilder.append("feed_id");
        stringBuilder.append(" INTEGER PRIMARY KEY , ");
        stringBuilder.append("feed_url");
        stringBuilder.append(" TEXT , ");
        stringBuilder.append("feed_content");
        stringBuilder.append(" TEXT, ");
        stringBuilder.append("feed_time_stamp");
        stringBuilder.append(" TEXT, ");
        stringBuilder.append("is_dynamic_cache");
        stringBuilder.append(" INTEGER, ");
        stringBuilder.append("feed_user_type");
        stringBuilder.append(" INTEGER");
        stringBuilder.append(")");
        String stringBuilder2 = stringBuilder.toString();
        String a = e.a(k.a);
        String a2 = e.a(h.a);
        String a3 = e.a(l.a);
        String a4 = e.a(com.e.a.e.a.a);
        e.a(e.a);
        String a5 = e.a(d.a);
        String a6 = e.a(i.a);
        String a7 = e.a(j.a);
        sQLiteDatabase.execSQL(a);
        sQLiteDatabase.execSQL(a2);
        sQLiteDatabase.execSQL(a3);
        sQLiteDatabase.execSQL(a4);
        sQLiteDatabase.execSQL(a5);
        sQLiteDatabase.execSQL(a6);
        sQLiteDatabase.execSQL(a7);
        sQLiteDatabase.execSQL(stringBuilder2);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        handleDbUpgrade(sQLiteDatabase, i, i2);
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE IF EXISTS ");
        stringBuilder.append(b.a);
        sQLiteDatabase.execSQL(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE IF EXISTS ");
        stringBuilder.append(k.a);
        sQLiteDatabase.execSQL(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE IF EXISTS ");
        stringBuilder.append(h.a);
        sQLiteDatabase.execSQL(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE IF EXISTS ");
        stringBuilder.append(m.a);
        sQLiteDatabase.execSQL(stringBuilder.toString());
        onCreate(sQLiteDatabase);
    }

    public synchronized SQLiteDatabase getDB() {
        if (mDB == null || !mDB.isOpen()) {
            mDB = getWritableDatabase();
        }
        return mDB;
    }

    public void closeDB() {
        if (mDB != null && mDB.isOpen()) {
            mDB.close();
        }
    }

    private void handleDbUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 5) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("DROP TABLE IF EXISTS ");
            stringBuilder.append(m.a);
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
        if (i < 7) {
            sQLiteDatabase.execSQL(e.a(n.a));
        }
        handleUpgradeForFeedTable(sQLiteDatabase, i, i2);
        handleUpgradeForTrackDetailsTable(sQLiteDatabase, i, i2);
        handleUpgradeForTrackMappingTable(sQLiteDatabase, i, i2);
        handleUpgradeForPlaylistMetadataTable(sQLiteDatabase, i, i2);
        handleUpgradeForPlaylistDetailsTable(sQLiteDatabase, i, i2);
        handleUpgradeForTrackMetadataTable(sQLiteDatabase, i, i2);
        handleUpgradeForDownloadSyncDetailsTable(sQLiteDatabase, i, i2);
        handleUpgradeForLocalMediaActivityTable(sQLiteDatabase, i, i2);
        handleUpgradeForLastHeardSongsTable(sQLiteDatabase, i, i2);
        handleUpgradeForSongsIdentifierHistoryTable(sQLiteDatabase, i, i2);
        handleUpgradeForTrackCacheTable(sQLiteDatabase, i, i2);
    }

    private void handleUpgradeForTrackDetailsTable(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 4) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(k.a);
            stringBuilder.append(" ADD 'encryption_scheme' INTEGER NOT NULL DEFAULT (1)");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private void handleUpgradeForTrackMappingTable(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 7) {
            sQLiteDatabase.execSQL(e.a(g.a));
        }
    }

    private void handleUpgradeForPlaylistMetadataTable(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 7) {
            sQLiteDatabase.execSQL(e.a(f.a));
        } else if (i < 8) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER IGNORE TABLE ");
            stringBuilder.append(f.a);
            stringBuilder.append(" ADD UNIQUE (");
            stringBuilder.append("playlist_id");
            stringBuilder.append(")");
            stringBuilder.toString();
        }
    }

    private void handleUpgradeForPlaylistDetailsTable(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        StringBuilder stringBuilder;
        if (i < 3) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(h.a);
            stringBuilder.append(" ADD 'playlist_content' TEXT");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
        if (i < 5) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(h.a);
            stringBuilder.append(" ADD 'playlist_name' TEXT");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(h.a);
            stringBuilder.append(" ADD 'playlist_type' INTEGER");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(h.a);
            stringBuilder.append(" ADD 'artist_name' TEXT");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
        if (i < 9) {
            long currentTimeMillis = System.currentTimeMillis();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("ALTER TABLE ");
            stringBuilder2.append(h.a);
            stringBuilder2.append(" ADD ");
            stringBuilder2.append("download_time");
            stringBuilder2.append(" TIMESTAMP DEFAULT ");
            stringBuilder2.append(currentTimeMillis);
            stringBuilder2.append(";");
            sQLiteDatabase.execSQL(stringBuilder2.toString());
        }
    }

    private void handleUpgradeForTrackMetadataTable(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 5) {
            sQLiteDatabase.execSQL(e.a(l.a));
            return;
        }
        long currentTimeMillis;
        StringBuilder stringBuilder;
        if (i < 9) {
            currentTimeMillis = System.currentTimeMillis();
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(l.a);
            stringBuilder.append(" ADD ");
            stringBuilder.append("download_time");
            stringBuilder.append(" TIMESTAMP DEFAULT ");
            stringBuilder.append(currentTimeMillis);
            stringBuilder.append(";");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
        if (i < 11) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(l.a);
            stringBuilder.append(" ADD ");
            stringBuilder.append("album_name");
            stringBuilder.append(" TEXT");
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(l.a);
            stringBuilder.append(" ADD ");
            stringBuilder.append("track_artwork");
            stringBuilder.append(" TEXT");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
        if (i < 12) {
            currentTimeMillis = System.currentTimeMillis();
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(l.a);
            stringBuilder.append(" ADD ");
            stringBuilder.append("offline_play_time");
            stringBuilder.append(" TIMESTAMP DEFAULT ");
            stringBuilder.append(currentTimeMillis);
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(l.a);
            stringBuilder.append(" ADD ");
            stringBuilder.append("offline_play_count");
            stringBuilder.append(" INTEGER NOT NULL DEFAULT (1) ");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
        if (i < 13) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(l.a);
            stringBuilder.append(" ADD ");
            stringBuilder.append("parental_warn");
            stringBuilder.append(" INTEGER NOT NULL DEFAULT (0) ");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
        if (i < 14) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("ALTER TABLE ");
            stringBuilder2.append(l.a);
            stringBuilder2.append(" ADD ");
            stringBuilder2.append("track_language");
            stringBuilder2.append(" TEXT DEFAULT ");
            stringBuilder2.append("English");
            sQLiteDatabase.execSQL(stringBuilder2.toString());
        }
        if (i < 16) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(l.a);
            stringBuilder.append(" ADD ");
            stringBuilder.append("smart_download");
            stringBuilder.append(" INTEGER NOT NULL DEFAULT (0) ");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
        if (i < 17) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("ALTER TABLE ");
            stringBuilder3.append(l.a);
            stringBuilder3.append(" ADD ");
            stringBuilder3.append("free_download");
            stringBuilder3.append(" INTEGER NOT NULL DEFAULT (0) ");
            sQLiteDatabase.execSQL(stringBuilder3.toString());
        }
    }

    private void handleUpgradeForDownloadSyncDetailsTable(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 5) {
            sQLiteDatabase.execSQL(e.a(com.e.a.e.a.a));
        }
    }

    private void handleUpgradeForLastHeardSongsTable(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 10) {
            sQLiteDatabase.execSQL(e.a(d.a));
        } else if (i < 14) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(d.a);
            stringBuilder.append(" ADD ");
            stringBuilder.append("track_language");
            stringBuilder.append(" TEXT DEFAULT ");
            stringBuilder.append("English");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private void handleUpgradeForSongsIdentifierHistoryTable(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 15) {
            sQLiteDatabase.execSQL(e.a(i.a));
        }
    }

    private void handleUpgradeForFeedTable(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 6) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(b.a);
            stringBuilder.append(" ADD ");
            stringBuilder.append("is_dynamic_cache");
            stringBuilder.append(" INTEGER");
            sQLiteDatabase.execSQL(stringBuilder.toString());
        }
    }

    private void handleUpgradeForTrackCacheTable(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 18) {
            sQLiteDatabase.execSQL(e.a(j.a));
        }
    }
}
