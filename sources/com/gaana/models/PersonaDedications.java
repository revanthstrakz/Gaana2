package com.gaana.models;

import android.text.TextUtils;
import com.constants.Constants;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class PersonaDedications extends BusinessObject {
    @SerializedName("bg_color_black")
    private String bgColorBlack;
    @SerializedName("bg_color_white")
    private String bgColorWhite;
    @SerializedName("message")
    private Object message;
    @SerializedName("mob_footer_image")
    private String mobFooterImage;
    @SerializedName("mob_header_image")
    private String mobHeaderImage;
    @SerializedName("mob_logo_free")
    private String mobLogoFree;
    @SerializedName("mob_logo_paid")
    private String mobLogoPaid;
    @SerializedName("personas")
    private ArrayList<PersonaDedication> personas = null;
    @SerializedName("status")
    private int status;
    @SerializedName("entityDescription")
    private String title;
    @SerializedName("userTokenStatus")
    private String userTokenStatus;
    @SerializedName("web_footer_image")
    private String webFooterImage;
    @SerializedName("web_header_image")
    private String webHeaderImage;
    @SerializedName("web_logo_free")
    private String webLogoFree;
    @SerializedName("web_logo_paid")
    private String webLogoPaid;

    public static class PersonaDedication extends BusinessObject {
        @SerializedName("artwork")
        private String artwork;
        @SerializedName("gender")
        private int gender;
        @SerializedName("language")
        private String language;
        @SerializedName("entity_description")
        private String personaDescription;
        @SerializedName("persona_id")
        private String personaId;
        @SerializedName("name")
        private String personaTitle;
        @SerializedName("playlist_id")
        private String playlistId;
        @SerializedName("playlist_seokey")
        private String playlistSeokey;

        public String getLanguage() {
            return this.language;
        }

        public void setLanguage(String str) {
            this.language = str;
        }

        public String getArtwork() {
            if (TextUtils.isEmpty(getAtw())) {
                return this.artwork;
            }
            return getAtw();
        }

        public void setArtwork(String str) {
            this.artwork = str;
        }

        public int getGender() {
            return this.gender;
        }

        public void setGender(int i) {
            this.gender = i;
        }

        public String getPersonaId() {
            return this.personaId;
        }

        public void setPersonaId(String str) {
            this.personaId = str;
        }

        public String getPersonaTitle() {
            return Constants.b(this.personaTitle);
        }

        public String getEnglishPersonaTitle() {
            return Constants.a(this.personaTitle);
        }

        public String getRawPersonaTitle() {
            return this.personaTitle;
        }

        public String getEnglishName() {
            return Constants.a(this.personaTitle);
        }

        public void setPersonaTitle(String str) {
            this.personaTitle = str;
        }

        public String getPersonaDescription() {
            return Constants.b(this.personaDescription);
        }

        public void setPersonaDescription(String str) {
            this.personaDescription = str;
        }

        public String getPlaylistId() {
            return this.playlistId;
        }

        public void setPlaylistId(String str) {
            this.playlistId = str;
        }

        public String getPlaylistSeokey() {
            return this.playlistSeokey;
        }

        public void setPlaylistSeokey(String str) {
            this.playlistSeokey = str;
        }
    }

    public String getName() {
        return Constants.b(this.title);
    }

    public String getEnglishName() {
        return Constants.a(this.title);
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getUserTokenStatus() {
        return this.userTokenStatus;
    }

    public void setUserTokenStatus(String str) {
        this.userTokenStatus = str;
    }

    public Object getMessage() {
        return this.message;
    }

    public void setMessage(Object obj) {
        this.message = obj;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public List<PersonaDedication> getPersonas() {
        return this.personas;
    }

    public void setPersonas(ArrayList<PersonaDedication> arrayList) {
        this.personas = arrayList;
    }

    public String getWebHeaderImage() {
        return this.webHeaderImage;
    }

    public void setWebHeaderImage(String str) {
        this.webHeaderImage = str;
    }

    public String getWebFooterImage() {
        return this.webFooterImage;
    }

    public void setWebFooterImage(String str) {
        this.webFooterImage = str;
    }

    public String getWebLogoFree() {
        return this.webLogoFree;
    }

    public void setWebLogoFree(String str) {
        this.webLogoFree = str;
    }

    public String getMobLogoPaid() {
        return this.mobLogoPaid;
    }

    public void setMobLogoPaid(String str) {
        this.mobLogoPaid = str;
    }

    public String getWebLogoPaid() {
        return this.webLogoPaid;
    }

    public void setWebLogoPaid(String str) {
        this.webLogoPaid = str;
    }

    public String getMobLogoFree() {
        return this.mobLogoFree;
    }

    public void setMobLogoFree(String str) {
        this.mobLogoFree = str;
    }

    public String getMobHeaderImage() {
        return this.mobHeaderImage;
    }

    public void setMobHeaderImage(String str) {
        this.mobHeaderImage = str;
    }

    public String getMobFooterImage() {
        return this.mobFooterImage;
    }

    public void setMobFooterImage(String str) {
        this.mobFooterImage = str;
    }

    public String getBgColorBlack() {
        return this.bgColorBlack;
    }

    public void setBgColorBlack(String str) {
        this.bgColorBlack = str;
    }

    public String getBgColorWhite() {
        return this.bgColorWhite;
    }

    public void setBgColorWhite(String str) {
        this.bgColorWhite = str;
    }

    public ArrayList<?> getArrListBusinessObj() {
        return this.personas;
    }
}
