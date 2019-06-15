package com.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.constants.Constants.ErrorType;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukePartyFragment;
import com.gaana.juke.JukeSessionManager;
import com.gaana.models.Tracks.Track;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.GaanaSearchManager;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlaySequenceType;
import com.managers.PlayerManager.PlayerType;
import com.managers.ad;
import com.managers.an;
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

public class MiniPlayerFragment extends Fragment implements OnClickListener, com.managers.PlayerManager.a, com.managers.ad.a, aj, ak {
    private a A;
    private boolean B = false;
    private av C;
    private int D = 0;
    private ImageView E;
    private ImageView F;
    private SimpleExoPlayerView G = null;
    private final GestureDetector H = new GestureDetector(new OnGestureListener() {
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
            ((GaanaActivity) MiniPlayerFragment.this.j).launchExpandedPlayer();
            return true;
        }
    });
    private final OnTouchListener I = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return MiniPlayerFragment.this.H.onTouchEvent(motionEvent);
        }
    };
    private ServiceConnection J = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MiniPlayerFragment.this.x = true;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            MiniPlayerFragment.this.x = false;
        }
    };
    private m K = new m() {
        public void onAdEventUpdate(f fVar, AdEvent adEvent) {
        }

        public void onInfo(f fVar, int i, int i2) {
        }

        public void onPrepared(f fVar) {
            if (MiniPlayerFragment.this.isAdded()) {
                MiniPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (GaanaMusicService.s().isPlaying()) {
                            MiniPlayerFragment.this.w = PlayerStates.PLAYING;
                            MiniPlayerFragment.this.d();
                            ((GaanaActivity) MiniPlayerFragment.this.j).showFreedomUserEngagementPopup(((GaanaActivity) MiniPlayerFragment.this.j).getmFreedomPlanUserEngagementData());
                        }
                        if (MiniPlayerFragment.this.v) {
                            MiniPlayerFragment.this.A();
                            MiniPlayerFragment.this.E();
                            MiniPlayerFragment.this.v = false;
                            return;
                        }
                        MiniPlayerFragment.this.w();
                        MiniPlayerFragment.this.E();
                        try {
                            if (PlayerManager.a(GaanaApplication.getContext()).i() != null) {
                                Track b = PlayerManager.a(GaanaApplication.getContext()).i().b();
                                MiniPlayerFragment.this.s = "song/";
                                if (PlayerManager.a(GaanaApplication.getContext()).i().e() == SOURCE_TYPE.ALBUM.ordinal()) {
                                    MiniPlayerFragment.this.s = "song/";
                                } else if (PlayerManager.a(GaanaApplication.getContext()).i().e() == SOURCE_TYPE.PLAYLIST.ordinal()) {
                                    MiniPlayerFragment.this.s = "song/";
                                } else if (PlayerManager.a(GaanaApplication.getContext()).i().e() == SOURCE_TYPE.RADIO_MIRCHI.ordinal()) {
                                    MiniPlayerFragment.this.s = "radio/";
                                } else if (PlayerManager.a(GaanaApplication.getContext()).i().e() == SOURCE_TYPE.GAANA_RADIO.ordinal()) {
                                    MiniPlayerFragment.this.s = "gaanaradio/";
                                }
                                MiniPlayerFragment.this.a(b);
                            }
                        } catch (Exception unused) {
                        }
                    }
                });
            }
        }

        public void onError(f fVar, int i, int i2) {
            if (MiniPlayerFragment.this.isAdded()) {
                if (i == -1000 || i == -1001) {
                    MiniPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            MiniPlayerFragment.this.z();
                        }
                    });
                }
            }
        }

        public void onCompletion(f fVar) {
            MiniPlayerFragment.this.w = PlayerStates.STOPPED;
            MiniPlayerFragment.this.g();
        }

        public void onBufferingUpdate(final f fVar, final int i) {
            if (MiniPlayerFragment.this.isAdded()) {
                MiniPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (PlayerStatus.a(MiniPlayerFragment.this.getContext()).c()) {
                            MiniPlayerFragment.this.a(fVar, i);
                        }
                    }
                });
            }
        }
    };
    private boolean L = false;
    private PlayerTrack M = null;
    private int N = -1;
    private TextView O;
    private TextView P;
    protected PlayerTrack a;
    int[] b = new int[]{R.attr.bottom_pause_button, R.attr.miniplayer_play};
    n c = new n() {
        public void onPlayerRepeatReset(boolean z) {
        }

        public void onStreamingQualityChanged(int i) {
        }

        public void onPlayerPlay() {
            if (MiniPlayerFragment.this.isAdded()) {
                MiniPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragment.this.D();
                    }
                });
            }
        }

        public void onPlayerPause() {
            if (MiniPlayerFragment.this.isAdded()) {
                MiniPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragment.this.x();
                    }
                });
            }
        }

        public void onPlayerResume() {
            if (MiniPlayerFragment.this.isAdded()) {
                MiniPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragment.this.y();
                        if ((MiniPlayerFragment.this.j instanceof GaanaActivity) && ((GaanaActivity) MiniPlayerFragment.this.j).getCurrentFragment() != null && (((GaanaActivity) MiniPlayerFragment.this.j).getCurrentFragment() instanceof GaanaVideoPlayerFragment)) {
                            ((GaanaActivity) MiniPlayerFragment.this.j).popBackStackImmediate();
                        }
                    }
                });
            }
        }

        public void onPlayerStop() {
            if (MiniPlayerFragment.this.isAdded()) {
                MiniPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragment.this.z();
                    }
                });
            }
        }

        public void onPlayPrevious(final boolean z, final boolean z2) {
            if (MiniPlayerFragment.this.isAdded()) {
                MiniPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragment.this.a(z, z2);
                    }
                });
            }
        }

        public void onPlayNext(final boolean z, final boolean z2) {
            if (MiniPlayerFragment.this.isAdded()) {
                MiniPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragment.this.b(z, z2);
                    }
                });
            }
        }

        public void displayErrorDialog(final String str, ErrorType errorType) {
            if (MiniPlayerFragment.this.isAdded()) {
                MiniPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        MiniPlayerFragment.this.z();
                    }
                });
                if (errorType == ErrorType.NETWORK_ERROR) {
                    MiniPlayerFragment.this.a(str);
                } else if (errorType == ErrorType.OTHER) {
                    MiniPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            if (ad.a(MiniPlayerFragment.this.j).o().booleanValue()) {
                                ap.a().a(MiniPlayerFragment.this.getContext(), MiniPlayerFragment.this.j.getString(R.string.unable_to_start_radio));
                            } else {
                                ap.a().a(MiniPlayerFragment.this.getContext(), str);
                            }
                        }
                    });
                } else if (errorType == ErrorType.TEMPORARY_NETWORK_ERROR) {
                    displayErrorToast(str, 1);
                }
            }
        }

        public void displayErrorToast(final String str, int i) {
            if (MiniPlayerFragment.this.isAdded()) {
                MiniPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        com.managers.aj.a().a(MiniPlayerFragment.this.getContext(), str);
                    }
                });
            }
        }
    };
    boolean d = false;
    boolean e = false;
    boolean f = false;
    boolean g = false;
    boolean h = false;
    OnPageChangeListener i = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
            MiniPlayerFragment.this.L = true;
        }

        public void onPageSelected(int i) {
            PlayerTrack i2;
            if (MiniPlayerFragment.this.L) {
                if (MiniPlayerFragment.this.N < i) {
                    if (MiniPlayerFragment.this.b(i)) {
                        u.a().a("Mini Player", "Swipe", "Right");
                        an.a().a("swipe", "rt", "", "miniplayer", "", "", "", "");
                    }
                } else if (MiniPlayerFragment.this.a(i)) {
                    u.a().a("Mini Player", "Swipe", "Left");
                    an.a().a("swipe", "lt", "", "miniplayer", "", "", "", "");
                }
            }
            MiniPlayerFragment.this.L = false;
            MiniPlayerFragment.this.N = i;
            Track track = null;
            if (PlayerManager.a(GaanaApplication.getContext()).m() != PlayerType.GAANA) {
                i2 = PlayerManager.a(GaanaApplication.getContext()).i();
                if (i2 != null) {
                    track = i2.b();
                }
            } else if (i < MiniPlayerFragment.this.y.size()) {
                i2 = (PlayerTrack) MiniPlayerFragment.this.y.get(i);
                if (i2 != null) {
                    track = i2.b();
                }
            } else {
                i2 = null;
            }
            MiniPlayerFragment.this.M = i2;
            if (track != null) {
                MiniPlayerFragment.this.C();
            }
        }
    };
    private Context j;
    private GaanaApplication k;
    private GoogleApiClient l;
    private Track m;
    private SeekBar n;
    private Drawable o;
    private Drawable p;
    private ImageView q;
    private ProgressBar r;
    private String s;
    private String t;
    private final Handler u = new Handler();
    private boolean v;
    private PlayerStates w = PlayerStates.INVALID;
    private boolean x = false;
    private List<PlayerTrack> y = new ArrayList();
    private ViewPager z;

    class a extends PagerAdapter {
        private LayoutInflater b;
        private SparseArray<View> c = new SparseArray();

        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }

        a(Context context) {
            this.b = LayoutInflater.from(context);
        }

        public int getCount() {
            if (MiniPlayerFragment.this.y == null || PlayerManager.a(GaanaApplication.getContext()).m() != PlayerType.GAANA) {
                return MiniPlayerFragment.this.y != null ? 1 : 0;
            } else {
                return MiniPlayerFragment.this.y.size();
            }
        }

        public int getItemPosition(@NonNull Object obj) {
            View view = (View) obj;
            return (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO || view == null || view.getTag() == null) ? -2 : -1;
        }

        @NonNull
        public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
            int i2;
            View inflate = this.b.inflate(R.layout.item_mini_player, viewGroup, false);
            inflate.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ((GaanaActivity) MiniPlayerFragment.this.j).launchExpandedPlayer();
                    an.a().a("click", "ac", "", "miniplayer", "", "tap", "", "");
                }
            });
            inflate.setOnTouchListener(MiniPlayerFragment.this.I);
            if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA) {
                i2 = i;
            } else {
                i2 = PlayerManager.a(GaanaApplication.getContext()).s();
            }
            if (i2 >= 0 && i2 < MiniPlayerFragment.this.y.size()) {
                i = i2;
            }
            PlayerTrack playerTrack = (PlayerTrack) MiniPlayerFragment.this.y.get(i);
            Track a = ((PlayerTrack) MiniPlayerFragment.this.y.get(i)).a(true);
            if (a != null) {
                MiniPlayerFragment.this.P = (TextView) inflate.findViewById(R.id.player_bottom_main_text_bottom);
                MiniPlayerFragment.this.P.setTypeface(MiniPlayerFragment.this.P.getTypeface(), 1);
                MiniPlayerFragment.this.O = (TextView) inflate.findViewById(R.id.player_bottom_secondary_text_bottom);
                inflate.findViewById(R.id.img_artwork).setVisibility(0);
                inflate.findViewById(R.id.vertical_video_view).setVisibility(8);
                ((CrossFadeImageView) inflate.findViewById(R.id.img_artwork)).bindImage(Util.d(MiniPlayerFragment.this.j, a.getArtwork()));
                MiniPlayerFragment.this.P.setText(a.getTrackTitle());
                CharSequence trim = MiniPlayerFragment.this.a(a.getAlbumTitle(), a.getArtistNames()).trim();
                if (Constants.aG) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(MiniPlayerFragment.this.j.getString(R.string.CASTING_TO));
                    stringBuilder.append(Constants.aH);
                    trim = stringBuilder.toString();
                } else if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO && !TextUtils.isEmpty(ad.a(GaanaApplication.getContext()).l())) {
                    trim = ad.a(GaanaApplication.getContext()).o().booleanValue() ? ad.a(GaanaApplication.getContext()).l() : MiniPlayerFragment.this.a(MiniPlayerFragment.this.a.b().getAlbumTitle(), MiniPlayerFragment.this.a.b().getArtistNames());
                }
                MiniPlayerFragment.this.O.setText(trim);
                inflate.setTag(playerTrack);
                viewGroup.addView(inflate);
                this.c.append(i, inflate);
            }
            return inflate;
        }

        public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
            viewGroup.removeView((View) obj);
            this.c.remove(i);
        }

        public void notifyDataSetChanged() {
            int i = 0;
            View view;
            if (!Constants.cY && PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA) {
                while (i < MiniPlayerFragment.this.y.size()) {
                    view = (View) this.c.get(i);
                    if (view != null) {
                        PlayerTrack playerTrack = (PlayerTrack) view.getTag();
                        PlayerTrack i2 = PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA ? (PlayerTrack) MiniPlayerFragment.this.y.get(i) : PlayerManager.a(GaanaApplication.getContext()).i();
                        if (i == 0 || playerTrack == null || i2 == null || !playerTrack.h().equals(i2.h())) {
                            view.setTag(null);
                        }
                    }
                    i++;
                }
            } else if (Constants.cY) {
                view = (View) this.c.get(0);
                if (view != null) {
                    view.setTag(null);
                }
            }
            super.notifyDataSetChanged();
        }
    }

    public void p() {
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.j = context;
        this.k = GaanaApplication.getInstance();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_mini_player, viewGroup, false);
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
                    if (MiniPlayerFragment.this.E != null) {
                        MiniPlayerFragment.this.E.setVisibility(0);
                        MiniPlayerFragment.this.E.setImageBitmap(bitmap);
                    }
                }
            });
        }
    }

    public void b() {
        if (this.E != null) {
            this.E.setVisibility(8);
        }
    }

    private void a(View view) {
        if (q()) {
            this.E = (ImageView) view.findViewById(R.id.miniplayer_background);
            a();
            F();
            this.l = new Builder(GaanaApplication.getContext()).addApi(AppIndex.APP_INDEX_API).build();
            TypedArray obtainStyledAttributes = this.j.obtainStyledAttributes(R.styleable.VectorDrawables);
            this.o = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(30, -1));
            this.p = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(31, -1));
            obtainStyledAttributes.recycle();
            this.n = (SeekBar) view.findViewById(R.id.seekBarMiniPLayer);
            this.r = (ProgressBar) view.findViewById(R.id.progressBarMiniPlayer);
            this.q = (ImageView) view.findViewById(R.id.player_bottom_button);
            this.z = (ViewPager) view.findViewById(R.id.pager_mini_player);
            this.F = (ImageView) view.findViewById(R.id.img_juke);
            this.F.setOnClickListener(this);
            if (this.y == null) {
                this.y = new ArrayList();
            }
            this.y.clear();
            this.y.addAll(PlayerManager.a(GaanaApplication.getContext()).n());
            this.A = new a(this.j);
            this.z.setAdapter(this.A);
            this.z.addOnPageChangeListener(this.i);
            this.n.setThumb(new ColorDrawable(getResources().getColor(17170445)));
            this.n.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
            ((GaanaActivity) this.j).findViewById(R.id.bottom_shadow).setVisibility(0);
            this.n.setPadding(0, 0, 0, 0);
            this.n.setFocusable(false);
            this.q.setOnClickListener(this);
            this.a = PlayerManager.a(GaanaApplication.getContext()).a(PlaySequenceType.CURRENT);
            return;
        }
        o();
    }

    public void onStart() {
        super.onStart();
        getActivity().bindService(new Intent(getActivity(), GaanaMusicService.class), this.J, 1);
    }

    public void onStop() {
        if (this.x) {
            getActivity().unbindService(this.J);
            this.x = false;
            if (GaanaMusicService.s().k()) {
                o.d(getActivity());
            }
        }
        super.onStop();
    }

    private boolean q() {
        if (PlayerManager.a(this.j).n() == null) {
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
            r();
            PlayerManager.a(GaanaApplication.getContext()).a(PlayerType.GAANA, this.j);
            PlayerStatus.a(this.j, PlayerStates.STOPPED);
            o.d(this.j);
            PlayerManager.a = false;
            return true;
        }
        PlayerManager.a(getContext()).a(PlayerManager.a(getContext()).a(-1));
        GaanaApplication.getInstance().setPlayerStatus(true);
        return true;
    }

    private void r() {
        d a = d.a();
        if (a.b("PREFERENCE_KEY_SHUFFLE_STATUS", false, true)) {
            ArrayList f = com.managers.o.a().f();
            if (f == null || f.size() <= 0) {
                a.a("PREFERENCE_KEY_SHUFFLE_STATUS", false, true);
            } else {
                PlayerManager.a(this.j).a(f);
            }
        }
        int b = a.b("PREFERENCE_KEY_REPEAT_STATUS", 2, true);
        if (b == 1) {
            PlayerManager.a(this.j).b(true);
        } else if (b == 2) {
            PlayerManager.a(this.j).c(true);
        }
    }

    public void c() {
        if (this.C != null) {
            this.C.refreshPlayerStatus();
        }
    }

    private void a(PlayerTrack playerTrack) {
        ((GaanaActivity) getContext()).hideFakePlayer();
        this.k.setPlayerStatus(true);
        if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO) {
            a(PlayerManager.a(GaanaApplication.getContext()).n());
            ad a = ad.a(this.j);
            if (TextUtils.isEmpty(a.c()) || TextUtils.isEmpty(a.d()) || !a.o().booleanValue()) {
                ad.a(this.j).f();
                n();
            } else {
                ad.a(this.j).a(Long.parseLong(a.d()));
            }
        } else {
            int s = PlayerManager.a(GaanaApplication.getContext()).s();
            if (s > -1 && s < this.y.size()) {
                if (s < this.z.getCurrentItem()) {
                    this.e = true;
                } else if (s > this.z.getCurrentItem()) {
                    this.g = true;
                }
                this.M = (PlayerTrack) this.y.get(s);
                this.z.setCurrentItem(s);
            }
        }
        s();
        if (this.a != null) {
            Util.a(this.j, "APP_WIDGET_UPDATE_ACTION", this.a.b());
        }
    }

    private void s() {
        if (this.F.getVisibility() == (!Constants.cY ? 4 : 0)) {
            return;
        }
        if (Constants.cY) {
            this.F.getLayoutParams().width = this.j.getResources().getDimensionPixelSize(R.dimen.player_bottom_control_height);
            this.F.setVisibility(0);
            this.F.requestLayout();
            this.F.setOnClickListener(this);
            return;
        }
        this.F.getLayoutParams().width = 1;
        this.F.setVisibility(4);
        this.F.requestLayout();
    }

    public void onClick(View view) {
        if (view.getId() != R.id.img_juke) {
            if (this.a == null) {
                this.a = PlayerManager.a(this.j).i();
            }
            if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO || (this.M != null && this.a.a(true).getBusinessObjId().equals(this.M.a(true).getBusinessObjId()))) {
                if (GaanaMusicService.t() || GaanaMusicService.s().m()) {
                    ((BaseActivity) this.j).sendGAEvent("Mini Player", "Pause Click", "Mini Player - Pause Click");
                    an.a().a("click", "ac", "", "miniplayer", "", "pause", "", "");
                } else {
                    ((BaseActivity) this.j).sendGAEvent("Mini Player", "Play Click", "Mini Player - Play Click");
                    an.a().a("click", "ac", "", "miniplayer", "", "play", "", "");
                }
                this.a.d(true);
                t();
            } else if (this.M != null) {
                if (Constants.aa) {
                    u.a().a("Shuffle Product", "Gaana+ popup", "Mini Player Tap");
                    Util.a(this.j, BLOCK_ACTION.SKIP);
                    return;
                }
                GaanaLogger.a().a(this.j, true, false);
                an.a().a("click", "ac", "", "miniplayer", "", "play", "", "");
                ((BaseActivity) this.j).sendGAEvent("Mini Player", "Play Click", "Mini Player - Play Click");
                PlayerManager.a(this.j).c();
                this.M.d(true);
                PlayerManager.a(this.j).a(null, this.M, this.N);
                PlayerManager.a(this.j).a(PlayerType.GAANA, this.j);
                f();
            } else if (this.a != null) {
                if (GaanaMusicService.t() || GaanaMusicService.s().m()) {
                    ((BaseActivity) this.j).sendGAEvent("Mini Player", "Pause Click", "Mini Player - Pause Click");
                    an.a().a("click", "ac", "", "miniplayer", "", "pause", "", "");
                } else {
                    ((BaseActivity) this.j).sendGAEvent("Mini Player", "Play Click", "Mini Player - Play Click");
                    an.a().a("click", "ac", "", "miniplayer", "", "play", "", "");
                }
                this.a.d(true);
                t();
            }
        } else if (!(((GaanaActivity) this.j).isJukeSessionFragment() || JukeSessionManager.getInstance().getJukeSessionPlaylist() == null)) {
            ((GaanaActivity) this.j).displayFragment(JukePartyFragment.newInstance(JukeSessionManager.getInstance().getJukeSessionPlaylist(), -1, "", false));
        }
    }

    private void t() {
        d();
        if (!ad.a(this.j).o().booleanValue()) {
            e();
        } else if (GaanaMusicService.t()) {
            o.d(getContext());
        } else {
            e();
        }
        String h = ad.a(this.j).h();
        if (h.equals("")) {
            n();
        } else {
            b(h);
        }
        o.g(GaanaApplication.getContext());
    }

    public void d() {
        if (this.j instanceof BaseActivity) {
            ((BaseActivity) this.j).refreshListView();
        }
    }

    public void e() {
        if (GaanaMusicService.t() || GaanaMusicService.s().m() || GaanaMusicService.s().l()) {
            this.D = 1;
            C();
            o.b(this.j, PauseReasons.MEDIA_BUTTON_TAP);
            return;
        }
        this.D = 0;
        C();
        o.a(this.j);
        if (ad.a(this.j).o().booleanValue()) {
            ad.a(this.j).j();
        }
    }

    public void f() {
        u();
        o.a("listener_mini_frag", this.c);
        o.a("listener_mini_frag", this.K);
        this.k.setPlayerStatus(true);
        this.a = PlayerManager.a(GaanaApplication.getContext()).a(PlaySequenceType.CURRENT);
        if (this.a == null) {
            o();
            return;
        }
        a(PlayerManager.a(GaanaApplication.getContext()).n());
        if (PlayerManager.a(GaanaApplication.getContext()).t() && PlayerManager.a(GaanaApplication.getContext()).s() != -1) {
            this.a = PlayerManager.a(GaanaApplication.getContext()).a(PlayerManager.a(GaanaApplication.getContext()).s());
            a(this.a);
        }
        b(PlayerManager.a(GaanaApplication.getContext()).m());
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
                this.a = PlayerManager.a(this.j).i();
            }
            if (PlayerStatus.a(getContext()).c()) {
                this.w = PlayerStates.PLAYING;
                a(this.a);
                w();
            } else if (GaanaMusicService.s().l() && !GaanaMusicService.s().m() && !PlayerStatus.a(getContext()).e()) {
                a(this.a);
                w();
            } else if (GaanaMusicService.s().m()) {
                a(this.a);
                this.D = 1;
                C();
            } else if (PlayerStatus.a(getContext()).e()) {
                a(this.a);
                v();
            }
        }
    }

    private void u() {
        if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO && PlayerManager.a(GaanaApplication.getContext()).g()) {
            PlayerManager.a(GaanaApplication.getContext()).a(PlayerType.GAANA_RADIO, getContext());
        } else if (PlayerManager.a(GaanaApplication.getContext()).m() != PlayerType.GAANA_RADIO) {
            PlayerManager.a(GaanaApplication.getContext()).f(false);
        }
    }

    private void v() {
        this.n.setProgress(0);
        this.n.setSecondaryProgress(0);
        this.n.setMax(0);
        if (!this.v) {
            this.d = true;
        }
    }

    private void w() {
        int u;
        try {
            u = GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
            u = 0;
        }
        this.n.setMax(u);
        this.n.setSecondaryProgress(0);
        B();
        if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
            this.D = 1;
            C();
            return;
        }
        this.D = 0;
        C();
    }

    public void a(PlayerType playerType) {
        b(playerType);
        a(PlayerManager.a(GaanaApplication.getContext()).n());
        if (this.C != null) {
            this.C.onPlayerStateChanged();
        }
    }

    public void b(PlayerType playerType) {
        if (playerType == PlayerType.GAANA) {
            this.n.setVisibility(0);
        } else {
            this.n.setVisibility(8);
        }
    }

    public void g() {
        int i = 0;
        int i2 = this.m != null ? 1 : 0;
        if (this.l != null) {
            i = 1;
        }
        if ((i2 & i) != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("android-app://com.gaana/gaanagoogle/");
            stringBuilder.append(this.t);
            stringBuilder.append(this.m.getSeokey());
            AppIndex.AppIndexApi.viewEnd(this.l, (GaanaActivity) this.j, Uri.parse(stringBuilder.toString()));
            this.l.disconnect();
        }
    }

    public void a(Track track) {
        if (this.l.isConnected()) {
            g();
        }
        this.l.connect();
        this.t = this.s;
        this.m = track;
        List arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("android-app://com.gaana/gaanagoogle/");
        stringBuilder.append(this.s);
        stringBuilder.append(track.getSeokey());
        Uri parse = Uri.parse(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("https://gaana.com/");
        stringBuilder.append(this.s);
        stringBuilder.append(track.getSeokey());
        AppIndex.AppIndexApi.view(this.l, (GaanaActivity) this.j, parse, track.getName(), Uri.parse(stringBuilder.toString()), arrayList);
    }

    /* Access modifiers changed, original: protected */
    public void a(final String str) {
        if (isAdded()) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    String string = MiniPlayerFragment.this.getResources().getString(R.string.DataError);
                    if ("JSON Data Null".equalsIgnoreCase(str)) {
                        string = MiniPlayerFragment.this.j.getString(R.string.failed_to_connect_to_server);
                    }
                    ((BaseActivity) MiniPlayerFragment.this.getActivity()).mDialog.a(MiniPlayerFragment.this.j.getString(R.string.app_name), string, Boolean.valueOf(false), new b() {
                        public void onCancelListner() {
                        }

                        public void onOkListner(String str) {
                        }
                    }, false);
                }
            });
        }
    }

    private void x() {
        this.D = 0;
        C();
        this.w = PlayerStates.PAUSED;
        d();
    }

    private void y() {
        this.D = 1;
        C();
        if (!GaanaMusicService.s().m()) {
            if (this.v) {
                A();
            } else {
                w();
            }
        }
        this.w = PlayerStates.PLAYING;
        d();
    }

    private void z() {
        this.D = 0;
        C();
        this.w = PlayerStates.STOPPED;
        d();
        s();
    }

    private void a(boolean z, boolean z2) {
        this.D = 1;
        C();
        if (z2) {
            a(z, PlayerCommands.PLAY_PREVIOUS);
            return;
        }
        this.u.removeCallbacksAndMessages(null);
        v();
    }

    private void b(boolean z, boolean z2) {
        this.D = 1;
        C();
        if (z2) {
            a(z, PlayerCommands.PLAY_NEXT);
            d();
        } else if (PlayerManager.a(GaanaApplication.getContext()).m() != PlayerType.GAANA_RADIO) {
            this.u.removeCallbacksAndMessages(null);
            v();
        } else if (PlayerManager.a(GaanaApplication.getContext()).f()) {
            if (!PlayerManager.a(GaanaApplication.getContext()).h()) {
                PlayerManager.a(GaanaApplication.getContext()).f(true);
            }
            this.u.removeCallbacksAndMessages(null);
            v();
        } else {
            this.B = true;
            ((BaseActivity) getActivity()).showProgressDialog();
        }
    }

    private void a(boolean z, PlayerCommands playerCommands) {
        switch (playerCommands) {
            case PLAY_PREVIOUS:
                if (z) {
                    ap.a().a(getContext(), this.j.getString(R.string.no_previous_song));
                    return;
                }
                return;
            case PLAY_NEXT:
                if (z) {
                    ap.a().a(getContext(), this.j.getString(R.string.no_next_song));
                    if (GaanaMusicService.s().l() || GaanaMusicService.s().k()) {
                        this.D = 0;
                        C();
                        return;
                    }
                    return;
                }
                if (!ad.a(this.j).o().booleanValue()) {
                    ap.a().a(getContext(), this.j.getString(R.string.playback_ended));
                }
                this.a = PlayerManager.a(GaanaApplication.getContext()).a(PlayerManager.a(GaanaApplication.getContext()).s());
                this.u.removeCallbacksAndMessages(null);
                v();
                this.D = 0;
                C();
                return;
            default:
                return;
        }
    }

    private void A() {
        int u;
        try {
            u = GaanaMusicService.s().u();
        } catch (IllegalStateException unused) {
            u = 0;
        }
        o.a(getContext(), this.n.getProgress());
        this.n.setMax(u);
        this.n.setSecondaryProgress(this.n.getProgress());
        B();
        if (PlayerStatus.a(getContext()).b() || PlayerStatus.a(getContext()).c()) {
            this.D = 1;
            C();
            return;
        }
        this.D = 0;
        C();
    }

    private void B() {
        int v;
        if (!GaanaMusicService.s().m()) {
            if (!ad.a(this.j).o().booleanValue()) {
                int u;
                try {
                    v = GaanaMusicService.s().v();
                    u = GaanaMusicService.s().u();
                } catch (IllegalStateException unused) {
                    v = 0;
                    u = v;
                }
                int i = u - v;
                this.n.setProgress(v);
                this.n.setMax(u);
                this.n.setSelected(false);
                this.n.setSecondaryProgress((int) ((0.01d * ((double) GaanaMusicService.s().t())) * ((double) GaanaMusicService.s().u())));
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
                    v();
                }
                if (this.d && v > 30000) {
                    this.d = false;
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
                    AnonymousClass12 anonymousClass12 = new Runnable() {
                        public void run() {
                            MiniPlayerFragment.this.B();
                        }
                    };
                    this.u.removeCallbacksAndMessages(null);
                    this.u.postDelayed(anonymousClass12, 1000);
                }
            } else if (GaanaMusicService.t()) {
                AnonymousClass2 anonymousClass2 = new Runnable() {
                    public void run() {
                        MiniPlayerFragment.this.B();
                    }
                };
                this.u.removeCallbacksAndMessages(null);
                this.u.postDelayed(anonymousClass2, 1000);
            } else {
                if (this.u != null) {
                    this.u.removeCallbacksAndMessages(null);
                }
            }
        }
    }

    private void C() {
        PlayerTrack i = PlayerManager.a(GaanaApplication.getContext()).i();
        Track track = null;
        Track b = i != null ? i.b() : null;
        if (this.M != null) {
            track = this.M.a(true);
        }
        if (PlayerManager.a(GaanaApplication.getContext()).m() != PlayerType.GAANA_RADIO && this.M != null && (b == null || track == null || !b.getBusinessObjId().equals(track.getBusinessObjId()))) {
            this.r.setVisibility(8);
            this.q.setVisibility(0);
            this.q.setImageDrawable(this.p);
        } else if (this.D == 0) {
            this.q.setImageDrawable(this.p);
            this.q.setVisibility(0);
            this.r.setVisibility(8);
        } else if (this.D == 1) {
            this.q.setImageDrawable(this.o);
            this.q.setVisibility(0);
            this.r.setVisibility(8);
        } else if (this.D == 2) {
            this.q.setVisibility(8);
            this.r.setVisibility(0);
        }
    }

    public void a(f fVar, int i) {
        if (GaanaMusicService.s().m()) {
            this.n.setSecondaryProgress(0);
            return;
        }
        this.n.setMax(fVar.u());
        this.n.setSecondaryProgress((int) ((0.01d * ((double) i)) * ((double) fVar.u())));
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ((GaanaActivity) this.j).setPlayerListeners(this);
    }

    private void D() {
        o.a("listener_mini_frag", this.K);
        this.a = PlayerManager.a(GaanaApplication.getContext()).a(PlaySequenceType.CURRENT);
        a(this.a);
        if (!this.v) {
            v();
        }
        PlayerManager.a(this.j).d(null);
        this.D = 2;
        C();
        this.w = PlayerStates.LOADING;
        d();
        if (GaanaApplication.sessionHistoryCount > 0) {
            ((GaanaActivity) this.j).CallCustomCardApi(false, true);
        }
        if (!((GaanaActivity) this.j).IS_COACHMARK_VISIBLE && GaanaApplication.sessionHistoryCount > 0 && !this.a.a(true).isLocalMedia()) {
            Util.i(this.j, "Listen");
        }
    }

    private void E() {
        if (GaanaMusicService.s().m()) {
            this.D = 2;
            C();
        } else if (GaanaMusicService.t()) {
            this.D = 1;
            C();
        } else {
            this.D = 0;
            C();
        }
    }

    public void onResume() {
        super.onResume();
        F();
        if (this.z != null) {
            this.z.addOnPageChangeListener(this.i);
        }
        try {
            f();
        } catch (Exception unused) {
        }
    }

    public void onPause() {
        super.onPause();
        this.C = null;
        if (this.z != null) {
            this.z.removeOnPageChangeListener(this.i);
        }
        if (o.a("listener_mini_frag") != null) {
            PlayerManager.a(GaanaApplication.getContext()).a(null);
            PlayerManager.a(GaanaApplication.getContext()).a(null);
            PlayerManager.a(GaanaApplication.getContext()).a(null);
            ad.a(GaanaApplication.getContext()).a(null);
            Util.a(null);
            this.u.removeCallbacksAndMessages(null);
            o.b("listener_mini_frag");
            o.d("listener_mini_frag");
        }
    }

    public PlayerStates h() {
        return this.w;
    }

    public void a(List<PlayerTrack> list) {
        if (list != null && list.size() > 0) {
            if (this.y == null) {
                this.y = new ArrayList();
            }
            this.y.clear();
            this.y.addAll(list);
            if (this.A != null) {
                this.A.notifyDataSetChanged();
                int s = PlayerManager.a(GaanaApplication.getContext()).s();
                if (s >= 0 && s < this.y.size()) {
                    if (s < this.z.getCurrentItem()) {
                        this.f = true;
                    } else if (s > this.z.getCurrentItem()) {
                        this.h = true;
                    }
                    this.M = (PlayerTrack) this.y.get(s);
                    this.z.setCurrentItem(s);
                }
            }
        }
    }

    private void F() {
        PlayerManager.a(GaanaApplication.getContext()).a((aj) this);
        PlayerManager.a(GaanaApplication.getContext()).a((ak) this);
        PlayerManager.a(GaanaApplication.getContext()).a((com.managers.PlayerManager.a) this);
        ad.a(GaanaApplication.getContext()).a((com.managers.ad.a) this);
    }

    public void i() {
        a(PlayerManager.a(GaanaApplication.getContext()).n());
        if (PlayerManager.a(GaanaApplication.getContext()).d()) {
            PlayerManager.a(GaanaApplication.getContext()).b(false);
            d.a().a("PREFERENCE_KEY_REPEAT_STATUS", 0, true);
        }
        if (this.C != null) {
            this.C.on_enque();
        }
    }

    public void j() {
        if (this.C != null) {
            this.C.refreshList();
        }
    }

    public void k() {
        a(PlayerManager.a(GaanaApplication.getContext()).n());
        if (this.C != null) {
            this.C.on_deque();
        }
    }

    public void a(boolean z) {
        a(PlayerManager.a(GaanaApplication.getContext()).n());
        if (this.C != null) {
            this.C.updateCardAdapter(z);
        }
    }

    public void l() {
        if (isAdded() && ad.a(this.j).o().booleanValue()) {
            this.B = false;
            this.a = PlayerManager.a(this.j).i();
            if (this.a != null) {
                a(this.a);
            }
            if (this.C != null) {
                this.C.onLiveRadioUpdate();
            }
        }
    }

    public void a(Boolean bool) {
        if (this.B || bool.booleanValue()) {
            this.B = false;
            PlayerManager.a(GaanaApplication.getContext()).k();
            PlayerManager.a(GaanaApplication.getContext()).f(true);
            if (this.C != null) {
                this.C.onRadioTracksFetched(bool.booleanValue());
            }
        }
    }

    public void m() {
        if (this.C != null) {
            this.C.refreshForFavorite();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(int i) {
        if ((this.e || this.f) && i == PlayerManager.a(GaanaApplication.getContext()).s()) {
            this.f = false;
            this.e = false;
            return false;
        }
        this.f = false;
        this.e = false;
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean b(int i) {
        if ((this.g || this.h) && i == PlayerManager.a(GaanaApplication.getContext()).s()) {
            this.h = false;
            this.g = false;
            return false;
        }
        this.h = false;
        this.g = false;
        return true;
    }

    public void n() {
        int s = PlayerManager.a(GaanaApplication.getContext()).s();
        PlayerTrack playerTrack = (PlayerTrack) this.y.get(s);
        Track a = ((PlayerTrack) this.y.get(s)).a(true);
        CharSequence trim = a(a.getAlbumTitle(), a.getArtistNames()).trim();
        if (Constants.aG) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.j.getString(R.string.CASTING_TO));
            stringBuilder.append(Constants.aH);
            trim = stringBuilder.toString();
        } else if (PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO && !TextUtils.isEmpty(ad.a(GaanaApplication.getContext()).l())) {
            trim = ad.a(GaanaApplication.getContext()).o().booleanValue() ? ad.a(GaanaApplication.getContext()).l() : a(this.a.b().getAlbumTitle(), this.a.b().getArtistNames());
        }
        if (this.O != null) {
            this.O.setText(trim);
        }
        if (this.P != null) {
            this.P.setText(a.getTrackTitle());
        }
    }

    public void b(String str) {
        Track a = ((PlayerTrack) this.y.get(PlayerManager.a(GaanaApplication.getContext()).s())).a(true);
        if (this.O != null) {
            this.O.setText(a.getAlbumTitle());
        }
        if (this.P != null) {
            this.P.setText(str);
            this.P.setSelected(true);
        }
    }

    /* Access modifiers changed, original: protected */
    public String a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" - ");
            stringBuilder.append(str2);
            return stringBuilder.toString();
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return "";
        } else {
            return TextUtils.isEmpty(str) ? str2 : str;
        }
    }

    public void a(av avVar) {
        this.C = avVar;
    }

    public void o() {
        FragmentManager supportFragmentManager = ((GaanaActivity) this.j).getSupportFragmentManager();
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
