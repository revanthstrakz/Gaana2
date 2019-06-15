package com.bumptech.glide;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.c.c;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.a.i;
import com.bumptech.glide.load.b.o;
import com.bumptech.glide.load.b.t;
import com.bumptech.glide.load.b.w;
import com.bumptech.glide.load.b.x;
import com.bumptech.glide.load.engine.a.b;
import com.bumptech.glide.load.engine.d.a;
import com.bumptech.glide.load.engine.h;
import com.bumptech.glide.load.g;
import com.bumptech.glide.load.resource.bitmap.j;
import com.bumptech.glide.load.resource.bitmap.q;
import com.bumptech.glide.load.resource.bitmap.s;
import com.bumptech.glide.load.resource.bitmap.u;
import com.bumptech.glide.load.resource.bitmap.v;
import com.bumptech.glide.manager.d;
import com.bumptech.glide.manager.k;
import com.bumptech.glide.request.f;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TargetApi(14)
public class e implements ComponentCallbacks2 {
    private static volatile e a;
    private static volatile boolean b;
    private final h c;
    private final com.bumptech.glide.load.engine.a.e d;
    private final com.bumptech.glide.load.engine.b.h e;
    private final a f;
    private final g g;
    private final Registry h;
    private final b i;
    private final k j;
    private final d k;
    private final List<i> l = new ArrayList();
    private MemoryCategory m = MemoryCategory.NORMAL;

    public void onConfigurationChanged(Configuration configuration) {
    }

    @Nullable
    public static File a(Context context) {
        return a(context, "image_manager_disk_cache");
    }

