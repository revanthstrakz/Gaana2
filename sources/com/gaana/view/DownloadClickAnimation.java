package com.gaana.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.fragments.PlayerFragmentV2;
import com.fragments.PlayerFragmentV4;
import com.fragments.PlayerRadioFragmentV4;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.fragments.BaseFragment;
import com.gaana.models.BusinessObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaanavideo.VideoCoachmarkActivity;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.an;
import com.managers.ap;
import com.managers.j;
import com.managers.j.a;
import com.managers.u;
import com.utilities.Util;
import com.views.RateTextCircularProgressBar;

public class DownloadClickAnimation extends BaseItemView implements a {
    private BusinessObject businessObj;
    private View mView;
    private RateTextCircularProgressBar rateTextCircularProgressBar;

    public DownloadClickAnimation(Context context, BaseFragment baseFragment, ImageView imageView, BusinessObject businessObject, View view) {
        super(context, baseFragment, 0);
        this.businessObj = businessObject;
        this.mView = view;
        changeDownlaodButtonIcon(businessObject, imageView);
    }

    public void changeDownlaodButtonIcon(final BusinessObject businessObject, final ImageView imageView) {
        j.a(this.mContext).a(this);
        final int a = Util.a(businessObject.getBusinessObjId());
        PlayerManager.a(this.mContext).i();
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
        TypedArray obtainStyledAttributes;
        Drawable drawable;
        if (businessObject.isLocalMedia()) {
            imageView.setVisibility(0);
            new int[1][0] = R.attr.offline_icon;
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(64, -1));
            obtainStyledAttributes.recycle();
            if (((GaanaActivity) this.mContext).isPlayerFullScreen()) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.vector_my_music_local_white));
            } else {
                imageView.setImageDrawable(drawable);
            }
            imageView.setClickable(false);
        } else {
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    DownloadClickAnimation.this.downloadTrack(String.valueOf(a), businessObject, imageView);
                    if ((businessObject instanceof Track) && ((Track) businessObject).isFreeDownloadEnabled() && ((Track) businessObject).getDownloadStatus() != DownloadStatus.DOWNLOADED) {
                        u.a().a("Free Download", "Click", ((BaseActivity) DownloadClickAnimation.this.mContext).currentScreen);
                    }
                    BaseFragment baseFragment = ((GaanaActivity) DownloadClickAnimation.this.mContext).getmCurrentPlayerFragment();
                    if (baseFragment instanceof PlayerFragmentV4) {
                        PlayerFragmentV4 playerFragmentV4 = (PlayerFragmentV4) baseFragment;
                        playerFragmentV4.a(false);
                        playerFragmentV4.e();
                        playerFragmentV4.d();
                    } else if (baseFragment instanceof PlayerRadioFragmentV4) {
                        PlayerRadioFragmentV4 playerRadioFragmentV4 = (PlayerRadioFragmentV4) baseFragment;
                        playerRadioFragmentV4.a(false);
                        playerRadioFragmentV4.e();
                        playerRadioFragmentV4.d();
                    }
                }
            });
            Drawable drawable2;
            if (e == null) {
                imageView.setVisibility(0);
                if ((businessObject instanceof Track) && ((Track) businessObject).isFreeDownloadEnabled() && Util.v()) {
                    setFreeDownloadIcon(imageView);
                } else {
                    new int[1][0] = R.attr.download_button_paused;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
                    obtainStyledAttributes.recycle();
                    if (((GaanaActivity) this.mContext).isPlayerFullScreen() || ((VideoCoachmarkActivity.a != null && VideoCoachmarkActivity.a.equals("COACHMARK_PLAYER_VIEW_PAGER")) || (VideoCoachmarkActivity.a != null && VideoCoachmarkActivity.a.equals("DOWNLOAD_PLAYER_TRACK_COACHMARK")))) {
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.vector_ab_download_white));
                    } else {
                        imageView.setImageDrawable(drawable2);
                    }
                }
            } else if (e == DownloadStatus.DOWNLOADING) {
                if (DownloadManager.c().w()) {
                    imageView.setVisibility(4);
                } else {
                    imageView.setVisibility(0);
                    imageView.setImageResource(R.drawable.vector_download_queued);
                }
            } else if (e == DownloadStatus.DOWNLOADED) {
                imageView.setVisibility(0);
                if (ap.a().o()) {
                    imageView.setImageResource(R.drawable.vector_download_completed);
                } else if (ap.a().m()) {
                    if (DownloadManager.c().j(businessObject.getBusinessObjId()).booleanValue()) {
                        imageView.setImageResource(R.drawable.vector_download_completed);
                    } else {
                        new int[1][0] = R.attr.download_button_disabled;
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(12, -1));
                        obtainStyledAttributes.recycle();
                        imageView.setImageDrawable(drawable);
                    }
                } else if (!ap.a().k() || Util.a(businessObject)) {
                    imageView.setImageResource(R.drawable.vector_download_completed);
                } else {
                    imageView.setImageResource(R.drawable.vector_download_expired_btn);
                }
            } else if (e == DownloadStatus.QUEUED) {
                imageView.setVisibility(0);
                imageView.setImageResource(R.drawable.vector_download_queued);
            } else if (e == DownloadStatus.TRIED_BUT_FAILED) {
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(91, -1));
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable);
            } else {
                imageView.setVisibility(0);
                if ((businessObject instanceof Track) && ((Track) businessObject).isFreeDownloadEnabled() && Util.v()) {
                    setFreeDownloadIcon(imageView);
                } else {
                    new int[1][0] = R.attr.download_button_paused;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
                    obtainStyledAttributes.recycle();
                    if (((GaanaActivity) this.mContext).isPlayerFullScreen()) {
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.vector_ab_download_white));
                    } else {
                        imageView.setImageDrawable(drawable2);
                    }
                }
            }
        }
        setProgressBarVisibility((RateTextCircularProgressBar) this.mView.findViewById(R.id.rate_progress_bar), e, imageView);
    }

    private void setFreeDownloadIcon(ImageView imageView) {
        imageView.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.free_download_icon_white));
        imageView.getLayoutParams().width = Util.a(this.mContext, 40);
        imageView.getLayoutParams().height = Util.a(this.mContext, 40);
    }

    private void downloadTrack(String str, BusinessObject businessObject, ImageView imageView) {
        final BusinessObject businessObject2 = businessObject;
        final ImageView imageView2 = imageView;
        final BaseFragment baseFragment = ((GaanaActivity) this.mContext).getmCurrentPlayerFragment();
        j.a(this.mContext).a(this);
        DownloadStatus e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
        } else if (Util.j(this.mContext)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
            stringBuilder.append(" - ");
            stringBuilder.append(((BaseActivity) this.mContext).currentFavpage);
            stringBuilder.append(" - Download");
            ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Download", stringBuilder.toString());
            if (this.isPlayerQueue) {
                an.a().a("click", "ac", "", "queue", "", "download", "", "");
            } else if (baseFragment != null && (baseFragment instanceof PlayerFragmentV2)) {
                an.a().a("click", "ac", "", "player", "", "download", "", "");
            }
            MoEngage.getInstance().reportDownload(businessObject2);
            if (e == DownloadStatus.DOWNLOADED) {
                if (!ap.a().k() || Util.a(businessObject)) {
                    final String str2 = str;
                    new CustomDialogView(this.mContext, this.mContext.getResources().getString(R.string.toast_delete_downloaded_song), new OnButtonClickListener() {
                        public void onNegativeButtonClick() {
                        }

                        public void onPositiveButtonClick() {
                            DownloadClickAnimation.this.deleteDownload(businessObject2);
                            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(str2));
                            if ((businessObject2 instanceof Track) && ((Track) businessObject2).isFreeDownloadEnabled() && Util.v()) {
                                DownloadClickAnimation.this.updateFreeDownloadImage(imageView2, e, true);
                            } else {
                                DownloadClickAnimation.this.updateDownloadImage(imageView2, e);
                            }
                            if (DownloadClickAnimation.this.isPlayerQueue) {
                                an.a().a("click", "ac", "", "queue", "", "dldn", "", "");
                            } else if (baseFragment != null && (baseFragment instanceof PlayerFragmentV2)) {
                                an.a().a("click", "ac", "", "player", "", "dldn", "", "");
                            }
                            if (baseFragment != null && (baseFragment instanceof PlayerFragmentV4)) {
                                u.a().a("Player", "Download", "Delete");
                                ((PlayerFragmentV4) baseFragment).d(((Integer) imageView2.getTag()).intValue());
                            }
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
                        DownloadClickAnimation.this.updateOfflineTracksStatus();
                        imageView2.setVisibility(0);
                        new int[1][0] = R.attr.download_button_paused;
                        TypedArray obtainStyledAttributes = DownloadClickAnimation.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        Drawable drawable = ContextCompat.getDrawable(DownloadClickAnimation.this.getContext(), obtainStyledAttributes.getResourceId(13, -1));
                        obtainStyledAttributes.recycle();
                        imageView2.setImageDrawable(drawable);
                        if (DownloadClickAnimation.this.isPlayerQueue) {
                            an.a().a("click", "ac", "", "queue", "", "rfq", "", "");
                        } else if (baseFragment != null && (baseFragment instanceof PlayerFragmentV2)) {
                            an.a().a("click", "ac", "", "player", "", "rfq", "", "");
                        }
                    }
                }).show();
            } else if (e == DownloadStatus.DOWNLOADING) {
                new CustomDialogView(this.mContext, this.mContext.getResources().getString(R.string.toast_stop_download), new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        DownloadManager.c().d(businessObject2.getBusinessObjId());
                        DownloadClickAnimation.this.updateOfflineTracksStatus();
                        imageView2.setVisibility(0);
                        new int[1][0] = R.attr.download_button_paused;
                        TypedArray obtainStyledAttributes = DownloadClickAnimation.this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        Drawable drawable = ContextCompat.getDrawable(DownloadClickAnimation.this.getContext(), obtainStyledAttributes.getResourceId(13, -1));
                        obtainStyledAttributes.recycle();
                        imageView2.setImageDrawable(drawable);
                        if (DownloadClickAnimation.this.isPlayerQueue) {
                            an.a().a("click", "ac", "", "queue", "", "stopdn", "", "");
                        } else if (baseFragment != null && (baseFragment instanceof PlayerFragmentV2)) {
                            an.a().a("click", "ac", "", "player", "", "stopdn", "", "");
                        }
                    }
                }).show();
            } else if (businessObject2 instanceof OfflineTrack) {
                DownloadManager c = DownloadManager.c();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("");
                stringBuilder2.append(str);
                startDownload(c.a(stringBuilder2.toString(), true));
            } else {
                startDownload(businessObject2);
                if (baseFragment != null && (baseFragment instanceof PlayerFragmentV4)) {
                    u.a().a("Player", "Download", "Start");
                }
            }
        } else {
            ap.a().f(this.mContext);
        }
    }

    private void setProgressBarVisibility(RateTextCircularProgressBar rateTextCircularProgressBar, DownloadStatus downloadStatus, ImageView imageView) {
        if (rateTextCircularProgressBar != null) {
            this.rateTextCircularProgressBar = rateTextCircularProgressBar;
            if (downloadStatus == DownloadStatus.DOWNLOADING) {
                rateTextCircularProgressBar.setVisibility(0);
                if (imageView.getVisibility() != 8) {
                    imageView.setVisibility(8);
                }
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
    }

    public void updateUiForCircularProgressBar(final int i, final int i2) {
        if (this.mContext != null && (this.mContext instanceof Activity)) {
            ((Activity) this.mContext).runOnUiThread(new Runnable() {
                public void run() {
                    if (DownloadClickAnimation.this.rateTextCircularProgressBar != null) {
                        DownloadClickAnimation.this.rateTextCircularProgressBar.setProgress(DownloadClickAnimation.this.calculatePercentage(i, i2));
                    }
                }
            });
        }
    }

    private int calculatePercentage(int i, int i2) {
        return (i <= 0 || i2 <= 0) ? 0 : (i2 * 100) / i;
    }
}
