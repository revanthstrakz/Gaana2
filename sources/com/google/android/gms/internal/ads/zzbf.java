package com.google.android.gms.internal.ads;

import android.support.v4.view.MotionEventCompat;
import java.nio.ByteBuffer;
import java.util.Date;

public final class zzbf extends zzbvx {
    private Date zzcp;
    private Date zzcq;
    private long zzcr;
    private long zzcs;
    private double zzct = 1.0d;
    private float zzcu = 1.0f;
    private zzbwh zzcv = zzbwh.zzgda;
    private long zzcw;
    private int zzcx;
    private int zzcy;
    private int zzcz;
    private int zzda;
    private int zzdb;
    private int zzdc;

    public zzbf() {
        super("mvhd");
    }

    public final long zzs() {
        return this.zzcr;
    }

    public final long getDuration() {
        return this.zzcs;
    }

    public final void zzg(ByteBuffer byteBuffer) {
        zzp(byteBuffer);
        if (getVersion() == 1) {
            this.zzcp = zzbwc.zzbm(zzbb.zzc(byteBuffer));
            this.zzcq = zzbwc.zzbm(zzbb.zzc(byteBuffer));
            this.zzcr = zzbb.zza(byteBuffer);
            this.zzcs = zzbb.zzc(byteBuffer);
        } else {
            this.zzcp = zzbwc.zzbm(zzbb.zza(byteBuffer));
            this.zzcq = zzbwc.zzbm(zzbb.zza(byteBuffer));
            this.zzcr = zzbb.zza(byteBuffer);
            this.zzcs = zzbb.zza(byteBuffer);
        }
        this.zzct = zzbb.zzd(byteBuffer);
        byte[] bArr = new byte[2];
        byteBuffer.get(bArr);
        this.zzcu = ((float) ((short) ((bArr[1] & 255) | ((short) (0 | ((bArr[0] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)))))) / 256.0f;
        zzbb.zzb(byteBuffer);
        zzbb.zza(byteBuffer);
        zzbb.zza(byteBuffer);
        this.zzcv = zzbwh.zzq(byteBuffer);
        this.zzcx = byteBuffer.getInt();
        this.zzcy = byteBuffer.getInt();
        this.zzcz = byteBuffer.getInt();
        this.zzda = byteBuffer.getInt();
        this.zzdb = byteBuffer.getInt();
        this.zzdc = byteBuffer.getInt();
        this.zzcw = zzbb.zza(byteBuffer);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MovieHeaderBox[");
        stringBuilder.append("creationTime=");
        stringBuilder.append(this.zzcp);
        stringBuilder.append(";");
        stringBuilder.append("modificationTime=");
        stringBuilder.append(this.zzcq);
        stringBuilder.append(";");
        stringBuilder.append("timescale=");
        stringBuilder.append(this.zzcr);
        stringBuilder.append(";");
        stringBuilder.append("duration=");
        stringBuilder.append(this.zzcs);
        stringBuilder.append(";");
        stringBuilder.append("rate=");
        stringBuilder.append(this.zzct);
        stringBuilder.append(";");
        stringBuilder.append("volume=");
        stringBuilder.append(this.zzcu);
        stringBuilder.append(";");
        stringBuilder.append("matrix=");
        stringBuilder.append(this.zzcv);
        stringBuilder.append(";");
        stringBuilder.append("nextTrackId=");
        stringBuilder.append(this.zzcw);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
