package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.collapsible_header.ListingFragmentMaterial;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.Constants;
import com.constants.c;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.DynamicOccasionFragment;
import com.fragments.ActivityFeedActivityFragment;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.ArtistDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.fragments.DiscoverDetailFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.DownloadHomeFragment;
import com.fragments.FavoritesFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.fragments.GridActivityFragment;
import com.fragments.ItemListingFragment;
import com.fragments.ListingFragment;
import com.fragments.LocalMediaFragment;
import com.fragments.MyMusicFragment;
import com.fragments.MyMusicItemFragment;
import com.fragments.PersonaDedicationFragment;
import com.fragments.PersonaDetailsFragment;
import com.fragments.PlayerFragmentV4;
import com.fragments.ProfileFragment;
import com.fragments.RadioActivityFragment;
import com.fragments.RadioDetailsMaterialListing;
import com.fragments.SearchFragment;
import com.fragments.SearchTabFragment;
import com.fragments.SettingsDetailFragment;
import com.fragments.TabbedFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Playlists.Playlist.PlaylistSourceType;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.gaana.models.UserMessage;
import com.gaana.view.PulsatorView;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.i.i;
import com.library.controls.CircularImageView;
import com.library.controls.CrossFadeImageView;
import com.library.custom_glide.GlideFileLoader;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.aj;
import com.managers.ap;
import com.managers.q;
import com.managers.u;
import com.models.ListingComponents;
import com.services.d;
import com.services.f;
import com.services.f.b;
import com.services.l.as;
import com.services.l.s;
import com.services.l.v;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class BaseItemView extends LinearLayout implements OnClickListener {
    private int count = 0;
    protected boolean isPlayerQueue = false;
    protected GaanaApplication mAppState;
    protected BusinessObject mBusinessObject;
    protected Context mContext = null;
    protected BaseGaanaFragment mFragment;
    protected LayoutInflater mInflater = null;
    protected int mLayoutId = -1;
    protected ListingComponents mListingComponents;
    private String mSearchQuery;
    protected View mView;

    public static class ActivityListHolder extends ViewHolder {
        public CrossFadeImageView crossFadeImageView;
        public TextView listItemDesc;
        public TextView listItemName;
        public View mView;

        public ActivityListHolder(View view) {
            super(view);
            this.mView = view;
            this.crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.itemImg);
            this.listItemName = (TextView) view.findViewById(R.id.itemName);
            this.listItemDesc = (TextView) view.findViewById(R.id.itemDesc);
        }
    }

    public static class CuratedDownloadSongSelectionHolder extends ViewHolder {
        public CrossFadeImageView crossFadeImageView;
        public FrameLayout crossFadeImageViewLyt;
        public ImageView mThumbnailRightTopIndicator;
        public View mView;
        public SquareView overlayView;
        public RelativeLayout parentEmptyLayout;
        public ImageView select_icon;
        public TextView tvBottomHeading;
        public TextView tvTopHeading;

        public CuratedDownloadSongSelectionHolder(View view) {
            super(view);
            this.mView = view;
            this.parentEmptyLayout = (RelativeLayout) view.findViewById(R.id.rl_empty_item_view);
            this.crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.overlayView = (SquareView) view.findViewById(R.id.overlay);
            this.select_icon = (ImageView) view.findViewById(R.id.select_icon);
            this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
            this.tvBottomHeading = (TextView) view.findViewById(R.id.tvBottomHeading);
            this.mThumbnailRightTopIndicator = (ImageView) view.findViewById(R.id.indicatorIconRightTop);
            this.crossFadeImageViewLyt = (FrameLayout) view.findViewById(R.id.f39download.item.img.thumb.container);
        }
    }

    public static class DetailListingHeaderHolder extends ViewHolder {
        public LinearLayout headerContainer;

        public DetailListingHeaderHolder(View view) {
            super(view);
            this.headerContainer = (LinearLayout) view.findViewById(R.id.ll_header_container);
        }
    }

    public static class DetailListingItemHolder extends ViewHolder {
        public DetailListingItemHolder(View view) {
            super(view);
        }
    }

    public static class EmptyMessageHolder extends ViewHolder {
        public TextView emptyMessageText;

        public EmptyMessageHolder(View view) {
            super(view);
            this.emptyMessageText = (TextView) view.findViewById(R.id.tvUserMsg);
        }
    }

    public static class FriendsActivityListHolder extends ViewHolder {
        public CrossFadeImageView imgUser;
        public View mView;
        public TextView tvDurationAgo;
        public TextView tvItemName;
        public LinearLayout userImagesMultiple;

        public FriendsActivityListHolder(View view) {
            super(view);
            this.mView = view;
            this.userImagesMultiple = (LinearLayout) view.findViewById(R.id.userImagesMultiple);
            this.imgUser = (CrossFadeImageView) view.findViewById(R.id.imgUser);
            this.tvItemName = (TextView) view.findViewById(R.id.tvItemName);
            this.tvDurationAgo = (TextView) view.findViewById(R.id.tvDurationAgo);
        }
    }

    public static class GaanaSpecialHolder extends ViewHolder {
        public TextView mAbout;
        public LinearLayout mContainer;
        public TextView mDescription;
        public SwitchCompat mSwitch;

        public GaanaSpecialHolder(View view) {
            super(view);
            this.mContainer = (LinearLayout) view.findViewById(R.id.view_gaana_special_holder_container);
            this.mAbout = (TextView) view.findViewById(R.id.about_the_show);
            this.mDescription = (TextView) view.findViewById(R.id.detail_description);
            this.mSwitch = (SwitchCompat) view.findViewById(R.id.switchButton);
        }

        public void setVisibility(boolean z) {
            LayoutParams layoutParams = (LayoutParams) this.itemView.getLayoutParams();
            this.itemView.getContext();
            if (z) {
                layoutParams.height = -2;
                layoutParams.width = -1;
                this.itemView.setVisibility(0);
            } else {
                this.itemView.setVisibility(8);
                layoutParams.height = 0;
                layoutParams.width = 0;
            }
            this.itemView.setLayoutParams(layoutParams);
        }
    }

    public static class GridItemHolder extends ViewHolder {
        public TextView albumName;
        public CrossFadeImageView imageViewThumb;
        public CrossFadeImageView imageViewThumbRect;
        @Nullable
        public View mImgGradient;
        public ImageView mImgIndicator;
        public ImageView mThumbnailRightTopIndicator;
        public TextView mTxtPlayCount;
        public RelativeLayout parentLayout;
        public ImageView play_icon;
        public TextView tvName;
        public TextView tvSectionTitle;
        public TextView txtHeaderName;

        public GridItemHolder(View view) {
            super(view);
            this.parentLayout = (RelativeLayout) view.findViewById(R.id.view_grid_item_relative);
            this.imageViewThumb = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.imageViewThumbRect = (CrossFadeImageView) view.findViewById(R.id.imgProductIconRect);
            this.tvName = (TextView) view.findViewById(R.id.f54grid.item.tv.name);
            this.play_icon = (ImageView) view.findViewById(R.id.play_icon);
            this.mImgIndicator = (ImageView) view.findViewById(R.id.img_indicator);
            this.tvSectionTitle = (TextView) view.findViewById(R.id.tv_section_title);
            this.txtHeaderName = (TextView) view.findViewById(R.id.txt_header);
            this.mImgGradient = view.findViewById(R.id.viewProductIconOverlay);
            this.mThumbnailRightTopIndicator = (ImageView) view.findViewById(R.id.indicatorIconRightTop);
            this.mTxtPlayCount = (TextView) view.findViewById(R.id.txt_play_count);
        }
    }

    public static class ItemAdViewHolder extends ViewHolder {
        public ItemAdViewHolder(View view) {
            super(view);
        }
    }

    public static class ItemEmptyMessageHolder extends ViewHolder {
        public ItemEmptyMessageHolder(View view) {
            super(view);
        }
    }

    public static class ItemNormalViewHolder extends ViewHolder {
        public ItemNormalViewHolder(View view) {
            super(view);
        }
    }

    public static class MoreInfoListingItemHolder extends ViewHolder {
        public ImageView favoriteItem;
        public CircularImageView itemImg;
        public View mView;
        public TextView title;

        public MoreInfoListingItemHolder(View view) {
            super(view);
            this.mView = view;
            this.favoriteItem = (ImageView) view.findViewById(R.id.favourite_item);
            this.title = (TextView) view.findViewById(R.id.title);
            this.itemImg = (CircularImageView) view.findViewById(R.id.itemImg);
        }
    }

    public static class MyMusicItemViewHolder extends ViewHolder {
        public ImageView mImgIcon;
        public TextView mTxtCount;
        public TextView mTxtLabel;

        public MyMusicItemViewHolder(View view) {
            super(view);
            this.mTxtLabel = (TextView) view.findViewById(R.id.txt_name);
            this.mImgIcon = (ImageView) view.findViewById(R.id.img_artwork);
            this.mTxtCount = (TextView) view.findViewById(R.id.txt_downloads_count);
        }
    }

    class OnBusinessObjectRetrievedDownload implements s {
        private BusinessObject parentBusinessObject = null;

        public void onErrorResponse(BusinessObject businessObject) {
        }

        OnBusinessObjectRetrievedDownload() {
        }

        public void setParentBusinessObject(BusinessObject businessObject) {
            this.parentBusinessObject = businessObject;
        }

        public void onRetreivalComplete(BusinessObject businessObject) {
            ((BaseActivity) BaseItemView.this.mContext).hideProgressDialog();
            if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                aj.a().a(BaseItemView.this.mContext, BaseItemView.this.mContext.getResources().getString(R.string.toast_no_tracks_to_download));
            } else {
                ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                for (int size = arrListBusinessObj.size() - 1; size >= 0; size--) {
                    Track track = (Track) arrListBusinessObj.get(size);
                    if (track.getIslocal() != null && track.getIslocal().equals("1")) {
                        track = LocalMediaManager.getInstance(BaseItemView.this.mContext).getLocalTrackFromHash(track.getBusinessObjId());
                        arrListBusinessObj.remove(size);
                        if (track != null) {
                            arrListBusinessObj.add(size, track);
                        }
                    }
                }
                this.parentBusinessObject.setArrListBusinessObj(arrListBusinessObj);
                if (this.parentBusinessObject instanceof Playlist) {
                    Tracks tracks = (Tracks) businessObject;
                    ((Playlist) this.parentBusinessObject).setFavoriteCount(tracks.getFavoriteCount());
                    ((Playlist) this.parentBusinessObject).setLastModifiedDate(tracks.getModifiedOn());
                } else if (this.parentBusinessObject instanceof Album) {
                    ((Album) this.parentBusinessObject).setFavoriteCount(((Tracks) businessObject).getFavoriteCount());
                }
                DownloadManager.c().a(this.parentBusinessObject, BaseItemView.this.mContext);
            }
            BaseItemView.this.updateOfflineTracksStatus(false);
        }
    }

    public static class PlaylistGridHolder extends ViewHolder {
        public CrossFadeImageView crossFadeImageView;
        public FrameLayout crossFadeImageViewLyt;
        public ImageView imgLightOverlay;
        public ImageView mImgIndictor;
        @Nullable
        public View mOverlayBg;
        public ImageView mThumbnailRightTopIndicator;
        public TextView mTxtPlayCount;
        public View mView;
        public FrameLayout parentEmptyLayout;
        public ImageView play_icon;
        public LinearLayout profileLikesImage;
        public View shadowLayer;
        public ImageView shareIcon;
        public FrameLayout tvDownloadItemView;
        public TextView tvHeadingDownloadItem;
        public TextView tvSectionTitle;
        public TextView tvSubHeader;
        public TextView tvTopHeading;
        public TextView tvTopHeadingMix;

        public PlaylistGridHolder(View view) {
            super(view);
            this.mView = view;
            this.tvDownloadItemView = (FrameLayout) view.findViewById(R.id.download_track_item_view);
            this.tvSubHeader = (TextView) view.findViewById(R.id.tvSubHeading);
            this.tvHeadingDownloadItem = (TextView) view.findViewById(R.id.tvTopHeading_download_item);
            this.parentEmptyLayout = (FrameLayout) view.findViewById(R.id.rl_empty_item_view);
            this.crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.imgLightOverlay = (ImageView) view.findViewById(R.id.imgLightOverlay);
            this.play_icon = (ImageView) view.findViewById(R.id.play_icon);
            this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
            this.profileLikesImage = (LinearLayout) view.findViewById(R.id.profileLikesImage);
            this.shadowLayer = view.findViewById(R.id.shadow_layer);
            this.tvSectionTitle = (TextView) view.findViewById(R.id.tv_section_title);
            this.mImgIndictor = (ImageView) view.findViewById(R.id.img_indicator);
            this.mThumbnailRightTopIndicator = (ImageView) view.findViewById(R.id.indicatorIconRightTop);
            this.mTxtPlayCount = (TextView) view.findViewById(R.id.txt_play_count);
            this.mOverlayBg = view.findViewById(R.id.viewProductIconOverlay);
            this.tvTopHeadingMix = (TextView) view.findViewById(R.id.tvTopHeadingMix);
            this.shareIcon = (ImageView) view.findViewById(R.id.shareIcon);
            this.crossFadeImageViewLyt = (FrameLayout) view.findViewById(R.id.f39download.item.img.thumb.container);
        }
    }

    public static class RecommendedItemHolder extends ViewHolder {
        public TextView albumName;
        public ImageView downloadImage;
        public ProgressBar downloadProgressBar;
        public PulsatorView downloadPulse;
        public ImageView favImage;
        public CrossFadeImageView imageViewThumb;
        public CrossFadeImageView imageViewThumbRect;
        public RelativeLayout parentLayout;
        public ImageView play_icon;
        public TextView tvName;
        public TextView tvSectionTitle;
        public TextView txtHeaderName;

        public RecommendedItemHolder(View view) {
            super(view);
            this.parentLayout = (RelativeLayout) view.findViewById(R.id.view_grid_item_relative);
            this.imageViewThumb = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.imageViewThumbRect = (CrossFadeImageView) view.findViewById(R.id.imgProductIconRect);
            this.tvName = (TextView) view.findViewById(R.id.f54grid.item.tv.name);
            this.downloadImage = (ImageView) view.findViewById(R.id.f50grid.item.image.download);
            this.favImage = (ImageView) view.findViewById(R.id.f51grid.item.image.favorite);
            this.downloadProgressBar = (ProgressBar) view.findViewById(R.id.download_ProgressBar);
            this.play_icon = (ImageView) view.findViewById(R.id.play_icon);
            this.downloadPulse = (PulsatorView) view.findViewById(R.id.downloadPulse);
            this.tvSectionTitle = (TextView) view.findViewById(R.id.tv_section_title);
            this.txtHeaderName = (TextView) view.findViewById(R.id.txt_header);
        }
    }

    public static class ReferralActivityHolder extends ViewHolder {
        public CircularImageView friendPicture;
        public View mView;
        public TextView referralFriendMessage;

        public ReferralActivityHolder(View view) {
            super(view);
            this.mView = view;
            this.friendPicture = (CircularImageView) view.findViewById(R.id.friend_pic);
            this.referralFriendMessage = (TextView) view.findViewById(R.id.friend_activity);
        }
    }

    public static class SocialHomeGridHolder extends ViewHolder {
        public CrossFadeImageView crossFadeImageView;
        public RelativeLayout parentEmptyLayout;
        public ImageView play_icon;
        public LinearLayout profileLikesImage;
        public TextView tvBottomHeading;
        public TextView tvTopHeading;

        public SocialHomeGridHolder(View view) {
            super(view);
            this.parentEmptyLayout = (RelativeLayout) view.findViewById(R.id.rl_empty_item_view);
            this.crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.play_icon = (ImageView) view.findViewById(R.id.play_icon);
            this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
            this.tvBottomHeading = (TextView) view.findViewById(R.id.tvBottomHeading);
            this.profileLikesImage = (LinearLayout) view.findViewById(R.id.profileLikesImage);
        }
    }

    public static class SocialPlaylistGridHolder extends ViewHolder {
        public CrossFadeImageView crossFadeImageView;
        public View mView;
        public RelativeLayout parentEmptyLayout;
        public ImageView play_icon;
        public LinearLayout profileLikesImage;
        public TextView tvBottomHeading;
        public TextView tvTopHeading;

        public SocialPlaylistGridHolder(View view) {
            super(view);
            this.mView = view;
            this.parentEmptyLayout = (RelativeLayout) view.findViewById(R.id.rl_empty_item_view);
            this.crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.play_icon = (ImageView) view.findViewById(R.id.play_icon);
            this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
            this.tvBottomHeading = (TextView) view.findViewById(R.id.tvBottomHeading);
            this.profileLikesImage = (LinearLayout) view.findViewById(R.id.profileLikesImage);
        }
    }

    public static class SpinnerHolder extends ViewHolder {
        public Spinner mSpinner;
        public TextView mSpinnerHeader;

        public SpinnerHolder(View view) {
            super(view);
            this.mSpinner = (Spinner) view.findViewById(R.id.spinner);
            this.mSpinnerHeader = (TextView) view.findViewById(R.id.tvHeader);
        }

        public void setVisibility(boolean z) {
            LayoutParams layoutParams = (LayoutParams) this.itemView.getLayoutParams();
            Context context = this.itemView.getContext();
            if (z) {
                layoutParams.height = context.getResources().getDimensionPixelOffset(R.dimen.filter_spinner_height_detail);
                layoutParams.width = -1;
                this.itemView.setVisibility(0);
            } else {
                this.itemView.setVisibility(8);
                layoutParams.height = 0;
                layoutParams.width = 0;
            }
            this.itemView.setLayoutParams(layoutParams);
        }
    }

    public static class SponsorAdViewHolder extends ViewHolder {
        public LinearLayout adView;

        public SponsorAdViewHolder(View view) {
            super(view);
            this.adView = (LinearLayout) view.findViewById(R.id.llNativeAdSlot);
        }
    }

    public static class TrendListHolder extends ViewHolder {
        public ImageView clickoptionImage;
        public CrossFadeImageView crossFadeImageView;
        public ImageView favBtnSongView;
        public View mView;
        public TextView tvAlbumName = ((TextView) this.mView.findViewById(R.id.tvAlbumName));
        public TextView tvSongName = ((TextView) this.mView.findViewById(R.id.tvSongName));

        public TrendListHolder(View view) {
            super(view);
            this.mView = view;
            this.crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.favBtnSongView = (ImageView) view.findViewById(R.id.favBtnSongView);
            this.clickoptionImage = (ImageView) view.findViewById(R.id.clickoptionImage);
        }
    }

    public static class TwoCrossTwoGridItemHolder extends ViewHolder {
        private FrameLayout firstChildLayout;
        public GridItemHolder firstHolder = new GridItemHolder(this.firstChildLayout);
        private FrameLayout fourthChildLayout;
        public GridItemHolder fourthHolder;
        public TextView headerText;
        private FrameLayout secondChildLayout;
        public GridItemHolder secondHolder;
        private FrameLayout thirdChildLayout;
        public GridItemHolder thirdHolder;

        public TwoCrossTwoGridItemHolder(View view) {
            super(view);
            this.headerText = (TextView) view.findViewById(R.id.f55header.text);
            this.firstChildLayout = (FrameLayout) view.findViewById(R.id.f58ll.grid.firstitem);
            this.secondChildLayout = (FrameLayout) view.findViewById(R.id.f60ll.grid.seconditem);
            this.secondHolder = new GridItemHolder(this.secondChildLayout);
            this.thirdChildLayout = (FrameLayout) view.findViewById(R.id.f61ll.grid.thirditem);
            this.thirdHolder = new GridItemHolder(this.thirdChildLayout);
            this.fourthChildLayout = (FrameLayout) view.findViewById(R.id.f59ll.grid.fourthitem);
            this.fourthHolder = new GridItemHolder(this.fourthChildLayout);
        }
    }

    public static class TwoGridItemHolder extends ViewHolder {
        private RelativeLayout firstChildLayout;
        public GridItemHolder firstHolder = new GridItemHolder(this.firstChildLayout);
        public TextView headerText;
        private RelativeLayout secondChildLayout;
        public GridItemHolder secondHolder;

        public TwoGridItemHolder(View view) {
            super(view);
            this.headerText = (TextView) view.findViewById(R.id.f55header.text);
            this.firstChildLayout = (RelativeLayout) view.findViewById(R.id.f58ll.grid.firstitem);
            this.secondChildLayout = (RelativeLayout) view.findViewById(R.id.f60ll.grid.seconditem);
            this.secondHolder = new GridItemHolder(this.secondChildLayout);
        }
    }

    public static class TwoRecommendedItemHolder extends ViewHolder {
        private RelativeLayout firstChildLayout;
        public RecommendedItemHolder firstHolder = new RecommendedItemHolder(this.firstChildLayout);
        public TextView headerText;
        private RelativeLayout secondChildLayout;
        public RecommendedItemHolder secondHolder;

        public TwoRecommendedItemHolder(View view) {
            super(view);
            this.headerText = (TextView) view.findViewById(R.id.f55header.text);
            this.firstChildLayout = (RelativeLayout) view.findViewById(R.id.f58ll.grid.firstitem);
            this.secondChildLayout = (RelativeLayout) view.findViewById(R.id.f60ll.grid.seconditem);
            this.secondHolder = new RecommendedItemHolder(this.secondChildLayout);
        }
    }

    public static class VoicePlaylistGridHolder extends ViewHolder {
        public CrossFadeImageView crossFadeImageView;
        public View mView;
        public FrameLayout parentEmptyLayout;
        public ImageView play_icon;
        public LinearLayout profileLikesImage;
        public View shadowLayer;
        public TextView tvBottomHeading;
        public TextView tvTopHeading;

        public VoicePlaylistGridHolder(View view) {
            super(view);
            this.mView = view;
            this.parentEmptyLayout = (FrameLayout) view.findViewById(R.id.rl_empty_item_view);
            this.crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.play_icon = (ImageView) view.findViewById(R.id.play_icon);
            this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
            this.tvBottomHeading = (TextView) view.findViewById(R.id.tvBottomHeading);
            this.profileLikesImage = (LinearLayout) view.findViewById(R.id.profileLikesImage);
            this.shadowLayer = view.findViewById(R.id.shadow_layer);
        }
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup, boolean z) {
        return view;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup, boolean z, Boolean bool) {
        return view;
    }

    public View getPoplatedViewForGrid(View view, BusinessObject businessObject, ViewGroup viewGroup, int i) {
        return null;
    }

    public void getPopulatedViewGrid(View view, BusinessObject businessObject) {
    }

    public void setItemPosition(int i) {
    }

    public void setSearchQuery(String str) {
        this.mSearchQuery = str;
    }

    public BaseItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context);
        this.mContext = context;
        if (baseGaanaFragment == null && (this.mContext instanceof GaanaActivity)) {
            this.mFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
        } else if (baseGaanaFragment instanceof ListingFragment) {
            Fragment parentFragment = baseGaanaFragment.getParentFragment();
            if (parentFragment instanceof TabbedFragment) {
                this.mFragment = baseGaanaFragment;
            } else if (parentFragment instanceof BaseGaanaFragment) {
                this.mFragment = (BaseGaanaFragment) parentFragment;
            } else if (this.mContext instanceof GaanaActivity) {
                this.mFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
            } else {
                this.mFragment = baseGaanaFragment;
            }
        } else {
            this.mFragment = baseGaanaFragment;
        }
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
        linearLayout.setLayoutParams(layoutParams);
    }

    public BaseItemView(Context context, BaseFragment baseFragment, int i) {
        super(context);
        this.mContext = context;
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
        linearLayout.setLayoutParams(layoutParams);
    }

    public View getEmptyMsgView(UserMessage userMessage, ViewGroup viewGroup) {
        View inflate = this.mInflater.inflate(R.layout.view_user_msg, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.tvUserMsg)).setText(userMessage.getEmptyMsg());
        return inflate;
    }

    /* Access modifiers changed, original: protected */
    public View createNewBaseView(int i, View view, ViewGroup viewGroup) {
        return this.mInflater.inflate(i, viewGroup, false);
    }

    public View getNewView(int i, ViewGroup viewGroup, BusinessObject businessObject) {
        if (viewGroup != null) {
            this.mView = this.mInflater.inflate(i, viewGroup, false);
        } else {
            this.mView = this.mInflater.inflate(i, this, false);
        }
        this.mView.setTag(businessObject);
        this.mView.setOnClickListener(this);
        return this.mView;
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        if (viewGroup != null) {
            this.mView = this.mInflater.inflate(i, viewGroup, false);
        } else {
            this.mView = this.mInflater.inflate(i, this, false);
        }
        return this.mView;
    }

    public View getPoplatedView(View view, BusinessObject businessObject) {
        view.setOnClickListener(this);
        view.setTag(businessObject);
        return view;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        view.setTag(businessObject);
        return view;
    }

    /* Access modifiers changed, original: protected */
    public void populateRadioListing(BusinessObject businessObject) {
        af.a(this.mContext, this.mFragment).a((int) R.id.radioMenu, businessObject);
    }

    /* Access modifiers changed, original: protected */
    public void populateAlbumListing(BusinessObject businessObject) {
        af.a(this.mContext, this.mFragment).a((int) R.id.albumMenu, businessObject);
    }

    /* Access modifiers changed, original: protected */
    public void populateArtistListing(BusinessObject businessObject) {
        af.a(this.mContext, this.mFragment).a((int) R.id.artistMenu, businessObject);
    }

    /* Access modifiers changed, original: protected */
    public void populatePlaylistListing(Playlist playlist) {
        af.a(this.mContext, this.mFragment).a((int) R.id.playlistMenu, (BusinessObject) playlist);
    }

    /* Access modifiers changed, original: protected */
    public void populateJukePlaylistListing(Playlist playlist, String str) {
        playlist.setPartySource(str);
        af.a(this.mContext, this.mFragment).a((int) R.id.jukePlaylistMenu, (BusinessObject) playlist);
    }

    /* Access modifiers changed, original: protected */
    public void populateSpecialGaanaListing(Playlist playlist) {
        af.a(this.mContext, this.mFragment).a((int) R.id.specialGaanaMenu, (BusinessObject) playlist);
    }

    /* Access modifiers changed, original: protected */
    public void populateandPlayPlaylistListing(Playlist playlist) {
        af.a(this.mContext, this.mFragment).a((int) R.id.playPlaylistMenu, (BusinessObject) playlist);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, boolean z, Boolean bool) {
        return viewHolder.itemView;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, boolean z, Boolean bool, v vVar) {
        return viewHolder.itemView;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        return viewHolder.itemView;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, ArrayList<Track> arrayList) {
        return viewHolder.itemView;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject) {
        return viewHolder.itemView;
    }

    public void onClick(View view) {
        setTrackSectionName();
    }

    private void setFreeDownloadIcon(ImageView imageView) {
        imageView.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.free_download_icon_white));
    }

    public void updateFreeDownloadImage(ImageView imageView, DownloadStatus downloadStatus, boolean z) {
        BaseFragment baseFragment = ((GaanaActivity) this.mContext).getmCurrentPlayerFragment();
        if (downloadStatus == null) {
            if (imageView == null) {
                return;
            }
            if (z) {
                setFreeDownloadIcon(imageView);
                return;
            }
            imageView.setImageDrawable(Util.b(this.mContext, (int) R.attr.free_download_icon));
            Util.ab();
        } else if (downloadStatus == DownloadStatus.DOWNLOADING) {
            if (imageView == null || !DownloadManager.c().w()) {
                imageView.setImageResource(R.drawable.vector_download_queued);
            } else {
                imageView.setVisibility(4);
            }
        } else if (downloadStatus == DownloadStatus.QUEUED) {
            if (imageView != null) {
                imageView.setImageResource(R.drawable.vector_download_queued);
            }
        } else if (downloadStatus == DownloadStatus.DOWNLOADED) {
            if (imageView == null) {
                return;
            }
            if (!ap.a().k() || Util.a(this.mBusinessObject)) {
                imageView.setImageResource(R.drawable.vector_download_completed);
            } else if (Constants.l) {
                imageView.setImageResource(R.drawable.vector_download_expired_btn_white);
            } else {
                imageView.setImageResource(R.drawable.vector_download_expired_btn);
            }
        } else if (downloadStatus == DownloadStatus.PAUSED) {
            if (imageView == null) {
                return;
            }
            if (z) {
                setFreeDownloadIcon(imageView);
                return;
            }
            imageView.setImageDrawable(Util.b(this.mContext, (int) R.attr.free_download_icon));
            Util.ab();
        } else if (downloadStatus == DownloadStatus.PARTIALLY_DOWNLOADED) {
            if (imageView != null) {
                imageView.setImageResource(R.drawable.vector_download_button_downloading);
            }
        } else if (downloadStatus == DownloadStatus.TRIED_BUT_FAILED && imageView != null) {
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            Drawable drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(91, -1));
            obtainStyledAttributes.recycle();
            if (baseFragment == null || !(baseFragment instanceof PlayerFragmentV4)) {
                imageView.setImageDrawable(drawable);
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.vector_download_failed_white));
            }
        }
    }

    public void updateDownloadImage(ImageView imageView, DownloadStatus downloadStatus) {
        BaseFragment baseFragment = ((GaanaActivity) this.mContext).getmCurrentPlayerFragment();
        TypedArray obtainStyledAttributes;
        Drawable drawable;
        if (downloadStatus == null) {
            if (imageView != null) {
                new int[1][0] = R.attr.download_button_paused;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1)));
                if (baseFragment instanceof PlayerFragmentV4) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.vector_more_option_favorite_white));
                }
                obtainStyledAttributes.recycle();
            }
        } else if (downloadStatus == DownloadStatus.DOWNLOADING) {
            if (imageView == null || !DownloadManager.c().w()) {
                imageView.setImageResource(R.drawable.vector_download_queued);
            } else {
                imageView.setVisibility(4);
            }
        } else if (downloadStatus == DownloadStatus.QUEUED) {
            if (imageView != null) {
                imageView.setImageResource(R.drawable.vector_download_queued);
            }
        } else if (downloadStatus == DownloadStatus.DOWNLOADED) {
            if (imageView == null) {
                return;
            }
            if (!ap.a().k() || Util.a(this.mBusinessObject)) {
                imageView.setImageResource(R.drawable.vector_download_completed);
            } else if (Constants.l) {
                imageView.setImageResource(R.drawable.vector_download_expired_btn_white);
            } else {
                imageView.setImageResource(R.drawable.vector_download_expired_btn);
            }
        } else if (downloadStatus == DownloadStatus.PAUSED) {
            if (imageView != null) {
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
                if (!(baseFragment instanceof PlayerFragmentV4) || this.isPlayerQueue) {
                    imageView.setImageDrawable(drawable);
                } else {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.vector_more_option_download_white));
                }
                obtainStyledAttributes.recycle();
            }
        } else if (downloadStatus == DownloadStatus.PARTIALLY_DOWNLOADED) {
            if (imageView != null) {
                imageView.setImageResource(R.drawable.vector_download_button_downloading);
            }
        } else if (downloadStatus == DownloadStatus.TRIED_BUT_FAILED && imageView != null) {
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(91, -1));
            obtainStyledAttributes.recycle();
            if (baseFragment instanceof PlayerFragmentV4) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.vector_download_failed_white));
            } else {
                imageView.setImageDrawable(drawable);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void startDownload(final BusinessObject businessObject, final View view) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).hideProgressDialog();
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else if (Util.j(this.mContext)) {
            String str = null;
            String str2;
            if (!ap.a().a(businessObject, null) && !Util.b(businessObject)) {
                ((BaseActivity) this.mContext).hideProgressDialog();
                PopupWindowView.getInstance(this.mContext, this.mFragment).dismiss(true);
                if (view != null) {
                    str = this.mContext.getString(R.string.topsong_english);
                }
                str2 = "";
                if (ap.a().l()) {
                    str2 = businessObject instanceof Track ? "tr" : "pl";
                }
                Util.b(this.mContext, str2, str, new as() {
                    public void onTrialSuccess() {
                        BaseItemView.this.startActualDownload(view, businessObject);
                        if (BaseItemView.this.mFragment != null) {
                            BaseItemView.this.mFragment.refreshDataandAds();
                            BaseItemView.this.mFragment.showSnackbartoOpenMyMusic();
                        }
                        ((GaanaActivity) BaseItemView.this.mContext).updateSideBar();
                    }
                });
            } else if (!ap.a().f() || !ap.a().o()) {
                startActualDownload(view, businessObject);
            } else if (ap.a().h()) {
                str2 = "";
                if (ap.a().f()) {
                    str2 = businessObject instanceof Track ? "tr" : "pl";
                }
                if (!(businessObject instanceof Track)) {
                    int i = 0;
                    if (businessObject.getArrListBusinessObj() != null) {
                        Iterator it = businessObject.getArrListBusinessObj().iterator();
                        while (it.hasNext()) {
                            if (DownloadManager.c().e(Integer.valueOf(((Track) it.next()).getBusinessObjId()).intValue()) == null) {
                                i++;
                            }
                        }
                    } else if (businessObject instanceof Playlist) {
                        i = ((Playlist) businessObject).getTrackids().split(",").length;
                    }
                    if (((i + DownloadManager.c().K()) + DownloadManager.c().B()) + DownloadManager.c().r() > Integer.valueOf(this.mAppState.getCurrentUser().getUserSubscriptionData().getProductProperties().getSongLimit()).intValue()) {
                        Util.b(this.mContext, str2, "", "", new as() {
                            public void onTrialSuccess() {
                                BaseItemView.this.startActualDownload(view, businessObject);
                                BaseItemView.this.mFragment.refreshDataandAds();
                                BaseItemView.this.mFragment.showSnackbartoOpenMyMusic();
                                ((GaanaActivity) BaseItemView.this.mContext).updateSideBar();
                            }
                        });
                    } else {
                        startActualDownload(view, businessObject);
                    }
                } else if ((DownloadManager.c().K() + DownloadManager.c().B()) + DownloadManager.c().r() >= Integer.valueOf(this.mAppState.getCurrentUser().getUserSubscriptionData().getProductProperties().getSongLimit()).intValue()) {
                    Util.b(this.mContext, str2, "", "", new as() {
                        public void onTrialSuccess() {
                            BaseItemView.this.startActualDownload(view, businessObject);
                            BaseItemView.this.mFragment.refreshDataandAds();
                            BaseItemView.this.mFragment.showSnackbartoOpenMyMusic();
                            ((GaanaActivity) BaseItemView.this.mContext).updateSideBar();
                        }
                    });
                } else {
                    startActualDownload(view, businessObject);
                }
            } else {
                Util.x(this.mContext);
            }
        } else {
            ((BaseActivity) this.mContext).hideProgressDialog();
            ap.a().f(this.mContext);
        }
    }

    private void startActualDownload(View view, BusinessObject businessObject) {
        Util.i(this.mContext, "Download");
        if (Util.k(GaanaApplication.getContext()) == 0 && !Util.a(businessObject)) {
            ((BaseActivity) this.mContext).hideProgressDialog();
            d a = d.a();
            boolean b = a.b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
            if (!a.b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                ((BaseActivity) this.mContext).mDialog = new f(this.mContext);
                ((BaseActivity) this.mContext).mDialog.a(this.mContext.getString(R.string.gaana_plus_feature), this.mContext.getString(R.string.dlg_msg_sync_data_disablde), Boolean.valueOf(true), this.mContext.getString(R.string.settings_text), this.mContext.getString(R.string.dlg_msg_cancel), new b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        if ((BaseItemView.this.mFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) BaseItemView.this.mFragment).a() == 1) {
                            PopupWindowView.getInstance(BaseItemView.this.mContext, BaseItemView.this.mFragment).dismiss(true);
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 1);
                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                        settingsDetailFragment.setArguments(bundle);
                        ((BaseActivity) BaseItemView.this.mContext).sendGAEvent("GaanaPlus", "BuySubscription", "Others");
                        PopupWindowView.getInstance(BaseItemView.this.mContext, BaseItemView.this.mFragment).dismiss(true);
                        ((GaanaActivity) BaseItemView.this.mContext).displayFragment(settingsDetailFragment);
                    }
                });
                return;
            } else if (b) {
                if (!Constants.V) {
                    aj.a().a(this.mContext, this.mContext.getString(R.string.schedule_songs_queue_msg));
                    Constants.V = true;
                }
            } else if (!Constants.W) {
                Constants.W = true;
                aj.a().a(this.mContext, this.mContext.getString(R.string.schedule_cta_text), this.mContext.getString(R.string.schedule_download_msg), new OnClickListener() {
                    public void onClick(View view) {
                        if ((BaseItemView.this.mFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) BaseItemView.this.mFragment).a() == 1) {
                            PopupWindowView.getInstance(BaseItemView.this.mContext, BaseItemView.this.mFragment).dismiss(true);
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 1);
                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                        settingsDetailFragment.setArguments(bundle);
                        PopupWindowView.getInstance(BaseItemView.this.mContext, BaseItemView.this.mFragment).dismiss(true);
                        ((GaanaActivity) BaseItemView.this.mContext).displayFragment(settingsDetailFragment);
                    }
                });
            }
        }
        downloadInitiaized(view, businessObject);
    }

    private void downloadInitiaized(View view, BusinessObject businessObject) {
        if (!DownloadManager.c().v()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    Util.w(BaseItemView.this.mContext);
                }
            });
        } else if (Constants.t() && !Constants.U) {
            Constants.U = true;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    Constants.U = false;
                    new DownloadSyncPopupItemView(BaseItemView.this.mContext).showDownloadSyncWelcomeScreenDialog();
                }
            }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
        if (view != null) {
            view.setVisibility(0);
            if (businessObject instanceof Track) {
                String trackTitle = ((Track) businessObject).getTrackTitle();
                aj a = aj.a();
                Context context = this.mContext;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.mContext.getString(R.string.downloading_text));
                stringBuilder.append(trackTitle);
                a.a(context, stringBuilder.toString());
            }
        }
        DownloadStatus e;
        if (businessObject instanceof Track) {
            ((BaseActivity) this.mContext).hideProgressDialog();
            e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
            if (e == DownloadStatus.PAUSED || e == DownloadStatus.TRIED_BUT_FAILED) {
                DownloadManager.c().b((Track) businessObject);
            } else {
                DownloadManager.c().a((Track) businessObject, this.mContext);
            }
            updateOfflineTracksStatus(false);
        } else if ((businessObject instanceof Playlist) || (businessObject instanceof Album)) {
            e = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
            if (DownloadStatus.PAUSED == e || DownloadStatus.PARTIALLY_DOWNLOADED == e) {
                DownloadManager.c().c(businessObject);
                updateOfflineTracksStatus(false);
                ((BaseActivity) this.mContext).hideProgressDialog();
                return;
            }
            OnBusinessObjectRetrievedDownload onBusinessObjectRetrievedDownload = new OnBusinessObjectRetrievedDownload();
            onBusinessObjectRetrievedDownload.setParentBusinessObject(businessObject);
            retrieveFeed(businessObject, onBusinessObjectRetrievedDownload);
        }
    }

    /* Access modifiers changed, original: protected */
    public void startDownload(BusinessObject businessObject) {
        startDownload(businessObject, null);
    }

    /* Access modifiers changed, original: protected */
    public void retrieveFeed(BusinessObject businessObject, s sVar) {
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Tracks);
        String str = "https://api.gaana.com/index.php?";
        if (businessObject instanceof Playlist) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c.w);
            stringBuilder.append("playlist_id=");
            stringBuilder.append(businessObject.getBusinessObjId());
            stringBuilder.append("&playlist_type=");
            Playlist playlist = (Playlist) businessObject;
            stringBuilder.append(playlist.getPlaylistType());
            str = stringBuilder.toString();
            if (playlist.getAutomated() != null && playlist.getAutomated().equalsIgnoreCase("1")) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append("&automated=1");
                str = stringBuilder2.toString();
            }
        } else if (businessObject instanceof Album) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str);
            stringBuilder3.append("type=album&subtype=album_detail&album_id=");
            stringBuilder3.append(businessObject.getBusinessObjId());
            str = stringBuilder3.toString();
        }
        uRLManager.a(str);
        i.a().a(sVar, uRLManager);
    }

    public void updateOfflineTracksStatus() {
        updateOfflineTracksStatus(true);
    }

    public void updateOfflineTracksStatus(boolean z) {
        if ((this.mFragment instanceof DownloadDetailsFragment) && z) {
            ((DownloadDetailsFragment) this.mFragment).d();
        } else if (!(this.mFragment instanceof MyMusicItemFragment)) {
            ((BaseActivity) this.mContext).refreshListView();
        } else if (z) {
            ((MyMusicItemFragment) this.mFragment).d();
        } else {
            ((MyMusicItemFragment) this.mFragment).refreshListView();
        }
    }

    public void deleteDownload(BusinessObject businessObject) {
        String entityId;
        String str = "";
        boolean z = businessObject instanceof Item;
        if (z) {
            entityId = ((Item) businessObject).getEntityId();
        } else {
            entityId = businessObject.getBusinessObjId();
        }
        if (z) {
            str = ((Item) businessObject).getArtwork().replace("80x80", "480x480");
        } else if (businessObject instanceof OfflineTrack) {
            str = ((OfflineTrack) businessObject).getImageUrl().replace("80x80", "480x480");
        }
        GlideFileLoader.delete(str);
        if ((z && ((Item) businessObject).getEntityType().equals("TR")) || (businessObject instanceof Track) || (businessObject instanceof OfflineTrack)) {
            DownloadManager.c().d(entityId);
            updateOfflineTracksStatus();
            if (this.mFragment instanceof DownloadDetailsFragment) {
                ((DownloadDetailsFragment) this.mFragment).d();
            } else {
                ((BaseActivity) this.mContext).refreshListView();
            }
        } else {
            DownloadManager.c().p(Integer.parseInt(entityId));
            DownloadManager.c().d(Integer.parseInt(entityId));
        }
        updateOfflineTracksStatus();
    }

    public int getSourceType(BusinessObject businessObject) {
        SOURCE_TYPE source_type = SOURCE_TYPE.OTHER;
        if (businessObject instanceof Album) {
            source_type = SOURCE_TYPE.ALBUM;
        } else if (businessObject instanceof Artist) {
            source_type = SOURCE_TYPE.ARTIST;
        } else if (businessObject instanceof Playlist) {
            if (((Playlist) businessObject).getPlaylistSourceType() == PlaylistSourceType.HOURLY_PLAYLIST) {
                source_type = SOURCE_TYPE.HOURLY_PLAYLIST;
            } else {
                source_type = SOURCE_TYPE.PLAYLIST;
            }
        }
        return source_type.ordinal();
    }

    /* Access modifiers changed, original: protected */
    public void showOptionMenu(View view) {
        BusinessObject businessObject;
        this.mBusinessObject = (BusinessObject) view.getTag();
        if ((this.mBusinessObject instanceof Item) && ((Item) this.mBusinessObject).getEntityType().equals("TR")) {
            businessObject = (Track) populateTrackClicked((Item) this.mBusinessObject);
        } else {
            businessObject = this.mBusinessObject;
            if ((businessObject instanceof Track) && TextUtils.isEmpty(((Track) businessObject).getAlbumId())) {
                Util.a(this.mContext, this.mFragment, businessObject, this.isPlayerQueue, null);
                return;
            }
        }
        PopupWindowView instance = PopupWindowView.getInstance(this.mContext, this.mFragment);
        if (this instanceof DownloadPopupListener) {
            instance.setDownloadPopupListener((DownloadPopupListener) this);
        }
        instance.contextPopupWindow(businessObject, this.isPlayerQueue, true);
    }

    /* Access modifiers changed, original: protected */
    public boolean handleMenuClickListener(MenuItem menuItem) {
        return af.a(this.mContext, this.mFragment).a(menuItem.getItemId(), this.mBusinessObject);
    }

    public String getFormattedFavoriteCount(String str) {
        if (str == null || str.length() <= 3) {
            return str;
        }
        int length = str.length();
        int i = length - 3;
        String substring = str.substring(i, length);
        str = str.substring(0, i);
        if (str.length() > 2) {
            i = str.length();
            int i2 = i - 2;
            String substring2 = str.substring(i2, i);
            str = str.substring(0, i2);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(",");
            stringBuilder.append(substring2);
            str = stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(",");
        stringBuilder2.append(substring);
        return stringBuilder2.toString();
    }

    public View getEmptyView(View view, ViewGroup viewGroup, BusinessObjectType businessObjectType) {
        return inflateView(this.mLayoutId, viewGroup);
    }

    public View getEmptyView(ViewHolder viewHolder, ViewGroup viewGroup, BusinessObjectType businessObjectType) {
        return viewHolder.itemView;
    }

    /* Access modifiers changed, original: protected */
    public View inflateView(int i, ViewGroup viewGroup) {
        if (viewGroup != null) {
            this.mView = this.mInflater.inflate(i, viewGroup, false);
        } else {
            this.mView = this.mInflater.inflate(i, this, false);
        }
        this.mView.setPadding((int) getResources().getDimension(R.dimen.activity_horizontal_margin), 0, 0, 0);
        return this.mView;
    }

    private void setTrackSectionName() {
        GaanaApplication gaanaApplication = (GaanaApplication) this.mContext.getApplicationContext();
        String name = PLAYOUT_SECTION_TYPE.OTHERS.name();
        if ((this.mFragment instanceof DiscoverDetailFragment) || (this.mFragment instanceof FavoritesFragment) || (this.mFragment instanceof MyMusicItemFragment) || (this.mFragment instanceof DownloadDetailsFragment) || (this.mFragment instanceof MyMusicFragment) || (this.mFragment instanceof ListingFragment) || (this.mFragment instanceof DownloadHomeFragment) || (this.mFragment instanceof ItemListingFragment) || (this.mFragment instanceof LocalMediaFragment) || (this.mFragment instanceof SearchFragment) || (this.mFragment instanceof RadioDetailsMaterialListing) || (this.mFragment instanceof RadioActivityFragment) || (this.mFragment instanceof DynamicHomeFragment) || (this.mFragment instanceof GridActivityFragment) || (this.mFragment instanceof SongParallexListingFragment) || (this.mFragment instanceof ActivityFeedActivityFragment) || (this.mFragment instanceof ProfileFragment) || (this.mFragment instanceof AlbumDetailsMaterialListing) || (this.mFragment instanceof GaanaSpecialDetailsMaterialListing) || (this.mFragment instanceof ArtistDetailsMaterialListing) || (this.mFragment instanceof ListingFragmentMaterial) || (this.mFragment instanceof DynamicOccasionFragment) || (this.mFragment instanceof PersonaDedicationFragment) || (this.mFragment instanceof PersonaDetailsFragment)) {
            if (TextUtils.isEmpty(getSectionName()) || getSectionName().equals(PLAYOUT_SECTION_TYPE.OTHERS.name())) {
                name = this.mFragment.getSectionName();
            } else {
                name = getSectionName();
            }
            if ((this.mFragment instanceof AlbumDetailsMaterialListing) || (this.mFragment instanceof GaanaSpecialDetailsMaterialListing) || (this.mFragment instanceof ArtistDetailsMaterialListing) || (this.mFragment instanceof ListingFragmentMaterial)) {
                if (!TextUtils.isEmpty(gaanaApplication.getPlayoutSectionName())) {
                    name = getSectionName();
                    if (gaanaApplication.getPlayoutSectionName().equals(PLAYOUT_SECTION_TYPE.FAVORITES.name())) {
                        name = PLAYOUT_SECTION_TYPE.FAVORITES.name();
                    } else if (gaanaApplication.getPlayoutSectionName().equals(PLAYOUT_SECTION_TYPE.SEARCH_AUTO_SUGGEST.name())) {
                        name = PLAYOUT_SECTION_TYPE.SEARCH_AUTO_SUGGEST.name();
                    } else if (gaanaApplication.getPlayoutSectionName().equals(PLAYOUT_SECTION_TYPE.ARTISTS.name())) {
                        name = PLAYOUT_SECTION_TYPE.ARTISTS.name();
                    }
                } else if (this instanceof DownloadAlbumItemView) {
                    String playoutSectionName = gaanaApplication.getPlayoutSectionName();
                    if (!(TextUtils.isEmpty(playoutSectionName) || !playoutSectionName.equals(PLAYOUT_SECTION_TYPE.ARTISTS.name()) || playoutSectionName.equals(PLAYOUT_SECTION_TYPE.SEARCH_AUTO_SUGGEST.name()))) {
                        name = PLAYOUT_SECTION_TYPE.ARTISTS.name();
                    }
                }
            }
        }
        if (this.mFragment instanceof DynamicOccasionFragment) {
            if (TextUtils.isEmpty(gaanaApplication.getPlayoutSectionName()) || !gaanaApplication.getPlayoutSectionName().equalsIgnoreCase(PLAYOUT_SECTION_TYPE.QUICK_SEARCH.name())) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("OP_");
                stringBuilder.append(name);
                gaanaApplication.setPlayoutSectionName(stringBuilder.toString());
                return;
            }
            u.a().a("QuickSearch", "Click", name);
        } else if (!(this.mFragment instanceof ProfileFragment) && !(this.mFragment instanceof SearchTabFragment)) {
            gaanaApplication.setPlayoutSectionName(name);
        }
    }

    public View createViewHolder(ViewGroup viewGroup, int i) {
        if (this.mLayoutId != -1) {
            this.mView = createNewBaseView(this.mLayoutId, this.mView, viewGroup);
        }
        return this.mView;
    }

    public View createViewHolder(ViewGroup viewGroup, int i, int i2) {
        if (i2 != -1) {
            this.mView = createNewBaseView(i2, this.mView, viewGroup);
        }
        return this.mView;
    }

    /* Access modifiers changed, original: protected */
    public BusinessObject populateArtistClicked(Item item) {
        return Util.a(item);
    }

    /* Access modifiers changed, original: protected */
    public BusinessObject populatePlaylistClicked(Item item) {
        return Util.b(item);
    }

    /* Access modifiers changed, original: protected */
    public BusinessObject populateAlbumClicked(Item item) {
        return Util.c(item);
    }

    /* Access modifiers changed, original: protected */
    public BusinessObject populateRadioClicked(Item item) {
        q.a().a("Radio", item.getName());
        return Util.d(item);
    }

    /* Access modifiers changed, original: protected */
    public BusinessObject populateDiscoverTagClicked(Item item) {
        q.a().a("Discover", item.getName());
        return Util.e(item);
    }

    /* Access modifiers changed, original: protected */
    public BusinessObject populateVideoClicked(Item item) {
        return Util.f(item);
    }

    /* Access modifiers changed, original: protected */
    public BusinessObject populateTrackClicked(Item item) {
        return Util.g(item);
    }

    /* Access modifiers changed, original: protected */
    public void launchYouTubePlayer(String str, String str2, BusinessObject businessObject, int i) {
        Util.a(this.mContext, str, str2, businessObject, i);
    }

    public String getSectionName() {
        String playoutSectionName = GaanaApplication.getInstance().getPlayoutSectionName();
        if (TextUtils.isEmpty(playoutSectionName) || playoutSectionName.equals(PLAYOUT_SECTION_TYPE.OTHERS.name())) {
            return PLAYOUT_SECTION_TYPE.OTHERS.name();
        }
        return playoutSectionName;
    }
}
