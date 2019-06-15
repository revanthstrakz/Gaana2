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
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.library.controls.CrossFadeImageView;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.an;
import com.managers.ap;
import com.managers.i;
import com.managers.u;
import com.utilities.Util;

public class DownloadPlaylistItemView extends PlaylistItemView {
    private ImageView downloadImage;
    private CrossFadeImageView mCrossFadeImageIcon;
    private String mSearchQuery;
    private ProgressBar progressBar;
    private TextView tvSubtitle;
    private TextView tvTitle;

    public static class DownloadPlayListItemHolder extends ViewHolder {
        private CheckBox checkBox;
        private ImageView downloadImage;
        private CrossFadeImageView mCrossFadeImageIcon;
        private ImageView optionImageView;
        private ProgressBar progressBar;
        private TextView tvSubtitle;
        private TextView tvTitle;

        public DownloadPlayListItemHolder(View view) {
            super(view);
            this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.f38download.item.img.thumb);
            this.tvTitle = (TextView) view.findViewById(R.id.f43download.item.tv.trackname);
            this.tvSubtitle = (TextView) view.findViewById(R.id.f40download.item.tv.genere);
            this.downloadImage = (ImageView) view.findViewById(R.id.f37download.item.img.download);
            this.progressBar = (ProgressBar) view.findViewById(R.id.f34download.item.ProgressBar);
            this.optionImageView = (ImageView) view.findViewById(R.id.clickoptionImage);
            this.checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
        }
    }

    public DownloadPlaylistItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        ((BaseActivity) this.mContext).currentItem = "Playlist";
        this.mLayoutId = R.layout.view_item_download;
    }

    public View getView(ViewGroup viewGroup) {
        return super.createNewBaseView(this.mLayoutId, null, viewGroup);
    }

    public void setSearchQuery(String str) {
        this.mSearchQuery = str;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        DownloadPlayListItemHolder downloadPlayListItemHolder = (DownloadPlayListItemHolder) viewHolder;
        this.mView = downloadPlayListItemHolder.itemView;
        final BusinessObject businessObject2 = (Playlist) businessObject;
        this.mView.setTag(businessObject2);
        super.getPoplatedView(this.mView, businessObject);
        this.mCrossFadeImageIcon = downloadPlayListItemHolder.mCrossFadeImageIcon;
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
        this.tvTitle = downloadPlayListItemHolder.tvTitle;
        this.tvTitle.setText(Util.c(this.mSearchQuery, businessObject2.getName()));
        this.progressBar = downloadPlayListItemHolder.progressBar;
        this.tvSubtitle = downloadPlayListItemHolder.tvSubtitle;
        if (businessObject2.isParentalWarningEnabled()) {
            this.tvSubtitle.setVisibility(0);
            this.tvSubtitle.setText("");
            this.tvSubtitle.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(Util.Y()), null, null, null);
        } else {
            this.tvSubtitle.setVisibility(8);
        }
        final CheckBox access$400 = downloadPlayListItemHolder.checkBox;
        this.downloadImage = downloadPlayListItemHolder.downloadImage;
        ImageView access$600 = downloadPlayListItemHolder.optionImageView;
        final int a = Util.a(businessObject2.getBusinessObjId());
        access$400.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (i.a().c(businessObject2.getBusinessObjId(), false)) {
                    i.a().b(businessObject2.getBusinessObjId(), false);
                    access$400.setChecked(false);
                    return;
                }
                i.a().a(businessObject2.getBusinessObjId(), false);
                access$400.setChecked(true);
            }
        });
        if ((this.mFragment instanceof DownloadDetailsFragment) && i.a().f()) {
            this.downloadImage.setVisibility(8);
            access$600.setVisibility(4);
            this.progressBar.setVisibility(8);
            access$400.setVisibility(0);
            if (i.a().c(businessObject2.getBusinessObjId(), false)) {
                access$400.setChecked(true);
            } else if (i.a().g()) {
                access$400.setChecked(true);
                i.a().a(businessObject2.getBusinessObjId(), false);
            } else {
                access$400.setChecked(false);
            }
            return this.mView;
        }
        access$600.setVisibility(0);
        access$400.setVisibility(8);
        downloadPlayListItemHolder.optionImageView.setVisibility(0);
        downloadPlayListItemHolder.optionImageView.setTag(businessObject);
        downloadPlayListItemHolder.optionImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DownloadPlaylistItemView.this.showOptionMenu(view);
            }
        });
        if (businessObject2.isLocalMedia()) {
            this.downloadImage.setVisibility(0);
            new int[1][0] = R.attr.offline_icon;
            TypedArray obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            Drawable drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(64, -1));
            obtainStyledAttributes2.recycle();
            this.downloadImage.setImageDrawable(drawable2);
            this.downloadImage.setClickable(false);
            this.progressBar.setVisibility(8);
        } else {
            TypedValue typedValue;
            downloadPlayListItemHolder.downloadImage.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    an.a().a("click", "ac", businessObject2.getBusinessObjId(), "", businessObject2.getBusinessObjType().name(), "download", "", "");
                    if (DownloadPlaylistItemView.this.mAppState.isAppInOfflineMode()) {
                        ((BaseActivity) DownloadPlaylistItemView.this.mContext).displayFeatureNotAvailableOfflineDialog(DownloadPlaylistItemView.this.mContext.getString(R.string.this_feature));
                    } else if (Util.j(DownloadPlaylistItemView.this.mContext)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(((BaseActivity) DownloadPlaylistItemView.this.mContext).currentScreen);
                        stringBuilder.append(" - ");
                        stringBuilder.append(((BaseActivity) DownloadPlaylistItemView.this.mContext).currentFavpage);
                        stringBuilder.append(" - Download");
                        ((BaseActivity) DownloadPlaylistItemView.this.mContext).sendGAEvent(((BaseActivity) DownloadPlaylistItemView.this.mContext).currentScreen, "Download", stringBuilder.toString());
                        MoEngage.getInstance().reportDownload(businessObject2);
                        DownloadStatus h = DownloadManager.c().h(a);
                        if (h == null || h == DownloadStatus.PAUSED || h == DownloadStatus.PARTIALLY_DOWNLOADED || h == DownloadStatus.TRIED_BUT_FAILED) {
                            ((BaseActivity) DownloadPlaylistItemView.this.mContext).showProgressDialog(Boolean.valueOf(true), DownloadPlaylistItemView.this.mContext.getString(R.string.loading));
                            DownloadPlaylistItemView.this.startDownload(businessObject2);
                        } else if (h == DownloadStatus.DOWNLOADED) {
                            if (ap.a().k()) {
                                Util.a(DownloadPlaylistItemView.this.mContext, null);
                                u.a().a("Expired Download", "Click", "Playlist");
                            } else {
                                new CustomDialogView(DownloadPlaylistItemView.this.mContext, DownloadPlaylistItemView.this.mContext.getString(R.string.remove_album_from_download), new OnButtonClickListener() {
                                    public void onNegativeButtonClick() {
                                    }

                                    public void onPositiveButtonClick() {
                                        DownloadPlaylistItemView.this.deleteDownload(businessObject2.getBusinessObjId());
                                        DownloadPlaylistItemView.this.updateDownloadImage(DownloadPlaylistItemView.this.downloadImage, DownloadManager.c().h(a));
                                    }
                                }).show();
                            }
                        } else if (h == DownloadStatus.QUEUED) {
                            new CustomDialogView(DownloadPlaylistItemView.this.mContext, DownloadPlaylistItemView.this.mContext.getResources().getString(R.string.toast_remove_queue_playlist), new OnButtonClickListener() {
                                public void onNegativeButtonClick() {
                                }

                                public void onPositiveButtonClick() {
                                    DownloadManager.c().r(a);
                                    DownloadPlaylistItemView.this.updateDownloadImage(DownloadPlaylistItemView.this.downloadImage, DownloadManager.c().h(a));
                                    DownloadPlaylistItemView.this.updateOfflineTracksStatus();
                                }
                            }).show();
                        } else if (h == DownloadStatus.DOWNLOADING) {
                            new CustomDialogView(DownloadPlaylistItemView.this.mContext, DownloadPlaylistItemView.this.mContext.getResources().getString(R.string.toast_stop_download), new OnButtonClickListener() {
                                public void onNegativeButtonClick() {
                                }

                                public void onPositiveButtonClick() {
                                    DownloadPlaylistItemView.this.progressBar.setVisibility(8);
                                    DownloadPlaylistItemView.this.downloadImage.setVisibility(0);
                                    DownloadManager.c().r(a);
                                    DownloadPlaylistItemView.this.updateDownloadImage(DownloadPlaylistItemView.this.downloadImage, DownloadManager.c().h(a));
                                    DownloadPlaylistItemView.this.updateOfflineTracksStatus();
                                }
                            }).show();
                        }
                    } else {
                        ap.a().f(DownloadPlaylistItemView.this.mContext);
                    }
                }
            });
            if (businessObject.isLocalMedia() || !this.mAppState.isAppInOfflineMode() || DownloadManager.c().b(businessObject2).booleanValue()) {
                typedValue = new TypedValue();
                this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
                this.tvTitle.setTextColor(typedValue.data);
            } else {
                this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
            }
            if (a != 0) {
                setUpdateDownloadStatus(businessObject2);
            }
            typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
            downloadPlayListItemHolder.tvTitle.setTextColor(typedValue.data);
        }
        return this.mView;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutId, view, viewGroup);
        }
        final BusinessObject businessObject2 = (Playlist) businessObject;
        view.setTag(businessObject2);
        super.getPoplatedView(view, businessObject);
        this.mView = view;
        this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.f38download.item.img.thumb);
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
        this.tvTitle = (TextView) view.findViewById(R.id.f43download.item.tv.trackname);
        this.tvTitle.setText(Util.c(this.mSearchQuery, businessObject2.getName()));
        this.progressBar = (ProgressBar) view.findViewById(R.id.f34download.item.ProgressBar);
        this.tvSubtitle = (TextView) view.findViewById(R.id.f40download.item.tv.genere);
        if (businessObject2.isParentalWarningEnabled()) {
            this.tvSubtitle.setVisibility(0);
            this.tvSubtitle.setText("");
            this.tvSubtitle.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(Util.Y()), null, null, null);
        } else {
            this.tvSubtitle.setVisibility(8);
        }
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
        this.downloadImage = (ImageView) view.findViewById(R.id.f37download.item.img.download);
        ImageView imageView = (ImageView) view.findViewById(R.id.clickoptionImage);
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
            imageView.setVisibility(4);
            this.progressBar.setVisibility(8);
            checkBox.setVisibility(0);
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
        imageView.setVisibility(0);
        checkBox.setVisibility(8);
        view.findViewById(R.id.clickoptionImage).setVisibility(0);
        view.findViewById(R.id.clickoptionImage).setTag(businessObject);
        view.findViewById(R.id.clickoptionImage).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DownloadPlaylistItemView.this.showOptionMenu(view);
            }
        });
        if (businessObject2.isLocalMedia()) {
            this.downloadImage.setVisibility(0);
            new int[1][0] = R.attr.offline_icon;
            TypedArray obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            Drawable drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(64, -1));
            obtainStyledAttributes2.recycle();
            this.downloadImage.setImageDrawable(drawable2);
            this.downloadImage.setClickable(false);
            this.progressBar.setVisibility(8);
        } else {
            TypedValue typedValue;
            view.findViewById(R.id.f37download.item.img.download).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (DownloadPlaylistItemView.this.mAppState.isAppInOfflineMode()) {
                        ((BaseActivity) DownloadPlaylistItemView.this.mContext).displayFeatureNotAvailableOfflineDialog(DownloadPlaylistItemView.this.mContext.getString(R.string.this_feature));
                    } else if (Util.j(DownloadPlaylistItemView.this.mContext)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(((BaseActivity) DownloadPlaylistItemView.this.mContext).currentScreen);
                        stringBuilder.append(" - ");
                        stringBuilder.append(((BaseActivity) DownloadPlaylistItemView.this.mContext).currentFavpage);
                        stringBuilder.append(" - Download");
                        ((BaseActivity) DownloadPlaylistItemView.this.mContext).sendGAEvent(((BaseActivity) DownloadPlaylistItemView.this.mContext).currentScreen, "Download", stringBuilder.toString());
                        MoEngage.getInstance().reportDownload(businessObject2);
                        DownloadStatus h = DownloadManager.c().h(a);
                        if (h == null || h == DownloadStatus.PAUSED || h == DownloadStatus.PARTIALLY_DOWNLOADED || h == DownloadStatus.TRIED_BUT_FAILED) {
                            ((BaseActivity) DownloadPlaylistItemView.this.mContext).showProgressDialog(Boolean.valueOf(true), DownloadPlaylistItemView.this.mContext.getString(R.string.loading));
                            DownloadPlaylistItemView.this.startDownload(businessObject2);
                        } else if (h == DownloadStatus.DOWNLOADED) {
                            if (ap.a().k()) {
                                Util.a(DownloadPlaylistItemView.this.mContext, null);
                                u.a().a("Expired Download", "Click", "Playlist");
                            } else {
                                new CustomDialogView(DownloadPlaylistItemView.this.mContext, DownloadPlaylistItemView.this.mContext.getString(R.string.remove_album_from_download), new OnButtonClickListener() {
                                    public void onNegativeButtonClick() {
                                    }

                                    public void onPositiveButtonClick() {
                                        DownloadPlaylistItemView.this.deleteDownload(businessObject2.getBusinessObjId());
                                        DownloadPlaylistItemView.this.updateDownloadImage(DownloadPlaylistItemView.this.downloadImage, DownloadManager.c().h(a));
                                    }
                                }).show();
                            }
                        } else if (h == DownloadStatus.QUEUED) {
                            new CustomDialogView(DownloadPlaylistItemView.this.mContext, DownloadPlaylistItemView.this.mContext.getResources().getString(R.string.toast_remove_queue_playlist), new OnButtonClickListener() {
                                public void onNegativeButtonClick() {
                                }

                                public void onPositiveButtonClick() {
                                    DownloadManager.c().r(a);
                                    DownloadPlaylistItemView.this.updateDownloadImage(DownloadPlaylistItemView.this.downloadImage, DownloadManager.c().h(a));
                                    DownloadPlaylistItemView.this.updateOfflineTracksStatus();
                                }
                            }).show();
                        } else if (h == DownloadStatus.DOWNLOADING) {
                            new CustomDialogView(DownloadPlaylistItemView.this.mContext, DownloadPlaylistItemView.this.mContext.getResources().getString(R.string.toast_stop_download), new OnButtonClickListener() {
                                public void onNegativeButtonClick() {
                                }

                                public void onPositiveButtonClick() {
                                    DownloadPlaylistItemView.this.progressBar.setVisibility(8);
                                    DownloadPlaylistItemView.this.downloadImage.setVisibility(0);
                                    DownloadManager.c().r(a);
                                    DownloadPlaylistItemView.this.updateDownloadImage(DownloadPlaylistItemView.this.downloadImage, DownloadManager.c().h(a));
                                    DownloadPlaylistItemView.this.updateOfflineTracksStatus();
                                }
                            }).show();
                        }
                    } else {
                        ap.a().f(DownloadPlaylistItemView.this.mContext);
                    }
                }
            });
            if (businessObject.isLocalMedia() || !this.mAppState.isAppInOfflineMode() || DownloadManager.c().b(businessObject2).booleanValue()) {
                typedValue = new TypedValue();
                this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
                this.tvTitle.setTextColor(typedValue.data);
            } else {
                this.tvTitle.setTextColor(this.mContext.getResources().getColor(R.color.text_disabled));
            }
            if (a != 0) {
                setUpdateDownloadStatus(businessObject2);
            }
            typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
            ((TextView) view.findViewById(R.id.f43download.item.tv.trackname)).setTextColor(typedValue.data);
        }
        return view;
    }

    public void onClick(View view) {
        Util.a(this.mContext, view);
        this.mBusinessObject = (BusinessObject) view.getTag();
        Playlist playlist = (Playlist) this.mBusinessObject;
        if (this.mFragment instanceof DownloadDetailsFragment) {
            an.a().a("click", "ac", playlist.getBusinessObjId(), "", playlist.getBusinessObjType().name(), "download", "", "");
        } else if (this.mFragment instanceof FavoritesFragment) {
            an.a().a("click", "ac", playlist.getBusinessObjId(), "", playlist.getBusinessObjType().name(), "fav", "", "");
        }
        if ((this.mFragment instanceof DownloadDetailsFragment) && i.a().f()) {
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.f35download.item.checkbox);
            if (i.a().c(playlist.getBusinessObjId(), false)) {
                i.a().b(playlist.getBusinessObjId(), false);
                checkBox.setChecked(false);
            } else {
                i.a().a(playlist.getBusinessObjId(), false);
                checkBox.setChecked(true);
            }
            return;
        }
        super.onClick(view);
    }

    private void setUpdateDownloadStatus(Playlist playlist) {
        int parseInt = Integer.parseInt(playlist.getBusinessObjId());
        DownloadStatus h = DownloadManager.c().h(parseInt);
        this.progressBar.setVisibility(8);
        this.downloadImage.setVisibility(0);
        int i;
        TextView textView;
        StringBuilder stringBuilder;
        if (h == DownloadStatus.DOWNLOADING && DownloadManager.c().k() != -1) {
            i = DownloadManager.c().i(parseInt);
            parseInt = DownloadManager.c().j(parseInt);
            this.tvSubtitle.setVisibility(0);
            this.progressBar.setVisibility(0);
            this.downloadImage.setVisibility(4);
            if (i < parseInt) {
                textView = this.tvSubtitle;
                stringBuilder = new StringBuilder();
                stringBuilder.append(i);
                stringBuilder.append(" of ");
                stringBuilder.append(parseInt);
                stringBuilder.append(" Synced");
                textView.setText(stringBuilder.toString());
            }
        } else if (h == DownloadStatus.PARTIALLY_DOWNLOADED || h == DownloadStatus.DOWNLOADING || h == DownloadStatus.DOWNLOADED) {
            i = DownloadManager.c().i(parseInt);
            parseInt = DownloadManager.c().j(parseInt);
            if (i < parseInt) {
                this.tvSubtitle.setVisibility(0);
                textView = this.tvSubtitle;
                stringBuilder = new StringBuilder();
                stringBuilder.append(i);
                stringBuilder.append(" of ");
                stringBuilder.append(parseInt);
                stringBuilder.append(" Synced");
                textView.setText(stringBuilder.toString());
            } else {
                this.tvSubtitle.setVisibility(8);
            }
        } else {
            this.tvSubtitle.setVisibility(8);
        }
        if (playlist.isParentalWarningEnabled()) {
            this.tvSubtitle.setVisibility(0);
            this.tvSubtitle.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(Util.Y()), null, null, null);
        } else {
            this.tvSubtitle.setVisibility(8);
        }
        updateDownloadImage(this.downloadImage, h);
    }

    private void deleteDownload(String str) {
        DownloadManager.c().p(Integer.parseInt(str));
        DownloadManager.c().d(Integer.parseInt(str));
        updateOfflineTracksStatus();
    }
}
