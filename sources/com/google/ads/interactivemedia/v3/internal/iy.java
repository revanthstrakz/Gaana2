package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.media.MediaCodec.CryptoException;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer.VideoAdPlayerCallback;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class iy implements jj {
    private final ba a;
    private final SurfaceView b;
    private final at c;
    private final FrameLayout d;
    private final ViewGroup e;
    private final Context f;
    private final Handler g;
    private final List<VideoAdPlayerCallback> h;
    private final b i;
    private final e j;
    private final c k;
    private final a l;
    private f m;
    private boolean n;
    private bq o;

    class a implements com.google.ads.interactivemedia.v3.internal.ba.c {
        a() {
        }

        public void a() {
        }

        public void a(az azVar) {
            iy.this.g();
            String valueOf = String.valueOf(azVar);
            StringBuilder stringBuilder = new StringBuilder(13 + String.valueOf(valueOf).length());
            stringBuilder.append("Player Error:");
            stringBuilder.append(valueOf);
            Log.e("IMA SDK", stringBuilder.toString());
        }

        public void a(boolean z, int i) {
            if (i == 5) {
                for (VideoAdPlayerCallback onEnded : iy.this.h) {
                    onEnded.onEnded();
                }
            }
            if (i == 4) {
                for (VideoAdPlayerCallback onLoaded : iy.this.h) {
                    onLoaded.onLoaded();
                }
            }
        }
    }

    class b implements com.google.ads.interactivemedia.v3.internal.cf.a {
        b() {
        }

        public void a(int i, IOException iOException) {
            iy.this.g();
            String valueOf = String.valueOf(iOException);
            StringBuilder stringBuilder = new StringBuilder(41 + String.valueOf(valueOf).length());
            stringBuilder.append("Load Error from SampleSource:");
            stringBuilder.append(i);
            stringBuilder.append(":");
            stringBuilder.append(valueOf);
            Log.e("IMA SDK", stringBuilder.toString());
        }
    }

    class d implements com.google.ads.interactivemedia.v3.internal.bg.b {
        d() {
        }

        public void a(String str, long j, long j2) {
        }

        public void a(com.google.ads.interactivemedia.v3.internal.bg.a aVar) {
            iy.this.g();
        }

        public void a(CryptoException cryptoException) {
            iy.this.g();
        }
    }

    class c extends d implements com.google.ads.interactivemedia.v3.internal.be.a {
        c(iy iyVar) {
            super();
        }

        public void a(int i, long j, long j2) {
        }

        public void a(com.google.ads.interactivemedia.v3.internal.bt.d dVar) {
        }

        public void a(com.google.ads.interactivemedia.v3.internal.bt.f fVar) {
        }
    }

    class e extends d implements com.google.ads.interactivemedia.v3.internal.bi.a {
        e() {
            super();
        }

        public void a(int i, long j) {
        }

        public void a(Surface surface) {
        }

        public void a(int i, int i2, int i3, float f) {
            iy.this.c.a(i2 == 0 ? 1.0f : (((float) i) * f) / ((float) i2));
        }
    }

    enum f {
        IDLE,
        LOADED,
        PLAYING,
        PAUSED
    }

    public enum g {
        TYPE_VIDEO(0),
        TYPE_AUDIO(1);
        
        private final int c;

        public int a() {
            return this.c;
        }

        private g(int i) {
            this.c = i;
        }
    }

    public iy(Context context, ViewGroup viewGroup) {
        this(context, viewGroup, com.google.ads.interactivemedia.v3.internal.ba.b.a(2));
    }

    public int getVolume() {
        return 100;
    }

    iy(Context context, ViewGroup viewGroup, final ba baVar) {
        this.f = context;
        this.e = viewGroup;
        this.a = baVar;
        this.i = new b();
        this.k = new c(this);
        this.j = new e();
        this.l = new a();
        baVar.a(this.l);
        this.g = new Handler();
        this.h = new ArrayList(1);
        this.d = new FrameLayout(context);
        this.d.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.c = new at(context);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.c.setLayoutParams(layoutParams);
        this.m = f.IDLE;
        this.b = new SurfaceView(context);
        this.b.setZOrderMediaOverlay(true);
        this.b.getHolder().addCallback(new Callback() {
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            }

            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                iy.this.n = true;
                if (iy.this.m == f.PLAYING || iy.this.m == f.PAUSED) {
                    iy.this.a(surfaceHolder.getSurface(), false);
                }
                if (iy.this.m == f.PLAYING) {
                    baVar.a(true);
                }
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                iy.this.a(null, true);
                baVar.a(false);
                iy.this.n = false;
            }
        });
        this.c.addView(this.b);
        this.d.addView(this.c);
        this.e.addView(this.d, new ViewGroup.LayoutParams(-1, -1));
    }

    public void playAd() {
        switch (this.m) {
            case LOADED:
                for (VideoAdPlayerCallback onPlay : this.h) {
                    onPlay.onPlay();
                }
                a(this.b.getHolder().getSurface(), false);
                break;
            case PAUSED:
                for (VideoAdPlayerCallback onPlay2 : this.h) {
                    onPlay2.onResume();
                }
                break;
            case PLAYING:
                return;
            default:
                String valueOf = String.valueOf(this.m);
                StringBuilder stringBuilder = new StringBuilder(53 + String.valueOf(valueOf).length());
                stringBuilder.append("Ignoring call to playAd during invalid player state: ");
                stringBuilder.append(valueOf);
                Log.w("IMA SDK", stringBuilder.toString());
                return;
        }
        this.m = f.PLAYING;
        if (this.n) {
            this.a.a(true);
        }
    }

    public void loadAd(String str) {
        this.a.b();
        this.a.a(0);
        bq[] a = new jp(this.f, ft.a(this.f, "IMA SDK ExoPlayer"), Uri.parse(str)).a(this, this.g);
        this.o = a[g.TYPE_VIDEO.a()];
        this.a.a(a);
        this.m = f.LOADED;
    }

    public void stopAd() {
        this.m = f.IDLE;
        this.a.b();
        a(null, false);
    }

    public void pauseAd() {
        this.m = f.PAUSED;
        this.a.a(false);
        for (VideoAdPlayerCallback onPause : this.h) {
            onPause.onPause();
        }
    }

    public void resumeAd() {
        playAd();
    }

    public void addCallback(VideoAdPlayerCallback videoAdPlayerCallback) {
        this.h.add(videoAdPlayerCallback);
    }

    public void removeCallback(VideoAdPlayerCallback videoAdPlayerCallback) {
        this.h.remove(videoAdPlayerCallback);
    }

    public VideoProgressUpdate getAdProgress() {
        if ((this.a.a() == 3 || this.a.a() == 4) && this.a.d() > 0) {
            return new VideoProgressUpdate(this.a.e(), this.a.d());
        }
        return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
    }

    private void a(Surface surface, boolean z) {
        if (this.a != null && this.o != null) {
            if (z) {
                this.a.b(this.o, 1, surface);
            } else {
                this.a.a(this.o, 1, surface);
            }
        }
    }

    public void a() {
        this.d.setVisibility(0);
        this.b.setVisibility(0);
    }

    public void b() {
        this.d.setVisibility(8);
        this.b.setVisibility(4);
    }

    public void c() {
        this.a.b(this.l);
        this.a.c();
        this.e.removeView(this.d);
    }

    private void g() {
        for (VideoAdPlayerCallback onError : this.h) {
            onError.onError();
        }
    }

    public b d() {
        return this.i;
    }

    public e e() {
        return this.j;
    }

    public c f() {
        return this.k;
    }
}
