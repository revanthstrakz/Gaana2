package com.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbar.DetailsMaterialActionBar;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.collapsible_header.ObservableRecyclerView;
import com.collapsible_header.ScrollState;
import com.collapsible_header.d;
import com.collapsible_header.f;
import com.collapsible_header.i;
import com.constants.Constants;
import com.constants.c;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.adapter.CustomListAdapter.IAddListItemView;
import com.gaana.analytics.AppsFlyer;
import com.gaana.application.GaanaApplication;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.models.Radios.Radio;
import com.gaana.view.UpgradeHomeView;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.ItemNormalViewHolder;
import com.gaana.view.item.BaseItemView.TwoGridItemHolder;
import com.google.android.gms.appindexing.AppIndex;
import com.library.controls.CrossFadeImageView;
import com.library.controls.CrossFadeImageView.ImageLoadingCompeletedListener;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap;
import com.managers.ap.a;
import com.managers.e;
import com.managers.n;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RadioDetailsMaterialListing extends BaseGaanaFragment implements OnRefreshListener, OnClickListener, d, IAddListItemView, a {
    private View A;
    private DisplayMetrics B;
    private long C;
    private String D = "";
    private String E = "";
    private Menu F;
    private MenuItem G;
    private View H;
    private String I = "0";
    private LinearLayout J;
    String a = "";
    private View b = null;
    private ListingComponents c;
    private BusinessObject d;
    private ObservableRecyclerView e;
    private CrossFadeImageView f;
    private View g;
    private CustomListAdapter h;
    private BaseItemView i;
    private SwipeRefreshLayout j;
    private boolean k = false;
    private int l = 0;
    private FloatingActionButton m;
    private DetailsMaterialActionBar n;
    private Toolbar o;
    private ProgressBar p;
    private View q = null;
    private int r;
    private View s;
    private View t;
    private View u;
    private TextView v;
    private ArrayList<BusinessObject> w = new ArrayList();
    private int x = 0;
    private UpgradeHomeView y = null;
    private LinearLayout z;

    public void onDownMotionEvent() {
    }

    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    public void showHideEmtpyView(boolean z) {
    }

    public static Bundle a(BusinessObject businessObject, String str) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("BUSINESS_OBJECT", businessObject);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
        }
        return bundle;
    }

    private boolean a(Bundle bundle, ViewGroup viewGroup) {
        if (bundle != null) {
            this.d = (BusinessObject) bundle.getSerializable("BUSINESS_OBJECT");
            if (this.d != null) {
                if (this.d instanceof Radio) {
                    this.c = Constants.a((Radio) this.d);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(Util.c(this.d.getBusinessObjType()));
                    stringBuilder.append(this.d.getBusinessObjId());
                    AppsFlyer.getInstance().reportViewContent(this.d.getEnglishName(), "Radio", stringBuilder.toString());
                }
                this.c.b(this.d.getName());
                this.c.a(this.d);
                this.mAppState.setListingComponents(this.c);
                this.z = new LinearLayout(getActivity());
                this.z.setLayoutParams(new LayoutParams(-1, -2));
                this.z.setGravity(17);
                this.z.setPadding(0, 50, 0, 0);
                this.z.setBackgroundColor(getResources().getColor(R.color.black));
                a((ListingButton) this.c.c().get(0));
                a(viewGroup);
                return true;
            }
        }
        ((GaanaActivity) this.mContext).popBackStack();
        return false;
    }

    public void onStart() {
        a();
        super.onStart();
    }

    public void onStop() {
        b();
        super.onStop();
    }

    public void a() {
        if (!this.mClient.isConnected()) {
            this.mClient.connect();
        }
        if (!TextUtils.isEmpty(this.D)) {
            List arrayList = new ArrayList();
            AppIndex.AppIndexApi.view(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.D), this.TITLE, Uri.parse(this.E), arrayList);
        }
    }

    public void b() {
        if (!TextUtils.isEmpty(this.D)) {
            AppIndex.AppIndexApi.viewEnd(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.D));
            this.mClient.disconnect();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.d != null) {
            this.d.setArrListBusinessObj(null);
            bundle.putSerializable("BUSINESS_OBJECT", this.d);
        }
    }

    public final void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.B = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.B);
        if (this.b == null || this.loginStatus != this.mAppState.getCurrentUser().getLoginStatus()) {
            boolean a;
            this.C = Calendar.getInstance().getTimeInMillis();
            super.onCreateView(layoutInflater, viewGroup, bundle);
            if (bundle == null) {
                a = a(getArguments(), viewGroup);
            } else {
                a = a(bundle, viewGroup);
            }
            if (a) {
                this.p.setVisibility(8);
                k();
                if (this.d != null) {
                    h();
                }
            } else {
                ((GaanaActivity) this.mContext).popBackStack();
            }
        } else {
            this.mAppState.setListingComponents(this.c);
        }
        ((GaanaActivity) this.mContext).hideThemeBackground(false);
        if (this.d != null) {
            this.mAppState.setGADParameter(this.d.getBusinessObjId());
            this.TITLE = this.d.getEnglishName();
            this.currentUJPage = "RADIODETAILS";
            String str = "";
            if (this.d instanceof Radio) {
                Radio radio = (Radio) this.d;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/radio/");
                stringBuilder.append(radio.getSeokey());
                this.E = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/radio/");
                stringBuilder.append(radio.getSeokey());
                this.D = stringBuilder.toString();
                StringBuilder stringBuilder2;
                if (radio.getType().equalsIgnoreCase(c.d.c)) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Radio Mirchi Detail Screen:");
                    stringBuilder2.append(this.TITLE);
                    str = stringBuilder2.toString();
                } else {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Gaana Radio Detail Screen:");
                    stringBuilder2.append(this.TITLE);
                    str = stringBuilder2.toString();
                }
            }
            setGAScreenName(str, str);
        }
        f();
        return this.b;
    }

    public void onResume() {
        super.onResume();
        ((GaanaActivity) this.mContext).setFragment(this);
    }

    private void a(ViewGroup viewGroup) {
        this.b = setContentView(R.layout.fragment_radio_details_material_listing, viewGroup);
        this.r = this.mContext.getResources().getDimensionPixelSize(R.dimen.detail_page_artwork);
        this.e = (ObservableRecyclerView) this.b.findViewById(R.id.scroll);
        this.f = (CrossFadeImageView) this.b.findViewById(R.id.details_artwork);
        this.g = this.b.findViewById(R.id.img_background);
        this.t = this.b.findViewById(R.id.overlay);
        this.v = (TextView) this.b.findViewById(R.id.album_title);
        this.H = this.b.findViewById(R.id.radio_title_container);
        this.A = this.b.findViewById(R.id.button_padding);
        this.A.setVisibility(8);
        this.e.setScrollViewCallbacks(this);
        this.e.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.e.setHasFixedSize(false);
        this.j = (SwipeRefreshLayout) this.b.findViewById(R.id.swipe_refresh_layout);
        this.j.setOnRefreshListener(this);
        this.m = (FloatingActionButton) this.b.findViewById(R.id.shuffle_play_button);
        this.m.setVisibility(8);
        this.J = (LinearLayout) this.b.findViewById(R.id.ll_fav_parent);
        this.s = LayoutInflater.from(this.mContext).inflate(R.layout.recycler_header, null);
        this.s.setLayoutParams(new LinearLayout.LayoutParams(-1, this.r));
        this.s.post(new Runnable() {
            public void run() {
                RadioDetailsMaterialListing.this.s.getLayoutParams().height = RadioDetailsMaterialListing.this.r;
            }
        });
        this.h = new CustomListAdapter(this.mContext, this.s);
        this.h.setDFPBannerAdCode(e.K);
        if (((Radio) this.d).getType().equalsIgnoreCase("RM")) {
            this.h.setAdSectionName(Constants.dW);
        } else if (((Radio) this.d).getType().equalsIgnoreCase("RL")) {
            this.h.setAdSectionName(Constants.dX);
        }
        this.h.setParamaters(0, this);
        this.e.setAdapter(this.h);
        this.h.setShowRepetativeBannerAd(true);
        this.o = (Toolbar) this.b.findViewById(R.id.main_toolbar);
        this.u = this.b.findViewById(R.id.toolbar_dummy_view);
        this.o.setContentInsetsAbsolute(0, 0);
        g();
        this.n = new DetailsMaterialActionBar(this.mContext);
        this.o.addView(this.n);
        setmToolbar(this.o);
        this.n.setParams(this, this.d);
        ((TextView) this.n.findViewById(R.id.title)).setText("");
        ((ImageView) this.n.findViewById(R.id.menu_icon)).setImageResource(R.drawable.actionbar_back);
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.parallax_header_title_color});
        ((TextView) this.n.findViewById(R.id.title)).setTextColor(obtainStyledAttributes.getColor(0, -1));
        obtainStyledAttributes.recycle();
        this.p = (ProgressBar) this.b.findViewById(R.id.progressbar);
        this.v.setText(this.d.getName());
        this.v.setLayoutParams(new LinearLayout.LayoutParams((int) (((float) this.B.widthPixels) / 1.4f), -2));
        this.v.setTextColor(-1);
        i.g(this.t, (float) this.r);
        this.J.post(new Runnable() {
            public void run() {
                i.g(RadioDetailsMaterialListing.this.J, (float) (((int) (((float) RadioDetailsMaterialListing.this.r) - (((float) RadioDetailsMaterialListing.this.v.getHeight()) * 1.2f))) - RadioDetailsMaterialListing.this.J.getHeight()));
                i.b(RadioDetailsMaterialListing.this.J, 0.0f);
                i.c(RadioDetailsMaterialListing.this.J, 0.0f);
            }
        });
        this.v.post(new Runnable() {
            public void run() {
                i.g(RadioDetailsMaterialListing.this.v, (float) ((int) (((float) (RadioDetailsMaterialListing.this.r - RadioDetailsMaterialListing.this.v.getHeight())) - (((float) RadioDetailsMaterialListing.this.J.getHeight()) * 1.2f))));
                i.b(RadioDetailsMaterialListing.this.v, 0.0f);
                i.c(RadioDetailsMaterialListing.this.v, 0.0f);
                i.d(RadioDetailsMaterialListing.this.v, 1.2f);
                i.e(RadioDetailsMaterialListing.this.v, 1.2f);
            }
        });
        this.u.post(new Runnable() {
            public void run() {
                i.a(RadioDetailsMaterialListing.this.u, 0.0f);
            }
        });
        this.n.setToolbar(this.o);
        if (VERSION.SDK_INT >= 21) {
            this.t.setElevation((float) Util.b(20));
            this.H.setElevation((float) Util.b(20));
            this.o.setElevation((float) Util.b(20));
        }
    }

    private void f() {
        String str;
        if (this.d == null || !((Radio) this.d).getType().equalsIgnoreCase("RM")) {
            str = e.A;
        } else {
            str = e.F;
        }
        String str2 = str;
        GaanaApplication.getInstance().setGADParameter(this.d.getBusinessObjId());
        if (e.aj == 0) {
            ColombiaAdViewManager.a().a(this.mContext, (LinearLayout) this.b.findViewById(R.id.adSlot), str2, "RADIO_DETAIL_BOTTOM_BANNER", new AdsUJData[0]);
        }
    }

    private void g() {
        this.o.getMenu().clear();
        this.o.inflateMenu(R.menu.cast_menu_detail);
        ((BaseActivity) this.mContext).initializeMediaRouterButton(this.o.getMenu(), R.id.media_route_menu_item);
        this.F = this.o.getMenu();
        if (this.F != null) {
            this.G = this.F.findItem(R.id.media_route_menu_item);
        }
        c();
    }

    public void c() {
        if (this.o != null) {
            Menu menu = this.o.getMenu();
            if (menu != null) {
                ImageView imageView = (ImageView) menu.findItem(R.id.menu_favourite).getActionView();
                if (n.a().a(this.d)) {
                    TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(51, -1)));
                    obtainStyledAttributes.recycle();
                    return;
                }
                imageView.setImageResource(R.drawable.vector_ab_favorite_white);
            }
        }
    }

    private void h() {
        String artwork = ((Radio) this.d).getArtwork();
        if (artwork != null && artwork.contains("80x80")) {
            artwork = artwork.replace("80x80", "480x480");
        } else if (artwork != null && artwork.contains("175x175")) {
            artwork = artwork.replace("175x175", "480x480");
        }
        try {
            this.f.bindImage(artwork, new ImageLoadingCompeletedListener() {
                public void onImageLoadingCompeleted(Bitmap bitmap) {
                }

                public void onError() {
                    RadioDetailsMaterialListing.this.i();
                }
            }, ScaleType.CENTER_CROP);
        } catch (OutOfMemoryError unused) {
            i();
        }
    }

    private void i() {
        String artwork = ((Radio) this.d).getArtwork();
        if (artwork != null && artwork.contains("80x80")) {
            artwork = artwork.replace("80x80", "175x175");
        }
        this.f.bindImage(artwork, ScaleType.CENTER_CROP);
    }

    /* Access modifiers changed, original: protected */
    public int d() {
        int[] iArr = new int[]{R.attr.actionBarSize};
        TypedArray obtainStyledAttributes = getActivity().obtainStyledAttributes(new TypedValue().data, iArr);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    private void j() {
        if (this.d instanceof Radio) {
            this.I = ((Radio) this.d).getFavorite_count();
        }
        if (TextUtils.isEmpty(this.I)) {
            this.I = "0";
        }
        if (!TextUtils.isEmpty(this.I)) {
            StringBuilder stringBuilder;
            if (Integer.parseInt(this.I.trim()) < 2) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(Util.q(this.I));
                stringBuilder.append(" ");
                stringBuilder.append(getString(R.string.favorite));
                this.a = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(Util.q(this.I));
                stringBuilder.append(" ");
                stringBuilder.append(getString(R.string.favorites));
                this.a = stringBuilder.toString();
            }
        }
        l();
    }

    public void refreshFavoriteCount(boolean z) {
        int parseInt;
        super.refreshFavoriteCount(z);
        String favorite_count = ((Radio) this.d).getFavorite_count();
        if (TextUtils.isEmpty(favorite_count)) {
            favorite_count = "0";
        }
        if (z) {
            parseInt = Integer.parseInt(favorite_count) + 1;
        } else {
            parseInt = Integer.parseInt(favorite_count) - 1;
        }
        Radio radio = (Radio) this.d;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(parseInt);
        stringBuilder.append("");
        radio.setFavoriteCount(stringBuilder.toString());
        j();
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    private void k() {
        this.C = Calendar.getInstance().getTimeInMillis();
        ListingButton listingButton = (ListingButton) this.c.c().get(0);
        URLManager c = listingButton.c();
        c.c(Boolean.valueOf(this.k));
        if (this.d.isLocalMedia()) {
            ((BaseActivity) this.mContext).getDownloadedBusinessObject((b) this, this.d.getBusinessObjId(), c);
        } else if (!listingButton.l() || this.k) {
            com.i.i.a().a(c, toString(), (b) this, (com.android.volley.i.a) this);
        } else {
            ((BaseActivity) this.mContext).getDownloadedBusinessObject((b) this, this.d.getBusinessObjId(), c);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(ListingButton listingButton) {
        try {
            this.i = (BaseItemView) Class.forName(listingButton.f()).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.mContext, this});
        } catch (Exception unused) {
        }
    }

    private void l() {
        if (!TextUtils.isEmpty(this.a)) {
            this.J.removeAllViews();
            TextView textView = new TextView(this.mContext);
            if (VERSION.SDK_INT >= 23) {
                textView.setTextAppearance(R.style.item_detail_second_line_white);
            } else {
                textView.setTextAppearance(this.mContext, R.style.item_detail_second_line_white);
            }
            textView.setMaxLines(1);
            textView.setText(this.a);
            this.J.addView(textView);
        }
        this.J.setVisibility(0);
    }

    public BusinessObject e() {
        return this.d;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (i == 0) {
            if (viewHolder != null && viewHolder.getItemViewType() == 4) {
                if (this.y == null) {
                    String str = "";
                    if (this.d != null && ((Radio) this.d).getType().equalsIgnoreCase("RM")) {
                        str = Constants.dV;
                    }
                    if (this.d != null && ((Radio) this.d).getType().equalsIgnoreCase("RL")) {
                        str = Constants.dU;
                    }
                    this.y = new UpgradeHomeView(this.mContext, (BaseGaanaFragment) this, str);
                }
                this.y.getPopulatedView(i, viewHolder.itemView, (ViewGroup) viewHolder.itemView.getParent(), this.d);
            }
            return viewHolder.itemView;
        } else if (i == 1) {
            return viewHolder.itemView;
        } else {
            if (i >= this.l + this.x) {
                return viewHolder.itemView;
            }
            return this.i.getPoplatedView(viewHolder, a(i), viewGroup, false, Boolean.valueOf(false));
        }
    }

    public void notifyItemChanged(int i) {
        if (this.h != null) {
            this.h.notifyItemChanged(i + 1);
        }
    }

    public BusinessObject a(int i) {
        BusinessObject businessObject = new BusinessObject();
        ArrayList arrayList = new ArrayList();
        i = (i - this.x) * 2;
        for (int i2 = 0; i2 < 2; i2++) {
            if (i2 < this.w.size()) {
                int i3 = i + i2;
                if (i3 < this.w.size()) {
                    arrayList.add(i2, this.w.get(i3));
                }
            }
        }
        businessObject.setArrListBusinessObj(arrayList);
        return businessObject;
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        if (i == 4) {
            if (this.y == null) {
                this.y = new UpgradeHomeView(this.mContext, (BaseGaanaFragment) this, "");
            }
            return this.y.onCreateViewHolder(viewGroup, i);
        } else if (i == 6) {
            if (this.y == null) {
                this.y = new UpgradeHomeView(this.mContext, (BaseGaanaFragment) this, "");
            }
            return new ItemAdViewHolder(new View(this.mContext));
        } else if (i == 5) {
            return new ItemNormalViewHolder(m());
        } else {
            return i == 1 ? new TwoGridItemHolder(this.i.createViewHolder(viewGroup, i, R.layout.grid_twoitem_view)) : null;
        }
    }

    public int getItemViewType(int i) {
        return i == 0 ? ap.a().b(this.mContext) ? 4 : 6 : i == 1 ? 5 : 1;
    }

    public void onRefresh() {
        if (!this.k) {
            this.k = true;
            if (ap.a().b(this.mContext)) {
                ColombiaManager.b().c();
                if (this.y != null) {
                    this.y.setIsToBeRefreshed(true);
                }
            }
            k();
        }
    }

    public void onClick(View view) {
        FloatingActionButton floatingActionButton = this.m;
    }

    public void onDestroyView() {
        if (this.b.getParent() != null) {
            ((ViewGroup) this.b.getParent()).removeView(this.b);
        }
        super.onDestroyView();
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.k = false;
        super.onErrorResponse(volleyError);
        showNetworkErrorView(null);
        this.p.setVisibility(8);
    }

    public void onResponse(Object obj) {
        this.k = false;
        this.j.setRefreshing(false);
        this.p.setVisibility(8);
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (this.C != 0) {
            long j = timeInMillis - this.C;
            String str = "";
            if (this.d.getBusinessObjType() == BusinessObjectType.Radios) {
                str = "Radio detail";
            }
            Constants.a("Load", j, str, null);
        }
        if (Constants.l) {
            this.b.findViewById(R.id.overlay).setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.parallax_bar_color));
        }
        BusinessObject businessObject = (BusinessObject) obj;
        if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
            ((ListingButton) this.c.c().get(0)).a(businessObject.getArrListBusinessObj());
            this.mAppState.setCurrentBusObjInListView(businessObject.getArrListBusinessObj());
            this.e.setItemAnimator(new DefaultItemAnimator());
            this.d.setCount(String.valueOf(businessObject.getArrListBusinessObj().size()));
            j();
            this.w = businessObject.getArrListBusinessObj();
            if (this.w.size() % 2 == 0) {
                this.l = this.w.size() / 2;
            } else {
                this.l = (this.w.size() / 2) + 1;
            }
            this.x = 2;
            this.h.updateAdapterCount(this.l + this.x);
        }
    }

    private View m() {
        TextView textView = new TextView(this.mContext);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        float f = getResources().getDisplayMetrics().density;
        textView.setPadding((int) ((8.0f * f) + 0.5f), (int) ((24.0f * f) + 0.5f), 0, (int) ((10.0f * f) + 0.5f));
        textView.setText(this.mContext.getResources().getString(R.string.similar_radios));
        textView.setTextSize(2, 16.0f);
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.header_title_color});
        textView.setTextColor(obtainStyledAttributes.getColor(0, -1));
        obtainStyledAttributes.recycle();
        return textView;
    }

    public void onScrollChanged(int i, boolean z, boolean z2) {
        float d = (float) (this.r - d());
        int i2 = -i;
        float f = (float) i2;
        float d2 = (float) (d() - this.t.getHeight());
        i.g(this.t, f.a(f, d2, (float) d()));
        i.g(this.f, f.a(f, d2, 0.0f));
        i.g(this.g, f.a(f, d2, 0.0f));
        float f2 = (float) i;
        i.a(this.t, f.a(f2 / d, 0.0f, 1.0f));
        d2 = 0.7f + f.a((d - f2) / d, 0.0f, 0.5f);
        n();
        i.c(this.v, 0.0f);
        d = (float) (i2 + ((int) ((((float) this.r) - (((float) this.v.getHeight()) * d2)) - ((float) this.J.getHeight()))));
        i.g(this.v, f.a(d, ((float) (d() / 2)) - ((((float) this.v.getHeight()) * d2) / 1.5f), (float) this.r));
        d = f.a(d, 0.0f, (float) this.r);
        i.g(this.J, d);
        if (d < ((float) d())) {
            this.J.setVisibility(4);
            this.v.setLayoutParams(new LinearLayout.LayoutParams((int) (((float) this.B.widthPixels) / 2.0f), -2));
            i.f(this.v, f.a((float) (i / 6), (float) (d() / 3), ((float) d()) / 1.4f));
            i.d(this.v, f.a(d2, 0.8f, 1.0f));
            i.e(this.v, f.a(d2, 0.8f, 1.0f));
            return;
        }
        this.J.setVisibility(0);
        float f3 = (float) (i / 6);
        i.f(this.v, f.a(f3, 0.0f, (float) d()));
        i.f(this.J, f.a(f3, 0.0f, (float) d()));
        i.d(this.v, d2);
        i.e(this.v, d2);
        this.v.setLayoutParams(new LinearLayout.LayoutParams(this.B.widthPixels, -2));
    }

    @TargetApi(17)
    private void n() {
        Configuration configuration = getResources().getConfiguration();
        if (17 > VERSION.SDK_INT || configuration.getLayoutDirection() != 1) {
            i.b(this.v, 0.0f);
        } else {
            i.b(this.v, (float) this.b.getWidth());
        }
    }

    public String getSectionName() {
        return PLAYOUT_SECTION_TYPE.OTHER_RADIOS.name();
    }

    public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
        c();
    }
}
