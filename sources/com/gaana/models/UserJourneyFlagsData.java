package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class UserJourneyFlagsData extends BusinessObject {
    @SerializedName("download_settings")
    private DownloadSettings downloadSettings;
    @SerializedName("gapless_enabled")
    private Gapless gapless;
    @SerializedName("journey")
    private Journey journey;
    @SerializedName("mini_player_v4")
    private MiniPlayerV4 mini_player_v4;
    private NewPlayer new_player;
    @SerializedName("player_carousel_view")
    private PlayerCarouselView player_carousel_view;
    @SerializedName("status")
    private int status;
    @SerializedName("tags_visible")
    private Tags tags;
    @SerializedName("view_config")
    private ViewConfig viewConfig;
    @SerializedName("voice_ui")
    private VoiceUi voice_ui;

    public class DownloadSettings {
        @SerializedName("download")
        private boolean download;

        public boolean getDownload() {
            return this.download;
        }

        public void setDownload(boolean z) {
            this.download = z;
        }
    }

    public class Gapless {
        @SerializedName("master")
        private int master;

        public int getMaster() {
            return this.master;
        }

        public void setMaster(int i) {
            this.master = i;
        }
    }

    public class Journey {
        @SerializedName("ad")
        private int ad;
        @SerializedName("click")
        private int click;
        @SerializedName("master")
        private int master;
        @SerializedName("max_batch_interval")
        private int max_batch_interval;
        @SerializedName("max_batch_size")
        private int max_batch_size;
        @SerializedName("min_batch_size")
        private int min_batch_size;
        @SerializedName("playout")
        private int playout;
        @SerializedName("scroll")
        private int scroll;
        @SerializedName("state")
        private int state;

        public int getMaster() {
            return this.master;
        }

        public void setMaster(int i) {
            this.master = i;
        }

        public int getClick() {
            return this.click;
        }

        public void setClick(int i) {
            this.click = i;
        }

        public int getScroll() {
            return this.scroll;
        }

        public void setScroll(int i) {
            this.scroll = i;
        }

        public int getState() {
            return this.state;
        }

        public void setState(int i) {
            this.state = i;
        }

        public int getAd() {
            return this.ad;
        }

        public void setAd(int i) {
            this.ad = i;
        }

        public int getPlayout() {
            return this.playout;
        }

        public void setPlayout(int i) {
            this.playout = i;
        }

        public int getMinBatchSize() {
            return this.min_batch_size;
        }

        public void setMinBatchSize(int i) {
            this.min_batch_size = i;
        }

        public int getMaxBatchSize() {
            return this.max_batch_size;
        }

        public void setMaxBatchSize(int i) {
            this.max_batch_size = i;
        }

        public int getMaxBatchInterval() {
            return this.max_batch_interval;
        }

        public void setMaxBatchInterval(int i) {
            this.max_batch_interval = i;
        }
    }

    public class MiniPlayerV4 {
        @SerializedName("show_center_player")
        private int show_center_player;
        @SerializedName("show_suggestive_label")
        private int show_suggestive_label;

        public int getShowCenterPlayer() {
            return this.show_center_player;
        }

        public void setShowCenterPlayer(int i) {
            this.show_center_player = i;
        }

        public int getShowSuggestiveLabel() {
            return this.show_suggestive_label;
        }

        public void setShowSuggestiveLabel(int i) {
            this.show_suggestive_label = i;
        }
    }

    public class NewPlayer {
        @SerializedName("master")
        private int master;

        public int getMaster() {
            return this.master;
        }

        public void setMaster(int i) {
            this.master = i;
        }
    }

    public class PlayerCarouselView {
        @SerializedName("master")
        private int master;

        public int getMaster() {
            return this.master;
        }

        public void setMaster(int i) {
            this.master = i;
        }
    }

    public class Tags {
        @SerializedName("master")
        private int master;

        public int getMaster() {
            return this.master;
        }

        public void setMaster(int i) {
            this.master = i;
        }
    }

    public static class ViewConfig {
        @SerializedName("entity_play_icon")
        private int playlistPlayIcon;
        @SerializedName("show_lyrics_card")
        private int showLyricsCard;
        @SerializedName("show_prescreen")
        private int showPreScreen;
        @SerializedName("track_playouts")
        private int trackPlayouts;
        @SerializedName("view_all_img")
        private int viewAllImg;

        public boolean showViewAllImg() {
            return this.viewAllImg == 1;
        }

        public boolean showPlaylistPlayIcon() {
            return this.playlistPlayIcon == 1;
        }

        public boolean showTrackPlayouts() {
            return this.trackPlayouts == 1;
        }

        public boolean showLyricsCard() {
            return this.showLyricsCard == 1;
        }

        public boolean showPreScreen() {
            return this.showPreScreen == 1;
        }
    }

    public class VoiceUi {
        @SerializedName("auto_keyboard")
        private int auto_keyboard;
        @SerializedName("auto_play")
        private int auto_play;
        @SerializedName("master")
        private int master;
        @SerializedName("read_play")
        private int read_play;
        @SerializedName("search_card")
        private int search_card;

        public boolean getMaster() {
            return this.master == 1;
        }

        public void setMaster(int i) {
            this.master = i;
        }

        public boolean isToShowSearchCard() {
            return this.search_card == 1;
        }

        public boolean isToReadPlay() {
            return this.read_play == 1;
        }

        public boolean isAutoplayEnabled() {
            return this.auto_play == 1;
        }

        public boolean isAuto_keyboard() {
            return this.auto_keyboard == 1;
        }
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public Journey getJourney() {
        return this.journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public VoiceUi getVoiceUi() {
        return this.voice_ui;
    }

    public void setVoiceUi(VoiceUi voiceUi) {
        this.voice_ui = voiceUi;
    }

    public Tags getTags() {
        return this.tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public Gapless getGapless() {
        return this.gapless;
    }

    public void setGapless(Gapless gapless) {
        this.gapless = gapless;
    }

    public ViewConfig getViewConfig() {
        return this.viewConfig;
    }

    public NewPlayer getNewPlayer() {
        return this.new_player;
    }

    public void setNewPlayer(NewPlayer newPlayer) {
        this.new_player = newPlayer;
    }

    public PlayerCarouselView getPlayerCarouselView() {
        return this.player_carousel_view;
    }

    public void setPlayerCarouselView(PlayerCarouselView playerCarouselView) {
        this.player_carousel_view = playerCarouselView;
    }

    public MiniPlayerV4 getMiniPlayerV4() {
        return this.mini_player_v4;
    }

    public void setMiniPlayerV4(MiniPlayerV4 miniPlayerV4) {
        this.mini_player_v4 = miniPlayerV4;
    }

    public DownloadSettings getDownloadSettings() {
        return this.downloadSettings;
    }

    public void setDownloadSettings(DownloadSettings downloadSettings) {
        this.downloadSettings = downloadSettings;
    }
}
