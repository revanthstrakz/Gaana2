package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import com.google.android.gms.ads.internal.zzbv;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@zzark
@TargetApi(14)
public final class zzbcx extends zzbdi implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnVideoSizeChangedListener, SurfaceTextureListener {
    private static final Map<Integer, String> zzeqe = new HashMap();
    private final zzbea zzeqf;
    private final boolean zzeqg;
    private int zzeqh = 0;
    private int zzeqi = 0;
    private MediaPlayer zzeqj;
    private Uri zzeqk;
    private int zzeql;
    private int zzeqm;
    private int zzeqn;
    private int zzeqo;
    private int zzeqp;
    private zzbdx zzeqq;
    private boolean zzeqr;
    private int zzeqs;
    private zzbdh zzeqt;

    public zzbcx(Context context, boolean z, boolean z2, zzbdy zzbdy, zzbea zzbea) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzeqf = zzbea;
        this.zzeqr = z;
        this.zzeqg = z2;
        this.zzeqf.zzb(this);
    }

    public final String zzaaz() {
        String str = "MediaPlayer";
        String valueOf = String.valueOf(this.zzeqr ? " spherical" : "");
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public final void zza(zzbdh zzbdh) {
        this.zzeqt = zzbdh;
    }

    public final void setVideoPath(String str) {
        Uri parse = Uri.parse(str);
        zzty zzd = zzty.zzd(parse);
        if (zzd != null) {
            parse = Uri.parse(zzd.url);
        }
        this.zzeqk = parse;
        this.zzeqs = 0;
        zzaba();
        requestLayout();
        invalidate();
    }

    public final void stop() {
        zzaxz.v("AdMediaPlayerView stop");
        if (this.zzeqj != null) {
            this.zzeqj.stop();
            this.zzeqj.release();
            this.zzeqj = null;
            zzcx(0);
            this.zzeqi = 0;
        }
        this.zzeqf.onStop();
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder(57);
        stringBuilder.append("AdMediaPlayerView size changed: ");
        stringBuilder.append(i);
        stringBuilder.append(" x ");
        stringBuilder.append(i2);
        zzaxz.v(stringBuilder.toString());
        this.zzeql = mediaPlayer.getVideoWidth();
        this.zzeqm = mediaPlayer.getVideoHeight();
        if (this.zzeql != 0 && this.zzeqm != 0) {
            requestLayout();
        }
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        zzaxz.v("AdMediaPlayerView prepared");
        zzcx(2);
        this.zzeqf.zzcg();
        zzayh.zzelc.post(new zzbcz(this));
        this.zzeql = mediaPlayer.getVideoWidth();
        this.zzeqm = mediaPlayer.getVideoHeight();
        if (this.zzeqs != 0) {
            seekTo(this.zzeqs);
        }
        zzabb();
        int i = this.zzeql;
        int i2 = this.zzeqm;
        StringBuilder stringBuilder = new StringBuilder(62);
        stringBuilder.append("AdMediaPlayerView stream dimensions: ");
        stringBuilder.append(i);
        stringBuilder.append(" x ");
        stringBuilder.append(i2);
        zzbbd.zzen(stringBuilder.toString());
        if (this.zzeqi == 3) {
            play();
        }
        zzabd();
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        zzaxz.v("AdMediaPlayerView completion");
        zzcx(5);
        this.zzeqi = 5;
        zzayh.zzelc.post(new zzbda(this));
    }

    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzeqe.get(Integer.valueOf(i));
        String str2 = (String) zzeqe.get(Integer.valueOf(i2));
        StringBuilder stringBuilder = new StringBuilder((37 + String.valueOf(str).length()) + String.valueOf(str2).length());
        stringBuilder.append("AdMediaPlayerView MediaPlayer info: ");
        stringBuilder.append(str);
        stringBuilder.append(":");
        stringBuilder.append(str2);
        zzaxz.v(stringBuilder.toString());
        return true;
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzeqe.get(Integer.valueOf(i));
        String str2 = (String) zzeqe.get(Integer.valueOf(i2));
        StringBuilder stringBuilder = new StringBuilder((38 + String.valueOf(str).length()) + String.valueOf(str2).length());
        stringBuilder.append("AdMediaPlayerView MediaPlayer error: ");
        stringBuilder.append(str);
        stringBuilder.append(":");
        stringBuilder.append(str2);
        zzbbd.zzeo(stringBuilder.toString());
        zzcx(-1);
        this.zzeqi = -1;
        zzayh.zzelc.post(new zzbdb(this, str, str2));
        return true;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzeqn = i;
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzaxz.v("AdMediaPlayerView surface created");
        zzaba();
        zzayh.zzelc.post(new zzbdc(this));
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        zzaxz.v("AdMediaPlayerView surface changed");
        Object obj = null;
        Object obj2 = this.zzeqi == 3 ? 1 : null;
        if (this.zzeql == i && this.zzeqm == i2) {
            obj = 1;
        }
        if (!(this.zzeqj == null || obj2 == null || obj == null)) {
            if (this.zzeqs != 0) {
                seekTo(this.zzeqs);
            }
            play();
        }
        if (this.zzeqq != null) {
            this.zzeqq.zzo(i, i2);
        }
        zzayh.zzelc.post(new zzbdd(this, i, i2));
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzaxz.v("AdMediaPlayerView surface destroyed");
        if (this.zzeqj != null && this.zzeqs == 0) {
            this.zzeqs = this.zzeqj.getCurrentPosition();
        }
        if (this.zzeqq != null) {
            this.zzeqq.zzabq();
        }
        zzayh.zzelc.post(new zzbde(this));
        zzar(true);
        return true;
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzeqf.zzc(this);
        this.zzera.zza(surfaceTexture, this.zzeqt);
    }

    /* Access modifiers changed, original: protected|final */
    public final void onWindowVisibilityChanged(int i) {
        StringBuilder stringBuilder = new StringBuilder(58);
        stringBuilder.append("AdMediaPlayerView window visibility changed to ");
        stringBuilder.append(i);
        zzaxz.v(stringBuilder.toString());
        zzayh.zzelc.post(new zzbcy(this, i));
        super.onWindowVisibilityChanged(i);
    }

    /* Access modifiers changed, original: protected|final */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Missing block: B:24:0x006a, code skipped:
            if (r1 > r6) goto L_0x008e;
     */
    public final void onMeasure(int r6, int r7) {
        /*
        r5 = this;
        r0 = r5.zzeql;
        r0 = getDefaultSize(r0, r6);
        r1 = r5.zzeqm;
        r1 = getDefaultSize(r1, r7);
        r2 = r5.zzeql;
        if (r2 <= 0) goto L_0x008c;
    L_0x0010:
        r2 = r5.zzeqm;
        if (r2 <= 0) goto L_0x008c;
    L_0x0014:
        r2 = r5.zzeqq;
        if (r2 != 0) goto L_0x008c;
    L_0x0018:
        r0 = android.view.View.MeasureSpec.getMode(r6);
        r6 = android.view.View.MeasureSpec.getSize(r6);
        r1 = android.view.View.MeasureSpec.getMode(r7);
        r7 = android.view.View.MeasureSpec.getSize(r7);
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        if (r0 != r2) goto L_0x004f;
    L_0x002c:
        if (r1 != r2) goto L_0x004f;
    L_0x002e:
        r0 = r5.zzeql;
        r0 = r0 * r7;
        r1 = r5.zzeqm;
        r1 = r1 * r6;
        if (r0 >= r1) goto L_0x003f;
    L_0x0036:
        r6 = r5.zzeql;
        r6 = r6 * r7;
        r0 = r5.zzeqm;
        r0 = r6 / r0;
        r6 = r0;
        goto L_0x008e;
    L_0x003f:
        r0 = r5.zzeql;
        r0 = r0 * r7;
        r1 = r5.zzeqm;
        r1 = r1 * r6;
        if (r0 <= r1) goto L_0x008e;
    L_0x0047:
        r7 = r5.zzeqm;
        r7 = r7 * r6;
        r0 = r5.zzeql;
        r1 = r7 / r0;
        goto L_0x008d;
    L_0x004f:
        r3 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        if (r0 != r2) goto L_0x0060;
    L_0x0053:
        r0 = r5.zzeqm;
        r0 = r0 * r6;
        r2 = r5.zzeql;
        r0 = r0 / r2;
        if (r1 != r3) goto L_0x005e;
    L_0x005b:
        if (r0 <= r7) goto L_0x005e;
    L_0x005d:
        goto L_0x008e;
    L_0x005e:
        r7 = r0;
        goto L_0x008e;
    L_0x0060:
        if (r1 != r2) goto L_0x006f;
    L_0x0062:
        r1 = r5.zzeql;
        r1 = r1 * r7;
        r2 = r5.zzeqm;
        r1 = r1 / r2;
        if (r0 != r3) goto L_0x006d;
    L_0x006a:
        if (r1 <= r6) goto L_0x006d;
    L_0x006c:
        goto L_0x008e;
    L_0x006d:
        r6 = r1;
        goto L_0x008e;
    L_0x006f:
        r2 = r5.zzeql;
        r4 = r5.zzeqm;
        if (r1 != r3) goto L_0x007e;
    L_0x0075:
        if (r4 <= r7) goto L_0x007e;
    L_0x0077:
        r1 = r5.zzeql;
        r1 = r1 * r7;
        r2 = r5.zzeqm;
        r1 = r1 / r2;
        goto L_0x0080;
    L_0x007e:
        r1 = r2;
        r7 = r4;
    L_0x0080:
        if (r0 != r3) goto L_0x006d;
    L_0x0082:
        if (r1 <= r6) goto L_0x006d;
    L_0x0084:
        r7 = r5.zzeqm;
        r7 = r7 * r6;
        r0 = r5.zzeql;
        r1 = r7 / r0;
        goto L_0x008d;
    L_0x008c:
        r6 = r0;
    L_0x008d:
        r7 = r1;
    L_0x008e:
        r5.setMeasuredDimension(r6, r7);
        r0 = r5.zzeqq;
        if (r0 == 0) goto L_0x009a;
    L_0x0095:
        r0 = r5.zzeqq;
        r0.zzo(r6, r7);
    L_0x009a:
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 16;
        if (r0 != r1) goto L_0x00b7;
    L_0x00a0:
        r0 = r5.zzeqo;
        if (r0 <= 0) goto L_0x00a8;
    L_0x00a4:
        r0 = r5.zzeqo;
        if (r0 != r6) goto L_0x00b0;
    L_0x00a8:
        r0 = r5.zzeqp;
        if (r0 <= 0) goto L_0x00b3;
    L_0x00ac:
        r0 = r5.zzeqp;
        if (r0 == r7) goto L_0x00b3;
    L_0x00b0:
        r5.zzabb();
    L_0x00b3:
        r5.zzeqo = r6;
        r5.zzeqp = r7;
    L_0x00b7:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcx.onMeasure(int, int):void");
    }

    public final String toString() {
        String name = getClass().getName();
        String toHexString = Integer.toHexString(hashCode());
        StringBuilder stringBuilder = new StringBuilder((1 + String.valueOf(name).length()) + String.valueOf(toHexString).length());
        stringBuilder.append(name);
        stringBuilder.append("@");
        stringBuilder.append(toHexString);
        return stringBuilder.toString();
    }

    private final void zzaba() {
        zzaxz.v("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzeqk != null && surfaceTexture != null) {
            zzar(false);
            try {
                zzbv.zzlx();
                this.zzeqj = new MediaPlayer();
                this.zzeqj.setOnBufferingUpdateListener(this);
                this.zzeqj.setOnCompletionListener(this);
                this.zzeqj.setOnErrorListener(this);
                this.zzeqj.setOnInfoListener(this);
                this.zzeqj.setOnPreparedListener(this);
                this.zzeqj.setOnVideoSizeChangedListener(this);
                this.zzeqn = 0;
                if (this.zzeqr) {
                    this.zzeqq = new zzbdx(getContext());
                    this.zzeqq.zza(surfaceTexture, getWidth(), getHeight());
                    this.zzeqq.start();
                    SurfaceTexture zzabr = this.zzeqq.zzabr();
                    if (zzabr != null) {
                        surfaceTexture = zzabr;
                    } else {
                        this.zzeqq.zzabq();
                        this.zzeqq = null;
                    }
                }
                this.zzeqj.setDataSource(getContext(), this.zzeqk);
                zzbv.zzly();
                this.zzeqj.setSurface(new Surface(surfaceTexture));
                this.zzeqj.setAudioStreamType(3);
                this.zzeqj.setScreenOnWhilePlaying(true);
                this.zzeqj.prepareAsync();
                zzcx(1);
            } catch (IOException | IllegalArgumentException | IllegalStateException e) {
                String valueOf = String.valueOf(this.zzeqk);
                StringBuilder stringBuilder = new StringBuilder(36 + String.valueOf(valueOf).length());
                stringBuilder.append("Failed to initialize MediaPlayer at ");
                stringBuilder.append(valueOf);
                zzbbd.zzc(stringBuilder.toString(), e);
                onError(this.zzeqj, 1, 0);
            }
        }
    }

    private final void zzabb() {
        if (this.zzeqg && zzabc() && this.zzeqj.getCurrentPosition() > 0 && this.zzeqi != 3) {
            zzaxz.v("AdMediaPlayerView nudging MediaPlayer");
            zzd(0.0f);
            this.zzeqj.start();
            int currentPosition = this.zzeqj.getCurrentPosition();
            long currentTimeMillis = zzbv.zzlm().currentTimeMillis();
            while (zzabc() && this.zzeqj.getCurrentPosition() == currentPosition) {
                if (zzbv.zzlm().currentTimeMillis() - currentTimeMillis > 250) {
                    break;
                }
            }
            this.zzeqj.pause();
            zzabd();
        }
    }

    private final void zzar(boolean z) {
        zzaxz.v("AdMediaPlayerView release");
        if (this.zzeqq != null) {
            this.zzeqq.zzabq();
            this.zzeqq = null;
        }
        if (this.zzeqj != null) {
            this.zzeqj.reset();
            this.zzeqj.release();
            this.zzeqj = null;
            zzcx(0);
            if (z) {
                this.zzeqi = 0;
                this.zzeqi = 0;
            }
        }
    }

    public final void play() {
        zzaxz.v("AdMediaPlayerView play");
        if (zzabc()) {
            this.zzeqj.start();
            zzcx(3);
            this.zzera.zzabf();
            zzayh.zzelc.post(new zzbdf(this));
        }
        this.zzeqi = 3;
    }

    public final void pause() {
        zzaxz.v("AdMediaPlayerView pause");
        if (zzabc() && this.zzeqj.isPlaying()) {
            this.zzeqj.pause();
            zzcx(4);
            zzayh.zzelc.post(new zzbdg(this));
        }
        this.zzeqi = 4;
    }

    public final int getDuration() {
        return zzabc() ? this.zzeqj.getDuration() : -1;
    }

    public final int getCurrentPosition() {
        return zzabc() ? this.zzeqj.getCurrentPosition() : 0;
    }

    public final void seekTo(int i) {
        StringBuilder stringBuilder = new StringBuilder(34);
        stringBuilder.append("AdMediaPlayerView seek ");
        stringBuilder.append(i);
        zzaxz.v(stringBuilder.toString());
        if (zzabc()) {
            this.zzeqj.seekTo(i);
            this.zzeqs = 0;
            return;
        }
        this.zzeqs = i;
    }

    private final boolean zzabc() {
        return (this.zzeqj == null || this.zzeqh == -1 || this.zzeqh == 0 || this.zzeqh == 1) ? false : true;
    }

    public final void zza(float f, float f2) {
        if (this.zzeqq != null) {
            this.zzeqq.zzb(f, f2);
        }
    }

    public final int getVideoWidth() {
        return this.zzeqj != null ? this.zzeqj.getVideoWidth() : 0;
    }

    public final int getVideoHeight() {
        return this.zzeqj != null ? this.zzeqj.getVideoHeight() : 0;
    }

    public final void zzabd() {
        zzd(this.zzerb.getVolume());
    }

    private final void zzd(float f) {
        if (this.zzeqj != null) {
            try {
                this.zzeqj.setVolume(f, f);
                return;
            } catch (IllegalStateException unused) {
                return;
            }
        }
        zzbbd.zzeo("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
    }

    private final void zzcx(int i) {
        if (i == 3) {
            this.zzeqf.zzacd();
            this.zzerb.zzacd();
        } else if (this.zzeqh == 3) {
            this.zzeqf.zzace();
            this.zzerb.zzace();
        }
        this.zzeqh = i;
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzcy(int i) {
        if (this.zzeqt != null) {
            this.zzeqt.onWindowVisibilityChanged(i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            zzeqe.put(Integer.valueOf(-1004), "MEDIA_ERROR_IO");
            zzeqe.put(Integer.valueOf(-1007), "MEDIA_ERROR_MALFORMED");
            zzeqe.put(Integer.valueOf(-1010), "MEDIA_ERROR_UNSUPPORTED");
            zzeqe.put(Integer.valueOf(-110), "MEDIA_ERROR_TIMED_OUT");
            zzeqe.put(Integer.valueOf(3), "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzeqe.put(Integer.valueOf(100), "MEDIA_ERROR_SERVER_DIED");
        zzeqe.put(Integer.valueOf(1), "MEDIA_ERROR_UNKNOWN");
        zzeqe.put(Integer.valueOf(1), "MEDIA_INFO_UNKNOWN");
        zzeqe.put(Integer.valueOf(700), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzeqe.put(Integer.valueOf(701), "MEDIA_INFO_BUFFERING_START");
        zzeqe.put(Integer.valueOf(702), "MEDIA_INFO_BUFFERING_END");
        zzeqe.put(Integer.valueOf(800), "MEDIA_INFO_BAD_INTERLEAVING");
        zzeqe.put(Integer.valueOf(801), "MEDIA_INFO_NOT_SEEKABLE");
        zzeqe.put(Integer.valueOf(802), "MEDIA_INFO_METADATA_UPDATE");
        if (VERSION.SDK_INT >= 19) {
            zzeqe.put(Integer.valueOf(901), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzeqe.put(Integer.valueOf(902), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }
}
