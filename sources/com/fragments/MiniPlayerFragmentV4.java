package com.fragments;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import com.android.volley.VolleyError;
import com.bumptech.glide.load.resource.bitmap.g;
import com.constants.Constants;
import com.constants.Constants.ErrorType;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.models.Tracks.Track;
import com.gaana.view.CustomTextView;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.i.i;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.library.controls.CrossFadeImageView;
import com.library.controls.CrossFadeImageView.ImageLoadingCompeletedListener;
import com.library.custom_glide.GlideApp;
import com.logging.GaanaLogger;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.GaanaSearchManager;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlaySequenceType;
import com.managers.PlayerManager.PlayerType;
import com.managers.PlayerManager.a;
import com.managers.ad;
import com.managers.an;
import com.managers.ap;
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
import com.services.f.b;
import com.services.l.aj;
import com.services.l.ak;
import com.services.l.av;
import com.services.l.r;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MiniPlayerFragmentV4 extends Fragment implements OnClickListener, a, ad.a, aj, ak {
    private String A;
    private String B;
    private final Handler C = new Handler();
    private boolean D;
    private PlayerStates E = PlayerStates.INVALID;
    private boolean F = false;
    private List<PlayerTrack> G = new ArrayList();
    private boolean H = false;
    private av I;
    private int J = 0;
    private ImageView K;
    private PlayerManager L;
    private CustomTextView M;
    private Drawable N;
    private Drawable O;
    private int[] P = new int[]{R.drawable.vector_player_shuffle_white, R.drawable.vector_player_shuffle_active};
    private int[] Q = new int[]{R.drawable.vector_player_repeat_white, R.drawable.vector_player_repeat_one, R.drawable.vector_player_repeat_active};
    private final GestureDetector R = new GestureDetector(new OnGestureListener() {
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
            if (motionEvent.getY() - motionEvent2.getY() <= 30.0f) {
                return false;
            }
            ((GaanaActivity) MiniPlayerFragmentV4.this.s).launchExpandedPlayer();
            u.a().a("CentreMiniPlayer", "SwipeUp", "");
            return true;
        }
    });
    private final OnTouchListener S = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return MiniPlayerFragmentV4.this.R.onTouchEvent(motionEvent);
        }
    };
    private ServiceConnection T = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MiniPlayerFragmentV4.this.F = true;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            MiniPlayerFragmentV4.this.F = false;
        }
    };
    private m U = new m() {
        public void onAdEventUpdate(f fVar, AdEvent adEvent) {
        }

        public void onInfo(f fVar, int i, int i2) {
        }

        public void onPrepared(f fVar) {
            if (MiniPlayerFragmentV4.this.isAdded()) {
                MiniPlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (GaanaMusicService.s().isPlaying()) {
                            MiniPlayerFragmentV4.this.E = PlayerStates.PLAYING;
                            MiniPlayerFragmentV4.this.f();
                            MiniPlayerFragmentV4.this.h.setImageResource(R.drawable.vector_player_pause_white);
                            MiniPlayerFragmentV4.this.h.setVisibility(8);
                            ((GaanaActivity) MiniPlayerFragmentV4.this.s).showFreedomUserEngagementPopup(((GaanaActivity) MiniPlayerFragmentV4.this.s).getmFreedomPlanUserEngagementData());
                        } else {
                            MiniPlayerFragmentV4.this.h.setImageResource(R.drawable.vector_player_play_white);
                            MiniPlayerFragmentV4.this.h.setVisibility(0);
                        }
                        if (MiniPlayerFragmentV4.this.D) {
                            MiniPlayerFragmentV4.this.G();
                            MiniPlayerFragmentV4.this.K();
                            MiniPlayerFragmentV4.this.D = false;
                            return;
                        }
                        MiniPlayerFragmentV4.this.C();
                        MiniPlayerFragmentV4.this.K();
                        try {
                            if (PlayerManager.a(GaanaApplication.getContext()).i() != null) {
                                Track b = PlayerManager.a(GaanaApplication.getContext()).i().b();
                                MiniPlayerFragmentV4.this.A = "song/";
                                if (PlayerManager.a(GaanaApplication.getContext()).i().e() == SOURCE_TYPE.ALBUM.ordinal()) {
                                    MiniPlayerFragmentV4.this.A = "song/";
                                } else if (PlayerManager.a(GaanaApplication.getContext()).i().e() == SOURCE_TYPE.PLAYLIST.ordinal()) {
                                    MiniPlayerFragmentV4.this.A = "song/";
                                } else if (PlayerManager.a(GaanaApplication.getContext()).i().e() == SOURCE_TYPE.RADIO_MIRCHI.ordinal()) {
                                    MiniPlayerFragmentV4.this.A = "radio/";
                                } else if (PlayerManager.a(GaanaApplication.getContext()).i().e() == SOURCE_TYPE.GAANA_RADIO.ordinal()) {
                                    MiniPlayerFragmentV4.this.A = "gaanaradio/";
                                }
                                MiniPlayerFragmentV4.this.a(b);
                            }
                        } catch (Exception unused) {
                        }
                    }
                });
            }
        }

        public void onError(f fVar, int i, int i2) {
            if (MiniPlayerFragmentV4.this.isAdded()) {
                if (i == -1000 || i == -1001) {
                    MiniPlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            MiniPlayerFragmentV4.this.F();
                        }
                    });
                }
            }
        }

        public void onCompletion(f fVar) {
            MiniPlayerFragmentV4.this.E = PlayerStates.STOPPED;
            MiniPlayerFragmentV4.this.m();
        }

        public void onBufferingUpdate(final f fVar, final int i) {
            if (MiniPlayerFragmentV4.this.isAdded()) {
                MiniPlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (PlayerStatus.a(MiniPlayerFragmentV4.this.s).c()) {
                            MiniPlayerFragmentV4.this.a(fVar, i);
                        }
                    }
                });
            }
        }
    };
    private boolean V = false;
    private PlayerTrack W = null;
    private int X = -1;
    protected PlayerTrack a;
    int[] b = new int[]{R.attr.bottom_pause_button, R.attr.miniplayer_play};
    n c = new n() {
        public void onStreamingQualityChanged(int i) {
        }

        public void onPlayerPlay() {
            if (MiniPlayerFragmentV4.this.isAdded()) {
                MiniPlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragmentV4.this.J();
                    }
                });
            }
        }

        public void onPlayerPause() {
            if (MiniPlayerFragmentV4.this.isAdded()) {
                MiniPlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragmentV4.this.D();
                    }
                });
            }
        }

        public void onPlayerResume() {
            if (MiniPlayerFragmentV4.this.isAdded()) {
                MiniPlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragmentV4.this.E();
                        if ((MiniPlayerFragmentV4.this.s instanceof GaanaActivity) && ((GaanaActivity) MiniPlayerFragmentV4.this.s).getCurrentFragment() != null && (((GaanaActivity) MiniPlayerFragmentV4.this.s).getCurrentFragment() instanceof GaanaVideoPlayerFragment)) {
                            ((GaanaActivity) MiniPlayerFragmentV4.this.s).popBackStackImmediate();
                        }
                    }
                });
            }
        }

        public void onPlayerStop() {
            if (MiniPlayerFragmentV4.this.isAdded()) {
                MiniPlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragmentV4.this.F();
                    }
                });
            }
        }

        public void onPlayPrevious(final boolean z, final boolean z2) {
            if (MiniPlayerFragmentV4.this.isAdded()) {
                MiniPlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragmentV4.this.a(z, z2);
                    }
                });
            }
        }

        public void onPlayNext(final boolean z, final boolean z2) {
            if (MiniPlayerFragmentV4.this.isAdded()) {
                MiniPlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragmentV4.this.b(z, z2);
                    }
                });
            }
        }

        public void displayErrorDialog(final String str, ErrorType errorType) {
            if (MiniPlayerFragmentV4.this.isAdded()) {
                MiniPlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragmentV4.this.F();
                    }
                });
                if (errorType == ErrorType.NETWORK_ERROR) {
                    MiniPlayerFragmentV4.this.a(str);
                } else if (errorType == ErrorType.OTHER) {
                    MiniPlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            if (ad.a(MiniPlayerFragmentV4.this.s).o().booleanValue()) {
                                ap.a().a(MiniPlayerFragmentV4.this.getContext(), MiniPlayerFragmentV4.this.s.getString(R.string.unable_to_start_radio));
                            } else {
                                ap.a().a(MiniPlayerFragmentV4.this.getContext(), str);
                            }
                        }
                    });
                } else if (errorType == ErrorType.TEMPORARY_NETWORK_ERROR) {
                    displayErrorToast(str, 1);
                }
            }
        }

        public void displayErrorToast(final String str, int i) {
            if (MiniPlayerFragmentV4.this.isAdded()) {
                MiniPlayerFragmentV4.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        com.managers.aj.a().a(MiniPlayerFragmentV4.this.getContext(), str);
                    }
                });
            }
        }

        public void onPlayerRepeatReset(boolean z) {
            if (!PlayerManager.a(GaanaApplication.getContext()).e() && z) {
                MiniPlayerFragmentV4.this.d();
            }
        }
    };
    public ImageView d;
    public ImageView e;
    public ImageView f;
    public ImageView g;
    public ImageView h;
    Handler i;
    Runnable j;
    boolean k = false;
    public boolean l = false;
    boolean m = false;
    boolean n = false;
    boolean o = false;
    boolean p = false;
    boolean q = false;
    OnPageChangeListener r = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
            MiniPlayerFragmentV4.this.V = true;
        }

        public void onPageSelected(int i) {
            PlayerTrack i2;
            if (MiniPlayerFragmentV4.this.V) {
                if (MiniPlayerFragmentV4.this.X < i) {
                    if (MiniPlayerFragmentV4.this.b(i)) {
                        u.a().a("Mini Player", "Swipe", "Right");
                        an.a().a("swipe", "rt", "", "miniplayer", "", "", "", "");
                    }
                } else if (MiniPlayerFragmentV4.this.a(i)) {
                    u.a().a("Mini Player", "Swipe", "Left");
                    an.a().a("swipe", "lt", "", "miniplayer", "", "", "", "");
                }
            }
            MiniPlayerFragmentV4.this.V = false;
            MiniPlayerFragmentV4.this.X = i;
            Track track = null;
            if (PlayerManager.a(GaanaApplication.getContext()).m() != PlayerType.GAANA) {
                i2 = PlayerManager.a(GaanaApplication.getContext()).i();
                if (i2 != null) {
                    track = i2.b();
                }
            } else if (i < MiniPlayerFragmentV4.this.G.size()) {
                i2 = (PlayerTrack) MiniPlayerFragmentV4.this.G.get(i);
                if (i2 != null) {
                    track = i2.b();
                }
            } else {
                i2 = null;
            }
            MiniPlayerFragmentV4.this.W = i2;
            if (track != null) {
                MiniPlayerFragmentV4.this.I();
            }
        }
    };
    private Context s;
    private GaanaApplication t;
    private GoogleApiClient u;
    private Track v;
    private SeekBar w;
    private Drawable x;
    private Drawable y;
    private View z;

    private void I() {
    }

    public void p() {
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.s = context;
        this.t = GaanaApplication.getInstance();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_mini_player_v4, viewGroup, false);
        a(inflate);
        return inflate;
    }

    public void a() {
        if (Constants.du && Constants.dt != null) {
            String playerbackgroundImageWhiteArtwork;
            if (Constants.l) {
                playerbackgroundImageWhiteArtwork = Constants.dt.getPlayerbackgroundImageWhiteArtwork();
            } else {
                playerbackgroundImageWhiteArtwork = Constants.dt.getPlayerbackgroundImageBlackArtwork();
            }
            i.a().a(playerbackgroundImageWhiteArtwork, new r() {
                public void onErrorResponse(VolleyError volleyError) {
                }

                public void onSuccessfulResponse(Bitmap bitmap) {
                    if (MiniPlayerFragmentV4.this.K != null) {
                        MiniPlayerFragmentV4.this.K.setVisibility(0);
                        MiniPlayerFragmentV4.this.K.setImageBitmap(bitmap);
                    }
                }
            });
        }
    }

    public void b() {
        if (this.K != null) {
            this.K.setVisibility(8);
        }
    }

    private void q() {
        if (this.M == null) {
            return;
        }
        if (Constants.J == 1) {
            this.M.setVisibility(0);
            this.M.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ((GaanaActivity) MiniPlayerFragmentV4.this.s).launchExpandedPlayer();
                    u.a().a("CentreMiniPlayer", "PullUpToView", "");
                }
            });
            return;
        }
        this.M.setVisibility(8);
    }

    private void a(View view) {
        this.L = PlayerManager.a(this.s);
        view.setOnTouchListener(this.S);
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((GaanaActivity) MiniPlayerFragmentV4.this.s).launchExpandedPlayer();
            }
        });
        if (u()) {
            this.K = (ImageView) view.findViewById(R.id.miniplayer_background);
            this.M = (CustomTextView) view.findViewById(R.id.pullUpToViewTextContainer);
            q();
            a();
            L();
            TypedArray obtainStyledAttributes = this.s.obtainStyledAttributes(R.styleable.VectorDrawables);
            this.N = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(69, -1));
            this.O = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(70, -1));
            this.u = new Builder(GaanaApplication.getContext()).addApi(AppIndex.APP_INDEX_API).build();
            obtainStyledAttributes = this.s.obtainStyledAttributes(R.styleable.VectorDrawables);
            this.x = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(30, -1));
            this.y = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(31, -1));
            obtainStyledAttributes.recycle();
            this.w = (SeekBar) view.findViewById(R.id.seekBarMiniPLayer);
            this.M.setOnTouchListener(this.S);
            if (this.G == null) {
                this.G = new ArrayList();
            }
            this.G.clear();
            this.G.addAll(PlayerManager.a(GaanaApplication.getContext()).n());
            this.w.setThumb(new ColorDrawable(getResources().getColor(17170445)));
            this.w.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            ((GaanaActivity) this.s).findViewById(R.id.bottom_shadow).setVisibility(0);
            this.w.setPadding(0, 0, 0, 0);
            this.z = ((GaanaActivity) this.s).getProgressOverlayView();
            this.w.setFocusable(false);
            this.a = PlayerManager.a(GaanaApplication.getContext()).a(PlaySequenceType.CURRENT);
            if (this.z != null) {
                this.d = (ImageView) this.z.findViewById(R.id.playerBtnNext);
                this.e = (ImageView) this.z.findViewById(R.id.playerBtnPrev);
                this.f = (ImageView) this.z.findViewById(R.id.playerBtnShuffle);
                this.g = (ImageView) this.z.findViewById(R.id.playerBtnRepeat);
                this.h = (ImageView) this.z.findViewById(R.id.player_bottom_button);
                this.h.setClickable(false);
                this.f.setTag(Integer.valueOf(0));
                this.g.setTag(Integer.valueOf(0));
                if (PlayerStatus.b(this.s) == PlayerStates.PLAYING) {
                    this.h.setImageResource(R.drawable.vector_player_pause_white);
                    if (!this.l) {
                        this.h.setVisibility(8);
                    }
                } else {
                    this.h.setImageResource(R.drawable.vector_player_play_white);
                    this.h.setVisibility(0);
                }
                w();
                x();
                c();
                this.d.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        MiniPlayerFragmentV4.this.t();
                        MiniPlayerFragmentV4.this.s();
                        o.f(MiniPlayerFragmentV4.this.getContext());
                        u.a().a("CentreMiniPlayer", "Next", "");
                    }
                });
                this.e.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        MiniPlayerFragmentV4.this.t();
                        MiniPlayerFragmentV4.this.s();
                        o.e(MiniPlayerFragmentV4.this.getContext());
                        u.a().a("CentreMiniPlayer", "Previous", "");
                    }
                });
                this.f.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        MiniPlayerFragmentV4.this.t();
                        MiniPlayerFragmentV4.this.s();
                        MiniPlayerFragmentV4.this.b(view);
                    }
                });
                this.g.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        MiniPlayerFragmentV4.this.t();
                        MiniPlayerFragmentV4.this.s();
                        MiniPlayerFragmentV4.this.a(view, true);
                    }
                });
                ((GaanaActivity) this.s).showMiniPlayerOverlayCoachmark();
                return;
            }
            return;
        }
        M();
    }

    private void r() {
        if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO) {
            a(PlayerManager.a(GaanaApplication.getContext()).n());
            if (ad.a(this.s).o().booleanValue()) {
                this.d.setAlpha(0.5f);
                this.d.setClickable(false);
            } else {
                this.d.setAlpha(1.0f);
                this.d.setClickable(true);
            }
            this.g.setClickable(false);
            this.f.setClickable(false);
            this.e.setClickable(false);
            this.g.setAlpha(0.5f);
            this.f.setAlpha(0.5f);
            this.e.setAlpha(0.5f);
            return;
        }
        this.g.setClickable(true);
        this.f.setClickable(true);
        this.d.setClickable(true);
        this.e.setClickable(true);
        this.g.setAlpha(1.0f);
        this.f.setAlpha(1.0f);
        this.d.setAlpha(1.0f);
        this.e.setAlpha(1.0f);
    }

    public void c() {
        if (this.a != null && this.a.b() != null) {
            View view = this.z;
            boolean z = ((GaanaActivity) this.s).getCurrentFragment() instanceof a;
            if (view == null) {
                view = ((GaanaActivity) this.s).findViewById(R.id.container_bottom_progress_view);
            }
            if (view != null) {
                final CrossFadeImageView crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.imgArtwork);
                ((GaanaActivity) this.s).isPlayerExpanded();
                View view2 = (LinearLayout) view.findViewById(R.id.actionButtonLayout);
                if (this.a.b().isLocalMedia) {
                    crossFadeImageView.bindImageForLocalMedia(this.a.b().getArtwork(), new ImageLoadingCompeletedListener() {
                        public void onError() {
                        }

                        public void onImageLoadingCompeleted(Bitmap bitmap) {
                            GlideApp.with(MiniPlayerFragmentV4.this.s).load(new BitmapDrawable(MiniPlayerFragmentV4.this.getResources(), bitmap)).apply(new com.bumptech.glide.request.f().transforms(new g(), new com.bumptech.glide.load.resource.bitmap.r(Util.b(4)))).into((ImageView) crossFadeImageView);
                        }
                    }, new LocalMediaImageLoader(), false);
                } else {
                    crossFadeImageView.bindImage(this.a.b().getArtwork());
                }
                a(view.findViewById(R.id.current_track_info_holder), view2);
                if (z) {
                    view.setVisibility(8);
                } else {
                    view.setVisibility(0);
                }
            }
        }
    }

    private void s() {
        this.j = new Runnable() {
            public void run() {
                View n = MiniPlayerFragmentV4.this.z;
                if (n == null) {
                    n = ((GaanaActivity) MiniPlayerFragmentV4.this.s).findViewById(R.id.container_bottom_progress_view);
                }
                View view = (LinearLayout) n.findViewById(R.id.actionButtonLayout);
                MiniPlayerFragmentV4.this.b(n.findViewById(R.id.current_track_info_holder), view);
            }
        };
        if (this.i == null) {
            this.i = new Handler(Looper.getMainLooper());
        }
        this.i.postDelayed(this.j, 3000);
    }

    private void t() {
        if (this.j != null) {
            this.i.removeCallbacks(this.j);
        }
    }

    private void a(final View view, final View view2) {
        view.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                MiniPlayerFragmentV4.this.b(view, view2);
                return true;
            }
        });
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (MiniPlayerFragmentV4.this.h != null) {
                    if (MiniPlayerFragmentV4.this.l) {
                        if (MiniPlayerFragmentV4.this.E == PlayerStates.PLAYING) {
                            MiniPlayerFragmentV4.this.h.setImageResource(R.drawable.vector_player_play_white);
                            u.a().b("CentreMiniPlayer", "Pause");
                        } else {
                            MiniPlayerFragmentV4.this.h.setImageResource(R.drawable.vector_player_pause_white);
                            u.a().b("CentreMiniPlayer", "Play");
                        }
                        MiniPlayerFragmentV4.this.y();
                    } else if (MiniPlayerFragmentV4.this.E == PlayerStates.PLAYING || MiniPlayerFragmentV4.this.E == PlayerStates.LOADING) {
                        ((GaanaActivity) MiniPlayerFragmentV4.this.s).launchExpandedPlayer();
                        MiniPlayerFragmentV4.this.h.setImageResource(R.drawable.vector_player_play_white);
                        u.a().a("CentreMiniPlayer", "Tap", "Open");
                    } else {
                        MiniPlayerFragmentV4.this.y();
                        ((GaanaActivity) MiniPlayerFragmentV4.this.s).launchExpandedPlayer();
                        MiniPlayerFragmentV4.this.h.setImageResource(R.drawable.vector_player_pause_white);
                        u.a().a("CentreMiniPlayer", "Tap", "PlayOpen");
                    }
                }
            }
        });
        view.setOnTouchListener(this.S);
    }

    private void b(View view, final View view2) {
        ValueAnimator ofInt;
        if (this.l && !this.k) {
            ofInt = ValueAnimator.ofInt(new int[]{view2.getMeasuredWidth(), Util.a(this.s, 70)});
            ofInt.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    LayoutParams layoutParams = view2.getLayoutParams();
                    layoutParams.width = intValue;
                    view2.setLayoutParams(layoutParams);
                    MiniPlayerFragmentV4.this.l = false;
                    ((GaanaActivity) MiniPlayerFragmentV4.this.s).setMiniPlayerExpanded(MiniPlayerFragmentV4.this.l);
                }
            });
            ofInt.addListener(new AnimatorListener() {
                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                    MiniPlayerFragmentV4.this.k = true;
                    if (MiniPlayerFragmentV4.this.d != null) {
                        MiniPlayerFragmentV4.this.r();
                        if (PlayerStatus.a(MiniPlayerFragmentV4.this.s).c()) {
                            MiniPlayerFragmentV4.this.h.setImageResource(R.drawable.vector_player_pause_white);
                            MiniPlayerFragmentV4.this.h.setVisibility(4);
                            return;
                        }
                        MiniPlayerFragmentV4.this.h.setVisibility(0);
                        MiniPlayerFragmentV4.this.h.setImageResource(R.drawable.vector_player_play_white);
                    }
                }

                public void onAnimationEnd(Animator animator) {
                    MiniPlayerFragmentV4.this.k = false;
                    MiniPlayerFragmentV4.this.t();
                }

                public void onAnimationCancel(Animator animator) {
                    MiniPlayerFragmentV4.this.k = false;
                }
            });
            ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
            ofInt.setDuration(300);
            ofInt.start();
        } else if (!this.l && !this.k) {
            String str = Util.k().split(AvidJSONUtil.KEY_X)[1];
            ofInt = ValueAnimator.ofInt(new int[]{view2.getMeasuredWidth(), Integer.parseInt(str)});
            ofInt.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    LayoutParams layoutParams = view2.getLayoutParams();
                    layoutParams.width = intValue;
                    view2.setLayoutParams(layoutParams);
                    MiniPlayerFragmentV4.this.l = true;
                    ((GaanaActivity) MiniPlayerFragmentV4.this.s).setMiniPlayerExpanded(MiniPlayerFragmentV4.this.l);
                }
            });
            ofInt.addListener(new AnimatorListener() {
                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                    MiniPlayerFragmentV4.this.k = true;
                    if (MiniPlayerFragmentV4.this.d != null) {
                        MiniPlayerFragmentV4.this.r();
                        if (PlayerStatus.a(MiniPlayerFragmentV4.this.s).c()) {
                            MiniPlayerFragmentV4.this.h.setImageResource(R.drawable.vector_player_pause_white);
                            MiniPlayerFragmentV4.this.h.setVisibility(0);
                            return;
                        }
                        MiniPlayerFragmentV4.this.h.setImageResource(R.drawable.vector_player_play_white);
                        MiniPlayerFragmentV4.this.h.setVisibility(0);
                    }
                }

                public void onAnimationEnd(Animator animator) {
                    MiniPlayerFragmentV4.this.k = false;
                    MiniPlayerFragmentV4.this.s();
                    MiniPlayerFragmentV4.this.r();
                }

                public void onAnimationCancel(Animator animator) {
                    MiniPlayerFragmentV4.this.k = false;
                }
            });
            ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
            ofInt.setDuration(300);
            ofInt.start();
        }
    }

    public void onStart() {
        super.onStart();
        getActivity().bindService(new Intent(getActivity(), GaanaMusicService.class), this.T, 1);
    }

    public void onStop() {
        if (this.F) {
            getActivity().unbindService(this.T);
            this.F = false;
            if (GaanaMusicService.s().k()) {
                o.d(getActivity());
            }
        }
        super.onStop();
    }

    private boolean u() {
        if (PlayerManager.a(this.s).n() == null) {
            ArrayList e = com.managers.o.a().e();
            int b = d.a().b("PREFERENCE_KEY_LAST_PLAYED_TRACK_INDEX", 0, true);
            if (e == null || e.size() <= 0) {
                o.h(getContext());
                return false;
            }
            if (b < 0 || b > e.size() - 1 || b > Constants.dg - 1) {
                b = 0;
            }
            PlayerManager.a(GaanaApplication.getContext()).a(e, (PlayerTrack) e.get(b));
            v();
            PlayerManager.a(GaanaApplication.getContext()).a(PlayerType.GAANA, this.s);
            PlayerStatus.a(this.s, PlayerStates.STOPPED);
            o.d(this.s);
            PlayerManager.a = false;
            return true;
        }
        PlayerManager.a(getContext()).a(PlayerManager.a(getContext()).a(-1));
        GaanaApplication.getInstance().setPlayerStatus(true);
        return true;
    }

    private void v() {
        d a = d.a();
        if (a.b("PREFERENCE_KEY_SHUFFLE_STATUS", false, true)) {
            ArrayList f = com.managers.o.a().f();
            if (f == null || f.size() <= 0) {
                a.a("PREFERENCE_KEY_SHUFFLE_STATUS", false, true);
            } else {
                PlayerManager.a(this.s).a(f);
            }
        }
        int b = a.b("PREFERENCE_KEY_REPEAT_STATUS", 2, true);
        if (b == 1) {
            PlayerManager.a(this.s).b(true);
        } else if (b == 2) {
            PlayerManager.a(this.s).c(true);
        }
    }

    public void d() {
        if (this.g != null && this.g.getTag() != null && ((Integer) this.g.getTag()).intValue() == 1) {
            this.g.setTag(InternalAvidAdSessionContext.AVID_API_LEVEL);
            a(this.g, true);
        } else if (!PlayerManager.a(GaanaApplication.getContext()).e()) {
            this.L.h(false);
        }
    }

    private void a(View view, boolean z) {
        int parseInt = Integer.parseInt(view.getTag().toString());
        if (parseInt == 2) {
            parseInt = 0;
        } else {
            parseInt++;
            if (parseInt == 1) {
                this.L.i(true);
            } else {
                this.L.i(false);
            }
        }
        if (this.L.y()) {
            this.L.h(false);
            parseInt = 0;
        }
        if (parseInt == 0) {
            this.g.setImageDrawable(this.N);
        } else {
            this.g.setImageResource(this.Q[parseInt]);
        }
        this.g.setTag(Integer.valueOf(parseInt));
        this.L.b(false);
        this.L.c(false);
        String str = "";
        switch (RepeatModes.values()[parseInt]) {
            case SINGLE:
                this.L.b(true);
                o.c(getContext());
                str = "One";
                break;
            case ALL:
                this.L.c(true);
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
            u.a().a("CentreMiniPlayer", "Repeat", str);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("repeat");
            stringBuilder.append(str);
            an.a().a("click", "ac", "", "player", "", stringBuilder.toString(), "", "");
        }
        d.a().a("PREFERENCE_KEY_REPEAT_STATUS", parseInt, true);
    }

    private void w() {
        if (PlayerManager.a(this.s).d()) {
            this.g.setTag(Integer.valueOf(0));
        } else if (PlayerManager.a(this.s).e()) {
            this.g.setTag(Integer.valueOf(1));
        } else {
            this.g.setTag(Integer.valueOf(2));
        }
        a(this.g, false);
    }

    private void x() {
        boolean b = PlayerManager.a(this.s).b();
        if (b) {
            this.f.setImageResource(this.P[b]);
        } else {
            this.f.setImageDrawable(this.O);
        }
        this.f.setTag(Integer.valueOf(b));
    }

    public void e() {
        if (this.I != null) {
            this.I.refreshPlayerStatus();
        }
    }

    private void a(PlayerTrack playerTrack) {
        ((GaanaActivity) getContext()).hideFakePlayer();
        this.t.setPlayerStatus(true);
        if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO) {
            a(PlayerManager.a(GaanaApplication.getContext()).n());
        }
        c();
        if (this.a != null) {
            Util.a(this.s, "APP_WIDGET_UPDATE_ACTION", this.a.b());
        }
    }

    public void onClick(View view) {
        if (this.a == null) {
            this.a = PlayerManager.a(this.s).i();
        }
        if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO || (this.W != null && this.a.a(true).getBusinessObjId().equals(this.W.a(true).getBusinessObjId()))) {
            if (GaanaMusicService.t() || GaanaMusicService.s().m()) {
                ((BaseActivity) this.s).sendGAEvent("Mini Player", "Pause Click", "Mini Player - Pause Click");
                an.a().a("click", "ac", "", "miniplayer", "", "pause", "", "");
            } else {
                ((BaseActivity) this.s).sendGAEvent("Mini Player", "Play Click", "Mini Player - Play Click");
                an.a().a("click", "ac", "", "miniplayer", "", "play", "", "");
            }
            this.a.d(true);
            z();
        } else if (this.W != null) {
            if (Constants.aa) {
                u.a().a("Shuffle Product", "Gaana+ popup", "Mini Player Tap");
                Util.a(this.s, BLOCK_ACTION.SKIP);
                return;
            }
            GaanaLogger.a().a(this.s, true, false);
            an.a().a("click", "ac", "", "miniplayer", "", "play", "", "");
            ((BaseActivity) this.s).sendGAEvent("Mini Player", "Play Click", "Mini Player - Play Click");
            PlayerManager.a(this.s).c();
            this.W.d(true);
            PlayerManager.a(this.s).a(null, this.W, this.X);
            PlayerManager.a(this.s).a(PlayerType.GAANA, this.s);
            h();
        } else if (this.a != null) {
            if (GaanaMusicService.t() || GaanaMusicService.s().m()) {
                ((BaseActivity) this.s).sendGAEvent("Mini Player", "Pause Click", "Mini Player - Pause Click");
                an.a().a("click", "ac", "", "miniplayer", "", "pause", "", "");
            } else {
                ((BaseActivity) this.s).sendGAEvent("Mini Player", "Play Click", "Mini Player - Play Click");
                an.a().a("click", "ac", "", "miniplayer", "", "play", "", "");
            }
            this.a.d(true);
            z();
        }
    }

    private void y() {
        if (this.a == null) {
            this.a = PlayerManager.a(this.s).i();
        }
        if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO || (this.W != null && this.a.a(true).getBusinessObjId().equals(this.W.a(true).getBusinessObjId()))) {
            if (GaanaMusicService.t() || GaanaMusicService.s().m()) {
                ((BaseActivity) this.s).sendGAEvent("Mini Player", "Pause Click", "Mini Player - Pause Click");
                an.a().a("click", "ac", "", "miniplayer", "", "pause", "", "");
            } else {
                ((BaseActivity) this.s).sendGAEvent("Mini Player", "Play Click", "Mini Player - Play Click");
                an.a().a("click", "ac", "", "miniplayer", "", "play", "", "");
            }
            this.a.d(true);
            z();
        } else if (this.W != null) {
            if (Constants.aa) {
                u.a().a("Shuffle Product", "Gaana+ popup", "Mini Player Tap");
                Util.a(this.s, BLOCK_ACTION.SKIP);
                return;
            }
            GaanaLogger.a().a(this.s, true, false);
            an.a().a("click", "ac", "", "miniplayer", "", "play", "", "");
            ((BaseActivity) this.s).sendGAEvent("Mini Player", "Play Click", "Mini Player - Play Click");
            PlayerManager.a(this.s).c();
            this.W.d(true);
            PlayerManager.a(this.s).a(null, this.W, this.X);
            PlayerManager.a(this.s).a(PlayerType.GAANA, this.s);
            h();
        } else if (this.a != null) {
            if (GaanaMusicService.t() || GaanaMusicService.s().m()) {
                ((BaseActivity) this.s).sendGAEvent("Mini Player", "Pause Click", "Mini Player - Pause Click");
                an.a().a("click", "ac", "", "miniplayer", "", "pause", "", "");
            } else {
                ((BaseActivity) this.s).sendGAEvent("Mini Player", "Play Click", "Mini Player - Play Click");
                an.a().a("click", "ac", "", "miniplayer", "", "play", "", "");
            }
            this.a.d(true);
            z();
        }
    }

    private void z() {
        f();
        if (!ad.a(this.s).o().booleanValue()) {
            g();
        } else if (GaanaMusicService.t()) {
            o.d(getContext());
        } else {
            g();
        }
    }

    public void f() {
        if (this.s instanceof BaseActivity) {
            ((BaseActivity) this.s).refreshListView();
        }
    }

    public void g() {
        if (GaanaMusicService.t() || GaanaMusicService.s().m() || GaanaMusicService.s().l()) {
            this.J = 1;
            I();
            o.b(this.s, PauseReasons.MEDIA_BUTTON_TAP);
            return;
        }
        this.J = 0;
        I();
        o.a(this.s);
        if (ad.a(this.s).o().booleanValue()) {
            ad.a(this.s).j();
        }
    }

    private void b(View view) {
        if (Constants.ab) {
            Util.a(this.s, BLOCK_ACTION.SHUFFLE);
            return;
        }
        ((BaseActivity) this.s).sendGAEvent("Player", "Shuffle", "Player - Shuffle - Song");
        if (PlayerManager.a(this.s).E() != null) {
            PlayerManager.a(this.s).j(false);
        }
        c(view);
    }

    private void c(View view) {
        int parseInt = Integer.parseInt(view.getTag().toString());
        parseInt = parseInt == 1 ? 0 : parseInt + 1;
        if (parseInt == 0) {
            this.f.setImageDrawable(this.O);
        } else {
            this.f.setImageResource(this.P[parseInt]);
        }
        this.f.setTag(Integer.valueOf(parseInt));
        PlayerManager.a(getContext()).a(false);
        switch (parseInt) {
            case 0:
                PlayerManager.a(getContext()).a(false, null);
                an.a().a("click", "ac", "", "player", "off", "shuffle", "", "");
                u.a().a("CentreMiniPlayer", "Shuffle", "Off");
                return;
            case 1:
                PlayerManager.a(getContext()).a(true, null);
                an.a().a("click", "ac", "", "player", "on", "shuffle", "", "");
                u.a().a("CentreMiniPlayer", "Shuffle", "On");
                return;
            default:
                return;
        }
    }

    public void h() {
        A();
        o.a("listener_mini_frag", this.c);
        o.a("listener_mini_frag", this.U);
        this.t.setPlayerStatus(true);
        this.a = PlayerManager.a(GaanaApplication.getContext()).a(PlaySequenceType.CURRENT);
        if (this.a == null) {
            M();
            return;
        }
        a(PlayerManager.a(GaanaApplication.getContext()).n());
        if (PlayerManager.a(GaanaApplication.getContext()).t() && PlayerManager.a(GaanaApplication.getContext()).s() != -1) {
            this.a = PlayerManager.a(GaanaApplication.getContext()).a(PlayerManager.a(GaanaApplication.getContext()).s());
            a(this.a);
        }
        b(PlayerManager.a(GaanaApplication.getContext()).m());
        q();
        if (this.h != null) {
            if (PlayerStatus.b(this.s) == PlayerStates.PLAYING) {
                this.h.setVisibility(8);
                this.h.setImageResource(R.drawable.vector_player_pause_white);
            } else {
                this.h.setVisibility(0);
                this.h.setImageResource(R.drawable.vector_player_play_white);
            }
        }
        if (PlayerManager.a) {
            o.a(getContext());
            PlayerManager.a = false;
        } else if (PlayerManager.b) {
            PlayerManager.b = false;
        } else if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO && PlayerManager.a(GaanaApplication.getContext()).g()) {
            PlayerManager.a(GaanaApplication.getContext()).e(false);
            this.a = PlayerManager.a(GaanaApplication.getContext()).a(PlaySequenceType.CURRENT);
            GaanaMusicService.s().b(false);
            o.a(getContext(), this.a);
        } else {
            if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO && !PlayerManager.a(GaanaApplication.getContext()).h()) {
                this.a = PlayerManager.a(this.s).i();
            }
            if (PlayerStatus.a(this.s).c()) {
                this.E = PlayerStates.PLAYING;
                a(this.a);
                C();
            } else if (GaanaMusicService.s().l() && !GaanaMusicService.s().m() && !PlayerStatus.a(this.s).e()) {
                a(this.a);
                C();
            } else if (GaanaMusicService.s().m()) {
                a(this.a);
                this.J = 1;
                I();
            } else if (PlayerStatus.a(this.s).e()) {
                a(this.a);
                B();
            }
        }
    }

    private void A() {
        if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO && PlayerManager.a(GaanaApplication.getContext()).g()) {
            PlayerManager.a(GaanaApplication.getContext()).a(PlayerType.GAANA_RADIO, getContext());
        } else if (PlayerManager.a(GaanaApplication.getContext()).m() != PlayerType.GAANA_RADIO) {
            PlayerManager.a(GaanaApplication.getContext()).f(false);
        }
    }

    private void B() {
        this.w.setProgress(0);
        this.w.setSecondaryProgress(0);
        this.w.setMax(0);
        if (!this.D) {
            this.m = true;
        }
    }

    private void C() {
        int u;
        try {
            u = GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
            u = 0;
        }
        this.w.setMax(u);
        this.w.setSecondaryProgress(0);
        H();
        if (PlayerStatus.a(this.s).b() || PlayerStatus.a(this.s).c()) {
            this.J = 1;
            I();
            return;
        }
        this.J = 0;
        I();
    }

    public void a(PlayerType playerType) {
        b(playerType);
        a(PlayerManager.a(GaanaApplication.getContext()).n());
        if (this.I != null) {
            this.I.onPlayerStateChanged();
        }
    }

    public void b(PlayerType playerType) {
        if (playerType == PlayerType.GAANA) {
            this.w.setVisibility(0);
        } else {
            this.w.setVisibility(8);
        }
    }

    public void m() {
        int i = 0;
        int i2 = this.v != null ? 1 : 0;
        if (this.u != null) {
            i = 1;
        }
        if ((i2 & i) != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("android-app://com.gaana/gaanagoogle/");
            stringBuilder.append(this.B);
            stringBuilder.append(this.v.getSeokey());
            AppIndex.AppIndexApi.viewEnd(this.u, (GaanaActivity) this.s, Uri.parse(stringBuilder.toString()));
            this.u.disconnect();
        }
    }

    public void a(Track track) {
        if (this.u.isConnected()) {
            m();
        }
        this.u.connect();
        this.B = this.A;
        this.v = track;
        List arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("android-app://com.gaana/gaanagoogle/");
        stringBuilder.append(this.A);
        stringBuilder.append(track.getSeokey());
        Uri parse = Uri.parse(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("https://gaana.com/");
        stringBuilder.append(this.A);
        stringBuilder.append(track.getSeokey());
        AppIndex.AppIndexApi.view(this.u, (GaanaActivity) this.s, parse, track.getName(), Uri.parse(stringBuilder.toString()), arrayList);
    }

    /* Access modifiers changed, original: protected */
    public void a(final String str) {
        if (isAdded()) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    String string = MiniPlayerFragmentV4.this.getResources().getString(R.string.DataError);
                    if ("JSON Data Null".equalsIgnoreCase(str)) {
                        string = MiniPlayerFragmentV4.this.s.getString(R.string.failed_to_connect_to_server);
                    }
                    ((BaseActivity) MiniPlayerFragmentV4.this.getActivity()).mDialog.a(MiniPlayerFragmentV4.this.s.getString(R.string.app_name), string, Boolean.valueOf(false), new b() {
                        public void onCancelListner() {
                        }

                        public void onOkListner(String str) {
                        }
                    }, false);
                }
            });
        }
    }

    private void D() {
        this.J = 0;
        I();
        if (this.h != null) {
            this.h.setImageResource(R.drawable.vector_player_play_white);
            this.h.setVisibility(0);
        }
        this.E = PlayerStates.PAUSED;
        f();
    }

    private void E() {
        this.J = 1;
        I();
        if (this.h != null) {
            this.h.setImageResource(R.drawable.vector_player_pause_white);
            if (this.l) {
                this.h.setVisibility(0);
            } else {
                this.h.setVisibility(4);
            }
        }
        if (!GaanaMusicService.s().m()) {
            if (this.D) {
                G();
            } else {
                C();
            }
        }
        this.E = PlayerStates.PLAYING;
        f();
    }

    private void F() {
        this.J = 0;
        I();
        this.E = PlayerStates.STOPPED;
        if (this.h != null) {
            this.h.setVisibility(0);
        }
        f();
    }

    private void a(boolean z, boolean z2) {
        this.J = 1;
        I();
        if (z) {
            d();
        }
        if (z2) {
            a(z, PlayerCommands.PLAY_PREVIOUS);
            return;
        }
        this.C.removeCallbacksAndMessages(null);
        B();
    }

    private void b(boolean z, boolean z2) {
        this.J = 1;
        I();
        if (z) {
            d();
        }
        if (z2) {
            a(z, PlayerCommands.PLAY_NEXT);
            f();
        } else if (PlayerManager.a(GaanaApplication.getContext()).m() != PlayerType.GAANA_RADIO) {
            this.C.removeCallbacksAndMessages(null);
            B();
        } else if (PlayerManager.a(GaanaApplication.getContext()).f()) {
            if (!PlayerManager.a(GaanaApplication.getContext()).h()) {
                PlayerManager.a(GaanaApplication.getContext()).f(true);
            }
            this.C.removeCallbacksAndMessages(null);
            B();
        } else {
            this.H = true;
            ((BaseActivity) getActivity()).showProgressDialog();
        }
    }

    private void a(boolean z, PlayerCommands playerCommands) {
        switch (playerCommands) {
            case PLAY_PREVIOUS:
                if (z) {
                    ap.a().a(getContext(), this.s.getString(R.string.no_previous_song));
                    return;
                }
                return;
            case PLAY_NEXT:
                if (z) {
                    ap.a().a(getContext(), this.s.getString(R.string.no_next_song));
                    if (GaanaMusicService.s().l() || GaanaMusicService.s().k()) {
                        this.J = 0;
                        I();
                        return;
                    }
                    return;
                }
                if (!ad.a(this.s).o().booleanValue()) {
                    ap.a().a(getContext(), this.s.getString(R.string.playback_ended));
                }
                this.a = PlayerManager.a(GaanaApplication.getContext()).a(PlayerManager.a(GaanaApplication.getContext()).s());
                this.C.removeCallbacksAndMessages(null);
                B();
                this.J = 0;
                I();
                return;
            default:
                return;
        }
    }

    private void G() {
        int u;
        try {
            u = GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
            u = 0;
        }
        o.a(getContext(), this.w.getProgress());
        this.w.setMax(u);
        this.w.setSecondaryProgress(this.w.getProgress());
        H();
        if (PlayerStatus.a(this.s).b() || PlayerStatus.a(this.s).c()) {
            this.J = 1;
            I();
            return;
        }
        this.J = 0;
        I();
    }

    private void H() {
        int v;
        if (!GaanaMusicService.s().m()) {
            if (!ad.a(this.s).o().booleanValue()) {
                int u;
                try {
                    v = GaanaMusicService.s().v();
                    u = GaanaMusicService.s().u();
                } catch (IllegalStateException unused) {
                    v = 0;
                    u = v;
                }
                int i = u - v;
                this.w.setProgress(v);
                this.w.setMax(u);
                this.w.setSelected(false);
                this.w.setSecondaryProgress((int) ((0.01d * ((double) GaanaMusicService.s().t())) * ((double) GaanaMusicService.s().u())));
                r9 = new Object[2];
                long j = (long) v;
                r9[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) / 60);
                r9[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % 60);
                String.format("%2d:%02d", r9);
                r8 = new Object[2];
                long j2 = (long) i;
                r8[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j2) / 60);
                r8[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j2) % 60);
                String format = String.format("%2d:%02d", r8);
                if (v > 15958442) {
                    B();
                }
                if (this.m && v > 30000) {
                    this.m = false;
                    if (!(this.a == null || this.a.g() == null || this.a.a(true) == null || this.a.a(true).isLocalMedia() || (!this.a.g().equalsIgnoreCase(PLAYOUT_SECTION_TYPE.SEARCH_AUTO_SUGGEST.name()) && !this.a.g().equalsIgnoreCase(PLAYOUT_SECTION_TYPE.SEARCH_VOICE.name())))) {
                        GaanaSearchManager a = GaanaSearchManager.a();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(this.a.h());
                        stringBuilder.append(":-");
                        stringBuilder.append(this.a.a(true).getName());
                        a.a(stringBuilder.toString());
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(this.a.h());
                        stringBuilder2.append(":-");
                        stringBuilder2.append(this.a.a(true).getName());
                        d.a().a("PREFF_SEARCH_RECOMMENDATION_FOR_TRACK", stringBuilder2.toString(), false);
                    }
                }
                if (!((format.equalsIgnoreCase(" 0:00") && PlayerManager.a(GaanaApplication.getContext()).t() && PlayerManager.a(GaanaApplication.getContext()).v()) || !GaanaMusicService.t() || GaanaMusicService.s().m())) {
                    AnonymousClass16 anonymousClass16 = new Runnable() {
                        public void run() {
                            MiniPlayerFragmentV4.this.H();
                        }
                    };
                    this.C.removeCallbacksAndMessages(null);
                    this.C.postDelayed(anonymousClass16, 1000);
                }
            } else if (GaanaMusicService.t()) {
                AnonymousClass17 anonymousClass17 = new Runnable() {
                    public void run() {
                        MiniPlayerFragmentV4.this.H();
                    }
                };
                this.C.removeCallbacksAndMessages(null);
                this.C.postDelayed(anonymousClass17, 1000);
            } else {
                if (this.C != null) {
                    this.C.removeCallbacksAndMessages(null);
                }
            }
        }
    }

    public void a(f fVar, int i) {
        if (GaanaMusicService.s().m()) {
            this.w.setSecondaryProgress(0);
            return;
        }
        this.w.setMax(fVar.u());
        this.w.setSecondaryProgress((int) ((0.01d * ((double) i)) * ((double) fVar.u())));
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ((GaanaActivity) this.s).setPlayerListeners(this);
    }

    private void J() {
        o.a("listener_mini_frag", this.U);
        this.a = PlayerManager.a(GaanaApplication.getContext()).a(PlaySequenceType.CURRENT);
        a(this.a);
        if (!this.D) {
            B();
        }
        PlayerManager.a(this.s).d(null);
        this.J = 2;
        I();
        this.E = PlayerStates.LOADING;
        f();
        if (GaanaApplication.sessionHistoryCount > 0) {
            ((GaanaActivity) this.s).CallCustomCardApi(false, true);
        }
        if (!(((GaanaActivity) this.s).IS_COACHMARK_VISIBLE || GaanaApplication.sessionHistoryCount <= 0 || this.a.a(true).isLocalMedia())) {
            Util.i(this.s, "Listen");
        }
        if (((GaanaActivity) this.s).hasWindowFocus()) {
            d.a().a("PREFERENCE_KEY_MINI_PLAYER_SWIPE_INITIATED", true, false);
        }
        if (this.h != null) {
            this.h.setVisibility(4);
        }
    }

    private void K() {
        if (GaanaMusicService.s().m()) {
            this.J = 2;
            I();
        } else if (GaanaMusicService.t()) {
            this.J = 1;
            I();
        } else {
            this.J = 0;
            I();
        }
    }

    public void onResume() {
        super.onResume();
        L();
        try {
            h();
        } catch (Exception unused) {
        }
    }

    public void onPause() {
        super.onPause();
        this.I = null;
        if (o.a("listener_mini_frag") != null) {
            PlayerManager.a(GaanaApplication.getContext()).a(null);
            PlayerManager.a(GaanaApplication.getContext()).a(null);
            PlayerManager.a(GaanaApplication.getContext()).a(null);
            ad.a(GaanaApplication.getContext()).a(null);
            Util.a(null);
            this.C.removeCallbacksAndMessages(null);
            o.b("listener_mini_frag");
            o.d("listener_mini_frag");
        }
    }

    public PlayerStates n() {
        return this.E;
    }

    public void a(List<PlayerTrack> list) {
        if (list != null && list.size() > 0) {
            if (this.G == null) {
                this.G = new ArrayList();
            }
            this.G.clear();
            this.G.addAll(list);
        }
    }

    private void L() {
        PlayerManager.a(GaanaApplication.getContext()).a((aj) this);
        PlayerManager.a(GaanaApplication.getContext()).a((ak) this);
        PlayerManager.a(GaanaApplication.getContext()).a((a) this);
        ad.a(GaanaApplication.getContext()).a((ad.a) this);
    }

    public void i() {
        a(PlayerManager.a(GaanaApplication.getContext()).n());
        if (PlayerManager.a(GaanaApplication.getContext()).d()) {
            PlayerManager.a(GaanaApplication.getContext()).b(false);
            d.a().a("PREFERENCE_KEY_REPEAT_STATUS", 0, true);
        }
        if (this.I != null) {
            this.I.on_enque();
        }
    }

    public void j() {
        if (this.I != null) {
            this.I.refreshList();
        }
    }

    public void k() {
        a(PlayerManager.a(GaanaApplication.getContext()).n());
        if (this.I != null) {
            this.I.on_deque();
        }
    }

    public void a(boolean z) {
        a(PlayerManager.a(GaanaApplication.getContext()).n());
        if (this.I != null) {
            this.I.updateCardAdapter(z);
        }
    }

    public void l() {
        if (isAdded() && ad.a(this.s).o().booleanValue()) {
            this.H = false;
            this.a = PlayerManager.a(this.s).i();
            if (this.a != null) {
                a(this.a);
            }
            if (this.I != null) {
                this.I.onLiveRadioUpdate();
            }
        }
    }

    public void a(Boolean bool) {
        if (this.H || bool.booleanValue()) {
            this.H = false;
            PlayerManager.a(GaanaApplication.getContext()).k();
            PlayerManager.a(GaanaApplication.getContext()).f(true);
            if (this.I != null) {
                this.I.onRadioTracksFetched(bool.booleanValue());
            }
        }
    }

    public void o() {
        if (this.I != null) {
            this.I.refreshForFavorite();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(int i) {
        if ((this.n || this.o) && i == PlayerManager.a(GaanaApplication.getContext()).s()) {
            this.o = false;
            this.n = false;
            return false;
        }
        this.o = false;
        this.n = false;
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean b(int i) {
        if ((this.p || this.q) && i == PlayerManager.a(GaanaApplication.getContext()).s()) {
            this.q = false;
            this.p = false;
            return false;
        }
        this.q = false;
        this.p = false;
        return true;
    }

    public void a(av avVar) {
        this.I = avVar;
    }

    private void M() {
        FragmentManager supportFragmentManager = ((GaanaActivity) this.s).getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag("player_fragment");
        if (findFragmentByTag != null) {
            try {
                FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
                beginTransaction.remove(findFragmentByTag);
                beginTransaction.setCustomAnimations(R.anim.fade_out, R.anim.fade_in);
                beginTransaction.commitAllowingStateLoss();
            } catch (Exception unused) {
            }
        }
    }
}
