package com.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.actionbar.PlayerMaterialActionBar;
import com.constants.Constants;
import com.constants.Constants.ErrorType;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.adapter.CardPagerAdapter;
import com.gaana.adapter.MusicQueueAdapter.IUpdatePlayer;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Tracks.Track;
import com.gaana.view.DiscreteScrollView;
import com.gaana.view.DiscreteScrollView.OnItemChangedListener;
import com.gaana.view.DownloadClickAnimation;
import com.gaana.view.PlayerQueueView;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.gaana.view.item.PopupWindowView;
import com.gaana.view.transform.ScaleTransformer;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.ADSTATUS;
import com.managers.ColombiaAdViewManager.b;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlaySequenceType;
import com.managers.PlayerManager.PlayerType;
import com.managers.ad;
import com.managers.af;
import com.managers.aj;
import com.managers.ap;
import com.managers.ap.a;
import com.managers.e;
import com.managers.u;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.PlayerConstants.PlayerCommands;
import com.player_framework.PlayerConstants.RepeatModes;
import com.player_framework.PlayerStatus;
import com.player_framework.PlayerStatus.PlayerStates;
import com.player_framework.f;
import com.player_framework.m;
import com.player_framework.n;
import com.player_framework.o;
import com.services.d;
import com.services.l.as;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;
import com.views.QueueSlidingUpPanelLayout;
import com.views.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class PlayerFragment extends BaseFragment implements OnClickListener, IUpdatePlayer, b {
    private ImageView A;
    private ImageView B;
    private ImageView C;
    private ImageView D;
    private ImageView E;
    private TextView F;
    private TextView G;
    private TextView H;
    private TypedValue I;
    private View J;
    private PlayerMaterialActionBar K;
    private ImageView L = null;
    private Drawable M;
    private Drawable N;
    private ProgressBar O;
    private ProgressBar P;
    private SeekBar Q;
    private View R;
    private TextView S;
    private PlayerQueueView T;
    private ImageView U;
    private ImageView V;
    private QueueSlidingUpPanelLayout W;
    private boolean X = false;
    private boolean Y = false;
    private CardPagerAdapter Z;
    int[] a = new int[]{R.attr.miniplayer_pause, R.attr.miniplayer_play};
    private DiscreteScrollView aa;
    private View ab;
    private View ac;
    private Drawable ad;
    private boolean ae;
    private PublisherAdView af;
    private boolean ag;
    private int ah = 0;
    private DownloadClickAnimation ai;
    private ProgressBar aj;
    private TextView ak;
    private TextView al;
    private boolean am = true;
    private boolean an = false;
    private boolean ao = true;
    private boolean ap = false;
    private final Handler aq = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            boolean z = false;
            PlayerFragment playerFragment;
            boolean z2;
            switch (message.what) {
                case 1000:
                    if (GaanaMusicService.s().isPlaying()) {
                        PlayerFragment.this.mPlayerStates = PlayerStates.PLAYING;
                        PlayerFragment.this.b();
                    }
                    if (PlayerFragment.this.g) {
                        PlayerFragment.this.t();
                        PlayerFragment.this.f();
                        PlayerFragment.this.g = false;
                        return;
                    }
                    PlayerFragment.this.s();
                    PlayerFragment.this.f();
                    return;
                case 1001:
                    PlayerFragment.this.y();
                    return;
                case 1002:
                    PlayerFragment.this.b(PlayerFragment.this.ap);
                    return;
                case 1003:
                    playerFragment = PlayerFragment.this;
                    z2 = message.arg1 == 1;
                    if (message.arg2 == 1) {
                        z = true;
                    }
                    playerFragment.b(z2, z);
                    return;
                case PointerIconCompat.TYPE_WAIT /*1004*/:
                    playerFragment = PlayerFragment.this;
                    z2 = message.arg1 == 1;
                    if (message.arg2 == 1) {
                        z = true;
                    }
                    playerFragment.a(z2, z);
                    return;
                case 1005:
                    PlayerFragment.this.w();
                    return;
                case PointerIconCompat.TYPE_CELL /*1006*/:
                    PlayerFragment.this.x();
                    return;
                case PointerIconCompat.TYPE_CROSSHAIR /*1007*/:
                    f fVar = (f) message.obj;
                    int i = message.arg1;
                    if (PlayerStatus.a(PlayerFragment.this.getContext()).c()) {
                        PlayerFragment.this.a(fVar, i);
                        return;
                    }
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    };
    private m ar = new m() {
        public void onAdEventUpdate(f fVar, AdEvent adEvent) {
        }

        public void onInfo(f fVar, int i, int i2) {
        }

        public void onPrepared(f fVar) {
            if (!(PlayerFragment.this.isActivityDestroyed() || PlayerFragment.this.aq == null)) {
                PlayerFragment.this.aq.sendEmptyMessage(1000);
            }
        }

        public void onError(f fVar, int i, int i2) {
            if (!PlayerFragment.this.isActivityDestroyed()) {
                if ((i == -1000 || i == -1001) && PlayerFragment.this.aq != null) {
                    PlayerFragment.this.aq.sendEmptyMessage(1001);
                }
            }
        }

        public void onCompletion(f fVar) {
            PlayerFragment.this.mPlayerStates = PlayerStates.STOPPED;
        }

        public void onBufferingUpdate(f fVar, int i) {
            if (!(PlayerFragment.this.isActivityDestroyed() || PlayerFragment.this.aq == null)) {
                Message obtain = Message.obtain(PlayerFragment.this.aq);
                obtain.what = PointerIconCompat.TYPE_CROSSHAIR;
                obtain.obj = fVar;
                obtain.arg1 = i;
                obtain.sendToTarget();
            }
        }
    };
    private Handler as = new Handler();
    private Runnable at = new Runnable() {
        public void run() {
            PlayerFragment.this.r();
            PlayerFragment.this.Z.setShouldUpdate(true);
            if (PlayerManager.a(GaanaApplication.getContext()).n() != null && PlayerManager.a(GaanaApplication.getContext()).s() <= PlayerManager.a(GaanaApplication.getContext()).n().size()) {
                PlayerFragment.this.aa.scrollToPosition(PlayerManager.a(GaanaApplication.getContext()).s());
            }
            PlayerFragment.this.am = false;
        }
    };
    n b = new n() {
        public void displayErrorToast(String str, int i) {
        }

        public void onStreamingQualityChanged(int i) {
        }

        public void onPlayerPlay() {
            PlayerFragment.this.ap = false;
            if (!PlayerFragment.this.ao) {
                PlayerFragment.this.ao = true;
            } else if (PlayerFragment.this.mPlayerManager.d()) {
                PlayerFragment.this.mPlayerManager.b(false);
                PlayerFragment.this.ap = true;
            }
            if (!(PlayerFragment.this.isActivityDestroyed() || PlayerFragment.this.aq == null)) {
                PlayerFragment.this.aq.sendEmptyMessage(1002);
            }
        }

        public void onPlayerRepeatReset(boolean z) {
            PlayerFragment.this.ao = z;
        }

        public void onPlayerPause() {
            if (!(PlayerFragment.this.isActivityDestroyed() || PlayerFragment.this.aq == null)) {
                PlayerFragment.this.aq.sendEmptyMessage(1005);
            }
        }

        public void onPlayerResume() {
            if (!(PlayerFragment.this.isActivityDestroyed() || PlayerFragment.this.aq == null)) {
                PlayerFragment.this.aq.sendEmptyMessage(PointerIconCompat.TYPE_CELL);
            }
        }

        public void onPlayerStop() {
            if (!(PlayerFragment.this.isActivityDestroyed() || PlayerFragment.this.aq == null)) {
                PlayerFragment.this.aq.sendEmptyMessage(1001);
            }
        }

        public void onPlayPrevious(boolean z, boolean z2) {
            if (!(PlayerFragment.this.isActivityDestroyed() || PlayerFragment.this.aq == null)) {
                Message obtain = Message.obtain(PlayerFragment.this.aq);
                obtain.what = PointerIconCompat.TYPE_WAIT;
                obtain.arg1 = z;
                obtain.arg2 = z2;
                obtain.sendToTarget();
            }
        }

        public void onPlayNext(boolean z, boolean z2) {
            if (!(PlayerFragment.this.isActivityDestroyed() || PlayerFragment.this.aq == null)) {
                Message obtain = Message.obtain(PlayerFragment.this.aq);
                obtain.what = 1003;
                obtain.arg1 = z;
                obtain.arg2 = z2;
                obtain.sendToTarget();
            }
        }

        public void displayErrorDialog(String str, ErrorType errorType) {
            if (!(PlayerFragment.this.isActivityDestroyed() || PlayerFragment.this.aq == null)) {
                PlayerFragment.this.aq.sendEmptyMessage(1001);
            }
        }
    };
    TranslateAnimation c;
    TranslateAnimation d;
    private int e;
    private boolean f;
    private boolean g;
    private int h = 0;
    private long i = 0;
    private Toolbar j;
    private final Handler k = new Handler();
    private i l;
    private Context m;
    private PopupWindowView n;
    private PopupWindow o;
    private ViewGroup p;
    private Drawable q;
    private Drawable r;
    private Drawable s;
    private Drawable t;
    private Drawable u;
    private Drawable v;
    private int[] w = new int[]{R.drawable.vector_player_shuffle_white, R.drawable.vector_player_shuffle_active};
    private int[] x = new int[]{R.drawable.vector_player_repeat_white, R.drawable.vector_player_repeat_one, R.drawable.vector_player_repeat_active};
    private TextView y;
    private TextView z;

    public void a(ADSTATUS adstatus) {
    }

    public void a(PlayerType playerType) {
    }

    public void b(ADSTATUS adstatus) {
    }

    public void c(ADSTATUS adstatus) {
    }

    public void d(ADSTATUS adstatus) {
    }

    public void onPlayerStateChanged() {
    }

    public void onRadioTracksFetched(boolean z) {
    }

    public void updateCardAdapter(boolean z) {
    }

    private void f() {
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(getPlayingTrack().getBusinessObjId()));
        if (GaanaMusicService.s().m()) {
            if (this.ah != 0) {
                this.aj.setVisibility(0);
                this.V.setVisibility(0);
            } else if (e == DownloadStatus.DOWNLOADING) {
                this.P.setVisibility(8);
            } else {
                this.P.setVisibility(0);
            }
            this.U.setVisibility(8);
            this.O.setVisibility(0);
            this.L.setVisibility(4);
            if (this.ah == 0) {
                this.V.setVisibility(8);
            }
        } else if (GaanaMusicService.t()) {
            ((ProgressBar) this.J.findViewById(R.id.queue_panel_bottom_progressBar)).setVisibility(8);
            this.aj.setVisibility(8);
            this.U.setVisibility(0);
            this.U.setImageDrawable(this.ad);
            this.O.setVisibility(8);
            this.L.setVisibility(0);
            this.L.setImageDrawable(this.s);
            if (this.ah == 0) {
                this.U.setVisibility(8);
                if (e == DownloadStatus.DOWNLOADING) {
                    ((ImageView) this.p.findViewById(R.id.queue_panel_download_button)).setVisibility(4);
                } else {
                    ((ImageView) this.p.findViewById(R.id.queue_panel_download_button)).setVisibility(0);
                }
            }
        } else {
            this.P.setVisibility(8);
            this.aj.setVisibility(8);
            this.U.setVisibility(0);
            this.U.setImageDrawable(this.N);
            this.O.setVisibility(8);
            this.L.setVisibility(0);
            this.L.setImageDrawable(this.u);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.J = layoutInflater.inflate(R.layout.fragment_player, viewGroup, false);
        this.K = new PlayerMaterialActionBar(getContext());
        this.am = true;
        Toolbar toolbar = (Toolbar) this.J.findViewById(R.id.toolbar);
        toolbar.addView(this.K);
        toolbar.setContentInsetsAbsolute(0, 0);
        setGAScreenName("PlayerHomeScreen", "PlayerHomeScreen");
        ((GaanaActivity) this.m).getSlidingPanelLayout().b(1);
        return this.J;
    }

    private void g() {
        boolean z = getResources().getBoolean(R.bool.isPlayerAdEnabled);
        if (this.m != null && ap.a().b(this.m) && z) {
            if (this.af == null) {
                this.af = new PublisherAdView(this.m);
            }
            ColombiaAdViewManager.a().b(this.m, this.J, e.C, this.af, this, "");
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.m = getActivity();
        h();
    }

    public void a(i iVar) {
        this.l = iVar;
    }

    private void h() {
        int[] iArr = new int[]{R.attr.player_repeat, R.attr.player_shuffle, R.attr.bottom_pause_button, R.attr.moreoptions_favorited, R.attr.moreoptions_favorite, R.attr.player_pause, R.attr.bottom_play_button, R.attr.selector_btn_global_bg_transparent, R.attr.streaming_quality_layout_drawable};
        TypedArray obtainStyledAttributes = this.m.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.q = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(69, -1));
        this.r = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(70, -1));
        this.s = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(5, -1));
        this.t = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(30, -1));
        this.u = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(6, -1));
        this.v = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(86, -1));
        this.M = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(30, -1));
        this.N = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(31, -1));
        obtainStyledAttributes.recycle();
        g();
        this.ad = getResources().getDrawable(R.drawable.vector_player_pause_active);
        this.I = new TypedValue();
        boolean z = true;
        getActivity().getTheme().resolveAttribute(R.attr.song_quality_color, this.I, true);
        this.mPlayerManager = PlayerManager.a(getContext());
        this.mRadioManager = ad.a(this.m);
        this.p = (ViewGroup) this.J.findViewById(R.id.ll_queue_container);
        this.W = (QueueSlidingUpPanelLayout) this.J.findViewById(R.id.sliding_layout_queue);
        this.W.setAnchorPoint(0.88f);
        this.W.setOverlayed(true);
        this.W.setBackgroundResource(R.drawable.shape_bg_transparant);
        this.A = (ImageView) this.J.findViewById(R.id.playerBtnRepeat);
        this.A.setImageDrawable(this.q);
        this.A.setTag(Integer.valueOf(0));
        this.B = (ImageView) this.J.findViewById(R.id.playerBtnShuffle);
        this.B.setImageDrawable(this.r);
        this.B.setTag(Integer.valueOf(0));
        this.Q = (SeekBar) this.J.findViewById(R.id.seekBar);
        this.Q.setFocusable(false);
        this.Q.setPadding(0, 0, 0, 0);
        this.C = (ImageView) this.J.findViewById(R.id.playerBtnPrev);
        this.H = (TextView) this.J.findViewById(R.id.tvPlayerEndTimer);
        this.E = (ImageView) this.J.findViewById(R.id.lyricsButton);
        this.G = (TextView) this.J.findViewById(R.id.lyricsButtonText);
        this.G.setOnClickListener(this);
        this.E.setOnClickListener(this);
        this.ak = (TextView) this.J.findViewById(R.id.queue_panel_main_text_bottom);
        this.al = (TextView) this.J.findViewById(R.id.queue_panel_secondary_text_bottom);
        this.y = (TextView) this.K.findViewById(R.id.trackText);
        this.z = (TextView) this.K.findViewById(R.id.albumText);
        this.W.setPanelHeight((int) getResources().getDimension(R.dimen.playerG_miniplayer_height));
        F();
        this.L.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerFragment.this.a(view);
            }
        });
        this.R.setOnClickListener(this);
        this.J.findViewById(R.id.playerBtnRepeat).setOnClickListener(this);
        this.J.findViewById(R.id.playerBtnShuffle).setOnClickListener(this);
        this.J.findViewById(R.id.playerBtnPrev).setOnClickListener(this);
        this.D.setOnClickListener(this);
        k();
        this.mCurrentTrack = this.mPlayerManager.a(PlaySequenceType.CURRENT);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ONBOARD_PLAYER_CREATED_FIRST_TIME", 0);
        if (!(GaanaApplication.sessionHistoryCount == 0 && sharedPreferences.getBoolean("ONBOARD_PLAYER_CREATED_FIRST_TIME", true))) {
            z = false;
        }
        this.X = z;
        this.T = new PlayerQueueView(this.m, ((GaanaActivity) this.m).getCurrentFragment());
        i();
        D();
        a(this.mCurrentTrack.b());
        this.U = (ImageView) this.J.findViewById(R.id.queue_panel_bottom_button);
        this.P = (ProgressBar) this.J.findViewById(R.id.queue_panel_bottom_progressBar);
        this.aj = (ProgressBar) this.J.findViewById(R.id.queue_panel_bottom_progressBar2);
        this.U.setOnClickListener(this);
        this.V = (ImageView) this.p.findViewById(R.id.queue_panel_download_button);
        this.p.findViewById(R.id.queue_panel_button_save).setOnClickListener(this);
        this.p.findViewById(R.id.queue_panel_button_clear).setOnClickListener(this);
        this.p.findViewById(R.id.queue_panel_cheveron_view).setOnClickListener(this);
        this.p.findViewById(R.id.queue_panel_bottom_options).setOnClickListener(this);
        this.ai = new DownloadClickAnimation(this.m, this, this.V, getPlayingTrack(), this.J);
        a(((GaanaActivity) this.m).getSlidingPanelLayout());
        a(this.p);
    }

    /* Access modifiers changed, original: protected */
    public void a(ViewGroup viewGroup) {
        if (!d.a().b("PREFERENCE_KEY_QUEUE_ANIMATION_INITIATED", false, false)) {
            int b = d.a().b("QUEUE_ANIMATION_UP_DOWN", 0, false);
            if (b < 2) {
                int b2 = d.a().b("SESSION_OCCURENCE_QUEUE_ANIMATION_UP_DOWN", 0, false);
                int i = b2 + 10;
                if (b2 > 0) {
                    if (GaanaApplication.sessionHistoryCount + 1 >= i) {
                        a((View) viewGroup, b);
                    }
                } else if (b == 0 && GaanaApplication.sessionHistoryCount + 1 >= 5) {
                    a((View) viewGroup, b);
                }
            }
        }
    }

    private void a(View view, int i) {
        final int i2 = i + 1;
        final float c = (float) (d.a().c() / 3);
        final ImageView imageView = (ImageView) this.J.findViewById(R.id.handIcon);
        this.c = new TranslateAnimation(0.0f, 0.0f, 0.0f, -c);
        this.c.setFillEnabled(true);
        this.c.setFillAfter(true);
        this.c.setDuration(1000);
        this.c.setStartOffset(500);
        final View view2 = view;
        this.c.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                imageView.setVisibility(0);
            }

            public void onAnimationEnd(Animation animation) {
                PlayerFragment.this.d = new TranslateAnimation(0.0f, 0.0f, -c, 0.0f);
                PlayerFragment.this.d.setFillEnabled(true);
                PlayerFragment.this.d.setFillAfter(true);
                PlayerFragment.this.d.setStartOffset(1000);
                PlayerFragment.this.d.setDuration(1000);
                view2.startAnimation(PlayerFragment.this.d);
                d.a().a("QUEUE_ANIMATION_UP_DOWN", i2, false);
                d.a().a("SESSION_OCCURENCE_QUEUE_ANIMATION_UP_DOWN", GaanaApplication.sessionHistoryCount, false);
                PlayerFragment.this.d.setAnimationListener(new AnimationListener() {
                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        imageView.setVisibility(8);
                    }
                });
            }
        });
        view.startAnimation(this.c);
    }

    private void i() {
        this.aa = (DiscreteScrollView) this.J.findViewById(R.id.viewPager);
        this.Z = new CardPagerAdapter(this.m, this, this.aa, PlayerManager.a(this.m).n());
        this.aa.setAdapter(this.Z);
        this.aa.setItemTransformer(new ScaleTransformer());
        this.aa.setVisibility(0);
        j();
        this.aa.addOnItemChangedListener(new OnItemChangedListener<ViewHolder>() {
            private int b = -1;

            public void onCurrentItemChanged(@Nullable ViewHolder viewHolder, int i) {
                PlayerTrack a = PlayerManager.a(GaanaApplication.getContext()).a(i);
                if (!(a == null || a.b() == null)) {
                    PlayerFragment.this.a(a.b());
                    PlayerFragment.this.q();
                }
                if (this.b != i && i != PlayerManager.a(PlayerFragment.this.m).s()) {
                    u.a().a("BoxQueue", "Song Swipe", i > PlayerManager.a(PlayerFragment.this.m).s() ? "Up Next" : "Previous");
                    this.b = i;
                }
            }
        });
    }

    private void j() {
        if (this.m != null) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.aa.getLayoutParams();
            int c = d.a().c();
            boolean a = a(this.m);
            if (a) {
                a = getResources().getBoolean(R.bool.isPlayerAdEnabled);
            }
            marginLayoutParams.height = (int) (((float) c) / (a ? 2.7f : 2.5f));
            if (a) {
                marginLayoutParams.topMargin = 0;
                this.J.findViewById(R.id.llNativeAdSlot).setVisibility(0);
                g();
            } else {
                marginLayoutParams.topMargin = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
                this.J.findViewById(R.id.llNativeAdSlot).setVisibility(8);
            }
            this.aa.setLayoutParams(marginLayoutParams);
        }
    }

    private void k() {
        this.j = (Toolbar) this.J.findViewById(R.id.toolbar);
        this.j.setContentInsetsAbsolute(0, 0);
        this.j.getMenu().clear();
        this.j.inflateMenu(R.menu.cast_menu_player);
        ((BaseActivity) this.m).initializeMediaRouterButton(this.j.getMenu(), R.id.media_route_menu_item);
        this.K.setToolbar(this.j);
    }

    private void l() {
        Editor edit = GaanaApplication.getContext().getSharedPreferences("ONBOARD_PLAYER_CREATED_FIRST_TIME", 0).edit();
        edit.putBoolean("ONBOARD_PLAYER_CREATED_FIRST_TIME", false);
        edit.apply();
        Util.a(null);
        this.X = false;
        if (!(isActivityDestroyed() || this.l == null || this.l.a() != 0)) {
            this.l.a(0);
        }
    }

    private void a(boolean z) {
        if (this.l != null) {
            if (this.X && z && this.mPlayerManager.E() == null) {
                l();
            }
            if (!z) {
                this.l.a(2);
            } else if (this.l.a() == 2 && !com.managers.f.v().w()) {
                this.l.a(0);
            }
        }
    }

    private void m() {
        if (this.mPlayerManager.m() == PlayerType.GAANA_RADIO && this.mPlayerManager.g()) {
            this.mPlayerManager.a(PlayerType.GAANA_RADIO, getContext());
        } else if (this.mPlayerManager.m() != PlayerType.GAANA_RADIO) {
            this.mPlayerManager.f(false);
        }
    }

    public void onResume() {
        super.onResume();
        try {
            a();
            if (this.am) {
                this.as.postDelayed(this.at, 100);
            }
        } catch (Exception unused) {
        }
        if (this.ag != a(this.m)) {
            this.ag = a(this.m);
            j();
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private boolean a(Context context) {
        if (!ap.a().b(context)) {
            return false;
        }
        if (GaanaApplication.getInstance().getCurrentUser() == null || GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData() == null || GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getAccountType() != 2) {
            return true;
        }
        return false;
    }

    public void a() {
        this.m = getContext();
        if (this.m != null) {
            if (this.mPlayerManager.n() == null || this.mPlayerManager.n().size() <= 0) {
                a(false);
                return;
            }
            a(true);
            m();
            o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.b);
            o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.ar);
            this.mAppState.setPlayerStatus(true);
            this.mCurrentTrack = PlayerManager.a(GaanaApplication.getContext()).i();
            this.Z.updateArrayList(PlayerManager.a(this.m).n());
            if (this.mPlayerManager.t() && this.mPlayerManager.s() != -1) {
                this.mCurrentTrack = this.mPlayerManager.a(this.mPlayerManager.s());
                a(this.mCurrentTrack);
            }
            a(this.mPlayerManager.m());
            b(getPlayingTrack());
            n();
            if (PlayerManager.a) {
                o.a(getContext());
                PlayerManager.a = false;
            } else if (PlayerManager.b) {
                PlayerManager.b = false;
            } else if (this.mPlayerManager.m() == PlayerType.GAANA_RADIO && this.mPlayerManager.g()) {
                this.mPlayerManager.e(false);
                this.mCurrentTrack = this.mPlayerManager.a(PlaySequenceType.CURRENT);
                GaanaMusicService.s().b(false);
                o.a(getContext(), this.mCurrentTrack);
            } else {
                if (this.mPlayerManager.m() == PlayerType.GAANA_RADIO && !this.mPlayerManager.h()) {
                    this.mCurrentTrack = PlayerManager.a(this.m).i();
                }
                if (PlayerStatus.a(getContext()).c()) {
                    this.mPlayerStates = PlayerStates.PLAYING;
                    a(this.mCurrentTrack);
                    s();
                } else if (GaanaMusicService.s().l() && !GaanaMusicService.s().m() && !PlayerStatus.a(getContext()).e()) {
                    a(this.mCurrentTrack);
                    s();
                } else if (GaanaMusicService.s().m()) {
                    a(this.mCurrentTrack);
                    if (GaanaMusicService.s().l()) {
                        this.L.setImageDrawable(this.s);
                        this.U.setImageDrawable(this.ad);
                    } else {
                        this.L.setImageDrawable(this.s);
                        this.U.setImageDrawable(this.ad);
                    }
                } else if (PlayerStatus.a(getContext()).e()) {
                    a(this.mCurrentTrack);
                    u();
                }
            }
        }
    }

    private void n() {
        o();
        a(this.A, false);
        if (PlayerStatus.a(getContext()).c() || PlayerStatus.a(getContext()).b()) {
            this.L.setImageDrawable(this.s);
            this.U.setImageDrawable(this.ad);
        } else {
            this.L.setImageDrawable(this.u);
            this.U.setImageDrawable(this.N);
        }
        p();
    }

    private void o() {
        if (this.mPlayerManager.d()) {
            this.A.setTag(Integer.valueOf(0));
        } else if (this.mPlayerManager.e()) {
            this.A.setTag(Integer.valueOf(1));
        } else {
            this.A.setTag(Integer.valueOf(2));
        }
    }

    private void p() {
        boolean b = PlayerManager.a(this.m).b();
        if (b) {
            this.B.setImageResource(this.w[b]);
        } else {
            this.B.setImageDrawable(this.r);
        }
        this.B.setTag(Integer.valueOf(b));
    }

    public void a(f fVar, int i) {
        if (this.Q == null) {
            this.Q = (SeekBar) this.J.findViewById(R.id.seekBar);
            this.Q.setFocusable(false);
        }
        if (GaanaMusicService.s().m()) {
            this.Q.setSecondaryProgress(0);
            return;
        }
        this.Q.setMax(fVar.u());
        this.Q.setSecondaryProgress((int) ((0.01d * ((double) i)) * ((double) fVar.u())));
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        if (this.o != null && this.o.isShowing()) {
            this.o.dismiss();
            this.o = null;
        }
        super.onPause();
    }

    public void onStop() {
        this.as.removeCallbacks(this.at);
        super.onStop();
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.T != null) {
            this.T.dismiss();
            this.T = null;
        }
        if (this.aq != null) {
            this.aq.removeMessages(1000);
            this.aq.removeMessages(1001);
            this.aq.removeMessages(1002);
            this.aq.removeMessages(1003);
            this.aq.removeMessages(PointerIconCompat.TYPE_WAIT);
            this.aq.removeMessages(1005);
            this.aq.removeMessages(PointerIconCompat.TYPE_CELL);
            this.aq.removeMessages(PointerIconCompat.TYPE_CROSSHAIR);
        }
    }

    public void onDestroyView() {
        if (o.a("LISTENER_KEY_PLAYER_ACTIVITY") == this) {
            this.k.removeCallbacksAndMessages(null);
            if (this.X && this.Y) {
                l();
            }
            o.b("LISTENER_KEY_PLAYER_ACTIVITY");
            o.d("LISTENER_KEY_PLAYER_ACTIVITY");
        }
        this.as.removeCallbacks(this.at);
        if (!(this.J == null || this.J.getParent() == null)) {
            ((ViewGroup) this.J.getParent()).removeView(this.J);
        }
        this.J = null;
        super.onDestroyView();
    }

    private void a(PlayerTrack playerTrack) {
        this.mAppState.setPlayerStatus(true);
        String subtitleText = getSubtitleText(getPlayingTrack().getAlbumTitle(), getPlayingTrack().getArtistNames());
        boolean isParentalWarningEnabled = getPlayingTrack().isParentalWarningEnabled();
        CharSequence trim = subtitleText.trim();
        if (Constants.aG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.m.getString(R.string.CASTING_TO));
            stringBuilder.append(Constants.aH);
            trim = stringBuilder.toString();
        }
        this.y.setText(getPlayingTrack().getName());
        this.z.setText(trim);
        this.ak.setText(getPlayingTrack().getName());
        this.al.setSelected(true);
        this.al.setText(trim);
        int i = Constants.l ? R.drawable.vector_ic_explicit_content_white : R.drawable.vector_ic_explicit_content;
        if (isParentalWarningEnabled) {
            this.al.setCompoundDrawablesWithIntrinsicBounds(this.m.getResources().getDrawable(i), null, null, null);
        } else {
            this.al.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        if (this.ai != null && this.ah == 0) {
            this.ai.changeDownlaodButtonIcon(getPlayingTrack(), (ImageView) this.p.findViewById(R.id.queue_panel_download_button));
        }
        if (getPlayingTrack().isLocalMedia()) {
            Toolbar toolbar = (Toolbar) this.J.findViewById(R.id.toolbar);
            if (toolbar != null) {
                Menu menu = toolbar.getMenu();
                if (menu != null) {
                    menu.findItem(R.id.menu_add_to_playlist).setVisible(false);
                }
            }
        }
        this.Z.notifyDataSetChanged();
        i = PlayerManager.a(GaanaApplication.getContext()).s();
        if (i > -1 && i < PlayerManager.a(GaanaApplication.getContext()).n().size()) {
            this.aa.scrollToPosition(i);
        }
        D();
        q();
        b(this.mCurrentTrack.b());
        if (this.mCurrentTrack != null) {
            a((ImageView) this.J.findViewById(R.id.queue_panel_img_animation), getPlayingTrack());
        }
    }

    private void q() {
        if (this.mPlayerManager != null && this.mPlayerManager.m() == PlayerType.GAANA) {
            Track F = this.mPlayerManager.F();
            if (F == null) {
                if (this.mPlayerManager.i() != null) {
                    F = this.mPlayerManager.i().b();
                } else {
                    return;
                }
            }
            Toolbar toolbar = (Toolbar) this.J.findViewById(R.id.toolbar);
            if (toolbar != null) {
                Menu menu = toolbar.getMenu();
                if (menu != null) {
                    MenuItem findItem = menu.findItem(R.id.menu_add_to_playlist);
                    ImageView imageView = (ImageView) findItem.getActionView();
                    if (F.isLocalMedia()) {
                        findItem.setVisible(false);
                        return;
                    }
                    findItem.setVisible(true);
                    if (F.isFavorite().booleanValue()) {
                        imageView.setImageResource(R.drawable.vector_more_option_favorited);
                    } else {
                        TypedArray obtainStyledAttributes = this.m.obtainStyledAttributes(R.styleable.VectorDrawables);
                        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(49, -1)));
                        obtainStyledAttributes.recycle();
                    }
                }
            }
        }
    }

    private void r() {
        this.W.setBackgroundColor(0);
        this.ac = this.p.findViewById(R.id.queue_panel_button_save);
        this.ab = this.p.findViewById(R.id.ll_queue_panel_header_text);
        this.ab.setOnClickListener(this);
        this.W.setBackgroundDrawable(new ColorDrawable(0));
        this.W.setPanelSlideListener(new QueueSlidingUpPanelLayout.b() {
            public void a(View view, float f) {
                double d = (double) f;
                if (d <= 0.5d) {
                    if (PlayerFragment.this.ab.getVisibility() != 0) {
                        PlayerFragment.this.an = false;
                        ((ImageView) PlayerFragment.this.J.findViewById(R.id.queue_panel_img_animation)).setVisibility(0);
                        PlayerFragment.this.ab.setVisibility(0);
                        PlayerFragment.this.ac.setVisibility(8);
                        PlayerFragment.this.p.findViewById(R.id.queue_panel_button_save).setVisibility(4);
                        PlayerFragment.this.p.findViewById(R.id.queue_panel_button_clear).setVisibility(4);
                        PlayerFragment.this.p.findViewById(R.id.queue_panel_cheveron_view).setVisibility(4);
                        PlayerFragment.this.p.findViewById(R.id.queue_panel_bottom_options).setVisibility(4);
                        PlayerFragment.this.p.findViewById(R.id.queue_panel_button_save).setOnClickListener(null);
                        PlayerFragment.this.p.findViewById(R.id.queue_panel_button_clear).setOnClickListener(null);
                        PlayerFragment.this.p.findViewById(R.id.queue_panel_cheveron_view).setOnClickListener(null);
                        PlayerFragment.this.p.findViewById(R.id.queue_panel_bottom_options).setOnClickListener(null);
                        PlayerFragment.this.p.findViewById(R.id.queue_panel_bottom_options).setVisibility(8);
                        PlayerFragment.this.V.setVisibility(0);
                        PlayerFragment.this.P = (ProgressBar) PlayerFragment.this.J.findViewById(R.id.queue_panel_bottom_progressBar);
                        PlayerFragment.this.aj = (ProgressBar) PlayerFragment.this.J.findViewById(R.id.queue_panel_bottom_progressBar2);
                        PlayerFragment.this.aj.setVisibility(8);
                        if (PlayerFragment.this.ai == null) {
                            PlayerFragment.this.ai = new DownloadClickAnimation(PlayerFragment.this.m, PlayerFragment.this, PlayerFragment.this.V, PlayerFragment.this.getPlayingTrack(), PlayerFragment.this.J);
                        } else {
                            PlayerFragment.this.ai.changeDownlaodButtonIcon(PlayerFragment.this.getPlayingTrack(), PlayerFragment.this.V);
                        }
                        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(PlayerFragment.this.getPlayingTrack().getBusinessObjId()));
                        if (GaanaMusicService.s().m()) {
                            if (e == DownloadStatus.DOWNLOADING) {
                                PlayerFragment.this.P.setVisibility(8);
                                PlayerFragment.this.V.setVisibility(8);
                            } else {
                                PlayerFragment.this.V.setVisibility(8);
                            }
                        } else if (e != DownloadStatus.DOWNLOADING) {
                            PlayerFragment.this.P.setVisibility(8);
                            PlayerFragment.this.V.setVisibility(0);
                        }
                        PlayerFragment.this.J.findViewById(R.id.queue_panel_bottom_button).setVisibility(8);
                    }
                    PlayerFragment.this.T.stopQueueAnimation();
                } else if (PlayerFragment.this.ac.getVisibility() != 0) {
                    PlayerFragment.this.an = true;
                    PlayerFragment.this.setGAScreenName("PlayerQueue", "PlayerQueue");
                    ((ImageView) PlayerFragment.this.p.findViewById(R.id.queue_panel_img_animation)).setVisibility(4);
                    PlayerFragment.this.ac.setVisibility(0);
                    PlayerFragment.this.p.findViewById(R.id.queue_panel_button_save).setVisibility(0);
                    PlayerFragment.this.p.findViewById(R.id.queue_panel_button_clear).setVisibility(0);
                    PlayerFragment.this.p.findViewById(R.id.queue_panel_button_save).setOnClickListener(PlayerFragment.this);
                    PlayerFragment.this.p.findViewById(R.id.queue_panel_button_clear).setOnClickListener(PlayerFragment.this);
                    PlayerFragment.this.p.findViewById(R.id.queue_panel_cheveron_view).setVisibility(0);
                    PlayerFragment.this.p.findViewById(R.id.queue_panel_cheveron_view).setOnClickListener(PlayerFragment.this);
                    PlayerFragment.this.p.findViewById(R.id.queue_panel_bottom_options).setOnClickListener(PlayerFragment.this);
                    PlayerFragment.this.p.findViewById(R.id.queue_panel_bottom_options).setVisibility(0);
                    PlayerFragment.this.V.setVisibility(0);
                    PlayerFragment.this.U = (ImageView) PlayerFragment.this.J.findViewById(R.id.queue_panel_bottom_button);
                    PlayerFragment.this.P = (ProgressBar) PlayerFragment.this.J.findViewById(R.id.queue_panel_bottom_progressBar);
                    PlayerFragment.this.P.setVisibility(8);
                    PlayerFragment.this.aj = (ProgressBar) PlayerFragment.this.J.findViewById(R.id.queue_panel_bottom_progressBar2);
                    PlayerFragment.this.U.setOnClickListener(PlayerFragment.this);
                    if (PlayerFragment.this.ai == null) {
                        PlayerFragment.this.ai = new DownloadClickAnimation(PlayerFragment.this.m, PlayerFragment.this, PlayerFragment.this.V, PlayerFragment.this.getPlayingTrack(), PlayerFragment.this.J);
                    } else {
                        PlayerFragment.this.ai.changeDownlaodButtonIcon(PlayerFragment.this.getPlayingTrack(), PlayerFragment.this.V);
                    }
                    if (GaanaMusicService.s().m()) {
                        PlayerFragment.this.aj.setVisibility(0);
                        PlayerFragment.this.U.setVisibility(8);
                    } else if (GaanaMusicService.t()) {
                        PlayerFragment.this.aj.setVisibility(8);
                        PlayerFragment.this.U.setVisibility(0);
                        PlayerFragment.this.U.setImageDrawable(PlayerFragment.this.ad);
                    } else {
                        PlayerFragment.this.aj.setVisibility(8);
                        PlayerFragment.this.U.setVisibility(0);
                        PlayerFragment.this.U.setImageDrawable(PlayerFragment.this.N);
                    }
                    PlayerFragment.this.ab.setVisibility(4);
                    PlayerFragment.this.z();
                }
                if (d > 0.1d && !PlayerFragment.this.ae) {
                    PlayerFragment.this.ae = true;
                    ((LinearLayoutManager) PlayerFragment.this.T.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(PlayerFragment.this.mPlayerManager.s() + 1, 0);
                }
            }

            public void a(View view, int i, int i2) {
                PlayerFragment.this.ah = i2;
                if (i2 == 0) {
                    if (i2 != i) {
                        PlayerFragment.this.setGAScreenName("PlayerHomeScreen", "PlayerHomeScreen");
                    }
                    ((LinearLayoutManager) PlayerFragment.this.T.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(PlayerFragment.this.mPlayerManager.s() + 1, 0);
                    PlayerFragment.this.ae = false;
                }
            }
        });
        View view = this.T.getView(this.m, this.mPlayerManager.n(), this, this.p);
        this.T.getPlayerQueueView().setVisibility(0);
        this.T.getRecyclerView().setVisibility(0);
        view.setClickable(true);
        this.W.setScrollingView(this.T.getRecyclerView());
    }

    private void s() {
        int u;
        try {
            u = GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
            u = 0;
        }
        this.Q.setMax(u);
        this.Q.setSecondaryProgress(0);
        this.Q.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (PlayerFragment.this.f) {
                    Util.d(PlayerFragment.this.i - ((long) PlayerFragment.this.e));
                }
                u.a().b("Player", "Seekbar Moved");
                o.a(PlayerFragment.this.getContext(), PlayerFragment.this.Q.getProgress());
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                PlayerFragment.this.i = (long) PlayerFragment.this.e;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                PlayerFragment.this.f = z;
                r0 = new Object[2];
                long j = (long) i;
                r0[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) / 60);
                r0[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % 60);
                PlayerFragment.this.F.setText(String.format("%2d:%02d", r0));
                r15 = new Object[2];
                long u = (long) (GaanaMusicService.s().u() - i);
                r15[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) / 60);
                r15[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) % 60);
                PlayerFragment.this.H.setText(String.format("%2d:%02d", r15));
                PlayerFragment.this.e = i;
            }
        });
        v();
        if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
            this.L.setImageDrawable(this.s);
            this.U.setImageDrawable(this.ad);
            return;
        }
        this.L.setImageDrawable(this.u);
        this.U.setImageDrawable(this.N);
    }

    private void t() {
        int u;
        try {
            u = GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
            u = 0;
        }
        o.a(getContext(), this.h);
        this.Q.setProgress(this.h);
        this.Q.setMax(u);
        this.Q.setSecondaryProgress(this.h);
        this.Q.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (PlayerFragment.this.f) {
                    Util.d(PlayerFragment.this.i - ((long) PlayerFragment.this.e));
                }
                o.a(PlayerFragment.this.getContext(), PlayerFragment.this.Q.getProgress());
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                PlayerFragment.this.i = (long) PlayerFragment.this.e;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                PlayerFragment.this.f = z;
                r0 = new Object[2];
                long j = (long) i;
                r0[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) / 60);
                r0[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % 60);
                PlayerFragment.this.F.setText(String.format("%2d:%02d", r0));
                r15 = new Object[2];
                long u = (long) (GaanaMusicService.s().u() - i);
                r15[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) / 60);
                r15[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) % 60);
                PlayerFragment.this.H.setText(String.format("%2d:%02d", r15));
                PlayerFragment.this.e = i;
            }
        });
        v();
        if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
            this.L.setImageDrawable(this.s);
            this.U.setImageDrawable(this.ad);
            return;
        }
        this.L.setImageDrawable(this.u);
        this.U.setImageDrawable(this.N);
    }

    private void u() {
        this.Q.setProgress(0);
        this.Q.setSecondaryProgress(0);
        this.Q.setMax(0);
        this.F.setText("0:00");
        this.H.setText("0:00");
    }

    private void v() {
        int v;
        if (!(GaanaMusicService.s().m() || ad.a(this.m).o().booleanValue())) {
            int u;
            try {
                v = GaanaMusicService.s().v();
                u = GaanaMusicService.s().u();
            } catch (IllegalStateException unused) {
                v = 0;
                u = v;
            }
            int i = u - v;
            this.Q.setProgress(v);
            this.Q.setMax(u);
            this.Q.setSelected(false);
            this.Q.setSecondaryProgress((int) ((0.01d * ((double) GaanaMusicService.s().t())) * ((double) GaanaMusicService.s().u())));
            r5 = new Object[2];
            long j = (long) v;
            r5[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) / 60);
            r5[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % 60);
            CharSequence format = String.format("%2d:%02d", r5);
            r4 = new Object[2];
            long j2 = (long) i;
            r4[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j2) / 60);
            r4[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j2) % 60);
            String format2 = String.format("%2d:%02d", r4);
            if (v > 15958442) {
                format = "0:00";
                u();
            }
            this.F.setText(format);
            if (i >= 0) {
                this.H.setText(format2);
            }
            if (!((format2.equalsIgnoreCase(" 0:00") && this.mPlayerManager.t() && this.mPlayerManager.v()) || !GaanaMusicService.t() || GaanaMusicService.s().m())) {
                AnonymousClass2 anonymousClass2 = new Runnable() {
                    public void run() {
                        PlayerFragment.this.v();
                    }
                };
                this.k.removeCallbacksAndMessages(null);
                this.k.postDelayed(anonymousClass2, 1000);
            }
        }
    }

    private void b(boolean z) {
        if (((GaanaActivity) this.m).isPlayerExpanded()) {
            g();
        }
        o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.ar);
        this.mCurrentTrack = this.mPlayerManager.a(PlaySequenceType.CURRENT);
        a(this.mCurrentTrack);
        if (!this.g) {
            u();
        }
        this.U.setImageDrawable(this.ad);
        this.L.setImageDrawable(this.s);
        this.L.setVisibility(4);
        this.U.setVisibility(8);
        this.O.setVisibility(0);
        this.mPlayerStates = PlayerStates.LOADING;
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(getPlayingTrack().getBusinessObjId()));
        b();
        if (z) {
            o();
            a(this.A, false);
        }
        if (this.ah != 0) {
            this.aj.setVisibility(0);
            if (e == DownloadStatus.DOWNLOADING) {
                this.V.setVisibility(8);
            } else {
                this.V.setVisibility(0);
            }
        } else if (e == DownloadStatus.DOWNLOADING) {
            this.P.setVisibility(8);
        } else {
            ((ProgressBar) this.J.findViewById(R.id.queue_panel_bottom_progressBar)).setVisibility(0);
            ((ImageView) this.J.findViewById(R.id.queue_panel_download_button)).setVisibility(4);
        }
    }

    public void b() {
        if (this.T != null) {
            this.T.notifyDataSetChanged();
        }
        if (this.Z != null) {
            this.Z.notifyDataSetChanged();
        }
        if (this.mCurrentTrack != null) {
            a((ImageView) this.J.findViewById(R.id.queue_panel_img_animation), getPlayingTrack());
        }
        if (this.mCurrentTrack != null && this.ai != null) {
            this.ai.changeDownlaodButtonIcon(getPlayingTrack(), (ImageView) this.J.findViewById(R.id.queue_panel_download_button));
        }
    }

    public void refreshPlayerStatus() {
        if (isAdded()) {
            if (this.o != null && this.o.isShowing()) {
                this.o.dismiss();
                this.o = null;
            }
            if (this.T != null) {
                this.T.notifyDataSetChanged();
            }
            if (!(this.mCurrentTrack == null || this.ai == null)) {
                this.ai.changeDownlaodButtonIcon(getPlayingTrack(), (ImageView) this.p.findViewById(R.id.queue_panel_download_button));
                q();
            }
        }
    }

    private void w() {
        this.L.setImageDrawable(this.u);
        this.L.setVisibility(0);
        this.U.setImageDrawable(this.N);
        if (this.ah != 0) {
            this.U.setVisibility(0);
        }
        this.P.setVisibility(8);
        this.aj.setVisibility(8);
        this.O.setVisibility(8);
        this.mPlayerStates = PlayerStates.PAUSED;
        b();
    }

    private void x() {
        this.L.setImageDrawable(this.s);
        this.L.setVisibility(0);
        this.U.setImageDrawable(this.ad);
        if (this.ah != 0) {
            this.U.setVisibility(0);
        }
        this.P.setVisibility(8);
        this.aj.setVisibility(8);
        this.O.setVisibility(8);
        if (!GaanaMusicService.s().m()) {
            if (this.g) {
                t();
            } else {
                s();
            }
        }
        this.mPlayerStates = PlayerStates.PLAYING;
        b();
    }

    private void y() {
        if (this.L != null) {
            this.L.setImageDrawable(this.u);
            this.L.setVisibility(0);
            this.U.setImageDrawable(this.N);
            if (this.an) {
                this.U.setVisibility(0);
            } else {
                this.U.setVisibility(8);
            }
            this.P.setVisibility(8);
            this.aj.setVisibility(8);
            this.O.setVisibility(8);
            this.mPlayerStates = PlayerStates.STOPPED;
            b();
        }
    }

    private void a(boolean z, boolean z2) {
        this.L.setImageDrawable(this.s);
        this.U.setImageDrawable(this.ad);
        if (z2) {
            a(z, PlayerCommands.PLAY_PREVIOUS);
            return;
        }
        this.k.removeCallbacksAndMessages(null);
        u();
    }

    private void b(boolean z, boolean z2) {
        if (this.o != null && this.o.isShowing()) {
            this.o.dismiss();
            this.o = null;
        }
        this.L.setImageDrawable(this.s);
        this.U.setImageDrawable(this.ad);
        if (z2) {
            a(z, PlayerCommands.PLAY_NEXT);
            b();
            return;
        }
        this.k.removeCallbacksAndMessages(null);
        u();
    }

    public void a(View view) {
        int i = false;
        Track b;
        switch (view.getId()) {
            case R.id.downloadTrackPlayer /*2131296970*/:
                a(getPlayingTrack());
                break;
            case R.id.equalizerIconRadio /*2131297058*/:
                ((BaseActivity) this.m).sendGAEvent("Player", "Equalizer", "Click");
                Util.t(this.m);
                break;
            case R.id.ll_queue_panel_header_text /*2131297620*/:
            case R.id.queue_panel_cheveron_view /*2131298146*/:
                if (this.W != null) {
                    z();
                    if (this.W.f()) {
                        this.W.h();
                    } else {
                        this.W.g();
                    }
                    d.a().a("PREFERENCE_KEY_QUEUE_ANIMATION_INITIATED", true, false);
                    break;
                }
                break;
            case R.id.lyricsButton /*2131297649*/:
            case R.id.lyricsButtonText /*2131297650*/:
                A();
                break;
            case R.id.playerBtnNext /*2131297993*/:
            case R.id.playerBtnNextRadio /*2131297994*/:
                if (PlayerManager.a(this.m).x() == null || PlayerManager.a(this.m).x().b().isLocalMedia() || !Constants.aa || Constants.h > 0) {
                    if (this.X) {
                        l();
                        PlayerManager.a(this.m).j(false);
                    }
                    ((BaseActivity) this.m).sendGAEvent("Player", "Skip", "Player - Skip - Song");
                    o.f(getContext());
                    break;
                }
                u.a().a("Shuffle Product", "Gaana+ popup", "Player Next");
                Util.a(this.m, BLOCK_ACTION.SKIP);
                return;
                break;
            case R.id.playerBtnPrev /*2131297996*/:
                if (PlayerManager.a(this.m).w() == null || PlayerManager.a(this.m).w().b().isLocalMedia() || !Constants.aa || Constants.h > 0) {
                    if (this.X) {
                        l();
                        PlayerManager.a(this.m).j(false);
                    }
                    ((BaseActivity) this.m).sendGAEvent("Player", "Skip", "Player - Skip - Song");
                    o.e(getContext());
                    break;
                }
                u.a().a("Shuffle Product", "Gaana+ popup", "Player Previous");
                Util.a(this.m, BLOCK_ACTION.SKIP);
                return;
            case R.id.playerBtnRepeat /*2131297997*/:
                a(view, true);
                break;
            case R.id.playerBtnShuffle /*2131297998*/:
                if (!Constants.ab) {
                    ((BaseActivity) this.m).sendGAEvent("Player", "Shuffle", "Player - Shuffle - Song");
                    if (PlayerManager.a(this.m).E() != null) {
                        PlayerManager.a(this.m).j(false);
                    }
                    c(view);
                    if (this.Z != null) {
                        this.Z.notifyDataSetChanged();
                    }
                    if (this.T != null) {
                        this.T.updateArrayList(PlayerManager.a(GaanaApplication.getContext()).n());
                        break;
                    }
                }
                Util.a(this.m, BLOCK_ACTION.SHUFFLE);
                return;
                break;
            case R.id.playerButton /*2131297999*/:
            case R.id.playerButtonRadio /*2131298001*/:
            case R.id.queue_panel_bottom_button /*2131298140*/:
                if (this.X) {
                    l();
                    PlayerManager.a(this.m).j(false);
                }
                if (GaanaMusicService.t() || GaanaMusicService.s().m()) {
                    u.a().b("Player", "Pause");
                } else {
                    u.a().b("Player", "Play");
                }
                if (ad.a(this.m).o().booleanValue()) {
                    if (!GaanaMusicService.t()) {
                        c();
                        break;
                    } else {
                        o.d(getContext());
                        break;
                    }
                }
                c();
                break;
                break;
            case R.id.playerqueueLL /*2131298037*/:
                d();
                break;
            case R.id.qualityText /*2131298132*/:
            case R.id.streamingList /*2131298500*/:
            case R.id.streamingListLayoutRadio /*2131298501*/:
                if (this.X) {
                    l();
                    PlayerManager.a(this.m).j(false);
                }
                b(view);
                break;
            case R.id.queue_panel_bottom_options /*2131298141*/:
                b = this.mCurrentTrack.b();
                if (!(b == null || b.getBusinessObjType() == null || ad.a(this.m).p().booleanValue())) {
                    PopupWindowView instance = PopupWindowView.getInstance(this.m, null);
                    instance.setDownloadPopupListener(new DownloadPopupListener() {
                        public void onPopupClicked(String str, BusinessObject businessObject) {
                            if (DownloadManager.c().e(Integer.parseInt(str)) == DownloadStatus.DOWNLOADED) {
                                PlayerFragment.this.b(businessObject);
                            } else {
                                PlayerFragment.this.a(PlayerFragment.this.getPlayingTrack());
                            }
                        }
                    });
                    instance.contextPopupWindow(b, true, new a() {
                        public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
                            PlayerFragment.this.q();
                        }
                    }, true);
                    break;
                }
            case R.id.queue_panel_button_clear /*2131298144*/:
                if (PlayerManager.a(this.m).m() != PlayerType.GAANA) {
                    b = PlayerManager.a(this.m).i().b();
                    if (!(b == null || b.getBusinessObjType() == null || ad.a(this.m).p().booleanValue())) {
                        PopupWindowView.getInstance(this.m, null).contextPopupWindow(b, true, false);
                        break;
                    }
                }
                B();
                break;
            case R.id.queue_panel_button_save /*2131298145*/:
                u a = u.a();
                String str = "Add to Playlist";
                String str2 = "Player Screen";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Queue Saved ");
                if (PlayerManager.a(GaanaApplication.getContext()).n() != null) {
                    i = PlayerManager.a(GaanaApplication.getContext()).n().size();
                }
                stringBuilder.append(i);
                a.a(str, str2, stringBuilder.toString());
                C();
                break;
        }
    }

    private void z() {
        if (this.d != null) {
            this.d.cancel();
        }
        if (this.c != null) {
            this.c.cancel();
        }
        this.p.clearAnimation();
        if (!d.a().b("PREFERENCE_KEY_SLIDE_LEFT_INITIATED", false, false)) {
            this.T.setSwipeCoachmarkAnimation();
        }
    }

    private void A() {
        u.a().b("Player", "Lyrics");
        if (!Util.j(this.m)) {
            aj.a().a(this.m, this.m.getString(R.string.error_msg_no_connection));
        } else if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.m).displayFeatureNotAvailableOfflineDialog(this.m.getString(R.string.this_feature));
        } else {
            PlayerTrack i = PlayerManager.a(this.m).i();
            if (i != null && i.b() != null) {
                String lyricsUrl = i.b().getLyricsUrl();
                if (!TextUtils.isEmpty(lyricsUrl)) {
                    Intent intent = new Intent(this.m, WebViewActivity.class);
                    intent.putExtra("EXTRA_WEBVIEW_URL", lyricsUrl);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                    intent.putExtra("title", "Lyrics");
                    this.m.startActivity(intent);
                }
            }
        }
    }

    private void B() {
        u.a().a("PlayerQueue", "Clear queue", "PlayerQueue - Clear queue");
        CustomDialogView customDialogView = new CustomDialogView(this.m, getString(R.string.player_and_queue_clear), new OnButtonClickListener() {
            public void onNegativeButtonClick() {
            }

            public void onPositiveButtonClick() {
                PlayerFragment.this.mPlayerManager.C();
                o.d(PlayerFragment.this.m);
            }
        });
        customDialogView.getPositiveButton().setText(getString(R.string.continue_button));
        customDialogView.show();
    }

    private void C() {
        u.a().a("PlayerQueue", "Save Queue", "PlayerQueue - Save Queue");
        ArrayList n = PlayerManager.a(this.m).n();
        if (n != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = n.iterator();
            while (it.hasNext()) {
                arrayList.add(((PlayerTrack) it.next()).a(true));
            }
            BusinessObject businessObject = new BusinessObject();
            businessObject.setArrList(arrayList);
            if (this.mAppState.getCurrentUser().getLoginStatus()) {
                ap.a().a(this.m, arrayList, false);
                return;
            } else {
                af.a(this.m, null).a((int) R.id.addToPlaylistMenu, businessObject, this.m.getResources().getString(R.string.login_bottom_sheet_playlist_text));
                return;
            }
        }
        aj.a().a(this.m, this.m.getString(R.string.no_songs_to_add));
    }

    public void c() {
        if (GaanaMusicService.t() || GaanaMusicService.s().m() || GaanaMusicService.s().l()) {
            this.L.setImageDrawable(this.s);
            this.U.setImageDrawable(this.ad);
            o.b(this.m, PauseReasons.MEDIA_BUTTON_TAP);
            return;
        }
        this.L.setImageDrawable(this.u);
        this.U.setImageDrawable(this.N);
        o.a(this.m);
        if (ad.a(this.m).o().booleanValue()) {
            ad.a(this.m).j();
        }
    }

    private void b(View view) {
        String string;
        String str;
        final LayoutInflater layoutInflater = (LayoutInflater) this.m.getSystemService("layout_inflater");
        View inflate = layoutInflater.inflate(R.layout.stream_quality_layout, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.m);
        bottomSheetDialog.setContentView(inflate);
        ListView listView = (ListView) inflate.findViewById(R.id.streaming_list);
        if (ap.a().s()) {
            string = this.m.getResources().getString(R.string.stream_quality_hd);
            str = "High Definition";
        } else {
            string = this.m.getResources().getString(R.string.stream_quality_hd_gaanaplus);
            str = "High Definition (Gaana+ only)";
        }
        final String[] strArr = new String[]{this.m.getResources().getString(R.string.stream_quality_auto), string, this.m.getResources().getString(R.string.stream_quality_high), this.m.getResources().getString(R.string.stream_quality_med), this.m.getResources().getString(R.string.stream_quality_low)};
        String[] strArr2 = new String[]{"Auto", str, "High", "Medium", "Low"};
        final int[] iArr = new int[]{10004, 10003, 10002, 10001, 10000};
        listView.setSelector(this.v);
        listView.setAdapter(new BaseAdapter() {
            public Object getItem(int i) {
                return null;
            }

            public long getItemId(int i) {
                return 0;
            }

            public int getCount() {
                return strArr.length;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = layoutInflater.inflate(R.layout.stream_quality_item_view, viewGroup, false);
                }
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_check);
                TextView textView = (TextView) view.findViewById(R.id.tv_quality_name);
                int b = d.a().b("PREFERENCE_KEY_STREAMING_QUALITY", Constants.s(), false);
                textView.setText(strArr[i]);
                if (b == iArr[i]) {
                    textView.setTextColor(Color.parseColor("#E2322A"));
                } else {
                    imageView.setVisibility(4);
                    textView.setTextColor(PlayerFragment.this.I.data);
                }
                return view;
            }
        });
        final BottomSheetDialog bottomSheetDialog2 = bottomSheetDialog;
        final String[] strArr3 = strArr2;
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                d a = d.a();
                if (PlayerFragment.this.mAppState.isAppInDataSaveMode()) {
                    ((BaseActivity) PlayerFragment.this.m).displayFeatureNotAvailableDataSaveModeDialog(i, -1);
                    bottomSheetDialog2.dismiss();
                    return;
                }
                aj a2;
                Context x;
                StringBuilder stringBuilder;
                if (i == 0) {
                    if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                        bottomSheetDialog2.dismiss();
                        return;
                    }
                    a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                    aj.a().a(PlayerFragment.this.m, PlayerFragment.this.m.getString(R.string.adjusting_sound_quality));
                    bottomSheetDialog2.dismiss();
                    PlayerFragment.this.E();
                    PlayerFragment.this.g = true;
                    PlayerFragment.this.h = PlayerFragment.this.Q.getProgress();
                    o.b(PlayerFragment.this.getContext(), 1);
                    u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                } else if (i == 1) {
                    if (!ap.a().s()) {
                        u.a().a("Mini Player", "Set Streaming Quality", "Trial HD (Gaana+ only)");
                        bottomSheetDialog2.dismiss();
                        Util.a(PlayerFragment.this.m, PlayerFragment.this.m.getResources().getString(R.string.subscribe_gaanaplus_hdq_msg), "HDQuality");
                    } else if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                        bottomSheetDialog2.dismiss();
                    } else {
                        a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                        a2 = aj.a();
                        x = PlayerFragment.this.m;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(PlayerFragment.this.m.getString(R.string.changing_sound_quality));
                        stringBuilder.append(strArr[i]);
                        a2.a(x, stringBuilder.toString());
                        bottomSheetDialog2.dismiss();
                        PlayerFragment.this.E();
                        PlayerFragment.this.g = true;
                        PlayerFragment.this.h = PlayerFragment.this.Q.getProgress();
                        o.b(PlayerFragment.this.getContext(), 1);
                        u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                    }
                } else if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                    bottomSheetDialog2.dismiss();
                } else {
                    a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                    a2 = aj.a();
                    x = PlayerFragment.this.m;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(PlayerFragment.this.m.getString(R.string.changing_sound_quality));
                    stringBuilder.append(strArr[i]);
                    a2.a(x, stringBuilder.toString());
                    bottomSheetDialog2.dismiss();
                    PlayerFragment.this.E();
                    PlayerFragment.this.g = true;
                    PlayerFragment.this.h = PlayerFragment.this.Q.getProgress();
                    o.b(PlayerFragment.this.getContext(), 1);
                    u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                }
            }
        });
        bottomSheetDialog.show();
    }

    private void D() {
        int i = (ad.a(this.m).o().booleanValue() || GaanaApplication.getInstance().isAppInOfflineMode() || !Util.j(this.m)) ? true : 0;
        if (!(this.mCurrentTrack == null || this.mCurrentTrack.a(true) == null)) {
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(this.mCurrentTrack.a(true).getBusinessObjId()));
            if (this.mCurrentTrack.a(true).isLocalMedia() || ((ap.a().o() || DownloadManager.c().j(this.mCurrentTrack.a(true).getBusinessObjId()).booleanValue()) && e != null && e == DownloadStatus.DOWNLOADED)) {
                i = true;
            }
        }
        if (i != 0) {
            this.R.setVisibility(4);
            this.S.setVisibility(4);
            return;
        }
        this.R.setVisibility(0);
        this.S.setVisibility(0);
        CharSequence charSequence = "";
        GaanaApplication gaanaApplication;
        switch (d.a().b("PREFERENCE_KEY_STREAMING_QUALITY", Constants.s(), false)) {
            case 10000:
                charSequence = this.m.getString(R.string.low);
                break;
            case 10001:
                gaanaApplication = this.mAppState;
                if (!GaanaApplication.getLanguage(this.m).equalsIgnoreCase("English")) {
                    charSequence = this.m.getString(R.string.medium);
                    break;
                } else {
                    charSequence = this.m.getString(R.string.med);
                    break;
                }
            case 10002:
                charSequence = this.m.getString(R.string.high);
                break;
            case 10003:
                gaanaApplication = this.mAppState;
                if (!GaanaApplication.getLanguage(this.m).equalsIgnoreCase("English")) {
                    charSequence = this.m.getString(R.string.high_defination);
                    break;
                } else {
                    charSequence = this.m.getString(R.string.hd);
                    break;
                }
            case 10004:
                charSequence = this.m.getString(R.string.auto);
                break;
        }
        this.S.setText(charSequence);
    }

    public void d() {
        this.n = PopupWindowView.getInstance(this.m, ((GaanaActivity) this.m).getCurrentFragment());
        this.n.populatePlayerQueue(getPlayingTrack(), this.T);
    }

    /* Access modifiers changed, original: protected */
    public void a(final BusinessObject businessObject) {
        if (!businessObject.isLocalMedia()) {
            if (ap.a().o()) {
                af.a(this.m, null).a((int) R.id.downloadMenu, businessObject);
            } else {
                ((BaseActivity) this.m).hideProgressDialog();
                final BaseGaanaFragment currentFragment = ((GaanaActivity) this.m).getCurrentFragment();
                if (!((currentFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) currentFragment).a() == 1)) {
                    Util.b(this.m, null, new as() {
                        public void onTrialSuccess() {
                            af.a(PlayerFragment.this.m, null).a((int) R.id.downloadMenu, businessObject);
                            currentFragment.showSnackbartoOpenMyMusic();
                            ((GaanaActivity) PlayerFragment.this.m).updateSideBar();
                        }
                    });
                }
            }
        }
    }

    private void a(View view, boolean z) {
        int parseInt = Integer.parseInt(view.getTag().toString());
        parseInt = parseInt == 2 ? 0 : parseInt + 1;
        if (parseInt == 0) {
            this.A.setImageDrawable(this.q);
        } else {
            this.A.setImageResource(this.x[parseInt]);
        }
        this.A.setTag(Integer.valueOf(parseInt));
        this.mPlayerManager.b(false);
        this.mPlayerManager.c(false);
        String str = "";
        switch (RepeatModes.values()[parseInt]) {
            case SINGLE:
                this.mPlayerManager.b(true);
                o.c(getContext());
                str = "One";
                break;
            case ALL:
                this.mPlayerManager.c(true);
                o.c(getContext());
                str = "On";
                break;
            case NO_REPEAT:
                o.b(getContext());
                str = "Off";
                break;
        }
        if (z) {
            u.a().a("Player", "Repeat", str);
        }
        d.a().a("PREFERENCE_KEY_REPEAT_STATUS", parseInt, true);
    }

    private void c(View view) {
        int parseInt = Integer.parseInt(view.getTag().toString());
        parseInt = parseInt == 1 ? 0 : parseInt + 1;
        if (parseInt == 0) {
            this.B.setImageDrawable(this.r);
        } else {
            this.B.setImageResource(this.w[parseInt]);
        }
        this.B.setTag(Integer.valueOf(parseInt));
        PlayerManager.a(getContext()).a(false);
        switch (parseInt) {
            case 0:
                PlayerManager.a(getContext()).a(false, null);
                break;
            case 1:
                PlayerManager.a(getContext()).a(true, null);
                break;
        }
        if (this.X) {
            p();
            if (this.T != null && this.mPlayerManager.m() == PlayerType.GAANA) {
                this.T.getRecyclerView().setVisibility(0);
                this.T.updateArrayList(PlayerManager.a(this.m).n());
                this.Z.updateAndNotifyArrayList(PlayerManager.a(this.m).n());
                return;
            } else if (this.T != null && this.mPlayerManager.m() == PlayerType.GAANA_RADIO) {
                this.T.getRecyclerView().setVisibility(8);
                return;
            } else {
                return;
            }
        }
        a();
    }

    private void a(boolean z, PlayerCommands playerCommands) {
        switch (playerCommands) {
            case PLAY_NEXT:
                if (!z) {
                    this.mCurrentTrack = this.mPlayerManager.a(this.mPlayerManager.s());
                    this.k.removeCallbacksAndMessages(null);
                    u();
                    this.L.setImageDrawable(this.u);
                    this.U.setImageDrawable(this.N);
                    return;
                } else if (GaanaMusicService.s().l() || GaanaMusicService.s().k()) {
                    this.L.setImageDrawable(this.u);
                    this.U.setImageDrawable(this.N);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private void E() {
        this.mCurrentTrack = PlayerManager.a(this.m).i();
        m();
        if (this.mCurrentTrack != null) {
            a(this.mCurrentTrack);
        }
        b();
    }

    public Context getContext() {
        if (isAdded() && this.m == null) {
            this.m = getActivity();
        }
        return this.m;
    }

    public void onClick(View view) {
        a(view);
    }

    public void onListUpdated() {
        if (this.Z != null) {
            this.Z.notifyDataSetChanged();
        }
        if (this.T != null) {
            this.T.updateQueueStatus();
        }
        ((GaanaActivity) this.m).updateMiniPlayerList();
    }

    public PlayerStates e() {
        return this.mPlayerStates;
    }

    private void F() {
        this.O = (ProgressBar) this.J.findViewById(R.id.progressBarPlayer);
        this.D = (ImageView) this.J.findViewById(R.id.playerBtnNext);
        this.L = (ImageView) this.J.findViewById(R.id.playerButton);
        this.R = this.J.findViewById(R.id.streamingList);
        this.S = (TextView) this.J.findViewById(R.id.qualityText);
        this.F = (TextView) this.J.findViewById(R.id.tvPlayerStartTimer);
        this.D.setOnClickListener(this);
        this.L.setOnClickListener(this);
        this.R.setOnClickListener(this);
        this.S.setOnClickListener(this);
    }

    /* Access modifiers changed, original: protected */
    public void a(ImageView imageView, BusinessObject businessObject) {
        int i;
        int i2;
        if (Constants.l) {
            i = R.drawable.queue_equalizer_white;
            i2 = R.drawable.queue_equalizer_1;
        } else {
            i = R.drawable.queue_equalizer_normal_theme;
            i2 = R.drawable.queue_equalizer_black_1;
        }
        if (imageView != null) {
            PlayerTrack i3 = PlayerManager.a(this.m).i();
            Object entityId;
            if (businessObject instanceof Item) {
                entityId = ((Item) businessObject).getEntityId();
            } else {
                entityId = businessObject.getBusinessObjId();
            }
            if (i3 != null && i3.h().equals(entityId)) {
                if (e() == PlayerStates.PLAYING && GaanaMusicService.s().isPlaying()) {
                    AnimationDrawable animationDrawable = (AnimationDrawable) ContextCompat.getDrawable(this.m, i);
                    imageView.setImageDrawable(animationDrawable);
                    if (!this.W.e()) {
                        imageView.setVisibility(0);
                    }
                    animationDrawable.start();
                } else {
                    if (imageView.getAnimation() != null) {
                        imageView.getAnimation().cancel();
                    }
                    if (!this.W.e()) {
                        imageView.setVisibility(0);
                    }
                    imageView.setImageResource(i2);
                }
                if (!this.an && this.U != null) {
                    this.U.setVisibility(8);
                }
            } else if (imageView.getVisibility() == 0) {
                imageView.setVisibility(8);
                if (imageView.getAnimation() != null) {
                    imageView.getAnimation().cancel();
                }
                if (this.U != null) {
                    this.U.setVisibility(0);
                }
            }
        }
    }

    private void a(Track track) {
        if (track != null) {
            CharSequence trim = getSubtitleText(track.getAlbumTitle(), track.getArtistNames()).trim();
            if (Constants.aG) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.m.getString(R.string.CASTING_TO));
                stringBuilder.append(Constants.aH);
                trim = stringBuilder.toString();
            }
            if (!(this.am || this.y == null || this.z == null)) {
                this.y.setText(track.getName());
                this.z.setText(trim);
            }
            this.mPlayerManager.b(track);
        }
        if (TextUtils.isEmpty(this.ak.getText()) || TextUtils.isEmpty(this.al.getText())) {
            CharSequence trim2 = getSubtitleText(getPlayingTrack().getAlbumTitle(), getPlayingTrack().getArtistNames()).trim();
            if (Constants.aG) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(this.m.getString(R.string.CASTING_TO));
                stringBuilder2.append(Constants.aH);
                trim2 = stringBuilder2.toString();
            }
            this.ak.setText(getPlayingTrack().getName());
            this.al.setSelected(true);
            this.al.setText(trim2);
            if (getPlayingTrack() != null) {
                a((ImageView) this.J.findViewById(R.id.queue_panel_img_animation), getPlayingTrack());
            }
        }
    }

    private void b(Track track) {
        TypedArray obtainStyledAttributes = this.m.obtainStyledAttributes(R.styleable.VectorDrawables);
        if (TextUtils.isEmpty(track.getLyricsUrl())) {
            this.E.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(22, -1)));
            this.G.setAlpha(0.25f);
        } else {
            this.E.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(24, -1)));
            this.G.setAlpha(1.0f);
        }
        obtainStyledAttributes.recycle();
    }

    public void on_enque() {
        if (isAdded()) {
            o();
            a(this.A, false);
            this.Z.updateArrayList(PlayerManager.a(this.m).n());
            E();
        }
    }

    public void refreshList() {
        if (isAdded() && this.mCurrentTrack != null) {
            E();
            this.g = true;
            this.h = this.Q.getProgress();
            o.b(getContext(), 1);
        }
    }

    public void on_deque() {
        if (isAdded()) {
            if (PlayerManager.a(this.m).n() == null || PlayerManager.a(this.m).n().size() <= 0) {
                this.W.setPanelSlideListener(null);
                ((GaanaActivity) this.m).popBackStackImmediate();
                ((GaanaActivity) this.m).getSlidingPanelLayout().a(3);
            } else {
                this.Z.updateArrayList(PlayerManager.a(this.m).n());
                if (this.m != null) {
                    ((Activity) this.m).runOnUiThread(new Runnable() {
                        public void run() {
                            PlayerFragment.this.E();
                            if (PlayerManager.a(PlayerFragment.this.m).l() <= 0) {
                                PlayerFragment.this.a();
                            }
                        }
                    });
                }
            }
        }
    }

    public void onLiveRadioUpdate() {
        if (isAdded()) {
            a(PlayerManager.a(GaanaApplication.getContext()).i());
        }
    }

    public void refreshForFavorite() {
        q();
    }

    private void b(final BusinessObject businessObject) {
        final String businessObjId = businessObject.getBusinessObjId();
        new CustomDialogView(this.m, (int) R.string.dialog_deletdownload_text, new OnButtonClickListener() {
            public void onNegativeButtonClick() {
            }

            public void onPositiveButtonClick() {
                if ((businessObject instanceof Track) || (businessObject instanceof OfflineTrack)) {
                    DownloadManager.c().d(businessObject.getBusinessObjId());
                } else {
                    DownloadManager.c().p(Integer.parseInt(businessObjId));
                    DownloadManager.c().d(Integer.parseInt(businessObjId));
                }
                PlayerFragment.this.E();
            }
        }).show();
    }
}
