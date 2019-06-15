package com.library.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.graphics.drawable.VectorDrawable;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.engine.g;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.resource.b.c;
import com.bumptech.glide.request.f;
import com.constants.Constants;
import com.gaana.R;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.library.custom_glide.GlideApp;
import com.library.custom_glide.GlideFileLoader;
import com.library.custom_glide.transformations.RoundedCornersTransformation;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.utilities.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Random;

public class CrossFadeImageView extends AppCompatImageView implements OnClickListener {
    public static final int SCALE_MODE_DEFAULT = 0;
    public static final int SCALE_MODE_NO_SCALE = 1;
    private static final int[] defaultResIds = new int[]{R.drawable.bg_green, R.drawable.bg_indigo, R.drawable.bg_pink, R.drawable.bg_seal, R.drawable.bg_seal_2, R.drawable.bg_yellow};
    private static final Random random = new Random();
    private boolean isCrossFadeEnabled = true;
    private Bitmap mBmpResized = null;
    private Context mContext = null;
    protected Drawable mDefaultDrwable;
    private int mErrorResId;
    private ImageLoadingCompeletedListener mOnImageLodingCompeleted;
    private int mScaleMode = 0;
    private int mScreenWidth = -1;
    private String mUrl;
    private boolean showLoadingState;
    private i<Bitmap> transformation = null;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScaleModeUnit {
    }

    public interface ImageLoadingCompeletedListener {
        void onError();

        void onImageLoadingCompeleted(Bitmap bitmap);
    }

    public CrossFadeImageView(Context context) {
        super(context);
        init(context);
    }

