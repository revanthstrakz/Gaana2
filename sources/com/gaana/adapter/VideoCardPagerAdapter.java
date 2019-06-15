package com.gaana.adapter;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioFocusRequest.Builder;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPagerUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.exoplayer2.ui.VideoPlayerView;
import com.fragments.BaseGaanaFragment;
import com.fragments.GaanaVideoPlayerFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.login.sso.SsoErrorCodes;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Tracks.Track;
import com.gaana.view.GaanaYourYearView.GAANA_ENTRY_PAGE;
import com.gaanavideo.c;
import com.gaanavideo.e;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.api.client.http.HttpStatusCodes;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.internal.LinkedTreeMap;
import com.i.e.b;
import com.logging.VideoTrackLog;
import com.managers.PlayerManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.aj;
import com.managers.ap.a;
import com.managers.u;
import com.player_framework.d;
import com.player_framework.f;
import com.player_framework.m;
import com.player_framework.n;
import com.player_framework.o;
import com.services.l.al;
import com.utilities.Util;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class VideoCardPagerAdapter extends PagerAdapter {
    private static boolean isFirstRetry = true;
    private al ItemClickListener;
    private final OnAudioFocusChangeListener _audioFocusChangeListener = new OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int i) {
            if (e.b != null) {
                switch (i) {
                    case -3:
                        if (e.b.isPlaying()) {
                            e.b.setVolume(0.1f, 0.1f);
                            break;
                        }
                        break;
                    case -2:
                        if (e.b.isPlaying()) {
                            e.b.h();
                            VideoCardPagerAdapter.this.needToResume = true;
                            break;
                        }
                        break;
                    case -1:
                        if (e.b.isPlaying()) {
                            VideoCardPagerAdapter.this.pausePlayer();
                            VideoCardPagerAdapter.this.needToResume = false;
                            break;
                        }
                        break;
                    case 1:
                        if (VideoCardPagerAdapter.this.mFragment != null && VideoCardPagerAdapter.this.mFragment.isResumed() && VideoCardPagerAdapter.this.needToResume) {
                            if (e.b.isPlaying()) {
                                e.b.setVolume(1.0f, 1.0f);
                            } else {
                                e.b.j();
                                e.b.setVolume(1.0f, 1.0f);
                            }
                            VideoCardPagerAdapter.this.needToResume = false;
                            break;
                        }
                        return;
                    case 2:
                        if (e.b.isPlaying() && VideoCardPagerAdapter.this.needToResume) {
                            e.b.setVolume(1.0f, 1.0f);
                            VideoCardPagerAdapter.this.needToResume = false;
                            break;
                        }
                }
            }
        }
    };
    private final m _playerCallbacksListener = new m() {
        public void onAdEventUpdate(f fVar, AdEvent adEvent) {
        }

        public void onInfo(f fVar, int i, int i2) {
        }

        public void onPrepared(f fVar) {
            long u = (long) e.b.u();
            String format = String.format("%2d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) / 60), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) % 60)});
            if (VideoCardPagerAdapter.this.currentTimerText != null) {
                VideoCardPagerAdapter.this.currentTimerText.setText(format.trim());
            }
            VideoCardPagerAdapter.this.currentProgressBar.setVisibility(8);
            ((GaanaActivity) VideoCardPagerAdapter.this.mContext).getWindow().addFlags(128);
            VideoCardPagerAdapter.this.startPlayProgressUpdater();
            VideoCardPagerAdapter.this.performVideoTrackLogging();
            VideoCardPagerAdapter.isFirstRetry = true;
            u.a().a("VideoFeed Events", "Video Played Online", com.logging.e.c().b());
        }

        public void onBufferingUpdate(f fVar, int i) {
            VideoCardPagerAdapter.this.currentSeekbar.setSecondaryProgress((int) ((0.01d * ((double) i)) * ((double) fVar.u())));
        }

        public void onCompletion(f fVar) {
            com.logging.e.c().b(GAANA_ENTRY_PAGE.VIDEO_FEED.name());
            VideoCardPagerAdapter.this.playVideoOnItemClick(VideoCardPagerAdapter.this.mCurrentPosition + 1, -1);
        }

        public void onError(f fVar, int i, int i2) {
            if (fVar == e.b) {
                if (i == HttpStatusCodes.STATUS_CODE_FOUND) {
                    VideoCardPagerAdapter.this.playVideoOnItemClick(VideoCardPagerAdapter.this.mCurrentPosition + 1, -1);
                    u.a().a("VideoStreamingFailure", "Buffer not fetched - Server-302", Util.O());
                } else if (i == 403) {
                    VideoCardPagerAdapter.this.playVideoAgain(fVar, i);
                } else if (i == SsoErrorCodes.NO_INTERNET_CONNECTION) {
                    if (Util.j(VideoCardPagerAdapter.this.mContext)) {
                        VideoCardPagerAdapter.this.playVideoOnItemClick(VideoCardPagerAdapter.this.mCurrentPosition + 1, -1);
                    }
                } else if (i == 9876) {
                    VideoCardPagerAdapter.this.playVideoAgain(fVar, i);
                } else if (i == 4567) {
                    e.a(2);
                    e.a(0);
                    VideoCardPagerAdapter.this.playVideoAgain(fVar, i);
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Buffer not fetched - ");
                    stringBuilder.append(i);
                    u.a().a("VideoStreamingFailure", stringBuilder.toString(), Util.O());
                }
            } else if (fVar == e.c) {
                e.a(2);
            } else if (fVar == e.a) {
                e.a(0);
            } else if (fVar != null) {
                fVar.d(false);
                fVar.c(false);
                fVar.b(false);
                fVar.y();
                fVar.w();
            }
        }
    };
    private final Handler _seekHandler = new Handler();
    private final AudioFocusRequest audioFocusRequest;
    private ImageView currentPlayPauseIcon;
    private ImageView currentProgressBar;
    private SeekBar currentSeekbar;
    private TextView currentTimerText;
    private YouTubeVideo currentYoutubeVideo;
    boolean isFadeOut;
    private boolean isFromUser;
    private View mClickedViewFavorite;
    private final Context mContext;
    private int mCurrentPosition = 0;
    private BaseGaanaFragment mFragment;
    private final PlayerManager mPlayerManager;
    private ArrayList<Item> mTrackList;
    private ViewPager mViewPager;
    private boolean needToResume = false;
    private int progressPosition;
    private long seekBarLastPosition = 0;
    private int seekPositionIfAny = -1;
    private LinearLayout videoController;

    private void setOptionLayout(int i, LinearLayout linearLayout) {
    }

    public int getItemPosition(@NonNull Object obj) {
        return -2;
    }

    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }

    private void performVideoTrackLogging() {
        VideoTrackLog videoTrackLog = new VideoTrackLog();
        videoTrackLog.b(com.logging.e.c().d());
        videoTrackLog.a(com.logging.e.c().e());
        if (!(GaanaApplication.getInstance().getCurrentUser() == null || GaanaApplication.getInstance().getCurrentUser().getUserProfile() == null || GaanaApplication.getInstance().getCurrentUser().getUserProfile().getUserId() == null)) {
            videoTrackLog.c(GaanaApplication.getInstance().getCurrentUser().getUserProfile().getUserId());
        }
        videoTrackLog.d(Util.l(this.mContext));
        videoTrackLog.a(com.logging.e.c().f());
        videoTrackLog.c(System.currentTimeMillis());
        videoTrackLog.e(com.logging.e.c().b());
        videoTrackLog.b(com.logging.e.c().a());
        com.logging.e.c().a(videoTrackLog, this.mContext);
    }

    public LinearLayout getVideoController() {
        return this.videoController;
    }

    private void playVideoAgain(f fVar, int i) {
        if (fVar != null && fVar.s() != null && e.b != null && fVar.s().equalsIgnoreCase(e.b.s())) {
            if (isFirstRetry) {
                final YouTubeVideo youTubeVideo = (YouTubeVideo) Util.f((Item) this.mTrackList.get(this.mCurrentPosition));
                new d(this.mContext).a(youTubeVideo.getBusinessObjId(), youTubeVideo.e() == 2 ? "horz" : "vert", new b() {
                    public void onDataRetrieved(Object obj, int i, boolean z) {
                        try {
                            String str = null;
                            if (obj instanceof String) {
                                str = Util.k((String) obj);
                            } else if (obj != null && (obj instanceof LinkedTreeMap)) {
                                String str2 = (String) ((LinkedTreeMap) obj).get("data");
                                if (!TextUtils.isEmpty(str2)) {
                                    str = Util.k(str2);
                                }
                            }
                            String str3 = str;
                            if (str3 == null) {
                                VideoCardPagerAdapter.this.playVideoOnItemClick(VideoCardPagerAdapter.this.mCurrentPosition + 1, -1);
                                VideoCardPagerAdapter.isFirstRetry = false;
                                return;
                            }
                            if (youTubeVideo.e() == 2) {
                                e.b.f(false);
                            } else {
                                e.b.f(false);
                            }
                            e.b.a(VideoCardPagerAdapter.this.mContext, str3, null, 0, false);
                            if (VideoCardPagerAdapter.this.grabAudioFocus()) {
                                e.b.q();
                            }
                            if (VideoCardPagerAdapter.this.seekPositionIfAny != -1) {
                                e.b.a(VideoCardPagerAdapter.this.seekPositionIfAny);
                                VideoCardPagerAdapter.this.seekPositionIfAny = -1;
                            }
                            View currentView = ViewPagerUtils.getCurrentView(VideoCardPagerAdapter.this.mViewPager);
                            if (currentView != null && (currentView.findViewById(R.id.video_feed_card) instanceof VideoPlayerView)) {
                                VideoPlayerView videoPlayerView = (VideoPlayerView) currentView.findViewById(R.id.video_feed_card);
                                videoPlayerView.b();
                                e.b.a(videoPlayerView);
                            }
                            VideoCardPagerAdapter.isFirstRetry = false;
                        } catch (Exception e) {
                            ThrowableExtension.printStackTrace(e);
                        } catch (Throwable th) {
                            VideoCardPagerAdapter.isFirstRetry = false;
                        }
                    }

                    public void onErrorResponse(BusinessObject businessObject) {
                        VideoCardPagerAdapter.isFirstRetry = false;
                    }
                });
                return;
            }
            if (i == 403) {
                u.a().a("VideoStreamingFailure", "Buffer not fetched - Server-403", Util.O());
            } else if (i == 9876) {
                u.a().a("VideoStreamingFailure", "Buffer not fetched - Cache Read Failure - 9876", Util.O());
            } else if (i == 4567) {
                u.a().a("VideoStreamingFailure", "Buffer not fetched - Media Codec Renderer - 4567", Util.O());
            }
            isFirstRetry = true;
            playVideoOnItemClick(this.mCurrentPosition + 1, -1);
        }
    }

    public void releasePlayers() {
        if (e.b != null) {
            com.logging.e.c().b((long) e.b.v());
            Util.A();
        }
        if (this.currentYoutubeVideo != null) {
            com.logging.e.c().a(this.currentYoutubeVideo.getBusinessObjId());
        }
        e.a();
        this._seekHandler.removeCallbacksAndMessages(null);
        releaseAudioFocus();
        ((GaanaActivity) this.mContext).getWindow().clearFlags(128);
    }

    public void pausePlayer() {
        if (e.b != null && e.b.isPlaying()) {
            e.b.h();
        }
        ((GaanaActivity) this.mContext).getWindow().clearFlags(128);
        if (this.currentPlayPauseIcon != null) {
            this.currentPlayPauseIcon.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.vector_player_play_white));
        }
        releaseAudioFocus();
    }

    private void releaseAudioFocus() {
        AudioManager audioManager = (AudioManager) this.mContext.getSystemService("audio");
        if (com.utilities.d.d()) {
            audioManager.abandonAudioFocusRequest(this.audioFocusRequest);
        } else {
            audioManager.abandonAudioFocus(this._audioFocusChangeListener);
        }
    }

    public boolean grabAudioFocus() {
        int requestAudioFocus;
        AudioManager audioManager = (AudioManager) this.mContext.getSystemService("audio");
        if (com.utilities.d.d()) {
            requestAudioFocus = audioManager.requestAudioFocus(this.audioFocusRequest);
        } else {
            requestAudioFocus = audioManager.requestAudioFocus(this._audioFocusChangeListener, 3, 1);
        }
        if (requestAudioFocus != 0) {
            return true;
        }
        for (n nVar : o.d().values()) {
            if (nVar != null) {
                nVar.displayErrorToast(this.mContext.getResources().getString(R.string.error_ongoing_call_during_music_play), 1);
            }
        }
        e.a(1);
        return false;
    }

    public void updatePlayer(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("state : ");
        stringBuilder.append(i);
        stringBuilder.append("deltaX : ");
        stringBuilder.append(i2);
        Log.v("gb103", stringBuilder.toString());
        if (i == 0) {
            if (e.b != null) {
                com.logging.e.c().b((long) e.b.v());
                Util.A();
            }
            if (this.currentYoutubeVideo != null) {
                com.logging.e.c().a(this.currentYoutubeVideo.getBusinessObjId());
            }
            updatePlayerInIdleState();
        } else if (i == 1) {
            if (i2 > 0) {
                if (e.a != null) {
                    e.a.r();
                }
                if (e.c != null) {
                    if (e.b != null) {
                        com.logging.e.c().b((long) e.b.v());
                        Util.A();
                    }
                    if (this.currentYoutubeVideo != null) {
                        com.logging.e.c().a(this.currentYoutubeVideo.getBusinessObjId());
                    }
                    e.c.b(false);
                    e.c.q();
                    return;
                }
                return;
            }
            if (e.c != null) {
                e.c.r();
            }
            if (e.a != null) {
                if (e.b != null) {
                    com.logging.e.c().b((long) e.b.v());
                    Util.A();
                }
                if (this.currentYoutubeVideo != null) {
                    com.logging.e.c().a(this.currentYoutubeVideo.getBusinessObjId());
                }
                e.a.b(false);
                e.a.q();
            }
        } else if (i != 2) {
        } else {
            if (i2 < 0) {
                if (e.c != null) {
                    e.c.r();
                }
            } else if (e.a != null) {
                e.a.r();
            }
        }
    }

    private void defineCurrentViewChilderen() {
        int currentItem = this.mViewPager.getCurrentItem();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("defineCurrentViewChilderen : current position:");
        stringBuilder.append(currentItem);
        Log.v("gb103", stringBuilder.toString());
        View currentView = ViewPagerUtils.getCurrentView(this.mViewPager);
        if (currentView != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("defineCurrentViewChilderen : current position child:");
            stringBuilder.append(currentView);
            Log.v("gb103", stringBuilder.toString());
            VideoPlayerView videoPlayerView = (VideoPlayerView) currentView.findViewById(R.id.video_feed_card);
            this.currentSeekbar = (SeekBar) currentView.findViewById(R.id.seekBar);
            this.currentTimerText = (TextView) currentView.findViewById(R.id.timer_text);
            this.currentPlayPauseIcon = (ImageView) currentView.findViewById(R.id.play_pause_icon);
            this.currentProgressBar = (ImageView) currentView.findViewById(R.id.progressbar);
            com.bumptech.glide.e.c(this.mContext.getApplicationContext()).asGif().load(Integer.valueOf(R.drawable.videoloader)).into(this.currentProgressBar);
            this.currentProgressBar.setVisibility(0);
            this.videoController = (LinearLayout) currentView.findViewById(R.id.video_controls);
            if (this.isFadeOut) {
                this.videoController.setVisibility(0);
            } else {
                this.videoController.setVisibility(8);
            }
            if (!(e.b == null || e.b.l())) {
                this.currentPlayPauseIcon.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.vector_player_pause_white));
            }
            this.currentPlayPauseIcon.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (e.b != null && e.b.isPlaying()) {
                        VideoCardPagerAdapter.this.pausePlayer();
                        e.b.b(true);
                        VideoCardPagerAdapter.this.currentPlayPauseIcon.setImageDrawable(VideoCardPagerAdapter.this.mContext.getResources().getDrawable(R.drawable.vector_player_play_white));
                        u.a().b("VideoFeed", "Pause");
                    } else if (e.b != null) {
                        e.b.j();
                        e.b.b(false);
                        ((GaanaActivity) VideoCardPagerAdapter.this.mContext).getWindow().addFlags(128);
                        VideoCardPagerAdapter.this.currentPlayPauseIcon.setImageDrawable(VideoCardPagerAdapter.this.mContext.getResources().getDrawable(R.drawable.vector_player_pause_white));
                        VideoCardPagerAdapter.this.startPlayProgressUpdater();
                        u.a().b("VideoFeed", "Play");
                    }
                }
            });
            this.currentSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (VideoCardPagerAdapter.this.isFromUser) {
                        Util.e(VideoCardPagerAdapter.this.seekBarLastPosition - ((long) VideoCardPagerAdapter.this.progressPosition));
                    }
                    e.b.a(VideoCardPagerAdapter.this.currentSeekbar.getProgress());
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    VideoCardPagerAdapter.this.seekBarLastPosition = (long) VideoCardPagerAdapter.this.progressPosition;
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    VideoCardPagerAdapter.this.isFromUser = z;
                    r0 = new Object[2];
                    long j = (long) i;
                    r0[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) / 60);
                    r0[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) % 60);
                    String.format("%2d:%02d", r0);
                    r15 = new Object[2];
                    long u = (long) (e.b.u() - i);
                    r15[0] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) / 60);
                    r15[1] = Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(u) % 60);
                    VideoCardPagerAdapter.this.currentTimerText.setText(String.format("%2d:%02d", r15));
                    VideoCardPagerAdapter.this.progressPosition = i;
                }
            });
        }
    }

    private void startPlayProgressUpdater() {
        int v;
        if (this.currentSeekbar != null && e.b != null) {
            int u;
            try {
                v = e.b.v();
                u = e.b.u();
            } catch (Exception unused) {
                v = 0;
                u = v;
            }
            this.currentSeekbar.setMax(u);
            com.logging.e.c().a((long) u);
            this.currentSeekbar.setProgress(v);
            this.currentSeekbar.setSelected(false);
            this.currentSeekbar.setSecondaryProgress((int) ((0.01d * ((double) e.b.t())) * ((double) e.b.u())));
            String.format("%2d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(r4) / 60), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(r4) % 60)});
            if (e.b.isPlaying() && !e.b.m()) {
                AnonymousClass6 anonymousClass6 = new Runnable() {
                    public void run() {
                        VideoCardPagerAdapter.this.startPlayProgressUpdater();
                    }
                };
                this._seekHandler.removeCallbacksAndMessages(null);
                this._seekHandler.postDelayed(anonymousClass6, 1000);
            }
        }
    }

    private void updatePlayerInIdleState() {
        Log.v("gb103", "updatePlayerInIdleState");
        int currentItem = this.mViewPager.getCurrentItem();
        defineCurrentViewChilderen();
        m e;
        if (currentItem > this.mCurrentPosition && e.c != null && currentItem == this.mCurrentPosition + 1) {
            e.a(0);
            e.a = e.b;
            if (e.a != null) {
                e.a.a(0);
                e.a.r();
            }
            e.b = e.c;
            e.c = null;
            e.b.d(true);
            e.b.q();
            e.b.c(false);
            e.b.b(false);
            e = o.e("LISTENER_KEY_VIDEO_FEED_PLAYER_ACTIVITY");
            if (e != null && e.b.b()) {
                e.onPrepared(e.b);
            }
            setupPlayer(2);
        } else if (currentItem < this.mCurrentPosition && e.a != null && currentItem == this.mCurrentPosition - 1) {
            e.a(2);
            e.c = e.b;
            if (e.c != null) {
                e.c.a(0);
                e.c.r();
            }
            e.b = e.a;
            e.a = null;
            e.b.d(true);
            e.b.q();
            e.b.c(false);
            e.b.b(false);
            e = o.e("LISTENER_KEY_VIDEO_FEED_PLAYER_ACTIVITY");
            if (e != null && e.b.b()) {
                e.onPrepared(e.b);
            }
            setupPlayer(0);
        } else if (currentItem != this.mCurrentPosition || e.b == null) {
            e.a(1);
            e.a(2);
            e.a(0);
            setupPlayer(1);
            setupPlayer(2);
            setupPlayer(0);
        } else {
            if (!(e.b.isPlaying() || e.b.l())) {
                e.b.q();
            }
            if (e.c != null) {
                e.c.r();
            }
            if (e.a != null) {
                e.a.r();
            }
            if (this.currentProgressBar != null) {
                this.currentProgressBar.setVisibility(8);
            }
        }
        o.b("LISTENER_KEY_VIDEO_FEED_PLAYER_ACTIVITY", this._playerCallbacksListener);
        this.mCurrentPosition = currentItem;
    }

    public void resetPlayerArrangement() {
        if (e.b != null) {
            com.logging.e.c().b((long) e.b.v());
            Util.A();
        }
        if (this.currentYoutubeVideo != null) {
            com.logging.e.c().a(this.currentYoutubeVideo.getBusinessObjId());
        }
        int currentItem = this.mViewPager.getCurrentItem();
        e.a(1);
        e.a(2);
        e.a(0);
        setupPlayer(1);
        setupPlayer(2);
        setupPlayer(0);
        o.b("LISTENER_KEY_VIDEO_FEED_PLAYER_ACTIVITY", this._playerCallbacksListener);
        this.mCurrentPosition = currentItem;
    }

    private void setupCurrentPlayer(final int i) {
        final BusinessObject businessObject = (YouTubeVideo) Util.f((Item) this.mTrackList.get(i));
        new d(this.mContext).a(businessObject, businessObject.e() == 2 ? "horz" : "vert", new b() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onDataRetrieved(Object obj, int i, boolean z) {
                if (i != VideoCardPagerAdapter.this.mViewPager.getCurrentItem()) {
                    if (e.b != null) {
                        e.a(1);
                    }
                    return;
                }
                try {
                    String str = null;
                    if (obj instanceof String) {
                        str = Util.k((String) obj);
                    } else if (obj != null && (obj instanceof LinkedTreeMap)) {
                        String str2 = (String) ((LinkedTreeMap) obj).get("data");
                        if (!TextUtils.isEmpty(str2)) {
                            str = Util.k(str2);
                        }
                    }
                    String str3 = str;
                    if (str3 != null) {
                        if (e.b != null) {
                            e.a(1);
                        }
                        e.b = new c();
                        e.b.c(false);
                        e.b.d(true);
                        if (businessObject.e() == 2) {
                            e.b.f(false);
                        } else {
                            e.b.f(false);
                        }
                        e.b.a(VideoCardPagerAdapter.this.mContext, str3, null, 0, false);
                        if (VideoCardPagerAdapter.this.grabAudioFocus()) {
                            e.b.q();
                        }
                        if (VideoCardPagerAdapter.this.seekPositionIfAny != -1) {
                            e.b.a(VideoCardPagerAdapter.this.seekPositionIfAny);
                            VideoCardPagerAdapter.this.seekPositionIfAny = -1;
                        }
                        View currentView = ViewPagerUtils.getCurrentView(VideoCardPagerAdapter.this.mViewPager);
                        if (currentView != null && (currentView.findViewById(R.id.video_feed_card) instanceof VideoPlayerView)) {
                            VideoPlayerView videoPlayerView = (VideoPlayerView) currentView.findViewById(R.id.video_feed_card);
                            videoPlayerView.b();
                            e.b.a(videoPlayerView);
                        }
                    }
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        });
    }

    private void setupNextPlayer(final int i) {
        final BusinessObject businessObject = (YouTubeVideo) Util.f((Item) this.mTrackList.get(i));
        new d(this.mContext).a(businessObject, businessObject.e() == 2 ? "horz" : "vert", new b() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onDataRetrieved(Object obj, int i, boolean z) {
                if (i != VideoCardPagerAdapter.this.mViewPager.getCurrentItem() + 1) {
                    if (e.c != null) {
                        e.a(2);
                    }
                    return;
                }
                try {
                    String str = null;
                    if (obj instanceof String) {
                        str = Util.k((String) obj);
                    } else if (obj != null && (obj instanceof LinkedTreeMap)) {
                        String str2 = (String) ((LinkedTreeMap) obj).get("data");
                        if (!TextUtils.isEmpty(str2)) {
                            str = Util.k(str2);
                        }
                    }
                    String str3 = str;
                    if (str3 != null && VideoCardPagerAdapter.this.mViewPager.getCurrentItem() < VideoCardPagerAdapter.this.mTrackList.size() - 1) {
                        if (e.c != null) {
                            e.a(2);
                        }
                        e.c = new c();
                        e.c.c(true);
                        e.c.d(false);
                        if (businessObject.e() == 2) {
                            e.c.f(false);
                        } else {
                            e.c.f(false);
                        }
                        e.c.a(VideoCardPagerAdapter.this.mContext, str3, null, 0, false);
                        e.c.h();
                        View nextView = ViewPagerUtils.getNextView(VideoCardPagerAdapter.this.mViewPager);
                        if (nextView != null && (nextView.findViewById(R.id.video_feed_card) instanceof VideoPlayerView)) {
                            VideoPlayerView videoPlayerView = (VideoPlayerView) nextView.findViewById(R.id.video_feed_card);
                            videoPlayerView.b();
                            e.c.a(videoPlayerView);
                        }
                    }
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        });
    }

    private void setupPrevPlayer(final int i) {
        final BusinessObject businessObject = (YouTubeVideo) Util.f((Item) this.mTrackList.get(i));
        new d(this.mContext).a(businessObject, businessObject.e() == 2 ? "horz" : "vert", new b() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onDataRetrieved(Object obj, int i, boolean z) {
                if (i != VideoCardPagerAdapter.this.mViewPager.getCurrentItem() - 1) {
                    if (e.a != null) {
                        e.a(0);
                    }
                    return;
                }
                try {
                    String str = null;
                    if (obj instanceof String) {
                        str = Util.k((String) obj);
                    } else if (obj != null && (obj instanceof LinkedTreeMap)) {
                        String str2 = (String) ((LinkedTreeMap) obj).get("data");
                        if (!TextUtils.isEmpty(str2)) {
                            str = Util.k(str2);
                        }
                    }
                    String str3 = str;
                    if (str3 != null && VideoCardPagerAdapter.this.mViewPager.getCurrentItem() > 0) {
                        if (e.a != null) {
                            e.a(0);
                        }
                        e.a = new c();
                        e.a.c(true);
                        e.a.d(false);
                        if (businessObject.e() == 2) {
                            e.a.f(false);
                        } else {
                            e.a.f(false);
                        }
                        e.a.a(VideoCardPagerAdapter.this.mContext, str3, null, 0, false);
                        e.a.h();
                        View previousView = ViewPagerUtils.getPreviousView(VideoCardPagerAdapter.this.mViewPager);
                        if (previousView != null && (previousView.findViewById(R.id.video_feed_card) instanceof VideoPlayerView)) {
                            VideoPlayerView videoPlayerView = (VideoPlayerView) previousView.findViewById(R.id.video_feed_card);
                            videoPlayerView.b();
                            e.a.a(videoPlayerView);
                        }
                    }
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        });
    }

    private void setupPlayer(int i) {
        int currentItem = this.mViewPager.getCurrentItem();
        if (i == 2 && currentItem < this.mTrackList.size() - 1) {
            setupNextPlayer(currentItem + 1);
        } else if (i == 0 && currentItem > 0) {
            setupPrevPlayer(currentItem - 1);
        } else if (i == 1) {
            setupCurrentPlayer(currentItem);
        }
    }

    public void setUpdateLastSongPlayedDuration() {
        if (e.b != null) {
            com.logging.e.c().b((long) e.b.v());
            Util.A();
        }
        if (this.currentYoutubeVideo != null) {
            com.logging.e.c().a(this.currentYoutubeVideo.getBusinessObjId());
        }
    }

    public void updateList(ArrayList<Item> arrayList) {
        this.mTrackList = arrayList;
        e.a(1);
        e.a(2);
        e.a(0);
    }

    public VideoCardPagerAdapter(Context context, GaanaVideoPlayerFragment gaanaVideoPlayerFragment, ViewPager viewPager, ArrayList<Item> arrayList, al alVar) {
        this.mContext = context;
        this.mFragment = gaanaVideoPlayerFragment;
        this.mPlayerManager = PlayerManager.a(this.mContext);
        this.mViewPager = viewPager;
        this.mTrackList = arrayList;
        this.ItemClickListener = alVar;
        if (com.utilities.d.d()) {
            this.audioFocusRequest = new Builder(1).setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(3).build()).setOnAudioFocusChangeListener(this._audioFocusChangeListener).build();
        } else {
            this.audioFocusRequest = null;
        }
    }

    private void setAndUpdateFavoritesV2(int i, final ImageView imageView, TextView textView, LinearLayout linearLayout) {
        Track b = this.mPlayerManager.a(i).b();
        if (b == null) {
            if (this.mPlayerManager.i() != null) {
                b = this.mPlayerManager.i().b();
            } else {
                return;
            }
        }
        imageView.setTag(Integer.valueOf(i));
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((Integer) view.getTag()).intValue();
                VideoCardPagerAdapter.this.mClickedViewFavorite = view;
                VideoCardPagerAdapter.this.setFavorite(imageView, b);
            }
        });
        if (b.isLocalMedia()) {
            linearLayout.setVisibility(8);
            return;
        }
        linearLayout.setVisibility(0);
        if (b.isFavorite().booleanValue()) {
            imageView.setImageResource(R.drawable.vector_more_option_favorited);
        } else {
            imageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.vector_more_option_favorite_white));
        }
    }

    public void setFavorite(final ImageView imageView, final Track track) {
        if (track != null) {
            track.setBusinessObjType(BusinessObjectType.Tracks);
            af a = af.a(this.mContext, null);
            a.a("Video Player Screen");
            a.b(track.getBusinessObjId());
            a.a((int) R.id.favoriteMenu, (BusinessObject) track, new a() {
                public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
                    ImageView imageView = imageView;
                    if (track == null || !track.isFavorite().booleanValue()) {
                        imageView.setImageDrawable(VideoCardPagerAdapter.this.mContext.getResources().getDrawable(R.drawable.vector_more_option_favorite_white));
                        return;
                    }
                    imageView.setImageResource(R.drawable.vector_more_option_favorited);
                    if (VideoCardPagerAdapter.this.mClickedViewFavorite != null) {
                        Animation loadAnimation = AnimationUtils.loadAnimation(VideoCardPagerAdapter.this.mContext, R.anim.favorite_tap_animation);
                        loadAnimation.setInterpolator(new com.a.a(0.2d, 20.0d));
                        imageView.startAnimation(loadAnimation);
                    }
                }
            });
        }
    }

    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        viewGroup.removeView((View) obj);
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        this.mViewPager.getCurrentItem();
        ViewGroup viewGroup2 = (ViewGroup) from.inflate(R.layout.card_video_feed, viewGroup, false);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("instantiateItem");
        stringBuilder.append(i);
        stringBuilder.append(" child = ");
        stringBuilder.append(viewGroup2);
        Log.v("gb103", stringBuilder.toString());
        LinearLayout linearLayout = (LinearLayout) viewGroup2.findViewById(R.id.video_controls);
        if (this.isFadeOut) {
            linearLayout.setVisibility(0);
        } else {
            linearLayout.setVisibility(8);
        }
        VideoPlayerView videoPlayerView = (VideoPlayerView) viewGroup2.findViewById(R.id.video_feed_card);
        videoPlayerView.b();
        if (((YouTubeVideo) Util.f((Item) this.mTrackList.get(i))).e() == 2) {
            LayoutParams layoutParams = videoPlayerView.getLayoutParams();
            LayoutParams layoutParams2 = videoPlayerView.getLayoutParams();
            int b = com.services.d.a().b();
            layoutParams2.width = b;
            layoutParams.height = b;
            videoPlayerView.setResizeMode(0);
        } else {
            videoPlayerView.getLayoutParams().height = -1;
            videoPlayerView.getLayoutParams().width = -1;
            videoPlayerView.setResizeMode(3);
        }
        setOptionLayout(i, (LinearLayout) viewGroup2.findViewById(R.id.optionLayout));
        viewGroup.addView(viewGroup2);
        return viewGroup2;
    }

    public void setPlayerFadeout(boolean z) {
        this.isFadeOut = z;
    }

    public int getCount() {
        return this.mTrackList.size();
    }

    public void playVideoOnItemClick(int i, int i2, boolean z) {
        if (z) {
            this.mViewPager.setCurrentItem(i, true);
            resetPlayerArrangement();
        } else if (i < 0 || i > this.mTrackList.size() - 1) {
            aj.a().a(this.mContext, "No video beyond this point");
            if (!(this.currentPlayPauseIcon == null || e.b == null)) {
                ((GaanaActivity) this.mContext).getWindow().clearFlags(128);
                e.b.a(0);
                e.b.r();
                this.currentPlayPauseIcon.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.vector_player_play_white));
            }
        } else {
            this.currentYoutubeVideo = (YouTubeVideo) Util.f((Item) this.mTrackList.get(i));
            if (i2 != -1) {
                this.seekPositionIfAny = i2;
            }
            this.mViewPager.setCurrentItem(i, true);
        }
    }

    public void playVideoOnItemClick(int i, int i2) {
        playVideoOnItemClick(i, i2, false);
    }

    public boolean isPlaying() {
        return e.b != null ? e.b.d() : false;
    }
}
