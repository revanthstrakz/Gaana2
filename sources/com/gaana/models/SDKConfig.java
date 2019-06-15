package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.facebook.internal.ServerProtocol;
import com.gaana.application.GaanaApplication;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SDKConfig implements Serializable {
    @SerializedName("SDLifetimeCap")
    @Expose
    private int SDLifetimeCap;
    @SerializedName("SDMonthlyCap")
    @Expose
    private int SDMonthlyCap;
    @SerializedName("activity_detection_interval")
    private int activityDetectionInterval;
    @SerializedName("adsfree_session")
    private int adsFreeSession = 1;
    @SerializedName("ads_ram")
    private int adsRAM = 1024;
    @SerializedName("appJacket")
    private AppJacketModel appJacket;
    @SerializedName("autosync_download_interval")
    private int autoSyncSongsInterval;
    @SerializedName("autosync_time_interval")
    private int autoSyncTimeInterval;
    @SerializedName("autologin_email")
    private int autologin_smart;
    @SerializedName("autologin_email_switch")
    private int autologin_smart_switch;
    @SerializedName("cd_active")
    private String cdActive;
    @SerializedName("cd_fcap")
    private int cdFcap = 0;
    @SerializedName("cd_reset")
    private long cdReset = 0;
    @SerializedName("colombiaAdCode")
    private ColombiaAdCode colombiaAdCode;
    @SerializedName("config")
    @Expose
    private Config config;
    @SerializedName("days_interval")
    private int daysInterval = 4;
    @SerializedName("dfpAdCode")
    @Expose
    private DfpAdCode dfpAdCode;
    @SerializedName("diwali_mode")
    private int diwali_mode = 0;
    @SerializedName("educative_screen_on_applaunch")
    private int educative_screen_on_applaunch;
    @SerializedName("fcap")
    private int fcap;
    @SerializedName("CURATED_DIALOG_LIMIT")
    private int firstCuratedSessionLimit;
    @SerializedName("google_login_age_permission")
    private int googleLoginAgePermission;
    @SerializedName("home_feed_session_time")
    private int homeFeedSessionTime = 3;
    @SerializedName("HousePartyOn")
    private int housePartyOn;
    @SerializedName("hv")
    private String hv;
    @SerializedName("identify_song_record_time")
    private int identify_song_record_time;
    @SerializedName("initial_session_time")
    private int initialSessionTime = 3;
    @SerializedName("apptimize_enable")
    private int isApptimizeEnabled;
    @SerializedName("db_search_log")
    private int isDBSearchLogEnabled;
    @SerializedName("lm_switch")
    private int isLocalMusicEnabled;
    @SerializedName("youtube_video")
    private int isYouTubeVideoEnabled;
    @SerializedName("is_add_to_playlist_visible")
    private int is_add_to_playlist_visible;
    @SerializedName("is_referral_active")
    private int is_referral_active = 1;
    @SerializedName("is_referral_banner_active")
    private int is_referral_banner_active = 1;
    @SerializedName("is_tags_visible")
    private int is_tags_visible = 0;
    @SerializedName("lay_hereit_again")
    private String layoutHereIt;
    @SerializedName("lay_trending")
    private String layoutTrending;
    @SerializedName("login_mode")
    private String loginMode;
    @SerializedName("login_fcap")
    private int login_fcap;
    @SerializedName("low_mem_alert")
    private String low_mem_alert;
    @SerializedName("low_mem_threshold")
    private int low_mem_threshold;
    @SerializedName("mandatory_signup")
    private int mandatory_signup;
    @SerializedName("new_detail_page")
    private String new_detail_page;
    @SerializedName("notif_day_repeat")
    private int notif_day_repeat;
    @SerializedName("notif_weekday_range")
    private String notif_weekday_range;
    @SerializedName("notif_weekend_range")
    private String notif_weekend_range;
    @SerializedName("onboard_player_bottom_text")
    private String onBoardPlayerBottomText;
    @SerializedName("onboard_player_need_show")
    private int onBoardPlayerNeedToShow = 0;
    @SerializedName("onboard_player_stop_text")
    private String onBoardPlayerStopText;
    @SerializedName("onboard_time_after_show")
    private int onBoardTimeAfterShow;
    @SerializedName("onboard_player_start_time")
    private int onboard_player_start_time;
    @SerializedName("onboard_player_text")
    private String onboard_player_text;
    @SerializedName("places_api")
    private int placesApi;
    @SerializedName("playback_cache_enabled")
    private int playbackCacheEnabled = 1;
    @SerializedName("pr_content_logic")
    private String prContentLogic;
    @SerializedName("queue_max_sc")
    private int queue_max_sc;
    @SerializedName("queue_next_sc")
    private int queue_next_sc;
    @SerializedName("recent_search_max_sc")
    private int recent_search_max_sc;
    @SerializedName("retuser_signup")
    private int returnuser_signup;
    @SerializedName("retuser_signup_switch")
    private int returnuser_signup_switch;
    @SerializedName("tost_notification")
    private int showToastNotification;
    @SerializedName("signup_session")
    private int signup_session;
    @SerializedName("simpl_flag")
    private int simpl_flag = 1;
    @SerializedName("skip_message")
    private String skip_message;
    @SerializedName("splashAdCode")
    private SplashAdCode splashAdCode;
    @SerializedName("sponsored_content")
    private int sponsored_content;
    @SerializedName("CURATED_DIALOG_SUBSEQUENT_LIMIT")
    private int subsequentCuratedSessionLimit;
    @SerializedName("tc_initialize")
    private int tc_initialize;
    @SerializedName("tc_intervals")
    private int tc_intervals;
    @SerializedName("tc_repeat")
    private String tc_repeat;
    @SerializedName("ts_cache")
    private long timestamp_cache;
    @SerializedName("ts_metadata")
    private long timestamp_metadata;
    @SerializedName("tost_notification_msg")
    private String toastNotificationMsg;
    @SerializedName("track_advanced_cache")
    private int track_advanced_cache = 1;
    @SerializedName("trial_notification_wait_time")
    private int trialNotificationWaitTime;
    @SerializedName("update_home_meta")
    private int update_home_meta;
    @SerializedName("use_secondary_player")
    private int useSecondaryPlayer = 1;
    @SerializedName("vertical_videos_autoplay_active")
    private int vertical_videos_autoplay_active = 1;
    @SerializedName("wait_time")
    private int wait_time_smart_login;
    @SerializedName("wait_time_switch")
    private int wait_time_smart_login_switch;

    public class AppJacketModel implements Serializable {
        private String g_switch;
        private String pub_id;
        private ArrayList<Config> z_config;

        public class Config implements Serializable {
            private String ftac;
            private String sac;
            private String status;
            private String z_id;
            private String zone;

            public String getStatus() {
                return this.status;
            }

            public String getFtac() {
                return this.ftac;
            }

            public String getSac() {
                return this.sac;
            }

            public String getZ_id() {
                return this.z_id;
            }

            public String getZone() {
                return this.zone;
            }
        }

        public String getG_switch() {
            return this.g_switch;
        }

        public String getPub_id() {
            return this.pub_id;
        }

        public ArrayList<Config> getZ_config() {
            return this.z_config;
        }
    }

    public class ColombiaAdCode implements Serializable {
        @SerializedName("ad_config")
        private ArrayList<AdConfig> ad_config;

        public class AdConfig implements Serializable {
            @SerializedName("ad_code")
            private String ad_code;
            @SerializedName("ad_status")
            private String ad_status;
            @SerializedName("ad_title")
            private String ad_title;
            @SerializedName("follow_up")
            private String follow_up;
            @SerializedName("ftac")
            private String ftac;
            @SerializedName("retry")
            private String retry;
            @SerializedName("sac")
            private String sac;
            @SerializedName("st")
            private String st;
            @SerializedName("ti")
            private String ti;

            public String getAd_title() {
                return this.ad_title;
            }

            public String getAd_code() {
                return this.ad_code;
            }

            public String getAd_status() {
                return this.ad_status;
            }

            public String getFtac() {
                return this.ftac;
            }

            public String getSac() {
                return this.sac;
            }

            public String getTi() {
                return this.ti;
            }

            public String getSt() {
                return this.st;
            }

            public String getRetry() {
                return this.retry;
            }

            public String getFollow_up() {
                return this.follow_up;
            }
        }

        public ArrayList<AdConfig> getAd_config() {
            return this.ad_config;
        }
    }

    public class Config implements Serializable {
        @SerializedName("excl_IMA")
        private String excl_IMA;
        @SerializedName("excl_display")
        private String excl_display;
        @SerializedName("incl_dl")
        private String incl_dl;

        public String getIncl_dl() {
            return this.incl_dl;
        }

        public String getExcl_IMA() {
            return this.excl_IMA;
        }

        public String getExcl_display() {
            return this.excl_display;
        }
    }

    public class DfpAdCode implements Serializable {
        @SerializedName("dfp_config")
        @Expose
        private List<DfpConfig> dfpConfig = new ArrayList();
        @SerializedName("dfp_media_config")
        @Expose
        private List<DfpMediaConfig> dfpMediaConfig = new ArrayList();
        @SerializedName("fallback_deeplink_url")
        @Expose
        public String fallback_deeplink_url;
        @SerializedName("fallback_image_url")
        @Expose
        public String fallback_image_url;
        @SerializedName("top_banner_hp_height")
        @Expose
        public String top_banner_hp_height;

        public class DfpConfig {
            @SerializedName("ad_code")
            @Expose
            private String adCode;
            @SerializedName("ad_server")
            @Expose
            private Integer adServer;
            @SerializedName("ad_title")
            @Expose
            private String adTitle;
            @SerializedName("frequency")
            @Expose
            private int frequency;
            @SerializedName("mediation")
            @Expose
            private Integer mediation;
            @SerializedName("st")
            @Expose
            private int startTime;
            @SerializedName("status")
            @Expose
            private Integer status;
            @SerializedName("ti")
            @Expose
            private int timeInterval;

            public String getAdTitle() {
                return this.adTitle;
            }

            public void setAdTitle(String str) {
                this.adTitle = str;
            }

            public String getAdCode() {
                return this.adCode;
            }

            public void setAdCode(String str) {
                this.adCode = str;
            }

            public Integer getAdServer() {
                return this.adServer;
            }

            public void setAdServer(Integer num) {
                this.adServer = num;
            }

            public Integer getMediation() {
                return this.mediation;
            }

            public void setMediation(Integer num) {
                this.mediation = num;
            }

            public Integer getStatus() {
                return this.status;
            }

            public void setStatus(Integer num) {
                this.status = num;
            }

            public Integer getStartTime() {
                return Integer.valueOf(this.startTime);
            }

            public void setStartTime(Integer num) {
                this.startTime = num.intValue();
            }

            public Integer getTimeInterval() {
                return Integer.valueOf(this.timeInterval);
            }

            public void setTimeInterval(Integer num) {
                this.timeInterval = num.intValue();
            }

            public Integer getFrequency() {
                return Integer.valueOf(this.frequency);
            }

            public void setFrequency(Integer num) {
                this.frequency = num.intValue();
            }
        }

        public class DfpMediaConfig {
            @SerializedName("AWC_height")
            private int AWC_height;
            @SerializedName("AWC_width")
            @Expose
            private int AWC_width;
            @SerializedName("ad_code")
            @Expose
            private String adCode;
            @SerializedName("ad_server")
            @Expose
            private Integer adServer;
            @SerializedName("ad_title")
            @Expose
            private String adTitle;
            @SerializedName("frequency")
            private int frequency;
            @SerializedName("mediation")
            @Expose
            private Integer mediation;
            @SerializedName("st")
            @Expose
            private int st;
            @SerializedName("status")
            @Expose
            private Integer status;

            public String getAdTitle() {
                return this.adTitle;
            }

            public void setAdTitle(String str) {
                this.adTitle = str;
            }

            public String getAdCode() {
                return this.adCode;
            }

            public void setAdCode(String str) {
                this.adCode = str;
            }

            public Integer getAdServer() {
                return this.adServer;
            }

            public void setAdServer(Integer num) {
                this.adServer = num;
            }

            public Integer getMediation() {
                return this.mediation;
            }

            public void setMediation(Integer num) {
                this.mediation = num;
            }

            public Integer getStatus() {
                return this.status;
            }

            public void setStatus(Integer num) {
                this.status = num;
            }

            public int getSt() {
                return this.st;
            }

            public int getFrequency() {
                return this.frequency;
            }

            public int getAWC_width() {
                return this.AWC_width;
            }

            public int getAWC_height() {
                return this.AWC_height;
            }
        }

        public List<DfpConfig> getDfpConfig() {
            return this.dfpConfig;
        }

        public List<DfpMediaConfig> getDfpMediaConfig() {
            return this.dfpMediaConfig;
        }

        public void setDfpConfig(List<DfpConfig> list) {
            this.dfpConfig = list;
        }

        public void setDfpMediaConfig(List<DfpMediaConfig> list) {
            this.dfpMediaConfig = list;
        }
    }

    public static class SplashAdCode implements Serializable {
        @SerializedName("ad_title")
        private String adTitle;
        @SerializedName("image_url")
        private String imageUrl;

        public String getImageUrl() {
            return this.imageUrl;
        }

        public String getAdTitle() {
            return this.adTitle;
        }
    }

    public boolean isHousePartyOn() {
        return this.housePartyOn == 1;
    }

    public boolean shouldFetchLoginAgePermission() {
        return this.googleLoginAgePermission == 1;
    }

    public String getNew_detail_page() {
        return this.new_detail_page;
    }

    public void setNew_detail_page(String str) {
        this.new_detail_page = str;
    }

    public int getIs_tags_visible() {
        return this.is_tags_visible;
    }

    public void setIs_tags_visible(int i) {
        this.is_tags_visible = i;
    }

    public int getIs_add_to_playlist_visible() {
        return this.is_add_to_playlist_visible;
    }

    public void setIs_add_to_playlist_visible(int i) {
        this.is_add_to_playlist_visible = i;
    }

    public int getUpdateHomeMeta() {
        return this.update_home_meta;
    }

    public String getHashValue() {
        return this.hv;
    }

    public String getbackPressedMessage() {
        return this.skip_message;
    }

    public int getNotif_day_repeat() {
        return this.notif_day_repeat;
    }

    public String getNotif_weekday_range() {
        return this.notif_weekday_range;
    }

    public String getNotif_weekend_range() {
        return this.notif_weekend_range;
    }

    public int getTrackAdvancedCache() {
        return this.track_advanced_cache;
    }

    public int getPlaybackCacheEnabled() {
        return this.playbackCacheEnabled;
    }

    public int getUseSecondaryPlayer() {
        return this.useSecondaryPlayer;
    }

    public String getPrContentLogic() {
        return this.prContentLogic;
    }

    public void setPrContentLogic(String str) {
        this.prContentLogic = str;
    }

    public boolean getIsApptimizeEnabled() {
        return this.isApptimizeEnabled == 1;
    }

    public boolean isSSOEnabled() {
        return this.loginMode != null && this.loginMode.equals(ServerProtocol.DIALOG_PARAM_SSO_DEVICE);
    }

    public int getLogin_fcap() {
        return this.login_fcap;
    }

    public int getTc_initialize() {
        return this.tc_initialize;
    }

    public int getTc_intervals() {
        return this.tc_intervals;
    }

    public String getTc_repeat() {
        return this.tc_repeat;
    }

    public int getFcap() {
        return this.fcap;
    }

    public boolean isPlacesApiEnabled() {
        return this.placesApi == 1;
    }

    public boolean isDBSearchLogEnabled() {
        return this.isDBSearchLogEnabled == 1;
    }

    public boolean isYouTubeVideoEnabled() {
        return this.isYouTubeVideoEnabled == 1;
    }

    public int getActivityDetectionInterval() {
        return this.activityDetectionInterval;
    }

    public int getAutoSyncSongsInterval() {
        return this.autoSyncSongsInterval;
    }

    public int getCuratedFirstLimit() {
        return this.firstCuratedSessionLimit;
    }

    public int getCuratedSubsequentLimit() {
        return this.subsequentCuratedSessionLimit;
    }

    public int getAutoSyncTimeInterval() {
        return this.autoSyncTimeInterval;
    }

    public int shouldShowToastNotification() {
        return this.showToastNotification;
    }

    public String getToastNotificationMsg() {
        return this.toastNotificationMsg;
    }

    public int isReferralActive() {
        return this.is_referral_active;
    }

    public int isReferralBannerActive() {
        return this.is_referral_banner_active;
    }

    public int isVerticalVideosAutoplayActive() {
        return this.vertical_videos_autoplay_active;
    }

    public int getInitialSessionTime() {
        return this.initialSessionTime;
    }

    public int getHomeFeedSessionTime() {
        return this.homeFeedSessionTime;
    }

    public int getDaysInterval() {
        return this.daysInterval;
    }

    public int getSponsored_content() {
        return this.sponsored_content;
    }

    public int getSDMonthlyCap() {
        return this.SDMonthlyCap;
    }

    public int getSDLifetimeCap() {
        return this.SDLifetimeCap;
    }

    public int getTrialNotificationWaitTime() {
        return this.trialNotificationWaitTime;
    }

    public boolean isEducativeScreenLaunch() {
        return this.educative_screen_on_applaunch == 1;
    }

    public DfpAdCode getDfpAdCode() {
        return this.dfpAdCode;
    }

    public void setDfpAdCode(DfpAdCode dfpAdCode) {
        this.dfpAdCode = dfpAdCode;
    }

    public Config getConfig() {
        return this.config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public int getMaxQueueSize() {
        if (this.queue_max_sc > 0) {
            return this.queue_max_sc;
        }
        return Constants.dg;
    }

    public int getMaxRecentSearchSize() {
        if (this.recent_search_max_sc <= 0) {
            return Constants.dh;
        }
        Constants.dh = this.recent_search_max_sc;
        return this.recent_search_max_sc;
    }

    public int getOnboardPlayStartTimer() {
        if (this.onboard_player_start_time <= 0) {
            return Constants.di;
        }
        Constants.di = this.onboard_player_start_time;
        return this.onboard_player_start_time;
    }

    public String getOnboardPlayerText() {
        if (TextUtils.isEmpty(this.onboard_player_text)) {
            return Constants.dj;
        }
        Constants.dj = this.onboard_player_text;
        return this.onboard_player_text;
    }

    public String getOnBoardPlayerBottomText() {
        if (TextUtils.isEmpty(this.onBoardPlayerBottomText)) {
            return Constants.dk;
        }
        Constants.dk = this.onBoardPlayerBottomText;
        return this.onBoardPlayerBottomText;
    }

    public String getOnBoardPlayerStopText() {
        if (TextUtils.isEmpty(this.onBoardPlayerStopText)) {
            return Constants.dl;
        }
        Constants.dl = this.onBoardPlayerStopText;
        return this.onBoardPlayerStopText;
    }

    public boolean getOnboardPlayerNeedToShow() {
        if (GaanaApplication.getContext().getSharedPreferences("ONBOARD_PLAYER_CREATED_FIRST_TIME", 0).getBoolean("ONBOARD_PLAYER_CREATED_FIRST_TIME", true)) {
            if (this.onBoardPlayerNeedToShow == 1) {
                Constants.dn = true;
            } else if (this.onBoardPlayerNeedToShow == 0) {
                Constants.dn = false;
            }
            return Constants.dn;
        }
        Constants.dn = false;
        return false;
    }

    public int getOnBoardTimeAfterShow() {
        if (this.onBoardTimeAfterShow <= 0) {
            return Constants.dm;
        }
        Constants.dm = this.onBoardTimeAfterShow;
        return this.onBoardTimeAfterShow;
    }

    public int getDummyQueueSize() {
        if (this.queue_next_sc > 0) {
            return this.queue_next_sc;
        }
        return Constants.df;
    }

    public boolean isLayoutTrendingHorizontal() {
        return this.layoutTrending != null && this.layoutTrending.equals("horiz");
    }

    public boolean isLayoutHereItHorizontal() {
        return this.layoutHereIt != null && this.layoutHereIt.equals("horiz");
    }

    public AppJacketModel getAppJacket() {
        return this.appJacket;
    }

    public ColombiaAdCode getColombiaAdCode() {
        return this.colombiaAdCode;
    }

    public int isLocalMusicEnabled() {
        return this.isLocalMusicEnabled;
    }

    public int getReturnuser_signup() {
        return this.returnuser_signup;
    }

    public void setReturnuser_signup(int i) {
        this.returnuser_signup = i;
    }

    public int getReturnuser_signup_switch() {
        return this.returnuser_signup_switch;
    }

    public void setReturnuser_signup_switch(int i) {
        this.returnuser_signup_switch = i;
    }

    public int getAutologin_smart() {
        return this.autologin_smart;
    }

    public void setAutologin_smart(int i) {
        this.autologin_smart = i;
    }

    public int getAutologin_smart_switch() {
        return this.autologin_smart_switch;
    }

    public void setAutologin_smart_switch(int i) {
        this.autologin_smart_switch = i;
    }

    public int getWait_time_smart_login() {
        return this.wait_time_smart_login;
    }

    public void setWait_time_smart_login(int i) {
        this.wait_time_smart_login = i;
    }

    public int getWait_time_smart_login_switch() {
        return this.wait_time_smart_login_switch;
    }

    public void setWait_time_smart_login_switch(int i) {
        this.wait_time_smart_login_switch = i;
    }

    public int getSignup_session() {
        return this.signup_session;
    }

    public void setSignup_session(int i) {
        this.signup_session = i;
    }

    public long getTimestampMetadata() {
        return this.timestamp_metadata;
    }

    public long getTimestampCache() {
        return this.timestamp_cache;
    }

    public int getMandatory_signup() {
        return this.mandatory_signup;
    }

    public void setMandatory_signup(int i) {
        this.mandatory_signup = i;
    }

    public String getSplashAdUrl() {
        return this.splashAdCode != null ? this.splashAdCode.getImageUrl() : "";
    }

    public SplashAdCode getSplashAdCodeParams() {
        return this.splashAdCode;
    }

    public boolean isDiwaliMode() {
        return this.diwali_mode == 1;
    }

    public int getAdsFreeSession() {
        return this.adsFreeSession;
    }

    public void setAdsFreeSession(int i) {
        this.adsFreeSession = i;
    }

    public int getAdsRAM() {
        return this.adsRAM;
    }

    public void setAdsRAM(int i) {
        this.adsRAM = i;
    }

    public String getCdActive() {
        return this.cdActive;
    }

    public void setCdActive(String str) {
        this.cdActive = str;
    }

    public int getCdFcap() {
        return this.cdFcap;
    }

    public void setCdFcap(int i) {
        this.cdFcap = i;
    }

    public long getCdReset() {
        return this.cdReset;
    }

    public void setCdReset(long j) {
        this.cdReset = j;
    }

    public int getIdentifySongRecordTime() {
        return this.identify_song_record_time;
    }

    public int getLow_mem_threshold() {
        return this.low_mem_threshold;
    }

    public void setLow_mem_threshold(int i) {
        this.low_mem_threshold = i;
    }

    public boolean getSimplFlag() {
        return this.simpl_flag == 1;
    }

    public void setSimplFlag(boolean z) {
        if (z) {
            this.simpl_flag = 1;
        } else {
            this.simpl_flag = 0;
        }
    }

    public String getLow_mem_alert() {
        return this.low_mem_alert;
    }

    public void setLow_mem_alert(String str) {
        this.low_mem_alert = str;
    }
}
