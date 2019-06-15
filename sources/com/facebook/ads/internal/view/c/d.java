package com.facebook.ads.internal.view.c;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.f.c;
import com.facebook.ads.internal.l.a;
import com.facebook.ads.internal.l.b;
import com.facebook.ads.internal.s.a.p;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.s.b.f;
import java.lang.ref.WeakReference;

public class d extends AsyncTask<String, Void, Bitmap[]> {
    private static final String b = "d";
    public boolean a = false;
    private final WeakReference<Context> c;
    private final int d;
    @Nullable
    private final WeakReference<ImageView> e;
    @Nullable
    private final WeakReference<b> f;
    @Nullable
    private final WeakReference<ViewGroup> g;
    private e h;
    private int i = -1;
    private int j = -1;

    public d(ViewGroup viewGroup, int i) {
        this.c = new WeakReference(viewGroup.getContext());
        this.f = null;
        this.e = null;
        this.g = new WeakReference(viewGroup);
        this.d = i;
    }

    public d(ImageView imageView) {
        this.c = new WeakReference(imageView.getContext());
        this.f = null;
        this.e = new WeakReference(imageView);
        this.g = null;
        this.d = 0;
    }

    public d(b bVar) {
        this.c = new WeakReference(bVar.getContext());
        this.f = new WeakReference(bVar);
        this.e = null;
        this.g = null;
        this.d = 0;
    }

    public d a() {
        this.i = -1;
        this.j = -1;
        return this;
    }

    public d a(int i, int i2) {
        this.i = i;
        this.j = i2;
        return this;
    }

    public d a(e eVar) {
        this.h = eVar;
        return this;
    }

    public d a(boolean z) {
        this.a = z;
        return this;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            if (this.h != null) {
                this.h.a(false);
            }
            return;
        }
        executeOnExecutor(p.a, new String[]{str});
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void onPostExecute(Bitmap[] bitmapArr) {
        boolean z = false;
        if (this.e != null) {
            ImageView imageView = (ImageView) this.e.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmapArr[0]);
            }
        }
        if (this.f != null) {
            b bVar = (b) this.f.get();
            if (bVar != null) {
                bVar.a(bitmapArr[0], bitmapArr[1]);
            }
        }
        if (!(this.g == null || this.g.get() == null || bitmapArr[1] == null)) {
            y.a((View) this.g.get(), new BitmapDrawable(((Context) this.c.get()).getResources(), bitmapArr[1]));
        }
        if (this.h != null) {
            e eVar = this.h;
            if (bitmapArr[0] != null) {
                z = true;
            }
            eVar.a(z);
        }
    }

    /* Access modifiers changed, original: protected|varargs */
    /* renamed from: a */
    public Bitmap[] doInBackground(String... strArr) {
        Throwable th;
        String str;
        StringBuilder stringBuilder;
        String str2 = strArr[0];
        Context context = (Context) this.c.get();
        String str3 = null;
        if (context == null) {
            return new Bitmap[]{null, null};
        }
        Bitmap a;
        try {
            a = c.a(context).a(str2, this.i, this.j);
            try {
                int i = (this.f == null || this.f.get() == null) ? 0 : 1;
                int i2 = (this.g == null || this.g.get() == null) ? 0 : 1;
                if (!((i == 0 && i2 == 0) || a == null || this.a)) {
                    f fVar = new f(a);
                    fVar.a(this.d != 0 ? this.d : Math.round(((float) a.getWidth()) / 40.0f));
                    str3 = fVar.a();
                }
            } catch (Throwable th2) {
                th = th2;
                str = b;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Error downloading image: ");
                stringBuilder.append(str2);
                Log.e(str, stringBuilder.toString(), th);
                b.a(a.a(th, null));
                return new Bitmap[]{a, str3};
            }
        } catch (Throwable th3) {
            th = th3;
            a = null;
            str = b;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Error downloading image: ");
            stringBuilder.append(str2);
            Log.e(str, stringBuilder.toString(), th);
            b.a(a.a(th, null));
            return new Bitmap[]{a, str3};
        }
        return new Bitmap[]{a, str3};
    }
}
