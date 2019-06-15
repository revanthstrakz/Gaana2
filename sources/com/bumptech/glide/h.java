package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.bumptech.glide.e.a;
import com.bumptech.glide.e.c;
import com.bumptech.glide.load.engine.g;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.SingleRequest;
import com.bumptech.glide.request.a.i;
import com.bumptech.glide.request.b;
import com.bumptech.glide.request.d;
import com.bumptech.glide.request.e;
import com.bumptech.glide.request.f;
import java.io.File;
import java.net.URL;
import java.util.UUID;

public class h<TranscodeType> implements Cloneable {
    protected static final f DOWNLOAD_ONLY_OPTIONS = new f().diskCacheStrategy(g.c).priority(Priority.LOW).skipMemoryCache(true);
    private final Context context;
    private final f defaultRequestOptions;
    @Nullable
    private h<TranscodeType> errorBuilder;
    private final e glide;
    private final g glideContext;
    private boolean isDefaultTransitionOptionsSet;
    private boolean isModelSet;
    private boolean isThumbnailBuilt;
    @Nullable
    private Object model;
    @Nullable
    private e<TranscodeType> requestListener;
    private final i requestManager;
    @NonNull
    protected f requestOptions;
    @Nullable
    private Float thumbSizeMultiplier;
    @Nullable
    private h<TranscodeType> thumbnailBuilder;
    private final Class<TranscodeType> transcodeClass;
    @NonNull
    private j<?, ? super TranscodeType> transitionOptions;

