package com.e.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.constants.c.d;
import com.e.a.a.a;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.FavoriteOccasions.FavoriteOccasion;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.managers.URLManager.BusinessObjectType;
import java.util.List;

public class c extends a {
    private static c b;
    private Gson a = new GsonBuilder().excludeFieldsWithModifiers(8, 4).create();

    private c(Context context) {
        super(context);
    }

    public static c a() {
        if (b == null) {
            b = new c(GaanaApplication.getContext());
        }
        return b;
    }

    public long a(BusinessObject businessObject, boolean z, boolean z2) {
        return a(businessObject, z, z2, 0);
    }

    public long a(BusinessObject businessObject, boolean z, boolean z2, int i) {
        SQLiteDatabase k = k();
        long j = 0;
        try {
            long insert;
            k.beginTransaction();
            if (z2) {
                ContentValues c = c(businessObject);
                c.put("favourite_status", Integer.valueOf(z));
                c.put("has_synced", Integer.valueOf(i));
                insert = k.insert("local_favorite_table", null, c);
            } else {
                String str = "local_favorite_table";
                insert = (long) k.delete(str, "id= ? AND type= ?", new String[]{String.valueOf(businessObject.getBusinessObjId()), b(businessObject)});
            }
            j = insert;
            k.setTransactionSuccessful();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        } catch (Throwable th) {
            k.endTransaction();
        }
        k.endTransaction();
        return j;
    }

