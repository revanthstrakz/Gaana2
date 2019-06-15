package com.bumptech.glide.request.b;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import com.bumptech.glide.request.b.d.a;

public class b implements d<Drawable> {
    private final int a;
    private final boolean b;

    public b(int i, boolean z) {
        this.a = i;
        this.b = z;
    }

    public boolean a(Drawable drawable, a aVar) {
        Drawable a = aVar.a();
        if (a == null) {
            a = new ColorDrawable(0);
        }
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{a, drawable});
        transitionDrawable.setCrossFadeEnabled(this.b);
        transitionDrawable.startTransition(this.a);
        aVar.b(transitionDrawable);
        return true;
    }
}
