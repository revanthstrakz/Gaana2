package com.library.custom_glide;

import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.c;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.g;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.f;

public final class GlideOptions extends f implements Cloneable {
    private static GlideOptions centerCropTransform2;
    private static GlideOptions centerInsideTransform1;
    private static GlideOptions circleCropTransform3;
    private static GlideOptions fitCenterTransform0;
    private static GlideOptions noAnimation5;
    private static GlideOptions noTransformation4;

    @CheckResult
    public static GlideOptions sizeMultiplierOf(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return new GlideOptions().sizeMultiplier(f);
    }

    @CheckResult
    public static GlideOptions diskCacheStrategyOf(@NonNull g gVar) {
        return new GlideOptions().diskCacheStrategy(gVar);
    }

    @CheckResult
    public static GlideOptions priorityOf(@NonNull Priority priority) {
        return new GlideOptions().priority(priority);
    }

    @CheckResult
    public static GlideOptions placeholderOf(@Nullable Drawable drawable) {
        return new GlideOptions().placeholder(drawable);
    }

    @CheckResult
    public static GlideOptions placeholderOf(@DrawableRes int i) {
        return new GlideOptions().placeholder(i);
    }

    @CheckResult
    public static GlideOptions errorOf(@Nullable Drawable drawable) {
        return new GlideOptions().error(drawable);
    }

    @CheckResult
    public static GlideOptions errorOf(@DrawableRes int i) {
        return new GlideOptions().error(i);
    }

    @CheckResult
    public static GlideOptions skipMemoryCacheOf(boolean z) {
        return new GlideOptions().skipMemoryCache(z);
    }

    @CheckResult
    public static GlideOptions overrideOf(@IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        return new GlideOptions().override(i, i2);
    }

    @CheckResult
    public static GlideOptions overrideOf(@IntRange(from = 0) int i) {
        return new GlideOptions().override(i);
    }

    @CheckResult
    public static GlideOptions signatureOf(@NonNull c cVar) {
        return new GlideOptions().signature(cVar);
    }

    @CheckResult
    public static GlideOptions fitCenterTransform() {
        if (fitCenterTransform0 == null) {
            fitCenterTransform0 = new GlideOptions().fitCenter().autoClone();
        }
        return fitCenterTransform0;
    }

    @CheckResult
    public static GlideOptions centerInsideTransform() {
        if (centerInsideTransform1 == null) {
            centerInsideTransform1 = new GlideOptions().centerInside().autoClone();
        }
        return centerInsideTransform1;
    }

    @CheckResult
    public static GlideOptions centerCropTransform() {
        if (centerCropTransform2 == null) {
            centerCropTransform2 = new GlideOptions().centerCrop().autoClone();
        }
        return centerCropTransform2;
    }

    @CheckResult
    public static GlideOptions circleCropTransform() {
        if (circleCropTransform3 == null) {
            circleCropTransform3 = new GlideOptions().circleCrop().autoClone();
        }
        return circleCropTransform3;
    }

    @CheckResult
    public static GlideOptions bitmapTransform(@NonNull i<Bitmap> iVar) {
        return new GlideOptions().transform((i) iVar);
    }

    @CheckResult
    public static GlideOptions noTransformation() {
        if (noTransformation4 == null) {
            noTransformation4 = new GlideOptions().dontTransform().autoClone();
        }
        return noTransformation4;
    }

    @CheckResult
    public static <T> GlideOptions option(@NonNull e<T> eVar, @NonNull T t) {
        return new GlideOptions().set((e) eVar, (Object) t);
    }

    @CheckResult
    public static GlideOptions decodeTypeOf(@NonNull Class<?> cls) {
        return new GlideOptions().decode((Class) cls);
    }

    @CheckResult
    public static GlideOptions formatOf(@NonNull DecodeFormat decodeFormat) {
        return new GlideOptions().format(decodeFormat);
    }

    @CheckResult
    public static GlideOptions frameOf(@IntRange(from = 0) long j) {
        return new GlideOptions().frame(j);
    }

    @CheckResult
    public static GlideOptions downsampleOf(@NonNull DownsampleStrategy downsampleStrategy) {
        return new GlideOptions().downsample(downsampleStrategy);
    }

    @CheckResult
    public static GlideOptions timeoutOf(@IntRange(from = 0) int i) {
        return new GlideOptions().timeout(i);
    }

    @CheckResult
    public static GlideOptions encodeQualityOf(@IntRange(from = 0, to = 100) int i) {
        return new GlideOptions().encodeQuality(i);
    }

    @CheckResult
    public static GlideOptions encodeFormatOf(@NonNull CompressFormat compressFormat) {
        return new GlideOptions().encodeFormat(compressFormat);
    }

    @CheckResult
    public static GlideOptions noAnimation() {
        if (noAnimation5 == null) {
            noAnimation5 = new GlideOptions().dontAnimate().autoClone();
        }
        return noAnimation5;
    }

