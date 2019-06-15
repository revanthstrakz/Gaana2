package com.gaana.view.item;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.constants.c.d;
import com.dynamicview.DynamicOccasionFragment;
import com.fragments.BaseGaanaFragment;
import com.fragments.ListingFragment;
import com.fragments.MyMusicSearchResultFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.library.controls.RoundedCornerImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.GaanaSearchManager;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlaySequenceType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.u;
import com.models.PlayerTrack;
import com.services.c;
import com.services.l.ag;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;

public class SearchItemView extends BaseItemView implements DownloadPopupListener {
    private ImageView clickoptionImage;
    public CrossFadeImageView crossFadeImageView;
    private ImageView downloadImage;
    private boolean mBlockPopular;
    private CrossFadeImageView mCrossFadeImageIcon;
    public ImageView play_icon;
    private String searchText;
    public TextView tvBottomHeading;
    private TextView tvHighlightTxt;
    private TextView tvSubtitle;
    private TextView tvTitle;
    public TextView tvTopHeading;

    public enum SearchCategory {
        Track,
        Album,
        Artist,
        Playlist,
        Radio,
        OfflineTrack,
        Occasion,
        video
    }

    public static class RecentSearchItemHolder extends ViewHolder {
        public CrossFadeImageView crossFadeImageView;
        public View mView;
        public RelativeLayout parentEmptyLayout;
        public ImageView play_icon;
        public TextView tvBottomHeading;
        public TextView tvTopHeading;

        public RecentSearchItemHolder(View view) {
            super(view);
            this.mView = view;
            this.parentEmptyLayout = (RelativeLayout) view.findViewById(R.id.rl_empty_item_view);
            this.crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.play_icon = (ImageView) view.findViewById(R.id.play_icon);
            this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
            this.tvBottomHeading = (TextView) view.findViewById(R.id.tvBottomHeading);
        }
    }

    public static class SearchItemHolder extends ViewHolder {
        private ImageView clickoptionImage;
        private ImageView downloadImage;
        private boolean isTopResult = false;
        private RoundedCornerImageView mCrossFadeImageIcon;
        private ProgressBar progressBar;
        private TextView tvHighlightTxt;
        private TextView tvSubtitle;
        private TextView tvTitle;

        public SearchItemHolder(View view) {
            super(view);
            this.mCrossFadeImageIcon = (RoundedCornerImageView) view.findViewById(R.id.f38download.item.img.thumb);
            this.tvTitle = (TextView) view.findViewById(R.id.f43download.item.tv.trackname);
            this.tvSubtitle = (TextView) view.findViewById(R.id.f40download.item.tv.genere);
            this.tvHighlightTxt = (TextView) view.findViewById(R.id.f36download.item.highlight.tv);
            this.clickoptionImage = (ImageView) view.findViewById(R.id.clickoptionImage);
            this.clickoptionImage.setRotation(90.0f);
            this.progressBar = (ProgressBar) view.findViewById(R.id.f34download.item.ProgressBar);
            this.downloadImage = (ImageView) view.findViewById(R.id.f37download.item.img.download);
        }

        public void setTopResult(boolean z) {
            this.isTopResult = z;
        }
    }

