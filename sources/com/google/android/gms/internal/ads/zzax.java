package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzax implements zzaz {
    private static Logger zzcm = Logger.getLogger(zzax.class.getName());
    private ThreadLocal<ByteBuffer> zzcn = new zzay(this);

    public abstract zzbc zza(String str, byte[] bArr, String str2);

    public final zzbc zza(zzbwa zzbwa, zzbd zzbd) throws IOException {
        long position = zzbwa.position();
        ((ByteBuffer) this.zzcn.get()).rewind().limit(8);
        int read;
        do {
            read = zzbwa.read((ByteBuffer) this.zzcn.get());
            if (read == 8) {
                ((ByteBuffer) this.zzcn.get()).rewind();
                position = zzbb.zza((ByteBuffer) this.zzcn.get());
                byte[] bArr = null;
                if (position >= 8 || position <= 1) {
                    long zzc;
                    long j;
                    String zzf = zzbb.zzf((ByteBuffer) this.zzcn.get());
                    if (position == 1) {
                        ((ByteBuffer) this.zzcn.get()).limit(16);
                        zzbwa.read((ByteBuffer) this.zzcn.get());
                        ((ByteBuffer) this.zzcn.get()).position(8);
                        zzc = zzbb.zzc((ByteBuffer) this.zzcn.get()) - 16;
                    } else {
                        zzc = position == 0 ? zzbwa.size() - zzbwa.position() : position - 8;
                    }
                    if ("uuid".equals(zzf)) {
                        ((ByteBuffer) this.zzcn.get()).limit(((ByteBuffer) this.zzcn.get()).limit() + 16);
                        zzbwa.read((ByteBuffer) this.zzcn.get());
                        bArr = new byte[16];
                        for (int position2 = ((ByteBuffer) this.zzcn.get()).position() - 16; position2 < ((ByteBuffer) this.zzcn.get()).position(); position2++) {
                            bArr[position2 - (((ByteBuffer) this.zzcn.get()).position() - 16)] = ((ByteBuffer) this.zzcn.get()).get(position2);
                        }
                        j = zzc - 16;
                    } else {
                        j = zzc;
                    }
                    zzbc zza = zza(zzf, bArr, zzbd instanceof zzbc ? ((zzbc) zzbd).getType() : "");
                    zza.zza(zzbd);
                    ((ByteBuffer) this.zzcn.get()).rewind();
                    zza.zza(zzbwa, (ByteBuffer) this.zzcn.get(), j, this);
                    return zza;
                }
                StringBuilder stringBuilder = new StringBuilder(80);
                stringBuilder.append("Plausibility check failed: size < 8 (size = ");
                stringBuilder.append(position);
                stringBuilder.append("). Stop parsing!");
                zzcm.logp(Level.SEVERE, "com.coremedia.iso.AbstractBoxParser", "parseBox", stringBuilder.toString());
                return null;
            }
        } while (read >= 0);
        zzbwa.zzaw(position);
        throw new EOFException();
    }
}
