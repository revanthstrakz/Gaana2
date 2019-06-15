package com.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import android.util.TypedValue;
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
import android.widget.FrameLayout.LayoutParams;
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
import com.gaana.models.BusinessObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Tracks.Track;
import com.gaana.view.DownloadClickAnimation;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.gaana.view.item.PopupWindowView;
import com.gaana.view.item.SquareImageViewByHeight;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.integralads.avid.library.inmobi.AvidBridge;
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
import com.managers.j;
import com.managers.u;
import com.models.PlayerTrack;
import com.moe.pushlibrary.utils.MoEHelperUtils;
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
import com.services.l.as;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;
import java.util.concurrent.TimeUnit;

public class PlayerRadioFragmentV2 extends BaseFragment implements OnClickListener, DownloadPopupListener, b, a, j.a {
    private static int c;
    private SeekBar A;
    private LinearLayout B;
    private DownloadClickAnimation C;
    private ImageView D;
    private ImageView E;
    private ImageView F;
    private ImageView G;
    private TextView H;
    private TextView I;
    private TextView J;
    private FrameLayout K;
    private LinearLayout L;
    private View M;
    private PublisherAdView N;
    private TextView O;
    private TextView P;
    private ImageView Q;
    private boolean R = false;
    private m S = new m() {
        public void onAdEventUpdate(f fVar, AdEvent adEvent) {
        }

        public void onBufferingUpdate(f fVar, int i) {
        }

        public void onInfo(f fVar, int i, int i2) {
        }

        public void onPrepared(f fVar) {
            if (!PlayerRadioFragmentV2.this.isActivityDestroyed()) {
                PlayerRadioFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (GaanaMusicService.s().isPlaying()) {
                            PlayerRadioFragmentV2.this.mPlayerStates = PlayerStates.PLAYING;
                        }
                        if (PlayerRadioFragmentV2.this.n) {
                            PlayerRadioFragmentV2.this.p();
                            PlayerRadioFragmentV2.this.s();
                            PlayerRadioFragmentV2.this.n = false;
                            return;
                        }
                        PlayerRadioFragmentV2.this.o();
                        PlayerRadioFragmentV2.this.s();
                    }
                });
            }
        }

        public void onError(f fVar, int i, int i2) {
            if (!PlayerRadioFragmentV2.this.isActivityDestroyed()) {
                if (i == -1000 || i == -1001) {
                    PlayerRadioFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            PlayerRadioFragmentV2.this.t();
                        }
                    });
                }
            }
        }

        public void onCompletion(f fVar) {
            PlayerRadioFragmentV2.this.mPlayerStates = PlayerStates.STOPPED;
        }
    };
    private View T;
    private int U = 0;
    private long V = 0;
    private int W;
    private String X = "0";
    n a = new n() {
        public void displayErrorToast(String str, int i) {
        }

        public void onPlayerRepeatReset(boolean z) {
        }

        public void onStreamingQualityChanged(int i) {
        }

        public void onPlayerPlay() {
            if (!PlayerRadioFragmentV2.this.isActivityDestroyed()) {
                PlayerRadioFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV2.this.u();
                    }
                });
            }
        }

        public void onPlayerPause() {
            if (!PlayerRadioFragmentV2.this.isActivityDestroyed()) {
                PlayerRadioFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV2.this.v();
                    }
                });
            }
        }

        public void onPlayerResume() {
            if (!PlayerRadioFragmentV2.this.isActivityDestroyed()) {
                PlayerRadioFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV2.this.w();
                    }
                });
            }
        }

        public void onPlayerStop() {
            if (!PlayerRadioFragmentV2.this.isActivityDestroyed()) {
                PlayerRadioFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV2.this.t();
                    }
                });
            }
        }

        public void onPlayPrevious(final boolean z, final boolean z2) {
            if (!PlayerRadioFragmentV2.this.isActivityDestroyed()) {
                PlayerRadioFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV2.this.a(z, z2);
                    }
                });
            }
        }

        public void onPlayNext(final boolean z, final boolean z2) {
            if (!PlayerRadioFragmentV2.this.isActivityDestroyed()) {
                PlayerRadioFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV2.this.b(z, z2);
                    }
                });
            }
        }

        public void displayErrorDialog(String str, ErrorType errorType) {
            if (!PlayerRadioFragmentV2.this.isActivityDestroyed()) {
                PlayerRadioFragmentV2.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV2.this.t();
                    }
                });
            }
        }
    };
    int b = 100;
    private PlayerMaterialActionBar d;
    private View e;
    private SquareImageViewByHeight f;
    private TextView g;
    private LinearLayout h;
    private ImageView i;
    private ProgressBar j;
    private ImageView k;
    private TextView l;
    private Toolbar m;
    private boolean n;
    private TypedValue o;
    private final Handler p = new Handler();
    private Drawable q;
    private Drawable r;
    private Drawable s;
    private Drawable t;
    private TextView u;
    private TextView v;
    private View w;
    private View x;
    private TextView y;
    private CrossFadeImageView z;

    /* renamed from: com.fragments.PlayerRadioFragmentV2$14 */
    static /* synthetic */ class AnonymousClass14 {
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

    public void d(ADSTATUS adstatus) {
    }

    public void onRadioTracksFetched(boolean z) {
    }

    public void on_deque() {
    }

    public void on_enque() {
    }

    public void refreshForFavorite() {
    }

    public void updateCardAdapter(boolean z) {
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.e = layoutInflater.inflate(R.layout.fragment_player_radio_v2, viewGroup, false);
        this.f = (SquareImageViewByHeight) this.e.findViewById(R.id.player_image);
        this.g = (TextView) this.e.findViewById(R.id.tvPlayerStartTimer);
        this.h = (LinearLayout) this.e.findViewById(R.id.streamingListLayoutRadio);
        this.i = (ImageView) this.e.findViewById(R.id.playerButton);
        this.j = (ProgressBar) this.e.findViewById(R.id.progressBarPlayer);
        this.k = (ImageView) this.e.findViewById(R.id.playerBtnNext);
        this.m = (Toolbar) this.e.findViewById(R.id.toolbar);
        this.d = new PlayerMaterialActionBar(getContext(), PlayerVersion.PlayerV2);
        this.m.addView(this.d);
        int[] iArr = new int[]{R.attr.bottom_pause_button, R.attr.bottom_play_button, R.attr.selector_btn_global_bg_transparent, R.attr.miniplayer_play};
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.r = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(66, -1));
        this.q = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(68, -1));
        this.s = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(86, -1));
        this.t = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(31, -1));
        this.u = (TextView) this.e.findViewById(R.id.queue_panel_main_text_bottom);
        this.v = (TextView) this.e.findViewById(R.id.queue_panel_secondary_text_bottom);
        this.w = this.d.findViewById(R.id.tracker_previous);
        this.x = this.d.findViewById(R.id.tracker_next);
        this.l = (TextView) this.d.findViewById(R.id.qualityText);
        this.y = (TextView) this.d.findViewById(R.id.radioName);
        this.A = (SeekBar) this.e.findViewById(R.id.seekBar);
        this.B = (LinearLayout) this.e.findViewById(R.id.optionLayout);
        this.D = (ImageView) this.e.findViewById(R.id.queue_panel_download_button);
        this.E = (ImageView) this.e.findViewById(R.id.menu_option_img);
        this.E.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerRadioFragmentV2.this.j();
            }
        });
        this.z = (CrossFadeImageView) this.d.findViewById(R.id.tracker_img);
        this.F = (ImageView) this.e.findViewById(R.id.lyricsButton);
        this.H = (TextView) this.e.findViewById(R.id.lyricsButtonText);
        this.G = (ImageView) this.e.findViewById(R.id.videoButton);
        this.J = (TextView) this.e.findViewById(R.id.videoButtonText);
        this.K = (FrameLayout) this.e.findViewById(R.id.playerTopLayout);
        this.L = (LinearLayout) this.e.findViewById(R.id.cardLayoutContainer);
        this.M = this.e.findViewById(R.id.timeSeekerBlackView);
        this.I = (TextView) this.e.findViewById(R.id.lyricsTextButton);
        this.O = (TextView) this.d.findViewById(R.id.trackText);
        this.P = (TextView) this.d.findViewById(R.id.albumText);
        this.Q = (ImageView) this.d.findViewById(R.id.playerQueueIcon);
        this.Q.setVisibility(8);
        obtainStyledAttributes.recycle();
        this.o = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.song_quality_color, this.o, true);
        g();
        this.k.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.F.setOnClickListener(this);
        i();
        this.A.setPadding(0, 0, 0, 0);
        this.A.setFocusable(false);
        this.A.setThumb(new ColorDrawable(getResources().getColor(17170445)));
        this.A.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        setGAScreenName("PlayerHomeScreen", "PlayerHomeScreen");
        c();
        f();
        e();
        return this.e;
    }

    public void onResume() {
        super.onResume();
        e();
    }

    private void c() {
        int b;
        String k = Util.k();
        int parseInt = Integer.parseInt(k.split(AvidJSONUtil.KEY_X)[0]);
        int parseInt2 = Integer.parseInt(k.split(AvidJSONUtil.KEY_X)[1]);
        Util.b(193);
        Util.b((int) CallbackHandler.MSG_ROUTE_VOLUME_CHANGED);
        if (a(this.mContext)) {
            b = ((parseInt - Util.b(193)) - Util.b((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION)) - Util.b(20);
        } else {
            b = ((parseInt - Util.b(193)) - Util.b((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION)) - Util.b(20);
        }
        parseInt2 = Math.min(parseInt2, b);
        if (parseInt2 < Util.b((int) MoEHelperUtils.BASELINE_SCREEN_DPI)) {
            parseInt2 = Util.b((int) MoEHelperUtils.BASELINE_SCREEN_DPI);
        }
        this.b = Util.a((float) parseInt2, this.mContext);
        LayoutParams layoutParams = (LayoutParams) this.f.getLayoutParams();
        layoutParams.height = parseInt2;
        layoutParams.width = parseInt2;
        this.f.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.B.getLayoutParams();
        layoutParams2.width = parseInt2;
        this.B.setLayoutParams(layoutParams2);
        layoutParams = (LayoutParams) this.L.getLayoutParams();
        layoutParams.gravity = 49;
        if (d()) {
            layoutParams.topMargin = ((((parseInt - Util.b(193)) - Util.b(60)) - Util.b(50)) - parseInt2) / 2;
        } else {
            layoutParams.topMargin = (((parseInt - Util.b(193)) - Util.b(60)) - parseInt2) / 2;
        }
        this.L.setLayoutParams(layoutParams);
    }

    private boolean d() {
        boolean a = a(this.mContext);
        return a ? getResources().getBoolean(R.bool.isPlayerAdEnabled) : a;
    }

    private void e() {
        boolean a = a(this.mContext);
        if (a) {
            a = getResources().getBoolean(R.bool.isPlayerAdEnabled);
        }
        LinearLayout linearLayout = (LinearLayout) this.e.findViewById(R.id.llNativeAdSlot);
        if (a && this.R) {
            linearLayout.setVisibility(0);
            linearLayout.bringToFront();
            return;
        }
        linearLayout.setVisibility(8);
    }

    private void f() {
        if (e.W == 0) {
            boolean z = getResources().getBoolean(R.bool.isPlayerAdEnabled);
            if (this.mContext != null && ap.a().b(this.mContext) && z) {
                if (this.N == null) {
                    this.N = new PublisherAdView(this.mContext);
                }
                ColombiaAdViewManager.a().b(this.mContext, this.e, e.y, this.N, this, "");
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

    private void g() {
        this.m = (Toolbar) this.e.findViewById(R.id.toolbar);
        this.m.setContentInsetsAbsolute(0, 0);
        this.m.getMenu().clear();
        this.m.setBackgroundColor(getResources().getColor(R.color.transparent_color));
        this.d.setToolbar(this.m);
    }

    private void h() {
        getPlayingTrack();
        this.O.setVisibility(0);
        this.P.setVisibility(0);
        this.O.setText("Now Playing");
        TextView textView = this.P;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("From ");
        stringBuilder.append(this.mRadioManager.l());
        textView.setText(stringBuilder.toString());
    }

    private void i() {
        if (ad.a(this.mContext).p().booleanValue()) {
            this.k.setVisibility(8);
            this.l.setVisibility(8);
        } else if (ad.a(this.mContext).o().booleanValue()) {
            this.k.setVisibility(8);
            this.l.setVisibility(8);
        } else {
            this.k.setVisibility(0);
            this.l.setVisibility(0);
        }
    }

    private void j() {
        Track b = PlayerManager.a(this.mContext).i().b();
        if (b == null) {
            b = PlayerManager.a(this.mContext).i().b();
        }
        b.setBusinessObjType(BusinessObjectType.Tracks);
        if (b == null || PlayerManager.a(this.mContext).m() == PlayerType.GAANA_RADIO) {
            b = PlayerManager.a(this.mContext).i().b();
        }
        if (!(b == null || b.getBusinessObjType() == null || ad.a(this.mContext).p().booleanValue())) {
            PopupWindowView instance = PopupWindowView.getInstance(this.mContext, null);
            instance.setDownloadPopupListener(this);
            instance.contextPopupWindow(b, true, this, false);
        }
        u.a().b("Player", "Context Menu tapped");
    }

    public void a(Track track) {
        if (track != null) {
            this.B.setAlpha(0.0f);
            this.B.setVisibility(0);
            d(getPlayingTrack());
            this.C = new DownloadClickAnimation(this.mContext, this, this.D, track, this.e);
            if (!(this.mCurrentTrack == null || this.C == null)) {
                this.C.changeDownlaodButtonIcon(track, this.D);
                c(track);
                b(track);
            }
            this.B.animate().alpha(1.0f).setDuration(3000).start();
        }
    }

    private void b(final Track track) {
        if (this.mPlayerManager != null && track != null) {
            ImageView imageView = (ImageView) this.e.findViewById(R.id.menu_add_to_playlist);
            imageView.setTag(track.getBusinessObjId());
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PlayerRadioFragmentV2.this.T = view;
                    af.a(PlayerRadioFragmentV2.this.mContext, null).a((int) R.id.addToPlaylistMenu, track);
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
            final ImageView imageView = (ImageView) this.e.findViewById(R.id.favourite_item);
            imageView.setTag(track.getBusinessObjId());
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PlayerRadioFragmentV2.this.T = view;
                    PlayerRadioFragmentV2.this.a(imageView, track);
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
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(49, -1)));
                obtainStyledAttributes.recycle();
            }
        }
    }

    public void a(final ImageView imageView, final Track track) {
        if (track != null) {
            track.setBusinessObjType(BusinessObjectType.Tracks);
            af a = af.a(this.mContext, null);
            a.a("Radio Player Screen");
            a.b(track.getBusinessObjId());
            a.a((int) R.id.favoriteMenu, (BusinessObject) track, new a() {
                public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
                    ImageView imageView = imageView;
                    if (track == null || !track.isFavorite().booleanValue()) {
                        TypedArray obtainStyledAttributes = PlayerRadioFragmentV2.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        imageView.setImageDrawable(ContextCompat.getDrawable(PlayerRadioFragmentV2.this.getContext(), obtainStyledAttributes.getResourceId(49, -1)));
                        obtainStyledAttributes.recycle();
                        return;
                    }
                    imageView.setImageResource(R.drawable.vector_more_option_favorited);
                    imageView.setPadding(PlayerRadioFragmentV2.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp10), PlayerRadioFragmentV2.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp10), PlayerRadioFragmentV2.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp10), PlayerRadioFragmentV2.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp10));
                    if (PlayerRadioFragmentV2.this.T != null) {
                        Animation loadAnimation = AnimationUtils.loadAnimation(PlayerRadioFragmentV2.this.mContext, R.anim.favorite_tap_animation);
                        loadAnimation.setInterpolator(new com.a.a(0.2d, 20.0d));
                        PlayerRadioFragmentV2.this.T.startAnimation(loadAnimation);
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
        k();
        b();
        a(getPlayingTrack());
    }

    private void k() {
        if (!(ad.a(this.mContext).o().booleanValue() || GaanaApplication.getInstance().isAppInOfflineMode())) {
            boolean j = Util.j(this.mContext);
        }
        if (!(this.mCurrentTrack == null || this.mCurrentTrack.a(true) == null)) {
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(this.mCurrentTrack.a(true).getBusinessObjId()));
            if (!this.mCurrentTrack.a(true).isLocalMedia() && ((ap.a().o() || DownloadManager.c().j(this.mCurrentTrack.a(true).getBusinessObjId()).booleanValue()) && e != null)) {
                DownloadStatus downloadStatus = DownloadStatus.DOWNLOADED;
            }
        }
        String str = "";
        GaanaApplication gaanaApplication;
        switch (d.a().b("PREFERENCE_KEY_STREAMING_QUALITY", Constants.s(), false)) {
            case 10000:
                str = this.mContext.getString(R.string.low);
                break;
            case 10001:
                gaanaApplication = this.mAppState;
                if (!GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                    str = this.mContext.getString(R.string.medium);
                    break;
                } else {
                    str = this.mContext.getString(R.string.med);
                    break;
                }
            case 10002:
                str = this.mContext.getString(R.string.high);
                break;
            case 10003:
                gaanaApplication = this.mAppState;
                if (!GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                    str = this.mContext.getString(R.string.high_defination);
                    break;
                } else {
                    str = this.mContext.getString(R.string.hd);
                    break;
                }
            case 10004:
                str = this.mContext.getString(R.string.auto);
                break;
        }
        this.l.setText(str.toUpperCase());
    }

    public void onPlayerStateChanged() {
        if (isAdded()) {
            i();
        }
    }

    public void refreshList() {
        if (isAdded()) {
            this.mCurrentTrack = PlayerManager.a(this.mContext).i();
            l();
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
                    a();
                } else if (GaanaMusicService.t()) {
                    o.d(getContext());
                    c = 0;
                } else {
                    a();
                }
            } else if (id == R.id.qualityText) {
                a(view);
            }
        } else if (!Constants.aa || Constants.h > 0) {
            ((BaseActivity) this.mContext).sendGAEvent("Player", "Skip", "Player - Skip - Song");
            o.f(getContext());
        } else {
            Util.a(this.mContext, BLOCK_ACTION.SKIP);
        }
    }

    public void a() {
        if (GaanaMusicService.t() || GaanaMusicService.s().m() || GaanaMusicService.s().l()) {
            this.i.setImageDrawable(this.r);
            o.b(this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
            return;
        }
        this.i.setImageDrawable(this.q);
        o.a(this.mContext);
        if (ad.a(this.mContext).o().booleanValue()) {
            ad.a(this.mContext).j();
        }
    }

    private void l() {
        if (this.mPlayerManager.m() == PlayerType.GAANA_RADIO && this.mPlayerManager.g()) {
            this.mPlayerManager.a(PlayerType.GAANA_RADIO, getContext());
        } else if (this.mPlayerManager.m() != PlayerType.GAANA_RADIO) {
            this.mPlayerManager.f(false);
        }
    }

    public void b() {
        this.mContext = getContext();
        if (this.mContext != null) {
            l();
            o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.a);
            o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.S);
            this.mAppState.setPlayerStatus(true);
            this.mCurrentTrack = PlayerManager.a(GaanaApplication.getContext()).i();
            onPlayerStateChanged();
            b(this.mPlayerManager.a(PlaySequenceType.CURRENT));
            c(this.mPlayerManager.a(PlaySequenceType.CURRENT));
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
                    o();
                } else if (GaanaMusicService.s().l() && !GaanaMusicService.s().m() && !PlayerStatus.a(getContext()).e()) {
                    a(this.mCurrentTrack);
                    o();
                } else if (GaanaMusicService.s().m()) {
                    a(this.mCurrentTrack);
                    if (GaanaMusicService.s().l()) {
                        this.i.setImageDrawable(this.r);
                    } else {
                        this.i.setImageDrawable(this.r);
                    }
                } else if (PlayerStatus.a(getContext()).e()) {
                    a(this.mCurrentTrack);
                    r();
                }
            }
        }
    }

    private void a(PlayerTrack playerTrack) {
        this.mAppState.setPlayerStatus(true);
        CharSequence trim = getSubtitleText(getPlayingTrack().getAlbumTitle(), getPlayingTrack().getArtistNames()).trim();
        if (Constants.aG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getString(R.string.CASTING_TO));
            stringBuilder.append(Constants.aH);
            trim = stringBuilder.toString();
        } else if (this.mPlayerManager.m() == PlayerType.GAANA_RADIO && !TextUtils.isEmpty(this.mRadioManager.l()) && this.mRadioManager.o().booleanValue()) {
            trim = this.mRadioManager.l();
        }
        this.u.setTypeface(Util.i(this.mContext));
        this.u.setText(getPlayingTrack().getName());
        this.v.setText(trim);
        this.v.setSelected(true);
        if (getPlayingTrack().isLocalMedia()) {
            this.f.bindImageForLocalMedia(getPlayingTrack().getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
            Toolbar toolbar = (Toolbar) this.e.findViewById(R.id.toolbar);
            if (toolbar != null) {
                Menu menu = toolbar.getMenu();
                if (menu != null) {
                    menu.findItem(R.id.menu_add_to_playlist).setVisible(false);
                }
            }
        } else {
            m();
        }
        this.w.setVisibility(8);
        this.x.setVisibility(8);
        this.z.setVisibility(8);
        this.l.setVisibility(8);
        this.y.setVisibility(8);
        h();
    }

    private void m() {
        try {
            this.f.bindImage((BusinessObject) getPlayingTrack(), Util.f(this.mContext, getPlayingTrack().getArtworkLarge()), ScaleType.CENTER_CROP);
        } catch (OutOfMemoryError unused) {
            this.f.bindImage(getPlayingTrack().getArtwork(), ScaleType.CENTER_CROP);
        }
    }

    private void b(PlayerTrack playerTrack) {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.F.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(73, -1)));
        this.H.setAlpha(0.2f);
        this.F.setClickable(false);
        this.H.setClickable(false);
        obtainStyledAttributes.recycle();
    }

    private void d(Track track) {
        if (TextUtils.isEmpty(track.getLyricsUrl())) {
            this.I.setVisibility(8);
        } else {
            this.I.setAlpha(1.0f);
            this.I.setClickable(true);
        }
        this.I.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PlayerRadioFragmentV2.this.n();
            }
        });
    }

    private void c(PlayerTrack playerTrack) {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.G.setTag(playerTrack);
        this.J.setTag(playerTrack);
        if (playerTrack != null && this.G != null) {
            if (!Constants.cF || TextUtils.isEmpty(playerTrack.b().getYoutubeId())) {
                if (Constants.l) {
                    this.J.setTextColor(getResources().getColor(R.color.black_alfa_20));
                } else {
                    this.J.setTextColor(getResources().getColor(R.color.white_alfa_20));
                }
                this.G.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(71, -1)));
                this.G.setClickable(false);
                this.J.setClickable(false);
                an.a().a("click", "ac", "", "radioplayer", "", "video", "", AvidBridge.APP_STATE_INACTIVE);
                return;
            }
            this.G.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(72, -1)));
            if (Constants.l) {
                this.J.setTextColor(getResources().getColor(R.color.black));
            } else {
                this.J.setTextColor(getResources().getColor(R.color.white));
            }
            this.G.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PlayerTrack playerTrack = (PlayerTrack) view.getTag();
                    u.a().b("Player", "Video Played");
                    Util.a(PlayerRadioFragmentV2.this.mContext, playerTrack.b().getYoutubeId(), "", playerTrack.b(), 0);
                }
            });
            this.J.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PlayerTrack playerTrack = (PlayerTrack) view.getTag();
                    u.a().b("Player", "Video Played");
                    Util.a(PlayerRadioFragmentV2.this.mContext, playerTrack.b().getYoutubeId(), "", playerTrack.b(), 0);
                }
            });
            this.G.setClickable(true);
            this.J.setClickable(true);
            an.a().a("click", "ac", "", "radioplayer", "", "video", "", "active");
        }
    }

    private void n() {
        u.a().b("Player", "Lyrics");
        if (!Util.j(this.mContext)) {
            aj.a().a(this.mContext, this.mContext.getString(R.string.error_msg_no_connection));
        } else if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else {
            PlayerTrack i = PlayerManager.a(this.mContext).i();
            if (i != null && i.b() != null) {
                String lyricsUrl = i.b().getLyricsUrl();
                if (!TextUtils.isEmpty(lyricsUrl)) {
                    Intent intent = new Intent(this.mContext, WebViewActivity.class);
                    intent.putExtra("EXTRA_WEBVIEW_URL", lyricsUrl);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                    intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                    intent.putExtra("title", "Lyrics");
                    Util.a(this.mContext, "Fetching lyrics");
                    this.mContext.startActivity(intent);
                }
            }
        }
    }

    public void onDestroyView() {
        if (o.a("LISTENER_KEY_PLAYER_ACTIVITY") == this) {
            this.p.removeCallbacksAndMessages(null);
            o.b("LISTENER_KEY_PLAYER_ACTIVITY");
            o.d("LISTENER_KEY_PLAYER_ACTIVITY");
        }
        super.onDestroyView();
    }

    private void o() {
        try {
            GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
        }
        q();
        if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
            this.i.setImageDrawable(this.r);
        } else {
            this.i.setImageDrawable(this.q);
        }
        this.A.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                Util.d(PlayerRadioFragmentV2.this.V - ((long) PlayerRadioFragmentV2.this.W));
                u.a().b("Player", "Seekbar Moved");
                o.a(PlayerRadioFragmentV2.this.getContext(), PlayerRadioFragmentV2.this.A.getProgress());
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                PlayerRadioFragmentV2.this.V = (long) PlayerRadioFragmentV2.this.W;
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
                PlayerRadioFragmentV2.this.W = i;
            }
        });
    }

    private void p() {
        try {
            GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
        }
        o.a(getContext(), this.U);
        q();
        if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
            this.i.setImageDrawable(this.r);
        } else {
            this.i.setImageDrawable(this.q);
        }
    }

    private void q() {
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
                this.U = v;
                int i = u - v;
                this.A.setProgress(v);
                this.A.setMax(u);
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
                    r();
                }
                this.g.setText(format);
                if (!((format2.equalsIgnoreCase(" 0:00") && this.mPlayerManager.t() && this.mPlayerManager.v()) || !GaanaMusicService.t() || GaanaMusicService.s().m())) {
                    AnonymousClass6 anonymousClass6 = new Runnable() {
                        public void run() {
                            PlayerRadioFragmentV2.this.q();
                        }
                    };
                    this.p.removeCallbacksAndMessages(null);
                    this.p.postDelayed(anonymousClass6, 1000);
                }
            } else if (GaanaMusicService.t()) {
                c = GaanaMusicService.s().v();
                this.g.setText(String.format("%2d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) c) / 60), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) c) % 60)}));
                AnonymousClass7 anonymousClass7 = new Runnable() {
                    public void run() {
                        PlayerRadioFragmentV2.this.q();
                    }
                };
                this.p.removeCallbacksAndMessages(null);
                this.p.postDelayed(anonymousClass7, 1000);
            } else {
                if (this.p != null) {
                    this.p.removeCallbacksAndMessages(null);
                }
                c = 0;
                this.g.setText(String.format("%2d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) c) / 60), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) c) % 60)}));
            }
        }
    }

    private void r() {
        c = 0;
        this.U = 0;
        this.g.setText("0:00");
    }

    private void s() {
        DownloadManager.c().e(Integer.parseInt(getPlayingTrack().getBusinessObjId()));
        if (GaanaMusicService.s().m()) {
            this.j.setVisibility(0);
            this.i.setVisibility(4);
        } else if (GaanaMusicService.t()) {
            this.j.setVisibility(8);
            this.i.setVisibility(0);
            this.i.setImageDrawable(this.r);
        } else {
            this.j.setVisibility(8);
            this.i.setVisibility(0);
            this.i.setImageDrawable(this.q);
        }
    }

    private void t() {
        if (this.i != null) {
            this.i.setImageDrawable(this.q);
            this.i.setVisibility(0);
            this.j.setVisibility(8);
            this.mPlayerStates = PlayerStates.STOPPED;
        }
    }

    private void u() {
        o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.S);
        this.mCurrentTrack = this.mPlayerManager.a(PlaySequenceType.CURRENT);
        a(this.mCurrentTrack);
        if (!this.n) {
            r();
        }
        f();
        b(this.mPlayerManager.a(PlaySequenceType.CURRENT));
        c(this.mPlayerManager.a(PlaySequenceType.CURRENT));
        a(this.mPlayerManager.i().b());
        this.i.setImageDrawable(this.r);
        this.i.setVisibility(4);
        this.j.setVisibility(0);
        this.mPlayerStates = PlayerStates.LOADING;
        DownloadManager.c().e(Integer.parseInt(getPlayingTrack().getBusinessObjId()));
    }

    private void v() {
        this.i.setImageDrawable(this.q);
        this.i.setVisibility(0);
        this.j.setVisibility(8);
        this.mPlayerStates = PlayerStates.PAUSED;
    }

    private void w() {
        this.i.setImageDrawable(this.r);
        this.i.setVisibility(0);
        this.j.setVisibility(8);
        if (!GaanaMusicService.s().m()) {
            if (this.n) {
                p();
            } else {
                o();
            }
        }
        this.mPlayerStates = PlayerStates.PLAYING;
    }

    private void a(boolean z, boolean z2) {
        this.i.setImageDrawable(this.r);
        if (z2) {
            a(z, PlayerCommands.PLAY_PREVIOUS);
            return;
        }
        this.p.removeCallbacksAndMessages(null);
        r();
    }

    private void b(boolean z, boolean z2) {
        this.i.setImageDrawable(this.r);
        if (z2) {
            a(z, PlayerCommands.PLAY_NEXT);
        } else if (this.mPlayerManager.m() != PlayerType.GAANA_RADIO) {
            this.p.removeCallbacksAndMessages(null);
            r();
        } else if (this.mPlayerManager.f()) {
            if (!this.mPlayerManager.h()) {
                this.mPlayerManager.f(true);
            }
            this.p.removeCallbacksAndMessages(null);
            r();
        } else {
            ((BaseActivity) getActivity()).showProgressDialog();
        }
    }

    private void a(boolean z, PlayerCommands playerCommands) {
        if (AnonymousClass14.a[playerCommands.ordinal()] == 1) {
            if (!z) {
                this.mCurrentTrack = this.mPlayerManager.a(this.mPlayerManager.s());
                this.p.removeCallbacksAndMessages(null);
                r();
                this.i.setImageDrawable(this.q);
            } else if (GaanaMusicService.s().l() || GaanaMusicService.s().k()) {
                this.i.setImageDrawable(this.q);
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
        listView.setSelector(this.s);
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
                    textView.setTextColor(PlayerRadioFragmentV2.this.o.data);
                }
                return view;
            }
        });
        final BottomSheetDialog bottomSheetDialog2 = bottomSheetDialog;
        final String[] strArr3 = strArr2;
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                d a = d.a();
                if (PlayerRadioFragmentV2.this.mAppState.isAppInDataSaveMode()) {
                    ((BaseActivity) PlayerRadioFragmentV2.this.mContext).displayFeatureNotAvailableDataSaveModeDialog(i, -1);
                    bottomSheetDialog2.dismiss();
                    return;
                }
                aj a2;
                Context M;
                StringBuilder stringBuilder;
                if (i == 0) {
                    if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                        bottomSheetDialog2.dismiss();
                        return;
                    }
                    a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                    aj.a().a(PlayerRadioFragmentV2.this.mContext, PlayerRadioFragmentV2.this.mContext.getString(R.string.adjusting_sound_quality));
                    bottomSheetDialog2.dismiss();
                    PlayerRadioFragmentV2.this.refreshList();
                    PlayerRadioFragmentV2.this.n = true;
                    o.b(PlayerRadioFragmentV2.this.getContext(), 1);
                    u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                } else if (i == 1) {
                    if (!ap.a().s()) {
                        u.a().a("Mini Player", "Set Streaming Quality", "Trial HD (Gaana+ only)");
                        bottomSheetDialog2.dismiss();
                        Util.a(PlayerRadioFragmentV2.this.mContext, PlayerRadioFragmentV2.this.mContext.getResources().getString(R.string.subscribe_gaanaplus_hdq_msg), "HDQuality");
                    } else if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                        bottomSheetDialog2.dismiss();
                        return;
                    } else {
                        a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                        a2 = aj.a();
                        M = PlayerRadioFragmentV2.this.mContext;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(PlayerRadioFragmentV2.this.mContext.getString(R.string.changing_sound_quality));
                        stringBuilder.append(strArr[i]);
                        a2.a(M, stringBuilder.toString());
                        bottomSheetDialog2.dismiss();
                        PlayerRadioFragmentV2.this.refreshList();
                        PlayerRadioFragmentV2.this.n = true;
                        o.b(PlayerRadioFragmentV2.this.getContext(), 1);
                        u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                    }
                } else if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                    bottomSheetDialog2.dismiss();
                    return;
                } else {
                    a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                    a2 = aj.a();
                    M = PlayerRadioFragmentV2.this.mContext;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(PlayerRadioFragmentV2.this.mContext.getString(R.string.changing_sound_quality));
                    stringBuilder.append(strArr[i]);
                    a2.a(M, stringBuilder.toString());
                    bottomSheetDialog2.dismiss();
                    PlayerRadioFragmentV2.this.refreshList();
                    PlayerRadioFragmentV2.this.n = true;
                    o.b(PlayerRadioFragmentV2.this.getContext(), 1);
                    u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                }
                PlayerRadioFragmentV2.this.k();
            }
        });
        bottomSheetDialog.show();
    }

    public void refreshPlayerStatus() {
        if (this.mCurrentTrack != null && this.C != null) {
            this.C.changeDownlaodButtonIcon(getPlayingTrack(), (ImageView) this.e.findViewById(R.id.queue_panel_download_button));
        }
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
                af.a(this.mContext, null).a((int) R.id.downloadMenu, businessObject);
            } else {
                ((BaseActivity) this.mContext).hideProgressDialog();
                final BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
                if (!((currentFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) currentFragment).a() == 1)) {
                    Util.b(this.mContext, null, new as() {
                        public void onTrialSuccess() {
                            af.a(PlayerRadioFragmentV2.this.mContext, null).a((int) R.id.downloadMenu, businessObject);
                            currentFragment.showSnackbartoOpenMyMusic();
                            ((GaanaActivity) PlayerRadioFragmentV2.this.mContext).updateSideBar();
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
                PlayerRadioFragmentV2.this.refreshList();
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
        this.R = true;
        e();
    }

    public void c(ADSTATUS adstatus) {
        this.R = false;
    }
}
