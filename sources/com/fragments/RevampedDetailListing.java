package com.fragments;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbar.BaseContextualActionBar;
import com.android.volley.Request.Priority;
import com.android.volley.VolleyError;
import com.collapsible_header.ObservableRecyclerView;
import com.collapsible_header.ScrollState;
import com.collapsible_header.d;
import com.collapsible_header.f;
import com.constants.Constants;
import com.constants.Constants.ErrorType;
import com.constants.Constants.REVAMPED_DETAIL_TYPE;
import com.constants.c;
import com.exoplayer2.ui.VideoPlayerAutoPlayView;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.ads.base.ILifeCycleAwareCustomView;
import com.gaana.ads.interstitial.IAdType;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.models.AdsUJData;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.models.Tracks.Track.Tags;
import com.gaana.models.Tracks.Track.TopArtists;
import com.gaana.models.Tracks.Track.TopLanguage;
import com.gaana.revampeddetail.actionbar.RevampDetailMaterialActionBar;
import com.gaana.revampeddetail.adapter.RevampedCarouselPagerAdapter;
import com.gaana.revampeddetail.adapter.RevampedDetailListAdapter;
import com.gaana.revampeddetail.manager.RevampedDetailObjectManager;
import com.gaana.revampeddetail.model.RevampedDetailObject;
import com.gaana.revampeddetail.model.RevampedDetailObject.RevampedSectionData;
import com.gaana.revampeddetail.view.RevampedDetailCarouselView;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.DiscoverItemView;
import com.gaana.view.item.DownloadSongsItemView;
import com.gaana.view.item.PopupWindowView;
import com.google.android.gms.appindexing.AppIndex;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.ColombiaAdViewManager;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.af;
import com.managers.aj;
import com.managers.al;
import com.managers.an;
import com.managers.ap;
import com.managers.ap.a;
import com.managers.e;
import com.managers.u;
import com.moengage.ActionMapperConstants;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.n;
import com.player_framework.o;
import com.services.f.b;
import com.services.l;
import com.services.l.as;
import com.services.l.s;
import com.utilities.Util;
import com.views.i;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class RevampedDetailListing extends BaseGaanaFragment implements OnRefreshListener, d, a, l.a {
    public static HashMap<Integer, VideoPlayerAutoPlayView> d;
    private String A = "";
    private String B = "";
    private String C = "";
    private String D = "";
    private String E = "";
    private int F;
    private int G;
    private int H;
    private View I;
    private int J;
    private TabLayout K;
    private String L;
    private RevampedDetailCarouselView M;
    private String N = null;
    private ProgressBar O;
    private boolean P = false;
    private View Q = null;
    private int R = 0;
    private BaseItemView S;
    private boolean T = false;
    private TextView U = null;
    private ILifeCycleAwareCustomView V;
    RevampDetailMaterialActionBar a;
    TextView b;
    public String c = "";
    Button e;
    ImageView f;
    ImageView g;
    ImageView h;
    RevampedCarouselPagerAdapter i;
    n j = new n() {
        public void displayErrorDialog(String str, ErrorType errorType) {
        }

        public void displayErrorToast(String str, int i) {
        }

        public void onPlayNext(boolean z, boolean z2) {
        }

        public void onPlayPrevious(boolean z, boolean z2) {
        }

        public void onPlayerRepeatReset(boolean z) {
        }

        public void onStreamingQualityChanged(int i) {
        }

        public void onPlayerPlay() {
            RevampedDetailListing.this.b(true);
        }

        public void onPlayerPause() {
            RevampedDetailListing.this.b(false);
        }

        public void onPlayerResume() {
            RevampedDetailListing.this.b(true);
        }

        public void onPlayerStop() {
            RevampedDetailListing.this.b(false);
        }
    };
    private boolean k = false;
    private DisplayMetrics l;
    private Bundle m = null;
    private View n = null;
    private RevampedDetailObject o;
    private ObservableRecyclerView p;
    private LinearLayout q;
    private ViewGroup r;
    private SwipeRefreshLayout s;
    private boolean t = false;
    private Toolbar u;
    private ViewGroup v;
    private BusinessObject w = null;
    private RevampedDetailListAdapter x;
    private ArrayList<BusinessObject> y = new ArrayList();
    private RevampedDetailObjectManager z;

    public void e() {
    }

    public void onDownMotionEvent() {
    }

    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

    public static Bundle a(BusinessObject businessObject, String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("BUSINESS_OBJECT", businessObject);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
        }
        bundle.putInt("EXTRA_REVAMPED_DETAIL_TYPE", i);
        return bundle;
    }

    public ObservableRecyclerView a() {
        return this.p;
    }

    public SwipeRefreshLayout b() {
        return this.s;
    }

    public RevampedDetailListAdapter c() {
        return this.x;
    }

    public String d() {
        return this.L;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.k = false;
        this.l = new DisplayMetrics();
        this.m = bundle;
        if (this.n == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            if (bundle == null) {
                this.P = true;
            }
            this.n = setContentView(R.layout.revamped_detail_listing, viewGroup);
            this.N = getArguments().getString("DEEPLINKING_SCREEN_EXTRA_PARAM");
            this.Q = this.n.findViewById(R.id.remove_ad_cta);
            this.Q.setVisibility(8);
            if (bundle == null) {
                bundle = getArguments();
            }
            this.w = (BusinessObject) bundle.getSerializable("BUSINESS_OBJECT");
            ColombiaAdViewManager.a().e();
            this.F = bundle.getInt("EXTRA_REVAMPED_DETAIL_TYPE");
            this.z = new RevampedDetailObjectManager(this.F);
            x();
            if (this.w != null) {
                int color;
                if (Constants.l) {
                    color = getResources().getColor(R.color.view_foreground_light);
                } else {
                    color = getResources().getColor(R.color.view_background_dark);
                }
                this.n.setBackgroundColor(color);
                this.u.setBackgroundColor(color);
                this.q.setBackgroundColor(color);
            }
            a(this.w.getBusinessObjId(), this.F);
            r();
        } else if (!(this.p == null || this.p.getAdapter() == null)) {
            this.p.getAdapter().notifyDataSetChanged();
        }
        d = new HashMap();
        if (this.w != null) {
            this.TITLE = this.w.getEnglishName();
            this.D = this.w.getBusinessObjId();
            StringBuilder stringBuilder;
            StringBuilder stringBuilder2;
            if (this.w instanceof Playlist) {
                Playlist playlist = (Playlist) this.w;
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/playlist/");
                stringBuilder.append(playlist.getSeokey());
                this.B = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/playlist/");
                stringBuilder.append(playlist.getSeokey());
                this.A = stringBuilder.toString();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("PlaylistDetailScreen:");
                stringBuilder2.append(this.TITLE);
                this.c = stringBuilder2.toString();
                this.C = "Playlist Detail Screen";
                this.E = "Playlist";
            } else if (this.w instanceof Album) {
                Album album = (Album) this.w;
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/album/");
                stringBuilder.append(album.getSeokey());
                this.B = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/album/");
                stringBuilder.append(album.getSeokey());
                this.A = stringBuilder.toString();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("AlbumDetailScreen:");
                stringBuilder2.append(this.TITLE);
                this.c = stringBuilder2.toString();
                this.C = "Album Detail Screen";
                this.E = "Album";
            } else if (this.w instanceof Artist) {
                Artist artist = (Artist) this.w;
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/artist/");
                stringBuilder.append(artist.getSeokey());
                this.B = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/artist/");
                stringBuilder.append(artist.getSeokey());
                this.A = stringBuilder.toString();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("ArtistDetailScreen:");
                stringBuilder2.append(this.TITLE);
                this.c = stringBuilder2.toString();
                this.C = "Artist Detail Screen";
                this.E = "Artist";
            } else if (this.w instanceof Radio) {
                Radio radio = (Radio) this.w;
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/radio/");
                stringBuilder.append(radio.getSeokey());
                this.B = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/radio/");
                stringBuilder.append(radio.getSeokey());
                this.A = stringBuilder.toString();
                if (radio.getType().equalsIgnoreCase(c.d.c)) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Radio Mirchi Detail Screen:");
                    stringBuilder2.append(this.TITLE);
                    this.c = stringBuilder2.toString();
                } else {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Gaana Radio Detail Screen:");
                    stringBuilder2.append(this.TITLE);
                    this.c = stringBuilder2.toString();
                }
                this.C = "Radio Detail Screen";
                this.E = "Radio";
            }
            setGAScreenName(this.c, this.c);
        }
        return this.n;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.m = null;
        if (this.w != null) {
            this.w.setArrListBusinessObj(null);
            bundle.putSerializable("BUSINESS_OBJECT", this.w);
            bundle.putInt("EXTRA_REVAMPED_DETAIL_TYPE", this.F);
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

    private void x() {
        o();
        this.G = this.mContext.getResources().getDimensionPixelSize(R.dimen.header_height);
        if ((this.w instanceof Album) || (this.w instanceof Playlist)) {
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp27);
            this.I = LayoutInflater.from(this.mContext).inflate(R.layout.recycler_header_revamped, null, false);
            this.I.setLayoutParams(new LayoutParams(-1, (this.G - f()) + dimensionPixelSize));
        } else {
            this.I = LayoutInflater.from(this.mContext).inflate(R.layout.recycler_header, null, false);
            this.I.setLayoutParams(new LayoutParams(-1, this.G - f()));
        }
        this.q = (LinearLayout) this.n.findViewById(R.id.carousal_parent_layout);
        this.O = (ProgressBar) this.n.findViewById(R.id.progressbarlisting);
        this.H = (int) this.mContext.getResources().getDimension(R.dimen.activity_vertical_margin_small);
        this.r = (ViewGroup) this.n.findViewById(R.id.bottom_layout);
        this.b = (TextView) this.n.findViewById(R.id.detail_info_text);
        this.p = (ObservableRecyclerView) this.n.findViewById(R.id.revamp_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, 1, false);
        linearLayoutManager.setAutoMeasureEnabled(false);
        linearLayoutManager.setItemPrefetchEnabled(false);
        this.p.setHasFixedSize(true);
        this.p.setLayoutManager(linearLayoutManager);
        this.p.setScrollViewCallbacks(this);
        this.p.setNestedScrollingEnabled(false);
        this.s = (SwipeRefreshLayout) this.n.findViewById(R.id.swipe_refresh_layout);
        this.s.setOnRefreshListener(this);
        this.u = (Toolbar) this.n.findViewById(R.id.main_toolbar);
        this.u.setContentInsetsAbsolute(0, 0);
        this.a = new RevampDetailMaterialActionBar(this.mContext);
        this.u.addView(this.a);
        this.K = new TabLayout(this.mContext);
        this.K.addOnTabSelectedListener(new OnTabSelectedListener() {
            public void onTabReselected(Tab tab) {
            }

            public void onTabUnselected(Tab tab) {
            }

            public void onTabSelected(Tab tab) {
                RevampedDetailListing.this.L = tab.getText().toString();
                an.a().a("click", "en", RevampedDetailListing.this.w.getBusinessObjId(), RevampedDetailListing.this.c, "", tab.getText().toString(), "", "");
            }
        });
        this.K.setTabMode(1);
        this.a.setParams(this, this.w);
        this.a.showContextMenu(false);
        setmToolbar(this.u);
        if (this.s == null) {
            ((GaanaActivity) this.mContext).popBackStack();
        } else if (this.t) {
            this.s.setRefreshing(true);
        } else {
            this.s.setRefreshing(false);
        }
        this.v = a(this.q);
        this.M = new RevampedDetailCarouselView(this.mContext, this);
        this.q.removeAllViews();
        this.q.addView(this.M.getPopulatedView());
        this.q.addView(this.v);
        this.i = this.M.getCarouselPagerAdapter();
        this.x = new RevampedDetailListAdapter(this.mContext, this, this.I, this);
        if (this.w instanceof Artist) {
            if (this.K.getParent() != null) {
                ((ViewGroup) this.K.getParent()).removeView(this.K);
            }
            this.q.addView(this.K);
            this.s.setVisibility(8);
            this.r.addView(this.x.initViewPagerUI(this.r, this.K));
        } else if ((this.w instanceof Playlist) || (this.w instanceof Album)) {
            this.x.setParentBusinessObject(this.w);
        }
    }

    public void refreshListView() {
        super.refreshListView();
        if (this.mContext != null) {
            i slidingPanelLayout = ((GaanaActivity) this.mContext).getSlidingPanelLayout();
            if (slidingPanelLayout == null || slidingPanelLayout.a() != 1) {
                y();
            }
            if ((this.w instanceof Artist) && this.x != null) {
                this.x.refreshArtistPager();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public int f() {
        if (this.J == 0) {
            int[] iArr = new int[]{R.attr.actionBarSize};
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new TypedValue().data, iArr);
            this.J = obtainStyledAttributes.getDimensionPixelSize(0, -1);
            obtainStyledAttributes.recycle();
        }
        return this.J;
    }

    public void refreshListView(BusinessObjectType businessObjectType) {
        y();
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        y();
    }

    public void refreshFavoriteCount(boolean z) {
        if (!this.w.isLocalMedia()) {
            String stringBuilder;
            super.refreshFavoriteCount(z);
            String str = "";
            if (this.w instanceof Album) {
                str = ((Album) this.w).getFavoriteCount();
            } else if (this.w instanceof Playlist) {
                str = ((Playlist) this.w).getFavoriteCount();
            } else if (this.w instanceof Radio) {
                str = ((Radio) this.w).getFavorite_count();
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
            if (this.w instanceof Album) {
                ((Album) this.w).setFavoriteCount(stringBuilder);
            } else if (this.w instanceof Playlist) {
                ((Playlist) this.w).setFavoriteCount(stringBuilder);
            } else if (this.w instanceof Radio) {
                ((Radio) this.w).setFavoriteCount(stringBuilder);
            }
            if (!(this.w.isLocalMedia() || (this.w instanceof Radio) || DownloadManager.c().h(Integer.parseInt(this.w.getBusinessObjId())) == null || str == null || stringBuilder == null || str.equals(stringBuilder))) {
                DownloadManager.c().a(this.w.getBusinessObjId(), this.w);
            }
            refreshListView();
        }
    }

    private void y() {
        if (this.w != null) {
            if (!(this.x == null || this.x.getCustomListAdapter() == null)) {
                this.x.updateTrackUI();
            }
            if (!this.w.isLocalMedia()) {
                r();
            }
        }
    }

    private void a(String str, int i) {
        if (!this.t) {
            ((GaanaActivity) this.mContext).showProgressDialog();
        }
        final URLManager uRLManager = new URLManager();
        uRLManager.a(RevampedDetailObject.class);
        uRLManager.a(b(str, i));
        uRLManager.b(Boolean.valueOf(true));
        uRLManager.f(true);
        uRLManager.c(Boolean.valueOf(this.t));
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.b(this.w.getBusinessObjType());
        uRLManager.a(Priority.HIGH);
        uRLManager.a(60);
        uRLManager.i(true);
        com.i.i.a().a(uRLManager, toString(), null, new com.android.volley.i.a() {
            public void onErrorResponse(VolleyError volleyError) {
                ((GaanaActivity) RevampedDetailListing.this.mContext).hideProgressDialog();
                RevampedDetailListing.this.D();
                RevampedDetailListing.this.t = false;
                RevampedDetailListing.this.showNetworkErrorView(null);
            }
        }, new com.android.volley.i.c() {
            public void a(Object obj, boolean z) {
                if (!RevampedDetailListing.this.k) {
                    z = RevampedDetailListing.this.t;
                    RevampedDetailListing.this.t = false;
                    RevampedDetailListing.this.s.setRefreshing(false);
                    ((GaanaActivity) RevampedDetailListing.this.mContext).hideProgressDialog();
                    RevampedDetailListing.this.D();
                    if (obj instanceof RevampedDetailObject) {
                        String isSponsored;
                        RevampedDetailListing.this.o = (RevampedDetailObject) obj;
                        RevampedDetailListing.this.mAppState.setGADParameter(RevampedDetailListing.this.w.getBusinessObjId());
                        ArrayList trackListifAvailable = RevampedDetailListing.this.o.getTrackListifAvailable();
                        if (trackListifAvailable != null && trackListifAvailable.size() > 0) {
                            Iterator it = trackListifAvailable.iterator();
                            while (it.hasNext()) {
                                ((BusinessObject) it.next()).setBusinessObjType(uRLManager.i());
                            }
                            RevampedDetailListing.this.y.clear();
                            RevampedDetailListing.this.y.addAll(trackListifAvailable);
                            RevampedDetailListing.this.w.setArrListBusinessObj(RevampedDetailListing.this.y);
                            if ((RevampedDetailListing.this.w instanceof Album) && TextUtils.isEmpty(((Album) RevampedDetailListing.this.w).getSeokey())) {
                                ((Album) RevampedDetailListing.this.w).setSeokey(((Album) RevampedDetailListing.this.o.getBusinessObject()).getSeokey());
                            }
                            if ((RevampedDetailListing.this.w instanceof Playlist) && TextUtils.isEmpty(((Playlist) RevampedDetailListing.this.w).getSeokey())) {
                                ((Playlist) RevampedDetailListing.this.w).setSeokey(((Playlist) RevampedDetailListing.this.o.getBusinessObject()).getSeokey());
                            }
                            RevampedDetailListing.this.mAppState.setCurrentBusObjInListView(trackListifAvailable);
                            RevampedDetailListing.this.w.setCount(String.valueOf(trackListifAvailable.size()));
                            RevampedDetailListing.this.b.setVisibility(8);
                        } else if (RevampedDetailListing.this.w instanceof Radio) {
                            if (TextUtils.isEmpty(((Radio) RevampedDetailListing.this.w).getSeokey())) {
                                ((Radio) RevampedDetailListing.this.w).setSeokey(((Radio) RevampedDetailListing.this.o.getBusinessObject()).getSeokey());
                            }
                        } else if (RevampedDetailListing.this.w instanceof Artist) {
                            RevampedDetailListing.this.p.setVisibility(8);
                            if (TextUtils.isEmpty(((Artist) RevampedDetailListing.this.w).getSeokey())) {
                                ((Artist) RevampedDetailListing.this.w).setSeokey(((Artist) RevampedDetailListing.this.o.getBusinessObject()).getSeokey());
                            }
                        }
                        Object obj2 = null;
                        if (RevampedDetailListing.this.o.getBusinessObject() != null) {
                            if (RevampedDetailListing.this.w instanceof Album) {
                                ((Album) RevampedDetailListing.this.w).setFavoriteCount(((Album) RevampedDetailListing.this.o.getBusinessObject()).getFavoriteCount());
                                if (RevampedDetailListing.this.P) {
                                    isSponsored = ((Album) RevampedDetailListing.this.o.getBusinessObject()).getIsSponsored();
                                    if (TextUtils.isEmpty(isSponsored) || !isSponsored.equals("1")) {
                                        GaanaApplication.getInstance().setIsCurrentALPLSponsored(false);
                                    } else {
                                        GaanaApplication.getInstance().setIsCurrentALPLSponsored(true);
                                    }
                                }
                                ((Album) RevampedDetailListing.this.w).setIs_sponsored(((Album) RevampedDetailListing.this.o.getBusinessObject()).getIsSponsored());
                            } else if (RevampedDetailListing.this.w instanceof Playlist) {
                                isSponsored = ((Playlist) RevampedDetailListing.this.w).getFavoriteCount();
                                ((Playlist) RevampedDetailListing.this.w).setFavoriteCount(((Playlist) RevampedDetailListing.this.o.getBusinessObject()).getFavoriteCount());
                                ((Playlist) RevampedDetailListing.this.w).setLastModifiedDate(((Playlist) RevampedDetailListing.this.o.getBusinessObject()).getLastModifiedDate());
                                if (RevampedDetailListing.this.P) {
                                    String isSponsored2 = ((Playlist) RevampedDetailListing.this.o.getBusinessObject()).getIsSponsored();
                                    if (TextUtils.isEmpty(isSponsored2) || !isSponsored2.equals("1")) {
                                        GaanaApplication.getInstance().setIsCurrentALPLSponsored(false);
                                    } else {
                                        GaanaApplication.getInstance().setIsCurrentALPLSponsored(true);
                                    }
                                }
                                ((Playlist) RevampedDetailListing.this.w).setIs_sponsored(((Playlist) RevampedDetailListing.this.o.getBusinessObject()).getIsSponsored());
                                if (TextUtils.isEmpty(RevampedDetailListing.this.w.getBusinessObjId()) && !TextUtils.isEmpty(RevampedDetailListing.this.o.getBusinessObjId())) {
                                    RevampedDetailListing.this.w.setBusinessObjId(RevampedDetailListing.this.o.getBusinessObjId());
                                }
                                RevampedDetailListing.this.a.setParams(RevampedDetailListing.this, RevampedDetailListing.this.w);
                            } else if (RevampedDetailListing.this.w instanceof Radio) {
                                ((Radio) RevampedDetailListing.this.w).setFavoriteCount(((Radio) RevampedDetailListing.this.o.getBusinessObject()).getFavorite_count());
                                if (RevampedDetailListing.this.P) {
                                    isSponsored = ((Radio) RevampedDetailListing.this.o.getBusinessObject()).getIsSponsored();
                                    if (TextUtils.isEmpty(isSponsored) || !isSponsored.equals("1")) {
                                        GaanaApplication.getInstance().setIsCurrentALPLSponsored(false);
                                    } else {
                                        GaanaApplication.getInstance().setIsCurrentALPLSponsored(true);
                                    }
                                }
                                ((Radio) RevampedDetailListing.this.w).setIs_sponsored(((Radio) RevampedDetailListing.this.o.getBusinessObject()).getIsSponsored());
                                ((Radio) RevampedDetailListing.this.w).setGaana_ad(((Radio) RevampedDetailListing.this.o.getBusinessObject()).getGaana_ad());
                            } else if (RevampedDetailListing.this.w instanceof Artist) {
                                ((Artist) RevampedDetailListing.this.w).setFavoriteCount(((Artist) RevampedDetailListing.this.o.getBusinessObject()).getFavoriteCount());
                                if (TextUtils.isEmpty(RevampedDetailListing.this.w.getAtw())) {
                                    RevampedDetailListing.this.w.setAtw(RevampedDetailListing.this.o.getArtist().getArtwork());
                                }
                                if (RevampedDetailListing.this.P) {
                                    isSponsored = ((Artist) RevampedDetailListing.this.o.getBusinessObject()).getIsSponsored();
                                    if (TextUtils.isEmpty(isSponsored) || !isSponsored.equals("1")) {
                                        GaanaApplication.getInstance().setIsCurrentALPLSponsored(false);
                                    } else {
                                        GaanaApplication.getInstance().setIsCurrentALPLSponsored(true);
                                    }
                                }
                                ((Artist) RevampedDetailListing.this.w).setIs_sponsored(((Artist) RevampedDetailListing.this.o.getBusinessObject()).getIsSponsored());
                            }
                            isSponsored = null;
                            RevampedDetailListing.this.w.setBusinessObjId(RevampedDetailListing.this.o.getBusinessObjId());
                            RevampedDetailListing.this.a.setParams(RevampedDetailListing.this, RevampedDetailListing.this.w);
                        } else {
                            isSponsored = null;
                        }
                        RevampedDetailListing.this.z.setData(RevampedDetailListing.this.o, z);
                        RevampedDetailListing.this.x.setData(RevampedDetailListing.this.z.getOtherSections());
                        RevampedDetailListing.this.p.setAdapter(RevampedDetailListing.this.x);
                        RevampedDetailListing.this.M.updateData(z);
                        RevampedDetailListing.this.M.getPopulatedView();
                        RevampedDetailListing.this.i.notifyDataSetChanged();
                        RevampedDetailListing.this.i.initFavLayout(RevampedDetailListing.this.w);
                        RevampedDetailListing.this.q();
                        if (!(RevampedDetailListing.this.w.isLocalMedia() || DownloadManager.c().h(Integer.parseInt(RevampedDetailListing.this.w.getBusinessObjId())) == null)) {
                            if (RevampedDetailListing.this.w instanceof Playlist) {
                                DownloadManager.c().d(RevampedDetailListing.this.w);
                            }
                            if (RevampedDetailListing.this.w instanceof Album) {
                                obj2 = ((Album) RevampedDetailListing.this.w).getFavoriteCount();
                            } else if (RevampedDetailListing.this.w instanceof Playlist) {
                                obj2 = ((Playlist) RevampedDetailListing.this.w).getFavoriteCount();
                            }
                            if (!(isSponsored == null || obj2 == null || isSponsored.equals(obj2))) {
                                DownloadManager.c().a(RevampedDetailListing.this.w.getBusinessObjId(), RevampedDetailListing.this.w);
                            }
                        }
                        if (RevampedDetailListing.this.w instanceof Artist) {
                            RevampedDetailListing.this.H = (int) RevampedDetailListing.this.mContext.getResources().getDimension(R.dimen.dp50);
                            RevampedSectionData artistSectionData = RevampedDetailListing.this.z.getArtistSectionData();
                            if (!(artistSectionData == null || artistSectionData.getDetail_artist_sections() == null || artistSectionData.getDetail_artist_sections().size() <= 0)) {
                                RevampedDetailListing.this.r.setVisibility(0);
                                RevampedDetailListing.this.x.addArtistSectionPageronDetailPage(RevampedDetailListing.this.r, artistSectionData);
                            }
                        }
                        if ((!(RevampedDetailListing.this.w instanceof Artist) || RevampedDetailListing.this.r.getChildCount() >= 1) && RevampedDetailListing.this.x.getItemCount() < 1) {
                            RevampedDetailListing.this.w.setCount("0");
                            RevampedDetailListing.this.b.setVisibility(0);
                            RevampedDetailListing.this.b.setText(R.string.error_generic_unableto_process);
                        }
                        RevampedDetailListing.this.z();
                        if ((RevampedDetailListing.this.w instanceof Album) && !RevampedDetailListing.this.o.getAlbum().isBottomBannerOff()) {
                            RevampedDetailListing.this.E();
                        } else if ((RevampedDetailListing.this.w instanceof Playlist) && !RevampedDetailListing.this.o.getPlaylist().isBottomBannerOff()) {
                            RevampedDetailListing.this.E();
                        } else if ((RevampedDetailListing.this.w instanceof Radio) && !RevampedDetailListing.this.o.getRadio().isBottomBannerOff()) {
                            RevampedDetailListing.this.E();
                        } else if ((RevampedDetailListing.this.w instanceof Artist) && !RevampedDetailListing.this.o.getArtist().isBottomBannerOff()) {
                            RevampedDetailListing.this.E();
                        }
                    }
                }
            }
        });
    }

    public ViewGroup g() {
        if (this.n == null) {
            return null;
        }
        return (ViewGroup) this.n.getParent();
    }

    private void z() {
        if (this.m == null && !TextUtils.isEmpty(this.N)) {
            String[] split;
            String str;
            ArrayList arrListBusinessObj;
            if (this.N.contains("play")) {
                split = this.N.split("/");
                if (split.length > 1) {
                    BusinessObject businessObject;
                    str = split[1];
                    arrListBusinessObj = this.w.getArrListBusinessObj();
                    if (arrListBusinessObj != null && arrListBusinessObj.size() > 0) {
                        Iterator it = arrListBusinessObj.iterator();
                        while (it.hasNext()) {
                            BusinessObject businessObject2 = (BusinessObject) it.next();
                            if (str.equals(businessObject2.getBusinessObjId())) {
                                businessObject = (Track) businessObject2;
                                break;
                            }
                        }
                    }
                    businessObject = null;
                    if (businessObject != null) {
                        PlayerManager.a(this.mContext).a(com.logging.d.a().a((BaseGaanaFragment) this, arrListBusinessObj), com.logging.d.a().a((BaseGaanaFragment) this, businessObject));
                        PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
                        ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
                    }
                } else {
                    af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.playMenu, s());
                }
            } else if (this.N.contains("download") && this.N.contains(ActionMapperConstants.KEY_TRACK)) {
                split = this.N.split("/");
                if (split.length > 2) {
                    Track track;
                    str = split[1];
                    arrListBusinessObj = this.w.getArrListBusinessObj();
                    if (arrListBusinessObj != null && arrListBusinessObj.size() > 0) {
                        Iterator it2 = arrListBusinessObj.iterator();
                        while (it2.hasNext()) {
                            BusinessObject businessObject3 = (BusinessObject) it2.next();
                            if (str.equals(businessObject3.getBusinessObjId())) {
                                track = (Track) businessObject3;
                                break;
                            }
                        }
                    }
                    track = null;
                    if (this.I != null) {
                        this.I.findViewById(R.id.ll_header_container).setVisibility(4);
                    }
                    if (!(track == null || DownloadManager.c().e(Integer.parseInt(str)) == DownloadStatus.DOWNLOADED)) {
                        ((DownloadSongsItemView) this.S).downloadTrack(track.getBusinessObjId(), track);
                    }
                }
            } else if (this.N.contains("download")) {
                a(false, null);
            }
            this.N = null;
        }
    }

    public RevampedDetailObjectManager h() {
        return this.z;
    }

    private ViewGroup a(ViewGroup viewGroup) {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.revamped_detail_album_action_button, viewGroup, false);
        this.f = (ImageView) frameLayout.findViewById(R.id.btn_action_left);
        this.e = (Button) frameLayout.findViewById(R.id.btn_action_main);
        this.g = (ImageView) frameLayout.findViewById(R.id.btn_action_download);
        this.h = (ImageView) frameLayout.findViewById(R.id.btn_action_share);
        this.f.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String str = "Favorite ";
                if (RevampedDetailListing.this.w.isFavorite().booleanValue()) {
                    str = "UnFavorite ";
                }
                RevampedDetailListing.this.a(str, true);
                af.a(RevampedDetailListing.this.mContext, RevampedDetailListing.this).a((int) R.id.favoriteMenu, RevampedDetailListing.this.w);
                RevampedDetailListing.this.q();
            }
        });
        this.g.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String str = "Download ";
                if ((RevampedDetailListing.this.w.getBusinessObjType() == BusinessObjectType.Playlists || RevampedDetailListing.this.w.getBusinessObjType() == BusinessObjectType.Albums) && DownloadManager.c().h(Integer.parseInt(RevampedDetailListing.this.w.getBusinessObjId())) != null) {
                    str = "Delete Download ";
                }
                RevampedDetailListing.this.a(str, true);
                an.a().a("click", "ac", RevampedDetailListing.this.w.getBusinessObjId(), "", "", "downloadall", "", "");
                af.a(RevampedDetailListing.this.mContext, RevampedDetailListing.this).a((int) R.id.downloadMenu, RevampedDetailListing.this.w);
            }
        });
        this.h.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                af.a(RevampedDetailListing.this.mContext, RevampedDetailListing.this).a((int) R.id.shareMenu, RevampedDetailListing.this.w);
            }
        });
        this.e.setOnClickListener(new OnClickListener() {
            public void onClick(final View view) {
                String str = "Play ";
                StringBuilder stringBuilder;
                if ((RevampedDetailListing.this.w instanceof Album) || (RevampedDetailListing.this.w instanceof Playlist)) {
                    if (!Constants.ab || RevampedDetailListing.this.o == null) {
                        an.a().a("click", "ac", RevampedDetailListing.this.w.getBusinessObjId(), "", "", "playall", "", "");
                        af.a(RevampedDetailListing.this.mContext, RevampedDetailListing.this).a((int) R.id.playMenu, RevampedDetailListing.this.w);
                    } else {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(RevampedDetailListing.this.o.getBusinessObjectString());
                        stringBuilder.append(RevampedDetailListing.this.o.getBusinessObjId());
                        u.a().a("Shuffle Product", "Shuffle Play", stringBuilder.toString());
                        af.a(RevampedDetailListing.this.mContext, RevampedDetailListing.this).a((int) R.id.shuffleMenu, RevampedDetailListing.this.w);
                    }
                } else if ((RevampedDetailListing.this.w instanceof Radio) || (RevampedDetailListing.this.w instanceof Artist)) {
                    if ((RevampedDetailListing.this.w instanceof Radio) && Constants.cY) {
                        JukeSessionManager.getErrorDialog(RevampedDetailListing.this.mContext, 0, new OnButtonClickListener() {
                            public void onNegativeButtonClick() {
                            }

                            public void onPositiveButtonClick() {
                                JukeSessionManager.getInstance().stopJukeSession(new s() {
                                    public void onErrorResponse(BusinessObject businessObject) {
                                    }

                                    public void onRetreivalComplete(BusinessObject businessObject) {
                                        if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                                            AnonymousClass14.this.onClick(view);
                                        }
                                    }
                                });
                            }
                        });
                        return;
                    } else if (PlayerManager.a(RevampedDetailListing.this.mContext).i() == null || TextUtils.isEmpty(RevampedDetailListing.this.w.getBusinessObjId()) || !RevampedDetailListing.this.w.getBusinessObjId().equalsIgnoreCase(PlayerManager.a(RevampedDetailListing.this.mContext).i().c()) || PlayerManager.a(RevampedDetailListing.this.mContext).m() != PlayerType.GAANA_RADIO) {
                        if (RevampedDetailListing.this.w instanceof Artist) {
                            aj a = aj.a();
                            Context context = RevampedDetailListing.this.mContext;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(RevampedDetailListing.this.getString(R.string.radio_for_artist));
                            stringBuilder.append(RevampedDetailListing.this.w.getName());
                            a.a(context, stringBuilder.toString());
                            ad.a(RevampedDetailListing.this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=artistradios&artist_id=<artist_id>&page=1&limit=10".replace("<artist_id>", RevampedDetailListing.this.w.getBusinessObjId()), SOURCE_TYPE.RADIO_SEARCH_ARTIST.ordinal(), RevampedDetailListing.this.w);
                        } else if (((Radio) RevampedDetailListing.this.w).getType().equals(c.d.c)) {
                            ad.a(RevampedDetailListing.this.mContext).a(RevampedDetailListing.this.w);
                        } else {
                            ad.a(RevampedDetailListing.this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", RevampedDetailListing.this.w.getBusinessObjId()).replace("<radio_type>", ((Radio) RevampedDetailListing.this.w).getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), RevampedDetailListing.this.w);
                        }
                        RevampedDetailListing.this.e.setText(R.string.pause_radio);
                    } else {
                        String str2;
                        if (GaanaMusicService.t()) {
                            o.a(RevampedDetailListing.this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
                            RevampedDetailListing.this.e.setText(R.string.opt_start_radio);
                            str2 = "Pause ";
                        } else {
                            o.a(RevampedDetailListing.this.mContext);
                            RevampedDetailListing.this.e.setText(R.string.pause_radio);
                            str2 = "Play ";
                        }
                        str = str2;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(str.toUpperCase());
                        stringBuilder2.append(" RADIO");
                        an.a().a("click", "ac", RevampedDetailListing.this.w.getBusinessObjId(), GaanaApplication.getInstance().getPageName(), "", stringBuilder2.toString(), "", "");
                    }
                }
                RevampedDetailListing.this.a(str, true);
            }
        });
        return frameLayout;
    }

    /* Access modifiers changed, original: protected */
    public View setContentView(int i, View view) {
        this.layoutInflater = LayoutInflater.from(getActivity());
        return this.layoutInflater.inflate(i, (LinearLayout) view.findViewById(R.id.llParentActivityLayout), false);
    }

    public BaseContextualActionBar i() {
        return this.a;
    }

    public void onStart() {
        super.onStart();
        v();
    }

    public void onStop() {
        super.onStop();
        w();
        k();
    }

    public void onResume() {
        super.onResume();
        if (this.loginStatus != this.mAppState.getCurrentUser().getLoginStatus()) {
            a(this.w.getBusinessObjId(), this.F);
            this.loginStatus = this.mAppState.getCurrentUser().getLoginStatus();
        }
        o.a("LISTENER_KEY_RADIO_DETAIL", this.j);
        if ((this.w instanceof Radio) || (this.w instanceof Artist)) {
            if (GaanaMusicService.t() && PlayerManager.a(this.mContext).m() == PlayerType.GAANA_RADIO && PlayerManager.a(this.mContext).i() != null && !TextUtils.isEmpty(this.w.getBusinessObjId()) && this.w.getBusinessObjId().equalsIgnoreCase(PlayerManager.a(this.mContext).i().c())) {
                this.e.setText(R.string.pause_radio);
            } else {
                this.e.setText(R.string.opt_start_radio);
            }
            this.h.setVisibility(0);
            this.g.setVisibility(8);
        }
        refreshListView();
    }

    public void onPause() {
        super.onPause();
        if (o.a("LISTENER_KEY_RADIO_DETAIL") != null) {
            o.b("LISTENER_KEY_RADIO_DETAIL");
        }
    }

    public void j() {
        this.v.findViewById(R.id.action_button_layout).setVisibility(8);
        this.v.findViewById(R.id.context_button_layout).setVisibility(0);
        a("Long Press", false);
        this.v.findViewById(R.id.btn_context_addtoplaylist).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RevampedDetailListing.this.a("Add to Playlist", false);
                if (RevampedDetailListing.this.B()) {
                    aj.a().a(RevampedDetailListing.this.mContext, RevampedDetailListing.this.mContext.getString(R.string.playlist_error_notracks_selected));
                    return;
                }
                if (RevampedDetailListing.this.w == null || !RevampedDetailListing.this.w.isLocalMedia()) {
                    ((BaseActivity) RevampedDetailListing.this.mContext).checkSetLoginStatus(new l.ad() {
                        public void onLoginSuccess() {
                            RevampedDetailListing.this.A();
                        }
                    }, GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_ADD_TO_PLAYLIST));
                } else {
                    RevampedDetailListing.this.A();
                }
            }
        });
        this.v.findViewById(R.id.btn_context_playnext).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RevampedDetailListing.this.a("Play Next", false);
                af.a(RevampedDetailListing.this.mContext, RevampedDetailListing.this).a((int) R.id.enqueueNextMenu, al.a().g());
                RevampedDetailListing.this.k();
            }
        });
    }

    private void A() {
        ArrayList arrListBusinessObj = al.a().g().getArrListBusinessObj();
        if (arrListBusinessObj.size() > 0) {
            ArrayList arrayList = new ArrayList();
            Iterator it = arrListBusinessObj.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof Track) {
                    arrayList.add((Track) next);
                } else if (next instanceof BusinessObject) {
                    Track a = a(next);
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
            }
            GaanaApplication.getInstance().setArrListTracksForPlaylist(arrayList);
        }
        ap.a().a(this.mContext, false);
        k();
    }

    private Track a(Object obj) {
        BusinessObject businessObject = (BusinessObject) obj;
        if (businessObject.isLocalMedia()) {
            return LocalMediaManager.getInstance(this.mContext).getTrackFromLocalMedia((OfflineTrack) obj);
        }
        return (Track) DownloadManager.c().a(businessObject.getBusinessObjId(), true);
    }

    private boolean B() {
        ArrayList arrListBusinessObj = al.a().g().getArrListBusinessObj();
        if (arrListBusinessObj != null && arrListBusinessObj.size() > 0) {
            return false;
        }
        return true;
    }

    public void k() {
        if (al.a && this.x != null) {
            this.x.destroyActionMode();
        }
        this.v.findViewById(R.id.action_button_layout).setVisibility(0);
        this.v.findViewById(R.id.context_button_layout).setVisibility(8);
    }

    public void l() {
        if (al.a && this.x != null) {
            this.x.updateSelectedCount();
        }
    }

    public void onDestroyView() {
        ((GaanaActivity) this.mContext).hideProgressDialog();
        if (this.n.getParent() != null) {
            ((ViewGroup) this.n.getParent()).removeView(this.n);
        }
        super.onDestroyView();
        d = null;
        this.k = true;
        this.P = false;
    }

    public void onRefresh() {
        if (!this.t) {
            C();
            this.s.setRefreshing(true);
            this.t = true;
            a(this.w.getBusinessObjId(), this.F);
            r();
        }
    }

    private void C() {
        this.O.setVisibility(0);
        this.O.bringToFront();
    }

    private void D() {
        this.O.setVisibility(8);
    }

    public void onScrollChanged(int i, boolean z, boolean z2) {
        this.q.setTranslationY(f.a((float) (-i), (float) ((f() + this.H) - (this.q.getHeight() != 0 ? this.q.getHeight() : (int) ((float) (this.G - (2 * f()))))), (float) f()));
        this.R = i;
    }

    public int m() {
        return this.R;
    }

    public HashMap<Integer, VideoPlayerAutoPlayView> n() {
        return d;
    }

    public void o() {
        try {
            Class cls = Class.forName(Constants.n());
            if (this.w instanceof Radio) {
                cls = Class.forName(DiscoverItemView.class.getName());
            }
            this.S = (BaseItemView) cls.getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.mContext, this});
        } catch (Exception unused) {
        }
    }

    public BaseItemView p() {
        return this.S;
    }

    public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
        q();
    }

    public void q() {
        TypedArray obtainStyledAttributes;
        if (com.managers.n.a().a(this.w)) {
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            this.f.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(51, -1)));
            obtainStyledAttributes.recycle();
            return;
        }
        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.f.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(49, -1)));
        obtainStyledAttributes.recycle();
    }

    public void a(boolean z, TextView textView, ImageView imageView) {
        this.T = z;
        this.U = textView;
        this.g = imageView;
    }

    public void a(final boolean z, final BusinessObject businessObject) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else if (!Util.j(this.mContext)) {
            ap.a().f(this.mContext);
        } else if (this.o != null) {
            if (ap.a().a(this.o.getBusinessObject(), null)) {
                b(z, businessObject);
            } else {
                Util.b(this.mContext, "pl", null, new as() {
                    public void onTrialSuccess() {
                        RevampedDetailListing.this.b(z, businessObject);
                        RevampedDetailListing.this.refreshDataandAds();
                        RevampedDetailListing.this.showSnackbartoOpenMyMusic();
                        ((GaanaActivity) RevampedDetailListing.this.mContext).updateSideBar();
                    }
                });
            }
        }
    }

    private void b(boolean z, BusinessObject businessObject) {
        Util.i(this.mContext, "Download");
        final BusinessObject businessObject2 = businessObject == null ? this.w : businessObject;
        final BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
        boolean b = com.services.d.a().b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
        DownloadStatus h = DownloadManager.c().h(Integer.parseInt(businessObject2.getBusinessObjId()));
        if (h == null || h == DownloadStatus.TRIED_BUT_FAILED || h == DownloadStatus.PAUSED || h == DownloadStatus.PARTIALLY_DOWNLOADED) {
            if (Util.k(GaanaApplication.getContext()) == 0) {
                if (!com.services.d.a().b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                    ((BaseActivity) this.mContext).mDialog = new com.services.f(this.mContext);
                    ((BaseActivity) this.mContext).mDialog.a(this.mContext.getString(R.string.gaana_plus_feature), this.mContext.getString(R.string.sync_over_data_connection_disabled), Boolean.valueOf(true), this.mContext.getString(R.string.settings_text), this.mContext.getString(R.string.dlg_msg_cancel), new b() {
                        public void onCancelListner() {
                        }

                        public void onOkListner(String str) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            ((BaseActivity) RevampedDetailListing.this.mContext).sendGAEvent("GaanaPlus", "BuySubscription", "Others");
                            ((GaanaActivity) RevampedDetailListing.this.mContext).displayFragment(settingsDetailFragment);
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
                                PopupWindowView.getInstance(RevampedDetailListing.this.mContext, currentFragment).dismiss(true);
                                return;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                            settingsDetailFragment.setArguments(bundle);
                            PopupWindowView.getInstance(RevampedDetailListing.this.mContext, currentFragment).dismiss(true);
                            ((GaanaActivity) RevampedDetailListing.this.mContext).displayFragment(settingsDetailFragment);
                        }
                    });
                }
            }
            if (h == null) {
                DownloadManager.c().a(businessObject2, this.mContext);
            } else {
                DownloadManager.c().c(businessObject2);
            }
            a(Boolean.valueOf(false), businessObject2);
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            Drawable drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(8, -1));
            obtainStyledAttributes.recycle();
            this.g.setImageDrawable(drawable);
            if (this.T) {
                this.U.setText(this.mContext.getString(this.w instanceof Album ? R.string.downloading_album : R.string.downloading_playlist));
            }
        } else if (z) {
            if (h == DownloadStatus.QUEUED || h == DownloadStatus.DOWNLOADING) {
                new com.services.f(this.mContext).a(this.mContext.getString(R.string.gaana_text), this.mContext.getString(R.string.do_you_want_pause_this_album_download), Boolean.valueOf(true), this.mContext.getString(R.string.dialog_yes), this.mContext.getString(R.string.dialog_no), new b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        DownloadManager.c().r(Integer.parseInt(RevampedDetailListing.this.o.getBusinessObjId()));
                        TypedArray obtainStyledAttributes = RevampedDetailListing.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        Drawable drawable = ContextCompat.getDrawable(RevampedDetailListing.this.getContext(), obtainStyledAttributes.getResourceId(10, -1));
                        obtainStyledAttributes.recycle();
                        RevampedDetailListing.this.g.setImageDrawable(drawable);
                        if (RevampedDetailListing.this.T) {
                            Context context;
                            int i;
                            TextView s = RevampedDetailListing.this.U;
                            if (RevampedDetailListing.this.w instanceof Album) {
                                context = RevampedDetailListing.this.mContext;
                                i = R.string.download_album;
                            } else {
                                context = RevampedDetailListing.this.mContext;
                                i = R.string.download_playlist;
                            }
                            s.setText(context.getString(i));
                        }
                    }
                }, false);
            } else if (h == DownloadStatus.DOWNLOADED) {
                new com.services.f(this.mContext).a(this.mContext.getString(R.string.gaana_text), this.mContext.getString(R.string.do_you_want_to_remove_this_album_from_download), Boolean.valueOf(true), this.mContext.getString(R.string.dialog_yes), this.mContext.getString(R.string.dialog_no), new b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        DownloadManager.c().p(Integer.parseInt(RevampedDetailListing.this.o.getBusinessObjId()));
                        DownloadManager.c().d(Integer.parseInt(RevampedDetailListing.this.o.getBusinessObjId()));
                        RevampedDetailListing.this.a(Boolean.valueOf(false), businessObject2);
                    }
                }, false);
            }
        }
    }

    private void a(Boolean bool, BusinessObject businessObject) {
        if (this.w != null) {
            ((BaseActivity) this.mContext).refreshListView();
            a(null, businessObject);
        }
    }

    public void r() {
        System.currentTimeMillis();
        if (this.w != null) {
            a(this.g, this.w);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(View view, BusinessObject businessObject) {
        DownloadStatus h = ((businessObject instanceof Playlist) || (businessObject instanceof Album)) ? DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId())) : businessObject instanceof Track ? DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId())) : null;
        a(h);
    }

    /* Access modifiers changed, original: protected */
    public void a(DownloadStatus downloadStatus) {
        TypedArray obtainStyledAttributes;
        Drawable drawable;
        if (this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext) || ((this.mAppState.getCurrentUser() != null && !this.mAppState.getCurrentUser().getLoginStatus()) || downloadStatus == null || !ap.a().a(this.w, null))) {
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(44, -1));
            obtainStyledAttributes.recycle();
            this.g.setImageDrawable(drawable);
            if (this.T) {
                Context context;
                int i;
                TextView textView = this.U;
                if (this.w instanceof Album) {
                    context = this.mContext;
                    i = R.string.download_album;
                } else {
                    context = this.mContext;
                    i = R.string.download_playlist;
                }
                textView.setText(context.getString(i));
            }
        } else if (downloadStatus == DownloadStatus.DOWNLOADING) {
            if (DownloadManager.c().w()) {
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(8, -1));
                obtainStyledAttributes.recycle();
                this.g.setImageDrawable(drawable);
            } else {
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(9, -1));
                obtainStyledAttributes.recycle();
                this.g.setImageDrawable(drawable);
            }
            if (this.T) {
                this.U.setText(this.mContext.getString(this.w instanceof Album ? R.string.downloading_album : R.string.downloading_playlist));
            }
        } else if (downloadStatus == DownloadStatus.DOWNLOADED) {
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(7, -1));
            obtainStyledAttributes.recycle();
            this.g.setImageDrawable(drawable);
            if (this.T) {
                this.U.setText(this.mContext.getString(R.string.downloaded));
            }
        } else if (downloadStatus == DownloadStatus.PAUSED || downloadStatus == DownloadStatus.PARTIALLY_DOWNLOADED) {
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(10, -1));
            obtainStyledAttributes.recycle();
            this.g.setImageDrawable(drawable);
            if (this.T) {
                this.U.setText(this.mContext.getString(R.string.resume_download));
            }
        } else if (downloadStatus == DownloadStatus.QUEUED) {
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(9, -1));
            obtainStyledAttributes.recycle();
            this.g.setImageDrawable(drawable);
            if (this.T) {
                this.U.setText(this.mContext.getString(R.string.queued));
            }
        } else if (downloadStatus == DownloadStatus.TRIED_BUT_FAILED) {
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(91, -1));
            obtainStyledAttributes.recycle();
            this.g.setImageDrawable(drawable);
            if (this.T) {
                this.U.setText(this.mContext.getString(R.string.retry_download));
            }
        }
    }

    public void onAdBottomBannerLoaded() {
        if (this.Q != null && !TextUtils.isEmpty(this.removeAdDeeplink)) {
            this.Q.setVisibility(0);
            this.Q.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    u.a().a("Gaana Plus", "remove_adhook", "DetailPage");
                    com.services.c.a(RevampedDetailListing.this.mContext).a(RevampedDetailListing.this.mContext, RevampedDetailListing.this.removeAdDeeplink, RevampedDetailListing.this.mAppState);
                }
            });
        }
    }

    public void onAdBottomBannerGone() {
        if (this.Q != null && !TextUtils.isEmpty(this.removeAdDeeplink)) {
            this.Q.setVisibility(8);
            this.Q.setOnClickListener(null);
        }
    }

    private void E() {
        String str = "";
        if (this.w instanceof Playlist) {
            str = Constants.dH;
        } else if (this.w instanceof Album) {
            str = Constants.dI;
        } else if (this.w instanceof Artist) {
            str = Constants.dK;
        } else if ((this.w instanceof Radio) && "RL".equalsIgnoreCase(this.o.getRadio().getType())) {
            str = Constants.dL;
        } else if ((this.w instanceof Radio) && "RM".equalsIgnoreCase(this.o.getRadio().getType())) {
            str = Constants.dM;
        }
        String str2 = str;
        LinearLayout linearLayout = (LinearLayout) this.n.findViewById(R.id.adSlot);
        str = e.A;
        if ((this.w instanceof Radio) && "RM".equalsIgnoreCase(((Radio) this.w).getType())) {
            str = e.F;
        }
        if ((this.w instanceof Radio) && ((Radio) this.w).getGaana_ad() == 1) {
            str = e.I;
        }
        String str3 = str;
        if (e.X == 0) {
            GaanaApplication.getInstance().setDFPAdSectionName("album_details_bottom");
            this.V = ColombiaAdViewManager.a().a(this.mContext, linearLayout, str3, str2, (l.a) this, new AdsUJData[0]);
            if (this.V != null) {
                getLifecycle().a(this.V);
            }
        }
    }

    public BusinessObject s() {
        return this.w;
    }

    public void t() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.favorite_tap_animation);
        loadAnimation.setInterpolator(new com.a.a(0.1d, 2.0d));
        this.e.startAnimation(loadAnimation);
    }

    public void a(ArrayList<BusinessObject> arrayList) {
        u();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it.next();
            if (businessObject instanceof Tags) {
                Tags tags = (Tags) businessObject;
                if (tags.isSelected()) {
                    arrayList3.add(tags);
                }
            } else if (businessObject instanceof TopArtists) {
                TopArtists topArtists = (TopArtists) businessObject;
                if (topArtists.isSelected()) {
                    arrayList4.add(topArtists);
                }
            } else if (businessObject instanceof TopLanguage) {
                TopLanguage topLanguage = (TopLanguage) businessObject;
                if (topLanguage.isSelected()) {
                    arrayList5.add(topLanguage);
                }
            }
        }
        ArrayList arrayList6 = new ArrayList();
        if (!(arrayList3.size() == 0 && arrayList4.size() == 0 && arrayList5.size() == 0)) {
            Iterator it2 = this.y.iterator();
            while (it2.hasNext()) {
                Iterator it3;
                BusinessObject businessObject2 = (BusinessObject) it2.next();
                Track track = (Track) businessObject2;
                ArrayList tags2 = track.getTags();
                ArrayList artists = track.getArtists();
                String language = track.getLanguage();
                if (tags2 != null) {
                    Iterator it4;
                    int i = 0;
                    if (arrayList3.size() != 0) {
                        it4 = arrayList3.iterator();
                        while (it4.hasNext()) {
                            Tags tags3 = (Tags) it4.next();
                            Iterator it5 = tags2.iterator();
                            while (it5.hasNext()) {
                                it3 = it2;
                                if (((Tags) it5.next()).getTag_id().equals(tags3.getTag_id())) {
                                    i++;
                                    break;
                                }
                                it2 = it3;
                            }
                            it3 = it2;
                            it2 = it3;
                        }
                    }
                    it3 = it2;
                    if (arrayList4.size() != 0) {
                        it2 = arrayList4.iterator();
                        while (it2.hasNext()) {
                            TopArtists topArtists2 = (TopArtists) it2.next();
                            it4 = artists.iterator();
                            while (it4.hasNext()) {
                                if (((Track.Artist) it4.next()).getArtist_id().equals(topArtists2.getTop_artistId())) {
                                    i++;
                                    break;
                                }
                            }
                        }
                    }
                    if (arrayList5.size() != 0) {
                        it2 = arrayList5.iterator();
                        while (it2.hasNext()) {
                            if (language.equals(((TopLanguage) it2.next()).getLang_name())) {
                                i++;
                                break;
                            }
                        }
                    }
                    if (i == (arrayList3.size() + arrayList4.size()) + arrayList5.size()) {
                        arrayList6.add(businessObject2);
                    }
                } else {
                    it3 = it2;
                }
                it2 = it3;
            }
        }
        arrayList2.clear();
        if (arrayList6.size() != 0) {
            arrayList2.addAll(arrayList6);
        } else if (arrayList3.size() == 0 && arrayList4.size() == 0 && arrayList5.size() == 0) {
            arrayList2.addAll(this.y);
        }
        this.w.setArrListBusinessObj(arrayList2);
        this.mAppState.setCurrentBusObjInListView(arrayList2);
        int size = arrayList2.size();
        if (this.x.getCustomListAdapter() != null) {
            this.x.getCustomListAdapter().updateAdapterCount(size);
        }
        if (al.a().d()) {
            al.a().c();
            this.a.updateSelectedCountinContextMode(size);
        }
    }

    public void u() {
        this.p.scrollToPosition(0);
        this.q.setTranslationY(0.0f);
    }

    private String b(String str, int i) {
        String str2 = "";
        StringBuilder stringBuilder;
        if (i == REVAMPED_DETAIL_TYPE.PLAYLIST.getNumVal()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("https://apiv2.gaana.com/playlist/entity/detail?playlist_id=");
            stringBuilder.append(str);
            return stringBuilder.toString();
        } else if (i == REVAMPED_DETAIL_TYPE.ALBUM.getNumVal()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("https://apiv2.gaana.com/album/entity/detail?album_id=");
            stringBuilder.append(str);
            return stringBuilder.toString();
        } else if (i == REVAMPED_DETAIL_TYPE.ARTIST.getNumVal()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("https://apiv2.gaana.com/artist/entity/detail?artist_id=");
            stringBuilder.append(str);
            return stringBuilder.toString();
        } else if (i != REVAMPED_DETAIL_TYPE.RADIO.getNumVal()) {
            return str2;
        } else {
            String type = ((Radio) this.w).getType();
            if (type.equals(c.d.d)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://apiv2.gaana.com/radio/live/entity/detail?radio_id=");
                stringBuilder.append(str);
                return stringBuilder.toString();
            } else if (!type.equals(c.d.c)) {
                return str2;
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://apiv2.gaana.com/radio/mirchi/entity/detail?radio_id=");
                stringBuilder.append(str);
                return stringBuilder.toString();
            }
        }
    }

    public void a(boolean z) {
        this.s.setEnabled(z);
    }

    public void v() {
        if (!TextUtils.isEmpty(this.A)) {
            if (!this.mClient.isConnected()) {
                this.mClient.connect();
            }
            List arrayList = new ArrayList();
            AppIndex.AppIndexApi.view(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.A), this.TITLE, Uri.parse(this.B), arrayList);
        }
    }

    public void w() {
        if (!TextUtils.isEmpty(this.A)) {
            AppIndex.AppIndexApi.viewEnd(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.A));
            this.mClient.disconnect();
        }
    }

    public void a(String str, boolean z) {
        if (z) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(this.E);
            str = stringBuilder.toString();
        }
        u.a().a(this.C, str, this.D);
    }

    public void a(int i, int i2) {
        if (this.i != null) {
            this.i.refreshArtistTabs(this.w, i, i2);
        }
    }

    public void b(boolean z) {
        if (PlayerManager.a(this.mContext).m() == PlayerType.GAANA_RADIO) {
            if (PlayerManager.a(this.mContext).i() != null && !TextUtils.isEmpty(this.w.getBusinessObjId()) && this.w.getBusinessObjId().equalsIgnoreCase(PlayerManager.a(this.mContext).i().c())) {
                if (z) {
                    this.e.setText(R.string.pause_radio);
                } else {
                    this.e.setText(R.string.opt_start_radio);
                }
            }
        } else if ((this.w instanceof Radio) || (this.w instanceof Artist)) {
            this.e.setText(R.string.opt_start_radio);
        }
    }
}
