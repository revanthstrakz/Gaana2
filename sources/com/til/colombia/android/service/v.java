package com.til.colombia.android.service;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.til.colombia.android.R;
import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;
import com.til.colombia.android.commons.CommonUtil;
import com.til.colombia.android.commons.CommonUtil.AutoPlay;
import com.til.colombia.android.commons.CommonUtil.InlineVideoVisiblity;
import com.til.colombia.android.commons.CommonUtil.VideoPauseMode;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.internal.a.j;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.service.am.b;

@TargetApi(14)
final class v implements OnAudioFocusChangeListener, SurfaceTextureListener, b {
    private static final String d = "BaseVideoView";
    Context a;
    com.til.colombia.android.internal.a.a b;
    am c;
    private RelativeLayout e;
    private View f;
    private TextureView g;
    private Surface h;
    private ProgressBar i;
    private ImageView j;
    private ImageView k;
    private FrameLayout l;
    private VASTHelper m;
    private String n;
    private j.b o;
    private CmItem p;
    private boolean q = true;
    private Button r;
    private Button s;
    private Button t;
    private boolean u = false;
    private AudioManager v;
    private OnScrollChangedListener w = new x(this);
    private OnClickListener x = new y(this);
    private OnClickListener y = new z(this);
    private OnClickListener z = new aa(this);

    private class a extends AsyncTask<Object, Object, Bitmap> {
        private a() {
        }

        /* synthetic */ a(v vVar, byte b) {
            this();
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Bitmap bitmap = (Bitmap) obj;
            super.onPostExecute(bitmap);
            if ((v.this.c == null || v.this.c.e != COLOMBIA_PLAYER_STATE.ERROR) && bitmap != null && bitmap.getHeight() > 0 && bitmap.getWidth() > 0) {
                try {
                    if (v.this.j != null) {
                        CommonUtil.a(bitmap, v.this.j);
                    }
                } catch (Exception e) {
                    Log.a(i.f, "", e);
                }
            }
        }

        private Bitmap a() {
            return CommonUtil.a(v.this.n);
        }

        private void a(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if ((v.this.c == null || v.this.c.e != COLOMBIA_PLAYER_STATE.ERROR) && bitmap != null && bitmap.getHeight() > 0 && bitmap.getWidth() > 0) {
                try {
                    if (v.this.j != null) {
                        CommonUtil.a(bitmap, v.this.j);
                    }
                } catch (Exception e) {
                    Log.a(i.f, "", e);
                }
            }
        }

        /* Access modifiers changed, original: protected|final|synthetic */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return CommonUtil.a(v.this.n);
        }
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    private v(Context context) {
        this.a = context;
        this.o = new j.b();
    }

