package com.managers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.constants.Constants;
import com.e.a.e.c;
import com.e.a.h;
import com.e.a.h.a;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.login.GaanaMiniSubDetails;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.SmartDownloadsData;
import com.gaana.models.Tracks.Track;
import com.gaana.models.Tracks.Track.Artist;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.library.util.StorageUtils;
import com.managers.SdCardManager.STORAGE_TYPE;
import com.managers.URLManager.BusinessObjectType;
import com.payu.custombrowser.util.CBConstant;
import com.services.FileDownloadService;
import com.services.NetworkChangeBroadcastReceiver;
import com.services.l.af;
import com.til.colombia.android.internal.d;
import com.til.colombia.android.internal.e;
import com.utilities.Util;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class DownloadManager implements a {
    public static int a = -100;
    public static String b = "Track_Download_Status";
    public static String c = "PREFERENCE_KEY_DOWNLOAD_STATUS";
    public static String d = "4001";
    public static String e = "4002";
    public static String f = "5001";
    public static String g = "4017";
    private static DownloadManager i = null;
    private static String j = "4003";
    private static String k = "4004";
    private boolean A = false;
    private BroadcastReceiver B = null;
    private final String C = d.a;
    int h = 0;
    private ConcurrentHashMap<Integer, DownloadStatus> l = new ConcurrentHashMap();
    private ConcurrentHashMap<Integer, DownloadStatus> m = new ConcurrentHashMap();
    private ConcurrentHashMap<Integer, Boolean> n = new ConcurrentHashMap();
    private h o;
    private String p;
    private int q = -1;
    private BroadcastReceiver r = null;
    private boolean s = false;
    private GaanaApplication t = ((GaanaApplication) GaanaApplication.getContext());
    private com.services.d u = com.services.d.a();
    private boolean v = true;
    private boolean w = false;
    private HashMap<String, ArrayList<String>> x = new HashMap();
    private boolean y = false;
    private Handler z = new Handler(Looper.getMainLooper());

    public enum DownloadHTTPStatus {
        SUCCESS,
        CONNECTION_RESET,
        FAILED
    }

    public enum DownloadStatus {
        QUEUED,
        DOWNLOADING,
        DOWNLOADED,
        PAUSED,
        PARTIALLY_DOWNLOADED,
        TRIED_BUT_FAILED
    }

    public ArrayList<Track> n(int i) {
        return null;
    }

    public void a(Context context) {
        b(context);
        this.B = new NetworkChangeBroadcastReceiver();
        context.registerReceiver(this.B, new IntentFilter(d.a));
    }

    public void a(boolean z) {
        this.A = z;
    }

    public boolean a() {
        return this.A;
    }

    public void a(int i) {
        this.h = i;
    }

    public int b() {
        return this.h;
    }

    public void b(Context context) {
        if (this.B != null) {
            try {
                context.unregisterReceiver(this.B);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    private DownloadManager() {
        if (this.o == null) {
            this.o = new h(GaanaApplication.getContext());
        }
        this.o.a((a) this);
    }

    public static DownloadManager c() {
        if (i == null) {
            synchronized (DownloadManager.class) {
                if (i == null) {
                    i = new DownloadManager();
                }
            }
        }
        return i;
    }

    public void d() {
        if (!GaanaApplication.getInstance().isAppInForeground()) {
            O();
        } else if (com.utilities.d.e()) {
            O();
        } else {
            this.z.postDelayed(new Runnable() {
                public void run() {
                    if (GaanaApplication.getInstance().isAppInForeground()) {
                        DownloadManager.this.O();
                    }
                }
            }, 400);
        }
    }

    private void O() {
        GaanaApplication.getContext().startService(new Intent(GaanaApplication.getContext(), FileDownloadService.class));
    }

    public void e() {
        a(null);
    }

    public void a(String str) {
        if (GaanaApplication.getContext().stopService(new Intent(GaanaApplication.getContext(), FileDownloadService.class))) {
            if (!(c().k() == -1 || Util.j(GaanaApplication.getContext()))) {
                aj.a().a(GaanaApplication.getContext(), GaanaApplication.getContext().getResources().getString(R.string.toast_download_stop_due_to_no_internet));
            }
            if (FileDownloadService.b() != null) {
                FileDownloadService.b().OnNetworkChangeListener(FileDownloadService.a());
            }
            c().k(-1);
            c().d(false);
            Intent intent = new Intent("broadcast_intent_download_service");
            intent.putExtra("track_id", -1);
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra("SNACKBAR_MSG", str);
            }
            LocalBroadcastManager.getInstance(GaanaApplication.getContext()).sendBroadcast(intent);
            s();
            this.o.a();
        }
    }

    public void a(BroadcastReceiver broadcastReceiver) {
        s();
        this.r = broadcastReceiver;
        this.s = false;
    }

    public void f() {
        g();
        h();
        if (ap.a().m()) {
            i();
        }
        j();
    }

    public void g() {
        this.l = this.o.e();
    }

    public void h() {
        this.m = this.o.g();
    }

    public void i() {
        synchronized (this.x) {
            this.x = this.o.v();
        }
    }

    public void j() {
        this.n = this.o.f();
    }

    public void a(BusinessObject businessObject, ArrayList<String> arrayList) {
        if (!TextUtils.isEmpty(businessObject.getBusinessObjId())) {
            boolean z = businessObject instanceof Track;
            if (!z || !l(Integer.parseInt(businessObject.getBusinessObjId())).booleanValue()) {
                int i;
                ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                businessObject.setArrListBusinessObj(arrListBusinessObj);
                if ((businessObject instanceof Playlist) && arrListBusinessObj != null) {
                    ArrayList arrayList2 = new ArrayList();
                    for (i = 0; i < arrListBusinessObj.size(); i++) {
                        Track track = (Track) arrListBusinessObj.get(i);
                        if (!track.isLocalMedia()) {
                            arrayList2.add(track);
                        }
                    }
                    businessObject.setArrListBusinessObj(arrayList2);
                }
                int a = this.o.a(businessObject, (ArrayList) arrayList);
                DownloadStatus a2 = this.o.a(a, businessObject);
                i = Integer.parseInt(businessObject.getBusinessObjId());
                if (z) {
                    a(i, a, a2);
                    a(i, ((Track) businessObject).isFreeDownloadEnabled());
                } else {
                    a(i, a, a2, arrListBusinessObj);
                }
                businessObject.setArrListBusinessObj(arrListBusinessObj);
                if (ap.a().m()) {
                    i();
                }
                c().d();
                e(businessObject.getBusinessObjId());
            }
        }
    }

    public DownloadStatus a(int i, int i2) {
        DownloadStatus downloadStatus = DownloadStatus.QUEUED;
        if (i2 == 0 && i == c().k()) {
            return DownloadStatus.DOWNLOADING;
        }
        if (i2 == 1) {
            return DownloadStatus.DOWNLOADED;
        }
        if (i2 == -2) {
            return DownloadStatus.PAUSED;
        }
        return i2 == -1 ? DownloadStatus.TRIED_BUT_FAILED : downloadStatus;
    }

    public DownloadStatus b(int i, int i2) {
        DownloadStatus downloadStatus = DownloadStatus.QUEUED;
        switch (i2) {
            case -2:
                if (i(i) != 0) {
                    return DownloadStatus.PARTIALLY_DOWNLOADED;
                }
                return DownloadStatus.PAUSED;
            case -1:
                if (i(i) == j(i)) {
                    return DownloadStatus.DOWNLOADED;
                }
                return DownloadStatus.PARTIALLY_DOWNLOADED;
            case 0:
                return DownloadStatus.DOWNLOADING;
            case 1:
                return DownloadStatus.QUEUED;
            default:
                return downloadStatus;
        }
    }

    public void b(int i) {
        this.l.remove(Integer.valueOf(i));
        this.n.remove(Integer.valueOf(i));
    }

    public void c(int i) {
        this.m.remove(Integer.valueOf(i));
    }

    public void a(int i, int i2, DownloadStatus downloadStatus) {
        Object downloadStatus2;
        if (downloadStatus2 == null) {
            downloadStatus2 = a(i, i2);
        }
        this.l.put(Integer.valueOf(i), downloadStatus2);
    }

    public void a(int i, boolean z) {
        if (z) {
            this.n.put(Integer.valueOf(i), Boolean.TRUE);
        }
    }

    public void a(int i, int i2, DownloadStatus downloadStatus, ArrayList<BusinessObject> arrayList) {
        Object downloadStatus2;
        if (downloadStatus2 == null) {
            downloadStatus2 = b(i, i2);
        }
        this.m.put(Integer.valueOf(i), downloadStatus2);
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                i2 = Integer.parseInt(((BusinessObject) it.next()).getBusinessObjId());
                downloadStatus2 = (DownloadStatus) this.l.get(Integer.valueOf(i2));
                if (downloadStatus2 == null || downloadStatus2 == DownloadStatus.PAUSED || downloadStatus2 == DownloadStatus.TRIED_BUT_FAILED) {
                    this.l.put(Integer.valueOf(i2), DownloadStatus.QUEUED);
                }
            }
        }
    }

    public void a(BusinessObject businessObject) {
        a(businessObject, null);
    }

    public void a(BusinessObject businessObject, Context context) {
        String str;
        StringBuilder stringBuilder;
        String str2 = "";
        boolean z = businessObject instanceof Track;
        int i = 0;
        if (z) {
            str2 = businessObject.getEnglishName();
            str = "Track";
        } else {
            boolean isUserCreatedPlaylist;
            if (businessObject instanceof Playlist) {
                str = "Playlist";
                isUserCreatedPlaylist = ((Playlist) businessObject).isUserCreatedPlaylist();
            } else {
                if (businessObject instanceof Album) {
                    str2 = "Album";
                }
                str = str2;
                isUserCreatedPlaylist = false;
            }
            if (businessObject.getArrListBusinessObj() != null) {
                i = businessObject.getArrListBusinessObj().size();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(businessObject.getEnglishName());
            stringBuilder.append(" - ");
            stringBuilder.append(i);
            i = isUserCreatedPlaylist;
            str2 = stringBuilder.toString();
        }
        if (z && ((Track) businessObject).isFreeDownloadEnabled()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Free Download_");
            stringBuilder.append(businessObject.getBusinessObjId());
            u.a().a("Download-start", "Download", stringBuilder.toString());
        } else {
            u.a().a("Download-start", str, str2);
        }
        if (!("-100".equals(businessObject.getBusinessObjId()) || businessObject.isFavorite().booleanValue() || i != 0)) {
            businessObject.setFavorite(Boolean.valueOf(true));
            ap.a().a(context, businessObject, true);
        }
        a(businessObject);
    }

    public boolean a(ArrayList<?> arrayList, int i, boolean z) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Track track = (Track) arrayList.get(i2);
            if (!track.isLocalMedia()) {
                DownloadStatus e = c().e(Integer.parseInt(track.getBusinessObjId()));
                if (!(e == DownloadStatus.QUEUED || e == DownloadStatus.DOWNLOADED)) {
                    this.o.b(Integer.parseInt(track.getBusinessObjId()), 0);
                }
            }
        }
        return b((ArrayList) arrayList, i, z);
    }

    public void a(boolean z, Track track) {
        if (SdCardManager.a().e().isSuccess().booleanValue()) {
            ArrayList s = c().s(1);
            int size = s.size();
            try {
                Date c = c(5, -30);
                Date c2 = c(10, -24);
                Iterator it = s.iterator();
                int i = 0;
                int i2 = 0;
                long j = 0;
                while (it.hasNext()) {
                    long downloadTime = ((OfflineTrack) ((BusinessObject) it.next())).getDownloadTime();
                    if (downloadTime >= c.getTime()) {
                        i++;
                    }
                    if (downloadTime >= c2.getTime()) {
                        i2++;
                    }
                    if (downloadTime > j) {
                        j = downloadTime;
                    }
                }
                if (i < Constants.L && size < Constants.M && i2 == 0) {
                    String str;
                    Date date = new Date(j);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                    if (j == 0) {
                        str = "00/00/0000";
                    } else {
                        str = simpleDateFormat.format(date);
                    }
                    String str2 = str;
                    if (z) {
                        a(track, i, size);
                        return;
                    } else {
                        c(str2, i, size);
                        return;
                    }
                }
                return;
            } catch (Exception unused) {
                return;
            }
        }
        u.a().a("Smart Download", "Low Memory", String.valueOf(SdCardManager.a(SdCardManager.a().a(STORAGE_TYPE.INTERNAL_STORAGE))));
    }

    public Date c(int i, int i2) {
        Date time = Calendar.getInstance().getTime();
        Calendar instance = Calendar.getInstance();
        instance.setTime(time);
        instance.add(i, i2);
        return instance.getTime();
    }

    public void b(boolean z) {
        this.y = z;
    }

    private void c(String str, final int i, final int i2) {
        URLManager uRLManager = new URLManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://apiv2.gaana.com/smart-download/details?token=");
        stringBuilder.append(GaanaApplication.getInstance().getCurrentUser().getAuthToken());
        stringBuilder.append("&last_download=");
        stringBuilder.append(str);
        uRLManager.a(stringBuilder.toString());
        uRLManager.a(SmartDownloadsData.class);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                SmartDownloadsData smartDownloadsData = (SmartDownloadsData) obj;
                Constants.bI = smartDownloadsData.getCTAText();
                Constants.bH = smartDownloadsData.getSCTAText();
                Constants.bG = smartDownloadsData.getEntityDescription();
                Constants.bJ = smartDownloadsData.getTitle();
                Constants.bF = smartDownloadsData.getSettingsMessage();
                Constants.bK = smartDownloadsData.getSnackbar_CTA();
                Constants.bL = smartDownloadsData.getSnackbar_text();
                ArrayList tracks = smartDownloadsData.getTracks();
                if (tracks != null) {
                    ArrayList arrayList = new ArrayList(tracks.subList(0, DownloadManager.this.a(tracks.size(), i, i2)));
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        Track track = (Track) it.next();
                        track.setBusinessObjType(BusinessObjectType.Tracks);
                        track.setSmartDownload(1);
                    }
                    if (arrayList.size() > 0) {
                        DownloadManager.this.y = true;
                        DownloadManager.c().b(arrayList, -100, true);
                    }
                }
            }
        }, uRLManager);
    }

    private void a(Track track, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        if (a(1, i, i2) > 0) {
            track.setBusinessObjType(BusinessObjectType.Tracks);
            track.setSmartDownload(1);
            arrayList.add(track);
            this.y = true;
            u.a().a("Smart Download", "Repeat", track.getBusinessObjId());
            c().b(arrayList, -100, true);
        }
    }

    private int a(int i, int i2, int i3) {
        if (i3 >= Constants.M || i2 >= Constants.L) {
            return 0;
        }
        if (i3 + i <= Constants.M) {
            return i2 + i > Constants.L ? Constants.L - i2 : i;
        } else {
            i = Constants.M - i3;
            return i2 + i > Constants.L ? Constants.L - i2 : i;
        }
    }

    public boolean b(ArrayList<?> arrayList, int i, boolean z) {
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Track track = (Track) arrayList.get(i2);
            if (!(track.isLocalMedia() || c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue())) {
                arrayList2.add(track);
            }
        }
        boolean a = this.o.a(arrayList2, i, z);
        if (a) {
            c().d();
            e("-1");
        }
        return a;
    }

    public void a(int i, DownloadHTTPStatus downloadHTTPStatus) {
        this.o.a(i, downloadHTTPStatus);
    }

    public void d(int i, int i2) {
        this.o.b(i, i2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(i);
        e(stringBuilder.toString());
    }

    public void d(int i) {
        this.o.e(i);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(i);
        b(stringBuilder.toString(), c.b);
    }

    public void a(int i, ArrayList<Integer> arrayList) {
        this.o.a(i, (ArrayList) arrayList);
    }

    public void a(ArrayList<String> arrayList) {
        this.o.a((ArrayList) arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            SdCardManager.a().g(String.valueOf((String) it.next()));
        }
    }

    public DownloadStatus e(int i) {
        return (this.l == null || this.l.size() == 0) ? null : (DownloadStatus) this.l.get(Integer.valueOf(i));
    }

    public boolean f(int i) {
        return this.n != null && this.n.containsKey(Integer.valueOf(i));
    }

    public ArrayList<String> b(String str) {
        ArrayList arrayList = new ArrayList();
        if (this.x != null && this.x.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.x.get(str);
            if (arrayList2 != null && arrayList2.size() > 0) {
                arrayList.addAll(arrayList2);
            }
        }
        return arrayList;
    }

    public ArrayList<Integer> g(int i) {
        return this.o.f(i);
    }

    public DownloadStatus h(int i) {
        return (this.m == null || this.m.size() == 0) ? null : (DownloadStatus) this.m.get(Integer.valueOf(i));
    }

    public int i(int i) {
        return this.o.i(i);
    }

    public int j(int i) {
        return this.o.h(i);
    }

    public int k() {
        return this.q;
    }

    public void k(int i) {
        this.q = i;
    }

    public Boolean b(BusinessObject businessObject) {
        if (ap.a().a(businessObject, null)) {
            return this.o.j(Integer.parseInt(businessObject.getBusinessObjId()));
        }
        return Boolean.valueOf(false);
    }

    public Boolean l(int i) {
        if (this.n.containsKey(Integer.valueOf(i))) {
            return Boolean.valueOf(true);
        }
        if (!ap.a().j()) {
            return Boolean.valueOf(false);
        }
        if (ap.a().j() && !ap.a().h()) {
            return Boolean.valueOf(false);
        }
        if (ap.a().m()) {
            return this.o.l(i);
        }
        return this.o.k(i);
    }

    public Boolean a(Track track) {
        boolean z = track.isFreeDownloadEnabled() && this.o.k(Integer.parseInt(track.getBusinessObjId())).booleanValue();
        return Boolean.valueOf(z);
    }

    public int l() {
        return this.o.m();
    }

    public void m() {
        this.o.q();
    }

    public int n() {
        return this.o.n();
    }

    public void o() {
        if (this.r != null && !this.s) {
            LocalBroadcastManager.getInstance(GaanaApplication.getContext()).registerReceiver(this.r, new IntentFilter("broadcast_intent_download_service"));
            this.s = true;
        }
    }

    public int m(int i) {
        return this.o.b(i);
    }

    public boolean c(String str) {
        return this.o.a(str);
    }

    public int p() {
        return this.o.h();
    }

    public int q() {
        return this.o.i();
    }

    public int r() {
        return this.o.j();
    }

    public void s() {
        if (this.B != null && Util.j(GaanaApplication.getContext())) {
            LocalBroadcastManager.getInstance(GaanaApplication.getContext()).unregisterReceiver(this.B);
        }
        if (this.r != null && this.s) {
            LocalBroadcastManager.getInstance(GaanaApplication.getContext()).unregisterReceiver(this.r);
        }
        this.s = false;
    }

    public void a(String str, String str2) {
        this.u.a("PREFF_LAST_DOWNLOADE_TRACK_ID", str, true);
        this.u.a("PREFF_LAST_DOWNLOADE_TRACK_STATUS", str2, true);
    }

    public String e(int i, int i2) {
        String miniPackIdsForDownload;
        String str;
        String substring;
        StringBuilder stringBuilder;
        this.p = com.constants.c.v;
        HashMap hashMap = new HashMap();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("");
        stringBuilder2.append(i);
        hashMap.put("track_id", stringBuilder2.toString());
        hashMap.put("delivery_type", "download");
        hashMap.put("connection_type", e.ad);
        if (i2 == 1) {
            hashMap.put("download_type", "1");
        }
        String str2 = "-1";
        switch (this.u.b("PREFERENCE_KEY_SYNC_QUALITY", 1, true)) {
            case 0:
                str2 = "medium";
                break;
            case 1:
                str2 = "high";
                break;
            case 2:
                str2 = "extreme";
                break;
        }
        if (!str2.equalsIgnoreCase("-1")) {
            hashMap.put("quality", str2);
        }
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append("");
        stringBuilder2.append(i);
        hashMap.put("hashcode", Util.a(Util.b(stringBuilder2.toString()), Constants.bd));
        if (this.t.getCurrentUser().getAuthToken() != null) {
            hashMap.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, this.t.getCurrentUser().getAuthToken());
        }
        String c = this.u.c("PREFF_LAST_DOWNLOADE_TRACK_ID", true);
        Object c2 = this.u.c("PREFF_LAST_DOWNLOADE_TRACK_STATUS", true);
        if (!TextUtils.isEmpty(c)) {
            hashMap.put("previous_track_downloaded", c);
            if (TextUtils.isEmpty(c2)) {
                c2 = CBConstant.FAIL;
            }
            hashMap.put("previous_track_status", c2);
        }
        if (ap.a().m()) {
            miniPackIdsForDownload = GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getMiniPackIdsForDownload();
            String[] split = !TextUtils.isEmpty(miniPackIdsForDownload) ? miniPackIdsForDownload.split(",") : null;
            if (split != null && split.length > 0) {
                int length = split.length;
                int i3 = 0;
                while (i3 < length) {
                    str = split[i3];
                    ArrayList b = b(str);
                    if (b == null || b.size() <= 0 || !b.contains(String.valueOf(i))) {
                        i3++;
                    } else {
                        substring = str.substring(0, 2);
                        c2 = str.substring(2);
                        if (substring.equalsIgnoreCase("AR")) {
                            c2 = ap.a().g(c2);
                        }
                        hashMap.put("entity_id", c2);
                        hashMap.put("entity_type", substring);
                    }
                }
            }
        }
        try {
            String a = o.a().a(this.p, hashMap, false, 30000).a();
            if (a != null) {
                try {
                    JSONObject jSONObject = new JSONObject(a);
                    a = jSONObject.getString("status");
                    substring = jSONObject.getString("data");
                    String str3 = "";
                    if (jSONObject.has("error_code")) {
                        StringBuilder stringBuilder3;
                        str = jSONObject.getString("error_code");
                        if (jSONObject.has(AccountKitGraphConstants.BODY_ERROR_MESSAGE_KEY)) {
                            str3 = jSONObject.getString(AccountKitGraphConstants.BODY_ERROR_MESSAGE_KEY);
                        }
                        if (!TextUtils.isEmpty(str)) {
                            if (str.equalsIgnoreCase(d)) {
                                miniPackIdsForDownload = d;
                                d(String.valueOf(i));
                            } else if (str.equalsIgnoreCase(g)) {
                                miniPackIdsForDownload = g;
                                d(String.valueOf(i));
                            } else if (str.equalsIgnoreCase(e)) {
                                miniPackIdsForDownload = k;
                                this.t.setAuthenticationStatus(false);
                            } else if (str.equalsIgnoreCase(f)) {
                                miniPackIdsForDownload = f;
                            }
                            Util.a(str3, i, str);
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append("URL not fetched - ErrorCode : ");
                            stringBuilder3.append(str);
                            str = stringBuilder3.toString();
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(i);
                            stringBuilder3.append(" - ");
                            stringBuilder3.append(str2);
                            u.a().a("DownloadFailure", str, stringBuilder3.toString());
                        }
                        miniPackIdsForDownload = null;
                        Util.a(str3, i, str);
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("URL not fetched - ErrorCode : ");
                        stringBuilder3.append(str);
                        str = stringBuilder3.toString();
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(i);
                        stringBuilder3.append(" - ");
                        stringBuilder3.append(str2);
                        u.a().a("DownloadFailure", str, stringBuilder3.toString());
                    } else {
                        if (TextUtils.isEmpty(substring)) {
                            StringBuilder stringBuilder4 = new StringBuilder();
                            stringBuilder4.append(i);
                            stringBuilder4.append(" - ");
                            stringBuilder4.append(str2);
                            u.a().a("DownloadFailure", "URL not fetched - URL Blank", stringBuilder4.toString());
                        }
                        miniPackIdsForDownload = null;
                    }
                    if (a.equalsIgnoreCase("1")) {
                        return Util.l(substring);
                    }
                    if (!a.equalsIgnoreCase("0")) {
                        miniPackIdsForDownload = null;
                    }
                    return miniPackIdsForDownload;
                } catch (JSONException unused) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(i);
                    stringBuilder.append(" - ");
                    stringBuilder.append(str2);
                    u.a().a("DownloadFailure", "URL not fetched - Invalid JSON", stringBuilder.toString());
                    return null;
                } catch (Exception e) {
                    Util.a("Network Failure", i, "");
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("URL not fetched - Network Failure - ");
                    stringBuilder.append(e.getMessage());
                    a = stringBuilder.toString();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(i);
                    stringBuilder.append(" - ");
                    stringBuilder.append(str2);
                    u.a().a("DownloadFailure", a, stringBuilder.toString());
                    return null;
                }
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            stringBuilder.append(" - ");
            stringBuilder.append(str2);
            u.a().a("DownloadFailure", "URL not fetched - Network Failure", stringBuilder.toString());
            Util.a("Network Failure", i, "");
            return null;
        } catch (Exception e2) {
            StringBuilder stringBuilder5 = new StringBuilder();
            stringBuilder5.append(i);
            stringBuilder5.append(" - ");
            stringBuilder5.append(str2);
            u.a().a("DownloadFailure", "URL not fetched - Network Failure", stringBuilder5.toString());
            Util.a("Network Failure", i, "");
            ThrowableExtension.printStackTrace(e2);
            return null;
        }
    }

    public String b(int i, boolean z) {
        if (z) {
            u(i);
        }
        SdCardManager a = SdCardManager.a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(i));
        stringBuilder.append(com.utilities.i.a);
        String e = a.e(stringBuilder.toString());
        return TextUtils.isEmpty(e) ? SdCardManager.a().e(String.valueOf(i)) : e;
    }

    private void u(int i) {
        String e = SdCardManager.a().e(String.valueOf(i));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e);
        stringBuilder.append(".tmp");
        String stringBuilder2 = stringBuilder.toString();
        try {
            File file = new File(e);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                File file2 = new File(stringBuilder2);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[1024];
                int i2 = 0;
                while (bufferedInputStream.read(bArr) != -1) {
                    if (i2 == 0) {
                        for (int i3 = 0; i3 < bArr.length; i3++) {
                            if (bArr[i3] != Byte.MIN_VALUE) {
                                bArr[i3] = (byte) (bArr[i3] ^ -1);
                            }
                        }
                    }
                    fileOutputStream.write(bArr);
                    i2++;
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                bufferedInputStream.close();
                fileInputStream.close();
                StorageUtils.delete(file);
                file2.renameTo(new File(e));
            }
        } catch (FileNotFoundException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (IOException e3) {
            ThrowableExtension.printStackTrace(e3);
        } catch (Exception unused) {
        }
    }

    public void t() {
        i = new DownloadManager();
        f();
    }

    public void u() {
        this.o.s();
        P();
        i = new DownloadManager();
        f();
    }

    public Boolean o(int i) {
        return Boolean.valueOf(SdCardManager.a().b(String.valueOf(i)));
    }

    private void P() {
        com.i.d.a(new Runnable() {
            public void run() {
                Process.setThreadPriority(10);
                SdCardManager.a().c();
            }
        });
    }

    public void p(int i) {
        final ArrayList g = this.o.g(i);
        if (g != null) {
            com.i.d.a(new Runnable() {
                public void run() {
                    Process.setThreadPriority(10);
                    Iterator it = g.iterator();
                    while (it.hasNext()) {
                        SdCardManager.a().g(String.valueOf((Integer) it.next()));
                    }
                }
            });
        }
    }

    public void c(int i, boolean z) {
        if (z) {
            p(i);
            return;
        }
        ArrayList g = this.o.g(i);
        if (g != null) {
            Iterator it = g.iterator();
            while (it.hasNext()) {
                SdCardManager.a().g(String.valueOf((Integer) it.next()));
            }
        }
    }

    public void q(final int i) {
        com.i.d.a(new Runnable() {
            public void run() {
                Process.setThreadPriority(10);
                SdCardManager.a().g(String.valueOf(i));
            }
        });
    }

    public void d(String str) {
        try {
            String.valueOf(GaanaApplication.getInstance().getListingComponents().a().getBusinessObjId());
        } catch (Exception unused) {
        }
        this.o.c(Integer.parseInt(str), -100);
        f();
        q(Integer.parseInt(str));
        b(str, c.c);
    }

    public void a(ArrayList<String> arrayList, boolean z) {
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                String str = (String) arrayList.get(i);
                if (z) {
                    d(str);
                } else {
                    p(Integer.parseInt(str));
                    d(Integer.parseInt(str));
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(k());
        e(stringBuilder.toString());
    }

    public void e(String str) {
        Intent intent = new Intent("broadcast_intent_download_service");
        intent.putExtra("track_id", Integer.parseInt(str));
        intent.putExtra("has_downloaded", -3);
        LocalBroadcastManager.getInstance(this.t.getApplicationContext()).sendBroadcast(intent);
    }

    private void b(String str, int i) {
        Intent intent = new Intent("broadcast_intent_download_service");
        intent.putExtra("track_id", Integer.parseInt(str));
        intent.putExtra("item_deleted", i);
        intent.putExtra("has_downloaded", -4);
        LocalBroadcastManager.getInstance(this.t.getApplicationContext()).sendBroadcast(intent);
    }

    public void r(int i) {
        this.o.d(i, -2);
        e(String.valueOf(i));
    }

    public void b(Track track) {
        u.a().a("Download-resume", "Track", track.getEnglishName());
        this.o.a(track);
        c().d();
        e(String.valueOf(track.getBusinessObjId()));
    }

    public void c(BusinessObject businessObject) {
        String b = this.o.b(businessObject.getBusinessObjId());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(businessObject.getEnglishName());
        stringBuilder.append(" ");
        stringBuilder.append(businessObject.getCount());
        u.a().a("Download-resume", b, stringBuilder.toString());
        int intValue = Integer.valueOf(businessObject.getBusinessObjId()).intValue();
        this.o.m(intValue);
        c().d();
        e(String.valueOf(intValue));
    }

    public boolean v() {
        this.v = this.u.b(c, true, false);
        return this.v;
    }

    public void c(boolean z) {
        this.v = z;
        this.u.a(c, z, false);
    }

    /* JADX WARNING: Missing block: B:12:0x0023, code skipped:
            return false;
     */
    public boolean w() {
        /*
        r3 = this;
        r0 = r3.t;
        r0 = r0.isAppInOfflineMode();
        r1 = 0;
        if (r0 != 0) goto L_0x0023;
    L_0x0009:
        r0 = r3.t;
        r0 = com.utilities.Util.j(r0);
        if (r0 != 0) goto L_0x0012;
    L_0x0011:
        goto L_0x0023;
    L_0x0012:
        r0 = r3.k();
        r2 = -1;
        if (r0 != r2) goto L_0x001a;
    L_0x0019:
        return r1;
    L_0x001a:
        r0 = r3.v();
        if (r0 != 0) goto L_0x0021;
    L_0x0020:
        return r1;
    L_0x0021:
        r0 = 1;
        return r0;
    L_0x0023:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.managers.DownloadManager.w():boolean");
    }

    public BusinessObject f(String str) {
        BusinessObject businessObject = new BusinessObject();
        ArrayList arrayList = new ArrayList();
        Iterator it = this.o.a(str, c.b).iterator();
        while (it.hasNext()) {
            BusinessObject businessObject2 = (BusinessObject) it.next();
            if (!(businessObject2 == null || !(businessObject2 instanceof Playlist) || businessObject2.getBusinessObjId().equals("-100"))) {
                businessObject2.setBusinessObjType(BusinessObjectType.Playlists);
                ((Playlist) businessObject2).setSyncStatus(PlaylistSyncManager.getInstance().getPlaylistSyncStatus(businessObject2.getBusinessObjId()));
                arrayList.add(businessObject2);
            }
        }
        businessObject.setArrListBusinessObj(arrayList);
        return businessObject;
    }

    public BusinessObject g(String str) {
        BusinessObject businessObject = new BusinessObject();
        ArrayList arrayList = new ArrayList();
        Iterator it = this.o.a(str, c.a).iterator();
        while (it.hasNext()) {
            BusinessObject businessObject2 = (BusinessObject) it.next();
            if (businessObject2 != null && (businessObject2 instanceof Album)) {
                businessObject2.setBusinessObjType(BusinessObjectType.Albums);
                arrayList.add(businessObject2);
            }
        }
        businessObject.setArrListBusinessObj(arrayList);
        return businessObject;
    }

    public ArrayList<BusinessObject> a(String str, int i) {
        ArrayList arrayList = new ArrayList();
        Iterator it;
        BusinessObject businessObject;
        if (i != c.c) {
            it = this.o.a(str, i).iterator();
            while (it.hasNext()) {
                businessObject = (BusinessObject) it.next();
                if (businessObject != null) {
                    if (businessObject instanceof Album) {
                        businessObject.setBusinessObjType(BusinessObjectType.Albums);
                        arrayList.add(businessObject);
                    } else if (businessObject instanceof Playlist) {
                        businessObject.setBusinessObjType(BusinessObjectType.Playlists);
                        arrayList.add(businessObject);
                    }
                }
            }
        } else {
            it = this.o.a(str, true, false, -1, -1).iterator();
            while (it.hasNext()) {
                businessObject = (BusinessObject) it.next();
                if (businessObject != null) {
                    businessObject.setBusinessObjType(BusinessObjectType.Tracks);
                    arrayList.add(businessObject);
                }
            }
        }
        return arrayList;
    }

    public BusinessObject a(String str, int i, boolean z, boolean z2, int i2, int i3, int i4) {
        int i5 = i;
        BusinessObject businessObject = new BusinessObject();
        if (i5 != c.c) {
            businessObject.setArrListBusinessObj(this.o.a(str, i5, z, z2, i2, i3, i4));
            if (i5 == c.b) {
                businessObject.setBusinessObjType(BusinessObjectType.Playlists);
            } else {
                businessObject.setBusinessObjType(BusinessObjectType.Albums);
            }
        } else {
            businessObject.setBusinessObjType(BusinessObjectType.Tracks);
            businessObject.setArrListBusinessObj(this.o.a(str, true, false, i2, i3, i4));
        }
        return businessObject;
    }

    public ArrayList<BusinessObject> s(int i) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.o.a(null, i, -1, -1).iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject != null) {
                businessObject.setBusinessObjType(BusinessObjectType.Tracks);
                arrayList.add(businessObject);
            }
        }
        return arrayList;
    }

    public ArrayList<String> a(BusinessObjectType businessObjectType) {
        if (businessObjectType == BusinessObjectType.Tracks) {
            return this.o.b();
        }
        if (businessObjectType == BusinessObjectType.Playlists) {
            return this.o.a(c.b);
        }
        return businessObjectType == BusinessObjectType.Albums ? this.o.a(c.a) : null;
    }

    public ArrayList<String> x() {
        return this.o.c();
    }

    public BusinessObject a(String str, boolean z, boolean z2, int i, int i2) {
        BusinessObject businessObject = new BusinessObject();
        businessObject.setArrListBusinessObj(this.o.a(str, z, z2, i, i2));
        return businessObject;
    }

    public Track h(String str) {
        return (Track) this.o.a(str, true);
    }

    private BusinessObject l(String str) {
        return this.o.c(str);
    }

    public BusinessObject a(BusinessObjectType businessObjectType, String str) {
        switch (businessObjectType) {
            case Tracks:
                return h(str);
            case Playlists:
            case Albums:
                return l(str);
            default:
                return null;
        }
    }

    public void y() {
        this.o.t();
    }

    public void z() {
        this.o.r();
    }

    public void A() {
        this.o.u();
        FileDownloadService.a(false);
        f();
        k(-1);
    }

    public BusinessObject a(String str, boolean z) {
        return this.o.a(str, z);
    }

    public void d(BusinessObject businessObject) {
        if (businessObject.getArrListBusinessObj() == null) {
            return;
        }
        if (businessObject instanceof Playlist) {
            BusinessObject c = this.o.c(businessObject.getBusinessObjId());
            if (c != null) {
                Playlist playlist = (Playlist) businessObject;
                Date lastModifiedDate = playlist.getLastModifiedDate();
                Date lastModifiedDate2 = ((Playlist) c).getLastModifiedDate();
                if (lastModifiedDate2 == null || !(lastModifiedDate == null || lastModifiedDate.compareTo(lastModifiedDate2) == 0)) {
                    ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < arrListBusinessObj.size(); i++) {
                        Track track = (Track) arrListBusinessObj.get(i);
                        if (!track.isLocalMedia()) {
                            arrayList.add(track);
                        }
                    }
                    businessObject.setArrListBusinessObj(arrayList);
                    this.o.a(playlist, Integer.parseInt(businessObject.getBusinessObjId()));
                    businessObject.setArrListBusinessObj(arrListBusinessObj);
                }
            }
        } else if (businessObject instanceof Album) {
            this.o.a((Album) businessObject, Integer.parseInt(businessObject.getBusinessObjId()));
        }
    }

    public void a(String str, BusinessObject businessObject) {
        this.o.a(str, businessObject);
    }

    public int B() {
        return this.o.k();
    }

    public int C() {
        return this.o.l();
    }

    public boolean D() {
        return this.w;
    }

    public void d(boolean z) {
        this.w = z;
    }

    public Track i(String str) {
        return this.o.d(str);
    }

    public boolean E() {
        return this.u.b("PREF_DOWNLOAD_LIST_SHOW_PARAMETER_QUEUED", true, true);
    }

    public boolean F() {
        return this.u.b("PREF_DOWNLOAD_LIST_SHOW_SMART_DOWNLOADS", true, true);
    }

    public boolean G() {
        return this.u.b("PREF_DOWNLOAD_LIST_SHOW_PARAMETER_DOWNLOADED", true, true);
    }

    public boolean H() {
        return this.u.b("PREF_DOWNLOAD_LIST_SHOW_EXPIRED_DOWNLOADS", true, true);
    }

    public void e(boolean z) {
        this.u.a("PREF_DOWNLOAD_LIST_SHOW_PARAMETER_DOWNLOADED", z, true);
    }

    public boolean I() {
        return this.u.b("PREF_DOWNLOAD_LIST_SHOW_PARAMETER_GAANA_MINI", true, true);
    }

    public void f(boolean z) {
        this.u.a("PREF_DOWNLOAD_LIST_SHOW_PARAMETER_GAANA_MINI", z, true);
    }

    public void g(boolean z) {
        this.u.a("PREF_DOWNLOAD_LIST_SHOW_PARAMETER_QUEUED", z, true);
    }

    public void h(boolean z) {
        this.u.a("PREF_DOWNLOAD_LIST_SHOW_SMART_DOWNLOADS", z, true);
    }

    public void i(boolean z) {
        this.u.a("PREF_DOWNLOAD_LIST_SHOW_EXPIRED_DOWNLOADS", z, true);
    }

    public boolean J() {
        return this.y;
    }

    public String j(boolean z) {
        String str = "has_downloaded!=-2";
        boolean G = G();
        boolean E = E();
        boolean F = F();
        boolean H = H();
        boolean k = ap.a().k();
        if (G && E && F && (!Util.v() || H)) {
            return str;
        }
        if (!G && !E && !F && (!Util.v() || !H)) {
            return str;
        }
        if (!z) {
            StringBuilder stringBuilder;
            CharSequence charSequence = "";
            if (G) {
                String str2 = "( has_downloaded=1";
                if (!F) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str2);
                    stringBuilder.append(" and smart_download=0");
                    str2 = stringBuilder.toString();
                }
                if (k && !H) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str2);
                    stringBuilder.append(" and free_download=1");
                    str2 = stringBuilder.toString();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(" ) ");
                charSequence = stringBuilder.toString();
            }
            if (F) {
                if (TextUtils.isEmpty(charSequence)) {
                    charSequence = "(smart_download=1 and has_downloaded!=-2)";
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(charSequence);
                    stringBuilder.append(" or (smart_download=1 and has_downloaded!=-2)");
                    charSequence = stringBuilder.toString();
                }
            }
            if (k && H) {
                if (TextUtils.isEmpty(charSequence)) {
                    charSequence = "(free_download=0 and has_downloaded!=-2)";
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(charSequence);
                    stringBuilder.append(" or (free_download=0 and has_downloaded!=-2)");
                    charSequence = stringBuilder.toString();
                }
            }
            str = charSequence;
            if (E) {
                if (TextUtils.isEmpty(str)) {
                    str = "has_downloaded=0";
                } else {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str);
                    stringBuilder2.append(" or has_downloaded=0");
                    str = stringBuilder2.toString();
                }
            }
        }
        return str;
    }

    public void a(int i, Playlist playlist) {
        this.o.a(i, playlist);
    }

    public Boolean j(String str) {
        if (ap.a().m()) {
            String miniPackIdsForDownload = GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getMiniPackIdsForDownload();
            String[] strArr = null;
            if (!TextUtils.isEmpty(miniPackIdsForDownload)) {
                strArr = miniPackIdsForDownload.split(",");
            }
            if (strArr != null && strArr.length > 0) {
                for (String b : strArr) {
                    ArrayList b2 = b(b);
                    if (b2 != null && b2.size() > 0 && b2.contains(str)) {
                        return Boolean.valueOf(true);
                    }
                }
            }
        }
        return Boolean.valueOf(false);
    }

    public Playlist c(Track track) {
        ArrayList artists = track.getArtists();
        ArrayList gaanaMiniSubDetails = this.t.getCurrentUser().getUserSubscriptionData().getGaanaMiniSubDetails();
        Playlist playlist = null;
        int i = 0;
        while (i < artists.size()) {
            if (gaanaMiniSubDetails != null) {
                Playlist playlist2 = playlist;
                for (int i2 = 0; i2 < gaanaMiniSubDetails.size(); i2++) {
                    GaanaMiniSubDetails gaanaMiniSubDetails2 = (GaanaMiniSubDetails) gaanaMiniSubDetails.get(i2);
                    if (gaanaMiniSubDetails2.getEntityType().equalsIgnoreCase("AR") && gaanaMiniSubDetails2.getEntityId().equalsIgnoreCase(((Artist) artists.get(i)).artist_id)) {
                        playlist2 = (Playlist) this.o.c(gaanaMiniSubDetails2.getPlaylistId());
                        if (playlist2 == null) {
                            playlist2 = PlaylistSyncManager.getInstance().getPlaylistDetails(gaanaMiniSubDetails2.getPlaylistId());
                            if (playlist2 != null) {
                                a((BusinessObject) playlist2, new ArrayList());
                            }
                        }
                        if (playlist2 != null) {
                            int j = j(Integer.parseInt(playlist2.getBusinessObjId()));
                            String downloadLimitCount = gaanaMiniSubDetails2.getDownloadLimitCount();
                            if (!TextUtils.isEmpty(downloadLimitCount) && Integer.parseInt(downloadLimitCount) > j) {
                                return playlist2;
                            }
                        }
                        continue;
                    }
                }
                playlist = playlist2;
            }
            i++;
        }
        return playlist;
    }

    public int K() {
        return (int) this.o.d();
    }

    public void L() {
        if (ap.a().j()) {
            this.o.w();
        }
    }

    public BusinessObject a(String str, int i, int i2) {
        return this.o.a(str, i, i2);
    }

    public BusinessObject b(String str, int i, int i2) {
        return this.o.b(str, i, i2);
    }

    public void t(final int i) {
        com.i.d.a(new Runnable() {
            public void run() {
                DownloadManager.this.o.n(i);
            }
        });
    }

    public int M() {
        return this.o.x();
    }

    public ArrayList<BusinessObject> N() {
        return this.o.y();
    }

    public void k(final String str) {
        final String e = SdCardManager.a().e(str);
        if (!TextUtils.isEmpty(e) && !Uri.parse(e).getPath().contains(com.utilities.i.a)) {
            com.i.d.a(new Runnable() {
                /* JADX WARNING: Unknown top exception splitter block from list: {B:90:0x0121=Splitter:B:90:0x0121, B:79:0x0105=Splitter:B:79:0x0105, B:68:0x00e9=Splitter:B:68:0x00e9, B:57:0x00ca=Splitter:B:57:0x00ca} */
                /* JADX WARNING: Removed duplicated region for block: B:106:0x0146 A:{SYNTHETIC, Splitter:B:106:0x0146} */
                /* JADX WARNING: Removed duplicated region for block: B:111:0x014e A:{Catch:{ Exception -> 0x014a }} */
                /* JADX WARNING: Removed duplicated region for block: B:113:0x0156 A:{Catch:{ Exception -> 0x014a }} */
                /* JADX WARNING: Removed duplicated region for block: B:93:0x0126 A:{SYNTHETIC, Splitter:B:93:0x0126} */
                /* JADX WARNING: Removed duplicated region for block: B:98:0x012e A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:122:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:100:0x0136 A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:82:0x010a A:{SYNTHETIC, Splitter:B:82:0x010a} */
                /* JADX WARNING: Removed duplicated region for block: B:85:0x010f A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:121:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:87:0x0117 A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:71:0x00ee A:{SYNTHETIC, Splitter:B:71:0x00ee} */
                /* JADX WARNING: Removed duplicated region for block: B:74:0x00f3 A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:120:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:76:0x00fb A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:60:0x00d2 A:{SYNTHETIC, Splitter:B:60:0x00d2} */
                /* JADX WARNING: Removed duplicated region for block: B:63:0x00d7 A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:119:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:65:0x00df A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:106:0x0146 A:{SYNTHETIC, Splitter:B:106:0x0146} */
                /* JADX WARNING: Removed duplicated region for block: B:111:0x014e A:{Catch:{ Exception -> 0x014a }} */
                /* JADX WARNING: Removed duplicated region for block: B:113:0x0156 A:{Catch:{ Exception -> 0x014a }} */
                /* JADX WARNING: Removed duplicated region for block: B:93:0x0126 A:{SYNTHETIC, Splitter:B:93:0x0126} */
                /* JADX WARNING: Removed duplicated region for block: B:98:0x012e A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:100:0x0136 A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:122:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:82:0x010a A:{SYNTHETIC, Splitter:B:82:0x010a} */
                /* JADX WARNING: Removed duplicated region for block: B:85:0x010f A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:87:0x0117 A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:121:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:71:0x00ee A:{SYNTHETIC, Splitter:B:71:0x00ee} */
                /* JADX WARNING: Removed duplicated region for block: B:74:0x00f3 A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:76:0x00fb A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:120:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:60:0x00d2 A:{SYNTHETIC, Splitter:B:60:0x00d2} */
                /* JADX WARNING: Removed duplicated region for block: B:63:0x00d7 A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:65:0x00df A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:119:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:106:0x0146 A:{SYNTHETIC, Splitter:B:106:0x0146} */
                /* JADX WARNING: Removed duplicated region for block: B:111:0x014e A:{Catch:{ Exception -> 0x014a }} */
                /* JADX WARNING: Removed duplicated region for block: B:113:0x0156 A:{Catch:{ Exception -> 0x014a }} */
                /* JADX WARNING: Removed duplicated region for block: B:93:0x0126 A:{SYNTHETIC, Splitter:B:93:0x0126} */
                /* JADX WARNING: Removed duplicated region for block: B:98:0x012e A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:122:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:100:0x0136 A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:82:0x010a A:{SYNTHETIC, Splitter:B:82:0x010a} */
                /* JADX WARNING: Removed duplicated region for block: B:85:0x010f A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:121:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:87:0x0117 A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:71:0x00ee A:{SYNTHETIC, Splitter:B:71:0x00ee} */
                /* JADX WARNING: Removed duplicated region for block: B:74:0x00f3 A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:120:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:76:0x00fb A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:60:0x00d2 A:{SYNTHETIC, Splitter:B:60:0x00d2} */
                /* JADX WARNING: Removed duplicated region for block: B:63:0x00d7 A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:119:? A:{SYNTHETIC, RETURN} */
                /* JADX WARNING: Removed duplicated region for block: B:65:0x00df A:{Catch:{ Exception -> 0x012a }} */
                /* JADX WARNING: Removed duplicated region for block: B:106:0x0146 A:{SYNTHETIC, Splitter:B:106:0x0146} */
                /* JADX WARNING: Removed duplicated region for block: B:111:0x014e A:{Catch:{ Exception -> 0x014a }} */
                /* JADX WARNING: Removed duplicated region for block: B:113:0x0156 A:{Catch:{ Exception -> 0x014a }} */
                public void run() {
                    /*
                    r9 = this;
                    r0 = 0;
                    r1 = com.managers.SdCardManager.a();	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r2 = com.managers.SdCardManager.STORAGE_TYPE.SD_CARD;	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r1 = r1.a(r2);	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r2 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r2.<init>(r1);	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r2.mkdirs();	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r1 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r3 = r0;	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r1.<init>(r3);	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r3 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
                    r3 = new byte[r3];	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r4 = new java.io.BufferedInputStream;	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r5 = new java.io.FileInputStream;	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r5.<init>(r1);	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r4.<init>(r5);	 Catch:{ InterruptedIOException | SocketException -> 0x011e, InterruptedIOException | SocketException -> 0x011e, IOException -> 0x0102, Exception -> 0x00e6, OutOfMemoryError -> 0x00c7, all -> 0x00c2 }
                    r5 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x00bc, InterruptedIOException | SocketException -> 0x00bc, IOException -> 0x00b7, Exception -> 0x00b2, OutOfMemoryError -> 0x00ad, all -> 0x00a9 }
                    r6 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x00bc, InterruptedIOException | SocketException -> 0x00bc, IOException -> 0x00b7, Exception -> 0x00b2, OutOfMemoryError -> 0x00ad, all -> 0x00a9 }
                    r6.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x00bc, InterruptedIOException | SocketException -> 0x00bc, IOException -> 0x00b7, Exception -> 0x00b2, OutOfMemoryError -> 0x00ad, all -> 0x00a9 }
                    r7 = r4;	 Catch:{ InterruptedIOException | SocketException -> 0x00bc, InterruptedIOException | SocketException -> 0x00bc, IOException -> 0x00b7, Exception -> 0x00b2, OutOfMemoryError -> 0x00ad, all -> 0x00a9 }
                    r6.append(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x00bc, InterruptedIOException | SocketException -> 0x00bc, IOException -> 0x00b7, Exception -> 0x00b2, OutOfMemoryError -> 0x00ad, all -> 0x00a9 }
                    r7 = com.utilities.i.b;	 Catch:{ InterruptedIOException | SocketException -> 0x00bc, InterruptedIOException | SocketException -> 0x00bc, IOException -> 0x00b7, Exception -> 0x00b2, OutOfMemoryError -> 0x00ad, all -> 0x00a9 }
                    r6.append(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x00bc, InterruptedIOException | SocketException -> 0x00bc, IOException -> 0x00b7, Exception -> 0x00b2, OutOfMemoryError -> 0x00ad, all -> 0x00a9 }
                    r6 = r6.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x00bc, InterruptedIOException | SocketException -> 0x00bc, IOException -> 0x00b7, Exception -> 0x00b2, OutOfMemoryError -> 0x00ad, all -> 0x00a9 }
                    r5.<init>(r2, r6);	 Catch:{ InterruptedIOException | SocketException -> 0x00bc, InterruptedIOException | SocketException -> 0x00bc, IOException -> 0x00b7, Exception -> 0x00b2, OutOfMemoryError -> 0x00ad, all -> 0x00a9 }
                    r6 = new java.io.FileOutputStream;	 Catch:{ InterruptedIOException | SocketException -> 0x00bc, InterruptedIOException | SocketException -> 0x00bc, IOException -> 0x00b7, Exception -> 0x00b2, OutOfMemoryError -> 0x00ad, all -> 0x00a9 }
                    r7 = 0;
                    r6.<init>(r5, r7);	 Catch:{ InterruptedIOException | SocketException -> 0x00bc, InterruptedIOException | SocketException -> 0x00bc, IOException -> 0x00b7, Exception -> 0x00b2, OutOfMemoryError -> 0x00ad, all -> 0x00a9 }
                    r8 = com.utilities.i.a(r6);	 Catch:{ InterruptedIOException | SocketException -> 0x00a6, InterruptedIOException | SocketException -> 0x00a6, IOException -> 0x00a3, Exception -> 0x00a0, OutOfMemoryError -> 0x009d, all -> 0x0099 }
                L_0x004a:
                    r0 = r4.read(r3);	 Catch:{ InterruptedIOException | SocketException -> 0x0096, InterruptedIOException | SocketException -> 0x0096, IOException -> 0x0093, Exception -> 0x0090, OutOfMemoryError -> 0x008d, all -> 0x0089 }
                    if (r0 <= 0) goto L_0x0054;
                L_0x0050:
                    r8.write(r3, r7, r0);	 Catch:{ InterruptedIOException | SocketException -> 0x0096, InterruptedIOException | SocketException -> 0x0096, IOException -> 0x0093, Exception -> 0x0090, OutOfMemoryError -> 0x008d, all -> 0x0089 }
                    goto L_0x004a;
                L_0x0054:
                    r0 = new java.io.File;	 Catch:{ InterruptedIOException | SocketException -> 0x0096, InterruptedIOException | SocketException -> 0x0096, IOException -> 0x0093, Exception -> 0x0090, OutOfMemoryError -> 0x008d, all -> 0x0089 }
                    r3 = new java.lang.StringBuilder;	 Catch:{ InterruptedIOException | SocketException -> 0x0096, InterruptedIOException | SocketException -> 0x0096, IOException -> 0x0093, Exception -> 0x0090, OutOfMemoryError -> 0x008d, all -> 0x0089 }
                    r3.<init>();	 Catch:{ InterruptedIOException | SocketException -> 0x0096, InterruptedIOException | SocketException -> 0x0096, IOException -> 0x0093, Exception -> 0x0090, OutOfMemoryError -> 0x008d, all -> 0x0089 }
                    r7 = r4;	 Catch:{ InterruptedIOException | SocketException -> 0x0096, InterruptedIOException | SocketException -> 0x0096, IOException -> 0x0093, Exception -> 0x0090, OutOfMemoryError -> 0x008d, all -> 0x0089 }
                    r3.append(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x0096, InterruptedIOException | SocketException -> 0x0096, IOException -> 0x0093, Exception -> 0x0090, OutOfMemoryError -> 0x008d, all -> 0x0089 }
                    r7 = com.utilities.i.a;	 Catch:{ InterruptedIOException | SocketException -> 0x0096, InterruptedIOException | SocketException -> 0x0096, IOException -> 0x0093, Exception -> 0x0090, OutOfMemoryError -> 0x008d, all -> 0x0089 }
                    r3.append(r7);	 Catch:{ InterruptedIOException | SocketException -> 0x0096, InterruptedIOException | SocketException -> 0x0096, IOException -> 0x0093, Exception -> 0x0090, OutOfMemoryError -> 0x008d, all -> 0x0089 }
                    r3 = r3.toString();	 Catch:{ InterruptedIOException | SocketException -> 0x0096, InterruptedIOException | SocketException -> 0x0096, IOException -> 0x0093, Exception -> 0x0090, OutOfMemoryError -> 0x008d, all -> 0x0089 }
                    r0.<init>(r2, r3);	 Catch:{ InterruptedIOException | SocketException -> 0x0096, InterruptedIOException | SocketException -> 0x0096, IOException -> 0x0093, Exception -> 0x0090, OutOfMemoryError -> 0x008d, all -> 0x0089 }
                    r5.renameTo(r0);	 Catch:{ InterruptedIOException | SocketException -> 0x0096, InterruptedIOException | SocketException -> 0x0096, IOException -> 0x0093, Exception -> 0x0090, OutOfMemoryError -> 0x008d, all -> 0x0089 }
                    r1.delete();	 Catch:{ InterruptedIOException | SocketException -> 0x0096, InterruptedIOException | SocketException -> 0x0096, IOException -> 0x0093, Exception -> 0x0090, OutOfMemoryError -> 0x008d, all -> 0x0089 }
                    if (r4 == 0) goto L_0x0077;
                L_0x0074:
                    r4.close();	 Catch:{ Exception -> 0x012a }
                L_0x0077:
                    if (r6 == 0) goto L_0x007f;
                L_0x0079:
                    r6.flush();	 Catch:{ Exception -> 0x012a }
                    r6.close();	 Catch:{ Exception -> 0x012a }
                L_0x007f:
                    if (r8 == 0) goto L_0x0140;
                L_0x0081:
                    r8.flush();	 Catch:{ Exception -> 0x012a }
                    r8.close();	 Catch:{ Exception -> 0x012a }
                    goto L_0x0140;
                L_0x0089:
                    r0 = move-exception;
                    r1 = r0;
                    goto L_0x0143;
                L_0x008d:
                    r0 = move-exception;
                    r1 = r0;
                    goto L_0x00b0;
                L_0x0090:
                    r0 = move-exception;
                    r1 = r0;
                    goto L_0x00b5;
                L_0x0093:
                    r0 = move-exception;
                    r1 = r0;
                    goto L_0x00ba;
                L_0x0096:
                    r0 = move-exception;
                    r1 = r0;
                    goto L_0x00bf;
                L_0x0099:
                    r1 = move-exception;
                    r8 = r0;
                    goto L_0x0143;
                L_0x009d:
                    r1 = move-exception;
                    r8 = r0;
                    goto L_0x00b0;
                L_0x00a0:
                    r1 = move-exception;
                    r8 = r0;
                    goto L_0x00b5;
                L_0x00a3:
                    r1 = move-exception;
                    r8 = r0;
                    goto L_0x00ba;
                L_0x00a6:
                    r1 = move-exception;
                    r8 = r0;
                    goto L_0x00bf;
                L_0x00a9:
                    r1 = move-exception;
                    r8 = r0;
                    goto L_0x0144;
                L_0x00ad:
                    r1 = move-exception;
                    r6 = r0;
                    r8 = r6;
                L_0x00b0:
                    r0 = r4;
                    goto L_0x00ca;
                L_0x00b2:
                    r1 = move-exception;
                    r6 = r0;
                    r8 = r6;
                L_0x00b5:
                    r0 = r4;
                    goto L_0x00e9;
                L_0x00b7:
                    r1 = move-exception;
                    r6 = r0;
                    r8 = r6;
                L_0x00ba:
                    r0 = r4;
                    goto L_0x0105;
                L_0x00bc:
                    r1 = move-exception;
                    r6 = r0;
                    r8 = r6;
                L_0x00bf:
                    r0 = r4;
                    goto L_0x0121;
                L_0x00c2:
                    r1 = move-exception;
                    r4 = r0;
                    r8 = r4;
                    goto L_0x0144;
                L_0x00c7:
                    r1 = move-exception;
                    r6 = r0;
                    r8 = r6;
                L_0x00ca:
                    com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);	 Catch:{ all -> 0x0141 }
                    java.lang.System.gc();	 Catch:{ all -> 0x0141 }
                    if (r0 == 0) goto L_0x00d5;
                L_0x00d2:
                    r0.close();	 Catch:{ Exception -> 0x012a }
                L_0x00d5:
                    if (r6 == 0) goto L_0x00dd;
                L_0x00d7:
                    r6.flush();	 Catch:{ Exception -> 0x012a }
                    r6.close();	 Catch:{ Exception -> 0x012a }
                L_0x00dd:
                    if (r8 == 0) goto L_0x0140;
                L_0x00df:
                    r8.flush();	 Catch:{ Exception -> 0x012a }
                    r8.close();	 Catch:{ Exception -> 0x012a }
                    goto L_0x0140;
                L_0x00e6:
                    r1 = move-exception;
                    r6 = r0;
                    r8 = r6;
                L_0x00e9:
                    com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);	 Catch:{ all -> 0x0141 }
                    if (r0 == 0) goto L_0x00f1;
                L_0x00ee:
                    r0.close();	 Catch:{ Exception -> 0x012a }
                L_0x00f1:
                    if (r6 == 0) goto L_0x00f9;
                L_0x00f3:
                    r6.flush();	 Catch:{ Exception -> 0x012a }
                    r6.close();	 Catch:{ Exception -> 0x012a }
                L_0x00f9:
                    if (r8 == 0) goto L_0x0140;
                L_0x00fb:
                    r8.flush();	 Catch:{ Exception -> 0x012a }
                    r8.close();	 Catch:{ Exception -> 0x012a }
                    goto L_0x0140;
                L_0x0102:
                    r1 = move-exception;
                    r6 = r0;
                    r8 = r6;
                L_0x0105:
                    com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);	 Catch:{ all -> 0x0141 }
                    if (r0 == 0) goto L_0x010d;
                L_0x010a:
                    r0.close();	 Catch:{ Exception -> 0x012a }
                L_0x010d:
                    if (r6 == 0) goto L_0x0115;
                L_0x010f:
                    r6.flush();	 Catch:{ Exception -> 0x012a }
                    r6.close();	 Catch:{ Exception -> 0x012a }
                L_0x0115:
                    if (r8 == 0) goto L_0x0140;
                L_0x0117:
                    r8.flush();	 Catch:{ Exception -> 0x012a }
                    r8.close();	 Catch:{ Exception -> 0x012a }
                    goto L_0x0140;
                L_0x011e:
                    r1 = move-exception;
                    r6 = r0;
                    r8 = r6;
                L_0x0121:
                    com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);	 Catch:{ all -> 0x0141 }
                    if (r0 == 0) goto L_0x012c;
                L_0x0126:
                    r0.close();	 Catch:{ Exception -> 0x012a }
                    goto L_0x012c;
                L_0x012a:
                    r0 = move-exception;
                    goto L_0x013d;
                L_0x012c:
                    if (r6 == 0) goto L_0x0134;
                L_0x012e:
                    r6.flush();	 Catch:{ Exception -> 0x012a }
                    r6.close();	 Catch:{ Exception -> 0x012a }
                L_0x0134:
                    if (r8 == 0) goto L_0x0140;
                L_0x0136:
                    r8.flush();	 Catch:{ Exception -> 0x012a }
                    r8.close();	 Catch:{ Exception -> 0x012a }
                    goto L_0x0140;
                L_0x013d:
                    com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                L_0x0140:
                    return;
                L_0x0141:
                    r1 = move-exception;
                    r4 = r0;
                L_0x0143:
                    r0 = r6;
                L_0x0144:
                    if (r4 == 0) goto L_0x014c;
                L_0x0146:
                    r4.close();	 Catch:{ Exception -> 0x014a }
                    goto L_0x014c;
                L_0x014a:
                    r0 = move-exception;
                    goto L_0x015d;
                L_0x014c:
                    if (r0 == 0) goto L_0x0154;
                L_0x014e:
                    r0.flush();	 Catch:{ Exception -> 0x014a }
                    r0.close();	 Catch:{ Exception -> 0x014a }
                L_0x0154:
                    if (r8 == 0) goto L_0x0160;
                L_0x0156:
                    r8.flush();	 Catch:{ Exception -> 0x014a }
                    r8.close();	 Catch:{ Exception -> 0x014a }
                    goto L_0x0160;
                L_0x015d:
                    com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
                L_0x0160:
                    throw r1;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.managers.DownloadManager$AnonymousClass7.run():void");
                }
            });
        }
    }

    public String a(ArrayList<String> arrayList, BusinessObjectType businessObjectType) {
        String str = null;
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
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
        }
        if (businessObjectType == BusinessObjectType.Playlists) {
            String str3 = com.constants.c.u;
            Iterator it2 = arrayList.iterator();
            str = str3;
            while (it2.hasNext()) {
                str3 = (String) it2.next();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append(str3);
                stringBuilder2.append(",");
                str = stringBuilder2.toString();
            }
        }
        if (str != null && str.contains(",")) {
            str = str.substring(0, str.lastIndexOf(","));
        }
        return str;
    }

    public ArrayList<String> b(ArrayList<?> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (((DownloadStatus) this.l.get(Integer.valueOf(Integer.parseInt(businessObject.getBusinessObjId())))) == null) {
                arrayList2.add(businessObject.getBusinessObjId());
            }
            if (arrayList2.size() == 30) {
                break;
            }
        }
        return arrayList2;
    }
}
