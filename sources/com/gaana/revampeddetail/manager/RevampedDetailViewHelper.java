package com.gaana.revampeddetail.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.library.controls.CrossFadeImageView.ImageLoadingCompeletedListener;
import com.library.managers.TaskManager.TaskListner;
import com.services.h;
import com.services.l.r;
import com.utilities.Util;

public class RevampedDetailViewHelper {
    private Context mContext;
    private DisplayMetrics mDisplayMetrices = GaanaApplication.getContext().getResources().getDisplayMetrics();
    private BaseGaanaFragment mFragment;
    private BusinessObject mParentBusinessObject;

    public RevampedDetailViewHelper(Context context, BaseGaanaFragment baseGaanaFragment, BusinessObject businessObject) {
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mParentBusinessObject = businessObject;
    }

    public void bindArtworkPlaylistAlbum(final CrossFadeImageView crossFadeImageView) {
        String artwork = this.mParentBusinessObject instanceof Playlist ? ((Playlist) this.mParentBusinessObject).getArtwork() : this.mParentBusinessObject instanceof Album ? ((Album) this.mParentBusinessObject).getArtwork() : null;
        if (artwork != null) {
            artwork = Util.e(this.mContext, artwork);
        }
        if (this.mParentBusinessObject.isLocalMedia()) {
            crossFadeImageView.bindImageForLocalMedia(artwork, null, new LocalMediaImageLoader(), false);
        } else if (!(this.mParentBusinessObject instanceof Playlist) || !((Playlist) this.mParentBusinessObject).getAutoDisplayHome()) {
            if (Constants.dC.equalsIgnoreCase("2G")) {
                if (artwork != null && artwork.contains("80x80")) {
                    artwork = artwork.replace("80x80", "175x175");
                }
            } else if (artwork != null && artwork.contains("80x80")) {
                artwork = artwork.replace("80x80", "480x480");
            } else if (artwork != null && artwork.contains("175x175")) {
                artwork = artwork.replace("175x175", "480x480");
            }
            try {
                i.a().a(artwork, new r() {
                    public void onSuccessfulResponse(Bitmap bitmap) {
                        if (RevampedDetailViewHelper.this.mFragment.isAdded() && bitmap != null) {
                            crossFadeImageView.setImageBitmap(bitmap);
                        } else if (RevampedDetailViewHelper.this.mFragment.isAdded()) {
                            RevampedDetailViewHelper.this.bindDefaultArtworkPlaylistAlbum(crossFadeImageView);
                        }
                    }

                    public void onErrorResponse(VolleyError volleyError) {
                        if (RevampedDetailViewHelper.this.mFragment.isAdded()) {
                            RevampedDetailViewHelper.this.bindDefaultArtworkPlaylistAlbum(crossFadeImageView);
                        }
                    }
                });
            } catch (OutOfMemoryError unused) {
                bindDefaultArtworkPlaylistAlbum(crossFadeImageView);
            }
        }
    }

    private void bindDefaultArtworkPlaylistAlbum(final CrossFadeImageView crossFadeImageView) {
        String str = "";
        if (this.mParentBusinessObject instanceof Playlist) {
            str = ((Playlist) this.mParentBusinessObject).getArtwork();
        } else if (this.mParentBusinessObject instanceof Album) {
            str = ((Album) this.mParentBusinessObject).getArtwork();
        }
        if (str != null && str.contains("480x480")) {
            str = str.replace("480x480", "175x175");
        }
        if (str != null && str.contains("80x80")) {
            str = str.replace("80x80", "175x175");
        }
        i.a().a(str, new r() {
            public void onErrorResponse(VolleyError volleyError) {
            }

            public void onSuccessfulResponse(Bitmap bitmap) {
                RevampedDetailViewHelper.this.displayOverlayArtwork(bitmap, crossFadeImageView);
            }
        });
    }

