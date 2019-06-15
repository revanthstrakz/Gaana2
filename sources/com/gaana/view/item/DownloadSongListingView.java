package com.gaana.view.item;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.Constants;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.ArtistDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.FavoritesFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.fragments.ListingFragment;
import com.fragments.LocalMediaFragment;
import com.fragments.MyMusicItemFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.ai;
import com.managers.aj;
import com.managers.al;
import com.managers.an;
import com.managers.ap;
import com.managers.i;
import com.managers.j;
import com.managers.j.a;
import com.managers.o;
import com.managers.u;
import com.models.PlayerTrack;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;
import com.views.RateTextCircularProgressBar;
import java.util.ArrayList;

public class DownloadSongListingView extends SongsItemView implements DownloadPopupListener, a {
    private ImageView downloadImage;
    private String mSearchQuery;
    private RateTextCircularProgressBar rateTextCircularProgressBar;

    public static class DownloadSongListingHolder extends ViewHolder {
        private CheckBox checkBox;
        private ImageView downloadImage;
        private boolean isArtworkVisible = false;
        private CrossFadeImageView mCrossFadeImageIcon;
        public ImageView mThumbnailRightTopIndicator;
        private ImageView optionImageView;
        private ProgressBar progressBar;
        private TextView tvAlbumName;
        private TextView tvSongName;

