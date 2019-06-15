package com.facebook.ads.internal.view.g.c;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Handler;
import android.os.Looper;
import com.facebook.ads.internal.view.g.a.c;
import com.facebook.ads.internal.view.g.b.h;
import com.facebook.ads.internal.view.g.b.i;
import com.facebook.ads.internal.view.g.b.j;
import com.facebook.ads.internal.view.g.b.k;
import java.lang.ref.WeakReference;

public class b extends c {
    private WeakReference<OnAudioFocusChangeListener> a = null;
    private final com.facebook.ads.internal.view.g.b.c b = new com.facebook.ads.internal.view.g.b.c() {
        public void a(com.facebook.ads.internal.view.g.b.b bVar) {
            ((AudioManager) b.this.getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(b.this.a == null ? null : (OnAudioFocusChangeListener) b.this.a.get());
        }
    };
    private final i c = new i() {
        public void a(h hVar) {
            ((AudioManager) b.this.getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(b.this.a == null ? null : (OnAudioFocusChangeListener) b.this.a.get());
        }
    };
    private final k d = new k() {
        public void a(j jVar) {
            if (b.this.a == null || b.this.a.get() == null) {
                b.this.a = new WeakReference(new OnAudioFocusChangeListener() {
                    public void onAudioFocusChange(final int i) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                if (b.this.getVideoView() != null && i <= 0) {
                                    b.this.getVideoView().a(false);
                                }
                            }
                        });
                    }
                });
            }
            ((AudioManager) b.this.getContext().getApplicationContext().getSystemService("audio")).requestAudioFocus((OnAudioFocusChangeListener) b.this.a.get(), 3, 1);
        }
    };

    public b(Context context) {
        super(context);
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        super.a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().a(this.d, this.b, this.c);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b(this.c, this.b, this.d);
        }
        super.b();
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        ((AudioManager) getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.a == null ? null : (OnAudioFocusChangeListener) this.a.get());
        super.onDetachedFromWindow();
    }
}
