package com.gaana;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.constants.Constants;
import com.dragpanel.DraggablePanel;
import com.dragpanel.a;
import com.fragments.GridRecommendationFragment;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnFullscreenListener;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.managers.ap;
import com.managers.f;
import com.managers.u;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.o;
import com.services.d;
import com.utilities.Util;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseActivityWithVerticalYoutube extends BaseActivity {
    protected DraggablePanel draggablePanel;
    protected GridRecommendationFragment gridRecommendationFragment;
    protected boolean isFullScreen;
    private String mVideoId;
    protected YouTubePlayerSupportFragment youtubeFragment;
    protected YouTubePlayer youtubePlayer;
    private BusinessObject youtubeVideo;

    public DraggablePanel getDraggablePanel() {
        return this.draggablePanel;
    }

    private void initiatializeDraggablePanel() {
        this.draggablePanel = new DraggablePanel(this);
        this.draggablePanel.setLayoutParams(new LayoutParams(-1, -1));
        this.draggablePanel.setXScaleFactor(2.3f);
        this.draggablePanel.setYScaleFactor(2.3f);
        this.draggablePanel.setTopViewHeight((int) getResources().getDimension(R.dimen.top_fragment_height));
        this.draggablePanel.setTopFragmentMarginBottom((int) getResources().getDimension(R.dimen.top_fragment_margin_bottom));
        this.draggablePanel.setTopFragmentMarginRight((int) getResources().getDimension(R.dimen.top_fragment_margin_right));
        this.draggablePanel.setEnableHorizontalAlphaEffect(false);
        this.draggablePanel.setClickToMaximizeEnabled(true);
    }

    public void launchYoutubePlayer(String str, String str2, BusinessObject businessObject, int i) {
        if (Util.j(this.mContext)) {
            this.mVideoId = str;
            if (i == 0) {
                if (TextUtils.isEmpty(this.mVideoId) && !TextUtils.isEmpty(str2)) {
                    this.mVideoId = extractVideoId(str2);
                }
                if (!TextUtils.isEmpty(this.mVideoId)) {
                    this.youtubeVideo = businessObject;
                    if (this.youtubePlayer == null || this.draggablePanel == null) {
                        initiatializeDraggablePanel();
                        RelativeLayout.LayoutParams layoutParams;
                        if (this.draggablePanel.getParent() == null) {
                            this.frameContainer.addView(this.draggablePanel);
                            layoutParams = (RelativeLayout.LayoutParams) this.draggablePanel.getLayoutParams();
                            layoutParams.setMargins(0, getResources().getDimensionPixelSize(R.dimen.status_bar_height), 0, 0);
                            if (this.overlayExoview != null) {
                                layoutParams.addRule(3, this.overlayExoview.getId());
                            }
                        } else {
                            ((ViewGroup) this.draggablePanel.getParent()).removeView(this.draggablePanel);
                            this.frameContainer.addView(this.draggablePanel);
                            layoutParams = (RelativeLayout.LayoutParams) this.draggablePanel.getLayoutParams();
                            layoutParams.setMargins(0, getResources().getDimensionPixelSize(R.dimen.status_bar_height), 0, 0);
                            if (this.overlayExoview != null) {
                                layoutParams.addRule(3, this.overlayExoview.getId());
                            }
                        }
                        this.draggablePanel.setTranslationY((float) d.a().c());
                        initializeYoutubeFragment();
                        hookDraggablePanelListeners();
                        setupDraggablePanel();
                        ((GaanaActivity) this.mContext).setDrawerLockMode(1);
                        animateDraggablePanel();
                    } else {
                        loadVideoAndPauseMusic();
                        if (this.gridRecommendationFragment != null) {
                            this.gridRecommendationFragment.a(businessObject, true);
                        }
                        this.draggablePanel.c();
                    }
                } else {
                    return;
                }
            } else if ((businessObject instanceof YouTubeVideo) || (businessObject instanceof Track)) {
                Util.a(this.mContext, str2, "", businessObject.getBusinessObjId(), i, ((YouTubeVideo) businessObject).f());
            }
            return;
        }
        ap.a().f(this.mContext);
    }

    public static String extractVideoId(String str) {
        Matcher matcher = Pattern.compile("(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%‌​2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*").matcher(str);
        return matcher.find() ? matcher.group() : null;
    }

    private void doLayout(boolean z) {
        if (this.youtubeFragment != null && this.youtubeFragment.getView() != null && this.draggablePanel != null) {
            LayoutParams layoutParams = this.youtubeFragment.getView().getLayoutParams();
            if (z) {
                layoutParams.height = d.a().c();
                this.draggablePanel.a(d.a().c());
            } else {
                layoutParams.height = (int) getResources().getDimension(R.dimen.top_fragment_height);
                this.draggablePanel.a((int) getResources().getDimension(R.dimen.top_fragment_height));
            }
        }
    }

    private void initializeYoutubeFragment() {
        this.youtubeFragment = new YouTubePlayerSupportFragment();
        this.youtubeFragment.initialize(getResources().getString(R.string.youtube_api_key), new OnInitializedListener() {
            public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }

            public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean z) {
                if (!z) {
                    BaseActivityWithVerticalYoutube.this.youtubePlayer = youTubePlayer;
                    BaseActivityWithVerticalYoutube.this.loadVideoAndPauseMusic();
                    BaseActivityWithVerticalYoutube.this.youtubePlayer.setShowFullscreenButton(true);
                    BaseActivityWithVerticalYoutube.this.youtubePlayer.setFullscreenControlFlags(7);
                    BaseActivityWithVerticalYoutube.this.youtubePlayer.setOnFullscreenListener(new OnFullscreenListener() {
                        public void onFullscreen(boolean z) {
                            BaseActivityWithVerticalYoutube.this.isFullScreen = z;
                            if (z) {
                                BaseActivityWithVerticalYoutube.this.setRequestedOrientation(0);
                                u.a().b("VideoPlayerEvents", "Full Screen");
                            } else {
                                BaseActivityWithVerticalYoutube.this.setRequestedOrientation(1);
                                u.a().b("VideoPlayerEvents", "Back to Normal Screen");
                            }
                            BaseActivityWithVerticalYoutube.this.doLayout(z);
                        }
                    });
                }
            }
        });
    }

    private void setupDraggablePanel() {
        this.draggablePanel.setFragmentManager(((AppCompatActivity) this.mContext).getSupportFragmentManager());
        this.draggablePanel.setTopFragment(this.youtubeFragment);
        this.gridRecommendationFragment = new GridRecommendationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("BUSINESS_OBJECT", this.youtubeVideo);
        this.gridRecommendationFragment.setArguments(bundle);
        this.draggablePanel.setBottomFragment(this.gridRecommendationFragment);
        this.draggablePanel.e();
        this.draggablePanel.b();
    }

    private void hookDraggablePanelListeners() {
        this.draggablePanel.setDraggableListener(new a() {
            public void onMaximized() {
                u.a().b("VideoPlayerEvents", "Maximize Video");
                ((GaanaActivity) BaseActivityWithVerticalYoutube.this.mContext).setDrawerLockMode(1);
                if (BaseActivityWithVerticalYoutube.this.youtubePlayer != null) {
                    BaseActivityWithVerticalYoutube.this.youtubePlayer.setFullscreenControlFlags(7);
                    if (BaseActivityWithVerticalYoutube.this.mContext instanceof GaanaActivity) {
                        ((GaanaActivity) BaseActivityWithVerticalYoutube.this.mContext).showHideVoiceCoachMark(R.id.voice_search_coachmark, false);
                    }
                }
            }

            public void onMinimized() {
                u.a().b("VideoPlayerEvents", "Minimize Video");
                ((GaanaActivity) BaseActivityWithVerticalYoutube.this.mContext).setDrawerLockMode(0);
                if (BaseActivityWithVerticalYoutube.this.youtubePlayer != null) {
                    BaseActivityWithVerticalYoutube.this.youtubePlayer.setFullscreenControlFlags(2);
                }
            }

            public void onClosedToLeft() {
                ((GaanaActivity) BaseActivityWithVerticalYoutube.this.mContext).setDrawerLockMode(0);
                u.a().a("VideoPlayerEvents", "Dismiss Video", "onClosedToLeft");
                BaseActivityWithVerticalYoutube.this.pauseVideoAndReset();
                BaseActivityWithVerticalYoutube.this.setRequestedOrientation(1);
            }

            public void onClosedToRight() {
                ((GaanaActivity) BaseActivityWithVerticalYoutube.this.mContext).setDrawerLockMode(0);
                u.a().a("VideoPlayerEvents", "Dismiss Video", "onClosedToRight");
                BaseActivityWithVerticalYoutube.this.pauseVideoAndReset();
                BaseActivityWithVerticalYoutube.this.setRequestedOrientation(1);
            }
        });
    }

    private void loadVideoAndPauseMusic() {
        this.youtubePlayer.loadVideo(this.mVideoId);
        if (GaanaMusicService.t()) {
            o.a((Context) this, PauseReasons.MEDIA_BUTTON_TAP);
            Constants.dc = true;
        }
        if (f.v().w()) {
            f.v().F();
            Constants.dc = true;
        }
    }

    private void pauseVideoAndReset() {
        pauseVideo();
        this.frameContainer.removeView(this.draggablePanel);
        this.youtubePlayer = null;
        this.youtubeFragment = null;
        if (Constants.dc) {
            o.c((Context) this, PauseReasons.MEDIA_BUTTON_TAP);
            Constants.dc = false;
        }
    }

    private void pauseVideo() {
        if (this.youtubePlayer != null && this.youtubePlayer.isPlaying()) {
            this.youtubePlayer.release();
            this.youtubePlayer = null;
        }
    }

    private void playVideo() {
        if (this.youtubePlayer != null && !this.youtubePlayer.isPlaying()) {
            this.youtubePlayer.play();
        }
    }

    private void animateDraggablePanel() {
        if (this.draggablePanel != null) {
            this.draggablePanel.setVisibility(0);
            this.draggablePanel.bringToFront();
            this.draggablePanel.animate().translationY(0.0f).setListener(new AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    if ((BaseActivityWithVerticalYoutube.this.mContext instanceof GaanaActivity) && ((GaanaActivity) BaseActivityWithVerticalYoutube.this.mContext).isPlayerExpanded()) {
                        ((GaanaActivity) BaseActivityWithVerticalYoutube.this.mContext).popBackStackImmediate();
                    }
                }
            }).setDuration(1000).start();
        }
    }
}
