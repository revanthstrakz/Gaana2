package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import com.google.android.gms.ads.internal.zzbv;
import java.nio.ByteBuffer;

@zzark
@TargetApi(16)
public final class zzbee extends zzbdi implements SurfaceTextureListener, zzbez {
    private Surface zzbjb;
    private final zzbdz zzerw;
    private final zzbea zzeum;
    private final boolean zzeun;
    private final zzbdy zzeuo;
    private zzbdh zzeup;
    private zzbes zzeuq;
    private String zzeur;
    private boolean zzeus;
    private int zzeut = 1;
    private zzbdx zzeuu;
    private final boolean zzeuv;
    private boolean zzeuw;
    private boolean zzeux;
    private int zzeuy;
    private int zzeuz;
    private int zzeva;
    private int zzevb;
    private float zzevc;

    public zzbee(Context context, zzbea zzbea, zzbdz zzbdz, boolean z, boolean z2, zzbdy zzbdy) {
        super(context);
        this.zzeun = z2;
        this.zzerw = zzbdz;
        this.zzeum = zzbea;
        this.zzeuv = z;
        this.zzeuo = zzbdy;
        setSurfaceTextureListener(this);
        this.zzeum.zzb(this);
    }

    private final zzbes zzach() {
        return new zzbes(this.zzerw.getContext(), this.zzeuo);
    }

    private final String zzaci() {
        return zzbv.zzlf().zzo(this.zzerw.getContext(), this.zzerw.zzabz().zzdp);
    }

    private final boolean zzacj() {
        return (this.zzeuq == null || this.zzeus) ? false : true;
    }

    private final boolean zzack() {
        return zzacj() && this.zzeut != 1;
    }

    private final void zzacl() {
        if (this.zzeuq == null && this.zzeur != null && this.zzbjb != null) {
            if (this.zzeur.startsWith("cache:")) {
                zzbfk zzet = this.zzerw.zzet(this.zzeur);
                String zzaci;
                String url;
                if (zzet instanceof zzbfw) {
                    this.zzeuq = ((zzbfw) zzet).zzadd();
                } else if (zzet instanceof zzbfv) {
                    zzbfv zzbfv = (zzbfv) zzet;
                    zzaci = zzaci();
                    ByteBuffer byteBuffer = zzbfv.getByteBuffer();
                    boolean zzadc = zzbfv.zzadc();
                    url = zzbfv.getUrl();
                    if (url == null) {
                        zzbbd.zzeo("Stream cache URL is null.");
                        return;
                    } else {
                        this.zzeuq = zzach();
                        this.zzeuq.zza(Uri.parse(url), zzaci, byteBuffer, zzadc);
                    }
                } else {
                    url = "Stream cache miss: ";
                    zzaci = String.valueOf(this.zzeur);
                    zzbbd.zzeo(zzaci.length() != 0 ? url.concat(zzaci) : new String(url));
                    return;
                }
            }
            this.zzeuq = zzach();
            this.zzeuq.zza(Uri.parse(this.zzeur), zzaci());
            this.zzeuq.zza((zzbez) this);
            zza(this.zzbjb, false);
            this.zzeut = this.zzeuq.zzacw().getPlaybackState();
            if (this.zzeut == 3) {
                zzacm();
            }
        }
    }

    private final void zza(Surface surface, boolean z) {
        if (this.zzeuq != null) {
            this.zzeuq.zza(surface, z);
        } else {
            zzbbd.zzeo("Trying to set surface before player is initalized.");
        }
    }

    private final void zza(float f, boolean z) {
        if (this.zzeuq != null) {
            this.zzeuq.zzb(f, z);
        } else {
            zzbbd.zzeo("Trying to set volume before player is initalized.");
        }
    }

    public final void zzabd() {
        zza(this.zzerb.getVolume(), false);
    }

    private final void zzacm() {
        if (!this.zzeuw) {
            this.zzeuw = true;
            zzayh.zzelc.post(new zzbef(this));
            zzabd();
            this.zzeum.zzcg();
            if (this.zzeux) {
                play();
            }
        }
    }

