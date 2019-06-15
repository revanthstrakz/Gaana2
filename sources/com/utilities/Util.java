package com.utilities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request.Priority;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.Constants;
import com.constants.Constants.VIEW_SIZE;
import com.constants.c.c;
import com.custom_card_response.CustomCard;
import com.dynamicview.DynamicViewManager;
import com.e.a.f;
import com.exoplayer2.VideoPlayerActivityTwo;
import com.facebook.AccessToken;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.fragments.BaseGaanaFragment;
import com.fragments.CreateNewPlaylistFragment;
import com.fragments.GaanaMiniSetupFragment;
import com.fragments.GaanaVideoPlayerFragment;
import com.fragments.ListingFragment;
import com.fragments.SettingsDetailFragment;
import com.gaana.BaseActivity;
import com.gaana.BuildConfig;
import com.gaana.GaanaActivity;
import com.gaana.Login;
import com.gaana.OnBoardLanguagePreferenceActivityNew;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.WebViewActivity.DeviceIdCallBack;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.cardoption.AssetsHelper.CARD;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukePlaylists;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.login.GooglePlusLogin;
import com.gaana.login.LoginInfo;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.UserInfo;
import com.gaana.models.Albums;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BasicResponse;
import com.gaana.models.BusinessObject;
import com.gaana.models.CampaignPromo;
import com.gaana.models.CountryData;
import com.gaana.models.DeleteHash;
import com.gaana.models.DiscoverTags.DiscoverTag;
import com.gaana.models.EntityInfo;
import com.gaana.models.FavoriteData;
import com.gaana.models.FavoriteOccasions;
import com.gaana.models.Friends;
import com.gaana.models.GaEventsConfig;
import com.gaana.models.GaEventsConfig.ABTesting;
import com.gaana.models.GaEventsConfig.CustomInApp;
import com.gaana.models.Genres;
import com.gaana.models.HomeAction;
import com.gaana.models.IssueBankHash;
import com.gaana.models.Item;
import com.gaana.models.Items;
import com.gaana.models.LyricsObject;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.Notifications;
import com.gaana.models.OfflineTrack;
import com.gaana.models.PayUHash;
import com.gaana.models.PaymentConfig;
import com.gaana.models.PaymentConfig.PaidTrial;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.models.PersonaDedications;
import com.gaana.models.PlaylistDetail;
import com.gaana.models.Playlists;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.PreScreens;
import com.gaana.models.PremiumContentTextConfig;
import com.gaana.models.Products;
import com.gaana.models.ProfileUsers;
import com.gaana.models.RadioMoods;
import com.gaana.models.Radios;
import com.gaana.models.Radios.Radio;
import com.gaana.models.SDKConfig;
import com.gaana.models.SDKConfig.SplashAdCode;
import com.gaana.models.SessionLoginInfo;
import com.gaana.models.SocialFeed;
import com.gaana.models.StreamUrls;
import com.gaana.models.StudentIdentityAction;
import com.gaana.models.SubscriptionCard;
import com.gaana.models.SubscriptionTrialCard;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.gaana.models.TrendingSearches;
import com.gaana.models.TrialProductFeature;
import com.gaana.models.TrialProductFeature.UserActionConfig;
import com.gaana.models.Uber;
import com.gaana.models.UpdateServerResponse;
import com.gaana.models.User;
import com.gaana.models.User.LoginMode;
import com.gaana.models.User.LoginType;
import com.gaana.models.UserActivities;
import com.gaana.models.UserAppSettings;
import com.gaana.models.UserJourneyFlagsData;
import com.gaana.revampeddetail.model.RevampedSimilarAlbumEntityInfo.GenericEntity;
import com.gaana.revampeddetail.model.RevampedSimilarAlbumEntityInfo.GenericEntityInfo;
import com.gaana.view.GaanaYourYearView.GAANA_ENTRY_PAGE;
import com.gaana.view.HomeSettingsItemView;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnCheckBoxDialogButtonClickListener;
import com.gaana.view.item.CustomMaterialDialogView;
import com.gaana.view.item.FreeUserTrialPopUpView;
import com.gaana.view.item.GaanaMiniPopupView;
import com.gaana.view.item.GoogleTrialPopUpView;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.gaana.view.item.PopupWindowView;
import com.gaana.view.item.PremiumContentPopUpView;
import com.gaana.view.item.RadioButtonGenericView;
import com.gaana.view.item.SearchItemView.SearchCategory;
import com.gaana.view.item.UserFeedbackDialog;
import com.gaanavideo.FullScreenVideoPlayerActivity;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.i.i;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.library.custom_glide.GlideApp;
import com.library.custom_glide.GlideFileLoader;
import com.library.helpers.Enums.ConnectionType;
import com.library.managers.TaskManager.TaskListner;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.PLAYOUT_SOURCE_NAME;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaManager;
import com.managers.DownloadManager;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ag;
import com.managers.ai;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.o;
import com.managers.q;
import com.managers.u;
import com.models.AppDetails;
import com.models.BankCodeList;
import com.models.CouponProducts;
import com.models.DeviceList;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.models.PlayerTrack;
import com.moengage.inapp.InAppMessage;
import com.payment.PaytmRenewal;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.services.d;
import com.services.h;
import com.services.j;
import com.services.l;
import com.services.l.aa;
import com.services.l.ad;
import com.services.l.af;
import com.services.l.as;
import com.services.l.au;
import com.services.l.aw;
import com.services.l.ba;
import com.services.l.m;
import com.services.l.s;
import com.services.l.y;
import com.services.n;
import com.til.colombia.android.internal.e;
import com.views.g;
import com.widget.GaanaWidgetProvider;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {
    private static com.services.l.b A = null;
    private static long B = 0;
    private static Context C = GaanaApplication.getInstance().getApplicationContext();
    private static String D = null;
    private static TypedArray E = null;
    private static long F = 0;
    private static final char[] G = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] H = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static boolean I = false;
    private static String J = "";
    private static String K = "";
    private static String L = "";
    private static ColorStateList M = null;
    public static int a = 0;
    static int b = -1;
    public static String c = "";
    public static JSONObject d = null;
    public static String e = null;
    public static Locale f = null;
    public static b g = null;
    private static Typeface h = null;
    private static Typeface i = null;
    private static Typeface j = null;
    private static Typeface k = null;
    private static Typeface l = null;
    private static Typeface m = null;
    private static Typeface n = null;
    private static Typeface o = null;
    private static Typeface p = null;
    private static Typeface q = null;
    private static Typeface r = null;
    private static Typeface s = null;
    private static Typeface t = null;
    private static ConnectivityManager u = null;
    private static ProgressDialog v = null;
    private static ConnectionType[] w = null;
    private static String x = null;
    private static boolean y = false;
    private static boolean z = false;

    public interface b {
        void initOnboardPlayer();
    }

    /* renamed from: com.utilities.Util$50 */
    static /* synthetic */ class AnonymousClass50 {
        static final /* synthetic */ int[] a = new int[FontFamily.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00b0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x01b0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:135:0x02b1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x012c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x0138 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:113:0x0234 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:115:0x0240 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00ba */
        /* JADX WARNING: Missing exception handler attribute for start block: B:137:0x02bb */
        /* JADX WARNING: Missing exception handler attribute for start block: B:93:0x01bc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:95:0x01c8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00c4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x0144 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:139:0x02c5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00ce */
        /* JADX WARNING: Missing exception handler attribute for start block: B:141:0x02cf */
        /* JADX WARNING: Missing exception handler attribute for start block: B:75:0x0150 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:117:0x024c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x00d8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:119:0x0258 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:143:0x02d9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:97:0x01d4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:99:0x01e0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x015c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:79:0x0168 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:145:0x02e3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x00e4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:121:0x0264 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x00f0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:123:0x0270 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:101:0x01ec */
        /* JADX WARNING: Missing exception handler attribute for start block: B:103:0x01f8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:81:0x0174 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0075 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:83:0x0180 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x00fc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:125:0x027c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x0108 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:127:0x0288 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:105:0x0204 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:107:0x0210 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:85:0x018c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:87:0x0198 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x0092 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x0114 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x0120 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:109:0x021c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00a6 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:133:0x02a7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:111:0x0228 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:89:0x01a4 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(138:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(138:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(138:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|127|128|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(136:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(136:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(136:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(136:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(135:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(135:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(135:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(134:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(134:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(134:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(134:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(134:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(133:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(133:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(133:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(133:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(133:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(132:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(132:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(132:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(132:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(132:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(131:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(131:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(131:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(131:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(131:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|25|26|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(130:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(130:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(130:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(130:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(130:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(130:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|29|30|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(129:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(129:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(129:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|145|146|148) */
        /* JADX WARNING: Can't wrap try/catch for region: R(127:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|(3:145|146|148)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(127:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|(3:145|146|148)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(127:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|(3:145|146|148)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(127:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|(3:145|146|148)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(127:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|(3:145|146|148)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(127:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|(3:145|146|148)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(127:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|35|36|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|(3:145|146|148)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(126:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|(2:35|36)|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|(3:145|146|148)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(126:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|(2:35|36)|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|(3:145|146|148)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(126:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|(2:21|22)|23|(2:25|26)|27|(2:29|30)|31|33|34|(2:35|36)|37|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|95|96|97|98|99|100|101|102|103|104|105|106|107|108|109|110|111|112|113|114|115|116|117|118|119|120|121|122|123|124|125|126|(2:127|128)|129|131|132|133|134|135|136|137|138|139|140|141|142|143|144|(3:145|146|148)) */
        static {
            /*
            r0 = com.gaana.view.item.SearchItemView.SearchCategory.values();
            r0 = r0.length;
            r0 = new int[r0];
            d = r0;
            r0 = 1;
            r1 = d;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = com.gaana.view.item.SearchItemView.SearchCategory.Artist;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r1 = 2;
            r2 = d;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = com.gaana.view.item.SearchItemView.SearchCategory.Radio;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = r3.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2[r3] = r1;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r2 = 3;
            r3 = d;	 Catch:{ NoSuchFieldError -> 0x002a }
            r4 = com.gaana.view.item.SearchItemView.SearchCategory.Album;	 Catch:{ NoSuchFieldError -> 0x002a }
            r4 = r4.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
            r3[r4] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
        L_0x002a:
            r3 = 4;
            r4 = d;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r5 = com.gaana.view.item.SearchItemView.SearchCategory.Playlist;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x0035 }
            r4[r5] = r3;	 Catch:{ NoSuchFieldError -> 0x0035 }
        L_0x0035:
            r4 = 5;
            r5 = d;	 Catch:{ NoSuchFieldError -> 0x0040 }
            r6 = com.gaana.view.item.SearchItemView.SearchCategory.Track;	 Catch:{ NoSuchFieldError -> 0x0040 }
            r6 = r6.ordinal();	 Catch:{ NoSuchFieldError -> 0x0040 }
            r5[r6] = r4;	 Catch:{ NoSuchFieldError -> 0x0040 }
        L_0x0040:
            r5 = 6;
            r6 = d;	 Catch:{ NoSuchFieldError -> 0x004b }
            r7 = com.gaana.view.item.SearchItemView.SearchCategory.video;	 Catch:{ NoSuchFieldError -> 0x004b }
            r7 = r7.ordinal();	 Catch:{ NoSuchFieldError -> 0x004b }
            r6[r7] = r5;	 Catch:{ NoSuchFieldError -> 0x004b }
        L_0x004b:
            r6 = 7;
            r7 = d;	 Catch:{ NoSuchFieldError -> 0x0056 }
            r8 = com.gaana.view.item.SearchItemView.SearchCategory.OfflineTrack;	 Catch:{ NoSuchFieldError -> 0x0056 }
            r8 = r8.ordinal();	 Catch:{ NoSuchFieldError -> 0x0056 }
            r7[r8] = r6;	 Catch:{ NoSuchFieldError -> 0x0056 }
        L_0x0056:
            r7 = 8;
            r8 = d;	 Catch:{ NoSuchFieldError -> 0x0062 }
            r9 = com.gaana.view.item.SearchItemView.SearchCategory.Occasion;	 Catch:{ NoSuchFieldError -> 0x0062 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0062 }
            r8[r9] = r7;	 Catch:{ NoSuchFieldError -> 0x0062 }
        L_0x0062:
            r8 = com.utilities.Util.NETWORK_TYPE.values();
            r8 = r8.length;
            r8 = new int[r8];
            c = r8;
            r8 = c;	 Catch:{ NoSuchFieldError -> 0x0075 }
            r9 = com.utilities.Util.NETWORK_TYPE.NETWORK_2G;	 Catch:{ NoSuchFieldError -> 0x0075 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0075 }
            r8[r9] = r0;	 Catch:{ NoSuchFieldError -> 0x0075 }
        L_0x0075:
            r8 = c;	 Catch:{ NoSuchFieldError -> 0x007f }
            r9 = com.utilities.Util.NETWORK_TYPE.NETWORK_WI_FI;	 Catch:{ NoSuchFieldError -> 0x007f }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x007f }
            r8[r9] = r1;	 Catch:{ NoSuchFieldError -> 0x007f }
        L_0x007f:
            r8 = com.managers.URLManager.BusinessObjectType.values();
            r8 = r8.length;
            r8 = new int[r8];
            b = r8;
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0092 }
            r9 = com.managers.URLManager.BusinessObjectType.Tracks;	 Catch:{ NoSuchFieldError -> 0x0092 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0092 }
            r8[r9] = r0;	 Catch:{ NoSuchFieldError -> 0x0092 }
        L_0x0092:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x009c }
            r9 = com.managers.URLManager.BusinessObjectType.Artists;	 Catch:{ NoSuchFieldError -> 0x009c }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x009c }
            r8[r9] = r1;	 Catch:{ NoSuchFieldError -> 0x009c }
        L_0x009c:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x00a6 }
            r9 = com.managers.URLManager.BusinessObjectType.Albums;	 Catch:{ NoSuchFieldError -> 0x00a6 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x00a6 }
            r8[r9] = r2;	 Catch:{ NoSuchFieldError -> 0x00a6 }
        L_0x00a6:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x00b0 }
            r9 = com.managers.URLManager.BusinessObjectType.Playlists;	 Catch:{ NoSuchFieldError -> 0x00b0 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x00b0 }
            r8[r9] = r3;	 Catch:{ NoSuchFieldError -> 0x00b0 }
        L_0x00b0:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x00ba }
            r9 = com.managers.URLManager.BusinessObjectType.Radios;	 Catch:{ NoSuchFieldError -> 0x00ba }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x00ba }
            r8[r9] = r4;	 Catch:{ NoSuchFieldError -> 0x00ba }
        L_0x00ba:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x00c4 }
            r9 = com.managers.URLManager.BusinessObjectType.Occasion;	 Catch:{ NoSuchFieldError -> 0x00c4 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x00c4 }
            r8[r9] = r5;	 Catch:{ NoSuchFieldError -> 0x00c4 }
        L_0x00c4:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x00ce }
            r9 = com.managers.URLManager.BusinessObjectType.GenericItems;	 Catch:{ NoSuchFieldError -> 0x00ce }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x00ce }
            r8[r9] = r6;	 Catch:{ NoSuchFieldError -> 0x00ce }
        L_0x00ce:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x00d8 }
            r9 = com.managers.URLManager.BusinessObjectType.Charts;	 Catch:{ NoSuchFieldError -> 0x00d8 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x00d8 }
            r8[r9] = r7;	 Catch:{ NoSuchFieldError -> 0x00d8 }
        L_0x00d8:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x00e4 }
            r9 = com.managers.URLManager.BusinessObjectType.TopCharts;	 Catch:{ NoSuchFieldError -> 0x00e4 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x00e4 }
            r10 = 9;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x00e4 }
        L_0x00e4:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x00f0 }
            r9 = com.managers.URLManager.BusinessObjectType.Products;	 Catch:{ NoSuchFieldError -> 0x00f0 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x00f0 }
            r10 = 10;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x00f0 }
        L_0x00f0:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x00fc }
            r9 = com.managers.URLManager.BusinessObjectType.Notifications;	 Catch:{ NoSuchFieldError -> 0x00fc }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x00fc }
            r10 = 11;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x00fc }
        L_0x00fc:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0108 }
            r9 = com.managers.URLManager.BusinessObjectType.CampaignPromo;	 Catch:{ NoSuchFieldError -> 0x0108 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0108 }
            r10 = 12;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0108 }
        L_0x0108:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0114 }
            r9 = com.managers.URLManager.BusinessObjectType.AppDetails;	 Catch:{ NoSuchFieldError -> 0x0114 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0114 }
            r10 = 13;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0114 }
        L_0x0114:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0120 }
            r9 = com.managers.URLManager.BusinessObjectType.TrendingSearches;	 Catch:{ NoSuchFieldError -> 0x0120 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0120 }
            r10 = 14;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0120 }
        L_0x0120:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x012c }
            r9 = com.managers.URLManager.BusinessObjectType.User;	 Catch:{ NoSuchFieldError -> 0x012c }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x012c }
            r10 = 15;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x012c }
        L_0x012c:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0138 }
            r9 = com.managers.URLManager.BusinessObjectType.Friends;	 Catch:{ NoSuchFieldError -> 0x0138 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0138 }
            r10 = 16;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0138 }
        L_0x0138:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0144 }
            r9 = com.managers.URLManager.BusinessObjectType.Geners;	 Catch:{ NoSuchFieldError -> 0x0144 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0144 }
            r10 = 17;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0144 }
        L_0x0144:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0150 }
            r9 = com.managers.URLManager.BusinessObjectType.Activities;	 Catch:{ NoSuchFieldError -> 0x0150 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0150 }
            r10 = 18;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0150 }
        L_0x0150:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x015c }
            r9 = com.managers.URLManager.BusinessObjectType.Discover;	 Catch:{ NoSuchFieldError -> 0x015c }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x015c }
            r10 = 19;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x015c }
        L_0x015c:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0168 }
            r9 = com.managers.URLManager.BusinessObjectType.ProfileUsers;	 Catch:{ NoSuchFieldError -> 0x0168 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0168 }
            r10 = 20;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0168 }
        L_0x0168:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0174 }
            r9 = com.managers.URLManager.BusinessObjectType.BasicResponse;	 Catch:{ NoSuchFieldError -> 0x0174 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0174 }
            r10 = 21;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0174 }
        L_0x0174:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0180 }
            r9 = com.managers.URLManager.BusinessObjectType.RadioMoods;	 Catch:{ NoSuchFieldError -> 0x0180 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0180 }
            r10 = 22;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0180 }
        L_0x0180:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x018c }
            r9 = com.managers.URLManager.BusinessObjectType.DynamicViews;	 Catch:{ NoSuchFieldError -> 0x018c }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x018c }
            r10 = 23;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x018c }
        L_0x018c:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0198 }
            r9 = com.managers.URLManager.BusinessObjectType.UberResponse;	 Catch:{ NoSuchFieldError -> 0x0198 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0198 }
            r10 = 24;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0198 }
        L_0x0198:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x01a4 }
            r9 = com.managers.URLManager.BusinessObjectType.SubscriptionTrialCard;	 Catch:{ NoSuchFieldError -> 0x01a4 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x01a4 }
            r10 = 25;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x01a4 }
        L_0x01a4:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x01b0 }
            r9 = com.managers.URLManager.BusinessObjectType.SubscriptionCard;	 Catch:{ NoSuchFieldError -> 0x01b0 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x01b0 }
            r10 = 26;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x01b0 }
        L_0x01b0:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x01bc }
            r9 = com.managers.URLManager.BusinessObjectType.FavoriteData;	 Catch:{ NoSuchFieldError -> 0x01bc }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x01bc }
            r10 = 27;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x01bc }
        L_0x01bc:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x01c8 }
            r9 = com.managers.URLManager.BusinessObjectType.PlaylistDetails;	 Catch:{ NoSuchFieldError -> 0x01c8 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x01c8 }
            r10 = 28;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x01c8 }
        L_0x01c8:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x01d4 }
            r9 = com.managers.URLManager.BusinessObjectType.PersonaDedications;	 Catch:{ NoSuchFieldError -> 0x01d4 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x01d4 }
            r10 = 29;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x01d4 }
        L_0x01d4:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x01e0 }
            r9 = com.managers.URLManager.BusinessObjectType.SocialFeed;	 Catch:{ NoSuchFieldError -> 0x01e0 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x01e0 }
            r10 = 30;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x01e0 }
        L_0x01e0:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x01ec }
            r9 = com.managers.URLManager.BusinessObjectType.HomeAction;	 Catch:{ NoSuchFieldError -> 0x01ec }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x01ec }
            r10 = 31;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x01ec }
        L_0x01ec:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x01f8 }
            r9 = com.managers.URLManager.BusinessObjectType.BankCodes;	 Catch:{ NoSuchFieldError -> 0x01f8 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x01f8 }
            r10 = 32;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x01f8 }
        L_0x01f8:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0204 }
            r9 = com.managers.URLManager.BusinessObjectType.CouponProducts;	 Catch:{ NoSuchFieldError -> 0x0204 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0204 }
            r10 = 33;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0204 }
        L_0x0204:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0210 }
            r9 = com.managers.URLManager.BusinessObjectType.TrialProductFeature;	 Catch:{ NoSuchFieldError -> 0x0210 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0210 }
            r10 = 34;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0210 }
        L_0x0210:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x021c }
            r9 = com.managers.URLManager.BusinessObjectType.CountryData;	 Catch:{ NoSuchFieldError -> 0x021c }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x021c }
            r10 = 35;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x021c }
        L_0x021c:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0228 }
            r9 = com.managers.URLManager.BusinessObjectType.DeviceList;	 Catch:{ NoSuchFieldError -> 0x0228 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0228 }
            r10 = 36;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0228 }
        L_0x0228:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0234 }
            r9 = com.managers.URLManager.BusinessObjectType.FavoriteOccasions;	 Catch:{ NoSuchFieldError -> 0x0234 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0234 }
            r10 = 37;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0234 }
        L_0x0234:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0240 }
            r9 = com.managers.URLManager.BusinessObjectType.PayUHashes;	 Catch:{ NoSuchFieldError -> 0x0240 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0240 }
            r10 = 38;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0240 }
        L_0x0240:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x024c }
            r9 = com.managers.URLManager.BusinessObjectType.DeleteHash;	 Catch:{ NoSuchFieldError -> 0x024c }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x024c }
            r10 = 39;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x024c }
        L_0x024c:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0258 }
            r9 = com.managers.URLManager.BusinessObjectType.IssueBankHash;	 Catch:{ NoSuchFieldError -> 0x0258 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0258 }
            r10 = 40;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0258 }
        L_0x0258:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0264 }
            r9 = com.managers.URLManager.BusinessObjectType.DynamicViewSections;	 Catch:{ NoSuchFieldError -> 0x0264 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0264 }
            r10 = 41;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0264 }
        L_0x0264:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0270 }
            r9 = com.managers.URLManager.BusinessObjectType.DynamicViewCategories;	 Catch:{ NoSuchFieldError -> 0x0270 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0270 }
            r10 = 42;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0270 }
        L_0x0270:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x027c }
            r9 = com.managers.URLManager.BusinessObjectType.JukePlaylist;	 Catch:{ NoSuchFieldError -> 0x027c }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x027c }
            r10 = 43;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x027c }
        L_0x027c:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0288 }
            r9 = com.managers.URLManager.BusinessObjectType.JukePlayLists;	 Catch:{ NoSuchFieldError -> 0x0288 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0288 }
            r10 = 44;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0288 }
        L_0x0288:
            r8 = b;	 Catch:{ NoSuchFieldError -> 0x0294 }
            r9 = com.managers.URLManager.BusinessObjectType.PreScreens;	 Catch:{ NoSuchFieldError -> 0x0294 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x0294 }
            r10 = 45;
            r8[r9] = r10;	 Catch:{ NoSuchFieldError -> 0x0294 }
        L_0x0294:
            r8 = com.utilities.Util.FontFamily.values();
            r8 = r8.length;
            r8 = new int[r8];
            a = r8;
            r8 = a;	 Catch:{ NoSuchFieldError -> 0x02a7 }
            r9 = com.utilities.Util.FontFamily.ARIAL;	 Catch:{ NoSuchFieldError -> 0x02a7 }
            r9 = r9.ordinal();	 Catch:{ NoSuchFieldError -> 0x02a7 }
            r8[r9] = r0;	 Catch:{ NoSuchFieldError -> 0x02a7 }
        L_0x02a7:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x02b1 }
            r8 = com.utilities.Util.FontFamily.GEORGIA;	 Catch:{ NoSuchFieldError -> 0x02b1 }
            r8 = r8.ordinal();	 Catch:{ NoSuchFieldError -> 0x02b1 }
            r0[r8] = r1;	 Catch:{ NoSuchFieldError -> 0x02b1 }
        L_0x02b1:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x02bb }
            r1 = com.utilities.Util.FontFamily.HELVETICA;	 Catch:{ NoSuchFieldError -> 0x02bb }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x02bb }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x02bb }
        L_0x02bb:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x02c5 }
            r1 = com.utilities.Util.FontFamily.LOTO;	 Catch:{ NoSuchFieldError -> 0x02c5 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x02c5 }
            r0[r1] = r3;	 Catch:{ NoSuchFieldError -> 0x02c5 }
        L_0x02c5:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x02cf }
            r1 = com.utilities.Util.FontFamily.PROXIMANOVA_REGULAR;	 Catch:{ NoSuchFieldError -> 0x02cf }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x02cf }
            r0[r1] = r4;	 Catch:{ NoSuchFieldError -> 0x02cf }
        L_0x02cf:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x02d9 }
            r1 = com.utilities.Util.FontFamily.PROXIMANOVA_EXTRA_BOLD;	 Catch:{ NoSuchFieldError -> 0x02d9 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x02d9 }
            r0[r1] = r5;	 Catch:{ NoSuchFieldError -> 0x02d9 }
        L_0x02d9:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x02e3 }
            r1 = com.utilities.Util.FontFamily.ROBOTO_REGULAR;	 Catch:{ NoSuchFieldError -> 0x02e3 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x02e3 }
            r0[r1] = r6;	 Catch:{ NoSuchFieldError -> 0x02e3 }
        L_0x02e3:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x02ed }
            r1 = com.utilities.Util.FontFamily.ROBOTO_EXTRA_BOLD;	 Catch:{ NoSuchFieldError -> 0x02ed }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x02ed }
            r0[r1] = r7;	 Catch:{ NoSuchFieldError -> 0x02ed }
        L_0x02ed:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.utilities.Util$AnonymousClass50.<clinit>():void");
        }
    }

    public enum BLOCK_ACTION {
        NONE,
        SHUFFLE,
        SKIP
    }

    public enum FontFamily {
        ARIAL,
        GEORGIA,
        HELVETICA,
        PROXIMANOVA_REGULAR,
        LOTO,
        PROXIMANOVA_EXTRA_BOLD,
        ROBOTO_REGULAR,
        ROBOTO_EXTRA_BOLD
    }

    public enum NETWORK_TYPE {
        NETWORK_2G,
        NETWORK_3G,
        NETWORK_4G,
        NETWORK_WI_FI,
        NETWORK_NO_CONNECTION,
        NETWORK_UNKNOWN
    }

    public interface a {
        void onErrorResponse(BusinessObject businessObject);

        void onRetreivalComplete(CustomCard customCard);
    }

    public static String a(int i) {
        return (i <= 0 || i > 15) ? (i <= 15 || i > 30) ? (i <= 30 || i > 45) ? (i <= 45 || i > 60) ? i > 60 ? "Above 60 sec" : "" : "60 Sec" : "45 Sec" : "30 Sec" : "15 Sec";
    }

    public static String f(String str) {
        return str;
    }

    public static Typeface a(Context context) {
        if (m == null) {
            m = Typeface.createFromAsset(context.getAssets(), "fonts/Gujarati.ttf");
        }
        return m;
    }

    public static Typeface b(Context context) {
        if (n == null) {
            n = Typeface.createFromAsset(context.getAssets(), "fonts/Malayalam.ttf");
        }
        return n;
    }

    public static Typeface c(Context context) {
        if (p == null) {
            p = Typeface.createFromAsset(context.getAssets(), "fonts/Punjabi.ttf");
        }
        return p;
    }

    public static Typeface d(Context context) {
        if (q == null) {
            q = Typeface.createFromAsset(context.getAssets(), "fonts/Kannada.ttf");
        }
        return q;
    }

    public static Typeface e(Context context) {
        if (r == null) {
            r = Typeface.createFromAsset(context.getAssets(), "fonts/Tamil.ttf");
        }
        return r;
    }

    public static Typeface f(Context context) {
        if (s == null) {
            s = Typeface.createFromAsset(context.getAssets(), "fonts/Telugu.ttf");
        }
        return s;
    }

    public static Typeface g(Context context) {
        if (t == null) {
            t = Typeface.createFromAsset(context.getAssets(), "fonts/Mangal.ttf");
        }
        return t;
    }

    public static Typeface h(Context context) {
        if (k == null) {
            k = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        }
        return k;
    }

    public static Typeface i(Context context) {
        if (k == null) {
            k = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
        }
        return k;
    }

    public static void a(Context context, String str, int i, TextView... textViewArr) {
        Typeface h;
        if (str.equalsIgnoreCase("english")) {
            h = h(context);
        } else if (str.equalsIgnoreCase("tamil")) {
            h = e(context);
        } else if (str.equalsIgnoreCase("telugu")) {
            h = f(context);
        } else if (str.equalsIgnoreCase("malayalam")) {
            h = b(context);
        } else if (str.equalsIgnoreCase("gujarati")) {
            h = a(context);
        } else if (str.equalsIgnoreCase("punjabi")) {
            h = c(context);
        } else if (str.equalsIgnoreCase("kannada")) {
            h = d(context);
        } else {
            h = g(context);
        }
        for (TextView typeface : textViewArr) {
            typeface.setTypeface(h, i);
        }
    }

    public static int a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static boolean j(Context context) {
        if (context == null) {
            return false;
        }
        if (u == null) {
            u = (ConnectivityManager) context.getSystemService("connectivity");
        }
        NetworkInfo activeNetworkInfo = u.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static int k(Context context) {
        if (u == null) {
            u = (ConnectivityManager) context.getSystemService("connectivity");
        }
        NetworkInfo activeNetworkInfo = u.getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getType() : -1;
    }

    public static String b(String str) {
        return Base64.encodeToString(str.getBytes(), 0).trim();
    }

    public static void a(View view, int i, int i2) {
        int i3 = 0;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            while (i3 < viewGroup.getChildCount()) {
                a(viewGroup.getChildAt(i3), i, i2);
                i3++;
            }
        } else if (view instanceof RadioButton) {
            if (view.getId() == i2) {
                ((RadioButton) view).setChecked(true);
            } else {
                ((RadioButton) view).setChecked(false);
            }
        } else if (!(view instanceof TextView)) {
        } else {
            if (view.getId() == i) {
                ((TextView) view).setSelected(true);
            } else {
                ((TextView) view).setSelected(false);
            }
        }
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("MMMM d, yyyy").format(new Date(j));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String l(Context context) {
        if (!TextUtils.isEmpty(x)) {
            return x;
        }
        m(context);
        return x;
    }

    public static int m(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Build.MODEL);
        stringBuilder.append("_");
        String stringBuilder2 = stringBuilder.toString();
        String string = Secure.getString(context.getContentResolver(), "android_id");
        if (TextUtils.isEmpty(string)) {
            string = d.a().c("PREFERENCE_KEY_DEVICE_ID", false);
            if (TextUtils.isEmpty(string)) {
                x = UUID.randomUUID().toString();
                d.a().a("PREFERENCE_KEY_DEVICE_ID", x, false);
                return 2;
            }
            x = string;
            return 2;
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append(string);
        x = stringBuilder3.toString();
        return 1;
    }

    public static String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Build.MANUFACTURER);
        stringBuilder.append(" ");
        stringBuilder.append(Build.MODEL);
        return stringBuilder.toString();
    }

    public static String c(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                String toHexString = Integer.toHexString(b & 255);
                while (toHexString.length() < 2) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("0");
                    stringBuilder2.append(toHexString);
                    toHexString = stringBuilder2.toString();
                }
                stringBuilder.append(toHexString);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    public static byte[] a(byte[] bArr) {
        return b(bArr).getBytes(Charset.forName("UTF-8"));
    }

    public static String b(byte[] bArr) {
        return new String(c(bArr));
    }

    public static char[] c(byte[] bArr) {
        return a(bArr, true);
    }

    public static char[] a(byte[] bArr, boolean z) {
        return a(bArr, z ? G : H);
    }

    protected static char[] a(byte[] bArr, char[] cArr) {
        int i = 0;
        int length = bArr.length;
        char[] cArr2 = new char[(length << 1)];
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            cArr2[i2] = cArr[(PsExtractor.VIDEO_STREAM_MASK & bArr[i]) >>> 4];
            i2 = i3 + 1;
            cArr2[i3] = cArr[15 & bArr[i]];
            i++;
        }
        return cArr2;
    }

    public static String a(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacMD5");
            Mac instance = Mac.getInstance("HmacMD5");
            instance.init(secretKeySpec);
            return new String(a(instance.doFinal(str.getBytes())), "UTF-8");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e2) {
            ThrowableExtension.printStackTrace(e2);
            return null;
        } catch (InvalidKeyException e3) {
            ThrowableExtension.printStackTrace(e3);
            return null;
        } catch (UnsupportedEncodingException e4) {
            ThrowableExtension.printStackTrace(e4);
            return null;
        }
    }

    public static void a(Context context, String str) {
        try {
            if (v == null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("\t\t\t\t\t");
                v = ProgressDialog.show(context, "", stringBuilder.toString(), true, false);
                v.setCancelable(true);
                v.setIndeterminate(false);
            } else if (!v.isShowing()) {
                v.show();
            }
        } catch (Exception unused) {
        }
    }

    public static void b() {
        try {
            if (v != null && v.isShowing()) {
                v.dismiss();
            }
        } catch (IllegalArgumentException unused) {
        }
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        int length = stringBuilder.length();
        Object obj = 1;
        for (int i = 0; i < length; i++) {
            char charAt = stringBuilder.charAt(i);
            if (obj != null) {
                if (!Character.isWhitespace(charAt)) {
                    stringBuilder.setCharAt(i, Character.toTitleCase(charAt));
                    obj = null;
                }
            } else if (Character.isWhitespace(charAt)) {
                obj = 1;
            } else {
                stringBuilder.setCharAt(i, Character.toLowerCase(charAt));
            }
        }
        return stringBuilder.toString();
    }

    public static String a(Context context, final EditText editText, ImageView imageView) {
        Calendar instance = Calendar.getInstance();
        final int i = instance.get(1);
        final int i2 = instance.get(2);
        final int i3 = instance.get(5);
        b(i3, i2, i, editText);
        final AnonymousClass1 anonymousClass1 = new OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Util.b(i3, i2, i, editText);
            }
        };
        final Context context2 = context;
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new DatePickerDialog(context2, anonymousClass1, i, i2, i3).show();
            }
        });
        return null;
    }

    public static String a(Context context, final TextView textView) {
        Calendar instance = Calendar.getInstance();
        final int i = instance.get(1);
        final int i2 = instance.get(2);
        final int i3 = instance.get(5);
        final AnonymousClass23 anonymousClass23 = new OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Util.b(i3, i2, i, textView);
            }
        };
        final Context context2 = context;
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new DatePickerDialog(context2, anonymousClass23, i, i2, i3).show();
            }
        });
        return null;
    }

    private static void b(int i, int i2, int i3, EditText editText) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append("/");
        stringBuilder.append(i2 + 1);
        stringBuilder.append("/");
        stringBuilder.append(i3);
        stringBuilder.append(" ");
        editText.setText(u(stringBuilder.toString()));
    }

    private static void b(int i, int i2, int i3, TextView textView) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i3);
        stringBuilder.append("/");
        stringBuilder.append(i2 + 1);
        stringBuilder.append("/");
        stringBuilder.append(i);
        stringBuilder.append(" ");
        textView.setText(v(stringBuilder.toString()));
    }

    private static String u(String str) {
        str = str.trim();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return new StringBuilder(simpleDateFormat.format(simpleDateFormat.parse(str))).toString();
        } catch (ParseException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    private static String v(String str) {
        str = str.trim();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return new StringBuilder(simpleDateFormat.format(simpleDateFormat.parse(str))).toString();
        } catch (ParseException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String a(String str, boolean z) {
        SimpleDateFormat simpleDateFormat;
        Date parse;
        str = str.trim();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        if (z) {
            simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        } else {
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        }
        try {
            parse = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            ThrowableExtension.printStackTrace(e);
            parse = null;
        }
        if (parse == null) {
            try {
                parse = simpleDateFormat2.parse(str);
            } catch (ParseException e2) {
                ThrowableExtension.printStackTrace(e2);
                return null;
            }
        }
        return new StringBuilder(simpleDateFormat2.format(parse)).toString();
    }

    public static int e(String str) {
        Date parse;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            parse = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            ThrowableExtension.printStackTrace(e);
            parse = null;
        }
        if (parse == null) {
            try {
                parse = simpleDateFormat2.parse(str);
            } catch (ParseException e2) {
                ThrowableExtension.printStackTrace(e2);
                return -1;
            }
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(System.currentTimeMillis()));
        if (parse.getTime() > instance.getTime().getTime()) {
            return -1;
        }
        instance.add(1, -13);
        if (parse.getTime() <= instance.getTime().getTime()) {
            return 1;
        }
        return 0;
    }

    public static void a(Context context, View view) {
        if (view != null) {
            try {
                Activity activity = (Activity) context;
                ((InputMethodManager) ((Activity) context).getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
            } catch (Exception unused) {
            }
        }
    }

    public static void b(Context context, View view) {
        if (view != null) {
            try {
                ((InputMethodManager) ((Activity) context).getSystemService("input_method")).showSoftInput(view, 1);
            } catch (Exception unused) {
            }
        }
    }

    public static ConnectionType[] n(Context context) {
        if (w == null) {
            o(context);
        }
        return w;
    }

    public static void o(Context context) {
        w = new ConnectionType[3];
        int b = d.a().b("PREFERENCE_KEY_DOWNLOAD_IMAGE", 1, true);
        if (b == 0) {
            w[0] = ConnectionType.WIFI;
            w[1] = ConnectionType.H_SPEED;
            w[2] = ConnectionType.L_SPEED;
        } else if (b == 1) {
            w[0] = ConnectionType.H_SPEED;
            w[1] = ConnectionType.WIFI;
            w[2] = null;
        } else if (b == 2) {
            w[0] = ConnectionType.WIFI;
            w[1] = null;
            w[2] = null;
        }
    }

    public static String p(Context context) {
        NETWORK_TYPE d = d();
        if (d == NETWORK_TYPE.NETWORK_WI_FI) {
            return "WIFI";
        }
        if (d == NETWORK_TYPE.NETWORK_4G) {
            return "4G";
        }
        if (d == NETWORK_TYPE.NETWORK_3G) {
            return "3G";
        }
        if (d == NETWORK_TYPE.NETWORK_2G) {
            return "2G";
        }
        return d == NETWORK_TYPE.NETWORK_NO_CONNECTION ? "noConnection" : "unknown";
    }

    public static boolean c() {
        return y;
    }

    public static void a(boolean z) {
        y = z;
    }

    public static boolean q(Context context) {
        try {
            if ((context.getPackageManager().getPackageInfo(BuildConfig.APPLICATION_ID, 0).applicationInfo.flags & 1) != 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
        }
    }

    public static String r(Context context) {
        d a = d.a();
        int b = a.b("PREFERENCE_KEY_STREAMING_QUALITY", Constants.s(), false);
        if (!ap.a().s() && b == 10003) {
            b = 10002;
            a.a("PREFERENCE_KEY_STREAMING_QUALITY", 10004, false);
            a.a("PREFERENCE_KEY_STREAMING_QUALITY_SELECTED", 10004, false);
        }
        String str = "-1";
        switch (b) {
            case 10000:
                str = "normal";
                break;
            case 10001:
                str = "medium";
                break;
            case 10002:
                str = "high";
                break;
            case 10003:
                str = "extreme";
                break;
            case 10004:
                str = "auto";
                break;
        }
        o(str);
        return str;
    }

    public static NETWORK_TYPE d() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) GaanaApplication.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return NETWORK_TYPE.NETWORK_NO_CONNECTION;
        }
        if (activeNetworkInfo.getType() == 1) {
            return NETWORK_TYPE.NETWORK_WI_FI;
        }
        if (activeNetworkInfo.getType() != 0) {
            return NETWORK_TYPE.NETWORK_UNKNOWN;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return NETWORK_TYPE.NETWORK_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return NETWORK_TYPE.NETWORK_3G;
            case 13:
                return NETWORK_TYPE.NETWORK_4G;
            default:
                return NETWORK_TYPE.NETWORK_UNKNOWN;
        }
    }

    public static void e() {
        Context instance = GaanaApplication.getInstance();
        d a = d.a();
        JSONObject jSONObject;
        if (a.c("DEVICE_HARDWARE_JSON", false) == null) {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("brand", Build.BRAND);
                jSONObject.put("model", Build.MODEL);
                jSONObject.put("devID", l(instance));
                if (VERSION.SDK_INT >= 23) {
                    jSONObject.put("simTy", s(instance));
                }
                jSONObject.put("netTy", f());
                jSONObject.put("conTy", p(instance));
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(VERSION.SDK_INT);
                jSONObject.put("osVer", stringBuilder.toString());
                HashMap g = g();
                jSONObject.put("cpu", g.get("cpuVendor") != null ? g.get("cpuVendor") : CARD.UNKNOWN);
                jSONObject.put("cpuBit", g.get("cpuArch") != null ? g.get("cpuArch") : CARD.UNKNOWN);
                jSONObject.put("cpuFre", g.get("cpuFreq") != null ? g.get("cpuFreq") : CARD.UNKNOWN);
                jSONObject.put("cpuCor", g.get("cpuNum") != null ? g.get("cpuNum") : CARD.UNKNOWN);
                stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(instance.getResources().getDisplayMetrics().densityDpi);
                jSONObject.put("scrDen", stringBuilder.toString());
                jSONObject.put("scrRes", k());
                jSONObject.put("batt", m());
                jSONObject.put("ram", h());
                jSONObject.put("intSto", n());
                jSONObject.put("sdSlot", r() ? "YES" : "NO");
                jSONObject.put("sdMem", p());
                jSONObject.put("IDFA", o());
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
            a.a("DEVICE_HARDWARE_JSON", jSONObject.toString(), false);
            if (j(instance)) {
                b(jSONObject.toString(), false);
                return;
            }
            return;
        }
        try {
            JSONObject jSONObject2 = new JSONObject(a.c("DEVICE_HARDWARE_JSON", false));
            jSONObject = new JSONObject();
            if (jSONObject2.has("netTy") && !jSONObject2.getString("netTy").equalsIgnoreCase(f())) {
                jSONObject.put("netTy", f());
                jSONObject2.put("netTy", f());
            }
            if (jSONObject2.has("conTy") && !jSONObject2.getString("conTy").equalsIgnoreCase(p(instance))) {
                jSONObject.put("conTy", p(instance));
                jSONObject2.put("conTy", p(instance));
            }
            if (jSONObject2.has("osVer") && jSONObject2.getInt("osVer") != VERSION.SDK_INT) {
                jSONObject.put("osVer", VERSION.SDK_INT);
                jSONObject2.put("osVer", VERSION.SDK_INT);
            }
            if (jSONObject2.has("sdSlot")) {
                if (!jSONObject2.getString("sdSlot").equalsIgnoreCase(r() ? "YES" : "NO")) {
                    jSONObject.put("sdSlot", r() ? "YES" : "NO");
                    jSONObject2.put("sdSlot", r() ? "YES" : "NO");
                }
            }
            if (jSONObject2.has("sdMem") && !jSONObject2.getString("sdMem").equalsIgnoreCase(p())) {
                jSONObject.put("sdMem", p());
                jSONObject2.put("sdMem", p());
            }
            if (!jSONObject2.has("IDFA") || (jSONObject2.has("IDFA") && !jSONObject2.getString("IDFA").equalsIgnoreCase(o()))) {
                jSONObject.put("IDFA", o());
                jSONObject2.put("IDFA", o());
            }
            if (jSONObject.length() >= 1 && j(instance)) {
                a.a("DEVICE_HARDWARE_JSON", jSONObject2.toString(), false);
                if (a.b("HARDWARE_JSON_POSTED", false, false)) {
                    b(jSONObject.toString(), true);
                } else {
                    b(jSONObject2.toString(), false);
                }
            } else if (!a.b("HARDWARE_JSON_POSTED", false, false) && j(instance)) {
                b(jSONObject2.toString(), false);
            }
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
        }
    }

    public static void b(String str, boolean z) {
        try {
            byte[] c = new k(Constants.bw).c(str);
            HashMap hashMap = new HashMap();
            hashMap.put("device_settings", k.a(c));
            if (Constants.b) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Encrypted:\n");
                stringBuilder.append(k.a(c));
                stringBuilder.append("\nActual:\n");
                stringBuilder.append(str);
                Log.d("HardwareJSON", stringBuilder.toString());
            }
            str = "https://logs.gaana.com/user/insert/device-settings";
            if (z) {
                str = "https://logs.gaana.com/user/update/device-settings";
            }
            URLManager uRLManager = new URLManager();
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.i(false);
            uRLManager.a(str);
            uRLManager.a(UpdateServerResponse.class);
            uRLManager.c(1);
            uRLManager.a(hashMap);
            i.a().a(new af() {
                public void onRetreivalComplete(Object obj) {
                    if (((UpdateServerResponse) obj).getStatus() == 1) {
                        d.a().a("HARDWARE_JSON_POSTED", true, false);
                    } else {
                        d.a().a("HARDWARE_JSON_POSTED", false, false);
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    d.a().a("HARDWARE_JSON_POSTED", false, false);
                }
            }, uRLManager);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static String f() {
        int networkType;
        try {
            networkType = ((TelephonyManager) GaanaApplication.getContext().getSystemService("phone")).getNetworkType();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            networkType = 0;
        }
        switch (networkType) {
            case 0:
                return CARD.UNKNOWN;
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA";
            case 5:
                return "EVDO_0";
            case 6:
                return "EVDO_A";
            case 7:
                return "1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "IDEN";
            case 12:
                return "EVDO_B";
            case 13:
                return "LTE";
            case 14:
                return "EHRPD";
            case 15:
                return "HSPAP";
            case 16:
                return "GSM";
            case 17:
                return "TD_SCDMA";
            case 18:
                return "IWLAN";
            default:
                return CARD.UNKNOWN;
        }
    }

    public static HashMap<String, String> g() {
        HashMap hashMap = new HashMap();
        if (new File("/proc/cpuinfo").exists()) {
            try {
                String readLine;
                StringBuilder stringBuilder;
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/cpuinfo")));
                int i = 0;
                while (true) {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (readLine.contains("Processor\t:")) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(Build.CPU_ABI);
                        stringBuilder.append(" ");
                        stringBuilder.append(readLine.split("\t: ")[1]);
                        hashMap.put("cpuArch", stringBuilder.toString());
                    } else if (readLine.contains("Hardware\t:")) {
                        hashMap.put("cpuVendor", readLine.split("\t: ")[1]);
                    } else if (readLine.contains("processor\t:")) {
                        i++;
                    }
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("");
                stringBuilder2.append(i);
                hashMap.put("cpuNum", stringBuilder2.toString());
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                readLine = "";
                for (int i2 = 0; i2 < i; i2++) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("/sys/devices/system/cpu/cpu");
                    stringBuilder.append(i2);
                    stringBuilder.append("/cpufreq/cpuinfo_max_freq");
                    RandomAccessFile randomAccessFile = new RandomAccessFile(stringBuilder.toString(), e.o);
                    String readLine2 = randomAccessFile.readLine();
                    if (readLine2 != null) {
                        float parseFloat = Float.parseFloat(readLine2) / 1000000.0f;
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(readLine);
                        stringBuilder3.append(parseFloat);
                        stringBuilder3.append("Ghz ");
                        readLine = stringBuilder3.toString();
                    }
                    randomAccessFile.close();
                }
                hashMap.put("cpuFreq", readLine.substring(0, readLine.length() - 1));
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00aa A:{SYNTHETIC, Splitter:B:41:0x00aa} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b3 A:{SYNTHETIC, Splitter:B:47:0x00b3} */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b8 A:{SYNTHETIC, Splitter:B:51:0x00b8} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00aa A:{SYNTHETIC, Splitter:B:41:0x00aa} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b3 A:{SYNTHETIC, Splitter:B:47:0x00b3} */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b8 A:{SYNTHETIC, Splitter:B:51:0x00b8} */
    /* JADX WARNING: Missing block: B:25:0x008a, code skipped:
            if (r2 == null) goto L_0x00bc;
     */
    /* JADX WARNING: Missing block: B:28:?, code skipped:
            r2.close();
     */
    /* JADX WARNING: Missing block: B:43:0x00ad, code skipped:
            if (r2 == null) goto L_0x00bc;
     */
    public static java.lang.String h() {
        /*
        r0 = c;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x000b;
    L_0x0008:
        r0 = c;
        return r0;
    L_0x000b:
        r0 = "UNKNOWN";
        c = r0;
        r0 = C;
        if (r0 == 0) goto L_0x0045;
    L_0x0013:
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 15;
        if (r0 <= r1) goto L_0x0045;
    L_0x0019:
        r0 = new android.app.ActivityManager$MemoryInfo;
        r0.<init>();
        r1 = C;
        r2 = "activity";
        r1 = r1.getSystemService(r2);
        r1 = (android.app.ActivityManager) r1;
        r1.getMemoryInfo(r0);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r0.totalMem;
        r4 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r2 = r2 / r4;
        r1.append(r2);
        r0 = "kB";
        r1.append(r0);
        r0 = r1.toString();
        c = r0;
        goto L_0x00bc;
    L_0x0045:
        r0 = new java.io.File;
        r1 = "/proc/meminfo";
        r0.<init>(r1);
        r0 = r0.exists();
        if (r0 == 0) goto L_0x00bc;
    L_0x0052:
        r0 = 0;
        r1 = new java.io.FileReader;	 Catch:{ Exception -> 0x00a1, all -> 0x009c }
        r2 = new java.io.File;	 Catch:{ Exception -> 0x00a1, all -> 0x009c }
        r3 = "/proc/meminfo";
        r2.<init>(r3);	 Catch:{ Exception -> 0x00a1, all -> 0x009c }
        r1.<init>(r2);	 Catch:{ Exception -> 0x00a1, all -> 0x009c }
        r2 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0097, all -> 0x0092 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x0097, all -> 0x0092 }
    L_0x0064:
        r0 = r2.readLine();	 Catch:{ Exception -> 0x0090 }
        if (r0 == 0) goto L_0x0085;
    L_0x006a:
        r3 = "MemTotal:";
        r3 = r0.contains(r3);	 Catch:{ Exception -> 0x0090 }
        if (r3 == 0) goto L_0x0064;
    L_0x0072:
        r3 = ":";
        r0 = r0.split(r3);	 Catch:{ Exception -> 0x0090 }
        r3 = 1;
        r0 = r0[r3];	 Catch:{ Exception -> 0x0090 }
        r3 = " ";
        r4 = "";
        r0 = r0.replace(r3, r4);	 Catch:{ Exception -> 0x0090 }
        c = r0;	 Catch:{ Exception -> 0x0090 }
    L_0x0085:
        if (r1 == 0) goto L_0x008a;
    L_0x0087:
        r1.close();	 Catch:{ IOException -> 0x008a }
    L_0x008a:
        if (r2 == 0) goto L_0x00bc;
    L_0x008c:
        r2.close();	 Catch:{ IOException -> 0x00bc }
        goto L_0x00bc;
    L_0x0090:
        r0 = move-exception;
        goto L_0x00a5;
    L_0x0092:
        r2 = move-exception;
        r6 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x00b1;
    L_0x0097:
        r2 = move-exception;
        r6 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x00a5;
    L_0x009c:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        r1 = r2;
        goto L_0x00b1;
    L_0x00a1:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        r1 = r2;
    L_0x00a5:
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r0);	 Catch:{ all -> 0x00b0 }
        if (r1 == 0) goto L_0x00ad;
    L_0x00aa:
        r1.close();	 Catch:{ IOException -> 0x00ad }
    L_0x00ad:
        if (r2 == 0) goto L_0x00bc;
    L_0x00af:
        goto L_0x008c;
    L_0x00b0:
        r0 = move-exception;
    L_0x00b1:
        if (r1 == 0) goto L_0x00b6;
    L_0x00b3:
        r1.close();	 Catch:{ IOException -> 0x00b6 }
    L_0x00b6:
        if (r2 == 0) goto L_0x00bb;
    L_0x00b8:
        r2.close();	 Catch:{ IOException -> 0x00bb }
    L_0x00bb:
        throw r0;
    L_0x00bc:
        r0 = c;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.utilities.Util.h():java.lang.String");
    }

    @SuppressLint({"NewApi"})
    public static int i() {
        if (C == null || VERSION.SDK_INT <= 15) {
            return 512;
        }
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) C.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return Math.round((float) (memoryInfo.totalMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED));
    }

    public static boolean j() {
        return Constants.cO - i() >= 0;
    }

    public static String k() {
        Display defaultDisplay = ((WindowManager) GaanaApplication.getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(displayMetrics.heightPixels);
        stringBuilder.append(AvidJSONUtil.KEY_X);
        stringBuilder.append(displayMetrics.widthPixels);
        return stringBuilder.toString();
    }

    public static String l() {
        Display defaultDisplay = ((WindowManager) GaanaApplication.getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (VERSION.SDK_INT < 23) {
            return k();
        }
        int physicalWidth = defaultDisplay.getMode().getPhysicalWidth();
        int physicalHeight = defaultDisplay.getMode().getPhysicalHeight();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(physicalHeight);
        stringBuilder.append(AvidJSONUtil.KEY_X);
        stringBuilder.append(physicalWidth);
        return stringBuilder.toString();
    }

    public static String m() {
        double doubleValue;
        try {
            doubleValue = ((Double) Class.forName("com.android.internal.os.PowerProfile").getMethod("getBatteryCapacity", new Class[0]).invoke(Class.forName("com.android.internal.os.PowerProfile").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{GaanaApplication.getContext()}), new Object[0])).doubleValue();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            doubleValue = 0.0d;
        }
        if (doubleValue == 0.0d) {
            return "unknown";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(doubleValue);
        stringBuilder.append(" mAh");
        return stringBuilder.toString();
    }

    @TargetApi(23)
    public static String s(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(telephonyManager.getPhoneCount());
        return stringBuilder.toString();
    }

    public static String n() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (VERSION.SDK_INT >= 18) {
            return c(statFs.getBlockCountLong() * statFs.getBlockSizeLong());
        }
        return c(((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
    }

    public static String o() {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(C);
            return advertisingIdInfo != null ? advertisingIdInfo.getId() : "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String p() {
        if (!r()) {
            return "No SD Card";
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        if (VERSION.SDK_INT >= 18) {
            return c(statFs.getBlockCountLong() * statFs.getBlockSizeLong());
        }
        return c(((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
    }

    public static long q() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (VERSION.SDK_INT >= 18) {
            return b(statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
        }
        return b(((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()));
    }

    public static boolean r() {
        return Boolean.valueOf(Environment.getExternalStorageState().equals("mounted")).booleanValue() && (Boolean.valueOf(Environment.isExternalStorageRemovable()).booleanValue() || ContextCompat.getExternalFilesDirs(GaanaApplication.getContext(), null).length > 1);
    }

    public static long b(long j) {
        if (j < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return j;
        }
        j /= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        return j >= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID ? j / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID : j;
    }

    public static String c(long j) {
        String str;
        if (j >= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            str = "KB";
            j /= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            if (j >= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
                str = "MB";
                j /= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                if (j >= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
                    str = "GB";
                    j /= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                }
            }
        } else {
            str = null;
        }
        StringBuilder stringBuilder = new StringBuilder(Long.toString(j));
        if (str != null) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public static void a(Context context, String str, @Nullable Track track) {
        if (d.a().b("PREFERENCE_DOES_WIDGET_EXIST", true, false)) {
            Intent intent = new Intent(context, GaanaWidgetProvider.class);
            intent.setAction(str);
            if (track != null) {
                intent.putExtra("currentTrack", track);
            }
            intent.putExtra("appWidgetIds", new int[]{R.xml.gaana_widget_player});
            context.sendBroadcast(intent);
        }
    }

    public static void g(String str) {
        D = str;
    }

    public static String s() {
        return D;
    }

    public static String t() {
        String str = "";
        try {
            return Integer.toString(GaanaApplication.getContext().getPackageManager().getPackageInfo(GaanaApplication.getContext().getPackageName(), 0).versionCode);
        } catch (NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return str;
        }
    }

    public static String u() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(7);
        if (i > 0) {
            i--;
        }
        int i2 = instance.get(10);
        int i3 = instance.get(12);
        int i4 = instance.get(9);
        int i5 = instance.get(5);
        int i6 = instance.get(2) + 1;
        int i7 = instance.get(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i7);
        stringBuilder.append("/");
        stringBuilder.append(i6);
        stringBuilder.append("/");
        stringBuilder.append(i5);
        String stringBuilder2 = stringBuilder.toString();
        if (i4 == 1 && i2 < 12) {
            i2 += 12;
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(i);
        stringBuilder3.append("-");
        stringBuilder3.append(i2);
        stringBuilder3.append(":");
        stringBuilder3.append(i3);
        stringBuilder3.append("-");
        stringBuilder3.append(stringBuilder2);
        return stringBuilder3.toString();
    }

    public static boolean a(BusinessObject businessObject) {
        if ((businessObject instanceof Track) && ((Track) businessObject).isFreeDownloadEnabled()) {
            return true;
        }
        if ((businessObject instanceof OfflineTrack) && ((OfflineTrack) businessObject).isFreeDownload() == 1) {
            return true;
        }
        if (businessObject instanceof Item) {
            Item item = (Item) businessObject;
            if (item.getEntityType().equalsIgnoreCase(c.c) && ((Track) g(item)).isFreeDownloadEnabled()) {
                return true;
            }
        }
        return false;
    }

    public static boolean v() {
        return (GaanaApplication.getInstance().getCurrentUser() == null || !GaanaApplication.getInstance().getCurrentUser().getLoginStatus() || ap.a().d()) ? false : true;
    }

    public static boolean b(BusinessObject businessObject) {
        return v() && a(businessObject);
    }

    public static void a(final Context context, final as asVar) {
        if (!j(context) || GaanaApplication.getInstance().isAppInOfflineMode()) {
            ap.a().f(context);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/gaanaplusservice_nxtgen.php?type=get_gtrial&source=expired_downloads&no_downloads=");
        stringBuilder.append(DownloadManager.c().p());
        String stringBuilder2 = stringBuilder.toString();
        URLManager uRLManager = new URLManager();
        uRLManager.a(stringBuilder2);
        uRLManager.b(1);
        uRLManager.a(BusinessObjectType.TrialProductFeature);
        uRLManager.i(false);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if ((context instanceof Activity) && !((Activity) context).isFinishing() && (obj instanceof TrialProductFeature)) {
                    TrialProductFeature trialProductFeature = (TrialProductFeature) obj;
                    if (TextUtils.isEmpty(trialProductFeature.getCard_identifier())) {
                        u.a().b("A/B Testing", "Generic");
                    } else {
                        u.a().b("A/B Testing", trialProductFeature.getCard_identifier());
                    }
                    trialProductFeature.getMessage_text();
                    String str = "";
                    if (trialProductFeature.getIs_card() == 1) {
                        GoogleTrialPopUpView googleTrialPopUpView = new GoogleTrialPopUpView(context, trialProductFeature, asVar);
                        googleTrialPopUpView.setSourceType(str);
                        googleTrialPopUpView.show();
                        if (str != null && str.equals("Free_trial")) {
                            u.a().a("Skip Count", "30 days Pop Up", "Default Plan_30 Days Trial_Fail");
                        }
                    }
                }
            }
        }, uRLManager);
    }

    public static void a(Context context, BLOCK_ACTION block_action) {
        if (j(context)) {
            a(context, true, block_action);
        } else {
            ap.a().f(context);
        }
    }

    public static void a(Context context, String str, String str2) {
        a(context, str, str2, null);
    }

    public static void a(Context context, String str, String str2, String str3, as asVar) {
        z = false;
        if (str3.equalsIgnoreCase("Trial_card") && Constants.az) {
            a(context, str3, asVar);
            return;
        }
        if (str3.equalsIgnoreCase("Trial_card")) {
            z = true;
        }
        a(context, str, str2, str3, asVar, true);
    }

    public static void a(Context context, String str, String str2, as asVar) {
        z = false;
        if (str2.equalsIgnoreCase("Trial_card") && Constants.az) {
            a(context, str2, asVar);
            return;
        }
        if (str2.equalsIgnoreCase("Trial_card")) {
            z = true;
        }
        b(context, str, str2, asVar, true);
    }

    private static boolean b(Context context, String str, String str2, as asVar, boolean z) {
        return a(context, "", str, str2, asVar, z);
    }

    private static boolean a(Context context, String str, String str2, String str3, as asVar, boolean z) {
        StringBuilder stringBuilder;
        String str4 = "";
        if (!TextUtils.isEmpty(str)) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("&user_type=freedom&source=bottom_sheet&sub_source=");
            stringBuilder2.append(str);
            str4 = stringBuilder2.toString();
        }
        if (z) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str4);
            stringBuilder.append("&source=gaanaplus_card");
            str4 = stringBuilder.toString();
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/gaanaplusservice_nxtgen.php?type=get_gtrial&no_downloads=");
        stringBuilder.append(DownloadManager.c().B());
        stringBuilder.append(DownloadManager.c().K());
        if (TextUtils.isEmpty(str4)) {
            str4 = "";
        }
        stringBuilder.append(str4);
        stringBuilder.append(ag.a(C).d());
        str = stringBuilder.toString();
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.b(1);
        uRLManager.a(BusinessObjectType.TrialProductFeature);
        uRLManager.i(false);
        uRLManager.b(Boolean.valueOf(false));
        final Context context2 = context;
        final as asVar2 = asVar;
        final String str5 = str3;
        final String str6 = str2;
        final boolean z2 = z;
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if ((context2 instanceof Activity) && !((Activity) context2).isFinishing() && (obj instanceof TrialProductFeature)) {
                    TrialProductFeature trialProductFeature = (TrialProductFeature) obj;
                    if (TextUtils.isEmpty(trialProductFeature.getCard_identifier())) {
                        u.a().b("A/B Testing", "Generic");
                    } else {
                        u.a().b("A/B Testing", trialProductFeature.getCard_identifier());
                    }
                    if (Util.z) {
                        if (trialProductFeature.getPg_product() == null || trialProductFeature.getPg_product().getP_id() == null) {
                            u.a().a("GaanaPlus Card", "Click", "");
                        } else {
                            u.a().a("GaanaPlus Card", "Click", trialProductFeature.getPg_product().getP_id());
                        }
                    }
                    if (trialProductFeature.getIs_card() == 1) {
                        GoogleTrialPopUpView googleTrialPopUpView = new GoogleTrialPopUpView(context2, trialProductFeature, asVar2);
                        googleTrialPopUpView.setSourceType(str5);
                        googleTrialPopUpView.show();
                        if (str5 != null && str5.equals("Free_trial")) {
                            u.a().a("Skip Count", "30 days Pop Up", "Default Plan_30 Days Trial_Fail");
                        }
                        Util.z = false;
                        return;
                    }
                    Util.c(context2, str6, str5, asVar2, z2);
                }
            }
        }, uRLManager);
        return false;
    }

    public static boolean a(final Context context, final boolean z, final BLOCK_ACTION block_action) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/gaanaplusservice_nxtgen.php?type=get_gtrial&no_downloads=");
        stringBuilder.append(DownloadManager.c().B());
        stringBuilder.append(DownloadManager.c().K());
        stringBuilder.append(ag.a(C).d());
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3;
        if (block_action == BLOCK_ACTION.SHUFFLE) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append("&action=shuffle");
            stringBuilder2 = stringBuilder3.toString();
        } else if (block_action == BLOCK_ACTION.SKIP) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append("&action=skip");
            stringBuilder2 = stringBuilder3.toString();
        }
        URLManager uRLManager = new URLManager();
        uRLManager.a(stringBuilder2);
        uRLManager.b(1);
        uRLManager.a(BusinessObjectType.TrialProductFeature);
        uRLManager.i(false);
        uRLManager.c(Boolean.valueOf(j(context)));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj instanceof TrialProductFeature) {
                    TrialProductFeature trialProductFeature = (TrialProductFeature) obj;
                    ArrayList userActionConfig = trialProductFeature.getUserActionConfig();
                    if (!(userActionConfig == null || userActionConfig.isEmpty())) {
                        Iterator it = userActionConfig.iterator();
                        while (it.hasNext()) {
                            UserActionConfig userActionConfig2 = (UserActionConfig) it.next();
                            if (userActionConfig2.getActionType().equals("shuffle")) {
                                if (userActionConfig2.isActive()) {
                                    if (!z) {
                                        Constants.g = Integer.valueOf(userActionConfig2.getMaxLimit()).intValue();
                                    }
                                    Constants.ab = true;
                                } else {
                                    Constants.ab = false;
                                }
                                d.a().a(Constants.ac, Constants.ab, true);
                            }
                            if (userActionConfig2.getActionType().equals("skip")) {
                                if (userActionConfig2.isActive()) {
                                    if (!z) {
                                        Constants.h = Integer.valueOf(userActionConfig2.getMaxLimit()).intValue();
                                    }
                                    Constants.aa = true;
                                } else {
                                    Constants.aa = false;
                                }
                                d.a().a(Constants.ad, Constants.aa, true);
                            }
                        }
                    }
                    if (z) {
                        if (TextUtils.isEmpty(trialProductFeature.getCard_identifier())) {
                            u.a().b("A/B Testing", "Generic");
                        } else {
                            u.a().b("A/B Testing", trialProductFeature.getCard_identifier());
                        }
                        if (GaanaApplication.getInstance().getCurrentUser() == null || !GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                            new FreeUserTrialPopUpView(context, trialProductFeature, block_action).show();
                        } else {
                            new GoogleTrialPopUpView(context, trialProductFeature, block_action).show();
                        }
                    }
                }
            }
        }, uRLManager);
        return false;
    }

    public static void a(final Context context, final String str, final as asVar) {
        LoginManager.getInstance().checkTrialAvailability(context, new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject instanceof BasicResponse) {
                    BasicResponse basicResponse = (BasicResponse) businessObject;
                    if (basicResponse.getResult() != null && basicResponse.getResult().equalsIgnoreCase("Yes")) {
                        ag.a(context).a(context, str, new com.managers.ag.a() {
                            public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                                ((BaseActivity) context).updateUserStatus(new au() {
                                    public void onUserStatusUpdated() {
                                        ((BaseActivity) context).hideProgressDialog();
                                        ap.a().a(context);
                                        Util.aa();
                                        aj.a().a(context, context.getString(R.string.enjoy_using_gaana_plus));
                                        if (asVar != null) {
                                            asVar.onTrialSuccess();
                                            an.a().e("click", "ac", "", "TRIAL", "", "SUCCESS", "", "");
                                            if (GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getAccountType() != 2) {
                                                ((BaseActivity) context).sendGAEvent("Gaana+ Dialog", "Trial Dialog Activation failed - Server Rejected", str);
                                                return;
                                            } else if (str.equalsIgnoreCase("Trial_card")) {
                                                ((GaanaActivity) context).refreshTrialCard();
                                                Constants.Y = false;
                                                aj.a().a(context, "Download Songs", "your 14 days of free trial pack has been activated", new OnClickListener() {
                                                    public void onClick(View view) {
                                                        Util.D(context);
                                                    }
                                                });
                                                ((BaseActivity) context).sendGAEvent("Trial activation card", "Login result", "Success");
                                                return;
                                            } else {
                                                ((BaseActivity) context).sendGAEvent("Gaana+ Dialog", "Trial Dialog Activation success", str);
                                                return;
                                            }
                                        }
                                        Intent intent = new Intent(context, GaanaActivity.class);
                                        intent.setFlags(71303168);
                                        context.startActivity(intent);
                                    }
                                });
                            }

                            public void onFailure(String str, String str2) {
                                an.a().e("click", "ac", "", "TRIAL", "", "FAILURE", "", "");
                                ((BaseActivity) context).hideProgressDialog();
                                if (asVar != null) {
                                    if (str.equalsIgnoreCase("Trial_card")) {
                                        ((BaseActivity) context).sendGAEvent("Trial activation card", "Login result", "Failure");
                                    } else {
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("Trial Dialog Activation failed - ");
                                        stringBuilder.append(str);
                                        ((BaseActivity) context).sendGAEvent("Gaana+ Dialog", stringBuilder.toString(), str);
                                    }
                                }
                                if (!TextUtils.isEmpty(str)) {
                                    aj.a().a(context, str);
                                }
                            }
                        });
                    }
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                if (str.equalsIgnoreCase("Trial_card")) {
                    if (asVar != null) {
                        asVar.onTrialSuccess();
                    }
                    ((BaseActivity) context).sendGAEvent("Trial activation card", "Login result", "Not Eligible for Trial");
                }
            }
        });
    }

    public static boolean b(Context context, String str, String str2, String str3, as asVar) {
        ((BaseActivity) context).hideProgressDialog();
        String str4 = "";
        if (!TextUtils.isEmpty(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("&user_type=gplus_mini&source=bottom_sheet&sub_source=");
            stringBuilder.append(str);
            str4 = stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("https://api.gaana.com/gaanaplusservice_nxtgen.php?type=get_gtrial");
        if (TextUtils.isEmpty(str4)) {
            str4 = "";
        }
        stringBuilder2.append(str4);
        stringBuilder2.append(ag.a(C).d());
        str4 = stringBuilder2.toString();
        URLManager uRLManager = new URLManager();
        uRLManager.a(str4);
        uRLManager.b(1);
        uRLManager.a(BusinessObjectType.TrialProductFeature);
        uRLManager.i(false);
        uRLManager.b(Boolean.valueOf(false));
        final Context context2 = context;
        final as asVar2 = asVar;
        final String str5 = str;
        final String str6 = str3;
        final String str7 = str2;
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if ((context2 instanceof Activity) && !((Activity) context2).isFinishing() && (obj instanceof TrialProductFeature)) {
                    TrialProductFeature trialProductFeature = (TrialProductFeature) obj;
                    if (trialProductFeature.getIs_card() == 1) {
                        new GaanaMiniPopupView(context2, trialProductFeature, asVar2, str5).show();
                        if (str6 != null) {
                            str6.equals("Free_trial");
                            return;
                        }
                        return;
                    }
                    Util.c(context2, str7, str6, asVar2, true);
                }
            }
        }, uRLManager);
        return false;
    }

    public static void a(Context context, TrialProductFeature trialProductFeature, @Nullable BLOCK_ACTION block_action, @Nullable as asVar) {
        if (trialProductFeature != null && trialProductFeature.getPg_product() != null) {
            ProductItem pg_product = trialProductFeature.getPg_product();
            final boolean is_trial = trialProductFeature.getIs_trial();
            ag.a(context).a("Trial Popup", "Gaana Plus");
            if (NativeContentAd.ASSET_HEADLINE.equalsIgnoreCase(pg_product.getAction())) {
                if (trialProductFeature.getIs_trial()) {
                    u.a().a("Free Gaana+ pop up", "Click", "Default Plan");
                } else {
                    u.a().a("Gaana+ subscription pop up", "Click", "Default Plan");
                }
                ag a = ag.a(context);
                final Context context2 = context;
                final ProductItem productItem = pg_product;
                final BLOCK_ACTION block_action2 = block_action;
                final as asVar2 = asVar;
                AnonymousClass3 anonymousClass3 = new com.managers.ag.a() {
                    public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                        String p_payment_mode;
                        StringBuilder stringBuilder;
                        ag.a(context2).a("", "", "success");
                        ((BaseActivity) context2).updateUserStatus(new au() {
                            public void onUserStatusUpdated() {
                                ((BaseActivity) context2).hideProgressDialog();
                                ap.a().a(context2);
                                Util.aa();
                                aj.a().a(context2, GaanaApplication.getContext().getString(R.string.enjoy_using_gaana_plus));
                                if (Util.v(context2)) {
                                    Intent intent = new Intent(context2, GaanaActivity.class);
                                    intent.setFlags(71303168);
                                    context2.startActivity(intent);
                                }
                            }
                        });
                        if (!(Util.s() == null || TextUtils.isEmpty(productItem.getP_payment_mode()))) {
                            p_payment_mode = productItem.getP_payment_mode();
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Success; ");
                            stringBuilder.append(Util.s());
                            u.a().a("Payment_Mode", p_payment_mode, stringBuilder.toString());
                        }
                        StringBuilder stringBuilder2;
                        if (is_trial) {
                            an a = an.a();
                            p_payment_mode = "click";
                            String str = "ac";
                            String str2 = "";
                            String str3 = "TRIAL";
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Description: ");
                            stringBuilder2.append(productItem);
                            a.e(p_payment_mode, str, str2, str3, stringBuilder2.toString() != null ? productItem.getDesc() : "Not Available!", "SUCCESS", "", "");
                        } else {
                            an a2 = an.a();
                            String str4 = "click";
                            String str5 = "ac";
                            String str6 = "";
                            String str7 = "PG";
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Description: ");
                            stringBuilder2.append(productItem);
                            a2.e(str4, str5, str6, str7, stringBuilder2.toString() != null ? productItem.getDesc() : "Not Available!", "SUCCESS", "", "");
                        }
                        if (block_action2 != null && block_action2 == BLOCK_ACTION.SKIP) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Plan_");
                            stringBuilder.append(productItem.getDesc());
                            stringBuilder.append("_Success");
                            u.a().a("Skip Count", "Paid Pop Up", stringBuilder.toString());
                        }
                        if (asVar2 != null) {
                            asVar2.onTrialSuccess();
                        }
                    }

                    public void onFailure(String str, String str2) {
                        StringBuilder stringBuilder;
                        String str3 = str;
                        StringBuilder stringBuilder2;
                        String desc;
                        if (is_trial) {
                            an a = an.a();
                            String str4 = "click";
                            String str5 = "ac";
                            String str6 = "";
                            String str7 = "TRIAL";
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Description: ");
                            stringBuilder2.append(productItem);
                            if (stringBuilder2.toString() != null) {
                                desc = productItem.getDesc();
                            } else {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Not Available !, Error: ");
                                stringBuilder2.append(str3);
                                desc = stringBuilder2.toString();
                            }
                            a.e(str4, str5, str6, str7, desc, "FAILURE", "", "");
                        } else {
                            an a2 = an.a();
                            String str8 = "click";
                            String str9 = "ac";
                            String str10 = "";
                            String str11 = "PG";
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Description: ");
                            stringBuilder2.append(productItem);
                            if (stringBuilder2.toString() != null) {
                                desc = productItem.getDesc();
                            } else {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Not Available !, Error: ");
                                stringBuilder2.append(str3);
                                desc = stringBuilder2.toString();
                            }
                            a2.e(str8, str9, str10, str11, desc, "FAILURE", "", "");
                        }
                        ag.a(context2).a(str3, "", str2);
                        aj.a().a(context2, str3);
                        if (!(Util.s() == null || productItem == null || TextUtils.isEmpty(productItem.getP_payment_mode()))) {
                            String p_payment_mode = productItem.getP_payment_mode();
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Failure; ");
                            stringBuilder.append(Util.s());
                            u.a().a("Payment_Mode", p_payment_mode, stringBuilder.toString());
                        }
                        if (block_action2 != null && block_action2 == BLOCK_ACTION.SKIP) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Default Plan_");
                            stringBuilder.append(productItem.getDesc());
                            stringBuilder.append("_Fail");
                            u.a().a("Skip Count", "Paid Pop Up", stringBuilder.toString());
                        }
                    }
                };
                a.a(context2, productItem, anonymousClass3, pg_product.getItem_id(), pg_product.getDesc());
            } else if (NativeContentAd.ASSET_BODY.equalsIgnoreCase(pg_product.getAction())) {
                GaanaApplication.getInstance().setSidebarActiveBtn(R.id.upgradeButtonLayout);
                ((GaanaActivity) context).changeFragment(R.id.LeftMenuPurchase, pg_product.getItem_id(), pg_product.getP_id());
                if (block_action != null && block_action == BLOCK_ACTION.SKIP) {
                    u.a().a("Skip Count", "Paid Pop Up", "Subscription Screen");
                }
            } else {
                GaanaApplication.getInstance().setSidebarActiveBtn(R.id.upgradeButtonLayout);
                ((GaanaActivity) context).changeFragment(R.id.LeftMenuPurchase, pg_product.getItem_id(), pg_product.getP_id());
            }
        }
    }

    private static void D(Context context) {
        if (GaanaApplication.getInstance().isAppInOfflineMode()) {
            ((BaseActivity) context).displayFeatureNotAvailableOfflineDialog(context.getString(R.string.this_feature));
        } else if (j(context)) {
            ListingParams listingParams = new ListingParams();
            listingParams.e(false);
            listingParams.f(true);
            listingParams.h(false);
            listingParams.d(false);
            listingParams.i(false);
            listingParams.a(false);
            ListingComponents am = am();
            ListingButton listingButton = (ListingButton) am.c().get(0);
            URLManager c = listingButton.c();
            c.g(true);
            c.d(false);
            c.a(true);
            c.a(BusinessObjectType.GenericItems);
            c.h(true);
            listingParams.a(listingButton);
            BaseGaanaFragment listingFragment = new ListingFragment();
            listingFragment.a(listingParams);
            GaanaApplication.getInstance().setListingComponents(am);
            ((GaanaActivity) context).displayFragment(listingFragment);
        } else {
            ap.a().f(context);
        }
    }

    private static ListingComponents am() {
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.c(true);
        listingButton.a(true);
        URLManager uRLManager = new URLManager();
        uRLManager.a(Priority.HIGH);
        uRLManager.a(60);
        listingButton.c(RadioButtonGenericView.class.getName());
        uRLManager.a("https://apiv2.gaana.com/home/trending/songs");
        uRLManager.b(Boolean.valueOf(true));
        uRLManager.f(true);
        uRLManager.i(true);
        listingButton.a(uRLManager);
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        return listingComponents;
    }

    private static void c(final Context context, String str, final String str2, final as asVar, boolean z) {
        if (z) {
            LoginManager.getInstance().checkTrialAvailability(context, new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(final BusinessObject businessObject) {
                    if (businessObject instanceof BasicResponse) {
                        BasicResponse basicResponse = (BasicResponse) businessObject;
                        if (basicResponse.getResult() != null && basicResponse.getResult().equalsIgnoreCase("Yes") && Constants.az) {
                            ((GaanaActivity) context).setSlideUpPanel(true);
                            ((BaseActivity) context).sendGAEvent("Gaana+ Dialog", "Trial Dialog action taken", str2);
                            ((BaseActivity) context).checkSetLoginStatus(new ad() {
                                public void onLoginSuccess() {
                                    ((BaseActivity) context).showProgressDialog(Boolean.valueOf(false), context.getString(R.string.activating_trial));
                                    if (ap.a().d()) {
                                        ((BaseActivity) context).hideProgressDialog();
                                        if (asVar != null) {
                                            asVar.onTrialSuccess();
                                        }
                                        return;
                                    }
                                    ag.a(context).a(context, str2, new com.managers.ag.a() {
                                        public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                                            ((BaseActivity) context).updateUserStatus(new au() {
                                                public void onUserStatusUpdated() {
                                                    ((BaseActivity) context).hideProgressDialog();
                                                    ap.a().a(context);
                                                    Util.aa();
                                                    aj.a().a(context, context.getString(R.string.enjoy_using_gaana_plus));
                                                    if (asVar != null) {
                                                        if (GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getAccountType() == 2) {
                                                            ((BaseActivity) context).sendGAEvent("Gaana+ Dialog", "Trial Dialog Activation success", str2);
                                                            Toast.makeText(context, context.getString(R.string.trial_activation_msg), 0).show();
                                                        } else {
                                                            ((BaseActivity) context).sendGAEvent("Gaana+ Dialog", "Trial Dialog Activation failed - Server Rejected", str2);
                                                        }
                                                        asVar.onTrialSuccess();
                                                        return;
                                                    }
                                                    Intent intent = new Intent(context, GaanaActivity.class);
                                                    intent.setFlags(71303168);
                                                    context.startActivity(intent);
                                                }
                                            });
                                        }

                                        public void onFailure(String str, String str2) {
                                            if (asVar != null) {
                                                StringBuilder stringBuilder = new StringBuilder();
                                                stringBuilder.append("Trial Dialog Activation failed - ");
                                                stringBuilder.append(str);
                                                ((BaseActivity) context).sendGAEvent("Gaana+ Dialog", stringBuilder.toString(), str2);
                                                if (str2 != null && str2.equals("Free_trial")) {
                                                    u.a().a("Skip Count", "30 days Pop Up", "Default Plan_30 Days Trial_Fail");
                                                }
                                            }
                                            if (!TextUtils.isEmpty(str)) {
                                                aj.a().a(context, str);
                                            }
                                        }
                                    });
                                }
                            }, context.getResources().getString(R.string.LOGIN_LAUNCHED_FOR_FREE_TRAIL), false, true);
                        } else if (basicResponse.getResult() != null && basicResponse.getResult().equalsIgnoreCase("No")) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            bundle.putBoolean("LAUNCH_GAANA_PLUS", true);
                            ((GaanaActivity) context).setSlideUpPanel(true);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            ((BaseActivity) context).sendGAEvent("Gaana+ Dialog", "Gaana+ Dialog action taken", str2);
                            if (!((GaanaActivity) context).isFragmentDisplayed(settingsDetailFragment)) {
                                ((GaanaActivity) context).displayFragment(settingsDetailFragment);
                            }
                        }
                    } else if (businessObject instanceof TrialProductFeature) {
                        TrialProductFeature trialProductFeature = (TrialProductFeature) businessObject;
                        if (trialProductFeature.getIs_trial()) {
                            if (TextUtils.isEmpty(trialProductFeature.getCard_identifier())) {
                                u.a().b("A/B Testing", "Generic");
                            } else {
                                u.a().b("A/B Testing", trialProductFeature.getCard_identifier());
                            }
                            ((BaseActivity) context).checkSetLoginStatus(new ad() {
                                public void onLoginSuccess() {
                                    Util.a(context, (TrialProductFeature) businessObject, null, asVar);
                                }
                            }, context.getResources().getString(R.string.login_for_paid_trial), false, true);
                        }
                    }
                }
            }, z ? "&source=gaanaplus_card" : "");
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt("KEY_SETTINGS", 1);
            bundle.putBoolean("LAUNCH_GAANA_PLUS", true);
            GaanaActivity gaanaActivity = (GaanaActivity) context;
            gaanaActivity.setSlideUpPanel(true);
            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
            settingsDetailFragment.setArguments(bundle);
            ((BaseActivity) context).sendGAEvent("Gaana+ Dialog", "Gaana+ Dialog action taken", str2);
            if (!gaanaActivity.isFragmentDisplayed(settingsDetailFragment)) {
                gaanaActivity.displayFragment(settingsDetailFragment);
            }
        }
        z = false;
    }

    public static void b(Context context, String str, as asVar) {
        String string = context.getResources().getString(R.string.subscribe_gaanaplus_download_msg);
        if (TextUtils.isEmpty(str)) {
            str = context.getString(R.string.opt_download_english);
        }
        a(context, string, str, asVar);
    }

    public static void b(Context context, String str, String str2, as asVar) {
        String string = context.getResources().getString(R.string.subscribe_gaanaplus_download_msg);
        if (TextUtils.isEmpty(str2)) {
            str2 = context.getString(R.string.opt_download_english);
        }
        a(context, str, string, str2, asVar);
    }

    public static String a(String str, String str2, String str3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(str3);
        return c(stringBuilder.toString().toLowerCase());
    }

    public static int w() {
        if (!g.b()) {
            return VERSION.SDK_INT >= 21 ? R.drawable.gaana_logo_notif : R.mipmap.gaana_logo;
        } else {
            if (VERSION.SDK_INT >= 21) {
                return R.drawable.gaana_logo_notif_mmx;
            }
            return R.mipmap.gaana_logo_mmx;
        }
    }

    public static int x() {
        return g.b() ? R.mipmap.gaana_logo_mmx : R.mipmap.gaana_logo;
    }

    public static long y() {
        return B;
    }

    public static void d(long j) {
        B += j;
    }

    public static void e(long j) {
        F += j;
    }

    public static void z() {
        B = 0;
    }

    public static void A() {
        F = 0;
    }

    public static long B() {
        return F;
    }

    public static void C() {
        if (j(GaanaApplication.getContext())) {
            h.a().a(new TaskListner() {
                public void onBackGroundTaskCompleted() {
                }

                public void doBackGroundTask() {
                    try {
                        String str = "https://api.gaana.com/user.php?type=create_app_installation_log&campaign_code=RFB50&referrer_user_id=";
                        String c = com.managers.d.a().c();
                        if (!TextUtils.isEmpty(c)) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(str);
                            stringBuilder.append(c);
                            str = stringBuilder.toString();
                        }
                        new j().b(str).b().booleanValue();
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
            }, -1);
        }
    }

    public static void D() {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://logs.gaana.com/user/ab/settings");
        uRLManager.i(true);
        uRLManager.c(0);
        uRLManager.a(UserJourneyFlagsData.class);
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject instanceof UserJourneyFlagsData) {
                    UserJourneyFlagsData userJourneyFlagsData = (UserJourneyFlagsData) businessObject;
                    if (userJourneyFlagsData.getJourney() != null) {
                        Constants.aL = userJourneyFlagsData.getJourney().getClick();
                        Constants.aM = userJourneyFlagsData.getJourney().getScroll();
                        Constants.aN = userJourneyFlagsData.getJourney().getState();
                        Constants.aQ = userJourneyFlagsData.getJourney().getPlayout();
                        Constants.aP = userJourneyFlagsData.getJourney().getAd();
                        Constants.aR = userJourneyFlagsData.getJourney().getMaster();
                        Constants.aS = userJourneyFlagsData.getJourney().getMinBatchSize();
                        Constants.aT = userJourneyFlagsData.getJourney().getMaxBatchSize();
                        Constants.aU = userJourneyFlagsData.getJourney().getMaxBatchInterval();
                        d.a().a("PREFERENCE_UJ_MASTER", userJourneyFlagsData.getJourney().getMaster(), false);
                        d.a().a("PREFERENCE_UJ_STATE", userJourneyFlagsData.getJourney().getState(), false);
                        d.a().a("PREFERENCE_UJ_CLICK", userJourneyFlagsData.getJourney().getClick(), false);
                        d.a().a("PREFERENCE_UJ_SCROLL", userJourneyFlagsData.getJourney().getScroll(), false);
                        d.a().a("PREFERENCE_UJ_PLAYOUT", userJourneyFlagsData.getJourney().getPlayout(), false);
                        d.a().a("PREFERENCE_UJ_ADS", userJourneyFlagsData.getJourney().getAd(), false);
                    }
                    if (userJourneyFlagsData.getVoiceUi() != null) {
                        Constants.ba = userJourneyFlagsData.getVoiceUi().isToShowSearchCard();
                        Constants.bb = userJourneyFlagsData.getVoiceUi().isAuto_keyboard();
                    }
                    u.a().a(23, "ON");
                    u.a().a(25, "autoplay_on");
                    if (userJourneyFlagsData.getTags() != null) {
                        Constants.aF = userJourneyFlagsData.getTags().getMaster();
                    }
                    if (userJourneyFlagsData.getGapless() != null) {
                        Constants.C = userJourneyFlagsData.getGapless().getMaster();
                    }
                    if (userJourneyFlagsData.getNewPlayer() != null) {
                        Constants.G = userJourneyFlagsData.getNewPlayer().getMaster();
                    }
                    if (userJourneyFlagsData.getViewConfig() != null) {
                        Constants.aV = userJourneyFlagsData.getViewConfig().showViewAllImg();
                        d.a().a("pref_home_view_all", Constants.aV, false);
                        Constants.aW = userJourneyFlagsData.getViewConfig().showPlaylistPlayIcon();
                        d.a().a("pref_home_playlist_play_icon", Constants.aW, false);
                        Constants.aX = userJourneyFlagsData.getViewConfig().showTrackPlayouts();
                        d.a().a("pref_home_tracks_playouts", Constants.aX, false);
                        Constants.aY = userJourneyFlagsData.getViewConfig().showLyricsCard();
                        d.a().a("pref_home_lyrics_card", Constants.aY, false);
                        Constants.aZ = userJourneyFlagsData.getViewConfig().showPreScreen();
                        d.a().a("pref_home_prescreen", Constants.aZ, false);
                    }
                    if (userJourneyFlagsData.getPlayerCarouselView() != null) {
                        Constants.H = userJourneyFlagsData.getPlayerCarouselView().getMaster();
                        u.a().b(String.valueOf(Constants.H));
                        d.a().a("PREFERENCE_UJ_PLAYER_CAROUSEL_ENABLED", userJourneyFlagsData.getPlayerCarouselView().getMaster(), false);
                    }
                    if (userJourneyFlagsData.getMiniPlayerV4() != null) {
                        Constants.I = userJourneyFlagsData.getMiniPlayerV4().getShowCenterPlayer();
                        Constants.J = userJourneyFlagsData.getMiniPlayerV4().getShowSuggestiveLabel();
                        d.a().a("PREFERENCE_UJ_MINI_V4_ENABLED", userJourneyFlagsData.getMiniPlayerV4().getShowCenterPlayer(), false);
                        d.a().a("PREFERENCE_UJ_MINI_V4_PULL_UP_TEXT", userJourneyFlagsData.getMiniPlayerV4().getShowSuggestiveLabel(), false);
                    }
                    if (userJourneyFlagsData.getDownloadSettings() != null) {
                        Constants.ca = userJourneyFlagsData.getDownloadSettings().getDownload();
                        d.a().a("PREFERENCE_DOWNLOAD_SETTINGS_ENABLED", userJourneyFlagsData.getDownloadSettings().getDownload(), false);
                    }
                }
            }
        }, uRLManager, Boolean.valueOf(true));
    }

    public static void E() {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://logs.gaana.com/ga-events");
        uRLManager.c(0);
        uRLManager.a(GaEventsConfig.class);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj instanceof GaEventsConfig) {
                    u a = u.a();
                    GaEventsConfig gaEventsConfig = (GaEventsConfig) obj;
                    CustomInApp customInApp = gaEventsConfig.getCustomInApp();
                    if (customInApp != null) {
                        a.a(GaEventsConfig.IN_APP_CATEGORY_KEY, "MASTER", customInApp.getMaster());
                        a.a(GaEventsConfig.IN_APP_CATEGORY_KEY, GaEventsConfig.IN_APP_ACTION_APIRESPONSE_KEY, customInApp.getApiResponse());
                        a.a(GaEventsConfig.IN_APP_CATEGORY_KEY, GaEventsConfig.IN_APP_ACTION_RESPONSE_KEY, customInApp.getResponse());
                        d.a().a(customInApp.getMaster(), GaEventsConfig.IN_APP_CATEGORY_KEY, false);
                        d.a().a(customInApp.getResponse(), GaEventsConfig.IN_APP_ACTION_RESPONSE_KEY, false);
                        d.a().a(customInApp.getApiResponse(), GaEventsConfig.IN_APP_ACTION_APIRESPONSE_KEY, false);
                    }
                    ABTesting abTesting = gaEventsConfig.getAbTesting();
                    if (abTesting != null) {
                        a.a(GaEventsConfig.ABTESTING_CATEGORY_KEY, "MASTER", abTesting.getMaster());
                        d.a().a(abTesting.getMaster(), GaEventsConfig.ABTESTING_PREFERENCE_KEY, false);
                    }
                }
            }
        }, uRLManager);
    }

    public static void F() {
        Constants.cv = true;
        MoEngage.getInstance().reportAppLaunched();
        h.a().a(new TaskListner() {
            public void doBackGroundTask() {
                try {
                    com.services.i b = new j().b("https://api.gaana.com/index.php?type=nxtgen_sdk_config&is_deviceid".replace("<is_deviceid>", URLEncoder.encode(String.valueOf(Util.m(GaanaApplication.getContext())))));
                    if (b != null && b.a() != null) {
                        SDKConfig sDKConfig = (SDKConfig) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(b.a(), SDKConfig.class);
                        if (sDKConfig != null) {
                            boolean z = true;
                            if (sDKConfig.isLocalMusicEnabled() == 1) {
                                Constants.Q = true;
                            } else {
                                Constants.Q = false;
                            }
                            Constants.M = sDKConfig.getSDLifetimeCap();
                            Constants.L = sDKConfig.getSDMonthlyCap();
                            if (sDKConfig.isReferralActive() == 1) {
                                Constants.R = true;
                            } else {
                                Constants.R = false;
                            }
                            if (sDKConfig.isVerticalVideosAutoplayActive() == 1) {
                                Constants.cH = true;
                            } else {
                                Constants.cH = false;
                            }
                            if (sDKConfig.isReferralBannerActive() == 1) {
                                Constants.S = true;
                            } else {
                                Constants.S = false;
                            }
                            long b2 = d.a().b(0, "PREFERENCE_TIMESTAMP_LAST_METADATA_SYNC", false);
                            long b3 = d.a().b(0, "PREFERENCE_TIMESTAMP_LAST_CACHE_READ", false);
                            if (!(b2 == 0 || b2 == sDKConfig.getTimestampMetadata())) {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    public void run() {
                                        DynamicViewManager.a().c();
                                    }
                                });
                            }
                            if (!(b3 == 0 || b3 == sDKConfig.getTimestampCache())) {
                                com.i.j.a().c().d().a();
                                o.a().b();
                                o.a().c();
                            }
                            Constants.bT = sDKConfig.getSimplFlag();
                            if (!Constants.bT) {
                                LoginManager.getInstance().clearSimplSession();
                            }
                            Constants.aC = sDKConfig.isLayoutTrendingHorizontal();
                            Constants.aD = sDKConfig.isLayoutHereItHorizontal();
                            Constants.df = sDKConfig.getDummyQueueSize();
                            Constants.aI = sDKConfig.getToastNotificationMsg();
                            Constants.aJ = sDKConfig.shouldShowToastNotification() == 1;
                            Constants.dv = sDKConfig.getInitialSessionTime();
                            Constants.dw = sDKConfig.getHomeFeedSessionTime();
                            Constants.dy = sDKConfig.getDaysInterval();
                            Constants.dA = sDKConfig.getTrialNotificationWaitTime();
                            Constants.dk = sDKConfig.getOnBoardPlayerBottomText();
                            Constants.dn = sDKConfig.getOnboardPlayerNeedToShow();
                            Constants.dm = sDKConfig.getOnBoardTimeAfterShow();
                            Constants.dl = sDKConfig.getOnBoardPlayerStopText();
                            Constants.cF = sDKConfig.isYouTubeVideoEnabled();
                            Constants.cG = sDKConfig.isDBSearchLogEnabled();
                            Constants.t = sDKConfig.getWait_time_smart_login_switch();
                            Constants.s = sDKConfig.getWait_time_smart_login();
                            Constants.z = sDKConfig.getAutologin_smart();
                            Constants.A = sDKConfig.getAutologin_smart_switch();
                            Constants.u = sDKConfig.getReturnuser_signup();
                            Constants.v = sDKConfig.getReturnuser_signup_switch();
                            Constants.w = sDKConfig.getSignup_session();
                            Constants.x = sDKConfig.getLogin_fcap();
                            Constants.B = sDKConfig.getMandatory_signup();
                            Constants.E = sDKConfig.getPlaybackCacheEnabled();
                            Constants.K = sDKConfig.getTrackAdvancedCache();
                            Constants.F = sDKConfig.getUseSecondaryPlayer();
                            Constants.cM = sDKConfig.getIs_add_to_playlist_visible();
                            Constants.cP = sDKConfig.getAdsFreeSession() - 1;
                            Constants.cO = sDKConfig.getAdsRAM();
                            Constants.cZ = sDKConfig.isHousePartyOn();
                            if (!TextUtils.isEmpty(sDKConfig.getPrContentLogic())) {
                                Constants.cQ = sDKConfig.getPrContentLogic();
                            }
                            d.a().a("pref_gaana_party_hub", sDKConfig.isHousePartyOn(), false);
                            if (sDKConfig.getIdentifySongRecordTime() != 0) {
                                Constants.dr = sDKConfig.getIdentifySongRecordTime();
                            }
                            if (sDKConfig.getLow_mem_threshold() != 0) {
                                Constants.ds = sDKConfig.getLow_mem_threshold();
                            }
                            Constants.cX = sDKConfig.getLow_mem_alert();
                            String new_detail_page = sDKConfig.getNew_detail_page();
                            if (TextUtils.isEmpty(new_detail_page) || !new_detail_page.equals("0")) {
                                z = false;
                            }
                            Constants.Z = z;
                            if (sDKConfig.getAutoSyncTimeInterval() > 0) {
                                Constants.cI = (long) ((((sDKConfig.getAutoSyncTimeInterval() * 24) * 60) * 60) * 1000);
                            }
                            if (sDKConfig.getAutoSyncSongsInterval() > 0) {
                                Constants.cJ = sDKConfig.getAutoSyncSongsInterval();
                            }
                            if (sDKConfig.getCuratedFirstLimit() > 0) {
                                Constants.ak = sDKConfig.getCuratedFirstLimit();
                            }
                            if (sDKConfig.getCuratedSubsequentLimit() > 0) {
                                Constants.al = sDKConfig.getCuratedSubsequentLimit();
                            }
                            if (sDKConfig.getTc_initialize() > 0) {
                                Constants.ap = sDKConfig.getTc_initialize();
                            }
                            if (sDKConfig.getTc_intervals() > 0) {
                                Constants.aq = sDKConfig.getTc_intervals();
                            }
                            if (sDKConfig.getFcap() > 0) {
                                Constants.ar = sDKConfig.getFcap();
                            }
                            if (!TextUtils.isEmpty(sDKConfig.getTc_repeat())) {
                                Constants.as = sDKConfig.getTc_repeat();
                            }
                            if (!TextUtils.isEmpty(sDKConfig.getNotif_weekday_range())) {
                                Constants.aw = sDKConfig.getNotif_weekday_range();
                            }
                            if (!TextUtils.isEmpty(sDKConfig.getNotif_weekend_range())) {
                                Constants.ax = sDKConfig.getNotif_weekend_range();
                            }
                            if (sDKConfig.getNotif_day_repeat() > 0) {
                                Constants.ay = sDKConfig.getNotif_day_repeat();
                            }
                            if (!TextUtils.isEmpty(sDKConfig.getbackPressedMessage())) {
                                d.a().a("PREFERENCE_BACKPRESSED_MESSAGE", sDKConfig.getbackPressedMessage(), false);
                            }
                            d.a().a("PREFERENCE_EDUCATIVE_SCREEN", sDKConfig.isEducativeScreenLaunch(), false);
                            Constants.an = sDKConfig.getCdActive();
                            Constants.am = sDKConfig.getCdFcap();
                            Constants.ao = sDKConfig.getCdReset();
                            Constants.bE = sDKConfig.shouldFetchLoginAgePermission();
                            d.a().a("PREFERENCE_CURATED_DOWNLOAD_ACTIVE", Constants.an, false);
                            d.a().a(Constants.am, "PREFERENCE_CURATED_DOWNLOAD_FCAP", false);
                            d.a().a("pref_trending_layout_config", Constants.aC, false);
                            d.a().a("pref_hereit_layout_config", Constants.aD, false);
                            d.a().a("PREFERENCE_IS_LOCAL_MEDIA", Constants.Q, false);
                            d.a().a("PREFERENCE_MAX_QUEUE_SIZE", sDKConfig.getMaxQueueSize(), false);
                            d.a().a("PREFERENCE_REFERRAL_ACTIVE", Constants.R, false);
                            d.a().a("PREFERENCE_REFERRAL_BANNER_ACTIVE", Constants.S, false);
                            d.a().a("PREFERENCE_MAX_RECENT_SEARCH_SIZE", sDKConfig.getMaxRecentSearchSize(), false);
                            d.a().a("PREFERENCE_ONBOARD_PLAYER_TEXT", sDKConfig.getOnboardPlayerText(), false);
                            d.a().a("PREFERENCE_ONBOARD_PLAYER_START_TIMER", sDKConfig.getOnboardPlayStartTimer(), false);
                            d.a().a("PREFERENCE_INITIAL_SESSION_TIME", Constants.dv, false);
                            d.a().a("PREFERENCE_HOME_FEED_SESSION_TIME", Constants.dw, false);
                            d.a().a("PREFERENCE_DAYS_INTERVAL", Constants.dy, false);
                            d.a().a("PREFERENCE_MANDATORY_SIGNUP", Constants.B, false);
                            d.a().a("PREFERENCE_IS_DB_SEARCH_LOG_ENABLED", Constants.cG, false);
                            d.a().a(sDKConfig.getTimestampMetadata(), "PREFERENCE_TIMESTAMP_LAST_METADATA_SYNC", false);
                            d.a().a(sDKConfig.getTimestampCache(), "PREFERENCE_TIMESTAMP_LAST_CACHE_READ", false);
                            LoginManager.getInstance().checkAndMigrateToSSO(sDKConfig.isSSOEnabled());
                            d.a().a("PREF_KEY_LOW_RAM_ADS_FREE_SESSION", Constants.cP, false);
                            d.a().a("PREF_KEY_LOW_RAM_THRESHOLD", Constants.cO, false);
                        }
                    }
                } catch (Exception unused) {
                    Constants.cu = false;
                }
            }

            public void onBackGroundTaskCompleted() {
                if (ColombiaManager.b().e() && ap.a().b(GaanaApplication.getContext())) {
                    ColombiaManager.b().d();
                } else if (ColombiaManager.b().e() && GaanaApplication.getInstance().getCurrentUser() != null && GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getAccountType() == 2) {
                    ColombiaManager.b().d();
                }
                if (Constants.v == 1 && GaanaApplication.getInstance().getCurrentUser().getLoginType() == null && GaanaApplication.sessionHistoryCount < Constants.w * Constants.x && GaanaApplication.sessionHistoryCount != 0 && GaanaApplication.sessionHistoryCount > Constants.w && (GaanaApplication.sessionHistoryCount + Constants.w) % Constants.u == 0) {
                    Intent intent = new Intent(Util.C, Login.class);
                    intent.putExtra("ONBOARD_SIGNUP", true);
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.putExtra("ONBOARD_SIGNUP_FROM_APP_INSIDE", false);
                    Util.C.startActivity(intent);
                }
            }
        }, -1);
    }

    public static void G() {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://apiv2.gaana.com/ad/device");
        uRLManager.a(SDKConfig.class);
        uRLManager.b(Boolean.valueOf(true));
        uRLManager.k(true);
        i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
                GaanaApplication.getInstance().setMetadataUpdateTimestamp();
                SDKConfig sDKConfig = (SDKConfig) obj;
                String b = d.a().b(Constants.ae, null, true);
                if (!(TextUtils.isEmpty(sDKConfig.getHashValue()) || TextUtils.isEmpty(sDKConfig.getHashValue()) || String.valueOf(b).equals(sDKConfig.getHashValue()))) {
                    d.a().a(Constants.ae, sDKConfig.getHashValue(), true);
                    if (sDKConfig.getUpdateHomeMeta() == 1) {
                        DynamicViewManager.a().c();
                    }
                }
                if (!(sDKConfig.getConfig() == null || TextUtils.isEmpty(sDKConfig.getConfig().getIncl_dl()))) {
                    ColombiaAdViewManager.a().c(sDKConfig.getConfig().getIncl_dl());
                }
                if (ap.a().b(GaanaApplication.getContext()) || !(GaanaApplication.getInstance().getCurrentUser() == null || GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData() == null || GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getAccountType() != 2)) {
                    if (sDKConfig.getColombiaAdCode() != null) {
                        ColombiaManager.b().a(sDKConfig);
                        if (Util.A != null) {
                            Util.A.onAdConfigLoaded();
                        }
                    } else if (Util.A != null) {
                        Util.A.onAdConfigFailed();
                    }
                }
                Util.a(sDKConfig.getSplashAdCodeParams());
            }

            public void onErrorResponse(BusinessObject businessObject) {
                if (Util.A != null) {
                    Util.A.onAdConfigFailed();
                }
            }
        }, uRLManager);
    }

    public static void H() {
        String str = "";
        if (!TextUtils.isEmpty(GaanaApplication.getInstance().getCurrentUser().getAuthToken())) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("&token=");
            stringBuilder.append(GaanaApplication.getInstance().getCurrentUser().getAuthToken());
            str = stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("https://api.gaana.com/app_pmt_config.php?type=pmt_config");
        stringBuilder2.append(str);
        str = stringBuilder2.toString();
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
            }

            public void doBackGroundTask() {
                com.services.i b = new j().b(str);
                if (b != null && b.a() != null) {
                    PaymentConfig paymentConfig = (PaymentConfig) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(b.a(), PaymentConfig.class);
                    if (paymentConfig != null && paymentConfig.getStatus() == 1 && paymentConfig.getPaidTrial() != null) {
                        PaidTrial paidTrial = paymentConfig.getPaidTrial();
                        Constants.az = paidTrial.isFreeTrial();
                        Constants.aA = paidTrial.isPaidTrial();
                        Constants.aB = paidTrial.getPgGateway();
                    }
                }
            }
        }, -1);
    }

    public static void I() {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/app_pmt_config.php?type=postback");
        uRLManager.a(1440);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
            }
        }, uRLManager);
    }

    public static void J() {
        d = null;
        d.a().b("USER_APP_SETTING_CHANGES", true);
    }

    public static boolean K() {
        try {
            if (d == null) {
                String b = d.a().b("USER_APP_SETTING_CHANGES", null, true);
                if (b == null) {
                    d = new JSONObject();
                    return false;
                }
                d = new JSONObject(b);
            }
            return d.length() > 0;
        } catch (JSONException unused) {
            return false;
        }
    }

    public static void b(String str, String str2) {
        try {
            K();
            if (d.has(str)) {
                d.remove(str);
            }
            d.put(str, str2);
            d.a().a("USER_APP_SETTING_CHANGES", d.toString(), true);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static void L() {
        if (GaanaApplication.getInstance().getCurrentUser() != null || GaanaApplication.getInstance().getCurrentUser().getAuthToken() != null || K()) {
            HashMap hashMap = new HashMap();
            hashMap.put("app_settings", d.toString());
            hashMap.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, GaanaApplication.getInstance().getCurrentUser().getAuthToken());
            URLManager uRLManager = new URLManager();
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.i(false);
            uRLManager.a("https://apiv2.gaana.com/user/update/app-setting");
            uRLManager.a(String.class);
            uRLManager.c(1);
            uRLManager.a(hashMap);
            i.a().a(new af() {
                public void onRetreivalComplete(Object obj) {
                    try {
                        if (new JSONObject((String) obj).getInt("status") == 1) {
                            d.a().b("USER_APP_SETTING_CHANGES", true);
                            Util.d = null;
                        }
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    if (Util.d != null) {
                        d.a().a("USER_APP_SETTING_CHANGES", Util.d.toString(), true);
                    }
                }
            }, uRLManager);
        }
    }

    public static void M() {
        int b;
        StringBuilder stringBuilder;
        d a = d.a();
        b("is_private", a.b("PREFERENCE_KEY_SOCIAL_ALLOW_FOLLOW_WITH_REQUEST", true, false) ? "1" : "0");
        if (Constants.C == 1) {
            b("gap_less_playback", a.b("PREFERENCE_KEY_GAPLESS_PLAYBACK", false, true) ? "1" : "0");
            b = a.b("PREFERENCE_KEY_CROSSFADE_VALUE", 0, true);
            stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(b);
            b("cross_fade", stringBuilder.toString());
        }
        b("data_save_mode", a.b("PREFERENCE_KEY_DATA_SAVE_MODE", false, false) ? "1" : "0");
        b("recommend_song_queue", a.b("PREFERENCE_KEY_ENDLESS_PLAYBACK", true, false) ? "1" : "0");
        b = a.b("PREFERENCE_KEY_DOWNLOAD_IMAGE", 1, true);
        stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(b);
        b("download_over", stringBuilder.toString());
        b("parental_warning", a.b("pref_explicit_content", false, false) ? "1" : "0");
        b("download_over_2G3G", a.b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true) ? "1" : "0");
        b("smart_download", a.b("PREFERENCE_KEY_AUTO_DOWNLOAD", true, false) ? "1" : "0");
        b("schedule_downloads", a.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_ENABLED", false, true) ? "1" : "0");
        b = a.b("PREFERENCE_KEY_SYNC_QUALITY", 2, true);
        stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(b);
        b("download_quality", stringBuilder.toString());
        b("sync_over_2G3G", a.b("PREFERENCE_KEY_DOWNLOAD_SYNC_OVER_DATA_CONNECTION", false, true) ? "1" : "0");
        b("auto_sync", a.b("PREFERENCE_KEY_SETTINGS_AUTO_SYNC_V5", false, true) ? "1" : "0");
        b("music_recommendations", a.b("PREFERENCE_KEY_NOTIFICATION_MUSIC_RECOMMENDATIONS", true, false) ? "1" : "0");
        b("mark_fav_playlist", a.b("PREFERENCE_KEY_NOTIFICATION_FAVORITE_PLAYLIST", true, false) ? "1" : "0");
        b("can_follow", a.b("PREFERENCE_KEY_NOTIFICATION_FOLLOW_UPDATES", true, false) ? "1" : "0");
        b(HomeSettingsItemView.SETTINGS_TAG_DISPLAY_LANGUAGE, f.b(C));
        L();
    }

    public static void N() {
        if (GaanaApplication.getInstance().getCurrentUser() != null || GaanaApplication.getInstance().getCurrentUser().getAuthToken() != null) {
            final String authToken = GaanaApplication.getInstance().getCurrentUser().getAuthToken();
            h.a().a(new TaskListner() {
                d a = d.a();

                public void onBackGroundTaskCompleted() {
                }

                public void doBackGroundTask() {
                    try {
                        j jVar = new j();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("https://apiv2.gaana.com/user/get-app-setting?token=");
                        stringBuilder.append(authToken);
                        com.services.i b = jVar.b(stringBuilder.toString());
                        if (b != null && b.a() != null) {
                            UserAppSettings userAppSettings = (UserAppSettings) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(b.a().trim(), UserAppSettings.class);
                            if (userAppSettings != null) {
                                if (userAppSettings.getIsPrivate() != -1) {
                                    this.a.a("PREFERENCE_KEY_SOCIAL_ALLOW_FOLLOW_WITH_REQUEST", userAppSettings.getIsPrivate() == 1, false);
                                }
                                if (userAppSettings.getGapLessPlayback() != -1 && Constants.C == 1) {
                                    this.a.a("PREFERENCE_KEY_GAPLESS_PLAYBACK", userAppSettings.getGapLessPlayback() == 1, true);
                                }
                                if (userAppSettings.getCrossFade() != -1 && Constants.C == 1) {
                                    this.a.a("PREFERENCE_KEY_CROSSFADE_VALUE", userAppSettings.getCrossFade(), true);
                                }
                                if (userAppSettings.getDataSaveMode() != -1) {
                                    this.a.a("PREFERENCE_KEY_DATA_SAVE_MODE", userAppSettings.getDataSaveMode() == 1, false);
                                }
                                if (userAppSettings.getRecommendSongQueue() != -1) {
                                    this.a.a("PREFERENCE_KEY_ENDLESS_PLAYBACK", userAppSettings.getRecommendSongQueue() == 1, false);
                                }
                                if (userAppSettings.getDownloadOver() != -1) {
                                    this.a.a("PREFERENCE_KEY_DOWNLOAD_IMAGE", userAppSettings.getDownloadOver(), true);
                                }
                                if (userAppSettings.getParentalWarning() != -1) {
                                    this.a.a("pref_explicit_content", userAppSettings.getParentalWarning() == 1, false);
                                }
                                if (userAppSettings.getDownloadOver2G3G() != -1) {
                                    this.a.a("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", userAppSettings.getDownloadOver2G3G() == 1, true);
                                }
                                if (userAppSettings.getAutoDownloadOverWifi() != -1) {
                                    this.a.a("PREFERENCE_KEY_AUTO_DOWNLOAD", userAppSettings.getAutoDownloadOverWifi() == 1, false);
                                }
                                if (userAppSettings.getScheduleDownloads() != -1) {
                                    this.a.a("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_ENABLED", userAppSettings.getScheduleDownloads() == 1, true);
                                    this.a.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", userAppSettings.getScheduleDownloads() == 1, true);
                                }
                                if (userAppSettings.getSyncOver2G3G() != -1) {
                                    this.a.a("PREFERENCE_KEY_DOWNLOAD_SYNC_OVER_DATA_CONNECTION", userAppSettings.getSyncOver2G3G() == 1, true);
                                }
                                if (userAppSettings.getAutoSync() != -1) {
                                    this.a.a("PREFERENCE_KEY_SETTINGS_AUTO_SYNC_V5", userAppSettings.getSyncOver2G3G() == 1, true);
                                }
                                if (userAppSettings.getMusicRecommendations() != -1) {
                                    this.a.a("PREFERENCE_KEY_NOTIFICATION_MUSIC_RECOMMENDATIONS", userAppSettings.getMusicRecommendations() == 1, false);
                                }
                                if (userAppSettings.getMarkFavPlaylist() != -1) {
                                    this.a.a("PREFERENCE_KEY_NOTIFICATION_FAVORITE_PLAYLIST", userAppSettings.getMarkFavPlaylist() == 1, false);
                                }
                                if (userAppSettings.getCanFollow() != -1) {
                                    this.a.a("PREFERENCE_KEY_NOTIFICATION_FOLLOW_UPDATES", userAppSettings.getCanFollow() == 1, false);
                                }
                                if (!(userAppSettings.getDisplayLanguage() == null || userAppSettings.getDisplayLanguage().equalsIgnoreCase(f.b(Util.C)))) {
                                    final String displayLanguage = userAppSettings.getDisplayLanguage();
                                    if (ai.a() != null) {
                                        aj.a().a(ai.a(), displayLanguage, Util.C.getResources().getString(R.string.toast_switch_display_lang), new com.managers.aj.a() {
                                            public void onSet() {
                                                Util.h(displayLanguage);
                                            }

                                            public void onDismiss() {
                                                Util.i(f.b(Util.C));
                                                Util.b(HomeSettingsItemView.SETTINGS_TAG_DISPLAY_LANGUAGE, f.b(Util.C));
                                            }
                                        });
                                    } else {
                                        this.a.a("UPDATE_DISP_LANG", displayLanguage, true);
                                    }
                                }
                                if (userAppSettings.getDownloadQuality() != -1) {
                                    int b2 = this.a.b("PREFERENCE_KEY_SYNC_QUALITY", 1, true);
                                    if (userAppSettings.getDownloadQuality() == 2 && b2 != 2) {
                                        if (ap.a().i()) {
                                            u.a().a("HD_Update", "Auto", "Trial Users");
                                        }
                                        if (ap.a().d()) {
                                            u.a().a("HD_Update", "Auto", "Gaana Plus Users");
                                        }
                                        if (ai.a() != null) {
                                            aj.a().a(ai.a(), Util.C.getString(R.string.change_now), Util.C.getString(R.string.server_hd_update_msg), new com.managers.aj.b() {
                                                public void undoSnackBar() {
                                                    try {
                                                        Bundle bundle = new Bundle();
                                                        bundle.putInt("KEY_SETTINGS", 1);
                                                        bundle.putBoolean("NOT_DOWNLOAD", false);
                                                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                                                        settingsDetailFragment.setArguments(bundle);
                                                        ((GaanaActivity) ai.a()).displayFragment(settingsDetailFragment);
                                                    } catch (Exception unused) {
                                                    }
                                                }
                                            });
                                        }
                                    }
                                    this.a.a("PREFERENCE_KEY_SYNC_QUALITY", userAppSettings.getDownloadQuality(), true);
                                }
                            }
                        }
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
            }, -1);
        }
    }

    public static void h(final String str) {
        GaanaApplication.getInstance();
        GaanaApplication.setLanguage(C, str, new aa() {
            public void onFontRetrieved(Typeface typeface) {
                Util.i(str);
                Configuration configuration = Util.C.getResources().getConfiguration();
                new com.constants.b(Util.C, configuration.locale, configuration.locale).a(f.a(str));
                if (!TextUtils.isEmpty(str)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("RTlang:");
                    stringBuilder.append(str);
                    q.a().a("int", stringBuilder.toString());
                }
                d.a().b("PREFERENCE_DYNAMIC_VIEW_FETCH_TIME", false);
                d.a().b("PREFERENCE_DYNAMIC_VIEW_FETCH_DATA", false);
                com.i.j.a().c().d().a();
                o.a().b();
                if (GaanaApplication.getInstance().getCurrentUser() != null && GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                    f.a().b();
                    PlaylistSyncManager.getInstance().syncOnLogin();
                }
                if (Util.j(Util.C)) {
                    com.i.j.a().a("https://apiv2.gaana.com/radio/metadata");
                    DynamicViewManager.a().a(new y() {
                        public void OnDynamicViewDataFetched() {
                            Intent intent = new Intent(Util.C, GaanaActivity.class);
                            intent.setFlags(335544320);
                            Util.C.startActivity(intent);
                        }
                    }, Util.C);
                }
            }

            public void onError(String str) {
                Toast.makeText(Util.C, "There is some problem applying this language", 1).show();
            }
        });
    }

    public static void i(String str) {
        String replace = "https://api.gaana.com/user.php?type=set_user_language_setting&display_language=<display_language>".replace("<display_language>", str);
        UserInfo currentUser = ((GaanaApplication) GaanaApplication.getContext()).getCurrentUser();
        if (currentUser != null && currentUser.getLoginStatus()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(replace);
            stringBuilder.append("&token=");
            stringBuilder.append(currentUser.getAuthToken());
            replace = stringBuilder.toString();
        }
        URLManager uRLManager = new URLManager();
        uRLManager.a(String.class);
        uRLManager.a(replace);
        uRLManager.a(Priority.HIGH);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.i(false);
        if (!TextUtils.isEmpty(str)) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("DispLang:");
            stringBuilder2.append(str);
            q.a().a("ua", stringBuilder2.toString());
        }
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                try {
                    JSONObject jSONObject = new JSONObject((String) obj);
                    jSONObject.getString("message");
                    int i = jSONObject.getInt("status");
                } catch (Exception unused) {
                }
            }
        }, uRLManager);
    }

    public static String j(String str) {
        try {
            String l = l(GaanaApplication.getContext());
            int length = l.length();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("GENDC@092016");
            stringBuilder.append(l.substring(length - 4));
            byte[] d = new k(stringBuilder.toString()).d(str);
            if (d != null) {
                return new String(d);
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return null;
    }

    public static String k(String str) {
        if (str.contains("http") || str.contains("https")) {
            return str;
        }
        try {
            byte[] d = new k(Constants.bu).d(str);
            if (d != null) {
                String str2 = new String(d);
                if (str2.startsWith("http")) {
                    return str2.trim();
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return null;
    }

    public static String l(String str) {
        try {
            byte[] d = new k(Constants.bu).d(str);
            if (d != null) {
                String str2 = new String(d);
                if (str2.startsWith("http")) {
                    return str2.trim();
                }
                u.a().a("StreamingFailure", "Server - URL decoding failure", P());
            }
        } catch (Exception e) {
            u.a().a("StreamingFailure", "Server - URL decoding failure", P());
            ThrowableExtension.printStackTrace(e);
        }
        return null;
    }

    public static void m(String str) {
        L = str;
    }

    public static String n(String str) {
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        str2 = "akamai";
        if (str.equals("3")) {
            return "unisys";
        }
        if (str.equals("4")) {
            return "saregama";
        }
        return str.equals("8") ? "medianet" : str2;
    }

    public static String O() {
        String str = K;
        return "VideoStreamingFailure";
    }

    public static void o(String str) {
        K = str;
    }

    public static String P() {
        String str = K;
        String str2 = "";
        if (PlayerManager.a(GaanaApplication.getContext()).i() != null) {
            str2 = PlayerManager.a(GaanaApplication.getContext()).i().h();
        }
        if (com.managers.ad.a(GaanaApplication.getContext()).o().booleanValue()) {
            str2 = com.managers.ad.a(GaanaApplication.getContext()).s();
        }
        String str3 = "Online";
        if (!TextUtils.isEmpty(str)) {
            StringBuilder stringBuilder;
            if (!TextUtils.isEmpty(L)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str3);
                stringBuilder.append(" - ");
                stringBuilder.append(L);
                str3 = stringBuilder.toString();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(str3);
            stringBuilder.append(" - ");
            stringBuilder.append(str2);
            stringBuilder.append(" - ");
            stringBuilder.append(str);
            return stringBuilder.toString();
        } else if (com.managers.ad.a(GaanaApplication.getContext()).o().booleanValue()) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("RadioMirchi");
            stringBuilder2.append(" - ");
            stringBuilder2.append(str2);
            return stringBuilder2.toString();
        } else {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Download - ");
            stringBuilder3.append(str2);
            return stringBuilder3.toString();
        }
    }

    public static String Q() {
        String str = "-1";
        switch (d.a().b("PREFERENCE_KEY_SYNC_QUALITY", 1, true)) {
            case 0:
                return "medium";
            case 1:
                return "high";
            case 2:
                return "extreme";
            default:
                return str;
        }
    }

    public static String R() {
        return System.getProperty("http.agent");
    }

    public static void t(final Context context) {
        if (context.getSharedPreferences("DO_NOT_SHOW_AGAIN", 0).getBoolean("DO_NOT_SHOW_AGAIN", false)) {
            E(context);
        } else {
            new CustomDialogView(context, context.getResources().getString(R.string.equalizer_warning_message), new OnCheckBoxDialogButtonClickListener() {
                public void onNegativeButtonClick() {
                }

                public void onPositiveButtonClick(boolean z) {
                    Editor edit = context.getSharedPreferences("DO_NOT_SHOW_AGAIN", 0).edit();
                    edit.putBoolean("DO_NOT_SHOW_AGAIN", z);
                    edit.commit();
                    Util.E(context);
                }
            }, false).show();
        }
    }

    private static void E(Context context) {
        String str = "Player";
        Intent intent = new Intent("android.media.action.DISPLAY_AUDIO_EFFECT_CONTROL_PANEL");
        intent.putExtra("android.media.extra.PACKAGE_NAME", BuildConfig.APPLICATION_ID);
        try {
            intent.putExtra("android.media.extra.AUDIO_SESSION", GaanaMusicService.s().getAudioSessionId());
        } catch (Exception unused) {
        }
        BaseActivity baseActivity = (BaseActivity) context;
        if (baseActivity.currentScreen.startsWith("SettingsScreen")) {
            str = "Settings";
        }
        try {
            ((Activity) context).startActivityForResult(intent, 709);
            ((BaseActivity) context).sendGAEvent(str, "Equalizer", "Open");
        } catch (Exception unused2) {
            baseActivity.sendGAEvent(str, "Equalizer", "Exception");
            aj.a().a(context, context.getString(R.string.equalizer_unavailable));
        }
    }

    public static View a(Context context, InAppMessage inAppMessage) {
        try {
            JSONObject jSONObject = new JSONObject(inAppMessage.content);
            int b = b(jSONObject, "type");
            ConcurrentHashMap concurrentHashMap = GaanaApplication.getInstance().inAppShownList;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("user_feedback");
            stringBuilder.append(b);
            Long l = (Long) concurrentHashMap.get(stringBuilder.toString());
            if (b >= 0 && b < 4 && (l == null || System.currentTimeMillis() - l.longValue() > 900000)) {
                UserFeedbackDialog userFeedbackDialog = new UserFeedbackDialog(context, inAppMessage, b, a(jSONObject, "question"), a(jSONObject, "yes"), a(jSONObject, "no"), a(jSONObject, FacebookAdapter.KEY_SUBTITLE_ASSET));
                if ((context instanceof Activity) && !((Activity) context).isFinishing()) {
                    userFeedbackDialog.show();
                }
            }
        } catch (JSONException unused) {
        }
        return null;
    }

    public static String a(JSONObject jSONObject, String str) {
        try {
            if (jSONObject.has(str)) {
                return jSONObject.getString(str);
            }
        } catch (JSONException unused) {
        }
        return null;
    }

    public static int b(JSONObject jSONObject, String str) {
        try {
            if (jSONObject.has(str)) {
                return jSONObject.getInt(str);
            }
        } catch (JSONException unused) {
        }
        return -1;
    }

    public static void b(final String str, final String str2, final String str3) {
        com.i.d.a(new Runnable() {
            public void run() {
                String c = Util.d(str, str2, str3);
                try {
                    AbstractHttpMessage httpPost = new HttpPost("https://ga.gaana.com/ga");
                    StringEntity stringEntity = new StringEntity(c, "UTF-8");
                    stringEntity.setContentType("application/json");
                    httpPost.setEntity(stringEntity);
                    new j().a(httpPost);
                    new DefaultHttpClient().execute(httpPost);
                } catch (Exception unused) {
                }
            }
        });
    }

    private static String d(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.accumulate("stream_url", str);
            Object h = PlayerManager.a(GaanaApplication.getContext()).i().h();
            if (com.managers.ad.a(GaanaApplication.getContext()).o().booleanValue()) {
                h = com.managers.ad.a(GaanaApplication.getContext()).s();
            }
            jSONObject.accumulate("item_id", h);
            jSONObject.accumulate("content_source", L);
            h = !TextUtils.isEmpty(K) ? "Online" : com.managers.ad.a(GaanaApplication.getContext()).o().booleanValue() ? "RadioMirchi" : "Download";
            jSONObject.accumulate("play_mode", h);
            jSONObject.accumulate("stream_quality", K);
            jSONObject.accumulate(NativeProtocol.BRIDGE_ARG_ERROR_TYPE, str2);
            jSONObject.accumulate(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, str3);
        } catch (Exception unused) {
        }
        return jSONObject.toString();
    }

    public static String p(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (str.replace(" ", "").length() > 14) {
            str = str.replaceFirst(" ", "\n");
        }
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            stringBuilder.append(charAt);
            i++;
            if (i < str.length() && charAt != 10) {
                stringBuilder.append(" ");
            }
        }
        SpannableString spannableString = new SpannableString(stringBuilder.toString());
        length = stringBuilder.toString().length();
        i = 1;
        if (length > 1) {
            while (i < length) {
                spannableString.setSpan(new ScaleXSpan(0.104499996f), i, i + 1, 33);
                i += 2;
            }
        }
        return stringBuilder.toString();
    }

    public static String a(BusinessObjectType businessObjectType) {
        if (businessObjectType == null) {
            return e.ab;
        }
        switch (businessObjectType) {
            case Tracks:
                return "song";
            case Artists:
                return "artist";
            case Albums:
                return "album";
            case Playlists:
                return "playlist";
            case Radios:
                return "radio";
            default:
                return e.ab;
        }
    }

    public static String b(BusinessObjectType businessObjectType) {
        if (businessObjectType == null) {
            return "";
        }
        switch (businessObjectType) {
            case Tracks:
                return "song";
            case Artists:
                return "artist";
            case Albums:
                return "album";
            case Playlists:
                return "playlist";
            case Radios:
                return "radio";
            default:
                return "";
        }
    }

    public static String c(BusinessObjectType businessObjectType) {
        switch (businessObjectType) {
            case Tracks:
                return "t";
            case Artists:
                return "a";
            case Albums:
                return "A";
            case Playlists:
                return TtmlNode.TAG_P;
            case Radios:
                return com.managers.ad.a(GaanaApplication.getContext()).o().booleanValue() ? "RM" : "RL";
            case Occasion:
                return "O";
            default:
                return "";
        }
    }

    public static String S() {
        if (com.managers.ad.a(GaanaApplication.getContext()).o().booleanValue()) {
            return "RM";
        }
        String str = "";
        switch (d.a().b("PREFERENCE_KEY_STREAMING_QUALITY", Constants.s(), false)) {
            case 10000:
                str = "LOW";
                break;
            case 10001:
                str = "MEDIUM";
                break;
            case 10002:
                str = "HIGH";
                break;
            case 10003:
                str = "HD";
                break;
            case 10004:
                str = "AUTOMATIC";
                break;
        }
        return str;
    }

    public static Class<?> d(BusinessObjectType businessObjectType) {
        Class<?> cls = String.class;
        if (businessObjectType == null) {
            return cls;
        }
        switch (businessObjectType) {
            case Tracks:
                cls = Tracks.class;
                break;
            case Artists:
                cls = Artists.class;
                break;
            case Albums:
                cls = Albums.class;
                break;
            case Playlists:
            case Charts:
            case TopCharts:
                cls = Playlists.class;
                break;
            case Radios:
                cls = Radios.class;
                break;
            case GenericItems:
                cls = Items.class;
                break;
            case Products:
                cls = Products.class;
                break;
            case Notifications:
                cls = Notifications.class;
                break;
            case CampaignPromo:
                cls = CampaignPromo.class;
                break;
            case AppDetails:
                cls = AppDetails.class;
                break;
            case TrendingSearches:
                cls = TrendingSearches.class;
                break;
            case User:
                cls = User.class;
                break;
            case Friends:
                cls = Friends.class;
                break;
            case Geners:
                cls = Genres.class;
                break;
            case Activities:
                cls = UserActivities.class;
                break;
            case Discover:
                cls = Items.class;
                break;
            case ProfileUsers:
                cls = ProfileUsers.class;
                break;
            case BasicResponse:
                cls = BasicResponse.class;
                break;
            case RadioMoods:
                cls = RadioMoods.class;
                break;
            case DynamicViews:
                cls = com.dynamicview.f.class;
                break;
            case UberResponse:
                cls = Uber.class;
                break;
            case SubscriptionTrialCard:
                cls = SubscriptionTrialCard.class;
                break;
            case SubscriptionCard:
                cls = SubscriptionCard.class;
                break;
            case FavoriteData:
                cls = FavoriteData.class;
                break;
            case PlaylistDetails:
                cls = PlaylistDetail.class;
                break;
            case PersonaDedications:
                cls = PersonaDedications.class;
                break;
            case SocialFeed:
                cls = SocialFeed.class;
                break;
            case HomeAction:
                cls = HomeAction.class;
                break;
            case BankCodes:
                cls = BankCodeList.class;
                break;
            case CouponProducts:
                cls = CouponProducts.class;
                break;
            case TrialProductFeature:
                cls = TrialProductFeature.class;
                break;
            case CountryData:
                cls = CountryData.class;
                break;
            case DeviceList:
                cls = DeviceList.class;
                break;
            case FavoriteOccasions:
                cls = FavoriteOccasions.class;
                break;
            case PayUHashes:
                cls = PayUHash.class;
                break;
            case DeleteHash:
                cls = DeleteHash.class;
                break;
            case IssueBankHash:
                cls = IssueBankHash.class;
                break;
            case DynamicViewSections:
                cls = com.dynamicview.e.class;
                break;
            case DynamicViewCategories:
                cls = com.dynamicview.d.class;
                break;
            case JukePlaylist:
                cls = JukePlaylist.class;
                break;
            case JukePlayLists:
                cls = JukePlaylists.class;
                break;
            case PreScreens:
                cls = PreScreens.class;
                break;
        }
        return cls;
    }

    public static ColorStateList b(boolean z) {
        if (M == null) {
            F(GaanaApplication.getContext());
        }
        return M;
    }

    private static void F(Context context) {
        M = ColorStateList.valueOf(context.getResources().getColor(R.color.media_item_icon_playing));
    }

    public static void a(b bVar) {
        g = bVar;
    }

    public static boolean u(Context context) {
        return YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(context) == YouTubeInitializationResult.SUCCESS;
    }

    public static boolean T() {
        return !TextUtils.isEmpty(System.getProperty("ro.yunos.version"));
    }

    public static void a(String str, String str2, com.services.f fVar, com.services.f.b bVar, IOnLoginCompleted iOnLoginCompleted, Activity activity, boolean z, com.services.f.b bVar2) {
        final Context context = activity;
        a(context, context.getString(R.string.loading));
        final String str3 = str;
        final String str4 = str2;
        final boolean z2 = z;
        final com.services.f.b bVar3 = bVar;
        final com.services.f fVar2 = fVar;
        final IOnLoginCompleted iOnLoginCompleted2 = iOnLoginCompleted;
        final com.services.f.b bVar4 = bVar2;
        h.a().a(new TaskListner() {
            private String i = "";
            private String j = "";

            public void onBackGroundTaskCompleted() {
                Util.b();
                if (this.i != null && this.i.equalsIgnoreCase("1")) {
                    GooglePlusLogin.getInstance().onEmailSaveClicked(str3, str4, context);
                    if (z2) {
                        Util.b(str3, context, bVar3, fVar2);
                    } else {
                        Util.a(str3, str4, iOnLoginCompleted2, context, LoginManager.getInstance().getDefaultLoginMode());
                    }
                } else if (this.i != null && this.i.equalsIgnoreCase("0")) {
                    ((Login) context).setSignupEmailPwd(str3, str4);
                    fVar2.a("", GaanaApplication.getContext().getString(R.string.email_not_registered), Boolean.valueOf(true), bVar3);
                } else if (this.i != null && this.i.equalsIgnoreCase(InternalAvidAdSessionContext.AVID_API_LEVEL)) {
                    fVar2.a("", GaanaApplication.getContext().getString(R.string.already_login_with_phone), Boolean.valueOf(false), bVar4);
                } else if (this.i != null && this.i.equalsIgnoreCase("99")) {
                    LoginMode defaultLoginMode = LoginManager.getInstance().getDefaultLoginMode();
                    if (defaultLoginMode == LoginMode.SSO) {
                        defaultLoginMode = LoginMode.GAANA_SSO_NOT_VERIFIED;
                    }
                    Util.a(str3, str4, iOnLoginCompleted2, context, defaultLoginMode);
                } else if (this.i != null && this.i.equalsIgnoreCase("3")) {
                    fVar2.a(TextUtils.isEmpty(this.j) ? GaanaApplication.getContext().getString(R.string.invalid_email_id) : this.j);
                }
            }

            public void doBackGroundTask() {
                try {
                    this.i = a(str3);
                } catch (ClientProtocolException e) {
                    ThrowableExtension.printStackTrace(e);
                } catch (IOException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
            }

            public String a(String str) throws ClientProtocolException, IOException {
                String a = Util.a(Util.b(str), Constants.bv);
                List arrayList = new ArrayList();
                arrayList.add(new BasicNameValuePair("email", str));
                arrayList.add(new BasicNameValuePair("st", a));
                str = new j().a("https://api.gaana.com/user/email-exists", arrayList);
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                a = ap.a().c(str);
                if (!TextUtils.isEmpty(a) && a.equalsIgnoreCase("3")) {
                    this.j = ap.a().b(str);
                }
                return a;
            }
        }, -1);
    }

    public static void a(String str, String str2, IOnLoginCompleted iOnLoginCompleted, Activity activity, LoginMode loginMode) {
        MoEngage.getInstance().reportLoginStarted("GAANA");
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setLoginType(LoginType.GAANA);
        loginInfo.setEmailId(str);
        loginInfo.setPassword(str2);
        loginInfo.setLoginMode(loginMode);
        LoginManager.getInstance().login(activity, loginInfo, iOnLoginCompleted);
    }

    private static void b(final String str, final Activity activity, final com.services.f.b bVar, final com.services.f fVar) {
        a((Context) activity, "Loading....");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/user.php?type=forgotpassword&email=");
        stringBuilder.append(str);
        String stringBuilder2 = stringBuilder.toString();
        UserInfo currentUser = GaanaApplication.getInstance().getCurrentUser();
        if (!(currentUser == null || !currentUser.getLoginStatus() || stringBuilder2.contains(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE))) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append("&token=");
            stringBuilder3.append(currentUser.getAuthToken());
            stringBuilder2 = stringBuilder3.toString();
        }
        URLManager uRLManager = new URLManager();
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(stringBuilder2);
        uRLManager.a(String.class);
        uRLManager.a(Priority.HIGH);
        uRLManager.i(false);
        if (j((Context) activity)) {
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                /* JADX WARNING: Removed duplicated region for block: B:15:0x004b  */
                /* JADX WARNING: Removed duplicated region for block: B:14:0x0045  */
                /* JADX WARNING: Removed duplicated region for block: B:14:0x0045  */
                /* JADX WARNING: Removed duplicated region for block: B:15:0x004b  */
                public void onRetreivalComplete(java.lang.Object r7) {
                    /*
                    r6 = this;
                    r0 = "-1";
                    r1 = "";
                    r7 = (java.lang.String) r7;
                    if (r7 == 0) goto L_0x0026;
                L_0x0008:
                    r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0021 }
                    r2.<init>(r7);	 Catch:{ JSONException -> 0x0021 }
                    r7 = "Status";
                    r7 = r2.getString(r7);	 Catch:{ JSONException -> 0x0021 }
                    r0 = "Error";
                    r0 = r2.getString(r0);	 Catch:{ JSONException -> 0x001c }
                    r1 = r0;
                    r0 = r7;
                    goto L_0x003a;
                L_0x001c:
                    r0 = move-exception;
                    r5 = r0;
                    r0 = r7;
                    r7 = r5;
                    goto L_0x0022;
                L_0x0021:
                    r7 = move-exception;
                L_0x0022:
                    com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r7);
                    goto L_0x003a;
                L_0x0026:
                    r7 = com.managers.aj.a();
                    r2 = r5;
                    r3 = com.gaana.application.GaanaApplication.getContext();
                    r4 = 2131822373; // 0x7f110725 float:1.9277516E38 double:1.0532601975E-314;
                    r3 = r3.getString(r4);
                    r7.a(r2, r3);
                L_0x003a:
                    com.utilities.Util.b();
                    r7 = "1";
                    r7 = r0.equals(r7);
                    if (r7 == 0) goto L_0x004b;
                L_0x0045:
                    r7 = r5;
                    com.utilities.Util.c(r7);
                    goto L_0x007f;
                L_0x004b:
                    r7 = "0";
                    r7 = r0.equals(r7);
                    if (r7 == 0) goto L_0x007f;
                L_0x0053:
                    r7 = "Email not exists";
                    r7 = r1.equalsIgnoreCase(r7);
                    if (r7 == 0) goto L_0x007f;
                L_0x005b:
                    r7 = r5;
                    r7 = (com.gaana.Login) r7;
                    r0 = r4;
                    r1 = "";
                    r7.setSignupEmailPwd(r0, r1);
                    r7 = r7;
                    r0 = "";
                    r1 = com.gaana.application.GaanaApplication.getContext();
                    r2 = 2131821186; // 0x7f110282 float:1.9275108E38 double:1.053259611E-314;
                    r1 = r1.getString(r2);
                    r2 = 1;
                    r2 = java.lang.Boolean.valueOf(r2);
                    r3 = r6;
                    r7.a(r0, r1, r2, r3);
                L_0x007f:
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.utilities.Util$AnonymousClass20.onRetreivalComplete(java.lang.Object):void");
                }
            }, uRLManager);
        }
    }

    private static void c(Activity activity) {
        new CustomMaterialDialogView(activity, activity.getString(R.string.reset_password), activity.getResources().getString(R.string.forgot_password_success_message), activity.getResources().getString(R.string.forget_password_bottom_success_message)).show();
    }

    public static boolean v(Context context) {
        if (!Constants.T || !j(context)) {
            return true;
        }
        Intent intent = new Intent(context, OnBoardLanguagePreferenceActivityNew.class);
        intent.setFlags(603979776);
        context.startActivity(intent);
        return false;
    }

    public static BusinessObject a(Item item) {
        if (item.isFlatBufferResponse()) {
            return m(item);
        }
        Artist artist = new Artist();
        artist.setBusinessObjType(BusinessObjectType.Artists);
        artist.setSeokey(item.getSeokey());
        artist.setName(item.getRawName());
        artist.setLanguage(item.getLanguage());
        artist.setArtwork(item.getArtwork());
        artist.setAtw(item.getAtw());
        artist.setBusinessObjId(item.getEntityId());
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((EntityInfo) arrayList.get(i)).getKey().equals("songs")) {
                    artist.setSongsCount((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("albums")) {
                    artist.setAlbumsCount((String) ((EntityInfo) arrayList.get(i)).getValue());
                }
            }
        }
        return artist;
    }

    public static BusinessObject b(Item item) {
        if (item.isFlatBufferResponse()) {
            return n(item);
        }
        Playlist playlist = new Playlist();
        playlist.setBusinessObjType(BusinessObjectType.Playlists);
        playlist.setLanguage(item.getLanguage());
        playlist.setSeokey(item.getSeokey());
        playlist.setName(item.getRawName());
        playlist.setArtwork(item.getArtwork());
        playlist.setAtw(item.getAtw());
        playlist.setPlaylistId(item.getEntityId());
        playlist.setFavoriteCount(Long.toString(item.getFavoriteCount()));
        playlist.setPremiumContent(item.getPremiumContent());
        playlist.setSapID(item.getSapID());
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((EntityInfo) arrayList.get(i)).getKey().equals("ad_code")) {
                    playlist.setChannelPageAdCode((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("created_by")) {
                    playlist.setCreatedby((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("created_by_user_id")) {
                    playlist.setCreatedbyUserId((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("modified_on")) {
                    playlist.setLastModifiedDate(new Date(Long.parseLong((String) ((EntityInfo) arrayList.get(i)).getValue())));
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("detailed_description")) {
                    playlist.setPlaylistDetailedDescription((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("lpid")) {
                    playlist.setLocalPlaylistId((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("track_id")) {
                    playlist.setTrackIds((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("notify_status")) {
                    playlist.setNotifyStatus(((Double) ((EntityInfo) arrayList.get(i)).getValue()).intValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("parental_warning")) {
                    playlist.setParentalWarning(Double.compare(((Double) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue(), 1.0d) == 0 ? 1 : 0);
                }
            }
        }
        return playlist;
    }

    public static BusinessObject a(GenericEntity genericEntity) {
        Album album = new Album();
        album.setBusinessObjType(BusinessObjectType.Albums);
        album.setLanguage(genericEntity.getLanguage());
        album.setSeokey(genericEntity.getSeokey());
        album.setName(genericEntity.getRawName());
        album.setArtwork(genericEntity.getArtwork());
        album.setAtw(genericEntity.getAtw());
        album.setBusinessObjId(genericEntity.getEntityId());
        album.setFavoriteCount(Long.toString(genericEntity.getFavoriteCount()));
        album.setPremiumContent(genericEntity.getPremiumContent());
        GenericEntityInfo generic_entity_info = genericEntity.getGeneric_entity_info();
        if (generic_entity_info != null) {
            ArrayList primaryartist = generic_entity_info.getPrimaryartist();
            if (primaryartist != null && primaryartist.size() > 0) {
                album.setPrimaryartist(primaryartist);
            }
            album.setParentalWarning(generic_entity_info.getParental_warning());
        }
        return album;
    }

    public static BusinessObject c(Item item) {
        if (item.isFlatBufferResponse()) {
            return h(item);
        }
        Album album = new Album();
        album.setBusinessObjType(BusinessObjectType.Albums);
        album.setLanguage(item.getLanguage());
        album.setSeokey(item.getSeokey());
        album.setName(item.getRawName());
        album.setArtwork(item.getArtwork());
        album.setAtw(item.getAtw());
        album.setBusinessObjId(item.getEntityId());
        album.setFavoriteCount(Long.toString(item.getFavoriteCount()));
        album.setPremiumContent(item.getPremiumContent());
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((EntityInfo) arrayList.get(i)).getKey().equals("ad_code")) {
                    album.setChannelPageAdCode((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("primaryartist")) {
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = (ArrayList) ((EntityInfo) arrayList.get(i)).getValue();
                    for (int i2 = 0; i2 < arrayList3.size(); i2++) {
                        Object obj = arrayList3.get(i);
                        Gson create = new GsonBuilder().create();
                        arrayList2.add((Album.Artist) create.fromJson(create.toJson(obj), Album.Artist.class));
                    }
                    album.setPrimaryartist(arrayList2);
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("parental_warning")) {
                    try {
                        album.setParentalWarning(Double.compare(((Double) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue(), 1.0d) == 0 ? 1 : 0);
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return album;
    }

    public static BusinessObject d(Item item) {
        if (item.isFlatBufferResponse()) {
            return i(item);
        }
        Radio radio = new Radio();
        radio.setBusinessObjType(BusinessObjectType.Radios);
        radio.setLanguage(item.getLanguage());
        radio.setSeokey(item.getSeokey());
        radio.setName(item.getRawName());
        radio.setArtwork(item.getArtwork());
        radio.setAtw(item.getAtw());
        radio.setBusinessObjId(item.getEntityId());
        radio.setFavoriteCount(Long.toString(item.getFavoriteCount()));
        radio.setType(item.getEntityType());
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                String str;
                if (((EntityInfo) arrayList.get(i)).getKey().equals("ad_code")) {
                    radio.setChannelPageAdCode((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("stream_url")) {
                    radio.setStreamUrl((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("ads_position")) {
                    Double d = (Double) ((EntityInfo) arrayList.get(i)).getValue();
                    if (d != null) {
                        radio.setAdCompaignPosition(d.intValue());
                    }
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("poll_api")) {
                    str = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    if (str != null) {
                        radio.setPoll_api(str);
                    }
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("poll_time")) {
                    str = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    if (str != null) {
                        radio.setPoll_time(String.valueOf(str));
                    }
                }
            }
        }
        return radio;
    }

    public static BusinessObject e(Item item) {
        if (item.isFlatBufferResponse()) {
            return j(item);
        }
        DiscoverTag discoverTag = new DiscoverTag();
        if (item.getEntityType() == null) {
            discoverTag.setBusinessObjType(BusinessObjectType.Discover);
        } else if (item.getEntityType().equals(c.a)) {
            discoverTag.setBusinessObjType(BusinessObjectType.Playlists);
        } else if (item.getEntityType().equals(c.b)) {
            discoverTag.setBusinessObjType(BusinessObjectType.Albums);
        } else if (item.getEntityType().equals(com.constants.c.d.c) || item.getEntityType().equals(com.constants.c.d.d)) {
            discoverTag.setBusinessObjType(BusinessObjectType.Radios);
        } else if (item.getEntityType().equals(c.c)) {
            discoverTag.setBusinessObjType(BusinessObjectType.Tracks);
        }
        discoverTag.setTagEntityType(item.getEntityType());
        discoverTag.setName(item.getRawName());
        discoverTag.setLanguage(item.getLanguage());
        discoverTag.setArtwork(item.getArtwork());
        discoverTag.setAtw(item.getAtw());
        discoverTag.setBusinessObjId(item.getEntityId());
        discoverTag.setFavoriteCount(Long.toString(item.getFavoriteCount()));
        return discoverTag;
    }

    public static BusinessObject f(Item item) {
        if (item.isFlatBufferResponse()) {
            return k(item);
        }
        YouTubeVideo youTubeVideo = new YouTubeVideo();
        youTubeVideo.setBusinessObjType(BusinessObjectType.YouTubeVideos);
        youTubeVideo.d(item.getArtwork());
        youTubeVideo.setAtw(item.getAtw());
        youTubeVideo.setName(item.getRawName());
        youTubeVideo.b(item.getRawName());
        youTubeVideo.setLanguage(item.getLanguage());
        youTubeVideo.setBusinessObjId(item.getEntityId());
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        String str = "";
        String str2 = "";
        String str3 = "";
        CharSequence charSequence = "";
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null) {
            int size = arrayList.size();
            String str4 = charSequence;
            String str5 = str3;
            str3 = str2;
            str2 = str;
            for (int i = 0; i < size; i++) {
                ArrayList arrayList3;
                int i2;
                if (((EntityInfo) arrayList.get(i)).getKey().equals("artist")) {
                    arrayList3 = (ArrayList) ((EntityInfo) arrayList.get(i)).getValue();
                    if (arrayList3 != null && arrayList3.size() > 0) {
                        for (i2 = 0; i2 < arrayList3.size(); i2++) {
                            Object obj = arrayList3.get(i2);
                            Gson create = new GsonBuilder().create();
                            arrayList2.add((Track.Artist) create.fromJson(create.toJson(obj), Track.Artist.class));
                        }
                        youTubeVideo.a(arrayList2);
                    }
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("album")) {
                    arrayList3 = (ArrayList) ((EntityInfo) arrayList.get(i)).getValue();
                    if (arrayList3 != null && arrayList3.size() > 0) {
                        for (i2 = 0; i2 < arrayList3.size(); i2++) {
                            Map map = (Map) arrayList3.get(i2);
                            youTubeVideo.f((String) map.get("album_id"));
                            youTubeVideo.g((String) map.get("name"));
                        }
                    }
                }
                if (((EntityInfo) arrayList.get(i)).getKey().equals("horz_vd")) {
                    str3 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("vert_vd")) {
                    str2 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("url")) {
                    str5 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("vid_id")) {
                    str4 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    youTubeVideo.c(str4);
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("video_id")) {
                    str4 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    youTubeVideo.setBusinessObjId(str4);
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("vd_expiry")) {
                    youTubeVideo.e((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("v_cnt_src")) {
                    youTubeVideo.a(((Double) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("h_cnt_src")) {
                    youTubeVideo.b(((Double) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue());
                }
            }
            str = str2;
            str2 = str3;
            str3 = str5;
            charSequence = str4;
        }
        if (!TextUtils.isEmpty(str)) {
            youTubeVideo.a(str);
            youTubeVideo.a(1);
        } else if (!TextUtils.isEmpty(str2)) {
            youTubeVideo.a(str2);
            youTubeVideo.a(2);
        } else if (!TextUtils.isEmpty(str3)) {
            youTubeVideo.a(str3);
            youTubeVideo.a(0);
        } else if (!TextUtils.isEmpty(charSequence)) {
            youTubeVideo.c(charSequence);
            youTubeVideo.a(0);
        }
        return youTubeVideo;
    }

    public static BusinessObject g(Item item) {
        if (item.isFlatBufferResponse()) {
            return l(item);
        }
        Track track = new Track();
        track.setBusinessObjType(BusinessObjectType.Tracks);
        track.setLanguage(item.getLanguage());
        track.setSeokey(item.getSeokey());
        track.setName(item.getRawName());
        track.setArtwork(item.getArtwork());
        track.setAtw(item.getAtw());
        track.setBusinessObjId(item.getEntityId());
        track.setArtworkLarge(r(item.getArtwork()));
        track.setPremiumContent(item.getPremiumContent());
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ArrayList arrayList3;
                int i2;
                if (((EntityInfo) arrayList.get(i)).getKey().equals("artist")) {
                    arrayList3 = (ArrayList) ((EntityInfo) arrayList.get(i)).getValue();
                    if (arrayList3 != null && arrayList3.size() > 0) {
                        for (i2 = 0; i2 < arrayList3.size(); i2++) {
                            Object obj = arrayList3.get(i2);
                            Gson create = new GsonBuilder().create();
                            arrayList2.add((Track.Artist) create.fromJson(create.toJson(obj), Track.Artist.class));
                        }
                        track.setArtist(arrayList2);
                    }
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("album")) {
                    arrayList3 = (ArrayList) ((EntityInfo) arrayList.get(i)).getValue();
                    if (arrayList3 != null && arrayList3.size() > 0) {
                        for (i2 = 0; i2 < arrayList3.size(); i2++) {
                            Map map = (Map) arrayList3.get(i2);
                            track.setAlbumId((String) map.get("album_id"));
                            track.setAlbumName((String) map.get("name"));
                        }
                    }
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("stream_url")) {
                    try {
                        Object value = ((EntityInfo) arrayList.get(i)).getValue();
                        Gson create2 = new GsonBuilder().create();
                        track.setStreamUrls((StreamUrls) create2.fromJson(create2.toJson(value), StreamUrls.class));
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("lyrics_url")) {
                    track.setLyricsUrl((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("lyrics_type")) {
                    track.setLyricsType((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (!((EntityInfo) arrayList.get(i)).getKey().equals("artwork_large")) {
                    if (((EntityInfo) arrayList.get(i)).getKey().equals("country")) {
                        track.setLocationAvailability((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("mobile")) {
                        track.setDeviceAvailability((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("stream_type")) {
                        track.setStreamType((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("duration")) {
                        track.setDuration((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("isrc")) {
                        track.setIsrc((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("is_most_popular")) {
                        track.setIsMostPopular(Double.toString(((Double) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue()));
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("is_local")) {
                        track.setIsLocal((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("parental_warning")) {
                        track.setParentalWarning(Double.compare(((Double) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue(), 1.0d) == 0 ? 1 : 0);
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("youtube_id")) {
                        track.setYoutubeId((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("video_id")) {
                        track.setVideoId((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("vgid")) {
                        track.setVgid((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("play_ct")) {
                        track.setPlayCount((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("vert_vd")) {
                        track.setVerticalUrl((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("horz_vd")) {
                        track.setHorizontalUrl((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("content_source")) {
                        if (((EntityInfo) arrayList.get(i)).getValue() instanceof String) {
                            track.setContentSource(Double.parseDouble((String) ((EntityInfo) arrayList.get(i)).getValue()));
                        } else {
                            track.setContentSource(((Double) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue());
                        }
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("clip_vd")) {
                        track.setClipVideoUrl((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("vd_expiry")) {
                        track.setVideoExpiryTime((String) ((EntityInfo) arrayList.get(i)).getValue());
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("download_enabled")) {
                        if (((EntityInfo) arrayList.get(i)).getValue() instanceof Double) {
                            track.setFreeDownloadEnabled(((Double) ((EntityInfo) arrayList.get(i)).getValue()).intValue());
                        } else {
                            track.setFreeDownloadEnabled(((Integer) ((EntityInfo) arrayList.get(i)).getValue()).intValue());
                        }
                    }
                }
            }
        }
        return track;
    }

    public static void a(Context context, BaseGaanaFragment baseGaanaFragment, BusinessObject businessObject, boolean z, DownloadPopupListener downloadPopupListener) {
        a(context, baseGaanaFragment, businessObject, z, downloadPopupListener, false);
    }

    public static void a(Context context, BaseGaanaFragment baseGaanaFragment, BusinessObject businessObject, boolean z, DownloadPopupListener downloadPopupListener, boolean z2) {
        if (!businessObject.isLocalMedia()) {
            URLManager a;
            if (businessObject.getBusinessObjType() == BusinessObjectType.Radios) {
                a = Constants.a(((Radio) businessObject).getType(), businessObject.getBusinessObjId(), false);
            } else {
                a = Constants.a(businessObject.getBusinessObjType(), businessObject.getBusinessObjId(), false);
            }
            if (a != null) {
                ((BaseActivity) context).showProgressDialog(Boolean.valueOf(true), context.getString(R.string.fetching_details));
                final Context context2 = context;
                final BaseGaanaFragment baseGaanaFragment2 = baseGaanaFragment;
                final DownloadPopupListener downloadPopupListener2 = downloadPopupListener;
                final boolean z3 = z;
                final boolean z4 = z2;
                i.a().a(new s() {
                    public void onRetreivalComplete(BusinessObject businessObject) {
                        ((BaseActivity) context2).hideProgressDialog();
                        if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                            PopupWindowView instance = PopupWindowView.getInstance(context2, baseGaanaFragment2);
                            if (downloadPopupListener2 != null) {
                                instance.setDownloadPopupListener(downloadPopupListener2);
                            }
                            instance.contextPopupWindow((BusinessObject) businessObject.getArrListBusinessObj().get(0), z3, false, false, z4);
                        }
                    }

                    public void onErrorResponse(BusinessObject businessObject) {
                        ((BaseActivity) context2).hideProgressDialog();
                    }
                }, a);
            }
        }
    }

    public static void a(final Context context, BaseGaanaFragment baseGaanaFragment, BusinessObject businessObject, boolean z, @NonNull final s sVar) {
        if (!businessObject.isLocalMedia()) {
            URLManager a;
            if (businessObject.getBusinessObjType() == BusinessObjectType.Radios) {
                a = Constants.a(((Radio) businessObject).getType(), businessObject.getBusinessObjId(), false);
            } else {
                a = Constants.a(businessObject.getBusinessObjType(), businessObject.getBusinessObjId(), false);
            }
            if (a != null) {
                if (context instanceof BaseActivity) {
                    ((BaseActivity) context).showProgressDialog(Boolean.valueOf(true), context.getString(R.string.fetching_details));
                }
                i.a().a(new s() {
                    public void onRetreivalComplete(BusinessObject businessObject) {
                        if (context instanceof BaseActivity) {
                            ((BaseActivity) context).hideProgressDialog();
                        }
                        sVar.onRetreivalComplete(businessObject);
                    }

                    public void onErrorResponse(BusinessObject businessObject) {
                        if (context instanceof BaseActivity) {
                            ((BaseActivity) context).hideProgressDialog();
                        }
                        sVar.onErrorResponse(businessObject);
                    }
                }, a);
            }
        }
    }

    public static CharSequence c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return str2;
        }
        str = Normalizer.normalize(str, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
        String toLowerCase = Normalizer.normalize(str2, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
        int indexOf = toLowerCase.indexOf(str);
        if (indexOf < 0) {
            return str2;
        }
        SpannableString spannableString = new SpannableString(str2);
        while (indexOf >= 0) {
            int min = Math.min(indexOf, str2.length());
            indexOf = Math.min(indexOf + str.length(), str2.length());
            spannableString.setSpan(new StyleSpan(1), min, indexOf, 33);
            indexOf = toLowerCase.indexOf(str, indexOf);
        }
        return spannableString;
    }

    public static boolean a(Activity activity) {
        if (activity == null) {
            return false;
        }
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity) == 0) {
            return true;
        }
        Toast.makeText(activity, "Plaease update your google play services", 0).show();
        return false;
    }

    public static void w(Context context) {
        if (!((Activity) context).isFinishing()) {
            DownloadManager.c().c(true);
            DownloadManager.c().d();
            aj.a().a(context, context.getString(R.string.resume_download_snackbar_msg), true);
        }
    }

    public static int U() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int b(int i) {
        return Math.round(((float) i) * (GaanaApplication.getContext().getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    public static int a(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public static int a(float f, Context context) {
        return (int) (f / (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f));
    }

    public static String V() {
        return Constants.l ? "white" : "black";
    }

    public static String f(long j) {
        double d = (double) j;
        String str = "";
        if (d >= 1000.0d) {
            if (d < 1000000.0d) {
                d /= 1000.0d;
                str = "K";
            } else if (d < 1.0E9d) {
                d /= 1000000.0d;
                str = "M";
            } else {
                d /= 1.0E9d;
                str = "B";
            }
        }
        if (d <= 1.0d || d >= 10.0d) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append((long) d);
            stringBuilder.append(str);
            return stringBuilder.toString();
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(decimalFormat.format(d));
        stringBuilder2.append(str);
        return stringBuilder2.toString();
    }

    public static String q(String str) {
        double parseLong = (double) Long.parseLong(str);
        str = "";
        if (parseLong >= 1000.0d) {
            if (parseLong < 1000000.0d) {
                parseLong /= 1000.0d;
                str = "K";
            } else if (parseLong < 1.0E9d) {
                parseLong /= 1000000.0d;
                str = "M";
            } else {
                parseLong /= 1.0E9d;
                str = "B";
            }
        }
        if (parseLong <= 1.0d || parseLong >= 10.0d) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append((long) parseLong);
            stringBuilder.append(str);
            return stringBuilder.toString();
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(decimalFormat.format(parseLong));
        stringBuilder2.append(str);
        return stringBuilder2.toString();
    }

    public static void b(Context context, String str) {
        new com.services.f(context).a(context.getString(R.string.gaana_text), context.getResources().getString(R.string.error_msg_feature_not_available_offline), Boolean.valueOf(true), context.getString(R.string.go_online_text), context.getString(R.string.dlg_msg_cancel), new com.services.f.b() {
            public void onCancelListner() {
            }

            public void onOkListner(String str) {
                d a = d.a();
                a.a("PREFERENCE_KEY_OFFLINE_MODE", false, false);
                a.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
                a.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
                GaanaApplication.getInstance().setAppInOfflineMode(false);
                DownloadManager.c().d();
            }
        });
    }

    public static String a(List<EntityInfo> list) {
        for (int i = 0; i < list.size(); i++) {
            if (((EntityInfo) list.get(i)).getKey().equalsIgnoreCase("ga_events")) {
                if (((EntityInfo) list.get(i)).getValue() != null) {
                    return ((EntityInfo) list.get(i)).getValue().toString();
                }
                return null;
            }
        }
        return null;
    }

    public static String a(Map<String, String> map) {
        return map.containsKey("ga_events") ? (String) map.get("ga_events") : null;
    }

    public static Boolean W() {
        return Boolean.valueOf(true);
    }

    public static Bitmap a(Context context, Bitmap bitmap) {
        return a(bitmap, 5);
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        Bitmap bitmap2 = bitmap;
        int i2 = i;
        if (bitmap2 == null) {
            return null;
        }
        bitmap2 = bitmap2.copy(bitmap.getConfig(), true);
        if (i2 < 1) {
            return null;
        }
        Bitmap bitmap3;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int[] iArr;
        int i12;
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        int i13 = width * height;
        int[] iArr2 = new int[i13];
        bitmap2.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i14 = width - 1;
        int i15 = height - 1;
        int i16 = (i2 + i2) + 1;
        int[] iArr3 = new int[i13];
        int[] iArr4 = new int[i13];
        int[] iArr5 = new int[i13];
        int[] iArr6 = new int[Math.max(width, height)];
        int i17 = (i16 + 1) >> 1;
        i17 *= i17;
        i13 = 256 * i17;
        int[] iArr7 = new int[i13];
        for (int i18 = 0; i18 < i13; i18++) {
            iArr7[i18] = i18 / i17;
        }
        int[][] iArr8 = (int[][]) Array.newInstance(int.class, new int[]{i16, 3});
        i17 = i2 + 1;
        i13 = 0;
        int i19 = 0;
        int i20 = 0;
        while (i13 < height) {
            bitmap3 = bitmap2;
            i3 = -i2;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
            i8 = 0;
            i9 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            while (i3 <= i2) {
                i10 = height;
                i11 = i15;
                i15 = iArr2[i19 + Math.min(i14, Math.max(i3, 0))];
                int[] iArr9 = iArr8[i3 + i2];
                iArr9[0] = (i15 & 16711680) >> 16;
                iArr9[1] = (i15 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr9[2] = i15 & 255;
                i15 = i17 - Math.abs(i3);
                i4 += iArr9[0] * i15;
                i5 += iArr9[1] * i15;
                i6 += iArr9[2] * i15;
                if (i3 > 0) {
                    i7 += iArr9[0];
                    i8 += iArr9[1];
                    i9 += iArr9[2];
                } else {
                    i21 += iArr9[0];
                    i22 += iArr9[1];
                    i23 += iArr9[2];
                }
                i3++;
                height = i10;
                i15 = i11;
            }
            i10 = height;
            i11 = i15;
            height = i2;
            i3 = 0;
            while (i3 < width) {
                iArr3[i19] = iArr7[i4];
                iArr4[i19] = iArr7[i5];
                iArr5[i19] = iArr7[i6];
                i4 -= i21;
                i5 -= i22;
                i6 -= i23;
                int[] iArr10 = iArr8[((height - i2) + i16) % i16];
                i21 -= iArr10[0];
                i22 -= iArr10[1];
                i23 -= iArr10[2];
                if (i13 == 0) {
                    iArr = iArr7;
                    iArr6[i3] = Math.min((i3 + i2) + 1, i14);
                } else {
                    iArr = iArr7;
                }
                i12 = iArr2[i20 + iArr6[i3]];
                iArr10[0] = (i12 & 16711680) >> 16;
                iArr10[1] = (i12 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr10[2] = i12 & 255;
                i7 += iArr10[0];
                i8 += iArr10[1];
                i9 += iArr10[2];
                i4 += i7;
                i5 += i8;
                i6 += i9;
                height = (height + 1) % i16;
                iArr10 = iArr8[height % i16];
                i21 += iArr10[0];
                i22 += iArr10[1];
                i23 += iArr10[2];
                i7 -= iArr10[0];
                i8 -= iArr10[1];
                i9 -= iArr10[2];
                i19++;
                i3++;
                iArr7 = iArr;
            }
            iArr = iArr7;
            i20 += width;
            i13++;
            bitmap2 = bitmap3;
            height = i10;
            i15 = i11;
        }
        bitmap3 = bitmap2;
        i10 = height;
        i11 = i15;
        iArr = iArr7;
        i3 = 0;
        while (i3 < width) {
            int[] iArr11;
            int abs;
            height = -i2;
            i14 = height * width;
            i15 = 0;
            i13 = 0;
            i12 = 0;
            i19 = 0;
            i20 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            i7 = 0;
            while (height <= i2) {
                iArr11 = iArr6;
                i8 = Math.max(0, i14) + i3;
                int[] iArr12 = iArr8[height + i2];
                iArr12[0] = iArr3[i8];
                iArr12[1] = iArr4[i8];
                iArr12[2] = iArr5[i8];
                abs = i17 - Math.abs(height);
                i15 += iArr3[i8] * abs;
                i13 += iArr4[i8] * abs;
                i12 += iArr5[i8] * abs;
                if (height > 0) {
                    i19 += iArr12[0];
                    i20 += iArr12[1];
                    i4 += iArr12[2];
                } else {
                    i5 += iArr12[0];
                    i6 += iArr12[1];
                    i7 += iArr12[2];
                }
                abs = i11;
                if (height < abs) {
                    i14 += width;
                }
                height++;
                i11 = abs;
                iArr6 = iArr11;
            }
            iArr11 = iArr6;
            abs = i11;
            i8 = i20;
            i9 = i4;
            height = i10;
            i14 = 0;
            i20 = i2;
            i4 = i19;
            i19 = i12;
            i12 = i13;
            i13 = i15;
            i15 = i3;
            while (i14 < height) {
                iArr2[i15] = (((ViewCompat.MEASURED_STATE_MASK & iArr2[i15]) | (iArr[i13] << 16)) | (iArr[i12] << 8)) | iArr[i19];
                i13 -= i5;
                i12 -= i6;
                i19 -= i7;
                int[] iArr13 = iArr8[((i20 - i2) + i16) % i16];
                i5 -= iArr13[0];
                i6 -= iArr13[1];
                i7 -= iArr13[2];
                if (i3 == 0) {
                    iArr11[i14] = Math.min(i14 + i17, abs) * width;
                }
                i2 = iArr11[i14] + i3;
                iArr13[0] = iArr3[i2];
                iArr13[1] = iArr4[i2];
                iArr13[2] = iArr5[i2];
                i4 += iArr13[0];
                i8 += iArr13[1];
                i9 += iArr13[2];
                i13 += i4;
                i12 += i8;
                i19 += i9;
                i20 = (i20 + 1) % i16;
                int[] iArr14 = iArr8[i20];
                i5 += iArr14[0];
                i6 += iArr14[1];
                i7 += iArr14[2];
                i4 -= iArr14[0];
                i8 -= iArr14[1];
                i9 -= iArr14[2];
                i15 += width;
                i14++;
                i2 = i;
            }
            i3++;
            i10 = height;
            i11 = abs;
            iArr6 = iArr11;
            i2 = i;
        }
        bitmap3.setPixels(iArr2, 0, width, 0, 0, width, i10);
        return bitmap3;
    }

    public static int c(int i) {
        if (i == VIEW_SIZE.SCROLL_RECTANGLE.getNumVal() || i == VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT.getNumVal()) {
            return R.layout.item_playlist_grid_150x275;
        }
        if (i == VIEW_SIZE.SCROLL_BIG_SQAUE.getNumVal() || i == VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT.getNumVal()) {
            return R.layout.item_playlist_grid_200x200;
        }
        if (i == VIEW_SIZE.SCROLL_MEDIUM_SQAUE.getNumVal()) {
            return R.layout.item_playlist_grid_120x120;
        }
        if (i == VIEW_SIZE.SCROLL_RECTANGLE_DISCOVER.getNumVal()) {
            return R.layout.item_playlist_grid_140x75;
        }
        if (i == VIEW_SIZE.RECENTLY_PLAYED_SMALL.getNumVal()) {
            return R.layout.item_playlist_grid_65x65;
        }
        if (i == VIEW_SIZE.GRID_LARGE.getNumVal()) {
            return R.layout.item_playlist_grid_140x190;
        }
        if (i == VIEW_SIZE.GRID_LARGE_MIX.getNumVal()) {
            return R.layout.item_playlist_gridmix_140x190;
        }
        if (i == VIEW_SIZE.GRID_RECTANGLE_255x142.getNumVal()) {
            return R.layout.item_playlist_grid_255x142;
        }
        if (i == VIEW_SIZE.GRID_RECTANGLE_MEDIUM_140x250.getNumVal()) {
            return R.layout.item_playlist_grid_140x250;
        }
        if (i == VIEW_SIZE.CARD_BIG_SQUARE.getNumVal()) {
            return R.layout.item_playlist_grid_227x227;
        }
        return i == VIEW_SIZE.CARD_BIGGER_SQUARE.getNumVal() ? R.layout.item_playlist_grid_228x228 : R.layout.item_playlist_grid_140x140;
    }

    public static int d(int i) {
        int i2 = i == VIEW_SIZE.SCROLL_MEDIUM_SQAUE.getNumVal() ? R.layout.item_playlist_grid_juke_120x120 : i == VIEW_SIZE.SCROLL_GENERIC.getNumVal() ? R.layout.item_playlist_grid_juke_140x140 : -1;
        if (i2 != -1) {
            return i2;
        }
        return c(i);
    }

    public static int e(int i) {
        return (i == VIEW_SIZE.SCROLL_MEDIUM_SQAUE.getNumVal() || i == VIEW_SIZE.SCROLL_MEDIUM_CIRCLE.getNumVal()) ? R.layout.item_playlist_circular_grid_120x120 : R.layout.item_playlist_circular_grid_140x140;
    }

    public static String X() {
        String str = "Not logged in";
        return (GaanaApplication.getInstance().getCurrentUser() == null || GaanaApplication.getInstance().getCurrentUser().getUserProfile() == null) ? str : GaanaApplication.getInstance().getCurrentUser().getUserProfile().getUserId();
    }

    public static String a(PlayerTrack playerTrack, String str) {
        String name = PLAYOUT_SOURCE_NAME.TRACK.name();
        int e = playerTrack.e();
        if (TextUtils.isEmpty(str) || !str.equals(PLAYOUT_SECTION_TYPE.ARTISTS.name())) {
            if (!TextUtils.isEmpty(str) && str.equals(PLAYOUT_SECTION_TYPE.MY_PLAYLISTS.name())) {
                return PLAYOUT_SOURCE_NAME.PLAYLIST.name();
            }
            if (e == SOURCE_TYPE.PLAYLIST.ordinal() || e == SOURCE_TYPE.HOURLY_PLAYLIST.ordinal()) {
                return PLAYOUT_SOURCE_NAME.PLAYLIST.name();
            }
            if (e == SOURCE_TYPE.ALBUM.ordinal()) {
                return PLAYOUT_SOURCE_NAME.ALBUM.name();
            }
            if (e == SOURCE_TYPE.TRACK.ordinal()) {
                return PLAYOUT_SOURCE_NAME.TRACK.name();
            }
            if (e == SOURCE_TYPE.RADIO_MIRCHI.ordinal()) {
                return PLAYOUT_SOURCE_NAME.RADIOMIRCHI.name();
            }
            if (e == SOURCE_TYPE.GAANA_RADIO.ordinal() || e == SOURCE_TYPE.RADIO_SEARCH_SONG.ordinal()) {
                return PLAYOUT_SOURCE_NAME.GAANARADIO.name();
            }
            if (e == SOURCE_TYPE.ARTIST_RADIO.ordinal() || e == SOURCE_TYPE.RADIO_SEARCH_ARTIST.ordinal()) {
                return PLAYOUT_SOURCE_NAME.ECHONESTARTISTRADIO.name();
            }
            if (!TextUtils.isEmpty(str) && str.equals(PLAYOUT_SECTION_TYPE.SONG_RADIO.name())) {
                return PLAYOUT_SOURCE_NAME.ECHONESSONGTRADIO.name();
            }
            if (e == SOURCE_TYPE.ONE_TOUCH_RADIO.ordinal()) {
                return PLAYOUT_SOURCE_NAME.ONETOUCHRADIO.name();
            }
            if (e == SOURCE_TYPE.ARTIST.ordinal()) {
                return PLAYOUT_SOURCE_NAME.ARTIST.name();
            }
            return name;
        } else if (e == SOURCE_TYPE.ARTIST_RADIO.ordinal() || e == SOURCE_TYPE.ECHONEST_ARTIST_RADIO.ordinal() || e == SOURCE_TYPE.GAANA_RADIO.ordinal() || e == SOURCE_TYPE.RADIO_SEARCH_ARTIST.ordinal()) {
            return PLAYOUT_SOURCE_NAME.ECHONESTARTISTRADIO.name();
        } else {
            return PLAYOUT_SOURCE_NAME.ARTIST.name();
        }
    }

    public static int Y() {
        return Constants.l ? R.drawable.vector_ic_explicit_content_white : R.drawable.vector_ic_explicit_content;
    }

    public static int Z() {
        return Constants.l ? R.drawable.vector_ab_download : R.drawable.vector_ab_download_white;
    }

    public static void a(@NonNull TextView textView, String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(160);
        spannableStringBuilder.append(160);
        Drawable drawable = ContextCompat.getDrawable(textView.getContext(), Y());
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable, 1);
        if (!TextUtils.isEmpty(str)) {
            spannableStringBuilder.append(str);
        }
        spannableStringBuilder.setSpan(imageSpan, 0, 1, 18);
        textView.setText(spannableStringBuilder);
    }

    public static void a(@NonNull TextView textView, Context context) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(context.getResources().getString(R.string.tap_on_icon_to_download_songs));
        Drawable drawable = ContextCompat.getDrawable(textView.getContext(), Z());
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spannableStringBuilder.setSpan(new ImageSpan(drawable, 1), 7, 8, 18);
        textView.setText(spannableStringBuilder);
    }

    @TargetApi(14)
    public static Bitmap a(Bitmap bitmap) {
        return bitmap.copy(Config.RGB_565, true);
    }

    public static void a(String str, int i, String str2) {
        URLManager uRLManager = new URLManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/index.php?type=download_error_log&download_msg=");
        stringBuilder.append(str);
        stringBuilder.append("&download_msg_code=");
        stringBuilder.append(str2);
        stringBuilder.append("&track_id=");
        stringBuilder.append(i);
        uRLManager.a(stringBuilder.toString());
        uRLManager.b(1);
        uRLManager.i(false);
        uRLManager.a(String.class);
        uRLManager.b(Boolean.valueOf(false));
        a(C, C.getResources().getString(R.string.fetching_details));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                Util.b();
                try {
                    JSONObject jSONObject = new JSONObject((String) obj);
                    if (jSONObject.has("status")) {
                        "1".equalsIgnoreCase(jSONObject.getString("status"));
                    }
                } catch (Exception unused) {
                }
            }
        }, uRLManager);
    }

    public static void aa() {
        int b = d.a().b("PREFERENCE_KEY_SYNC_QUALITY", 2, true);
        d.a().a("PREFERENCE_KEY_SYNC_QUALITY", b, true);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(b);
        b("download_quality", stringBuilder.toString());
    }

    public static void a(final a aVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/gplus_app_ad.php?type=gplus_ad_banner");
        stringBuilder.append(ag.a(C).d());
        String stringBuilder2 = stringBuilder.toString();
        URLManager uRLManager = new URLManager();
        uRLManager.a(stringBuilder2);
        uRLManager.a(120);
        uRLManager.a(CustomCard.class);
        uRLManager.b(Boolean.valueOf(true));
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject != null && (businessObject instanceof CustomCard) && Util.c()) {
                    aVar.onRetreivalComplete((CustomCard) businessObject);
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                aVar.onErrorResponse(businessObject);
            }
        }, uRLManager);
    }

    public static HashMap<Integer, String> a(Class<?> cls) {
        HashMap hashMap = new HashMap();
        for (Field field : cls.getDeclaredFields()) {
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder stringBuilder2 = new StringBuilder();
            try {
                stringBuilder.append(field.getName());
                stringBuilder2.append(field.get(cls));
                hashMap.put(new Integer(stringBuilder2.toString()), stringBuilder.toString());
            } catch (Exception unused) {
            }
        }
        return hashMap;
    }

    public static String c(Context context, String str) {
        double d = (double) context.getResources().getDisplayMetrics().density;
        if (d >= 4.0d) {
            return str;
        }
        if (d >= 3.0d) {
            return str.replace("size_m", "size_l");
        }
        if (d >= 2.0d) {
            return str.replace("size_m", "size_l");
        }
        if (d >= 1.5d) {
            return str.replace("size_m", "size_s");
        }
        return d >= 1.0d ? str.replace("size_m", "size_s") : str;
    }

    public static String d(Context context, String str) {
        double a = (double) a(context.getResources().getDisplayMetrics().density);
        if (a >= 4.0d) {
            return str;
        }
        if (a >= 3.0d) {
            return str.replace("size_m", "size_s");
        }
        if (a >= 2.0d) {
            return str.replace("size_m", "size_s");
        }
        if (a >= 1.5d) {
            return str.replace("size_m", "size_xs");
        }
        return a >= 1.0d ? str.replace("size_m", "size_xs") : str;
    }

    public static String e(Context context, String str) {
        double a = (double) a(context.getResources().getDisplayMetrics().density);
        if (a >= 4.0d) {
            return str.replace("size_m", "size_l");
        }
        if (a >= 3.0d) {
            return str.replace("size_m", "size_l");
        }
        if (a >= 2.0d) {
            return str.replace("size_m", "size_l");
        }
        return (a < 1.5d && a >= 1.0d) ? str.replace("size_m", "size_s") : str;
    }

    public static String f(Context context, String str) {
        double a = (double) a(context.getResources().getDisplayMetrics().density);
        if (a >= 4.0d) {
            return str.replace("size_m", "size_xl");
        }
        if (a >= 3.0d) {
            return str.replace("size_m", "size_xl");
        }
        if (a >= 2.0d) {
            return str.replace("size_m", "size_l");
        }
        if (a >= 1.5d) {
            return str.replace("size_m", "size_l");
        }
        return a >= 1.0d ? str : str;
    }

    public static String g(Context context, String str) {
        double a = (double) a(context.getResources().getDisplayMetrics().density);
        if (a >= 4.0d) {
            return str.replace("size_m", "size_l");
        }
        if (a >= 3.0d) {
            return str.replace("size_m", "size_l");
        }
        if (a >= 2.0d) {
            return str.replace("size_m", "size_l");
        }
        return (a < 1.5d && a >= 1.0d) ? str : str;
    }

    public static String h(Context context, String str) {
        double a = (double) a(context.getResources().getDisplayMetrics().density);
        if (a >= 4.0d) {
            return str.replace("size_m", "size_l");
        }
        if (a >= 3.0d) {
            return str.replace("size_m", "size_l");
        }
        if (a >= 2.0d) {
            return str.replace("size_m", "size_l");
        }
        return (a < 1.5d && a >= 1.0d) ? str : str;
    }

    private static float a(float f) {
        if (GaanaApplication.getInstance().isAppInDataSaveMode() || an()) {
            return f >= 3.0f ? 1.5f : 1.0f;
        } else {
            return f;
        }
    }

    private static boolean an() {
        ConnectionType connectionType;
        switch (d()) {
            case NETWORK_2G:
                connectionType = ConnectionType.L_SPEED;
                break;
            case NETWORK_WI_FI:
                connectionType = ConnectionType.WIFI;
                break;
            default:
                connectionType = ConnectionType.H_SPEED;
                break;
        }
        for (ConnectionType connectionType2 : n(GaanaApplication.getContext())) {
            if (connectionType == connectionType2) {
                return false;
            }
        }
        return true;
    }

    public static String r(String str) {
        return str.replace("size_m", "size_l");
    }

    public static String s(String str) {
        if (str.contains("_m")) {
            return str.replace("size_m", "size_xl");
        }
        return str.replace("size_l", "size_xl");
    }

    public static void i(final Context context, final String str) {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://pay.gaana.com/paytm/paytm_consent.php?type=get_consent");
        uRLManager.a(PaytmRenewal.class);
        uRLManager.b(Boolean.valueOf(true));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj != null && (obj instanceof PaytmRenewal)) {
                    Util.b(context, (PaytmRenewal) obj, str);
                }
            }
        }, uRLManager);
    }

    public static Drawable b(Context context, int i) {
        E = context.getTheme().obtainStyledAttributes(R.style.AppTheme, new int[]{i});
        return ContextCompat.getDrawable(context, E.getResourceId(0, 0));
    }

    public static void ab() {
        if (E != null) {
            E.recycle();
            E = null;
        }
    }

    private static void b(Context context, PaytmRenewal paytmRenewal, String str) {
        if (paytmRenewal.j().intValue() != 0) {
            int i;
            int i2;
            long e = paytmRenewal.e();
            long d = paytmRenewal.d();
            long intValue = (long) paytmRenewal.g().intValue();
            int intValue2 = paytmRenewal.f().a().a().intValue();
            int intValue3 = paytmRenewal.f().a().b().intValue();
            int intValue4 = paytmRenewal.f().b().a().intValue();
            int intValue5 = paytmRenewal.f().b().b().intValue();
            int intValue6 = paytmRenewal.h().intValue();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            int b = d.a().b("SESSION_COUNT_PAYTMRENEWAL", 0, false);
            int i3 = intValue5;
            intValue5 = d.a().b("DAY_COUNT_PAYMENTRENEWAL", 0, false);
            int i4 = intValue4;
            intValue4 = d.a().b("PREVIOUS_DATE_PAYTMRENEWAL", 0, false);
            int i5 = intValue3;
            long j = d;
            long b2 = d.a().b(0, "PREVIOUS_SHOW_TIME_PAYTMRENEWAL", false);
            intValue3 = d.a().b("PREVIOUS_SESSION_COUNT", 0, false);
            int b3 = d.a().b("VISIBLE_COUNT_PAYTMRENEWAL", 0, false);
            Calendar instance = Calendar.getInstance();
            instance.get(1);
            instance.get(2);
            int i6 = instance.get(5);
            int i7 = GaanaApplication.sessionHistoryCount;
            if (intValue5 == 0 || intValue4 == i6) {
                i = i6;
                i2 = intValue4;
            } else {
                i = i6;
                i2 = intValue4;
                d.a().a("DAY_COUNT_PAYMENTRENEWAL", 0, false);
            }
            if (!(b == 0 || intValue3 == i7)) {
                d.a().a("SESSION_COUNT_PAYTMRENEWAL", 0, false);
            }
            if ((b2 == 0 || currentTimeMillis - b2 >= intValue) && currentTimeMillis >= e && currentTimeMillis <= j && b3 < intValue6) {
                int i8;
                if (intValue2 == 1) {
                    i8 = i5;
                    if (intValue5 < i8) {
                        i6 = i;
                        if (i2 != i6) {
                            intValue5++;
                            d.a().a(intValue5, "DAY_COUNT_PAYMENTRENEWAL", false);
                            if (intValue5 >= i8) {
                                d.a().a("PREVIOUS_DATE_PAYTMRENEWAL", i6, false);
                                d.a().a("DAY_COUNT_PAYMENTRENEWAL", 0, false);
                            }
                            d.a().a(currentTimeMillis, "PREVIOUS_SHOW_TIME_PAYTMRENEWAL", false);
                            new g(context, paytmRenewal, str).show();
                        }
                    }
                }
                i8 = 1;
                if (i4 == i8) {
                    int i9 = i3;
                    if (b < i9 && i7 > 0 && intValue3 != i7) {
                        b += i8;
                        d.a().a(b, "SESSION_COUNT_PAYTMRENEWAL", false);
                        if (b >= i9) {
                            d.a().a("SESSION_COUNT_PAYTMRENEWAL", 0, false);
                            d.a().a("PREVIOUS_SESSION_COUNT", i7, false);
                        }
                        d.a().a(currentTimeMillis, "PREVIOUS_SHOW_TIME_PAYTMRENEWAL", false);
                        new g(context, paytmRenewal, str).show();
                    }
                }
            }
        }
    }

    public static void x(final Context context) {
        Constants.av = true;
        ((BaseActivity) context).hideProgressDialog();
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.limit_exceed_bottom_sheet_display, null);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(inflate);
        BottomSheetBehavior from = BottomSheetBehavior.from((View) inflate.getParent());
        if (from != null) {
            from.setPeekHeight(context.getResources().getDimensionPixelSize(R.dimen.mini_bottom_sheet));
        }
        TextView textView = (TextView) inflate.findViewById(R.id.setup_title_text);
        textView = (TextView) inflate.findViewById(R.id.setup_below);
        textView = (TextView) inflate.findViewById(R.id.not_now);
        ((Button) inflate.findViewById(R.id.setup_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                u.a().a("Gaana Plus Mini", "setup", "popup");
                ((GaanaActivity) context).displayFragment(new GaanaMiniSetupFragment());
                Constants.av = false;
                bottomSheetDialog.dismiss();
            }
        });
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Constants.av = false;
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }

    public static String a(@NonNull String str, int i, int i2) {
        StringBuilder stringBuilder;
        if (str.contains("size")) {
            str = str.split("&size")[0];
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&limit=");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            return stringBuilder.toString();
        } else if (str.contains("limit")) {
            if (str.contains("?limit")) {
                str = str.split("\\?limit")[0];
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("?limit=");
                stringBuilder.append(i);
                stringBuilder.append(",");
                stringBuilder.append(i2);
                return stringBuilder.toString();
            }
            str = str.split("&limit")[0];
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&limit=");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            return stringBuilder.toString();
        } else if (str.contains("?")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("&limit=");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            return stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("?limit=");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            return stringBuilder.toString();
        }
    }

    public static void a(@Nullable SplashAdCode splashAdCode) {
        String imageUrl = splashAdCode != null ? splashAdCode.getImageUrl() : "";
        String adTitle = splashAdCode != null ? splashAdCode.getAdTitle() : "brand";
        if (ap.a().b(GaanaApplication.getContext())) {
            String fullFileName = GlideFileLoader.getFullFileName("spl_ad_*#");
            if (TextUtils.isEmpty(imageUrl) && !TextUtils.isEmpty(fullFileName)) {
                GlideFileLoader.delete(fullFileName);
                return;
            } else if (!TextUtils.isEmpty(imageUrl)) {
                if (!TextUtils.isEmpty(fullFileName) && !imageUrl.equals(fullFileName.substring("spl_ad_*#".length()))) {
                    GlideFileLoader.delete(fullFileName);
                    d(imageUrl, adTitle);
                    return;
                } else if (TextUtils.isEmpty(fullFileName)) {
                    d(imageUrl, adTitle);
                    return;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        adTitle = GlideFileLoader.getFullFileName("spl_ad_*#");
        if (!TextUtils.isEmpty(adTitle)) {
            GlideFileLoader.delete(adTitle);
        }
    }

    public static void d(final String str, final String str2) {
        GlideApp.with(GaanaApplication.getContext()).asBitmap().load(str).into((com.bumptech.glide.request.a.i) new com.bumptech.glide.request.a.g<Bitmap>() {
            /* renamed from: a */
            public void onResourceReady(Bitmap bitmap, com.bumptech.glide.request.b.d dVar) {
                if (bitmap != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str.replaceAll("/", ""));
                    stringBuilder.append("spl_ad_*#");
                    stringBuilder.append(str2);
                    String stringBuilder2 = stringBuilder.toString();
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(GlideFileLoader.getFile(), stringBuilder2));
                        bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        GlideFileLoader.add(stringBuilder2);
                    } catch (FileNotFoundException | IOException unused) {
                    }
                }
            }

            public void onLoadFailed(@Nullable Drawable drawable) {
                super.onLoadFailed(drawable);
            }
        });
    }

    public static void ac() {
        com.i.b bVar = new com.i.b("https://api.gaana.com/config/api-data", String.class, new com.i.e.a() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onDataRetrieved(Object obj, boolean z) {
                Map map = (Map) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson((String) obj, new TypeToken<Map<String, String>>() {
                }.getType());
                if (map != null) {
                    com.constants.c.a(map);
                }
            }
        });
        bVar.a(true);
        bVar.d((int) PsExtractor.VIDEO_STREAM_MASK);
        i.a().a(bVar);
    }

    public static String a(Context context, ArrayList<EntityInfo> arrayList) {
        String str = null;
        if (!j(context) || GaanaApplication.getInstance().isAppInOfflineMode()) {
            ap.a().f(context);
            return null;
        }
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((EntityInfo) arrayList.get(i)).getKey().equals("dl_url")) {
                    str = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    break;
                }
            }
        }
        return str;
    }

    public static void a(final String str, String str2, String str3, final Context context) {
        if (str3 != null && str3.equalsIgnoreCase("0")) {
            com.services.c.a(context).a(context, str, GaanaApplication.getInstance());
        } else if (str3 == null || !str3.equalsIgnoreCase("1")) {
            if (str3 != null && str3.equalsIgnoreCase(InternalAvidAdSessionContext.AVID_API_LEVEL) && !TextUtils.isEmpty(str) && URLUtil.isValidUrl(str)) {
                try {
                    context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(context, context.getString(R.string.error_generic_unableto_process), 1).show();
                }
            }
        } else if (str2 != null && str2.equalsIgnoreCase("0")) {
            a(str, false, context);
        } else if (str2 == null || !str2.equalsIgnoreCase("1")) {
            if (str2 != null && str2.equalsIgnoreCase(InternalAvidAdSessionContext.AVID_API_LEVEL)) {
                if (GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                    a(str, true, context);
                } else {
                    com.managers.af.a(context, null).a((int) R.id.MyMusicMenuArtists, null, context.getString(R.string.mlogin_text), new m() {
                        public void onResponse(boolean z) {
                            Util.a(str, true, context);
                        }
                    });
                }
            }
        } else if (GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
            a(str, true, context);
        } else {
            a(str, false, context);
        }
    }

    public static void a(String str, boolean z, Context context) {
        if (str != null) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("EXTRA_WEBVIEW_URL", str);
            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
            intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
            intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
            intent.putExtra("MWEB_VIEW_LOGIN", "MWEB_VIEW_LOGIN");
            intent.putExtra("GETTING_DEVICE_ID", z);
            context.startActivity(intent);
        }
    }

    public static void a(final DeviceIdCallBack deviceIdCallBack) {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/user.php?type=get_modelwindow_token");
        uRLManager.a(SessionLoginInfo.class);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj != null && (obj instanceof SessionLoginInfo)) {
                    SessionLoginInfo sessionLoginInfo = (SessionLoginInfo) obj;
                    if (!TextUtils.isEmpty(sessionLoginInfo.getDevice_id()) && !TextUtils.isEmpty(sessionLoginInfo.getWebToken())) {
                        deviceIdCallBack.callDeviceId(sessionLoginInfo.getDevice_id(), sessionLoginInfo.getWebToken());
                    }
                }
            }
        }, uRLManager);
    }

    public static int a(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static BusinessObject h(Item item) {
        Album album = new Album();
        album.setBusinessObjType(BusinessObjectType.Albums);
        album.setLanguage(item.getLanguage());
        album.setSeokey(item.getSeokey());
        album.setName(item.getRawName());
        album.setArtwork(item.getArtwork());
        album.setAtw(item.getAtw());
        album.setBusinessObjId(item.getEntityId());
        album.setFavoriteCount(Long.toString(item.getFavoriteCount()));
        album.setPremiumContent(item.getPremiumContent());
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((EntityInfo) arrayList.get(i)).getKey().equals("ad_code")) {
                    album.setChannelPageAdCode((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("primaryartist")) {
                    ArrayList arrayList2 = new ArrayList();
                    try {
                        JSONArray jSONArray = new JSONArray((String) ((EntityInfo) arrayList.get(i)).getValue());
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            arrayList2.add((Album.Artist) new GsonBuilder().create().fromJson(jSONArray.get(i).toString(), Album.Artist.class));
                        }
                    } catch (JSONException unused) {
                    }
                    album.setPrimaryartist(arrayList2);
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("parental_warning")) {
                    try {
                        album.setParentalWarning(Double.compare(Double.valueOf((String) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue(), 1.0d) == 0 ? 1 : 0);
                    } catch (Exception unused2) {
                    }
                }
            }
        }
        return album;
    }

    public static BusinessObject i(Item item) {
        Radio radio = new Radio();
        radio.setBusinessObjType(BusinessObjectType.Radios);
        radio.setLanguage(item.getLanguage());
        radio.setSeokey(item.getSeokey());
        radio.setName(item.getRawName());
        radio.setArtwork(item.getArtwork());
        radio.setAtw(item.getAtw());
        radio.setBusinessObjId(item.getEntityId());
        radio.setFavoriteCount(Long.toString(item.getFavoriteCount()));
        radio.setType(item.getEntityType());
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                String str;
                if (((EntityInfo) arrayList.get(i)).getKey().equals("ad_code")) {
                    radio.setChannelPageAdCode((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("stream_url")) {
                    radio.setStreamUrl((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("ads_position")) {
                    try {
                        Double d = (Double) ((EntityInfo) arrayList.get(i)).getValue();
                        if (d != null) {
                            radio.setAdCompaignPosition(d.intValue());
                        }
                    } catch (Exception unused) {
                    }
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("poll_api")) {
                    str = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    if (str != null) {
                        radio.setPoll_api(str);
                    }
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("poll_time")) {
                    str = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    if (str != null) {
                        radio.setPoll_time(String.valueOf(str));
                    }
                }
            }
        }
        return radio;
    }

    public static BusinessObject j(Item item) {
        DiscoverTag discoverTag = new DiscoverTag();
        if (item.getEntityType() == null) {
            discoverTag.setBusinessObjType(BusinessObjectType.Discover);
        } else if (item.getEntityType().equals(c.a)) {
            discoverTag.setBusinessObjType(BusinessObjectType.Playlists);
        } else if (item.getEntityType().equals(c.b)) {
            discoverTag.setBusinessObjType(BusinessObjectType.Albums);
        } else if (item.getEntityType().equals(com.constants.c.d.c) || item.getEntityType().equals(com.constants.c.d.d)) {
            discoverTag.setBusinessObjType(BusinessObjectType.Radios);
        } else if (item.getEntityType().equals(c.c)) {
            discoverTag.setBusinessObjType(BusinessObjectType.Tracks);
        }
        discoverTag.setTagEntityType(item.getEntityType());
        discoverTag.setName(item.getRawName());
        discoverTag.setLanguage(item.getLanguage());
        discoverTag.setArtwork(item.getArtwork());
        discoverTag.setAtw(item.getAtw());
        discoverTag.setBusinessObjId(item.getEntityId());
        discoverTag.setFavoriteCount(Long.toString(item.getFavoriteCount()));
        return discoverTag;
    }

    public static BusinessObject k(Item item) {
        YouTubeVideo youTubeVideo = new YouTubeVideo();
        youTubeVideo.setBusinessObjType(BusinessObjectType.YouTubeVideos);
        youTubeVideo.d(item.getArtwork());
        youTubeVideo.setAtw(item.getAtw());
        youTubeVideo.setName(item.getRawName());
        youTubeVideo.b(item.getRawName());
        youTubeVideo.setLanguage(item.getLanguage());
        youTubeVideo.setBusinessObjId(item.getEntityId());
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        String str = "";
        String str2 = "";
        String str3 = "";
        CharSequence charSequence = "";
        if (arrayList != null) {
            int size = arrayList.size();
            String str4 = charSequence;
            String str5 = str3;
            str3 = str2;
            str2 = str;
            for (int i = 0; i < size; i++) {
                if (((EntityInfo) arrayList.get(i)).getKey().equals("horz_vd")) {
                    str3 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("vert_vd")) {
                    str2 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("url")) {
                    str5 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("vid_id")) {
                    str4 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    youTubeVideo.c(str4);
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("vd_expiry")) {
                    youTubeVideo.e((String) ((EntityInfo) arrayList.get(i)).getValue());
                }
            }
            str = str2;
            str2 = str3;
            str3 = str5;
            charSequence = str4;
        }
        if (!TextUtils.isEmpty(str)) {
            youTubeVideo.a(str);
            youTubeVideo.a(1);
        } else if (!TextUtils.isEmpty(str2)) {
            youTubeVideo.a(str2);
            youTubeVideo.a(2);
        } else if (!TextUtils.isEmpty(str3)) {
            youTubeVideo.a(str3);
            youTubeVideo.a(0);
        } else if (!TextUtils.isEmpty(charSequence)) {
            youTubeVideo.c(charSequence);
            youTubeVideo.a(0);
        }
        return youTubeVideo;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public static com.gaana.models.BusinessObject l(com.gaana.models.Item r10) {
        /*
        r0 = new com.gaana.models.Tracks$Track;
        r0.<init>();
        r1 = com.managers.URLManager.BusinessObjectType.Tracks;
        r0.setBusinessObjType(r1);
        r1 = r10.getLanguage();
        r0.setLanguage(r1);
        r1 = r10.getSeokey();
        r0.setSeokey(r1);
        r1 = r10.getRawName();
        r0.setName(r1);
        r1 = r10.getArtwork();
        r0.setArtwork(r1);
        r1 = r10.getAtw();
        r0.setAtw(r1);
        r1 = r10.getEntityId();
        r0.setBusinessObjId(r1);
        r1 = r10.getArtwork();
        r1 = r(r1);
        r0.setArtworkLarge(r1);
        r1 = r10.getPremiumContent();
        r0.setPremiumContent(r1);
        r10 = r10.getEntityInfo();
        r10 = (java.util.ArrayList) r10;
        r1 = new java.util.ArrayList;
        r1.<init>();
        if (r10 == 0) goto L_0x042e;
    L_0x0053:
        r2 = r10.size();
        r3 = 0;
        r4 = r3;
    L_0x0059:
        if (r4 >= r2) goto L_0x042e;
    L_0x005b:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "artist";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x00af;
    L_0x006d:
        r5 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x042a }
        r6 = r10.get(r4);	 Catch:{ JSONException -> 0x042a }
        r6 = (com.gaana.models.EntityInfo) r6;	 Catch:{ JSONException -> 0x042a }
        r6 = r6.getValue();	 Catch:{ JSONException -> 0x042a }
        r6 = (java.lang.String) r6;	 Catch:{ JSONException -> 0x042a }
        r5.<init>(r6);	 Catch:{ JSONException -> 0x042a }
        r6 = r5.length();	 Catch:{ JSONException -> 0x042a }
        if (r6 <= 0) goto L_0x042a;
    L_0x0084:
        r6 = r3;
    L_0x0085:
        r7 = r5.length();	 Catch:{ JSONException -> 0x042a }
        if (r6 >= r7) goto L_0x00aa;
    L_0x008b:
        r7 = r5.get(r6);	 Catch:{ JSONException -> 0x042a }
        r8 = new com.google.gson.GsonBuilder;	 Catch:{ JSONException -> 0x042a }
        r8.<init>();	 Catch:{ JSONException -> 0x042a }
        r8 = r8.create();	 Catch:{ JSONException -> 0x042a }
        r7 = r7.toString();	 Catch:{ JSONException -> 0x042a }
        r9 = com.gaana.models.Tracks.Track.Artist.class;
        r7 = r8.fromJson(r7, r9);	 Catch:{ JSONException -> 0x042a }
        r7 = (com.gaana.models.Tracks.Track.Artist) r7;	 Catch:{ JSONException -> 0x042a }
        r1.add(r7);	 Catch:{ JSONException -> 0x042a }
        r6 = r6 + 1;
        goto L_0x0085;
    L_0x00aa:
        r0.setArtist(r1);	 Catch:{ JSONException -> 0x042a }
        goto L_0x042a;
    L_0x00af:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "album";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x0100;
    L_0x00c1:
        r5 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x042a }
        r6 = r10.get(r4);	 Catch:{ JSONException -> 0x042a }
        r6 = (com.gaana.models.EntityInfo) r6;	 Catch:{ JSONException -> 0x042a }
        r6 = r6.getValue();	 Catch:{ JSONException -> 0x042a }
        r6 = (java.lang.String) r6;	 Catch:{ JSONException -> 0x042a }
        r5.<init>(r6);	 Catch:{ JSONException -> 0x042a }
        if (r5 == 0) goto L_0x042a;
    L_0x00d4:
        r6 = r5.length();	 Catch:{ JSONException -> 0x042a }
        if (r6 <= 0) goto L_0x042a;
    L_0x00da:
        r6 = r3;
    L_0x00db:
        r7 = r5.length();	 Catch:{ JSONException -> 0x042a }
        if (r6 >= r7) goto L_0x042a;
    L_0x00e1:
        r7 = r5.get(r6);	 Catch:{ JSONException -> 0x042a }
        r7 = (org.json.JSONObject) r7;	 Catch:{ JSONException -> 0x042a }
        r8 = "album_id";
        r8 = r7.opt(r8);	 Catch:{ JSONException -> 0x042a }
        r8 = (java.lang.String) r8;	 Catch:{ JSONException -> 0x042a }
        r0.setAlbumId(r8);	 Catch:{ JSONException -> 0x042a }
        r8 = "name";
        r7 = r7.opt(r8);	 Catch:{ JSONException -> 0x042a }
        r7 = (java.lang.String) r7;	 Catch:{ JSONException -> 0x042a }
        r0.setAlbumName(r7);	 Catch:{ JSONException -> 0x042a }
        r6 = r6 + 1;
        goto L_0x00db;
    L_0x0100:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "stream_url";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x013a;
    L_0x0112:
        r5 = r10.get(r4);	 Catch:{ Exception -> 0x0134 }
        r5 = (com.gaana.models.EntityInfo) r5;	 Catch:{ Exception -> 0x0134 }
        r5 = r5.getValue();	 Catch:{ Exception -> 0x0134 }
        r6 = new com.google.gson.GsonBuilder;	 Catch:{ Exception -> 0x0134 }
        r6.<init>();	 Catch:{ Exception -> 0x0134 }
        r6 = r6.create();	 Catch:{ Exception -> 0x0134 }
        r5 = (java.lang.String) r5;	 Catch:{ Exception -> 0x0134 }
        r7 = com.gaana.models.StreamUrls.class;
        r5 = r6.fromJson(r5, r7);	 Catch:{ Exception -> 0x0134 }
        r5 = (com.gaana.models.StreamUrls) r5;	 Catch:{ Exception -> 0x0134 }
        r0.setStreamUrls(r5);	 Catch:{ Exception -> 0x0134 }
        goto L_0x042a;
    L_0x0134:
        r5 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r5);
        goto L_0x042a;
    L_0x013a:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "lyrics_url";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x015d;
    L_0x014c:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setLyricsUrl(r5);
        goto L_0x042a;
    L_0x015d:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "lyrics_type";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x0180;
    L_0x016f:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setLyricsType(r5);
        goto L_0x042a;
    L_0x0180:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "artwork_large";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x0194;
    L_0x0192:
        goto L_0x042a;
    L_0x0194:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "country";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x01b7;
    L_0x01a6:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setLocationAvailability(r5);
        goto L_0x042a;
    L_0x01b7:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "mobile";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x01da;
    L_0x01c9:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setDeviceAvailability(r5);
        goto L_0x042a;
    L_0x01da:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "stream_type";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x01fd;
    L_0x01ec:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setStreamType(r5);
        goto L_0x042a;
    L_0x01fd:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "duration";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x0220;
    L_0x020f:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setDuration(r5);
        goto L_0x042a;
    L_0x0220:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "isrc";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x0243;
    L_0x0232:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setIsrc(r5);
        goto L_0x042a;
    L_0x0243:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "is_most_popular";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x026e;
    L_0x0255:
        r5 = r10.get(r4);	 Catch:{  }
        r5 = (com.gaana.models.EntityInfo) r5;	 Catch:{  }
        r5 = r5.getValue();	 Catch:{  }
        r5 = (java.lang.Double) r5;	 Catch:{  }
        r5 = r5.doubleValue();	 Catch:{  }
        r5 = java.lang.Double.toString(r5);	 Catch:{  }
        r0.setIsMostPopular(r5);	 Catch:{  }
        goto L_0x042a;
    L_0x026e:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "is_local";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x0291;
    L_0x0280:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setIsLocal(r5);
        goto L_0x042a;
    L_0x0291:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "parental_warning";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x02c7;
    L_0x02a3:
        r5 = r10.get(r4);	 Catch:{  }
        r5 = (com.gaana.models.EntityInfo) r5;	 Catch:{  }
        r5 = r5.getValue();	 Catch:{  }
        r5 = (java.lang.String) r5;	 Catch:{  }
        r5 = java.lang.Double.valueOf(r5);	 Catch:{  }
        r5 = r5.doubleValue();	 Catch:{  }
        r7 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r5 = java.lang.Double.compare(r5, r7);	 Catch:{  }
        if (r5 != 0) goto L_0x02c1;
    L_0x02bf:
        r5 = 1;
        goto L_0x02c2;
    L_0x02c1:
        r5 = r3;
    L_0x02c2:
        r0.setParentalWarning(r5);	 Catch:{  }
        goto L_0x042a;
    L_0x02c7:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "youtube_id";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x02ea;
    L_0x02d9:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setYoutubeId(r5);
        goto L_0x042a;
    L_0x02ea:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "video_id";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x030d;
    L_0x02fc:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setVideoId(r5);
        goto L_0x042a;
    L_0x030d:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "play_ct";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x0330;
    L_0x031f:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setPlayCount(r5);
        goto L_0x042a;
    L_0x0330:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "download_enabled";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x0359;
    L_0x0342:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = r5.toString();
        r5 = java.lang.Integer.parseInt(r5);
        r0.setFreeDownloadEnabled(r5);
        goto L_0x042a;
    L_0x0359:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "vert_vd";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x037c;
    L_0x036b:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setVerticalUrl(r5);
        goto L_0x042a;
    L_0x037c:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "horz_vd";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x039f;
    L_0x038e:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setHorizontalUrl(r5);
        goto L_0x042a;
    L_0x039f:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "content_source";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x03e7;
    L_0x03b1:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = r5 instanceof java.lang.String;
        if (r5 == 0) goto L_0x03d3;
    L_0x03bf:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r5 = java.lang.Double.parseDouble(r5);
        r0.setContentSource(r5);
        goto L_0x042a;
    L_0x03d3:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.Double) r5;
        r5 = r5.doubleValue();
        r0.setContentSource(r5);
        goto L_0x042a;
    L_0x03e7:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "clip_vd";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x0409;
    L_0x03f9:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setClipVideoUrl(r5);
        goto L_0x042a;
    L_0x0409:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getKey();
        r6 = "vd_expiry";
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x042a;
    L_0x041b:
        r5 = r10.get(r4);
        r5 = (com.gaana.models.EntityInfo) r5;
        r5 = r5.getValue();
        r5 = (java.lang.String) r5;
        r0.setVideoExpiryTime(r5);
    L_0x042a:
        r4 = r4 + 1;
        goto L_0x0059;
    L_0x042e:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.utilities.Util.l(com.gaana.models.Item):com.gaana.models.BusinessObject");
    }

    public static BusinessObject m(Item item) {
        Artist artist = new Artist();
        artist.setBusinessObjType(BusinessObjectType.Artists);
        artist.setSeokey(item.getSeokey());
        artist.setName(item.getRawName());
        artist.setLanguage(item.getLanguage());
        artist.setArtwork(item.getArtwork());
        artist.setAtw(item.getAtw());
        artist.setBusinessObjId(item.getEntityId());
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((EntityInfo) arrayList.get(i)).getKey().equals("songs")) {
                    artist.setSongsCount((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("albums")) {
                    artist.setAlbumsCount((String) ((EntityInfo) arrayList.get(i)).getValue());
                }
            }
        }
        return artist;
    }

    public static BusinessObject n(Item item) {
        Playlist playlist = new Playlist();
        playlist.setBusinessObjType(BusinessObjectType.Playlists);
        playlist.setLanguage(item.getLanguage());
        playlist.setSeokey(item.getSeokey());
        playlist.setName(item.getRawName());
        playlist.setArtwork(item.getArtwork());
        playlist.setAtw(item.getAtw());
        playlist.setPlaylistId(item.getEntityId());
        playlist.setFavoriteCount(Long.toString(item.getFavoriteCount()));
        playlist.setPremiumContent(item.getPremiumContent());
        playlist.setSapID(item.getSapID());
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((EntityInfo) arrayList.get(i)).getKey().equals("ad_code")) {
                    playlist.setChannelPageAdCode((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("created_by")) {
                    playlist.setCreatedby((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("created_by_user_id")) {
                    playlist.setCreatedbyUserId((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("modified_on")) {
                    playlist.setLastModifiedDate(new Date(Long.parseLong((String) ((EntityInfo) arrayList.get(i)).getValue())));
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("detailed_description")) {
                    playlist.setPlaylistDetailedDescription((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("lpid")) {
                    playlist.setLocalPlaylistId((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("track_id")) {
                    playlist.setTrackIds((String) ((EntityInfo) arrayList.get(i)).getValue());
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("notify_status")) {
                    try {
                        playlist.setNotifyStatus(((Double) ((EntityInfo) arrayList.get(i)).getValue()).intValue());
                    } catch (Exception unused) {
                    }
                } else if (((EntityInfo) arrayList.get(i)).getKey().equals("parental_warning")) {
                    playlist.setParentalWarning(Double.compare(Double.valueOf((String) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue(), 1.0d) == 0 ? 1 : 0);
                }
            }
        }
        return playlist;
    }

    @Nullable
    public static BaseGaanaFragment o(@NonNull Item item) {
        List entityInfo = item.getEntityInfo();
        String str = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        if (item.getEntityType().equals(c.j) && entityInfo != null) {
            int size = entityInfo.size();
            String str5 = str4;
            str4 = str3;
            str3 = str2;
            CharSequence charSequence = str;
            for (int i = 0; i < size; i++) {
                if (((EntityInfo) entityInfo.get(i)).getKey().equals("url_logo_banner")) {
                    str3 = ((EntityInfo) entityInfo.get(i)).getValue().toString();
                } else if (((EntityInfo) entityInfo.get(i)).getKey().equals("tracker_adcode_dfp_viewall")) {
                    str4 = ((EntityInfo) entityInfo.get(i)).getValue().toString();
                } else if (((EntityInfo) entityInfo.get(i)).getKey().equals("vpl_type")) {
                    str5 = ((EntityInfo) entityInfo.get(i)).getValue().toString();
                } else if (((EntityInfo) entityInfo.get(i)).getKey().equals("vpl_count")) {
                    try {
                        Integer.parseInt(((EntityInfo) entityInfo.get(i)).getValue().toString());
                    } catch (Exception unused) {
                    }
                } else if (((EntityInfo) entityInfo.get(i)).getKey().equals("url")) {
                    charSequence = ((EntityInfo) entityInfo.get(i)).getValue().toString();
                }
            }
            str = charSequence;
            str2 = str3;
            str3 = str4;
            str4 = str5;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SongParallexListingFragment songParallexListingFragment = new SongParallexListingFragment();
        ListingParams listingParams = new ListingParams();
        listingParams.e(false);
        listingParams.f(true);
        listingParams.h(false);
        listingParams.d(true);
        listingParams.i(false);
        listingParams.a(true);
        ListingButton listingButton = (ListingButton) Constants.e().c().get(0);
        listingButton.b(item.getRawName());
        listingButton.a(item.getRawName());
        URLManager c = listingButton.c();
        c.g(true);
        c.a(str);
        c.d(false);
        c.a(true);
        c.a(BusinessObjectType.GenericItems);
        c.h(false);
        listingButton.a(c);
        listingParams.a(listingButton);
        songParallexListingFragment.a(listingParams);
        ListingComponents listingComponents = new ListingComponents();
        new ArrayList().add(listingButton);
        GaanaApplication.getInstance().setListingComponents(listingComponents);
        Bundle bundle = new Bundle();
        bundle.putString("EXTRA_VIEW_ALL_BANNER_AD_IMG", str2);
        bundle.putString("EXTRA_BRAND_DFP_TRACKER", str3);
        bundle.putString("EXTRA_VPL_TYPE", str4);
        songParallexListingFragment.setArguments(bundle);
        return songParallexListingFragment;
    }

    public static String a(Item item, String str) {
        List entityInfo = item.getEntityInfo();
        String str2 = null;
        if (entityInfo != null) {
            int size = entityInfo.size();
            int i = 0;
            while (i < size) {
                if (((EntityInfo) entityInfo.get(i)).getKey().equalsIgnoreCase(str) && entityInfo.get(i) != null) {
                    str2 = (String) ((EntityInfo) entityInfo.get(i)).getValue();
                }
                i++;
            }
        }
        return str2;
    }

    public static boolean ad() {
        CountryData countryData = GaanaApplication.getInstance().getCountryData();
        if (countryData == null) {
            return false;
        }
        if (countryData.getEuRegion() == 1 && Constants.ek == 0) {
            return false;
        }
        return true;
    }

    public static void ae() {
        final Geocoder geocoder = new Geocoder(C);
        com.i.d.a(new Runnable() {
            public void run() {
                if (geocoder != null) {
                    Geocoder geocoder = geocoder;
                    if (Geocoder.isPresent()) {
                        CountryData countryData = GaanaApplication.getInstance().getCountryData();
                        String displayCountry = new Locale("", countryData.getCountry()).getDisplayCountry();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(countryData.getCity());
                        stringBuilder.append(", ");
                        stringBuilder.append(countryData.getRegion());
                        stringBuilder.append(", ");
                        stringBuilder.append(displayCountry);
                        try {
                            List<Address> fromLocationName = geocoder.getFromLocationName(stringBuilder.toString(), 2);
                            if (fromLocationName != null && fromLocationName.size() > 0) {
                                for (Address address : fromLocationName) {
                                    if (address != null && address.getLongitude() > 0.0d && address.getLatitude() > 0.0d) {
                                        d.a().a(address.getLatitude(), "PREF_LOCATION_LAT", false);
                                        d.a().a(address.getLongitude(), "PREF_LOCATION_LNG", false);
                                        return;
                                    }
                                }
                            }
                        } catch (IOException e) {
                            ThrowableExtension.printStackTrace(e);
                        }
                    }
                }
            }
        });
    }

    public static void y(Context context) {
        if (j(context)) {
            URLManager uRLManager = new URLManager();
            uRLManager.a("https://api.gaana.com/get_country_code.php");
            uRLManager.a(CountryData.class);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.e(10000);
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    if (obj != null) {
                        GaanaApplication.getInstance().setCountryData((CountryData) obj, true);
                        Util.ae();
                        d.a().a("PREF_COUNTRY_CODE", Constants.ct, false);
                        d.a().a("PREF_CITY_NAME", Constants.cC, false);
                        d.a().a("PREF_STATE_NAME", Constants.cB, false);
                        d.a().a("PREF_CONSENT_STATUS", Constants.ek, false);
                    }
                }
            }, uRLManager);
        }
    }

    public static void a(Context context, final l.e eVar) {
        if (j(context)) {
            URLManager uRLManager = new URLManager();
            uRLManager.a("https://api.gaana.com/get_country_code.php");
            uRLManager.a(CountryData.class);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.e(10000);
            i.a().a(new af() {
                public void onRetreivalComplete(Object obj) {
                    if (obj != null) {
                        CountryData countryData = (CountryData) obj;
                        GaanaApplication.getInstance().setCountryData(countryData, true);
                        Util.ae();
                        Constants.ct = countryData.getCountry();
                        Constants.cB = countryData.getRegion();
                        Constants.cC = countryData.getCity();
                        Constants.ek = countryData.getIsConsent();
                        Constants.T = countryData.getBoarding().intValue() == 1;
                        d.a().a("PREF_COUNTRY_CODE", Constants.ct, false);
                        d.a().a("PREF_CITY_NAME", Constants.cC, false);
                        d.a().a("PREF_STATE_NAME", Constants.cB, false);
                        d.a().a("PREF_CONSENT_STATUS", Constants.ek, false);
                        eVar.onDataRetrieved(1);
                        return;
                    }
                    eVar.onDataRetrieved(0);
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    eVar.onDataRetrieved(0);
                }
            }, uRLManager);
            return;
        }
        eVar.onDataRetrieved(2);
    }

    public static void j(final Context context, final String str) {
        ((BaseActivity) context).checkSetLoginStatus(new ad() {
            public void onLoginSuccess() {
                if (Util.j(context)) {
                    URLManager uRLManager = new URLManager();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("https://api.gaana.com/studentidentity.php?action=get_consent");
                    stringBuilder.append("&product_id=");
                    stringBuilder.append(str);
                    uRLManager.a(stringBuilder.toString());
                    uRLManager.a(StudentIdentityAction.class);
                    uRLManager.b(Boolean.valueOf(false));
                    i.a().a(new s() {
                        public void onRetreivalComplete(BusinessObject businessObject) {
                            if (businessObject != null && (businessObject instanceof StudentIdentityAction)) {
                                StudentIdentityAction studentIdentityAction = (StudentIdentityAction) businessObject;
                                if (studentIdentityAction.getVerified() != 0) {
                                    ag.a(context).b(context);
                                } else if (TextUtils.isEmpty(studentIdentityAction.getWebview_url())) {
                                    aj.a().a(context, context.getString(R.string.error_in_purchase));
                                } else {
                                    Util.b(context, studentIdentityAction);
                                }
                            }
                        }

                        public void onErrorResponse(BusinessObject businessObject) {
                            aj.a().a(context, context.getString(R.string.error_in_purchase));
                        }
                    }, uRLManager);
                }
            }
        }, context.getResources().getString(R.string.LOGIN_LAUNCHED_FOR_SUBSCRIPTION));
    }

    private static void b(Context context, StudentIdentityAction studentIdentityAction) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("EXTRA_WEBVIEW_URL", studentIdentityAction.getWebview_url());
        intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
        intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
        intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
        ((GaanaActivity) context).startActivityForResult(intent, 714);
    }

    public static void a(final Context context, final l.d dVar) {
        if (GaanaApplication.getInstance().getCountryData() == null) {
            if (j(GaanaApplication.getContext())) {
                URLManager uRLManager = new URLManager();
                uRLManager.a("https://api.gaana.com/get_country_code.php");
                uRLManager.a(CountryData.class);
                uRLManager.b(Boolean.valueOf(false));
                i.a().a(new af() {
                    public void onRetreivalComplete(Object obj) {
                        if (obj != null) {
                            CountryData countryData = (CountryData) obj;
                            Constants.ct = countryData.getCountry();
                            Constants.cB = countryData.getRegion();
                            Constants.cC = countryData.getCity();
                            GaanaApplication.getInstance().setCountryData(countryData, true);
                            Constants.ek = countryData.getIsConsent();
                            d.a().a("PREF_CONSENT_STATUS", Constants.ek, false);
                            if (dVar != null) {
                                dVar.onConsentProvided(Util.ad());
                            }
                        }
                    }

                    public void onErrorResponse(BusinessObject businessObject) {
                        if (context != null && !(context instanceof GaanaActivity)) {
                            Intent intent = new Intent(context, GaanaActivity.class);
                            if (!(context instanceof Activity)) {
                                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                            }
                            intent.setFlags(603979776);
                            context.startActivity(intent);
                        }
                    }
                }, uRLManager);
                return;
            }
            String c = d.a().c("PREF_COUNTRY_CODE", false);
            String c2 = d.a().c("PREF_CITY_NAME", false);
            String c3 = d.a().c("PREF_STATE_NAME", false);
            CountryData countryData = (CountryData) n.a(d.a().b("PREF_COUNTRY_DATA", null, false));
            int b = d.a().b("PREF_CONSENT_STATUS", 1, false);
            if (c != null) {
                Constants.ct = c;
                Constants.cB = c3;
                Constants.cC = c2;
                GaanaApplication.getInstance().setCountryData(countryData, false);
                Constants.ek = b;
                if (dVar != null) {
                    dVar.onConsentProvided(ad());
                }
            } else if (context != null && !(context instanceof GaanaActivity)) {
                Intent intent = new Intent(context, GaanaActivity.class);
                if (!(context instanceof Activity)) {
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                }
                intent.setFlags(603979776);
                context.startActivity(intent);
            }
        } else if (dVar != null) {
            dVar.onConsentProvided(ad());
        }
    }

    public static void af() {
        String c = d.a().c("PREF_COUNTRY_CODE", false);
        String c2 = d.a().c("PREF_CITY_NAME", false);
        String c3 = d.a().c("PREF_STATE_NAME", false);
        CountryData countryData = (CountryData) n.a(d.a().b("PREF_COUNTRY_DATA", null, false));
        int b = d.a().b("PREF_CONSENT_STATUS", 0, false);
        if (c != null) {
            Constants.ct = c;
            Constants.cB = c3;
            Constants.cC = c2;
            GaanaApplication.getInstance().setCountryData(countryData, false);
            Constants.ek = b;
        }
    }

    public static void k(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device_id", l(context));
            String X = X();
            if (!TextUtils.isEmpty(X)) {
                jSONObject.put(AccessToken.USER_ID_KEY, X);
            }
            jSONObject.put("email_id", str);
            jSONObject.put("idfa", o());
            byte[] c = new k(Constants.bw).c(jSONObject.toString());
            HashMap hashMap = new HashMap();
            hashMap.put("userData", k.a(c));
            URLManager uRLManager = new URLManager();
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.i(false);
            uRLManager.a("https://api.gaana.com/user/log/delete-data");
            uRLManager.a(UpdateServerResponse.class);
            uRLManager.c(1);
            uRLManager.a(hashMap);
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                }
            }, uRLManager);
        } catch (Exception unused) {
        }
    }

    public static void l(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device_id", l(context));
            String X = X();
            if (!TextUtils.isEmpty(X)) {
                jSONObject.put(AccessToken.USER_ID_KEY, X);
            }
            jSONObject.put("idfa", o());
            jSONObject.put("email_id", str);
            byte[] c = new k(Constants.bw).c(jSONObject.toString());
            HashMap hashMap = new HashMap();
            hashMap.put("userData", k.a(c));
            URLManager uRLManager = new URLManager();
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.i(false);
            uRLManager.a("https://api.gaana.com/user/log/download-data");
            uRLManager.a(UpdateServerResponse.class);
            uRLManager.c(1);
            uRLManager.a(hashMap);
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                }
            }, uRLManager);
        } catch (Exception unused) {
        }
    }

    public static void z(Context context) {
        try {
            Set<Entry> entrySet = Constants.em.entrySet();
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : entrySet) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("consent_id", entry.getKey());
                jSONObject.put("status", entry.getValue());
                jSONArray.put(jSONObject);
            }
            HashMap hashMap = new HashMap();
            hashMap.put("user_consent", jSONArray.toString());
            URLManager uRLManager = new URLManager();
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.i(false);
            uRLManager.a("https://api.gaana.com/user/log/consent");
            uRLManager.a(UpdateServerResponse.class);
            uRLManager.c(1);
            uRLManager.a(hashMap);
            Constants.en = 2;
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    if (obj != null && (obj instanceof UpdateServerResponse) && ((UpdateServerResponse) obj).getStatus() == 1) {
                        Constants.ek = 1;
                        d.a().a("PREF_CONSENT_STATUS", Constants.ek, false);
                    }
                }
            }, uRLManager);
        } catch (Exception unused) {
        }
    }

    public static void a(Item item, Context context, String str) {
        List entityInfo = item.getEntityInfo();
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String str5 = "";
        if (item.getEntityType().equals(c.j) && entityInfo != null) {
            int size = entityInfo.size();
            String str6 = str5;
            str5 = str4;
            str4 = str3;
            CharSequence charSequence = str2;
            for (int i = 0; i < size; i++) {
                if (((EntityInfo) entityInfo.get(i)).getKey().equals("url_logo_banner")) {
                    str4 = ((EntityInfo) entityInfo.get(i)).getValue().toString();
                } else if (((EntityInfo) entityInfo.get(i)).getKey().equals("tracker_adcode_dfp_viewall")) {
                    str5 = ((EntityInfo) entityInfo.get(i)).getValue().toString();
                } else if (((EntityInfo) entityInfo.get(i)).getKey().equals("vpl_type")) {
                    str6 = ((EntityInfo) entityInfo.get(i)).getValue().toString();
                } else if (((EntityInfo) entityInfo.get(i)).getKey().equals("vpl_count")) {
                    try {
                        Integer.parseInt(((EntityInfo) entityInfo.get(i)).getValue().toString());
                    } catch (Exception unused) {
                    }
                } else if (((EntityInfo) entityInfo.get(i)).getKey().equals("url")) {
                    charSequence = ((EntityInfo) entityInfo.get(i)).getValue().toString();
                }
            }
            str2 = charSequence;
            str3 = str4;
            str4 = str5;
            str5 = str6;
        }
        if (!TextUtils.isEmpty(str2)) {
            BaseGaanaFragment songParallexListingFragment = new SongParallexListingFragment();
            ListingParams listingParams = new ListingParams();
            listingParams.e(false);
            listingParams.f(true);
            listingParams.h(false);
            listingParams.d(true);
            listingParams.i(false);
            listingParams.a(true);
            listingParams.b(str);
            ListingButton listingButton = (ListingButton) Constants.e().c().get(0);
            listingButton.b(item.getRawName());
            listingButton.a(item.getRawName());
            URLManager c = listingButton.c();
            c.g(true);
            c.a(str2);
            c.d(false);
            c.a(true);
            c.a(BusinessObjectType.GenericItems);
            c.h(false);
            listingButton.a(c);
            listingParams.a(listingButton);
            songParallexListingFragment.a(listingParams);
            ListingComponents listingComponents = new ListingComponents();
            new ArrayList().add(listingButton);
            GaanaApplication.getInstance().setListingComponents(listingComponents);
            Bundle bundle = new Bundle();
            bundle.putString("EXTRA_VIEW_ALL_BANNER_AD_IMG", str3);
            bundle.putString("EXTRA_BRAND_DFP_TRACKER", str4);
            bundle.putString("EXTRA_VPL_TYPE", str5);
            songParallexListingFragment.setArguments(bundle);
            ((GaanaActivity) context).displayFragment(songParallexListingFragment);
        }
    }

    public static String a(URLManager uRLManager) {
        if (uRLManager == null || uRLManager.i() == null) {
            return GaanaApplication.getContext().getString(R.string.name);
        }
        int i = AnonymousClass50.b[uRLManager.i().ordinal()];
        if (i != 37) {
            switch (i) {
                case 1:
                    return GaanaApplication.getContext().getString(R.string.name_album_artist);
                case 2:
                case 4:
                case 5:
                    break;
                case 3:
                    return GaanaApplication.getContext().getString(R.string.name_artist);
                default:
                    return null;
            }
        }
        return GaanaApplication.getContext().getString(R.string.name);
    }

    public static void ag() {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/user.php?type=notify_timesuser");
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
            }
        }, uRLManager);
    }

    public static void ah() {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/user.php?type=notify_fp");
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
            }
        }, uRLManager);
    }

    public static void t(String str) {
        URLManager uRLManager = new URLManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://apiv2.gaana.com/user/entity/activity/delete?track_id=");
        stringBuilder.append(str);
        uRLManager.a(stringBuilder.toString());
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
            }
        }, uRLManager);
    }

    public static void A(final Context context) {
        u.a().b("Playlist", "Create Playlist");
        ((BaseActivity) context).checkSetLoginStatus(new ad() {
            public void onLoginSuccess() {
                ((GaanaActivity) context).displayFragment(CreateNewPlaylistFragment.a("", true));
            }
        }, context.getResources().getString(R.string.LOGIN_LAUNCHED_FOR_ADD_TO_PLAYLIST));
    }

    public static void a(Context context, String str, String str2, BusinessObject businessObject, int i) {
        ((GaanaActivity) context).launchYoutubePlayer(str, str2, businessObject, i);
    }

    public static void a(com.services.l.b bVar) {
        A = bVar;
    }

    public static void a(Context context, String str, String str2, String str3, int i, long j, int i2, int i3, long j2) {
        final Context context2 = context;
        String str4 = str;
        String str5 = str3;
        final int i4 = i;
        final long j3 = j;
        int i5 = i2;
        int i6 = i3;
        long j4 = j2;
        if (GaanaMusicService.t()) {
            com.player_framework.o.a(context2, PauseReasons.MEDIA_BUTTON_TAP);
            Constants.dc = true;
        }
        if (com.managers.f.v().w()) {
            com.managers.f.v().F();
            Constants.dc = true;
        }
        com.player_framework.d dVar = new com.player_framework.d(context2);
        if (j3 == -1 || dVar.a(j3)) {
            Intent intent;
            if (d.g()) {
                intent = new Intent(context2, VideoPlayerActivityTwo.class);
            } else {
                intent = new Intent(context2, FullScreenVideoPlayerActivity.class);
            }
            intent.setAction("com.google.android.exoplayer.demo.action.VIEW");
            intent.putExtra("share_url", str2);
            intent.putExtra("video_id", str5);
            intent.putExtra("video_type", i4);
            if (str4.contains("http") || str4.contains("https")) {
                intent.putExtra("video_url", str4);
            } else {
                str4 = k(str);
                intent.putExtra("video_url", str4);
            }
            if (i5 != -1) {
                intent.putExtra("from_page", i5);
            }
            if (i6 != -1) {
                intent.putExtra("seek_index", i6);
            }
            if (j4 != -1) {
                intent.putExtra("seek_pos", j4);
            }
            YouTubeVideo youTubeVideo = new YouTubeVideo();
            youTubeVideo.b("");
            youTubeVideo.setBusinessObjId(str5);
            youTubeVideo.d("");
            youTubeVideo.setLanguage("");
            youTubeVideo.e(String.valueOf(j));
            youTubeVideo.a(str4);
            youTubeVideo.a(i4);
            a(context2, youTubeVideo, GAANA_ENTRY_PAGE.SEARCH_FEED.name());
            return;
        }
        switch (i4) {
            case 0:
                str4 = "youtube";
                break;
            case 1:
                str4 = "vert";
                break;
            case 2:
                str4 = "horz";
                break;
            default:
                str4 = "youtube";
                break;
        }
        final String str6 = str5;
        dVar.a(str5, str4, new com.i.e.b() {
            public void onDataRetrieved(Object obj, int i, boolean z) {
                if (obj instanceof LinkedTreeMap) {
                    String k = Util.k((String) ((LinkedTreeMap) obj).get("data"));
                    YouTubeVideo youTubeVideo = new YouTubeVideo();
                    youTubeVideo.b("");
                    youTubeVideo.setBusinessObjId(str6);
                    youTubeVideo.d("");
                    youTubeVideo.setLanguage("");
                    youTubeVideo.e(String.valueOf(j3));
                    youTubeVideo.a(k);
                    youTubeVideo.a(i4);
                    Util.a(context2, youTubeVideo, GAANA_ENTRY_PAGE.SEARCH_FEED.name());
                }
            }
        });
    }

    public static void a(Context context, String str, String str2, String str3, int i, long j) {
        a(context, str, str2, str3, i, j, -1, -1, -1);
    }

    public static boolean c(BusinessObject businessObject) {
        if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks && DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId())) != null) {
            return true;
        }
        if ((businessObject.getBusinessObjType() == BusinessObjectType.Playlists || businessObject.getBusinessObjType() == BusinessObjectType.Albums) && DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId())) != null) {
            return true;
        }
        return false;
    }

    public static BusinessObject a(AutoComplete autoComplete, GaanaApplication gaanaApplication) {
        String rawTitle = autoComplete.getRawTitle();
        String artwork = autoComplete.getArtwork();
        String rawSubtitle = autoComplete.getRawSubtitle();
        String businessObjectId = autoComplete.getBusinessObjectId();
        BusinessObject businessObject = new BusinessObject();
        switch (SearchCategory.valueOf(autoComplete.getType())) {
            case Artist:
                businessObject = new Artist();
                businessObject.setBusinessObjType(BusinessObjectType.Artists);
                ((Artist) businessObject).setArtwork(artwork);
                break;
            case Radio:
                businessObject = new Radio();
                businessObject.setBusinessObjType(BusinessObjectType.Radios);
                Radio radio = (Radio) businessObject;
                radio.setArtwork(artwork);
                radio.setBusinessObjId(businessObjectId);
                radio.setType(autoComplete.getRadioType());
                break;
            case Album:
                businessObject = new Album();
                businessObject.setBusinessObjType(BusinessObjectType.Albums);
                ((Album) businessObject).setArtwork(artwork);
                break;
            case Playlist:
                businessObject = new Playlist();
                businessObject.setBusinessObjType(BusinessObjectType.Playlists);
                Playlist playlist = (Playlist) businessObject;
                playlist.setArtwork(artwork);
                playlist.setPlaylistId(businessObjectId);
                if (!TextUtils.isEmpty(rawSubtitle) && rawSubtitle.equalsIgnoreCase("My Playlist")) {
                    playlist.setCreatedbyUserId(gaanaApplication.getCurrentUser().getUserProfile().getUserId());
                    break;
                }
            case Track:
                businessObject = new Track();
                businessObject.setBusinessObjType(BusinessObjectType.Tracks);
                Track track = (Track) businessObject;
                track.setStreamUrls(autoComplete.getStreamUrls());
                track.setArtwork(artwork);
                break;
            case video:
                businessObject = new YouTubeVideo();
                YouTubeVideo youTubeVideo = (YouTubeVideo) businessObject;
                youTubeVideo.setBusinessObjType(BusinessObjectType.YouTubeVideos);
                youTubeVideo.d(artwork);
                youTubeVideo.setAtw(artwork);
                youTubeVideo.setName(rawTitle);
                youTubeVideo.b(rawTitle);
                youTubeVideo.a(autoComplete.getVurl());
                youTubeVideo.c(autoComplete.getVurl());
                businessObject.setBusinessObjId(businessObjectId);
                break;
            case OfflineTrack:
                businessObject = new OfflineTrack(businessObjectId, rawTitle, rawSubtitle);
                businessObject.setBusinessObjType(BusinessObjectType.Tracks);
                break;
            case Occasion:
                businessObject.setBusinessObjType(BusinessObjectType.Occasion);
                break;
        }
        businessObject.setBusinessObjId(businessObjectId);
        businessObject.setName(rawTitle);
        businessObject.setLanguage(autoComplete.getLanguage());
        businessObject.setLocalMedia(autoComplete.isLocalMedia());
        return businessObject;
    }

    public static BusinessObject d(BusinessObject businessObject) {
        Track track = new Track();
        track.setBusinessObjId(businessObject.getBusinessObjId());
        track.setName(businessObject.getRawName());
        track.setBusinessObjType(BusinessObjectType.Tracks);
        return track;
    }

    public static boolean a(aw awVar) {
        if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA && PlayerManager.a(C).E() == null) {
            ArrayList n = PlayerManager.a(GaanaApplication.getContext()).n();
            if (n == null || n.size() == 0) {
                d.a().a("PREFERENCE_KEY_SHUFFLE_STATUS", false, true);
                o.a().a(new ArrayList());
            }
            o.a().a(n, PlayerManager.a(GaanaApplication.getContext()).s(), awVar);
        } else if (awVar != null) {
            awVar.onPlayerQueueSavingCompleted();
        }
        return true;
    }

    public static void a(final Context context, final Track track, final View view, final ba baVar) {
        if (TextUtils.isEmpty(Constants.ch) || Constants.ci < 1000) {
            baVar.onPlaySong(view, track);
            return;
        }
        d.a().a("premium_content_track_id", track.getBusinessObjId(), false);
        HashMap a = d.a().a("premium_content_track_data", false);
        Object c = d.a().c("premium_content_track_id", false);
        if (!Constants.cR.equalsIgnoreCase(Constants.cQ)) {
            c = Constants.cS;
        }
        String c2 = d.a().c("PREFERENCE_SESSION_REWARD_TYPE", false);
        long currentTimeMillis;
        if (TextUtils.isEmpty(c2) || !c2.equalsIgnoreCase(Constants.cU)) {
            currentTimeMillis = System.currentTimeMillis();
        } else {
            currentTimeMillis = (long) d.a().b("PREFERENCE_SESSION_HISTORY_COUNT", 0, false);
        }
        if ((a == null || a.get(c) == null || (a.get(c) != null && ((Long) a.get(c)).longValue() < currentTimeMillis)) && !TextUtils.isEmpty(track.getPremiumContent()) && track.getPremiumContent().equalsIgnoreCase(Constants.cV) && !ap.a().d()) {
            if (view != null) {
                ((GaanaActivity) context).setCurrentSongSelectedView(view);
            }
            URLManager uRLManager = new URLManager();
            uRLManager.a("https://api.gaana.com/gaanaplusservice_nxtgen.php?type=get_pr_details");
            uRLManager.a(PremiumContentTextConfig.class);
            uRLManager.a(Priority.HIGH);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.i(false);
            i.a().a(new af() {
                public void onRetreivalComplete(Object obj) {
                    if (!(context instanceof Activity) || ((Activity) context).isFinishing()) {
                        baVar.onPlaySong(view, track);
                    } else if (obj instanceof PremiumContentTextConfig) {
                        if (context instanceof BaseActivity) {
                            ((BaseActivity) context).hideProgressDialog();
                        }
                        new PremiumContentPopUpView(context, (PremiumContentTextConfig) obj, track, view, baVar).show();
                    } else {
                        baVar.onPlaySong(view, track);
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    baVar.onPlaySong(view, track);
                }
            }, uRLManager);
            return;
        }
        baVar.onPlaySong(view, track);
    }

    public static boolean ai() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static LyricsObject a(Track track) {
        LyricsObject lyricsObject = new LyricsObject();
        if (track == null) {
            return null;
        }
        lyricsObject.setId(track.getBusinessObjId());
        if (track.getLyricsType().equalsIgnoreCase("lrc")) {
            lyricsObject.setLyricsType(2);
        } else {
            lyricsObject.setLyricsType(3);
        }
        lyricsObject.setLyricsUrl(track.getLyricsUrl());
        lyricsObject.setTrackName(track.getRawName());
        lyricsObject.setTrackAlbumName(track.getRawAlbumTitle());
        lyricsObject.setTrackArtistName(track.getArtists());
        lyricsObject.setLanguage(track.getLanguage());
        return lyricsObject;
    }

    public static void a(Context context, @NonNull YouTubeVideo youTubeVideo, String str) {
        a(context, youTubeVideo, str, -1);
    }

    public static void a(Context context, @NonNull YouTubeVideo youTubeVideo, String str, int i) {
        if (Constants.cN || !d.g()) {
            aj.a().a(context, context.getResources().getString(R.string.operation_not_supported));
        } else if (!j(context) || GaanaApplication.getInstance().isAppInOfflineMode()) {
            aj.a().a(context, context.getResources().getString(R.string.error_msg_no_connection));
        } else {
            if (GaanaMusicService.t()) {
                com.player_framework.o.a(context, PauseReasons.MEDIA_BUTTON_TAP);
                Constants.dc = true;
            }
            if (com.managers.f.v().w()) {
                com.managers.f.v().F();
                Constants.dc = true;
            }
            BaseGaanaFragment gaanaVideoPlayerFragment = new GaanaVideoPlayerFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(GaanaVideoPlayerFragment.a.d(), youTubeVideo);
            bundle.putString(GaanaVideoPlayerFragment.a.e(), str);
            bundle.putInt(GaanaVideoPlayerFragment.a.f(), i);
            gaanaVideoPlayerFragment.setArguments(bundle);
            ((GaanaActivity) context).displayFragment(gaanaVideoPlayerFragment);
        }
    }
}
