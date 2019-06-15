package com.auto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Callback;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.media.session.PlaybackStateCompat.Builder;
import android.support.v4.media.session.PlaybackStateCompat.CustomAction;
import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.constants.Constants.ErrorType;
import com.constants.c;
import com.constants.c.d;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.LocalTrack;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.i.i;
import com.i.j;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.PlayerConstants.RepeatModes;
import com.player_framework.PlayerStatus.PlayerStates;
import com.player_framework.f;
import com.player_framework.m;
import com.player_framework.n;
import com.player_framework.o;
import java.util.ArrayList;
import java.util.Iterator;

public class a implements com.android.volley.i.a, b, ad.b {
    n a = new n() {
        public void onPlayerRepeatReset(boolean z) {
        }

        public void onStreamingQualityChanged(int i) {
        }

        public void onPlayerPlay() {
            a.this.m();
        }

        public void onPlayerPause() {
            a.this.n();
        }

        public void onPlayerResume() {
            a.this.o();
        }

        public void onPlayerStop() {
            a.this.l();
        }

        public void onPlayPrevious(boolean z, boolean z2) {
            a.this.a(z, z2);
        }

        public void onPlayNext(boolean z, boolean z2) {
            a.this.b(z, z2);
        }

        public void displayErrorDialog(String str, ErrorType errorType) {
            a.this.a(str, PlayerStates.INVALID);
        }

        public void displayErrorToast(String str, int i) {
            a.this.a(str, PlayerStates.INVALID);
        }
    };
    private int[] b = new int[]{R.drawable.auto_icon_repeat, R.drawable.vector_player_repeat_one_white, R.drawable.auto_icon_repealall};
    private MediaSessionCompat c;
    private com.auto.a.a d;
    private Bundle e = null;
    private String f;
    private boolean g = false;
    private Context h;
    private GaanaApplication i;
    private String j;
    private int k = 0;
    private PlaybackStateCompat l;
    private String m = "prefix_query";
    private String n = "https://api.gaana.com/index.php?type=v_search&voice_callback=";
    private SOURCE_TYPE o = SOURCE_TYPE.OTHER;
    private m p = new m() {
        public void onAdEventUpdate(f fVar, AdEvent adEvent) {
        }

        public void onBufferingUpdate(f fVar, int i) {
        }

        public void onInfo(f fVar, int i, int i2) {
        }

        public void onPrepared(f fVar) {
            a.this.k();
        }

        public void onError(f fVar, int i, int i2) {
            if (i == -1000 || i == -1001) {
                a.this.l();
            }
        }

        public void onCompletion(f fVar) {
            a.this.a(null, PlayerStates.STOPPED);
        }
    };

    private final class a extends Callback {
        public void onFastForward() {
        }

        public void onPlayFromUri(Uri uri, Bundle bundle) {
        }

        public void onRewind() {
        }

        private a() {
        }

        /* synthetic */ a(a aVar, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onPlay() {
            a.this.h();
        }

        public void onPlayFromMediaId(String str, Bundle bundle) {
            a.this.a(str);
        }

        public void onPlayFromSearch(String str, Bundle bundle) {
            a aVar = a.this;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a.this.m);
            stringBuilder.append(str);
            aVar.a(stringBuilder.toString());
        }

        public void onSkipToQueueItem(long j) {
            a aVar = a.this;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("TR");
            stringBuilder.append(String.valueOf(j));
            stringBuilder.append("SK");
            aVar.a(stringBuilder.toString());
        }

        public void onPause() {
            a.this.h();
        }

        public void onSkipToNext() {
            o.f(a.this.h);
        }

        public void onSkipToPrevious() {
            o.e(a.this.h);
        }

        public void onStop() {
            o.d(a.this.h);
        }

        public void onSeekTo(long j) {
            o.a(a.this.h, (int) j);
        }

