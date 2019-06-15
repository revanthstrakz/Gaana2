package com.dynamicview;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.comscore.measurement.MeasurementDispatcher;
import com.constants.Constants;
import com.constants.Constants.ACTION_TYPE;
import com.constants.Constants.VIEW_SUBTYPE;
import com.exoplayer2.VideoPlayerActivityTwo;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.HomeSponsorAdView;
import com.gaana.R;
import com.gaana.SplashScreenActivity;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.GaanaVideoItem;
import com.gaana.models.PreScreens.PreScreen;
import com.gaana.view.AutoPlayVideoView;
import com.gaana.view.BaseItemView;
import com.gaana.view.FailedPaymentCardView;
import com.gaana.view.GaanaYourYearView;
import com.gaana.view.GenericHomeView;
import com.gaana.view.GetFreeDownloadView;
import com.gaana.view.HomeSettingsItemView;
import com.gaana.view.ImageCardView;
import com.gaana.view.LoginBannerOnHomePageView;
import com.gaana.view.SectionTitleViews;
import com.gaana.view.SmartFeedNxtgenItemView;
import com.gaana.view.SponsoredOccasionCardView;
import com.gaana.view.ThemeCardView;
import com.gaana.view.UpgradeHomeView;
import com.gaana.view.VideoAdView;
import com.gaana.view.header.HomeCarouselView;
import com.gaana.view.header.RadioFragmentHeader;
import com.gaana.view.item.LastHeardSongsHorizontalScroll;
import com.gaana.view.item.SubscriptionTrialCardView;
import com.gaana.view.prescreen.SquareCardView;
import com.gaanavideo.FullScreenVideoPlayerActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.i.i;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap;
import com.managers.f;
import com.managers.s;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.o;
import com.services.c;
import com.services.d;
import com.services.l;
import com.services.l.y;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DynamicViewManager implements a, b<Object> {
    private static DynamicViewManager a;
    private e b;
    private e c;
    private e d;
    private y e;
    private List<y> f;
    private String g = "";
    private ArrayList<f.a> h;
    private ArrayList<f.a> i;

    public enum DynamicViewType {
        hor_scroll,
        carousel,
        img_card,
        login_card,
        ad,
        grid,
        list,
        generic_detail,
        nudge,
        trial_sponsor_ad,
        dummy_view,
        header,
        last_heard,
        uber_connect,
        user_activity,
        your_year,
        gaanayear,
        gaana_year_2016,
        card,
        footer,
        download,
        abandon_card,
        subscription_card,
        toggle,
        content_card,
        grid_rect,
        user_radio_activity,
        chameleon,
        theme_card,
        video_ad,
        gaana_video,
        text_card,
        spon_oc,
        inline_video,
        smartfeed_nxtgen,
        staggered_grid,
        cir_hor_scroll,
        double_scroll,
        settings,
        view_more,
        section_heading,
        section_heading_occasion,
        grid_2x2,
        tag_radio
    }

    public enum PreScreenViewType {
        full_screen_card,
        grid_2x2,
        cir_grid_2x2,
        list
    }

    private DynamicViewManager() {
    }

    public static DynamicViewManager a() {
        if (a == null) {
            a = new DynamicViewManager();
        }
        return a;
    }

    public void b() {
        d.a().b("PREFERENCE_DYNAMIC_VIEW_FETCH_TIME", false);
        d.a().b("PREFERENCE_DYNAMIC_VIEW_FETCH_DATA", false);
        l();
    }

    public void a(y yVar) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(yVar);
    }

    public void b(y yVar) {
        if (this.f != null) {
            this.f.remove(yVar);
        }
    }

    public void c() {
        URLManager i = i();
        i.c(Boolean.valueOf(true));
        i.a().a(i, "DynamicApi", (b) this, (a) this);
    }

    public void a(y yVar, Context context) {
        this.e = yVar;
        GaanaApplication instance = GaanaApplication.getInstance();
        if (!Util.j(context) || instance.isAppInOfflineMode()) {
            l();
            return;
        }
        URLManager i = i();
        i.c(Boolean.valueOf(true));
        i.a().a(i, "DynamicApi", (b) this, (a) this);
    }

    public void a(final boolean z) {
        com.i.d.a(new Runnable() {
            public void run() {
                int i = false;
                long b = d.a().b(0, "PREFERENCE_DYNAMIC_VIEW_FETCH_TIME", false);
                String c = d.a().c("PREFERENCE_DYNAMIC_VIEW_FETCH_DATA", false);
                if (b == 0 || System.currentTimeMillis() - b > MeasurementDispatcher.MILLIS_PER_DAY || z) {
                    i.a().a(DynamicViewManager.this.i(), "DynamicApi", DynamicViewManager.a, DynamicViewManager.a);
                }
                if (!(c == null || c.isEmpty())) {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.excludeFieldsWithModifiers(128);
                    DynamicViewManager.this.c = (e) gsonBuilder.create().fromJson(c, e.class);
                }
                if (DynamicViewManager.this.c == null) {
                    DynamicViewManager.this.l();
                } else {
                    DynamicViewManager.this.d = DynamicViewManager.this.c;
                }
                DynamicViewManager.this.j();
                if (DynamicViewManager.this.e != null) {
                    DynamicViewManager.this.e.OnDynamicViewDataFetched();
                    DynamicViewManager.this.e = null;
                }
                if (DynamicViewManager.this.f != null) {
                    while (i < DynamicViewManager.this.f.size()) {
                        ((y) DynamicViewManager.this.f.get(i)).OnDynamicViewDataFetched();
                        i++;
                    }
                }
            }
        });
    }

    public void d() {
        if (this.c != null) {
            this.d = this.c;
        } else if (this.b != null) {
            this.d = this.b;
        } else {
            com.i.d.a(new Runnable() {
                public void run() {
                    String c = d.a().c("PREFERENCE_DYNAMIC_VIEW_FETCH_DATA", false);
                    if (!(c == null || c.isEmpty())) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.excludeFieldsWithModifiers(128);
                        DynamicViewManager.this.d = (e) gsonBuilder.create().fromJson(c, e.class);
                    }
                    if (DynamicViewManager.this.d == null) {
                        DynamicViewManager.this.l();
                    }
                }
            });
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        int i = 0;
        String c = d.a().c("PREFERENCE_DYNAMIC_VIEW_FETCH_DATA", false);
        if (!(c == null || c.isEmpty())) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.excludeFieldsWithModifiers(128);
            this.c = (e) gsonBuilder.create().fromJson(c, e.class);
            this.d = this.c;
        }
        if (this.c == null && this.b == null) {
            l();
        }
        j();
        if (this.e != null) {
            this.e.OnDynamicViewDataFetched();
            this.e = null;
        }
        if (this.f != null) {
            while (i < this.f.size()) {
                ((y) this.f.get(i)).OnDynamicViewDataFetched();
                i++;
            }
        }
    }

    public void onResponse(Object obj) {
        if (obj instanceof e) {
            final e eVar = (e) obj;
            if (eVar.a() != null && eVar.a().size() > 0) {
                this.c = eVar;
                this.d = this.c;
                com.i.d.a(new Runnable() {
                    public void run() {
                        d.a().a("PREFERENCE_DYNAMIC_VIEW_FETCH_DATA", new Gson().toJson(eVar), false);
                        d.a().a(System.currentTimeMillis(), "PREFERENCE_DYNAMIC_VIEW_FETCH_TIME", false);
                    }
                });
            }
        }
        if (this.c == null && this.b == null) {
            l();
        }
        j();
        if (this.e != null) {
            this.e.OnDynamicViewDataFetched();
            this.e = null;
        }
        if (this.f != null) {
            for (int i = 0; i < this.f.size(); i++) {
                ((y) this.f.get(i)).OnDynamicViewDataFetched();
            }
        }
    }

    private URLManager i() {
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.DynamicViewSections);
        uRLManager.a(1440);
        if (Util.ad()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://apiv2.gaana.com/home/metadata/v1?ram=");
            stringBuilder.append(Util.i());
            uRLManager.a(stringBuilder.toString());
        }
        return uRLManager;
    }

    public ArrayList<BaseItemView> a(Context context, BaseGaanaFragment baseGaanaFragment) {
        return b(context, baseGaanaFragment);
    }

    public ArrayList<BaseItemView> a(List<f.a> list, Context context, BaseGaanaFragment baseGaanaFragment) {
        this.i = (ArrayList) list;
        ArrayList arrayList = new ArrayList();
        for (f.a aVar : list) {
            if (aVar != null) {
                aVar.d(true);
                String m = aVar.m();
                if (!TextUtils.isEmpty(m)) {
                    if (ap.a().a(aVar.b())) {
                        if (m.equals(DynamicViewType.hor_scroll.name()) || m.equals(DynamicViewType.cir_hor_scroll.name()) || m.equals(DynamicViewType.double_scroll.name())) {
                            arrayList.add(new DynamicHomeScrollerView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.carousel.name())) {
                            arrayList.add(new HomeCarouselView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.img_card.name())) {
                            arrayList.add(new GetFreeDownloadView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.login_card.name())) {
                            arrayList.add(new LoginBannerOnHomePageView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.ad.name())) {
                            arrayList.add(new UpgradeHomeView(context, baseGaanaFragment, aVar, Constants.dO));
                        } else if (m.equals(DynamicViewType.header.name())) {
                            arrayList.add(new RadioFragmentHeader(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.your_year.name())) {
                            arrayList.add(new GaanaYourYearView(context, baseGaanaFragment));
                        } else if (m.equals(DynamicViewType.user_radio_activity.name())) {
                            arrayList.add(new DynamicUserActivityView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.card.name())) {
                            arrayList.add(new ImageCardView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.section_heading.name())) {
                            arrayList.add(new SectionTitleViews(context, baseGaanaFragment, aVar));
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<BaseItemView> b(List<f.a> list, Context context, BaseGaanaFragment baseGaanaFragment) {
        ArrayList arrayList = new ArrayList();
        for (f.a aVar : list) {
            if (aVar != null) {
                aVar.d(true);
                String m = aVar.m();
                if (!TextUtils.isEmpty(m)) {
                    if (ap.a().a(aVar.b())) {
                        if (m.equals(DynamicViewType.hor_scroll.name()) || m.equals(DynamicViewType.cir_hor_scroll.name()) || m.equals(DynamicViewType.double_scroll.name())) {
                            arrayList.add(new DynamicHomeScrollerView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.carousel.name())) {
                            arrayList.add(new HomeCarouselView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.img_card.name())) {
                            arrayList.add(new GetFreeDownloadView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.login_card.name())) {
                            arrayList.add(new LoginBannerOnHomePageView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.ad.name())) {
                            arrayList.add(new UpgradeHomeView(context, baseGaanaFragment, aVar, Constants.dO));
                        } else if (m.equals(DynamicViewType.header.name())) {
                            arrayList.add(new RadioFragmentHeader(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.your_year.name())) {
                            arrayList.add(new GaanaYourYearView(context, baseGaanaFragment));
                        } else if (m.equals(DynamicViewType.user_radio_activity.name())) {
                            arrayList.add(new DynamicUserActivityView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.card.name())) {
                            arrayList.add(new ImageCardView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.section_heading.name())) {
                            arrayList.add(new SectionTitleViews(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.tag_radio.name())) {
                            arrayList.add(new DynamicHomeScrollerView(context, baseGaanaFragment, aVar));
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<BaseItemView> c(List<PreScreen> list, Context context, BaseGaanaFragment baseGaanaFragment) {
        ArrayList arrayList = new ArrayList();
        for (PreScreen preScreen : list) {
            if (preScreen != null) {
                String viewType = preScreen.getViewType();
                if (!TextUtils.isEmpty(viewType)) {
                    if (ap.a().a(preScreen.getUserType())) {
                        if (viewType.equals(PreScreenViewType.full_screen_card.name())) {
                            SquareCardView squareCardView = new SquareCardView(context, baseGaanaFragment, preScreen, arrayList.size());
                            List items = preScreen.getItems();
                            if (!(items == null || items.isEmpty())) {
                                for (int i = 0; i < items.size(); i++) {
                                    arrayList.add(squareCardView);
                                }
                            }
                        } else if (viewType.equals(PreScreenViewType.grid_2x2.name()) || viewType.equals(PreScreenViewType.list.name()) || viewType.equals(PreScreenViewType.cir_grid_2x2.name())) {
                            arrayList.add(new g(context, baseGaanaFragment, preScreen));
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private ArrayList<f.a> j() {
        ArrayList arrayList = new ArrayList();
        if (!(this.d == null || this.d.a() == null)) {
            for (int i = 0; i < this.d.a().size(); i++) {
                if (((e.a) this.d.a().get(i)).a() != null) {
                    if (!TextUtils.isEmpty(((e.a) this.d.a().get(i)).b())) {
                        arrayList.add(new f.a(((e.a) this.d.a().get(i)).b(), "url", DynamicViewType.section_heading.name(), "url_seeall", "source_name", "ad_code", "0", "140"));
                    }
                    arrayList.addAll(((e.a) this.d.a().get(i)).a());
                }
            }
        }
        this.h = arrayList;
        return arrayList;
    }

    private ArrayList<BaseItemView> b(Context context, BaseGaanaFragment baseGaanaFragment) {
        boolean b;
        boolean z;
        ArrayList arrayList = new ArrayList();
        ArrayList j = j();
        Context instance = GaanaApplication.getInstance();
        boolean loginStatus = instance.getCurrentUser().getLoginStatus();
        if (loginStatus) {
            b = ap.a().b(instance);
            z = instance.getCurrentUser().getUserSubscriptionData() != null && instance.getCurrentUser().getUserSubscriptionData().getAccountType() == 2;
        } else {
            z = false;
            b = true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = j.iterator();
        while (it.hasNext()) {
            f.a aVar = (f.a) it.next();
            if (aVar != null) {
                String m = aVar.m();
                aVar.d(true);
                if (!TextUtils.isEmpty(m)) {
                    if (ap.a().a(aVar.b())) {
                        if (m.equals(DynamicViewType.hor_scroll.name()) || m.equals(DynamicViewType.staggered_grid.name()) || m.equals(DynamicViewType.double_scroll.name()) || m.equals(DynamicViewType.cir_hor_scroll.name())) {
                            if (aVar.k().equalsIgnoreCase(context.getString(R.string.friends_are_listening))) {
                                aVar.c(VIEW_SUBTYPE.SOCIAL.getNumVal());
                            } else {
                                aVar.c(0);
                            }
                            arrayList.add(new DynamicHomeScrollerView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.chameleon.name())) {
                            aVar.c(VIEW_SUBTYPE.CHAMELEON.getNumVal());
                            arrayList.add(new DynamicHomeScrollerView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.download.name())) {
                            if (ap.a().d()) {
                                aVar.d("dummy offline");
                                arrayList.add(new DynamicHomeScrollerView(context, baseGaanaFragment, aVar));
                            }
                        } else if (m.equals(DynamicViewType.carousel.name())) {
                            arrayList.add(new HomeCarouselView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.img_card.name())) {
                            if (Constants.S && !ap.a().d()) {
                                arrayList.add(new GetFreeDownloadView(context, baseGaanaFragment, aVar));
                            }
                        } else if (m.equals(DynamicViewType.login_card.name())) {
                            if (!loginStatus) {
                                arrayList.add(new LoginBannerOnHomePageView(context, baseGaanaFragment, aVar));
                            }
                        } else if (m.equals(DynamicViewType.ad.name())) {
                            if (b) {
                                arrayList.add(new UpgradeHomeView(context, baseGaanaFragment, aVar, Constants.dG));
                            }
                        } else if (m.equals(DynamicViewType.video_ad.name())) {
                            if (b) {
                                arrayList.add(new VideoAdView(context, baseGaanaFragment, aVar));
                            }
                        } else if (m.equals(DynamicViewType.dummy_view.name())) {
                            arrayList.add(new GenericHomeView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.nudge.name())) {
                            arrayList.add(new GenericHomeView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.trial_sponsor_ad.name())) {
                            if (z) {
                                arrayList.add(new HomeSponsorAdView(context, baseGaanaFragment, aVar));
                            }
                        } else if (m.equals(DynamicViewType.abandon_card.name()) && k()) {
                            arrayList.add(new FailedPaymentCardView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.spon_oc.name())) {
                            arrayList.add(new SponsoredOccasionCardView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.last_heard.name())) {
                            if (Constants.aE) {
                                BaseActivity baseActivity = (BaseActivity) context;
                                baseActivity.sendGAEvent(baseActivity.currentScreen, "Section View", "Last Heard Playlist Suggestion");
                                arrayList.add(new LastHeardSongsHorizontalScroll(context, baseGaanaFragment, aVar));
                            }
                        } else if (m.equals(DynamicViewType.user_activity.name())) {
                            arrayList.add(new DynamicUserActivityView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.your_year.name())) {
                            arrayList.add(new GaanaYourYearView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.card.name())) {
                            arrayList.add(new ImageCardView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.theme_card.name())) {
                            if (!(s.a().b() == null || Constants.dt == null)) {
                                arrayList.add(new ThemeCardView(context, baseGaanaFragment, aVar));
                            }
                        } else if (m.equals(DynamicViewType.subscription_card.name())) {
                            arrayList.add(new SubscriptionTrialCardView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.gaana_video.name())) {
                            arrayList.add(new GaanaYourYearView(context, baseGaanaFragment, aVar, aVar.f() == ACTION_TYPE.GAANA_VIDEO.getNumVal()));
                        } else if (m.equals(DynamicViewType.inline_video.name())) {
                            arrayList.add(new AutoPlayVideoView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.smartfeed_nxtgen.name())) {
                            arrayList.add(new SmartFeedNxtgenItemView(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.section_heading.name())) {
                            arrayList.add(new SectionTitleViews(context, baseGaanaFragment, aVar));
                        } else if (m.equals(DynamicViewType.settings.name())) {
                            arrayList.add(new HomeSettingsItemView(context, baseGaanaFragment, aVar));
                        }
                        stringBuilder.append(1);
                    }
                }
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(this.g);
        stringBuilder2.append(stringBuilder.toString());
        this.g = stringBuilder2.toString();
        return arrayList;
    }

    private boolean k() {
        if (GaanaApplication.getInstance().getCurrentUser() == null || !GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
            return false;
        }
        if (GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData() == null || GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getAccountType() != 3) {
            return true;
        }
        return false;
    }

    public boolean a(final Context context) {
        boolean z = false;
        if (this.d == null) {
            return false;
        }
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            f.a aVar = (f.a) it.next();
            if (aVar.m().equals(DynamicViewType.gaana_video.name())) {
                z = true;
                URLManager uRLManager = new URLManager();
                uRLManager.a(aVar.l());
                uRLManager.a(GaanaVideoItem.class);
                i.a().a(new l.s() {
                    public void onRetreivalComplete(BusinessObject businessObject) {
                        if (businessObject != null && (businessObject instanceof GaanaVideoItem)) {
                            GaanaVideoItem gaanaVideoItem = (GaanaVideoItem) businessObject;
                            String videoStreamingUrl = gaanaVideoItem.getVideoStreamingUrl();
                            String videoShareUrl = gaanaVideoItem.getVideoShareUrl();
                            Intent intent;
                            if (TextUtils.isEmpty(videoStreamingUrl)) {
                                c.a(context).b(context, false);
                            } else if (context instanceof SplashScreenActivity) {
                                intent = new Intent(context, GaanaActivity.class);
                                intent.putExtra("share_url", videoShareUrl);
                                intent.putExtra("video_url", videoStreamingUrl);
                                intent.putExtra("LAUNCH_YEAR_VIDEO_PLAYER_ACTIVITY", true);
                                context.startActivity(intent);
                            } else if (context instanceof GaanaActivity) {
                                if (GaanaMusicService.t()) {
                                    o.a(context, PauseReasons.MEDIA_BUTTON_TAP);
                                    Constants.dc = true;
                                }
                                if (f.v().w()) {
                                    f.v().F();
                                    Constants.dc = true;
                                }
                                if (com.utilities.d.g()) {
                                    intent = new Intent(context, VideoPlayerActivityTwo.class);
                                } else {
                                    intent = new Intent(context, FullScreenVideoPlayerActivity.class);
                                }
                                intent.setAction("com.google.android.exoplayer.demo.action.VIEW");
                                intent.putExtra("share_url", videoShareUrl);
                                intent.putExtra("video_url", videoStreamingUrl);
                                ((GaanaActivity) context).startActivityForResult(intent, 1001);
                            } else {
                                c.a(context).b(context, false);
                            }
                        }
                    }

                    public void onErrorResponse(BusinessObject businessObject) {
                        c.a(context).b(context, false);
                    }
                }, uRLManager);
                break;
            }
        }
        return z;
    }

    private void l() {
        this.b = a.a();
        this.d = this.b;
    }

    public ArrayList<f.a> e() {
        return this.h;
    }

    public String f() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public ArrayList<f.a> g() {
        if (this.i == null) {
            return new ArrayList();
        }
        return this.i;
    }

    public void c(y yVar) {
        this.e = yVar;
    }
}
