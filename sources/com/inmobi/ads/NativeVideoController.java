package com.inmobi.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.inmobi.commons.core.utilities.b.c;
import com.inmobi.rendering.CustomView;
import java.lang.ref.WeakReference;

@TargetApi(15)
public class NativeVideoController extends FrameLayout {
    private static final String b = "NativeVideoController";
    boolean a;
    private bd c;
    private a d;
    private NativeVideoView e;
    private CustomView f;
    private CustomView g;
    private ProgressBar h;
    private RelativeLayout i;
    private boolean j;
    private float k;
    private final OnClickListener l;

    static final class a extends Handler {
        @NonNull
        private final WeakReference<NativeVideoController> a;

        a(@NonNull NativeVideoController nativeVideoController) {
            this.a = new WeakReference(nativeVideoController);
        }

        public final void handleMessage(Message message) {
            if (message.what != 2) {
                super.handleMessage(message);
                return;
            }
            NativeVideoController nativeVideoController = (NativeVideoController) this.a.get();
            if (nativeVideoController != null) {
                nativeVideoController.e();
                if (nativeVideoController.a && nativeVideoController.e.isPlaying()) {
                    sendMessageDelayed(obtainMessage(2), 200);
                }
            }
        }
    }

    public NativeVideoController(Context context) {
        this(context, null);
    }

    public NativeVideoController(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NativeVideoController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = false;
        this.l = new OnClickListener() {
            public final void onClick(View view) {
                NativeVideoController.c(NativeVideoController.this);
            }
        };
        this.i = new RelativeLayout(getContext());
        addView(this.i, new LayoutParams(-1, -1));
        this.i.setPadding(0, 0, 0, 0);
        if (this.i != null) {
            this.k = c.a().c;
            this.f = new CustomView(getContext(), this.k, 9);
            this.g = new CustomView(getContext(), this.k, 11);
            this.h = new ProgressBar(getContext(), null, 16842872);
            this.h.setScaleY(0.8f);
            c();
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            layoutParams.addRule(12, -1);
            float f = c.a().c;
            layoutParams.setMargins(0, (int) (-6.0f * f), 0, (int) (-8.0f * f));
            LayerDrawable layerDrawable = (LayerDrawable) this.h.getProgressDrawable();
            if (layerDrawable != null) {
                layerDrawable.getDrawable(0).setColorFilter(-1, Mode.SRC_IN);
                layerDrawable.getDrawable(2).setColorFilter(-327674, Mode.SRC_IN);
            }
            this.i.addView(this.h, layoutParams);
        }
        this.d = new a(this);
    }

    public void setMediaPlayer(@NonNull NativeVideoView nativeVideoView) {
        this.e = nativeVideoView;
        be beVar = (be) this.e.getTag();
        if (beVar != null && beVar.B && !beVar.a()) {
            this.j = true;
            this.i.removeView(this.g);
            this.i.removeView(this.f);
            d();
        }
    }

    private void c() {
        LayoutParams layoutParams = new LayoutParams((int) (this.k * 30.0f), (int) (30.0f * this.k));
        layoutParams.addRule(9, -1);
        layoutParams.addRule(12, -1);
        this.i.addView(this.f, layoutParams);
        this.f.setOnClickListener(this.l);
    }

    private void d() {
        LayoutParams layoutParams = new LayoutParams((int) (this.k * 30.0f), (int) (30.0f * this.k));
        layoutParams.addRule(9, -1);
        layoutParams.addRule(12, -1);
        this.i.addView(this.g, layoutParams);
        this.g.setOnClickListener(this.l);
    }

    public final void a() {
        if (!this.a) {
            e();
            this.a = true;
            be beVar = (be) this.e.getTag();
            if (beVar != null) {
                int i = 4;
                this.f.setVisibility(beVar.B ? 0 : 4);
                ProgressBar progressBar = this.h;
                if (beVar.D) {
                    i = 0;
                }
                progressBar.setVisibility(i);
            }
            setVisibility(0);
        }
        this.d.sendEmptyMessage(2);
    }

    public final void b() {
        if (this.a) {
            try {
                this.d.removeMessages(2);
                setVisibility(8);
            } catch (IllegalArgumentException e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            this.a = false;
        }
    }

    @TargetApi(15)
    private int e() {
        if (this.e == null) {
            return 0;
        }
        int currentPosition = this.e.getCurrentPosition();
        int duration = this.e.getDuration();
        if (!(this.h == null || duration == 0)) {
            this.h.setProgress((currentPosition * 100) / duration);
        }
        return currentPosition;
    }

    @TargetApi(15)
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (VERSION.SDK_INT >= 15 && this.e != null && this.e.b()) {
            if (this.a) {
                b();
            } else {
                a();
            }
        }
        return false;
    }

    @TargetApi(15)
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        boolean z = keyEvent.getRepeatCount() == 0 && keyEvent.getAction() == 0;
        if (VERSION.SDK_INT >= 15) {
            if (keyCode == 79 || keyCode == 85 || keyCode == 62) {
                if (z) {
                    if (this.e.isPlaying()) {
                        this.e.pause();
                    } else {
                        this.e.start();
                    }
                    a();
                }
                return true;
            } else if (keyCode == 126) {
                if (z && !this.e.isPlaying()) {
                    this.e.start();
                    a();
                }
                return true;
            } else if (keyCode == 86 || keyCode == 127) {
                if (z && this.e.isPlaying()) {
                    this.e.pause();
                    a();
                }
                return true;
            } else if (keyCode == 25 || keyCode == 24 || keyCode == 164 || keyCode == 27) {
                return super.dispatchKeyEvent(keyEvent);
            } else {
                a();
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(NativeVideoController.class.getName());
    }

    @TargetApi(15)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (VERSION.SDK_INT >= 15) {
            accessibilityNodeInfo.setClassName(NativeVideoController.class.getName());
        }
    }

    public void setVideoAd(bd bdVar) {
        this.c = bdVar;
    }

    static /* synthetic */ void c(NativeVideoController nativeVideoController) {
        if (nativeVideoController.e != null) {
            be beVar = (be) nativeVideoController.e.getTag();
            if (nativeVideoController.j) {
                nativeVideoController.e.e();
                nativeVideoController.j = false;
                nativeVideoController.i.removeView(nativeVideoController.g);
                nativeVideoController.i.removeView(nativeVideoController.f);
                nativeVideoController.c();
                if (!(beVar == null || nativeVideoController.c == null)) {
                    try {
                        nativeVideoController.c.d(beVar);
                        beVar.A = true;
                        return;
                    } catch (Exception e) {
                        new StringBuilder("SDK encountered unexpected error in handling the onVideoUnMuted event; ").append(e.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                        return;
                    }
                }
            }
            nativeVideoController.e.d();
            nativeVideoController.j = true;
            nativeVideoController.i.removeView(nativeVideoController.f);
            nativeVideoController.i.removeView(nativeVideoController.g);
            nativeVideoController.d();
            if (!(beVar == null || nativeVideoController.c == null)) {
                try {
                    nativeVideoController.c.c(beVar);
                    beVar.A = false;
                } catch (Exception e2) {
                    new StringBuilder("SDK encountered unexpected error in handling the onVideoMuted event; ").append(e2.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                }
            }
        }
    }
}
