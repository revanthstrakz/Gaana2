package com.gaana.revampeddetail.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.constants.Constants;
import com.constants.Constants.REVAMPED_DETAIL_CAROUSAL_CARD_TYPE;
import com.constants.Constants.REVAMPED_DETAIL_TYPE;
import com.exoplayer2.VideoPlayerActivityTwo;
import com.fragments.BaseGaanaFragment;
import com.fragments.RevampedDetailListing;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.revampeddetail.manager.RevampedDetailObjectManager;
import com.gaana.revampeddetail.manager.RevampedDetailViewHelper;
import com.gaana.revampeddetail.model.RevampedCarouselData.CarouselCardData;
import com.gaana.revampeddetail.model.RevampedCarouselData.CarouselDetailEntity;
import com.gaana.revampeddetail.model.RevampedCarouselData.DetailEntityInfo;
import com.gaana.revampeddetail.view.RevampedCarouselItemView;
import com.gaana.view.CustomTextView;
import com.gaanavideo.FullScreenVideoPlayerActivity;
import com.i.i;
import com.library.controls.CircularImageView;
import com.library.controls.CrossFadeImageView;
import com.managers.URLManager;
import com.managers.ap;
import com.managers.f;
import com.managers.u;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.o;
import com.services.c;
import com.services.l.s;
import com.utilities.Util;
import com.utilities.d;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;

public class RevampedCarouselPagerAdapter extends Adapter<ViewHolder> implements OnClickListener {
    private static final int INVALID_CARD = -1;
    private ArrayList<CarouselCardData> carouselCardData;
    private String combinedText;
    private LinearLayout favoriteCountLayout;
    private String favoriteString = "";
    private String favoritesCount = "0";
    private RevampedCarouselItemView homeCarouselListener;
    private LayoutInflater inflater;
    private String localAlbumCount = null;
    private String localSongsCount = null;
    CustomTextView mAlbumCountView;
    private GaanaApplication mAppState;
    private Context mContext;
    private int mCount;
    private int mDetailType;
    private RevampedDetailViewHelper mDetailViewHelper;
    private BaseGaanaFragment mFragment;
    LinearLayout mLLFavoriteHolder;
    BusinessObject mParentBusinessObject;
    private RevampedDetailObjectManager mRevampDetailObjManager;
    private String songString = "";

    public class TagObject {
        CarouselCardData item;
        int position;

        public TagObject(CarouselCardData carouselCardData, int i) {
            this.item = carouselCardData;
            this.position = i;
        }

        public int getPosition() {
            return this.position;
        }

        public CarouselCardData getItem() {
            return this.item;
        }
    }

    public static class CarouselAdsItemHolder extends ViewHolder {
        TextView tvDesc;
        TextView tvTitle;
        CrossFadeImageView videoImage;

        public CarouselAdsItemHolder(View view) {
            super(view);
            this.videoImage = (CrossFadeImageView) view.findViewById(R.id.ad_image);
            this.tvDesc = (TextView) view.findViewById(R.id.ad_text_desc);
            this.tvTitle = (TextView) view.findViewById(R.id.ad_text_title);
        }
    }

    public static class CarouselAlbumPlaylistMetaItemHolder extends ViewHolder {
        CrossFadeImageView createdByImageRect;
        TextView descText;
        TextView favCountText;
        LinearLayout infoLayout;
        TextView titleText;

        public CarouselAlbumPlaylistMetaItemHolder(View view) {
            super(view);
            this.createdByImageRect = (CrossFadeImageView) view.findViewById(R.id.image_created_by_rect);
            this.titleText = (TextView) view.findViewById(R.id.created_title);
            this.descText = (TextView) view.findViewById(R.id.created_desc);
            this.infoLayout = (LinearLayout) view.findViewById(R.id.created_bottom_info);
            this.favCountText = (TextView) view.findViewById(R.id.fav_count);
        }
    }

    public static class CarouselCreatedByItemHolder extends ViewHolder {
        CircularImageView createdByImage;
        TextView descText;
        TextView infoLayout;
        TextView titleText;

        public CarouselCreatedByItemHolder(View view) {
            super(view);
            this.createdByImage = (CircularImageView) view.findViewById(R.id.image_created_by);
            this.titleText = (TextView) view.findViewById(R.id.created_title);
            this.descText = (TextView) view.findViewById(R.id.created_desc);
            this.infoLayout = (TextView) view.findViewById(R.id.created_bottom_info);
        }
    }

    public static class CarouselDeeplinkItemHolder extends ViewHolder {
        ImageView playButton;
        CrossFadeImageView videoImage;

