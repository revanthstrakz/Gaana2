package com.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.actionbar.GenericBackActionBar;
import com.constants.Constants;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.CustomGridViewAdapter.AdViewHolder;
import com.gaana.application.GaanaApplication;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.view.CustomGridView;
import com.gaana.view.CustomGridView.OnGetViewCallback;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.DiscoverItemView;
import com.gaana.view.item.DiscoverItemView.DiscoverGridHolder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.ADSTATUS;
import com.managers.ColombiaAdViewManager.b;
import com.managers.ColombiaAdViewManager.c;
import com.managers.URLManager;
import com.managers.an;
import com.managers.ap;
import com.managers.e;
import com.managers.f;
import com.managers.f.a;
import com.til.colombia.android.service.ColombiaNativeVideoAdView;
import com.utilities.Util;

public class GridActivityFragment extends BaseGaanaFragment implements b, c {
    String a = "";
    ColombiaNativeVideoAdView b;
    private CustomGridView c;
    private LinearLayout d;
    private View e = null;
    private ViewGroup f;
    private ViewGroup g;
    private PublisherAdView h;
    private ADSTATUS i;
    private String j;
    private String k = "";
    private String l;
    private String m;
    private URLManager n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t = null;
    private String u = null;
    private int v = 0;
    private int w = 0;
    private int x = -1;
    private int y = -1;
    private String z = "";

    public String a() {
        return this.o;
    }

