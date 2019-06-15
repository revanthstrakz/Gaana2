package com.bumptech.glide;

import android.content.Context;
import android.util.Log;
import com.library.custom_glide.GaanaGlide;
import java.util.Collections;
import java.util.Set;

final class b extends a {
    private final GaanaGlide a = new GaanaGlide();

    b() {
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Discovered AppGlideModule from annotation: com.library.custom_glide.GaanaGlide");
        }
    }

    public void applyOptions(Context context, f fVar) {
        this.a.applyOptions(context, fVar);
    }

    public void registerComponents(Context context, e eVar, Registry registry) {
        this.a.registerComponents(context, eVar, registry);
    }

    public boolean isManifestParsingEnabled() {
        return this.a.isManifestParsingEnabled();
    }

    public Set<Class<?>> a() {
        return Collections.emptySet();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: c */
    public c b() {
        return new c();
    }
}