    public final String zzaaz() {
        String valueOf = String.valueOf("ExoPlayer/3");
        String valueOf2 = String.valueOf(this.zzeuv ? " spherical" : "");
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final void zza(zzbdh zzbdh) {
        this.zzeup = zzbdh;
    }

    public final void setVideoPath(String str) {
        if (str != null) {
            this.zzeur = str;
            zzacl();
        }
    }

    public final void play() {
        if (zzack()) {
            if (this.zzeuo.zzetk) {
                zzaco();
            }
            this.zzeuq.zzacw().zzc(true);
            this.zzeum.zzacd();
            this.zzerb.zzacd();
            this.zzera.zzabf();
            zzayh.zzelc.post(new zzbei(this));
            return;
        }
        this.zzeux = true;
    }

    public final void stop() {
        if (zzacj()) {
            this.zzeuq.zzacw().stop();
            if (this.zzeuq != null) {
                zza(null, true);
                if (this.zzeuq != null) {
                    this.zzeuq.zza(null);
                    this.zzeuq.release();
                    this.zzeuq = null;
                }
                this.zzeut = 1;
                this.zzeus = false;
                this.zzeuw = false;
                this.zzeux = false;
            }
        }
        this.zzeum.zzace();
        this.zzerb.zzace();
        this.zzeum.onStop();
    }

    public final void pause() {
        if (zzack()) {
            if (this.zzeuo.zzetk) {
                zzacp();
            }
            this.zzeuq.zzacw().zzc(false);
            this.zzeum.zzace();
            this.zzerb.zzace();
            zzayh.zzelc.post(new zzbej(this));
        }
    }

    public final void seekTo(int i) {
        if (zzack()) {
            this.zzeuq.zzacw().seekTo((long) i);
        }
    }

    public final void zzcz(int i) {
        if (this.zzeuq != null) {
            this.zzeuq.zzacz().zzdf(i);
        }
    }

    public final void zzda(int i) {
        if (this.zzeuq != null) {
            this.zzeuq.zzacz().zzdg(i);
        }
    }

    public final void zzdb(int i) {
        if (this.zzeuq != null) {
            this.zzeuq.zzacz().zzdb(i);
        }
    }

    public final void zzdc(int i) {
        if (this.zzeuq != null) {
            this.zzeuq.zzacz().zzdc(i);
        }
    }

    public final void zza(float f, float f2) {
        if (this.zzeuu != null) {
            this.zzeuu.zzb(f, f2);
        }
    }

    public final int getCurrentPosition() {
        return zzack() ? (int) this.zzeuq.zzacw().zzbr() : 0;
    }

    public final int getDuration() {
        return zzack() ? (int) this.zzeuq.zzacw().getDuration() : 0;
    }

    public final int getVideoWidth() {
        return this.zzeuy;
    }

    public final int getVideoHeight() {
        return this.zzeuz;
    }

    /* Access modifiers changed, original: protected|final */
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        i = getMeasuredWidth();
        i2 = getMeasuredHeight();
        if (this.zzevc != 0.0f && this.zzeuu == null) {
            float f = (float) i;
            float f2 = f / ((float) i2);
            if (this.zzevc > f2) {
                i2 = (int) (f / this.zzevc);
            }
            if (this.zzevc < f2) {
                i = (int) (((float) i2) * this.zzevc);
            }
        }
        setMeasuredDimension(i, i2);
        if (this.zzeuu != null) {
            this.zzeuu.zzo(i, i2);
        }
        if (VERSION.SDK_INT == 16) {
            if (((this.zzeva > 0 && this.zzeva != i) || (this.zzevb > 0 && this.zzevb != i2)) && this.zzeun && zzacj()) {
                zzfg zzacw = this.zzeuq.zzacw();
                if (zzacw.zzbr() > 0 && !zzacw.zzbp()) {
                    zza(0.0f, true);
                    zzacw.zzc(true);
                    long zzbr = zzacw.zzbr();
                    long currentTimeMillis = zzbv.zzlm().currentTimeMillis();
                    while (zzacj() && zzacw.zzbr() == zzbr) {
                        if (zzbv.zzlm().currentTimeMillis() - currentTimeMillis > 250) {
                            break;
                        }
                    }
                    zzacw.zzc(false);
                    zzabd();
                }
            }
            this.zzeva = i;
            this.zzevb = i2;
        }
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.zzeuv) {
            this.zzeuu = new zzbdx(getContext());
            this.zzeuu.zza(surfaceTexture, i, i2);
            this.zzeuu.start();
            SurfaceTexture zzabr = this.zzeuu.zzabr();
            if (zzabr != null) {
                surfaceTexture = zzabr;
            } else {
                this.zzeuu.zzabq();
                this.zzeuu = null;
            }
        }
        this.zzbjb = new Surface(surfaceTexture);
        if (this.zzeuq == null) {
            zzacl();
        } else {
            zza(this.zzbjb, true);
            if (!this.zzeuo.zzetk) {
                zzaco();
            }
        }
        zzacn();
        zzayh.zzelc.post(new zzbek(this));
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.zzeuu != null) {
            this.zzeuu.zzo(i, i2);
        }
        zzayh.zzelc.post(new zzbel(this, i, i2));
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzeum.zzc(this);
        this.zzera.zza(surfaceTexture, this.zzeup);
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        pause();
        if (this.zzeuu != null) {
            this.zzeuu.zzabq();
            this.zzeuu = null;
        }
        if (this.zzeuq != null) {
            zzacp();
            if (this.zzbjb != null) {
                this.zzbjb.release();
            }
            this.zzbjb = null;
            zza(null, true);
        }
        zzayh.zzelc.post(new zzbem(this));
        return true;
    }

    /* Access modifiers changed, original: protected|final */
    public final void onWindowVisibilityChanged(int i) {
        StringBuilder stringBuilder = new StringBuilder(57);
        stringBuilder.append("AdExoPlayerView3 window visibility changed to ");
        stringBuilder.append(i);
        zzaxz.v(stringBuilder.toString());
        zzayh.zzelc.post(new zzben(this, i));
        super.onWindowVisibilityChanged(i);
    }

    public final void zzb(boolean z, long j) {
        if (this.zzerw != null) {
            zzbcg.zzepo.execute(new zzbeo(this, z, j));
        }
    }

    public final void zzdd(int i) {
        if (this.zzeut != i) {
            this.zzeut = i;
            switch (i) {
                case 3:
                    zzacm();
                    return;
                case 4:
                    if (this.zzeuo.zzetk) {
                        zzacp();
                    }
                    this.zzeum.zzace();
                    this.zzerb.zzace();
                    zzayh.zzelc.post(new zzbeg(this));
                    break;
            }
        }
    }

    public final void zzp(int i, int i2) {
        this.zzeuy = i;
        this.zzeuz = i2;
        zzacn();
    }

    public final void zza(String str, Exception exception) {
        String canonicalName = exception.getClass().getCanonicalName();
        String message = exception.getMessage();
        StringBuilder stringBuilder = new StringBuilder(((2 + String.valueOf(str).length()) + String.valueOf(canonicalName).length()) + String.valueOf(message).length());
        stringBuilder.append(str);
        stringBuilder.append("/");
        stringBuilder.append(canonicalName);
        stringBuilder.append(":");
        stringBuilder.append(message);
        str = stringBuilder.toString();
        message = "ExoPlayerAdapter error: ";
        canonicalName = String.valueOf(str);
        zzbbd.zzeo(canonicalName.length() != 0 ? message.concat(canonicalName) : new String(message));
        this.zzeus = true;
        if (this.zzeuo.zzetk) {
            zzacp();
        }
        zzayh.zzelc.post(new zzbeh(this, str));
    }

    private final void zzacn() {
        float f = this.zzeuz > 0 ? ((float) this.zzeuy) / ((float) this.zzeuz) : 1.0f;
        if (this.zzevc != f) {
            this.zzevc = f;
            requestLayout();
        }
    }

    private final void zzaco() {
        if (this.zzeuq != null) {
            this.zzeuq.zzau(true);
        }
    }

    private final void zzacp() {
        if (this.zzeuq != null) {
            this.zzeuq.zzau(false);
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzc(boolean z, long j) {
        this.zzerw.zza(z, j);
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzde(int i) {
        if (this.zzeup != null) {
            this.zzeup.onWindowVisibilityChanged(i);
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzacq() {
        if (this.zzeup != null) {
            this.zzeup.zzabh();
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzq(int i, int i2) {
        if (this.zzeup != null) {
            this.zzeup.zzm(i, i2);
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzacr() {
        if (this.zzeup != null) {
            this.zzeup.zzabe();
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzacs() {
        if (this.zzeup != null) {
            this.zzeup.onPaused();
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzact() {
        if (this.zzeup != null) {
            this.zzeup.zzabf();
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzeu(String str) {
        if (this.zzeup != null) {
            this.zzeup.zzi("ExoPlayerAdapter error", str);
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzacu() {
        if (this.zzeup != null) {
            this.zzeup.zzabg();
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzacv() {
        if (this.zzeup != null) {
            this.zzeup.zzcg();
        }
    }
}
