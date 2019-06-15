package com.collapsible_header;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.a.a;
import com.actionbar.DetailsMaterialActionBar;
import com.android.volley.VolleyError;
import com.collapsible_header.c.b;
import com.constants.Constants;
import com.fragments.FavoritesFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.ListAdapterSectionIndexer.OnSearchCompleted;
import com.gaana.ads.interstitial.IAdType;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.localmedia.RecommendedPageView;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.gaana.models.UserRecentActivity;
import com.gaana.view.CustomListView.OnDataLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.i.i;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.library.controls.CrossFadeImageView;
import com.logging.d;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.ADSTATUS;
import com.managers.ColombiaAdViewManager.c;
import com.managers.ColombiaManager;
import com.managers.PlayerManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.managers.al;
import com.managers.an;
import com.managers.ap;
import com.managers.e;
import com.models.ListingParams;
import com.models.PlayerTrack;
import com.services.l.r;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Collections;

public class SongParallexListingFragment extends FlexibleSpaceWithImageBaseFragment<ObservableRecyclerView> implements b, OnSearchCompleted, ColombiaAdViewManager.b, c {
    private static float b = 1.2f;
    private ADSTATUS A;
    private String B = null;
    private String C = null;
    private String D = null;
    private boolean E;
    private BusinessObject F;
    private String G;
    private String H;
    private boolean I;
    private String J;
    private int K = 0;
    private int L = 0;
    private int M = -1;
    private int N = -1;
    private LinearLayout a;
    private c c = null;
    private View d = null;
    private ListingParams e;
    private OnDataLoadedListener f;
    private Fragment g;
    private int h;
    private ObservableRecyclerView i;
    private CrossFadeImageView j;
    private View k;
    private View l;
    private TextView m;
    private TextView n;
    private FloatingActionButton o;
    private View p;
    private View q;
    private Toolbar r;
    private DetailsMaterialActionBar s;
    private MenuItem t;
    private DisplayMetrics u;
    private ArrayList<String> v;
    private ArrayList<Bitmap> w;
    private int x;
    private int y;
    private PublisherAdView z;

    /* Access modifiers changed, original: protected */
    public void a(int i, View view) {
    }

    public ListingParams a() {
        return this.e;
    }

    private void a(Bundle bundle) {
        if (bundle != null) {
            if (this.e == null) {
                this.e = (ListingParams) bundle.getParcelable("bgf_saved_state");
            }
            if (bundle.getString("EXTRA_DYNAMIC_SECTION_UID") != null) {
                this.B = bundle.getString("EXTRA_DYNAMIC_SECTION_UID");
            }
            if (bundle.getString("EXTRA_SOURCE_NAME") != null) {
                this.D = bundle.getString("EXTRA_SOURCE_NAME");
            }
            if (bundle.getString("EXTRA_VIEW_ALL_BANNER_AD_IMG") != null) {
                this.C = bundle.getString("EXTRA_VIEW_ALL_BANNER_AD_IMG");
            }
            if (bundle.getString("EXTRA_BRAND_CTN_TRACKER") != null) {
                this.G = bundle.getString("EXTRA_BRAND_CTN_TRACKER");
            }
            if (bundle.getString("EXTRA_BRAND_DFP_TRACKER") != null) {
                this.H = bundle.getString("EXTRA_BRAND_DFP_TRACKER");
            }
            if (bundle.getString("EXTRA_VPL_TYPE") != null) {
                this.J = bundle.getString("EXTRA_VPL_TYPE");
                setSourceNameForVPL(this.J);
            }
            this.I = bundle.getBoolean("SEE_ALL_BOTTOM_BANNER_OFF");
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("bgf_saved_state", this.e);
        bundle.putString("EXTRA_SOURCE_NAME", this.D);
        bundle.putString("EXTRA_VIEW_ALL_BANNER_AD_IMG", this.C);
        bundle.putString("EXTRA_DYNAMIC_SECTION_UID", this.B);
        bundle.putString("EXTRA_BRAND_CTN_TRACKER", this.G);
        bundle.putString("EXTRA_BRAND_DFP_TRACKER", this.H);
        bundle.putBoolean("SEE_ALL_BOTTOM_BANNER_OFF", this.I);
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

    public View b() {
        return this.c != null ? this.c.a() : null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.u = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(this.u);
        this.h = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        if (this.d == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.d = setContentView(R.layout.item_listing, viewGroup);
            this.a = (LinearLayout) this.d.findViewById(R.id.llParentListing);
            if (ap.a().b(this.mContext)) {
                this.z = new PublisherAdView(this.mContext);
            }
            if (bundle == null) {
                a(getArguments());
            } else {
                a(bundle);
            }
            if (this.e != null) {
                d();
            }
        }
        if (this.e != null) {
            if (((GaanaActivity) this.mContext).getRefreshData() || PlaylistSyncManager.refreshFragment) {
                ((GaanaActivity) this.mContext).setRefreshData(false);
                PlaylistSyncManager.refreshFragment = false;
                if (this.c != null) {
                    g();
                } else {
                    d();
                }
            } else if (!(this.c == null || this.c.c() == null || this.c.c().getAdapter() == null)) {
                this.c.c().getAdapter().notifyDataSetChanged();
            }
            ((GaanaActivity) this.mContext).hideThemeBackground(false);
        } else {
            ((GaanaActivity) this.mContext).popBackStackImmediate();
        }
        if (!(this.e == null || GaanaApplication.getInstance().getListingComponents() == null)) {
            GaanaApplication.getInstance().getListingComponents().a(this.F);
        }
        return this.d;
    }

    public void onResume() {
        super.onResume();
        ColombiaAdViewManager.a().a((c) this);
        if (this.c != null) {
            this.c.e();
        }
        updateView();
        if (Constants.ab) {
            this.o.setImageResource(R.drawable.vector_shuffle_white);
        } else {
            this.o.setImageResource(R.drawable.vector_player_play_white);
        }
        if (this.z != null) {
            this.z.resume();
        }
    }

    public void c() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.favorite_tap_animation);
        loadAnimation.setInterpolator(new a(0.2d, 20.0d));
        this.o.startAnimation(loadAnimation);
    }

