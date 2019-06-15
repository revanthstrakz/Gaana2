package com.gaana.analytics;

import android.app.Activity;
import android.app.Application;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.comscore.measurement.MeasurementDispatcher;
import com.constants.Constants;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.gaana.application.GaanaApplication;
import com.gaana.login.UserInfo;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.Languages.Language;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.iabutils.c;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.managers.URLManager.BusinessObjectType;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.utils.MoEHelperConstants;
import com.player_framework.GaanaMusicService.PLAY_TYPE;
import com.services.d;
import com.til.colombia.android.internal.e;
import com.utilities.Util;
import com.utilities.g;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONObject;

public class MoEngage {
    public static final String MoE_Tag = "MoEngage:";
    static final String PREF_MOENGAGE_FIRST_VIEW_SECTION = "PREF_MOENGAGE_FIRST_VIEW_SECTION";
    private static MoEngage instance;
    private String lastProductId = null;
    private boolean locationReported = false;
    private MoEHelper moEHelper = null;
    private String sectionViewed = null;

    private MoEngage() {
        if (this.moEHelper == null) {
            this.moEHelper = MoEHelper.getInstance(GaanaApplication.getContext());
        }
    }

    public static MoEngage getInstance() {
        if (instance == null) {
            instance = new MoEngage();
        }
        return instance;
    }

    public void autoIntegrate(Application application) {
        this.moEHelper.autoIntegrate(application);
    }

    public void setDebugMode() {
        if (Constants.aK) {
            this.moEHelper.setLogStatus(true);
            this.moEHelper.setLogLevel(5);
            return;
        }
        this.moEHelper.setLogStatus(false);
    }

    public void MoTrackEvent(String str, JSONObject jSONObject) {
        this.moEHelper.trackEvent(str, jSONObject);
        if (Constants.aK) {
            String str2 = MoE_Tag;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(jSONObject.toString());
            Log.d(str2, stringBuilder.toString());
        }
    }

    public void MoSetUserAttribute(String str, String str2) {
        this.moEHelper.setUserAttribute(str, str2);
    }

    public void MoSetUserAttributeInt(String str, int i) {
        this.moEHelper.setUserAttribute(str, i);
    }

    public void MoSetUserAttributeBoolean(String str, boolean z) {
        this.moEHelper.setUserAttribute(str, z);
    }

    public void MoSetUserAttributeDate(String str, Date date) {
        this.moEHelper.setUserAttribute(str, date);
        if (Constants.aK) {
            String str2 = MoE_Tag;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(date);
            Log.d(str2, stringBuilder.toString());
        }
    }

    public void setExistingUser(boolean z) {
        this.moEHelper.setExistingUser(z);
    }

    public void reportOnFirstLaunch() {
        MoSetUserAttribute("DeviceID", Util.l(GaanaApplication.getContext()));
        MoSetUserAttributeDate("AppFirstLaunch", new Date());
        reportPushNotificationSettings();
    }

