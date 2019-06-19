package com.e.a;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteException;
import android.os.Process;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.e.a.e.c;
import com.e.a.e.k;
import com.e.a.e.l;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.d;
import com.library.util.Serializer;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadHTTPStatus;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap;
import com.managers.u;
import com.services.n;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class h extends a {
    private static HashMap<String, BusinessObject> a = new HashMap();
    private final String b = "TrackDownloadDBHelper";
    private String c;
    private a d;
    private final int e = -9999;

    public interface a {
    }

    public void a() {
    }

    public h(Context context) {
        super(context);
        a = new HashMap();
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        super.onCreate(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        super.onUpgrade(sQLiteDatabase, i, i2);
    }

    public int a(BusinessObject businessObject, ArrayList<String> arrayList) {
        Cursor rawQuery = getDB().rawQuery("select * from playlist_details where download_status =0", null);
        int i = (rawQuery.moveToFirst() || !DownloadManager.c().v()) ? 1 : 0;
        rawQuery.close();
        if (businessObject instanceof Track) {
            i = DownloadStatus.DOWNLOADED == DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId())) ? 1 : 0;
            a(businessObject, -100, i);
        } else {
            a(businessObject, i, (ArrayList) arrayList);
        }
        return i;
    }

    private int a(BusinessObject businessObject, int i, ArrayList<String> arrayList) {
        int parseInt = Integer.parseInt(businessObject.getBusinessObjId());
        SQLiteDatabase db = getDB();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select * from ");
            stringBuilder.append(com.e.a.e.h.a);
            stringBuilder.append(" where ");
            stringBuilder.append("playlist_id");
            stringBuilder.append(" = ");
            stringBuilder.append(parseInt);
            Cursor rawQuery = db.rawQuery(stringBuilder.toString(), null);
            if (rawQuery.moveToFirst()) {
                db.beginTransaction();
                try {
                    ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                    if (!(arrayList == null || arrListBusinessObj == null)) {
                        int h = h(parseInt);
                        Iterator it = arrListBusinessObj.iterator();
                        while (it.hasNext()) {
                            Track track = (Track) it.next();
                            if (arrayList.contains(track.getBusinessObjId())) {
                                int i2 = h + 1;
                                a(track, parseInt, i2, DownloadStatus.DOWNLOADED == DownloadManager.c().e(Integer.parseInt(track.getBusinessObjId())) ? 1 : 0, true);
                                h = i2;
                            }
                        }
                    }
                    h(parseInt, i);
                    db.setTransactionSuccessful();
                    return -1;
                } catch (Exception unused) {
                    return -1;
                } finally {
                    if (!(rawQuery == null || rawQuery.isClosed())) {
                        rawQuery.close();
                    }
                    db.endTransaction();
                }
            } else {
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                return b(businessObject, i, (ArrayList) arrayList);
            }
        } catch (Exception unused2) {
            return -1;
        }
    }

    public int a(BusinessObject businessObject, int i, int i2) {
        int parseInt = Integer.parseInt(businessObject.getBusinessObjId());
        SQLiteDatabase db = getDB();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from ");
        stringBuilder.append(com.e.a.e.h.a);
        stringBuilder.append(" where ");
        stringBuilder.append("playlist_id");
        stringBuilder.append("=");
        stringBuilder.append(i);
        Cursor rawQuery = db.rawQuery(stringBuilder.toString(), null);
        SQLiteDatabase db2 = getDB();
        db2.beginTransaction();
        try {
            if (rawQuery.moveToFirst()) {
                h(i, i2);
            } else {
                BusinessObject playlist = new Playlist();
                playlist.setPlaylistId(String.valueOf(i));
                a(i, c.b, playlist, i2);
            }
            a((Track) businessObject, i, h(i), 0, true);
            rawQuery.close();
            db2.setTransactionSuccessful();
            return parseInt;
        } catch (Exception unused) {
            int i3 = -1;
            return i3;
        } finally {
            db2.endTransaction();
        }
    }

    public boolean a(Playlist playlist, int i) {
        Throwable th;
        SQLiteDatabase db = getDB();
        Cursor cursor = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select * from ");
            stringBuilder.append(com.e.a.e.h.a);
            stringBuilder.append(" where ");
            stringBuilder.append("playlist_id");
            stringBuilder.append("=");
            stringBuilder.append(i);
            Cursor rawQuery = db.rawQuery(stringBuilder.toString(), null);
            try {
                if (rawQuery.moveToFirst()) {
                    BusinessObject playlist2;
                    String string = rawQuery.getString(rawQuery.getColumnIndex("playlist_content"));
                    if (playlist2 == null) {
                        playlist2 = (Playlist) Serializer.deserialize(string);
                    } else {
                        a(playlist2.getArrListBusinessObj(), i, c.b);
                    }
                    int i2 = rawQuery.getInt(rawQuery.getColumnIndex("download_status"));
                    if (!(rawQuery == null || rawQuery.isClosed())) {
                        rawQuery.close();
                    }
                    try {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("select * from ");
                        stringBuilder.append(k.a);
                        stringBuilder.append(" where ");
                        stringBuilder.append("playlist_id");
                        stringBuilder.append("=");
                        stringBuilder.append(i);
                        stringBuilder.append(" AND ");
                        stringBuilder.append("has_downloaded");
                        stringBuilder.append("=");
                        stringBuilder.append(0);
                        rawQuery = db.rawQuery(stringBuilder.toString(), null);
                        try {
                            if (rawQuery.moveToFirst()) {
                                if (!(rawQuery == null || rawQuery.isClosed())) {
                                    rawQuery.close();
                                }
                                if (p() == -1 && i2 != -2) {
                                    i2 = 0;
                                } else if (i2 == -1) {
                                    i2 = 1;
                                }
                                db.beginTransaction();
                                try {
                                    String str = "playlist_details";
                                    db.delete(str, "playlist_id=?", new String[]{String.valueOf(i)});
                                    if (h(i) != 0) {
                                        playlist2.setPlaylistId(String.valueOf(i));
                                        playlist2.setArrListBusinessObj(null);
                                        a(i, c.b, playlist2, i2);
                                    }
                                    db.setTransactionSuccessful();
                                    return true;
                                } catch (Exception unused) {
                                    return false;
                                } finally {
                                    db.endTransaction();
                                }
                            } else {
                                rawQuery.close();
                                if (!(rawQuery == null || rawQuery.isClosed())) {
                                    rawQuery.close();
                                }
                                return false;
                            }
                        } catch (Exception unused2) {
                            cursor = rawQuery;
                            if (!(cursor == null || cursor.isClosed())) {
                                cursor.close();
                            }
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            cursor = rawQuery;
                            if (!(cursor == null || cursor.isClosed())) {
                                cursor.close();
                            }
                            throw th;
                        }
                    } catch (Exception unused3) {
                        cursor.close();
                        return false;
                    } catch (Throwable th3) {
                        th = th3;
                        cursor.close();
                        throw th;
                    }
                }
                rawQuery.close();
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                return false;
            } catch (Exception unused4) {
                cursor = rawQuery;
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                return false;
            } catch (Throwable th4) {
                th = th4;
                cursor = rawQuery;
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Exception unused5) {
            cursor.close();
            return false;
        } catch (Throwable th5) {
            th = th5;
            cursor.close();
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0098 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d7 A:{SYNTHETIC, Splitter:B:45:0x00d7} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c8 A:{Catch:{ Exception -> 0x01cb, all -> 0x01c8 }} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0098 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c8 A:{Catch:{ Exception -> 0x01cb, all -> 0x01c8 }} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d7 A:{SYNTHETIC, Splitter:B:45:0x00d7} */
    /* JADX WARNING: Missing block: B:15:0x0073, code skipped:
            if (r4.isClosed() == false) goto L_0x008f;
     */
    /* JADX WARNING: Missing block: B:29:0x008d, code skipped:
            if (r4.isClosed() == false) goto L_0x008f;
     */
    /* JADX WARNING: Missing block: B:30:0x008f, code skipped:
            r4.close();
     */
    public boolean a(com.gaana.models.Albums.Album r9, int r10) {
        /*
        r8 = this;
        r0 = r8.getDB();
        r1 = 0;
        r2 = 0;
        if (r9 == 0) goto L_0x0099;
    L_0x0008:
        r3 = new java.util.ArrayList;
        r3.<init>();
        r4 = r9.getArrListBusinessObj();
        r5 = r2;
    L_0x0012:
        r6 = r4.size();
        if (r5 >= r6) goto L_0x002c;
    L_0x0018:
        r6 = r4.get(r5);
        r6 = (com.gaana.models.BusinessObject) r6;
        r6 = r6.getBusinessObjId();
        r6 = java.lang.Integer.valueOf(r6);
        r3.add(r6);
        r5 = r5 + 1;
        goto L_0x0012;
    L_0x002c:
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0086, all -> 0x0079 }
        r4.<init>();	 Catch:{ Exception -> 0x0086, all -> 0x0079 }
        r5 = "select track_id from ";
        r4.append(r5);	 Catch:{ Exception -> 0x0086, all -> 0x0079 }
        r5 = com.e.a.e.k.a;	 Catch:{ Exception -> 0x0086, all -> 0x0079 }
        r4.append(r5);	 Catch:{ Exception -> 0x0086, all -> 0x0079 }
        r5 = " where ";
        r4.append(r5);	 Catch:{ Exception -> 0x0086, all -> 0x0079 }
        r5 = "playlist_id";
        r4.append(r5);	 Catch:{ Exception -> 0x0086, all -> 0x0079 }
        r5 = "=";
        r4.append(r5);	 Catch:{ Exception -> 0x0086, all -> 0x0079 }
        r4.append(r10);	 Catch:{ Exception -> 0x0086, all -> 0x0079 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0086, all -> 0x0079 }
        r4 = r0.rawQuery(r4, r1);	 Catch:{ Exception -> 0x0086, all -> 0x0079 }
    L_0x0055:
        r5 = r4.moveToNext();	 Catch:{ Exception -> 0x0087, all -> 0x0076 }
        if (r5 == 0) goto L_0x006d;
    L_0x005b:
        r5 = "track_id";
        r5 = r4.getColumnIndex(r5);	 Catch:{ Exception -> 0x0087, all -> 0x0076 }
        r5 = r4.getInt(r5);	 Catch:{ Exception -> 0x0087, all -> 0x0076 }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x0087, all -> 0x0076 }
        r3.remove(r5);	 Catch:{ Exception -> 0x0087, all -> 0x0076 }
        goto L_0x0055;
    L_0x006d:
        if (r4 == 0) goto L_0x0092;
    L_0x006f:
        r5 = r4.isClosed();
        if (r5 != 0) goto L_0x0092;
    L_0x0075:
        goto L_0x008f;
    L_0x0076:
        r9 = move-exception;
        r1 = r4;
        goto L_0x007a;
    L_0x0079:
        r9 = move-exception;
    L_0x007a:
        if (r1 == 0) goto L_0x0085;
    L_0x007c:
        r10 = r1.isClosed();
        if (r10 != 0) goto L_0x0085;
    L_0x0082:
        r1.close();
    L_0x0085:
        throw r9;
    L_0x0086:
        r4 = r1;
    L_0x0087:
        if (r4 == 0) goto L_0x0092;
    L_0x0089:
        r5 = r4.isClosed();
        if (r5 != 0) goto L_0x0092;
    L_0x008f:
        r4.close();
    L_0x0092:
        r3 = r3.size();
        if (r3 != 0) goto L_0x0099;
    L_0x0098:
        return r2;
    L_0x0099:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01da, all -> 0x01cd }
        r3.<init>();	 Catch:{ Exception -> 0x01da, all -> 0x01cd }
        r4 = "select * from ";
        r3.append(r4);	 Catch:{ Exception -> 0x01da, all -> 0x01cd }
        r4 = com.e.a.e.h.a;	 Catch:{ Exception -> 0x01da, all -> 0x01cd }
        r3.append(r4);	 Catch:{ Exception -> 0x01da, all -> 0x01cd }
        r4 = " where ";
        r3.append(r4);	 Catch:{ Exception -> 0x01da, all -> 0x01cd }
        r4 = "playlist_id";
        r3.append(r4);	 Catch:{ Exception -> 0x01da, all -> 0x01cd }
        r4 = "=";
        r3.append(r4);	 Catch:{ Exception -> 0x01da, all -> 0x01cd }
        r3.append(r10);	 Catch:{ Exception -> 0x01da, all -> 0x01cd }
        r3 = r3.toString();	 Catch:{ Exception -> 0x01da, all -> 0x01cd }
        r3 = r0.rawQuery(r3, r1);	 Catch:{ Exception -> 0x01da, all -> 0x01cd }
        r4 = r3.moveToFirst();	 Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
        if (r4 != 0) goto L_0x00d7;
    L_0x00c8:
        r3.close();	 Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
        if (r3 == 0) goto L_0x00d6;
    L_0x00cd:
        r9 = r3.isClosed();
        if (r9 != 0) goto L_0x00d6;
    L_0x00d3:
        r3.close();
    L_0x00d6:
        return r2;
    L_0x00d7:
        r4 = "playlist_content";
        r4 = r3.getColumnIndex(r4);	 Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
        r4 = r3.getString(r4);	 Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
        if (r9 != 0) goto L_0x00ea;
    L_0x00e3:
        r9 = com.library.util.Serializer.deserialize(r4);	 Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
        r9 = (com.gaana.models.Albums.Album) r9;	 Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
        goto L_0x00f3;
    L_0x00ea:
        r4 = r9.getArrListBusinessObj();	 Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
        r5 = com.e.a.e.c.a;	 Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
        r8.a(r4, r10, r5);	 Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
    L_0x00f3:
        r4 = "download_status";
        r4 = r3.getColumnIndex(r4);	 Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
        r4 = r3.getInt(r4);	 Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
        if (r3 == 0) goto L_0x0108;
    L_0x00ff:
        r5 = r3.isClosed();
        if (r5 != 0) goto L_0x0108;
    L_0x0105:
        r3.close();
    L_0x0108:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r3.<init>();	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r5 = "select * from ";
        r3.append(r5);	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r5 = com.e.a.e.k.a;	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r3.append(r5);	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r5 = " where ";
        r3.append(r5);	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r5 = "playlist_id";
        r3.append(r5);	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r5 = "=";
        r3.append(r5);	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r3.append(r10);	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r5 = " AND ";
        r3.append(r5);	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r5 = "has_downloaded";
        r3.append(r5);	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r5 = "=";
        r3.append(r5);	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r3.append(r2);	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r3 = r3.toString();	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r3 = r0.rawQuery(r3, r1);	 Catch:{ Exception -> 0x01bc, all -> 0x01af }
        r5 = r3.moveToFirst();	 Catch:{ Exception -> 0x01ad, all -> 0x01aa }
        if (r5 != 0) goto L_0x0158;
    L_0x0149:
        r3.close();	 Catch:{ Exception -> 0x01ad, all -> 0x01aa }
        if (r3 == 0) goto L_0x0157;
    L_0x014e:
        r9 = r3.isClosed();
        if (r9 != 0) goto L_0x0157;
    L_0x0154:
        r3.close();
    L_0x0157:
        return r2;
    L_0x0158:
        if (r3 == 0) goto L_0x0163;
    L_0x015a:
        r5 = r3.isClosed();
        if (r5 != 0) goto L_0x0163;
    L_0x0160:
        r3.close();
    L_0x0163:
        r3 = r8.p();
        r5 = -1;
        r6 = 1;
        if (r3 != r5) goto L_0x0170;
    L_0x016b:
        r3 = -2;
        if (r4 == r3) goto L_0x0170;
    L_0x016e:
        r4 = r2;
        goto L_0x0173;
    L_0x0170:
        if (r4 != r5) goto L_0x0173;
    L_0x0172:
        r4 = r6;
    L_0x0173:
        r0.beginTransaction();
        r3 = "playlist_id=?";
        r5 = new java.lang.String[r6];	 Catch:{ Exception -> 0x01a6, all -> 0x01a1 }
        r7 = java.lang.String.valueOf(r10);	 Catch:{ Exception -> 0x01a6, all -> 0x01a1 }
        r5[r2] = r7;	 Catch:{ Exception -> 0x01a6, all -> 0x01a1 }
        r7 = "playlist_details";
        r0.delete(r7, r3, r5);	 Catch:{ Exception -> 0x01a6, all -> 0x01a1 }
        r3 = r8.h(r10);	 Catch:{ Exception -> 0x01a6, all -> 0x01a1 }
        if (r3 == 0) goto L_0x019a;
    L_0x018b:
        r3 = java.lang.String.valueOf(r10);	 Catch:{ Exception -> 0x01a6, all -> 0x01a1 }
        r9.setBusinessObjId(r3);	 Catch:{ Exception -> 0x01a6, all -> 0x01a1 }
        r9.setArrListBusinessObj(r1);	 Catch:{ Exception -> 0x01a6, all -> 0x01a1 }
        r1 = com.e.a.e.c.a;	 Catch:{ Exception -> 0x01a6, all -> 0x01a1 }
        r8.a(r10, r1, r9, r4);	 Catch:{ Exception -> 0x01a6, all -> 0x01a1 }
    L_0x019a:
        r0.setTransactionSuccessful();	 Catch:{ Exception -> 0x01a6, all -> 0x01a1 }
        r0.endTransaction();
        return r6;
    L_0x01a1:
        r9 = move-exception;
        r0.endTransaction();
        throw r9;
    L_0x01a6:
        r0.endTransaction();
        return r2;
    L_0x01aa:
        r9 = move-exception;
        r1 = r3;
        goto L_0x01b0;
    L_0x01ad:
        r1 = r3;
        goto L_0x01bc;
    L_0x01af:
        r9 = move-exception;
    L_0x01b0:
        if (r1 == 0) goto L_0x01bb;
    L_0x01b2:
        r10 = r1.isClosed();
        if (r10 != 0) goto L_0x01bb;
    L_0x01b8:
        r1.close();
    L_0x01bb:
        throw r9;
    L_0x01bc:
        if (r1 == 0) goto L_0x01c7;
    L_0x01be:
        r9 = r1.isClosed();
        if (r9 != 0) goto L_0x01c7;
    L_0x01c4:
        r1.close();
    L_0x01c7:
        return r2;
    L_0x01c8:
        r9 = move-exception;
        r1 = r3;
        goto L_0x01ce;
    L_0x01cb:
        r1 = r3;
        goto L_0x01da;
    L_0x01cd:
        r9 = move-exception;
    L_0x01ce:
        if (r1 == 0) goto L_0x01d9;
    L_0x01d0:
        r10 = r1.isClosed();
        if (r10 != 0) goto L_0x01d9;
    L_0x01d6:
        r1.close();
    L_0x01d9:
        throw r9;
    L_0x01da:
        if (r1 == 0) goto L_0x01e5;
    L_0x01dc:
        r9 = r1.isClosed();
        if (r9 != 0) goto L_0x01e5;
    L_0x01e2:
        r1.close();
    L_0x01e5:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.a(com.gaana.models.Albums$Album, int):boolean");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x00b4 */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0116 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:9:0x0019} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:31:0x0116, code skipped:
            r11 = th;
     */
    private void a(java.util.ArrayList<?> r11, int r12, int r13) {
        /*
        r10 = this;
        r0 = com.e.a.e.c.b;
        r1 = 1;
        if (r13 != r0) goto L_0x0008;
    L_0x0005:
        r10.a(r11, r12, r1);
    L_0x0008:
        r0 = com.e.a.e.c.a;
        if (r13 != r0) goto L_0x000f;
    L_0x000c:
        r10.b(r11, r12, r1);
    L_0x000f:
        r13 = new java.util.ArrayList;
        r13.<init>();
        r0 = 0;
        r2 = r10.getDB();	 Catch:{ SQLiteException -> 0x011e, all -> 0x0118 }
        r2.beginTransaction();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r3 = r10.getDB();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r4.<init>();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r5 = "select track_id from track_details where playlist_id=";
        r4.append(r5);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r4.append(r12);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r4 = r4.toString();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r3 = r3.rawQuery(r4, r0);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r4 = r3.moveToFirst();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        if (r4 == 0) goto L_0x0052;
    L_0x003b:
        r4 = "track_id";
        r4 = r3.getColumnIndex(r4);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r4 = r3.getInt(r4);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r13.add(r4);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r4 = r3.moveToNext();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        if (r4 != 0) goto L_0x003b;
    L_0x0052:
        r3.close();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r3 = r11.iterator();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
    L_0x0059:
        r4 = r3.hasNext();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        if (r4 == 0) goto L_0x006d;
    L_0x005f:
        r4 = r3.next();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r4 = (com.gaana.models.BusinessObject) r4;	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r4 = r4.getBusinessObjId();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r13.remove(r4);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        goto L_0x0059;
    L_0x006d:
        r3 = 0;
        r4 = r3;
    L_0x006f:
        r5 = r13.size();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        if (r4 >= r5) goto L_0x00c9;
    L_0x0075:
        r5 = r13.get(r4);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r5 = (java.lang.String) r5;	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r6 = new android.content.ContentValues;	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r6.<init>();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r7 = "playlist_id";
        r8 = -100;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r6.put(r7, r8);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r7 = com.e.a.e.k.a;	 Catch:{ SQLiteException -> 0x00b4, all -> 0x0116 }
        r8 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x00b4, all -> 0x0116 }
        r8.<init>();	 Catch:{ SQLiteException -> 0x00b4, all -> 0x0116 }
        r9 = "playlist_id=";
        r8.append(r9);	 Catch:{ SQLiteException -> 0x00b4, all -> 0x0116 }
        r8.append(r12);	 Catch:{ SQLiteException -> 0x00b4, all -> 0x0116 }
        r9 = " AND ";
        r8.append(r9);	 Catch:{ SQLiteException -> 0x00b4, all -> 0x0116 }
        r9 = "track_id";
        r8.append(r9);	 Catch:{ SQLiteException -> 0x00b4, all -> 0x0116 }
        r9 = "=";
        r8.append(r9);	 Catch:{ SQLiteException -> 0x00b4, all -> 0x0116 }
        r8.append(r5);	 Catch:{ SQLiteException -> 0x00b4, all -> 0x0116 }
        r8 = r8.toString();	 Catch:{ SQLiteException -> 0x00b4, all -> 0x0116 }
        r2.update(r7, r6, r8, r0);	 Catch:{ SQLiteException -> 0x00b4, all -> 0x0116 }
        goto L_0x00c6;
    L_0x00b4:
        r6 = "playlist_id=? AND track_id=?";
        r7 = 2;
        r7 = new java.lang.String[r7];	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r8 = java.lang.String.valueOf(r12);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r7[r3] = r8;	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r7[r1] = r5;	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r5 = com.e.a.e.k.a;	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r2.delete(r5, r6, r7);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
    L_0x00c6:
        r4 = r4 + 1;
        goto L_0x006f;
    L_0x00c9:
        r13 = r11.size();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        if (r3 >= r13) goto L_0x0112;
    L_0x00cf:
        r13 = r11.get(r3);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r13 = (com.gaana.models.BusinessObject) r13;	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r1 = new android.content.ContentValues;	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r1.<init>();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r4 = "track_position_in_playlist";
        r5 = java.lang.Integer.valueOf(r3);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r1.put(r4, r5);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r4 = com.e.a.e.k.a;	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r5 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r5.<init>();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r6 = "playlist_id=";
        r5.append(r6);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r5.append(r12);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r6 = " AND ";
        r5.append(r6);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r6 = "track_id";
        r5.append(r6);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r6 = "=";
        r5.append(r6);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r13 = r13.getBusinessObjId();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r5.append(r13);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r13 = r5.toString();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r2.update(r4, r1, r13, r0);	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        r3 = r3 + 1;
        goto L_0x00c9;
    L_0x0112:
        r2.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x011f, all -> 0x0116 }
        goto L_0x011f;
    L_0x0116:
        r11 = move-exception;
        goto L_0x011a;
    L_0x0118:
        r11 = move-exception;
        r2 = r0;
    L_0x011a:
        r2.endTransaction();
        throw r11;
    L_0x011e:
        r2 = r0;
    L_0x011f:
        r2.endTransaction();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.a(java.util.ArrayList, int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f4  */
    /* JADX WARNING: Missing block: B:27:0x00dc, code skipped:
            if (r7.isClosed() == false) goto L_0x00fa;
     */
    /* JADX WARNING: Missing block: B:41:0x00f8, code skipped:
            if (r7.isClosed() == false) goto L_0x00fa;
     */
    /* JADX WARNING: Missing block: B:42:0x00fa, code skipped:
            r7.close();
     */
    public java.util.ArrayList<com.gaana.models.BusinessObject> a(java.lang.String r7, int r8) {
        /*
        r6 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "select playlist_content,download_time from ";
        r1.append(r2);
        r2 = com.e.a.e.h.a;
        r1.append(r2);
        r1 = r1.toString();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r1);
        r1 = " where ";
        r2.append(r1);
        r1 = "playlist_type";
        r2.append(r1);
        r1 = "=";
        r2.append(r1);
        r2.append(r8);
        r8 = r2.toString();
        if (r7 == 0) goto L_0x006b;
    L_0x0038:
        r1 = "'";
        r1 = r7.contains(r1);
        if (r1 == 0) goto L_0x0048;
    L_0x0040:
        r1 = "'";
        r2 = "''";
        r7 = r7.replaceAll(r1, r2);
    L_0x0048:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r1.append(r8);
        r8 = " AND ";
        r1.append(r8);
        r8 = "playlist_name";
        r1.append(r8);
        r8 = " like '%";
        r1.append(r8);
        r1.append(r7);
        r7 = "%'";
        r1.append(r7);
        r8 = r1.toString();
    L_0x006b:
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r7.append(r8);
        r8 = " ORDER BY ";
        r7.append(r8);
        r8 = "playlist_name";
        r7.append(r8);
        r7 = r7.toString();
        r8 = 0;
        r1 = r6.getDB();	 Catch:{ Exception -> 0x00f1, all -> 0x00e1 }
        r7 = r1.rawQuery(r7, r8);	 Catch:{ Exception -> 0x00f1, all -> 0x00e1 }
        r8 = r7.moveToFirst();	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        if (r8 == 0) goto L_0x00d6;
    L_0x0090:
        r8 = "playlist_content";
        r8 = r7.getColumnIndex(r8);	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        r8 = r7.getString(r8);	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        r1 = "download_time";
        r1 = r7.getColumnIndex(r1);	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        r1 = r7.getLong(r1);	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        r8 = com.library.util.Serializer.deserialize(r8);	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        r8 = (com.gaana.models.BusinessObject) r8;	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        if (r8 == 0) goto L_0x00d0;
    L_0x00ac:
        r3 = "-100";
        r4 = r8.getBusinessObjId();	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        r3 = r3.equals(r4);	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        if (r3 != 0) goto L_0x00d0;
    L_0x00b8:
        r3 = r8 instanceof com.gaana.models.Playlists.Playlist;	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        if (r3 == 0) goto L_0x00c3;
    L_0x00bc:
        r3 = r8;
        r3 = (com.gaana.models.Playlists.Playlist) r3;	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        r3.setDownloadTime(r1);	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        goto L_0x00cd;
    L_0x00c3:
        r3 = r8 instanceof com.gaana.models.Albums.Album;	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        if (r3 == 0) goto L_0x00cd;
    L_0x00c7:
        r3 = r8;
        r3 = (com.gaana.models.Albums.Album) r3;	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        r3.setDownloadTime(r1);	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
    L_0x00cd:
        r0.add(r8);	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
    L_0x00d0:
        r8 = r7.moveToNext();	 Catch:{ Exception -> 0x00f2, all -> 0x00df }
        if (r8 != 0) goto L_0x0090;
    L_0x00d6:
        if (r7 == 0) goto L_0x00fd;
    L_0x00d8:
        r8 = r7.isClosed();
        if (r8 != 0) goto L_0x00fd;
    L_0x00de:
        goto L_0x00fa;
    L_0x00df:
        r8 = move-exception;
        goto L_0x00e5;
    L_0x00e1:
        r7 = move-exception;
        r5 = r8;
        r8 = r7;
        r7 = r5;
    L_0x00e5:
        if (r7 == 0) goto L_0x00f0;
    L_0x00e7:
        r0 = r7.isClosed();
        if (r0 != 0) goto L_0x00f0;
    L_0x00ed:
        r7.close();
    L_0x00f0:
        throw r8;
    L_0x00f1:
        r7 = r8;
    L_0x00f2:
        if (r7 == 0) goto L_0x00fd;
    L_0x00f4:
        r8 = r7.isClosed();
        if (r8 != 0) goto L_0x00fd;
    L_0x00fa:
        r7.close();
    L_0x00fd:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.a(java.lang.String, int):java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x0157  */
    /* JADX WARNING: Missing block: B:36:0x013f, code skipped:
            if (r3.isClosed() == false) goto L_0x015d;
     */
    /* JADX WARNING: Missing block: B:50:0x015b, code skipped:
            if (r3.isClosed() == false) goto L_0x015d;
     */
    /* JADX WARNING: Missing block: B:51:0x015d, code skipped:
            r3.close();
     */
    public java.util.ArrayList<com.gaana.models.BusinessObject> a(java.lang.String r3, int r4, boolean r5, boolean r6, int r7, int r8, int r9) {
        /*
        r2 = this;
        r5 = new java.util.ArrayList;
        r5.<init>();
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r0 = "select playlist_content,download_time from ";
        r6.append(r0);
        r0 = com.e.a.e.h.a;
        r6.append(r0);
        r6 = r6.toString();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0.append(r6);
        r6 = " where ";
        r0.append(r6);
        r6 = "playlist_type";
        r0.append(r6);
        r6 = "=";
        r0.append(r6);
        r0.append(r4);
        r4 = r0.toString();
        if (r3 == 0) goto L_0x006b;
    L_0x0038:
        r6 = "'";
        r6 = r3.contains(r6);
        if (r6 == 0) goto L_0x0048;
    L_0x0040:
        r6 = "'";
        r0 = "''";
        r3 = r3.replaceAll(r6, r0);
    L_0x0048:
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r6.append(r4);
        r4 = " AND ";
        r6.append(r4);
        r4 = "playlist_name";
        r6.append(r4);
        r4 = " like '%";
        r6.append(r4);
        r6.append(r3);
        r3 = "%'";
        r6.append(r3);
        r4 = r6.toString();
    L_0x006b:
        r3 = 2;
        if (r7 != r3) goto L_0x008a;
    L_0x006e:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r4);
        r4 = " ORDER BY ";
        r3.append(r4);
        r4 = "download_time";
        r3.append(r4);
        r4 = " DESC";
        r3.append(r4);
        r3 = r3.toString();
        goto L_0x00a0;
    L_0x008a:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r4);
        r4 = " ORDER BY ";
        r3.append(r4);
        r4 = "playlist_name";
        r3.append(r4);
        r3 = r3.toString();
    L_0x00a0:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4.append(r3);
        r3 = ",";
        r4.append(r3);
        r3 = "playlist_id";
        r4.append(r3);
        r3 = r4.toString();
        r4 = 20;
        if (r9 >= r4) goto L_0x00bb;
    L_0x00ba:
        goto L_0x00bc;
    L_0x00bb:
        r4 = r9;
    L_0x00bc:
        if (r4 <= 0) goto L_0x00da;
    L_0x00be:
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r6.append(r3);
        r3 = " LIMIT ";
        r6.append(r3);
        r6.append(r8);
        r3 = ",";
        r6.append(r3);
        r6.append(r4);
        r3 = r6.toString();
    L_0x00da:
        r4 = 0;
        r6 = r2.getDB();	 Catch:{ Exception -> 0x0154, all -> 0x0144 }
        r3 = r6.rawQuery(r3, r4);	 Catch:{ Exception -> 0x0154, all -> 0x0144 }
        r4 = r3.moveToFirst();	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        if (r4 == 0) goto L_0x0139;
    L_0x00e9:
        r4 = "playlist_content";
        r4 = r3.getColumnIndex(r4);	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        r4 = r3.getString(r4);	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        r6 = "download_time";
        r6 = r3.getColumnIndex(r6);	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        r6 = r3.getLong(r6);	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        r4 = com.library.util.Serializer.deserialize(r4);	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        r4 = (com.gaana.models.BusinessObject) r4;	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        if (r4 == 0) goto L_0x0133;
    L_0x0105:
        r8 = "-100";
        r9 = r4.getBusinessObjId();	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        if (r8 != 0) goto L_0x0133;
    L_0x0111:
        r8 = r4 instanceof com.gaana.models.Playlists.Playlist;	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        if (r8 == 0) goto L_0x0121;
    L_0x0115:
        r8 = com.managers.URLManager.BusinessObjectType.Playlists;	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        r4.setBusinessObjType(r8);	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        r8 = r4;
        r8 = (com.gaana.models.Playlists.Playlist) r8;	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        r8.setDownloadTime(r6);	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        goto L_0x0130;
    L_0x0121:
        r8 = r4 instanceof com.gaana.models.Albums.Album;	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        if (r8 == 0) goto L_0x0130;
    L_0x0125:
        r8 = com.managers.URLManager.BusinessObjectType.Albums;	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        r4.setBusinessObjType(r8);	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        r8 = r4;
        r8 = (com.gaana.models.Albums.Album) r8;	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        r8.setDownloadTime(r6);	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
    L_0x0130:
        r5.add(r4);	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
    L_0x0133:
        r4 = r3.moveToNext();	 Catch:{ Exception -> 0x0155, all -> 0x0142 }
        if (r4 != 0) goto L_0x00e9;
    L_0x0139:
        if (r3 == 0) goto L_0x0160;
    L_0x013b:
        r4 = r3.isClosed();
        if (r4 != 0) goto L_0x0160;
    L_0x0141:
        goto L_0x015d;
    L_0x0142:
        r4 = move-exception;
        goto L_0x0148;
    L_0x0144:
        r3 = move-exception;
        r1 = r4;
        r4 = r3;
        r3 = r1;
    L_0x0148:
        if (r3 == 0) goto L_0x0153;
    L_0x014a:
        r5 = r3.isClosed();
        if (r5 != 0) goto L_0x0153;
    L_0x0150:
        r3.close();
    L_0x0153:
        throw r4;
    L_0x0154:
        r3 = r4;
    L_0x0155:
        if (r3 == 0) goto L_0x0160;
    L_0x0157:
        r4 = r3.isClosed();
        if (r4 != 0) goto L_0x0160;
    L_0x015d:
        r3.close();
    L_0x0160:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.a(java.lang.String, int, boolean, boolean, int, int, int):java.util.ArrayList");
    }

    public void a(int i, Playlist playlist) {
        try {
            getDB().beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("playlist_id", playlist.getBusinessObjId());
            contentValues.put("playlist_content", Serializer.serialize(playlist));
            SQLiteDatabase db = getDB();
            String str = com.e.a.e.h.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("playlist_id=");
            stringBuilder.append(i);
            db.update(str, contentValues, stringBuilder.toString(), null);
            getDB().setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            getDB().endTransaction();
        }
        getDB().endTransaction();
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0074  */
    /* JADX WARNING: Missing block: B:10:0x005e, code skipped:
            if (r4.isClosed() == false) goto L_0x007a;
     */
    /* JADX WARNING: Missing block: B:24:0x0078, code skipped:
            if (r4.isClosed() == false) goto L_0x007a;
     */
    /* JADX WARNING: Missing block: B:25:0x007a, code skipped:
            r4.close();
     */
    public java.util.ArrayList<java.lang.String> a(int r4) {
        /*
        r3 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "select playlist_id from ";
        r1.append(r2);
        r2 = com.e.a.e.h.a;
        r1.append(r2);
        r1 = r1.toString();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r1);
        r1 = " where ";
        r2.append(r1);
        r1 = "playlist_type";
        r2.append(r1);
        r1 = "=";
        r2.append(r1);
        r2.append(r4);
        r4 = r2.toString();
        r1 = 0;
        r2 = r3.getDB();	 Catch:{ Exception -> 0x0071, all -> 0x0063 }
        r4 = r2.rawQuery(r4, r1);	 Catch:{ Exception -> 0x0071, all -> 0x0063 }
        r1 = r4.moveToFirst();	 Catch:{ Exception -> 0x0072, all -> 0x0061 }
        if (r1 == 0) goto L_0x0058;
    L_0x0045:
        r1 = "playlist_id";
        r1 = r4.getColumnIndex(r1);	 Catch:{ Exception -> 0x0072, all -> 0x0061 }
        r1 = r4.getString(r1);	 Catch:{ Exception -> 0x0072, all -> 0x0061 }
        r0.add(r1);	 Catch:{ Exception -> 0x0072, all -> 0x0061 }
        r1 = r4.moveToNext();	 Catch:{ Exception -> 0x0072, all -> 0x0061 }
        if (r1 != 0) goto L_0x0045;
    L_0x0058:
        if (r4 == 0) goto L_0x007d;
    L_0x005a:
        r1 = r4.isClosed();
        if (r1 != 0) goto L_0x007d;
    L_0x0060:
        goto L_0x007a;
    L_0x0061:
        r0 = move-exception;
        goto L_0x0065;
    L_0x0063:
        r0 = move-exception;
        r4 = r1;
    L_0x0065:
        if (r4 == 0) goto L_0x0070;
    L_0x0067:
        r1 = r4.isClosed();
        if (r1 != 0) goto L_0x0070;
    L_0x006d:
        r4.close();
    L_0x0070:
        throw r0;
    L_0x0071:
        r4 = r1;
    L_0x0072:
        if (r4 == 0) goto L_0x007d;
    L_0x0074:
        r1 = r4.isClosed();
        if (r1 != 0) goto L_0x007d;
    L_0x007a:
        r4.close();
    L_0x007d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.a(int):java.util.ArrayList");
    }

    public ArrayList<String> b() {
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select track_id from ");
        stringBuilder.append(l.a);
        stringBuilder.append(" where ");
        stringBuilder.append("has_downloaded");
        stringBuilder.append("=");
        stringBuilder.append(1);
        String stringBuilder2 = stringBuilder.toString();
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery;
        try {
            rawQuery = getDB().rawQuery(stringBuilder2, null);
            try {
                if (rawQuery.moveToFirst()) {
                    do {
                        arrayList.add(rawQuery.getString(rawQuery.getColumnIndex("track_id")));
                    } while (rawQuery.moveToNext());
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
            rawQuery.close();
            throw th;
        }
    }

    public ArrayList<String> c() {
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select track_id from ");
        stringBuilder.append(l.a);
        String stringBuilder2 = stringBuilder.toString();
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery;
        try {
            rawQuery = getDB().rawQuery(stringBuilder2, null);
            try {
                if (rawQuery.moveToFirst()) {
                    do {
                        arrayList.add(rawQuery.getString(rawQuery.getColumnIndex("track_id")));
                    } while (rawQuery.moveToNext());
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
            rawQuery.close();
            throw th;
        }
    }

    public long d() {
        return DatabaseUtils.queryNumEntries(getDB(), l.a, "has_downloaded=?", new String[]{String.valueOf(1)});
    }

    public ArrayList<?> a(String str, boolean z, boolean z2, int i, int i2) {
        return b(str, z, z2, i, i2);
    }

    public ArrayList<BusinessObject> a(String str, boolean z, boolean z2, int i, int i2, int i3) {
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        StringBuilder stringBuilder3;
        Throwable th;
        Throwable th2;
        String str2 = str;
        String str3 = l.a;
        String str4 = "metadata.";
        String str5 = "track_id";
        String str6 = "";
        if (ap.a().m() && DownloadManager.c().I()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str3);
            stringBuilder.append(" metadata ");
            str3 = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(str4);
            stringBuilder.append(str5);
            str5 = stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("select track_name,track_language,artist_name,download_time,album_name,track_artwork,parental_warn,smart_download,free_download,");
        stringBuilder.append(str5);
        stringBuilder.append(" from ");
        stringBuilder.append(str3);
        str3 = stringBuilder.toString();
        str5 = z ? "has_downloaded=1" : DownloadManager.c().j(z2);
        if (!TextUtils.isEmpty(str5)) {
            if (ap.a().m() && DownloadManager.c().I()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str4);
                stringBuilder.append(str5);
                str5 = stringBuilder.toString();
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str6);
            stringBuilder2.append(" where (");
            stringBuilder2.append(str5);
            stringBuilder2.append(")");
            str6 = stringBuilder2.toString();
        }
        if (str2 != null) {
            if (str2.contains("'")) {
                str2 = str2.replaceAll("'", "''");
            }
            if (str6.contains("where")) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str6);
                stringBuilder2.append(" AND ");
                stringBuilder2.append("track_name");
                stringBuilder2.append(" like '%");
                stringBuilder2.append(str2);
                stringBuilder2.append("%'");
                str6 = stringBuilder2.toString();
            } else {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str6);
                stringBuilder2.append(" where ");
                stringBuilder2.append("track_name");
                stringBuilder2.append(" like '%");
                stringBuilder2.append(str2);
                stringBuilder2.append("%'");
                str6 = stringBuilder2.toString();
            }
        }
        StringBuilder stringBuilder4;
        if (ap.a().m() && DownloadManager.c().I()) {
            stringBuilder4 = new StringBuilder();
            stringBuilder4.append(str3);
            stringBuilder4.append(" JOIN ");
            stringBuilder4.append(k.a);
            stringBuilder4.append(" detail ON detail.track_id=metadata.track_id ");
            stringBuilder4.append(str6);
            stringBuilder4.append(" and detail.playlist_id in (");
            stringBuilder4.append(GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getMiniPacks());
            stringBuilder4.append(")");
            str2 = stringBuilder4.toString();
        } else {
            stringBuilder4 = new StringBuilder();
            stringBuilder4.append(str3);
            stringBuilder4.append(str6);
            str2 = stringBuilder4.toString();
        }
        if (i == 2) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str2);
            stringBuilder3.append(" ORDER BY ");
            stringBuilder3.append("download_time");
            stringBuilder3.append(" DESC");
            str2 = stringBuilder3.toString();
        } else if (ap.a().k()) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str2);
            stringBuilder3.append(" ORDER BY ");
            stringBuilder3.append("free_download");
            stringBuilder3.append(" DESC");
            str2 = stringBuilder3.toString();
        } else {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str2);
            stringBuilder3.append(" ORDER BY ");
            stringBuilder3.append("track_name");
            str2 = stringBuilder3.toString();
        }
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(str2);
        stringBuilder3.append(",");
        stringBuilder3.append("track_id");
        str2 = stringBuilder3.toString();
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(str2);
        stringBuilder3.append(" LIMIT ");
        stringBuilder3.append(i2);
        stringBuilder3.append(",");
        stringBuilder3.append(i3);
        str2 = stringBuilder3.toString();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor rawQuery = getDB().rawQuery(str2, null);
            int i4 = 0;
            try {
                if (rawQuery.moveToFirst()) {
                    do {
                        String string = rawQuery.getString(rawQuery.getColumnIndex("track_name"));
                        str5 = rawQuery.getString(rawQuery.getColumnIndex("track_language"));
                        String string2 = rawQuery.getString(rawQuery.getColumnIndex("track_id"));
                        String string3 = rawQuery.getString(rawQuery.getColumnIndex("artist_name"));
                        String string4 = rawQuery.getString(rawQuery.getColumnIndex("album_name"));
                        String string5 = rawQuery.getString(rawQuery.getColumnIndex("track_artwork"));
                        long j = rawQuery.getLong(rawQuery.getColumnIndex("download_time"));
                        int i5 = rawQuery.getInt(rawQuery.getColumnIndex("parental_warn"));
                        int i6 = rawQuery.getInt(rawQuery.getColumnIndex("smart_download"));
                        OfflineTrack offlineTrack = r5;
                        OfflineTrack offlineTrack2 = offlineTrack;
                        Cursor cursor2 = rawQuery;
                        int i7 = rawQuery.getInt(rawQuery.getColumnIndex("free_download"));
                        try {
                            offlineTrack2 = new OfflineTrack(string2, string, string3, "", j);
                            offlineTrack2.setSmartDownload(i6);
                            offlineTrack2.setFreeDownload(i7);
                            offlineTrack2.setLanguage(str5);
                            offlineTrack2.setAlbumName(string4);
                            offlineTrack2.setImageUrl(string5);
                            offlineTrack2.setPosition(i4);
                            offlineTrack2.setParentalWarning(i5);
                            offlineTrack2.setBusinessObjType(BusinessObjectType.Tracks);
                            arrayList.add(offlineTrack2);
                            i4++;
                            rawQuery = cursor2;
                        } catch (Throwable th3) {
                            th = th3;
                            rawQuery = cursor2;
                            cursor = rawQuery;
                            th2 = th;
                            cursor.close();
                            throw th2;
                        }
                    } while (rawQuery.moveToNext());
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                return arrayList;
            } catch (Throwable th4) {
                th = th4;
                cursor = rawQuery;
                th2 = th;
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                throw th2;
            }
        } catch (Throwable th5) {
            th = th5;
            th2 = th;
            cursor.close();
            throw th2;
        }
    }

    public ArrayList<BusinessObject> b(String str, boolean z, boolean z2, int i, int i2) {
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        StringBuilder stringBuilder3;
        Throwable th;
        Throwable th2;
        String str2 = str;
        int i3 = i2;
        String str3 = l.a;
        String str4 = "metadata.";
        String str5 = "track_id";
        String str6 = "";
        if (ap.a().m() && DownloadManager.c().I()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str3);
            stringBuilder.append(" metadata ");
            str3 = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(str4);
            stringBuilder.append(str5);
            str5 = stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("select track_name,track_language,artist_name,download_time,album_name,track_artwork,parental_warn,smart_download,free_download,");
        stringBuilder.append(str5);
        stringBuilder.append(" from ");
        stringBuilder.append(str3);
        str3 = stringBuilder.toString();
        str5 = z ? "has_downloaded=1" : DownloadManager.c().j(z2);
        if (!TextUtils.isEmpty(str5)) {
            if (ap.a().m() && DownloadManager.c().I()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str4);
                stringBuilder.append(str5);
                str5 = stringBuilder.toString();
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str6);
            stringBuilder2.append(" where (");
            stringBuilder2.append(str5);
            stringBuilder2.append(")");
            str6 = stringBuilder2.toString();
        }
        if (str2 != null) {
            if (str2.contains("'")) {
                str2 = str2.replaceAll("'", "''");
            }
            if (str6.contains("where")) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str6);
                stringBuilder2.append(" AND ");
                stringBuilder2.append("track_name");
                stringBuilder2.append(" like '%");
                stringBuilder2.append(str2);
                stringBuilder2.append("%'");
                str6 = stringBuilder2.toString();
            } else {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str6);
                stringBuilder2.append(" where ");
                stringBuilder2.append("track_name");
                stringBuilder2.append(" like '%");
                stringBuilder2.append(str2);
                stringBuilder2.append("%'");
                str6 = stringBuilder2.toString();
            }
        }
        StringBuilder stringBuilder4;
        if (ap.a().m() && DownloadManager.c().I()) {
            stringBuilder4 = new StringBuilder();
            stringBuilder4.append(str3);
            stringBuilder4.append(" JOIN ");
            stringBuilder4.append(k.a);
            stringBuilder4.append(" detail ON detail.track_id=metadata.track_id ");
            stringBuilder4.append(str6);
            stringBuilder4.append(" and detail.playlist_id in (");
            stringBuilder4.append(GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getMiniPacks());
            stringBuilder4.append(")");
            str2 = stringBuilder4.toString();
        } else {
            stringBuilder4 = new StringBuilder();
            stringBuilder4.append(str3);
            stringBuilder4.append(str6);
            str2 = stringBuilder4.toString();
        }
        if (i == 2) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str2);
            stringBuilder3.append(" ORDER BY ");
            stringBuilder3.append("download_time");
            stringBuilder3.append(" DESC");
            str2 = stringBuilder3.toString();
        } else if (ap.a().k()) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str2);
            stringBuilder3.append(" ORDER BY ");
            stringBuilder3.append("free_download");
            stringBuilder3.append(" DESC");
            str2 = stringBuilder3.toString();
        } else {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str2);
            stringBuilder3.append(" ORDER BY ");
            stringBuilder3.append("track_name");
            str2 = stringBuilder3.toString();
        }
        if (i3 > 0) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str2);
            stringBuilder3.append(" LIMIT ");
            stringBuilder3.append(i3);
            str2 = stringBuilder3.toString();
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor rawQuery = getDB().rawQuery(str2, null);
            int i4 = 0;
            try {
                if (rawQuery.moveToFirst()) {
                    do {
                        String string = rawQuery.getString(rawQuery.getColumnIndex("track_name"));
                        str4 = rawQuery.getString(rawQuery.getColumnIndex("track_language"));
                        str6 = rawQuery.getString(rawQuery.getColumnIndex("track_id"));
                        String string2 = rawQuery.getString(rawQuery.getColumnIndex("artist_name"));
                        String string3 = rawQuery.getString(rawQuery.getColumnIndex("album_name"));
                        String string4 = rawQuery.getString(rawQuery.getColumnIndex("track_artwork"));
                        int i5 = rawQuery.getInt(rawQuery.getColumnIndex("smart_download"));
                        int i6 = rawQuery.getInt(rawQuery.getColumnIndex("free_download"));
                        long j = rawQuery.getLong(rawQuery.getColumnIndex("download_time"));
                        OfflineTrack offlineTrack = r5;
                        OfflineTrack offlineTrack2 = offlineTrack;
                        Cursor cursor2 = rawQuery;
                        int i7 = rawQuery.getInt(rawQuery.getColumnIndex("parental_warn"));
                        try {
                            offlineTrack2 = new OfflineTrack(str6, string, string2, "", j);
                            offlineTrack2.setLanguage(str4);
                            offlineTrack2.setSmartDownload(i5);
                            offlineTrack2.setFreeDownload(i6);
                            offlineTrack2.setAlbumName(string3);
                            offlineTrack2.setImageUrl(string4);
                            offlineTrack2.setPosition(i4);
                            offlineTrack2.setParentalWarning(i7);
                            offlineTrack2.setBusinessObjType(BusinessObjectType.Tracks);
                            arrayList.add(offlineTrack2);
                            i4++;
                            rawQuery = cursor2;
                        } catch (Throwable th3) {
                            th = th3;
                            rawQuery = cursor2;
                            cursor = rawQuery;
                            th2 = th;
                            cursor.close();
                            throw th2;
                        }
                    } while (rawQuery.moveToNext());
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                return arrayList;
            } catch (Throwable th4) {
                th = th4;
                cursor = rawQuery;
                th2 = th;
                cursor.close();
                throw th2;
            }
        } catch (Throwable th5) {
            th = th5;
            th2 = th;
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
            throw th2;
        }
    }

    public ArrayList<BusinessObject> a(String str, int i, int i2, int i3) {
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        StringBuilder stringBuilder3;
        String stringBuilder4;
        Throwable th;
        Throwable th2;
        String str2 = str;
        int i4 = i;
        int i5 = i3;
        String str3 = l.a;
        String str4 = "metadata.";
        String str5 = "track_id";
        String str6 = "";
        if (ap.a().m() && DownloadManager.c().I()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str3);
            stringBuilder.append(" metadata ");
            str3 = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(str4);
            stringBuilder.append(str5);
            str5 = stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("select track_name,track_language,artist_name,download_time,album_name,track_artwork,parental_warn,smart_download,free_download,");
        stringBuilder.append(str5);
        stringBuilder.append(" from ");
        stringBuilder.append(str3);
        str3 = stringBuilder.toString();
        CharSequence charSequence = "";
        if (i4 != -1) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("has_downloaded=");
            stringBuilder2.append(i4);
            charSequence = stringBuilder2.toString();
        }
        if (TextUtils.isEmpty(charSequence)) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(charSequence);
            stringBuilder3.append("smart_download=1");
            stringBuilder4 = stringBuilder3.toString();
        } else {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(charSequence);
            stringBuilder3.append(" and smart_download=1");
            stringBuilder4 = stringBuilder3.toString();
        }
        if (!TextUtils.isEmpty(stringBuilder4)) {
            if (ap.a().m() && DownloadManager.c().I()) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str4);
                stringBuilder2.append(stringBuilder4);
                stringBuilder4 = stringBuilder2.toString();
            }
            StringBuilder stringBuilder5 = new StringBuilder();
            stringBuilder5.append(str6);
            stringBuilder5.append(" where (");
            stringBuilder5.append(stringBuilder4);
            stringBuilder5.append(")");
            str6 = stringBuilder5.toString();
        }
        if (str2 != null) {
            if (str2.contains("'")) {
                str2 = str2.replaceAll("'", "''");
            }
            if (str6.contains("where")) {
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(str6);
                stringBuilder3.append(" AND ");
                stringBuilder3.append("track_name");
                stringBuilder3.append(" like '%");
                stringBuilder3.append(str2);
                stringBuilder3.append("%'");
                str6 = stringBuilder3.toString();
            } else {
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(str6);
                stringBuilder3.append(" where ");
                stringBuilder3.append("track_name");
                stringBuilder3.append(" like '%");
                stringBuilder3.append(str2);
                stringBuilder3.append("%'");
                str6 = stringBuilder3.toString();
            }
        }
        StringBuilder stringBuilder6;
        if (ap.a().m() && DownloadManager.c().I()) {
            stringBuilder6 = new StringBuilder();
            stringBuilder6.append(str3);
            stringBuilder6.append(" JOIN ");
            stringBuilder6.append(k.a);
            stringBuilder6.append(" detail ON detail.track_id=metadata.track_id ");
            stringBuilder6.append(str6);
            stringBuilder6.append(" and detail.playlist_id in (");
            stringBuilder6.append(GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getMiniPacks());
            stringBuilder6.append(")");
            str2 = stringBuilder6.toString();
        } else {
            stringBuilder6 = new StringBuilder();
            stringBuilder6.append(str3);
            stringBuilder6.append(str6);
            str2 = stringBuilder6.toString();
        }
        if (i2 == 2) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str2);
            stringBuilder3.append(" ORDER BY ");
            stringBuilder3.append("download_time");
            stringBuilder3.append(" DESC");
            str2 = stringBuilder3.toString();
        } else if (ap.a().k()) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str2);
            stringBuilder3.append(" ORDER BY ");
            stringBuilder3.append("free_download");
            stringBuilder3.append(" DESC");
            str2 = stringBuilder3.toString();
        } else {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str2);
            stringBuilder3.append(" ORDER BY ");
            stringBuilder3.append("track_name");
            str2 = stringBuilder3.toString();
        }
        if (i5 > 0) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str2);
            stringBuilder3.append(" LIMIT ");
            stringBuilder3.append(i5);
            str2 = stringBuilder3.toString();
        }
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor rawQuery = getDB().rawQuery(str2, null);
            i5 = 0;
            try {
                if (rawQuery.moveToFirst()) {
                    do {
                        str6 = rawQuery.getString(rawQuery.getColumnIndex("track_name"));
                        str3 = rawQuery.getString(rawQuery.getColumnIndex("track_language"));
                        str5 = rawQuery.getString(rawQuery.getColumnIndex("track_id"));
                        String string = rawQuery.getString(rawQuery.getColumnIndex("artist_name"));
                        String string2 = rawQuery.getString(rawQuery.getColumnIndex("album_name"));
                        String string3 = rawQuery.getString(rawQuery.getColumnIndex("track_artwork"));
                        int i6 = rawQuery.getInt(rawQuery.getColumnIndex("smart_download"));
                        int i7 = rawQuery.getInt(rawQuery.getColumnIndex("free_download"));
                        long j = rawQuery.getLong(rawQuery.getColumnIndex("download_time"));
                        OfflineTrack offlineTrack = r5;
                        OfflineTrack offlineTrack2 = offlineTrack;
                        Cursor cursor2 = rawQuery;
                        int i8 = rawQuery.getInt(rawQuery.getColumnIndex("parental_warn"));
                        try {
                            offlineTrack2 = new OfflineTrack(str5, str6, string, "", j);
                            offlineTrack2.setLanguage(str3);
                            offlineTrack2.setSmartDownload(i6);
                            offlineTrack2.setFreeDownload(i7);
                            offlineTrack2.setAlbumName(string2);
                            offlineTrack2.setImageUrl(string3);
                            offlineTrack2.setPosition(i5);
                            offlineTrack2.setParentalWarning(i8);
                            offlineTrack2.setBusinessObjType(BusinessObjectType.Tracks);
                            arrayList.add(offlineTrack2);
                            i5++;
                            rawQuery = cursor2;
                        } catch (Throwable th3) {
                            th = th3;
                            rawQuery = cursor2;
                            cursor = rawQuery;
                            th2 = th;
                            cursor.close();
                            throw th2;
                        }
                    } while (rawQuery.moveToNext());
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                return arrayList;
            } catch (Throwable th4) {
                th = th4;
                cursor = rawQuery;
                th2 = th;
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                throw th2;
            }
        } catch (Throwable th5) {
            th = th5;
            th2 = th;
            cursor.close();
            throw th2;
        }
    }

    public boolean a(String str) {
        boolean z = false;
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT COUNT(*) FROM ");
            stringBuilder.append(l.a);
            stringBuilder.append(" where has_downloaded=1 and smart_download=1 and track_id=");
            stringBuilder.append(str);
            if (((int) db.compileStatement(stringBuilder.toString()).simpleQueryForLong()) != 0) {
                z = true;
            }
            return z;
        } catch (SQLiteDoneException unused) {
            return false;
        }
    }

    public int b(int i) {
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT COUNT(*) FROM ");
            stringBuilder.append(l.a);
            stringBuilder.append(" where has_downloaded=");
            stringBuilder.append(i);
            stringBuilder.append(" and smart_download=1");
            return (int) db.compileStatement(stringBuilder.toString()).simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return 0;
        }
    }

    public boolean a(ArrayList<?> arrayList, int i, boolean z) {
        boolean c = c(arrayList, i, z);
        a(null, i);
        return c;
    }

    public void b(ArrayList<?> arrayList, int i, boolean z) {
        c(arrayList, i, z);
        a(null, i);
    }

    private boolean c(ArrayList<?> arrayList, int i, boolean z) {
        SQLiteDatabase db;
        Cursor cursor;
        Throwable th;
        Throwable th2;
        int i2 = i;
        ArrayList arrayList2 = new ArrayList();
        String[] strArr = null;
        try {
            db = getDB();
            try {
                db.beginTransaction();
                int i3 = 0;
                String[] strArr2 = null;
                while (i3 < arrayList.size()) {
                    BusinessObject businessObject = (BusinessObject) arrayList.get(i3);
                    try {
                        SQLiteDatabase db2 = getDB();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("select track_id from track_details where track_id=");
                        stringBuilder.append(businessObject.getBusinessObjId());
                        stringBuilder.append(" AND playlist_id=");
                        stringBuilder.append(i2);
                        Cursor rawQuery = db2.rawQuery(stringBuilder.toString(), strArr);
                        try {
                            if (rawQuery.moveToFirst()) {
                                cursor = rawQuery;
                            } else {
                                cursor = rawQuery;
                                BusinessObject businessObject2 = businessObject;
                                try {
                                    a((Track) businessObject, i2, h(i2), 0, false);
                                    if (z) {
                                        arrayList2.add((Track) businessObject2);
                                    } else {
                                        a((Track) businessObject2, 0);
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    th2 = th;
                                    if (!(cursor == null || cursor.isClosed())) {
                                        cursor.close();
                                    }
                                    throw th2;
                                }
                            }
                            if (cursor != null) {
                                if (!cursor.isClosed()) {
                                    cursor.close();
                                }
                            }
                            i3++;
                            strArr2 = cursor;
                            strArr = null;
                        } catch (Throwable th4) {
                            th = th4;
                            cursor = rawQuery;
                            th2 = th;
                            cursor.close();
                            throw th2;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        cursor = strArr2;
                        th2 = th;
                        cursor.close();
                        throw th2;
                    }
                }
                db.setTransactionSuccessful();
            } catch (SQLiteException unused) {
            } catch (Throwable th6) {
                th2 = th6;
                db.endTransaction();
                throw th2;
            }
        } catch (SQLiteException unused2) {
            db = null;
        } catch (Throwable th62) {
            th2 = th62;
            db = null;
            db.endTransaction();
            throw th2;
        }
        db.endTransaction();
        if (z) {
            b(arrayList2);
        }
        if (arrayList2.size() > 0) {
            return true;
        }
        return false;
    }

    private void b(final ArrayList<Track> arrayList) {
        if (arrayList != null) {
            d.a(new Runnable() {
                public void run() {
                    Process.setThreadPriority(10);
                    SQLiteDatabase db = h.this.getDB();
                    db.beginTransaction();
                    try {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            Track track = (Track) it.next();
                            h.this.a(track, DownloadStatus.DOWNLOADED == DownloadManager.c().e(Integer.parseInt(track.getBusinessObjId())) ? 1 : 0);
                        }
                        db.setTransactionSuccessful();
                    } catch (SQLiteException unused) {
                    } catch (Throwable th) {
                        db.endTransaction();
                    }
                    db.endTransaction();
                }
            });
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00a6  */
    /* JADX WARNING: Missing block: B:14:0x009b, code skipped:
            if (r3 != null) goto L_0x00ad;
     */
    /* JADX WARNING: Missing block: B:24:0x00ab, code skipped:
            if (r3 != null) goto L_0x00ad;
     */
    /* JADX WARNING: Missing block: B:26:0x00ad, code skipped:
            r3.close();
     */
    /* JADX WARNING: Missing block: B:27:0x00b0, code skipped:
            return r2;
     */
    public com.gaana.models.BusinessObject a(java.lang.String r16, int r17, int r18) {
        /*
        r15 = this;
        r1 = new java.util.ArrayList;
        r1.<init>();
        r2 = new com.gaana.models.BusinessObject;
        r2.<init>();
        r12 = "artist_name LIKE ?";
        r3 = 1;
        r13 = new java.lang.String[r3];
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "%";
        r3.append(r4);
        r4 = r16;
        r3.append(r4);
        r4 = "%";
        r3.append(r4);
        r3 = r3.toString();
        r4 = 0;
        r13[r4] = r3;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r17;
        r3.append(r4);
        r4 = ",";
        r3.append(r4);
        r4 = r18;
        r3.append(r4);
        r11 = r3.toString();
        r10 = "track_name,track_id";
        r3 = com.managers.URLManager.BusinessObjectType.Tracks;
        r2.setBusinessObjType(r3);
        r14 = 0;
        r3 = r15.getDB();	 Catch:{ Exception -> 0x00aa, all -> 0x00a1 }
        r4 = com.e.a.e.l.a;	 Catch:{ Exception -> 0x00aa, all -> 0x00a1 }
        r5 = 0;
        r8 = 0;
        r9 = 0;
        r6 = r12;
        r7 = r13;
        r3 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11);	 Catch:{ Exception -> 0x00aa, all -> 0x00a1 }
    L_0x0059:
        r4 = r3.moveToNext();	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        if (r4 == 0) goto L_0x0079;
    L_0x005f:
        r4 = "track_metadata";
        r4 = r3.getColumnIndex(r4);	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        r4 = r3.getString(r4);	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        if (r5 != 0) goto L_0x0059;
    L_0x006f:
        r4 = com.library.util.Serializer.deserialize(r4);	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        r4 = (com.gaana.models.Tracks.Track) r4;	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        r1.add(r4);	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        goto L_0x0059;
    L_0x0079:
        r4 = r1.size();	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        if (r4 <= 0) goto L_0x008a;
    L_0x007f:
        r4 = r15.getDB();	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        r5 = com.e.a.e.l.a;	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        r4 = android.database.DatabaseUtils.queryNumEntries(r4, r5, r12, r13);	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        goto L_0x008c;
    L_0x008a:
        r4 = 0;
    L_0x008c:
        r2.setArrListBusinessObj(r1);	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        r1 = com.managers.URLManager.BusinessObjectType.Tracks;	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        r2.setBusinessObjType(r1);	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        r1 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        r2.setCount(r1);	 Catch:{ Exception -> 0x00ab, all -> 0x009e }
        if (r3 == 0) goto L_0x00b0;
    L_0x009d:
        goto L_0x00ad;
    L_0x009e:
        r0 = move-exception;
        r1 = r0;
        goto L_0x00a4;
    L_0x00a1:
        r0 = move-exception;
        r1 = r0;
        r3 = r14;
    L_0x00a4:
        if (r3 == 0) goto L_0x00a9;
    L_0x00a6:
        r3.close();
    L_0x00a9:
        throw r1;
    L_0x00aa:
        r3 = r14;
    L_0x00ab:
        if (r3 == 0) goto L_0x00b0;
    L_0x00ad:
        r3.close();
    L_0x00b0:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.a(java.lang.String, int, int):com.gaana.models.BusinessObject");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00a1  */
    /* JADX WARNING: Missing block: B:14:0x0096, code skipped:
            if (r3 != null) goto L_0x00a8;
     */
    /* JADX WARNING: Missing block: B:24:0x00a6, code skipped:
            if (r3 != null) goto L_0x00a8;
     */
    /* JADX WARNING: Missing block: B:26:0x00a8, code skipped:
            r3.close();
     */
    /* JADX WARNING: Missing block: B:27:0x00ab, code skipped:
            return r2;
     */
    public com.gaana.models.BusinessObject b(java.lang.String r16, int r17, int r18) {
        /*
        r15 = this;
        r1 = new java.util.ArrayList;
        r1.<init>();
        r2 = new com.gaana.models.BusinessObject;
        r2.<init>();
        r12 = "artist_name LIKE ?";
        r3 = 1;
        r13 = new java.lang.String[r3];
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "%";
        r3.append(r4);
        r4 = r16;
        r3.append(r4);
        r4 = "%";
        r3.append(r4);
        r3 = r3.toString();
        r4 = 0;
        r13[r4] = r3;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r17;
        r3.append(r4);
        r4 = ",";
        r3.append(r4);
        r4 = r18;
        r3.append(r4);
        r11 = r3.toString();
        r10 = "playlist_name,playlist_id";
        r14 = 0;
        r3 = r15.getDB();	 Catch:{ Exception -> 0x00a5, all -> 0x009c }
        r4 = com.e.a.e.h.a;	 Catch:{ Exception -> 0x00a5, all -> 0x009c }
        r5 = 0;
        r8 = 0;
        r9 = 0;
        r6 = r12;
        r7 = r13;
        r3 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11);	 Catch:{ Exception -> 0x00a5, all -> 0x009c }
    L_0x0054:
        r4 = r3.moveToNext();	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        if (r4 == 0) goto L_0x0074;
    L_0x005a:
        r4 = "playlist_content";
        r4 = r3.getColumnIndex(r4);	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        r4 = r3.getString(r4);	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        if (r5 != 0) goto L_0x0054;
    L_0x006a:
        r4 = com.library.util.Serializer.deserialize(r4);	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        r4 = (com.gaana.models.Albums.Album) r4;	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        r1.add(r4);	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        goto L_0x0054;
    L_0x0074:
        r4 = r1.size();	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        if (r4 <= 0) goto L_0x0085;
    L_0x007a:
        r4 = r15.getDB();	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        r5 = com.e.a.e.h.a;	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        r4 = android.database.DatabaseUtils.queryNumEntries(r4, r5, r12, r13);	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        goto L_0x0087;
    L_0x0085:
        r4 = 0;
    L_0x0087:
        r2.setArrListBusinessObj(r1);	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        r1 = com.managers.URLManager.BusinessObjectType.Albums;	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        r2.setBusinessObjType(r1);	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        r1 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        r2.setCount(r1);	 Catch:{ Exception -> 0x00a6, all -> 0x0099 }
        if (r3 == 0) goto L_0x00ab;
    L_0x0098:
        goto L_0x00a8;
    L_0x0099:
        r0 = move-exception;
        r1 = r0;
        goto L_0x009f;
    L_0x009c:
        r0 = move-exception;
        r1 = r0;
        r3 = r14;
    L_0x009f:
        if (r3 == 0) goto L_0x00a4;
    L_0x00a1:
        r3.close();
    L_0x00a4:
        throw r1;
    L_0x00a5:
        r3 = r14;
    L_0x00a6:
        if (r3 == 0) goto L_0x00ab;
    L_0x00a8:
        r3.close();
    L_0x00ab:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.b(java.lang.String, int, int):com.gaana.models.BusinessObject");
    }

    private void a(Track track, int i) {
        Throwable th;
        Cursor rawQuery;
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select track_id from ");
            stringBuilder.append(l.a);
            stringBuilder.append(" where track_id=");
            stringBuilder.append(track.getBusinessObjId());
            rawQuery = db.rawQuery(stringBuilder.toString(), null);
            try {
                if (rawQuery.moveToFirst()) {
                    String str = "track_id=?";
                    String[] strArr = new String[]{String.valueOf(track.getBusinessObjId())};
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("track_metadata", Serializer.serialize(track));
                    contentValues.put("track_name", track.getRawName());
                    if (TextUtils.isEmpty(track.getLanguage())) {
                        contentValues.put("track_language", "English");
                    } else {
                        contentValues.put("track_language", track.getLanguage());
                    }
                    contentValues.put("has_downloaded", Integer.valueOf(i));
                    if (i == 0) {
                        contentValues.put("download_time", Long.valueOf(System.currentTimeMillis()));
                    }
                    contentValues.put("artist_name", track.getArtistRawNames());
                    contentValues.put("album_name", track.getRawAlbumTitle());
                    contentValues.put("track_artwork", track.getArtwork());
                    contentValues.put("smart_download", Integer.valueOf(track.getSmartDownload()));
                    contentValues.put("parental_warn", Integer.valueOf(track.isParentalWarningEnabled()));
                    contentValues.put("free_download", Boolean.valueOf(track.isFreeDownloadEnabled()));
                    getDB().update(l.a, contentValues, str, strArr);
                    a.put(track.getBusinessObjId(), track);
                } else {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("track_id", track.getBusinessObjId());
                    contentValues2.put("has_downloaded", Integer.valueOf(i));
                    if (i == 0) {
                        contentValues2.put("download_time", Long.valueOf(System.currentTimeMillis()));
                    }
                    contentValues2.put("track_metadata", Serializer.serialize(track));
                    contentValues2.put("track_name", track.getRawName());
                    if (TextUtils.isEmpty(track.getLanguage())) {
                        contentValues2.put("track_language", "English");
                    } else {
                        contentValues2.put("track_language", track.getLanguage());
                    }
                    contentValues2.put("artist_name", track.getArtistRawNames());
                    contentValues2.put("album_name", track.getRawAlbumTitle());
                    contentValues2.put("track_artwork", track.getArtwork());
                    contentValues2.put("parental_warn", Integer.valueOf(track.isParentalWarningEnabled()));
                    contentValues2.put("smart_download", Integer.valueOf(track.getSmartDownload()));
                    contentValues2.put("free_download", Boolean.valueOf(track.isFreeDownloadEnabled()));
                    getDB().insert(l.a, null, contentValues2);
                    a.put(track.getBusinessObjId(), track);
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                int parseInt = Integer.parseInt(track.getBusinessObjId());
                DownloadManager.c().a(parseInt, i, null);
                DownloadManager.c().a(parseInt, track.isFreeDownloadEnabled());
            } catch (Throwable th2) {
                th = th2;
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
            rawQuery.close();
            throw th;
        }
    }

    private void e(int i, int i2) {
        g(i, i2);
        boolean a = PlayerManager.a(GaanaApplication.getContext()).a(String.valueOf(i2));
        if (c(i2) != 0) {
            return;
        }
        if (a) {
            o(i2);
        } else {
            u(i2);
        }
    }

    private void o(int i) {
        j(i, -2);
    }

    private void p(int i) {
        g(-9999, i);
        boolean a = PlayerManager.a(GaanaApplication.getContext()).a(String.valueOf(i));
        if (c(i) != 0) {
            return;
        }
        if (a) {
            o(i);
        } else {
            u(i);
        }
    }

    public int c(int i) {
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT COUNT(*) FROM track_details where track_id =");
            stringBuilder.append(i);
            return (int) db.compileStatement(stringBuilder.toString()).simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return 0;
        }
    }

    public int d(int i) {
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT playlist_type FROM ");
            stringBuilder.append(com.e.a.e.h.a);
            stringBuilder.append(" where ");
            stringBuilder.append("playlist_id");
            stringBuilder.append("=");
            stringBuilder.append(i);
            return (int) db.compileStatement(stringBuilder.toString()).simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return 0;
        }
    }

    public String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int d = d(Integer.valueOf(str).intValue());
        String str2 = "";
        if (d == c.a) {
            str2 = "Album";
        } else if (d == c.b) {
            str2 = "Playlist";
        } else if (d == c.c) {
            str2 = "Track";
        }
        return str2;
    }

    private void q(int i) {
        int d = d(i);
        if (v(i) > 0) {
            com.managers.l.a().a(i, d, 0);
        }
    }

    public void a(int i, int i2) {
        e(i2, i);
        if (h(i2) == 0) {
            q(i2);
        }
    }

    public void a(int i, ArrayList<Integer> arrayList) {
        SQLiteDatabase db = getDB();
        db.beginTransaction();
        int i2 = 0;
        while (i2 < arrayList.size()) {
            try {
                int intValue = ((Integer) arrayList.get(i2)).intValue();
                if (intValue != 0) {
                    a(intValue, i);
                }
                i2++;
            } catch (SQLiteException unused) {
            } catch (Throwable th) {
                db.endTransaction();
            }
        }
        if (arrayList.size() == 0) {
            q(i);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void a(ArrayList<String> arrayList) {
        SQLiteDatabase db = getDB();
        db.beginTransaction();
        int i = 0;
        while (i < arrayList.size()) {
            try {
                String str = (String) arrayList.get(i);
                if (!(str == null || str.equals("0"))) {
                    p(Integer.parseInt(str));
                }
                i++;
            } catch (SQLiteException unused) {
            } catch (Throwable th) {
                db.endTransaction();
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void a(ArrayList<Integer> arrayList, int i) {
        SQLiteDatabase db = getDB();
        db.beginTransaction();
        try {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                e(i, ((Integer) it.next()).intValue());
            }
            if (h(i) == 0) {
                q(i);
            }
            db.setTransactionSuccessful();
        } catch (SQLiteException unused) {
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x005e A:{Catch:{ SQLiteException -> 0x0065, all -> 0x0062 }} */
    public void e(int r7) {
        /*
        r6 = this;
        r0 = 0;
        r1 = java.lang.Boolean.valueOf(r0);
        r2 = r6.getDB();
        r3 = "select playlist_id from playlist_details where download_status = 0";
        r4 = 0;
        r2 = r2.rawQuery(r3, r4);
        r3 = r2.moveToFirst();
        if (r3 == 0) goto L_0x0027;
    L_0x0016:
        r3 = "playlist_id";
        r3 = r2.getColumnIndex(r3);
        r3 = r2.getInt(r3);
        if (r3 != r7) goto L_0x0027;
    L_0x0022:
        r1 = 1;
        r1 = java.lang.Boolean.valueOf(r1);
    L_0x0027:
        r3 = r6.getDB();
        r3.beginTransaction();
        r5 = r6.f(r7);	 Catch:{ SQLiteException -> 0x0076, all -> 0x006e }
        r6.a(r7, r5);	 Catch:{ SQLiteException -> 0x0076, all -> 0x006e }
        r6.q(r7);	 Catch:{ SQLiteException -> 0x0076, all -> 0x006e }
        r7 = r1.booleanValue();	 Catch:{ SQLiteException -> 0x0076, all -> 0x006e }
        r1 = -1;
        if (r7 == 0) goto L_0x005a;
    L_0x003f:
        r7 = "select playlist_id from playlist_details where download_status=1 limit 1";
        r5 = r6.getDB();	 Catch:{ SQLiteException -> 0x0076, all -> 0x006e }
        r7 = r5.rawQuery(r7, r4);	 Catch:{ SQLiteException -> 0x0076, all -> 0x006e }
        r2 = r7.moveToFirst();	 Catch:{ SQLiteException -> 0x0065, all -> 0x0062 }
        if (r2 == 0) goto L_0x005b;
    L_0x004f:
        r2 = "playlist_id";
        r2 = r7.getColumnIndex(r2);	 Catch:{ SQLiteException -> 0x0065, all -> 0x0062 }
        r2 = r7.getInt(r2);	 Catch:{ SQLiteException -> 0x0065, all -> 0x0062 }
        goto L_0x005c;
    L_0x005a:
        r7 = r2;
    L_0x005b:
        r2 = r1;
    L_0x005c:
        if (r2 == r1) goto L_0x0067;
    L_0x005e:
        r6.f(r2, r0);	 Catch:{ SQLiteException -> 0x0065, all -> 0x0062 }
        goto L_0x0067;
    L_0x0062:
        r0 = move-exception;
        r2 = r7;
        goto L_0x006f;
    L_0x0065:
        r2 = r7;
        goto L_0x0076;
    L_0x0067:
        r3.setTransactionSuccessful();	 Catch:{ SQLiteException -> 0x0065, all -> 0x0062 }
        r7.close();
        goto L_0x0079;
    L_0x006e:
        r0 = move-exception;
    L_0x006f:
        r2.close();
        r3.endTransaction();
        throw r0;
    L_0x0076:
        r2.close();
    L_0x0079:
        r3.endTransaction();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.e(int):void");
    }

    public ArrayList<Integer> f(int i) {
        SQLiteDatabase db = getDB();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select track_id from track_details where playlist_id = ");
        stringBuilder.append(i);
        stringBuilder.append(" ORDER BY ");
        stringBuilder.append("track_position_in_playlist");
        stringBuilder.append(" ASC");
        Cursor rawQuery = db.rawQuery(stringBuilder.toString(), null);
        ArrayList arrayList = new ArrayList();
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("track_id"))));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        return arrayList;
    }

    public ArrayList<Integer> g(int i) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = getDB();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select distinct track_id from track_details group by track_id having count(*) < 2 and has_downloaded=1 and playlist_id=");
        stringBuilder.append(i);
        Cursor rawQuery = db.rawQuery(stringBuilder.toString(), null);
        if (rawQuery.moveToFirst()) {
            do {
                arrayList.add(Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("track_id"))));
            } while (rawQuery.moveToNext());
        }
        rawQuery.close();
        if (arrayList.size() != 0) {
            return arrayList;
        }
        return null;
    }

    public Boolean a(int i, DownloadHTTPStatus downloadHTTPStatus) {
        SQLiteDatabase db = getDB();
        db.beginTransaction();
        try {
            if (downloadHTTPStatus == DownloadHTTPStatus.SUCCESS) {
                b(i, 1);
            } else {
                b(i, -1);
            }
            db.setTransactionSuccessful();
        } catch (SQLiteException unused) {
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
        return Boolean.valueOf(true);
    }

    public void b(int i, int i2) {
        if (i2 == 1) {
            Track d = d(String.valueOf(i));
            StringBuilder stringBuilder;
            if (d.isFreeDownloadEnabled()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Free Download_");
                stringBuilder.append(d.getBusinessObjId());
                u.a().a("Download", "Download Successful", stringBuilder.toString());
            } else {
                u a = u.a();
                String str = "Download";
                String str2 = "Download Successful";
                stringBuilder = new StringBuilder();
                stringBuilder.append("Track - ");
                String englishName = (d == null || d.getEnglishName() == null) ? "" : d.getEnglishName();
                stringBuilder.append(englishName);
                a.a(str, str2, stringBuilder.toString());
            }
        }
        try {
            i(i, i2);
            j(i, i2);
            i2 = i2 == -2 ? 0 : -1;
            if (i2 != -1) {
                com.managers.l.a().a(i, c.c, i2);
            }
        } catch (SQLException unused) {
        }
    }

    private int b(BusinessObject businessObject, int i, ArrayList<String> arrayList) {
        BusinessObject businessObject2 = businessObject;
        ArrayList<String> arrayList2 = arrayList;
        SQLiteDatabase db = getDB();
        int i2 = c.b;
        int parseInt = Integer.parseInt(businessObject.getBusinessObjId());
        ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
        try {
            int i3;
            int i4;
            db.beginTransaction();
            int i5 = 0;
            boolean z = arrayList2 != null;
            if (arrListBusinessObj != null) {
                int i6 = 0;
                i3 = -1;
                while (i6 < arrListBusinessObj.size()) {
                    Track track = (Track) arrListBusinessObj.get(i6);
                    int i7 = DownloadStatus.DOWNLOADED == DownloadManager.c().e(Integer.parseInt(track.getBusinessObjId())) ? 1 : DownloadStatus.TRIED_BUT_FAILED == DownloadManager.c().e(Integer.parseInt(track.getBusinessObjId())) ? -1 : (arrayList2 == null || arrayList2.contains(track.getBusinessObjId())) ? i5 : -2;
                    int i8 = i6;
                    int i9 = i5;
                    a(track, parseInt, i6, i7, z);
                    i3 = Integer.parseInt(((Track) arrListBusinessObj.get(i9)).getBusinessObjId());
                    i6 = i8 + 1;
                    i5 = i9;
                }
                i4 = i3;
                i3 = i;
            } else {
                i3 = -1;
                i4 = i3;
            }
            if (businessObject2 instanceof Album) {
                i2 = c.a;
            }
            a(parseInt, i2, businessObject2, i3);
            if (arrayList2 == null) {
                com.managers.l.a().a(parseInt, i2);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            if (arrayList2 == null) {
                b(arrListBusinessObj);
            }
            return i4;
        } catch (Exception unused) {
            db.endTransaction();
            if (arrayList2 == null) {
                b(arrListBusinessObj);
            }
            return -1;
        } catch (Throwable th) {
            Throwable th2 = th;
            db.endTransaction();
            if (arrayList2 == null) {
                b(arrListBusinessObj);
            }
        }
    }

    private void a(int i, int i2, BusinessObject businessObject, int i3) {
        ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
        businessObject.setArrListBusinessObj(null);
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("playlist_id", Integer.valueOf(i));
            contentValues.put("download_status", Integer.valueOf(i3));
            contentValues.put("playlist_content", Serializer.serialize(businessObject));
            contentValues.put("playlist_name", businessObject.getRawName());
            contentValues.put("playlist_type", Integer.valueOf(i2));
            if (i3 == 1 || i3 == 0) {
                contentValues.put("download_time", Long.valueOf(System.currentTimeMillis()));
            }
            getDB().insert(com.e.a.e.h.a, null, contentValues);
            DownloadManager.c().a(i, i3, null, null);
        } catch (Exception unused) {
        } catch (Throwable th) {
            businessObject.setArrListBusinessObj(arrListBusinessObj);
        }
        businessObject.setArrListBusinessObj(arrListBusinessObj);
    }

    private void a(Track track, int i, int i2, int i3, boolean z) {
        try {
            if (!track.isLocalMedia()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("track_id", track.getBusinessObjId());
                contentValues.put("playlist_id", Integer.valueOf(i));
                contentValues.put("track_position_in_playlist", Integer.valueOf(i2));
                contentValues.put("has_downloaded", Integer.valueOf(i3));
                getDB().insert(k.a, null, contentValues);
                if (z) {
                    a(track, i3);
                }
            }
        } catch (Exception unused) {
        }
    }

    private int r(int i) {
        Throwable th;
        SQLiteDatabase db = getDB();
        Cursor cursor = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT DISTINCT track_details.track_id,track_details.track_position_in_playlist FROM track_details WHERE track_details.playlist_id=");
            stringBuilder.append(i);
            stringBuilder.append(" AND track_details.has_downloaded = 0 ");
            stringBuilder.append("AND track_details.track_position_in_playlist=");
            stringBuilder.append("(SELECT MIN(track_details.track_position_in_playlist) ");
            stringBuilder.append("FROM track_details ");
            stringBuilder.append("WHERE track_details.playlist_id=");
            stringBuilder.append(i);
            stringBuilder.append(" AND has_downloaded = 0)");
            this.c = stringBuilder.toString();
            Cursor rawQuery = db.rawQuery(this.c, null);
            try {
                int i2;
                if (rawQuery.moveToFirst()) {
                    rawQuery.getInt(rawQuery.getColumnIndex("track_position_in_playlist"));
                    i2 = rawQuery.getInt(rawQuery.getColumnIndex("track_id"));
                } else {
                    i2 = -1;
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                return i2;
            } catch (Throwable th2) {
                th = th2;
                cursor = rawQuery;
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor.close();
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x004a  */
    /* JADX WARNING: Missing block: B:10:0x0030, code skipped:
            if (r2.isClosed() == false) goto L_0x0032;
     */
    /* JADX WARNING: Missing block: B:11:0x0032, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:25:0x004e, code skipped:
            if (r2.isClosed() == false) goto L_0x0032;
     */
    private int z() {
        /*
        r4 = this;
        r0 = 0;
        r1 = -1;
        r2 = r4.getDB();	 Catch:{ SQLiteException -> 0x0047, all -> 0x0038 }
        r3 = "SELECT DISTINCT track_details.track_id,track_details.track_position_in_playlist, track_details.has_downloaded FROM track_details WHERE (track_details.has_downloaded = 0)AND track_details.track_position_in_playlist=(SELECT MIN(track_details.track_position_in_playlist) FROM track_details WHERE (has_downloaded = 0))";
        r4.c = r3;	 Catch:{ SQLiteException -> 0x0047, all -> 0x0038 }
        r3 = r4.c;	 Catch:{ SQLiteException -> 0x0047, all -> 0x0038 }
        r2 = r2.rawQuery(r3, r0);	 Catch:{ SQLiteException -> 0x0047, all -> 0x0038 }
        r0 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x0048, all -> 0x0036 }
        if (r0 == 0) goto L_0x002a;
    L_0x0016:
        r0 = "track_position_in_playlist";
        r0 = r2.getColumnIndex(r0);	 Catch:{ SQLiteException -> 0x0048, all -> 0x0036 }
        r2.getInt(r0);	 Catch:{ SQLiteException -> 0x0048, all -> 0x0036 }
        r0 = "track_id";
        r0 = r2.getColumnIndex(r0);	 Catch:{ SQLiteException -> 0x0048, all -> 0x0036 }
        r0 = r2.getInt(r0);	 Catch:{ SQLiteException -> 0x0048, all -> 0x0036 }
        r1 = r0;
    L_0x002a:
        if (r2 == 0) goto L_0x0051;
    L_0x002c:
        r0 = r2.isClosed();
        if (r0 != 0) goto L_0x0051;
    L_0x0032:
        r2.close();
        goto L_0x0051;
    L_0x0036:
        r0 = move-exception;
        goto L_0x003b;
    L_0x0038:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x003b:
        if (r2 == 0) goto L_0x0046;
    L_0x003d:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x0046;
    L_0x0043:
        r2.close();
    L_0x0046:
        throw r0;
    L_0x0047:
        r2 = r0;
    L_0x0048:
        if (r2 == 0) goto L_0x0051;
    L_0x004a:
        r0 = r2.isClosed();
        if (r0 != 0) goto L_0x0051;
    L_0x0050:
        goto L_0x0032;
    L_0x0051:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.z():int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0089  */
    /* JADX WARNING: Missing block: B:24:0x0073, code skipped:
            if (r2.isClosed() == false) goto L_0x008f;
     */
    /* JADX WARNING: Missing block: B:38:0x008d, code skipped:
            if (r2.isClosed() == false) goto L_0x008f;
     */
    /* JADX WARNING: Missing block: B:39:0x008f, code skipped:
            r2.close();
     */
    public java.util.concurrent.ConcurrentHashMap<java.lang.Integer, com.managers.DownloadManager.DownloadStatus> e() {
        /*
        r6 = this;
        r0 = new java.util.concurrent.ConcurrentHashMap;
        r0.<init>();
        r1 = 0;
        r2 = r6.getDB();	 Catch:{ SQLiteException -> 0x0086, all -> 0x0078 }
        r0.clear();	 Catch:{ SQLiteException -> 0x0086, all -> 0x0078 }
        r3 = new java.lang.StringBuilder;	 Catch:{ SQLiteException -> 0x0086, all -> 0x0078 }
        r3.<init>();	 Catch:{ SQLiteException -> 0x0086, all -> 0x0078 }
        r4 = "SELECT distinct track_id,has_downloaded FROM ";
        r3.append(r4);	 Catch:{ SQLiteException -> 0x0086, all -> 0x0078 }
        r4 = com.e.a.e.l.a;	 Catch:{ SQLiteException -> 0x0086, all -> 0x0078 }
        r3.append(r4);	 Catch:{ SQLiteException -> 0x0086, all -> 0x0078 }
        r3 = r3.toString();	 Catch:{ SQLiteException -> 0x0086, all -> 0x0078 }
        r2 = r2.rawQuery(r3, r1);	 Catch:{ SQLiteException -> 0x0086, all -> 0x0078 }
        r1 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        if (r1 == 0) goto L_0x006d;
    L_0x002a:
        r1 = "track_id";
        r1 = r2.getColumnIndex(r1);	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        r1 = r2.getInt(r1);	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        r3 = "has_downloaded";
        r3 = r2.getColumnIndex(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        r3 = r2.getInt(r3);	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        r4 = com.managers.DownloadManager.DownloadStatus.QUEUED;	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        if (r3 != 0) goto L_0x004f;
    L_0x0042:
        r5 = com.managers.DownloadManager.c();	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        r5 = r5.k();	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        if (r1 != r5) goto L_0x004f;
    L_0x004c:
        r4 = com.managers.DownloadManager.DownloadStatus.DOWNLOADING;	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        goto L_0x0060;
    L_0x004f:
        r5 = 1;
        if (r3 != r5) goto L_0x0055;
    L_0x0052:
        r4 = com.managers.DownloadManager.DownloadStatus.DOWNLOADED;	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        goto L_0x0060;
    L_0x0055:
        r5 = -2;
        if (r3 != r5) goto L_0x005b;
    L_0x0058:
        r4 = com.managers.DownloadManager.DownloadStatus.PAUSED;	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        goto L_0x0060;
    L_0x005b:
        r5 = -1;
        if (r3 != r5) goto L_0x0060;
    L_0x005e:
        r4 = com.managers.DownloadManager.DownloadStatus.TRIED_BUT_FAILED;	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
    L_0x0060:
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        r0.put(r1, r4);	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        r1 = r2.moveToNext();	 Catch:{ SQLiteException -> 0x0087, all -> 0x0076 }
        if (r1 != 0) goto L_0x002a;
    L_0x006d:
        if (r2 == 0) goto L_0x0092;
    L_0x006f:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x0092;
    L_0x0075:
        goto L_0x008f;
    L_0x0076:
        r0 = move-exception;
        goto L_0x007a;
    L_0x0078:
        r0 = move-exception;
        r2 = r1;
    L_0x007a:
        if (r2 == 0) goto L_0x0085;
    L_0x007c:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x0085;
    L_0x0082:
        r2.close();
    L_0x0085:
        throw r0;
    L_0x0086:
        r2 = r1;
    L_0x0087:
        if (r2 == 0) goto L_0x0092;
    L_0x0089:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x0092;
    L_0x008f:
        r2.close();
    L_0x0092:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.e():java.util.concurrent.ConcurrentHashMap");
    }

    public ConcurrentHashMap<Integer, Boolean> f() {
        Throwable th;
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Cursor cursor = null;
        try {
            SQLiteDatabase db = getDB();
            concurrentHashMap.clear();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT distinct track_id FROM ");
            stringBuilder.append(l.a);
            stringBuilder.append(" WHERE ");
            stringBuilder.append("free_download");
            stringBuilder.append("=1 AND ");
            stringBuilder.append("has_downloaded");
            stringBuilder.append("=1");
            Cursor rawQuery = db.rawQuery(stringBuilder.toString(), null);
            try {
                if (rawQuery.moveToFirst()) {
                    do {
                        concurrentHashMap.put(Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("track_id"))), Boolean.TRUE);
                    } while (rawQuery.moveToNext());
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
            } catch (SQLiteException unused) {
                cursor = rawQuery;
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                return concurrentHashMap;
            } catch (Throwable th2) {
                th = th2;
                cursor = rawQuery;
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException unused2) {
            cursor.close();
            return concurrentHashMap;
        } catch (Throwable th3) {
            th = th3;
            cursor.close();
            throw th;
        }
        return concurrentHashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x007d  */
    /* JADX WARNING: Missing block: B:23:0x0067, code skipped:
            if (r2.isClosed() == false) goto L_0x0083;
     */
    /* JADX WARNING: Missing block: B:37:0x0081, code skipped:
            if (r2.isClosed() == false) goto L_0x0083;
     */
    /* JADX WARNING: Missing block: B:38:0x0083, code skipped:
            r2.close();
     */
    public java.util.concurrent.ConcurrentHashMap<java.lang.Integer, com.managers.DownloadManager.DownloadStatus> g() {
        /*
        r5 = this;
        r0 = new java.util.concurrent.ConcurrentHashMap;
        r0.<init>();
        r1 = 0;
        r2 = r5.getDB();	 Catch:{ SQLiteException -> 0x007a, all -> 0x006c }
        r0.clear();	 Catch:{ SQLiteException -> 0x007a, all -> 0x006c }
        r3 = "SELECT * FROM playlist_details";
        r2 = r2.rawQuery(r3, r1);	 Catch:{ SQLiteException -> 0x007a, all -> 0x006c }
        r1 = r2.moveToFirst();	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        if (r1 == 0) goto L_0x0061;
    L_0x0019:
        r1 = "playlist_id";
        r1 = r2.getColumnIndex(r1);	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        r1 = r2.getInt(r1);	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        r3 = "download_status";
        r3 = r2.getColumnIndex(r3);	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        r3 = r2.getInt(r3);	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        r4 = com.managers.DownloadManager.DownloadStatus.QUEUED;	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        switch(r3) {
            case -2: goto L_0x0049;
            case -1: goto L_0x0039;
            case 0: goto L_0x0036;
            case 1: goto L_0x0033;
            default: goto L_0x0032;
        };	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
    L_0x0032:
        goto L_0x0054;
    L_0x0033:
        r4 = com.managers.DownloadManager.DownloadStatus.QUEUED;	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        goto L_0x0054;
    L_0x0036:
        r4 = com.managers.DownloadManager.DownloadStatus.DOWNLOADING;	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        goto L_0x0054;
    L_0x0039:
        r3 = r5.i(r1);	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        r4 = r5.h(r1);	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        if (r3 != r4) goto L_0x0046;
    L_0x0043:
        r4 = com.managers.DownloadManager.DownloadStatus.DOWNLOADED;	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        goto L_0x0054;
    L_0x0046:
        r4 = com.managers.DownloadManager.DownloadStatus.PARTIALLY_DOWNLOADED;	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        goto L_0x0054;
    L_0x0049:
        r3 = r5.i(r1);	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        if (r3 == 0) goto L_0x0052;
    L_0x004f:
        r4 = com.managers.DownloadManager.DownloadStatus.PARTIALLY_DOWNLOADED;	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        goto L_0x0054;
    L_0x0052:
        r4 = com.managers.DownloadManager.DownloadStatus.PAUSED;	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
    L_0x0054:
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        r0.put(r1, r4);	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        r1 = r2.moveToNext();	 Catch:{ SQLiteException -> 0x007b, all -> 0x006a }
        if (r1 != 0) goto L_0x0019;
    L_0x0061:
        if (r2 == 0) goto L_0x0086;
    L_0x0063:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x0086;
    L_0x0069:
        goto L_0x0083;
    L_0x006a:
        r0 = move-exception;
        goto L_0x006e;
    L_0x006c:
        r0 = move-exception;
        r2 = r1;
    L_0x006e:
        if (r2 == 0) goto L_0x0079;
    L_0x0070:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x0079;
    L_0x0076:
        r2.close();
    L_0x0079:
        throw r0;
    L_0x007a:
        r2 = r1;
    L_0x007b:
        if (r2 == 0) goto L_0x0086;
    L_0x007d:
        r1 = r2.isClosed();
        if (r1 != 0) goto L_0x0086;
    L_0x0083:
        r2.close();
    L_0x0086:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.g():java.util.concurrent.ConcurrentHashMap");
    }

    public DownloadStatus a(int i, BusinessObject businessObject) {
        if (businessObject instanceof Track) {
            switch (i) {
                case -2:
                    return DownloadStatus.PAUSED;
                case -1:
                    return DownloadStatus.TRIED_BUT_FAILED;
                case 0:
                    return DownloadStatus.QUEUED;
                case 1:
                    return DownloadStatus.DOWNLOADED;
            }
        }
        switch (i) {
            case -2:
                return DownloadStatus.PAUSED;
            case -1:
                if (i(Integer.parseInt(businessObject.getBusinessObjId())) == h(Integer.parseInt(businessObject.getBusinessObjId()))) {
                    return DownloadStatus.DOWNLOADED;
                }
                return DownloadStatus.PARTIALLY_DOWNLOADED;
            case 0:
                return DownloadStatus.DOWNLOADING;
            case 1:
                return DownloadStatus.QUEUED;
        }
        return null;
    }

    public int h(int i) {
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT COUNT(*) FROM track_details where playlist_id=");
            stringBuilder.append(i);
            return (int) db.compileStatement(stringBuilder.toString()).simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return 0;
        }
    }

    public int i(int i) {
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT COUNT(*) FROM track_details where has_downloaded=1 AND playlist_id=");
            stringBuilder.append(i);
            return (int) db.compileStatement(stringBuilder.toString()).simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return 0;
        }
    }

    public int h() {
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT COUNT(*) FROM ");
            stringBuilder.append(l.a);
            stringBuilder.append(" where has_downloaded=1");
            return (int) db.compileStatement(stringBuilder.toString()).simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return 0;
        }
    }

    public int i() {
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT COUNT(*) FROM ");
            stringBuilder.append(l.a);
            return (int) db.compileStatement(stringBuilder.toString()).simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return 0;
        }
    }

    public int j() {
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT COUNT(*) FROM ");
            stringBuilder.append(k.a);
            stringBuilder.append(" where has_downloaded=-1");
            return (int) db.compileStatement(stringBuilder.toString()).simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return 0;
        }
    }

    public int k() {
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT COUNT(*) FROM ");
            stringBuilder.append(l.a);
            stringBuilder.append(" where has_downloaded = ");
            stringBuilder.append(0);
            return (int) db.compileStatement(stringBuilder.toString()).simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return 0;
        }
    }

    public int l() {
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT COUNT(*) FROM ");
            stringBuilder.append(l.a);
            stringBuilder.append(" where has_downloaded = ");
            stringBuilder.append(-2);
            return (int) db.compileStatement(stringBuilder.toString()).simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return 0;
        }
    }

    public int m() {
        int p = p();
        if (p == -1) {
            p = C();
        }
        if (p != -1) {
            int r = r(p);
            if (r != -1) {
                return r;
            }
            t(p);
            return m();
        }
        p = z();
        if (p == -1) {
            DownloadManager.c().k(-1);
            LocalBroadcastManager.getInstance(GaanaApplication.getContext()).sendBroadcast(new Intent("broadcast_playlist_update_status_for_download_progress"));
        }
        return p;
    }

    public int n() {
        int o = o();
        if (o == -1) {
            o = B();
        }
        if (o == -1) {
            return A();
        }
        int r = r(o);
        if (r != -1) {
            return r;
        }
        s(o);
        return r(o());
    }

    private int A() {
        Throwable th;
        Cursor cursor = null;
        int i = -1;
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT DISTINCT track_details.track_id, track_details.has_downloaded FROM track_details WHERE (track_details.has_downloaded = 0)AND track_details.playlist_id IN (");
            stringBuilder.append(GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getMiniPacks());
            stringBuilder.append(")");
            this.c = stringBuilder.toString();
            Cursor rawQuery = db.rawQuery(this.c, null);
            try {
                if (rawQuery.moveToFirst()) {
                    i = rawQuery.getInt(rawQuery.getColumnIndex("track_id"));
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
            } catch (SQLiteException unused) {
                cursor = rawQuery;
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                return i;
            } catch (Throwable th2) {
                th = th2;
                cursor = rawQuery;
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLiteException unused2) {
            cursor.close();
            return i;
        } catch (Throwable th3) {
            th = th3;
            cursor.close();
            throw th;
        }
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0058  */
    /* JADX WARNING: Missing block: B:12:0x004e, code skipped:
            if (r2 != null) goto L_0x0066;
     */
    /* JADX WARNING: Missing block: B:22:0x0064, code skipped:
            if (r2 != null) goto L_0x0066;
     */
    /* JADX WARNING: Missing block: B:24:0x0066, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:25:0x0069, code skipped:
            getDB().endTransaction();
     */
    /* JADX WARNING: Missing block: B:26:0x0070, code skipped:
            return r1;
     */
    public int o() {
        /*
        r6 = this;
        r0 = r6.getDB();
        r0.beginTransaction();
        r0 = 0;
        r1 = -1;
        r2 = "SELECT playlist_details.playlist_content FROM playlist_details WHERE playlist_details.download_status = 0";
        r3 = r6.getDB();	 Catch:{ Exception -> 0x0063, all -> 0x0053 }
        r2 = r3.rawQuery(r2, r0);	 Catch:{ Exception -> 0x0063, all -> 0x0053 }
        r3 = r2.moveToFirst();	 Catch:{ Exception -> 0x0064, all -> 0x0051 }
        if (r3 == 0) goto L_0x004e;
    L_0x0019:
        r3 = "playlist_content";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x0064, all -> 0x0051 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x0064, all -> 0x0051 }
        r3 = com.library.util.Serializer.deserialize(r3);	 Catch:{ Exception -> 0x0064, all -> 0x0051 }
        r3 = (com.gaana.models.BusinessObject) r3;	 Catch:{ Exception -> 0x0064, all -> 0x0051 }
        r4 = r3.getBusinessObjId();	 Catch:{ Exception -> 0x0064, all -> 0x0051 }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ Exception -> 0x0064, all -> 0x0051 }
        r5 = com.managers.ap.a();	 Catch:{ Exception -> 0x0064, all -> 0x0051 }
        r3 = r5.a(r3, r0);	 Catch:{ Exception -> 0x0064, all -> 0x0051 }
        if (r3 == 0) goto L_0x003d;
    L_0x003b:
        r1 = r4;
        goto L_0x0047;
    L_0x003d:
        r3 = 1;
        r6.f(r4, r3);	 Catch:{ Exception -> 0x0064, all -> 0x0051 }
        r3 = r2.moveToNext();	 Catch:{ Exception -> 0x0064, all -> 0x0051 }
        if (r3 != 0) goto L_0x0019;
    L_0x0047:
        r0 = r6.getDB();	 Catch:{ Exception -> 0x0064, all -> 0x0051 }
        r0.setTransactionSuccessful();	 Catch:{ Exception -> 0x0064, all -> 0x0051 }
    L_0x004e:
        if (r2 == 0) goto L_0x0069;
    L_0x0050:
        goto L_0x0066;
    L_0x0051:
        r0 = move-exception;
        goto L_0x0056;
    L_0x0053:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0056:
        if (r2 == 0) goto L_0x005b;
    L_0x0058:
        r2.close();
    L_0x005b:
        r1 = r6.getDB();
        r1.endTransaction();
        throw r0;
    L_0x0063:
        r2 = r0;
    L_0x0064:
        if (r2 == 0) goto L_0x0069;
    L_0x0066:
        r2.close();
    L_0x0069:
        r0 = r6.getDB();
        r0.endTransaction();
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.o():int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0059  */
    /* JADX WARNING: Missing block: B:12:0x0050, code skipped:
            if (r4 != null) goto L_0x0067;
     */
    /* JADX WARNING: Missing block: B:22:0x0065, code skipped:
            if (r4 != null) goto L_0x0067;
     */
    /* JADX WARNING: Missing block: B:24:0x0067, code skipped:
            r4.close();
     */
    /* JADX WARNING: Missing block: B:25:0x006a, code skipped:
            getDB().endTransaction();
     */
    /* JADX WARNING: Missing block: B:26:0x0071, code skipped:
            return;
     */
    private void s(int r4) {
        /*
        r3 = this;
        r0 = r3.getDB();
        r0.beginTransaction();
        r0 = -1;
        r1 = 0;
        r3.f(r4, r0);	 Catch:{ Exception -> 0x0064, all -> 0x0055 }
        r4 = "select playlist_id, playlist_content from playlist_details where download_status=1";
        r0 = r3.getDB();	 Catch:{ Exception -> 0x0064, all -> 0x0055 }
        r4 = r0.rawQuery(r4, r1);	 Catch:{ Exception -> 0x0064, all -> 0x0055 }
        r0 = r4.moveToFirst();	 Catch:{ Exception -> 0x0065, all -> 0x0053 }
        if (r0 == 0) goto L_0x0049;
    L_0x001c:
        r0 = "playlist_content";
        r0 = r4.getColumnIndex(r0);	 Catch:{ Exception -> 0x0065, all -> 0x0053 }
        r0 = r4.getString(r0);	 Catch:{ Exception -> 0x0065, all -> 0x0053 }
        r0 = com.library.util.Serializer.deserialize(r0);	 Catch:{ Exception -> 0x0065, all -> 0x0053 }
        r0 = (com.gaana.models.BusinessObject) r0;	 Catch:{ Exception -> 0x0065, all -> 0x0053 }
        r2 = com.managers.ap.a();	 Catch:{ Exception -> 0x0065, all -> 0x0053 }
        r2 = r2.a(r0, r1);	 Catch:{ Exception -> 0x0065, all -> 0x0053 }
        if (r2 == 0) goto L_0x0043;
    L_0x0036:
        r0 = r0.getBusinessObjId();	 Catch:{ Exception -> 0x0065, all -> 0x0053 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0065, all -> 0x0053 }
        r1 = 0;
        r3.f(r0, r1);	 Catch:{ Exception -> 0x0065, all -> 0x0053 }
        goto L_0x0049;
    L_0x0043:
        r0 = r4.moveToNext();	 Catch:{ Exception -> 0x0065, all -> 0x0053 }
        if (r0 != 0) goto L_0x001c;
    L_0x0049:
        r0 = r3.getDB();	 Catch:{ Exception -> 0x0065, all -> 0x0053 }
        r0.setTransactionSuccessful();	 Catch:{ Exception -> 0x0065, all -> 0x0053 }
        if (r4 == 0) goto L_0x006a;
    L_0x0052:
        goto L_0x0067;
    L_0x0053:
        r0 = move-exception;
        goto L_0x0057;
    L_0x0055:
        r0 = move-exception;
        r4 = r1;
    L_0x0057:
        if (r4 == 0) goto L_0x005c;
    L_0x0059:
        r4.close();
    L_0x005c:
        r4 = r3.getDB();
        r4.endTransaction();
        throw r0;
    L_0x0064:
        r4 = r1;
    L_0x0065:
        if (r4 == 0) goto L_0x006a;
    L_0x0067:
        r4.close();
    L_0x006a:
        r4 = r3.getDB();
        r4.endTransaction();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.s(int):void");
    }

    private int B() {
        int parseInt;
        Cursor rawQuery = getDB().rawQuery("select playlist_id, playlist_content from playlist_details where download_status=1", null);
        if (rawQuery.moveToFirst()) {
            do {
                BusinessObject businessObject = (BusinessObject) Serializer.deserialize(rawQuery.getString(rawQuery.getColumnIndex("playlist_content")));
                if (ap.a().a(businessObject, null)) {
                    parseInt = Integer.parseInt(businessObject.getBusinessObjId());
                    break;
                }
            } while (rawQuery.moveToNext());
            parseInt = -1;
        } else {
            parseInt = -1;
        }
        rawQuery.close();
        try {
            getDB().beginTransaction();
            if (parseInt != -1) {
                f(parseInt, 0);
            } else if (!DownloadManager.c().D()) {
                DownloadManager.c().d(true);
                ContentValues contentValues = new ContentValues();
                contentValues.put("has_downloaded", Integer.valueOf(0));
                getDB().update(k.a, contentValues, "has_downloaded=-1", null);
                ContentValues contentValues2 = new ContentValues();
                contentValues.put("has_downloaded", Integer.valueOf(0));
                getDB().update(l.a, contentValues2, "has_downloaded=-1", null);
            }
            getDB().setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            getDB().endTransaction();
        }
        getDB().endTransaction();
        return parseInt;
    }

    public int p() {
        Cursor rawQuery = getDB().rawQuery("SELECT playlist_details.playlist_id FROM playlist_details WHERE playlist_details.download_status = 0", null);
        int i = rawQuery.moveToFirst() ? rawQuery.getInt(rawQuery.getColumnIndex("playlist_id")) : -1;
        rawQuery.close();
        return i;
    }

    public void q() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("has_downloaded", Integer.valueOf(0));
        getDB().update(k.a, contentValues, "has_downloaded=-1", null);
        contentValues = new ContentValues();
        contentValues.put("has_downloaded", Integer.valueOf(0));
        getDB().update(l.a, contentValues, "has_downloaded=-1", null);
    }

    private int C() {
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select playlist_id from ");
        stringBuilder.append(com.e.a.e.h.a);
        stringBuilder.append(" where ");
        stringBuilder.append("download_status");
        stringBuilder.append(" = ");
        stringBuilder.append(1);
        stringBuilder.append(" limit 1");
        Cursor rawQuery;
        try {
            rawQuery = getDB().rawQuery(stringBuilder.toString(), null);
            try {
                int i = rawQuery.moveToFirst() ? rawQuery.getInt(rawQuery.getColumnIndex("playlist_id")) : -1;
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                try {
                    getDB().beginTransaction();
                    if (i != -1) {
                        f(i, 0);
                    }
                    getDB().setTransactionSuccessful();
                } catch (Exception unused) {
                } catch (Throwable th2) {
                    getDB().endTransaction();
                }
                getDB().endTransaction();
                return i;
            } catch (Throwable th3) {
                th = th3;
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            rawQuery = null;
            rawQuery.close();
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0046  */
    /* JADX WARNING: Missing block: B:10:0x002b, code skipped:
            if (r0.isClosed() == false) goto L_0x004c;
     */
    /* JADX WARNING: Missing block: B:25:0x004a, code skipped:
            if (r0.isClosed() == false) goto L_0x004c;
     */
    /* JADX WARNING: Missing block: B:26:0x004c, code skipped:
            r0.close();
     */
    public void r() {
        /*
        r5 = this;
        r0 = "select playlist_id from playlist_details where download_status=1 limit 1";
        r1 = r5.getDB();
        r2 = 0;
        r1.beginTransaction();	 Catch:{ Exception -> 0x0043, all -> 0x0030 }
        r0 = r1.rawQuery(r0, r2);	 Catch:{ Exception -> 0x0043, all -> 0x0030 }
        r2 = r0.moveToFirst();	 Catch:{ Exception -> 0x0044, all -> 0x002e }
        if (r2 == 0) goto L_0x0022;
    L_0x0014:
        r2 = "playlist_id";
        r2 = r0.getColumnIndex(r2);	 Catch:{ Exception -> 0x0044, all -> 0x002e }
        r2 = r0.getInt(r2);	 Catch:{ Exception -> 0x0044, all -> 0x002e }
        r3 = 0;
        r5.h(r2, r3);	 Catch:{ Exception -> 0x0044, all -> 0x002e }
    L_0x0022:
        r1.setTransactionSuccessful();	 Catch:{ Exception -> 0x0044, all -> 0x002e }
        if (r0 == 0) goto L_0x004f;
    L_0x0027:
        r2 = r0.isClosed();
        if (r2 != 0) goto L_0x004f;
    L_0x002d:
        goto L_0x004c;
    L_0x002e:
        r2 = move-exception;
        goto L_0x0034;
    L_0x0030:
        r0 = move-exception;
        r4 = r2;
        r2 = r0;
        r0 = r4;
    L_0x0034:
        if (r0 == 0) goto L_0x003f;
    L_0x0036:
        r3 = r0.isClosed();
        if (r3 != 0) goto L_0x003f;
    L_0x003c:
        r0.close();
    L_0x003f:
        r1.endTransaction();
        throw r2;
    L_0x0043:
        r0 = r2;
    L_0x0044:
        if (r0 == 0) goto L_0x004f;
    L_0x0046:
        r2 = r0.isClosed();
        if (r2 != 0) goto L_0x004f;
    L_0x004c:
        r0.close();
    L_0x004f:
        r1.endTransaction();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.r():void");
    }

    private void t(int i) {
        getDB().beginTransaction();
        f(i, -1);
        Cursor rawQuery = getDB().rawQuery("select playlist_id from playlist_details where download_status=1 limit 1", null);
        try {
            if (rawQuery.moveToFirst()) {
                f(rawQuery.getInt(rawQuery.getColumnIndex("playlist_id")), 0);
            }
            getDB().setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            rawQuery.close();
            getDB().endTransaction();
        }
        rawQuery.close();
        getDB().endTransaction();
    }

    public Boolean j(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select playlist_id from playlist_details where playlist_id=");
        stringBuilder.append(i);
        Cursor rawQuery = getDB().rawQuery(stringBuilder.toString(), null);
        try {
            if (rawQuery.moveToFirst()) {
                Boolean valueOf = Boolean.valueOf(true);
                return valueOf;
            }
            rawQuery.close();
            return Boolean.valueOf(false);
        } finally {
            rawQuery.close();
        }
    }

    public Boolean k(int i) {
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select track_id from ");
        stringBuilder.append(l.a);
        stringBuilder.append(" where has_downloaded=1 and track_id = ");
        stringBuilder.append(i);
        String stringBuilder2 = stringBuilder.toString();
        Cursor cursor = null;
        try {
            Cursor rawQuery = getDB().rawQuery(stringBuilder2, null);
            try {
                if (rawQuery.moveToFirst()) {
                    Boolean valueOf = Boolean.valueOf(true);
                    rawQuery.close();
                    return valueOf;
                }
                rawQuery.close();
                return Boolean.valueOf(false);
            } catch (Throwable th2) {
                Throwable th3 = th2;
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

    public Boolean l(int i) {
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select track_id from ");
        stringBuilder.append(k.a);
        stringBuilder.append(" where track_id = ");
        stringBuilder.append(i);
        stringBuilder.append(" and ");
        stringBuilder.append("playlist_id");
        stringBuilder.append(" in (");
        stringBuilder.append(GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getMiniPacks());
        stringBuilder.append(")");
        Cursor rawQuery;
        try {
            rawQuery = getDB().rawQuery(stringBuilder.toString(), null);
            try {
                if (rawQuery.moveToFirst()) {
                    Boolean k = k(i);
                    rawQuery.close();
                    return k;
                }
                rawQuery.close();
                return Boolean.valueOf(false);
            } catch (Throwable th2) {
                th = th2;
                rawQuery.close();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
            rawQuery.close();
            throw th;
        }
    }

    public Boolean c(int i, int i2) {
        SQLiteDatabase db = getDB();
        try {
            db.beginTransaction();
            a(i, i2);
            b(i, -2);
            db.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
        return Boolean.valueOf(false);
    }

    public void s() {
        SQLiteDatabase db = getDB();
        db.beginTransaction();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("DROP TABLE IF EXISTS ");
            stringBuilder.append(k.a);
            db.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("DROP TABLE IF EXISTS ");
            stringBuilder.append(com.e.a.e.h.a);
            db.execSQL(stringBuilder.toString());
            onCreate(db);
            db.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
    }

    public void a(Track track) {
        Throwable th;
        SQLiteDatabase db = getDB();
        int parseInt = Integer.parseInt(track.getBusinessObjId());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM track_details WHERE track_id = ");
        stringBuilder.append(parseInt);
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            int i;
            int i2;
            Cursor rawQuery2;
            rawQuery = db.rawQuery(stringBuilder.toString(), null);
            try {
                i = rawQuery.moveToFirst() ? rawQuery.getInt(rawQuery.getColumnIndex("playlist_id")) : -1;
                if (i == -1) {
                    a((BusinessObject) track, null);
                }
                db.beginTransaction();
                i2 = 0;
                b(parseInt, 0);
                rawQuery2 = getDB().rawQuery("select * from playlist_details where download_status = 0", null);
            } catch (SQLException unused) {
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                db.endTransaction();
            } catch (Throwable th2) {
                th = th2;
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                db.endTransaction();
                throw th;
            }
            try {
                if (rawQuery2.moveToFirst()) {
                    if (i == rawQuery2.getInt(rawQuery2.getColumnIndex("playlist_id"))) {
                        h(i, i2);
                        db.setTransactionSuccessful();
                        if (!(rawQuery == null || rawQuery.isClosed())) {
                            rawQuery.close();
                        }
                        if (!(rawQuery2 == null || rawQuery2.isClosed())) {
                            rawQuery2.close();
                        }
                        db.endTransaction();
                    }
                } else if (DownloadManager.c().v()) {
                    h(i, i2);
                    db.setTransactionSuccessful();
                    rawQuery.close();
                    rawQuery2.close();
                    db.endTransaction();
                }
                i2 = 1;
                h(i, i2);
                db.setTransactionSuccessful();
                rawQuery.close();
                rawQuery2.close();
            } catch (SQLException unused2) {
                cursor = rawQuery2;
                rawQuery.close();
                cursor.close();
                db.endTransaction();
            } catch (Throwable th3) {
                th = th3;
                cursor = rawQuery2;
                rawQuery.close();
                cursor.close();
                db.endTransaction();
                throw th;
            }
        } catch (SQLException unused3) {
            rawQuery = null;
            rawQuery.close();
            cursor.close();
            db.endTransaction();
        } catch (Throwable th4) {
            th = th4;
            rawQuery = null;
            rawQuery.close();
            cursor.close();
            db.endTransaction();
            throw th;
        }
        db.endTransaction();
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006b  */
    /* JADX WARNING: Missing block: B:14:0x0052, code skipped:
            if (r1.isClosed() == false) goto L_0x0071;
     */
    /* JADX WARNING: Missing block: B:29:0x006f, code skipped:
            if (r1.isClosed() == false) goto L_0x0071;
     */
    /* JADX WARNING: Missing block: B:30:0x0071, code skipped:
            r1.close();
     */
    public void d(int r6, int r7) {
        /*
        r5 = this;
        r0 = r5.getDB();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "SELECT track_details.track_id FROM track_details WHERE track_details.playlist_id = ";
        r1.append(r2);
        r1.append(r6);
        r1 = r1.toString();
        r0.beginTransaction();
        r2 = 0;
        r1 = r0.rawQuery(r1, r2);	 Catch:{ SQLException -> 0x0068, all -> 0x0057 }
        r2 = r1.moveToFirst();	 Catch:{ SQLException -> 0x0069, all -> 0x0055 }
        if (r2 == 0) goto L_0x0043;
    L_0x0023:
        r2 = "track_id";
        r2 = r1.getColumnIndex(r2);	 Catch:{ SQLException -> 0x0069, all -> 0x0055 }
        r2 = r1.getInt(r2);	 Catch:{ SQLException -> 0x0069, all -> 0x0055 }
        r3 = com.managers.DownloadManager.c();	 Catch:{ SQLException -> 0x0069, all -> 0x0055 }
        r3 = r3.e(r2);	 Catch:{ SQLException -> 0x0069, all -> 0x0055 }
        r4 = com.managers.DownloadManager.DownloadStatus.DOWNLOADED;	 Catch:{ SQLException -> 0x0069, all -> 0x0055 }
        if (r3 == r4) goto L_0x003d;
    L_0x0039:
        r3 = -2;
        r5.b(r2, r3);	 Catch:{ SQLException -> 0x0069, all -> 0x0055 }
    L_0x003d:
        r2 = r1.moveToNext();	 Catch:{ SQLException -> 0x0069, all -> 0x0055 }
        if (r2 != 0) goto L_0x0023;
    L_0x0043:
        r1.close();	 Catch:{ SQLException -> 0x0069, all -> 0x0055 }
        r5.f(r6, r7);	 Catch:{ SQLException -> 0x0069, all -> 0x0055 }
        r0.setTransactionSuccessful();	 Catch:{ SQLException -> 0x0069, all -> 0x0055 }
        if (r1 == 0) goto L_0x0074;
    L_0x004e:
        r6 = r1.isClosed();
        if (r6 != 0) goto L_0x0074;
    L_0x0054:
        goto L_0x0071;
    L_0x0055:
        r6 = move-exception;
        goto L_0x0059;
    L_0x0057:
        r6 = move-exception;
        r1 = r2;
    L_0x0059:
        if (r1 == 0) goto L_0x0064;
    L_0x005b:
        r7 = r1.isClosed();
        if (r7 != 0) goto L_0x0064;
    L_0x0061:
        r1.close();
    L_0x0064:
        r0.endTransaction();
        throw r6;
    L_0x0068:
        r1 = r2;
    L_0x0069:
        if (r1 == 0) goto L_0x0074;
    L_0x006b:
        r6 = r1.isClosed();
        if (r6 != 0) goto L_0x0074;
    L_0x0071:
        r1.close();
    L_0x0074:
        r0.endTransaction();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.d(int, int):void");
    }

    private void f(int i, int i2) {
        ContentValues contentValues;
        Throwable e;
        Cursor cursor;
        SQLiteDatabase db = getDB();
        Cursor cursor2 = null;
        if (i2 == 1) {
            try {
                contentValues = new ContentValues();
                contentValues.put("download_time", Long.valueOf(System.currentTimeMillis()));
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    ThrowableExtension.printStackTrace(e);
                    if (cursor2 != null) {
                        return;
                    }
                } catch (Throwable th) {
                    e = th;
                    cursor = cursor2;
                    cursor.close();
                    throw e;
                }
            }
        }
        contentValues = null;
        cursor = getDB().rawQuery("select * from playlist_details where download_status =0", null);
        try {
            String str;
            if (!cursor.moveToFirst() && DownloadManager.c().v() && i2 == 0) {
                if (contentValues == null) {
                    contentValues = new ContentValues();
                }
                contentValues.put("download_time", Long.valueOf(System.currentTimeMillis()));
            }
            cursor.close();
            if (contentValues != null) {
                str = com.e.a.e.h.a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("playlist_id = ");
                stringBuilder.append(i);
                db.update(str, contentValues, stringBuilder.toString(), null);
            }
            if (h(i, i2) > 0 && (i2 == -1 || i2 == 0 || i2 == -2)) {
                int d = d(i);
                str = "downloaded";
                if (i2 == 0) {
                    str = "downloading";
                } else if (i2 == -2) {
                    str = "pause";
                } else if (i2 == -1) {
                    String str2 = d == c.a ? "Album" : d == c.b ? "Playlist" : "Track";
                    CharSequence charSequence = "";
                    if (!TextUtils.isEmpty(String.valueOf(i))) {
                        charSequence = e(String.valueOf(i));
                    }
                    if (!TextUtils.isEmpty(charSequence)) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(str2);
                        stringBuilder2.append(" - ");
                        stringBuilder2.append(charSequence);
                        u.a().a("Download", "Download Successful", stringBuilder2.toString());
                    }
                }
                com.managers.l.a().a(i, d, str);
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch (SQLiteException e3) {
            e = e3;
            cursor2 = cursor;
            ThrowableExtension.printStackTrace(e);
            if (cursor2 != null && !cursor2.isClosed()) {
                cursor2.close();
            }
        } catch (Throwable th2) {
            e = th2;
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0083 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0031, PHI: r3 } */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:24:0x0083, code skipped:
            r7 = th;
     */
    /* JADX WARNING: Missing block: B:25:0x0084, code skipped:
            r1 = r3;
     */
    public void m(int r7) {
        /*
        r6 = this;
        r0 = r6.getDB();
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "SELECT track_id, track_details.track_id FROM track_details WHERE track_details.playlist_id = ";
        r1.append(r2);
        r1.append(r7);
        r2 = " AND has_downloaded IN  (";
        r1.append(r2);
        r2 = -2;
        r1.append(r2);
        r2 = ", ";
        r1.append(r2);
        r2 = -1;
        r1.append(r2);
        r2 = ")";
        r1.append(r2);
        r1 = r1.toString();
        r0.beginTransaction();
        r2 = 1;
        r3 = 0;
        r1 = r0.rawQuery(r1, r3);	 Catch:{ SQLException -> 0x0094, all -> 0x0083 }
        r4 = r1.moveToFirst();	 Catch:{ SQLException -> 0x0081, all -> 0x007f }
        r5 = 0;
        if (r4 == 0) goto L_0x004f;
    L_0x003c:
        r4 = "track_id";
        r4 = r1.getColumnIndex(r4);	 Catch:{ SQLException -> 0x0081, all -> 0x007f }
        r4 = r1.getInt(r4);	 Catch:{ SQLException -> 0x0081, all -> 0x007f }
        r6.b(r4, r5);	 Catch:{ SQLException -> 0x0081, all -> 0x007f }
        r4 = r1.moveToNext();	 Catch:{ SQLException -> 0x0081, all -> 0x007f }
        if (r4 != 0) goto L_0x003c;
    L_0x004f:
        r1.close();	 Catch:{ SQLException -> 0x0081, all -> 0x007f }
        r4 = "select * from playlist_details where download_status = 0";
        r3 = r0.rawQuery(r4, r3);	 Catch:{ SQLException -> 0x0081, all -> 0x007f }
        r1 = r3.moveToFirst();	 Catch:{ SQLException -> 0x0094, all -> 0x0083 }
        if (r1 != 0) goto L_0x0069;
    L_0x005e:
        r1 = com.managers.DownloadManager.c();	 Catch:{ SQLException -> 0x0094, all -> 0x0083 }
        r1 = r1.v();	 Catch:{ SQLException -> 0x0094, all -> 0x0083 }
        if (r1 == 0) goto L_0x0069;
    L_0x0068:
        goto L_0x006a;
    L_0x0069:
        r5 = r2;
    L_0x006a:
        r3.close();	 Catch:{ SQLException -> 0x0095, all -> 0x0083 }
        r6.f(r7, r5);	 Catch:{ SQLException -> 0x0095, all -> 0x0083 }
        r0.setTransactionSuccessful();	 Catch:{ SQLException -> 0x0095, all -> 0x0083 }
        if (r3 == 0) goto L_0x00a1;
    L_0x0075:
        r1 = r3.isClosed();
        if (r1 != 0) goto L_0x00a1;
    L_0x007b:
        r3.close();
        goto L_0x00a1;
    L_0x007f:
        r7 = move-exception;
        goto L_0x0085;
    L_0x0081:
        r5 = r2;
        goto L_0x0096;
    L_0x0083:
        r7 = move-exception;
        r1 = r3;
    L_0x0085:
        if (r1 == 0) goto L_0x0090;
    L_0x0087:
        r2 = r1.isClosed();
        if (r2 != 0) goto L_0x0090;
    L_0x008d:
        r1.close();
    L_0x0090:
        r0.endTransaction();
        throw r7;
    L_0x0094:
        r5 = r2;
    L_0x0095:
        r1 = r3;
    L_0x0096:
        if (r1 == 0) goto L_0x00a1;
    L_0x0098:
        r3 = r1.isClosed();
        if (r3 != 0) goto L_0x00a1;
    L_0x009e:
        r1.close();
    L_0x00a1:
        r0.endTransaction();
        if (r5 != r2) goto L_0x00b1;
    L_0x00a6:
        r0 = com.managers.l.a();
        r1 = r6.d(r7);
        r0.a(r7, r1);
    L_0x00b1:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.m(int):void");
    }

    public void t() {
        int p = p();
        SQLiteDatabase db = getDB();
        try {
            db.beginTransaction();
            f(p, 1);
            db.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            db.endTransaction();
        }
        db.endTransaction();
    }

    private void D() {
        Throwable th;
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = getDB();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from ");
        stringBuilder.append(k.a);
        stringBuilder.append(" where ");
        stringBuilder.append("has_downloaded");
        stringBuilder.append("=");
        stringBuilder.append(0);
        stringBuilder.append(" OR ");
        stringBuilder.append("has_downloaded");
        stringBuilder.append("=");
        stringBuilder.append(-1);
        Cursor rawQuery;
        try {
            rawQuery = db.rawQuery(stringBuilder.toString(), null);
            try {
                if (rawQuery.moveToFirst()) {
                    do {
                        arrayList.add(Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("track_id"))));
                    } while (rawQuery.moveToNext());
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                if (arrayList.size() != 0) {
                    a(arrayList, -100);
                }
            } catch (Throwable th2) {
                th = th2;
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            rawQuery = null;
            rawQuery.close();
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0107  */
    /* JADX WARNING: Missing block: B:27:0x00ee, code skipped:
            if (r3.isClosed() == false) goto L_0x010d;
     */
    /* JADX WARNING: Missing block: B:42:0x010b, code skipped:
            if (r3.isClosed() == false) goto L_0x010d;
     */
    /* JADX WARNING: Missing block: B:43:0x010d, code skipped:
            r3.close();
     */
    public void u() {
        /*
        r9 = this;
        r0 = r9.p();
        r1 = -1;
        r2 = -100;
        if (r0 != r2) goto L_0x000d;
    L_0x0009:
        r9.D();
        goto L_0x0012;
    L_0x000d:
        if (r0 == r2) goto L_0x0012;
    L_0x000f:
        r9.d(r0, r1);
    L_0x0012:
        r0 = r9.getDB();
        r2 = new java.util.ArrayList;
        r2.<init>();
        r3 = "select playlist_id from playlist_details where download_status=1";
        r4 = 0;
        r5 = r9.getDB();	 Catch:{ SQLException -> 0x0104, all -> 0x00f3 }
        r3 = r5.rawQuery(r3, r4);	 Catch:{ SQLException -> 0x0104, all -> 0x00f3 }
        r0.beginTransaction();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r4 = r3.moveToFirst();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        if (r4 == 0) goto L_0x004c;
    L_0x002f:
        r4 = "playlist_id";
        r4 = r3.getColumnIndex(r4);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r4 = r3.getInt(r4);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r5 = r9.i(r4);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        if (r5 != 0) goto L_0x0046;
    L_0x003f:
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r2.add(r4);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
    L_0x0046:
        r4 = r3.moveToNext();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        if (r4 != 0) goto L_0x002f;
    L_0x004c:
        r3.close();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r2 = r2.iterator();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
    L_0x0053:
        r4 = r2.hasNext();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        if (r4 == 0) goto L_0x008b;
    L_0x0059:
        r4 = r2.next();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r4 = (java.lang.Integer) r4;	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r5 = r4.intValue();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r5 = r9.f(r5);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r5 = r5.iterator();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
    L_0x006b:
        r6 = r5.hasNext();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        if (r6 == 0) goto L_0x0083;
    L_0x0071:
        r6 = r5.next();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r6 = (java.lang.Integer) r6;	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r6 = r6.intValue();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r7 = r4.intValue();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r9.e(r7, r6);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        goto L_0x006b;
    L_0x0083:
        r4 = r4.intValue();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r9.q(r4);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        goto L_0x0053;
    L_0x008b:
        r2 = new android.content.ContentValues;	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r2.<init>();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r4 = "download_status";
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r2.put(r4, r1);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r1 = 1;
        r4 = new java.lang.String[r1];	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r5 = java.lang.String.valueOf(r1);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r6 = 0;
        r4[r6] = r5;	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r5 = com.e.a.e.h.a;	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r7 = "download_status=?";
        r0.update(r5, r2, r7, r4);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r2 = new android.content.ContentValues;	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r2.<init>();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r4 = "has_downloaded";
        r5 = -2;
        r7 = java.lang.Integer.valueOf(r5);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r2.put(r4, r7);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r4 = new java.lang.String[r1];	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r7 = java.lang.String.valueOf(r6);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r4[r6] = r7;	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r7 = com.e.a.e.k.a;	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r8 = "has_downloaded=?";
        r0.update(r7, r2, r8, r4);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r2 = new android.content.ContentValues;	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r2.<init>();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r4 = "has_downloaded";
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r2.put(r4, r5);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r1 = new java.lang.String[r1];	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r4 = java.lang.String.valueOf(r6);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r1[r6] = r4;	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r4 = com.e.a.e.l.a;	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r5 = "has_downloaded=?";
        r0.update(r4, r2, r5, r1);	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        r0.setTransactionSuccessful();	 Catch:{ SQLException -> 0x0105, all -> 0x00f1 }
        if (r3 == 0) goto L_0x0110;
    L_0x00ea:
        r1 = r3.isClosed();
        if (r1 != 0) goto L_0x0110;
    L_0x00f0:
        goto L_0x010d;
    L_0x00f1:
        r1 = move-exception;
        goto L_0x00f5;
    L_0x00f3:
        r1 = move-exception;
        r3 = r4;
    L_0x00f5:
        if (r3 == 0) goto L_0x0100;
    L_0x00f7:
        r2 = r3.isClosed();
        if (r2 != 0) goto L_0x0100;
    L_0x00fd:
        r3.close();
    L_0x0100:
        r0.endTransaction();
        throw r1;
    L_0x0104:
        r3 = r4;
    L_0x0105:
        if (r3 == 0) goto L_0x0110;
    L_0x0107:
        r1 = r3.isClosed();
        if (r1 != 0) goto L_0x0110;
    L_0x010d:
        r3.close();
    L_0x0110:
        r0.endTransaction();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.u():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x004e  */
    public com.gaana.models.BusinessObject c(java.lang.String r6) {
        /*
        r5 = this;
        r0 = 0;
        r1 = r5.getDB();	 Catch:{ all -> 0x004b }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x004b }
        r2.<init>();	 Catch:{ all -> 0x004b }
        r3 = "select playlist_content from ";
        r2.append(r3);	 Catch:{ all -> 0x004b }
        r3 = com.e.a.e.h.a;	 Catch:{ all -> 0x004b }
        r2.append(r3);	 Catch:{ all -> 0x004b }
        r3 = " where playlist_id = ";
        r2.append(r3);	 Catch:{ all -> 0x004b }
        r2.append(r6);	 Catch:{ all -> 0x004b }
        r6 = r2.toString();	 Catch:{ all -> 0x004b }
        r6 = r1.rawQuery(r6, r0);	 Catch:{ all -> 0x004b }
        r1 = r6.moveToFirst();	 Catch:{ all -> 0x0046 }
        if (r1 == 0) goto L_0x0040;
    L_0x002a:
        r0 = "playlist_content";
        r0 = r6.getColumnIndex(r0);	 Catch:{ all -> 0x0046 }
        r0 = r6.getString(r0);	 Catch:{ all -> 0x0046 }
        r0 = com.library.util.Serializer.deserialize(r0);	 Catch:{ all -> 0x0046 }
        r0 = (com.gaana.models.BusinessObject) r0;	 Catch:{ all -> 0x0046 }
        r1 = r6.moveToNext();	 Catch:{ all -> 0x0046 }
        if (r1 != 0) goto L_0x002a;
    L_0x0040:
        if (r6 == 0) goto L_0x0045;
    L_0x0042:
        r6.close();
    L_0x0045:
        return r0;
    L_0x0046:
        r0 = move-exception;
        r4 = r0;
        r0 = r6;
        r6 = r4;
        goto L_0x004c;
    L_0x004b:
        r6 = move-exception;
    L_0x004c:
        if (r0 == 0) goto L_0x0051;
    L_0x004e:
        r0.close();
    L_0x0051:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.c(java.lang.String):com.gaana.models.BusinessObject");
    }

    public BusinessObject a(String str, boolean z) {
        Throwable th;
        Throwable th2;
        ArrayList arrayList = new ArrayList();
        BusinessObject businessObject = null;
        if (!z) {
            BusinessObject tracks = new Tracks();
            String str2 = "";
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select playlist_content from ");
            stringBuilder.append(com.e.a.e.h.a);
            stringBuilder.append(" where playlist_id=");
            stringBuilder.append(str);
            Cursor rawQuery;
            try {
                rawQuery = getDB().rawQuery(stringBuilder.toString(), null);
                try {
                    BusinessObject businessObject2;
                    if (rawQuery.moveToFirst()) {
                        do {
                            businessObject2 = (BusinessObject) Serializer.deserialize(rawQuery.getString(rawQuery.getColumnIndex("playlist_content")));
                            if (businessObject2 instanceof Album) {
                                str2 = ((Album) businessObject2).getFavoriteCount();
                                arrayList2 = ((Album) businessObject2).getTags();
                                arrayList3 = ((Album) businessObject2).getTopArtists();
                                arrayList4 = ((Album) businessObject2).getTopLanguages();
                            } else if (businessObject2 instanceof Playlist) {
                                str2 = ((Playlist) businessObject2).getFavoriteCount();
                                arrayList2 = ((Playlist) businessObject2).getTags();
                                arrayList3 = ((Playlist) businessObject2).getTopArtists();
                                arrayList4 = ((Playlist) businessObject2).getTopLanguages();
                            }
                        } while (rawQuery.moveToNext());
                    } else {
                        businessObject2 = null;
                    }
                    if (!(rawQuery == null || rawQuery.isClosed())) {
                        rawQuery.close();
                    }
                    boolean z2 = businessObject2 instanceof Playlist;
                    if (z2) {
                        Playlist playlist = (Playlist) businessObject2;
                        if (PlaylistSyncManager.getInstance().isMyPlaylist(playlist)) {
                            tracks = PlaylistSyncManager.getInstance().getPlaylistDetails(playlist);
                            if (tracks.getArrListBusinessObj().size() > 0) {
                                ((Tracks) tracks).setFavoriteCount(str2);
                                tracks.setCount(String.valueOf(tracks.getArrListBusinessObj().size()));
                                return tracks;
                            }
                        }
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("select metadata.track_id,track_metadata from ");
                    stringBuilder2.append(l.a);
                    stringBuilder2.append(" metadata ");
                    stringBuilder2.append(" JOIN ");
                    stringBuilder2.append(k.a);
                    stringBuilder2.append(" detail ON detail.track_id=metadata.track_id ");
                    stringBuilder2.append(" where detail.playlist_id=");
                    stringBuilder2.append(str);
                    stringBuilder2.append(" ORDER BY detail.");
                    stringBuilder2.append("track_position_in_playlist");
                    Cursor rawQuery2;
                    try {
                        rawQuery2 = getDB().rawQuery(stringBuilder2.toString(), null);
                        try {
                            if (rawQuery2.moveToFirst()) {
                                do {
                                    Object obj;
                                    String string = rawQuery2.getString(rawQuery2.getColumnIndex("track_id"));
                                    if (!a.containsKey(string) || a.get(string) == null) {
                                        BusinessObject businessObject3 = (BusinessObject) Serializer.deserialize(rawQuery2.getString(rawQuery2.getColumnIndex("track_metadata")));
                                        a.put(string, businessObject3);
                                        obj = businessObject3;
                                    } else {
                                        obj = (BusinessObject) a.get(string);
                                    }
                                    arrayList.add(obj);
                                } while (rawQuery2.moveToNext());
                            }
                            if (!(rawQuery2 == null || rawQuery2.isClosed())) {
                                rawQuery2.close();
                            }
                            if (z2) {
                                str = ((Playlist) businessObject2).getLocalPlaylistId();
                                if (!TextUtils.isEmpty(str)) {
                                    ArrayList songsByPlaylist = LocalMediaManager.getInstance(GaanaApplication.getContext()).getSongsByPlaylist(str);
                                    if (songsByPlaylist != null) {
                                        arrayList.addAll(songsByPlaylist);
                                    }
                                }
                            }
                            if (businessObject2 != null) {
                                Tracks tracks2 = (Tracks) tracks;
                                tracks2.setFavoriteCount(str2);
                                tracks2.setTags(arrayList2);
                                tracks2.setTopArtists(arrayList3);
                                tracks2.setTopLanguages(arrayList4);
                            }
                            tracks.setArrListBusinessObj(arrayList);
                            tracks.setCount(String.valueOf(arrayList.size()));
                            return tracks;
                        } catch (Throwable th3) {
                            th = th3;
                            if (!(rawQuery2 == null || rawQuery2.isClosed())) {
                                rawQuery2.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        rawQuery2 = rawQuery;
                        rawQuery2.close();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    if (!(rawQuery == null || rawQuery.isClosed())) {
                        rawQuery.close();
                    }
                    throw th2;
                }
            } catch (Throwable th6) {
                th2 = th6;
                rawQuery = null;
                rawQuery.close();
                throw th2;
            }
        } else if (a.containsKey(str) && a.get(str) != null) {
            return (BusinessObject) a.get(str);
        } else {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("select track_metadata from ");
            stringBuilder3.append(l.a);
            stringBuilder3.append(" where track_id=");
            stringBuilder3.append(str);
            Cursor rawQuery3;
            try {
                rawQuery3 = getDB().rawQuery(stringBuilder3.toString(), null);
                try {
                    if (rawQuery3.moveToFirst()) {
                        do {
                            businessObject = (BusinessObject) Serializer.deserialize(rawQuery3.getString(rawQuery3.getColumnIndex("track_metadata")));
                            a.put(str, businessObject);
                        } while (rawQuery3.moveToNext());
                    }
                    if (!(rawQuery3 == null || rawQuery3.isClosed())) {
                        rawQuery3.close();
                    }
                    return businessObject;
                } catch (Throwable th7) {
                    th2 = th7;
                    if (!(rawQuery3 == null || rawQuery3.isClosed())) {
                        rawQuery3.close();
                    }
                    throw th2;
                }
            } catch (Throwable th8) {
                th2 = th8;
                rawQuery3 = null;
                rawQuery3.close();
                throw th2;
            }
        }
    }

    /* JADX WARNING: Missing block: B:19:0x00ab, code skipped:
            if (r2.isClosed() == false) goto L_0x00c6;
     */
    /* JADX WARNING: Missing block: B:30:0x00c4, code skipped:
            if (r2.isClosed() == false) goto L_0x00c6;
     */
    /* JADX WARNING: Missing block: B:31:0x00c6, code skipped:
            r2.close();
     */
    public void a(java.lang.String r9, com.gaana.models.BusinessObject r10) {
        /*
        r8 = this;
        r0 = r8.getDB();
        r1 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ce }
        r2.<init>();	 Catch:{ Exception -> 0x00ce }
        r3 = "select * from ";
        r2.append(r3);	 Catch:{ Exception -> 0x00ce }
        r3 = com.e.a.e.h.a;	 Catch:{ Exception -> 0x00ce }
        r2.append(r3);	 Catch:{ Exception -> 0x00ce }
        r3 = " where ";
        r2.append(r3);	 Catch:{ Exception -> 0x00ce }
        r3 = "playlist_id";
        r2.append(r3);	 Catch:{ Exception -> 0x00ce }
        r3 = "=";
        r2.append(r3);	 Catch:{ Exception -> 0x00ce }
        r2.append(r9);	 Catch:{ Exception -> 0x00ce }
        r2 = r2.toString();	 Catch:{ Exception -> 0x00ce }
        r2 = r0.rawQuery(r2, r1);	 Catch:{ Exception -> 0x00ce }
        r1 = r2.moveToFirst();	 Catch:{ Exception -> 0x00cd }
        if (r1 != 0) goto L_0x0038;
    L_0x0034:
        r2.close();	 Catch:{ Exception -> 0x00cd }
        return;
    L_0x0038:
        r1 = "playlist_content";
        r1 = r2.getColumnIndex(r1);
        r1 = r2.getString(r1);
        r1 = com.library.util.Serializer.deserialize(r1);
        r1 = (com.gaana.models.BusinessObject) r1;
        r3 = com.e.a.e.c.b;
        r4 = r1 instanceof com.gaana.models.Playlists.Playlist;
        if (r4 == 0) goto L_0x0063;
    L_0x004e:
        r4 = r1;
        r4 = (com.gaana.models.Playlists.Playlist) r4;
        r5 = r10;
        r5 = (com.gaana.models.Playlists.Playlist) r5;
        r5 = r5.getFavoriteCount();
        r4.setFavoriteCount(r5);
        r10 = r10.getName();
        r4.setName(r10);
        goto L_0x007d;
    L_0x0063:
        r4 = r1 instanceof com.gaana.models.Albums.Album;
        if (r4 == 0) goto L_0x007d;
    L_0x0067:
        r3 = r1;
        r3 = (com.gaana.models.Albums.Album) r3;
        r4 = r10;
        r4 = (com.gaana.models.Albums.Album) r4;
        r4 = r4.getFavoriteCount();
        r3.setFavoriteCount(r4);
        r10 = r10.getName();
        r3.setName(r10);
        r3 = com.e.a.e.c.a;
    L_0x007d:
        r10 = "download_status";
        r10 = r2.getColumnIndex(r10);
        r10 = r2.getInt(r10);
        r0.beginTransaction();
        r4 = "playlist_id=?";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ Exception -> 0x00be, all -> 0x00ae }
        r6 = 0;
        r7 = java.lang.String.valueOf(r9);	 Catch:{ Exception -> 0x00be, all -> 0x00ae }
        r5[r6] = r7;	 Catch:{ Exception -> 0x00be, all -> 0x00ae }
        r6 = "playlist_details";
        r0.delete(r6, r4, r5);	 Catch:{ Exception -> 0x00be, all -> 0x00ae }
        r9 = java.lang.Integer.parseInt(r9);	 Catch:{ Exception -> 0x00be, all -> 0x00ae }
        r8.a(r9, r3, r1, r10);	 Catch:{ Exception -> 0x00be, all -> 0x00ae }
        r0.setTransactionSuccessful();	 Catch:{ Exception -> 0x00be, all -> 0x00ae }
        if (r2 == 0) goto L_0x00c9;
    L_0x00a7:
        r9 = r2.isClosed();
        if (r9 != 0) goto L_0x00c9;
    L_0x00ad:
        goto L_0x00c6;
    L_0x00ae:
        r9 = move-exception;
        if (r2 == 0) goto L_0x00ba;
    L_0x00b1:
        r10 = r2.isClosed();
        if (r10 != 0) goto L_0x00ba;
    L_0x00b7:
        r2.close();
    L_0x00ba:
        r0.endTransaction();
        throw r9;
    L_0x00be:
        if (r2 == 0) goto L_0x00c9;
    L_0x00c0:
        r9 = r2.isClosed();
        if (r9 != 0) goto L_0x00c9;
    L_0x00c6:
        r2.close();
    L_0x00c9:
        r0.endTransaction();
        return;
    L_0x00cd:
        r1 = r2;
    L_0x00ce:
        if (r1 == 0) goto L_0x00d9;
    L_0x00d0:
        r9 = r1.isClosed();
        if (r9 != 0) goto L_0x00d9;
    L_0x00d6:
        r1.close();
    L_0x00d9:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.a(java.lang.String, com.gaana.models.BusinessObject):void");
    }

    public Track d(String str) {
        Throwable th;
        Cursor cursor = null;
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select track_metadata from ");
            stringBuilder.append(l.a);
            stringBuilder.append(" where ");
            stringBuilder.append("track_id");
            stringBuilder.append("=");
            stringBuilder.append(str);
            Cursor rawQuery = db.rawQuery(stringBuilder.toString(), null);
            try {
                Track track;
                if (rawQuery.moveToFirst()) {
                    String string = rawQuery.getString(rawQuery.getColumnIndex("track_metadata"));
                    if (!TextUtils.isEmpty(string)) {
                        track = (Track) Serializer.deserialize(string);
                    }
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                return track;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                cursor = rawQuery;
                th = th3;
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            cursor.close();
            throw th;
        }
    }

    public String e(String str) {
        Throwable th;
        String str2 = "";
        Cursor cursor = null;
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select playlist_name from ");
            stringBuilder.append(com.e.a.e.h.a);
            stringBuilder.append(" where ");
            stringBuilder.append("playlist_id");
            stringBuilder.append("=");
            stringBuilder.append(str);
            Cursor rawQuery = db.rawQuery(stringBuilder.toString(), null);
            try {
                if (rawQuery.moveToFirst()) {
                    str2 = rawQuery.getString(rawQuery.getColumnIndex("playlist_name"));
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                cursor = rawQuery;
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            cursor.close();
            throw th;
        }
    }

    public HashMap<String, ArrayList<String>> v() {
        Cursor cursor;
        Cursor rawQuery;
        Throwable th;
        HashMap hashMap = new HashMap();
        Cursor cursor2 = null;
        Cursor cursor3;
        try {
            String miniPacks = GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getMiniPacks();
            String[] split = !TextUtils.isEmpty(miniPacks) ? miniPacks.split(",") : null;
            if (split == null || split.length <= 0) {
                cursor = null;
            } else {
                int length = split.length;
                int i = 0;
                cursor3 = null;
                cursor = cursor3;
                while (i < length) {
                    String str;
                    try {
                        str = split[i];
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("select playlist_content,playlist_id,playlist_type from ");
                        stringBuilder.append(com.e.a.e.h.a);
                        stringBuilder.append(" where playlist_id=");
                        stringBuilder.append(str);
                        rawQuery = getDB().rawQuery(stringBuilder.toString(), null);
                    } catch (SQLiteException unused) {
                        rawQuery = cursor;
                        if (!(cursor3 == null || cursor3.isClosed())) {
                            cursor3.close();
                        }
                        if (!(rawQuery == null || rawQuery.isClosed())) {
                            rawQuery.close();
                        }
                        return hashMap;
                    } catch (Throwable th2) {
                        th = th2;
                        rawQuery = cursor;
                        if (!(cursor3 == null || cursor3.isClosed())) {
                            cursor3.close();
                        }
                        if (!(rawQuery == null || rawQuery.isClosed())) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                    try {
                        if (rawQuery.moveToFirst()) {
                            Playlist playlist = (Playlist) Serializer.deserialize(rawQuery.getString(rawQuery.getColumnIndex("playlist_content")));
                            String string = rawQuery.getString(rawQuery.getColumnIndex("playlist_id"));
                            String str2 = "AL";
                            if (rawQuery.getInt(rawQuery.getColumnIndex("playlist_type")) == c.b) {
                                str2 = (TextUtils.isEmpty(playlist.getIsMiniPlaylist()) || !playlist.getIsMiniPlaylist().equalsIgnoreCase("1")) ? "PL" : "AR";
                            }
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(str2);
                            stringBuilder2.append(string);
                            String stringBuilder3 = stringBuilder2.toString();
                            rawQuery.close();
                            StringBuilder stringBuilder4 = new StringBuilder();
                            stringBuilder4.append("select metadata.track_id,track_metadata from ");
                            stringBuilder4.append(l.a);
                            stringBuilder4.append(" metadata ");
                            stringBuilder4.append(" JOIN ");
                            stringBuilder4.append(k.a);
                            stringBuilder4.append(" detail ON detail.track_id=metadata.track_id ");
                            stringBuilder4.append(" where detail.playlist_id=");
                            stringBuilder4.append(str);
                            stringBuilder4.append(" ORDER BY detail.");
                            stringBuilder4.append("track_position_in_playlist");
                            str = stringBuilder4.toString();
                            ArrayList arrayList = new ArrayList();
                            Cursor rawQuery2 = getDB().rawQuery(str, null);
                            try {
                                if (rawQuery2.moveToFirst()) {
                                    do {
                                        arrayList.add(rawQuery2.getString(rawQuery2.getColumnIndex("track_id")));
                                    } while (rawQuery2.moveToNext());
                                    hashMap.put(stringBuilder3, arrayList);
                                }
                                cursor3 = rawQuery2;
                            } catch (SQLiteException unused2) {
                                cursor3 = rawQuery2;
                                cursor3.close();
                                rawQuery.close();
                                return hashMap;
                            } catch (Throwable th3) {
                                th = th3;
                                cursor3 = rawQuery2;
                                cursor3.close();
                                rawQuery.close();
                                throw th;
                            }
                        }
                        i++;
                        cursor = rawQuery;
                    } catch (SQLiteException unused3) {
                        cursor3.close();
                        rawQuery.close();
                        return hashMap;
                    } catch (Throwable th4) {
                        th = th4;
                        cursor3.close();
                        rawQuery.close();
                        throw th;
                    }
                }
                cursor2 = cursor3;
            }
            if (!(cursor2 == null || cursor2.isClosed())) {
                cursor2.close();
            }
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
        } catch (SQLiteException unused4) {
            cursor3 = null;
            rawQuery = cursor3;
            cursor3.close();
            rawQuery.close();
            return hashMap;
        } catch (Throwable th5) {
            th = th5;
            cursor3 = null;
            rawQuery = cursor3;
            cursor3.close();
            rawQuery.close();
            throw th;
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00af  */
    /* JADX WARNING: Missing block: B:12:0x0088, code skipped:
            if (r6 != null) goto L_0x0099;
     */
    /* JADX WARNING: Missing block: B:20:0x0097, code skipped:
            if (r6 != null) goto L_0x0099;
     */
    /* JADX WARNING: Missing block: B:21:0x0099, code skipped:
            r6.close();
     */
    /* JADX WARNING: Missing block: B:22:0x009c, code skipped:
            r0.endTransaction();
            r3 = r6;
     */
    public void w() {
        /*
        r13 = this;
        r0 = r13.getDB();
        r1 = 0;
        r2 = 0;
        r4 = r1;
        r3 = r2;
    L_0x0008:
        r5 = 1;
        r0.beginTransaction();	 Catch:{ Exception -> 0x0090, all -> 0x008d }
        r6 = " where album_name IS NULL  OR track_artwork IS NULL LIMIT 40";
        r7 = r13.getDB();	 Catch:{ Exception -> 0x0090, all -> 0x008d }
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0090, all -> 0x008d }
        r8.<init>();	 Catch:{ Exception -> 0x0090, all -> 0x008d }
        r9 = "SELECT track_id , track_metadata FROM ";
        r8.append(r9);	 Catch:{ Exception -> 0x0090, all -> 0x008d }
        r9 = com.e.a.e.l.a;	 Catch:{ Exception -> 0x0090, all -> 0x008d }
        r8.append(r9);	 Catch:{ Exception -> 0x0090, all -> 0x008d }
        r8.append(r6);	 Catch:{ Exception -> 0x0090, all -> 0x008d }
        r6 = r8.toString();	 Catch:{ Exception -> 0x0090, all -> 0x008d }
        r6 = r7.rawQuery(r6, r2);	 Catch:{ Exception -> 0x0090, all -> 0x008d }
        r3 = r6.moveToFirst();	 Catch:{ Exception -> 0x008b }
        if (r3 == 0) goto L_0x0084;
    L_0x0032:
        r3 = "track_metadata";
        r3 = r6.getColumnIndex(r3);	 Catch:{ Exception -> 0x008b }
        r3 = r6.getString(r3);	 Catch:{ Exception -> 0x008b }
        r7 = "track_id";
        r7 = r6.getColumnIndex(r7);	 Catch:{ Exception -> 0x008b }
        r7 = r6.getString(r7);	 Catch:{ Exception -> 0x008b }
        r3 = com.library.util.Serializer.deserialize(r3);	 Catch:{ Exception -> 0x008b }
        r3 = (com.gaana.models.Tracks.Track) r3;	 Catch:{ Exception -> 0x008b }
        r8 = new android.content.ContentValues;	 Catch:{ Exception -> 0x008b }
        r8.<init>();	 Catch:{ Exception -> 0x008b }
        r9 = "album_name";
        r10 = r3.getRawAlbumTitle();	 Catch:{ Exception -> 0x008b }
        r8.put(r9, r10);	 Catch:{ Exception -> 0x008b }
        r9 = "track_artwork";
        r3 = r3.getArtwork();	 Catch:{ Exception -> 0x008b }
        r8.put(r9, r3);	 Catch:{ Exception -> 0x008b }
        r3 = r13.getDB();	 Catch:{ Exception -> 0x008b }
        r9 = com.e.a.e.l.a;	 Catch:{ Exception -> 0x008b }
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x008b }
        r10.<init>();	 Catch:{ Exception -> 0x008b }
        r11 = "track_id=";
        r10.append(r11);	 Catch:{ Exception -> 0x008b }
        r10.append(r7);	 Catch:{ Exception -> 0x008b }
        r7 = r10.toString();	 Catch:{ Exception -> 0x008b }
        r3.update(r9, r8, r7, r2);	 Catch:{ Exception -> 0x008b }
        r3 = r6.moveToNext();	 Catch:{ Exception -> 0x008b }
        if (r3 != 0) goto L_0x0032;
    L_0x0083:
        goto L_0x0085;
    L_0x0084:
        r4 = r5;
    L_0x0085:
        r0.setTransactionSuccessful();	 Catch:{ Exception -> 0x008b }
        if (r6 == 0) goto L_0x009c;
    L_0x008a:
        goto L_0x0099;
    L_0x008b:
        r3 = move-exception;
        goto L_0x0094;
    L_0x008d:
        r1 = move-exception;
        r6 = r3;
        goto L_0x00ad;
    L_0x0090:
        r6 = move-exception;
        r12 = r6;
        r6 = r3;
        r3 = r12;
    L_0x0094:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r3);	 Catch:{ all -> 0x00ac }
        if (r6 == 0) goto L_0x009c;
    L_0x0099:
        r6.close();
    L_0x009c:
        r0.endTransaction();
        r3 = r6;
        if (r4 == 0) goto L_0x0008;
    L_0x00a2:
        r0 = com.services.d.a();
        r2 = "PREFERENCE_DOWNLOADED_TRACKS_META_UPDATED";
        r0.a(r2, r5, r1);
        return;
    L_0x00ac:
        r1 = move-exception;
    L_0x00ad:
        if (r6 == 0) goto L_0x00b2;
    L_0x00af:
        r6.close();
    L_0x00b2:
        r0.endTransaction();
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.w():void");
    }

    private int g(int i, int i2) {
        String[] strArr;
        String str = "playlist_id=? and track_id=?";
        if (i == -9999) {
            strArr = new String[]{String.valueOf(i2)};
        } else {
            strArr = new String[]{String.valueOf(i), String.valueOf(i2)};
        }
        i = getDB().delete(k.a, str, strArr);
        if (i > 0) {
            com.managers.l.a().a(i2, c.c, 0);
        }
        return i;
    }

    private int u(int i) {
        String[] strArr = new String[]{String.valueOf(i)};
        DownloadManager.c().b(i);
        return getDB().delete(l.a, "track_id=?", strArr);
    }

    private int v(int i) {
        String[] strArr = new String[]{String.valueOf(i)};
        DownloadManager.c().c(i);
        return getDB().delete(com.e.a.e.h.a, "playlist_id=?", strArr);
    }

    private int h(int i, int i2) {
        int update;
        ContentValues contentValues = new ContentValues();
        contentValues.put("download_status", Integer.valueOf(i2));
        try {
            SQLiteDatabase db = getDB();
            String str = com.e.a.e.h.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("playlist_id = ");
            stringBuilder.append(i);
            update = db.update(str, contentValues, stringBuilder.toString(), null);
            if (update > 0) {
                try {
                    DownloadManager.c().a(i, i2, null, null);
                } catch (SQLException unused) {
                }
            }
        } catch (SQLException unused2) {
            update = -1;
        }
        LocalBroadcastManager.getInstance(GaanaApplication.getContext()).sendBroadcast(new Intent("broadcast_playlist_update_status"));
        return update;
    }

    private int i(int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("has_downloaded", Integer.valueOf(i2));
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("track_id = ");
            stringBuilder.append(i);
            i2 = getDB().update("track_details", contentValues, stringBuilder.toString(), null);
        } catch (SQLException unused) {
            i2 = -1;
        }
        DownloadManager.c().e(String.valueOf(i));
        return i2;
    }

    private int j(int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("has_downloaded", Integer.valueOf(i2));
        if (i2 == 0) {
            contentValues.put("download_time", Long.valueOf(System.currentTimeMillis()));
        } else if (i2 == 1) {
            contentValues.put("offline_play_count", Integer.valueOf(0));
        }
        try {
            SQLiteDatabase db = getDB();
            String str = l.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("track_id = ");
            stringBuilder.append(i);
            int update = db.update(str, contentValues, stringBuilder.toString(), null);
            if (update <= 0) {
                return update;
            }
            try {
                DownloadManager.c().a(i, i2, null);
                return update;
            } catch (SQLException unused) {
                return update;
            }
        } catch (SQLException unused2) {
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b8 A:{ExcHandler: all (th java.lang.Throwable), Splitter:B:32:0x0084} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:40:0x00b9, code skipped:
            getDB().endTransaction();
     */
    public int n(int r8) {
        /*
        r7 = this;
        r0 = -1;
        r1 = 0;
        r2 = r7.getDB();	 Catch:{ SQLException -> 0x0053, all -> 0x0046 }
        r3 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x0053, all -> 0x0046 }
        r3.<init>();	 Catch:{ SQLException -> 0x0053, all -> 0x0046 }
        r4 = "SELECT offline_play_count FROM ";
        r3.append(r4);	 Catch:{ SQLException -> 0x0053, all -> 0x0046 }
        r4 = com.e.a.e.l.a;	 Catch:{ SQLException -> 0x0053, all -> 0x0046 }
        r3.append(r4);	 Catch:{ SQLException -> 0x0053, all -> 0x0046 }
        r4 = " where track_id=";
        r3.append(r4);	 Catch:{ SQLException -> 0x0053, all -> 0x0046 }
        r3.append(r8);	 Catch:{ SQLException -> 0x0053, all -> 0x0046 }
        r3 = r3.toString();	 Catch:{ SQLException -> 0x0053, all -> 0x0046 }
        r2 = r2.rawQuery(r3, r1);	 Catch:{ SQLException -> 0x0053, all -> 0x0046 }
        r3 = r2.moveToFirst();	 Catch:{ SQLException -> 0x0054, all -> 0x0043 }
        if (r3 == 0) goto L_0x0036;
    L_0x002b:
        r3 = "offline_play_count";
        r3 = r2.getColumnIndex(r3);	 Catch:{ SQLException -> 0x0054, all -> 0x0043 }
        r3 = r2.getInt(r3);	 Catch:{ SQLException -> 0x0054, all -> 0x0043 }
        goto L_0x0037;
    L_0x0036:
        r3 = r0;
    L_0x0037:
        if (r2 == 0) goto L_0x0060;
    L_0x0039:
        r4 = r2.isClosed();
        if (r4 != 0) goto L_0x0060;
    L_0x003f:
        r2.close();
        goto L_0x0060;
    L_0x0043:
        r8 = move-exception;
        r1 = r2;
        goto L_0x0047;
    L_0x0046:
        r8 = move-exception;
    L_0x0047:
        if (r1 == 0) goto L_0x0052;
    L_0x0049:
        r0 = r1.isClosed();
        if (r0 != 0) goto L_0x0052;
    L_0x004f:
        r1.close();
    L_0x0052:
        throw r8;
    L_0x0053:
        r2 = r1;
    L_0x0054:
        if (r2 == 0) goto L_0x005f;
    L_0x0056:
        r3 = r2.isClosed();
        if (r3 != 0) goto L_0x005f;
    L_0x005c:
        r2.close();
    L_0x005f:
        r3 = r0;
    L_0x0060:
        if (r3 < 0) goto L_0x00c8;
    L_0x0062:
        r2 = new android.content.ContentValues;
        r2.<init>();
        r4 = "offline_play_time";
        r5 = java.lang.System.currentTimeMillis();
        r5 = java.lang.Long.valueOf(r5);
        r2.put(r4, r5);
        r4 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r3 >= r4) goto L_0x0084;
    L_0x0079:
        r4 = "offline_play_count";
        r3 = r3 + 1;
        r3 = java.lang.Integer.valueOf(r3);
        r2.put(r4, r3);
    L_0x0084:
        r3 = r7.getDB();	 Catch:{ SQLException -> 0x00c1, all -> 0x00b8 }
        r3.beginTransaction();	 Catch:{ SQLException -> 0x00c1, all -> 0x00b8 }
        r3 = r7.getDB();	 Catch:{ SQLException -> 0x00c1, all -> 0x00b8 }
        r4 = com.e.a.e.l.a;	 Catch:{ SQLException -> 0x00c1, all -> 0x00b8 }
        r5 = new java.lang.StringBuilder;	 Catch:{ SQLException -> 0x00c1, all -> 0x00b8 }
        r5.<init>();	 Catch:{ SQLException -> 0x00c1, all -> 0x00b8 }
        r6 = "track_id = ";
        r5.append(r6);	 Catch:{ SQLException -> 0x00c1, all -> 0x00b8 }
        r5.append(r8);	 Catch:{ SQLException -> 0x00c1, all -> 0x00b8 }
        r8 = r5.toString();	 Catch:{ SQLException -> 0x00c1, all -> 0x00b8 }
        r8 = r3.update(r4, r2, r8, r1);	 Catch:{ SQLException -> 0x00c1, all -> 0x00b8 }
        r0 = r7.getDB();	 Catch:{ SQLException -> 0x00b6, all -> 0x00b8 }
        r0.setTransactionSuccessful();	 Catch:{ SQLException -> 0x00b6, all -> 0x00b8 }
        r0 = r7.getDB();
        r0.endTransaction();
        r0 = r8;
        goto L_0x00c8;
    L_0x00b6:
        r0 = r8;
        goto L_0x00c1;
    L_0x00b8:
        r8 = move-exception;
        r0 = r7.getDB();
        r0.endTransaction();
        throw r8;
    L_0x00c1:
        r8 = r7.getDB();
        r8.endTransaction();
    L_0x00c8:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.h.n(int):int");
    }

    public int x() {
        try {
            SQLiteDatabase db = getDB();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT COUNT(*) FROM ");
            stringBuilder.append(l.a);
            stringBuilder.append(" where ");
            stringBuilder.append("offline_play_count");
            stringBuilder.append(" = 0");
            stringBuilder.append(" AND ");
            stringBuilder.append("has_downloaded");
            stringBuilder.append(" = ");
            stringBuilder.append(1);
            return (int) db.compileStatement(stringBuilder.toString()).simpleQueryForLong();
        } catch (SQLiteDoneException unused) {
            return 0;
        }
    }

    public ArrayList<BusinessObject> y() {
        Throwable th;
        Calendar instance = Calendar.getInstance();
        instance.add(5, -1);
        long timeInMillis = instance.getTimeInMillis();
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM ");
            stringBuilder.append(l.a);
            stringBuilder.append(" where ");
            stringBuilder.append("offline_play_count");
            stringBuilder.append(" = 0");
            stringBuilder.append(" AND ");
            stringBuilder.append(timeInMillis);
            stringBuilder.append(" >= ");
            stringBuilder.append("download_time");
            stringBuilder.append(" AND ");
            stringBuilder.append("has_downloaded");
            stringBuilder.append(" = ");
            stringBuilder.append(1);
            stringBuilder.append(" order by ");
            stringBuilder.append("download_time");
            stringBuilder.append(" DESC LIMIT 20");
            rawQuery = getDB().rawQuery(stringBuilder.toString(), null);
            try {
                ArrayList<BusinessObject> arrayList;
                if (rawQuery.moveToFirst()) {
                    arrayList = new ArrayList();
                    do {
                        String string = rawQuery.getString(rawQuery.getColumnIndex("track_metadata"));
                        if (!TextUtils.isEmpty(string)) {
                            arrayList.add((Track) n.a(string));
                        }
                    } while (rawQuery.moveToNext());
                } else {
                    arrayList = null;
                }
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                return arrayList;
            } catch (SQLException unused) {
                if (!(rawQuery == null || rawQuery.isClosed())) {
                    rawQuery.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                cursor = rawQuery;
                if (!(cursor == null || cursor.isClosed())) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLException unused2) {
            rawQuery = null;
            rawQuery.close();
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor.close();
            throw th;
        }
    }
}
