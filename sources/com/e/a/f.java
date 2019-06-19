package com.e.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.text.TextUtils;
import com.e.a.a.a;
import com.e.a.e.g;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.managers.URLManager.BusinessObjectType;
import com.services.n;
import com.utilities.Util;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class f extends a {
    private static f a;

    private f(Context context) {
        super(context);
    }

    public static f a() {
        if (a == null) {
            a = new f(GaanaApplication.getContext());
        }
        return a;
    }

    public long a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                SQLiteDatabase k = k();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT id FROM ");
                stringBuilder.append(com.e.a.e.f.a);
                stringBuilder.append(" where ");
                stringBuilder.append("playlist_id");
                stringBuilder.append("=");
                stringBuilder.append(str);
                return k.compileStatement(stringBuilder.toString()).simpleQueryForLong();
            } catch (SQLiteDoneException unused) {
            }
        }
        return -1;
    }

    private long j(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                SQLiteDatabase k = k();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT id FROM ");
                stringBuilder.append(com.e.a.e.f.a);
                stringBuilder.append(" where ");
                stringBuilder.append("local_playlist_id");
                stringBuilder.append("=");
                stringBuilder.append(str);
                return k.compileStatement(stringBuilder.toString()).simpleQueryForLong();
            } catch (SQLiteDoneException unused) {
            }
        }
        return -1;
    }

    public void b() {
        k().delete(g.a, null, null);
        k().delete(com.e.a.e.f.a, null, null);
    }

    public long a(Playlist playlist) {
        long a = a(playlist.getBusinessObjId());
        long j = (a > -1 ? 1 : (a == -1 ? 0 : -1));
        if (j != null) {
            return a;
        }
        SQLiteDatabase k = k();
        try {
            k.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", playlist.getName());
            contentValues.put("language", playlist.getLanguage());
            contentValues.put("playlist_id", playlist.getBusinessObjId());
            if (!TextUtils.isEmpty(playlist.getLocalPlaylistId())) {
                contentValues.put("local_playlist_id", playlist.getLocalPlaylistId());
            }
            contentValues.put("playlist_metadata", n.a((Serializable) playlist));
            contentValues.put("time_stamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
            j = k.insert(com.e.a.e.f.a, null, contentValues);
            k.setTransactionSuccessful();
            return j;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return -1;
        } finally {
            k.endTransaction();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00af  */
    public java.lang.String b(java.lang.String r13) {
        /*
        r12 = this;
        r0 = r12.k();
        r9 = 1;
        r4 = new java.lang.String[r9];
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "%";
        r1.append(r2);
        r1.append(r13);
        r2 = "%";
        r1.append(r2);
        r1 = r1.toString();
        r10 = 0;
        r4[r10] = r1;
        r1 = 2;
        r2 = new java.lang.String[r1];
        r1 = "id";
        r2[r10] = r1;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "SUBSTR(name,";
        r1.append(r3);
        r3 = r13.length();
        r1.append(r3);
        r3 = ")";
        r1.append(r3);
        r1 = r1.toString();
        r2[r9] = r1;
        r3 = "name LIKE ?";
        r7 = "name DESC, time_stamp DESC";
        r11 = 0;
        r1 = com.e.a.e.f.a;	 Catch:{ all -> 0x00ab }
        r5 = 0;
        r6 = 0;
        r8 = 0;
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x00ab }
        r1 = r0.getCount();	 Catch:{ all -> 0x00a9 }
        if (r1 <= 0) goto L_0x005d;
    L_0x0057:
        r1 = r0.getCount();	 Catch:{ all -> 0x00a9 }
        r11 = new boolean[r1];	 Catch:{ all -> 0x00a9 }
    L_0x005d:
        r1 = r0.moveToNext();	 Catch:{ all -> 0x00a9 }
        if (r1 == 0) goto L_0x0076;
    L_0x0063:
        r1 = r0.getString(r9);	 Catch:{ all -> 0x00a9 }
        r1 = com.utilities.Util.a(r1, r10);	 Catch:{ all -> 0x00a9 }
        if (r1 <= 0) goto L_0x005d;
    L_0x006d:
        r2 = r11.length;	 Catch:{ all -> 0x00a9 }
        r2 = r2 + r9;
        if (r1 >= r2) goto L_0x005d;
    L_0x0071:
        r1 = r1 + -1;
        r11[r1] = r9;	 Catch:{ all -> 0x00a9 }
        goto L_0x005d;
    L_0x0076:
        if (r11 == 0) goto L_0x008f;
    L_0x0078:
        r1 = r11.length;	 Catch:{ all -> 0x00a9 }
        if (r1 <= 0) goto L_0x008f;
    L_0x007b:
        r1 = r9;
    L_0x007c:
        r2 = r11.length;	 Catch:{ all -> 0x00a9 }
        if (r10 >= r2) goto L_0x008e;
    L_0x007f:
        r2 = r11[r10];	 Catch:{ all -> 0x00a9 }
        if (r2 != 0) goto L_0x0085;
    L_0x0083:
        r9 = r9 + r10;
        goto L_0x008f;
    L_0x0085:
        r2 = r11.length;	 Catch:{ all -> 0x00a9 }
        r2 = r2 - r9;
        if (r10 != r2) goto L_0x008b;
    L_0x0089:
        r1 = r10 + 2;
    L_0x008b:
        r10 = r10 + 1;
        goto L_0x007c;
    L_0x008e:
        r9 = r1;
    L_0x008f:
        if (r0 == 0) goto L_0x0094;
    L_0x0091:
        r0.close();
    L_0x0094:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0.append(r13);
        r13 = "";
        r0.append(r13);
        r0.append(r9);
        r13 = r0.toString();
        return r13;
    L_0x00a9:
        r13 = move-exception;
        goto L_0x00ad;
    L_0x00ab:
        r13 = move-exception;
        r0 = r11;
    L_0x00ad:
        if (r0 == 0) goto L_0x00b2;
    L_0x00af:
        r0.close();
    L_0x00b2:
        throw r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.f.b(java.lang.String):java.lang.String");
    }

    public long b(Playlist playlist) {
        SQLiteDatabase k = k();
        try {
            k.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", playlist.getName());
            contentValues.put("language", playlist.getLanguage());
            contentValues.put("playlist_id", playlist.getBusinessObjId());
            contentValues.put("time_stamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
            if (!TextUtils.isEmpty(playlist.getLocalPlaylistId())) {
                contentValues.put("local_playlist_id", playlist.getLocalPlaylistId());
            }
            contentValues.put("playlist_metadata", n.a((Serializable) playlist));
            long insert = k.insert(com.e.a.e.f.a, null, contentValues);
            k.setTransactionSuccessful();
            return insert;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return -1;
        } finally {
            k.endTransaction();
        }
    }

    public long c(String str) {
        if (k(str)) {
            return -2;
        }
        SQLiteDatabase k = k();
        try {
            k.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", str);
            Serializable playlist = new Playlist();
            playlist.setName(str);
            playlist.setBusinessObjType(BusinessObjectType.Playlists);
            UserInfo currentUser = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser();
            playlist.setCreatedby(currentUser.getUserProfile().getFullname());
            playlist.setCreatedbyUserId(currentUser.getUserProfile().getUserId());
            contentValues.put("playlist_metadata", n.a(playlist));
            contentValues.put("time_stamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
            long insert = k.insert(com.e.a.e.f.a, null, contentValues);
            k.setTransactionSuccessful();
            return insert;
        } catch (Exception e) {
            e.getMessage().toString();
            return -1;
        } finally {
            k.endTransaction();
        }
    }

    private boolean k(String str) {
        SQLiteDatabase k = k();
        String[] strArr = new String[]{str};
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT name FROM ");
        stringBuilder.append(com.e.a.e.f.a);
        stringBuilder.append(" WHERE ");
        stringBuilder.append("name");
        stringBuilder.append("=?");
        Cursor rawQuery = k.rawQuery(stringBuilder.toString(), strArr);
        boolean moveToFirst = rawQuery.moveToFirst();
        rawQuery.close();
        return moveToFirst;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x00c6  */
    public com.gaana.models.Playlists.Playlist d(@android.support.annotation.NonNull java.lang.String r11) {
        /*
        r10 = this;
        r0 = r10.k();
        r1 = 1;
        r4 = new java.lang.String[r1];
        r2 = 0;
        r4[r2] = r11;
        r11 = 8;
        r11 = new java.lang.String[r11];
        r3 = "local_playlist_id";
        r11[r2] = r3;
        r2 = "playlist_id";
        r11[r1] = r2;
        r1 = "id";
        r2 = 2;
        r11[r2] = r1;
        r1 = "has_synced";
        r2 = 3;
        r11[r2] = r1;
        r1 = "playlist_metadata";
        r2 = 4;
        r11[r2] = r1;
        r1 = "name";
        r2 = 5;
        r11[r2] = r1;
        r1 = "language";
        r2 = 6;
        r11[r2] = r1;
        r1 = "last_sync_time";
        r2 = 7;
        r11[r2] = r1;
        r3 = "name=?";
        r9 = 0;
        r1 = com.e.a.e.f.a;	 Catch:{ all -> 0x00c2 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r2 = r11;
        r11 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x00c2 }
        r0 = r11.moveToFirst();	 Catch:{ all -> 0x00c0 }
        if (r0 == 0) goto L_0x00b9;
    L_0x0048:
        r0 = "local_playlist_id";
        r0 = r11.getColumnIndex(r0);	 Catch:{ all -> 0x00c0 }
        r0 = r11.getString(r0);	 Catch:{ all -> 0x00c0 }
        r1 = "playlist_id";
        r1 = r11.getColumnIndex(r1);	 Catch:{ all -> 0x00c0 }
        r1 = r11.getString(r1);	 Catch:{ all -> 0x00c0 }
        r2 = "id";
        r2 = r11.getColumnIndex(r2);	 Catch:{ all -> 0x00c0 }
        r2 = r11.getLong(r2);	 Catch:{ all -> 0x00c0 }
        r4 = "has_synced";
        r4 = r11.getColumnIndex(r4);	 Catch:{ all -> 0x00c0 }
        r4 = r11.getInt(r4);	 Catch:{ all -> 0x00c0 }
        r5 = "playlist_metadata";
        r5 = r11.getColumnIndex(r5);	 Catch:{ all -> 0x00c0 }
        r5 = r11.getString(r5);	 Catch:{ all -> 0x00c0 }
        r6 = "name";
        r6 = r11.getColumnIndex(r6);	 Catch:{ all -> 0x00c0 }
        r6 = r11.getString(r6);	 Catch:{ all -> 0x00c0 }
        r7 = "language";
        r7 = r11.getColumnIndex(r7);	 Catch:{ all -> 0x00c0 }
        r7 = r11.getString(r7);	 Catch:{ all -> 0x00c0 }
        r8 = "last_sync_time";
        r8 = r11.getColumnIndex(r8);	 Catch:{ all -> 0x00c0 }
        r8 = r11.getLong(r8);	 Catch:{ all -> 0x00c0 }
        r5 = com.services.n.a(r5);	 Catch:{ all -> 0x00c0 }
        r5 = (com.gaana.models.Playlists.Playlist) r5;	 Catch:{ all -> 0x00c0 }
        r5.setOfflinePlaylistId(r2);	 Catch:{ all -> 0x00c0 }
        r5.setLocalPlaylistId(r0);	 Catch:{ all -> 0x00c0 }
        r5.setPlaylistId(r1);	 Catch:{ all -> 0x00c0 }
        r5.setSyncStatus(r4);	 Catch:{ all -> 0x00c0 }
        r0 = com.managers.URLManager.BusinessObjectType.Playlists;	 Catch:{ all -> 0x00c0 }
        r5.setBusinessObjType(r0);	 Catch:{ all -> 0x00c0 }
        r5.setName(r6);	 Catch:{ all -> 0x00c0 }
        r5.setLanguage(r7);	 Catch:{ all -> 0x00c0 }
        r5.setSyncTime(r8);	 Catch:{ all -> 0x00c0 }
        goto L_0x00ba;
    L_0x00b9:
        r5 = r9;
    L_0x00ba:
        if (r11 == 0) goto L_0x00bf;
    L_0x00bc:
        r11.close();
    L_0x00bf:
        return r5;
    L_0x00c0:
        r0 = move-exception;
        goto L_0x00c4;
    L_0x00c2:
        r0 = move-exception;
        r11 = r9;
    L_0x00c4:
        if (r11 == 0) goto L_0x00c9;
    L_0x00c6:
        r11.close();
    L_0x00c9:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.f.d(java.lang.String):com.gaana.models.Playlists$Playlist");
    }

    public long a(String str, String str2) {
        SQLiteDatabase k = k();
        try {
            k.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("local_playlist_id", str2);
            contentValues.put("name", str);
            Serializable playlist = new Playlist();
            playlist.setName(str);
            playlist.setLocalPlaylistId(str2);
            playlist.setBusinessObjType(BusinessObjectType.Playlists);
            UserInfo currentUser = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser();
            playlist.setCreatedby(currentUser.getUserProfile().getFullname());
            playlist.setCreatedbyUserId(currentUser.getUserProfile().getUserId());
            contentValues.put("playlist_metadata", n.a(playlist));
            contentValues.put("time_stamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
            long str3 = k.insert(com.e.a.e.f.a, "playlist_id", contentValues);
            k.setTransactionSuccessful();
            return str3;
        } catch (Exception unused) {
            return -1;
        } finally {
            k.endTransaction();
        }
    }

    public void a(String str, Playlist playlist) {
        SQLiteDatabase k = k();
        try {
            k.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("playlist_metadata", n.a((Serializable) playlist));
            String str2 = com.e.a.e.f.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("playlist_id=");
            stringBuilder.append(str);
            k.update(str2, contentValues, stringBuilder.toString(), null);
            k.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            k.endTransaction();
        }
        k.endTransaction();
    }

    public void a(Playlist playlist, ArrayList<Track> arrayList) {
        long offlinePlaylistId = playlist.getOfflinePlaylistId();
        if (offlinePlaylistId <= 0) {
            offlinePlaylistId = a(playlist.getBusinessObjId());
        }
        if (offlinePlaylistId > 0) {
            SQLiteDatabase k = k();
            try {
                k.beginTransaction();
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    Serializable serializable = (Track) arrayList.get(size);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", Long.valueOf(offlinePlaylistId));
                    contentValues.put("track_id", serializable.getBusinessObjId());
                    contentValues.put("track_metadata", n.a(serializable));
                    contentValues.put("added_on", Long.valueOf(System.currentTimeMillis()));
                    k.insert(g.a, "id", contentValues);
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("has_synced", Integer.valueOf(-1));
                String[] strArr = new String[]{String.valueOf(offlinePlaylistId)};
                contentValues2.put("time_stamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
                k.update(com.e.a.e.f.a, contentValues2, "id=? AND has_synced!=0", strArr);
                k.setTransactionSuccessful();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            } catch (Throwable th) {
                k.endTransaction();
            }
            k.endTransaction();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0080 A:{ExcHandler: all (r0_0 'th' java.lang.Throwable), Splitter:B:10:0x0026} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:26:0x0080, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:27:0x0081, code skipped:
            r1 = r0;
            r2.endTransaction();
     */
    public int b(com.gaana.models.Playlists.Playlist r19, java.util.ArrayList<com.gaana.models.Tracks.Track> r20) {
        /*
        r18 = this;
        r1 = 0;
        if (r19 == 0) goto L_0x008a;
    L_0x0003:
        if (r20 != 0) goto L_0x0007;
    L_0x0005:
        goto L_0x008a;
    L_0x0007:
        r4 = r19.getOfflinePlaylistId();
        r6 = 0;
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 > 0) goto L_0x001c;
    L_0x0011:
        r2 = r19.getBusinessObjId();
        r8 = r18;
        r4 = r8.a(r2);
        goto L_0x001e;
    L_0x001c:
        r8 = r18;
    L_0x001e:
        r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r2 <= 0) goto L_0x0089;
    L_0x0022:
        r2 = r18.k();
        r2.beginTransaction();	 Catch:{ Exception -> 0x0086, all -> 0x0080 }
        r3 = r20.iterator();	 Catch:{ Exception -> 0x0086, all -> 0x0080 }
        r6 = r1;
    L_0x002e:
        r7 = r3.hasNext();	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        if (r7 == 0) goto L_0x0076;
    L_0x0034:
        r7 = r3.next();	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        r7 = (com.gaana.models.Tracks.Track) r7;	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        r7 = r7.getBusinessObjId();	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        r9 = 2;
        r13 = new java.lang.String[r9];	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        r13[r1] = r7;	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        r7 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        r10 = 1;
        r13[r10] = r7;	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        r12 = "track_id= ? AND id= ?";
        r7 = com.e.a.e.g.a;	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        r11 = new java.lang.String[r9];	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        r9 = "track_id";
        r11[r1] = r9;	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        r9 = "id";
        r11[r10] = r9;	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        r14 = 0;
        r15 = 0;
        r16 = 0;
        r17 = 0;
        r9 = r2;
        r10 = r7;
        r7 = r9.query(r10, r11, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        if (r7 == 0) goto L_0x0072;
    L_0x006a:
        r9 = r7.getCount();	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        if (r9 <= 0) goto L_0x0072;
    L_0x0070:
        r6 = r6 + 1;
    L_0x0072:
        r7.close();	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        goto L_0x002e;
    L_0x0076:
        r2.setTransactionSuccessful();	 Catch:{ Exception -> 0x007e, all -> 0x0080 }
        r2.endTransaction();
        r1 = r6;
        goto L_0x0089;
    L_0x007e:
        r1 = r6;
        goto L_0x0086;
    L_0x0080:
        r0 = move-exception;
        r1 = r0;
        r2.endTransaction();
        throw r1;
    L_0x0086:
        r2.endTransaction();
    L_0x0089:
        return r1;
    L_0x008a:
        r8 = r18;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.f.b(com.gaana.models.Playlists$Playlist, java.util.ArrayList):int");
    }

    public Tracks c(Playlist playlist) {
        long a;
        Throwable th;
        Tracks tracks = new Tracks();
        ArrayList arrayList = new ArrayList();
        String businessObjId = playlist.getBusinessObjId();
        if (TextUtils.isEmpty(businessObjId) || !businessObjId.startsWith("-")) {
            a = a(businessObjId);
        } else {
            a = Long.parseLong(businessObjId.replaceFirst("-", ""));
        }
        if (a > 0) {
            SQLiteDatabase k = k();
            Cursor cursor = null;
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT track_metadata FROM ");
                stringBuilder.append(g.a);
                stringBuilder.append(" where ");
                stringBuilder.append("id");
                stringBuilder.append("=");
                stringBuilder.append(a);
                stringBuilder.append(" AND ");
                stringBuilder.append("has_synced");
                stringBuilder.append("!=");
                stringBuilder.append(-2);
                stringBuilder.append(" ORDER BY ");
                stringBuilder.append("added_on");
                stringBuilder.append(" DESC");
                Cursor rawQuery = k.rawQuery(stringBuilder.toString(), null);
                try {
                    if (rawQuery.moveToFirst()) {
                        do {
                            arrayList.add((Track) n.a(rawQuery.getString(rawQuery.getColumnIndex("track_metadata"))));
                        } while (rawQuery.moveToNext());
                    }
                    rawQuery.close();
                } catch (Throwable th2) {
                    th = th2;
                    cursor = rawQuery;
                    cursor.close();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor.close();
                throw th;
            }
        }
        tracks.setCount(String.valueOf(arrayList.size()));
        tracks.setArrListBusinessObj(arrayList);
        return tracks;
    }

    public Playlist e(String str) {
        long parseLong;
        Throwable th;
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        if (str.startsWith("-")) {
            parseLong = Long.parseLong(str.replaceFirst("-", ""));
        } else {
            parseLong = a(str);
        }
        Playlist playlist = null;
        if (parseLong > 0) {
            SQLiteDatabase k = k();
            try {
                Cursor rawQuery;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT track_metadata FROM ");
                stringBuilder.append(g.a);
                stringBuilder.append(" where ");
                stringBuilder.append("id");
                stringBuilder.append("=");
                stringBuilder.append(parseLong);
                stringBuilder.append(" AND ");
                stringBuilder.append("has_synced");
                stringBuilder.append("!=");
                stringBuilder.append(-2);
                stringBuilder.append(" ORDER BY ");
                stringBuilder.append("added_on");
                stringBuilder.append(" DESC");
                Cursor rawQuery2 = k.rawQuery(stringBuilder.toString(), null);
                try {
                    if (rawQuery2.moveToFirst()) {
                        do {
                            arrayList.add((Track) n.a(rawQuery2.getString(rawQuery2.getColumnIndex("track_metadata"))));
                        } while (rawQuery2.moveToNext());
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("SELECT * FROM ");
                    stringBuilder2.append(com.e.a.e.f.a);
                    stringBuilder2.append(" where ");
                    stringBuilder2.append("playlist_id");
                    stringBuilder2.append("=");
                    stringBuilder2.append(str);
                    rawQuery = k.rawQuery(stringBuilder2.toString(), null);
                } catch (Throwable th2) {
                    th = th2;
                    cursor = rawQuery2;
                    cursor.close();
                    throw th;
                }
                try {
                    if (rawQuery.moveToFirst()) {
                        String string = rawQuery.getString(rawQuery.getColumnIndex("local_playlist_id"));
                        String string2 = rawQuery.getString(rawQuery.getColumnIndex("playlist_id"));
                        long j = rawQuery.getLong(rawQuery.getColumnIndex("id"));
                        int i = rawQuery.getInt(rawQuery.getColumnIndex("has_synced"));
                        String string3 = rawQuery.getString(rawQuery.getColumnIndex("name"));
                        String string4 = rawQuery.getString(rawQuery.getColumnIndex("language"));
                        Playlist playlist2 = (Playlist) n.a(rawQuery.getString(rawQuery.getColumnIndex("playlist_metadata")));
                        playlist2.setName(string3);
                        playlist2.setLanguage(string4);
                        playlist2.setOfflinePlaylistId(j);
                        playlist2.setLocalPlaylistId(string);
                        playlist2.setBusinessObjType(BusinessObjectType.Playlists);
                        if (TextUtils.isEmpty(string2)) {
                            string2 = String.valueOf(0 - j);
                        }
                        playlist2.setPlaylistId(string2);
                        playlist2.setSyncStatus(i);
                        playlist2.setArrListBusinessObj(arrayList);
                        playlist = playlist2;
                    }
                    rawQuery.close();
                } catch (Throwable th3) {
                    cursor = rawQuery;
                    th = th3;
                    cursor.close();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                cursor.close();
                throw th;
            }
        }
        return playlist;
    }

    public ArrayList<Track> f(String str) {
        Throwable th;
        ArrayList arrayList = new ArrayList();
        long a = a(str);
        if (a > 0) {
            SQLiteDatabase k = k();
            Cursor cursor = null;
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT track_metadata FROM ");
                stringBuilder.append(g.a);
                stringBuilder.append("");
                stringBuilder.append(" where ");
                stringBuilder.append("id");
                stringBuilder.append("=");
                stringBuilder.append(a);
                stringBuilder.append(" AND ");
                stringBuilder.append("is_local");
                stringBuilder.append("=");
                stringBuilder.append(1);
                Cursor rawQuery = k.rawQuery(stringBuilder.toString(), null);
                try {
                    if (rawQuery.moveToFirst()) {
                        do {
                            arrayList.add((Track) n.a(rawQuery.getString(rawQuery.getColumnIndex("track_metadata"))));
                        } while (rawQuery.moveToNext());
                    }
                    rawQuery.close();
                } catch (Throwable th2) {
                    th = th2;
                    cursor = rawQuery;
                    cursor.close();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor.close();
                throw th;
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x00cf  */
    public java.util.ArrayList<com.gaana.models.BusinessObject> c() {
        /*
        r10 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = r10.k();
        r2 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cb }
        r3.<init>();	 Catch:{ all -> 0x00cb }
        r4 = "SELECT * FROM ";
        r3.append(r4);	 Catch:{ all -> 0x00cb }
        r4 = com.e.a.e.f.a;	 Catch:{ all -> 0x00cb }
        r3.append(r4);	 Catch:{ all -> 0x00cb }
        r4 = " where ";
        r3.append(r4);	 Catch:{ all -> 0x00cb }
        r4 = "has_synced";
        r3.append(r4);	 Catch:{ all -> 0x00cb }
        r4 = "!=";
        r3.append(r4);	 Catch:{ all -> 0x00cb }
        r4 = -2;
        r3.append(r4);	 Catch:{ all -> 0x00cb }
        r4 = " ORDER BY ";
        r3.append(r4);	 Catch:{ all -> 0x00cb }
        r4 = "time_stamp";
        r3.append(r4);	 Catch:{ all -> 0x00cb }
        r4 = " DESC";
        r3.append(r4);	 Catch:{ all -> 0x00cb }
        r3 = r3.toString();	 Catch:{ all -> 0x00cb }
        r1 = r1.rawQuery(r3, r2);	 Catch:{ all -> 0x00cb }
        r2 = r1.moveToFirst();	 Catch:{ all -> 0x00c9 }
        if (r2 == 0) goto L_0x00c3;
    L_0x0049:
        r2 = "local_playlist_id";
        r2 = r1.getColumnIndex(r2);	 Catch:{ all -> 0x00c9 }
        r2 = r1.getString(r2);	 Catch:{ all -> 0x00c9 }
        r3 = "playlist_id";
        r3 = r1.getColumnIndex(r3);	 Catch:{ all -> 0x00c9 }
        r3 = r1.getString(r3);	 Catch:{ all -> 0x00c9 }
        r4 = "id";
        r4 = r1.getColumnIndex(r4);	 Catch:{ all -> 0x00c9 }
        r4 = r1.getLong(r4);	 Catch:{ all -> 0x00c9 }
        r6 = "has_synced";
        r6 = r1.getColumnIndex(r6);	 Catch:{ all -> 0x00c9 }
        r6 = r1.getInt(r6);	 Catch:{ all -> 0x00c9 }
        r7 = "name";
        r7 = r1.getColumnIndex(r7);	 Catch:{ all -> 0x00c9 }
        r7 = r1.getString(r7);	 Catch:{ all -> 0x00c9 }
        r8 = "language";
        r8 = r1.getColumnIndex(r8);	 Catch:{ all -> 0x00c9 }
        r8 = r1.getString(r8);	 Catch:{ all -> 0x00c9 }
        r9 = "playlist_metadata";
        r9 = r1.getColumnIndex(r9);	 Catch:{ all -> 0x00c9 }
        r9 = r1.getString(r9);	 Catch:{ all -> 0x00c9 }
        r9 = com.services.n.a(r9);	 Catch:{ all -> 0x00c9 }
        r9 = (com.gaana.models.Playlists.Playlist) r9;	 Catch:{ all -> 0x00c9 }
        r9.setName(r7);	 Catch:{ all -> 0x00c9 }
        r9.setLanguage(r8);	 Catch:{ all -> 0x00c9 }
        r9.setOfflinePlaylistId(r4);	 Catch:{ all -> 0x00c9 }
        r9.setLocalPlaylistId(r2);	 Catch:{ all -> 0x00c9 }
        r2 = com.managers.URLManager.BusinessObjectType.Playlists;	 Catch:{ all -> 0x00c9 }
        r9.setBusinessObjType(r2);	 Catch:{ all -> 0x00c9 }
        r2 = android.text.TextUtils.isEmpty(r3);	 Catch:{ all -> 0x00c9 }
        if (r2 == 0) goto L_0x00b4;
    L_0x00ac:
        r2 = 0;
        r7 = r2 - r4;
        r3 = java.lang.String.valueOf(r7);	 Catch:{ all -> 0x00c9 }
    L_0x00b4:
        r9.setPlaylistId(r3);	 Catch:{ all -> 0x00c9 }
        r9.setSyncStatus(r6);	 Catch:{ all -> 0x00c9 }
        r0.add(r9);	 Catch:{ all -> 0x00c9 }
        r2 = r1.moveToNext();	 Catch:{ all -> 0x00c9 }
        if (r2 != 0) goto L_0x0049;
    L_0x00c3:
        if (r1 == 0) goto L_0x00c8;
    L_0x00c5:
        r1.close();
    L_0x00c8:
        return r0;
    L_0x00c9:
        r0 = move-exception;
        goto L_0x00cd;
    L_0x00cb:
        r0 = move-exception;
        r1 = r2;
    L_0x00cd:
        if (r1 == 0) goto L_0x00d2;
    L_0x00cf:
        r1.close();
    L_0x00d2:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.f.c():java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x00c4  */
    public java.util.ArrayList<com.gaana.models.Playlists.Playlist> d() {
        /*
        r12 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = r12.k();
        r2 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c0 }
        r3.<init>();	 Catch:{ all -> 0x00c0 }
        r4 = "SELECT * FROM ";
        r3.append(r4);	 Catch:{ all -> 0x00c0 }
        r4 = com.e.a.e.f.a;	 Catch:{ all -> 0x00c0 }
        r3.append(r4);	 Catch:{ all -> 0x00c0 }
        r4 = " WHERE ";
        r3.append(r4);	 Catch:{ all -> 0x00c0 }
        r4 = "has_synced";
        r3.append(r4);	 Catch:{ all -> 0x00c0 }
        r4 = " NOT IN (";
        r3.append(r4);	 Catch:{ all -> 0x00c0 }
        r4 = 1;
        r3.append(r4);	 Catch:{ all -> 0x00c0 }
        r4 = ")";
        r3.append(r4);	 Catch:{ all -> 0x00c0 }
        r3 = r3.toString();	 Catch:{ all -> 0x00c0 }
        r1 = r1.rawQuery(r3, r2);	 Catch:{ all -> 0x00c0 }
        r2 = r1.moveToFirst();	 Catch:{ all -> 0x00be }
        if (r2 == 0) goto L_0x00b8;
    L_0x003f:
        r2 = "local_playlist_id";
        r2 = r1.getColumnIndex(r2);	 Catch:{ all -> 0x00be }
        r2 = r1.getString(r2);	 Catch:{ all -> 0x00be }
        r3 = "playlist_id";
        r3 = r1.getColumnIndex(r3);	 Catch:{ all -> 0x00be }
        r3 = r1.getString(r3);	 Catch:{ all -> 0x00be }
        r4 = "id";
        r4 = r1.getColumnIndex(r4);	 Catch:{ all -> 0x00be }
        r4 = r1.getLong(r4);	 Catch:{ all -> 0x00be }
        r6 = "has_synced";
        r6 = r1.getColumnIndex(r6);	 Catch:{ all -> 0x00be }
        r6 = r1.getInt(r6);	 Catch:{ all -> 0x00be }
        r7 = "playlist_metadata";
        r7 = r1.getColumnIndex(r7);	 Catch:{ all -> 0x00be }
        r7 = r1.getString(r7);	 Catch:{ all -> 0x00be }
        r8 = "name";
        r8 = r1.getColumnIndex(r8);	 Catch:{ all -> 0x00be }
        r8 = r1.getString(r8);	 Catch:{ all -> 0x00be }
        r9 = "language";
        r9 = r1.getColumnIndex(r9);	 Catch:{ all -> 0x00be }
        r9 = r1.getString(r9);	 Catch:{ all -> 0x00be }
        r10 = "last_sync_time";
        r10 = r1.getColumnIndex(r10);	 Catch:{ all -> 0x00be }
        r10 = r1.getLong(r10);	 Catch:{ all -> 0x00be }
        r7 = com.services.n.a(r7);	 Catch:{ all -> 0x00be }
        r7 = (com.gaana.models.Playlists.Playlist) r7;	 Catch:{ all -> 0x00be }
        r7.setOfflinePlaylistId(r4);	 Catch:{ all -> 0x00be }
        r7.setLocalPlaylistId(r2);	 Catch:{ all -> 0x00be }
        r7.setPlaylistId(r3);	 Catch:{ all -> 0x00be }
        r7.setSyncStatus(r6);	 Catch:{ all -> 0x00be }
        r2 = com.managers.URLManager.BusinessObjectType.Playlists;	 Catch:{ all -> 0x00be }
        r7.setBusinessObjType(r2);	 Catch:{ all -> 0x00be }
        r7.setName(r8);	 Catch:{ all -> 0x00be }
        r7.setLanguage(r9);	 Catch:{ all -> 0x00be }
        r7.setSyncTime(r10);	 Catch:{ all -> 0x00be }
        r0.add(r7);	 Catch:{ all -> 0x00be }
        r2 = r1.moveToNext();	 Catch:{ all -> 0x00be }
        if (r2 != 0) goto L_0x003f;
    L_0x00b8:
        if (r1 == 0) goto L_0x00bd;
    L_0x00ba:
        r1.close();
    L_0x00bd:
        return r0;
    L_0x00be:
        r0 = move-exception;
        goto L_0x00c2;
    L_0x00c0:
        r0 = move-exception;
        r1 = r2;
    L_0x00c2:
        if (r1 == 0) goto L_0x00c7;
    L_0x00c4:
        r1.close();
    L_0x00c7:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.f.d():java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0074  */
    public java.util.ArrayList<com.gaana.models.Tracks.Track> a(com.gaana.models.Playlists.Playlist r7, int r8) {
        /*
        r6 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = r6.k();
        r2 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0071 }
        r3.<init>();	 Catch:{ all -> 0x0071 }
        r4 = "SELECT track_metadata FROM ";
        r3.append(r4);	 Catch:{ all -> 0x0071 }
        r4 = com.e.a.e.g.a;	 Catch:{ all -> 0x0071 }
        r3.append(r4);	 Catch:{ all -> 0x0071 }
        r4 = " WHERE ";
        r3.append(r4);	 Catch:{ all -> 0x0071 }
        r4 = "id";
        r3.append(r4);	 Catch:{ all -> 0x0071 }
        r4 = "=";
        r3.append(r4);	 Catch:{ all -> 0x0071 }
        r4 = r7.getOfflinePlaylistId();	 Catch:{ all -> 0x0071 }
        r3.append(r4);	 Catch:{ all -> 0x0071 }
        r7 = " AND ";
        r3.append(r7);	 Catch:{ all -> 0x0071 }
        r7 = "has_synced";
        r3.append(r7);	 Catch:{ all -> 0x0071 }
        r7 = "=";
        r3.append(r7);	 Catch:{ all -> 0x0071 }
        r3.append(r8);	 Catch:{ all -> 0x0071 }
        r7 = r3.toString();	 Catch:{ all -> 0x0071 }
        r7 = r1.rawQuery(r7, r2);	 Catch:{ all -> 0x0071 }
        r8 = r7.moveToFirst();	 Catch:{ all -> 0x006e }
        if (r8 == 0) goto L_0x0068;
    L_0x004f:
        r8 = "track_metadata";
        r8 = r7.getColumnIndex(r8);	 Catch:{ all -> 0x006e }
        r8 = r7.getString(r8);	 Catch:{ all -> 0x006e }
        r8 = com.services.n.a(r8);	 Catch:{ all -> 0x006e }
        r8 = (com.gaana.models.Tracks.Track) r8;	 Catch:{ all -> 0x006e }
        r0.add(r8);	 Catch:{ all -> 0x006e }
        r8 = r7.moveToNext();	 Catch:{ all -> 0x006e }
        if (r8 != 0) goto L_0x004f;
    L_0x0068:
        if (r7 == 0) goto L_0x006d;
    L_0x006a:
        r7.close();
    L_0x006d:
        return r0;
    L_0x006e:
        r8 = move-exception;
        r2 = r7;
        goto L_0x0072;
    L_0x0071:
        r8 = move-exception;
    L_0x0072:
        if (r2 == 0) goto L_0x0077;
    L_0x0074:
        r2.close();
    L_0x0077:
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.f.a(com.gaana.models.Playlists$Playlist, int):java.util.ArrayList");
    }

    public int g(String str) {
        try {
            SQLiteDatabase k;
            StringBuilder stringBuilder;
            if (Integer.parseInt(str) > 0) {
                k = k();
                stringBuilder = new StringBuilder();
                stringBuilder.append("SELECT has_synced FROM ");
                stringBuilder.append(com.e.a.e.f.a);
                stringBuilder.append(" where ");
                stringBuilder.append("playlist_id");
                stringBuilder.append("=");
                stringBuilder.append(str);
                return (int) k.compileStatement(stringBuilder.toString()).simpleQueryForLong();
            }
            int parseInt = 0 - Integer.parseInt(str);
            k = k();
            stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT has_synced FROM ");
            stringBuilder.append(com.e.a.e.f.a);
            stringBuilder.append(" where ");
            stringBuilder.append("id");
            stringBuilder.append("=");
            stringBuilder.append(parseInt);
            return (int) k.compileStatement(stringBuilder.toString()).simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return -3;
        }
    }

    public void a(long j, String str, int i) {
        SQLiteDatabase k = k();
        try {
            k.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("has_synced", Integer.valueOf(i));
            if (i == 1) {
                contentValues.put("last_sync_time", Integer.valueOf(0));
            }
            contentValues.put("playlist_id", str);
            k.update(com.e.a.e.f.a, contentValues, "id=?", new String[]{String.valueOf(j)});
            k.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            k.endTransaction();
        }
        k.endTransaction();
    }

    public void a(Playlist playlist, long j, String str, int i) {
        SQLiteDatabase k = k();
        try {
            k.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("has_synced", Integer.valueOf(i));
            contentValues.put("playlist_id", str);
            contentValues.put("name", playlist.getName());
            contentValues.put("language", playlist.getLanguage());
            contentValues.put("last_sync_time", Long.valueOf(System.currentTimeMillis()));
            k.update(com.e.a.e.f.a, contentValues, "id=?", new String[]{String.valueOf(j)});
            k.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            k.endTransaction();
        }
        k.endTransaction();
    }

    public void a(long j, int i) {
        if (j > 0) {
            SQLiteDatabase k = k();
            try {
                k.beginTransaction();
                ContentValues contentValues = new ContentValues();
                contentValues.put("has_synced", Integer.valueOf(i));
                String str = g.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("id=");
                stringBuilder.append(j);
                k.update(str, contentValues, stringBuilder.toString(), null);
                k.setTransactionSuccessful();
            } catch (Exception unused) {
            } catch (Throwable th) {
                k.endTransaction();
            }
            k.endTransaction();
        }
    }

    public void b(Playlist playlist, int i) {
        long offlinePlaylistId = playlist.getOfflinePlaylistId();
        if (offlinePlaylistId <= 0) {
            offlinePlaylistId = a(playlist.getBusinessObjId());
        }
        if (offlinePlaylistId > 0) {
            SQLiteDatabase k = k();
            try {
                k.beginTransaction();
                ContentValues contentValues = new ContentValues();
                contentValues.put("has_synced", Integer.valueOf(i));
                String str = com.e.a.e.f.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("id=");
                stringBuilder.append(offlinePlaylistId);
                k.update(str, contentValues, stringBuilder.toString(), null);
                k.setTransactionSuccessful();
            } catch (Exception unused) {
            } catch (Throwable th) {
                k.endTransaction();
            }
            k.endTransaction();
        }
    }

    public void a(long j, String str) {
        SQLiteDatabase k = k();
        try {
            k.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("local_playlist_id", str);
            str = com.e.a.e.f.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("id=");
            stringBuilder.append(j);
            k.update(str, contentValues, stringBuilder.toString(), null);
            k.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            k.endTransaction();
        }
        k.endTransaction();
    }

    public void d(Playlist playlist) {
        long offlinePlaylistId = playlist.getOfflinePlaylistId();
        if (offlinePlaylistId <= 0) {
            offlinePlaylistId = a(playlist.getBusinessObjId());
        }
        if (offlinePlaylistId > 0) {
            SQLiteDatabase k = k();
            String str = com.e.a.e.f.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("id=");
            stringBuilder.append(offlinePlaylistId);
            k.delete(str, stringBuilder.toString(), null);
            k = k();
            str = g.a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("id=");
            stringBuilder.append(offlinePlaylistId);
            k.delete(str, stringBuilder.toString(), null);
        }
    }

    public void e(Playlist playlist) {
        long offlinePlaylistId = playlist.getOfflinePlaylistId();
        if (offlinePlaylistId <= 0) {
            if (!TextUtils.isEmpty(playlist.getBusinessObjId())) {
                offlinePlaylistId = a(playlist.getBusinessObjId());
            } else if (!TextUtils.isEmpty(playlist.getLocalPlaylistId())) {
                offlinePlaylistId = j(playlist.getLocalPlaylistId());
            }
        }
        if (offlinePlaylistId <= 0) {
            return;
        }
        if (g(playlist.getBusinessObjId()) != 0) {
            a(offlinePlaylistId, playlist.getBusinessObjId(), -2);
            a(offlinePlaylistId, -2);
            return;
        }
        d(playlist);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003f A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x000b} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:11:0x0040, code skipped:
            r0.endTransaction();
     */
    public int h(java.lang.String r7) {
        /*
        r6 = this;
        r0 = android.text.TextUtils.isEmpty(r7);
        r1 = 0;
        if (r0 != 0) goto L_0x0047;
    L_0x0007:
        r0 = r6.k();
        r0.beginTransaction();	 Catch:{ Exception -> 0x0044, all -> 0x003f }
        r2 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0044, all -> 0x003f }
        r2.<init>();	 Catch:{ Exception -> 0x0044, all -> 0x003f }
        r3 = "has_synced";
        r4 = -2;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x0044, all -> 0x003f }
        r2.put(r3, r4);	 Catch:{ Exception -> 0x0044, all -> 0x003f }
        r3 = com.e.a.e.g.a;	 Catch:{ Exception -> 0x0044, all -> 0x003f }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0044, all -> 0x003f }
        r4.<init>();	 Catch:{ Exception -> 0x0044, all -> 0x003f }
        r5 = "track_id=";
        r4.append(r5);	 Catch:{ Exception -> 0x0044, all -> 0x003f }
        r4.append(r7);	 Catch:{ Exception -> 0x0044, all -> 0x003f }
        r7 = r4.toString();	 Catch:{ Exception -> 0x0044, all -> 0x003f }
        r4 = 0;
        r7 = r0.update(r3, r2, r7, r4);	 Catch:{ Exception -> 0x0044, all -> 0x003f }
        r0.setTransactionSuccessful();	 Catch:{ Exception -> 0x003d, all -> 0x003f }
        r0.endTransaction();
        r1 = r7;
        goto L_0x0047;
    L_0x003d:
        r1 = r7;
        goto L_0x0044;
    L_0x003f:
        r7 = move-exception;
        r0.endTransaction();
        throw r7;
    L_0x0044:
        r0.endTransaction();
    L_0x0047:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.f.h(java.lang.String):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b4  */
    public void i(java.lang.String r7) {
        /*
        r6 = this;
        r0 = r6.h(r7);
        if (r0 <= 0) goto L_0x00b8;
    L_0x0006:
        r0 = "";
        r1 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00b1 }
        r2.<init>();	 Catch:{ all -> 0x00b1 }
        r3 = "SELECT id FROM ";
        r2.append(r3);	 Catch:{ all -> 0x00b1 }
        r3 = com.e.a.e.g.a;	 Catch:{ all -> 0x00b1 }
        r2.append(r3);	 Catch:{ all -> 0x00b1 }
        r3 = " WHERE ";
        r2.append(r3);	 Catch:{ all -> 0x00b1 }
        r3 = "track_id";
        r2.append(r3);	 Catch:{ all -> 0x00b1 }
        r3 = "=";
        r2.append(r3);	 Catch:{ all -> 0x00b1 }
        r2.append(r7);	 Catch:{ all -> 0x00b1 }
        r7 = r2.toString();	 Catch:{ all -> 0x00b1 }
        r2 = r6.k();	 Catch:{ all -> 0x00b1 }
        r7 = r2.rawQuery(r7, r1);	 Catch:{ all -> 0x00b1 }
        r2 = r7.moveToFirst();	 Catch:{ all -> 0x00ae }
        if (r2 == 0) goto L_0x005d;
    L_0x003c:
        r0 = "id";
        r0 = r7.getColumnIndex(r0);	 Catch:{ all -> 0x00ae }
        r0 = r7.getString(r0);	 Catch:{ all -> 0x00ae }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ae }
        r2.<init>();	 Catch:{ all -> 0x00ae }
        r3 = ",";
        r2.append(r3);	 Catch:{ all -> 0x00ae }
        r2.append(r0);	 Catch:{ all -> 0x00ae }
        r0 = r2.toString();	 Catch:{ all -> 0x00ae }
        r2 = r7.moveToNext();	 Catch:{ all -> 0x00ae }
        if (r2 != 0) goto L_0x003c;
    L_0x005d:
        if (r7 == 0) goto L_0x0062;
    L_0x005f:
        r7.close();
    L_0x0062:
        r7 = android.text.TextUtils.isEmpty(r0);
        if (r7 != 0) goto L_0x00b8;
    L_0x0068:
        r7 = ",";
        r2 = "";
        r7 = r0.replaceFirst(r7, r2);
        r0 = r6.k();
        r0.beginTransaction();	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        r2 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        r2.<init>();	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        r3 = "has_synced";
        r4 = -1;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        r2.put(r3, r4);	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        r3 = com.e.a.e.f.a;	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        r4.<init>();	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        r5 = "id IN (";
        r4.append(r5);	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        r4.append(r7);	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        r7 = ")";
        r4.append(r7);	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        r7 = r4.toString();	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        r0.update(r3, r2, r7, r1);	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        r0.setTransactionSuccessful();	 Catch:{ Exception -> 0x00aa, all -> 0x00a5 }
        goto L_0x00aa;
    L_0x00a5:
        r7 = move-exception;
        r0.endTransaction();
        throw r7;
    L_0x00aa:
        r0.endTransaction();
        goto L_0x00b8;
    L_0x00ae:
        r0 = move-exception;
        r1 = r7;
        goto L_0x00b2;
    L_0x00b1:
        r0 = move-exception;
    L_0x00b2:
        if (r1 == 0) goto L_0x00b7;
    L_0x00b4:
        r1.close();
    L_0x00b7:
        throw r0;
    L_0x00b8:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.f.i(java.lang.String):void");
    }

    public void a(long j, ArrayList<Track> arrayList) {
        SQLiteDatabase k = k();
        try {
            k.beginTransaction();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Track track = (Track) it.next();
                String businessObjId = track.getBusinessObjId();
                ContentValues contentValues = new ContentValues();
                contentValues.put("has_synced", Integer.valueOf(-2));
                String[] strArr = new String[]{String.valueOf(businessObjId), String.valueOf(j)};
                if (((long) k.update(g.a, contentValues, "track_id= ? AND id= ?", strArr)) == 0) {
                    String a = Util.a(track.getName(), track.getDuration(), track.getArtistNames());
                    String[] strArr2 = new String[]{String.valueOf(a), String.valueOf(j)};
                    k.update(g.a, contentValues, "track_id= ? AND id= ?", strArr2);
                }
            }
            k.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            k.endTransaction();
        }
        k.endTransaction();
    }

    public void b(long j, ArrayList<Track> arrayList) {
        SQLiteDatabase k = k();
        try {
            k.beginTransaction();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Track track = (Track) it.next();
                String[] strArr = new String[]{String.valueOf(track.getBusinessObjId()), String.valueOf(j)};
                if (k.delete(g.a, "track_id= ? AND id= ?", strArr) == 0) {
                    String a = Util.a(track.getName(), track.getDuration(), track.getArtistNames());
                    String[] strArr2 = new String[]{String.valueOf(a), String.valueOf(j)};
                    k.delete(g.a, "track_id= ? AND id= ?", strArr2);
                }
            }
            k.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            k.endTransaction();
        }
        k.endTransaction();
    }

    public void a(Playlist playlist, Tracks tracks) {
        long offlinePlaylistId = playlist.getOfflinePlaylistId();
        if (offlinePlaylistId <= 0) {
            offlinePlaylistId = a(playlist.getBusinessObjId());
        }
        SQLiteDatabase k = k();
        try {
            k.beginTransaction();
            String str = g.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("id=");
            stringBuilder.append(offlinePlaylistId);
            k.delete(str, stringBuilder.toString(), null);
            if (!(tracks == null || tracks.getArrListBusinessObj() == null)) {
                ArrayList arrListBusinessObj = tracks.getArrListBusinessObj();
                for (int size = arrListBusinessObj.size() - 1; size >= 0; size--) {
                    Serializable serializable = (Track) arrListBusinessObj.get(size);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", Long.valueOf(offlinePlaylistId));
                    contentValues.put("track_id", serializable.getBusinessObjId());
                    contentValues.put("track_metadata", n.a(serializable));
                    contentValues.put("added_on", Long.valueOf(System.currentTimeMillis()));
                    contentValues.put("has_synced", Integer.valueOf(1));
                    k.insert(g.a, "id", contentValues);
                }
            }
            k.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            k.endTransaction();
        }
        k.endTransaction();
    }

    public void c(Playlist playlist, ArrayList<Track> arrayList) {
        ArrayList<Track> arrayList2 = arrayList;
        if (arrayList2 == null || arrayList.size() <= 0) {
            return;
        }
        long offlinePlaylistId = playlist.getOfflinePlaylistId();
        long j = 0;
        if (offlinePlaylistId <= 0) {
            offlinePlaylistId = a(playlist.getBusinessObjId());
        }
        SQLiteDatabase k = k();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            k.beginTransaction();
            int i = 1;
            int size = arrayList.size() - 1;
            while (size >= 0) {
                long j2;
                Track track = (Track) arrayList2.get(size);
                if (track.isMarkedForDeletionFromPlaylist()) {
                    j2 = j;
                } else {
                    ContentValues contentValues = new ContentValues();
                    j = currentTimeMillis + 1;
                    contentValues.put("added_on", Long.valueOf(currentTimeMillis));
                    String str = "track_id= ? AND id= ?";
                    j2 = 0;
                    if (((long) k.update(g.a, contentValues, str, new String[]{String.valueOf(track.getBusinessObjId()), String.valueOf(offlinePlaylistId)})) == 0) {
                        String[] strArr = new String[2];
                        strArr[0] = Util.a(track.getName(), track.getDuration(), track.getArtistNames());
                        i = 1;
                        strArr[1] = String.valueOf(offlinePlaylistId);
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("added_on", Long.valueOf(j));
                        k.update(g.a, contentValues2, str, strArr);
                    } else {
                        i = 1;
                    }
                    currentTimeMillis = j;
                }
                size--;
                j = j2;
            }
            k.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            Throwable th2 = th;
            k.endTransaction();
        }
        k.endTransaction();
    }
}
