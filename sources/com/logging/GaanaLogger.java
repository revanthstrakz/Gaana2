package com.logging;

import android.content.Context;
import com.player_framework.GaanaMusicService;
import com.player_framework.f;
import com.services.d;
import com.services.n;

public class GaanaLogger {
    private static GaanaLogger a;
    private TrackLog b = null;
    private a c;
    private String d = "";

    public enum PAGE_SORCE_NAME {
        HOME,
        RADIO,
        DISCOVER,
        MYMUSIC,
        SEARCH,
        FRIEND_ACTIVITY,
        MUSIC_YEAR,
        SOCIAL_FEED,
        ACTIVITY,
        SOCIAL_ACTIVITY,
        CURATED_DOWNLOAD_SUGGESTION,
        FOR_YOU,
        OTHER
    }

    public enum CONTENT_TYPE {
        OTHERS,
        AUDIO_TRACK,
        VIDEO_TRACK,
        AUDIO_VIDEO_CLIP
    }

    public enum PLAYOUT_SECTION_TYPE {
        OTHERS,
        TOP_SECTION,
        PoTH,
        NEW_RELEASES,
        TOP_CHARTS,
        FEATURED_ARTISTS,
        MORE_P_AND_R,
        ONE_TOUCH,
        GAANA_RADIO,
        RADIO_MIRCHI,
        OTHER_RADIOS,
        MY_PLAYLISTS,
        FAVORITES,
        DOWNLOADS,
        DOWNLOADS_NOTIFICATION,
        MY_ACTVITY,
        SEARCH_AUTO_SUGGEST,
        SEARCH_FULL,
        SIMILAR_ALBUM,
        SONG,
        RADIO,
        ARTISTS,
        PUSH,
        SHARE,
        INAPP,
        FRIENDS_ACIVITY,
        OTHER_PROFILE,
        ALBUMS,
        SONG_RADIO,
        DISCOVER,
        LOCAL,
        TRENDING_SONG,
        HEAR_IT_ALL,
        GAANA_SPECIALS,
        GAANA_RECOMMENDS1,
        GAANA_RECOMMENDS2,
        GAANA_RECOMMENDS3,
        GAANA_RECOMMENDS4,
        GAANA_RECOMMENDS5,
        GAANA_RECOMMENDS6,
        HOME_CAROUSEL_VIEW,
        HOME_CAROUSEL_VIEW_2,
        POPULAR_RADIO,
        ARTIST_RADIO,
        MOODS_RADIO,
        LATEST_RADIO,
        FLASHBACK_RADIO,
        YOUTUBE_VIDEO,
        AUTOPLAY,
        PLAYER,
        MYMUSIC_PLAYLIST,
        MYMUSIC_SONGS,
        MYMUSIC_ALBUMS,
        MYMUSIC_RADIO,
        MYMUSIC_ARTIST,
        MYMUSIC_RECENTLYPLAYED,
        DEDICATIONS,
        SOCIAL_FEED,
        SOCIAL_FOLLOW,
        CF_TRACK,
        GAANA_ACTIONS,
        SEARCH_RECOMMENDED,
        SEARCH_VOICE,
        VOICEINT_PLAY,
        VOICEINT_AUTOPLAY,
        MYMUSIC_MUSICHUB,
        SONG_IDENTIFY_HISTORY,
        VOICE_AUTO_SUGGEST,
        RECENT_SEARCH,
        QUICK_SEARCH,
        TRENDING_SEARCH,
        MADE_FOR_YOU,
        GAANA_ORIGINALS,
        GAANA_EXCLUSIVES,
        PLAYLIST_FOR_YOU,
        POPULAR_SONGS_1,
        DISCOVER_CATEGORY_OCCASION,
        SPONSORED_OCCASION,
        HORIZONTAL_VIDEOS,
        SEARCH_FEED
    }

    public enum PLAYOUT_SOURCE {
        OTHERS,
        NETWORK,
        CACHE,
        FILE
    }

    public enum PLAYOUT_SOURCE_NAME {
        OTHERS,
        PLAYLIST,
        ALBUM,
        RADIOMIRCHI,
        TRACK,
        ARTIST,
        GAANARADIO,
        ECHONESSONGTRADIO,
        ECHONESTARTISTRADIO,
        ONETOUCHRADIO
    }

    public enum PLAYOUT_SOURCE_TYPE {
        OTHER,
        SYSTEM_INITIATED,
        USER_INITIATED
    }

    public enum SOURCE_TYPE {
        ZERO,
        OTHER,
        ALBUM,
        PLAYLIST,
        ARTIST,
        RADIO_MIRCHI,
        CHANNEL,
        THIRD_PARTY_APP,
        PREROLL,
        RECOMENDATION_RADIO,
        TRACK,
        MY_FAVORITES,
        MOST_POPULAR,
        ARTIST_RADIO,
        GAANA_RADIO,
        ECHONEST_ARTIST_RADIO,
        ECHONEST_GENRE_RADIO,
        ECHONEST_SONG_RADIO,
        MINI_SITE_TOP_SONGS,
        NITEEN,
        ARTIST_FOLLOW,
        PUSH_NOTIFICATION,
        ONE_TOUCH_RADIO,
        IN_APP,
        FRIENDS_ACTIVITY,
        SEARCH,
        RADIO_SEARCH_SONG,
        RADIO_SEARCH_ARTIST,
        DEEP_LINKING,
        MY_DOWNLOADS,
        ACTOR,
        ACTRESS,
        FB_AUTO,
        LABEL_CHANNEL,
        LABEL,
        FB_BEFORE_AUTO,
        DISCOVER,
        HOURLY_PLAYLIST,
        LOCAL_MUSIC,
        CF_TRACK,
        DISCOVER_RADIO_GAANA,
        TIMESNEWS,
        DOWNLOAD,
        TOIWIDGET,
        OCCASION,
        ADS_DEEPLINK,
        SHOWCASE_IMG,
        TRENDINGWIDGET,
        SHOWCASE_VPL,
        SEARCH_TOP_RESULT
    }

    public static GaanaLogger a() {
        if (a == null) {
            a = new GaanaLogger();
        }
        return a;
    }

    public TrackLog b() {
        return this.b;
    }

    public void a(TrackLog trackLog, Context context) {
        this.b = trackLog;
        b(context);
    }

    public a c() {
        return this.c;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void a(Context context) {
        String c = d.a().c("PREFERENCE_KEY_SAVED_TRACK_LOG", false);
        a().b = (TrackLog) n.a(c);
        try {
            if (this.b != null && !GaanaMusicService.t()) {
                a().a(b.a());
                a().c().a(context);
            }
        } catch (IllegalStateException unused) {
            if (this.b != null) {
                a().a(b.a());
                a().c().a(context);
            }
        }
    }

    public void b(Context context) {
        c(context);
        d.a().a("PREFERENCE_KEY_SAVED_TRACK_LOG", n.a(this.b), false);
    }

    private void c(Context context) {
        d.a().b("PREFERENCE_KEY_SAVED_TRACK_LOG", false);
    }

    public void a(Context context, boolean z, boolean z2) {
        f s = GaanaMusicService.s();
        if (z && s != null) {
            int v = s.v() / 1000;
            if (v <= 180) {
                if (v <= 120 && v <= 60 && v <= 30 && v <= 20) {
                }
                this.d = String.valueOf(v);
            }
        }
    }
}
