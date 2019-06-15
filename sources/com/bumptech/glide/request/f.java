package com.bumptech.glide.request;

import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.e.b;
import com.bumptech.glide.f.h;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.b.a.a;
import com.bumptech.glide.load.c;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.g;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.k;
import com.bumptech.glide.load.resource.bitmap.m;
import com.bumptech.glide.load.resource.bitmap.n;
import com.bumptech.glide.load.resource.bitmap.v;
import java.util.HashMap;
import java.util.Map;

public class f implements Cloneable {
    private static final int DISK_CACHE_STRATEGY = 4;
    private static final int ERROR_ID = 32;
    private static final int ERROR_PLACEHOLDER = 16;
    private static final int FALLBACK = 8192;
    private static final int FALLBACK_ID = 16384;
    private static final int IS_CACHEABLE = 256;
    private static final int ONLY_RETRIEVE_FROM_CACHE = 524288;
    private static final int OVERRIDE = 512;
    private static final int PLACEHOLDER = 64;
    private static final int PLACEHOLDER_ID = 128;
    private static final int PRIORITY = 8;
    private static final int RESOURCE_CLASS = 4096;
    private static final int SIGNATURE = 1024;
    private static final int SIZE_MULTIPLIER = 2;
    private static final int THEME = 32768;
    private static final int TRANSFORMATION = 2048;
    private static final int TRANSFORMATION_ALLOWED = 65536;
    private static final int TRANSFORMATION_REQUIRED = 131072;
    private static final int UNSET = -1;
    private static final int USE_ANIMATION_POOL = 1048576;
    private static final int USE_UNLIMITED_SOURCE_GENERATORS_POOL = 262144;
    @Nullable
    private static f centerCropOptions;
    @Nullable
    private static f centerInsideOptions;
    @Nullable
    private static f circleCropOptions;
    @Nullable
    private static f fitCenterOptions;
    @Nullable
    private static f noAnimationOptions;
    @Nullable
    private static f noTransformOptions;
    @Nullable
    private static f skipMemoryCacheFalseOptions;
    @Nullable
    private static f skipMemoryCacheTrueOptions;
    @NonNull
    private g diskCacheStrategy = g.e;
    private int errorId;
    @Nullable
    private Drawable errorPlaceholder;
    @Nullable
    private Drawable fallbackDrawable;
    private int fallbackId;
    private int fields;
    private boolean isAutoCloneEnabled;
    private boolean isCacheable = true;
    private boolean isLocked;
    private boolean isScaleOnlyOrNoTransform = true;
    private boolean isTransformationAllowed = true;
    private boolean isTransformationRequired;
    private boolean onlyRetrieveFromCache;
    @NonNull
    private com.bumptech.glide.load.f options = new com.bumptech.glide.load.f();
    private int overrideHeight = -1;
    private int overrideWidth = -1;
    @Nullable
    private Drawable placeholderDrawable;
    private int placeholderId;
    @NonNull
    private Priority priority = Priority.NORMAL;
    @NonNull
    private Class<?> resourceClass = Object.class;
    @NonNull
    private c signature = b.a();
    private float sizeMultiplier = 1.0f;
    @Nullable
    private Theme theme;
    @NonNull
    private Map<Class<?>, i<?>> transformations = new HashMap();
    private boolean useAnimationPool;
    private boolean useUnlimitedSourceGeneratorsPool;

    private static boolean isSet(int i, int i2) {
        return (i & i2) != 0;
    }

    @CheckResult
    public static f sizeMultiplierOf(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return new f().sizeMultiplier(f);
    }

    @CheckResult
    public static f diskCacheStrategyOf(@NonNull g gVar) {
        return new f().diskCacheStrategy(gVar);
    }

    @CheckResult
    public static f priorityOf(@NonNull Priority priority) {
        return new f().priority(priority);
    }

    @CheckResult
    public static f placeholderOf(@Nullable Drawable drawable) {
        return new f().placeholder(drawable);
    }

    @CheckResult
    public static f placeholderOf(@DrawableRes int i) {
        return new f().placeholder(i);
    }

    @CheckResult
    public static f errorOf(@Nullable Drawable drawable) {
        return new f().error(drawable);
    }

    @CheckResult
    public static f errorOf(@DrawableRes int i) {
        return new f().error(i);
    }

