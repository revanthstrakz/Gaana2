package com.fragments;

import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.actionbar.GenericActionBar;
import com.android.volley.VolleyError;
import com.android.volley.i.a;
import com.android.volley.i.b;
import com.constants.Constants;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.DynamicHomeScrollerView;
import com.dynamicview.DynamicViewManager;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.dynamicview.f;
import com.dynamicview.h;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.adapter.CustomListAdapter.IAddListItemView;
import com.gaana.ads.base.ILifeCycleAwareCustomView;
import com.gaana.ads.interstitial.IAdType;
import com.gaana.application.GaanaApplication;
import com.gaana.models.AdsUJData;
import com.gaana.view.BaseItemView;
import com.gaana.view.UpgradeHomeView;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.AppIndexApi.AppIndexingLink;
import com.i.i;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.ADSTATUS;
import com.managers.ColombiaAdViewManager.c;
import com.managers.ColombiaManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aa;
import com.managers.an;
import com.managers.ap;
import com.managers.e;
import com.managers.u;
import com.services.l;
import com.til.colombia.android.service.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RadioActivityFragment extends BaseGaanaFragment implements OnRefreshListener, a, b<Object>, IAddListItemView, ColombiaAdViewManager.b, c, l.a {
    public static int b = 10;
    public static int c = 12;
    boolean a = false;
    private boolean d = false;
    private ArrayList<BaseItemView> e = null;
    private View f = null;
    private String g = "";
    private String h = "";
    private LinearLayout i;
    private RecyclerView j = null;
    private SwipeRefreshLayout k;
    private CustomListAdapter l = null;
    private ViewGroup m;
    private PublisherAdView n;
    private ADSTATUS o;
    private int p;
    private int q = 0;
    private int r = 0;
    private Map<Integer, DynamicHomeFragment.a> s = new HashMap();
    private List<f.a> t = new ArrayList();
    private AppBarLayout u;
    private View v = null;
    private ILifeCycleAwareCustomView w;

    public void showHideEmtpyView(boolean z) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f == null || this.loginStatus != this.mAppState.getCurrentUser().getLoginStatus()) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.f = setContentView(R.layout.layout_home_new, viewGroup);
            this.i = (LinearLayout) this.f.findViewById(R.id.llParentHeader);
            this.u = (AppBarLayout) this.f.findViewById(R.id.app_bar_layout);
            this.a = true;
            this.mContext = getActivity();
            ColombiaAdViewManager.a().e();
            this.j = (RecyclerView) this.f.findViewById(R.id.recycler_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, 1, false);
            linearLayoutManager.setAutoMeasureEnabled(false);
            this.j.setHasFixedSize(true);
            this.j.setLayoutManager(linearLayoutManager);
            this.j.addOnScrollListener(new OnScrollListener() {
                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (i == 0 && RadioActivityFragment.this.q > RadioActivityFragment.this.r) {
                        double findLastCompletelyVisibleItemPosition = (double) (((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() + 1);
                        double itemCount = (double) recyclerView.getAdapter().getItemCount();
                        an.a().c("scroll", AvidJSONUtil.KEY_Y, "", an.a().a(an.a().a), "", "", String.valueOf((int) itemCount), String.valueOf((int) findLastCompletelyVisibleItemPosition));
                        RadioActivityFragment.this.r = RadioActivityFragment.this.q;
                    }
                }

                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    RadioActivityFragment.this.q = RadioActivityFragment.this.q + i2;
                }
            });
            this.l = new CustomListAdapter(this.mContext, null);
            this.k = (SwipeRefreshLayout) this.f.findViewById(R.id.swipe_refresh_layout);
            this.k.setOnRefreshListener(this);
            ((BaseActivity) this.mContext).showProgressDialog();
            i.a().a(a(this.d), "radio_meta", (b) this, (a) this);
            this.n = new PublisherAdView(this.mContext);
            if (ap.a().b(this.mContext) && g()) {
                this.m = (ViewGroup) layoutInflater.inflate(R.layout.top_banner_ad, null);
                this.i.addView(this.m);
            }
            this.i.setVisibility(0);
            sendGAScreenName("RadioScreen", "RadioScreen");
            f();
        } else if (!(this.j == null || this.j.getAdapter() == null)) {
            this.j.getAdapter().notifyDataSetChanged();
        }
        GaanaApplication.getInstance().setGADParameter("");
        ((BaseActivity) this.mContext).setCustomActionBar((ViewGroup) this.f, new GenericActionBar(this.mContext, getString(R.string.radio), false, this));
        if (Constants.du) {
            ((GaanaActivity) this.mContext).showThemeBackground(false);
        }
        this.h = "https://gaana.com/radio";
        this.g = "android-app://com.gaana/gaanagoogle/radio";
        this.v = this.f.findViewById(R.id.remove_ad_cta);
        this.v.setVisibility(8);
        e();
        this.u.setExpanded(true, false);
        aa.a().b(this.a);
        this.currentUJPage = "RADIO";
        return this.f;
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

    private void d() {
        if (this.e != null) {
            this.s.clear();
            for (int i = 0; i < this.e.size(); i++) {
                Integer valueOf = Integer.valueOf(((BaseItemView) this.e.get(i)).getItemViewType());
                DynamicHomeFragment.a aVar = (DynamicHomeFragment.a) this.s.get(valueOf);
                if (aVar == null) {
                    this.s.put(valueOf, new DynamicHomeFragment.a((BaseItemView) this.e.get(i), 1));
                } else {
                    aVar.b++;
                }
            }
            com.dynamicview.i iVar = (com.dynamicview.i) ((GaanaActivity) this.mContext).getViewPool();
            for (Entry entry : this.s.entrySet()) {
                if (((DynamicHomeFragment.a) entry.getValue()).b > 2) {
                    iVar.setMaxRecycledViews(h.a(((DynamicHomeFragment.a) entry.getValue()).a.getDynamicView()), ((DynamicHomeFragment.a) entry.getValue()).b * 5);
                }
            }
        }
    }

    public void onAdBottomBannerLoaded() {
        if (this.v != null && !TextUtils.isEmpty(this.removeAdDeeplink)) {
            this.v.setVisibility(0);
            this.v.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    u.a().a("Gaana Plus", "remove_adhook", "RadioPage");
                    com.services.c.a(RadioActivityFragment.this.mContext).a(RadioActivityFragment.this.mContext, RadioActivityFragment.this.removeAdDeeplink, RadioActivityFragment.this.mAppState);
                }
            });
        }
    }

    public void onAdBottomBannerGone() {
        if (this.v != null && !TextUtils.isEmpty(this.removeAdDeeplink)) {
            this.v.setVisibility(8);
            this.v.setOnClickListener(null);
        }
    }

    private void e() {
        LinearLayout linearLayout = (LinearLayout) this.f.findViewById(R.id.bottomAdSlot);
        ColombiaAdViewManager.a().e();
        e.a();
        if (e.X == 0) {
            this.w = ColombiaAdViewManager.a().a(this.mContext, linearLayout, e.A, "RADIO_BOTTOM_BANNER", (l.a) this, new AdsUJData[0]);
            if (this.w != null) {
                getLifecycle().a(this.w);
            }
        }
    }

    private void f() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("LAUNCH_PAGE");
            if (!TextUtils.isEmpty(string) && string.equals("Radio")) {
                this.p = Integer.parseInt(arguments.getString("DEEPLINKING_SCREEN_EXTRA_PARAM"));
            }
        }
    }

    public void loadTopBannerAds() {
        if (e.Y == 0) {
            ColombiaAdViewManager.a().b(this.mContext, this.m, e.B, this.n, this, "RADIO_TAB_TOP");
            return;
        }
        ColombiaAdViewManager.a().a(this.mContext, this.m, 27, getClass().getSimpleName(), this.n, (ColombiaAdViewManager.b) this, "RADIO_TAB_TOP", new AdsUJData[0]);
    }

    private boolean g() {
        return this.o != ADSTATUS.LOADING;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onStart() {
        super.onStart();
        b();
    }

    public void onStop() {
        c();
        super.onStop();
    }

    public void onResume() {
        updateView();
        ColombiaAdViewManager.a().a((c) this);
        ColombiaAdViewManager.a().a(this.mContext, this.m);
        if (this.p > -1 && this.t != null && this.e != null && this.p < this.t.size()) {
            int i;
            f.a aVar = (f.a) this.t.get(this.p);
            if (aVar != null) {
                i = -1;
                for (int i2 = 0; i2 < this.e.size(); i2++) {
                    if (this.e.get(i2) instanceof DynamicHomeScrollerView) {
                        f.a dynamicView = ((DynamicHomeScrollerView) this.e.get(i2)).getDynamicView();
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
                this.j.scrollToPosition(i);
            }
            this.p = -1;
        }
        if (this.n != null) {
            this.n.resume();
        }
        super.onResume();
    }

    public void onPause() {
        ColombiaAdViewManager.a().a(null);
        if (this.n != null) {
            this.n.pause();
        }
        super.onPause();
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            setCurrentFragment();
            GaanaApplication.getInstance().setCurrentPageName(getPageName());
        }
    }

    public void a() {
        h();
    }

    private void h() {
        if (this.d) {
            for (int i = 0; i < this.e.size(); i++) {
                ((BaseItemView) this.e.get(i)).setIsToBeRefreshed(this.d);
            }
        }
        ((GaanaActivity) this.mContext).hideProgressDialog();
        if (!this.d) {
            ((BaseActivity) this.mContext).resetLoginStatus();
            this.l.setParamaters(a(this.mContext, (BaseGaanaFragment) this), this);
            this.j.setAdapter(this.l);
        }
    }

    public void b() {
        if (!this.mClient.isConnected()) {
            this.mClient.connect();
        }
        this.TITLE = "Gaana Radio";
        List arrayList = new ArrayList();
        Uri parse = Uri.parse(this.g);
        Uri parse2 = Uri.parse(this.h);
        arrayList.add(new AppIndexingLink(parse, Uri.parse("https://gaana.com/gaanaradio"), new View(this.mContext)));
        AppIndex.AppIndexApi.view(this.mClient, (GaanaActivity) this.mContext, parse, this.TITLE, parse2, arrayList);
    }

    public void c() {
        AppIndex.AppIndexApi.viewEnd(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.g));
        this.mClient.disconnect();
    }

    public void onDestroyView() {
        if (this.w != null) {
            this.w.destroy();
            getLifecycle().b(this.w);
        }
        if (this.f.getParent() != null) {
            ((ViewGroup) this.f.getParent()).removeView(this.f);
        }
        ((GaanaActivity) this.mContext).hideProgressDialog();
        ColombiaAdViewManager.a().a(this.n);
        if (this.e != null) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                BaseItemView baseItemView = (BaseItemView) it.next();
                if (baseItemView != null) {
                    baseItemView.setFirstCall(true);
                }
            }
        }
        if (this.n != null) {
            this.n.destroy();
        }
        super.onDestroyView();
    }

    private int a(Context context, BaseGaanaFragment baseGaanaFragment) {
        if (this.e == null) {
            this.e = DynamicViewManager.a().a(this.t, context, baseGaanaFragment);
        }
        return this.e.size();
    }

    public View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        int i2 = i;
        ViewHolder viewHolder2 = viewHolder;
        if (!(((BaseItemView) this.e.get(i2)) instanceof UpgradeHomeView)) {
            return ((BaseItemView) this.e.get(i2)).getPopulatedView(i2, viewHolder2, viewGroup);
        }
        f.a dynamicView = ((BaseItemView) this.e.get(i2)).getDynamicView();
        final View view = viewHolder2.itemView;
        if (dynamicView.w().equalsIgnoreCase("columbia")) {
            ColombiaManager.b().a(0, this.mContext, 8, Long.parseLong(dynamicView.q()), (LinearLayout) view, "RadioActivity", new ColombiaManager.a() {
                public void onItemLoaded(Item item) {
                    view.setPadding(0, RadioActivityFragment.this.mContext.getResources().getDimensionPixelSize(R.dimen.bw_section_vert_padding_half), 0, RadioActivityFragment.this.mContext.getResources().getDimensionPixelSize(R.dimen.bw_section_vert_padding_half));
                    view.setVisibility(0);
                }

                public void onItemRequestFailed(Exception exception) {
                    view.setVisibility(8);
                }
            }, Constants.dT);
            return view;
        }
        ColombiaAdViewManager.a().a(this.mContext, (LinearLayout) view, new PublisherAdView(this.mContext), dynamicView.q(), null, 100, Constants.dT, new AdsUJData[0]);
        return view;
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        BaseItemView baseItemView = ((DynamicHomeFragment.a) this.s.get(Integer.valueOf(i))).a;
        if (baseItemView instanceof UpgradeHomeView) {
            return new ItemAdViewHolder(a(viewGroup));
        }
        return baseItemView.onCreateViewHolder(viewGroup, i);
    }

    public View a(ViewGroup viewGroup) {
        return LayoutInflater.from(this.mContext).inflate(R.layout.view_native_ad_dfp_colombia, viewGroup, false);
    }

    public int getItemViewType(int i) {
        return ((BaseItemView) this.e.get(i)).getItemViewType();
    }

    public void onRefresh() {
        if (this.j != null && this.j.getAdapter() != null) {
            this.d = true;
            if (ap.a().b(this.mContext)) {
                ColombiaManager.b().c();
            }
            a();
            if (ap.a().b(this.mContext) && g() && this.m == null) {
                this.m = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
            }
            i();
            this.d = false;
        }
    }

    private void i() {
        if (this.k != null) {
            this.k.setRefreshing(false);
        }
    }

    public void a(ADSTATUS adstatus) {
        this.o = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public void b(ADSTATUS adstatus) {
        this.o = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public void c(ADSTATUS adstatus) {
        this.o = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public void d(ADSTATUS adstatus) {
        this.o = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public String getPageName() {
        return PAGE_SORCE_NAME.RADIO.name();
    }

    public void l() {
        if (this.m == null) {
            this.m = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
        }
    }

    private URLManager a(boolean z) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(1440);
        uRLManager.a("https://apiv2.gaana.com/radio/metadata");
        uRLManager.c(Boolean.valueOf(z));
        uRLManager.a(BusinessObjectType.DynamicViewSections);
        return uRLManager;
    }

    public void onResponse(Object obj) {
        if (obj instanceof com.dynamicview.e) {
            com.dynamicview.e eVar = (com.dynamicview.e) obj;
            if (eVar.b() != null && eVar.b().size() > 0) {
                this.t.clear();
                for (int i = 0; i < eVar.b().size(); i++) {
                    if (!TextUtils.isEmpty(((com.dynamicview.e.a) eVar.b().get(i)).b())) {
                        this.t.add(new f.a(((com.dynamicview.e.a) eVar.b().get(i)).b(), "url", DynamicViewType.section_heading.name(), "url_seeall", "source_name", "ad_code", "0", "140"));
                    }
                    List a = ((com.dynamicview.e.a) eVar.b().get(i)).a();
                    if (a != null) {
                        this.t.addAll(a);
                    }
                }
                this.e = DynamicViewManager.a().a(this.t, this.mContext, this);
                d();
                a();
            }
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.e = DynamicViewManager.a().a(com.dynamicview.a.b(), this.mContext, this);
        d();
    }
}
