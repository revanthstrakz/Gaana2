package com.gaana.application;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.android.volley.Request;
import com.bumptech.glide.request.a.j;
import com.cast_music.VideoCastManager;
import com.cast_music.b;
import com.comscore.analytics.comScore;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.FBAppEventsLogger;
import com.gaana.analytics.MoEngage;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.CountryData;
import com.gaana.models.GaanaThemeModel.GaanaTheme;
import com.gaana.models.GoogleIntroductoryPriceConfig;
import com.gaana.models.Languages.Language;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.SmartDownloadNotificationView;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.Gson;
import com.helpshift.support.l;
import com.library.custom_glide.GlideFileLoader;
import com.library.util.CrashUtil;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.managers.ColombiaAdViewManager;
import com.managers.DownloadManager;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.ai;
import com.managers.am;
import com.managers.an;
import com.managers.ap;
import com.managers.f;
import com.managers.q;
import com.managers.u;
import com.managers.w;
import com.models.ListingComponents;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.h;
import com.services.d;
import com.services.l.aa;
import com.services.n;
import com.til.colombia.android.internal.e;
import com.utilities.Util;
import com.utilities.g;
import in.til.core.a;
import in.til.sdk.android.identity.sso.SSOIntegration;
import in.til.sdk.android.identity.tp.TPIntegration;
import java.io.File;
import java.io.Serializable;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class GaanaApplication extends Application {
    private static final String AF_DEV_KEY = "EdczYSFfLWnd3ystudC5GK";
    private static String currentSessionId = null;
    private static final CookieManager defaultCookieManager = new CookieManager();
    private static GaanaApplication instance = null;
    public static boolean onBoardingSkipped = false;
    public static int sessionHistoryCount = -1;
    public static String targetUri;
    private boolean _fetchRecommendedSongs = false;
    private boolean _showCFSongsToast = false;
    private String ads_env = "";
    private ArrayList<Playlist> arrListPlaylist = null;
    private ArrayList<Track> arrListTracksForPlaylist = null;
    private ArrayList<String> arrayListPlaylistIds = null;
    private ArrayList<String> arrayListTrackIds = null;
    private boolean authenticationStatus = true;
    private CountryData countryData;
    private ArrayList<?> currentBusObjInListView = null;
    private String currentGenreName = null;
    String currentPageName = null;
    private String currentSponsoredOccassion;
    public int customCardSessionValue = -1;
    private String dfpAdSectionName = null;
    public Boolean hasLoginStatusChanged = Boolean.valueOf(false);
    private Boolean hasPlayerStarted = Boolean.valueOf(false);
    public ConcurrentHashMap<String, Long> inAppShownList;
    private long initialPlayTime = 0;
    private long initialSecondaryPlayTime = 0;
    private boolean isAppInDataSaveMode = false;
    private boolean isAppInOfflineMode = false;
    private boolean isAppLaucnhedFromDeeplinking = false;
    private boolean isColombiaSdkIntialized = false;
    private boolean isCurrentALPLSponsored;
    private boolean isEndlessPlayback = false;
    private boolean isLyricsDisplay = false;
    private boolean isUpgradedToNewVersion = false;
    private boolean isVideoAutoplay = false;
    private ListingComponents listingComponents;
    private AppLifeCycleListener mAppLifeCycleListener;
    public String mFinalUrl = "";
    private GoogleIntroductoryPriceConfig mIntroductoryPriceConfig;
    private int metadata_refresh_interval = 5;
    private long metadata_timestamp;
    Bundle networkBundle = null;
    private AdMobExtras networkExtrasBundle = null;
    String playoutSectionName = null;
    String playoutSectionPrevForSongRadio = null;
    private String promorUrl;
    private int sidebarActiveBtn = R.id.GaanaHome;
    private long tapToPlayTime = 0;
    private long tapToPlayTimeSecondary = 0;
    private boolean themeRefreshRequired;
    protected String userAgent;

    static class AppLifeCycleListener implements ActivityLifecycleCallbacks {
        public int mSelectedIndex = -1;
        private int pauseCount = 0;
        private int resumeCount = 0;
        private int startCount = 0;
        private int stopCount = 0;

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        AppLifeCycleListener() {
        }

        public void onActivityStarted(Activity activity) {
            this.startCount++;
            if (VERSION.SDK_INT >= 19) {
                try {
                    activity.reportFullyDrawn();
                } catch (Exception unused) {
                }
            }
        }

        public void onActivityResumed(Activity activity) {
            this.resumeCount++;
            if (this.pauseCount == 0 && Util.ad()) {
                an.a().a("state", "fg", "", "", "", an.a().a(this.mSelectedIndex));
            }
        }

        public void onActivityPaused(Activity activity) {
            this.pauseCount++;
        }

        public void onActivityStopped(Activity activity) {
            this.stopCount++;
            if (this.stopCount >= this.startCount && Util.ad()) {
                an.a().a("state", "bg", "", an.a().a(an.a().a), "", "");
                reset();
            }
        }

        private void reset() {
            this.stopCount = 0;
            this.startCount = 0;
            this.resumeCount = 0;
            this.pauseCount = 0;
            this.mSelectedIndex = an.a().a;
            an.a().b = -1;
            an.a().a = -1;
        }

        public boolean isApplicationInForeground() {
            return this.startCount > this.stopCount;
        }
    }

    private static native String getEncryptionKey(int i, Context context);

    private void initDebugMode() {
    }

    public native String[] nativeStringFromMethod();

    public native String[] stringFromMethod();

    public native String[] stringFromMethodKey();

    public boolean isCurrentALPLSponsored() {
        return this.isCurrentALPLSponsored;
    }

    public void setAdsEnv(String str) {
        this.ads_env = str;
    }

    public String getAdsEnv() {
        return this.ads_env;
    }

    public void setIsCurrentALPLSponsored(boolean z) {
        if (z) {
            GaanaMusicService.j();
        }
        f.v().j(z);
        this.isCurrentALPLSponsored = z;
    }

    public String getCurrentSponsoredOccassion() {
        return this.currentSponsoredOccassion;
    }

    public void setCurrentSponsoredOccassion(String str) {
        if (str != null) {
            GaanaMusicService.j();
        }
        f.v().j(str != null);
        this.currentSponsoredOccassion = str;
    }

    static {
        defaultCookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        try {
            System.loadLibrary("ViewAnim");
        } catch (UnsatisfiedLinkError e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void setRecommendedSongsFlag(boolean z) {
        this._fetchRecommendedSongs = z;
    }

    public boolean getRecommendedSongsFlag() {
        return this._fetchRecommendedSongs;
    }

    public void setShowCFSongsToastFlag(boolean z) {
        this._showCFSongsToast = z;
    }

    public boolean getShowCFSongsToastFlag() {
        return this._showCFSongsToast;
    }

    public GaanaApplication() {
        instance = this;
    }

    public static String getCurrentSessionId() {
        return currentSessionId;
    }

    public void setCurrentSessionId(String str) {
        currentSessionId = str;
    }

    public static Context getContext() {
        return instance;
    }

    public static GaanaApplication getInstance() {
        return instance;
    }

    public void setColombiaSdkinit(boolean z) {
        this.isColombiaSdkIntialized = z;
    }

    public boolean getColombiaSdkInit() {
        return this.isColombiaSdkIntialized;
    }

    public String getCurrentGenreName() {
        return this.currentGenreName;
    }

    public void setCurrentGenreName(String str) {
        this.currentGenreName = str;
    }

    public boolean isAppInOfflineMode() {
        return this.isAppInOfflineMode;
    }

    public void setAppInOfflineMode(boolean z) {
        this.isAppInOfflineMode = z;
    }

    public boolean isAppInDataSaveMode() {
        return this.isAppInDataSaveMode;
    }

    public void setAppInDataSaveMode(boolean z) {
        this.isAppInDataSaveMode = z;
    }

    private void initTilSdk() {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("enabled", "true");
        hashMap.put("nsso", hashMap2);
        hashMap2 = new HashMap();
        hashMap2.put("enabled", "true");
        hashMap.put("tp", hashMap2);
        a.a(new a.a(this).a(SSOIntegration.FACTORY).a(TPIntegration.FACTORY).a(hashMap));
    }

    public void setIsEndlessPlayback(boolean z) {
        this.isEndlessPlayback = z;
    }

    public boolean isEndlessPlayback() {
        return this.isEndlessPlayback;
    }

    public void setLyricsDisplay(boolean z) {
        this.isLyricsDisplay = z;
    }

    public boolean getLyricsDisplay() {
        return this.isLyricsDisplay;
    }

    public void setIsVideoAutoplay(boolean z) {
        this.isVideoAutoplay = z;
    }

    public void showSmartDownloadsNotification(Context context, boolean z, String str) {
        SmartDownloadNotificationView smartDownloadNotificationView = new SmartDownloadNotificationView(context);
        if (z) {
            DownloadManager.c().a(false);
            smartDownloadNotificationView.setTrackId(str);
            smartDownloadNotificationView.setNotificationType("repeat");
        } else {
            smartDownloadNotificationView.setNotificationType("smart_downloads");
        }
        smartDownloadNotificationView.show();
    }

    public boolean isVideoAutoplay() {
        return this.isVideoAutoplay;
    }

    public void onCreate() {
        super.onCreate();
        initKeyVariables();
        initializeKeys();
        initiateCountryDataFromPrefs();
        this.mAppLifeCycleListener = new AppLifeCycleListener();
        registerActivityLifecycleCallbacks(this.mAppLifeCycleListener);
        d.a().a("MASTHEAD_DISPLAY_COUNT", 0, false);
        initTilSdk();
        initHelpShift();
        if (!g.a()) {
            Constants.l = d.a().b("PREFERENCE_WHITE_THEME_ENABLED", false, false);
        }
        sessionHistoryCount = d.a().b("PREFERENCE_SESSION_HISTORY_COUNT", 0, false);
        boolean z = true;
        if (sessionHistoryCount == 0) {
            if (Util.ad()) {
                MoEngage.getInstance().sendThemeChangeAttribute(true);
            }
            Constants.l = true;
            d.a().a("PREFERENCE_WHITE_THEME_ENABLED", Constants.l, false);
            d.a().a("PREFERENCE_DISPLAY_LANGUAGE_SET_FIRST_TIME", Constants.m, false);
        }
        f.v().b(System.currentTimeMillis());
        f.v().b(0);
        if (d.a().b("pref_auto_night_mode_on", false, false)) {
            if (isDayOrNightUsingTwilightCalculator() != 0) {
                z = false;
            }
            Constants.l = z;
        }
        checkAppUpgrade();
        initAsync();
        j.a((int) R.id.glide_tag);
        VideoCastManager.a(getApplicationContext(), new b.a(getResources().getString(R.string.cast_app_id)).d().b().c().e().a());
        FBAppEventsLogger.init(this);
    }

    private void initiateCountryDataFromPrefs() {
        String c = d.a().c("PREF_COUNTRY_CODE", false);
        String c2 = d.a().c("PREF_CITY_NAME", false);
        String c3 = d.a().c("PREF_STATE_NAME", false);
        CountryData countryData = (CountryData) n.a(d.a().b("PREF_COUNTRY_DATA", null, false));
        int b = d.a().b("PREF_CONSENT_STATUS", 0, false);
        if (c != null) {
            Constants.ct = c;
            Constants.cB = c3;
            Constants.cC = c2;
            setCountryData(countryData, false);
            Constants.ek = b;
        }
    }

    private void initAsync() {
        com.i.d.a(new com.i.d.a(new Runnable() {
            public void run() {
                GlideFileLoader.read();
                GaanaApplication.this.initVariables();
                GaanaApplication.this.initThemeValues();
                GaanaApplication.this.initDebugMode();
                AppsFlyer.getInstance().initialize(GaanaApplication.AF_DEV_KEY, GaanaApplication.getInstance());
                MoEngage.getInstance().autoIntegrate(GaanaApplication.getInstance());
                MoEngage.getInstance().setDebugMode();
                comScore.setAppContext(GaanaApplication.this.getApplicationContext());
                comScore.setCustomerC2("6036484");
                comScore.setPublisherSecret("db32bf9205278a4af70d41ece515f7fc");
                if (g.b()) {
                    comScore.setAppName(GaanaApplication.this.getResources().getString(R.string.app_name_mmx));
                } else {
                    comScore.setAppName(GaanaApplication.this.getResources().getString(R.string.app_name));
                }
                comScore.enableAutoUpdate(60, false);
                GaanaApplication.this.inAppShownList = new ConcurrentHashMap();
                if (CookieHandler.getDefault() != GaanaApplication.defaultCookieManager) {
                    CookieHandler.setDefault(GaanaApplication.defaultCookieManager);
                }
                com.b.a.a(new com.b.a.a().a("fonts/Roboto-Regular.ttf").a((int) R.attr.fontPath).a());
                int i = VERSION.SDK_INT;
                if (VERSION.SDK_INT >= 26) {
                    new h(GaanaApplication.getContext()).a();
                }
                LoginManager.getInstance();
                com.i.j.a().c();
            }
        }, 8));
    }

    private void initHelpShift() {
        com.helpshift.a.a(l.b());
        HashMap hashMap = new HashMap();
        hashMap.put("notificationIcon", Integer.valueOf(Util.w()));
        hashMap.put("notificationSound", Integer.valueOf(R.raw.bajnachahiyegaana));
        try {
            com.helpshift.a.a(this, "1fd9bf1d612a9ed2b5524c62ceeca715", "gaana.helpshift.com", "gaana_platform_20130801080211150-477754f9dddc59b", hashMap);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    public int getSidebarActiveBtn() {
        return this.sidebarActiveBtn;
    }

    public void setSidebarActiveBtn(int i) {
        this.sidebarActiveBtn = i;
    }

    public boolean getPlayerStatus() {
        return this.hasPlayerStarted.booleanValue();
    }

    public void setPlayerStatus(boolean z) {
        this.hasPlayerStarted = Boolean.valueOf(z);
    }

    public ListingComponents getListingComponents() {
        return this.listingComponents;
    }

    public void setListingComponents(ListingComponents listingComponents) {
        this.listingComponents = listingComponents;
    }

    public ArrayList<?> getCurrentBusObjInListView() {
        return this.currentBusObjInListView;
    }

    public void setCurrentBusObjInListView(ArrayList<?> arrayList) {
        this.currentBusObjInListView = arrayList;
    }

    public UserInfo getCurrentUser() {
        return LoginManager.getInstance().getUserInfo();
    }

    public String getUserAccountStatus() {
        String str;
        if (getCurrentUser() != null && getCurrentUser().getUserSubscriptionData() != null) {
            if (!ap.a().l()) {
                switch (getCurrentUser().getUserSubscriptionData().getAccountType()) {
                    case 0:
                        str = "ACCOUNT_TYPE_NONE";
                        break;
                    case 1:
                        str = "ACCOUNT_TYPE_FREE";
                        break;
                    case 2:
                        str = "ACCOUNT_TYPE_TRIAL";
                        break;
                    case 3:
                        str = "ACCOUNT_TYPE_GAANA_PLUS_PAID";
                        break;
                    default:
                        str = "ACCOUNT_TYPE_NONE";
                        break;
                }
            }
            return "ACCOUNT_TYPE_FREEDOM";
        }
        str = "ACCOUNT_TYPE_NONE";
        return str;
    }

    public ArrayList<Playlist> getArrListPlaylist() {
        return this.arrListPlaylist;
    }

    public void setArrListPlaylist(ArrayList<Playlist> arrayList) {
        this.arrListPlaylist = arrayList;
    }

    public ArrayList<Track> getArrListTracksForPlaylist() {
        return this.arrListTracksForPlaylist;
    }

    public void setArrListTracksForPlaylist(ArrayList<Track> arrayList) {
        this.arrListTracksForPlaylist = new ArrayList();
        if (arrayList != null) {
            this.arrListTracksForPlaylist.addAll(arrayList);
        }
    }

    public ArrayList<String> getArrListForPlaylistIds() {
        return this.arrayListPlaylistIds;
    }

    public void setArrListForPlaylistIds(String str) {
        if (this.arrayListPlaylistIds == null) {
            this.arrayListPlaylistIds = new ArrayList();
        }
        this.arrayListPlaylistIds.add(str);
    }

    public ArrayList<String> getArrListForTrackIds() {
        return this.arrayListTrackIds;
    }

    public void setArrListForTrackIds(String str) {
        if (this.arrayListTrackIds == null) {
            this.arrayListTrackIds = new ArrayList();
        }
        this.arrayListTrackIds.add(str);
    }

    private DisplayMetrics getDisplayMetrics() {
        return getContext().getResources().getDisplayMetrics();
    }

    public void hockeyCheckForCrashes(Context context) {
        CrashUtil.checkCrashesByHockey(context, Constants.be);
    }

    public void setGADParameter(String str) {
        if (getCurrentSponsoredOccassion() == null || !ColombiaAdViewManager.a().f()) {
            setNetworkExtrasBundle("GAD", str);
        } else {
            setNetworkExtrasBundle("GAD", getCurrentSponsoredOccassion());
        }
    }

    public void removeGADParameter() {
        if (this.networkBundle != null && this.networkBundle.containsKey("GAD")) {
            this.networkBundle.remove("GAD");
        }
    }

    public void setVPLTypeParameter(String str) {
        setNetworkExtrasBundle("source_name", str);
    }

    public void removeVPLTypeParameter() {
        if (this.networkBundle != null && this.networkBundle.containsKey("source_name")) {
            this.networkBundle.remove("source_name");
        }
    }

    public void setNetworkExtrasBundle(String str, String str2) {
        if (this.networkBundle == null) {
            this.networkBundle = new Bundle();
        }
        if (this.networkBundle.containsKey(str)) {
            this.networkBundle.remove(str);
        }
        removeExistingKeys();
        if (!str.equalsIgnoreCase("GC")) {
            if (Constants.ck.get(str) == null || !((Boolean) Constants.ck.get(str)).booleanValue()) {
                this.networkBundle.putString(str, str2);
            } else if (this.networkBundle.containsKey(str)) {
                this.networkBundle.remove(str);
            }
        }
        if (str.equalsIgnoreCase("followup") && TextUtils.isEmpty(str2) && this.networkBundle.containsKey(str)) {
            this.networkBundle.remove(str);
        }
        q.a().b();
        HashMap I = f.v().I();
        if (I != null) {
            if (Constants.ck.get("gender") == null || !((Boolean) Constants.ck.get("gender")).booleanValue()) {
                this.networkBundle.putString("gender", (String) I.get("gender"));
            } else if (this.networkBundle.containsKey("gender")) {
                this.networkBundle.remove("gender");
            }
            if (Constants.ck.get(e.K) == null || !((Boolean) Constants.ck.get(e.K)).booleanValue()) {
                this.networkBundle.putString(e.K, (String) I.get(e.K));
            } else if (this.networkBundle.containsKey(e.K)) {
                this.networkBundle.remove(e.K);
            }
        }
        if (!TextUtils.isEmpty(getInstance().getAdsEnv())) {
            if (Constants.ck.get("demo") == null || !((Boolean) Constants.ck.get("demo")).booleanValue()) {
                this.networkBundle.putString("demo", getInstance().getAdsEnv());
            } else if (this.networkBundle.containsKey("demo")) {
                this.networkBundle.remove("demo");
            }
        }
        if (Constants.ck.get("GUL") == null || !((Boolean) Constants.ck.get("GUL")).booleanValue()) {
            this.networkBundle.putString("GUL", getSongLanguagesString());
        } else if (this.networkBundle.containsKey("GUL")) {
            this.networkBundle.remove("GUL");
        }
        if (Constants.ck.get("sg") == null || !((Boolean) Constants.ck.get("sg")).booleanValue()) {
            this.networkBundle.putStringArray("sg", q.a().d());
        } else if (this.networkBundle.containsKey("sg")) {
            this.networkBundle.remove("sg");
        }
        if (Constants.ck.get("ver") == null || !((Boolean) Constants.ck.get("ver")).booleanValue()) {
            this.networkBundle.putString("ver", Util.t());
        } else if (this.networkBundle.containsKey("ver")) {
            this.networkBundle.remove("ver");
        }
        if (Constants.ck.get("current_network") == null || !((Boolean) Constants.ck.get("current_network")).booleanValue()) {
            this.networkBundle.putString("current_network", Constants.dC);
        } else if (this.networkBundle.containsKey("current_network")) {
            this.networkBundle.remove("current_network");
        }
        this.networkExtrasBundle = new AdMobExtras(this.networkBundle);
        setCurrentTrackParameters();
    }

    private void setCurrentTrackParameters() {
        if (Constants.ck.get("app_version") == null || !((Boolean) Constants.ck.get("app_version")).booleanValue()) {
            this.networkExtrasBundle.getExtras().putString("app_version", am.a().b());
        } else if (this.networkBundle.containsKey("app_version")) {
            this.networkBundle.remove("app_version");
        }
        if (Constants.ck.get("user_subtype") == null || !((Boolean) Constants.ck.get("user_subtype")).booleanValue()) {
            this.networkExtrasBundle.getExtras().putString("user_subtype", getInstance().getUserAccountStatus());
        } else if (this.networkBundle.containsKey("user_subtype")) {
            this.networkBundle.remove("user_subtype");
        }
        PlayerTrack i = PlayerManager.a(getContext()).i();
        if (i != null) {
            Track b = i.b();
            if (Constants.ck.get("entity_type") == null || !((Boolean) Constants.ck.get("entity_type")).booleanValue()) {
                this.networkExtrasBundle.getExtras().putString("entity_type", "TR");
            } else if (this.networkBundle.containsKey("entity_type")) {
                this.networkBundle.remove("entity_type");
            }
            if (Constants.ck.get("track_id") == null || !((Boolean) Constants.ck.get("track_id")).booleanValue()) {
                this.networkExtrasBundle.getExtras().putString("track_id", b.getBusinessObjId());
            } else if (this.networkBundle.containsKey("track_id")) {
                this.networkBundle.remove("track_id");
            }
            if (Constants.ck.get("Artist_ID") == null || !((Boolean) Constants.ck.get("Artist_ID")).booleanValue()) {
                this.networkExtrasBundle.getExtras().putString("Artist_ID", b.getArtistIds());
            } else if (this.networkBundle.containsKey("Artist_ID")) {
                this.networkBundle.remove("Artist_ID");
            }
            if (Constants.ck.get("Album_ID") == null || !((Boolean) Constants.ck.get("Album_ID")).booleanValue()) {
                this.networkExtrasBundle.getExtras().putString("Album_ID", b.getAlbumId());
            } else if (this.networkBundle.containsKey("Album_ID")) {
                this.networkBundle.remove("Album_ID");
            }
            if (Constants.ck.get("Language_ID") == null || !((Boolean) Constants.ck.get("Language_ID")).booleanValue()) {
                this.networkExtrasBundle.getExtras().putString("Language_ID", b.getLanguage());
            } else if (this.networkBundle.containsKey("Language_ID")) {
                this.networkBundle.remove("Language_ID");
            }
            if (Constants.ck.get("Tag_ID") == null || !((Boolean) Constants.ck.get("Tag_ID")).booleanValue()) {
                this.networkExtrasBundle.getExtras().putString("Tag_ID", b.getTagID());
            } else if (this.networkBundle.containsKey("Tag_ID")) {
                this.networkBundle.remove("Tag_ID");
            }
        }
    }

    public String getSongLanguagesString() {
        ArrayList a = w.a(this).a((Context) this);
        String str = "";
        if (a != null && a.size() > 0) {
            Iterator it = a.iterator();
            while (it.hasNext()) {
                Language language = (Language) it.next();
                if (language.isPrefered() != 0) {
                    CharSequence language2;
                    if (TextUtils.isEmpty(language2)) {
                        language2 = language.getLanguage();
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(language2);
                        stringBuilder.append(",");
                        stringBuilder.append(language.getLanguage());
                        language2 = stringBuilder.toString();
                    }
                }
            }
        }
        return str;
    }

    public void setDFPAdSectionName(String str) {
        this.dfpAdSectionName = str;
    }

    private void removeExistingKeys() {
        if (this.networkBundle != null) {
            if (this.networkBundle.containsKey("sg")) {
                this.networkBundle.remove("sg");
            }
            if (this.networkBundle.containsKey("GUL")) {
                this.networkBundle.remove("GUL");
            }
            if (this.networkBundle.containsKey("ver")) {
                this.networkBundle.remove("ver");
            }
            if (this.networkBundle.containsKey("s_count")) {
                this.networkBundle.remove("s_count");
            }
            if (this.networkBundle.containsKey("g_theme")) {
                this.networkBundle.remove("g_theme");
            }
            if (this.networkBundle.containsKey("section_name")) {
                this.networkBundle.remove("section_name");
            }
            if (this.networkBundle.containsKey(e.K)) {
                this.networkBundle.remove(e.K);
            }
            if (this.networkBundle.containsKey("gender")) {
                this.networkBundle.remove("gender");
            }
            if (this.networkBundle.containsKey("campaign")) {
                this.networkBundle.remove("campaign");
            }
            if (this.networkBundle.containsKey("demo")) {
                this.networkBundle.remove("demo");
            }
            if (this.networkBundle.containsKey("GC")) {
                this.networkBundle.remove("GC");
            }
        }
    }

    public void setNetworkExtrasBundle() {
        Bundle bundle = new Bundle();
        q.a().b();
        HashMap I = f.v().I();
        if (I != null) {
            if (Constants.ck.get("gender") == null || !((Boolean) Constants.ck.get("gender")).booleanValue()) {
                bundle.putString("gender", (String) I.get("gender"));
            }
            if (Constants.ck.get(e.K) == null || !((Boolean) Constants.ck.get(e.K)).booleanValue()) {
                bundle.putString(e.K, (String) I.get(e.K));
            }
        }
        if (Constants.ck.get("sg") == null || !((Boolean) Constants.ck.get("sg")).booleanValue()) {
            bundle.putStringArray("sg", q.a().d());
        }
        if (Constants.ck.get("ver") == null || !((Boolean) Constants.ck.get("ver")).booleanValue()) {
            bundle.putString("ver", Util.t());
        }
        if (Constants.ck.get("current_network") == null || !((Boolean) Constants.ck.get("current_network")).booleanValue()) {
            bundle.putString("current_network", Constants.dC);
        }
        if (!TextUtils.isEmpty(this.dfpAdSectionName)) {
            if ((Constants.ck.get("section_name") == null || !((Boolean) Constants.ck.get("section_name")).booleanValue()) && this.dfpAdSectionName != null) {
                bundle.putString("section_name", this.dfpAdSectionName.replaceAll("\\s+", "_"));
            }
            this.dfpAdSectionName = null;
        }
        this.networkExtrasBundle = new AdMobExtras(bundle);
    }

    public AdMobExtras getNetworkExtrasBundle() {
        return this.networkExtrasBundle;
    }

    public boolean isAuthenticationStatus() {
        return this.authenticationStatus;
    }

    public void setAuthenticationStatus(boolean z) {
        this.authenticationStatus = z;
        if (!z) {
            refreshUserToken();
        }
    }

    public void refreshUserToken() {
        LoginManager.getInstance().loginSilently(null, new IOnLoginCompleted() {
            public void onLoginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle) {
            }
        }, false);
    }

    public boolean isAppLaucnhedFromDeeplinking() {
        return this.isAppLaucnhedFromDeeplinking;
    }

    public void setAppLaucnhedFromDeeplinking(boolean z) {
        this.isAppLaucnhedFromDeeplinking = z;
    }

    public void setPromoUrl(String str) {
        this.promorUrl = str;
    }

    public String getPromorUrl() {
        return this.promorUrl;
    }

    public void initThemeValues() {
        String b = d.a().b("PREFERENCE_CURRENT_THEME", null, false);
        if (b != null && !b.isEmpty()) {
            Constants.dt = (GaanaTheme) new Gson().fromJson(b, GaanaTheme.class);
        }
    }

    private void initVariables() {
        String[] stringFromMethod = stringFromMethod();
        Constants.bd = stringFromMethod[0];
        Constants.be = stringFromMethod[1];
        Constants.bf = stringFromMethod[2];
        Constants.bg = stringFromMethod[3];
        Constants.bh = stringFromMethod[4];
        Constants.bi = stringFromMethod[5];
        Constants.bj = stringFromMethod[6];
        Constants.bk = stringFromMethod[7];
        Constants.br = stringFromMethod[8];
        Constants.bs = stringFromMethod[9];
        Constants.bc = stringFromMethod[10];
        Constants.bt = stringFromMethod[11];
        Constants.bu = stringFromMethod[12];
        Constants.bv = stringFromMethod[13];
        Constants.by = stringFromMethod[14];
        Constants.bz = stringFromMethod[15];
        Constants.bA = stringFromMethod[16];
        Constants.bB = stringFromMethod[17];
        Constants.bC = stringFromMethod[18];
        Constants.bw = stringFromMethod[19];
        Constants.bx = stringFromMethod[20];
    }

    private void initKeyVariables() {
        String[] stringFromMethodKey = stringFromMethodKey();
        Constants.bl = stringFromMethodKey[0];
        Constants.bm = stringFromMethodKey[1];
        Constants.bn = stringFromMethodKey[2];
        Constants.bo = stringFromMethodKey[3];
        Constants.bp = stringFromMethodKey[4];
        Constants.bq = stringFromMethodKey[5];
    }

    private void initializeKeys() {
        String[] nativeStringFromMethod = nativeStringFromMethod();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(nativeStringFromMethod[0]);
        stringBuilder.append(nativeStringFromMethod[2]);
        stringBuilder.append(nativeStringFromMethod[1]);
        Constants.bD = stringBuilder.toString();
    }

    public static String getEncryptionKey(int i) {
        String encryptionKey = getEncryptionKey(i, getInstance());
        if (i == 2) {
            return encryptionKey;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(encryptionKey.substring(0, 5));
        stringBuilder.append(LoginManager.TAG_SUBTYPE_GAANA);
        stringBuilder.append(encryptionKey.substring(5));
        return stringBuilder.toString();
    }

    public static byte[] getExoEncryptionKey(int i) {
        String encryptionKey = getEncryptionKey(i, getInstance());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(encryptionKey.substring(0, 5));
        stringBuilder.append(LoginManager.TAG_SUBTYPE_GAANA);
        stringBuilder.append(encryptionKey.substring(5));
        return Arrays.copyOfRange(stringBuilder.toString().getBytes(Charset.forName("UTF-8")), 0, 16);
    }

    public void setInitialPlayTimeForSecondaryTrack(long j) {
        this.initialSecondaryPlayTime = j;
    }

    public void sendUrlFetchTimeEventForSecondaryTrack(boolean z) {
        if (this.initialSecondaryPlayTime != 0) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            String str = PlayerManager.a((Context) this).m() == PlayerType.GAANA_RADIO ? "Radio - Secondary" : "Track - Secondary";
            long j = timeInMillis - this.initialSecondaryPlayTime;
            if (!z) {
                u.a().a("Play", j, str, "URL");
            }
            this.tapToPlayTimeSecondary = j;
        }
    }

    public void sendPlayLoadTimeEventFromSecdndaryPlayer(boolean z) {
        if (this.initialSecondaryPlayTime != 0) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis() - this.initialSecondaryPlayTime;
            String str = PlayerManager.a((Context) this).m() == PlayerType.GAANA_RADIO ? "Radio - Secondary" : "Track - Secondary";
            u.a().a("Play", timeInMillis, str, "Buffer");
            this.initialSecondaryPlayTime = 0;
            this.tapToPlayTimeSecondary += timeInMillis;
            u.a().a("Play", this.tapToPlayTimeSecondary, str, "Tap to Play");
            PlayerTrack i = PlayerManager.a((Context) this).i();
            if (i != null) {
                if (z) {
                    an.a().a("android_buffer", i.h(), i.c(), Util.S(), "s", "uf", this.tapToPlayTimeSecondary);
                } else {
                    an.a().a("android_buffer", i.h(), i.c(), Util.S(), "s", "uc", this.tapToPlayTimeSecondary);
                }
            }
            this.tapToPlayTimeSecondary = 0;
        }
    }

    public void setInitialPlayTime(long j) {
        this.initialPlayTime = j;
    }

    public void sendUrlFetchTimeEvent(boolean z) {
        if (this.initialPlayTime != 0) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            String str = PlayerManager.a((Context) this).m() == PlayerType.GAANA_RADIO ? "Radio" : "Track";
            long j = timeInMillis - this.initialPlayTime;
            if (!z) {
                u.a().a("Play", j, str, "URL");
            }
            setInitialPlayTime(Calendar.getInstance().getTimeInMillis());
            this.tapToPlayTime = j;
        }
    }

    public void sendPlayLoadTimeEvent(boolean z) {
        if (this.initialPlayTime != 0) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis() - this.initialPlayTime;
            String str = PlayerManager.a((Context) this).m() == PlayerType.GAANA_RADIO ? "Radio" : "Track";
            u.a().a("Play", timeInMillis, str, "Buffer");
            this.initialPlayTime = 0;
            this.tapToPlayTime += timeInMillis;
            u.a().a("Play", this.tapToPlayTime, str, "Tap to Play");
            PlayerTrack i = PlayerManager.a((Context) this).i();
            if (i != null) {
                if (z) {
                    an.a().a("android_buffer", i.h(), i.c(), Util.S(), TtmlNode.TAG_P, "uf", this.tapToPlayTime);
                } else {
                    an.a().a("android_buffer", i.h(), i.c(), Util.S(), TtmlNode.TAG_P, "uc", this.tapToPlayTime);
                }
            }
            this.tapToPlayTime = 0;
        }
    }

    public boolean checkAuthTokenStatus(Object obj) {
        if (obj != null && (obj instanceof BusinessObject)) {
            BusinessObject businessObject = (BusinessObject) obj;
            String usertokenstatus = businessObject.getUsertokenstatus();
            String uts = businessObject.getUts();
            GaanaApplication gaanaApplication = (GaanaApplication) getContext();
            if (gaanaApplication.getCurrentUser().getLoginStatus()) {
                if (usertokenstatus != null && usertokenstatus.equals("0")) {
                    gaanaApplication.setAuthenticationStatus(false);
                    return false;
                } else if (uts == null || !uts.equals("0")) {
                    gaanaApplication.setAuthenticationStatus(true);
                    return true;
                } else {
                    gaanaApplication.setAuthenticationStatus(false);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkAuthTokenStatus(Object obj, Request request) {
        String uts;
        if (obj != null && (obj instanceof BusinessObject)) {
            BusinessObject businessObject = (BusinessObject) obj;
            String usertokenstatus = businessObject.getUsertokenstatus();
            uts = businessObject.getUts();
            GaanaApplication gaanaApplication = (GaanaApplication) getContext();
            if (gaanaApplication.getCurrentUser().getLoginStatus()) {
                if (!request.getUrl().contains(gaanaApplication.getCurrentUser().getAuthToken())) {
                    return false;
                }
                if (usertokenstatus != null && usertokenstatus.equals("0")) {
                    if (!request.isCanceled()) {
                        gaanaApplication.setAuthenticationStatus(false);
                    }
                    return false;
                } else if (uts == null || !uts.equals("0")) {
                    if (!request.isCanceled()) {
                        gaanaApplication.setAuthenticationStatus(true);
                    }
                    return true;
                } else {
                    if (!request.isCanceled()) {
                        gaanaApplication.setAuthenticationStatus(false);
                    }
                    return false;
                }
            }
        } else if (obj instanceof String) {
            try {
                JSONObject jSONObject = new JSONObject((String) obj);
                String str = null;
                uts = jSONObject.opt("user_token_status") instanceof String ? (String) jSONObject.opt("user_token_status") : jSONObject.opt("user_token_status") != null ? String.valueOf(((Integer) jSONObject.opt("user_token_status")).intValue()) : null;
                if (jSONObject.opt("uts") instanceof String) {
                    str = (String) jSONObject.opt("uts");
                } else if (jSONObject.opt("uts") != null) {
                    str = String.valueOf(((Integer) jSONObject.opt("uts")).intValue());
                }
                GaanaApplication gaanaApplication2 = (GaanaApplication) getContext();
                if (gaanaApplication2.getCurrentUser().getLoginStatus()) {
                    if (!request.getUrl().contains(gaanaApplication2.getCurrentUser().getAuthToken())) {
                        return false;
                    }
                    if (uts != null && uts.equals("0")) {
                        if (!request.isCanceled()) {
                            gaanaApplication2.setAuthenticationStatus(false);
                        }
                        return false;
                    } else if (str == null || !str.equals("0")) {
                        if (!request.isCanceled()) {
                            gaanaApplication2.setAuthenticationStatus(true);
                        }
                        return true;
                    } else {
                        if (!request.isCanceled()) {
                            gaanaApplication2.setAuthenticationStatus(false);
                        }
                        return false;
                    }
                }
            } catch (JSONException unused) {
            }
        }
        return true;
    }

    public boolean checkAuthTokenStatus(JSONObject jSONObject) {
        try {
            String string = jSONObject.has("user_token_status") ? jSONObject.getString("user_token_status") : jSONObject.has("uts") ? jSONObject.getString("uts") : null;
            GaanaApplication gaanaApplication = (GaanaApplication) getContext();
            if (gaanaApplication.getCurrentUser().getLoginStatus() && string != null && string.equals("0")) {
                gaanaApplication.setAuthenticationStatus(false);
                return false;
            }
        } catch (Exception unused) {
        }
        return true;
    }

    public void setPlayoutSectionName(String str) {
        this.playoutSectionName = str;
    }

    public String getPlayoutSectionName() {
        return this.playoutSectionName;
    }

    public void setPlayoutSectionNamePrevForSongradio(String str) {
        this.playoutSectionPrevForSongRadio = str;
    }

    public String getPlayoutSectionNamePrevForSongradio() {
        return this.playoutSectionPrevForSongRadio;
    }

    public String getCurrentPageName() {
        return this.currentPageName;
    }

    public void setCurrentPageName(String str) {
        this.currentPageName = str;
    }

    public static void setLanguage(Context context, String str, aa aaVar) {
        com.utilities.f.a(context, str, aaVar);
    }

    public static String getLanguage(Context context) {
        return com.utilities.f.b(context);
    }

    public String getPageName() {
        if (ai.a() != null) {
            BaseGaanaFragment currentFragment = ((GaanaActivity) ai.a()).getCurrentFragment();
            if (currentFragment != null) {
                return currentFragment.getPageName();
            }
        } else if (this.playoutSectionName != null && this.playoutSectionName.equals("AndroidAuto")) {
            return this.playoutSectionName;
        }
        return PAGE_SORCE_NAME.OTHER.name();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0034  */
    private void checkAppUpgrade() {
        /*
        r6 = this;
        r0 = com.constants.Constants.cz;
        r1 = com.constants.Constants.cA;
        r2 = 0;
        r3 = r6.getPackageManager();	 Catch:{ NameNotFoundException -> 0x0024 }
        r4 = r6.getPackageName();	 Catch:{ NameNotFoundException -> 0x0024 }
        r3 = r3.getPackageInfo(r4, r2);	 Catch:{ NameNotFoundException -> 0x0024 }
        r3 = r3.versionName;	 Catch:{ NameNotFoundException -> 0x0024 }
        r0 = r6.getPackageManager();	 Catch:{ NameNotFoundException -> 0x0022 }
        r4 = r6.getPackageName();	 Catch:{ NameNotFoundException -> 0x0022 }
        r0 = r0.getPackageInfo(r4, r2);	 Catch:{ NameNotFoundException -> 0x0022 }
        r0 = r0.versionCode;	 Catch:{ NameNotFoundException -> 0x0022 }
        goto L_0x002c;
    L_0x0022:
        r0 = move-exception;
        goto L_0x0028;
    L_0x0024:
        r3 = move-exception;
        r5 = r3;
        r3 = r0;
        r0 = r5;
    L_0x0028:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);
        r0 = r1;
    L_0x002c:
        com.constants.Constants.cz = r3;
        com.constants.Constants.cA = r0;
        r0 = sessionHistoryCount;
        if (r0 <= 0) goto L_0x004f;
    L_0x0034:
        r0 = com.services.d.a();
        r1 = "PREFERENCE_KEY_GAANAAPP_VERSION";
        r0 = r0.c(r1, r2);
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x004c;
    L_0x0044:
        r1 = com.constants.Constants.cz;
        r0 = r0.equals(r1);
        if (r0 != 0) goto L_0x004f;
    L_0x004c:
        r0 = 1;
        r6.isUpgradedToNewVersion = r0;
    L_0x004f:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.application.GaanaApplication.checkAppUpgrade():void");
    }

    public boolean isUpgradedToNewVersion() {
        return this.isUpgradedToNewVersion;
    }

    public Factory buildDataSourceFactory(DefaultBandwidthMeter defaultBandwidthMeter, boolean z) {
        return new com.exoplayer2.upstream.b(this, defaultBandwidthMeter, buildHttpDataSourceFactory(defaultBandwidthMeter, z));
    }

    public HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter defaultBandwidthMeter, boolean z) {
        return new com.exoplayer2.upstream.d(Util.R(), defaultBandwidthMeter, z);
    }

    public boolean isAppInForeground() {
        return this.mAppLifeCycleListener.isApplicationInForeground();
    }

    public int isDayOrNightUsingTwilightCalculator() {
        double b = d.a().b(Double.parseDouble("28.7041"), "PREF_LOCATION_LAT", false);
        double b2 = d.a().b(Double.parseDouble("77.1025"), "PREF_LOCATION_LNG", false);
        if (b <= 0.0d || b2 <= 0.0d) {
            return 0;
        }
        com.utilities.l lVar = new com.utilities.l();
        lVar.a(System.currentTimeMillis(), b, b2);
        return lVar.c;
    }

    public void updateMetadata() {
        if (System.currentTimeMillis() - this.metadata_timestamp >= ((long) (60000 * this.metadata_refresh_interval))) {
            Util.G();
        }
    }

    public void setMetadataUpdateTimestamp() {
        this.metadata_timestamp = System.currentTimeMillis();
    }

    public void setThemeRefreshRequired(boolean z) {
        this.themeRefreshRequired = z;
    }

    public boolean getThemeRefreshRequired() {
        return this.themeRefreshRequired;
    }

    public GoogleIntroductoryPriceConfig getmIntroductoryPriceConfig() {
        return this.mIntroductoryPriceConfig;
    }

    public void setmIntroductoryPriceConfig(GoogleIntroductoryPriceConfig googleIntroductoryPriceConfig) {
        this.mIntroductoryPriceConfig = googleIntroductoryPriceConfig;
    }

    public void setCountryData(CountryData countryData, boolean z) {
        this.countryData = countryData;
        if (countryData != null) {
            boolean z2 = true;
            if (countryData.getEuRegion() != 1) {
                z2 = false;
            }
            Constants.el = z2;
        }
        if (z) {
            d.a().a("PREF_COUNTRY_DATA", n.a((Serializable) countryData), false);
        }
    }

    public CountryData getCountryData() {
        return this.countryData;
    }

    public void clearApplicationData() {
        File file = new File(getCacheDir().getParent());
        if (file.exists()) {
            for (String str : file.list()) {
                if (!str.equals("lib")) {
                    deleteFile(new File(file, str));
                }
            }
        }
    }

    public static boolean deleteFile(File file) {
        if (file == null) {
            return true;
        }
        if (!file.isDirectory()) {
            return file.delete();
        }
        String[] list = file.list();
        boolean z = true;
        for (String file2 : list) {
            z = deleteFile(new File(file, file2)) && z;
        }
        return z;
    }
}
