package com.gaana.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.exoplayer2.ui.VideoPlayerAutoPlayView;
import com.fragments.BaseGaanaFragment;
import com.fragments.PlayerFragmentV4;
import com.fragments.SettingsDetailFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.models.BusinessObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.gaana.view.DownloadClickAnimation;
import com.gaana.view.GaanaYourYearView.GAANA_ENTRY_PAGE;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.gaana.view.item.PopupWindowView;
import com.gaana.view.item.SquareImageByHeight;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.gson.internal.LinkedTreeMap;
import com.i.e.b;
import com.i.i;
import com.i.j;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger;
import com.logging.d;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.af;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.ap.a;
import com.managers.f;
import com.managers.u;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.o;
import com.services.l.al;
import com.services.l.as;
import com.services.l.ay;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;
import com.views.RateTextCircularProgressBar;
import com.views.e;
import com.youtube.YouTubePlayerActivity;
import com.youtube.YouTubePlayerActivity.Orientation;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;
import java.util.Iterator;

public class CardPagerAdapterV4 extends Adapter<ViewHolder> implements DownloadPopupListener, a {
    private static final int SWIPE_DOWN_THRESHOLD = 120;
    BusinessObject CFTracksBusinessObj;
    private al ItemClickListener;
    private int NORMAL_CARD = 1;
    private View mClickedViewFavorite;
    private final Context mContext;
    private Fragment mFragment;
    private final GestureDetector mGestureDetector = new GestureDetector(new OnGestureListener() {
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
            if (Math.abs(motionEvent.getY() - motionEvent2.getY()) <= 120.0f || CardPagerAdapterV4.this.mFragment == null || !(CardPagerAdapterV4.this.mFragment instanceof PlayerFragmentV4) || ((PlayerFragmentV4) CardPagerAdapterV4.this.mFragment).k().e()) {
                return false;
            }
            ((GaanaActivity) CardPagerAdapterV4.this.mContext).popBackStackImmediate();
            return true;
        }
    });
    private final PlayerManager mPlayerManager;
    private final OnTouchListener mTouchListener = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return CardPagerAdapterV4.this.mGestureDetector.onTouchEvent(motionEvent);
        }
    };
    private ArrayList<PlayerTrack> mTrackList;
    private RecyclerView mViewPager;
    private ImageView menu_option;
    private OnGlobalLayoutListener onGlobalLayoutListener = new OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            if (CardPagerAdapterV4.this.optionsLayout != null && VERSION.SDK_INT >= 16) {
                CardPagerAdapterV4.this.optionsLayout.getViewTreeObserver().removeOnGlobalLayoutListener(CardPagerAdapterV4.this.onGlobalLayoutListener);
                if (CardPagerAdapterV4.this.optionsLayoutPositionListener != null) {
                    CardPagerAdapterV4.this.optionsLayoutPositionListener.OptionsLayoutPosition(CardPagerAdapterV4.this.optionsLayout);
                }
            }
        }
    };
    private View optionsLayout;
    private IOptionsLayoutPositionListener optionsLayoutPositionListener = null;
    private boolean pagerFirstTime = true;
    private RateTextCircularProgressBar rateTextCircularProgressBar;
    private boolean shouldUpdateList = false;
    private String tappedPosId = "";
    ArrayList<Track> tracksCFData;

    public interface IOptionsLayoutPositionListener {
        void OptionsLayoutPosition(View view);
    }

    public static class CardViewHolder extends ViewHolder {
        private TextView albumText;
        private Button btnVideo;
        public ImageView download_button;
        private SquareImageByHeight image_first;
        private SquareImageByHeight image_fourth;
        private SquareImageByHeight image_second;
        private SquareImageByHeight image_third;
        private CrossFadeImageView imgArtwork;
        private ImageView imgPlayPause;
        private TextView rcText;
        public RelativeLayout recommendationCard;
        public View timeSeekerBlack;
        private TextView trackText;
        private FrameLayout videoDynamicView;

        public CardViewHolder(View view) {
            super(view);
            this.btnVideo = (Button) view.findViewById(R.id.btn_play_video);
            this.imgArtwork = (CrossFadeImageView) view.findViewById(R.id.card_player_image);
            this.videoDynamicView = (FrameLayout) view.findViewById(R.id.video_dynamic_view);
            this.imgPlayPause = (ImageView) view.findViewById(R.id.card_play_icon);
            this.trackText = (TextView) view.findViewById(R.id.trackText);
            this.albumText = (TextView) view.findViewById(R.id.albumText);
            this.rcText = (TextView) view.findViewById(R.id.rcText);
            this.recommendationCard = (RelativeLayout) view.findViewById(R.id.recommendationCard);
            this.timeSeekerBlack = view.findViewById(R.id.timeSeekerBlack);
            this.download_button = (ImageView) view.findViewById(R.id.queue_panel_download_button);
        }
    }

    public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
    }

    public void setOptionsLayoutPositionListener(IOptionsLayoutPositionListener iOptionsLayoutPositionListener) {
        this.optionsLayoutPositionListener = iOptionsLayoutPositionListener;
    }

    public CardPagerAdapterV4(Context context, Fragment fragment, RecyclerView recyclerView, ArrayList<PlayerTrack> arrayList, al alVar) {
        this.mContext = context;
        this.mFragment = fragment;
        this.mPlayerManager = PlayerManager.a(this.mContext);
        this.mTrackList = arrayList;
        this.mViewPager = recyclerView;
        this.ItemClickListener = alVar;
    }

    public void setCFTracksData(Track track) {
        fetchRecommendedTracks(track.getBusinessObjId(), track.getTrackTitle());
    }

    public void setShouldUpdate(boolean z) {
        this.shouldUpdateList = z;
        notifyDataSetChanged();
    }

    public ArrayList<PlayerTrack> getTrackList() {
        return this.mTrackList;
    }

    public int getCount() {
        if (this.mTrackList != null) {
            return !this.shouldUpdateList ? 1 : this.mTrackList.size();
        } else {
            return 0;
        }
    }

    public void updateAndNotifyArrayList(ArrayList<PlayerTrack> arrayList) {
        updateArrayList(arrayList);
        notifyDataSetChanged();
    }

    public void updateArrayList(ArrayList<PlayerTrack> arrayList) {
        this.mTrackList = arrayList;
    }

    private void setTrackDetail(CardViewHolder cardViewHolder, Track track) {
        if (track != null && cardViewHolder.itemView != null && !track.getBusinessObjId().equals(this.mPlayerManager.i().h())) {
            cardViewHolder.imgPlayPause.setVisibility(0);
            cardViewHolder.imgPlayPause.setImageResource(R.drawable.vector_player_play_button_outline);
        } else if (cardViewHolder.itemView != null && !GaanaMusicService.s().l()) {
            cardViewHolder.imgPlayPause.setVisibility(8);
        } else if (cardViewHolder.itemView != null) {
            cardViewHolder.imgPlayPause.setVisibility(8);
        }
        if (track != null && cardViewHolder.btnVideo != null) {
            if (!Constants.cF || TextUtils.isEmpty(track.getYoutubeId())) {
                cardViewHolder.btnVideo.setVisibility(8);
            }
        }
    }

    public void play(PlayerTrack playerTrack, int i) {
        if (i == this.mPlayerManager.s()) {
            o.c(this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
            return;
        }
        u.a().b("BoxQueue", "Song Play");
        if (i > PlayerManager.a(this.mContext).s()) {
            u.a().a("BoxQueue", "Song Play", "Up Next");
        } else if (PlayerManager.a(this.mContext).s() - i > 100) {
            u.a().a("BoxQueue", "Song Play", "History");
        } else {
            u.a().a("BoxQueue", "Song Play", "History Last 100");
        }
        PlayerManager.a(this.mContext).c();
        playerTrack.d(true);
        PlayerManager.a(this.mContext).a(null, playerTrack, i);
        PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
        ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
        this.pagerFirstTime = true;
    }

    public void enqueTrack(BusinessObject businessObject, boolean z, boolean z2) {
        if (!businessObject.isLocalMedia()) {
            if (((PlayerFragmentV4) this.mFragment).j().isAppInOfflineMode() && !DownloadManager.c().l(Integer.parseInt(businessObject.getBusinessObjId())).booleanValue()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This song");
                return;
            } else if (!(Util.j(this.mContext) || DownloadManager.c().l(Integer.parseInt(businessObject.getBusinessObjId())).booleanValue())) {
                ap.a().f(this.mContext);
                return;
            }
        }
        PlayerTrack a = d.a().a((BaseFragment) this.mFragment, businessObject, z2);
        int a2 = PlayerManager.a(this.mContext).a(a, this.mContext, z);
        if (a2 == 1 || !(a2 == -1 || GaanaMusicService.s().isPlaying() || GaanaMusicService.s().l())) {
            PlayerManager.a(this.mContext).a(null, a, 999999);
            PlayerManager.a(this.mContext).a(PlayerType.GAANA);
            PlayerManager.a(this.mContext).e(true);
            PlayerManager.a(this.mContext).j();
            ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
        }
    }

    public void closePlayerScreen() {
        if (this.mFragment != null && (this.mFragment instanceof PlayerFragmentV4) && !((PlayerFragmentV4) this.mFragment).k().e()) {
            ((GaanaActivity) this.mContext).popBackStackImmediate();
        }
    }

    /* Access modifiers changed, original: protected */
    public void launchYouTubePlayer(String str, String str2) {
        if (!Util.j(this.mContext) || GaanaApplication.getInstance().isAppInOfflineMode()) {
            ap.a().f(this.mContext);
            return;
        }
        Intent intent = new Intent(this.mContext, YouTubePlayerActivity.class);
        intent.putExtra("orientation", Orientation.AUTO_START_WITH_LANDSCAPE);
        intent.putExtra("video_id", str);
        intent.putExtra("browser_url", str2);
        if (GaanaMusicService.t()) {
            o.a(this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
            Constants.dc = true;
        }
        if (f.v().w()) {
            f.v().F();
            Constants.dc = true;
        }
        ((Activity) this.mContext).startActivityForResult(intent, 101);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CardViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_adapter_current_v4, viewGroup, false));
    }

    public int getItemViewType(int i) {
        return this.NORMAL_CARD;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:? A:{SYNTHETIC, RETURN, SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x004d A:{SKIP} */
    public void updateDownloadState(android.support.v7.widget.RecyclerView.ViewHolder r11, int r12) {
        /*
        r10 = this;
        r0 = com.gaana.application.GaanaApplication.getContext();
        r0 = com.managers.PlayerManager.a(r0);
        r0 = r0.s();
        r1 = r10.mTrackList;
        if (r1 == 0) goto L_0x0047;
    L_0x0010:
        r1 = r10.mTrackList;
        r1 = r1.size();
        if (r1 <= 0) goto L_0x0047;
    L_0x0018:
        r1 = r10.shouldUpdateList;
        r2 = 1;
        if (r1 != 0) goto L_0x0032;
    L_0x001d:
        r1 = r10.mTrackList;
        r1 = r1.size();
        if (r0 >= r1) goto L_0x0032;
    L_0x0025:
        r1 = r10.mTrackList;
        r0 = r1.get(r0);
        r0 = (com.models.PlayerTrack) r0;
        r0 = r0.a(r2);
        goto L_0x0048;
    L_0x0032:
        r0 = r10.mTrackList;
        r0 = r0.get(r12);
        if (r0 == 0) goto L_0x0047;
    L_0x003a:
        r0 = r10.mTrackList;
        r0 = r0.get(r12);
        r0 = (com.models.PlayerTrack) r0;
        r0 = r0.a(r2);
        goto L_0x0048;
    L_0x0047:
        r0 = 0;
    L_0x0048:
        r1 = r11;
        r1 = (com.gaana.adapter.CardPagerAdapterV4.CardViewHolder) r1;
        if (r0 == 0) goto L_0x008b;
    L_0x004d:
        if (r11 == 0) goto L_0x008b;
    L_0x004f:
        r11 = r11.itemView;
        r1 = 2131297887; // 0x7f09065f float:1.8213732E38 double:1.053001067E-314;
        r1 = r11.findViewById(r1);
        r7 = r1;
        r7 = (android.widget.LinearLayout) r7;
        r8 = 2131298148; // 0x7f090764 float:1.821426E38 double:1.053001196E-314;
        r1 = r7.findViewById(r8);
        r2 = java.lang.Integer.valueOf(r12);
        r1.setTag(r2);
        r9 = new com.gaana.view.DownloadClickAnimation;
        r2 = r10.mContext;
        r1 = r10.mFragment;
        r3 = r1;
        r3 = (com.fragments.PlayerFragmentV4) r3;
        r1 = r7.findViewById(r8);
        r4 = r1;
        r4 = (android.widget.ImageView) r4;
        r1 = r9;
        r5 = r0;
        r6 = r11;
        r1.<init>(r2, r3, r4, r5, r6);
        r1 = r7.findViewById(r8);
        r1 = (android.widget.ImageView) r1;
        r9.changeDownlaodButtonIcon(r0, r1);
        r10.setOptionLayout(r12, r7, r11);
    L_0x008b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.adapter.CardPagerAdapterV4.updateDownloadState(android.support.v7.widget.RecyclerView$ViewHolder, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008f  */
    public void updatePlaybackState(android.support.v7.widget.RecyclerView.ViewHolder r8, int r9) {
        /*
        r7 = this;
        if (r8 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r0 = new android.widget.FrameLayout$LayoutParams;
        r1 = -1;
        r0.<init>(r1, r1);
        r0 = com.gaana.application.GaanaApplication.getContext();
        r0 = com.managers.PlayerManager.a(r0);
        r0 = r0.s();
        r1 = r7.mTrackList;
        r2 = 0;
        r3 = 1;
        if (r1 == 0) goto L_0x006e;
    L_0x001b:
        r1 = r7.mTrackList;
        r1 = r1.size();
        if (r1 <= 0) goto L_0x006e;
    L_0x0023:
        r1 = r7.shouldUpdateList;
        if (r1 != 0) goto L_0x003c;
    L_0x0027:
        r1 = r7.mTrackList;
        r1 = r1.size();
        if (r0 >= r1) goto L_0x003c;
    L_0x002f:
        r1 = r7.mTrackList;
        r1 = r1.get(r0);
        r1 = (com.models.PlayerTrack) r1;
        r1 = r1.a(r3);
        goto L_0x006f;
    L_0x003c:
        r1 = r7.mTrackList;
        r1 = r1.size();
        if (r9 >= r1) goto L_0x0059;
    L_0x0044:
        r1 = r7.mTrackList;
        r1 = r1.get(r9);
        if (r1 == 0) goto L_0x0059;
    L_0x004c:
        r1 = r7.mTrackList;
        r1 = r1.get(r9);
        r1 = (com.models.PlayerTrack) r1;
        r1 = r1.a(r3);
        goto L_0x006f;
    L_0x0059:
        r1 = r7.mTrackList;
        r1 = r1.size();
        if (r0 >= r1) goto L_0x006e;
    L_0x0061:
        r1 = r7.mTrackList;
        r1 = r1.get(r0);
        r1 = (com.models.PlayerTrack) r1;
        r1 = r1.a(r3);
        goto L_0x006f;
    L_0x006e:
        r1 = r2;
    L_0x006f:
        r8 = (com.gaana.adapter.CardPagerAdapterV4.CardViewHolder) r8;
        r7.setTrackDetail(r8, r1);
        r4 = r8.imgArtwork;
        r5 = 0;
        r4.setVisibility(r5);
        r4 = r8.videoDynamicView;
        r6 = 8;
        r4.setVisibility(r6);
        r4 = r8.videoDynamicView;
        r4 = r4.getChildCount();
        if (r4 <= 0) goto L_0x0113;
    L_0x008f:
        r4 = r8.videoDynamicView;
        r4 = r4.getChildAt(r5);
        r4 = r4 instanceof com.google.android.exoplayer2.ui.SimpleExoPlayerView;
        if (r4 == 0) goto L_0x0109;
    L_0x009b:
        r4 = com.player_framework.GaanaMusicService.t();
        if (r4 == 0) goto L_0x00f6;
    L_0x00a1:
        if (r9 != r0) goto L_0x00aa;
    L_0x00a3:
        r9 = com.player_framework.GaanaMusicService.y();
        if (r9 == 0) goto L_0x00aa;
    L_0x00a9:
        goto L_0x00f6;
    L_0x00aa:
        r9 = r8.imgArtwork;
        r9.setVisibility(r5);
        r9 = r8.videoDynamicView;
        r9.setVisibility(r6);
        r9 = r8.videoDynamicView;
        r9.removeAllViews();
        r9 = r1.isLocalMedia();
        if (r9 != 0) goto L_0x00d9;
    L_0x00c5:
        r9 = r7.mContext;
        r0 = r1.getArtworkLarge();
        r9 = com.utilities.Util.f(r9, r0);
        r8 = r8.imgArtwork;
        r0 = android.widget.ImageView.ScaleType.CENTER_CROP;
        r8.bindImage(r1, r9, r0, r3);
        goto L_0x0113;
    L_0x00d9:
        r9 = r7.tappedPosId;
        r0 = r1.getArtwork();
        r9 = r9.equals(r0);
        if (r9 != 0) goto L_0x0113;
    L_0x00e5:
        r8 = r8.imgArtwork;
        r9 = r1.getArtwork();
        r0 = new com.gaana.localmedia.LocalMediaImageLoader;
        r0.<init>();
        r8.bindImageForLocalMedia(r9, r2, r0, r5);
        goto L_0x0113;
    L_0x00f6:
        r9 = r8.videoDynamicView;
        r9 = r9.getChildAt(r5);
        r9 = (com.google.android.exoplayer2.ui.SimpleExoPlayerView) r9;
        r0 = new com.gaana.adapter.CardPagerAdapterV4$2;
        r0.<init>(r8);
        com.player_framework.GaanaMusicService.a(r9, r0);
        goto L_0x0113;
    L_0x0109:
        r8 = r8.videoDynamicView;
        r8 = r8.getChildAt(r5);
        r8 = r8 instanceof com.exoplayer2.ui.VideoPlayerAutoPlayView;
    L_0x0113:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.adapter.CardPagerAdapterV4.updatePlaybackState(android.support.v7.widget.RecyclerView$ViewHolder, int):void");
    }

    private void handlePlayerBackground(final CardViewHolder cardViewHolder, final Track track, int i) {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        switch (i) {
            case -1:
                cardViewHolder.imgArtwork.setVisibility(8);
                cardViewHolder.videoDynamicView.setVisibility(8);
                cardViewHolder.videoDynamicView.removeAllViews();
                return;
            case 0:
                cardViewHolder.imgArtwork.setVisibility(0);
                cardViewHolder.videoDynamicView.setVisibility(8);
                cardViewHolder.videoDynamicView.removeAllViews();
                if (!track.isLocalMedia()) {
                    cardViewHolder.imgArtwork.bindImage((BusinessObject) track, Util.f(this.mContext, track.getArtworkLarge()), ScaleType.CENTER_CROP, true);
                    return;
                } else if (!this.tappedPosId.equals(track.getArtwork())) {
                    cardViewHolder.imgArtwork.bindImageForLocalMedia(track.getArtwork(), null, new LocalMediaImageLoader(), false);
                    return;
                } else {
                    return;
                }
            case 1:
                SimpleExoPlayerView simpleExoPlayerView;
                if (cardViewHolder.videoDynamicView.getChildCount() <= 0) {
                    simpleExoPlayerView = new SimpleExoPlayerView(this.mContext);
                    simpleExoPlayerView.setUseController(false);
                    simpleExoPlayerView.setResizeMode(3);
                    cardViewHolder.videoDynamicView.addView(simpleExoPlayerView, layoutParams);
                } else if (cardViewHolder.videoDynamicView.getChildAt(0) instanceof SimpleExoPlayerView) {
                    simpleExoPlayerView = (SimpleExoPlayerView) cardViewHolder.videoDynamicView.getChildAt(0);
                } else {
                    simpleExoPlayerView = new SimpleExoPlayerView(this.mContext);
                    simpleExoPlayerView.setUseController(false);
                    simpleExoPlayerView.setResizeMode(3);
                    cardViewHolder.videoDynamicView.removeAllViews();
                    cardViewHolder.videoDynamicView.addView(simpleExoPlayerView, layoutParams);
                }
                GaanaMusicService.a(simpleExoPlayerView, new ay() {
                    public void videoClicked(int i, long j) {
                    }

                    public void videoStateTransitioned(Bitmap bitmap) {
                    }

                    public void volumeStateChanged(boolean z) {
                    }

                    public void videoStateChanged(int i) {
                        if (i == 1) {
                            cardViewHolder.imgArtwork.setVisibility(8);
                            cardViewHolder.videoDynamicView.setVisibility(0);
                        }
                    }
                });
                return;
            case 2:
                VideoPlayerAutoPlayView videoPlayerAutoPlayView;
                cardViewHolder.imgArtwork.setVisibility(8);
                cardViewHolder.videoDynamicView.setVisibility(0);
                if (cardViewHolder.videoDynamicView.getChildCount() <= 0) {
                    videoPlayerAutoPlayView = new VideoPlayerAutoPlayView(this.mContext);
                    videoPlayerAutoPlayView.setVideoScalingMode(false);
                    cardViewHolder.videoDynamicView.addView(videoPlayerAutoPlayView, layoutParams);
                } else if (cardViewHolder.videoDynamicView.getChildAt(0) instanceof VideoPlayerAutoPlayView) {
                    videoPlayerAutoPlayView = (VideoPlayerAutoPlayView) cardViewHolder.videoDynamicView.getChildAt(0);
                    videoPlayerAutoPlayView.setVideoScalingMode(false);
                } else {
                    videoPlayerAutoPlayView = new VideoPlayerAutoPlayView(this.mContext);
                    videoPlayerAutoPlayView.setVideoScalingMode(false);
                    cardViewHolder.videoDynamicView.removeAllViews();
                    cardViewHolder.videoDynamicView.addView(videoPlayerAutoPlayView, layoutParams);
                }
                new com.player_framework.d(this.mContext).a(track, new b() {
                    public void onErrorResponse(BusinessObject businessObject) {
                    }

                    public void onDataRetrieved(Object obj, int i, boolean z) {
                        videoPlayerAutoPlayView.setVideoParams(track.getVideoId(), 1);
                        if (obj instanceof String) {
                            videoPlayerAutoPlayView.setSource(Util.k((String) obj));
                        } else if (obj instanceof LinkedTreeMap) {
                            videoPlayerAutoPlayView.setSource(Util.k((String) ((LinkedTreeMap) obj).get("data")));
                        }
                        videoPlayerAutoPlayView.set_act((GaanaActivity) CardPagerAdapterV4.this.mContext);
                        videoPlayerAutoPlayView.setVideoStateChangeListener(new ay() {
                            public void videoClicked(int i, long j) {
                            }

                            public void videoStateTransitioned(Bitmap bitmap) {
                            }

                            public void volumeStateChanged(boolean z) {
                            }

                            public void videoStateChanged(int i) {
                                if (i == 1) {
                                    videoPlayerAutoPlayView.setLooping(true);
                                    videoPlayerAutoPlayView.d();
                                }
                            }
                        });
                        videoPlayerAutoPlayView.g();
                    }
                });
                return;
            default:
                return;
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        boolean z;
        ViewHolder viewHolder2 = viewHolder;
        final int i2 = i;
        int s = PlayerManager.a(GaanaApplication.getContext()).s();
        Track a = s != -1 ? (this.shouldUpdateList || s >= this.mTrackList.size()) ? ((PlayerTrack) this.mTrackList.get(i2)).a(true) : ((PlayerTrack) this.mTrackList.get(s)).a(true) : null;
        CardViewHolder cardViewHolder = (CardViewHolder) viewHolder2;
        if (a != null) {
            View view = viewHolder2.itemView;
            ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.cardView);
            View view2 = cardViewHolder.timeSeekerBlack;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.optionLayout);
            view2 = linearLayout.findViewById(R.id.queue_panel_download_button);
            linearLayout.setVisibility(0);
            view2.setTag(Integer.valueOf(i));
            this.optionsLayout = linearLayout;
            this.optionsLayout.getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
            DownloadClickAnimation downloadClickAnimation = r6;
            int i3 = R.id.queue_panel_download_button;
            DownloadClickAnimation downloadClickAnimation2 = new DownloadClickAnimation(this.mContext, (PlayerFragmentV4) this.mFragment, (ImageView) linearLayout.findViewById(R.id.queue_panel_download_button), a, view);
            downloadClickAnimation.changeDownlaodButtonIcon(a, (ImageView) linearLayout.findViewById(i3));
            setOptionLayout(i2, linearLayout, view);
            view.findViewById(R.id.playerSeekerCardBg);
            this.menu_option = (ImageView) view.findViewById(R.id.menu_option_img);
            this.menu_option.setTag(Integer.valueOf(i));
            this.menu_option.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (((Integer) view.getTag()).intValue() == ((PlayerFragmentV4) CardPagerAdapterV4.this.mFragment).g()) {
                        CardPagerAdapterV4.this.getOptionClickMenu();
                    }
                }
            });
            handlePlayerBackground(cardViewHolder, a, 0);
            if (getCount() < 1) {
                handlePlayerBackground(cardViewHolder, a, 0);
                if (this.ItemClickListener != null) {
                    this.ItemClickListener.a(false);
                }
            } else if (PlayerManager.a(this.mContext).c(a) && s == i2) {
                handlePlayerBackground(cardViewHolder, a, 1);
                if (this.ItemClickListener != null) {
                    this.ItemClickListener.a(true);
                }
            } else if (PlayerManager.a(this.mContext).d(a) && s == i2) {
                handlePlayerBackground(cardViewHolder, a, 2);
                if (this.ItemClickListener != null) {
                    this.ItemClickListener.a(true);
                }
            } else {
                handlePlayerBackground(cardViewHolder, a, 0);
                if (this.ItemClickListener != null) {
                    this.ItemClickListener.a(false);
                }
            }
            this.tappedPosId = a.getArtwork();
            cardViewHolder.imgPlayPause.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (Constants.aa && ((GaanaActivity) CardPagerAdapterV4.this.mContext).isSlidingPanelExpanded()) {
                        u.a().a("Shuffle Product", "Gaana+ popup", "Player");
                        Util.a(CardPagerAdapterV4.this.mContext, BLOCK_ACTION.SKIP);
                    } else if (CardPagerAdapterV4.this.mPlayerManager == null || CardPagerAdapterV4.this.mPlayerManager.m() != PlayerType.GAANA_RADIO) {
                        GaanaLogger.a().a(CardPagerAdapterV4.this.mContext, true, false);
                        CardPagerAdapterV4.this.tappedPosId = "";
                        an.a().a("click", "ac", "", "player", "", "", "", String.valueOf(i2));
                        if (CardPagerAdapterV4.this.ItemClickListener != null) {
                            CardPagerAdapterV4.this.ItemClickListener.a(CardPagerAdapterV4.this.mViewPager.findViewHolderForAdapterPosition(i2), i2);
                        }
                        CardPagerAdapterV4.this.play((PlayerTrack) CardPagerAdapterV4.this.mTrackList.get(i2), i2);
                        CardPagerAdapterV4.this.mViewPager.scrollToPosition(i2);
                        ((PlayerFragmentV4) CardPagerAdapterV4.this.mFragment).q();
                        u.a().a("Player", "Play on artwork", "");
                    }
                }
            });
            viewGroup.setOnTouchListener(new e(this.mContext) {
                public void onSwipeBottom() {
                }

                public void onSwipeLeft() {
                }

                public void onSwipeRight() {
                }

                public void onSwipeTop() {
                }

                public void onTap() {
                    if (CardPagerAdapterV4.this.ItemClickListener != null) {
                        CardPagerAdapterV4.this.ItemClickListener.a(null, i2);
                    }
                    super.onTap();
                }
            });
            setTrackDetail(cardViewHolder, a);
            viewGroup.setTag("PlayerV4");
            view.setTag(a.getBusinessObjId());
            z = false;
        } else {
            z = false;
        }
        this.pagerFirstTime = z;
    }

    private void getOptionClickMenu() {
        BusinessObject b = PlayerManager.a(this.mContext).a(((PlayerFragmentV4) this.mFragment).g()).b();
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

    private void setOptionLayout(int i, LinearLayout linearLayout, View view) {
        setAndUpdateFavoritesV2(i, (ImageView) linearLayout.findViewById(R.id.favourite_item));
        setAddToPlaylist(i, view, (ImageView) linearLayout.findViewById(R.id.menu_add_to_playlist));
        updateVideoIcon(i, (ImageView) linearLayout.findViewById(R.id.video_menu_img));
        View findViewById = view.findViewById(R.id.dark_overlay);
        if (((PlayerFragmentV4) this.mFragment).f()) {
            linearLayout.setVisibility(8);
            linearLayout.setAlpha(0.0f);
            if (findViewById != null) {
                findViewById.setAlpha(0.0f);
                return;
            }
            return;
        }
        linearLayout.setVisibility(0);
        linearLayout.setAlpha(1.0f);
        if (findViewById != null) {
            findViewById.setAlpha(1.0f);
        }
    }

    private void setSimilarSongsData(Track track) {
        setCFTracksData(track);
    }

    private void updateVideoIcon(final int i, ImageView imageView) {
        Object b = (this.mPlayerManager == null || this.mPlayerManager.a(i) == null) ? null : this.mPlayerManager.a(i).b();
        if (b == null) {
            if (this.mPlayerManager.i() != null) {
                b = this.mPlayerManager.i().b();
            } else {
                return;
            }
        }
        int[] iArr = new int[]{R.drawable.vector_player_video_play_white, R.drawable.vector_player_video_play_white_disabled};
        imageView.setTag(b);
        if (!(b == null || imageView == null)) {
            if (!Constants.cF || (TextUtils.isEmpty(b.getVerticalUrl()) && TextUtils.isEmpty(b.getHorizontalUrl()))) {
                imageView.setImageResource(iArr[1]);
                imageView.setClickable(false);
            } else {
                imageView.setImageResource(iArr[0]);
                imageView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (Util.j(CardPagerAdapterV4.this.mContext)) {
                            u.a().b("Player", "Video");
                            Track track = (Track) view.getTag();
                            if (PlayerManager.a(CardPagerAdapterV4.this.mContext).s() == i) {
                                Util.a(CardPagerAdapterV4.this.mContext, YouTubeVideo.a(track), GAANA_ENTRY_PAGE.PLAYER.name(), GaanaMusicService.s().v());
                                return;
                            } else {
                                Util.a(CardPagerAdapterV4.this.mContext, YouTubeVideo.a(track), GAANA_ENTRY_PAGE.PLAYER.name());
                                return;
                            }
                        }
                        aj.a().a(CardPagerAdapterV4.this.mContext, CardPagerAdapterV4.this.mContext.getString(R.string.error_msg_no_connection));
                    }
                });
                imageView.setClickable(true);
                an.a().a("click", "ac", "", "player", "", "video", "", "active");
            }
        }
    }

    private void setAddToPlaylist(int i, View view, ImageView imageView) {
        if (this.mPlayerManager != null && this.mPlayerManager.m() == PlayerType.GAANA) {
            Track b = this.mPlayerManager.a(i).b();
            if (b == null) {
                if (this.mPlayerManager.i() != null) {
                    b = this.mPlayerManager.i().b();
                } else {
                    return;
                }
            }
            imageView.setTag(b.getBusinessObjId());
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    CardPagerAdapterV4.this.mClickedViewFavorite = view;
                    u.a().a("Player", "Add to playlist", "");
                    af.a(CardPagerAdapterV4.this.mContext, null).a((int) R.id.addToPlaylistMenu, b);
                }
            });
            if (b.isLocalMedia()) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
            }
        }
    }

    private void setAndUpdateFavoritesV2(int i, final ImageView imageView) {
        if (this.mPlayerManager != null && this.mPlayerManager.m() == PlayerType.GAANA) {
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
                    if (((Integer) view.getTag()).intValue() == ((PlayerFragmentV4) CardPagerAdapterV4.this.mFragment).g()) {
                        CardPagerAdapterV4.this.mClickedViewFavorite = view;
                        CardPagerAdapterV4.this.setFavorite(imageView, b);
                        if (CardPagerAdapterV4.this.mFragment instanceof PlayerFragmentV4) {
                            ((PlayerFragmentV4) CardPagerAdapterV4.this.mFragment).a(false);
                            ((PlayerFragmentV4) CardPagerAdapterV4.this.mFragment).e();
                            ((PlayerFragmentV4) CardPagerAdapterV4.this.mFragment).d();
                        }
                    }
                }
            });
            if (b.isLocalMedia()) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
                if (b.isFavorite().booleanValue()) {
                    imageView.setImageResource(R.drawable.vector_more_option_favorited);
                } else {
                    imageView.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.vector_more_option_favorite_white));
                }
            }
        }
    }

    public void setFavorite(final ImageView imageView, final Track track) {
        if (track != null) {
            track.setBusinessObjType(BusinessObjectType.Tracks);
            af a = af.a(this.mContext, null);
            a.a("Player Screen");
            a.b(track.getBusinessObjId());
            a.a((int) R.id.favoriteMenu, (BusinessObject) track, new a() {
                public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
                    ImageView imageView = imageView;
                    if (track == null || !track.isFavorite().booleanValue()) {
                        imageView.setImageDrawable(CardPagerAdapterV4.this.mContext.getResources().getDrawable(R.drawable.vector_more_option_favorite_white));
                        an.a().a("click", "ac", "", "player", "", "unfav", "", "");
                        return;
                    }
                    imageView.setImageResource(R.drawable.vector_more_option_favorited);
                    if (CardPagerAdapterV4.this.mClickedViewFavorite != null) {
                        Animation loadAnimation = AnimationUtils.loadAnimation(CardPagerAdapterV4.this.mContext, R.anim.favorite_tap_animation);
                        loadAnimation.setInterpolator(new com.a.a(0.2d, 20.0d));
                        imageView.startAnimation(loadAnimation);
                    }
                    an.a().a("click", "ac", "", "player", "", "fav", "", "");
                }
            });
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private void fetchRecommendedTracks(final String str, final String str2) {
        j.a().a((Object) "CF_API");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://rec.gaana.com/recommendation/recommendedTracks/");
        stringBuilder.append(str);
        com.i.b bVar = new com.i.b(stringBuilder.toString(), Tracks.class, new com.i.e.a() {
            public void onDataRetrieved(Object obj, boolean z) {
                BusinessObject businessObject = (BusinessObject) obj;
                if (businessObject != null) {
                    CardPagerAdapterV4.this.tracksCFData = businessObject.getArrListBusinessObj();
                    Iterator it = CardPagerAdapterV4.this.tracksCFData.iterator();
                    while (it.hasNext()) {
                        ((Track) it.next()).setSeedTrackId(str);
                    }
                    CardPagerAdapterV4.this.mPlayerManager.d(CardPagerAdapterV4.this.tracksCFData);
                    CardPagerAdapterV4.this.mPlayerManager.a(businessObject);
                    CardPagerAdapterV4.this.CFTracksBusinessObj = businessObject;
                    ((PlayerFragmentV4) CardPagerAdapterV4.this.mFragment).t();
                    ((PlayerFragmentV4) CardPagerAdapterV4.this.mFragment).a(businessObject, str2);
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                ((BaseActivity) CardPagerAdapterV4.this.mContext).hideProgressDialog();
            }
        });
        bVar.a("CF_API");
        i.a().a(bVar);
    }

    private int calculatePercentage(int i, int i2) {
        return (i <= 0 || i2 <= 0) ? 0 : (i2 * 100) / i;
    }

    public int getItemCount() {
        if (this.mTrackList != null) {
            return !this.shouldUpdateList ? 1 : this.mTrackList.size();
        } else {
            return 0;
        }
    }

    public void onPopupClicked(String str, BusinessObject businessObject) {
        if (DownloadManager.c().e(Integer.parseInt(str)) == DownloadStatus.DOWNLOADED) {
            deleteDownload(businessObject);
        } else {
            startDownload(businessObject);
        }
    }

    /* Access modifiers changed, original: protected */
    public void startDownload(final BusinessObject businessObject) {
        if (!Util.j(this.mContext)) {
            aj.a().a(this.mContext, this.mContext.getString(R.string.error_msg_no_connection));
        } else if (!businessObject.isLocalMedia()) {
            if (ap.a().o()) {
                af.a(this.mContext, null).a((int) R.id.downloadMenu, businessObject);
            } else {
                ((BaseActivity) this.mContext).hideProgressDialog();
                final BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
                if (!((currentFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) currentFragment).a() == 1)) {
                    String str = "";
                    if (ap.a().l()) {
                        str = businessObject instanceof Track ? "tr" : "pl";
                    }
                    Util.b(this.mContext, str, null, new as() {
                        public void onTrialSuccess() {
                            af.a(CardPagerAdapterV4.this.mContext, null).a((int) R.id.downloadMenu, businessObject);
                            currentFragment.showSnackbartoOpenMyMusic();
                            ((GaanaActivity) CardPagerAdapterV4.this.mContext).updateSideBar();
                        }
                    });
                }
            }
        }
    }

    private void deleteDownload(final BusinessObject businessObject) {
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
                CardPagerAdapterV4.this.notifyItemChanged(((PlayerFragmentV4) CardPagerAdapterV4.this.mFragment).g());
            }
        }).show();
    }
}