    private void displayOverlayArtwork(final Bitmap bitmap, final ImageView imageView) {
        if (this.mFragment.isAdded() && bitmap != null) {
            h.a().a(new TaskListner() {
                private Bitmap blurredBitmap;

                public void doBackGroundTask() {
                    this.blurredBitmap = Util.a(bitmap, 30);
                }

                public void onBackGroundTaskCompleted() {
                    if (RevampedDetailViewHelper.this.mFragment.isAdded()) {
                        imageView.post(new Runnable() {
                            public void run() {
                                int dimensionPixelSize = RevampedDetailViewHelper.this.mContext.getResources().getDimensionPixelSize(R.dimen.dp160);
                                int i = (RevampedDetailViewHelper.this.mDisplayMetrices.widthPixels - dimensionPixelSize) / 2;
                                Bitmap createBitmap = Bitmap.createBitmap(RevampedDetailViewHelper.this.mDisplayMetrices.widthPixels, RevampedDetailViewHelper.this.mDisplayMetrices.widthPixels, bitmap.getConfig());
                                Canvas canvas = new Canvas(createBitmap);
                                dimensionPixelSize += i;
                                Rect rect = new Rect(i, i, dimensionPixelSize, dimensionPixelSize);
                                Rect rect2 = new Rect(0, 0, RevampedDetailViewHelper.this.mDisplayMetrices.widthPixels, RevampedDetailViewHelper.this.mDisplayMetrices.widthPixels);
                                if (AnonymousClass3.this.blurredBitmap != null) {
                                    canvas.drawBitmap(AnonymousClass3.this.blurredBitmap, null, rect2, null);
                                }
                                canvas.drawBitmap(bitmap, null, rect, null);
                                imageView.setImageBitmap(createBitmap);
                            }
                        });
                    }
                }
            }, imageView.getId());
        }
    }

    public void bindArtworkRadioandArtist(final CrossFadeImageView crossFadeImageView) {
        String str = "";
        if (this.mParentBusinessObject instanceof Radio) {
            str = ((Radio) this.mParentBusinessObject).getArtwork();
        } else if (this.mParentBusinessObject instanceof Artist) {
            str = ((Artist) this.mParentBusinessObject).getArtwork();
        }
        if (str != null && str.contains("80x80")) {
            str = str.replace("80x80", "480x480");
        } else if (str != null && str.contains("175x175")) {
            str = str.replace("175x175", "480x480");
        }
        try {
            crossFadeImageView.bindImage(str, new ImageLoadingCompeletedListener() {
                public void onImageLoadingCompeleted(Bitmap bitmap) {
                }

                public void onError() {
                    if (RevampedDetailViewHelper.this.mParentBusinessObject instanceof Artist) {
                        RevampedDetailViewHelper.this.bindDefaultArtworkArtist(crossFadeImageView);
                    } else {
                        RevampedDetailViewHelper.this.bindDefaultArtworkRadio(crossFadeImageView);
                    }
                }
            }, ScaleType.CENTER_CROP);
        } catch (OutOfMemoryError unused) {
            bindDefaultArtworkRadio(crossFadeImageView);
        }
    }

    private void bindDefaultArtworkRadio(CrossFadeImageView crossFadeImageView) {
        String artwork = ((Radio) this.mParentBusinessObject).getArtwork();
        if (artwork != null && artwork.contains("80x80")) {
            artwork = artwork.replace("80x80", "175x175");
        }
        crossFadeImageView.bindImage(artwork, ScaleType.CENTER_CROP);
    }

    private void bindDefaultArtworkArtist(final CrossFadeImageView crossFadeImageView) {
        String artwork = ((Artist) this.mParentBusinessObject).getArtwork();
        if (artwork != null && artwork.contains("480x480")) {
            artwork = artwork.replace("480x480", "175x175");
        }
        if (artwork != null && artwork.contains("80x80")) {
            artwork = artwork.replace("80x80", "175x175");
        }
        i.a().a(artwork, new r() {
            public void onErrorResponse(VolleyError volleyError) {
            }

            public void onSuccessfulResponse(Bitmap bitmap) {
                RevampedDetailViewHelper.this.displayOverlayArtwork(bitmap, crossFadeImageView);
            }
        });
    }
}
