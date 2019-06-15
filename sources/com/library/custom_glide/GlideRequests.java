package com.library.custom_glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.e;
import com.bumptech.glide.i;
import com.bumptech.glide.load.resource.d.c;
import com.bumptech.glide.manager.h;
import com.bumptech.glide.manager.l;
import com.bumptech.glide.request.f;
import java.io.File;

public class GlideRequests extends i {
    public GlideRequests(e eVar, h hVar, l lVar, Context context) {
        super(eVar, hVar, lVar, context);
    }

    @CheckResult
    public <ResourceType> GlideRequest<ResourceType> as(Class<ResourceType> cls) {
        return new GlideRequest(this.glide, this, cls, this.context);
    }

    public GlideRequests applyDefaultRequestOptions(f fVar) {
        return (GlideRequests) super.applyDefaultRequestOptions(fVar);
    }

    public GlideRequests setDefaultRequestOptions(f fVar) {
        return (GlideRequests) super.setDefaultRequestOptions(fVar);
    }

    @CheckResult
    public GlideRequest<Bitmap> asBitmap() {
        return (GlideRequest) super.asBitmap();
    }

    @CheckResult
    public GlideRequest<c> asGif() {
        return (GlideRequest) super.asGif();
    }

    @CheckResult
    public GlideRequest<Drawable> asDrawable() {
        return (GlideRequest) super.asDrawable();
    }

    @CheckResult
    public GlideRequest<Drawable> load(@Nullable Object obj) {
        return (GlideRequest) super.load(obj);
    }

    @CheckResult
    public GlideRequest<File> downloadOnly() {
        return (GlideRequest) super.downloadOnly();
    }

    @CheckResult
    public GlideRequest<File> download(@Nullable Object obj) {
        return (GlideRequest) super.download(obj);
    }

    @CheckResult
    public GlideRequest<File> asFile() {
        return (GlideRequest) super.asFile();
    }

    /* Access modifiers changed, original: protected */
    public void setRequestOptions(@NonNull f fVar) {
        if (fVar instanceof GlideOptions) {
            super.setRequestOptions(fVar);
        } else {
            super.setRequestOptions(new GlideOptions().apply(fVar));
        }
    }
}
