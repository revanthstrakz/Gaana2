package com.library.custom_glide;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import com.bumptech.glide.Priority;
import com.bumptech.glide.e;
import com.bumptech.glide.h;
import com.bumptech.glide.i;
import com.bumptech.glide.j;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.c;
import com.bumptech.glide.load.engine.g;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.f;
import java.io.File;
import java.net.URL;

public class GlideRequest<TranscodeType> extends h<TranscodeType> implements Cloneable {
    GlideRequest(Class<TranscodeType> cls, h<?> hVar) {
        super(cls, hVar);
    }

    GlideRequest(e eVar, i iVar, Class<TranscodeType> cls, Context context) {
        super(eVar, iVar, cls, context);
    }

    /* Access modifiers changed, original: protected */
    @CheckResult
    public GlideRequest<File> getDownloadOnlyRequest() {
        return new GlideRequest(File.class, this).apply(DOWNLOAD_ONLY_OPTIONS);
    }

    @CheckResult
    public GlideRequest<TranscodeType> sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).sizeMultiplier(f);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).sizeMultiplier(f);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> useUnlimitedSourceGeneratorsPool(boolean z) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).useUnlimitedSourceGeneratorsPool(z);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).useUnlimitedSourceGeneratorsPool(z);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> useAnimationPool(boolean z) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).useAnimationPool(z);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).useAnimationPool(z);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> onlyRetrieveFromCache(boolean z) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).onlyRetrieveFromCache(z);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).onlyRetrieveFromCache(z);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> diskCacheStrategy(@NonNull g gVar) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).diskCacheStrategy(gVar);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).diskCacheStrategy(gVar);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> priority(@NonNull Priority priority) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).priority(priority);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).priority(priority);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> placeholder(@Nullable Drawable drawable) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).placeholder(drawable);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).placeholder(drawable);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> placeholder(@DrawableRes int i) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).placeholder(i);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).placeholder(i);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> fallback(@Nullable Drawable drawable) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).fallback(drawable);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).fallback(drawable);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> fallback(@DrawableRes int i) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).fallback(i);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).fallback(i);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> error(@Nullable Drawable drawable) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).error(drawable);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).error(drawable);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> error(@DrawableRes int i) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).error(i);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).error(i);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> theme(@Nullable Theme theme) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).theme(theme);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).theme(theme);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> skipMemoryCache(boolean z) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).skipMemoryCache(z);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).skipMemoryCache(z);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> override(int i, int i2) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).override(i, i2);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).override(i, i2);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> override(int i) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).override(i);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).override(i);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> signature(@NonNull c cVar) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).signature(cVar);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).signature(cVar);
        }
        return this;
    }

    @CheckResult
    public <T> GlideRequest<TranscodeType> set(@NonNull com.bumptech.glide.load.e<T> eVar, @NonNull T t) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).set((com.bumptech.glide.load.e) eVar, (Object) t);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).set((com.bumptech.glide.load.e) eVar, (Object) t);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> decode(@NonNull Class<?> cls) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).decode((Class) cls);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).decode((Class) cls);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> encodeFormat(@NonNull CompressFormat compressFormat) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).encodeFormat(compressFormat);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).encodeFormat(compressFormat);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> encodeQuality(@IntRange(from = 0, to = 100) int i) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).encodeQuality(i);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).encodeQuality(i);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> frame(@IntRange(from = 0) long j) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).frame(j);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).frame(j);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> format(@NonNull DecodeFormat decodeFormat) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).format(decodeFormat);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).format(decodeFormat);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> disallowHardwareConfig() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).disallowHardwareConfig();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).disallowHardwareConfig();
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> downsample(@NonNull DownsampleStrategy downsampleStrategy) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).downsample(downsampleStrategy);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).downsample(downsampleStrategy);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> timeout(@IntRange(from = 0) int i) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).timeout(i);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).timeout(i);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> optionalCenterCrop() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).optionalCenterCrop();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalCenterCrop();
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> centerCrop() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).centerCrop();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).centerCrop();
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> optionalFitCenter() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).optionalFitCenter();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalFitCenter();
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> fitCenter() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).fitCenter();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).fitCenter();
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> optionalCenterInside() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).optionalCenterInside();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalCenterInside();
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> centerInside() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).centerInside();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).centerInside();
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> optionalCircleCrop() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).optionalCircleCrop();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalCircleCrop();
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> circleCrop() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).circleCrop();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).circleCrop();
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> transform(@NonNull com.bumptech.glide.load.i<Bitmap> iVar) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).transform((com.bumptech.glide.load.i) iVar);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).transform((com.bumptech.glide.load.i) iVar);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> transforms(@NonNull com.bumptech.glide.load.i<Bitmap>... iVarArr) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).transforms((com.bumptech.glide.load.i[]) iVarArr);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).transforms((com.bumptech.glide.load.i[]) iVarArr);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> optionalTransform(@NonNull com.bumptech.glide.load.i<Bitmap> iVar) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).optionalTransform((com.bumptech.glide.load.i) iVar);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalTransform((com.bumptech.glide.load.i) iVar);
        }
        return this;
    }

    @CheckResult
    public <T> GlideRequest<TranscodeType> optionalTransform(@NonNull Class<T> cls, @NonNull com.bumptech.glide.load.i<T> iVar) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).optionalTransform((Class) cls, (com.bumptech.glide.load.i) iVar);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalTransform((Class) cls, (com.bumptech.glide.load.i) iVar);
        }
        return this;
    }

    @CheckResult
    public <T> GlideRequest<TranscodeType> transform(@NonNull Class<T> cls, @NonNull com.bumptech.glide.load.i<T> iVar) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).transform((Class) cls, (com.bumptech.glide.load.i) iVar);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).transform((Class) cls, (com.bumptech.glide.load.i) iVar);
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> dontTransform() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).dontTransform();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).dontTransform();
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> dontAnimate() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).dontAnimate();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).dontAnimate();
        }
        return this;
    }

    @CheckResult
    public GlideRequest<TranscodeType> apply(@NonNull f fVar) {
        return (GlideRequest) super.apply(fVar);
    }

    @CheckResult
    public GlideRequest<TranscodeType> transition(@NonNull j<?, ? super TranscodeType> jVar) {
        return (GlideRequest) super.transition(jVar);
    }

    @CheckResult
    public GlideRequest<TranscodeType> listener(@Nullable com.bumptech.glide.request.e<TranscodeType> eVar) {
        return (GlideRequest) super.listener(eVar);
    }

    public GlideRequest<TranscodeType> error(@Nullable h<TranscodeType> hVar) {
        return (GlideRequest) super.error(hVar);
    }

    @CheckResult
    public GlideRequest<TranscodeType> thumbnail(@Nullable h<TranscodeType> hVar) {
        return (GlideRequest) super.thumbnail((h) hVar);
    }

    @SafeVarargs
    @CheckResult
    public final GlideRequest<TranscodeType> thumbnail(@Nullable h<TranscodeType>... hVarArr) {
        return (GlideRequest) super.thumbnail((h[]) hVarArr);
    }

    @CheckResult
    public GlideRequest<TranscodeType> thumbnail(float f) {
        return (GlideRequest) super.thumbnail(f);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Object obj) {
        return (GlideRequest) super.load(obj);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Bitmap bitmap) {
        return (GlideRequest) super.load(bitmap);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Drawable drawable) {
        return (GlideRequest) super.load(drawable);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable String str) {
        return (GlideRequest) super.load(str);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Uri uri) {
        return (GlideRequest) super.load(uri);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable File file) {
        return (GlideRequest) super.load(file);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable @RawRes @DrawableRes Integer num) {
        return (GlideRequest) super.load(num);
    }

    @Deprecated
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable URL url) {
        return (GlideRequest) super.load(url);
    }

    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable byte[] bArr) {
        return (GlideRequest) super.load(bArr);
    }

    @CheckResult
    public GlideRequest<TranscodeType> clone() {
        return (GlideRequest) super.clone();
    }
}
