package com.fragments;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbar.PlayerMaterialActionBar;
import com.constants.Constants;
import com.constants.Constants.ErrorType;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.library.controls.CrossFadeImageView;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlaySequenceType;
import com.managers.PlayerManager.PlayerType;
import com.managers.ad;
import com.managers.aj;
import com.managers.ap;
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
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;
import java.util.concurrent.TimeUnit;

public class PlayerRadioFragment extends BaseFragment implements OnClickListener {
    private static int b;
    n a = new n() {
        public void displayErrorToast(String str, int i) {
        }

        public void onPlayerRepeatReset(boolean z) {
        }

        public void onStreamingQualityChanged(int i) {
        }

        public void onPlayerPlay() {
            if (!PlayerRadioFragment.this.isActivityDestroyed()) {
                PlayerRadioFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragment.this.n();
                    }
                });
            }
        }

        public void onPlayerPause() {
            if (!PlayerRadioFragment.this.isActivityDestroyed()) {
                PlayerRadioFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragment.this.o();
                    }
                });
            }
        }

        public void onPlayerResume() {
            if (!PlayerRadioFragment.this.isActivityDestroyed()) {
                PlayerRadioFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragment.this.p();
                    }
                });
            }
        }

        public void onPlayerStop() {
            if (!PlayerRadioFragment.this.isActivityDestroyed()) {
                PlayerRadioFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragment.this.m();
                    }
                });
            }
        }

        public void onPlayPrevious(final boolean z, final boolean z2) {
            if (!PlayerRadioFragment.this.isActivityDestroyed()) {
                PlayerRadioFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragment.this.a(z, z2);
                    }
                });
            }
        }

        public void onPlayNext(final boolean z, final boolean z2) {
            if (!PlayerRadioFragment.this.isActivityDestroyed()) {
                PlayerRadioFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragment.this.b(z, z2);
                    }
                });
            }
        }

        public void displayErrorDialog(String str, ErrorType errorType) {
            if (!PlayerRadioFragment.this.isActivityDestroyed()) {
                PlayerRadioFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        PlayerRadioFragment.this.m();
                    }
                });
            }
        }
    };
    private PlayerMaterialActionBar c;
    private View d;
    private CrossFadeImageView e;
    private TextView f;
    private LinearLayout g;
    private ImageView h;
    private ProgressBar i;
    private ImageView j;
    private ImageView k;
    private TextView l;
    private Toolbar m;
    private boolean n;
    private TextView o;
    private TextView p;
    private TypedValue q;
    private final Handler r = new Handler();
    private Drawable s;
    private Drawable t;
    private Drawable u;
    private Drawable v;
    private int w;
    private m x = new m() {
        public void onAdEventUpdate(f fVar, AdEvent adEvent) {
        }

        public void onBufferingUpdate(f fVar, int i) {
        }

        public void onInfo(f fVar, int i, int i2) {
        }

        public void onPrepared(f fVar) {
            if (!PlayerRadioFragment.this.isActivityDestroyed()) {
                PlayerRadioFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (GaanaMusicService.s().isPlaying()) {
                            PlayerRadioFragment.this.mPlayerStates = PlayerStates.PLAYING;
                        }
                        if (PlayerRadioFragment.this.n) {
                            PlayerRadioFragment.this.i();
                            PlayerRadioFragment.this.l();
                            PlayerRadioFragment.this.n = false;
                            return;
                        }
                        PlayerRadioFragment.this.h();
                        PlayerRadioFragment.this.l();
                    }
                });
            }
        }

        public void onError(f fVar, int i, int i2) {
            if (!PlayerRadioFragment.this.isActivityDestroyed()) {
                if (i == -1000 || i == -1001) {
                    PlayerRadioFragment.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            PlayerRadioFragment.this.m();
                        }
                    });
                }
            }
        }

        public void onCompletion(f fVar) {
            PlayerRadioFragment.this.mPlayerStates = PlayerStates.STOPPED;
        }
    };
    private String y = "0";

    /* renamed from: com.fragments.PlayerRadioFragment$7 */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] a = new int[PlayerCommands.values().length];

        static {
            try {
                a[PlayerCommands.PLAY_NEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void onRadioTracksFetched(boolean z) {
    }

    public void on_deque() {
    }

    public void on_enque() {
    }

    public void refreshForFavorite() {
    }

    public void refreshPlayerStatus() {
    }

    public void updateCardAdapter(boolean z) {
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.d = layoutInflater.inflate(R.layout.fragment_player_radio, viewGroup, false);
        this.e = (CrossFadeImageView) this.d.findViewById(R.id.player_image);
        this.f = (TextView) this.d.findViewById(R.id.tvPlayerStartTimerRadio);
        this.g = (LinearLayout) this.d.findViewById(R.id.streamingListLayoutRadio);
        this.h = (ImageView) this.d.findViewById(R.id.playerButtonRadio);
        this.i = (ProgressBar) this.d.findViewById(R.id.progressBarPlayerRadio);
        this.j = (ImageView) this.d.findViewById(R.id.playerBtnNextRadio);
        this.k = (ImageView) this.d.findViewById(R.id.equalizerIconRadio);
        this.m = (Toolbar) this.d.findViewById(R.id.toolbar);
        this.l = (TextView) this.d.findViewById(R.id.qualityTextRadio);
        this.c = new PlayerMaterialActionBar(getContext());
        this.m.addView(this.c);
        int[] iArr = new int[]{R.attr.bottom_pause_button, R.attr.bottom_play_button, R.attr.selector_btn_global_bg_transparent, R.attr.miniplayer_play};
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.t = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(5, -1));
        this.s = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(6, -1));
        this.u = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(86, -1));
        this.v = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(31, -1));
        obtainStyledAttributes.recycle();
        this.q = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.song_quality_color, this.q, true);
        c();
        this.g.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.k.setOnClickListener(this);
        if (this.m != null) {
            Menu menu = this.m.getMenu();
            if (menu != null) {
                menu.findItem(R.id.menu_add_to_playlist).setVisible(false);
                menu.findItem(R.id.menu_option).setVisible(true);
            }
        }
        d();
        setGAScreenName("PlayerHomeScreen", "PlayerHomeScreen");
        return this.d;
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void c() {
        this.m.setContentInsetsAbsolute(0, 0);
        this.m.getMenu().clear();
        this.m.inflateMenu(R.menu.cast_menu_player);
        ((BaseActivity) this.mContext).initializeMediaRouterButton(this.m.getMenu(), R.id.media_route_menu_item);
        this.c.setToolbar(this.m);
    }

    private void d() {
        if (ad.a(this.mContext).p().booleanValue()) {
            this.j.setVisibility(8);
            this.g.setVisibility(8);
        } else if (ad.a(this.mContext).o().booleanValue()) {
            this.j.setVisibility(8);
            this.g.setVisibility(8);
        } else {
            this.j.setVisibility(0);
            this.g.setVisibility(0);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mCurrentTrack = PlayerManager.a(GaanaApplication.getContext()).a(PlaySequenceType.CURRENT);
        this.mRadioManager = ad.a(GaanaApplication.getContext());
        this.mPlayerManager = PlayerManager.a(GaanaApplication.getContext());
        e();
        b();
    }

    private void e() {
        int i = (ad.a(this.mContext).o().booleanValue() || GaanaApplication.getInstance().isAppInOfflineMode() || !Util.j(this.mContext)) ? true : 0;
        if (!(this.mCurrentTrack == null || this.mCurrentTrack.a(true) == null)) {
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(this.mCurrentTrack.a(true).getBusinessObjId()));
            if (this.mCurrentTrack.a(true).isLocalMedia() || ((ap.a().o() || DownloadManager.c().j(this.mCurrentTrack.a(true).getBusinessObjId()).booleanValue()) && e != null && e == DownloadStatus.DOWNLOADED)) {
                i = true;
            }
        }
        if (i != 0) {
            this.g.setVisibility(8);
        } else {
            this.g.setVisibility(0);
        }
        CharSequence charSequence = "";
        GaanaApplication gaanaApplication;
        switch (d.a().b("PREFERENCE_KEY_STREAMING_QUALITY", Constants.s(), false)) {
            case 10000:
                charSequence = this.mContext.getString(R.string.low);
                break;
            case 10001:
                gaanaApplication = this.mAppState;
                if (!GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                    charSequence = this.mContext.getString(R.string.medium);
                    break;
                } else {
                    charSequence = this.mContext.getString(R.string.med);
                    break;
                }
            case 10002:
                charSequence = this.mContext.getString(R.string.high);
                break;
            case 10003:
                gaanaApplication = this.mAppState;
                if (!GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                    charSequence = this.mContext.getString(R.string.high_defination);
                    break;
                } else {
                    charSequence = this.mContext.getString(R.string.hd);
                    break;
                }
            case 10004:
                charSequence = this.mContext.getString(R.string.auto);
                break;
        }
        this.l.setText(charSequence);
    }

    public void onPlayerStateChanged() {
        if (isAdded()) {
            d();
        }
    }

    public void refreshList() {
        if (isAdded()) {
            this.mCurrentTrack = PlayerManager.a(this.mContext).i();
            f();
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
        } else if (id != R.id.playerBtnNextRadio) {
            if (id == R.id.playerButtonRadio) {
                if (GaanaMusicService.t() || GaanaMusicService.s().m()) {
                    u.a().b("Player", "Pause");
                } else {
                    u.a().b("Player", "Play");
                }
                if (!ad.a(this.mContext).o().booleanValue()) {
                    a();
                } else if (GaanaMusicService.t()) {
                    o.d(getContext());
                    b = 0;
                } else {
                    a();
                }
            } else if (id == R.id.streamingListLayoutRadio) {
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
            this.h.setImageDrawable(this.t);
            o.b(this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
            return;
        }
        this.h.setImageDrawable(this.s);
        o.a(this.mContext);
        if (ad.a(this.mContext).o().booleanValue()) {
            ad.a(this.mContext).j();
        }
    }

    private void f() {
        if (this.mPlayerManager.m() == PlayerType.GAANA_RADIO && this.mPlayerManager.g()) {
            this.mPlayerManager.a(PlayerType.GAANA_RADIO, getContext());
        } else if (this.mPlayerManager.m() != PlayerType.GAANA_RADIO) {
            this.mPlayerManager.f(false);
        }
    }

    public void b() {
        this.mContext = getContext();
        if (this.mContext != null) {
            f();
            o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.a);
            o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.x);
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
                    h();
                } else if (GaanaMusicService.s().l() && !GaanaMusicService.s().m() && !PlayerStatus.a(getContext()).e()) {
                    a(this.mCurrentTrack);
                    h();
                } else if (GaanaMusicService.s().m()) {
                    a(this.mCurrentTrack);
                    if (GaanaMusicService.s().l()) {
                        this.h.setImageDrawable(this.t);
                    } else {
                        this.h.setImageDrawable(this.t);
                    }
                } else if (PlayerStatus.a(getContext()).e()) {
                    a(this.mCurrentTrack);
                    k();
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
        this.o = (TextView) this.c.findViewById(R.id.trackText);
        this.p = (TextView) this.c.findViewById(R.id.albumText);
        this.o.setText(getPlayingTrack().getName());
        this.p.setText(trim);
        if (getPlayingTrack().isLocalMedia()) {
            this.e.bindImageForLocalMedia(getPlayingTrack().getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
            Toolbar toolbar = (Toolbar) this.d.findViewById(R.id.toolbar);
            if (toolbar != null) {
                Menu menu = toolbar.getMenu();
                if (menu != null) {
                    menu.findItem(R.id.menu_add_to_playlist).setVisible(false);
                }
            }
        } else {
            g();
        }
        e();
    }

    private void g() {
        try {
            this.e.bindImage(getPlayingTrack().getArtworkLarge(), ScaleType.CENTER_CROP);
        } catch (OutOfMemoryError unused) {
            this.e.bindImage(getPlayingTrack().getArtwork(), ScaleType.CENTER_CROP);
        }
    }

    public void onDestroyView() {
        if (o.a("LISTENER_KEY_PLAYER_ACTIVITY") == this) {
            this.r.removeCallbacksAndMessages(null);
            o.b("LISTENER_KEY_PLAYER_ACTIVITY");
            o.d("LISTENER_KEY_PLAYER_ACTIVITY");
        }
        super.onDestroyView();
    }

    private void h() {
        try {
            GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
        }
        j();
        if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
            this.h.setImageDrawable(this.t);
        } else {
            this.h.setImageDrawable(this.v);
        }
    }

    private void i() {
        try {
            GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
        }
        o.a(getContext(), this.w);
        j();
        if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
            this.h.setImageDrawable(this.t);
        } else {
            this.h.setImageDrawable(this.s);
        }
    }

    private void j() {
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
                this.w = v;
                u -= v;
                r12 = new Object[2];
                long j = (long) v;
                r12[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) / 60);
                r12[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % 60);
                CharSequence format = String.format("%2d:%02d", r12);
                r6 = new Object[2];
                long j2 = (long) u;
                r6[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j2) / 60);
                r6[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j2) % 60);
                String format2 = String.format("%2d:%02d", r6);
                if (v > 15958442) {
                    format = "0:00";
                    k();
                }
                this.f.setText(format);
                if (!((format2.equalsIgnoreCase(" 0:00") && this.mPlayerManager.t() && this.mPlayerManager.v()) || !GaanaMusicService.t() || GaanaMusicService.s().m())) {
                    AnonymousClass3 anonymousClass3 = new Runnable() {
                        public void run() {
                            PlayerRadioFragment.this.j();
                        }
                    };
                    this.r.removeCallbacksAndMessages(null);
                    this.r.postDelayed(anonymousClass3, 1000);
                }
            } else if (GaanaMusicService.t()) {
                b = GaanaMusicService.s().v();
                this.f.setText(String.format("%2d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) b) / 60), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) b) % 60)}));
                AnonymousClass4 anonymousClass4 = new Runnable() {
                    public void run() {
                        PlayerRadioFragment.this.j();
                    }
                };
                this.r.removeCallbacksAndMessages(null);
                this.r.postDelayed(anonymousClass4, 1000);
            } else {
                if (this.r != null) {
                    this.r.removeCallbacksAndMessages(null);
                }
                b = 0;
                this.f.setText(String.format("%2d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) b) / 60), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) b) % 60)}));
            }
        }
    }

    private void k() {
        b = 0;
        this.w = 0;
        this.f.setText("0:00");
    }

    private void l() {
        DownloadManager.c().e(Integer.parseInt(getPlayingTrack().getBusinessObjId()));
        if (GaanaMusicService.s().m()) {
            this.i.setVisibility(0);
            this.h.setVisibility(8);
        } else if (GaanaMusicService.t()) {
            this.i.setVisibility(8);
            this.h.setVisibility(0);
            this.h.setImageDrawable(this.t);
        } else {
            this.i.setVisibility(8);
            this.h.setVisibility(0);
            this.h.setImageDrawable(this.s);
        }
    }

    private void m() {
        if (this.h != null) {
            this.h.setImageDrawable(this.s);
            this.h.setVisibility(0);
            this.i.setVisibility(8);
            this.mPlayerStates = PlayerStates.STOPPED;
        }
    }

    private void n() {
        o.a("LISTENER_KEY_PLAYER_ACTIVITY", this.x);
        this.mCurrentTrack = this.mPlayerManager.a(PlaySequenceType.CURRENT);
        a(this.mCurrentTrack);
        if (!this.n) {
            k();
        }
        this.h.setImageDrawable(this.t);
        this.h.setVisibility(8);
        this.i.setVisibility(0);
        this.mPlayerStates = PlayerStates.LOADING;
        DownloadManager.c().e(Integer.parseInt(getPlayingTrack().getBusinessObjId()));
    }

    private void o() {
        this.h.setImageDrawable(this.s);
        this.h.setVisibility(0);
        this.i.setVisibility(8);
        this.mPlayerStates = PlayerStates.PAUSED;
    }

    private void p() {
        this.h.setImageDrawable(this.t);
        this.h.setVisibility(0);
        this.i.setVisibility(8);
        if (!GaanaMusicService.s().m()) {
            if (this.n) {
                i();
            } else {
                h();
            }
        }
        this.mPlayerStates = PlayerStates.PLAYING;
    }

    private void a(boolean z, boolean z2) {
        this.h.setImageDrawable(this.t);
        if (z2) {
            a(z, PlayerCommands.PLAY_PREVIOUS);
            return;
        }
        this.r.removeCallbacksAndMessages(null);
        k();
    }

    private void b(boolean z, boolean z2) {
        this.h.setImageDrawable(this.t);
        if (z2) {
            a(z, PlayerCommands.PLAY_NEXT);
        } else if (this.mPlayerManager.m() != PlayerType.GAANA_RADIO) {
            this.r.removeCallbacksAndMessages(null);
            k();
        } else if (this.mPlayerManager.f()) {
            if (!this.mPlayerManager.h()) {
                this.mPlayerManager.f(true);
            }
            this.r.removeCallbacksAndMessages(null);
            k();
        } else {
            ((BaseActivity) getActivity()).showProgressDialog();
        }
    }

    private void a(boolean z, PlayerCommands playerCommands) {
        if (AnonymousClass7.a[playerCommands.ordinal()] == 1) {
            if (!z) {
                this.mCurrentTrack = this.mPlayerManager.a(this.mPlayerManager.s());
                this.r.removeCallbacksAndMessages(null);
                k();
                this.h.setImageDrawable(this.s);
            } else if (GaanaMusicService.s().l() || GaanaMusicService.s().k()) {
                this.h.setImageDrawable(this.s);
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
                    textView.setTextColor(PlayerRadioFragment.this.q.data);
                }
                return view;
            }
        });
        final BottomSheetDialog bottomSheetDialog2 = bottomSheetDialog;
        final String[] strArr3 = strArr2;
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                d a = d.a();
                if (PlayerRadioFragment.this.mAppState.isAppInDataSaveMode()) {
                    ((BaseActivity) PlayerRadioFragment.this.mContext).displayFeatureNotAvailableDataSaveModeDialog(i, -1);
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
                    aj.a().a(PlayerRadioFragment.this.mContext, PlayerRadioFragment.this.mContext.getString(R.string.adjusting_sound_quality));
                    bottomSheetDialog2.dismiss();
                    PlayerRadioFragment.this.refreshList();
                    PlayerRadioFragment.this.n = true;
                    o.b(PlayerRadioFragment.this.getContext(), 1);
                    u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                } else if (i == 1) {
                    if (!ap.a().s()) {
                        u.a().a("Mini Player", "Set Streaming Quality", "Trial HD (Gaana+ only)");
                        bottomSheetDialog2.dismiss();
                        Util.a(PlayerRadioFragment.this.mContext, PlayerRadioFragment.this.mContext.getResources().getString(R.string.subscribe_gaanaplus_hdq_msg), "HDQuality");
                    } else if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                        bottomSheetDialog2.dismiss();
                    } else {
                        a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                        a2 = aj.a();
                        x = PlayerRadioFragment.this.mContext;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(PlayerRadioFragment.this.mContext.getString(R.string.changing_sound_quality));
                        stringBuilder.append(strArr[i]);
                        a2.a(x, stringBuilder.toString());
                        bottomSheetDialog2.dismiss();
                        PlayerRadioFragment.this.refreshList();
                        PlayerRadioFragment.this.n = true;
                        o.b(PlayerRadioFragment.this.getContext(), 1);
                        u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                    }
                } else if (a.b("PREFERENCE_KEY_STREAMING_QUALITY", 0, false) == iArr[i]) {
                    bottomSheetDialog2.dismiss();
                } else {
                    a.a("PREFERENCE_KEY_STREAMING_QUALITY", iArr[i], false);
                    a2 = aj.a();
                    x = PlayerRadioFragment.this.mContext;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(PlayerRadioFragment.this.mContext.getString(R.string.changing_sound_quality));
                    stringBuilder.append(strArr[i]);
                    a2.a(x, stringBuilder.toString());
                    bottomSheetDialog2.dismiss();
                    PlayerRadioFragment.this.refreshList();
                    PlayerRadioFragment.this.n = true;
                    o.b(PlayerRadioFragment.this.getContext(), 1);
                    u.a().a("Mini Player", "Set Streaming Quality", strArr3[i]);
                }
            }
        });
        bottomSheetDialog.show();
    }
}
