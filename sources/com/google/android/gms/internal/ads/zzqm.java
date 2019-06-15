package com.google.android.gms.internal.ads;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

final class zzqm extends HandlerThread implements OnFrameAvailableListener, Callback {
    private Handler handler;
    private final int[] zzbin = new int[1];
    private SurfaceTexture zzbio;
    private Error zzbip;
    private RuntimeException zzbiq;
    private zzqk zzbir;

    public zzqm() {
        super("dummySurface");
    }

    public final zzqk zzk(boolean z) {
        int i;
        start();
        this.handler = new Handler(getLooper(), this);
        synchronized (this) {
            i = 0;
            this.handler.obtainMessage(1, z, 0).sendToTarget();
            while (this.zzbir == null && this.zzbiq == null && this.zzbip == null) {
                try {
                    wait();
                } catch (InterruptedException unused) {
                    i = 1;
                }
            }
        }
        if (i != 0) {
            Thread.currentThread().interrupt();
        }
        if (this.zzbiq != null) {
            throw this.zzbiq;
        } else if (this.zzbip == null) {
            return this.zzbir;
        } else {
            throw this.zzbip;
        }
    }

    public final void release() {
        this.handler.sendEmptyMessage(3);
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.handler.sendEmptyMessage(2);
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                try {
                    int[] iArr;
                    int[] iArr2;
                    boolean z = message.arg1 != 0;
                    EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
                    zzpo.checkState(eglGetDisplay != null, "eglGetDisplay failed");
                    int[] iArr3 = new int[2];
                    zzpo.checkState(EGL14.eglInitialize(eglGetDisplay, iArr3, 0, iArr3, 1), "eglInitialize failed");
                    EGLConfig[] eGLConfigArr = new EGLConfig[1];
                    int[] iArr4 = new int[1];
                    boolean z2 = EGL14.eglChooseConfig(eglGetDisplay, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344}, 0, eGLConfigArr, 0, 1, iArr4, 0) && iArr4[0] > 0 && eGLConfigArr[0] != null;
                    zzpo.checkState(z2, "eglChooseConfig failed");
                    EGLConfig eGLConfig = eGLConfigArr[0];
                    if (z) {
                        iArr = new int[]{12440, 2, 12992, 1, 12344};
                    } else {
                        iArr = new int[]{12440, 2, 12344};
                    }
                    EGLContext eglCreateContext = EGL14.eglCreateContext(eglGetDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, iArr, 0);
                    zzpo.checkState(eglCreateContext != null, "eglCreateContext failed");
                    if (z) {
                        iArr2 = new int[]{12375, 1, 12374, 1, 12992, 1, 12344};
                    } else {
                        iArr2 = new int[]{12375, 1, 12374, 1, 12344};
                    }
                    EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eglGetDisplay, eGLConfig, iArr2, 0);
                    zzpo.checkState(eglCreatePbufferSurface != null, "eglCreatePbufferSurface failed");
                    zzpo.checkState(EGL14.eglMakeCurrent(eglGetDisplay, eglCreatePbufferSurface, eglCreatePbufferSurface, eglCreateContext), "eglMakeCurrent failed");
                    GLES20.glGenTextures(1, this.zzbin, 0);
                    this.zzbio = new SurfaceTexture(this.zzbin[0]);
                    this.zzbio.setOnFrameAvailableListener(this);
                    this.zzbir = new zzqk(this, this.zzbio, z, null);
                    synchronized (this) {
                        notify();
                    }
                } catch (RuntimeException e) {
                    Log.e("DummySurface", "Failed to initialize dummy surface", e);
                    this.zzbiq = e;
                    synchronized (this) {
                        notify();
                    }
                } catch (Error e2) {
                    try {
                        Log.e("DummySurface", "Failed to initialize dummy surface", e2);
                        this.zzbip = e2;
                        synchronized (this) {
                            notify();
                        }
                    } catch (Throwable th) {
                        synchronized (this) {
                            notify();
                        }
                    }
                }
                return true;
            case 2:
                this.zzbio.updateTexImage();
                return true;
            case 3:
                try {
                    this.zzbio.release();
                    this.zzbir = null;
                    this.zzbio = null;
                    GLES20.glDeleteTextures(1, this.zzbin, 0);
                    quit();
                } catch (Throwable th2) {
                    try {
                        Log.e("DummySurface", "Failed to release dummy surface", th2);
                    } finally {
                        quit();
                    }
                }
                return true;
            default:
                return true;
        }
    }
}