    public void onPause() {
        if (this.z != null) {
            this.z.pause();
        }
        ColombiaAdViewManager.a().a(null);
        super.onPause();
    }

    public void d() {
        this.a.removeAllViews();
        if (!this.e.i()) {
            this.c = new c(this.mContext, this);
        }
        this.c.a(this.g);
        this.c.c().setLayoutManager(new LinearLayoutManager(getActivity()));
        this.c.c().setHasFixedSize(false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int i = displayMetrics.widthPixels;
        this.c.c().setTouchInterceptionViewGroup((ViewGroup) this.d.findViewById(R.id.llParentListing));
        Bundle arguments = getArguments();
        if (arguments == null || !arguments.containsKey("ARG_SCROLL_Y")) {
            a(0, this.d);
        } else {
            final int i2 = arguments.getInt("ARG_SCROLL_Y", 0);
            f.a(this.c.c(), new Runnable() {
                public void run() {
                    int i = i2 % i;
                    LayoutManager layoutManager = SongParallexListingFragment.this.c.c().getLayoutManager();
                    if (layoutManager != null && (layoutManager instanceof LinearLayoutManager)) {
                        ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(0, -i);
                    }
                }
            });
            a(i2, this.d);
        }
        this.c.c().setScrollViewCallbacks(this);
        this.c.a((b) this);
        this.e.j().a(null);
        this.a.addView(this.c.b());
        m();
        this.c.b(this.e.j());
    }

    private void m() {
        this.d = this.a;
        this.i = (ObservableRecyclerView) this.d.findViewById(R.id.recycler_view);
        this.j = (CrossFadeImageView) this.d.findViewById(R.id.details_artwork);
        this.k = this.d.findViewById(R.id.img_background);
        this.l = this.d.findViewById(R.id.overlay);
        this.m = (TextView) this.d.findViewById(R.id.album_title);
        this.n = (TextView) this.d.findViewById(R.id.tvAlbumSongCount_Value);
        this.i.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.i.setHasFixedSize(false);
        this.o = (FloatingActionButton) this.d.findViewById(R.id.shuffle_play_button);
        this.p = this.d.findViewById(R.id.button_padding);
        this.o.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SongParallexListingFragment.this.c.f() != null && SongParallexListingFragment.this.c.f().g() != null) {
                    ArrayList a = d.a().a(SongParallexListingFragment.this, SongParallexListingFragment.this.mAppState.getCurrentBusObjInListView(), 0);
                    if (Constants.ab) {
                        Collections.shuffle(a);
                    }
                    if (a != null && a.size() > 0) {
                        ((PlayerTrack) a.get(0)).d(true);
                    }
                    PlayerManager.a(SongParallexListingFragment.this.mContext).b(a, SongParallexListingFragment.this.mContext);
                }
            }
        });
        this.r = (Toolbar) this.d.findViewById(R.id.main_toolbar);
        this.q = this.d.findViewById(R.id.toolbar_dummy_view);
        this.r.setContentInsetsAbsolute(0, 0);
        q();
        this.s = new DetailsMaterialActionBar(this.mContext);
        this.r.addView(this.s);
        this.s.showContextMenu(false);
        ((TextView) this.s.findViewById(R.id.title)).setText("");
        ((ImageView) this.s.findViewById(R.id.menu_icon)).setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.vec_actionbar_back));
        if (Constants.l) {
            DrawableCompat.setTint(((ImageView) this.s.findViewById(R.id.menu_icon)).getDrawable(), ViewCompat.MEASURED_STATE_MASK);
        } else {
            DrawableCompat.setTint(((ImageView) this.s.findViewById(R.id.menu_icon)).getDrawable(), -1);
        }
        this.s.setToolbar(this.r);
        String b = Constants.b(this.e.j().d());
        if (TextUtils.isEmpty(b)) {
            this.m.setText("");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(b.substring(0, 1).toUpperCase());
            stringBuilder.append(b.length() > 1 ? b.substring(1) : "");
            this.m.setText(stringBuilder.toString());
        }
        if (Constants.l) {
            this.m.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.n.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            this.m.setTextColor(-1);
            this.n.setTextColor(-1);
        }
        this.m.setLayoutParams(new LayoutParams((int) (((float) this.u.widthPixels) / 1.4f), -2));
        i.g(this.l, (float) this.h);
        this.n.post(new Runnable() {
            public void run() {
                i.g(SongParallexListingFragment.this.n, (float) ((int) ((((float) SongParallexListingFragment.this.h) - (((float) SongParallexListingFragment.this.n.getHeight()) * 1.2f)) - SongParallexListingFragment.this.mContext.getResources().getDimension(R.dimen.albumbutton_anchor_height))));
                i.b(SongParallexListingFragment.this.n, 0.0f);
                i.c(SongParallexListingFragment.this.n, 0.0f);
            }
        });
        this.m.post(new Runnable() {
            public void run() {
                i.g(SongParallexListingFragment.this.m, (float) ((int) ((((float) (SongParallexListingFragment.this.h - SongParallexListingFragment.this.m.getHeight())) - (((float) SongParallexListingFragment.this.n.getHeight()) * 1.2f)) - ((float) SongParallexListingFragment.this.p.getHeight()))));
                i.b(SongParallexListingFragment.this.m, 0.0f);
                i.c(SongParallexListingFragment.this.m, 0.0f);
                i.d(SongParallexListingFragment.this.m, 1.2f);
                i.e(SongParallexListingFragment.this.m, 1.2f);
            }
        });
        this.p.post(new Runnable() {
            public void run() {
                i.g(SongParallexListingFragment.this.p, (float) (SongParallexListingFragment.this.h - SongParallexListingFragment.this.p.getHeight()));
            }
        });
        this.q.post(new Runnable() {
            public void run() {
                i.a(SongParallexListingFragment.this.q, 0.0f);
            }
        });
        n();
        s();
        b(-1);
    }

    private void b(final int i) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                SongParallexListingFragment.this.i.smoothScrollBy(0, i);
            }
        }, 50);
    }

    private void n() {
        if (this.c.d() != null) {
            ArrayList adapterArrayList = this.c.d().getAdapterArrayList();
            if (adapterArrayList != null && this.n != null) {
                TextView textView = this.n;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(adapterArrayList.size());
                stringBuilder.append(" Songs");
                textView.setText(stringBuilder.toString());
            }
        }
    }

    public void e() {
        this.x = 0;
        this.v = new ArrayList();
        this.w = new ArrayList();
        if (this.c == null || this.c.d() == null) {
            showNetworkErrorView(null);
            return;
        }
        ArrayList adapterArrayList = this.c.d().getAdapterArrayList();
        Track track;
        if (adapterArrayList != null && adapterArrayList.size() > 3) {
            if (this.v.size() == 0 && (((!TextUtils.isEmpty(this.D) && this.D.equalsIgnoreCase("TRENDING_SONG")) || (!TextUtils.isEmpty(this.mAppState.getPlayoutSectionName()) && this.mAppState.getPlayoutSectionName().equalsIgnoreCase("MADE_FOR_YOU"))) && !TextUtils.isEmpty(this.C))) {
                this.v.add(this.C);
            }
            for (int i = 0; i < adapterArrayList.size(); i++) {
                Object obj = adapterArrayList.get(i);
                obj = obj instanceof Track ? ((Track) obj).getArtworkLarge() : null;
                if (!(obj == null || this.v.contains(obj))) {
                    this.v.add(obj);
                    if (this.v.size() >= 4) {
                        break;
                    }
                }
            }
            if (this.v.size() > 3) {
                a((String) this.v.get(this.x));
            } else if (adapterArrayList != null && adapterArrayList.size() > 0) {
                track = (Track) adapterArrayList.get(0);
                if (track.isLocalMedia()) {
                    this.j.bindImage(track.getArtwork(), ScaleType.CENTER_CROP);
                } else {
                    this.j.bindImage(track.getArtworkLarge(), ScaleType.CENTER_CROP);
                }
            }
        } else if (adapterArrayList != null && adapterArrayList.size() > 0) {
            track = (Track) adapterArrayList.get(0);
            if (track.isLocalMedia()) {
                this.j.bindImage(track.getArtwork(), ScaleType.CENTER_CROP);
            } else {
                this.j.bindImage(track.getArtworkLarge(), ScaleType.CENTER_CROP);
            }
        }
        n();
    }

    private boolean o() {
        if (this.mContext == null) {
            return false;
        }
        if (this.z == null) {
            this.z = new PublisherAdView(this.mContext);
        }
        if (((((GaanaActivity) this.mContext).getCurrentFragment() instanceof FavoritesFragment) || (((GaanaActivity) this.mContext).getCurrentFragment() instanceof SongParallexListingFragment)) && ap.a().b(this.mContext)) {
            return true;
        }
        return false;
    }

    private void a(String str) {
        if (this.v.size() > 3) {
            i.a().a(str.replace("480x480", "175x175"), new r() {
                public void onSuccessfulResponse(Bitmap bitmap) {
                    if (bitmap != null && SongParallexListingFragment.this.h > 0) {
                        try {
                            SongParallexListingFragment.this.w.add(Bitmap.createScaledBitmap(bitmap, SongParallexListingFragment.this.h, SongParallexListingFragment.this.h, false));
                            SongParallexListingFragment.this.x = SongParallexListingFragment.this.x + 1;
                            if (SongParallexListingFragment.this.x >= 4) {
                                SongParallexListingFragment.this.j.setImageBitmap(SongParallexListingFragment.this.p());
                            } else {
                                SongParallexListingFragment.this.a((String) SongParallexListingFragment.this.v.get(SongParallexListingFragment.this.x));
                            }
                        } catch (OutOfMemoryError unused) {
                        }
                    }
                }

                public void onErrorResponse(VolleyError volleyError) {
                    ArrayList adapterArrayList = SongParallexListingFragment.this.c.d().getAdapterArrayList();
                    if (adapterArrayList != null && adapterArrayList.size() > 0) {
                        Track track = (Track) adapterArrayList.get(0);
                        if (track.isLocalMedia()) {
                            SongParallexListingFragment.this.j.bindImage(track.getArtwork(), ScaleType.CENTER_CROP);
                        } else {
                            SongParallexListingFragment.this.j.bindImage(track.getArtworkLarge(), ScaleType.CENTER_CROP);
                        }
                    }
                }
            });
        }
    }

    private void a(ViewGroup viewGroup) {
        if (this.G != null && !TextUtils.isEmpty(this.G)) {
            e.a().a((View) viewGroup, this.mContext, Long.parseLong(this.G));
        } else if (this.H != null && !TextUtils.isEmpty(this.H)) {
            ColombiaAdViewManager.a().a((View) viewGroup, this.mContext, this.H);
        }
    }

    private Bitmap p() {
        Bitmap createBitmap = Bitmap.createBitmap(this.h * 2, this.h * 2, Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        for (int i = 0; i < this.w.size(); i++) {
            if (com.utilities.d.d()) {
                Bitmap a = Util.a((Bitmap) this.w.get(i));
                if (a != null) {
                    if (((TextUtils.isEmpty(this.D) || TextUtils.isEmpty(this.D) || !this.D.equalsIgnoreCase("TRENDING_SONG")) && (TextUtils.isEmpty(this.mAppState.getPlayoutSectionName()) || !this.mAppState.getPlayoutSectionName().equalsIgnoreCase("MADE_FOR_YOU"))) || TextUtils.isEmpty(this.C)) {
                        canvas.drawBitmap(a, (float) (this.h * (i % 2)), (float) (this.h * (i / 2)), paint);
                    } else {
                        a(i, a, canvas, paint);
                    }
                    a.recycle();
                }
            } else if (((TextUtils.isEmpty(this.D) || TextUtils.isEmpty(this.D) || !this.D.equalsIgnoreCase("TRENDING_SONG")) && (TextUtils.isEmpty(this.mAppState.getPlayoutSectionName()) || !this.mAppState.getPlayoutSectionName().equalsIgnoreCase("MADE_FOR_YOU"))) || TextUtils.isEmpty(this.C)) {
                canvas.drawBitmap((Bitmap) this.w.get(i), (float) (this.h * (i % 2)), (float) (this.h * (i / 2)), paint);
            } else {
                a(i, (Bitmap) this.w.get(i), canvas, paint);
            }
        }
        return createBitmap;
    }

    private void a(int i, Bitmap bitmap, Canvas canvas, Paint paint) {
        if (i == 0) {
            this.d.findViewById(R.id.img_background).setVisibility(8);
            canvas.drawBitmap(bitmap, null, new Rect(0, 0, this.h * 2, this.h), paint);
            a((ViewGroup) this.d);
        } else if (i > 1) {
            canvas.drawBitmap((Bitmap) this.w.get(i), (float) (this.h * (i % 2)), (float) (this.h * (i / 2)), paint);
        }
    }

    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        super.onUpOrCancelMotionEvent(scrollState);
        if ((scrollState == ScrollState.UP || scrollState == ScrollState.DOWN) && this.K > this.L) {
            this.M = ((LinearLayoutManager) this.c.c().getLayoutManager()).findLastCompletelyVisibleItemPosition() + 1;
            this.N = this.c.c().getAdapter().getItemCount();
            an.a().c("scroll", AvidJSONUtil.KEY_Y, this.B, "", "", "", String.valueOf(this.N), String.valueOf(this.M));
            this.L = this.K;
        }
    }

    public void onScrollChanged(int i, boolean z, boolean z2) {
        if (i > this.u.widthPixels) {
            onScrollChanged(this.u.widthPixels, false, false);
        } else if (((LinearLayoutManager) this.i.getLayoutManager()).findFirstVisibleItemPosition() != 0) {
            i = (int) (-b().getY());
        }
        this.K = i;
        float f = (float) (this.h - f());
        int i2 = -i;
        float f2 = (float) (f() - this.l.getHeight());
        i.g(this.l, f.a((float) i2, f2, (float) f()));
        float f3 = (float) (i2 / 2);
        i.g(this.j, f.a(f3, f2, 0.0f));
        i.g(this.k, f.a(f3, f2, 0.0f));
        float f4 = (float) i;
        f3 = f4 / f;
        i.a(this.l, f.a(f3, 0.0f, 1.0f));
        i.a(this.p, f.a(b - f3, 0.0f, 1.0f));
        f2 = 0.7f + f.a((f - f4) / f, 0.0f, 0.5f);
        r();
        i.c(this.m, 0.0f);
        f = (float) (((int) (((((float) this.h) - (((float) this.m.getHeight()) * f2)) - ((float) this.n.getHeight())) - ((float) this.p.getHeight()))) + i2);
        i.g(this.m, f.a(f, ((float) (f() / 2)) - ((((float) this.m.getHeight()) * f2) / 1.5f), (float) this.h));
        i.g(this.n, f.a(f, 0.0f, (float) (this.h - this.p.getHeight())));
        f = f.a((float) ((i2 + this.h) - this.p.getHeight()), 0.0f, (float) this.h);
        i.g(this.p, f);
        if (((double) f) < ((double) f()) * 1.5d) {
            this.o.hide();
        } else {
            this.o.show();
        }
        f4 = (this.t == null || !this.t.isVisible()) ? 2.0f : 2.5f;
        if (f < ((float) (f() * 2))) {
            this.n.setVisibility(4);
            this.m.setLayoutParams(new LayoutParams((int) (((float) this.u.widthPixels) / f4), -2));
            i.f(this.m, f.a((float) (i / 6), (float) (f() / 3), ((float) f()) / 1.4f));
            i.d(this.m, f.a(f2, 0.8f, 1.0f));
            i.e(this.m, f.a(f2, 0.8f, 1.0f));
            return;
        }
        this.n.setVisibility(0);
        float f5 = (float) (i / 6);
        i.f(this.m, f.a(f5, 0.0f, (float) f()));
        i.f(this.n, f.a(f5, 0.0f, (float) f()));
        i.d(this.m, f2);
        i.e(this.m, f2);
        this.m.setLayoutParams(new LayoutParams(this.u.widthPixels, -2));
    }

    private void q() {
        this.r.getMenu().clear();
        this.r.inflateMenu(R.menu.cast_menu_detail);
        ((BaseActivity) this.mContext).initializeMediaRouterButton(this.r.getMenu(), R.id.media_route_menu_item);
        Menu menu = this.r.getMenu();
        menu.findItem(R.id.menu_download).setVisible(false);
        menu.findItem(R.id.menu_option).setVisible(false);
        if (Constants.l) {
            DrawableCompat.setTint(menu.findItem(R.id.menu_search).getIcon(), ViewCompat.MEASURED_STATE_MASK);
        } else {
            DrawableCompat.setTint(menu.findItem(R.id.menu_search).getIcon(), -1);
        }
        if (menu != null) {
            this.t = menu.findItem(R.id.media_route_menu_item);
        }
    }

    /* Access modifiers changed, original: protected */
    public int f() {
        int[] iArr = new int[]{R.attr.actionBarSize};
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new TypedValue().data, iArr);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    @TargetApi(17)
    private void r() {
        Configuration configuration = this.mContext.getResources().getConfiguration();
        if (17 > VERSION.SDK_INT || configuration.getLayoutDirection() != 1) {
            i.b(this.m, 0.0f);
        } else {
            i.b(this.m, (float) this.d.getWidth());
        }
    }

    public void refreshListView() {
        if (!(this.c == null || this.c.d() == null)) {
            this.c.d().notifyDataSetChanged();
        }
        if (this.a != null) {
            Object tag = this.a.getTag();
            if (tag != null && (tag instanceof RecommendedPageView)) {
                ((RecommendedPageView) tag).refreshListView(true);
            }
        }
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        super.refreshListView(businessObject, z);
        if (businessObject == null || !z) {
            refreshListView();
            return;
        }
        this.c.d().removeItem(businessObject);
        e();
    }

    public void g() {
        if (this.c != null) {
            this.c.k();
        }
        if (this.a != null) {
            Object tag = this.a.getTag();
            if (tag != null && (tag instanceof RecommendedPageView)) {
                ((RecommendedPageView) tag).refreshListView(true);
            }
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public void a(BusinessObject businessObject, BusinessObjectType businessObjectType) {
        this.E = true;
        this.F = businessObject;
        if (getActivity() != null) {
            if (businessObject != null) {
                a(businessObject.getArrListBusinessObj());
                if (this.mAppState.isAppLaucnhedFromDeeplinking() && this.m.getText().toString().equalsIgnoreCase("Gaana") && !TextUtils.isEmpty(businessObject.getRawName())) {
                    String b = Constants.b(businessObject.getRawName());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(b.substring(0, 1).toUpperCase());
                    stringBuilder.append(b.length() > 1 ? b.substring(1) : "");
                    this.m.setText(stringBuilder.toString());
                }
            }
            if (this.mAppState.isAppLaucnhedFromDeeplinking() && (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0)) {
                aj.a().a(this.mContext, getString(R.string.empty_vpl));
                ((GaanaActivity) this.mContext).displayLaunchFragment(GaanaActivity.SHOW_TAB_HOME, null);
            }
            s();
            if (this.f != null) {
                this.f.onDataLoaded(businessObject, businessObjectType);
            }
        }
        this.c.c().setVisibility(0);
        if (this.e != null && this.e.j().c().j() != UserRecentActivity.class) {
            b(1);
        }
    }

    private void s() {
        if (this.p != null && this.E) {
            if (Constants.l) {
                this.p.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.view_foreground_light));
                b = 1.0f;
                this.d.findViewById(R.id.overlay).setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.view_foreground_light));
                return;
            }
            this.p.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.view_background_dark));
            b = 1.0f;
            this.d.findViewById(R.id.overlay).setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.view_background_dark));
        }
    }

    private boolean t() {
        return this.A != ADSTATUS.LOADING;
    }

    private void a(ArrayList<BusinessObject> arrayList) {
        if (o() && t() && !this.I) {
            if (!TextUtils.isEmpty(this.B)) {
                this.mAppState.setGADParameter(this.B);
            }
            if (!TextUtils.isEmpty(this.J)) {
                this.mAppState.setVPLTypeParameter(this.J);
            }
            ColombiaAdViewManager.a().e();
            e.a();
            if (e.X == 0) {
                ColombiaAdViewManager.a().b(this.mContext, this.c.b(), e.A, this.z, this, GaanaApplication.getInstance().getPlayoutSectionName());
                return;
            }
            ColombiaAdViewManager.a().a(this.mContext, this.c.b(), 28, getClass().getSimpleName(), this.z, (ColombiaAdViewManager.b) this, GaanaApplication.getInstance().getPlayoutSectionName(), new AdsUJData[0]);
        }
    }

    public void onSearch(ArrayList<BusinessObject> arrayList) {
        a((ArrayList) arrayList);
    }

    public void a(int i, int i2) {
        View view = getView();
        if (view != null) {
            ObservableRecyclerView observableRecyclerView = (ObservableRecyclerView) view.findViewById(R.id.recycler_view);
            if (observableRecyclerView != null) {
                int i3 = 0;
                View childAt = observableRecyclerView.getChildAt(0);
                if (childAt != null) {
                    if (i2 < i) {
                        i2 = childAt.getHeight();
                        i3 = i / i2;
                        i %= i2;
                    }
                    LayoutManager layoutManager = observableRecyclerView.getLayoutManager();
                    if (layoutManager != null && (layoutManager instanceof LinearLayoutManager)) {
                        ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(i3, -i);
                    }
                }
            }
        }
    }

    public void onStop() {
        super.onStop();
        if (al.a) {
            h();
        }
    }

    public void onDestroyView() {
        ColombiaManager.b().a();
        if (this.z != null) {
            this.z.destroy();
        }
        if (!TextUtils.isEmpty(this.J)) {
            this.mAppState.removeVPLTypeParameter();
        }
        if (!(this.d == null || this.d.getParent() == null)) {
            ((ViewGroup) this.d.getParent()).removeView(this.d);
        }
        super.onDestroyView();
    }

    public void a(View view, int i) {
        b(view, i);
    }

    private void b(View view, int i) {
        this.y = i;
        this.s.setParams(this, this.c.j());
        this.s.showContextMenu(true);
        al.a().a(true);
        al.a().a((BusinessObject) view.getTag(), true);
        ((CheckBox) view.findViewById(R.id.f35download.item.checkbox)).setChecked(true);
        j();
    }

    public void h() {
        this.y = 0;
        if (this.s != null) {
            this.s.showContextMenu(false);
            al.a().a(false);
            al.a().c();
            refreshListView();
        }
    }

    public void i() {
        if (al.a().e()) {
            al.a().c();
        } else {
            al.a().a(this.c.f().g());
        }
        refreshListView();
        j();
    }

    public void j() {
        this.s.updateSelectedCountinContextMode(this.y);
    }

    public void a(ADSTATUS adstatus) {
        this.A = adstatus;
    }

    public void b(ADSTATUS adstatus) {
        this.A = adstatus;
    }

    public void c(ADSTATUS adstatus) {
        this.A = adstatus;
    }

    public void d(ADSTATUS adstatus) {
        this.A = adstatus;
    }

    public String k() {
        return this.e != null ? this.e.n() : null;
    }

    public void l() {
        if (o()) {
            e.a();
            if (e.V == 0) {
                ColombiaAdViewManager.a().b(this.mContext, this.c.b(), e.A, this.z, this, "");
                return;
            }
            ColombiaAdViewManager.a().a(this.mContext, this.c.b(), 28, getClass().getSimpleName(), this.z, (ColombiaAdViewManager.b) this, "", new AdsUJData[0]);
        }
    }

    public String getSectionName() {
        return this.e.p();
    }

    public void refreshDataandAds() {
        if (this.c.b().findViewById(R.id.llNativeAdSlot) != null) {
            this.c.b().findViewById(R.id.llNativeAdSlot).setVisibility(8);
        }
        g();
    }

    public void a(ListingParams listingParams) {
        this.e = listingParams;
    }
}
