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
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.actionbar.PlayerMaterialActionBar;
import com.actionbar.PlayerMaterialActionBar.PlayerVersion;
import com.constants.Constants;
import com.constants.Constants.ErrorType;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.adapter.CardPagerAdapterV2;
import com.gaana.adapter.CardPagerAdapterV2.CardViewHolder;
import com.gaana.adapter.MusicQueueAdapterV2.IUpdatePlayer;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Tracks.Track;
import com.gaana.view.DiscreteScrollLayoutManager;
import com.gaana.view.DiscreteScrollView;
import com.gaana.view.DownloadClickAnimation;
import com.gaana.view.PlayerQueueViewV2;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.gaana.view.item.PopupWindowView;
import com.gaana.view.item.SimilarItemHorizontalScroll;
import com.gaana.view.item.TrackItemView;
import com.gaana.view.transform.ScaleTransformer;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.integralads.avid.library.inmobi.AvidBridge;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.library.controls.CrossFadeImageView;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.ADSTATUS;
import com.managers.ColombiaAdViewManager.b;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlaySequenceType;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.af;
import com.managers.aj;
import com.managers.an;
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
import com.services.l.ai;
import com.services.l.al;
import com.services.l.as;
import com.services.l.q;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;
import com.views.QueueSlidingUpPanelLayout;
import com.views.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class PlayerFragmentV2 extends BaseFragment implements OnClickListener, IUpdatePlayer, DownloadPopupListener, b, a, q {
    private ImageView A;
    private ImageView B;
    private ImageView C;
    private ImageView D;
    private ImageView E;
    private TextView F;
    private TextView G;
    private TextView H;
    private TextView I;
    private TypedValue J;
    private View K;
    private PlayerMaterialActionBar L;
    private ImageView M = null;
    private View N;
    private View O;
    private CrossFadeImageView P;
    private Drawable Q;
    private Drawable R;
    private ProgressBar S;
    private ProgressBar T;
    private SeekBar U;
    private View V;
    private ImageView W;
    private TextView X;
    private ImageView Y;
    private PlayerQueueViewV2 Z;
    int[] a = new int[]{R.attr.miniplayer_pause, R.attr.miniplayer_play};
    private LinearLayout aA;
    private int aB = -1;
    private TextView aC;
    private TextView aD;
    private boolean aE = false;
    private int aF = -1;
    private View aG;
    private boolean aH = false;
    private m aI = new m() {
        public void onInfo(f fVar, int i, int i2) {
        }

        public void onPrepared(f fVar) {
            if (!PlayerFragmentV2.this.isActivityDestroyed()) {
                PlayerFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (GaanaMusicService.s().isPlaying()) {
                            PlayerFragmentV2.this.mPlayerStates = PlayerStates.PLAYING;
                            PlayerFragmentV2.this.g();
                            PlayerFragmentV2.this.a(PlayerFragmentV2.this.ai.findViewHolderForAdapterPosition(PlayerFragmentV2.this.mPlayerManager.s()), PlayerFragmentV2.this.mPlayerManager.s());
                        }
                        if (PlayerFragmentV2.this.g) {
                            PlayerFragmentV2.this.J();
                            PlayerFragmentV2.this.q();
                            PlayerFragmentV2.this.g = false;
                            return;
                        }
                        PlayerFragmentV2.this.H();
                        PlayerFragmentV2.this.q();
                    }
                });
            }
        }

        public void onError(f fVar, int i, int i2) {
            if (!PlayerFragmentV2.this.isActivityDestroyed()) {
                if (i == -1000 || i == -1001) {
                    PlayerFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            PlayerFragmentV2.this.P();
                        }
                    });
                }
            }
        }

        public void onAdEventUpdate(f fVar, AdEvent adEvent) {
            switch (adEvent.getType()) {
                case CONTENT_RESUME_REQUESTED:
                    PlayerFragmentV2.this.L();
                    return;
                default:
                    return;
            }
        }

        public void onCompletion(f fVar) {
            PlayerFragmentV2.this.mPlayerStates = PlayerStates.STOPPED;
        }

        public void onBufferingUpdate(final f fVar, final int i) {
            if (!PlayerFragmentV2.this.isActivityDestroyed()) {
                PlayerFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (PlayerStatus.a(PlayerFragmentV2.this.getContext()).c()) {
                            PlayerFragmentV2.this.a(fVar, i);
                        }
                    }
                });
            }
        }
    };
    private int aJ = -1;
    private int aK;
    private boolean aL = true;
    private int aM = 0;
    private View aN;
    private float aO = 0.0f;
    private int aP = -1;
    private int aQ;
    private int aR;
    private Drawable aS;
    private Drawable aT;
    private Drawable aU;
    private Drawable aV;
    private boolean aW = false;
    private Handler aX = new Handler();
    private Runnable aY = new Runnable() {
        public void run() {
            PlayerFragmentV2.this.F();
            PlayerFragmentV2.this.ah.setShouldUpdate(true);
            if (PlayerManager.a(GaanaApplication.getContext()).n() != null && PlayerManager.a(GaanaApplication.getContext()).s() <= PlayerManager.a(GaanaApplication.getContext()).n().size()) {
                PlayerFragmentV2.this.ai.scrollToPosition(PlayerManager.a(GaanaApplication.getContext()).s());
                PlayerFragmentV2.this.aE = true;
            }
            PlayerFragmentV2.this.ax = false;
            PlayerFragmentV2.this.b(PlayerFragmentV2.this.y);
        }
    };
    private ImageView aa;
    private TextView ab;
    private TextView ac;
    private TextView ad;
    private QueueSlidingUpPanelLayout ae;
    private boolean af = false;
    private boolean ag = false;
    private CardPagerAdapterV2 ah;
    private DiscreteScrollView ai;
    private ImageView aj;
    private View ak;
    private LinearLayout al;
    private TextView am;
    private View an;
    private Drawable ao;
    private boolean ap;
    private PublisherAdView aq;
    private boolean ar;
    private int as = 0;
    private DownloadClickAnimation at;
    private ProgressBar au;
    private TextView av;
    private TextView aw;
    private boolean ax = true;
    private boolean ay = false;
    private View az = null;
    n b = new n() {
        public void displayErrorToast(String str, int i) {
        }

        public void onPlayerRepeatReset(boolean z) {
        }

        public void onStreamingQualityChanged(int i) {
        }

        public void onPlayerPlay() {
            if (!PlayerFragmentV2.this.isActivityDestroyed()) {
                PlayerFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV2.this.M();
                    }
                });
            }
        }

        public void onPlayerPause() {
            if (!PlayerFragmentV2.this.isActivityDestroyed()) {
                PlayerFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV2.this.N();
                    }
                });
            }
        }

        public void onPlayerResume() {
            if (!PlayerFragmentV2.this.isActivityDestroyed()) {
                PlayerFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV2.this.O();
                    }
                });
            }
        }

        public void onPlayerStop() {
            if (!PlayerFragmentV2.this.isActivityDestroyed()) {
                PlayerFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV2.this.P();
                    }
                });
            }
        }

        public void onPlayPrevious(final boolean z, final boolean z2) {
            if (!PlayerFragmentV2.this.isActivityDestroyed()) {
                PlayerFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV2.this.a(z, z2);
                    }
                });
            }
        }

        public void onPlayNext(final boolean z, final boolean z2) {
            if (!PlayerFragmentV2.this.isActivityDestroyed()) {
                PlayerFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV2.this.b(z, z2);
                    }
                });
            }
        }

        public void displayErrorDialog(String str, ErrorType errorType) {
            if (!PlayerFragmentV2.this.isActivityDestroyed()) {
                PlayerFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV2.this.P();
                    }
                });
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
    private FrameLayout y;
    private ConstraintLayout z;

    private void E() {
    }

    public void a(ADSTATUS adstatus) {
    }

    public void a(PlayerType playerType) {
    }

    public void d(ADSTATUS adstatus) {
    }

    public void onPlayerStateChanged() {
    }

    public void onRadioTracksFetched(boolean z) {
    }

    public void updateCardAdapter(boolean z) {
    }

    private void q() {
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(getPlayingTrack().getBusinessObjId()));
        if (GaanaMusicService.s().m()) {
            if (this.as != 0) {
                this.au.setVisibility(0);
            } else if (e == DownloadStatus.DOWNLOADING) {
                this.T.setVisibility(8);
            } else {
                this.T.setVisibility(0);
            }
            this.aa.setVisibility(8);
            this.S.setVisibility(0);
            this.M.setVisibility(4);
        } else if (GaanaMusicService.t()) {
            ((ProgressBar) this.K.findViewById(R.id.queue_panel_bottom_progressBar)).setVisibility(8);
            this.au.setVisibility(8);
            this.aa.setVisibility(0);
            this.aa.setImageDrawable(this.ao);
            this.S.setVisibility(8);
            this.M.setVisibility(0);
            this.M.setImageDrawable(this.s);
            if (this.as == 0) {
                this.aa.setVisibility(8);
            }
        } else {
            this.T.setVisibility(8);
            this.au.setVisibility(8);
            this.aa.setVisibility(0);
            this.aa.setImageDrawable(this.R);
            this.S.setVisibility(8);
            this.M.setVisibility(0);
            this.M.setImageDrawable(this.u);
        }
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.ax = true;
        this.K = layoutInflater.inflate(R.layout.fragment_player_v2, viewGroup, false);
        this.L = new PlayerMaterialActionBar(getContext(), PlayerVersion.PlayerV2);
        this.N = this.L.findViewById(R.id.tracker_previous);
        this.O = this.L.findViewById(R.id.tracker_next);
        this.X = (TextView) this.K.findViewById(R.id.qualityText);
        this.Y = (ImageView) this.L.findViewById(R.id.playerQueueIcon);
        this.Y.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PlayerFragmentV2.this.ae.f()) {
                    PlayerFragmentV2.this.ae.h();
                } else {
                    PlayerFragmentV2.this.ae.g();
                }
            }
        });
        this.P = (CrossFadeImageView) this.L.findViewById(R.id.tracker_img);
        this.X.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PlayerFragmentV2.this.af) {
                    PlayerFragmentV2.this.y();
                    PlayerManager.a(PlayerFragmentV2.this.m).j(false);
                }
                PlayerFragmentV2.this.d(view);
            }
        });
        this.P.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerFragmentV2.this.ai.smoothScrollToPosition(PlayerManager.a(PlayerFragmentV2.this.m).s());
                PlayerFragmentV2.this.P.setAlpha(1.0f);
                if (Constants.l) {
                    PlayerFragmentV2.this.O.setBackgroundDrawable(PlayerFragmentV2.this.getResources().getDrawable(R.drawable.player_header_actions_bg_current));
                    PlayerFragmentV2.this.N.setBackgroundDrawable(PlayerFragmentV2.this.getResources().getDrawable(R.drawable.player_header_actions_bg_current));
                } else {
                    PlayerFragmentV2.this.O.setBackgroundDrawable(PlayerFragmentV2.this.getResources().getDrawable(R.drawable.player_header_actions_bg_white_current));
                    PlayerFragmentV2.this.N.setBackgroundDrawable(PlayerFragmentV2.this.getResources().getDrawable(R.drawable.player_header_actions_bg_white_current));
                }
                an.a().a("click", "ac", "indicator", "player", "", "", "", "");
            }
        });
        Toolbar toolbar = (Toolbar) this.K.findViewById(R.id.toolbar);
        toolbar.addView(this.L);
        toolbar.setContentInsetsAbsolute(0, 0);
        setGAScreenName("PlayerHomeScreen", "PlayerHomeScreen");
        ((GaanaActivity) this.m).getSlidingPanelLayout().b(1);
        return this.K;
    }

    private void r() {
        if (e.W == 0) {
            boolean z = getResources().getBoolean(R.bool.isPlayerAdEnabled);
            if (this.m != null && ap.a().b(this.m) && z) {
                if (this.aq == null) {
                    this.aq = new PublisherAdView(this.m);
                }
                ColombiaAdViewManager.a().b(this.m, this.K, e.y, this.aq, this, "");
            }
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.m = getActivity();
        s();
    }

    private void s() {
        TypedArray obtainStyledAttributes = this.m.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.q = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(69, -1));
        this.r = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(70, -1));
        this.s = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(66, -1));
        this.t = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(30, -1));
        this.u = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(68, -1));
        this.v = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(86, -1));
        this.Q = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(30, -1));
        this.R = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(31, -1));
        obtainStyledAttributes.recycle();
        r();
        this.ao = getResources().getDrawable(R.drawable.vector_player_pause_active);
        this.J = new TypedValue();
        boolean z = true;
        getActivity().getTheme().resolveAttribute(R.attr.song_quality_color, this.J, true);
        this.mPlayerManager = PlayerManager.a(getContext());
        this.mRadioManager = ad.a(this.m);
        this.p = (ViewGroup) this.K.findViewById(R.id.ll_queue_container);
        this.ae = (QueueSlidingUpPanelLayout) this.K.findViewById(R.id.sliding_layout_queue);
        this.ae.setAnchorPoint(0.88f);
        this.ae.setOverlayed(true);
        this.ae.setBackgroundResource(R.drawable.shape_bg_transparant);
        this.A = (ImageView) this.K.findViewById(R.id.playerBtnRepeat);
        this.A.setImageDrawable(this.q);
        this.A.setTag(Integer.valueOf(0));
        this.B = (ImageView) this.K.findViewById(R.id.playerBtnShuffle);
        this.B.setImageDrawable(this.r);
        this.B.setTag(Integer.valueOf(0));
        this.U = (SeekBar) this.K.findViewById(R.id.seekBar);
        this.U.setFocusable(false);
        this.U.setPadding(0, 0, 0, 0);
        this.C = (ImageView) this.K.findViewById(R.id.playerBtnPrev);
        this.I = (TextView) this.K.findViewById(R.id.tvPlayerEndTimer);
        this.E = (ImageView) this.K.findViewById(R.id.similarSongsButton);
        this.G = (TextView) this.K.findViewById(R.id.similarSongsButtonText);
        this.G.setOnClickListener(this);
        this.E.setOnClickListener(this);
        this.av = (TextView) this.K.findViewById(R.id.queue_panel_main_text_bottom);
        this.aw = (TextView) this.K.findViewById(R.id.queue_panel_secondary_text_bottom);
        this.y = (FrameLayout) this.K.findViewById(R.id.queueInnerContainer);
        this.ak = this.K.findViewById(R.id.playerBottomBgLayer);
        this.z = (ConstraintLayout) this.K.findViewById(R.id.queueSlidingContainer);
        this.aj = (ImageView) this.K.findViewById(R.id.chevron_up);
        this.ae.setPanelHeight((int) getResources().getDimension(R.dimen.bottom_player_action_container_height));
        X();
        this.aC = (TextView) this.L.findViewById(R.id.trackText);
        this.aD = (TextView) this.L.findViewById(R.id.albumText);
        this.av.setTypeface(Util.i(this.m));
        this.ak.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.M.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerFragmentV2.this.a(view);
            }
        });
        this.V.setOnClickListener(this);
        this.K.findViewById(R.id.playerBtnRepeat).setOnClickListener(this);
        this.K.findViewById(R.id.playerBtnShuffle).setOnClickListener(this);
        this.K.findViewById(R.id.playerBtnPrev).setOnClickListener(this);
        this.D.setOnClickListener(this);
        x();
        this.mCurrentTrack = this.mPlayerManager.a(PlaySequenceType.CURRENT);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ONBOARD_PLAYER_CREATED_FIRST_TIME", 0);
        if (!(GaanaApplication.sessionHistoryCount == 0 && sharedPreferences.getBoolean("ONBOARD_PLAYER_CREATED_FIRST_TIME", true))) {
            z = false;
        }
        this.af = z;
        this.Z = new PlayerQueueViewV2(this.m, ((GaanaActivity) this.m).getCurrentFragment());
        u();
        U();
        if (!(this.mCurrentTrack == null || this.mCurrentTrack.b() == null)) {
            a(this.mCurrentTrack.b());
        }
        this.aa = (ImageView) this.K.findViewById(R.id.queue_panel_bottom_button);
        this.T = (ProgressBar) this.K.findViewById(R.id.queue_panel_bottom_progressBar);
        this.au = (ProgressBar) this.K.findViewById(R.id.queue_panel_bottom_progressBar2);
        this.aa.setOnClickListener(this);
        this.ab = (TextView) this.K.findViewById(R.id.pagerTrackText);
        this.ac = (TextView) this.K.findViewById(R.id.pagerAlbumText);
        this.ad = (TextView) this.K.findViewById(R.id.lyricsTextButton);
        this.ad.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.ab.setTypeface(Util.i(this.m));
        this.p.findViewById(R.id.queue_panel_button_save).setOnClickListener(this);
        this.p.findViewById(R.id.queue_panel_button_clear).setOnClickListener(this);
        this.p.findViewById(R.id.queue_panel_cheveron_view).setOnClickListener(this);
        this.p.findViewById(R.id.queue_panel_bottom_options).setOnClickListener(this);
        a(this.p);
        t();
        v();
        ((GaanaActivity) this.m).setmCurrentPlayerFragment(this);
    }

    private void t() {
        final AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -80.0f);
        translateAnimation.setRepeatMode(1);
        translateAnimation.setRepeatCount(3);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setRepeatMode(1);
        alphaAnimation.setRepeatCount(3);
        animationSet.setDuration(700);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        this.aj.setAnimation(animationSet);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                animationSet.start();
            }
        }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
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

    public boolean a() {
        return this.ax;
    }

    private void a(View view, int i) {
        final int i2 = i + 1;
        final float c = (float) (d.a().c() / 3);
        final ImageView imageView = (ImageView) this.K.findViewById(R.id.handIcon);
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
                PlayerFragmentV2.this.d = new TranslateAnimation(0.0f, 0.0f, -c, 0.0f);
                PlayerFragmentV2.this.d.setFillEnabled(true);
                PlayerFragmentV2.this.d.setFillAfter(true);
                PlayerFragmentV2.this.d.setStartOffset(1000);
                PlayerFragmentV2.this.d.setDuration(1000);
                view2.startAnimation(PlayerFragmentV2.this.d);
                d.a().a("QUEUE_ANIMATION_UP_DOWN", i2, false);
                d.a().a("SESSION_OCCURENCE_QUEUE_ANIMATION_UP_DOWN", GaanaApplication.sessionHistoryCount, false);
                PlayerFragmentV2.this.d.setAnimationListener(new AnimationListener() {
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

    public void a(ViewHolder viewHolder, int i) {
        PlayerTrack a = PlayerManager.a(GaanaApplication.getContext()).a(i);
        if (!(a == null || a.b() == null)) {
            a(a.b());
            E();
        }
        if (this.aJ != i && i != PlayerManager.a(this.m).s()) {
            u.a().a("BoxQueue", "Song Swipe", i > PlayerManager.a(this.m).s() ? "Up Next" : "Previous");
            this.aJ = i;
        }
    }

    private void u() {
        AnonymousClass3 anonymousClass3 = new al() {
            public void a(boolean z) {
            }

            public void a(ViewHolder viewHolder, int i) {
                PlayerFragmentV2.this.aE = true;
                PlayerFragmentV2.this.a(i, i);
                PlayerFragmentV2.this.G();
            }
        };
        this.ai = (DiscreteScrollView) this.K.findViewById(R.id.viewPager);
        this.ai.setItemTransformer(new ScaleTransformer());
        this.ai.setSlideOnFling(false);
        this.aK = PlayerManager.a(this.m).s();
        this.ai.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0) {
                    PlayerFragmentV2.this.aL = true;
                    DiscreteScrollLayoutManager discreteScrollLayoutManager = (DiscreteScrollLayoutManager) recyclerView.getLayoutManager();
                    an.a().c("scroll", AvidJSONUtil.KEY_X, "", "player", "", "", String.valueOf(PlayerFragmentV2.this.aK), String.valueOf(discreteScrollLayoutManager.getCurrentPosition()));
                    PlayerFragmentV2.this.aB = discreteScrollLayoutManager.getCurrentPosition();
                    if (discreteScrollLayoutManager.getCurrentPosition() == PlayerFragmentV2.this.aK || PlayerFragmentV2.this.aM != 0) {
                        PlayerFragmentV2.this.a(discreteScrollLayoutManager.getCurrentPosition());
                        PlayerFragmentV2.this.a(discreteScrollLayoutManager.getCurrentPosition(), PlayerFragmentV2.this.mPlayerManager.s());
                    }
                }
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (PlayerFragmentV2.this.aL && i != 0) {
                    PlayerFragmentV2.this.aM = i;
                    PlayerFragmentV2.this.ab.setAlpha(0.0f);
                    PlayerFragmentV2.this.ac.setAlpha(0.0f);
                    PlayerFragmentV2.this.ad.setAlpha(0.0f);
                    PlayerFragmentV2.this.aL = false;
                }
            }
        });
        this.ah = new CardPagerAdapterV2(this.m, this, this.ai, PlayerManager.a(this.m).n(), anonymousClass3);
        this.ai.setAdapter(this.ah);
        this.ai.setVisibility(0);
        this.al = (LinearLayout) this.K.findViewById(R.id.optionLayout);
    }

    private void v() {
        boolean a = a(this.m);
        if (a) {
            a = this.m.getResources().getBoolean(R.bool.isPlayerAdEnabled);
        }
        LinearLayout linearLayout = (LinearLayout) this.K.findViewById(R.id.llNativeAdSlot);
        View findViewById = this.K.findViewById(R.id.dummy_view);
        LayoutParams layoutParams = (LayoutParams) findViewById.getLayoutParams();
        if (a && this.aH) {
            if (findViewById != null) {
                layoutParams.bottomMargin = Util.b(180);
            }
            linearLayout.setVisibility(0);
            linearLayout.bringToFront();
            return;
        }
        if (findViewById != null) {
            layoutParams.bottomMargin = Util.b(125);
        }
        linearLayout.setVisibility(8);
    }

    public int c() {
        return this.aB;
    }

    private void b(int i) {
        if (i == this.mPlayerManager.s()) {
            if (this.mPlayerManager.a(i).b().isLocalMedia()) {
                this.ac.setVisibility(8);
                this.ab.setVisibility(8);
            }
            return;
        }
        this.ac.setVisibility(0);
        this.ab.setVisibility(0);
        this.ad.setVisibility(8);
        this.ab.setText(this.mPlayerManager.a(i).b().getTrackTitle());
        this.ac.setText(this.mPlayerManager.a(i).b().getAlbumTitle());
        this.ab.setAlpha(0.0f);
        this.ac.setAlpha(0.0f);
        this.ab.animate().alpha(1.0f).setDuration(1000).start();
        this.ac.animate().alpha(1.0f).setDuration(1000).start();
    }

    public void a(int i) {
        this.aB = i;
        if (i != this.mPlayerManager.s()) {
            b(i);
        } else if (getPlayingTrack() == null || getPlayingTrack().isLocalMedia()) {
            b(i);
        } else {
            b(getPlayingTrack());
            this.ac.setVisibility(8);
            this.ab.setVisibility(8);
        }
    }

    private void a(int i, int i2) {
        CharSequence charSequence = "";
        if (i2 == i) {
            charSequence = "Now Playing";
        } else if (i2 < i) {
            charSequence = "Up Next";
        } else if (i2 > i) {
            charSequence = "Previous";
        }
        PlayerTrack a = this.mPlayerManager.a(i);
        this.aC.setText(charSequence);
        if (a != null && a.b() != null) {
            TextView textView = this.aD;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("From ");
            stringBuilder.append(a.b().getAlbumTitle());
            textView.setText(stringBuilder.toString());
        }
    }

    private int w() {
        return (int) ((float) (d.a().c() - this.aR));
    }

    private void b(View view) {
        view.bringToFront();
    }

    private void x() {
        this.j = (Toolbar) this.K.findViewById(R.id.toolbar);
        this.j.setContentInsetsAbsolute(0, 0);
        this.j.getMenu().clear();
        this.j.setBackgroundColor(getResources().getColor(R.color.transparent_color));
        this.L.setToolbar(this.j);
        this.j.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PlayerFragmentV2.this.ae.e()) {
                    PlayerFragmentV2.this.ae.g();
                }
            }
        });
    }

    private void y() {
        Editor edit = GaanaApplication.getContext().getSharedPreferences("ONBOARD_PLAYER_CREATED_FIRST_TIME", 0).edit();
        edit.putBoolean("ONBOARD_PLAYER_CREATED_FIRST_TIME", false);
        edit.apply();
        Util.a(null);
        this.af = false;
        if (!(isActivityDestroyed() || this.l == null || this.l.a() != 0)) {
            this.l.a(0);
        }
    }

    private void b(boolean z) {
        if (this.l != null) {
            if (this.af && z && this.mPlayerManager.E() == null) {
                y();
            }
            if (!z) {
                this.l.a(2);
            } else if (this.l.a() == 2 && !com.managers.f.v().w()) {
                this.l.a(0);
            }
        }
    }

    private void z() {
        if (this.mPlayerManager.m() == PlayerType.GAANA_RADIO && this.mPlayerManager.g()) {
            this.mPlayerManager.a(PlayerType.GAANA_RADIO, getContext());
        } else if (this.mPlayerManager.m() != PlayerType.GAANA_RADIO) {
            this.mPlayerManager.f(false);
        }
    }

    public void onResume() {
        super.onResume();
        try {
            d();
            if (this.ax) {
                this.aX.postDelayed(this.aY, 100);
            }
            this.aX.postDelayed(new Runnable() {
                public void run() {
                    DiscreteScrollLayoutManager layoutManager = PlayerFragmentV2.this.ai.getLayoutManager();
                    PlayerFragmentV2.this.a(layoutManager.getCurrentPosition());
                    PlayerFragmentV2.this.a(layoutManager.getCurrentPosition(), layoutManager.getCurrentPosition());
                    if (PlayerFragmentV2.this.aK == PlayerFragmentV2.this.mPlayerManager.s()) {
                        PlayerFragmentV2.this.ah.setCFTracksData();
                    }
                    PlayerFragmentV2.this.aL = true;
                    PlayerFragmentV2.this.v();
                    PlayerFragmentV2.this.G();
                    if (PlayerFragmentV2.this.ai.getViewHolder(PlayerFragmentV2.this.mPlayerManager.s()) != null) {
                        PlayerFragmentV2.this.aP = PlayerFragmentV2.this.ai.getViewHolder(PlayerFragmentV2.this.mPlayerManager.s()).itemView.findViewById(R.id.card_player_image).getHeight();
                        RelativeLayout relativeLayout = ((CardViewHolder) PlayerFragmentV2.this.ai.getViewHolder(PlayerFragmentV2.this.mPlayerManager.s())).recommendationCard;
                    }
                }
            }, 1000);
        } catch (Exception unused) {
        }
        ((GaanaActivity) this.m).setmCurrentPlayerFragment(this);
        if (this.ar != a(this.m)) {
            this.ar = a(this.m);
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

    public void d() {
        this.m = getContext();
        if (this.m != null) {
            if (this.mPlayerManager.n() == null || this.mPlayerManager.n().size() <= 0) {
                b(false);
                return;
            }
            b(true);
            z();
            o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.b);
            o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.aI);
            this.mAppState.setPlayerStatus(true);
            this.mCurrentTrack = PlayerManager.a(GaanaApplication.getContext()).i();
            this.ah.updateArrayList(PlayerManager.a(this.m).n());
            if (this.mPlayerManager.t() && this.mPlayerManager.s() != -1) {
                this.mCurrentTrack = this.mPlayerManager.a(this.mPlayerManager.s());
                a(this.mCurrentTrack);
            }
            a(this.mPlayerManager.m());
            A();
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
                    H();
                } else if (GaanaMusicService.s().l() && !GaanaMusicService.s().m() && !PlayerStatus.a(getContext()).e()) {
                    a(this.mCurrentTrack);
                    H();
                } else if (GaanaMusicService.s().m()) {
                    a(this.mCurrentTrack);
                    if (GaanaMusicService.s().l()) {
                        this.M.setImageDrawable(this.s);
                        this.aa.setImageDrawable(this.ao);
                    } else {
                        this.M.setImageDrawable(this.s);
                        this.aa.setImageDrawable(this.ao);
                    }
                } else if (PlayerStatus.a(getContext()).e()) {
                    a(this.mCurrentTrack);
                    K();
                }
            }
        }
    }

    public GaanaApplication e() {
        return this.mAppState;
    }

    private void A() {
        B();
        a(this.A, false);
        if (PlayerStatus.a(getContext()).c() || PlayerStatus.a(getContext()).b()) {
            this.M.setImageDrawable(this.s);
            this.aa.setImageDrawable(this.ao);
        } else {
            this.M.setImageDrawable(this.u);
            this.aa.setImageDrawable(this.R);
        }
        C();
    }

    private void B() {
        if (this.mPlayerManager.d()) {
            this.A.setTag(Integer.valueOf(0));
        } else if (this.mPlayerManager.e()) {
            this.A.setTag(Integer.valueOf(1));
        } else {
            this.A.setTag(Integer.valueOf(2));
        }
    }

    private void C() {
        boolean b = PlayerManager.a(this.m).b();
        if (b) {
            this.B.setImageResource(this.w[b]);
        } else {
            this.B.setImageDrawable(this.r);
        }
        this.B.setTag(Integer.valueOf(b));
    }

    public void a(f fVar, int i) {
        if (this.U == null) {
            this.U = (SeekBar) this.K.findViewById(R.id.seekBar);
            this.U.setFocusable(false);
        }
        if (GaanaMusicService.s().m()) {
            this.U.setSecondaryProgress(0);
            return;
        }
        this.U.setMax(fVar.u());
        this.U.setSecondaryProgress((int) ((0.01d * ((double) i)) * ((double) fVar.u())));
    }

    public void onStart() {
        super.onStart();
    }

    private void D() {
        int b = d.a().b("PREFERENCE_UJ_MINI_V4_ENABLED", 0, false);
        if (Constants.P != b) {
            ((GaanaActivity) this.m).initBottomNavigationBar();
            ((GaanaActivity) this.m).initMiniPlayer();
            Constants.P = b;
        }
    }

    public void onPause() {
        if (this.o != null && this.o.isShowing()) {
            this.o.dismiss();
            this.o = null;
        }
        ((GaanaActivity) this.m).setmCurrentPlayerFragment(null);
        D();
        super.onPause();
    }

    public void onStop() {
        this.aX.removeCallbacks(this.aY);
        ((GaanaActivity) this.m).setmCurrentPlayerFragment(null);
        super.onStop();
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDestroyView() {
        if (o.a("LISTENER_KEY_PLAYER_ACTIVITY") == this) {
            this.k.removeCallbacksAndMessages(null);
            if (this.af && this.ag) {
                y();
            }
            o.b("LISTENER_KEY_PLAYER_ACTIVITY");
            o.d("LISTENER_KEY_PLAYER_ACTIVITY");
        }
        this.aX.removeCallbacks(this.aY);
        super.onDestroyView();
    }

    public QueueSlidingUpPanelLayout f() {
        return this.ae;
    }

    private void a(PlayerTrack playerTrack) {
        this.mAppState.setPlayerStatus(true);
        String subtitleText = getSubtitleText(getPlayingTrack().getAlbumTitle(), getPlayingTrack().getArtistNames());
        boolean isParentalWarningEnabled = getPlayingTrack().isParentalWarningEnabled();
        subtitleText = subtitleText.trim();
        if (Constants.aG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.m.getString(R.string.CASTING_TO));
            stringBuilder.append(Constants.aH);
            subtitleText = stringBuilder.toString();
        }
        this.av.setText(getPlayingTrack().getName());
        this.aw.setSelected(true);
        boolean z = Constants.l;
        if (isParentalWarningEnabled) {
            Util.a(this.aw, subtitleText);
        } else {
            this.aw.setText(subtitleText);
        }
        if (getPlayingTrack().isLocalMedia()) {
            Toolbar toolbar = (Toolbar) this.K.findViewById(R.id.toolbar);
            if (toolbar != null) {
                Menu menu = toolbar.getMenu();
                if (!(menu == null || menu.findItem(R.id.menu_add_to_playlist) == null)) {
                    menu.findItem(R.id.menu_add_to_playlist).setVisible(false);
                }
            }
        }
        this.ah.notifyDataSetChanged();
        if (PlayerManager.a(GaanaApplication.getContext()).s() > -1) {
            PlayerManager.a(GaanaApplication.getContext()).n().size();
        }
        U();
        E();
        c(this.mCurrentTrack.b());
        if (this.mPlayerManager.H() == null && this.mCurrentTrack.b().isLocalMedia()) {
            a(false);
        } else {
            a(true);
        }
        if (this.mCurrentTrack != null) {
            a((ImageView) this.K.findViewById(R.id.queue_panel_img_animation), getPlayingTrack());
        }
        this.K.findViewById(R.id.pull_up_text).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerFragmentV2.this.ae.h();
            }
        });
    }

    private void c(int i) {
        if (this.mPlayerManager != null && this.mPlayerManager.m() == PlayerType.GAANA) {
            Track b = this.mPlayerManager.a(i).b();
            if (b == null) {
                if (this.mPlayerManager.i() != null) {
                    b = this.mPlayerManager.i().b();
                } else {
                    return;
                }
            }
            final ImageView imageView = (ImageView) this.K.findViewById(R.id.favourite_item);
            imageView.setTag(b.getBusinessObjId());
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PlayerFragmentV2.this.aN = view;
                    PlayerFragmentV2.this.a(imageView, b);
                }
            });
            if (b.isLocalMedia()) {
                imageView.setVisibility(8);
                return;
            }
            imageView.setVisibility(0);
            if (b.isFavorite().booleanValue()) {
                imageView.setImageResource(R.drawable.vector_more_option_favorited);
            } else {
                TypedArray obtainStyledAttributes = this.m.obtainStyledAttributes(R.styleable.VectorDrawables);
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(49, -1)));
                obtainStyledAttributes.recycle();
            }
        }
    }

    public void a(final ImageView imageView, final Track track) {
        if (track != null) {
            track.setBusinessObjType(BusinessObjectType.Tracks);
            af a = af.a(this.m, null);
            a.a("Player Screen");
            a.b(track.getBusinessObjId());
            a.a((int) R.id.favoriteMenu, (BusinessObject) track, new a() {
                public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
                    ImageView imageView = imageView;
                    if (track == null || !track.isFavorite().booleanValue()) {
                        TypedArray obtainStyledAttributes = PlayerFragmentV2.this.m.obtainStyledAttributes(R.styleable.VectorDrawables);
                        imageView.setImageDrawable(ContextCompat.getDrawable(PlayerFragmentV2.this.getContext(), obtainStyledAttributes.getResourceId(49, -1)));
                        obtainStyledAttributes.recycle();
                        an.a().a("click", "ac", "", "player", "", "unfav", "", "");
                        return;
                    }
                    imageView.setImageResource(R.drawable.vector_more_option_favorited);
                    imageView.setPadding(PlayerFragmentV2.this.m.getResources().getDimensionPixelSize(R.dimen.dp10), PlayerFragmentV2.this.m.getResources().getDimensionPixelSize(R.dimen.dp10), PlayerFragmentV2.this.m.getResources().getDimensionPixelSize(R.dimen.dp10), PlayerFragmentV2.this.m.getResources().getDimensionPixelSize(R.dimen.dp10));
                    if (PlayerFragmentV2.this.aN != null) {
                        Animation loadAnimation = AnimationUtils.loadAnimation(PlayerFragmentV2.this.m, R.anim.favorite_tap_animation);
                        loadAnimation.setInterpolator(new com.a.a(0.2d, 20.0d));
                        PlayerFragmentV2.this.aN.startAnimation(loadAnimation);
                    }
                    an.a().a("click", "ac", "", "player", "", "fav", "", "");
                }
            });
        }
    }

    private void F() {
        this.ae.setBackgroundColor(0);
        this.an = this.p.findViewById(R.id.queue_panel_button_save);
        this.am = (TextView) this.p.findViewById(R.id.queue_panel_cheveron_view);
        this.am.setOnClickListener(this);
        this.ae.setBackgroundDrawable(new ColorDrawable(0));
        this.ae.setPanelSlideListener(new QueueSlidingUpPanelLayout.b() {
            public void a(View view, float f) {
                float f2 = f;
                PlayerFragmentV2.this.aO = f2;
                double d = (double) f2;
                if (d <= 0.8d) {
                    if (PlayerFragmentV2.this.am.getVisibility() != 4) {
                        int i;
                        if (PlayerFragmentV2.this.aA != null) {
                            PlayerFragmentV2.this.aA.setVisibility(8);
                        }
                        PlayerFragmentV2.this.U.setVisibility(0);
                        PlayerFragmentV2.this.ay = false;
                        ((ImageView) PlayerFragmentV2.this.K.findViewById(R.id.queue_panel_img_animation)).setVisibility(0);
                        PlayerFragmentV2.this.am.setVisibility(4);
                        PlayerFragmentV2.this.an.setVisibility(8);
                        PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_button_save).setVisibility(4);
                        PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_button_clear).setVisibility(4);
                        PlayerFragmentV2.this.j.setVisibility(0);
                        PlayerFragmentV2.this.K.findViewById(R.id.view_pager_container).setVisibility(0);
                        PlayerFragmentV2.this.ai.setVisibility(0);
                        PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_bottom_options).setVisibility(4);
                        PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_button_save).setOnClickListener(null);
                        PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_button_clear).setOnClickListener(null);
                        PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_bottom_options).setOnClickListener(null);
                        PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_bottom_options).setVisibility(8);
                        PlayerFragmentV2.this.T = (ProgressBar) PlayerFragmentV2.this.K.findViewById(R.id.queue_panel_bottom_progressBar);
                        PlayerFragmentV2.this.au = (ProgressBar) PlayerFragmentV2.this.K.findViewById(R.id.queue_panel_bottom_progressBar2);
                        PlayerFragmentV2.this.au.setVisibility(8);
                        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(PlayerFragmentV2.this.getPlayingTrack().getBusinessObjId()));
                        if (!GaanaMusicService.s().m()) {
                            i = 8;
                            if (e != DownloadStatus.DOWNLOADING) {
                                PlayerFragmentV2.this.T.setVisibility(8);
                            }
                        } else if (e == DownloadStatus.DOWNLOADING) {
                            i = 8;
                            PlayerFragmentV2.this.T.setVisibility(8);
                        } else {
                            i = 8;
                        }
                        PlayerFragmentV2.this.K.findViewById(R.id.queue_panel_bottom_button).setVisibility(i);
                    }
                    PlayerFragmentV2.this.y.setVisibility(0);
                    PlayerFragmentV2.this.Z.stopQueueAnimation();
                    PlayerFragmentV2.this.y.setAlpha((1.0f - f2) + 0.2f);
                    PlayerFragmentV2.this.z.setVisibility(8);
                } else if (PlayerFragmentV2.this.an.getVisibility() != 0) {
                    if (PlayerFragmentV2.this.aA != null) {
                        PlayerFragmentV2.this.aA.setVisibility(8);
                    }
                    PlayerFragmentV2.this.y.setVisibility(8);
                    PlayerFragmentV2.this.z.setVisibility(0);
                    PlayerFragmentV2.this.ay = true;
                    PlayerFragmentV2.this.setGAScreenName("PlayerQueue", "PlayerQueue");
                    ((ImageView) PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_img_animation)).setVisibility(4);
                    PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_button_save).setVisibility(0);
                    PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_button_clear).setVisibility(0);
                    PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_button_save).setOnClickListener(PlayerFragmentV2.this);
                    PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_button_clear).setOnClickListener(PlayerFragmentV2.this);
                    PlayerFragmentV2.this.K.findViewById(R.id.view_pager_container).setVisibility(0);
                    PlayerFragmentV2.this.ai.setVisibility(4);
                    PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_bottom_options).setOnClickListener(PlayerFragmentV2.this);
                    PlayerFragmentV2.this.p.findViewById(R.id.queue_panel_bottom_options).setVisibility(0);
                    PlayerFragmentV2.this.aa = (ImageView) PlayerFragmentV2.this.K.findViewById(R.id.queue_panel_bottom_button);
                    PlayerFragmentV2.this.T = (ProgressBar) PlayerFragmentV2.this.K.findViewById(R.id.queue_panel_bottom_progressBar);
                    PlayerFragmentV2.this.T.setVisibility(8);
                    PlayerFragmentV2.this.au = (ProgressBar) PlayerFragmentV2.this.K.findViewById(R.id.queue_panel_bottom_progressBar2);
                    PlayerFragmentV2.this.aa.setOnClickListener(PlayerFragmentV2.this);
                    if (GaanaMusicService.s().m()) {
                        PlayerFragmentV2.this.au.setVisibility(0);
                        PlayerFragmentV2.this.aa.setVisibility(8);
                    } else if (GaanaMusicService.t()) {
                        PlayerFragmentV2.this.au.setVisibility(8);
                        PlayerFragmentV2.this.aa.setVisibility(0);
                        PlayerFragmentV2.this.aa.setImageDrawable(PlayerFragmentV2.this.ao);
                    } else {
                        PlayerFragmentV2.this.au.setVisibility(8);
                        PlayerFragmentV2.this.aa.setVisibility(0);
                        PlayerFragmentV2.this.aa.setImageDrawable(PlayerFragmentV2.this.R);
                    }
                    PlayerFragmentV2.this.am.setVisibility(0);
                    PlayerFragmentV2.this.Q();
                    PlayerFragmentV2.this.y.setAlpha((1.0f - f2) - 15.0f);
                }
                if (d > 0.1d && !PlayerFragmentV2.this.ap) {
                    PlayerFragmentV2.this.ap = true;
                }
            }

            public void a(View view, int i, int i2) {
                if (PlayerFragmentV2.this.aO <= 0.65f) {
                    PlayerFragmentV2.this.as = i2;
                    if (i2 == 0) {
                        if (i2 != i) {
                            PlayerFragmentV2.this.setGAScreenName("PlayerHomeScreen", "PlayerHomeScreen");
                        }
                        if (PlayerFragmentV2.this.mPlayerManager.s() == 0) {
                            ((LinearLayoutManager) PlayerFragmentV2.this.Z.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(PlayerFragmentV2.this.mPlayerManager.s(), 0);
                        } else {
                            ((LinearLayoutManager) PlayerFragmentV2.this.Z.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(PlayerFragmentV2.this.mPlayerManager.s(), Util.b(-30));
                        }
                        an.a().a("click", "ac", "", "queue", "", "close", "", "");
                        PlayerFragmentV2.this.ap = false;
                    } else if (i2 == 1) {
                        if (PlayerFragmentV2.this.mPlayerManager.s() == 0) {
                            ((LinearLayoutManager) PlayerFragmentV2.this.Z.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(PlayerFragmentV2.this.mPlayerManager.s(), 0);
                        } else {
                            ((LinearLayoutManager) PlayerFragmentV2.this.Z.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(PlayerFragmentV2.this.mPlayerManager.s(), Util.b(-30));
                        }
                        an.a().a("click", "ac", "", "queue", "", "open", "", "");
                    } else {
                        PlayerFragmentV2.this.G();
                    }
                }
            }
        });
        if (this.mPlayerManager.n() == null || this.mPlayerManager.n().size() <= 0) {
            this.ae.setSlidingEnabled(false);
            return;
        }
        View view = this.Z.getView(this.m, this.mPlayerManager.n(), this, this.p);
        this.Z.getPlayerQueueView().setVisibility(0);
        this.Z.getRecyclerView().setVisibility(0);
        view.setClickable(true);
        this.ae.setScrollingView(this.Z.getRecyclerView());
    }

    private void G() {
        if (this.Z.getRecyclerView() != null && this.Z.getRecyclerView().getLayoutManager() != null) {
            if (this.mPlayerManager.s() == 0) {
                ((LinearLayoutManager) this.Z.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(this.mPlayerManager.s(), 0);
            } else {
                ((LinearLayoutManager) this.Z.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(this.mPlayerManager.s(), Util.b(-30));
            }
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.aS = getResources().getDrawable(R.drawable.player_header_actions_bg_white);
        this.aV = getResources().getDrawable(R.drawable.player_header_actions_bg_white_current);
        this.aT = getResources().getDrawable(R.drawable.player_header_actions_bg);
        this.aU = getResources().getDrawable(R.drawable.player_header_actions_bg_current);
        this.aR = (int) getResources().getDimension(R.dimen.bottom_player_action_container_height);
        this.aQ = w() + 100;
    }

    private void H() {
        int u;
        try {
            u = GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
            u = 0;
        }
        this.U.setMax(u);
        this.U.setSecondaryProgress(0);
        this.U.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                PlayerFragmentV2.this.a((float) PlayerFragmentV2.this.aF);
                return false;
            }
        });
        this.U.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (PlayerFragmentV2.this.f) {
                    Util.d(PlayerFragmentV2.this.i - ((long) PlayerFragmentV2.this.e));
                }
                u.a().b("Player", "Seekbar Moved");
                o.a(PlayerFragmentV2.this.getContext(), PlayerFragmentV2.this.U.getProgress());
                PlayerFragmentV2.this.I();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                PlayerFragmentV2.this.i = (long) PlayerFragmentV2.this.e;
                PlayerFragmentV2.this.a(0.0f, 0.0f);
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                PlayerFragmentV2.this.f = z;
                r0 = new Object[2];
                long j = (long) i;
                r0[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) / 60);
                r0[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % 60);
                String format = String.format("%2d:%02d", r0);
                r15 = new Object[2];
                long u = (long) (GaanaMusicService.s().u() - i);
                r15[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) / 60);
                r15[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) % 60);
                PlayerFragmentV2.this.I.setText(String.format("%2d:%02d", r15));
                PlayerFragmentV2.this.e = i;
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((Activity) PlayerFragmentV2.this.m).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                i = (int) ((((double) i) / ((double) GaanaMusicService.s().u())) * 100.0d);
                int i2 = (displayMetrics.widthPixels * i) / 100;
                PlayerFragmentV2.this.aF = i2;
                if (PlayerFragmentV2.this.ae.e()) {
                    PlayerFragmentV2.this.Z.setPlayerSeekAnimation((float) i2);
                }
                if (PlayerFragmentV2.this.f) {
                    PlayerFragmentV2.this.H.setTypeface(Util.i(PlayerFragmentV2.this.m));
                    PlayerFragmentV2.this.H.setText(format);
                    PlayerFragmentV2.this.F.setText(format);
                    PlayerFragmentV2.this.a((float) i2);
                    PlayerFragmentV2.this.ae.g();
                    return;
                }
                PlayerFragmentV2.this.H.setTypeface(Util.i(PlayerFragmentV2.this.m));
                PlayerFragmentV2.this.H.setText(format);
                i2 = (PlayerFragmentV2.this.aP * i) / 100;
                PlayerFragmentV2.this.F.setText(format);
            }
        });
        L();
        if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
            this.M.setImageDrawable(this.s);
            this.aa.setImageDrawable(this.ao);
            return;
        }
        this.M.setImageDrawable(this.u);
        this.aa.setImageDrawable(this.R);
    }

    public void a(float f) {
        Handler handler = new Handler(Looper.getMainLooper());
        a(0.0f, f);
    }

    public void a(float f, float f2) {
        this.aW = true;
        this.ae.setSlidingEnabled(false);
        View findViewById = this.K.findViewById(R.id.playerSeekerBg);
        View view = (CrossFadeImageView) this.K.findViewById(R.id.seekerLargeImage);
        findViewById.setVisibility(0);
        view.bindImage(Util.f(this.m, this.mPlayerManager.i().b().getArtworkLarge()));
        FrameLayout frameLayout = (FrameLayout) this.K.findViewById(R.id.playerTopLayout);
        frameLayout = (FrameLayout) this.K.findViewById(R.id.seekerLayout);
        frameLayout.setVisibility(0);
        if (view.getVisibility() == 4 || view.getVisibility() == 8) {
            view.setVisibility(0);
            c(view);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.aQ);
            layoutParams.bottomMargin = this.aR;
            frameLayout.setLayoutParams(layoutParams);
        }
        findViewById.setLayoutParams(new FrameLayout.LayoutParams((int) f2, this.aQ));
    }

    private void c(View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.m, R.anim.zoom_in);
        int parseInt = Integer.parseInt(Util.l().split(AvidJSONUtil.KEY_X)[0]);
        if (parseInt <= 1920) {
            loadAnimation = AnimationUtils.loadAnimation(this.m, R.anim.zoom_in_medium_resolution);
        }
        if (parseInt <= 1280) {
            loadAnimation = AnimationUtils.loadAnimation(this.m, R.anim.zoom_in_low_resolution);
            this.H.setPadding(0, 0, 0, 10);
        }
        if (parseInt <= 900) {
            loadAnimation = AnimationUtils.loadAnimation(this.m, R.anim.zoom_in_lower_resolution);
            this.H.setPadding(0, 0, 0, 40);
        }
        view.startAnimation(loadAnimation);
        this.ai.setAlpha(0.0f);
        this.K.findViewById(R.id.pull_up_text).setVisibility(4);
        this.K.findViewById(R.id.chevron_up).setVisibility(4);
        this.K.findViewById(R.id.tvPlayerStartTimer).setVisibility(4);
        this.K.findViewById(R.id.tvPlayerEndTimer).setVisibility(4);
    }

    private void I() {
        ((FrameLayout) this.K.findViewById(R.id.seekerLayout)).setVisibility(8);
        this.ai.setAlpha(1.0f);
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) this.K.findViewById(R.id.seekerLargeImage);
        crossFadeImageView.startAnimation(AnimationUtils.loadAnimation(this.m, R.anim.zoom_out));
        crossFadeImageView.setVisibility(8);
        this.F.setText(this.H.getText());
        this.aW = false;
        this.ae.setSlidingEnabled(true);
        this.K.findViewById(R.id.pull_up_text).setVisibility(8);
        this.K.findViewById(R.id.chevron_up).setVisibility(8);
        this.K.findViewById(R.id.tvPlayerStartTimer).setVisibility(8);
        this.K.findViewById(R.id.tvPlayerEndTimer).setVisibility(8);
    }

    private void J() {
        int u;
        try {
            u = GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
            u = 0;
        }
        o.a(getContext(), this.h);
        this.U.setProgress(this.h);
        this.U.setMax(u);
        this.U.setSecondaryProgress(this.h);
        this.U.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (PlayerFragmentV2.this.f) {
                    Util.d(PlayerFragmentV2.this.i - ((long) PlayerFragmentV2.this.e));
                }
                o.a(PlayerFragmentV2.this.getContext(), PlayerFragmentV2.this.U.getProgress());
                PlayerFragmentV2.this.I();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                PlayerFragmentV2.this.i = (long) PlayerFragmentV2.this.e;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                PlayerFragmentV2.this.f = z;
                r0 = new Object[2];
                long j = (long) i;
                r0[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) / 60);
                r0[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % 60);
                PlayerFragmentV2.this.F.setText(String.format("%2d:%02d", r0));
                r15 = new Object[2];
                long u = (long) (GaanaMusicService.s().u() - i);
                r15[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) / 60);
                r15[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) % 60);
                PlayerFragmentV2.this.I.setText(String.format("%2d:%02d", r15));
                PlayerFragmentV2.this.e = i;
                PlayerFragmentV2.this.Z.setPlayerSeekAnimation((float) i);
            }
        });
        L();
        if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
            this.M.setImageDrawable(this.s);
            this.aa.setImageDrawable(this.ao);
            return;
        }
        this.M.setImageDrawable(this.u);
        this.aa.setImageDrawable(this.R);
    }

    private void K() {
        this.U.setProgress(0);
        this.U.setSecondaryProgress(0);
        this.U.setMax(0);
        this.F.setText("0:00");
        this.I.setText("0:00");
    }

    private void L() {
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
            if (!this.aW) {
                this.U.setProgress(v);
            }
            this.U.setMax(u);
            this.U.setSelected(false);
            this.U.setSecondaryProgress((int) ((0.01d * ((double) GaanaMusicService.s().t())) * ((double) GaanaMusicService.s().u())));
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
                K();
            }
            this.F.setText(format);
            if (i >= 0) {
                this.I.setText(format2);
            }
            if (!((format2.equalsIgnoreCase(" 0:00") && this.mPlayerManager.t() && this.mPlayerManager.v()) || !GaanaMusicService.t() || GaanaMusicService.s().m())) {
                AnonymousClass15 anonymousClass15 = new Runnable() {
                    public void run() {
                        PlayerFragmentV2.this.L();
                    }
                };
                this.k.removeCallbacksAndMessages(null);
                this.k.postDelayed(anonymousClass15, 1000);
            }
        }
    }

    private void M() {
        if (((GaanaActivity) this.m).isPlayerExpanded()) {
            r();
        }
        o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.aI);
        this.mCurrentTrack = this.mPlayerManager.a(PlaySequenceType.CURRENT);
        a(this.mCurrentTrack);
        if (!this.g) {
            K();
        }
        this.mPlayerManager.d(null);
        this.ah.setCFTracksData();
        this.aa.setImageDrawable(this.ao);
        this.M.setImageDrawable(this.s);
        this.M.setVisibility(4);
        this.aa.setVisibility(8);
        this.S.setVisibility(0);
        this.S.setVisibility(0);
        this.mPlayerStates = PlayerStates.LOADING;
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(getPlayingTrack().getBusinessObjId()));
        g();
        this.ai.scrollToPosition(this.mPlayerManager.s());
        if (this.as != 0) {
            this.au.setVisibility(0);
        } else if (e == DownloadStatus.DOWNLOADING) {
            this.T.setVisibility(8);
        } else {
            ((ProgressBar) this.K.findViewById(R.id.queue_panel_bottom_progressBar)).setVisibility(0);
        }
        this.aK = this.mPlayerManager.s();
        a(this.aK);
        a(this.aK, this.aK);
    }

    public void g() {
        if (this.Z != null) {
            this.Z.notifyDataSetChanged();
        }
        if (this.ah != null) {
            this.ah.notifyDataSetChanged();
        }
        if (this.mCurrentTrack != null) {
            a((ImageView) this.K.findViewById(R.id.queue_panel_img_animation), getPlayingTrack());
        }
        if (!(this.mCurrentTrack == null || this.at == null)) {
            this.at.changeDownlaodButtonIcon(getPlayingTrack(), (ImageView) this.K.findViewById(R.id.queue_panel_download_button));
        }
        if (this.ai != null) {
            this.ai.scrollToPosition(this.mPlayerManager.s());
        }
    }

    public void h() {
        if (this.Z != null) {
            this.Z.notifyDataSetChanged();
        }
        if (this.ah != null) {
            this.ah.notifyDataSetChanged();
        }
    }

    public void refreshPlayerStatus() {
        if (isAdded()) {
            if (this.o != null && this.o.isShowing()) {
                this.o.dismiss();
                this.o = null;
            }
            if (this.Z != null) {
                this.Z.notifyDataSetChanged();
            }
            if (!(this.mCurrentTrack == null || this.at == null)) {
                this.at.changeDownlaodButtonIcon(getPlayingTrack(), (ImageView) this.K.findViewById(R.id.queue_panel_download_button));
                E();
            }
        }
    }

    private void N() {
        this.M.setImageDrawable(this.u);
        this.M.setVisibility(0);
        this.aa.setImageDrawable(this.R);
        if (this.as != 0) {
            this.aa.setVisibility(0);
        }
        this.T.setVisibility(8);
        this.au.setVisibility(8);
        this.S.setVisibility(8);
        this.mPlayerStates = PlayerStates.PAUSED;
    }

    private void O() {
        this.M.setImageDrawable(this.s);
        this.M.setVisibility(0);
        this.aa.setImageDrawable(this.ao);
        if (this.as != 0) {
            this.aa.setVisibility(0);
        }
        this.T.setVisibility(8);
        this.au.setVisibility(8);
        this.S.setVisibility(8);
        if (!GaanaMusicService.s().m()) {
            if (this.g) {
                J();
            } else {
                H();
            }
        }
        this.mPlayerStates = PlayerStates.PLAYING;
    }

    private void P() {
        if (this.M != null) {
            this.M.setImageDrawable(this.u);
            this.M.setVisibility(0);
            this.aa.setImageDrawable(this.R);
            if (this.ay) {
                this.aa.setVisibility(0);
            } else {
                this.aa.setVisibility(8);
            }
            this.T.setVisibility(8);
            this.au.setVisibility(8);
            this.S.setVisibility(8);
            this.mPlayerStates = PlayerStates.STOPPED;
            g();
        }
    }

    private void a(boolean z, boolean z2) {
        this.M.setImageDrawable(this.s);
        this.aa.setImageDrawable(this.ao);
        this.ai.scrollToPosition(this.mPlayerManager.s());
        a(this.mPlayerManager.s(), this.mPlayerManager.s());
        if (z2) {
            a(z, PlayerCommands.PLAY_PREVIOUS);
            return;
        }
        this.k.removeCallbacksAndMessages(null);
        K();
    }

    private void b(boolean z, boolean z2) {
        if (this.o != null && this.o.isShowing()) {
            this.o.dismiss();
            this.o = null;
        }
        this.M.setImageDrawable(this.s);
        this.aa.setImageDrawable(this.ao);
        this.ai.scrollToPosition(this.mPlayerManager.s());
        a(this.mPlayerManager.s(), this.mPlayerManager.s());
        if (z2) {
            a(z, PlayerCommands.PLAY_NEXT);
            g();
            return;
        }
        this.k.removeCallbacksAndMessages(null);
        K();
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
                if (this.ae != null) {
                    Q();
                    if (this.ae.f()) {
                        this.ae.h();
                    } else {
                        this.ae.g();
                    }
                    d.a().a("PREFERENCE_KEY_QUEUE_ANIMATION_INITIATED", true, false);
                    break;
                }
                break;
            case R.id.lyricsButton /*2131297649*/:
            case R.id.lyricsButtonText /*2131297650*/:
                R();
                break;
            case R.id.playerBtnNext /*2131297993*/:
            case R.id.playerBtnNextRadio /*2131297994*/:
                if (PlayerManager.a(this.m).x() == null || PlayerManager.a(this.m).x().b().isLocalMedia() || !Constants.aa || Constants.h > 0) {
                    if (this.af) {
                        y();
                        PlayerManager.a(this.m).j(false);
                    }
                    ((BaseActivity) this.m).sendGAEvent("Player", "Skip", "Player - Skip - Song");
                    o.f(getContext());
                    k();
                    break;
                }
                u.a().a("Shuffle Product", "Gaana+ popup", "Player Next");
                an.a().a("click", "ac", "", "player", "", "nxt", "", "");
                Util.a(this.m, BLOCK_ACTION.SKIP);
                return;
            case R.id.playerBtnPrev /*2131297996*/:
                k();
                if (PlayerManager.a(this.m).w() == null || PlayerManager.a(this.m).w().b().isLocalMedia() || !Constants.aa || Constants.h > 0) {
                    if (this.af) {
                        y();
                        PlayerManager.a(this.m).j(false);
                    }
                    ((BaseActivity) this.m).sendGAEvent("Player", "Skip", "Player - Skip - Song");
                    o.e(getContext());
                    break;
                }
                u.a().a("Shuffle Product", "Gaana+ popup", "Player Previous");
                an.a().a("click", "ac", "", "player", "", "prev", "", "");
                Util.a(this.m, BLOCK_ACTION.SKIP);
                return;
                break;
            case R.id.playerBtnRepeat /*2131297997*/:
                a(view, true);
                break;
            case R.id.playerBtnShuffle /*2131297998*/:
                if (!Constants.ab) {
                    ((BaseActivity) this.m).sendGAEvent("Player", "Shuffle", "Player - Shuffle - Song");
                    if (PlayerManager.a(this.m).E() != null) {
                        PlayerManager.a(this.m).j(false);
                    }
                    e(view);
                    if (this.ah != null) {
                        this.ah.notifyDataSetChanged();
                    }
                    if (this.Z != null) {
                        this.Z.updateArrayList(PlayerManager.a(GaanaApplication.getContext()).n());
                        break;
                    }
                }
                Util.a(this.m, BLOCK_ACTION.SHUFFLE);
                return;
                break;
            case R.id.playerButton /*2131297999*/:
            case R.id.playerButtonRadio /*2131298001*/:
            case R.id.queue_panel_bottom_button /*2131298140*/:
                if (this.af) {
                    y();
                    PlayerManager.a(this.m).j(false);
                }
                if (GaanaMusicService.t() || GaanaMusicService.s().m()) {
                    u.a().b("Player", "Pause");
                    an.a().a("click", "ac", "", "player", "", "pause", "", "");
                } else {
                    u.a().b("Player", "Play");
                    an.a().a("click", "ac", "", "player", "", "play", "", "");
                }
                if (ad.a(this.m).o().booleanValue()) {
                    if (!GaanaMusicService.t()) {
                        i();
                        break;
                    } else {
                        o.d(getContext());
                        break;
                    }
                }
                i();
                break;
                break;
            case R.id.playerqueueLL /*2131298037*/:
                j();
                break;
            case R.id.qualityText /*2131298132*/:
            case R.id.streamingList /*2131298500*/:
            case R.id.streamingListLayoutRadio /*2131298501*/:
                if (this.af) {
                    y();
                    PlayerManager.a(this.m).j(false);
                }
                d(view);
                break;
            case R.id.queue_panel_bottom_options /*2131298141*/:
                b = this.mCurrentTrack.b();
                if (!(b == null || b.getBusinessObjType() == null || ad.a(this.m).p().booleanValue())) {
                    PopupWindowView instance = PopupWindowView.getInstance(this.m, null);
                    instance.setDownloadPopupListener(new DownloadPopupListener() {
                        public void onPopupClicked(String str, BusinessObject businessObject) {
                            if (DownloadManager.c().e(Integer.parseInt(str)) == DownloadStatus.DOWNLOADED) {
                                PlayerFragmentV2.this.b(businessObject);
                            } else {
                                PlayerFragmentV2.this.a(PlayerFragmentV2.this.getPlayingTrack());
                            }
                        }
                    });
                    instance.contextPopupWindow(b, true, new a() {
                        public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
                            PlayerFragmentV2.this.E();
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
                S();
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
                T();
                break;
        }
    }

    private void Q() {
        if (this.d != null) {
            this.d.cancel();
        }
        if (this.c != null) {
            this.c.cancel();
        }
        this.p.clearAnimation();
        if (!d.a().b("PREFERENCE_KEY_SLIDE_LEFT_INITIATED", false, false)) {
            this.Z.setSwipeCoachmarkAnimation();
        }
    }

    private void R() {
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

    private void S() {
        u.a().a("PlayerQueue", "Clear queue", "PlayerQueue - Clear queue");
        an.a().a("click", "ac", "", "queue", "", "clear", "", "");
        CustomDialogView customDialogView = new CustomDialogView(this.m, getString(R.string.player_and_queue_clear), new OnButtonClickListener() {
            public void onNegativeButtonClick() {
            }

            public void onPositiveButtonClick() {
                PlayerFragmentV2.this.l();
                PlayerFragmentV2.this.mPlayerManager.C();
                o.d(PlayerFragmentV2.this.m);
                if (PlayerFragmentV2.this.m instanceof GaanaActivity) {
                    ((GaanaActivity) PlayerFragmentV2.this.m).resetBottomNavigationBar();
                }
                an.a().a("click", "ac", "", "clear", "", "ok", "", "");
            }
        });
        customDialogView.getPositiveButton().setText(getString(R.string.continue_button));
        customDialogView.show();
    }

    private void T() {
        u.a().a("PlayerQueue", "Save Queue", "PlayerQueue - Save Queue");
        an.a().a("click", "ac", "", "queue", "", "save", "", "");
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

    public void i() {
        if (GaanaMusicService.t() || GaanaMusicService.s().m() || GaanaMusicService.s().l()) {
            this.M.setImageDrawable(this.s);
            this.aa.setImageDrawable(this.ao);
            o.b(this.m, PauseReasons.MEDIA_BUTTON_TAP);
            return;
        }
        this.M.setImageDrawable(this.u);
        this.aa.setImageDrawable(this.R);
        o.a(this.m);
        if (ad.a(this.m).o().booleanValue()) {
            ad.a(this.m).j();
        }
    }

    private void d(View view) {
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
                    textView.setTextColor(PlayerFragmentV2.this.J.data);
                }
                return view;
            }
        });
        final BottomSheetDialog bottomSheetDialog2 = bottomSheetDialog;
        final String[] strArr3 = strArr2;
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long j) {
                final d a = d.a();
                if (PlayerFragmentV2.this.mAppState.isAppInDataSaveMode()) {
                    ((BaseActivity) PlayerFragmentV2.this.m).displayFeatureNotAvailableDataSaveModeDialog(i, -1);
                    bottomSheetDialog2.dismiss();
                    return;
                }
                a.b("PREFERENCE_KEY_STREAMING_QUALITY", Constants.s(), false);
                aj a2;
                Context z;
                StringBuilder stringBuilder;
                if (i == 0) {
                    if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                        bottomSheetDialog2.dismiss();
                        return;
                    }
                    a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                    aj.a().a(PlayerFragmentV2.this.m, PlayerFragmentV2.this.m.getString(R.string.adjusting_sound_quality));
                    bottomSheetDialog2.dismiss();
                    PlayerFragmentV2.this.W();
                    PlayerFragmentV2.this.g = true;
                    PlayerFragmentV2.this.h = PlayerFragmentV2.this.U.getProgress();
                    o.b(PlayerFragmentV2.this.getContext(), 1);
                    u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                    an.a().a("click", "ac", String.valueOf(PlayerFragmentV2.this.X.getText()), "player", strArr[i], "", "", "");
                } else if (i == 1) {
                    if (!ap.a().s()) {
                        u.a().a("Mini Player", "Set Streaming Quality", "Trial HD (Gaana+ only)");
                        an.a().a("click", "ac", String.valueOf(PlayerFragmentV2.this.X.getText()), "player", strArr[i], "", "", "");
                        bottomSheetDialog2.dismiss();
                        Util.a(PlayerFragmentV2.this.m, PlayerFragmentV2.this.m.getResources().getString(R.string.subscribe_gaanaplus_hdq_msg), "HDQuality", new as() {
                            public void onTrialSuccess() {
                                a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                                PlayerFragmentV2.this.U();
                            }
                        });
                    } else if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                        bottomSheetDialog2.dismiss();
                    } else {
                        a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                        a2 = aj.a();
                        z = PlayerFragmentV2.this.m;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(PlayerFragmentV2.this.m.getString(R.string.changing_sound_quality));
                        stringBuilder.append(strArr[i]);
                        a2.a(z, stringBuilder.toString());
                        bottomSheetDialog2.dismiss();
                        PlayerFragmentV2.this.W();
                        PlayerFragmentV2.this.g = true;
                        PlayerFragmentV2.this.h = PlayerFragmentV2.this.U.getProgress();
                        o.b(PlayerFragmentV2.this.getContext(), 1);
                        u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                        an.a().a("click", "ac", String.valueOf(PlayerFragmentV2.this.X.getText()), "player", strArr[i], "", "", "");
                    }
                } else if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                    bottomSheetDialog2.dismiss();
                } else {
                    a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                    a2 = aj.a();
                    z = PlayerFragmentV2.this.m;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(PlayerFragmentV2.this.m.getString(R.string.changing_sound_quality));
                    stringBuilder.append(strArr[i]);
                    a2.a(z, stringBuilder.toString());
                    bottomSheetDialog2.dismiss();
                    PlayerFragmentV2.this.W();
                    PlayerFragmentV2.this.g = true;
                    PlayerFragmentV2.this.h = PlayerFragmentV2.this.U.getProgress();
                    o.b(PlayerFragmentV2.this.getContext(), 1);
                    u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                    an.a().a("click", "ac", String.valueOf(PlayerFragmentV2.this.X.getText()), "player", strArr[i], "", "", "");
                }
            }
        });
        bottomSheetDialog.show();
    }

    private void U() {
        boolean z = ad.a(this.m).o().booleanValue() || GaanaApplication.getInstance().isAppInOfflineMode() || !Util.j(this.m);
        if (!(this.mCurrentTrack == null || this.mCurrentTrack.a(true) == null)) {
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(this.mCurrentTrack.a(true).getBusinessObjId()));
            if (this.mCurrentTrack.a(true).isLocalMedia() || ((ap.a().o() || DownloadManager.c().j(this.mCurrentTrack.a(true).getBusinessObjId()).booleanValue()) && e != null && e == DownloadStatus.DOWNLOADED)) {
                z = true;
            }
        }
        if (z) {
            this.X.setVisibility(8);
            return;
        }
        this.X.setVisibility(8);
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
                    charSequence = this.m.getString(R.string.med_small);
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
        this.X.setText(charSequence);
    }

    public void j() {
        this.n = PopupWindowView.getInstance(this.m, ((GaanaActivity) this.m).getCurrentFragment());
        this.n.populatePlayerQueue(getPlayingTrack(), this.Z);
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
                            af.a(PlayerFragmentV2.this.m, null).a((int) R.id.downloadMenu, businessObject);
                            currentFragment.showSnackbartoOpenMyMusic();
                            ((GaanaActivity) PlayerFragmentV2.this.m).updateSideBar();
                        }
                    });
                }
            }
        }
    }

    public void k() {
        if (this.A == null || this.A.getTag() == null || ((Integer) this.A.getTag()).intValue() != 1) {
            this.mPlayerManager.h(false);
            return;
        }
        this.A.setTag(InternalAvidAdSessionContext.AVID_API_LEVEL);
        a(this.A, true);
    }

    public void l() {
        if (this.A != null && this.A.getTag() != null) {
            this.A.setTag(InternalAvidAdSessionContext.AVID_API_LEVEL);
            a(this.A, true);
        }
    }

    private void a(View view, boolean z) {
        int parseInt = Integer.parseInt(view.getTag().toString());
        if (parseInt == 2) {
            parseInt = 0;
        } else {
            parseInt++;
            if (parseInt == 1) {
                this.mPlayerManager.i(true);
            } else {
                this.mPlayerManager.i(false);
            }
        }
        if (this.mPlayerManager.y()) {
            this.mPlayerManager.h(false);
            parseInt = 0;
        }
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
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("repeat");
            stringBuilder.append(str);
            an.a().a("click", "ac", "", "player", "", stringBuilder.toString(), "", "");
        }
        d.a().a("PREFERENCE_KEY_REPEAT_STATUS", parseInt, true);
    }

    private void e(View view) {
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
                an.a().a("click", "ac", "", "player", "off", "shuffle", "", "");
                break;
            case 1:
                PlayerManager.a(getContext()).a(true, null);
                an.a().a("click", "ac", "", "player", "on", "shuffle", "", "");
                break;
        }
        if (this.af) {
            C();
            if (this.Z != null && this.mPlayerManager.m() == PlayerType.GAANA) {
                this.Z.getRecyclerView().setVisibility(0);
                this.Z.updateArrayList(PlayerManager.a(this.m).n());
                this.ah.updateAndNotifyArrayList(PlayerManager.a(this.m).n());
                return;
            } else if (this.Z != null && this.mPlayerManager.m() == PlayerType.GAANA_RADIO) {
                this.Z.getRecyclerView().setVisibility(8);
                return;
            } else {
                return;
            }
        }
        d();
    }

    public void a(BusinessObject businessObject, String str) {
        if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Similar songs to \"");
            stringBuilder.append(str);
            stringBuilder.append("\"");
            this.az = new SimilarItemHorizontalScroll(this.m, ((GaanaActivity) this.m).getCurrentFragment(), new ai() {
                public void a(Track track, int i) {
                    track.setBusinessObjType(BusinessObjectType.Tracks);
                    PlayerFragmentV2.this.ah.enqueTrack(track, true, false);
                    PlayerFragmentV2.this.a(PlayerFragmentV2.this.c(), PlayerFragmentV2.this.mPlayerManager.s());
                    an.a().a("click", "en", "", "queue", track.getBusinessObjId(), "rec", "", String.valueOf(i));
                }
            }).populateSimilar(businessObject, TrackItemView.class.getName(), stringBuilder.toString());
            if (this.Z.getCurrentView() != null) {
                this.aA = (LinearLayout) this.K.findViewById(R.id.similarSongsLayout);
                if (this.aA != null && this.az != null && this.az != null) {
                    this.aA.addView(this.az);
                    m();
                }
            }
        }
    }

    private void V() {
        if (this.aA != null) {
            this.aA.setVisibility(0);
            this.aA.bringToFront();
            this.aA.animate().translationY(0.0f).setDuration(500).start();
            this.U.setVisibility(4);
        }
    }

    public void m() {
        if (this.aA != null) {
            this.aA.animate().translationY((float) Util.b(200)).setDuration(500).start();
            this.U.setVisibility(0);
        }
    }

    public void n() {
        if (this.aA != null) {
            this.aA.removeAllViews();
        }
        if (this.Z.getRecyclerView() != null) {
            ((FrameLayout.LayoutParams) this.Z.getRecyclerView().getLayoutParams()).bottomMargin = Util.b(0);
        }
    }

    private void a(boolean z, PlayerCommands playerCommands) {
        switch (playerCommands) {
            case PLAY_NEXT:
                if (!z) {
                    this.mCurrentTrack = this.mPlayerManager.a(this.mPlayerManager.s());
                    this.k.removeCallbacksAndMessages(null);
                    K();
                    this.M.setImageDrawable(this.u);
                    this.aa.setImageDrawable(this.R);
                    return;
                } else if (GaanaMusicService.s().l() || GaanaMusicService.s().k()) {
                    this.M.setImageDrawable(this.u);
                    this.aa.setImageDrawable(this.R);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private void W() {
        this.mCurrentTrack = PlayerManager.a(this.m).i();
        z();
        if (this.mCurrentTrack != null) {
            a(this.mCurrentTrack);
        }
        g();
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
        if (this.ah != null) {
            this.ah.notifyDataSetChanged();
        }
        if (this.Z != null) {
            this.Z.updateQueueStatus();
        }
        ((GaanaActivity) this.m).updateMiniPlayerList();
    }

    public PlayerStates o() {
        return this.mPlayerStates;
    }

    private void X() {
        this.S = (ProgressBar) this.K.findViewById(R.id.progressBarPlayer);
        this.D = (ImageView) this.K.findViewById(R.id.playerBtnNext);
        this.M = (ImageView) this.K.findViewById(R.id.playerButton);
        this.V = this.K.findViewById(R.id.streamingList);
        this.W = (ImageView) this.K.findViewById(R.id.videoButton);
        this.aG = this.K.findViewById(R.id.videoButtonText);
        this.X = (TextView) this.K.findViewById(R.id.qualityText);
        this.F = (TextView) this.K.findViewById(R.id.tvPlayerStartTimer);
        this.H = (TextView) this.K.findViewById(R.id.tvPlayerDragTimer);
        this.D.setOnClickListener(this);
        this.M.setOnClickListener(this);
        this.V.setOnClickListener(this);
        this.X.setOnClickListener(this);
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
                if (o() == PlayerStates.PLAYING && GaanaMusicService.s().isPlaying()) {
                    AnimationDrawable animationDrawable = (AnimationDrawable) ContextCompat.getDrawable(this.m, i);
                    imageView.setImageDrawable(animationDrawable);
                    if (!this.ae.e()) {
                        imageView.setVisibility(0);
                    }
                    animationDrawable.start();
                } else {
                    if (imageView.getAnimation() != null) {
                        imageView.getAnimation().cancel();
                    }
                    if (!this.ae.e()) {
                        imageView.setVisibility(0);
                    }
                    imageView.setImageResource(i2);
                }
                if (!this.ay && this.aa != null) {
                    this.aa.setVisibility(8);
                }
            } else if (imageView.getVisibility() == 0) {
                imageView.setVisibility(8);
                if (imageView.getAnimation() != null) {
                    imageView.getAnimation().cancel();
                }
                if (this.aa != null) {
                    this.aa.setVisibility(0);
                }
            }
        }
    }

    private void a(Track track) {
        if (track != null) {
            getSubtitleText(track.getAlbumTitle(), track.getArtistNames()).trim();
            if (Constants.aG) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.m.getString(R.string.CASTING_TO));
                stringBuilder.append(Constants.aH);
                stringBuilder.toString();
            }
            this.mPlayerManager.b(track);
        }
        if (TextUtils.isEmpty(this.av.getText()) || TextUtils.isEmpty(this.aw.getText())) {
            CharSequence trim = getSubtitleText(getPlayingTrack().getAlbumTitle(), getPlayingTrack().getArtistNames()).trim();
            if (Constants.aG) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(this.m.getString(R.string.CASTING_TO));
                stringBuilder2.append(Constants.aH);
                trim = stringBuilder2.toString();
            }
            this.av.setText(getPlayingTrack().getName());
            this.aw.setSelected(true);
            this.aw.setText(trim);
            if (getPlayingTrack() != null) {
                a((ImageView) this.K.findViewById(R.id.queue_panel_img_animation), getPlayingTrack());
            }
        }
    }

    public void b(ADSTATUS adstatus) {
        this.aH = true;
        v();
    }

    public void c(ADSTATUS adstatus) {
        this.aH = false;
    }

    public void a(boolean z) {
        TypedArray obtainStyledAttributes = this.m.obtainStyledAttributes(R.styleable.VectorDrawables);
        if (z) {
            this.E.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(74, -1)));
            this.G.setAlpha(1.0f);
            this.E.setClickable(true);
            this.G.setClickable(true);
            this.G.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PlayerFragmentV2.this.V();
                }
            });
            this.E.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PlayerFragmentV2.this.V();
                }
            });
        } else {
            this.E.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(73, -1)));
            this.G.setAlpha(0.2f);
            this.E.setClickable(false);
            this.G.setClickable(false);
        }
        obtainStyledAttributes.recycle();
    }

    private void b(Track track) {
        if (track.getLyricsUrl() == null || TextUtils.isEmpty(track.getLyricsUrl()) || track.isLocalMedia()) {
            this.ad.setVisibility(8);
        } else {
            this.ad.setAlpha(1.0f);
            this.ad.setClickable(true);
            this.ad.setVisibility(0);
            this.ad.setAlpha(0.0f);
            this.ad.animate().alpha(1.0f).setDuration(1000).start();
        }
        this.ad.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerFragmentV2.this.R();
            }
        });
    }

    private void c(final Track track) {
        TypedArray obtainStyledAttributes = this.m.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.W.setTag(track);
        this.aG.setTag(track);
        if (track != null && this.W != null) {
            if (!Constants.cF || TextUtils.isEmpty(track.getYoutubeId())) {
                TextView textView = (TextView) this.K.findViewById(R.id.videoButtonText);
                if (Constants.l) {
                    textView.setTextColor(getResources().getColor(R.color.black_alfa_20));
                } else {
                    textView.setTextColor(getResources().getColor(R.color.white_alfa_20));
                }
                this.W.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(71, -1)));
                this.W.setClickable(false);
                this.aG.setClickable(false);
                an.a().a("click", "ac", "", "player", "", "video", "", AvidBridge.APP_STATE_INACTIVE);
                return;
            }
            this.W.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(72, -1)));
            TextView textView2 = (TextView) this.K.findViewById(R.id.videoButtonText);
            if (Constants.l) {
                textView2.setTextColor(getResources().getColor(R.color.black));
            } else {
                textView2.setTextColor(getResources().getColor(R.color.white));
            }
            this.W.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Track b = PlayerManager.a(PlayerFragmentV2.this.m).i().b();
                    u.a().b("Player", "Video Played");
                    Util.a(PlayerFragmentV2.this.m, b.getYoutubeId(), "", track, 0);
                }
            });
            this.aG.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Track b = PlayerManager.a(PlayerFragmentV2.this.m).i().b();
                    u.a().b("Player", "Video Played");
                    Util.a(PlayerFragmentV2.this.m, b.getYoutubeId(), "", track, 0);
                }
            });
            this.W.setClickable(true);
            this.aG.setClickable(true);
            an.a().a("click", "ac", "", "player", "", "video", "", "active");
        }
    }

    public void on_enque() {
        if (isAdded()) {
            this.ah.updateArrayList(PlayerManager.a(this.m).n());
            W();
        }
    }

    public void refreshList() {
        if (isAdded() && this.mCurrentTrack != null) {
            W();
            U();
            this.g = true;
            this.h = this.U.getProgress();
            o.b(getContext(), 1);
        }
    }

    public void p() {
        this.ah.notifyItemChanged(c());
    }

    public void on_deque() {
        if (isAdded()) {
            if (PlayerManager.a(this.m).n() == null || PlayerManager.a(this.m).n().size() <= 0) {
                this.ae.setPanelSlideListener(null);
                ((GaanaActivity) this.m).popBackStackImmediate();
                ((GaanaActivity) this.m).getSlidingPanelLayout().a(3);
            } else {
                this.ah.updateArrayList(PlayerManager.a(this.m).n());
                if (this.m != null) {
                    ((Activity) this.m).runOnUiThread(new Runnable() {
                        public void run() {
                            PlayerFragmentV2.this.W();
                            if (PlayerManager.a(PlayerFragmentV2.this.m).l() <= 0) {
                                PlayerFragmentV2.this.d();
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
        E();
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
                PlayerFragmentV2.this.W();
            }
        }).show();
    }

    public void onPopupClicked(String str, BusinessObject businessObject) {
        if (DownloadManager.c().e(Integer.parseInt(str)) == DownloadStatus.DOWNLOADED) {
            b(businessObject);
        } else {
            a(businessObject);
        }
    }

    public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
        c(this.aK);
        if (com.managers.n.a().a(businessObject)) {
            an.a().a("click", "ac", "three dot menu", "player", "", "fav", "", "");
        } else {
            an.a().a("click", "ac", "three dot menu", "player", "", "unfav", "", "");
        }
    }

    public void b() {
        ((GaanaActivity) this.m).popBackStackImmediate();
        Fragment miniPlayer = ((GaanaActivity) this.m).getMiniPlayer();
        if (miniPlayer != null && (miniPlayer instanceof MiniPlayerFragment)) {
            ((MiniPlayerFragment) miniPlayer).p();
        } else if (miniPlayer != null && (miniPlayer instanceof MiniPlayerFragmentV4)) {
            ((MiniPlayerFragmentV4) miniPlayer).p();
        }
    }
}