    public void reportAppLaunched() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Date", new Date());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("AppLaunch", jSONObject);
    }

    public void reportPushNotificationSettings() {
        MoSetUserAttributeBoolean("MusicRecommendations", d.a().b("PREFERENCE_KEY_NOTIFICATION_MUSIC_RECOMMENDATIONS", true, false));
        MoSetUserAttributeBoolean("FavoritePlaylist", d.a().b("PREFERENCE_KEY_NOTIFICATION_FAVORITE_PLAYLIST", true, false));
        MoSetUserAttributeBoolean("FollowsMe", d.a().b("PREFERENCE_KEY_NOTIFICATION_FOLLOW_UPDATES", true, false));
    }

    public void reportUserNotLoggedIn() {
        MoSetUserAttribute("LoginStatus", "NotLoggedIn");
    }

    public void reportOnLogin(UserInfo userInfo) {
        MoSetUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_USER_NAME, userInfo.getUserProfile().getFullname());
        MoSetUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_USER_EMAIL, userInfo.getUserProfile().getEmail());
        MoSetUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_UNIQUE_ID, userInfo.getUserProfile().getUserId());
        MoSetUserAttribute(MoEHelperConstants.USER_ATTRIBUTE_USER_GENDER, userInfo.getUserProfile().getSex());
        if (!TextUtils.isEmpty(userInfo.getUserProfile().getPhoneNumber())) {
            MoSetUserAttribute("MobileNumber", userInfo.getUserProfile().getPhoneNumber());
        }
        reportUserInfoChanged(userInfo);
        reportPushNotificationSettings();
        MoSetUserAttribute("LoginStatus", "LoggedIn");
        switch (userInfo.getLoginType()) {
            case FB:
                MoSetUserAttribute("ConnectType", "FB");
                break;
            case GOOGLE:
                MoSetUserAttribute("ConnectType", "GOOGLE");
                break;
            case GAANA:
                MoSetUserAttribute("ConnectType", "GAANA");
                break;
            case PHONENUMBER:
                MoSetUserAttribute("ConnectType", "PHONE");
                break;
        }
        MoSetUserAttributeDate("LastSeen", new Date());
        MoSetUserAttributeBoolean("LoginAtleastOnce", true);
    }

    public void reportUserInfoChanged(UserInfo userInfo) {
        MoSetUserAttribute("UserAccountType", userInfo.getUserSubscriptionData().getServerAccountType());
        if (userInfo.getUserSubscriptionData().getServerAccountType().equalsIgnoreCase("trial")) {
            MoSetUserAttributeBoolean("HasTriedTrial", true);
        }
        MoSetUserAttribute("LastPaymentType", userInfo.getUserSubscriptionData().getSubscriptionType());
        if (userInfo.getUserSubscriptionData().getExpiryDate() != null) {
            long time = (userInfo.getUserSubscriptionData().getExpiryDate().getTime() - new Date().getTime()) / MeasurementDispatcher.MILLIS_PER_DAY;
            if (time < 0) {
                time = -1;
            }
            MoSetUserAttributeDate("ExpiryDate", userInfo.getUserSubscriptionData().getExpiryDate());
            MoSetUserAttributeInt("subscriptionRemaining", (int) time);
        }
        try {
            Long valueOf = Long.valueOf(1000 * Long.parseLong(userInfo.getUserSubscriptionData().getLastPayment()));
            MoSetUserAttribute("LastSubscriptionDate", new SimpleDateFormat("dd/MM/yy").format(valueOf));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public void reportOnLogout() {
        this.moEHelper.logoutUser();
        MoSetUserAttribute("LoginStatus", "LoggedOut");
    }

    public void reportLanguagesSelected(String str) {
        MoSetUserAttribute("LanguagesSelected", str);
    }

    public void reportWasReferred(String str) {
        MoSetUserAttribute("WasReferred", str);
    }

    public void reportLanguagesScreenViewed() {
        MoSetUserAttributeBoolean("LanguageSet", true);
    }

    public void reportNewUser() {
        MoSetUserAttribute("NewUserFromAppVersion", Constants.cz);
    }

    public void reportWhetherAppRated(boolean z) {
        MoSetUserAttributeBoolean("HasRatedApp", z);
    }

    public void reportLoginStarted(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("loginType", str);
            jSONObject.put("date", new Date());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("LoginStarted", jSONObject);
    }

    public void reportRateUsAction(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(NativeProtocol.WEB_DIALOG_ACTION, str);
            jSONObject.put("date", new Date());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("RateUsAction", jSONObject);
    }

    public void reportFeedbackAction(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(NativeProtocol.WEB_DIALOG_ACTION, str);
            jSONObject.put("date", new Date());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("FeedbackAction", jSONObject);
    }

    public void reportEnjoyingGaanaAction(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(NativeProtocol.WEB_DIALOG_ACTION, str);
            jSONObject.put("date", new Date());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("EnjoyingGaana", jSONObject);
    }

    public void reportTrialTaken() {
        MoSetUserAttributeBoolean("TrialTaken", true);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("DateTrialTaken", new Date());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("TrialActivated", jSONObject);
    }

    public void reportCuratedDownloadEvent() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isDownloadStarted", true);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("CuratedDownloadStarted", jSONObject);
    }

    public void reportLanguageSet(ArrayList<Language> arrayList) {
        JSONObject jSONObject = new JSONObject();
        try {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Language language = (Language) it.next();
                if (language.isPrefered() == 1) {
                    jSONObject.put(language.getLanguage(), true);
                } else {
                    jSONObject.put(language.getLanguage(), false);
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("LanguageSet", jSONObject);
    }

    public void reportPaymentInitiated(String str, ProductItem productItem) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(InternalAvidAdSessionContext.CONTEXT_MODE, str);
            jSONObject.put("duration", productItem.getDuration_days());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(productItem.getP_cost_curr());
            stringBuilder.append(" ");
            stringBuilder.append(productItem.getP_cost());
            jSONObject.put("cost", stringBuilder.toString());
            jSONObject.put("productID", productItem.getP_id());
            if (this.lastProductId == null) {
                jSONObject.put("multiplePaymentsViewed", false);
                this.lastProductId = productItem.getP_id();
            } else if (productItem.getP_id().equalsIgnoreCase(this.lastProductId)) {
                jSONObject.put("multiplePaymentsViewed", false);
            } else {
                jSONObject.put("multiplePaymentsViewed", true);
                this.lastProductId = productItem.getP_id();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("paymentInitiated", jSONObject);
    }

    public void reportOnPaymentFailed(ProductItem productItem) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("DateOfPurchase", new Date());
            jSONObject.put("Duration", productItem.getDuration_days());
            jSONObject.put("Product_id", productItem.getP_id());
            jSONObject.put("Payment_mode", productItem.getP_payment_mode());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("paymentFailed", jSONObject);
    }

    public void reportOnPaymentCompletedGoogle(ProductItem productItem, c cVar, UserInfo userInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("DateOfPurchase", new Date());
            jSONObject.put("Duration", productItem.getDesc());
            jSONObject.put("Product_id", cVar.b());
            jSONObject.put("Payment_mode", productItem.getP_payment_mode());
            jSONObject.put("PaymentDone", "Yes");
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("Purchase", jSONObject);
    }

    public void reportOnPaymentCompleted(ProductItem productItem, UserInfo userInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("DateOfPurchase", new Date());
            jSONObject.put("Duration", productItem.getDesc());
            jSONObject.put("Product_id", productItem.getP_id());
            jSONObject.put("Payment_mode", productItem.getP_payment_mode());
            jSONObject.put("PaymentDone", "Yes");
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("Purchase", jSONObject);
    }

    public void reportDownloadSuccess(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("downloadSuccess", z);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("download", jSONObject);
    }

    public void reportAddToFavorites(BusinessObject businessObject) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", businessObject.getBusinessObjType());
            jSONObject.put("name", businessObject.getEnglishName());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("Favorites", jSONObject);
    }

    public void reportDownload(BusinessObject businessObject) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", businessObject.getBusinessObjType());
            jSONObject.put("name", businessObject.getEnglishName());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("Download", jSONObject);
    }

    public void reportShareItem(BusinessObject businessObject) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", businessObject.getBusinessObjType());
            jSONObject.put("name", businessObject.getEnglishName());
            if (businessObject.getBusinessObjType() == BusinessObjectType.Playlists) {
                jSONObject.put("createdBy", ((Playlist) businessObject).getCreatedby());
            }
            if (businessObject.getBusinessObjType() == BusinessObjectType.Albums) {
                jSONObject.put("language", ((Album) businessObject).getLanguage());
            }
            if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks) {
                jSONObject.put("language", ((Track) businessObject).getLanguage());
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("Share", jSONObject);
    }

    public void reportReferralSource(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ShareConstants.FEED_SOURCE_PARAM, str);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("invite", jSONObject);
    }

    public void reportItemPlayed(Track track, String str, PLAY_TYPE play_type) {
        if (track != null && str != null && play_type != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("trackTitle", track.getEnglishName());
                jSONObject.put("albumName", track.getEnglishAlbumTitle());
                jSONObject.put("artistName", track.getEnglishArtistNames());
                jSONObject.put("language", track.getLanguage());
                jSONObject.put("playingFromSource", str);
                jSONObject.put("playType", play_type.toString());
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
            MoTrackEvent("Play", jSONObject);
        }
    }

    public void reportAddToPlaylist(BusinessObject businessObject) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", businessObject.getEnglishName());
            jSONObject.put("type", businessObject.getBusinessObjType());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("addToPlaylist", jSONObject);
    }

    public void reportLocation(Location location) {
        if (!this.locationReported) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("City", d.a().c("PREF_CITY_NAME", false));
                jSONObject.put("State", d.a().c("PREF_STATE_NAME", false));
                jSONObject.put("Country", d.a().c("PREF_COUNTRY_CODE", false));
                if (location != null) {
                    jSONObject.put("Latitude", location.getLatitude());
                    jSONObject.put("Longitude", location.getLongitude());
                    this.moEHelper.setUserLocation(location.getLatitude(), location.getLongitude());
                }
                MoTrackEvent(e.e, jSONObject);
                this.locationReported = true;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public void reportSectionViewedEvent(String str) {
        JSONObject jSONObject;
        if (TextUtils.isEmpty(this.sectionViewed)) {
            this.sectionViewed = d.a().c(PREF_MOENGAGE_FIRST_VIEW_SECTION, false);
            if (TextUtils.isEmpty(this.sectionViewed)) {
                this.sectionViewed = str;
                d.a().a(PREF_MOENGAGE_FIRST_VIEW_SECTION, this.sectionViewed, false);
                jSONObject = new JSONObject();
                try {
                    jSONObject.put(Promotion.ACTION_VIEW, str);
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
                MoTrackEvent(Promotion.ACTION_VIEW, jSONObject);
                return;
            }
        }
        if (!this.sectionViewed.contains(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.sectionViewed);
            stringBuilder.append(",");
            stringBuilder.append(str);
            this.sectionViewed = stringBuilder.toString();
            d.a().a(PREF_MOENGAGE_FIRST_VIEW_SECTION, this.sectionViewed, false);
            jSONObject = new JSONObject();
            try {
                jSONObject.put(Promotion.ACTION_VIEW, str);
            } catch (Exception e2) {
                ThrowableExtension.printStackTrace(e2);
            }
            MoTrackEvent(Promotion.ACTION_VIEW, jSONObject);
        }
    }

    public void reportNotificationClickedEvent(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Time", new Date());
            jSONObject.put("DeepLink", str);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        MoTrackEvent("NotificationClicked", jSONObject);
    }

    public void sendPreburnUserAttribute() {
        MoSetUserAttribute("Preburn", g.c());
    }

    public void sendThemeChangeAttribute(boolean z) {
        MoSetUserAttribute("ThemeChanged", z ? "White" : "Black");
    }

    public void reportSilentPush() {
        if (!d.a().b("SILENT_PUSH_DATA_SENT", false, false)) {
            MoSetUserAttribute("gaana_DeviceID", Util.l(GaanaApplication.getContext()));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Build.MANUFACTURER);
            stringBuilder.append(Build.BRAND);
            stringBuilder.append(Build.MODEL);
            MoSetUserAttribute("gaana_DeviceModel", stringBuilder.toString());
            d.a().a("SILENT_PUSH_DATA_SENT", true, false);
        }
        MoSetUserAttribute("gaana_OS_Version", VERSION.RELEASE);
        MoSetUserAttribute("gaana_NetworkType", Util.p(GaanaApplication.getInstance()));
    }

    public void onFragmentStart(Activity activity, String str) {
        this.moEHelper.onFragmentStart(activity, str);
    }

    public void onFragmentStop(Activity activity, String str) {
        this.moEHelper.onFragmentStop(activity, str);
    }
}
