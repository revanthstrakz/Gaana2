package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.view.Surface;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.nio.ByteBuffer;

@zzark
public final class zzbes implements zzfh, zzll, zzpn<zzov>, zzqu {
    @VisibleForTesting
    private static int zzevp;
    @VisibleForTesting
    private static int zzevq;
    private int bytesTransferred;
    private final zzbdy zzeuo;
    private final zzber zzevr = new zzber();
    private final zzfz zzevs = new zzqo(this.zzsp, zzka.zzavh, 0, zzayh.zzelc, this, -1);
    private final zzfz zzevt = new zzhd(zzka.zzavh);
    private final zzoj zzevu = new zzog();
    private zzfg zzevv;
    private ByteBuffer zzevw;
    private boolean zzevx;
    private zzbez zzevy;
    private final Context zzsp;

    public zzbes(Context context, zzbdy zzbdy) {
        this.zzsp = context;
        this.zzeuo = zzbdy;
        if (zzaxz.zzza()) {
            String valueOf = String.valueOf(this);
            StringBuilder stringBuilder = new StringBuilder(28 + String.valueOf(valueOf).length());
            stringBuilder.append("ExoPlayerAdapter initialize ");
            stringBuilder.append(valueOf);
            zzaxz.v(stringBuilder.toString());
        }
        zzevp++;
        this.zzevv = zzfk.zza(new zzfz[]{this.zzevt, this.zzevs}, this.zzevu, this.zzevr);
        this.zzevv.zza((zzfh) this);
    }

    public final void zza(Surface surface) {
    }

    public final void zza(zzfy zzfy) {
    }

    public final void zza(zzgc zzgc, Object obj) {
    }

    public final void zza(zzma zzma, zzoo zzoo) {
    }

    public final void zzbs() {
    }

    public final void zzd(String str, long j, long j2) {
    }

    public final void zzd(boolean z) {
    }

    public final void zze(zzhn zzhn) {
    }

    public final void zzf(zzhn zzhn) {
    }

    public final void zzh(int i, long j) {
    }

    public final void zzl(zzfs zzfs) {
    }

    public final zzfg zzacw() {
        return this.zzevv;
    }

    public static int zzacx() {
        return zzevp;
    }

    public static int zzacy() {
        return zzevq;
    }

    public final void zza(zzbez zzbez) {
        this.zzevy = zzbez;
    }

    public final zzber zzacz() {
        return this.zzevr;
    }

    public final void zza(Uri uri, String str) {
        zza(uri, str, ByteBuffer.allocate(0), false);
    }

    public final void zza(Uri uri, String str, ByteBuffer byteBuffer, boolean z) {
        zzlo zzmu;
        this.zzevw = byteBuffer;
        this.zzevx = z;
        if (this.zzeuo.zzett) {
            zzow zzev = zzev(str);
            zzmu = new zzmu(uri, zzev, new zzng(zzev), zzayh.zzelc, null);
        } else {
            zzlo zzlk = new zzlk(uri, zzev(str), zzbex.zzewd, -1, zzayh.zzelc, this, null, this.zzeuo.zzetq);
        }
        this.zzevv.zza(zzmu);
        zzevq++;
    }

    public final void release() {
        if (this.zzevv != null) {
            this.zzevv.zzb((zzfh) this);
            this.zzevv.release();
            this.zzevv = null;
            zzevq--;
        }
    }

    public final long getBytesTransferred() {
        return (long) this.bytesTransferred;
    }

    public final void zzb(IOException iOException) {
        if (this.zzevy != null) {
            this.zzevy.zza("onLoadError", iOException);
        }
    }

    public final void zza(int i, int i2, int i3, float f) {
        if (this.zzevy != null) {
            this.zzevy.zzp(i, i2);
        }
    }

    public final void zza(boolean z, int i) {
        if (this.zzevy != null) {
            this.zzevy.zzdd(i);
        }
    }

    public final void zza(zzff zzff) {
        if (this.zzevy != null) {
            this.zzevy.zza("onPlayerError", zzff);
        }
    }

    /* Access modifiers changed, original: final */
    public final void zza(Surface surface, boolean z) {
        zzfj zzfj = new zzfj(this.zzevs, 1, surface);
        if (z) {
            this.zzevv.zzb(zzfj);
            return;
        }
        this.zzevv.zza(zzfj);
    }

    /* Access modifiers changed, original: final */
    public final void zzb(float f, boolean z) {
        zzfj zzfj = new zzfj(this.zzevt, 2, Float.valueOf(f));
        if (z) {
            this.zzevv.zzb(zzfj);
            return;
        }
        this.zzevv.zza(zzfj);
    }

    /* Access modifiers changed, original: final */
    public final void zzau(boolean z) {
        for (int i = 0; i < this.zzevv.zzbq(); i++) {
            this.zzevu.zzf(i, z ^ 1);
        }
    }

    @VisibleForTesting
    private final zzow zzev(String str) {
        if (!this.zzevx || this.zzevw.limit() <= 0) {
            zzbeu zzbeu = new zzbeu(this, str);
            zzow zzbev = this.zzeuo.zzets ? new zzbev(this, zzbeu) : zzbeu;
            if (this.zzevw.limit() > 0) {
                byte[] bArr = new byte[this.zzevw.limit()];
                this.zzevw.get(bArr);
                zzbev = new zzbew(zzbev, bArr);
            }
            return zzbev;
        }
        byte[] bArr2 = new byte[this.zzevw.limit()];
        this.zzevw.get(bArr2);
        return new zzbet(bArr2);
    }

    public final void finalize() throws Throwable {
        zzevp--;
        if (zzaxz.zzza()) {
            String valueOf = String.valueOf(this);
            StringBuilder stringBuilder = new StringBuilder(26 + String.valueOf(valueOf).length());
            stringBuilder.append("ExoPlayerAdapter finalize ");
            stringBuilder.append(valueOf);
            zzaxz.v(stringBuilder.toString());
        }
    }

    public final /* synthetic */ void zzc(Object obj, int i) {
        this.bytesTransferred += i;
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void zzd(boolean z, long j) {
        if (this.zzevy != null) {
            this.zzevy.zzb(z, j);
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ zzov zzew(String str) {
        return new zzpb(str, null, this.zzeuo.zzets ? null : this, this.zzeuo.zzetn, this.zzeuo.zzetp, true, null);
    }
}