        public void onCustomAction(String str, Bundle bundle) {
            if (str.equals("obj_favorite")) {
                BusinessObject b = PlayerManager.a(a.this.h).i().b();
                b.setFavorite(Boolean.valueOf(b.isFavorite().booleanValue() ^ 1));
                if (b.isUserFavorited()) {
                    com.managers.n.a().b(b);
                } else {
                    com.managers.n.a().c(b);
                }
            } else if (str.equals("player_repeat")) {
                a.this.a(a.this.k);
            }
            a.this.a(null, PlayerStates.INVALID);
        }
    }

    public a(MediaSessionCompat mediaSessionCompat, com.auto.a.a aVar) {
        this.c = mediaSessionCompat;
        this.d = aVar;
        this.h = GaanaApplication.getContext();
        this.i = GaanaApplication.getInstance();
    }

    public void a() {
        this.e = new Bundle();
        this.c.setCallback(new a(this, null));
        this.c.setFlags(3);
        com.auto.b.a.a(this.e, true, true, true);
        this.c.setExtras(this.e);
        if (AutoMediaBrowserService.a()) {
            this.c.setQueue(this.d.e());
        }
        o.a("listener_android_auto_player", this.a);
        o.a("listener_android_auto_player", this.p);
    }

    public void b() {
        com.managers.n.a().c();
        com.managers.f.v().m();
        a(null, PlayerStates.INVALID);
    }

