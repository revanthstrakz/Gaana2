package com.fragments;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.lrc.DefaultLrcBuilder;
import com.gaana.lrc.ILrcView.LrcViewListener;
import com.gaana.lrc.LrcRow;
import com.gaana.lrc.LrcView;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Tracks.Track;
import com.gaana.view.DownloadClickAnimation;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.gaana.view.item.PopupWindowView;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.i.i;
import com.library.controls.CrossFadeImageView;
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
import com.managers.ap;
import com.managers.ap.a;
import com.managers.e;
import com.managers.j;
import com.managers.u;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.PlayerConstants.PlayerCommands;
import com.player_framework.PlayerStatus;
import com.player_framework.PlayerStatus.PlayerStates;
import com.player_framework.f;
import com.player_framework.m;
import com.player_framework.n;
import com.player_framework.o;
import com.services.d;
import com.services.h;
import com.services.l.af;
import com.services.l.as;
import com.services.l.q;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;
import com.utilities.k;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class PlayerRadioFragmentV4 extends BaseFragment implements OnClickListener, DownloadPopupListener, b, a, j.a, q {
    private static final int ai = Util.b(128);
    private static int f;
    private TextView A;
    private CrossFadeImageView B;
    private SeekBar C;
    private SeekBar D;
    private LinearLayout E;
    private DownloadClickAnimation F;
    private ImageView G;
    private ImageView H;
    private ImageView I;
    private TextView J;
    private TextView K;
    private FrameLayout L;
    private LinearLayout M;
    private View N;
    private PublisherAdView O;
    private TextView P;
    private TextView Q;
    private FrameLayout R;
    private boolean S = false;
    private int T;
    private String U;
    private TextView V;
    private ConstraintLayout W;
    private LrcView X;
    private ImageView Y;
    private LinearLayout Z;
    n a = new n() {
        public void displayErrorToast(String str, int i) {
        }

        public void onPlayerRepeatReset(boolean z) {
        }

        public void onStreamingQualityChanged(int i) {
        }

        public void onPlayerPlay() {
            if (!PlayerRadioFragmentV4.this.isActivityDestroyed()) {
                PlayerRadioFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV4.this.K();
                    }
                });
            }
        }

        public void onPlayerPause() {
            if (!PlayerRadioFragmentV4.this.isActivityDestroyed()) {
                PlayerRadioFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV4.this.L();
                    }
                });
            }
        }

        public void onPlayerResume() {
            if (!PlayerRadioFragmentV4.this.isActivityDestroyed()) {
                PlayerRadioFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV4.this.M();
                    }
                });
            }
        }

        public void onPlayerStop() {
            if (!PlayerRadioFragmentV4.this.isActivityDestroyed()) {
                PlayerRadioFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV4.this.J();
                    }
                });
            }
        }

        public void onPlayPrevious(final boolean z, final boolean z2) {
            if (!PlayerRadioFragmentV4.this.isActivityDestroyed()) {
                PlayerRadioFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV4.this.a(z, z2);
                    }
                });
            }
        }

        public void onPlayNext(final boolean z, final boolean z2) {
            if (!PlayerRadioFragmentV4.this.isActivityDestroyed()) {
                PlayerRadioFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV4.this.b(z, z2);
                    }
                });
            }
        }

        public void displayErrorDialog(String str, ErrorType errorType) {
            if (!PlayerRadioFragmentV4.this.isActivityDestroyed()) {
                PlayerRadioFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV4.this.J();
                    }
                });
            }
        }
    };
    private TextView aa;
    private String ab;
    private boolean ac = false;
    private ImageView ad;
    private ImageView ae;
    private ImageView af;
    private ImageView ag;
    private m ah = new m() {
        public void onAdEventUpdate(f fVar, AdEvent adEvent) {
        }

        public void onBufferingUpdate(f fVar, int i) {
        }

        public void onInfo(f fVar, int i, int i2) {
        }

        public void onPrepared(f fVar) {
            if (!PlayerRadioFragmentV4.this.isActivityDestroyed()) {
                PlayerRadioFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (GaanaMusicService.s().isPlaying()) {
                            PlayerRadioFragmentV4.this.mPlayerStates = PlayerStates.PLAYING;
                            if (PlayerRadioFragmentV4.this.mCurrentTrack != null) {
                                PlayerRadioFragmentV4.this.a((ImageView) PlayerRadioFragmentV4.this.h.findViewById(R.id.queue_panel_img_animation), PlayerRadioFragmentV4.this.getPlayingTrack());
                            }
                        }
                        if (PlayerRadioFragmentV4.this.p) {
                            PlayerRadioFragmentV4.this.F();
                            PlayerRadioFragmentV4.this.I();
                            PlayerRadioFragmentV4.this.p = false;
                            return;
                        }
                        PlayerRadioFragmentV4.this.E();
                        PlayerRadioFragmentV4.this.I();
                    }
                });
            }
        }

        public void onError(f fVar, int i, int i2) {
            if (!PlayerRadioFragmentV4.this.isActivityDestroyed()) {
                if (i == -1000 || i == -1001) {
                    PlayerRadioFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            PlayerRadioFragmentV4.this.J();
                        }
                    });
                }
            }
        }

        public void onCompletion(f fVar) {
            PlayerRadioFragmentV4.this.mPlayerStates = PlayerStates.STOPPED;
            PlayerRadioFragmentV4.this.n();
        }
    };
    private GestureDetectorCompat aj;
    private boolean ak = false;
    private View al;
    private boolean am = false;
    private boolean an = false;
    private int ao = 0;
    private long ap = 0;
    private int aq;
    private String ar = "0";
    BaseFragment b;
    TimerTask c;
    final Handler d = new Handler();
    Timer e = new Timer();
    private PlayerMaterialActionBar g;
    private View h;
    private CrossFadeImageView i;
    private TextView j;
    private LinearLayout k;
    private ImageView l;
    private ProgressBar m;
    private ImageView n;
    private Toolbar o;
    private boolean p;
    private TypedValue q;
    private final Handler r = new Handler();
    private Drawable s;
    private Drawable t;
    private Drawable u;
    private Drawable v;
    private TextView w;
    private TextView x;
    private View y;
    private View z;

    /* renamed from: com.fragments.PlayerRadioFragmentV4$25 */
    static /* synthetic */ class AnonymousClass25 {
        static final /* synthetic */ int[] a = new int[PlayerCommands.values().length];

        static {
            try {
                a[PlayerCommands.PLAY_NEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void a(ADSTATUS adstatus) {
    }

    public void d() {
    }

    public void d(ADSTATUS adstatus) {
    }

    public void on_deque() {
    }

    public void on_enque() {
    }

    public void refreshForFavorite() {
    }

    public void updateCardAdapter(boolean z) {
    }

    private void m() {
        this.mCurrentTrack = this.mPlayerManager.i();
        if (this.mCurrentTrack == null || this.mCurrentTrack.b() == null) {
            this.T = 0;
            return;
        }
        String lyricsType = this.mCurrentTrack.b().getLyricsType();
        this.U = this.mCurrentTrack.b().getLyricsUrl();
        if (TextUtils.isEmpty(this.U)) {
            this.T = 0;
        } else if (TextUtils.isEmpty(lyricsType)) {
            this.T = 1;
        } else if (lyricsType.equalsIgnoreCase("lrc")) {
            this.T = 2;
        } else if (lyricsType.equalsIgnoreCase("txt")) {
            this.T = 3;
        } else {
            this.T = 1;
        }
    }

    private void n() {
        c();
        this.W.setVisibility(8);
        this.X.setClickable(false);
        this.V.setVisibility(8);
        this.V.setClickable(true);
        this.J.setVisibility(8);
        this.mPlayerManager.a(null);
        this.X.setLrc(null);
    }

    private void o() {
        this.J = (TextView) this.h.findViewById(R.id.lyricsTextButton);
        this.J.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!Util.j(PlayerRadioFragmentV4.this.mContext) || PlayerRadioFragmentV4.this.mAppState.isAppInOfflineMode()) {
                    ap.a().f(PlayerRadioFragmentV4.this.mContext);
                    return;
                }
                ((BaseActivity) PlayerRadioFragmentV4.this.mContext).sendGAEvent("Player", "Lyrics", "Player - Lyrics");
                Intent intent = new Intent(PlayerRadioFragmentV4.this.mContext, WebViewActivity.class);
                intent.putExtra("EXTRA_WEBVIEW_URL", PlayerRadioFragmentV4.this.U);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                intent.putExtra("title", "Lyrics");
                PlayerRadioFragmentV4.this.mContext.startActivity(intent);
            }
        });
        this.V = (TextView) this.h.findViewById(R.id.lyrics_text_view);
        this.V.setMovementMethod(new ScrollingMovementMethod());
        this.V.setTypeface(Util.h(this.mContext));
        this.V.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerRadioFragmentV4.this.p();
            }
        });
        this.W = (ConstraintLayout) this.h.findViewById(R.id.lrc_container);
        this.X = (LrcView) this.h.findViewById(R.id.main_lrc_view);
        this.Y = (ImageView) this.h.findViewById(R.id.lrcplay_icon);
        this.aa = (TextView) this.h.findViewById(R.id.highlightrow_time);
        this.Z = (LinearLayout) this.h.findViewById(R.id.lyrics_banner);
        ((TextView) this.Z.findViewById(R.id.banner_text)).setTypeface(Util.h(this.mContext));
        this.Z.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PlayerRadioFragmentV4.this.mPlayerManager == null || PlayerRadioFragmentV4.this.mPlayerManager.o() == null || PlayerRadioFragmentV4.this.mPlayerManager.o().size() <= 0) {
                    aj.a().a(PlayerRadioFragmentV4.this.mContext, "Sorry! Lyrics are not availble for this track");
                    return;
                }
                BaseGaanaFragment lyricsBannerFragment = new LyricsBannerFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("lrc_type", Integer.valueOf(PlayerRadioFragmentV4.this.T));
                lyricsBannerFragment.setArguments(bundle);
                ((GaanaActivity) PlayerRadioFragmentV4.this.mContext).displayFragment(lyricsBannerFragment);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Lyrics Banner");
                stringBuilder.append(PlayerRadioFragmentV4.this.T);
                u.a().b("Lyrics", stringBuilder.toString());
            }
        });
        this.Y.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                u.a().b("Lyrics", "Lyrics Seek Play");
                PlayerRadioFragmentV4.this.Y.setVisibility(8);
                PlayerRadioFragmentV4.this.aa.setVisibility(8);
                if (!(GaanaMusicService.t() || GaanaMusicService.s().m() || GaanaMusicService.s().l())) {
                    PlayerRadioFragmentV4.this.f();
                }
                PlayerRadioFragmentV4.this.X.seekLrc();
            }
        });
        this.X.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerRadioFragmentV4.this.Y.setVisibility(8);
                PlayerRadioFragmentV4.this.aa.setVisibility(8);
                PlayerRadioFragmentV4.this.q();
            }
        });
        this.X.setListener(new LrcViewListener() {
            public void onLrcSeeked(int i, LrcRow lrcRow) {
                o.a(PlayerRadioFragmentV4.this.getContext(), (int) lrcRow.time);
                PlayerRadioFragmentV4.this.C.setSecondaryProgress((int) lrcRow.time);
            }

            public void onLrcClicked() {
                PlayerRadioFragmentV4.this.Y.setVisibility(8);
                PlayerRadioFragmentV4.this.aa.setVisibility(8);
            }

            public void onLrcScrollStateChanged(boolean z) {
                if (z) {
                    PlayerRadioFragmentV4.this.Y.setVisibility(0);
                    PlayerRadioFragmentV4.this.aa.setVisibility(0);
                    PlayerRadioFragmentV4.this.aa.setText(PlayerRadioFragmentV4.this.X.getHighlightRowTime());
                    return;
                }
                PlayerRadioFragmentV4.this.Y.setVisibility(8);
                PlayerRadioFragmentV4.this.aa.setVisibility(8);
            }
        });
        this.X.setClickable(false);
        D();
    }

    public void a() {
        if (!this.ac) {
            if (this.T == 2) {
                q();
            } else if (this.T == 3) {
                p();
            }
        }
    }

    public void c() {
        if (!this.ac) {
            return;
        }
        if (this.T == 2) {
            q();
        } else if (this.T == 3) {
            p();
        }
    }

    private void p() {
        LayoutParams layoutParams = (LayoutParams) this.V.getLayoutParams();
        if (this.ac) {
            layoutParams.height = ai;
            layoutParams.setMargins(Util.b(56), 0, Util.b(56), Util.b((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
            this.Z.setVisibility(4);
            this.V.requestLayout();
            e(false);
            return;
        }
        layoutParams.height = -1;
        layoutParams.setMargins(Util.b(86), Util.b(76), 0, Util.b(120));
        if (Constants.aY) {
            this.Z.setVisibility(0);
        }
        this.V.requestLayout();
        e(true);
    }

    private void q() {
        LayoutParams layoutParams;
        if (this.ac) {
            layoutParams = (LayoutParams) this.W.getLayoutParams();
            layoutParams.height = ai;
            layoutParams.setMargins(0, 0, 0, Util.b((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
            this.Z.setVisibility(4);
            this.ac = false;
            this.W.requestLayout();
            e(false);
            return;
        }
        layoutParams = (LayoutParams) this.W.getLayoutParams();
        layoutParams.height = -1;
        layoutParams.setMargins(0, Util.b(86), 0, Util.b(120));
        if (Constants.aY) {
            this.Z.setVisibility(0);
        }
        this.ac = true;
        this.W.requestLayout();
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

    private void r() {
        if (Util.j(this.mContext) && !GaanaApplication.getInstance().isAppInOfflineMode()) {
            URLManager uRLManager = new URLManager();
            uRLManager.a(this.U);
            uRLManager.a(String.class);
            i.a().a(new af() {
                public void onRetreivalComplete(Object obj) {
                    PlayerRadioFragmentV4.this.ab = (String) obj;
                    if (PlayerRadioFragmentV4.this.T == 2) {
                        PlayerRadioFragmentV4.this.ab = PlayerRadioFragmentV4.this.a(PlayerRadioFragmentV4.this.ab);
                    }
                    h.a().a(new TaskListner() {
                        public void doBackGroundTask() {
                            switch (PlayerRadioFragmentV4.this.T) {
                                case 2:
                                    PlayerRadioFragmentV4.this.mPlayerManager.a(new DefaultLrcBuilder().getLrcRows(PlayerRadioFragmentV4.this.ab));
                                    return;
                                case 3:
                                    List arrayList = new ArrayList();
                                    String[] split = PlayerRadioFragmentV4.this.ab.split("\n");
                                    int i = 0;
                                    int length = split.length;
                                    int i2 = 0;
                                    while (i < length) {
                                        long j = (long) i2;
                                        i2++;
                                        arrayList.add(new LrcRow(null, j, split[i]));
                                        i++;
                                    }
                                    PlayerRadioFragmentV4.this.mPlayerManager.a(arrayList);
                                    return;
                                default:
                                    PlayerRadioFragmentV4.this.mPlayerManager.a(null);
                                    return;
                            }
                        }

                        public void onBackGroundTaskCompleted() {
                            switch (PlayerRadioFragmentV4.this.T) {
                                case 2:
                                    PlayerRadioFragmentV4.this.X.setLrc(PlayerRadioFragmentV4.this.mPlayerManager.o());
                                    PlayerRadioFragmentV4.this.X.seekLrcToTime(0);
                                    PlayerRadioFragmentV4.this.W.setVisibility(0);
                                    PlayerRadioFragmentV4.this.V.setVisibility(8);
                                    PlayerRadioFragmentV4.this.J.setVisibility(8);
                                    PlayerRadioFragmentV4.this.X.setClickable(true);
                                    return;
                                case 3:
                                    PlayerRadioFragmentV4.this.X.setLrc(PlayerRadioFragmentV4.this.mPlayerManager.o());
                                    PlayerRadioFragmentV4.this.W.setVisibility(8);
                                    PlayerRadioFragmentV4.this.V.setText(PlayerRadioFragmentV4.this.ab);
                                    PlayerRadioFragmentV4.this.V.setVisibility(0);
                                    PlayerRadioFragmentV4.this.J.setVisibility(8);
                                    return;
                                default:
                                    PlayerRadioFragmentV4.this.X.setLrc(PlayerRadioFragmentV4.this.mPlayerManager.o());
                                    PlayerRadioFragmentV4.this.W.setVisibility(8);
                                    PlayerRadioFragmentV4.this.V.setVisibility(8);
                                    return;
                            }
                        }
                    }, -1);
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    PlayerRadioFragmentV4.this.mPlayerManager.a(null);
                    PlayerRadioFragmentV4.this.ab = null;
                }
            }, uRLManager);
        }
    }

    private void b(boolean z) {
        LayoutParams layoutParams;
        int b;
        if (z) {
            switch (this.T) {
                case 1:
                    layoutParams = (LayoutParams) this.J.getLayoutParams();
                    if (this.S) {
                        layoutParams.setMargins(0, 0, 0, Util.b(80));
                    } else {
                        layoutParams.setMargins(0, 0, 0, Util.b(30));
                    }
                    this.J.bringToFront();
                    return;
                case 2:
                    layoutParams = (LayoutParams) this.W.getLayoutParams();
                    layoutParams.height = ai;
                    if (this.S) {
                        layoutParams.setMargins(0, 0, 0, Util.b(60));
                    } else {
                        layoutParams.setMargins(0, 0, 0, Util.b(10));
                    }
                    this.W.bringToFront();
                    return;
                case 3:
                    layoutParams = (LayoutParams) this.V.getLayoutParams();
                    layoutParams.height = ai;
                    b = Util.b(56);
                    if (this.S) {
                        layoutParams.setMargins(b, b, b, Util.b(60));
                    } else {
                        layoutParams.setMargins(b, b, b, Util.b(10));
                    }
                    this.V.bringToFront();
                    return;
                default:
                    return;
            }
        }
        switch (this.T) {
            case 1:
                layoutParams = (LayoutParams) this.J.getLayoutParams();
                if (this.S) {
                    layoutParams.setMargins(0, 0, 0, Util.b(220));
                } else {
                    layoutParams.setMargins(0, 0, 0, Util.b(170));
                }
                this.J.bringToFront();
                return;
            case 2:
                layoutParams = (LayoutParams) this.W.getLayoutParams();
                layoutParams.height = ai;
                if (this.S) {
                    layoutParams.setMargins(0, 0, 0, Util.b((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
                } else {
                    layoutParams.setMargins(0, 0, 0, Util.b(200));
                }
                this.W.bringToFront();
                return;
            case 3:
                layoutParams = (LayoutParams) this.V.getLayoutParams();
                layoutParams.height = ai;
                b = Util.b(56);
                if (this.S) {
                    layoutParams.setMargins(b, b, b, Util.b((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
                } else {
                    layoutParams.setMargins(b, b, b, Util.b(200));
                }
                this.V.bringToFront();
                return;
            default:
                return;
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        ((GaanaActivity) this.mContext).setPlayerFullScreen(true);
        ((GaanaActivity) this.mContext).setmCurrentPlayerFragment(this);
        this.h = layoutInflater.inflate(R.layout.fragment_player_radio_v4, viewGroup, false);
        this.i = (CrossFadeImageView) this.h.findViewById(R.id.player_image);
        this.j = (TextView) this.h.findViewById(R.id.tvPlayerStartTimer);
        this.k = (LinearLayout) this.h.findViewById(R.id.streamingListLayoutRadio);
        this.l = (ImageView) this.h.findViewById(R.id.playerButton);
        this.m = (ProgressBar) this.h.findViewById(R.id.progressBarPlayer);
        this.n = (ImageView) this.h.findViewById(R.id.playerBtnNext);
        this.o = (Toolbar) this.h.findViewById(R.id.toolbar);
        this.g = new PlayerMaterialActionBar(getContext(), PlayerVersion.PlayerV4);
        this.o.addView(this.g);
        int[] iArr = new int[]{R.attr.bottom_pause_button, R.attr.bottom_play_button, R.attr.selector_btn_global_bg_transparent, R.attr.miniplayer_play};
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.t = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(66, -1));
        this.s = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(68, -1));
        this.u = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(86, -1));
        this.v = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(31, -1));
        this.w = (TextView) this.h.findViewById(R.id.queue_panel_main_text_bottom);
        this.x = (TextView) this.h.findViewById(R.id.queue_panel_secondary_text_bottom);
        this.y = this.g.findViewById(R.id.tracker_previous);
        this.z = this.g.findViewById(R.id.tracker_next);
        this.A = (TextView) this.g.findViewById(R.id.radioName);
        this.C = (SeekBar) this.h.findViewById(R.id.seekBar);
        this.E = (LinearLayout) this.h.findViewById(R.id.optionLayout);
        this.G = (ImageView) this.h.findViewById(R.id.queue_panel_download_button);
        this.H = (ImageView) this.h.findViewById(R.id.menu_option_img);
        this.H.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerRadioFragmentV4.this.y();
            }
        });
        this.B = (CrossFadeImageView) this.g.findViewById(R.id.tracker_img);
        this.I = (ImageView) this.h.findViewById(R.id.videoButton);
        this.K = (TextView) this.h.findViewById(R.id.videoButtonText);
        this.L = (FrameLayout) this.h.findViewById(R.id.playerTopLayout);
        this.M = (LinearLayout) this.h.findViewById(R.id.cardLayoutContainer);
        this.N = this.h.findViewById(R.id.timeSeekerBlackView);
        this.J = (TextView) this.h.findViewById(R.id.lyricsTextButton);
        this.P = (TextView) this.g.findViewById(R.id.trackText);
        this.Q = (TextView) this.g.findViewById(R.id.albumText);
        this.R = (FrameLayout) this.h.findViewById(R.id.ll_queue_container);
        this.ad = (ImageView) this.g.findViewById(R.id.menu_icon);
        this.ae = (ImageView) this.g.findViewById(R.id.menu_icon_back);
        this.af = (ImageView) this.g.findViewById(R.id.gaana_logo_header);
        this.ag = (ImageView) this.g.findViewById(R.id.report_lrc_button);
        this.D = (SeekBar) this.h.findViewById(R.id.seekBarBottom);
        this.D.setPadding(0, 0, 0, 0);
        this.D.setThumb(new ColorDrawable(getResources().getColor(17170445)));
        this.D.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.R.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.i.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerRadioFragmentV4.this.c(false);
            }
        });
        s();
        obtainStyledAttributes.recycle();
        this.q = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.song_quality_color, this.q, true);
        v();
        this.n.setOnClickListener(this);
        this.l.setOnClickListener(this);
        x();
        this.C.setPadding(0, 0, 0, 0);
        this.C.setFocusable(false);
        this.C.setThumb(new ColorDrawable(getResources().getColor(17170445)));
        this.C.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        setGAScreenName("PlayerHomeScreen", "PlayerHomeScreen");
        GaanaApplication.getInstance().setGADParameter("Player Page");
        u();
        t();
        d();
        return this.h;
    }

    private void s() {
        this.b = ((GaanaActivity) this.mContext).getmCurrentPlayerFragment();
        this.aj = new GestureDetectorCompat(this.mContext, new OnGestureListener() {
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            public void onLongPress(MotionEvent motionEvent) {
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return false;
            }

            public void onShowPress(MotionEvent motionEvent) {
            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (Math.abs(motionEvent.getY() - motionEvent2.getY()) <= 120.0f) {
                    return false;
                }
                ((GaanaActivity) PlayerRadioFragmentV4.this.mContext).popBackStackImmediate();
                return true;
            }
        });
        this.i.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return PlayerRadioFragmentV4.this.aj.onTouchEvent(motionEvent);
            }
        });
    }

    public void a(boolean z) {
        this.ak = z;
    }

    public void e() {
        if (this.c != null) {
            this.c.cancel();
        }
    }

    private void a(TextView textView, String str, String str2, StyleSpan styleSpan) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        int indexOf = str.indexOf(str2);
        int length = str2.length() + indexOf;
        spannableStringBuilder.setSpan(styleSpan, indexOf, length, 18);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(this.mContext.getResources().getDimensionPixelSize(R.dimen.player_bottom_moving_bold_text_size)), indexOf, length, 18);
        textView.setText(spannableStringBuilder);
    }

    public void onResume() {
        super.onResume();
        ((GaanaActivity) this.mContext).setmCurrentPlayerFragment(this);
        ((GaanaActivity) this.mContext).setPlayerFullScreen(true);
        t();
        C();
        if (this.O != null) {
            this.O.resume();
        }
    }

    private void t() {
        boolean a = a(this.mContext);
        if (a) {
            a = getResources().getBoolean(R.bool.isPlayerAdEnabled);
        }
        LinearLayout linearLayout = (LinearLayout) this.h.findViewById(R.id.llNativeAdSlot);
        if (a && this.S) {
            linearLayout.setVisibility(0);
            linearLayout.bringToFront();
            return;
        }
        linearLayout.setVisibility(8);
    }

    private void u() {
        ColombiaAdViewManager.a().e();
        if (e.W == 0) {
            boolean z = getResources().getBoolean(R.bool.isPlayerAdEnabled);
            if (this.mContext != null && ap.a().b(this.mContext) && z) {
                if (this.O == null) {
                    this.O = new PublisherAdView(this.mContext);
                }
                ColombiaAdViewManager.a().b(this.mContext, this.h, e.y, this.O, this, "");
            }
        }
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

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void v() {
        this.o = (Toolbar) this.h.findViewById(R.id.toolbar);
        this.o.setContentInsetsAbsolute(0, 0);
        this.o.getMenu().clear();
        this.o.setBackgroundColor(getResources().getColor(R.color.transparent_color));
        this.g.setToolbar(this.o);
        this.o.bringToFront();
    }

    private void w() {
        switch (this.T) {
            case 1:
                this.J.setVisibility(0);
                return;
            case 2:
                this.W.setVisibility(0);
                return;
            case 3:
                this.V.setVisibility(0);
                return;
            default:
                this.W.setVisibility(8);
                this.V.setVisibility(8);
                this.J.setVisibility(8);
                return;
        }
    }

    private void x() {
        if (ad.a(this.mContext).p().booleanValue()) {
            this.n.setVisibility(8);
        } else if (ad.a(this.mContext).o().booleanValue()) {
            this.n.setVisibility(8);
        } else {
            this.n.setVisibility(0);
        }
    }

    private void y() {
        BusinessObject b = PlayerManager.a(this.mContext).i().b();
        if (b == null) {
            b = PlayerManager.a(this.mContext).i().b();
        }
        b.setBusinessObjType(BusinessObjectType.Tracks);
        if (b == null || PlayerManager.a(this.mContext).m() == PlayerType.GAANA_RADIO) {
            b = PlayerManager.a(this.mContext).i().b();
        }
        if (!(b == null || b.getBusinessObjType() == null || ad.a(this.mContext).p().booleanValue())) {
            if (TextUtils.isEmpty(b.getAlbumId())) {
                Util.a(this.mContext, null, b, true, (DownloadPopupListener) this);
                return;
            }
            PopupWindowView instance = PopupWindowView.getInstance(this.mContext, null);
            instance.setDownloadPopupListener(this);
            instance.contextPopupWindow(b, true, this, false);
        }
        u.a().b("Player", "Context Menu tapped");
    }

    public void a(Track track) {
        if (track != null && !ad.a(this.mContext).p().booleanValue()) {
            this.E.setVisibility(0);
            this.F = new DownloadClickAnimation(this.mContext, this, this.G, track, this.h);
            if (!(this.mCurrentTrack == null || this.F == null)) {
                this.F.changeDownlaodButtonIcon(track, this.G);
                c(track);
                b(track);
            }
        }
    }

    private void b(final Track track) {
        if (this.mPlayerManager != null && track != null) {
            ImageView imageView = (ImageView) this.h.findViewById(R.id.menu_add_to_playlist);
            imageView.setTag(track.getBusinessObjId());
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PlayerRadioFragmentV4.this.al = view;
                    com.managers.af.a(PlayerRadioFragmentV4.this.mContext, null).a((int) R.id.addToPlaylistMenu, track);
                }
            });
            if (track.isLocalMedia()) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
            }
        }
    }

    private void c(Track track) {
        if (this.mPlayerManager != null) {
            if (track == null) {
                if (this.mPlayerManager.i() != null) {
                    track = this.mPlayerManager.i().b();
                } else {
                    return;
                }
            }
            final ImageView imageView = (ImageView) this.h.findViewById(R.id.favourite_item);
            imageView.setTag(track.getBusinessObjId());
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PlayerRadioFragmentV4.this.al = view;
                    PlayerRadioFragmentV4.this.a(imageView, track);
                    PlayerRadioFragmentV4.this.ak = false;
                    PlayerRadioFragmentV4.this.e();
                    PlayerRadioFragmentV4.this.d();
                }
            });
            if (track.isLocalMedia()) {
                imageView.setVisibility(8);
                return;
            }
            imageView.setVisibility(0);
            if (track.isFavorite().booleanValue()) {
                imageView.setImageResource(R.drawable.vector_more_option_favorited);
            } else {
                TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                if (((GaanaActivity) this.mContext).isPlayerFullScreen()) {
                    imageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.vector_more_option_favorite_white));
                } else {
                    imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(49, -1)));
                    obtainStyledAttributes.recycle();
                }
            }
        }
    }

    public void a(final ImageView imageView, final Track track) {
        if (track != null) {
            track.setBusinessObjType(BusinessObjectType.Tracks);
            com.managers.af a = com.managers.af.a(this.mContext, null);
            a.a("Radio Player Screen");
            a.b(track.getBusinessObjId());
            a.a((int) R.id.favoriteMenu, (BusinessObject) track, new a() {
                public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
                    ImageView imageView = imageView;
                    if (track == null || !track.isFavorite().booleanValue()) {
                        TypedArray obtainStyledAttributes = PlayerRadioFragmentV4.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        if (((GaanaActivity) PlayerRadioFragmentV4.this.mContext).isPlayerFullScreen()) {
                            imageView.setImageDrawable(PlayerRadioFragmentV4.this.getResources().getDrawable(R.drawable.vector_more_option_favorite_white));
                            return;
                        }
                        imageView.setImageDrawable(ContextCompat.getDrawable(PlayerRadioFragmentV4.this.getContext(), obtainStyledAttributes.getResourceId(49, -1)));
                        obtainStyledAttributes.recycle();
                        return;
                    }
                    imageView.setImageResource(R.drawable.vector_more_option_favorited);
                    imageView.setPadding(PlayerRadioFragmentV4.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp10), PlayerRadioFragmentV4.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp10), PlayerRadioFragmentV4.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp10), PlayerRadioFragmentV4.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp10));
                    if (PlayerRadioFragmentV4.this.al != null) {
                        Animation loadAnimation = AnimationUtils.loadAnimation(PlayerRadioFragmentV4.this.mContext, R.anim.favorite_tap_animation);
                        loadAnimation.setInterpolator(new com.a.a(0.2d, 20.0d));
                        PlayerRadioFragmentV4.this.al.startAnimation(loadAnimation);
                    }
                }
            });
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mCurrentTrack = PlayerManager.a(GaanaApplication.getContext()).a(PlaySequenceType.CURRENT);
        this.mRadioManager = ad.a(GaanaApplication.getContext());
        this.mPlayerManager = PlayerManager.a(GaanaApplication.getContext());
        z();
        m();
        o();
        g();
        a(getPlayingTrack());
    }

    private void z() {
        if (!(ad.a(this.mContext).o().booleanValue() || GaanaApplication.getInstance().isAppInOfflineMode())) {
            boolean j = Util.j(this.mContext);
        }
        if (!(this.mCurrentTrack == null || this.mCurrentTrack.a(true) == null)) {
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(this.mCurrentTrack.a(true).getBusinessObjId()));
            if (!this.mCurrentTrack.a(true).isLocalMedia() && ((ap.a().o() || DownloadManager.c().j(this.mCurrentTrack.a(true).getBusinessObjId()).booleanValue()) && e != null)) {
                DownloadStatus downloadStatus = DownloadStatus.DOWNLOADED;
            }
        }
        GaanaApplication gaanaApplication;
        switch (d.a().b("PREFERENCE_KEY_STREAMING_QUALITY", Constants.s(), false)) {
            case 10000:
                this.mContext.getString(R.string.low);
                return;
            case 10001:
                gaanaApplication = this.mAppState;
                if (GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                    this.mContext.getString(R.string.med);
                    return;
                } else {
                    this.mContext.getString(R.string.medium);
                    return;
                }
            case 10002:
                this.mContext.getString(R.string.high);
                return;
            case 10003:
                gaanaApplication = this.mAppState;
                if (GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                    this.mContext.getString(R.string.hd);
                    return;
                } else {
                    this.mContext.getString(R.string.high_defination);
                    return;
                }
            case 10004:
                this.mContext.getString(R.string.auto);
                return;
            default:
                return;
        }
    }

    public void onPlayerStateChanged() {
        if (isAdded()) {
            x();
        }
    }

    public void onRadioTracksFetched(boolean z) {
        if (isAdded()) {
            x();
        }
    }

    public void refreshList() {
        if (isAdded()) {
            this.mCurrentTrack = PlayerManager.a(this.mContext).i();
            A();
            if (this.mCurrentTrack != null) {
                a(this.mCurrentTrack);
            }
        }
    }

    public void onLiveRadioUpdate() {
        if (isAdded()) {
            this.mCurrentTrack = PlayerManager.a(GaanaApplication.getContext()).i();
            a(this.mCurrentTrack);
        }
    }

    public void onPause() {
        if (this.O != null) {
            this.O.pause();
        }
        super.onPause();
        ((GaanaActivity) this.mContext).setmCurrentPlayerFragment(null);
        ((GaanaActivity) this.mContext).setPlayerFullScreen(false);
        e();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.equalizerIconRadio) {
            ((BaseActivity) this.mContext).sendGAEvent("Player", "Equalizer", "Click");
            Util.t(this.mContext);
        } else if (id != R.id.playerBtnNext) {
            if (id == R.id.playerButton) {
                if (GaanaMusicService.t() || GaanaMusicService.s().m()) {
                    u.a().b("Player", "Pause");
                } else {
                    u.a().b("Player", "Play");
                }
                if (!ad.a(this.mContext).o().booleanValue()) {
                    f();
                } else if (GaanaMusicService.t()) {
                    o.d(getContext());
                    f = 0;
                } else {
                    f();
                }
            } else if (id == R.id.qualityText) {
                a(view);
            }
        } else if (!Constants.aa || Constants.h > 0) {
            ((BaseActivity) this.mContext).sendGAEvent("Player", "Skip", "Player - Skip - Song");
            o.f(getContext());
            this.ak = false;
            e();
            d();
        } else {
            Util.a(this.mContext, BLOCK_ACTION.SKIP);
        }
    }

    public void f() {
        if (GaanaMusicService.t() || GaanaMusicService.s().m() || GaanaMusicService.s().l()) {
            this.l.setImageDrawable(this.t);
            o.b(this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
        } else {
            this.l.setImageDrawable(this.s);
            o.a(this.mContext);
            if (ad.a(this.mContext).o().booleanValue()) {
                ad.a(this.mContext).j();
            }
        }
        this.ak = false;
        e();
        d();
    }

    private void A() {
        if (this.mPlayerManager.m() == PlayerType.GAANA_RADIO && this.mPlayerManager.g()) {
            this.mPlayerManager.a(PlayerType.GAANA_RADIO, getContext());
        } else if (this.mPlayerManager.m() != PlayerType.GAANA_RADIO) {
            this.mPlayerManager.f(false);
        }
    }

    public void g() {
        this.mContext = getContext();
        if (this.mContext != null) {
            A();
            o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.a);
            o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.ah);
            this.mAppState.setPlayerStatus(true);
            this.mCurrentTrack = PlayerManager.a(GaanaApplication.getContext()).i();
            onPlayerStateChanged();
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
                    this.mCurrentTrack = PlayerManager.a(this.mContext).i();
                }
                if (PlayerStatus.a(getContext()).c()) {
                    this.mPlayerStates = PlayerStates.PLAYING;
                    a(this.mCurrentTrack);
                    E();
                } else if (GaanaMusicService.s().l() && !GaanaMusicService.s().m() && !PlayerStatus.a(getContext()).e()) {
                    a(this.mCurrentTrack);
                    E();
                } else if (GaanaMusicService.s().m()) {
                    a(this.mCurrentTrack);
                    if (GaanaMusicService.s().l()) {
                        this.l.setImageDrawable(this.t);
                    } else {
                        this.l.setImageDrawable(this.t);
                    }
                } else if (PlayerStatus.a(getContext()).e()) {
                    a(this.mCurrentTrack);
                    H();
                }
            }
        }
    }

    private void a(PlayerTrack playerTrack) {
        this.mAppState.setPlayerStatus(true);
        String trim = getSubtitleText(getPlayingTrack().getAlbumTitle(), getPlayingTrack().getArtistNames()).trim();
        if (Constants.aG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getString(R.string.CASTING_TO));
            stringBuilder.append(Constants.aH);
            trim = stringBuilder.toString();
        } else if (this.mPlayerManager.m() == PlayerType.GAANA_RADIO && !TextUtils.isEmpty(this.mRadioManager.l()) && this.mRadioManager.o().booleanValue()) {
            trim = this.mRadioManager.l();
        }
        ad a = ad.a(this.mContext);
        if (a.c() == null || a.d() == null || !a.o().booleanValue() || TextUtils.isEmpty(a.h())) {
            TextView textView = this.x;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(getPlayingTrack().getName());
            stringBuilder2.append(" - ");
            stringBuilder2.append(trim);
            a(textView, stringBuilder2.toString(), getPlayingTrack().getName(), new StyleSpan(1));
        } else {
            a(this.x, a.h(), "", new StyleSpan(0));
        }
        this.x.setSelected(true);
        if (getPlayingTrack().isLocalMedia()) {
            this.i.bindImageForLocalMedia(getPlayingTrack().getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
            Toolbar toolbar = (Toolbar) this.h.findViewById(R.id.toolbar);
            if (toolbar != null) {
                Menu menu = toolbar.getMenu();
                if (menu != null) {
                    menu.findItem(R.id.menu_add_to_playlist).setVisible(false);
                }
            }
        } else {
            B();
        }
        w();
        a(getPlayingTrack());
        if (this.mCurrentTrack != null) {
            a((ImageView) this.h.findViewById(R.id.queue_panel_img_animation), getPlayingTrack());
        }
    }

    public void h() {
        String trim = getSubtitleText(getPlayingTrack().getAlbumTitle(), getPlayingTrack().getArtistNames()).trim();
        if (Constants.aG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getString(R.string.CASTING_TO));
            stringBuilder.append(Constants.aH);
            trim = stringBuilder.toString();
        } else if (this.mPlayerManager.m() == PlayerType.GAANA_RADIO && !TextUtils.isEmpty(this.mRadioManager.l()) && this.mRadioManager.o().booleanValue()) {
            trim = this.mRadioManager.l();
        }
        ad a = ad.a(this.mContext);
        if (a.c() == null || a.d() == null || !a.o().booleanValue() || TextUtils.isEmpty(a.h())) {
            TextView textView = this.x;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(getPlayingTrack().getName());
            stringBuilder2.append(" - ");
            stringBuilder2.append(trim);
            a(textView, stringBuilder2.toString(), getPlayingTrack().getName(), new StyleSpan(1));
            return;
        }
        a(this.x, a.h(), "", new StyleSpan(0));
    }

    /* Access modifiers changed, original: protected */
    public void a(ImageView imageView, BusinessObject businessObject) {
        if (imageView != null) {
            PlayerTrack i = PlayerManager.a(this.mContext).i();
            Object entityId;
            if (businessObject instanceof Item) {
                entityId = ((Item) businessObject).getEntityId();
            } else {
                entityId = businessObject.getBusinessObjId();
            }
            Drawable wrap = DrawableCompat.wrap(ContextCompat.getDrawable(this.mContext, R.drawable.ic_equalizer1_white_36dp));
            if (i == null || !i.h().equals(entityId)) {
                if (imageView.getVisibility() == 0) {
                    imageView.setVisibility(8);
                    if (imageView.getAnimation() != null) {
                        imageView.getAnimation().cancel();
                    }
                }
            } else if (i() == PlayerStates.PLAYING) {
                AnimationDrawable animationDrawable = (AnimationDrawable) ContextCompat.getDrawable(this.mContext, R.drawable.ic_equalizer_white_36dp);
                DrawableCompat.setTintList(animationDrawable, ColorStateList.valueOf(this.mContext.getResources().getColor(R.color.vector_active_icon_color)));
                imageView.setImageDrawable(animationDrawable);
                imageView.setVisibility(0);
                animationDrawable.start();
            } else {
                if (imageView.getAnimation() != null) {
                    imageView.getAnimation().cancel();
                }
                imageView.setVisibility(0);
                int color = ContextCompat.getColor(this.mContext, R.color.first_line_color);
                if (VERSION.SDK_INT >= 21) {
                    DrawableCompat.setTint(wrap, color);
                } else {
                    DrawableCompat.setTint(DrawableCompat.wrap(wrap), ContextCompat.getColor(this.mContext, R.color.first_line_color));
                }
                imageView.setImageDrawable(wrap);
            }
        }
    }

    public PlayerStates i() {
        return this.mPlayerStates;
    }

    private void B() {
        try {
            this.i.bindImage(getPlayingTrack(), Util.f(this.mContext, getPlayingTrack().getArtworkLarge()), ScaleType.CENTER_CROP, true);
        } catch (OutOfMemoryError unused) {
            this.i.bindImage(getPlayingTrack().getArtwork(), ScaleType.CENTER_CROP);
        }
    }

    private void c(boolean z) {
        LinearLayout linearLayout = (LinearLayout) this.h.findViewById(R.id.optionLayout);
        View findViewById = this.h.findViewById(R.id.dark_overlay);
        if (!z && TextUtils.isEmpty(ad.a(this.mContext).h())) {
            if (j() && !this.am) {
                a(linearLayout, findViewById);
            } else if (!this.am) {
                b(linearLayout, findViewById);
            }
        }
    }

    private void a(final LinearLayout linearLayout, View view) {
        this.o.setAlpha(0.0f);
        linearLayout.setAlpha(0.0f);
        this.R.setAlpha(0.0f);
        this.D.setAlpha(0.0f);
        this.o.animate().alpha(1.0f).setDuration(500).start();
        this.R.animate().alpha(1.0f).setDuration(500).start();
        linearLayout.animate().alpha(1.0f).setListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                if (!ad.a(PlayerRadioFragmentV4.this.mContext).p().booleanValue()) {
                    linearLayout.setVisibility(0);
                }
                PlayerRadioFragmentV4.this.R.setVisibility(0);
                PlayerRadioFragmentV4.this.am = true;
                PlayerRadioFragmentV4.this.b(false);
            }

            public void onAnimationEnd(Animator animator) {
                PlayerRadioFragmentV4.this.am = false;
            }
        }).setDuration(600).start();
        this.an = false;
        C();
        d();
    }

    private void d(boolean z) {
        LinearLayout linearLayout;
        FrameLayout.LayoutParams layoutParams;
        if (z) {
            if (this.S) {
                linearLayout = (LinearLayout) this.h.findViewById(R.id.llNativeAdSlot);
                layoutParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.bottomMargin = this.mContext.getResources().getDimensionPixelSize(R.dimen.bottom_player_ad_fade_out_margin);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.setAlpha(0.0f);
                linearLayout.animate().alpha(1.0f).setDuration(500).start();
                a(getPlayingTrack());
                return;
            }
            a(getPlayingTrack());
        } else if (this.S) {
            linearLayout = (LinearLayout) this.h.findViewById(R.id.llNativeAdSlot);
            layoutParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.bottomMargin = this.mContext.getResources().getDimensionPixelSize(R.dimen.bottom_player_radio_ad_fade_in_margin);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setAlpha(0.0f);
            linearLayout.animate().alpha(1.0f).setDuration(500).start();
            a(getPlayingTrack());
        } else {
            a(getPlayingTrack());
        }
    }

    private void C() {
        boolean z = this.an || this.ac;
        d(z);
    }

    public boolean j() {
        return this.an;
    }

    private void b(final LinearLayout linearLayout, View view) {
        this.o.animate().alpha(0.0f).setDuration(500).start();
        linearLayout.animate().alpha(0.0f).setDuration(500).start();
        this.R.animate().alpha(0.0f).setDuration(500).start();
        this.D.animate().alpha(1.0f).setListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                PlayerRadioFragmentV4.this.am = true;
                PlayerRadioFragmentV4.this.b(true);
            }

            public void onAnimationEnd(Animator animator) {
                linearLayout.setVisibility(8);
                PlayerRadioFragmentV4.this.R.setVisibility(8);
                PlayerRadioFragmentV4.this.am = false;
                PlayerRadioFragmentV4.this.ak = false;
            }
        }).setDuration(600).start();
        this.an = true;
        a(getPlayingTrack());
        C();
    }

    private void e(boolean z) {
        if (z) {
            GaanaApplication.getInstance().setGADParameter("Lyrics Page");
        } else {
            GaanaApplication.getInstance().setGADParameter("Player Page");
        }
        u();
        this.ac = z;
        if (this.X != null) {
            this.X.setFullscreenMode(z);
        }
        LinearLayout linearLayout = (LinearLayout) this.h.findViewById(R.id.optionLayout);
        if (z) {
            linearLayout.setAlpha(0.0f);
            linearLayout.setVisibility(8);
            linearLayout.setClickable(false);
        } else {
            linearLayout.setAlpha(1.0f);
            linearLayout.setVisibility(0);
            linearLayout.setClickable(true);
        }
        if (z) {
            e();
            this.o.setVisibility(0);
            this.o.setAlpha(1.0f);
            this.o.setClickable(true);
            this.ae.setVisibility(0);
            this.ad.setVisibility(8);
            this.af.setVisibility(8);
            this.ag.setVisibility(0);
            this.P.setText(this.mPlayerManager.i().b().getTrackTitle());
            this.Q.setText(this.mPlayerManager.i().b().getAlbumTitle());
            this.P.setVisibility(0);
            this.Q.setVisibility(0);
            this.R.setVisibility(8);
            this.D.setVisibility(0);
            this.D.setAlpha(1.0f);
            this.R.setVisibility(8);
            this.R.setClickable(false);
            this.i.setVisibility(8);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Lyrics Full Page");
            stringBuilder.append(this.T);
            u.a().b("Lyrics", stringBuilder.toString());
            ((GaanaActivity) this.mContext).getWindow().addFlags(128);
        } else {
            this.ae.setVisibility(8);
            this.ad.setVisibility(0);
            this.ag.setVisibility(8);
            this.af.setVisibility(0);
            w();
            this.P.setVisibility(4);
            this.Q.setVisibility(4);
            this.i.setVisibility(0);
            b(this.an);
            if (!this.an) {
                d();
                this.R.setVisibility(0);
                this.D.setVisibility(8);
                this.D.setAlpha(0.0f);
            }
            ((GaanaActivity) this.mContext).getWindow().clearFlags(128);
        }
        C();
    }

    private void D() {
        switch (this.T) {
            case 1:
                this.W.setVisibility(8);
                this.V.setVisibility(8);
                this.J.setVisibility(0);
                return;
            case 2:
                if (GaanaApplication.getInstance().getLyricsDisplay()) {
                    r();
                    return;
                }
                return;
            case 3:
                if (GaanaApplication.getInstance().getLyricsDisplay()) {
                    r();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onDestroyView() {
        if (o.a("LISTENER_KEY_PLAYER_ACTIVITY") == this) {
            this.r.removeCallbacksAndMessages(null);
            o.b("LISTENER_KEY_PLAYER_ACTIVITY");
            o.d("LISTENER_KEY_PLAYER_ACTIVITY");
        }
        if (this.O != null) {
            this.O.destroy();
        }
        super.onDestroyView();
    }

    private void E() {
        try {
            GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
        }
        G();
        if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
            this.l.setImageDrawable(this.t);
        } else {
            this.l.setImageDrawable(this.s);
        }
        this.C.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                Util.d(PlayerRadioFragmentV4.this.ap - ((long) PlayerRadioFragmentV4.this.aq));
                u.a().b("Player", "Seekbar Moved");
                o.a(PlayerRadioFragmentV4.this.getContext(), PlayerRadioFragmentV4.this.C.getProgress());
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                PlayerRadioFragmentV4.this.ap = (long) PlayerRadioFragmentV4.this.aq;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                r0 = new Object[2];
                long j = (long) i;
                r0[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) / 60);
                r0[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % 60);
                String.format("%2d:%02d", r0);
                r15 = new Object[2];
                long u = (long) (GaanaMusicService.s().u() - i);
                r15[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) / 60);
                r15[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) % 60);
                String.format("%2d:%02d", r15);
                PlayerRadioFragmentV4.this.aq = i;
            }
        });
    }

    private void F() {
        try {
            GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
        }
        o.a(getContext(), this.ao);
        G();
        if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
            this.l.setImageDrawable(this.t);
        } else {
            this.l.setImageDrawable(this.s);
        }
    }

    private void G() {
        int v;
        if (!GaanaMusicService.s().m()) {
            if (!ad.a(this.mContext).o().booleanValue()) {
                int u;
                try {
                    v = GaanaMusicService.s().v();
                    u = GaanaMusicService.s().u();
                } catch (IllegalStateException unused) {
                    v = 0;
                    u = v;
                }
                this.ao = v;
                int i = u - v;
                if (j()) {
                    this.D.setProgress(v);
                    this.D.setMax(u);
                } else {
                    this.C.setProgress(v);
                    this.C.setMax(u);
                }
                r12 = new Object[2];
                long j = (long) v;
                r12[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) / 60);
                r12[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % 60);
                CharSequence format = String.format("%2d:%02d", r12);
                r6 = new Object[2];
                long j2 = (long) i;
                r6[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j2) / 60);
                r6[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j2) % 60);
                String format2 = String.format("%2d:%02d", r6);
                if (v > 15958442) {
                    format = "0:00";
                    H();
                }
                this.j.setText(format);
                this.X.seekLrcToTime(j);
                if (!((format2.equalsIgnoreCase(" 0:00") && this.mPlayerManager.t() && this.mPlayerManager.v()) || !GaanaMusicService.t() || GaanaMusicService.s().m())) {
                    AnonymousClass16 anonymousClass16 = new Runnable() {
                        public void run() {
                            PlayerRadioFragmentV4.this.G();
                        }
                    };
                    this.r.removeCallbacksAndMessages(null);
                    this.r.postDelayed(anonymousClass16, 1000);
                }
            } else if (GaanaMusicService.t()) {
                f = GaanaMusicService.s().v();
                this.j.setText(String.format("%2d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) f) / 60), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) f) % 60)}));
                AnonymousClass17 anonymousClass17 = new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV4.this.G();
                    }
                };
                this.r.removeCallbacksAndMessages(null);
                this.r.postDelayed(anonymousClass17, 1000);
            } else {
                if (this.r != null) {
                    this.r.removeCallbacksAndMessages(null);
                }
                f = 0;
                this.j.setText(String.format("%2d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) f) / 60), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) f) % 60)}));
            }
        }
    }

    private void H() {
        f = 0;
        this.ao = 0;
        this.j.setText("0:00");
    }

    private void I() {
        DownloadManager.c().e(Integer.parseInt(getPlayingTrack().getBusinessObjId()));
        if (GaanaMusicService.s().m()) {
            this.m.setVisibility(0);
            this.l.setVisibility(4);
        } else if (GaanaMusicService.t()) {
            this.m.setVisibility(8);
            this.l.setVisibility(0);
            this.l.setImageDrawable(this.t);
        } else {
            this.m.setVisibility(8);
            this.l.setVisibility(0);
            this.l.setImageDrawable(this.s);
        }
    }

    private void J() {
        if (this.l != null) {
            this.l.setImageDrawable(this.s);
            this.l.setVisibility(0);
            this.m.setVisibility(8);
            this.mPlayerStates = PlayerStates.STOPPED;
            if (this.mCurrentTrack != null) {
                a((ImageView) this.h.findViewById(R.id.queue_panel_img_animation), getPlayingTrack());
            }
        }
    }

    private void K() {
        o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.ah);
        this.mCurrentTrack = this.mPlayerManager.a(PlaySequenceType.CURRENT);
        n();
        m();
        D();
        a(this.mCurrentTrack);
        if (!this.p) {
            H();
        }
        GaanaApplication.getInstance().setGADParameter("Player Page");
        u();
        a(this.mPlayerManager.i().b());
        this.l.setImageDrawable(this.t);
        this.l.setVisibility(4);
        this.m.setVisibility(0);
        this.mPlayerStates = PlayerStates.LOADING;
        DownloadManager.c().e(Integer.parseInt(getPlayingTrack().getBusinessObjId()));
    }

    private void L() {
        this.l.setImageDrawable(this.s);
        this.l.setVisibility(0);
        this.m.setVisibility(8);
        this.mPlayerStates = PlayerStates.PAUSED;
    }

    private void M() {
        this.l.setImageDrawable(this.t);
        this.l.setVisibility(0);
        this.m.setVisibility(8);
        if (!GaanaMusicService.s().m()) {
            if (this.p) {
                F();
            } else {
                E();
            }
        }
        this.mPlayerStates = PlayerStates.PLAYING;
        if (this.mCurrentTrack != null) {
            a((ImageView) this.h.findViewById(R.id.queue_panel_img_animation), getPlayingTrack());
        }
    }

    private void a(boolean z, boolean z2) {
        this.l.setImageDrawable(this.t);
        if (z2) {
            a(z, PlayerCommands.PLAY_PREVIOUS);
            return;
        }
        this.r.removeCallbacksAndMessages(null);
        H();
    }

    private void b(boolean z, boolean z2) {
        this.l.setImageDrawable(this.t);
        if (z2) {
            a(z, PlayerCommands.PLAY_NEXT);
        } else if (this.mPlayerManager.m() != PlayerType.GAANA_RADIO) {
            this.r.removeCallbacksAndMessages(null);
            H();
        } else if (this.mPlayerManager.f()) {
            if (!this.mPlayerManager.h()) {
                this.mPlayerManager.f(true);
            }
            this.r.removeCallbacksAndMessages(null);
            H();
        } else {
            ((BaseActivity) getActivity()).showProgressDialog();
        }
    }

    private void a(boolean z, PlayerCommands playerCommands) {
        if (AnonymousClass25.a[playerCommands.ordinal()] == 1) {
            if (!z) {
                this.mCurrentTrack = this.mPlayerManager.a(this.mPlayerManager.s());
                this.r.removeCallbacksAndMessages(null);
                H();
                this.l.setImageDrawable(this.s);
            } else if (GaanaMusicService.s().l() || GaanaMusicService.s().k()) {
                this.l.setImageDrawable(this.s);
            }
        }
    }

    private void a(View view) {
        String string;
        String str;
        final LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        View inflate = layoutInflater.inflate(R.layout.stream_quality_layout, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this.mContext);
        bottomSheetDialog.setContentView(inflate);
        ListView listView = (ListView) inflate.findViewById(R.id.streaming_list);
        if (ap.a().s()) {
            string = this.mContext.getResources().getString(R.string.stream_quality_hd);
            str = "High Definition";
        } else {
            string = this.mContext.getResources().getString(R.string.stream_quality_hd_gaanaplus);
            str = "High Definition (Gaana+ only)";
        }
        final String[] strArr = new String[]{this.mContext.getResources().getString(R.string.stream_quality_auto), string, this.mContext.getResources().getString(R.string.stream_quality_high), this.mContext.getResources().getString(R.string.stream_quality_med), this.mContext.getResources().getString(R.string.stream_quality_low)};
        String[] strArr2 = new String[]{"Auto", str, "High", "Medium", "Low"};
        final int[] iArr = new int[]{10004, 10003, 10002, 10001, 10000};
        listView.setSelector(this.u);
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
                    textView.setTextColor(PlayerRadioFragmentV4.this.q.data);
                }
                return view;
            }
        });
        final BottomSheetDialog bottomSheetDialog2 = bottomSheetDialog;
        final String[] strArr3 = strArr2;
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                d a = d.a();
                if (PlayerRadioFragmentV4.this.mAppState.isAppInDataSaveMode()) {
                    ((BaseActivity) PlayerRadioFragmentV4.this.mContext).displayFeatureNotAvailableDataSaveModeDialog(i, -1);
                    bottomSheetDialog2.dismiss();
                    return;
                }
                aj a2;
                Context av;
                StringBuilder stringBuilder;
                if (i == 0) {
                    if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                        bottomSheetDialog2.dismiss();
                        return;
                    }
                    a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                    aj.a().a(PlayerRadioFragmentV4.this.mContext, PlayerRadioFragmentV4.this.mContext.getString(R.string.adjusting_sound_quality));
                    bottomSheetDialog2.dismiss();
                    PlayerRadioFragmentV4.this.refreshList();
                    PlayerRadioFragmentV4.this.p = true;
                    o.b(PlayerRadioFragmentV4.this.getContext(), 1);
                    u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                } else if (i == 1) {
                    if (!ap.a().s()) {
                        u.a().a("Mini Player", "Set Streaming Quality", "Trial HD (Gaana+ only)");
                        bottomSheetDialog2.dismiss();
                        Util.a(PlayerRadioFragmentV4.this.mContext, PlayerRadioFragmentV4.this.mContext.getResources().getString(R.string.subscribe_gaanaplus_hdq_msg), "HDQuality");
                    } else if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                        bottomSheetDialog2.dismiss();
                        return;
                    } else {
                        a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                        a2 = aj.a();
                        av = PlayerRadioFragmentV4.this.mContext;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(PlayerRadioFragmentV4.this.mContext.getString(R.string.changing_sound_quality));
                        stringBuilder.append(strArr[i]);
                        a2.a(av, stringBuilder.toString());
                        bottomSheetDialog2.dismiss();
                        PlayerRadioFragmentV4.this.refreshList();
                        PlayerRadioFragmentV4.this.p = true;
                        o.b(PlayerRadioFragmentV4.this.getContext(), 1);
                        u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                    }
                } else if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                    bottomSheetDialog2.dismiss();
                    return;
                } else {
                    a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                    a2 = aj.a();
                    av = PlayerRadioFragmentV4.this.mContext;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(PlayerRadioFragmentV4.this.mContext.getString(R.string.changing_sound_quality));
                    stringBuilder.append(strArr[i]);
                    a2.a(av, stringBuilder.toString());
                    bottomSheetDialog2.dismiss();
                    PlayerRadioFragmentV4.this.refreshList();
                    PlayerRadioFragmentV4.this.p = true;
                    o.b(PlayerRadioFragmentV4.this.getContext(), 1);
                    u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                }
                PlayerRadioFragmentV4.this.z();
            }
        });
        bottomSheetDialog.show();
    }

    public void refreshPlayerStatus() {
        if (this.mCurrentTrack != null && this.F != null) {
            this.F.changeDownlaodButtonIcon(getPlayingTrack(), (ImageView) this.h.findViewById(R.id.queue_panel_download_button));
        }
    }

    public void k() {
        c(getPlayingTrack());
    }

    public void onPopupClicked(String str, BusinessObject businessObject) {
        if (DownloadManager.c().e(Integer.parseInt(str)) == DownloadStatus.DOWNLOADED) {
            b(businessObject);
        } else {
            a(businessObject);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(final BusinessObject businessObject) {
        if (!businessObject.isLocalMedia()) {
            if (ap.a().o()) {
                com.managers.af.a(this.mContext, null).a((int) R.id.downloadMenu, businessObject);
            } else {
                ((BaseActivity) this.mContext).hideProgressDialog();
                final BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
                if (!((currentFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) currentFragment).a() == 1)) {
                    Util.b(this.mContext, null, new as() {
                        public void onTrialSuccess() {
                            com.managers.af.a(PlayerRadioFragmentV4.this.mContext, null).a((int) R.id.downloadMenu, businessObject);
                            currentFragment.showSnackbartoOpenMyMusic();
                            ((GaanaActivity) PlayerRadioFragmentV4.this.mContext).updateSideBar();
                        }
                    });
                }
            }
        }
    }

    private void b(final BusinessObject businessObject) {
        final String businessObjId = businessObject.getBusinessObjId();
        new CustomDialogView(this.mContext, (int) R.string.dialog_deletdownload_text, new OnButtonClickListener() {
            public void onNegativeButtonClick() {
            }

            public void onPositiveButtonClick() {
                if ((businessObject instanceof Track) || (businessObject instanceof OfflineTrack)) {
                    DownloadManager.c().d(businessObject.getBusinessObjId());
                } else {
                    DownloadManager.c().p(Integer.parseInt(businessObjId));
                    DownloadManager.c().d(Integer.parseInt(businessObjId));
                }
                PlayerRadioFragmentV4.this.refreshList();
                if (PlayerRadioFragmentV4.this.mCurrentTrack != null && PlayerRadioFragmentV4.this.F != null) {
                    PlayerRadioFragmentV4.this.F.changeDownlaodButtonIcon(PlayerRadioFragmentV4.this.getPlayingTrack(), (ImageView) PlayerRadioFragmentV4.this.h.findViewById(R.id.queue_panel_download_button));
                }
            }
        }).show();
    }

    public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
        c((Track) businessObject);
    }

    public void updateUiForCircularProgressBar(int i, int i2) {
        if (this.mContext != null && (this.mContext instanceof Activity)) {
            ((Activity) this.mContext).runOnUiThread(new Runnable() {
                public void run() {
                }
            });
        }
    }

    public void b(ADSTATUS adstatus) {
        this.S = true;
        t();
        C();
    }

    public void c(ADSTATUS adstatus) {
        this.S = false;
    }

    public void b() {
        if (this.ac) {
            c();
            return;
        }
        ((GaanaActivity) this.mContext).popBackStackImmediate();
        Fragment miniPlayer = ((GaanaActivity) this.mContext).getMiniPlayer();
        if (miniPlayer != null && (miniPlayer instanceof MiniPlayerFragment)) {
            ((MiniPlayerFragment) miniPlayer).p();
        } else if (miniPlayer != null && (miniPlayer instanceof MiniPlayerFragmentV4)) {
            ((MiniPlayerFragmentV4) miniPlayer).p();
        }
    }

    public void l() {
        new com.services.f(this.mContext).a(this.mContext.getString(R.string.gaana_text), this.mContext.getResources().getString(R.string.report_lyrics_text), Boolean.valueOf(true), getString(R.string.yes), getString(R.string.no), new com.services.f.b() {
            public void onCancelListner() {
            }

            public void onOkListner(String str) {
                URLManager uRLManager = new URLManager();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("https://api.gaana.com/lyrics/report?track_id=");
                stringBuilder.append(PlayerRadioFragmentV4.this.mCurrentTrack.h());
                uRLManager.a(stringBuilder.toString());
                i.a().a(new af() {
                    public void onErrorResponse(BusinessObject businessObject) {
                    }

                    public void onRetreivalComplete(Object obj) {
                        aj.a().a(PlayerRadioFragmentV4.this.mContext, PlayerRadioFragmentV4.this.mContext.getResources().getString(R.string.thanks_for_report));
                    }
                }, uRLManager);
            }
        });
    }
}
