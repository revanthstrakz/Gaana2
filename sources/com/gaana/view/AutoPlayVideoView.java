package com.gaana.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.dynamicview.DynamicOccasionFragment;
import com.dynamicview.f.a;
import com.exoplayer2.VideoPlayerActivityTwo;
import com.exoplayer2.ui.VideoPlayerAutoPlayView;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.GaanaVideoItem;
import com.gaana.view.autoplay.AutoVideoImage;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaanavideo.FullScreenVideoPlayerActivity;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.URLManager;
import com.managers.ap;
import com.managers.f;
import com.managers.u;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.o;
import com.services.d;
import com.services.l.ay;
import com.services.l.r;
import com.utilities.Util;

public class AutoPlayVideoView extends BaseItemView implements OnClickListener {
    private AutoVideoViewHolder autoVideoViewHolder;
    private TextView bottomText;
    private TextView headerText;
    String impression = "";
    private boolean isFirstCall = true;
    private boolean isGaanaVideo = false;
    private boolean isVideoPlaying = false;
    private GaanaApplication mAppState;
    private a mDynamicView;
    private RelativeLayout mVideoContainer;
    private CrossFadeImageView mVideoPreview;
    private RelativeLayout mVideoPreviewContainer;
    private FrameLayout mVideoPreviewFrameContainer;
    private View mView;
    private CrossFadeImageView play_icon;
    private String shareURL = "";
    private String streamingURL = "";

    public enum GAANA_VIDEO_SOURCE {
        HOME_PAGE,
        OCCASION_PAGE
    }

    public class AutoVideoViewHolder extends ViewHolder {
        private AutoVideoImage aah_vi;
        private TextView headerText;
        private String imageUrl;
        final ImageView img_playback;
        final ImageView img_vol;
        private boolean isLooping = true;
        boolean isMuted;
        private boolean isPaused = false;
        private ProgressBar mProgressBar;
        private String videoUrl;

        public void unmuteVideo() {
        }

        public AutoVideoViewHolder(View view) {
            super(view);
            this.headerText = (TextView) view.findViewById(R.id.f55header.text);
            this.aah_vi = (AutoVideoImage) view.findViewById(R.id.autoVideoImage);
            this.img_playback = (ImageView) view.findViewById(R.id.img_playback);
            this.img_vol = (ImageView) view.findViewById(R.id.img_vol);
            this.mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        }

        public void playVideo() {
            this.aah_vi.getCustomVideoView().k();
            this.aah_vi.getCustomVideoView().g();
        }

