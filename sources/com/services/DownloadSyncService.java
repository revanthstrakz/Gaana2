package com.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.constants.Constants;
import com.e.a.e.c;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.login.UserInfo;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.DownloadSyncArrays;
import com.gaana.models.DownloadSyncArrays.DownloadSyncArray;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.GsonBuilder;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.managers.ap;
import com.managers.l;
import com.managers.o;
import com.managers.u;
import com.models.ListingButton;
import com.utilities.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DownloadSyncService extends IntentService {
    private final String a = "DownloadSyncService";
    private boolean b = false;
    private d c;
    private Context d;
    private GaanaApplication e;

    private void a(String str, String str2) {
    }

    public DownloadSyncService() {
        super(DownloadSyncService.class.getName());
    }

    public void onCreate() {
        super.onCreate();
        this.d = getApplicationContext();
        this.e = (GaanaApplication) GaanaApplication.getContext();
        this.c = d.a();
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARNING: Missing block: B:20:0x005a, code skipped:
            if (r6.equals("NORMAL_SYNC") == false) goto L_0x0067;
     */
    /* JADX WARNING: Missing block: B:37:0x0091, code skipped:
            return;
     */
    public void onHandleIntent(android.content.Intent r6) {
        /*
        r5 = this;
        r0 = com.gaana.application.GaanaApplication.getContext();
        r0 = com.utilities.Util.j(r0);
        if (r0 == 0) goto L_0x0091;
    L_0x000a:
        r0 = com.gaana.application.GaanaApplication.getInstance();
        r0 = r0.isAppInOfflineMode();
        if (r0 == 0) goto L_0x0016;
    L_0x0014:
        goto L_0x0091;
    L_0x0016:
        r0 = com.managers.ap.a();
        r0 = r0.o();
        if (r0 != 0) goto L_0x0021;
    L_0x0020:
        return;
    L_0x0021:
        r0 = r5.e();
        if (r0 == 0) goto L_0x0028;
    L_0x0027:
        return;
    L_0x0028:
        r0 = r5.b();
        if (r0 != 0) goto L_0x0039;
    L_0x002e:
        r0 = com.managers.ap.a();
        r0 = r0.m();
        if (r0 != 0) goto L_0x0039;
    L_0x0038:
        return;
    L_0x0039:
        r0 = "isForcedFullSync";
        r1 = 0;
        r0 = r6.getBooleanExtra(r0, r1);
        r6 = r6.getAction();
        r2 = -1;
        r3 = r6.hashCode();
        r4 = 1139143243; // 0x43e5f24b float:459.8929 double:5.62811542E-315;
        if (r3 == r4) goto L_0x005d;
    L_0x004e:
        r4 = 1921356531; // 0x728592f3 float:5.291412E30 double:9.492762554E-315;
        if (r3 == r4) goto L_0x0054;
    L_0x0053:
        goto L_0x0067;
    L_0x0054:
        r3 = "NORMAL_SYNC";
        r6 = r6.equals(r3);
        if (r6 == 0) goto L_0x0067;
    L_0x005c:
        goto L_0x0068;
    L_0x005d:
        r1 = "FULL_SYNC";
        r6 = r6.equals(r1);
        if (r6 == 0) goto L_0x0067;
    L_0x0065:
        r1 = 1;
        goto L_0x0068;
    L_0x0067:
        r1 = r2;
    L_0x0068:
        switch(r1) {
            case 0: goto L_0x0070;
            case 1: goto L_0x006c;
            default: goto L_0x006b;
        };
    L_0x006b:
        goto L_0x0090;
    L_0x006c:
        r5.d();
        goto L_0x0090;
    L_0x0070:
        r6 = r5.c();
        if (r6 != 0) goto L_0x0078;
    L_0x0076:
        if (r0 == 0) goto L_0x0090;
    L_0x0078:
        r6 = com.constants.Constants.b;
        if (r6 == 0) goto L_0x008d;
    L_0x007c:
        if (r0 == 0) goto L_0x0086;
    L_0x007e:
        r6 = "DownloadSyncService";
        r1 = "User wants full sync. Let's do it.";
        android.util.Log.d(r6, r1);
        goto L_0x008d;
    L_0x0086:
        r6 = "DownloadSyncService";
        r1 = "Download sync time has been expired. try syncing downloads now";
        android.util.Log.d(r6, r1);
    L_0x008d:
        r5.b(r0);
    L_0x0090:
        return;
    L_0x0091:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.services.DownloadSyncService.onHandleIntent(android.content.Intent):void");
    }

    private boolean c() {
        if (!ap.a().j()) {
            return false;
        }
        if (ap.a().m()) {
            return true;
        }
        if (!this.c.b("PREFERENCE_KEY_SETTINGS_AUTO_SYNC_V5", false, true)) {
            return false;
        }
        String c = this.c.c("LAST_DOWNLOAD_SYNC_TIME_LOCAL", true);
        if (TextUtils.isEmpty(c)) {
            return true;
        }
        if (((int) ((new Date().getTime() - Long.parseLong(c)) / 1000)) > 0) {
            return true;
        }
        return false;
    }

    private void d() {
        a(true);
        LocalBroadcastManager.getInstance(this.d.getApplicationContext()).sendBroadcast(new Intent("intent_download_sync_progress_update"));
        try {
            i b = new j().b(a("https://api.gaana.com/gaanaplusservice.php?type=download_sync&subtype=fullSync"));
            if (b.b().booleanValue()) {
                b(b.a());
                LocalBroadcastManager.getInstance(this.d.getApplicationContext()).sendBroadcast(new Intent("intent_download_sync_progress_update"));
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void a(boolean z) {
        l.a().b(z);
        this.b = z;
    }

    private boolean e() {
        return this.b;
    }

    private void b(boolean z) {
        Intent intent;
        a(this.d.getString(R.string.syncing_download), this.d.getString(R.string.syncing_in_progress));
        if (Constants.b) {
            Log.d("DownloadSyncService", "Starting download syncing with server. ");
        }
        a(true);
        LocalBroadcastManager.getInstance(this.d.getApplicationContext()).sendBroadcast(new Intent("intent_download_sync_progress_update"));
        String c = c(z);
        j jVar = new j();
        try {
            String a = a("https://api.gaana.com/gaanaplusservice.php?type=download_sync&subtype=entitySync");
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("file_string", c));
            b(jVar.a(a, arrayList));
            a(false);
            intent = new Intent("intent_download_sync_progress_update");
        } catch (IllegalArgumentException e) {
            ThrowableExtension.printStackTrace(e);
            a(false);
            intent = new Intent("intent_download_sync_progress_update");
        } catch (IOException e2) {
            ThrowableExtension.printStackTrace(e2);
            a(false);
            intent = new Intent("intent_download_sync_progress_update");
        } catch (Throwable th) {
            a(false);
            LocalBroadcastManager.getInstance(this.d.getApplicationContext()).sendBroadcast(new Intent("intent_download_sync_progress_update"));
        }
        LocalBroadcastManager.getInstance(this.d.getApplicationContext()).sendBroadcast(intent);
    }

    private String c(boolean z) {
        String a = a();
        if (a.equals("7001") || z) {
            return f();
        }
        DownloadSyncArrays b = l.a().b();
        b.setLastSyncTime(a);
        DownloadSyncArray deletedArray = b.getDeletedArray();
        ArrayList arrListPlaylists = deletedArray.getArrListPlaylists();
        if (arrListPlaylists.remove(String.valueOf(-100))) {
            arrListPlaylists.add("playlist_favourite");
        }
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.accumulate("downloaded_albums", null);
            jSONObject2.accumulate("downloaded_playlists", null);
            jSONObject2.accumulate("downloaded_tracks", null);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.accumulate("downloaded_albums", new JSONArray(deletedArray.getArrListAlbums()));
            jSONObject3.accumulate("downloaded_playlists", new JSONArray(arrListPlaylists));
            jSONObject3.accumulate("downloaded_tracks", new JSONArray(deletedArray.getArrListTracks()));
            jSONObject.accumulate("deleted_items", jSONObject3);
            jSONObject.accumulate("last_sync_time", a);
            jSONObject.accumulate("added_items", jSONObject2);
            jSONObject.accumulate("sync_required", "YES");
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject.toString();
    }

    public String a() {
        return this.c.b("LAST_DOWNLOAD_SYNC_TIME_SERVER", "7001", true);
    }

    private String f() {
        ArrayList a = DownloadManager.c().a(BusinessObjectType.Albums);
        ArrayList a2 = DownloadManager.c().a(BusinessObjectType.Playlists);
        if (a2.remove(String.valueOf(-100)) && d.a().b("FAVORITE_SONGS_DOWNLOADED", false, true)) {
            a2.add("playlist_favourite");
        }
        ArrayList a3 = DownloadManager.c().a(BusinessObjectType.Tracks);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.accumulate("last_sync_time", a());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.accumulate("downloaded_albums", new JSONArray(a));
            jSONObject2.accumulate("downloaded_playlists", new JSONArray(a2));
            jSONObject2.accumulate("downloaded_tracks", new JSONArray(a3));
            jSONObject.accumulate("added_items", jSONObject2);
            DownloadSyncArray deletedArray = l.a().b().getDeletedArray();
            a2 = deletedArray.getArrListPlaylists();
            if (a2.remove(String.valueOf(-100))) {
                a2.add("playlist_favourite");
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.accumulate("downloaded_albums", new JSONArray(deletedArray.getArrListAlbums()));
            jSONObject3.accumulate("downloaded_playlists", new JSONArray(a2));
            jSONObject3.accumulate("downloaded_tracks", new JSONArray(deletedArray.getArrListTracks()));
            jSONObject.accumulate("deleted_items", jSONObject3);
            jSONObject.accumulate("sync_required", "YES");
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject.toString();
    }

    private String a(String str) {
        UserInfo currentUser = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser();
        if (currentUser != null && currentUser.getLoginStatus()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&token=");
            stringBuilder.append(currentUser.getAuthToken());
            str = stringBuilder.toString();
        }
        return str.replace(" ", "%20");
    }

    private void b(String str) {
        if (str != null) {
            try {
                DownloadSyncArrays downloadSyncArrays = (DownloadSyncArrays) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(str, DownloadSyncArrays.class);
                if ("true".equalsIgnoreCase(downloadSyncArrays.getStatus())) {
                    if (Constants.b) {
                        DownloadManager.c().p();
                        DownloadManager.c().B();
                    }
                    String lastSyncTime = downloadSyncArrays.getLastSyncTime();
                    if (lastSyncTime != null) {
                        if (!lastSyncTime.equalsIgnoreCase("5001")) {
                            this.c.a("IS_DOWNLOAD_INFO_CHANGED_LOCALLY", false, true);
                            this.c.a("LAST_DOWNLOAD_SYNC_TIME_LOCAL", String.valueOf(System.currentTimeMillis()), true);
                            this.c.a("LAST_DOWNLOAD_SYNC_TIME_SERVER", lastSyncTime, true);
                            l.a().d();
                            if (downloadSyncArrays.isSyncRequired()) {
                                a(downloadSyncArrays);
                            } else {
                                a(false);
                                l.a().c(0, 0, this.d.getString(R.string.sync_completed));
                            }
                            l.a().a(false);
                            return;
                        }
                    }
                    l.a().c();
                    a(false);
                    l.a().c(0, 0, this.d.getString(R.string.error_in_syncing_download));
                    aj.a().a(this.d, this.d.getString(R.string.sync_error_download_msg));
                    return;
                }
                l.a().c();
                a(false);
                l.a().c(0, 0, this.d.getString(R.string.error_in_syncing_download));
                aj.a().a(this.d, this.d.getString(R.string.sync_error_download_msg));
                return;
            } catch (Exception unused) {
                a(false);
                return;
            }
        }
        l.a().c();
        a(false);
        l.a().c(0, 0, this.d.getString(R.string.error_in_syncing_download));
        aj.a().a(this.d, this.d.getString(R.string.sync_error_download_msg));
    }

    private void a(DownloadSyncArrays downloadSyncArrays) {
        if (downloadSyncArrays != null) {
            ArrayList arrListTracks;
            ArrayList smart_download;
            StringBuilder stringBuilder;
            StringBuilder stringBuilder2;
            StringBuilder stringBuilder3;
            int size;
            int size2;
            Intent intent;
            DownloadSyncArray addedArray = downloadSyncArrays.getAddedArray();
            DownloadSyncArray deletedArray = downloadSyncArrays.getDeletedArray();
            if (addedArray != null) {
                arrListTracks = addedArray.getArrListTracks();
                ArrayList arrListAlbums = addedArray.getArrListAlbums();
                ArrayList arrListPlaylists = addedArray.getArrListPlaylists();
                smart_download = downloadSyncArrays.getSmart_download();
                l.a().a(arrListTracks, arrListAlbums, arrListPlaylists);
                if (Constants.b) {
                    if (arrListAlbums != null) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Albums ids to be added in download: ");
                        stringBuilder.append(arrListAlbums.toString());
                        Log.d("DownloadSyncService", stringBuilder.toString());
                    }
                    if (arrListPlaylists != null) {
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Playlist ids to be added in download: ");
                        stringBuilder2.append(arrListPlaylists.toString());
                        Log.d("DownloadSyncService", stringBuilder2.toString());
                    }
                    if (arrListTracks != null) {
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Tracks ids to be added in download: ");
                        stringBuilder3.append(arrListTracks.toString());
                        Log.d("DownloadSyncService", stringBuilder3.toString());
                        size = arrListTracks.size();
                        l.a().b(arrListTracks);
                        a(smart_download);
                    }
                }
                size = 0;
                l.a().b(arrListTracks);
                a(smart_download);
            } else {
                size = 0;
            }
            if (deletedArray != null) {
                String str;
                smart_download = deletedArray.getArrListTracks();
                arrListTracks = deletedArray.getArrListAlbums();
                ArrayList arrListPlaylists2 = deletedArray.getArrListPlaylists();
                if (arrListTracks != null) {
                    if (Constants.b) {
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Albums ids to be deleted from download: ");
                        stringBuilder2.append(arrListTracks.toString());
                        Log.d("DownloadSyncService", stringBuilder2.toString());
                    }
                    if (this.e.getCurrentUser().getLoginStatus() && arrListTracks.size() > 0) {
                        String userId = this.e.getCurrentUser().getUserProfile().getUserId();
                        StringBuilder stringBuilder4 = new StringBuilder();
                        stringBuilder4.append("Deleted Albums - ");
                        stringBuilder4.append(arrListTracks.size());
                        u.a().a("DownloadSync", userId, stringBuilder4.toString());
                    }
                    Iterator it = arrListTracks.iterator();
                    while (it.hasNext()) {
                        str = (String) it.next();
                        DownloadManager.c().c(Integer.parseInt(str), false);
                        DownloadManager.c().d(Integer.parseInt(str));
                    }
                }
                if (arrListPlaylists2 != null) {
                    if (Constants.b) {
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Playlist ids to be deleted from download: ");
                        stringBuilder3.append(arrListPlaylists2.toString());
                        Log.d("DownloadSyncService", stringBuilder3.toString());
                    }
                    if (this.e.getCurrentUser().getLoginStatus() && arrListPlaylists2.size() > 0) {
                        String userId2 = this.e.getCurrentUser().getUserProfile().getUserId();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Deleted Playlists - ");
                        stringBuilder.append(arrListPlaylists2.size());
                        u.a().a("DownloadSync", userId2, stringBuilder.toString());
                    }
                    Iterator it2 = arrListPlaylists2.iterator();
                    while (it2.hasNext()) {
                        String str2 = (String) it2.next();
                        DownloadManager.c().c(Integer.parseInt(str2), false);
                        DownloadManager.c().d(Integer.parseInt(str2));
                    }
                }
                if (smart_download != null) {
                    if (Constants.b) {
                        StringBuilder stringBuilder5 = new StringBuilder();
                        stringBuilder5.append("Tracks ids to be deleted from download: ");
                        stringBuilder5.append(smart_download.toString());
                        Log.d("DownloadSyncService", stringBuilder5.toString());
                    }
                    if (this.e.getCurrentUser().getLoginStatus() && smart_download.size() > 0) {
                        str = this.e.getCurrentUser().getUserProfile().getUserId();
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Deleted Songs - ");
                        stringBuilder2.append(smart_download.size());
                        u.a().a("DownloadSync", str, stringBuilder2.toString());
                    }
                    DownloadManager.c().a(smart_download);
                    size2 = smart_download.size();
                    if (size > 0 || size2 > 0) {
                        DownloadManager.c().d();
                        intent = new Intent("intent_download_sync_completed");
                        intent.putExtra("EXTRA_KEY_ADDED_SONGS", size);
                        intent.putExtra("EXTRA_KEY_REMOVED_SONGS", size2);
                        this.d.sendBroadcast(intent);
                    }
                    a(false);
                    l.a().c(0, 0, this.d.getString(R.string.syncing_completed));
                }
            }
            size2 = 0;
            DownloadManager.c().d();
            intent = new Intent("intent_download_sync_completed");
            intent.putExtra("EXTRA_KEY_ADDED_SONGS", size);
            intent.putExtra("EXTRA_KEY_REMOVED_SONGS", size2);
            this.d.sendBroadcast(intent);
            a(false);
            l.a().c(0, 0, this.d.getString(R.string.syncing_completed));
        }
    }

    private void a(ArrayList<String> arrayList) {
        a(c.a);
        a(c.b);
        int a = l.a().a(c.c);
        int size = arrayList != null ? arrayList.size() : 0;
        l a2 = l.a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.d.getString(R.string.syncing_songs));
        stringBuilder.append(0);
        stringBuilder.append(" of ");
        stringBuilder.append(size + a);
        stringBuilder.append(")");
        a2.a(stringBuilder.toString());
        int i = 0;
        int i2 = 1;
        do {
            ArrayList b = l.a().b(c.c);
            if (b.size() < 30) {
                i2 = 0;
            }
            if (arrayList != null) {
                b.addAll(arrayList);
            }
            BusinessObject a3 = a(b, BusinessObjectType.Tracks);
            if (!(a3 == null || a3.getArrListBusinessObj() == null)) {
                ArrayList arrListBusinessObj = a3.getArrListBusinessObj();
                if (arrayList != null) {
                    Iterator it = arrListBusinessObj.iterator();
                    while (it.hasNext()) {
                        BusinessObject businessObject = (BusinessObject) it.next();
                        if (arrayList.contains(businessObject.getBusinessObjId())) {
                            ((Track) businessObject).setSmartDownload(1);
                        }
                    }
                }
                DownloadManager.c().b(arrListBusinessObj, -100, false);
                l.a().a(b);
                i += b.size();
                l a4 = l.a();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(this.d.getString(R.string.syncing_songs));
                stringBuilder2.append(i);
                stringBuilder2.append(" of ");
                stringBuilder2.append(a);
                stringBuilder2.append(")");
                a4.a(stringBuilder2.toString());
                continue;
            }
        } while (i2 != 0);
    }

    private void a(int i) {
        int a = l.a().a(i);
        BusinessObjectType businessObjectType = BusinessObjectType.Albums;
        String string = this.d.getString(R.string.albums);
        Object obj = 1;
        if (i == c.b) {
            businessObjectType = BusinessObjectType.Playlists;
            string = this.d.getString(R.string.playlists);
        }
        BusinessObjectType businessObjectType2 = businessObjectType;
        int i2 = 0;
        do {
            l a2 = l.a();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.d.getString(R.string.syncing));
            stringBuilder.append(string);
            stringBuilder.append(" (");
            stringBuilder.append(i2);
            stringBuilder.append(" of ");
            stringBuilder.append(a);
            stringBuilder.append(")");
            a2.a(stringBuilder.toString());
            ArrayList b = l.a().b(i);
            if (b.size() < 30) {
                obj = null;
            }
            BusinessObject a3 = a(b, businessObjectType2);
            if (!(a3 == null || a3.getArrListBusinessObj() == null)) {
                Iterator it = a3.getArrListBusinessObj().iterator();
                while (it.hasNext()) {
                    BusinessObject businessObject = (BusinessObject) it.next();
                    BusinessObject a4 = a(businessObject);
                    if (!(a4 == null || a4.getArrListBusinessObj() == null)) {
                        businessObject.setArrListBusinessObj(a4.getArrListBusinessObj());
                        DownloadStatus h = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
                        ArrayList b2 = b(a4.getArrListBusinessObj());
                        if (h == null) {
                            DownloadManager.c().a(businessObject, b2);
                        } else {
                            businessObject.setArrListBusinessObj(a4.getArrListBusinessObj());
                            Playlist playlist = (Playlist) businessObject;
                            Tracks tracks = (Tracks) a4;
                            playlist.setLastModifiedDate(tracks.getModifiedOn());
                            playlist.setFavoriteCount(tracks.getFavoriteCount());
                            DownloadManager.c().d(businessObject);
                        }
                        l.a().a(businessObject.getBusinessObjId(), a4.getArrListBusinessObj());
                        i2++;
                        l a5 = l.a();
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(this.d.getString(R.string.syncing));
                        stringBuilder2.append(string);
                        stringBuilder2.append(" (");
                        stringBuilder2.append(i2);
                        stringBuilder2.append(" of ");
                        stringBuilder2.append(a);
                        stringBuilder2.append(")");
                        a5.a(stringBuilder2.toString());
                    }
                }
            }
            if (i == c.b && b.contains("0")) {
                g();
                continue;
            }
        } while (obj != null);
    }

    private void g() {
        l.a().a(this.d.getString(R.string.syncing_favorite_songs));
        BusinessObject a = o.a().a(a("http://api.gaana.com/user.php?type=mysongs"), BusinessObjectType.Tracks);
        if (a != null && a.getArrListBusinessObj() != null) {
            this.c.a("FAVORITE_SONGS_DOWNLOADED", true, true);
            BusinessObject playlist = new Playlist();
            playlist.setPlaylistId(String.valueOf(-100));
            playlist.setArrListBusinessObj(a.getArrListBusinessObj());
            DownloadManager.c().a(playlist, b(a.getArrListBusinessObj()));
            l.a().a("0", a.getArrListBusinessObj());
        }
    }

    public BusinessObject a(BusinessObject businessObject) {
        try {
            URLManager c;
            StringBuilder stringBuilder;
            if (businessObject instanceof Album) {
                c = ((ListingButton) Constants.b().c().get(0)).c();
                stringBuilder = new StringBuilder();
                stringBuilder.append(c.k());
                stringBuilder.append(businessObject.getBusinessObjId());
                c.a(stringBuilder.toString());
            } else if (businessObject instanceof Playlist) {
                c = ((ListingButton) Constants.e().c().get(0)).c();
                stringBuilder = new StringBuilder();
                stringBuilder.append(c.k());
                stringBuilder.append("playlist_id=");
                stringBuilder.append(businessObject.getBusinessObjId());
                stringBuilder.append("&playlist_type=");
                stringBuilder.append(((Playlist) businessObject).getPlaylistType());
                String stringBuilder2 = stringBuilder.toString();
                if (((Playlist) businessObject).getAutomated() != null && ((Playlist) businessObject).getAutomated().equalsIgnoreCase("1")) {
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(stringBuilder2);
                    stringBuilder3.append("&automated=1");
                    stringBuilder2 = stringBuilder3.toString();
                }
                c.a(stringBuilder2);
            } else {
                c = null;
            }
            c.c(Boolean.valueOf(true));
            BusinessObject a = o.a().a(c);
            if (a != null && a.getVolleyError() == null) {
                ArrayList arrListBusinessObj = a.getArrListBusinessObj();
                if (arrListBusinessObj != null && arrListBusinessObj.size() != 0 && (businessObject instanceof Playlist) && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) businessObject)) {
                    for (int size = arrListBusinessObj.size() - 1; size >= 0; size--) {
                        Track track = (Track) arrListBusinessObj.get(size);
                        if (track.getIslocal() != null && track.getIslocal().equals("1")) {
                            track = LocalMediaManager.getInstance(this.d).getLocalTrackFromHash(track.getBusinessObjId());
                            arrListBusinessObj.remove(size);
                            if (track != null) {
                                arrListBusinessObj.add(size, track);
                            }
                        }
                    }
                }
            }
            return a;
        } catch (IllegalArgumentException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public BusinessObject a(ArrayList<String> arrayList, BusinessObjectType businessObjectType) {
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        String str;
        Iterator it;
        String str2;
        StringBuilder stringBuilder;
        if (businessObjectType == BusinessObjectType.Tracks) {
            str = "https://api.gaana.com/index.php?type=song&subtype=song_detail&track_id=";
            it = arrayList.iterator();
            while (it.hasNext()) {
                str2 = (String) it.next();
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(str2);
                stringBuilder.append(",");
                str = stringBuilder.toString();
            }
        } else if (businessObjectType == BusinessObjectType.Albums) {
            str = com.constants.c.t;
            it = arrayList.iterator();
            while (it.hasNext()) {
                str2 = (String) it.next();
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(str2);
                stringBuilder.append(",");
                str = stringBuilder.toString();
            }
        } else {
            str = null;
        }
        if (businessObjectType == BusinessObjectType.Playlists) {
            str = com.constants.c.u;
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                String str3 = (String) it2.next();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append(str3);
                stringBuilder2.append(",");
                str = stringBuilder2.toString();
            }
        }
        if (str == null || !str.contains(",")) {
            return null;
        }
        return o.a().a(str.substring(0, str.lastIndexOf(",")), businessObjectType);
    }

    public boolean b() {
        if (Util.k(GaanaApplication.getContext()) == 0) {
            return this.c.b("PREFERENCE_KEY_DOWNLOAD_SYNC_OVER_DATA_CONNECTION", false, true);
        }
        return true;
    }

    private ArrayList<String> b(ArrayList<BusinessObject> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        ArrayList e = l.a().e();
        if (e != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                BusinessObject businessObject = (BusinessObject) it.next();
                if (e.contains(businessObject.getBusinessObjId())) {
                    arrayList2.add(businessObject.getBusinessObjId());
                }
            }
        }
        return arrayList2;
    }
}