    public String b() {
        return this.p;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.e == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.e = setContentView(R.layout.activity_main, viewGroup);
            this.a = getArguments().getString("EXTRA_ACTIONBAR_TITLE");
            this.j = getArguments().getString("EXTRA_GA_TITLE");
            this.m = getArguments().getString("EXTRA_OBJ_ID");
            this.k = getArguments().getString("extra_dynamic_view_type_see_all", "");
            this.h = new PublisherAdView(this.mContext);
            b(bundle);
            setActionBar(this.e, new GenericBackActionBar(this.mContext, true, this.a));
        }
        if (TextUtils.isEmpty(this.j)) {
            this.j = Constants.a(this.a);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.j);
        stringBuilder.append("Screen");
        String replace = stringBuilder.toString().replace(" ", "");
        setGAScreenName(replace, replace);
        ((GaanaActivity) this.mContext).title = this.j;
        ((GaanaActivity) this.mContext).hideThemeBackground(false);
        g();
        return this.e;
    }

    public String c() {
        return this.u;
    }

    public String d() {
        return this.s;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onDestroyView() {
        if (this.h != null) {
            this.h.destroy();
        }
        if (this.e.getParent() != null) {
            ((ViewGroup) this.e.getParent()).removeView(this.e);
        }
        super.onDestroyView();
    }

    public void onResume() {
        super.onResume();
        ColombiaAdViewManager.a().a((c) this);
        if (this.h != null) {
            this.h.resume();
        }
        updateView();
        refreshListView();
    }

    public void onPause() {
        ColombiaAdViewManager.a().a(null);
        if (this.h != null) {
            this.h.pause();
        }
        super.onPause();
    }

    public void refreshListView() {
        super.refreshListView();
        if (this.c != null && this.c.getPagerAdapter() != null) {
            this.c.getPagerAdapter().notifyDataSetChanged();
        }
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        super.refreshListView(businessObject, z);
        if (this.c != null) {
            this.c.updateGridContent();
        }
        if (this.f == null) {
            this.f = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
        }
    }

    private void b(Bundle bundle) {
        this.n = a(bundle);
        this.n.c(Boolean.valueOf(false));
        a(this.n);
    }

    public URLManager a(Bundle bundle) {
        URLManager uRLManager = (URLManager) (bundle != null ? bundle : getArguments()).getParcelable("EXTRA_URL_MANAGER");
        String k = uRLManager.k();
        if (bundle != null && k.contains("limit")) {
            k = Util.a(k, 0, 20);
        }
        if (!(getArguments() == null || !getArguments().getBoolean("EXTRA_SHOW_LOADMORE") || k.contains("limit"))) {
            StringBuilder stringBuilder;
            if (k.contains("?")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(k);
                stringBuilder.append("&limit=0,20");
                k = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(k);
                stringBuilder.append("?limit=0,20");
                k = stringBuilder.toString();
            }
        }
        uRLManager.a(k);
        return uRLManager;
    }

    public String e() {
        return this.t;
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        bundle.putParcelable("EXTRA_URL_MANAGER", this.n);
        bundle.putString("EXTRA_VIEW_ALL_BANNER_AD_IMG", this.t);
        bundle.putString("EXTRA_BRAND_CTN_TRACKER", this.q);
        bundle.putString("EXTRA_BRAND_DFP_TRACKER", this.r);
        super.onSaveInstanceState(bundle);
    }

    private boolean f() {
        return this.i != ADSTATUS.LOADING;
    }

    private void g() {
        if (TextUtils.isEmpty(this.m)) {
            GaanaApplication.getInstance().setGADParameter("");
        } else {
            GaanaApplication.getInstance().setGADParameter(this.m);
        }
        LinearLayout linearLayout = (LinearLayout) this.e.findViewById(R.id.adSlot);
        ColombiaAdViewManager.a().e();
        String playoutSectionName = GaanaApplication.getInstance().getPlayoutSectionName();
        if (!TextUtils.isEmpty(playoutSectionName) && PLAYOUT_SECTION_TYPE.MADE_FOR_YOU.name().equalsIgnoreCase(playoutSectionName)) {
            playoutSectionName = "MADE_FOR_YOU_SEE_ALL";
        }
        String str = playoutSectionName;
        e.a();
        if (e.X == 0) {
            ColombiaAdViewManager.a().a(this.mContext, linearLayout, e.A, str, new AdsUJData[0]);
        }
    }

    private void a(URLManager uRLManager) {
        this.d = (LinearLayout) this.e.findViewById(R.id.llParentListing);
        this.d.removeAllViews();
        Bundle arguments = getArguments();
        String str = "";
        if (arguments != null) {
            boolean z = arguments.getBoolean("EXTRA_SHOW_LOADMORE");
            if (arguments.getString("EXTRA_GASECTION_NAME") != null) {
                str = arguments.getString("EXTRA_GASECTION_NAME");
            }
            if (arguments.getString("EXTRA_GRID_SEE_ALL_AD_CODE") != null) {
                this.o = arguments.getString("EXTRA_GRID_SEE_ALL_AD_CODE");
            }
            if (arguments.getString("SEE_ALL_BANNER_AD_CODE") != null) {
                this.p = arguments.getString("SEE_ALL_BANNER_AD_CODE");
            }
            if (arguments.getString("EXTRA_SOURCE_NAME") != null) {
                this.s = arguments.getString("EXTRA_SOURCE_NAME");
            }
            if (arguments.getString("SEE_ALL_VIDEO_AD_CODE") != null) {
                this.l = arguments.getString("SEE_ALL_VIDEO_AD_CODE");
            }
            if (arguments.getString("EXTRA_DYNAMIC_SECTION_UID") != null) {
                this.u = arguments.getString("EXTRA_DYNAMIC_SECTION_UID");
            }
            if (arguments.getString("EXTRA_VIEW_ALL_BANNER_AD_IMG") != null) {
                this.t = arguments.getString("EXTRA_VIEW_ALL_BANNER_AD_IMG");
            }
            if (arguments.getString("EXTRA_BRAND_CTN_TRACKER") != null) {
                this.q = arguments.getString("EXTRA_BRAND_CTN_TRACKER");
            }
            if (arguments.getString("EXTRA_BRAND_DFP_TRACKER") != null) {
                this.r = arguments.getString("EXTRA_BRAND_DFP_TRACKER");
            }
            if (ap.a().b(this.mContext) && f()) {
                if (this.h == null) {
                    this.h = new PublisherAdView(this.mContext);
                }
                if (this.f == null) {
                    this.f = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
                }
                this.d.addView(this.f);
            }
            this.c = new CustomGridView(this.mContext, this);
            this.c.setViewTypeName(this.k);
            this.c.setOnAdRefreshListener(this);
            this.c.setNumColumns(2);
            this.c.getmGridView().setOnScrollListener(new OnScrollListener() {
                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (i == 0 && GridActivityFragment.this.v > GridActivityFragment.this.w) {
                        GridActivityFragment.this.x = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() + 1;
                        GridActivityFragment.this.y = recyclerView.getAdapter().getItemCount();
                        an.a().c("scroll", AvidJSONUtil.KEY_Y, "", GridActivityFragment.this.z, "", "", String.valueOf(GridActivityFragment.this.y), String.valueOf(GridActivityFragment.this.x));
                        GridActivityFragment.this.w = GridActivityFragment.this.v;
                    }
                }

                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    GridActivityFragment.this.v = GridActivityFragment.this.v + i2;
                }
            });
            this.c.setViewClassName(DiscoverItemView.class.getName());
            this.d.addView(this.c.getPopulatedView());
            final String string = arguments.getString("EXTRA_VIEW_TYPE_SEE_ALL");
            this.c.seOnGetViewCallback(new OnGetViewCallback() {
                public View onGetViewCalled(ViewHolder viewHolder, View view, BusinessObject businessObject, ViewGroup viewGroup, int i) {
                    if (viewHolder instanceof AdViewHolder) {
                        if (GridActivityFragment.this.g != null) {
                            GridActivityFragment.this.g.removeAllViews();
                        }
                        GridActivityFragment.this.g = (LinearLayout) viewHolder.itemView;
                        GridActivityFragment.this.g.addView(GridActivityFragment.this.b);
                        GridActivityFragment.this.g.setVisibility(0);
                        return viewHolder.itemView;
                    } else if (viewHolder instanceof ItemAdViewHolder) {
                        return viewHolder.itemView;
                    } else {
                        if ((viewHolder instanceof DiscoverGridHolder) && !TextUtils.isEmpty(GridActivityFragment.this.e()) && i == 0) {
                            CrossFadeImageView crossFadeImageView = ((DiscoverGridHolder) viewHolder).crossFadeImageView;
                            crossFadeImageView.bindImage(GridActivityFragment.this.e(), ScaleType.FIT_XY);
                            crossFadeImageView.setVisibility(0);
                            GridActivityFragment.this.a((ViewGroup) GridActivityFragment.this.e);
                            return viewHolder.itemView;
                        }
                        ((Item) businessObject).getEntityType();
                        DiscoverItemView discoverItemView = new DiscoverItemView(GridActivityFragment.this.mContext, GridActivityFragment.this);
                        discoverItemView.setGASectionName(str);
                        discoverItemView.setItemPosition(i);
                        return discoverItemView.getViewAllGriditemView(viewHolder, businessObject, viewGroup, i, string);
                    }
                }
            });
            uRLManager.a(Boolean.valueOf(z));
            this.c.updateGridView(uRLManager);
            h();
        }
    }

    private void h() {
        if (ap.a().b(this.mContext) && !TextUtils.isEmpty(this.l)) {
            f.v().a(new a() {
                public void adPopulated(ColombiaNativeVideoAdView colombiaNativeVideoAdView) {
                    GridActivityFragment.this.b = colombiaNativeVideoAdView;
                    GridActivityFragment.this.c.setIsVideoAdLoaded(true);
                    if (GridActivityFragment.this.c.getPagerAdapter() != null) {
                        GridActivityFragment.this.c.getPagerAdapter().notifyDataSetChanged();
                    }
                }
            });
            f.v().a(Long.parseLong(this.l), this.g, this.mContext);
        }
    }

    private void a(ViewGroup viewGroup) {
        if (!TextUtils.isEmpty(this.q)) {
            e.a().a((View) viewGroup, this.mContext, Long.parseLong(this.q));
        } else if (!TextUtils.isEmpty(this.r)) {
            ColombiaAdViewManager.a().a((View) viewGroup, this.mContext, this.r);
        }
    }

    public void onAdRefresh() {
        super.onAdRefresh();
        if (ap.a().b(this.mContext) && f()) {
            h();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        ColombiaAdViewManager.a().a(this.h);
        if (this.b != null) {
            this.b.clear();
            this.b.removeAllViews();
            f.v().H();
        }
    }

    public void a(ADSTATUS adstatus) {
        this.i = adstatus;
    }

    public void b(ADSTATUS adstatus) {
        this.i = adstatus;
    }

    public void c(ADSTATUS adstatus) {
        this.i = adstatus;
    }

    public void d(ADSTATUS adstatus) {
        this.i = adstatus;
    }

    public void l() {
        if (ap.a().b(this.mContext) && this.f == null) {
            this.f = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
        }
    }

    public void refreshDataandAds() {
        if (!(this.f == null || this.f.findViewById(R.id.llNativeAdSlot) == null)) {
            this.f.findViewById(R.id.llNativeAdSlot).setVisibility(8);
        }
        this.c.refreshList();
    }
}
