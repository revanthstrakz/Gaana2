package com.gaana.view.item;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.c.c;
import com.constants.c.d;
import com.fragments.BaseGaanaFragment;
import com.fragments.DiscoverDetailFragment;
import com.fragments.ListingFragment;
import com.fragments.RevampedDetailListing;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
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
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.library.controls.CrossFadeImageView;
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
import com.managers.u;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Map;

public class NewGenericItemView extends BaseItemView implements OnClickListener {
    private ImageView clickoptionImage;
    private int mLayoutResourceId = R.layout.view_generic_new_item;
    private int mPosition = -1;
    private int mViewType = -1;

    public static class NewGenericItemHolder extends ViewHolder {
        private ImageView clickoptionImage;
        private View divider;
        private CrossFadeImageView mCrossFadeImageIcon;
        private TextView tvSubtitle;
        private TextView tvTitle;

        public NewGenericItemHolder(View view) {
            super(view);
            this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.tvTitle = (TextView) view.findViewById(R.id.f54grid.item.tv.name);
            this.tvSubtitle = (TextView) view.findViewById(R.id.f53grid.item.tv.genere);
            this.clickoptionImage = (ImageView) view.findViewById(R.id.clickoptionImage);
            this.divider = view.findViewById(R.id.item_divider);
        }
    }

