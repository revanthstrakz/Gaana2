package com.managers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.comscore.streaming.Constants;
import com.constants.c.d;
import com.dynamicview.c;
import com.fragments.MiniPlayerFragment;
import com.fragments.PlayerRadioFragmentV4;
import com.gaana.BaseActivity.RadioCallBackListener;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.DiscoverTags.DiscoverTag;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.models.LiveCricketData;
import com.gaana.models.PollData;
import com.gaana.models.RadioLiveDetails;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.logging.GaanaLogger;
import com.logging.GaanaLogger.PLAYOUT_SOURCE_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.logging.TrackLog;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager.BusinessObjectType;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.o;
import com.services.l.af;
import com.services.l.be;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class ad implements RadioCallBackListener {
    private static ad j;
    private Boolean A = Boolean.valueOf(false);
    private Boolean B = Boolean.valueOf(false);
    private String C = "";
    private String D = "";
    private String E = "";
    private b F;
    private boolean G = false;
    private Handler H = new Handler(Looper.getMainLooper());
    private boolean I = false;
    private Track J = null;
    private String K = "";
    a a;
    s b = new s() {
        public void onRetreivalComplete(BusinessObject businessObject) {
            try {
                if (ad.this.m != null && ad.this.m.isShowing()) {
                    ad.this.m.dismiss();
                    ad.this.m = null;
                }
            } catch (Exception unused) {
            }
            if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                ad.this.d("");
            } else {
                if (ad.this.k != null) {
                    ad.this.k.clear();
                }
                ad.this.k = ad.this.a(businessObject.getArrListBusinessObj());
                if (ad.this.a()) {
                    if (ad.this.k == null) {
                        ad.this.k = new ArrayList();
                    }
                    ad.this.k.add(0, ad.this.b(ad.this.b()));
                }
                if (ad.this.k != null && ad.this.k.size() > 0) {
                    PlayerManager.a(GaanaApplication.getContext()).f(true);
                    PlayerManager.a(GaanaApplication.getContext()).a(PlayerType.GAANA_RADIO);
                    PlayerManager.a(GaanaApplication.getContext()).e(true);
                    PlayerManager.a(GaanaApplication.getContext()).a(ad.this.k, ad.this.n().booleanValue(), true);
                    PlayerManager.a(GaanaApplication.getContext()).c(ad.this.k, (PlayerTrack) ad.this.k.get(0), 0);
                    PlayerManager.a(GaanaApplication.getContext()).b(ai.a());
                    if (ad.this.F != null) {
                        ad.this.F.d();
                    }
                }
            }
            ad.this.a(false);
            ad.this.a(null);
        }

        public void onErrorResponse(BusinessObject businessObject) {
            ad.this.a(false);
            ad.this.a(null);
        }
    };
    s c = new s() {
        public void onErrorResponse(BusinessObject businessObject) {
        }

        public void onRetreivalComplete(BusinessObject businessObject) {
            if (ad.this.l != null && ad.this.l.size() != 0) {
                int i = 30000;
                ArrayList g;
                int v;
                if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                    g = ad.this.t();
                    if (!((PlayerTrack) g.get(0)).h().equals(((PlayerTrack) ad.this.l.get(0)).h())) {
                        try {
                            v = GaanaMusicService.s().v();
                            i = ad.this.p().booleanValue() ? 0 : v > ((int) Long.parseLong(((PlayerTrack) ad.this.l.get(0)).b().getDuration().trim())) * 1000 ? ((int) Long.parseLong(((PlayerTrack) ad.this.l.get(0)).b().getDuration().trim())) * 1000 : v;
                        } catch (Exception unused) {
                        }
                        ad.this.l = g;
                        ad.this.c(Boolean.valueOf(true));
                        ad.this.d(Boolean.valueOf(true));
                        ad.this.a((PlayerTrack) ad.this.l.get(0), i);
                        if (ad.this.a != null) {
                            ad.this.a.l();
                        }
                        ad.this.u();
                        o.g(GaanaApplication.getContext());
                        ad.this.a(ad.this.u, 0);
                    }
                } else {
                    g = businessObject.getArrListBusinessObj();
                    if (!((Track) g.get(0)).getBusinessObjId().equalsIgnoreCase(((PlayerTrack) ad.this.l.get(0)).h())) {
                        String name;
                        try {
                            v = GaanaMusicService.s().v();
                            i = ad.this.p().booleanValue() ? 0 : v > ((int) Long.parseLong(((PlayerTrack) ad.this.l.get(0)).b().getDuration().trim())) * 1000 ? ((int) Long.parseLong(((PlayerTrack) ad.this.l.get(0)).b().getDuration().trim())) * 1000 : v;
                        } catch (Exception unused2) {
                        }
                        ad.this.l = ad.this.a(g);
                        ad.this.c(Boolean.valueOf(false));
                        ad.this.d(Boolean.valueOf(true));
                        PlayerTrack i2 = PlayerManager.a(GaanaApplication.getContext()).i();
                        if (i2 == null || !i2.k()) {
                            name = PLAYOUT_SOURCE_TYPE.USER_INITIATED.name();
                        } else {
                            name = PLAYOUT_SOURCE_TYPE.SYSTEM_INITIATED.name();
                        }
                        String str = name;
                        String g2 = i2.g();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(Util.S());
                        stringBuilder.append("-");
                        stringBuilder.append(i2.h());
                        u.a().a("Player Events", "Track Played Online", stringBuilder.toString(), Util.a(i2, g2), g2, str);
                        ad.this.a((PlayerTrack) ad.this.l.get(0), i);
                        if (ad.this.a != null) {
                            ad.this.a.l();
                        }
                        if (ad.this.F != null) {
                            ad.this.F.e();
                        }
                        ad.this.u();
                        o.g(GaanaApplication.getContext());
                        String trim = ((PlayerTrack) ad.this.l.get(0)).b().getDuration().trim();
                        try {
                            if (trim.contains(":")) {
                                trim = trim.replace(":", ".");
                            }
                            if (trim.contains(".")) {
                                ad.this.a(ad.this.u, (Long.parseLong(trim.split("\\.")[0]) * 1000) - ad.this.u);
                            } else {
                                ad.this.a(ad.this.u, (Long.parseLong(trim) * 1000) - ad.this.u);
                            }
                        } catch (Exception unused3) {
                        }
                    }
                }
            }
        }
    };
    TimerTask d;
    Timer e;
    TimerTask f;
    Timer g;
    private TimerTask h;
    private Timer i;
    private ArrayList<PlayerTrack> k;
    private ArrayList<PlayerTrack> l;
    private Dialog m;
    private GaanaApplication n = GaanaApplication.getInstance();
    private String o = "";
    private String p = "";
    private String q = "";
    private String r = "";
    private String s = "";
    private String t = "";
    private long u = Constants.HEARTBEAT_STAGE_ONE_INTERVAL;
    private boolean v = false;
    private String w = null;
    private int x = SOURCE_TYPE.OTHER.ordinal();
    private String y = "";
    private Boolean z = Boolean.valueOf(false);

    public interface a {
        void l();
    }

    public interface b {
        void d();

        void e();
    }

    public boolean a() {
        return this.I;
    }

    public void a(boolean z) {
        this.I = z;
    }

    public Track b() {
        return this.J;
    }

    public void a(Track track) {
        this.J = track;
    }

    public void a(String str) {
        this.r = str;
    }

    public String c() {
        return this.r;
    }

    public void b(String str) {
        this.o = str;
    }

    public String d() {
        return this.s;
    }

    private ad(Context context) {
    }

    public static ad a(Context context) {
        if (j == null) {
            j = new ad(context);
        }
        return j;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(final String str, final int i, final BusinessObject businessObject) {
        if (com.constants.Constants.cY) {
            if (ai.a() == null) {
                aj.a().a(ai.a(), GaanaApplication.getContext().getResources().getString(R.string.error_juke_radio_playback));
            } else {
                JukeSessionManager.getErrorDialog(ai.a(), 0, new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        JukeSessionManager.getInstance().stopJukeSession(new s() {
                            public void onErrorResponse(BusinessObject businessObject) {
                            }

                            public void onRetreivalComplete(BusinessObject businessObject) {
                                if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                                    ad.this.a(str, i, businessObject);
                                }
                            }
                        });
                    }
                });
            }
            return;
        }
        k();
        if ((n().booleanValue() || PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO) && q().compareTo(str.trim()) == 0 && !o().booleanValue() && GaanaMusicService.t()) {
            aj.a().a(ai.a(), this.n.getString(R.string.radio_playing));
            return;
        }
        String favorite_count;
        this.x = i;
        this.w = businessObject.getBusinessObjId();
        this.y = businessObject.getEnglishName();
        b(Boolean.valueOf(false));
        c(Boolean.valueOf(false));
        a(Boolean.valueOf(true));
        if (i == SOURCE_TYPE.GAANA_RADIO.ordinal() && (businessObject instanceof Radio)) {
            Item item = new Item();
            item.setEntityId(businessObject.getBusinessObjId());
            item.setEntityType(d.d);
            item.setName(businessObject.getRawName());
            item.setLanguage(businessObject.getLanguage());
            Radio radio = (Radio) businessObject;
            item.setArtwork(radio.getArtwork());
            item.setAtw(radio.getArtwork());
            favorite_count = radio.getFavorite_count();
            if (!TextUtils.isEmpty(favorite_count)) {
                item.setFavoriteCount(Long.parseLong(favorite_count));
            }
            aq.a().a(item);
        }
        this.K = str;
        d(str);
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.c(Boolean.valueOf(true));
        favorite_count = c.a().h();
        if (str.contains("https://api.gaana.com/home/one-touch-songs/") || str.contains("https://api.gaana.com/home/gaana-yim/radio-rewind") || (!TextUtils.isEmpty(favorite_count) && businessObject.getName().equalsIgnoreCase(favorite_count))) {
            uRLManager.a(BusinessObjectType.GenericItems);
            uRLManager.a(true);
            f(this.n.getString(R.string.starting_one_touch));
            this.G = true;
        } else {
            this.G = false;
            uRLManager.a(BusinessObjectType.Tracks);
            f(null);
        }
        i.a().a(this.b, uRLManager);
    }

    public void e() {
        if (!TextUtils.isEmpty(this.K)) {
            String str = this.K;
            com.constants.Constants.cs = true;
            b(Boolean.valueOf(false));
            c(Boolean.valueOf(false));
            a(Boolean.valueOf(true));
            d(str);
            URLManager uRLManager = new URLManager();
            if (str.contains("https://api.gaana.com/home/one-touch-songs/") || this.G) {
                uRLManager.a(BusinessObjectType.GenericItems);
                uRLManager.a(true);
            } else {
                uRLManager.a(BusinessObjectType.Tracks);
            }
            uRLManager.a(str);
            uRLManager.c(Boolean.valueOf(true));
            i.a().a(this.b, uRLManager);
        }
    }

    public void a(final BusinessObject businessObject) {
        if (com.constants.Constants.cY) {
            if (ai.a() == null) {
                aj.a().a(ai.a(), GaanaApplication.getContext().getResources().getString(R.string.error_juke_radio_playback));
            } else {
                JukeSessionManager.getErrorDialog(ai.a(), 0, new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        JukeSessionManager.getInstance().stopJukeSession(new s() {
                            public void onErrorResponse(BusinessObject businessObject) {
                            }

                            public void onRetreivalComplete(BusinessObject businessObject) {
                                if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                                    ad.this.a(businessObject);
                                }
                            }
                        });
                    }
                });
            }
            return;
        }
        k();
        if ((o().booleanValue() || PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO) && businessObject.getBusinessObjId().equals(this.o) && !n().booleanValue() && GaanaMusicService.t()) {
            aj.a().a(ai.a(), this.n.getString(R.string.radio_playing));
            return;
        }
        String streamUrl;
        Object poll_time;
        Object poll_api;
        this.n.setInitialPlayTime(Calendar.getInstance().getTimeInMillis());
        this.x = SOURCE_TYPE.RADIO_MIRCHI.ordinal();
        b(Boolean.valueOf(true));
        a(Boolean.valueOf(false));
        d("");
        String str = null;
        f(null);
        this.y = businessObject.getEnglishName();
        boolean z = businessObject instanceof Radio;
        if (z) {
            Radio radio = (Radio) businessObject;
            streamUrl = radio.getStreamUrl();
            poll_time = radio.getPoll_time();
            String str2 = streamUrl;
            poll_api = radio.getPoll_api();
            str = str2;
        } else {
            boolean z2 = businessObject instanceof DiscoverTag;
            poll_api = null;
            poll_time = poll_api;
        }
        if (z) {
            Item item = new Item();
            item.setEntityId(businessObject.getBusinessObjId());
            item.setEntityType(d.c);
            item.setName(businessObject.getRawName());
            item.setLanguage(businessObject.getLanguage());
            Radio radio2 = (Radio) businessObject;
            item.setArtwork(radio2.getArtwork());
            item.setAtw(radio2.getArtwork());
            if (!TextUtils.isEmpty(str)) {
                EntityInfo entityInfo = new EntityInfo();
                entityInfo.setKey("stream_url");
                entityInfo.setValue(str);
                EntityInfo entityInfo2 = new EntityInfo();
                entityInfo2.setKey("poll_api");
                entityInfo2.setValue(poll_api);
                EntityInfo entityInfo3 = new EntityInfo();
                entityInfo3.setKey("poll_time");
                entityInfo3.setValue(poll_time);
                ArrayList arrayList = new ArrayList();
                arrayList.add(entityInfo);
                arrayList.add(entityInfo2);
                arrayList.add(entityInfo3);
                item.setEntityInfo(arrayList);
            }
            streamUrl = radio2.getFavorite_count();
            if (!TextUtils.isEmpty(streamUrl)) {
                item.setFavoriteCount(Long.parseLong(streamUrl));
            }
            aq.a().a(item);
        }
        if (TextUtils.isEmpty(str)) {
            String replace = "https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=RM&hashcode=<hashMacValue>".replace("<radio_id>", businessObject.getBusinessObjId()).replace("<hashMacValue>", Util.a(Util.b(businessObject.getBusinessObjId()), com.constants.Constants.bd));
            if (this.n.getCurrentUser().getLoginStatus() && this.n.getCurrentUser().getAuthToken() != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(replace);
                stringBuilder.append("&token=");
                stringBuilder.append(this.n.getCurrentUser().getAuthToken());
                replace = stringBuilder.toString();
            }
            c(replace);
        } else {
            this.o = businessObject.getBusinessObjId();
            Radio radio3 = (Radio) businessObject;
            this.p = radio3.getArtwork();
            this.q = businessObject.getName();
            this.w = this.o;
            this.y = businessObject.getEnglishName();
            this.r = radio3.getPoll_api();
            this.s = radio3.getPoll_time();
            e(str);
            a(this.o, false);
        }
    }

    public void c(String str) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.a(RadioLiveDetails.class);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj instanceof RadioLiveDetails) {
                    RadioLiveDetails radioLiveDetails = (RadioLiveDetails) obj;
                    if (radioLiveDetails.getStatus().equalsIgnoreCase("true")) {
                        ArrayList arrListRadioObj = radioLiveDetails.getArrListRadioObj();
                        if (arrListRadioObj != null && arrListRadioObj.size() > 0) {
                            String radioStream = ((RadioLiveDetails.Radio) arrListRadioObj.get(0)).getRadioStream();
                            ad.this.o = ((RadioLiveDetails.Radio) arrListRadioObj.get(0)).getRadioId();
                            ad.this.p = ((RadioLiveDetails.Radio) arrListRadioObj.get(0)).getArtwork();
                            ad.this.q = ((RadioLiveDetails.Radio) arrListRadioObj.get(0)).getName();
                            ad.this.e(radioStream);
                            ad.this.w = ad.this.o;
                            ad.this.y = ((RadioLiveDetails.Radio) arrListRadioObj.get(0)).getEnglishName();
                            ad.this.v = true;
                        }
                    }
                }
                if (ad.this.v) {
                    ad.this.n.sendUrlFetchTimeEvent(false);
                    ad.this.a(ad.this.o, false);
                    return;
                }
                if (ad.this.m != null && ad.this.m.isShowing()) {
                    ad.this.m.dismiss();
                    ad.this.m = null;
                }
                ap.a().a(ai.a(), ad.this.n.getString(R.string.unable_live_radio));
            }
        }, uRLManager);
    }

    public void a(String str, String str2) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.c(Boolean.valueOf(true));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("&match_id=");
        stringBuilder.append(str2);
        uRLManager.a(stringBuilder.toString());
        uRLManager.a(PollData.class);
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject instanceof PollData) {
                    PollData pollData = (PollData) businessObject;
                    if (pollData.getStatus().equals("1")) {
                        ad.this.t = pollData.getMessage();
                        o.g(GaanaApplication.getContext());
                        if (ad.a(GaanaApplication.getContext()).c() == null || !pollData.getStatus().equals("1") || !pollData.getMatch_end().equals("0") || pollData.getPoll_time() == null || Long.parseLong(pollData.getPoll_time()) <= 0) {
                            ad.this.f();
                            return;
                        } else {
                            ad.this.a(Long.parseLong(pollData.getPoll_time()) * 1000);
                            return;
                        }
                    }
                }
                ad.this.f();
            }

            public void onErrorResponse(BusinessObject businessObject) {
                ad.this.t = "";
            }
        }, uRLManager);
    }

    public void a(final String str, final be beVar, final ViewGroup viewGroup) {
        URLManager uRLManager = new URLManager();
        uRLManager.c(Boolean.valueOf(true));
        uRLManager.a(str);
        uRLManager.a(LiveCricketData.class);
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject instanceof LiveCricketData) {
                    LiveCricketData liveCricketData = (LiveCricketData) businessObject;
                    if (liveCricketData.getStatus().equals("1")) {
                        beVar.showCricketCarouselScore(viewGroup, liveCricketData.getData());
                        if (!liveCricketData.getStatus().equals("1") || liveCricketData.getMatch_end() != 0 || liveCricketData.getPoll_time() == null || Long.parseLong(liveCricketData.getPoll_time()) <= 0) {
                            ad.this.g();
                            return;
                        } else {
                            ad.this.a(Long.parseLong(liveCricketData.getPoll_time()) * 1000, str, beVar, viewGroup);
                            return;
                        }
                    }
                }
                ad.this.g();
            }

            public void onErrorResponse(BusinessObject businessObject) {
                ad.this.t = "";
            }
        }, uRLManager);
    }

    public void a(long j) {
        final Context applicationContext = this.n.getApplicationContext();
        if (this.d != null) {
            this.d.cancel();
        }
        if (this.e != null) {
            this.e.cancel();
            this.e = new Timer();
        } else {
            this.e = new Timer();
        }
        this.d = new TimerTask() {
            public void run() {
                ad a = ad.a(applicationContext);
                if (a.c() != null) {
                    a.a(a.c(), ad.this.o);
                }
            }
        };
        this.e.schedule(this.d, j);
    }

    public void f() {
        this.t = "";
        Context a = ai.a();
        Fragment miniPlayerFragment = a instanceof GaanaActivity ? ((GaanaActivity) a).getMiniPlayerFragment() : null;
        if (this.d != null) {
            this.d.cancel();
        }
        if (this.e != null) {
            this.e.cancel();
        }
        a(a).a(null);
        if (miniPlayerFragment instanceof MiniPlayerFragment) {
            ((MiniPlayerFragment) miniPlayerFragment).n();
        }
        BaseFragment baseFragment = ((GaanaActivity) ai.a()).getmCurrentPlayerFragment();
        if (baseFragment instanceof PlayerRadioFragmentV4) {
            ((PlayerRadioFragmentV4) baseFragment).h();
        }
    }

    public void a(long j, String str, be beVar, ViewGroup viewGroup) {
        final Context applicationContext = this.n.getApplicationContext();
        if (this.f != null) {
            this.f.cancel();
        }
        if (this.g != null) {
            this.g.cancel();
            this.g = new Timer();
        } else {
            this.g = new Timer();
        }
        final String str2 = str;
        final be beVar2 = beVar;
        final ViewGroup viewGroup2 = viewGroup;
        this.f = new TimerTask() {
            public void run() {
                ad a = ad.a(applicationContext);
                if (!TextUtils.isEmpty(str2)) {
                    a.a(str2, beVar2, viewGroup2);
                }
            }
        };
        this.g.schedule(this.f, j);
    }

    public void g() {
        this.t = "";
        Context a = ai.a();
        if (a instanceof GaanaActivity) {
            ((GaanaActivity) a).getMiniPlayerFragment();
        }
        if (this.f != null) {
            this.f.cancel();
        }
        if (this.g != null) {
            this.g.cancel();
        }
        a(a).a(null);
    }

    public String h() {
        return this.t;
    }

    public void a(String str, boolean z) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.c(Boolean.valueOf(true));
        this.o = str;
        uRLManager.a("https://api.gaana.com/index.php?type=radio&subtype=metadata&id=<id>".replace("<id>", str));
        if (z) {
            i.a().a(this.c, uRLManager);
        } else {
            i.a().a(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    ad.this.playRadioNow(businessObject);
                }
            }, uRLManager);
        }
    }

    private void a(PlayerTrack playerTrack, int i) {
        GaanaMusicService.s().u();
        TrackLog trackLog = new TrackLog();
        if (playerTrack != null && playerTrack.b() != null) {
            trackLog.f(playerTrack.c());
            trackLog.d(String.valueOf(playerTrack.e()));
            trackLog.m(playerTrack.g());
            trackLog.l(playerTrack.j());
            trackLog.n(playerTrack.l() ? "1" : "0");
            trackLog.c(playerTrack.d());
            try {
                trackLog.i(String.valueOf(i));
                trackLog.b(playerTrack.h());
                trackLog.a(playerTrack.b().getDuration());
                trackLog.a(System.currentTimeMillis());
            } catch (IllegalStateException unused) {
                trackLog.i(String.valueOf(Double.parseDouble(playerTrack.b().getDuration()) * 1000.0d));
                trackLog.b(playerTrack.h());
                trackLog.a(playerTrack.b().getDuration());
                trackLog.a(System.currentTimeMillis());
            }
            GaanaLogger.a().a(trackLog, GaanaApplication.getContext());
            GaanaLogger.a().a(com.logging.c.a());
        }
    }

    private void f(String str) {
        Context a = ai.a();
        if (a != null) {
            try {
                if (!this.n.isAppInOfflineMode()) {
                    if (Util.j(GaanaApplication.getContext())) {
                        LinearLayout linearLayout = new LinearLayout(a);
                        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
                        LayoutInflater.from(a).inflate(R.layout.view_loading_radio, linearLayout, true);
                        if (str != null) {
                            ((TextView) linearLayout.findViewById(R.id.tvTrackName)).setText(str);
                        } else if (n().booleanValue()) {
                            ((TextView) linearLayout.findViewById(R.id.tvTrackName)).setText(R.string.starting_gaana_radio);
                        } else if (o().booleanValue()) {
                            ((TextView) linearLayout.findViewById(R.id.tvTrackName)).setText(R.string.starting_live_radio);
                        }
                        if (this.m != null && this.m.isShowing()) {
                            this.m.dismiss();
                            this.m = null;
                        }
                        this.m = new Dialog(a, R.style.dialog_transparent_bg);
                        this.m.setContentView(linearLayout);
                        this.m.setCancelable(true);
                        this.m.show();
                    }
                }
                if (!ap.a().o()) {
                    aj.a().a(a, a.getResources().getString(R.string.toast_subscription_expired));
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    private void d(Boolean bool) {
        if (ai.a() == null || !((Activity) ai.a()).isFinishing()) {
            PlayerManager.a(GaanaApplication.getContext()).f(false);
            if (!bool.booleanValue()) {
                PlayerManager.a(GaanaApplication.getContext()).a(PlayerType.GAANA_RADIO);
            }
            PlayerManager.a(GaanaApplication.getContext()).e(bool.booleanValue() ^ 1);
            PlayerManager.a(GaanaApplication.getContext()).c(this.l, (PlayerTrack) this.l.get(0), 0);
            PlayerManager.a(GaanaApplication.getContext()).a(this.l, n().booleanValue(), bool.booleanValue() ^ 1);
            if (!bool.booleanValue()) {
                PlayerManager.a(GaanaApplication.getContext()).b(ai.a());
                if (this.F != null) {
                    this.F.d();
                }
            }
            return;
        }
        k();
    }

    public Track i() {
        Track track = new Track();
        track.setArtwork(this.p);
        track.setArtworkLarge(this.p);
        track.setTracktitle(this.q);
        return track;
    }

    private ArrayList<PlayerTrack> t() {
        Track track = new Track();
        track.setArtwork(this.p);
        track.setTracktitle(this.q);
        ArrayList arrayList = new ArrayList();
        arrayList.add(b(track));
        return arrayList;
    }

    public void j() {
        a(this.u, 0);
    }

    private void a(long j, long j2) {
        k();
        this.h = new TimerTask() {
            public void run() {
                ad.this.a(ad.this.o, true);
            }
        };
        this.i = new Timer();
        try {
            this.i.schedule(this.h, j2, j);
        } catch (Exception unused) {
            k();
        }
    }

    public void k() {
        if (this.i != null) {
            this.i.cancel();
        }
        if (this.h != null) {
            this.h.cancel();
        }
    }

    private ArrayList<PlayerTrack> a(ArrayList<Track> arrayList) {
        if (arrayList == null) {
            return null;
        }
        ArrayList<PlayerTrack> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Track track = (Track) it.next();
            PlayerTrack playerTrack = new PlayerTrack(track, this.w, this.x, this.y);
            playerTrack.f(GaanaApplication.getInstance().getPageName());
            if (arrayList.indexOf(track) == 0) {
                playerTrack.d(true);
            }
            arrayList2.add(playerTrack);
        }
        return arrayList2;
    }

    private PlayerTrack b(Track track) {
        if (track == null) {
            return null;
        }
        PlayerTrack playerTrack = new PlayerTrack(track, this.w, this.x, this.y);
        playerTrack.f(GaanaApplication.getInstance().getPageName());
        return playerTrack;
    }

    public String l() {
        return this.y;
    }

    public void playRadioNow(BusinessObject businessObject) {
        try {
            if (this.m != null && this.m.isShowing()) {
                this.m.dismiss();
                this.m = null;
            }
        } catch (Exception unused) {
        }
        if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
            this.l = t();
            c(Boolean.valueOf(true));
            d(Boolean.valueOf(false));
            a(this.u, 0);
            return;
        }
        this.l = a(businessObject.getArrListBusinessObj());
        c(Boolean.valueOf(false));
        d(Boolean.valueOf(false));
        a(this.u, 0);
    }

    public boolean m() {
        return this.z.booleanValue() || this.A.booleanValue();
    }

    public Boolean n() {
        return this.z;
    }

    public void a(Boolean bool) {
        this.z = bool;
    }

    public Boolean o() {
        return this.A;
    }

    public void b(Boolean bool) {
        this.A = bool;
    }

    public Boolean p() {
        boolean z = this.B.booleanValue() && this.A.booleanValue();
        return Boolean.valueOf(z);
    }

    public void c(Boolean bool) {
        this.B = bool;
    }

    public String q() {
        return this.E;
    }

    public void d(String str) {
        this.E = str;
    }

    public String r() {
        return this.C;
    }

    public String s() {
        return this.o;
    }

    public void e(String str) {
        this.C = str;
    }

    private void u() {
        if (GaanaLogger.a().b() != null) {
            try {
                GaanaLogger.a().c().a(GaanaApplication.getContext());
            } catch (IllegalStateException unused) {
            }
        }
    }

    public void a(b bVar) {
        this.F = bVar;
    }
}
