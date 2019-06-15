package com.gaana.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.dynamicview.DynamicOccasionFragment;
import com.dynamicview.f.a;
import com.exoplayer2.VideoPlayerActivityTwo;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.GaanaVideoItem;
import com.gaana.models.YearVideoItem;
import com.gaana.view.item.BaseItemView.DetailListingItemHolder;
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
import com.services.l.r;
import com.services.l.s;
import com.utilities.Util;

public class GaanaYourYearView extends BaseItemView implements OnClickListener {
    private TextView bottomText;
    private TextView headerText;
    String impression = "";
    private boolean isFirstCall = true;
    private boolean isGaanaVideo = false;
    private boolean isVideoPlaying = false;
    private GaanaApplication mAppState;
    private a mDynamicView;
    private CrossFadeImageView mVideoPreview;
    private RelativeLayout mVideoPreviewContainer;
    private FrameLayout mVideoPreviewFrameContainer;
    private View mView;
    private CrossFadeImageView play_icon;
    private String shareURL = "";
    private String streamingURL = "";

    public enum GAANA_ENTRY_PAGE {
        OTHERS,
        HOME_ACTIONBAR,
        SEARCH_FEED,
        VIDEO_FEED,
        EXPLORE,
        PLAYER,
        RADIO_PLAYER,
        DEEP_LINK
    }

    public enum GAANA_VIDEO_SOURCE {
        HOME_PAGE,
        OCCASION_PAGE
    }

