package com.facebook.ads.internal.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import com.facebook.ads.internal.p.f;
import com.facebook.ads.internal.s.a.j;

public class l extends f {
    private s a;

    public l(Context context) {
        super(context);
        this.a = new s(context);
        this.a.setScaleType(ScaleType.CENTER_CROP);
        j.a(this.a, j.INTERNAL_AD_MEDIA);
        addView(this.a, new LayoutParams(-1, -1));
    }

    public View getAdContentsView() {
        return this.a;
    }
}
