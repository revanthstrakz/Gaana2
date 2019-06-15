package com.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbar.DetailsMaterialActionBar;
import com.android.volley.VolleyError;
import com.collapsible_header.FlexibleSpaceWithImageBaseFragment;
import com.collapsible_header.ListingFragmentMaterial;
import com.collapsible_header.SlidingTabLayout;
import com.collapsible_header.SlidingTabLayout.b;
import com.collapsible_header.f;
import com.collapsible_header.g;
import com.collapsible_header.i;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.ListAdapter.IAddListItemView;
import com.gaana.analytics.AppsFlyer;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.models.AdsUJData;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.ArtistItemView.ArtistItemHolder;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.BaseItemView.SponsorAdViewHolder;
import com.google.android.gms.appindexing.AppIndex;
import com.library.controls.CrossFadeImageView;
import com.library.managers.TaskManager.TaskListner;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.c;
import com.managers.ColombiaManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.aj;
import com.managers.ap;
import com.managers.e;
import com.managers.n;
import com.managers.q;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.services.h;
import com.services.l.r;
import com.services.l.s;
import com.til.colombia.android.service.Item;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArtistDetailsMaterialListing extends BaseGaanaFragment implements OnClickListener, b, IAddListItemView, c, com.managers.ap.a {
    private String A = null;
    private String B = null;
    private String C;
    private LinearLayout D;
    private View a = null;
    private ListingComponents b;
    private BusinessObject c;
    private CrossFadeImageView d;
    private BaseItemView e;
    private FloatingActionButton f;
    private DetailsMaterialActionBar g;
    private Toolbar h;
    private ProgressBar i;
    private int j;
    private int k;
    private SlidingTabLayout l;
    private a m;
    private ViewPager n;
    private View o;
    private DisplayMetrics p;
    private TextView q;
    private TextView r;
    private String s = "";
    private String t = "";
    private ArrayList<ListingFragmentMaterial> u;
    private MenuItem v;
    private SponsorAdViewHolder w;
    private Menu x;
    private LinearLayout y;
    private boolean z = false;

    class a extends com.collapsible_header.b {
        private FragmentManager b;
        private int c;

        public a(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.b = fragmentManager;
        }

        public int getCount() {
            return ArtistDetailsMaterialListing.this.b.c().size();
        }

        public CharSequence getPageTitle(int i) {
            return ((ListingButton) ArtistDetailsMaterialListing.this.b.c().get(i)).d();
        }

        public void a(int i) {
            this.c = i;
        }

        /* Access modifiers changed, original: protected */
        public Fragment createItem(int i) {
            ListingFragmentMaterial listingFragmentMaterial = new ListingFragmentMaterial();
            ListingParams listingParams = new ListingParams();
            listingParams.e(false);
            listingParams.b(i);
            listingParams.f(false);
            listingParams.h(false);
            ((ListingButton) ArtistDetailsMaterialListing.this.b.c().get(i)).c().a(Boolean.valueOf(ArtistDetailsMaterialListing.this.c.isLocalMedia() ^ 1));
            listingParams.a((ListingButton) ArtistDetailsMaterialListing.this.b.c().get(i));
            listingFragmentMaterial.a(listingParams);
            listingFragmentMaterial.setArguments(ListingFragmentMaterial.b(this.c));
            if (ArtistDetailsMaterialListing.this.u.size() > i) {
                ArtistDetailsMaterialListing.this.u.set(i, listingFragmentMaterial);
            } else {
                ArtistDetailsMaterialListing.this.u.add(listingFragmentMaterial);
            }
            return listingFragmentMaterial;
        }

        public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
            super.restoreState(parcelable, classLoader);
            if (parcelable != null) {
                Bundle bundle = (Bundle) ((Bundle) parcelable).getParcelable("superState");
                for (String str : bundle.keySet()) {
                    if (str.startsWith("f")) {
                        int parseInt = Integer.parseInt(str.substring(1));
                        Fragment fragment = this.b.getFragment(bundle, str);
                        if (fragment != null) {
                            while (ArtistDetailsMaterialListing.this.u.size() <= parseInt) {
                                ArtistDetailsMaterialListing.this.u.add(null);
                            }
                            fragment.setMenuVisibility(false);
                            ArtistDetailsMaterialListing.this.u.set(parseInt, (ListingFragmentMaterial) fragment);
                        }
                    }
                }
            }
        }
    }

    public int getItemViewType(int i) {
        return 0;
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

    private boolean a(Bundle bundle, ViewGroup viewGroup, boolean z) {
        if (bundle != null) {
            this.c = (BusinessObject) bundle.getSerializable("BUSINESS_OBJECT");
            String string = bundle.getString("DEEPLINKING_SCREEN_EXTRA_PARAM");
            if (!TextUtils.isEmpty(string)) {
                if (string.equals("play") && this.c != null && (this.c instanceof Artist)) {
                    af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.playMenu, this.c);
                } else if (string.equals("mini_purchase")) {
                    aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.gaana_mini_artist_purchase_success));
                }
            }
            if (this.c != null) {
                if (this.c instanceof Artist) {
                    this.b = Constants.a("", this.c.isLocalMedia());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(Util.c(this.c.getBusinessObjType()));
                    stringBuilder.append(this.c.getBusinessObjId());
                    AppsFlyer.getInstance().reportViewContent(this.c.getEnglishName(), "Artist", stringBuilder.toString());
                    Iterator it = this.b.c().iterator();
                    while (it.hasNext()) {
                        ListingButton listingButton = (ListingButton) it.next();
                        String k = listingButton.c().k();
                        if (k.contains("<artist_id>")) {
                            k = k.replace("<artist_id>", this.c.getBusinessObjId());
                        } else {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(k);
                            stringBuilder.append(this.c.getBusinessObjId());
                            k = stringBuilder.toString();
                        }
                        listingButton.c().a(k);
                        listingButton.e(this.c.isLocalMedia());
                        listingButton.c().d(this.c.isLocalMedia());
                    }
                }
                this.b.b(this.c.getName());
                this.b.a(this.c);
                this.mAppState.setListingComponents(this.b);
                a((ListingButton) this.b.c().get(0));
                a(viewGroup, bundle, z);
                return true;
            }
        }
        ((GaanaActivity) this.mContext).popBackStack();
        return false;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.c != null) {
            this.c.setArrListBusinessObj(null);
            bundle.putSerializable("BUSINESS_OBJECT", this.c);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.p = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.p);
        if (this.a == null) {
            boolean a;
            super.onCreateView(layoutInflater, viewGroup, bundle);
            if (bundle == null) {
                a = a(getArguments(), viewGroup, false);
            } else {
                a = a(bundle, viewGroup, true);
            }
            if (!a) {
                ((GaanaActivity) this.mContext).popBackStack();
            }
        } else {
            this.mAppState.setListingComponents(this.b);
        }
        if (getActivity().findViewById(R.id.dummy_shadow) != null) {
            getActivity().findViewById(R.id.dummy_shadow).setVisibility(8);
        }
        if (this.c != null) {
            this.mAppState.setGADParameter(this.c.getBusinessObjId());
            this.TITLE = this.c.getEnglishName();
            String str = "";
            StringBuilder stringBuilder;
            StringBuilder stringBuilder2;
            if (this.c instanceof Playlist) {
                Playlist playlist = (Playlist) this.c;
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/playlist/");
                stringBuilder.append(playlist.getSeokey());
                this.t = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/playlist/");
                stringBuilder.append(playlist.getSeokey());
                this.s = stringBuilder.toString();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("PlaylistDetailScreen:");
                stringBuilder2.append(this.TITLE);
                str = stringBuilder2.toString();
            } else if (this.c instanceof Album) {
                Album album = (Album) this.c;
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/album/");
                stringBuilder.append(album.getSeokey());
                this.t = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/album/");
                stringBuilder.append(album.getSeokey());
                this.s = stringBuilder.toString();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("AlbumDetailScreen:");
                stringBuilder2.append(this.TITLE);
                str = stringBuilder2.toString();
            } else if (this.c instanceof Artist) {
                Artist artist = (Artist) this.c;
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/artist/");
                stringBuilder.append(artist.getSeokey());
                this.t = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/artist/");
                stringBuilder.append(artist.getSeokey());
                this.s = stringBuilder.toString();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("ArtistDetailScreen:");
                stringBuilder2.append(this.TITLE);
                str = stringBuilder2.toString();
            }
            setGAScreenName(str, str);
        }
        return this.a;
    }

    public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
        b();
    }

    public void a() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.favorite_tap_animation);
        loadAnimation.setInterpolator(new com.a.a(0.2d, 20.0d));
        this.f.startAnimation(loadAnimation);
    }

    public void b() {
        if (this.h != null) {
            Menu menu = this.h.getMenu();
            if (menu != null) {
                MenuItem findItem = menu.findItem(R.id.menu_favourite);
                ImageView imageView = (ImageView) findItem.getActionView();
                TypedArray obtainStyledAttributes;
                if (findItem != null && this.c.isLocalMedia()) {
                    findItem.setVisible(false);
                } else if (n.a().a(this.c)) {
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(51, -1)));
                    obtainStyledAttributes.recycle();
                } else {
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(50, -1)));
                    obtainStyledAttributes.recycle();
                }
            }
        }
    }

    public BusinessObject c() {
        return this.c;
    }

    public void a(int i, int i2) {
        StringBuilder stringBuilder;
        if (i == 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(i2);
            stringBuilder.append("");
            this.A = stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(i2);
            stringBuilder.append("");
            this.B = stringBuilder.toString();
        }
        if (this.A != null && this.B != null) {
            this.i.setVisibility(8);
            if (this.c.isLocalMedia()) {
                a(this.A, this.B);
            }
        }
    }

    private void a(String str, String str2) {
        String stringBuilder;
        this.D.removeAllViews();
        long j = 0;
        long parseLong = !TextUtils.isEmpty(str) ? Long.parseLong(str.trim()) : 0;
        if (!TextUtils.isEmpty(str2)) {
            j = Long.parseLong(str2.trim());
        }
        StringBuilder stringBuilder2;
        if (parseLong < 2) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(Util.f(parseLong));
            stringBuilder2.append(" ");
            stringBuilder2.append(this.mContext.getString(R.string.song_text));
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(Util.f(parseLong));
            stringBuilder2.append(" ");
            stringBuilder2.append(this.mContext.getString(R.string.songs_text));
            stringBuilder = stringBuilder2.toString();
        }
        StringBuilder stringBuilder3;
        if (j < 2) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(Util.f(j));
            stringBuilder3.append(" ");
            stringBuilder3.append(this.mContext.getString(R.string.album_text));
            str = stringBuilder3.toString();
        } else {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(Util.f(j));
            stringBuilder3.append(" ");
            stringBuilder3.append(this.mContext.getString(R.string.albums_text));
            str = stringBuilder3.toString();
        }
        this.r.setVisibility(0);
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append(stringBuilder);
        stringBuilder4.append(" | ");
        stringBuilder4.append(str);
        this.C = stringBuilder4.toString();
        this.r.setText(this.C);
    }

    public void onStart() {
        super.onStart();
        f();
    }

    public void onResume() {
        super.onResume();
        ((GaanaActivity) this.mContext).setFragment(this);
        if (ap.a().b(this.mContext)) {
            ColombiaAdViewManager.a().a((c) this);
        }
        if (!Constants.ab || this.c == null || this.c.isLocalMedia()) {
            this.f.setImageResource(R.drawable.vector_player_play_white);
        } else {
            this.f.setImageResource(R.drawable.vector_shuffle_white);
        }
    }

    public void onPause() {
        if (ap.a().b(this.mContext)) {
            ColombiaAdViewManager.a().a(null);
        }
        super.onPause();
    }

    private void a(ViewGroup viewGroup, Bundle bundle, boolean z) {
        if (this.c instanceof Artist) {
            this.a = setContentView(R.layout.artist_pager_tabs, viewGroup);
        } else {
            this.a = setContentView(R.layout.details_material_listing, viewGroup);
        }
        this.i = (ProgressBar) this.a.findViewById(R.id.progressbar);
        this.i.setVisibility(0);
        this.u = new ArrayList();
        this.n = (ViewPager) this.a.findViewById(R.id.pager);
        this.m = new a(getChildFragmentManager());
        this.n.setAdapter(this.m);
        this.n.setOffscreenPageLimit(2);
        this.j = this.p.widthPixels;
        this.k = this.mContext.getResources().getDimensionPixelSize(R.dimen.tab_height);
        this.q = (TextView) this.a.findViewById(R.id.album_title);
        this.y = (LinearLayout) this.a.findViewById(R.id.ll_song_fav_count);
        this.D = (LinearLayout) this.y.findViewById(R.id.ll_fav_parent);
        this.r = (TextView) this.y.findViewById(R.id.tvAlbumSongCount_Value);
        this.o = this.a.findViewById(R.id.sliding_tabs_container);
        this.l = (SlidingTabLayout) this.a.findViewById(R.id.sliding_tabs);
        this.l.setScrolldListner(this);
        this.l.setCustomTabView(R.layout.tab_indicator, 16908308);
        if (Constants.l) {
            this.l.setSelectedIndicatorColors(-1);
            this.a.findViewById(R.id.overlay).setBackgroundColor(this.mContext.getResources().getColor(R.color.parallax_bar_color));
            this.o.findViewById(R.id.sliding_tabs).setBackgroundColor(this.mContext.getResources().getColor(R.color.parallax_bar_color));
            this.o.findViewById(R.id.sliding_tabs_view).setBackgroundColor(this.mContext.getResources().getColor(R.color.parallax_bar_color));
        } else {
            this.l.setSelectedIndicatorColors(this.mContext.getResources().getColor(R.color.f17gaana.red));
        }
        this.l.setDistributeEvenly(true);
        this.l.setViewPager(this.n);
        this.a.findViewById(R.id.overlay).setLayoutParams(new LayoutParams(-1, this.j));
        this.q.setTextColor(-1);
        this.r.setTextColor(-1);
        f.a(this.o, new Runnable() {
            public void run() {
                ArtistDetailsMaterialListing.this.a(0, false);
            }
        });
        this.y.post(new Runnable() {
            public void run() {
                if (ArtistDetailsMaterialListing.this.isAdded()) {
                    i.g(ArtistDetailsMaterialListing.this.y, (float) (ArtistDetailsMaterialListing.this.j - (((int) ArtistDetailsMaterialListing.this.mContext.getResources().getDimension(R.dimen.artist_tab_padding_fab)) + ArtistDetailsMaterialListing.this.y.getHeight())));
                    i.b(ArtistDetailsMaterialListing.this.y, 0.0f);
                    i.c(ArtistDetailsMaterialListing.this.y, 0.0f);
                }
            }
        });
        i.g(this.q, (float) ((this.j - this.q.getHeight()) - (((int) this.mContext.getResources().getDimension(R.dimen.artist_tab_padding_fab)) + this.y.getHeight())));
        this.q.post(new Runnable() {
            public void run() {
                if (ArtistDetailsMaterialListing.this.isAdded()) {
                    i.b(ArtistDetailsMaterialListing.this.q, 0.0f);
                    i.c(ArtistDetailsMaterialListing.this.q, 0.0f);
                }
            }
        });
        this.d = (CrossFadeImageView) this.a.findViewById(R.id.details_artwork);
        this.f = (FloatingActionButton) this.a.findViewById(R.id.fab);
        this.f.setOnClickListener(this);
        this.h = (Toolbar) this.a.findViewById(R.id.main_toolbar);
        this.h.setContentInsetsAbsolute(0, 0);
        i();
        this.g = new DetailsMaterialActionBar(this.mContext);
        this.h.addView(this.g);
        this.g.setParams(this, this.c);
        this.g.findViewById(R.id.title).setVisibility(8);
        ((ImageView) this.g.findViewById(R.id.menu_icon)).setImageResource(R.drawable.actionbar_back);
        this.g.setToolbar(this.h);
        this.i = (ProgressBar) this.a.findViewById(R.id.progressbar);
        this.q.setText(Constants.a(this.c.getName(), this.c.getLanguage()));
        h();
        if (this.c instanceof Artist) {
            String artwork = ((Artist) this.c).getArtwork();
            if (this.c.isLocalMedia()) {
                this.d.bindImageForLocalMedia(artwork, null, new LocalMediaImageLoader(), false);
            } else if (artwork == null) {
                k();
            } else {
                a(artwork);
                j();
            }
        }
    }

    private void i() {
        this.h.getMenu().clear();
        this.h.inflateMenu(R.menu.cast_menu_detail);
        ((BaseActivity) this.mContext).initializeMediaRouterButton(this.h.getMenu(), R.id.media_route_menu_item);
        this.x = this.h.getMenu();
        if (this.x != null) {
            this.v = this.x.findItem(R.id.media_route_menu_item);
            this.x.findItem(R.id.menu_favourite).setVisible(true);
        }
        b();
    }

    public void refreshListView(BusinessObjectType businessObjectType) {
        refreshListView();
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        refreshListView();
    }

    public void a(int i, g gVar) {
        ListingFragmentMaterial listingFragmentMaterial = (ListingFragmentMaterial) this.m.getItemAt(this.n.getCurrentItem());
        if (listingFragmentMaterial != null) {
            View view = listingFragmentMaterial.getView();
            if (view != null) {
                g gVar2 = (g) view.findViewById(R.id.recycler_view);
                if (gVar2 != null && gVar2 == gVar) {
                    if (i > this.p.widthPixels) {
                        i = this.p.widthPixels;
                    } else {
                        View a = listingFragmentMaterial.a();
                        if (a != null) {
                            i = (int) (-f.a(a.getY(), (float) (d() - this.j), 0.0f));
                        }
                    }
                    if (i >= 0 && i <= this.p.widthPixels) {
                        a(i, false);
                        b(i);
                    }
                }
            }
        }
    }

    private void b(int i) {
        this.m.a(i);
        for (int i2 = 0; i2 < this.m.getCount(); i2++) {
            if (i2 != this.n.getCurrentItem()) {
                FlexibleSpaceWithImageBaseFragment flexibleSpaceWithImageBaseFragment = (FlexibleSpaceWithImageBaseFragment) this.m.getItemAt(i2);
                if (!(flexibleSpaceWithImageBaseFragment == null || flexibleSpaceWithImageBaseFragment.getView() == null)) {
                    flexibleSpaceWithImageBaseFragment.a(i, this.j);
                    flexibleSpaceWithImageBaseFragment.a(i);
                }
            }
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

    private void a(int i, boolean z) {
        float f;
        int i2 = i;
        int i3 = this.p.widthPixels;
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.tab_height);
        this.mContext.getResources().getDimensionPixelSize(R.dimen.tab_height);
        View findViewById = this.a.findViewById(R.id.details_artwork);
        View findViewById2 = this.a.findViewById(R.id.overlay);
        TextView textView = (TextView) this.a.findViewById(R.id.album_title);
        View findViewById3 = this.a.findViewById(R.id.ll_song_fav_count);
        float d = (float) (i3 - d());
        float f2 = (float) i2;
        float a = 0.7f + f.a((d - f2) / d, 0.0f, 0.5f);
        int i4 = -i2;
        float d2 = (float) ((d() + dimensionPixelSize) - findViewById2.getHeight());
        i.g(findViewById2, f.a((float) i4, d2, (float) d()));
        i.g(findViewById, f.a((float) (i4 / 2), d2, 0.0f));
        i.a(findViewById2, f.a((f2 / d) + 0.1f, 0.0f, 1.0f));
        a(textView);
        int height = (((this.j - this.q.getHeight()) - this.y.getHeight()) - (((int) this.mContext.getResources().getDimension(R.dimen.artist_tab_padding_fab)) + this.y.getHeight())) + i4;
        i.g(this.q, f.a((float) height, (((float) this.q.getHeight()) * a) / 8.0f, (float) this.j));
        i.g(this.y, f.a((float) (height - (this.y.getPaddingBottom() / 2)), 0.0f, (float) this.j));
        FloatingActionButton floatingActionButton = (FloatingActionButton) this.a.findViewById(R.id.fab);
        double a2 = (double) f.a((float) ((this.j + i4) - this.k), (float) d(), (float) (this.j - this.k));
        double d3 = (double) dimensionPixelSize;
        if (a2 < 1.5d * d3) {
            floatingActionButton.hide();
            this.y.setVisibility(4);
            i.d(this.q, f.a(a, 0.8f, 1.0f));
            i.e(this.q, f.a(a, 0.8f, 1.0f));
        } else {
            floatingActionButton.show();
            this.y.setVisibility(0);
            i.d(this.q, a);
            i.e(this.q, a);
        }
        if (a2 < d3 * 2.5d) {
            textView.setLayoutParams(new LinearLayout.LayoutParams((int) (((float) this.p.widthPixels) / 2.0f), -2));
            i.f(this.q, f.a((float) (i2 / 6), (float) (d() / 3), ((float) d()) / 1.4f));
        } else {
            textView.setLayoutParams(new LinearLayout.LayoutParams(this.p.widthPixels, -2));
            f = (float) (i2 / 6);
            d = (float) dimensionPixelSize;
            i.f(textView, f.a(f, 0.0f, d));
            i.f(findViewById3, f.a(f, 0.0f, d));
        }
        f = f.a((float) ((i4 + this.j) - this.k), (float) (d() - (this.o.getHeight() / 4)), (float) (this.j - this.k));
        if (!z) {
            i.g(this.o, f);
        }
    }

    public int e() {
        return (this.o.getHeight() - (d() / 3)) + this.mContext.getResources().getDimensionPixelSize(R.dimen.player_bottom_control_height);
    }

    private void j() {
        String businessObjId = this.c.getBusinessObjId();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(com.constants.c.x);
        stringBuilder.append(businessObjId);
        businessObjId = stringBuilder.toString();
        URLManager uRLManager = new URLManager();
        uRLManager.a(businessObjId);
        uRLManager.a(Artists.class);
        com.i.i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject != null) {
                    Artist artist = (Artist) businessObject.getArrListBusinessObj().get(0);
                    if (artist != null) {
                        ArtistDetailsMaterialListing.this.a(artist.getSongsCount(), artist.getAlbumsCount());
                    }
                }
            }
        }, uRLManager);
    }

    public void refreshListView() {
        super.refreshListView();
        if (this.c != null && this.u != null) {
            Iterator it = this.u.iterator();
            while (it.hasNext()) {
                ListingFragmentMaterial listingFragmentMaterial = (ListingFragmentMaterial) it.next();
                if (listingFragmentMaterial != null) {
                    listingFragmentMaterial.refreshListView();
                }
            }
        }
    }

    private void k() {
        String businessObjId = this.c.getBusinessObjId();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(com.constants.c.x);
        stringBuilder.append(businessObjId);
        businessObjId = stringBuilder.toString();
        URLManager uRLManager = new URLManager();
        uRLManager.a(businessObjId);
        uRLManager.a(Artists.class);
        com.i.i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                    Artist artist = (Artist) businessObject.getArrListBusinessObj().get(0);
                    if (artist != null) {
                        String songsCount = artist.getSongsCount();
                        String albumsCount = artist.getAlbumsCount();
                        ArtistDetailsMaterialListing.this.a(songsCount, albumsCount);
                        String artwork = artist.getArtwork();
                        ((Artist) ArtistDetailsMaterialListing.this.c).setArtwork(artwork);
                        ((Artist) ArtistDetailsMaterialListing.this.c).setSongsCount(songsCount);
                        ((Artist) ArtistDetailsMaterialListing.this.c).setAlbumsCount(albumsCount);
                        ArtistDetailsMaterialListing.this.a(artwork);
                    }
                }
            }
        }, uRLManager);
    }

    private void a(String str) {
        if (str != null) {
            try {
                if (str.contains("80x80")) {
                    str = str.replace("80x80", "480x480");
                    com.i.i.a().a(str, new r() {
                        public void onSuccessfulResponse(Bitmap bitmap) {
                            if (ArtistDetailsMaterialListing.this.isAdded() && bitmap != null) {
                                ArtistDetailsMaterialListing.this.d.setImageBitmap(bitmap);
                            } else if (ArtistDetailsMaterialListing.this.isAdded()) {
                                ArtistDetailsMaterialListing.this.m();
                            }
                        }

                        public void onErrorResponse(VolleyError volleyError) {
                            if (ArtistDetailsMaterialListing.this.isAdded()) {
                                ArtistDetailsMaterialListing.this.m();
                            }
                        }
                    });
                }
            } catch (OutOfMemoryError unused) {
                m();
                return;
            }
        }
        if (str != null && str.contains("175x175")) {
            str = str.replace("175x175", "480x480");
        }
        com.i.i.a().a(str, /* anonymous class already generated */);
    }

    private void m() {
        String artwork = ((Artist) this.c).getArtwork();
        if (artwork != null && artwork.contains("480x480")) {
            artwork = artwork.replace("480x480", "175x175");
        }
        if (artwork != null && artwork.contains("80x80")) {
            artwork = artwork.replace("80x80", "175x175");
        }
        com.i.i.a().a(artwork, new r() {
            public void onErrorResponse(VolleyError volleyError) {
            }

            public void onSuccessfulResponse(Bitmap bitmap) {
                ArtistDetailsMaterialListing.this.a(bitmap, ArtistDetailsMaterialListing.this.d);
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
                    if (ArtistDetailsMaterialListing.this.isAdded()) {
                        int dimensionPixelSize = ArtistDetailsMaterialListing.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp160);
                        int i = (ArtistDetailsMaterialListing.this.p.widthPixels - dimensionPixelSize) / 2;
                        Bitmap createBitmap = Bitmap.createBitmap(ArtistDetailsMaterialListing.this.p.widthPixels, ArtistDetailsMaterialListing.this.p.widthPixels, bitmap.getConfig());
                        Canvas canvas = new Canvas(createBitmap);
                        dimensionPixelSize += i;
                        Rect rect = new Rect(i, i, dimensionPixelSize, dimensionPixelSize);
                        Rect rect2 = new Rect(0, 0, ArtistDetailsMaterialListing.this.p.widthPixels, ArtistDetailsMaterialListing.this.p.widthPixels);
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

    /* Access modifiers changed, original: protected */
    public void a(ListingButton listingButton) {
        try {
            this.e = (BaseItemView) Class.forName(listingButton.f()).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.mContext, this});
        } catch (Exception unused) {
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void onStop() {
        super.onStop();
        g();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public View addListItemView(Object obj, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (obj instanceof Track) {
            return this.e.getPoplatedView(viewHolder, (BusinessObject) obj, viewGroup);
        }
        return viewHolder.itemView;
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        return new ArtistItemHolder(this.e.createViewHolder(viewGroup, i));
    }

    public void onClick(View view) {
        if (view == this.f) {
            BaseActivity baseActivity = (BaseActivity) this.mContext;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
            stringBuilder.append(" Detail");
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(((BaseActivity) this.mContext).currentScreen);
            stringBuilder2.append(" Detail - ");
            stringBuilder2.append(((BaseActivity) this.mContext).currentScreen);
            stringBuilder2.append(" - Shuffle Play");
            baseActivity.sendGAEvent(stringBuilder.toString(), "Play", stringBuilder2.toString());
            if (!Constants.ab || this.c == null || this.c.isLocalMedia()) {
                af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.playMenu, c());
            } else {
                af.a(this.mContext, (BaseGaanaFragment) this).a((int) R.id.playShuffleArtistMenu, c());
            }
        }
    }

    public void onDestroyView() {
        if (this.a.getParent() != null) {
            ((ViewGroup) this.a.getParent()).removeView(this.a);
        }
        super.onDestroyView();
        if (getActivity().findViewById(R.id.dummy_shadow) != null) {
            getActivity().findViewById(R.id.dummy_shadow).setVisibility(8);
        }
    }

    @TargetApi(17)
    private void a(TextView textView) {
        Configuration configuration = this.mContext.getResources().getConfiguration();
        if (17 > VERSION.SDK_INT || configuration.getLayoutDirection() != 1) {
            i.b(textView, 0.0f);
        } else {
            i.b(textView, (float) this.a.getWidth());
        }
    }

    public void f() {
        if (!TextUtils.isEmpty(this.s)) {
            if (!this.mClient.isConnected()) {
                this.mClient.connect();
            }
            List arrayList = new ArrayList();
            AppIndex.AppIndexApi.view(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.s), this.TITLE, Uri.parse(this.t), arrayList);
        }
    }

    public void g() {
        if (!TextUtils.isEmpty(this.s)) {
            AppIndex.AppIndexApi.viewEnd(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.s));
            this.mClient.disconnect();
        }
    }

    public void a(int i) {
        if (!(this.c == null || TextUtils.isEmpty(this.c.getName()))) {
            StringBuilder stringBuilder;
            if (i == 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("col:artist:");
                stringBuilder.append(this.c.getEnglishName());
                q.a().a("int", stringBuilder.toString());
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("col:artist:");
                stringBuilder.append(this.c.getEnglishName());
                stringBuilder.append(" playlist");
                q.a().a("int", stringBuilder.toString());
            }
        }
        h();
    }

    public void h() {
        ColombiaAdViewManager.a().a((c) this);
        if (this.w == null) {
            this.w = new SponsorAdViewHolder(this.a);
        }
        final View view = this.w.adView;
        view.setVisibility(8);
        if (ap.a().b(this.mContext)) {
            if (e.ad != 0 || !TextUtils.isEmpty(n())) {
                ColombiaAdViewManager.a().a(this.mContext, view, e.A, "AR_BOTTOM_BANNER", new AdsUJData[0]);
            } else if (e.X == 0) {
                ColombiaAdViewManager.a().a(this.mContext, view, e.A, "AR_BOTTOM_BANNER", new AdsUJData[0]);
            } else {
                ColombiaManager.b().a(1, this.mContext, 28, -1, view, "artist_details_material_fragment", new com.managers.ColombiaManager.a() {
                    public void onItemLoaded(Item item) {
                        view.setVisibility(0);
                    }

                    public void onItemRequestFailed(Exception exception) {
                        if (e.Z == 0) {
                            ColombiaAdViewManager.a().a(ArtistDetailsMaterialListing.this.mContext, view, e.A, "AR_BOTTOM_BANNER", new AdsUJData[0]);
                        } else {
                            view.setVisibility(8);
                        }
                    }
                }, "AR_BOTTOM_BANNER");
            }
            return;
        }
        view.setVisibility(8);
    }

    public void l() {
        h();
    }

    public void refreshDataandAds() {
        if (!(this.c == null || this.u == null)) {
            Iterator it = this.u.iterator();
            while (it.hasNext()) {
                ListingFragmentMaterial listingFragmentMaterial = (ListingFragmentMaterial) it.next();
                if (!(listingFragmentMaterial == null || listingFragmentMaterial.a == null)) {
                    listingFragmentMaterial.a.i();
                }
            }
        }
        h();
    }

    private String n() {
        String str = "";
        if (this.c == null || !(this.c instanceof Album)) {
            return (this.c == null || !(this.c instanceof Playlist)) ? str : ((Playlist) this.c).getChannelPageAdCode();
        } else {
            return ((Album) this.c).getChannelPageAdCode();
        }
    }
}