    @Nullable
    public static File a(Context context, String str) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File file = new File(cacheDir, str);
            if (file.mkdirs() || (file.exists() && file.isDirectory())) {
                return file;
            }
            return null;
        }
        if (Log.isLoggable("Glide", 6)) {
            Log.e("Glide", "default disk cache dir is null");
        }
        return null;
    }

    public static e b(Context context) {
        if (a == null) {
            synchronized (e.class) {
                if (a == null) {
                    d(context);
                }
            }
        }
        return a;
    }

    private static void d(Context context) {
        if (b) {
            throw new IllegalStateException("You cannot call Glide.get() in registerComponents(), use the provided Glide instance instead");
        }
        b = true;
        e(context);
        b = false;
    }

    @VisibleForTesting
    public static synchronized void a(e eVar) {
        synchronized (e.class) {
            a = eVar;
        }
    }

    @VisibleForTesting
    public static synchronized void a() {
        synchronized (e.class) {
            a = null;
        }
    }

    private static void e(Context context) {
        Context applicationContext = context.getApplicationContext();
        a j = j();
        List emptyList = Collections.emptyList();
        if (j == null || j.isManifestParsingEnabled()) {
            emptyList = new com.bumptech.glide.c.e(applicationContext).a();
        }
        if (!(j == null || j.a().isEmpty())) {
            Set a = j.a();
            Iterator it = emptyList.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (a.contains(cVar.getClass())) {
                    if (Log.isLoggable("Glide", 3)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("AppGlideModule excludes manifest GlideModule: ");
                        stringBuilder.append(cVar);
                        Log.d("Glide", stringBuilder.toString());
                    }
                    it.remove();
                }
            }
        }
        if (Log.isLoggable("Glide", 3)) {
            for (c cVar2 : emptyList) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Discovered GlideModule from manifest: ");
                stringBuilder2.append(cVar2.getClass());
                Log.d("Glide", stringBuilder2.toString());
            }
        }
        f a2 = new f().a(j != null ? j.b() : null);
        for (c applyOptions : emptyList) {
            applyOptions.applyOptions(applicationContext, a2);
        }
        if (j != null) {
            j.applyOptions(applicationContext, a2);
        }
        e a3 = a2.a(applicationContext);
        for (c cVar22 : emptyList) {
            cVar22.registerComponents(applicationContext, a3, a3.h);
        }
        if (j != null) {
            j.registerComponents(applicationContext, a3, a3.h);
        }
        context.getApplicationContext().registerComponentCallbacks(a3);
        a = a3;
    }

    @Nullable
    private static a j() {
        try {
            return (a) Class.forName("com.bumptech.glide.b").newInstance();
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable("Glide", 5)) {
                Log.w("Glide", "Failed to find GeneratedAppGlideModule. You should include an annotationProcessor compile dependency on com.github.bumptech.glide:compiler in your application and a @GlideModule annotated AppGlideModule implementation or LibraryGlideModules will be silently ignored");
            }
            return null;
        } catch (InstantiationException e) {
            throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e);
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("GeneratedAppGlideModuleImpl is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", e2);
        }
    }

    @TargetApi(14)
    e(Context context, h hVar, com.bumptech.glide.load.engine.b.h hVar2, com.bumptech.glide.load.engine.a.e eVar, b bVar, k kVar, d dVar, int i, f fVar, Map<Class<?>, j<?, ?>> map) {
        Context context2 = context;
        com.bumptech.glide.load.engine.b.h hVar3 = hVar2;
        com.bumptech.glide.load.engine.a.e eVar2 = eVar;
        b bVar2 = bVar;
        this.c = hVar;
        this.d = eVar2;
        this.i = bVar2;
        this.e = hVar3;
        this.j = kVar;
        this.k = dVar;
        this.f = new a(hVar3, eVar2, (DecodeFormat) fVar.getOptions().a(com.bumptech.glide.load.resource.bitmap.k.a));
        Resources resources = context.getResources();
        this.h = new Registry();
        this.h.a(new j());
        com.bumptech.glide.load.resource.bitmap.k kVar2 = new com.bumptech.glide.load.resource.bitmap.k(this.h.a(), resources.getDisplayMetrics(), eVar2, bVar2);
        com.bumptech.glide.load.resource.d.a aVar = new com.bumptech.glide.load.resource.d.a(context2, this.h.a(), eVar2, bVar2);
        v vVar = new v(eVar2);
        com.bumptech.glide.load.resource.bitmap.f fVar2 = new com.bumptech.glide.load.resource.bitmap.f(kVar2);
        s sVar = new s(kVar2, bVar2);
        g eVar3 = new com.bumptech.glide.load.resource.b.e(context2);
        o bVar3 = new com.bumptech.glide.load.b.s.b(resources);
        com.bumptech.glide.load.b.s.c cVar = new com.bumptech.glide.load.b.s.c(resources);
        com.bumptech.glide.load.b.s.a aVar2 = new com.bumptech.glide.load.b.s.a(resources);
        com.bumptech.glide.load.h cVar2 = new com.bumptech.glide.load.resource.bitmap.c();
        o oVar = aVar2;
        oVar = cVar;
        Context context3 = context;
        this.h.a(ByteBuffer.class, new com.bumptech.glide.load.b.c()).a(InputStream.class, new t(bVar2)).a("Bitmap", ByteBuffer.class, Bitmap.class, fVar2).a("Bitmap", InputStream.class, Bitmap.class, sVar).a("Bitmap", ParcelFileDescriptor.class, Bitmap.class, vVar).a("Bitmap", Bitmap.class, Bitmap.class, new u()).a(Bitmap.class, Bitmap.class, com.bumptech.glide.load.b.v.a.a()).a(Bitmap.class, cVar2).a("BitmapDrawable", ByteBuffer.class, BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.a(resources, eVar2, fVar2)).a("BitmapDrawable", InputStream.class, BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.a(resources, eVar2, sVar)).a("BitmapDrawable", ParcelFileDescriptor.class, BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.a(resources, eVar2, vVar)).a(BitmapDrawable.class, new com.bumptech.glide.load.resource.bitmap.b(eVar2, cVar2)).a("Gif", InputStream.class, com.bumptech.glide.load.resource.d.c.class, new com.bumptech.glide.load.resource.d.j(this.h.a(), aVar, bVar2)).a("Gif", ByteBuffer.class, com.bumptech.glide.load.resource.d.c.class, aVar).a(com.bumptech.glide.load.resource.d.c.class, new com.bumptech.glide.load.resource.d.d()).a(com.bumptech.glide.b.a.class, com.bumptech.glide.b.a.class, com.bumptech.glide.load.b.v.a.a()).a("Bitmap", com.bumptech.glide.b.a.class, Bitmap.class, new com.bumptech.glide.load.resource.d.h(eVar2)).a(Uri.class, Drawable.class, eVar3).a(Uri.class, Bitmap.class, new q(eVar3, eVar2)).a(new com.bumptech.glide.load.resource.a.a.a()).a(File.class, ByteBuffer.class, new com.bumptech.glide.load.b.d.b()).a(File.class, InputStream.class, new com.bumptech.glide.load.b.f.e()).a(File.class, File.class, new com.bumptech.glide.load.resource.c.a()).a(File.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.f.b()).a(File.class, File.class, com.bumptech.glide.load.b.v.a.a()).a(new i.a(bVar2)).a(Integer.TYPE, InputStream.class, bVar3).a(Integer.TYPE, ParcelFileDescriptor.class, oVar).a(Integer.class, InputStream.class, bVar3).a(Integer.class, ParcelFileDescriptor.class, oVar).a(Integer.class, Uri.class, oVar).a(Integer.TYPE, Uri.class, oVar).a(String.class, InputStream.class, new com.bumptech.glide.load.b.e.c()).a(String.class, InputStream.class, new com.bumptech.glide.load.b.u.b()).a(String.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.u.a()).a(Uri.class, InputStream.class, new com.bumptech.glide.load.b.a.b.a()).a(Uri.class, InputStream.class, new com.bumptech.glide.load.b.a.c(context.getAssets())).a(Uri.class, ParcelFileDescriptor.class, new com.bumptech.glide.load.b.a.b(context.getAssets())).a(Uri.class, InputStream.class, new com.bumptech.glide.load.b.a.c.a(context3)).a(Uri.class, InputStream.class, new com.bumptech.glide.load.b.a.d.a(context3)).a(Uri.class, InputStream.class, new w.c(context.getContentResolver())).a(Uri.class, ParcelFileDescriptor.class, new w.a(context.getContentResolver())).a(Uri.class, InputStream.class, new x.a()).a(URL.class, InputStream.class, new com.bumptech.glide.load.b.a.e.a()).a(Uri.class, File.class, new com.bumptech.glide.load.b.k.a(context3)).a(com.bumptech.glide.load.b.g.class, InputStream.class, new com.bumptech.glide.load.b.a.a.a()).a(byte[].class, ByteBuffer.class, new com.bumptech.glide.load.b.b.a()).a(byte[].class, InputStream.class, new com.bumptech.glide.load.b.b.d()).a(Uri.class, Uri.class, com.bumptech.glide.load.b.v.a.a()).a(Drawable.class, Drawable.class, com.bumptech.glide.load.b.v.a.a()).a(Drawable.class, Drawable.class, new com.bumptech.glide.load.resource.b.f()).a(Bitmap.class, BitmapDrawable.class, new com.bumptech.glide.load.resource.e.b(resources, eVar2)).a(Bitmap.class, byte[].class, new com.bumptech.glide.load.resource.e.a()).a(com.bumptech.glide.load.resource.d.c.class, byte[].class, new com.bumptech.glide.load.resource.e.c());
        context2 = context3;
        this.g = new g(context2, this.h, new com.bumptech.glide.request.a.e(), fVar, map, hVar, i);
    }

    public com.bumptech.glide.load.engine.a.e b() {
        return this.d;
    }

    public b c() {
        return this.i;
    }

    public Context d() {
        return this.g.getBaseContext();
    }

    /* Access modifiers changed, original: 0000 */
    public d e() {
        return this.k;
    }

    /* Access modifiers changed, original: 0000 */
    public g f() {
        return this.g;
    }

    public void g() {
        com.bumptech.glide.f.i.a();
        this.e.a();
        this.d.a();
        this.i.a();
    }

    public void a(int i) {
        com.bumptech.glide.f.i.a();
        this.e.a(i);
        this.d.a(i);
        this.i.a(i);
    }

    public k h() {
        return this.j;
    }

    private static k f(@Nullable Context context) {
        com.bumptech.glide.f.h.a((Object) context, "You cannot start a load on a not yet attached View or a  Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
        return b(context).h();
    }

    public static i c(Context context) {
        return f(context).a(context);
    }

    public static i a(Activity activity) {
        return f(activity).a(activity);
    }

    public static i a(FragmentActivity fragmentActivity) {
        return f(fragmentActivity).a(fragmentActivity);
    }

    public static i a(Fragment fragment) {
        return f(fragment.getActivity()).a(fragment);
    }

    public static i a(android.support.v4.app.Fragment fragment) {
        return f(fragment.getActivity()).a(fragment);
    }

    public static i a(View view) {
        return f(view.getContext()).a(view);
    }

    public Registry i() {
        return this.h;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(com.bumptech.glide.request.a.i<?> iVar) {
        synchronized (this.l) {
            for (i untrack : this.l) {
                if (untrack.untrack(iVar)) {
                    return;
                }
            }
            throw new IllegalStateException("Failed to remove target from managers");
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(i iVar) {
        synchronized (this.l) {
            if (this.l.contains(iVar)) {
                throw new IllegalStateException("Cannot register already registered manager");
            }
            this.l.add(iVar);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void b(i iVar) {
        synchronized (this.l) {
            if (this.l.contains(iVar)) {
                this.l.remove(iVar);
            } else {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
        }
    }

    public void onTrimMemory(int i) {
        a(i);
    }

    public void onLowMemory() {
        g();
    }
}
