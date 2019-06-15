package com.gaana.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.PlayerFragmentV2;
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
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.gaana.view.item.PopupWindowView;
import com.gaana.view.item.SquareImageByHeight;
import com.i.b;
import com.i.i;
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
import com.managers.an;
import com.managers.ap;
import com.managers.ap.a;
import com.managers.f;
import com.managers.j;
import com.managers.u;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.o;
import com.services.l.al;
import com.services.l.as;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;
import com.views.RateTextCircularProgressBar;
import com.views.e;
import com.youtube.YouTubePlayerActivity;
import com.youtube.YouTubePlayerActivity.Orientation;
import java.util.ArrayList;
import java.util.Iterator;

public class CardPagerAdapterV2 extends Adapter<ViewHolder> implements DownloadPopupListener, a, j.a {
    BusinessObject CFTracksBusinessObj;
    private int CURRENT_CARD = 2;
    private al ItemClickListener;
    private int NORMAL_CARD = 1;
    private DownloadClickAnimation downlaodAnimation;
    private ImageView downloadImage;
    private View mClickedViewFavorite;
    private final Context mContext;
    private Fragment mFragment;
    private final PlayerManager mPlayerManager;
    private ArrayList<PlayerTrack> mTrackList;
    private RecyclerView mViewPager;
    private ImageView menu_option;
    private boolean pagerFirstTime = true;
    private RateTextCircularProgressBar rateTextCircularProgressBar;
    private boolean shouldUpdateList = false;
    ArrayList<Track> tracksCFData;

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
        public LinearLayout llSocialFavorite;
        private TextView rcText;
        public RelativeLayout recommendationCard;
        public View timeSeekerBlack;
        private TextView trackText;

