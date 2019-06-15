package com.constants;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.text.TextUtils;
import com.android.volley.Request.Priority;
import com.exoplayer2.CookieSpan;
import com.exoplayer2.TrackSpan;
import com.exoplayer2.upstream.cache.n;
import com.gaana.R;
import com.gaana.analytics.AppsFlyer;
import com.gaana.application.GaanaApplication;
import com.gaana.login.GooglePlusLogin;
import com.gaana.login.LoginManager;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.GaanaThemeModel.GaanaTheme;
import com.gaana.models.NextGenSearchAutoSuggests;
import com.gaana.models.PaymentProductModel.LayoutConfig;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.models.UserRecentActivity;
import com.gaana.view.FavoriteOccasionItemView;
import com.gaana.view.item.AlbumItemView;
import com.gaana.view.item.ArtistItemView;
import com.gaana.view.item.ByMePlaylistItemView;
import com.gaana.view.item.DiscoverItemView;
import com.gaana.view.item.DownloadAlbumItemView;
import com.gaana.view.item.DownloadPlaylistItemView;
import com.gaana.view.item.DownloadSongListingView;
import com.gaana.view.item.DownloadSongsItemView;
import com.gaana.view.item.EditPlaylistSelectSongView;
import com.gaana.view.item.GaanaPlusItemView;
import com.gaana.view.item.MyActivityInfoItemView;
import com.gaana.view.item.NewGenericItemView;
import com.gaana.view.item.NotificationItemView;
import com.gaana.view.item.RadioButtonGenericView;
import com.gaana.view.item.RadioItemView;
import com.gaana.view.item.SearchItemView;
import com.gaana.view.item.ShareableSongsView;
import com.gaana.view.item.TwoLineView;
import com.gaana.view.item.VotingSongsItemView;
import com.google.android.gms.actions.SearchIntents;
import com.google.api.client.http.HttpStatusCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.ColombiaManager;
import com.managers.DownloadManager;
import com.managers.GaanaSearchManager;
import com.managers.GaanaSearchManager.SearchType;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ae;
import com.managers.ap;
import com.managers.e;
import com.managers.m;
import com.managers.o;
import com.managers.u;
import com.managers.z;
import com.models.ListingButton;
import com.models.ListingButton.a;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.models.PlayerTrack;
import com.services.d;
import com.utilities.Util;
import com.utilities.Util.FontFamily;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class Constants {
    public static int A = 0;
    public static int B = 0;
    public static int C = 0;
    public static int D = 0;
    public static int E = 1;
    public static int F = 1;
    public static int G = 0;
    public static int H = 1;
    public static int I = 0;
    public static int J = 0;
    public static int K = 1;
    public static int L = 20;
    public static int M = 100;
    public static int N = 1;
    public static int O = 3;
    public static int P = 0;
    public static boolean Q = true;
    public static boolean R = true;
    public static boolean S = true;
    public static boolean T = false;
    public static boolean U = false;
    public static boolean V = false;
    public static boolean W = false;
    public static boolean X = false;
    public static boolean Y = false;
    public static boolean Z = false;
    public static long a = 0;
    public static boolean aA = false;
    public static String aB = null;
    public static boolean aC = false;
    public static boolean aD = false;
    public static boolean aE = true;
    public static int aF = 0;
    public static boolean aG = false;
    public static String aH = "Chrome";
    public static String aI = "";
    public static boolean aJ = false;
    public static boolean aK = false;
    public static int aL = 0;
    public static int aM = 0;
    public static int aN = 0;
    public static int aO = 0;
    public static int aP = 0;
    public static int aQ = 0;
    public static int aR = 0;
    public static int aS = 10;
    public static int aT = 10;
    public static int aU = 10;
    public static boolean aV = false;
    public static boolean aW = false;
    public static boolean aX = false;
    public static boolean aY = false;
    public static boolean aZ = false;
    public static boolean aa = false;
    public static boolean ab = false;
    public static String ac = "preference_shuffle_play";
    public static String ad = "preference_block_skips";
    public static String ae = "PREFERENCE_HASH_VALUE";
    public static String af = "PREFRENCE_DFP_SESSION_COUNTER";
    public static String ag = "PREFERENCE_DFP_SESSION_DATE";
    public static String ah = "LISTENET_KEY_VIDEO_AD";
    public static String ai = "LISTENET_KEY_HOME_CAROUSEL";
    public static int aj = -1;
    public static int ak = -1;
    public static int al = -1;
    public static int am = -1;
    public static String an = "";
    public static long ao = -1;
    public static int ap = -1;
    public static int aq = -1;
    public static int ar = -1;
    public static String as = "";
    public static boolean at = false;
    public static boolean au = false;
    public static boolean av = false;
    public static String aw = "";
    public static String ax = "";
    public static int ay = -1;
    public static boolean az = false;
    public static boolean b = false;
    public static String bA = "";
    public static String bB = "";
    public static String bC = "";
    public static String bD = "";
    public static boolean bE = false;
    public static String bF = "";
    public static String bG = "";
    public static String bH = "";
    public static String bI = "";
    public static String bJ = "";
    public static String bK = "";
    public static String bL = "";
    public static String bM = "";
    public static boolean bN = false;
    public static String bO = "";
    public static int bP = -1;
    public static int bQ = 0;
    public static String bR = "";
    public static String bS = "";
    public static boolean bT = true;
    public static String bU = "GaanaAndroidApp";
    public static String bV = "English";
    public static final FontFamily bW = FontFamily.ROBOTO_REGULAR;
    public static final FontFamily bX = FontFamily.ROBOTO_EXTRA_BOLD;
    public static String bY = "UA-23401975-28";
    public static int bZ = 0;
    public static boolean ba = false;
    public static boolean bb = false;
    public static String bc = "";
    public static String bd = "";
    public static String be = "";
    public static String bf = "";
    public static String bg = "";
    public static String bh = "";
    public static String bi = "";
    public static String bj = "";
    public static String bk = "";
    public static String bl = "";
    public static String bm = "";
    public static String bn = "";
    public static String bo = "";
    public static String bp = "";
    public static String bq = "";
    public static String br = "";
    public static String bs = "";
    public static String bt = "";
    public static String bu = "";
    public static String bv = "";
    public static String bw = "";
    public static String bx = "";
    public static String by = "";
    public static String bz = "";
    public static boolean c = false;
    public static int cA = 520;
    public static String cB = "";
    public static String cC = "";
    public static String cD = "0";
    public static String cE = "Addition to playlist has failed. Please try again.";
    public static boolean cF = false;
    public static boolean cG = false;
    public static boolean cH = false;
    public static long cI = 259200000;
    public static int cJ = 100;
    public static int cK = 30000;
    public static boolean cL = false;
    public static int cM = 1;
    public static boolean cN = false;
    public static int cO = 1024;
    public static int cP = 0;
    public static String cQ = "global";
    public static String cR = "local";
    public static String cS = "global";
    public static String cT = "seconds";
    public static String cU = "sessions";
    public static String cV = "3";
    public static boolean cW = false;
    public static String cX = "Device memory low";
    public static boolean cY = false;
    public static boolean cZ = true;
    public static boolean ca = false;
    public static boolean cb = false;
    public static boolean cc = false;
    public static String cd = "DOWNLOAD_GIF";
    public static int ce = -1;
    public static boolean cf = false;
    public static String cg = "";
    public static String ch = "";
    public static int ci = 0;
    public static HashMap<String, Boolean> cj = new HashMap();
    public static HashMap<String, Boolean> ck = new HashMap();
    public static String cl = "";
    public static int cm = 0;
    public static long cn = 0;
    public static long co = 0;
    public static String cp = "-1";
    public static String cq = "-1";
    public static String cr = "-1";
    public static boolean cs = false;
    public static String ct = "IN";
    public static boolean cu = false;
    public static boolean cv = false;
    public static boolean cw = true;
    public static String cx = null;
    public static boolean cy = false;
    public static String cz = "5.2.0";
    public static boolean d = true;
    public static int dA = 5;
    public static boolean dB = false;
    public static String dC = "unknown";
    public static boolean dD = false;
    public static String dE = "HOME_TOP_BANNER";
    public static String dF = "HOME_BOTTOM_BANNER";
    public static String dG = "HOME_320x100";
    public static String dH = "PL_BOTTOM_BANNER";
    public static String dI = "AL_BOTTOM_BANNER";
    public static String dJ = "FAV_BOTTOM_BANNER";
    public static String dK = "AR_BOTTOM_BANNER";
    public static String dL = "RL_BOTTOM_BANNER";
    public static String dM = "RM_BOTTOM_BANNER";
    public static String dN = "SEARCH_BOTTOM_BANNER";
    public static String dO = "FAV_AL_BOTTOM_BANNER";
    public static String dP = "FAV_PL_BOTTOM_BANNER";
    public static String dQ = "FAV_RL_BOTTOM_BANNER";
    public static String dR = "FAV_RM_BOTTOM_BANNER";
    public static String dS = "FAV_TR_BOTTOM_BANNER";
    public static String dT = "RADIO_TAB";
    public static String dU = "RL_DETAIL_320x100";
    public static String dV = "RM_DETAIL_320x100";
    public static String dW = "RM_DETAIL_320x50";
    public static String dX = "RL_DETAIL_320x50";
    public static String dY = "RL_VIEW_ALL_320x50";
    public static String dZ = "RM_VIEW_ALL_320x50";
    public static HashMap<String, Integer> da = null;
    public static String db = "";
    public static boolean dc = false;
    public static int dd = -1;
    public static long de = -1;
    public static int df = 5;
    public static int dg = 3000;
    public static int dh = 50;
    public static int di = 30;
    public static String dj = GaanaApplication.getContext().getResources().getString(R.string.default_onboard_player_text);
    public static String dk = GaanaApplication.getContext().getResources().getString(R.string.default_onboard_player_bottom_text);
    public static String dl = GaanaApplication.getContext().getResources().getString(R.string.default_onboard_player_stop_text);
    public static int dm = 0;
    public static boolean dn = false;
    /* renamed from: do */
    public static int f15do = 100;
    public static int dp = 30;
    public static int dq = 24;
    public static int dr = 3000;
    public static int ds = 0;
    public static GaanaTheme dt = null;
    public static boolean du = false;
    public static int dv = 3;
    public static int dw = 3;
    public static int dx = 5;
    public static int dy = 4;
    public static String dz = "";
    public static boolean e = false;
    public static String ea = "OC_BOTTOM_BANNER";
    public static String eb = "DFP_SECTION_OC_BANNER";
    public static String ec = "SOCIAL_FEED_BOTTOM_BANNER";
    public static int ed = 0;
    public static boolean ee = false;
    public static TreeSet<TrackSpan> ef = null;
    public static TreeSet<CookieSpan> eg = null;
    public static HashMap<String, String> eh = null;
    public static HashMap<String, String> ei = new HashMap();
    public static final n ej = new n(104857600, null);
    public static int ek = 0;
    public static boolean el = false;
    public static HashMap<Integer, Boolean> em = new HashMap();
    public static int en = 0;
    private static final Pattern eo = Pattern.compile(Pattern.quote("*˜#$#˜*"));
    public static int f = 3;
    public static int g = 2;
    public static int h = -1;
    public static boolean i = false;
    public static String j = "";
    public static boolean k = false;
    public static boolean l = false;
    public static boolean m = false;
    public static boolean n = false;
    public static String o = "";
    public static boolean p = true;
    public static int q;
    public static int r;
    public static int s;
    public static int t;
    public static int u;
    public static int v;
    public static int w;
    public static int x;
    public static int y;
    public static int z;

    public enum ACTION_TYPE {
        DEFAULT(101),
        DEDICATION(102),
        OCCASSION(103),
        ONE_TOUCH_RADIO(104),
        CREATE_PLAYLIST(105),
        HEADER(106),
        SOCIAL_FEED(107),
        TRIAL_CARD(108),
        SUBSCRIPTION_TRIAL_CARD(109),
        SUBSCRIPTION_CARD(110),
        FB_LOGIN_CARD(111),
        GAANA_VIDEO(113),
        SHARE_CARD(114),
        FB_LIVE(115),
        THEME_SETTINGS(116),
        DEEPLINK(117),
        RADIO_WITHOUT_DETAILS(119),
        ONE_TOUCH_DIALOG(120),
        JUKE_LANDING_PAGE(121);
        
        private int numVal;

        private ACTION_TYPE(int i) {
            this.numVal = i;
        }

        public int getNumVal() {
            return this.numVal;
        }
    }

    public enum ErrorType {
        NETWORK_ERROR,
        PLAYER_ERROR,
        JSON_ERROR,
        TEMPORARY_NETWORK_ERROR,
        OTHER,
        PERMISSION_DENIED
    }

    public enum QUEUE_ACTION {
        SWIPE,
        MOVE,
        UNDO
    }

    public enum REVAMPED_DETAIL_CAROUSAL_CARD_TYPE {
        GALLERY(0),
        IMAGE(1),
        TEXT(2),
        CREATED_BY(3),
        FOLLOW_ME(4),
        VIDEO(5),
        AD(6),
        DEEPLINK(7),
        META(8);
        
        private int numVal;

        private REVAMPED_DETAIL_CAROUSAL_CARD_TYPE(int i) {
            this.numVal = i;
        }

        public int getNumVal() {
            return this.numVal;
        }
    }

    public enum REVAMPED_DETAIL_SECTION_TYPE {
        CAROUSAL(0),
        HOR_SCROLL(1),
        LIST(2),
        GRID2x2(3),
        TEXT(4),
        PROMOTION(5),
        ADS(6);
        
        private int numVal;

        private REVAMPED_DETAIL_SECTION_TYPE(int i) {
            this.numVal = i;
        }

        public int getNumVal() {
            return this.numVal;
        }
    }

    public enum REVAMPED_DETAIL_TYPE {
        ALBUM(0),
        PLAYLIST(1),
        RADIO(2),
        ARTIST(3),
        SPECIALs(4);
        
        private int numVal;

        private REVAMPED_DETAIL_TYPE(int i) {
            this.numVal = i;
        }

        public int getNumVal() {
            return this.numVal;
        }
    }

    public enum REVAMPED_DETAIL_VIEW_TYPE {
        RECT_CENTER_TILE(0),
        SQUARE_TILE(1),
        RECT_BOTTOM_TILE(2),
        ARTIST_PAGE_LIST(3),
        TRACK_TAGGED_LIST(4),
        SPECIALS_LIST(5),
        SQUARE_GRID(6),
        UNDEFINED(7);
        
        private int numVal;

        private REVAMPED_DETAIL_VIEW_TYPE(int i) {
            this.numVal = i;
        }

        public int getNumVal() {
            return this.numVal;
        }
    }

    public enum SortOrder {
        Default,
        TrackName,
        ArtistName,
        Popularity,
        DownloadTime,
        Name
    }

    public enum VIEW_SIZE {
        CARD_LARGE(203),
        SCROLL_RECTANGLE(104),
        SCROLL_RECTANGLE_WITHOUT_TXT(105),
        SCROLL_GENERIC(HttpStatusCodes.STATUS_CODE_CREATED),
        SCROLL_BIG_SQAUE(207),
        SCROLL_BIG_SQAUE_WITHOUT_TXT(208),
        CARD_SOCIAL(GooglePlusLogin.RC_SIGN_IN),
        SCROLL_MEDIUM_SQAUE(205),
        SCROLL_MEDIUM_CIRCLE(HttpStatusCodes.STATUS_CODE_NO_CONTENT),
        SCROLL_RECTANGLE_DISCOVER(GooglePlusLogin.RC_CREDENTIALS_SAVE),
        CAROUSEL_VIEW(202),
        RECENTLY_PLAYED_SMALL(213),
        SPONSORED_OC_SIZE(214),
        GRID_LARGE(216),
        GRID_RECTANGLE_255x142(217),
        GRID_RECTANGLE_MEDIUM_140x250(218),
        CARD_SMALL(219),
        CARD_MEDIUM(220),
        CAROUSEL_VIEW_XL_SQUARE(221),
        SETTINGS_ITEM(222),
        CARD_LARGE_NEW(223),
        CARD_LARGE_XL(224),
        CARD_LARGE_XXL(225),
        GRID_LARGE_MIX(226),
        CARD_BIG_SQUARE(227),
        CARD_BIGGER_SQUARE(228),
        TAGS(229);
        
        private int numVal;

        private VIEW_SIZE(int i) {
            this.numVal = i;
        }

        public int getNumVal() {
            return this.numVal;
        }
    }

    public enum VIEW_SUBTYPE {
        DEFAULT(0),
        SOCIAL(1),
        CHAMELEON(2),
        SEARCH_TRENDING(3);
        
        private int numVal;

        private VIEW_SUBTYPE(int i) {
            this.numVal = i;
        }

        public int getNumVal() {
            return this.numVal;
        }
    }

    public enum VoiceActivatedAdsFormat {
        Default,
        Page_open,
        Play
    }

    public enum WebLaunchFLag {
        Default,
        InAppBrowser,
        ExternalBrowser,
        Deeplink
    }

    public static int s() {
        return 10004;
    }

    public static ListingComponents a() {
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b("My Gaana Zone");
        ArrayList arrayList = new ArrayList();
        arrayList.add(a(BusinessObjectType.Tracks));
        arrayList.add(a(BusinessObjectType.Albums));
        arrayList.add(a(BusinessObjectType.Playlists));
        arrayList.add(a(BusinessObjectType.Radios));
        arrayList.add(a(BusinessObjectType.Artists));
        arrayList.add(a(BusinessObjectType.FavoriteOccasions));
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingButton a(BusinessObjectType businessObjectType) {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingButton listingButton;
        URLManager uRLManager;
        if (businessObjectType == BusinessObjectType.Playlists) {
            listingButton = new ListingButton();
            listingButton.b(resources.getString(R.string.tab_playlists));
            listingButton.a(resources.getString(R.string.tab_playlists));
            listingButton.c(true);
            listingButton.b(true);
            listingButton.a(new m());
            listingButton.c(DownloadPlaylistItemView.class.getName());
            listingButton.a(new m());
            uRLManager = new URLManager();
            uRLManager.a("https://api.gaana.com/user.php?type=myplaylists&subtype=favorites&limit=0,100");
            uRLManager.b(1);
            uRLManager.a(Boolean.valueOf(true));
            uRLManager.a(BusinessObjectType.Playlists);
            listingButton.a(uRLManager);
            return listingButton;
        } else if (businessObjectType == BusinessObjectType.Albums) {
            listingButton = new ListingButton();
            listingButton.a(resources.getString(R.string.tab_artist_album));
            listingButton.b(resources.getString(R.string.tab_artist_album));
            listingButton.c(true);
            listingButton.b(true);
            listingButton.a(new m());
            listingButton.c(DownloadAlbumItemView.class.getName());
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.Albums);
            uRLManager.a("https://api.gaana.com/user.php?type=myalbums&subtype=favorites&limit=0,100");
            uRLManager.a(Boolean.valueOf(true));
            uRLManager.b(1);
            uRLManager.a(BusinessObjectType.Albums);
            listingButton.a(uRLManager);
            return listingButton;
        } else if (businessObjectType == BusinessObjectType.Tracks) {
            listingButton = new ListingButton();
            listingButton.a(resources.getString(R.string.tab_artist_songs));
            listingButton.b(resources.getString(R.string.tab_artist_songs));
            listingButton.c(true);
            listingButton.b(true);
            listingButton.a(new m());
            listingButton.c(DownloadSongListingView.class.getName());
            uRLManager = new URLManager();
            uRLManager.a(Boolean.valueOf(true));
            uRLManager.a("https://api.gaana.com/user.php?type=mysongs&subtype=favorites&limit=0,100");
            uRLManager.b(1);
            uRLManager.a(BusinessObjectType.Tracks);
            listingButton.a(uRLManager);
            return listingButton;
        } else if (businessObjectType == BusinessObjectType.Radios) {
            listingButton = new ListingButton();
            listingButton.a(resources.getString(R.string.tab_radios));
            listingButton.b(resources.getString(R.string.tab_radios));
            listingButton.c(true);
            listingButton.b(true);
            listingButton.a(new m());
            listingButton.c(RadioItemView.class.getName());
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.Radios);
            uRLManager.b(1);
            uRLManager.a("https://api.gaana.com/radio.php?type=radio&subtype=favorite_radios");
            uRLManager.a(Boolean.valueOf(true));
            listingButton.a(uRLManager);
            return listingButton;
        } else if (businessObjectType == BusinessObjectType.Artists) {
            listingButton = new ListingButton();
            listingButton.a(resources.getString(R.string.tab_artists));
            listingButton.b(resources.getString(R.string.tab_artists));
            listingButton.c(true);
            listingButton.b(true);
            listingButton.a(new m());
            listingButton.c(ArtistItemView.class.getName());
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.Artists);
            uRLManager.a("https://api.gaana.com/user.php?type=myartists&subtype=favorites&limit=0,100");
            uRLManager.b(1);
            uRLManager.a(Boolean.valueOf(true));
            uRLManager.a(BusinessObjectType.Artists);
            listingButton.a(uRLManager);
            return listingButton;
        } else if (businessObjectType != BusinessObjectType.FavoriteOccasions) {
            return null;
        } else {
            listingButton = new ListingButton();
            listingButton.a(resources.getString(R.string.tab_occasions));
            listingButton.b(resources.getString(R.string.tab_occasions));
            listingButton.c(true);
            listingButton.b(true);
            listingButton.a(new m());
            listingButton.c(FavoriteOccasionItemView.class.getName());
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.FavoriteOccasions);
            uRLManager.a("https://api.gaana.com/user.php?type=myoccasions&subtype=favorites&limit=0,100");
            uRLManager.b(1);
            uRLManager.a(Boolean.valueOf(true));
            uRLManager.a(BusinessObjectType.FavoriteOccasions);
            listingButton.a(uRLManager);
            return listingButton;
        }
    }

    public static ListingButton a(BusinessObjectType businessObjectType, boolean z) {
        Resources resources = GaanaApplication.getContext().getResources();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        ListingButton listingButton;
        URLManager uRLManager;
        long b;
        String k;
        if (businessObjectType == BusinessObjectType.Playlists) {
            listingButton = new ListingButton();
            listingButton.b(resources.getString(R.string.tab_playlists));
            listingButton.a(resources.getString(R.string.tab_playlists));
            listingButton.c(true);
            listingButton.c(DownloadPlaylistItemView.class.getName());
            uRLManager = new URLManager();
            uRLManager.a("https://api.gaana.com/user.php?type=myplaylists&subtype=favorites&fromtime=<currentTime>&limit=0,100");
            uRLManager.b(1);
            uRLManager.a(BusinessObjectType.Playlists);
            uRLManager.b(Boolean.valueOf(false));
            b = d.a().b(0, "favorite_sync_playlist", false);
            k = uRLManager.k();
            if (b > 0 && (currentTimeMillis - b > 86400 || z)) {
                uRLManager.a(k.replace("<currentTime>", String.valueOf(b)));
            } else if (z) {
                uRLManager.a("https://api.gaana.com/user.php?type=myplaylists&subtype=favorites&limit=0,100");
            } else {
                uRLManager = null;
            }
            listingButton.a(uRLManager);
            return listingButton;
        } else if (businessObjectType == BusinessObjectType.Albums) {
            listingButton = new ListingButton();
            listingButton.a(resources.getString(R.string.tab_artist_album));
            listingButton.b(resources.getString(R.string.tab_artist_album));
            listingButton.c(true);
            listingButton.c(DownloadAlbumItemView.class.getName());
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.Albums);
            uRLManager.a("https://api.gaana.com/user.php?type=myalbums&subtype=favorites&fromtime=<currentTime>&limit=0,100");
            uRLManager.b(1);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.a(BusinessObjectType.Albums);
            b = d.a().b(0, "favorite_sync_album", false);
            k = uRLManager.k();
            if (b > 0 && (currentTimeMillis - b > 86400 || z)) {
                uRLManager.a(k.replace("<currentTime>", String.valueOf(b)));
            } else if (z) {
                uRLManager.a("https://api.gaana.com/user.php?type=myalbums&subtype=favorites&limit=0,100");
            } else {
                uRLManager = null;
            }
            listingButton.a(uRLManager);
            return listingButton;
        } else if (businessObjectType == BusinessObjectType.Tracks) {
            listingButton = new ListingButton();
            listingButton.a(resources.getString(R.string.tab_artist_songs));
            listingButton.b(resources.getString(R.string.tab_artist_songs));
            listingButton.c(true);
            listingButton.c(DownloadSongListingView.class.getName());
            uRLManager = new URLManager();
            uRLManager.a("https://api.gaana.com/user.php?type=mysongs&subtype=favorites&fromtime=<currentTime>&limit=0,100");
            uRLManager.b(1);
            uRLManager.a(BusinessObjectType.Tracks);
            uRLManager.b(Boolean.valueOf(false));
            b = d.a().b(0, "favorite_sync_tracks", false);
            k = uRLManager.k();
            if (b > 0 && (currentTimeMillis - b > 86400 || z)) {
                uRLManager.a(k.replace("<currentTime>", String.valueOf(b)));
            } else if (z) {
                uRLManager.a("https://api.gaana.com/user.php?type=mysongs&subtype=favorites&limit=0,100");
            } else {
                uRLManager = null;
            }
            listingButton.a(uRLManager);
            return listingButton;
        } else if (businessObjectType == BusinessObjectType.Radios) {
            listingButton = new ListingButton();
            listingButton.a(resources.getString(R.string.tab_radios));
            listingButton.b(resources.getString(R.string.tab_radios));
            listingButton.c(true);
            listingButton.c(RadioItemView.class.getName());
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.Radios);
            uRLManager.b(1);
            uRLManager.a("https://api.gaana.com/radio.php?type=radio&subtype=favorite_radios&fromtime=<currentTime>");
            uRLManager.b(Boolean.valueOf(false));
            b = d.a().b(0, "favorite_sync_radios", false);
            k = uRLManager.k();
            if (b > 0 && (currentTimeMillis - b > 86400 || z)) {
                uRLManager.a(k.replace("<currentTime>", String.valueOf(b)));
            } else if (z) {
                uRLManager.a("https://api.gaana.com/radio.php?type=radio&subtype=favorite_radios");
            } else {
                uRLManager = null;
            }
            listingButton.a(uRLManager);
            return listingButton;
        } else if (businessObjectType == BusinessObjectType.Artists) {
            listingButton = new ListingButton();
            listingButton.a(resources.getString(R.string.tab_artists));
            listingButton.b(resources.getString(R.string.tab_artists));
            listingButton.c(true);
            listingButton.c(ArtistItemView.class.getName());
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.Artists);
            uRLManager.a("https://api.gaana.com/user.php?type=myartists&subtype=favorites&fromtime=<currentTime>&limit=0,100");
            uRLManager.b(1);
            uRLManager.a(BusinessObjectType.Artists);
            uRLManager.b(Boolean.valueOf(false));
            b = d.a().b(0, "favorite_sync_artist", false);
            k = uRLManager.k();
            if (b > 0 && (currentTimeMillis - b > 86400 || z)) {
                uRLManager.a(k.replace("<currentTime>", String.valueOf(b)));
            } else if (z) {
                uRLManager.a("https://api.gaana.com/user.php?type=myartists&subtype=favorites&limit=0,100");
            } else {
                uRLManager = null;
            }
            listingButton.a(uRLManager);
            return listingButton;
        } else if (businessObjectType != BusinessObjectType.FavoriteOccasions) {
            return null;
        } else {
            listingButton = new ListingButton();
            listingButton.a(resources.getString(R.string.mymusic_occasions));
            listingButton.b(resources.getString(R.string.mymusic_occasions));
            listingButton.c(true);
            listingButton.c(FavoriteOccasionItemView.class.getName());
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.FavoriteOccasions);
            uRLManager.a("https://api.gaana.com/user.php?type=myoccasions&subtype=favorites&fromtime=<currentTime>&limit=0,100");
            uRLManager.b(1);
            uRLManager.a(BusinessObjectType.FavoriteOccasions);
            uRLManager.b(Boolean.valueOf(false));
            b = d.a().b(0, "favorite_sync_occasions", false);
            k = uRLManager.k();
            if (b > 0 && (currentTimeMillis - b > 86400 || z)) {
                uRLManager.a(k.replace("<currentTime>", String.valueOf(b)));
            } else if (z) {
                uRLManager.a("https://api.gaana.com/user.php?type=myoccasions&subtype=favorites&limit=0,100");
            } else {
                uRLManager = null;
            }
            listingButton.a(uRLManager);
            return listingButton;
        }
    }

    public static ListingComponents a(Radio radio) {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b(resources.getString(R.string.radio_details_title));
        listingComponents.a((BusinessObject) radio);
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        URLManager uRLManager = new URLManager();
        listingButton.c(DiscoverItemView.class.getName());
        uRLManager.a(BusinessObjectType.GenericItems);
        uRLManager.a(Priority.HIGH);
        uRLManager.a(60);
        if (radio.getType().equals(c.d.c)) {
            uRLManager.a("https://api.gaana.com/home/radio/similar-mirchi/<radio_id>".replace("<radio_id>", radio.getBusinessObjId()));
        } else {
            uRLManager.a("https://api.gaana.com/radio/gaana/detail/<radio_id>?limit=0,15".replace("<radio_id>", radio.getBusinessObjId()));
        }
        uRLManager.b(Boolean.valueOf(true));
        uRLManager.f(true);
        listingButton.a(uRLManager);
        listingButton.c(true);
        arrayList.add(listingButton);
        listingComponents.a(true, RadioItemView.class.getName());
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents b() {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b(resources.getString(R.string.radio_details_title));
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        URLManager uRLManager = new URLManager();
        listingButton.c(n());
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.b(BusinessObjectType.Albums);
        uRLManager.a(c.s);
        uRLManager.b(Boolean.valueOf(true));
        uRLManager.f(true);
        uRLManager.a(Priority.HIGH);
        uRLManager.a(60);
        uRLManager.i(true);
        listingButton.a(uRLManager);
        listingButton.c(true);
        arrayList.add(listingButton);
        listingComponents.a(true, AlbumItemView.class.getName());
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents c() {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.a(null);
        listingComponents.b(resources.getString(R.string.similar_album));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        URLManager uRLManager = new URLManager();
        listingButton.c(v());
        uRLManager.a(BusinessObjectType.Albums);
        uRLManager.a("https://api.gaana.com/index.php?type=album&subtype=similar_album&album_id=");
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents a(boolean z) {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.a(null);
        listingComponents.b(resources.getString(R.string.notifications));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.c(NotificationItemView.class.getName());
        listingButton.c(true);
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Notifications);
        uRLManager.a("https://api.gaana.com/user.php?type=get_inapp_notification");
        uRLManager.c(Boolean.valueOf(z));
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents d() {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.a(null);
        listingComponents.b(resources.getString(R.string.similar_artists));
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        URLManager uRLManager = new URLManager();
        listingButton.c(ArtistItemView.class.getName());
        uRLManager.a(BusinessObjectType.Artists);
        uRLManager.a("https://api.gaana.com/index.php?type=artist&subtype=similar_artist&artist_id=");
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents e() {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b(resources.getString(R.string.playlist_details_title));
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.c(true);
        URLManager uRLManager = new URLManager();
        uRLManager.a(Priority.HIGH);
        uRLManager.a(60);
        listingButton.c(n());
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.b(BusinessObjectType.Playlists);
        uRLManager.a(c.w);
        uRLManager.b(Boolean.valueOf(true));
        uRLManager.f(true);
        uRLManager.i(true);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents f() {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b(resources.getString(R.string.playlist_details_title));
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.c(true);
        URLManager uRLManager = new URLManager();
        uRLManager.a(Priority.HIGH);
        uRLManager.a(60);
        listingButton.c(n());
        uRLManager.a(BusinessObjectType.GenericItems);
        uRLManager.b(BusinessObjectType.Playlists);
        uRLManager.a("https://api.gaana.com/home/tag/data?tag_id=");
        uRLManager.b(Boolean.valueOf(true));
        uRLManager.a(true);
        uRLManager.f(true);
        uRLManager.i(true);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents g() {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b(resources.getString(R.string.playlist_details_title));
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.c(true);
        URLManager uRLManager = new URLManager();
        uRLManager.a(Priority.HIGH);
        uRLManager.a(60);
        listingButton.c(ShareableSongsView.class.getName());
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.b(BusinessObjectType.Playlists);
        uRLManager.a(c.w);
        uRLManager.b(Boolean.valueOf(true));
        uRLManager.f(true);
        uRLManager.i(true);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents h() {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b(resources.getString(R.string.playlist_details_title));
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.c(true);
        URLManager uRLManager = new URLManager();
        uRLManager.a(Priority.HIGH);
        uRLManager.a(60);
        listingButton.c(VotingSongsItemView.class.getName());
        uRLManager.a(BusinessObjectType.JukePlaylist);
        uRLManager.a("https://apiv2.gaana.com/collab/playlist/detail?");
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.f(false);
        uRLManager.i(true);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents i() {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b(resources.getString(R.string.opt_edit_playlist));
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        URLManager uRLManager = new URLManager();
        listingButton.c(EditPlaylistSelectSongView.class.getName());
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.a(c.w);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents a(String str, boolean z) {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b(resources.getString(R.string.artists_title));
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.c(n());
        listingButton.c(true);
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.GenericItems);
        uRLManager.a(true);
        uRLManager.b(BusinessObjectType.Artists);
        uRLManager.a(Boolean.valueOf(true));
        uRLManager.a(c.q);
        uRLManager.a(Priority.HIGH);
        uRLManager.a(60);
        listingButton.a(uRLManager);
        listingButton.a(resources.getString(R.string.top_songs));
        arrayList.add(listingButton);
        listingButton = new ListingButton();
        if (z) {
            listingButton.c(DownloadAlbumItemView.class.getName());
        } else {
            listingButton.c(NewGenericItemView.class.getName());
        }
        listingButton.c(true);
        uRLManager = new URLManager();
        if (z) {
            uRLManager.a(BusinessObjectType.Albums);
            uRLManager.b(BusinessObjectType.Artists);
        } else {
            uRLManager.a(BusinessObjectType.GenericItems);
            uRLManager.b(BusinessObjectType.GenericItems);
        }
        uRLManager.a(Boolean.valueOf(true));
        uRLManager.a(c.r);
        uRLManager.a(Priority.HIGH);
        uRLManager.a(60);
        listingButton.a(uRLManager);
        listingButton.a(resources.getString(R.string.top_playlist));
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents a(SearchType searchType, ArrayList<Track> arrayList) {
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b("Favorite Songs");
        ListingButton listingButton = new ListingButton();
        if (searchType == SearchType.Playlist_Search) {
            listingButton.c(RadioButtonGenericView.class.getName());
        } else {
            listingButton.c(SearchItemView.class.getName());
        }
        listingButton.a("Track");
        listingButton.b("Track");
        listingButton.c(false);
        listingButton.b(true);
        listingButton.b((ArrayList) arrayList);
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.b(1);
        uRLManager.a(Boolean.valueOf(true));
        uRLManager.a("https://api.gaana.com/user.php?type=mysongs&subtype=favorites&limit=0,100");
        listingButton.a(new m());
        listingButton.a(uRLManager);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(listingButton);
        listingComponents.a(arrayList2);
        return listingComponents;
    }

    public static ListingComponents a(String[] strArr, ArrayList<Track> arrayList) {
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b(GaanaApplication.getContext().getResources().getString(R.string.opt_suggestions));
        ListingButton listingButton = new ListingButton();
        listingButton.c(RadioButtonGenericView.class.getName());
        listingButton.a("Track");
        listingButton.b("Track");
        int i = 0;
        listingButton.c(false);
        listingButton.b((ArrayList) arrayList);
        URLManager uRLManager = new URLManager();
        uRLManager.b(1);
        uRLManager.a(Boolean.valueOf(false));
        String str = "";
        if (strArr == null || strArr.length == 0) {
            uRLManager.a(UserRecentActivity.class);
        } else {
            uRLManager.a(BusinessObjectType.Tracks);
            StringBuilder stringBuilder = new StringBuilder();
            while (i < strArr.length) {
                stringBuilder.append(",");
                stringBuilder.append(strArr[i]);
                i++;
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("https://api.gaana.com/playlist/recom?trackIds=");
            stringBuilder2.append(stringBuilder.toString().replaceFirst(",", ""));
            str = stringBuilder2.toString();
        }
        uRLManager.a(str);
        listingButton.a(uRLManager);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(listingButton);
        listingComponents.a(arrayList2);
        return listingComponents;
    }

    public static ListingComponents b(SearchType searchType, ArrayList<Track> arrayList) {
        ArrayList n;
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b("Queued Songs");
        ListingButton listingButton = new ListingButton();
        if (searchType == SearchType.Playlist_Search) {
            listingButton.c(RadioButtonGenericView.class.getName());
        } else {
            listingButton.c(SearchItemView.class.getName());
        }
        listingButton.a("Track");
        listingButton.b("Track");
        listingButton.c(false);
        listingButton.d(true);
        listingButton.b((ArrayList) arrayList);
        if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA) {
            n = PlayerManager.a(GaanaApplication.getContext()).n();
        } else {
            n = o.a().e();
        }
        ArrayList arrayList2 = new ArrayList();
        if (n != null && n.size() > 0) {
            Iterator it = n.iterator();
            while (it.hasNext()) {
                PlayerTrack playerTrack = (PlayerTrack) it.next();
                if (playerTrack.b() != null) {
                    arrayList2.add(playerTrack.b());
                }
            }
        }
        listingButton.a(arrayList2);
        n = new ArrayList();
        n.add(listingButton);
        listingComponents.a(n);
        return listingComponents;
    }

    public static ListingComponents a(SearchType searchType, String str, String str2) {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.a(null);
        listingComponents.b(resources.getString(R.string.opt_search));
        listingComponents.a(Boolean.valueOf(true));
        String str3 = "";
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        if (searchType == SearchType.Playlist_Search || searchType == SearchType.OnlySongs) {
            listingButton.c(RadioButtonGenericView.class.getName());
        } else {
            listingButton.c(SearchItemView.class.getName());
        }
        URLManager uRLManager = new URLManager();
        if (str2.contains("Track")) {
            uRLManager.a(BusinessObjectType.Tracks);
            str3 = resources.getString(R.string.track);
        } else if (str2.contains("Album")) {
            uRLManager.a(BusinessObjectType.Albums);
            str3 = resources.getString(R.string.album_text).trim();
        } else if (str2.contains("Artist")) {
            uRLManager.a(BusinessObjectType.Artists);
            str3 = resources.getString(R.string.artist);
        } else if (str2.contains("Playlist")) {
            uRLManager.a(BusinessObjectType.Playlists);
            str3 = resources.getString(R.string.playlist_text);
        } else if (str2.contains("Radio")) {
            uRLManager.a(BusinessObjectType.Radios);
            str3 = resources.getString(R.string.radio);
        }
        String str4 = "https://gsearch.gaana.com/gaanasearch-api/mobilesuggest/autosuggest-lite-vltr-ro?";
        if (!bV.equalsIgnoreCase("English")) {
            str4 = "https://gsearch.gaana.com/gaanasearch-api/mobilesuggest/autosuggest-lite-vltr-ro?";
        }
        HashMap hashMap = new HashMap();
        hashMap.put("geoLocation", ct);
        hashMap.put(SearchIntents.EXTRA_QUERY, str);
        hashMap.put("content_filter", InternalAvidAdSessionContext.AVID_API_LEVEL);
        hashMap.put("include", str2.toLowerCase());
        if (!TextUtils.isEmpty(GaanaSearchManager.a().d())) {
            hashMap.put("usrLang", GaanaSearchManager.a().d());
        }
        hashMap.put("isRegSrch", GaanaSearchManager.a().e());
        if (GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
            if (ap.a().e()) {
                hashMap.put("UserType", InternalAvidAdSessionContext.AVID_API_LEVEL);
            } else if (ap.a().i()) {
                hashMap.put("UserType", "1");
            } else {
                hashMap.put("UserType", "0");
            }
        }
        uRLManager.a(hashMap);
        uRLManager.a(str4);
        listingButton.a(uRLManager);
        listingButton.c(false);
        listingButton.a(str3);
        listingButton.b(str3);
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents j() {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.a(null);
        listingComponents.b(resources.getString(R.string.opt_search));
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.c(n());
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.a("https://api.gaana.com/index.php?type=search&subtype=search_song&content_filter=2&key=");
        listingButton.a(uRLManager);
        listingButton.c(true);
        listingButton.a(resources.getString(R.string.tab_artist_songs));
        listingButton.b(resources.getString(R.string.tab_artist_songs));
        arrayList.add(listingButton);
        listingButton = new ListingButton();
        listingButton.c(v());
        uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Albums);
        uRLManager.a("https://api.gaana.com/index.php?type=search&subtype=search_album&content_filter=2&key=");
        listingButton.a(uRLManager);
        listingButton.c(true);
        listingButton.a(resources.getString(R.string.tab_artist_album));
        listingButton.b(resources.getString(R.string.tab_artist_album));
        arrayList.add(listingButton);
        listingButton = new ListingButton();
        listingButton.c(ArtistItemView.class.getName());
        uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Artists);
        uRLManager.a("https://api.gaana.com/index.php?type=search&subtype=search_artist&key=");
        listingButton.a(uRLManager);
        listingButton.c(true);
        listingButton.a(resources.getString(R.string.tab_artists));
        listingButton.b(resources.getString(R.string.tab_artists));
        arrayList.add(listingButton);
        listingButton = new ListingButton();
        listingButton.c(u());
        uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Playlists);
        uRLManager.a("https://api.gaana.com/index.php?type=search&subtype=search_playlist&key=");
        listingButton.a(uRLManager);
        listingButton.c(true);
        listingButton.a(resources.getString(R.string.tab_playlists));
        listingButton.b(resources.getString(R.string.tab_playlists));
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents k() {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.a(resources.getString(R.string.tab_artist_songs));
        listingButton.b(resources.getString(R.string.tab_artist_songs));
        listingButton.c(true);
        listingButton.c(DownloadSongListingView.class.getName());
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Tracks);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingButton = new ListingButton();
        listingButton.a(resources.getString(R.string.tab_artist_album));
        listingButton.b(resources.getString(R.string.tab_artist_album));
        listingButton.c(true);
        listingButton.c(DownloadAlbumItemView.class.getName());
        uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Albums);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingButton = new ListingButton();
        listingButton.a(resources.getString(R.string.tab_playlists));
        listingButton.b(resources.getString(R.string.tab_playlists));
        listingButton.c(true);
        listingButton.c(DownloadPlaylistItemView.class.getName());
        URLManager uRLManager2 = new URLManager();
        uRLManager2.a(BusinessObjectType.Playlists);
        listingButton.a(uRLManager2);
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents l() {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.a(resources.getString(R.string.tab_artist_songs));
        listingButton.b(resources.getString(R.string.tab_artist_songs));
        listingButton.c(true);
        listingButton.c(DownloadSongListingView.class.getName());
        URLManager uRLManager = new URLManager();
        uRLManager.d(true);
        uRLManager.a(BusinessObjectType.Tracks);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingButton = new ListingButton();
        listingButton.a(resources.getString(R.string.tab_artist_album));
        listingButton.b(resources.getString(R.string.tab_artist_album));
        listingButton.c(true);
        listingButton.c(DownloadAlbumItemView.class.getName());
        uRLManager = new URLManager();
        uRLManager.d(true);
        uRLManager.a(BusinessObjectType.Albums);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingButton = new ListingButton();
        listingButton.a(resources.getString(R.string.tab_playlists));
        listingButton.b(resources.getString(R.string.tab_playlists));
        listingButton.c(true);
        listingButton.c(DownloadPlaylistItemView.class.getName());
        uRLManager = new URLManager();
        uRLManager.d(true);
        uRLManager.a(BusinessObjectType.Playlists);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingButton = new ListingButton();
        listingButton.a(resources.getString(R.string.tab_artists));
        listingButton.b(resources.getString(R.string.tab_artists));
        listingButton.c(true);
        listingButton.c(ArtistItemView.class.getName());
        URLManager uRLManager2 = new URLManager();
        uRLManager2.d(true);
        uRLManager2.a(BusinessObjectType.Artists);
        listingButton.a(uRLManager2);
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents a(LayoutConfig layoutConfig) {
        ListingComponents listingComponents = new ListingComponents();
        ArrayList arrayList = new ArrayList();
        listingComponents.a(Boolean.valueOf(true));
        ListingButton listingButton = new ListingButton();
        listingButton.a(layoutConfig.getTabNames1());
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        listingButton = new ListingButton();
        listingButton.a(layoutConfig.getTabNames2());
        arrayList.add(listingButton);
        return listingComponents;
    }

    public static ListingComponents a(a aVar) {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        ArrayList arrayList = new ArrayList();
        listingComponents.a(Boolean.valueOf(true));
        ListingButton listingButton = new ListingButton();
        listingButton.a(resources.getString(R.string.subscription_title));
        listingButton.c(true);
        arrayList.add(listingButton);
        listingButton.c(GaanaPlusItemView.class.getName());
        listingComponents.a(arrayList);
        listingButton = new ListingButton();
        listingButton.a(resources.getString(R.string.myactivity));
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://social.gaana.com/feed/listening/activity");
        uRLManager.b(1);
        uRLManager.a(Boolean.valueOf(false));
        uRLManager.a(BusinessObjectType.SocialFeed);
        listingButton.a(aVar);
        listingButton.a(uRLManager);
        listingButton.c(MyActivityInfoItemView.class.getName());
        listingButton.c(true);
        arrayList.add(listingButton);
        return listingComponents;
    }

    public static ListingComponents m() {
        GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b("Say it with Gaana");
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.c(true);
        URLManager uRLManager = new URLManager();
        uRLManager.a(Priority.HIGH);
        uRLManager.a(60);
        uRLManager.a(BusinessObjectType.PersonaDedications);
        uRLManager.b(BusinessObjectType.PersonaDedications);
        uRLManager.a("https://api.gaana.com/home/personas?gender=<which>");
        uRLManager.b(Boolean.valueOf(true));
        uRLManager.f(true);
        uRLManager.i(true);
        listingButton.c(TwoLineView.class.getName());
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static String n() {
        return DownloadSongsItemView.class.getName();
    }

    private static String u() {
        return DownloadPlaylistItemView.class.getName();
    }

    private static String v() {
        return DownloadAlbumItemView.class.getName();
    }

    public static ListingComponents o() {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.a(null);
        listingComponents.b(resources.getString(R.string.opt_search));
        listingComponents.a(Boolean.valueOf(true));
        listingComponents.a(SearchType.Radio);
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.c(n());
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.a("https://api.gaana.com/index.php?type=search&subtype=search_song&content_filter=2&key=");
        listingButton.a(uRLManager);
        listingButton.a(resources.getString(R.string.tab_artist_songs));
        arrayList.add(listingButton);
        listingButton = new ListingButton();
        listingButton.c(ArtistItemView.class.getName());
        uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Artists);
        uRLManager.a("https://api.gaana.com/index.php?type=search&subtype=search_artist&key=");
        listingButton.a(uRLManager);
        listingButton.a(resources.getString(R.string.tab_artists));
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ListingComponents b(String str, boolean z) {
        Resources resources = GaanaApplication.getContext().getResources();
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.a(resources.getString(R.string.all_results));
        listingButton.b(resources.getString(R.string.all_results));
        listingButton.c(SearchItemView.class.getName());
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.ALL);
        uRLManager.d(true);
        uRLManager.g(false);
        uRLManager.e(true);
        uRLManager.h(z);
        uRLManager.b(str);
        uRLManager.a(NextGenSearchAutoSuggests.class);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingButton = new ListingButton();
        listingButton.a(resources.getString(R.string.tab_artist_songs));
        listingButton.b(resources.getString(R.string.tab_artist_songs));
        listingButton.c(SearchItemView.class.getName());
        uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.d(true);
        uRLManager.g(false);
        uRLManager.e(true);
        uRLManager.h(z);
        uRLManager.b(str);
        uRLManager.a(NextGenSearchAutoSuggests.class);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingButton = new ListingButton();
        listingButton.a(resources.getString(R.string.tab_artist_album));
        listingButton.b(resources.getString(R.string.tab_artist_album));
        listingButton.c(SearchItemView.class.getName());
        uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Albums);
        uRLManager.d(true);
        uRLManager.g(false);
        uRLManager.e(true);
        uRLManager.h(z);
        uRLManager.b(str);
        uRLManager.a(NextGenSearchAutoSuggests.class);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingButton = new ListingButton();
        listingButton.a(resources.getString(R.string.tab_playlists));
        listingButton.b(resources.getString(R.string.tab_playlists));
        listingButton.c(SearchItemView.class.getName());
        uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Playlists);
        uRLManager.d(true);
        uRLManager.g(false);
        uRLManager.e(true);
        uRLManager.h(z);
        uRLManager.b(str);
        uRLManager.a(NextGenSearchAutoSuggests.class);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        if (!z) {
            listingButton = new ListingButton();
            listingButton.a(resources.getString(R.string.tab_artists));
            listingButton.b(resources.getString(R.string.tab_artists));
            listingButton.c(SearchItemView.class.getName());
            URLManager uRLManager2 = new URLManager();
            uRLManager2.a(BusinessObjectType.Artists);
            uRLManager2.d(true);
            uRLManager2.g(false);
            uRLManager2.e(true);
            uRLManager2.h(z);
            uRLManager2.b(str);
            uRLManager2.a(NextGenSearchAutoSuggests.class);
            listingButton.a(uRLManager2);
            arrayList.add(listingButton);
        }
        listingComponents.a(arrayList);
        return listingComponents;
    }

    public static ArrayList<ListingParams> p() {
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.a(GaanaApplication.getContext().getString(R.string.all_results));
        listingButton.b(GaanaApplication.getContext().getString(R.string.all_results));
        listingButton.c(true);
        listingButton.b(true);
        listingButton.a(new z());
        listingButton.c(DownloadSongListingView.class.getName());
        URLManager uRLManager = new URLManager();
        uRLManager.a(Boolean.valueOf(true));
        uRLManager.a("https://api.gaana.com/user.php?type=mysongs&subtype=favorites&limit=0,100");
        uRLManager.b(1);
        uRLManager.a(BusinessObjectType.Tracks);
        listingButton.a(uRLManager);
        ListingParams listingParams = new ListingParams();
        listingParams.e(true);
        listingParams.f(true);
        listingParams.i(true);
        listingParams.b(true);
        listingParams.c(Util.a(uRLManager));
        listingParams.j(false);
        listingParams.k(false);
        listingParams.d(false);
        listingParams.b(PLAYOUT_SECTION_TYPE.MYMUSIC_SONGS.name());
        listingParams.a(listingButton);
        listingParams.a(String.valueOf(e.k));
        arrayList.add(listingParams);
        listingButton = new ListingButton();
        listingButton.a(GaanaApplication.getContext().getString(R.string.favorites_txt));
        listingButton.b(GaanaApplication.getContext().getString(R.string.favorites_txt));
        listingButton.c(true);
        listingButton.b(true);
        listingButton.a(new m());
        listingButton.c(DownloadSongListingView.class.getName());
        URLManager uRLManager2 = new URLManager();
        uRLManager2.a(Boolean.valueOf(true));
        uRLManager2.a("https://api.gaana.com/user.php?type=mysongs&subtype=favorites&limit=0,100");
        uRLManager2.b(1);
        uRLManager2.a(BusinessObjectType.Tracks);
        listingButton.a(uRLManager2);
        ListingParams listingParams2 = new ListingParams();
        listingParams2.e(true);
        listingParams2.f(true);
        listingParams2.i(true);
        listingParams2.b(true);
        listingParams2.c(Util.a(uRLManager2));
        listingParams2.j(true);
        listingParams2.a(SortOrder.TrackName);
        listingParams2.d(false);
        listingParams2.l(true);
        listingParams2.d((int) R.menu.filter_menu_fav_tracks);
        listingParams2.k(false);
        listingParams2.b(PLAYOUT_SECTION_TYPE.MYMUSIC_SONGS.name());
        listingParams2.a(listingButton);
        listingParams2.a(String.valueOf(e.k));
        arrayList.add(listingParams2);
        listingButton = new ListingButton();
        listingButton.a(GaanaApplication.getContext().getString(R.string.downloads));
        listingButton.b(GaanaApplication.getContext().getString(R.string.downloads));
        listingButton.c(true);
        listingButton.f(ap.a().d());
        listingButton.c(DownloadSongListingView.class.getName());
        uRLManager2 = new URLManager();
        uRLManager2.a(BusinessObjectType.Tracks);
        listingButton.a(uRLManager2);
        listingParams2 = new ListingParams();
        listingParams2.a(SortOrder.TrackName);
        listingParams2.e(true);
        listingParams2.f(true);
        listingParams2.i(true);
        listingParams2.h(true);
        listingParams2.b(true);
        listingParams2.c(Util.a(uRLManager2));
        listingParams2.j(true);
        listingParams2.d(false);
        listingParams2.k(false);
        listingParams2.b(PLAYOUT_SECTION_TYPE.MYMUSIC_SONGS.name());
        listingParams2.a(listingButton);
        listingParams2.d((int) R.menu.filter_menu_download_items);
        listingParams2.l(true);
        listingParams2.a(String.valueOf(e.k));
        arrayList.add(listingParams2);
        return arrayList;
    }

    public static ArrayList<ListingParams> q() {
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.a(GaanaApplication.getContext().getString(R.string.all_results));
        listingButton.b(GaanaApplication.getContext().getString(R.string.all_results));
        listingButton.c(true);
        listingButton.c(DownloadPlaylistItemView.class.getName());
        listingButton.b(true);
        listingButton.a(new ae());
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Playlists);
        uRLManager.a(Boolean.valueOf(false));
        listingButton.a(uRLManager);
        ListingParams listingParams = new ListingParams();
        listingParams.e(true);
        listingParams.f(true);
        listingParams.b(true);
        listingParams.c((int) R.layout.item_add_playlist_card);
        listingParams.c(Util.a(uRLManager));
        listingParams.b(PLAYOUT_SECTION_TYPE.MYMUSIC_PLAYLIST.name());
        listingParams.i(true);
        listingParams.j(false);
        listingParams.k(false);
        listingParams.d(false);
        listingParams.a(listingButton);
        listingParams.a(String.valueOf(e.k));
        arrayList.add(listingParams);
        listingButton = new ListingButton();
        listingButton.c(true);
        listingButton.b(GaanaApplication.getContext().getString(R.string.my_music_by_me));
        listingButton.a(GaanaApplication.getContext().getString(R.string.my_music_by_me));
        listingButton.c(ByMePlaylistItemView.class.getName());
        uRLManager = new URLManager();
        HashMap hashMap = new HashMap();
        hashMap.put("type", "myplaylists");
        hashMap.put(LoginManager.TAG_SUBTYPE, "myplaylist_favorites");
        uRLManager.a(hashMap);
        uRLManager.b(1);
        uRLManager.a(Boolean.valueOf(false));
        uRLManager.g(true);
        uRLManager.a("https://api.gaana.com/user.php?type=myplaylists&subtype=myplaylists");
        uRLManager.d(true);
        uRLManager.h(false);
        uRLManager.a(BusinessObjectType.Playlists);
        listingButton.a(uRLManager);
        listingParams = new ListingParams();
        listingParams.e(true);
        listingParams.f(true);
        listingParams.h(true);
        listingParams.b(true);
        listingParams.c((int) R.layout.item_add_playlist_card);
        listingParams.a(SortOrder.TrackName);
        listingParams.c(Util.a(uRLManager));
        listingParams.i(true);
        listingParams.j(true);
        listingParams.d(false);
        listingParams.l(true);
        listingParams.d((int) R.menu.filter_menu_favorites_items);
        listingParams.k(false);
        listingParams.a(String.valueOf(e.k));
        listingParams.b(PLAYOUT_SECTION_TYPE.MY_PLAYLISTS.name());
        listingParams.a(listingButton);
        arrayList.add(listingParams);
        listingButton = new ListingButton();
        listingButton.c(true);
        listingButton.b(true);
        listingButton.a(new m());
        listingButton.b(GaanaApplication.getContext().getString(R.string.favorites_txt));
        listingButton.a(GaanaApplication.getContext().getString(R.string.favorites_txt));
        listingButton.c(DownloadPlaylistItemView.class.getName());
        URLManager uRLManager2 = new URLManager();
        uRLManager2.a("https://api.gaana.com/user.php?type=myplaylists&subtype=favorites&limit=0,100");
        uRLManager2.b(1);
        uRLManager2.a(Boolean.valueOf(true));
        uRLManager2.a(BusinessObjectType.Playlists);
        listingButton.a(uRLManager2);
        ListingParams listingParams2 = new ListingParams();
        listingParams2.e(true);
        listingParams2.f(true);
        listingParams2.i(true);
        listingParams2.b(true);
        listingParams2.a(SortOrder.TrackName);
        listingParams2.c(Util.a(uRLManager2));
        listingParams2.h(false);
        listingParams2.j(true);
        listingParams2.d(false);
        listingParams2.l(true);
        listingParams2.d((int) R.menu.filter_menu_favorites_items);
        listingParams2.k(false);
        listingParams2.b(PLAYOUT_SECTION_TYPE.MYMUSIC_PLAYLIST.name());
        listingParams2.a(listingButton);
        listingParams2.a(String.valueOf(e.k));
        arrayList.add(listingParams2);
        listingButton = new ListingButton();
        listingButton.a(GaanaApplication.getContext().getString(R.string.downloads));
        listingButton.b(GaanaApplication.getContext().getString(R.string.downloads));
        listingButton.c(true);
        listingButton.c(DownloadPlaylistItemView.class.getName());
        uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Playlists);
        listingButton.a(uRLManager);
        listingParams = new ListingParams();
        listingParams.e(true);
        listingParams.f(true);
        listingParams.h(true);
        listingParams.l(true);
        listingParams.a(SortOrder.TrackName);
        listingParams.d((int) R.menu.filter_menu_download_items);
        listingParams.b(true);
        listingParams.c(Util.a(uRLManager));
        listingParams.b(PLAYOUT_SECTION_TYPE.MYMUSIC_PLAYLIST.name());
        listingParams.i(true);
        listingParams.j(true);
        listingParams.d(false);
        listingParams.k(false);
        listingParams.a(listingButton);
        listingParams.a(String.valueOf(e.k));
        arrayList.add(listingParams);
        return arrayList;
    }

    public static ArrayList<ListingParams> r() {
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.a(GaanaApplication.getContext().getString(R.string.all_results));
        listingButton.b(GaanaApplication.getContext().getString(R.string.all_results));
        listingButton.c(true);
        listingButton.a(new z());
        listingButton.c(DownloadAlbumItemView.class.getName());
        listingButton.b(true);
        listingButton.a(new z());
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Albums);
        uRLManager.a(Boolean.valueOf(true));
        listingButton.a(uRLManager);
        ListingParams listingParams = new ListingParams();
        listingParams.c(Util.a(uRLManager));
        listingParams.e(true);
        listingParams.f(true);
        listingParams.i(true);
        listingParams.b(true);
        listingParams.j(false);
        listingParams.d(false);
        listingParams.k(false);
        listingParams.a(listingButton);
        listingParams.a(String.valueOf(e.k));
        listingParams.b(PLAYOUT_SECTION_TYPE.MYMUSIC_ALBUMS.name());
        arrayList.add(listingParams);
        listingButton = new ListingButton();
        listingButton.a(GaanaApplication.getContext().getString(R.string.favorites_txt));
        listingButton.b(GaanaApplication.getContext().getString(R.string.favorites_txt));
        listingButton.c(true);
        listingButton.b(true);
        listingButton.a(new m());
        listingButton.c(DownloadAlbumItemView.class.getName());
        URLManager uRLManager2 = new URLManager();
        uRLManager2.a(BusinessObjectType.Albums);
        uRLManager2.a("https://api.gaana.com/user.php?type=myalbums&subtype=favorites&limit=0,100");
        uRLManager2.a(Boolean.valueOf(true));
        uRLManager2.b(1);
        uRLManager2.a(BusinessObjectType.Albums);
        listingButton.a(uRLManager2);
        ListingParams listingParams2 = new ListingParams();
        listingParams2.a(SortOrder.TrackName);
        listingParams2.e(true);
        listingParams2.f(true);
        listingParams2.i(true);
        listingParams2.h(false);
        listingParams2.b(true);
        listingParams2.j(true);
        listingParams2.c(Util.a(uRLManager2));
        listingParams2.d(false);
        listingParams2.l(true);
        listingParams2.d((int) R.menu.filter_menu_favorites_items);
        listingParams2.k(false);
        listingParams2.b(PLAYOUT_SECTION_TYPE.MYMUSIC_ALBUMS.name());
        listingParams2.a(listingButton);
        listingParams2.a(String.valueOf(e.k));
        arrayList.add(listingParams2);
        listingButton = new ListingButton();
        listingButton.a(GaanaApplication.getContext().getString(R.string.downloads));
        listingButton.b(GaanaApplication.getContext().getString(R.string.downloads));
        listingButton.c(true);
        listingButton.c(DownloadAlbumItemView.class.getName());
        uRLManager2 = new URLManager();
        uRLManager2.a(BusinessObjectType.Albums);
        listingButton.a(uRLManager2);
        listingParams2 = new ListingParams();
        listingParams2.a(SortOrder.TrackName);
        listingParams2.e(true);
        listingParams2.f(true);
        listingParams2.h(true);
        listingParams2.i(true);
        listingParams2.b(true);
        listingParams2.c(Util.a(uRLManager2));
        listingParams2.j(true);
        listingParams2.d(false);
        listingParams2.l(true);
        listingParams2.d((int) R.menu.filter_menu_download_items);
        listingParams2.k(false);
        listingParams2.a(listingButton);
        listingParams2.a(String.valueOf(e.k));
        listingParams2.b(PLAYOUT_SECTION_TYPE.MYMUSIC_ALBUMS.name());
        arrayList.add(listingParams2);
        return arrayList;
    }

    public static URLManager a(String str, String str2, boolean z) {
        URLManager uRLManager;
        HashMap hashMap;
        StringBuilder stringBuilder;
        if ("song".equalsIgnoreCase(str)) {
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.Tracks);
            hashMap = new HashMap();
            hashMap.put("type", "song");
            hashMap.put(LoginManager.TAG_SUBTYPE, "song_detail");
            if (z) {
                hashMap.put("seokey", str2);
            } else {
                hashMap.put("track_id", str2);
            }
            uRLManager.a(hashMap);
            return uRLManager;
        } else if ("album".equalsIgnoreCase(str)) {
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.Albums);
            hashMap = new HashMap();
            hashMap.put("type", "album");
            hashMap.put(LoginManager.TAG_SUBTYPE, "album_detail_info");
            if (z) {
                hashMap.put("seokey", str2);
            } else {
                hashMap.put("album_id", str2);
            }
            uRLManager.a(hashMap);
            return uRLManager;
        } else if ("artist".equalsIgnoreCase(str)) {
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.Artists);
            hashMap = new HashMap();
            hashMap.put("type", "artist");
            hashMap.put(LoginManager.TAG_SUBTYPE, "artist_details_info");
            if (z) {
                hashMap.put("seokey", str2);
            } else {
                hashMap.put("artist_id", str2);
            }
            uRLManager.a(hashMap);
            return uRLManager;
        } else if ("playlist".equalsIgnoreCase(str)) {
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.Playlists);
            hashMap = new HashMap();
            hashMap.put("type", "playlist");
            hashMap.put(LoginManager.TAG_SUBTYPE, "playlist_detail_info");
            if (z) {
                hashMap.put("seokey", str2);
            } else {
                hashMap.put("playlist_id", str2);
            }
            uRLManager.a(hashMap);
            return uRLManager;
        } else if ("user".equalsIgnoreCase(str)) {
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.ProfileUsers);
            String str3 = "https://api.gaana.com/user.php?type=get_user_object";
            if (z) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str3);
                stringBuilder.append("&");
                stringBuilder.append("seokey");
                stringBuilder.append("=");
                stringBuilder.append(str2);
                str2 = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str3);
                stringBuilder.append("&user_id=");
                stringBuilder.append(str2);
                str2 = stringBuilder.toString();
            }
            uRLManager.a(str2);
            return uRLManager;
        } else if (!c.d.d.equalsIgnoreCase(str) && !c.d.c.equalsIgnoreCase(str)) {
            return null;
        } else {
            URLManager uRLManager2 = new URLManager();
            uRLManager2.a(BusinessObjectType.Radios);
            str = "https://api.gaana.com/radio.php?type=radio&subtype=get_radio_object&radio_type=<radio_type>".replace("<radio_type>", str);
            if (z) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("&");
                stringBuilder.append("seokey");
                stringBuilder.append("=");
                stringBuilder.append(str2);
                str = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("&radio_id=");
                stringBuilder.append(str2);
                str = stringBuilder.toString();
            }
            uRLManager2.a(str);
            return uRLManager2;
        }
    }

    public static Bitmap a(Context context, Uri uri) throws FileNotFoundException {
        Options options = new Options();
        int i = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        while (true) {
            i2 /= 2;
            if (i2 < 140) {
                break;
            }
            i3 /= 2;
            if (i3 < 140) {
                break;
            }
            i *= 2;
        }
        options = new Options();
        options.inSampleSize = i;
        return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
    }

    public static URLManager a(BusinessObjectType businessObjectType, String str, boolean z) {
        String str2 = "";
        if (businessObjectType == BusinessObjectType.Tracks) {
            str2 = "song";
        } else if (businessObjectType == BusinessObjectType.Albums) {
            str2 = "album";
        } else if (businessObjectType == BusinessObjectType.Artists) {
            str2 = "artist";
        } else if (businessObjectType == BusinessObjectType.Playlists) {
            str2 = "playlist";
        } else if (businessObjectType != BusinessObjectType.Radios && businessObjectType == BusinessObjectType.ProfileUsers) {
            str2 = "user";
        }
        return a(str2, str, z);
    }

    public static void a(String str, long j, String str2, String str3) {
        u.a().a(str, j, str2, str3);
    }

    public static void a(ProductItem productItem) {
        if (productItem != null && !TextUtils.isEmpty(productItem.getDuration_days()) && !TextUtils.isEmpty(productItem.getP_payment_mode())) {
            AppsFlyer.getInstance().trackEvent("purchase.gaana_plus.<mode>.<period>".replace("<mode>", productItem.getP_payment_mode()).replace("<period>", productItem.getDuration_days()), new HashMap());
        }
    }

    public static void a(UserInfo userInfo) {
        if (userInfo != null) {
            try {
                if (userInfo.getLoginStatus()) {
                    if (userInfo.getUserProfile() != null) {
                        String dob = userInfo.getUserProfile().getDob();
                        if (dob != null) {
                            ColombiaManager.b().b(dob);
                        }
                        String sex = userInfo.getUserProfile().getSex();
                        if (!TextUtils.isEmpty(sex)) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(sex.substring(0, 1).toUpperCase());
                            stringBuilder.append(sex.substring(1));
                            ColombiaManager.b().a(stringBuilder.toString());
                        }
                    }
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public static String a(String str) {
        if (str == null) {
            return str;
        }
        String[] split = eo.split(str);
        return split.length > 1 ? split[0] : str;
    }

    public static String b(String str) {
        return a(str, bV);
    }

    public static String a(String str, String str2) {
        if (str == null) {
            return str;
        }
        String[] split = eo.split(str);
        if (split.length <= 1) {
            return str;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = bV;
        }
        if (bV.equalsIgnoreCase(str2)) {
            return split[1];
        }
        return split[0];
    }

    public static boolean t() {
        boolean b = d.a().b("PREFERENCE_KEY_SETTINGS_AUTO_SYNC_V5", false, true);
        int i = cJ;
        long currentTimeMillis = System.currentTimeMillis() - d.a().b(0, "PREFERENCE_KEY_AUTO_SYNC_LAST_SHOWN", false);
        long j = cI;
        if (ap.a().o() && !b && currentTimeMillis >= j && DownloadManager.c().p() >= i) {
            return true;
        }
        U = false;
        return false;
    }
}
