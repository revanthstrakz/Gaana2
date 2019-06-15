package com.til.colombia.android.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import com.til.colombia.android.R;
import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;
import com.til.colombia.android.commons.CommonUtil;
import com.til.colombia.android.commons.USER_ACTION;
import com.til.colombia.android.commons.a.g;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.internal.e;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.internal.views.CloseableLayout;
import com.til.colombia.android.network.l;
import com.til.colombia.android.network.n;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.vast.VastCompanionAdConfig;
import com.til.colombia.android.vast.VastCompanionResource.CreativeType;
import com.til.colombia.android.vast.VastCompanionResource.Type;
import com.til.colombia.android.vast.VastSponsoredAdConfig;
import com.til.colombia.android.vast.VastTrackingEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InterstitialActivity extends Activity {
    private static String d = InterstitialActivity.class.getCanonicalName();
    private b A = new b(this, (byte) 0);
    int a;
    float b;
    float c;
    private Context e;
    private VastSponsoredAdConfig f;
    private VastCompanionAdConfig g;
    private NativeItem h;
    private ItemResponse i;
    private boolean j;
    private Context k;
    private CloseableLayout l;
    private View m;
    private VideoView n;
    private ProgressDialog o;
    private Button p;
    private TextView q;
    private Button r;
    private int s;
    private final float t = 8.0f;
    private MediaPlayer u = null;
    private COLOMBIA_PLAYER_STATE v = COLOMBIA_PLAYER_STATE.NULL;
    private a w;
    private com.til.colombia.android.internal.a.a x;
    private AudioManager y;
    private ScheduledExecutorService z;

    class a extends Handler {
        a() {
        }

        public final void handleMessage(Message message) {
            InterstitialActivity.this.a(USER_ACTION.AUTO_CLOSED);
        }
    }

    private class b extends Handler {
        private b() {
        }

        /* synthetic */ b(InterstitialActivity interstitialActivity, byte b) {
            this();
        }

        public final void handleMessage(Message message) {
            try {
                InterstitialActivity.l(InterstitialActivity.this);
            } catch (Exception unused) {
                InterstitialActivity.this.z.shutdownNow();
            }
        }
    }

    private class c implements OnTouchListener {
        private c() {
        }

        /* synthetic */ c(InterstitialActivity interstitialActivity, byte b) {
            this();
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    return false;
                case 1:
                    try {
                        if (InterstitialActivity.this.g.getClickTrackers() != null) {
                            for (VastTrackingEvent url : InterstitialActivity.this.g.getClickTrackers()) {
                                l.a(url.getUrl(), 5, null);
                            }
                        }
                    } catch (Exception e) {
                        Log.a(i.f, "", e);
                    } catch (Throwable th) {
                        InterstitialActivity.this.a(USER_ACTION.AUTO_CLOSED);
                    }
                    InterstitialActivity.this.a(USER_ACTION.AUTO_CLOSED);
                    break;
                case 2:
                    return true;
            }
            return false;
        }
    }

    private static void o() {
    }

    private void a(COLOMBIA_PLAYER_STATE colombia_player_state) {
        this.v = colombia_player_state;
    }

    private COLOMBIA_PLAYER_STATE b() {
        return this.v;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        this.e = this;
        setRequestedOrientation(1);
        setContentView(R.layout.companion_layout);
        this.l = (CloseableLayout) findViewById(R.id.interstitial_layout);
        this.l.setKeepScreenOn(true);
        this.k = this;
        this.w = new a();
        this.h = (NativeItem) getIntent().getSerializableExtra(e.ab);
        this.i = (ItemResponse) getIntent().getSerializableExtra(e.ac);
        this.j = getIntent().getBooleanExtra("skipPreCompanion", false);
        this.f = this.h.getVastHelper().getSponsoredAdConfig();
        this.g = this.h.getVastHelper().getBestCompanionAdConfig();
        if (this.j) {
            if (this.h.getItemType() == ITEM_TYPE.INTERSTITIAL_VIDEO) {
                a(ck.a, null);
            } else {
                a(ck.b, null);
            }
            j();
        } else if (!(this.f.getPreAudioCompanion() == null && this.f.getPreAudioCompanion().getCompanionResource() == null)) {
            if (this.f.getPreAudioCompanion().getCompanionResource().getType() == Type.STATIC_RESOURCE && this.f.getPreAudioCompanion().getCompanionResource().getCreativeType() == CreativeType.IMAGE) {
                this.m = new ImageView(this.e);
            } else {
                this.m = new com.til.colombia.android.vast.i(this.e);
            }
            h();
            this.f.getPreAudioCompanion().initialize(this.m);
            String audioSrc = this.f.getPreAudioCompanion().getAudioSrc();
            if (h.a(audioSrc)) {
                a((long) com.til.colombia.android.internal.a.p());
            } else {
                a(audioSrc);
            }
            a(this.m, com.til.colombia.android.vast.h.a, false);
            a(ck.a, null);
            bi.a().a(this.i, this.h, this.m);
        }
        this.x = new bn(this);
        this.y = (AudioManager) getSystemService("audio");
        try {
            this.y.requestAudioFocus(null, 3, 1);
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }

    private void c() {
        this.x = new bn(this);
        this.y = (AudioManager) getSystemService("audio");
        try {
            this.y.requestAudioFocus(null, 3, 1);
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }

    private void a(String str) {
        try {
            this.u = new MediaPlayer();
            this.v = COLOMBIA_PLAYER_STATE.IDLE;
            MediaPlayer mediaPlayer = this.u;
            try {
                mediaPlayer.setAudioStreamType(3);
                mediaPlayer.setOnPreparedListener(new br(this, mediaPlayer));
                mediaPlayer.setOnCompletionListener(new bs(this));
                mediaPlayer.setOnErrorListener(new bt(this));
            } catch (Exception e) {
                Log.a(i.f, "", e);
            }
            this.u.setDataSource(this.k, g.a(str));
            this.v = COLOMBIA_PLAYER_STATE.INITIALIZED;
            this.u.prepareAsync();
            this.v = COLOMBIA_PLAYER_STATE.PREPARING;
        } catch (IOException e2) {
            Log.a(i.f, "", e2);
        }
    }

    private void a(MediaPlayer mediaPlayer) {
        try {
            mediaPlayer.setAudioStreamType(3);
            mediaPlayer.setOnPreparedListener(new br(this, mediaPlayer));
            mediaPlayer.setOnCompletionListener(new bs(this));
            mediaPlayer.setOnErrorListener(new bt(this));
        } catch (Exception e) {
            Log.a(i.f, "", e);
        }
    }

    /* JADX WARNING: Failed to extract finally block: empty outs */
    private void d() {
        /*
        r3 = this;
        r0 = 0;
        r1 = r3.u;	 Catch:{ Exception -> 0x004d }
        if (r1 == 0) goto L_0x002e;
    L_0x0005:
        r1 = r3.v;	 Catch:{ Exception -> 0x004d }
        r2 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.NULL;	 Catch:{ Exception -> 0x004d }
        if (r1 == r2) goto L_0x002e;
    L_0x000b:
        r1 = r3.v;	 Catch:{ Exception -> 0x004d }
        r2 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.STARTED;	 Catch:{ Exception -> 0x004d }
        if (r1 == r2) goto L_0x001d;
    L_0x0011:
        r1 = r3.v;	 Catch:{ Exception -> 0x004d }
        r2 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.COMPLETED;	 Catch:{ Exception -> 0x004d }
        if (r1 == r2) goto L_0x001d;
    L_0x0017:
        r1 = r3.v;	 Catch:{ Exception -> 0x004d }
        r2 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.PREPARED;	 Catch:{ Exception -> 0x004d }
        if (r1 != r2) goto L_0x002e;
    L_0x001d:
        r1 = r3.u;	 Catch:{ Exception -> 0x004d }
        r1 = r1.isPlaying();	 Catch:{ Exception -> 0x004d }
        if (r1 == 0) goto L_0x002e;
    L_0x0025:
        r1 = r3.u;	 Catch:{ Exception -> 0x004d }
        r1.stop();	 Catch:{ Exception -> 0x004d }
        r1 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.STOPPED;	 Catch:{ Exception -> 0x004d }
        r3.v = r1;	 Catch:{ Exception -> 0x004d }
    L_0x002e:
        r1 = r3.u;
        if (r1 == 0) goto L_0x006e;
    L_0x0032:
        r1 = r3.u;
        r1.reset();
        r1 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.IDLE;
        r3.v = r1;
        r1 = r3.u;
        r1.release();
        r1 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.END;
        r3.v = r1;
        r3.u = r0;
        r0 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.NULL;
        r3.v = r0;
        return;
    L_0x004b:
        r1 = move-exception;
        goto L_0x006f;
    L_0x004d:
        r1 = move-exception;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r1);	 Catch:{ all -> 0x004b }
        r1 = r3.u;
        if (r1 == 0) goto L_0x006e;
    L_0x0055:
        r1 = r3.u;
        r1.reset();
        r1 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.IDLE;
        r3.v = r1;
        r1 = r3.u;
        r1.release();
        r1 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.END;
        r3.v = r1;
        r3.u = r0;
        r0 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.NULL;
        r3.v = r0;
        return;
    L_0x006e:
        return;
    L_0x006f:
        r2 = r3.u;
        if (r2 == 0) goto L_0x008b;
    L_0x0073:
        r2 = r3.u;
        r2.reset();
        r2 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.IDLE;
        r3.v = r2;
        r2 = r3.u;
        r2.release();
        r2 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.END;
        r3.v = r2;
        r3.u = r0;
        r0 = com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE.NULL;
        r3.v = r0;
    L_0x008b:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.til.colombia.android.service.InterstitialActivity.d():void");
    }

    private void e() {
        if (this.f.getPreAudioCompanion() != null || this.f.getPreAudioCompanion().getCompanionResource() != null) {
            if (this.f.getPreAudioCompanion().getCompanionResource().getType() == Type.STATIC_RESOURCE && this.f.getPreAudioCompanion().getCompanionResource().getCreativeType() == CreativeType.IMAGE) {
                this.m = new ImageView(this.e);
            } else {
                this.m = new com.til.colombia.android.vast.i(this.e);
            }
            h();
            this.f.getPreAudioCompanion().initialize(this.m);
            String audioSrc = this.f.getPreAudioCompanion().getAudioSrc();
            if (h.a(audioSrc)) {
                a((long) com.til.colombia.android.internal.a.p());
            } else {
                a(audioSrc);
            }
            a(this.m, com.til.colombia.android.vast.h.a, false);
            a(ck.a, null);
            bi.a().a(this.i, this.h, this.m);
        }
    }

    private void f() {
        boolean z;
        if (this.z != null) {
            this.z.shutdownNow();
            this.z = null;
        }
        this.l.removeView(this.q);
        this.l.removeView(this.p);
        this.l.removeView(this.n);
        if (this.r != null) {
            this.l.removeView(this.r);
        }
        this.q = null;
        this.p = null;
        this.n = null;
        this.r = null;
        int i = 1;
        setRequestedOrientation(1);
        this.l.a(true);
        if (this.f == null || this.f.getPostAudioCompanion() == null || this.f.getPostAudioCompanion().getCompanionResource() == null) {
            if (this.g == null || this.g.getVastResource() == null) {
                i = 0;
            } else {
                if (this.g.getVastResource().getType() == Type.STATIC_RESOURCE && this.g.getVastResource().getCreativeType() == CreativeType.IMAGE) {
                    this.m = new ImageView(this.e);
                } else {
                    this.m = new com.til.colombia.android.vast.i(this.e);
                }
                h();
                this.g.getVastResource().initializeVastResourceView(this.m);
                List<VastTrackingEvent> creativeViewTrackers = this.g.getCreativeViewTrackers();
                if (creativeViewTrackers != null) {
                    for (VastTrackingEvent url : creativeViewTrackers) {
                        l.a(url.getUrl(), 5, null);
                    }
                }
            }
            z = i;
        } else {
            if (this.f.getPostAudioCompanion().getCompanionResource().getType() == Type.STATIC_RESOURCE && this.f.getPostAudioCompanion().getCompanionResource().getCreativeType() == CreativeType.IMAGE) {
                this.m = new ImageView(this.e);
            } else {
                this.m = new com.til.colombia.android.vast.i(this.e);
            }
            h();
            this.f.getPostAudioCompanion().initialize(this.m);
            z = false;
        }
        if (i != 0) {
            if (this.m instanceof WebView) {
                ((com.til.colombia.android.vast.i) this.m).setOnTouchListener(new c(this, (byte) 0));
            } else {
                a(this.m, com.til.colombia.android.vast.h.b, z);
            }
            if (this.f == null || this.f.getPostAudioCompanion() == null || h.a(this.f.getPostAudioCompanion().getAudioSrc())) {
                a((long) com.til.colombia.android.internal.a.p());
                return;
            } else {
                a(this.f.getPostAudioCompanion().getAudioSrc());
                return;
            }
        }
        a(USER_ACTION.INVALID_CONFIG);
    }

    private void g() {
        boolean z;
        boolean z2 = true;
        if (this.f != null && this.f.getPostAudioCompanion() != null && this.f.getPostAudioCompanion().getCompanionResource() != null) {
            if (this.f.getPostAudioCompanion().getCompanionResource().getType() == Type.STATIC_RESOURCE && this.f.getPostAudioCompanion().getCompanionResource().getCreativeType() == CreativeType.IMAGE) {
                this.m = new ImageView(this.e);
            } else {
                this.m = new com.til.colombia.android.vast.i(this.e);
            }
            h();
            this.f.getPostAudioCompanion().initialize(this.m);
            z = false;
        } else if (this.g == null || this.g.getVastResource() == null) {
            z = false;
            z2 = z;
        } else {
            if (this.g.getVastResource().getType() == Type.STATIC_RESOURCE && this.g.getVastResource().getCreativeType() == CreativeType.IMAGE) {
                this.m = new ImageView(this.e);
            } else {
                this.m = new com.til.colombia.android.vast.i(this.e);
            }
            h();
            this.g.getVastResource().initializeVastResourceView(this.m);
            List<VastTrackingEvent> creativeViewTrackers = this.g.getCreativeViewTrackers();
            if (creativeViewTrackers != null) {
                for (VastTrackingEvent url : creativeViewTrackers) {
                    l.a(url.getUrl(), 5, null);
                }
            }
            z = true;
        }
        if (z2) {
            if (this.m instanceof WebView) {
                ((com.til.colombia.android.vast.i) this.m).setOnTouchListener(new c(this, (byte) 0));
            } else {
                a(this.m, com.til.colombia.android.vast.h.b, z);
            }
            if (this.f == null || this.f.getPostAudioCompanion() == null || h.a(this.f.getPostAudioCompanion().getAudioSrc())) {
                a((long) com.til.colombia.android.internal.a.p());
                return;
            } else {
                a(this.f.getPostAudioCompanion().getAudioSrc());
                return;
            }
        }
        a(USER_ACTION.INVALID_CONFIG);
    }

    private void a(View view, String str, boolean z) {
        view.setOnTouchListener(null);
        view.setOnTouchListener(new bu(this, str, z));
    }

    private void h() {
        CommonUtil.a(this.m);
        this.l.addView(this.m);
        this.l.d = new bv(this);
        if (this.m != null && (this.m instanceof com.til.colombia.android.vast.i)) {
            WebSettings settings = ((com.til.colombia.android.vast.i) this.m).getSettings();
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            if (VERSION.SDK_INT >= 11) {
                settings.setDisplayZoomControls(false);
            }
            settings.setDefaultTextEncodingName("UTF-8");
            settings.setJavaScriptEnabled(true);
            settings.setCacheMode(2);
            if (VERSION.SDK_INT >= 16) {
                settings.setAllowUniversalAccessFromFileURLs(true);
            }
        }
    }

    private void i() {
        if (this.m != null && (this.m instanceof com.til.colombia.android.vast.i)) {
            WebSettings settings = ((com.til.colombia.android.vast.i) this.m).getSettings();
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            if (VERSION.SDK_INT >= 11) {
                settings.setDisplayZoomControls(false);
            }
            settings.setDefaultTextEncodingName("UTF-8");
            settings.setJavaScriptEnabled(true);
            settings.setCacheMode(2);
            if (VERSION.SDK_INT >= 16) {
                settings.setAllowUniversalAccessFromFileURLs(true);
            }
        }
    }

    private void j() {
        this.l.removeView(this.m);
        setRequestedOrientation(-1);
        this.l.a(false);
        this.n = new VideoView(this.k);
        this.l.addView(this.n);
        Uri b = g.b(this.h.getVastHelper().getMediaFileUrl());
        this.o = new ProgressDialog(this);
        this.o.setMessage("Loading...");
        this.o.setCancelable(false);
        this.o.show();
        CommonUtil.a(this.n);
        this.n.getHolder().setSizeFromLayout();
        this.n.setVideoURI(b);
        this.n.requestFocus();
        this.n.setOnPreparedListener(new bw(this));
        this.n.setOnCompletionListener(new bx(this));
        this.n.setOnErrorListener(new by(this));
        this.q = new TextView(this.k);
        this.q.setTypeface(Typeface.SERIF);
        this.q.setTextSize(12.0f);
        this.q.setBackgroundColor(getResources().getColor(R.color.transparent_color));
        this.q.setTextColor(getResources().getColor(R.color.color_white));
        this.q.setClickable(false);
        this.q.setVisibility(4);
        this.l.addView(this.q, new LayoutParams(-2, -2));
        this.p = new Button(this.k);
        this.p.setText(R.string.skip_ad);
        this.p.setTypeface(Typeface.SERIF);
        this.p.setTextSize(15.0f);
        this.p.setBackgroundResource(R.drawable.transparent_bg);
        this.p.setTextColor(Color.parseColor("#BBBBBB"));
        this.p.setVisibility(4);
        this.p.setOnClickListener(new bo(this));
        this.l.addView(this.p, new LayoutParams(-2, -2));
        if (this.h.getItemType() == ITEM_TYPE.INTERSTITIAL_VIDEO) {
            this.h.recordImpression(null);
        }
    }

    private void k() {
        Uri b = g.b(this.h.getVastHelper().getMediaFileUrl());
        this.o = new ProgressDialog(this);
        this.o.setMessage("Loading...");
        this.o.setCancelable(false);
        this.o.show();
        CommonUtil.a(this.n);
        this.n.getHolder().setSizeFromLayout();
        this.n.setVideoURI(b);
        this.n.requestFocus();
        this.n.setOnPreparedListener(new bw(this));
        this.n.setOnCompletionListener(new bx(this));
        this.n.setOnErrorListener(new by(this));
        this.q = new TextView(this.k);
        this.q.setTypeface(Typeface.SERIF);
        this.q.setTextSize(12.0f);
        this.q.setBackgroundColor(getResources().getColor(R.color.transparent_color));
        this.q.setTextColor(getResources().getColor(R.color.color_white));
        this.q.setClickable(false);
        this.q.setVisibility(4);
        this.l.addView(this.q, new LayoutParams(-2, -2));
        this.p = new Button(this.k);
        this.p.setText(R.string.skip_ad);
        this.p.setTypeface(Typeface.SERIF);
        this.p.setTextSize(15.0f);
        this.p.setBackgroundResource(R.drawable.transparent_bg);
        this.p.setTextColor(Color.parseColor("#BBBBBB"));
        this.p.setVisibility(4);
        this.p.setOnClickListener(new bo(this));
        this.l.addView(this.p, new LayoutParams(-2, -2));
    }

    private void l() {
        this.n.setOnPreparedListener(new bw(this));
        this.n.setOnCompletionListener(new bx(this));
        this.n.setOnErrorListener(new by(this));
    }

    private void m() {
        this.p = new Button(this.k);
        this.p.setText(R.string.skip_ad);
        this.p.setTypeface(Typeface.SERIF);
        this.p.setTextSize(15.0f);
        this.p.setBackgroundResource(R.drawable.transparent_bg);
        this.p.setTextColor(Color.parseColor("#BBBBBB"));
        this.p.setVisibility(4);
        this.p.setOnClickListener(new bo(this));
        this.l.addView(this.p, new LayoutParams(-2, -2));
    }

    private void n() {
        this.q = new TextView(this.k);
        this.q.setTypeface(Typeface.SERIF);
        this.q.setTextSize(12.0f);
        this.q.setBackgroundColor(getResources().getColor(R.color.transparent_color));
        this.q.setTextColor(getResources().getColor(R.color.color_white));
        this.q.setClickable(false);
        this.q.setVisibility(4);
        this.l.addView(this.q, new LayoutParams(-2, -2));
    }

    private void p() {
        if (this.r == null && this.h.getItemType() == ITEM_TYPE.INTERSTITIAL_VIDEO) {
            this.r = new Button(this.k);
            this.r.setTypeface(Typeface.SERIF);
            this.r.setTextSize(18.0f);
            this.r.setBackgroundResource(R.drawable.interstitial_ctabutton);
            this.r.setTextColor(getResources().getColor(R.color.color_white));
            this.r.setGravity(17);
            this.r.setText(this.h.getCtaText());
            this.r.setVisibility(0);
            this.r.setOnClickListener(new bp(this));
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.gravity = 81;
            layoutParams.bottomMargin = (int) this.k.getResources().getDimension(R.dimen.skip_btn_bottom);
            this.l.addView(this.r, layoutParams);
        }
    }

    private void q() {
        this.z.scheduleWithFixedDelay(new bq(this), 1000, 1000, TimeUnit.MILLISECONDS);
    }

    private void r() {
        if (this.n != null && this.n.isPlaying()) {
            int duration = this.n.getDuration() / 1000;
            int currentPosition = this.n.getCurrentPosition() / 1000;
            if (this.q != null) {
                String a = CommonUtil.a(duration - currentPosition);
                TextView textView = this.q;
                StringBuilder stringBuilder = new StringBuilder("Ad - ");
                stringBuilder.append(a);
                textView.setText(stringBuilder.toString());
                t();
            }
            if (currentPosition == this.h.getVastHelper().getStartNotifyTime()) {
                n.a(this.h.getVastHelper().getVastTrackingByType(2), 5, "start video tracked.");
                if (this.r == null && this.h.getItemType() == ITEM_TYPE.INTERSTITIAL_VIDEO) {
                    this.r = new Button(this.k);
                    this.r.setTypeface(Typeface.SERIF);
                    this.r.setTextSize(18.0f);
                    this.r.setBackgroundResource(R.drawable.interstitial_ctabutton);
                    this.r.setTextColor(getResources().getColor(R.color.color_white));
                    this.r.setGravity(17);
                    this.r.setText(this.h.getCtaText());
                    this.r.setVisibility(0);
                    this.r.setOnClickListener(new bp(this));
                    LayoutParams layoutParams = new LayoutParams(-2, -2);
                    layoutParams.gravity = 81;
                    layoutParams.bottomMargin = (int) this.k.getResources().getDimension(R.dimen.skip_btn_bottom);
                    this.l.addView(this.r, layoutParams);
                }
            }
            if (currentPosition == this.h.getVastHelper().getSkipOffset(this.n.getDuration())) {
                if (this.p != null) {
                    this.s = this.p.getWidth();
                }
                a(ck.f, null);
            }
            n.a(this.h.getVastHelper().getVastProgressEvent(currentPosition), 5, "video progress tracked.");
            if (currentPosition >= this.h.getVastHelper().getSkipOffset(this.n.getDuration())) {
                s();
            }
            int i = currentPosition * 4;
            if (duration >= i && duration < (currentPosition + 1) * 4) {
                n.a(this.h.getVastHelper().getVastTrackingByType(3), 5, "video Q1 tracked.");
            } else if (duration < currentPosition * 2 || duration >= (currentPosition + 1) * 2) {
                duration *= 3;
                if (duration >= i && duration < (currentPosition + 1) * 4) {
                    n.a(this.h.getVastHelper().getVastTrackingByType(5), 5, "video Q3 tracked.");
                }
            } else {
                n.a(this.h.getVastHelper().getVastTrackingByType(4), 5, "video Q-mid tracked.");
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.m != null) {
            CommonUtil.a(this.m);
        }
        if (this.n != null) {
            this.q.setVisibility(4);
            CommonUtil.a(this.n);
            this.n.getHolder().setSizeFromLayout();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        if (this.n != null) {
            this.n.pause();
            this.a = this.n.getCurrentPosition();
        }
        if (this.u != null && this.u.isPlaying() && this.v == COLOMBIA_PLAYER_STATE.STARTED) {
            this.u.pause();
            this.v = COLOMBIA_PLAYER_STATE.PAUSED;
        }
        this.x.b(this);
        super.onPause();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        this.x.a(this);
        if (this.n != null) {
            if (!(this.n.isPlaying() || this.o == null)) {
                this.o.show();
            }
            this.n.resume();
        }
        if (!(this.u == null || this.u.isPlaying() || this.v != COLOMBIA_PLAYER_STATE.PAUSED)) {
            this.u.start();
            this.v = COLOMBIA_PLAYER_STATE.STARTED;
        }
        super.onResume();
    }

    public void onWindowFocusChanged(boolean z) {
        if (z) {
            if (!(this.n == null || this.n == null)) {
                this.n.start();
            }
            if (!(this.u == null || this.u.isPlaying() || this.v != COLOMBIA_PLAYER_STATE.PAUSED)) {
                this.u.start();
                this.v = COLOMBIA_PLAYER_STATE.STARTED;
            }
        } else {
            if (this.n != null) {
                this.n.pause();
            }
            if (this.u != null && this.u.isPlaying() && this.v == COLOMBIA_PLAYER_STATE.STARTED) {
                this.u.pause();
                this.v = COLOMBIA_PLAYER_STATE.PAUSED;
            }
        }
        super.onWindowFocusChanged(z);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("Position", this.a);
        if (this.n != null) {
            this.n.pause();
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.a = bundle.getInt("Position");
        if (this.n != null) {
            this.n.seekTo(this.a);
        }
    }

    private synchronized void s() {
        if (this.p != null) {
            Rect rect = new Rect();
            this.n.getGlobalVisibleRect(rect);
            LayoutParams layoutParams = (LayoutParams) this.p.getLayoutParams();
            layoutParams.leftMargin = rect.right - this.s;
            layoutParams.topMargin = rect.bottom - ((int) this.k.getResources().getDimension(R.dimen.skip_btn_bottom));
            this.p.setLayoutParams(layoutParams);
            this.p.setVisibility(0);
            this.p.bringToFront();
        }
    }

    private synchronized void t() {
        if (this.q != null) {
            Rect rect = new Rect();
            this.n.getGlobalVisibleRect(rect);
            LayoutParams layoutParams = (LayoutParams) this.q.getLayoutParams();
            layoutParams.leftMargin = (int) this.k.getResources().getDimension(R.dimen.progress_btn_left_margin);
            layoutParams.topMargin = rect.bottom - ((int) this.k.getResources().getDimension(R.dimen.progress_btn_bottom_margin));
            this.q.setLayoutParams(layoutParams);
            this.q.setVisibility(0);
            this.q.bringToFront();
        }
    }

    private void a(long j) {
        if (j < ((long) com.til.colombia.android.internal.a.p())) {
            j = (long) com.til.colombia.android.internal.a.p();
        }
        this.w.sendEmptyMessageDelayed(0, j);
    }

    private void a(String str, Bundle bundle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(":");
        stringBuilder.append(this.h.getUID());
        Intent intent = new Intent(stringBuilder.toString());
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(this).sendBroadcastSync(intent);
    }

    public final void a(USER_ACTION user_action) {
        Bundle bundle = new Bundle();
        bundle.putString("USER_ACTION", user_action.toString());
        a(ck.c, bundle);
        super.finish();
    }

    public void onBackPressed() {
        Bundle bundle = new Bundle();
        bundle.putString("USER_ACTION", USER_ACTION.USER_CLOSED.toString());
        a(ck.c, bundle);
        super.onBackPressed();
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        this.l.removeAllViews();
        if (this.w != null) {
            this.w.removeMessages(0);
            this.w = null;
        }
        if (this.z != null) {
            this.z.shutdownNow();
            this.z = null;
        }
        if (this.m != null && (this.m instanceof com.til.colombia.android.vast.i)) {
            ((com.til.colombia.android.vast.i) this.m).clearHistory();
            ((com.til.colombia.android.vast.i) this.m).clearCache(true);
            ((com.til.colombia.android.vast.i) this.m).destroy();
        }
        this.m = null;
        if (this.n != null) {
            this.n.suspend();
            this.n = null;
        }
        try {
            this.y.abandonAudioFocus(null);
        } catch (Exception e) {
            Log.a(i.f, "", e);
        } catch (Throwable th) {
            this.y = null;
        }
        this.y = null;
        try {
            g.a(com.til.colombia.android.commons.a.a.a());
        } catch (IOException e2) {
            Log.a(i.f, "", e2);
        }
        d();
        super.onDestroy();
    }

    static /* synthetic */ void k(InterstitialActivity interstitialActivity) {
        boolean z;
        if (interstitialActivity.z != null) {
            interstitialActivity.z.shutdownNow();
            interstitialActivity.z = null;
        }
        interstitialActivity.l.removeView(interstitialActivity.q);
        interstitialActivity.l.removeView(interstitialActivity.p);
        interstitialActivity.l.removeView(interstitialActivity.n);
        if (interstitialActivity.r != null) {
            interstitialActivity.l.removeView(interstitialActivity.r);
        }
        interstitialActivity.q = null;
        interstitialActivity.p = null;
        interstitialActivity.n = null;
        interstitialActivity.r = null;
        int i = 1;
        interstitialActivity.setRequestedOrientation(1);
        interstitialActivity.l.a(true);
        if (interstitialActivity.f == null || interstitialActivity.f.getPostAudioCompanion() == null || interstitialActivity.f.getPostAudioCompanion().getCompanionResource() == null) {
            if (interstitialActivity.g == null || interstitialActivity.g.getVastResource() == null) {
                i = 0;
            } else {
                if (interstitialActivity.g.getVastResource().getType() == Type.STATIC_RESOURCE && interstitialActivity.g.getVastResource().getCreativeType() == CreativeType.IMAGE) {
                    interstitialActivity.m = new ImageView(interstitialActivity.e);
                } else {
                    interstitialActivity.m = new com.til.colombia.android.vast.i(interstitialActivity.e);
                }
                interstitialActivity.h();
                interstitialActivity.g.getVastResource().initializeVastResourceView(interstitialActivity.m);
                List<VastTrackingEvent> creativeViewTrackers = interstitialActivity.g.getCreativeViewTrackers();
                if (creativeViewTrackers != null) {
                    for (VastTrackingEvent url : creativeViewTrackers) {
                        l.a(url.getUrl(), 5, null);
                    }
                }
            }
            z = i;
        } else {
            if (interstitialActivity.f.getPostAudioCompanion().getCompanionResource().getType() == Type.STATIC_RESOURCE && interstitialActivity.f.getPostAudioCompanion().getCompanionResource().getCreativeType() == CreativeType.IMAGE) {
                interstitialActivity.m = new ImageView(interstitialActivity.e);
            } else {
                interstitialActivity.m = new com.til.colombia.android.vast.i(interstitialActivity.e);
            }
            interstitialActivity.h();
            interstitialActivity.f.getPostAudioCompanion().initialize(interstitialActivity.m);
            z = false;
        }
        if (i != 0) {
            if (interstitialActivity.m instanceof WebView) {
                ((com.til.colombia.android.vast.i) interstitialActivity.m).setOnTouchListener(new c(interstitialActivity, (byte) 0));
            } else {
                interstitialActivity.a(interstitialActivity.m, com.til.colombia.android.vast.h.b, z);
            }
            if (interstitialActivity.f == null || interstitialActivity.f.getPostAudioCompanion() == null || h.a(interstitialActivity.f.getPostAudioCompanion().getAudioSrc())) {
                interstitialActivity.a((long) com.til.colombia.android.internal.a.p());
                return;
            } else {
                interstitialActivity.a(interstitialActivity.f.getPostAudioCompanion().getAudioSrc());
                return;
            }
        }
        interstitialActivity.a(USER_ACTION.INVALID_CONFIG);
    }

    static /* synthetic */ void l(InterstitialActivity interstitialActivity) {
        if (interstitialActivity.n != null && interstitialActivity.n.isPlaying()) {
            int duration = interstitialActivity.n.getDuration() / 1000;
            int currentPosition = interstitialActivity.n.getCurrentPosition() / 1000;
            if (interstitialActivity.q != null) {
                String a = CommonUtil.a(duration - currentPosition);
                TextView textView = interstitialActivity.q;
                StringBuilder stringBuilder = new StringBuilder("Ad - ");
                stringBuilder.append(a);
                textView.setText(stringBuilder.toString());
                interstitialActivity.t();
            }
            if (currentPosition == interstitialActivity.h.getVastHelper().getStartNotifyTime()) {
                n.a(interstitialActivity.h.getVastHelper().getVastTrackingByType(2), 5, "start video tracked.");
                if (interstitialActivity.r == null && interstitialActivity.h.getItemType() == ITEM_TYPE.INTERSTITIAL_VIDEO) {
                    interstitialActivity.r = new Button(interstitialActivity.k);
                    interstitialActivity.r.setTypeface(Typeface.SERIF);
                    interstitialActivity.r.setTextSize(18.0f);
                    interstitialActivity.r.setBackgroundResource(R.drawable.interstitial_ctabutton);
                    interstitialActivity.r.setTextColor(interstitialActivity.getResources().getColor(R.color.color_white));
                    interstitialActivity.r.setGravity(17);
                    interstitialActivity.r.setText(interstitialActivity.h.getCtaText());
                    interstitialActivity.r.setVisibility(0);
                    interstitialActivity.r.setOnClickListener(new bp(interstitialActivity));
                    LayoutParams layoutParams = new LayoutParams(-2, -2);
                    layoutParams.gravity = 81;
                    layoutParams.bottomMargin = (int) interstitialActivity.k.getResources().getDimension(R.dimen.skip_btn_bottom);
                    interstitialActivity.l.addView(interstitialActivity.r, layoutParams);
                }
            }
            if (currentPosition == interstitialActivity.h.getVastHelper().getSkipOffset(interstitialActivity.n.getDuration())) {
                if (interstitialActivity.p != null) {
                    interstitialActivity.s = interstitialActivity.p.getWidth();
                }
                interstitialActivity.a(ck.f, null);
            }
            n.a(interstitialActivity.h.getVastHelper().getVastProgressEvent(currentPosition), 5, "video progress tracked.");
            if (currentPosition >= interstitialActivity.h.getVastHelper().getSkipOffset(interstitialActivity.n.getDuration())) {
                interstitialActivity.s();
            }
            int i = currentPosition * 4;
            if (duration >= i && duration < (currentPosition + 1) * 4) {
                n.a(interstitialActivity.h.getVastHelper().getVastTrackingByType(3), 5, "video Q1 tracked.");
            } else if (duration < currentPosition * 2 || duration >= (currentPosition + 1) * 2) {
                duration *= 3;
                if (duration >= i && duration < (currentPosition + 1) * 4) {
                    n.a(interstitialActivity.h.getVastHelper().getVastTrackingByType(5), 5, "video Q3 tracked.");
                }
            } else {
                n.a(interstitialActivity.h.getVastHelper().getVastTrackingByType(4), 5, "video Q-mid tracked.");
            }
        }
    }
}
