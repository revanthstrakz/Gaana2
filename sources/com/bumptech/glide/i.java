package com.bumptech.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.bumptech.glide.load.engine.g;
import com.bumptech.glide.load.resource.d.c;
import com.bumptech.glide.manager.h;
import com.bumptech.glide.manager.l;
import com.bumptech.glide.manager.m;
import com.bumptech.glide.manager.n;
import com.bumptech.glide.request.a.j;
import com.bumptech.glide.request.b.d;
import com.bumptech.glide.request.f;
import java.io.File;

public class i implements com.bumptech.glide.manager.i {
    private static final f DECODE_TYPE_BITMAP = f.decodeTypeOf(Bitmap.class).lock();
    private static final f DECODE_TYPE_GIF = f.decodeTypeOf(c.class).lock();
    private static final f DOWNLOAD_ONLY_OPTIONS = f.diskCacheStrategyOf(g.c).priority(Priority.LOW).skipMemoryCache(true);
    private final Runnable addSelfToLifecycle;
    private final com.bumptech.glide.manager.c connectivityMonitor;
    protected final Context context;
    protected final e glide;
    final h lifecycle;
    private final Handler mainHandler;
    @NonNull
    private f requestOptions;
    private final m requestTracker;
    private final n targetTracker;
    private final l treeNode;

    private static class a extends j<View, Object> {
        public void onResourceReady(Object obj, d<? super Object> dVar) {
        }

        public a(View view) {
            super(view);
        }
    }

    private static class b implements com.bumptech.glide.manager.c.a {
        private final m a;

        public b(m mVar) {
            this.a = mVar;
        }

        public void a(boolean z) {
            if (z) {
                this.a.e();
            }
        }
    }

    public i(e eVar, h hVar, l lVar, Context context) {
        this(eVar, hVar, lVar, new m(), eVar.e(), context);
    }

    i(e eVar, h hVar, l lVar, m mVar, com.bumptech.glide.manager.d dVar, Context context) {
        this.targetTracker = new n();
        this.addSelfToLifecycle = new Runnable() {
            public void run() {
                i.this.lifecycle.a(i.this);
            }
        };
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.glide = eVar;
        this.lifecycle = hVar;
        this.treeNode = lVar;
        this.requestTracker = mVar;
        this.context = context;
        this.connectivityMonitor = dVar.a(context.getApplicationContext(), new b(mVar));
        if (com.bumptech.glide.f.i.d()) {
            this.mainHandler.post(this.addSelfToLifecycle);
        } else {
            hVar.a(this);
        }
        hVar.a(this.connectivityMonitor);
        setRequestOptions(eVar.f().a());
        eVar.a(this);
    }

    /* Access modifiers changed, original: protected */
    public void setRequestOptions(@NonNull f fVar) {
        this.requestOptions = fVar.clone().autoClone();
    }

    private void updateRequestOptions(f fVar) {
        this.requestOptions = this.requestOptions.apply(fVar);
    }

    public i applyDefaultRequestOptions(f fVar) {
        updateRequestOptions(fVar);
        return this;
    }

    public i setDefaultRequestOptions(f fVar) {
        setRequestOptions(fVar);
        return this;
    }

    @Deprecated
    public void onTrimMemory(int i) {
        this.glide.onTrimMemory(i);
    }

    @Deprecated
    public void onLowMemory() {
        this.glide.onLowMemory();
    }

    public boolean isPaused() {
        com.bumptech.glide.f.i.a();
        return this.requestTracker.a();
    }

    public void pauseRequests() {
        com.bumptech.glide.f.i.a();
        this.requestTracker.b();
    }

    public void pauseRequestsRecursive() {
        com.bumptech.glide.f.i.a();
        pauseRequests();
        for (i pauseRequests : this.treeNode.a()) {
            pauseRequests.pauseRequests();
        }
    }

    public void resumeRequests() {
        com.bumptech.glide.f.i.a();
        this.requestTracker.c();
    }

    public void resumeRequestsRecursive() {
        com.bumptech.glide.f.i.a();
        resumeRequests();
        for (i resumeRequests : this.treeNode.a()) {
            resumeRequests.resumeRequests();
        }
    }

    public void onStart() {
        resumeRequests();
        this.targetTracker.onStart();
    }

    public void onStop() {
        pauseRequests();
        this.targetTracker.onStop();
    }

    public void onDestroy() {
        this.targetTracker.onDestroy();
        for (com.bumptech.glide.request.a.i clear : this.targetTracker.a()) {
            clear(clear);
        }
        this.targetTracker.b();
        this.requestTracker.d();
        this.lifecycle.b(this);
        this.lifecycle.b(this.connectivityMonitor);
        this.mainHandler.removeCallbacks(this.addSelfToLifecycle);
        this.glide.b(this);
    }

    @CheckResult
    public h<Bitmap> asBitmap() {
        return as(Bitmap.class).apply(DECODE_TYPE_BITMAP);
    }

    @CheckResult
    public h<c> asGif() {
        return as(c.class).apply(DECODE_TYPE_GIF);
    }

    @CheckResult
    public h<Drawable> asDrawable() {
        return as(Drawable.class);
    }

    @CheckResult
    public h<Drawable> load(@Nullable Object obj) {
        return asDrawable().load(obj);
    }

    @CheckResult
    public h<File> downloadOnly() {
        return as(File.class).apply(DOWNLOAD_ONLY_OPTIONS);
    }

    @CheckResult
    public h<File> download(@Nullable Object obj) {
        return downloadOnly().load(obj);
    }

    @CheckResult
    public h<File> asFile() {
        return as(File.class).apply(f.skipMemoryCacheOf(true));
    }

    @CheckResult
    public <ResourceType> h<ResourceType> as(Class<ResourceType> cls) {
        return new h(this.glide, this, cls, this.context);
    }

    public void clear(View view) {
        clear(new a(view));
    }

    public void clear(@Nullable final com.bumptech.glide.request.a.i<?> iVar) {
        if (iVar != null) {
            if (com.bumptech.glide.f.i.c()) {
                untrackOrDelegate(iVar);
            } else {
                this.mainHandler.post(new Runnable() {
                    public void run() {
                        i.this.clear(iVar);
                    }
                });
            }
        }
    }

    private void untrackOrDelegate(com.bumptech.glide.request.a.i<?> iVar) {
        if (!untrack(iVar)) {
            this.glide.a((com.bumptech.glide.request.a.i) iVar);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean untrack(com.bumptech.glide.request.a.i<?> iVar) {
        com.bumptech.glide.request.c request = iVar.getRequest();
        if (request == null) {
            return true;
        }
        if (!this.requestTracker.b(request)) {
            return false;
        }
        this.targetTracker.b(iVar);
        iVar.setRequest(null);
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public void track(com.bumptech.glide.request.a.i<?> iVar, com.bumptech.glide.request.c cVar) {
        this.targetTracker.a(iVar);
        this.requestTracker.a(cVar);
    }

    /* Access modifiers changed, original: 0000 */
    public f getDefaultRequestOptions() {
        return this.requestOptions;
    }

    /* Access modifiers changed, original: 0000 */
    @NonNull
    public <T> j<?, T> getDefaultTransitionOptions(Class<T> cls) {
        return this.glide.f().a(cls);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("{tracker=");
        stringBuilder.append(this.requestTracker);
        stringBuilder.append(", treeNode=");
        stringBuilder.append(this.treeNode);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
