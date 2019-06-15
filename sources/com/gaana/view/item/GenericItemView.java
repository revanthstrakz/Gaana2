package com.gaana.view.item;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.bumptech.glide.request.a.g;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.Constants;
import com.constants.Constants.VIEW_SIZE;
import com.constants.c.b;
import com.constants.c.c;
import com.constants.c.d;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.DynamicOccasionFragment;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.fragments.DiscoverDetailFragment;
import com.fragments.MoreRadioActivityFragment;
import com.fragments.PartyFragment;
import com.fragments.PreScreenFragment;
import com.fragments.SearchTabFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSeeAllFragment;
import com.gaana.juke.JukeSessionManager;
import com.gaana.models.AdsUJData;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.DiscoverTags.DiscoverTag;
import com.gaana.models.EntityInfo;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Playlists.Playlist.PlaylistSourceType;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.BaseItemView.ActivityListHolder;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.gms.ads.AdListener;
import com.library.controls.CircularImageView;
import com.library.controls.RoundedCornerImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.ColombiaManager;
import com.managers.ColombiaManager.ADSTATUS;
import com.managers.DownloadManager;
import com.managers.GaanaSearchManager;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.af;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.aq;
import com.managers.h;
import com.managers.s;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.models.PlayerTrack;
import com.services.l;
import com.services.l.ag;
import com.services.l.ba;
import com.services.l.o;
import com.til.colombia.android.internal.e;
import com.til.colombia.android.service.Item;
import com.utilities.Util;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class GenericItemView extends BaseItemView {
    private ADSTATUS adstatus;
    String brandLogoTracker;
    String brandLogoUrl;
    private boolean colombiaItemFailed;
    private boolean mItemWithoutText;
    protected boolean mLightsOn;
    private List<a> mRadioMetaViews;
    private int playlistType;
    private String sourceName;
    private String uniqueID;
    String vplDetailsUrl;
    String vplType;

    public static class TagObject {
        BusinessObject businessObject;
        String header;
        int position;

        TagObject(BusinessObject businessObject, int i, String str) {
            this.businessObject = businessObject;
            this.position = i;
            this.header = str;
        }

        public BusinessObject getBusinessObject() {
            return this.businessObject;
        }

        public int getPosition() {
            return this.position;
        }

        public String getHeader() {
            return this.header;
        }
    }

    public GenericItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.playlistType = b.a;
        this.colombiaItemFailed = false;
        this.mItemWithoutText = false;
        this.mLightsOn = false;
        this.vplDetailsUrl = "";
        this.brandLogoUrl = "";
        this.brandLogoTracker = "";
        this.vplType = "";
        this.mLayoutId = R.layout.view_item_playlist;
        ((BaseActivity) this.mContext).currentItem = "Playlist";
    }

    public GenericItemView(Context context, BaseGaanaFragment baseGaanaFragment, int i) {
        super(context, baseGaanaFragment);
        this.playlistType = b.a;
        this.colombiaItemFailed = false;
        this.mItemWithoutText = false;
        this.mLightsOn = false;
        this.vplDetailsUrl = "";
        this.brandLogoUrl = "";
        this.brandLogoTracker = "";
        this.vplType = "";
        this.mLayoutId = i;
        ((BaseActivity) this.mContext).currentItem = "Playlist";
    }

    public String getSourceType() {
        return this.sourceName;
    }

    public void setSourceName(String str) {
        this.sourceName = str;
    }

    public void setItemWithoutText(Boolean bool) {
        this.mItemWithoutText = bool.booleanValue();
    }

    public String getUniqueID() {
        return this.uniqueID;
    }

    public void setUniqueID(String str) {
        this.uniqueID = str;
    }

    public void setLightsOn(boolean z) {
        this.mLightsOn = z;
    }

    public boolean isHourlyPlaylist() {
        return this.playlistType == b.b;
    }

    private boolean shouldGetFreshAd(int i) {
        return this.adstatus == null || this.adstatus == ADSTATUS.FAILED;
    }

    public View getPoplatedAdView(long j, boolean z, int i, int i2, View view, ViewGroup viewGroup, o oVar) {
        return getPoplatedAdView(j, z, i, VIEW_SIZE.SCROLL_GENERIC.getNumVal(), i2, view, viewGroup, oVar);
    }

    public View getHorizontalScrollAd(com.dynamicview.b bVar, long j, int i, boolean z, boolean z2, View view, int i2, int i3) {
        final com.dynamicview.b bVar2 = bVar;
        final View view2 = view;
        if ((bVar2 != null && (bVar.e() == null || bVar.e() == ADSTATUS.FAILED)) || z) {
            String simpleName = this.mFragment.getClass().getSimpleName();
            this.colombiaItemFailed = false;
            bVar2.a(ADSTATUS.LOADING);
            final AdsUJData adsUJData = new AdsUJData();
            adsUJData.setSectionName(bVar.c().p());
            adsUJData.setSectionId(bVar.c().y());
            adsUJData.setAdUnitCode(String.valueOf(j));
            adsUJData.setAdType(e.V);
            if (!(bVar.c().q().equals("") || ap.a().d())) {
                an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", "start", adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
            }
            ColombiaManager.b().a(1, this.mContext, i, i2, j, view2, z2, simpleName, new ColombiaManager.a() {
                public void onItemLoaded(Item item) {
                    if (bVar2 != null) {
                        bVar2.a(ADSTATUS.LOADED);
                        bVar2.a(item);
                    }
                    view2.findViewById(R.id.llNativeAdSlot).setPadding((int) GenericItemView.this.mContext.getResources().getDimension(R.dimen.list_padding), 0, 0, 0);
                    view2.findViewById(R.id.llNativeAdSlot).setVisibility(0);
                    an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", "start", adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
                }

                public void onItemRequestFailed(Exception exception) {
                    GenericItemView.this.colombiaItemFailed = true;
                    if (bVar2 != null) {
                        bVar2.a(ADSTATUS.FAILED);
                        bVar2.a(null);
                    }
                    view2.findViewById(R.id.llNativeAdSlot).setPadding(0, 0, 0, 0);
                    view2.findViewById(R.id.llNativeAdSlot).setVisibility(8);
                }
            }, bVar.c().p());
        } else if (!(view2 == null || view2.findViewById(R.id.llNativeAdSlot) == null || view2.findViewById(R.id.llNativeAdSlot).getVisibility() == 0 || this.colombiaItemFailed || bVar2 == null || bVar.f() == null || bVar.e() != ADSTATUS.LOADED)) {
            LinearLayout linearLayout = (LinearLayout) view2.findViewById(R.id.llNativeAdSlot);
            if (bVar.f() != null) {
                com.managers.e.a().a(i, i2, i3, this.mContext, bVar.f(), z2, linearLayout, null);
                linearLayout.setPadding((int) this.mContext.getResources().getDimension(R.dimen.list_padding), 0, 0, 0);
                linearLayout.setVisibility(0);
            }
        }
        return view2;
    }

    public View getHorizontalScrollDfpAd(com.dynamicview.b bVar, String str, int i, boolean z, View view, boolean z2) {
        final com.dynamicview.b bVar2 = bVar;
        String str2 = str;
        final View view2 = view;
        if (!(bVar2 == null || str2 == null || view2 == null || view2.findViewById(R.id.llNativeAdSlot) == null || view2.findViewById(R.id.llNativeAdSlot).getVisibility() == 0)) {
            AdsUJData adsUJData = new AdsUJData();
            adsUJData.setSectionName(bVar.c().p());
            adsUJData.setSectionId(bVar.c().y());
            adsUJData.setAdUnitCode(str2);
            adsUJData.setAdType("dfp");
            if (!(bVar.c().B().equals("") || ap.a().d())) {
                an.a().e("ad", "", adsUJData.getSectionId(), "ad_load", "", "start", adsUJData.getSectionIndex(), adsUJData.getAdUnitCode());
            }
            h.a().a(this.mContext, str2, i, view2, z2, new AdListener() {
                public void onAdFailedToLoad(int i) {
                    view2.findViewById(R.id.llNativeAdSlot).setVisibility(8);
                    view2.setPadding(0, 0, 0, 0);
                    bVar2.a(null);
                    bVar2.a(ADSTATUS.FAILED);
                }

                public void onAdLoaded() {
                    bVar2.a(ADSTATUS.LOADED);
                }
            });
        }
        return view2;
    }

    public View getPoplatedAdView(long j, boolean z, int i, int i2, int i3, View view, ViewGroup viewGroup, o oVar) {
        final int i4 = i3;
        View view2 = view;
        if (shouldGetFreshAd(i4) || z) {
            String simpleName = this.mFragment.getClass().getSimpleName();
            this.colombiaItemFailed = false;
            this.adstatus = ADSTATUS.LOADING;
            final o oVar2 = oVar;
            ColombiaManager.b().a(1, this.mContext, i, i2, j, view2, false, simpleName, new ColombiaManager.a() {
                public void onItemLoaded(Item item) {
                    GenericItemView.this.adstatus = ADSTATUS.LOADED;
                    if (oVar2 != null) {
                        oVar2.notifyItemChanged(i4);
                    }
                }

                public void onItemRequestFailed(Exception exception) {
                    GenericItemView.this.colombiaItemFailed = true;
                    GenericItemView.this.adstatus = ADSTATUS.FAILED;
                }
            }, "");
        } else if (!(view2 == null || view2.findViewById(R.id.llNativeAdSlot) == null || view2.findViewById(R.id.llNativeAdSlot).getVisibility() == 0 || this.colombiaItemFailed || this.adstatus != ADSTATUS.LOADED)) {
            view2.findViewById(R.id.llNativeAdSlot).setPadding(0, 0, (int) this.mContext.getResources().getDimension(R.dimen.list_padding), 0);
            view2.findViewById(R.id.llNativeAdSlot).setVisibility(0);
        }
        return this.colombiaItemFailed ? new View(this.mContext) : view2;
    }

    public View getPopulatedBlankView(ViewHolder viewHolder) {
        PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder;
        this.mView = playlistGridHolder.itemView;
        this.mView.setTag(null);
        this.mView.setOnClickListener(null);
        playlistGridHolder.play_icon.setVisibility(4);
        playlistGridHolder.crossFadeImageView.setImageBitmap(null);
        playlistGridHolder.tvTopHeading.setVisibility(8);
        playlistGridHolder.itemView.setVisibility(4);
        return this.mView;
    }

    public View getPoplatedGenericView(int i, ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, String str) {
        return getPoplatedGenericView(i, viewHolder, businessObject, viewGroup, str, null);
    }

    public View getPoplatedGenericView(int i, ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, String str, a aVar) {
        boolean z;
        String str2;
        BusinessObject businessObject2 = businessObject;
        final PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder;
        this.mView = playlistGridHolder.itemView;
        if (this.mView.findViewById(R.id.download_track_item_view) != null) {
            this.mView.findViewById(R.id.download_track_item_view).setVisibility(8);
        }
        TagObject tagObject = new TagObject(businessObject2, i, str);
        this.mView.setTag(tagObject);
        this.mView.setOnClickListener(this);
        com.gaana.models.Item item = (com.gaana.models.Item) businessObject2;
        String artwork = item.getArtwork();
        if (!(Constants.cN || artwork == null)) {
            artwork = artwork.replace("80x80", "175x175");
        }
        if (artwork != null) {
            artwork = Util.g(this.mContext, artwork);
        }
        int i2 = (item.getEntityType().equals(c.f) || ((item.getEntityType().equals(c.b) || item.getEntityType().equals(c.a)) && Constants.aW && !(playlistGridHolder.crossFadeImageView instanceof CircularImageView))) ? 1 : 0;
        int i3 = (item.getEntityType().equals(d.d) || item.getEntityType().equals(d.c)) ? 1 : 0;
        if (i2 != 0) {
            if (item.getEntityType().equals(c.f)) {
                playlistGridHolder.play_icon.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.vector_ic_video_indicator));
            } else {
                playlistGridHolder.play_icon.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.ic_artwork_play));
            }
            playlistGridHolder.play_icon.setVisibility(0);
        } else {
            playlistGridHolder.play_icon.setVisibility(4);
        }
        if (aVar != null && aVar.C() && playlistGridHolder.shareIcon != null) {
            playlistGridHolder.shareIcon.setVisibility(0);
            playlistGridHolder.shareIcon.setTag(tagObject);
            playlistGridHolder.shareIcon.setOnClickListener(this);
        } else if (!(aVar == null || aVar.C() || playlistGridHolder.shareIcon == null)) {
            playlistGridHolder.shareIcon.setVisibility(4);
        }
        if (playlistGridHolder.crossFadeImageView instanceof RoundedCornerImageView) {
            ((RoundedCornerImageView) playlistGridHolder.crossFadeImageView).setHasGradient(i3 ^ 1);
        }
        if (Constants.aW) {
            playlistGridHolder.play_icon.setOnClickListener(this);
            playlistGridHolder.play_icon.setTag(tagObject);
        }
        if (playlistGridHolder.mImgIndictor != null) {
            if (item.getEntityType().equals(c.a)) {
                playlistGridHolder.mImgIndictor.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.vector_ic_playlist_indicator));
                playlistGridHolder.mImgIndictor.setVisibility(0);
            } else if (item.getEntityType().equals(c.b)) {
                playlistGridHolder.mImgIndictor.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.vector_ic_playlist_indicator));
                playlistGridHolder.mImgIndictor.setVisibility(0);
            } else if (item.getEntityType().equals(c.c)) {
                playlistGridHolder.mImgIndictor.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.vector_ic_tracks_indicator));
                playlistGridHolder.mImgIndictor.setVisibility(0);
            } else {
                playlistGridHolder.mImgIndictor.setVisibility(4);
            }
        }
        if ((item.getEntityType().equals(c.g) || item.getEntityType().equals(c.i) || item.getEntityType().equals(c.m)) && playlistGridHolder.tvSectionTitle != null) {
            playlistGridHolder.tvSectionTitle.setVisibility(0);
            playlistGridHolder.tvSectionTitle.setText(item.getName());
            playlistGridHolder.tvSectionTitle.setTypeface(null, 1);
        } else if (playlistGridHolder.tvSectionTitle != null) {
            playlistGridHolder.tvSectionTitle.setVisibility(8);
        }
        List entityInfo = item.getEntityInfo();
        i2 = (item.getEntityType().equals(c.g) || item.getEntityType().equals(c.i) || item.getEntityType().equals(c.m)) ? 1 : 0;
        if (!(playlistGridHolder.tvSectionTitle == null || i2 == 0)) {
            playlistGridHolder.tvSectionTitle.setTypeface(Util.h(this.mContext));
            playlistGridHolder.tvSectionTitle.setTextColor(getResources().getColor(R.color.first_line_color));
            playlistGridHolder.tvSectionTitle.setTextSize(2, 14.0f);
        }
        if (playlistGridHolder.tvSectionTitle != null && (item.getEntityType().equals(c.g) || item.getEntityType().equals(c.i))) {
            playlistGridHolder.tvSectionTitle.setAllCaps(true);
        }
        if (entityInfo != null) {
            i3 = entityInfo.size();
            int i4 = 0;
            z = i4;
            while (i4 < i3) {
                if (!((EntityInfo) entityInfo.get(i4)).getKey().equals("parental_warning")) {
                    str2 = artwork;
                    if (i2 != 0 && ((EntityInfo) entityInfo.get(i4)).getKey().equals("bg_colour")) {
                        Object value = ((EntityInfo) entityInfo.get(i4)).getValue();
                        if (value != null) {
                            String valueOf = String.valueOf(value);
                            if (!TextUtils.isEmpty(valueOf)) {
                                GradientDrawable gradientDrawable = new GradientDrawable();
                                gradientDrawable.setCornerRadius((float) Util.b(4));
                                gradientDrawable.setColor(Color.parseColor(valueOf));
                                playlistGridHolder.crossFadeImageView.setImageDrawable(gradientDrawable);
                            }
                        }
                    } else if (i2 != 0 && ((EntityInfo) entityInfo.get(i4)).getKey().equals(InMobiNetworkValues.ICON)) {
                        com.bumptech.glide.e.c(this.mContext).asBitmap().load(((EntityInfo) entityInfo.get(i4)).getValue()).into(new g() {
                            public void onResourceReady(Object obj, com.bumptech.glide.request.b.d dVar) {
                                playlistGridHolder.tvSectionTitle.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(GenericItemView.this.getResources(), (Bitmap) obj), null, null, null);
                                playlistGridHolder.tvSectionTitle.setCompoundDrawablePadding(7);
                            }

                            public void onLoadFailed(@Nullable Drawable drawable) {
                                super.onLoadFailed(drawable);
                            }
                        });
                    }
                } else if (((EntityInfo) entityInfo.get(i4)).getValue() instanceof Double) {
                    str2 = artwork;
                    z = Double.compare(((Double) ((EntityInfo) entityInfo.get(i4)).getValue()).doubleValue(), 1.0d) == 0;
                } else {
                    str2 = artwork;
                    z = ((EntityInfo) entityInfo.get(i4)).getValue().equals("1");
                }
                i4++;
                artwork = str2;
            }
            str2 = artwork;
        } else {
            str2 = artwork;
            z = false;
        }
        if (item.getEntityType().equals(c.j)) {
            if (playlistGridHolder.tvTopHeadingMix != null) {
                playlistGridHolder.tvTopHeadingMix.setTypeface(Util.i(this.mContext));
                playlistGridHolder.tvTopHeadingMix.setVisibility(0);
                String a = Util.a(item, "vpl_title");
                if (!TextUtils.isEmpty(a)) {
                    playlistGridHolder.tvTopHeadingMix.setText(a);
                }
            }
            com.managers.e.a().a(playlistGridHolder.itemView, this.mContext, item);
        } else if (playlistGridHolder.tvTopHeadingMix != null) {
            playlistGridHolder.tvTopHeadingMix.setVisibility(8);
        }
        if (playlistGridHolder.mTxtPlayCount != null) {
            playlistGridHolder.mTxtPlayCount.setVisibility(4);
        }
        if (Constants.cN) {
            artwork = str2;
            if (artwork.contains("175x175")) {
                artwork = artwork.replace("175x175", "80x80");
            }
        } else {
            artwork = str2;
        }
        if (i2 == 0) {
            if (aVar != null && aVar.e() == VIEW_SIZE.GRID_LARGE.getNumVal()) {
                artwork = item.getArtworkSpecific();
            }
            playlistGridHolder.crossFadeImageView.bindImage(businessObject2, artwork, this.mAppState.isAppInOfflineMode());
            playlistGridHolder.crossFadeImageView.setScaleType(ScaleType.FIT_XY);
        }
        if (this.mItemWithoutText) {
            playlistGridHolder.tvTopHeading.setVisibility(8);
        } else {
            playlistGridHolder.tvTopHeading.setVisibility(0);
            if (z) {
                Util.a(playlistGridHolder.tvTopHeading, item.getName());
            } else {
                playlistGridHolder.tvTopHeading.setText(item.getName());
            }
        }
        s.a().a(playlistGridHolder.imgLightOverlay, this.mLightsOn);
        playlistGridHolder.itemView.setVisibility(0);
        return this.mView;
    }

    public View getEmptyView(int i, ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, String str) {
        if (viewHolder instanceof PlaylistGridHolder) {
            PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder;
            this.mView = playlistGridHolder.itemView;
            this.mView.setOnClickListener(null);
            playlistGridHolder.play_icon.setVisibility(4);
            playlistGridHolder.crossFadeImageView.setScaleType(ScaleType.CENTER_CROP);
            playlistGridHolder.tvTopHeading.setText(" ");
        }
        return viewHolder.itemView;
    }

    public View getPoplatedGenericUserActivityView(int i, ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, String str) {
        ActivityListHolder activityListHolder = (ActivityListHolder) viewHolder;
        this.mView = activityListHolder.itemView;
        CharSequence charSequence = "";
        this.mView.setTag(new TagObject(businessObject, i, str));
        this.mView.setOnClickListener(this);
        com.gaana.models.Item item = (com.gaana.models.Item) businessObject;
        String artwork = item.getArtwork();
        if (!(Constants.cN || artwork == null)) {
            artwork = artwork.replace("80x80", "175x175");
        }
        activityListHolder.crossFadeImageView.bindImage(artwork, this.mAppState.isAppInOfflineMode());
        activityListHolder.crossFadeImageView.setScaleType(ScaleType.FIT_XY);
        activityListHolder.listItemName.setText(item.getName());
        if (item.getEntityType().equals(c.a)) {
            charSequence = getResources().getString(R.string.opt_playlists);
        } else if (item.getEntityType().equals(c.c)) {
            charSequence = getResources().getString(R.string.song_text).trim();
        } else if (item.getEntityType().equals(c.b)) {
            charSequence = getResources().getString(R.string.album_text);
        } else if (item.getEntityType().equals(d.d) || item.getEntityType().equals(d.c)) {
            charSequence = getResources().getString(R.string.opt_radio);
        }
        activityListHolder.listItemDesc.setText(charSequence);
        return this.mView;
    }

    public void onClick(View view) {
        final View view2 = view;
        super.onClick(view);
        int i = view.getId() == R.id.play_icon ? 1 : false;
        int i2 = view.getId() == R.id.share_icon ? 1 : false;
        TagObject tagObject = (TagObject) view.getTag();
        this.mBusinessObject = tagObject.getBusinessObject();
        int position = tagObject.getPosition() + 1;
        String header = tagObject.getHeader();
        if (this.mBusinessObject instanceof com.gaana.models.Item) {
            StringBuilder stringBuilder;
            com.gaana.models.Item item = (com.gaana.models.Item) this.mBusinessObject;
            String entityType = ((com.gaana.models.Item) this.mBusinessObject).getEntityType();
            if (this.mFragment instanceof PreScreenFragment) {
                ((PreScreenFragment) this.mFragment).a(getSectionName());
            }
            BaseGaanaFragment songParallexListingFragment;
            StringBuilder stringBuilder2;
            String stringBuilder3;
            BusinessObject businessObject;
            StringBuilder stringBuilder4;
            String stringBuilder5;
            String stringBuilder6;
            StringBuilder stringBuilder7;
            String stringBuilder8;
            BaseActivity baseActivity;
            if (entityType.equalsIgnoreCase(c.j)) {
                if (TextUtils.isEmpty(this.vplDetailsUrl)) {
                    List entityInfo = item.getEntityInfo();
                    if (entityInfo != null) {
                        i = entityInfo.size();
                        for (i2 = 0; i2 < i; i2++) {
                            if (((EntityInfo) entityInfo.get(i2)).getKey().equals("url_logo_banner")) {
                                this.brandLogoUrl = ((EntityInfo) entityInfo.get(i2)).getValue().toString();
                            } else if (((EntityInfo) entityInfo.get(i2)).getKey().equals("tracker_adcode_dfp_viewall")) {
                                this.brandLogoTracker = ((EntityInfo) entityInfo.get(i2)).getValue().toString();
                            } else if (((EntityInfo) entityInfo.get(i2)).getKey().equals("vpl_type")) {
                                this.vplType = ((EntityInfo) entityInfo.get(i2)).getValue().toString();
                            } else if (((EntityInfo) entityInfo.get(i2)).getKey().equals("url")) {
                                try {
                                    this.vplDetailsUrl = ((EntityInfo) entityInfo.get(i2)).getValue().toString();
                                } catch (Exception unused) {
                                }
                            }
                        }
                    }
                }
                if (!TextUtils.isEmpty(this.vplDetailsUrl)) {
                    songParallexListingFragment = new SongParallexListingFragment();
                    ListingParams listingParams = new ListingParams();
                    listingParams.e(false);
                    listingParams.f(true);
                    listingParams.h(false);
                    listingParams.d(true);
                    listingParams.i(false);
                    listingParams.a(true);
                    ListingButton listingButton = (ListingButton) Constants.e().c().get(0);
                    listingButton.b(this.mBusinessObject.getRawName());
                    listingButton.a(this.mBusinessObject.getRawName());
                    URLManager c = listingButton.c();
                    c.g(true);
                    c.a(this.vplDetailsUrl);
                    c.d(false);
                    c.a(true);
                    c.a(BusinessObjectType.GenericItems);
                    c.h(false);
                    listingButton.a(c);
                    listingParams.a(listingButton);
                    songParallexListingFragment.a(listingParams);
                    ListingComponents listingComponents = new ListingComponents();
                    new ArrayList().add(listingButton);
                    this.mAppState.setListingComponents(listingComponents);
                    Bundle bundle = new Bundle();
                    bundle.putString("EXTRA_SOURCE_NAME", this.sourceName);
                    bundle.putString("EXTRA_VIEW_ALL_BANNER_AD_IMG", this.brandLogoUrl);
                    bundle.putString("EXTRA_BRAND_DFP_TRACKER", this.brandLogoTracker);
                    bundle.putString("EXTRA_VPL_TYPE", this.vplType);
                    songParallexListingFragment.setArguments(bundle);
                    ((GaanaActivity) this.mContext).displayFragment(songParallexListingFragment);
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(header);
                    stringBuilder2.append(" VPL ");
                    stringBuilder2.append(" click ");
                    stringBuilder3 = stringBuilder2.toString();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Position ");
                    stringBuilder.append(position);
                    stringBuilder.append(" - Album - ");
                    stringBuilder.append(item.getBusinessObjId());
                    ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder3, stringBuilder.toString());
                }
            } else if (entityType.equals(c.a)) {
                businessObject = (Playlist) populatePlaylistClicked(item);
                if (this.mFragment instanceof PartyFragment) {
                    if (i != 0) {
                        af.a(this.mContext, this.mFragment).a((int) R.id.playMenu, businessObject);
                        return;
                    }
                    this.mListingComponents = Constants.h();
                    this.mListingComponents.a(businessObject);
                    populateJukePlaylistListing(businessObject, header);
                } else if (i != 0) {
                    stringBuilder4 = new StringBuilder();
                    stringBuilder4.append(header);
                    stringBuilder4.append("_playclick");
                    stringBuilder5 = stringBuilder4.toString();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(c.a);
                    stringBuilder.append(businessObject.getBusinessObjId());
                    u.a().a("Browse", stringBuilder5, stringBuilder.toString());
                    af.a(this.mContext, this.mFragment).a((int) R.id.playMenu, businessObject);
                    return;
                } else {
                    if (i2 != 0) {
                        af.a(this.mContext, this.mFragment).a((int) R.id.shareMenu, businessObject);
                    }
                    if (TextUtils.isEmpty(businessObject.getChannelPageAdCode())) {
                        Constants.i = false;
                        Constants.j = "";
                    } else {
                        Constants.i = true;
                        Constants.j = businessObject.getChannelPageAdCode();
                    }
                    if (getSourceType() != null && getSourceType().equals("PoTH")) {
                        businessObject.setPlaylistSource(PlaylistSourceType.HOURLY_PLAYLIST);
                        StringBuilder stringBuilder9 = new StringBuilder();
                        stringBuilder9.append("Position ");
                        stringBuilder9.append(position);
                        stringBuilder9.append(" - ");
                        stringBuilder9.append(Calendar.getInstance().get(11));
                        stringBuilder9.append(" - Playlist - ");
                        stringBuilder9.append(businessObject.getBusinessObjId());
                        stringBuilder6 = stringBuilder9.toString();
                        stringBuilder7 = new StringBuilder();
                        stringBuilder7.append(header);
                        stringBuilder7.append(" click ");
                        ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder7.toString(), stringBuilder6);
                    } else if (this.mFragment instanceof DynamicHomeFragment) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(header);
                        stringBuilder.append(" click ");
                        stringBuilder8 = stringBuilder.toString();
                        stringBuilder7 = new StringBuilder();
                        stringBuilder7.append("Position ");
                        stringBuilder7.append(position);
                        stringBuilder7.append(" - Playlist - ");
                        stringBuilder7.append(businessObject.getBusinessObjId());
                        ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder8, stringBuilder7.toString());
                    } else if (this.mFragment instanceof DynamicOccasionFragment) {
                        baseActivity = (BaseActivity) this.mContext;
                        stringBuilder3 = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(header);
                        stringBuilder.append(" click ");
                        stringBuilder8 = stringBuilder.toString();
                        stringBuilder7 = new StringBuilder();
                        stringBuilder7.append("Position ");
                        stringBuilder7.append(position);
                        stringBuilder7.append(" - Playlist - ");
                        stringBuilder7.append(businessObject.getBusinessObjId());
                        baseActivity.sendGAEvent(stringBuilder3, stringBuilder8, stringBuilder7.toString());
                    } else if (this.mFragment instanceof MoreRadioActivityFragment) {
                        baseActivity = (BaseActivity) this.mContext;
                        stringBuilder3 = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(header);
                        stringBuilder.append(" click ");
                        stringBuilder8 = stringBuilder.toString();
                        stringBuilder7 = new StringBuilder();
                        stringBuilder7.append("Position ");
                        stringBuilder7.append(position);
                        stringBuilder7.append(" - Playlist - ");
                        stringBuilder7.append(businessObject.getBusinessObjId());
                        baseActivity.sendGAEvent(stringBuilder3, stringBuilder8, stringBuilder7.toString());
                    } else {
                        baseActivity = (BaseActivity) this.mContext;
                        stringBuilder3 = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Playlist Detail : ");
                        stringBuilder.append(businessObject.getName());
                        stringBuilder8 = stringBuilder.toString();
                        stringBuilder7 = new StringBuilder();
                        stringBuilder7.append(((BaseActivity) this.mContext).currentScreen);
                        stringBuilder7.append(" - ");
                        stringBuilder7.append(((BaseActivity) this.mContext).currentFavpage);
                        stringBuilder7.append(" - Playlist Detail");
                        baseActivity.sendGAEvent(stringBuilder3, stringBuilder8, stringBuilder7.toString());
                    }
                    this.mListingComponents = Constants.e();
                    this.mListingComponents.a(businessObject);
                    if (businessObject.isGaanaSpecial()) {
                        populateSpecialGaanaListing(businessObject);
                    } else {
                        populatePlaylistListing(businessObject);
                    }
                }
            } else {
                BusinessObject businessObject2 = null;
                int i3;
                if (entityType.equals(c.b)) {
                    businessObject = (Album) populateAlbumClicked(item);
                    if (i != 0) {
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(header);
                        stringBuilder4.append("_playclick");
                        stringBuilder5 = stringBuilder4.toString();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(c.b);
                        stringBuilder.append(businessObject.getBusinessObjId());
                        u.a().a("Browse", stringBuilder5, stringBuilder.toString());
                        af.a(this.mContext, this.mFragment).a((int) R.id.playMenu, businessObject);
                        return;
                    }
                    if (i2 != 0) {
                        af.a(this.mContext, this.mFragment).a((int) R.id.shareMenu, businessObject);
                    }
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
                    if (this.mFragment instanceof DynamicHomeFragment) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(header);
                        stringBuilder.append(" click ");
                        stringBuilder8 = stringBuilder.toString();
                        stringBuilder7 = new StringBuilder();
                        stringBuilder7.append("Position ");
                        stringBuilder7.append(position);
                        stringBuilder7.append(" - Album - ");
                        stringBuilder7.append(businessObject.getBusinessObjId());
                        ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder8, stringBuilder7.toString());
                    } else if (this.mFragment instanceof DynamicOccasionFragment) {
                        baseActivity = (BaseActivity) this.mContext;
                        stringBuilder3 = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(header);
                        stringBuilder.append(" click ");
                        stringBuilder8 = stringBuilder.toString();
                        stringBuilder7 = new StringBuilder();
                        stringBuilder7.append("Position ");
                        stringBuilder7.append(position);
                        stringBuilder7.append(" - Album - ");
                        stringBuilder7.append(businessObject.getBusinessObjId());
                        baseActivity.sendGAEvent(stringBuilder3, stringBuilder8, stringBuilder7.toString());
                    } else {
                        baseActivity = (BaseActivity) this.mContext;
                        stringBuilder3 = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Album Detail : ");
                        stringBuilder.append(businessObject.getName());
                        stringBuilder8 = stringBuilder.toString();
                        stringBuilder7 = new StringBuilder();
                        stringBuilder7.append(((BaseActivity) this.mContext).currentScreen);
                        stringBuilder7.append(" - ");
                        stringBuilder7.append(((BaseActivity) this.mContext).currentFavpage);
                        stringBuilder7.append(" - Album Detail");
                        baseActivity.sendGAEvent(stringBuilder3, stringBuilder8, stringBuilder7.toString());
                    }
                    populateAlbumListing(businessObject);
                } else if (entityType.equals(d.d) || entityType.equals(d.c)) {
                    an.a().a("click", "en", getUniqueID(), GaanaApplication.getInstance().getPageName(), this.mBusinessObject.getBusinessObjId(), this.mBusinessObject.getName(), String.valueOf(position - 1), "");
                    if (Constants.cY) {
                        JukeSessionManager.getErrorDialog(this.mContext, 0, new OnButtonClickListener() {
                            public void onNegativeButtonClick() {
                            }

                            public void onPositiveButtonClick() {
                                JukeSessionManager.getInstance().stopJukeSession(new l.s() {
                                    public void onErrorResponse(BusinessObject businessObject) {
                                    }

                                    public void onRetreivalComplete(BusinessObject businessObject) {
                                        if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                                            GenericItemView.this.onClick(view2);
                                        }
                                    }
                                });
                            }
                        });
                        return;
                    }
                    businessObject = (Radio) populateRadioClicked(item);
                    if (this.mAppState.isAppInOfflineMode()) {
                        ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_radio));
                        return;
                    } else if (Util.j(this.mContext)) {
                        this.mBusinessObject = businessObject;
                        if (businessObject.getType().equals(d.c)) {
                            if (this.mFragment instanceof DynamicHomeFragment) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(header);
                                stringBuilder.append(" click ");
                                stringBuilder8 = stringBuilder.toString();
                                stringBuilder7 = new StringBuilder();
                                stringBuilder7.append("Position ");
                                stringBuilder7.append(position);
                                stringBuilder7.append(" - RadioMirchi - ");
                                stringBuilder7.append(businessObject.getBusinessObjId());
                                ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder8, stringBuilder7.toString());
                            } else if (this.mFragment instanceof DynamicOccasionFragment) {
                                baseActivity = (BaseActivity) this.mContext;
                                stringBuilder3 = ((BaseActivity) this.mContext).currentScreen;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(header);
                                stringBuilder.append(" click ");
                                stringBuilder8 = stringBuilder.toString();
                                stringBuilder7 = new StringBuilder();
                                stringBuilder7.append("Position ");
                                stringBuilder7.append(position);
                                stringBuilder7.append(" - RadioMirchi - ");
                                stringBuilder7.append(businessObject.getBusinessObjId());
                                baseActivity.sendGAEvent(stringBuilder3, stringBuilder8, stringBuilder7.toString());
                            } else {
                                stringBuilder7 = new StringBuilder();
                                stringBuilder7.append(((BaseActivity) this.mContext).currentScreen);
                                stringBuilder7.append(" - RadioMirchi - ");
                                stringBuilder7.append(businessObject.getName());
                                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Play", stringBuilder7.toString());
                            }
                            ad.a(this.mContext).a(businessObject);
                        } else {
                            if (this.mFragment instanceof DynamicHomeFragment) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(header);
                                stringBuilder.append(" click ");
                                stringBuilder8 = stringBuilder.toString();
                                stringBuilder7 = new StringBuilder();
                                stringBuilder7.append("Position");
                                stringBuilder7.append(position);
                                stringBuilder7.append(" - GaanaRadio - ");
                                stringBuilder7.append(businessObject.getBusinessObjId());
                                ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder8, stringBuilder7.toString());
                            } else if (this.mFragment instanceof DynamicOccasionFragment) {
                                baseActivity = (BaseActivity) this.mContext;
                                stringBuilder3 = ((BaseActivity) this.mContext).currentScreen;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(header);
                                stringBuilder.append(" click ");
                                stringBuilder8 = stringBuilder.toString();
                                stringBuilder7 = new StringBuilder();
                                stringBuilder7.append("Position");
                                stringBuilder7.append(position);
                                stringBuilder7.append(" - GaanaRadio - ");
                                stringBuilder7.append(businessObject.getBusinessObjId());
                                baseActivity.sendGAEvent(stringBuilder3, stringBuilder8, stringBuilder7.toString());
                            } else {
                                stringBuilder7 = new StringBuilder();
                                stringBuilder7.append(((BaseActivity) this.mContext).currentScreen);
                                stringBuilder7.append(" - GaanaRadio - ");
                                stringBuilder7.append(businessObject.getName());
                                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Play", stringBuilder7.toString());
                            }
                            ad.a(this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", businessObject.getBusinessObjId()).replace("<radio_type>", businessObject.getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), businessObject);
                        }
                        this.mListingComponents = Constants.a((Radio) businessObject);
                        this.mListingComponents.a(businessObject);
                        populateRadioListing(businessObject);
                    } else {
                        ap.a().f(this.mContext);
                        return;
                    }
                } else if (entityType.equals(c.d)) {
                    businessObject = (Artist) populateArtistClicked(item);
                    if (i != 0) {
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(header);
                        stringBuilder4.append("_playclick");
                        stringBuilder5 = stringBuilder4.toString();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(c.d);
                        stringBuilder.append(businessObject.getBusinessObjId());
                        u.a().a("Browse", stringBuilder5, stringBuilder.toString());
                        af.a(this.mContext, this.mFragment).a((int) R.id.playMenu, businessObject);
                        return;
                    }
                    this.mListingComponents = Constants.a("", businessObject.isLocalMedia());
                    this.mAppState.setListingComponents(this.mListingComponents);
                    if (this.mFragment instanceof DynamicHomeFragment) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(header);
                        stringBuilder.append(" click ");
                        stringBuilder8 = stringBuilder.toString();
                        stringBuilder7 = new StringBuilder();
                        stringBuilder7.append("Position ");
                        stringBuilder7.append(position);
                        stringBuilder7.append(" - Artist - ");
                        stringBuilder7.append(businessObject.getBusinessObjId());
                        ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder8, stringBuilder7.toString());
                    } else if (this.mFragment instanceof DynamicOccasionFragment) {
                        baseActivity = (BaseActivity) this.mContext;
                        stringBuilder3 = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(header);
                        stringBuilder.append(" click ");
                        stringBuilder8 = stringBuilder.toString();
                        stringBuilder7 = new StringBuilder();
                        stringBuilder7.append("Position ");
                        stringBuilder7.append(position);
                        stringBuilder7.append(" - Artist - ");
                        stringBuilder7.append(businessObject.getBusinessObjId());
                        baseActivity.sendGAEvent(stringBuilder3, stringBuilder8, stringBuilder7.toString());
                    } else {
                        ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Artist Detail", ((BaseActivity) this.mContext).currentScreen);
                    }
                    populateArtistListing(businessObject);
                } else if (entityType.equals(c.c)) {
                    Track track = (Track) populateTrackClicked(item);
                    if ("1".equalsIgnoreCase(track.getLocationAvailability()) && "0".equalsIgnoreCase(track.getDeviceAvailability())) {
                        ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
                        return;
                    } else if ("0".equalsIgnoreCase(track.getLocationAvailability()) && "1".equalsIgnoreCase(track.getDeviceAvailability())) {
                        ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
                        return;
                    } else if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue()) {
                        ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This song");
                        return;
                    } else if (Util.j(this.mContext) || DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue()) {
                        String stringBuilder10;
                        StringBuilder stringBuilder11;
                        if (this.mFragment instanceof DynamicHomeFragment) {
                            stringBuilder7 = new StringBuilder();
                            stringBuilder7.append(header);
                            stringBuilder7.append(" click ");
                            stringBuilder10 = stringBuilder7.toString();
                            stringBuilder11 = new StringBuilder();
                            stringBuilder11.append("Position ");
                            stringBuilder11.append(position);
                            stringBuilder11.append(" - Track - ");
                            stringBuilder11.append(track.getBusinessObjId());
                            ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder10, stringBuilder11.toString());
                        } else if (this.mFragment instanceof DynamicOccasionFragment) {
                            BaseActivity baseActivity2 = (BaseActivity) this.mContext;
                            stringBuilder8 = ((BaseActivity) this.mContext).currentScreen;
                            stringBuilder7 = new StringBuilder();
                            stringBuilder7.append(header);
                            stringBuilder7.append(" click ");
                            stringBuilder10 = stringBuilder7.toString();
                            stringBuilder11 = new StringBuilder();
                            stringBuilder11.append("Position ");
                            stringBuilder11.append(position);
                            stringBuilder11.append(" - Track - ");
                            stringBuilder11.append(track.getBusinessObjId());
                            baseActivity2.sendGAEvent(stringBuilder8, stringBuilder10, stringBuilder11.toString());
                        } else {
                            stringBuilder11 = new StringBuilder();
                            stringBuilder11.append(((BaseActivity) this.mContext).currentScreen);
                            stringBuilder11.append(" - Track - ");
                            stringBuilder11.append(track.getName());
                            ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Play", stringBuilder11.toString());
                            this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.MYMUSIC_RECENTLYPLAYED.name());
                        }
                        Util.a(this.mContext, track, view2, new ba() {
                            public void onPlaySong(View view, Track track) {
                                GenericItemView.this.playTrackClickedSong(view, track);
                            }
                        });
                    } else {
                        if (this.isPlayerQueue) {
                            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.error_msg_no_connection));
                        } else {
                            ap.a().f(this.mContext);
                        }
                        return;
                    }
                } else if (entityType.equals(c.f)) {
                    YouTubeVideo youTubeVideo = (YouTubeVideo) populateVideoClicked(item);
                    if (youTubeVideo.e() == 0) {
                        launchYouTubePlayer(youTubeVideo.c(), youTubeVideo.a(), youTubeVideo, youTubeVideo.e());
                        baseActivity = (BaseActivity) this.mContext;
                        stringBuilder3 = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(header);
                        stringBuilder.append(" click ");
                        stringBuilder8 = stringBuilder.toString();
                        stringBuilder7 = new StringBuilder();
                        stringBuilder7.append("Position ");
                        stringBuilder7.append(position);
                        stringBuilder7.append(" - Video - ");
                        stringBuilder7.append(youTubeVideo.getBusinessObjId());
                        baseActivity.sendGAEvent(stringBuilder3, stringBuilder8, stringBuilder7.toString());
                    } else {
                        Util.a(this.mContext, (YouTubeVideo) populateVideoClicked(item), getSectionName());
                        if (this.mFragment instanceof MoreRadioActivityFragment) {
                            u a = u.a();
                            stringBuilder6 = ((BaseActivity) this.mContext).currentScreen;
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(getSectionName());
                            stringBuilder2.append(" click");
                            stringBuilder3 = stringBuilder2.toString();
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("position-");
                            stringBuilder.append(tagObject.getPosition());
                            stringBuilder.append("-");
                            stringBuilder.append("Video-");
                            stringBuilder.append(item.getEntityId());
                            a.a(stringBuilder6, stringBuilder3, stringBuilder.toString());
                        } else {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append(getSectionName());
                            stringBuilder2.append(" click");
                            stringBuilder3 = stringBuilder2.toString();
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("position-");
                            stringBuilder.append(tagObject.getPosition());
                            stringBuilder.append("-");
                            stringBuilder.append("Video-");
                            stringBuilder.append(item.getEntityId());
                            u.a().a("Browse", stringBuilder3, stringBuilder.toString());
                        }
                    }
                } else if (entityType.equals(c.g)) {
                    DiscoverTag discoverTag = (DiscoverTag) populateDiscoverTagClicked(item);
                    if (this.mAppState.isAppInOfflineMode()) {
                        ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This item");
                        return;
                    } else if (Util.j(this.mContext)) {
                        baseActivity = (BaseActivity) this.mContext;
                        stringBuilder3 = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(discoverTag.getEnglishName());
                        stringBuilder.append(" Detail ");
                        stringBuilder8 = stringBuilder.toString();
                        stringBuilder7 = new StringBuilder();
                        stringBuilder7.append(((BaseActivity) this.mContext).currentScreen);
                        stringBuilder7.append(" - Discover - ");
                        stringBuilder7.append(discoverTag.getName());
                        baseActivity.sendGAEvent(stringBuilder3, stringBuilder8, stringBuilder7.toString());
                        stringBuilder6 = discoverTag.getBusinessObjId();
                        discoverTag.getName();
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("<category_id>", stringBuilder6);
                        bundle2.putString("EXTRA_ACTIONBAR_TITLE", discoverTag.getRawName());
                        songParallexListingFragment = new DiscoverDetailFragment();
                        songParallexListingFragment.setArguments(bundle2);
                        ((GaanaActivity) this.mContext).displayFragment(songParallexListingFragment);
                    } else {
                        ap.a().f(this.mContext);
                        return;
                    }
                } else if (entityType.equals(c.i)) {
                    handleOccasionEntity(item);
                } else if (entityType.equals(c.e)) {
                    if (!Util.j(this.mFragment.getActivity()) || this.mAppState.isAppInOfflineMode()) {
                        ap.a().f(this.mContext);
                        return;
                    }
                    ArrayList arrayList = (ArrayList) item.getEntityInfo();
                    if (arrayList != null) {
                        i = arrayList.size();
                        for (i3 = 0; i3 < i; i3++) {
                            if (((EntityInfo) arrayList.get(i3)).getKey().equals("dl_url")) {
                                businessObject2 = (String) ((EntityInfo) arrayList.get(i3)).getValue();
                            }
                        }
                    }
                    if (businessObject2 != null) {
                        ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "DeepLink Detail", ((BaseActivity) this.mContext).currentScreen);
                        Util.a(Util.a(this.mContext, arrayList), getMandatoryLogin(arrayList), getInAppWeb(arrayList), this.mContext);
                    }
                } else if (entityType.equals(c.h)) {
                    if (!Util.j(this.mFragment.getActivity()) || this.mAppState.isAppInOfflineMode()) {
                        ap.a().f(this.mContext);
                        return;
                    }
                    CharSequence charSequence = "";
                    CharSequence charSequence2 = "";
                    stringBuilder8 = "";
                    for (EntityInfo entityInfo2 : item.getEntityInfo()) {
                        if (entityInfo2.getKey().equalsIgnoreCase("ad_url")) {
                            charSequence = entityInfo2.getValue().toString();
                        } else if (entityInfo2.getKey().equalsIgnoreCase("dl_url")) {
                            charSequence2 = entityInfo2.getValue().toString();
                        } else if (entityInfo2.getKey().equalsIgnoreCase("web_dl_url")) {
                            stringBuilder8 = entityInfo2.getValue().toString();
                        }
                    }
                    if (!TextUtils.isEmpty(charSequence2)) {
                        com.services.c.a(this.mContext).a(this.mContext, (String) charSequence2, this.mAppState);
                    } else if (!TextUtils.isEmpty(charSequence)) {
                        Intent intent = new Intent(this.mContext, WebViewActivity.class);
                        intent.putExtra("EXTRA_WEBVIEW_URL", charSequence);
                        intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
                        intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
                        intent.putExtra("title", item.getName());
                        this.mContext.startActivity(intent);
                    } else if (!TextUtils.isEmpty(stringBuilder8)) {
                        if (!(stringBuilder8.startsWith("http://") || stringBuilder8.startsWith("https://"))) {
                            StringBuilder stringBuilder12 = new StringBuilder();
                            stringBuilder12.append("http://");
                            stringBuilder12.append(stringBuilder8);
                            stringBuilder8 = stringBuilder12.toString();
                        }
                        this.mContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder8)));
                    }
                } else if (entityType.equals(c.k)) {
                    CharSequence charSequence3 = "";
                    List entityInfo3 = item.getEntityInfo();
                    if (entityInfo3 != null) {
                        for (i3 = 0; i3 < entityInfo3.size(); i3++) {
                            if (((EntityInfo) entityInfo3.get(i3)).getKey().equals("url")) {
                                charSequence3 = (String) ((EntityInfo) entityInfo3.get(i3)).getValue();
                                break;
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(charSequence3)) {
                        ((GaanaActivity) this.mContext).displayFragment(JukeSeeAllFragment.newInstance(charSequence3, item.getName(), BusinessObjectType.GenericItems.name(), false));
                    }
                } else if (entityType.equals(c.m)) {
                    handleOccasionEntity(item);
                }
            }
            String a2 = an.a().a(an.a().a);
            if (!(this.mFragment instanceof DynamicOccasionFragment) || this.isPlayerQueue) {
                an.a().a("click", "en", getUniqueID(), a2, this.mBusinessObject.getBusinessObjId(), item.getEntityType(), String.valueOf(position - 1), "");
            } else {
                an.a().a("click", "en", getUniqueID(), this.mBusinessObject.getLanguage(), this.mBusinessObject.getBusinessObjId(), item.getEntityType(), String.valueOf(position - 1), "");
            }
            if (this.mFragment instanceof SearchTabFragment) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(position);
                stringBuilder.append(" - ");
                stringBuilder.append(entityType);
                stringBuilder.append(" - ");
                stringBuilder.append(item.getBusinessObjId());
                ((BaseActivity) this.mContext).sendGAEvent("Online-SearchScreen", "TrendingSearch", stringBuilder.toString());
                GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.TRENDING_SEARCH.name());
                GaanaSearchManager.a().b(true);
            }
        } else if ((this.mBusinessObject instanceof Playlist) && (this.mFragment instanceof PartyFragment)) {
            populateJukePlaylistListing((Playlist) this.mBusinessObject, this.sourceName);
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

    private void playTrackClickedSong(View view, Track track) {
        PlayerTrack playerTrack = new PlayerTrack(track, track.getBusinessObjId(), SOURCE_TYPE.OTHER.ordinal(), track.getEnglishName());
        playerTrack.f(this.mFragment.getPageName());
        ArrayList arrayList = new ArrayList();
        ArrayList b = aq.a().b();
        if (b != null && b.size() > 0) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                arrayList.add(populateTrackClicked((com.gaana.models.Item) ((BusinessObject) it.next())));
            }
        }
        arrayList = com.logging.d.a().a(this.mFragment, arrayList);
        if (arrayList != null) {
            PlayerManager.a(this.mContext).b(arrayList, playerTrack, 0);
        }
        PlayerManager.a(this.mContext).g(true);
        play(playerTrack);
        PlayerManager.a(this.mContext).g(false);
    }

    private void handleOccasionEntity(final com.gaana.models.Item item) {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This item");
        } else if (Util.j(this.mContext)) {
            ArrayList arrayList = (ArrayList) item.getEntityInfo();
            String str = "";
            String str2 = "";
            if (arrayList != null) {
                int size = arrayList.size();
                CharSequence charSequence = str2;
                CharSequence charSequence2 = str;
                for (int i = 0; i < size; i++) {
                    if (((EntityInfo) arrayList.get(i)).getKey().equals("dl_url")) {
                        charSequence2 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("url")) {
                        charSequence = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    }
                }
                if (!TextUtils.isEmpty(charSequence2)) {
                    ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "DeepLink Detail", ((BaseActivity) this.mContext).currentScreen);
                    Util.a(Util.a(this.mContext, arrayList), getMandatoryLogin(arrayList), getInAppWeb(arrayList), this.mContext);
                } else if (!TextUtils.isEmpty(charSequence) && (charSequence.contains("occasion") || charSequence.contains(TtmlNode.TAG_METADATA))) {
                    String substring = charSequence.substring(charSequence.lastIndexOf("/") + 1, charSequence.length());
                    if (this.mFragment instanceof MoreRadioActivityFragment) {
                        u a = u.a();
                        str = ((BaseActivity) this.mContext).currentScreen;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(item.getName());
                        stringBuilder.append("_Click");
                        a.b(str, stringBuilder.toString());
                    } else {
                        u a2 = u.a();
                        str2 = ((BaseActivity) this.mContext).currentScreen;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(substring);
                        stringBuilder2.append("_Click");
                        a2.b(str2, stringBuilder2.toString());
                    }
                    com.dynamicview.c.a().a(new ag() {
                        public void onOccasionResponse() {
                            BaseGaanaFragment dynamicOccasionFragment = new DynamicOccasionFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("OCCASION_URL", charSequence);
                            bundle.putString("OCCASION_REFRESH_INTERVAL", null);
                            bundle.putString(JukeSeeAllFragment.EXTRA_ARG_TITLE, item.getName());
                            dynamicOccasionFragment.setArguments(bundle);
                            ((GaanaActivity) GenericItemView.this.mContext).displayFragment(dynamicOccasionFragment);
                        }

                        public void onOccasionError() {
                            aj.a().a(GenericItemView.this.mContext, GenericItemView.this.getResources().getString(R.string.error_download_no_internet));
                        }
                    }, charSequence, null, false);
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

    public String getSectionName() {
        return getSourceType();
    }

    public View getAdView(int i, ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, String str) {
        PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder;
        com.gaana.models.Item item = (com.gaana.models.Item) businessObject;
        CharSequence name = item.getName();
        for (int i2 = 0; i2 < item.getEntityInfo().size(); i2++) {
            EntityInfo entityInfo = (EntityInfo) item.getEntityInfo().get(i2);
            if (entityInfo.getKey().equals("dl_tag_title")) {
                name = (String) entityInfo.getValue();
            }
        }
        if (playlistGridHolder.crossFadeImageView instanceof RoundedCornerImageView) {
            ((RoundedCornerImageView) playlistGridHolder.crossFadeImageView).setHasGradient(false);
        }
        playlistGridHolder.crossFadeImageView.bindImage(item.getArtwork());
        playlistGridHolder.tvTopHeading.setTypeface(playlistGridHolder.tvTopHeading.getTypeface(), 2);
        playlistGridHolder.tvTopHeading.setText(name);
        this.mView = viewHolder.itemView;
        this.mView.setTag(new TagObject(businessObject, i, str));
        this.mView.setOnClickListener(this);
        return viewHolder.itemView;
    }
}