    public SearchItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mLayoutId = R.layout.view_item_download_search;
        if (baseGaanaFragment != null && (baseGaanaFragment instanceof ListingFragment)) {
            this.mBlockPopular = ((ListingFragment) baseGaanaFragment).n();
        }
    }

    public SearchItemView(Context context, BaseGaanaFragment baseGaanaFragment, int i) {
        super(context, baseGaanaFragment);
        this.mLayoutId = R.layout.view_item_download_search;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        this.mView = viewHolder.itemView;
        this.mView = super.getPoplatedView(this.mView, businessObject);
        return getDataFilledView(viewHolder, businessObject);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject) {
        this.mView = viewHolder.itemView;
        this.mView = super.getPoplatedView(this.mView, businessObject);
        return getRecentSearchDataFilledView(viewHolder, businessObject);
    }

    public View getDataFilledView(ViewHolder viewHolder, BusinessObject businessObject) {
        this.mBusinessObject = businessObject;
        AutoComplete autoComplete = (AutoComplete) this.mBusinessObject;
        SearchItemHolder searchItemHolder = (SearchItemHolder) viewHolder;
        boolean access$000 = searchItemHolder.isTopResult;
        this.mView = searchItemHolder.itemView;
        this.tvTitle = searchItemHolder.tvTitle;
        this.tvSubtitle = searchItemHolder.tvSubtitle;
        this.tvHighlightTxt = searchItemHolder.tvHighlightTxt;
        this.downloadImage = searchItemHolder.downloadImage;
        int i = 8;
        if (this.downloadImage != null) {
            this.downloadImage.setVisibility(8);
        }
        TypedArray obtainStyledAttributes;
        if (!autoComplete.isHighlighted() || this.mBlockPopular) {
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.selector_button_search});
            obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            if (this.tvHighlightTxt != null) {
                this.tvHighlightTxt.setVisibility(8);
                if (this.downloadImage != null) {
                    this.downloadImage.setVisibility(8);
                }
            }
        } else {
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.view_foreground});
            obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            if (this.tvHighlightTxt != null) {
                this.tvHighlightTxt.setVisibility(0);
                if (this.downloadImage != null) {
                    this.downloadImage.setVisibility(4);
                }
                if (access$000) {
                    this.tvHighlightTxt.setVisibility(8);
                }
            }
        }
        this.tvTitle.setText(Util.c(this.searchText, autoComplete.getTitle()));
        if (TextUtils.isEmpty(autoComplete.getSubtitle()) || autoComplete.getSubtitle().equals("0")) {
            this.tvSubtitle.setVisibility(8);
        } else {
            this.tvSubtitle.setVisibility(0);
            this.tvSubtitle.setText(Util.c(this.searchText, autoComplete.getSubtitle()));
            int i2 = Constants.l ? R.drawable.vector_ic_explicit_content_white : R.drawable.vector_ic_explicit_content;
            if (autoComplete.isParentalWarningEnabled()) {
                this.tvSubtitle.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(i2), null, null, null);
            } else {
                this.tvSubtitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
        }
        if (access$000 && !TextUtils.isEmpty(autoComplete.getAutoType())) {
            this.tvSubtitle.setVisibility(0);
            this.tvSubtitle.setText(autoComplete.getAutoType());
        }
        TypedValue typedValue;
        if (TextUtils.isEmpty(autoComplete.getType()) || !autoComplete.getType().equalsIgnoreCase("Track")) {
            typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
            this.tvTitle.setTextColor(typedValue.data);
        } else {
            PlayerTrack i3 = PlayerManager.a(this.mContext).i();
            if (i3 == null || i3.b() == null || !autoComplete.getBusinessObjectId().equalsIgnoreCase(i3.h())) {
                typedValue = new TypedValue();
                this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
                this.tvTitle.setTextColor(typedValue.data);
            } else {
                this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.gaana_orange_text));
            }
        }
        this.mCrossFadeImageIcon = searchItemHolder.mCrossFadeImageIcon;
        if (!TextUtils.isEmpty(autoComplete.getArtwork())) {
            if (autoComplete.isLocalMedia()) {
                this.mCrossFadeImageIcon.bindImageForLocalMedia(autoComplete.getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
            } else {
                this.mCrossFadeImageIcon.bindImage(autoComplete.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
            }
        }
        this.clickoptionImage = searchItemHolder.clickoptionImage;
        this.clickoptionImage.setTag(businessObject);
        this.clickoptionImage.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SearchItemView.this.showOptionMenu(view);
            }
        });
        if (autoComplete.getType().equalsIgnoreCase("Artist") && autoComplete.isLocalMedia()) {
            this.clickoptionImage.setVisibility(8);
        }
        if (autoComplete.getType() != null && autoComplete.getType().equalsIgnoreCase("Occasion")) {
            if (autoComplete.isHighlighted()) {
                i = 4;
            }
            this.clickoptionImage.setVisibility(i);
        }
        this.mView.setOnClickListener(this);
        return this.mView;
    }

    public View getRecentSearchDataFilledView(ViewHolder viewHolder, BusinessObject businessObject) {
        this.mBusinessObject = businessObject;
        AutoComplete autoComplete = (AutoComplete) this.mBusinessObject;
        RecentSearchItemHolder recentSearchItemHolder = (RecentSearchItemHolder) viewHolder;
        this.mView = recentSearchItemHolder.itemView;
        this.tvTopHeading = recentSearchItemHolder.tvTopHeading;
        this.tvBottomHeading = recentSearchItemHolder.tvBottomHeading;
        this.tvBottomHeading.setVisibility(0);
        this.tvTopHeading.setText(autoComplete.getTitle());
        if (autoComplete.getType() != null) {
            this.tvBottomHeading.setText(autoComplete.getType());
        }
        this.play_icon = recentSearchItemHolder.play_icon;
        if (autoComplete.getType() == null || !autoComplete.getType().equalsIgnoreCase("Track")) {
            this.play_icon.setVisibility(8);
        } else {
            this.play_icon.setVisibility(0);
            int i = Constants.l ? R.drawable.vector_ic_explicit_content_white : R.drawable.vector_ic_explicit_content;
            if (autoComplete.isParentalWarningEnabled()) {
                this.tvBottomHeading.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(i), null, null, null);
            } else {
                this.tvBottomHeading.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
            this.tvBottomHeading.setText("Song");
        }
        this.crossFadeImageView = recentSearchItemHolder.crossFadeImageView;
        if (TextUtils.isEmpty(autoComplete.getArtwork())) {
            if (autoComplete.getType() != null) {
                TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
                this.crossFadeImageView.setImageDrawable(obtainStyledAttributes.getDrawable(0));
                obtainStyledAttributes.recycle();
            } else {
                this.crossFadeImageView.setImageResource(R.drawable.recent_search_default);
            }
        } else if (autoComplete.isLocalMedia()) {
            this.crossFadeImageView.bindImageForLocalMedia(autoComplete.getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
        } else {
            this.crossFadeImageView.bindImage(autoComplete.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        }
        this.mView.setOnClickListener(this);
        return this.mView;
    }

    public void showOptionHeader(BusinessObject businessObject) {
        View view = new View(this.mContext);
        view.setTag(businessObject);
        showOptionMenu(view);
    }

    /* Access modifiers changed, original: protected */
    public void showOptionMenu(View view) {
        String str;
        if (Constants.au) {
            GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.VOICE_AUTO_SUGGEST.name());
        } else {
            GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.SEARCH_AUTO_SUGGEST.name());
        }
        AutoComplete autoComplete = (AutoComplete) view.getTag();
        BusinessObject convertToBusinessObject = convertToBusinessObject(autoComplete);
        boolean z = false;
        PopupWindowView instance;
        if (!convertToBusinessObject.isLocalMedia() && Util.c(convertToBusinessObject)) {
            convertToBusinessObject = DownloadManager.c().a(convertToBusinessObject.getBusinessObjType(), convertToBusinessObject.getBusinessObjId());
            instance = PopupWindowView.getInstance(this.mContext, this.mFragment);
            if (this instanceof DownloadPopupListener) {
                instance.setDownloadPopupListener(this);
            }
            instance.contextPopupWindow(convertToBusinessObject, this.isPlayerQueue, false);
        } else if (isMyPlaylist(convertToBusinessObject)) {
            convertToBusinessObject = PlaylistSyncManager.getInstance().getPlaylistDetails(convertToBusinessObject.getBusinessObjId());
            instance = PopupWindowView.getInstance(this.mContext, this.mFragment);
            if (this instanceof DownloadPopupListener) {
                instance.setDownloadPopupListener(this);
            }
            instance.contextPopupWindow(convertToBusinessObject, this.isPlayerQueue, false);
        } else if (convertToBusinessObject.isLocalMedia()) {
            convertToBusinessObject = LocalMediaManager.getInstance(this.mContext).getLocalItemById(convertToBusinessObject.getBusinessObjType(), convertToBusinessObject.getBusinessObjId());
            instance = PopupWindowView.getInstance(this.mContext, this.mFragment);
            if (this instanceof DownloadPopupListener) {
                instance.setDownloadPopupListener(this);
            }
            instance.contextPopupWindow(convertToBusinessObject, this.isPlayerQueue, false);
        } else {
            Util.a(this.mContext, this.mFragment, convertToBusinessObject, this.isPlayerQueue, (DownloadPopupListener) this, autoComplete.isRecentSearch());
        }
        String type = autoComplete.getType();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Moreoptions-");
        stringBuilder.append(type);
        type = stringBuilder.toString();
        if ((this.mFragment instanceof ListingFragment) || (this.mFragment instanceof MyMusicSearchResultFragment)) {
            z = true;
        }
        boolean z2 = z;
        String type2 = autoComplete.getType();
        if (!TextUtils.isEmpty(autoComplete.getSectionType())) {
            type2 = autoComplete.getSectionType();
            type = autoComplete.getSectionType().equals("MY_DOWNLOADS") ? "Moreoptions-Downloads" : "Moreoptions-Local Files";
        }
        String str2 = type;
        if (type2.equalsIgnoreCase("Radio")) {
            str = ((Radio) convertToBusinessObject).getType().equalsIgnoreCase(d.c) ? "RADIO_MIRCHI" : "GAANA_RADIO";
        } else {
            str = type2;
        }
        GaanaSearchManager.a().a((Activity) this.mContext, str2, autoComplete.getPosition(), autoComplete.getEnglishTitle(), z2, str, autoComplete.getBusinessObjectId());
    }

    public void setRepeatOne(String str, String str2) {
        PlayerManager a = PlayerManager.a(this.mContext);
        if (str == null && a != null && a.z()) {
            PlayerManager.a(this.mContext).i(false);
            return;
        }
        if (str == null || !str.equals(str2) || a == null || !a.z()) {
            PlayerManager.a(this.mContext).i(false);
        } else {
            PlayerManager.a(this.mContext).i(true);
        }
    }

    public void onClick(View view) {
        final View view2 = view;
        AutoComplete autoComplete = (AutoComplete) view.getTag();
        if (autoComplete.getType() == null && autoComplete.isRecentSearch()) {
            GaanaSearchManager.a().a(view2);
            return;
        }
        BusinessObject convertToBusinessObject = convertToBusinessObject(autoComplete);
        if (view.getId() == R.id.btn_play_all) {
            handlePlayall(convertToBusinessObject);
        }
        if (convertToBusinessObject.getBusinessObjType() == BusinessObjectType.Radios) {
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
                                    SearchItemView.this.onClick(view2);
                                }
                            }
                        });
                    }
                });
                return;
            }
            populateRadio(convertToBusinessObject);
        } else if (convertToBusinessObject.getBusinessObjType() == BusinessObjectType.Occasion) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://api.gaana.com/home/occasion/meta/v2/");
            stringBuilder.append(autoComplete.getOccasionUrl());
            loadOccaionPage(stringBuilder.toString());
        } else {
            if (convertToBusinessObject.getBusinessObjType() == BusinessObjectType.Tracks) {
                if ("1".equalsIgnoreCase(convertToBusinessObject.getLocationAvailability()) && "0".equalsIgnoreCase(convertToBusinessObject.getDeviceAvailability())) {
                    if (GaanaSearchManager.a().g() != null) {
                        GaanaSearchManager.a().g().a(this.mContext);
                    }
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
                    return;
                } else if ("0".equalsIgnoreCase(convertToBusinessObject.getLocationAvailability()) && "1".equalsIgnoreCase(convertToBusinessObject.getDeviceAvailability())) {
                    if (GaanaSearchManager.a().g() != null) {
                        GaanaSearchManager.a().g().a(this.mContext);
                    }
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
                    return;
                } else {
                    if (!(DownloadManager.c().e(Integer.parseInt(convertToBusinessObject.getBusinessObjId())) != DownloadStatus.DOWNLOADED || !Util.v() || DownloadManager.c().f(Integer.parseInt(convertToBusinessObject.getBusinessObjId())) || ap.a().o() || DownloadManager.c().j(convertToBusinessObject.getBusinessObjId()).booleanValue())) {
                        if (GaanaSearchManager.a().g() != null) {
                            GaanaSearchManager.a().g().a(this.mContext);
                        }
                        aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.downloaded_songs_stream_online));
                    }
                    PlayerTrack a = PlayerManager.a(GaanaApplication.getContext()).a(PlaySequenceType.CURRENT);
                    if (a == null || convertToBusinessObject == null) {
                        setRepeatOne(null, convertToBusinessObject.getBusinessObjId());
                    } else {
                        setRepeatOne(a.h(), convertToBusinessObject.getBusinessObjId());
                    }
                }
            }
            if ((!this.mAppState.isAppInOfflineMode() && Util.j(this.mContext)) || convertToBusinessObject.isLocalMedia() || Util.c(convertToBusinessObject) || isMyPlaylist(convertToBusinessObject)) {
                ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading));
                if (!autoComplete.isRecentSearch() && autoComplete.isRecommendedSearch()) {
                    GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.SEARCH_RECOMMENDED.name());
                    u.a().b("SEARCH_RECOMMENDED", "Search_Recommended_Clicks");
                } else if (GaanaSearchManager.a().m()) {
                    GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.SEARCH_VOICE.name());
                } else if (Constants.au) {
                    GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.VOICE_AUTO_SUGGEST.name());
                } else {
                    GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.SEARCH_AUTO_SUGGEST.name());
                }
                ((GaanaActivity) this.mContext).setCurrentSongSelectedView(view2);
                if ((this.mBusinessObject instanceof AutoComplete) && ((AutoComplete) this.mBusinessObject).getSectionType() == "MY_DOWNLOADS") {
                    an.a().a("click", "ac", "", "MY MUSIC", ((AutoComplete) this.mBusinessObject).getBusinessObjectId(), ((AutoComplete) this.mBusinessObject).getTitle(), "", "");
                } else if (!TextUtils.isEmpty(((AutoComplete) this.mBusinessObject).getAutoType())) {
                    an.a().a("click", "ac", this.searchText, "SEARCH", this.mBusinessObject.getBusinessObjId(), ((AutoComplete) this.mBusinessObject).getAutoType().toUpperCase(), "", "");
                }
                c.a(this.mContext).b(this.mContext, convertToBusinessObject, SOURCE_TYPE.SEARCH.ordinal());
            } else {
                if (GaanaSearchManager.a().g() != null) {
                    GaanaSearchManager.a().g().a(this.mContext);
                }
                ap.a().f(this.mContext);
                return;
            }
        }
        String englishTitle;
        if (autoComplete.isRecentSearch()) {
            englishTitle = autoComplete.getEnglishTitle();
            if ((this.mFragment instanceof ListingFragment) || (this.mFragment instanceof MyMusicSearchResultFragment)) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("ViewAll-");
                stringBuilder2.append(englishTitle);
                englishTitle = stringBuilder2.toString();
            }
            GaanaSearchManager.a().b((Activity) this.mContext, "RecentSearch", englishTitle);
            GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.RECENT_SEARCH.name());
            GaanaSearchManager.a().b(true);
        } else if (!autoComplete.isRecommendedSearch()) {
            String str;
            GaanaSearchManager.a().a(autoComplete);
            englishTitle = autoComplete.getType();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("Tap-");
            stringBuilder3.append(englishTitle);
            englishTitle = stringBuilder3.toString();
            boolean z = (this.mFragment instanceof ListingFragment) || (this.mFragment instanceof MyMusicSearchResultFragment);
            String type = autoComplete.getType();
            if (!TextUtils.isEmpty(autoComplete.getSectionType())) {
                type = autoComplete.getSectionType();
                englishTitle = autoComplete.getSectionType().equals("MY_DOWNLOADS") ? "Tap-Downloads" : autoComplete.getSectionType().equals("SEARCH_TOP_RESULT") ? "Tap-Top Result" : "Tap-Local Files";
            }
            String str2 = englishTitle;
            if (type.equalsIgnoreCase("Radio")) {
                str = ((Radio) convertToBusinessObject).getType().equalsIgnoreCase(d.c) ? "RADIO_MIRCHI" : "GAANA_RADIO";
            } else {
                str = type;
            }
            GaanaSearchManager.a().a((Activity) this.mContext, str2, autoComplete.getPosition(), autoComplete.getTitle(), z, str, autoComplete.getBusinessObjectId());
            GaanaSearchManager a2 = GaanaSearchManager.a();
            type = autoComplete.getTitle();
            String type2 = autoComplete.getType();
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append(Util.c(convertToBusinessObject.getBusinessObjType()));
            stringBuilder4.append(autoComplete.getBusinessObjectId());
            a2.a(type, type2, stringBuilder4.toString());
        }
    }

    private void loadOccaionPage(final String str) {
        com.dynamicview.c.a().a(new ag() {
            public void onOccasionResponse() {
                BaseGaanaFragment dynamicOccasionFragment = new DynamicOccasionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("OCCASION_URL", str);
                bundle.putString("OCCASION_REFRESH_INTERVAL", null);
                dynamicOccasionFragment.setArguments(bundle);
                ((GaanaActivity) SearchItemView.this.mContext).displayFragment(dynamicOccasionFragment);
            }

            public void onOccasionError() {
                aj.a().a(SearchItemView.this.mContext, SearchItemView.this.getResources().getString(R.string.error_download_no_internet));
            }
        }, str, null, false);
    }

    private void populateRadio(BusinessObject businessObject) {
        URLManager a = Constants.a(((Radio) businessObject).getType(), businessObject.getBusinessObjId(), false);
        if (a != null) {
            ((BaseActivity) this.mContext).showProgressDialog();
            i.a().a(new s() {
                public void onRetreivalComplete(BusinessObject businessObject) {
                    ((BaseActivity) SearchItemView.this.mContext).hideProgressDialog();
                    if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                        SearchItemView.this.populateListing((Radio) ((Radios) businessObject).getArrListBusinessObj().get(0));
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    ((BaseActivity) SearchItemView.this.mContext).hideProgressDialog();
                }
            }, a);
        }
    }

    private void populateListing(Radio radio) {
        this.mBusinessObject = radio;
        StringBuilder stringBuilder;
        if (radio.getType().equals(d.c)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
            stringBuilder.append(" - RadioMirchi - ");
            stringBuilder.append(radio.getEnglishName());
            ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Play", stringBuilder.toString());
            ad.a(this.mContext).a((BusinessObject) radio);
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
            stringBuilder.append(" - GaanaRadio - ");
            stringBuilder.append(radio.getEnglishName());
            ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Play", stringBuilder.toString());
            ad.a(this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", radio.getBusinessObjId()).replace("<radio_type>", radio.getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), (BusinessObject) radio);
        }
        this.mListingComponents = Constants.a(radio);
        this.mListingComponents.a((BusinessObject) radio);
        populateRadioListing(radio);
    }

    private boolean isMyPlaylist(BusinessObject businessObject) {
        return businessObject.getBusinessObjType() == BusinessObjectType.Playlists && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) businessObject);
    }

    public BusinessObject convertToBusinessObject(AutoComplete autoComplete) {
        String rawTitle = autoComplete.getRawTitle();
        String artwork = autoComplete.getArtwork();
        String rawSubtitle = autoComplete.getRawSubtitle();
        String businessObjectId = autoComplete.getBusinessObjectId();
        BusinessObject businessObject = new BusinessObject();
        switch (SearchCategory.valueOf(autoComplete.getType())) {
            case Artist:
                businessObject = new Artist();
                businessObject.setBusinessObjType(BusinessObjectType.Artists);
                ((Artist) businessObject).setArtwork(artwork);
                break;
            case Radio:
                businessObject = new Radio();
                businessObject.setBusinessObjType(BusinessObjectType.Radios);
                Radio radio = (Radio) businessObject;
                radio.setArtwork(artwork);
                radio.setType(autoComplete.getRadioType());
                break;
            case Album:
                businessObject = new Album();
                businessObject.setBusinessObjType(BusinessObjectType.Albums);
                ((Album) businessObject).setArtwork(artwork);
                break;
            case Playlist:
                businessObject = new Playlist();
                businessObject.setBusinessObjType(BusinessObjectType.Playlists);
                Playlist playlist = (Playlist) businessObject;
                playlist.setArtwork(artwork);
                playlist.setPlaylistId(businessObjectId);
                if (!TextUtils.isEmpty(rawSubtitle) && rawSubtitle.equalsIgnoreCase("My Playlist")) {
                    playlist.setCreatedbyUserId(this.mAppState.getCurrentUser().getUserProfile().getUserId());
                    break;
                }
            case Track:
                businessObject = new Track();
                businessObject.setBusinessObjType(BusinessObjectType.Tracks);
                Track track = (Track) businessObject;
                track.setStreamUrls(autoComplete.getStreamUrls());
                track.setArtwork(artwork);
                break;
            case OfflineTrack:
                businessObject = new OfflineTrack(businessObjectId, rawTitle, rawSubtitle);
                businessObject.setBusinessObjType(BusinessObjectType.Tracks);
                break;
            case Occasion:
                businessObject.setBusinessObjType(BusinessObjectType.Occasion);
                break;
        }
        businessObject.setBusinessObjId(businessObjectId);
        businessObject.setName(rawTitle);
        businessObject.setLanguage(autoComplete.getLanguage());
        businessObject.setLocalMedia(autoComplete.isLocalMedia());
        return businessObject;
    }

    public void setSearchQuery(String str) {
        this.searchText = str;
    }

    public void onPopupClicked(String str, BusinessObject businessObject) {
        downloadTrack(str, businessObject);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0030  */
    public void downloadTrack(final java.lang.String r7, com.gaana.models.BusinessObject r8) {
        /*
        r6 = this;
        r0 = com.managers.DownloadManager.c();
        r1 = java.lang.Integer.parseInt(r7);
        r0 = r0.e(r1);
        r1 = r8 instanceof com.gaana.models.Item;
        if (r1 == 0) goto L_0x0026;
    L_0x0010:
        r1 = r8;
        r1 = (com.gaana.models.Item) r1;
        r2 = r1.getEntityType();
        r3 = com.constants.c.c.c;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0026;
    L_0x001f:
        r8 = r6.populateTrackClicked(r1);
        r8 = (com.gaana.models.Tracks.Track) r8;
        goto L_0x0028;
    L_0x0026:
        r8 = (com.gaana.models.Tracks.Track) r8;
    L_0x0028:
        r1 = r6.mAppState;
        r1 = r1.isAppInOfflineMode();
        if (r1 == 0) goto L_0x0041;
    L_0x0030:
        r7 = r6.mContext;
        r7 = (com.gaana.BaseActivity) r7;
        r8 = r6.mContext;
        r0 = 2131822681; // 0x7f110859 float:1.927814E38 double:1.0532603497E-314;
        r8 = r8.getString(r0);
        r7.displayFeatureNotAvailableOfflineDialog(r8);
        return;
    L_0x0041:
        r1 = r6.mContext;
        r1 = com.utilities.Util.j(r1);
        if (r1 != 0) goto L_0x0053;
    L_0x0049:
        r7 = com.managers.ap.a();
        r8 = r6.mContext;
        r7.f(r8);
        return;
    L_0x0053:
        r1 = r6.mContext;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = r6.mContext;
        r2 = (com.gaana.BaseActivity) r2;
        r2 = r2.currentScreen;
        r3 = "Download";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r6.mContext;
        r5 = (com.gaana.BaseActivity) r5;
        r5 = r5.currentScreen;
        r4.append(r5);
        r5 = " - ";
        r4.append(r5);
        r5 = r6.mContext;
        r5 = (com.gaana.BaseActivity) r5;
        r5 = r5.currentFavpage;
        r4.append(r5);
        r5 = " - Download";
        r4.append(r5);
        r4 = r4.toString();
        r1.sendGAEvent(r2, r3, r4);
        r1 = com.gaana.analytics.MoEngage.getInstance();
        r1.reportDownload(r8);
        r1 = com.managers.DownloadManager.DownloadStatus.DOWNLOADED;
        if (r0 != r1) goto L_0x00af;
    L_0x0092:
        r7 = new com.gaana.view.item.CustomDialogView;
        r0 = r6.mContext;
        r1 = r6.mContext;
        r1 = r1.getResources();
        r2 = 2131822705; // 0x7f110871 float:1.9278189E38 double:1.0532603616E-314;
        r1 = r1.getString(r2);
        r2 = new com.gaana.view.item.SearchItemView$5;
        r2.<init>(r8);
        r7.<init>(r0, r1, r2);
        r7.show();
        goto L_0x00f4;
    L_0x00af:
        r1 = com.managers.DownloadManager.DownloadStatus.QUEUED;
        if (r0 != r1) goto L_0x00d0;
    L_0x00b3:
        r8 = new com.gaana.view.item.CustomDialogView;
        r0 = r6.mContext;
        r1 = r6.mContext;
        r1 = r1.getResources();
        r2 = 2131822713; // 0x7f110879 float:1.9278205E38 double:1.0532603655E-314;
        r1 = r1.getString(r2);
        r2 = new com.gaana.view.item.SearchItemView$6;
        r2.<init>(r7);
        r8.<init>(r0, r1, r2);
        r8.show();
        goto L_0x00f4;
    L_0x00d0:
        r1 = com.managers.DownloadManager.DownloadStatus.DOWNLOADING;
        if (r0 != r1) goto L_0x00f1;
    L_0x00d4:
        r8 = new com.gaana.view.item.CustomDialogView;
        r0 = r6.mContext;
        r1 = r6.mContext;
        r1 = r1.getResources();
        r2 = 2131822714; // 0x7f11087a float:1.9278207E38 double:1.053260366E-314;
        r1 = r1.getString(r2);
        r2 = new com.gaana.view.item.SearchItemView$7;
        r2.<init>(r7);
        r8.<init>(r0, r1, r2);
        r8.show();
        goto L_0x00f4;
    L_0x00f1:
        r6.startDownload(r8);
    L_0x00f4:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.item.SearchItemView.downloadTrack(java.lang.String, com.gaana.models.BusinessObject):void");
    }

    private void handlePlayall(BusinessObject businessObject) {
        URLManager uRLManager;
        StringBuilder stringBuilder;
        if (businessObject.getBusinessObjType() == BusinessObjectType.Artists) {
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.GenericItems);
            uRLManager.b(BusinessObjectType.Artists);
            stringBuilder = new StringBuilder();
            stringBuilder.append(com.constants.c.q);
            stringBuilder.append(businessObject.getBusinessObjId());
            uRLManager.a(stringBuilder.toString());
            uRLManager.b(Boolean.valueOf(true));
            uRLManager.a(true);
            uRLManager.f(true);
            uRLManager.a(Priority.HIGH);
            uRLManager.i(true);
            startRetrieval(uRLManager, businessObject);
        } else if (businessObject.getBusinessObjType() == BusinessObjectType.Albums) {
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.Tracks);
            uRLManager.b(BusinessObjectType.Albums);
            stringBuilder = new StringBuilder();
            stringBuilder.append(com.constants.c.s);
            stringBuilder.append(businessObject.getBusinessObjId());
            uRLManager.a(stringBuilder.toString());
            uRLManager.b(Boolean.valueOf(true));
            uRLManager.f(true);
            uRLManager.a(Priority.HIGH);
            uRLManager.i(true);
            startRetrieval(uRLManager, businessObject);
        } else if (businessObject.getBusinessObjType() == BusinessObjectType.Playlists) {
            uRLManager = new URLManager();
            uRLManager.a(BusinessObjectType.Tracks);
            uRLManager.b(BusinessObjectType.Playlists);
            stringBuilder = new StringBuilder();
            stringBuilder.append(com.constants.c.w);
            stringBuilder.append("playlist_id=");
            stringBuilder.append(businessObject.getBusinessObjId());
            stringBuilder.append("&playlist_type=");
            stringBuilder.append(((Playlist) businessObject).getPlaylistType());
            uRLManager.a(stringBuilder.toString());
            uRLManager.b(Boolean.valueOf(true));
            uRLManager.f(true);
            uRLManager.a(Priority.HIGH);
            uRLManager.i(true);
            startRetrieval(uRLManager, businessObject);
        }
    }

    public void startRetrieval(URLManager uRLManager, final BusinessObject businessObject) {
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject != null) {
                    ArrayList arrListBusinessObj = ((Tracks) businessObject).getArrListBusinessObj();
                    if (arrListBusinessObj != null && arrListBusinessObj.size() > 0) {
                        String businessObjId = businessObject.getBusinessObjId();
                        int ordinal = SOURCE_TYPE.ALBUM.ordinal();
                        if (businessObject.getBusinessObjType() == BusinessObjectType.Artists) {
                            ordinal = SOURCE_TYPE.ARTIST.ordinal();
                        } else if (businessObject.getBusinessObjType() == BusinessObjectType.Playlists) {
                            ordinal = SOURCE_TYPE.ARTIST.ordinal();
                        }
                        int i = ordinal;
                        Util.a(SearchItemView.this.mContext, SearchItemView.this);
                        PlayerManager.a(SearchItemView.this.mContext).b(businessObjId, i, businessObject.getName(), businessObject, arrListBusinessObj, SearchItemView.this.mContext);
                    }
                }
            }
        }, uRLManager);
    }
}
