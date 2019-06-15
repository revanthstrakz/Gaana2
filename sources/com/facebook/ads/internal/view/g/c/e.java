package com.facebook.ads.internal.view.g.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.g.a.c;
import com.facebook.ads.internal.view.g.b.a;
import java.util.HashMap;

public class e extends c {
    private final String a;
    private final TextView b = new TextView(getContext());
    private final com.facebook.ads.internal.o.c c;
    private final String d;
    private final Paint e;
    private final RectF f;

    public e(Context context, String str, com.facebook.ads.internal.o.c cVar, String str2, String str3) {
        super(context);
        this.a = str;
        this.c = cVar;
        this.d = str2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.b.setTextColor(-3355444);
        this.b.setTextSize(16.0f);
        this.b.setPadding((int) (displayMetrics.density * 6.0f), (int) (displayMetrics.density * 4.0f), (int) (6.0f * displayMetrics.density), (int) (4.0f * displayMetrics.density));
        this.e = new Paint();
        this.e.setStyle(Style.FILL);
        this.e.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.e.setAlpha(178);
        this.f = new RectF();
        y.a((View) this, 0);
        this.b.setText(str3);
        addView(this.b, new LayoutParams(-2, -2));
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        super.a();
        this.b.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (e.this.getVideoView() != null) {
                    Uri parse = Uri.parse(e.this.a);
                    e.this.getVideoView().getEventBus().a(new a(parse));
                    b a = com.facebook.ads.internal.a.c.a(e.this.getContext(), e.this.c, e.this.d, parse, new HashMap());
                    if (a != null) {
                        a.b();
                    }
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        this.b.setOnClickListener(null);
        super.b();
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        this.f.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        canvas.drawRoundRect(this.f, 0.0f, 0.0f, this.e);
        super.onDraw(canvas);
    }
}
