package com.managers;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.constants.Constants;
import com.constants.c;
import com.e.a.b;
import com.e.a.e;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.UserInfo;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.DownloadSyncArrays;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.managers.TaskManager.TaskListner;
import com.managers.URLManager.BusinessObjectType;
import com.models.ListingButton;
import com.moengage.ActionMapperConstants;
import com.services.DownloadSyncService;
import com.services.d;
import com.services.h;
import com.services.i;
import com.services.j;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class l {
    private static l a;
    private d b;
    private boolean c;
    private boolean d;
    private b e;
    private BroadcastReceiver f;
    private ArrayList<String> g;
    private NotificationManager h;

    public void c(int i, int i2, String str) {
    }

    private l() {
        this.c = true;
        this.d = false;
        this.e = null;
        this.f = null;
        this.g = null;
        this.b = d.a();
        this.e = new b(GaanaApplication.getContext());
    }

    public static l a() {
        if (a == null) {
            synchronized (l.class) {
                if (a == null) {
                    a = new l();
                }
            }
        }
        return a;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public DownloadSyncArrays b() {
        return this.e.a();
    }

    public void c() {
        this.e.c();
    }

    public void d() {
        this.e.b();
    }

    public void a(String str, ArrayList<BusinessObject> arrayList) {
        if (this.e != null) {
            this.e.a(str);
            this.e.a((ArrayList) arrayList);
        }
    }

    public void a(ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        this.e.a((ArrayList) arrayList, (ArrayList) arrayList2, (ArrayList) arrayList3);
    }

    public int a(int i) {
        return this.e.a(i);
    }

    public ArrayList<String> b(int i) {
        return this.e.b(i);
    }

    public void a(ArrayList<String> arrayList) {
        if (this.e != null) {
            this.e.b((ArrayList) arrayList);
        }
    }

    public void b(ArrayList<String> arrayList) {
        this.g = arrayList;
    }

    public ArrayList<String> e() {
        return this.g;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public boolean f() {
        return this.d;
    }

    public boolean g() {
        if (Util.k(GaanaApplication.getContext()) == 0) {
            return this.b.b("PREFERENCE_KEY_DOWNLOAD_SYNC_OVER_DATA_CONNECTION", false, true);
        }
        return true;
    }

    public void c(boolean z) {
        Intent intent = new Intent(GaanaApplication.getContext(), DownloadSyncService.class);
        intent.setAction("NORMAL_SYNC");
        intent.putExtra("isForcedFullSync", z);
        GaanaApplication.getContext().startService(intent);
    }

    public void h() {
        this.b.b("IS_DOWNLOAD_INFO_CHANGED_LOCALLY", true);
        this.b.b("LAST_DOWNLOAD_SYNC_TIME_LOCAL", true);
        this.b.b("LAST_DOWNLOAD_SYNC_TIME_SERVER", true);
        this.e.d();
    }

    private String c(String str) {
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

    private ArrayList<BusinessObject> c(ArrayList<BusinessObject> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (this.g != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                BusinessObject businessObject = (BusinessObject) it.next();
                if (this.g.contains(businessObject.getBusinessObjId())) {
                    arrayList2.add(businessObject);
                }
            }
        }
        return arrayList2;
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
                            track = LocalMediaManager.getInstance(GaanaApplication.getContext()).getLocalTrackFromHash(track.getBusinessObjId());
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
            str = c.t;
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
            str = c.u;
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

    public void a(int i, int i2, int i3) {
        this.e.a(i, i2, i3);
    }

    public void a(final int i, final int i2) {
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
            }

            public void doBackGroundTask() {
                j jVar = new j();
                try {
                    String str = "https://api.gaana.com/gaanaplusservice.php?type=download_sync&subtype=queueAndDownload&entity_type=<entity_type>&entity_id=<entity_id>";
                    CharSequence charSequence = "playlist";
                    if (i2 == e.c.a) {
                        charSequence = "album";
                    }
                    CharSequence valueOf = String.valueOf(i);
                    if (i == -100) {
                        valueOf = "playlist_favourite";
                    }
                    i b = jVar.b(l.this.c(str.replace("<entity_type>", charSequence).replace("<entity_id>", valueOf)));
                    if (b.b().booleanValue()) {
                        b.a();
                    }
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }, -1);
    }

    public void a(final int i, final int i2, final String str) {
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
            }

            public void doBackGroundTask() {
                l.this.b(i, i2, str);
            }
        }, -1);
    }

    public boolean b(int i, int i2, String str) {
        j jVar = new j();
        try {
            CharSequence str2;
            String str3 = "https://api.gaana.com/gaanaplusservice.php?type=download_sync&subtype=entityLog&entity_type=<entity_type>&entity_id=<entity_id>&entity_status=<entity_status>";
            CharSequence charSequence = "playlist";
            if (i2 == e.c.a) {
                charSequence = "album";
            } else if (i2 == e.c.c) {
                charSequence = ActionMapperConstants.KEY_TRACK;
            }
            CharSequence valueOf = String.valueOf(i);
            if (i == -100) {
                valueOf = "playlist_favourite";
            }
            String replace = str3.replace("<entity_type>", charSequence).replace("<entity_id>", valueOf);
            if (i2 == e.c.c && str2.equals("success")) {
                str2 = "downloaded";
            }
            i b = jVar.b(c(replace.replace("<entity_status>", str2)));
            if (b.b().booleanValue()) {
                b.a();
                return true;
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return false;
    }

    public void i() {
        if (this.h != null) {
            this.h.cancel(1001);
        }
    }

    public void a(String str) {
        int size = this.g.size();
        int a = this.e.a(e.c.c);
        c(size, a < size ? size - a : 0, str);
    }

    public void b(final String str) {
        com.i.d.a(new Runnable() {
            public void run() {
                LoginManager.getInstance().loginSilently(null, new IOnLoginCompleted() {
                    public void onLoginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle) {
                        if (userInfo != null && userInfo.getLoginStatus()) {
                            if (userInfo.getUserProfile() != null) {
                                ap.a().a(userInfo.getUserProfile().getUserId());
                            } else {
                                ap.a().a(userInfo.getUserProfile().getEmail());
                            }
                            if (str.contains("view/downloadsync/")) {
                                String str = str.split("view/downloadsync/")[1];
                                BusinessObjectType businessObjectType = null;
                                int i = -1;
                                if (str.startsWith("t")) {
                                    businessObjectType = BusinessObjectType.Tracks;
                                    i = e.c.c;
                                } else if (str.startsWith(TtmlNode.TAG_P)) {
                                    businessObjectType = BusinessObjectType.Playlists;
                                    i = e.c.b;
                                } else if (str.startsWith("A")) {
                                    businessObjectType = BusinessObjectType.Albums;
                                    i = e.c.a;
                                }
                                int lastIndexOf = str.lastIndexOf("I");
                                if (lastIndexOf >= 0) {
                                    str = str.substring(lastIndexOf + 1);
                                    if (!TextUtils.isEmpty(str)) {
                                        ArrayList arrayList = new ArrayList();
                                        arrayList.add(str);
                                        BusinessObject a = l.this.a(arrayList, businessObjectType);
                                        l.this.e.a(Integer.parseInt(str), i, 1);
                                        if (a == null) {
                                            return;
                                        }
                                        if (businessObjectType == BusinessObjectType.Tracks) {
                                            if (a.getArrListBusinessObj() != null && a.getArrListBusinessObj().size() > 0) {
                                                l.this.e.a(str);
                                                DownloadManager.c().a((BusinessObject) a.getArrListBusinessObj().get(0));
                                            }
                                        } else if (a.getArrListBusinessObj() != null && a.getArrListBusinessObj().size() > 0) {
                                            BusinessObject businessObject = (BusinessObject) a.getArrListBusinessObj().get(0);
                                            BusinessObject a2 = l.this.a(businessObject);
                                            if (a2 != null && a2.getArrListBusinessObj() != null) {
                                                businessObject.setArrListBusinessObj(a2.getArrListBusinessObj());
                                                if (DownloadManager.c().h(Integer.parseInt(str)) == null) {
                                                    DownloadManager.c().a(businessObject);
                                                } else if (businessObject instanceof Playlist) {
                                                    DownloadManager.c().b(l.this.c(a2.getArrListBusinessObj()), Integer.parseInt(str), false);
                                                }
                                                if (l.this.e != null) {
                                                    l.this.e.a(str);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (str.contains("view/downloadsync")) {
                                l.this.c(true);
                            }
                        } else if (Constants.b) {
                            Log.d("Test", "Invalid user");
                        }
                    }
                }, false);
            }
        });
    }
}
