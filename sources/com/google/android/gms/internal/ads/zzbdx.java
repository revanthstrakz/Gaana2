package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

@zzark
@TargetApi(14)
public final class zzbdx extends Thread implements OnFrameAvailableListener, zzbdw {
    private static final float[] zzesl = new float[]{-1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f};
    private final float[] zzesi;
    private final zzbdu zzesm;
    private final float[] zzesn;
    private final float[] zzeso;
    private final float[] zzesp;
    private final float[] zzesq;
    private final float[] zzesr;
    private final float[] zzess;
    private float zzest;
    private float zzesu;
    private float zzesv;
    private SurfaceTexture zzesw;
    private SurfaceTexture zzesx;
    private int zzesy;
    private int zzesz;
    private int zzeta;
    private FloatBuffer zzetb = ByteBuffer.allocateDirect(zzesl.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
    private final CountDownLatch zzetc;
    private final Object zzetd;
    private EGL10 zzete;
    private EGLDisplay zzetf;
    private EGLContext zzetg;
    private EGLSurface zzeth;
    private volatile boolean zzeti;
    private volatile boolean zzetj;
    private int zzvt;
    private int zzvu;

    public zzbdx(Context context) {
        super("SphericalVideoProcessor");
        this.zzetb.put(zzesl).position(0);
        this.zzesi = new float[9];
        this.zzesn = new float[9];
        this.zzeso = new float[9];
        this.zzesp = new float[9];
        this.zzesq = new float[9];
        this.zzesr = new float[9];
        this.zzess = new float[9];
        this.zzest = Float.NaN;
        this.zzesm = new zzbdu(context);
        this.zzesm.zza((zzbdw) this);
        this.zzetc = new CountDownLatch(1);
        this.zzetd = new Object();
    }

    public final void zza(SurfaceTexture surfaceTexture, int i, int i2) {
        this.zzvt = i;
        this.zzvu = i2;
        this.zzesx = surfaceTexture;
    }

    public final void zzo(int i, int i2) {
        synchronized (this.zzetd) {
            this.zzvt = i;
            this.zzvu = i2;
            this.zzeti = true;
            this.zzetd.notifyAll();
        }
    }

    public final void zzabq() {
        synchronized (this.zzetd) {
            this.zzetj = true;
            this.zzesx = null;
            this.zzetd.notifyAll();
        }
    }

    public final SurfaceTexture zzabr() {
        if (this.zzesx == null) {
            return null;
        }
        try {
            this.zzetc.await();
        } catch (InterruptedException unused) {
        }
        return this.zzesw;
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.zzeta++;
        synchronized (this.zzetd) {
            this.zzetd.notifyAll();
        }
    }

    public final void zzvu() {
        synchronized (this.zzetd) {
            this.zzetd.notifyAll();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x01e2  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01e0  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x03af  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x03aa  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01e0  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01e2  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x01e5 A:{SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x03aa  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x03af  */
    /* JADX WARNING: Missing exception handler attribute for start block: B:100:0x036f */
    public final void run() {
        /*
        r14 = this;
        r0 = r14.zzesx;
        if (r0 != 0) goto L_0x000f;
    L_0x0004:
        r0 = "SphericalVideoProcessor started with no output texture.";
        com.google.android.gms.internal.ads.zzbbd.e(r0);
        r0 = r14.zzetc;
        r0.countDown();
        return;
    L_0x000f:
        r0 = javax.microedition.khronos.egl.EGLContext.getEGL();
        r0 = (javax.microedition.khronos.egl.EGL10) r0;
        r14.zzete = r0;
        r0 = r14.zzete;
        r1 = javax.microedition.khronos.egl.EGL10.EGL_DEFAULT_DISPLAY;
        r0 = r0.eglGetDisplay(r1);
        r14.zzetf = r0;
        r0 = r14.zzetf;
        r1 = javax.microedition.khronos.egl.EGL10.EGL_NO_DISPLAY;
        r2 = 3;
        r3 = 2;
        r4 = 0;
        r5 = 1;
        r6 = 0;
        if (r0 != r1) goto L_0x002f;
    L_0x002c:
        r0 = r6;
        goto L_0x00a4;
    L_0x002f:
        r0 = new int[r3];
        r1 = r14.zzete;
        r7 = r14.zzetf;
        r0 = r1.eglInitialize(r7, r0);
        if (r0 != 0) goto L_0x003c;
    L_0x003b:
        goto L_0x002c;
    L_0x003c:
        r0 = new int[r5];
        r1 = new javax.microedition.khronos.egl.EGLConfig[r5];
        r7 = 11;
        r9 = new int[r7];
        r9 = {12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344};
        r7 = r14.zzete;
        r8 = r14.zzetf;
        r11 = 1;
        r10 = r1;
        r12 = r0;
        r7 = r7.eglChooseConfig(r8, r9, r10, r11, r12);
        if (r7 == 0) goto L_0x005b;
    L_0x0054:
        r0 = r0[r6];
        if (r0 <= 0) goto L_0x005b;
    L_0x0058:
        r0 = r1[r6];
        goto L_0x005c;
    L_0x005b:
        r0 = r4;
    L_0x005c:
        if (r0 != 0) goto L_0x005f;
    L_0x005e:
        goto L_0x002c;
    L_0x005f:
        r1 = new int[r2];
        r1 = {12440, 2, 12344};
        r7 = r14.zzete;
        r8 = r14.zzetf;
        r9 = javax.microedition.khronos.egl.EGL10.EGL_NO_CONTEXT;
        r1 = r7.eglCreateContext(r8, r0, r9, r1);
        r14.zzetg = r1;
        r1 = r14.zzetg;
        if (r1 == 0) goto L_0x002c;
    L_0x0074:
        r1 = r14.zzetg;
        r7 = javax.microedition.khronos.egl.EGL10.EGL_NO_CONTEXT;
        if (r1 != r7) goto L_0x007b;
    L_0x007a:
        goto L_0x002c;
    L_0x007b:
        r1 = r14.zzete;
        r7 = r14.zzetf;
        r8 = r14.zzesx;
        r0 = r1.eglCreateWindowSurface(r7, r0, r8, r4);
        r14.zzeth = r0;
        r0 = r14.zzeth;
        if (r0 == 0) goto L_0x002c;
    L_0x008b:
        r0 = r14.zzeth;
        r1 = javax.microedition.khronos.egl.EGL10.EGL_NO_SURFACE;
        if (r0 != r1) goto L_0x0092;
    L_0x0091:
        goto L_0x002c;
    L_0x0092:
        r0 = r14.zzete;
        r1 = r14.zzetf;
        r7 = r14.zzeth;
        r8 = r14.zzeth;
        r9 = r14.zzetg;
        r0 = r0.eglMakeCurrent(r1, r7, r8, r9);
        if (r0 != 0) goto L_0x00a3;
    L_0x00a2:
        goto L_0x002c;
    L_0x00a3:
        r0 = r5;
    L_0x00a4:
        r1 = 35633; // 0x8b31 float:4.9932E-41 double:1.7605E-319;
        r7 = com.google.android.gms.internal.ads.zzaan.zzcsq;
        r8 = com.google.android.gms.internal.ads.zzwu.zzpz();
        r8 = r8.zzd(r7);
        r8 = (java.lang.String) r8;
        r9 = r7.zzqv();
        r8 = r8.equals(r9);
        if (r8 != 0) goto L_0x00c8;
    L_0x00bd:
        r8 = com.google.android.gms.internal.ads.zzwu.zzpz();
        r7 = r8.zzd(r7);
        r7 = (java.lang.String) r7;
        goto L_0x00ca;
    L_0x00c8:
        r7 = "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}";
    L_0x00ca:
        r1 = zze(r1, r7);
        if (r1 != 0) goto L_0x00d3;
    L_0x00d0:
        r8 = r6;
        goto L_0x0155;
    L_0x00d3:
        r7 = 35632; // 0x8b30 float:4.9931E-41 double:1.76045E-319;
        r8 = com.google.android.gms.internal.ads.zzaan.zzcsr;
        r9 = com.google.android.gms.internal.ads.zzwu.zzpz();
        r9 = r9.zzd(r8);
        r9 = (java.lang.String) r9;
        r10 = r8.zzqv();
        r9 = r9.equals(r10);
        if (r9 != 0) goto L_0x00f7;
    L_0x00ec:
        r9 = com.google.android.gms.internal.ads.zzwu.zzpz();
        r8 = r9.zzd(r8);
        r8 = (java.lang.String) r8;
        goto L_0x00f9;
    L_0x00f7:
        r8 = "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}";
    L_0x00f9:
        r7 = zze(r7, r8);
        if (r7 != 0) goto L_0x0100;
    L_0x00ff:
        goto L_0x00d0;
    L_0x0100:
        r8 = android.opengl.GLES20.glCreateProgram();
        r9 = "createProgram";
        zzes(r9);
        if (r8 == 0) goto L_0x0155;
    L_0x010b:
        android.opengl.GLES20.glAttachShader(r8, r1);
        r1 = "attachShader";
        zzes(r1);
        android.opengl.GLES20.glAttachShader(r8, r7);
        r1 = "attachShader";
        zzes(r1);
        android.opengl.GLES20.glLinkProgram(r8);
        r1 = "linkProgram";
        zzes(r1);
        r1 = new int[r5];
        r7 = 35714; // 0x8b82 float:5.0046E-41 double:1.7645E-319;
        android.opengl.GLES20.glGetProgramiv(r8, r7, r1, r6);
        r7 = "getProgramiv";
        zzes(r7);
        r1 = r1[r6];
        if (r1 == r5) goto L_0x014d;
    L_0x0134:
        r1 = "SphericalVideoRenderer";
        r7 = "Could not link program: ";
        android.util.Log.e(r1, r7);
        r1 = "SphericalVideoRenderer";
        r7 = android.opengl.GLES20.glGetProgramInfoLog(r8);
        android.util.Log.e(r1, r7);
        android.opengl.GLES20.glDeleteProgram(r8);
        r1 = "deleteProgram";
        zzes(r1);
        goto L_0x00d0;
    L_0x014d:
        android.opengl.GLES20.glValidateProgram(r8);
        r1 = "validateProgram";
        zzes(r1);
    L_0x0155:
        r14.zzesy = r8;
        r1 = r14.zzesy;
        android.opengl.GLES20.glUseProgram(r1);
        r1 = "useProgram";
        zzes(r1);
        r1 = r14.zzesy;
        r7 = "aPosition";
        r1 = android.opengl.GLES20.glGetAttribLocation(r1, r7);
        r9 = 3;
        r10 = 5126; // 0x1406 float:7.183E-42 double:2.5326E-320;
        r11 = 0;
        r12 = 12;
        r13 = r14.zzetb;
        r8 = r1;
        android.opengl.GLES20.glVertexAttribPointer(r8, r9, r10, r11, r12, r13);
        r7 = "vertexAttribPointer";
        zzes(r7);
        android.opengl.GLES20.glEnableVertexAttribArray(r1);
        r1 = "enableVertexAttribArray";
        zzes(r1);
        r1 = new int[r5];
        android.opengl.GLES20.glGenTextures(r5, r1, r6);
        r7 = "genTextures";
        zzes(r7);
        r1 = r1[r6];
        r7 = 36197; // 0x8d65 float:5.0723E-41 double:1.78837E-319;
        android.opengl.GLES20.glBindTexture(r7, r1);
        r8 = "bindTextures";
        zzes(r8);
        r8 = 10240; // 0x2800 float:1.4349E-41 double:5.059E-320;
        r9 = 9729; // 0x2601 float:1.3633E-41 double:4.807E-320;
        android.opengl.GLES20.glTexParameteri(r7, r8, r9);
        r8 = "texParameteri";
        zzes(r8);
        r8 = 10241; // 0x2801 float:1.435E-41 double:5.0597E-320;
        android.opengl.GLES20.glTexParameteri(r7, r8, r9);
        r8 = "texParameteri";
        zzes(r8);
        r8 = 10242; // 0x2802 float:1.4352E-41 double:5.06E-320;
        r9 = 33071; // 0x812f float:4.6342E-41 double:1.6339E-319;
        android.opengl.GLES20.glTexParameteri(r7, r8, r9);
        r8 = "texParameteri";
        zzes(r8);
        r8 = 10243; // 0x2803 float:1.4354E-41 double:5.0607E-320;
        android.opengl.GLES20.glTexParameteri(r7, r8, r9);
        r7 = "texParameteri";
        zzes(r7);
        r7 = r14.zzesy;
        r8 = "uVMat";
        r7 = android.opengl.GLES20.glGetUniformLocation(r7, r8);
        r14.zzesz = r7;
        r7 = 9;
        r7 = new float[r7];
        r7 = {1065353216, 0, 0, 0, 1065353216, 0, 0, 0, 1065353216};
        r8 = r14.zzesz;
        android.opengl.GLES20.glUniformMatrix3fv(r8, r5, r6, r7, r6);
        r7 = r14.zzesy;
        if (r7 == 0) goto L_0x01e2;
    L_0x01e0:
        r7 = r5;
        goto L_0x01e3;
    L_0x01e2:
        r7 = r6;
    L_0x01e3:
        if (r0 == 0) goto L_0x0394;
    L_0x01e5:
        if (r7 != 0) goto L_0x01e9;
    L_0x01e7:
        goto L_0x0394;
    L_0x01e9:
        r0 = new android.graphics.SurfaceTexture;
        r0.<init>(r1);
        r14.zzesw = r0;
        r0 = r14.zzesw;
        r0.setOnFrameAvailableListener(r14);
        r0 = r14.zzetc;
        r0.countDown();
        r0 = r14.zzesm;
        r0.start();
        r14.zzeti = r5;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
    L_0x0201:
        r0 = r14.zzetj;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        if (r0 != 0) goto L_0x033e;
    L_0x0205:
        r0 = r14.zzeta;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        if (r0 <= 0) goto L_0x0214;
    L_0x0209:
        r0 = r14.zzesw;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0.updateTexImage();	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r14.zzeta;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r0 - r5;
        r14.zzeta = r0;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        goto L_0x0205;
    L_0x0214:
        r0 = r14.zzesm;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r1 = r14.zzesi;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r0.zza(r1);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r1 = 1070141403; // 0x3fc90fdb float:1.5707964 double:5.287201034E-315;
        r7 = 5;
        r8 = 4;
        if (r0 == 0) goto L_0x028b;
    L_0x0223:
        r0 = r14.zzest;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = java.lang.Float.isNaN(r0);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        if (r0 == 0) goto L_0x0280;
    L_0x022b:
        r0 = r14.zzesi;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = new float[r2];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = {0, 1065353216, 0};	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r10 = new float[r2];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r11 = r0[r6];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r12 = r9[r6];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r11 = r11 * r12;
        r12 = r0[r5];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r13 = r9[r5];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r12 = r12 * r13;
        r11 = r11 + r12;
        r12 = r0[r3];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r13 = r9[r3];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r12 = r12 * r13;
        r11 = r11 + r12;
        r10[r6] = r11;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r11 = r0[r2];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r12 = r9[r6];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r11 = r11 * r12;
        r12 = r0[r8];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r13 = r9[r5];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r12 = r12 * r13;
        r11 = r11 + r12;
        r12 = r0[r7];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r13 = r9[r3];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r12 = r12 * r13;
        r11 = r11 + r12;
        r10[r5] = r11;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r11 = 6;
        r11 = r0[r11];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r12 = r9[r6];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r11 = r11 * r12;
        r12 = 7;
        r12 = r0[r12];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r13 = r9[r5];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r12 = r12 * r13;
        r11 = r11 + r12;
        r12 = 8;
        r0 = r0[r12];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = r9[r3];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r0 * r9;
        r11 = r11 + r0;
        r10[r3] = r11;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r10[r5];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r11 = (double) r0;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r10[r6];	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = (double) r0;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = java.lang.Math.atan2(r11, r9);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = (float) r9;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r0 - r1;
        r0 = -r0;
        r14.zzest = r0;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
    L_0x0280:
        r0 = r14.zzesr;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = r14.zzest;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r10 = r14.zzesu;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = r9 + r10;
        zzb(r0, r9);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        goto L_0x029a;
    L_0x028b:
        r0 = r14.zzesi;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = -1077342245; // 0xffffffffbfc90fdb float:-1.5707964 double:NaN;
        zza(r0, r9);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r14.zzesr;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = r14.zzesu;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        zzb(r0, r9);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
    L_0x029a:
        r0 = r14.zzesn;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        zza(r0, r1);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r14.zzeso;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r1 = r14.zzesr;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = r14.zzesn;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        zza(r0, r1, r9);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r14.zzesp;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r1 = r14.zzesi;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = r14.zzeso;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        zza(r0, r1, r9);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r14.zzesq;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r1 = r14.zzesv;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        zza(r0, r1);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r14.zzess;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r1 = r14.zzesq;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = r14.zzesp;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        zza(r0, r1, r9);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r14.zzesz;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r1 = r14.zzess;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        android.opengl.GLES20.glUniformMatrix3fv(r0, r5, r6, r1, r6);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        android.opengl.GLES20.glDrawArrays(r7, r6, r8);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = "drawArrays";
        zzes(r0);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        android.opengl.GLES20.glFinish();	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r14.zzete;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r1 = r14.zzetf;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r7 = r14.zzeth;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0.eglSwapBuffers(r1, r7);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r14.zzeti;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        if (r0 == 0) goto L_0x0324;
    L_0x02e0:
        r0 = r14.zzvt;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r1 = r14.zzvu;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        android.opengl.GLES20.glViewport(r6, r6, r0, r1);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = "viewport";
        zzes(r0);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r14.zzesy;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r1 = "uFOVx";
        r0 = android.opengl.GLES20.glGetUniformLocation(r0, r1);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r1 = r14.zzesy;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r7 = "uFOVy";
        r1 = android.opengl.GLES20.glGetUniformLocation(r1, r7);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r7 = r14.zzvt;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r8 = r14.zzvu;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = 1063216883; // 0x3f5f66f3 float:0.87266463 double:5.25298936E-315;
        if (r7 <= r8) goto L_0x0314;
    L_0x0305:
        android.opengl.GLES20.glUniform1f(r0, r9);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = r14.zzvu;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = (float) r0;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = r9 * r0;
        r0 = r14.zzvt;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r0 = (float) r0;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r9 = r9 / r0;
        android.opengl.GLES20.glUniform1f(r1, r9);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        goto L_0x0322;
    L_0x0314:
        r7 = r14.zzvt;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r7 = (float) r7;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r7 = r7 * r9;
        r8 = r14.zzvu;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r8 = (float) r8;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        r7 = r7 / r8;
        android.opengl.GLES20.glUniform1f(r0, r7);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
        android.opengl.GLES20.glUniform1f(r1, r9);	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
    L_0x0322:
        r14.zzeti = r6;	 Catch:{ IllegalStateException -> 0x036f, Throwable -> 0x0350 }
    L_0x0324:
        r0 = r14.zzetd;	 Catch:{ InterruptedException -> 0x0201 }
        monitor-enter(r0);	 Catch:{ InterruptedException -> 0x0201 }
        r1 = r14.zzetj;	 Catch:{ all -> 0x033b }
        if (r1 != 0) goto L_0x0338;
    L_0x032b:
        r1 = r14.zzeti;	 Catch:{ all -> 0x033b }
        if (r1 != 0) goto L_0x0338;
    L_0x032f:
        r1 = r14.zzeta;	 Catch:{ all -> 0x033b }
        if (r1 != 0) goto L_0x0338;
    L_0x0333:
        r1 = r14.zzetd;	 Catch:{ all -> 0x033b }
        r1.wait();	 Catch:{ all -> 0x033b }
    L_0x0338:
        monitor-exit(r0);	 Catch:{ all -> 0x033b }
        goto L_0x0201;
    L_0x033b:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x033b }
        throw r1;	 Catch:{ InterruptedException -> 0x0201 }
    L_0x033e:
        r0 = r14.zzesm;
        r0.stop();
        r0 = r14.zzesw;
        r0.setOnFrameAvailableListener(r4);
        r14.zzesw = r4;
        r14.zzabs();
        return;
    L_0x034e:
        r0 = move-exception;
        goto L_0x0384;
    L_0x0350:
        r0 = move-exception;
        r1 = "SphericalVideoProcessor died.";
        com.google.android.gms.internal.ads.zzbbd.zzb(r1, r0);	 Catch:{ all -> 0x034e }
        r1 = com.google.android.gms.ads.internal.zzbv.zzlj();	 Catch:{ all -> 0x034e }
        r2 = "SphericalVideoProcessor.run.2";
        r1.zza(r0, r2);	 Catch:{ all -> 0x034e }
        r0 = r14.zzesm;
        r0.stop();
        r0 = r14.zzesw;
        r0.setOnFrameAvailableListener(r4);
        r14.zzesw = r4;
        r14.zzabs();
        return;
    L_0x036f:
        r0 = "SphericalVideoProcessor halted unexpectedly.";
        com.google.android.gms.internal.ads.zzbbd.zzeo(r0);	 Catch:{ all -> 0x034e }
        r0 = r14.zzesm;
        r0.stop();
        r0 = r14.zzesw;
        r0.setOnFrameAvailableListener(r4);
        r14.zzesw = r4;
        r14.zzabs();
        return;
    L_0x0384:
        r1 = r14.zzesm;
        r1.stop();
        r1 = r14.zzesw;
        r1.setOnFrameAvailableListener(r4);
        r14.zzesw = r4;
        r14.zzabs();
        throw r0;
    L_0x0394:
        r0 = r14.zzete;
        r0 = r0.eglGetError();
        r0 = android.opengl.GLUtils.getEGLErrorString(r0);
        r1 = "EGL initialization failed: ";
        r0 = java.lang.String.valueOf(r0);
        r2 = r0.length();
        if (r2 == 0) goto L_0x03af;
    L_0x03aa:
        r0 = r1.concat(r0);
        goto L_0x03b4;
    L_0x03af:
        r0 = new java.lang.String;
        r0.<init>(r1);
    L_0x03b4:
        com.google.android.gms.internal.ads.zzbbd.e(r0);
        r1 = com.google.android.gms.ads.internal.zzbv.zzlj();
        r2 = new java.lang.Throwable;
        r2.<init>(r0);
        r0 = "SphericalVideoProcessor.run.1";
        r1.zza(r2, r0);
        r14.zzabs();
        r0 = r14.zzetc;
        r0.countDown();
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbdx.run():void");
    }

    public final void zzb(float f, float f2) {
        float f3;
        if (this.zzvt > this.zzvu) {
            f = (f * 1.7453293f) / ((float) this.zzvt);
            f3 = (1.7453293f * f2) / ((float) this.zzvt);
        } else {
            f = (f * 1.7453293f) / ((float) this.zzvu);
            f3 = (1.7453293f * f2) / ((float) this.zzvu);
        }
        this.zzesu -= f;
        this.zzesv -= f3;
        if (this.zzesv < -1.5707964f) {
            this.zzesv = -1.5707964f;
        }
        if (this.zzesv > 1.5707964f) {
            this.zzesv = 1.5707964f;
        }
    }

    private static void zza(float[] fArr, float[] fArr2, float[] fArr3) {
        fArr[0] = ((fArr2[0] * fArr3[0]) + (fArr2[1] * fArr3[3])) + (fArr2[2] * fArr3[6]);
        fArr[1] = ((fArr2[0] * fArr3[1]) + (fArr2[1] * fArr3[4])) + (fArr2[2] * fArr3[7]);
        fArr[2] = ((fArr2[0] * fArr3[2]) + (fArr2[1] * fArr3[5])) + (fArr2[2] * fArr3[8]);
        fArr[3] = ((fArr2[3] * fArr3[0]) + (fArr2[4] * fArr3[3])) + (fArr2[5] * fArr3[6]);
        fArr[4] = ((fArr2[3] * fArr3[1]) + (fArr2[4] * fArr3[4])) + (fArr2[5] * fArr3[7]);
        fArr[5] = ((fArr2[3] * fArr3[2]) + (fArr2[4] * fArr3[5])) + (fArr2[5] * fArr3[8]);
        fArr[6] = ((fArr2[6] * fArr3[0]) + (fArr2[7] * fArr3[3])) + (fArr2[8] * fArr3[6]);
        fArr[7] = ((fArr2[6] * fArr3[1]) + (fArr2[7] * fArr3[4])) + (fArr2[8] * fArr3[7]);
        fArr[8] = ((fArr2[6] * fArr3[2]) + (fArr2[7] * fArr3[5])) + (fArr2[8] * fArr3[8]);
    }

    private static void zza(float[] fArr, float f) {
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        double d = (double) f;
        fArr[4] = (float) Math.cos(d);
        fArr[5] = (float) (-Math.sin(d));
        fArr[6] = 0.0f;
        fArr[7] = (float) Math.sin(d);
        fArr[8] = (float) Math.cos(d);
    }

    private static void zzb(float[] fArr, float f) {
        double d = (double) f;
        fArr[0] = (float) Math.cos(d);
        fArr[1] = (float) (-Math.sin(d));
        fArr[2] = 0.0f;
        fArr[3] = (float) Math.sin(d);
        fArr[4] = (float) Math.cos(d);
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
    }

    private static int zze(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        zzes("createShader");
        if (glCreateShader == 0) {
            return glCreateShader;
        }
        GLES20.glShaderSource(glCreateShader, str);
        zzes("shaderSource");
        GLES20.glCompileShader(glCreateShader);
        zzes("compileShader");
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        zzes("getShaderiv");
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        StringBuilder stringBuilder = new StringBuilder(37);
        stringBuilder.append("Could not compile shader ");
        stringBuilder.append(i);
        stringBuilder.append(":");
        Log.e("SphericalVideoRenderer", stringBuilder.toString());
        Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(glCreateShader));
        GLES20.glDeleteShader(glCreateShader);
        zzes("deleteShader");
        return 0;
    }

    @VisibleForTesting
    private final boolean zzabs() {
        boolean z = false;
        if (!(this.zzeth == null || this.zzeth == EGL10.EGL_NO_SURFACE)) {
            z = this.zzete.eglDestroySurface(this.zzetf, this.zzeth) | (this.zzete.eglMakeCurrent(this.zzetf, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT) | 0);
            this.zzeth = null;
        }
        if (this.zzetg != null) {
            z |= this.zzete.eglDestroyContext(this.zzetf, this.zzetg);
            this.zzetg = null;
        }
        if (this.zzetf == null) {
            return z;
        }
        int eglTerminate = z | this.zzete.eglTerminate(this.zzetf);
        this.zzetf = null;
        return eglTerminate;
    }

    private static void zzes(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            StringBuilder stringBuilder = new StringBuilder(21 + String.valueOf(str).length());
            stringBuilder.append(str);
            stringBuilder.append(": glError ");
            stringBuilder.append(glGetError);
            Log.e("SphericalVideoRenderer", stringBuilder.toString());
        }
    }
}