    public v(Context context, RelativeLayout relativeLayout, View view) {
        this.a = context;
        this.e = relativeLayout;
        this.f = view;
        this.o = new j.b();
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(CmItem cmItem) {
        this.p = cmItem;
        this.m = ((NativeItem) cmItem).getVastHelper();
        this.n = this.m.getMediaFileUrl();
        this.v = (AudioManager) this.a.getSystemService("audio");
    }

    public final void a() {
        CommonUtil.a(this.a, this.e);
        this.l = (FrameLayout) LayoutInflater.from(this.a).inflate(R.layout.inline_video_layout, this.l, true);
        this.j = (ImageView) this.l.findViewById(R.id.thumb);
        if (VERSION.SDK_INT >= 11) {
            new a(this, (byte) 0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        } else {
            new a(this, (byte) 0).execute(new Object[0]);
        }
        this.k = (ImageView) this.l.findViewById(R.id.play);
        this.i = (ProgressBar) this.l.findViewById(R.id.pbHeaderProgress);
        this.g = (TextureView) this.l.findViewById(R.id.textureview);
        this.g.setSurfaceTextureListener(this);
        this.g.setOnClickListener(this.x);
        this.r = (Button) this.l.findViewById(R.id.mute_btn);
        ((LinearLayout) this.l.findViewById(R.id.mute_btnlayout)).setOnClickListener(this.y);
        this.r.setOnClickListener(this.y);
        if (!(h.a(this.p.getCtaText()) || h.a(((NativeItem) this.p).getCtaVideoUrl()))) {
            this.s = (Button) this.l.findViewById(R.id.cta_btn);
            this.t = (Button) this.l.findViewById(R.id.replay_cta_btn);
            this.s.setText(this.p.getCtaText());
            this.t.setText(this.p.getCtaText());
            this.s.setOnClickListener(this.z);
            this.t.setOnClickListener(this.z);
        }
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.e.addView(this.l, layoutParams);
        this.e.getViewTreeObserver().addOnScrollChangedListener(this.w);
    }

    private void n() {
        this.l = (FrameLayout) LayoutInflater.from(this.a).inflate(R.layout.inline_video_layout, this.l, true);
        this.j = (ImageView) this.l.findViewById(R.id.thumb);
        if (VERSION.SDK_INT >= 11) {
            new a(this, (byte) 0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
        } else {
            new a(this, (byte) 0).execute(new Object[0]);
        }
        this.k = (ImageView) this.l.findViewById(R.id.play);
        this.i = (ProgressBar) this.l.findViewById(R.id.pbHeaderProgress);
        this.g = (TextureView) this.l.findViewById(R.id.textureview);
        this.g.setSurfaceTextureListener(this);
        this.g.setOnClickListener(this.x);
        this.r = (Button) this.l.findViewById(R.id.mute_btn);
        ((LinearLayout) this.l.findViewById(R.id.mute_btnlayout)).setOnClickListener(this.y);
        this.r.setOnClickListener(this.y);
        if (!(h.a(this.p.getCtaText()) || h.a(((NativeItem) this.p).getCtaVideoUrl()))) {
            this.s = (Button) this.l.findViewById(R.id.cta_btn);
            this.t = (Button) this.l.findViewById(R.id.replay_cta_btn);
            this.s.setText(this.p.getCtaText());
            this.t.setText(this.p.getCtaText());
            this.s.setOnClickListener(this.z);
            this.t.setOnClickListener(this.z);
        }
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.e.addView(this.l, layoutParams);
    }

    private void o() {
        this.r = (Button) this.l.findViewById(R.id.mute_btn);
        ((LinearLayout) this.l.findViewById(R.id.mute_btnlayout)).setOnClickListener(this.y);
        this.r.setOnClickListener(this.y);
        if (!h.a(this.p.getCtaText()) && !h.a(((NativeItem) this.p).getCtaVideoUrl())) {
            this.s = (Button) this.l.findViewById(R.id.cta_btn);
            this.t = (Button) this.l.findViewById(R.id.replay_cta_btn);
            this.s.setText(this.p.getCtaText());
            this.t.setText(this.p.getCtaText());
            this.s.setOnClickListener(this.z);
            this.t.setOnClickListener(this.z);
        }
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.h = new Surface(surfaceTexture);
        if (this.c != null) {
            this.c.setSurface(this.h);
        } else if (h.a(this.n)) {
            f();
        } else {
            try {
                this.c = new am(this.a, Uri.parse(this.n), this.p, this.i);
                this.c.a = this;
                this.c.setSurface(this.h);
                this.c.setAudioStreamType(3);
                this.c.a();
                this.c.prepareAsync();
            } catch (Exception unused) {
                f();
            }
            if (((NativeItem) this.p).getPlayMode() == AutoPlay.ON && !CommonUtil.a(this.a) && com.til.colombia.android.internal.a.b(this.a)) {
                this.k.setVisibility(8);
                this.k.setBackgroundResource(R.drawable.play);
                this.i.setVisibility(0);
            }
            if (com.til.colombia.android.internal.a.J()) {
                this.b = new w(this);
                this.b.a(this.a);
            }
        }
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.h = null;
        return false;
    }

    private void p() {
        if (h.a(this.n)) {
            f();
            return;
        }
        try {
            this.c = new am(this.a, Uri.parse(this.n), this.p, this.i);
            this.c.a = this;
            this.c.setSurface(this.h);
            this.c.setAudioStreamType(3);
            this.c.a();
            this.c.prepareAsync();
        } catch (Exception unused) {
            f();
        }
        if (((NativeItem) this.p).getPlayMode() == AutoPlay.ON && !CommonUtil.a(this.a) && com.til.colombia.android.internal.a.b(this.a)) {
            this.k.setVisibility(8);
            this.k.setBackgroundResource(R.drawable.play);
            this.i.setVisibility(0);
        }
        if (com.til.colombia.android.internal.a.J()) {
            this.b = new w(this);
            this.b.a(this.a);
        }
    }

    public final void b() {
        if (this.c != null) {
            CommonUtil.a(this.l, this.g, this.c, this.j);
        }
        if (((NativeItem) this.p).getPlayMode() == AutoPlay.ON && !CommonUtil.a(this.a)) {
            a(false);
        }
    }

    public final void c() {
        if (!(this.c == null || this.c.b)) {
            this.v.requestAudioFocus(this, 3, 1);
        }
        s();
        q();
    }

    public final void d() {
        t();
    }

    public final void e() {
        this.u = false;
        if (!i()) {
            this.k.setBackgroundResource(R.drawable.replay);
            if (this.t != null) {
                this.t.setVisibility(0);
            }
            t();
            this.v.abandonAudioFocus(this);
        }
    }

    public final void f() {
        t();
        this.i.setVisibility(8);
        if (!h.a(this.n)) {
            try {
                this.c = new am(this.a, Uri.parse(this.n), this.p, this.i);
                this.c.a = this;
                this.c.setSurface(this.h);
                this.c.setAudioStreamType(3);
                this.c.a();
            } catch (Exception unused) {
            }
        }
    }

    public final void g() {
        this.u = true;
        if (i() && this.s != null && this.u) {
            this.s.setVisibility(0);
        }
    }

    public final void h() {
        if (this.k.getVisibility() == 0) {
            s();
        }
    }

    public final void onAudioFocusChange(int i) {
        if (i == -2) {
            if (i() || (this.c != null && this.c.d() == VideoPauseMode.AUTO_PAUSE)) {
                a(false);
            }
        } else if (i != 1 && i == -1 && (i() || (this.c != null && this.c.d() == VideoPauseMode.AUTO_PAUSE))) {
            a(false);
        }
    }

    private void q() {
        if (this.e != null && this.c != null && this.o != null) {
            int a = this.o.a(this.f, this.e, 60);
            if (a != InlineVideoVisiblity.NONE.ordinal()) {
                if (a == InlineVideoVisiblity.VISIBLE.ordinal()) {
                    if (this.c.d() == VideoPauseMode.AUTO_PAUSE) {
                        s();
                        this.c.start();
                    }
                } else if (this.c.isPlaying()) {
                    this.c.pause();
                    this.c.c = VideoPauseMode.AUTO_PAUSE;
                }
            }
        }
    }

    private void a(boolean z) {
        if (this.c != null) {
            am amVar = this.c;
            try {
                amVar.setVolume(0.0f, 0.0f);
                amVar.b = true;
                if (z) {
                    amVar.i();
                }
            } catch (Exception e) {
                Log.a(i.f, "", e);
            }
        }
        this.r.setBackgroundResource(R.drawable.mute);
        this.v.abandonAudioFocus(this);
    }

    private void b(boolean z) {
        this.v.requestAudioFocus(this, 3, 1);
        if (this.c != null) {
            am amVar = this.c;
            try {
                amVar.setVolume(1.0f, 1.0f);
                amVar.b = false;
                if (z) {
                    amVar.j();
                }
            } catch (Exception e) {
                Log.a(i.f, "", e);
            }
        }
        this.r.setBackgroundResource(R.drawable.unmute);
    }

    public final boolean i() {
        return this.c != null && this.c.isPlaying();
    }

    private VideoPauseMode r() {
        if (this.c == null) {
            return VideoPauseMode.NONE;
        }
        return this.c.d();
    }

    /* Access modifiers changed, original: protected|final */
    public final void j() {
        if (this.c != null) {
            if (i()) {
                this.c.pause();
                this.c.c = VideoPauseMode.USER_PAUSE;
                t();
            }
            this.c.e();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void k() {
        if (this.c != null) {
            this.q = false;
            if (i()) {
                this.c.pause();
                this.c.c = VideoPauseMode.AUTO_PAUSE;
                t();
            }
            this.c.e();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void l() {
        this.q = true;
        if (this.c != null) {
            if (i()) {
                this.c.setSurface(this.h);
                s();
            } else if (this.c.d() == VideoPauseMode.USER_PAUSE) {
                this.c.e();
                t();
            } else if (this.c.d() == VideoPauseMode.AUTO_PAUSE) {
                q();
            } else if (this.c.d() == VideoPauseMode.BUFFERING) {
                this.c.f();
            } else {
                if (this.c.e == COLOMBIA_PLAYER_STATE.PREPARING || this.c.e == COLOMBIA_PLAYER_STATE.PREPARED) {
                    this.i.setVisibility(0);
                }
            }
        }
    }

    private void s() {
        this.k.setVisibility(8);
        this.k.setBackgroundResource(R.drawable.play);
        this.r.setVisibility(0);
        if (this.s != null && this.u) {
            this.s.setVisibility(0);
        }
        if (this.t != null) {
            this.t.setVisibility(8);
        }
    }

    private void t() {
        this.k.setVisibility(0);
        this.r.setVisibility(8);
        if (this.s != null) {
            this.s.setVisibility(8);
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void m() {
        if (this.b != null) {
            this.b.b(this.a);
        }
        if (this.c != null) {
            this.c.release();
            this.c = null;
        }
    }

    static /* synthetic */ void b(v vVar, boolean z) {
        vVar.v.requestAudioFocus(vVar, 3, 1);
        if (vVar.c != null) {
            am amVar = vVar.c;
            try {
                amVar.setVolume(1.0f, 1.0f);
                amVar.b = false;
                if (z) {
                    amVar.j();
                }
            } catch (Exception e) {
                Log.a(i.f, "", e);
            }
        }
        vVar.r.setBackgroundResource(R.drawable.unmute);
    }
}