        public DownloadSongListingHolder(View view, boolean z) {
            super(view);
            this.isArtworkVisible = z;
            this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.f38download.item.img.thumb);
            this.tvSongName = (TextView) view.findViewById(R.id.f43download.item.tv.trackname);
            this.tvAlbumName = (TextView) view.findViewById(R.id.f40download.item.tv.genere);
            this.downloadImage = (ImageView) view.findViewById(R.id.f37download.item.img.download);
            this.progressBar = (ProgressBar) view.findViewById(R.id.f34download.item.ProgressBar);
            this.optionImageView = (ImageView) view.findViewById(R.id.clickoptionImage);
            this.checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
            this.mThumbnailRightTopIndicator = (ImageView) view.findViewById(R.id.indicatorIconRightTop);
        }
    }

    public DownloadSongListingView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mLayoutId = R.layout.view_item_download;
        j.a(this.mContext).a(this);
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
        DownloadSongListingHolder downloadSongListingHolder = (DownloadSongListingHolder) viewHolder;
        this.mView = viewHolder.itemView;
        this.mView = super.getPoplatedView(this.mView, businessObject);
        j.a(this.mContext).a(this);
        return getDataFilledView(downloadSongListingHolder, businessObject);
    }

    private View getDataFilledView(DownloadSongListingHolder downloadSongListingHolder, final BusinessObject businessObject) {
        boolean isParentalWarningEnabled;
        if (downloadSongListingHolder.isArtworkVisible) {
            downloadSongListingHolder.mCrossFadeImageIcon.setVisibility(0);
        } else {
            downloadSongListingHolder.mCrossFadeImageIcon.setVisibility(8);
        }
        if (!downloadSongListingHolder.isArtworkVisible || TextUtils.isEmpty(businessObject.getAtw())) {
            if (downloadSongListingHolder.isArtworkVisible && (businessObject instanceof OfflineTrack)) {
                OfflineTrack offlineTrack = (OfflineTrack) businessObject;
                if (!TextUtils.isEmpty(offlineTrack.getImageUrl())) {
                    if (offlineTrack.isLocalMedia()) {
                        downloadSongListingHolder.mCrossFadeImageIcon.bindImageForLocalMedia(offlineTrack.getImageUrl(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
                    } else {
                        downloadSongListingHolder.mCrossFadeImageIcon.bindImage(offlineTrack.getImageUrl(), this.mAppState.isAppInOfflineMode());
                    }
                }
            }
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            downloadSongListingHolder.mCrossFadeImageIcon.setImageDrawable(drawable);
        } else {
            downloadSongListingHolder.mCrossFadeImageIcon.bindImage(businessObject.getAtw());
        }
        TextView access$200 = downloadSongListingHolder.tvSongName;
        TextView access$300 = downloadSongListingHolder.tvAlbumName;
        access$300.setVisibility(0);
        ImageView imageView = (ImageView) this.mView.findViewById(R.id.smart_download_label);
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
        boolean z = businessObject instanceof OfflineTrack;
        if (z && ((OfflineTrack) businessObject).getSmartDownload() == 1 && e != null && e != DownloadStatus.PAUSED) {
            imageView.setVisibility(0);
        } else if (!(businessObject instanceof Track) || ((Track) businessObject).getSmartDownload() != 1 || e == null || e == DownloadStatus.PAUSED) {
            imageView.setVisibility(8);
        } else {
            imageView.setVisibility(0);
        }
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                u.a().a("Smart Download", "Notify", "Snackbar");
                ((GaanaActivity) ai.a()).showSmartDownloadToastMessage("settings", DownloadSongListingView.this.mContext);
            }
        });
        access$200.setText(Util.c(this.mSearchQuery, businessObject.getName()));
        if (z) {
            OfflineTrack offlineTrack2 = (OfflineTrack) businessObject;
            isParentalWarningEnabled = offlineTrack2.isParentalWarningEnabled();
            access$300.setText(Util.c(this.mSearchQuery, getSubtitleText(offlineTrack2.getAlbumName(), offlineTrack2.getArtistName())));
        } else if (businessObject instanceof Track) {
            Track track = (Track) businessObject;
            isParentalWarningEnabled = track.isParentalWarningEnabled();
            access$300.setText(Util.c(this.mSearchQuery, getSubtitleText(track.getAlbumTitle(), track.getArtistNames())));
            if (downloadSongListingHolder.isArtworkVisible && downloadSongListingHolder.mThumbnailRightTopIndicator != null) {
                if (Constants.cV.equalsIgnoreCase(track.getPremiumContent())) {
                    downloadSongListingHolder.mThumbnailRightTopIndicator.setVisibility(0);
                } else {
                    downloadSongListingHolder.mThumbnailRightTopIndicator.setVisibility(8);
                }
            }
        } else {
            isParentalWarningEnabled = false;
        }
        int i = Constants.l ? R.drawable.vector_ic_explicit_content_white : R.drawable.vector_ic_explicit_content;
        if (isParentalWarningEnabled) {
            access$300.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(i), null, null, null);
        } else {
            access$300.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        final int a = Util.a(businessObject.getBusinessObjId());
        this.downloadImage = downloadSongListingHolder.downloadImage;
        if (businessObject.isLocalMedia() || !this.mAppState.isAppInOfflineMode() || DownloadManager.c().l(a).booleanValue()) {
            PlayerTrack i2 = PlayerManager.a(this.mContext).i();
            if (i2 == null || i2.b() == null || !businessObject.getBusinessObjId().equalsIgnoreCase(i2.h())) {
                TypedValue typedValue = new TypedValue();
                this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
                access$200.setTextColor(typedValue.data);
            } else {
                access$200.setTextColor(this.mContext.getResources().getColor(R.color.gaana_orange_text));
            }
            TypedValue typedValue2 = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.second_line_color, typedValue2, true);
            access$300.setTextColor(typedValue2.data);
        } else {
            access$200.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
            access$300.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
        }
        downloadSongListingHolder.optionImageView.setTag(businessObject);
        downloadSongListingHolder.optionImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DownloadSongListingView.this.showOptionMenu(view);
            }
        });
        ImageView access$500 = downloadSongListingHolder.optionImageView;
        if ((isFavouriteContainsFragment() || (this.mFragment instanceof FavoritesFragment) || (this.mFragment instanceof DownloadDetailsFragment) || (this.mFragment instanceof LocalMediaFragment)) && al.a().d()) {
            access$500.setVisibility(4);
            return initTrackSelectionMode(businessObject, this.mView);
        }
        if (this.mFragment instanceof DownloadDetailsFragment) {
            if (i.a().f()) {
                access$500.setVisibility(4);
                return initEditMode(businessObject, this.mView);
            } else if (z) {
                String g = o.a().g();
                if (g != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(((OfflineTrack) businessObject).getBusinessObjId());
                    stringBuilder.append(",");
                    if (g.contains(stringBuilder.toString())) {
                        downloadSongListingHolder.itemView.setBackgroundColor(getResources().getColor(R.color.gaana_bg_grey));
                    }
                }
                downloadSongListingHolder.itemView.setBackgroundColor(getResources().getColor(R.color.transparent_color));
            }
        }
        access$500.setVisibility(0);
        downloadSongListingHolder.checkBox.setVisibility(8);
        DownloadStatus e2 = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
        TypedArray obtainStyledAttributes2;
        Drawable drawable2;
        if (businessObject.isLocalMedia()) {
            this.downloadImage.setVisibility(0);
            new int[1][0] = R.attr.offline_icon;
            obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(64, -1));
            obtainStyledAttributes2.recycle();
            this.downloadImage.setImageDrawable(drawable2);
            this.downloadImage.setClickable(false);
        } else {
            downloadSongListingHolder.downloadImage.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    DownloadSongListingView.this.downloadTrack(String.valueOf(a), businessObject);
                }
            });
            if (e2 == null) {
                this.downloadImage.setVisibility(0);
                new int[1][0] = R.attr.download_button_paused;
                obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(13, -1));
                obtainStyledAttributes2.recycle();
                this.downloadImage.setImageDrawable(drawable2);
            } else if (e2 == DownloadStatus.DOWNLOADING) {
                if (DownloadManager.c().w()) {
                    this.downloadImage.setVisibility(4);
                } else {
                    this.downloadImage.setVisibility(0);
                    this.downloadImage.setImageResource(R.drawable.vector_download_queued);
                }
            } else if (e2 == DownloadStatus.DOWNLOADED) {
                this.downloadImage.setVisibility(0);
                if (ap.a().j()) {
                    if (z && ((OfflineTrack) businessObject).getSmartDownload() == 1) {
                        this.downloadImage.setImageResource(R.drawable.smart_download_icon);
                    } else if ((businessObject instanceof Track) && ((Track) businessObject).getSmartDownload() == 1) {
                        this.downloadImage.setImageResource(R.drawable.smart_download_icon);
                    } else {
                        this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                    }
                } else if (ap.a().m()) {
                    if (DownloadManager.c().j(businessObject.getBusinessObjId()).booleanValue()) {
                        this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                    } else {
                        new int[1][0] = R.attr.download_button_disabled;
                        obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(12, -1));
                        obtainStyledAttributes2.recycle();
                        this.downloadImage.setImageDrawable(drawable2);
                    }
                } else if (!ap.a().k() || Util.a(businessObject)) {
                    this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                } else {
                    obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable2 = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes2.getResourceId(90, -1));
                    obtainStyledAttributes2.recycle();
                    this.downloadImage.setImageDrawable(drawable2);
                }
            } else if (e2 == DownloadStatus.QUEUED) {
                if (z && ((OfflineTrack) businessObject).getSmartDownload() == 1) {
                    this.downloadImage.setImageResource(R.drawable.smart_download_icon);
                } else if ((businessObject instanceof Track) && ((Track) businessObject).getSmartDownload() == 1) {
                    this.downloadImage.setImageResource(R.drawable.smart_download_icon);
                } else {
                    this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                }
                this.downloadImage.setVisibility(0);
                this.downloadImage.setImageResource(R.drawable.vector_download_queued);
            } else if (e2 == DownloadStatus.TRIED_BUT_FAILED) {
                this.downloadImage.setVisibility(0);
                obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(91, -1));
                obtainStyledAttributes2.recycle();
                this.downloadImage.setImageDrawable(drawable2);
            } else {
                this.downloadImage.setVisibility(0);
                new int[1][0] = R.attr.download_button_paused;
                obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(13, -1));
                obtainStyledAttributes2.recycle();
                this.downloadImage.setImageDrawable(drawable2);
            }
        }
        setProgressBarVisibility((RateTextCircularProgressBar) downloadSongListingHolder.itemView.findViewById(R.id.rate_progress_bar), e2);
        View findViewById = this.mView.findViewById(R.id.view_item_overlay_disable);
        if (findViewById != null) {
            if (Constants.ab && this.mAppState.getPlayoutSectionName().equals(PLAYOUT_SECTION_TYPE.MYMUSIC_SONGS.name())) {
                findViewById.setVisibility(0);
                findViewById.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (Constants.g > 0) {
                            if (DownloadSongListingView.this.mFragment != null) {
                                if (DownloadSongListingView.this.mFragment instanceof AlbumDetailsMaterialListing) {
                                    u.a().a("Shuffle Product", "Gaana+ popup", "Album Detail");
                                } else if (DownloadSongListingView.this.mFragment.getParentFragment() != null && (DownloadSongListingView.this.mFragment.getParentFragment() instanceof ArtistDetailsMaterialListing)) {
                                    u.a().a("Shuffle Product", "Gaana+ popup", "Artist");
                                } else if (DownloadSongListingView.this.mFragment instanceof GaanaSpecialDetailsMaterialListing) {
                                    u.a().a("Shuffle Product", "Gaana+ popup", "Gaana Special");
                                } else if (DownloadSongListingView.this.mFragment instanceof SongParallexListingFragment) {
                                    u.a().a("Shuffle Product", "Gaana+ popup", "Songs Detail");
                                } else if (DownloadSongListingView.this.mFragment instanceof MyMusicItemFragment) {
                                    u.a().a("Shuffle Product", "Gaana+ popup", "MyMusic Songs");
                                }
                            }
                            Constants.g--;
                            Util.a(DownloadSongListingView.this.mContext, BLOCK_ACTION.SHUFFLE);
                        } else if (DownloadSongListingView.this.mFragment == null) {
                        } else {
                            if (DownloadSongListingView.this.mFragment instanceof AlbumDetailsMaterialListing) {
                                ((AlbumDetailsMaterialListing) DownloadSongListingView.this.mFragment).j();
                            } else if (DownloadSongListingView.this.mFragment.getParentFragment() != null && (DownloadSongListingView.this.mFragment.getParentFragment() instanceof ArtistDetailsMaterialListing)) {
                                ((ArtistDetailsMaterialListing) DownloadSongListingView.this.mFragment.getParentFragment()).a();
                            } else if (DownloadSongListingView.this.mFragment instanceof GaanaSpecialDetailsMaterialListing) {
                                ((GaanaSpecialDetailsMaterialListing) DownloadSongListingView.this.mFragment).a();
                            } else if (DownloadSongListingView.this.mFragment instanceof SongParallexListingFragment) {
                                ((SongParallexListingFragment) DownloadSongListingView.this.mFragment).c();
                            } else if (DownloadSongListingView.this.mFragment instanceof MyMusicItemFragment) {
                                ((MyMusicItemFragment) DownloadSongListingView.this.mFragment).e();
                            }
                        }
                    }
                });
            } else {
                findViewById.setVisibility(8);
            }
        }
        return this.mView;
    }

    public void setSearchQuery(String str) {
        this.mSearchQuery = str;
    }

    public View getView(ViewGroup viewGroup) {
        return super.createNewBaseView(this.mLayoutId, null, viewGroup);
    }

    private boolean isFavouriteContainsFragment() {
        BaseGaanaFragment baseGaanaFragment = this.mFragment;
        return (baseGaanaFragment instanceof MyMusicItemFragment) || (baseGaanaFragment instanceof ListingFragment);
    }

    private String getSubtitleText(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" - ");
            stringBuilder.append(str2);
            return stringBuilder.toString();
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return "";
        } else {
            return TextUtils.isEmpty(str) ? str2 : str;
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
            textView.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(i), null, null, null);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    private View getDataFilledView(View view, final BusinessObject businessObject) {
        view.findViewById(R.id.f38download.item.img.thumb).setVisibility(8);
        TextView textView = (TextView) view.findViewById(R.id.f43download.item.tv.trackname);
        TextView textView2 = (TextView) view.findViewById(R.id.f40download.item.tv.genere);
        textView2.setVisibility(0);
        textView.setText(businessObject.getName());
        if (businessObject instanceof OfflineTrack) {
            OfflineTrack offlineTrack = (OfflineTrack) businessObject;
            setSubtitleText(textView2, offlineTrack.getAlbumName(), offlineTrack.getArtistName(), offlineTrack.isParentalWarningEnabled());
        } else if (businessObject instanceof Track) {
            Track track = (Track) businessObject;
            setSubtitleText(textView2, track.getAlbumTitle(), track.getArtistNames(), track.isParentalWarningEnabled());
        }
        final int a = Util.a(businessObject.getBusinessObjId());
        this.downloadImage = (ImageView) view.findViewById(R.id.f37download.item.img.download);
        if (businessObject.isLocalMedia() || !this.mAppState.isAppInOfflineMode() || DownloadManager.c().l(a).booleanValue()) {
            PlayerTrack i = PlayerManager.a(this.mContext).i();
            if (i == null || i.b() == null || !businessObject.getBusinessObjId().equalsIgnoreCase(i.h())) {
                TypedValue typedValue = new TypedValue();
                this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
                textView.setTextColor(typedValue.data);
            } else {
                textView.setTextColor(this.mContext.getResources().getColor(R.color.gaana_orange_text));
            }
            TypedValue typedValue2 = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.second_line_color, typedValue2, true);
            textView2.setTextColor(typedValue2.data);
        } else {
            textView.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
            textView2.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
        }
        view.findViewById(R.id.clickoptionImage).setTag(businessObject);
        view.findViewById(R.id.clickoptionImage).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DownloadSongListingView.this.showOptionMenu(view);
            }
        });
        ImageView imageView = (ImageView) view.findViewById(R.id.clickoptionImage);
        if ((isFavouriteContainsFragment() || (this.mFragment instanceof FavoritesFragment) || (this.mFragment instanceof DownloadDetailsFragment) || (this.mFragment instanceof LocalMediaFragment)) && al.a().d()) {
            imageView.setVisibility(4);
            return initTrackSelectionMode(businessObject, view);
        } else if ((this.mFragment instanceof DownloadDetailsFragment) && i.a().f()) {
            imageView.setVisibility(4);
            return initEditMode(businessObject, view);
        } else {
            imageView.setVisibility(0);
            view.findViewById(R.id.f35download.item.checkbox).setVisibility(8);
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
            TypedArray obtainStyledAttributes;
            Drawable drawable;
            if (businessObject.isLocalMedia()) {
                this.downloadImage.setVisibility(0);
                new int[1][0] = R.attr.offline_icon;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(64, -1));
                obtainStyledAttributes.recycle();
                this.downloadImage.setImageDrawable(drawable);
                this.downloadImage.setClickable(false);
            } else {
                view.findViewById(R.id.f37download.item.img.download).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        DownloadSongListingView.this.downloadTrack(String.valueOf(a), businessObject);
                    }
                });
                if (e == null) {
                    this.downloadImage.setVisibility(0);
                    new int[1][0] = R.attr.download_button_paused;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
                    obtainStyledAttributes.recycle();
                    this.downloadImage.setImageDrawable(drawable);
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
                    this.downloadImage.setImageResource(R.drawable.vector_download_completed);
                } else if (e == DownloadStatus.TRIED_BUT_FAILED) {
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(91, -1));
                    obtainStyledAttributes.recycle();
                    this.downloadImage.setImageDrawable(drawable);
                } else {
                    this.downloadImage.setVisibility(0);
                    new int[1][0] = R.attr.download_button_paused;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
                    obtainStyledAttributes.recycle();
                    this.downloadImage.setImageDrawable(drawable);
                }
            }
            setProgressBarVisibility((RateTextCircularProgressBar) view.findViewById(R.id.rate_progress_bar), e);
            View findViewById = view.findViewById(R.id.view_item_overlay_disable);
            if (findViewById != null) {
                if (Constants.ab && this.mAppState.getPlayoutSectionName().equals(PLAYOUT_SECTION_TYPE.MYMUSIC_SONGS.name())) {
                    findViewById.setVisibility(0);
                    findViewById.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            if (Constants.g > 0) {
                                if (DownloadSongListingView.this.mFragment != null) {
                                    if (DownloadSongListingView.this.mFragment instanceof AlbumDetailsMaterialListing) {
                                        u.a().a("Shuffle Product", "Gaana+ popup", "Album Detail");
                                    } else if (DownloadSongListingView.this.mFragment.getParentFragment() != null && (DownloadSongListingView.this.mFragment.getParentFragment() instanceof ArtistDetailsMaterialListing)) {
                                        u.a().a("Shuffle Product", "Gaana+ popup", "Artist");
                                    } else if (DownloadSongListingView.this.mFragment instanceof GaanaSpecialDetailsMaterialListing) {
                                        u.a().a("Shuffle Product", "Gaana+ popup", "Gaana Special");
                                    } else if (DownloadSongListingView.this.mFragment instanceof SongParallexListingFragment) {
                                        u.a().a("Shuffle Product", "Gaana+ popup", "Songs Detail");
                                    } else if (DownloadSongListingView.this.mFragment instanceof MyMusicItemFragment) {
                                        u.a().a("Shuffle Product", "Gaana+ popup", "MyMusic Songs");
                                    }
                                }
                                Constants.g--;
                                Util.a(DownloadSongListingView.this.mContext, BLOCK_ACTION.SHUFFLE);
                            } else if (DownloadSongListingView.this.mFragment == null) {
                            } else {
                                if (DownloadSongListingView.this.mFragment instanceof AlbumDetailsMaterialListing) {
                                    ((AlbumDetailsMaterialListing) DownloadSongListingView.this.mFragment).j();
                                } else if (DownloadSongListingView.this.mFragment.getParentFragment() != null && (DownloadSongListingView.this.mFragment.getParentFragment() instanceof ArtistDetailsMaterialListing)) {
                                    ((ArtistDetailsMaterialListing) DownloadSongListingView.this.mFragment.getParentFragment()).a();
                                } else if (DownloadSongListingView.this.mFragment instanceof GaanaSpecialDetailsMaterialListing) {
                                    ((GaanaSpecialDetailsMaterialListing) DownloadSongListingView.this.mFragment).a();
                                } else if (DownloadSongListingView.this.mFragment instanceof SongParallexListingFragment) {
                                    ((SongParallexListingFragment) DownloadSongListingView.this.mFragment).c();
                                } else if (DownloadSongListingView.this.mFragment instanceof MyMusicItemFragment) {
                                    ((MyMusicItemFragment) DownloadSongListingView.this.mFragment).e();
                                }
                            }
                        }
                    });
                } else {
                    findViewById.setVisibility(8);
                }
            }
            return view;
        }
    }

    private void setProgressBarVisibility(RateTextCircularProgressBar rateTextCircularProgressBar, DownloadStatus downloadStatus) {
        if (rateTextCircularProgressBar == null) {
            return;
        }
        if (downloadStatus == DownloadStatus.DOWNLOADING && downloadStatus != DownloadStatus.DOWNLOADED) {
            this.rateTextCircularProgressBar = rateTextCircularProgressBar;
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

    private void setUpdateTrackSelectionCount() {
        if (this.mFragment instanceof FavoritesFragment) {
            ((FavoritesFragment) this.mFragment).f();
        } else if (this.mFragment instanceof DownloadDetailsFragment) {
            ((DownloadDetailsFragment) this.mFragment).h();
        } else if (this.mFragment instanceof LocalMediaFragment) {
            ((LocalMediaFragment) this.mFragment).d();
        } else if (this.mFragment instanceof MyMusicItemFragment) {
            ((MyMusicItemFragment) this.mFragment).g();
        } else if ((this.mFragment instanceof ListingFragment) && !this.isPlayerQueue) {
            ((ListingFragment) this.mFragment).k();
        }
    }

    public void onClick(View view) {
        Util.a(this.mContext, view);
        BusinessObject businessObject = (BusinessObject) view.getTag();
        int i = 0;
        CheckBox checkBox;
        if (al.a().d()) {
            checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
            if (al.a().f() > 100) {
                aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.selection_exceed_message_100_songs));
                return;
            }
            if (al.a().c(businessObject, true)) {
                al.a().b(businessObject, true);
                checkBox.setChecked(false);
            } else {
                al.a().a(businessObject, true);
                checkBox.setChecked(true);
            }
            setUpdateTrackSelectionCount();
        } else if ((this.mFragment instanceof DownloadDetailsFragment) && i.a().f()) {
            checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
            if (i.a().c(businessObject.getBusinessObjId(), true)) {
                i.a().b(businessObject.getBusinessObjId(), true);
                checkBox.setChecked(false);
            } else {
                i.a().a(businessObject.getBusinessObjId(), true);
                checkBox.setChecked(true);
            }
        } else {
            Track trackFromLocalMedia;
            if (businessObject instanceof OfflineTrack) {
                i = this.mAppState.getCurrentBusObjInListView().indexOf(businessObject);
                trackFromLocalMedia = businessObject.isLocalMedia() ? LocalMediaManager.getInstance(this.mContext).getTrackFromLocalMedia((OfflineTrack) businessObject) : (Track) DownloadManager.c().a(businessObject.getBusinessObjId(), true);
            } else if (businessObject instanceof Track) {
                Track track = (Track) businessObject;
                i = this.mAppState.getCurrentBusObjInListView().indexOf(businessObject);
                trackFromLocalMedia = track;
            } else {
                trackFromLocalMedia = null;
            }
            if (trackFromLocalMedia != null) {
                if (trackFromLocalMedia.isLocalMedia()) {
                    setPlayerQueueAndPlay(view, trackFromLocalMedia, i, null);
                } else {
                    checkOfflineAndplayTrack(view, trackFromLocalMedia, i, null);
                }
            }
        }
    }

    private View initTrackSelectionMode(final BusinessObject businessObject, View view) {
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
        this.downloadImage.setVisibility(8);
        this.mView.findViewById(R.id.rate_progress_bar).setVisibility(8);
        checkBox.setVisibility(0);
        ((TextView) this.mView.findViewById(R.id.f43download.item.tv.trackname)).setTextAppearance(this.mContext, R.style.list_download_item_first_line);
        if (al.a().c(businessObject, true)) {
            checkBox.setChecked(true);
        } else if (al.a().e()) {
            checkBox.setChecked(true);
            al.a().a(businessObject, true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (al.a().c(businessObject, true)) {
                    al.a().b(businessObject, true);
                    checkBox.setChecked(false);
                } else if (al.a().f() > 100) {
                    aj.a().a(DownloadSongListingView.this.mContext, DownloadSongListingView.this.mContext.getResources().getString(R.string.selection_exceed_message_100_songs));
                    checkBox.setChecked(false);
                    return;
                } else {
                    al.a().a(businessObject, true);
                    checkBox.setChecked(true);
                }
                DownloadSongListingView.this.setUpdateTrackSelectionCount();
            }
        });
        return view;
    }

    private View initEditMode(final BusinessObject businessObject, View view) {
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
        this.downloadImage.setVisibility(8);
        checkBox.setVisibility(0);
        if (i.a().c(businessObject.getBusinessObjId(), true)) {
            checkBox.setChecked(true);
        } else if (i.a().g()) {
            checkBox.setChecked(true);
            i.a().a(businessObject.getBusinessObjId(), true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (i.a().c(businessObject.getBusinessObjId(), true)) {
                    i.a().b(businessObject.getBusinessObjId(), true);
                    checkBox.setChecked(false);
                    return;
                }
                i.a().a(businessObject.getBusinessObjId(), true);
                checkBox.setChecked(true);
            }
        });
        return view;
    }

    public void updateUiForCircularProgressBar(final int i, final int i2) {
        if (this.mContext instanceof Activity) {
            ((Activity) this.mContext).runOnUiThread(new Runnable() {
                public void run() {
                    if (DownloadSongListingView.this.rateTextCircularProgressBar != null) {
                        DownloadSongListingView.this.rateTextCircularProgressBar.setProgress(DownloadSongListingView.this.calculatePercentage(i, i2));
                    }
                }
            });
        }
    }

    private int calculatePercentage(int i, int i2) {
        return (i <= 0 || i2 <= 0) ? 0 : (i2 * 100) / i;
    }

    private void downloadTrack(String str, BusinessObject businessObject) {
        final BusinessObject businessObject2 = businessObject;
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else if (Util.j(this.mContext)) {
            BusinessObject businessObject3;
            boolean z = businessObject2 instanceof OfflineTrack;
            if (z) {
                businessObject3 = (Track) DownloadManager.c().a(businessObject.getBusinessObjId(), true);
            } else {
                businessObject3 = (Track) businessObject2;
            }
            int i = -1;
            if (this.mAppState.getCurrentBusObjInListView() != null) {
                ArrayList arrayList = new ArrayList();
                ArrayList currentBusObjInListView = this.mAppState.getCurrentBusObjInListView();
                if (currentBusObjInListView != null && currentBusObjInListView.size() > 0) {
                    arrayList.addAll(currentBusObjInListView);
                }
                i = arrayList.indexOf(businessObject3);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
            stringBuilder.append(" - ");
            stringBuilder.append(((BaseActivity) this.mContext).currentFavpage);
            stringBuilder.append(" - Download");
            ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Download", stringBuilder.toString());
            MoEngage.getInstance().reportDownload(businessObject2);
            an.a().a("click", "ac", businessObject3.getAlbumId(), "", businessObject3.getBusinessObjId(), "download", String.valueOf(i), "");
            if (e == DownloadStatus.DOWNLOADED) {
                if (!ap.a().k() || Util.a(businessObject3)) {
                    new CustomDialogView(this.mContext, this.mContext.getResources().getString(R.string.toast_delete_downloaded_song), new OnButtonClickListener() {
                        public void onNegativeButtonClick() {
                        }

                        public void onPositiveButtonClick() {
                            DownloadSongListingView.this.deleteDownload(businessObject2);
                        }
                    }).show();
                } else {
                    Util.a(this.mContext, null);
                    u.a().a("Expired Download", "Click", "Track");
                }
            } else if (e == DownloadStatus.QUEUED) {
                new CustomDialogView(this.mContext, this.mContext.getResources().getString(R.string.toast_remove_queue_song), new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        DownloadManager.c().d(businessObject2.getBusinessObjId());
                        DownloadSongListingView.this.updateOfflineTracksStatus();
                        DownloadSongListingView.this.updateDownloadImage(DownloadSongListingView.this.downloadImage, DownloadManager.c().e(Integer.parseInt(businessObject2.getBusinessObjId())));
                    }
                }).show();
            } else if (e == DownloadStatus.DOWNLOADING) {
                new CustomDialogView(this.mContext, this.mContext.getResources().getString(R.string.toast_stop_download), new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        DownloadManager.c().d(businessObject2.getBusinessObjId());
                        DownloadSongListingView.this.updateOfflineTracksStatus();
                        DownloadSongListingView.this.updateDownloadImage(DownloadSongListingView.this.downloadImage, DownloadManager.c().e(Integer.parseInt(businessObject2.getBusinessObjId())));
                    }
                }).show();
            } else if (z) {
                DownloadManager c = DownloadManager.c();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("");
                stringBuilder2.append(str);
                startDownload(c.a(stringBuilder2.toString(), true));
            } else {
                startDownload(businessObject2);
            }
        } else {
            ap.a().f(this.mContext);
        }
    }

    public void onPopupClicked(String str, BusinessObject businessObject) {
        downloadTrack(str, businessObject);
    }
}