        public CardViewHolder(View view) {
            super(view);
            this.btnVideo = (Button) view.findViewById(R.id.btn_play_video);
            this.llSocialFavorite = (LinearLayout) view.findViewById(R.id.card_player_title_container);
            this.imgArtwork = (CrossFadeImageView) view.findViewById(R.id.card_player_image);
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

    public CardPagerAdapterV2(Context context, Fragment fragment, RecyclerView recyclerView, ArrayList<PlayerTrack> arrayList, al alVar) {
        this.mContext = context;
        this.mFragment = fragment;
        this.mPlayerManager = PlayerManager.a(this.mContext);
        this.mTrackList = arrayList;
        this.mViewPager = recyclerView;
        this.ItemClickListener = alVar;
    }

    public void setCFTracksData() {
        if (this.mPlayerManager.i() == null || this.mPlayerManager.i().b().isLocalMedia) {
            this.tracksCFData = null;
            this.CFTracksBusinessObj = null;
            ((PlayerFragmentV2) this.mFragment).n();
            ((PlayerFragmentV2) this.mFragment).a(false);
            return;
        }
        this.tracksCFData = this.mPlayerManager.G();
        if (this.tracksCFData == null) {
            fetchRecommendedTracks(this.mPlayerManager.i().h(), this.mPlayerManager.i().b().getTrackTitle());
            return;
        }
        this.CFTracksBusinessObj = this.mPlayerManager.H();
        ((PlayerFragmentV2) this.mFragment).a(this.CFTracksBusinessObj, this.mPlayerManager.i().b().getTrackTitle());
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
            cardViewHolder.llSocialFavorite.setVisibility(4);
            cardViewHolder.imgPlayPause.setVisibility(0);
            cardViewHolder.imgPlayPause.setImageResource(R.drawable.vector_circle_play_button_overlay_v2);
        } else if (cardViewHolder.itemView == null || GaanaMusicService.s().l()) {
            if (!(cardViewHolder.itemView == null || cardViewHolder.llSocialFavorite == null)) {
                cardViewHolder.imgPlayPause.setVisibility(8);
            }
        } else if (cardViewHolder.llSocialFavorite != null) {
            cardViewHolder.imgPlayPause.setVisibility(8);
        }
        if (track != null && track.isLocalMedia()) {
            cardViewHolder.llSocialFavorite.setVisibility(4);
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

    public void enqueTrack(BusinessObject businessObject, boolean z, final boolean z2) {
        if (!businessObject.isLocalMedia()) {
            if (((PlayerFragmentV2) this.mFragment).e().isAppInOfflineMode() && !DownloadManager.c().l(Integer.parseInt(businessObject.getBusinessObjId())).booleanValue()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This song");
                return;
            } else if (!(Util.j(this.mContext) || DownloadManager.c().l(Integer.parseInt(businessObject.getBusinessObjId())).booleanValue())) {
                ap.a().f(this.mContext);
                return;
            }
        }
        final PlayerTrack a = d.a().a((BaseFragment) this.mFragment, businessObject, z2);
        int a2 = PlayerManager.a(this.mContext).a(a, this.mContext, z);
        if (a2 == 1 || !(a2 == -1 || GaanaMusicService.s().isPlaying() || GaanaMusicService.s().l())) {
            PlayerManager.a(this.mContext).a(null, a, 999999);
            PlayerManager.a(this.mContext).a(PlayerType.GAANA);
            PlayerManager.a(this.mContext).e(true);
            PlayerManager.a(this.mContext).j();
            ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
        }
        if (z2) {
            this.mViewPager.scrollToPosition(this.mPlayerManager.s() + 1);
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                if (!(CardPagerAdapterV2.this.ItemClickListener == null || CardPagerAdapterV2.this.mPlayerManager.s() + 1 == CardPagerAdapterV2.this.mPlayerManager.s())) {
                    CardPagerAdapterV2.this.ItemClickListener.a(CardPagerAdapterV2.this.mViewPager.findViewHolderForAdapterPosition(CardPagerAdapterV2.this.mPlayerManager.s() + 1), CardPagerAdapterV2.this.mPlayerManager.s() + 1);
                }
                if (z2) {
                    CardPagerAdapterV2.this.play(a, CardPagerAdapterV2.this.mPlayerManager.s() + 1);
                }
            }
        }, 200);
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
        View inflate;
        if (i == this.NORMAL_CARD) {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_adapter_current_v3, viewGroup, false);
        } else {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_adapter_current_v3, viewGroup, false);
        }
        return new CardViewHolder(inflate);
    }

    public int getItemViewType(int i) {
        if (i == PlayerManager.a(this.mContext).s() || ((PlayerFragmentV2) this.mFragment).a()) {
            return this.CURRENT_CARD;
        }
        return this.NORMAL_CARD;
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        BusinessObject a;
        if (this.shouldUpdateList || PlayerManager.a(GaanaApplication.getContext()).s() >= this.mTrackList.size()) {
            a = ((PlayerTrack) this.mTrackList.get(i)).a(true);
        } else {
            a = ((PlayerTrack) this.mTrackList.get(PlayerManager.a(GaanaApplication.getContext()).s())).a(true);
        }
        CardViewHolder cardViewHolder = (CardViewHolder) viewHolder;
        if (a != null) {
            View view = viewHolder.itemView;
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.cardView);
            View view2 = cardViewHolder.timeSeekerBlack;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.optionLayout);
            new DownloadClickAnimation(this.mContext, (PlayerFragmentV2) this.mFragment, (ImageView) linearLayout.findViewById(R.id.queue_panel_download_button), a, view).changeDownlaodButtonIcon(a, (ImageView) linearLayout.findViewById(R.id.queue_panel_download_button));
            setOptionLayout(i, linearLayout, view);
            view.findViewById(R.id.playerSeekerCardBg);
            this.menu_option = (ImageView) view.findViewById(R.id.menu_option_img);
            this.menu_option.setTag(Integer.valueOf(i));
            this.menu_option.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (((Integer) view.getTag()).intValue() == ((PlayerFragmentV2) CardPagerAdapterV2.this.mFragment).c()) {
                        CardPagerAdapterV2.this.getOptionClickMenu();
                    }
                }
            });
            if (i == this.mPlayerManager.s()) {
                relativeLayout.setBackgroundResource(R.drawable.player_card_border_white);
            } else {
                relativeLayout.setBackgroundResource(R.drawable.player_card_border_transparent);
            }
            if (a.isLocalMedia()) {
                cardViewHolder.imgArtwork.bindImageForLocalMedia(a.getArtwork(), null, new LocalMediaImageLoader(), false);
            } else {
                cardViewHolder.imgArtwork.bindImage(a, Util.f(this.mContext, a.getArtworkLarge()), ScaleType.CENTER_CROP);
            }
            cardViewHolder.imgArtwork.setOnTouchListener(new e(this.mContext) {
                public void onSwipeBottom() {
                }

                public void onSwipeLeft() {
                }

                public void onSwipeRight() {
                }

                public void onSwipeTop() {
                }

                public void onTap() {
                    if (Constants.aa && ((GaanaActivity) CardPagerAdapterV2.this.mContext).isSlidingPanelExpanded()) {
                        u.a().a("Shuffle Product", "Gaana+ popup", "Player");
                        Util.a(CardPagerAdapterV2.this.mContext, BLOCK_ACTION.SKIP);
                    } else if (CardPagerAdapterV2.this.mPlayerManager == null || CardPagerAdapterV2.this.mPlayerManager.m() != PlayerType.GAANA_RADIO) {
                        GaanaLogger.a().a(CardPagerAdapterV2.this.mContext, true, false);
                        an.a().a("click", "ac", "", "player", "", "", "", String.valueOf(i));
                        if (!(CardPagerAdapterV2.this.ItemClickListener == null || i == CardPagerAdapterV2.this.mPlayerManager.s())) {
                            CardPagerAdapterV2.this.ItemClickListener.a(CardPagerAdapterV2.this.mViewPager.findViewHolderForAdapterPosition(i), i);
                        }
                        CardPagerAdapterV2.this.play((PlayerTrack) CardPagerAdapterV2.this.mTrackList.get(i), i);
                        CardPagerAdapterV2.this.mViewPager.scrollToPosition(i);
                        ((PlayerFragmentV2) CardPagerAdapterV2.this.mFragment).k();
                        super.onTap();
                    }
                }
            });
            setTrackDetail(cardViewHolder, a);
            relativeLayout.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (Constants.aa && ((GaanaActivity) CardPagerAdapterV2.this.mContext).isSlidingPanelExpanded()) {
                        u.a().a("Shuffle Product", "Gaana+ popup", "Player");
                        Util.a(CardPagerAdapterV2.this.mContext, BLOCK_ACTION.SKIP);
                    } else if (CardPagerAdapterV2.this.mPlayerManager == null || CardPagerAdapterV2.this.mPlayerManager.m() != PlayerType.GAANA_RADIO) {
                        GaanaLogger.a().a(CardPagerAdapterV2.this.mContext, true, false);
                        CardPagerAdapterV2.this.mViewPager.scrollToPosition(i);
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            public void run() {
                                if (!(CardPagerAdapterV2.this.ItemClickListener == null || i == CardPagerAdapterV2.this.mPlayerManager.s())) {
                                    CardPagerAdapterV2.this.ItemClickListener.a(CardPagerAdapterV2.this.mViewPager.findViewHolderForAdapterPosition(i), i);
                                }
                                CardPagerAdapterV2.this.play((PlayerTrack) CardPagerAdapterV2.this.mTrackList.get(i), i);
                            }
                        }, 200);
                    }
                }
            });
            view.setTag(a.getBusinessObjId());
        }
        this.pagerFirstTime = false;
    }

    private void getOptionClickMenu() {
        Track b = PlayerManager.a(this.mContext).a(((PlayerFragmentV2) this.mFragment).c()).b();
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

    private void setOptionLayout(int i, LinearLayout linearLayout, View view) {
        setAndUpdateFavoritesV2(i, (ImageView) linearLayout.findViewById(R.id.favourite_item));
        setAddToPlaylist(i, view);
    }

    private void setAddToPlaylist(int i, View view) {
        if (this.mPlayerManager != null && this.mPlayerManager.m() == PlayerType.GAANA) {
            Track b = this.mPlayerManager.a(i).b();
            if (b == null) {
                if (this.mPlayerManager.i() != null) {
                    b = this.mPlayerManager.i().b();
                } else {
                    return;
                }
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.menu_add_to_playlist);
            imageView.setTag(b.getBusinessObjId());
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    CardPagerAdapterV2.this.mClickedViewFavorite = view;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Song ");
                    stringBuilder.append(b.getBusinessObjId());
                    u.a().a("Add to Playlist", "Player Screen", stringBuilder.toString());
                    af.a(CardPagerAdapterV2.this.mContext, null).a((int) R.id.addToPlaylistMenu, b);
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
                    if (((Integer) view.getTag()).intValue() == ((PlayerFragmentV2) CardPagerAdapterV2.this.mFragment).c()) {
                        CardPagerAdapterV2.this.mClickedViewFavorite = view;
                        CardPagerAdapterV2.this.setFavorite(imageView, b);
                    }
                }
            });
            if (b.isLocalMedia()) {
                imageView.setVisibility(8);
                return;
            }
            imageView.setVisibility(0);
            if (b.isFavorite().booleanValue()) {
                imageView.setImageResource(R.drawable.vector_more_option_favorited);
            } else {
                TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                imageView.setImageDrawable(ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(49, -1)));
                obtainStyledAttributes.recycle();
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
                        TypedArray obtainStyledAttributes = CardPagerAdapterV2.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        imageView.setImageDrawable(ContextCompat.getDrawable(CardPagerAdapterV2.this.mContext, obtainStyledAttributes.getResourceId(49, -1)));
                        obtainStyledAttributes.recycle();
                        an.a().a("click", "ac", "", "player", "", "unfav", "", "");
                        return;
                    }
                    imageView.setImageResource(R.drawable.vector_more_option_favorited);
                    imageView.setPadding(CardPagerAdapterV2.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp10), CardPagerAdapterV2.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp10), CardPagerAdapterV2.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp10), CardPagerAdapterV2.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp10));
                    if (CardPagerAdapterV2.this.mClickedViewFavorite != null) {
                        Animation loadAnimation = AnimationUtils.loadAnimation(CardPagerAdapterV2.this.mContext, R.anim.favorite_tap_animation);
                        loadAnimation.setInterpolator(new com.a.a(0.2d, 20.0d));
                        CardPagerAdapterV2.this.mClickedViewFavorite.startAnimation(loadAnimation);
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
        com.i.j.a().a((Object) "CF_API");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://rec.gaana.com/recommendation/recommendedTracks/");
        stringBuilder.append(str);
        b bVar = new b(stringBuilder.toString(), Tracks.class, new com.i.e.a() {
            public void onDataRetrieved(Object obj, boolean z) {
                BusinessObject businessObject = (BusinessObject) obj;
                if (businessObject != null) {
                    CardPagerAdapterV2.this.tracksCFData = businessObject.getArrListBusinessObj();
                    Iterator it = CardPagerAdapterV2.this.tracksCFData.iterator();
                    while (it.hasNext()) {
                        ((Track) it.next()).setSeedTrackId(str);
                    }
                    CardPagerAdapterV2.this.mPlayerManager.d(CardPagerAdapterV2.this.tracksCFData);
                    CardPagerAdapterV2.this.mPlayerManager.a(businessObject);
                    CardPagerAdapterV2.this.CFTracksBusinessObj = businessObject;
                    CardPagerAdapterV2.this.setCFTracksData();
                    ((PlayerFragmentV2) CardPagerAdapterV2.this.mFragment).n();
                    ((PlayerFragmentV2) CardPagerAdapterV2.this.mFragment).a(businessObject, str2);
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                ((BaseActivity) CardPagerAdapterV2.this.mContext).hideProgressDialog();
            }
        });
        bVar.a("CF_API");
        i.a().a(bVar);
    }

    private int dipToPixels(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public void updateUiForCircularProgressBar(final int i, final int i2) {
        if (this.mContext != null && (this.mContext instanceof Activity)) {
            ((Activity) this.mContext).runOnUiThread(new Runnable() {
                public void run() {
                    if (CardPagerAdapterV2.this.rateTextCircularProgressBar != null) {
                        CardPagerAdapterV2.this.rateTextCircularProgressBar.setProgress(CardPagerAdapterV2.this.calculatePercentage(i, i2));
                    }
                }
            });
        }
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
        if (!businessObject.isLocalMedia()) {
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
                            af.a(CardPagerAdapterV2.this.mContext, null).a((int) R.id.downloadMenu, businessObject);
                            currentFragment.showSnackbartoOpenMyMusic();
                            ((GaanaActivity) CardPagerAdapterV2.this.mContext).updateSideBar();
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
                    return;
                }
                DownloadManager.c().p(Integer.parseInt(businessObjId));
                DownloadManager.c().d(Integer.parseInt(businessObjId));
            }
        }).show();
    }
}