    private void a(String str) {
        GaanaApplication.getInstance().setCurrentPageName("AndroidAuto");
        GaanaApplication.getInstance().setPlayoutSectionName("AndroidAuto");
        boolean z = true;
        this.c.setActive(true);
        if (this.g && !TextUtils.isEmpty(this.f)) {
            j.a().a(this.f);
        }
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Tracks);
        StringBuilder stringBuilder;
        String stringBuilder2;
        if (str.startsWith(this.m)) {
            this.g = true;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.n);
            stringBuilder.append(str.replaceFirst(this.m, ""));
            stringBuilder2 = stringBuilder.toString();
            this.o = SOURCE_TYPE.TRACK;
            uRLManager.a(stringBuilder2);
        } else if (str.contains("PL")) {
            this.j = str.substring(2);
            this.g = true;
            uRLManager.a(c.p);
            stringBuilder = new StringBuilder();
            stringBuilder.append(uRLManager.k());
            stringBuilder.append(this.j);
            stringBuilder.append("&playlist_type=playlist");
            uRLManager.a(stringBuilder.toString());
            this.o = SOURCE_TYPE.PLAYLIST;
        } else if (str.contains("AL")) {
            this.g = true;
            this.j = str.substring(2);
            stringBuilder = new StringBuilder();
            stringBuilder.append(c.o);
            stringBuilder.append(this.j);
            uRLManager.a(stringBuilder.toString());
            this.o = SOURCE_TYPE.ALBUM;
        } else if (str.contains("TR")) {
            String substring = str.substring(2);
            if (substring.contains("SK")) {
                substring = substring.replace("SK", "");
            } else {
                z = false;
            }
            this.j = substring;
            this.o = SOURCE_TYPE.TRACK;
            a(this.j, z);
        } else if (str.contains(d.d)) {
            this.j = str.substring(2);
            ad.a(GaanaApplication.getContext()).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", this.j).replace("<radio_type>", d.d), SOURCE_TYPE.GAANA_RADIO.ordinal(), this.d.b(this.j));
            ad.a(this.h).a((ad.b) this);
            return;
        } else if (str.contains(d.c)) {
            this.j = str.substring(2);
            ad.a(this.h).a(this.d.b(this.j));
            ad.a(this.h).a((ad.b) this);
            return;
        } else if (str.contains("PT")) {
            this.j = str.substring(2);
            b(this.j);
            return;
        } else if (str.contains("AR")) {
            this.g = true;
            this.j = str.substring(2);
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("https://api.gaana.com/index.php?type=artist&subtype=artist_track_listing&genre_id=");
            stringBuilder3.append("");
            stringBuilder3.append("&artist_id=");
            stringBuilder3.append(this.j);
            stringBuilder2 = stringBuilder3.toString();
            this.o = SOURCE_TYPE.ARTIST;
            uRLManager.a(stringBuilder2);
        }
        i.a().a(uRLManager, str, (b) this, (com.android.volley.i.a) this);
        this.f = str;
    }

    private long g() {
        switch (PlayerManager.a(GaanaApplication.getContext()).m()) {
            case GAANA:
                if (PlayerManager.a(this.h).e() || !PlayerManager.a(this.h).t()) {
                    return 68918;
                }
                return 68886;
            case GAANA_RADIO:
                if (ad.a(GaanaApplication.getContext()).n().booleanValue()) {
                    return 68902;
                }
                break;
        }
        return 68870;
    }

    public void a(String str, PlayerStates playerStates) {
        Builder actions = new Builder().setActions(g());
        int i = 2;
        int i2 = 3;
        int i3 = 0;
        if (playerStates == PlayerStates.INVALID) {
            if (GaanaMusicService.s().isPlaying()) {
                i3 = GaanaMusicService.s().v();
                i = 3;
            } else if (GaanaMusicService.s().m()) {
                i = 8;
            }
            a(actions);
            i2 = i;
        } else if (TextUtils.isEmpty(str)) {
            switch (playerStates) {
                case PLAYING:
                    i3 = GaanaMusicService.s().v();
                    break;
                case PAUSED:
                    i3 = GaanaMusicService.s().v();
                    i2 = 2;
                    break;
                case STOPPED:
                    i2 = 1;
                    break;
            }
            a(actions);
        } else {
            actions.setErrorMessage(str);
            i2 = 7;
        }
        actions.setState(i2, (long) i3, 1.0f, SystemClock.elapsedRealtime());
        this.l = actions.build();
        if (this.c != null) {
            this.c.setActive(true);
            this.c.setPlaybackState(this.l);
            this.c.setMetadata(this.d.g());
            if (AutoMediaBrowserService.a()) {
                this.c.setQueue(this.d.e());
            }
        }
    }

    public void a(Builder builder) {
        boolean loginStatus = GaanaApplication.getInstance().getCurrentUser().getLoginStatus();
        PlayerTrack i = PlayerManager.a(GaanaApplication.getContext()).i();
        if (i != null) {
            BusinessObject b = i.b();
            if (b != null) {
                this.k = com.services.d.a().b("PREFERENCE_KEY_REPEAT_STATUS", 2, true);
                if (this.k == 1) {
                    PlayerManager.a(this.h).b(true);
                } else if (this.k == 2) {
                    PlayerManager.a(this.h).c(true);
                }
                int i2 = com.managers.n.a().a(b) ? R.drawable.auto_icon_favorited : R.drawable.auto_icon_favorite;
                int i3 = this.b[this.k];
                if (loginStatus && !(b instanceof LocalTrack)) {
                    builder.addCustomAction(new CustomAction.Builder("obj_favorite", GaanaApplication.getContext().getResources().getString(R.string.favorite), i2).build());
                }
                if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA) {
                    builder.addCustomAction(new CustomAction.Builder("player_repeat", GaanaApplication.getContext().getResources().getString(R.string.player_repeat), i3).build());
                }
            }
        }
    }

    public void c() {
        o.b("listener_android_auto_player");
        o.d("listener_android_auto_player");
        if (this.c != null) {
            this.c.release();
        }
        this.c = null;
        this.d = null;
    }

    public void onResponse(Object obj) {
        this.g = false;
        BusinessObject businessObject = (BusinessObject) obj;
        if (businessObject != null && businessObject.getArrListBusinessObj() != null) {
            ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
            if (arrListBusinessObj != null && arrListBusinessObj.size() != 0) {
                this.i.setCurrentBusObjInListView(arrListBusinessObj);
                PlayerManager.a(GaanaApplication.getContext()).a(this.j, this.o.ordinal(), "AndroidAuto", null, arrListBusinessObj, GaanaApplication.getContext(), true);
                PlayerManager.a(GaanaApplication.getContext()).a(PlayerType.GAANA);
                j();
            }
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.g = false;
    }

    public void d() {
        j();
    }

    public void e() {
        a(null, PlayerStates.INVALID);
    }

    private void a(String str, boolean z) {
        PlayerTrack playerTrack = null;
        ArrayList arrayList;
        if (z) {
            arrayList = new ArrayList();
            ArrayList n = PlayerManager.a(this.h).n();
            arrayList.addAll(n);
            Iterator it = n.iterator();
            while (it.hasNext()) {
                PlayerTrack playerTrack2 = (PlayerTrack) it.next();
                if (playerTrack2.h().equals(str)) {
                    playerTrack = playerTrack2;
                }
            }
            if (n != null) {
                PlayerManager.a(this.h).b(arrayList, playerTrack, 0);
                PlayerManager.a(this.h).a(PlayerType.GAANA, this.h);
                j();
                return;
            }
            return;
        }
        arrayList = new ArrayList();
        arrayList = (ArrayList) this.d.d();
        Iterator it2 = arrayList.iterator();
        PlayerTrack playerTrack3 = null;
        while (it2.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it2.next();
            if (businessObject.getBusinessObjId().equals(str)) {
                playerTrack3 = com.logging.d.a().a(null, businessObject);
            }
        }
        ArrayList a = com.logging.d.a().a(null, arrayList);
        if (a != null) {
            PlayerManager.a(this.h).b(a, playerTrack3, 0);
            PlayerManager.a(this.h).a(PlayerType.GAANA, this.h);
            j();
        }
    }

    private void b(String str) {
        PlayerTrack playerTrack;
        for (int i = 0; i < PlayerManager.a(this.h).n().size(); i++) {
            if (str.equals(((PlayerTrack) PlayerManager.a(this.h).n().get(i)).h())) {
                playerTrack = (PlayerTrack) PlayerManager.a(this.h).n().get(i);
                break;
            }
        }
        playerTrack = null;
        if (playerTrack != null) {
            playerTrack.c(false);
            playerTrack.d(true);
            PlayerManager.a(this.h).c();
            PlayerManager.a(this.h).a(null, playerTrack, 999999);
            PlayerManager.a(this.h).a(PlayerType.GAANA, this.h);
            j();
        }
    }

    private void h() {
        if (!ad.a(this.h).o().booleanValue()) {
            f();
        } else if (GaanaMusicService.t()) {
            o.d(this.h);
        } else {
            f();
        }
    }

    public void f() {
        if (GaanaMusicService.t() || GaanaMusicService.s().m() || GaanaMusicService.s().l()) {
            o.b(this.h, PauseReasons.MEDIA_BUTTON_TAP);
            return;
        }
        o.a(this.h);
        if (ad.a(this.h).o().booleanValue()) {
            ad.a(this.h).j();
        }
    }

    private void i() {
        if (PlayerManager.a(this.h).d()) {
            this.k = 0;
        } else if (PlayerManager.a(this.h).e()) {
            this.k = 1;
        } else {
            this.k = 2;
        }
    }

    private void a(int i) {
        PlayerManager a = PlayerManager.a(this.h);
        i = i == 2 ? 0 : i + 1;
        this.k = i;
        a.b(false);
        a.c(false);
        switch (RepeatModes.values()[i]) {
            case SINGLE:
                a.b(true);
                break;
            case ALL:
                a.c(true);
                break;
        }
        com.services.d.a().a("PREFERENCE_KEY_REPEAT_STATUS", i, true);
    }

    private void j() {
        i();
        o.a("listener_android_auto_player", this.a);
        o.a("listener_android_auto_player", this.p);
        if (PlayerManager.a) {
            o.a(this.h);
        }
    }

    private void k() {
        a(null, PlayerStates.INVALID);
    }

    private void l() {
        a(null, PlayerStates.STOPPED);
    }

    private void m() {
        o.a("listener_android_auto_player", this.p);
        a(null, PlayerStates.LOADING);
    }

    private void n() {
        a(null, PlayerStates.PAUSED);
    }

    private void o() {
        a(null, PlayerStates.PLAYING);
    }

    private void a(boolean z, boolean z2) {
        a(null, PlayerStates.PAUSED);
    }

    private void b(boolean z, boolean z2) {
        a(null, PlayerStates.PAUSED);
    }
}