    public CrossFadeImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CrossFadeImageView, 0, 0);
        if (obtainStyledAttributes.getBoolean(2, false)) {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(3, getResources().getDimensionPixelSize(R.dimen.dimen_4dp));
            this.transformation = new RoundedCornersTransformation(context, dimensionPixelSize, 0);
            setRoundedDrawable(getDrawable(), dimensionPixelSize);
        }
        this.showLoadingState = obtainStyledAttributes.getBoolean(4, false);
        obtainStyledAttributes.recycle();
        init(context);
        setEnabled(true);
        this.mDefaultDrwable = getDrawable();
        if (this.showLoadingState) {
            this.mDefaultDrwable = this.mContext.getResources().getDrawable(defaultResIds[random.nextInt(defaultResIds.length)]);
            setImageDrawable(this.mDefaultDrwable);
        }
        if (this.mDefaultDrwable == null) {
            this.mDefaultDrwable = context.getResources().getDrawable(17170445);
        }
    }

    public void setShowLoadingState(boolean z) {
        this.showLoadingState = z;
    }

    /* Access modifiers changed, original: protected */
    public void setDefaultDrawable(@NonNull Drawable drawable) {
        setImageDrawable(drawable);
    }

    /* Access modifiers changed, original: protected */
    public void init(Context context) {
        this.mContext = context;
        setLayoutParams(new LayoutParams(-1, -1));
    }

    public int getScaleMode() {
        return this.mScaleMode;
    }

    public void setScaleMode(int i) {
        this.mScaleMode = i;
    }

    public void bindImage(String str) {
        bindImage(str, ScaleType.CENTER_CROP, 0);
    }

    public void bindImage(String str, boolean z) {
        bindImage(str, ScaleType.FIT_CENTER, 0);
    }

    public void bindImage(String str, ScaleType scaleType) {
        bindImage(str, scaleType, 0);
    }

    public void bindImage(BusinessObject businessObject, String str, ScaleType scaleType) {
        if (!(businessObject instanceof Track)) {
            bindImage(str, scaleType, 0);
        } else if (DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId())) != DownloadStatus.DOWNLOADED) {
            bindImage(str, scaleType, 0);
        } else {
            bindImage(businessObject, str, ScaleType.FIT_CENTER, 0, false, true);
        }
    }

    public void bindImage(BusinessObject businessObject, String str, ScaleType scaleType, boolean z) {
        if (!(businessObject instanceof Track)) {
            bindImage(str, scaleType, 0);
        } else if (DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId())) != DownloadStatus.DOWNLOADED) {
            bindImage(str, scaleType, 0);
        } else {
            bindImage(businessObject, str, scaleType, 0, false, true);
        }
    }

    public void bindRectImage(String str) {
        if (str != null || str.length() > 0) {
            bindImage(str, ScaleType.CENTER_CROP, 0);
        }
    }

    public void bindImage(BusinessObject businessObject, String str, boolean z) {
        if (!(businessObject instanceof Track)) {
            bindImage(str, 0);
        } else if (DownloadManager.c().e(Integer.parseInt(((Track) businessObject).getBusinessObjId())) != DownloadStatus.DOWNLOADED) {
            bindImage(str, 0);
        } else {
            bindImage(businessObject, str, ScaleType.FIT_CENTER, 0, false, true);
        }
    }

    public void bindImage(String str, int i) {
        bindImage(str, ScaleType.CENTER_CROP, i);
    }

    public void bindImage(String str, ScaleType scaleType, int i) {
        bindImage(str, scaleType, i, false);
    }

    public void bindImage(String str, ScaleType scaleType, int i, boolean z) {
        if (str != null && str.length() != 0) {
            f placeholder = new f().onlyRetrieveFromCache(Util.W().booleanValue() ^ 1).placeholder(this.mDefaultDrwable);
            if (scaleType == ScaleType.FIT_CENTER || scaleType == ScaleType.CENTER_INSIDE) {
                placeholder = placeholder.fitCenter();
            } else if (scaleType == ScaleType.CENTER_CROP) {
                placeholder = placeholder.centerCrop();
            }
            setScaleType(scaleType);
            setScaleMode(i);
            this.mUrl = str;
            if (z) {
                placeholder = placeholder.diskCacheStrategy(g.b).skipMemoryCache(true);
            }
            makeRequest(placeholder, str);
        }
    }

    public void bindImage(BusinessObject businessObject, String str, ScaleType scaleType, int i, boolean z, boolean z2) {
        if (str != null && str.length() != 0) {
            f onlyRetrieveFromCache = new f().placeholder(this.mDefaultDrwable).onlyRetrieveFromCache(Util.W().booleanValue() ^ 1);
            if (scaleType == ScaleType.FIT_CENTER || scaleType == ScaleType.CENTER_INSIDE) {
                onlyRetrieveFromCache = onlyRetrieveFromCache.fitCenter();
            } else if (scaleType == ScaleType.CENTER_CROP) {
                onlyRetrieveFromCache = onlyRetrieveFromCache.centerCrop();
            }
            setScaleType(scaleType);
            setScaleMode(i);
            this.mUrl = str;
            if (z) {
                makeRequest(onlyRetrieveFromCache.diskCacheStrategy(g.b).skipMemoryCache(true), str);
            } else {
                Track track = (Track) businessObject;
                if (!TextUtils.isEmpty(track.getArtworkLarge())) {
                    str = Util.f(this.mContext, track.getArtworkLarge());
                }
                if (GlideFileLoader.contains(str)) {
                    bindFromFile(str.replaceAll("/", ""), onlyRetrieveFromCache);
                } else if (z2) {
                    makeRequest(onlyRetrieveFromCache, str);
                    com.i.i.a().a(businessObject.getBusinessObjId(), Util.f(this.mContext, track.getArtworkLarge()), null);
                } else {
                    makeRequest(onlyRetrieveFromCache, str);
                }
            }
        }
    }

    private void bindFromFile(String str, f fVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GlideFileLoader.getPath());
        stringBuilder.append("/");
        stringBuilder.append(str);
        makeRequest(fVar, stringBuilder.toString());
    }

    public void bindImage(String str, ImageLoadingCompeletedListener imageLoadingCompeletedListener, ScaleType scaleType) {
        this.mOnImageLodingCompeleted = imageLoadingCompeletedListener;
        bindImage(str, scaleType);
    }

    public void bindImage(String str, ImageLoadingCompeletedListener imageLoadingCompeletedListener, boolean z) {
        this.mOnImageLodingCompeleted = imageLoadingCompeletedListener;
        bindImage(str);
    }

    public void bindImageForLocalMedia(String str, ImageLoadingCompeletedListener imageLoadingCompeletedListener, LocalMediaImageLoader localMediaImageLoader, boolean z) {
        this.mOnImageLodingCompeleted = imageLoadingCompeletedListener;
        if (str != null && str.length() != 0) {
            setScaleType(ScaleType.CENTER_CROP);
            setScaleMode(0);
            this.mUrl = str;
            String str2 = "";
            if (!(this.mDefaultDrwable == null || !this.isCrossFadeEnabled || this.mUrl.equalsIgnoreCase(str2))) {
                setImageDrawable(this.mDefaultDrwable);
            }
            com.i.i.a().a(str, this, localMediaImageLoader);
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            setBitmapToImageView(bitmap);
        }
    }

    public void setBlankImage() {
        GlideApp.with(this.mContext.getApplicationContext()).load(null).into((ImageView) this);
    }

    public void setImageResource(int i) {
        if (i != this.mErrorResId) {
            setBitmapToImageView(i);
        }
    }

    public void setBitmapToImageView(Bitmap bitmap) {
        if (bitmap != null) {
            setVisibility(0);
            if (this.mOnImageLodingCompeleted != null) {
                this.mOnImageLodingCompeleted.onImageLoadingCompeleted(bitmap);
            }
            this.mBmpResized = bitmap;
            super.setImageBitmap(this.mBmpResized);
        }
    }

    public void setBitmapToImageView(Bitmap bitmap, Boolean bool) {
        setVisibility(0);
        if (this.mOnImageLodingCompeleted != null) {
            this.mOnImageLodingCompeleted.onImageLoadingCompeleted(bitmap);
        }
        this.mBmpResized = bitmap;
        if (this.mDefaultDrwable == null || bool.booleanValue() || !this.isCrossFadeEnabled) {
            super.setImageBitmap(this.mBmpResized);
        } else {
            setFadeEffect();
        }
    }

    public void setBitmapToImageView(int i) {
        Bitmap bitmap;
        Drawable drawable = ContextCompat.getDrawable(this.mContext, i);
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else if ((drawable instanceof VectorDrawableCompat) || (drawable instanceof VectorDrawable)) {
            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_4444);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            bitmap = createBitmap;
        } else {
            bitmap = null;
        }
        setImageBitmap(bitmap);
    }

    private void setImageViewToFullScreen(Bitmap bitmap) {
        int width = getWidth();
        if (width == -1 || width == 0) {
            width = getScreenWidth();
        }
        int round = Math.round(((float) width) / (((float) bitmap.getWidth()) / ((float) bitmap.getHeight())));
        getLayoutParams().width = width;
        getLayoutParams().height = round;
    }

    private void setFadeEffect() {
        if (Constants.cN) {
            setImageDrawable(new BitmapDrawable(getResources(), this.mBmpResized));
            return;
        }
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{this.mDefaultDrwable, new BitmapDrawable(getResources(), this.mBmpResized)});
        transitionDrawable.setCrossFadeEnabled(true);
        setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(1000);
    }

    private int getScreenWidth() {
        if (this.mScreenWidth == -1) {
            this.mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        }
        return this.mScreenWidth;
    }

    public void onClick(View view) {
        bindImage(this.mUrl, getScaleType(), getScaleMode());
    }

    public void onViewRecycled() {
        GlideApp.with(this.mContext.getApplicationContext()).clear((View) this);
    }

    public void setBitmapTransformation(i<Bitmap> iVar) {
        this.transformation = iVar;
    }

    private void makeRequest(f fVar, String str) {
        if (this.transformation != null) {
            if (fVar.getTransformations().get(Bitmap.class) != null) {
                fVar = fVar.transform(Bitmap.class, new d((i) fVar.getTransformations().get(Bitmap.class), this.transformation));
            } else {
                fVar = fVar.transform(Bitmap.class, this.transformation);
            }
        }
        if (Constants.cN) {
            if (this.transformation == null) {
                fVar = fVar.dontTransform();
            }
            GlideApp.with(this.mContext.getApplicationContext()).load((Object) str).apply(fVar).into((ImageView) this);
            return;
        }
        GlideApp.with(this.mContext.getApplicationContext()).load((Object) str).transition(c.c()).apply(fVar).into((ImageView) this);
    }

    /* Access modifiers changed, original: protected */
    public void setRoundedDrawable(Drawable drawable, int i) {
        if (drawable == null) {
            return;
        }
        RoundedBitmapDrawable create;
        if (drawable instanceof BitmapDrawable) {
            create = RoundedBitmapDrawableFactory.create(getResources(), ((BitmapDrawable) drawable).getBitmap());
            create.setCornerRadius((float) i);
            super.setImageDrawable(create);
        } else if ((drawable instanceof VectorDrawableCompat) || (drawable instanceof VectorDrawable)) {
            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_4444);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            create = RoundedBitmapDrawableFactory.create(getResources(), createBitmap);
            create.setCornerRadius((float) i);
            super.setImageDrawable(create);
        } else if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable).setCornerRadius((float) i);
            super.setImageDrawable(drawable);
        }
    }

    public static int getDrawableId() {
        return defaultResIds[random.nextInt(defaultResIds.length)];
    }
}
