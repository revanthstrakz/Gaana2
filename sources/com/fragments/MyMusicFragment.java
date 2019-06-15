package com.fragments;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.actionbar.GenericActionBar;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.f;
import com.constants.Constants;
import com.constants.Constants.SortOrder;
import com.constants.Constants.VIEW_SIZE;
import com.dynamicview.DynamicHomeScrollerView;
import com.dynamicview.DynamicViewManager;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.adapter.ListAdapter;
import com.gaana.adapter.ListAdapter.IAddListItemView;
import com.gaana.ads.interstitial.IAdType;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.models.PaymentProductDetailModel.CarouselOfferConfig;
import com.gaana.models.PaymentProductDetailModel.CarouselOfferDetails;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.MyMusicItemViewHolder;
import com.gaana.view.item.DownloadProgressBar;
import com.gaana.view.item.GenericCarouselView;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.ADSTATUS;
import com.managers.ColombiaAdViewManager.b;
import com.managers.ColombiaAdViewManager.c;
import com.managers.DownloadManager;
import com.managers.PurchaseGoogleManager.SubscriptionPurchaseType;
import com.managers.URLManager;
import com.managers.aa;
import com.managers.ag;
import com.managers.aj;
import com.managers.ap;
import com.managers.e;
import com.managers.m;
import com.managers.p;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.models.MyMusicItem;
import com.services.d;
import com.services.l.au;
import com.services.l.s;
import com.utilities.Util;
import com.utilities.h;
import java.util.ArrayList;
import java.util.List;

public class MyMusicFragment extends BaseGaanaFragment implements OnClickListener, com.constants.a.a, b, c {
    DynamicHomeScrollerView a;
    private View b;
    private View c = null;
    private RecyclerView d;
    private RecyclerView e;
    private ListAdapter f;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private int l;
    private TextView m;
    private CardView n;
    private LinearLayout o;
    private ViewGroup p;
    private PublisherAdView q;
    private ADSTATUS r;
    private DownloadProgressBar s;
    private List<MyMusicItem> t = new ArrayList();
    private List<MyMusicItem> u = new ArrayList();
    private a v;
    private String w = "";
    private AppBarLayout x;
    private ViewGroup y;

    class a extends Adapter<ViewHolder> {
        public int getItemViewType(int i) {
            return i != 3 ? 1 : 2;
        }

        a() {
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == 1) {
                return new MyMusicItemViewHolder(LayoutInflater.from(MyMusicFragment.this.mContext).inflate(R.layout.item_my_music, viewGroup, false));
            }
            return new ItemAdViewHolder(LayoutInflater.from(MyMusicFragment.this.mContext).inflate(R.layout.view_expand_item, viewGroup, false));
        }

