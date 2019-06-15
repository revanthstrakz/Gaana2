package com.gaana.view.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.FavoritesFragment;
import com.fragments.MyMusicFragment;
import com.fragments.MyMusicItemFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.DownloadManager;
import com.managers.aj;
import com.managers.ap;
import com.utilities.Util;

public class AlbumItemView extends BaseItemView {
    private int albumPosition;
    private CrossFadeImageView mCrossFadeImageIcon;
    private TextView tvBottomHeading;
    private TextView tvTopHeading;

    public int getAlbumPosition() {
        return this.albumPosition;
    }

    public AlbumItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.albumPosition = -1;
        this.mLayoutId = R.layout.view_item_album;
        ((BaseActivity) this.mContext).currentItem = "Album";
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(R.layout.view_item_album, view, viewGroup);
        }
        super.getPoplatedView(view, businessObject);
        view.findViewById(R.id.clickoptionImage).setTag(businessObject);
        view.findViewById(R.id.clickoptionImage).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AlbumItemView.this.showOptionMenu(view);
            }
        });
        return getDataFilledView(view, businessObject);
    }

    private View getDataFilledView(View view, BusinessObject businessObject) {
        Album album = (Album) businessObject;
        this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
        this.tvBottomHeading = (TextView) view.findViewById(R.id.tvBottomHeading);
        this.mCrossFadeImageIcon.bindImage(album.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        this.tvTopHeading.setText(album.getName());
        this.tvBottomHeading.setText(album.getArtistNames().toUpperCase());
        int i = Constants.l ? R.drawable.vector_ic_explicit_content_white : R.drawable.vector_ic_explicit_content;
        if (album.isParentalWarningEnabled()) {
            this.tvBottomHeading.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(i), null, null, null);
        } else {
            this.tvBottomHeading.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        if (ap.a().a(album)) {
            this.tvTopHeading.setTextAppearance(this.mContext, R.style.grid_caption);
            this.tvBottomHeading.setTextAppearance(this.mContext, R.style.item_text_second_line);
        } else {
            this.tvTopHeading.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
            this.tvBottomHeading.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
        }
        return view;
    }

    private View getDataFilledView(PlaylistGridHolder playlistGridHolder, BusinessObject businessObject) {
        Album album = (Album) businessObject;
        this.mCrossFadeImageIcon = playlistGridHolder.crossFadeImageView;
        this.tvTopHeading = playlistGridHolder.tvTopHeading;
        this.mCrossFadeImageIcon.bindImage(album.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        if (album.isParentalWarningEnabled()) {
            Util.a(this.tvTopHeading, album.getName());
        } else {
            this.tvTopHeading.setText(album.getName());
        }
        playlistGridHolder.play_icon.setVisibility(4);
        if (!ap.a().a(album)) {
            this.tvTopHeading.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
        }
        return playlistGridHolder.itemView;
    }

    public View getPoplatedViewForGrid(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder;
        this.mView = playlistGridHolder.itemView;
        this.mView.setTag(businessObject);
        this.mView.setOnClickListener(this);
        super.getPoplatedView(this.mView, businessObject, viewGroup);
        return getDataFilledView(playlistGridHolder, businessObject);
    }

    public void onClick(View view) {
        BusinessObject businessObject = (Album) view.getTag();
        if (!businessObject.isLocalMedia()) {
            if ("1".equalsIgnoreCase(businessObject.getLocationAvailability()) && "0".equalsIgnoreCase(businessObject.getDeviceAvailability())) {
                ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
                return;
            } else if ("0".equalsIgnoreCase(businessObject.getLocationAvailability()) && "1".equalsIgnoreCase(businessObject.getDeviceAvailability())) {
                ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
                return;
            } else if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().b(businessObject).booleanValue()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_album));
                return;
            } else if (!Util.j(this.mContext) && !DownloadManager.c().b(businessObject).booleanValue()) {
                ap.a().f(this.mContext);
                return;
            } else if ((this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) && !ap.a().a(businessObject, null)) {
                aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.toast_subscription_expired));
                return;
            }
        }
        super.onClick(view);
        if (TextUtils.isEmpty(businessObject.getChannelPageAdCode())) {
            Constants.i = false;
            Constants.j = "";
        } else {
            Constants.i = true;
            Constants.j = businessObject.getChannelPageAdCode();
        }
        StringBuilder stringBuilder;
        if (((BaseActivity) this.mContext).currentScreen.equalsIgnoreCase("Browse")) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Position ");
            stringBuilder.append(getAlbumPosition());
            stringBuilder.append(" - Album - ");
            stringBuilder.append(businessObject.getBusinessObjId());
            ((BaseActivity) this.mContext).sendGAEvent("Browse", "New Releases click ", stringBuilder.toString());
        } else {
            BaseActivity baseActivity = (BaseActivity) this.mContext;
            String str = ((BaseActivity) this.mContext).currentScreen;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Album Detail : ");
            stringBuilder2.append(businessObject.getEnglishName());
            String stringBuilder3 = stringBuilder2.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
            stringBuilder.append(" - ");
            stringBuilder.append(((BaseActivity) this.mContext).currentFavpage);
            stringBuilder.append(" - Album Detail");
            baseActivity.sendGAEvent(str, stringBuilder3, stringBuilder.toString());
        }
        populateAlbumListing(businessObject);
    }

    public String getSectionName() {
        if (this.mFragment instanceof DownloadDetailsFragment) {
            return PLAYOUT_SECTION_TYPE.DOWNLOADS.name();
        }
        if (this.mFragment instanceof FavoritesFragment) {
            return PLAYOUT_SECTION_TYPE.FAVORITES.name();
        }
        if (this.mFragment instanceof MyMusicFragment) {
            return PLAYOUT_SECTION_TYPE.LOCAL.name();
        }
        if (this.mFragment instanceof MyMusicItemFragment) {
            return PLAYOUT_SECTION_TYPE.MYMUSIC_ALBUMS.name();
        }
        return PLAYOUT_SECTION_TYPE.SIMILAR_ALBUM.name();
    }
}