    @CheckResult
    public static f skipMemoryCacheOf(boolean z) {
        if (z) {
            if (skipMemoryCacheTrueOptions == null) {
                skipMemoryCacheTrueOptions = new f().skipMemoryCache(true).autoClone();
            }
            return skipMemoryCacheTrueOptions;
        }
        if (skipMemoryCacheFalseOptions == null) {
            skipMemoryCacheFalseOptions = new f().skipMemoryCache(false).autoClone();
        }
        return skipMemoryCacheFalseOptions;
    }

    @CheckResult
    public static f overrideOf(@IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        return new f().override(i, i2);
    }

    @CheckResult
    public static f overrideOf(@IntRange(from = 0) int i) {
        return overrideOf(i, i);
    }

    @CheckResult
    public static f signatureOf(@NonNull c cVar) {
        return new f().signature(cVar);
    }

    @CheckResult
    public static f fitCenterTransform() {
        if (fitCenterOptions == null) {
            fitCenterOptions = new f().fitCenter().autoClone();
        }
        return fitCenterOptions;
    }

    @CheckResult
    public static f centerInsideTransform() {
        if (centerInsideOptions == null) {
            centerInsideOptions = new f().centerInside().autoClone();
        }
        return centerInsideOptions;
    }

    @CheckResult
    public static f centerCropTransform() {
        if (centerCropOptions == null) {
            centerCropOptions = new f().centerCrop().autoClone();
        }
        return centerCropOptions;
    }

    @CheckResult
    public static f circleCropTransform() {
        if (circleCropOptions == null) {
            circleCropOptions = new f().circleCrop().autoClone();
        }
        return circleCropOptions;
    }

    @CheckResult
    public static f bitmapTransform(@NonNull i<Bitmap> iVar) {
        return new f().transform(iVar);
    }

    @CheckResult
    public static f noTransformation() {
        if (noTransformOptions == null) {
            noTransformOptions = new f().dontTransform().autoClone();
        }
        return noTransformOptions;
    }

    @CheckResult
    public static <T> f option(@NonNull e<T> eVar, @NonNull T t) {
        return new f().set(eVar, t);
    }

    @CheckResult
    public static f decodeTypeOf(@NonNull Class<?> cls) {
        return new f().decode(cls);
    }

    @CheckResult
    public static f formatOf(@NonNull DecodeFormat decodeFormat) {
        return new f().format(decodeFormat);
    }

    @CheckResult
    public static f frameOf(@IntRange(from = 0) long j) {
        return new f().frame(j);
    }

    @CheckResult
    public static f downsampleOf(@NonNull DownsampleStrategy downsampleStrategy) {
        return new f().downsample(downsampleStrategy);
    }

    @CheckResult
    public static f timeoutOf(@IntRange(from = 0) int i) {
        return new f().timeout(i);
    }

    @CheckResult
    public static f encodeQualityOf(@IntRange(from = 0, to = 100) int i) {
        return new f().encodeQuality(i);
    }

    @CheckResult
    public static f encodeFormatOf(@NonNull CompressFormat compressFormat) {
        return new f().encodeFormat(compressFormat);
    }

    @CheckResult
    public static f noAnimation() {
        if (noAnimationOptions == null) {
            noAnimationOptions = new f().dontAnimate().autoClone();
        }
        return noAnimationOptions;
    }