        public void onBindViewHolder(final ViewHolder viewHolder, int i) {
            int i2 = i > 3 ? i - 1 : i;
            TypedArray obtainStyledAttributes;
            if (viewHolder.getItemViewType() == 1) {
                MyMusicItemViewHolder myMusicItemViewHolder = (MyMusicItemViewHolder) viewHolder;
                myMusicItemViewHolder.itemView.setId(((MyMusicItem) MyMusicFragment.this.t.get(i2)).c());
                myMusicItemViewHolder.itemView.setOnClickListener(MyMusicFragment.this);
                myMusicItemViewHolder.mTxtLabel.setText(((MyMusicItem) MyMusicFragment.this.t.get(i2)).a());
                TypedArray obtainStyledAttributes2 = MyMusicFragment.this.mContext.obtainStyledAttributes(R.styleable.MyMusicVecDrawables);
                myMusicItemViewHolder.mImgIcon.setImageDrawable(ContextCompat.getDrawable(MyMusicFragment.this.mContext, obtainStyledAttributes2.getResourceId(((MyMusicItem) MyMusicFragment.this.t.get(i2)).b(), -1)));
                obtainStyledAttributes2.recycle();
                if (i > 3) {
                    obtainStyledAttributes = MyMusicFragment.this.mContext.obtainStyledAttributes(new int[]{R.attr.first_line_color});
                    DrawableCompat.setTint(myMusicItemViewHolder.mImgIcon.getDrawable(), obtainStyledAttributes.getColor(0, -1));
                    obtainStyledAttributes.recycle();
                }
                if (!((GaanaActivity) MyMusicFragment.this.mContext).shouldHideDownloadedSongsCount()) {
                    int newDownloadsCount = ((GaanaActivity) MyMusicFragment.this.mContext).getNewDownloadsCount();
                    if (i != 0 || MyMusicFragment.this.j || newDownloadsCount <= 0) {
                        myMusicItemViewHolder.mTxtCount.setVisibility(8);
                        return;
                    }
                    myMusicItemViewHolder.mTxtCount.setVisibility(0);
                    TextView textView = myMusicItemViewHolder.mTxtCount;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("");
                    stringBuilder.append(newDownloadsCount);
                    textView.setText(stringBuilder.toString());
                }
            } else if (viewHolder.getItemViewType() == 2) {
                final TextView textView2 = (TextView) viewHolder.itemView.findViewById(R.id.txt_header);
                if (MyMusicFragment.this.w.equals("up")) {
                    obtainStyledAttributes = MyMusicFragment.this.mContext.obtainStyledAttributes(new int[]{R.attr.chevron_up});
                    textView2.setCompoundDrawablesWithIntrinsicBounds(null, null, obtainStyledAttributes.getDrawable(0), null);
                    obtainStyledAttributes.recycle();
                } else {
                    obtainStyledAttributes = MyMusicFragment.this.mContext.obtainStyledAttributes(new int[]{R.attr.chevron_down});
                    textView2.setCompoundDrawablesWithIntrinsicBounds(null, null, obtainStyledAttributes.getDrawable(0), null);
                    obtainStyledAttributes.recycle();
                }
                if (Constants.cZ) {
                    viewHolder.itemView.findViewById(R.id.my_music_party_banner).setOnClickListener(MyMusicFragment.this);
                    viewHolder.itemView.findViewById(R.id.my_music_party_banner).setVisibility(0);
                    viewHolder.itemView.findViewById(R.id.view).setVisibility(4);
                } else {
                    viewHolder.itemView.findViewById(R.id.view).setVisibility(0);
                }
                MyMusicFragment myMusicFragment = MyMusicFragment.this;
                boolean z = (ap.a().j() || ((GaanaActivity) MyMusicFragment.this.mContext).getNewDownloadsCount() > 0) && d.a().b("OPEN_MY_DOWNLOADS_COACHMARK", true, true);
                myMusicFragment.k = z;
                if (MyMusicFragment.this.k) {
                    d.a().a("OPEN_MY_DOWNLOADS_COACHMARK", false, true);
                    viewHolder.itemView.findViewById(R.id.open_my_downloads).setVisibility(0);
                    ((TextView) viewHolder.itemView.findViewById(R.id.txt_coachmark_my_downloads)).setTypeface(p.a().a(GaanaApplication.getLanguage(MyMusicFragment.this.mContext)));
                    viewHolder.itemView.findViewById(R.id.open_my_downloads).setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            viewHolder.itemView.findViewById(R.id.open_my_downloads).setVisibility(8);
                        }
                    });
                } else {
                    viewHolder.itemView.findViewById(R.id.open_my_downloads).setVisibility(8);
                }
                textView2.setOnClickListener(new OnClickListener() {
                    /* JADX WARNING: Removed duplicated region for block: B:13:0x0066  */
                    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
                    /* JADX WARNING: Removed duplicated region for block: B:17:0x00b6  */
                    /* JADX WARNING: Removed duplicated region for block: B:16:0x0098  */
                    /* JADX WARNING: Removed duplicated region for block: B:13:0x0066  */
                    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
                    /* JADX WARNING: Removed duplicated region for block: B:16:0x0098  */
                    /* JADX WARNING: Removed duplicated region for block: B:17:0x00b6  */
                    public void onClick(android.view.View r5) {
                        /*
                        r4 = this;
                        r5 = com.fragments.MyMusicFragment.a.this;
                        r5 = com.fragments.MyMusicFragment.this;
                        r5 = r5.w;
                        r0 = r5.hashCode();
                        r1 = 3739; // 0xe9b float:5.24E-42 double:1.8473E-320;
                        r2 = 1;
                        r3 = 0;
                        if (r0 == r1) goto L_0x0022;
                    L_0x0012:
                        r1 = 3089570; // 0x2f24a2 float:4.32941E-39 double:1.5264504E-317;
                        if (r0 == r1) goto L_0x0018;
                    L_0x0017:
                        goto L_0x002c;
                    L_0x0018:
                        r0 = "down";
                        r5 = r5.equals(r0);
                        if (r5 == 0) goto L_0x002c;
                    L_0x0020:
                        r5 = r2;
                        goto L_0x002d;
                    L_0x0022:
                        r0 = "up";
                        r5 = r5.equals(r0);
                        if (r5 == 0) goto L_0x002c;
                    L_0x002a:
                        r5 = r3;
                        goto L_0x002d;
                    L_0x002c:
                        r5 = -1;
                    L_0x002d:
                        switch(r5) {
                            case 0: goto L_0x0066;
                            case 1: goto L_0x0031;
                            default: goto L_0x0030;
                        };
                    L_0x0030:
                        goto L_0x0087;
                    L_0x0031:
                        r5 = com.fragments.MyMusicFragment.a.this;
                        r5 = com.fragments.MyMusicFragment.this;
                        r0 = "up";
                        r5.w = r0;
                        r5 = com.fragments.MyMusicFragment.a.this;
                        r5 = com.fragments.MyMusicFragment.this;
                        r5 = r5.t;
                        r0 = com.fragments.MyMusicFragment.a.this;
                        r0 = com.fragments.MyMusicFragment.this;
                        r0 = r0.u;
                        r5.removeAll(r0);
                        r5 = com.fragments.MyMusicFragment.a.this;
                        r5 = com.fragments.MyMusicFragment.this;
                        r5 = r5.t;
                        r0 = com.fragments.MyMusicFragment.a.this;
                        r0 = com.fragments.MyMusicFragment.this;
                        r0 = r0.u;
                        r5.addAll(r0);
                        r5 = com.fragments.MyMusicFragment.a.this;
                        r5.notifyDataSetChanged();
                        goto L_0x0087;
                    L_0x0066:
                        r5 = com.fragments.MyMusicFragment.a.this;
                        r5 = com.fragments.MyMusicFragment.this;
                        r0 = "down";
                        r5.w = r0;
                        r5 = com.fragments.MyMusicFragment.a.this;
                        r5 = com.fragments.MyMusicFragment.this;
                        r5 = r5.t;
                        r0 = com.fragments.MyMusicFragment.a.this;
                        r0 = com.fragments.MyMusicFragment.this;
                        r0 = r0.u;
                        r5.removeAll(r0);
                        r5 = com.fragments.MyMusicFragment.a.this;
                        r5.notifyDataSetChanged();
                    L_0x0087:
                        r5 = com.fragments.MyMusicFragment.a.this;
                        r5 = com.fragments.MyMusicFragment.this;
                        r5 = r5.w;
                        r0 = "up";
                        r5 = r5.equals(r0);
                        r0 = 0;
                        if (r5 == 0) goto L_0x00b6;
                    L_0x0098:
                        r5 = new int[r2];
                        r1 = 2130968769; // 0x7f0400c1 float:1.75462E38 double:1.052838461E-314;
                        r5[r3] = r1;
                        r1 = com.fragments.MyMusicFragment.a.this;
                        r1 = com.fragments.MyMusicFragment.this;
                        r1 = r1.mContext;
                        r5 = r1.obtainStyledAttributes(r5);
                        r1 = r5.getDrawable(r3);
                        r2 = r10;
                        r2.setCompoundDrawablesWithIntrinsicBounds(r0, r0, r1, r0);
                        r5.recycle();
                        goto L_0x00d3;
                    L_0x00b6:
                        r5 = new int[r2];
                        r1 = 2130968768; // 0x7f0400c0 float:1.7546199E38 double:1.0528384606E-314;
                        r5[r3] = r1;
                        r1 = com.fragments.MyMusicFragment.a.this;
                        r1 = com.fragments.MyMusicFragment.this;
                        r1 = r1.mContext;
                        r5 = r1.obtainStyledAttributes(r5);
                        r1 = r5.getDrawable(r3);
                        r2 = r10;
                        r2.setCompoundDrawablesWithIntrinsicBounds(r0, r0, r1, r0);
                        r5.recycle();
                    L_0x00d3:
                        r5 = com.services.d.a();
                        r0 = "pref_mymusic_cat";
                        r1 = com.fragments.MyMusicFragment.a.this;
                        r1 = com.fragments.MyMusicFragment.this;
                        r1 = r1.w;
                        r5.a(r0, r1, r3);
                        return;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.fragments.MyMusicFragment$a$AnonymousClass2.onClick(android.view.View):void");
                    }
                });
            }
        }

        public int getItemCount() {
            return MyMusicFragment.this.t != null ? MyMusicFragment.this.t.size() + 1 : 0;
        }
    }

    public String getFragmentStackName() {
        return "mymusic";
    }

    private void d() {
        this.t.add(new MyMusicItem(R.id.MyMusicMenuDownloads, GaanaApplication.getContext().getString(R.string.mymusic_downloads), 2, "my_music_downloads"));
        this.t.add(new MyMusicItem(R.id.MyMusicFavorites, GaanaApplication.getContext().getString(R.string.mymusic_favorites), 3, "my_music_favorites"));
        this.t.add(new MyMusicItem(R.id.MyMusicMenuPhoneMusic, GaanaApplication.getContext().getString(R.string.memory_card), 4, "my_music_local_phn_music"));
        this.u.add(new MyMusicItem(R.id.MyMusicMenuSongs, GaanaApplication.getContext().getString(R.string.songs_text), 8, "my_music_songs"));
        this.u.add(new MyMusicItem(R.id.MyMusicMenuAlbums, GaanaApplication.getContext().getString(R.string.albums_text), 0, "my_music_albums"));
        this.u.add(new MyMusicItem(R.id.MyMusicMenuPlaylists, GaanaApplication.getContext().getString(R.string.playlists), 6, "my_music_playlists"));
        this.u.add(new MyMusicItem(R.id.MyMusicMenuArtists, GaanaApplication.getContext().getString(R.string.artists_title), 1, "my_music_artists"));
        this.u.add(new MyMusicItem(R.id.MyMusicMenuRadios, GaanaApplication.getContext().getString(R.string.radios_title), 7, "my_music_radios"));
        this.w = d.a().b("pref_mymusic_cat", "up", false);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        d();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.c == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.c = setContentView(R.layout.layout_mymusic, viewGroup);
            this.y = (ViewGroup) this.c.findViewById(R.id.ad_banner_top);
            this.x = (AppBarLayout) this.c.findViewById(R.id.app_bar_layout);
            this.s = new DownloadProgressBar(getActivity(), this);
            this.d = (RecyclerView) this.c.findViewById(R.id.listViewHome);
            ((SwipeRefreshLayout) this.c.findViewById(R.id.swipe_refresh_layout)).setEnabled(false);
            a(new ArrayList());
            this.mAppState = (GaanaApplication) getActivity().getApplicationContext();
            if (ap.a().b(this.mContext) && f()) {
                this.q = new PublisherAdView(this.mContext);
                this.p = (ViewGroup) layoutInflater.inflate(R.layout.top_banner_ad, null);
                this.y.addView(this.p);
                this.y.setVisibility(0);
            }
            sendGAScreenName("MyMusicScreen", "MyMusicScreen");
            ColombiaAdViewManager.a().e();
            GaanaApplication.getInstance().removeGADParameter();
            Bundle arguments = getArguments();
            if (arguments != null && arguments.getInt("DEEPLINKING_SCREEN") > 0) {
                a(arguments.getInt("DEEPLINKING_SCREEN"), arguments.getString("DEEPLINKING_SCREEN_EXTRA_PARAM"), arguments.getString("DEEPLINKING_SCREEN_EXTRA_PARAM2"), arguments.getString("DEEPLINKING_SCREEN_SORT_ORDER"));
            }
        } else {
            if (this.v != null) {
                this.v.notifyDataSetChanged();
            }
            this.i = false;
        }
        setCurrentFragment();
        ((BaseActivity) this.mContext).setCustomActionBar((ViewGroup) this.c, new GenericActionBar(this.mContext, getString(R.string.mymusic), false, this));
        ((BaseActivity) this.mContext).resetLoginStatus();
        ((GaanaActivity) this.mContext).hideThemeBackground(false);
        aa.a().b(false);
        this.x.setExpanded(true, false);
        this.currentUJPage = "MYMUSIC";
        n();
        return this.c;
    }

    private List<MyMusicItem> e() {
        List i = com.e.a.c.a().i();
        if (i != null && i.size() > 0) {
            this.u.add(new MyMusicItem(R.id.MyMusicMenuOccasions, GaanaApplication.getContext().getString(R.string.mymusic_occasions), 5, "my_music_occasions"));
        }
        if (this.w.equals("up")) {
            this.t.removeAll(this.u);
            this.t.addAll(this.u);
        } else {
            this.t.removeAll(this.u);
        }
        return this.t;
    }

    public void a() {
        if (this.mAppState.getCurrentUser().getLoginStatus()) {
            if (!DownloadManager.c().v() || DownloadManager.c().k() != -1) {
                View view = this.s.getView(null);
                this.n.addView(view);
                new LayoutParams(-1, -2).setMargins(0, 0, 0, (int) TypedValue.applyDimension(1, 8.0f, GaanaApplication.getContext().getResources().getDisplayMetrics()));
                this.n.setVisibility(0);
                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        ((GaanaActivity) MyMusicFragment.this.mContext).displayFragment(new DownloadDetailsFragment());
                    }
                });
            } else if (DownloadManager.c().r() > 0) {
                this.s.checkForFailedDownloadView(this.n);
            } else {
                this.n.removeAllViews();
                this.n.setVisibility(8);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0061  */
    public void refreshListView() {
        /*
        r5 = this;
        super.refreshListView();
        r0 = r5.s;
        if (r0 == 0) goto L_0x006a;
    L_0x0007:
        r0 = r5.n;
        r1 = 0;
        r2 = 1;
        if (r0 == 0) goto L_0x0020;
    L_0x000d:
        r0 = r5.n;
        r0 = r0.getChildCount();
        r3 = r5.q;
        if (r3 == 0) goto L_0x0019;
    L_0x0017:
        r3 = r2;
        goto L_0x001a;
    L_0x0019:
        r3 = r1;
    L_0x001a:
        if (r0 != r3) goto L_0x0020;
    L_0x001c:
        r5.a();
        goto L_0x0058;
    L_0x0020:
        r0 = r5.n;
        if (r0 == 0) goto L_0x0058;
    L_0x0024:
        r0 = r5.n;
        r0 = r0.getChildCount();
        r3 = r5.q;
        if (r3 == 0) goto L_0x0030;
    L_0x002e:
        r3 = 2;
        goto L_0x0031;
    L_0x0030:
        r3 = r2;
    L_0x0031:
        if (r0 != r3) goto L_0x0058;
    L_0x0033:
        r0 = r5.n;
        r3 = r5.q;
        if (r3 == 0) goto L_0x003b;
    L_0x0039:
        r3 = r2;
        goto L_0x003c;
    L_0x003b:
        r3 = r1;
    L_0x003c:
        r0 = r0.getChildAt(r3);
        r0 = r0 instanceof com.gaana.view.item.FailedDownloadView;
        if (r0 == 0) goto L_0x0058;
    L_0x0044:
        r0 = r5.n;
        r3 = r5.n;
        r4 = r5.q;
        if (r4 == 0) goto L_0x004d;
    L_0x004c:
        goto L_0x004e;
    L_0x004d:
        r2 = r1;
    L_0x004e:
        r2 = r3.getChildAt(r2);
        r0.removeView(r2);
        r5.a();
    L_0x0058:
        r0 = r5.s;
        r0.refreshProgressBar();
        r0 = r5.e;
        if (r0 == 0) goto L_0x006a;
    L_0x0061:
        r0 = r5.e;
        r0 = r0.getAdapter();
        r0.notifyItemChanged(r1);
    L_0x006a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fragments.MyMusicFragment.refreshListView():void");
    }

    public void loadTopBannerAds() {
        if (e.Y == 0) {
            ColombiaAdViewManager.a().b(this.mContext, this.p, e.B, this.q, this, "DFP_SECTION_FAV_TOP_BANNER");
            return;
        }
        ColombiaAdViewManager.a().a(this.mContext, this.p, 27, getClass().getSimpleName(), this.q, (b) this, "DFP_SECTION_FAV_TOP_BANNER", new AdsUJData[0]);
    }

    private boolean f() {
        return this.r != ADSTATUS.LOADING;
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            setCurrentFragment();
            GaanaApplication.getInstance().setCurrentPageName(getPageName());
        }
    }

    public void b() {
        if (this.l != 0) {
            onClick(getView().findViewById(this.l));
            DownloadManager.c().d();
        }
        this.l = 0;
    }

    private com.dynamicview.f.a g() {
        com.dynamicview.f.a aVar;
        ArrayList<com.dynamicview.f.a> e = DynamicViewManager.a().e();
        if (e != null) {
            for (com.dynamicview.f.a aVar2 : e) {
                if (aVar2 != null) {
                    if (!TextUtils.isEmpty(aVar2.p()) && aVar2.p().equalsIgnoreCase("MADE_FOR_YOU")) {
                        break;
                    }
                }
            }
        }
        com.dynamicview.f.a aVar22 = null;
        if (aVar22 == null) {
            com.dynamicview.f.a aVar3 = new com.dynamicview.f.a("Made For You", "https://apiv2.gaana.com/made-for-you/mixes", DynamicViewType.hor_scroll.name(), "https://apiv2.gaana.com/made-for-you/mixes", "MADE_FOR_YOU", "", "", "240");
            aVar3.a(VIEW_SIZE.GRID_LARGE.getNumVal());
        } else {
            aVar = aVar22;
        }
        aVar.c(0);
        return aVar;
    }

    private void a(ArrayList<BusinessObject> arrayList) {
        this.f = new ListAdapter(this.mContext);
        arrayList.add(new BusinessObject());
        this.f.setParamaters(arrayList, new IAddListItemView() {
            public int getItemViewType(int i) {
                return 1;
            }

            public void showHideEmtpyView(boolean z) {
            }

            public View addListItemView(Object obj, ViewHolder viewHolder, ViewGroup viewGroup) {
                return MyMusicFragment.this.a.getPopulatedView(0, viewHolder, viewGroup);
            }

            public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
                if (MyMusicFragment.this.a == null) {
                    MyMusicFragment.this.a = new DynamicHomeScrollerView(MyMusicFragment.this.mContext, MyMusicFragment.this);
                }
                MyMusicFragment.this.a.setDynamicData(MyMusicFragment.this.g());
                return MyMusicFragment.this.a.onCreateViewHolder(viewGroup, i);
            }
        });
        this.f.setHeaderView(i());
        this.d.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        this.d.setItemAnimator(new DefaultItemAnimator());
        this.d.setAdapter(this.f);
    }

    private void h() {
        if (Util.j(this.mContext) && !this.mAppState.isAppInOfflineMode()) {
            URLManager uRLManager = new URLManager();
            uRLManager.a("https://api.gaana.com/gaanaplusservice_nxtgen.php?type=my_music_card");
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.a(CarouselOfferDetails.class);
            uRLManager.b(1);
            uRLManager.i(false);
            i.a().a(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                /* Access modifiers changed, original: 0000 */
                public boolean a(BusinessObject businessObject) {
                    if (businessObject instanceof CarouselOfferDetails) {
                        CarouselOfferDetails carouselOfferDetails = (CarouselOfferDetails) businessObject;
                        if (carouselOfferDetails.getArrCarouselOfferConfig() != null && carouselOfferDetails.getArrCarouselOfferConfig().size() > 0) {
                            return true;
                        }
                    }
                    return false;
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (a(businessObject)) {
                        CarouselOfferDetails carouselOfferDetails = (CarouselOfferDetails) businessObject;
                        if (!carouselOfferDetails.isCarousel() || carouselOfferDetails.getArrCarouselOfferConfig().size() == 1) {
                            View inflate = ((LayoutInflater) MyMusicFragment.this.mContext.getSystemService("layout_inflater")).inflate(R.layout.carousel_view_item_mymusic_offer, null);
                            ImageView imageView = (CrossFadeImageView) inflate.findViewById(R.id.carouselImage);
                            final CarouselOfferConfig carouselOfferConfig = (CarouselOfferConfig) carouselOfferDetails.getArrCarouselOfferConfig().get(0);
                            String offerUrl = carouselOfferConfig.getOfferUrl();
                            com.bumptech.glide.e.c(MyMusicFragment.this.mContext.getApplicationContext()).load(offerUrl).apply(new f().placeholder(imageView.getDrawable())).listener(new com.bumptech.glide.request.e<Drawable>() {
                                /* renamed from: a */
                                public boolean onResourceReady(Drawable drawable, Object obj, com.bumptech.glide.request.a.i<Drawable> iVar, DataSource dataSource, boolean z) {
                                    return false;
                                }

                                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, com.bumptech.glide.request.a.i<Drawable> iVar, boolean z) {
                                    return false;
                                }
                            }).into(imageView);
                            inflate.setOnClickListener(new OnClickListener() {
                                public void onClick(View view) {
                                    if (carouselOfferConfig.getOfferProduct() != null) {
                                        MyMusicFragment.this.a(carouselOfferConfig.getOfferProduct());
                                    } else if (carouselOfferConfig.getAppDeepLink() != null) {
                                        com.services.c.a(MyMusicFragment.this.mContext).a(MyMusicFragment.this.mContext, carouselOfferConfig.getAppDeepLink(), MyMusicFragment.this.mAppState);
                                    }
                                }
                            });
                            MyMusicFragment.this.o.removeAllViews();
                            MyMusicFragment.this.o.addView(inflate);
                            MyMusicFragment.this.o.setVisibility(0);
                            return;
                        }
                        GenericCarouselView genericCarouselView = new GenericCarouselView(MyMusicFragment.this.mContext, MyMusicFragment.this, R.layout.carousel_view_item_mymusic_offers, 15, 15);
                        genericCarouselView.setCarouselData(carouselOfferDetails.getArrCarouselOfferConfig());
                        MyMusicFragment.this.o.removeAllViews();
                        MyMusicFragment.this.o.addView(genericCarouselView.getNewView(R.layout.mymusic_offers_carousel_view, null));
                        MyMusicFragment.this.o.setVisibility(0);
                    }
                }
            }, uRLManager);
        }
    }

    public void a(ProductItem productItem) {
        if (productItem == null || TextUtils.isEmpty(productItem.getAction())) {
            ((GaanaActivity) this.mContext).changeFragment(R.id.upgradeButtonLayout, null, null);
        } else if (NativeContentAd.ASSET_HEADLINE.equalsIgnoreCase(productItem.getAction())) {
            u.a().a(productItem, productItem.getItem_id());
            ag.a(this.mContext).a(this.mContext, productItem, new com.managers.ag.a() {
                public void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType) {
                    ag.a(MyMusicFragment.this.mContext).a("", "", "success");
                    ((BaseActivity) MyMusicFragment.this.mContext).updateUserStatus(new au() {
                        public void onUserStatusUpdated() {
                            ((BaseActivity) MyMusicFragment.this.mContext).hideProgressDialog();
                            ap.a().a(MyMusicFragment.this.mContext);
                            Util.aa();
                            aj.a().a(MyMusicFragment.this.mContext, MyMusicFragment.this.mContext.getString(R.string.enjoy_using_gaana_plus));
                            if (((GaanaActivity) MyMusicFragment.this.mContext).getCurrentSongSelectedView() != null) {
                                ((GaanaActivity) MyMusicFragment.this.mContext).getCurrentSongSelectedView().callOnClick();
                            }
                        }
                    });
                }

                public void onFailure(String str, String str2) {
                    if (!TextUtils.isEmpty(str)) {
                        aj.a().a(MyMusicFragment.this.mContext, str);
                    }
                    ag.a(MyMusicFragment.this.mContext).a(str, "", str2);
                    u.a().a("Premium pop-up", "Try Gaana Plus", "Failure");
                }
            }, productItem.getItem_id(), productItem.getDesc());
        } else if (NativeContentAd.ASSET_BODY.equalsIgnoreCase(productItem.getAction())) {
            BaseGaanaFragment paymentDetailFragment = new PaymentDetailFragment();
            paymentDetailFragment.a(productItem);
            ((GaanaActivity) this.mContext).displayFragment(paymentDetailFragment);
        } else if (NativeContentAd.ASSET_CALL_TO_ACTION.equalsIgnoreCase(productItem.getAction()) && !TextUtils.isEmpty(productItem.getWeb_url())) {
            Intent intent = new Intent(this.mContext, WebViewActivity.class);
            intent.putExtra("EXTRA_WEBVIEW_URL", productItem.getWeb_url());
            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
            intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
            intent.putExtra("title", LoginManager.TAG_SUBTYPE_GAANA);
            this.mContext.startActivity(intent);
        } else if (NativeContentAd.ASSET_ADVERTISER.equalsIgnoreCase(productItem.getAction())) {
            u.a().a(productItem, productItem.getItem_id());
            u.a().a(productItem, productItem.getDesc(), productItem.getItem_id(), 0);
            ((GaanaActivity) this.mContext).displayFragment(new ReferFriendsFragment());
        }
    }

    private View i() {
        this.t = e();
        this.layoutInflater = LayoutInflater.from(getActivity());
        this.b = this.layoutInflater.inflate(R.layout.fragment_mymusic, null);
        this.m = (TextView) this.b.findViewById(R.id.txt_page_title);
        this.m.setText(this.mContext.getResources().getString(R.string.mymusic));
        this.m.setTypeface(null, 1);
        this.o = (LinearLayout) this.b.findViewById(R.id.ll_carousel_offers);
        h();
        this.n = (CardView) this.b.findViewById(R.id.llParentHeader);
        this.e = (RecyclerView) this.b.findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.mContext, 3);
        gridLayoutManager.setSpanSizeLookup(new SpanSizeLookup() {
            public int getSpanSize(int i) {
                return i == 3 ? 3 : 1;
            }
        });
        this.e.setHasFixedSize(true);
        this.e.setNestedScrollingEnabled(false);
        this.e.setLayoutManager(gridLayoutManager);
        this.v = new a();
        this.e.setAdapter(this.v);
        return this.b;
    }

    public void onPause() {
        if (this.q != null) {
            this.q.pause();
        }
        ColombiaAdViewManager.a().a(null);
        super.onPause();
    }

    public void onResume() {
        if (!this.i) {
            updateView();
        }
        if (this.q != null) {
            this.q.resume();
        }
        ColombiaAdViewManager.a().a((c) this);
        ColombiaAdViewManager.a().a(this.mContext, this.p);
        if (this.s != null) {
            if (this.n != null) {
                if (this.n.getChildCount() == (this.q != null ? 1 : 0)) {
                    a();
                }
            }
            this.s.refreshProgressBar();
        }
        super.onResume();
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void j() {
        BaseGaanaFragment listingFragment = new ListingFragment();
        listingFragment.a(SortOrder.Default);
        listingFragment.setAnimateFragmentElements(true);
        listingFragment.a("FAV_RD_BOTTOM_BANNER");
        ListingParams listingParams = new ListingParams();
        listingParams.e(true);
        listingParams.g(true);
        listingParams.f(true);
        listingParams.d(true);
        listingParams.i(true);
        listingParams.j(true);
        listingParams.b(true);
        listingParams.k(false);
        listingParams.b(PLAYOUT_SECTION_TYPE.MYMUSIC_RADIO.name());
        listingParams.a(String.valueOf(e.k));
        ListingButton listingButton = (ListingButton) Constants.a().c().get(3);
        listingButton.b(true);
        listingButton.a(new m());
        listingButton.b(this.mContext.getString(R.string.radios_title));
        listingButton.a(this.mContext.getString(R.string.radios_title));
        URLManager c = listingButton.c();
        c.g(true);
        c.a("https://api.gaana.com/radio.php?type=radio&subtype=favorite_radios");
        c.a(Boolean.valueOf(true));
        c.d(true);
        c.h(false);
        listingParams.c(Util.a(c));
        listingParams.a(SortOrder.TrackName);
        listingParams.a(listingButton);
        listingFragment.a(listingParams);
        listingFragment.a((BaseGaanaFragment) this);
        listingFragment.b(true);
        ListingComponents listingComponents = new ListingComponents();
        new ArrayList().add(listingButton);
        this.mAppState.setListingComponents(listingComponents);
        ((GaanaActivity) this.mContext).displayFragment(listingFragment);
    }

    private void k() {
        BaseGaanaFragment listingFragment = new ListingFragment();
        listingFragment.a(SortOrder.Default);
        listingFragment.setAnimateFragmentElements(true);
        listingFragment.a("FAV_AR_BOTTOM_BANNER");
        ListingParams listingParams = new ListingParams();
        listingParams.e(true);
        listingParams.d(true);
        listingParams.f(true);
        listingParams.b(true);
        listingParams.i(true);
        listingParams.k(false);
        listingParams.j(true);
        listingParams.a(String.valueOf(e.k));
        listingParams.b(PLAYOUT_SECTION_TYPE.MYMUSIC_ARTIST.name());
        listingParams.g(true);
        ListingButton listingButton = (ListingButton) Constants.a().c().get(4);
        listingButton.b(this.mContext.getString(R.string.artists_title));
        listingButton.a(this.mContext.getString(R.string.artists_title));
        listingButton.b(true);
        listingButton.a(new m());
        URLManager c = listingButton.c();
        c.g(true);
        c.a("https://api.gaana.com/user.php?type=myartists&subtype=favorites&limit=0,100");
        c.a(Boolean.valueOf(true));
        c.h(false);
        listingParams.a(listingButton);
        listingParams.a(SortOrder.TrackName);
        listingParams.c(Util.a(c));
        listingFragment.a(listingParams);
        listingFragment.a((BaseGaanaFragment) this);
        ListingComponents listingComponents = new ListingComponents();
        new ArrayList().add(listingButton);
        this.mAppState.setListingComponents(listingComponents);
        ((GaanaActivity) this.mContext).displayFragment(listingFragment);
    }

    private void m() {
        BaseGaanaFragment listingFragment = new ListingFragment();
        listingFragment.a(SortOrder.Default);
        listingFragment.setAnimateFragmentElements(true);
        listingFragment.a("FAV_MUSIC_HUB_BOTTOM_BANNER");
        ListingParams listingParams = new ListingParams();
        listingParams.e(true);
        listingParams.g(true);
        listingParams.d(true);
        listingParams.f(true);
        listingParams.b(true);
        listingParams.i(true);
        listingParams.k(false);
        listingParams.j(true);
        listingParams.b(PLAYOUT_SECTION_TYPE.MYMUSIC_MUSICHUB.name());
        ListingButton listingButton = (ListingButton) Constants.a().c().get(5);
        listingButton.b(this.mContext.getString(R.string.mymusic_occasions));
        listingButton.a(this.mContext.getString(R.string.mymusic_occasions));
        listingButton.b(true);
        listingButton.a(new m());
        URLManager c = listingButton.c();
        c.g(true);
        c.a("https://api.gaana.com/user.php?type=myoccasions&subtype=favorites&limit=0,100");
        c.a(Boolean.valueOf(true));
        c.h(false);
        listingParams.c(Util.a(c));
        listingParams.a(SortOrder.TrackName);
        listingParams.a(listingButton);
        listingFragment.a(listingParams);
        listingFragment.a((BaseGaanaFragment) this);
        ListingComponents listingComponents = new ListingComponents();
        new ArrayList().add(listingButton);
        this.mAppState.setListingComponents(listingComponents);
        ((GaanaActivity) this.mContext).displayFragment(listingFragment);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.my_music_party_banner) {
            u.a().a("PartyHub", "Entry", "MyMusic");
            ((GaanaActivity) this.mContext).changeFragment(R.id.LeftPartyHub, null, null);
        } else if (id != R.id.MyMusicMenuPhoneMusic || h.e(getActivity())) {
            a(id, null, null);
        } else {
            this.l = id;
        }
    }

    private void a(int i, String str, String str2) {
        a(i, str, str2, SortOrder.Default.name());
    }

    /* JADX WARNING: Removed duplicated region for block: B:91:0x03a4  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0381  */
    /* JADX WARNING: Missing block: B:84:0x037d, code skipped:
            if (com.managers.ap.a().d() == false) goto L_0x0354;
     */
    private void a(int r11, java.lang.String r12, java.lang.String r13, java.lang.String r14) {
        /*
        r10 = this;
        r0 = new android.os.Bundle;
        r0.<init>();
        r1 = android.text.TextUtils.isEmpty(r12);
        if (r1 != 0) goto L_0x0010;
    L_0x000b:
        r1 = "DEEPLINKING_SCREEN_EXTRA_PARAM";
        r0.putString(r1, r12);
    L_0x0010:
        r12 = android.text.TextUtils.isEmpty(r13);
        if (r12 != 0) goto L_0x001b;
    L_0x0016:
        r12 = "DEEPLINKING_SCREEN_EXTRA_PARAM2";
        r0.putString(r12, r13);
    L_0x001b:
        r12 = android.text.TextUtils.isEmpty(r14);
        if (r12 != 0) goto L_0x0032;
    L_0x0021:
        r12 = com.constants.Constants.SortOrder.Default;
        r12 = r12.name();
        r12 = r14.equalsIgnoreCase(r12);
        if (r12 != 0) goto L_0x0032;
    L_0x002d:
        r12 = "DEEPLINKING_SCREEN_SORT_ORDER";
        r0.putString(r12, r14);
    L_0x0032:
        r12 = 2131297774; // 0x7f0905ee float:1.8213502E38 double:1.053001011E-314;
        r13 = 3;
        r14 = 2131821271; // 0x7f1102d7 float:1.927528E38 double:1.053259653E-314;
        if (r11 == r12) goto L_0x032e;
    L_0x003b:
        r12 = 2131821784; // 0x7f1104d8 float:1.927632E38 double:1.0532599065E-314;
        r1 = 0;
        switch(r11) {
            case 2131296320: goto L_0x02b1;
            case 2131296321: goto L_0x0246;
            case 2131296322: goto L_0x01ee;
            case 2131296323: goto L_0x032e;
            case 2131296324: goto L_0x0196;
            case 2131296325: goto L_0x0174;
            case 2131296326: goto L_0x0109;
            case 2131296327: goto L_0x00af;
            case 2131296328: goto L_0x0044;
            default: goto L_0x0042;
        };
    L_0x0042:
        goto L_0x03d9;
    L_0x0044:
        r13 = com.gaana.application.GaanaApplication.getInstance();
        r13 = r13.getCurrentUser();
        r13 = r13.getLoginStatus();
        if (r13 == 0) goto L_0x007f;
    L_0x0052:
        r11 = r10.mAppState;
        r12 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.MYMUSIC_SONGS;
        r12 = r12.name();
        r11.setPlayoutSectionName(r12);
        r11 = com.managers.u.a();
        r12 = "MyMusicScreen";
        r13 = "Songs Click";
        r11.b(r12, r13);
        r11 = new com.fragments.MyMusicItemFragment;
        r11.<init>();
        r12 = "obj_type";
        r13 = com.managers.URLManager.BusinessObjectType.Tracks;
        r0.putSerializable(r12, r13);
        r11.setArguments(r0);
        r12 = r10.mContext;
        r12 = (com.gaana.GaanaActivity) r12;
        r12.displayFragment(r11);
        return;
    L_0x007f:
        r13 = r10.mContext;
        r13 = com.utilities.Util.j(r13);
        if (r13 != 0) goto L_0x0095;
    L_0x0087:
        r11 = com.managers.aj.a();
        r12 = r10.mContext;
        r13 = r10.getString(r14);
        r11.a(r12, r13);
        return;
    L_0x0095:
        r13 = r10.mContext;
        r13 = com.managers.af.a(r13, r1);
        r14 = r10.mContext;
        r14 = r14.getResources();
        r12 = r14.getString(r12);
        r14 = new com.fragments.MyMusicFragment$10;
        r14.<init>(r0);
        r13.a(r11, r1, r12, r14);
        goto L_0x03d9;
    L_0x00af:
        r13 = com.gaana.application.GaanaApplication.getInstance();
        r13 = r13.getCurrentUser();
        r13 = r13.getLoginStatus();
        if (r13 == 0) goto L_0x00d8;
    L_0x00bd:
        r11 = r10.mAppState;
        r12 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.MYMUSIC_RADIO;
        r12 = r12.name();
        r11.setPlayoutSectionName(r12);
        r11 = com.managers.u.a();
        r12 = "MyMusicScreen";
        r13 = "Radio Click";
        r11.b(r12, r13);
        r10.j();
        goto L_0x03d9;
    L_0x00d8:
        r13 = r10.mContext;
        r13 = com.utilities.Util.j(r13);
        if (r13 != 0) goto L_0x00ef;
    L_0x00e0:
        r11 = com.managers.aj.a();
        r12 = r10.mContext;
        r13 = r10.getString(r14);
        r11.a(r12, r13);
        goto L_0x03d9;
    L_0x00ef:
        r13 = r10.mContext;
        r13 = com.managers.af.a(r13, r1);
        r14 = r10.mContext;
        r14 = r14.getResources();
        r12 = r14.getString(r12);
        r14 = new com.fragments.MyMusicFragment$13;
        r14.<init>();
        r13.a(r11, r1, r12, r14);
        goto L_0x03d9;
    L_0x0109:
        r13 = com.gaana.application.GaanaApplication.getInstance();
        r13 = r13.getCurrentUser();
        r13 = r13.getLoginStatus();
        if (r13 == 0) goto L_0x0144;
    L_0x0117:
        r11 = r10.mAppState;
        r12 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.MYMUSIC_PLAYLIST;
        r12 = r12.name();
        r11.setPlayoutSectionName(r12);
        r11 = com.managers.u.a();
        r12 = "MyMusicScreen";
        r13 = "Playlist Click";
        r11.b(r12, r13);
        r11 = new com.fragments.MyMusicItemFragment;
        r11.<init>();
        r12 = "obj_type";
        r13 = com.managers.URLManager.BusinessObjectType.Playlists;
        r0.putSerializable(r12, r13);
        r11.setArguments(r0);
        r12 = r10.mContext;
        r12 = (com.gaana.GaanaActivity) r12;
        r12.displayFragment(r11);
        return;
    L_0x0144:
        r13 = r10.mContext;
        r13 = com.utilities.Util.j(r13);
        if (r13 != 0) goto L_0x015a;
    L_0x014c:
        r11 = com.managers.aj.a();
        r12 = r10.mContext;
        r13 = r10.getString(r14);
        r11.a(r12, r13);
        return;
    L_0x015a:
        r13 = r10.mContext;
        r13 = com.managers.af.a(r13, r1);
        r14 = r10.mContext;
        r14 = r14.getResources();
        r12 = r14.getString(r12);
        r14 = new com.fragments.MyMusicFragment$11;
        r14.<init>(r0);
        r13.a(r11, r1, r12, r14);
        goto L_0x03d9;
    L_0x0174:
        r11 = r10.mAppState;
        r12 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.LOCAL;
        r12 = r12.name();
        r11.setPlayoutSectionName(r12);
        r11 = com.managers.u.a();
        r12 = "MyMusicScreen";
        r13 = "Local Music Click";
        r11.b(r12, r13);
        r11 = r10.mContext;
        r11 = (com.gaana.GaanaActivity) r11;
        r12 = 2131296325; // 0x7f090045 float:1.8210563E38 double:1.0530002953E-314;
        r11.changeFragment(r12, r1, r1);
        goto L_0x03d9;
    L_0x0196:
        r13 = com.gaana.application.GaanaApplication.getInstance();
        r13 = r13.getCurrentUser();
        r13 = r13.getLoginStatus();
        if (r13 == 0) goto L_0x01be;
    L_0x01a4:
        r11 = r10.mAppState;
        r12 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.MYMUSIC_MUSICHUB;
        r12 = r12.name();
        r11.setPlayoutSectionName(r12);
        r11 = com.managers.u.a();
        r12 = "MyMusicScreen";
        r13 = "Music Hub Click";
        r11.b(r12, r13);
        r10.m();
        return;
    L_0x01be:
        r13 = r10.mContext;
        r13 = com.utilities.Util.j(r13);
        if (r13 != 0) goto L_0x01d4;
    L_0x01c6:
        r11 = com.managers.aj.a();
        r12 = r10.mContext;
        r13 = r10.getString(r14);
        r11.a(r12, r13);
        return;
    L_0x01d4:
        r13 = r10.mContext;
        r13 = com.managers.af.a(r13, r1);
        r14 = r10.mContext;
        r14 = r14.getResources();
        r12 = r14.getString(r12);
        r14 = new com.fragments.MyMusicFragment$4;
        r14.<init>();
        r13.a(r11, r1, r12, r14);
        goto L_0x03d9;
    L_0x01ee:
        r13 = com.gaana.application.GaanaApplication.getInstance();
        r13 = r13.getCurrentUser();
        r13 = r13.getLoginStatus();
        if (r13 == 0) goto L_0x0216;
    L_0x01fc:
        r11 = r10.mAppState;
        r12 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.MYMUSIC_ARTIST;
        r12 = r12.name();
        r11.setPlayoutSectionName(r12);
        r11 = com.managers.u.a();
        r12 = "MyMusicScreen";
        r13 = "Artists Click";
        r11.b(r12, r13);
        r10.k();
        return;
    L_0x0216:
        r13 = r10.mContext;
        r13 = com.utilities.Util.j(r13);
        if (r13 != 0) goto L_0x022c;
    L_0x021e:
        r11 = com.managers.aj.a();
        r12 = r10.mContext;
        r13 = r10.getString(r14);
        r11.a(r12, r13);
        return;
    L_0x022c:
        r13 = r10.mContext;
        r13 = com.managers.af.a(r13, r1);
        r14 = r10.mContext;
        r14 = r14.getResources();
        r12 = r14.getString(r12);
        r14 = new com.fragments.MyMusicFragment$2;
        r14.<init>();
        r13.a(r11, r1, r12, r14);
        goto L_0x03d9;
    L_0x0246:
        r13 = com.gaana.application.GaanaApplication.getInstance();
        r13 = r13.getCurrentUser();
        r13 = r13.getLoginStatus();
        if (r13 == 0) goto L_0x0281;
    L_0x0254:
        r11 = r10.mAppState;
        r12 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.MYMUSIC_ALBUMS;
        r12 = r12.name();
        r11.setPlayoutSectionName(r12);
        r11 = com.managers.u.a();
        r12 = "MyMusicScreen";
        r13 = "Albums Click";
        r11.b(r12, r13);
        r11 = new com.fragments.MyMusicItemFragment;
        r11.<init>();
        r12 = "obj_type";
        r13 = com.managers.URLManager.BusinessObjectType.Albums;
        r0.putSerializable(r12, r13);
        r11.setArguments(r0);
        r12 = r10.mContext;
        r12 = (com.gaana.GaanaActivity) r12;
        r12.displayFragment(r11);
        return;
    L_0x0281:
        r13 = r10.mContext;
        r13 = com.utilities.Util.j(r13);
        if (r13 != 0) goto L_0x0297;
    L_0x0289:
        r11 = com.managers.aj.a();
        r12 = r10.mContext;
        r13 = r10.getString(r14);
        r11.a(r12, r13);
        return;
    L_0x0297:
        r13 = r10.mContext;
        r13 = com.managers.af.a(r13, r1);
        r14 = r10.mContext;
        r14 = r14.getResources();
        r12 = r14.getString(r12);
        r14 = new com.fragments.MyMusicFragment$12;
        r14.<init>(r0);
        r13.a(r11, r1, r12, r14);
        goto L_0x03d9;
    L_0x02b1:
        r0 = com.gaana.application.GaanaApplication.getInstance();
        r0 = r0.getCurrentUser();
        r0 = r0.getLoginStatus();
        if (r0 == 0) goto L_0x02fd;
    L_0x02bf:
        r11 = r10.mAppState;
        r12 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.FAVORITES;
        r12 = r12.name();
        r11.setPlayoutSectionName(r12);
        r11 = new com.fragments.FavoritesFragment;
        r11.<init>();
        r12 = new android.os.Bundle;
        r12.<init>();
        r11.setArguments(r12);
        r0 = com.managers.an.a();
        r1 = "click";
        r2 = "ac";
        r3 = "";
        r12 = com.managers.an.a();
        r4 = r12.a(r13);
        r5 = "";
        r6 = "fav";
        r7 = "";
        r8 = "";
        r0.a(r1, r2, r3, r4, r5, r6, r7, r8);
        r12 = r10.mContext;
        r12 = (com.gaana.GaanaActivity) r12;
        r12.displayFragment(r11);
        goto L_0x03d9;
    L_0x02fd:
        r13 = r10.mContext;
        r13 = com.utilities.Util.j(r13);
        if (r13 != 0) goto L_0x0314;
    L_0x0305:
        r11 = com.managers.aj.a();
        r12 = r10.mContext;
        r13 = r10.getString(r14);
        r11.a(r12, r13);
        goto L_0x03d9;
    L_0x0314:
        r13 = r10.mContext;
        r13 = com.managers.af.a(r13, r1);
        r14 = r10.mContext;
        r14 = r14.getResources();
        r12 = r14.getString(r12);
        r14 = new com.fragments.MyMusicFragment$5;
        r14.<init>();
        r13.a(r11, r1, r12, r14);
        goto L_0x03d9;
    L_0x032e:
        r11 = r10.mAppState;
        r12 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.DOWNLOADS;
        r12 = r12.name();
        r11.setPlayoutSectionName(r12);
        r11 = com.managers.u.a();
        r12 = "MyMusicScreen";
        r1 = "Downloads Click";
        r11.b(r12, r1);
        r11 = com.gaana.application.GaanaApplication.getInstance();
        r11 = r11.getCurrentUser();
        r11 = r11.getLoginStatus();
        r12 = 0;
        r1 = 1;
        if (r11 != 0) goto L_0x0356;
    L_0x0354:
        r12 = r1;
        goto L_0x037f;
    L_0x0356:
        r11 = com.managers.ap.a();
        r11 = r11.l();
        if (r11 == 0) goto L_0x0361;
    L_0x0360:
        goto L_0x037f;
    L_0x0361:
        r11 = com.managers.DownloadManager.c();
        r11 = r11.B();
        r2 = com.managers.DownloadManager.c();
        r2 = r2.p();
        if (r11 > 0) goto L_0x037f;
    L_0x0373:
        if (r2 > 0) goto L_0x037f;
    L_0x0375:
        r11 = com.managers.ap.a();
        r11 = r11.d();
        if (r11 == 0) goto L_0x0354;
    L_0x037f:
        if (r12 == 0) goto L_0x03a4;
    L_0x0381:
        r11 = r10.mContext;
        r11 = com.utilities.Util.j(r11);
        if (r11 != 0) goto L_0x0397;
    L_0x0389:
        r11 = com.managers.aj.a();
        r12 = r10.mContext;
        r13 = r10.getString(r14);
        r11.a(r12, r13);
        return;
    L_0x0397:
        r11 = "My_Download_section";
        r12 = r10.mContext;
        r13 = new com.fragments.MyMusicFragment$3;
        r13.<init>(r0);
        com.utilities.Util.b(r12, r11, r13);
        goto L_0x03d9;
    L_0x03a4:
        r10.j = r1;
        r11 = r10.mContext;
        r11 = (com.gaana.GaanaActivity) r11;
        r11.showHideNewDownloadedSongCount();
        r11 = new com.fragments.DownloadDetailsFragment;
        r11.<init>();
        r11.setArguments(r0);
        r1 = com.managers.an.a();
        r2 = "click";
        r3 = "ac";
        r4 = "";
        r12 = com.managers.an.a();
        r5 = r12.a(r13);
        r6 = "";
        r7 = "download";
        r8 = "";
        r9 = "";
        r1.a(r2, r3, r4, r5, r6, r7, r8, r9);
        r12 = r10.mContext;
        r12 = (com.gaana.GaanaActivity) r12;
        r12.displayFragment(r11);
    L_0x03d9:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fragments.MyMusicFragment.a(int, java.lang.String, java.lang.String, java.lang.String):void");
    }

    private void n() {
        LinearLayout linearLayout = (LinearLayout) this.c.findViewById(R.id.adSlot);
        ColombiaAdViewManager.a().e();
        if (e.X == 0) {
            ColombiaAdViewManager.a().a(this.mContext, linearLayout, e.A, "MYMUSIC_BOTTOM_BANNER", new AdsUJData[0]);
        }
    }

    public void onDestroyView() {
        if (!(this.c == null || this.c.getParent() == null)) {
            ((ViewGroup) this.c.getParent()).removeView(this.c);
        }
        if (this.q != null) {
            this.q.destroy();
        }
        super.onDestroyView();
    }

    public void a(ADSTATUS adstatus) {
        this.r = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public void b(ADSTATUS adstatus) {
        this.r = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public void c(ADSTATUS adstatus) {
        this.r = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public void d(ADSTATUS adstatus) {
        this.r = adstatus;
        ColombiaAdViewManager.a(adstatus);
    }

    public String getPageName() {
        return PAGE_SORCE_NAME.MYMUSIC.name();
    }

    public void l() {
        if (this.p == null) {
            this.p = (ViewGroup) this.layoutInflater.inflate(R.layout.top_banner_ad, null);
        }
    }

    public void onFragmentScroll() {
        if (this.d != null) {
            this.d.smoothScrollToPosition(0);
        }
        if (this.x != null) {
            this.x.setExpanded(true, false);
        }
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

    public void c() {
        if (this.c != null) {
            this.c.findViewById(R.id.adSlot).setVisibility(8);
        }
    }
}
