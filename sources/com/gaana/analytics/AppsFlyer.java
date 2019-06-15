package com.gaana.analytics;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.appsflyer.f;
import com.appsflyer.h;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.models.Tracks.Track;
import com.models.PlayerTrack;
import com.services.d;
import com.utilities.Util;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AppsFlyer {
    private static final String AF_TAG = "APPS_FLYER";
    private static AppsFlyer instance;
    private h appsFlyerLib = null;
    private Context context = GaanaApplication.getContext();

    private AppsFlyer() {
        if (this.appsFlyerLib == null) {
            this.appsFlyerLib = h.c();
        }
    }

    public static AppsFlyer getInstance() {
        if (instance == null) {
            instance = new AppsFlyer();
        }
        return instance;
    }

    public void initialize(String str, GaanaApplication gaanaApplication) {
        this.appsFlyerLib.a(str, getConversionListener(), GaanaApplication.getContext());
        this.appsFlyerLib.a("776891288343");
        this.appsFlyerLib.a((Application) gaanaApplication);
    }

    private f getConversionListener() {
        return new f() {
            public void onAppOpenAttribution(Map<String, String> map) {
            }

            public void onAttributionFailure(String str) {
            }

            public void onInstallConversionFailure(String str) {
            }

            public void onInstallConversionDataLoaded(Map<String, String> map) {
                if (GaanaApplication.sessionHistoryCount == 0 && map.containsKey("af_dp") && !TextUtils.isEmpty((CharSequence) map.get("af_dp"))) {
                    GaanaApplication.targetUri = (String) map.get("af_dp");
                }
            }
        };
    }

    public void updateServerUninstallToken(String str) {
        this.appsFlyerLib.b(this.context, str);
    }

    public void sendDeepLinkData(Activity activity) {
        this.appsFlyerLib.a(activity);
    }

    public void trackEvent(String str, Map<String, Object> map) {
        this.appsFlyerLib.a(this.context, str, (Map) map);
    }

    private void setCustomerId() {
        this.appsFlyerLib.b(Util.X());
    }

    public void reportPlaylistCreated(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("playlist", str);
        hashMap.put("Number", Integer.valueOf(i));
        trackEvent("create.playlist", hashMap);
    }

    public void reportDownloadSuccess(Track track) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", "Tracks");
        hashMap.put("song", track.getEnglishName());
        hashMap.put("album", track.getEnglishAlbumTitle());
        trackEvent("download", hashMap);
    }

    public void reportFavorite(BusinessObject businessObject) {
        String b = Util.b(businessObject.getBusinessObjType());
        HashMap hashMap = new HashMap();
        hashMap.put(b, businessObject.getEnglishName());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("favorite.");
        stringBuilder.append(b);
        trackEvent(stringBuilder.toString(), hashMap);
        FBAppEventsLogger.reportFavorite(businessObject);
    }

    public void reportPurchaseCompleted(ProductItem productItem, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(".");
        stringBuilder.append(productItem.getDesc().replace(" ", "_"));
        String stringBuilder2 = stringBuilder.toString();
        HashMap hashMap = new HashMap();
        hashMap.put("subscription", productItem.getDuration_days());
        hashMap.put("payment", str);
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("gaana.purchase");
        stringBuilder3.append(stringBuilder2);
        trackEvent(stringBuilder3.toString(), hashMap);
    }

    public void reportStudentPackPurchaseCompleted(ProductItem productItem, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("subscription", productItem.getDuration_days());
        hashMap.put("payment", str);
        trackEvent("gaana.purchase.Student_Pack", hashMap);
    }

    public void reportUserLogin(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", str);
        trackEvent(str, hashMap);
        setCustomerId();
    }

    public void reportUserRegistration(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", str);
        trackEvent(str, hashMap);
    }

    public void reportSearchSong(String str, String str2, boolean z, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("title", str);
        hashMap.put("type", z ? "offline" : "online");
        hashMap.put("content_type", str2);
        hashMap.put("content_id", str3);
        trackEvent("search.song", hashMap);
    }

    public void reportViewContent(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("content_name", str);
        hashMap.put("content_type", str2);
        hashMap.put("content_type", str3);
        trackEvent("view.content", hashMap);
    }

    public void reportPlayEvent(PlayerTrack playerTrack, boolean z) {
        Object obj = (!z || playerTrack.b().isLocalMedia()) ? "offline" : "online";
        Map hashMap = new HashMap();
        hashMap.put("song", playerTrack.b().getEnglishName());
        hashMap.put("album", playerTrack.b().getEnglishAlbumTitle());
        hashMap.put("section", playerTrack.f());
        hashMap.put("language", playerTrack.b().getLanguage());
        hashMap.put("type", obj);
        hashMap.put("content_type", "music");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("t");
        stringBuilder.append(playerTrack.h());
        hashMap.put("content_id", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("t");
        stringBuilder.append(playerTrack.h());
        hashMap.put("af_content_id", stringBuilder.toString());
        trackEvent("play.song", hashMap);
        if (d.a().b("AF_FirstSongPlay", true, false)) {
            hashMap = new HashMap();
            trackEvent("play.song.first", hashMap);
            d.a().a("AF_FirstSongPlay", false, false);
        }
        if (isWithin30Days()) {
            int b = d.a().b("AF_PLAY_CYCLE_TRACK_COUNT", 0, false) + 1;
            d.a().a("AF_PLAY_CYCLE_TRACK_COUNT", b, false);
            if (b == 10) {
                trackEvent("play.10songs", new HashMap());
            } else if (b == 15) {
                trackEvent("play.15songs", new HashMap());
            } else if (b == 30) {
                trackEvent("play.30songs", new HashMap());
            } else if (b == 50) {
                trackEvent("play.50songs", new HashMap());
            } else if (b == 100) {
                trackEvent("play.100songs", new HashMap());
            }
        } else {
            d.a().b("AF_PLAY_CYCLE_TRACK_COUNT", false);
        }
        if (z) {
            trackEvent("play.song.online", hashMap);
        }
        if (!TextUtils.isEmpty(playerTrack.b().getLanguage())) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("play.song.");
            stringBuilder2.append(playerTrack.b().getLanguage());
            trackEvent(stringBuilder2.toString(), new HashMap());
        }
        FBAppEventsLogger.reportPlay(playerTrack);
    }

    public boolean isWithin30Days() {
        Calendar instance = Calendar.getInstance();
        long timeInMillis = instance.getTimeInMillis();
        long b = d.a().b(timeInMillis, "PLAY_CYCLE_START_DATE", false);
        if (b == timeInMillis) {
            d.a().a(b, "PLAY_CYCLE_START_DATE", false);
        }
        instance.add(5, -30);
        long timeInMillis2 = instance.getTimeInMillis();
        if (b == timeInMillis || b > timeInMillis2) {
            return true;
        }
        if (b < timeInMillis2) {
            d.a().a(timeInMillis, "PLAY_CYCLE_START_DATE", false);
        }
        return false;
    }
}
