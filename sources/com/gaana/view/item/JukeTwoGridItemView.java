package com.gaana.view.item;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.c.c;
import com.constants.c.d;
import com.fragments.BaseGaanaFragment;
import com.fragments.DiscoverDetailFragment;
import com.fragments.PartyFragment;
import com.fragments.RevampedDetailListing;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.juke.JukePartyFragment;
import com.gaana.juke.JukePlaylist;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.DiscoverTags.DiscoverTag;
import com.gaana.models.Item;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.BaseItemView.RecommendedItemHolder;
import com.gaana.view.item.BaseItemView.TwoRecommendedItemHolder;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.DownloadManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.aj;
import com.managers.ap;
import com.managers.q;
import com.managers.u;
import com.services.l.v;
import com.utilities.Util;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;

public class JukeTwoGridItemView extends BaseItemView implements OnClickListener {
    private String mGASectionName = "";
    private int mLayoutHomeResourceId = R.layout.grid_two_item_recommended;
    private int mLayoutResourceId = R.layout.view_recommended_item;
    private int mPosition = -1;
    private v onClickItemUpdateListener;

    public JukeTwoGridItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
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

    public void onClick(View view) {
        if (view.getId() != R.id.create_playlist) {
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
                    } else if (entityType.equals(c.a)) {
                        businessObject = (Playlist) populatePlaylistClicked(item);
                    } else if (entityType.equals(c.b)) {
                        businessObject = (Album) populateAlbumClicked(item);
                    } else if (entityType.equals(d.c) || entityType.equals(d.d)) {
                        businessObject = (Radio) populateRadioClicked(item);
                        if (this.mFragment != null && (this.mFragment instanceof RevampedDetailListing)) {
                            ((RevampedDetailListing) this.mFragment).a("Similar Radio", false);
                        }
                    } else if (entityType.equals(c.c)) {
                        businessObject = (Track) populateTrackClicked(item);
                    } else if (entityType.equals(c.d)) {
                        businessObject = (Artist) populateArtistClicked(item);
                    } else if (entityType.equals(c.f)) {
                        businessObject = (YouTubeVideo) populateVideoClicked(item);
                    } else if (entityType.equals(c.g)) {
                        businessObject = (DiscoverTag) populateDiscoverTagClicked(item);
                    } else if (entityType.equals(c.i)) {
                        if ("1".equalsIgnoreCase(item.getLocationAvailability()) && "0".equalsIgnoreCase(item.getDeviceAvailability())) {
                            ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
                            return;
                        } else if ("0".equalsIgnoreCase(item.getLocationAvailability()) && "1".equalsIgnoreCase(item.getDeviceAvailability())) {
                            ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
                            return;
                        }
                    } else if (entityType.equalsIgnoreCase(c.j)) {
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
                    String stringBuilder2;
                    StringBuilder stringBuilder3;
                    if ((businessObject instanceof DiscoverTag) || view.getId() == R.id.discoverTagImg) {
                        if (this.mAppState.isAppInOfflineMode()) {
                            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_item));
                        } else if (Util.j(this.mContext)) {
                            BaseActivity baseActivity = (BaseActivity) this.mContext;
                            String str = ((BaseActivity) this.mContext).currentScreen;
                            StringBuilder stringBuilder4 = new StringBuilder();
                            DiscoverTag discoverTag = (DiscoverTag) businessObject;
                            stringBuilder4.append(discoverTag.getEnglishName());
                            stringBuilder4.append(" Detail ");
                            stringBuilder2 = stringBuilder4.toString();
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(((BaseActivity) this.mContext).currentScreen);
                            stringBuilder3.append(" - Discover - ");
                            stringBuilder3.append(discoverTag.getEnglishName());
                            baseActivity.sendGAEvent(str, stringBuilder2, stringBuilder3.toString());
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
                    } else if (businessObject instanceof Playlist) {
                        BusinessObject businessObject2 = (Playlist) businessObject;
                        if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().b(businessObject2).booleanValue()) {
                            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_playlist));
                        } else if (!Util.j(this.mContext) && !DownloadManager.c().b(businessObject2).booleanValue()) {
                            ap.a().f(this.mContext);
                        } else if ((this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) && !ap.a().a(businessObject2, null)) {
                            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.toast_subscription_expired));
                        } else {
                            if (((BaseActivity) this.mContext).currentScreen.startsWith("Browse")) {
                                StringBuilder stringBuilder5 = new StringBuilder();
                                stringBuilder5.append("Position ");
                                stringBuilder5.append(this.mPosition);
                                stringBuilder5.append(" - Playlist - ");
                                stringBuilder5.append(businessObject2.getBusinessObjId());
                                ((BaseActivity) this.mContext).sendGAEvent("Browse", "moreplaylists&radios click ", stringBuilder5.toString());
                            } else {
                                BaseActivity baseActivity2 = (BaseActivity) this.mContext;
                                stringBuilder2 = ((BaseActivity) this.mContext).currentScreen;
                                stringBuilder3 = new StringBuilder();
                                stringBuilder3.append("Playlist Detail  : ");
                                stringBuilder3.append(businessObject2.getEnglishName());
                                baseActivity2.sendGAEvent(stringBuilder2, stringBuilder3.toString(), ((BaseActivity) this.mContext).currentScreen);
                            }
                            if (this.mFragment instanceof PartyFragment) {
                                af.a(this.mContext, this.mFragment).a((int) R.id.jukePlaylistMenu, businessObject);
                            } else {
                                this.mListingComponents = Constants.e();
                                this.mAppState.setListingComponents(this.mListingComponents);
                                if (businessObject2.isGaanaSpecial()) {
                                    populateSpecialGaanaListing(businessObject2);
                                } else {
                                    populatePlaylistListing(businessObject2);
                                }
                            }
                        }
                    }
                }
            }
        } else if (Util.j(this.mContext)) {
            JukePlaylist jukePlaylist = new JukePlaylist();
            jukePlaylist.setName(this.mContext.getResources().getString(R.string.opt_my_party));
            u.a().a("PartyHub", "Create_Suggestion_Tap", "New");
            ((GaanaActivity) this.mContext).displayFragment(JukePartyFragment.newInstance(jukePlaylist, 0, "", false));
        } else {
            ap.a().f(this.mContext);
        }
    }

    public View initView(TwoRecommendedItemHolder twoRecommendedItemHolder, BusinessObject businessObject, ViewGroup viewGroup, boolean z) {
        twoRecommendedItemHolder.itemView.setClickable(false);
        if (businessObject != null) {
            ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
            int size = arrListBusinessObj.size();
            RecommendedItemHolder recommendedItemHolder = twoRecommendedItemHolder.firstHolder;
            recommendedItemHolder.itemView.setVisibility(0);
            getPoplatedViewGrid(recommendedItemHolder, (BusinessObject) arrListBusinessObj.get(0), viewGroup, z, twoRecommendedItemHolder);
            RecommendedItemHolder recommendedItemHolder2 = twoRecommendedItemHolder.secondHolder;
            if (size == 2) {
                recommendedItemHolder2.itemView.setVisibility(0);
                getPoplatedViewGrid(recommendedItemHolder2, (BusinessObject) arrListBusinessObj.get(1), viewGroup, z, twoRecommendedItemHolder);
            } else {
                recommendedItemHolder2.itemView.setVisibility(4);
            }
        }
        return twoRecommendedItemHolder.itemView;
    }

    public void getPoplatedViewGrid(RecommendedItemHolder recommendedItemHolder, BusinessObject businessObject, ViewGroup viewGroup, boolean z, TwoRecommendedItemHolder twoRecommendedItemHolder) {
        View view = recommendedItemHolder.itemView;
        CrossFadeImageView crossFadeImageView = recommendedItemHolder.imageViewThumb;
        TextView textView = recommendedItemHolder.tvName;
        view.setTag(businessObject);
        crossFadeImageView.setTag(businessObject);
        String artwork;
        if (businessObject instanceof Item) {
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
            if (item.getEntityType().equals(c.c)) {
                recommendedItemHolder.play_icon.setVisibility(0);
            } else if (item.getEntityType().equals(c.f)) {
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
        view.setOnClickListener(this);
        super.getPoplatedView(view, businessObject, viewGroup);
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
        return initView(twoRecommendedItemHolder, businessObject, viewGroup, z);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, boolean z, Boolean bool, v vVar) {
        this.onClickItemUpdateListener = vVar;
        return getPoplatedView(viewHolder, businessObject, viewGroup, z, bool);
    }

    public boolean performClick() {
        return super.performClick();
    }
}
