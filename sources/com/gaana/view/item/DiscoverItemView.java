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
import com.fragments.GridActivityFragment;
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
import com.gaana.view.item.BaseItemView.GridItemHolder;
import com.gaana.view.item.BaseItemView.TwoGridItemHolder;
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
import com.managers.an;
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

public class DiscoverItemView extends BaseItemView implements OnClickListener, a {
    private String mGASectionName = "";
    private int mLayoutHomeResourceId = R.layout.grid_twoitem_view;
    private int mLayoutResourceId = R.layout.view_item_discover;
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

    public DiscoverItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
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
        GridItemHolder gridItemHolder = (GridItemHolder) viewHolder;
        if (gridItemHolder.parentLayout != null) {
            gridItemHolder.parentLayout.setTag(businessObject);
        }
        getPoplatedViewGrid(gridItemHolder, businessObject, viewGroup);
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
                                    ((GaanaActivity) DiscoverItemView.this.mContext).displayFragment(dynamicOccasionFragment);
                                }

                                public void onOccasionError() {
                                    aj.a().a(DiscoverItemView.this.mContext, DiscoverItemView.this.getResources().getString(R.string.error_download_no_internet));
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
                                DiscoverItemView.this.deleteDownload(businessObject);
                                if (businessObject instanceof Track) {
                                    e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
                                } else if ((businessObject instanceof Playlist) || (businessObject instanceof Album)) {
                                    e = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
                                } else {
                                    e = null;
                                }
                                DiscoverItemView.this.updateDownloadImage((ImageView) view, e);
                            }
                        }).show();
                    } else {
                        Util.a(this.mContext, null);
                        if (z) {
                            u.a().a("Expired Download", "Click", "Track");
                        } else if (businessObject instanceof Playlist) {
                            u.a().a("Expired Download", "Click", "Playlist");
                        } else if (businessObject instanceof Album) {
                            u.a().a("Expired Download", "Click", "Album");
                        }
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
                            DiscoverItemView.this.updateDownloadImage((ImageView) view, e);
                            DiscoverItemView.this.updateOfflineTracksStatus();
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
            if (view.getId() == R.id.play_icon) {
                af.a(this.mContext, this.mFragment).a((int) R.id.playMenu, businessObject);
                return;
            }
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
                    StringBuilder stringBuilder2;
                    BusinessObject businessObject2;
                    StringBuilder stringBuilder3;
                    BaseActivity baseActivity;
                    if ((businessObject instanceof DiscoverTag) || view.getId() == R.id.discoverTagImg) {
                        if (this.mAppState.isAppInOfflineMode()) {
                            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_item));
                        } else if (Util.j(this.mContext)) {
                            BaseActivity baseActivity2 = (BaseActivity) this.mContext;
                            String str = ((BaseActivity) this.mContext).currentScreen;
                            StringBuilder stringBuilder4 = new StringBuilder();
                            DiscoverTag discoverTag = (DiscoverTag) businessObject;
                            stringBuilder4.append(discoverTag.getEnglishName());
                            stringBuilder4.append(" Detail ");
                            string = stringBuilder4.toString();
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(((BaseActivity) this.mContext).currentScreen);
                            stringBuilder2.append(" - Discover - ");
                            stringBuilder2.append(discoverTag.getEnglishName());
                            baseActivity2.sendGAEvent(str, string, stringBuilder2.toString());
                            if (!(discoverTag.getRawName() == null || TextUtils.isEmpty(discoverTag.getRawName()))) {
                                stringBuilder4 = new StringBuilder();
                                stringBuilder4.append("col:discover:");
                                stringBuilder4.append(discoverTag.getEnglishName());
                                q.a().a("int", stringBuilder4.toString());
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
                        businessObject2 = (Playlist) businessObject;
                        if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().b(businessObject2).booleanValue()) {
                            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_playlist));
                        } else if (!Util.j(this.mContext) && !DownloadManager.c().b(businessObject2).booleanValue()) {
                            ap.a().f(this.mContext);
                        } else if ((this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) && !ap.a().a(businessObject2, null)) {
                            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.toast_subscription_expired));
                        } else {
                            if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                                stringBuilder3 = new StringBuilder();
                                stringBuilder3.append("Position ");
                                stringBuilder3.append(this.mPosition);
                                stringBuilder3.append(" - Playlist - ");
                                stringBuilder3.append(businessObject2.getBusinessObjId());
                                ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder3.toString());
                            } else {
                                baseActivity = (BaseActivity) this.mContext;
                                string = ((BaseActivity) this.mContext).currentScreen;
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Playlist Detail  : ");
                                stringBuilder2.append(businessObject2.getEnglishName());
                                baseActivity.sendGAEvent(string, stringBuilder2.toString(), ((BaseActivity) this.mContext).currentScreen);
                            }
                            this.mListingComponents = Constants.e();
                            this.mAppState.setListingComponents(this.mListingComponents);
                            if (businessObject2.isGaanaSpecial()) {
                                populateSpecialGaanaListing(businessObject2);
                            } else {
                                populatePlaylistListing(businessObject2);
                            }
                            if (this.mFragment instanceof GridActivityFragment) {
                                an.a().a("click", "en", ((GridActivityFragment) this.mFragment).c(), "SEEALL", businessObject.getBusinessObjId(), "", String.valueOf(this.mPosition), "");
                            }
                        }
                    } else if (businessObject instanceof Album) {
                        businessObject2 = (Album) businessObject;
                        if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().b(businessObject2).booleanValue()) {
                            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_playlist));
                        } else if (!Util.j(this.mContext) && !DownloadManager.c().b(businessObject2).booleanValue()) {
                            ap.a().f(this.mContext);
                        } else if ((this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) && !ap.a().a(businessObject2, null)) {
                            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.toast_subscription_expired));
                        } else {
                            if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                                stringBuilder3 = new StringBuilder();
                                stringBuilder3.append("Position ");
                                stringBuilder3.append(this.mPosition);
                                stringBuilder3.append(" - Album - ");
                                stringBuilder3.append(businessObject2.getBusinessObjId());
                                ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder3.toString());
                            } else {
                                baseActivity = (BaseActivity) this.mContext;
                                string = ((BaseActivity) this.mContext).currentScreen;
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Album Detail  : ");
                                stringBuilder2.append(businessObject2.getEnglishName());
                                baseActivity.sendGAEvent(string, stringBuilder2.toString(), ((BaseActivity) this.mContext).currentScreen);
                            }
                            this.mListingComponents = Constants.b();
                            this.mAppState.setListingComponents(this.mListingComponents);
                            populateAlbumListing(businessObject2);
                            if (this.mFragment instanceof GridActivityFragment) {
                                an.a().a("click", "en", ((GridActivityFragment) this.mFragment).c(), "SEEALL", businessObject.getBusinessObjId(), "", String.valueOf(this.mPosition), "");
                            }
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
                        if (this.mFragment instanceof GridActivityFragment) {
                            an.a().a("click", "en", ((GridActivityFragment) this.mFragment).c(), "SEEALL", businessObject.getBusinessObjId(), "", String.valueOf(this.mPosition), "");
                        } else if (this.mFragment instanceof RevampedDetailListing) {
                            an.a().a("click", "en", ((RevampedDetailListing) this.mFragment).s().getBusinessObjId(), ((RevampedDetailListing) this.mFragment).s().getName(), businessObject.getBusinessObjId(), businessObject.getName(), String.valueOf(this.mPosition), "");
                        }
                        playRadio(radio);
                    } else if (businessObject instanceof Track) {
                        Track track = (Track) businessObject;
                        if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                            StringBuilder stringBuilder5 = new StringBuilder();
                            stringBuilder5.append("Position ");
                            stringBuilder5.append(this.mPosition);
                            stringBuilder5.append(" - Track - ");
                            stringBuilder5.append(track.getBusinessObjId());
                            ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder5.toString());
                        } else {
                            BaseActivity baseActivity3 = (BaseActivity) this.mContext;
                            String str2 = ((BaseActivity) this.mContext).currentScreen;
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append("Track Detail  : ");
                            stringBuilder3.append(track.getEnglishName());
                            baseActivity3.sendGAEvent(str2, stringBuilder3.toString(), ((BaseActivity) this.mContext).currentScreen);
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
                        if (this.mFragment instanceof GridActivityFragment) {
                            an.a().a("click", "en", ((GridActivityFragment) this.mFragment).c(), "SEEALL", businessObject.getBusinessObjId(), "", String.valueOf(this.mPosition), "");
                        }
                    } else if (businessObject instanceof Artist) {
                        Artist artist = (Artist) businessObject;
                        this.mListingComponents = Constants.a("", businessObject.isLocalMedia());
                        this.mAppState.setListingComponents(this.mListingComponents);
                        ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Artist Detail", ((BaseActivity) this.mContext).currentScreen);
                        if (this.mFragment instanceof GridActivityFragment) {
                            an.a().a("click", "en", ((GridActivityFragment) this.mFragment).c(), "SEEALL", businessObject.getBusinessObjId(), "", String.valueOf(this.mPosition), "");
                        }
                        populateArtistListing(artist);
                    } else if (businessObject instanceof YouTubeVideo) {
                        YouTubeVideo youTubeVideo = (YouTubeVideo) businessObject;
                        launchYouTubePlayer(youTubeVideo.c(), youTubeVideo.a(), businessObject, youTubeVideo.e());
                        if (this.onClickItemUpdateListener != null) {
                            this.onClickItemUpdateListener.a(businessObject, false);
                        }
                        if (this.mFragment instanceof GridActivityFragment) {
                            an.a().a("click", "en", ((GridActivityFragment) this.mFragment).c(), "SEEALL", businessObject.getBusinessObjId(), "", String.valueOf(this.mPosition), "");
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
                                DiscoverItemView.this.playRadio(radio);
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

    public View getDefaultPoplatedView(TwoGridItemHolder twoGridItemHolder, BusinessObject businessObject) {
        this.mView.findViewById(R.id.f58ll.grid.firstitem);
        GridItemHolder gridItemHolder = twoGridItemHolder.firstHolder;
        if (gridItemHolder != null) {
            gridItemHolder.itemView.setVisibility(0);
            if (businessObject instanceof Item) {
                if (((Item) businessObject).getEntityType().equals(com.constants.c.c.c)) {
                    gridItemHolder.play_icon.setVisibility(0);
                } else {
                    gridItemHolder.play_icon.setVisibility(4);
                }
            }
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            gridItemHolder.imageViewThumb.setImageDrawable(drawable);
            gridItemHolder.imageViewThumbRect.setImageDrawable(drawable);
            obtainStyledAttributes.recycle();
            gridItemHolder.tvName.setText(((BusinessObject) businessObject.getArrListBusinessObj().get(0)).getName());
        }
        gridItemHolder = twoGridItemHolder.secondHolder;
        View view = gridItemHolder.itemView;
        if (gridItemHolder != null) {
            if (businessObject.getArrListBusinessObj().size() == 1) {
                gridItemHolder.itemView.setVisibility(8);
            } else {
                gridItemHolder.itemView.setVisibility(0);
                if (businessObject instanceof Item) {
                    if (((Item) businessObject).getEntityType().equals(com.constants.c.c.c)) {
                        gridItemHolder.play_icon.setVisibility(0);
                    } else {
                        gridItemHolder.play_icon.setVisibility(4);
                    }
                }
                TypedArray obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
                Drawable drawable2 = obtainStyledAttributes2.getDrawable(0);
                gridItemHolder.imageViewThumb.setImageDrawable(drawable2);
                gridItemHolder.imageViewThumbRect.setImageDrawable(drawable2);
                obtainStyledAttributes2.recycle();
                gridItemHolder.tvName.setText(((BusinessObject) businessObject.getArrListBusinessObj().get(1)).getName());
            }
        }
        return twoGridItemHolder.itemView;
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

    public View initView(TwoGridItemHolder twoGridItemHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        twoGridItemHolder.itemView.setClickable(false);
        if (businessObject != null) {
            ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
            int size = arrListBusinessObj.size();
            GridItemHolder gridItemHolder = twoGridItemHolder.firstHolder;
            gridItemHolder.itemView.setVisibility(0);
            getPoplatedViewGrid(gridItemHolder, (BusinessObject) arrListBusinessObj.get(0), viewGroup);
            if (this.mFragment instanceof DynamicHomeFragment) {
                twoGridItemHolder.headerText.setText(this.mContext.getResources().getString(R.string.home_grid_header_text));
            } else if (this.mFragment instanceof RadioActivityFragment) {
                twoGridItemHolder.headerText.setText(R.string.gaana_radios);
            } else {
                twoGridItemHolder.headerText.setVisibility(8);
            }
            gridItemHolder = twoGridItemHolder.secondHolder;
            if (size == 2) {
                gridItemHolder.itemView.setVisibility(0);
                getPoplatedViewGrid(gridItemHolder, (BusinessObject) arrListBusinessObj.get(1), viewGroup);
            } else {
                gridItemHolder.itemView.setVisibility(8);
            }
        }
        return twoGridItemHolder.itemView;
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

    public void getPoplatedViewGrid(GridItemHolder gridItemHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        View view = gridItemHolder.itemView;
        CrossFadeImageView crossFadeImageView = gridItemHolder.imageViewThumb;
        TextView textView = gridItemHolder.tvName;
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
                gridItemHolder.play_icon.setVisibility(0);
            } else if (item.getEntityType().equals(com.constants.c.c.f)) {
                gridItemHolder.play_icon.setVisibility(0);
                gridItemHolder.play_icon.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.vector_ic_video_indicator));
            } else {
                gridItemHolder.play_icon.setVisibility(4);
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

    /* JADX WARNING: Removed duplicated region for block: B:110:0x02f6  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0250  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x02f9  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0250  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x02f6  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x02f9  */
    /* JADX WARNING: Missing block: B:69:0x01d8, code skipped:
            if (r23.equals(com.dynamicview.DynamicViewManager.DynamicViewType.grid_rect.name()) != false) goto L_0x01f1;
     */
    public android.view.View getViewAllGriditemView(android.support.v7.widget.RecyclerView.ViewHolder r19, com.gaana.models.BusinessObject r20, android.view.ViewGroup r21, int r22, java.lang.String r23) {
        /*
        r18 = this;
        r0 = r18;
        r1 = r20;
        r2 = r19;
        r2 = (com.gaana.view.item.BaseItemView.GridItemHolder) r2;
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
        r8 = r2.tvSectionTitle;
        r6.setTag(r1);
        r6.setOnClickListener(r0);
        r3.setTag(r1);
        r4.setTag(r1);
        r6 = r1 instanceof com.gaana.models.Item;
        if (r6 == 0) goto L_0x0308;
    L_0x0031:
        r6 = r1;
        r6 = (com.gaana.models.Item) r6;
        r9 = r6.getArtworkMedium();
        if (r9 == 0) goto L_0x0040;
    L_0x003a:
        r10 = android.text.TextUtils.isEmpty(r9);
        if (r10 == 0) goto L_0x0044;
    L_0x0040:
        r9 = r6.getArtwork();
    L_0x0044:
        r10 = r0.mContext;
        r9 = com.utilities.Util.h(r10, r9);
        if (r9 == 0) goto L_0x0072;
    L_0x004c:
        r10 = android.text.TextUtils.isEmpty(r9);
        if (r10 != 0) goto L_0x0072;
    L_0x0052:
        r10 = r6.getEntityType();
        r11 = com.constants.c.d.e;
        if (r10 != r11) goto L_0x006a;
    L_0x005a:
        r10 = r6.getEntityType();
        r11 = com.constants.c.d.d;
        if (r10 != r11) goto L_0x006a;
    L_0x0062:
        r10 = r6.getEntityType();
        r11 = com.constants.c.d.c;
        if (r10 == r11) goto L_0x0072;
    L_0x006a:
        r10 = "80x80";
        r11 = "175x175";
        r9 = r9.replace(r10, r11);
    L_0x0072:
        r10 = r6.getName();
        r5.setText(r10);
        r10 = r2.txtHeaderName;
        r11 = 4;
        r10.setVisibility(r11);
        r10 = r2.mImgIndicator;
        r10.setVisibility(r11);
        r10 = r6.getEntityType();
        r12 = com.constants.c.c.c;
        r10 = r10.equals(r12);
        r12 = 8;
        r13 = 0;
        if (r10 == 0) goto L_0x00c8;
    L_0x0093:
        r10 = r2.play_icon;
        r10.setVisibility(r13);
        r10 = r2.mImgIndicator;
        r10.setVisibility(r13);
        r10 = r2.mImgIndicator;
        r14 = r0.mContext;
        r15 = 2131232158; // 0x7f08059e float:1.8080417E38 double:1.0529685926E-314;
        r14 = android.support.v4.content.ContextCompat.getDrawable(r14, r15);
        r10.setImageDrawable(r14);
        r10 = r2.mThumbnailRightTopIndicator;
        if (r10 == 0) goto L_0x0137;
    L_0x00af:
        r10 = r6.getPremiumContent();
        r14 = com.constants.Constants.cV;
        r10 = r10.equalsIgnoreCase(r14);
        if (r10 == 0) goto L_0x00c2;
    L_0x00bb:
        r10 = r2.mThumbnailRightTopIndicator;
        r10.setVisibility(r13);
        goto L_0x0137;
    L_0x00c2:
        r10 = r2.mThumbnailRightTopIndicator;
        r10.setVisibility(r12);
        goto L_0x0137;
    L_0x00c8:
        r10 = r6.getEntityType();
        r14 = com.constants.c.c.j;
        r10 = r10.equals(r14);
        if (r10 == 0) goto L_0x00e3;
    L_0x00d4:
        r10 = r2.txtHeaderName;
        r14 = r20.getName();
        r10.setText(r14);
        r10 = r2.txtHeaderName;
        r10.setVisibility(r11);
        goto L_0x0137;
    L_0x00e3:
        r10 = r6.getEntityType();
        r14 = com.constants.c.c.f;
        r10 = r10.equals(r14);
        if (r10 == 0) goto L_0x00f5;
    L_0x00ef:
        r10 = r2.play_icon;
        r10.setVisibility(r13);
        goto L_0x0137;
    L_0x00f5:
        r10 = r6.getEntityType();
        r14 = com.constants.c.c.a;
        r10 = r10.equals(r14);
        r14 = 2131232145; // 0x7f080591 float:1.808039E38 double:1.052968586E-314;
        if (r10 == 0) goto L_0x0115;
    L_0x0104:
        r10 = r2.mImgIndicator;
        r10.setVisibility(r13);
        r10 = r2.mImgIndicator;
        r15 = r0.mContext;
        r14 = android.support.v4.content.ContextCompat.getDrawable(r15, r14);
        r10.setImageDrawable(r14);
        goto L_0x0137;
    L_0x0115:
        r10 = r6.getEntityType();
        r15 = com.constants.c.c.b;
        r10 = r10.equals(r15);
        if (r10 == 0) goto L_0x0132;
    L_0x0121:
        r10 = r2.mImgIndicator;
        r10.setVisibility(r13);
        r10 = r2.mImgIndicator;
        r15 = r0.mContext;
        r14 = android.support.v4.content.ContextCompat.getDrawable(r15, r14);
        r10.setImageDrawable(r14);
        goto L_0x0137;
    L_0x0132:
        r10 = r2.play_icon;
        r10.setVisibility(r11);
    L_0x0137:
        r10 = r6.getEntityType();
        r14 = com.constants.c.c.g;
        r10 = r10.equals(r14);
        if (r10 != 0) goto L_0x0152;
    L_0x0143:
        r10 = r6.getEntityType();
        r15 = com.constants.c.c.i;
        r10 = r10.equals(r15);
        if (r10 == 0) goto L_0x0150;
    L_0x014f:
        goto L_0x0152;
    L_0x0150:
        r10 = r13;
        goto L_0x0153;
    L_0x0152:
        r10 = 1;
    L_0x0153:
        r15 = r6.getEntityType();
        r14 = com.constants.c.c.f;
        r14 = r15.equals(r14);
        if (r14 != 0) goto L_0x017e;
    L_0x015f:
        r14 = r6.getEntityType();
        r15 = com.constants.c.c.b;
        r14 = r14.equals(r15);
        if (r14 != 0) goto L_0x0177;
    L_0x016b:
        r14 = r6.getEntityType();
        r15 = com.constants.c.c.a;
        r14 = r14.equals(r15);
        if (r14 == 0) goto L_0x017c;
    L_0x0177:
        r14 = com.constants.Constants.aW;
        if (r14 == 0) goto L_0x017c;
    L_0x017b:
        goto L_0x017e;
    L_0x017c:
        r14 = r13;
        goto L_0x017f;
    L_0x017e:
        r14 = 1;
    L_0x017f:
        if (r14 == 0) goto L_0x01a1;
    L_0x0181:
        r14 = r6.getEntityType();
        r15 = com.constants.c.c.f;
        r14 = r14.equals(r15);
        if (r14 == 0) goto L_0x019b;
    L_0x018d:
        r14 = r2.play_icon;
        r15 = r0.mContext;
        r12 = 2131232162; // 0x7f0805a2 float:1.8080425E38 double:1.0529685946E-314;
        r12 = android.support.v4.content.ContextCompat.getDrawable(r15, r12);
        r14.setImageDrawable(r12);
    L_0x019b:
        r12 = r2.play_icon;
        r12.setVisibility(r13);
        goto L_0x01a6;
    L_0x01a1:
        r12 = r2.play_icon;
        r12.setVisibility(r11);
    L_0x01a6:
        r12 = com.constants.Constants.aW;
        if (r12 == 0) goto L_0x01b4;
    L_0x01aa:
        r12 = r2.play_icon;
        r12.setOnClickListener(r0);
        r2 = r2.play_icon;
        r2.setTag(r1);
    L_0x01b4:
        r2 = r6.getEntityType();
        r12 = com.constants.c.c.g;
        r2 = r2.equals(r12);
        if (r2 != 0) goto L_0x01f1;
    L_0x01c0:
        r2 = r6.getEntityType();
        r12 = com.constants.c.c.i;
        r2 = r2.equals(r12);
        if (r2 == 0) goto L_0x01db;
    L_0x01cc:
        r2 = com.dynamicview.DynamicViewManager.DynamicViewType.grid_rect;
        r2 = r2.name();
        r12 = r23;
        r2 = r12.equals(r2);
        if (r2 == 0) goto L_0x01db;
    L_0x01da:
        goto L_0x01f1;
    L_0x01db:
        r3.setVisibility(r13);
        r2 = 8;
        r4.setVisibility(r2);
        if (r8 == 0) goto L_0x01e8;
    L_0x01e5:
        r8.setVisibility(r2);
    L_0x01e8:
        r2 = r3.getScaleType();
        r3.bindImage(r9, r2);
        r3 = 1;
        goto L_0x024a;
    L_0x01f1:
        r2 = 8;
        r12 = r0.mContext;
        r12 = r12.getResources();
        r14 = 2131165677; // 0x7f0701ed float:1.7945578E38 double:1.0529357466E-314;
        r12 = r12.getDimension(r14);
        r5.setVisibility(r2);
        r3.setVisibility(r2);
        if (r8 == 0) goto L_0x0217;
    L_0x0208:
        r8.setVisibility(r13);
        r2 = r6.getName();
        r8.setText(r2);
        r2 = 0;
        r3 = 1;
        r8.setTypeface(r2, r3);
    L_0x0217:
        r4.setVisibility(r13);
        if (r10 != 0) goto L_0x0225;
    L_0x021c:
        r2 = r4.getScaleType();
        r4.bindImage(r9, r2);
    L_0x0223:
        r3 = 1;
        goto L_0x0240;
    L_0x0225:
        r2 = 2131231682; // 0x7f0803c2 float:1.8079452E38 double:1.0529683574E-314;
        r4.setImageResource(r2);
        if (r8 == 0) goto L_0x0223;
    L_0x022d:
        r2 = r0.mContext;
        r2 = com.utilities.Util.h(r2);
        r8.setTypeface(r2);
        r3 = 1;
        r8.setAllCaps(r3);
        r2 = 2;
        r9 = 1096810496; // 0x41600000 float:14.0 double:5.41896386E-315;
        r8.setTextSize(r2, r9);
    L_0x0240:
        r2 = new android.widget.FrameLayout$LayoutParams;
        r8 = -1;
        r9 = (int) r12;
        r2.<init>(r8, r9);
        r4.setLayoutParams(r2);
    L_0x024a:
        r2 = r6.getEntityInfo();
        if (r2 == 0) goto L_0x02f6;
    L_0x0250:
        r8 = r13;
        r9 = r8;
    L_0x0252:
        r12 = r2.size();
        if (r8 >= r12) goto L_0x02f7;
    L_0x0258:
        r12 = r2.get(r8);
        r12 = (com.gaana.models.EntityInfo) r12;
        r12 = r12.getKey();
        r14 = "parental_warning";
        r12 = r12.equals(r14);
        if (r12 == 0) goto L_0x02ab;
    L_0x026a:
        r9 = r2.get(r8);
        r9 = (com.gaana.models.EntityInfo) r9;
        r9 = r9.getValue();
        r9 = r9 instanceof java.lang.Double;
        if (r9 == 0) goto L_0x0296;
    L_0x0278:
        r9 = r2.get(r8);
        r9 = (com.gaana.models.EntityInfo) r9;
        r9 = r9.getValue();
        r9 = (java.lang.Double) r9;
        r14 = r9.doubleValue();
        r16 = r4;
        r3 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r3 = java.lang.Double.compare(r14, r3);
        if (r3 != 0) goto L_0x0294;
    L_0x0292:
        r9 = 1;
        goto L_0x02a8;
    L_0x0294:
        r9 = r13;
        goto L_0x02a8;
    L_0x0296:
        r16 = r4;
        r3 = r2.get(r8);
        r3 = (com.gaana.models.EntityInfo) r3;
        r3 = r3.getValue();
        r4 = "1";
        r9 = r3.equals(r4);
    L_0x02a8:
        r3 = r16;
        goto L_0x02f0;
    L_0x02ab:
        r16 = r4;
        if (r10 == 0) goto L_0x02a8;
    L_0x02af:
        r3 = r2.get(r8);
        r3 = (com.gaana.models.EntityInfo) r3;
        r3 = r3.getKey();
        r4 = "bg_colour";
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x02a8;
    L_0x02c1:
        r3 = r2.get(r8);
        r3 = (com.gaana.models.EntityInfo) r3;
        r3 = r3.getValue();
        if (r3 == 0) goto L_0x02a8;
    L_0x02cd:
        r3 = java.lang.String.valueOf(r3);
        r4 = android.text.TextUtils.isEmpty(r3);
        if (r4 != 0) goto L_0x02a8;
    L_0x02d7:
        r4 = new android.graphics.drawable.GradientDrawable;
        r4.<init>();
        r12 = com.utilities.Util.b(r11);
        r12 = (float) r12;
        r4.setCornerRadius(r12);
        r3 = android.graphics.Color.parseColor(r3);
        r4.setColor(r3);
        r3 = r16;
        r3.setImageDrawable(r4);
    L_0x02f0:
        r8 = r8 + 1;
        r4 = r3;
        r3 = 1;
        goto L_0x0252;
    L_0x02f6:
        r9 = r13;
    L_0x02f7:
        if (r9 == 0) goto L_0x0300;
    L_0x02f9:
        r2 = r6.getName();
        com.utilities.Util.a(r5, r2);
    L_0x0300:
        r2 = r0.mContext;
        r3 = 2131886676; // 0x7f120254 float:1.9407938E38 double:1.0532919674E-314;
        r5.setTextAppearance(r2, r3);
    L_0x0308:
        if (r7 == 0) goto L_0x031a;
    L_0x030a:
        r2 = r0.mView;
        r3 = 2131297274; // 0x7f0903fa float:1.8212488E38 double:1.053000764E-314;
        r2 = r2.findViewById(r3);
        r2 = r2.getVisibility();
        r0.updateFavoriteCount(r7, r1, r2);
    L_0x031a:
        r2 = r0.mView;
        r2.setOnClickListener(r0);
        r2 = r0.mView;
        r3 = r21;
        r1 = super.getPoplatedView(r2, r1, r3);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.item.DiscoverItemView.getViewAllGriditemView(android.support.v7.widget.RecyclerView$ViewHolder, com.gaana.models.BusinessObject, android.view.ViewGroup, int, java.lang.String):android.view.View");
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
        TwoGridItemHolder twoGridItemHolder = (TwoGridItemHolder) viewHolder;
        if (z) {
            twoGridItemHolder.headerText.setVisibility(0);
        } else {
            twoGridItemHolder.headerText.setVisibility(8);
        }
        if (bool.booleanValue()) {
            return getDefaultPoplatedView(twoGridItemHolder, businessObject);
        }
        return initView(twoGridItemHolder, businessObject, viewGroup);
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
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.item.DiscoverItemView.shouldShowFavorite(com.gaana.models.BusinessObject):boolean");
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
