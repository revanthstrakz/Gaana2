package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;

@zzark
public final class zzbfc {
    private long zzcs;

    public final long zzl(ByteBuffer byteBuffer) {
        if (this.zzcs > 0) {
            return this.zzcs;
        }
        try {
            zzbc zzbc;
            zzbf zzbf;
            zzbvy zzbvy;
            byteBuffer = byteBuffer.duplicate();
            byteBuffer.flip();
            Iterator it = new zzba(new zzbfb(byteBuffer), zzbfe.zzewi).zzaqh().iterator();
            do {
                zzbf = null;
                if (!it.hasNext()) {
                    zzbvy = null;
                    break;
                }
                zzbc = (zzbc) it.next();
            } while (!(zzbc instanceof zzbe));
            zzbvy = (zzbe) zzbc;
            for (zzbc zzbc2 : zzbvy.zzaqh()) {
                if (zzbc2 instanceof zzbf) {
                    zzbf = (zzbf) zzbc2;
                    break;
                }
            }
            this.zzcs = (1000 * zzbf.getDuration()) / zzbf.zzs();
            return this.zzcs;
        } catch (IOException | RuntimeException unused) {
            return 0;
        }
    }
}
