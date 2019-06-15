package com.facebook.ads.internal.view.d;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.a.b;
import com.facebook.ads.internal.o.c;
import com.facebook.ads.internal.s.a.k;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.s.a.y;
import java.util.HashMap;

public class a extends RelativeLayout {
    private final w a;
    private final String b;
    private com.facebook.ads.internal.view.g.a c;
    private final Paint d = new Paint();
    private final RectF e;

    public a(Context context, w wVar, String str, String str2, int i, com.facebook.ads.internal.view.g.a aVar, final c cVar, final String str3) {
        super(context);
        this.a = wVar;
        this.b = str;
        this.c = aVar;
        TextView textView = new TextView(context);
        textView.setTextColor(-1);
        textView.setTextSize(16.0f);
        textView.setText(str2);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        setGravity(17);
        addView(textView);
        this.d.setStyle(Style.FILL);
        this.d.setColor(i);
        this.e = new RectF();
        y.a((View) this, 0);
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Throwable e;
                String valueOf;
                String stringBuilder;
                try {
                    Uri parse = Uri.parse(a.this.b);
                    a.this.c.getEventBus().a(new com.facebook.ads.internal.view.g.b.a(parse));
                    HashMap hashMap = new HashMap();
                    hashMap.put("touch", k.a(a.this.a.e()));
                    b a = com.facebook.ads.internal.a.c.a(a.this.getContext(), cVar, str3, parse, hashMap);
                    if (a != null) {
                        a.b();
                    }
                } catch (ActivityNotFoundException e2) {
                    e = e2;
                    valueOf = String.valueOf(a.class);
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Error while opening ");
                    stringBuilder2.append(a.this.b);
                    stringBuilder = stringBuilder2.toString();
                    Log.e(valueOf, stringBuilder, e);
                } catch (Exception e3) {
                    e = e3;
                    valueOf = String.valueOf(a.class);
                    stringBuilder = "Error executing action";
                    Log.e(valueOf, stringBuilder, e);
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.e.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        float f2 = 10.0f * f;
        canvas.drawRoundRect(this.e, f2, f2, this.d);
        super.onDraw(canvas);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.a.a(motionEvent, getRootView(), this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