        public CarouselDeeplinkItemHolder(View view) {
            super(view);
            this.videoImage = (CrossFadeImageView) view.findViewById(R.id.video_thumbnail);
            this.playButton = (ImageView) view.findViewById(R.id.play_video);
        }
    }

    public static class CarouselFollowMeItemHolder extends ViewHolder {
        TextView fTitle;
        LinearLayout followme_buttons;

        public CarouselFollowMeItemHolder(View view) {
            super(view);
            this.fTitle = (TextView) view.findViewById(R.id.followme_title);
            this.followme_buttons = (LinearLayout) view.findViewById(R.id.followme_buttons);
        }
    }

    public static class CarouselGalleryItemHolder extends ViewHolder {
        CrossFadeImageView centerImage;
        CrossFadeImageView centerImageSquare;
        CrossFadeImageView leftImage;
        CrossFadeImageView rightImage;

        public CarouselGalleryItemHolder(View view) {
            super(view);
            this.centerImage = (CrossFadeImageView) view.findViewById(R.id.gallery_image_center);
            this.centerImageSquare = (CrossFadeImageView) view.findViewById(R.id.gallery_image_center_sqaure);
            this.leftImage = (CrossFadeImageView) view.findViewById(R.id.gallery_image_left);
            this.rightImage = (CrossFadeImageView) view.findViewById(R.id.gallery_image_right);
        }
    }

    public static class CarouselImageItemHolder extends ViewHolder {
        LinearLayout bottomInfo;
        CrossFadeImageView image;

        public CarouselImageItemHolder(View view) {
            super(view);
            this.image = (CrossFadeImageView) view.findViewById(R.id.r_carousel_image);
            this.bottomInfo = (LinearLayout) view.findViewById(R.id.bottom_info_layout);
        }
    }

    public static class CarouselRadioArtistMetaItemHolder extends ViewHolder {
        LinearLayout bottomInfo;
        TextView favCountText;
        CircularImageView image;
        TextView infoText;

        public CarouselRadioArtistMetaItemHolder(View view) {
            super(view);
            this.image = (CircularImageView) view.findViewById(R.id.r_carousel_image);
            this.infoText = (TextView) view.findViewById(R.id.item_info);
            this.bottomInfo = (LinearLayout) view.findViewById(R.id.bottom_info_layout);
            this.favCountText = (TextView) view.findViewById(R.id.fav_count);
        }
    }

    public static class CarouselTextItemHolder extends ViewHolder {
        TextView tvDesc;
        TextView tvTitle;
        TextView tvUpdate;

        public CarouselTextItemHolder(View view) {
            super(view);
            this.tvTitle = (TextView) view.findViewById(R.id.text_item_title);
            this.tvDesc = (TextView) view.findViewById(R.id.text_item_desc);
            this.tvUpdate = (TextView) view.findViewById(R.id.text_item_lastupdate);
        }
    }

    public static class CarouselVideoItemHolder extends ViewHolder {
        ImageView playImage;
        CrossFadeImageView videoImage;

        public CarouselVideoItemHolder(View view) {
            super(view);
            this.videoImage = (CrossFadeImageView) view.findViewById(R.id.video_thumbnail);
            this.playImage = (ImageView) view.findViewById(R.id.play_video);
        }
    }

    public RevampedCarouselPagerAdapter(Context context, BaseGaanaFragment baseGaanaFragment, ArrayList<CarouselCardData> arrayList, RecyclerView recyclerView) {
        this.carouselCardData = arrayList;
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mParentBusinessObject = ((RevampedDetailListing) this.mFragment).s();
        this.mAppState = GaanaApplication.getInstance();
        this.mRevampDetailObjManager = ((RevampedDetailListing) baseGaanaFragment).h();
        this.inflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.mDetailViewHelper = new RevampedDetailViewHelper(this.mContext, this.mFragment, ((RevampedDetailListing) this.mFragment).s());
    }

    public void setCarouselData(ArrayList<CarouselCardData> arrayList, int i, int i2) {
        if (this.carouselCardData == null) {
            this.carouselCardData = new ArrayList();
        } else {
            this.carouselCardData.clear();
        }
        this.carouselCardData.addAll(arrayList);
        this.mDetailType = i2;
        this.mCount = i;
        notifyDataSetChanged();
    }

    public void setCarouselListener(RevampedCarouselItemView revampedCarouselItemView) {
        this.homeCarouselListener = revampedCarouselItemView;
    }

