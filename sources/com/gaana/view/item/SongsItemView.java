package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.c.c;
import com.constants.c.d;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.DynamicOccasionFragment;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.fragments.MoreRadioActivityFragment;
import com.fragments.PreScreenFragment;
import com.fragments.ProfileFragment;
import com.fragments.RevampedDetailListing;
import com.fragments.SearchFragment;
import com.fragments.SearchTabFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PAGE_SORCE_NAME;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.GaanaSearchManager;
import com.managers.GaanaSearchManager.SearchType;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlaySequenceType;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.n;
import com.managers.q;
import com.managers.u;
import com.models.ListingComponents;
import com.models.PlayerTrack;
import com.services.l.ba;
import com.utilities.Util;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;
import java.util.Iterator;

public class SongsItemView extends BaseItemView {
    private ImageView imgBtnAddFav;
    protected boolean isVideoListingView;
    private CrossFadeImageView mCrossFadeImageIcon;
    private String mGASectionName;
    protected String mHeader;
    public boolean mIsSongSection;
    protected boolean mItemWithoutText;
    protected boolean mLightsOn;
    public ArrayList<?> mSongsListBusinessObject;
    private int position;
    protected String sourceName;
    private TextView tvAlbumName;
    private TextView tvSongName;
    private String uniqueID;

    public void setGAData(String str, String str2, int i) {
        this.mGASectionName = str;
        this.mHeader = str2;
        this.position = i;
    }

    public void setItemWithoutText(boolean z) {
        this.mItemWithoutText = z;
    }

    public void setLightsOn(boolean z) {
        this.mLightsOn = z;
    }