    public void a(List<BusinessObject> list) {
        if (list != null) {
            int size = list.size();
            SQLiteDatabase k = k();
            k.beginTransaction();
            int i = 0;
            while (i < size) {
                try {
                    BusinessObject businessObject = (BusinessObject) list.get(i);
                    if (businessObject != null && (businessObject instanceof FavoriteOccasion)) {
                        businessObject.setBusinessObjId(((FavoriteOccasion) businessObject).getEntityId());
                    }
                    ContentValues c = c(businessObject);
                    c.put("favourite_status", Integer.valueOf(1));
                    c.put("has_synced", Integer.valueOf(1));
                    k.insert("local_favorite_table", null, c);
                    i++;
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (Throwable th) {
                    k.endTransaction();
                }
            }
            k.setTransactionSuccessful();
            k.endTransaction();
        }
    }

    public long a(String str, String str2) {
        String[] strArr = new String[]{str, str2};
        return (long) k().delete("local_favorite_table", "id=? AND type=?", strArr);
    }

    private ContentValues c(BusinessObject businessObject) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", businessObject.getBusinessObjId());
        contentValues.put("name", businessObject.getRawName());
        String str = "TR";
        if (businessObject instanceof Playlist) {
            str = "PL";
            Playlist playlist = (Playlist) businessObject;
            contentValues.put("artwork", playlist.getArtwork());
            contentValues.put("popularity", Long.valueOf(playlist.getPopularity()));
        } else if (businessObject instanceof Radio) {
            Radio radio = (Radio) businessObject;
            String str2 = radio.getType().equals(d.d) ? "RD_L" : "RD_M";
            contentValues.put("artwork", radio.getArtwork());
            str = str2;
        } else if (businessObject instanceof Track) {
            str = "TR";
            Track track = (Track) businessObject;
            contentValues.put("artwork", track.getArtwork());
            contentValues.put("artist_names", track.getArtistRawNames());
            contentValues.put("popularity", Long.valueOf(track.getPopularity()));
            contentValues.put("album_id", track.getAlbumId());
            contentValues.put("album_name", track.getRawAlbumTitle());
        } else if (businessObject instanceof Artist) {
            str = "AR";
            contentValues.put("artwork", ((Artist) businessObject).getArtwork());
        } else if (businessObject instanceof Album) {
            str = "AL";
            Album album = (Album) businessObject;
            contentValues.put("artwork", album.getArtwork());
            contentValues.put("artist_names", album.getRawArtistNames());
        } else if (businessObject instanceof FavoriteOccasion) {
            str = "OC";
            FavoriteOccasion favoriteOccasion = (FavoriteOccasion) businessObject;
            contentValues.put("artwork", favoriteOccasion.getArtwork());
            businessObject.setBusinessObjId(favoriteOccasion.getEntityId());
        }
        contentValues.put("business_object", this.a.toJson((Object) businessObject));
        contentValues.put("type", str);
        contentValues.put("added_on", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    public void a(String str, String str2, int i) {
        SQLiteDatabase k = k();
        try {
            k.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put("has_synced", Integer.valueOf(i));
            String[] strArr = new String[]{str, str2};
            k.update("local_favorite_table", contentValues, "id=? AND type=?", strArr);
            k.setTransactionSuccessful();
        } catch (Exception unused) {
        } catch (Throwable th) {
            k.endTransaction();
        }
        k.endTransaction();
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x01f6  */
    /* JADX WARNING: Missing block: B:36:0x01df, code skipped:
            if (r11 != null) goto L_0x01ee;
     */
    /* JADX WARNING: Missing block: B:43:0x01ec, code skipped:
            if (r11 != null) goto L_0x01ee;
     */
    /* JADX WARNING: Missing block: B:44:0x01ee, code skipped:
            r11.close();
     */
    /* JADX WARNING: Missing block: B:45:0x01f1, code skipped:
            return r8;
     */
    public com.gaana.models.BusinessObject a(com.managers.URLManager.BusinessObjectType r25, java.lang.String r26, int r27, int r28, java.lang.String r29, java.lang.String r30) {
        /*
        r24 = this;
        r1 = r24;
        r2 = r25;
        r3 = r26;
        r4 = new java.util.ArrayList;
        r4.<init>();
        r5 = com.gaana.models.Tracks.Track.class;
        r5 = "favourite_status=? AND type=?";
        r6 = com.managers.URLManager.BusinessObjectType.Tracks;
        r7 = 0;
        if (r2 != r6) goto L_0x0027;
    L_0x0014:
        r6 = "TR";
        r8 = new com.gaana.models.Tracks;
        r8.<init>();
        r9 = android.text.TextUtils.isEmpty(r26);
        r10 = com.managers.URLManager.BusinessObjectType.Tracks;
        r8.setBusinessObjType(r10);
        r10 = com.gaana.models.Tracks.Track.class;
        goto L_0x008f;
    L_0x0027:
        r6 = com.managers.URLManager.BusinessObjectType.Albums;
        if (r2 != r6) goto L_0x003e;
    L_0x002b:
        r6 = "AL";
        r8 = new com.gaana.models.Albums;
        r8.<init>();
        r9 = android.text.TextUtils.isEmpty(r26);
        r10 = com.managers.URLManager.BusinessObjectType.Albums;
        r8.setBusinessObjType(r10);
        r10 = com.gaana.models.Albums.Album.class;
        goto L_0x008f;
    L_0x003e:
        r6 = com.managers.URLManager.BusinessObjectType.Playlists;
        if (r2 != r6) goto L_0x0055;
    L_0x0042:
        r6 = "PL";
        r8 = new com.gaana.models.Playlists;
        r8.<init>();
        r9 = android.text.TextUtils.isEmpty(r26);
        r10 = com.managers.URLManager.BusinessObjectType.Playlists;
        r8.setBusinessObjType(r10);
        r10 = com.gaana.models.Playlists.Playlist.class;
        goto L_0x008f;
    L_0x0055:
        r6 = com.managers.URLManager.BusinessObjectType.Radios;
        if (r2 != r6) goto L_0x006b;
    L_0x0059:
        r6 = "RD%";
        r5 = "favourite_status=? AND type LIKE ?";
        r8 = new com.gaana.models.Radios;
        r8.<init>();
        r9 = com.managers.URLManager.BusinessObjectType.Radios;
        r8.setBusinessObjType(r9);
        r10 = com.gaana.models.Radios.Radio.class;
    L_0x0069:
        r9 = r7;
        goto L_0x008f;
    L_0x006b:
        r6 = com.managers.URLManager.BusinessObjectType.FavoriteOccasions;
        if (r2 != r6) goto L_0x0080;
    L_0x006f:
        r6 = "OC%";
        r5 = "favourite_status=? AND type LIKE ?";
        r8 = new com.gaana.models.FavoriteOccasions;
        r8.<init>();
        r9 = com.managers.URLManager.BusinessObjectType.FavoriteOccasions;
        r8.setBusinessObjType(r9);
        r10 = com.gaana.models.FavoriteOccasions.FavoriteOccasion.class;
        goto L_0x0069;
    L_0x0080:
        r8 = new com.gaana.models.Artists;
        r8.<init>();
        r6 = com.managers.URLManager.BusinessObjectType.Artists;
        r8.setBusinessObjType(r6);
        r6 = "AR";
        r10 = com.gaana.models.Artists.Artist.class;
        goto L_0x0069;
    L_0x008f:
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = r29;
        r12.append(r13);
        r13 = " ";
        r12.append(r13);
        r13 = r30;
        r12.append(r13);
        r13 = ",";
        r12.append(r13);
        r13 = "id";
        r12.append(r13);
        r13 = " ASC";
        r12.append(r13);
        r21 = r12.toString();
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = r27;
        r12.append(r13);
        r13 = ",";
        r12.append(r13);
        r13 = r28;
        r12.append(r13);
        r22 = r12.toString();
        r12 = 2;
        r13 = new java.lang.String[r12];	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r14 = "1";
        r13[r7] = r14;	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r15 = 1;
        r13[r15] = r6;	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r14 = android.text.TextUtils.isEmpty(r26);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        if (r14 != 0) goto L_0x0160;
    L_0x00de:
        r13 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r13.<init>();	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r13.append(r5);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r5 = " AND (";
        r13.append(r5);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r5 = "name";
        r13.append(r5);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r5 = " LIKE ? OR ";
        r13.append(r5);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r5 = "artist_names";
        r13.append(r5);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r5 = " LIKE ? OR ";
        r13.append(r5);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r5 = "album_name";
        r13.append(r5);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r5 = " LIKE ?)";
        r13.append(r5);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r5 = r13.toString();	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r13 = 5;
        r13 = new java.lang.String[r13];	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r14 = "1";
        r13[r7] = r14;	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r13[r15] = r6;	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r14 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r14.<init>();	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r11 = "%";
        r14.append(r11);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r14.append(r3);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r11 = "%";
        r14.append(r11);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r11 = r14.toString();	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r13[r12] = r11;	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r11 = 3;
        r14 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r14.<init>();	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r15 = "%";
        r14.append(r15);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r14.append(r3);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r15 = "%";
        r14.append(r15);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r14 = r14.toString();	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r13[r11] = r14;	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r11 = 4;
        r14 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r14.<init>();	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r15 = "%";
        r14.append(r15);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r14.append(r3);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r15 = "%";
        r14.append(r15);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r14 = r14.toString();	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r13[r11] = r14;	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
    L_0x0160:
        r17 = r5;
        r18 = r13;
        r5 = new java.lang.String[r12];	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r11 = "business_object";
        r5[r7] = r11;	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r7 = "added_on";
        r11 = 1;
        r5[r11] = r7;	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r14 = r24.k();	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        r15 = "local_favorite_table";
        r19 = 0;
        r20 = 0;
        r7 = r11;
        r16 = r5;
        r11 = r14.query(r15, r16, r17, r18, r19, r20, r21, r22);	 Catch:{ Exception -> 0x01e6, all -> 0x01e2 }
        if (r11 == 0) goto L_0x01b4;
    L_0x0182:
        r5 = r11.moveToNext();	 Catch:{ Exception -> 0x01b1 }
        if (r5 == 0) goto L_0x01b4;
    L_0x0188:
        r5 = r1.a;	 Catch:{ Exception -> 0x01b1 }
        r12 = "business_object";
        r12 = r11.getColumnIndex(r12);	 Catch:{ Exception -> 0x01b1 }
        r12 = r11.getString(r12);	 Catch:{ Exception -> 0x01b1 }
        r5 = r5.fromJson(r12, r10);	 Catch:{ Exception -> 0x01b1 }
        r5 = (com.gaana.models.BusinessObject) r5;	 Catch:{ Exception -> 0x01b1 }
        r12 = "added_on";
        r12 = r11.getColumnIndex(r12);	 Catch:{ Exception -> 0x01b1 }
        r12 = r11.getLong(r12);	 Catch:{ Exception -> 0x01b1 }
        r5.setBusinessObjType(r2);	 Catch:{ Exception -> 0x01b1 }
        r5.setUserFavorite(r7);	 Catch:{ Exception -> 0x01b1 }
        r5.setResponseTime(r12);	 Catch:{ Exception -> 0x01b1 }
        r4.add(r5);	 Catch:{ Exception -> 0x01b1 }
        goto L_0x0182;
    L_0x01b1:
        r0 = move-exception;
        r2 = r0;
        goto L_0x01e9;
    L_0x01b4:
        r2 = r4.size();	 Catch:{ Exception -> 0x01b1 }
        if (r2 <= 0) goto L_0x01df;
    L_0x01ba:
        if (r9 == 0) goto L_0x01c1;
    L_0x01bc:
        r2 = r1.b(r6, r3);	 Catch:{ Exception -> 0x01b1 }
        goto L_0x01c6;
    L_0x01c1:
        r2 = r4.size();	 Catch:{ Exception -> 0x01b1 }
        r2 = (long) r2;	 Catch:{ Exception -> 0x01b1 }
    L_0x01c6:
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01b1 }
        r5.<init>();	 Catch:{ Exception -> 0x01b1 }
        r5.append(r2);	 Catch:{ Exception -> 0x01b1 }
        r2 = "";
        r5.append(r2);	 Catch:{ Exception -> 0x01b1 }
        r2 = r5.toString();	 Catch:{ Exception -> 0x01b1 }
        r8.setCount(r2);	 Catch:{ Exception -> 0x01b1 }
        r4 = (java.util.ArrayList) r4;	 Catch:{ Exception -> 0x01b1 }
        r8.setArrListBusinessObj(r4);	 Catch:{ Exception -> 0x01b1 }
    L_0x01df:
        if (r11 == 0) goto L_0x01f1;
    L_0x01e1:
        goto L_0x01ee;
    L_0x01e2:
        r0 = move-exception;
        r2 = r0;
        r11 = 0;
        goto L_0x01f4;
    L_0x01e6:
        r0 = move-exception;
        r2 = r0;
        r11 = 0;
    L_0x01e9:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ all -> 0x01f2 }
        if (r11 == 0) goto L_0x01f1;
    L_0x01ee:
        r11.close();
    L_0x01f1:
        return r8;
    L_0x01f2:
        r0 = move-exception;
        r2 = r0;
    L_0x01f4:
        if (r11 == 0) goto L_0x01f9;
    L_0x01f6:
        r11.close();
    L_0x01f9:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.c.a(com.managers.URLManager$BusinessObjectType, java.lang.String, int, int, java.lang.String, java.lang.String):com.gaana.models.BusinessObject");
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x01cf  */
    /* JADX WARNING: Missing block: B:36:0x01b8, code skipped:
            if (r11 != null) goto L_0x01c7;
     */
    /* JADX WARNING: Missing block: B:43:0x01c5, code skipped:
            if (r11 != null) goto L_0x01c7;
     */
    /* JADX WARNING: Missing block: B:44:0x01c7, code skipped:
            r11.close();
     */
    /* JADX WARNING: Missing block: B:45:0x01ca, code skipped:
            return r8;
     */
    public com.gaana.models.BusinessObject a(com.managers.URLManager.BusinessObjectType r24, java.lang.String r25, java.lang.String r26, java.lang.String r27) {
        /*
        r23 = this;
        r1 = r23;
        r2 = r24;
        r3 = r25;
        r4 = new java.util.ArrayList;
        r4.<init>();
        r5 = com.gaana.models.Tracks.Track.class;
        r5 = "favourite_status=? AND type=?";
        r6 = com.managers.URLManager.BusinessObjectType.Tracks;
        r7 = 0;
        if (r2 != r6) goto L_0x0027;
    L_0x0014:
        r6 = "TR";
        r8 = new com.gaana.models.Tracks;
        r8.<init>();
        r9 = android.text.TextUtils.isEmpty(r25);
        r10 = com.managers.URLManager.BusinessObjectType.Tracks;
        r8.setBusinessObjType(r10);
        r10 = com.gaana.models.Tracks.Track.class;
        goto L_0x008f;
    L_0x0027:
        r6 = com.managers.URLManager.BusinessObjectType.Albums;
        if (r2 != r6) goto L_0x003e;
    L_0x002b:
        r6 = "AL";
        r8 = new com.gaana.models.Albums;
        r8.<init>();
        r9 = android.text.TextUtils.isEmpty(r25);
        r10 = com.managers.URLManager.BusinessObjectType.Albums;
        r8.setBusinessObjType(r10);
        r10 = com.gaana.models.Albums.Album.class;
        goto L_0x008f;
    L_0x003e:
        r6 = com.managers.URLManager.BusinessObjectType.Playlists;
        if (r2 != r6) goto L_0x0055;
    L_0x0042:
        r6 = "PL";
        r8 = new com.gaana.models.Playlists;
        r8.<init>();
        r9 = android.text.TextUtils.isEmpty(r25);
        r10 = com.managers.URLManager.BusinessObjectType.Playlists;
        r8.setBusinessObjType(r10);
        r10 = com.gaana.models.Playlists.Playlist.class;
        goto L_0x008f;
    L_0x0055:
        r6 = com.managers.URLManager.BusinessObjectType.Radios;
        if (r2 != r6) goto L_0x006b;
    L_0x0059:
        r6 = "RD%";
        r5 = "favourite_status=? AND type LIKE ?";
        r8 = new com.gaana.models.Radios;
        r8.<init>();
        r9 = com.managers.URLManager.BusinessObjectType.Radios;
        r8.setBusinessObjType(r9);
        r10 = com.gaana.models.Radios.Radio.class;
    L_0x0069:
        r9 = r7;
        goto L_0x008f;
    L_0x006b:
        r6 = com.managers.URLManager.BusinessObjectType.FavoriteOccasions;
        if (r2 != r6) goto L_0x0080;
    L_0x006f:
        r6 = "OC%";
        r5 = "favourite_status=? AND type LIKE ?";
        r8 = new com.gaana.models.FavoriteOccasions;
        r8.<init>();
        r9 = com.managers.URLManager.BusinessObjectType.FavoriteOccasions;
        r8.setBusinessObjType(r9);
        r10 = com.gaana.models.FavoriteOccasions.FavoriteOccasion.class;
        goto L_0x0069;
    L_0x0080:
        r8 = new com.gaana.models.Artists;
        r8.<init>();
        r6 = com.managers.URLManager.BusinessObjectType.Artists;
        r8.setBusinessObjType(r6);
        r6 = "AR";
        r10 = com.gaana.models.Artists.Artist.class;
        goto L_0x0069;
    L_0x008f:
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = r26;
        r12.append(r13);
        r13 = " ";
        r12.append(r13);
        r13 = r27;
        r12.append(r13);
        r20 = r12.toString();
        r12 = 2;
        r13 = new java.lang.String[r12];	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r14 = "1";
        r13[r7] = r14;	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r15 = 1;
        r13[r15] = r6;	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r14 = android.text.TextUtils.isEmpty(r25);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        if (r14 != 0) goto L_0x0139;
    L_0x00b7:
        r13 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r13.<init>();	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r13.append(r5);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r5 = " AND (";
        r13.append(r5);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r5 = "name";
        r13.append(r5);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r5 = " LIKE ? OR ";
        r13.append(r5);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r5 = "artist_names";
        r13.append(r5);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r5 = " LIKE ? OR ";
        r13.append(r5);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r5 = "album_name";
        r13.append(r5);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r5 = " LIKE ?)";
        r13.append(r5);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r5 = r13.toString();	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r13 = 5;
        r13 = new java.lang.String[r13];	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r14 = "1";
        r13[r7] = r14;	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r13[r15] = r6;	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r14 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r14.<init>();	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r11 = "%";
        r14.append(r11);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r14.append(r3);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r11 = "%";
        r14.append(r11);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r11 = r14.toString();	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r13[r12] = r11;	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r11 = 3;
        r14 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r14.<init>();	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r15 = "%";
        r14.append(r15);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r14.append(r3);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r15 = "%";
        r14.append(r15);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r14 = r14.toString();	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r13[r11] = r14;	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r11 = 4;
        r14 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r14.<init>();	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r15 = "%";
        r14.append(r15);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r14.append(r3);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r15 = "%";
        r14.append(r15);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r14 = r14.toString();	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r13[r11] = r14;	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
    L_0x0139:
        r16 = r5;
        r17 = r13;
        r15 = new java.lang.String[r12];	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r5 = "business_object";
        r15[r7] = r5;	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r5 = "added_on";
        r7 = 1;
        r15[r7] = r5;	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r13 = r23.k();	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        r14 = "local_favorite_table";
        r18 = 0;
        r19 = 0;
        r21 = 0;
        r5 = r7;
        r11 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21);	 Catch:{ Exception -> 0x01bf, all -> 0x01bb }
        if (r11 == 0) goto L_0x018d;
    L_0x015b:
        r7 = r11.moveToNext();	 Catch:{ Exception -> 0x018a }
        if (r7 == 0) goto L_0x018d;
    L_0x0161:
        r7 = r1.a;	 Catch:{ Exception -> 0x018a }
        r12 = "business_object";
        r12 = r11.getColumnIndex(r12);	 Catch:{ Exception -> 0x018a }
        r12 = r11.getString(r12);	 Catch:{ Exception -> 0x018a }
        r7 = r7.fromJson(r12, r10);	 Catch:{ Exception -> 0x018a }
        r7 = (com.gaana.models.BusinessObject) r7;	 Catch:{ Exception -> 0x018a }
        r12 = "added_on";
        r12 = r11.getColumnIndex(r12);	 Catch:{ Exception -> 0x018a }
        r12 = r11.getLong(r12);	 Catch:{ Exception -> 0x018a }
        r7.setBusinessObjType(r2);	 Catch:{ Exception -> 0x018a }
        r7.setUserFavorite(r5);	 Catch:{ Exception -> 0x018a }
        r7.setResponseTime(r12);	 Catch:{ Exception -> 0x018a }
        r4.add(r7);	 Catch:{ Exception -> 0x018a }
        goto L_0x015b;
    L_0x018a:
        r0 = move-exception;
        r2 = r0;
        goto L_0x01c2;
    L_0x018d:
        r2 = r4.size();	 Catch:{ Exception -> 0x018a }
        if (r2 <= 0) goto L_0x01b8;
    L_0x0193:
        if (r9 == 0) goto L_0x019a;
    L_0x0195:
        r2 = r1.b(r6, r3);	 Catch:{ Exception -> 0x018a }
        goto L_0x019f;
    L_0x019a:
        r2 = r4.size();	 Catch:{ Exception -> 0x018a }
        r2 = (long) r2;	 Catch:{ Exception -> 0x018a }
    L_0x019f:
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x018a }
        r5.<init>();	 Catch:{ Exception -> 0x018a }
        r5.append(r2);	 Catch:{ Exception -> 0x018a }
        r2 = "";
        r5.append(r2);	 Catch:{ Exception -> 0x018a }
        r2 = r5.toString();	 Catch:{ Exception -> 0x018a }
        r8.setCount(r2);	 Catch:{ Exception -> 0x018a }
        r4 = (java.util.ArrayList) r4;	 Catch:{ Exception -> 0x018a }
        r8.setArrListBusinessObj(r4);	 Catch:{ Exception -> 0x018a }
    L_0x01b8:
        if (r11 == 0) goto L_0x01ca;
    L_0x01ba:
        goto L_0x01c7;
    L_0x01bb:
        r0 = move-exception;
        r2 = r0;
        r11 = 0;
        goto L_0x01cd;
    L_0x01bf:
        r0 = move-exception;
        r2 = r0;
        r11 = 0;
    L_0x01c2:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r2);	 Catch:{ all -> 0x01cb }
        if (r11 == 0) goto L_0x01ca;
    L_0x01c7:
        r11.close();
    L_0x01ca:
        return r8;
    L_0x01cb:
        r0 = move-exception;
        r2 = r0;
    L_0x01cd:
        if (r11 == 0) goto L_0x01d2;
    L_0x01cf:
        r11.close();
    L_0x01d2:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.c.a(com.managers.URLManager$BusinessObjectType, java.lang.String, java.lang.String, java.lang.String):com.gaana.models.BusinessObject");
    }

    public long b(String str, String str2) {
        String str3 = "favourite_status=? AND type=?";
        String[] strArr = new String[]{"1", str};
        if (!TextUtils.isEmpty(str2)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str3);
            stringBuilder.append(" AND (");
            stringBuilder.append("name");
            stringBuilder.append(" LIKE ? OR ");
            stringBuilder.append("artist_names");
            stringBuilder.append(" LIKE ? OR ");
            stringBuilder.append("album_name");
            stringBuilder.append(" LIKE ?)");
            str3 = stringBuilder.toString();
            strArr = new String[5];
            strArr[0] = "1";
            strArr[1] = str;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("%");
            stringBuilder2.append(str2);
            stringBuilder2.append("%");
            strArr[2] = stringBuilder2.toString();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("%");
            stringBuilder3.append(str2);
            stringBuilder3.append("%");
            strArr[3] = stringBuilder3.toString();
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("%");
            stringBuilder3.append(str2);
            stringBuilder3.append("%");
            strArr[4] = stringBuilder3.toString();
        }
        return DatabaseUtils.queryNumEntries(k(), "local_favorite_table", str3, strArr);
    }

    public void b() {
        k().delete("local_favorite_table", null, null);
    }

    public int a(BusinessObject businessObject) {
        int i = -1;
        if (!(businessObject == null || TextUtils.isEmpty(businessObject.getBusinessObjId()))) {
            String[] strArr = new String[]{"favourite_status"};
            String b = b(businessObject);
            String[] strArr2 = new String[]{businessObject.getBusinessObjId(), b};
            Cursor query = k().query("local_favorite_table", strArr, "id=? and type=?", strArr2, null, null, null);
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            query.close();
        }
        return i;
    }

    public String b(BusinessObject businessObject) {
        String str = "TR";
        if (businessObject instanceof FavoriteOccasion) {
            return "OC";
        }
        if (businessObject instanceof Album) {
            return "AL";
        }
        if (businessObject instanceof Radio) {
            return ((Radio) businessObject).getType().equals(d.d) ? "RD_L" : "RD_M";
        } else {
            if (businessObject instanceof Playlist) {
                return "PL";
            }
            if (businessObject instanceof Track) {
                return "TR";
            }
            return businessObject instanceof Artist ? "AR" : str;
        }
    }

    public long a(String str, BusinessObjectType businessObjectType) {
        return c(str, a(businessObjectType));
    }

    public long c(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM local_favorite_table WHERE id IN (%s) AND type=\"");
        stringBuilder.append(str2);
        stringBuilder.append("\" ;");
        str2 = stringBuilder.toString();
        k().execSQL(String.format(str2, new Object[]{str}));
        return 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x006a  */
    /* JADX WARNING: Missing block: B:12:0x0061, code skipped:
            if (r0 != null) goto L_0x0071;
     */
    /* JADX WARNING: Missing block: B:21:0x006f, code skipped:
            if (r0 != null) goto L_0x0071;
     */
    /* JADX WARNING: Missing block: B:23:0x0071, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:24:0x0074, code skipped:
            return r8;
     */
    public java.util.List<com.gaana.models.FavoriteItem> c() {
        /*
        r12 = this;
        r0 = r12.k();
        r8 = new java.util.ArrayList;
        r8.<init>();
        r1 = 3;
        r9 = 0;
        r2 = new java.lang.String[r1];	 Catch:{ Exception -> 0x006e, all -> 0x0066 }
        r1 = "id";
        r10 = 0;
        r2[r10] = r1;	 Catch:{ Exception -> 0x006e, all -> 0x0066 }
        r1 = "type";
        r11 = 1;
        r2[r11] = r1;	 Catch:{ Exception -> 0x006e, all -> 0x0066 }
        r1 = 2;
        r3 = "favourite_status";
        r2[r1] = r3;	 Catch:{ Exception -> 0x006e, all -> 0x0066 }
        r3 = "has_synced=?";
        r4 = new java.lang.String[r11];	 Catch:{ Exception -> 0x006e, all -> 0x0066 }
        r1 = java.lang.String.valueOf(r10);	 Catch:{ Exception -> 0x006e, all -> 0x0066 }
        r4[r10] = r1;	 Catch:{ Exception -> 0x006e, all -> 0x0066 }
        r1 = "local_favorite_table";
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x006e, all -> 0x0066 }
    L_0x002f:
        r1 = r0.moveToNext();	 Catch:{ Exception -> 0x006f, all -> 0x0064 }
        if (r1 == 0) goto L_0x0061;
    L_0x0035:
        r1 = "favourite_status";
        r1 = r0.getColumnIndex(r1);	 Catch:{ Exception -> 0x006f, all -> 0x0064 }
        r1 = r0.getInt(r1);	 Catch:{ Exception -> 0x006f, all -> 0x0064 }
        if (r1 != r11) goto L_0x0043;
    L_0x0041:
        r1 = r11;
        goto L_0x0044;
    L_0x0043:
        r1 = r10;
    L_0x0044:
        r2 = "type";
        r2 = r0.getColumnIndex(r2);	 Catch:{ Exception -> 0x006f, all -> 0x0064 }
        r2 = r0.getString(r2);	 Catch:{ Exception -> 0x006f, all -> 0x0064 }
        r3 = "id";
        r3 = r0.getColumnIndex(r3);	 Catch:{ Exception -> 0x006f, all -> 0x0064 }
        r3 = r0.getString(r3);	 Catch:{ Exception -> 0x006f, all -> 0x0064 }
        r4 = new com.gaana.models.FavoriteItem;	 Catch:{ Exception -> 0x006f, all -> 0x0064 }
        r4.<init>(r3, r2, r1);	 Catch:{ Exception -> 0x006f, all -> 0x0064 }
        r8.add(r4);	 Catch:{ Exception -> 0x006f, all -> 0x0064 }
        goto L_0x002f;
    L_0x0061:
        if (r0 == 0) goto L_0x0074;
    L_0x0063:
        goto L_0x0071;
    L_0x0064:
        r1 = move-exception;
        goto L_0x0068;
    L_0x0066:
        r1 = move-exception;
        r0 = r9;
    L_0x0068:
        if (r0 == 0) goto L_0x006d;
    L_0x006a:
        r0.close();
    L_0x006d:
        throw r1;
    L_0x006e:
        r0 = r9;
    L_0x006f:
        if (r0 == 0) goto L_0x0074;
    L_0x0071:
        r0.close();
    L_0x0074:
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.c.c():java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077  */
    public java.lang.Class<?> a(java.lang.String r4) {
        /*
        r3 = this;
        r0 = com.gaana.models.Tracks.Track.class;
        r1 = r4.hashCode();
        r2 = 2091; // 0x82b float:2.93E-42 double:1.033E-320;
        if (r1 == r2) goto L_0x0068;
    L_0x000a:
        r2 = 2097; // 0x831 float:2.939E-42 double:1.036E-320;
        if (r1 == r2) goto L_0x005e;
    L_0x000e:
        r2 = 2516; // 0x9d4 float:3.526E-42 double:1.243E-320;
        if (r1 == r2) goto L_0x0054;
    L_0x0012:
        r2 = 2556; // 0x9fc float:3.582E-42 double:1.263E-320;
        if (r1 == r2) goto L_0x004a;
    L_0x0016:
        r2 = 2610; // 0xa32 float:3.657E-42 double:1.2895E-320;
        if (r1 == r2) goto L_0x0040;
    L_0x001a:
        r2 = 2686; // 0xa7e float:3.764E-42 double:1.327E-320;
        if (r1 == r2) goto L_0x0036;
    L_0x001e:
        switch(r1) {
            case 2511231: goto L_0x002c;
            case 2511232: goto L_0x0022;
            default: goto L_0x0021;
        };
    L_0x0021:
        goto L_0x0072;
    L_0x0022:
        r1 = "RD_M";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x002a:
        r4 = 5;
        goto L_0x0073;
    L_0x002c:
        r1 = "RD_L";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x0034:
        r4 = 4;
        goto L_0x0073;
    L_0x0036:
        r1 = "TR";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x003e:
        r4 = 0;
        goto L_0x0073;
    L_0x0040:
        r1 = "RD";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x0048:
        r4 = 3;
        goto L_0x0073;
    L_0x004a:
        r1 = "PL";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x0052:
        r4 = 1;
        goto L_0x0073;
    L_0x0054:
        r1 = "OC";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x005c:
        r4 = 7;
        goto L_0x0073;
    L_0x005e:
        r1 = "AR";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x0066:
        r4 = 2;
        goto L_0x0073;
    L_0x0068:
        r1 = "AL";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x0070:
        r4 = 6;
        goto L_0x0073;
    L_0x0072:
        r4 = -1;
    L_0x0073:
        switch(r4) {
            case 0: goto L_0x0085;
            case 1: goto L_0x0083;
            case 2: goto L_0x0080;
            case 3: goto L_0x007d;
            case 4: goto L_0x007d;
            case 5: goto L_0x007d;
            case 6: goto L_0x007a;
            case 7: goto L_0x0077;
            default: goto L_0x0076;
        };
    L_0x0076:
        goto L_0x0085;
    L_0x0077:
        r0 = com.gaana.models.FavoriteOccasions.FavoriteOccasion.class;
        goto L_0x0085;
    L_0x007a:
        r0 = com.gaana.models.Albums.Album.class;
        goto L_0x0085;
    L_0x007d:
        r0 = com.gaana.models.Radios.Radio.class;
        goto L_0x0085;
    L_0x0080:
        r0 = com.gaana.models.Artists.Artist.class;
        goto L_0x0085;
    L_0x0083:
        r0 = com.gaana.models.Playlists.Playlist.class;
    L_0x0085:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.c.a(java.lang.String):java.lang.Class");
    }

    public String a(BusinessObjectType businessObjectType) {
        String str = "TR";
        switch (businessObjectType) {
            case Albums:
                return "AL";
            case Playlists:
                return "PL";
            case Radios:
                return "RD";
            case Artists:
                return "AR";
            case FavoriteOccasions:
                return "OC";
            default:
                return str;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0077  */
    public com.managers.URLManager.BusinessObjectType b(java.lang.String r4) {
        /*
        r3 = this;
        r0 = com.managers.URLManager.BusinessObjectType.Tracks;
        r1 = r4.hashCode();
        r2 = 2091; // 0x82b float:2.93E-42 double:1.033E-320;
        if (r1 == r2) goto L_0x0068;
    L_0x000a:
        r2 = 2097; // 0x831 float:2.939E-42 double:1.036E-320;
        if (r1 == r2) goto L_0x005e;
    L_0x000e:
        r2 = 2516; // 0x9d4 float:3.526E-42 double:1.243E-320;
        if (r1 == r2) goto L_0x0054;
    L_0x0012:
        r2 = 2556; // 0x9fc float:3.582E-42 double:1.263E-320;
        if (r1 == r2) goto L_0x004a;
    L_0x0016:
        r2 = 2610; // 0xa32 float:3.657E-42 double:1.2895E-320;
        if (r1 == r2) goto L_0x0040;
    L_0x001a:
        r2 = 2686; // 0xa7e float:3.764E-42 double:1.327E-320;
        if (r1 == r2) goto L_0x0036;
    L_0x001e:
        switch(r1) {
            case 2511231: goto L_0x002c;
            case 2511232: goto L_0x0022;
            default: goto L_0x0021;
        };
    L_0x0021:
        goto L_0x0072;
    L_0x0022:
        r1 = "RD_M";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x002a:
        r4 = 4;
        goto L_0x0073;
    L_0x002c:
        r1 = "RD_L";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x0034:
        r4 = 5;
        goto L_0x0073;
    L_0x0036:
        r1 = "TR";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x003e:
        r4 = 0;
        goto L_0x0073;
    L_0x0040:
        r1 = "RD";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x0048:
        r4 = 3;
        goto L_0x0073;
    L_0x004a:
        r1 = "PL";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x0052:
        r4 = 1;
        goto L_0x0073;
    L_0x0054:
        r1 = "OC";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x005c:
        r4 = 7;
        goto L_0x0073;
    L_0x005e:
        r1 = "AR";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x0066:
        r4 = 2;
        goto L_0x0073;
    L_0x0068:
        r1 = "AL";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x0072;
    L_0x0070:
        r4 = 6;
        goto L_0x0073;
    L_0x0072:
        r4 = -1;
    L_0x0073:
        switch(r4) {
            case 0: goto L_0x0085;
            case 1: goto L_0x0083;
            case 2: goto L_0x0080;
            case 3: goto L_0x007d;
            case 4: goto L_0x007d;
            case 5: goto L_0x007d;
            case 6: goto L_0x007a;
            case 7: goto L_0x0077;
            default: goto L_0x0076;
        };
    L_0x0076:
        goto L_0x0085;
    L_0x0077:
        r0 = com.managers.URLManager.BusinessObjectType.FavoriteOccasions;
        goto L_0x0085;
    L_0x007a:
        r0 = com.managers.URLManager.BusinessObjectType.Albums;
        goto L_0x0085;
    L_0x007d:
        r0 = com.managers.URLManager.BusinessObjectType.Radios;
        goto L_0x0085;
    L_0x0080:
        r0 = com.managers.URLManager.BusinessObjectType.Artists;
        goto L_0x0085;
    L_0x0083:
        r0 = com.managers.URLManager.BusinessObjectType.Playlists;
    L_0x0085:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.c.b(java.lang.String):com.managers.URLManager$BusinessObjectType");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0043  */
    public java.util.List<java.lang.String> c(java.lang.String r11) {
        /*
        r10 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r4 = "favourite_status=? AND type=?";
        r1 = 2;
        r5 = new java.lang.String[r1];
        r1 = "1";
        r9 = 0;
        r5[r9] = r1;
        r1 = 1;
        r5[r1] = r11;
        r3 = new java.lang.String[r1];
        r11 = "id";
        r3[r9] = r11;
        r11 = 0;
        r1 = r10.k();	 Catch:{ Exception -> 0x0047, all -> 0x003e }
        r2 = "local_favorite_table";
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r1 = r1.query(r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0047, all -> 0x003e }
    L_0x0026:
        r11 = r1.moveToNext();	 Catch:{ Exception -> 0x003c, all -> 0x003a }
        if (r11 == 0) goto L_0x0034;
    L_0x002c:
        r11 = r1.getString(r9);	 Catch:{ Exception -> 0x003c, all -> 0x003a }
        r0.add(r11);	 Catch:{ Exception -> 0x003c, all -> 0x003a }
        goto L_0x0026;
    L_0x0034:
        if (r1 == 0) goto L_0x004c;
    L_0x0036:
        r1.close();
        goto L_0x004c;
    L_0x003a:
        r11 = move-exception;
        goto L_0x0041;
    L_0x003c:
        r11 = r1;
        goto L_0x0047;
    L_0x003e:
        r0 = move-exception;
        r1 = r11;
        r11 = r0;
    L_0x0041:
        if (r1 == 0) goto L_0x0046;
    L_0x0043:
        r1.close();
    L_0x0046:
        throw r11;
    L_0x0047:
        if (r11 == 0) goto L_0x004c;
    L_0x0049:
        r11.close();
    L_0x004c:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.c.c(java.lang.String):java.util.List");
    }

    public List<String> d() {
        return c("TR");
    }

    public List<String> e() {
        return c("PL");
    }

    public List<String> f() {
        return c("AL");
    }

    public List<String> g() {
        return c("AR");
    }

    public List<String> h() {
        return c("RD_L");
    }

    public List<String> i() {
        return c("OC");
    }

    public List<String> j() {
        return c("RD_M");
    }

    public long d(String str, String str2) {
        String[] strArr = new String[3];
        strArr[0] = "1";
        strArr[1] = str;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("%");
        stringBuilder.append(str2);
        stringBuilder.append("%");
        strArr[2] = stringBuilder.toString();
        return DatabaseUtils.queryNumEntries(k(), "local_favorite_table", "favourite_status=? AND type=? AND artist_names LIKE ?", strArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x00c1  */
    /* JADX WARNING: Missing block: B:13:0x00b8, code skipped:
            if (r6 != null) goto L_0x00c9;
     */
    /* JADX WARNING: Missing block: B:21:0x00c7, code skipped:
            if (r6 != null) goto L_0x00c9;
     */
    /* JADX WARNING: Missing block: B:23:0x00c9, code skipped:
            r6.close();
     */
    /* JADX WARNING: Missing block: B:24:0x00cc, code skipped:
            return r5;
     */
    public com.gaana.models.BusinessObject a(java.lang.String r24, int r25, int r26, java.lang.String r27) {
        /*
        r23 = this;
        r1 = r23;
        r2 = r24;
        r3 = r27;
        r4 = new java.util.ArrayList;
        r4.<init>();
        r5 = new com.gaana.models.BusinessObject;
        r5.<init>();
        r9 = "favourite_status=? AND type=? AND artist_names LIKE ?";
        r6 = 3;
        r10 = new java.lang.String[r6];
        r6 = "1";
        r15 = 0;
        r10[r15] = r6;
        r14 = 1;
        r10[r14] = r3;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "%";
        r6.append(r7);
        r6.append(r2);
        r7 = "%";
        r6.append(r7);
        r6 = r6.toString();
        r7 = 2;
        r10[r7] = r6;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = r25;
        r6.append(r7);
        r7 = ",";
        r6.append(r7);
        r7 = r26;
        r6.append(r7);
        r16 = r6.toString();
        r13 = r1.a(r3);
        r12 = r1.b(r3);
        r5.setBusinessObjType(r12);
        r17 = 0;
        r8 = new java.lang.String[r14];	 Catch:{ Exception -> 0x00c5, all -> 0x00bb }
        r6 = "business_object";
        r8[r15] = r6;	 Catch:{ Exception -> 0x00c5, all -> 0x00bb }
        r6 = r23.k();	 Catch:{ Exception -> 0x00c5, all -> 0x00bb }
        r18 = "name,id";
        r7 = "local_favorite_table";
        r11 = 0;
        r19 = 0;
        r20 = r12;
        r12 = r19;
        r21 = r13;
        r13 = r18;
        r14 = r16;
        r6 = r6.query(r7, r8, r9, r10, r11, r12, r13, r14);	 Catch:{ Exception -> 0x00c5, all -> 0x00bb }
        if (r6 == 0) goto L_0x00a4;
    L_0x007c:
        r7 = r6.moveToNext();	 Catch:{ Exception -> 0x00c7, all -> 0x00a1 }
        if (r7 == 0) goto L_0x00a4;
    L_0x0082:
        r7 = r1.a;	 Catch:{ Exception -> 0x00c7, all -> 0x00a1 }
        r8 = r6.getString(r15);	 Catch:{ Exception -> 0x00c7, all -> 0x00a1 }
        r9 = r21;
        r7 = r7.fromJson(r8, r9);	 Catch:{ Exception -> 0x00c7, all -> 0x00a1 }
        r7 = (com.gaana.models.BusinessObject) r7;	 Catch:{ Exception -> 0x00c7, all -> 0x00a1 }
        r8 = r20;
        r7.setBusinessObjType(r8);	 Catch:{ Exception -> 0x00c7, all -> 0x00a1 }
        r10 = 1;
        r7.setUserFavorite(r10);	 Catch:{ Exception -> 0x00c7, all -> 0x00a1 }
        r4.add(r7);	 Catch:{ Exception -> 0x00c7, all -> 0x00a1 }
        r20 = r8;
        r21 = r9;
        goto L_0x007c;
    L_0x00a1:
        r0 = move-exception;
        r2 = r0;
        goto L_0x00bf;
    L_0x00a4:
        r5.setArrListBusinessObj(r4);	 Catch:{ Exception -> 0x00c7, all -> 0x00a1 }
        r4 = r4.size();	 Catch:{ Exception -> 0x00c7, all -> 0x00a1 }
        if (r4 <= 0) goto L_0x00b8;
    L_0x00ad:
        r2 = r1.d(r3, r2);	 Catch:{ Exception -> 0x00c7, all -> 0x00a1 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ Exception -> 0x00c7, all -> 0x00a1 }
        r5.setCount(r2);	 Catch:{ Exception -> 0x00c7, all -> 0x00a1 }
    L_0x00b8:
        if (r6 == 0) goto L_0x00cc;
    L_0x00ba:
        goto L_0x00c9;
    L_0x00bb:
        r0 = move-exception;
        r2 = r0;
        r6 = r17;
    L_0x00bf:
        if (r6 == 0) goto L_0x00c4;
    L_0x00c1:
        r6.close();
    L_0x00c4:
        throw r2;
    L_0x00c5:
        r6 = r17;
    L_0x00c7:
        if (r6 == 0) goto L_0x00cc;
    L_0x00c9:
        r6.close();
    L_0x00cc:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.a.c.a(java.lang.String, int, int, java.lang.String):com.gaana.models.BusinessObject");
    }

    public BusinessObject a(String str, int i, int i2) {
        return a(str, i, i2, "AL");
    }
}
