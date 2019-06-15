package com.gaana.view.item;

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
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.FavoritesFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.DownloadSongsItemView.AlbumDetailItemHolder;
import com.library.controls.CrossFadeImageView;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.an;
import com.managers.ap;
import com.managers.i;
import com.managers.u;
import com.utilities.Util;

public class DownloadAlbumItemView extends AlbumItemView {
    private ImageView downloadImage;
    private CrossFadeImageView mCrossFadeImageIcon;
    BaseGaanaFragment mGaanaFragment;
    private String mSearchQuery;
    private ProgressBar progressBar;
    private TextView tvDownloadProgress;
    private TextView tvSubtitle;
    private TextView tvTitle;

    public static class DownloadAlbumItemHolder extends ViewHolder {
        public CheckBox checkBox;
        public ImageView downloadImage;
        public ImageView imgMoreOptions;
        public CrossFadeImageView mCrossFadeImageIcon;
        public ProgressBar progressBar;
        public TextView tvDownloadProgress;
        public TextView tvSubtitle;
        public TextView tvTitle;

        public DownloadAlbumItemHolder(View view) {
            super(view);
            this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.f38download.item.img.thumb);
            this.tvTitle = (TextView) view.findViewById(R.id.f43download.item.tv.trackname);
            this.tvSubtitle = (TextView) view.findViewById(R.id.f40download.item.tv.genere);
            this.progressBar = (ProgressBar) view.findViewById(R.id.f34download.item.ProgressBar);
            this.tvDownloadProgress = (TextView) view.findViewById(R.id.f42download.item.tv.progress);
            this.checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
            this.downloadImage = (ImageView) view.findViewById(R.id.f37download.item.img.download);
            this.imgMoreOptions = (ImageView) view.findViewById(R.id.clickoptionImage);
        }
    }

    public DownloadAlbumItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mLayoutId = R.layout.view_item_download;
        this.mGaanaFragment = baseGaanaFragment;
        ((BaseActivity) this.mContext).currentItem = "Album";
    }

    public View getView(ViewGroup viewGroup) {
        return super.createNewBaseView(this.mLayoutId, null, viewGroup);
    }

    public void setSearchQuery(String str) {
        this.mSearchQuery = str;
    }

    public View getPoplatedViewforHome(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        AlbumDetailItemHolder albumDetailItemHolder = (AlbumDetailItemHolder) viewHolder;
        this.mView = albumDetailItemHolder.itemView;
        super.getPoplatedView(this.mView, businessObject);
        final BusinessObject businessObject2 = (Album) businessObject;
        this.mView.setTag(businessObject2);
        this.mCrossFadeImageIcon = albumDetailItemHolder.mCrossFadeImageIcon;
        if (TextUtils.isEmpty(businessObject2.getArtwork())) {
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            this.mCrossFadeImageIcon.setImageDrawable(drawable);
        } else if (businessObject2.isLocalMedia()) {
            this.mCrossFadeImageIcon.bindImageForLocalMedia(businessObject2.getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
        } else {
            this.mCrossFadeImageIcon.bindImage(businessObject2.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        }
        this.tvTitle = albumDetailItemHolder.tvTitle;
        this.tvTitle.setText(Util.c(this.mSearchQuery, businessObject2.getName()));
        this.tvSubtitle = albumDetailItemHolder.tvSubtitle;
        this.tvSubtitle.setVisibility(0);
        this.tvSubtitle.setText(Util.c(this.mSearchQuery, businessObject2.getArtistNames()));
        this.progressBar = albumDetailItemHolder.progressBar;
        this.downloadImage = albumDetailItemHolder.downloadImage;
        ImageView imageView = albumDetailItemHolder.clickoptionImage;
        imageView.setTag(businessObject);
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DownloadAlbumItemView.this.showOptionMenu(view);
            }
        });
        final int a = Util.a(businessObject2.getBusinessObjId());
        if ((this.mFragment instanceof DownloadDetailsFragment) && i.a().f()) {
            this.downloadImage.setVisibility(8);
            this.progressBar.setVisibility(8);
            imageView.setVisibility(4);
            return this.mView;
        }
        if (businessObject.isLocalMedia()) {
            this.downloadImage.setVisibility(0);
            new int[1][0] = R.attr.offline_icon;
            TypedArray obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            this.downloadImage.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(64, -1)));
            obtainStyledAttributes2.recycle();
            this.downloadImage.setClickable(false);
            this.progressBar.setVisibility(8);
        } else if (ap.a().a((Album) businessObject2)) {
            imageView = albumDetailItemHolder.downloadImage;
            albumDetailItemHolder.downloadImage.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (DownloadAlbumItemView.this.mAppState.isAppInOfflineMode()) {
                        ((BaseActivity) DownloadAlbumItemView.this.mContext).displayFeatureNotAvailableOfflineDialog(DownloadAlbumItemView.this.mContext.getString(R.string.this_feature));
                    } else if (Util.j(DownloadAlbumItemView.this.mContext)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(((BaseActivity) DownloadAlbumItemView.this.mContext).currentScreen);
                        stringBuilder.append(" - ");
                        stringBuilder.append(((BaseActivity) DownloadAlbumItemView.this.mContext).currentFavpage);
                        stringBuilder.append(" - Download");
                        ((BaseActivity) DownloadAlbumItemView.this.mContext).sendGAEvent(((BaseActivity) DownloadAlbumItemView.this.mContext).currentScreen, "Download", stringBuilder.toString());
                        MoEngage.getInstance().reportDownload(businessObject2);
                        DownloadStatus h = DownloadManager.c().h(a);
                        if (h == null || h == DownloadStatus.PAUSED || h == DownloadStatus.PARTIALLY_DOWNLOADED || h == DownloadStatus.TRIED_BUT_FAILED) {
                            ((BaseActivity) DownloadAlbumItemView.this.mContext).showProgressDialog(Boolean.valueOf(true), DownloadAlbumItemView.this.mContext.getString(R.string.loading));
                            DownloadAlbumItemView.this.startDownload(businessObject2);
                        } else if (h == DownloadStatus.DOWNLOADED) {
                            if (ap.a().k()) {
                                Util.a(DownloadAlbumItemView.this.mContext, null);
                                u.a().a("Expired Download", "Click", "Album");
                            } else {
                                new CustomDialogView(DownloadAlbumItemView.this.mContext, DownloadAlbumItemView.this.mContext.getResources().getString(R.string.toast_delete_downloaded_album), new OnButtonClickListener() {
                                    public void onNegativeButtonClick() {
                                    }

                                    public void onPositiveButtonClick() {
                                        DownloadAlbumItemView.this.deleteDownload(businessObject2.getBusinessObjId());
                                        DownloadAlbumItemView.this.updateDownloadImage(imageView, DownloadManager.c().h(a));
                                    }
                                }).show();
                            }
                        } else if (h == DownloadStatus.QUEUED) {
                            new CustomDialogView(DownloadAlbumItemView.this.mContext, DownloadAlbumItemView.this.mContext.getResources().getString(R.string.toast_remove_queue_album), new OnButtonClickListener() {
                                public void onNegativeButtonClick() {
                                }

                                public void onPositiveButtonClick() {
                                    DownloadManager.c().r(a);
                                    DownloadAlbumItemView.this.updateDownloadImage(imageView, DownloadManager.c().h(a));
                                    DownloadAlbumItemView.this.updateOfflineTracksStatus();
                                }
                            }).show();
                        } else if (h == DownloadStatus.DOWNLOADING) {
                            new CustomDialogView(DownloadAlbumItemView.this.mContext, DownloadAlbumItemView.this.mContext.getResources().getString(R.string.toast_stop_download), new OnButtonClickListener() {
                                public void onNegativeButtonClick() {
                                }

                                public void onPositiveButtonClick() {
                                    DownloadAlbumItemView.this.progressBar.setVisibility(8);
                                    imageView.setVisibility(0);
                                    DownloadManager.c().r(a);
                                    DownloadAlbumItemView.this.updateDownloadImage(imageView, DownloadManager.c().h(a));
                                    DownloadAlbumItemView.this.updateOfflineTracksStatus();
                                }
                            }).show();
                        }
                    } else {
                        ap.a().f(DownloadAlbumItemView.this.mContext);
                    }
                }
            });
            if (a != 0) {
                setUpdateDownloadStatus(businessObject2);
            }
        } else {
            albumDetailItemHolder.downloadImage.setClickable(false);
        }
        if (businessObject.isLocalMedia() || (ap.a().a((Album) businessObject2) && (!this.mAppState.isAppInOfflineMode() || DownloadManager.c().b(businessObject2).booleanValue()))) {
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
            this.tvTitle.setTextColor(typedValue.data);
        } else {
            this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
        }
        return this.mView;
    }

    public View getPoplatedView(ViewHolder viewHolder, final BusinessObject businessObject, ViewGroup viewGroup) {
        DownloadAlbumItemHolder downloadAlbumItemHolder = (DownloadAlbumItemHolder) viewHolder;
        this.mView = downloadAlbumItemHolder.itemView;
        super.getPoplatedView(this.mView, businessObject);
        final BusinessObject businessObject2 = (Album) businessObject;
        this.mView.setTag(businessObject2);
        this.mCrossFadeImageIcon = downloadAlbumItemHolder.mCrossFadeImageIcon;
        if (TextUtils.isEmpty(businessObject2.getArtwork())) {
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            this.mCrossFadeImageIcon.setImageDrawable(drawable);
        } else if (businessObject2.isLocalMedia()) {
            this.mCrossFadeImageIcon.bindImageForLocalMedia(businessObject2.getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
        } else {
            this.mCrossFadeImageIcon.bindImage(businessObject2.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        }
        this.tvTitle = downloadAlbumItemHolder.tvTitle;
        this.tvTitle.setText(Util.c(this.mSearchQuery, businessObject2.getName()));
        this.tvSubtitle = downloadAlbumItemHolder.tvSubtitle;
        this.tvSubtitle.setVisibility(0);
        this.tvSubtitle.setText(Util.c(this.mSearchQuery, businessObject2.getArtistNames()));
        if (businessObject2.isParentalWarningEnabled()) {
            this.tvSubtitle.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(Util.Y()), null, null, null);
        } else {
            this.tvSubtitle.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        this.progressBar = downloadAlbumItemHolder.progressBar;
        this.tvDownloadProgress = downloadAlbumItemHolder.tvDownloadProgress;
        final CheckBox checkBox = downloadAlbumItemHolder.checkBox;
        this.downloadImage = downloadAlbumItemHolder.downloadImage;
        ImageView imageView = downloadAlbumItemHolder.imgMoreOptions;
        imageView.setTag(businessObject);
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DownloadAlbumItemView.this.showOptionMenu(view);
            }
        });
        final int a = Util.a(businessObject2.getBusinessObjId());
        checkBox.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (i.a().c(businessObject2.getBusinessObjId(), false)) {
                    i.a().b(businessObject2.getBusinessObjId(), false);
                    checkBox.setChecked(false);
                    return;
                }
                i.a().a(businessObject2.getBusinessObjId(), false);
                checkBox.setChecked(true);
            }
        });
        if ((this.mFragment instanceof DownloadDetailsFragment) && i.a().f()) {
            this.downloadImage.setVisibility(8);
            this.progressBar.setVisibility(8);
            checkBox.setVisibility(0);
            imageView.setVisibility(4);
            if (i.a().c(businessObject2.getBusinessObjId(), false)) {
                checkBox.setChecked(true);
            } else if (i.a().g()) {
                checkBox.setChecked(true);
                i.a().a(businessObject2.getBusinessObjId(), false);
            } else {
                checkBox.setChecked(false);
            }
            return this.mView;
        }
        checkBox.setVisibility(8);
        if (businessObject.isLocalMedia()) {
            this.downloadImage.setVisibility(0);
            new int[1][0] = R.attr.offline_icon;
            TypedArray obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            this.downloadImage.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(64, -1)));
            obtainStyledAttributes2.recycle();
            this.downloadImage.setClickable(false);
            this.progressBar.setVisibility(8);
        } else if (ap.a().a((Album) businessObject2)) {
            downloadAlbumItemHolder.downloadImage.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    an.a().a("click", "ac", businessObject.getBusinessObjId(), "", businessObject2.getBusinessObjType().name(), "download", "", "");
                    if (DownloadAlbumItemView.this.mAppState.isAppInOfflineMode()) {
                        ((BaseActivity) DownloadAlbumItemView.this.mContext).displayFeatureNotAvailableOfflineDialog(DownloadAlbumItemView.this.mContext.getString(R.string.this_feature));
                    } else if (Util.j(DownloadAlbumItemView.this.mContext)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(((BaseActivity) DownloadAlbumItemView.this.mContext).currentScreen);
                        stringBuilder.append(" - ");
                        stringBuilder.append(((BaseActivity) DownloadAlbumItemView.this.mContext).currentFavpage);
                        stringBuilder.append(" - Download");
                        ((BaseActivity) DownloadAlbumItemView.this.mContext).sendGAEvent(((BaseActivity) DownloadAlbumItemView.this.mContext).currentScreen, "Download", stringBuilder.toString());
                        MoEngage.getInstance().reportDownload(businessObject2);
                        DownloadStatus h = DownloadManager.c().h(a);
                        if (h == null || h == DownloadStatus.PAUSED || h == DownloadStatus.PARTIALLY_DOWNLOADED || h == DownloadStatus.TRIED_BUT_FAILED) {
                            ((BaseActivity) DownloadAlbumItemView.this.mContext).showProgressDialog(Boolean.valueOf(true), DownloadAlbumItemView.this.mContext.getString(R.string.loading));
                            DownloadAlbumItemView.this.startDownload(businessObject2);
                        } else if (h == DownloadStatus.DOWNLOADED) {
                            if (ap.a().k()) {
                                Util.a(DownloadAlbumItemView.this.mContext, null);
                                u.a().a("Expired Download", "Click", "Album");
                            } else {
                                new CustomDialogView(DownloadAlbumItemView.this.mContext, DownloadAlbumItemView.this.mContext.getResources().getString(R.string.toast_delete_downloaded_album), new OnButtonClickListener() {
                                    public void onNegativeButtonClick() {
                                    }

                                    public void onPositiveButtonClick() {
                                        DownloadAlbumItemView.this.deleteDownload(businessObject2.getBusinessObjId());
                                        DownloadAlbumItemView.this.updateDownloadImage(DownloadAlbumItemView.this.downloadImage, DownloadManager.c().h(a));
                                    }
                                }).show();
                            }
                        } else if (h == DownloadStatus.QUEUED) {
                            new CustomDialogView(DownloadAlbumItemView.this.mContext, DownloadAlbumItemView.this.mContext.getResources().getString(R.string.toast_remove_queue_album), new OnButtonClickListener() {
                                public void onNegativeButtonClick() {
                                }

                                public void onPositiveButtonClick() {
                                    DownloadManager.c().r(a);
                                    DownloadAlbumItemView.this.updateDownloadImage(DownloadAlbumItemView.this.downloadImage, DownloadManager.c().h(a));
                                    DownloadAlbumItemView.this.updateOfflineTracksStatus();
                                }
                            }).show();
                        } else if (h == DownloadStatus.DOWNLOADING) {
                            new CustomDialogView(DownloadAlbumItemView.this.mContext, DownloadAlbumItemView.this.mContext.getResources().getString(R.string.toast_stop_download), new OnButtonClickListener() {
                                public void onNegativeButtonClick() {
                                }

                                public void onPositiveButtonClick() {
                                    DownloadAlbumItemView.this.progressBar.setVisibility(8);
                                    DownloadAlbumItemView.this.downloadImage.setVisibility(0);
                                    DownloadManager.c().r(a);
                                    DownloadAlbumItemView.this.updateDownloadImage(DownloadAlbumItemView.this.downloadImage, DownloadManager.c().h(a));
                                    DownloadAlbumItemView.this.updateOfflineTracksStatus();
                                }
                            }).show();
                        }
                    } else {
                        ap.a().f(DownloadAlbumItemView.this.mContext);
                    }
                }
            });
            if (a != 0) {
                setUpdateDownloadStatus(businessObject2);
            }
        } else {
            downloadAlbumItemHolder.downloadImage.setClickable(false);
        }
        if (businessObject.isLocalMedia() || (ap.a().a((Album) businessObject2) && (!this.mAppState.isAppInOfflineMode() || DownloadManager.c().b(businessObject2).booleanValue()))) {
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
            this.tvTitle.setTextColor(typedValue.data);
        } else {
            this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
        }
        return this.mView;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        TypedArray obtainStyledAttributes;
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutId, view, viewGroup);
        }
        super.getPoplatedView(view, businessObject);
        final BusinessObject businessObject2 = (Album) businessObject;
        view.setTag(businessObject2);
        this.mView = view;
        this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.f38download.item.img.thumb);
        if (TextUtils.isEmpty(businessObject2.getArtwork())) {
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            this.mCrossFadeImageIcon.setImageDrawable(drawable);
        } else if (businessObject2.isLocalMedia()) {
            this.mCrossFadeImageIcon.bindImageForLocalMedia(businessObject2.getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
        } else {
            this.mCrossFadeImageIcon.bindImage(businessObject2.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        }
        this.tvTitle = (TextView) view.findViewById(R.id.f43download.item.tv.trackname);
        this.tvTitle.setText(Util.c(this.mSearchQuery, businessObject2.getName()));
        this.tvSubtitle = (TextView) view.findViewById(R.id.f40download.item.tv.genere);
        this.tvSubtitle.setVisibility(0);
        this.tvSubtitle.setText(Util.c(this.mSearchQuery, businessObject2.getArtistNames()));
        this.progressBar = (ProgressBar) view.findViewById(R.id.f34download.item.ProgressBar);
        this.tvDownloadProgress = (TextView) view.findViewById(R.id.f42download.item.tv.progress);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
        this.downloadImage = (ImageView) view.findViewById(R.id.f37download.item.img.download);
        ImageView imageView = (ImageView) view.findViewById(R.id.clickoptionImage);
        imageView.setTag(businessObject);
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DownloadAlbumItemView.this.showOptionMenu(view);
            }
        });
        final int a = Util.a(businessObject2.getBusinessObjId());
        checkBox.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (i.a().c(businessObject2.getBusinessObjId(), false)) {
                    i.a().b(businessObject2.getBusinessObjId(), false);
                    checkBox.setChecked(false);
                    return;
                }
                i.a().a(businessObject2.getBusinessObjId(), false);
                checkBox.setChecked(true);
            }
        });
        if ((this.mFragment instanceof DownloadDetailsFragment) && i.a().f()) {
            this.downloadImage.setVisibility(8);
            this.progressBar.setVisibility(8);
            checkBox.setVisibility(0);
            imageView.setVisibility(4);
            if (i.a().c(businessObject2.getBusinessObjId(), false)) {
                checkBox.setChecked(true);
            } else if (i.a().g()) {
                checkBox.setChecked(true);
                i.a().a(businessObject2.getBusinessObjId(), false);
            } else {
                checkBox.setChecked(false);
            }
            return view;
        }
        checkBox.setVisibility(8);
        if (businessObject.isLocalMedia()) {
            this.downloadImage.setVisibility(0);
            new int[1][0] = R.attr.offline_icon;
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            this.downloadImage.setImageDrawable(ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(64, -1)));
            obtainStyledAttributes.recycle();
            this.downloadImage.setClickable(false);
            this.progressBar.setVisibility(8);
        } else if (ap.a().a((Album) businessObject2)) {
            view.findViewById(R.id.f37download.item.img.download).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (DownloadAlbumItemView.this.mAppState.isAppInOfflineMode()) {
                        ((BaseActivity) DownloadAlbumItemView.this.mContext).displayFeatureNotAvailableOfflineDialog(DownloadAlbumItemView.this.mContext.getString(R.string.this_feature));
                    } else if (Util.j(DownloadAlbumItemView.this.mContext)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(((BaseActivity) DownloadAlbumItemView.this.mContext).currentScreen);
                        stringBuilder.append(" - ");
                        stringBuilder.append(((BaseActivity) DownloadAlbumItemView.this.mContext).currentFavpage);
                        stringBuilder.append(" - Download");
                        ((BaseActivity) DownloadAlbumItemView.this.mContext).sendGAEvent(((BaseActivity) DownloadAlbumItemView.this.mContext).currentScreen, "Download", stringBuilder.toString());
                        MoEngage.getInstance().reportDownload(businessObject2);
                        DownloadStatus h = DownloadManager.c().h(a);
                        if (h == null || h == DownloadStatus.PAUSED || h == DownloadStatus.PARTIALLY_DOWNLOADED || h == DownloadStatus.TRIED_BUT_FAILED) {
                            ((BaseActivity) DownloadAlbumItemView.this.mContext).showProgressDialog(Boolean.valueOf(true), DownloadAlbumItemView.this.mContext.getString(R.string.loading));
                            DownloadAlbumItemView.this.startDownload(businessObject2);
                        } else if (h == DownloadStatus.DOWNLOADED) {
                            if (ap.a().k()) {
                                Util.a(DownloadAlbumItemView.this.mContext, null);
                                u.a().a("Expired Download", "Click", "Album");
                            } else {
                                new CustomDialogView(DownloadAlbumItemView.this.mContext, DownloadAlbumItemView.this.mContext.getResources().getString(R.string.toast_delete_downloaded_album), new OnButtonClickListener() {
                                    public void onNegativeButtonClick() {
                                    }

                                    public void onPositiveButtonClick() {
                                        DownloadAlbumItemView.this.deleteDownload(businessObject2.getBusinessObjId());
                                        DownloadAlbumItemView.this.updateDownloadImage(DownloadAlbumItemView.this.downloadImage, DownloadManager.c().h(a));
                                    }
                                }).show();
                            }
                        } else if (h == DownloadStatus.QUEUED) {
                            new CustomDialogView(DownloadAlbumItemView.this.mContext, DownloadAlbumItemView.this.mContext.getResources().getString(R.string.toast_remove_queue_album), new OnButtonClickListener() {
                                public void onNegativeButtonClick() {
                                }

                                public void onPositiveButtonClick() {
                                    DownloadManager.c().r(a);
                                    DownloadAlbumItemView.this.updateDownloadImage(DownloadAlbumItemView.this.downloadImage, DownloadManager.c().h(a));
                                    DownloadAlbumItemView.this.updateOfflineTracksStatus();
                                }
                            }).show();
                        } else if (h == DownloadStatus.DOWNLOADING) {
                            new CustomDialogView(DownloadAlbumItemView.this.mContext, DownloadAlbumItemView.this.mContext.getResources().getString(R.string.toast_stop_download), new OnButtonClickListener() {
                                public void onNegativeButtonClick() {
                                }

                                public void onPositiveButtonClick() {
                                    DownloadAlbumItemView.this.progressBar.setVisibility(8);
                                    DownloadAlbumItemView.this.downloadImage.setVisibility(0);
                                    DownloadManager.c().r(a);
                                    DownloadAlbumItemView.this.updateDownloadImage(DownloadAlbumItemView.this.downloadImage, DownloadManager.c().h(a));
                                    DownloadAlbumItemView.this.updateOfflineTracksStatus();
                                }
                            }).show();
                        }
                    } else {
                        ap.a().f(DownloadAlbumItemView.this.mContext);
                    }
                }
            });
            if (a != 0) {
                setUpdateDownloadStatus(businessObject2);
            }
        } else {
            view.findViewById(R.id.f37download.item.img.download).setClickable(false);
        }
        if (businessObject.isLocalMedia() || (ap.a().a((Album) businessObject2) && (!this.mAppState.isAppInOfflineMode() || DownloadManager.c().b(businessObject2).booleanValue()))) {
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
            this.tvTitle.setTextColor(typedValue.data);
        } else {
            this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
        }
        return view;
    }

    public void onClick(View view) {
        Util.a(this.mContext, view);
        Album album = (Album) view.getTag();
        this.mBusinessObject = album;
        if (this.mFragment instanceof FavoritesFragment) {
            an.a().a("click", "ac", album.getBusinessObjId(), "", album.getBusinessObjType().name(), "fav", "", "");
        } else if (this.mFragment instanceof DownloadDetailsFragment) {
            an.a().a("click", "ac", album.getBusinessObjId(), "", album.getBusinessObjType().name(), "download", "", "");
        }
        if ((this.mFragment instanceof DownloadDetailsFragment) && i.a().f()) {
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
            if (i.a().c(album.getBusinessObjId(), false)) {
                i.a().b(album.getBusinessObjId(), false);
                checkBox.setChecked(false);
            } else {
                i.a().a(album.getBusinessObjId(), false);
                checkBox.setChecked(true);
            }
            return;
        }
        super.onClick(view);
    }

    private void setUpdateDownloadStatus(Album album) {
        int parseInt = Integer.parseInt(album.getBusinessObjId());
        DownloadStatus h = DownloadManager.c().h(parseInt);
        this.progressBar.setVisibility(8);
        this.downloadImage.setVisibility(0);
        int i;
        TextView textView;
        StringBuilder stringBuilder;
        if (h == DownloadStatus.DOWNLOADING && DownloadManager.c().k() != -1) {
            i = DownloadManager.c().i(parseInt);
            parseInt = DownloadManager.c().j(parseInt);
            this.progressBar.setVisibility(0);
            this.downloadImage.setVisibility(4);
            this.tvDownloadProgress.setVisibility(0);
            if (i < parseInt) {
                textView = this.tvDownloadProgress;
                stringBuilder = new StringBuilder();
                stringBuilder.append(i);
                stringBuilder.append(" of ");
                stringBuilder.append(parseInt);
                stringBuilder.append(" Synced");
                textView.setText(stringBuilder.toString());
            }
        } else if (h == DownloadStatus.PARTIALLY_DOWNLOADED || h == DownloadStatus.DOWNLOADING || h == DownloadStatus.DOWNLOADED) {
            parseInt = DownloadManager.c().i(parseInt);
            if (album.getArrListBusinessObj() != null) {
                i = album.getArrListBusinessObj().size();
                if (parseInt < i) {
                    this.tvDownloadProgress.setVisibility(0);
                    textView = this.tvDownloadProgress;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(parseInt);
                    stringBuilder.append(" of ");
                    stringBuilder.append(i);
                    stringBuilder.append(" Synced");
                    textView.setText(stringBuilder.toString());
                } else {
                    this.tvDownloadProgress.setVisibility(8);
                }
            } else {
                this.tvDownloadProgress.setVisibility(8);
            }
        } else {
            this.tvDownloadProgress.setVisibility(8);
        }
        updateDownloadImage(this.downloadImage, h);
    }

    private void deleteDownload(String str) {
        DownloadManager.c().p(Integer.parseInt(str));
        DownloadManager.c().d(Integer.parseInt(str));
        updateOfflineTracksStatus();
    }
}
