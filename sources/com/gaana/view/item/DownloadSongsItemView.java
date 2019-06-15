package com.gaana.view.item;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.Constants;
import com.constants.Constants.VIEW_SIZE;
import com.constants.c.c;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.f;
import com.exoplayer2.ui.VideoPlayerAutoPlayView;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.ArtistDetailsMaterialListing;
import com.fragments.ArtistFragment;
import com.fragments.BaseGaanaFragment;
import com.fragments.CuratedDownloadSuggestionFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.GaanaEducativeFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.fragments.ListingFragment;
import com.fragments.MyMusicItemFragment;
import com.fragments.PlayerFragmentV2;
import com.fragments.PlayerFragmentV4;
import com.fragments.RevampedDetailListing;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.view.PulsatorView;
import com.gaana.view.item.BaseItemView.CuratedDownloadSongSelectionHolder;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.gaanavideo.LifecycleAwareVideoView;
import com.google.gson.internal.LinkedTreeMap;
import com.i.e.b;
import com.library.controls.CrossFadeImageView;
import com.library.controls.RoundedCornerImageView;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.TrackSelectionForDownload;
import com.managers.TrackSelectionForDownload.DownloadSelectionType;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.managers.al;
import com.managers.an;
import com.managers.ap;
import com.managers.i;
import com.managers.j;
import com.managers.j.a;
import com.managers.s;
import com.managers.u;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.PlayerStatus;
import com.player_framework.PlayerStatus.PlayerStates;
import com.player_framework.o;
import com.services.d;
import com.services.l.am;
import com.services.l.ay;
import com.services.l.az;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;
import com.views.RateTextCircularProgressBar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class DownloadSongsItemView extends SongsItemView implements DownloadPopupListener, a {
    private static RateTextCircularProgressBar rateTextCircularProgressBarPlayer;
    private HashMap<Integer, VideoPlayerAutoPlayView> autoPlayViewsHashMap;
    private ImageView clickoptionImage;
    public String currentSongBlinkingTrackId;
    private ImageView downloadImage;
    private PulsatorView downloadPulse;
    boolean download_enabled;
    ImageView download_image;
    private boolean isCuratedDownloadItem;
    private CrossFadeImageView mCrossFadeImageIcon;
    private FrameLayout mCrossFadeImageIconLyt;
    private DownloadSelectionType mDownloadSelectionType;
    private ImageView mThumbnailRightTopIndicator;
    private am onSelectAllStatusChangeListener;
    private ImageView playerQueueFav;
    private double random;
    private RateTextCircularProgressBar rateTextCircularProgressBar;
    private TextView tvSubtitle;
    private TextView tvTitle;

    public static class AddMoreSongsItemHolder extends ViewHolder implements az {
        public ImageView addText;
        public ImageView clickoptionImage;
        public ImageView downloadImage;
        public PulsatorView downloadPulse;
        public CrossFadeImageView mCrossFadeImageIcon;
        public ProgressBar progressBar;
        public TextView tvSubtitle;
        public TextView tvTitle;

        public void onItemClear(int i) {
        }

        public void onItemSelected() {
        }

        public AddMoreSongsItemHolder(View view) {
            super(view);
            this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.f38download.item.img.thumb);
            this.tvTitle = (TextView) view.findViewById(R.id.f43download.item.tv.trackname);
            this.tvSubtitle = (TextView) view.findViewById(R.id.f40download.item.tv.genere);
            this.progressBar = (ProgressBar) view.findViewById(R.id.f34download.item.ProgressBar);
            this.addText = (ImageView) view.findViewById(R.id.addText);
        }
    }

    public static class AlbumDetailItemHolder extends ViewHolder implements az {
        public ImageView clickoptionImage;
        public FrameLayout crossFadeImageViewLyt;
        public View divider;
        public ImageView downloadImage;
        public PulsatorView downloadPulse;
        public boolean isFromCuratedDialog = false;
        public CrossFadeImageView mCrossFadeImageIcon;
        public ImageView mThumbnailRightTopIndicator;
        public ImageView playerQueueFav;
        public View playerQueueSeekerBg;
        public ProgressBar progressBar;
        public TextView tvSubtitle;
        public TextView tvTitle;

        public AlbumDetailItemHolder(View view) {
            super(view);
            this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.f38download.item.img.thumb);
            this.tvTitle = (TextView) view.findViewById(R.id.f43download.item.tv.trackname);
            this.tvSubtitle = (TextView) view.findViewById(R.id.f40download.item.tv.genere);
            this.progressBar = (ProgressBar) view.findViewById(R.id.f34download.item.ProgressBar);
            this.downloadImage = (ImageView) view.findViewById(R.id.f37download.item.img.download);
            this.clickoptionImage = (ImageView) view.findViewById(R.id.clickoptionImage);
            this.downloadPulse = (PulsatorView) view.findViewById(R.id.downloadPulse);
            this.playerQueueFav = (ImageView) view.findViewById(R.id.player_queue_fav);
            this.playerQueueSeekerBg = view.findViewById(R.id.playerQueueSeekerBg);
            this.divider = view.findViewById(R.id.item_divider);
            this.mThumbnailRightTopIndicator = (ImageView) view.findViewById(R.id.indicatorIconRightTop);
            this.crossFadeImageViewLyt = (FrameLayout) view.findViewById(R.id.f39download.item.img.thumb.container);
        }

        public void onItemSelected() {
            if (this.itemView != null) {
                BaseGaanaFragment currentFragment = ((GaanaActivity) this.itemView.getContext()).getCurrentFragment();
                if (currentFragment instanceof AlbumDetailsMaterialListing) {
                    ((AlbumDetailsMaterialListing) currentFragment).a(false);
                }
            }
        }

        public void onItemClear(int i) {
            if (this.itemView != null) {
                BaseGaanaFragment currentFragment = ((GaanaActivity) this.itemView.getContext()).getCurrentFragment();
                if (currentFragment instanceof AlbumDetailsMaterialListing) {
                    ((AlbumDetailsMaterialListing) currentFragment).a(true);
                }
            }
        }
    }

    public static class VideoItemViewHolder extends ViewHolder {
        public TextView albumArtistName;
        public ImageView downloadImage;
        public ImageView menuIcon;
        public TextView trackName;
        public ImageView videoFeedIcon;
        public VideoPlayerAutoPlayView videoPlayerAutoPlayView;

        public VideoItemViewHolder(View view) {
            super(view);
            this.videoPlayerAutoPlayView = (VideoPlayerAutoPlayView) view.findViewById(R.id.video_autoplay_view);
            this.trackName = (TextView) view.findViewById(R.id.track_name);
            this.albumArtistName = (TextView) view.findViewById(R.id.album_artist_name);
            this.videoFeedIcon = (ImageView) view.findViewById(R.id.video_feed_icon);
            this.menuIcon = (ImageView) view.findViewById(R.id.clickoptionImage);
            this.downloadImage = (ImageView) view.findViewById(R.id.f37download.item.img.download);
        }
    }

    public DownloadSongsItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.isCuratedDownloadItem = false;
        this.download_enabled = false;
        this.currentSongBlinkingTrackId = "";
        this.random = Math.random();
        this.mLayoutId = R.layout.view_item_download;
        ((BaseActivity) this.mContext).currentItem = "Song";
        j.a(this.mContext).a(this);
        setWillNotDraw(false);
    }

    public DownloadSongsItemView(Context context, BaseGaanaFragment baseGaanaFragment, boolean z) {
        super(context, baseGaanaFragment);
        this.isCuratedDownloadItem = false;
        this.download_enabled = false;
        this.currentSongBlinkingTrackId = "";
        this.random = Math.random();
        this.mLayoutId = R.layout.view_item_download;
        ((BaseActivity) this.mContext).currentItem = "Song";
        this.isPlayerQueue = z;
        j.a(this.mContext).a(this);
        setWillNotDraw(false);
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutId, view, viewGroup);
        }
        view = super.getPoplatedView(view, businessObject);
        j.a(this.mContext).a(this);
        return getDataFilledView(view, businessObject);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        this.mView = viewHolder.itemView;
        this.mView = super.getPoplatedView(this.mView, businessObject);
        j.a(this.mContext).a(this);
        if (viewHolder instanceof VideoItemViewHolder) {
            return getVideoDataFilledView(viewHolder, businessObject);
        }
        return getDataFilledView(viewHolder, businessObject);
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup, boolean z) {
        this.isPlayerQueue = z;
        j.a(this.mContext).a(this);
        return getPoplatedView(view, businessObject, viewGroup);
    }

    public String getAlbumName(Item item) {
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        String str = "";
        if (arrayList == null) {
            return str;
        }
        int size = arrayList.size();
        String str2 = str;
        for (int i = 0; i < size; i++) {
            if (((EntityInfo) arrayList.get(i)).getKey().equals("album")) {
                if (((EntityInfo) arrayList.get(i)).getValue() instanceof String) {
                    str2 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                } else if (((EntityInfo) arrayList.get(i)).getValue() instanceof ArrayList) {
                    str2 = Constants.a((String) ((LinkedTreeMap) ((ArrayList) ((EntityInfo) arrayList.get(i)).getValue()).get(0)).get("name"), item.getLanguage());
                }
            }
        }
        return str2;
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
        for (int i = 0; i < size; i++) {
            if (((EntityInfo) arrayList.get(i)).getKey().equals("artist")) {
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
        }
        return str2;
    }

    public void setAutoPlayHashMap(HashMap<Integer, VideoPlayerAutoPlayView> hashMap) {
        this.autoPlayViewsHashMap = hashMap;
    }

    private void startPauseAsPerVisibility(VideoPlayerAutoPlayView videoPlayerAutoPlayView) {
        Rect rect = new Rect();
        videoPlayerAutoPlayView.getGlobalVisibleRect(rect);
        if (rect.intersect(new Rect(0, 0, d.a().b(), d.a().c()))) {
            videoPlayerAutoPlayView.g();
        } else {
            videoPlayerAutoPlayView.i();
        }
    }

    public View getVideoDataFilledView(ViewHolder viewHolder, final BusinessObject businessObject) {
        VideoItemViewHolder videoItemViewHolder = (VideoItemViewHolder) viewHolder;
        final BusinessObject businessObject2 = (Track) businessObject;
        this.downloadImage = (ImageView) this.mView.findViewById(R.id.f37download.item.img.download);
        this.mView = videoItemViewHolder.itemView;
        this.tvTitle = videoItemViewHolder.trackName;
        this.tvSubtitle = videoItemViewHolder.albumArtistName;
        this.tvTitle.setText(businessObject.getName());
        setSubtitleText(this.tvSubtitle, businessObject2.getAlbumTitle(), businessObject2.getArtistNames(), businessObject2.isParentalWarningEnabled());
        final VideoPlayerAutoPlayView videoPlayerAutoPlayView = videoItemViewHolder.videoPlayerAutoPlayView;
        this.autoPlayViewsHashMap.put(Integer.valueOf(videoItemViewHolder.getAdapterPosition()), videoPlayerAutoPlayView);
        new com.player_framework.d(this.mContext).a(businessObject2, new b() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onDataRetrieved(Object obj, int i, boolean z) {
                if (obj instanceof String) {
                    videoPlayerAutoPlayView.setSource(Util.k((String) obj));
                } else if (obj instanceof LinkedTreeMap) {
                    videoPlayerAutoPlayView.setSource(Util.k((String) ((LinkedTreeMap) obj).get("data")));
                }
                videoPlayerAutoPlayView.setVideoParams(businessObject2.getVideoId(), 1);
                videoPlayerAutoPlayView.set_act((GaanaActivity) DownloadSongsItemView.this.mContext);
                LifecycleAwareVideoView lifecycleAwareVideoView = new LifecycleAwareVideoView();
                lifecycleAwareVideoView.wrap(videoPlayerAutoPlayView);
                if (DownloadSongsItemView.this.mFragment != null && DownloadSongsItemView.this.mFragment.isAdded()) {
                    DownloadSongsItemView.this.mFragment.getLifecycle().a(lifecycleAwareVideoView);
                }
                videoPlayerAutoPlayView.setVideoStateChangeListener(new ay() {
                    public void videoClicked(int i, long j) {
                    }

                    public void videoStateTransitioned(Bitmap bitmap) {
                    }

                    public void volumeStateChanged(boolean z) {
                    }

                    public void videoStateChanged(int i) {
                        if (i == 1) {
                            videoPlayerAutoPlayView.setLooping(true);
                            videoPlayerAutoPlayView.d();
                            if (DownloadSongsItemView.this.mFragment instanceof RevampedDetailListing) {
                                ((RevampedDetailListing) DownloadSongsItemView.this.mFragment).refreshListView();
                            }
                        }
                    }
                });
                videoPlayerAutoPlayView.getLayoutParams().width = d.a().b() - Util.b(40);
                videoPlayerAutoPlayView.getLayoutParams().height = (9 * videoPlayerAutoPlayView.getLayoutParams().width) / 16;
                videoPlayerAutoPlayView.setVideoScalingMode(true);
                Rect rect = new Rect();
                videoPlayerAutoPlayView.getGlobalVisibleRect(rect);
                if (rect.intersect(new Rect(0, 0, d.a().b(), d.a().c()))) {
                    videoPlayerAutoPlayView.g();
                }
            }
        });
        this.clickoptionImage = videoItemViewHolder.menuIcon;
        this.clickoptionImage.setRotation(90.0f);
        this.clickoptionImage.setTag(businessObject2);
        this.clickoptionImage.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (DownloadSongsItemView.this.mFragment instanceof AlbumDetailsMaterialListing) {
                    ((AlbumDetailsMaterialListing) DownloadSongsItemView.this.mFragment).e();
                }
                if ((DownloadSongsItemView.this.mFragment instanceof RevampedDetailListing) && ((RevampedDetailListing) DownloadSongsItemView.this.mFragment).c.startsWith("ArtistDetailScreen")) {
                    an.a().a("click", "ac", ((RevampedDetailListing) DownloadSongsItemView.this.mFragment).s().getBusinessObjId(), ((RevampedDetailListing) DownloadSongsItemView.this.mFragment).d(), ((BusinessObject) view.getTag()).getBusinessObjId(), "three dot menu", "", "");
                }
                DownloadSongsItemView.this.showOptionMenu(view);
                an.a().a("click", "ac", DownloadSongsItemView.this.mBusinessObject.getBusinessObjId(), "", "", "three dot menu", "", "");
            }
        });
        this.mView.findViewById(R.id.f35download.item.checkbox).setVisibility(8);
        ImageView imageView = this.clickoptionImage;
        if (((this.mFragment instanceof AlbumDetailsMaterialListing) || (this.mFragment instanceof RevampedDetailListing) || (this.mFragment instanceof SongParallexListingFragment) || (this.mFragment instanceof GaanaSpecialDetailsMaterialListing) || isTrendingScreen()) && !this.isPlayerQueue && al.a().d()) {
            imageView.setVisibility(4);
            return initTrackSelectionModeForVideo(businessObject2, this.mView);
        } else if ((this.mFragment instanceof DownloadDetailsFragment) && !this.isPlayerQueue && i.a().f()) {
            imageView.setVisibility(4);
            return initEditMode(businessObject2, this.mView);
        } else {
            String entityId;
            imageView.setVisibility(0);
            if (businessObject instanceof Item) {
                entityId = ((Item) businessObject).getEntityId();
            } else {
                entityId = businessObject.getBusinessObjId();
            }
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(entityId));
            if (!ap.a().b(businessObject)) {
                this.downloadImage.setClickable(false);
            } else if (businessObject.isLocalMedia()) {
                this.downloadImage.setVisibility(0);
                this.downloadImage.setImageDrawable(getResources().getDrawable(R.drawable.vector_my_music_local_white));
                this.downloadImage.setClickable(false);
            } else {
                this.downloadImage.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (DownloadSongsItemView.this.currentSongBlinkingTrackId.equalsIgnoreCase(entityId)) {
                            BaseGaanaFragment currentFragment = ((GaanaActivity) DownloadSongsItemView.this.mContext).getCurrentFragment();
                            BusinessObject businessObject = null;
                            if (currentFragment instanceof ArtistDetailsMaterialListing) {
                                businessObject = ((ArtistDetailsMaterialListing) currentFragment).c();
                            } else if (currentFragment instanceof AlbumDetailsMaterialListing) {
                                businessObject = ((AlbumDetailsMaterialListing) currentFragment).c();
                            } else if (currentFragment instanceof GaanaSpecialDetailsMaterialListing) {
                                businessObject = ((GaanaSpecialDetailsMaterialListing) currentFragment).d();
                            }
                            if (businessObject != null) {
                                if (businessObject instanceof Playlist) {
                                    u.a().a("Downloads: PlaylistView", "Clicked on Download icon", "");
                                } else if (businessObject instanceof Album) {
                                    u.a().a("Downloads: AlbumlistView", "Clicked on Download icon", "");
                                }
                            }
                        }
                        DownloadSongsItemView.this.downloadPulse.setVisibility(4);
                        DownloadSongsItemView.this.downloadTrack(entityId, businessObject);
                    }
                });
                if (e == null) {
                    this.downloadImage.setVisibility(0);
                    if ((businessObject instanceof Track) && businessObject2.isFreeDownloadEnabled() && isFreeUser()) {
                        this.downloadImage.setImageDrawable(Util.b(this.mContext, (int) R.attr.free_download_icon));
                        Util.ab();
                    } else {
                        this.downloadImage.setImageDrawable(getResources().getDrawable(R.drawable.vector_more_option_download_white_for_video));
                    }
                } else if (e == DownloadStatus.DOWNLOADING) {
                    if (DownloadManager.c().w()) {
                        this.downloadImage.setVisibility(4);
                    } else {
                        this.downloadImage.setVisibility(0);
                        this.downloadImage.setImageResource(R.drawable.vector_download_queued);
                    }
                } else if (e == DownloadStatus.DOWNLOADED) {
                    this.downloadImage.setVisibility(0);
                    if (ap.a().o()) {
                        this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                    } else if (ap.a().m()) {
                        if (DownloadManager.c().j(businessObject.getBusinessObjId()).booleanValue()) {
                            this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                        } else {
                            this.downloadImage.setImageDrawable(getResources().getDrawable(R.drawable.vector_download_completed_disabled_white_for_video));
                        }
                    } else if (!ap.a().k() || Util.a(businessObject)) {
                        this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                    } else {
                        this.downloadImage.setImageDrawable(getResources().getDrawable(R.drawable.vector_download_expired_btn));
                    }
                } else if (e == DownloadStatus.QUEUED) {
                    this.downloadImage.setVisibility(0);
                    this.downloadImage.setImageResource(R.drawable.vector_download_queued);
                } else if (e == DownloadStatus.TRIED_BUT_FAILED) {
                    this.downloadImage.setVisibility(0);
                    this.downloadImage.setImageResource(R.drawable.vector_download_retry_white_for_video);
                } else {
                    this.downloadImage.setVisibility(0);
                    if ((businessObject instanceof Track) && businessObject2.isFreeDownloadEnabled() && isFreeUser()) {
                        this.downloadImage.setImageDrawable(Util.b(this.mContext, (int) R.attr.free_download_icon));
                        Util.ab();
                    } else {
                        new int[1][0] = R.attr.download_button_paused;
                        this.downloadImage.setImageDrawable(getResources().getDrawable(R.drawable.vector_more_option_download_white_for_video));
                    }
                }
            }
            setProgressBarVisibility((RateTextCircularProgressBar) this.mView.findViewById(R.id.rate_progress_bar), DownloadManager.c().e(Integer.parseInt(entityId)));
            return this.mView;
        }
    }

    public View getDataFilledView(ViewHolder viewHolder, final BusinessObject businessObject) {
        this.mBusinessObject = businessObject;
        AlbumDetailItemHolder albumDetailItemHolder = (AlbumDetailItemHolder) viewHolder;
        this.mView = albumDetailItemHolder.itemView;
        this.downloadImage = albumDetailItemHolder.downloadImage;
        this.downloadPulse = albumDetailItemHolder.downloadPulse;
        this.tvTitle = albumDetailItemHolder.tvTitle;
        this.tvSubtitle = albumDetailItemHolder.tvSubtitle;
        this.tvSubtitle.setVisibility(0);
        if (this.mFragment instanceof RevampedDetailListing) {
            albumDetailItemHolder.divider.setVisibility(8);
        }
        boolean z = businessObject instanceof Item;
        if (z) {
            boolean equals;
            Item item = (Item) businessObject;
            this.tvTitle.setText(item.getName());
            ArrayList arrayList = (ArrayList) item.getEntityInfo();
            if (arrayList != null) {
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    if (((EntityInfo) arrayList.get(i)).getKey().equals("parental_warning")) {
                        if (!(((EntityInfo) arrayList.get(i)).getValue() instanceof Double)) {
                            equals = ((EntityInfo) arrayList.get(i)).getValue().equals("1");
                        } else if (Double.compare(((Double) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue(), 1.0d) == 0) {
                            equals = true;
                        }
                        setSubtitleText(this.tvSubtitle, getAlbumName(item), getArtistNames(item), equals);
                    } else {
                        i++;
                    }
                }
            }
            equals = false;
            setSubtitleText(this.tvSubtitle, getAlbumName(item), getArtistNames(item), equals);
        } else {
            Track track = (Track) businessObject;
            this.tvTitle.setText(track.getName());
            BusinessObject a = this.mAppState.getListingComponents() != null ? this.mAppState.getListingComponents().a() : null;
            if (a == null || a.getBusinessObjType() != BusinessObjectType.Albums) {
                setSubtitleText(this.tvSubtitle, track.getAlbumTitle(), track.getArtistNames(), track.isParentalWarningEnabled());
            } else {
                this.tvSubtitle.setText(track.getArtistNames());
                int i2 = Constants.l ? R.drawable.vector_ic_explicit_content_white : R.drawable.vector_ic_explicit_content;
                if (track.isParentalWarningEnabled()) {
                    this.tvSubtitle.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.mContext, i2), null, null, null);
                } else {
                    this.tvSubtitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                }
            }
        }
        this.clickoptionImage = albumDetailItemHolder.clickoptionImage;
        this.clickoptionImage.setRotation(90.0f);
        this.clickoptionImage.setTag(businessObject);
        this.clickoptionImage.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (DownloadSongsItemView.this.mFragment instanceof AlbumDetailsMaterialListing) {
                    ((AlbumDetailsMaterialListing) DownloadSongsItemView.this.mFragment).e();
                }
                if ((DownloadSongsItemView.this.mFragment instanceof RevampedDetailListing) && ((RevampedDetailListing) DownloadSongsItemView.this.mFragment).c.startsWith("ArtistDetailScreen")) {
                    an.a().a("click", "ac", ((RevampedDetailListing) DownloadSongsItemView.this.mFragment).s().getBusinessObjId(), ((RevampedDetailListing) DownloadSongsItemView.this.mFragment).d(), ((BusinessObject) view.getTag()).getBusinessObjId(), "three dot menu", "", "");
                }
                if ((DownloadSongsItemView.this.mFragment instanceof SongParallexListingFragment) && DownloadSongsItemView.this.mFragment.getSectionNameForReturn().equalsIgnoreCase("RECENTLY_PLAYED")) {
                    DownloadSongsItemView.this.showOptionMenu(view, true);
                } else {
                    DownloadSongsItemView.this.showOptionMenu(view);
                }
                an.a().a("click", "ac", DownloadSongsItemView.this.mBusinessObject.getBusinessObjId(), "", "", "three dot menu", "", "");
            }
        });
        ImageView imageView = this.clickoptionImage;
        this.mCrossFadeImageIcon = albumDetailItemHolder.mCrossFadeImageIcon;
        this.mThumbnailRightTopIndicator = albumDetailItemHolder.mThumbnailRightTopIndicator;
        this.mCrossFadeImageIconLyt = albumDetailItemHolder.crossFadeImageViewLyt;
        Track track2;
        if ((this.mFragment instanceof CuratedDownloadSuggestionFragment) && !this.isPlayerQueue) {
            this.mView.findViewById(R.id.select_icon);
            if (businessObject instanceof Track) {
                track2 = (Track) businessObject;
            } else {
                track2 = (Track) populateTrackClicked((Item) businessObject);
            }
            return initTrackDownloadSelectionMode(track2, this.mView);
        } else if (((this.mFragment instanceof AlbumDetailsMaterialListing) || (this.mFragment instanceof RevampedDetailListing) || (this.mFragment instanceof SongParallexListingFragment) || (this.mFragment instanceof GaanaSpecialDetailsMaterialListing) || isTrendingScreen()) && !this.isPlayerQueue && al.a().d()) {
            imageView.setVisibility(4);
            this.mView.findViewById(R.id.img_animation).setVisibility(8);
            if (businessObject instanceof Track) {
                track2 = (Track) businessObject;
            } else {
                track2 = (Track) populateTrackClicked((Item) businessObject);
            }
            return initTrackSelectionMode(track2, this.mView);
        } else if ((this.mFragment instanceof DownloadDetailsFragment) && !this.isPlayerQueue && i.a().f()) {
            imageView.setVisibility(4);
            if (businessObject instanceof Track) {
                track2 = (Track) businessObject;
            } else {
                track2 = (Track) populateTrackClicked((Item) businessObject);
            }
            return initEditMode(track2, this.mView);
        } else {
            String entityId;
            if (albumDetailItemHolder.isFromCuratedDialog) {
                imageView.setVisibility(4);
                imageView.getLayoutParams().width = 25;
                imageView.setEnabled(false);
                this.isCuratedDownloadItem = true;
            } else {
                imageView.setVisibility(0);
            }
            this.mView.findViewById(R.id.f35download.item.checkbox).setVisibility(8);
            if (z) {
                entityId = ((Item) businessObject).getEntityId();
            } else {
                entityId = businessObject.getBusinessObjId();
            }
            String str = "";
            if (z) {
                str = ((Item) businessObject).getPremiumContent();
            } else if (businessObject instanceof Track) {
                str = ((Track) businessObject).getPremiumContent();
            }
            if (albumDetailItemHolder.mThumbnailRightTopIndicator != null) {
                if (Constants.cV.equalsIgnoreCase(str)) {
                    albumDetailItemHolder.mThumbnailRightTopIndicator.setVisibility(0);
                } else {
                    albumDetailItemHolder.mThumbnailRightTopIndicator.setVisibility(8);
                }
            }
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(entityId));
            TypedArray obtainStyledAttributes;
            Drawable drawable;
            if (!ap.a().b(businessObject)) {
                this.downloadImage.setClickable(false);
            } else if (businessObject.isLocalMedia()) {
                this.downloadImage.setVisibility(0);
                new int[1][0] = R.attr.offline_icon;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(64, -1));
                obtainStyledAttributes.recycle();
                this.downloadImage.setImageDrawable(drawable);
                this.downloadImage.setClickable(false);
            } else {
                this.downloadImage.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (DownloadSongsItemView.this.currentSongBlinkingTrackId.equalsIgnoreCase(entityId)) {
                            BaseGaanaFragment currentFragment = ((GaanaActivity) DownloadSongsItemView.this.mContext).getCurrentFragment();
                            BusinessObject businessObject = null;
                            if (currentFragment instanceof ArtistDetailsMaterialListing) {
                                businessObject = ((ArtistDetailsMaterialListing) currentFragment).c();
                            } else if (currentFragment instanceof AlbumDetailsMaterialListing) {
                                businessObject = ((AlbumDetailsMaterialListing) currentFragment).c();
                            } else if (currentFragment instanceof GaanaSpecialDetailsMaterialListing) {
                                businessObject = ((GaanaSpecialDetailsMaterialListing) currentFragment).d();
                            }
                            if (businessObject != null) {
                                if (businessObject instanceof Playlist) {
                                    u.a().a("Downloads: PlaylistView", "Clicked on Download icon", "");
                                } else if (businessObject instanceof Album) {
                                    u.a().a("Downloads: AlbumlistView", "Clicked on Download icon", "");
                                }
                            }
                        }
                        DownloadSongsItemView.this.downloadPulse.setVisibility(4);
                        DownloadSongsItemView.this.downloadTrack(entityId, businessObject);
                    }
                });
                if (e == null) {
                    this.downloadImage.setVisibility(0);
                    if ((businessObject instanceof Track) && ((Track) businessObject).isFreeDownloadEnabled() && isFreeUser()) {
                        this.downloadImage.setImageDrawable(Util.b(this.mContext, (int) R.attr.free_download_icon));
                        Util.ab();
                    } else {
                        new int[1][0] = R.attr.download_button_paused;
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
                        obtainStyledAttributes.recycle();
                        this.downloadImage.setImageDrawable(drawable);
                    }
                } else if (e == DownloadStatus.DOWNLOADING) {
                    if (DownloadManager.c().w()) {
                        this.downloadImage.setVisibility(4);
                    } else {
                        this.downloadImage.setVisibility(0);
                        this.downloadImage.setImageResource(R.drawable.vector_download_queued);
                    }
                } else if (e == DownloadStatus.DOWNLOADED) {
                    this.downloadImage.setVisibility(0);
                    if (ap.a().o()) {
                        this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                    } else if (ap.a().m()) {
                        if (DownloadManager.c().j(businessObject.getBusinessObjId()).booleanValue()) {
                            this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                        } else {
                            new int[1][0] = R.attr.download_button_disabled;
                            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                            drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(12, -1));
                            obtainStyledAttributes.recycle();
                            this.downloadImage.setImageDrawable(drawable);
                        }
                    } else if (!ap.a().k() || Util.a(businessObject)) {
                        this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                    } else {
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(90, -1));
                        obtainStyledAttributes.recycle();
                        this.downloadImage.setImageDrawable(drawable);
                    }
                } else if (e == DownloadStatus.QUEUED) {
                    this.downloadImage.setVisibility(0);
                    this.downloadImage.setImageResource(R.drawable.vector_download_queued);
                } else if (e == DownloadStatus.TRIED_BUT_FAILED) {
                    this.downloadImage.setVisibility(0);
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(91, -1));
                    obtainStyledAttributes.recycle();
                    this.downloadImage.setImageDrawable(drawable);
                } else {
                    this.downloadImage.setVisibility(0);
                    if ((businessObject instanceof Track) && ((Track) businessObject).isFreeDownloadEnabled() && isFreeUser()) {
                        this.downloadImage.setImageDrawable(Util.b(this.mContext, (int) R.attr.free_download_icon));
                        Util.ab();
                    } else {
                        new int[1][0] = R.attr.download_button_paused;
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
                        obtainStyledAttributes.recycle();
                        this.downloadImage.setImageDrawable(drawable);
                    }
                }
            }
            if (businessObject.isLocalMedia() || ((!this.mAppState.isAppInOfflineMode() || DownloadManager.c().l(Integer.parseInt(entityId)).booleanValue()) && ap.a().b(businessObject))) {
                PlayerTrack i3 = PlayerManager.a(this.mContext).i();
                if (i3 == null || i3.b() == null || !entityId.equalsIgnoreCase(i3.h())) {
                    if (Constants.l) {
                        this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.first_line_color_white));
                    } else {
                        this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.first_line_color));
                    }
                    if (this.isPlayerQueue) {
                        if (Constants.l) {
                            this.mView.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.selector_btn_global_bg_grey_white));
                        } else {
                            this.mView.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.selector_btn_global_bg_grey));
                        }
                    }
                } else {
                    this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.gaana_orange_text));
                }
            } else {
                this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
                this.tvSubtitle.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
            }
            setMusicPlayingAnimation((CrossFadeImageView) this.mView.findViewById(R.id.f38download.item.img.thumb), (ImageView) this.mView.findViewById(R.id.img_animation), businessObject, (ImageView) this.mView.findViewById(R.id.indicatorIconRightTop), (FrameLayout) this.mView.findViewById(R.id.f39download.item.img.thumb.container));
            setAnimationBlinker((PulsatorView) this.mView.findViewById(R.id.downloadPulse), businessObject);
            setProgressBarVisibility((RateTextCircularProgressBar) this.mView.findViewById(R.id.rate_progress_bar), DownloadManager.c().e(Integer.parseInt(entityId)));
            View findViewById = this.mView.findViewById(R.id.view_item_overlay_disable);
            if (findViewById != null) {
                if (Constants.ab) {
                    findViewById.setVisibility(0);
                    findViewById.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            if (Constants.g > 0) {
                                if (DownloadSongsItemView.this.mFragment != null) {
                                    if ((DownloadSongsItemView.this.mFragment instanceof AlbumDetailsMaterialListing) || (DownloadSongsItemView.this.mFragment instanceof RevampedDetailListing)) {
                                        String str = "";
                                        if (DownloadSongsItemView.this.mBusinessObject != null && DownloadSongsItemView.this.mBusinessObject.getParentBusinessObjType() == BusinessObjectType.Playlists) {
                                            str = "Playlist Detail";
                                        } else if (DownloadSongsItemView.this.mBusinessObject != null && DownloadSongsItemView.this.mBusinessObject.getParentBusinessObjType() == BusinessObjectType.Albums) {
                                            str = "Album Detail";
                                        }
                                        u.a().a("Shuffle Product", "Gaana+ popup", str);
                                    } else if ((DownloadSongsItemView.this.mFragment.getParentFragment() != null && (DownloadSongsItemView.this.mFragment.getParentFragment() instanceof ArtistDetailsMaterialListing)) || (DownloadSongsItemView.this.mFragment instanceof ArtistFragment)) {
                                        u.a().a("Shuffle Product", "Gaana+ popup", "Artist");
                                    } else if (DownloadSongsItemView.this.mFragment instanceof GaanaSpecialDetailsMaterialListing) {
                                        u.a().a("Shuffle Product", "Gaana+ popup", "Gaana Special");
                                    } else if ((DownloadSongsItemView.this.mFragment instanceof SongParallexListingFragment) || (DownloadSongsItemView.this.mFragment instanceof ListingFragment)) {
                                        u.a().a("Shuffle Product", "Gaana+ popup", "Songs Detail");
                                    }
                                }
                                Constants.g--;
                                Util.a(DownloadSongsItemView.this.mContext, BLOCK_ACTION.SHUFFLE);
                            } else if (DownloadSongsItemView.this.mFragment == null) {
                            } else {
                                if (DownloadSongsItemView.this.mFragment instanceof AlbumDetailsMaterialListing) {
                                    ((AlbumDetailsMaterialListing) DownloadSongsItemView.this.mFragment).j();
                                } else if (DownloadSongsItemView.this.mFragment instanceof RevampedDetailListing) {
                                    ((RevampedDetailListing) DownloadSongsItemView.this.mFragment).t();
                                } else if (DownloadSongsItemView.this.mFragment.getParentFragment() != null && (DownloadSongsItemView.this.mFragment.getParentFragment() instanceof ArtistDetailsMaterialListing)) {
                                    ((ArtistDetailsMaterialListing) DownloadSongsItemView.this.mFragment.getParentFragment()).a();
                                } else if (DownloadSongsItemView.this.mFragment instanceof GaanaSpecialDetailsMaterialListing) {
                                    ((GaanaSpecialDetailsMaterialListing) DownloadSongsItemView.this.mFragment).a();
                                } else if (DownloadSongsItemView.this.mFragment instanceof SongParallexListingFragment) {
                                    ((SongParallexListingFragment) DownloadSongsItemView.this.mFragment).c();
                                } else if (DownloadSongsItemView.this.mFragment instanceof ListingFragment) {
                                    ((ListingFragment) DownloadSongsItemView.this.mFragment).o();
                                }
                            }
                        }
                    });
                } else {
                    findViewById.setVisibility(8);
                }
            }
            if (Constants.ab) {
                findViewById = this.mView.findViewById(R.id.item_player_overlay);
                if (findViewById != null) {
                    findViewById.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            if (Constants.ab && ((GaanaActivity) DownloadSongsItemView.this.mContext).isSlidingPanelExpanded()) {
                                u.a().a("Shuffle Product", "Gaana+ popup", "Player Queue");
                                Util.a(DownloadSongsItemView.this.mContext, BLOCK_ACTION.SHUFFLE);
                            }
                        }
                    });
                }
            }
            return this.mView;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0036  */
    public void downloadTrack(java.lang.String r25, com.gaana.models.BusinessObject r26) {
        /*
        r24 = this;
        r0 = r24;
        r1 = r25;
        r2 = r26;
        r3 = com.managers.DownloadManager.c();
        r4 = java.lang.Integer.parseInt(r25);
        r3 = r3.e(r4);
        r4 = r2 instanceof com.gaana.models.Item;
        if (r4 == 0) goto L_0x002c;
    L_0x0016:
        r4 = r2;
        r4 = (com.gaana.models.Item) r4;
        r5 = r4.getEntityType();
        r6 = com.constants.c.c.c;
        r5 = r5.equals(r6);
        if (r5 == 0) goto L_0x002c;
    L_0x0025:
        r2 = r0.populateTrackClicked(r4);
        r2 = (com.gaana.models.Tracks.Track) r2;
        goto L_0x002e;
    L_0x002c:
        r2 = (com.gaana.models.Tracks.Track) r2;
    L_0x002e:
        r4 = r0.mAppState;
        r4 = r4.isAppInOfflineMode();
        if (r4 == 0) goto L_0x0047;
    L_0x0036:
        r1 = r0.mContext;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = r0.mContext;
        r3 = 2131822681; // 0x7f110859 float:1.927814E38 double:1.0532603497E-314;
        r2 = r2.getString(r3);
        r1.displayFeatureNotAvailableOfflineDialog(r2);
        return;
    L_0x0047:
        r4 = r0.mContext;
        r4 = com.utilities.Util.j(r4);
        if (r4 != 0) goto L_0x0059;
    L_0x004f:
        r1 = com.managers.ap.a();
        r2 = r0.mContext;
        r1.f(r2);
        return;
    L_0x0059:
        r4 = -1;
        r5 = r0.mAppState;
        r5 = r5.getCurrentBusObjInListView();
        if (r5 == 0) goto L_0x007c;
    L_0x0062:
        r4 = new java.util.ArrayList;
        r4.<init>();
        r5 = r0.mAppState;
        r5 = r5.getCurrentBusObjInListView();
        if (r5 == 0) goto L_0x0078;
    L_0x006f:
        r6 = r5.size();
        if (r6 <= 0) goto L_0x0078;
    L_0x0075:
        r4.addAll(r5);
    L_0x0078:
        r4 = r4.indexOf(r2);
    L_0x007c:
        r5 = r0.mContext;
        r5 = (com.gaana.BaseActivity) r5;
        r6 = r0.mContext;
        r6 = (com.gaana.BaseActivity) r6;
        r6 = r6.currentScreen;
        r7 = "Download";
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = r0.mContext;
        r9 = (com.gaana.BaseActivity) r9;
        r9 = r9.currentScreen;
        r8.append(r9);
        r9 = " - ";
        r8.append(r9);
        r9 = r0.mContext;
        r9 = (com.gaana.BaseActivity) r9;
        r9 = r9.currentFavpage;
        r8.append(r9);
        r9 = " - Download";
        r8.append(r9);
        r8 = r8.toString();
        r5.sendGAEvent(r6, r7, r8);
        r5 = com.gaana.analytics.MoEngage.getInstance();
        r5.reportDownload(r2);
        r5 = r0.mFragment;
        r5 = r5 instanceof com.fragments.RevampedDetailListing;
        if (r5 == 0) goto L_0x00f7;
    L_0x00bd:
        r5 = r0.mFragment;
        r5 = (com.fragments.RevampedDetailListing) r5;
        r5 = r5.c;
        r6 = "ArtistDetailScreen";
        r5 = r5.startsWith(r6);
        if (r5 == 0) goto L_0x00f7;
    L_0x00cb:
        r6 = com.managers.an.a();
        r7 = "click";
        r8 = "ac";
        r4 = r0.mFragment;
        r4 = (com.fragments.RevampedDetailListing) r4;
        r4 = r4.s();
        r9 = r4.getBusinessObjId();
        r4 = r0.mFragment;
        r4 = (com.fragments.RevampedDetailListing) r4;
        r10 = r4.d();
        r4 = r0.mBusinessObject;
        r11 = r4.getBusinessObjId();
        r12 = "download";
        r13 = "";
        r14 = "";
        r6.a(r7, r8, r9, r10, r11, r12, r13, r14);
        goto L_0x0114;
    L_0x00f7:
        r15 = com.managers.an.a();
        r16 = "click";
        r17 = "ac";
        r18 = r2.getAlbumId();
        r19 = "";
        r20 = r2.getBusinessObjId();
        r21 = "download";
        r22 = java.lang.String.valueOf(r4);
        r23 = "";
        r15.a(r16, r17, r18, r19, r20, r21, r22, r23);
    L_0x0114:
        r4 = com.managers.DownloadManager.DownloadStatus.DOWNLOADED;
        if (r3 != r4) goto L_0x015a;
    L_0x0118:
        r3 = com.managers.ap.a();
        r3 = r3.k();
        if (r3 == 0) goto L_0x013d;
    L_0x0122:
        r3 = com.utilities.Util.a(r2);
        if (r3 != 0) goto L_0x013d;
    L_0x0128:
        r1 = r0.mContext;
        r2 = 0;
        com.utilities.Util.a(r1, r2);
        r1 = com.managers.u.a();
        r2 = "Expired Download";
        r3 = "Click";
        r4 = "Track";
        r1.a(r2, r3, r4);
        goto L_0x01b3;
    L_0x013d:
        r3 = new com.gaana.view.item.CustomDialogView;
        r4 = r0.mContext;
        r5 = r0.mContext;
        r5 = r5.getResources();
        r6 = 2131822705; // 0x7f110871 float:1.9278189E38 double:1.0532603616E-314;
        r5 = r5.getString(r6);
        r6 = new com.gaana.view.item.DownloadSongsItemView$8;
        r6.<init>(r2, r1);
        r3.<init>(r4, r5, r6);
        r3.show();
        goto L_0x01b3;
    L_0x015a:
        r4 = com.managers.DownloadManager.DownloadStatus.QUEUED;
        if (r3 != r4) goto L_0x017b;
    L_0x015e:
        r3 = new com.gaana.view.item.CustomDialogView;
        r4 = r0.mContext;
        r5 = r0.mContext;
        r5 = r5.getResources();
        r6 = 2131822713; // 0x7f110879 float:1.9278205E38 double:1.0532603655E-314;
        r5 = r5.getString(r6);
        r6 = new com.gaana.view.item.DownloadSongsItemView$9;
        r6.<init>(r1, r2);
        r3.<init>(r4, r5, r6);
        r3.show();
        goto L_0x01b3;
    L_0x017b:
        r4 = com.managers.DownloadManager.DownloadStatus.DOWNLOADING;
        if (r3 != r4) goto L_0x019c;
    L_0x017f:
        r3 = new com.gaana.view.item.CustomDialogView;
        r4 = r0.mContext;
        r5 = r0.mContext;
        r5 = r5.getResources();
        r6 = 2131822714; // 0x7f11087a float:1.9278207E38 double:1.053260366E-314;
        r5 = r5.getString(r6);
        r6 = new com.gaana.view.item.DownloadSongsItemView$10;
        r6.<init>(r1, r2);
        r3.<init>(r4, r5, r6);
        r3.show();
        goto L_0x01b3;
    L_0x019c:
        r1 = com.services.d.a();
        r3 = "PREFERENCE_KEY_DOWNLOAD_CLICK_INITIATED";
        r4 = 1;
        r5 = 0;
        r1.a(r3, r4, r5);
        r0.startDownload(r2);
        r1 = r0.mContext;
        r1 = com.managers.j.a(r1);
        r1.a(r0);
    L_0x01b3:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.item.DownloadSongsItemView.downloadTrack(java.lang.String, com.gaana.models.BusinessObject):void");
    }

    private void setProgressBarVisibility(RateTextCircularProgressBar rateTextCircularProgressBar, DownloadStatus downloadStatus) {
        if (rateTextCircularProgressBar == null) {
            return;
        }
        if (downloadStatus == DownloadStatus.DOWNLOADING && downloadStatus != DownloadStatus.DOWNLOADED) {
            if (this.isPlayerQueue) {
                rateTextCircularProgressBarPlayer = rateTextCircularProgressBar;
            } else {
                this.rateTextCircularProgressBar = rateTextCircularProgressBar;
            }
            rateTextCircularProgressBar.setVisibility(0);
        } else if (downloadStatus == DownloadStatus.DOWNLOADED) {
            rateTextCircularProgressBar.setVisibility(8);
        } else if (downloadStatus == DownloadStatus.QUEUED) {
            rateTextCircularProgressBar.setVisibility(8);
        } else if (downloadStatus == DownloadStatus.PARTIALLY_DOWNLOADED) {
            rateTextCircularProgressBar.setVisibility(8);
        } else if (downloadStatus == DownloadStatus.PAUSED || downloadStatus == DownloadStatus.TRIED_BUT_FAILED) {
            rateTextCircularProgressBar.setVisibility(8);
        } else {
            rateTextCircularProgressBar.setVisibility(8);
        }
    }

    private void setSubtitleText(TextView textView, String str, String str2, boolean z) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" - ");
            stringBuilder.append(str2);
            textView.setText(stringBuilder.toString());
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            textView.setVisibility(8);
        } else if (TextUtils.isEmpty(str)) {
            textView.setText(str2);
        } else {
            textView.setText(str);
        }
        int i = Constants.l ? R.drawable.vector_ic_explicit_content_white : R.drawable.vector_ic_explicit_content;
        if (z) {
            textView.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.mContext, i), null, null, null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    public View getDataFilledView(View view, BusinessObject businessObject) {
        View view2 = view;
        BusinessObject businessObject2 = businessObject;
        this.mBusinessObject = businessObject2;
        final BusinessObject businessObject3 = (Track) businessObject2;
        this.downloadImage = (ImageView) view2.findViewById(R.id.f37download.item.img.download);
        this.downloadPulse = (PulsatorView) view2.findViewById(R.id.downloadPulse);
        this.tvTitle = (TextView) view2.findViewById(R.id.f43download.item.tv.trackname);
        this.tvSubtitle = (TextView) view2.findViewById(R.id.f40download.item.tv.genere);
        this.tvSubtitle.setVisibility(0);
        this.tvTitle.setText(businessObject3.getName());
        setSubtitleText(this.tvSubtitle, businessObject3.getAlbumTitle(), businessObject3.getArtistNames(), businessObject3.isParentalWarningEnabled());
        view2.findViewById(R.id.clickoptionImage).setTag(businessObject2);
        view2.findViewById(R.id.clickoptionImage).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DownloadSongsItemView.this.showOptionMenu(view);
                if (DownloadSongsItemView.this.isPlayerQueue) {
                    an.a().a("click", "ac", "", "queue", "", "three dot menu", "", "");
                }
            }
        });
        ImageView imageView = (ImageView) view2.findViewById(R.id.clickoptionImage);
        this.mCrossFadeImageIcon = (CrossFadeImageView) view2.findViewById(R.id.f38download.item.img.thumb);
        this.mThumbnailRightTopIndicator = (ImageView) view2.findViewById(R.id.indicatorIconRightTop);
        this.mCrossFadeImageIconLyt = (FrameLayout) view2.findViewById(R.id.f39download.item.img.thumb.container);
        if ((this.mFragment instanceof CuratedDownloadSuggestionFragment) && !this.isPlayerQueue) {
            return initTrackDownloadSelectionMode(businessObject3, view2);
        }
        if (((this.mFragment instanceof AlbumDetailsMaterialListing) || (this.mFragment instanceof RevampedDetailListing) || (this.mFragment instanceof SongParallexListingFragment) || (this.mFragment instanceof GaanaSpecialDetailsMaterialListing) || isTrendingScreen()) && !this.isPlayerQueue && al.a().d()) {
            imageView.setVisibility(4);
            return initTrackSelectionMode(businessObject3, view2);
        } else if ((this.mFragment instanceof DownloadDetailsFragment) && !this.isPlayerQueue && i.a().f()) {
            imageView.setVisibility(4);
            return initEditMode(businessObject3, view2);
        } else {
            DownloadStatus e;
            imageView.setVisibility(0);
            view2.findViewById(R.id.f35download.item.checkbox).setVisibility(8);
            try {
                e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
            } catch (Exception unused) {
                e = null;
            }
            final int a = Util.a(businessObject3.getBusinessObjId());
            TypedArray obtainStyledAttributes;
            Drawable drawable;
            if (!ap.a().b(businessObject3)) {
                view2.findViewById(R.id.f37download.item.img.download).setClickable(false);
            } else if (businessObject3.isLocalMedia()) {
                this.downloadImage.setVisibility(0);
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(64, -1));
                obtainStyledAttributes.recycle();
                this.downloadImage.setImageDrawable(drawable);
                this.downloadImage.setClickable(false);
            } else {
                view2.findViewById(R.id.f37download.item.img.download).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        DownloadSongsItemView.this.downloadTrack(String.valueOf(a), businessObject3);
                        if (DownloadSongsItemView.this.isPlayerQueue) {
                            an.a().a("click", "ac", "", "queue", "", "download", "", "");
                        }
                    }
                });
                TypedArray obtainStyledAttributes2;
                Drawable drawable2;
                if (e != null) {
                    if (e == DownloadStatus.DOWNLOADING) {
                        if (DownloadManager.c().w()) {
                            this.downloadImage.setVisibility(4);
                        } else {
                            this.downloadImage.setVisibility(0);
                            this.downloadImage.setImageResource(R.drawable.vector_download_queued);
                        }
                    } else if (e == DownloadStatus.DOWNLOADED) {
                        this.downloadImage.setVisibility(0);
                        if (ap.a().o()) {
                            this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                        } else if (ap.a().m()) {
                            if (DownloadManager.c().j(businessObject.getBusinessObjId()).booleanValue()) {
                                this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                            } else {
                                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(12, -1));
                                obtainStyledAttributes.recycle();
                                this.downloadImage.setImageDrawable(drawable);
                            }
                        } else if (!ap.a().k() || Util.a(businessObject)) {
                            this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                        } else {
                            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                            drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(90, -1));
                            obtainStyledAttributes.recycle();
                            this.downloadImage.setImageDrawable(drawable);
                        }
                    } else if (e == DownloadStatus.QUEUED) {
                        this.downloadImage.setVisibility(0);
                        this.downloadImage.setImageResource(R.drawable.vector_download_queued);
                    } else if (e == DownloadStatus.TRIED_BUT_FAILED) {
                        this.downloadImage.setVisibility(0);
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(91, -1));
                        obtainStyledAttributes.recycle();
                        this.downloadImage.setImageDrawable(drawable);
                    } else {
                        this.downloadImage.setVisibility(0);
                        if (businessObject3.isFreeDownloadEnabled() && isFreeUser()) {
                            this.downloadImage.setImageDrawable(Util.b(this.mContext, (int) R.attr.free_download_icon));
                            Util.ab();
                        } else {
                            obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                            drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(13, -1));
                            obtainStyledAttributes2.recycle();
                            this.downloadImage.setImageDrawable(drawable2);
                        }
                    }
                } else if (businessObject3.isFreeDownloadEnabled() && isFreeUser()) {
                    this.downloadImage.setImageDrawable(Util.b(this.mContext, (int) R.attr.free_download_icon));
                    Util.ab();
                } else {
                    this.downloadImage.setVisibility(0);
                    obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(13, -1));
                    obtainStyledAttributes2.recycle();
                    this.downloadImage.setImageDrawable(drawable2);
                }
            }
            if (businessObject.isLocalMedia() || ((!this.mAppState.isAppInOfflineMode() || DownloadManager.c().l(a).booleanValue()) && ap.a().b(businessObject3))) {
                PlayerTrack i = PlayerManager.a(this.mContext).i();
                if (i != null && i.b() != null && businessObject3.getBusinessObjId().equalsIgnoreCase(i.h())) {
                    this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.gaana_orange_text));
                } else if (Constants.l) {
                    this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.first_line_color_white));
                } else {
                    this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.first_line_color));
                }
            } else {
                this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
                this.tvSubtitle.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
            }
            this.mCrossFadeImageIcon.setVisibility(0);
            if (this.mCrossFadeImageIconLyt != null) {
                this.mCrossFadeImageIconLyt.setVisibility(0);
            }
            setProgressBarVisibility((RateTextCircularProgressBar) view2.findViewById(R.id.rate_progress_bar), e);
            setMusicPlayingAnimation((CrossFadeImageView) view2.findViewById(R.id.f38download.item.img.thumb), (ImageView) view2.findViewById(R.id.img_animation), businessObject3, (ImageView) view2.findViewById(R.id.indicatorIconRightTop), (FrameLayout) view2.findViewById(R.id.f39download.item.img.thumb.container));
            setAnimationBlinker((PulsatorView) view2.findViewById(R.id.downloadPulse), businessObject3);
            View findViewById = view2.findViewById(R.id.view_item_overlay_disable);
            if (findViewById != null) {
                if (Constants.ab) {
                    findViewById.setVisibility(0);
                    findViewById.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            if (Constants.g > 0) {
                                if (DownloadSongsItemView.this.mFragment != null) {
                                    if ((DownloadSongsItemView.this.mFragment instanceof AlbumDetailsMaterialListing) || (DownloadSongsItemView.this.mFragment instanceof RevampedDetailListing)) {
                                        String str = "";
                                        if (DownloadSongsItemView.this.mBusinessObject != null && DownloadSongsItemView.this.mBusinessObject.getParentBusinessObjType() == BusinessObjectType.Playlists) {
                                            str = "Playlist Detail";
                                        } else if (DownloadSongsItemView.this.mBusinessObject != null && DownloadSongsItemView.this.mBusinessObject.getParentBusinessObjType() == BusinessObjectType.Albums) {
                                            str = "Album Detail";
                                        }
                                        u.a().a("Shuffle Product", "Gaana+ popup", str);
                                    } else if (DownloadSongsItemView.this.mFragment.getParentFragment() != null && (DownloadSongsItemView.this.mFragment.getParentFragment() instanceof ArtistDetailsMaterialListing)) {
                                        u.a().a("Shuffle Product", "Gaana+ popup", "Artist");
                                    } else if (DownloadSongsItemView.this.mFragment instanceof GaanaSpecialDetailsMaterialListing) {
                                        u.a().a("Shuffle Product", "Gaana+ popup", "Gaana Special");
                                    } else if ((DownloadSongsItemView.this.mFragment instanceof SongParallexListingFragment) || (DownloadSongsItemView.this.mFragment instanceof ListingFragment)) {
                                        u.a().a("Shuffle Product", "Gaana+ popup", "Songs Detail");
                                    }
                                }
                                Constants.g--;
                                Util.a(DownloadSongsItemView.this.mContext, BLOCK_ACTION.SHUFFLE);
                            } else if (DownloadSongsItemView.this.mFragment == null) {
                            } else {
                                if (DownloadSongsItemView.this.mFragment instanceof AlbumDetailsMaterialListing) {
                                    ((AlbumDetailsMaterialListing) DownloadSongsItemView.this.mFragment).j();
                                } else if (DownloadSongsItemView.this.mFragment instanceof RevampedDetailListing) {
                                    ((RevampedDetailListing) DownloadSongsItemView.this.mFragment).t();
                                } else if (DownloadSongsItemView.this.mFragment.getParentFragment() != null && (DownloadSongsItemView.this.mFragment.getParentFragment() instanceof ArtistDetailsMaterialListing)) {
                                    ((ArtistDetailsMaterialListing) DownloadSongsItemView.this.mFragment.getParentFragment()).a();
                                } else if (DownloadSongsItemView.this.mFragment instanceof GaanaSpecialDetailsMaterialListing) {
                                    ((GaanaSpecialDetailsMaterialListing) DownloadSongsItemView.this.mFragment).a();
                                } else if (DownloadSongsItemView.this.mFragment instanceof SongParallexListingFragment) {
                                    ((SongParallexListingFragment) DownloadSongsItemView.this.mFragment).c();
                                } else if (DownloadSongsItemView.this.mFragment instanceof ListingFragment) {
                                    ((ListingFragment) DownloadSongsItemView.this.mFragment).o();
                                }
                            }
                        }
                    });
                } else {
                    findViewById.setVisibility(8);
                }
            }
            if (Constants.ab) {
                findViewById = view2.findViewById(R.id.item_player_overlay);
                if (findViewById != null) {
                    findViewById.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            if (Constants.ab && ((GaanaActivity) DownloadSongsItemView.this.mContext).isSlidingPanelExpanded()) {
                                u.a().a("Shuffle Product", "Gaana+ popup", "Player Queue");
                                Util.a(DownloadSongsItemView.this.mContext, BLOCK_ACTION.SHUFFLE);
                            }
                        }
                    });
                }
            }
            return view2;
        }
    }

    private View initTrackDownloadSelectionMode(Track track, View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.select_icon);
        int[] iArr = new int[]{R.attr.select_icon, R.attr.unselect_icon};
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        if (TrackSelectionForDownload.a().a(track.getBusinessObjId(), this.mDownloadSelectionType)) {
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(85, -1)));
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(88, -1)));
        }
        obtainStyledAttributes.recycle();
        return view;
    }

    private View initTrackSelectionModeForVideo(final Track track, View view) {
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
        checkBox.setVisibility(0);
        if (al.a().c(track, true)) {
            checkBox.setChecked(true);
        } else if (al.a().e()) {
            checkBox.setChecked(true);
            if (al.a().e()) {
                al.a().a(track, true);
            }
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (al.a().c(track, true)) {
                    al.a().b(track, true);
                    checkBox.setChecked(false);
                } else if (al.a().f() > 100) {
                    aj.a().a(DownloadSongsItemView.this.mContext, DownloadSongsItemView.this.mContext.getResources().getString(R.string.selection_exceed_message_100_songs));
                    return;
                } else {
                    al.a().a(track, true);
                    checkBox.setChecked(true);
                }
                DownloadSongsItemView.this.setUpdateTrackSelectionCount();
            }
        });
        return this.mView;
    }

    private View initTrackSelectionMode(final Track track, View view) {
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
        this.downloadImage.setVisibility(8);
        this.mView.findViewById(R.id.rate_progress_bar).setVisibility(8);
        this.mCrossFadeImageIcon.setVisibility(8);
        if (this.mThumbnailRightTopIndicator != null) {
            this.mThumbnailRightTopIndicator.setVisibility(8);
        }
        if (this.mCrossFadeImageIconLyt != null) {
            this.mCrossFadeImageIconLyt.setVisibility(8);
        }
        View findViewById = view.findViewById(R.id.clickoptionLayout);
        if (findViewById != null) {
            findViewById.setVisibility(4);
        }
        checkBox.setVisibility(0);
        if (al.a().c(track, true)) {
            checkBox.setChecked(true);
        } else if (al.a().e()) {
            checkBox.setChecked(true);
            if (al.a().e()) {
                al.a().a(track, true);
            }
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (al.a().c(track, true)) {
                    al.a().b(track, true);
                    checkBox.setChecked(false);
                } else if (al.a().f() > 100) {
                    aj.a().a(DownloadSongsItemView.this.mContext, DownloadSongsItemView.this.mContext.getResources().getString(R.string.selection_exceed_message_100_songs));
                    return;
                } else {
                    al.a().a(track, true);
                    checkBox.setChecked(true);
                }
                DownloadSongsItemView.this.setUpdateTrackSelectionCount();
            }
        });
        setAnimationBlinker((PulsatorView) this.mView.findViewById(R.id.downloadPulse), track);
        return view;
    }

    private View initEditMode(final Track track, View view) {
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
        this.downloadImage.setVisibility(8);
        View findViewById = view.findViewById(R.id.clickoptionLayout);
        if (findViewById != null) {
            findViewById.setVisibility(4);
        }
        checkBox.setVisibility(0);
        if (i.a().c(track.getBusinessObjId(), true)) {
            checkBox.setChecked(true);
        } else if (i.a().g()) {
            checkBox.setChecked(true);
            i.a().a(track.getBusinessObjId(), true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (i.a().c(track.getBusinessObjId(), true)) {
                    i.a().b(track.getBusinessObjId(), true);
                    checkBox.setChecked(false);
                    return;
                }
                i.a().a(track.getBusinessObjId(), true);
                checkBox.setChecked(true);
            }
        });
        return view;
    }

    private void setUpdateTrackSelectionCount() {
        if ((this.mFragment instanceof AlbumDetailsMaterialListing) && !this.isPlayerQueue) {
            ((AlbumDetailsMaterialListing) this.mFragment).m();
        } else if ((this.mFragment instanceof RevampedDetailListing) && !this.isPlayerQueue) {
            ((RevampedDetailListing) this.mFragment).l();
        }
        if ((this.mFragment instanceof SongParallexListingFragment) && !this.isPlayerQueue) {
            ((SongParallexListingFragment) this.mFragment).j();
        }
        if ((this.mFragment instanceof GaanaSpecialDetailsMaterialListing) && !this.isPlayerQueue) {
            ((GaanaSpecialDetailsMaterialListing) this.mFragment).h();
        }
        if (isTrendingScreen() && !this.isPlayerQueue) {
            ((ListingFragment) this.mFragment).k();
        }
    }

    private boolean isTrendingScreen() {
        return ((this.mFragment instanceof ListingFragment) && !this.isCuratedDownloadItem) || (this.mFragment instanceof MyMusicItemFragment);
    }

    public void onClick(View view) {
        BaseFragment baseFragment = ((GaanaActivity) this.mContext).getmCurrentPlayerFragment();
        if (Constants.ab && ((GaanaActivity) this.mContext).isSlidingPanelExpanded()) {
            u.a().a("Shuffle Product", "Gaana+ popup", "Player Queue");
            Util.a(this.mContext, BLOCK_ACTION.SHUFFLE);
            return;
        }
        BusinessObject businessObject = (BusinessObject) view.getTag();
        if (this.isPlayerQueue) {
            an.a().a("click", "ac", "", "queue", "", "play", "", "");
            PlayerTrack i = PlayerManager.a(this.mContext).i();
            if (!(i == null || i.b() == null || businessObject.getBusinessObjId().equals(i.h()))) {
                if (baseFragment instanceof PlayerFragmentV2) {
                    ((PlayerFragmentV2) baseFragment).k();
                } else if (baseFragment instanceof PlayerFragmentV4) {
                    ((PlayerFragmentV4) baseFragment).q();
                }
            }
        }
        if (businessObject instanceof Item) {
            Item item = (Item) businessObject;
            if (item.getEntityType().equals(c.c)) {
                businessObject = (Track) populateTrackClicked(item);
            } else if (item.getEntityType().equals(c.b)) {
                businessObject = (Album) populateAlbumClicked(item);
            } else if (item.getEntityType().equals(c.a)) {
                businessObject = (Playlist) populatePlaylistClicked(item);
            } else if (item.getEntityType().equals(com.constants.c.d.d) || item.getEntityType().equals(com.constants.c.d.c)) {
                businessObject = (Radio) populateRadioClicked(item);
            }
        }
        if (businessObject instanceof Track) {
            Track track = (Track) businessObject;
            CheckBox checkBox;
            if (track.isFreeDownloadEnabled() && (this.mFragment instanceof CuratedDownloadSuggestionFragment)) {
                DownloadStatus e = DownloadManager.c().e(Integer.parseInt(track.getBusinessObjId()));
                if (e == null || e == DownloadStatus.PAUSED || e == DownloadStatus.TRIED_BUT_FAILED) {
                    ImageView imageView = (ImageView) view.findViewById(R.id.select_icon);
                    TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    BaseGaanaFragment currentFragment;
                    if (TrackSelectionForDownload.a().a(track.getBusinessObjId()) >= 0) {
                        if (TrackSelectionForDownload.a().e(this.mDownloadSelectionType) && this.onSelectAllStatusChangeListener != null) {
                            this.onSelectAllStatusChangeListener.onSelectAllStausChanged(false);
                        }
                        TrackSelectionForDownload.a().c(track, this.mDownloadSelectionType);
                        currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
                        if (currentFragment instanceof CuratedDownloadSuggestionFragment) {
                            ((CuratedDownloadSuggestionFragment) currentFragment).a();
                        }
                        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(88, -1)));
                    } else {
                        TrackSelectionForDownload.a().a((BusinessObject) track, this.mDownloadSelectionType);
                        currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
                        if (currentFragment instanceof CuratedDownloadSuggestionFragment) {
                            ((CuratedDownloadSuggestionFragment) currentFragment).a();
                        }
                        if (TrackSelectionForDownload.a().b(this.mDownloadSelectionType) && this.onSelectAllStatusChangeListener != null) {
                            TrackSelectionForDownload.a().a(this.mDownloadSelectionType, true);
                            this.onSelectAllStatusChangeListener.onSelectAllStausChanged(true);
                        }
                        imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(85, -1)));
                    }
                    obtainStyledAttributes.recycle();
                    return;
                }
                return;
            } else if (al.a().d() && (((this.mFragment instanceof SongParallexListingFragment) || (this.mFragment instanceof AlbumDetailsMaterialListing) || (this.mFragment instanceof GaanaSpecialDetailsMaterialListing) || (this.mFragment instanceof RevampedDetailListing) || isTrendingScreen()) && !this.isPlayerQueue)) {
                checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
                if (al.a().f() > 100) {
                    aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.selection_exceed_message_100_songs));
                    return;
                }
                if (al.a().c(track, true)) {
                    al.a().b(track, true);
                    checkBox.setChecked(false);
                } else {
                    al.a().a(track, true);
                    checkBox.setChecked(true);
                }
                setUpdateTrackSelectionCount();
                return;
            } else if (!track.isLocalMedia()) {
                if ((this.mFragment instanceof DownloadDetailsFragment) && i.a().f()) {
                    checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
                    if (i.a().c(track.getBusinessObjId(), true)) {
                        i.a().b(track.getBusinessObjId(), true);
                        checkBox.setChecked(false);
                    } else {
                        i.a().a(track.getBusinessObjId(), true);
                        checkBox.setChecked(true);
                    }
                    return;
                } else if ("1".equalsIgnoreCase(track.getLocationAvailability()) && "0".equalsIgnoreCase(track.getDeviceAvailability())) {
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
                    return;
                } else if ("0".equalsIgnoreCase(track.getLocationAvailability()) && "1".equalsIgnoreCase(track.getDeviceAvailability())) {
                    ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
                    return;
                } else if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue()) {
                    ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_song));
                    return;
                } else if (!(Util.j(this.mContext) || DownloadManager.c().a(track).booleanValue() || DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue())) {
                    if (this.isPlayerQueue) {
                        aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.error_msg_no_connection));
                    } else {
                        ap.a().f(this.mContext);
                    }
                    return;
                }
            }
        }
        if (this.mFragment instanceof RevampedDetailListing) {
            ((RevampedDetailListing) this.mFragment).e();
        }
        PulsatorView.resetPulsatorFlag();
        if ((view instanceof ConstraintLayout) && (((ConstraintLayout) view).getChildAt(0) instanceof VideoPlayerAutoPlayView)) {
            setVideoListingView(true);
        } else {
            setVideoListingView(false);
        }
        super.onClick(view);
    }

    public View getPopulatedOfflineTrackView(ViewHolder viewHolder, BusinessObject businessObject, String str) {
        PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder;
        this.mView = playlistGridHolder.itemView;
        this.mView.setTag(businessObject);
        this.mView.setOnClickListener(this);
        OfflineTrack offlineTrack = (OfflineTrack) businessObject;
        String imageUrl = offlineTrack.getImageUrl();
        if (!(Constants.cN || imageUrl == null)) {
            imageUrl = imageUrl.replace("80x80", "175x175");
        }
        boolean isParentalWarningEnabled = offlineTrack.isParentalWarningEnabled();
        playlistGridHolder.play_icon.setOnClickListener(this);
        playlistGridHolder.play_icon.setTag(businessObject);
        playlistGridHolder.play_icon.setVisibility(0);
        playlistGridHolder.crossFadeImageView.bindImage(imageUrl, this.mAppState.isAppInOfflineMode());
        playlistGridHolder.crossFadeImageView.setScaleType(ScaleType.FIT_XY);
        if (this.mItemWithoutText) {
            playlistGridHolder.tvTopHeading.setVisibility(8);
        } else {
            playlistGridHolder.tvTopHeading.setVisibility(0);
            if (isParentalWarningEnabled) {
                Util.a(playlistGridHolder.tvTopHeading, businessObject.getName());
            } else {
                playlistGridHolder.tvTopHeading.setText(businessObject.getName());
            }
            playlistGridHolder.tvTopHeading.setTextAppearance(this.mContext, R.style.grid_caption);
        }
        s.a().a(playlistGridHolder.imgLightOverlay, this.mLightsOn);
        return this.mView;
    }

    private void deleteDownload(Track track) {
        DownloadManager.c().d(track.getBusinessObjId());
        updateOfflineTracksStatus();
    }

    /* Access modifiers changed, original: protected */
    public void setMusicPlayingAnimation(CrossFadeImageView crossFadeImageView, final ImageView imageView, BusinessObject businessObject, ImageView imageView2, FrameLayout frameLayout) {
        if (imageView != null) {
            PlayerTrack i = PlayerManager.a(this.mContext).i();
            Object entityId;
            if (businessObject instanceof Item) {
                entityId = ((Item) businessObject).getEntityId();
            } else {
                entityId = businessObject.getBusinessObjId();
            }
            if (i != null && i.h().equals(entityId)) {
                if (((GaanaActivity) this.mContext).getPlayerStates() == PlayerStates.PLAYING && GaanaMusicService.s().isPlaying()) {
                    if (this.isPlayerQueue) {
                        bindImage(crossFadeImageView, imageView2, frameLayout, businessObject, true, false);
                        imageView.setVisibility(0);
                        imageView.setImageResource(R.drawable.vector_player_pause_white);
                        imageView.setOnClickListener(new OnClickListener() {
                            public void onClick(View view) {
                                if (PlayerStatus.b(DownloadSongsItemView.this.mContext) == PlayerStates.PLAYING) {
                                    if (GaanaMusicService.t()) {
                                        o.a(DownloadSongsItemView.this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
                                    }
                                    imageView.setImageResource(R.drawable.vector_player_play_white);
                                    return;
                                }
                                o.c(DownloadSongsItemView.this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
                                imageView.setImageResource(R.drawable.vector_player_pause_white);
                            }
                        });
                    } else {
                        AnimationDrawable animationDrawable = (AnimationDrawable) ContextCompat.getDrawable(this.mContext, R.drawable.ic_equalizer_white_36dp);
                        DrawableCompat.setTintList(animationDrawable, Util.b(true));
                        imageView.setImageDrawable(animationDrawable);
                        imageView.setVisibility(0);
                        animationDrawable.start();
                    }
                    bindImage(crossFadeImageView, imageView2, frameLayout, businessObject, true, false);
                } else {
                    if (this.isPlayerQueue) {
                        imageView.setVisibility(0);
                        imageView.setImageResource(R.drawable.vector_player_play_white);
                    } else {
                        if (imageView.getAnimation() != null) {
                            imageView.getAnimation().cancel();
                        }
                        imageView.setVisibility(0);
                        imageView.setImageResource(R.drawable.ic_equalizer1_white_36dp);
                    }
                    bindImage(crossFadeImageView, imageView2, frameLayout, businessObject, true, false);
                }
                return;
            } else if (imageView.getVisibility() == 0) {
                imageView.setVisibility(8);
                if (imageView.getAnimation() != null) {
                    imageView.getAnimation().cancel();
                }
            }
        }
        bindImage(crossFadeImageView, imageView2, frameLayout, businessObject, false, false);
    }

    private void setAnimationBlinker(PulsatorView pulsatorView, BusinessObject businessObject) {
        if (!d.a().b("PREFERENCE_KEY_DOWNLOAD_CLICK_INITIATED", false, false)) {
            int b = d.a().b("DOWNLOAD_BLINKER_ANIMATION", 0, false);
            if (b < 4) {
                int b2 = d.a().b("SESSION_OCCURENCE_BLINKER_ANIMATION", 0, false);
                int i = b2 + 2;
                if (b2 > 0) {
                    if (GaanaApplication.sessionHistoryCount + 1 >= i) {
                        blinkerAnimationAction(pulsatorView, businessObject, b);
                    }
                } else if (b == 0 && GaanaApplication.sessionHistoryCount + 1 >= 1) {
                    blinkerAnimationAction(pulsatorView, businessObject, b);
                }
            }
        }
    }

    private void blinkerAnimationAction(PulsatorView pulsatorView, BusinessObject businessObject, int i) {
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
        if (e == DownloadStatus.DOWNLOADED || e == DownloadStatus.DOWNLOADING || e == DownloadStatus.QUEUED || e == DownloadStatus.PARTIALLY_DOWNLOADED) {
            pulsatorView.setVisibility(4);
            pulsatorView.clear();
        } else if (businessObject.isLocalMedia()) {
            pulsatorView.clear();
            pulsatorView.setVisibility(4);
        } else if (al.a) {
            pulsatorView.setVisibility(4);
            pulsatorView.clear();
        } else {
            Object entityId;
            PlayerTrack i2 = PlayerManager.a(this.mContext).i();
            if (businessObject instanceof Item) {
                entityId = ((Item) businessObject).getEntityId();
            } else {
                entityId = businessObject.getBusinessObjId();
            }
            if (i2 == null || !i2.h().equals(entityId)) {
                pulsatorView.setVisibility(4);
                pulsatorView.stop();
                pulsatorView.clear();
                if (e == DownloadStatus.DOWNLOADING) {
                    if (DownloadManager.c().w()) {
                        this.downloadImage.setVisibility(4);
                    } else {
                        this.downloadImage.setVisibility(0);
                    }
                }
            } else {
                PlayerStates playerStates = ((GaanaActivity) this.mContext).getPlayerStates();
                if (playerStates == PlayerStates.PLAYING && GaanaMusicService.s().isPlaying()) {
                    if (e == null || e == DownloadStatus.PAUSED || e == DownloadStatus.TRIED_BUT_FAILED) {
                        float dimension = this.mContext.getResources().getDimension(R.dimen.gif_download_image_height);
                        float dimension2 = this.mContext.getResources().getDimension(R.dimen.gif_download_image_width);
                        float dimension3 = this.mContext.getResources().getDimension(R.dimen.gif_download_image_padding_right);
                        LayoutParams layoutParams = (LayoutParams) pulsatorView.getLayoutParams();
                        layoutParams.width = (int) dimension2;
                        layoutParams.height = (int) dimension;
                        layoutParams.leftMargin = (int) dimension3;
                        if (pulsatorView.getCycleCompleted()) {
                            pulsatorView.clear();
                            pulsatorView.setVisibility(8);
                            return;
                        }
                        pulsatorView.clear();
                        pulsatorView.setVisibility(0);
                        pulsatorView.build();
                        pulsatorView.start();
                        d.a().a("DOWNLOAD_BLINKER_ANIMATION", i + 1, false);
                        d.a().a("SESSION_OCCURENCE_BLINKER_ANIMATION", GaanaApplication.sessionHistoryCount, false);
                        BusinessObject businessObject2 = null;
                        BaseGaanaFragment currentFragment = ((GaanaActivity) this.mContext).getCurrentFragment();
                        if (currentFragment instanceof ArtistDetailsMaterialListing) {
                            businessObject2 = ((ArtistDetailsMaterialListing) currentFragment).c();
                        } else if (currentFragment instanceof AlbumDetailsMaterialListing) {
                            businessObject2 = ((AlbumDetailsMaterialListing) currentFragment).c();
                        } else if (currentFragment instanceof RevampedDetailListing) {
                            businessObject2 = ((RevampedDetailListing) currentFragment).s();
                        } else if (currentFragment instanceof GaanaSpecialDetailsMaterialListing) {
                            businessObject2 = ((GaanaSpecialDetailsMaterialListing) currentFragment).d();
                        }
                        if (businessObject2 != null) {
                            if (businessObject2 instanceof Playlist) {
                                Playlist playlist = (Playlist) businessObject2;
                                u.a().a("Downloads: PlaylistView", "Download blinker appeared", "");
                            } else if (businessObject2 instanceof Album) {
                                u.a().a("Downloads: AlbumlistView", "Download blinker appeared", "");
                            } else if (businessObject2 instanceof Artist) {
                                u.a().a("Downloads: ArtistlistView", "Download blinker appeared", "");
                            }
                        }
                        this.currentSongBlinkingTrackId = entityId;
                    } else {
                        if (e == DownloadStatus.DOWNLOADING) {
                            if (DownloadManager.c().w()) {
                                this.downloadImage.setVisibility(4);
                            } else {
                                this.downloadImage.setVisibility(0);
                            }
                        }
                        pulsatorView.setVisibility(4);
                        pulsatorView.stop();
                        pulsatorView.clear();
                    }
                    return;
                }
                if (e == DownloadStatus.DOWNLOADING) {
                    if (DownloadManager.c().w()) {
                        this.downloadImage.setVisibility(4);
                    } else {
                        this.downloadImage.setVisibility(0);
                    }
                    pulsatorView.setVisibility(4);
                    pulsatorView.stop();
                    pulsatorView.clear();
                }
                if (playerStates == PlayerStates.PAUSED) {
                    pulsatorView.setVisibility(4);
                    pulsatorView.stop();
                    pulsatorView.clear();
                }
            }
        }
    }

    public void bindImage(CrossFadeImageView crossFadeImageView, ImageView imageView, FrameLayout frameLayout, BusinessObject businessObject, boolean z, boolean z2) {
        int i = z ^ 1;
        if (!(i == 0 || this.mAppState.getListingComponents() == null || this.isPlayerQueue)) {
            BusinessObject a = this.mAppState.getListingComponents().a();
            i = ((a == null || a.getBusinessObjType() != BusinessObjectType.Albums) && !(this.mFragment instanceof GaanaSpecialDetailsMaterialListing)) ? 0 : 1;
        }
        if (i == 0 || this.isPlayerQueue || (this.mFragment instanceof DynamicHomeFragment) || (this.mFragment instanceof RevampedDetailListing) || (this.mFragment instanceof GaanaEducativeFragment)) {
            crossFadeImageView.setVisibility(0);
            String str = "";
            if (i == 0) {
                if (businessObject instanceof Item) {
                    str = ((Item) businessObject).getPremiumContent();
                } else if (businessObject instanceof Track) {
                    str = ((Track) businessObject).getPremiumContent();
                }
                if (imageView != null) {
                    if (Constants.cV.equalsIgnoreCase(str)) {
                        imageView.setVisibility(0);
                    } else {
                        imageView.setVisibility(8);
                    }
                }
                if (frameLayout != null) {
                    frameLayout.setVisibility(0);
                }
            }
            String artworkSpecific = businessObject instanceof Item ? z2 ? ((Item) businessObject).getArtworkSpecific() : ((Item) businessObject).getArtwork() : z2 ? ((Track) businessObject).getArtworkSpecific() : ((Track) businessObject).getArtwork();
            if ((this.mFragment instanceof AlbumDetailsMaterialListing) || (this.mFragment instanceof RevampedDetailListing) || this.isPlayerQueue || (this.mFragment instanceof GaanaEducativeFragment)) {
                artworkSpecific = Util.d(this.mContext, artworkSpecific);
            } else {
                artworkSpecific = Util.g(this.mContext, artworkSpecific);
            }
            if (!TextUtils.isEmpty(artworkSpecific)) {
                crossFadeImageView.bindImage(businessObject, artworkSpecific, this.mAppState.isAppInOfflineMode());
            }
        } else {
            crossFadeImageView.setVisibility(8);
            if (imageView != null) {
                imageView.setVisibility(8);
            }
            if (frameLayout != null) {
                frameLayout.setVisibility(8);
            }
        }
        crossFadeImageView.setScaleType(ScaleType.FIT_XY);
    }

    public void updateUiForCircularProgressBar(final int i, final int i2) {
        if (this.mContext instanceof Activity) {
            ((Activity) this.mContext).runOnUiThread(new Runnable() {
                public void run() {
                    DownloadSongsItemView downloadSongsItemView = DownloadSongsItemView.this;
                    if (DownloadSongsItemView.rateTextCircularProgressBarPlayer != null) {
                        downloadSongsItemView = DownloadSongsItemView.this;
                        DownloadSongsItemView.rateTextCircularProgressBarPlayer.setProgress(DownloadSongsItemView.this.calculatePercentage(i, i2));
                    }
                    if (DownloadSongsItemView.this.rateTextCircularProgressBar != null) {
                        DownloadSongsItemView.this.rateTextCircularProgressBar.setProgress(DownloadSongsItemView.this.calculatePercentage(i, i2));
                    }
                }
            });
        }
    }

    private int calculatePercentage(int i, int i2) {
        return (i <= 0 || i2 <= 0) ? 0 : (i2 * 100) / i;
    }

    public static void resetStatiView() {
        if (rateTextCircularProgressBarPlayer != null) {
            rateTextCircularProgressBarPlayer = null;
        }
    }

    public View getGridItemView(ViewHolder viewHolder, BusinessObject businessObject, am amVar) {
        this.onSelectAllStatusChangeListener = amVar;
        return getGridItemView(viewHolder, businessObject);
    }

    public View getGridItemView(ViewHolder viewHolder, BusinessObject businessObject) {
        return getGridItemViewforDynamicView(viewHolder, businessObject, null);
    }

    public View getGridItemViewforDynamicView(ViewHolder viewHolder, BusinessObject businessObject, f.a aVar) {
        BusinessObject businessObject2;
        CharSequence charSequence;
        boolean z;
        ViewHolder viewHolder2 = viewHolder;
        final BusinessObject businessObject3 = businessObject;
        this.mView = viewHolder2.itemView;
        this.mView = super.getPoplatedView(this.mView, businessObject3);
        this.mBusinessObject = businessObject3;
        String str = "";
        boolean z2 = false;
        if (businessObject3 instanceof Item) {
            businessObject2 = (Item) businessObject3;
            ArrayList arrayList = (ArrayList) businessObject2.getEntityInfo();
            if (arrayList != null) {
                int size = arrayList.size();
                charSequence = str;
                int i = 0;
                z = i;
                while (i < size) {
                    if (((EntityInfo) arrayList.get(i)).getKey().equals("parental_warning")) {
                        z = ((EntityInfo) arrayList.get(i)).getValue() instanceof Double ? Double.compare(((Double) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue(), 1.0d) == 0 : ((EntityInfo) arrayList.get(i)).getValue().equals("1");
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("play_ct")) {
                        charSequence = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    } else if (isFreeDownloadItem((EntityInfo) arrayList.get(i))) {
                        Object value = ((EntityInfo) arrayList.get(i)).getValue();
                        int intValue = value instanceof Double ? ((Double) value).intValue() : value instanceof String ? Integer.parseInt((String) value) : value instanceof Integer ? ((Integer) value).intValue() : 0;
                        if (intValue == 1) {
                            this.download_enabled = true;
                        }
                    }
                    i++;
                }
            } else {
                charSequence = str;
                z = false;
            }
            str = ((Item) businessObject2).getPremiumContent();
        } else {
            BusinessObject businessObject4 = (Track) businessObject3;
            z = businessObject4.isParentalWarningEnabled();
            charSequence = businessObject4.getPlayCount();
            businessObject2 = businessObject4;
            str = businessObject4.getPremiumContent();
        }
        if (viewHolder2 instanceof CuratedDownloadSongSelectionHolder) {
            populateCuratedGridView(viewHolder2, businessObject2);
        } else {
            PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder2;
            this.tvTitle = playlistGridHolder.tvTopHeading;
            boolean b = d.a().b("PREFERENCE_DOWNLOAD_SETTINGS_ENABLED", false, false);
            if (aVar == null || aVar.j() == null || !b || !aVar.j().containsKey("showDownloads") || !((String) aVar.j().get("showDownloads")).equals("1")) {
                this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            } else if (aVar.j() != null && aVar.j().containsKey("showDownloads") && ((String) aVar.j().get("showDownloads")).equals("1")) {
                DownloadStatus e = DownloadManager.c().e(Integer.parseInt(((Item) businessObject3).getEntityId()));
                this.tvTitle.setOnTouchListener(new OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 1 && motionEvent.getRawX() >= ((float) (DownloadSongsItemView.this.tvTitle.getRight() - DownloadSongsItemView.this.tvTitle.getTotalPaddingRight()))) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("");
                            stringBuilder.append(DownloadSongsItemView.this.mHeader);
                            stringBuilder.append(":");
                            stringBuilder.append(((Item) businessObject3).getBusinessObjId());
                            u.a().a("Download", "Click", stringBuilder.toString());
                            if (DownloadManager.c().e(Integer.parseInt(((Item) businessObject3).getEntityId())) == DownloadStatus.DOWNLOADED) {
                                new CustomDialogView(DownloadSongsItemView.this.mContext, DownloadSongsItemView.this.mContext.getResources().getString(R.string.toast_delete_downloaded_song), new OnButtonClickListener() {
                                    public void onNegativeButtonClick() {
                                    }

                                    public void onPositiveButtonClick() {
                                        int i;
                                        DownloadSongsItemView.this.deleteDownload((Track) DownloadSongsItemView.this.populateTrackClicked((Item) businessObject3));
                                        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(((Item) businessObject3).getEntityId()));
                                        for (EntityInfo access$900 : ((Item) businessObject3).getEntityInfo()) {
                                            if (DownloadSongsItemView.this.isFreeDownloadItem(access$900)) {
                                                i = 1;
                                                break;
                                            }
                                        }
                                        i = false;
                                        if (i != 0) {
                                            DownloadSongsItemView.this.updateFreeDownloadImage(DownloadSongsItemView.this.downloadImage, e, false);
                                        } else {
                                            DownloadSongsItemView.this.updateDownloadImage(DownloadSongsItemView.this.downloadImage, e);
                                        }
                                        TypedArray obtainStyledAttributes = DownloadSongsItemView.this.mContext.obtainStyledAttributes(new int[]{R.attr.download_button_paused});
                                        Drawable drawable = obtainStyledAttributes.getDrawable(0);
                                        obtainStyledAttributes.recycle();
                                        DownloadSongsItemView.this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                                        if (DownloadSongsItemView.this.mFragment != null && DownloadSongsItemView.this.mFragment.isAdded()) {
                                            DownloadSongsItemView.this.mFragment.refreshListView();
                                        }
                                    }
                                }).show();
                            } else {
                                d.a().a("PREFERENCE_KEY_DOWNLOAD_CLICK_INITIATED", true, false);
                                if (ap.a().j()) {
                                    DownloadSongsItemView.this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(DownloadSongsItemView.this.mContext, R.drawable.vector_download_queued), null);
                                }
                                DownloadSongsItemView.this.startDownload((Track) DownloadSongsItemView.this.populateTrackClicked((Item) businessObject3));
                                j.a(DownloadSongsItemView.this.mContext).a(DownloadSongsItemView.this);
                                return true;
                            }
                        }
                        return true;
                    }
                });
                TypedArray obtainStyledAttributes;
                Drawable drawable;
                if (e == null) {
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.download_button_paused});
                    drawable = AppCompatResources.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(0, -1));
                    obtainStyledAttributes.recycle();
                    this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                } else if (e == DownloadStatus.DOWNLOADING) {
                    this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this.mContext, R.drawable.vector_download_queued), null);
                } else if (e == DownloadStatus.DOWNLOADED) {
                    if (ap.a().o()) {
                        this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this.mContext, R.drawable.vector_download_completed), null);
                    } else if (ap.a().m()) {
                        if (DownloadManager.c().j(businessObject.getBusinessObjId()).booleanValue()) {
                            this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this.mContext, R.drawable.vector_download_completed), null);
                        } else {
                            new int[1][0] = R.attr.download_button_disabled;
                            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                            drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(12, -1));
                            obtainStyledAttributes.recycle();
                            this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                        }
                    } else if (!ap.a().k() || Util.a(businessObject)) {
                        this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this.mContext, R.drawable.vector_download_completed), null);
                    } else {
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(90, -1));
                        obtainStyledAttributes.recycle();
                        this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                    }
                } else if (e == DownloadStatus.QUEUED) {
                    this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this.mContext, R.drawable.vector_download_queued), null);
                } else if (e == DownloadStatus.TRIED_BUT_FAILED) {
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(91, -1));
                    obtainStyledAttributes.recycle();
                    this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                } else {
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.download_button_paused});
                    drawable = AppCompatResources.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(0, -1));
                    obtainStyledAttributes.recycle();
                    this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                }
            } else {
                this.tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
            if (!this.mItemWithoutText) {
                this.tvTitle.setVisibility(0);
            }
            if (z) {
                Util.a(this.tvTitle, businessObject2.getName());
            } else {
                this.tvTitle.setText(businessObject2.getName());
            }
            if (this.download_enabled && isFreeUser() && "klD".equals(getUniqueID())) {
                this.tvTitle.setVisibility(8);
                playlistGridHolder.tvDownloadItemView.setVisibility(0);
                playlistGridHolder.tvHeadingDownloadItem.setText(businessObject2.getName());
                if (this.mView.findViewById(R.id.track_download_image) != null) {
                    this.download_image = (ImageView) this.mView.findViewById(R.id.track_download_image);
                    this.download_image.setImageDrawable(Util.b(this.mContext, (int) R.attr.free_download_icon));
                    Util.ab();
                    this.download_image.setVisibility(0);
                    this.download_image.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(((Item) businessObject3).getEntityId()));
                            if (e == null || e == DownloadStatus.PAUSED || e == DownloadStatus.TRIED_BUT_FAILED) {
                                DownloadSongsItemView.this.download_image.setImageDrawable(DownloadSongsItemView.this.getResources().getDrawable(R.drawable.vector_download_queued));
                                DownloadSongsItemView.this.startDownload((Track) DownloadSongsItemView.this.populateTrackClicked((Item) businessObject3));
                                j.a(DownloadSongsItemView.this.mContext).a(DownloadSongsItemView.this);
                                u.a().a("Free Download", "Click", ((BaseActivity) DownloadSongsItemView.this.mContext).currentScreen);
                            } else if (e == DownloadStatus.DOWNLOADED) {
                                new CustomDialogView(DownloadSongsItemView.this.mContext, DownloadSongsItemView.this.mContext.getResources().getString(R.string.toast_delete_downloaded_song), new OnButtonClickListener() {
                                    public void onNegativeButtonClick() {
                                    }

                                    public void onPositiveButtonClick() {
                                        DownloadSongsItemView.this.deleteDownload(DownloadSongsItemView.this.populateTrackClicked((Item) businessObject3));
                                        DownloadSongsItemView.this.download_image.setImageDrawable(Util.b(DownloadSongsItemView.this.mContext, (int) R.attr.free_download_icon));
                                        Util.ab();
                                    }
                                }).show();
                            }
                        }
                    });
                }
                setFreeTrackDownloadImage(businessObject3);
            } else {
                this.tvTitle.setVisibility(0);
                if (playlistGridHolder.tvDownloadItemView != null) {
                    playlistGridHolder.tvDownloadItemView.setVisibility(8);
                }
            }
            s.a().a(playlistGridHolder.imgLightOverlay, this.mLightsOn);
            playlistGridHolder.play_icon.setOnClickListener(this);
            playlistGridHolder.play_icon.setTag(businessObject3);
            playlistGridHolder.play_icon.setVisibility(0);
            if (playlistGridHolder.mTxtPlayCount != null) {
                if (Constants.aX) {
                    playlistGridHolder.mTxtPlayCount.setText(charSequence);
                    playlistGridHolder.mTxtPlayCount.setVisibility(0);
                } else {
                    playlistGridHolder.mTxtPlayCount.setVisibility(4);
                }
            }
            if (playlistGridHolder.mImgIndictor != null) {
                playlistGridHolder.mImgIndictor.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.vector_ic_tracks_indicator));
                playlistGridHolder.mImgIndictor.setVisibility(0);
            }
            this.mCrossFadeImageIcon = playlistGridHolder.crossFadeImageView;
            if (this.mCrossFadeImageIcon instanceof RoundedCornerImageView) {
                ((RoundedCornerImageView) this.mCrossFadeImageIcon).setHasGradient(true);
            }
            this.mThumbnailRightTopIndicator = playlistGridHolder.mThumbnailRightTopIndicator;
            this.mCrossFadeImageIconLyt = playlistGridHolder.crossFadeImageViewLyt;
            if (playlistGridHolder.mThumbnailRightTopIndicator != null) {
                if (Constants.cV.equalsIgnoreCase(str)) {
                    playlistGridHolder.mThumbnailRightTopIndicator.setVisibility(0);
                } else {
                    playlistGridHolder.mThumbnailRightTopIndicator.setVisibility(8);
                }
            }
            playlistGridHolder.itemView.setVisibility(0);
        }
        CrossFadeImageView crossFadeImageView = this.mCrossFadeImageIcon;
        ImageView imageView = this.mThumbnailRightTopIndicator;
        FrameLayout frameLayout = this.mCrossFadeImageIconLyt;
        if (aVar != null && aVar.e() == VIEW_SIZE.GRID_LARGE.getNumVal()) {
            z2 = true;
        }
        bindImage(crossFadeImageView, imageView, frameLayout, businessObject2, true, z2);
        return this.mView;
    }

    private boolean isFreeDownloadItem(EntityInfo entityInfo) {
        if (entityInfo.getKey().equals("download_enabled")) {
            if (entityInfo.getValue() instanceof Double) {
                if (Double.compare(((Double) entityInfo.getValue()).doubleValue(), 1.0d) == 0) {
                    return true;
                }
            } else if (entityInfo.getValue().equals("1")) {
                return true;
            }
        }
        return false;
    }

    private String getArtistName(BusinessObject businessObject) {
        String str = "";
        try {
            String b;
            int i = 0;
            for (EntityInfo key : ((Item) businessObject).getEntityInfo()) {
                if (key.getKey().equals("artist")) {
                    b = Constants.b(new JSONObject(new JSONArray(((EntityInfo) ((Item) businessObject).getEntityInfo().get(i)).getValue().toString()).getString(0)).get("name").toString());
                    break;
                }
                i++;
            }
            b = str;
            return b;
        } catch (Exception unused) {
            return str;
        }
    }

    private boolean isFreeUser() {
        return (GaanaApplication.getInstance().getCurrentUser() == null || !GaanaApplication.getInstance().getCurrentUser().getLoginStatus() || ap.a().d()) ? false : true;
    }

    private void setFreeTrackDownloadImage(BusinessObject businessObject) {
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(((Item) businessObject).getEntityId()));
        if (e == null) {
            return;
        }
        if (e == DownloadStatus.DOWNLOADING) {
            if (this.download_image != null) {
                this.download_image.setImageDrawable(getResources().getDrawable(R.drawable.vector_download_queued));
            }
        } else if (e == DownloadStatus.DOWNLOADED) {
            if (this.download_image != null) {
                this.download_image.setImageDrawable(getResources().getDrawable(R.drawable.vector_download_button_downloaded));
            }
        } else if (e == DownloadStatus.QUEUED) {
            if (this.download_image != null) {
                this.download_image.setImageDrawable(getResources().getDrawable(R.drawable.vector_download_queued));
            }
        } else if (e == DownloadStatus.TRIED_BUT_FAILED) {
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            Drawable drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(91, -1));
            obtainStyledAttributes.recycle();
            if (this.download_image != null) {
                this.download_image.setImageDrawable(drawable);
            }
        }
    }

    public void populateCuratedGridView(ViewHolder viewHolder, BusinessObject businessObject) {
        CharSequence charSequence;
        boolean z;
        CuratedDownloadSongSelectionHolder curatedDownloadSongSelectionHolder = (CuratedDownloadSongSelectionHolder) viewHolder;
        this.tvTitle = curatedDownloadSongSelectionHolder.tvTopHeading;
        this.tvSubtitle = curatedDownloadSongSelectionHolder.tvBottomHeading;
        if (this.mItemWithoutText) {
            this.tvTitle.setVisibility(8);
        } else {
            this.tvTitle.setVisibility(0);
            this.tvTitle.setText(businessObject.getName());
            this.tvTitle.setTextAppearance(this.mContext, R.style.grid_caption);
        }
        curatedDownloadSongSelectionHolder.select_icon.setVisibility(0);
        int[] iArr = new int[]{R.attr.select_icon, R.attr.unselect_icon};
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        boolean z2 = true;
        if (TrackSelectionForDownload.a().a(businessObject.getBusinessObjId()) >= 0) {
            curatedDownloadSongSelectionHolder.select_icon.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(85, -1)));
            if (!TrackSelectionForDownload.a().a(businessObject.getBusinessObjId(), this.mDownloadSelectionType)) {
                TrackSelectionForDownload.a().b(businessObject, this.mDownloadSelectionType);
                if (TrackSelectionForDownload.a().b(this.mDownloadSelectionType) && this.onSelectAllStatusChangeListener != null) {
                    TrackSelectionForDownload.a().a(this.mDownloadSelectionType, true);
                    this.onSelectAllStatusChangeListener.onSelectAllStausChanged(true);
                }
            }
        } else {
            if (TrackSelectionForDownload.a().a(businessObject.getBusinessObjId(), this.mDownloadSelectionType)) {
                TrackSelectionForDownload.a().d(businessObject, this.mDownloadSelectionType);
                if (this.onSelectAllStatusChangeListener != null) {
                    this.onSelectAllStatusChangeListener.onSelectAllStausChanged(false);
                }
            }
            curatedDownloadSongSelectionHolder.select_icon.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(88, -1)));
        }
        obtainStyledAttributes.recycle();
        this.mCrossFadeImageIcon = curatedDownloadSongSelectionHolder.crossFadeImageView;
        this.mThumbnailRightTopIndicator = curatedDownloadSongSelectionHolder.crossFadeImageView;
        this.mCrossFadeImageIconLyt = curatedDownloadSongSelectionHolder.crossFadeImageViewLyt;
        String str = "";
        if (businessObject instanceof Item) {
            String language = businessObject.getLanguage();
            ArrayList arrayList = (ArrayList) ((Item) businessObject).getEntityInfo();
            if (arrayList != null) {
                int size = arrayList.size();
                charSequence = str;
                for (int i = 0; i < size; i++) {
                    if (((EntityInfo) arrayList.get(i)).getKey().equals("artist_info")) {
                        charSequence = Constants.a((String) ((EntityInfo) arrayList.get(i)).getValue(), language);
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("parental_warning")) {
                        if (((EntityInfo) arrayList.get(i)).getValue() instanceof Double) {
                            if (Double.compare(((Double) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue(), 1.0d) != 0) {
                                z2 = false;
                            }
                            z = z2;
                        } else {
                            z = ((EntityInfo) arrayList.get(i)).getValue().equals("1");
                        }
                    }
                }
            } else {
                charSequence = str;
            }
            z = false;
        } else {
            Track track = (Track) businessObject;
            charSequence = track.getArtistNames();
            z = track.isParentalWarningEnabled();
        }
        if (charSequence == null || (this.mFragment instanceof CuratedDownloadSuggestionFragment)) {
            this.tvSubtitle.setVisibility(8);
            return;
        }
        this.tvSubtitle.setVisibility(0);
        this.tvSubtitle.setText(charSequence);
        int i2 = Constants.l ? R.drawable.vector_ic_explicit_content_white : R.drawable.vector_ic_explicit_content;
        if (z) {
            this.tvSubtitle.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this.mContext, i2), null, null, null);
        } else {
            this.tvSubtitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    public void setSongsListBusinessObject(ArrayList<?> arrayList) {
        this.mSongsListBusinessObject = arrayList;
    }

    public void setIsSongSection() {
        this.mIsSongSection = true;
    }

    public void setDownloadSelectionType(DownloadSelectionType downloadSelectionType) {
        this.mDownloadSelectionType = downloadSelectionType;
    }

    public void onPopupClicked(String str, BusinessObject businessObject) {
        downloadTrack(str, businessObject);
    }

    private void showOptionMenu(View view, boolean z) {
        BusinessObject businessObject;
        this.mBusinessObject = (BusinessObject) view.getTag();
        if ((this.mBusinessObject instanceof Item) && ((Item) this.mBusinessObject).getEntityType().equals("TR")) {
            businessObject = (Track) populateTrackClicked((Item) this.mBusinessObject);
        } else {
            businessObject = this.mBusinessObject;
            if ((businessObject instanceof Track) && TextUtils.isEmpty(((Track) businessObject).getAlbumId())) {
                Util.a(this.mContext, this.mFragment, businessObject, this.isPlayerQueue, null);
                return;
            }
        }
        BusinessObject businessObject2 = businessObject;
        PopupWindowView instance = PopupWindowView.getInstance(this.mContext, this.mFragment);
        if (this instanceof DownloadPopupListener) {
            instance.setDownloadPopupListener(this);
        }
        instance.contextPopupWindow(businessObject2, this.isPlayerQueue, false, z, false);
    }
}