        public void videoStarted() {
            this.mProgressBar.setVisibility(8);
            AutoPlayVideoView.this.mVideoContainer.getLayoutParams().width = -1;
            AutoPlayVideoView.this.mVideoContainer.getLayoutParams().height = Util.a(AutoPlayVideoView.this.mContext, (int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            this.aah_vi.getImageView().setVisibility(8);
            this.img_playback.setImageResource(R.drawable.video_pause_icon);
            if (this.isMuted) {
                muteVideo();
                this.img_vol.setImageResource(R.drawable.video_mute);
                return;
            }
            unmuteVideo();
            this.img_vol.setImageResource(R.drawable.video_unmute);
        }

        public void videoPaused() {
            this.mProgressBar.setVisibility(8);
            AutoPlayVideoView.this.mVideoContainer.getLayoutParams().width = -1;
            AutoPlayVideoView.this.mVideoContainer.getLayoutParams().height = Util.a(AutoPlayVideoView.this.mContext, (int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            this.aah_vi.getImageView().setVisibility(8);
            this.img_playback.setImageResource(R.drawable.video_play_icon);
        }

        public void videoLoading() {
            this.mProgressBar.setVisibility(0);
        }

        public void showThumb() {
            this.aah_vi.getImageView().setVisibility(0);
        }

        public void initVideoView(String str, String str2, Activity activity) {
            this.aah_vi.getCustomVideoView().setVisibility(0);
            this.aah_vi.getCustomVideoView().setSource(str);
            this.aah_vi.getCustomVideoView().set_act(activity);
            this.aah_vi.getCustomVideoView().setVideoStateChangeListener(new ay() {
                public void videoStateChanged(int i) {
                    if (i == 1) {
                        AutoVideoViewHolder.this.videoStarted();
                    } else if (i == 0) {
                        AutoVideoViewHolder.this.videoPaused();
                    } else if (i == 2) {
                        AutoVideoViewHolder.this.videoLoading();
                    }
                }

                public void volumeStateChanged(boolean z) {
                    if (z) {
                        AutoVideoViewHolder.this.img_vol.setImageResource(R.drawable.video_mute);
                    } else {
                        AutoVideoViewHolder.this.img_vol.setImageResource(R.drawable.video_mute);
                    }
                    AutoVideoViewHolder.this.isMuted = z;
                }

                public void videoClicked(int i, long j) {
                    AutoPlayVideoView.this.launchVideoPlayerActivity(i, j);
                }

                public void videoStateTransitioned(Bitmap bitmap) {
                    AutoVideoViewHolder.this.getAAH_ImageView().setVisibility(0);
                    AutoVideoViewHolder.this.getAAH_ImageView().setImageBitmap(bitmap);
                }
            });
        }

        public void setLooping(boolean z) {
            this.isLooping = z;
        }

        public void destroyVideo() {
            this.aah_vi.getCustomVideoView().i();
        }

        public void pauseVideo() {
            this.aah_vi.getCustomVideoView().e();
            this.img_playback.setImageResource(R.drawable.video_play_icon);
        }

        public void muteVideo() {
            this.aah_vi.getCustomVideoView().d();
        }

        public AutoVideoImage getAah_vi() {
            return this.aah_vi;
        }

        public ImageView getAAH_ImageView() {
            return this.aah_vi.getImageView();
        }

        public String getImageUrl() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.imageUrl);
            stringBuilder.append("");
            return stringBuilder.toString();
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
            this.aah_vi.getImageView().setVisibility(0);
            this.aah_vi.getCustomVideoView().setVisibility(8);
        }

        public void setAah_vi(AutoVideoImage autoVideoImage) {
            this.aah_vi = autoVideoImage;
        }

        public String getVideoUrl() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.videoUrl);
            stringBuilder.append("");
            return stringBuilder.toString();
        }

        public boolean isPlaying() {
            return this.aah_vi.getCustomVideoView().c();
        }

        public void setVideoUrl(String str) {
            this.videoUrl = str;
        }

        public void setPaused(boolean z) {
            this.isPaused = z;
            this.aah_vi.getCustomVideoView().setPaused(z);
        }

        public boolean isPaused() {
            return this.isPaused;
        }

        public boolean isLooping() {
            return this.isLooping;
        }
    }

    private URLManager getURLManager() {
        URLManager uRLManager = new URLManager();
        uRLManager.a(this.mDynamicView.l());
        uRLManager.a(GaanaVideoItem.class);
        return uRLManager;
    }