    public int getItemViewType(int i) {
        return this.carouselCardData.get(i) != null ? ((CarouselCardData) this.carouselCardData.get(i)).getCard_type() : -1;
    }

    public void onClick(View view) {
        if (this.homeCarouselListener != null && (view.getTag() instanceof com.gaana.view.header.CarouselPagerAdapter.TagObject)) {
            com.gaana.view.header.CarouselPagerAdapter.TagObject tagObject = (com.gaana.view.header.CarouselPagerAdapter.TagObject) view.getTag();
            this.homeCarouselListener.setItemPosition(tagObject.getPosition());
            this.homeCarouselListener.onClickPerformed(view, tagObject.getItem());
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder carouselGalleryItemHolder;
        if (i == REVAMPED_DETAIL_CAROUSAL_CARD_TYPE.GALLERY.getNumVal()) {
            carouselGalleryItemHolder = new CarouselGalleryItemHolder(this.inflater.inflate(R.layout.revamped_carousal_item_gallery, viewGroup, false));
        } else if (i == REVAMPED_DETAIL_CAROUSAL_CARD_TYPE.IMAGE.getNumVal()) {
            carouselGalleryItemHolder = new CarouselImageItemHolder(this.inflater.inflate(R.layout.revamped_carousal_item_image, viewGroup, false));
        } else if (i == REVAMPED_DETAIL_CAROUSAL_CARD_TYPE.TEXT.getNumVal()) {
            carouselGalleryItemHolder = new CarouselTextItemHolder(this.inflater.inflate(R.layout.revamped_carousel_item_text, viewGroup, false));
        } else if (i == REVAMPED_DETAIL_CAROUSAL_CARD_TYPE.FOLLOW_ME.getNumVal()) {
            carouselGalleryItemHolder = new CarouselFollowMeItemHolder(this.inflater.inflate(R.layout.revamped_carousel_item_followme, viewGroup, false));
        } else if (i == REVAMPED_DETAIL_CAROUSAL_CARD_TYPE.CREATED_BY.getNumVal()) {
            carouselGalleryItemHolder = new CarouselCreatedByItemHolder(this.inflater.inflate(R.layout.revamped_carousel_item_createdby, viewGroup, false));
        } else if (i == REVAMPED_DETAIL_CAROUSAL_CARD_TYPE.VIDEO.getNumVal()) {
            carouselGalleryItemHolder = new CarouselVideoItemHolder(this.inflater.inflate(R.layout.revamped_carousel_item_video, viewGroup, false));
        } else if (i == REVAMPED_DETAIL_CAROUSAL_CARD_TYPE.AD.getNumVal()) {
            carouselGalleryItemHolder = new CarouselAdsItemHolder(this.inflater.inflate(R.layout.revamped_carousel_item_ads, viewGroup, false));
        } else if (i == REVAMPED_DETAIL_CAROUSAL_CARD_TYPE.DEEPLINK.getNumVal()) {
            carouselGalleryItemHolder = new CarouselDeeplinkItemHolder(this.inflater.inflate(R.layout.revamped_carousel_item_video, viewGroup, false));
        } else if (i == REVAMPED_DETAIL_CAROUSAL_CARD_TYPE.META.getNumVal()) {
            this.favoriteCountLayout = (LinearLayout) this.inflater.inflate(R.layout.revamped_favorite_count_layout, viewGroup, false);
            this.mLLFavoriteHolder = (LinearLayout) this.favoriteCountLayout.findViewById(R.id.ll_fav_parent);
            this.mAlbumCountView = (CustomTextView) this.favoriteCountLayout.findViewById(R.id.tvAlbumSongCount_Value);
            if (this.mParentBusinessObject instanceof Album) {
                this.mAlbumCountView.setTextColor(this.mContext.getResources().getColor(R.color.white));
            } else if (Constants.l) {
                this.mAlbumCountView.setTextColor(this.mContext.getResources().getColor(R.color.second_line_color_white));
            } else {
                this.mAlbumCountView.setTextColor(this.mContext.getResources().getColor(R.color.second_line_color));
            }
            if (this.mParentBusinessObject instanceof Artist) {
                initArtistFavLayout(((RevampedDetailListing) this.mFragment).s());
            }
            if (this.mDetailType == REVAMPED_DETAIL_TYPE.ALBUM.getNumVal()) {
                carouselGalleryItemHolder = new CarouselAlbumPlaylistMetaItemHolder(this.inflater.inflate(R.layout.revamped_carousel_item_playlist_meta, viewGroup, false));
            } else if (this.mDetailType == REVAMPED_DETAIL_TYPE.RADIO.getNumVal()) {
                carouselGalleryItemHolder = new CarouselRadioArtistMetaItemHolder(this.inflater.inflate(R.layout.revamped_carousel_radio_artist_meta_item_image, viewGroup, false));
            } else if (this.mDetailType == REVAMPED_DETAIL_TYPE.ARTIST.getNumVal()) {
                carouselGalleryItemHolder = new CarouselRadioArtistMetaItemHolder(this.inflater.inflate(R.layout.revamped_carousel_radio_artist_meta_item_image, viewGroup, false));
            } else if (this.mDetailType == REVAMPED_DETAIL_TYPE.PLAYLIST.getNumVal()) {
                carouselGalleryItemHolder = new CarouselAlbumPlaylistMetaItemHolder(this.inflater.inflate(R.layout.revamped_carousel_item_playlist_meta, viewGroup, false));
            } else {
                carouselGalleryItemHolder = new CarouselImageItemHolder(this.inflater.inflate(R.layout.revamped_carousal_item_image, viewGroup, false));
            }
        } else {
            carouselGalleryItemHolder = new CarouselImageItemHolder(this.inflater.inflate(R.layout.revamped_carousal_item_image, viewGroup, false));
        }
        ((MarginLayoutParams) carouselGalleryItemHolder.itemView.getLayoutParams()).topMargin = Util.b(15);
        return carouselGalleryItemHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ArrayList detail_entities = ((CarouselCardData) this.carouselCardData.get(i)).getDetail_entities();
        if (viewHolder != null) {
            try {
                handleHolderUI(viewHolder, detail_entities, i);
            } catch (Exception unused) {
            }
        }
    }

    public int getItemCount() {
        return this.mCount;
    }

    private void handleHolderUI(ViewHolder viewHolder, final ArrayList<CarouselDetailEntity> arrayList, int i) {
        CharSequence charSequence;
        CarouselCreatedByItemHolder carouselCreatedByItemHolder;
        if (viewHolder instanceof CarouselGalleryItemHolder) {
            CarouselGalleryItemHolder carouselGalleryItemHolder = (CarouselGalleryItemHolder) viewHolder;
            carouselGalleryItemHolder.leftImage.bindImage(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_atw());
            carouselGalleryItemHolder.leftImage.setScaleType(ScaleType.FIT_XY);
            if ("1".equals(((CarouselDetailEntity) arrayList.get(1)).getDetail_entity_info().getDetail_entity_type())) {
                carouselGalleryItemHolder.centerImage.setVisibility(8);
                carouselGalleryItemHolder.centerImageSquare.setVisibility(0);
                carouselGalleryItemHolder.centerImageSquare.bindImage(((CarouselDetailEntity) arrayList.get(1)).getDetail_entity_info().getDetail_entity_atw());
                carouselGalleryItemHolder.centerImageSquare.setScaleType(ScaleType.FIT_XY);
            } else {
                carouselGalleryItemHolder.centerImage.bindImage(((CarouselDetailEntity) arrayList.get(1)).getDetail_entity_info().getDetail_entity_atw());
                carouselGalleryItemHolder.centerImage.setScaleType(ScaleType.FIT_XY);
            }
            carouselGalleryItemHolder.rightImage.bindImage(((CarouselDetailEntity) arrayList.get(2)).getDetail_entity_info().getDetail_entity_atw());
            carouselGalleryItemHolder.rightImage.setScaleType(ScaleType.FIT_XY);
        } else if (viewHolder instanceof CarouselImageItemHolder) {
            if (i == 0 && getItemViewType(0) == REVAMPED_DETAIL_CAROUSAL_CARD_TYPE.META.getNumVal()) {
                CarouselImageItemHolder carouselImageItemHolder = (CarouselImageItemHolder) viewHolder;
                this.mDetailViewHelper.bindArtworkPlaylistAlbum(carouselImageItemHolder.image);
                carouselImageItemHolder.bottomInfo.removeAllViews();
                carouselImageItemHolder.bottomInfo.addView(this.favoriteCountLayout);
            } else {
                ((CarouselImageItemHolder) viewHolder).image.bindImage(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_atw());
            }
            ((CarouselImageItemHolder) viewHolder).image.setScaleType(ScaleType.FIT_XY);
        } else if (viewHolder instanceof CarouselRadioArtistMetaItemHolder) {
            if (i == 0 && getItemViewType(0) == REVAMPED_DETAIL_CAROUSAL_CARD_TYPE.META.getNumVal()) {
                CarouselRadioArtistMetaItemHolder carouselRadioArtistMetaItemHolder = (CarouselRadioArtistMetaItemHolder) viewHolder;
                this.mDetailViewHelper.bindArtworkRadioandArtist(carouselRadioArtistMetaItemHolder.image);
                carouselRadioArtistMetaItemHolder.infoText.setText(this.mParentBusinessObject.getName());
                carouselRadioArtistMetaItemHolder.infoText.setTypeface(carouselRadioArtistMetaItemHolder.infoText.getTypeface(), 1);
                carouselRadioArtistMetaItemHolder.infoText.setIncludeFontPadding(false);
                charSequence = "";
                if (this.mParentBusinessObject instanceof Radio) {
                    charSequence = Util.q(((Radio) this.mParentBusinessObject).getFavorite_count());
                } else if (this.mParentBusinessObject instanceof Artist) {
                    charSequence = Util.q(((Artist) this.mParentBusinessObject).getFavoriteCount());
                }
                if (TextUtils.isEmpty(charSequence)) {
                    carouselRadioArtistMetaItemHolder.bottomInfo.setVisibility(8);
                } else {
                    carouselRadioArtistMetaItemHolder.bottomInfo.setVisibility(0);
                    TextView textView = carouselRadioArtistMetaItemHolder.favCountText;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("+ ");
                    stringBuilder.append(charSequence);
                    stringBuilder.append(" ");
                    stringBuilder.append(this.mContext.getString(R.string.fav));
                    textView.setText(stringBuilder.toString());
                }
            } else {
                ((CarouselRadioArtistMetaItemHolder) viewHolder).image.bindImage(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_atw());
            }
            ((CarouselRadioArtistMetaItemHolder) viewHolder).image.setScaleType(ScaleType.FIT_XY);
        } else if (viewHolder instanceof CarouselTextItemHolder) {
            CarouselTextItemHolder carouselTextItemHolder = (CarouselTextItemHolder) viewHolder;
            carouselTextItemHolder.tvTitle.setText(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_title());
            carouselTextItemHolder.tvDesc.setText(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_desc());
            carouselTextItemHolder.tvUpdate.setText(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_title());
        } else if (viewHolder instanceof CarouselFollowMeItemHolder) {
            CarouselFollowMeItemHolder carouselFollowMeItemHolder = (CarouselFollowMeItemHolder) viewHolder;
            carouselFollowMeItemHolder.fTitle.setText(this.mContext.getString(R.string.follow_me_on));
            if (arrayList != null && arrayList.size() > 0) {
                carouselFollowMeItemHolder.followme_buttons.removeAllViews();
                for (i = 0; i < arrayList.size(); i++) {
                    LinearLayout linearLayout = (LinearLayout) this.inflater.inflate(R.layout.revamped_carousel_item_follow_me_inside, carouselFollowMeItemHolder.followme_buttons, false);
                    linearLayout.findViewById(R.id.f_layout).setVisibility(0);
                    ((CrossFadeImageView) linearLayout.findViewById(R.id.follow_image)).bindImage(((CarouselDetailEntity) arrayList.get(i)).getDetail_entity_info().getDetail_entity_atw());
                    ((CrossFadeImageView) linearLayout.findViewById(R.id.follow_image)).setScaleType(ScaleType.FIT_XY);
                    ((TextView) linearLayout.findViewById(R.id.follow_text)).setText(((CarouselDetailEntity) arrayList.get(i)).getDetail_entity_info().getDetail_entity_title());
                    linearLayout.findViewById(R.id.f_layout).setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            RevampedCarouselPagerAdapter.this.openExternalBrowser(((CarouselDetailEntity) arrayList.get(i)).getDetail_entity_info().getDetail_entity_url());
                        }
                    });
                    carouselFollowMeItemHolder.followme_buttons.addView(linearLayout);
                }
            }
        } else if (viewHolder instanceof CarouselCreatedByItemHolder) {
            carouselCreatedByItemHolder = (CarouselCreatedByItemHolder) viewHolder;
            carouselCreatedByItemHolder.createdByImage.bindImage(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_atw());
            carouselCreatedByItemHolder.titleText.setText(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_title());
            if (this.mParentBusinessObject instanceof Playlist) {
                carouselCreatedByItemHolder.descText.setText(((Playlist) this.mParentBusinessObject).getCreatedby());
            }
        } else if (viewHolder instanceof CarouselAlbumPlaylistMetaItemHolder) {
            if (i == 0 && getItemViewType(0) == REVAMPED_DETAIL_CAROUSAL_CARD_TYPE.META.getNumVal()) {
                BusinessObject s = ((RevampedDetailListing) this.mFragment).s();
                if (s != null) {
                    boolean z = s instanceof Playlist;
                    if (z || (s instanceof Album)) {
                        CarouselAlbumPlaylistMetaItemHolder carouselAlbumPlaylistMetaItemHolder = (CarouselAlbumPlaylistMetaItemHolder) viewHolder;
                        this.mDetailViewHelper.bindArtworkPlaylistAlbum(carouselAlbumPlaylistMetaItemHolder.createdByImageRect);
                        carouselAlbumPlaylistMetaItemHolder.titleText.setText(s.getName());
                        carouselAlbumPlaylistMetaItemHolder.titleText.setTypeface(carouselAlbumPlaylistMetaItemHolder.titleText.getTypeface(), 1);
                        carouselAlbumPlaylistMetaItemHolder.titleText.setIncludeFontPadding(false);
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(TextUtils.isEmpty(s.getCount()) ? " " : s.getCount());
                        stringBuilder2.append(" ");
                        stringBuilder2.append(this.mContext.getString(R.string.songs));
                        carouselAlbumPlaylistMetaItemHolder.descText.setText(stringBuilder2.toString());
                        charSequence = "";
                        if (z) {
                            charSequence = Util.q(((Playlist) s).getFavoriteCount());
                        } else if (s instanceof Album) {
                            charSequence = Util.q(((Album) s).getFavoriteCount());
                        }
                        if (TextUtils.isEmpty(charSequence)) {
                            carouselAlbumPlaylistMetaItemHolder.infoLayout.setVisibility(8);
                            return;
                        }
                        carouselAlbumPlaylistMetaItemHolder.infoLayout.setVisibility(0);
                        TextView textView2 = carouselAlbumPlaylistMetaItemHolder.favCountText;
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("+ ");
                        stringBuilder3.append(charSequence);
                        stringBuilder3.append(" ");
                        stringBuilder3.append(this.mContext.getString(R.string.fav));
                        textView2.setText(stringBuilder3.toString());
                        return;
                    }
                }
                carouselCreatedByItemHolder = (CarouselCreatedByItemHolder) viewHolder;
                carouselCreatedByItemHolder.createdByImage.bindImage(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_atw());
                carouselCreatedByItemHolder.titleText.setText(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_title());
                carouselCreatedByItemHolder.descText.setText(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_desc());
            }
        } else if (viewHolder instanceof CarouselVideoItemHolder) {
            CarouselVideoItemHolder carouselVideoItemHolder = (CarouselVideoItemHolder) viewHolder;
            carouselVideoItemHolder.videoImage.bindImage(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_atw());
            carouselVideoItemHolder.videoImage.setScaleType(ScaleType.FIT_XY);
            carouselVideoItemHolder.playImage.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    DetailEntityInfo detail_entity_info = ((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info();
                    YouTubeVideo youTubeVideo = new YouTubeVideo();
                    youTubeVideo.setBusinessObjId(detail_entity_info.getDetail_entity_id());
                    youTubeVideo.b(detail_entity_info.getDetail_entity_title());
                    youTubeVideo.d(detail_entity_info.getDetail_entity_atw());
                    youTubeVideo.c("");
                    youTubeVideo.a(detail_entity_info.getDetail_entity_url());
                    RevampedCarouselPagerAdapter.this.launchYouTubePlayer("", detail_entity_info.getDetail_entity_url(), youTubeVideo);
                }
            });
        } else if (viewHolder instanceof CarouselAdsItemHolder) {
            CarouselAdsItemHolder carouselAdsItemHolder = (CarouselAdsItemHolder) viewHolder;
            carouselAdsItemHolder.videoImage.bindImage(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_atw());
            carouselAdsItemHolder.videoImage.setScaleType(ScaleType.FIT_XY);
            carouselAdsItemHolder.tvDesc.setText(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_desc());
            carouselAdsItemHolder.tvTitle.setText(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_title());
            viewHolder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String detail_entity_url = ((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_url();
                    if (!TextUtils.isEmpty(detail_entity_url)) {
                        if (detail_entity_url.contains(".html")) {
                            RevampedCarouselPagerAdapter.this.openLinkinWebView(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_title(), detail_entity_url);
                        } else {
                            c.a(RevampedCarouselPagerAdapter.this.mContext).a(RevampedCarouselPagerAdapter.this.mContext, detail_entity_url, GaanaApplication.getInstance());
                        }
                    }
                }
            });
        } else if (viewHolder instanceof CarouselDeeplinkItemHolder) {
            CarouselDeeplinkItemHolder carouselDeeplinkItemHolder = (CarouselDeeplinkItemHolder) viewHolder;
            carouselDeeplinkItemHolder.videoImage.bindImage(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_atw());
            carouselDeeplinkItemHolder.videoImage.setScaleType(ScaleType.FIT_XY);
            carouselDeeplinkItemHolder.playButton.setVisibility(8);
            viewHolder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    String detail_entity_url = ((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_url();
                    if (!TextUtils.isEmpty(detail_entity_url)) {
                        if (detail_entity_url.contains(".html")) {
                            RevampedCarouselPagerAdapter.this.openLinkinWebView(((CarouselDetailEntity) arrayList.get(0)).getDetail_entity_info().getDetail_entity_title(), detail_entity_url);
                        } else {
                            c.a(RevampedCarouselPagerAdapter.this.mContext).a(RevampedCarouselPagerAdapter.this.mContext, detail_entity_url, GaanaApplication.getInstance());
                        }
                    }
                }
            });
        }
    }

    private void openExternalBrowser(String str) {
        if (!TextUtils.isEmpty(str) && URLUtil.isValidUrl(str)) {
            try {
                this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(this.mContext, this.mContext.getString(R.string.error_generic_unableto_process), 1).show();
            }
        }
    }

    private void openLinkinWebView(String str, String str2) {
        Intent intent = new Intent(this.mContext, WebViewActivity.class);
        intent.putExtra("EXTRA_WEBVIEW_URL", str2);
        intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
        intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
        intent.putExtra("title", str);
        this.mContext.startActivity(intent);
    }

    private void notifyAdClick(String str) {
        u.a().b("Revamped Showcase Ad", "Click");
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.a(String.class);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
            }
        }, uRLManager);
    }

    private void launchVideoPlayerActivity(String str) {
        if (!Util.j(this.mContext) || GaanaApplication.getInstance().isAppInOfflineMode()) {
            ap.a().f(this.mContext);
            return;
        }
        Intent intent;
        if (GaanaMusicService.t()) {
            o.a(this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
            Constants.dc = true;
        }
        if (f.v().w()) {
            f.v().F();
            Constants.dc = true;
        }
        if (d.g()) {
            intent = new Intent(this.mContext, VideoPlayerActivityTwo.class);
        } else {
            intent = new Intent(this.mContext, FullScreenVideoPlayerActivity.class);
        }
        intent.setAction("com.google.android.exoplayer.demo.action.VIEW");
        intent.putExtra("share_url", "");
        intent.putExtra("video_url", str);
        ((GaanaActivity) this.mContext).startActivityForResult(intent, 1001);
    }

    /* Access modifiers changed, original: protected */
    public void launchYouTubePlayer(String str, String str2, BusinessObject businessObject) {
        Util.a(this.mContext, str, str2, businessObject, 0);
    }

    public void initFavLayout(BusinessObject businessObject) {
        StringBuilder stringBuilder;
        String count = businessObject.getCount();
        if (this.mLLFavoriteHolder == null) {
            if (((RevampedDetailListing) this.mFragment).g() != null) {
                initFavLayoutifNull(((RevampedDetailListing) this.mFragment).g());
            } else {
                return;
            }
        }
        this.mLLFavoriteHolder.removeAllViews();
        boolean z = businessObject instanceof Album;
        if (z) {
            this.favoritesCount = ((Album) businessObject).getFavoriteCount();
        } else if (businessObject instanceof Playlist) {
            this.favoritesCount = ((Playlist) businessObject).getFavoriteCount();
        } else if (businessObject instanceof Radio) {
            this.favoritesCount = ((Radio) businessObject).getFavorite_count();
        }
        if (TextUtils.isEmpty(count) || (businessObject instanceof Playlist)) {
            count = "0";
        }
        if (Integer.parseInt(count.trim()) < 2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(Util.q(count));
            stringBuilder.append(" ");
            stringBuilder.append(this.mContext.getString(R.string.song_text));
            this.songString = stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(Util.q(count));
            stringBuilder.append(" ");
            stringBuilder.append(this.mContext.getString(R.string.songs_text));
            this.songString = stringBuilder.toString();
        }
        if (!TextUtils.isEmpty(this.favoritesCount) && Integer.parseInt(this.favoritesCount.trim()) > 0) {
            if (!z) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.songString);
                stringBuilder.append(" | ");
                this.songString = stringBuilder.toString();
            }
            if (Integer.parseInt(this.favoritesCount.trim()) < 2) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(Util.q(this.favoritesCount));
                stringBuilder.append(" ");
                stringBuilder.append(this.mContext.getString(R.string.favorite_text));
                this.favoriteString = stringBuilder.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(Util.q(this.favoritesCount));
                stringBuilder.append(" ");
                stringBuilder.append(this.mContext.getString(R.string.favorites_text));
                this.favoriteString = stringBuilder.toString();
            }
            stringBuilder = new StringBuilder();
            if (count.equals("0")) {
                count = "";
            } else {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(this.songString);
                stringBuilder2.append("");
                count = stringBuilder2.toString();
            }
            stringBuilder.append(count);
            stringBuilder.append(z ? "\n" : "");
            stringBuilder.append(this.favoriteString);
            this.mAlbumCountView.setText(stringBuilder.toString());
        } else if (!(TextUtils.isEmpty(count) || count.equals("0"))) {
            this.mAlbumCountView.setText(this.songString);
        }
        boolean z2 = false;
        if (businessObject instanceof Playlist) {
            z2 = ((Playlist) businessObject).isParentalWarningEnabled();
        } else if (z) {
            z2 = ((Album) businessObject).isParentalWarningEnabled();
        }
        if (z2) {
            this.mAlbumCountView.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(Util.Y()), null, null, null);
        } else {
            this.mAlbumCountView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    public void refreshArtistTabs(BusinessObject businessObject, int i, int i2) {
        StringBuilder stringBuilder;
        if (i == 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(i2);
            stringBuilder.append("");
            this.localSongsCount = stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(i2);
            stringBuilder.append("");
            this.localAlbumCount = stringBuilder.toString();
        }
        if (this.localSongsCount != null && this.localAlbumCount != null && businessObject.isLocalMedia()) {
            setArtistFavElements(this.localSongsCount, this.localAlbumCount);
        }
    }

    private void initArtistFavLayout(BusinessObject businessObject) {
        String businessObjId = businessObject.getBusinessObjId();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(com.constants.c.x);
        stringBuilder.append(businessObjId);
        businessObjId = stringBuilder.toString();
        URLManager uRLManager = new URLManager();
        uRLManager.a(businessObjId);
        uRLManager.a(Artists.class);
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject != null) {
                    Artist artist = (Artist) businessObject.getArrListBusinessObj().get(0);
                    if (artist != null) {
                        RevampedCarouselPagerAdapter.this.setArtistFavElements(artist.getSongsCount(), artist.getAlbumsCount());
                    }
                }
            }
        }, uRLManager);
    }

    private void setArtistFavElements(String str, String str2) {
        String stringBuilder;
        this.mLLFavoriteHolder.removeAllViews();
        long j = 0;
        long parseLong = !TextUtils.isEmpty(str) ? Long.parseLong(str.trim()) : 0;
        if (!TextUtils.isEmpty(str2)) {
            j = Long.parseLong(str2.trim());
        }
        StringBuilder stringBuilder2;
        if (parseLong < 2) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(Util.f(parseLong));
            stringBuilder2.append(" ");
            stringBuilder2.append(this.mContext.getString(R.string.song_text));
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(Util.f(parseLong));
            stringBuilder2.append(" ");
            stringBuilder2.append(this.mContext.getString(R.string.songs_text));
            stringBuilder = stringBuilder2.toString();
        }
        StringBuilder stringBuilder3;
        if (j < 2) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(Util.f(j));
            stringBuilder3.append(" ");
            stringBuilder3.append(this.mContext.getString(R.string.album_text));
            str = stringBuilder3.toString();
        } else {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(Util.f(j));
            stringBuilder3.append(" ");
            stringBuilder3.append(this.mContext.getString(R.string.albums_text));
            str = stringBuilder3.toString();
        }
        this.mAlbumCountView.setVisibility(0);
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append(stringBuilder);
        stringBuilder4.append(" | ");
        stringBuilder4.append(str);
        this.combinedText = stringBuilder4.toString();
        this.mAlbumCountView.setText(this.combinedText);
    }

    private void initFavLayoutifNull(ViewGroup viewGroup) {
        this.favoriteCountLayout = (LinearLayout) this.inflater.inflate(R.layout.revamped_favorite_count_layout, viewGroup, false);
        this.mLLFavoriteHolder = (LinearLayout) this.favoriteCountLayout.findViewById(R.id.ll_fav_parent);
        this.mAlbumCountView = (CustomTextView) this.favoriteCountLayout.findViewById(R.id.tvAlbumSongCount_Value);
    }
}