    public NewGenericItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mLayoutId = R.layout.view_generic_new_item;
    }

    public View getEmptyView(View view, ViewGroup viewGroup, BusinessObjectType businessObjectType) {
        return view == null ? inflateView(this.mLayoutResourceId, viewGroup) : view;
    }

    public void setViewType(int i) {
        this.mViewType = i;
    }

    public int getViewType() {
        return this.mViewType;
    }

    public String getArtistNames(Item item) {
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        String str = "";
        String language = item.getLanguage();
        if (arrayList == null) {
            return str;
        }
        int size = arrayList.size();
        String str2 = str;
        int i = 0;
        while (i < size) {
            if (((EntityInfo) arrayList.get(i)).getKey().equals("artist") || ((EntityInfo) arrayList.get(i)).getKey().equals("primaryartist")) {
                ArrayList arrayList2 = (ArrayList) ((EntityInfo) arrayList.get(i)).getValue();
                if (arrayList2 != null && arrayList2.size() > 0) {
                    String str3 = str2;
                    for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                        Map map = (Map) arrayList2.get(i2);
                        StringBuilder stringBuilder;
                        if (i2 == 0) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(str3);
                            stringBuilder.append(Constants.a((String) map.get("name"), language));
                            str3 = stringBuilder.toString();
                        } else {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(str3);
                            stringBuilder.append(", ");
                            stringBuilder.append(Constants.a((String) map.get("name"), language));
                            str3 = stringBuilder.toString();
                        }
                    }
                    str2 = str3;
                }
            }
            i++;
        }
        return str2;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        NewGenericItemHolder newGenericItemHolder = (NewGenericItemHolder) viewHolder;
        this.mView = newGenericItemHolder.itemView;
        TextView access$000 = newGenericItemHolder.tvTitle;
        TextView access$100 = newGenericItemHolder.tvSubtitle;
        if (this.mFragment.getParentFragment() instanceof RevampedDetailListing) {
            newGenericItemHolder.divider.setVisibility(8);
        }
        if (businessObject instanceof Item) {
            Item item = (Item) businessObject;
            if (item != null) {
                newGenericItemHolder.mCrossFadeImageIcon.setTag(item);
            }
            if (newGenericItemHolder.mCrossFadeImageIcon instanceof SquareImageView) {
                ((SquareImageView) newGenericItemHolder.mCrossFadeImageIcon).bindImage(item.getArtwork(), this.mAppState.isAppInOfflineMode());
            } else if (newGenericItemHolder.mCrossFadeImageIcon instanceof CrossFadeImageView) {
                newGenericItemHolder.mCrossFadeImageIcon.bindImage(item.getArtwork(), this.mAppState.isAppInOfflineMode());
            }
            if (access$000 != null) {
                access$000.setText(item.getName());
            }
            if (access$100 != null) {
                CharSequence artistNames = getArtistNames(item);
                if (!TextUtils.isEmpty(artistNames) && artistNames.equalsIgnoreCase("null")) {
                    artistNames = "";
                }
                ArrayList arrayList = (ArrayList) item.getEntityInfo();
                boolean z = false;
                if (arrayList != null) {
                    int size = arrayList.size();
                    int i = 0;
                    while (i < size) {
                        if (!((EntityInfo) arrayList.get(i)).getKey().equals("parental_warning")) {
                            i++;
                        } else if (!(((EntityInfo) arrayList.get(i)).getValue() instanceof Double)) {
                            z = ((EntityInfo) arrayList.get(i)).getValue().equals("1");
                        } else if (Double.compare(((Double) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue(), 1.0d) == 0) {
                            z = true;
                        }
                    }
                }
                if (z) {
                    access$100.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(Util.Y()), null, null, null);
                } else {
                    access$100.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                }
                access$100.setText(artistNames);
            }
            this.clickoptionImage = newGenericItemHolder.clickoptionImage;
            this.clickoptionImage.setRotation(90.0f);
            if (item.getEntityType().equals(c.c)) {
                this.clickoptionImage.setTag((Track) populateTrackClicked(item));
            } else if (item.getEntityType().equals(c.a)) {
                this.clickoptionImage.setTag((Playlist) populatePlaylistClicked(item));
            } else if (item.getEntityType().equals(c.b)) {
                this.clickoptionImage.setTag((Album) populateAlbumClicked(item));
            } else if (item.getEntityType().equals(c.d)) {
                this.clickoptionImage.setTag((Artist) populateArtistClicked(item));
            } else if (item.getEntityType().equals(d.c) || item.getEntityType().equals(d.d)) {
                this.clickoptionImage.setTag((Radio) populateRadioClicked(item));
            }
            this.clickoptionImage.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if ((NewGenericItemView.this.mFragment.getParentFragment() instanceof RevampedDetailListing) && ((RevampedDetailListing) NewGenericItemView.this.mFragment.getParentFragment()).c.startsWith("ArtistDetailScreen")) {
                        an.a().a("click", "ac", ((RevampedDetailListing) NewGenericItemView.this.mFragment.getParentFragment()).s().getBusinessObjId(), ((RevampedDetailListing) NewGenericItemView.this.mFragment.getParentFragment()).d(), ((BusinessObject) view.getTag()).getBusinessObjId(), "three dot menu", "", "");
                    }
                    NewGenericItemView.this.showOptionMenu(view);
                }
            });
        }
        if (!((businessObject instanceof DiscoverTag) && ((DiscoverTag) businessObject).getTagEntityType() == null)) {
            initFavoriteDownload(this.mView, businessObject);
        }
        this.mView.setOnClickListener(this);
        return super.getPoplatedView(this.mView, businessObject, viewGroup);
    }

    private void executeRequest(final View view) {
        BusinessObject businessObject = (BusinessObject) view.getTag();
        if (businessObject instanceof Item) {
            Item item = (Item) businessObject;
            String entityType = item.getEntityType();
            if (entityType.equals(c.a)) {
                businessObject = (Playlist) populatePlaylistClicked(item);
            } else if (entityType.equals(c.b)) {
                businessObject = (Album) populateAlbumClicked(item);
            } else if (entityType.equals(d.c) || entityType.equals(d.d)) {
                businessObject = (Radio) populateRadioClicked(item);
            } else if (entityType.equals(c.c)) {
                businessObject = (Track) populateTrackClicked(item);
            } else if (entityType.equals(c.d)) {
                businessObject = (Artist) populateArtistClicked(item);
            }
        }
        if (businessObject != null) {
            if ("1".equalsIgnoreCase(businessObject.getLocationAvailability()) && "0".equalsIgnoreCase(businessObject.getDeviceAvailability())) {
                ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
            } else if ("0".equalsIgnoreCase(businessObject.getLocationAvailability()) && "1".equalsIgnoreCase(businessObject.getDeviceAvailability())) {
                ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
            } else if (view.getId() == R.id.f51grid.item.image.favorite) {
                if (this.mAppState.isAppInOfflineMode()) {
                    ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(getContext().getString(R.string.this_feature));
                } else if (Util.j(this.mContext)) {
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
                    af.a(this.mContext, this.mFragment).a((int) R.id.favoriteMenu, businessObject);
                } else {
                    ap.a().f(this.mContext);
                }
            } else if (view.getId() == R.id.f50grid.item.image.download) {
                DownloadStatus e;
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Download", ((BaseActivity) this.mContext).currentScreen);
                MoEngage.getInstance().reportDownload(businessObject);
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
                    ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), getContext().getString(R.string.loading));
                    startDownload(businessObject);
                } else if (e == DownloadStatus.DOWNLOADED) {
                    if (!ap.a().k() || Util.a(businessObject)) {
                        new CustomDialogView(this.mContext, this.mContext.getResources().getString(R.string.toast_delete_downloaded_album), new OnButtonClickListener() {
                            public void onNegativeButtonClick() {
                            }

                            public void onPositiveButtonClick() {
                                DownloadStatus e;
                                NewGenericItemView.this.deleteDownload(businessObject);
                                if (businessObject instanceof Track) {
                                    e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
                                } else if ((businessObject instanceof Playlist) || (businessObject instanceof Album)) {
                                    e = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
                                } else {
                                    e = null;
                                }
                                NewGenericItemView.this.updateDownloadImage((ImageView) view, e);
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
                            NewGenericItemView.this.updateDownloadImage((ImageView) view, e);
                            NewGenericItemView.this.updateOfflineTracksStatus();
                        }
                    }).show();
                }
            }
        }
    }

    private boolean isItemAvailableForOffline(BusinessObject businessObject) {
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
            String entityType;
            Constants.i = false;
            Constants.j = "";
            super.onClick(view);
            if (businessObject instanceof Item) {
                Item item = (Item) businessObject;
                entityType = item.getEntityType();
                if (entityType == null) {
                    businessObject = (DiscoverTag) populateDiscoverTagClicked(item);
                } else if (entityType.equals(c.a)) {
                    businessObject = (Playlist) populatePlaylistClicked(item);
                } else if (entityType.equals(c.b)) {
                    businessObject = (Album) populateAlbumClicked(item);
                } else if (entityType.equals(d.c) || entityType.equals(d.d)) {
                    businessObject = (Radio) populateRadioClicked(item);
                } else if (entityType.equals(c.c)) {
                    businessObject = (Track) populateTrackClicked(item);
                } else if (entityType.equals(c.d)) {
                    businessObject = (Artist) populateArtistClicked(item);
                }
            } else if (businessObject instanceof DiscoverTag) {
                ((DiscoverTag) businessObject).getTagEntityType();
            }
            if (businessObject != null) {
                String str;
                boolean isItemAvailableForOffline = isItemAvailableForOffline(businessObject);
                if (this.mAppState.isAppInOfflineMode() && !isItemAvailableForOffline) {
                    str = businessObject instanceof Album ? "This album" : businessObject instanceof Track ? "This track" : businessObject instanceof Playlist ? "This playlist" : businessObject instanceof Radio ? "This radio" : businessObject.getBusinessObjType() == BusinessObjectType.Artists ? "This artist" : null;
                    if (str != null) {
                        ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(str);
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
                    StringBuilder stringBuilder;
                    StringBuilder stringBuilder2;
                    if ((businessObject instanceof DiscoverTag) || view.getId() == R.id.discoverTagImg) {
                        if (this.mAppState.isAppInOfflineMode()) {
                            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This item");
                        } else if (Util.j(this.mContext)) {
                            baseActivity = (BaseActivity) this.mContext;
                            str = ((BaseActivity) this.mContext).currentScreen;
                            stringBuilder = new StringBuilder();
                            DiscoverTag discoverTag = (DiscoverTag) businessObject;
                            stringBuilder.append(discoverTag.getEnglishName());
                            stringBuilder.append(" Detail ");
                            entityType = stringBuilder.toString();
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(((BaseActivity) this.mContext).currentScreen);
                            stringBuilder2.append(" - Discover - ");
                            stringBuilder2.append(discoverTag.getName());
                            baseActivity.sendGAEvent(str, entityType, stringBuilder2.toString());
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
                            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This playlist");
                        } else if (!Util.j(this.mContext) && !DownloadManager.c().b(businessObject).booleanValue()) {
                            ap.a().f(this.mContext);
                        } else if ((this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) && !ap.a().a(businessObject, null)) {
                            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.toast_subscription_expired));
                        } else {
                            if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Position ");
                                stringBuilder2.append(this.mPosition);
                                stringBuilder2.append(" - Playlist - ");
                                stringBuilder2.append(businessObject.getBusinessObjId());
                                ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder2.toString());
                            } else {
                                baseActivity = (BaseActivity) this.mContext;
                                str = ((BaseActivity) this.mContext).currentScreen;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Playlist Detail  : ");
                                stringBuilder.append(businessObject.getEnglishName());
                                baseActivity.sendGAEvent(str, stringBuilder.toString(), ((BaseActivity) this.mContext).currentScreen);
                            }
                            if ((this.mFragment.getParentFragment() instanceof RevampedDetailListing) && ((RevampedDetailListing) ((ListingFragment) this.mFragment).e()).s() != null) {
                                an.a().a("click", "ac", ((RevampedDetailListing) ((ListingFragment) this.mFragment).e()).s().getBusinessObjId(), "Playlist", businessObject.getBusinessObjId(), businessObject.getName(), "", "");
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
                        if ((this.mFragment instanceof ListingFragment) && (((ListingFragment) this.mFragment).e() instanceof RevampedDetailListing) && ((RevampedDetailListing) ((ListingFragment) this.mFragment).e()).s() != null) {
                            an.a().a("click", "ac", ((RevampedDetailListing) ((ListingFragment) this.mFragment).e()).s().getBusinessObjId(), "Album", businessObject.getBusinessObjId(), businessObject.getName(), "", "");
                        }
                        if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().b(businessObject).booleanValue()) {
                            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(getContext().getString(R.string.THIS_PLAYLIST));
                        } else if (!Util.j(this.mContext) && !DownloadManager.c().b(businessObject).booleanValue()) {
                            ap.a().f(this.mContext);
                        } else if ((this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) && !ap.a().a(businessObject, null)) {
                            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.toast_subscription_expired));
                        } else {
                            if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Position ");
                                stringBuilder2.append(this.mPosition);
                                stringBuilder2.append(" - Album - ");
                                stringBuilder2.append(businessObject.getBusinessObjId());
                                ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder2.toString());
                            } else {
                                baseActivity = (BaseActivity) this.mContext;
                                str = ((BaseActivity) this.mContext).currentScreen;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Album Detail  : ");
                                stringBuilder.append(businessObject.getEnglishName());
                                baseActivity.sendGAEvent(str, stringBuilder.toString(), ((BaseActivity) this.mContext).currentScreen);
                            }
                            this.mListingComponents = Constants.b();
                            this.mAppState.setListingComponents(this.mListingComponents);
                            populateAlbumListing(businessObject);
                        }
                    } else if (businessObject instanceof Radio) {
                        Radio radio = (Radio) businessObject;
                        if (radio.getType().equals(d.c)) {
                            if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Position ");
                                stringBuilder2.append(this.mPosition);
                                stringBuilder2.append(" - RadioMirchi - ");
                                stringBuilder2.append(radio.getBusinessObjId());
                                ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder2.toString());
                            } else {
                                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Radio Detail ", ((BaseActivity) this.mContext).currentScreen);
                            }
                        } else if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Position ");
                            stringBuilder2.append(this.mPosition);
                            stringBuilder2.append(" - GaanaRadio - ");
                            stringBuilder2.append(radio.getBusinessObjId());
                            ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder2.toString());
                        } else {
                            ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Radio Detail ", ((BaseActivity) this.mContext).currentScreen);
                        }
                        playRadio(radio);
                    } else if (businessObject instanceof Track) {
                        businessObject = (Track) businessObject;
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(businessObject);
                        PlayerManager.a(this.mContext).a(com.logging.d.a().a(this.mFragment, arrayList), com.logging.d.a().a(this.mFragment, businessObject));
                        if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Position ");
                            stringBuilder2.append(this.mPosition);
                            stringBuilder2.append(" - Track - ");
                            stringBuilder2.append(businessObject.getBusinessObjId());
                            ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder2.toString());
                        } else {
                            baseActivity = (BaseActivity) this.mContext;
                            str = ((BaseActivity) this.mContext).currentScreen;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Track Detail  : ");
                            stringBuilder.append(businessObject.getEnglishName());
                            baseActivity.sendGAEvent(str, stringBuilder.toString(), ((BaseActivity) this.mContext).currentScreen);
                        }
                        PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
                        ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
                    } else if (businessObject instanceof Artist) {
                        Artist artist = (Artist) businessObject;
                        if (this.mFragment.getParentFragment() instanceof RevampedDetailListing) {
                            BusinessObject s = ((RevampedDetailListing) this.mFragment.getParentFragment()).s();
                            if (s instanceof Artist) {
                                an.a().a("click", "ac", s.getBusinessObjId(), ((RevampedDetailListing) this.mFragment.getParentFragment()).c, artist.getBusinessObjId(), artist.getName(), "", "");
                            }
                        }
                        this.mListingComponents = Constants.a("", artist.isLocalMedia());
                        this.mAppState.setListingComponents(this.mListingComponents);
                        ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Artist Detail", ((BaseActivity) this.mContext).currentScreen);
                        populateArtistListing(artist);
                    }
                }
            }
        }
    }

    private void playRadio(final Radio radio) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(getContext().getString(R.string.THIS_RADIO));
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
                                NewGenericItemView.this.playRadio(radio);
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
            if (type.equals(d.c) || type.equals(d.d)) {
                radio2.setName(name);
                radio2.setLanguage(radio.getLanguage());
                radio2.setArtwork(radio.getArtwork());
                radio2.setUrlManager(radio.getUrlManager());
                radio2.setFavoriteCount(radio.getFavorite_count());
                radio2.setBusinessObjType(BusinessObjectType.Radios);
                radio2.setBusinessObjId(businessObjId);
                radio2.setType(type);
            }
            if (radio.getType().equals(d.c)) {
                ad.a(this.mContext).a((BusinessObject) radio);
            } else {
                ad.a(this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", radio.getBusinessObjId()).replace("<radio_type>", radio.getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), (BusinessObject) radio);
            }
            this.mListingComponents = Constants.a((Radio) radio2);
            this.mListingComponents.a(radio2);
            populateRadioListing(radio2);
        }
    }

    /* JADX WARNING: Missing block: B:19:0x0054, code skipped:
            if (r2.getEntityType().equals("TR") == false) goto L_0x0057;
     */
    private void initFavoriteDownload(android.view.View r11, com.gaana.models.BusinessObject r12) {
        /*
        r10 = this;
        r0 = 2131296971; // 0x7f0902cb float:1.8211874E38 double:1.0530006145E-314;
        r0 = r11.findViewById(r0);
        r0 = (android.widget.ProgressBar) r0;
        if (r0 != 0) goto L_0x000c;
    L_0x000b:
        return;
    L_0x000c:
        r1 = 8;
        r0.setVisibility(r1);
        r2 = 2131297273; // 0x7f0903f9 float:1.8212486E38 double:1.0530007637E-314;
        r11 = r11.findViewById(r2);
        r11 = (android.widget.ImageView) r11;
        if (r11 == 0) goto L_0x001f;
    L_0x001c:
        r11.setOnClickListener(r10);
    L_0x001f:
        r2 = r12 instanceof com.gaana.models.Playlists.Playlist;
        if (r2 != 0) goto L_0x005c;
    L_0x0023:
        r2 = r12 instanceof com.gaana.models.Albums.Album;
        if (r2 != 0) goto L_0x005c;
    L_0x0027:
        r2 = r12 instanceof com.gaana.models.Tracks.Track;
        if (r2 != 0) goto L_0x005c;
    L_0x002b:
        r2 = r12 instanceof com.gaana.models.Item;
        if (r2 == 0) goto L_0x0057;
    L_0x002f:
        r2 = r12;
        r2 = (com.gaana.models.Item) r2;
        r3 = r2.getEntityType();
        r4 = "PL";
        r3 = r3.equals(r4);
        if (r3 != 0) goto L_0x005c;
    L_0x003e:
        r3 = r2.getEntityType();
        r4 = "AL";
        r3 = r3.equals(r4);
        if (r3 != 0) goto L_0x005c;
    L_0x004a:
        r2 = r2.getEntityType();
        r3 = "TR";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0056:
        goto L_0x005c;
    L_0x0057:
        r11.setVisibility(r1);
        goto L_0x0190;
    L_0x005c:
        r2 = 0;
        r11.setVisibility(r2);
        r11.setTag(r12);
        r11.setOnClickListener(r10);
        r3 = r12 instanceof com.gaana.models.Item;
        if (r3 == 0) goto L_0x0072;
    L_0x006a:
        r4 = r12;
        r4 = (com.gaana.models.Item) r4;
        r4 = r4.getEntityId();
        goto L_0x0076;
    L_0x0072:
        r4 = r12.getBusinessObjId();
    L_0x0076:
        r5 = 13;
        r6 = 2130968944; // 0x7f040170 float:1.7546556E38 double:1.0528385476E-314;
        r7 = 1;
        r8 = -1;
        if (r4 == 0) goto L_0x0172;
    L_0x007f:
        r9 = r12 instanceof com.gaana.models.Tracks.Track;
        if (r9 != 0) goto L_0x00a2;
    L_0x0083:
        if (r3 == 0) goto L_0x0095;
    L_0x0085:
        r3 = r12;
        r3 = (com.gaana.models.Item) r3;
        r3 = r3.getEntityType();
        r9 = "TR";
        r3 = r3.equals(r9);
        if (r3 == 0) goto L_0x0095;
    L_0x0094:
        goto L_0x00a2;
    L_0x0095:
        r3 = com.managers.DownloadManager.c();
        r4 = java.lang.Integer.parseInt(r4);
        r3 = r3.h(r4);
        goto L_0x00ae;
    L_0x00a2:
        r3 = com.managers.DownloadManager.c();
        r4 = java.lang.Integer.parseInt(r4);
        r3 = r3.e(r4);
    L_0x00ae:
        r4 = com.managers.DownloadManager.DownloadStatus.DOWNLOADED;
        if (r3 != r4) goto L_0x00e6;
    L_0x00b2:
        r0 = com.managers.ap.a();
        r0 = r0.k();
        if (r0 == 0) goto L_0x00de;
    L_0x00bc:
        r12 = com.utilities.Util.a(r12);
        if (r12 != 0) goto L_0x00de;
    L_0x00c2:
        r12 = r10.mContext;
        r0 = com.gaana.R.styleable.VectorDrawables;
        r12 = r12.obtainStyledAttributes(r0);
        r0 = 90;
        r0 = r12.getResourceId(r0, r8);
        r1 = r10.mContext;
        r0 = android.support.v4.content.ContextCompat.getDrawable(r1, r0);
        r12.recycle();
        r11.setImageDrawable(r0);
        goto L_0x0190;
    L_0x00de:
        r12 = 2131232088; // 0x7f080558 float:1.8080275E38 double:1.052968558E-314;
        r11.setImageResource(r12);
        goto L_0x0190;
    L_0x00e6:
        r12 = com.managers.DownloadManager.DownloadStatus.QUEUED;
        r4 = 2131232096; // 0x7f080560 float:1.8080292E38 double:1.052968562E-314;
        if (r3 != r12) goto L_0x00f2;
    L_0x00ed:
        r11.setImageResource(r4);
        goto L_0x0190;
    L_0x00f2:
        r12 = com.managers.DownloadManager.DownloadStatus.PAUSED;
        if (r3 != r12) goto L_0x0116;
    L_0x00f6:
        r12 = new int[r7];
        r12[r2] = r6;
        r12 = r10.mContext;
        r0 = com.gaana.R.styleable.VectorDrawables;
        r12 = r12.obtainStyledAttributes(r0);
        r0 = r10.getContext();
        r1 = r12.getResourceId(r5, r8);
        r0 = android.support.v4.content.ContextCompat.getDrawable(r0, r1);
        r11.setImageDrawable(r0);
        r12.recycle();
        goto L_0x0190;
    L_0x0116:
        r12 = com.managers.DownloadManager.DownloadStatus.DOWNLOADING;
        if (r3 != r12) goto L_0x0132;
    L_0x011a:
        r12 = com.managers.DownloadManager.c();
        r12 = r12.w();
        if (r12 == 0) goto L_0x012b;
    L_0x0124:
        r11.setVisibility(r1);
        r0.setVisibility(r2);
        goto L_0x0190;
    L_0x012b:
        r11.setVisibility(r2);
        r11.setImageResource(r4);
        goto L_0x0190;
    L_0x0132:
        r12 = com.managers.DownloadManager.DownloadStatus.TRIED_BUT_FAILED;
        if (r3 != r12) goto L_0x0153;
    L_0x0136:
        r12 = r10.mContext;
        r0 = com.gaana.R.styleable.VectorDrawables;
        r12 = r12.obtainStyledAttributes(r0);
        r0 = r10.getContext();
        r1 = 91;
        r1 = r12.getResourceId(r1, r8);
        r0 = android.support.v4.content.ContextCompat.getDrawable(r0, r1);
        r12.recycle();
        r11.setImageDrawable(r0);
        goto L_0x0190;
    L_0x0153:
        r12 = new int[r7];
        r12[r2] = r6;
        r12 = r10.mContext;
        r0 = com.gaana.R.styleable.VectorDrawables;
        r12 = r12.obtainStyledAttributes(r0);
        r0 = r10.getContext();
        r1 = r12.getResourceId(r5, r8);
        r0 = android.support.v4.content.ContextCompat.getDrawable(r0, r1);
        r11.setImageDrawable(r0);
        r12.recycle();
        goto L_0x0190;
    L_0x0172:
        r12 = new int[r7];
        r12[r2] = r6;
        r12 = r10.mContext;
        r0 = com.gaana.R.styleable.VectorDrawables;
        r12 = r12.obtainStyledAttributes(r0);
        r0 = r10.getContext();
        r1 = r12.getResourceId(r5, r8);
        r0 = android.support.v4.content.ContextCompat.getDrawable(r0, r1);
        r11.setImageDrawable(r0);
        r12.recycle();
    L_0x0190:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.item.NewGenericItemView.initFavoriteDownload(android.view.View, com.gaana.models.BusinessObject):void");
    }
}