    public AutoPlayVideoView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
    }

    public AutoPlayVideoView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
        this.mDynamicView = aVar;
    }

    public AutoPlayVideoView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar, boolean z) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
        this.mDynamicView = aVar;
        this.isGaanaVideo = z;
    }

    public AutoPlayVideoView(Context context, BaseGaanaFragment baseGaanaFragment, AttributeSet attributeSet) {
        super(context, baseGaanaFragment, attributeSet);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        this.mView = LayoutInflater.from(this.mContext).inflate(R.layout.gaana_user_year_view_blank_container, viewGroup, false);
        return this.mView;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (this.mView == null) {
            return viewHolder.itemView;
        }
        this.mView = viewHolder.itemView;
        if (!this.isFirstCall) {
            return this.mView;
        }
        this.isFirstCall = false;
        if (TextUtils.isEmpty(this.mDynamicView.l())) {
            this.mView.setVisibility(8);
        } else {
            setAutoPlayMode();
        }
        setIsToBeRefreshed(false);
        return this.mView;
    }

    public a getDynamicView() {
        return this.mDynamicView;
    }

    public void setFirstCall(boolean z) {
        this.isFirstCall = z;
    }

    public void setIsToBeRefreshed(boolean z) {
        getURLManager().c(Boolean.valueOf(z));
        if (z) {
            View view = this.mView;
        }
    }

    private void setAutoPlayMode() {
        this.streamingURL = this.mDynamicView.l();
        String d = this.mDynamicView.d();
        if (TextUtils.isEmpty(this.streamingURL)) {
            this.autoVideoViewHolder.getAah_vi().setVisibility(8);
            return;
        }
        this.autoVideoViewHolder.headerText.setText(this.mDynamicView.k());
        this.autoVideoViewHolder.setImageUrl(d);
        this.autoVideoViewHolder.setVideoUrl(this.streamingURL);
        i.a().a(d, new r() {
            public void onErrorResponse(VolleyError volleyError) {
            }

            public void onSuccessfulResponse(Bitmap bitmap) {
                AutoPlayVideoView.this.mVideoContainer.getLayoutParams().width = -1;
                AutoPlayVideoView.this.mVideoContainer.getLayoutParams().height = Util.a(AutoPlayVideoView.this.mContext, (int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
                AutoPlayVideoView.this.autoVideoViewHolder.getAAH_ImageView().setImageBitmap(bitmap);
            }
        });
        this.autoVideoViewHolder.setLooping(false);
        this.autoVideoViewHolder.img_playback.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (AutoPlayVideoView.this.autoVideoViewHolder.isPlaying()) {
                    AutoPlayVideoView.this.autoVideoViewHolder.pauseVideo();
                    AutoPlayVideoView.this.autoVideoViewHolder.setPaused(true);
                    return;
                }
                AutoPlayVideoView.this.autoVideoViewHolder.playVideo();
                AutoPlayVideoView.this.autoVideoViewHolder.setPaused(false);
            }
        });
        this.autoVideoViewHolder.isMuted = true;
        this.autoVideoViewHolder.img_vol.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (AutoPlayVideoView.this.autoVideoViewHolder.isMuted) {
                    AutoPlayVideoView.this.autoVideoViewHolder.unmuteVideo();
                    AutoPlayVideoView.this.autoVideoViewHolder.img_vol.setImageResource(R.drawable.video_unmute);
                } else {
                    AutoPlayVideoView.this.autoVideoViewHolder.muteVideo();
                    AutoPlayVideoView.this.autoVideoViewHolder.img_vol.setImageResource(R.drawable.video_mute);
                }
                AutoPlayVideoView.this.autoVideoViewHolder.isMuted ^= 1;
            }
        });
        if (this.autoVideoViewHolder.getVideoUrl() == null) {
            this.autoVideoViewHolder.img_vol.setVisibility(8);
            this.autoVideoViewHolder.img_playback.setVisibility(8);
        } else {
            this.autoVideoViewHolder.img_vol.setVisibility(0);
            this.autoVideoViewHolder.img_playback.setVisibility(0);
        }
        this.autoVideoViewHolder.getAah_vi().setOnClickListener(this);
        this.autoVideoViewHolder.getAah_vi().getCustomVideoView().setOnClickListener(this);
        Constants.de = -1;
        Constants.dd = -1;
        this.autoVideoViewHolder.initVideoView(this.streamingURL, null, (Activity) this.mContext);
        this.autoVideoViewHolder.playVideo();
        this.autoVideoViewHolder.setPaused(false);
        if (TextUtils.isEmpty(this.impression)) {
            this.impression = "impression";
            d = "Inline_Video_Impression";
            if (this.mFragment instanceof DynamicOccasionFragment) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.impression);
                stringBuilder.append("_OP");
                this.impression = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append(d);
                stringBuilder.append("_OP");
                d = stringBuilder.toString();
            }
            u.a().a("Inline_Video", d, "");
        }
    }

    public void onClick(View view) {
        String str;
        StringBuilder stringBuilder;
        int id = view.getId();
        if (!(id == R.id.autoVideoImage || id == R.id.prev_container)) {
            if (id == R.id.share_option_video) {
                str = "Inline_Video_Share";
                if (this.mFragment instanceof DynamicOccasionFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("_OP");
                    str = stringBuilder.toString();
                }
                u.a().a("Inline_Video", str, "");
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                String str2 = this.shareURL;
                intent.putExtra("android.intent.extra.SUBJECT", this.mContext.getString(R.string.my_year_in_gaana));
                intent.putExtra("android.intent.extra.TEXT", str2);
                this.mContext.startActivity(Intent.createChooser(intent, this.mContext.getString(R.string.share_via)));
                return;
            } else if (id != R.id.video_preview_container) {
                return;
            }
        }
        str = "Inline_Video_Click";
        if (this.mFragment instanceof DynamicOccasionFragment) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("_OP");
            str = stringBuilder.toString();
        }
        u.a().a("Inline_Video", str, "");
        launchVideoPlayerActivity();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        i = TextUtils.isEmpty(this.mDynamicView.l()) ^ 1;
        d.a().b("PREFERENCE_YEAR_VIDEO_SESSION_LAUNCH", GaanaApplication.sessionHistoryCount, false);
        if (i != 0) {
            this.mView = LayoutInflater.from(this.mContext).inflate(R.layout.autoplay_video_view, viewGroup, false);
            this.mVideoContainer = (RelativeLayout) this.mView.findViewById(R.id.video_preview_container);
            this.mVideoContainer.getLayoutParams().width = 0;
            this.mVideoContainer.getLayoutParams().height = 0;
            this.isFirstCall = true;
            this.autoVideoViewHolder = new AutoVideoViewHolder(this.mView);
            return this.autoVideoViewHolder;
        }
        this.mView = null;
        return new ItemAdViewHolder(getEmptyLayout());
    }

    private void launchVideoPlayerActivity() {
        launchVideoPlayerActivity(-1, -1);
    }

    private void launchVideoPlayerActivity(int i, long j) {
        if (!Util.j(this.mFragment.getActivity()) || this.mAppState.isAppInOfflineMode()) {
            ap.a().f(this.mContext);
            return;
        }
        Intent intent;
        int ordinal;
        if (GaanaMusicService.t()) {
            o.a(this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
            Constants.dc = true;
        }
        if (f.v().w()) {
            f.v().F();
            Constants.dc = true;
        }
        if (com.utilities.d.g()) {
            intent = new Intent(this.mContext, VideoPlayerActivityTwo.class);
        } else {
            intent = new Intent(this.mContext, FullScreenVideoPlayerActivity.class);
        }
        intent.setAction("com.google.android.exoplayer.demo.action.VIEW");
        intent.putExtra("share_url", this.shareURL);
        intent.putExtra("video_url", this.streamingURL);
        String str = "from_page";
        if (this.mFragment instanceof DynamicOccasionFragment) {
            ordinal = com.gaana.view.GaanaYourYearView.GAANA_VIDEO_SOURCE.OCCASION_PAGE.ordinal();
        } else {
            ordinal = com.gaana.view.GaanaYourYearView.GAANA_VIDEO_SOURCE.HOME_PAGE.ordinal();
        }
        intent.putExtra(str, ordinal);
        if (this.autoVideoViewHolder.getAah_vi().getCustomVideoView() != null) {
            VideoPlayerAutoPlayView customVideoView = this.autoVideoViewHolder.getAah_vi().getCustomVideoView();
            intent.putExtra("seek_index", customVideoView.getResumeWindow());
            intent.putExtra("seek_pos", customVideoView.getResumePosition());
        }
        ((GaanaActivity) this.mContext).startActivityForResult(intent, 1001);
    }
}
