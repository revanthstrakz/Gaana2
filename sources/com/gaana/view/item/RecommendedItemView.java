package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.constants.Constants;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.DynamicOccasionFragment;
import com.dynamicview.c;
import com.fragments.BaseGaanaFragment;
import com.fragments.DiscoverDetailFragment;
import com.fragments.FavoritesFragment;
import com.fragments.MyMusicFragment;
import com.fragments.MyMusicItemFragment;
import com.fragments.RadioActivityFragment;
import com.fragments.RadioDetailsMaterialListing;
import com.fragments.RevampedDetailListing;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.DiscoverTags.DiscoverTag;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.view.PulsatorView;
import com.gaana.view.item.BaseItemView.RecommendedItemHolder;
import com.gaana.view.item.BaseItemView.TwoRecommendedItemHolder;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.af;
import com.managers.aj;
import com.managers.ap;
import com.managers.ap.a;
import com.managers.aq;
import com.managers.n;
import com.managers.q;
import com.managers.u;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerStatus.PlayerStates;
import com.services.l.ag;
import com.services.l.s;
import com.services.l.v;
import com.utilities.Util;
import com.utilities.d;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecommendedItemView extends BaseItemView implements OnClickListener, a {
    private String mGASectionName = "";
    private int mLayoutHomeResourceId = R.layout.grid_two_item_recommended;
    private int mLayoutResourceId = R.layout.view_recommended_item;
    private int mPosition = -1;
    private v onClickItemUpdateListener;

    public static class DiscoverGridHolder extends ViewHolder {
        public CrossFadeImageView crossFadeImageView;
        public TextView discoverTagText;

        public DiscoverGridHolder(View view) {
            super(view);
            this.crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.discoverTagImg);
            this.discoverTagText = (TextView) view.findViewById(R.id.discoverTagName);
        }
    }

    public void setBlinker(String str, BusinessObject businessObject, PulsatorView pulsatorView) {
    }

    public RecommendedItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
    }

    public View getEmptyView(View view, ViewGroup viewGroup, BusinessObjectType businessObjectType) {
        return view == null ? inflateView(this.mLayoutResourceId, viewGroup) : view;
    }

    public void setGASectionName(String str) {
        this.mGASectionName = str;
    }

    public String getSectionName() {
        return this.mGASectionName;
    }

    public View getPoplatedViewForDetail(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, int i) {
        RecommendedItemHolder recommendedItemHolder = (RecommendedItemHolder) viewHolder;
        if (recommendedItemHolder.parentLayout != null) {
            recommendedItemHolder.parentLayout.setTag(businessObject);
        }
        getPoplatedViewGrid(recommendedItemHolder, businessObject, viewGroup);
        if (recommendedItemHolder.downloadImage != null) {
            recommendedItemHolder.downloadImage.setOnClickListener(this);
        }
        if (recommendedItemHolder.favImage != null) {
            recommendedItemHolder.favImage.setOnClickListener(this);
        }
        return this.mView;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup, boolean z) {
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutResourceId, view, viewGroup);
            view.findViewById(R.id.discoverTagImg).setOnClickListener(this);
        }
        TextView textView = (TextView) view.findViewById(R.id.discoverTagName);
        if (businessObject instanceof Item) {
            Item item = (Item) businessObject;
            if (item != null) {
                view.findViewById(R.id.discoverTagImg).setTag(item);
            }
            if (view.findViewById(R.id.view_grid_item_relative) != null) {
                view.findViewById(R.id.view_grid_item_relative).setTag(item);
            }
            if (z && (view.findViewById(R.id.discoverTagImg) instanceof SquareImageView)) {
                SquareImageView squareImageView = (SquareImageView) view.findViewById(R.id.discoverTagImg);
                String str = null;
                if (item.getArtwork() != null) {
                    str = item.getArtwork().replace("80x80", "175x175");
                }
                squareImageView.bindImage(str, this.mAppState.isAppInOfflineMode());
            }
            if (textView != null) {
                textView.setText(item.getName());
            }
        }
        if (!((businessObject instanceof DiscoverTag) && ((DiscoverTag) businessObject).getTagEntityType() == null)) {
            initFavoriteDownload(view, businessObject);
        }
        view.setOnClickListener(this);
        return super.getPoplatedView(view, businessObject, viewGroup);
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            if (d.b()) {
                view = super.createNewBaseView(R.layout.view_item_discover, view, viewGroup);
            } else {
                view = super.createNewBaseView(R.layout.view_item_discover_adjustable, view, viewGroup);
            }
            view.findViewById(R.id.discoverTagImg).setOnClickListener(this);
        }
        TextView textView = (TextView) view.findViewById(R.id.discoverTagName);
        if (businessObject instanceof Item) {
            Item item = (Item) businessObject;
            if (item != null) {
                view.findViewById(R.id.discoverTagImg).setTag(item);
            }
            if (view.findViewById(R.id.view_grid_item_relative) != null) {
                view.findViewById(R.id.view_grid_item_relative).setTag(item);
            }
            String str = null;
            if (view.findViewById(R.id.discoverTagImg) instanceof SquareImageView) {
                SquareImageView squareImageView = (SquareImageView) view.findViewById(R.id.discoverTagImg);
                if (item.getArtwork() != null) {
                    str = item.getArtwork().replace("80x80", "175x175");
                }
                squareImageView.bindImage(str, squareImageView.getScaleType());
            } else if (view.findViewById(R.id.discoverTagImg) instanceof CrossFadeImageView) {
                CrossFadeImageView crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.discoverTagImg);
                if (item.getArtwork() != null) {
                    str = item.getArtwork().replace("80x80", "175x175");
                }
                crossFadeImageView.bindImage(str, crossFadeImageView.getScaleType());
            }
            if (textView != null) {
                GaanaApplication gaanaApplication = this.mAppState;
                if (GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                    textView.setText(Util.p(item.getName().toUpperCase()), BufferType.SPANNABLE);
                } else {
                    textView.setText(item.getName().toUpperCase(), BufferType.SPANNABLE);
                }
            }
        }
        if (!((businessObject instanceof DiscoverTag) && ((DiscoverTag) businessObject).getTagEntityType() == null)) {
            initFavoriteDownload(view, businessObject);
        }
        view.setOnClickListener(this);
        return super.getPoplatedView(view, businessObject, viewGroup);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        DiscoverGridHolder discoverGridHolder = (DiscoverGridHolder) viewHolder;
        this.mView = discoverGridHolder.itemView;
        TextView textView = discoverGridHolder.discoverTagText;
        if (businessObject instanceof Item) {
            Item item = (Item) businessObject;
            if (item != null) {
                discoverGridHolder.crossFadeImageView.setTag(item);
            }
            if (discoverGridHolder.crossFadeImageView instanceof SquareImageView) {
                ((SquareImageView) discoverGridHolder.crossFadeImageView).bindImage(item.getArtwork() != null ? item.getArtwork().replace("80x80", "175x175") : null, this.mAppState.isAppInOfflineMode());
            } else if (discoverGridHolder.crossFadeImageView instanceof CrossFadeImageView) {
                discoverGridHolder.crossFadeImageView.bindImage(item.getArtwork() != null ? item.getArtwork().replace("80x80", "175x175") : null, this.mAppState.isAppInOfflineMode());
            }
            if (textView != null) {
                GaanaApplication gaanaApplication = this.mAppState;
                if (GaanaApplication.getLanguage(this.mContext).equalsIgnoreCase("English")) {
                    if (item.getName() != null) {
                        textView.setText(Util.p(item.getName().toUpperCase()), BufferType.SPANNABLE);
                    }
                } else if (item.getName() != null) {
                    textView.setText(item.getName().toUpperCase(), BufferType.SPANNABLE);
                }
            }
            List entityInfo = item.getEntityInfo();
            boolean z = false;
            if (entityInfo != null) {
                int i = 0;
                while (i < entityInfo.size()) {
                    if (!((EntityInfo) entityInfo.get(i)).getKey().equals("parental_warning")) {
                        i++;
                    } else if (!(((EntityInfo) entityInfo.get(i)).getValue() instanceof Double)) {
                        z = ((EntityInfo) entityInfo.get(i)).getValue().equals("1");
                    } else if (Double.compare(((Double) ((EntityInfo) entityInfo.get(i)).getValue()).doubleValue(), 1.0d) == 0) {
                        z = true;
                    }
                }
            }
            if (z) {
                textView.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(Util.Y()), null, null, null);
            } else {
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
        }
        if (!((businessObject instanceof DiscoverTag) && ((DiscoverTag) businessObject).getTagEntityType() == null)) {
            initFavoriteDownload(this.mView, businessObject);
        }
        this.mView.setOnClickListener(this);
        return super.getPoplatedView(this.mView, businessObject, viewGroup);
    }

    private void handleOccasionEntity(Item item) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This item");
        } else if (Util.j(this.mContext)) {
            ArrayList arrayList = (ArrayList) item.getEntityInfo();
            if (arrayList != null) {
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    if (((EntityInfo) arrayList.get(i)).getKey().equals("url")) {
                        final String str = (String) ((EntityInfo) arrayList.get(i)).getValue();
                        if (Util.j(this.mContext) && !this.mAppState.isAppInOfflineMode() && !TextUtils.isEmpty(str) && str.contains("occasion")) {
                            String substring = str.substring(str.lastIndexOf("/") + 1, str.length());
                            u a = u.a();
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("OP_");
                            stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                            String stringBuilder2 = stringBuilder.toString();
                            StringBuilder stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(substring);
                            stringBuilder3.append("_Click");
                            a.b(stringBuilder2, stringBuilder3.toString());
                            c.a().a(new ag() {
                                public void onOccasionResponse() {
                                    BaseGaanaFragment dynamicOccasionFragment = new DynamicOccasionFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("OCCASION_URL", str);
                                    bundle.putString("OCCASION_REFRESH_INTERVAL", null);
                                    dynamicOccasionFragment.setArguments(bundle);
                                    ((GaanaActivity) RecommendedItemView.this.mContext).displayFragment(dynamicOccasionFragment);
                                }

                                public void onOccasionError() {
                                    aj.a().a(RecommendedItemView.this.mContext, RecommendedItemView.this.getResources().getString(R.string.error_download_no_internet));
                                }
                            }, str, null, false);
                        }
                    } else {
                        i++;
                    }
                }
            }
        } else {
            ap.a().f(this.mContext);
        }
    }

    private void executeRequest(final View view) {
        BusinessObject businessObject = (BusinessObject) view.getTag();
        if (businessObject instanceof Item) {
            Item item = (Item) businessObject;
            String entityType = item.getEntityType();
            if (entityType.equals(com.constants.c.c.a)) {
                businessObject = populatePlaylistClicked(item);
            } else if (entityType.equals(com.constants.c.c.b)) {
                businessObject = populateAlbumClicked(item);
            } else if (entityType.equals(com.constants.c.d.c) || entityType.equals(com.constants.c.d.d)) {
                businessObject = populateRadioClicked(item);
            } else if (entityType.equals(com.constants.c.c.c)) {
                businessObject = populateTrackClicked(item);
            } else if (entityType.equals(com.constants.c.c.d)) {
                businessObject = populateArtistClicked(item);
            } else if (entityType.equals(com.constants.c.c.f)) {
                businessObject = populateVideoClicked(item);
            } else if (entityType.equals(com.constants.c.c.i)) {
                if ("1".equalsIgnoreCase(item.getLocationAvailability()) && "0".equalsIgnoreCase(item.getDeviceAvailability())) {
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
                    return;
                } else if ("0".equalsIgnoreCase(item.getLocationAvailability()) && "1".equalsIgnoreCase(item.getDeviceAvailability())) {
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
                    return;
                } else {
                    handleOccasionEntity(item);
                }
            }
        }
        if (businessObject != null) {
            if ("1".equalsIgnoreCase(businessObject.getLocationAvailability()) && "0".equalsIgnoreCase(businessObject.getDeviceAvailability())) {
                ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
            } else if ("0".equalsIgnoreCase(businessObject.getLocationAvailability()) && "1".equalsIgnoreCase(businessObject.getDeviceAvailability())) {
                ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
            } else if (view.getId() == R.id.f51grid.item.image.favorite) {
                if (this.mAppState.getCurrentUser().getLoginStatus()) {
                    StringBuilder stringBuilder;
                    if (businessObject instanceof Radio) {
                        Radio radio = (Radio) businessObject;
                        String favorite_count = radio.getFavorite_count();
                        if (!TextUtils.isEmpty(favorite_count)) {
                            if (businessObject.isFavorite().booleanValue()) {
                                radio.setFavoriteCount(String.valueOf(Integer.parseInt(favorite_count) - 1));
                            } else {
                                radio.setFavoriteCount(String.valueOf(Integer.parseInt(favorite_count) + 1));
                            }
                        }
                        if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Position ");
                            stringBuilder.append(this.mPosition);
                            stringBuilder.append(" - GaanaRadio - ");
                            stringBuilder.append(businessObject.getBusinessObjId());
                            ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios favourite click ", stringBuilder.toString());
                        }
                    } else if ((businessObject instanceof Artist) && ((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Position ");
                        stringBuilder.append(this.mPosition);
                        stringBuilder.append(" - Artist - ");
                        stringBuilder.append(businessObject.getBusinessObjId());
                        ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios favourite click ", stringBuilder.toString());
                    }
                }
                af a = af.a(this.mContext, this.mFragment);
                if (((GaanaActivity) this.mContext).getCurrentFragment() instanceof RadioDetailsMaterialListing) {
                    a.a("Similar Radios");
                    a.b(businessObject.getBusinessObjId());
                }
                a.a((int) R.id.favoriteMenu, businessObject, (a) this);
            } else if (view.getId() == R.id.f50grid.item.image.download) {
                DownloadStatus e;
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Download", ((BaseActivity) this.mContext).currentScreen);
                boolean z = businessObject instanceof Track;
                DownloadStatus downloadStatus = null;
                if (z) {
                    e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
                } else if ((businessObject instanceof Playlist) || (businessObject instanceof Album)) {
                    e = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
                } else {
                    e = null;
                }
                if (e == null || e == DownloadStatus.PAUSED || e == DownloadStatus.PARTIALLY_DOWNLOADED || e == DownloadStatus.TRIED_BUT_FAILED) {
                    ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading));
                    startDownload(businessObject);
                } else if (e == DownloadStatus.DOWNLOADED) {
                    if (!ap.a().k() || Util.a(businessObject)) {
                        new CustomDialogView(this.mContext, this.mContext.getResources().getString(R.string.toast_delete_downloaded_album), new OnButtonClickListener() {
                            public void onNegativeButtonClick() {
                            }

                            public void onPositiveButtonClick() {
                                DownloadStatus e;
                                RecommendedItemView.this.deleteDownload(businessObject);
                                if (businessObject instanceof Track) {
                                    e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
                                } else if ((businessObject instanceof Playlist) || (businessObject instanceof Album)) {
                                    e = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
                                } else {
                                    e = null;
                                }
                                RecommendedItemView.this.updateDownloadImage((ImageView) view, e);
                            }
                        }).show();
                    } else {
                        Util.a(this.mContext, null);
                        u.a().a("Expired Download", "Click", "Album");
                    }
                } else if (e == DownloadStatus.QUEUED) {
                    DownloadManager.c().r(Integer.parseInt(businessObject.getBusinessObjId()));
                    if (z) {
                        downloadStatus = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
                    } else if ((businessObject instanceof Playlist) || (businessObject instanceof Album)) {
                        downloadStatus = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
                    }
                    updateDownloadImage((ImageView) view, downloadStatus);
                    updateOfflineTracksStatus();
                } else if (e == DownloadStatus.DOWNLOADING) {
                    new CustomDialogView(this.mContext, this.mContext.getResources().getString(R.string.toast_stop_download), new OnButtonClickListener() {
                        public void onNegativeButtonClick() {
                        }

                        public void onPositiveButtonClick() {
                            DownloadStatus e;
                            view.setVisibility(0);
                            DownloadManager.c().r(Integer.parseInt(businessObject.getBusinessObjId()));
                            if (businessObject instanceof Track) {
                                e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
                            } else if ((businessObject instanceof Playlist) || (businessObject instanceof Album)) {
                                e = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
                            } else {
                                e = null;
                            }
                            RecommendedItemView.this.updateDownloadImage((ImageView) view, e);
                            RecommendedItemView.this.updateOfflineTracksStatus();
                        }
                    }).show();
                }
            }
        }
    }

    private boolean isItemAvailableForOffline(BusinessObject businessObject, View view) {
        if (view.getId() == R.id.f51grid.item.image.favorite) {
            return true;
        }
        if (businessObject.getBusinessObjType() == BusinessObjectType.Radios || businessObject.getBusinessObjType() == BusinessObjectType.Artists) {
            return false;
        }
        if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks) {
            return DownloadManager.c().l(Integer.parseInt(businessObject.getBusinessObjId())).booleanValue();
        }
        if (businessObject.getBusinessObjType() == BusinessObjectType.Albums || businessObject.getBusinessObjType() == BusinessObjectType.Playlists) {
            return DownloadManager.c().b(businessObject).booleanValue();
        }
        return false;
    }

    public void onClick(View view) {
        BusinessObject businessObject = (BusinessObject) view.getTag();
        if (businessObject != null) {
            Constants.i = false;
            Constants.j = "";
            super.onClick(view);
            if (businessObject instanceof Item) {
                Item item = (Item) businessObject;
                String entityType = item.getEntityType();
                if (entityType == null) {
                    businessObject = (DiscoverTag) populateDiscoverTagClicked(item);
                } else if (entityType.equals(com.constants.c.c.a)) {
                    businessObject = (Playlist) populatePlaylistClicked(item);
                } else if (entityType.equals(com.constants.c.c.b)) {
                    businessObject = (Album) populateAlbumClicked(item);
                } else if (entityType.equals(com.constants.c.d.c) || entityType.equals(com.constants.c.d.d)) {
                    businessObject = (Radio) populateRadioClicked(item);
                    if (this.mFragment != null && (this.mFragment instanceof RevampedDetailListing)) {
                        ((RevampedDetailListing) this.mFragment).a("Similar Radio", false);
                    }
                } else if (entityType.equals(com.constants.c.c.c)) {
                    businessObject = (Track) populateTrackClicked(item);
                } else if (entityType.equals(com.constants.c.c.d)) {
                    businessObject = (Artist) populateArtistClicked(item);
                } else if (entityType.equals(com.constants.c.c.f)) {
                    businessObject = (YouTubeVideo) populateVideoClicked(item);
                } else if (entityType.equals(com.constants.c.c.g)) {
                    businessObject = (DiscoverTag) populateDiscoverTagClicked(item);
                } else if (entityType.equals(com.constants.c.c.i)) {
                    if ("1".equalsIgnoreCase(item.getLocationAvailability()) && "0".equalsIgnoreCase(item.getDeviceAvailability())) {
                        ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
                        return;
                    } else if ("0".equalsIgnoreCase(item.getLocationAvailability()) && "1".equalsIgnoreCase(item.getDeviceAvailability())) {
                        ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
                        return;
                    } else {
                        handleOccasionEntity(item);
                    }
                } else if (entityType.equalsIgnoreCase(com.constants.c.c.j)) {
                    Util.a(item, this.mContext, PLAYOUT_SECTION_TYPE.OTHERS.name());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Position ");
                    stringBuilder.append(this.mPosition);
                    stringBuilder.append(" - Album - ");
                    stringBuilder.append(item.getBusinessObjId());
                    ((BaseActivity) this.mContext).sendGAEvent("Browse", "See All VPL  click ", stringBuilder.toString());
                }
            } else if (businessObject instanceof DiscoverTag) {
                ((DiscoverTag) businessObject).getTagEntityType();
            }
            if (businessObject != null) {
                String string;
                boolean isItemAvailableForOffline = isItemAvailableForOffline(businessObject, view);
                if (this.mAppState.isAppInOfflineMode() && !isItemAvailableForOffline) {
                    string = businessObject instanceof Album ? this.mContext.getString(R.string.this_album) : businessObject instanceof Track ? this.mContext.getString(R.string.this_track) : businessObject instanceof Playlist ? this.mContext.getString(R.string.this_playlist) : businessObject instanceof Radio ? this.mContext.getString(R.string.this_radio) : businessObject.getBusinessObjType() == BusinessObjectType.Artists ? this.mContext.getString(R.string.this_artist) : businessObject instanceof YouTubeVideo ? this.mContext.getString(R.string.this_video) : null;
                    if (string != null) {
                        ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(string);
                        return;
                    }
                } else if (!(Util.j(this.mContext) || isItemAvailableForOffline)) {
                    ap.a().f(this.mContext);
                    return;
                }
                if ("1".equalsIgnoreCase(businessObject.getLocationAvailability()) && "0".equalsIgnoreCase(businessObject.getDeviceAvailability())) {
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
                } else if ("0".equalsIgnoreCase(businessObject.getLocationAvailability()) && "1".equalsIgnoreCase(businessObject.getDeviceAvailability())) {
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
                } else {
                    BaseActivity baseActivity;
                    String str;
                    StringBuilder stringBuilder2;
                    StringBuilder stringBuilder3;
                    if ((businessObject instanceof DiscoverTag) || view.getId() == R.id.discoverTagImg) {
                        if (this.mAppState.isAppInOfflineMode()) {
                            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_item));
                        } else if (Util.j(this.mContext)) {
                            baseActivity = (BaseActivity) this.mContext;
                            str = ((BaseActivity) this.mContext).currentScreen;
                            stringBuilder2 = new StringBuilder();
                            DiscoverTag discoverTag = (DiscoverTag) businessObject;
                            stringBuilder2.append(discoverTag.getEnglishName());
                            stringBuilder2.append(" Detail ");
                            string = stringBuilder2.toString();
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(((BaseActivity) this.mContext).currentScreen);
                            stringBuilder3.append(" - Discover - ");
                            stringBuilder3.append(discoverTag.getEnglishName());
                            baseActivity.sendGAEvent(str, string, stringBuilder3.toString());
                            if (!(discoverTag.getRawName() == null || TextUtils.isEmpty(discoverTag.getRawName()))) {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("col:discover:");
                                stringBuilder2.append(discoverTag.getEnglishName());
                                q.a().a("int", stringBuilder2.toString());
                            }
                            String businessObjId = discoverTag.getBusinessObjId();
                            discoverTag.getName();
                            Bundle bundle = new Bundle();
                            bundle.putString("<category_id>", businessObjId);
                            bundle.putString("EXTRA_ACTIONBAR_TITLE", discoverTag.getRawName());
                            BaseGaanaFragment discoverDetailFragment = new DiscoverDetailFragment();
                            discoverDetailFragment.setArguments(bundle);
                            ((GaanaActivity) this.mContext).displayFragment(discoverDetailFragment);
                        } else {
                            ap.a().f(this.mContext);
                        }
                    } else if (view.getId() == R.id.f50grid.item.image.download || view.getId() == R.id.f51grid.item.image.favorite) {
                        executeRequest(view);
                    } else if (businessObject instanceof Playlist) {
                        businessObject = (Playlist) businessObject;
                        if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().b(businessObject).booleanValue()) {
                            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_playlist));
                        } else if (!Util.j(this.mContext) && !DownloadManager.c().b(businessObject).booleanValue()) {
                            ap.a().f(this.mContext);
                        } else if ((this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) && !ap.a().a(businessObject, null)) {
                            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.toast_subscription_expired));
                        } else {
                            if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                                stringBuilder3 = new StringBuilder();
                                stringBuilder3.append("Position ");
                                stringBuilder3.append(this.mPosition);
                                stringBuilder3.append(" - Playlist - ");
                                stringBuilder3.append(businessObject.getBusinessObjId());
                                ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder3.toString());
                            } else {
                                baseActivity = (BaseActivity) this.mContext;
                                str = ((BaseActivity) this.mContext).currentScreen;
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Playlist Detail  : ");
                                stringBuilder2.append(businessObject.getEnglishName());
                                baseActivity.sendGAEvent(str, stringBuilder2.toString(), ((BaseActivity) this.mContext).currentScreen);
                            }
                            this.mListingComponents = Constants.e();
                            this.mAppState.setListingComponents(this.mListingComponents);
                            if (businessObject.isGaanaSpecial()) {
                                populateSpecialGaanaListing(businessObject);
                            } else {
                                populatePlaylistListing(businessObject);
                            }
                        }
                    } else if (businessObject instanceof Album) {
                        businessObject = (Album) businessObject;
                        if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().b(businessObject).booleanValue()) {
                            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_playlist));
                        } else if (!Util.j(this.mContext) && !DownloadManager.c().b(businessObject).booleanValue()) {
                            ap.a().f(this.mContext);
                        } else if ((this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) && !ap.a().a(businessObject, null)) {
                            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.toast_subscription_expired));
                        } else {
                            if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                                stringBuilder3 = new StringBuilder();
                                stringBuilder3.append("Position ");
                                stringBuilder3.append(this.mPosition);
                                stringBuilder3.append(" - Album - ");
                                stringBuilder3.append(businessObject.getBusinessObjId());
                                ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder3.toString());
                            } else {
                                baseActivity = (BaseActivity) this.mContext;
                                str = ((BaseActivity) this.mContext).currentScreen;
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Album Detail  : ");
                                stringBuilder2.append(businessObject.getEnglishName());
                                baseActivity.sendGAEvent(str, stringBuilder2.toString(), ((BaseActivity) this.mContext).currentScreen);
                            }
                            this.mListingComponents = Constants.b();
                            this.mAppState.setListingComponents(this.mListingComponents);
                            populateAlbumListing(businessObject);
                        }
                    } else if (businessObject instanceof Radio) {
                        Radio radio = (Radio) businessObject;
                        if (radio.getType().equals(com.constants.c.d.c)) {
                            if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                                stringBuilder3 = new StringBuilder();
                                stringBuilder3.append("Position ");
                                stringBuilder3.append(this.mPosition);
                                stringBuilder3.append(" - RadioMirchi - ");
                                stringBuilder3.append(radio.getBusinessObjId());
                                ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder3.toString());
                            } else {
                                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Radio Detail ", ((BaseActivity) this.mContext).currentScreen);
                            }
                        } else if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append("Position ");
                            stringBuilder3.append(this.mPosition);
                            stringBuilder3.append(" - GaanaRadio - ");
                            stringBuilder3.append(radio.getBusinessObjId());
                            ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder3.toString());
                        } else {
                            ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Radio Detail ", ((BaseActivity) this.mContext).currentScreen);
                        }
                        playRadio(radio);
                    } else if (businessObject instanceof Track) {
                        Track track = (Track) businessObject;
                        if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                            StringBuilder stringBuilder4 = new StringBuilder();
                            stringBuilder4.append("Position ");
                            stringBuilder4.append(this.mPosition);
                            stringBuilder4.append(" - Track - ");
                            stringBuilder4.append(track.getBusinessObjId());
                            ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder4.toString());
                        } else {
                            baseActivity = (BaseActivity) this.mContext;
                            string = ((BaseActivity) this.mContext).currentScreen;
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append("Track Detail  : ");
                            stringBuilder3.append(track.getEnglishName());
                            baseActivity.sendGAEvent(string, stringBuilder3.toString(), ((BaseActivity) this.mContext).currentScreen);
                        }
                        this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.MY_ACTVITY.name());
                        PlayerTrack playerTrack = new PlayerTrack(track, track.getBusinessObjId(), SOURCE_TYPE.OTHER.ordinal(), track.getEnglishName());
                        playerTrack.f(this.mFragment.getPageName());
                        ArrayList arrayList = new ArrayList();
                        ArrayList b = aq.a().b();
                        if (b != null && b.size() > 0) {
                            Iterator it = b.iterator();
                            while (it.hasNext()) {
                                arrayList.add(populateTrackClicked((Item) ((BusinessObject) it.next())));
                            }
                        }
                        if (!(arrayList == null || checkForContains(arrayList, track))) {
                            arrayList.add(track);
                        }
                        ArrayList a = com.logging.d.a().a(this.mFragment, arrayList);
                        if (a != null) {
                            PlayerManager.a(this.mContext).b(a, playerTrack, 0);
                        }
                        PlayerManager.a(this.mContext).g(true);
                        play(playerTrack);
                        PlayerManager.a(this.mContext).g(false);
                    } else if (businessObject instanceof Artist) {
                        Artist artist = (Artist) businessObject;
                        this.mListingComponents = Constants.a("", businessObject.isLocalMedia());
                        this.mAppState.setListingComponents(this.mListingComponents);
                        ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Artist Detail", ((BaseActivity) this.mContext).currentScreen);
                        populateArtistListing(artist);
                    } else if (businessObject instanceof YouTubeVideo) {
                        YouTubeVideo youTubeVideo = (YouTubeVideo) businessObject;
                        launchYouTubePlayer(youTubeVideo.c(), youTubeVideo.a(), businessObject, 0);
                        if (this.onClickItemUpdateListener != null) {
                            this.onClickItemUpdateListener.a(businessObject, false);
                        }
                    }
                }
            }
        }
    }

    private void playRadio(final Radio radio) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_radio));
        } else if (!Util.j(this.mContext)) {
            ap.a().f(this.mContext);
        } else if (Constants.cY) {
            JukeSessionManager.getErrorDialog(this.mContext, 0, new OnButtonClickListener() {
                public void onNegativeButtonClick() {
                }

                public void onPositiveButtonClick() {
                    JukeSessionManager.getInstance().stopJukeSession(new s() {
                        public void onErrorResponse(BusinessObject businessObject) {
                        }

                        public void onRetreivalComplete(BusinessObject businessObject) {
                            if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                                RecommendedItemView.this.playRadio(radio);
                            }
                        }
                    });
                }
            });
        } else {
            String type = radio.getType();
            String businessObjId = radio.getBusinessObjId();
            String name = radio.getName();
            BusinessObject radio2 = new Radio();
            if (type.equals(com.constants.c.d.c) || type.equals(com.constants.c.d.d)) {
                radio2.setName(name);
                radio2.setLanguage(radio.getLanguage());
                name = null;
                if (radio.getArtwork() != null) {
                    name = radio.getArtwork().replace("80x80", "175x175");
                }
                radio2.setArtwork(name);
                radio2.setSeokey(radio.getSeokey());
                radio2.setUrlManager(radio.getUrlManager());
                radio2.setFavoriteCount(radio.getFavorite_count());
                if (radio.getAdCompaignPosition() > 0) {
                    radio2.setAdCompaignPosition(radio.getAdCompaignPosition());
                }
                radio2.setBusinessObjType(BusinessObjectType.Radios);
                radio2.setBusinessObjId(businessObjId);
                radio2.setType(type);
            }
            if (radio.getType().equals(com.constants.c.d.c)) {
                ad.a(this.mContext).a((BusinessObject) radio);
            } else {
                ad.a(this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", radio.getBusinessObjId()).replace("<radio_type>", radio.getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), (BusinessObject) radio);
            }
            this.mListingComponents = Constants.a((Radio) radio2);
            this.mListingComponents.a(radio2);
            populateRadioListing(radio2);
        }
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup, boolean z, Boolean bool) {
        ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
        if (arrListBusinessObj.size() > 0) {
            this.mLayoutHomeResourceId = getLayoutId((BusinessObject) arrListBusinessObj.get(0), true);
        }
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutHomeResourceId, view, viewGroup);
        }
        if (z) {
            view.findViewById(R.id.f55header.text).setVisibility(0);
        } else {
            view.findViewById(R.id.f55header.text).setVisibility(8);
        }
        view.findViewById(R.id.f50grid.item.image.download).setOnClickListener(this);
        view.findViewById(R.id.f51grid.item.image.favorite).setOnClickListener(this);
        if (bool.booleanValue()) {
            return getDefaultPoplatedView(view, businessObject);
        }
        return initView(view, businessObject, viewGroup);
    }

    private int getLayoutId(BusinessObject businessObject, boolean z) {
        return (!(businessObject instanceof Radio) || ((Radio) businessObject).getFavorite_count() == null) ? (!(businessObject instanceof DiscoverTag) || ((DiscoverTag) businessObject).getFavorite_count() == null) ? z ? R.layout.grid_twoitem_view : R.layout.view_grid_item : z ? R.layout.grid_twoitem_view_favorite : R.layout.view_grid_item_favoritecount : z ? R.layout.grid_twoitem_view_favorite : R.layout.view_grid_item_favoritecount;
    }

    public View getDefaultPoplatedView(View view, BusinessObject businessObject) {
        TypedArray obtainStyledAttributes;
        BusinessObject businessObject2;
        View view2 = view;
        View findViewById = view2.findViewById(R.id.f58ll.grid.firstitem);
        if (findViewById != null) {
            findViewById.setVisibility(0);
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
            ((SquareImageView) findViewById.findViewById(R.id.imgProductIcon)).setImageDrawable(obtainStyledAttributes.getDrawable(0));
            obtainStyledAttributes.recycle();
            businessObject2 = (BusinessObject) businessObject.getArrListBusinessObj().get(0);
            ((TextView) findViewById.findViewById(R.id.f54grid.item.tv.name)).setText(businessObject2.getName());
            if (findViewById.findViewById(R.id.f51grid.item.image.favorite).getVisibility() == 0) {
                new int[1][0] = R.attr.moreoptions_favorite;
                ((ImageView) findViewById.findViewById(R.id.f51grid.item.image.favorite)).setImageDrawable(ContextCompat.getDrawable(getContext(), this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables).getResourceId(49, -1)));
            } else {
                findViewById.findViewById(R.id.f51grid.item.image.favorite).setVisibility(4);
            }
            if (findViewById.findViewById(R.id.f50grid.item.image.download).getVisibility() == 0) {
                new int[1][0] = R.attr.download_button_paused;
                ((ImageView) findViewById.findViewById(R.id.f50grid.item.image.download)).setImageDrawable(ContextCompat.getDrawable(getContext(), this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables).getResourceId(13, -1)));
            } else {
                findViewById.findViewById(R.id.f50grid.item.image.download).setVisibility(4);
            }
            findViewById.findViewById(R.id.download_ProgressBar).setVisibility(4);
            if (findViewById.findViewById(R.id.f52grid.item.text.favoritecount) != null) {
                updateFavoriteCount((TextView) findViewById.findViewById(R.id.f52grid.item.text.favoritecount), businessObject2, findViewById.findViewById(R.id.f51grid.item.image.favorite).getVisibility());
            }
        }
        findViewById = view2.findViewById(R.id.f60ll.grid.seconditem);
        if (findViewById != null) {
            if (businessObject.getArrListBusinessObj().size() == 1) {
                findViewById.setVisibility(8);
            } else {
                findViewById.setVisibility(0);
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
                ((SquareImageView) findViewById.findViewById(R.id.imgProductIcon)).setImageDrawable(obtainStyledAttributes.getDrawable(0));
                obtainStyledAttributes.recycle();
                businessObject2 = (BusinessObject) businessObject.getArrListBusinessObj().get(1);
                ((TextView) findViewById.findViewById(R.id.f54grid.item.tv.name)).setText(businessObject2.getName());
                if (findViewById.findViewById(R.id.f51grid.item.image.favorite).getVisibility() == 0) {
                    new int[1][0] = R.attr.moreoptions_favorite;
                    ((ImageView) findViewById.findViewById(R.id.f51grid.item.image.favorite)).setImageDrawable(ContextCompat.getDrawable(getContext(), this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables).getResourceId(49, -1)));
                } else {
                    findViewById.findViewById(R.id.f51grid.item.image.favorite).setVisibility(4);
                }
                if (findViewById.findViewById(R.id.f50grid.item.image.download).getVisibility() == 0) {
                    new int[1][0] = R.attr.download_button_paused;
                    ((ImageView) findViewById.findViewById(R.id.f50grid.item.image.download)).setImageDrawable(ContextCompat.getDrawable(getContext(), this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables).getResourceId(13, -1)));
                } else {
                    findViewById.findViewById(R.id.f50grid.item.image.download).setVisibility(4);
                }
                findViewById.findViewById(R.id.download_ProgressBar).setVisibility(4);
                if (findViewById.findViewById(R.id.f52grid.item.text.favoritecount) != null) {
                    updateFavoriteCount((TextView) findViewById.findViewById(R.id.f52grid.item.text.favoritecount), businessObject2, findViewById.findViewById(R.id.f51grid.item.image.favorite).getVisibility());
                }
            }
        }
        return view2;
    }

    public View getDefaultPoplatedView(TwoRecommendedItemHolder twoRecommendedItemHolder, BusinessObject businessObject) {
        Drawable drawable;
        this.mView.findViewById(R.id.f58ll.grid.firstitem);
        RecommendedItemHolder recommendedItemHolder = twoRecommendedItemHolder.firstHolder;
        if (recommendedItemHolder != null) {
            recommendedItemHolder.itemView.setVisibility(0);
            if (businessObject instanceof Item) {
                if (((Item) businessObject).getEntityType().equals(com.constants.c.c.c)) {
                    recommendedItemHolder.play_icon.setVisibility(0);
                } else {
                    recommendedItemHolder.play_icon.setVisibility(4);
                }
            }
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
            drawable = obtainStyledAttributes.getDrawable(0);
            recommendedItemHolder.imageViewThumb.setImageDrawable(drawable);
            recommendedItemHolder.imageViewThumbRect.setImageDrawable(drawable);
            obtainStyledAttributes.recycle();
            recommendedItemHolder.tvName.setText(((BusinessObject) businessObject.getArrListBusinessObj().get(0)).getName());
            if (recommendedItemHolder.downloadImage.getVisibility() == 0) {
                new int[1][0] = R.attr.download_button_paused;
                recommendedItemHolder.downloadImage.setImageDrawable(ContextCompat.getDrawable(getContext(), this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables).getResourceId(13, -1)));
            } else {
                recommendedItemHolder.downloadImage.setVisibility(4);
            }
            recommendedItemHolder.downloadProgressBar.setVisibility(4);
        }
        recommendedItemHolder = twoRecommendedItemHolder.secondHolder;
        View view = recommendedItemHolder.itemView;
        if (recommendedItemHolder != null) {
            if (businessObject.getArrListBusinessObj().size() == 1) {
                recommendedItemHolder.itemView.setVisibility(8);
            } else {
                recommendedItemHolder.itemView.setVisibility(0);
                if (businessObject instanceof Item) {
                    if (((Item) businessObject).getEntityType().equals(com.constants.c.c.c)) {
                        recommendedItemHolder.play_icon.setVisibility(0);
                    } else {
                        recommendedItemHolder.play_icon.setVisibility(4);
                    }
                }
                TypedArray obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
                drawable = obtainStyledAttributes2.getDrawable(0);
                recommendedItemHolder.imageViewThumb.setImageDrawable(drawable);
                recommendedItemHolder.imageViewThumbRect.setImageDrawable(drawable);
                obtainStyledAttributes2.recycle();
                recommendedItemHolder.tvName.setText(((BusinessObject) businessObject.getArrListBusinessObj().get(1)).getName());
                if (view.findViewById(R.id.f51grid.item.image.favorite).getVisibility() == 0) {
                    new int[1][0] = R.attr.moreoptions_favorite;
                    recommendedItemHolder.favImage.setImageDrawable(ContextCompat.getDrawable(getContext(), this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables).getResourceId(49, -1)));
                } else {
                    recommendedItemHolder.favImage.setVisibility(4);
                }
                if (recommendedItemHolder.downloadImage.getVisibility() == 0) {
                    new int[1][0] = R.attr.download_button_paused;
                    ((ImageView) view.findViewById(R.id.f50grid.item.image.download)).setImageDrawable(ContextCompat.getDrawable(getContext(), this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables).getResourceId(13, -1)));
                } else {
                    recommendedItemHolder.downloadImage.setVisibility(4);
                }
                recommendedItemHolder.downloadProgressBar.setVisibility(4);
            }
        }
        return twoRecommendedItemHolder.itemView;
    }

    private void updateFavoriteCount(TextView textView, BusinessObject businessObject, int i) {
        String favorite_count;
        if (businessObject instanceof Radio) {
            favorite_count = ((Radio) businessObject).getFavorite_count();
        } else if (businessObject instanceof DiscoverTag) {
            favorite_count = ((DiscoverTag) businessObject).getFavorite_count();
        } else {
            if (businessObject instanceof Item) {
                Item item = (Item) businessObject;
                if (item.getEntityType().equals(com.constants.c.d.c) || item.getEntityType().equals(com.constants.c.d.d) || item.getEntityType() == null) {
                    favorite_count = Long.toString(item.getFavoriteCount());
                }
            }
            favorite_count = null;
        }
        if (favorite_count == null) {
            textView.setVisibility(8);
            return;
        }
        if (!favorite_count.equalsIgnoreCase("0")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getFormattedFavoriteCount(favorite_count));
            stringBuilder.append(getContext().getString(R.string.favorites));
            textView.setText(stringBuilder.toString());
        } else if (i == 0) {
            textView.setText(R.string.favorite_now);
        } else {
            textView.setText(R.string.download_now);
        }
        textView.setVisibility(0);
    }

    public View initView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutResourceId, view, viewGroup);
        }
        view.setClickable(false);
        if (businessObject != null) {
            ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
            int size = arrListBusinessObj.size();
            View findViewById = view.findViewById(R.id.f58ll.grid.firstitem);
            findViewById.setVisibility(0);
            getPoplatedViewGrid(findViewById, (BusinessObject) arrListBusinessObj.get(0), viewGroup);
            if (this.mFragment instanceof DynamicHomeFragment) {
                ((TextView) view.findViewById(R.id.f55header.text)).setText(R.string.more_playlist_and_radio);
            } else {
                view.findViewById(R.id.f55header.text).setVisibility(8);
            }
            if (size == 2) {
                View findViewById2 = view.findViewById(R.id.f60ll.grid.seconditem);
                findViewById2.setVisibility(0);
                getPoplatedViewGrid(findViewById2, (BusinessObject) arrListBusinessObj.get(1), viewGroup);
            } else {
                view.findViewById(R.id.f60ll.grid.seconditem).setVisibility(8);
            }
        }
        return view;
    }

    public View initView(TwoRecommendedItemHolder twoRecommendedItemHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        twoRecommendedItemHolder.itemView.setClickable(false);
        if (businessObject != null) {
            ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
            int size = arrListBusinessObj.size();
            RecommendedItemHolder recommendedItemHolder = twoRecommendedItemHolder.firstHolder;
            recommendedItemHolder.itemView.setVisibility(0);
            getPoplatedViewGrid(recommendedItemHolder, (BusinessObject) arrListBusinessObj.get(0), viewGroup);
            if (this.mFragment instanceof DynamicHomeFragment) {
                twoRecommendedItemHolder.headerText.setText(this.mContext.getResources().getString(R.string.home_grid_header_text));
            } else if (this.mFragment instanceof RadioActivityFragment) {
                twoRecommendedItemHolder.headerText.setText(R.string.gaana_radios);
            } else {
                twoRecommendedItemHolder.headerText.setVisibility(8);
            }
            recommendedItemHolder = twoRecommendedItemHolder.secondHolder;
            if (size == 2) {
                recommendedItemHolder.itemView.setVisibility(0);
                getPoplatedViewGrid(recommendedItemHolder, (BusinessObject) arrListBusinessObj.get(1), viewGroup);
            } else {
                recommendedItemHolder.itemView.setVisibility(8);
            }
        }
        return twoRecommendedItemHolder.itemView;
    }

    public void getPoplatedViewGrid(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        TextView textView = (TextView) view.findViewById(R.id.f54grid.item.tv.name);
        TextView textView2 = (TextView) view.findViewById(R.id.f52grid.item.text.favoritecount);
        if (view.findViewById(R.id.f50grid.item.image.download) != null) {
            view.findViewById(R.id.f50grid.item.image.download).setOnClickListener(this);
        }
        if (view.findViewById(R.id.f51grid.item.image.favorite) != null) {
            view.findViewById(R.id.f51grid.item.image.favorite).setOnClickListener(this);
        }
        boolean z = businessObject instanceof DiscoverTag;
        if (z) {
            Item item = (Item) businessObject;
            if (crossFadeImageView != null) {
                String str = null;
                if (item.getArtwork() != null) {
                    str = item.getArtwork().replace("80x80", "175x175");
                }
                crossFadeImageView.bindImage(str, crossFadeImageView.getScaleType());
            }
            if (textView != null) {
                textView.setText(item.getName());
            }
            if (textView2 != null) {
                updateFavoriteCount(textView2, item, view.findViewById(R.id.f51grid.item.image.favorite).getVisibility());
            }
        }
        if (!(z && ((DiscoverTag) businessObject).getTagEntityType() == null)) {
            initFavoriteDownload(view, businessObject);
        }
        view.setOnClickListener(this);
        super.getPoplatedView(view, businessObject, viewGroup);
    }

    public void getPoplatedViewGrid(RecommendedItemHolder recommendedItemHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        View view = recommendedItemHolder.itemView;
        CrossFadeImageView crossFadeImageView = recommendedItemHolder.imageViewThumb;
        TextView textView = recommendedItemHolder.tvName;
        view.setTag(businessObject);
        crossFadeImageView.setTag(businessObject);
        boolean z = businessObject instanceof Item;
        String artwork;
        if (z) {
            Item item = (Item) businessObject;
            if (crossFadeImageView != null) {
                String artwork2 = item.getArtwork();
                if (artwork2 != null) {
                    artwork2 = artwork2.replace("80x80", "175x175");
                }
                crossFadeImageView.bindImage(artwork2, crossFadeImageView.getScaleType());
            }
            if (textView != null) {
                textView.setText(item.getName());
            }
            if (item.getEntityType().equals(com.constants.c.c.c)) {
                recommendedItemHolder.play_icon.setVisibility(0);
            } else if (item.getEntityType().equals(com.constants.c.c.f)) {
                recommendedItemHolder.play_icon.setVisibility(0);
                recommendedItemHolder.favImage.setVisibility(4);
            } else {
                recommendedItemHolder.play_icon.setVisibility(4);
            }
        } else if (businessObject.getBusinessObjType() == BusinessObjectType.Artists) {
            Artist artist = (Artist) businessObject;
            artwork = artist.getArtwork();
            if (artwork != null) {
                artwork = artwork.replace("80x80", "175x175");
            }
            if (crossFadeImageView != null) {
                crossFadeImageView.bindImage(artwork, crossFadeImageView.getScaleType());
            }
            if (textView != null) {
                textView.setText(artist.getName());
            }
        } else if (businessObject instanceof Track) {
            Track track = (Track) businessObject;
            artwork = track.getArtwork();
            if (artwork != null) {
                artwork = artwork.replace("80x80", "175x175");
            }
            if (crossFadeImageView != null) {
                crossFadeImageView.bindImage(artwork, crossFadeImageView.getScaleType());
            }
            if (textView != null) {
                textView.setText(track.getName());
            }
        }
        if (!(((businessObject instanceof DiscoverTag) && ((DiscoverTag) businessObject).getTagEntityType() == null) || (z && ((Item) businessObject).getEntityType().equals(com.constants.c.c.f)))) {
            initFavoriteDownload(view, businessObject);
        }
        view.setOnClickListener(this);
        super.getPoplatedView(view, businessObject, viewGroup);
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01af  */
    /* JADX WARNING: Missing block: B:33:0x00fb, code skipped:
            if (r20.equals(com.dynamicview.DynamicViewManager.DynamicViewType.grid_rect.name()) != false) goto L_0x0111;
     */
    public android.view.View getViewAllGriditemView(android.support.v7.widget.RecyclerView.ViewHolder r16, com.gaana.models.BusinessObject r17, android.view.ViewGroup r18, int r19, java.lang.String r20) {
        /*
        r15 = this;
        r0 = r15;
        r1 = r17;
        r2 = r16;
        r2 = (com.gaana.view.item.BaseItemView.RecommendedItemHolder) r2;
        r3 = r2.itemView;
        r0.mView = r3;
        r3 = r2.imageViewThumb;
        r4 = r2.imageViewThumbRect;
        r5 = r2.tvName;
        r6 = r2.parentLayout;
        r7 = r0.mView;
        r8 = 2131297275; // 0x7f0903fb float:1.821249E38 double:1.0530007647E-314;
        r7 = r7.findViewById(r8);
        r7 = (android.widget.TextView) r7;
        r8 = r2.downloadPulse;
        r8 = r2.tvSectionTitle;
        r6.setTag(r1);
        r6.setOnClickListener(r0);
        r3.setTag(r1);
        r4.setTag(r1);
        r6 = r0.mView;
        r0.initFavoriteDownload(r6, r1);
        r6 = r1 instanceof com.gaana.models.Item;
        if (r6 == 0) goto L_0x01be;
    L_0x0037:
        r6 = r1;
        r6 = (com.gaana.models.Item) r6;
        r9 = r6.getArtworkMedium();
        if (r9 == 0) goto L_0x0046;
    L_0x0040:
        r10 = android.text.TextUtils.isEmpty(r9);
        if (r10 == 0) goto L_0x004a;
    L_0x0046:
        r9 = r6.getArtwork();
    L_0x004a:
        r10 = r0.mContext;
        r9 = com.utilities.Util.h(r10, r9);
        if (r9 == 0) goto L_0x0078;
    L_0x0052:
        r10 = android.text.TextUtils.isEmpty(r9);
        if (r10 != 0) goto L_0x0078;
    L_0x0058:
        r10 = r6.getEntityType();
        r11 = com.constants.c.d.e;
        if (r10 != r11) goto L_0x0070;
    L_0x0060:
        r10 = r6.getEntityType();
        r11 = com.constants.c.d.d;
        if (r10 != r11) goto L_0x0070;
    L_0x0068:
        r10 = r6.getEntityType();
        r11 = com.constants.c.d.c;
        if (r10 == r11) goto L_0x0078;
    L_0x0070:
        r10 = "80x80";
        r11 = "175x175";
        r9 = r9.replace(r10, r11);
    L_0x0078:
        r10 = r6.getName();
        r5.setText(r10);
        r10 = r2.txtHeaderName;
        r11 = 4;
        r10.setVisibility(r11);
        r10 = r6.getEntityType();
        r12 = com.constants.c.c.c;
        r10 = r10.equals(r12);
        r12 = 0;
        if (r10 == 0) goto L_0x0098;
    L_0x0092:
        r10 = r2.play_icon;
        r10.setVisibility(r12);
        goto L_0x00d4;
    L_0x0098:
        r10 = r6.getEntityType();
        r13 = com.constants.c.c.j;
        r10 = r10.equals(r13);
        if (r10 == 0) goto L_0x00b8;
    L_0x00a4:
        r10 = r2.txtHeaderName;
        r13 = r17.getName();
        r10.setText(r13);
        r10 = r2.txtHeaderName;
        r10.setVisibility(r11);
        r10 = r2.favImage;
        r10.setVisibility(r11);
        goto L_0x00d4;
    L_0x00b8:
        r10 = r6.getEntityType();
        r13 = com.constants.c.c.f;
        r10 = r10.equals(r13);
        if (r10 == 0) goto L_0x00cf;
    L_0x00c4:
        r10 = r2.play_icon;
        r10.setVisibility(r12);
        r10 = r2.favImage;
        r10.setVisibility(r11);
        goto L_0x00d4;
    L_0x00cf:
        r10 = r2.play_icon;
        r10.setVisibility(r11);
    L_0x00d4:
        r10 = r6.getEntityType();
        r11 = com.constants.c.c.g;
        r10 = r10.equals(r11);
        r11 = 1;
        r13 = 8;
        if (r10 != 0) goto L_0x0111;
    L_0x00e3:
        r10 = r6.getEntityType();
        r14 = com.constants.c.c.i;
        r10 = r10.equals(r14);
        if (r10 == 0) goto L_0x00fe;
    L_0x00ef:
        r10 = com.dynamicview.DynamicViewManager.DynamicViewType.grid_rect;
        r10 = r10.name();
        r14 = r20;
        r10 = r14.equals(r10);
        if (r10 == 0) goto L_0x00fe;
    L_0x00fd:
        goto L_0x0111;
    L_0x00fe:
        r3.setVisibility(r12);
        r4.setVisibility(r13);
        if (r8 == 0) goto L_0x0109;
    L_0x0106:
        r8.setVisibility(r13);
    L_0x0109:
        r2 = r3.getScaleType();
        r3.bindImage(r9, r2);
        goto L_0x0152;
    L_0x0111:
        r10 = r0.mContext;
        r10 = r10.getResources();
        r14 = 2131165677; // 0x7f0701ed float:1.7945578E38 double:1.0529357466E-314;
        r10 = r10.getDimension(r14);
        r5.setVisibility(r13);
        r3.setVisibility(r13);
        if (r8 == 0) goto L_0x0134;
    L_0x0126:
        r8.setVisibility(r12);
        r3 = r6.getName();
        r8.setText(r3);
        r3 = 0;
        r8.setTypeface(r3, r11);
    L_0x0134:
        r4.setVisibility(r12);
        r3 = r4.getScaleType();
        r4.bindImage(r9, r3);
        r3 = new android.widget.FrameLayout$LayoutParams;
        r8 = -1;
        r9 = (int) r10;
        r3.<init>(r8, r9);
        r4.setLayoutParams(r3);
        r3 = r2.downloadImage;
        r3.setVisibility(r13);
        r2 = r2.favImage;
        r2.setVisibility(r13);
    L_0x0152:
        r2 = r6.getEntityInfo();
        if (r2 == 0) goto L_0x01ad;
    L_0x0158:
        r3 = r12;
    L_0x0159:
        r4 = r2.size();
        if (r3 >= r4) goto L_0x01ad;
    L_0x015f:
        r4 = r2.get(r3);
        r4 = (com.gaana.models.EntityInfo) r4;
        r4 = r4.getKey();
        r8 = "parental_warning";
        r4 = r4.equals(r8);
        if (r4 == 0) goto L_0x01aa;
    L_0x0171:
        r4 = r2.get(r3);
        r4 = (com.gaana.models.EntityInfo) r4;
        r4 = r4.getValue();
        r4 = r4 instanceof java.lang.Double;
        if (r4 == 0) goto L_0x0199;
    L_0x017f:
        r2 = r2.get(r3);
        r2 = (com.gaana.models.EntityInfo) r2;
        r2 = r2.getValue();
        r2 = (java.lang.Double) r2;
        r2 = r2.doubleValue();
        r8 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r2 = java.lang.Double.compare(r2, r8);
        if (r2 != 0) goto L_0x01ad;
    L_0x0197:
        r12 = r11;
        goto L_0x01ad;
    L_0x0199:
        r2 = r2.get(r3);
        r2 = (com.gaana.models.EntityInfo) r2;
        r2 = r2.getValue();
        r3 = "1";
        r12 = r2.equals(r3);
        goto L_0x01ad;
    L_0x01aa:
        r3 = r3 + 1;
        goto L_0x0159;
    L_0x01ad:
        if (r12 == 0) goto L_0x01b6;
    L_0x01af:
        r2 = r6.getName();
        com.utilities.Util.a(r5, r2);
    L_0x01b6:
        r2 = r0.mContext;
        r3 = 2131886676; // 0x7f120254 float:1.9407938E38 double:1.0532919674E-314;
        r5.setTextAppearance(r2, r3);
    L_0x01be:
        if (r7 == 0) goto L_0x01d0;
    L_0x01c0:
        r2 = r0.mView;
        r3 = 2131297274; // 0x7f0903fa float:1.8212488E38 double:1.053000764E-314;
        r2 = r2.findViewById(r3);
        r2 = r2.getVisibility();
        r0.updateFavoriteCount(r7, r1, r2);
    L_0x01d0:
        r2 = r0.mView;
        r2.setOnClickListener(r0);
        r2 = r0.mView;
        r3 = r18;
        r1 = super.getPoplatedView(r2, r1, r3);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.item.RecommendedItemView.getViewAllGriditemView(android.support.v7.widget.RecyclerView$ViewHolder, com.gaana.models.BusinessObject, android.view.ViewGroup, int, java.lang.String):android.view.View");
    }

    private void setAnimationBlinker(PulsatorView pulsatorView, BusinessObject businessObject) {
        PlayerManager.a(this.mContext).i();
        String str = "";
        if (businessObject instanceof Item) {
            str = ((Item) businessObject).getEntityType();
        }
        DownloadStatus downloadStatus;
        if (((GaanaActivity) this.mContext).getPlayerStates() == PlayerStates.PLAYING && GaanaMusicService.s().isPlaying()) {
            if (DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId())) == null) {
                pulsatorView.setVisibility(0);
                pulsatorView.build();
                pulsatorView.start();
                ((GaanaActivity) this.mContext).getCurrentFragment();
                Item item = (Item) businessObject;
                if (str.equals(com.constants.c.d.a.toString())) {
                    u.a().a("Downloads: PlaylistGridView", "Download blinker appeared", "");
                } else if (str.equals(com.constants.c.d.b.toString())) {
                    u.a().a("Downloads: AlbumlGridView", "Download blinker appeared", "");
                }
            } else {
                downloadStatus = DownloadStatus.DOWNLOADING;
                pulsatorView.setVisibility(4);
                pulsatorView.stop();
            }
            return;
        }
        downloadStatus = DownloadStatus.DOWNLOADING;
        pulsatorView.setVisibility(4);
        pulsatorView.stop();
    }

    private void initFavoriteDownload(View view, BusinessObject businessObject) {
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.download_ProgressBar);
        if (progressBar != null) {
            progressBar.setVisibility(8);
            ImageView imageView = (ImageView) view.findViewById(R.id.f50grid.item.image.download);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.f51grid.item.image.favorite);
            if (imageView != null) {
                imageView.setOnClickListener(this);
            }
            if (imageView2 != null) {
                imageView2.setOnClickListener(this);
            }
            if (shouldShowFavorite(businessObject)) {
                imageView.setVisibility(8);
                imageView2.setTag(businessObject);
                imageView2.setOnClickListener(this);
                TypedArray obtainStyledAttributes;
                Drawable drawable;
                if (n.e(businessObject)) {
                    progressBar.setVisibility(0);
                    imageView2.setVisibility(4);
                } else if (businessObject.isFavorite().booleanValue()) {
                    imageView2.setVisibility(0);
                    if (businessObject.getBusinessObjType() != BusinessObjectType.Artists) {
                        new int[1][0] = R.attr.moreoptions_favorited;
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(51, -1));
                        obtainStyledAttributes.recycle();
                        imageView2.setImageDrawable(drawable);
                    } else {
                        new int[1][0] = R.attr.moreoptions_unfollow;
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(59, -1));
                        obtainStyledAttributes.recycle();
                        imageView2.setImageDrawable(drawable);
                    }
                } else {
                    imageView2.setVisibility(0);
                    if (businessObject.getBusinessObjType() != BusinessObjectType.Artists) {
                        new int[1][0] = R.attr.moreoptions_favorite;
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(49, -1));
                        obtainStyledAttributes.recycle();
                        imageView2.setImageDrawable(drawable);
                    } else {
                        new int[1][0] = R.attr.moreoptions_follow;
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(53, -1));
                        obtainStyledAttributes.recycle();
                        imageView2.setImageDrawable(drawable);
                    }
                }
            } else {
                String entityId;
                imageView.setVisibility(0);
                imageView2.setVisibility(8);
                imageView.setTag(businessObject);
                imageView.setOnClickListener(this);
                boolean z = businessObject instanceof Item;
                if (z) {
                    entityId = ((Item) businessObject).getEntityId();
                } else {
                    entityId = businessObject.getBusinessObjId();
                }
                TypedArray obtainStyledAttributes2;
                if (entityId != null) {
                    DownloadStatus e;
                    if ((businessObject instanceof Track) || (z && ((Item) businessObject).getEntityType().equals(com.constants.c.c.c))) {
                        e = DownloadManager.c().e(Integer.parseInt(entityId));
                    } else {
                        e = DownloadManager.c().h(Integer.parseInt(entityId));
                    }
                    Drawable drawable2;
                    if (e == DownloadStatus.DOWNLOADED) {
                        if (!ap.a().k() || Util.a(businessObject)) {
                            imageView.setImageResource(R.drawable.vector_download_completed);
                        } else {
                            obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                            drawable2 = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes2.getResourceId(90, -1));
                            obtainStyledAttributes2.recycle();
                            imageView.setImageDrawable(drawable2);
                        }
                    } else if (e == DownloadStatus.QUEUED) {
                        imageView.setImageResource(R.drawable.vector_download_queued);
                    } else if (e == DownloadStatus.PAUSED) {
                        new int[1][0] = R.attr.download_button_paused;
                        obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(13, -1)));
                        obtainStyledAttributes2.recycle();
                    } else if (e == DownloadStatus.DOWNLOADING) {
                        if (DownloadManager.c().w()) {
                            imageView.setVisibility(8);
                            progressBar.setVisibility(0);
                        } else {
                            imageView.setVisibility(0);
                            imageView.setImageResource(R.drawable.vector_download_queued);
                        }
                    } else if (e == DownloadStatus.TRIED_BUT_FAILED) {
                        obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(91, -1));
                        obtainStyledAttributes2.recycle();
                        imageView.setImageDrawable(drawable2);
                    } else {
                        new int[1][0] = R.attr.download_button_paused;
                        obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(13, -1)));
                        obtainStyledAttributes2.recycle();
                    }
                } else {
                    new int[1][0] = R.attr.download_button_paused;
                    obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(13, -1)));
                    obtainStyledAttributes2.recycle();
                }
            }
        }
    }

    public void getPopulatedViewGrid(View view, BusinessObject businessObject) {
        SquareImageView squareImageView = (SquareImageView) view.findViewById(R.id.imgProductIcon);
        TextView textView = (TextView) view.findViewById(R.id.f54grid.item.tv.name);
        TextView textView2 = (TextView) view.findViewById(R.id.f52grid.item.text.favoritecount);
        if (view.findViewById(R.id.f50grid.item.image.download) != null) {
            view.findViewById(R.id.f50grid.item.image.download).setOnClickListener(this);
        }
        if (view.findViewById(R.id.f51grid.item.image.favorite) != null) {
            view.findViewById(R.id.f51grid.item.image.favorite).setOnClickListener(this);
        }
        if (businessObject instanceof Item) {
            Item item = (Item) businessObject;
            if (squareImageView != null) {
                squareImageView.bindImage(item.getArtwork() != null ? item.getArtwork().replace("80x80", "175x175") : null, squareImageView.getScaleType());
            }
            if (textView != null) {
                textView.setText(item.getName());
            }
            if (textView2 != null) {
                updateFavoriteCount(textView2, item, view.findViewById(R.id.f51grid.item.image.favorite).getVisibility());
            }
        }
        if (!((businessObject instanceof DiscoverTag) && ((DiscoverTag) businessObject).getTagEntityType() == null)) {
            initFavoriteDownload(view, businessObject);
        }
        view.setOnClickListener(this);
        super.getPoplatedView(view, businessObject, null);
    }

    public void setItemPosition(int i) {
        this.mPosition = i;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, boolean z, Boolean bool) {
        TwoRecommendedItemHolder twoRecommendedItemHolder = (TwoRecommendedItemHolder) viewHolder;
        if (z) {
            twoRecommendedItemHolder.headerText.setVisibility(0);
        } else {
            twoRecommendedItemHolder.headerText.setVisibility(8);
        }
        if (bool.booleanValue()) {
            return getDefaultPoplatedView(twoRecommendedItemHolder, businessObject);
        }
        return initView(twoRecommendedItemHolder, businessObject, viewGroup);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, boolean z, Boolean bool, v vVar) {
        this.onClickItemUpdateListener = vVar;
        return getPoplatedView(viewHolder, businessObject, viewGroup, z, bool);
    }

    public void onFavoriteCompleted(BusinessObject businessObject, boolean z) {
        if (this.mFragment != null) {
            this.mFragment.refreshListView();
        }
    }

    public boolean performClick() {
        return super.performClick();
    }

    /* JADX WARNING: Missing block: B:20:0x004a, code skipped:
            if (r4.getEntityType().equals(com.constants.c.c.c) != false) goto L_0x004e;
     */
    private boolean shouldShowFavorite(com.gaana.models.BusinessObject r4) {
        /*
        r3 = this;
        r0 = r3.mContext;
        r1 = 1;
        if (r0 == 0) goto L_0x000c;
    L_0x0005:
        r0 = r3.isFavouriteContainsFragment();
        if (r0 == 0) goto L_0x000c;
    L_0x000b:
        return r1;
    L_0x000c:
        r0 = r4 instanceof com.gaana.models.Playlists.Playlist;
        if (r0 != 0) goto L_0x004e;
    L_0x0010:
        r0 = r4 instanceof com.gaana.models.Albums.Album;
        if (r0 != 0) goto L_0x004e;
    L_0x0014:
        r0 = r4 instanceof com.gaana.models.Tracks.Track;
        if (r0 != 0) goto L_0x004e;
    L_0x0018:
        r0 = r4 instanceof com.gaana.models.Item;
        if (r0 == 0) goto L_0x004d;
    L_0x001c:
        r4 = (com.gaana.models.Item) r4;
        r0 = r4.getEntityType();
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x004d;
    L_0x0028:
        r0 = r4.getEntityType();
        r2 = com.constants.c.c.a;
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x004e;
    L_0x0034:
        r0 = r4.getEntityType();
        r2 = com.constants.c.c.b;
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x004e;
    L_0x0040:
        r4 = r4.getEntityType();
        r0 = com.constants.c.c.c;
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x004d;
    L_0x004c:
        goto L_0x004e;
    L_0x004d:
        return r1;
    L_0x004e:
        r4 = 0;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.item.RecommendedItemView.shouldShowFavorite(com.gaana.models.BusinessObject):boolean");
    }

    private boolean isFavouriteContainsFragment() {
        BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
        if ((currentFragment instanceof MyMusicItemFragment) || (currentFragment instanceof MyMusicFragment) || (currentFragment instanceof FavoritesFragment)) {
            return this.mFragment.isDownLoadFragment() ^ 1;
        }
        return false;
    }

    public void play(PlayerTrack playerTrack) {
        playerTrack.d(true);
        PlayerManager.a(this.mContext).c();
        PlayerManager.a(this.mContext).a(null, playerTrack, 999999);
        PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
        ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
    }

    private boolean checkForContains(ArrayList<BusinessObject> arrayList, Track track) {
        if (!(arrayList == null || track == null)) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (((BusinessObject) it.next()).getBusinessObjId().equals(track.getBusinessObjId())) {
                    return true;
                }
            }
        }
        return false;
    }
}