    public GaanaYourYearView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
    }

    public GaanaYourYearView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
        this.mDynamicView = aVar;
    }

    public GaanaYourYearView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar, boolean z) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
        this.mDynamicView = aVar;
        this.isGaanaVideo = z;
    }

    public GaanaYourYearView(Context context, BaseGaanaFragment baseGaanaFragment, AttributeSet attributeSet) {
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
        retrieveFeedItem(getURLManager());
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
        URLManager uRLManager = getURLManager();
        uRLManager.c(Boolean.valueOf(z));
        if (z && this.mView != null) {
            retrieveFeedItem(uRLManager);
        }
    }

    private void retrieveFeedItem(URLManager uRLManager) {
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject == null) {
                    return;
                }
                if (businessObject instanceof YearVideoItem) {
                    YearVideoItem yearVideoItem = (YearVideoItem) businessObject;
                    GaanaYourYearView.this.streamingURL = yearVideoItem.getVideoStreamingUrl();
                    GaanaYourYearView.this.shareURL = yearVideoItem.getVideoShareUrl();
                    if (!TextUtils.isEmpty(GaanaYourYearView.this.streamingURL)) {
                        ((ViewGroup) GaanaYourYearView.this.mView).removeAllViews();
                        LayoutInflater from = LayoutInflater.from(GaanaYourYearView.this.mContext);
                        if (GaanaYourYearView.this.mFragment instanceof DynamicOccasionFragment) {
                            from.inflate(R.layout.gaana_user_year_view_music_year_fragment, (ViewGroup) GaanaYourYearView.this.mView, true);
                        } else {
                            from.inflate(R.layout.gaana_user_year_view_home_fragment, (ViewGroup) GaanaYourYearView.this.mView, true);
                        }
                        GaanaYourYearView.this.mVideoPreviewContainer = (RelativeLayout) GaanaYourYearView.this.mView.findViewById(R.id.video_preview_container);
                        GaanaYourYearView.this.mVideoPreview = (CrossFadeImageView) GaanaYourYearView.this.mView.findViewById(R.id.user_video_container);
                        LinearLayout linearLayout = (LinearLayout) GaanaYourYearView.this.mView.findViewById(R.id.share_option_video);
                        if (GaanaYourYearView.this.mVideoPreviewContainer != null) {
                            GaanaYourYearView.this.mVideoPreviewContainer.setOnClickListener(GaanaYourYearView.this);
                        }
                        if (linearLayout != null) {
                            linearLayout.setOnClickListener(GaanaYourYearView.this);
                        }
                    }
                } else if (businessObject instanceof GaanaVideoItem) {
                    GaanaVideoItem gaanaVideoItem = (GaanaVideoItem) businessObject;
                    GaanaYourYearView.this.streamingURL = gaanaVideoItem.getVideoStreamingUrl();
                    GaanaYourYearView.this.shareURL = gaanaVideoItem.getVideoShareUrl();
                    String d = GaanaYourYearView.this.mDynamicView.d();
                    String header = gaanaVideoItem.getHeader();
                    if (!TextUtils.isEmpty(GaanaYourYearView.this.streamingURL)) {
                        ((ViewGroup) GaanaYourYearView.this.mView).removeAllViews();
                        LayoutInflater.from(GaanaYourYearView.this.mContext).inflate(R.layout.gaana_video_view, (ViewGroup) GaanaYourYearView.this.mView, true);
                        GaanaYourYearView.this.mVideoPreviewFrameContainer = (FrameLayout) GaanaYourYearView.this.mView.findViewById(R.id.prev_container);
                        GaanaYourYearView.this.mVideoPreview = (CrossFadeImageView) GaanaYourYearView.this.mView.findViewById(R.id.user_video_container);
                        GaanaYourYearView.this.play_icon = (CrossFadeImageView) GaanaYourYearView.this.mView.findViewById(R.id.btn_play_video);
                        if (!TextUtils.isEmpty(header)) {
                            ((TextView) GaanaYourYearView.this.mView.findViewById(R.id.head_text)).setText(header);
                        }
                        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) GaanaYourYearView.this.mView.findViewById(R.id.share_option_video);
                        if (Constants.l) {
                            crossFadeImageView.setImageResource(R.drawable.icon_share_new_white);
                        }
                        if (GaanaYourYearView.this.mVideoPreviewFrameContainer != null) {
                            GaanaYourYearView.this.mVideoPreviewFrameContainer.setOnClickListener(GaanaYourYearView.this);
                        }
                        if (crossFadeImageView != null) {
                            crossFadeImageView.setOnClickListener(GaanaYourYearView.this);
                        }
                        if (!TextUtils.isEmpty(d)) {
                            i.a().a(d, new r() {
                                public void onErrorResponse(VolleyError volleyError) {
                                }

                                public void onSuccessfulResponse(Bitmap bitmap) {
                                    GaanaYourYearView.this.mVideoPreview.setAdjustViewBounds(true);
                                    GaanaYourYearView.this.mVideoPreview.setScaleType(ScaleType.FIT_XY);
                                    GaanaYourYearView.this.mVideoPreview.setImageBitmap(bitmap);
                                }
                            });
                        }
                        if (TextUtils.isEmpty(GaanaYourYearView.this.impression)) {
                            GaanaYourYearView.this.impression = "impression";
                            header = "YIM_Video_Impression";
                            if (GaanaYourYearView.this.mFragment instanceof DynamicOccasionFragment) {
                                GaanaYourYearView gaanaYourYearView = GaanaYourYearView.this;
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(GaanaYourYearView.this.impression);
                                stringBuilder.append("_OP");
                                gaanaYourYearView.impression = stringBuilder.toString();
                                StringBuilder stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(header);
                                stringBuilder2.append("_OP");
                                header = stringBuilder2.toString();
                            }
                            u.a().a("YIM_Video", header, "");
                        }
                    }
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                GaanaYourYearView.this.isFirstCall = true;
            }
        }, uRLManager);
    }

    private URLManager getURLManager() {
        URLManager uRLManager = new URLManager();
        uRLManager.a(this.mDynamicView.l());
        if (this.isGaanaVideo) {
            uRLManager.a(GaanaVideoItem.class);
        } else {
            uRLManager.a(YearVideoItem.class);
        }
        return uRLManager;
    }

    public void onClick(View view) {
        String str;
        StringBuilder stringBuilder;
        int id = view.getId();
        if (id != R.id.prev_container) {
            if (id == R.id.share_option_video) {
                str = "YIM_Video_Share";
                if (this.mFragment instanceof DynamicOccasionFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("_OP");
                    str = stringBuilder.toString();
                }
                u.a().a("YIM_Video", str, "");
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
        str = "YIM_Video_Click";
        if (this.mFragment instanceof DynamicOccasionFragment) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("_OP");
            str = stringBuilder.toString();
        }
        u.a().a("YIM_Video", str, "");
        launchVideoPlayerActivity();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        i = TextUtils.isEmpty(this.mDynamicView.l()) ^ 1;
        int b = d.a().b("PREFERENCE_YEAR_VIDEO_SESSION_LAUNCH", GaanaApplication.sessionHistoryCount, false);
        if (i == 0) {
            this.mView = null;
            return new ItemAdViewHolder(getEmptyLayout());
        } else if ((this.mFragment instanceof DynamicOccasionFragment) || GaanaApplication.sessionHistoryCount - b <= 5) {
            return new DetailListingItemHolder(getNewView(0, viewGroup));
        } else {
            this.mView = null;
            return new ItemAdViewHolder(getEmptyLayout());
        }
    }

    private void launchVideoPlayerActivity() {
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
            ordinal = GAANA_VIDEO_SOURCE.OCCASION_PAGE.ordinal();
        } else {
            ordinal = GAANA_VIDEO_SOURCE.HOME_PAGE.ordinal();
        }
        intent.putExtra(str, ordinal);
        ((GaanaActivity) this.mContext).startActivityForResult(intent, 1001);
    }
}