    public void setVideoListingView(boolean z) {
        this.isVideoListingView = z;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setGASectionName(String str) {
        this.mGASectionName = str;
    }

    public SongsItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mSongsListBusinessObject = null;
        this.mIsSongSection = false;
        this.position = -1;
        this.mHeader = "";
        this.mGASectionName = "";
        this.mItemWithoutText = false;
        this.mLightsOn = false;
        this.mLayoutId = R.layout.view_item_song;
        if (this.mContext instanceof BaseActivity) {
            ((BaseActivity) this.mContext).currentItem = "Song";
        }
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutId, view, viewGroup);
        }
        return getDataFilledView(super.getPoplatedView(view, businessObject), businessObject);
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup, boolean z) {
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutId, view, viewGroup);
        }
        view = getDataFilledView(super.getPoplatedView(view, businessObject), businessObject);
        view.setClickable(false);
        return view;
    }

    private View getDataFilledView(View view, BusinessObject businessObject) {
        BusinessObject businessObject2 = (Track) businessObject;
        if (businessObject2.isPlaying() != null && businessObject2.isPlaying().booleanValue() && "Player Queue".equalsIgnoreCase(this.mAppState.getListingComponents().d())) {
            view.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
        }
        this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        if (businessObject2.getParentBusinessObjType() != BusinessObjectType.Albums) {
            this.mCrossFadeImageIcon.setVisibility(0);
            this.mCrossFadeImageIcon.bindImage(businessObject2.getArtwork(), this.mAppState.isAppInOfflineMode());
        } else {
            this.mCrossFadeImageIcon.setVisibility(8);
        }
        this.tvSongName = (TextView) view.findViewById(R.id.tvSongName);
        this.tvAlbumName = (TextView) view.findViewById(R.id.tvAlbumName);
        this.imgBtnAddFav = (ImageView) view.findViewById(R.id.favBtnSongView);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.favPrgSongView);
        this.tvSongName.setText(businessObject2.getTrackTitle());
        this.tvAlbumName.setText(businessObject2.getArtistNames());
        view.findViewById(R.id.clickoptionImage).setTag(businessObject);
        view.findViewById(R.id.clickoptionImage).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SongsItemView.this.showOptionMenu(view);
            }
        });
        int a = Util.a(businessObject2.getBusinessObjId());
        if ((!this.mAppState.isAppInOfflineMode() || DownloadManager.c().l(a).booleanValue()) && ap.a().b(businessObject2)) {
            TypedValue typedValue;
            PlayerTrack i = PlayerManager.a(this.mContext).i();
            if (i == null || i.b() == null || !businessObject2.getBusinessObjId().equalsIgnoreCase(i.h())) {
                typedValue = new TypedValue();
                this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
                this.tvSongName.setTextColor(typedValue.data);
            } else {
                this.tvSongName.setTextColor(this.mContext.getResources().getColor(R.color.gaana_orange_text));
            }
            typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.second_line_color, typedValue, true);
            this.tvAlbumName.setTextColor(typedValue.data);
            this.imgBtnAddFav.setClickable(true);
        } else {
            this.tvSongName.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
            this.tvAlbumName.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
            this.imgBtnAddFav.setClickable(false);
        }
        if (businessObject2.isMostPopular() && this.mAppState.getListingComponents() != null) {
            businessObject = this.mAppState.getListingComponents().a();
            if (!(businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getBusinessObjType() != BusinessObjectType.Albums)) {
                a = businessObject.getArrListBusinessObj().size();
            }
        }
        if (this.mFragment instanceof ProfileFragment) {
            progressBar.setVisibility(4);
            this.imgBtnAddFav.setVisibility(4);
            this.imgBtnAddFav.setClickable(false);
        } else {
            progressBar.setVisibility(8);
            this.imgBtnAddFav.setVisibility(0);
            TypedArray obtainStyledAttributes;
            Drawable drawable;
            if (n.e(businessObject2)) {
                progressBar.setVisibility(0);
                this.imgBtnAddFav.setVisibility(4);
            } else if (businessObject2.isFavorite().booleanValue()) {
                this.imgBtnAddFav.setImageDrawable(null);
                new int[1][0] = R.attr.moreoptions_favorited;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(51, -1));
                obtainStyledAttributes.recycle();
                this.imgBtnAddFav.setImageDrawable(drawable);
            } else {
                this.imgBtnAddFav.setImageDrawable(null);
                new int[1][0] = R.attr.download_button_paused;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
                obtainStyledAttributes.recycle();
                this.imgBtnAddFav.setImageDrawable(drawable);
            }
        }
        View findViewById = view.findViewById(R.id.watchVideo);
        if (TextUtils.isEmpty(businessObject2.getVideoUrl())) {
            findViewById.setVisibility(8);
        } else {
            findViewById.setVisibility(0);
            findViewById.setTag(businessObject2);
            findViewById.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ((Track) view.getTag()).getVideoUrl();
                }
            });
        }
        return view;
    }

    public String getUniqueID() {
        return this.uniqueID;
    }

    public void setUniqueID(String str) {
        this.uniqueID = str;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public void setSourceName(String str) {
        this.sourceName = str;
    }

    public void onClick(View view) {
        BusinessObject businessObject = (BusinessObject) view.getTag();
        ((GaanaActivity) this.mContext).setVideoItemPlayed(this.isVideoListingView);
        if (businessObject instanceof Item) {
            Item item = (Item) businessObject;
            if (item.getEntityType().equals(c.c)) {
                businessObject = (Track) populateTrackClicked(item);
            } else if (item.getEntityType().equals(c.b)) {
                businessObject = (Album) populateAlbumClicked(item);
            } else if (item.getEntityType().equals(c.a)) {
                businessObject = (Playlist) populatePlaylistClicked(item);
            } else if (item.getEntityType().equals(d.d) || item.getEntityType().equals(d.c)) {
                businessObject = (Radio) populateRadioClicked(item);
            } else if (item.getEntityType().equals(c.d)) {
                businessObject = (Artist) populateArtistClicked(item);
            } else if (item.getEntityType().equals(c.f)) {
                businessObject = (YouTubeVideo) populateVideoClicked(item);
            }
            if (this.mFragment instanceof SearchTabFragment) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.position);
                stringBuilder.append(" - ");
                stringBuilder.append(item.getEntityType());
                stringBuilder.append(" - ");
                stringBuilder.append(businessObject.getBusinessObjId());
                ((BaseActivity) this.mContext).sendGAEvent("Online-SearchScreen", "TrendingSearch", stringBuilder.toString());
                GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.TRENDING_SEARCH.name());
                GaanaSearchManager.a().b(true);
            }
        } else if (businessObject instanceof OfflineTrack) {
            businessObject = (Track) DownloadManager.c().a(businessObject.getBusinessObjId(), true);
        }
        BusinessObject businessObject2;
        StringBuilder stringBuilder2;
        String stringBuilder3;
        StringBuilder stringBuilder4;
        if (businessObject instanceof Track) {
            Util.a(this.mContext, (Track) businessObject, view, new ba() {
                public void onPlaySong(View view, Track track) {
                    SongsItemView.this.playTrackClickedSong(view, track);
                }
            });
        } else if (businessObject instanceof Playlist) {
            businessObject2 = (Playlist) businessObject;
            if (TextUtils.isEmpty(businessObject2.getChannelPageAdCode())) {
                Constants.i = false;
                Constants.j = "";
            } else {
                Constants.i = true;
                Constants.j = businessObject2.getChannelPageAdCode();
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.mHeader);
            stringBuilder2.append(" click ");
            stringBuilder3 = stringBuilder2.toString();
            stringBuilder4 = new StringBuilder();
            stringBuilder4.append("Position ");
            stringBuilder4.append(this.position);
            stringBuilder4.append(" - PlayList - ");
            stringBuilder4.append(businessObject.getBusinessObjId());
            ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder3, stringBuilder4.toString());
            this.mAppState.setPlayoutSectionName(this.mGASectionName);
            this.mListingComponents = Constants.e();
            this.mListingComponents.a(businessObject2);
            if (businessObject2.isGaanaSpecial()) {
                populateSpecialGaanaListing(businessObject2);
            } else {
                populatePlaylistListing(businessObject2);
            }
        } else if (businessObject instanceof Album) {
            businessObject2 = (Album) businessObject;
            if (!businessObject2.isLocalMedia()) {
                if ("1".equalsIgnoreCase(businessObject2.getLocationAvailability()) && "0".equalsIgnoreCase(businessObject2.getDeviceAvailability())) {
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
                    return;
                } else if ("0".equalsIgnoreCase(businessObject2.getLocationAvailability()) && "1".equalsIgnoreCase(businessObject2.getDeviceAvailability())) {
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
                    return;
                } else if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().b(businessObject2).booleanValue()) {
                    ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This album");
                    return;
                } else if (!Util.j(this.mContext) && !DownloadManager.c().b(businessObject2).booleanValue()) {
                    ap.a().f(this.mContext);
                    return;
                } else if ((this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) && !ap.a().a(businessObject2, null)) {
                    aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.toast_subscription_expired));
                    return;
                }
            }
            if (TextUtils.isEmpty(businessObject2.getChannelPageAdCode())) {
                Constants.i = false;
                Constants.j = "";
            } else {
                Constants.i = true;
                Constants.j = businessObject2.getChannelPageAdCode();
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.mHeader);
            stringBuilder2.append(" click ");
            stringBuilder3 = stringBuilder2.toString();
            stringBuilder4 = new StringBuilder();
            stringBuilder4.append("Position ");
            stringBuilder4.append(this.position);
            stringBuilder4.append(" - Album - ");
            stringBuilder4.append(businessObject.getBusinessObjId());
            ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder3, stringBuilder4.toString());
            this.mAppState.setPlayoutSectionName(this.mGASectionName);
            populateAlbumListing(businessObject2);
        } else if (businessObject instanceof Radio) {
            businessObject2 = (Radio) businessObject;
            if (this.mAppState.isAppInOfflineMode()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This radio");
            } else if (Util.j(this.mContext)) {
                this.mBusinessObject = businessObject2;
                if (businessObject2.getType().equals(d.c)) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(this.mHeader);
                    stringBuilder2.append(" click ");
                    stringBuilder3 = stringBuilder2.toString();
                    stringBuilder4 = new StringBuilder();
                    stringBuilder4.append("Position ");
                    stringBuilder4.append(this.position);
                    stringBuilder4.append(" - RadioMirchi - ");
                    stringBuilder4.append(businessObject.getBusinessObjId());
                    ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder3, stringBuilder4.toString());
                    this.mAppState.setPlayoutSectionName(this.mGASectionName);
                    ad.a(this.mContext).a(businessObject2);
                } else {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(this.mHeader);
                    stringBuilder2.append(" click ");
                    stringBuilder3 = stringBuilder2.toString();
                    stringBuilder4 = new StringBuilder();
                    stringBuilder4.append("Position ");
                    stringBuilder4.append(this.position);
                    stringBuilder4.append(" - GaanaRadio - ");
                    stringBuilder4.append(businessObject.getBusinessObjId());
                    ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder3, stringBuilder4.toString());
                    this.mAppState.setPlayoutSectionName(this.mGASectionName);
                    ad.a(this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", businessObject2.getBusinessObjId()).replace("<radio_type>", businessObject2.getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), businessObject2);
                }
                this.mListingComponents = Constants.a((Radio) businessObject2);
                this.mListingComponents.a(businessObject2);
                populateRadioListing(businessObject2);
            } else {
                ap.a().f(this.mContext);
            }
        } else if (businessObject instanceof Artist) {
            Artist artist = (Artist) businessObject;
            this.mListingComponents = Constants.a("", artist.isLocalMedia());
            this.mAppState.setListingComponents(this.mListingComponents);
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.mHeader);
            stringBuilder2.append(" click ");
            stringBuilder3 = stringBuilder2.toString();
            stringBuilder4 = new StringBuilder();
            stringBuilder4.append("Position ");
            stringBuilder4.append(this.position);
            stringBuilder4.append(" - Artist - ");
            stringBuilder4.append(businessObject.getBusinessObjId());
            ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder3, stringBuilder4.toString());
            this.mAppState.setPlayoutSectionName(this.mGASectionName);
            populateArtistListing(artist);
        } else if (businessObject instanceof YouTubeVideo) {
            StringBuilder stringBuilder5 = new StringBuilder();
            stringBuilder5.append(this.mHeader);
            stringBuilder5.append(" click ");
            String stringBuilder6 = stringBuilder5.toString();
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Position ");
            stringBuilder2.append(this.position);
            stringBuilder2.append(" - Video - ");
            stringBuilder2.append(businessObject.getBusinessObjId());
            ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder6, stringBuilder2.toString());
            YouTubeVideo youTubeVideo = (YouTubeVideo) businessObject;
            launchYouTubePlayer(youTubeVideo.c(), youTubeVideo.a(), businessObject, youTubeVideo.e());
        }
    }

    private void playTrackClickedSong(View view, Track track) {
        ArrayList arrayList;
        int indexOf;
        View view2 = view;
        Track track2 = track;
        if (this.mIsSongSection) {
            this.mAppState.setCurrentBusObjInListView(this.mSongsListBusinessObject);
        }
        if (this.mAppState.getCurrentBusObjInListView() != null) {
            arrayList = new ArrayList();
            ArrayList currentBusObjInListView = this.mAppState.getCurrentBusObjInListView();
            if (currentBusObjInListView != null && currentBusObjInListView.size() > 0) {
                arrayList.addAll(currentBusObjInListView);
            }
            indexOf = arrayList.indexOf(track2);
            if ((this.mFragment instanceof DynamicHomeFragment) && !this.isPlayerQueue) {
                an.a().a("click", "en", getUniqueID(), "HOME", track.getBusinessObjId(), "PLAY", String.valueOf(this.position - 1), "");
            } else if ((this.mFragment instanceof DynamicOccasionFragment) && !this.isPlayerQueue) {
                an.a().a("click", "ac", track.getAlbumId(), track.getLanguage(), track.getBusinessObjId(), "play", String.valueOf(this.position - 1), "");
            } else if (this.mFragment instanceof RevampedDetailListing) {
                if (((RevampedDetailListing) this.mFragment).c.contains("ArtistDetailScreen")) {
                    an.a().a("click", "ac", this.mBusinessObject.getBusinessObjId(), "Song", track.getBusinessObjId(), "play", String.valueOf(indexOf), "");
                } else {
                    an.a().a("click", "ac", track.getAlbumId(), "", track.getBusinessObjId(), "play", String.valueOf(indexOf), "");
                }
            }
        } else {
            arrayList = null;
            indexOf = 0;
        }
        if (this.isVideoListingView && PlayerManager.a(this.mContext).c(track2) && (this.mContext instanceof GaanaActivity)) {
            ((GaanaActivity) this.mContext).launchExpandedPlayer();
        }
        if (track.isLocalMedia()) {
            setPlayerQueueAndPlay(view2, track2, indexOf, arrayList);
        } else {
            checkOfflineAndplayTrack(view2, track2, indexOf, arrayList);
        }
        GaanaApplication.getInstance().setShowCFSongsToastFlag(false);
    }

    public void checkOfflineAndplayTrack(View view, Track track, int i, ArrayList<BusinessObject> arrayList) {
        if ("1".equalsIgnoreCase(track.getLocationAvailability()) && "0".equalsIgnoreCase(track.getDeviceAvailability())) {
            ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
        } else if ("0".equalsIgnoreCase(track.getLocationAvailability()) && "1".equalsIgnoreCase(track.getDeviceAvailability())) {
            ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
        } else if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This song");
        } else if (!Util.j(this.mContext) && !ap.a().j() && !DownloadManager.c().a(track).booleanValue() && !DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue()) {
            ap.a().f(this.mContext);
        } else if ((!this.mAppState.isAppInOfflineMode() && Util.j(this.mContext)) || ap.a().j() || track.isFreeDownloadEnabled()) {
            StringBuilder stringBuilder;
            String replace;
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(track.getBusinessObjId()));
            if (ap.a().f() && !ap.a().h() && e == DownloadStatus.DOWNLOADED && (ap.a().o() || DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue())) {
                aj.a().a(this.mContext, "Gaana Plus Mini Setup Incomplete. Your downloaded tracks will stream online");
            }
            if (!(e != DownloadStatus.DOWNLOADED || track.isFreeDownloadEnabled() || ap.a().o() || DownloadManager.c().j(track.getBusinessObjId()).booleanValue())) {
                aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.downloaded_songs_stream_online));
            }
            if (this.mAppState.getListingComponents() == null) {
                this.mAppState.setListingComponents(new ListingComponents());
            }
            if (this.mAppState.getListingComponents().f() == SearchType.Radio) {
                if (!(this.mFragment instanceof AlbumDetailsMaterialListing)) {
                    super.onClick(null);
                }
                if (!(this.mFragment instanceof GaanaSpecialDetailsMaterialListing)) {
                    super.onClick(null);
                }
                aj a = aj.a();
                Context context = this.mContext;
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.mContext.getString(R.string.start_radio_for_songs));
                stringBuilder.append(": ");
                stringBuilder.append(track.getName());
                a.a(context, stringBuilder.toString());
                replace = "https://api.gaana.com/radio.php?type=radio&subtype=songredios&track_id=<track_id>&page=1&limit=10".replace("<track_id>", track.getBusinessObjId());
                ad.a(this.mContext).a(true);
                ad.a(this.mContext).a(track);
                ad.a(this.mContext).a(replace, SOURCE_TYPE.RADIO_SEARCH_SONG.ordinal(), (BusinessObject) track);
            } else {
                setPlayerQueueAndPlay(view, track, i, arrayList);
            }
            boolean z = false;
            String playoutSectionName = GaanaApplication.getInstance().getPlayoutSectionName();
            if (!TextUtils.isEmpty(playoutSectionName) && playoutSectionName.equalsIgnoreCase("MADE_FOR_YOU")) {
                z = true;
            }
            BaseActivity baseActivity;
            if (TextUtils.isEmpty(((BaseActivity) this.mContext).currentFavpage)) {
                baseActivity = (BaseActivity) this.mContext;
                replace = z ? "Made For You" : ((BaseActivity) this.mContext).currentScreen;
                stringBuilder = new StringBuilder();
                stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder.append(" - Play");
                baseActivity.sendGAEvent(replace, "Play", stringBuilder.toString());
            } else {
                baseActivity = (BaseActivity) this.mContext;
                replace = z ? "Made For You" : ((BaseActivity) this.mContext).currentScreen;
                stringBuilder = new StringBuilder();
                stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder.append(" - ");
                stringBuilder.append(((BaseActivity) this.mContext).currentFavpage);
                stringBuilder.append(" - Play");
                baseActivity.sendGAEvent(replace, "Play", stringBuilder.toString());
            }
        } else {
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.toast_subscription_expired));
        }
    }

    public void setPlayerQueueAndPlay(View view, Track track, int i, ArrayList<BusinessObject> arrayList) {
        if (track != null && track.isLocalMedia() && Constants.cY) {
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.err_local_songs_party));
            return;
        }
        PlayerTrack a = PlayerManager.a(GaanaApplication.getContext()).a(PlaySequenceType.CURRENT);
        if (!(a == null || a.b() == null || track == null)) {
            ((GaanaActivity) this.mContext).setRepeatOne(a.h(), track.getBusinessObjId());
        }
        PlayerTrack playerTrack = null;
        if (this.isPlayerQueue) {
            int i2 = 0;
            while (i2 < PlayerManager.a(this.mContext).n().size()) {
                if (track.getBusinessObjId().equals(((PlayerTrack) PlayerManager.a(this.mContext).n().get(i2)).h())) {
                    playerTrack = (PlayerTrack) PlayerManager.a(this.mContext).n().get(i2);
                    break;
                }
                i2++;
            }
            i2 = 0;
            if (i2 > PlayerManager.a(this.mContext).s()) {
                u.a().a("PlayerQueue", "Track Clicked", "Up Next");
            } else if (PlayerManager.a(this.mContext).s() - i2 > 100) {
                u.a().a("PlayerQueue", "Track Clicked", "History");
            } else {
                u.a().a("PlayerQueue", "Track Clicked", "History Last 100");
            }
            if (playerTrack != null) {
                playerTrack.c(false);
            }
            if (PlayerManager.a(this.mContext).E() != null) {
                PlayerManager.a(this.mContext).j(false);
            }
            play(playerTrack);
        } else {
            if (!((this.mFragment instanceof AlbumDetailsMaterialListing) || (this.mFragment instanceof GaanaSpecialDetailsMaterialListing) || (this.mFragment instanceof RevampedDetailListing))) {
                super.onClick(null);
            }
            a = com.logging.d.a().a(this.mFragment, (BusinessObject) track);
            ArrayList arrayList2;
            if ((this.mFragment instanceof SearchFragment) || (this.mFragment instanceof SearchTabFragment)) {
                a = new PlayerTrack(track, track.getBusinessObjId(), SOURCE_TYPE.SEARCH.ordinal(), track.getEnglishName());
                a.f(this.mFragment.getPageName());
                arrayList2 = new ArrayList();
                arrayList2.add(a);
                PlayerManager.a(this.mContext).b(arrayList2, a, 0);
            } else if ((this.mFragment instanceof DynamicHomeFragment) || (this.mFragment instanceof DynamicOccasionFragment) || (this.mFragment instanceof MoreRadioActivityFragment)) {
                ArrayList arrayList3;
                StringBuilder stringBuilder;
                String stringBuilder2;
                StringBuilder stringBuilder3;
                BaseActivity baseActivity;
                String str;
                if (view.getId() == R.id.play_icon) {
                    if (this.mFragment instanceof DynamicHomeFragment) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(this.mHeader);
                        stringBuilder.append("_playclick ");
                        stringBuilder2 = stringBuilder.toString();
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Position ");
                        stringBuilder3.append(this.position);
                        stringBuilder3.append(" - Track - ");
                        stringBuilder3.append(track.getBusinessObjId());
                        ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder2, stringBuilder3.toString());
                    } else {
                        baseActivity = (BaseActivity) this.mContext;
                        str = ((BaseActivity) this.mContext).currentScreen;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(this.mHeader);
                        stringBuilder.append("_playclick ");
                        stringBuilder2 = stringBuilder.toString();
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Position ");
                        stringBuilder3.append(this.position);
                        stringBuilder3.append(" - Track - ");
                        stringBuilder3.append(track.getBusinessObjId());
                        baseActivity.sendGAEvent(str, stringBuilder2, stringBuilder3.toString());
                    }
                } else if (this.mFragment instanceof DynamicHomeFragment) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(this.mHeader);
                    stringBuilder.append(" click ");
                    stringBuilder2 = stringBuilder.toString();
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Position ");
                    stringBuilder3.append(this.position);
                    stringBuilder3.append(" - Track - ");
                    stringBuilder3.append(track.getBusinessObjId());
                    ((BaseActivity) this.mContext).sendGAEvent("Browse", stringBuilder2, stringBuilder3.toString());
                } else {
                    baseActivity = (BaseActivity) this.mContext;
                    str = ((BaseActivity) this.mContext).currentScreen;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(this.mHeader);
                    stringBuilder.append(" click ");
                    stringBuilder2 = stringBuilder.toString();
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Position ");
                    stringBuilder3.append(this.position);
                    stringBuilder3.append(" - Track - ");
                    stringBuilder3.append(track.getBusinessObjId());
                    baseActivity.sendGAEvent(str, stringBuilder2, stringBuilder3.toString());
                }
                this.mAppState.setPlayoutSectionName(this.mGASectionName);
                a = new PlayerTrack(track, track.getBusinessObjId(), SOURCE_TYPE.OTHER.ordinal(), track.getEnglishName());
                a.f(this.mFragment.getPageName());
                arrayList2 = this.mAppState.getCurrentBusObjInListView();
                if (arrayList3 == null || arrayList2 == null || arrayList2.size() <= 0 || !(arrayList2.get(0) instanceof Item)) {
                    arrayList3 = (arrayList2 == null || arrayList2.size() <= 0 || !(arrayList2.get(0) instanceof Item)) ? arrayList2 : new ArrayList();
                }
                arrayList2 = com.logging.d.a().a(this.mFragment, arrayList3);
                if (arrayList2 != null) {
                    PlayerManager.a(this.mContext).b(arrayList2, a, 0);
                }
            } else {
                if (this.mFragment instanceof PreScreenFragment) {
                    a.f(PAGE_SORCE_NAME.FOR_YOU.name());
                    a.d(getSourceName());
                }
                arrayList2 = new ArrayList();
                ArrayList currentBusObjInListView = this.mAppState.getCurrentBusObjInListView();
                if (arrayList3 != null && currentBusObjInListView != null && currentBusObjInListView.size() > 0 && (currentBusObjInListView.get(0) instanceof Item)) {
                    arrayList2 = arrayList3;
                } else if (currentBusObjInListView != null && currentBusObjInListView.size() > 0 && (currentBusObjInListView.get(0) instanceof Item)) {
                    arrayList2.addAll(currentBusObjInListView);
                } else if (currentBusObjInListView != null) {
                    arrayList2 = currentBusObjInListView;
                }
                if (!(arrayList2 == null || checkForContains(arrayList2, track))) {
                    arrayList2.add(track);
                }
                arrayList2 = com.logging.d.a().a(this.mFragment, arrayList2);
                if (arrayList2 != null) {
                    PlayerManager.a(this.mContext).b(arrayList2, a, 0);
                }
            }
            PlayerManager.a(this.mContext).g(true);
            play(a);
            PlayerManager.a(this.mContext).g(false);
        }
    }

    public void play(PlayerTrack playerTrack) {
        if (!(!Constants.t() || Constants.U || playerTrack == null || playerTrack.b() == null || playerTrack.b().getBusinessObjId() == null || DownloadManager.c().e(Integer.parseInt(playerTrack.b().getBusinessObjId())) != DownloadStatus.DOWNLOADED)) {
            Constants.U = true;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    Constants.U = false;
                    new DownloadSyncPopupItemView(SongsItemView.this.mContext).showDownloadSyncWelcomeScreenDialog();
                }
            }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
        if (!(playerTrack == null || playerTrack.b() == null || playerTrack.b().getEnglishName() == null || TextUtils.isEmpty(playerTrack.b().getEnglishName()))) {
            ArrayList artists = playerTrack.b().getArtists();
            if (artists != null) {
                for (int i = 0; i < artists.size(); i++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("listen:artist:");
                    stringBuilder.append(((Track.Artist) artists.get(i)).name);
                    q.a().a("ua", stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("play:song:");
                    stringBuilder.append(playerTrack.b().getEnglishName());
                    stringBuilder.append(":album:");
                    stringBuilder.append(playerTrack.b().getAlbumTitle());
                    stringBuilder.append(":artist:");
                    stringBuilder.append(((Track.Artist) artists.get(i)).name);
                    q.a().a("ua", stringBuilder.toString());
                }
            }
        }
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
