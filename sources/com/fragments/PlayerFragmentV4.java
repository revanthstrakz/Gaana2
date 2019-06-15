package com.fragments;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.actionbar.PlayerMaterialActionBar;
import com.actionbar.PlayerMaterialActionBar.PlayerVersion;
import com.constants.Constants;
import com.constants.Constants.ErrorType;
import com.facebook.share.internal.ShareConstants;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.adapter.CardPagerAdapterV4;
import com.gaana.adapter.CardPagerAdapterV4.CardViewHolder;
import com.gaana.adapter.CardPagerAdapterV4.IOptionsLayoutPositionListener;
import com.gaana.adapter.MusicQueueAdapterV2.IUpdatePlayer;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.juke.JukePartyFragment;
import com.gaana.juke.JukeSessionManager;
import com.gaana.lrc.DefaultLrcBuilder;
import com.gaana.lrc.ILrcView.LrcViewListener;
import com.gaana.lrc.LrcRow;
import com.gaana.lrc.LrcView;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.LyricsObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Tracks.Track;
import com.gaana.view.CustomTextView;
import com.gaana.view.DiscreteScrollLayoutManager;
import com.gaana.view.DiscreteScrollView;
import com.gaana.view.DownloadClickAnimation;
import com.gaana.view.GaanaYourYearView.GAANA_ENTRY_PAGE;
import com.gaana.view.PlayerQueueViewV2;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.gaana.view.item.PopupWindowView;
import com.gaana.view.item.SimilarItemHorizontalScroll;
import com.gaana.view.item.TrackItemView;
import com.gaanavideo.VideoCoachmarkActivity;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.integralads.avid.library.inmobi.AvidBridge;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.library.managers.TaskManager.TaskListner;
import com.managers.ColombiaAdViewManager;
import com.managers.ColombiaAdViewManager.ADSTATUS;
import com.managers.ColombiaAdViewManager.b;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlaySequenceType;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
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
import com.services.h;
import com.services.l.af;
import com.services.l.ai;
import com.services.l.al;
import com.services.l.as;
import com.services.l.ax;
import com.services.l.q;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;
import com.utilities.k;
import com.views.QueueSlidingUpPanelLayout;
import com.views.i;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class PlayerFragmentV4 extends BaseFragment implements OnClickListener, IUpdatePlayer, DownloadPopupListener, b, a, q {
    private static final int bb = Util.b(128);
    private Drawable A;
    private Drawable B;
    private int[] C = new int[]{R.drawable.vector_player_shuffle_v4_white, R.drawable.vector_player_shuffle_v4_active};
    private int[] D = new int[]{R.drawable.vector_player_repeat_v4_white, R.drawable.vector_player_repeat_one_v4, R.drawable.vector_player_repeat_v4_active};
    private FrameLayout E;
    private ConstraintLayout F;
    private ImageView G;
    private ImageView H;
    private ImageView I;
    private ImageView J;
    private ImageView K;
    private ImageView L;
    private TextView M;
    private TextView N;
    private TextView O;
    private TextView P;
    private TypedValue Q;
    private View R;
    private PlayerMaterialActionBar S;
    private ImageView T = null;
    private Drawable U;
    private ProgressBar V;
    private ProgressBar W;
    private SeekBar X;
    private SeekBar Y;
    private View Z;
    int[] a = new int[]{R.attr.miniplayer_pause, R.attr.miniplayer_play};
    private DownloadClickAnimation aA;
    private ProgressBar aB;
    private TextView aC;
    private TextView aD;
    private boolean aE = true;
    private boolean aF = false;
    private View aG = null;
    private LinearLayout aH;
    private int aI = -1;
    private TextView aJ;
    private TextView aK;
    private boolean aL = false;
    private int aM = -1;
    private View aN;
    private boolean aO = false;
    private boolean aP = false;
    private int aQ;
    private String aR;
    private TextView aS;
    private ConstraintLayout aT;
    private LrcView aU;
    private ImageView aV;
    private LinearLayout aW;
    private TextView aX;
    private String aY;
    private boolean aZ = false;
    private ImageView aa;
    private TextView ab;
    private ImageView ac;
    private ImageView ad;
    private ImageView ae;
    private ImageView af;
    private PlayerQueueViewV2 ag;
    private ImageView ah;
    private TextView ai;
    private TextView aj;
    private TextView ak;
    private TextView al;
    private QueueSlidingUpPanelLayout am;
    private boolean an = false;
    private boolean ao = false;
    private CardPagerAdapterV4 ap;
    private DiscreteScrollView aq;
    private ImageView ar;
    private View as;
    private TextView at;
    private View au;
    private Drawable av;
    private boolean aw;
    private PublisherAdView ax;
    private boolean ay;
    private int az = 0;
    boolean b = false;
    private Handler bA = new Handler();
    private Runnable bB = new Runnable() {
        public void run() {
            PlayerFragmentV4.this.V();
            PlayerFragmentV4.this.ap.setShouldUpdate(true);
            if (PlayerManager.a(GaanaApplication.getContext()).n() != null && PlayerManager.a(GaanaApplication.getContext()).s() <= PlayerManager.a(GaanaApplication.getContext()).n().size()) {
                PlayerFragmentV4.this.aq.scrollToPosition(PlayerManager.a(GaanaApplication.getContext()).s());
                PlayerFragmentV4.this.aL = true;
            }
            PlayerFragmentV4.this.aE = false;
            PlayerFragmentV4.this.b(PlayerFragmentV4.this.E);
            PlayerFragmentV4.this.bl = true;
        }
    };
    private m ba = new m() {
        public void onInfo(f fVar, int i, int i2) {
        }

        public void onPrepared(f fVar) {
            if (!PlayerFragmentV4.this.isActivityDestroyed()) {
                PlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (GaanaMusicService.s().isPlaying()) {
                            PlayerFragmentV4.this.mPlayerStates = PlayerStates.PLAYING;
                            PlayerFragmentV4.this.l();
                            PlayerFragmentV4.this.a(PlayerFragmentV4.this.aq.findViewHolderForAdapterPosition(PlayerFragmentV4.this.mPlayerManager.s()), PlayerFragmentV4.this.mPlayerManager.s());
                        }
                        if (PlayerFragmentV4.this.m) {
                            PlayerFragmentV4.this.aa();
                            PlayerFragmentV4.this.x();
                            PlayerFragmentV4.this.m = false;
                            return;
                        }
                        PlayerFragmentV4.this.Y();
                        PlayerFragmentV4.this.x();
                    }
                });
            }
        }

        public void onError(f fVar, int i, int i2) {
            if (!PlayerFragmentV4.this.isActivityDestroyed()) {
                if (i == -1000 || i == -1001) {
                    PlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            PlayerFragmentV4.this.ag();
                        }
                    });
                }
            }
        }

        public void onAdEventUpdate(f fVar, AdEvent adEvent) {
            switch (adEvent.getType()) {
                case CONTENT_PAUSE_REQUESTED:
                    PlayerFragmentV4.this.aP = true;
                    return;
                case CONTENT_RESUME_REQUESTED:
                    PlayerFragmentV4.this.ac();
                    if (PlayerFragmentV4.this.ap != null) {
                        int g = PlayerFragmentV4.this.g();
                        PlayerFragmentV4.this.ap.updatePlaybackState(PlayerFragmentV4.this.aq.getViewHolder(g), g);
                    }
                    PlayerFragmentV4.this.aP = false;
                    return;
                default:
                    return;
            }
        }

        public void onCompletion(f fVar) {
            PlayerFragmentV4.this.mPlayerStates = PlayerStates.STOPPED;
            PlayerFragmentV4.this.B();
            ((GaanaActivity) PlayerFragmentV4.this.s).getWindow().clearFlags(128);
        }

        public void onBufferingUpdate(final f fVar, final int i) {
            if (!PlayerFragmentV4.this.isActivityDestroyed()) {
                PlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (PlayerStatus.a(PlayerFragmentV4.this.getContext()).c()) {
                            PlayerFragmentV4.this.a(fVar, i);
                        }
                    }
                });
            }
        }
    };
    private int bc = -1;
    private int bd = 100;
    private float be;
    private float bf;
    private float bg;
    private float bh;
    private int bi;
    private boolean bj = true;
    private int bk = 0;
    private boolean bl = false;
    private boolean bm = false;
    private boolean bn = false;
    private boolean bo = false;
    private boolean bp = false;
    private View bq;
    private float br = 0.0f;
    private int bs = -1;
    private int bt;
    private int bu;
    private Drawable bv;
    private Drawable bw;
    private Drawable bx;
    private Drawable by;
    private boolean bz = false;
    boolean c = false;
    n d = new n() {
        public void displayErrorToast(String str, int i) {
        }

        public void onPlayerRepeatReset(boolean z) {
        }

        public void onStreamingQualityChanged(int i) {
        }

        public void onPlayerPlay() {
            if (!PlayerFragmentV4.this.isActivityDestroyed()) {
                PlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV4.this.ad();
                    }
                });
            }
        }

        public void onPlayerPause() {
            if (!PlayerFragmentV4.this.isActivityDestroyed()) {
                PlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV4.this.ae();
                    }
                });
            }
        }

        public void onPlayerResume() {
            if (!PlayerFragmentV4.this.isActivityDestroyed()) {
                PlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV4.this.af();
                    }
                });
            }
        }

        public void onPlayerStop() {
            if (!PlayerFragmentV4.this.isActivityDestroyed()) {
                PlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV4.this.ag();
                    }
                });
            }
        }

        public void onPlayPrevious(final boolean z, final boolean z2) {
            if (!PlayerFragmentV4.this.isActivityDestroyed()) {
                PlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV4.this.a(z, z2);
                    }
                });
            }
        }

        public void onPlayNext(final boolean z, final boolean z2) {
            if (!PlayerFragmentV4.this.isActivityDestroyed()) {
                PlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV4.this.b(z, z2);
                    }
                });
            }
        }

        public void displayErrorDialog(String str, ErrorType errorType) {
            if (!PlayerFragmentV4.this.isActivityDestroyed()) {
                PlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerFragmentV4.this.ag();
                    }
                });
            }
        }
    };
    TranslateAnimation e;
    TranslateAnimation f;
    TimerTask g;
    Timer h = new Timer();
    public ax i;
    boolean j = false;
    private int k;
    private boolean l;
    private boolean m;
    private int n = 0;
    private long o = 0;
    private Toolbar p;
    private final Handler q = new Handler();
    private i r;
    private Context s;
    private PopupWindowView t;
    private PopupWindow u;
    private ViewGroup v;
    private Drawable w;
    private Drawable x;
    private Drawable y;
    private Drawable z;

    private void U() {
    }

    private void f(boolean z) {
    }

    public void a(ADSTATUS adstatus) {
    }

    public void a(PlayerType playerType) {
    }

    public void c(ADSTATUS adstatus) {
    }

    public void d(ADSTATUS adstatus) {
    }

    public void onPlayerStateChanged() {
    }

    public void onRadioTracksFetched(boolean z) {
    }

    private void x() {
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(getPlayingTrack().getBusinessObjId()));
        if (GaanaMusicService.s().m()) {
            if (this.az != 0) {
                this.aB.setVisibility(0);
            } else if (e == DownloadStatus.DOWNLOADING) {
                this.W.setVisibility(8);
            } else {
                this.W.setVisibility(0);
            }
            this.ah.setVisibility(8);
            this.V.setVisibility(0);
            this.T.setVisibility(4);
            int i = this.az;
        } else if (GaanaMusicService.t()) {
            ((ProgressBar) this.R.findViewById(R.id.queue_panel_bottom_progressBar)).setVisibility(8);
            this.aB.setVisibility(8);
            this.ah.setVisibility(0);
            this.ah.setImageDrawable(this.av);
            this.V.setVisibility(8);
            this.T.setVisibility(0);
            this.T.setImageDrawable(this.y);
            if (this.az == 0) {
                this.ah.setVisibility(8);
            }
        } else {
            this.W.setVisibility(8);
            this.aB.setVisibility(8);
            this.ah.setVisibility(0);
            this.ah.setImageDrawable(this.U);
            this.V.setVisibility(8);
            this.T.setVisibility(0);
            this.T.setImageDrawable(this.A);
        }
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    private void y() {
        int b = d.a().b("PREFERENCE_UJ_MINI_V4_ENABLED", 0, false);
        if (Constants.P != b) {
            ((GaanaActivity) this.s).initBottomNavigationBar();
            ((GaanaActivity) this.s).initMiniPlayer();
            Constants.P = b;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.s = getActivity();
        this.aZ = false;
        ((GaanaActivity) this.s).setmCurrentPlayerFragment(this);
        this.aE = true;
        this.R = layoutInflater.inflate(R.layout.fragment_player_v4, viewGroup, false);
        this.S = new PlayerMaterialActionBar(getContext(), PlayerVersion.PlayerV4);
        this.ab = (TextView) this.R.findViewById(R.id.qualityText);
        this.ac = (ImageView) this.S.findViewById(R.id.menu_icon);
        this.ad = (ImageView) this.S.findViewById(R.id.menu_icon_back);
        this.ae = (ImageView) this.S.findViewById(R.id.gaana_logo_header);
        this.af = (ImageView) this.S.findViewById(R.id.report_lrc_button);
        ((GaanaActivity) getContext()).setmCurrentPlayerFragment(this);
        Toolbar toolbar = (Toolbar) this.R.findViewById(R.id.toolbar);
        toolbar.addView(this.S);
        toolbar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        toolbar.setContentInsetsAbsolute(0, 0);
        setGAScreenName("PlayerHomeScreen", "PlayerHomeScreen");
        ((GaanaActivity) this.s).getSlidingPanelLayout().b(1);
        return this.R;
    }

    private void z() {
        URLManager uRLManager = new URLManager();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://apiv2.gaana.com/lyrics/url?track_id=");
        stringBuilder.append(this.mCurrentTrack.b().getBusinessObjId());
        uRLManager.a(stringBuilder.toString());
        uRLManager.a(LyricsObject.class);
        com.i.i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
                if (obj instanceof LyricsObject) {
                    LyricsObject lyricsObject = (LyricsObject) obj;
                    PlayerFragmentV4.this.a(lyricsObject.getLyricsUrl(), lyricsObject.getLyricsTypeString());
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                PlayerFragmentV4.this.a(null, null);
            }
        }, uRLManager);
    }

    private void A() {
        this.mCurrentTrack = this.mPlayerManager.i();
        if (this.mCurrentTrack == null || this.mCurrentTrack.b() == null) {
            a(null, null);
        } else {
            String lyricsType = this.mCurrentTrack.b().getLyricsType();
            if (TextUtils.isEmpty(lyricsType)) {
                z();
            } else {
                this.aR = this.mCurrentTrack.b().getLyricsUrl();
                a(this.aR, lyricsType);
            }
        }
    }

    private void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            this.aQ = 0;
        } else {
            this.aR = str;
            if (TextUtils.isEmpty(str2)) {
                this.aQ = 1;
            } else if (str2.equalsIgnoreCase("lrc")) {
                this.aQ = 2;
            } else if (str2.equalsIgnoreCase("txt")) {
                this.aQ = 3;
            } else {
                this.aQ = 1;
            }
        }
        switch (this.aQ) {
            case 0:
                this.aT.setVisibility(8);
                this.aS.setVisibility(8);
                this.mPlayerManager.a(null);
                this.aU.setLrc(null);
                this.ak.setVisibility(8);
                return;
            case 1:
                this.aT.setVisibility(8);
                this.aS.setVisibility(8);
                this.mPlayerManager.a(null);
                this.aU.setLrc(null);
                this.ak.setVisibility(0);
                return;
            case 2:
                if (GaanaApplication.getInstance().getLyricsDisplay()) {
                    F();
                    return;
                }
                return;
            case 3:
                if (GaanaApplication.getInstance().getLyricsDisplay()) {
                    F();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void B() {
        ((GaanaActivity) this.s).getWindow().clearFlags(128);
        c();
        this.aT.setVisibility(8);
        this.aU.setClickable(false);
        this.aS.setVisibility(8);
        this.aS.setClickable(true);
        this.mPlayerManager.a(null);
        this.aU.setLrc(null);
        this.c = false;
    }

    private void C() {
        this.ak = (TextView) this.R.findViewById(R.id.lyricsTextButton);
        this.ak.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!Util.j(PlayerFragmentV4.this.s) || PlayerFragmentV4.this.mAppState.isAppInOfflineMode()) {
                    ap.a().f(PlayerFragmentV4.this.s);
                    return;
                }
                ((BaseActivity) PlayerFragmentV4.this.s).sendGAEvent("Player", "Lyrics", "Player - Lyrics");
                Intent intent = new Intent(PlayerFragmentV4.this.s, WebViewActivity.class);
                intent.putExtra("EXTRA_WEBVIEW_URL", PlayerFragmentV4.this.aR);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                intent.putExtra("EXTRA_SHOW_WEB_BARS", true);
                intent.putExtra("title", "Lyrics");
                PlayerFragmentV4.this.s.startActivity(intent);
            }
        });
        this.aS = (TextView) this.R.findViewById(R.id.lyrics_text_view);
        this.aS.setMovementMethod(new ScrollingMovementMethod());
        this.aS.setTypeface(Util.h(this.s));
        this.aS.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerFragmentV4.this.D();
            }
        });
        this.aT = (ConstraintLayout) this.R.findViewById(R.id.lrc_container);
        this.aU = (LrcView) this.R.findViewById(R.id.main_lrc_view);
        this.aV = (ImageView) this.R.findViewById(R.id.lrcplay_icon);
        this.aX = (TextView) this.R.findViewById(R.id.highlightrow_time);
        this.aW = (LinearLayout) this.R.findViewById(R.id.lyrics_banner);
        ((TextView) this.aW.findViewById(R.id.banner_text)).setTypeface(Util.h(this.s));
        this.aW.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PlayerFragmentV4.this.mPlayerManager == null || PlayerFragmentV4.this.mPlayerManager.o() == null || PlayerFragmentV4.this.mPlayerManager.o().size() <= 0) {
                    aj.a().a(PlayerFragmentV4.this.s, "Sorry! Lyrics are not availble for this track");
                    return;
                }
                BaseGaanaFragment lyricsBannerFragment = new LyricsBannerFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("lrc_type", Integer.valueOf(PlayerFragmentV4.this.aQ));
                lyricsBannerFragment.setArguments(bundle);
                ((GaanaActivity) PlayerFragmentV4.this.s).displayFragment(lyricsBannerFragment);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Lyrics Banner");
                stringBuilder.append(PlayerFragmentV4.this.aQ);
                u.a().b("Lyrics", stringBuilder.toString());
            }
        });
        this.aV.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                u.a().b("Lyrics", "Lyrics Seek Play");
                PlayerFragmentV4.this.aV.setVisibility(8);
                PlayerFragmentV4.this.aX.setVisibility(8);
                if (!(GaanaMusicService.t() || GaanaMusicService.s().m() || GaanaMusicService.s().l())) {
                    PlayerFragmentV4.this.n();
                }
                PlayerFragmentV4.this.aU.seekLrc();
            }
        });
        this.aU.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerFragmentV4.this.aV.setVisibility(8);
                PlayerFragmentV4.this.aX.setVisibility(8);
                PlayerFragmentV4.this.E();
            }
        });
        this.aU.setListener(new LrcViewListener() {
            public void onLrcSeeked(int i, LrcRow lrcRow) {
                o.a(PlayerFragmentV4.this.getContext(), (int) lrcRow.time);
                PlayerFragmentV4.this.X.setSecondaryProgress((int) lrcRow.time);
            }

            public void onLrcClicked() {
                PlayerFragmentV4.this.aV.setVisibility(8);
                PlayerFragmentV4.this.aX.setVisibility(8);
            }

            public void onLrcScrollStateChanged(boolean z) {
                if (z) {
                    PlayerFragmentV4.this.aV.setVisibility(0);
                    PlayerFragmentV4.this.aX.setVisibility(0);
                    PlayerFragmentV4.this.aX.setText(PlayerFragmentV4.this.aU.getHighlightRowTime());
                    return;
                }
                PlayerFragmentV4.this.aV.setVisibility(8);
                PlayerFragmentV4.this.aX.setVisibility(8);
            }
        });
        this.aU.setClickable(false);
    }

    public void a(Track track) {
        if (track != null && track.getBusinessObjId().equalsIgnoreCase(this.mCurrentTrack.h()) && (this.am == null || this.am.f())) {
            a();
            return;
        }
        BaseGaanaFragment lyricsDisplayFragment = new LyricsDisplayFragment();
        LyricsObject a = Util.a(track);
        Bundle bundle = new Bundle();
        bundle.putSerializable("lyrics_object", a);
        lyricsDisplayFragment.setArguments(bundle);
        ((GaanaActivity) this.s).displayFragment(lyricsDisplayFragment);
    }

    public void a() {
        if (!this.aZ) {
            if (this.aQ == 2) {
                E();
            } else if (this.aQ == 3) {
                D();
            }
        }
    }

    public void c() {
        if (!this.aZ) {
            return;
        }
        if (this.aQ == 2) {
            E();
        } else if (this.aQ == 3) {
            D();
        }
    }

    private void D() {
        LayoutParams layoutParams = (LayoutParams) this.aS.getLayoutParams();
        if (this.aZ) {
            layoutParams.height = bb;
            layoutParams.setMargins(Util.b(56), 0, Util.b(56), Util.b((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
            this.aW.setVisibility(4);
            this.aS.requestLayout();
            e(false);
            return;
        }
        layoutParams.height = -1;
        layoutParams.setMargins(Util.b(56), Util.b(86), 0, Util.b(120));
        if (Constants.aY) {
            this.aW.setVisibility(0);
        }
        this.aS.requestLayout();
        e(true);
    }

    private void E() {
        LayoutParams layoutParams;
        if (this.aZ) {
            layoutParams = (LayoutParams) this.aT.getLayoutParams();
            layoutParams.height = bb;
            layoutParams.setMargins(0, 0, 0, Util.b((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
            this.aW.setVisibility(4);
            this.aZ = false;
            this.aT.requestLayout();
            e(false);
            return;
        }
        layoutParams = (LayoutParams) this.aT.getLayoutParams();
        layoutParams.height = -1;
        layoutParams.setMargins(0, Util.b(86), 0, Util.b(120));
        if (Constants.aY) {
            this.aW.setVisibility(0);
        }
        this.aZ = true;
        this.aT.requestLayout();
        e(true);
    }

    private String a(String str) {
        try {
            byte[] d = new k(Constants.bx).d(str);
            if (d != null) {
                return new String(d);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private void F() {
        if (Util.j(this.s) && !GaanaApplication.getInstance().isAppInOfflineMode()) {
            URLManager uRLManager = new URLManager();
            uRLManager.a(this.aR);
            uRLManager.b(false);
            uRLManager.a(String.class);
            com.i.i.a().a(new af() {
                public void onRetreivalComplete(Object obj) {
                    PlayerFragmentV4.this.aY = (String) obj;
                    if (PlayerFragmentV4.this.aQ == 2) {
                        PlayerFragmentV4.this.aY = PlayerFragmentV4.this.a(PlayerFragmentV4.this.aY);
                    }
                    h.a().a(new TaskListner() {
                        public void doBackGroundTask() {
                            switch (PlayerFragmentV4.this.aQ) {
                                case 2:
                                    PlayerFragmentV4.this.mPlayerManager.a(new DefaultLrcBuilder().getLrcRows(PlayerFragmentV4.this.aY));
                                    return;
                                case 3:
                                    List arrayList = new ArrayList();
                                    String[] split = PlayerFragmentV4.this.aY.split("\n");
                                    int i = 0;
                                    int length = split.length;
                                    int i2 = 0;
                                    while (i < length) {
                                        long j = (long) i2;
                                        i2++;
                                        arrayList.add(new LrcRow(null, j, split[i]));
                                        i++;
                                    }
                                    PlayerFragmentV4.this.mPlayerManager.a(arrayList);
                                    return;
                                default:
                                    PlayerFragmentV4.this.mPlayerManager.a(null);
                                    return;
                            }
                        }

                        public void onBackGroundTaskCompleted() {
                            switch (PlayerFragmentV4.this.aQ) {
                                case 2:
                                    PlayerFragmentV4.this.aU.setLrc(PlayerFragmentV4.this.mPlayerManager.o());
                                    PlayerFragmentV4.this.aU.seekLrcToTime(0);
                                    PlayerFragmentV4.this.aT.setVisibility(0);
                                    PlayerFragmentV4.this.aU.setClickable(true);
                                    ((GaanaActivity) PlayerFragmentV4.this.s).getWindow().addFlags(128);
                                    return;
                                case 3:
                                    PlayerFragmentV4.this.aU.setLrc(PlayerFragmentV4.this.mPlayerManager.o());
                                    PlayerFragmentV4.this.aT.setVisibility(8);
                                    PlayerFragmentV4.this.aS.setText(PlayerFragmentV4.this.aY);
                                    PlayerFragmentV4.this.aS.setVisibility(0);
                                    ((GaanaActivity) PlayerFragmentV4.this.s).getWindow().clearFlags(128);
                                    return;
                                default:
                                    PlayerFragmentV4.this.aU.setLrc(PlayerFragmentV4.this.mPlayerManager.o());
                                    PlayerFragmentV4.this.aT.setVisibility(8);
                                    PlayerFragmentV4.this.aS.setVisibility(8);
                                    ((GaanaActivity) PlayerFragmentV4.this.s).getWindow().clearFlags(128);
                                    return;
                            }
                        }
                    }, -1);
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    ((GaanaActivity) PlayerFragmentV4.this.s).getWindow().clearFlags(128);
                    PlayerFragmentV4.this.mPlayerManager.a(null);
                    PlayerFragmentV4.this.aY = null;
                }
            }, uRLManager);
        }
    }

    private void G() {
        ColombiaAdViewManager.a().e();
        if (e.W == 0 && this.s != null) {
            boolean z = this.s.getResources().getBoolean(R.bool.isPlayerAdEnabled);
            if (ap.a().b(this.s) && z) {
                if (this.ax == null) {
                    this.ax = new PublisherAdView(this.s);
                }
                ColombiaAdViewManager.a().b(this.s, this.R, e.y, this.ax, this, "");
            }
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ((GaanaActivity) this.s).setPlayerFullScreen(true);
        this.mPlayerManager = PlayerManager.a(getContext());
        this.mCurrentTrack = this.mPlayerManager.a(PlaySequenceType.CURRENT);
        if (this.R != null) {
            H();
            C();
            A();
            ac();
            R();
            this.aI = this.mPlayerManager.s();
            v();
            if (!P()) {
                this.ap.setOptionsLayoutPositionListener(new IOptionsLayoutPositionListener() {
                    public void OptionsLayoutPosition(View view) {
                        int[] iArr = new int[2];
                        view.findViewById(R.id.queue_panel_download_button).getLocationInWindow(iArr);
                        if (!PlayerFragmentV4.this.b) {
                            PlayerFragmentV4.this.b = true;
                            PlayerFragmentV4.this.b(iArr[0], iArr[1]);
                        }
                    }
                });
            }
        }
    }

    private void H() {
        TypedArray obtainStyledAttributes = this.s.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.w = ContextCompat.getDrawable(getContext(), R.drawable.vector_player_repeat_v4_white);
        this.x = ContextCompat.getDrawable(getContext(), R.drawable.vector_player_shuffle_v4_white);
        this.y = ContextCompat.getDrawable(getContext(), R.drawable.vector_player_pause_circled_new);
        this.z = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(30, -1));
        this.A = ContextCompat.getDrawable(getContext(), R.drawable.vector_player_play_circled_new);
        this.B = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(86, -1));
        this.U = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(31, -1));
        obtainStyledAttributes.recycle();
        GaanaApplication.getInstance().setGADParameter("Player Page");
        G();
        this.av = getResources().getDrawable(R.drawable.vector_player_pause_active);
        this.Q = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.song_quality_color, this.Q, true);
        this.mRadioManager = ad.a(this.s);
        this.v = (ViewGroup) this.R.findViewById(R.id.ll_queue_container);
        this.am = (QueueSlidingUpPanelLayout) this.R.findViewById(R.id.sliding_layout_queue);
        this.am.setAnchorPoint(0.88f);
        this.am.setOverlayed(true);
        this.am.setBackgroundResource(R.drawable.shape_bg_transparant);
        boolean z = false;
        this.am.setSlidingEnabled(false);
        this.G = (ImageView) this.R.findViewById(R.id.playerBtnRepeat);
        this.G.setImageDrawable(this.w);
        this.G.setTag(Integer.valueOf(0));
        this.H = (ImageView) this.R.findViewById(R.id.playerBtnShuffle);
        this.H.setImageDrawable(this.x);
        this.H.setTag(Integer.valueOf(0));
        if (Constants.cY) {
            this.G.setVisibility(4);
            this.H.setVisibility(4);
            ((TextView) this.v.findViewById(R.id.repeatButtonText)).setVisibility(4);
            ((TextView) this.v.findViewById(R.id.shuffleButtonText)).setVisibility(4);
        }
        FrameLayout progressOverlayView = ((GaanaActivity) this.s).getProgressOverlayView();
        if (progressOverlayView != null) {
            this.I = (ImageView) progressOverlayView.findViewById(R.id.playerBtnShuffle);
            this.J = (ImageView) progressOverlayView.findViewById(R.id.playerBtnRepeat);
        }
        this.X = (SeekBar) this.R.findViewById(R.id.seekBar);
        this.X.setFocusable(false);
        this.X.setPadding(0, 0, 0, 0);
        this.Y = (SeekBar) this.R.findViewById(R.id.seekBarBottom);
        this.Y.setPadding(0, 0, 0, 0);
        this.Y.setThumb(new ColorDrawable(getResources().getColor(17170445)));
        this.Y.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.P = (TextView) this.R.findViewById(R.id.tvPlayerEndTimer);
        this.L = (ImageView) this.R.findViewById(R.id.similarSongsButton);
        this.N = (TextView) this.R.findViewById(R.id.similarSongsButtonText);
        this.N.setOnClickListener(this);
        this.L.setOnClickListener(this);
        this.aC = (TextView) this.R.findViewById(R.id.queue_panel_main_text_bottom);
        this.aD = (TextView) this.R.findViewById(R.id.queue_panel_secondary_text_bottom);
        this.E = (FrameLayout) this.R.findViewById(R.id.queueInnerContainer);
        this.as = this.R.findViewById(R.id.playerBottomBgLayer);
        this.F = (ConstraintLayout) this.R.findViewById(R.id.queueSlidingContainer);
        this.ar = (ImageView) this.R.findViewById(R.id.chevron_up);
        this.am.setPanelHeight((int) getResources().getDimension(R.dimen.bottom_player_action_container_height_v4));
        ao();
        this.aJ = (TextView) this.S.findViewById(R.id.trackText);
        this.aK = (TextView) this.S.findViewById(R.id.albumText);
        this.aC.setTypeface(Util.i(this.s));
        this.as.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.T.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        this.T.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerFragmentV4.this.a(view);
            }
        });
        this.Z.setOnClickListener(this);
        this.R.findViewById(R.id.playerBtnRepeat).setOnClickListener(this);
        this.R.findViewById(R.id.playerBtnShuffle).setOnClickListener(this);
        this.R.findViewById(R.id.playerBtnPrev).setOnClickListener(this);
        this.K.setOnClickListener(this);
        M();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ONBOARD_PLAYER_CREATED_FIRST_TIME", 0);
        if (GaanaApplication.sessionHistoryCount == 0 && sharedPreferences.getBoolean("ONBOARD_PLAYER_CREATED_FIRST_TIME", true)) {
            z = true;
        }
        this.an = z;
        this.ag = new PlayerQueueViewV2(this.s, ((GaanaActivity) this.s).getCurrentFragment());
        I();
        al();
        if (this.mCurrentTrack != null) {
            b(this.mCurrentTrack.b());
        }
        this.ah = (ImageView) this.R.findViewById(R.id.queue_panel_bottom_button);
        this.W = (ProgressBar) this.R.findViewById(R.id.queue_panel_bottom_progressBar);
        this.aB = (ProgressBar) this.R.findViewById(R.id.queue_panel_bottom_progressBar2);
        this.ah.setOnClickListener(this);
        this.ai = (TextView) this.R.findViewById(R.id.pagerTrackText);
        this.aj = (TextView) this.R.findViewById(R.id.pagerAlbumText);
        this.al = (TextView) this.R.findViewById(R.id.pagerTrackTextVideo);
        Typeface i = Util.i(this.s);
        this.ai.setTypeface(i);
        this.al.setTypeface(i);
        this.v.findViewById(R.id.queue_panel_button_save).setOnClickListener(this);
        this.v.findViewById(R.id.queue_panel_button_clear).setOnClickListener(this);
        this.v.findViewById(R.id.queue_panel_cheveron_view).setOnClickListener(this);
        this.v.findViewById(R.id.queue_panel_bottom_options).setOnClickListener(this);
        if (Constants.cY) {
            ((CustomTextView) this.v.findViewById(R.id.queue_panel_cheveron_view_round)).setText(R.string.go_to_party);
        }
        i = Util.i(this.s);
        if (i != null) {
            ((TextView) this.v.findViewById(R.id.repeatButtonText)).setTypeface(i);
            ((TextView) this.v.findViewById(R.id.shuffleButtonText)).setTypeface(i);
            ((CustomTextView) this.v.findViewById(R.id.queue_panel_cheveron_view_round)).setTypeface(i);
        }
        ((CustomTextView) this.v.findViewById(R.id.queue_panel_cheveron_view_round)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!Constants.cY) {
                    if (!PlayerFragmentV4.this.am.f()) {
                        PlayerFragmentV4.this.am.g();
                    } else if (PlayerFragmentV4.this.bl) {
                        PlayerFragmentV4.this.am.h();
                    }
                    d.a().a("PREFERENCE_KEY_QUEUE_ANIMATION_INITIATED", true, false);
                } else if (JukeSessionManager.getInstance().getJukeSessionPlaylist() != null) {
                    ((GaanaActivity) PlayerFragmentV4.this.s).displayFragment(JukePartyFragment.newInstance(JukeSessionManager.getInstance().getJukeSessionPlaylist(), -1, "", false));
                }
            }
        });
        K();
        ((GaanaActivity) this.s).setmCurrentPlayerFragment(this);
    }

    public void a(ViewHolder viewHolder, int i) {
        PlayerTrack a = PlayerManager.a(GaanaApplication.getContext()).a(i);
        if (!(a == null || a.b() == null)) {
            b(a.b());
            U();
        }
        if (this.bc != i && i != PlayerManager.a(this.s).s()) {
            u.a().a("BoxQueue", "Song Swipe", i > PlayerManager.a(this.s).s() ? "Up Next" : "Previous");
            this.bc = i;
        }
    }

    private void I() {
        AnonymousClass10 anonymousClass10 = new al() {
            public void a(ViewHolder viewHolder, int i) {
                PlayerFragmentV4.this.aL = true;
                PlayerFragmentV4.this.a(i, PlayerFragmentV4.this.mPlayerManager.s());
                PlayerFragmentV4.this.W();
                if (i == PlayerFragmentV4.this.mPlayerManager.s() || viewHolder == null) {
                    PlayerFragmentV4.this.a(i, false);
                    return;
                }
                PlayerFragmentV4.this.e(i);
                u.a().a("Player", "Play", "");
            }

            public void a(boolean z) {
                PlayerFragmentV4.this.c = z;
            }
        };
        this.aq = (DiscreteScrollView) this.R.findViewById(R.id.viewPager);
        this.aq.setSlideOnFling(false);
        this.bi = PlayerManager.a(this.s).s();
        if (this.aq != null) {
            f(true);
        }
        this.aq.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0) {
                    PlayerFragmentV4.this.bj = true;
                    DiscreteScrollLayoutManager discreteScrollLayoutManager = (DiscreteScrollLayoutManager) recyclerView.getLayoutManager();
                    an.a().c("scroll", AvidJSONUtil.KEY_X, "", "player", "", "", String.valueOf(PlayerFragmentV4.this.bi), String.valueOf(discreteScrollLayoutManager.getCurrentPosition()));
                    if (PlayerFragmentV4.this.aI < discreteScrollLayoutManager.getCurrentPosition()) {
                        u.a().a("Player", "Swipe", "Right");
                    } else {
                        u.a().a("Player", "Swipe", "Left");
                    }
                    PlayerFragmentV4.this.aI = discreteScrollLayoutManager.getCurrentPosition();
                    PlayerFragmentV4.this.bn = false;
                    if (PlayerFragmentV4.this.aI == PlayerFragmentV4.this.mPlayerManager.s()) {
                        PlayerFragmentV4.this.d();
                    } else if (PlayerFragmentV4.this.f()) {
                        PlayerFragmentV4.this.e(PlayerFragmentV4.this.aI);
                    }
                    if (discreteScrollLayoutManager.getCurrentPosition() == PlayerFragmentV4.this.bi || PlayerFragmentV4.this.bk != 0) {
                        PlayerFragmentV4.this.a(discreteScrollLayoutManager.getCurrentPosition());
                        PlayerFragmentV4.this.a(discreteScrollLayoutManager.getCurrentPosition(), PlayerFragmentV4.this.mPlayerManager.s());
                        PlayerFragmentV4.this.a(discreteScrollLayoutManager.getCurrentPosition(), true);
                    }
                    if (!PlayerFragmentV4.this.bp) {
                        d.a().a("PLAYER_VIEW_PAGER_COACHMARK_FIRSTTIME", 5, false);
                    }
                    if (PlayerFragmentV4.this.bp && ((GaanaActivity) PlayerFragmentV4.this.s).hasWindowFocus() && !PlayerFragmentV4.this.aZ && !PlayerFragmentV4.this.k().e() && !PlayerFragmentV4.this.aP) {
                        PlayerFragmentV4.this.aq.smoothScrollToPosition(PlayerFragmentV4.this.g() - 1);
                        ((GaanaActivity) PlayerFragmentV4.this.s).showPlayerVIewPagerCoachmark();
                        PlayerFragmentV4.this.bp = false;
                        d.a().a("PLAYER_VIEW_PAGER_COACHMARK_FIRSTTIME", 5, false);
                    } else {
                        return;
                    }
                }
                PlayerFragmentV4.this.aT.setVisibility(8);
                PlayerFragmentV4.this.aS.setVisibility(8);
                PlayerFragmentV4.this.ak.setVisibility(8);
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (PlayerFragmentV4.this.bj && i != 0) {
                    PlayerFragmentV4.this.bk = i;
                    PlayerFragmentV4.this.ai.setVisibility(4);
                    PlayerFragmentV4.this.aj.setVisibility(4);
                    PlayerFragmentV4.this.al.setVisibility(4);
                    PlayerFragmentV4.this.bj = false;
                    PlayerFragmentV4.this.bn = true;
                    PlayerFragmentV4.this.e();
                    if (PlayerFragmentV4.this.j) {
                        PlayerFragmentV4.this.s();
                    }
                }
            }
        });
        this.aq.addOnItemTouchListener(new OnItemTouchListener() {
            public void onRequestDisallowInterceptTouchEvent(boolean z) {
            }

            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            }

            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                int i = new DisplayMetrics().widthPixels;
                Util.a(PlayerFragmentV4.this.s, 230);
                switch (motionEvent.getAction()) {
                    case 0:
                        PlayerFragmentV4.this.be = motionEvent.getX();
                        PlayerFragmentV4.this.bf = motionEvent.getY();
                        break;
                    case 1:
                        break;
                }
                PlayerFragmentV4.this.bg = motionEvent.getX();
                PlayerFragmentV4.this.bh = motionEvent.getY();
                float ap = PlayerFragmentV4.this.bf - PlayerFragmentV4.this.bh;
                if (Math.abs(PlayerFragmentV4.this.be - PlayerFragmentV4.this.bg) <= Math.abs(ap) && Math.abs(ap) > ((float) PlayerFragmentV4.this.bd) && ap < 0.0f) {
                    recyclerView.requestDisallowInterceptTouchEvent(true);
                    PlayerFragmentV4.this.ap.closePlayerScreen();
                    return true;
                }
                return false;
            }
        });
        this.ap = new CardPagerAdapterV4(this.s, this, this.aq, PlayerManager.a(this.s).n(), anonymousClass10);
        this.aq.setAdapter(this.ap);
        this.aq.setVisibility(0);
    }

    public void a(int i, boolean z) {
        if (this.j) {
            s();
            return;
        }
        CardViewHolder cardViewHolder = (CardViewHolder) this.aq.findViewHolderForAdapterPosition(i);
        if (cardViewHolder != null) {
            ViewGroup viewGroup = (ViewGroup) cardViewHolder.itemView;
            LinearLayout linearLayout = (LinearLayout) viewGroup.findViewById(R.id.optionLayout);
            View findViewById = viewGroup.findViewById(R.id.dark_overlay);
            if (!(viewGroup == null || z)) {
                if (f() && !this.bm) {
                    a(linearLayout, findViewById);
                } else if (!this.bm) {
                    b(linearLayout, findViewById);
                }
            }
        }
    }

    private void e(int i) {
        CardViewHolder cardViewHolder = (CardViewHolder) this.aq.findViewHolderForAdapterPosition(i);
        if (cardViewHolder != null) {
            ViewGroup viewGroup = (ViewGroup) cardViewHolder.itemView;
            a((LinearLayout) viewGroup.findViewById(R.id.optionLayout), viewGroup.findViewById(R.id.dark_overlay));
        }
    }

    private void f(int i) {
        if ((k() == null || !k().e()) && !this.bz) {
            CardViewHolder cardViewHolder = (CardViewHolder) this.aq.findViewHolderForAdapterPosition(i);
            if (cardViewHolder != null) {
                ViewGroup viewGroup = (ViewGroup) cardViewHolder.itemView;
                b((LinearLayout) viewGroup.findViewById(R.id.optionLayout), viewGroup.findViewById(R.id.dark_overlay));
            }
            return;
        }
        e();
    }

    private void c(boolean z) {
        LayoutParams layoutParams;
        int b;
        if (z) {
            switch (this.aQ) {
                case 1:
                    layoutParams = (LayoutParams) this.ak.getLayoutParams();
                    if (this.aO) {
                        layoutParams.setMargins(0, 0, 0, Util.b(80));
                    } else {
                        layoutParams.setMargins(0, 0, 0, Util.b(30));
                    }
                    this.ak.bringToFront();
                    return;
                case 2:
                    layoutParams = (LayoutParams) this.aT.getLayoutParams();
                    layoutParams.height = bb;
                    if (this.aO) {
                        layoutParams.setMargins(0, 0, 0, Util.b(60));
                    } else {
                        layoutParams.setMargins(0, 0, 0, Util.b(10));
                    }
                    this.aT.bringToFront();
                    return;
                case 3:
                    layoutParams = (LayoutParams) this.aS.getLayoutParams();
                    layoutParams.height = bb;
                    b = Util.b(56);
                    if (this.aO) {
                        layoutParams.setMargins(b, b, b, Util.b(60));
                    } else {
                        layoutParams.setMargins(b, b, b, Util.b(10));
                    }
                    this.aS.bringToFront();
                    return;
                default:
                    return;
            }
        }
        switch (this.aQ) {
            case 1:
                ((LayoutParams) this.ak.getLayoutParams()).setMargins(0, 0, 0, (int) this.s.getResources().getDimension(R.dimen.dp220));
                this.ak.bringToFront();
                return;
            case 2:
                layoutParams = (LayoutParams) this.aT.getLayoutParams();
                layoutParams.height = bb;
                if (this.aO) {
                    layoutParams.setMargins(0, 0, 0, Util.b(275));
                } else {
                    layoutParams.setMargins(0, 0, 0, Util.b(225));
                }
                this.aT.bringToFront();
                return;
            case 3:
                layoutParams = (LayoutParams) this.aS.getLayoutParams();
                layoutParams.height = bb;
                b = Util.b(56);
                if (this.aO) {
                    layoutParams.setMargins(b, b, b, Util.b(275));
                } else {
                    layoutParams.setMargins(b, b, b, Util.b(225));
                }
                this.aS.bringToFront();
                return;
            default:
                return;
        }
    }

    private void a(final LinearLayout linearLayout, View view) {
        if (!this.aZ) {
            this.ac.setAlpha(0.0f);
            linearLayout.setAlpha(0.0f);
            this.E.setAlpha(0.0f);
            view.setAlpha(0.0f);
            this.Y.setAlpha(0.0f);
            ViewPropertyAnimator animate = this.ac.animate();
            ViewPropertyAnimator animate2 = this.E.animate();
            ViewPropertyAnimator animate3 = view.animate();
            ViewPropertyAnimator animate4 = linearLayout.animate();
            if (VERSION.SDK_INT >= 16) {
                animate.withLayer();
                animate2.withLayer();
                animate3.withLayer();
                animate4.withLayer();
            }
            animate.alpha(1.0f).setDuration(500).start();
            animate2.alpha(1.0f).setDuration(500).start();
            animate3.alpha(1.0f).setDuration(500).start();
            animate4.alpha(1.0f).setListener(new AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                    linearLayout.setVisibility(0);
                    PlayerFragmentV4.this.E.setVisibility(0);
                    PlayerFragmentV4.this.ac.setVisibility(0);
                    PlayerFragmentV4.this.v.setVisibility(0);
                    PlayerFragmentV4.this.v.setClickable(true);
                    if (PlayerFragmentV4.this.ag.getRecyclerView() != null) {
                        PlayerFragmentV4.this.ag.getRecyclerView().setVisibility(0);
                    }
                    PlayerFragmentV4.this.F.setClickable(true);
                    PlayerFragmentV4.this.bm = true;
                    PlayerFragmentV4.this.c(false);
                }

                public void onAnimationEnd(Animator animator) {
                    linearLayout.setClickable(true);
                    PlayerFragmentV4.this.E.setClickable(true);
                    PlayerFragmentV4.this.F.setClickable(true);
                    PlayerFragmentV4.this.ac.setClickable(true);
                    PlayerFragmentV4.this.am.setSlidingEnabled(true);
                    PlayerFragmentV4.this.bm = false;
                }
            }).setDuration(500).start();
            this.bo = false;
            J();
            d();
        }
    }

    public void a(boolean z) {
        this.bn = z;
    }

    /* JADX WARNING: Missing block: B:15:0x003c, code skipped:
            return;
     */
    public void d() {
        /*
        r4 = this;
        r0 = r4.c;
        if (r0 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = r4.bn;
        if (r0 != 0) goto L_0x003c;
    L_0x0009:
        r0 = r4.bz;
        if (r0 == 0) goto L_0x000e;
    L_0x000d:
        goto L_0x003c;
    L_0x000e:
        r0 = r4.s;
        r0 = (com.gaana.GaanaActivity) r0;
        r0 = r0.hasWindowFocus();
        if (r0 != 0) goto L_0x0019;
    L_0x0018:
        return;
    L_0x0019:
        r0 = new android.os.Handler;
        r1 = android.os.Looper.getMainLooper();
        r0.<init>(r1);
        r1 = r4.g;
        if (r1 == 0) goto L_0x002b;
    L_0x0026:
        r1 = r4.g;
        r1.cancel();
    L_0x002b:
        r1 = new com.fragments.PlayerFragmentV4$15;
        r1.<init>(r0);
        r4.g = r1;
        r0 = r4.h;
        r1 = r4.g;
        r2 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r0.schedule(r1, r2);
        return;
    L_0x003c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fragments.PlayerFragmentV4.d():void");
    }

    public void e() {
        if (this.g != null) {
            this.g.cancel();
        }
    }

    private void d(boolean z) {
        LinearLayout linearLayout;
        LayoutParams layoutParams;
        ViewPropertyAnimator animate;
        if (z) {
            if (this.aO) {
                linearLayout = (LinearLayout) this.R.findViewById(R.id.llNativeAdSlot);
                linearLayout.bringToFront();
                layoutParams = (LayoutParams) linearLayout.getLayoutParams();
                layoutParams.bottomMargin = this.s.getResources().getDimensionPixelSize(R.dimen.bottom_player_ad_fade_out_margin);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setAlpha(0.0f);
                animate = linearLayout.animate();
                if (VERSION.SDK_INT >= 16) {
                    animate.withLayer();
                }
                animate.alpha(1.0f).setDuration(500).start();
            }
        } else if (this.aO) {
            linearLayout = (LinearLayout) this.R.findViewById(R.id.llNativeAdSlot);
            linearLayout.bringToFront();
            layoutParams = (LayoutParams) linearLayout.getLayoutParams();
            layoutParams.bottomMargin = this.s.getResources().getDimensionPixelSize(R.dimen.bottom_player_ad_fade_in_margin);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setAlpha(0.0f);
            animate = linearLayout.animate();
            if (VERSION.SDK_INT >= 16) {
                animate.withLayer();
            }
            animate.alpha(1.0f).setDuration(500).start();
        }
    }

    private void J() {
        boolean z = this.bo || this.aZ;
        d(z);
    }

    public boolean f() {
        return this.bo;
    }

    private void b(final LinearLayout linearLayout, View view) {
        if (this.j) {
            s();
        } else if (!this.aZ && this.mPlayerManager.s() == this.aI) {
            ViewPropertyAnimator animate = this.ac.animate();
            ViewPropertyAnimator animate2 = linearLayout.animate();
            ViewPropertyAnimator animate3 = this.E.animate();
            ViewPropertyAnimator animate4 = this.Y.animate();
            ViewPropertyAnimator animate5 = view.animate();
            if (VERSION.SDK_INT >= 16) {
                animate.withLayer();
                animate2.withLayer();
                animate3.withLayer();
                animate4.withLayer();
                animate5.withLayer();
            }
            animate.alpha(0.0f).setDuration(500).start();
            animate2.alpha(0.0f).setDuration(500).start();
            animate3.alpha(0.0f).setDuration(500).start();
            animate5.alpha(0.0f).setDuration(500).start();
            animate4.alpha(1.0f).setListener(new AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                    PlayerFragmentV4.this.bm = true;
                    linearLayout.setClickable(false);
                    PlayerFragmentV4.this.E.setClickable(false);
                    PlayerFragmentV4.this.F.setClickable(false);
                    PlayerFragmentV4.this.ac.setClickable(false);
                    PlayerFragmentV4.this.am.setSlidingEnabled(false);
                    PlayerFragmentV4.this.c(true);
                }

                public void onAnimationEnd(Animator animator) {
                    linearLayout.setVisibility(8);
                    PlayerFragmentV4.this.E.setVisibility(8);
                    PlayerFragmentV4.this.ac.setVisibility(8);
                    if (PlayerFragmentV4.this.ag.getRecyclerView() != null) {
                        PlayerFragmentV4.this.ag.getRecyclerView().setVisibility(8);
                    }
                    PlayerFragmentV4.this.am.setSlidingEnabled(false);
                    PlayerFragmentV4.this.v.setClickable(false);
                    PlayerFragmentV4.this.F.setClickable(false);
                    PlayerFragmentV4.this.bm = false;
                    PlayerFragmentV4.this.bn = false;
                }
            }).setDuration(500).start();
            this.bo = true;
            J();
        }
    }

    private void e(boolean z) {
        if (z) {
            GaanaApplication.getInstance().setGADParameter("Lyrics Page");
        } else {
            GaanaApplication.getInstance().setGADParameter("Player Page");
        }
        G();
        this.aZ = z;
        if (this.aU != null) {
            this.aU.setFullscreenMode(z);
        }
        DiscreteScrollLayoutManager layoutManager = this.aq.getLayoutManager();
        CardViewHolder cardViewHolder = (CardViewHolder) this.aq.findViewHolderForAdapterPosition(layoutManager.getCurrentPosition());
        if (cardViewHolder != null) {
            ViewGroup viewGroup = (ViewGroup) cardViewHolder.itemView;
            LinearLayout linearLayout = (LinearLayout) viewGroup.findViewById(R.id.optionLayout);
            View findViewById = viewGroup.findViewById(R.id.dark_overlay);
            SimpleExoPlayerView simpleExoPlayerView = (SimpleExoPlayerView) viewGroup.findViewById(R.id.vertical_video_view);
            if (z) {
                findViewById.setVisibility(8);
                linearLayout.setVisibility(8);
                linearLayout.setClickable(false);
                if (simpleExoPlayerView != null) {
                    simpleExoPlayerView.setVisibility(8);
                }
            } else {
                linearLayout.setVisibility(0);
                linearLayout.setClickable(true);
                findViewById.setVisibility(0);
                if (simpleExoPlayerView != null) {
                    simpleExoPlayerView.setVisibility(0);
                }
            }
        }
        if (z) {
            e();
            this.ad.setVisibility(0);
            this.ac.setVisibility(8);
            this.ae.setVisibility(8);
            this.af.setVisibility(0);
            this.aJ.setText(this.mPlayerManager.i().b().getTrackTitle());
            this.aK.setText(this.mPlayerManager.i().b().getAlbumTitle());
            this.aJ.setVisibility(0);
            this.aK.setVisibility(0);
            this.E.setVisibility(8);
            this.Y.setVisibility(0);
            this.Y.setAlpha(1.0f);
            this.E.setVisibility(8);
            this.E.setClickable(false);
            this.F.setClickable(false);
            this.am.setSlidingEnabled(false);
            if (this.ag.getRecyclerView() != null) {
                this.ag.getRecyclerView().setVisibility(8);
            }
            this.am.setSlidingEnabled(false);
            this.v.setClickable(false);
            this.F.setClickable(false);
            this.aq.setVisibility(8);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Lyrics Full Page");
            stringBuilder.append(this.aQ);
            u.a().b("Lyrics", stringBuilder.toString());
            ((GaanaActivity) this.s).getWindow().addFlags(128);
        } else {
            this.ad.setVisibility(8);
            this.ac.setVisibility(0);
            this.af.setVisibility(8);
            this.ae.setVisibility(0);
            a(layoutManager.getCurrentPosition(), this.mPlayerManager.s());
            this.aJ.setVisibility(4);
            this.aK.setVisibility(4);
            this.ap.notifyDataSetChanged();
            this.aq.setVisibility(0);
            c(this.bo);
            if (!this.bo) {
                d();
                this.E.setVisibility(0);
                this.Y.setVisibility(8);
                this.Y.setAlpha(0.0f);
                this.v.setVisibility(0);
                this.v.setClickable(true);
                if (this.ag.getRecyclerView() != null) {
                    this.ag.getRecyclerView().setVisibility(0);
                }
                this.F.setClickable(true);
                this.E.setClickable(true);
                this.F.setClickable(true);
                this.am.setSlidingEnabled(true);
            }
            ((GaanaActivity) this.s).getWindow().clearFlags(128);
        }
        J();
    }

    private void K() {
        boolean a = a(this.s);
        if (a) {
            a = this.s.getResources().getBoolean(R.bool.isPlayerAdEnabled);
        }
        LinearLayout linearLayout = (LinearLayout) this.R.findViewById(R.id.llNativeAdSlot);
        if (a && this.aO) {
            linearLayout.setVisibility(0);
            linearLayout.bringToFront();
            return;
        }
        linearLayout.setVisibility(8);
    }

    public int g() {
        return this.aI;
    }

    private void g(int i) {
        PlayerTrack a = this.mPlayerManager.a(i);
        if (this.aI != this.mPlayerManager.s() && a != null) {
            this.aj.setVisibility(0);
            this.ai.setVisibility(0);
            if (!Constants.cF || TextUtils.isEmpty(a.b().getVerticalUrl())) {
                this.ai.setText(a.b().getTrackTitle());
                this.al.setText("");
                this.al.setVisibility(8);
            } else {
                this.ai.setText(a.b().getTrackTitle());
                this.al.setText(ShareConstants.VIDEO_URL);
                this.al.setVisibility(0);
                this.al.setAlpha(0.0f);
                this.al.animate().alpha(1.0f).setDuration(1000).start();
            }
            this.aj.setText(a.b().getAlbumTitle());
            this.ai.setAlpha(0.0f);
            this.aj.setAlpha(0.0f);
            this.ai.animate().alpha(1.0f).setDuration(1000).start();
            this.aj.animate().alpha(1.0f).setDuration(1000).start();
        }
    }

    public void a(int i) {
        this.aI = i;
        if (i == this.mPlayerManager.s()) {
            if (this.mPlayerManager.i() != null) {
                this.aj.setVisibility(8);
                this.ai.setVisibility(8);
                this.al.setVisibility(8);
            } else if (!f()) {
                g(i);
            }
        } else if (f()) {
            g(i);
        } else {
            g(i);
        }
    }

    private void a(int i, int i2) {
        if (i2 == i) {
            switch (this.aQ) {
                case 1:
                    this.ak.setVisibility(0);
                    break;
                case 2:
                    this.aT.setVisibility(0);
                    break;
                case 3:
                    this.aS.setVisibility(0);
                    break;
            }
        }
        this.aT.setVisibility(8);
        this.aS.setVisibility(8);
        this.ak.setVisibility(8);
        CharSequence charSequence = "";
        if (i2 == i) {
            charSequence = "Now Playing";
        } else if (i2 < i) {
            charSequence = "Up Next";
        } else if (i2 > i) {
            charSequence = "Previous";
        }
        PlayerTrack a = this.mPlayerManager.a(i);
        this.aJ.setText(charSequence);
        if (a != null) {
            TextView textView = this.aK;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("From ");
            stringBuilder.append(a.b().getAlbumTitle());
            textView.setText(stringBuilder.toString());
        }
    }

    private int L() {
        return (int) ((float) (d.a().c() - this.bu));
    }

    private void b(View view) {
        view.bringToFront();
    }

    private void M() {
        this.p = (Toolbar) this.R.findViewById(R.id.toolbar);
        this.p.setContentInsetsAbsolute(0, 0);
        this.p.getMenu().clear();
        this.p.setBackgroundColor(getResources().getColor(R.color.transparent_color));
        this.S.setToolbar(this.p);
        this.p.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PlayerFragmentV4.this.am.e()) {
                    PlayerFragmentV4.this.am.g();
                }
            }
        });
        this.p.bringToFront();
    }

    private void N() {
        Editor edit = GaanaApplication.getContext().getSharedPreferences("ONBOARD_PLAYER_CREATED_FIRST_TIME", 0).edit();
        edit.putBoolean("ONBOARD_PLAYER_CREATED_FIRST_TIME", false);
        edit.apply();
        Util.a(null);
        this.an = false;
        if (!(isActivityDestroyed() || this.r == null || this.r.a() != 0)) {
            this.r.a(0);
        }
    }

    private void g(boolean z) {
        if (this.r != null) {
            if (this.an && z && this.mPlayerManager.E() == null) {
                N();
            }
            if (!z) {
                this.r.a(2);
            } else if (this.r.a() == 2 && !com.managers.f.v().w()) {
                this.r.a(0);
            }
        }
    }

    private void O() {
        if (this.mPlayerManager.m() == PlayerType.GAANA_RADIO && this.mPlayerManager.g()) {
            this.mPlayerManager.a(PlayerType.GAANA_RADIO, getContext());
        } else if (this.mPlayerManager.m() != PlayerType.GAANA_RADIO) {
            this.mPlayerManager.f(false);
        }
    }

    public void onResume() {
        super.onResume();
        if (PlayerStatus.a(getContext()).c() && this.mCurrentTrack != null && this.mCurrentTrack.b() != null && (PlayerManager.a(this.s).c(this.mCurrentTrack.a(true)) || PlayerManager.a(this.s).d(this.mCurrentTrack.a(true)))) {
            ((GaanaActivity) this.s).getWindow().addFlags(128);
        }
        this.bA.postDelayed(new Runnable() {
            public void run() {
                try {
                    ((GaanaActivity) PlayerFragmentV4.this.s).setPlayerFullScreen(true);
                    PlayerFragmentV4.this.i();
                    if (PlayerFragmentV4.this.aE) {
                        PlayerFragmentV4.this.bA.postDelayed(PlayerFragmentV4.this.bB, 100);
                    }
                    PlayerFragmentV4.this.bA.postDelayed(new Runnable() {
                        public void run() {
                            DiscreteScrollLayoutManager layoutManager = PlayerFragmentV4.this.aq.getLayoutManager();
                            PlayerFragmentV4.this.a(layoutManager.getCurrentPosition());
                            PlayerFragmentV4.this.bj = true;
                            PlayerFragmentV4.this.K();
                            PlayerFragmentV4.this.W();
                            if (PlayerFragmentV4.this.aq.getViewHolder(PlayerFragmentV4.this.mPlayerManager.s()) != null) {
                                PlayerFragmentV4.this.bs = d.a().c();
                                PlayerFragmentV4.this.a(layoutManager.getCurrentPosition(), PlayerFragmentV4.this.mPlayerManager.s());
                                PlayerFragmentV4.this.d();
                            }
                            PlayerFragmentV4.this.am.setSlidingEnabled(true);
                            PlayerFragmentV4.this.h();
                            PlayerFragmentV4.this.p();
                            PlayerFragmentV4.this.bl = true;
                        }
                    }, 1000);
                } catch (Exception unused) {
                }
                ((GaanaActivity) PlayerFragmentV4.this.s).setmCurrentPlayerFragment(PlayerFragmentV4.this);
                if (PlayerFragmentV4.this.ay != PlayerFragmentV4.this.a(PlayerFragmentV4.this.s)) {
                    PlayerFragmentV4.this.ay = PlayerFragmentV4.this.a(PlayerFragmentV4.this.s);
                }
            }
        }, 1000);
        if (this.ax != null) {
            this.ax.resume();
        }
    }

    private void b(int i, int i2) {
        boolean b = d.a().b("DOWNLOAD_PLAYER_TRACK_COACHMARK", true, false);
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(getPlayingTrack().getBusinessObjId()));
        if (ap.a().j() && b && e != DownloadStatus.DOWNLOADED) {
            Intent intent = new Intent(this.s, VideoCoachmarkActivity.class);
            intent.putExtra("COACHMARK_VALUE", "DOWNLOAD_PLAYER_TRACK_COACHMARK");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            intent.putExtra("TargetCoords", stringBuilder.toString());
            ((GaanaActivity) this.s).startActivityForResult(intent, PointerIconCompat.TYPE_ALIAS);
            ((GaanaActivity) this.s).overridePendingTransition(17432576, 17432577);
        }
    }

    public void h() {
        if (P()) {
            Q();
        }
    }

    private boolean P() {
        if (!((GaanaActivity) this.s).hasWindowFocus() || this.aZ || k().e() || this.aP) {
            return false;
        }
        int b = d.a().b("PLAYER_VIEW_PAGER_COACHMARK_FIRSTTIME", 0, false);
        if (b >= 3) {
            return false;
        }
        int b2 = d.a().b("SESSION_OCCURENCE_MINI_PLAYER_SWIPE_COACHMARK", 0, false);
        int i = b2 + 2;
        if (b2 > 0) {
            if (GaanaApplication.sessionHistoryCount + 1 >= i) {
                return true;
            }
        } else if (b != 0 || GaanaApplication.sessionHistoryCount + 1 < 1) {
            return false;
        } else {
            return true;
        }
        return false;
    }

    private void Q() {
        this.bA.postDelayed(new Runnable() {
            /* JADX WARNING: Missing block: B:12:0x0055, code skipped:
            return;
     */
            public void run() {
                /*
                r3 = this;
                r0 = com.fragments.PlayerFragmentV4.this;
                r0 = r0.s;
                r0 = (com.gaana.GaanaActivity) r0;
                r0 = r0.hasWindowFocus();
                if (r0 == 0) goto L_0x0055;
            L_0x000e:
                r0 = com.fragments.PlayerFragmentV4.this;
                r0 = r0.aZ;
                if (r0 != 0) goto L_0x0055;
            L_0x0016:
                r0 = com.fragments.PlayerFragmentV4.this;
                r0 = r0.k();
                r0 = r0.e();
                if (r0 != 0) goto L_0x0055;
            L_0x0022:
                r0 = com.fragments.PlayerFragmentV4.this;
                r0 = r0.aP;
                if (r0 == 0) goto L_0x002b;
            L_0x002a:
                goto L_0x0055;
            L_0x002b:
                r0 = com.fragments.PlayerFragmentV4.this;
                r0 = r0.mPlayerManager;
                r1 = com.fragments.PlayerFragmentV4.this;
                r1 = r1.g();
                r2 = 1;
                r1 = r1 + r2;
                r0 = r0.a(r1);
                if (r0 == 0) goto L_0x0054;
            L_0x003f:
                r0 = com.fragments.PlayerFragmentV4.this;
                r0.bp = r2;
                r0 = com.fragments.PlayerFragmentV4.this;
                r0 = r0.aq;
                r1 = com.fragments.PlayerFragmentV4.this;
                r1 = r1.g();
                r1 = r1 + r2;
                r0.smoothScrollToPosition(r1);
            L_0x0054:
                return;
            L_0x0055:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.fragments.PlayerFragmentV4$AnonymousClass19.run():void");
            }
        }, 3000);
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

    public void i() {
        this.s = getContext();
        if (this.s != null) {
            if (this.mPlayerManager.n() == null || this.mPlayerManager.n().size() <= 0) {
                g(false);
                return;
            }
            g(true);
            O();
            o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.d);
            o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.ba);
            this.mAppState.setPlayerStatus(true);
            this.mCurrentTrack = PlayerManager.a(GaanaApplication.getContext()).i();
            this.ap.updateArrayList(PlayerManager.a(this.s).n());
            if (this.mPlayerManager.t() && this.mPlayerManager.s() != -1) {
                this.mCurrentTrack = this.mPlayerManager.a(this.mPlayerManager.s());
                a(this.mCurrentTrack);
            }
            a(this.mPlayerManager.m());
            R();
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
                    this.mCurrentTrack = PlayerManager.a(this.s).i();
                }
                if (PlayerStatus.a(getContext()).c()) {
                    this.mPlayerStates = PlayerStates.PLAYING;
                    a(this.mCurrentTrack);
                    Y();
                } else if (GaanaMusicService.s().l() && !GaanaMusicService.s().m() && !PlayerStatus.a(getContext()).e()) {
                    a(this.mCurrentTrack);
                    Y();
                } else if (GaanaMusicService.s().m()) {
                    a(this.mCurrentTrack);
                    if (GaanaMusicService.s().l()) {
                        this.T.setImageDrawable(this.y);
                        this.ah.setImageDrawable(this.av);
                    } else {
                        this.T.setImageDrawable(this.y);
                        this.ah.setImageDrawable(this.av);
                    }
                } else if (PlayerStatus.a(getContext()).e()) {
                    a(this.mCurrentTrack);
                    ab();
                }
            }
        }
    }

    public GaanaApplication j() {
        return this.mAppState;
    }

    private void R() {
        S();
        a(this.G, false);
        if (PlayerStatus.a(getContext()).c() || PlayerStatus.a(getContext()).b()) {
            this.T.setImageDrawable(this.y);
            this.ah.setImageDrawable(this.av);
        } else {
            this.T.setImageDrawable(this.A);
            this.ah.setImageDrawable(this.U);
        }
        T();
    }

    private void S() {
        if (this.mPlayerManager.d()) {
            this.G.setTag(Integer.valueOf(0));
        } else if (this.mPlayerManager.e()) {
            this.G.setTag(Integer.valueOf(1));
        } else {
            this.G.setTag(Integer.valueOf(2));
        }
    }

    private void T() {
        boolean b = PlayerManager.a(this.s).b();
        if (b) {
            this.H.setImageResource(this.C[b]);
        } else {
            this.H.setImageDrawable(this.x);
        }
        this.H.setTag(Integer.valueOf(b));
    }

    public void a(f fVar, int i) {
        if (f()) {
            if (this.Y == null) {
                this.Y = (SeekBar) this.R.findViewById(R.id.seekBarBottom);
                this.Y.setFocusable(false);
            }
            if (GaanaMusicService.s().m()) {
                this.Y.setSecondaryProgress(0);
                return;
            }
            this.Y.setMax(fVar.u());
            this.Y.setSecondaryProgress((int) ((0.01d * ((double) i)) * ((double) fVar.u())));
            return;
        }
        if (this.X == null) {
            this.X = (SeekBar) this.R.findViewById(R.id.seekBar);
            this.X.setFocusable(false);
        }
        if (GaanaMusicService.s().m()) {
            this.X.setSecondaryProgress(0);
            return;
        }
        this.X.setMax(fVar.u());
        this.X.setSecondaryProgress((int) ((0.01d * ((double) i)) * ((double) fVar.u())));
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        if (this.u != null && this.u.isShowing()) {
            this.u.dismiss();
            this.u = null;
        }
        y();
        ((GaanaActivity) this.s).setmCurrentPlayerFragment(null);
        ((GaanaActivity) this.s).setPlayerFullScreen(false);
        e();
        if (this.ax != null) {
            this.ax.pause();
        }
        ((GaanaActivity) this.s).getWindow().clearFlags(128);
        super.onPause();
    }

    public void onStop() {
        this.bA.removeCallbacks(this.bB);
        ((GaanaActivity) this.s).setmCurrentPlayerFragment(null);
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
            this.q.removeCallbacksAndMessages(null);
            if (this.an && this.ao) {
                N();
            }
            o.b("LISTENER_KEY_PLAYER_ACTIVITY");
            o.d("LISTENER_KEY_PLAYER_ACTIVITY");
        }
        this.bA.removeCallbacksAndMessages(null);
        if (this.ax != null) {
            this.ax.destroy();
        }
        super.onDestroyView();
        ((GaanaActivity) this.s).getWindow().clearFlags(128);
    }

    public QueueSlidingUpPanelLayout k() {
        return this.am;
    }

    private void a(PlayerTrack playerTrack) {
        this.mAppState.setPlayerStatus(true);
        String subtitleText = getSubtitleText(getPlayingTrack().getAlbumTitle(), getPlayingTrack().getArtistNames());
        boolean isParentalWarningEnabled = getPlayingTrack().isParentalWarningEnabled();
        subtitleText = subtitleText.trim();
        if (Constants.aG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.s.getString(R.string.CASTING_TO));
            stringBuilder.append(Constants.aH);
            subtitleText = stringBuilder.toString();
        }
        this.aC.setText(getPlayingTrack().getName());
        this.aD.setSelected(true);
        boolean z = Constants.l;
        if (isParentalWarningEnabled) {
            Util.a(this.aD, subtitleText);
        } else {
            this.aD.setText(subtitleText);
        }
        if (subtitleText.isEmpty()) {
            a(this.aD, getPlayingTrack().getName(), getPlayingTrack().getName(), new StyleSpan(1));
        } else {
            TextView textView = this.aD;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(getPlayingTrack().getName());
            stringBuilder2.append(" - ");
            stringBuilder2.append(subtitleText);
            a(textView, stringBuilder2.toString(), getPlayingTrack().getName(), new StyleSpan(1));
        }
        if (getPlayingTrack().isLocalMedia()) {
            Toolbar toolbar = (Toolbar) this.R.findViewById(R.id.toolbar);
            if (toolbar != null) {
                Menu menu = toolbar.getMenu();
                if (!(menu == null || menu.findItem(R.id.menu_add_to_playlist) == null)) {
                    menu.findItem(R.id.menu_add_to_playlist).setVisible(false);
                }
            }
        }
        this.ap.notifyDataSetChanged();
        if (PlayerManager.a(GaanaApplication.getContext()).s() > -1) {
            PlayerManager.a(GaanaApplication.getContext()).n().size();
        }
        al();
        U();
        c(this.mCurrentTrack.b());
        if (this.mPlayerManager.H() == null || this.mCurrentTrack.b().isLocalMedia()) {
            b(false);
        } else {
            b(true);
        }
        if (this.mCurrentTrack != null) {
            a((ImageView) this.R.findViewById(R.id.queue_panel_img_animation), getPlayingTrack());
        }
        this.R.findViewById(R.id.pull_up_text).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PlayerFragmentV4.this.bl) {
                    PlayerFragmentV4.this.am.h();
                }
            }
        });
    }

    private void h(int i) {
        if (this.mPlayerManager != null && this.mPlayerManager.m() == PlayerType.GAANA) {
            Track b = this.mPlayerManager.a(i).b();
            if (b == null) {
                if (this.mPlayerManager.i() != null) {
                    b = this.mPlayerManager.i().b();
                } else {
                    return;
                }
            }
            final ImageView imageView = (ImageView) this.R.findViewById(R.id.favourite_item);
            imageView.setTag(b.getBusinessObjId());
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PlayerFragmentV4.this.bq = view;
                    PlayerFragmentV4.this.a(imageView, b);
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
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.vector_more_option_favorite_white));
            }
        }
    }

    public void a(final ImageView imageView, final Track track) {
        if (track != null) {
            track.setBusinessObjType(BusinessObjectType.Tracks);
            com.managers.af a = com.managers.af.a(this.s, null);
            a.a("Player Screen");
            a.b(track.getBusinessObjId());
            a.a((int) R.id.favoriteMenu, (BusinessObject) track, new a() {
                public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
                    ImageView imageView = imageView;
                    if (track == null || !track.isFavorite().booleanValue()) {
                        imageView.setImageDrawable(PlayerFragmentV4.this.getResources().getDrawable(R.drawable.vector_more_option_favorite_white));
                        an.a().a("click", "ac", "", "player", "", "unfav", "", "");
                        return;
                    }
                    imageView.setImageResource(R.drawable.vector_more_option_favorited);
                    imageView.setPadding(PlayerFragmentV4.this.s.getResources().getDimensionPixelSize(R.dimen.dp10), PlayerFragmentV4.this.s.getResources().getDimensionPixelSize(R.dimen.dp10), PlayerFragmentV4.this.s.getResources().getDimensionPixelSize(R.dimen.dp10), PlayerFragmentV4.this.s.getResources().getDimensionPixelSize(R.dimen.dp10));
                    if (PlayerFragmentV4.this.bq != null) {
                        Animation loadAnimation = AnimationUtils.loadAnimation(PlayerFragmentV4.this.s, R.anim.favorite_tap_animation);
                        loadAnimation.setInterpolator(new com.a.a(0.2d, 20.0d));
                        PlayerFragmentV4.this.bq.startAnimation(loadAnimation);
                    }
                    an.a().a("click", "ac", "", "player", "", "fav", "", "");
                }
            });
        }
    }

    private void V() {
        this.am.setBackgroundColor(0);
        this.au = this.v.findViewById(R.id.queue_panel_button_save);
        this.at = (TextView) this.v.findViewById(R.id.queue_panel_cheveron_view);
        this.at.setOnClickListener(this);
        this.am.setBackgroundDrawable(new ColorDrawable(0));
        this.am.setPanelSlideListener(new QueueSlidingUpPanelLayout.b() {
            public void a(View view, float f) {
                float f2 = f;
                if (PlayerFragmentV4.this.j) {
                    PlayerFragmentV4.this.s();
                    return;
                }
                PlayerFragmentV4.this.br = f2;
                double d = (double) f2;
                if (d <= 0.8d) {
                    if (PlayerFragmentV4.this.at.getVisibility() != 4) {
                        if (PlayerFragmentV4.this.aH != null) {
                            PlayerFragmentV4.this.aH.setVisibility(8);
                        }
                        PlayerFragmentV4.this.E.setVisibility(0);
                        PlayerFragmentV4.this.X.setVisibility(0);
                        PlayerFragmentV4.this.aF = false;
                        ((ImageView) PlayerFragmentV4.this.R.findViewById(R.id.queue_panel_img_animation)).setVisibility(0);
                        PlayerFragmentV4.this.at.setVisibility(4);
                        PlayerFragmentV4.this.au.setVisibility(8);
                        PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_button_save).setVisibility(4);
                        PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_button_clear).setVisibility(4);
                        PlayerFragmentV4.this.p.setVisibility(0);
                        PlayerFragmentV4.this.aq.setVisibility(0);
                        PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_bottom_options).setVisibility(4);
                        PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_button_save).setOnClickListener(null);
                        PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_button_clear).setOnClickListener(null);
                        PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_bottom_options).setOnClickListener(null);
                        PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_bottom_options).setVisibility(8);
                        PlayerFragmentV4.this.W = (ProgressBar) PlayerFragmentV4.this.R.findViewById(R.id.queue_panel_bottom_progressBar);
                        PlayerFragmentV4.this.aB = (ProgressBar) PlayerFragmentV4.this.R.findViewById(R.id.queue_panel_bottom_progressBar2);
                        PlayerFragmentV4.this.aB.setVisibility(8);
                        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(PlayerFragmentV4.this.getPlayingTrack().getBusinessObjId()));
                        if (GaanaMusicService.s().m()) {
                            if (e == DownloadStatus.DOWNLOADING) {
                                PlayerFragmentV4.this.W.setVisibility(8);
                            }
                        } else if (e != DownloadStatus.DOWNLOADING) {
                            PlayerFragmentV4.this.W.setVisibility(8);
                        }
                        PlayerFragmentV4.this.R.findViewById(R.id.queue_panel_bottom_button).setVisibility(8);
                    }
                    PlayerFragmentV4.this.ag.stopQueueAnimation();
                    PlayerFragmentV4.this.bn = true;
                } else if (PlayerFragmentV4.this.au.getVisibility() != 0) {
                    if (PlayerFragmentV4.this.aH != null) {
                        PlayerFragmentV4.this.aH.setVisibility(8);
                    }
                    PlayerFragmentV4.this.E.setVisibility(8);
                    PlayerFragmentV4.this.aF = true;
                    PlayerFragmentV4.this.setGAScreenName("PlayerQueue", "PlayerQueue");
                    ((ImageView) PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_img_animation)).setVisibility(4);
                    PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_button_save).setVisibility(0);
                    PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_button_clear).setVisibility(0);
                    PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_button_save).setOnClickListener(PlayerFragmentV4.this);
                    PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_button_clear).setOnClickListener(PlayerFragmentV4.this);
                    PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_bottom_options).setOnClickListener(PlayerFragmentV4.this);
                    PlayerFragmentV4.this.v.findViewById(R.id.queue_panel_bottom_options).setVisibility(0);
                    PlayerFragmentV4.this.ah = (ImageView) PlayerFragmentV4.this.R.findViewById(R.id.queue_panel_bottom_button);
                    PlayerFragmentV4.this.W = (ProgressBar) PlayerFragmentV4.this.R.findViewById(R.id.queue_panel_bottom_progressBar);
                    PlayerFragmentV4.this.W.setVisibility(8);
                    PlayerFragmentV4.this.aB = (ProgressBar) PlayerFragmentV4.this.R.findViewById(R.id.queue_panel_bottom_progressBar2);
                    PlayerFragmentV4.this.ah.setOnClickListener(PlayerFragmentV4.this);
                    if (GaanaMusicService.s().m()) {
                        PlayerFragmentV4.this.aB.setVisibility(0);
                        PlayerFragmentV4.this.ah.setVisibility(8);
                    } else if (GaanaMusicService.t()) {
                        PlayerFragmentV4.this.aB.setVisibility(8);
                        PlayerFragmentV4.this.ah.setVisibility(0);
                        PlayerFragmentV4.this.ah.setImageDrawable(PlayerFragmentV4.this.av);
                    } else {
                        PlayerFragmentV4.this.aB.setVisibility(8);
                        PlayerFragmentV4.this.ah.setVisibility(0);
                        PlayerFragmentV4.this.ah.setImageDrawable(PlayerFragmentV4.this.U);
                    }
                    PlayerFragmentV4.this.at.setVisibility(0);
                    PlayerFragmentV4.this.ah();
                }
                float f3 = 2.0f * f2;
                PlayerFragmentV4.this.E.setAlpha(0.8f - f3);
                PlayerFragmentV4.this.ag.getRecyclerView().setAlpha(f3);
                PlayerFragmentV4.this.ag.getPlayer_queue_view_container().setAlpha(f3);
                PlayerFragmentV4.this.F.setAlpha(f3);
                if (f2 >= 0.05f && PlayerFragmentV4.this.F.getVisibility() != 0) {
                    PlayerFragmentV4.this.bn = true;
                } else if (f2 < 0.02f) {
                    PlayerFragmentV4.this.X();
                }
                if (d > 0.1d && !PlayerFragmentV4.this.aw) {
                    PlayerFragmentV4.this.aw = true;
                    if (PlayerFragmentV4.this.aH != null) {
                        PlayerFragmentV4.this.aH.setVisibility(8);
                    }
                }
            }

            public void a(View view, int i, int i2) {
                if (PlayerFragmentV4.this.br <= 0.65f) {
                    PlayerFragmentV4.this.az = i2;
                    if (i2 == 0) {
                        PlayerFragmentV4.this.bn = false;
                        PlayerFragmentV4.this.d();
                        if (i2 != i) {
                            PlayerFragmentV4.this.setGAScreenName("PlayerHomeScreen", "PlayerHomeScreen");
                        }
                        if (PlayerFragmentV4.this.mPlayerManager.s() == 0) {
                            ((LinearLayoutManager) PlayerFragmentV4.this.ag.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(PlayerFragmentV4.this.mPlayerManager.s(), 0);
                        } else {
                            ((LinearLayoutManager) PlayerFragmentV4.this.ag.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(PlayerFragmentV4.this.mPlayerManager.s(), Util.b(-30));
                        }
                        an.a().a("click", "ac", "", "queue", "", "close", "", "");
                        PlayerFragmentV4.this.aw = false;
                    } else if (i2 == 1) {
                        PlayerFragmentV4.this.bn = true;
                        if (PlayerFragmentV4.this.mPlayerManager.s() == 0) {
                            ((LinearLayoutManager) PlayerFragmentV4.this.ag.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(PlayerFragmentV4.this.mPlayerManager.s(), 0);
                        } else {
                            ((LinearLayoutManager) PlayerFragmentV4.this.ag.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(PlayerFragmentV4.this.mPlayerManager.s(), Util.b(-30));
                        }
                        an.a().a("click", "ac", "", "queue", "", "open", "", "");
                    } else {
                        PlayerFragmentV4.this.W();
                    }
                }
            }
        });
        if (this.mPlayerManager.n() == null || this.mPlayerManager.n().size() <= 0) {
            this.am.setSlidingEnabled(false);
            return;
        }
        View view = this.ag.getView(this.s, this.mPlayerManager.n(), this, this.v);
        this.ag.getPlayerQueueView().setVisibility(0);
        this.ag.getRecyclerView().setVisibility(0);
        view.setClickable(true);
        this.am.setScrollingView(this.ag.getRecyclerView());
        X();
    }

    private void W() {
        if (this.ag.getRecyclerView() != null && this.ag.getRecyclerView().getLayoutManager() != null) {
            if (this.mPlayerManager.s() == 0) {
                ((LinearLayoutManager) this.ag.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(this.mPlayerManager.s(), 0);
            } else {
                ((LinearLayoutManager) this.ag.getRecyclerView().getLayoutManager()).scrollToPositionWithOffset(this.mPlayerManager.s(), Util.b(-30));
            }
        }
    }

    private void X() {
        this.F.setAlpha(0.0f);
        this.ag.getRecyclerView().setAlpha(0.0f);
        this.ag.getPlayer_queue_view_container().setAlpha(0.0f);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.bv = context.getResources().getDrawable(R.drawable.player_header_actions_bg_white);
        this.by = context.getResources().getDrawable(R.drawable.player_header_actions_bg_white_current);
        this.bw = context.getResources().getDrawable(R.drawable.player_header_actions_bg);
        this.bx = context.getResources().getDrawable(R.drawable.player_header_actions_bg_current);
        this.bu = context.getResources().getDimensionPixelSize(R.dimen.bottom_player_action_container_height_v4);
        this.bt = L() + 100;
    }

    private void Y() {
        if (this.X != null) {
            int u;
            try {
                u = GaanaMusicService.s().u();
            } catch (IllegalStateException unused) {
                u = 0;
            }
            this.X.setMax(u);
            this.X.setSecondaryProgress(0);
            this.Y.setMax(u);
            this.Y.setSecondaryProgress(0);
            this.X.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    PlayerFragmentV4.this.a((float) PlayerFragmentV4.this.aM);
                    return false;
                }
            });
            this.X.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (PlayerFragmentV4.this.l) {
                        Util.d(PlayerFragmentV4.this.o - ((long) PlayerFragmentV4.this.k));
                    }
                    u.a().b("Player", "Seekbar Moved");
                    o.a(PlayerFragmentV4.this.getContext(), PlayerFragmentV4.this.X.getProgress());
                    PlayerFragmentV4.this.Z();
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    PlayerFragmentV4.this.o = (long) PlayerFragmentV4.this.k;
                    PlayerFragmentV4.this.a(0.0f, 0.0f);
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    PlayerFragmentV4.this.l = z;
                    r0 = new Object[2];
                    long j = (long) i;
                    r0[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) / 60);
                    r0[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % 60);
                    String format = String.format("%2d:%02d", r0);
                    PlayerFragmentV4.this.M.setText(format);
                    r15 = new Object[2];
                    long u = (long) GaanaMusicService.s().u();
                    r15[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) / 60);
                    r15[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) % 60);
                    PlayerFragmentV4.this.P.setText(String.format("%2d:%02d", r15));
                    PlayerFragmentV4.this.k = i;
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    ((Activity) PlayerFragmentV4.this.s).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    i = (int) ((((double) i) / ((double) GaanaMusicService.s().u())) * 100.0d);
                    int i2 = (displayMetrics.widthPixels * i) / 100;
                    PlayerFragmentV4.this.aM = i2;
                    if (PlayerFragmentV4.this.am.e()) {
                        PlayerFragmentV4.this.ag.setPlayerSeekAnimation((float) i2);
                    }
                    if (PlayerFragmentV4.this.l) {
                        PlayerFragmentV4.this.O.setTypeface(Util.i(PlayerFragmentV4.this.s));
                        PlayerFragmentV4.this.O.setText(format);
                        PlayerFragmentV4.this.M.setText(format);
                        PlayerFragmentV4.this.a((float) i2);
                        PlayerFragmentV4.this.am.g();
                        return;
                    }
                    PlayerFragmentV4.this.O.setTypeface(Util.i(PlayerFragmentV4.this.s));
                    PlayerFragmentV4.this.O.setText(format);
                    i2 = (PlayerFragmentV4.this.bs * i) / 100;
                    PlayerFragmentV4.this.M.setText(format);
                }
            });
            ac();
            if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
                this.T.setImageDrawable(this.y);
                this.ah.setImageDrawable(this.av);
            } else {
                this.T.setImageDrawable(this.A);
                this.ah.setImageDrawable(this.U);
            }
        }
    }

    public void a(float f) {
        Handler handler = new Handler(Looper.getMainLooper());
        a(0.0f, f);
    }

    public void a(float f, float f2) {
        this.bz = true;
        this.am.setSlidingEnabled(false);
        View findViewById = this.R.findViewById(R.id.playerSeekerBg);
        findViewById.setVisibility(0);
        ((FrameLayout) this.R.findViewById(R.id.seekerLayout)).setVisibility(0);
        if (findViewById.getVisibility() == 4 || findViewById.getVisibility() == 8) {
            findViewById.setVisibility(0);
        }
        findViewById.setLayoutParams(new FrameLayout.LayoutParams((int) f2, this.bt));
    }

    private void Z() {
        ((FrameLayout) this.R.findViewById(R.id.seekerLayout)).setVisibility(8);
        this.aq.setAlpha(1.0f);
        this.M.setText(this.O.getText());
        this.bz = false;
        this.am.setSlidingEnabled(true);
        this.R.findViewById(R.id.pull_up_text).setVisibility(8);
        this.R.findViewById(R.id.chevron_up).setVisibility(8);
        this.R.findViewById(R.id.tvPlayerStartTimer).setVisibility(0);
        this.R.findViewById(R.id.tvPlayerEndTimer).setVisibility(0);
        this.bn = false;
        e();
        d();
    }

    private void aa() {
        int u;
        try {
            u = GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
            u = 0;
        }
        o.a(getContext(), this.n);
        this.X.setProgress(this.n);
        this.X.setMax(u);
        this.X.setSecondaryProgress(this.n);
        this.X.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (PlayerFragmentV4.this.l) {
                    Util.d(PlayerFragmentV4.this.o - ((long) PlayerFragmentV4.this.k));
                }
                o.a(PlayerFragmentV4.this.getContext(), PlayerFragmentV4.this.X.getProgress());
                PlayerFragmentV4.this.Z();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                PlayerFragmentV4.this.o = (long) PlayerFragmentV4.this.k;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                PlayerFragmentV4.this.l = z;
                r0 = new Object[2];
                long j = (long) i;
                r0[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) / 60);
                r0[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % 60);
                PlayerFragmentV4.this.M.setText(String.format("%2d:%02d", r0));
                r15 = new Object[2];
                long u = (long) GaanaMusicService.s().u();
                r15[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) / 60);
                r15[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) % 60);
                PlayerFragmentV4.this.P.setText(String.format("%2d:%02d", r15));
                PlayerFragmentV4.this.k = i;
                PlayerFragmentV4.this.ag.setPlayerSeekAnimation((float) i);
            }
        });
        ac();
        if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
            this.T.setImageDrawable(this.y);
            this.ah.setImageDrawable(this.av);
            return;
        }
        this.T.setImageDrawable(this.A);
        this.ah.setImageDrawable(this.U);
    }

    private void ab() {
        this.X.setProgress(0);
        this.X.setSecondaryProgress(0);
        this.X.setMax(0);
        this.M.setText("0:00");
        this.P.setText("0:00");
    }

    private void ac() {
        int v;
        if (!(GaanaMusicService.s().m() || ad.a(this.s).o().booleanValue())) {
            int u;
            try {
                v = GaanaMusicService.s().v();
                u = GaanaMusicService.s().u();
            } catch (IllegalStateException unused) {
                v = 0;
                u = v;
            }
            if (!this.bz) {
                if (f() || this.aZ) {
                    this.Y.setProgress(v);
                    this.Y.setMax(u);
                    this.Y.setSelected(false);
                    this.Y.setSecondaryProgress((int) ((((double) GaanaMusicService.s().t()) * 0.01d) * ((double) GaanaMusicService.s().u())));
                } else {
                    this.X.setMax(u);
                    this.X.setProgress(v);
                    this.X.setSelected(false);
                    this.X.setSecondaryProgress((int) ((((double) GaanaMusicService.s().t()) * 0.01d) * ((double) GaanaMusicService.s().u())));
                }
            }
            this.X.setMax(u);
            this.X.setSelected(false);
            this.X.setSecondaryProgress((int) ((0.01d * ((double) GaanaMusicService.s().t())) * ((double) GaanaMusicService.s().u())));
            r6 = new Object[2];
            long j = (long) v;
            r6[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) / 60);
            r6[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % 60);
            CharSequence format = String.format("%2d:%02d", r6);
            r5 = new Object[2];
            long j2 = (long) u;
            r5[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j2) / 60);
            r5[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j2) % 60);
            String format2 = String.format("%2d:%02d", r5);
            if (v > 15958442) {
                format = "0:00";
                ab();
            }
            this.M.setText(format);
            if (u >= 0) {
                this.P.setText(format2);
            }
            this.aU.seekLrcToTime(j);
            if (!((format2.equalsIgnoreCase(" 0:00") && this.mPlayerManager.t() && this.mPlayerManager.v()) || !GaanaMusicService.t() || GaanaMusicService.s().m())) {
                AnonymousClass28 anonymousClass28 = new Runnable() {
                    public void run() {
                        PlayerFragmentV4.this.ac();
                    }
                };
                this.q.removeCallbacksAndMessages(null);
                this.q.postDelayed(anonymousClass28, 1000);
            }
        }
    }

    private void ad() {
        if (((GaanaActivity) this.s).isPlayerExpanded()) {
            G();
        }
        o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.ba);
        B();
        A();
        a(this.mCurrentTrack);
        if (!this.m) {
            ab();
        }
        if (this.mCurrentTrack != null && (PlayerManager.a(this.s).c(this.mCurrentTrack.b()) || PlayerManager.a(this.s).d(this.mCurrentTrack.b()))) {
            ((GaanaActivity) this.s).getWindow().addFlags(128);
        }
        this.mPlayerManager.d(null);
        this.ah.setImageDrawable(this.av);
        this.T.setImageDrawable(this.y);
        this.T.setVisibility(4);
        this.ah.setVisibility(8);
        this.V.setVisibility(0);
        this.V.setVisibility(0);
        this.mPlayerStates = PlayerStates.LOADING;
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(getPlayingTrack().getBusinessObjId()));
        l();
        if (this.az != 0) {
            this.aB.setVisibility(0);
        } else if (e == DownloadStatus.DOWNLOADING) {
            this.W.setVisibility(8);
        } else {
            ((ProgressBar) this.R.findViewById(R.id.queue_panel_bottom_progressBar)).setVisibility(0);
        }
        this.bi = this.mPlayerManager.s();
        a(this.bi);
        a(this.bi, this.mPlayerManager.s());
        this.bn = false;
        e(this.bi);
    }

    public void l() {
        if (this.aq != null) {
            this.aq.smoothScrollToPosition(this.mPlayerManager.s());
        }
        this.bA.postDelayed(new Runnable() {
            public void run() {
                if (PlayerFragmentV4.this.isAdded()) {
                    if (PlayerFragmentV4.this.ag != null) {
                        PlayerFragmentV4.this.ag.notifyDataSetChanged();
                    }
                    if (PlayerFragmentV4.this.ap != null) {
                        int g = PlayerFragmentV4.this.g();
                        PlayerFragmentV4.this.ap.updatePlaybackState(PlayerFragmentV4.this.aq.getViewHolder(g), g);
                    }
                    if (PlayerFragmentV4.this.mCurrentTrack != null) {
                        PlayerFragmentV4.this.a((ImageView) PlayerFragmentV4.this.R.findViewById(R.id.queue_panel_img_animation), PlayerFragmentV4.this.getPlayingTrack());
                    }
                    PlayerFragmentV4.this.a(PlayerFragmentV4.this.mPlayerManager.s());
                    PlayerFragmentV4.this.a(PlayerFragmentV4.this.mPlayerManager.s(), PlayerFragmentV4.this.mPlayerManager.s());
                }
            }
        }, 1000);
    }

    public void m() {
        if (this.ag != null) {
            this.ag.notifyDataSetChanged();
        }
        if (this.ap != null) {
            this.ap.notifyDataSetChanged();
        }
    }

    public void refreshPlayerStatus() {
        if (isAdded()) {
            if (this.u != null && this.u.isShowing()) {
                this.u.dismiss();
                this.u = null;
            }
            if (this.ag != null) {
                this.ag.notifyDataSetChanged();
            }
            if (!(this.mCurrentTrack == null || this.aA == null)) {
                U();
            }
        }
    }

    private void ae() {
        ((GaanaActivity) this.s).getWindow().clearFlags(128);
        this.T.setImageDrawable(this.A);
        this.T.setVisibility(0);
        this.ah.setImageDrawable(this.U);
        if (this.az != 0) {
            this.ah.setVisibility(0);
        }
        this.W.setVisibility(8);
        this.aB.setVisibility(8);
        this.V.setVisibility(8);
        this.mPlayerStates = PlayerStates.PAUSED;
        if (this.mCurrentTrack != null) {
            a((ImageView) this.R.findViewById(R.id.queue_panel_img_animation), getPlayingTrack());
        }
    }

    private void af() {
        this.T.setImageDrawable(this.y);
        this.T.setVisibility(0);
        this.ah.setImageDrawable(this.av);
        if (this.az != 0) {
            this.ah.setVisibility(0);
        }
        this.W.setVisibility(8);
        this.aB.setVisibility(8);
        this.V.setVisibility(8);
        if (!GaanaMusicService.s().m()) {
            if (this.m) {
                aa();
            } else {
                Y();
            }
        }
        this.mPlayerStates = PlayerStates.PLAYING;
        if (this.mCurrentTrack != null) {
            a((ImageView) this.R.findViewById(R.id.queue_panel_img_animation), getPlayingTrack());
        }
    }

    private void ag() {
        if (this.mPlayerManager != null) {
            this.mPlayerManager.a(null);
        }
        if (this.T != null) {
            this.T.setImageDrawable(this.A);
            this.T.setVisibility(0);
            this.ah.setImageDrawable(this.U);
            if (this.aF) {
                this.ah.setVisibility(0);
            } else {
                this.ah.setVisibility(8);
            }
            this.W.setVisibility(8);
            this.aB.setVisibility(8);
            this.V.setVisibility(8);
            this.mPlayerStates = PlayerStates.STOPPED;
            l();
        }
    }

    private void a(boolean z, boolean z2) {
        if (!PlayerManager.a(GaanaApplication.getContext()).e() && z) {
            r();
        }
        this.T.setImageDrawable(this.y);
        this.ah.setImageDrawable(this.av);
        this.bn = true;
        if (z2) {
            a(z, PlayerCommands.PLAY_PREVIOUS);
            return;
        }
        this.q.removeCallbacksAndMessages(null);
        ab();
    }

    private void b(boolean z, boolean z2) {
        if (this.u != null && this.u.isShowing()) {
            this.u.dismiss();
            this.u = null;
        }
        this.T.setImageDrawable(this.y);
        this.ah.setImageDrawable(this.av);
        this.bn = true;
        if (!PlayerManager.a(GaanaApplication.getContext()).e() && z) {
            r();
        }
        if (z2) {
            a(z, PlayerCommands.PLAY_NEXT);
            l();
            return;
        }
        this.q.removeCallbacksAndMessages(null);
        ab();
    }

    public void a(View view) {
        int i = false;
        Track b;
        switch (view.getId()) {
            case R.id.downloadTrackPlayer /*2131296970*/:
                a(getPlayingTrack());
                break;
            case R.id.equalizerIconRadio /*2131297058*/:
                ((BaseActivity) this.s).sendGAEvent("Player", "Equalizer", "Click");
                Util.t(this.s);
                break;
            case R.id.ll_queue_panel_header_text /*2131297620*/:
            case R.id.queue_panel_cheveron_view /*2131298146*/:
                if (!Constants.cY) {
                    if (this.am != null) {
                        ah();
                        if (!this.am.f()) {
                            this.am.g();
                        } else if (this.bl) {
                            this.am.h();
                        }
                        d.a().a("PREFERENCE_KEY_QUEUE_ANIMATION_INITIATED", true, false);
                        break;
                    }
                }
                ((GaanaActivity) this.s).displayFragment(JukePartyFragment.newInstance(JukeSessionManager.getInstance().getJukeSessionPlaylist(), -1, "", false));
                break;
                break;
            case R.id.lyricsButton /*2131297649*/:
            case R.id.lyricsButtonText /*2131297650*/:
                ai();
                break;
            case R.id.playerBtnNext /*2131297993*/:
            case R.id.playerBtnNextRadio /*2131297994*/:
                if (PlayerManager.a(this.s).x() == null || PlayerManager.a(this.s).x().b().isLocalMedia() || !Constants.aa || Constants.h > 0) {
                    if (this.an) {
                        N();
                        PlayerManager.a(this.s).j(false);
                    }
                    ((BaseActivity) this.s).sendGAEvent("Player", "Skip", "Player - Skip - Song");
                    o.f(getContext());
                    q();
                    break;
                }
                u.a().a("Shuffle Product", "Gaana+ popup", "Player Next");
                an.a().a("click", "ac", "", "player", "", "nxt", "", "");
                Util.a(this.s, BLOCK_ACTION.SKIP);
                return;
            case R.id.playerBtnPrev /*2131297996*/:
                q();
                if (PlayerManager.a(this.s).w() == null || PlayerManager.a(this.s).w().b().isLocalMedia() || !Constants.aa || Constants.h > 0) {
                    if (this.an) {
                        N();
                        PlayerManager.a(this.s).j(false);
                    }
                    ((BaseActivity) this.s).sendGAEvent("Player", "Skip", "Player - Skip - Song");
                    o.e(getContext());
                    break;
                }
                u.a().a("Shuffle Product", "Gaana+ popup", "Player Previous");
                an.a().a("click", "ac", "", "player", "", "prev", "", "");
                Util.a(this.s, BLOCK_ACTION.SKIP);
                return;
                break;
            case R.id.playerBtnRepeat /*2131297997*/:
                this.bn = false;
                e();
                d();
                a(view, true);
                break;
            case R.id.playerBtnShuffle /*2131297998*/:
                if (!Constants.ab) {
                    ((BaseActivity) this.s).sendGAEvent("Player", "Shuffle", "Player - Shuffle - Song");
                    if (PlayerManager.a(this.s).E() != null) {
                        PlayerManager.a(this.s).j(false);
                    }
                    d(view);
                    this.bn = false;
                    e();
                    d();
                    g(this.aI);
                    a(this.aI, this.mPlayerManager.s());
                    if (this.mCurrentTrack.b().isLocalMedia()) {
                        b(false);
                    }
                    if (this.ap != null) {
                        this.ap.notifyDataSetChanged();
                    }
                    if (this.ag != null) {
                        this.ag.updateArrayList(PlayerManager.a(GaanaApplication.getContext()).n());
                        break;
                    }
                }
                Util.a(this.s, BLOCK_ACTION.SHUFFLE);
                return;
                break;
            case R.id.playerButton /*2131297999*/:
            case R.id.playerButtonRadio /*2131298001*/:
            case R.id.queue_panel_bottom_button /*2131298140*/:
                this.bn = false;
                e();
                d();
                if (this.an) {
                    N();
                    PlayerManager.a(this.s).j(false);
                }
                if (GaanaMusicService.t() || GaanaMusicService.s().m()) {
                    u.a().b("Player", "Pause");
                    an.a().a("click", "ac", "", "player", "", "pause", "", "");
                } else {
                    u.a().b("Player", "Play");
                    an.a().a("click", "ac", "", "player", "", "play", "", "");
                }
                if (ad.a(this.s).o().booleanValue()) {
                    if (!GaanaMusicService.t()) {
                        n();
                        break;
                    } else {
                        o.d(getContext());
                        break;
                    }
                }
                n();
                break;
                break;
            case R.id.playerqueueLL /*2131298037*/:
                o();
                break;
            case R.id.qualityText /*2131298132*/:
            case R.id.streamingList /*2131298500*/:
            case R.id.streamingListLayoutRadio /*2131298501*/:
                if (this.an) {
                    N();
                    PlayerManager.a(this.s).j(false);
                }
                c(view);
                break;
            case R.id.queue_panel_bottom_options /*2131298141*/:
                b = this.mCurrentTrack.b();
                if (!(b == null || b.getBusinessObjType() == null || ad.a(this.s).p().booleanValue())) {
                    PopupWindowView instance = PopupWindowView.getInstance(this.s, null);
                    instance.setDownloadPopupListener(new DownloadPopupListener() {
                        public void onPopupClicked(String str, BusinessObject businessObject) {
                            if (DownloadManager.c().e(Integer.parseInt(str)) == DownloadStatus.DOWNLOADED) {
                                PlayerFragmentV4.this.b(businessObject);
                            } else {
                                PlayerFragmentV4.this.a(PlayerFragmentV4.this.getPlayingTrack());
                            }
                        }
                    });
                    instance.contextPopupWindow(b, true, new a() {
                        public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
                            PlayerFragmentV4.this.U();
                        }
                    }, true);
                    break;
                }
            case R.id.queue_panel_button_clear /*2131298144*/:
                if (!f()) {
                    if (PlayerManager.a(this.s).m() != PlayerType.GAANA) {
                        b = PlayerManager.a(this.s).i().b();
                        if (!(b == null || b.getBusinessObjType() == null || ad.a(this.s).p().booleanValue())) {
                            PopupWindowView.getInstance(this.s, null).contextPopupWindow(b, true, false);
                            break;
                        }
                    }
                    aj();
                    break;
                }
                e(g());
                return;
            case R.id.queue_panel_button_save /*2131298145*/:
                if (!f()) {
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
                    ak();
                    break;
                }
                e(g());
                return;
        }
    }

    private void ah() {
        if (this.f != null) {
            this.f.cancel();
        }
        if (this.e != null) {
            this.e.cancel();
        }
        this.v.clearAnimation();
        if (!d.a().b("PREFERENCE_KEY_SLIDE_LEFT_INITIATED", false, false)) {
            this.ag.setSwipeCoachmarkAnimation();
        }
    }

    private void ai() {
        u.a().b("Player", "Lyrics");
        if (!Util.j(this.s)) {
            aj.a().a(this.s, this.s.getString(R.string.error_msg_no_connection));
        } else if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.s).displayFeatureNotAvailableOfflineDialog(this.s.getString(R.string.this_feature));
        } else {
            PlayerTrack i = PlayerManager.a(this.s).i();
            if (i != null && i.b() != null) {
                String lyricsUrl = i.b().getLyricsUrl();
                if (!TextUtils.isEmpty(lyricsUrl)) {
                    Intent intent = new Intent(this.s, WebViewActivity.class);
                    intent.putExtra("EXTRA_WEBVIEW_URL", lyricsUrl);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                    intent.putExtra("title", "Lyrics");
                    this.s.startActivity(intent);
                }
            }
        }
    }

    private void aj() {
        u.a().a("PlayerQueue", "Clear queue", "PlayerQueue - Clear queue");
        an.a().a("click", "ac", "", "queue", "", "clear", "", "");
        CustomDialogView customDialogView = new CustomDialogView(this.s, getString(R.string.player_and_queue_clear), new OnButtonClickListener() {
            public void onNegativeButtonClick() {
            }

            public void onPositiveButtonClick() {
                PlayerFragmentV4.this.q();
                PlayerFragmentV4.this.r();
                PlayerFragmentV4.this.mPlayerManager.C();
                o.d(PlayerFragmentV4.this.s);
                if (PlayerFragmentV4.this.s instanceof GaanaActivity) {
                    ((GaanaActivity) PlayerFragmentV4.this.s).resetBottomNavigationBar();
                }
                an.a().a("click", "ac", "", "clear", "", "ok", "", "");
            }
        });
        customDialogView.getPositiveButton().setText(getString(R.string.continue_button));
        customDialogView.show();
    }

    private void ak() {
        u.a().a("PlayerQueue", "Save Queue", "PlayerQueue - Save Queue");
        an.a().a("click", "ac", "", "queue", "", "save", "", "");
        ArrayList n = PlayerManager.a(this.s).n();
        if (n != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = n.iterator();
            while (it.hasNext()) {
                arrayList.add(((PlayerTrack) it.next()).a(true));
            }
            BusinessObject businessObject = new BusinessObject();
            businessObject.setArrList(arrayList);
            if (this.mAppState.getCurrentUser().getLoginStatus()) {
                ap.a().a(this.s, arrayList, false);
                return;
            } else {
                com.managers.af.a(this.s, null).a((int) R.id.addToPlaylistMenu, businessObject, this.s.getResources().getString(R.string.login_bottom_sheet_playlist_text));
                return;
            }
        }
        aj.a().a(this.s, this.s.getString(R.string.no_songs_to_add));
    }

    public void n() {
        if (GaanaMusicService.t() || GaanaMusicService.s().m() || GaanaMusicService.s().l()) {
            this.T.setImageDrawable(this.y);
            this.ah.setImageDrawable(this.av);
            o.b(this.s, PauseReasons.MEDIA_BUTTON_TAP);
            return;
        }
        this.T.setImageDrawable(this.A);
        this.ah.setImageDrawable(this.U);
        o.a(this.s);
        if (ad.a(this.s).o().booleanValue()) {
            ad.a(this.s).j();
        }
    }

    private void c(View view) {
        String string;
        String str;
        final LayoutInflater layoutInflater = (LayoutInflater) this.s.getSystemService("layout_inflater");
        View inflate = layoutInflater.inflate(R.layout.stream_quality_layout, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.s);
        bottomSheetDialog.setContentView(inflate);
        ListView listView = (ListView) inflate.findViewById(R.id.streaming_list);
        if (ap.a().s()) {
            string = this.s.getResources().getString(R.string.stream_quality_hd);
            str = "High Definition";
        } else {
            string = this.s.getResources().getString(R.string.stream_quality_hd_gaanaplus);
            str = "High Definition (Gaana+ only)";
        }
        final String[] strArr = new String[]{this.s.getResources().getString(R.string.stream_quality_auto), string, this.s.getResources().getString(R.string.stream_quality_high), this.s.getResources().getString(R.string.stream_quality_med), this.s.getResources().getString(R.string.stream_quality_low)};
        String[] strArr2 = new String[]{"Auto", str, "High", "Medium", "Low"};
        final int[] iArr = new int[]{10004, 10003, 10002, 10001, 10000};
        listView.setSelector(this.B);
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
                    textView.setTextColor(PlayerFragmentV4.this.Q.data);
                }
                return view;
            }
        });
        final BottomSheetDialog bottomSheetDialog2 = bottomSheetDialog;
        final String[] strArr3 = strArr2;
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long j) {
                final d a = d.a();
                if (PlayerFragmentV4.this.mAppState.isAppInDataSaveMode()) {
                    ((BaseActivity) PlayerFragmentV4.this.s).displayFeatureNotAvailableDataSaveModeDialog(i, -1);
                    bottomSheetDialog2.dismiss();
                    return;
                }
                a.b("PREFERENCE_KEY_STREAMING_QUALITY", Constants.s(), false);
                aj a2;
                Context n;
                StringBuilder stringBuilder;
                if (i == 0) {
                    if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                        bottomSheetDialog2.dismiss();
                        return;
                    }
                    a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                    aj.a().a(PlayerFragmentV4.this.s, PlayerFragmentV4.this.s.getString(R.string.adjusting_sound_quality));
                    bottomSheetDialog2.dismiss();
                    PlayerFragmentV4.this.an();
                    PlayerFragmentV4.this.m = true;
                    PlayerFragmentV4.this.n = PlayerFragmentV4.this.X.getProgress();
                    o.b(PlayerFragmentV4.this.getContext(), 1);
                    u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                    an.a().a("click", "ac", String.valueOf(PlayerFragmentV4.this.ab.getText()), "player", strArr[i], "", "", "");
                } else if (i == 1) {
                    if (!ap.a().s()) {
                        u.a().a("Mini Player", "Set Streaming Quality", "Trial HD (Gaana+ only)");
                        an.a().a("click", "ac", String.valueOf(PlayerFragmentV4.this.ab.getText()), "player", strArr[i], "", "", "");
                        bottomSheetDialog2.dismiss();
                        Util.a(PlayerFragmentV4.this.s, PlayerFragmentV4.this.s.getResources().getString(R.string.subscribe_gaanaplus_hdq_msg), "HDQuality", new as() {
                            public void onTrialSuccess() {
                                a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                                PlayerFragmentV4.this.al();
                            }
                        });
                    } else if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                        bottomSheetDialog2.dismiss();
                    } else {
                        a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                        a2 = aj.a();
                        n = PlayerFragmentV4.this.s;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(PlayerFragmentV4.this.s.getString(R.string.changing_sound_quality));
                        stringBuilder.append(strArr[i]);
                        a2.a(n, stringBuilder.toString());
                        bottomSheetDialog2.dismiss();
                        PlayerFragmentV4.this.an();
                        PlayerFragmentV4.this.m = true;
                        PlayerFragmentV4.this.n = PlayerFragmentV4.this.X.getProgress();
                        o.b(PlayerFragmentV4.this.getContext(), 1);
                        u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                        an.a().a("click", "ac", String.valueOf(PlayerFragmentV4.this.ab.getText()), "player", strArr[i], "", "", "");
                    }
                } else if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                    bottomSheetDialog2.dismiss();
                } else {
                    a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                    a2 = aj.a();
                    n = PlayerFragmentV4.this.s;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(PlayerFragmentV4.this.s.getString(R.string.changing_sound_quality));
                    stringBuilder.append(strArr[i]);
                    a2.a(n, stringBuilder.toString());
                    bottomSheetDialog2.dismiss();
                    PlayerFragmentV4.this.an();
                    PlayerFragmentV4.this.m = true;
                    PlayerFragmentV4.this.n = PlayerFragmentV4.this.X.getProgress();
                    o.b(PlayerFragmentV4.this.getContext(), 1);
                    u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                    an.a().a("click", "ac", String.valueOf(PlayerFragmentV4.this.ab.getText()), "player", strArr[i], "", "", "");
                }
            }
        });
        bottomSheetDialog.show();
    }

    private void al() {
        boolean z = ad.a(this.s).o().booleanValue() || GaanaApplication.getInstance().isAppInOfflineMode() || !Util.j(this.s);
        if (!(this.mCurrentTrack == null || this.mCurrentTrack.a(true) == null)) {
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(this.mCurrentTrack.a(true).getBusinessObjId()));
            if (this.mCurrentTrack.a(true).isLocalMedia() || ((ap.a().o() || DownloadManager.c().j(this.mCurrentTrack.a(true).getBusinessObjId()).booleanValue()) && e != null && e == DownloadStatus.DOWNLOADED)) {
                z = true;
            }
        }
        if (z) {
            this.ab.setVisibility(8);
            return;
        }
        this.ab.setVisibility(8);
        CharSequence charSequence = "";
        GaanaApplication gaanaApplication;
        switch (d.a().b("PREFERENCE_KEY_STREAMING_QUALITY", Constants.s(), false)) {
            case 10000:
                charSequence = this.s.getString(R.string.low);
                break;
            case 10001:
                gaanaApplication = this.mAppState;
                if (!GaanaApplication.getLanguage(this.s).equalsIgnoreCase("English")) {
                    charSequence = this.s.getString(R.string.medium);
                    break;
                } else {
                    charSequence = this.s.getString(R.string.med_small);
                    break;
                }
            case 10002:
                charSequence = this.s.getString(R.string.high);
                break;
            case 10003:
                gaanaApplication = this.mAppState;
                if (!GaanaApplication.getLanguage(this.s).equalsIgnoreCase("English")) {
                    charSequence = this.s.getString(R.string.high_defination);
                    break;
                } else {
                    charSequence = this.s.getString(R.string.hd);
                    break;
                }
            case 10004:
                charSequence = this.s.getString(R.string.auto);
                break;
        }
        this.ab.setText(charSequence);
    }

    public void o() {
        this.t = PopupWindowView.getInstance(this.s, ((GaanaActivity) this.s).getCurrentFragment());
        this.t.populatePlayerQueue(getPlayingTrack(), this.ag);
    }

    /* Access modifiers changed, original: protected */
    public void a(final BusinessObject businessObject) {
        if (!businessObject.isLocalMedia()) {
            if (ap.a().o()) {
                com.managers.af.a(this.s, null).a((int) R.id.downloadMenu, businessObject);
            } else {
                ((BaseActivity) this.s).hideProgressDialog();
                final BaseGaanaFragment currentFragment = ((GaanaActivity) this.s).getCurrentFragment();
                if (!((currentFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) currentFragment).a() == 1)) {
                    Util.b(this.s, null, new as() {
                        public void onTrialSuccess() {
                            com.managers.af.a(PlayerFragmentV4.this.s, null).a((int) R.id.downloadMenu, businessObject);
                            currentFragment.showSnackbartoOpenMyMusic();
                            ((GaanaActivity) PlayerFragmentV4.this.s).updateSideBar();
                        }
                    });
                }
            }
        }
    }

    public void p() {
        this.i = new ax() {
        };
        PlayerManager.a(this.s).a(this.i);
    }

    public void q() {
        if (this.G == null || this.G.getTag() == null || !this.G.getTag().toString().equals("1")) {
            this.mPlayerManager.h(false);
            return;
        }
        this.G.setTag(InternalAvidAdSessionContext.AVID_API_LEVEL);
        a(this.G, true);
    }

    public void r() {
        if (this.G != null && this.G.getTag() != null) {
            this.G.setTag(InternalAvidAdSessionContext.AVID_API_LEVEL);
            a(this.G, true);
        }
    }

    public void b(int i) {
        if (this.J != null) {
            if (i == 0) {
                this.J.setImageDrawable(this.w);
            } else {
                this.J.setImageResource(this.D[i]);
            }
            this.J.setTag(Integer.valueOf(i));
        }
    }

    public void c(int i) {
        if (this.I != null) {
            if (i == 0) {
                this.I.setImageDrawable(this.x);
            } else {
                this.I.setImageResource(this.C[i]);
            }
            this.I.setTag(Integer.valueOf(i));
        }
    }

    private void a(View view, boolean z) {
        int parseInt = Integer.parseInt(view.getTag().toString());
        parseInt = parseInt == 2 ? 0 : parseInt + 1;
        if (parseInt == 1) {
            this.mPlayerManager.i(true);
        } else {
            this.mPlayerManager.i(false);
        }
        if (this.mPlayerManager.y()) {
            this.mPlayerManager.h(false);
            parseInt = 0;
        }
        if (parseInt == 0) {
            this.G.setImageDrawable(this.w);
        } else {
            this.G.setImageResource(this.D[parseInt]);
        }
        this.G.setTag(Integer.valueOf(parseInt));
        this.mPlayerManager.b(false);
        this.mPlayerManager.c(false);
        b(parseInt);
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
                if (!Constants.cY) {
                    o.b(getContext());
                }
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

    private void d(View view) {
        int parseInt = Integer.parseInt(view.getTag().toString());
        parseInt = parseInt == 1 ? 0 : parseInt + 1;
        if (parseInt == 0) {
            this.H.setImageDrawable(this.x);
        } else {
            this.H.setImageResource(this.C[parseInt]);
        }
        this.H.setTag(Integer.valueOf(parseInt));
        c(parseInt);
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
        if (this.an) {
            T();
            if (this.ag != null && this.mPlayerManager.m() == PlayerType.GAANA) {
                this.ag.getRecyclerView().setVisibility(0);
                this.ag.updateArrayList(PlayerManager.a(this.s).n());
                this.ap.updateAndNotifyArrayList(PlayerManager.a(this.s).n());
                return;
            } else if (this.ag != null && this.mPlayerManager.m() == PlayerType.GAANA_RADIO) {
                this.ag.getRecyclerView().setVisibility(8);
                return;
            } else {
                return;
            }
        }
        i();
    }

    public void a(BusinessObject businessObject, String str) {
        if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Similar songs to \"");
            stringBuilder.append(str);
            stringBuilder.append("\"");
            this.aG = new SimilarItemHorizontalScroll(this.s, ((GaanaActivity) this.s).getCurrentFragment(), new ai() {
                public void a(Track track, int i) {
                    track.setBusinessObjType(BusinessObjectType.Tracks);
                    PlayerFragmentV4.this.ap.enqueTrack(track, true, false);
                    PlayerFragmentV4.this.bn = true;
                    PlayerFragmentV4.this.a(PlayerFragmentV4.this.g(), PlayerFragmentV4.this.mPlayerManager.s());
                    an.a().a("click", "en", "", "queue", track.getBusinessObjId(), "rec", "", String.valueOf(i));
                }
            }).populateSimilar(businessObject, TrackItemView.class.getName(), stringBuilder.toString());
            if (this.ag.getCurrentView() != null) {
                this.aH = (LinearLayout) this.R.findViewById(R.id.similarSongsLayout);
                if (this.aH != null && this.aG != null && this.aG != null) {
                    this.aH.addView(this.aG);
                    if (this.aH != null) {
                        this.aH.setVisibility(4);
                    }
                    s();
                    am();
                }
            }
        }
    }

    private void am() {
        if (this.aH != null) {
            this.bn = true;
            e();
            this.aH.setVisibility(0);
            this.aH.bringToFront();
            ViewPropertyAnimator animate = this.aG.animate();
            if (VERSION.SDK_INT >= 16) {
                animate.withLayer();
            }
            animate.translationY(0.0f).setDuration(500).start();
            this.j = true;
            this.X.setVisibility(4);
        }
    }

    public void s() {
        if (this.aH != null) {
            ViewPropertyAnimator animate = this.aG.animate();
            if (VERSION.SDK_INT >= 16) {
                animate.withLayer();
            }
            animate.translationY((float) Util.b(200)).setDuration(500).start();
            this.X.setVisibility(0);
        }
        this.j = false;
        this.bn = false;
        d();
    }

    public void t() {
        if (this.aH != null) {
            this.aH.removeAllViews();
        }
        if (this.ag.getRecyclerView() != null) {
            ((FrameLayout.LayoutParams) this.ag.getRecyclerView().getLayoutParams()).bottomMargin = Util.b(0);
        }
    }

    private void a(boolean z, PlayerCommands playerCommands) {
        switch (playerCommands) {
            case PLAY_PREVIOUS:
                if (z) {
                    u.a().a("Player", "Previous", "");
                    return;
                }
                return;
            case PLAY_NEXT:
                if (z) {
                    if (GaanaMusicService.s().l() || GaanaMusicService.s().k()) {
                        this.T.setImageDrawable(this.A);
                        this.ah.setImageDrawable(this.U);
                    }
                    u.a().a("Player", "Next", "");
                    return;
                }
                this.mCurrentTrack = this.mPlayerManager.a(this.mPlayerManager.s());
                this.q.removeCallbacksAndMessages(null);
                ab();
                this.T.setImageDrawable(this.A);
                this.ah.setImageDrawable(this.U);
                return;
            default:
                return;
        }
    }

    private void an() {
        this.mCurrentTrack = PlayerManager.a(this.s).i();
        O();
        if (this.mCurrentTrack != null) {
            a(this.mCurrentTrack);
        }
        l();
    }

    public Context getContext() {
        if (isAdded() && this.s == null) {
            this.s = getActivity();
        }
        return this.s;
    }

    public void onClick(View view) {
        a(view);
    }

    public void onListUpdated() {
        if (this.ap != null) {
            this.ap.notifyDataSetChanged();
        }
        if (this.ag != null) {
            this.ag.updateQueueStatus();
        }
        ((GaanaActivity) this.s).updateMiniPlayerList();
    }

    public PlayerStates u() {
        return this.mPlayerStates;
    }

    private void ao() {
        this.V = (ProgressBar) this.R.findViewById(R.id.progressBarPlayer);
        this.K = (ImageView) this.R.findViewById(R.id.playerBtnNext);
        this.T = (ImageView) this.R.findViewById(R.id.playerButton);
        this.Z = this.R.findViewById(R.id.streamingList);
        this.aa = (ImageView) this.R.findViewById(R.id.videoButton);
        this.aN = this.R.findViewById(R.id.videoButtonText);
        this.ab = (TextView) this.R.findViewById(R.id.qualityText);
        this.M = (TextView) this.R.findViewById(R.id.tvPlayerStartTimer);
        this.O = (TextView) this.R.findViewById(R.id.tvPlayerDragTimer);
        this.K.setOnClickListener(this);
        this.T.setOnClickListener(this);
        this.Z.setOnClickListener(this);
        this.ab.setOnClickListener(this);
    }

    private void a(TextView textView, String str, String str2, StyleSpan styleSpan) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        int indexOf = str.indexOf(str2);
        int length = str2.length() + indexOf;
        spannableStringBuilder.setSpan(styleSpan, indexOf, length, 18);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen.player_bottom_moving_bold_text_size)), indexOf, length, 18);
        textView.setText(spannableStringBuilder);
    }

    /* Access modifiers changed, original: protected */
    public void a(ImageView imageView, BusinessObject businessObject) {
        if (imageView != null) {
            PlayerTrack i = PlayerManager.a(this.s).i();
            Object entityId;
            if (businessObject instanceof Item) {
                entityId = ((Item) businessObject).getEntityId();
            } else {
                entityId = businessObject.getBusinessObjId();
            }
            Drawable wrap = DrawableCompat.wrap(ContextCompat.getDrawable(this.s, R.drawable.ic_equalizer1_white_36dp));
            if (i != null && i.h().equals(entityId)) {
                if (u() == PlayerStates.PLAYING && GaanaMusicService.s().isPlaying()) {
                    AnimationDrawable animationDrawable = (AnimationDrawable) ContextCompat.getDrawable(this.s, R.drawable.ic_equalizer_white_36dp);
                    DrawableCompat.setTintList(animationDrawable, ColorStateList.valueOf(this.s.getResources().getColor(R.color.vector_active_icon_color)));
                    imageView.setImageDrawable(animationDrawable);
                    if (!this.am.e()) {
                        imageView.setVisibility(0);
                    }
                    animationDrawable.start();
                } else {
                    if (imageView.getAnimation() != null) {
                        imageView.getAnimation().cancel();
                    }
                    if (!this.am.e()) {
                        imageView.setVisibility(0);
                    }
                    if (Constants.l) {
                        int color = ContextCompat.getColor(this.s, R.color.first_line_color);
                        if (VERSION.SDK_INT >= 21) {
                            DrawableCompat.setTint(wrap, color);
                        } else {
                            DrawableCompat.setTint(DrawableCompat.wrap(wrap), ContextCompat.getColor(this.s, R.color.first_line_color));
                        }
                    }
                    imageView.setImageDrawable(wrap);
                }
                if (!this.aF && this.ah != null) {
                    this.ah.setVisibility(8);
                }
            } else if (imageView.getVisibility() == 0) {
                imageView.setVisibility(8);
                if (imageView.getAnimation() != null) {
                    imageView.getAnimation().cancel();
                }
                if (this.ah != null) {
                    this.ah.setVisibility(0);
                }
            }
        }
    }

    private void b(Track track) {
        if (track != null) {
            getSubtitleText(track.getAlbumTitle(), track.getArtistNames()).trim();
            if (Constants.aG) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.s.getString(R.string.CASTING_TO));
                stringBuilder.append(Constants.aH);
                stringBuilder.toString();
            }
            this.mPlayerManager.b(track);
        }
        if (TextUtils.isEmpty(this.aC.getText()) || TextUtils.isEmpty(this.aD.getText())) {
            CharSequence trim = getSubtitleText(getPlayingTrack().getAlbumTitle(), getPlayingTrack().getArtistNames()).trim();
            if (Constants.aG) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(this.s.getString(R.string.CASTING_TO));
                stringBuilder2.append(Constants.aH);
                trim = stringBuilder2.toString();
            }
            this.aC.setText(getPlayingTrack().getName());
            this.aD.setSelected(true);
            this.aD.setText(trim);
            if (getPlayingTrack() != null) {
                a((ImageView) this.R.findViewById(R.id.queue_panel_img_animation), getPlayingTrack());
            }
        }
    }

    public void b(ADSTATUS adstatus) {
        this.aO = true;
        K();
        J();
    }

    public void b(boolean z) {
        TypedArray obtainStyledAttributes = this.s.obtainStyledAttributes(R.styleable.VectorDrawables);
        if (z) {
            this.L.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(74, -1)));
            this.N.setAlpha(1.0f);
            this.L.setClickable(true);
            this.N.setClickable(true);
            this.N.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PlayerFragmentV4.this.am();
                }
            });
            this.L.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PlayerFragmentV4.this.am();
                }
            });
        } else {
            this.L.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(73, -1)));
            this.N.setAlpha(0.2f);
            this.L.setClickable(false);
            this.N.setClickable(false);
        }
        obtainStyledAttributes.recycle();
    }

    private void c(Track track) {
        TypedArray obtainStyledAttributes = this.s.obtainStyledAttributes(R.styleable.VectorDrawables);
        YouTubeVideo youTubeVideo = new YouTubeVideo();
        youTubeVideo.b(track.getTrackTitle());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(track.getVideoExpiryTime());
        stringBuilder.append("");
        youTubeVideo.e(stringBuilder.toString());
        if (!TextUtils.isEmpty(track.getHorizontalUrl())) {
            youTubeVideo.a(track.getHorizontalUrl());
            youTubeVideo.a(2);
        } else if (!TextUtils.isEmpty(track.getYoutubeId())) {
            youTubeVideo.c(track.getVerticalUrl());
            youTubeVideo.a(0);
        } else if (!TextUtils.isEmpty(track.getVideoUrl())) {
            youTubeVideo.a(track.getVideoUrl());
            youTubeVideo.a(0);
        }
        this.aa.setTag(youTubeVideo);
        this.aN.setTag(youTubeVideo);
        if (track != null && this.aa != null) {
            TextView textView;
            if (!Constants.cF || (TextUtils.isEmpty(track.getYoutubeId()) && TextUtils.isEmpty(track.getHorizontalUrl()))) {
                textView = (TextView) this.R.findViewById(R.id.videoButtonText);
                if (Constants.l) {
                    textView.setTextColor(getResources().getColor(R.color.black_alfa_20));
                } else {
                    textView.setTextColor(getResources().getColor(R.color.white_alfa_20));
                }
                this.aa.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(71, -1)));
                this.aa.setClickable(false);
                this.aN.setClickable(false);
                an.a().a("click", "ac", "", "player", "", "video", "", AvidBridge.APP_STATE_INACTIVE);
                return;
            }
            this.aa.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(72, -1)));
            textView = (TextView) this.R.findViewById(R.id.videoButtonText);
            if (Constants.l) {
                textView.setTextColor(getResources().getColor(R.color.black));
            } else {
                textView.setTextColor(getResources().getColor(R.color.white));
            }
            this.aa.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Util.a(PlayerFragmentV4.this.s, (YouTubeVideo) view.getTag(), GAANA_ENTRY_PAGE.PLAYER.name());
                    u.a().b("Player", "Video Feed");
                }
            });
            this.aN.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Util.a(PlayerFragmentV4.this.s, (YouTubeVideo) view.getTag(), GAANA_ENTRY_PAGE.PLAYER.name());
                }
            });
            this.aa.setClickable(true);
            this.aN.setClickable(true);
            an.a().a("click", "ac", "", "player", "", "video", "", "active");
        }
    }

    public void on_enque() {
        if (isAdded()) {
            this.ap.updateArrayList(PlayerManager.a(this.s).n());
            an();
        }
    }

    public void refreshList() {
        if (isAdded() && this.mCurrentTrack != null) {
            an();
            al();
            this.m = true;
            this.n = this.X.getProgress();
            o.b(getContext(), 1);
        }
    }

    public void v() {
        int g = g();
        this.ap.updateDownloadState(this.aq.getViewHolder(g), g);
    }

    public void d(int i) {
        this.ag.getListAdapter().notifyItemChanged(i);
    }

    public void on_deque() {
        if (isAdded()) {
            if (PlayerManager.a(this.s).n() == null || PlayerManager.a(this.s).n().size() <= 0) {
                this.am.setPanelSlideListener(null);
                ((GaanaActivity) this.s).popBackStackImmediate();
                ((GaanaActivity) this.s).getSlidingPanelLayout().a(3);
            } else {
                this.ap.updateArrayList(PlayerManager.a(this.s).n());
                if (this.s != null) {
                    ((Activity) this.s).runOnUiThread(new Runnable() {
                        public void run() {
                            PlayerFragmentV4.this.an();
                            if (PlayerManager.a(PlayerFragmentV4.this.s).l() <= 0) {
                                PlayerFragmentV4.this.i();
                            }
                        }
                    });
                }
            }
        }
    }

    public void updateCardAdapter(boolean z) {
        if (z && this.ap != null) {
            this.ap.updateAndNotifyArrayList(PlayerManager.a(GaanaApplication.getContext()).n());
        }
    }

    public void onLiveRadioUpdate() {
        if (isAdded()) {
            a(PlayerManager.a(GaanaApplication.getContext()).i());
        }
    }

    public void refreshForFavorite() {
        U();
    }

    private void b(final BusinessObject businessObject) {
        final String businessObjId = businessObject.getBusinessObjId();
        new CustomDialogView(this.s, (int) R.string.dialog_deletdownload_text, new OnButtonClickListener() {
            public void onNegativeButtonClick() {
            }

            public void onPositiveButtonClick() {
                if ((businessObject instanceof Track) || (businessObject instanceof OfflineTrack)) {
                    DownloadManager.c().d(businessObject.getBusinessObjId());
                } else {
                    DownloadManager.c().p(Integer.parseInt(businessObjId));
                    DownloadManager.c().d(Integer.parseInt(businessObjId));
                }
                PlayerFragmentV4.this.an();
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
        h(this.bi);
        if (com.managers.n.a().a(businessObject)) {
            an.a().a("click", "ac", "three dot menu", "player", "", "fav", "", "");
        } else {
            an.a().a("click", "ac", "three dot menu", "player", "", "unfav", "", "");
        }
    }

    public void b() {
        if (this.aZ) {
            c();
            return;
        }
        ((GaanaActivity) this.s).popBackStackImmediate();
        Fragment miniPlayer = ((GaanaActivity) this.s).getMiniPlayer();
        if (miniPlayer != null && (miniPlayer instanceof MiniPlayerFragment)) {
            ((MiniPlayerFragment) miniPlayer).p();
        } else if (miniPlayer != null && (miniPlayer instanceof MiniPlayerFragmentV4)) {
            ((MiniPlayerFragmentV4) miniPlayer).p();
        }
    }

    public void w() {
        new com.services.f(this.s).a(this.s.getString(R.string.gaana_text), getResources().getString(R.string.report_lyrics_text), Boolean.valueOf(true), getString(R.string.yes), getString(R.string.no), new com.services.f.b() {
            public void onCancelListner() {
            }

            public void onOkListner(String str) {
                URLManager uRLManager = new URLManager();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("https://api.gaana.com/lyrics/report?track_id=");
                stringBuilder.append(PlayerFragmentV4.this.mCurrentTrack.h());
                uRLManager.a(stringBuilder.toString());
                com.i.i.a().a(new af() {
                    public void onErrorResponse(BusinessObject businessObject) {
                    }

                    public void onRetreivalComplete(Object obj) {
                        aj.a().a(PlayerFragmentV4.this.s, PlayerFragmentV4.this.s.getResources().getString(R.string.thanks_for_report));
                    }
                }, uRLManager);
            }
        });
    }
}
