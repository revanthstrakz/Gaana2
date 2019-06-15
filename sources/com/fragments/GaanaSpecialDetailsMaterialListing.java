package com.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbar.DetailsMaterialActionBar;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.collapsible_header.ObservableRecyclerView;
import com.collapsible_header.ScrollState;
import com.collapsible_header.d;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.adapter.CustomListAdapter.IAddListItemView;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.AdsUJData;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.gaana.models.UserMessage;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.BaseItemView.DetailListingItemHolder;
import com.gaana.view.item.BaseItemView.GaanaSpecialHolder;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.ItemEmptyMessageHolder;
import com.gaana.view.item.BaseItemView.SponsorAdViewHolder;
import com.gaana.view.item.DownloadSongsItemView.AlbumDetailItemHolder;
import com.gaana.view.item.GenericItemView;
import com.gaana.view.item.PopupWindowView;
import com.gaana.view.item.SimilarItemHorizontalScroll;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.library.managers.TaskManager.TaskListner;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.c;
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
import com.managers.an;
import com.managers.ap;
import com.managers.ap.a;
import com.managers.e;
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
import com.views.ColumbiaAdItemview;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GaanaSpecialDetailsMaterialListing extends BaseGaanaFragment implements OnRefreshListener, OnClickListener, d, IAddListItemView, c, a {
    private static final Type ab = new TypeToken<HashMap<String, Integer>>() {
    }.getType();
    private static float i = 1.2f;
    private int A;
    private View B;
    private View C;
    private View D;
    private TextView E;
    private TextView F;
    private ArrayList<BusinessObject> G = new ArrayList();
    private ArrayList<BusinessObject> H = new ArrayList();
    private String I = null;
    private String J = "";
    private View K;
    private int L = 0;
    private boolean M = false;
    private long N;
    private String O = "";
    private String P = "";
    private DisplayMetrics Q;
    private boolean R = false;
    private MenuItem S;
    private boolean T = false;
    private ColumbiaAdItemview U = null;
    private int V;
    private SponsorAdViewHolder W;
    private Menu X;
    private View Y;
    private Bundle Z = null;
    LinearLayout a = null;
    private String aa = "";
    private PublisherAdView ac;
    private int ad = 0;
    private LinearLayout ae;
    private LinearLayout af;
    public HashMap<String, Integer> b;
    String c = "0";
    String d = "";
    String e = "";
    ArrayList<Integer> f = new ArrayList();
    s g = new s() {
        public void onErrorResponse(BusinessObject businessObject) {
        }

        public void onRetreivalComplete(BusinessObject businessObject) {
            if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() != 0) {
                GaanaSpecialDetailsMaterialListing.this.a(businessObject);
            }
        }
    };
    OnCheckedChangeListener h = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            String stringBuilder;
            URLManager uRLManager = new URLManager();
            StringBuilder stringBuilder2;
            StringBuilder stringBuilder3;
            if (z) {
                GaanaSpecialDetailsMaterialListing.this.V = 1;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("https://apiv2.gaana.com/home/gaana-special/");
                stringBuilder2.append(GaanaSpecialDetailsMaterialListing.this.mAppState.getListingComponents().a().getBusinessObjId());
                stringBuilder2.append("/1");
                stringBuilder = stringBuilder2.toString();
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append("NotifyStatus:");
                stringBuilder3.append(GaanaSpecialDetailsMaterialListing.this.l.getName());
                ((BaseActivity) GaanaSpecialDetailsMaterialListing.this.mContext).sendGAEvent("GaanaSpecial", stringBuilder3.toString(), "Enable");
            } else {
                GaanaSpecialDetailsMaterialListing.this.V = 0;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("https://apiv2.gaana.com/home/gaana-special/");
                stringBuilder2.append(GaanaSpecialDetailsMaterialListing.this.mAppState.getListingComponents().a().getBusinessObjId());
                stringBuilder2.append("/0");
                stringBuilder = stringBuilder2.toString();
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append("NotifyStatus:");
                stringBuilder3.append(GaanaSpecialDetailsMaterialListing.this.l.getName());
                ((BaseActivity) GaanaSpecialDetailsMaterialListing.this.mContext).sendGAEvent("GaanaSpecial", stringBuilder3.toString(), "Disable");
            }
            uRLManager.a(stringBuilder);
            uRLManager.a(String.class);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.i(false);
            ((GaanaActivity) GaanaSpecialDetailsMaterialListing.this.mContext).showProgressDialog(Boolean.valueOf(true), GaanaSpecialDetailsMaterialListing.this.mContext.getString(R.string.updating_notify_status));
            i.a().a(new s() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(BusinessObject businessObject) {
                    ((GaanaActivity) GaanaSpecialDetailsMaterialListing.this.mContext).hideProgressDialog();
                    MoEngage instance;
                    if (GaanaSpecialDetailsMaterialListing.this.V == 1) {
                        instance = MoEngage.getInstance();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("GaanaSpecial-");
                        stringBuilder.append(GaanaSpecialDetailsMaterialListing.this.l.getBusinessObjId());
                        instance.MoSetUserAttributeInt(stringBuilder.toString(), 1);
                    } else {
                        instance = MoEngage.getInstance();
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("GaanaSpecial-");
                        stringBuilder2.append(GaanaSpecialDetailsMaterialListing.this.l.getBusinessObjId());
                        instance.MoSetUserAttributeInt(stringBuilder2.toString(), 0);
                    }
                    GaanaSpecialDetailsMaterialListing.this.b.put(GaanaSpecialDetailsMaterialListing.this.l.getBusinessObjId(), Integer.valueOf(GaanaSpecialDetailsMaterialListing.this.V));
                }
            }, uRLManager);
        }
    };
    private View j = null;
    private ListingComponents k;
    private BusinessObject l;
    private ObservableRecyclerView m;
    private CrossFadeImageView n;
    private View o;
    private CustomListAdapter p;
    private BaseItemView q;
    private SwipeRefreshLayout r;
    private boolean s = false;
    private int t = 0;
    private FloatingActionButton u;
    private MenuItem v;
    private DetailsMaterialActionBar w;
    private Toolbar x;
    private ProgressBar y;
    private View z = null;

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
            this.l = (BusinessObject) bundle.getSerializable("BUSINESS_OBJECT");
            if (this.l != null) {
                this.mAppState.setGADParameter(this.l.getBusinessObjId());
                Iterator it;
                ListingButton listingButton;
                StringBuilder stringBuilder;
                StringBuilder stringBuilder2;
                if (this.l instanceof Playlist) {
                    this.k = Constants.e();
                    it = this.k.c().iterator();
                    while (it.hasNext()) {
                        listingButton = (ListingButton) it.next();
                        if (this.l.isLocalMedia()) {
                            listingButton.c().d(this.l.isLocalMedia());
                        } else {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(listingButton.c().k());
                            stringBuilder.append("playlist_id=");
                            stringBuilder.append(this.l.getBusinessObjId());
                            stringBuilder.append("&playlist_type=");
                            stringBuilder.append(((Playlist) this.l).getPlaylistType());
                            listingButton.c().a(stringBuilder.toString());
                            if (DownloadManager.c().b(this.l).booleanValue()) {
                                listingButton.e(true);
                            }
                        }
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(Util.c(this.l.getBusinessObjType()));
                        stringBuilder2.append(this.l.getBusinessObjId());
                        AppsFlyer.getInstance().reportViewContent(this.l.getEnglishName(), "Playlist", stringBuilder2.toString());
                    }
                } else if (this.l instanceof Album) {
                    this.k = Constants.b();
                    it = this.k.c().iterator();
                    while (it.hasNext()) {
                        listingButton = (ListingButton) it.next();
                        if (this.l.isLocalMedia()) {
                            listingButton.c().d(this.l.isLocalMedia());
                        } else {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(listingButton.c().k());
                            stringBuilder.append(this.l.getBusinessObjId());
                            listingButton.c().a(stringBuilder.toString());
                            listingButton.c().d(this.l.isLocalMedia());
                            if (this.l.isLocalMedia() || DownloadManager.c().b(this.l).booleanValue()) {
                                listingButton.e(true);
                            }
                        }
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(Util.c(this.l.getBusinessObjType()));
                        stringBuilder2.append(this.l.getBusinessObjId());
                        AppsFlyer.getInstance().reportViewContent(this.l.getEnglishName(), "Album", stringBuilder2.toString());
                    }
                }
                this.k.b(this.l.getName());
                this.k.a(this.l);
                this.mAppState.setListingComponents(this.k);
                a((ListingButton) this.k.c().get(0));
                a(viewGroup);
                y();
                return true;
            }
        }
        ((GaanaActivity) this.mContext).popBackStack();
        return false;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.Z = null;
        if (this.l != null) {
            this.l.setArrListBusinessObj(null);
            bundle.putSerializable("BUSINESS_OBJECT", this.l);
        }
    }

    public final void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.R = false;
        this.Q = new DisplayMetrics();
        this.Z = bundle;
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.Q);
        if (this.j == null) {
            boolean a;
            this.N = Calendar.getInstance().getTimeInMillis();
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.I = getArguments().getString("DEEPLINKING_SCREEN_EXTRA_PARAM");
            if (Constants.da == null) {
                this.b = (HashMap) new Gson().fromJson(com.services.d.a().b("PREFERENCE_REFRESH_SPECIAL_GAANA_INFO_REQUIRED", "", false), ab);
                if (this.b == null) {
                    this.b = new HashMap();
                }
            } else {
                this.b = Constants.da;
            }
            if (bundle == null) {
                a = a(getArguments(), viewGroup);
            } else {
                a = a(bundle, viewGroup);
            }
            if (a) {
                this.y.setVisibility(0);
                r();
                if (this.l != null) {
                    n();
                }
            } else {
                ((GaanaActivity) this.mContext).popBackStack();
            }
        } else {
            this.mAppState.setListingComponents(this.k);
            if (this.G != null) {
                this.mAppState.setCurrentBusObjInListView(this.G);
            }
            if (((GaanaActivity) this.mContext).getRefreshData()) {
                ((GaanaActivity) this.mContext).setRefreshData(false);
                e();
            }
        }
        ((GaanaActivity) this.mContext).hideThemeBackground(false);
        if (this.r == null) {
            ((GaanaActivity) this.mContext).popBackStack();
        } else if (this.s) {
            this.r.setRefreshing(true);
        } else {
            this.r.setRefreshing(false);
        }
        if (this.l != null) {
            this.TITLE = this.l.getEnglishName();
            StringBuilder stringBuilder;
            StringBuilder stringBuilder2;
            if (this.l instanceof Playlist) {
                Playlist playlist = (Playlist) this.l;
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/playlist/");
                stringBuilder.append(playlist.getSeokey());
                this.P = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/playlist/");
                stringBuilder.append(playlist.getSeokey());
                this.O = stringBuilder.toString();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("PlaylistDetailScreen:");
                stringBuilder2.append(this.TITLE);
                this.aa = stringBuilder2.toString();
            } else if (this.l instanceof Album) {
                Album album = (Album) this.l;
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/album/");
                stringBuilder.append(album.getSeokey());
                this.P = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/album/");
                stringBuilder.append(album.getSeokey());
                this.O = stringBuilder.toString();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("AlbumDetailScreen:");
                stringBuilder2.append(this.TITLE);
                this.aa = stringBuilder2.toString();
            } else if (this.l instanceof Artist) {
                Artist artist = (Artist) this.l;
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/artist/");
                stringBuilder.append(artist.getSeokey());
                this.P = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/artist/");
                stringBuilder.append(artist.getSeokey());
                this.O = stringBuilder.toString();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("ArtistDetailScreen:");
                stringBuilder2.append(this.TITLE);
                this.aa = stringBuilder2.toString();
            }
            setGAScreenName(this.aa, this.aa);
        }
        return this.j;
    }

    public void onStart() {
        super.onStart();
        j();
    }

    public void a() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.favorite_tap_animation);
        loadAnimation.setInterpolator(new com.a.a(0.2d, 20.0d));
        this.u.startAnimation(loadAnimation);
    }

    public void onResume() {
        super.onResume();
        ((GaanaActivity) this.mContext).setFragment(this);
        if (this.loginStatus != this.mAppState.getCurrentUser().getLoginStatus()) {
            r();
            y();
            this.loginStatus = this.mAppState.getCurrentUser().getLoginStatus();
        }
        if (ap.a().b(this.mContext)) {
            ColombiaAdViewManager.a().a((c) this);
        }
        if (this.ac != null) {
            this.ac.resume();
        }
        if (!Constants.ab || this.l == null || this.l.isLocalMedia()) {
            this.u.setImageResource(R.drawable.vector_player_play_white);
        } else {
            this.u.setImageResource(R.drawable.vector_shuffle_white);
        }
    }

    public void onPause() {
        if (ap.a().b(this.mContext)) {
            ColombiaAdViewManager.a().a(null);
        }
        if (this.ac != null) {
            this.ac.pause();
        }
        super.onPause();
    }

    private void a(ViewGroup viewGroup) {
        this.j = setContentView(R.layout.gaana_special_details_material_listing, viewGroup);
        this.A = this.mContext.getResources().getDimensionPixelSize(R.dimen.detail_page_artwork);
        this.r = (SwipeRefreshLayout) this.j.findViewById(R.id.swipe_refresh_layout);
        this.r.setOnRefreshListener(this);
        this.m = (ObservableRecyclerView) this.j.findViewById(R.id.scroll);
        this.n = (CrossFadeImageView) this.j.findViewById(R.id.details_artwork);
        this.o = this.j.findViewById(R.id.img_background);
        this.C = this.j.findViewById(R.id.overlay);
        this.E = (TextView) this.j.findViewById(R.id.album_title);
        this.Y = this.j.findViewById(R.id.gaana_special_title_container);
        this.F = (TextView) this.j.findViewById(R.id.tvAlbumSongCount_Value);
        this.m.setScrollViewCallbacks(this);
        this.m.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.m.setHasFixedSize(false);
        this.u = (FloatingActionButton) this.j.findViewById(R.id.shuffle_play_button);
        this.K = this.j.findViewById(R.id.button_padding);
        this.u.setOnClickListener(this);
        this.B = LayoutInflater.from(this.mContext).inflate(R.layout.recycler_header, null);
        this.B.setLayoutParams(new LayoutParams(-1, this.A));
        this.B.post(new Runnable() {
            public void run() {
                GaanaSpecialDetailsMaterialListing.this.B.getLayoutParams().height = GaanaSpecialDetailsMaterialListing.this.A;
            }
        });
        this.p = new CustomListAdapter(this.mContext, this.B);
        this.p.setParamaters(0, this);
        this.m.setAdapter(this.p);
        this.x = (Toolbar) this.j.findViewById(R.id.main_toolbar);
        this.D = this.j.findViewById(R.id.toolbar_dummy_view);
        this.x.setContentInsetsAbsolute(0, 0);
        m();
        this.v = this.x.getMenu().findItem(R.id.menu_download);
        this.w = new DetailsMaterialActionBar(this.mContext);
        this.x.addView(this.w);
        this.w.setParams(this, this.l);
        this.w.showContextMenu(false);
        ((TextView) this.w.findViewById(R.id.title)).setText("");
        ((ImageView) this.w.findViewById(R.id.menu_icon)).setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.vec_actionbar_back));
        if (Constants.l) {
            DrawableCompat.setTint(((ImageView) this.w.findViewById(R.id.menu_icon)).getDrawable(), ViewCompat.MEASURED_STATE_MASK);
        } else {
            DrawableCompat.setTint(((ImageView) this.w.findViewById(R.id.menu_icon)).getDrawable(), -1);
        }
        this.w.setToolbar(this.x);
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.first_line_color});
        ((TextView) this.w.findViewById(R.id.title)).setTextColor(obtainStyledAttributes.getColor(0, -1));
        obtainStyledAttributes.recycle();
        this.y = (ProgressBar) this.j.findViewById(R.id.progressbar);
        this.E.setText(Constants.a(this.l.getName(), this.l.getLanguage()));
        if (Constants.l) {
            this.E.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.F.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            this.E.setTextColor(-1);
            this.F.setTextColor(-1);
        }
        this.E.setLayoutParams(new LayoutParams((int) (((float) this.Q.widthPixels) / 1.4f), -2));
        this.af = (LinearLayout) this.j.findViewById(R.id.ll_song_fav_count);
        this.ae = (LinearLayout) this.af.findViewById(R.id.ll_fav_parent);
        com.collapsible_header.i.g(this.C, (float) this.A);
        this.af.post(new Runnable() {
            public void run() {
                com.collapsible_header.i.g(GaanaSpecialDetailsMaterialListing.this.af, (float) ((int) ((((float) GaanaSpecialDetailsMaterialListing.this.A) - (((float) GaanaSpecialDetailsMaterialListing.this.af.getHeight()) * 1.2f)) - ((float) GaanaSpecialDetailsMaterialListing.this.K.getHeight()))));
                com.collapsible_header.i.b(GaanaSpecialDetailsMaterialListing.this.af, 0.0f);
                com.collapsible_header.i.c(GaanaSpecialDetailsMaterialListing.this.af, 0.0f);
            }
        });
        this.E.post(new Runnable() {
            public void run() {
                com.collapsible_header.i.g(GaanaSpecialDetailsMaterialListing.this.E, (float) ((int) ((((float) (GaanaSpecialDetailsMaterialListing.this.A - GaanaSpecialDetailsMaterialListing.this.E.getHeight())) - (((float) GaanaSpecialDetailsMaterialListing.this.af.getHeight()) * 1.2f)) - ((float) GaanaSpecialDetailsMaterialListing.this.K.getHeight()))));
                com.collapsible_header.i.b(GaanaSpecialDetailsMaterialListing.this.E, 0.0f);
                com.collapsible_header.i.c(GaanaSpecialDetailsMaterialListing.this.E, 0.0f);
                com.collapsible_header.i.d(GaanaSpecialDetailsMaterialListing.this.E, 1.2f);
                com.collapsible_header.i.e(GaanaSpecialDetailsMaterialListing.this.E, 1.2f);
            }
        });
        this.K.post(new Runnable() {
            public void run() {
                com.collapsible_header.i.g(GaanaSpecialDetailsMaterialListing.this.K, (float) (GaanaSpecialDetailsMaterialListing.this.A - GaanaSpecialDetailsMaterialListing.this.K.getHeight()));
            }
        });
        this.D.post(new Runnable() {
            public void run() {
                com.collapsible_header.i.a(GaanaSpecialDetailsMaterialListing.this.D, 0.0f);
            }
        });
        if (VERSION.SDK_INT >= 21) {
            this.C.setElevation((float) Util.b(20));
            this.Y.setElevation((float) Util.b(20));
            this.K.setElevation((float) Util.b(20));
            this.u.setElevation((float) Util.b(20));
            this.u.setCompatElevation((float) Util.b(20));
            this.x.setElevation((float) Util.b(20));
        }
    }

    private void m() {
        this.x.getMenu().clear();
        this.x.inflateMenu(R.menu.cast_menu_detail);
        ((BaseActivity) this.mContext).initializeMediaRouterButton(this.x.getMenu(), R.id.media_route_menu_item);
        this.X = this.x.getMenu();
        if (this.X != null) {
            this.S = this.X.findItem(R.id.media_route_menu_item);
            if (this.X != null && Constants.l) {
                DrawableCompat.setTint(this.X.findItem(R.id.menu_search).getIcon(), ViewCompat.MEASURED_STATE_MASK);
                DrawableCompat.setTint(this.X.findItem(R.id.menu_option).getIcon(), ViewCompat.MEASURED_STATE_MASK);
            }
        }
        b();
    }

    private void n() {
        String artwork = this.l instanceof Playlist ? ((Playlist) this.l).getArtwork() : this.l instanceof Album ? ((Album) this.l).getArtwork() : null;
        artwork = Util.e(this.mContext, artwork);
        if (this.l.isLocalMedia()) {
            this.n.bindImageForLocalMedia(artwork, null, new LocalMediaImageLoader(), false);
            return;
        }
        if (artwork != null && artwork.contains("80x80")) {
            artwork = artwork.replace("80x80", "480x480");
        } else if (artwork != null && artwork.contains("175x175")) {
            artwork = artwork.replace("175x175", "480x480");
        }
        i.a().a(artwork, new r() {
            public void onSuccessfulResponse(Bitmap bitmap) {
                if (!GaanaSpecialDetailsMaterialListing.this.isAdded()) {
                    return;
                }
                if (bitmap != null) {
                    GaanaSpecialDetailsMaterialListing.this.n.setImageBitmap(bitmap);
                } else {
                    GaanaSpecialDetailsMaterialListing.this.o();
                }
            }

            public void onErrorResponse(VolleyError volleyError) {
                if (GaanaSpecialDetailsMaterialListing.this.isAdded()) {
                    GaanaSpecialDetailsMaterialListing.this.o();
                }
            }
        });
        f();
    }

    public void b() {
        if (this.x != null) {
            Menu menu = this.x.getMenu();
            if (menu != null) {
                ImageView imageView = (ImageView) menu.findItem(R.id.menu_favourite).getActionView();
                if (n.a().a(this.l)) {
                    TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(51, -1)));
                    obtainStyledAttributes.recycle();
                } else if (Constants.l) {
                    imageView.setImageResource(R.drawable.vector_ab_favorite);
                } else {
                    imageView.setImageResource(R.drawable.vector_ab_favorite_white);
                }
            }
        }
    }

    private void o() {
        String str = "";
        if (this.l instanceof Playlist) {
            str = ((Playlist) this.l).getArtwork();
        } else if (this.l instanceof Album) {
            str = ((Album) this.l).getArtwork();
        }
        if (str != null && str.contains("480x480")) {
            str = str.replace("480x480", "175x175");
        }
        if (str != null && str.contains("80x80")) {
            str = str.replace("80x80", "175x175");
        }
        i.a().a(str, new r() {
            public void onErrorResponse(VolleyError volleyError) {
            }

            public void onSuccessfulResponse(Bitmap bitmap) {
                GaanaSpecialDetailsMaterialListing.this.a(bitmap, GaanaSpecialDetailsMaterialListing.this.n);
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
                    if (GaanaSpecialDetailsMaterialListing.this.isAdded()) {
                        int dimensionPixelSize = GaanaSpecialDetailsMaterialListing.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp160);
                        int i = (GaanaSpecialDetailsMaterialListing.this.Q.widthPixels - dimensionPixelSize) / 2;
                        Bitmap createBitmap = Bitmap.createBitmap(GaanaSpecialDetailsMaterialListing.this.Q.widthPixels, GaanaSpecialDetailsMaterialListing.this.Q.widthPixels, bitmap.getConfig());
                        Canvas canvas = new Canvas(createBitmap);
                        dimensionPixelSize += i;
                        Rect rect = new Rect(i, i, dimensionPixelSize, dimensionPixelSize);
                        Rect rect2 = new Rect(0, 0, GaanaSpecialDetailsMaterialListing.this.Q.widthPixels, GaanaSpecialDetailsMaterialListing.this.Q.widthPixels);
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
                p();
            }
        }
    }

    private void p() {
        if (this.l != null) {
            if (this.p != null) {
                this.p.notifyDataSetChanged();
            }
            if (!this.l.isLocalMedia()) {
                f();
            }
        }
    }

    public void refreshListView(BusinessObjectType businessObjectType) {
        p();
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        p();
    }

    public void refreshFavoriteCount(boolean z) {
        if (!this.l.isLocalMedia()) {
            String stringBuilder;
            super.refreshFavoriteCount(z);
            String str = "";
            if (this.l instanceof Album) {
                str = ((Album) this.l).getFavoriteCount();
            } else if (this.l instanceof Playlist) {
                str = ((Playlist) this.l).getFavoriteCount();
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
            if (this.l instanceof Album) {
                ((Album) this.l).setFavoriteCount(stringBuilder);
            } else if (this.l instanceof Playlist) {
                ((Playlist) this.l).setFavoriteCount(stringBuilder);
            }
            if (!(this.l.isLocalMedia() || DownloadManager.c().h(Integer.parseInt(this.l.getBusinessObjId())) == null || str == null || stringBuilder == null || str.equals(stringBuilder))) {
                DownloadManager.c().a(this.l.getBusinessObjId(), this.l);
            }
            q();
            refreshListView();
        }
    }

    /* Access modifiers changed, original: protected */
    public int c() {
        int[] iArr = new int[]{R.attr.actionBarSize};
        TypedArray obtainStyledAttributes = getActivity().obtainStyledAttributes(new TypedValue().data, iArr);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    private void q() {
        StringBuilder stringBuilder;
        String count = this.l.getCount();
        this.ae.removeAllViews();
        if (this.l instanceof Album) {
            this.c = ((Album) this.l).getFavoriteCount();
        } else if (this.l instanceof Playlist) {
            this.c = ((Playlist) this.l).getFavoriteCount();
        }
        if (TextUtils.isEmpty(count)) {
            count = "0";
        }
        if (Integer.parseInt(count.trim()) < 2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(Util.q(count));
            stringBuilder.append(" ");
            stringBuilder.append(this.mContext.getString(R.string.episode));
            stringBuilder.append(" ");
            this.d = stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(Util.q(count));
            stringBuilder.append(" ");
            stringBuilder.append(this.mContext.getString(R.string.episodes));
            stringBuilder.append(" ");
            this.d = stringBuilder.toString();
        }
        if (!TextUtils.isEmpty(this.c) && Integer.parseInt(this.c.trim()) > 0) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.d);
            stringBuilder2.append(" | + ");
            this.d = stringBuilder2.toString();
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(Util.q(this.c));
            stringBuilder2.append(" ");
            stringBuilder2.append(this.mContext.getString(R.string.fav));
            this.e = stringBuilder2.toString();
        }
        if (TextUtils.isEmpty(this.e)) {
            this.F.setText(this.d);
            return;
        }
        TextView textView = this.F;
        stringBuilder = new StringBuilder();
        stringBuilder.append(this.d);
        stringBuilder.append("");
        stringBuilder.append(this.e);
        textView.setText(stringBuilder.toString());
    }

    private void r() {
        this.N = Calendar.getInstance().getTimeInMillis();
        ListingButton listingButton = (ListingButton) this.k.c().get(0);
        URLManager c = listingButton.c();
        c.c(Boolean.valueOf(this.s));
        if (this.l.isLocalMedia()) {
            ((BaseActivity) this.mContext).getDownloadedBusinessObject((b) this, this.l.getBusinessObjId(), c);
        } else if (listingButton.l() && !this.s) {
            ((BaseActivity) this.mContext).getDownloadedBusinessObject((b) this, this.l.getBusinessObjId(), c);
        } else if ((this.l instanceof Playlist) && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) this.l)) {
            ((ListingButton) this.k.c().get(0)).c().c(Boolean.valueOf(this.s));
            ((BaseActivity) this.mContext).getMyPlaylistDetails((b) this, (Playlist) this.l, ((ListingButton) this.k.c().get(0)).c());
        } else {
            i.a().a(c, toString(), (b) this, (com.android.volley.i.a) this);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(ListingButton listingButton) {
        try {
            this.q = (BaseItemView) Class.forName(listingButton.f()).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.mContext, this});
        } catch (Exception unused) {
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public BusinessObject d() {
        return this.l;
    }

    public void e() {
        if (this.k != null && this.k.c() != null) {
            r();
            y();
        }
    }

    public void onStop() {
        super.onStop();
        k();
        if (al.a) {
            g();
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
            if (!this.T) {
                a(viewHolder);
                this.T = true;
            }
            return viewHolder.itemView;
        } else if (i == (this.t + 2) + this.f.size()) {
            return viewHolder.itemView;
        } else {
            if (this.f.contains(Integer.valueOf(i))) {
                if (viewHolder != null && viewHolder.getItemViewType() >= 7 && viewHolder.getItemViewType() < this.f.size() + 7) {
                    if (this.U == null) {
                        this.U = new ColumbiaAdItemview(this.mContext, this);
                    }
                    this.U.getPopulatedView(i, viewHolder.itemView, (ViewGroup) viewHolder.itemView.getParent(), this.l);
                }
                return viewHolder.itemView;
            }
            if (!al.a) {
                viewHolder.itemView.setOnLongClickListener(new OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        GaanaSpecialDetailsMaterialListing.this.a(view, GaanaSpecialDetailsMaterialListing.this.G.size());
                        GaanaSpecialDetailsMaterialListing.this.p.notifyDataSetChanged();
                        return true;
                    }
                });
            }
            return this.q.getPoplatedView(viewHolder, (BusinessObject) this.G.get(a(i) - 2), viewGroup);
        }
    }

    private int a(int i) {
        if (this.f.size() > 0 && i == this.t && i == ((Integer) this.f.get(this.f.size() - 1)).intValue()) {
            return i - 1;
        }
        Iterator it = this.f.iterator();
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
            itemEmptyMessageHolder = new ItemEmptyMessageHolder(this.q.getEmptyMsgView(userMessage, viewGroup));
        } else if (i == 4) {
            return new ItemAdViewHolder(t());
        } else {
            if (i == 2) {
                itemEmptyMessageHolder = new DetailListingItemHolder(s());
            } else if (i == 6) {
                itemEmptyMessageHolder = new GaanaSpecialHolder(LayoutInflater.from(this.mContext).inflate(R.layout.view_gaana_special_header, viewGroup, false));
            } else if (i < 7 || i >= this.f.size() + 7) {
                itemEmptyMessageHolder = new AlbumDetailItemHolder(this.q.createViewHolder(viewGroup, i));
            } else {
                if (this.U == null) {
                    this.U = new ColumbiaAdItemview(this.mContext, this);
                }
                itemEmptyMessageHolder = new ItemAdViewHolder(this.U.getNewView(0, viewGroup));
            }
        }
        return itemEmptyMessageHolder;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return this.M ? 5 : 4;
        } else {
            if (i == 1) {
                return 6;
            }
            if (i == (this.t + 2) + this.f.size()) {
                return 2;
            }
            if (this.f.contains(Integer.valueOf(i))) {
                return this.f.indexOf(Integer.valueOf(i)) + 7;
            }
            return 1;
        }
    }

    public void onRefresh() {
        if (!this.s) {
            this.r.setRefreshing(true);
            this.s = true;
            if (ap.a().b(this.mContext)) {
                ColombiaManager.b().c();
                if (this.U != null) {
                    this.U.a();
                }
            }
            r();
            y();
        }
    }

    private View s() {
        if (this.z == null) {
            this.z = new View(this.mContext);
        }
        return this.z;
    }

    private LinearLayout t() {
        if (this.a == null) {
            this.a = new LinearLayout(this.mContext);
            this.a.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
            this.a.setGravity(17);
            this.a.setBackgroundColor(getResources().getColor(R.color.gaana_grey));
        }
        return this.a;
    }

    public void onClick(View view) {
        if (view != this.u) {
            return;
        }
        if (!Constants.ab || this.l == null || this.l.isLocalMedia()) {
            af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.playMenu, d());
            return;
        }
        u.a().a("Shuffle Product", "Shuffle Play", this.l.getBusinessObjId());
        af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.shuffleMenu, this.l);
    }

    public void onDestroyView() {
        if (this.j.getParent() != null) {
            ((ViewGroup) this.j.getParent()).removeView(this.j);
        }
        com.services.d.a().a("PREFERENCE_REFRESH_SPECIAL_GAANA_INFO_REQUIRED", new Gson().toJson(this.b), false);
        if (this.ac != null) {
            this.ac.destroy();
        }
        super.onDestroyView();
        this.R = true;
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.s = false;
        super.onErrorResponse(volleyError);
        showNetworkErrorView(null);
        this.y.setVisibility(8);
    }

    public void onResponse(Object obj) {
        if (!this.R) {
            int i = 0;
            this.s = false;
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (this.N != 0) {
                long j = timeInMillis - this.N;
                String str = "";
                if (this.l.getBusinessObjType() == BusinessObjectType.Playlists) {
                    str = "Playlist detail";
                } else if (this.l.getBusinessObjType() == BusinessObjectType.Albums) {
                    str = "Album detail";
                }
                Constants.a("Load", j, str, null);
            }
            this.r.setRefreshing(false);
            this.y.setVisibility(8);
            this.C.setVisibility(0);
            if (Constants.l) {
                this.K.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.view_foreground_light));
                i = 1.0f;
                this.j.findViewById(R.id.overlay).setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.view_foreground_light));
            } else {
                this.K.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.view_background_dark));
                i = 1.0f;
                this.j.findViewById(R.id.overlay).setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.view_background_dark));
            }
            BusinessObject businessObject = (BusinessObject) obj;
            if (businessObject == null || businessObject.getArrListBusinessObj() == null) {
                this.p.updateAdapterCount(1);
                this.M = true;
            } else {
                ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                if (!(arrListBusinessObj == null || arrListBusinessObj.size() == 0)) {
                    try {
                        HashMap h = ((ListingButton) this.k.c().get(0)).c().h();
                        int i2 = (h == null || h.get("type") == null || !((String) h.get("type")).equals("mysongs")) ? 0 : 1;
                        if (i2 != 0 || (this.l instanceof Playlist)) {
                            for (i2 = arrListBusinessObj.size() - 1; i2 >= 0; i2--) {
                                Track track = (Track) arrListBusinessObj.get(i2);
                                if (track.getIslocal() != null && track.getIslocal().equals("1")) {
                                    track = LocalMediaManager.getInstance(this.mContext).getLocalTrackFromHash(track.getBusinessObjId());
                                    arrListBusinessObj.remove(i2);
                                    if (track != null) {
                                        arrListBusinessObj.add(i2, track);
                                    }
                                }
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
                if (arrListBusinessObj == null || arrListBusinessObj.size() == 0) {
                    this.p.updateAdapterCount(1);
                    this.M = true;
                    this.F.setText("");
                } else {
                    this.G = businessObject.getArrListBusinessObj();
                    this.H.clear();
                    this.H.addAll(this.G);
                    this.t = this.G.size();
                    this.M = false;
                    ((ListingButton) this.k.c().get(0)).a(this.H);
                    this.l.setArrListBusinessObj(arrListBusinessObj);
                    this.mAppState.setCurrentBusObjInListView(arrListBusinessObj);
                    this.m.setItemAnimator(new DefaultItemAnimator());
                    String str2 = "0";
                    Object obj2 = "0";
                    if (this.l instanceof Album) {
                        str2 = ((Album) this.l).getFavoriteCount();
                        obj2 = ((Tracks) businessObject).getFavoriteCount();
                        ((Album) this.l).setFavoriteCount(obj2);
                    } else if (this.l instanceof Playlist) {
                        str2 = ((Playlist) this.l).getFavoriteCount();
                        obj2 = ((Tracks) businessObject).getFavoriteCount();
                        ((Playlist) this.l).setFavoriteCount(obj2);
                    }
                    this.l.setCount(String.valueOf(businessObject.getArrListBusinessObj().size()));
                    q();
                    if (!(this.l.isLocalMedia() || DownloadManager.c().h(Integer.parseInt(this.l.getBusinessObjId())) == null)) {
                        if (this.l instanceof Playlist) {
                            ((Playlist) this.l).setLastModifiedDate(((Tracks) businessObject).getModifiedOn());
                            DownloadManager.c().d(this.l);
                        }
                        if (!(str2 == null || obj2 == null || str2.equals(obj2))) {
                            DownloadManager.c().a(this.l.getBusinessObjId(), this.l);
                        }
                    }
                    this.L = 2;
                    this.f.clear();
                    if (ap.a().b(this.mContext) && GaanaApplication.getInstance().getColombiaSdkInit() && TextUtils.isEmpty(z()) && e.ad == 0) {
                        int i3 = this.t;
                        while (i < i3) {
                            if (i == 4) {
                                this.f.add(Integer.valueOf(this.L + i));
                                i3++;
                            } else if (i != 0 && (i - 4) % 7 == 0) {
                                this.f.add(Integer.valueOf(this.L + i));
                                i3++;
                            }
                            i++;
                        }
                        if (this.f.isEmpty()) {
                            this.f.add(Integer.valueOf(this.t + this.L));
                        }
                    }
                    this.p.updateAdapterCount(((this.t + this.L) + this.f.size()) + 1);
                    u();
                    if (!this.l.isLocalMedia()) {
                        w();
                    }
                }
            }
        }
    }

    private void u() {
        if ((this.l instanceof Album) || (this.l instanceof Playlist)) {
            this.J = "";
            if (this.l instanceof Album) {
                this.J = ((Album) this.l).getChannelPageAdCode();
            } else if (this.l instanceof Playlist) {
                this.J = ((Playlist) this.l).getChannelPageAdCode();
            }
            if (!(this.J == null || TextUtils.isEmpty(this.J))) {
                v();
            }
        }
        if (this.Z == null && !TextUtils.isEmpty(this.I)) {
            if (this.I.contains("play")) {
                String[] split = this.I.split("/");
                if (split.length > 1) {
                    BusinessObject businessObject;
                    String str = split[1];
                    ArrayList g = ((ListingButton) this.k.c().get(0)).g();
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
                    af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.playMenu, d());
                }
            } else if (this.I.contains("download")) {
                a(false);
            }
            this.I = null;
        }
    }

    public void a(final boolean z) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else if (Util.j(this.mContext)) {
            if (ap.a().a(this.l, null)) {
                b(z);
            } else {
                String str = "";
                if (ap.a().l()) {
                    str = this.l instanceof Track ? "tr" : "pl";
                }
                Util.b(this.mContext, str, null, new as() {
                    public void onTrialSuccess() {
                        GaanaSpecialDetailsMaterialListing.this.b(z);
                        GaanaSpecialDetailsMaterialListing.this.refreshDataandAds();
                        GaanaSpecialDetailsMaterialListing.this.showSnackbartoOpenMyMusic();
                        ((GaanaActivity) GaanaSpecialDetailsMaterialListing.this.mContext).updateSideBar();
                    }
                });
            }
        } else {
            ap.a().f(this.mContext);
        }
    }

    private void b(boolean z) {
        Util.i(this.mContext, "Download");
        BusinessObject businessObject = this.l;
        this.l.setArrListBusinessObj(((ListingButton) this.k.c().get(0)).g());
        final BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
        boolean b = com.services.d.a().b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
        DownloadStatus h = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
        if (h == null || h == DownloadStatus.TRIED_BUT_FAILED || h == DownloadStatus.PAUSED || h == DownloadStatus.PARTIALLY_DOWNLOADED) {
            if (Util.k(GaanaApplication.getContext()) == 0) {
                if (!com.services.d.a().b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                    ((BaseActivity) this.mContext).mDialog = new f(this.mContext);
                    ((BaseActivity) this.mContext).mDialog.a(this.mContext.getString(R.string.gaana_plus_feature), this.mContext.getString(R.string.sync_data_connection_disabled), Boolean.valueOf(true), this.mContext.getString(R.string.settings), this.mContext.getString(R.string.cancel), new f.b() {
                        public void onCancelListner() {
                        }

                        public void onOkListner(String str) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            ((BaseActivity) GaanaSpecialDetailsMaterialListing.this.mContext).sendGAEvent("GaanaPlus", "BuySubscription", "Others");
                            ((GaanaActivity) GaanaSpecialDetailsMaterialListing.this.mContext).displayFragment(settingsDetailFragment);
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
                                PopupWindowView.getInstance(GaanaSpecialDetailsMaterialListing.this.mContext, currentFragment).dismiss(true);
                                return;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            PopupWindowView.getInstance(GaanaSpecialDetailsMaterialListing.this.mContext, currentFragment).dismiss(true);
                            ((GaanaActivity) GaanaSpecialDetailsMaterialListing.this.mContext).displayFragment(settingsDetailFragment);
                        }
                    });
                }
            }
            if (this.l.getArrListBusinessObj() == null || this.l.getArrListBusinessObj().size() == 0) {
                aj.a().a(this.mContext, this.mContext.getString(R.string.download_no_songs_available));
                return;
            }
            if (h == null) {
                DownloadManager.c().a(businessObject, this.mContext);
            } else {
                DownloadManager.c().c(businessObject);
            }
            a(Boolean.valueOf(false));
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.button_inqueue});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            this.v.setIcon(drawable);
        } else if (z) {
            if (h == DownloadStatus.QUEUED || h == DownloadStatus.DOWNLOADING) {
                new f(this.mContext).a(this.mContext.getString(R.string.gaana), this.mContext.getString(R.string.pause_album_download), Boolean.valueOf(true), this.mContext.getString(R.string.yes), this.mContext.getString(R.string.no), new f.b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        DownloadManager.c().r(Integer.parseInt(GaanaSpecialDetailsMaterialListing.this.l.getBusinessObjId()));
                        new int[1][0] = R.attr.button_resume;
                        TypedArray obtainStyledAttributes = GaanaSpecialDetailsMaterialListing.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        Drawable drawable = ContextCompat.getDrawable(GaanaSpecialDetailsMaterialListing.this.getContext(), obtainStyledAttributes.getResourceId(10, -1));
                        obtainStyledAttributes.recycle();
                        GaanaSpecialDetailsMaterialListing.this.v.setIcon(drawable);
                    }
                }, false);
            } else if (h == DownloadStatus.DOWNLOADED) {
                new f(this.mContext).a(this.mContext.getString(R.string.gaana), this.mContext.getString(R.string.remove_album_from_download), Boolean.valueOf(true), "Yes", "No", new f.b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        DownloadManager.c().p(Integer.parseInt(GaanaSpecialDetailsMaterialListing.this.l.getBusinessObjId()));
                        DownloadManager.c().d(Integer.parseInt(GaanaSpecialDetailsMaterialListing.this.l.getBusinessObjId()));
                        GaanaSpecialDetailsMaterialListing.this.a(Boolean.valueOf(false));
                        TypedArray obtainStyledAttributes = GaanaSpecialDetailsMaterialListing.this.mContext.obtainStyledAttributes(new int[]{R.attr.menu_download_paused});
                        obtainStyledAttributes.getDrawable(0);
                        obtainStyledAttributes.recycle();
                    }
                }, false);
            }
        }
    }

    private void a(Boolean bool) {
        if (this.l != null) {
            ((BaseActivity) this.mContext).refreshListView();
            a(this.v, this.l);
        }
    }

    public void f() {
        if (this.l != null) {
            a(this.v, this.l);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(MenuItem menuItem, BusinessObject businessObject) {
        if (!this.l.isLocalMedia()) {
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
        if (this.v != null) {
            TypedArray obtainStyledAttributes;
            Drawable drawable;
            if (downloadStatus == DownloadStatus.DOWNLOADING) {
                if (DownloadManager.c().w()) {
                    new int[1][0] = R.attr.button_downloding;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    this.v.setIcon(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(8, -1)));
                    obtainStyledAttributes.recycle();
                } else {
                    new int[1][0] = R.attr.button_inqueue;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(9, -1));
                    obtainStyledAttributes.recycle();
                    this.v.setIcon(drawable);
                }
            } else if (downloadStatus == DownloadStatus.DOWNLOADED) {
                new int[1][0] = R.attr.button_downloded;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(7, -1));
                obtainStyledAttributes.recycle();
                this.v.setIcon(drawable);
            } else if (downloadStatus == DownloadStatus.PAUSED || downloadStatus == DownloadStatus.PARTIALLY_DOWNLOADED) {
                new int[1][0] = R.attr.button_resume;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(10, -1));
                obtainStyledAttributes.recycle();
                this.v.setIcon(drawable);
            } else if (downloadStatus == DownloadStatus.QUEUED) {
                new int[1][0] = R.attr.button_inqueue;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(9, -1));
                obtainStyledAttributes.recycle();
                this.v.setIcon(drawable);
            } else if (downloadStatus == DownloadStatus.TRIED_BUT_FAILED) {
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(91, -1));
                obtainStyledAttributes.recycle();
                this.v.setIcon(drawable);
            } else if (Constants.l) {
                this.v.setIcon(this.mContext.getResources().getDrawable(R.drawable.vector_ab_download));
            } else {
                this.v.setIcon(this.mContext.getResources().getDrawable(R.drawable.vector_ab_download_white));
            }
        }
    }

    private void v() {
        if (isAdded() && ap.a().p() && !TextUtils.isEmpty(Constants.j)) {
            String str = Constants.j;
            this.ac = new PublisherAdView(this.mContext);
            this.ac.setAdUnitId(str);
            AdSize[] adSizeArr = new AdSize[]{new AdSize(ModuleDescriptor.MODULE_VERSION, 100), new AdSize(ModuleDescriptor.MODULE_VERSION, 140), new AdSize(ModuleDescriptor.MODULE_VERSION, 150), new AdSize(340, 100), new AdSize(340, 140), new AdSize(340, 150), new AdSize(728, 100), new AdSize(728, 140), new AdSize(728, 150), new AdSize(468, 100), new AdSize(468, 140), new AdSize(468, 150)};
            final AdsUJData adsUJData = new AdsUJData();
            adsUJData.setSectionName(this.aa);
            adsUJData.setAdUnitCode(str);
            adsUJData.setSectionId("");
            adsUJData.setAdType("dfp");
            this.ac.setAdSizes(adSizeArr);
            this.ac.setAdListener(new AdListener() {
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
                        GaanaSpecialDetailsMaterialListing.this.a = GaanaSpecialDetailsMaterialListing.this.t();
                        GaanaSpecialDetailsMaterialListing.this.a.removeAllViews();
                        GaanaSpecialDetailsMaterialListing.this.a.addView(GaanaSpecialDetailsMaterialListing.this.ac);
                        if (adsUJData != null) {
                            an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", TtmlNode.END, adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
                        }
                    } catch (Exception unused) {
                    }
                }
            });
            if (adsUJData != null) {
                try {
                    an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", "start", adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
                } catch (Exception unused) {
                }
            }
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
                this.ac.loadAd(builder.setLocation(location2).build());
            } else {
                Builder builder2 = new Builder();
                if (this.mAppState.getNetworkExtrasBundle() != null) {
                    builder2.addNetworkExtras(this.mAppState.getNetworkExtrasBundle());
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(Util.l(GaanaApplication.getContext()));
                stringBuilder2.append("Gaana ");
                builder2.setPublisherProvidedId(Util.c(stringBuilder2.toString()));
                this.ac.loadAd(builder2.build());
            }
        }
    }

    private void w() {
        String stringBuilder;
        URLManager uRLManager = new URLManager();
        URLManager c = ((ListingButton) this.mAppState.getListingComponents().c().get(0)).c();
        BusinessObjectType l = c != null ? c.l() : null;
        uRLManager.a(l);
        StringBuilder stringBuilder2;
        if (l == BusinessObjectType.Albums) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("https://api.gaana.com/index.php?type=album&subtype=similar_album&album_id=");
            stringBuilder2.append(this.mAppState.getListingComponents().a().getBusinessObjId());
            stringBuilder = stringBuilder2.toString();
        } else if (l == BusinessObjectType.Playlists) {
            uRLManager.a(BusinessObjectType.GenericItems);
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("https://apiv2.gaana.com/home/gaana-special/other-playlist/");
            stringBuilder2.append(this.mAppState.getListingComponents().a().getBusinessObjId());
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("https://api.gaana.com/index.php?type=artist&subtype=similar_artist&artist_id=");
            stringBuilder2.append(this.mAppState.getListingComponents().a().getBusinessObjId());
            stringBuilder = stringBuilder2.toString();
        }
        uRLManager.a(stringBuilder);
        i.a().a(this.g, uRLManager);
    }

    private void a(BusinessObject businessObject) {
        if (((ListingButton) this.k.c().get(0)).g().size() > 3) {
            this.z = new SimilarItemHorizontalScroll(this.mContext, this).populateSimilar(businessObject, GenericItemView.class.getName(), this.mContext.getString(R.string.you_may_also_like));
            this.z.setLayoutParams(new ViewGroup.LayoutParams(this.m.getWidth(), -2));
            this.p.notifyDataSetChanged();
        }
    }

    public void onScrollChanged(int i, boolean z, boolean z2) {
        if (i > this.Q.widthPixels) {
            onScrollChanged(this.Q.widthPixels, false, false);
        } else if (((LinearLayoutManager) this.m.getLayoutManager()).findFirstVisibleItemPosition() != 0) {
            i = (int) (-this.B.getY());
        }
        float c = (float) (this.A - c());
        int i2 = -i;
        float c2 = (float) (c() - this.C.getHeight());
        com.collapsible_header.i.g(this.C, com.collapsible_header.f.a((float) i2, c2, (float) c()));
        float f = (float) (i2 / 2);
        com.collapsible_header.i.g(this.n, com.collapsible_header.f.a(f, c2, 0.0f));
        com.collapsible_header.i.g(this.o, com.collapsible_header.f.a(f, c2, 0.0f));
        float f2 = (float) i;
        f = f2 / c;
        com.collapsible_header.i.a(this.C, com.collapsible_header.f.a(f, 0.0f, 1.0f));
        com.collapsible_header.i.a(this.K, com.collapsible_header.f.a(i - f, 0.0f, 1.0f));
        c2 = 0.7f + com.collapsible_header.f.a((c - f2) / c, 0.0f, 0.5f);
        x();
        com.collapsible_header.i.c(this.E, 0.0f);
        c = (float) (((int) (((((float) this.A) - (((float) this.E.getHeight()) * c2)) - ((float) this.af.getHeight())) - ((float) this.K.getHeight()))) + i2);
        com.collapsible_header.i.g(this.E, com.collapsible_header.f.a(c, ((float) (c() / 2)) - ((((float) this.E.getHeight()) * c2) / 1.5f), (float) this.A));
        com.collapsible_header.i.g(this.af, com.collapsible_header.f.a(c, 0.0f, (float) (this.A - this.K.getHeight())));
        c = com.collapsible_header.f.a((float) ((i2 + this.A) - this.K.getHeight()), 0.0f, (float) this.A);
        com.collapsible_header.i.g(this.K, c);
        if (((double) c) < ((double) c()) * 1.5d) {
            this.u.hide();
        } else {
            this.u.show();
        }
        if (this.S != null) {
            boolean isVisible = this.S.isVisible();
        }
        if (c < ((float) (c() * 2))) {
            this.af.setVisibility(4);
            this.E.setLayoutParams(new LayoutParams((int) (((float) this.Q.widthPixels) / 2.5f), -2));
            com.collapsible_header.i.f(this.E, com.collapsible_header.f.a((float) (i / 6), (float) (c() / 3), ((float) c()) / 1.4f));
            com.collapsible_header.i.d(this.E, com.collapsible_header.f.a(c2, 0.8f, 1.0f));
            com.collapsible_header.i.e(this.E, com.collapsible_header.f.a(c2, 0.8f, 1.0f));
            return;
        }
        this.af.setVisibility(0);
        float f3 = (float) (i / 6);
        com.collapsible_header.i.f(this.E, com.collapsible_header.f.a(f3, 0.0f, (float) c()));
        com.collapsible_header.i.f(this.af, com.collapsible_header.f.a(f3, 0.0f, (float) c()));
        com.collapsible_header.i.d(this.E, c2);
        com.collapsible_header.i.e(this.E, c2);
        this.E.setLayoutParams(new LayoutParams(this.Q.widthPixels, -2));
    }

    @TargetApi(17)
    private void x() {
        Configuration configuration = getResources().getConfiguration();
        if (17 > VERSION.SDK_INT || configuration.getLayoutDirection() != 1) {
            com.collapsible_header.i.b(this.E, 0.0f);
        } else {
            com.collapsible_header.i.b(this.E, (float) this.j.getWidth());
        }
    }

    private void a(View view, int i) {
        b(view, i);
    }

    private void b(View view, int i) {
        this.ad = i;
        this.w.showContextMenu(true);
        al.a().a(true);
        al.a().a((BusinessObject) view.getTag(), true);
        ((CheckBox) view.findViewById(R.id.f35download.item.checkbox)).setChecked(true);
        h();
    }

    public void g() {
        this.ad = 0;
        this.w.showContextMenu(false);
        al.a().a(false);
        al.a().c();
        this.p.notifyDataSetChanged();
    }

    public void h() {
        this.w.updateSelectedCountinContextMode(this.ad);
    }

    public void i() {
        if (al.a().e()) {
            al.a().c();
        } else {
            al.a().a(this.G);
        }
        this.p.notifyDataSetChanged();
        h();
    }

    public void j() {
        if (!TextUtils.isEmpty(this.O)) {
            if (!this.mClient.isConnected()) {
                this.mClient.connect();
            }
            List arrayList = new ArrayList();
            AppIndex.AppIndexApi.view(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.O), this.TITLE, Uri.parse(this.P), arrayList);
        }
    }

    public void k() {
        if (!TextUtils.isEmpty(this.O)) {
            AppIndex.AppIndexApi.viewEnd(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.O));
            this.mClient.disconnect();
        }
    }

    private void a(ViewHolder viewHolder) {
        GaanaSpecialHolder gaanaSpecialHolder = (GaanaSpecialHolder) viewHolder;
        gaanaSpecialHolder.mDescription.setText(((Playlist) this.l).getPlaylistDetailedDescription());
        if (this.b.containsKey(this.l.getBusinessObjId())) {
            SwitchCompat switchCompat = gaanaSpecialHolder.mSwitch;
            boolean z = true;
            if (((Integer) this.b.get(this.l.getBusinessObjId())).intValue() != 1) {
                z = false;
            }
            switchCompat.setChecked(z);
        } else {
            gaanaSpecialHolder.mSwitch.setChecked(((Playlist) this.l).isNotifyStatus());
            this.b.put(this.l.getBusinessObjId(), Integer.valueOf(((Playlist) this.l).isNotifyStatus()));
        }
        gaanaSpecialHolder.mSwitch.setOnCheckedChangeListener(this.h);
    }

    private void y() {
        ColombiaAdViewManager.a().a((c) this);
        if (this.W == null) {
            this.W = new SponsorAdViewHolder(this.j);
        }
        final View view = this.W.adView;
        view.setVisibility(8);
        if (ap.a().b(this.mContext)) {
            if (e.ad != 0 || !TextUtils.isEmpty(z())) {
                ColombiaAdViewManager.a().a(this.mContext, view, e.A, "PL_320x50", new AdsUJData[0]);
            } else if (e.X == 0) {
                ColombiaAdViewManager.a().a(this.mContext, view, e.A, "PL_320x50", new AdsUJData[0]);
            } else {
                ColombiaManager.b().a("PL", this.l.getBusinessObjId());
                ColombiaManager.b().a(1, this.mContext, 28, -1, view, "gaana_special_details_material_fragment", new ColombiaManager.a() {
                    public void onItemLoaded(Item item) {
                        view.setVisibility(0);
                    }

                    public void onItemRequestFailed(Exception exception) {
                        if (e.Z == 0) {
                            ColombiaAdViewManager.a().a(GaanaSpecialDetailsMaterialListing.this.mContext, view, e.A, "PL_320x50", new AdsUJData[0]);
                        } else {
                            view.setVisibility(8);
                        }
                    }
                }, "PL_320x50");
            }
            return;
        }
        view.setVisibility(8);
    }

    public void l() {
        y();
    }

    private String z() {
        String str = "";
        if (this.l instanceof Album) {
            return ((Album) this.l).getChannelPageAdCode();
        }
        return this.l instanceof Playlist ? ((Playlist) this.l).getChannelPageAdCode() : str;
    }

    public void refreshDataandAds() {
        onRefresh();
    }

    public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
        b();
    }
}
