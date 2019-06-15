package com.gaanavideo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import java.io.InputStream;
import java.util.Map;
import java.util.Vector;

public class MutedVideoView extends SurfaceView implements MediaPlayerControl {
    private OnCompletionListener A = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            MutedVideoView.this.g = 5;
            MutedVideoView.this.h = 5;
            if (MutedVideoView.this.p != null) {
                MutedVideoView.this.p.hide();
            }
            if (MutedVideoView.this.q != null) {
                MutedVideoView.this.q.onCompletion(MutedVideoView.this.j);
            }
        }
    };
    private OnInfoListener B = new OnInfoListener() {
        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            if (MutedVideoView.this.u != null) {
                MutedVideoView.this.u.onInfo(mediaPlayer, i, i2);
            }
            return true;
        }
    };
    private OnErrorListener C = new OnErrorListener() {
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            MutedVideoView.this.g = -1;
            MutedVideoView.this.h = -1;
            if (MutedVideoView.this.p != null) {
                MutedVideoView.this.p.hide();
            }
            return (MutedVideoView.this.t == null || MutedVideoView.this.t.onError(MutedVideoView.this.j, i, i2)) ? true : true;
        }
    };
    private OnBufferingUpdateListener D = new OnBufferingUpdateListener() {
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            MutedVideoView.this.s = i;
        }
    };
    OnVideoSizeChangedListener a = new OnVideoSizeChangedListener() {
        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            MutedVideoView.this.l = mediaPlayer.getVideoWidth();
            MutedVideoView.this.m = mediaPlayer.getVideoHeight();
            if (MutedVideoView.this.l != 0 && MutedVideoView.this.m != 0) {
                MutedVideoView.this.getHolder().setFixedSize(MutedVideoView.this.l, MutedVideoView.this.m);
                MutedVideoView.this.requestLayout();
            }
        }
    };
    OnPreparedListener b = new OnPreparedListener() {
        public void onPrepared(MediaPlayer mediaPlayer) {
            MutedVideoView.this.g = 2;
            MutedVideoView.this.w = MutedVideoView.this.x = MutedVideoView.this.y = true;
            if (MutedVideoView.this.r != null) {
                MutedVideoView.this.r.onPrepared(MutedVideoView.this.j);
            }
            if (MutedVideoView.this.p != null) {
                MutedVideoView.this.p.setEnabled(true);
            }
            MutedVideoView.this.l = mediaPlayer.getVideoWidth();
            MutedVideoView.this.m = mediaPlayer.getVideoHeight();
            int f = MutedVideoView.this.v;
            if (f != 0) {
                MutedVideoView.this.seekTo(f);
            }
            if (MutedVideoView.this.l != 0 && MutedVideoView.this.m != 0) {
                MutedVideoView.this.getHolder().setFixedSize(MutedVideoView.this.l, MutedVideoView.this.m);
                if (MutedVideoView.this.n != MutedVideoView.this.l || MutedVideoView.this.o != MutedVideoView.this.m) {
                    return;
                }
                if (MutedVideoView.this.h == 3) {
                    MutedVideoView.this.start();
                    if (MutedVideoView.this.p != null) {
                        MutedVideoView.this.p.show();
                    }
                } else if (!MutedVideoView.this.isPlaying()) {
                    if ((f != 0 || MutedVideoView.this.getCurrentPosition() > 0) && MutedVideoView.this.p != null) {
                        MutedVideoView.this.p.show(0);
                    }
                }
            } else if (MutedVideoView.this.h == 3) {
                MutedVideoView.this.start();
            }
        }
    };
    Callback c = new Callback() {
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            MutedVideoView.this.n = i2;
            MutedVideoView.this.o = i3;
            Object obj = null;
            Object obj2 = MutedVideoView.this.h == 3 ? 1 : null;
            if (MutedVideoView.this.l == i2 && MutedVideoView.this.m == i3) {
                obj = 1;
            }
            if (MutedVideoView.this.j != null && obj2 != null && obj != null) {
                if (MutedVideoView.this.v != 0) {
                    MutedVideoView.this.seekTo(MutedVideoView.this.v);
                }
                MutedVideoView.this.start();
            }
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            MutedVideoView.this.i = surfaceHolder;
            MutedVideoView.this.c();
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            MutedVideoView.this.i = null;
            if (MutedVideoView.this.p != null) {
                MutedVideoView.this.p.hide();
            }
            MutedVideoView.this.a(true);
        }
    };
    private String d = "VideoView";
    private Uri e;
    private Map<String, String> f;
    private int g = 0;
    private int h = 0;
    private SurfaceHolder i = null;
    private MediaPlayer j = null;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private MediaController p;
    private OnCompletionListener q;
    private OnPreparedListener r;
    private int s;
    private OnErrorListener t;
    private OnInfoListener u;
    private int v;
    private boolean w;
    private boolean x;
    private boolean y;
    private Vector<Pair<InputStream, MediaFormat>> z;

    public MutedVideoView(Context context) {
        super(context);
        b();
    }

    public MutedVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        b();
    }

    public MutedVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    @TargetApi(21)
    public MutedVideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        b();
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing block: B:22:0x0066, code skipped:
            if (r1 > r6) goto L_0x008a;
     */
    public void onMeasure(int r6, int r7) {
        /*
        r5 = this;
        r0 = r5.l;
        r0 = getDefaultSize(r0, r6);
        r1 = r5.m;
        r1 = getDefaultSize(r1, r7);
        r2 = r5.l;
        if (r2 <= 0) goto L_0x0088;
    L_0x0010:
        r2 = r5.m;
        if (r2 <= 0) goto L_0x0088;
    L_0x0014:
        r0 = android.view.View.MeasureSpec.getMode(r6);
        r6 = android.view.View.MeasureSpec.getSize(r6);
        r1 = android.view.View.MeasureSpec.getMode(r7);
        r7 = android.view.View.MeasureSpec.getSize(r7);
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r0 != r2) goto L_0x004b;
    L_0x0028:
        if (r1 != r2) goto L_0x004b;
    L_0x002a:
        r0 = r5.l;
        r0 = r0 * r7;
        r1 = r5.m;
        r1 = r1 * r6;
        if (r0 >= r1) goto L_0x003b;
    L_0x0032:
        r6 = r5.l;
        r6 = r6 * r7;
        r0 = r5.m;
        r0 = r6 / r0;
        r6 = r0;
        goto L_0x008a;
    L_0x003b:
        r0 = r5.l;
        r0 = r0 * r7;
        r1 = r5.m;
        r1 = r1 * r6;
        if (r0 <= r1) goto L_0x008a;
    L_0x0043:
        r7 = r5.m;
        r7 = r7 * r6;
        r0 = r5.l;
        r1 = r7 / r0;
        goto L_0x0089;
    L_0x004b:
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r0 != r2) goto L_0x005c;
    L_0x004f:
        r0 = r5.m;
        r0 = r0 * r6;
        r2 = r5.l;
        r0 = r0 / r2;
        if (r1 != r3) goto L_0x005a;
    L_0x0057:
        if (r0 <= r7) goto L_0x005a;
    L_0x0059:
        goto L_0x008a;
    L_0x005a:
        r7 = r0;
        goto L_0x008a;
    L_0x005c:
        if (r1 != r2) goto L_0x006b;
    L_0x005e:
        r1 = r5.l;
        r1 = r1 * r7;
        r2 = r5.m;
        r1 = r1 / r2;
        if (r0 != r3) goto L_0x0069;
    L_0x0066:
        if (r1 <= r6) goto L_0x0069;
    L_0x0068:
        goto L_0x008a;
    L_0x0069:
        r6 = r1;
        goto L_0x008a;
    L_0x006b:
        r2 = r5.l;
        r4 = r5.m;
        if (r1 != r3) goto L_0x007a;
    L_0x0071:
        if (r4 <= r7) goto L_0x007a;
    L_0x0073:
        r1 = r5.l;
        r1 = r1 * r7;
        r2 = r5.m;
        r1 = r1 / r2;
        goto L_0x007c;
    L_0x007a:
        r1 = r2;
        r7 = r4;
    L_0x007c:
        if (r0 != r3) goto L_0x0069;
    L_0x007e:
        if (r1 <= r6) goto L_0x0069;
    L_0x0080:
        r7 = r5.m;
        r7 = r7 * r6;
        r0 = r5.l;
        r1 = r7 / r0;
        goto L_0x0089;
    L_0x0088:
        r6 = r0;
    L_0x0089:
        r7 = r1;
    L_0x008a:
        r5.setMeasuredDimension(r6, r7);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaanavideo.MutedVideoView.onMeasure(int, int):void");
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(MutedVideoView.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(MutedVideoView.class.getName());
    }

    private void b() {
        this.l = 0;
        this.m = 0;
        getHolder().addCallback(this.c);
        getHolder().setType(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.z = new Vector();
        this.g = 0;
        this.h = 0;
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        setVideoURI(uri, null);
    }

    public void setVideoURI(Uri uri, Map<String, String> map) {
        this.e = uri;
        this.f = map;
        this.v = 0;
        c();
        requestLayout();
        invalidate();
    }

    public void a() {
        if (this.j != null) {
            this.j.stop();
            this.j.release();
            this.j = null;
            this.g = 0;
            this.h = 0;
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x009c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x008b */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:14:0x008b, B:17:0x009c] */
    /* JADX WARNING: Missing block: B:15:?, code skipped:
            r7.g = -1;
            r7.h = -1;
            r7.C.onError(r7.j, 1, 0);
     */
    /* JADX WARNING: Missing block: B:16:0x009b, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:18:?, code skipped:
            r7.g = -1;
            r7.h = -1;
            r7.C.onError(r7.j, 1, 0);
     */
    /* JADX WARNING: Missing block: B:19:0x00a7, code skipped:
            r7.z.clear();
     */
    /* JADX WARNING: Missing block: B:20:0x00ac, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:21:0x00ad, code skipped:
            r7.z.clear();
     */
    private void c() {
        /*
        r7 = this;
        r0 = r7.e;
        if (r0 == 0) goto L_0x00b3;
    L_0x0004:
        r0 = r7.i;
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        goto L_0x00b3;
    L_0x000a:
        r0 = 0;
        r7.a(r0);
        r1 = -1;
        r2 = 1;
        r3 = new android.media.MediaPlayer;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3.<init>();	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r7.j = r3;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r7.getContext();	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3 = r7.k;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        if (r3 == 0) goto L_0x0026;
    L_0x001e:
        r3 = r7.j;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r4 = r7.k;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3.setAudioSessionId(r4);	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        goto L_0x002e;
    L_0x0026:
        r3 = r7.j;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3 = r3.getAudioSessionId();	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r7.k = r3;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
    L_0x002e:
        r3 = r7.j;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r4 = r7.b;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3.setOnPreparedListener(r4);	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3 = r7.j;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r4 = r7.a;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3.setOnVideoSizeChangedListener(r4);	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3 = r7.j;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r4 = r7.A;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3.setOnCompletionListener(r4);	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3 = r7.j;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r4 = r7.C;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3.setOnErrorListener(r4);	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3 = r7.j;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r4 = r7.B;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3.setOnInfoListener(r4);	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3 = r7.j;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r4 = r7.D;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3.setOnBufferingUpdateListener(r4);	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r7.s = r0;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3 = r7.j;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r4 = r7.getContext();	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r5 = r7.e;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r6 = r7.f;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3.setDataSource(r4, r5, r6);	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3 = r7.j;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r4 = r7.i;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3.setDisplay(r4);	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3 = r7.j;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r4 = 3;
        r3.setAudioStreamType(r4);	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3 = r7.j;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3.setScreenOnWhilePlaying(r2);	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3 = r7.j;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r3.prepareAsync();	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r7.g = r2;	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r7.d();	 Catch:{ IOException -> 0x009c, IllegalArgumentException -> 0x008b }
        r0 = r7.z;
        r0.clear();
        return;
    L_0x0089:
        r0 = move-exception;
        goto L_0x00ad;
    L_0x008b:
        r7.g = r1;	 Catch:{ all -> 0x0089 }
        r7.h = r1;	 Catch:{ all -> 0x0089 }
        r1 = r7.C;	 Catch:{ all -> 0x0089 }
        r3 = r7.j;	 Catch:{ all -> 0x0089 }
        r1.onError(r3, r2, r0);	 Catch:{ all -> 0x0089 }
        r0 = r7.z;
        r0.clear();
        return;
    L_0x009c:
        r7.g = r1;	 Catch:{ all -> 0x0089 }
        r7.h = r1;	 Catch:{ all -> 0x0089 }
        r1 = r7.C;	 Catch:{ all -> 0x0089 }
        r3 = r7.j;	 Catch:{ all -> 0x0089 }
        r1.onError(r3, r2, r0);	 Catch:{ all -> 0x0089 }
        r0 = r7.z;
        r0.clear();
        return;
    L_0x00ad:
        r1 = r7.z;
        r1.clear();
        throw r0;
    L_0x00b3:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaanavideo.MutedVideoView.c():void");
    }

    public void setMediaController(MediaController mediaController) {
        if (this.p != null) {
            this.p.hide();
        }
        this.p = mediaController;
        d();
    }

    private void d() {
        if (this.j != null && this.p != null) {
            this.p.setMediaPlayer(this);
            this.p.setAnchorView(getParent() instanceof View ? (View) getParent() : this);
            this.p.setEnabled(f());
        }
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.r = onPreparedListener;
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.q = onCompletionListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.t = onErrorListener;
    }

    public void setOnInfoListener(OnInfoListener onInfoListener) {
        this.u = onInfoListener;
    }

    private void a(boolean z) {
        if (this.j != null) {
            this.j.reset();
            this.j.release();
            this.j = null;
            this.z.clear();
            this.g = 0;
            if (z) {
                this.h = 0;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (f() && this.p != null) {
            e();
        }
        return false;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (f() && this.p != null) {
            e();
        }
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = (i == 4 || i == 24 || i == 25 || i == 164 || i == 82 || i == 5 || i == 6) ? false : true;
        if (f() && z && this.p != null) {
            if (i == 79 || i == 85) {
                if (this.j.isPlaying()) {
                    pause();
                    this.p.show();
                } else {
                    start();
                    this.p.hide();
                }
                return true;
            } else if (i == 126) {
                if (!this.j.isPlaying()) {
                    start();
                    this.p.hide();
                }
                return true;
            } else if (i == 86 || i == 127) {
                if (this.j.isPlaying()) {
                    pause();
                    this.p.show();
                }
                return true;
            } else {
                e();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void e() {
        if (this.p.isShowing()) {
            this.p.hide();
        } else {
            this.p.show();
        }
    }

    public void start() {
        if (f()) {
            this.j.start();
            this.g = 3;
        }
        this.h = 3;
    }

    public void pause() {
        if (f() && this.j.isPlaying()) {
            this.j.pause();
            this.g = 4;
        }
        this.h = 4;
    }

    public int getDuration() {
        return f() ? this.j.getDuration() : -1;
    }

    public int getCurrentPosition() {
        return f() ? this.j.getCurrentPosition() : 0;
    }

    public void seekTo(int i) {
        if (f()) {
            this.j.seekTo(i);
            this.v = 0;
            return;
        }
        this.v = i;
    }

    public boolean isPlaying() {
        return f() && this.j.isPlaying();
    }

    public int getBufferPercentage() {
        return this.j != null ? this.s : 0;
    }

    private boolean f() {
        return (this.j == null || this.g == -1 || this.g == 0 || this.g == 1) ? false : true;
    }

    public boolean canPause() {
        return this.w;
    }

    public boolean canSeekBackward() {
        return this.x;
    }

    public boolean canSeekForward() {
        return this.y;
    }

    public int getAudioSessionId() {
        if (this.k == 0) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.k = mediaPlayer.getAudioSessionId();
            mediaPlayer.release();
        }
        return this.k;
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
