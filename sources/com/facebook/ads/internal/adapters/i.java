package com.facebook.ads.internal.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.a.c;
import com.facebook.ads.internal.s.a.w;
import com.facebook.ads.internal.s.a.y;
import com.facebook.ads.internal.view.a;
import com.facebook.ads.internal.view.g.a.b;
import com.facebook.ads.internal.view.g.b.t;
import com.facebook.ads.internal.view.g.c.d;
import com.facebook.ads.internal.view.g.c.g;
import com.facebook.ads.internal.view.g.c.j;
import com.facebook.ads.internal.view.g.c.k;
import com.facebook.ads.internal.view.g.c.l;
import com.facebook.ads.internal.view.g.c.n;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.exoplayer2.util.MimeTypes;
import com.til.colombia.android.internal.e;
import java.util.HashMap;
import org.json.JSONObject;

public class i extends g implements OnTouchListener, a {
    static final /* synthetic */ boolean i = true;
    private static final String j = "i";
    private int A = -10525069;
    private int B = -12286980;
    private boolean C = false;
    @Nullable
    private com.facebook.ads.internal.view.g.a.a D;
    final int f = 64;
    final int g = 64;
    final int h = 16;
    @Nullable
    private a.a k;
    @Nullable
    private Activity l;
    private BackButtonInterceptor m = new BackButtonInterceptor() {
        public boolean interceptBackButton() {
            if (i.this.y == null) {
                return false;
            }
            if (!i.this.y.a()) {
                return true;
            }
            if (!(i.this.y.getSkipSeconds() == 0 || i.this.b == null)) {
                i.this.b.f();
            }
            if (i.this.b != null) {
                i.this.b.g();
            }
            return false;
        }
    };
    private final OnTouchListener n = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 1) {
                return true;
            }
            if (i.this.y != null) {
                if (!i.this.y.a()) {
                    return true;
                }
                if (!(i.this.y.getSkipSeconds() == 0 || i.this.b == null)) {
                    i.this.b.f();
                }
                if (i.this.b != null) {
                    i.this.b.g();
                }
            }
            i.this.l.finish();
            return true;
        }
    };
    private h.a o = h.a.UNSPECIFIED;
    private final w p = new w();
    private com.facebook.ads.internal.view.d.a q;
    private TextView r;
    private TextView s;
    private ImageView t;
    @Nullable
    private com.facebook.ads.internal.view.g.c.a.a u;
    private n v;
    private ViewGroup w;
    private d x;
    private j y;
    private int z = -1;

    private void a(int i) {
        View view;
        int i2;
        int i3 = i;
        float f = y.b;
        int i4 = (int) (56.0f * f);
        LayoutParams layoutParams = new LayoutParams(i4, i4);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        int i5 = (int) (16.0f * f);
        this.y.setPadding(i5, i5, i5, i5);
        this.y.setLayoutParams(layoutParams);
        d.a aVar = h() ? d.a.FADE_OUT_ON_PLAY : d.a.VISIBLE;
        int id = this.b.getId();
        Drawable gradientDrawable;
        LayoutParams layoutParams2;
        int i6;
        if (i3 == 1 && (k() || l())) {
            gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
            gradientDrawable.setCornerRadius(0.0f);
            LayoutParams layoutParams3 = new LayoutParams(-1, -2);
            layoutParams3.addRule(10);
            this.b.setLayoutParams(layoutParams3);
            a(this.b);
            a(this.y);
            if (this.u != null) {
                a(this.u);
            }
            layoutParams3 = new LayoutParams(-1, (int) (((float) (((((this.q != null ? 64 : 0) + 60) + 16) + 16) + 16)) * f));
            layoutParams3.addRule(12);
            View relativeLayout = new RelativeLayout(this.d);
            y.a(relativeLayout, gradientDrawable);
            relativeLayout.setLayoutParams(layoutParams3);
            relativeLayout.setPadding(i5, 0, i5, (int) (((float) (((this.q != null ? 64 : 0) + 16) + 16)) * f));
            this.w = relativeLayout;
            if (!this.C) {
                this.x.a(relativeLayout, aVar);
            }
            a(relativeLayout);
            if (this.v != null) {
                layoutParams2 = new LayoutParams(-1, (int) (6.0f * f));
                layoutParams2.addRule(12);
                layoutParams2.topMargin = (int) (-6.0f * f);
                this.v.setLayoutParams(layoutParams2);
                a(this.v);
            }
            if (this.q != null) {
                layoutParams2 = new LayoutParams(-1, (int) (64.0f * f));
                layoutParams2.bottomMargin = i5;
                layoutParams2.leftMargin = i5;
                layoutParams2.rightMargin = i5;
                layoutParams2.addRule(1);
                layoutParams2.addRule(12);
                this.q.setLayoutParams(layoutParams2);
                a(this.q);
            }
            if (this.t != null) {
                i6 = (int) (60.0f * f);
                layoutParams2 = new LayoutParams(i6, i6);
                layoutParams2.addRule(12);
                layoutParams2.addRule(9);
                this.t.setLayoutParams(layoutParams2);
                a(relativeLayout, this.t);
            }
            if (this.r != null) {
                layoutParams2 = new LayoutParams(-1, -2);
                layoutParams2.bottomMargin = (int) (36.0f * f);
                layoutParams2.addRule(12);
                layoutParams2.addRule(9);
                this.r.setEllipsize(TruncateAt.END);
                this.r.setGravity(8388611);
                this.r.setLayoutParams(layoutParams2);
                this.r.setMaxLines(1);
                this.r.setPadding((int) (72.0f * f), 0, 0, 0);
                this.r.setTextColor(-1);
                this.r.setTextSize(18.0f);
                a(relativeLayout, this.r);
            }
            if (this.s != null) {
                layoutParams2 = new LayoutParams(-1, -2);
                layoutParams2.addRule(12);
                layoutParams2.addRule(9);
                layoutParams2.bottomMargin = (int) (4.0f * f);
                this.s.setEllipsize(TruncateAt.END);
                this.s.setGravity(8388611);
                this.s.setLayoutParams(layoutParams2);
                this.s.setMaxLines(1);
                this.s.setPadding((int) (72.0f * f), 0, 0, 0);
                this.s.setTextColor(-1);
                a(relativeLayout, this.s);
            }
            view = (View) this.b.getParent();
            i2 = ViewCompat.MEASURED_STATE_MASK;
        } else {
            LinearLayout.LayoutParams layoutParams4;
            if (i3 == 1) {
                layoutParams2 = new LayoutParams(-1, -2);
                layoutParams2.addRule(10);
                this.b.setLayoutParams(layoutParams2);
                a(this.b);
                a(this.y);
                if (this.u != null) {
                    a(this.u);
                }
                view = new LinearLayout(this.d);
                this.w = view;
                view.setGravity(112);
                view.setOrientation(1);
                layoutParams = new LayoutParams(-1, -1);
                i4 = (int) (33.0f * f);
                layoutParams.leftMargin = i4;
                layoutParams.rightMargin = i4;
                layoutParams.topMargin = (int) (8.0f * f);
                if (this.q == null) {
                    layoutParams.bottomMargin = i5;
                } else {
                    layoutParams.bottomMargin = (int) (80.0f * f);
                }
                layoutParams.addRule(3, id);
                view.setLayoutParams(layoutParams);
                a(view);
                if (this.q != null) {
                    layoutParams = new LayoutParams(-1, (int) (64.0f * f));
                    layoutParams.bottomMargin = i5;
                    layoutParams.leftMargin = i4;
                    layoutParams.rightMargin = i4;
                    layoutParams.addRule(1);
                    layoutParams.addRule(12);
                    this.q.setLayoutParams(layoutParams);
                    a(this.q);
                }
                if (this.r != null) {
                    layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams4.weight = 2.0f;
                    layoutParams4.gravity = 17;
                    this.r.setEllipsize(TruncateAt.END);
                    this.r.setGravity(17);
                    this.r.setLayoutParams(layoutParams4);
                    this.r.setMaxLines(2);
                    this.r.setPadding(0, 0, 0, 0);
                    this.r.setTextColor(this.A);
                    this.r.setTextSize(24.0f);
                    a(view, this.r);
                }
                if (this.t != null) {
                    i4 = (int) (64.0f * f);
                    layoutParams4 = new LinearLayout.LayoutParams(i4, i4);
                    layoutParams4.weight = 0.0f;
                    layoutParams4.gravity = 17;
                    this.t.setLayoutParams(layoutParams4);
                    a(view, this.t);
                }
                if (this.s != null) {
                    layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
                    layoutParams4.weight = 2.0f;
                    layoutParams4.gravity = 16;
                    this.s.setEllipsize(TruncateAt.END);
                    this.s.setGravity(16);
                    this.s.setLayoutParams(layoutParams4);
                    this.s.setMaxLines(2);
                    this.s.setPadding(0, 0, 0, 0);
                    this.s.setTextColor(this.A);
                    a(view, this.s);
                }
                if (this.v != null) {
                    layoutParams2 = new LayoutParams(-1, (int) (6.0f * f));
                    layoutParams2.addRule(3, id);
                    this.v.setLayoutParams(layoutParams2);
                    view = this.v;
                }
                view = (View) this.b.getParent();
                i2 = this.z;
            } else if (!m() || l()) {
                gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
                gradientDrawable.setCornerRadius(0.0f);
                this.b.setLayoutParams(new LayoutParams(-1, -1));
                a(this.b);
                a(this.y);
                if (this.u != null) {
                    a(this.u);
                }
                LayoutParams layoutParams5 = new LayoutParams(-1, (int) (124.0f * f));
                layoutParams5.addRule(12);
                View relativeLayout2 = new RelativeLayout(this.d);
                y.a(relativeLayout2, gradientDrawable);
                relativeLayout2.setLayoutParams(layoutParams5);
                relativeLayout2.setPadding(i5, 0, i5, i5);
                this.w = relativeLayout2;
                if (!this.C) {
                    this.x.a(relativeLayout2, aVar);
                }
                a(relativeLayout2);
                if (this.q != null) {
                    layoutParams2 = new LayoutParams((int) (110.0f * f), i4);
                    layoutParams2.rightMargin = i5;
                    layoutParams2.bottomMargin = i5;
                    layoutParams2.addRule(12);
                    layoutParams2.addRule(11);
                    this.q.setLayoutParams(layoutParams2);
                    a(this.q);
                }
                if (this.t != null) {
                    i6 = (int) (64.0f * f);
                    layoutParams2 = new LayoutParams(i6, i6);
                    layoutParams2.addRule(12);
                    layoutParams2.addRule(9);
                    layoutParams2.bottomMargin = (int) (8.0f * f);
                    this.t.setLayoutParams(layoutParams2);
                    a(relativeLayout2, this.t);
                }
                if (this.r != null) {
                    layoutParams2 = new LayoutParams(-1, -2);
                    layoutParams2.bottomMargin = (int) (48.0f * f);
                    layoutParams2.addRule(12);
                    layoutParams2.addRule(9);
                    this.r.setEllipsize(TruncateAt.END);
                    this.r.setGravity(8388611);
                    this.r.setLayoutParams(layoutParams2);
                    this.r.setMaxLines(1);
                    this.r.setPadding((int) (80.0f * f), 0, this.q != null ? (int) (126.0f * f) : 0, 0);
                    this.r.setTextColor(-1);
                    this.r.setTextSize(24.0f);
                    a(relativeLayout2, this.r);
                }
                if (this.s != null) {
                    layoutParams2 = new LayoutParams(-1, -2);
                    layoutParams2.addRule(12);
                    layoutParams2.addRule(9);
                    this.s.setEllipsize(TruncateAt.END);
                    this.s.setGravity(8388611);
                    this.s.setLayoutParams(layoutParams2);
                    this.s.setMaxLines(2);
                    this.s.setTextColor(-1);
                    this.s.setPadding((int) (80.0f * f), 0, this.q != null ? (int) (126.0f * f) : 0, 0);
                    a(relativeLayout2, this.s);
                }
                if (this.v != null) {
                    layoutParams2 = new LayoutParams(-1, (int) (6.0f * f));
                    layoutParams2.addRule(12);
                    this.v.setLayoutParams(layoutParams2);
                    a(this.v);
                }
                view = (View) this.b.getParent();
                i2 = ViewCompat.MEASURED_STATE_MASK;
            } else {
                layoutParams2 = new LayoutParams(-2, -1);
                layoutParams2.addRule(9);
                this.b.setLayoutParams(layoutParams2);
                a(this.b);
                a(this.y);
                if (this.u != null) {
                    a(this.u);
                }
                view = new LinearLayout(this.d);
                this.w = view;
                view.setGravity(112);
                view.setOrientation(1);
                layoutParams = new LayoutParams(-1, -1);
                layoutParams.leftMargin = i5;
                layoutParams.rightMargin = i5;
                layoutParams.topMargin = (int) (8.0f * f);
                layoutParams.bottomMargin = (int) (80.0f * f);
                layoutParams.addRule(1, id);
                view.setLayoutParams(layoutParams);
                a(view);
                if (this.v != null) {
                    layoutParams = new LayoutParams(-1, (int) (6.0f * f));
                    layoutParams.addRule(5, id);
                    layoutParams.addRule(7, id);
                    layoutParams.addRule(3, id);
                    layoutParams.topMargin = (int) (-6.0f * f);
                    this.v.setLayoutParams(layoutParams);
                    a(this.v);
                }
                if (this.r != null) {
                    layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams4.weight = 2.0f;
                    layoutParams4.gravity = 17;
                    this.r.setEllipsize(TruncateAt.END);
                    this.r.setGravity(17);
                    this.r.setLayoutParams(layoutParams4);
                    this.r.setMaxLines(10);
                    this.r.setPadding(0, 0, 0, 0);
                    this.r.setTextColor(this.A);
                    this.r.setTextSize(24.0f);
                    a(view, this.r);
                }
                if (this.t != null) {
                    i4 = (int) (64.0f * f);
                    layoutParams4 = new LinearLayout.LayoutParams(i4, i4);
                    layoutParams4.weight = 0.0f;
                    layoutParams4.gravity = 17;
                    this.t.setLayoutParams(layoutParams4);
                    a(view, this.t);
                }
                if (this.s != null) {
                    layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
                    layoutParams4.weight = 2.0f;
                    layoutParams4.gravity = 16;
                    this.s.setEllipsize(TruncateAt.END);
                    this.s.setGravity(17);
                    this.s.setLayoutParams(layoutParams4);
                    this.s.setMaxLines(10);
                    this.s.setPadding(0, 0, 0, 0);
                    this.s.setTextColor(this.A);
                    a(view, this.s);
                }
                if (this.q != null) {
                    layoutParams2 = new LayoutParams(-1, (int) (f * 64.0f));
                    layoutParams2.bottomMargin = i5;
                    layoutParams2.leftMargin = i5;
                    layoutParams2.rightMargin = i5;
                    layoutParams2.addRule(1);
                    layoutParams2.addRule(12);
                    layoutParams2.addRule(1, id);
                    this.q.setLayoutParams(layoutParams2);
                    view = this.q;
                }
                view = (View) this.b.getParent();
                i2 = this.z;
            }
            a(view);
            view = (View) this.b.getParent();
            i2 = this.z;
        }
        y.a(view, i2);
        view = this.b.getRootView();
        if (view != null) {
            view.setOnTouchListener(this);
        }
    }

    private void a(View view) {
        if (this.k != null) {
            this.k.a(view);
        }
    }

    private void a(@Nullable ViewGroup viewGroup, @Nullable View view) {
        if (viewGroup != null) {
            viewGroup.addView(view);
        }
    }

    private void b(View view) {
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }

    private boolean k() {
        return ((double) (this.b.getVideoHeight() > 0 ? ((float) this.b.getVideoWidth()) / ((float) this.b.getVideoHeight()) : -1.0f)) <= 0.9d;
    }

    private boolean l() {
        boolean z = false;
        if (this.b.getVideoHeight() <= 0) {
            return false;
        }
        Rect rect = new Rect();
        this.l.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        if (rect.width() > rect.height()) {
            if (((float) (rect.width() - ((rect.height() * this.b.getVideoWidth()) / this.b.getVideoHeight()))) - (192.0f * y.b) < 0.0f) {
                z = true;
            }
            return z;
        }
        if (((((float) (rect.height() - ((rect.width() * this.b.getVideoHeight()) / this.b.getVideoWidth()))) - (y.b * 64.0f)) - (64.0f * y.b)) - (40.0f * y.b) < 0.0f) {
            z = true;
        }
        return z;
    }

    private boolean m() {
        double videoWidth = (double) (this.b.getVideoHeight() > 0 ? ((float) this.b.getVideoWidth()) / ((float) this.b.getVideoHeight()) : -1.0f);
        return videoWidth > 0.9d && videoWidth < 1.1d;
    }

    private void n() {
        b(this.b);
        b(this.q);
        b(this.r);
        b(this.s);
        b(this.t);
        b(this.v);
        b(this.w);
        b(this.y);
        if (this.u != null) {
            b(this.u);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        if (this.c == null) {
            Log.e(j, "Unable to add UI without a valid ad response.");
            return;
        }
        JSONObject jSONObject;
        JSONObject jSONObject2;
        String string = this.c.getString(e.P);
        String optString = this.c.getJSONObject("context").optString("orientation");
        if (!optString.isEmpty()) {
            this.o = h.a.a(Integer.parseInt(optString));
        }
        if (this.c.has(TtmlNode.TAG_LAYOUT) && !this.c.isNull(TtmlNode.TAG_LAYOUT)) {
            jSONObject = this.c.getJSONObject(TtmlNode.TAG_LAYOUT);
            this.z = (int) jSONObject.optLong("bgColor", (long) this.z);
            this.A = (int) jSONObject.optLong("textColor", (long) this.A);
            this.B = (int) jSONObject.optLong("accentColor", (long) this.B);
            this.C = jSONObject.optBoolean("persistentAdDetails", this.C);
        }
        jSONObject = this.c.getJSONObject(MimeTypes.BASE_TYPE_TEXT);
        this.b.setId(VERSION.SDK_INT >= 17 ? View.generateViewId() : y.a());
        int c = c();
        Context context = this.d;
        if (c < 0) {
            c = 0;
        }
        this.y = new j(context, c, this.B);
        this.y.setOnTouchListener(this.n);
        this.b.a(this.y);
        if (this.c.has(InMobiNetworkValues.CTA) && !this.c.isNull(InMobiNetworkValues.CTA)) {
            JSONObject jSONObject3 = this.c.getJSONObject(InMobiNetworkValues.CTA);
            this.q = new com.facebook.ads.internal.view.d.a(this.d, this.p, jSONObject3.getString("url"), jSONObject3.getString(MimeTypes.BASE_TYPE_TEXT), this.B, this.b, this.a, string);
            c.a(this.d, this.a, string, Uri.parse(jSONObject3.getString("url")), new HashMap());
        }
        if (this.c.has(InMobiNetworkValues.ICON) && !this.c.isNull(InMobiNetworkValues.ICON)) {
            jSONObject2 = this.c.getJSONObject(InMobiNetworkValues.ICON);
            this.t = new ImageView(this.d);
            new com.facebook.ads.internal.view.c.d(this.t).a((int) (y.b * 64.0f), (int) (64.0f * y.b)).a(jSONObject2.getString("url"));
        }
        if (this.c.has(TtmlNode.TAG_IMAGE) && !this.c.isNull(TtmlNode.TAG_IMAGE)) {
            jSONObject2 = this.c.getJSONObject(TtmlNode.TAG_IMAGE);
            b gVar = new g(this.d);
            this.b.a(gVar);
            gVar.setImage(jSONObject2.getString("url"));
        }
        String optString2 = jSONObject.optString("title");
        if (!optString2.isEmpty()) {
            this.r = new TextView(this.d);
            this.r.setText(optString2);
            this.r.setTypeface(Typeface.defaultFromStyle(1));
        }
        optString = jSONObject.optString(FacebookAdapter.KEY_SUBTITLE_ASSET);
        if (!optString.isEmpty()) {
            this.s = new TextView(this.d);
            this.s.setText(optString);
            this.s.setTextSize(16.0f);
        }
        this.v = new n(this.d);
        this.b.a(this.v);
        String d = d();
        if (!TextUtils.isEmpty(d)) {
            this.u = new com.facebook.ads.internal.view.g.c.a.a(this.d, "AdChoices", d, new float[]{0.0f, 0.0f, 8.0f, 0.0f}, string);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            this.u.setLayoutParams(layoutParams);
        }
        this.b.a(new k(this.d));
        b lVar = new l(this.d);
        this.b.a(lVar);
        d.a aVar = h() ? d.a.FADE_OUT_ON_PLAY : d.a.VISIBLE;
        this.b.a(new d(lVar, aVar));
        this.x = new d(new RelativeLayout(this.d), aVar);
        this.b.a(this.x);
    }

    @TargetApi(17)
    public void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        this.l = audienceNetworkActivity;
        if (i || this.k != null) {
            audienceNetworkActivity.addBackButtonInterceptor(this.m);
            n();
            a(this.l.getResources().getConfiguration().orientation);
            if (h()) {
                e();
                return;
            } else {
                f();
                return;
            }
        }
        throw new AssertionError();
    }

    public void a(Configuration configuration) {
        n();
        a(configuration.orientation);
    }

    public void a(Bundle bundle) {
    }

    public void a(boolean z) {
        if (this.b != null && this.b.getState() == com.facebook.ads.internal.view.g.d.d.STARTED) {
            this.D = this.b.getVideoStartReason();
            this.b.a(false);
        }
    }

    public void b(boolean z) {
        if (this.b != null && this.D != null) {
            this.b.a(this.D);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean h() {
        if (i || this.c != null) {
            try {
                return this.c.getJSONObject("video").getBoolean("autoplay");
            } catch (Exception e) {
                Log.w(String.valueOf(i.class), "Invalid JSON", e);
                return true;
            }
        }
        throw new AssertionError();
    }

    public h.a i() {
        return this.o;
    }

    public void j() {
        if (this.l != null) {
            this.l.finish();
        }
    }

    public void onDestroy() {
        if (!(this.c == null || this.a == null)) {
            String optString = this.c.optString(e.P);
            if (!TextUtils.isEmpty(optString)) {
                this.a.i(optString, new HashMap());
            }
        }
        if (this.b != null) {
            this.b.g();
        }
        h.a((a) this);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.p.a(motionEvent, view.getRootView(), view);
        if (this.b != null) {
            this.b.getEventBus().a(new t(view, motionEvent));
        }
        return true;
    }

    public void setListener(a.a aVar) {
        this.k = aVar;
    }
}
