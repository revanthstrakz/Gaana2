package com.facebook.ads.internal.view.a;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.c.c;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.s.b.b;

public class f extends LinearLayout {
    private static final int a = ((int) (40.0f * y.b));
    private static final int b = ((int) (20.0f * y.b));
    private static final int c = ((int) (10.0f * y.b));
    private final c d;
    private final c e;

    f(Context context, c cVar, c cVar2, String str, b bVar) {
        super(context);
        this.d = cVar;
        this.e = cVar2;
        setOrientation(1);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        View a = a(str);
        a.setPadding(0, 0, 0, 0);
        View view = new View(getContext());
        view.setLayoutParams(new LayoutParams(-1, 1));
        y.a(view, -10459280);
        View a2 = a();
        a2.setPadding(0, c, 0, 0);
        addView(a, layoutParams);
        addView(view);
        if (!TextUtils.isEmpty(this.d.c())) {
            View a3 = a(bVar, this.d.c());
            a3.setPadding(0, c, 0, c);
            addView(a3, layoutParams);
        }
        addView(a2, layoutParams);
    }

    private View a() {
        g gVar = new g(getContext());
        for (final c cVar : this.d.d()) {
            final TextView dVar = new d(getContext());
            dVar.setText(cVar.b());
            y.a(dVar, false, 14);
            dVar.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    dVar.a();
                    f.this.e.b(cVar);
                }
            });
            gVar.addView(dVar);
        }
        return gVar;
    }

    private View a(b bVar, String str) {
        ImageView imageView = new ImageView(getContext());
        imageView.setColorFilter(-10459280);
        LayoutParams layoutParams = new LayoutParams(b, b);
        layoutParams.gravity = 16;
        imageView.setImageBitmap(com.facebook.ads.internal.s.b.c.a(bVar));
        TextView textView = new TextView(getContext());
        y.a(textView, true, 14);
        textView.setTextColor(-10459280);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        textView.setText(str);
        textView.setPadding(c, 0, 0, 0);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.addView(imageView, layoutParams);
        linearLayout.addView(textView, layoutParams2);
        return linearLayout;
    }

    private View a(String str) {
        ImageView imageView = new ImageView(getContext());
        imageView.setColorFilter(-10459280);
        imageView.setImageBitmap(com.facebook.ads.internal.s.b.c.a(b.BACK_ARROW));
        imageView.setPadding(0, c, c * 2, c);
        LayoutParams layoutParams = new LayoutParams(a, a);
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                f.this.e.a(f.this.d);
            }
        });
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setText(str);
        y.a(textView, true, 16);
        textView.setTextColor(-14934495);
        LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.setMargins(0, 0, a, 0);
        layoutParams2.gravity = 17;
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.addView(imageView, layoutParams);
        linearLayout.addView(textView, layoutParams2);
        return linearLayout;
    }
}
