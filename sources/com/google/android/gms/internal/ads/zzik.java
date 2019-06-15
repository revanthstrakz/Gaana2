package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Stack;

final class zzik implements zzin {
    private final byte[] zzahi = new byte[8];
    private final Stack<zzim> zzahj = new Stack();
    private final zziu zzahk = new zziu();
    private zzio zzahl;
    private int zzahm;
    private int zzahn;
    private long zzaho;

    zzik() {
    }

    public final void zza(zzio zzio) {
        this.zzahl = zzio;
    }

    public final void reset() {
        this.zzahm = 0;
        this.zzahj.clear();
        this.zzahk.reset();
    }

    public final boolean zzb(zzia zzia) throws IOException, InterruptedException {
        zzpo.checkState(this.zzahl != null);
        while (true) {
            if (this.zzahj.isEmpty() || zzia.getPosition() < ((zzim) this.zzahj.peek()).zzahp) {
                int zzae;
                int zza;
                if (this.zzahm == 0) {
                    long zza2 = this.zzahk.zza(zzia, true, false, 4);
                    if (zza2 == -2) {
                        zzia.zzdx();
                        while (true) {
                            zzia.zza(this.zzahi, 0, 4);
                            zzae = zziu.zzae(this.zzahi[0]);
                            if (zzae != -1 && zzae <= 4) {
                                zza = (int) zziu.zza(this.zzahi, zzae, false);
                                if (this.zzahl.zzac(zza)) {
                                    zzia.zzw(zzae);
                                    zza2 = (long) zza;
                                }
                            }
                            zzia.zzw(1);
                        }
                    }
                    if (zza2 == -1) {
                        return false;
                    }
                    this.zzahn = (int) zza2;
                    this.zzahm = 1;
                }
                if (this.zzahm == 1) {
                    this.zzaho = this.zzahk.zza(zzia, false, true, 8);
                    this.zzahm = 2;
                }
                zzae = this.zzahl.zzab(this.zzahn);
                long j;
                StringBuilder stringBuilder;
                zzio zzio;
                switch (zzae) {
                    case 0:
                        zzia.zzw((int) this.zzaho);
                        this.zzahm = 0;
                    case 1:
                        long position = zzia.getPosition();
                        this.zzahj.add(new zzim(this.zzahn, position + this.zzaho));
                        this.zzahl.zzd(this.zzahn, position, this.zzaho);
                        this.zzahm = 0;
                        return true;
                    case 2:
                        if (this.zzaho > 8) {
                            j = this.zzaho;
                            stringBuilder = new StringBuilder(42);
                            stringBuilder.append("Invalid integer size: ");
                            stringBuilder.append(j);
                            throw new zzfx(stringBuilder.toString());
                        }
                        this.zzahl.zzc(this.zzahn, zza(zzia, (int) this.zzaho));
                        this.zzahm = 0;
                        return true;
                    case 3:
                        if (this.zzaho > 2147483647L) {
                            j = this.zzaho;
                            stringBuilder = new StringBuilder(41);
                            stringBuilder.append("String element size: ");
                            stringBuilder.append(j);
                            throw new zzfx(stringBuilder.toString());
                        }
                        String str;
                        zzio = this.zzahl;
                        int i = this.zzahn;
                        zza = (int) this.zzaho;
                        if (zza == 0) {
                            str = "";
                        } else {
                            byte[] bArr = new byte[zza];
                            zzia.readFully(bArr, 0, zza);
                            str = new String(bArr);
                        }
                        zzio.zza(i, str);
                        this.zzahm = 0;
                        return true;
                    case 4:
                        this.zzahl.zza(this.zzahn, (int) this.zzaho, zzia);
                        this.zzahm = 0;
                        return true;
                    case 5:
                        if (this.zzaho == 4 || this.zzaho == 8) {
                            double intBitsToFloat;
                            zzio = this.zzahl;
                            zza = this.zzahn;
                            int i2 = (int) this.zzaho;
                            long zza3 = zza(zzia, i2);
                            if (i2 == 4) {
                                intBitsToFloat = (double) Float.intBitsToFloat((int) zza3);
                            } else {
                                intBitsToFloat = Double.longBitsToDouble(zza3);
                            }
                            zzio.zza(zza, intBitsToFloat);
                            this.zzahm = 0;
                            return true;
                        }
                        j = this.zzaho;
                        stringBuilder = new StringBuilder(40);
                        stringBuilder.append("Invalid float size: ");
                        stringBuilder.append(j);
                        throw new zzfx(stringBuilder.toString());
                    default:
                        StringBuilder stringBuilder2 = new StringBuilder(32);
                        stringBuilder2.append("Invalid element type ");
                        stringBuilder2.append(zzae);
                        throw new zzfx(stringBuilder2.toString());
                }
            }
            this.zzahl.zzad(((zzim) this.zzahj.pop()).zzahn);
            return true;
        }
    }

    private final long zza(zzia zzia, int i) throws IOException, InterruptedException {
        int i2 = 0;
        zzia.readFully(this.zzahi, 0, i);
        long j = 0;
        while (i2 < i) {
            i2++;
            j = (j << 8) | ((long) (this.zzahi[i2] & 255));
        }
        return j;
    }
}
