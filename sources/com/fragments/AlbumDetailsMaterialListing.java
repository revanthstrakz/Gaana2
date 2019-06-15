package com.fragments;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.RecyclerListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.actionbar.DetailsMaterialActionBar;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.android.volley.i.c;
import com.collapsible_header.ObservableRecyclerView;
import com.collapsible_header.ScrollState;
import com.collapsible_header.d;
import com.collapsible_header.i;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.adapter.CustomListAdapter.IAddListItemView;
import com.gaana.adapter.TagsAdapter;
import com.gaana.adapter.TagsAdapter.OnTagClickListener;
import com.gaana.analytics.AppsFlyer;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.AdsUJData;
import com.gaana.models.Albums;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.gaana.models.Tracks.Track.Tags;
import com.gaana.models.Tracks.Track.TopArtists;
import com.gaana.models.Tracks.Track.TopLanguage;
import com.gaana.models.UserMessage;
import com.gaana.view.ExpandableListAdapter;
import com.gaana.view.item.AlbumItemView;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.BaseItemView.DetailListingItemHolder;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.ItemEmptyMessageHolder;
import com.gaana.view.item.BaseItemView.SpinnerHolder;
import com.gaana.view.item.BaseItemView.SponsorAdViewHolder;
import com.gaana.view.item.DownloadSongsItemView.AlbumDetailItemHolder;
import com.gaana.view.item.PopupWindowView;
import com.gaana.view.item.SimilarItemHorizontalScroll;
import com.gaanavideo.VideoCoachmarkActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.i.j;
import com.library.controls.CrossFadeImageView;
import com.library.managers.TaskManager.TaskListner;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaManager;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.aj;
import com.managers.al;
import com.managers.ap;
import com.managers.ap.a;
import com.managers.n;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.services.f;
import com.services.h;
import com.services.l.as;
import com.services.l.r;
import com.services.l.s;
import com.til.colombia.android.service.Item;
import com.utilities.Util;
import com.utilities.e;
import com.views.ColumbiaAdItemview;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AlbumDetailsMaterialListing extends BaseGaanaFragment implements OnRefreshListener, OnClickListener, c, d, IAddListItemView, OnTagClickListener, ColombiaAdViewManager.c, a {
    private static float l = 2.0f;
    private static float m = 1.2f;
    private ProgressBar A;
    private View B = null;
    private int C;
    private View D;
    private View E;
    private View F;
    private TextView G;
    private TextView H;
    private ArrayList<BusinessObject> I = new ArrayList();
    private ArrayList<BusinessObject> J = new ArrayList();
    private String K = null;
    private String L = "";
    private View M;
    private int N = 0;
    private boolean O = false;
    private long P;
    private String Q = "";
    private String R = "";
    private DisplayMetrics S;
    private boolean T = false;
    private MenuItem U;
    private boolean V = false;
    private ColumbiaAdItemview W = null;
    private Spinner X;
    private SponsorAdViewHolder Y;
    private boolean Z;
    LinearLayout a = null;
    private String aA = "";
    private String aB = "";
    private ArrayList<Track> aC = null;
    private boolean aD = false;
    private boolean aE = true;
    private PublisherAdView aF;
    private LayoutParams aG = new LayoutParams(-2, -2);
    private int aH;
    private int aI = 0;
    private View aa;
    private Menu ab;
    private int ac = 0;
    private View ad;
    private View ae;
    private View af;
    private TextView ag;
    private ImageView ah;
    private View ai;
    private int aj = 0;
    private LinearLayout ak;
    private LinearLayout al;
    private ArrayList<String> am;
    private ArrayList<Bitmap> an;
    private int ao;
    private ItemTouchHelper ap;
    private RecyclerView aq;
    private TagsAdapter ar;
    private ArrayList<Track> as;
    private ArrayList<Tags> at;
    private ArrayList<TopArtists> au;
    private ArrayList<BusinessObject> av;
    private ArrayList<TopLanguage> aw;
    private Bundle ax = null;
    private String ay = "";
    private String az = "0";
    Callback b;
    LinearLayoutManager c;
    int d = -1;
    boolean e = false;
    ArrayList<Track> f;
    ExpandableListAdapter g;
    boolean h = false;
    public boolean i;
    ArrayList<Integer> j = new ArrayList();
    s k = new s() {
        public void onErrorResponse(BusinessObject businessObject) {
        }

        public void onRetreivalComplete(BusinessObject businessObject) {
            if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() != 0) {
                AlbumDetailsMaterialListing.this.a(businessObject);
            }
        }
    };
    private View n = null;
    private ListingComponents o;
    private BusinessObject p;
    private ObservableRecyclerView q;
    private CrossFadeImageView r;
    private CustomListAdapter s;
    private BaseItemView t;
    private SwipeRefreshLayout u;
    private boolean v = false;
    private int w = 0;
    private View x;
    private DetailsMaterialActionBar y;
    private Toolbar z;

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

    private boolean a(Bundle bundle) {
        if (bundle != null) {
            this.p = (BusinessObject) bundle.getSerializable("BUSINESS_OBJECT");
            if (this.p != null) {
                this.mAppState.setGADParameter(this.p.getBusinessObjId());
                Iterator it;
                ListingButton listingButton;
                StringBuilder stringBuilder;
                StringBuilder stringBuilder2;
                if (this.p.getBusinessObjType() == BusinessObjectType.HomeAction) {
                    this.o = Constants.f();
                    it = this.o.c().iterator();
                    while (it.hasNext()) {
                        listingButton = (ListingButton) it.next();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(listingButton.c().k());
                        stringBuilder.append(this.p.getBusinessObjId());
                        listingButton.c().a(stringBuilder.toString());
                    }
                } else if (this.p instanceof Playlist) {
                    this.o = Constants.e();
                    it = this.o.c().iterator();
                    while (it.hasNext()) {
                        listingButton = (ListingButton) it.next();
                        if (this.p.isLocalMedia()) {
                            listingButton.c().d(this.p.isLocalMedia());
                        } else {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(listingButton.c().k());
                            stringBuilder.append("playlist_id=");
                            stringBuilder.append(this.p.getBusinessObjId());
                            stringBuilder.append("&playlist_type=");
                            stringBuilder.append(((Playlist) this.p).getPlaylistType());
                            String stringBuilder3 = stringBuilder.toString();
                            if (((Playlist) this.p).getAutomated() != null && ((Playlist) this.p).getAutomated().equalsIgnoreCase("1")) {
                                StringBuilder stringBuilder4 = new StringBuilder();
                                stringBuilder4.append(stringBuilder3);
                                stringBuilder4.append("&automated=1");
                                stringBuilder3 = stringBuilder4.toString();
                            }
                            listingButton.c().a(stringBuilder3);
                            if (DownloadManager.c().b(this.p).booleanValue()) {
                                listingButton.e(true);
                            }
                        }
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(Util.c(this.p.getBusinessObjType()));
                        stringBuilder2.append(this.p.getBusinessObjId());
                        AppsFlyer.getInstance().reportViewContent(this.p.getEnglishName(), "Playlist", stringBuilder2.toString());
                        q();
                    }
                } else if (this.p instanceof Album) {
                    this.o = Constants.b();
                    it = this.o.c().iterator();
                    while (it.hasNext()) {
                        listingButton = (ListingButton) it.next();
                        if (this.p.isLocalMedia()) {
                            listingButton.c().d(this.p.isLocalMedia());
                        } else {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(listingButton.c().k());
                            stringBuilder.append(this.p.getBusinessObjId());
                            listingButton.c().a(stringBuilder.toString());
                            listingButton.c().d(this.p.isLocalMedia());
                            if (this.p.isLocalMedia() || DownloadManager.c().b(this.p).booleanValue()) {
                                listingButton.e(true);
                            }
                        }
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(Util.c(this.p.getBusinessObjType()));
                        stringBuilder2.append(this.p.getBusinessObjId());
                        AppsFlyer.getInstance().reportViewContent(this.p.getEnglishName(), "Album", stringBuilder2.toString());
                        q();
                    }
                }
                this.o.b(this.p.getName());
                this.o.a(this.p);
                this.mAppState.setListingComponents(this.o);
                a((ListingButton) this.o.c().get(0));
                r();
                E();
                return true;
            }
        }
        ((GaanaActivity) this.mContext).popBackStackImmediate();
        return false;
    }

    private void q() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                if (!AlbumDetailsMaterialListing.this.i && !com.services.d.a().b("PREFERENCE_KEY_LONG_PRESS_INITIATED", false, false)) {
                    int b = com.services.d.a().b("LONG_PRESS_ON_TRACK_COUNT", 0, false);
                    if (b < 2) {
                        int b2 = com.services.d.a().b("SESSION_OCCURENCE_LONG_PRESS_COACHMARK", 0, false);
                        int i = b2 + 10;
                        if (b2 > 0) {
                            if (GaanaApplication.sessionHistoryCount + 1 >= i) {
                                AlbumDetailsMaterialListing.this.a(b);
                            }
                        } else if (b == 0 && GaanaApplication.sessionHistoryCount + 1 >= 9) {
                            AlbumDetailsMaterialListing.this.a(b);
                        }
                    }
                }
            }
        }, 200);
    }

    private void a(int i) {
        com.services.d.a().a("LONG_PRESS_ON_TRACK_COUNT", i + 1, false);
        com.services.d.a().a("SESSION_OCCURENCE_LONG_PRESS_COACHMARK", GaanaApplication.sessionHistoryCount, false);
        Intent intent = new Intent(this.mContext, VideoCoachmarkActivity.class);
        intent.putExtra("COACHMARK_VALUE", "LONG_PRESS_ON_TRACK");
        startActivityForResult(intent, PointerIconCompat.TYPE_ALIAS);
        getActivity().overridePendingTransition(17432576, 17432577);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.ax = null;
        if (this.p != null) {
            this.p.setArrListBusinessObj(null);
            bundle.putSerializable("BUSINESS_OBJECT", this.p);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.T = false;
        this.S = new DisplayMetrics();
        this.ax = bundle;
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.S);
        if (this.n == null) {
            boolean a;
            this.P = Calendar.getInstance().getTimeInMillis();
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.n = setContentView(R.layout.album_details_material_listing, viewGroup);
            this.K = getArguments().getString("DEEPLINKING_SCREEN_EXTRA_PARAM");
            if (bundle == null) {
                a = a(getArguments());
            } else {
                a = a(bundle);
            }
            if (a) {
                this.A.setVisibility(0);
                x();
                if (this.p != null) {
                    t();
                }
            } else {
                ((GaanaActivity) this.mContext).popBackStack();
            }
        } else {
            this.mAppState.setListingComponents(this.o);
            if (this.I != null) {
                this.mAppState.setCurrentBusObjInListView(this.I);
            }
            if (((GaanaActivity) this.mContext).getRefreshData()) {
                ((GaanaActivity) this.mContext).setRefreshData(false);
                d();
            } else if (!(this.q == null || this.q.getAdapter() == null)) {
                this.q.getAdapter().notifyDataSetChanged();
            }
            b();
        }
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.action_bar_overlay_attr});
        this.d = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        if (this.c == null) {
            this.c = new LinearLayoutManager(getContext().getApplicationContext());
        }
        this.q.setLayoutManager(this.c);
        ((GaanaActivity) this.mContext).hideThemeBackground(false);
        if (this.u == null) {
            ((GaanaActivity) this.mContext).popBackStack();
        } else if (this.v) {
            this.u.setRefreshing(true);
        } else {
            this.u.setRefreshing(false);
        }
        if (this.p != null) {
            this.TITLE = this.p.getEnglishName();
            String str = "";
            StringBuilder stringBuilder;
            StringBuilder stringBuilder2;
            if (this.p instanceof Playlist) {
                Playlist playlist = (Playlist) this.p;
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/playlist/");
                stringBuilder.append(playlist.getSeokey());
                this.R = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/playlist/");
                stringBuilder.append(playlist.getSeokey());
                this.Q = stringBuilder.toString();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("PlaylistDetailScreen:");
                stringBuilder2.append(this.TITLE);
                str = stringBuilder2.toString();
            } else if (this.p instanceof Album) {
                Album album = (Album) this.p;
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/album/");
                stringBuilder.append(album.getSeokey());
                this.R = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/album/");
                stringBuilder.append(album.getSeokey());
                this.Q = stringBuilder.toString();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("AlbumDetailScreen:");
                stringBuilder2.append(this.TITLE);
                str = stringBuilder2.toString();
            } else if (this.p instanceof Artist) {
                Artist artist = (Artist) this.p;
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/artist/");
                stringBuilder.append(artist.getSeokey());
                this.R = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/artist/");
                stringBuilder.append(artist.getSeokey());
                this.Q = stringBuilder.toString();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("ArtistDetailScreen:");
                stringBuilder2.append(this.TITLE);
                str = stringBuilder2.toString();
            }
            setGAScreenName(str, str);
        }
        this.currentUJPage = "ALBUMDETAILS";
        return this.n;
    }

    public void onStart() {
        super.onStart();
        o();
    }

    public void onResume() {
        super.onResume();
        ((GaanaActivity) this.mContext).setFragment(this);
        if (this.loginStatus != this.mAppState.getCurrentUser().getLoginStatus()) {
            x();
            E();
            this.loginStatus = this.mAppState.getCurrentUser().getLoginStatus();
        }
        if (ap.a().b(this.mContext)) {
            ColombiaAdViewManager.a().a((ColombiaAdViewManager.c) this);
        }
        if (this.aF != null) {
            this.aF.resume();
        }
        if (!Constants.ab || this.p == null || this.p.isLocalMedia()) {
            ((TextView) this.n.findViewById(R.id.text_play_all)).setText(getString(R.string.play_all));
            ((ImageView) this.n.findViewById(R.id.img_play_all)).setImageResource(R.drawable.vector_player_play_white);
            return;
        }
        ((TextView) this.n.findViewById(R.id.text_play_all)).setText(getString(R.string.opt_shuffle_play));
        ((ImageView) this.n.findViewById(R.id.img_play_all)).setImageResource(R.drawable.vector_shuffle_white);
    }

    public void onPause() {
        if (ap.a().b(this.mContext)) {
            ColombiaAdViewManager.a().a(null);
        }
        if (this.aF != null) {
            this.aF.pause();
        }
        super.onPause();
    }

    private void r() {
        this.C = this.mContext.getResources().getDimensionPixelSize(R.dimen.header_height);
        this.u = (SwipeRefreshLayout) this.n.findViewById(R.id.swipe_refresh_layout);
        this.u.setOnRefreshListener(this);
        this.q = (ObservableRecyclerView) this.n.findViewById(R.id.scroll);
        this.r = (CrossFadeImageView) this.n.findViewById(R.id.details_artwork);
        this.aq = (RecyclerView) this.n.findViewById(R.id.tags_recyclerview);
        this.E = this.n.findViewById(R.id.overlay);
        this.ai = this.n.findViewById(R.id.ll_album_second_line);
        this.G = (TextView) this.ai.findViewById(R.id.album_title);
        this.H = (TextView) this.ai.findViewById(R.id.tvAlbumSongCount_Value);
        this.q.setScrollViewCallbacks(this);
        this.q.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.q.setHasFixedSize(false);
        this.q.setRecyclerListener(new RecyclerListener() {
            public void onViewRecycled(ViewHolder viewHolder) {
                if (viewHolder instanceof AlbumDetailItemHolder) {
                    ((AlbumDetailItemHolder) viewHolder).mCrossFadeImageIcon.onViewRecycled();
                }
            }
        });
        this.M = this.n.findViewById(R.id.button_padding);
        this.ad = this.n.findViewById(R.id.ll_download_container);
        this.ae = this.ad.findViewById(R.id.btn_play_all);
        this.ae.setOnClickListener(this);
        this.af = this.ad.findViewById(R.id.btn_download_all);
        this.af.setOnClickListener(this);
        this.ah = (ImageView) this.af.findViewById(R.id.img_download_all);
        this.ag = (TextView) this.af.findViewById(R.id.txt_download_all);
        this.D = LayoutInflater.from(this.mContext).inflate(R.layout.recycler_header, null);
        this.D.setLayoutParams(new LayoutParams(-1, this.C));
        this.ak = (LinearLayout) this.n.findViewById(R.id.ll_song_fav_count);
        this.D.post(new Runnable() {
            public void run() {
                AlbumDetailsMaterialListing.this.D.getLayoutParams().height = AlbumDetailsMaterialListing.this.C;
            }
        });
        this.s = new CustomListAdapter(this.mContext, this.D);
        this.s.setParamaters(0, this);
        this.q.setAdapter(this.s);
        this.b = new e(this.s);
        this.ap = new ItemTouchHelper(this.b);
        this.ap.attachToRecyclerView(this.q);
        this.al = (LinearLayout) this.n.findViewById(R.id.ll_fav_parent);
        this.z = (Toolbar) this.n.findViewById(R.id.main_toolbar);
        this.F = this.n.findViewById(R.id.toolbar_dummy_view);
        this.z.setContentInsetsAbsolute(0, 0);
        s();
        this.x = this.af;
        this.y = new DetailsMaterialActionBar(this.mContext);
        this.z.addView(this.y);
        this.y.setParams(this, this.p);
        this.y.showContextMenu(false);
        setmToolbar(this.z);
        ((TextView) this.y.findViewById(R.id.title)).setText("");
        this.y.setToolbar(this.z);
        this.aj = Util.b(12);
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.parallax_header_title_color});
        ((TextView) this.y.findViewById(R.id.title)).setTextColor(obtainStyledAttributes.getColor(0, -1));
        obtainStyledAttributes.recycle();
        this.ac = a();
        this.A = (ProgressBar) this.n.findViewById(R.id.progressbar);
        this.G.setText(Constants.a(this.p.getName(), this.p.getLanguage()));
        this.G.setTypeface(this.G.getTypeface(), 1);
        i.g(this.E, (float) this.C);
        this.ak.post(new Runnable() {
            public void run() {
                AlbumDetailsMaterialListing.this.ak.setTranslationY((float) ((int) ((((float) AlbumDetailsMaterialListing.this.C) - (((float) AlbumDetailsMaterialListing.this.ak.getHeight()) * 1.2f)) - ((float) AlbumDetailsMaterialListing.this.M.getHeight()))));
                AlbumDetailsMaterialListing.this.ak.setPivotX(0.0f);
                AlbumDetailsMaterialListing.this.ak.setPivotY(0.0f);
                AlbumDetailsMaterialListing.this.G.setTranslationY((float) ((int) ((((float) ((AlbumDetailsMaterialListing.this.C - AlbumDetailsMaterialListing.this.G.getHeight()) - AlbumDetailsMaterialListing.this.mContext.getResources().getDimensionPixelSize(R.dimen.artist_tab_padding_fab))) - (((float) AlbumDetailsMaterialListing.this.ak.getHeight()) * 1.2f)) - ((float) (AlbumDetailsMaterialListing.this.M.getHeight() / 2)))));
                AlbumDetailsMaterialListing.this.M.setTranslationY((float) (AlbumDetailsMaterialListing.this.C - AlbumDetailsMaterialListing.this.M.getHeight()));
                AlbumDetailsMaterialListing.this.F.setAlpha(0.0f);
            }
        });
        if (this.p.isLocalMedia()) {
            this.af.setVisibility(8);
            if (this.p.isLocalMedia()) {
                LayoutParams layoutParams = new LayoutParams(this.S.widthPixels / 2, -1);
                layoutParams.gravity = 1;
                this.ae.setLayoutParams(layoutParams);
            }
        }
        if (this.p.getBusinessObjType() == BusinessObjectType.HomeAction) {
            this.ae.setVisibility(8);
            this.af.setVisibility(8);
            this.ab.findItem(R.id.menu_favourite).setVisible(false);
        }
        if (VERSION.SDK_INT >= 21) {
            this.E.setElevation((float) Util.b(20));
            this.ak.setElevation((float) Util.b(20));
            this.ai.setElevation((float) Util.b(20));
            this.ad.setElevation((float) Util.b(20));
            this.z.setElevation((float) Util.b(20));
        }
        GaanaApplication.getInstance().setPlayoutSectionName(getSectionName());
    }

    public void a(boolean z) {
        this.u.setEnabled(z);
    }

    private void s() {
        this.z.getMenu().clear();
        this.z.inflateMenu(R.menu.cast_menu_detail);
        ((BaseActivity) this.mContext).initializeMediaRouterButton(this.z.getMenu(), R.id.media_route_menu_item);
        this.ab = this.z.getMenu();
        if (this.ab != null) {
            this.U = this.ab.findItem(R.id.media_route_menu_item);
            this.ab.findItem(R.id.menu_download).setVisible(false);
        }
        b();
    }

    private void t() {
        String artwork = this.p instanceof Playlist ? ((Playlist) this.p).getArtwork() : this.p instanceof Album ? ((Album) this.p).getArtwork() : null;
        if (artwork != null) {
            artwork = Util.e(this.mContext, artwork);
        }
        if (this.p.isLocalMedia()) {
            this.r.bindImageForLocalMedia(artwork, null, new LocalMediaImageLoader(), false);
        } else if ((this.p instanceof Playlist) && ((Playlist) this.p).getAutoDisplayHome()) {
            i();
        } else {
            if (Constants.dC.equalsIgnoreCase("2G")) {
                if (artwork != null && artwork.contains("80x80")) {
                    artwork = artwork.replace("80x80", "175x175");
                }
            } else if (artwork != null && artwork.contains("80x80")) {
                artwork = artwork.replace("80x80", "480x480");
            } else if (artwork != null && artwork.contains("175x175")) {
                artwork = artwork.replace("175x175", "480x480");
            }
            try {
                com.i.i.a().a(artwork, new r() {
                    public void onSuccessfulResponse(Bitmap bitmap) {
                        if (AlbumDetailsMaterialListing.this.isAdded() && bitmap != null) {
                            AlbumDetailsMaterialListing.this.r.setImageBitmap(bitmap);
                        } else if (AlbumDetailsMaterialListing.this.isAdded()) {
                            AlbumDetailsMaterialListing.this.u();
                        }
                    }

                    public void onErrorResponse(VolleyError volleyError) {
                        if (AlbumDetailsMaterialListing.this.isAdded()) {
                            AlbumDetailsMaterialListing.this.u();
                        }
                    }
                });
            } catch (OutOfMemoryError unused) {
                u();
            }
            i();
        }
    }

    private void u() {
        String str = "";
        if (this.p instanceof Playlist) {
            str = ((Playlist) this.p).getArtwork();
        } else if (this.p instanceof Album) {
            str = ((Album) this.p).getArtwork();
        }
        if (str != null && str.contains("480x480")) {
            str = str.replace("480x480", "175x175");
        }
        if (str != null && str.contains("80x80")) {
            str = str.replace("80x80", "175x175");
        }
        com.i.i.a().a(str, new r() {
            public void onErrorResponse(VolleyError volleyError) {
            }

            public void onSuccessfulResponse(Bitmap bitmap) {
                AlbumDetailsMaterialListing.this.a(bitmap, AlbumDetailsMaterialListing.this.r);
            }
        });
    }

    private void a(final Bitmap bitmap, final ImageView imageView) {
        if (isAdded() && bitmap != null) {
            h.a().a(new TaskListner() {
                private Bitmap d;

                public void doBackGroundTask() {
                    this.d = Util.a(bitmap, 30);
                }

                public void onBackGroundTaskCompleted() {
                    if (AlbumDetailsMaterialListing.this.isAdded()) {
                        int dimensionPixelSize = AlbumDetailsMaterialListing.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp160);
                        int i = (AlbumDetailsMaterialListing.this.S.widthPixels - dimensionPixelSize) / 2;
                        Bitmap createBitmap = Bitmap.createBitmap(AlbumDetailsMaterialListing.this.S.widthPixels, AlbumDetailsMaterialListing.this.S.widthPixels, bitmap.getConfig());
                        Canvas canvas = new Canvas(createBitmap);
                        dimensionPixelSize += i;
                        Rect rect = new Rect(i, i, dimensionPixelSize, dimensionPixelSize);
                        Rect rect2 = new Rect(0, 0, AlbumDetailsMaterialListing.this.S.widthPixels, AlbumDetailsMaterialListing.this.S.widthPixels);
                        if (this.d != null) {
                            canvas.drawBitmap(this.d, null, rect2, null);
                        }
                        canvas.drawBitmap(bitmap, null, rect, null);
                        imageView.setImageBitmap(createBitmap);
                    }
                }
            }, imageView.getId());
        }
    }

    public void refreshListView() {
        super.refreshListView();
        if (this.mContext != null) {
            com.views.i slidingPanelLayout = ((GaanaActivity) this.mContext).getSlidingPanelLayout();
            if (slidingPanelLayout == null || slidingPanelLayout.a() != 1) {
                v();
            }
        }
    }

    private void v() {
        if (this.p != null) {
            if (this.s != null) {
                this.s.notifyDataSetChanged();
            }
            if (!this.p.isLocalMedia()) {
                i();
            }
        }
    }

    public void refreshListView(BusinessObjectType businessObjectType) {
        v();
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        v();
    }

    public void refreshFavoriteCount(boolean z) {
        if (!this.p.isLocalMedia()) {
            String stringBuilder;
            super.refreshFavoriteCount(z);
            String str = "";
            if (this.p instanceof Album) {
                str = ((Album) this.p).getFavoriteCount();
            } else if (this.p instanceof Playlist) {
                str = ((Playlist) this.p).getFavoriteCount();
            }
            if (!TextUtils.isEmpty(str)) {
                str = str.replaceAll("[^0-9?!\\.]", "");
            }
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            StringBuilder stringBuilder2;
            if (z) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(Integer.parseInt(str) + 1);
                stringBuilder2.append("");
                stringBuilder = stringBuilder2.toString();
            } else {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(Integer.parseInt(str) - 1);
                stringBuilder2.append("");
                stringBuilder = stringBuilder2.toString();
            }
            if (this.p instanceof Album) {
                ((Album) this.p).setFavoriteCount(stringBuilder);
            } else if (this.p instanceof Playlist) {
                ((Playlist) this.p).setFavoriteCount(stringBuilder);
            }
            if (!(this.p.isLocalMedia() || DownloadManager.c().h(Integer.parseInt(this.p.getBusinessObjId())) == null || str == null || stringBuilder == null || str.equals(stringBuilder))) {
                DownloadManager.c().a(this.p.getBusinessObjId(), this.p);
            }
            w();
            refreshListView();
        }
    }

    /* Access modifiers changed, original: protected */
    public int a() {
        int[] iArr = new int[]{R.attr.actionBarSize};
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new TypedValue().data, iArr);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    private void w() {
        StringBuilder stringBuilder;
        String count = this.p.getCount();
        this.al.removeAllViews();
        if (this.p instanceof Album) {
            this.az = ((Album) this.p).getFavoriteCount();
        } else if (this.p instanceof Playlist) {
            this.az = ((Playlist) this.p).getFavoriteCount();
        }
        if (TextUtils.isEmpty(count)) {
            count = "0";
        }
        if (Integer.parseInt(count.trim()) < 2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(Util.q(count));
            stringBuilder.append(" ");
            stringBuilder.append(this.mContext.getString(R.string.song_text));
            this.aA = stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(Util.q(count));
            stringBuilder.append(" ");
            stringBuilder.append(this.mContext.getString(R.string.songs_text));
            this.aA = stringBuilder.toString();
        }
        if (TextUtils.isEmpty(this.az) || Integer.parseInt(this.az.trim()) <= 0) {
            this.H.setText(this.aA);
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.aA);
            stringBuilder2.append(" | ");
            this.aA = stringBuilder2.toString();
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(Util.q(this.az));
            stringBuilder2.append(" ");
            stringBuilder2.append(this.mContext.getString(R.string.fav));
            this.ay = stringBuilder2.toString();
            TextView textView = this.H;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.aA);
            stringBuilder.append(this.ay);
            textView.setText(stringBuilder.toString());
        }
        boolean z = false;
        if (this.p instanceof Playlist) {
            z = ((Playlist) this.p).isParentalWarningEnabled();
        } else if (this.p instanceof Album) {
            z = ((Album) this.p).isParentalWarningEnabled();
        }
        if (z) {
            this.H.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(Util.Y()), null, null, null);
        } else {
            this.H.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    public void b() {
        if (this.z != null) {
            Menu menu = this.z.getMenu();
            if (menu != null) {
                MenuItem findItem = menu.findItem(R.id.menu_favourite);
                ImageView imageView = (ImageView) findItem.getActionView();
                TypedArray obtainStyledAttributes;
                if (findItem != null && this.p.isLocalMedia()) {
                    findItem.setVisible(false);
                } else if (findItem != null && (this.p instanceof Playlist) && ((Playlist) this.p).getAutomated() != null && ((Playlist) this.p).getAutomated().equalsIgnoreCase("1")) {
                    findItem.setVisible(false);
                } else if (n.a().a(this.p)) {
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(51, -1)));
                    obtainStyledAttributes.recycle();
                } else {
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(49, -1)));
                    obtainStyledAttributes.recycle();
                }
            }
        }
    }

    private void x() {
        this.P = Calendar.getInstance().getTimeInMillis();
        ListingButton listingButton = (ListingButton) this.o.c().get(0);
        URLManager c = listingButton.c();
        c.c(Boolean.valueOf(this.v));
        if (this.p.isLocalMedia()) {
            ((BaseActivity) this.mContext).getDownloadedBusinessObject((b) this, this.p.getBusinessObjId(), c);
        } else if (listingButton.l() && !this.v) {
            ((BaseActivity) this.mContext).getDownloadedBusinessObject((b) this, this.p.getBusinessObjId(), c);
        } else if ((this.p instanceof Playlist) && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) this.p) && !((Playlist) this.p).getAutoDisplayHome()) {
            ((ListingButton) this.o.c().get(0)).c().c(Boolean.valueOf(this.v));
            ((BaseActivity) this.mContext).getMyPlaylistDetails((b) this, (Playlist) this.p, ((ListingButton) this.o.c().get(0)).c());
        } else {
            com.i.i.a().a(c, toString(), null, this, this);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(ListingButton listingButton) {
        try {
            this.t = (BaseItemView) Class.forName(listingButton.f()).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.mContext, this});
        } catch (Exception unused) {
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public BusinessObject c() {
        return this.p;
    }

    public void d() {
        if (!(this.p == null || TextUtils.isEmpty(this.p.getName()))) {
            this.G.setText(Constants.a(this.p.getName(), this.p.getLanguage()));
        }
        if (this.o != null && this.o.c() != null) {
            x();
            E();
        }
    }

    public void onStop() {
        p();
        if (al.a) {
            k();
        }
        e();
        super.onStop();
    }

    public void e() {
        ArrayList arrListBusinessObj = this.p.getArrListBusinessObj();
        if (arrListBusinessObj != null) {
            Object obj;
            Iterator it = arrListBusinessObj.iterator();
            while (it.hasNext()) {
                if (((Track) it.next()).isAddedToPlaylist()) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj != null) {
                PlaylistSyncManager.getInstance().addSongsInPlaylist(this.p, this.mContext);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (i == 0) {
            return viewHolder.itemView;
        }
        if (i == 1) {
            if (!this.V) {
                this.V = true;
            }
            ((SpinnerHolder) viewHolder).setVisibility(true);
            return viewHolder.itemView;
        } else if (i == (this.w + 2) + this.j.size()) {
            this.aa = viewHolder.itemView;
            if (!this.Z || Constants.cM == 0 || this.J.size() == 0 || !Util.j(this.mContext)) {
                c(false);
                return this.aa;
            }
            if (this.Z && this.as == null) {
                a((RelativeLayout) this.aa);
            }
            return this.aa;
        } else if (i == (this.w + 3) + this.j.size()) {
            return viewHolder.itemView;
        } else {
            if (this.j.contains(Integer.valueOf(i))) {
                if (viewHolder != null && viewHolder.getItemViewType() >= 8 && viewHolder.getItemViewType() < this.j.size() + 8) {
                    if (this.W == null) {
                        this.W = new ColumbiaAdItemview(this.mContext, this);
                    }
                    this.W.getPopulatedView(i, viewHolder.itemView, (ViewGroup) viewHolder.itemView.getParent(), this.p);
                }
                return viewHolder.itemView;
            }
            if (!al.a) {
                viewHolder.itemView.setOnLongClickListener(new OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        AlbumDetailsMaterialListing.this.a(view, AlbumDetailsMaterialListing.this.I.size());
                        AlbumDetailsMaterialListing.this.s.notifyDataSetChanged();
                        com.services.d.a().a("PREFERENCE_KEY_LONG_PRESS_INITIATED", true, false);
                        return true;
                    }
                });
            }
            if (!this.e) {
                a(viewHolder);
            }
            return this.t.getPoplatedView(viewHolder, (BusinessObject) this.I.get(b(i) - 2), viewGroup);
        }
    }

    private void c(boolean z) {
        RecyclerView.LayoutParams layoutParams;
        if (z) {
            this.aa.setVisibility(0);
            layoutParams = (RecyclerView.LayoutParams) this.aa.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = -2;
                layoutParams.width = -1;
                this.aa.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        layoutParams = (RecyclerView.LayoutParams) this.aa.getLayoutParams();
        layoutParams.height = 0;
        layoutParams.width = 0;
        this.aa.setLayoutParams(layoutParams);
        this.aa.setVisibility(8);
    }

    private void a(final RelativeLayout relativeLayout) {
        final ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.chevronImg);
        final ObservableRecyclerView observableRecyclerView = (ObservableRecyclerView) relativeLayout.findViewById(R.id.recycler_view);
        Button button = (Button) relativeLayout.findViewById(R.id.refresh_button);
        LinearLayout linearLayout = (LinearLayout) relativeLayout.findViewById(R.id.text_layout);
        if (Constants.l) {
            button.setBackgroundResource(R.drawable.selector_btn_round_100dp_white);
        } else {
            button.setBackgroundResource(R.drawable.selector_btn_round_100dp);
        }
        button.setTypeface(null, 1);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    TypedArray obtainStyledAttributes;
                    Drawable drawable;
                    if (imageView.getTag().equals("DOWN")) {
                        obtainStyledAttributes = AlbumDetailsMaterialListing.this.mContext.obtainStyledAttributes(new int[]{R.attr.chevron_down});
                        drawable = obtainStyledAttributes.getDrawable(0);
                        obtainStyledAttributes.recycle();
                        imageView.setImageDrawable(drawable);
                        AlbumDetailsMaterialListing.this.a(relativeLayout);
                        imageView.setTag("UP");
                    } else if (imageView.getTag().equals("UP")) {
                        obtainStyledAttributes = AlbumDetailsMaterialListing.this.mContext.obtainStyledAttributes(new int[]{R.attr.chevron_up});
                        drawable = obtainStyledAttributes.getDrawable(0);
                        obtainStyledAttributes.recycle();
                        imageView.setImageDrawable(drawable);
                        AlbumDetailsMaterialListing.this.b(relativeLayout);
                        imageView.setTag("DOWN");
                    }
                }
            });
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String str = "";
                    int i = 0;
                    if (!(AlbumDetailsMaterialListing.this.aC == null || AlbumDetailsMaterialListing.this.aC.size() == 0)) {
                        int size = AlbumDetailsMaterialListing.this.aC.size();
                        int i2 = size - 1;
                        while (true) {
                            if (i2 <= (size > 5 ? size - 5 : -1)) {
                                break;
                            }
                            Track track = (Track) AlbumDetailsMaterialListing.this.aC.get(i2);
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(track.getBusinessObjId());
                            stringBuilder.append(",");
                            stringBuilder.append(str);
                            str = stringBuilder.toString();
                            i2--;
                        }
                        if (str.endsWith(",")) {
                            str = str.substring(0, str.length() - 1);
                        }
                        if (!AlbumDetailsMaterialListing.this.aB.equals(str)) {
                            i = 1;
                        }
                    }
                    if (i != 0) {
                        AlbumDetailsMaterialListing.this.aB = str;
                        AlbumDetailsMaterialListing.this.a(str, observableRecyclerView, true);
                    } else if (AlbumDetailsMaterialListing.this.f != null && AlbumDetailsMaterialListing.this.f.size() > 0) {
                        ((BaseActivity) AlbumDetailsMaterialListing.this.mContext).showProgressDialog();
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        Iterator it = AlbumDetailsMaterialListing.this.f.iterator();
                        while (it.hasNext()) {
                            Track track2 = (Track) it.next();
                            arrayList2.add(track2);
                            track2.setBusinessObjType(BusinessObjectType.Tracks);
                            if (!(AlbumDetailsMaterialListing.this.I.contains(track2) || AlbumDetailsMaterialListing.this.as.contains(track2))) {
                                arrayList.add(track2);
                            }
                            if (arrayList.size() >= 10) {
                                break;
                            }
                        }
                        if (arrayList2.size() > 0) {
                            AlbumDetailsMaterialListing.this.f.removeAll(arrayList2);
                        }
                        if (arrayList != null && arrayList.size() > 0) {
                            AlbumDetailsMaterialListing.this.as.clear();
                            AlbumDetailsMaterialListing.this.as.addAll(arrayList);
                        }
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            public void run() {
                                ((BaseActivity) AlbumDetailsMaterialListing.this.mContext).hideProgressDialog();
                                if (AlbumDetailsMaterialListing.this.g != null) {
                                    AlbumDetailsMaterialListing.this.g.notifyDataSetChanged();
                                }
                                AlbumDetailsMaterialListing.this.q.smoothScrollBy(0, Util.b(56));
                                if (AlbumDetailsMaterialListing.this.f == null || AlbumDetailsMaterialListing.this.f.size() == 0) {
                                    AlbumDetailsMaterialListing.this.aa.findViewById(R.id.addmore_msg).setVisibility(0);
                                    AlbumDetailsMaterialListing.this.aa.findViewById(R.id.refresh_button).setVisibility(8);
                                    AlbumDetailsMaterialListing.this.aa.findViewById(R.id.addmore_msg).setOnClickListener(new OnClickListener() {
                                        public void onClick(View view) {
                                            GaanaApplication.getInstance().setArrListTracksForPlaylist(null);
                                            if (AlbumDetailsMaterialListing.this.p != null && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) AlbumDetailsMaterialListing.this.p) && (AlbumDetailsMaterialListing.this.p instanceof Playlist) && AlbumDetailsMaterialListing.this.p.getBusinessObjId() != null) {
                                                af.a(AlbumDetailsMaterialListing.this.mContext, ((GaanaActivity) AlbumDetailsMaterialListing.this.mContext).getCurrentFragment()).a((int) R.id.addMoreSongs, (Playlist) AlbumDetailsMaterialListing.this.p, true);
                                            }
                                        }
                                    });
                                }
                            }
                        }, 100);
                    }
                    u.a().a("Add to Playlist", "CF List Refresh", AlbumDetailsMaterialListing.this.p.getBusinessObjId());
                }
            });
            a(this.p.getBusinessObjId(), observableRecyclerView, false);
        }
    }

    public ArrayList<BusinessObject> f() {
        return this.I;
    }

    public CustomListAdapter g() {
        return this.s;
    }

    public void a(Track track, boolean z) {
        if (track != null) {
            if (this.aC == null) {
                this.aC = new ArrayList();
            }
            if (z) {
                this.aC.add(track);
                this.I.add(track);
                this.J.add(track);
            } else {
                if (this.aC.contains(track)) {
                    this.aC.remove(track);
                }
                if (this.I != null && this.I.contains(track)) {
                    this.I.remove(track);
                }
                if (this.J != null && this.J.contains(track)) {
                    this.J.remove(track);
                }
            }
            this.w = this.I != null ? this.I.size() : 0;
            this.s.updateAdapterCount((((this.w + 1) + this.N) + this.j.size()) + 1);
            if (z && this.f != null && this.f.size() > 0) {
                Iterator it = this.f.iterator();
                while (it.hasNext()) {
                    Track track2 = (Track) it.next();
                    track2.setBusinessObjType(BusinessObjectType.Tracks);
                    if (!(this.I.contains(track2) || this.as.contains(track2))) {
                        this.as.add(track2);
                    }
                    if (this.as.size() >= 10) {
                        break;
                    }
                }
                if (this.g != null) {
                    this.g.notifyDataSetChanged();
                }
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        AlbumDetailsMaterialListing.this.q.smoothScrollBy(0, Util.b(56));
                    }
                }, 100);
            }
        }
    }

    private void a(String str, final ObservableRecyclerView observableRecyclerView, boolean z) {
        ((BaseActivity) this.mContext).showProgressDialog();
        j.a().a((Object) "CF_API");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/playlist/recom?");
        stringBuilder.append(z ? "trackIds=" : "playlist_id=");
        stringBuilder.append(str);
        com.i.b bVar = new com.i.b(stringBuilder.toString(), Tracks.class, new com.i.e.a() {
            public void onDataRetrieved(Object obj, boolean z) {
                BusinessObject businessObject = (BusinessObject) obj;
                AlbumDetailsMaterialListing.this.f = businessObject.getArrListBusinessObj();
                AlbumDetailsMaterialListing.this.c(true);
                if (AlbumDetailsMaterialListing.this.as == null) {
                    AlbumDetailsMaterialListing.this.as = new ArrayList();
                }
                AlbumDetailsMaterialListing.this.as.clear();
                if (AlbumDetailsMaterialListing.this.f != null && AlbumDetailsMaterialListing.this.f.size() > 0) {
                    Iterator it = AlbumDetailsMaterialListing.this.f.iterator();
                    while (it.hasNext()) {
                        Track track = (Track) it.next();
                        track.setBusinessObjType(BusinessObjectType.Tracks);
                        if (!(AlbumDetailsMaterialListing.this.I.contains(track) || AlbumDetailsMaterialListing.this.as.contains(track))) {
                            AlbumDetailsMaterialListing.this.as.add(track);
                        }
                        if (AlbumDetailsMaterialListing.this.as.size() >= 10) {
                            break;
                        }
                    }
                }
                ((BaseActivity) AlbumDetailsMaterialListing.this.mContext).hideProgressDialog();
                observableRecyclerView.setLayoutManager(new LinearLayoutManager(AlbumDetailsMaterialListing.this.getContext(), 1, false));
                AlbumDetailsMaterialListing.this.g = new ExpandableListAdapter(AlbumDetailsMaterialListing.this.as, AlbumDetailsMaterialListing.this);
                observableRecyclerView.setAdapter(AlbumDetailsMaterialListing.this.g);
                if (AlbumDetailsMaterialListing.this.as == null || AlbumDetailsMaterialListing.this.as.size() == 0) {
                    ((Button) AlbumDetailsMaterialListing.this.aa.findViewById(R.id.addmore_msg)).setTypeface(null, 1);
                    AlbumDetailsMaterialListing.this.aa.findViewById(R.id.addmore_msg).setVisibility(0);
                    AlbumDetailsMaterialListing.this.aa.findViewById(R.id.refresh_button).setVisibility(8);
                    AlbumDetailsMaterialListing.this.aa.findViewById(R.id.addmore_msg).setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            GaanaApplication.getInstance().setArrListTracksForPlaylist(null);
                            if (AlbumDetailsMaterialListing.this.p != null && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) AlbumDetailsMaterialListing.this.p) && (AlbumDetailsMaterialListing.this.p instanceof Playlist) && AlbumDetailsMaterialListing.this.p.getBusinessObjId() != null) {
                                af.a(AlbumDetailsMaterialListing.this.mContext, ((GaanaActivity) AlbumDetailsMaterialListing.this.mContext).getCurrentFragment()).a((int) R.id.addMoreSongs, (Playlist) AlbumDetailsMaterialListing.this.p, true);
                            }
                        }
                    });
                } else {
                    AlbumDetailsMaterialListing.this.aa.findViewById(R.id.addmore_msg).setVisibility(8);
                    AlbumDetailsMaterialListing.this.aa.findViewById(R.id.refresh_button).setVisibility(0);
                }
                AlbumDetailsMaterialListing.this.b = new e(AlbumDetailsMaterialListing.this.g);
                if (AlbumDetailsMaterialListing.this.b != null) {
                    ((e) AlbumDetailsMaterialListing.this.b).b(false);
                }
                AlbumDetailsMaterialListing.this.ap = new ItemTouchHelper(AlbumDetailsMaterialListing.this.b);
                AlbumDetailsMaterialListing.this.ap.attachToRecyclerView(observableRecyclerView);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        AlbumDetailsMaterialListing.this.q.smoothScrollBy(0, Util.b(100));
                    }
                }, 300);
            }

            public void onErrorResponse(BusinessObject businessObject) {
                ((BaseActivity) AlbumDetailsMaterialListing.this.mContext).hideProgressDialog();
            }
        });
        bVar.a("CF_API");
        com.i.i.a().a(bVar);
    }

    /* Access modifiers changed, original: protected */
    public void a(ViewGroup viewGroup) {
        final RecyclerView recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_view);
        final Button button = (Button) viewGroup.findViewById(R.id.refresh_button);
        float height = (float) recyclerView.getHeight();
        if (height >= 10.0f) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -height);
            translateAnimation.setFillEnabled(true);
            translateAnimation.setFillAfter(true);
            translateAnimation.setDuration(700);
            translateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (recyclerView.getVisibility() == 0) {
                        AlbumDetailsMaterialListing.this.s.notifyDataSetChanged();
                    }
                    recyclerView.setVisibility(8);
                    button.setVisibility(8);
                }
            });
            button.startAnimation(translateAnimation);
            recyclerView.startAnimation(translateAnimation);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(ViewGroup viewGroup) {
        final RecyclerView recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_view);
        final Button button = (Button) viewGroup.findViewById(R.id.refresh_button);
        float height = (float) recyclerView.getHeight();
        if (height >= 10.0f) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, -height, 0.0f);
            translateAnimation.setFillEnabled(true);
            translateAnimation.setFillAfter(true);
            translateAnimation.setDuration(700);
            translateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    recyclerView.setVisibility(0);
                    button.setVisibility(0);
                }
            });
            recyclerView.startAnimation(translateAnimation);
            button.startAnimation(translateAnimation);
        }
    }

    public void a(ViewHolder viewHolder) {
        if (!this.i) {
            this.h = false;
            if (!this.h) {
                this.e = true;
                b(viewHolder);
            }
        }
    }

    private void b(ViewHolder viewHolder) {
        if (!com.services.d.a().b("PREFERENCE_KEY_SWIPE_TO_FAVORITE_INITIATED", false, false)) {
            int b = com.services.d.a().b("SWIPE_TO_FAVOURITE_ANIMATION", 0, false);
            if (b < 3) {
                int b2 = com.services.d.a().b("SESSION_OCCURENCE_QUEUE_ANIMATION_UP_DOWN", 0, false);
                int i = b2 + 5;
                if (b2 > 0) {
                    if (GaanaApplication.sessionHistoryCount + 1 >= i) {
                        a(viewHolder, b);
                    }
                } else if (b == 0 && GaanaApplication.sessionHistoryCount + 1 >= 5) {
                    a(viewHolder, b);
                }
            }
        }
    }

    private void a(final ViewHolder viewHolder, final int i) {
        final Canvas canvas = new Canvas();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                if (!AlbumDetailsMaterialListing.this.h) {
                    AlbumDetailsMaterialListing.this.q.setOnTouchListener(new OnTouchListener() {
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });
                    AlbumDetailsMaterialListing.this.a(canvas, AlbumDetailsMaterialListing.this.q, viewHolder, 0.0f, (float) ((com.services.d.a().b() / 4) - 1));
                    com.services.d.a().a("SWIPE_TO_FAVOURITE_ANIMATION", i + 1, false);
                    com.services.d.a().a("SESSION_OCCURENCE_SWIPE_TO_FAVOURITE_ANIMATION", GaanaApplication.sessionHistoryCount, false);
                }
            }
        }, 500);
    }

    public void a(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, final float f, float f2) {
        if (recyclerView.getChildCount() > 1) {
            this.s.setIsSwipeAnimated(true);
            if (viewHolder.itemView.getTag() instanceof BusinessObject) {
                final View view = viewHolder.itemView;
                this.i = true;
                view.animate().translationX(f2).setDuration(1000).setListener(new AnimatorListener() {
                    public void onAnimationCancel(Animator animator) {
                    }

                    public void onAnimationRepeat(Animator animator) {
                    }

                    public void onAnimationStart(Animator animator) {
                    }

                    public void onAnimationEnd(Animator animator) {
                        new Timer().schedule(new TimerTask() {
                            public void run() {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    public void run() {
                                        view.animate().translationX(f).setDuration(1000).setListener(new AnimatorListener() {
                                            public void onAnimationCancel(Animator animator) {
                                            }

                                            public void onAnimationRepeat(Animator animator) {
                                            }

                                            public void onAnimationStart(Animator animator) {
                                            }

                                            public void onAnimationEnd(Animator animator) {
                                                AlbumDetailsMaterialListing.this.i = false;
                                                AlbumDetailsMaterialListing.this.s.setIsSwipeAnimated(false);
                                                ((e) AlbumDetailsMaterialListing.this.b).b(true);
                                                AlbumDetailsMaterialListing.this.q.setOnTouchListener(new OnTouchListener() {
                                                    public boolean onTouch(View view, MotionEvent motionEvent) {
                                                        return false;
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        }, 1000);
                    }
                });
                this.s.setIsSwipeAnimated(true);
                this.ap.startSwipe(recyclerView.getChildViewHolder(view));
            }
        }
    }

    public void notifyItemChanged(int i) {
        if (this.s != null) {
            this.s.notifyItemChanged(i + 1);
        }
    }

    private int b(int i) {
        if (this.j.size() > 0 && i == this.w && i == ((Integer) this.j.get(this.j.size() - 1)).intValue()) {
            return i - 1;
        }
        Iterator it = this.j.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (((Integer) it.next()).intValue() < i) {
                i2++;
            }
        }
        return i - i2;
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder itemEmptyMessageHolder;
        if (i == 5) {
            UserMessage userMessage = new UserMessage();
            userMessage.setEmptyMsg(this.mContext.getResources().getString(R.string.NO_DATA));
            itemEmptyMessageHolder = new ItemEmptyMessageHolder(this.t.getEmptyMsgView(userMessage, viewGroup));
        } else if (i == 7) {
            return new ItemAdViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.myplaylist_add_more_songs, viewGroup, false));
        } else {
            if (i == 4) {
                return new ItemAdViewHolder(z());
            }
            if (i == 2 || i == 20) {
                itemEmptyMessageHolder = new DetailListingItemHolder(y());
            } else if (i == 6) {
                itemEmptyMessageHolder = new SpinnerHolder(LayoutInflater.from(this.mContext).inflate(R.layout.view_album_details_filter, viewGroup, false));
            } else if (i < 8 || i >= this.j.size() + 8) {
                itemEmptyMessageHolder = new AlbumDetailItemHolder(this.t.createViewHolder(viewGroup, i));
            } else {
                if (this.W == null) {
                    this.W = new ColumbiaAdItemview(this.mContext, this);
                }
                itemEmptyMessageHolder = new ItemAdViewHolder(this.W.getNewView(0, viewGroup));
            }
        }
        return itemEmptyMessageHolder;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return this.O ? 5 : 4;
        } else {
            if (i == 1) {
                return 6;
            }
            if (i == (this.w + 2) + this.j.size()) {
                return 7;
            }
            if (i == (this.w + 3) + this.j.size()) {
                if (this.B instanceof LinearLayout) {
                    return 2;
                }
                return 20;
            } else if (this.j.contains(Integer.valueOf(i))) {
                return this.j.indexOf(Integer.valueOf(i)) + 8;
            } else {
                return 1;
            }
        }
    }

    public void onRefresh() {
        if (!this.v) {
            this.u.setRefreshing(true);
            this.v = true;
            if (ap.a().b(this.mContext)) {
                ColombiaManager.b().c();
                if (this.W != null) {
                    this.W.a();
                }
            }
            x();
            E();
        }
    }

    private View y() {
        if (this.B == null) {
            this.B = new View(this.mContext);
        }
        return this.B;
    }

    private LinearLayout z() {
        if (this.a == null) {
            this.a = new LinearLayout(this.mContext);
            this.a.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
            this.a.setGravity(17);
            this.a.setBackgroundColor(this.mContext.getResources().getColor(R.color.gaana_grey));
        }
        return this.a;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_play_all) {
            if (!Constants.ab || this.p == null || this.p.isLocalMedia()) {
                af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.playMenu, c());
                return;
            }
            String str = "";
            if (this.p.getBusinessObjType() == BusinessObjectType.Playlists) {
                str = "Playlist-";
            } else if (this.p.getBusinessObjType() == BusinessObjectType.Albums) {
                str = "Album-";
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(this.p.getBusinessObjId());
            u.a().a("Shuffle Product", "Shuffle Play", stringBuilder.toString());
            af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.shuffleMenu, this.p);
        } else if (view.getId() == R.id.btn_download_all) {
            af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.downloadMenu, this.p);
        }
    }

    public void onDestroyView() {
        ColombiaManager.b().a();
        if (this.aF != null) {
            this.aF.destroy();
        }
        if (this.n.getParent() != null) {
            ((ViewGroup) this.n.getParent()).removeView(this.n);
        }
        super.onDestroyView();
        this.T = true;
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.v = false;
        super.onErrorResponse(volleyError);
        showNetworkErrorView(null);
        this.A.setVisibility(8);
    }

    public void a(Object obj, boolean z) {
        if (!this.T) {
            int i = 0;
            if (((Tracks) obj).getIsSponsored()) {
                GaanaApplication.getInstance().setIsCurrentALPLSponsored(true);
            } else {
                GaanaApplication.getInstance().setIsCurrentALPLSponsored(false);
            }
            this.v = false;
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (this.P != 0) {
                long j = timeInMillis - this.P;
                String str = "";
                if (this.p.getBusinessObjType() == BusinessObjectType.Playlists) {
                    str = "Playlist detail";
                } else if (this.p.getBusinessObjType() == BusinessObjectType.Albums) {
                    str = "Album detail";
                }
                Constants.a("Load", j, str, null);
                if (z) {
                    Constants.a("Load - Network", j, str, null);
                }
            }
            this.u.setRefreshing(false);
            this.A.setVisibility(8);
            if (Constants.l) {
                m = 1.0f;
                this.n.findViewById(R.id.overlay).setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.view_foreground_light));
            }
            this.Z = false;
            BusinessObject businessObject = (BusinessObject) obj;
            ArrayList arrayList;
            if (businessObject == null || businessObject.getArrListBusinessObj() == null) {
                if ((this.p instanceof Playlist) && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) this.p) && !((Playlist) this.p).getAutoDisplayHome() && (TextUtils.isEmpty(((Playlist) this.p).getIsMiniPlaylist()) || ((Playlist) this.p).getIsMiniPlaylist().equals(Integer.valueOf(0)))) {
                    this.Z = true;
                }
                this.w = 0;
                arrayList = new ArrayList();
                this.p.setArrListBusinessObj(arrayList);
                this.p.setCount("0");
                this.J.clear();
                this.J.addAll(arrayList);
                this.j.clear();
                this.s.updateAdapterCount(3);
                this.O = true;
            } else {
                int size;
                if (Constants.aF != 0) {
                    Tracks tracks = (Tracks) businessObject;
                    if (!(tracks.getTags() == null && tracks.getTopArtists() == null && tracks.getTopLanguages() == null)) {
                        this.av = new ArrayList();
                        if (tracks.getTags() != null) {
                            this.at = tracks.getTags();
                            this.av.addAll(this.at);
                        }
                        if (tracks.getTopArtists() != null) {
                            this.au = tracks.getTopArtists();
                            this.av.addAll(this.au);
                        }
                        if (tracks.getTopLanguages() != null) {
                            this.aw = tracks.getTopLanguages();
                            this.av.addAll(this.aw);
                        }
                        if (this.p != null) {
                            this.ar = new TagsAdapter(this.av, this.p.getLanguage(), R.layout.recyclerview_tags, getActivity(), this, this);
                        } else {
                            this.ar = new TagsAdapter(this.av, "English", R.layout.recyclerview_tags, getActivity(), this, this);
                        }
                        this.aq.setAdapter(this.ar);
                    }
                }
                arrayList = businessObject.getArrListBusinessObj();
                if (!(arrayList == null || arrayList.size() == 0)) {
                    try {
                        HashMap h = ((ListingButton) this.o.c().get(0)).c().h();
                        boolean z2 = (h == null || h.get("type") == null || !((String) h.get("type")).equals("mysongs")) ? false : true;
                        if (z2 || (this.p instanceof Playlist)) {
                            for (size = arrayList.size() - 1; size >= 0; size--) {
                                Track track = (Track) arrayList.get(size);
                                if (track.getIslocal() != null && track.getIslocal().equals("1")) {
                                    track = LocalMediaManager.getInstance(this.mContext).getLocalTrackFromHash(track.getBusinessObjId());
                                    arrayList.remove(size);
                                    if (track != null) {
                                        arrayList.add(size, track);
                                    }
                                }
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
                if (arrayList == null || arrayList.size() == 0) {
                    if ((this.p instanceof Playlist) && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) this.p) && !((Playlist) this.p).getAutoDisplayHome() && (TextUtils.isEmpty(((Playlist) this.p).getIsMiniPlaylist()) || ((Playlist) this.p).getIsMiniPlaylist().equals(Integer.valueOf(0)))) {
                        this.Z = true;
                    }
                    this.w = 0;
                    this.p.setArrListBusinessObj(arrayList);
                    this.p.setCount("0");
                    this.J.clear();
                    this.J.addAll(arrayList);
                    this.j.clear();
                    this.s.updateAdapterCount(3);
                    this.O = true;
                    this.H.setText("");
                } else {
                    this.I = businessObject.getArrListBusinessObj();
                    this.J.clear();
                    this.J.addAll(this.I);
                    this.w = this.I.size();
                    this.O = false;
                    ((ListingButton) this.o.c().get(0)).a(this.J);
                    this.p.setArrListBusinessObj(arrayList);
                    this.mAppState.setCurrentBusObjInListView(arrayList);
                    this.q.setItemAnimator(new DefaultItemAnimator());
                    String str2 = "0";
                    Object obj2 = "0";
                    if (this.p instanceof Album) {
                        str2 = ((Album) this.p).getFavoriteCount();
                        obj2 = ((Tracks) businessObject).getFavoriteCount();
                        ((Album) this.p).setFavoriteCount(obj2);
                        ((Album) this.p).setTags(this.at);
                    } else if (this.p instanceof Playlist) {
                        str2 = ((Playlist) this.p).getFavoriteCount();
                        obj2 = ((Tracks) businessObject).getFavoriteCount();
                        ((Playlist) this.p).setFavoriteCount(obj2);
                        ((Playlist) this.p).setTags(this.at);
                    }
                    this.p.setCount(String.valueOf(businessObject.getArrListBusinessObj().size()));
                    w();
                    if (!(this.p.isLocalMedia() || DownloadManager.c().h(Integer.parseInt(this.p.getBusinessObjId())) == null)) {
                        if (this.p instanceof Playlist) {
                            ((Playlist) this.p).setLastModifiedDate(((Tracks) businessObject).getModifiedOn());
                            DownloadManager.c().d(this.p);
                        }
                        if (!(str2 == null || obj2 == null || str2.equals(obj2))) {
                            DownloadManager.c().a(this.p.getBusinessObjId(), this.p);
                        }
                    }
                    this.N = 2;
                    this.j.clear();
                    if (ap.a().b(this.mContext) && GaanaApplication.getInstance().getColombiaSdkInit() && TextUtils.isEmpty(F()) && com.managers.e.ad == 0) {
                        size = this.w;
                        int i2 = 0;
                        while (i2 < size) {
                            if (i2 == 4) {
                                this.j.add(Integer.valueOf(this.N + i2));
                                size++;
                            } else if (i2 != 0 && (i2 - 4) % 7 == 0) {
                                this.j.add(Integer.valueOf(this.N + i2));
                                size++;
                            }
                            i2++;
                        }
                        if (this.j.isEmpty()) {
                            this.j.add(Integer.valueOf(this.w + this.N));
                        }
                    }
                    if ((this.p instanceof Playlist) && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) this.p) && this.w > 0 && !((Playlist) this.p).getAutoDisplayHome() && (TextUtils.isEmpty(((Playlist) this.p).getIsMiniPlaylist()) || ((Playlist) this.p).getIsMiniPlaylist().equals(Integer.valueOf(0)))) {
                        this.Z = true;
                    }
                    this.s.updateAdapterCount((((this.w + 1) + this.N) + this.j.size()) + 1);
                    if (this.X != null) {
                        this.X.setSelection(0);
                    }
                    B();
                    if (!this.p.isLocalMedia() && (this.p instanceof Album)) {
                        D();
                    }
                }
            }
            if (this.p.getBusinessObjType() == BusinessObjectType.HomeAction) {
                af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.playMenu, c());
            }
            if ((this.p instanceof Playlist) && ((Playlist) this.p).getAutoDisplayHome()) {
                h();
            } else if ((this.p instanceof Playlist) && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) this.p)) {
                h();
            }
            if (this.aE) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        AlbumDetailsMaterialListing.this.q.smoothScrollBy(0, Util.b(100));
                        AlbumDetailsMaterialListing.this.aE = false;
                    }
                }, 50);
            }
            if (this.aC != null && this.aC.size() > 0) {
                this.aD = true;
                this.I.addAll(this.aC);
                this.J.addAll(this.aC);
                if (this.I != null) {
                    i = this.I.size();
                }
                this.w = i;
                this.s.updateAdapterCount((((this.w + 1) + this.N) + this.j.size()) + 1);
            }
        }
    }

    public void onResponse(Object obj) {
        a(obj, false);
    }

    public void h() {
        this.ao = 0;
        this.am = new ArrayList();
        this.an = new ArrayList();
        if (this.J != null && this.J.size() > 0) {
            Track track;
            if (this.J == null || this.J.size() <= 3) {
                track = (Track) this.J.get(0);
                if (track.isLocalMedia()) {
                    this.r.bindImage(track.getArtwork(), ScaleType.CENTER_CROP);
                } else {
                    this.r.bindImage(track.getArtworkLarge(), ScaleType.CENTER_CROP);
                }
            } else {
                Iterator it = this.J.iterator();
                while (it.hasNext()) {
                    String artworkLarge = ((Track) it.next()).getArtworkLarge();
                    if (!(artworkLarge == null || this.am.contains(artworkLarge))) {
                        this.am.add(artworkLarge);
                        if (this.am.size() >= 4) {
                            break;
                        }
                    }
                }
                if (this.am.size() > 3) {
                    a((String) this.am.get(this.ao));
                } else if (this.J != null && this.J.size() > 0) {
                    track = (Track) this.J.get(0);
                    if (track.isLocalMedia()) {
                        this.r.bindImage(track.getArtwork(), ScaleType.CENTER_CROP);
                    } else {
                        this.r.bindImage(track.getArtworkLarge(), ScaleType.CENTER_CROP);
                    }
                }
            }
            if (this.J != null) {
                TextView textView = this.H;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.J.size());
                stringBuilder.append(" Songs");
                textView.setText(stringBuilder.toString());
            }
        }
    }

    private void a(String str) {
        if (this.am.size() > 3) {
            com.i.i.a().a(str.replace("480x480", "175x175"), new r() {
                public void onSuccessfulResponse(Bitmap bitmap) {
                    if (bitmap != null) {
                        try {
                            AlbumDetailsMaterialListing.this.an.add(Bitmap.createScaledBitmap(bitmap, AlbumDetailsMaterialListing.this.C, AlbumDetailsMaterialListing.this.C, false));
                            AlbumDetailsMaterialListing.this.ao = AlbumDetailsMaterialListing.this.ao + 1;
                            if (AlbumDetailsMaterialListing.this.ao < 4 || AlbumDetailsMaterialListing.this.r == null) {
                                AlbumDetailsMaterialListing.this.a((String) AlbumDetailsMaterialListing.this.am.get(AlbumDetailsMaterialListing.this.ao));
                            } else {
                                AlbumDetailsMaterialListing.this.r.setImageBitmap(AlbumDetailsMaterialListing.this.A());
                            }
                        } catch (OutOfMemoryError unused) {
                        }
                    }
                }

                public void onErrorResponse(VolleyError volleyError) {
                    if (AlbumDetailsMaterialListing.this.J != null && AlbumDetailsMaterialListing.this.J.size() > 0 && AlbumDetailsMaterialListing.this.r != null) {
                        Track track = (Track) AlbumDetailsMaterialListing.this.J.get(0);
                        if (track.isLocalMedia()) {
                            AlbumDetailsMaterialListing.this.r.bindImage(track.getArtwork(), ScaleType.CENTER_CROP);
                        } else {
                            AlbumDetailsMaterialListing.this.r.bindImage(track.getArtworkLarge(), ScaleType.CENTER_CROP);
                        }
                    }
                }
            });
        }
    }

    private Bitmap A() {
        Bitmap createBitmap = Bitmap.createBitmap(this.C * 2, this.C * 2, Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        for (int i = 0; i < this.an.size(); i++) {
            if (com.utilities.d.d()) {
                Bitmap a = Util.a((Bitmap) this.an.get(i));
                if (a != null) {
                    canvas.drawBitmap(a, (float) (this.C * (i % 2)), (float) (this.C * (i / 2)), paint);
                    a.recycle();
                }
            } else {
                canvas.drawBitmap((Bitmap) this.an.get(i), (float) (this.C * (i % 2)), (float) (this.C * (i / 2)), paint);
            }
        }
        return createBitmap;
    }

    public String getSectionName() {
        if (this.p.getBusinessObjType() != BusinessObjectType.HomeAction) {
            return super.getSectionName();
        }
        String playoutSectionName = GaanaApplication.getInstance().getPlayoutSectionName();
        if (TextUtils.isEmpty(playoutSectionName) || playoutSectionName.equals(PLAYOUT_SECTION_TYPE.GAANA_ACTIONS.name())) {
            return PLAYOUT_SECTION_TYPE.GAANA_ACTIONS.name();
        }
        return playoutSectionName;
    }

    private void B() {
        if ((this.p instanceof Album) || (this.p instanceof Playlist)) {
            this.L = "";
            if (this.p instanceof Album) {
                this.L = ((Album) this.p).getChannelPageAdCode();
            } else if (this.p instanceof Playlist) {
                this.L = ((Playlist) this.p).getChannelPageAdCode();
            }
            if (!(this.L == null || TextUtils.isEmpty(this.L))) {
                C();
            }
        }
        if (this.ax == null && !TextUtils.isEmpty(this.K)) {
            if (this.K.contains("play")) {
                String[] split = this.K.split("/");
                if (split.length > 1) {
                    BusinessObject businessObject;
                    String str = split[1];
                    ArrayList g = ((ListingButton) this.o.c().get(0)).g();
                    Iterator it = g.iterator();
                    while (it.hasNext()) {
                        BusinessObject businessObject2 = (BusinessObject) it.next();
                        if (str.equals(businessObject2.getBusinessObjId())) {
                            businessObject = (Track) businessObject2;
                            break;
                        }
                    }
                    businessObject = null;
                    if (businessObject != null) {
                        PlayerManager.a(this.mContext).a(com.logging.d.a().a((BaseGaanaFragment) this, g), com.logging.d.a().a((BaseGaanaFragment) this, businessObject));
                        PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
                        ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
                    }
                } else {
                    af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.playMenu, c());
                }
            } else if (this.K.contains("download")) {
                b(false);
            }
            this.K = null;
        }
    }

    public void b(final boolean z) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else if (Util.j(this.mContext)) {
            if (ap.a().a(this.p, null)) {
                d(z);
            } else {
                String str = "";
                if (ap.a().l()) {
                    str = this.p instanceof Track ? "tr" : "pl";
                }
                Util.b(this.mContext, str, null, new as() {
                    public void onTrialSuccess() {
                        AlbumDetailsMaterialListing.this.d(z);
                        AlbumDetailsMaterialListing.this.refreshDataandAds();
                        AlbumDetailsMaterialListing.this.showSnackbartoOpenMyMusic();
                        ((GaanaActivity) AlbumDetailsMaterialListing.this.mContext).updateSideBar();
                    }
                });
            }
        } else {
            ap.a().f(this.mContext);
        }
    }

    private void d(boolean z) {
        Util.i(this.mContext, "Download");
        BusinessObject businessObject = this.p;
        this.p.setArrListBusinessObj(((ListingButton) this.o.c().get(0)).g());
        final BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
        boolean b = com.services.d.a().b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
        DownloadStatus h = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
        if (h == null || h == DownloadStatus.TRIED_BUT_FAILED || h == DownloadStatus.PAUSED || h == DownloadStatus.PARTIALLY_DOWNLOADED) {
            if (Util.k(GaanaApplication.getContext()) == 0) {
                if (!com.services.d.a().b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                    ((BaseActivity) this.mContext).mDialog = new f(this.mContext);
                    ((BaseActivity) this.mContext).mDialog.a(this.mContext.getString(R.string.gaana_plus_feature), this.mContext.getString(R.string.sync_over_data_connection_disabled), Boolean.valueOf(true), this.mContext.getString(R.string.settings_text), this.mContext.getString(R.string.dlg_msg_cancel), new f.b() {
                        public void onCancelListner() {
                        }

                        public void onOkListner(String str) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            ((BaseActivity) AlbumDetailsMaterialListing.this.mContext).sendGAEvent("GaanaPlus", "BuySubscription", "Others");
                            ((GaanaActivity) AlbumDetailsMaterialListing.this.mContext).displayFragment(settingsDetailFragment);
                        }
                    });
                    return;
                } else if (b) {
                    if (!Constants.V) {
                        aj.a().a(this.mContext, this.mContext.getString(R.string.schedule_songs_queue_msg));
                        Constants.V = true;
                    }
                } else if (!Constants.W) {
                    Constants.W = true;
                    aj.a().a(this.mContext, this.mContext.getString(R.string.schedule_cta_text), this.mContext.getString(R.string.schedule_download_msg), new OnClickListener() {
                        public void onClick(View view) {
                            if ((currentFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) currentFragment).a() == 1) {
                                PopupWindowView.getInstance(AlbumDetailsMaterialListing.this.mContext, currentFragment).dismiss(true);
                                return;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            PopupWindowView.getInstance(AlbumDetailsMaterialListing.this.mContext, currentFragment).dismiss(true);
                            ((GaanaActivity) AlbumDetailsMaterialListing.this.mContext).displayFragment(settingsDetailFragment);
                        }
                    });
                }
            }
            if (h == null) {
                DownloadManager.c().a(businessObject, this.mContext);
            } else {
                DownloadManager.c().c(businessObject);
            }
            a(Boolean.valueOf(false));
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(9, -1));
            obtainStyledAttributes.recycle();
            this.ag.setText(R.string.downloading);
            this.ah.setImageDrawable(null);
        } else if (z) {
            if (h == DownloadStatus.QUEUED || h == DownloadStatus.DOWNLOADING) {
                new f(this.mContext).a(this.mContext.getString(R.string.gaana_text), this.mContext.getString(R.string.do_you_want_pause_this_album_download), Boolean.valueOf(true), this.mContext.getString(R.string.dialog_yes), this.mContext.getString(R.string.dialog_no), new f.b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        DownloadManager.c().r(Integer.parseInt(AlbumDetailsMaterialListing.this.p.getBusinessObjId()));
                        new int[1][0] = R.attr.button_resume;
                        TypedArray obtainStyledAttributes = AlbumDetailsMaterialListing.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        Drawable drawable = ContextCompat.getDrawable(AlbumDetailsMaterialListing.this.getContext(), obtainStyledAttributes.getResourceId(10, -1));
                        obtainStyledAttributes.recycle();
                        AlbumDetailsMaterialListing.this.ag.setText(R.string.resume);
                        AlbumDetailsMaterialListing.this.ah.setImageDrawable(drawable);
                    }
                }, false);
            } else if (h == DownloadStatus.DOWNLOADED) {
                new f(this.mContext).a(this.mContext.getString(R.string.gaana_text), this.mContext.getString(R.string.do_you_want_to_remove_this_album_from_download), Boolean.valueOf(true), this.mContext.getString(R.string.dialog_yes), this.mContext.getString(R.string.dialog_no), new f.b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        DownloadManager.c().p(Integer.parseInt(AlbumDetailsMaterialListing.this.p.getBusinessObjId()));
                        DownloadManager.c().d(Integer.parseInt(AlbumDetailsMaterialListing.this.p.getBusinessObjId()));
                        AlbumDetailsMaterialListing.this.a(Boolean.valueOf(false));
                    }
                }, false);
            }
        }
    }

    private void a(Boolean bool) {
        if (this.p != null) {
            ((BaseActivity) this.mContext).refreshListView();
            a(this.x, this.p);
        }
    }

    public void i() {
        if (this.p != null) {
            a(this.x, this.p);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(View view, BusinessObject businessObject) {
        if (!this.p.isLocalMedia()) {
            DownloadStatus downloadStatus = null;
            if ((businessObject instanceof Playlist) || (businessObject instanceof Album)) {
                downloadStatus = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
            } else if (businessObject instanceof Track) {
                downloadStatus = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
            }
            a(downloadStatus);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(DownloadStatus downloadStatus) {
        if (this.x != null) {
            TypedArray obtainStyledAttributes;
            Drawable drawable;
            if (downloadStatus == DownloadStatus.DOWNLOADING) {
                if (DownloadManager.c().w()) {
                    new int[1][0] = R.attr.button_downloding;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(8, -1));
                    obtainStyledAttributes.recycle();
                    this.ag.setText(R.string.downloading);
                    this.ah.setImageDrawable(null);
                } else {
                    new int[1][0] = R.attr.button_inqueue;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(9, -1));
                    obtainStyledAttributes.recycle();
                    this.ag.setText(R.string.queued);
                    this.ah.setImageDrawable(drawable);
                }
            } else if (downloadStatus == DownloadStatus.DOWNLOADED) {
                new int[1][0] = R.attr.button_downloded;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(7, -1));
                obtainStyledAttributes.recycle();
                this.ag.setText(R.string.downloaded);
                this.ah.setImageDrawable(drawable);
            } else if (downloadStatus == DownloadStatus.PAUSED || downloadStatus == DownloadStatus.PARTIALLY_DOWNLOADED) {
                new int[1][0] = R.attr.button_resume;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(10, -1));
                obtainStyledAttributes.recycle();
                this.ag.setText(R.string.resume);
                this.ah.setImageDrawable(drawable);
            } else if (downloadStatus == DownloadStatus.QUEUED) {
                new int[1][0] = R.attr.button_inqueue;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(9, -1));
                obtainStyledAttributes.recycle();
                this.ag.setText(R.string.queued);
                this.ah.setImageDrawable(drawable);
            } else if (downloadStatus == DownloadStatus.TRIED_BUT_FAILED) {
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(91, -1));
                obtainStyledAttributes.recycle();
                this.ah.setImageDrawable(drawable);
            } else {
                new int[1][0] = R.attr.menu_download_paused;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(29, -1));
                obtainStyledAttributes.recycle();
                this.ag.setText(R.string.download);
                this.ah.setImageDrawable(drawable);
            }
        }
    }

    private void C() {
        if (isAdded() && ap.a().p() && !TextUtils.isEmpty(Constants.j)) {
            String str = Constants.j;
            this.aF = new PublisherAdView(this.mContext);
            this.aF.setAdUnitId(str);
            this.aF.setAdSizes(new AdSize(ModuleDescriptor.MODULE_VERSION, 100), new AdSize(ModuleDescriptor.MODULE_VERSION, 140), new AdSize(ModuleDescriptor.MODULE_VERSION, 150), new AdSize(340, 100), new AdSize(340, 140), new AdSize(340, 150), new AdSize(728, 100), new AdSize(728, 140), new AdSize(728, 150), new AdSize(468, 100), new AdSize(468, 140), new AdSize(468, 150));
            this.aF.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                }

                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                }

                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                }

                public void onAdOpened() {
                    super.onAdOpened();
                }

                public void onAdLoaded() {
                    super.onAdLoaded();
                    try {
                        AlbumDetailsMaterialListing.this.a = AlbumDetailsMaterialListing.this.z();
                        AlbumDetailsMaterialListing.this.a.removeAllViews();
                        AlbumDetailsMaterialListing.this.a.addView(AlbumDetailsMaterialListing.this.aF);
                    } catch (Exception unused) {
                    }
                }
            });
            try {
                Location location = ((GaanaActivity) this.mContext).getLocation();
                if (location != null) {
                    Builder builder = new Builder();
                    if (this.mAppState.getNetworkExtrasBundle() != null) {
                        builder.addNetworkExtras(this.mAppState.getNetworkExtrasBundle());
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(Util.l(GaanaApplication.getContext()));
                    stringBuilder.append("Gaana ");
                    builder.setPublisherProvidedId(Util.c(stringBuilder.toString()));
                    Location location2 = new Location("");
                    location2.setLatitude(location.getLatitude());
                    location2.setLongitude(location.getLongitude());
                    this.aF.loadAd(builder.setLocation(location2).build());
                } else {
                    Builder builder2 = new Builder();
                    if (this.mAppState.getNetworkExtrasBundle() != null) {
                        builder2.addNetworkExtras(this.mAppState.getNetworkExtrasBundle());
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(Util.l(GaanaApplication.getContext()));
                    stringBuilder2.append("Gaana ");
                    builder2.setPublisherProvidedId(Util.c(stringBuilder2.toString()));
                    this.aF.loadAd(builder2.build());
                }
            } catch (Exception unused) {
            }
        }
    }

    private void D() {
        URLManager uRLManager = new URLManager();
        URLManager c = ((ListingButton) this.mAppState.getListingComponents().c().get(0)).c();
        String str = null;
        BusinessObjectType l = c != null ? c.l() : null;
        uRLManager.a(l);
        if (l == BusinessObjectType.Albums) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://rec.gaana.com/recommendation/similarAlbums/");
            stringBuilder.append(this.mAppState.getListingComponents().a().getBusinessObjId());
            str = stringBuilder.toString();
        }
        uRLManager.a(str);
        com.i.i.a().a(this.k, uRLManager);
    }

    public void j() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.favorite_tap_animation);
        loadAnimation.setInterpolator(new com.a.a(0.1d, 2.0d));
        this.ae.startAnimation(loadAnimation);
    }

    private void a(BusinessObject businessObject) {
        if (((ListingButton) this.o.c().get(0)).g().size() > 0 && (businessObject instanceof Albums) && this.mAppState.getListingComponents().e().equals(AlbumItemView.class.getName())) {
            this.B = new SimilarItemHorizontalScroll(this.mContext, this).populateSimilar(businessObject, this.o.e(), this.mContext.getString(R.string.similar_album));
            this.B.setLayoutParams(new ViewGroup.LayoutParams(this.q.getWidth(), -2));
            this.s.notifyDataSetChanged();
        }
    }

    public void onScrollChanged(int i, boolean z, boolean z2) {
        if (i > this.S.widthPixels) {
            onScrollChanged(this.S.widthPixels, false, false);
        } else if (((LinearLayoutManager) this.q.getLayoutManager()).findFirstVisibleItemPosition() != 0) {
            i = (int) (-this.D.getY());
        }
        this.aH = i;
        if (this.U == null || !this.U.isVisible()) {
            l = 2.0f;
        } else {
            l = 2.5f;
        }
        float f = (float) (this.C - (this.ac * 2));
        int height = this.ac - this.E.getHeight();
        if (this.ae.getVisibility() == 8) {
            this.E.setTranslationY(com.collapsible_header.f.a((float) (-i), (float) height, (float) this.ac));
        } else {
            this.E.setTranslationY(com.collapsible_header.f.a((float) (-i), (float) (this.ac + height), (float) this.ac));
        }
        int i2 = -i;
        float f2 = (float) height;
        this.r.setTranslationY(com.collapsible_header.f.a((float) (i2 / 2), f2, 0.0f));
        if (this.at == null || this.at.isEmpty()) {
            this.aq.setVisibility(8);
            this.M.setTranslationY((com.collapsible_header.f.a((float) i2, (float) (height + this.ad.getHeight()), (float) this.ac) + ((float) this.C)) - ((float) (this.M.getHeight() / 2)));
        } else {
            this.M.setTranslationY((com.collapsible_header.f.a((float) i2, f2 + (((float) this.ad.getHeight()) / 1.5f), (float) this.ac) + ((float) this.C)) - (((float) this.M.getHeight()) / 4.5f));
        }
        float f3 = (float) i;
        this.E.setAlpha(com.collapsible_header.f.a(f3 / f, 0.0f, 1.0f));
        f = 0.5f + com.collapsible_header.f.a((f - f3) / f, 0.0f, 0.5f);
        f3 = (float) (((int) ((((((float) this.C) - (((float) this.G.getHeight()) * (0.2f + f))) - ((float) this.mContext.getResources().getDimensionPixelSize(R.dimen.artist_tab_padding_fab))) - ((float) this.ak.getHeight())) - ((float) (this.M.getHeight() / 2)))) + i2);
        this.G.setTranslationY(com.collapsible_header.f.a(f3, (float) this.aj, (float) this.C));
        this.ak.setTranslationY(com.collapsible_header.f.a(f3, 0.0f, (float) (this.C - this.M.getHeight())));
        f3 = com.collapsible_header.f.a((float) ((i2 + this.C) - this.M.getHeight()), 0.0f, (float) this.C);
        if (f3 < ((float) (this.ac * 2))) {
            this.ak.setVisibility(4);
            this.aq.setBackgroundResource(this.d);
            f3 = (f3 - (2.0f * ((float) this.ac))) / ((float) this.ac);
            if (((float) this.aG.width) != ((float) this.S.widthPixels) / l) {
                this.aG.width = (int) (((float) this.S.widthPixels) / l);
                this.G.setPadding(0, 0, 0, 0);
                this.G.setLayoutParams(this.aG);
            }
            if (this.U == null || !this.U.isVisible()) {
                this.G.setTranslationX(com.collapsible_header.f.a(f3 * ((float) this.ac), (float) (-this.ac), 0.0f));
            } else {
                this.G.setTranslationX(com.collapsible_header.f.a(f3 * ((float) this.ac), (float) (-(this.ac + Util.b(15))), 0.0f));
            }
            this.G.setScaleX(com.collapsible_header.f.a(f, 0.7f, 1.0f));
            this.G.setScaleY(com.collapsible_header.f.a(f, 0.7f, 1.0f));
            return;
        }
        this.ak.setVisibility(0);
        this.aq.setBackgroundColor(this.mContext.getResources().getColor(R.color.transparent_color));
        this.G.setScaleX(f);
        this.G.setScaleY(f);
        if (this.aG.width != this.S.widthPixels) {
            this.G.setTranslationX(0.0f);
            this.aG.width = this.S.widthPixels;
            this.aG.gravity = 1;
            this.G.setPadding((int) this.mContext.getResources().getDimension(R.dimen.dp15), 0, (int) this.mContext.getResources().getDimension(R.dimen.dp15), 0);
            this.G.setLayoutParams(this.aG);
        }
    }

    private void a(View view, int i) {
        b(view, i);
    }

    private void b(View view, int i) {
        this.aI = i;
        this.y.showContextMenu(true);
        al.a().a(true);
        if (this.b != null) {
            ((e) this.b).b(false);
        }
        al.a().a((BusinessObject) view.getTag(), true);
        ((CheckBox) view.findViewById(R.id.f35download.item.checkbox)).setChecked(true);
        m();
    }

    public void k() {
        this.aI = 0;
        this.y.showContextMenu(false);
        this.ab.findItem(R.id.menu_download).setVisible(false);
        al.a().a(false);
        al.a().c();
        this.s.notifyDataSetChanged();
        if (this.b != null) {
            ((e) this.b).b(true);
        }
    }

    public void m() {
        this.y.updateSelectedCountinContextMode(this.aI);
    }

    public void n() {
        if (al.a().e()) {
            al.a().c();
        } else {
            al.a().a(this.I);
        }
        this.s.notifyDataSetChanged();
        m();
    }

    public void o() {
        if (!TextUtils.isEmpty(this.Q)) {
            if (!this.mClient.isConnected()) {
                this.mClient.connect();
            }
            List arrayList = new ArrayList();
            AppIndex.AppIndexApi.view(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.Q), this.TITLE, Uri.parse(this.R), arrayList);
        }
    }

    public void p() {
        if (!TextUtils.isEmpty(this.Q)) {
            AppIndex.AppIndexApi.viewEnd(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.Q));
            this.mClient.disconnect();
        }
    }

    private void E() {
        GaanaApplication.getInstance().updateMetadata();
        ColombiaAdViewManager.a().a((ColombiaAdViewManager.c) this);
        if (this.Y == null) {
            this.Y = new SponsorAdViewHolder(this.n);
        }
        final View view = this.Y.adView;
        view.setVisibility(8);
        if (!ap.a().b(this.mContext) || ap.a().d()) {
            view.setVisibility(8);
            return;
        }
        String str = "";
        if (this.p instanceof Playlist) {
            str = Constants.dH;
        } else if (this.p instanceof Album) {
            str = Constants.dI;
        }
        String str2 = str;
        if (com.managers.e.ad != 0 || !TextUtils.isEmpty(F())) {
            ColombiaAdViewManager.a().a(this.mContext, view, com.managers.e.A, str2, new AdsUJData[0]);
        } else if (com.managers.e.X == 0) {
            GaanaApplication.getInstance().setDFPAdSectionName("album_details_bottom");
            ColombiaAdViewManager.a().a(this.mContext, view, com.managers.e.A, str2, new AdsUJData[0]);
        } else {
            ColombiaManager.b().a(1, this.mContext, 28, -1, view, "album_details_material_fragment", new ColombiaManager.a() {
                public void onItemLoaded(Item item) {
                    view.setVisibility(0);
                }

                public void onItemRequestFailed(Exception exception) {
                    if (com.managers.e.Z == 0) {
                        ColombiaAdViewManager.a().a(AlbumDetailsMaterialListing.this.mContext, view, com.managers.e.A);
                    } else {
                        view.setVisibility(8);
                    }
                }
            }, str2);
        }
    }

    public void l() {
        E();
    }

    private String F() {
        String str = "";
        if (this.p instanceof Album) {
            return ((Album) this.p).getChannelPageAdCode();
        }
        return this.p instanceof Playlist ? ((Playlist) this.p).getChannelPageAdCode() : str;
    }

    public void refreshDataandAds() {
        onRefresh();
    }

    public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
        b();
    }

    public void onTagClick(ArrayList<BusinessObject> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject instanceof Tags) {
                Tags tags = (Tags) businessObject;
                if (tags.isSelected()) {
                    arrayList2.add(tags);
                }
            } else if (businessObject instanceof TopArtists) {
                TopArtists topArtists = (TopArtists) businessObject;
                if (topArtists.isSelected()) {
                    arrayList3.add(topArtists);
                }
            } else if (businessObject instanceof TopLanguage) {
                TopLanguage topLanguage = (TopLanguage) businessObject;
                if (topLanguage.isSelected()) {
                    arrayList4.add(topLanguage);
                }
            }
        }
        ArrayList arrayList5 = new ArrayList();
        if (!(arrayList2.size() == 0 && arrayList3.size() == 0 && arrayList4.size() == 0)) {
            Iterator it2 = this.J.iterator();
            while (it2.hasNext()) {
                BusinessObject businessObject2 = (BusinessObject) it2.next();
                Track track = (Track) businessObject2;
                ArrayList tags2 = track.getTags();
                ArrayList artists = track.getArtists();
                String language = track.getLanguage();
                if (tags2 != null) {
                    Iterator it3;
                    int i;
                    Iterator it4;
                    if (arrayList2.size() != 0) {
                        it3 = arrayList2.iterator();
                        i = 0;
                        while (it3.hasNext()) {
                            Tags tags3 = (Tags) it3.next();
                            Iterator it5 = tags2.iterator();
                            while (it5.hasNext()) {
                                if (((Tags) it5.next()).getTag_id().equals(tags3.getTag_id())) {
                                    i++;
                                    break;
                                }
                            }
                        }
                    }
                    i = 0;
                    if (arrayList3.size() != 0) {
                        it4 = arrayList3.iterator();
                        while (it4.hasNext()) {
                            TopArtists topArtists2 = (TopArtists) it4.next();
                            it3 = artists.iterator();
                            while (it3.hasNext()) {
                                if (((Track.Artist) it3.next()).getArtist_id().equals(topArtists2.getTop_artistId())) {
                                    i++;
                                    break;
                                }
                            }
                        }
                    }
                    if (arrayList4.size() != 0) {
                        it4 = arrayList4.iterator();
                        while (it4.hasNext()) {
                            if (language.equals(((TopLanguage) it4.next()).getLang_name())) {
                                i++;
                                break;
                            }
                        }
                    }
                    if (i == (arrayList2.size() + arrayList3.size()) + arrayList4.size()) {
                        arrayList5.add(businessObject2);
                    }
                }
            }
        }
        this.I.clear();
        if (arrayList5.size() != 0) {
            this.I.addAll(arrayList5);
        } else if (arrayList2.size() == 0 && arrayList3.size() == 0 && arrayList4.size() == 0) {
            this.I.addAll(this.J);
        }
        this.j.clear();
        this.w = this.I.size();
        if (ap.a().b(this.mContext) && GaanaApplication.getInstance().getColombiaSdkInit() && TextUtils.isEmpty(F()) && com.managers.e.ad == 0) {
            int i2 = this.w;
            int i3 = 0;
            while (i3 < i2) {
                if (i3 == 4) {
                    this.j.add(Integer.valueOf(i3));
                    i2++;
                } else if (i3 != 0 && (i3 - 4) % 7 == 0) {
                    this.j.add(Integer.valueOf(i3));
                    i2++;
                }
                i3++;
            }
            if (this.j.isEmpty()) {
                this.j.add(Integer.valueOf(this.w));
            }
        }
        this.mAppState.setCurrentBusObjInListView(this.I);
        this.s.updateAdapterCount((((this.w + 1) + this.N) + this.j.size()) + 1);
        if (al.a().d()) {
            al.a().c();
            this.aI = this.w;
            this.y.updateSelectedCountinContextMode(this.w);
        }
    }
}
