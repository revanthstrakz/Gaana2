package com.gaana.models;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class GaanaThemeModel {
    private static final long serialVersionUID = 1;
    @SerializedName("fab_icon_image")
    private String fabIconImageArtwork;
    @SerializedName("fab_item_id")
    private String fabItemID;
    @SerializedName("fab_item_type")
    private String fabItemType;
    @SerializedName("hv")
    public String hash_value;
    @SerializedName("festive_theme")
    private ArrayList<GaanaTheme> themeArrayList;

    public static class GaanaTheme {
        @SerializedName("bg_img_black")
        private String backgroundImageBlackArtwork;
        @SerializedName("bg_img_white")
        private String backgroundImageWhiteArtwork;
        @SerializedName("fg_gif")
        private String foregroundGif;
        @SerializedName("foreground_img")
        private String foregroundImageArtwork;
        @SerializedName("theme_sponsored")
        private String isSponosored;
        @SerializedName("overlay_showcase")
        private String overlayShowcaseArtwork;
        @SerializedName("overlay_square")
        private String overlaySquareArtwork;
        @SerializedName("player_color")
        private String playerColor;
        @SerializedName("player_bg_image_black")
        private String playerbackgroundImageBlackArtwork;
        @SerializedName("player_bg_image_white")
        private String playerbackgroundImageWhiteArtwork;
        @SerializedName("setting_black")
        private String settingBlackArtwork;
        @SerializedName("setting_white")
        private String settingWhiteArtwork;
        @SerializedName("theme_default")
        private String themeDefault;
        @SerializedName("theme_name")
        private String themeName;

        public String getPlayerColor() {
            return this.playerColor;
        }

        public String getForegroundGif() {
            return this.foregroundGif;
        }

        public String getThemeName() {
            return this.themeName;
        }

        public String getOverlayShowcaseArtwork() {
            return this.overlayShowcaseArtwork;
        }

        public String getOverlaySquareArtwork() {
            return this.overlaySquareArtwork;
        }

        public String getForegroundImageArtwork() {
            return this.foregroundImageArtwork;
        }

        public String getBackgroundImageBlackArtwork() {
            return this.backgroundImageBlackArtwork;
        }

        public String getBackgroundImageWhiteArtwork() {
            return this.backgroundImageWhiteArtwork;
        }

        public String getPlayerbackgroundImageBlackArtwork() {
            return this.playerbackgroundImageBlackArtwork;
        }

        public String getPlayerbackgroundImageWhiteArtwork() {
            return this.playerbackgroundImageWhiteArtwork;
        }

        public String getSettingBlackArtwork() {
            return this.settingBlackArtwork;
        }

        public String getSettingWhiteArtwork() {
            return this.settingWhiteArtwork;
        }

        public String getThemeDefault() {
            return this.themeDefault;
        }

        public boolean isSponsored() {
            return !TextUtils.isEmpty(this.isSponosored) && this.isSponosored.equalsIgnoreCase("1");
        }
    }

    public String getHashValue() {
        return this.hash_value;
    }

    public ArrayList<GaanaTheme> getThemeArrayList() {
        return this.themeArrayList;
    }

    public String getFabItemID() {
        return this.fabItemID;
    }

    public String getFabItemType() {
        return this.fabItemType;
    }

    public String getFabIconImageArtwork() {
        return this.fabIconImageArtwork;
    }
}
