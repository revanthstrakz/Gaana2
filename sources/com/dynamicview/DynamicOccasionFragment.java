package com.dynamicview;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.actionbar.DetailsMaterialActionBar;
import com.collapsible_header.ObservableRecyclerView;
import com.collapsible_header.ScrollState;
import com.collapsible_header.d;
import com.collapsible_header.i;
import com.constants.Constants;
import com.facebook.share.widget.ShareDialog;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.adapter.CustomListAdapter.IAddListItemView;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukeSeeAllFragment;
import com.gaana.login.LoginManager;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.models.FavoriteOccasions.FavoriteOccasion;
import com.gaana.models.Item;
import com.gaana.view.BaseItemView;
import com.gaana.view.GaanaListView.OnDataLoadedListener;
import com.gaana.view.item.OccasionDynamicScrollView;
import com.gaana.view.item.PopupShareitemView;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaManager;
import com.managers.PlayerManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aa;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.ap.a;
import com.managers.c;
import com.managers.e;
import com.managers.n;
import com.managers.u;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.f;
import com.player_framework.m;
import com.player_framework.o;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.List;

public class DynamicOccasionFragment extends BaseGaanaFragment implements OnRefreshListener, d, IAddListItemView, OnDataLoadedListener {
    int a = 0;
    private boolean b = false;
    private boolean c = false;
    private GaanaApplication d;
    private boolean e = false;
    private ObservableRecyclerView f = null;
    private int g;
    private SwipeRefreshLayout h;
    private CustomListAdapter i = null;
    private DisplayMetrics j;
    private View k = null;
    private List<BaseItemView> l = null;
    private DetailsMaterialActionBar m;
    private Toolbar n;
    private View o;
    private View p;
    private String q = "";
    private int r = 0;
    private View s = null;
    private c t;
    private int u = 0;
    private int v = 0;
    private int w = -1;
    private int x = -1;

    public int getItemViewType(int i) {
        return i;
    }

    public void onDataLoaded(BusinessObject businessObject, BusinessObjectType businessObjectType) {
    }

