package com.dynamicview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.actionbar.GenericActionBar;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.facebook.share.internal.ShareConstants;
import com.fragments.BaseGaanaFragment;
import com.fragments.GaanaEducativeFragment;
import com.fragments.ProfileFragment;
import com.fragments.SettingsDetailFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.Login;
import com.gaana.R;
import com.gaana.SplashScreenActivity;
import com.gaana.WebViewActivity;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.adapter.CustomListAdapter.IAddListItemView;
import com.gaana.ads.base.ILifeCycleAwareCustomView;
import com.gaana.ads.interstitial.IAdType;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.models.AdsUJData;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.GaanaThemeModel;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.ProfileUsers.ProfileUser;
import com.gaana.models.Radios.Radio;
import com.gaana.view.BaseItemView;
import com.gaana.view.GaanaListView.OnDataLoadedListener;
import com.gaana.view.header.HomeCarouselView;
import com.gaana.view.item.CuratedDialog;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.appindexing.AppIndex;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.library.controls.CircularImageView;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.ADSTATUS;
import com.managers.ColombiaAdViewManager.b;
import com.managers.ColombiaAdViewManager.c;
import com.managers.ColombiaManager;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aa;
import com.managers.af;
import com.managers.an;
import com.managers.ap;
import com.managers.e;
import com.managers.s;
import com.managers.u;
import com.models.PlayerTrack;
import com.moengage.inapp.InAppMessage;
import com.services.d;
import com.services.l.ad;
import com.services.l.aq;
import com.services.l.r;
import com.services.l.y;
import com.utilities.Util;
import com.utilities.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class DynamicHomeFragment extends BaseGaanaFragment implements OnRefreshListener, com.constants.a.a, IAddListItemView, OnDataLoadedListener, b, c, com.services.l.a, y {
    public static InAppMessage d;
    private int A = -1;
    private int B = -1;
    private String C = "";
    private View D = null;
    private CuratedDialog E = null;
    private boolean F = false;
    private ILifeCycleAwareCustomView G;
    private View H = null;
    private boolean I = false;
    private com.managers.c J;
    int a = -1;
    int b = 0;
    View c = null;
    boolean e = false;
    private boolean f = false;
    private GaanaApplication g;
    private boolean h = false;
    private RecyclerView i = null;
    private SwipeRefreshLayout j;
    private CustomListAdapter k = null;
    private LinearLayout l;
    private View m = null;
    private String n = "";
    private String o = "";
    private ArrayList<BaseItemView> p = null;
    private ViewGroup q;
    private PublisherAdView r;
    private ADSTATUS s;
    private boolean t = false;
    private CircularImageView u;
    private int v = 0;
    private int w = 0;
    private boolean x = false;
    private Map<Integer, a> y = new HashMap();
    private AppBarLayout z;

    public static class a {
        public BaseItemView a;
        public int b;

        public a(BaseItemView baseItemView, int i) {
            this.a = baseItemView;
            this.b = i;
        }
    }

    public String getFragmentStackName() {
        return "home";
    }

    public void onDataLoaded(BusinessObject businessObject, BusinessObjectType businessObjectType) {
    }

    public void showHideEmtpyView(boolean z) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.m == null || this.loginStatus != this.g.getCurrentUser().getLoginStatus()) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            if (g.b()) {
                this.m = setContentView(R.layout.layout_home_new, viewGroup);
                this.l = (LinearLayout) this.m.findViewById(R.id.llParentHeader);
                View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.mmx_top_layout, null);
                ImageView imageView = (ImageView) inflate.findViewById(R.id.manufacturer_logo);
                LayoutParams layoutParams = imageView.getLayoutParams();
                if (Constants.o.equalsIgnoreCase("MICROMAX")) {
                    layoutParams.width = (int) TypedValue.applyDimension(1, 80.0f, getResources().getDisplayMetrics());
                    imageView.setLayoutParams(layoutParams);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.micromax_logo));
                } else if (Constants.o.equalsIgnoreCase("YU")) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.mmx_yu_logo));
                }
                this.l.addView(inflate);
                this.l.setVisibility(0);
            } else {
                this.m = setContentView(R.layout.layout_home_new, viewGroup);
            }
            this.C = an.a().a(an.a().a);
            this.z = (AppBarLayout) this.m.findViewById(R.id.app_bar_layout);
            this.f = true;
            this.g = GaanaApplication.getInstance();
            this.mContext = getActivity();
            this.i = (RecyclerView) this.m.findViewById(R.id.recycler_view);
            this.i.addOnScrollListener(new OnScrollListener() {
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    DynamicHomeFragment.this.v = DynamicHomeFragment.this.v + i2;
                }

                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    ((GaanaActivity) DynamicHomeFragment.this.mContext).showHideVoiceCoachMark(R.id.voice_longpress_coachmark, false);
                    if (DynamicHomeFragment.this.u.getVisibility() == 0) {
                        if (DynamicHomeFragment.this.v > 3000) {
                            DynamicHomeFragment.this.u.setAlpha(0.0f);
                        } else if (recyclerView.getScrollState() == 2) {
                            DynamicHomeFragment.this.u.setAlpha(0.5f);
                        } else if (recyclerView.getScrollState() == 1) {
                            DynamicHomeFragment.this.u.setAlpha(0.5f);
                        } else {
                            DynamicHomeFragment.this.u.setAlpha(1.0f);
                        }
                    }
                    if (i == 0 && DynamicHomeFragment.this.v > DynamicHomeFragment.this.w) {
                        DynamicHomeFragment.this.A = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() + 1;
                        DynamicHomeFragment.this.B = recyclerView.getAdapter().getItemCount();
                        an.a().c("scroll", AvidJSONUtil.KEY_Y, "", DynamicHomeFragment.this.C, "", "", String.valueOf(DynamicHomeFragment.this.B), String.valueOf(DynamicHomeFragment.this.A));
                        DynamicHomeFragment.this.w = DynamicHomeFragment.this.v;
                    }
                    DynamicHomeFragment.this.J.a(i);
                    if (!ap.a().i()) {
                        DynamicHomeFragment.this.j();
                    }
                    DynamicHomeFragment.this.b = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                }
            });
            m();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, 1, false);
            linearLayoutManager.setItemPrefetchEnabled(true);
            linearLayoutManager.setInitialPrefetchItemCount(4);
            this.I = false;
            this.i.setItemAnimator(null);
            this.i.setLayoutManager(linearLayoutManager);
            this.i.setItemViewCacheSize(4);
            this.k = new CustomListAdapter(this.mContext, null);
            this.j = (SwipeRefreshLayout) this.m.findViewById(R.id.swipe_refresh_layout);
            this.j.setOnRefreshListener(this);
            DynamicViewManager.a().a("");
            this.p = DynamicViewManager.a().a(this.mContext, (BaseGaanaFragment) this);
            i();
            r();
            a(this.p);
            if (ap.a().b(this.mContext) && q()) {
                this.r = new PublisherAdView(this.mContext);
                this.q = (ViewGroup) layoutInflater.inflate(R.layout.top_banner_ad, null);
                if (this.l == null) {
                    this.l = (LinearLayout) this.m.findViewById(R.id.llParentHeader);
                }
                this.l.addView(this.q);
                this.l.setVisibility(0);
            }
            GaanaApplication.getInstance().setGADParameter("");
            sendGAScreenName("Browse", "BrowseScreen");
            this.D = this.m.findViewById(R.id.remove_ad_cta);
            this.D.setVisibility(8);
            if (n() && !ap.a().f()) {
                this.E = new CuratedDialog(getActivity());
                this.E.showCuratedDialog();
            }
        } else if (!(this.i == null || this.i.getAdapter() == null)) {
            k();
            this.i.getAdapter().notifyDataSetChanged();
        }
        p();
        if (Constants.du) {
            ((GaanaActivity) this.mContext).showThemeBackground(this.g.getThemeRefreshRequired());
        } else {
            ((GaanaActivity) this.mContext).hideThemeBackground(this.g.getThemeRefreshRequired());
        }
        ((BaseActivity) this.mContext).setCustomActionBar((ViewGroup) this.m, new GenericActionBar(this.mContext, this.mContext.getString(R.string.home_tab), true, this));
        this.o = "https://gaana.com";
        this.n = "android-app://com.gaana/gaanagoogle/home";
        this.g.setNetworkExtrasBundle();
        this.z.setExpanded(true, false);
        aa.a().b(this.f);
        this.currentUJPage = "HOME";
        return this.m;
    }

    public CuratedDialog a() {
        return (this.E == null || this.E.isCuratedDialogShowing() == null) ? null : this.E;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (ap.a().b(this.mContext)) {
            IAdType interstitialAdType = ((GaanaActivity) this.mContext).getInterstitialAdType();
            if (interstitialAdType != null) {
                interstitialAdType.showAd();
            }
        }
    }

    private void i() {
        if (this.p != null) {
            this.y.clear();
            for (int i = 0; i < this.p.size(); i++) {
                Integer valueOf = Integer.valueOf(((BaseItemView) this.p.get(i)).getItemViewType());
                a aVar = (a) this.y.get(valueOf);
                if (aVar == null) {
                    this.y.put(valueOf, new a((BaseItemView) this.p.get(i), 1));
                } else {
                    aVar.b++;
                }
            }
            i iVar = (i) ((GaanaActivity) this.mContext).getViewPool();
            for (Entry entry : this.y.entrySet()) {
                if (((a) entry.getValue()).b > 2 && ((a) entry.getValue()).a.getDynamicView() != null) {
                    iVar.setMaxRecycledViews(h.a(((a) entry.getValue()).a.getDynamicView()), ((a) entry.getValue()).b * 5);
                }
            }
        }
    }

    private void a(ArrayList<BaseItemView> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            com.dynamicview.f.a dynamicView = ((BaseItemView) it.next()).getDynamicView();
            if (dynamicView != null && dynamicView.m().equals(DynamicViewType.spon_oc.name())) {
                this.t = true;
            }
        }
    }

    public boolean b() {
        return this.b <= 3;
    }

    private void j() {
        if (this.c != null && this.c.getBottom() < 30) {
            this.q.setVisibility(0);
            this.c.getLayoutParams().height = 0;
            this.c.setVisibility(8);
        }
    }

    private void k() {
        if (this.p != null) {
            for (int i = 0; i < this.p.size(); i++) {
                com.dynamicview.f.a dynamicView = ((BaseItemView) this.p.get(i)).getDynamicView();
                if (dynamicView != null && dynamicView.m().equals(DynamicViewType.download.name())) {
                    ((BaseItemView) this.p.get(i)).setIsToBeRefreshed(true);
                }
            }
        }
    }

    private void m() {
        this.u = (CircularImageView) this.m.findViewById(R.id.occasion_fab_button);
        final GaanaThemeModel b = s.a().b();
        if (b != null && !TextUtils.isEmpty(b.getFabItemID())) {
            if (!TextUtils.isEmpty(b.getFabIconImageArtwork())) {
                i.a().a(b.getFabIconImageArtwork(), new r() {
                    public void onSuccessfulResponse(Bitmap bitmap) {
                        DynamicHomeFragment.this.u.setBitmapToImageView(bitmap);
                        DynamicHomeFragment.this.u.setVisibility(0);
                        u a = u.a();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("FAB_");
                        stringBuilder.append(b.getFabItemID());
                        a.b(stringBuilder.toString(), "FAB appeared");
                    }

                    public void onErrorResponse(VolleyError volleyError) {
                        DynamicHomeFragment.this.u.setVisibility(8);
                    }
                });
            }
            this.u.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (DynamicHomeFragment.this.u.getAlpha() >= 0.2f) {
                        if (!Util.j(DynamicHomeFragment.this.getActivity()) || DynamicHomeFragment.this.g.isAppInOfflineMode()) {
                            ap.a().f(DynamicHomeFragment.this.mContext);
                            return;
                        }
                        if (b != null) {
                            if (DynamicHomeFragment.this.mContext instanceof BaseActivity) {
                                ((BaseActivity) DynamicHomeFragment.this.mContext).showProgressDialog(Boolean.valueOf(true), DynamicHomeFragment.this.getResources().getString(R.string.loading));
                            }
                            u a = u.a();
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("FAB_");
                            stringBuilder.append(b.getFabItemID());
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(b.getFabItemType());
                            stringBuilder2.append("_");
                            stringBuilder2.append(b.getFabItemID());
                            a.a(stringBuilder.toString(), "FAB clicked", stringBuilder2.toString());
                            com.services.c.a(DynamicHomeFragment.this.mContext, true).a(DynamicHomeFragment.this.mContext, DynamicHomeFragment.this.g, b.getFabItemType(), b.getFabItemID());
                        }
                    }
                }
            });
        } else if (this.u != null) {
            this.u.setVisibility(8);
        }
    }

    private boolean n() {
        if (((GaanaActivity) this.mContext).getCurrentFragment() instanceof GaanaEducativeFragment) {
            return false;
        }
        if (!TextUtils.isEmpty(d.a().b("PREFERENCE_CURATED_DOWNLOAD_ACTIVE", "", false)) && d.a().b("PREFERENCE_CURATED_DOWNLOAD_ACTIVE", "", false).equalsIgnoreCase("NO")) {
            return false;
        }
        boolean b = d.a().b("PREFERENCE_CURATED_DIALOG_CLOSED", false, true);
        if (b) {
            Constants.aj = Constants.al;
        } else {
            Constants.aj = Constants.ak;
        }
        if (!ap.a().j() || !Util.j(getActivity()) || this.g.isAppInOfflineMode()) {
            return false;
        }
        long b2 = d.a().b(0, "PREFERENCE_CURATED_DOWNLOAD_RESET", false);
        if (b2 == 0) {
            d.a().a(Constants.ao, "PREFERENCE_CURATED_DOWNLOAD_RESET", false);
        } else if (b2 != Constants.ao) {
            d.a().a(Constants.ao, "PREFERENCE_CURATED_DOWNLOAD_RESET", false);
            d.a().a("PREFERENCE_CURATED_DOWNLOAD_COUNT", 0, false);
        }
        int b3 = d.a().b("PREFERENCE_CURATED_DOWNLOAD_COUNT", 0, false);
        if (d.a().b("PREFERENCE_SESSION_TRIAL_FIRSTTIME", false, true) || (!d.a().b("PREFERENCE_CURATED_DIALOG_SHOWN", false, true) && Constants.aj > 0 && GaanaApplication.sessionHistoryCount - d.a().b("PREFERENCE_SESSION_TRIAL_COUNT", 0, true) >= Constants.aj && d.a().b("PREFERENCE_CURATED_DOWNLOAD_FCAP", 0, false) * Constants.aj >= b3)) {
            if (b) {
                d.a().a("PREFERENCE_CURATED_DOWNLOAD_COUNT", b3 + 1, false);
            }
            d.a().a("PREFERENCE_SESSION_TRIAL_FIRSTTIME", false, true);
            return true;
        }
        if (b) {
            d.a().a("PREFERENCE_CURATED_DOWNLOAD_COUNT", b3 + 1, false);
        }
        return false;
    }

    private void o() {
        this.q.setVisibility(8);
    }

    public void loadTopBannerAds() {
        if (this.F) {
            this.q.setVisibility(0);
            return;
        }
        this.F = true;
        GaanaApplication.getInstance().updateMetadata();
        if (this.q == null) {
            this.q = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
        }
        if (!this.t || ColombiaAdViewManager.a().f()) {
            this.q.setVisibility(0);
            AdsUJData adsUJData = new AdsUJData();
            adsUJData.setAdType("dfp");
            adsUJData.setSectionId("");
            ColombiaAdViewManager.a().d();
            ColombiaAdViewManager.a().e();
            if (e.V == 0) {
                if (e.S > 0) {
                    adsUJData.setAdType("dfp");
                    adsUJData.setAdUnitCode(e.z);
                    adsUJData.setSectionName(Constants.dE);
                    ColombiaAdViewManager.a().a(this.mContext, this.q, e.z, this.r, (b) this, e.S, Constants.dE, adsUJData);
                } else {
                    ColombiaAdViewManager.a().a(this.q);
                }
            }
            return;
        }
        this.q.setVisibility(8);
    }

    private void p() {
        if (TextUtils.isEmpty(e.O)) {
            this.x = true;
            return;
        }
        AdsUJData adsUJData = new AdsUJData();
        adsUJData.setSectionName(Constants.dF);
        adsUJData.setAdUnitCode(e.O);
        adsUJData.setSectionId("");
        adsUJData.setAdType("dfp");
        LinearLayout linearLayout = (LinearLayout) this.m.findViewById(R.id.bottomAdSlot);
        this.G = ColombiaAdViewManager.a().a(this.mContext, linearLayout, e.O, Constants.dF, (com.services.l.a) this, adsUJData);
        this.x = false;
        if (this.G != null) {
            getLifecycle().a(this.G);
        }
    }

    public void onAdBottomBannerLoaded() {
        this.removeAdDeeplink = ColombiaAdViewManager.a().b();
        if (this.D != null && !TextUtils.isEmpty(this.removeAdDeeplink)) {
            this.D.setVisibility(0);
            this.D.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    u.a().a("Gaana Plus", "remove_adhook", "HomePage");
                    com.services.c.a(DynamicHomeFragment.this.mContext).a(DynamicHomeFragment.this.mContext, DynamicHomeFragment.this.removeAdDeeplink, DynamicHomeFragment.this.g);
                }
            });
        }
    }

    public void onAdBottomBannerGone() {
        if (this.D != null && !TextUtils.isEmpty(this.removeAdDeeplink)) {
            this.D.setVisibility(8);
            this.D.setOnClickListener(null);
        }
    }

    private boolean q() {
        return this.s != ADSTATUS.LOADING;
    }

    private void r() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("LAUNCH_PAGE");
            if (!TextUtils.isEmpty(string) && string.equals("Home")) {
                this.a = Integer.parseInt(arguments.getString("DEEPLINKING_SCREEN_EXTRA_PARAM"));
            }
            boolean z = arguments.getBoolean("PLAY_DEEPLINKING_SONG", false);
            Radio radio = (Radio) arguments.getSerializable("PLAY_DEEPLINKING_RADIO");
            String string2 = arguments.getString("DEEPLINKING_SCREEN_EXTRA_PARAM");
            BusinessObject a;
            if (!arguments.getBoolean("LAUNCH_DETAIL_PAGE", false) || this.g.getListingComponents() == null) {
                if (z) {
                    ArrayList arrayList = new ArrayList();
                    PlayerTrack b = com.services.c.b();
                    if (b != null) {
                        com.services.c.a(null);
                        arrayList.add(b);
                        PlayerManager.a(this.mContext).a(arrayList, b);
                        PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
                        ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
                        ((GaanaActivity) this.mContext).setSlideUpPanel(true);
                        if (b != null && b.b().isLocalMedia()) {
                            this.g.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.SHARE.name());
                            af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.albumMenu, b.b());
                        }
                    }
                } else if (radio != null) {
                    a(radio);
                } else if (string2 != null && string2.equalsIgnoreCase("g")) {
                    s();
                }
            } else if (radio != null) {
                a = this.g.getListingComponents().a();
                a(radio);
                af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.radioMenu, a);
            } else {
                a = this.g.getListingComponents().a();
                af.a(this.mContext, (BaseGaanaFragment) this).c(string2);
                if (a instanceof Album) {
                    af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.albumMenu, a);
                } else if (a instanceof Playlist) {
                    af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.playlistMenu, a);
                } else if (a instanceof Artist) {
                    af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.artistMenu, a);
                }
            }
            ProfileUser profileUser = (ProfileUser) arguments.getSerializable("SHOW_PROFILE_USER");
            if (profileUser != null) {
                if (!"0".equals(profileUser.getBusinessObjId())) {
                    a(profileUser);
                } else if (!this.g.getCurrentUser().getLoginStatus()) {
                    Intent intent = new Intent(this.mContext, Login.class);
                    intent.putExtra("is_login_as_activity_result", true);
                    startActivity(intent);
                }
            }
        }
    }

    public void onStart() {
        d();
        super.onStart();
    }

    public void onStop() {
        e();
        super.onStop();
        w();
    }

    private void a(final ProfileUser profileUser) {
        ((BaseActivity) this.mContext).checkSetLoginStatus(new ad() {
            public void onLoginSuccess() {
                if (DynamicHomeFragment.this.g.getCurrentUser() != null && DynamicHomeFragment.this.g.getCurrentUser().getLoginStatus()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("EXTRA_PROFILE_ORIGIN_MYPROFILE", ShareConstants.PEOPLE_IDS);
                    bundle.putSerializable("EXTRA_PROFILE_USER_BUSINESS_OBJECT", profileUser);
                    BaseGaanaFragment profileFragment = new ProfileFragment();
                    profileFragment.setArguments(bundle);
                    ((GaanaActivity) DynamicHomeFragment.this.mContext).displayFragment(profileFragment);
                }
            }
        }, null, true);
    }

    private void s() {
        ((BaseActivity) this.mContext).checkSetLoginStatus(new ad() {
            public void onLoginSuccess() {
                if (DynamicHomeFragment.this.g.getCurrentUser() != null && DynamicHomeFragment.this.g.getCurrentUser().getLoginStatus()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("KEY_SETTINGS", 1);
                    BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                    settingsDetailFragment.setArguments(bundle);
                    ((GaanaActivity) DynamicHomeFragment.this.mContext).displayFragment(settingsDetailFragment);
                    if (DynamicHomeFragment.this.mContext instanceof SplashScreenActivity) {
                        ((SplashScreenActivity) DynamicHomeFragment.this.mContext).finish();
                    }
                }
            }
        }, null);
    }

    private void a(Radio radio) {
        try {
            if (radio.getType().equals(com.constants.c.d.c)) {
                com.managers.ad.a(this.mContext).a((BusinessObject) radio);
                return;
            }
            com.managers.ad.a(this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", radio.getBusinessObjId()).replace("<radio_type>", radio.getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), (BusinessObject) radio);
        } catch (Exception unused) {
        }
    }

    public void c() {
        t();
    }

    private void t() {
        if (this.h) {
            for (int i = 0; i < this.p.size(); i++) {
                BaseItemView baseItemView = (BaseItemView) this.p.get(i);
                baseItemView.setPositionToBeRefreshed(i);
                baseItemView.setIsToBeRefreshed(this.h);
            }
        }
        ((GaanaActivity) this.mContext).hideProgressDialog();
        if (this.h) {
            this.k.notifyDataSetChanged();
        } else {
            ((BaseActivity) this.mContext).resetLoginStatus();
            this.k.setParamaters(a(this.mContext, (BaseGaanaFragment) this), this);
            this.i.setAdapter(this.k);
        }
        z();
    }

    private void u() {
        if (d != null) {
            a(d);
            d = null;
        }
    }

    public void onResume() {
        u();
        ColombiaAdViewManager.a().a((c) this);
        if (((BaseActivity) this.mContext).hasLoginChanged().booleanValue() || this.f) {
            this.e = false;
            c();
            this.f = false;
        }
        if (!TextUtils.isEmpty(this.g.getPromorUrl())) {
            Intent intent = new Intent(this.mContext, WebViewActivity.class);
            intent.putExtra("EXTRA_WEBVIEW_URL", this.g.getPromorUrl());
            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
            intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
            intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
            this.mContext.startActivity(intent);
            this.g.setPromoUrl(null);
        }
        updateView();
        ColombiaAdViewManager.a().a(this.mContext, this.q);
        if (this.a > -1) {
            int i;
            com.dynamicview.f.a aVar = (com.dynamicview.f.a) DynamicViewManager.a().e().get(this.a);
            if (aVar != null) {
                i = -1;
                for (int i2 = 0; i2 < this.p.size(); i2++) {
                    if (this.p.get(i2) instanceof DynamicHomeScrollerView) {
                        com.dynamicview.f.a dynamicView = ((DynamicHomeScrollerView) this.p.get(i2)).getDynamicView();
                        if (dynamicView == null || aVar.x() == null) {
                            break;
                        } else if (dynamicView.x() != null && dynamicView.x().equals(aVar.x())) {
                            i = i2;
                        }
                    }
                }
            } else {
                i = -1;
            }
            if (i > -1) {
                this.i.scrollToPosition(i);
            }
            this.a = -1;
        }
        super.onResume();
        DynamicViewManager.a().c((y) this);
        if (getUserVisibleHint()) {
            v();
        }
        this.J.a(0);
        if (this.r != null) {
            this.r.resume();
        }
        ((BaseActivity) this.mContext).currentScreen = "Browse";
    }

    private void v() {
        if (this.p != null && this.p.size() > 0) {
            int size = this.p.size();
            for (int i = 0; i < size; i++) {
                if (this.p.get(i) instanceof HomeCarouselView) {
                    ((HomeCarouselView) this.p.get(i)).mTimerStart();
                    return;
                }
            }
        }
    }

    private void w() {
        if (this.p != null && this.p.size() > 0) {
            int size = this.p.size();
            for (int i = 0; i < size; i++) {
                if (this.p.get(i) instanceof HomeCarouselView) {
                    ((HomeCarouselView) this.p.get(i)).mTimerCancel();
                    return;
                }
            }
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            setCurrentFragment();
            v();
            GaanaApplication.getInstance().setCurrentPageName(getPageName());
            return;
        }
        w();
        if (this.p != null) {
            Iterator it = this.p.iterator();
            while (it.hasNext()) {
                BaseItemView baseItemView = (BaseItemView) it.next();
                if (baseItemView != null) {
                    baseItemView.setUserVisibleHint(z);
                }
            }
        }
    }

    public void onPause() {
        ColombiaAdViewManager.a().a(null);
        DynamicViewManager.a().c(null);
        if (this.r != null) {
            this.r.pause();
        }
        super.onPause();
        this.J.b();
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void d() {
        if (!this.mClient.isConnected()) {
            this.mClient.connect();
        }
        this.TITLE = "Gaana";
        List arrayList = new ArrayList();
        AppIndex.AppIndexApi.view(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.n), this.TITLE, Uri.parse(this.o), arrayList);
    }

    public void e() {
        AppIndex.AppIndexApi.viewEnd(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.n));
        this.mClient.disconnect();
    }

    public void onDestroy() {
        super.onDestroy();
        ColombiaAdViewManager.a().a(this.r);
        if (this.J != null) {
            this.J.a();
        }
    }

    public void onDestroyView() {
        if (this.G != null) {
            this.G.destroy();
            getLifecycle().b(this.G);
            if (this.D != null) {
                this.D.setVisibility(8);
            }
        }
        if (this.m.getParent() != null) {
            ((ViewGroup) this.m.getParent()).removeView(this.m);
        }
        super.onDestroyView();
        if (this.p != null) {
            Iterator it = this.p.iterator();
            while (it.hasNext()) {
                BaseItemView baseItemView = (BaseItemView) it.next();
                if (!(baseItemView == null || (baseItemView instanceof DynamicHomeScrollerView))) {
                    baseItemView.setFirstCall(true);
                }
            }
        }
    }

    public void a(InAppMessage inAppMessage) {
        View a = com.managers.r.a().a((Activity) this.mContext, new aq() {
            public void a() {
                if (DynamicHomeFragment.this.H != null) {
                    DynamicHomeFragment.this.x();
                }
            }
        }, inAppMessage);
        if (a != null) {
            if (this.H == null) {
                this.H = a;
            } else {
                return;
            }
        }
        try {
            GaanaApplication.getInstance().inAppShownList.put(new JSONObject(inAppMessage.content).getString("template"), Long.valueOf(System.currentTimeMillis()));
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private void x() {
        if (com.utilities.d.g()) {
            int measuredHeight = this.H.getMeasuredHeight();
            final AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) this.H.getLayoutParams();
            ObjectAnimator duration = ObjectAnimator.ofFloat(this, "alpha", new float[]{0.0f}).setDuration(300);
            ValueAnimator.ofInt(new int[]{measuredHeight, 0}).setDuration(400).addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    layoutParams.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    if (DynamicHomeFragment.this.H != null) {
                        DynamicHomeFragment.this.H.setLayoutParams(layoutParams);
                    }
                }
            });
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{duration, r0});
            animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    DynamicHomeFragment.this.H;
                    DynamicHomeFragment.this.H = null;
                }
            });
            animatorSet.start();
            return;
        }
        View view = this.H;
    }

    private void y() {
        if (this.j != null) {
            this.j.setRefreshing(false);
        }
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        BaseItemView baseItemView = this.y.get(Integer.valueOf(i)) != null ? ((a) this.y.get(Integer.valueOf(i))).a : null;
        if (baseItemView != null) {
            return baseItemView.onCreateViewHolder(viewGroup, i);
        }
        return null;
    }

    private boolean a(int i) {
        com.dynamicview.f.a dynamicView = ((BaseItemView) this.p.get(i)).getDynamicView();
        boolean z = false;
        if (dynamicView == null) {
            return false;
        }
        Map j = dynamicView.j();
        if (j == null) {
            return false;
        }
        String str = (String) j.get("ad_type");
        if (str != null && str.equalsIgnoreCase("masthead_scrollable")) {
            z = true;
        }
        return z;
    }

    public View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (viewHolder == null) {
            return new View(this.mContext);
        }
        if (this.c == null && a(i)) {
            o();
            this.c = viewHolder.itemView;
        }
        BaseItemView baseItemView = (BaseItemView) this.p.get(i);
        if (this.I) {
            return baseItemView.getPopulatedView(i, viewHolder, (ViewGroup) viewHolder.itemView.getParent(), true);
        }
        return baseItemView.getPopulatedView(i, viewHolder, (ViewGroup) viewHolder.itemView.getParent());
    }

    public int getItemViewType(int i) {
        if (this.y.size() == 0 && this.p.size() > 0) {
            i();
        }
        return ((BaseItemView) this.p.get(i)).getItemViewType();
    }

    public boolean f() {
        return this.e;
    }

    public void onRefresh() {
        if (this.i != null && this.i.getAdapter() != null) {
            this.h = true;
            if (ap.a().b(this.mContext)) {
                ColombiaManager.b().c();
            }
            this.e = true;
            c();
            if (ap.a().b(this.mContext) && q() && this.q == null) {
                this.q = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
            }
            y();
            this.h = false;
        }
    }

    private int a(Context context, BaseGaanaFragment baseGaanaFragment) {
        if (this.p == null) {
            this.p = DynamicViewManager.a().a(context, baseGaanaFragment);
            i();
        }
        return this.p.size();
    }

    public void notifyItemChanged(int i) {
        if (this.k != null) {
            this.k.notifyItemChanged(i);
        }
    }

    public void notifyItemRemoved(int i) {
        if (this.k != null) {
            this.k.notifyItemRemoved(i);
        }
    }

    public void a(ADSTATUS adstatus) {
        this.s = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public void b(ADSTATUS adstatus) {
        this.s = adstatus;
        ColombiaAdViewManager.a(adstatus);
        if (this.c != null) {
            o();
        }
    }

    public void c(ADSTATUS adstatus) {
        this.s = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public void d(ADSTATUS adstatus) {
        this.s = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public String getPageName() {
        return PAGE_SORCE_NAME.HOME.name();
    }

    public void l() {
        if (ap.a().b(this.mContext) && this.q == null) {
            this.q = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
        }
    }

    public void refreshDataandAds() {
        ColombiaAdViewManager.a().a(this.mContext, this.q);
        onRefresh();
    }

    public void g() {
        if (Constants.du) {
            ((GaanaActivity) this.mContext).showThemeBackground(true);
        } else {
            ((GaanaActivity) this.mContext).hideThemeBackground(true);
        }
        this.I = true;
        k();
        this.i.getAdapter().notifyDataSetChanged();
    }

    public void OnDynamicViewDataFetched() {
        GaanaActivity gaanaActivity = (GaanaActivity) this.mContext;
        if (gaanaActivity != null && !gaanaActivity.isFinishing()) {
            gaanaActivity.runOnUiThread(new Runnable() {
                public void run() {
                    DynamicHomeFragment.this.onRefresh();
                }
            });
        }
    }

    private void z() {
        this.J = new com.managers.c();
        this.J.a(this.mContext, this.i, true, false, false, 80.0f);
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("top_banner", this.F);
    }

    public void onFragmentScroll() {
        if (this.i != null) {
            this.i.smoothScrollToPosition(0);
        }
        if (this.z != null) {
            this.z.setExpanded(true, false);
        }
    }

    public void onAdConfigLoaded() {
        if (this.x) {
            p();
        }
    }

    public void h() {
        if (this.m != null) {
            this.m.findViewById(R.id.bottomAdSlot).setVisibility(8);
            this.m.findViewById(R.id.remove_ad_cta).setVisibility(8);
        }
    }
}
