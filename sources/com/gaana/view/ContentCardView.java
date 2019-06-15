package com.gaana.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.android.volley.Request.Priority;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.constants.Constants.ACTION_TYPE;
import com.constants.c.c;
import com.dynamicview.DynamicOccasionFragment;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.fragments.DiscoverDetailFragment;
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
import com.gaana.models.UserRecentActivity;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import com.models.ListingComponents;
import com.models.PlayerTrack;
import com.services.d;
import com.services.l.af;
import com.services.l.ag;
import com.services.l.r;
import com.services.l.s;
import com.utilities.Util;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;

public class ContentCardView extends BaseItemView {
    private a mDynamicView;
    private ListingComponents mListingComponents;

    public static class ContentCardViewHolder extends ViewHolder {
        public LinearLayout llImgParentLayout;
        public LinearLayout llParentLayout;

        public ContentCardViewHolder(View view, boolean z) {
            super(view);
            if (z) {
                this.llImgParentLayout = (LinearLayout) view.findViewById(R.id.ll_img_parent);
                this.llParentLayout = (LinearLayout) view.findViewById(R.id.llParentLayout);
            }
        }
    }

    public ContentCardView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mDynamicView = aVar;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ContentCardViewHolder(getNewView(R.layout.image_card_view, viewGroup), true);
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        downlaodBitmap(viewHolder, i);
        return null;
    }

    private void downlaodBitmap(final ViewHolder viewHolder, int i) {
        viewHolder.itemView.setOnClickListener(this);
        viewHolder.itemView.setTag(Integer.valueOf(i));
        i.a().a(this.mDynamicView.d(), new r() {
            public void onErrorResponse(VolleyError volleyError) {
            }

            public void onSuccessfulResponse(Bitmap bitmap) {
                CrossFadeImageView crossFadeImageView = new CrossFadeImageView(ContentCardView.this.mContext);
                crossFadeImageView.setAdjustViewBounds(true);
                crossFadeImageView.setShowLoadingState(true);
                crossFadeImageView.setScaleType(ScaleType.CENTER_CROP);
                crossFadeImageView.setPadding(ContentCardView.this.mContext.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin), 0, ContentCardView.this.mContext.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin), 0);
                int cardHeight = ImageCardView.getCardHeight(ContentCardView.this.mContext, ContentCardView.this.mDynamicView.e());
                int b = d.a().b();
                crossFadeImageView.setLayoutParams(new LayoutParams(b, cardHeight));
                crossFadeImageView.setImageBitmap(bitmap);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) ((ContentCardViewHolder) viewHolder).itemView.getLayoutParams();
                layoutParams.width = b;
                layoutParams.bottomMargin = (int) ContentCardView.this.mContext.getResources().getDimension(R.dimen.bw_section_vert_padding_half);
                layoutParams.topMargin = (int) ContentCardView.this.mContext.getResources().getDimension(R.dimen.bw_section_vert_padding_half);
                ((ContentCardViewHolder) viewHolder).llImgParentLayout.setLayoutParams(layoutParams);
                ((ContentCardViewHolder) viewHolder).llImgParentLayout.removeAllViews();
                ((ContentCardViewHolder) viewHolder).llImgParentLayout.setBackgroundColor(0);
                if (ContentCardView.this.mDynamicView.f() != ACTION_TYPE.FB_LOGIN_CARD.getNumVal()) {
                    ((ContentCardViewHolder) viewHolder).llImgParentLayout.addView(crossFadeImageView);
                } else if (GaanaApplication.getInstance().getCurrentUser() == null || !GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                    ((ContentCardViewHolder) viewHolder).llImgParentLayout.addView(crossFadeImageView);
                } else {
                    RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) ((ContentCardViewHolder) viewHolder).itemView.getLayoutParams();
                    layoutParams2.height = 0;
                    layoutParams2.width = 0;
                    layoutParams2.topMargin = 0;
                    layoutParams2.bottomMargin = 0;
                    ((ContentCardViewHolder) viewHolder).llImgParentLayout.setLayoutParams(layoutParams);
                }
            }
        });
    }

    public void onClick(View view) {
        if (this.mDynamicView != null) {
            URLManager uRLManager = new URLManager();
            uRLManager.a(this.mDynamicView.l());
            uRLManager.a(BusinessObjectType.GenericItems);
            uRLManager.a(UserRecentActivity.class);
            uRLManager.c(0);
            uRLManager.b(Boolean.valueOf(true));
            uRLManager.a(Priority.IMMEDIATE);
            uRLManager.i(false);
            i.a().a(new af() {
                public void onRetreivalComplete(Object obj) {
                    ArrayList entities = ((UserRecentActivity) obj).getEntities();
                    if (obj != null && entities != null && entities.size() > 0 && entities.get(0) != null && (entities.get(0) instanceof Item)) {
                        ContentCardView.this.handleClick(entities.get(0));
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    Log.e("Error", "businessObject");
                }
            }, uRLManager);
        }
    }

    private void handleClick(final Object obj) {
        StringBuilder stringBuilder;
        Item item = (Item) obj;
        String entityType = item.getEntityType();
        if (this.mFragment != null && (this.mFragment instanceof DynamicOccasionFragment)) {
            GaanaApplication gaanaApplication = this.mAppState;
            stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
            stringBuilder.append("_");
            stringBuilder.append(this.mDynamicView.p());
            gaanaApplication.setPlayoutSectionName(stringBuilder.toString());
            u a = u.a();
            String str = ((BaseActivity) this.mContext).currentScreen;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.mDynamicView.p());
            stringBuilder2.append("_Click");
            a.b(str, stringBuilder2.toString());
        }
        BusinessObject businessObject;
        if (entityType.equals(c.a)) {
            businessObject = (Playlist) Util.b(item);
            this.mListingComponents = Constants.e();
            this.mListingComponents.a(businessObject);
            if (businessObject.isGaanaSpecial()) {
                populateSpecialGaanaListing(businessObject);
            } else {
                populatePlaylistListing(businessObject);
            }
        } else if (entityType.equals(c.b)) {
            businessObject = (Album) Util.c(item);
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
            if (TextUtils.isEmpty(businessObject.getChannelPageAdCode())) {
                Constants.i = false;
                Constants.j = "";
            } else {
                Constants.i = true;
                Constants.j = businessObject.getChannelPageAdCode();
            }
            populateAlbumListing(businessObject);
        } else if (entityType.equals(com.constants.c.d.d) || entityType.equals(com.constants.c.d.c)) {
            if (Constants.cY) {
                JukeSessionManager.getErrorDialog(this.mContext, 0, new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        JukeSessionManager.getInstance().stopJukeSession(new s() {
                            public void onErrorResponse(BusinessObject businessObject) {
                            }

                            public void onRetreivalComplete(BusinessObject businessObject) {
                                if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                                    ContentCardView.this.handleClick(obj);
                                }
                            }
                        });
                    }
                });
                return;
            }
            businessObject = (Radio) Util.d(item);
            if (this.mAppState.isAppInOfflineMode()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_radio));
            } else if (Util.j(this.mContext)) {
                if (businessObject.getType().equals(com.constants.c.d.c)) {
                    ad.a(this.mContext).a(businessObject);
                } else {
                    ad.a(this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", businessObject.getBusinessObjId()).replace("<radio_type>", businessObject.getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), businessObject);
                }
                this.mListingComponents = Constants.a((Radio) businessObject);
                this.mListingComponents.a(businessObject);
                if (this.mDynamicView.f() != ACTION_TYPE.RADIO_WITHOUT_DETAILS.getNumVal()) {
                    populateRadioListing(businessObject);
                }
            } else {
                ap.a().f(this.mContext);
            }
        } else if (entityType.equals(c.d)) {
            Artist artist = (Artist) Util.a(item);
            this.mListingComponents = Constants.a("", artist.isLocalMedia());
            this.mAppState.setListingComponents(this.mListingComponents);
            populateArtistListing(artist);
        } else if (entityType.equals(c.c)) {
            businessObject = (Track) Util.g(item);
            if ("1".equalsIgnoreCase(businessObject.getLocationAvailability()) && "0".equalsIgnoreCase(businessObject.getDeviceAvailability())) {
                ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
            } else if ("0".equalsIgnoreCase(businessObject.getLocationAvailability()) && "1".equalsIgnoreCase(businessObject.getDeviceAvailability())) {
                ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
            } else if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().l(Integer.parseInt(businessObject.getBusinessObjId())).booleanValue()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_song));
            } else if (Util.j(this.mContext) || DownloadManager.c().l(Integer.parseInt(businessObject.getBusinessObjId())).booleanValue()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(businessObject);
                PlayerManager.a(this.mContext).a(com.logging.d.a().a(this.mFragment, arrayList), com.logging.d.a().a(this.mFragment, businessObject));
                PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
                ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
            } else {
                aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.error_msg_no_connection));
            }
        } else if (entityType.equals(c.f)) {
            YouTubeVideo youTubeVideo = (YouTubeVideo) Util.f(item);
            launchYouTubePlayer(youTubeVideo.c(), youTubeVideo.a(), youTubeVideo, 0);
        } else if (entityType.equals(c.g)) {
            DiscoverTag discoverTag = (DiscoverTag) Util.e(item);
            if (this.mAppState.isAppInOfflineMode()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This item");
            } else if (Util.j(this.mContext)) {
                BaseActivity baseActivity = (BaseActivity) this.mContext;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("OP_");
                stringBuilder3.append(((BaseActivity) this.mContext).currentScreen);
                entityType = stringBuilder3.toString();
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append(discoverTag.getEnglishName());
                stringBuilder4.append(" Detail ");
                String stringBuilder5 = stringBuilder4.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder.append(" - Discover - ");
                stringBuilder.append(discoverTag.getName());
                baseActivity.sendGAEvent(entityType, stringBuilder5, stringBuilder.toString());
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
        } else if (entityType.equals(c.i)) {
            handleOccasionEntity(item);
        } else if (entityType.equals(c.e)) {
            Util.a(Util.a(this.mContext, (ArrayList) item.getEntityInfo()), getMandatoryLogin((ArrayList) item.getEntityInfo()), getInAppWeb((ArrayList) item.getEntityInfo()), this.mContext);
        }
    }

    private String getMandatoryLogin(ArrayList<EntityInfo> arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            if (((EntityInfo) arrayList.get(i)).getKey().equalsIgnoreCase("login_flag")) {
                String obj = ((EntityInfo) arrayList.get(i)).getValue().toString();
                return obj.contains(".") ? obj.split("\\.")[0] : obj;
            } else {
                i++;
            }
        }
        return null;
    }

    private String getInAppWeb(ArrayList<EntityInfo> arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            if (((EntityInfo) arrayList.get(i)).getKey().equalsIgnoreCase("app_url_view")) {
                String obj = ((EntityInfo) arrayList.get(i)).getValue().toString();
                return obj.contains(".") ? obj.split("\\.")[0] : obj;
            } else {
                i++;
            }
        }
        return null;
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
                            str.substring(str.lastIndexOf("/") + 1, str.length());
                            u a = u.a();
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("OP_");
                            stringBuilder.append(item.getName());
                            a.b(stringBuilder.toString(), "Browse_Click");
                            com.dynamicview.c.a().a(new ag() {
                                public void onOccasionResponse() {
                                    BaseGaanaFragment dynamicOccasionFragment = new DynamicOccasionFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("OCCASION_URL", str);
                                    bundle.putString("OCCASION_REFRESH_INTERVAL", null);
                                    dynamicOccasionFragment.setArguments(bundle);
                                    ((GaanaActivity) ContentCardView.this.mContext).displayFragment(dynamicOccasionFragment);
                                }

                                public void onOccasionError() {
                                    aj.a().a(ContentCardView.this.mContext, ContentCardView.this.getResources().getString(R.string.error_download_no_internet));
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

    public void play(PlayerTrack playerTrack) {
        playerTrack.d(true);
        PlayerManager.a(this.mContext).c();
        PlayerManager.a(this.mContext).a(null, playerTrack, 999999);
        PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
        ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
    }
}