    public void onDownMotionEvent() {
    }

    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    public void showHideEmtpyView(boolean z) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.j = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.j);
        if (this.k == null || this.loginStatus != this.d.getCurrentUser().getLoginStatus()) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.k = setContentView(R.layout.fragment_music_year, viewGroup);
            this.b = true;
            this.d = GaanaApplication.getInstance();
            this.mContext = getActivity();
            this.n = (Toolbar) this.k.findViewById(R.id.main_toolbar);
            this.p = this.k.findViewById(R.id.main_toolbar_bg);
            this.m = new DetailsMaterialActionBar(this.mContext);
            this.m.setParams(this, new BusinessObject());
            if (!TextUtils.isEmpty(c.a().i())) {
                this.m.getTitleTextView().setText(c.a().i());
            } else if (!(getArguments() == null || TextUtils.isEmpty(getArguments().getString(JukeSeeAllFragment.EXTRA_ARG_TITLE)))) {
                this.m.getTitleTextView().setText(getArguments().getString(JukeSeeAllFragment.EXTRA_ARG_TITLE));
            }
            this.m.showContextMenu(false);
            this.n.addView(this.m);
            this.n.setContentInsetsAbsolute(0, 0);
            g();
            this.m.setToolbar(this.n);
            if (Constants.l) {
                this.m.getTitleTextView().setTextColor(ViewCompat.MEASURED_STATE_MASK);
                ((ImageView) this.m.findViewById(R.id.menu_icon)).setImageResource(R.drawable.vector_ab_back);
                this.n.getMenu().findItem(R.id.searchview_actionbar).setIcon(R.drawable.vector_icon_search_75);
            }
            h();
            this.f = (ObservableRecyclerView) this.k.findViewById(R.id.recycler_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, 1, false);
            linearLayoutManager.setAutoMeasureEnabled(false);
            linearLayoutManager.setItemPrefetchEnabled(false);
            this.f.setHasFixedSize(true);
            this.f.setLayoutManager(linearLayoutManager);
            this.f.setScrollViewCallbacks(this);
            this.i = new CustomListAdapter(this.mContext, null);
            this.h = (SwipeRefreshLayout) this.k.findViewById(R.id.swipe_refresh_layout);
            this.h.setOnRefreshListener(this);
            this.l = c.a().a(this.mContext, this);
            f();
            if ((c.a().g() != null ? c.a().g().d() : 0) == 1) {
                GaanaApplication.getInstance().setCurrentSponsoredOccassion(c.a().b());
            }
        } else if (!(this.f == null || this.f.getAdapter() == null)) {
            this.f.getAdapter().notifyDataSetChanged();
        }
        m();
        this.f.addOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                DynamicOccasionFragment.this.t.a(i);
                DynamicOccasionFragment.this.a = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (i == 0 && DynamicOccasionFragment.this.u > DynamicOccasionFragment.this.v) {
                    DynamicOccasionFragment.this.w = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() + 1;
                    DynamicOccasionFragment.this.x = recyclerView.getAdapter().getItemCount();
                    String str = null;
                    BaseItemView baseItemView = DynamicOccasionFragment.this.w < DynamicOccasionFragment.this.l.size() ? (BaseItemView) DynamicOccasionFragment.this.l.get(DynamicOccasionFragment.this.w) : null;
                    if (baseItemView != null) {
                        if (baseItemView.getDynamicView() != null) {
                            str = baseItemView.getDynamicView().p();
                        }
                        String str2 = str;
                        if (baseItemView instanceof OccasionDynamicScrollView) {
                            ArrayList arrListBusinessObj = ((OccasionDynamicScrollView) baseItemView).getArrListBusinessObj();
                            String language = (arrListBusinessObj == null || arrListBusinessObj.get(0) == null) ? "" : ((Item) arrListBusinessObj.get(0)).getLanguage();
                            an.a().c("scroll", AvidJSONUtil.KEY_Y, "", str2, "", language, String.valueOf(DynamicOccasionFragment.this.x), String.valueOf(DynamicOccasionFragment.this.w));
                            DynamicOccasionFragment.this.v = DynamicOccasionFragment.this.u;
                        }
                    }
                }
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                DynamicOccasionFragment.this.u = DynamicOccasionFragment.this.u + i2;
            }
        });
        this.q = c.a().b();
        int d = c.a().d();
        if (TextUtils.isEmpty(this.q) || !this.q.equalsIgnoreCase("gaanaplayback2017")) {
            this.n.getMenu().findItem(R.id.shareview_actionbar).setVisible(false);
            this.n.getMenu().findItem(R.id.favorite_actionbar).setVisible(false);
        } else {
            this.n.getMenu().findItem(R.id.shareview_actionbar).setVisible(true);
            this.n.getMenu().findItem(R.id.shareview_actionbar).setOnMenuItemClickListener(new OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem menuItem) {
                    DynamicOccasionFragment.this.a(true);
                    return false;
                }
            });
            if (Constants.l) {
                this.n.getMenu().findItem(R.id.shareview_actionbar).setIcon(R.drawable.vector_more_option_share);
            }
            n();
        }
        if (d == 1) {
            this.n.getMenu().findItem(R.id.shareview_actionbar).setVisible(true);
            this.n.getMenu().findItem(R.id.shareview_actionbar).setOnMenuItemClickListener(new OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem menuItem) {
                    DynamicOccasionFragment.this.e();
                    return false;
                }
            });
            if (Constants.l) {
                this.n.getMenu().findItem(R.id.shareview_actionbar).setIcon(R.drawable.vector_more_option_share);
            }
            n();
        }
        this.d.setNetworkExtrasBundle();
        String h = c.a().h();
        if (!TextUtils.isEmpty(h)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("OP_");
            stringBuilder.append(h);
            stringBuilder.append("_Screen");
            setGAScreenName(h, stringBuilder.toString());
        }
        aa.a().b(this.b);
        ((GaanaActivity) this.mContext).hideThemeBackground(false);
        GaanaApplication.getInstance().setGADParameter(c.a().f());
        if (c.a().g() == null) {
            j();
        } else if (!c.a().g().k()) {
            j();
        }
        return this.k;
    }

    public boolean a() {
        return this.a <= 3;
    }

    public boolean b() {
        boolean z = false;
        if (c.a().g() == null) {
            return false;
        }
        if (c.a().g().d() == 1) {
            z = true;
        }
        return z;
    }

    private void e() {
        String str = "";
        if (c.a().e() != null) {
            str = c.a().e();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("\n");
        stringBuilder.append("https://gaana.com/occasion/");
        stringBuilder.append(c.a().f());
        str = stringBuilder.toString();
        if (str != null && !TextUtils.isEmpty(str)) {
            new PopupShareitemView(this.mContext, str).shareOnOther();
            an.a().a("click", "ac", "", "Occasion Detail", this.q, ShareDialog.WEB_SHARE_DIALOG, "", "");
        }
    }

    private void f() {
        this.g = (int) getResources().getDimension(R.dimen.img_occasion_header_height);
    }

    private void g() {
        hideHomeActionBar();
        this.n.getMenu().clear();
        this.n.inflateMenu(R.menu.cast_menu_generic_back_with_shareoption);
    }

    private void h() {
        this.o = this.k.findViewById(R.id.fragment_music_year_shatter);
        if (VERSION.SDK_INT >= 21) {
            this.n.setElevation((float) Util.b(20));
            this.p.setElevation((float) Util.b(20));
        }
    }

    private void a(int i) {
        TextView textView = (TextView) this.k.findViewById(R.id.fragment_music_year_shatter_Off);
        TextView textView2 = (TextView) this.k.findViewById(R.id.fragment_music_year_shatter_On);
        textView.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
        textView2.setTypeface(Typeface.createFromAsset(this.mContext.getAssets(), "fonts/Roboto-Bold.ttf"));
        if (i == 1) {
            textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.white));
            textView.setBackgroundColor(0);
            textView2.setBackgroundResource(R.drawable.rounded_nokia_btn_blue);
        } else {
            textView.setBackgroundResource(R.drawable.rounded_nokia_btn_white);
            textView.setTextColor(ContextCompat.getColor(this.mContext, R.color.nokia_blue_border));
            textView2.setBackgroundColor(0);
        }
        if (VERSION.SDK_INT >= 21 && !Constants.l) {
            if (i == 1) {
                textView2.setElevation((float) Util.b(20));
                textView.setElevation(0.0f);
                return;
            }
            textView.setElevation((float) Util.b(20));
            textView2.setElevation(0.0f);
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    public void c() {
        if (!this.e) {
            ((GaanaActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading));
        }
        i();
    }

    private void i() {
        for (BaseItemView isToBeRefreshed : this.l) {
            isToBeRefreshed.setIsToBeRefreshed(this.e);
        }
        ((GaanaActivity) this.mContext).hideProgressDialog();
        if (!this.e) {
            ((BaseActivity) this.mContext).resetLoginStatus();
            this.i.setParamaters(a(this.mContext, (BaseGaanaFragment) this), this);
            this.f.setAdapter(this.i);
        }
        if (c.a().k()) {
            this.o.setVisibility(0);
            a(Constants.ed);
            this.o.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (Constants.ed == 1) {
                        Constants.ed = 0;
                        DynamicOccasionFragment.this.a(Constants.ed);
                    } else {
                        Constants.ed = 1;
                        DynamicOccasionFragment.this.a(Constants.ed);
                    }
                    DynamicOccasionFragment.this.k();
                    com.services.d.a().a("PREFERENCE_NOKIA_MODE", Constants.ed, false);
                }
            });
            return;
        }
        this.o.setVisibility(8);
    }

    private void j() {
        ColombiaAdViewManager.a().a(this.mContext, (LinearLayout) this.k.findViewById(R.id.bottomAdSlot), e.A, Constants.ea, new AdsUJData[0]);
    }

    private void k() {
        if (c.a().i() != null) {
            u a = u.a();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("OP_");
            stringBuilder.append(c.a().i());
            stringBuilder.append("_Screen");
            a.a(stringBuilder.toString(), "toggle click", Constants.ed == 1 ? "on" : "off");
        }
        PlayerTrack i = PlayerManager.a(this.mContext).i();
        if (i != null && i.e() == SOURCE_TYPE.ONE_TOUCH_RADIO.ordinal()) {
            o.a("OCCASION", new m() {
                public void onAdEventUpdate(f fVar, AdEvent adEvent) {
                }

                public void onBufferingUpdate(f fVar, int i) {
                }

                public void onCompletion(f fVar) {
                }

                public void onError(f fVar, int i, int i2) {
                }

                public void onInfo(f fVar, int i, int i2) {
                }

                public void onPrepared(f fVar) {
                    if (DynamicOccasionFragment.this.r > 0) {
                        o.a(DynamicOccasionFragment.this.getContext(), DynamicOccasionFragment.this.r);
                        DynamicOccasionFragment.this.r = 0;
                        o.d("OCCASION");
                    }
                }
            });
            try {
                this.r = GaanaMusicService.s().v();
            } catch (Exception unused) {
            }
            o.b(this.mContext, 1);
        }
    }

    public void onResume() {
        if (((BaseActivity) this.mContext).hasLoginChanged().booleanValue() || this.b) {
            c();
            this.b = false;
            this.c = true;
        }
        if (!TextUtils.isEmpty(this.d.getPromorUrl())) {
            Intent intent = new Intent(this.mContext, WebViewActivity.class);
            intent.putExtra("EXTRA_WEBVIEW_URL", this.d.getPromorUrl());
            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
            intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
            intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
            this.mContext.startActivity(intent);
            this.d.setPromoUrl(null);
        }
        updateView();
        this.t.a(0);
        super.onResume();
    }

    public void onPause() {
        super.onPause();
        this.t.b();
    }

    public void setGAScreenName(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OP_");
        stringBuilder.append(str);
        sendGAScreenName(stringBuilder.toString(), str2);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.t != null) {
            this.t.a();
        }
    }

    public void onDestroyView() {
        if (this.k.getParent() != null) {
            ((ViewGroup) this.k.getParent()).removeView(this.k);
        }
        super.onDestroyView();
        if (this.l != null) {
            for (BaseItemView baseItemView : this.l) {
                if (baseItemView != null) {
                    baseItemView.setFirstCall(true);
                }
            }
        }
        c.a().c();
    }

    private void l() {
        if (this.h != null) {
            this.h.setRefreshing(false);
        }
    }

    public View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        return a(this.mContext, (BaseGaanaFragment) this, i).getPopulatedView(i, viewHolder, (ViewGroup) viewHolder.itemView.getParent());
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        return a(this.mContext, (BaseGaanaFragment) this, i).onCreateViewHolder(viewGroup, i);
    }

    public void onRefresh() {
        if (this.f != null && this.f.getAdapter() != null) {
            this.e = true;
            if (ap.a().b(this.mContext)) {
                ColombiaManager.b().c();
            }
            c();
            l();
            this.e = false;
        }
    }

    private BaseItemView a(Context context, BaseGaanaFragment baseGaanaFragment, int i) {
        if (this.l == null) {
            this.l = c.a().a(context, baseGaanaFragment);
        }
        return (BaseItemView) this.l.get(i);
    }

    private int a(Context context, BaseGaanaFragment baseGaanaFragment) {
        if (this.l == null) {
            this.l = c.a().a(context, baseGaanaFragment);
        }
        return this.l.size();
    }

    public void notifyItemChanged(int i) {
        if (this.i != null) {
            this.i.notifyItemChanged(i);
        }
    }

    public String getPageName() {
        return PAGE_SORCE_NAME.HOME.name();
    }

    public void onScrollChanged(int i, boolean z, boolean z2) {
        float d = (float) (this.g - d());
        i.g(this.o, com.collapsible_header.f.a((float) (-i), (float) (d() - this.g), 0.0f));
        float f = (float) i;
        i.a(this.p, com.collapsible_header.f.a(f / (d / 2.0f), 0.0f, 1.0f));
        i.a(this.m.getTitleTextView(), com.collapsible_header.f.a(f / d, 0.0f, 1.0f));
        if (this.c) {
            this.c = false;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    i.a(DynamicOccasionFragment.this.p, 0.0f);
                    i.a(DynamicOccasionFragment.this.m.getTitleTextView(), 0.0f);
                }
            }, 200);
        }
    }

    /* Access modifiers changed, original: protected */
    public int d() {
        int[] iArr = new int[]{R.attr.actionBarSize};
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new TypedValue().data, iArr);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public void a(boolean z) {
        if (TextUtils.isEmpty(this.q)) {
            aj.a().a(this.mContext, getString(R.string.err_retry));
            return;
        }
        u.a().a("YIM_Video", "YIM_Page_Share_OP", z ? "FROM_YIM_Page_Menu" : "FROM_YIM_Share_Card");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://gaana.com/occasion/");
        stringBuilder.append(this.q);
        new PopupShareitemView(this.mContext, stringBuilder.toString()).shareOnOther();
        an.a().a("click", "ac", "", "Occasion Detail", this.q, ShareDialog.WEB_SHARE_DIALOG, "", "");
    }

    private void m() {
        this.t = new c();
        this.t.a(this.mContext, this.f, true, false, false, 80.0f);
    }

    private void n() {
        d g = c.a().g();
        final BusinessObject favoriteOccasion = new FavoriteOccasion();
        favoriteOccasion.setEntityId(String.valueOf(g.g()));
        favoriteOccasion.setBusinessObjId(String.valueOf(g.g()));
        favoriteOccasion.setEntityType(com.constants.c.c.i);
        favoriteOccasion.setName(g.b());
        favoriteOccasion.setArtwork(g.j());
        favoriteOccasion.setSeoKey(g.h());
        favoriteOccasion.setUserFavorite(a(String.valueOf(g.i())));
        if (this.n != null) {
            Menu menu = this.n.getMenu();
            if (menu != null) {
                MenuItem findItem = menu.findItem(R.id.favorite_actionbar);
                if (findItem != null) {
                    final ImageView imageView = (ImageView) findItem.getActionView();
                    findItem.setVisible(true);
                    imageView.setVisibility(0);
                    imageView.setPadding(this.mContext.getResources().getDimensionPixelSize(R.dimen.dp12), 0, this.mContext.getResources().getDimensionPixelSize(R.dimen.dp12), 0);
                    imageView.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            if (n.e(favoriteOccasion)) {
                                aj.a().a(DynamicOccasionFragment.this.mContext, DynamicOccasionFragment.this.mContext.getString(R.string.please_wait_while_previous_favorite_action_is_being_performed));
                                return;
                            }
                            if (n.a().a(favoriteOccasion)) {
                                favoriteOccasion.setFavorite(Boolean.valueOf(false));
                                ((BaseActivity) DynamicOccasionFragment.this.mContext).addRemoveFav(favoriteOccasion, Boolean.valueOf(true), false, new a() {
                                    public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
                                        if (z && favoriteOccasion != null) {
                                            DynamicOccasionFragment.this.a(imageView, false);
                                            n.a().b("Occasion Detail", businessObject.getBusinessObjId());
                                            an.a().a("click", "ac", "", "Occasion Detail", favoriteOccasion.getEntityId(), "unfav", "", "");
                                        }
                                    }
                                });
                            } else {
                                favoriteOccasion.setFavorite(Boolean.valueOf(true));
                                ap.a().a(DynamicOccasionFragment.this.mContext, favoriteOccasion, new a() {
                                    public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
                                        if (z && favoriteOccasion != null) {
                                            Animation loadAnimation = AnimationUtils.loadAnimation(DynamicOccasionFragment.this.mContext, R.anim.favorite_tap_animation);
                                            loadAnimation.setInterpolator(new com.a.a(0.2d, 20.0d));
                                            imageView.startAnimation(loadAnimation);
                                            DynamicOccasionFragment.this.a(imageView, true);
                                            n.a().a("Occasion Detail", businessObject.getBusinessObjId());
                                            an.a().a("click", "ac", "", "Occasion Detail", favoriteOccasion.getEntityId(), "fav", "", "");
                                        }
                                    }
                                });
                            }
                        }
                    });
                    a(imageView, n.a().a(favoriteOccasion));
                }
            }
        }
    }

    private void a(ImageView imageView, boolean z) {
        TypedArray obtainStyledAttributes;
        if (z) {
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(51, -1)));
            obtainStyledAttributes.recycle();
            return;
        }
        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(49, -1)));
        obtainStyledAttributes.recycle();
    }

    private boolean a(String str) {
        return str != null && str.compareTo("1") == 0;
    }
}
