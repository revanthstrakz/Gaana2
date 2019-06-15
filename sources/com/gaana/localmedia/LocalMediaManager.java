package com.gaana.localmedia;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Audio.Playlists;
import android.provider.MediaStore.Audio.Playlists.Members;
import android.text.TextUtils;
import com.constants.Constants;
import com.e.a.e.c;
import com.e.a.f;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.PlaylistSyncManager.PLAYLIST_STATUS;
import com.gaana.models.Albums;
import com.gaana.models.Albums.Album;
import com.gaana.models.Albums.Album.Artist;
import com.gaana.models.Artists;
import com.gaana.models.BusinessObject;
import com.gaana.models.LocalTrack;
import com.gaana.models.NextGenSearchAutoSuggests;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.NextGenSearchAutoSuggests.GroupItem;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.util.StorageUtils;
import com.logging.TrackLog;
import com.managers.DownloadManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;
import com.services.d;
import com.utilities.Util;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class LocalMediaManager {
    public static final String MY_MUSIC = "My Music";
    public static final String PLYALIST_MOST_HEARD_ID = "PLYALIST_MOST_HEARD_ID";
    public static final String PLYALIST_RECENTLY_ADDED_ID = "PLYALIST_RECENTLY_ADDED_ID";
    private static String TAG = "LocalMediaManager";
    private static LocalMediaManager mLocalMediaManager = null;
    private static HashMap<String, String> mediaIdAndHashMapping = null;
    public static boolean setAlbumFlagIsDirty = false;
    public static boolean setArtistFlagIsDirty = false;
    public static boolean setPlaylistFlagIsDirty;
    public static boolean setSongFlagIsDirty;
    private ContentResolver mContentResolver = null;
    private Context mContext;
    private BusinessObject mLocalAlbums;
    private BusinessObject mLocalArtists;
    private BusinessObject mLocalPlaylists;
    private BusinessObject mLocalTracks;
    private int songCount = 0;

    public void addActivity(TrackLog trackLog) {
    }

    public void init() {
    }

    private LocalMediaManager(Context context) {
        this.mContext = context.getApplicationContext();
        this.mContentResolver = this.mContext.getContentResolver();
        mediaIdAndHashMapping = new HashMap();
        if (Constants.Q) {
            initMediaIdHashMapping();
        }
    }

    public static LocalMediaManager getInstance(Context context) {
        if (mLocalMediaManager == null) {
            mLocalMediaManager = new LocalMediaManager(context);
        }
        mLocalMediaManager.mContext = context.getApplicationContext();
        return mLocalMediaManager;
    }

    public BusinessObject getLocalMedia(URLManager uRLManager) {
        BusinessObjectType i = uRLManager.i();
        if (Constants.Q) {
            if (uRLManager.i() == BusinessObjectType.Albums) {
                if (uRLManager.o()) {
                    return getLocalandDonwloadedAlbums(uRLManager.q(), uRLManager.s(), true);
                }
                return getLocalAlbums(uRLManager.q(), uRLManager.m().booleanValue());
            } else if (i == BusinessObjectType.Artists) {
                if (uRLManager.o()) {
                    return getLocalandDownloadedArtists(uRLManager.q(), uRLManager.m(), true);
                }
                return getLocalArtists(uRLManager.q(), uRLManager.m().booleanValue());
            } else if (i == BusinessObjectType.Tracks) {
                if (uRLManager.o()) {
                    return getLocalandDownloadedSongs(uRLManager.q(), uRLManager.s(), true);
                }
                return getLocalSongs(uRLManager.q(), uRLManager.m().booleanValue());
            } else if (i == BusinessObjectType.Playlists) {
                if (!uRLManager.n() || uRLManager.r()) {
                    return getMyPlaylists(uRLManager);
                }
                if (uRLManager.o()) {
                    return getLocalandDownloadedPlaylists(uRLManager.q(), uRLManager.s(), true);
                }
                return getLocalPlaylists(uRLManager);
            } else if (i == BusinessObjectType.ALL && uRLManager.o()) {
                return getAllLocalandDownloadedMedia(uRLManager.q(), uRLManager.s());
            }
        } else if (i == BusinessObjectType.Playlists) {
            return getMyPlaylists(uRLManager);
        }
        BusinessObject businessObject = new BusinessObject();
        businessObject.setBusinessObjType(uRLManager.i());
        return businessObject;
    }

    private BusinessObject getLocalandDownloadedArtists(String str, Boolean bool, boolean z) {
        if (z) {
            return convertToAutoSuggest(getLocalArtists(str, bool.booleanValue()), false, false);
        }
        return getLocalArtists(str, bool.booleanValue());
    }

    public BusinessObject getDetailPage(URLManager uRLManager, String str) {
        Tracks tracks = new Tracks();
        if (uRLManager.l() == BusinessObjectType.Albums) {
            tracks.setArrListBusinessObj(getSongsByAlbum(str));
            return tracks;
        } else if (uRLManager.l() == BusinessObjectType.Playlists) {
            tracks.setArrListBusinessObj(getSongsByPlaylist(str));
            return tracks;
        } else {
            if (uRLManager.l() == BusinessObjectType.Artists) {
                if (uRLManager.i() == BusinessObjectType.Albums) {
                    Albums albums = new Albums();
                    albums.setArrListBusinessObj(getAlbumByArtist(Long.parseLong(str)));
                    return albums;
                } else if ((uRLManager.i() == BusinessObjectType.GenericItems && uRLManager.a()) || uRLManager.i() == BusinessObjectType.Tracks) {
                    tracks.setArrListBusinessObj(getSongsByArtist(Long.parseLong(str)));
                    return tracks;
                }
            }
            return null;
        }
    }

    public BusinessObject getLocalandDonwloadedAlbums(String str, boolean z, boolean z2) {
        BusinessObject businessObject = new BusinessObject();
        ArrayList arrayList = new ArrayList();
        Collection collection = null;
        BusinessObject g;
        Iterator it;
        Album album;
        if (z) {
            g = DownloadManager.c().g(null);
            if (g != null) {
                collection = g.getArrListBusinessObj();
            }
            if (TextUtils.isEmpty(str)) {
                arrayList.addAll(collection);
            } else if (collection != null && collection.size() > 0) {
                it = collection.iterator();
                while (it.hasNext()) {
                    g = (BusinessObject) it.next();
                    if (g.getRawName() == null || !g.getRawName().toUpperCase().contains(str.trim().toUpperCase())) {
                        album = (Album) g;
                        if (album.getRawArtistNames() != null) {
                            if (!album.getRawArtistNames().toUpperCase().contains(str.trim().toUpperCase())) {
                            }
                        }
                    }
                    arrayList.add(g);
                }
            }
        } else {
            g = getLocalAlbums(null, false);
            if (g != null) {
                collection = g.getArrListBusinessObj();
            }
            if (TextUtils.isEmpty(str)) {
                arrayList.addAll(collection);
            } else if (collection != null && collection.size() > 0) {
                it = collection.iterator();
                while (it.hasNext()) {
                    g = (BusinessObject) it.next();
                    if (g.getRawName() == null || !g.getRawName().toUpperCase().contains(str.trim().toUpperCase())) {
                        album = (Album) g;
                        if (album.getRawArtistNames() != null) {
                            if (!album.getRawArtistNames().toUpperCase().contains(str.trim().toUpperCase())) {
                            }
                        }
                    }
                    arrayList.add(g);
                }
            }
        }
        businessObject.setArrListBusinessObj(arrayList);
        businessObject.setName(this.mContext.getResources().getString(R.string.albums));
        businessObject.setBusinessObjType(BusinessObjectType.Albums);
        if (TextUtils.isEmpty(str)) {
            this.mLocalAlbums = businessObject;
        }
        return z2 ? convertToAutoSuggest(businessObject, z, false) : businessObject;
    }

    public BusinessObject getAllLocalandDownloadedMedia(String str, boolean z) {
        BusinessObject businessObject = new BusinessObject();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getLocalandDownloadedSongs(str, z, false).getArrListBusinessObj());
        arrayList.addAll(getLocalandDonwloadedAlbums(str, z, false).getArrListBusinessObj());
        arrayList.addAll(getLocalandDownloadedPlaylists(str, z, false).getArrListBusinessObj());
        if (!z) {
            arrayList.addAll(getLocalandDownloadedArtists(str, Boolean.valueOf(z), false).getArrListBusinessObj());
        }
        businessObject.setArrListBusinessObj(arrayList);
        businessObject.setName(this.mContext.getResources().getString(R.string.tab_all));
        businessObject.setBusinessObjType(BusinessObjectType.ALL);
        return convertToAutoSuggest(businessObject, z, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00b4  */
    /* JADX WARNING: Missing block: B:18:0x00ab, code skipped:
            if (r1 != null) goto L_0x00bb;
     */
    /* JADX WARNING: Missing block: B:27:0x00b9, code skipped:
            if (r1 != null) goto L_0x00bb;
     */
    /* JADX WARNING: Missing block: B:29:0x00bb, code skipped:
            r1.close();
     */
    /* JADX WARNING: Missing block: B:30:0x00be, code skipped:
            r11.setArrListBusinessObj(r0);
            r11.setName(r9.mContext.getResources().getString(com.gaana.R.string.albums));
            r11.setBusinessObjType(com.managers.URLManager.BusinessObjectType.Albums);
     */
    /* JADX WARNING: Missing block: B:31:0x00da, code skipped:
            if (android.text.TextUtils.isEmpty(r10) == false) goto L_0x00de;
     */
    /* JADX WARNING: Missing block: B:32:0x00dc, code skipped:
            r9.mLocalAlbums = r11;
     */
    /* JADX WARNING: Missing block: B:33:0x00de, code skipped:
            return r11;
     */
    public com.gaana.models.BusinessObject getLocalAlbums(java.lang.String r10, boolean r11) {
        /*
        r9 = this;
        r0 = r9.mLocalAlbums;
        if (r0 == 0) goto L_0x000f;
    L_0x0004:
        r0 = android.text.TextUtils.isEmpty(r10);
        if (r0 == 0) goto L_0x000f;
    L_0x000a:
        if (r11 != 0) goto L_0x000f;
    L_0x000c:
        r10 = r9.mLocalAlbums;
        return r10;
    L_0x000f:
        r11 = new com.gaana.models.BusinessObject;
        r11.<init>();
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = android.text.TextUtils.isEmpty(r10);
        r2 = 0;
        if (r1 != 0) goto L_0x0038;
    L_0x0020:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r3 = "album LIKE '%";
        r1.append(r3);
        r1.append(r10);
        r3 = "%' ";
        r1.append(r3);
        r1 = r1.toString();
        r6 = r1;
        goto L_0x0039;
    L_0x0038:
        r6 = r2;
    L_0x0039:
        r3 = r9.mContentResolver;	 Catch:{ Exception -> 0x00b8, all -> 0x00b0 }
        r4 = android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x00b8, all -> 0x00b0 }
        r5 = 0;
        r7 = 0;
        r8 = "album ASC";
        r1 = r3.query(r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00b8, all -> 0x00b0 }
        r2 = r1.moveToFirst();	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        if (r2 == 0) goto L_0x00ab;
    L_0x004b:
        r2 = "_id";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r2 = r1.getString(r2);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r3 = "album";
        r3 = r1.getColumnIndex(r3);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r3 = r1.getString(r3);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r4 = "album_art";
        r4 = r1.getColumnIndex(r4);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r1.getString(r4);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r4 = "artist";
        r4 = r1.getColumnIndex(r4);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r4 = r1.getString(r4);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r5 = "minyear";
        r5 = r1.getColumnIndex(r5);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r1.getString(r5);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r5 = "numsongs";
        r5 = r1.getColumnIndex(r5);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r1.getString(r5);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r5 = new com.gaana.models.Albums$Album;	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r5.<init>();	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r5.setBusinessObjId(r2);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r5.setName(r3);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r5.setArtwork(r2);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r2 = r9.getAlbumArtist(r4);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r5.setPrimaryartist(r2);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r2 = 1;
        r5.setLocalMedia(r2);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r2 = com.managers.URLManager.BusinessObjectType.Albums;	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r5.setBusinessObjType(r2);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r0.add(r5);	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        r2 = r1.moveToNext();	 Catch:{ Exception -> 0x00b9, all -> 0x00ae }
        if (r2 != 0) goto L_0x004b;
    L_0x00ab:
        if (r1 == 0) goto L_0x00be;
    L_0x00ad:
        goto L_0x00bb;
    L_0x00ae:
        r10 = move-exception;
        goto L_0x00b2;
    L_0x00b0:
        r10 = move-exception;
        r1 = r2;
    L_0x00b2:
        if (r1 == 0) goto L_0x00b7;
    L_0x00b4:
        r1.close();
    L_0x00b7:
        throw r10;
    L_0x00b8:
        r1 = r2;
    L_0x00b9:
        if (r1 == 0) goto L_0x00be;
    L_0x00bb:
        r1.close();
    L_0x00be:
        r11.setArrListBusinessObj(r0);
        r0 = r9.mContext;
        r0 = r0.getResources();
        r1 = 2131820656; // 0x7f110070 float:1.9274033E38 double:1.053259349E-314;
        r0 = r0.getString(r1);
        r11.setName(r0);
        r0 = com.managers.URLManager.BusinessObjectType.Albums;
        r11.setBusinessObjType(r0);
        r10 = android.text.TextUtils.isEmpty(r10);
        if (r10 == 0) goto L_0x00de;
    L_0x00dc:
        r9.mLocalAlbums = r11;
    L_0x00de:
        return r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getLocalAlbums(java.lang.String, boolean):com.gaana.models.BusinessObject");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x008b A:{Splitter:B:6:0x0024, ExcHandler: all (th java.lang.Throwable)} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:15:0x0085, code skipped:
            if (r10 != null) goto L_0x0087;
     */
    /* JADX WARNING: Missing block: B:16:0x0087, code skipped:
            r10.close();
     */
    /* JADX WARNING: Missing block: B:17:0x008b, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:21:0x0091, code skipped:
            r10.close();
     */
    /* JADX WARNING: Missing block: B:25:0x0096, code skipped:
            if (r10 == null) goto L_0x0099;
     */
    /* JADX WARNING: Missing block: B:27:0x0099, code skipped:
            return r1;
     */
    private com.gaana.models.Albums.Album getAlbumById(java.lang.String r10) {
        /*
        r9 = this;
        r0 = android.text.TextUtils.isEmpty(r10);
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = "_id = ";
        r0.append(r2);
        r0.append(r10);
        r6 = r0.toString();
        r3 = r9.mContentResolver;	 Catch:{ Exception -> 0x0095, all -> 0x008d }
        r4 = android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x0095, all -> 0x008d }
        r5 = 0;
        r7 = 0;
        r8 = 0;
        r10 = r3.query(r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0095, all -> 0x008d }
        r0 = r10.moveToFirst();	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        if (r0 == 0) goto L_0x0085;
    L_0x002a:
        r0 = "_id";
        r0 = r10.getColumnIndex(r0);	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r0 = r10.getString(r0);	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r2 = "album";
        r2 = r10.getColumnIndex(r2);	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r2 = r10.getString(r2);	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r3 = "album_art";
        r3 = r10.getColumnIndex(r3);	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r10.getString(r3);	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r3 = "artist";
        r3 = r10.getColumnIndex(r3);	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r3 = r10.getString(r3);	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r4 = "minyear";
        r4 = r10.getColumnIndex(r4);	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r10.getString(r4);	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r4 = "numsongs";
        r4 = r10.getColumnIndex(r4);	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r10.getString(r4);	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r4 = new com.gaana.models.Albums$Album;	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r4.<init>();	 Catch:{ Exception -> 0x0096, all -> 0x008b }
        r4.setBusinessObjId(r0);	 Catch:{ Exception -> 0x0083, all -> 0x008b }
        r4.setName(r2);	 Catch:{ Exception -> 0x0083, all -> 0x008b }
        r4.setArtwork(r0);	 Catch:{ Exception -> 0x0083, all -> 0x008b }
        r0 = r9.getAlbumArtist(r3);	 Catch:{ Exception -> 0x0083, all -> 0x008b }
        r4.setPrimaryartist(r0);	 Catch:{ Exception -> 0x0083, all -> 0x008b }
        r0 = 1;
        r4.setLocalMedia(r0);	 Catch:{ Exception -> 0x0083, all -> 0x008b }
        r0 = com.managers.URLManager.BusinessObjectType.Albums;	 Catch:{ Exception -> 0x0083, all -> 0x008b }
        r4.setBusinessObjType(r0);	 Catch:{ Exception -> 0x0083, all -> 0x008b }
        r1 = r4;
        goto L_0x0085;
    L_0x0083:
        r1 = r4;
        goto L_0x0096;
    L_0x0085:
        if (r10 == 0) goto L_0x0099;
    L_0x0087:
        r10.close();
        goto L_0x0099;
    L_0x008b:
        r0 = move-exception;
        goto L_0x008f;
    L_0x008d:
        r0 = move-exception;
        r10 = r1;
    L_0x008f:
        if (r10 == 0) goto L_0x0094;
    L_0x0091:
        r10.close();
    L_0x0094:
        throw r0;
    L_0x0095:
        r10 = r1;
    L_0x0096:
        if (r10 == 0) goto L_0x0099;
    L_0x0098:
        goto L_0x0087;
    L_0x0099:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getAlbumById(java.lang.String):com.gaana.models.Albums$Album");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x00bb  */
    /* JADX WARNING: Missing block: B:12:0x00b1, code skipped:
            if (r1 != null) goto L_0x00c2;
     */
    /* JADX WARNING: Missing block: B:21:0x00c0, code skipped:
            if (r1 != null) goto L_0x00c2;
     */
    /* JADX WARNING: Missing block: B:23:0x00c2, code skipped:
            r1.close();
     */
    /* JADX WARNING: Missing block: B:24:0x00c5, code skipped:
            return r0;
     */
    public com.gaana.models.Artists.Artist getArtistById(java.lang.String r10) {
        /*
        r9 = this;
        r0 = new com.gaana.models.Artists$Artist;
        r0.<init>();
        r1 = 5;
        r4 = new java.lang.String[r1];
        r1 = "_id";
        r2 = 0;
        r4[r2] = r1;
        r1 = "artist";
        r8 = 1;
        r4[r8] = r1;
        r1 = "artist_key";
        r2 = 2;
        r4[r2] = r1;
        r1 = "number_of_albums";
        r2 = 3;
        r4[r2] = r1;
        r1 = "number_of_tracks";
        r2 = 4;
        r4[r2] = r1;
        r7 = "artist";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "_id='";
        r1.append(r2);
        r1.append(r10);
        r10 = "'";
        r1.append(r10);
        r5 = r1.toString();
        r10 = 0;
        r2 = r9.mContentResolver;	 Catch:{ Exception -> 0x00bf, all -> 0x00b6 }
        r3 = android.provider.MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x00bf, all -> 0x00b6 }
        r6 = 0;
        r1 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x00bf, all -> 0x00b6 }
        r10 = r1.moveToFirst();	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        if (r10 == 0) goto L_0x00b1;
    L_0x0049:
        r10 = "_id";
        r10 = r1.getColumnIndex(r10);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r10 = r1.getString(r10);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r2 = "artist";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r2 = r1.getString(r2);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r3 = "number_of_albums";
        r3 = r1.getColumnIndex(r3);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r3 = r1.getString(r3);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r4 = "number_of_tracks";
        r4 = r1.getColumnIndex(r4);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r4 = r1.getString(r4);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r5 = "artist_key";
        r5 = r1.getColumnIndex(r5);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r1.getString(r5);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r0.setBusinessObjId(r10);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r10 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r0.setSongsCount(r10);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r10 = java.lang.String.valueOf(r3);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r0.setAlbumsCount(r10);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r10 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        if (r10 != 0) goto L_0x0099;
    L_0x0091:
        r10 = "<unknown>";
        r10 = r2.equalsIgnoreCase(r10);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        if (r10 == 0) goto L_0x00a6;
    L_0x0099:
        r10 = r9.mContext;	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r10 = r10.getResources();	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r2 = 2131822799; // 0x7f1108cf float:1.927838E38 double:1.053260408E-314;
        r2 = r10.getString(r2);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
    L_0x00a6:
        r0.setName(r2);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r0.setLocalMedia(r8);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r10 = com.managers.URLManager.BusinessObjectType.Artists;	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
        r0.setBusinessObjType(r10);	 Catch:{ Exception -> 0x00c0, all -> 0x00b4 }
    L_0x00b1:
        if (r1 == 0) goto L_0x00c5;
    L_0x00b3:
        goto L_0x00c2;
    L_0x00b4:
        r10 = move-exception;
        goto L_0x00b9;
    L_0x00b6:
        r0 = move-exception;
        r1 = r10;
        r10 = r0;
    L_0x00b9:
        if (r1 == 0) goto L_0x00be;
    L_0x00bb:
        r1.close();
    L_0x00be:
        throw r10;
    L_0x00bf:
        r1 = r10;
    L_0x00c0:
        if (r1 == 0) goto L_0x00c5;
    L_0x00c2:
        r1.close();
    L_0x00c5:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getArtistById(java.lang.String):com.gaana.models.Artists$Artist");
    }

    private ArrayList<Artist> getAlbumArtist(String str) {
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            for (String str2 : str.split(",")) {
                Artist artist = new Artist();
                artist.setId(str2);
                artist.setName(str2);
            }
        }
        return arrayList;
    }

    private ArrayList<Track.Artist> getTrackArtist(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            Track.Artist artist = new Track.Artist();
            artist.artist_id = str2;
            if (TextUtils.isEmpty(str) || str.equalsIgnoreCase("<unknown>")) {
                str = this.mContext.getResources().getString(R.string.various_artists);
            }
            artist.name = str;
            arrayList.add(artist);
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x00fa  */
    /* JADX WARNING: Missing block: B:23:0x00f1, code skipped:
            if (r1 != null) goto L_0x0101;
     */
    /* JADX WARNING: Missing block: B:32:0x00ff, code skipped:
            if (r1 != null) goto L_0x0101;
     */
    /* JADX WARNING: Missing block: B:34:0x0101, code skipped:
            r1.close();
     */
    /* JADX WARNING: Missing block: B:35:0x0104, code skipped:
            r12.setArrListBusinessObj(r0);
     */
    /* JADX WARNING: Missing block: B:36:0x010b, code skipped:
            if (android.text.TextUtils.isEmpty(r11) == false) goto L_0x010f;
     */
    /* JADX WARNING: Missing block: B:37:0x010d, code skipped:
            r10.mLocalArtists = r12;
     */
    /* JADX WARNING: Missing block: B:38:0x010f, code skipped:
            return r12;
     */
    public com.gaana.models.BusinessObject getLocalArtists(java.lang.String r11, boolean r12) {
        /*
        r10 = this;
        r0 = r10.mLocalArtists;
        if (r0 == 0) goto L_0x000f;
    L_0x0004:
        r0 = android.text.TextUtils.isEmpty(r11);
        if (r0 == 0) goto L_0x000f;
    L_0x000a:
        if (r12 != 0) goto L_0x000f;
    L_0x000c:
        r11 = r10.mLocalArtists;
        return r11;
    L_0x000f:
        r12 = new com.gaana.models.BusinessObject;
        r12.<init>();
        r0 = r10.mContext;
        r0 = r0.getResources();
        r1 = 2131820688; // 0x7f110090 float:1.9274098E38 double:1.053259365E-314;
        r0 = r0.getString(r1);
        r12.setName(r0);
        r0 = com.managers.URLManager.BusinessObjectType.Artists;
        r12.setBusinessObjType(r0);
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = 5;
        r4 = new java.lang.String[r1];
        r1 = 0;
        r2 = "_id";
        r4[r1] = r2;
        r1 = "artist";
        r8 = 1;
        r4[r8] = r1;
        r1 = 2;
        r2 = "artist_key";
        r4[r1] = r2;
        r1 = 3;
        r2 = "number_of_albums";
        r4[r1] = r2;
        r1 = 4;
        r2 = "number_of_tracks";
        r4[r1] = r2;
        r7 = "artist";
        r1 = android.text.TextUtils.isEmpty(r11);
        r9 = 0;
        if (r1 != 0) goto L_0x006b;
    L_0x0053:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "artist LIKE '%";
        r1.append(r2);
        r1.append(r11);
        r2 = "%' ";
        r1.append(r2);
        r1 = r1.toString();
        r5 = r1;
        goto L_0x006c;
    L_0x006b:
        r5 = r9;
    L_0x006c:
        r2 = r10.mContentResolver;	 Catch:{ Exception -> 0x00fe, all -> 0x00f6 }
        r3 = android.provider.MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x00fe, all -> 0x00f6 }
        r6 = 0;
        r1 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x00fe, all -> 0x00f6 }
        r2 = r1.moveToFirst();	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        if (r2 == 0) goto L_0x00f1;
    L_0x007b:
        r2 = "_id";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r2 = r1.getString(r2);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r3 = "artist";
        r3 = r1.getColumnIndex(r3);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r3 = r1.getString(r3);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r4 = "number_of_albums";
        r4 = r1.getColumnIndex(r4);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r4 = r1.getString(r4);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r5 = "number_of_tracks";
        r5 = r1.getColumnIndex(r5);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r5 = r1.getString(r5);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r6 = "artist_key";
        r6 = r1.getColumnIndex(r6);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r1.getString(r6);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r6 = new com.gaana.models.Artists$Artist;	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r6.<init>();	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r6.setBusinessObjId(r2);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r2 = java.lang.String.valueOf(r5);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r6.setSongsCount(r2);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r2 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r6.setAlbumsCount(r2);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r2 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        if (r2 != 0) goto L_0x00d0;
    L_0x00c8:
        r2 = "<unknown>";
        r2 = r3.equalsIgnoreCase(r2);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        if (r2 == 0) goto L_0x00dd;
    L_0x00d0:
        r2 = r10.mContext;	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r2 = r2.getResources();	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r3 = 2131822799; // 0x7f1108cf float:1.927838E38 double:1.053260408E-314;
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
    L_0x00dd:
        r6.setName(r3);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r6.setLocalMedia(r8);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r2 = com.managers.URLManager.BusinessObjectType.Artists;	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r6.setBusinessObjType(r2);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r0.add(r6);	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        r2 = r1.moveToNext();	 Catch:{ Exception -> 0x00ff, all -> 0x00f4 }
        if (r2 != 0) goto L_0x007b;
    L_0x00f1:
        if (r1 == 0) goto L_0x0104;
    L_0x00f3:
        goto L_0x0101;
    L_0x00f4:
        r11 = move-exception;
        goto L_0x00f8;
    L_0x00f6:
        r11 = move-exception;
        r1 = r9;
    L_0x00f8:
        if (r1 == 0) goto L_0x00fd;
    L_0x00fa:
        r1.close();
    L_0x00fd:
        throw r11;
    L_0x00fe:
        r1 = r9;
    L_0x00ff:
        if (r1 == 0) goto L_0x0104;
    L_0x0101:
        r1.close();
    L_0x0104:
        r12.setArrListBusinessObj(r0);
        r11 = android.text.TextUtils.isEmpty(r11);
        if (r11 == 0) goto L_0x010f;
    L_0x010d:
        r10.mLocalArtists = r12;
    L_0x010f:
        return r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getLocalArtists(java.lang.String, boolean):com.gaana.models.BusinessObject");
    }

    public BusinessObject getLocalandDownloadedPlaylists(String str, boolean z, boolean z2) {
        BusinessObject businessObject = new BusinessObject();
        ArrayList arrayList = new ArrayList();
        if (z) {
            BusinessObject businessObject2;
            Iterator it = DownloadManager.c().a(str, c.b).iterator();
            while (it.hasNext()) {
                businessObject2 = (BusinessObject) it.next();
                if (!(businessObject2 == null || !(businessObject2 instanceof Playlist) || PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) businessObject2))) {
                    arrayList.add(businessObject2);
                }
            }
            it = f.a().c().iterator();
            while (it.hasNext()) {
                businessObject2 = (BusinessObject) it.next();
                if (!(businessObject2 == null || businessObject2.getRawName() == null || !businessObject2.getRawName().toUpperCase().contains(str.trim().toUpperCase()))) {
                    arrayList.add(businessObject2);
                }
            }
        } else {
            arrayList.addAll(getMediaStorePlaylists(null, str));
        }
        businessObject.setBusinessObjType(BusinessObjectType.Playlists);
        businessObject.setArrListBusinessObj(arrayList);
        if (TextUtils.isEmpty(str)) {
            this.mLocalPlaylists = businessObject;
        }
        setPlaylistFlagIsDirty = false;
        return z2 ? convertToAutoSuggest(businessObject, z, false) : businessObject;
    }

    public BusinessObject getLocalPlaylists(URLManager uRLManager) {
        String q = uRLManager.q();
        if (this.mLocalPlaylists != null && TextUtils.isEmpty(q) && !uRLManager.m().booleanValue() && !setPlaylistFlagIsDirty) {
            return this.mLocalPlaylists;
        }
        BusinessObject businessObject = new BusinessObject();
        ArrayList mediaStorePlaylists = getMediaStorePlaylists(null, q);
        businessObject.setBusinessObjType(BusinessObjectType.Playlists);
        businessObject.setArrListBusinessObj(mediaStorePlaylists);
        if (TextUtils.isEmpty(q)) {
            this.mLocalPlaylists = businessObject;
        }
        setPlaylistFlagIsDirty = false;
        return businessObject;
    }

    public BusinessObject getMyPlaylists(URLManager uRLManager) {
        BusinessObject businessObject = new BusinessObject();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (uRLManager.m().booleanValue()) {
            arrayList.addAll(PlaylistSyncManager.getInstance().retrieveMyPlaylists(arrayList2, false));
        } else {
            ArrayList myPlaylistsFromDb = PlaylistSyncManager.getInstance().getMyPlaylistsFromDb(true);
            if (myPlaylistsFromDb.size() > 0) {
                for (int size = myPlaylistsFromDb.size() - 1; size >= 0; size--) {
                    String localPlaylistId = ((Playlist) myPlaylistsFromDb.get(size)).getLocalPlaylistId();
                    if (!TextUtils.isEmpty(localPlaylistId)) {
                        arrayList2.add(localPlaylistId);
                    }
                }
            }
            myPlaylistsFromDb.addAll(getMediaStorePlaylists(arrayList2, uRLManager.q()));
            arrayList.addAll(myPlaylistsFromDb);
        }
        businessObject.setArrListBusinessObj(arrayList);
        return businessObject;
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0143  */
    /* JADX WARNING: Missing block: B:39:0x0138, code skipped:
            if (r12 != null) goto L_0x014a;
     */
    /* JADX WARNING: Missing block: B:48:0x0148, code skipped:
            if (r12 != null) goto L_0x014a;
     */
    /* JADX WARNING: Missing block: B:50:0x014a, code skipped:
            r12.close();
     */
    public java.util.ArrayList<com.gaana.models.BusinessObject> getMediaStorePlaylists(java.util.ArrayList<java.lang.String> r11, java.lang.String r12) {
        /*
        r10 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = com.constants.Constants.Q;
        if (r1 == 0) goto L_0x014d;
    L_0x0009:
        r1 = "";
        if (r11 == 0) goto L_0x0056;
    L_0x000d:
        r2 = r11.size();
        if (r2 <= 0) goto L_0x0056;
    L_0x0013:
        r11 = r11.iterator();
    L_0x0017:
        r2 = r11.hasNext();
        if (r2 == 0) goto L_0x0038;
    L_0x001d:
        r2 = r11.next();
        r2 = (java.lang.String) r2;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r1);
        r1 = ",";
        r3.append(r1);
        r3.append(r2);
        r1 = r3.toString();
        goto L_0x0017;
    L_0x0038:
        r11 = ",";
        r2 = "";
        r11 = r1.replaceFirst(r11, r2);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "_id NOT IN (";
        r1.append(r2);
        r1.append(r11);
        r11 = ")";
        r1.append(r11);
        r1 = r1.toString();
    L_0x0056:
        r11 = 0;
        r2 = "name";
        r3 = "";
        r4 = android.text.TextUtils.isEmpty(r12);
        if (r4 != 0) goto L_0x007b;
    L_0x0061:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r4 = " LIKE '%";
        r3.append(r4);
        r3.append(r12);
        r12 = "%' ";
        r3.append(r12);
        r12 = r3.toString();
        goto L_0x007c;
    L_0x007b:
        r12 = r3;
    L_0x007c:
        r3 = android.text.TextUtils.isEmpty(r1);
        if (r3 != 0) goto L_0x009e;
    L_0x0082:
        r3 = android.text.TextUtils.isEmpty(r12);
        if (r3 != 0) goto L_0x009c;
    L_0x0088:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r12);
        r12 = " AND ";
        r3.append(r12);
        r3.append(r1);
        r1 = r3.toString();
    L_0x009c:
        r6 = r1;
        goto L_0x009f;
    L_0x009e:
        r6 = r12;
    L_0x009f:
        r3 = r10.mContentResolver;	 Catch:{ Exception -> 0x0147, all -> 0x013d }
        r4 = android.provider.MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x0147, all -> 0x013d }
        r5 = 0;
        r7 = 0;
        r12 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0147, all -> 0x013d }
        r12.<init>();	 Catch:{ Exception -> 0x0147, all -> 0x013d }
        r12.append(r2);	 Catch:{ Exception -> 0x0147, all -> 0x013d }
        r1 = " ASC";
        r12.append(r1);	 Catch:{ Exception -> 0x0147, all -> 0x013d }
        r8 = r12.toString();	 Catch:{ Exception -> 0x0147, all -> 0x013d }
        r12 = r3.query(r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x0147, all -> 0x013d }
        r11 = r12.moveToFirst();	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        if (r11 == 0) goto L_0x0138;
    L_0x00c0:
        r11 = "_id";
        r11 = r12.getColumnIndex(r11);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r11 = r12.getString(r11);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r1 = "name";
        r1 = r12.getColumnIndex(r1);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r1 = r12.getString(r1);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r2 = "date_added";
        r2 = r12.getColumnIndex(r2);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r12.getString(r2);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r2 = "date_modified";
        r2 = r12.getColumnIndex(r2);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r2 = r12.getString(r2);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r3 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        if (r3 != 0) goto L_0x0132;
    L_0x00ed:
        r3 = new com.gaana.models.Playlists$Playlist;	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r3.<init>();	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r3.setBusinessObjId(r11);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r3.setPlaylistId(r11);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r3.setName(r1);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r1.<init>();	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r4 = "PLAYLIST_";
        r1.append(r4);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r1.append(r11);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r11 = r1.toString();	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r3.setArtwork(r11);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        if (r2 == 0) goto L_0x0126;
    L_0x0111:
        r11 = new java.text.SimpleDateFormat;	 Catch:{ ParseException -> 0x0122 }
        r1 = "yyyy-MM-dd HH:mm:ss";
        r4 = java.util.Locale.ENGLISH;	 Catch:{ ParseException -> 0x0122 }
        r11.<init>(r1, r4);	 Catch:{ ParseException -> 0x0122 }
        r11 = r11.parse(r2);	 Catch:{ ParseException -> 0x0122 }
        r3.setLastModifiedDate(r11);	 Catch:{ ParseException -> 0x0122 }
        goto L_0x0126;
    L_0x0122:
        r11 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r11);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
    L_0x0126:
        r11 = 1;
        r3.setLocalMedia(r11);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r11 = com.managers.URLManager.BusinessObjectType.Playlists;	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r3.setBusinessObjType(r11);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        r0.add(r3);	 Catch:{ Exception -> 0x0148, all -> 0x013b }
    L_0x0132:
        r11 = r12.moveToNext();	 Catch:{ Exception -> 0x0148, all -> 0x013b }
        if (r11 != 0) goto L_0x00c0;
    L_0x0138:
        if (r12 == 0) goto L_0x014d;
    L_0x013a:
        goto L_0x014a;
    L_0x013b:
        r11 = move-exception;
        goto L_0x0141;
    L_0x013d:
        r12 = move-exception;
        r9 = r12;
        r12 = r11;
        r11 = r9;
    L_0x0141:
        if (r12 == 0) goto L_0x0146;
    L_0x0143:
        r12.close();
    L_0x0146:
        throw r11;
    L_0x0147:
        r12 = r11;
    L_0x0148:
        if (r12 == 0) goto L_0x014d;
    L_0x014a:
        r12.close();
    L_0x014d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getMediaStorePlaylists(java.util.ArrayList, java.lang.String):java.util.ArrayList");
    }

    /* JADX WARNING: Missing block: B:21:0x008e, code skipped:
            if (r5.getAlbumRawName().toUpperCase().contains(r11.trim().toUpperCase()) != false) goto L_0x00ac;
     */
    /* JADX WARNING: Missing block: B:46:0x0116, code skipped:
            if (r5.getAlbumRawName().toUpperCase().contains(r11.trim().toUpperCase()) != false) goto L_0x0134;
     */
    public com.gaana.models.BusinessObject getLocalandDownloadedSongs(java.lang.String r11, boolean r12, boolean r13) {
        /*
        r10 = this;
        r0 = new com.gaana.models.BusinessObject;
        r0.<init>();
        r1 = r10.mContext;
        r2 = 2131822478; // 0x7f11078e float:1.9277729E38 double:1.0532602494E-314;
        r1 = r1.getString(r2);
        r0.setName(r1);
        r1 = com.managers.URLManager.BusinessObjectType.Tracks;
        r0.setBusinessObjType(r1);
        r1 = new java.util.ArrayList;
        r1.<init>();
        r2 = 0;
        r3 = 0;
        if (r12 == 0) goto L_0x00b0;
    L_0x001f:
        r4 = com.managers.DownloadManager.c();
        r5 = 0;
        r6 = 1;
        r7 = 0;
        r8 = -1;
        r9 = -1;
        r4 = r4.a(r5, r6, r7, r8, r9);
        if (r4 == 0) goto L_0x0032;
    L_0x002e:
        r3 = r4.getArrListBusinessObj();
    L_0x0032:
        r4 = android.text.TextUtils.isEmpty(r11);
        if (r4 == 0) goto L_0x003d;
    L_0x0038:
        r1.addAll(r3);
        goto L_0x0138;
    L_0x003d:
        if (r3 == 0) goto L_0x0138;
    L_0x003f:
        r4 = r3.size();
        if (r4 <= 0) goto L_0x0138;
    L_0x0045:
        r3 = r3.iterator();
    L_0x0049:
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x0138;
    L_0x004f:
        r4 = r3.next();
        r4 = (com.gaana.models.BusinessObject) r4;
        r5 = r4.getRawName();
        if (r5 == 0) goto L_0x0071;
    L_0x005b:
        r5 = r4.getRawName();
        r5 = r5.toUpperCase();
        r6 = r11.trim();
        r6 = r6.toUpperCase();
        r5 = r5.contains(r6);
        if (r5 != 0) goto L_0x00ac;
    L_0x0071:
        r5 = r4;
        r5 = (com.gaana.models.OfflineTrack) r5;
        r6 = r5.getAlbumRawName();
        if (r6 == 0) goto L_0x0090;
    L_0x007a:
        r6 = r5.getAlbumRawName();
        r6 = r6.toUpperCase();
        r7 = r11.trim();
        r7 = r7.toUpperCase();
        r6 = r6.contains(r7);
        if (r6 != 0) goto L_0x00ac;
    L_0x0090:
        r6 = r5.getArtistRawName();
        if (r6 == 0) goto L_0x0049;
    L_0x0096:
        r5 = r5.getArtistRawName();
        r5 = r5.toUpperCase();
        r6 = r11.trim();
        r6 = r6.toUpperCase();
        r5 = r5.contains(r6);
        if (r5 == 0) goto L_0x0049;
    L_0x00ac:
        r1.add(r4);
        goto L_0x0049;
    L_0x00b0:
        r4 = r10.getLocalSongs(r3, r2);
        if (r4 == 0) goto L_0x00ba;
    L_0x00b6:
        r3 = r4.getArrListBusinessObj();
    L_0x00ba:
        r4 = android.text.TextUtils.isEmpty(r11);
        if (r4 == 0) goto L_0x00c5;
    L_0x00c0:
        r1.addAll(r3);
        goto L_0x0138;
    L_0x00c5:
        if (r3 == 0) goto L_0x0138;
    L_0x00c7:
        r4 = r3.size();
        if (r4 <= 0) goto L_0x0138;
    L_0x00cd:
        r3 = r3.iterator();
    L_0x00d1:
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x0138;
    L_0x00d7:
        r4 = r3.next();
        r4 = (com.gaana.models.BusinessObject) r4;
        r5 = r4.getRawName();
        if (r5 == 0) goto L_0x00f9;
    L_0x00e3:
        r5 = r4.getRawName();
        r5 = r5.toUpperCase();
        r6 = r11.trim();
        r6 = r6.toUpperCase();
        r5 = r5.contains(r6);
        if (r5 != 0) goto L_0x0134;
    L_0x00f9:
        r5 = r4;
        r5 = (com.gaana.models.OfflineTrack) r5;
        r6 = r5.getAlbumRawName();
        if (r6 == 0) goto L_0x0118;
    L_0x0102:
        r6 = r5.getAlbumRawName();
        r6 = r6.toUpperCase();
        r7 = r11.trim();
        r7 = r7.toUpperCase();
        r6 = r6.contains(r7);
        if (r6 != 0) goto L_0x0134;
    L_0x0118:
        r6 = r5.getArtistRawName();
        if (r6 == 0) goto L_0x00d1;
    L_0x011e:
        r5 = r5.getArtistRawName();
        r5 = r5.toUpperCase();
        r6 = r11.trim();
        r6 = r6.toUpperCase();
        r5 = r5.contains(r6);
        if (r5 == 0) goto L_0x00d1;
    L_0x0134:
        r1.add(r4);
        goto L_0x00d1;
    L_0x0138:
        r0.setArrListBusinessObj(r1);
        r11 = android.text.TextUtils.isEmpty(r11);
        if (r11 == 0) goto L_0x0143;
    L_0x0141:
        r10.mLocalTracks = r0;
    L_0x0143:
        setSongFlagIsDirty = r2;
        if (r13 == 0) goto L_0x014c;
    L_0x0147:
        r11 = r10.convertToAutoSuggest(r0, r12, r2);
        return r11;
    L_0x014c:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getLocalandDownloadedSongs(java.lang.String, boolean, boolean):com.gaana.models.BusinessObject");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0156  */
    /* JADX WARNING: Missing block: B:26:0x014d, code skipped:
            if (r2 != null) goto L_0x015d;
     */
    /* JADX WARNING: Missing block: B:35:0x015b, code skipped:
            if (r2 != null) goto L_0x015d;
     */
    /* JADX WARNING: Missing block: B:37:0x015d, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:38:0x0160, code skipped:
            r15.setArrListBusinessObj(r0);
     */
    /* JADX WARNING: Missing block: B:39:0x0167, code skipped:
            if (android.text.TextUtils.isEmpty(r14) == false) goto L_0x016b;
     */
    /* JADX WARNING: Missing block: B:40:0x0169, code skipped:
            r13.mLocalTracks = r15;
     */
    /* JADX WARNING: Missing block: B:41:0x016b, code skipped:
            setSongFlagIsDirty = false;
     */
    /* JADX WARNING: Missing block: B:42:0x016d, code skipped:
            return r15;
     */
    public com.gaana.models.BusinessObject getLocalSongs(java.lang.String r14, boolean r15) {
        /*
        r13 = this;
        r0 = r13.mLocalTracks;
        if (r0 == 0) goto L_0x0013;
    L_0x0004:
        r0 = android.text.TextUtils.isEmpty(r14);
        if (r0 == 0) goto L_0x0013;
    L_0x000a:
        r0 = setSongFlagIsDirty;
        if (r0 != 0) goto L_0x0013;
    L_0x000e:
        if (r15 != 0) goto L_0x0013;
    L_0x0010:
        r14 = r13.mLocalTracks;
        return r14;
    L_0x0013:
        r15 = new com.gaana.models.BusinessObject;
        r15.<init>();
        r0 = r13.mContext;
        r0 = r0.getResources();
        r1 = 2131822478; // 0x7f11078e float:1.9277729E38 double:1.0532602494E-314;
        r0 = r0.getString(r1);
        r15.setName(r0);
        r0 = com.managers.URLManager.BusinessObjectType.Tracks;
        r15.setBusinessObjType(r0);
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = 10;
        r4 = new java.lang.String[r1];
        r1 = "_data";
        r8 = 0;
        r4[r8] = r1;
        r1 = "_id";
        r9 = 1;
        r4[r9] = r1;
        r1 = 2;
        r2 = "title";
        r4[r1] = r2;
        r1 = 3;
        r2 = "_display_name";
        r4[r1] = r2;
        r1 = 4;
        r2 = "artist";
        r4[r1] = r2;
        r1 = 5;
        r2 = "artist_id";
        r4[r1] = r2;
        r1 = 6;
        r2 = "album";
        r4[r1] = r2;
        r1 = 7;
        r2 = "album_id";
        r4[r1] = r2;
        r1 = 8;
        r2 = "mime_type";
        r4[r1] = r2;
        r1 = 9;
        r2 = "duration";
        r4[r1] = r2;
        r7 = "title";
        r1 = "mime_type NOT NULL AND is_music != 0";
        r2 = android.text.TextUtils.isEmpty(r14);
        if (r2 != 0) goto L_0x0095;
    L_0x0074:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r1);
        r1 = " AND ";
        r2.append(r1);
        r2.append(r7);
        r1 = " LIKE '%";
        r2.append(r1);
        r2.append(r14);
        r1 = "%' ";
        r2.append(r1);
        r1 = r2.toString();
    L_0x0095:
        r5 = r1;
        r1 = 0;
        r2 = r13.mContentResolver;	 Catch:{ Exception -> 0x015a, all -> 0x0152 }
        r3 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x015a, all -> 0x0152 }
        r6 = 0;
        r2 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x015a, all -> 0x0152 }
        r1 = r2.moveToFirst();	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        if (r1 == 0) goto L_0x014d;
    L_0x00a6:
        r1 = r8;
    L_0x00a7:
        r3 = "_id";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r4 = "title";
        r4 = r2.getColumnIndex(r4);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r4 = r2.getString(r4);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r5 = "_display_name";
        r5 = r2.getColumnIndex(r5);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r2.getString(r5);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r5 = "mime_type";
        r5 = r2.getColumnIndex(r5);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r2.getString(r5);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r5 = "artist";
        r5 = r2.getColumnIndex(r5);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r5 = r2.getString(r5);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r6 = "artist_id";
        r6 = r2.getColumnIndex(r6);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r6 = r2.getString(r6);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r7 = "album";
        r7 = r2.getColumnIndex(r7);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r7 = r2.getString(r7);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r10 = "album_id";
        r10 = r2.getColumnIndex(r10);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r10 = r2.getString(r10);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r11 = "_data";
        r11 = r2.getColumnIndex(r11);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r2.getString(r11);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r11 = "duration";
        r11 = r2.getColumnIndex(r11);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r11 = r2.getString(r11);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r12 = new com.gaana.models.OfflineTrack;	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r12.<init>(r3, r4, r5);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r12.setLocalMedia(r9);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r12.setAlbumName(r7);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r12.setAlbumId(r10);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r12.setArtistId(r6);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r3 = com.managers.URLManager.BusinessObjectType.Tracks;	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r12.setBusinessObjType(r3);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r12.setDuration(r11);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r3 = android.text.TextUtils.isEmpty(r5);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        if (r3 != 0) goto L_0x012f;
    L_0x0127:
        r3 = "<unknown>";
        r3 = r5.equalsIgnoreCase(r3);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        if (r3 == 0) goto L_0x013c;
    L_0x012f:
        r3 = r13.mContext;	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r3 = r3.getResources();	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r4 = 2131822799; // 0x7f1108cf float:1.927838E38 double:1.053260408E-314;
        r5 = r3.getString(r4);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
    L_0x013c:
        r12.setArtistName(r5);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r12.setPosition(r1);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r0.add(r12);	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        r1 = r1 + 1;
        r3 = r2.moveToNext();	 Catch:{ Exception -> 0x015b, all -> 0x0150 }
        if (r3 != 0) goto L_0x00a7;
    L_0x014d:
        if (r2 == 0) goto L_0x0160;
    L_0x014f:
        goto L_0x015d;
    L_0x0150:
        r14 = move-exception;
        goto L_0x0154;
    L_0x0152:
        r14 = move-exception;
        r2 = r1;
    L_0x0154:
        if (r2 == 0) goto L_0x0159;
    L_0x0156:
        r2.close();
    L_0x0159:
        throw r14;
    L_0x015a:
        r2 = r1;
    L_0x015b:
        if (r2 == 0) goto L_0x0160;
    L_0x015d:
        r2.close();
    L_0x0160:
        r15.setArrListBusinessObj(r0);
        r14 = android.text.TextUtils.isEmpty(r14);
        if (r14 == 0) goto L_0x016b;
    L_0x0169:
        r13.mLocalTracks = r15;
    L_0x016b:
        setSongFlagIsDirty = r8;
        return r15;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getLocalSongs(java.lang.String, boolean):com.gaana.models.BusinessObject");
    }

    public BusinessObject getLocalItemById(BusinessObjectType businessObjectType, String str) {
        switch (businessObjectType) {
            case Tracks:
                return getSongByid(str);
            case Albums:
                return getAlbumById(str);
            case Artists:
                return getArtistById(str);
            case Playlists:
                return getPlaylistbyId(str);
            default:
                return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00f6 A:{Splitter:B:6:0x005b, ExcHandler: all (th java.lang.Throwable)} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:15:0x00f0, code skipped:
            if (r12 != null) goto L_0x00f2;
     */
    /* JADX WARNING: Missing block: B:16:0x00f2, code skipped:
            r12.close();
     */
    /* JADX WARNING: Missing block: B:17:0x00f6, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:21:0x00fc, code skipped:
            r12.close();
     */
    /* JADX WARNING: Missing block: B:25:0x0101, code skipped:
            if (r12 == null) goto L_0x0104;
     */
    /* JADX WARNING: Missing block: B:27:0x0104, code skipped:
            return r1;
     */
    public com.gaana.models.Tracks.Track getSongByid(java.lang.String r12) {
        /*
        r11 = this;
        r0 = android.text.TextUtils.isEmpty(r12);
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = 10;
        r4 = new java.lang.String[r0];
        r0 = 0;
        r2 = "_data";
        r4[r0] = r2;
        r0 = "_id";
        r8 = 1;
        r4[r8] = r0;
        r0 = 2;
        r2 = "title";
        r4[r0] = r2;
        r0 = 3;
        r2 = "_display_name";
        r4[r0] = r2;
        r0 = 4;
        r2 = "artist";
        r4[r0] = r2;
        r0 = 5;
        r2 = "artist_id";
        r4[r0] = r2;
        r0 = 6;
        r2 = "album";
        r4[r0] = r2;
        r0 = 7;
        r2 = "album_id";
        r4[r0] = r2;
        r0 = 8;
        r2 = "mime_type";
        r4[r0] = r2;
        r0 = 9;
        r2 = "duration";
        r4[r0] = r2;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = "mime_type NOT NULL AND _id = ";
        r0.append(r2);
        r0.append(r12);
        r5 = r0.toString();
        r2 = r11.mContentResolver;	 Catch:{ Exception -> 0x0100, all -> 0x00f8 }
        r3 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x0100, all -> 0x00f8 }
        r6 = 0;
        r7 = 0;
        r12 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0100, all -> 0x00f8 }
        r0 = r12.moveToFirst();	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        if (r0 == 0) goto L_0x00f0;
    L_0x0061:
        r0 = "_id";
        r0 = r12.getColumnIndex(r0);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r0 = r12.getString(r0);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r2 = "title";
        r2 = r12.getColumnIndex(r2);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r2 = r12.getString(r2);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r3 = "_display_name";
        r3 = r12.getColumnIndex(r3);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r3 = r12.getString(r3);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r4 = "mime_type";
        r4 = r12.getColumnIndex(r4);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r12.getString(r4);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r4 = "artist";
        r4 = r12.getColumnIndex(r4);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r4 = r12.getString(r4);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r5 = "artist_id";
        r5 = r12.getColumnIndex(r5);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r5 = r12.getString(r5);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r6 = "album";
        r6 = r12.getColumnIndex(r6);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r6 = r12.getString(r6);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r7 = "album_id";
        r7 = r12.getColumnIndex(r7);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r7 = r12.getString(r7);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r9 = "_data";
        r9 = r12.getColumnIndex(r9);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r12.getString(r9);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r9 = "duration";
        r9 = r12.getColumnIndex(r9);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r9 = r12.getString(r9);	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r10 = new com.gaana.models.LocalTrack;	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r10.<init>();	 Catch:{ Exception -> 0x0101, all -> 0x00f6 }
        r10.setBusinessObjId(r0);	 Catch:{ Exception -> 0x00ee, all -> 0x00f6 }
        r10.setName(r3);	 Catch:{ Exception -> 0x00ee, all -> 0x00f6 }
        r10.setTracktitle(r2);	 Catch:{ Exception -> 0x00ee, all -> 0x00f6 }
        r10.setLocalMedia(r8);	 Catch:{ Exception -> 0x00ee, all -> 0x00f6 }
        r10.setAlbumName(r6);	 Catch:{ Exception -> 0x00ee, all -> 0x00f6 }
        r10.setAlbumId(r7);	 Catch:{ Exception -> 0x00ee, all -> 0x00f6 }
        r0 = r11.getTrackArtist(r4, r5);	 Catch:{ Exception -> 0x00ee, all -> 0x00f6 }
        r10.setArtist(r0);	 Catch:{ Exception -> 0x00ee, all -> 0x00f6 }
        r10.setArtwork(r7);	 Catch:{ Exception -> 0x00ee, all -> 0x00f6 }
        r0 = com.managers.URLManager.BusinessObjectType.Tracks;	 Catch:{ Exception -> 0x00ee, all -> 0x00f6 }
        r10.setBusinessObjType(r0);	 Catch:{ Exception -> 0x00ee, all -> 0x00f6 }
        r10.setDuration(r9);	 Catch:{ Exception -> 0x00ee, all -> 0x00f6 }
        r1 = r10;
        goto L_0x00f0;
    L_0x00ee:
        r1 = r10;
        goto L_0x0101;
    L_0x00f0:
        if (r12 == 0) goto L_0x0104;
    L_0x00f2:
        r12.close();
        goto L_0x0104;
    L_0x00f6:
        r0 = move-exception;
        goto L_0x00fa;
    L_0x00f8:
        r0 = move-exception;
        r12 = r1;
    L_0x00fa:
        if (r12 == 0) goto L_0x00ff;
    L_0x00fc:
        r12.close();
    L_0x00ff:
        throw r0;
    L_0x0100:
        r12 = r1;
    L_0x0101:
        if (r12 == 0) goto L_0x0104;
    L_0x0103:
        goto L_0x00f2;
    L_0x0104:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getSongByid(java.lang.String):com.gaana.models.Tracks$Track");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00e7 A:{Splitter:B:6:0x004f, ExcHandler: all (th java.lang.Throwable)} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:15:0x00e1, code skipped:
            if (r12 != null) goto L_0x00e3;
     */
    /* JADX WARNING: Missing block: B:16:0x00e3, code skipped:
            r12.close();
     */
    /* JADX WARNING: Missing block: B:17:0x00e7, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:21:0x00ed, code skipped:
            r12.close();
     */
    /* JADX WARNING: Missing block: B:25:0x00f2, code skipped:
            if (r12 == null) goto L_0x00f5;
     */
    /* JADX WARNING: Missing block: B:27:0x00f5, code skipped:
            return r1;
     */
    public com.gaana.models.Tracks.Track getSongByTitle(java.lang.String r12) {
        /*
        r11 = this;
        r0 = android.text.TextUtils.isEmpty(r12);
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = 10;
        r4 = new java.lang.String[r0];
        r0 = "_data";
        r2 = 0;
        r4[r2] = r0;
        r0 = "_id";
        r8 = 1;
        r4[r8] = r0;
        r0 = 2;
        r3 = "title";
        r4[r0] = r3;
        r0 = 3;
        r3 = "_display_name";
        r4[r0] = r3;
        r0 = 4;
        r3 = "artist";
        r4[r0] = r3;
        r0 = 5;
        r3 = "artist_id";
        r4[r0] = r3;
        r0 = 6;
        r3 = "album";
        r4[r0] = r3;
        r0 = 7;
        r3 = "album_id";
        r4[r0] = r3;
        r0 = 8;
        r3 = "mime_type";
        r4[r0] = r3;
        r0 = 9;
        r3 = "duration";
        r4[r0] = r3;
        r5 = "mime_type NOT NULL AND _display_name =?";
        r6 = new java.lang.String[r8];
        r6[r2] = r12;
        r2 = r11.mContentResolver;	 Catch:{ Exception -> 0x00f1, all -> 0x00e9 }
        r3 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x00f1, all -> 0x00e9 }
        r7 = 0;
        r12 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x00f1, all -> 0x00e9 }
        r0 = r12.moveToFirst();	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        if (r0 == 0) goto L_0x00e1;
    L_0x0055:
        r0 = "_id";
        r0 = r12.getColumnIndex(r0);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r0 = r12.getString(r0);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r2 = "title";
        r2 = r12.getColumnIndex(r2);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r2 = r12.getString(r2);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r3 = "_display_name";
        r3 = r12.getColumnIndex(r3);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r3 = r12.getString(r3);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r4 = "mime_type";
        r4 = r12.getColumnIndex(r4);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r12.getString(r4);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r4 = "artist";
        r4 = r12.getColumnIndex(r4);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r4 = r12.getString(r4);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r5 = "artist_id";
        r5 = r12.getColumnIndex(r5);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r5 = r12.getString(r5);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r6 = "album";
        r6 = r12.getColumnIndex(r6);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r6 = r12.getString(r6);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r7 = "album_id";
        r7 = r12.getColumnIndex(r7);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r7 = r12.getString(r7);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r9 = "_data";
        r9 = r12.getColumnIndex(r9);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r12.getString(r9);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r9 = "duration";
        r9 = r12.getColumnIndex(r9);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r9 = r12.getString(r9);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r10 = new com.gaana.models.LocalTrack;	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r10.<init>();	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r10.setBusinessObjId(r0);	 Catch:{ Exception -> 0x00df, all -> 0x00e7 }
        r10.setName(r3);	 Catch:{ Exception -> 0x00df, all -> 0x00e7 }
        r10.setTracktitle(r2);	 Catch:{ Exception -> 0x00df, all -> 0x00e7 }
        r10.setLocalMedia(r8);	 Catch:{ Exception -> 0x00df, all -> 0x00e7 }
        r10.setAlbumName(r6);	 Catch:{ Exception -> 0x00df, all -> 0x00e7 }
        r10.setAlbumId(r7);	 Catch:{ Exception -> 0x00df, all -> 0x00e7 }
        r0 = r11.getTrackArtist(r4, r5);	 Catch:{ Exception -> 0x00df, all -> 0x00e7 }
        r10.setArtist(r0);	 Catch:{ Exception -> 0x00df, all -> 0x00e7 }
        r0 = com.managers.URLManager.BusinessObjectType.Tracks;	 Catch:{ Exception -> 0x00df, all -> 0x00e7 }
        r10.setBusinessObjType(r0);	 Catch:{ Exception -> 0x00df, all -> 0x00e7 }
        r10.setDuration(r9);	 Catch:{ Exception -> 0x00df, all -> 0x00e7 }
        r1 = r10;
        goto L_0x00e1;
    L_0x00df:
        r1 = r10;
        goto L_0x00f2;
    L_0x00e1:
        if (r12 == 0) goto L_0x00f5;
    L_0x00e3:
        r12.close();
        goto L_0x00f5;
    L_0x00e7:
        r0 = move-exception;
        goto L_0x00eb;
    L_0x00e9:
        r0 = move-exception;
        r12 = r1;
    L_0x00eb:
        if (r12 == 0) goto L_0x00f0;
    L_0x00ed:
        r12.close();
    L_0x00f0:
        throw r0;
    L_0x00f1:
        r12 = r1;
    L_0x00f2:
        if (r12 == 0) goto L_0x00f5;
    L_0x00f4:
        goto L_0x00e3;
    L_0x00f5:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getSongByTitle(java.lang.String):com.gaana.models.Tracks$Track");
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a3 A:{Splitter:B:6:0x0024, ExcHandler: all (th java.lang.Throwable)} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:24:0x009d, code skipped:
            if (r10 != null) goto L_0x009f;
     */
    /* JADX WARNING: Missing block: B:25:0x009f, code skipped:
            r10.close();
     */
    /* JADX WARNING: Missing block: B:26:0x00a3, code skipped:
            r0 = th;
     */
    /* JADX WARNING: Missing block: B:30:0x00a9, code skipped:
            r10.close();
     */
    /* JADX WARNING: Missing block: B:34:0x00ae, code skipped:
            if (r10 == null) goto L_0x00b1;
     */
    /* JADX WARNING: Missing block: B:36:0x00b1, code skipped:
            return r1;
     */
    private com.gaana.models.Playlists.Playlist getPlaylistbyId(java.lang.String r10) {
        /*
        r9 = this;
        r0 = android.text.TextUtils.isEmpty(r10);
        r1 = 0;
        if (r0 == 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = "_id = ";
        r0.append(r2);
        r0.append(r10);
        r6 = r0.toString();
        r3 = r9.mContentResolver;	 Catch:{ Exception -> 0x00ad, all -> 0x00a5 }
        r4 = android.provider.MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x00ad, all -> 0x00a5 }
        r5 = 0;
        r7 = 0;
        r8 = 0;
        r10 = r3.query(r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00ad, all -> 0x00a5 }
        r0 = r10.moveToFirst();	 Catch:{ Exception -> 0x00ae, all -> 0x00a3 }
        if (r0 == 0) goto L_0x009d;
    L_0x002a:
        r0 = "_id";
        r0 = r10.getColumnIndex(r0);	 Catch:{ Exception -> 0x00ae, all -> 0x00a3 }
        r0 = r10.getString(r0);	 Catch:{ Exception -> 0x00ae, all -> 0x00a3 }
        r2 = "name";
        r2 = r10.getColumnIndex(r2);	 Catch:{ Exception -> 0x00ae, all -> 0x00a3 }
        r2 = r10.getString(r2);	 Catch:{ Exception -> 0x00ae, all -> 0x00a3 }
        r3 = "date_added";
        r3 = r10.getColumnIndex(r3);	 Catch:{ Exception -> 0x00ae, all -> 0x00a3 }
        r10.getString(r3);	 Catch:{ Exception -> 0x00ae, all -> 0x00a3 }
        r3 = "date_modified";
        r3 = r10.getColumnIndex(r3);	 Catch:{ Exception -> 0x00ae, all -> 0x00a3 }
        r3 = r10.getString(r3);	 Catch:{ Exception -> 0x00ae, all -> 0x00a3 }
        r4 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x00ae, all -> 0x00a3 }
        if (r4 != 0) goto L_0x009d;
    L_0x0057:
        r4 = new com.gaana.models.Playlists$Playlist;	 Catch:{ Exception -> 0x00ae, all -> 0x00a3 }
        r4.<init>();	 Catch:{ Exception -> 0x00ae, all -> 0x00a3 }
        r4.setBusinessObjId(r0);	 Catch:{ Exception -> 0x009b, all -> 0x00a3 }
        r4.setPlaylistId(r0);	 Catch:{ Exception -> 0x009b, all -> 0x00a3 }
        r4.setName(r2);	 Catch:{ Exception -> 0x009b, all -> 0x00a3 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x009b, all -> 0x00a3 }
        r1.<init>();	 Catch:{ Exception -> 0x009b, all -> 0x00a3 }
        r2 = "PLAYLIST_";
        r1.append(r2);	 Catch:{ Exception -> 0x009b, all -> 0x00a3 }
        r1.append(r0);	 Catch:{ Exception -> 0x009b, all -> 0x00a3 }
        r0 = r1.toString();	 Catch:{ Exception -> 0x009b, all -> 0x00a3 }
        r4.setArtwork(r0);	 Catch:{ Exception -> 0x009b, all -> 0x00a3 }
        if (r3 == 0) goto L_0x0090;
    L_0x007b:
        r0 = new java.text.SimpleDateFormat;	 Catch:{ ParseException -> 0x008c }
        r1 = "yyyy-MM-dd HH:mm:ss";
        r2 = java.util.Locale.ENGLISH;	 Catch:{ ParseException -> 0x008c }
        r0.<init>(r1, r2);	 Catch:{ ParseException -> 0x008c }
        r0 = r0.parse(r3);	 Catch:{ ParseException -> 0x008c }
        r4.setLastModifiedDate(r0);	 Catch:{ ParseException -> 0x008c }
        goto L_0x0090;
    L_0x008c:
        r0 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);	 Catch:{ Exception -> 0x009b, all -> 0x00a3 }
    L_0x0090:
        r0 = 1;
        r4.setLocalMedia(r0);	 Catch:{ Exception -> 0x009b, all -> 0x00a3 }
        r0 = com.managers.URLManager.BusinessObjectType.Playlists;	 Catch:{ Exception -> 0x009b, all -> 0x00a3 }
        r4.setBusinessObjType(r0);	 Catch:{ Exception -> 0x009b, all -> 0x00a3 }
        r1 = r4;
        goto L_0x009d;
    L_0x009b:
        r1 = r4;
        goto L_0x00ae;
    L_0x009d:
        if (r10 == 0) goto L_0x00b1;
    L_0x009f:
        r10.close();
        goto L_0x00b1;
    L_0x00a3:
        r0 = move-exception;
        goto L_0x00a7;
    L_0x00a5:
        r0 = move-exception;
        r10 = r1;
    L_0x00a7:
        if (r10 == 0) goto L_0x00ac;
    L_0x00a9:
        r10.close();
    L_0x00ac:
        throw r0;
    L_0x00ad:
        r10 = r1;
    L_0x00ae:
        if (r10 == 0) goto L_0x00b1;
    L_0x00b0:
        goto L_0x009f;
    L_0x00b1:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getPlaylistbyId(java.lang.String):com.gaana.models.Playlists$Playlist");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x00fd  */
    /* JADX WARNING: Missing block: B:8:0x00f3, code skipped:
            if (r1 != null) goto L_0x0104;
     */
    /* JADX WARNING: Missing block: B:17:0x0102, code skipped:
            if (r1 != null) goto L_0x0104;
     */
    /* JADX WARNING: Missing block: B:19:0x0104, code skipped:
            r1.close();
     */
    /* JADX WARNING: Missing block: B:20:0x0107, code skipped:
            return r0;
     */
    public java.util.ArrayList<com.gaana.models.BusinessObject> getSongsByAlbum(java.lang.String r12) {
        /*
        r11 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = 11;
        r4 = new java.lang.String[r1];
        r1 = "_data";
        r2 = 0;
        r4[r2] = r1;
        r1 = "_id";
        r8 = 1;
        r4[r8] = r1;
        r1 = "title";
        r3 = 2;
        r4[r3] = r1;
        r1 = "_display_name";
        r3 = 3;
        r4[r3] = r1;
        r1 = "artist";
        r3 = 4;
        r4[r3] = r1;
        r1 = "artist_id";
        r3 = 5;
        r4[r3] = r1;
        r1 = "artist_key";
        r3 = 6;
        r4[r3] = r1;
        r1 = "album";
        r3 = 7;
        r4[r3] = r1;
        r1 = "album_id";
        r3 = 8;
        r4[r3] = r1;
        r1 = "mime_type";
        r3 = 9;
        r4[r3] = r1;
        r1 = "duration";
        r3 = 10;
        r4[r3] = r1;
        r5 = "album_id=?";
        r6 = new java.lang.String[r8];
        r6[r2] = r12;
        r7 = "title";
        r12 = 0;
        r2 = r11.mContentResolver;	 Catch:{ Exception -> 0x0101, all -> 0x00f8 }
        r3 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x0101, all -> 0x00f8 }
        r1 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0101, all -> 0x00f8 }
        r12 = r1.moveToFirst();	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        if (r12 == 0) goto L_0x00f3;
    L_0x005a:
        r12 = "_id";
        r12 = r1.getColumnIndex(r12);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r12 = r1.getString(r12);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r2 = "title";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r2 = r1.getString(r2);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r3 = "album_id";
        r3 = r1.getColumnIndex(r3);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r3 = r1.getString(r3);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r4 = "album";
        r4 = r1.getColumnIndex(r4);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r4 = r1.getString(r4);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r5 = "_display_name";
        r5 = r1.getColumnIndex(r5);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r5 = r1.getString(r5);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r6 = "mime_type";
        r6 = r1.getColumnIndex(r6);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r1.getString(r6);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r6 = "artist";
        r6 = r1.getColumnIndex(r6);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r6 = r1.getString(r6);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r7 = "artist_id";
        r7 = r1.getColumnIndex(r7);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r7 = r1.getString(r7);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r9 = "artist_key";
        r9 = r1.getColumnIndex(r9);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r1.getString(r9);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r9 = "duration";
        r9 = r1.getColumnIndex(r9);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r9 = r1.getString(r9);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r10 = new com.gaana.models.LocalTrack;	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r10.<init>();	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r10.setBusinessObjId(r12);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r10.setName(r5);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r10.setAlbumId(r3);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r10.setAlbumName(r4);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r10.setTracktitle(r2);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r12 = r11.getTrackArtist(r6, r7);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r10.setArtist(r12);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r10.setLocalMedia(r8);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r10.setArtwork(r3);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r12 = com.managers.URLManager.BusinessObjectType.Tracks;	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r10.setBusinessObjType(r12);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r12 = com.managers.URLManager.BusinessObjectType.Albums;	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r10.setParentBusinessObjType(r12);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r10.setDuration(r9);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r0.add(r10);	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        r12 = r1.moveToNext();	 Catch:{ Exception -> 0x0102, all -> 0x00f6 }
        if (r12 != 0) goto L_0x005a;
    L_0x00f3:
        if (r1 == 0) goto L_0x0107;
    L_0x00f5:
        goto L_0x0104;
    L_0x00f6:
        r12 = move-exception;
        goto L_0x00fb;
    L_0x00f8:
        r0 = move-exception;
        r1 = r12;
        r12 = r0;
    L_0x00fb:
        if (r1 == 0) goto L_0x0100;
    L_0x00fd:
        r1.close();
    L_0x0100:
        throw r12;
    L_0x0101:
        r1 = r12;
    L_0x0102:
        if (r1 == 0) goto L_0x0107;
    L_0x0104:
        r1.close();
    L_0x0107:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getSongsByAlbum(java.lang.String):java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0049  */
    /* JADX WARNING: Missing block: B:12:0x003b, code skipped:
            if (r12 != null) goto L_0x003d;
     */
    /* JADX WARNING: Missing block: B:13:0x003d, code skipped:
            r12.close();
     */
    /* JADX WARNING: Missing block: B:22:0x004e, code skipped:
            if (r12 != null) goto L_0x003d;
     */
    /* JADX WARNING: Missing block: B:24:0x0051, code skipped:
            return r1;
     */
    private long getPlaylistIdByName(java.lang.String r12) {
        /*
        r11 = this;
        r0 = 2;
        r0 = new java.lang.String[r0];
        r1 = "_data";
        r2 = 0;
        r0[r2] = r1;
        r1 = "_id";
        r3 = 1;
        r0[r3] = r1;
        r0 = android.text.TextUtils.isEmpty(r12);
        if (r0 == 0) goto L_0x0016;
    L_0x0013:
        r0 = -1;
        return r0;
    L_0x0016:
        r0 = 0;
        r7 = "name = ?";
        r8 = new java.lang.String[r3];
        r8[r2] = r12;
        r1 = 0;
        r4 = r11.mContentResolver;	 Catch:{ Exception -> 0x004d, all -> 0x0043 }
        r5 = android.provider.MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x004d, all -> 0x0043 }
        r6 = 0;
        r9 = 0;
        r12 = r4.query(r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x004d, all -> 0x0043 }
        r0 = r12.moveToFirst();	 Catch:{ Exception -> 0x004e, all -> 0x0041 }
        if (r0 == 0) goto L_0x003b;
    L_0x002f:
        r0 = "_id";
        r0 = r12.getColumnIndex(r0);	 Catch:{ Exception -> 0x004e, all -> 0x0041 }
        r0 = r12.getInt(r0);	 Catch:{ Exception -> 0x004e, all -> 0x0041 }
        r0 = (long) r0;
        r1 = r0;
    L_0x003b:
        if (r12 == 0) goto L_0x0051;
    L_0x003d:
        r12.close();
        goto L_0x0051;
    L_0x0041:
        r0 = move-exception;
        goto L_0x0047;
    L_0x0043:
        r12 = move-exception;
        r10 = r0;
        r0 = r12;
        r12 = r10;
    L_0x0047:
        if (r12 == 0) goto L_0x004c;
    L_0x0049:
        r12.close();
    L_0x004c:
        throw r0;
    L_0x004d:
        r12 = r0;
    L_0x004e:
        if (r12 == 0) goto L_0x0051;
    L_0x0050:
        goto L_0x003d;
    L_0x0051:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getPlaylistIdByName(java.lang.String):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0043  */
    /* JADX WARNING: Missing block: B:8:0x003a, code skipped:
            if (r9 != null) goto L_0x004a;
     */
    /* JADX WARNING: Missing block: B:17:0x0048, code skipped:
            if (r9 != null) goto L_0x004a;
     */
    /* JADX WARNING: Missing block: B:19:0x004a, code skipped:
            r9.close();
     */
    /* JADX WARNING: Missing block: B:20:0x004d, code skipped:
            return r0;
     */
    private java.util.ArrayList<java.lang.String> getSongsIdOfPlaylist(java.lang.String r9) {
        /*
        r8 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = 1;
        r4 = new java.lang.String[r1];
        r1 = "audio_id";
        r2 = 0;
        r4[r2] = r1;
        r1 = 0;
        r2 = r8.mContentResolver;	 Catch:{ Exception -> 0x0047, all -> 0x003f }
        r3 = "external";
        r5 = java.lang.Long.parseLong(r9);	 Catch:{ Exception -> 0x0047, all -> 0x003f }
        r3 = android.provider.MediaStore.Audio.Playlists.Members.getContentUri(r3, r5);	 Catch:{ Exception -> 0x0047, all -> 0x003f }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r9 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0047, all -> 0x003f }
        r1 = r9.moveToFirst();	 Catch:{ Exception -> 0x0048, all -> 0x003d }
        if (r1 == 0) goto L_0x003a;
    L_0x0027:
        r1 = "audio_id";
        r1 = r9.getColumnIndex(r1);	 Catch:{ Exception -> 0x0048, all -> 0x003d }
        r1 = r9.getString(r1);	 Catch:{ Exception -> 0x0048, all -> 0x003d }
        r0.add(r1);	 Catch:{ Exception -> 0x0048, all -> 0x003d }
        r1 = r9.moveToNext();	 Catch:{ Exception -> 0x0048, all -> 0x003d }
        if (r1 != 0) goto L_0x0027;
    L_0x003a:
        if (r9 == 0) goto L_0x004d;
    L_0x003c:
        goto L_0x004a;
    L_0x003d:
        r0 = move-exception;
        goto L_0x0041;
    L_0x003f:
        r0 = move-exception;
        r9 = r1;
    L_0x0041:
        if (r9 == 0) goto L_0x0046;
    L_0x0043:
        r9.close();
    L_0x0046:
        throw r0;
    L_0x0047:
        r9 = r1;
    L_0x0048:
        if (r9 == 0) goto L_0x004d;
    L_0x004a:
        r9.close();
    L_0x004d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getSongsIdOfPlaylist(java.lang.String):java.util.ArrayList");
    }

    public ArrayList<BusinessObject> getMostHeardSongs() {
        return new ArrayList();
    }

    public ArrayList<BusinessObject> getRecentlyAddedSongs() {
        return getRecentlyAddedSongs(true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x00fe  */
    /* JADX WARNING: Missing block: B:12:0x00f5, code skipped:
            if (r12 != null) goto L_0x0105;
     */
    /* JADX WARNING: Missing block: B:21:0x0103, code skipped:
            if (r12 != null) goto L_0x0105;
     */
    /* JADX WARNING: Missing block: B:23:0x0105, code skipped:
            r12.close();
     */
    /* JADX WARNING: Missing block: B:24:0x0108, code skipped:
            return r0;
     */
    public java.util.ArrayList<com.gaana.models.BusinessObject> getRecentlyAddedSongs(boolean r12) {
        /*
        r11 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = 11;
        r4 = new java.lang.String[r1];
        r1 = "_data";
        r2 = 0;
        r4[r2] = r1;
        r1 = "_id";
        r8 = 1;
        r4[r8] = r1;
        r1 = "title";
        r2 = 2;
        r4[r2] = r1;
        r1 = "_display_name";
        r2 = 3;
        r4[r2] = r1;
        r1 = "artist";
        r2 = 4;
        r4[r2] = r1;
        r1 = "artist_id";
        r2 = 5;
        r4[r2] = r1;
        r1 = "artist_key";
        r2 = 6;
        r4[r2] = r1;
        r1 = "album";
        r2 = 7;
        r4[r2] = r1;
        r1 = "album_id";
        r2 = 8;
        r4[r2] = r1;
        r1 = "mime_type";
        r2 = 9;
        r4[r2] = r1;
        r1 = "duration";
        r2 = 10;
        r4[r2] = r1;
        r1 = "date_added DESC LIMIT 100";
        r5 = "mime_type NOT NULL AND is_music != 0";
        r9 = 0;
        r2 = r11.mContentResolver;	 Catch:{ Exception -> 0x0102, all -> 0x00fa }
        r3 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x0102, all -> 0x00fa }
        r6 = 0;
        if (r12 == 0) goto L_0x0051;
    L_0x004f:
        r7 = r1;
        goto L_0x0052;
    L_0x0051:
        r7 = r9;
    L_0x0052:
        r12 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0102, all -> 0x00fa }
        r1 = r12.moveToFirst();	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        if (r1 == 0) goto L_0x00f5;
    L_0x005c:
        r1 = "_id";
        r1 = r12.getColumnIndex(r1);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r1 = r12.getString(r1);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r2 = "title";
        r2 = r12.getColumnIndex(r2);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r2 = r12.getString(r2);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r3 = "album_id";
        r3 = r12.getColumnIndex(r3);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r3 = r12.getString(r3);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r4 = "album";
        r4 = r12.getColumnIndex(r4);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r4 = r12.getString(r4);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r5 = "_display_name";
        r5 = r12.getColumnIndex(r5);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r5 = r12.getString(r5);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r6 = "mime_type";
        r6 = r12.getColumnIndex(r6);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r12.getString(r6);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r6 = "artist";
        r6 = r12.getColumnIndex(r6);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r6 = r12.getString(r6);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r7 = "artist_id";
        r7 = r12.getColumnIndex(r7);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r7 = r12.getString(r7);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r9 = "artist_key";
        r9 = r12.getColumnIndex(r9);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r12.getString(r9);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r9 = "duration";
        r9 = r12.getColumnIndex(r9);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r9 = r12.getString(r9);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r10 = new com.gaana.models.LocalTrack;	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r10.<init>();	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r10.setBusinessObjId(r1);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r10.setName(r5);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r10.setAlbumId(r3);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r10.setAlbumName(r4);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r10.setTracktitle(r2);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r1 = r11.getTrackArtist(r6, r7);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r10.setArtist(r1);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r10.setLocalMedia(r8);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r10.setArtwork(r3);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r1 = com.managers.URLManager.BusinessObjectType.Tracks;	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r10.setBusinessObjType(r1);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r1 = com.managers.URLManager.BusinessObjectType.Albums;	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r10.setParentBusinessObjType(r1);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r10.setDuration(r9);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r0.add(r10);	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        r1 = r12.moveToNext();	 Catch:{ Exception -> 0x0103, all -> 0x00f8 }
        if (r1 != 0) goto L_0x005c;
    L_0x00f5:
        if (r12 == 0) goto L_0x0108;
    L_0x00f7:
        goto L_0x0105;
    L_0x00f8:
        r0 = move-exception;
        goto L_0x00fc;
    L_0x00fa:
        r0 = move-exception;
        r12 = r9;
    L_0x00fc:
        if (r12 == 0) goto L_0x0101;
    L_0x00fe:
        r12.close();
    L_0x0101:
        throw r0;
    L_0x0102:
        r12 = r9;
    L_0x0103:
        if (r12 == 0) goto L_0x0108;
    L_0x0105:
        r12.close();
    L_0x0108:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getRecentlyAddedSongs(boolean):java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x010c  */
    /* JADX WARNING: Missing block: B:16:0x0103, code skipped:
            if (r12 != null) goto L_0x0113;
     */
    /* JADX WARNING: Missing block: B:25:0x0111, code skipped:
            if (r12 != null) goto L_0x0113;
     */
    /* JADX WARNING: Missing block: B:27:0x0113, code skipped:
            r12.close();
     */
    /* JADX WARNING: Missing block: B:28:0x0116, code skipped:
            return r0;
     */
    public java.util.ArrayList<com.gaana.models.BusinessObject> getSongsByPlaylist(java.lang.String r12) {
        /*
        r11 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = "PLYALIST_RECENTLY_ADDED_ID";
        r1 = r12.equals(r1);
        if (r1 == 0) goto L_0x0012;
    L_0x000d:
        r12 = r11.getRecentlyAddedSongs();
        return r12;
    L_0x0012:
        r1 = "PLYALIST_MOST_HEARD_ID";
        r1 = r12.equals(r1);
        if (r1 == 0) goto L_0x001f;
    L_0x001a:
        r12 = r11.getMostHeardSongs();
        return r12;
    L_0x001f:
        r1 = 10;
        r4 = new java.lang.String[r1];
        r1 = 0;
        r2 = "audio_id";
        r4[r1] = r2;
        r1 = "artist";
        r8 = 1;
        r4[r8] = r1;
        r1 = 2;
        r2 = "artist_id";
        r4[r1] = r2;
        r1 = 3;
        r2 = "album_id";
        r4[r1] = r2;
        r1 = 4;
        r2 = "album";
        r4[r1] = r2;
        r1 = 5;
        r2 = "title";
        r4[r1] = r2;
        r1 = 6;
        r2 = "_id";
        r4[r1] = r2;
        r1 = 7;
        r2 = "_display_name";
        r4[r1] = r2;
        r1 = 8;
        r2 = "mime_type";
        r4[r1] = r2;
        r1 = 9;
        r2 = "duration";
        r4[r1] = r2;
        r5 = "mime_type NOT NULL";
        r6 = 0;
        r1 = 0;
        r2 = r11.mContentResolver;	 Catch:{ Exception -> 0x0110, all -> 0x0108 }
        r3 = "external";
        r9 = java.lang.Long.parseLong(r12);	 Catch:{ Exception -> 0x0110, all -> 0x0108 }
        r3 = android.provider.MediaStore.Audio.Playlists.Members.getContentUri(r3, r9);	 Catch:{ Exception -> 0x0110, all -> 0x0108 }
        r7 = "play_order DESC";
        r12 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0110, all -> 0x0108 }
        r1 = r12.moveToFirst();	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        if (r1 == 0) goto L_0x0103;
    L_0x0073:
        r1 = "audio_id";
        r1 = r12.getColumnIndex(r1);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r1 = r12.getString(r1);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r2 = "artist";
        r2 = r12.getColumnIndex(r2);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r2 = r12.getString(r2);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r3 = "artist_id";
        r3 = r12.getColumnIndex(r3);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r3 = r12.getString(r3);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r4 = "_display_name";
        r4 = r12.getColumnIndex(r4);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r4 = r12.getString(r4);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r5 = "album";
        r5 = r12.getColumnIndex(r5);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r5 = r12.getString(r5);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r6 = "album_id";
        r6 = r12.getColumnIndex(r6);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r6 = r12.getString(r6);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r7 = "title";
        r7 = r12.getColumnIndex(r7);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r7 = r12.getString(r7);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r9 = "_id";
        r9 = r12.getColumnIndex(r9);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r12.getString(r9);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r9 = "duration";
        r9 = r12.getColumnIndex(r9);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r9 = r12.getString(r9);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r10 = new com.gaana.models.LocalTrack;	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r10.<init>();	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r10.setBusinessObjId(r1);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r10.setName(r4);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r10.setTracktitle(r7);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r1 = r11.getTrackArtist(r2, r3);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r10.setArtist(r1);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r10.setLocalMedia(r8);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r10.setArtwork(r6);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r10.setAlbumName(r5);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r10.setAlbumId(r6);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r1 = com.managers.URLManager.BusinessObjectType.Tracks;	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r10.setBusinessObjType(r1);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r10.setDuration(r9);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r1 = com.managers.URLManager.BusinessObjectType.Playlists;	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r10.setParentBusinessObjType(r1);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r0.add(r10);	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        r1 = r12.moveToNext();	 Catch:{ Exception -> 0x0111, all -> 0x0106 }
        if (r1 != 0) goto L_0x0073;
    L_0x0103:
        if (r12 == 0) goto L_0x0116;
    L_0x0105:
        goto L_0x0113;
    L_0x0106:
        r0 = move-exception;
        goto L_0x010a;
    L_0x0108:
        r0 = move-exception;
        r12 = r1;
    L_0x010a:
        if (r12 == 0) goto L_0x010f;
    L_0x010c:
        r12.close();
    L_0x010f:
        throw r0;
    L_0x0110:
        r12 = r1;
    L_0x0111:
        if (r12 == 0) goto L_0x0116;
    L_0x0113:
        r12.close();
    L_0x0116:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getSongsByPlaylist(java.lang.String):java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0045  */
    /* JADX WARNING: Missing block: B:8:0x003c, code skipped:
            if (r11 != null) goto L_0x004c;
     */
    /* JADX WARNING: Missing block: B:17:0x004a, code skipped:
            if (r11 != null) goto L_0x004c;
     */
    /* JADX WARNING: Missing block: B:19:0x004c, code skipped:
            r11.close();
     */
    /* JADX WARNING: Missing block: B:20:0x004f, code skipped:
            return r0;
     */
    public java.util.ArrayList<java.lang.String> getAllAlbumIdForPlaylist(java.lang.String r11) {
        /*
        r10 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = 1;
        r4 = new java.lang.String[r1];
        r1 = "DISTINCT album_id";
        r2 = 0;
        r4[r2] = r1;
        r5 = "mime_type NOT NULL";
        r7 = "date_added DESC";
        r1 = 0;
        r2 = r10.mContentResolver;	 Catch:{ Exception -> 0x0049, all -> 0x0041 }
        r3 = "external";
        r8 = java.lang.Long.parseLong(r11);	 Catch:{ Exception -> 0x0049, all -> 0x0041 }
        r3 = android.provider.MediaStore.Audio.Playlists.Members.getContentUri(r3, r8);	 Catch:{ Exception -> 0x0049, all -> 0x0041 }
        r6 = 0;
        r11 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0049, all -> 0x0041 }
        r1 = r11.moveToFirst();	 Catch:{ Exception -> 0x004a, all -> 0x003f }
        if (r1 == 0) goto L_0x003c;
    L_0x0029:
        r1 = "album_id";
        r1 = r11.getColumnIndex(r1);	 Catch:{ Exception -> 0x004a, all -> 0x003f }
        r1 = r11.getString(r1);	 Catch:{ Exception -> 0x004a, all -> 0x003f }
        r0.add(r1);	 Catch:{ Exception -> 0x004a, all -> 0x003f }
        r1 = r11.moveToNext();	 Catch:{ Exception -> 0x004a, all -> 0x003f }
        if (r1 != 0) goto L_0x0029;
    L_0x003c:
        if (r11 == 0) goto L_0x004f;
    L_0x003e:
        goto L_0x004c;
    L_0x003f:
        r0 = move-exception;
        goto L_0x0043;
    L_0x0041:
        r0 = move-exception;
        r11 = r1;
    L_0x0043:
        if (r11 == 0) goto L_0x0048;
    L_0x0045:
        r11.close();
    L_0x0048:
        throw r0;
    L_0x0049:
        r11 = r1;
    L_0x004a:
        if (r11 == 0) goto L_0x004f;
    L_0x004c:
        r11.close();
    L_0x004f:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getAllAlbumIdForPlaylist(java.lang.String):java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0125  */
    /* JADX WARNING: Missing block: B:8:0x011a, code skipped:
            if (r13 != null) goto L_0x012c;
     */
    /* JADX WARNING: Missing block: B:17:0x012a, code skipped:
            if (r13 != null) goto L_0x012c;
     */
    /* JADX WARNING: Missing block: B:19:0x012c, code skipped:
            r13.close();
     */
    /* JADX WARNING: Missing block: B:20:0x012f, code skipped:
            return r0;
     */
    public java.util.ArrayList<com.gaana.models.BusinessObject> getSongsByArtist(long r12) {
        /*
        r11 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = 10;
        r4 = new java.lang.String[r1];
        r1 = "_data";
        r2 = 0;
        r4[r2] = r1;
        r1 = "_id";
        r8 = 1;
        r4[r8] = r1;
        r1 = "title";
        r2 = 2;
        r4[r2] = r1;
        r1 = "_display_name";
        r2 = 3;
        r4[r2] = r1;
        r1 = "artist";
        r2 = 4;
        r4[r2] = r1;
        r1 = "artist_id";
        r2 = 5;
        r4[r2] = r1;
        r1 = "album";
        r2 = 6;
        r4[r2] = r1;
        r1 = "album_id";
        r2 = 7;
        r4[r2] = r1;
        r1 = "mime_type";
        r2 = 8;
        r4[r2] = r1;
        r1 = "duration";
        r2 = 9;
        r4[r2] = r1;
        r1 = "mime_type NOT NULL";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r1);
        r1 = " AND ";
        r2.append(r1);
        r1 = "is_music";
        r2.append(r1);
        r1 = " != 0";
        r2.append(r1);
        r1 = r2.toString();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r1);
        r1 = " AND ";
        r2.append(r1);
        r1 = "artist_id";
        r2.append(r1);
        r1 = "=";
        r2.append(r1);
        r2.append(r12);
        r5 = r2.toString();
        r7 = "title";
        r12 = 0;
        r2 = r11.mContentResolver;	 Catch:{ Exception -> 0x0129, all -> 0x011f }
        r3 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x0129, all -> 0x011f }
        r6 = 0;
        r13 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0129, all -> 0x011f }
        r12 = r13.moveToFirst();	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        if (r12 == 0) goto L_0x011a;
    L_0x008a:
        r12 = "_id";
        r12 = r13.getColumnIndex(r12);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r12 = r13.getString(r12);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r1 = "title";
        r1 = r13.getColumnIndex(r1);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r1 = r13.getString(r1);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r2 = "_display_name";
        r2 = r13.getColumnIndex(r2);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r2 = r13.getString(r2);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r3 = "mime_type";
        r3 = r13.getColumnIndex(r3);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r13.getString(r3);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r3 = "artist";
        r3 = r13.getColumnIndex(r3);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r3 = r13.getString(r3);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r4 = "artist_id";
        r4 = r13.getColumnIndex(r4);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r4 = r13.getString(r4);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r5 = "album_id";
        r5 = r13.getColumnIndex(r5);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r5 = r13.getString(r5);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r6 = "album";
        r6 = r13.getColumnIndex(r6);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r6 = r13.getString(r6);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r7 = "duration";
        r7 = r13.getColumnIndex(r7);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r7 = r13.getString(r7);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r9 = new com.gaana.models.LocalTrack;	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r9.<init>();	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r9.setBusinessObjId(r12);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r9.setName(r2);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r9.setTracktitle(r1);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r9.setArtwork(r5);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r9.setAlbumName(r6);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r9.setAlbumId(r5);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r12 = r11.getTrackArtist(r3, r4);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r9.setArtist(r12);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r9.setLocalMedia(r8);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r12 = com.managers.URLManager.BusinessObjectType.Tracks;	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r9.setBusinessObjType(r12);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r9.setDuration(r7);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r12 = com.managers.URLManager.BusinessObjectType.Artists;	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r9.setParentBusinessObjType(r12);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r0.add(r9);	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        r12 = r13.moveToNext();	 Catch:{ Exception -> 0x012a, all -> 0x011d }
        if (r12 != 0) goto L_0x008a;
    L_0x011a:
        if (r13 == 0) goto L_0x012f;
    L_0x011c:
        goto L_0x012c;
    L_0x011d:
        r12 = move-exception;
        goto L_0x0123;
    L_0x011f:
        r13 = move-exception;
        r10 = r13;
        r13 = r12;
        r12 = r10;
    L_0x0123:
        if (r13 == 0) goto L_0x0128;
    L_0x0125:
        r13.close();
    L_0x0128:
        throw r12;
    L_0x0129:
        r13 = r12;
    L_0x012a:
        if (r13 == 0) goto L_0x012f;
    L_0x012c:
        r13.close();
    L_0x012f:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getSongsByArtist(long):java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x00a6  */
    /* JADX WARNING: Missing block: B:8:0x009d, code skipped:
            if (r10 != null) goto L_0x00ad;
     */
    /* JADX WARNING: Missing block: B:17:0x00ab, code skipped:
            if (r10 != null) goto L_0x00ad;
     */
    /* JADX WARNING: Missing block: B:19:0x00ad, code skipped:
            r10.close();
     */
    /* JADX WARNING: Missing block: B:20:0x00b0, code skipped:
            return r0;
     */
    public java.util.ArrayList<com.gaana.models.BusinessObject> getAlbumByArtist(long r10) {
        /*
        r9 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = 6;
        r4 = new java.lang.String[r1];
        r1 = "_id";
        r2 = 0;
        r4[r2] = r1;
        r1 = "album_key";
        r8 = 1;
        r4[r8] = r1;
        r1 = "album_art";
        r2 = 2;
        r4[r2] = r1;
        r1 = "numsongs";
        r2 = 3;
        r4[r2] = r1;
        r1 = "album";
        r2 = 4;
        r4[r2] = r1;
        r1 = "artist";
        r2 = 5;
        r4[r2] = r1;
        r7 = "album";
        r1 = 0;
        r2 = r9.mContentResolver;	 Catch:{ Exception -> 0x00aa, all -> 0x00a2 }
        r3 = "external";
        r3 = android.provider.MediaStore.Audio.Artists.Albums.getContentUri(r3, r10);	 Catch:{ Exception -> 0x00aa, all -> 0x00a2 }
        r5 = 0;
        r6 = 0;
        r10 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x00aa, all -> 0x00a2 }
        r11 = r10.moveToFirst();	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        if (r11 == 0) goto L_0x009d;
    L_0x003d:
        r11 = "_id";
        r11 = r10.getColumnIndex(r11);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r11 = r10.getString(r11);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r1 = "album_key";
        r1 = r10.getColumnIndex(r1);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r10.getString(r1);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r1 = "numsongs";
        r1 = r10.getColumnIndex(r1);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r10.getString(r1);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r1 = "artist";
        r1 = r10.getColumnIndex(r1);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r1 = r10.getString(r1);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r2 = "album";
        r2 = r10.getColumnIndex(r2);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r2 = r10.getString(r2);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r3 = "album_art";
        r3 = r10.getColumnIndex(r3);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r3 = r10.getString(r3);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r4 = new com.gaana.models.Albums$Album;	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r4.<init>();	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r4.setBusinessObjId(r11);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r4.setName(r2);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r1 = r9.getAlbumArtist(r1);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r4.setPrimaryartist(r1);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r4.setArtwork(r11);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r4.setLocalMedia(r8);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r11 = com.managers.URLManager.BusinessObjectType.Albums;	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r4.setBusinessObjType(r11);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r0.add(r4);	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        r11 = r10.moveToNext();	 Catch:{ Exception -> 0x00ab, all -> 0x00a0 }
        if (r11 != 0) goto L_0x003d;
    L_0x009d:
        if (r10 == 0) goto L_0x00b0;
    L_0x009f:
        goto L_0x00ad;
    L_0x00a0:
        r11 = move-exception;
        goto L_0x00a4;
    L_0x00a2:
        r11 = move-exception;
        r10 = r1;
    L_0x00a4:
        if (r10 == 0) goto L_0x00a9;
    L_0x00a6:
        r10.close();
    L_0x00a9:
        throw r11;
    L_0x00aa:
        r10 = r1;
    L_0x00ab:
        if (r10 == 0) goto L_0x00b0;
    L_0x00ad:
        r10.close();
    L_0x00b0:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getAlbumByArtist(long):java.util.ArrayList");
    }

    public Track getTrackFromLocalMedia(OfflineTrack offlineTrack) {
        LocalTrack localTrack = new LocalTrack();
        localTrack.setBusinessObjId(offlineTrack.getBusinessObjId());
        localTrack.setName(offlineTrack.getName());
        localTrack.setTracktitle(offlineTrack.getName());
        localTrack.setBusinessObjType(BusinessObjectType.Tracks);
        localTrack.setAlbumName(offlineTrack.getAlbumName());
        localTrack.setArtist(getTrackArtist(offlineTrack.getArtistName(), offlineTrack.getArtistId()));
        localTrack.setAlbumId(offlineTrack.getAlbumId());
        localTrack.setLocalMedia(true);
        localTrack.setArtwork(offlineTrack.getImageUrl());
        localTrack.setDuration(offlineTrack.getDuration());
        return localTrack;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006c A:{Splitter:B:7:0x0041, ExcHandler: all (th java.lang.Throwable)} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:16:0x0064, code skipped:
            r9 = r1;
     */
    /* JADX WARNING: Missing block: B:18:0x0066, code skipped:
            if (r0 != null) goto L_0x0068;
     */
    /* JADX WARNING: Missing block: B:19:0x0068, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:20:0x006c, code skipped:
            r9 = th;
     */
    /* JADX WARNING: Missing block: B:28:0x0079, code skipped:
            if (r0 == null) goto L_0x007c;
     */
    /* JADX WARNING: Missing block: B:30:0x007c, code skipped:
            return r9;
     */
    public java.lang.String getLocalTrackPath(java.lang.String r9) {
        /*
        r8 = this;
        r0 = 2;
        r3 = new java.lang.String[r0];
        r0 = "_data";
        r1 = 0;
        r3[r1] = r0;
        r0 = "_id";
        r1 = 1;
        r3[r1] = r0;
        r0 = "mime_type NOT NULL";
        r1 = android.text.TextUtils.isEmpty(r9);
        if (r1 != 0) goto L_0x0035;
    L_0x0015:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r1.append(r0);
        r0 = " AND ";
        r1.append(r0);
        r0 = "_id";
        r1.append(r0);
        r0 = "=";
        r1.append(r0);
        r1.append(r9);
        r9 = r1.toString();
        r4 = r9;
        goto L_0x0036;
    L_0x0035:
        r4 = r0;
    L_0x0036:
        r9 = 0;
        r1 = r8.mContentResolver;	 Catch:{ Exception -> 0x0078, all -> 0x006e }
        r2 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x0078, all -> 0x006e }
        r5 = 0;
        r6 = 0;
        r0 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0078, all -> 0x006e }
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x0079, all -> 0x006c }
        if (r1 == 0) goto L_0x0066;
    L_0x0047:
        r1 = "_id";
        r1 = r0.getColumnIndex(r1);	 Catch:{ Exception -> 0x0079, all -> 0x006c }
        r0.getString(r1);	 Catch:{ Exception -> 0x0079, all -> 0x006c }
        r1 = "_data";
        r1 = r0.getColumnIndex(r1);	 Catch:{ Exception -> 0x0079, all -> 0x006c }
        r1 = r0.getString(r1);	 Catch:{ Exception -> 0x0079, all -> 0x006c }
        r9 = r0.moveToNext();	 Catch:{ Exception -> 0x0064, all -> 0x006c }
        if (r9 != 0) goto L_0x0062;
    L_0x0060:
        r9 = r1;
        goto L_0x0066;
    L_0x0062:
        r9 = r1;
        goto L_0x0047;
    L_0x0064:
        r9 = r1;
        goto L_0x0079;
    L_0x0066:
        if (r0 == 0) goto L_0x007c;
    L_0x0068:
        r0.close();
        goto L_0x007c;
    L_0x006c:
        r9 = move-exception;
        goto L_0x0072;
    L_0x006e:
        r0 = move-exception;
        r7 = r0;
        r0 = r9;
        r9 = r7;
    L_0x0072:
        if (r0 == 0) goto L_0x0077;
    L_0x0074:
        r0.close();
    L_0x0077:
        throw r9;
    L_0x0078:
        r0 = r9;
    L_0x0079:
        if (r0 == 0) goto L_0x007c;
    L_0x007b:
        goto L_0x0068;
    L_0x007c:
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getLocalTrackPath(java.lang.String):java.lang.String");
    }

    public void startImport() {
        aj.a().a(this.mContext, this.mContext.getString(R.string.import_local_media_to_gaana));
        d.a().a("PREFF_LOCAL_MEDIA_IMPORTED_TO_GAANA", true, false);
    }

    public boolean isLocalMediaImported() {
        return d.a().b("PREFF_LOCAL_MEDIA_IMPORTED_TO_GAANA", false, false);
    }

    public String generateTrackKeyIdentifier(OfflineTrack offlineTrack) {
        String name = offlineTrack.getName();
        offlineTrack.getAlbumName();
        return Util.a(name, offlineTrack.getDuration(), offlineTrack.getArtistName());
    }

    public int deleteLocalItems(Activity activity, BusinessObject businessObject) {
        int delete;
        if ((businessObject instanceof Track) || (businessObject instanceof OfflineTrack)) {
            String localTrackPath = getLocalTrackPath(businessObject.getBusinessObjId());
            if (!TextUtils.isEmpty(localTrackPath)) {
                StorageUtils.delete(new File(localTrackPath));
            }
            ContentResolver contentResolver = this.mContentResolver;
            Uri uri = Media.EXTERNAL_CONTENT_URI;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("_id=");
            stringBuilder.append(businessObject.getBusinessObjId());
            delete = contentResolver.delete(uri, stringBuilder.toString(), null);
            if (delete > 0) {
                PlaylistSyncManager.getInstance().changeSyncStatusOnTrackDeleted(businessObject.getBusinessObjId());
                setSongFlagIsDirty = true;
            }
            return delete;
        } else if (!(businessObject instanceof Playlist)) {
            return 0;
        } else {
            delete = deleteLocalPlaylist(businessObject.getBusinessObjId());
            setPlaylistFlagIsDirty = true;
            return delete;
        }
    }

    public int deleteLocalPlaylist(String str) {
        ContentResolver contentResolver = this.mContentResolver;
        Uri uri = Playlists.EXTERNAL_CONTENT_URI;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("_id=");
        stringBuilder.append(str);
        return contentResolver.delete(uri, stringBuilder.toString(), null);
    }

    /* JADX WARNING: Missing block: B:11:0x003a, code skipped:
            r5 = r2;
     */
    /* JADX WARNING: Missing block: B:12:0x003b, code skipped:
            r6 = new java.lang.String[]{r0};
            r2 = new java.lang.StringBuilder();
            r2.append(r0);
            r2.append(" = ?");
            r11 = r10.mContentResolver.query(r5, r6, r2.toString(), new java.lang.String[]{r11.getBusinessObjId()}, null);
     */
    /* JADX WARNING: Missing block: B:13:0x0063, code skipped:
            if (r11.moveToFirst() == false) goto L_0x0066;
     */
    /* JADX WARNING: Missing block: B:14:0x0065, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:15:0x0066, code skipped:
            r11.close();
     */
    public boolean isLocalItemExist(com.gaana.models.BusinessObject r11) {
        /*
        r10 = this;
        r0 = r11.getBusinessObjId();
        r0 = android.text.TextUtils.isEmpty(r0);
        r1 = 0;
        if (r0 != 0) goto L_0x0069;
    L_0x000b:
        r0 = com.gaana.localmedia.LocalMediaManager.AnonymousClass2.$SwitchMap$com$managers$URLManager$BusinessObjectType;
        r2 = r11.getBusinessObjType();
        r2 = r2.ordinal();
        r0 = r0[r2];
        r2 = 0;
        r3 = 1;
        switch(r0) {
            case 2: goto L_0x0036;
            case 3: goto L_0x0031;
            case 4: goto L_0x001f;
            default: goto L_0x001c;
        };
    L_0x001c:
        r0 = r2;
        r5 = r0;
        goto L_0x003b;
    L_0x001f:
        r0 = r11.getBusinessObjId();
        r2 = "PLYALIST_RECENTLY_ADDED_ID";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x002c;
    L_0x002b:
        return r3;
    L_0x002c:
        r2 = android.provider.MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
        r0 = "_id";
        goto L_0x003a;
    L_0x0031:
        r2 = android.provider.MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        r0 = "_id";
        goto L_0x003a;
    L_0x0036:
        r2 = android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        r0 = "_id";
    L_0x003a:
        r5 = r2;
    L_0x003b:
        r6 = new java.lang.String[r3];
        r6[r1] = r0;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r2.append(r0);
        r0 = " = ?";
        r2.append(r0);
        r7 = r2.toString();
        r8 = new java.lang.String[r3];
        r11 = r11.getBusinessObjId();
        r8[r1] = r11;
        r4 = r10.mContentResolver;
        r9 = 0;
        r11 = r4.query(r5, r6, r7, r8, r9);
        r0 = r11.moveToFirst();
        if (r0 == 0) goto L_0x0066;
    L_0x0065:
        return r3;
    L_0x0066:
        r11.close();
    L_0x0069:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.isLocalItemExist(com.gaana.models.BusinessObject):boolean");
    }

    public boolean isEmptyPlaylist(String str) {
        if (str != null && str.equals(PLYALIST_RECENTLY_ADDED_ID)) {
            return false;
        }
        if (str != null) {
            Cursor query = this.mContentResolver.query(Members.getContentUri("external", Long.parseLong(str)), new String[]{"audio_id"}, null, null, null);
            if (query.moveToFirst()) {
                query.close();
                return false;
            }
        }
        return true;
    }

    public long createLocalPlaylist(String str, ArrayList<Track> arrayList) {
        Throwable e;
        ContentResolver contentResolver = GaanaApplication.getContext().getContentResolver();
        long j = -1;
        long playlistIdByName;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", str);
            contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
            Uri insert = contentResolver.insert(Playlists.EXTERNAL_CONTENT_URI, contentValues);
            if (insert != null) {
                addToLocalPlaylist(insert, (ArrayList) arrayList);
                Cursor query = contentResolver.query(insert, new String[]{BaseColumns._ID}, null, null, null);
                if (query.moveToFirst()) {
                    j = (long) query.getInt(query.getColumnIndex(BaseColumns._ID));
                    setPlaylistFlagIsDirty = true;
                }
                query.close();
                return j;
            }
            playlistIdByName = getPlaylistIdByName(str);
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(playlistIdByName);
                addToLocalPlaylist(stringBuilder.toString(), (ArrayList) arrayList);
                return playlistIdByName;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            playlistIdByName = -1;
            ThrowableExtension.printStackTrace(e);
            return playlistIdByName;
        }
    }

    public PLAYLIST_STATUS addToLocalPlaylist(String str, ArrayList<Track> arrayList) {
        ArrayList songsIdOfPlaylist = getSongsIdOfPlaylist(str);
        int size = arrayList.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            if (songsIdOfPlaylist.contains(((Track) arrayList.get(i2)).getBusinessObjId())) {
                arrayList.remove(i2);
            }
        }
        if (arrayList.size() == 0) {
            return PLAYLIST_STATUS.ALREADY_ADDED;
        }
        size = 1 + songsIdOfPlaylist.size();
        ContentValues[] contentValuesArr = new ContentValues[arrayList.size()];
        while (i < arrayList.size()) {
            contentValuesArr[i] = new ContentValues();
            int i3 = size + 1;
            contentValuesArr[i].put("play_order", Integer.valueOf(size));
            contentValuesArr[i].put("audio_id", Integer.valueOf(Integer.parseInt(((Track) arrayList.get(i)).getBusinessObjId())));
            i++;
            size = i3;
        }
        Uri contentUri = Members.getContentUri("external", Long.parseLong(str));
        ContentResolver contentResolver = this.mContext.getContentResolver();
        contentResolver.bulkInsert(contentUri, contentValuesArr);
        contentResolver.notifyChange(Uri.parse("content://media"), null);
        return PLAYLIST_STATUS.SUCCESS;
    }

    public PLAYLIST_STATUS deleteTracksFromMediaStorePlaylist(String str, ArrayList<Track> arrayList) {
        Uri contentUri = Members.getContentUri("external", Long.parseLong(str));
        ContentResolver contentResolver = this.mContext.getContentResolver();
        String str2 = "";
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Track track = (Track) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(",");
            stringBuilder.append(track.getBusinessObjId());
            str2 = stringBuilder.toString();
        }
        String replaceFirst = str2.replaceFirst(",", "");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("audio_id IN (");
        stringBuilder2.append(replaceFirst);
        stringBuilder2.append(")");
        contentResolver.delete(contentUri, stringBuilder2.toString(), null);
        contentResolver.notifyChange(Uri.parse("content://media"), null);
        return PLAYLIST_STATUS.SUCCESS;
    }

    private void addToLocalPlaylist(Uri uri, ArrayList<Track> arrayList) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Iterator it = PlaylistSyncManager.getInstance().removeGaanaTrack(arrayList).iterator();
        int i = 1;
        while (it.hasNext()) {
            Track track = (Track) it.next();
            ContentValues contentValues = new ContentValues();
            int i2 = i + 1;
            contentValues.put("play_order", Integer.valueOf(i));
            contentValues.put("audio_id", track.getBusinessObjId());
            contentResolver.insert(uri, contentValues);
            i = i2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0099  */
    public void initMediaIdHashMapping() {
        /*
        r8 = this;
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = 5;
        r4 = new java.lang.String[r1];
        r1 = "_id";
        r2 = 0;
        r4[r2] = r1;
        r1 = "title";
        r2 = 1;
        r4[r2] = r1;
        r1 = "artist";
        r2 = 2;
        r4[r2] = r1;
        r1 = "album";
        r2 = 3;
        r4[r2] = r1;
        r1 = "duration";
        r2 = 4;
        r4[r2] = r1;
        r7 = "title";
        r5 = "mime_type NOT NULL AND is_music != 0";
        r1 = 0;
        r2 = r8.mContentResolver;	 Catch:{ Exception -> 0x009d, all -> 0x0095 }
        r3 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x009d, all -> 0x0095 }
        r6 = 0;
        r2 = r2.query(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x009d, all -> 0x0095 }
        r1 = r2.moveToFirst();	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        if (r1 == 0) goto L_0x008e;
    L_0x0035:
        r1 = "_id";
        r1 = r2.getColumnIndex(r1);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r1 = r2.getString(r1);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r3 = "title";
        r3 = r2.getColumnIndex(r3);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r3 = r2.getString(r3);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r4 = "artist";
        r4 = r2.getColumnIndex(r4);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r4 = r2.getString(r4);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r5 = "album";
        r5 = r2.getColumnIndex(r5);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r2.getString(r5);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r5 = "duration";
        r5 = r2.getColumnIndex(r5);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r5 = r2.getString(r5);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r6 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        if (r6 != 0) goto L_0x0074;
    L_0x006c:
        r6 = "<unknown>";
        r6 = r4.equalsIgnoreCase(r6);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        if (r6 == 0) goto L_0x0081;
    L_0x0074:
        r4 = r8.mContext;	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r4 = r4.getResources();	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r6 = 2131822799; // 0x7f1108cf float:1.927838E38 double:1.053260408E-314;
        r4 = r4.getString(r6);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
    L_0x0081:
        r3 = com.utilities.Util.a(r3, r5, r4);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r0.put(r3, r1);	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        r1 = r2.moveToNext();	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        if (r1 != 0) goto L_0x0035;
    L_0x008e:
        mediaIdAndHashMapping = r0;	 Catch:{ Exception -> 0x009e, all -> 0x0093 }
        if (r2 == 0) goto L_0x00a3;
    L_0x0092:
        goto L_0x00a0;
    L_0x0093:
        r0 = move-exception;
        goto L_0x0097;
    L_0x0095:
        r0 = move-exception;
        r2 = r1;
    L_0x0097:
        if (r2 == 0) goto L_0x009c;
    L_0x0099:
        r2.close();
    L_0x009c:
        throw r0;
    L_0x009d:
        r2 = r1;
    L_0x009e:
        if (r2 == 0) goto L_0x00a3;
    L_0x00a0:
        r2.close();
    L_0x00a3:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.initMediaIdHashMapping():void");
    }

    public Playlist getRecentlyAddedPlaylist() {
        if (this.songCount <= 0) {
            return null;
        }
        Playlist playlist = new Playlist();
        playlist.setPlaylistId(PLYALIST_RECENTLY_ADDED_ID);
        playlist.setName(this.mContext.getString(R.string.recently_added));
        playlist.setLocalMedia(true);
        playlist.setBusinessObjType(BusinessObjectType.Playlists);
        playlist.setCreatedbyUserId("-1");
        return playlist;
    }

    public Playlist getMostHeardPlaylist() {
        Playlist playlist = new Playlist();
        playlist.setPlaylistId(PLYALIST_MOST_HEARD_ID);
        playlist.setName(this.mContext.getString(R.string.most_listen));
        playlist.setLocalMedia(true);
        return playlist;
    }

    public String getMediaId(String str) {
        return (String) mediaIdAndHashMapping.get(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00f3 A:{Splitter:B:3:0x0059, ExcHandler: all (th java.lang.Throwable)} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:10:0x00eb, code skipped:
            r12 = r9;
     */
    /* JADX WARNING: Missing block: B:12:0x00ed, code skipped:
            if (r0 != null) goto L_0x00ef;
     */
    /* JADX WARNING: Missing block: B:13:0x00ef, code skipped:
            r0.close();
     */
    /* JADX WARNING: Missing block: B:14:0x00f3, code skipped:
            r12 = th;
     */
    /* JADX WARNING: Missing block: B:22:0x0100, code skipped:
            if (r0 == null) goto L_0x0103;
     */
    /* JADX WARNING: Missing block: B:24:0x0103, code skipped:
            return r12;
     */
    public com.gaana.models.Tracks.Track getLocalTrackFromHash(java.lang.String r12) {
        /*
        r11 = this;
        r12 = r11.getMediaId(r12);
        r0 = 10;
        r3 = new java.lang.String[r0];
        r0 = "_data";
        r1 = 0;
        r3[r1] = r0;
        r0 = "_id";
        r7 = 1;
        r3[r7] = r0;
        r0 = "title";
        r1 = 2;
        r3[r1] = r0;
        r0 = "_display_name";
        r1 = 3;
        r3[r1] = r0;
        r0 = "artist";
        r1 = 4;
        r3[r1] = r0;
        r0 = "artist_id";
        r1 = 5;
        r3[r1] = r0;
        r0 = "album";
        r1 = 6;
        r3[r1] = r0;
        r0 = "album_id";
        r1 = 7;
        r3[r1] = r0;
        r0 = "mime_type";
        r1 = 8;
        r3[r1] = r0;
        r0 = "duration";
        r1 = 9;
        r3[r1] = r0;
        r6 = "title";
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "mime_type NOT NULL AND is_music != 0 AND _id=";
        r0.append(r1);
        r0.append(r12);
        r4 = r0.toString();
        r12 = 0;
        r1 = r11.mContentResolver;	 Catch:{ Exception -> 0x00ff, all -> 0x00f5 }
        r2 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x00ff, all -> 0x00f5 }
        r5 = 0;
        r0 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x00ff, all -> 0x00f5 }
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        if (r1 == 0) goto L_0x00ed;
    L_0x005f:
        r1 = "_id";
        r1 = r0.getColumnIndex(r1);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r1 = r0.getString(r1);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r2 = "title";
        r2 = r0.getColumnIndex(r2);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r2 = r0.getString(r2);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r3 = "_display_name";
        r3 = r0.getColumnIndex(r3);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r0.getString(r3);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r3 = "mime_type";
        r3 = r0.getColumnIndex(r3);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r0.getString(r3);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r3 = "artist";
        r3 = r0.getColumnIndex(r3);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r3 = r0.getString(r3);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r4 = "artist_id";
        r4 = r0.getColumnIndex(r4);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r4 = r0.getString(r4);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r5 = "album";
        r5 = r0.getColumnIndex(r5);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r5 = r0.getString(r5);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r6 = "album_id";
        r6 = r0.getColumnIndex(r6);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r6 = r0.getString(r6);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r8 = "_data";
        r8 = r0.getColumnIndex(r8);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r0.getString(r8);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r8 = "duration";
        r8 = r0.getColumnIndex(r8);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r8 = r0.getString(r8);	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r9 = new com.gaana.models.LocalTrack;	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r9.<init>();	 Catch:{ Exception -> 0x0100, all -> 0x00f3 }
        r9.setBusinessObjId(r1);	 Catch:{ Exception -> 0x00eb, all -> 0x00f3 }
        r9.setName(r2);	 Catch:{ Exception -> 0x00eb, all -> 0x00f3 }
        r9.setTracktitle(r2);	 Catch:{ Exception -> 0x00eb, all -> 0x00f3 }
        r12 = com.managers.URLManager.BusinessObjectType.Tracks;	 Catch:{ Exception -> 0x00eb, all -> 0x00f3 }
        r9.setBusinessObjType(r12);	 Catch:{ Exception -> 0x00eb, all -> 0x00f3 }
        r9.setAlbumName(r5);	 Catch:{ Exception -> 0x00eb, all -> 0x00f3 }
        r12 = r11.getTrackArtist(r3, r4);	 Catch:{ Exception -> 0x00eb, all -> 0x00f3 }
        r9.setArtist(r12);	 Catch:{ Exception -> 0x00eb, all -> 0x00f3 }
        r9.setAlbumId(r6);	 Catch:{ Exception -> 0x00eb, all -> 0x00f3 }
        r9.setLocalMedia(r7);	 Catch:{ Exception -> 0x00eb, all -> 0x00f3 }
        r9.setArtwork(r6);	 Catch:{ Exception -> 0x00eb, all -> 0x00f3 }
        r9.setDuration(r8);	 Catch:{ Exception -> 0x00eb, all -> 0x00f3 }
        r12 = r9;
        goto L_0x00ed;
    L_0x00eb:
        r12 = r9;
        goto L_0x0100;
    L_0x00ed:
        if (r0 == 0) goto L_0x0103;
    L_0x00ef:
        r0.close();
        goto L_0x0103;
    L_0x00f3:
        r12 = move-exception;
        goto L_0x00f9;
    L_0x00f5:
        r0 = move-exception;
        r10 = r0;
        r0 = r12;
        r12 = r10;
    L_0x00f9:
        if (r0 == 0) goto L_0x00fe;
    L_0x00fb:
        r0.close();
    L_0x00fe:
        throw r12;
    L_0x00ff:
        r0 = r12;
    L_0x0100:
        if (r0 == 0) goto L_0x0103;
    L_0x0102:
        goto L_0x00ef;
    L_0x0103:
        return r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getLocalTrackFromHash(java.lang.String):com.gaana.models.Tracks$Track");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002b  */
    public int getLocalTrackCounts() {
        /*
        r8 = this;
        r0 = 1;
        r3 = new java.lang.String[r0];
        r0 = "_id";
        r1 = 0;
        r3[r1] = r0;
        r4 = "mime_type NOT NULL AND is_music != 0";
        r0 = 0;
        r1 = r8.mContentResolver;	 Catch:{ Exception -> 0x002f, all -> 0x0028 }
        r2 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x002f, all -> 0x0028 }
        r5 = 0;
        r6 = 0;
        r1 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x002f, all -> 0x0028 }
        r0 = r1.getCount();	 Catch:{ Exception -> 0x0026, all -> 0x0021 }
        r8.songCount = r0;	 Catch:{ Exception -> 0x0026, all -> 0x0021 }
        if (r1 == 0) goto L_0x0034;
    L_0x001d:
        r1.close();
        goto L_0x0034;
    L_0x0021:
        r0 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x0029;
    L_0x0026:
        r0 = r1;
        goto L_0x002f;
    L_0x0028:
        r1 = move-exception;
    L_0x0029:
        if (r0 == 0) goto L_0x002e;
    L_0x002b:
        r0.close();
    L_0x002e:
        throw r1;
    L_0x002f:
        if (r0 == 0) goto L_0x0034;
    L_0x0031:
        r0.close();
    L_0x0034:
        r0 = r8.songCount;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getLocalTrackCounts():int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0028  */
    public int getLocalPlaylistCounts() {
        /*
        r9 = this;
        r0 = 1;
        r3 = new java.lang.String[r0];
        r0 = "name";
        r7 = 0;
        r3[r7] = r0;
        r0 = 0;
        r1 = r9.mContentResolver;	 Catch:{ Exception -> 0x002c, all -> 0x0025 }
        r2 = android.provider.MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;	 Catch:{ Exception -> 0x002c, all -> 0x0025 }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r1 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x002c, all -> 0x0025 }
        r0 = r1.getCount();	 Catch:{ Exception -> 0x0023, all -> 0x001e }
        if (r1 == 0) goto L_0x0032;
    L_0x001a:
        r1.close();
        goto L_0x0032;
    L_0x001e:
        r0 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0026;
    L_0x0023:
        r0 = r1;
        goto L_0x002c;
    L_0x0025:
        r1 = move-exception;
    L_0x0026:
        if (r0 == 0) goto L_0x002b;
    L_0x0028:
        r0.close();
    L_0x002b:
        throw r1;
    L_0x002c:
        if (r0 == 0) goto L_0x0031;
    L_0x002e:
        r0.close();
    L_0x0031:
        r0 = r7;
    L_0x0032:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.localmedia.LocalMediaManager.getLocalPlaylistCounts():int");
    }

    public BusinessObject getActivity() {
        return LocalMediaActivityDbHelper.getInstance().getActivity();
    }

    private NextGenSearchAutoSuggests convertToAutoSuggest(BusinessObject businessObject, boolean z, boolean z2) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
        for (int i = 0; i < arrListBusinessObj.size(); i++) {
            BusinessObject businessObject2 = (BusinessObject) arrListBusinessObj.get(i);
            if (businessObject2 != null) {
                String artistName;
                AutoComplete autoComplete;
                String str;
                AutoComplete autoComplete2;
                switch (businessObject2.getBusinessObjType()) {
                    case Tracks:
                        OfflineTrack offlineTrack = (OfflineTrack) businessObject2;
                        artistName = offlineTrack.getArtistName();
                        if (z2) {
                            artistName = this.mContext.getResources().getString(R.string.song_text).trim();
                        }
                        autoComplete = new AutoComplete(businessObject2.getName(), artistName, Integer.parseInt(businessObject2.getBusinessObjId()), offlineTrack.getImageUrl());
                        autoComplete.setLocalMedia(businessObject2.isLocalMedia());
                        autoComplete.setType("Track");
                        if (z) {
                            autoComplete.setSectionType("MY_DOWNLOADS");
                        } else {
                            autoComplete.setSectionType("LOCAL_MUSIC");
                        }
                        arrayList.add(autoComplete);
                        break;
                    case Albums:
                        Album album = (Album) businessObject2;
                        artistName = album.getArtistNames();
                        if (z2) {
                            artistName = this.mContext.getResources().getString(R.string.album_text).trim();
                        }
                        autoComplete = new AutoComplete(businessObject2.getName(), artistName, Integer.parseInt(businessObject2.getBusinessObjId()), album.getArtwork());
                        autoComplete.setLocalMedia(businessObject2.isLocalMedia());
                        autoComplete.setType("Album");
                        if (z) {
                            autoComplete.setSectionType("MY_DOWNLOADS");
                        } else {
                            autoComplete.setSectionType("LOCAL_MUSIC");
                        }
                        arrayList2.add(autoComplete);
                        break;
                    case Artists:
                        str = "";
                        if (z2) {
                            str = this.mContext.getResources().getString(R.string.artist);
                        }
                        autoComplete2 = new AutoComplete(businessObject2.getName(), str, Integer.parseInt(businessObject2.getBusinessObjId()), ((Artists.Artist) businessObject2).getArtwork());
                        autoComplete2.setLocalMedia(businessObject2.isLocalMedia());
                        autoComplete2.setType("Artist");
                        autoComplete2.setSectionType("LOCAL_MUSIC");
                        arrayList4.add(autoComplete2);
                        break;
                    case Playlists:
                        str = "";
                        if (z2) {
                            str = this.mContext.getResources().getString(R.string.playlist_text);
                            if ((businessObject2 instanceof Playlist) && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) businessObject2)) {
                                str = this.mContext.getResources().getString(R.string.my_playlist);
                            }
                        }
                        autoComplete2 = new AutoComplete(businessObject2.getName(), str, Integer.parseInt(businessObject2.getBusinessObjId()), ((Playlist) businessObject2).getArtwork());
                        autoComplete2.setLocalMedia(businessObject2.isLocalMedia());
                        autoComplete2.setType("Playlist");
                        if (z) {
                            autoComplete2.setSectionType("MY_DOWNLOADS");
                        } else {
                            autoComplete2.setSectionType("LOCAL_MUSIC");
                        }
                        arrayList3.add(autoComplete2);
                        break;
                    default:
                        break;
                }
            }
        }
        Object obj5 = null;
        if (z2) {
            ArrayList arrayList5 = new ArrayList();
            if (arrayList.size() > 0) {
                arrayList5.addAll(arrayList);
            }
            if (arrayList2.size() > 0) {
                arrayList5.addAll(arrayList2);
            }
            if (arrayList3.size() > 0) {
                arrayList5.addAll(arrayList3);
            }
            if (arrayList4.size() > 0) {
                arrayList5.addAll(arrayList4);
            }
            GroupItem groupItem = new GroupItem();
            if (z) {
                groupItem.setType(MY_MUSIC);
            } else {
                groupItem.setType("Local Files");
            }
            Collections.sort(arrayList5, new Comparator<AutoComplete>() {
                public int compare(AutoComplete autoComplete, AutoComplete autoComplete2) {
                    return autoComplete.getEnglishTitle().toLowerCase().compareTo(autoComplete2.getEnglishTitle().toLowerCase());
                }
            });
            groupItem.setLocalMedia(true);
            groupItem.setAutocomplete(arrayList5);
            obj = null;
            obj2 = obj;
            obj3 = groupItem;
            obj4 = obj2;
        } else {
            if (arrayList.size() > 0) {
                obj = new GroupItem();
                obj.setType("Track");
                obj.setLocalMedia(true);
                obj.setAutocomplete(arrayList);
            } else {
                obj = null;
            }
            if (arrayList2.size() > 0) {
                obj2 = new GroupItem();
                obj2.setType("Album");
                obj2.setLocalMedia(true);
                obj2.setAutocomplete(arrayList2);
            } else {
                obj2 = null;
            }
            if (arrayList3.size() > 0) {
                obj4 = new GroupItem();
                obj4.setType("Playlist");
                obj4.setLocalMedia(true);
                obj4.setAutocomplete(arrayList3);
            } else {
                obj4 = null;
            }
            if (arrayList4.size() > 0) {
                GroupItem groupItem2 = new GroupItem();
                groupItem2.setType("Artist");
                groupItem2.setLocalMedia(true);
                groupItem2.setAutocomplete(arrayList4);
                GroupItem groupItem3 = groupItem2;
                obj3 = null;
                obj5 = groupItem3;
            } else {
                obj3 = null;
            }
        }
        arrayList3 = new ArrayList();
        if (obj != null) {
            arrayList3.add(obj);
        }
        if (obj2 != null) {
            arrayList3.add(obj2);
        }
        if (obj4 != null) {
            arrayList3.add(obj4);
        }
        if (obj5 != null) {
            arrayList3.add(obj5);
        }
        if (obj3 != null) {
            arrayList3.add(obj3);
        }
        NextGenSearchAutoSuggests nextGenSearchAutoSuggests = new NextGenSearchAutoSuggests();
        nextGenSearchAutoSuggests.setGroupItems(arrayList3);
        return nextGenSearchAutoSuggests;
    }

    public PLAYLIST_STATUS reOrderMediaStorePlaylist(String str, ArrayList<Track> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return PLAYLIST_STATUS.SUCCESS;
        }
        Uri contentUri = Members.getContentUri("external", Long.parseLong(str));
        ContentResolver contentResolver = this.mContext.getContentResolver();
        int i = 1;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (!((Track) arrayList.get(size)).isMarkedForDeletionFromPlaylist()) {
                ContentValues contentValues = new ContentValues();
                int i2 = i + 1;
                contentValues.put("play_order", Integer.valueOf(i));
                contentResolver.update(contentUri, contentValues, "audio_id=?", new String[]{r4.getBusinessObjId()});
                i = i2;
            }
        }
        contentResolver.notifyChange(Uri.parse("content://media"), null);
        return PLAYLIST_STATUS.SUCCESS;
    }

    public PLAYLIST_STATUS updateLocalPlaylistName(String str, String str2) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        String[] strArr = new String[]{str};
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str2);
        contentResolver.update(Playlists.EXTERNAL_CONTENT_URI, contentValues, "_id = ?", strArr);
        contentResolver.notifyChange(Uri.parse("content://media"), null);
        return PLAYLIST_STATUS.SUCCESS;
    }
}