    /* renamed from: com.bumptech.glide.h$2 */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0066 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0087 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0052 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|(3:31|32|34)) */
        static {
            /*
            r0 = com.bumptech.glide.Priority.values();
            r0 = r0.length;
            r0 = new int[r0];
            b = r0;
            r0 = 1;
            r1 = b;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = com.bumptech.glide.Priority.LOW;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r1 = 2;
            r2 = b;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = com.bumptech.glide.Priority.NORMAL;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = r3.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2[r3] = r1;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r2 = 3;
            r3 = b;	 Catch:{ NoSuchFieldError -> 0x002a }
            r4 = com.bumptech.glide.Priority.HIGH;	 Catch:{ NoSuchFieldError -> 0x002a }
            r4 = r4.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
            r3[r4] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
        L_0x002a:
            r3 = 4;
            r4 = b;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r5 = com.bumptech.glide.Priority.IMMEDIATE;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x0035 }
            r4[r5] = r3;	 Catch:{ NoSuchFieldError -> 0x0035 }
        L_0x0035:
            r4 = android.widget.ImageView.ScaleType.values();
            r4 = r4.length;
            r4 = new int[r4];
            a = r4;
            r4 = a;	 Catch:{ NoSuchFieldError -> 0x0048 }
            r5 = android.widget.ImageView.ScaleType.CENTER_CROP;	 Catch:{ NoSuchFieldError -> 0x0048 }
            r5 = r5.ordinal();	 Catch:{ NoSuchFieldError -> 0x0048 }
            r4[r5] = r0;	 Catch:{ NoSuchFieldError -> 0x0048 }
        L_0x0048:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0052 }
            r4 = android.widget.ImageView.ScaleType.CENTER_INSIDE;	 Catch:{ NoSuchFieldError -> 0x0052 }
            r4 = r4.ordinal();	 Catch:{ NoSuchFieldError -> 0x0052 }
            r0[r4] = r1;	 Catch:{ NoSuchFieldError -> 0x0052 }
        L_0x0052:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x005c }
            r1 = android.widget.ImageView.ScaleType.FIT_CENTER;	 Catch:{ NoSuchFieldError -> 0x005c }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x005c }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x005c }
        L_0x005c:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0066 }
            r1 = android.widget.ImageView.ScaleType.FIT_START;	 Catch:{ NoSuchFieldError -> 0x0066 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0066 }
            r0[r1] = r3;	 Catch:{ NoSuchFieldError -> 0x0066 }
        L_0x0066:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0071 }
            r1 = android.widget.ImageView.ScaleType.FIT_END;	 Catch:{ NoSuchFieldError -> 0x0071 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0071 }
            r2 = 5;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0071 }
        L_0x0071:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x007c }
            r1 = android.widget.ImageView.ScaleType.FIT_XY;	 Catch:{ NoSuchFieldError -> 0x007c }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x007c }
            r2 = 6;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x007c }
        L_0x007c:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0087 }
            r1 = android.widget.ImageView.ScaleType.CENTER;	 Catch:{ NoSuchFieldError -> 0x0087 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0087 }
            r2 = 7;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0087 }
        L_0x0087:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0093 }
            r1 = android.widget.ImageView.ScaleType.MATRIX;	 Catch:{ NoSuchFieldError -> 0x0093 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0093 }
            r2 = 8;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0093 }
        L_0x0093:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.h$AnonymousClass2.<clinit>():void");
        }
    }

    protected h(e eVar, i iVar, Class<TranscodeType> cls, Context context) {
        this.isDefaultTransitionOptionsSet = true;
        this.glide = eVar;
        this.requestManager = iVar;
        this.transcodeClass = cls;
        this.defaultRequestOptions = iVar.getDefaultRequestOptions();
        this.context = context;
        this.transitionOptions = iVar.getDefaultTransitionOptions(cls);
        this.requestOptions = this.defaultRequestOptions;
        this.glideContext = eVar.f();
    }

    protected h(Class<TranscodeType> cls, h<?> hVar) {
        this(hVar.glide, hVar.requestManager, cls, hVar.context);
        this.model = hVar.model;
        this.isModelSet = hVar.isModelSet;
        this.requestOptions = hVar.requestOptions;
    }

    @CheckResult
    public h<TranscodeType> apply(@NonNull f fVar) {
        com.bumptech.glide.f.h.a((Object) fVar);
        this.requestOptions = getMutableOptions().apply(fVar);
        return this;
    }

    /* Access modifiers changed, original: protected */
    public f getMutableOptions() {
        return this.defaultRequestOptions == this.requestOptions ? this.requestOptions.clone() : this.requestOptions;
    }

    @CheckResult
    public h<TranscodeType> transition(@NonNull j<?, ? super TranscodeType> jVar) {
        this.transitionOptions = (j) com.bumptech.glide.f.h.a((Object) jVar);
        this.isDefaultTransitionOptionsSet = false;
        return this;
    }

    @CheckResult
    public h<TranscodeType> listener(@Nullable e<TranscodeType> eVar) {
        this.requestListener = eVar;
        return this;
    }

    public h<TranscodeType> error(@Nullable h<TranscodeType> hVar) {
        this.errorBuilder = hVar;
        return this;
    }

    @CheckResult
    public h<TranscodeType> thumbnail(@Nullable h<TranscodeType> hVar) {
        this.thumbnailBuilder = hVar;
        return this;
    }

    @CheckResult
    public h<TranscodeType> thumbnail(@Nullable h<TranscodeType>... hVarArr) {
        h hVar = null;
        if (hVarArr == null || hVarArr.length == 0) {
            return thumbnail((h) null);
        }
        for (int length = hVarArr.length - 1; length >= 0; length--) {
            h hVar2 = hVarArr[length];
            if (hVar2 != null) {
                hVar = hVar == null ? hVar2 : hVar2.thumbnail(hVar);
            }
        }
        return thumbnail(hVar);
    }

    @CheckResult
    public h<TranscodeType> thumbnail(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.thumbSizeMultiplier = Float.valueOf(f);
        return this;
    }

    @CheckResult
    public h<TranscodeType> load(@Nullable Object obj) {
        return loadGeneric(obj);
    }

    private h<TranscodeType> loadGeneric(@Nullable Object obj) {
        this.model = obj;
        this.isModelSet = true;
        return this;
    }

    @CheckResult
    public h<TranscodeType> load(@Nullable Bitmap bitmap) {
        return loadGeneric(bitmap).apply(f.diskCacheStrategyOf(g.b));
    }

    @CheckResult
    public h<TranscodeType> load(@Nullable Drawable drawable) {
        return loadGeneric(drawable).apply(f.diskCacheStrategyOf(g.b));
    }

    @CheckResult
    public h<TranscodeType> load(@Nullable String str) {
        return loadGeneric(str);
    }

    @CheckResult
    public h<TranscodeType> load(@Nullable Uri uri) {
        return loadGeneric(uri);
    }

    @CheckResult
    public h<TranscodeType> load(@Nullable File file) {
        return loadGeneric(file);
    }

    @CheckResult
    public h<TranscodeType> load(@Nullable @RawRes @DrawableRes Integer num) {
        return loadGeneric(num).apply(f.signatureOf(a.a(this.context)));
    }

    @Deprecated
    @CheckResult
    public h<TranscodeType> load(@Nullable URL url) {
        return loadGeneric(url);
    }

    @CheckResult
    public h<TranscodeType> load(@Nullable byte[] bArr) {
        return loadGeneric(bArr).apply(f.signatureOf(new c(UUID.randomUUID().toString())).diskCacheStrategy(g.b).skipMemoryCache(true));
    }

    @CheckResult
    public h<TranscodeType> clone() {
        try {
            h hVar = (h) super.clone();
            hVar.requestOptions = hVar.requestOptions.clone();
            hVar.transitionOptions = hVar.transitionOptions.clone();
            return hVar;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public <Y extends i<TranscodeType>> Y into(@NonNull Y y) {
        return into((i) y, null);
    }

    private <Y extends i<TranscodeType>> Y into(@NonNull Y y, @Nullable e<TranscodeType> eVar) {
        return into(y, eVar, getMutableOptions());
    }

    private <Y extends i<TranscodeType>> Y into(@NonNull Y y, @Nullable e<TranscodeType> eVar, f fVar) {
        com.bumptech.glide.f.i.a();
        com.bumptech.glide.f.h.a((Object) y);
        if (this.isModelSet) {
            com.bumptech.glide.request.c buildRequest = buildRequest(y, eVar, fVar.autoClone());
            Object request = y.getRequest();
            if (buildRequest.a(request)) {
                buildRequest.i();
                if (!((com.bumptech.glide.request.c) com.bumptech.glide.f.h.a(request)).d()) {
                    request.a();
                }
                return y;
            }
            this.requestManager.clear((i) y);
            y.setRequest(buildRequest);
            this.requestManager.track(y, buildRequest);
            return y;
        }
        throw new IllegalArgumentException("You must call #load() before calling #into()");
    }

    public i<TranscodeType> into(ImageView imageView) {
        com.bumptech.glide.f.i.a();
        com.bumptech.glide.f.h.a((Object) imageView);
        f fVar = this.requestOptions;
        if (!(fVar.isTransformationSet() || !fVar.isTransformationAllowed() || imageView.getScaleType() == null)) {
            switch (AnonymousClass2.a[imageView.getScaleType().ordinal()]) {
                case 1:
                    fVar = fVar.clone().optionalCenterCrop();
                    break;
                case 2:
                    fVar = fVar.clone().optionalCenterInside();
                    break;
                case 3:
                case 4:
                case 5:
                    fVar = fVar.clone().optionalFitCenter();
                    break;
                case 6:
                    fVar = fVar.clone().optionalCenterInside();
                    break;
            }
        }
        return into(this.glideContext.a(imageView, this.transcodeClass), null, fVar);
    }

    @Deprecated
    public b<TranscodeType> into(int i, int i2) {
        return submit(i, i2);
    }

    public b<TranscodeType> submit() {
        return submit(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public b<TranscodeType> submit(int i, int i2) {
        final i requestFutureTarget = new RequestFutureTarget(this.glideContext.b(), i, i2);
        if (com.bumptech.glide.f.i.d()) {
            this.glideContext.b().post(new Runnable() {
                public void run() {
                    if (!requestFutureTarget.isCancelled()) {
                        h.this.into((i) requestFutureTarget, (e) requestFutureTarget);
                    }
                }
            });
        } else {
            into(requestFutureTarget, (e) requestFutureTarget);
        }
        return requestFutureTarget;
    }

    public i<TranscodeType> preload(int i, int i2) {
        return into(com.bumptech.glide.request.a.f.a(this.requestManager, i, i2));
    }

    public i<TranscodeType> preload() {
        return preload(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Deprecated
    @CheckResult
    public <Y extends i<File>> Y downloadOnly(Y y) {
        return getDownloadOnlyRequest().into((i) y);
    }

    @Deprecated
    @CheckResult
    public b<File> downloadOnly(int i, int i2) {
        return getDownloadOnlyRequest().submit(i, i2);
    }

    /* Access modifiers changed, original: protected */
    @CheckResult
    public h<File> getDownloadOnlyRequest() {
        return new h(File.class, this).apply(DOWNLOAD_ONLY_OPTIONS);
    }

    private Priority getThumbnailPriority(Priority priority) {
        switch (priority) {
            case LOW:
                return Priority.NORMAL;
            case NORMAL:
                return Priority.HIGH;
            case HIGH:
            case IMMEDIATE:
                return Priority.IMMEDIATE;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("unknown priority: ");
                stringBuilder.append(this.requestOptions.getPriority());
                throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private com.bumptech.glide.request.c buildRequest(i<TranscodeType> iVar, @Nullable e<TranscodeType> eVar, f fVar) {
        return buildRequestRecursive(iVar, eVar, null, this.transitionOptions, fVar.getPriority(), fVar.getOverrideWidth(), fVar.getOverrideHeight(), fVar);
    }

    private com.bumptech.glide.request.c buildRequestRecursive(i<TranscodeType> iVar, @Nullable e<TranscodeType> eVar, @Nullable d dVar, j<?, ? super TranscodeType> jVar, Priority priority, int i, int i2, f fVar) {
        d aVar;
        d dVar2;
        if (this.errorBuilder != null) {
            aVar = new com.bumptech.glide.request.a(dVar);
            dVar2 = aVar;
        } else {
            dVar2 = null;
            aVar = dVar;
        }
        com.bumptech.glide.request.c buildThumbnailRequestRecursive = buildThumbnailRequestRecursive(iVar, eVar, aVar, jVar, priority, i, i2, fVar);
        if (dVar2 == null) {
            return buildThumbnailRequestRecursive;
        }
        int overrideWidth = this.errorBuilder.requestOptions.getOverrideWidth();
        int overrideHeight = this.errorBuilder.requestOptions.getOverrideHeight();
        if (com.bumptech.glide.f.i.a(i, i2) && !this.errorBuilder.requestOptions.isValidOverride()) {
            overrideWidth = fVar.getOverrideWidth();
            overrideHeight = fVar.getOverrideHeight();
        }
        int i3 = overrideWidth;
        int i4 = overrideHeight;
        aVar = dVar2;
        aVar.a(buildThumbnailRequestRecursive, this.errorBuilder.buildRequestRecursive(iVar, eVar, dVar2, this.errorBuilder.transitionOptions, this.errorBuilder.requestOptions.getPriority(), i3, i4, this.errorBuilder.requestOptions));
        return aVar;
    }

    private com.bumptech.glide.request.c buildThumbnailRequestRecursive(i<TranscodeType> iVar, e<TranscodeType> eVar, @Nullable d dVar, j<?, ? super TranscodeType> jVar, Priority priority, int i, int i2, f fVar) {
        d dVar2 = dVar;
        Priority priority2 = priority;
        if (this.thumbnailBuilder != null) {
            if (this.isThumbnailBuilt) {
                throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
            }
            j jVar2 = this.thumbnailBuilder.isDefaultTransitionOptionsSet ? jVar : this.thumbnailBuilder.transitionOptions;
            Priority priority3 = this.thumbnailBuilder.requestOptions.isPrioritySet() ? this.thumbnailBuilder.requestOptions.getPriority() : getThumbnailPriority(priority2);
            int overrideWidth = this.thumbnailBuilder.requestOptions.getOverrideWidth();
            int overrideHeight = this.thumbnailBuilder.requestOptions.getOverrideHeight();
            if (com.bumptech.glide.f.i.a(i, i2) && !this.thumbnailBuilder.requestOptions.isValidOverride()) {
                overrideWidth = fVar.getOverrideWidth();
                overrideHeight = fVar.getOverrideHeight();
            }
            int i3 = overrideWidth;
            int i4 = overrideHeight;
            com.bumptech.glide.request.h hVar = new com.bumptech.glide.request.h(dVar2);
            com.bumptech.glide.request.c obtainRequest = obtainRequest(iVar, eVar, fVar, hVar, jVar, priority2, i, i2);
            this.isThumbnailBuilt = true;
            com.bumptech.glide.request.h hVar2 = hVar;
            com.bumptech.glide.request.c buildRequestRecursive = this.thumbnailBuilder.buildRequestRecursive(iVar, eVar, hVar, jVar2, priority3, i3, i4, this.thumbnailBuilder.requestOptions);
            this.isThumbnailBuilt = false;
            hVar2.a(obtainRequest, buildRequestRecursive);
            return hVar2;
        } else if (this.thumbSizeMultiplier == null) {
            return obtainRequest(iVar, eVar, fVar, dVar2, jVar, priority2, i, i2);
        } else {
            com.bumptech.glide.request.h hVar3 = new com.bumptech.glide.request.h(dVar2);
            e<TranscodeType> eVar2 = eVar;
            com.bumptech.glide.request.h hVar4 = hVar3;
            j<?, ? super TranscodeType> jVar3 = jVar;
            int i5 = i;
            int i6 = i2;
            hVar3.a(obtainRequest(iVar, eVar2, fVar, hVar4, jVar3, priority2, i5, i6), obtainRequest(iVar, eVar2, fVar.clone().sizeMultiplier(this.thumbSizeMultiplier.floatValue()), hVar4, jVar3, getThumbnailPriority(priority2), i5, i6));
            return hVar3;
        }
    }

    private com.bumptech.glide.request.c obtainRequest(i<TranscodeType> iVar, e<TranscodeType> eVar, f fVar, d dVar, j<?, ? super TranscodeType> jVar, Priority priority, int i, int i2) {
        return SingleRequest.a(this.context, this.glideContext, this.model, this.transcodeClass, fVar, i, i2, priority, iVar, eVar, this.requestListener, dVar, this.glideContext.c(), jVar.b());
    }
}