    @CheckResult
    public f sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.isAutoCloneEnabled) {
            return clone().sizeMultiplier(f);
        }
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.sizeMultiplier = f;
        this.fields |= 2;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f useUnlimitedSourceGeneratorsPool(boolean z) {
        if (this.isAutoCloneEnabled) {
            return clone().useUnlimitedSourceGeneratorsPool(z);
        }
        this.useUnlimitedSourceGeneratorsPool = z;
        this.fields |= 262144;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f useAnimationPool(boolean z) {
        if (this.isAutoCloneEnabled) {
            return clone().useAnimationPool(z);
        }
        this.useAnimationPool = z;
        this.fields |= 1048576;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f onlyRetrieveFromCache(boolean z) {
        if (this.isAutoCloneEnabled) {
            return clone().onlyRetrieveFromCache(z);
        }
        this.onlyRetrieveFromCache = z;
        this.fields |= 524288;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f diskCacheStrategy(@NonNull g gVar) {
        if (this.isAutoCloneEnabled) {
            return clone().diskCacheStrategy(gVar);
        }
        this.diskCacheStrategy = (g) h.a((Object) gVar);
        this.fields |= 4;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f priority(@NonNull Priority priority) {
        if (this.isAutoCloneEnabled) {
            return clone().priority(priority);
        }
        this.priority = (Priority) h.a((Object) priority);
        this.fields |= 8;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f placeholder(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().placeholder(drawable);
        }
        this.placeholderDrawable = drawable;
        this.fields |= 64;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f placeholder(@DrawableRes int i) {
        if (this.isAutoCloneEnabled) {
            return clone().placeholder(i);
        }
        this.placeholderId = i;
        this.fields |= 128;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f fallback(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().fallback(drawable);
        }
        this.fallbackDrawable = drawable;
        this.fields |= 8192;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f fallback(@DrawableRes int i) {
        if (this.isAutoCloneEnabled) {
            return clone().fallback(i);
        }
        this.fallbackId = i;
        this.fields |= 16384;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f error(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().error(drawable);
        }
        this.errorPlaceholder = drawable;
        this.fields |= 16;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f error(@DrawableRes int i) {
        if (this.isAutoCloneEnabled) {
            return clone().error(i);
        }
        this.errorId = i;
        this.fields |= 32;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f theme(@Nullable Theme theme) {
        if (this.isAutoCloneEnabled) {
            return clone().theme(theme);
        }
        this.theme = theme;
        this.fields |= 32768;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f skipMemoryCache(boolean z) {
        if (this.isAutoCloneEnabled) {
            return clone().skipMemoryCache(true);
        }
        this.isCacheable = z ^ 1;
        this.fields |= 256;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f override(int i, int i2) {
        if (this.isAutoCloneEnabled) {
            return clone().override(i, i2);
        }
        this.overrideWidth = i;
        this.overrideHeight = i2;
        this.fields |= 512;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f override(int i) {
        return override(i, i);
    }

    @CheckResult
    public f signature(@NonNull c cVar) {
        if (this.isAutoCloneEnabled) {
            return clone().signature(cVar);
        }
        this.signature = (c) h.a((Object) cVar);
        this.fields |= 1024;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f clone() {
        try {
            f fVar = (f) super.clone();
            fVar.options = new com.bumptech.glide.load.f();
            fVar.options.a(this.options);
            fVar.transformations = new HashMap();
            fVar.transformations.putAll(this.transformations);
            fVar.isLocked = false;
            fVar.isAutoCloneEnabled = false;
            return fVar;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @CheckResult
    public <T> f set(@NonNull e<T> eVar, @NonNull T t) {
        if (this.isAutoCloneEnabled) {
            return clone().set(eVar, t);
        }
        h.a((Object) eVar);
        h.a((Object) t);
        this.options.a(eVar, t);
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f decode(@NonNull Class<?> cls) {
        if (this.isAutoCloneEnabled) {
            return clone().decode(cls);
        }
        this.resourceClass = (Class) h.a((Object) cls);
        this.fields |= 4096;
        return selfOrThrowIfLocked();
    }

    public final boolean isTransformationAllowed() {
        return this.isTransformationAllowed;
    }

    public final boolean isTransformationSet() {
        return isSet(2048);
    }

    public final boolean isLocked() {
        return this.isLocked;
    }

    @CheckResult
    public f encodeFormat(@NonNull CompressFormat compressFormat) {
        return set(com.bumptech.glide.load.resource.bitmap.c.b, h.a((Object) compressFormat));
    }

    @CheckResult
    public f encodeQuality(@IntRange(from = 0, to = 100) int i) {
        return set(com.bumptech.glide.load.resource.bitmap.c.a, Integer.valueOf(i));
    }

    @CheckResult
    public f frame(@IntRange(from = 0) long j) {
        return set(v.a, Long.valueOf(j));
    }

    @CheckResult
    public f format(@NonNull DecodeFormat decodeFormat) {
        h.a((Object) decodeFormat);
        return set(k.a, decodeFormat).set(com.bumptech.glide.load.resource.d.i.a, decodeFormat);
    }

    @CheckResult
    public f disallowHardwareConfig() {
        return set(k.d, Boolean.valueOf(false));
    }

    @CheckResult
    public f downsample(@NonNull DownsampleStrategy downsampleStrategy) {
        return set(k.b, h.a((Object) downsampleStrategy));
    }

    @CheckResult
    public f timeout(@IntRange(from = 0) int i) {
        return set(a.a, Integer.valueOf(i));
    }

    @CheckResult
    public f optionalCenterCrop() {
        return optionalTransform(DownsampleStrategy.b, new com.bumptech.glide.load.resource.bitmap.g());
    }

    @CheckResult
    public f centerCrop() {
        return transform(DownsampleStrategy.b, new com.bumptech.glide.load.resource.bitmap.g());
    }

    @CheckResult
    public f optionalFitCenter() {
        return optionalScaleOnlyTransform(DownsampleStrategy.a, new n());
    }

    @CheckResult
    public f fitCenter() {
        return scaleOnlyTransform(DownsampleStrategy.a, new n());
    }

    @CheckResult
    public f optionalCenterInside() {
        return optionalScaleOnlyTransform(DownsampleStrategy.e, new com.bumptech.glide.load.resource.bitmap.h());
    }

    @CheckResult
    public f centerInside() {
        return scaleOnlyTransform(DownsampleStrategy.e, new com.bumptech.glide.load.resource.bitmap.h());
    }

    @CheckResult
    public f optionalCircleCrop() {
        return optionalTransform(DownsampleStrategy.b, new com.bumptech.glide.load.resource.bitmap.i());
    }

    @CheckResult
    public f circleCrop() {
        return transform(DownsampleStrategy.e, new com.bumptech.glide.load.resource.bitmap.i());
    }

    /* Access modifiers changed, original: final */
    public final f optionalTransform(DownsampleStrategy downsampleStrategy, i<Bitmap> iVar) {
        if (this.isAutoCloneEnabled) {
            return clone().optionalTransform(downsampleStrategy, (i) iVar);
        }
        downsample(downsampleStrategy);
        return transform((i) iVar, false);
    }

    /* Access modifiers changed, original: final */
    @CheckResult
    public final f transform(DownsampleStrategy downsampleStrategy, i<Bitmap> iVar) {
        if (this.isAutoCloneEnabled) {
            return clone().transform(downsampleStrategy, (i) iVar);
        }
        downsample(downsampleStrategy);
        return transform(iVar);
    }

    private f scaleOnlyTransform(DownsampleStrategy downsampleStrategy, i<Bitmap> iVar) {
        return scaleOnlyTransform(downsampleStrategy, iVar, true);
    }

    private f optionalScaleOnlyTransform(DownsampleStrategy downsampleStrategy, i<Bitmap> iVar) {
        return scaleOnlyTransform(downsampleStrategy, iVar, false);
    }

    private f scaleOnlyTransform(DownsampleStrategy downsampleStrategy, i<Bitmap> iVar, boolean z) {
        f transform = z ? transform(downsampleStrategy, (i) iVar) : optionalTransform(downsampleStrategy, (i) iVar);
        transform.isScaleOnlyOrNoTransform = true;
        return transform;
    }

    @CheckResult
    public f transform(@NonNull i<Bitmap> iVar) {
        return transform((i) iVar, true);
    }

    @CheckResult
    public f transforms(@NonNull i<Bitmap>... iVarArr) {
        return transform(new d(iVarArr), true);
    }

    @CheckResult
    public f optionalTransform(@NonNull i<Bitmap> iVar) {
        return transform((i) iVar, false);
    }

    private f transform(@NonNull i<Bitmap> iVar, boolean z) {
        if (this.isAutoCloneEnabled) {
            return clone().transform((i) iVar, z);
        }
        m mVar = new m(iVar, z);
        transform(Bitmap.class, iVar, z);
        transform(Drawable.class, mVar, z);
        transform(BitmapDrawable.class, mVar.a(), z);
        transform(com.bumptech.glide.load.resource.d.c.class, new com.bumptech.glide.load.resource.d.f(iVar), z);
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public <T> f optionalTransform(@NonNull Class<T> cls, @NonNull i<T> iVar) {
        return transform(cls, iVar, false);
    }

    private <T> f transform(@NonNull Class<T> cls, @NonNull i<T> iVar, boolean z) {
        if (this.isAutoCloneEnabled) {
            return clone().transform(cls, iVar, z);
        }
        h.a((Object) cls);
        h.a((Object) iVar);
        this.transformations.put(cls, iVar);
        this.fields |= 2048;
        this.isTransformationAllowed = true;
        this.fields |= 65536;
        this.isScaleOnlyOrNoTransform = false;
        if (z) {
            this.fields |= 131072;
            this.isTransformationRequired = true;
        }
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public <T> f transform(@NonNull Class<T> cls, @NonNull i<T> iVar) {
        return transform(cls, iVar, true);
    }

    @CheckResult
    public f dontTransform() {
        if (this.isAutoCloneEnabled) {
            return clone().dontTransform();
        }
        this.transformations.clear();
        this.fields &= -2049;
        this.isTransformationRequired = false;
        this.fields &= -131073;
        this.isTransformationAllowed = false;
        this.fields |= 65536;
        this.isScaleOnlyOrNoTransform = true;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    public f dontAnimate() {
        return set(com.bumptech.glide.load.resource.d.i.b, Boolean.valueOf(true));
    }

    @CheckResult
    public f apply(@NonNull f fVar) {
        if (this.isAutoCloneEnabled) {
            return clone().apply(fVar);
        }
        if (isSet(fVar.fields, 2)) {
            this.sizeMultiplier = fVar.sizeMultiplier;
        }
        if (isSet(fVar.fields, 262144)) {
            this.useUnlimitedSourceGeneratorsPool = fVar.useUnlimitedSourceGeneratorsPool;
        }
        if (isSet(fVar.fields, 1048576)) {
            this.useAnimationPool = fVar.useAnimationPool;
        }
        if (isSet(fVar.fields, 4)) {
            this.diskCacheStrategy = fVar.diskCacheStrategy;
        }
        if (isSet(fVar.fields, 8)) {
            this.priority = fVar.priority;
        }
        if (isSet(fVar.fields, 16)) {
            this.errorPlaceholder = fVar.errorPlaceholder;
        }
        if (isSet(fVar.fields, 32)) {
            this.errorId = fVar.errorId;
        }
        if (isSet(fVar.fields, 64)) {
            this.placeholderDrawable = fVar.placeholderDrawable;
        }
        if (isSet(fVar.fields, 128)) {
            this.placeholderId = fVar.placeholderId;
        }
        if (isSet(fVar.fields, 256)) {
            this.isCacheable = fVar.isCacheable;
        }
        if (isSet(fVar.fields, 512)) {
            this.overrideWidth = fVar.overrideWidth;
            this.overrideHeight = fVar.overrideHeight;
        }
        if (isSet(fVar.fields, 1024)) {
            this.signature = fVar.signature;
        }
        if (isSet(fVar.fields, 4096)) {
            this.resourceClass = fVar.resourceClass;
        }
        if (isSet(fVar.fields, 8192)) {
            this.fallbackDrawable = fVar.fallbackDrawable;
        }
        if (isSet(fVar.fields, 16384)) {
            this.fallbackId = fVar.fallbackId;
        }
        if (isSet(fVar.fields, 32768)) {
            this.theme = fVar.theme;
        }
        if (isSet(fVar.fields, 65536)) {
            this.isTransformationAllowed = fVar.isTransformationAllowed;
        }
        if (isSet(fVar.fields, 131072)) {
            this.isTransformationRequired = fVar.isTransformationRequired;
        }
        if (isSet(fVar.fields, 2048)) {
            this.transformations.putAll(fVar.transformations);
            this.isScaleOnlyOrNoTransform = fVar.isScaleOnlyOrNoTransform;
        }
        if (isSet(fVar.fields, 524288)) {
            this.onlyRetrieveFromCache = fVar.onlyRetrieveFromCache;
        }
        if (!this.isTransformationAllowed) {
            this.transformations.clear();
            this.fields &= -2049;
            this.isTransformationRequired = false;
            this.fields &= -131073;
            this.isScaleOnlyOrNoTransform = true;
        }
        this.fields |= fVar.fields;
        this.options.a(fVar.options);
        return selfOrThrowIfLocked();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (Float.compare(fVar.sizeMultiplier, this.sizeMultiplier) == 0 && this.errorId == fVar.errorId && com.bumptech.glide.f.i.a(this.errorPlaceholder, fVar.errorPlaceholder) && this.placeholderId == fVar.placeholderId && com.bumptech.glide.f.i.a(this.placeholderDrawable, fVar.placeholderDrawable) && this.fallbackId == fVar.fallbackId && com.bumptech.glide.f.i.a(this.fallbackDrawable, fVar.fallbackDrawable) && this.isCacheable == fVar.isCacheable && this.overrideHeight == fVar.overrideHeight && this.overrideWidth == fVar.overrideWidth && this.isTransformationRequired == fVar.isTransformationRequired && this.isTransformationAllowed == fVar.isTransformationAllowed && this.useUnlimitedSourceGeneratorsPool == fVar.useUnlimitedSourceGeneratorsPool && this.onlyRetrieveFromCache == fVar.onlyRetrieveFromCache && this.diskCacheStrategy.equals(fVar.diskCacheStrategy) && this.priority == fVar.priority && this.options.equals(fVar.options) && this.transformations.equals(fVar.transformations) && this.resourceClass.equals(fVar.resourceClass) && com.bumptech.glide.f.i.a(this.signature, fVar.signature) && com.bumptech.glide.f.i.a(this.theme, fVar.theme)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return com.bumptech.glide.f.i.a(this.theme, com.bumptech.glide.f.i.a(this.signature, com.bumptech.glide.f.i.a(this.resourceClass, com.bumptech.glide.f.i.a(this.transformations, com.bumptech.glide.f.i.a(this.options, com.bumptech.glide.f.i.a(this.priority, com.bumptech.glide.f.i.a(this.diskCacheStrategy, com.bumptech.glide.f.i.a(this.onlyRetrieveFromCache, com.bumptech.glide.f.i.a(this.useUnlimitedSourceGeneratorsPool, com.bumptech.glide.f.i.a(this.isTransformationAllowed, com.bumptech.glide.f.i.a(this.isTransformationRequired, com.bumptech.glide.f.i.b(this.overrideWidth, com.bumptech.glide.f.i.b(this.overrideHeight, com.bumptech.glide.f.i.a(this.isCacheable, com.bumptech.glide.f.i.a(this.fallbackDrawable, com.bumptech.glide.f.i.b(this.fallbackId, com.bumptech.glide.f.i.a(this.placeholderDrawable, com.bumptech.glide.f.i.b(this.placeholderId, com.bumptech.glide.f.i.a(this.errorPlaceholder, com.bumptech.glide.f.i.b(this.errorId, com.bumptech.glide.f.i.a(this.sizeMultiplier)))))))))))))))))))));
    }

    public f lock() {
        this.isLocked = true;
        return this;
    }

    public f autoClone() {
        if (!this.isLocked || this.isAutoCloneEnabled) {
            this.isAutoCloneEnabled = true;
            return lock();
        }
        throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
    }

    private f selfOrThrowIfLocked() {
        if (!this.isLocked) {
            return this;
        }
        throw new IllegalStateException("You cannot modify locked RequestOptions, consider clone()");
    }

    /* Access modifiers changed, original: protected */
    public boolean isAutoCloneEnabled() {
        return this.isAutoCloneEnabled;
    }

    @NonNull
    public final Map<Class<?>, i<?>> getTransformations() {
        return this.transformations;
    }

    public final boolean isTransformationRequired() {
        return this.isTransformationRequired;
    }

    @NonNull
    public final com.bumptech.glide.load.f getOptions() {
        return this.options;
    }

    @NonNull
    public final Class<?> getResourceClass() {
        return this.resourceClass;
    }

    @NonNull
    public final g getDiskCacheStrategy() {
        return this.diskCacheStrategy;
    }

    @Nullable
    public final Drawable getErrorPlaceholder() {
        return this.errorPlaceholder;
    }

    public final int getErrorId() {
        return this.errorId;
    }

    public final int getPlaceholderId() {
        return this.placeholderId;
    }

    @Nullable
    public final Drawable getPlaceholderDrawable() {
        return this.placeholderDrawable;
    }

    public final int getFallbackId() {
        return this.fallbackId;
    }

    @Nullable
    public final Drawable getFallbackDrawable() {
        return this.fallbackDrawable;
    }

    @Nullable
    public final Theme getTheme() {
        return this.theme;
    }

    public final boolean isMemoryCacheable() {
        return this.isCacheable;
    }

    @NonNull
    public final c getSignature() {
        return this.signature;
    }

    public final boolean isPrioritySet() {
        return isSet(8);
    }

    @NonNull
    public final Priority getPriority() {
        return this.priority;
    }

    public final int getOverrideWidth() {
        return this.overrideWidth;
    }

    public final boolean isValidOverride() {
        return com.bumptech.glide.f.i.a(this.overrideWidth, this.overrideHeight);
    }

    public final int getOverrideHeight() {
        return this.overrideHeight;
    }

    public final float getSizeMultiplier() {
        return this.sizeMultiplier;
    }

    public boolean isScaleOnlyOrNoTransform() {
        return this.isScaleOnlyOrNoTransform;
    }

    private boolean isSet(int i) {
        return isSet(this.fields, i);
    }

    public final boolean getUseUnlimitedSourceGeneratorsPool() {
        return this.useUnlimitedSourceGeneratorsPool;
    }

    public final boolean getUseAnimationPool() {
        return this.useAnimationPool;
    }

    public final boolean getOnlyRetrieveFromCache() {
        return this.onlyRetrieveFromCache;
    }
}
