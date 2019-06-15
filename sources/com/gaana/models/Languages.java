package com.gaana.models;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Languages extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("translation_page")
    private int appDisplayPageNeedToDisplayed = 1;
    @SerializedName("data")
    private ArrayList<Language> arrListLanguages;
    @SerializedName("autologin_email")
    private int autologin_email;
    @SerializedName("autologin_email_switch")
    private int autologin_email_switch;
    @SerializedName("is_skip_enable")
    private boolean isSkipEnabled;
    @SerializedName("language_title")
    private String languageTitle;
    @SerializedName("login_skip")
    private int login_skip;
    @SerializedName("login_swtich")
    private int login_switch;
    @SerializedName("mandatory_signup")
    private int mandatory_signup;
    @SerializedName("skip_message")
    private String skip_message;
    @SerializedName("wait_time")
    private int wait_time;
    @SerializedName("wait_time_switch")
    private int wait_time_switch;

    public class Language extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("translation_artwork")
        private String appTranslationSampleArtwork;
        @SerializedName("translation_enabled")
        private int appTranslationSupported = 1;
        @SerializedName("is_prefered")
        private int is_prefered;
        @SerializedName("language")
        private String language;
        @SerializedName("artwork")
        private String language_img_url;
        @SerializedName("translated")
        private String translated;

        public String getLanguage() {
            return this.language;
        }

        public void setLanguage(String str) {
            this.language = str;
        }

        public String getTranslated() {
            return this.translated;
        }

        public void setTranslated(String str) {
            this.translated = str;
        }

        public int isPrefered() {
            return this.is_prefered;
        }

        public void setIsPrefered(int i) {
            this.is_prefered = i;
        }

        public String getLanguage_img_url() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.language_img_url;
            }
            return getAtw();
        }

        public void setLanguage_img_url(String str) {
            this.language_img_url = str;
        }

        public boolean isAppTranslationEnabled() {
            return this.appTranslationSupported == 1;
        }

        public String getAppTranslationSampleArtwork() {
            return this.appTranslationSampleArtwork;
        }
    }

    public String getbackPressedMessage() {
        return this.skip_message;
    }

    public int getLogin_skip() {
        return this.login_skip;
    }

    public void setLogin_skip(int i) {
        this.login_skip = i;
    }

    public int getLogin_switch() {
        return this.login_switch;
    }

    public void setLogin_switch(int i) {
        this.login_switch = i;
    }

    public int getAutologin_email_switch() {
        return this.autologin_email_switch;
    }

    public void setAutologin_email_switch(int i) {
        this.autologin_email_switch = i;
    }

    public int getAutologin_email() {
        return this.autologin_email;
    }

    public void setAutologin_email(int i) {
        this.autologin_email = i;
    }

    public int getWait_time_switch() {
        return this.wait_time_switch;
    }

    public void setWait_time_switch(int i) {
        this.wait_time_switch = i;
    }

    public int getWait_time() {
        return this.wait_time;
    }

    public void setWait_time(int i) {
        this.wait_time = i;
    }

    public void setSkipEnabled(boolean z) {
        this.isSkipEnabled = z;
    }

    public boolean getSkipEnabled() {
        return this.isSkipEnabled;
    }

    public String getLanguageTitle() {
        return this.languageTitle;
    }

    public boolean getAppDisplayPageNeededToDisplay() {
        return this.appDisplayPageNeedToDisplayed == 1;
    }

    public Language getLanguage(String str, int i) {
        Language language = new Language();
        language.setLanguage(str);
        language.setIsPrefered(i);
        return language;
    }

    public void setArrList(ArrayList<?> arrayList) {
        this.arrListLanguages = arrayList;
    }

    public ArrayList<?> getArrListBusinessObj() {
        return this.arrListLanguages;
    }

    public int getMandatory_signup() {
        return this.mandatory_signup;
    }

    public void setMandatory_signup(int i) {
        this.mandatory_signup = i;
    }
}