    @CheckResult
    public final GlideOptions sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (GlideOptions) super.sizeMultiplier(f);
    }

    @CheckResult
    public final GlideOptions useUnlimitedSourceGeneratorsPool(boolean z) {
        return (GlideOptions) super.useUnlimitedSourceGeneratorsPool(z);
    }

    @CheckResult
    public final GlideOptions useAnimationPool(boolean z) {
        return (GlideOptions) super.useAnimationPool(z);
    }

    @CheckResult
    public final GlideOptions onlyRetrieveFromCache(boolean z) {
        return (GlideOptions) super.onlyRetrieveFromCache(z);
    }

    @CheckResult
    public final GlideOptions diskCacheStrategy(@NonNull g gVar) {
        return (GlideOptions) super.diskCacheStrategy(gVar);
    }

    @CheckResult
    public final GlideOptions priority(@NonNull Priority priority) {
        return (GlideOptions) super.priority(priority);
    }

    @CheckResult
    public final GlideOptions placeholder(@Nullable Drawable drawable) {
        return (GlideOptions) super.placeholder(drawable);
    }

    @CheckResult
    public final GlideOptions placeholder(@DrawableRes int i) {
        return (GlideOptions) super.placeholder(i);
    }

    @CheckResult
    public final GlideOptions fallback(@Nullable Drawable drawable) {
        return (GlideOptions) super.fallback(drawable);
    }

    @CheckResult
    public final GlideOptions fallback(@DrawableRes int i) {
        return (GlideOptions) super.fallback(i);
    }

    @CheckResult
    public final GlideOptions error(@Nullable Drawable drawable) {
        return (GlideOptions) super.error(drawable);
    }

    @CheckResult
    public final GlideOptions error(@DrawableRes int i) {
        return (GlideOptions) super.error(i);
    }

    @CheckResult
    public final GlideOptions theme(@Nullable Theme theme) {
        return (GlideOptions) super.theme(theme);
    }

    @CheckResult
    public final GlideOptions skipMemoryCache(boolean z) {
        return (GlideOptions) super.skipMemoryCache(z);
    }

    @CheckResult
    public final GlideOptions override(int i, int i2) {
        return (GlideOptions) super.override(i, i2);
    }

    @CheckResult
    public final GlideOptions override(int i) {
        return (GlideOptions) super.override(i);
    }

    @CheckResult
    public final GlideOptions signature(@NonNull c cVar) {
        return (GlideOptions) super.signature(cVar);
    }

    @CheckResult
    public final GlideOptions clone() {
        return (GlideOptions) super.clone();
    }

    @CheckResult
    public final <T> GlideOptions set(@NonNull e<T> eVar, @NonNull T t) {
        return (GlideOptions) super.set(eVar, t);
    }

    @CheckResult
    public final GlideOptions decode(@NonNull Class<?> cls) {
        return (GlideOptions) super.decode(cls);
    }

    @CheckResult
    public final GlideOptions encodeFormat(@NonNull CompressFormat compressFormat) {
        return (GlideOptions) super.encodeFormat(compressFormat);
    }

    @CheckResult
    public final GlideOptions encodeQuality(@IntRange(from = 0, to = 100) int i) {
        return (GlideOptions) super.encodeQuality(i);
    }

    @CheckResult
    public final GlideOptions frame(@IntRange(from = 0) long j) {
        return (GlideOptions) super.frame(j);
    }

    @CheckResult
    public final GlideOptions format(@NonNull DecodeFormat decodeFormat) {
        return (GlideOptions) super.format(decodeFormat);
    }

    @CheckResult
    public final GlideOptions disallowHardwareConfig() {
        return (GlideOptions) super.disallowHardwareConfig();
    }

    @CheckResult
    public final GlideOptions downsample(@NonNull DownsampleStrategy downsampleStrategy) {
        return (GlideOptions) super.downsample(downsampleStrategy);
    }

    @CheckResult
    public final GlideOptions timeout(@IntRange(from = 0) int i) {
        return (GlideOptions) super.timeout(i);
    }

    @CheckResult
    public final GlideOptions optionalCenterCrop() {
        return (GlideOptions) super.optionalCenterCrop();
    }

    @CheckResult
    public final GlideOptions centerCrop() {
        return (GlideOptions) super.centerCrop();
    }

    @CheckResult
    public final GlideOptions optionalFitCenter() {
        return (GlideOptions) super.optionalFitCenter();
    }

    @CheckResult
    public final GlideOptions fitCenter() {
        return (GlideOptions) super.fitCenter();
    }

    @CheckResult
    public final GlideOptions optionalCenterInside() {
        return (GlideOptions) super.optionalCenterInside();
    }

    @CheckResult
    public final GlideOptions centerInside() {
        return (GlideOptions) super.centerInside();
    }

    @CheckResult
    public final GlideOptions optionalCircleCrop() {
        return (GlideOptions) super.optionalCircleCrop();
    }

    @CheckResult
    public final GlideOptions circleCrop() {
        return (GlideOptions) super.circleCrop();
    }

    @CheckResult
    public final GlideOptions transform(@NonNull i<Bitmap> iVar) {
        return (GlideOptions) super.transform(iVar);
    }

    @SafeVarargs
    @CheckResult
    public final GlideOptions transforms(@NonNull i<Bitmap>... iVarArr) {
        return (GlideOptions) super.transforms(iVarArr);
    }

    @CheckResult
    public final GlideOptions optionalTransform(@NonNull i<Bitmap> iVar) {
        return (GlideOptions) super.optionalTransform(iVar);
    }

    @CheckResult
    public final <T> GlideOptions optionalTransform(@NonNull Class<T> cls, @NonNull i<T> iVar) {
        return (GlideOptions) super.optionalTransform((Class) cls, (i) iVar);
    }

    @CheckResult
    public final <T> GlideOptions transform(@NonNull Class<T> cls, @NonNull i<T> iVar) {
        return (GlideOptions) super.transform((Class) cls, (i) iVar);
    }

    @CheckResult
    public final GlideOptions dontTransform() {
        return (GlideOptions) super.dontTransform();
    }

    @CheckResult
    public final GlideOptions dontAnimate() {
        return (GlideOptions) super.dontAnimate();
    }

    @CheckResult
    public final GlideOptions apply(@NonNull f fVar) {
        return (GlideOptions) super.apply(fVar);
    }

    public final GlideOptions lock() {
        return (GlideOptions) super.lock();
    }

    public final GlideOptions autoClone() {
        return (GlideOptions) super.autoClone();
    }
}
