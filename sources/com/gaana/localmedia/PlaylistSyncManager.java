package com.gaana.localmedia;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.constants.Constants;
import com.constants.c;
import com.e.a.f;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.EditPlaylistFragment;
import com.fragments.FavoritesFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.fragments.ListingFragment;
import com.fragments.LocalMediaFragment;
import com.fragments.MyMusicItemFragment;
import com.fragments.SongsSelectionFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.AppsFlyer;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.login.GaanaMiniSubDetails;
import com.gaana.login.LoginManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.LocalTrack;
import com.gaana.models.Playlists;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.GsonBuilder;
import com.library.managers.TaskManager.TaskListner;
import com.managers.DownloadManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.managers.ap;
import com.managers.l;
import com.managers.o;
import com.managers.u;
import com.services.d;
import com.services.h;
import com.services.i;
import com.services.j;
import com.services.l.s;
import com.utilities.Util;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlaylistSyncManager {
    private static final String TAG = "PlaylistSyncManager";
    private static PlaylistSyncManager mPlaylistSyncManager = null;
    public static boolean refreshFragment = false;
    private boolean isSyncingInProgress = false;
    private GaanaApplication mAppState = ((GaanaApplication) GaanaApplication.getContext());
    protected d mDeviceResManager = d.a();
    private PLAYLIST_STATUS playlistStatus;

    public enum PLAYLIST_STATUS {
        SUCCESS,
        FAILED,
        ALREADY_ADDED,
        PLAYLIST_ALREADY_ADDED,
        MAX_LIMIT_REACHED,
        PARTIALY_ADDED
    }

    enum SYNC_TYPE {
        ADD,
        DELETE
    }

    private PlaylistSyncManager() {
    }

    public static PlaylistSyncManager getInstance() {
        if (mPlaylistSyncManager == null) {
            mPlaylistSyncManager = new PlaylistSyncManager();
        }
        return mPlaylistSyncManager;
    }

    public void syncOnLogin() {
        com.i.d.a(new Runnable() {
            public void run() {
                PlaylistSyncManager.this.syncTracksOfPlaylist(PlaylistSyncManager.this.syncPlaylistsWithServer());
            }
        });
    }

    private Playlists syncPlaylistsWithServer() {
        this.isSyncingInProgress = true;
        Playlists myPlaylistsFromServer = getMyPlaylistsFromServer();
        if (!(myPlaylistsFromServer == null || myPlaylistsFromServer.getArrListBusinessObj() == null)) {
            Iterator it = myPlaylistsFromServer.getArrListBusinessObj().iterator();
            int i = 0;
            while (it.hasNext()) {
                Playlist playlist = (Playlist) it.next();
                long a = f.a().a(playlist.getBusinessObjId());
                if (a > 0) {
                    playlist.setOfflinePlaylistId(a);
                } else {
                    f.a().a(f.a().b(playlist), playlist.getBusinessObjId(), -4);
                }
                i++;
                if (myPlaylistsFromServer.getArrListBusinessObj().size() == i) {
                    this.mDeviceResManager.a("PREFERENCE_KEY_DB_INITIALIZED_WITH_PLAYLIST_NEW", true, true);
                }
            }
        }
        this.isSyncingInProgress = false;
        return myPlaylistsFromServer;
    }

    private void syncTracksOfPlaylist(Playlists playlists) {
        if (playlists != null && playlists.getArrListBusinessObj() != null) {
            this.isSyncingInProgress = true;
            Iterator it = playlists.getArrListBusinessObj().iterator();
            while (it.hasNext()) {
                Playlist playlist = (Playlist) it.next();
                if (f.a().g(playlist.getBusinessObjId()) == -4) {
                    BusinessObject a = l.a().a((BusinessObject) playlist);
                    if (!(a == null || a.getArrListBusinessObj() == null)) {
                        long a2 = f.a().a(playlist);
                        addToGaanaTable(playlist, a.getArrListBusinessObj());
                        f.a().a(a2, 1);
                        f.a().a(a2, playlist.getBusinessObjId(), 1);
                    }
                }
            }
            this.isSyncingInProgress = false;
        }
    }

    public void performSync() {
        if (!this.isSyncingInProgress) {
            com.i.d.a(new Runnable() {
                public void run() {
                    PlaylistSyncManager.this.startSync();
                }
            });
        }
    }

    private void startSync() {
        if (!this.isSyncingInProgress) {
            this.isSyncingInProgress = true;
            ArrayList d = f.a().d();
            if (d.size() > 0) {
                Iterator it = d.iterator();
                while (it.hasNext()) {
                    Playlist playlist = (Playlist) it.next();
                    int syncStatus = playlist.getSyncStatus();
                    if (syncStatus == 0) {
                        createPlaylistServer(playlist, playlist.getOfflinePlaylistId(), playlist.getLocalPlaylistId(), playlist.getName(), f.a().a(playlist, 0));
                    } else if (syncStatus == -1) {
                        ArrayList a = f.a().a(playlist, 0);
                        ArrayList a2 = f.a().a(playlist, -2);
                        long syncTime = playlist.getSyncTime();
                        if (a.size() == 0 && a2.size() == 0 && syncTime == 0) {
                            f.a().a(playlist.getOfflinePlaylistId(), playlist.getBusinessObjId(), 1);
                            return;
                        }
                        PLAYLIST_STATUS addDeleteSongsFromPlaylistServer;
                        PLAYLIST_STATUS playlist_status = PLAYLIST_STATUS.FAILED;
                        if (syncTime > 0) {
                            playlist.setChanged(true);
                            playlist.setArrList(getPlaylistDetails(playlist).getArrListBusinessObj());
                            addDeleteSongsFromPlaylistServer = addDeleteSongsFromPlaylistServer(playlist, a, a2);
                        } else {
                            addDeleteSongsFromPlaylistServer = addDeleteSongsFromPlaylistServer(playlist.getBusinessObjId(), playlist.getLocalPlaylistId(), a, a2);
                        }
                        if (addDeleteSongsFromPlaylistServer == PLAYLIST_STATUS.SUCCESS) {
                            f.a().a(playlist.getOfflinePlaylistId(), playlist.getBusinessObjId(), 1);
                            if (playlist.isPlaylistChanged()) {
                                playlist.setSyncTime(0);
                            }
                        }
                    } else if (syncStatus == -2) {
                        deletePlaylist(playlist);
                    }
                }
            } else {
                Log.d(TAG, "No playlist to sync");
            }
            this.isSyncingInProgress = false;
        }
    }

    private Playlists getMyPlaylistsFromServer() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/user.php?type=myplaylists&subtype=myplaylists");
        stringBuilder.append("&token=");
        stringBuilder.append(this.mAppState.getCurrentUser().getAuthToken());
        try {
            i a = new j().a(stringBuilder.toString(), true);
            if (a.b().booleanValue() && !TextUtils.isEmpty(a.a())) {
                Object obj = (Playlists) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(a.a(), Playlists.class);
                try {
                    if (!this.mAppState.checkAuthTokenStatus(obj) || obj == null || obj.getArrListBusinessObj() == null) {
                        return obj;
                    }
                    Iterator it = obj.getArrListBusinessObj().iterator();
                    while (it.hasNext()) {
                        ((Playlist) it.next()).setBusinessObjType(BusinessObjectType.Playlists);
                    }
                    return obj;
                } catch (Exception unused) {
                    return obj;
                }
            }
        } catch (Exception unused2) {
        }
        return null;
    }

    public void getMyPlaylistAsync(final s sVar, final boolean z) {
        com.i.d.a(new Runnable() {
            private Playlists myPlaylists = new Playlists();

            public void run() {
                this.myPlaylists.setArrListBusinessObj(PlaylistSyncManager.this.retrieveMyPlaylists(null, z));
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        sVar.onRetreivalComplete(AnonymousClass3.this.myPlaylists);
                    }
                });
            }
        });
    }

    public BusinessObject getMyPlaylist(boolean z) {
        Playlists playlists = new Playlists();
        playlists.setArrListBusinessObj(retrieveMyPlaylists(null, z));
        return playlists;
    }

    public ArrayList<BusinessObject> retrieveMyPlaylists(ArrayList<String> arrayList, boolean z) {
        ArrayList arrayList2;
        ArrayList<BusinessObject> arrayList3 = new ArrayList();
        if (this.mAppState.getCurrentUser().getLoginStatus()) {
            if (this.mAppState.isAppInOfflineMode() || !Util.j(GaanaApplication.getContext())) {
                arrayList3 = f.a().c();
            } else {
                startSync();
                Playlists syncPlaylistsWithServer = syncPlaylistsWithServer();
                if (syncPlaylistsWithServer == null || syncPlaylistsWithServer.getArrListBusinessObj() == null) {
                    arrayList3 = f.a().c();
                } else {
                    ArrayList<BusinessObject> arrListBusinessObj = syncPlaylistsWithServer.getArrListBusinessObj();
                    final Playlists playlists = new Playlists();
                    new ArrayList().addAll(syncPlaylistsWithServer.getArrListBusinessObj());
                    if (!this.isSyncingInProgress) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                new Thread(new Runnable() {
                                    public void run() {
                                        PlaylistSyncManager.this.syncTracksOfPlaylist(playlists);
                                    }
                                }).start();
                            }
                        });
                    }
                    arrayList3 = arrListBusinessObj;
                }
            }
            if (arrayList == null) {
                arrayList2 = new ArrayList();
            }
            if (arrayList3 != null && arrayList3.size() > 0) {
                Iterator it = arrayList3.iterator();
                while (it.hasNext()) {
                    String localPlaylistId = ((Playlist) ((BusinessObject) it.next())).getLocalPlaylistId();
                    if (!TextUtils.isEmpty(localPlaylistId)) {
                        arrayList2.add(localPlaylistId);
                    }
                }
            }
        }
        if (!z) {
            ArrayList<BusinessObject> mediaStorePlaylists = LocalMediaManager.getInstance(GaanaApplication.getContext()).getMediaStorePlaylists(arrayList2, null);
            if (mediaStorePlaylists != null) {
                if (arrayList3 == null) {
                    return mediaStorePlaylists;
                }
                arrayList3.addAll(mediaStorePlaylists);
            }
        }
        return arrayList3;
    }

    private boolean isContainGaanaSong(ArrayList<Track> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (!((Track) arrayList.get(i)).isLocalMedia()) {
                return true;
            }
        }
        return false;
    }

    private boolean isContainLocalSong(ArrayList<Track> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (((Track) arrayList.get(i)).isLocalMedia()) {
                return true;
            }
        }
        return false;
    }

    public PLAYLIST_STATUS createPlaylist(Playlist playlist, Activity activity, String str, ArrayList<Track> arrayList, boolean z) {
        GaanaActivity gaanaActivity;
        if (z || isContainGaanaSong(arrayList)) {
            long c = f.a().c(str);
            if (c == -2) {
                return PLAYLIST_STATUS.PLAYLIST_ALREADY_ADDED;
            }
            if (c == -1) {
                return PLAYLIST_STATUS.FAILED;
            }
            playlist.setOfflinePlaylistId(c);
            addToGaanaTable(playlist, arrayList);
            String str2 = "";
            if (isContainLocalSong(arrayList)) {
                str2 = String.valueOf(LocalMediaManager.getInstance(this.mAppState).createLocalPlaylist(str, arrayList));
                f.a().a(c, str2);
            }
            String str3 = str2;
            if (this.mAppState.isAppInOfflineMode() || !Util.j(this.mAppState)) {
                playlist.setPlaylistId(String.valueOf(0 - c));
                return PLAYLIST_STATUS.SUCCESS;
            }
            gaanaActivity = (GaanaActivity) activity;
            if (gaanaActivity.getCurrentFragment() instanceof ListingFragment) {
                gaanaActivity.setRefreshData(true);
            }
            return createPlaylistServer(playlist, c, str3, str, arrayList);
        }
        LocalMediaManager.getInstance(this.mAppState).createLocalPlaylist(str, arrayList);
        gaanaActivity = (GaanaActivity) activity;
        if (gaanaActivity.getCurrentFragment() instanceof ListingFragment) {
            gaanaActivity.setRefreshData(true);
        }
        return PLAYLIST_STATUS.SUCCESS;
    }

    public void addToGaanaTable(Playlist playlist, ArrayList<Track> arrayList) {
        f.a().a(playlist, (ArrayList) arrayList);
    }

    public ArrayList<?> getMyPlaylistsFromDb(boolean z) {
        if (this.mDeviceResManager.b("PREFERENCE_KEY_DB_INITIALIZED_WITH_PLAYLIST_NEW", false, false) && !z) {
            return f.a().c();
        }
        if (!this.mAppState.isAppInOfflineMode() && Util.j(GaanaApplication.getContext())) {
            Playlists syncPlaylistsWithServer = syncPlaylistsWithServer();
            if (!(syncPlaylistsWithServer == null || syncPlaylistsWithServer.getArrListBusinessObj() == null)) {
                final Playlists playlists = new Playlists();
                new ArrayList().addAll(syncPlaylistsWithServer.getArrListBusinessObj());
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        Thread thread = new Thread(new Runnable() {
                            public void run() {
                                PlaylistSyncManager.this.syncTracksOfPlaylist(playlists);
                            }
                        });
                    }
                });
                return syncPlaylistsWithServer.getArrListBusinessObj();
            }
        }
        return f.a().c();
    }

    public void getJukePlaylistDetailsAsync(final JukePlaylist jukePlaylist, @NonNull final s sVar) {
        com.i.d.a(new Runnable() {
            public void run() {
                Playlist playlist = new Playlist();
                playlist.setBusinessObjId(jukePlaylist.getBusinessObjId());
                Tracks playlistDetails = PlaylistSyncManager.this.getPlaylistDetails(playlist);
                if (playlistDetails != null) {
                    jukePlaylist.setArrList(JukeSessionManager.getJukeTrackList(playlistDetails.getArrListBusinessObj()));
                    sVar.onRetreivalComplete(jukePlaylist);
                    return;
                }
                sVar.onErrorResponse(jukePlaylist);
            }
        });
    }

    public Playlist getPlaylistDetails(String str) {
        return f.a().e(str);
    }

    public Tracks getPlaylistDetails(Playlist playlist) {
        return f.a().c(playlist);
    }

    private String getSuccessString(String str) {
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("Status")) {
                str2 = jSONObject.getString("Status");
            }
            if (jSONObject.has("status")) {
                str2 = jSONObject.getString("status");
            }
            if (str2.compareTo("1") == 0) {
                str = jSONObject.has("data") ? jSONObject.getString("data") : null;
                if (jSONObject.has("Success")) {
                    str = jSONObject.getString("Success");
                }
                if (jSONObject.has("Sucess")) {
                    str = jSONObject.getString("Sucess");
                }
                if (jSONObject.has("result")) {
                    str = jSONObject.getString("result");
                }
            } else {
                str = null;
            }
            this.mAppState.checkAuthTokenStatus(jSONObject);
            return str;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public PLAYLIST_STATUS addToPlaylistGaanaMini(Playlist playlist, ArrayList<Track> arrayList) {
        PLAYLIST_STATUS playlist_status = PLAYLIST_STATUS.FAILED;
        String localPlaylistId = playlist.getLocalPlaylistId();
        if (this.mAppState.isAppInOfflineMode() || !Util.j(GaanaApplication.getContext())) {
            return playlist_status;
        }
        int parseInt;
        Iterator it = this.mAppState.getCurrentUser().getUserSubscriptionData().getGaanaMiniSubDetails().iterator();
        while (it.hasNext()) {
            GaanaMiniSubDetails gaanaMiniSubDetails = (GaanaMiniSubDetails) it.next();
            if (gaanaMiniSubDetails.getEntityType().equals("AR") && playlist.getBusinessObjId().equals(gaanaMiniSubDetails.getPlaylistId())) {
                parseInt = Integer.parseInt(gaanaMiniSubDetails.getDownloadLimitCount());
                break;
            }
        }
        parseInt = 0;
        if (DownloadManager.c().j(Integer.parseInt(playlist.getBusinessObjId())) + arrayList.size() > parseInt) {
            return PLAYLIST_STATUS.MAX_LIMIT_REACHED;
        }
        playlist.setChanged(true);
        if (addDeleteSongsFromPlaylistServer(playlist.getBusinessObjId(), localPlaylistId, (ArrayList) arrayList, null) != PLAYLIST_STATUS.SUCCESS) {
            return playlist_status;
        }
        playlist.setChanged(false);
        addToGaanaTable(playlist, arrayList);
        f a = f.a();
        long offlinePlaylistId = playlist.getOfflinePlaylistId();
        f.a();
        a.a(offlinePlaylistId, 1);
        a = f.a();
        f.a();
        a.b(playlist, 1);
        return PLAYLIST_STATUS.SUCCESS;
    }

    public PLAYLIST_STATUS addToPlaylist(Activity activity, Playlist playlist, ArrayList<Track> arrayList) {
        PLAYLIST_STATUS playlist_status = PLAYLIST_STATUS.FAILED;
        String localPlaylistId = playlist.getLocalPlaylistId();
        boolean z = false;
        if (!playlist.isLocalMedia()) {
            boolean b = f.a().b(playlist, (ArrayList) arrayList);
            addToGaanaTable(playlist, arrayList);
            PLAYLIST_STATUS playlist_status2 = PLAYLIST_STATUS.SUCCESS;
            if (isContainLocalSong(arrayList)) {
                ArrayList removeGaanaTrack = removeGaanaTrack(arrayList);
                if (TextUtils.isEmpty(playlist.getLocalPlaylistId()) || playlist.getLocalPlaylistId().equals("0")) {
                    localPlaylistId = String.valueOf(LocalMediaManager.getInstance(this.mAppState).createLocalPlaylist(playlist.getName(), removeGaanaTrack));
                    long offlinePlaylistId = playlist.getOfflinePlaylistId();
                    if (offlinePlaylistId <= 0) {
                        offlinePlaylistId = f.a().a(playlist.getBusinessObjId());
                    }
                    f.a().a(offlinePlaylistId, String.valueOf(localPlaylistId));
                    playlist_status2 = PLAYLIST_STATUS.SUCCESS;
                } else {
                    playlist_status2 = LocalMediaManager.getInstance(this.mAppState).addToLocalPlaylist(playlist.getLocalPlaylistId(), removeGaanaTrack);
                }
            }
            if (!this.mAppState.isAppInOfflineMode() && Util.j(GaanaApplication.getContext())) {
                f a;
                playlist.setChanged(true);
                if (addDeleteSongsFromPlaylistServer(playlist.getBusinessObjId(), localPlaylistId, (ArrayList) arrayList, null) == PLAYLIST_STATUS.SUCCESS) {
                    playlist.setChanged(false);
                    a = f.a();
                    long offlinePlaylistId2 = playlist.getOfflinePlaylistId();
                    f.a();
                    a.a(offlinePlaylistId2, 1);
                }
                a = f.a();
                f.a();
                a.b(playlist, 1);
            }
            z = b;
            playlist_status = playlist_status2;
        } else if (isContainGaanaSong(arrayList)) {
            playlist_status = pushLocalPlaylistToServer(playlist, arrayList);
        } else {
            playlist_status = LocalMediaManager.getInstance(GaanaApplication.getContext()).addToLocalPlaylist(playlist.getBusinessObjId(), (ArrayList) arrayList);
        }
        if (z > false) {
            return playlist_status;
        }
        if (z == arrayList.size()) {
            return PLAYLIST_STATUS.ALREADY_ADDED;
        }
        return z < arrayList.size() ? PLAYLIST_STATUS.PARTIALY_ADDED : playlist_status;
    }

    public int markTrackForDeletion(String str) {
        return f.a().h(str);
    }

    public void changeSyncStatusOnTrackDeleted(String str) {
        f.a().i(str);
    }

    public ArrayList<Track> removeGaanaTrack(ArrayList<Track> arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (!((Track) arrayList.get(size)).isLocalMedia()) {
                arrayList.remove(size);
            }
        }
        return arrayList;
    }

    private PLAYLIST_STATUS pushLocalPlaylistToServer(Playlist playlist, ArrayList<Track> arrayList) {
        ArrayList songsByPlaylist = LocalMediaManager.getInstance(this.mAppState).getSongsByPlaylist(playlist.getBusinessObjId());
        songsByPlaylist.addAll(arrayList);
        long a = f.a().a(playlist.getName(), playlist.getBusinessObjId());
        playlist.setOfflinePlaylistId(a);
        addToGaanaTable(playlist, songsByPlaylist);
        if (a < 0) {
            return PLAYLIST_STATUS.FAILED;
        }
        if (this.mAppState.isAppInOfflineMode() || !Util.j(this.mAppState)) {
            return PLAYLIST_STATUS.SUCCESS;
        }
        return createPlaylistServer(playlist, a, playlist.getBusinessObjId(), playlist.getName(), songsByPlaylist);
    }

    public PLAYLIST_STATUS createPlaylistServer(Playlist playlist, long j, String str, String str2, ArrayList<Track> arrayList) {
        PLAYLIST_STATUS playlist_status = PLAYLIST_STATUS.FAILED;
        if (this.mAppState.isAppInOfflineMode() || !Util.j(this.mAppState)) {
            return playlist_status;
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://api.gaana.com/user.php?type=createplaylist&title=<title>&localplid=<localpid>");
            stringBuilder.append("&token=");
            stringBuilder.append(this.mAppState.getCurrentUser().getAuthToken());
            String stringBuilder2 = stringBuilder.toString();
            if (str == null) {
                str = "";
            }
            i b = new j().b(stringBuilder2.replace("<localpid>", str).replace("<title>", URLEncoder.encode(str2)).replace(" ", "%20"));
            if (!b.b().booleanValue()) {
                return playlist_status;
            }
            stringBuilder2 = getSuccessString(b.a());
            Constants.cE = this.mAppState.getString(R.string.Add_TO_PLAYLIST_FAILURE_MSG);
            if (stringBuilder2 == null || stringBuilder2.length() == 0) {
                Constants.cE = this.mAppState.getString(R.string.playlist_already_exists);
                return playlist_status;
            }
            f a = f.a();
            f.a();
            a.a(j, stringBuilder2, 1);
            if (addDeleteSongsFromPlaylistServer(stringBuilder2, str, (ArrayList) arrayList, null) == PLAYLIST_STATUS.SUCCESS) {
                f a2 = f.a();
                f.a();
                a2.a(j, 1);
            }
            updatePlaylistMetaData(stringBuilder2, j);
            AppsFlyer.getInstance().reportPlaylistCreated(str2, arrayList.size());
            PLAYLIST_STATUS playlist_status2 = PLAYLIST_STATUS.SUCCESS;
            refreshFragment = true;
            playlist.setPlaylistId(stringBuilder2);
            return playlist_status2;
        } catch (Exception unused) {
            playlist_status = PLAYLIST_STATUS.FAILED;
            Constants.cE = this.mAppState.getString(R.string.Add_TO_PLAYLIST_FAILURE_MSG);
            return playlist_status;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void updatePlaylistMetaData(String str, long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/index.php?type=playlist&subtype=playlist_detail_info&playlist_id=");
        stringBuilder.append(str);
        String stringBuilder2 = stringBuilder.toString();
        if (this.mAppState != null && this.mAppState.getCurrentUser().getLoginStatus()) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append("&token=");
            stringBuilder3.append(this.mAppState.getCurrentUser().getAuthToken());
            stringBuilder2 = stringBuilder3.toString();
        }
        Playlist playlist = (Playlist) o.a().a(stringBuilder2, BusinessObjectType.Playlists).getArrListBusinessObj().get(0);
        playlist.setBusinessObjType(BusinessObjectType.Playlists);
        f.a().a(str, playlist);
        int i = 0 - ((int) j);
        if (DownloadManager.c().h(i) != null) {
            DownloadManager.c().a(i, playlist);
        }
    }

    public PLAYLIST_STATUS addDeleteSongsFromPlaylistServer(String str, String str2, ArrayList<Track> arrayList, ArrayList<Track> arrayList2) {
        return addDeleteSongsFromPlaylistServer(str, !TextUtils.isEmpty(str2) ? Long.parseLong(str2) : 0, (ArrayList) arrayList, (ArrayList) arrayList2);
    }

    public PLAYLIST_STATUS addDeleteSongsFromPlaylistServer(String str, long j, ArrayList<Track> arrayList, ArrayList<Track> arrayList2) {
        try {
            str = getJsonObjectAddToPlaylist(str, j, arrayList, arrayList2);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://api.gaana.com/user.php?type=addeditplaylist");
            stringBuilder.append("&token=");
            stringBuilder.append(this.mAppState.getCurrentUser().getAuthToken());
            String replace = stringBuilder.toString().replace(" ", "%20");
            List arrayList3 = new ArrayList();
            arrayList3.add(new BasicNameValuePair("playlist_json", str));
            str = new j().a(replace, arrayList3);
            Constants.cE = this.mAppState.getString(R.string.Add_TO_PLAYLIST_FAILURE_MSG);
            if (!TextUtils.isEmpty(str)) {
                str = getSuccessString(str);
                if (!(str == null || str.length() == 0)) {
                    return PLAYLIST_STATUS.SUCCESS;
                }
            }
            return PLAYLIST_STATUS.FAILED;
        } catch (Exception unused) {
            Constants.cE = this.mAppState.getString(R.string.Add_TO_PLAYLIST_FAILURE_MSG);
            return PLAYLIST_STATUS.FAILED;
        }
    }

    private void clearPlaylistMemCache(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(c.w);
        stringBuilder.append("playlist_id=");
        stringBuilder.append(str);
        stringBuilder.append("&playlist_type=playlist&clear_mcache=1");
        str = stringBuilder.toString();
        String authToken = this.mAppState.getCurrentUser().getAuthToken();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append("&token=");
        stringBuilder2.append(authToken);
        new j().b(stringBuilder2.toString());
    }

    private String getJsonObjectAddToPlaylist(String str, long j, ArrayList<Track> arrayList, ArrayList<Track> arrayList2) {
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        try {
            Iterator it;
            Track track;
            JSONArray jSONArray3 = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.accumulate("pid", str);
            jSONObject2.accumulate("localplid", Long.valueOf(j));
            if (arrayList != null) {
                it = arrayList.iterator();
                while (it.hasNext()) {
                    track = (Track) it.next();
                    JSONObject jSONObject3 = new JSONObject();
                    if (track instanceof LocalTrack) {
                        jSONObject3.accumulate("tid", ((LocalTrack) track).getLocalHashValue());
                        jSONObject3.accumulate("is_local", Integer.valueOf(1));
                    } else {
                        jSONObject3.accumulate("is_local", Integer.valueOf(track.getIslocal() != null ? track.getIslocal().equals("1") : 0));
                        jSONObject3.accumulate("tid", track.getBusinessObjId());
                    }
                    jSONArray.put(jSONObject3);
                }
            }
            if (arrayList2 != null) {
                it = arrayList2.iterator();
                while (it.hasNext()) {
                    track = (Track) it.next();
                    JSONObject jSONObject4 = new JSONObject();
                    if (track instanceof LocalTrack) {
                        jSONObject4.accumulate("tid", ((LocalTrack) track).getLocalHashValue());
                        jSONObject4.accumulate("is_local", Integer.valueOf(1));
                    } else {
                        jSONObject4.accumulate("is_local", Integer.valueOf(track.getIslocal() != null ? track.getIslocal().equals("1") : 0));
                        jSONObject4.accumulate("tid", track.getBusinessObjId());
                    }
                    jSONArray2.put(jSONObject4);
                }
            }
            jSONObject2.accumulate("added_items", jSONArray);
            jSONObject2.accumulate("del_items", jSONArray2);
            jSONArray3.put(jSONObject2);
            jSONObject.accumulate("playlists", jSONArray3);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject.toString();
    }

    public void showDailogueAnddeletePlaylist(final Activity activity, final Playlist playlist) {
        new CustomDialogView((Context) activity, activity.getResources().getString(R.string.deletePlaylistConfirmationText), new OnButtonClickListener() {
            public void onNegativeButtonClick() {
            }

            public void onPositiveButtonClick() {
                f.a().e(playlist);
                if (!(playlist.getLocalPlaylistId() == null || playlist.getLocalPlaylistId().equalsIgnoreCase("0"))) {
                    LocalMediaManager.getInstance(activity).deleteLocalPlaylist(playlist.getLocalPlaylistId());
                }
                o.a().c("https://api.gaana.com/user.php?type=myplaylists&subtype=myplaylists");
                int a = Util.a(playlist.getBusinessObjId());
                if (a != 0 && ap.a().o() && DownloadManager.c().b(playlist).booleanValue()) {
                    DownloadManager.c().p(a);
                    DownloadManager.c().d(a);
                }
                if (PlaylistSyncManager.this.mAppState.isAppInOfflineMode() || !Util.j(GaanaApplication.getContext())) {
                    if (((GaanaActivity) activity).getCurrentFragment() instanceof AlbumDetailsMaterialListing) {
                        ((GaanaActivity) activity).popBackStack();
                    } else if (((GaanaActivity) activity).getCurrentFragment() instanceof GaanaSpecialDetailsMaterialListing) {
                        ((GaanaActivity) activity).popBackStack();
                    } else {
                        ((GaanaActivity) activity).refreshListView(playlist, true);
                    }
                    aj.a().a(activity, activity.getString(R.string.playlist_delete_success));
                    return;
                }
                PlaylistSyncManager.this.preparePlaylistDeletionUrl(activity, playlist);
            }
        }).show();
    }

    private void preparePlaylistDeletionUrl(final Activity activity, final Playlist playlist) {
        if (playlist == null) {
            ((GaanaActivity) activity).hideProgressDialog();
            aj.a().a(activity, activity.getString(R.string.invalid_playlist_delete));
            return;
        }
        ((GaanaActivity) activity).showProgressDialog(Boolean.valueOf(false), activity.getString(R.string.dlg_msg_deleting_playlist));
        h.a().a(new TaskListner() {
            private String mPlaylistDeletionResponse = "-1";

            public void onBackGroundTaskCompleted() {
                ((GaanaActivity) activity).hideProgressDialog();
                if (this.mPlaylistDeletionResponse.equalsIgnoreCase("1")) {
                    aj.a().a(activity, activity.getString(R.string.playlist_delete_success));
                    if (((GaanaActivity) activity).getCurrentFragment() instanceof AlbumDetailsMaterialListing) {
                        ((GaanaActivity) activity).setRefreshData(true);
                        ((GaanaActivity) activity).popBackStack();
                    } else if (((GaanaActivity) activity).getCurrentFragment() instanceof GaanaSpecialDetailsMaterialListing) {
                        ((GaanaActivity) activity).setRefreshData(true);
                        ((GaanaActivity) activity).popBackStack();
                    } else if (((GaanaActivity) activity).getCurrentFragment() instanceof LocalMediaFragment) {
                        ((LocalMediaFragment) ((GaanaActivity) activity).getCurrentFragment()).a();
                    } else if (((GaanaActivity) activity).getCurrentFragment() instanceof ListingFragment) {
                        ((ListingFragment) ((GaanaActivity) activity).getCurrentFragment()).i();
                    } else if (((GaanaActivity) activity).getCurrentFragment() instanceof FavoritesFragment) {
                        ((FavoritesFragment) ((GaanaActivity) activity).getCurrentFragment()).c();
                    } else if (((GaanaActivity) activity).getCurrentFragment() instanceof MyMusicItemFragment) {
                        ((MyMusicItemFragment) ((GaanaActivity) activity).getCurrentFragment()).d();
                    }
                }
            }

            public void doBackGroundTask() {
                try {
                    this.mPlaylistDeletionResponse = PlaylistSyncManager.this.deletePlaylist(playlist);
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            aj.a().a(activity, activity.getString(R.string.error_occured_delete_playlist_due_to_network));
                            ((GaanaActivity) activity).hideProgressDialog();
                        }
                    });
                }
            }
        }, -1);
    }

    private String deletePlaylist(Playlist playlist) {
        Throwable e;
        HashMap hashMap = new HashMap();
        hashMap.put("type", "myplaylists");
        hashMap.put(LoginManager.TAG_SUBTYPE, "delete_playlist");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(playlist.getBusinessObjId());
        hashMap.put("id", stringBuilder.toString());
        hashMap.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, this.mAppState.getCurrentUser().getAuthToken());
        String a = o.a().a("https://api.gaana.com/user.php?", hashMap).a();
        String str = "-1";
        try {
            a = new JSONObject(a).getString("Status");
            try {
                if (!TextUtils.isEmpty(a) && a.equals("1")) {
                    f.a().d(playlist);
                }
            } catch (Exception e2) {
                e = e2;
                ThrowableExtension.printStackTrace(e);
                return a;
            }
        } catch (Exception e3) {
            e = e3;
            a = str;
            ThrowableExtension.printStackTrace(e);
            return a;
        }
        return a;
    }

    public void showDialogAndEditPlaylist(Activity activity, Playlist playlist, boolean z, ArrayList<Track> arrayList) {
        Activity activity2 = activity;
        BusinessObject businessObject = playlist;
        ArrayList<Track> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList();
        if (arrayList2 != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                Track track = (Track) arrayList2.get(i);
                if (track.isMarkedForDeletionFromPlaylist()) {
                    arrayList3.add(track);
                }
            }
        }
        if (playlist.isLocalMedia()) {
            updateLocalMediaStorePlaylist(activity2, playlist.getBusinessObjId(), z, playlist.getName(), arrayList2, arrayList3);
            return;
        }
        if (arrayList3.size() > 0) {
            f.a().a(playlist.getOfflinePlaylistId(), arrayList3);
        }
        f.a().a(businessObject, playlist.getOfflinePlaylistId(), playlist.getBusinessObjId(), -1);
        if (z) {
            DownloadManager.c().a(playlist.getBusinessObjId(), businessObject);
        }
        f.a().c(businessObject, arrayList2);
        if (arrayList3.size() > 0) {
            deleteTracksFromOfflinePlaylist(arrayList3);
        }
        GaanaActivity gaanaActivity = (GaanaActivity) activity2;
        gaanaActivity.setRefreshData(true);
        if (this.mAppState.isAppInOfflineMode() || !Util.j(GaanaApplication.getContext())) {
            aj.a().a(activity2, activity2.getString(R.string.updated_playlist));
            gaanaActivity.popBackStack();
            return;
        }
        deleteSelectedTracksFromGaanaPlaylist(activity2, businessObject, arrayList3);
    }

    public void showDialogAndEditPlaylistOnline(Activity activity, Playlist playlist, boolean z, ArrayList<Track> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                Track track = (Track) arrayList.get(i);
                if (track.isMarkedForDeletionFromPlaylist()) {
                    arrayList2.add(track);
                }
            }
        }
        ((GaanaActivity) activity).setRefreshData(true);
        deleteSelectedTracksFromGaanaPlaylist(activity, playlist, arrayList2);
    }

    public void deleteSelectedTracksFromMediaStorePlaylist(final Activity activity, final String str, final ArrayList<Track> arrayList) {
        h.a().a(new TaskListner() {
            private PLAYLIST_STATUS playlist_status = PLAYLIST_STATUS.FAILED;

            public void onBackGroundTaskCompleted() {
                ((GaanaActivity) activity).hideProgressDialog();
                if (this.playlist_status == PLAYLIST_STATUS.SUCCESS) {
                    aj.a().a(activity, activity.getString(R.string.song_deletion_successful));
                    ((GaanaActivity) activity).setRefreshData(true);
                    if (((GaanaActivity) activity).getCurrentFragment() != null && (((GaanaActivity) activity).getCurrentFragment() instanceof EditPlaylistFragment) && ((GaanaActivity) activity).getCurrentFragment().isVisible()) {
                        ((GaanaActivity) activity).popBackStack();
                        return;
                    }
                    return;
                }
                aj.a().a(activity, activity.getString(R.string.song_deletion_failed));
            }

            public void doBackGroundTask() {
                ((GaanaActivity) activity).runOnUiThread(new Runnable() {
                    public void run() {
                        ((GaanaActivity) activity).showProgressDialog(Boolean.valueOf(false), activity.getString(R.string.deleting_selected_song));
                    }
                });
                try {
                    this.playlist_status = LocalMediaManager.getInstance(GaanaApplication.getContext()).deleteTracksFromMediaStorePlaylist(str, arrayList);
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                    ((GaanaActivity) activity).hideProgressDialog();
                }
            }
        }, -1);
    }

    public void deleteSelectedTracksFromGaanaPlaylist(final Activity activity, final Playlist playlist, final ArrayList<Track> arrayList) {
        h.a().a(new TaskListner() {
            private PLAYLIST_STATUS playlist_status = PLAYLIST_STATUS.FAILED;

            public void onBackGroundTaskCompleted() {
                ((GaanaActivity) activity).hideProgressDialog();
                if (this.playlist_status == PLAYLIST_STATUS.SUCCESS) {
                    aj.a().a(activity, activity.getString(R.string.updated_playlist));
                    f.a().b(playlist.getOfflinePlaylistId(), arrayList);
                    f.a().a(playlist.getOfflinePlaylistId(), playlist.getBusinessObjId(), 1);
                    PlaylistSyncManager.getInstance().updatePlaylistMemCache(Integer.parseInt(playlist.getBusinessObjId()));
                    if (((GaanaActivity) activity).getCurrentFragment() != null && (((GaanaActivity) activity).getCurrentFragment() instanceof EditPlaylistFragment) && ((GaanaActivity) activity).getCurrentFragment().isVisible()) {
                        ((GaanaActivity) activity).popBackStack();
                        return;
                    }
                    return;
                }
                aj.a().a(activity, activity.getString(R.string.playlist_updation_failed));
            }

            public void doBackGroundTask() {
                ((GaanaActivity) activity).runOnUiThread(new Runnable() {
                    public void run() {
                        ((GaanaActivity) activity).showProgressDialog(Boolean.valueOf(false), activity.getString(R.string.updating_playlist));
                    }
                });
                try {
                    this.playlist_status = PlaylistSyncManager.this.addDeleteSongsFromPlaylistServer(playlist, null, arrayList);
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                    ((GaanaActivity) activity).hideProgressDialog();
                }
            }
        }, -1);
    }

    private void deleteTracksFromOfflinePlaylist(ArrayList<Track> arrayList) {
        if (ap.a().o()) {
            BusinessObject businessObject = (Playlist) this.mAppState.getListingComponents().a();
            int a = Util.a(businessObject.getBusinessObjId());
            if (a != 0 && DownloadManager.c().b(businessObject).booleanValue()) {
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList2.add(Integer.valueOf(Integer.parseInt(((Track) arrayList.get(i)).getBusinessObjId())));
                }
                DownloadManager.c().a(a, arrayList2);
            }
        }
    }

    public boolean isMyPlaylist(Playlist playlist) {
        if (this.mAppState.getCurrentUser().getLoginStatus() && playlist != null) {
            String userId = this.mAppState.getCurrentUser().getUserProfile().getUserId();
            String creatorUserId = playlist.getCreatorUserId();
            if (!(TextUtils.isEmpty(creatorUserId) || TextUtils.isEmpty(userId) || !creatorUserId.equals(userId))) {
                return true;
            }
        }
        return false;
    }

    public void updatePlaylistTracks(Playlist playlist, Tracks tracks) {
        f.a().a(playlist, tracks);
    }

    public int getPlaylistSyncStatus(String str) {
        return f.a().g(str);
    }

    public void performSync(Playlist playlist, int i) {
        if (!this.isSyncingInProgress) {
            this.isSyncingInProgress = true;
            if (i == -1) {
                ArrayList a = f.a().a(playlist, 0);
                ArrayList a2 = f.a().a(playlist, -2);
                playlist.setChanged(true);
                PLAYLIST_STATUS addDeleteSongsFromPlaylistServer = addDeleteSongsFromPlaylistServer(playlist, a, a2);
                playlist.setChanged(false);
                if (addDeleteSongsFromPlaylistServer == PLAYLIST_STATUS.SUCCESS) {
                    f.a().a(playlist.getOfflinePlaylistId(), playlist.getBusinessObjId(), 1);
                }
            } else if (i == -2) {
                deletePlaylist(playlist);
            } else if (i == 0) {
                Playlist playlist2 = playlist;
                createPlaylistServer(playlist2, playlist.getOfflinePlaylistId(), playlist.getLocalPlaylistId(), playlist.getName(), f.a().a(playlist, 0));
            }
            this.isSyncingInProgress = false;
        }
    }

    public ArrayList<Track> getLocalTracksFromPlaylist(String str) {
        return f.a().f(str);
    }

    public void updatePlaylistMemCache(final int i) {
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
            }

            public void doBackGroundTask() {
                PlaylistSyncManager.this.clearPlaylistMemCache(String.valueOf(i));
            }
        }, -1);
    }

    public void addSongsInPlaylist(BusinessObject businessObject, final Context context) {
        final Playlist playlist = (Playlist) businessObject;
        final ArrayList arrayList = new ArrayList();
        if (businessObject.getArrListBusinessObj() != null) {
            Iterator it = businessObject.getArrListBusinessObj().iterator();
            while (it.hasNext()) {
                Track track = (Track) it.next();
                if (track.isAddedToPlaylist()) {
                    arrayList.add(track);
                    track.setAddedToPlaylist(false);
                }
            }
        }
        if (arrayList == null || arrayList.size() == 0) {
            aj.a().a(context, context.getString(R.string.select_atleas_a_track));
            return;
        }
        ((BaseActivity) context).showProgressDialog(Boolean.valueOf(false), context.getString(R.string.adding_to_playlist_text));
        h.a().a(100);
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
                PlaylistSyncManager.this.mAppState.setArrListTracksForPlaylist(null);
                o a = o.a();
                String[] strArr = new String[2];
                strArr[0] = "type=playlist&subtype=playlist_detail";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("playlist_id=");
                stringBuilder.append(playlist.getBusinessObjId());
                strArr[1] = stringBuilder.toString();
                a.a(strArr);
                if (ap.a().o()) {
                    int a2 = Util.a(playlist.getBusinessObjId());
                    if (!(a2 == 0 || !DownloadManager.c().b(playlist).booleanValue() || arrayList == null)) {
                        DownloadManager.c().b(arrayList, a2, true);
                    }
                }
                if (PlaylistSyncManager.this.playlistStatus == PLAYLIST_STATUS.SUCCESS) {
                    PlaylistSyncManager.getInstance().updatePlaylistMemCache(Util.a(playlist.getBusinessObjId()));
                }
                PlaylistSyncManager.this.showSuccessMsg(context);
            }

            public void doBackGroundTask() {
                if (arrayList != null) {
                    PlaylistSyncManager.this.removeDuplicateTracks(arrayList);
                    PlaylistSyncManager.this.playlistStatus = PlaylistSyncManager.getInstance().addToPlaylist((Activity) context, playlist, arrayList);
                    return;
                }
                PlaylistSyncManager.this.playlistStatus = PLAYLIST_STATUS.FAILED;
            }
        }, 100);
    }

    private void showSuccessMsg(Context context) {
        String str = "";
        switch (this.playlistStatus) {
            case SUCCESS:
                str = context.getString(R.string.songs_added_to_playlist);
                u.a().b("Playlist", "Add Songs");
                break;
            case FAILED:
                str = context.getString(R.string.songs_add_failed);
                break;
            case ALREADY_ADDED:
                str = context.getString(R.string.songs_already_in_playlist);
                break;
        }
        if (this.mAppState.getArrListForTrackIds() != null && this.mAppState.getArrListForTrackIds().size() > 0) {
            this.mAppState.getArrListForTrackIds().clear();
        }
        if (this.mAppState.getArrListForPlaylistIds() != null && this.mAppState.getArrListForPlaylistIds().size() > 0) {
            this.mAppState.getArrListForPlaylistIds().clear();
        }
        ((BaseActivity) context).hideProgressDialog();
        aj.a().a(context, str);
        ((GaanaActivity) context).setRefreshData(true);
        try {
            if (((GaanaActivity) context).getCurrentFragment() != null && (((GaanaActivity) context).getCurrentFragment() instanceof SongsSelectionFragment) && ((GaanaActivity) context).getCurrentFragment().isVisible() && !((SongsSelectionFragment) ((GaanaActivity) context).getCurrentFragment()).a()) {
                ((GaanaActivity) context).popBackStack();
            }
        } catch (Exception unused) {
        }
    }

    public void removeDuplicateTracks(ArrayList<Track> arrayList) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(arrayList);
        arrayList.clear();
        arrayList.addAll(linkedHashSet);
    }

    public PLAYLIST_STATUS addDeleteSongsFromPlaylistServer(Playlist playlist, ArrayList<Track> arrayList, ArrayList<Track> arrayList2) {
        try {
            String jsonObjectAddToPlaylist = getJsonObjectAddToPlaylist(playlist, arrayList, arrayList2);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://api.gaana.com/user.php?type=addeditplaylist");
            stringBuilder.append("&token=");
            stringBuilder.append(this.mAppState.getCurrentUser().getAuthToken());
            String replace = stringBuilder.toString().replace(" ", "%20");
            List arrayList3 = new ArrayList();
            arrayList3.add(new BasicNameValuePair("playlist_json", jsonObjectAddToPlaylist));
            jsonObjectAddToPlaylist = new j().a(replace, arrayList3);
            Constants.cE = this.mAppState.getString(R.string.Add_TO_PLAYLIST_FAILURE_MSG);
            if (!TextUtils.isEmpty(jsonObjectAddToPlaylist)) {
                jsonObjectAddToPlaylist = getSuccessString(jsonObjectAddToPlaylist);
                if (!(jsonObjectAddToPlaylist == null || jsonObjectAddToPlaylist.length() == 0)) {
                    return PLAYLIST_STATUS.SUCCESS;
                }
            }
            return PLAYLIST_STATUS.FAILED;
        } catch (Exception unused) {
            Constants.cE = this.mAppState.getString(R.string.Add_TO_PLAYLIST_FAILURE_MSG);
            return PLAYLIST_STATUS.FAILED;
        }
    }

    private String getJsonObjectAddToPlaylist(Playlist playlist, ArrayList<Track> arrayList, ArrayList<Track> arrayList2) {
        long parseLong = !TextUtils.isEmpty(playlist.getLocalPlaylistId()) ? Long.parseLong(playlist.getLocalPlaylistId()) : 0;
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        ArrayList arrListBusinessObj = playlist.getArrListBusinessObj();
        CharSequence charSequence = "";
        if (!playlist.isPlaylistChanged() || arrListBusinessObj == null || arrListBusinessObj.size() <= 0) {
            playlist.setChanged(false);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = arrListBusinessObj.iterator();
            while (it.hasNext()) {
                Track track = (Track) it.next();
                if (!track.isMarkedForDeletionFromPlaylist()) {
                    if (track instanceof LocalTrack) {
                        stringBuilder.append(((LocalTrack) track).getLocalHashValue());
                        stringBuilder.append(',');
                    } else {
                        stringBuilder.append(track.getBusinessObjId());
                        stringBuilder.append(',');
                    }
                }
            }
            charSequence = stringBuilder.toString();
            Playlist playlist2 = playlist;
        }
        try {
            Iterator it2;
            JSONArray jSONArray3 = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.accumulate("pid", playlist.getBusinessObjId());
            jSONObject2.accumulate("localplid", Long.valueOf(parseLong));
            if (arrayList != null) {
                StringBuilder stringBuilder2 = new StringBuilder(charSequence);
                it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    Track track2 = (Track) it2.next();
                    JSONObject jSONObject3 = new JSONObject();
                    if (track2 instanceof LocalTrack) {
                        jSONObject3.accumulate("tid", ((LocalTrack) track2).getLocalHashValue());
                        if (playlist.isPlaylistChanged()) {
                            stringBuilder2.append(((LocalTrack) track2).getLocalHashValue());
                            stringBuilder2.append(',');
                        }
                        jSONObject3.accumulate("is_local", Integer.valueOf(1));
                    } else {
                        jSONObject3.accumulate("is_local", Integer.valueOf(track2.getIslocal() != null ? track2.getIslocal().equals("1") : 0));
                        if (playlist.isPlaylistChanged()) {
                            stringBuilder2.append(track2.getBusinessObjId());
                            stringBuilder2.append(',');
                        }
                        jSONObject3.accumulate("tid", track2.getBusinessObjId());
                    }
                    jSONArray.put(jSONObject3);
                }
                charSequence = stringBuilder2.toString();
            }
            if (charSequence.length() > 1) {
                charSequence = charSequence.substring(0, charSequence.length() - 1);
            }
            if (arrayList2 != null) {
                it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    Track track3 = (Track) it2.next();
                    JSONObject jSONObject4 = new JSONObject();
                    if (track3 instanceof LocalTrack) {
                        jSONObject4.accumulate("tid", ((LocalTrack) track3).getLocalHashValue());
                        jSONObject4.accumulate("is_local", Integer.valueOf(1));
                    } else {
                        jSONObject4.accumulate("is_local", Integer.valueOf(track3.getIslocal() != null ? track3.getIslocal().equals("1") : 0));
                        jSONObject4.accumulate("tid", track3.getBusinessObjId());
                    }
                    jSONArray2.put(jSONObject4);
                }
            }
            jSONObject2.accumulate("added_items", jSONArray);
            jSONObject2.accumulate("del_items", jSONArray2);
            if (playlist.isPlaylistChanged() && !TextUtils.isEmpty(charSequence)) {
                jSONObject2.accumulate("ordered_items", charSequence);
            }
            if (!(TextUtils.isEmpty(playlist.getName()) || TextUtils.isEmpty(playlist.getName().trim()))) {
                jSONObject2.accumulate("playlist_title", playlist.getName().trim());
            }
            jSONArray3.put(jSONObject2);
            jSONObject.accumulate("playlists", jSONArray3);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject.toString();
    }

    public void updateLocalMediaStorePlaylist(Activity activity, String str, boolean z, String str2, ArrayList<Track> arrayList, ArrayList<Track> arrayList2) {
        final Activity activity2 = activity;
        final ArrayList<Track> arrayList3 = arrayList2;
        final String str3 = str;
        final boolean z2 = z;
        final String str4 = str2;
        final ArrayList<Track> arrayList4 = arrayList;
        h.a().a(new TaskListner() {
            private PLAYLIST_STATUS playlist_status = PLAYLIST_STATUS.FAILED;

            public void onBackGroundTaskCompleted() {
                ((GaanaActivity) activity2).hideProgressDialog();
                if (this.playlist_status == PLAYLIST_STATUS.SUCCESS) {
                    aj.a().a(activity2, activity2.getString(R.string.updated_playlist));
                    ((GaanaActivity) activity2).setRefreshData(true);
                    if (((GaanaActivity) activity2).getCurrentFragment() != null && (((GaanaActivity) activity2).getCurrentFragment() instanceof EditPlaylistFragment) && ((GaanaActivity) activity2).getCurrentFragment().isVisible()) {
                        ((GaanaActivity) activity2).popBackStack();
                        return;
                    }
                    return;
                }
                aj.a().a(activity2, activity2.getString(R.string.playlist_updation_failed));
            }

            public void doBackGroundTask() {
                ((GaanaActivity) activity2).runOnUiThread(new Runnable() {
                    public void run() {
                        ((GaanaActivity) activity2).showProgressDialog(Boolean.valueOf(false), activity2.getString(R.string.updating_playlist));
                    }
                });
                try {
                    if (arrayList3.size() > 0) {
                        this.playlist_status = LocalMediaManager.getInstance(GaanaApplication.getContext()).deleteTracksFromMediaStorePlaylist(str3, arrayList3);
                    }
                    if (z2) {
                        LocalMediaManager.getInstance(GaanaApplication.getContext()).updateLocalPlaylistName(str3, str4);
                    }
                    this.playlist_status = LocalMediaManager.getInstance(GaanaApplication.getContext()).reOrderMediaStorePlaylist(str3, arrayList4);
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                    ((GaanaActivity) activity2).hideProgressDialog();
                }
            }
        }, -1);
    }

    public String nextSimilarPlaylistWithName(String str) {
        return f.a().b(str);
    }

    public Playlist getPlaylistFromName(String str) {
        return f.a().d(str);
    }
}
