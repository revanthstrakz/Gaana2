package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzbpq {
    static int zza(byte[] bArr, int i, zzbpr zzbpr) {
        int i2 = i + 1;
        i = bArr[i];
        if (i < (byte) 0) {
            return zza(i, bArr, i2, zzbpr);
        }
        zzbpr.zzfld = i;
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, zzbpr zzbpr) {
        i &= 127;
        int i3 = i2 + 1;
        byte b = bArr[i2];
        if (b >= (byte) 0) {
            zzbpr.zzfld = i | (b << 7);
            return i3;
        }
        i |= (b & 127) << 7;
        i2 = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 >= (byte) 0) {
            zzbpr.zzfld = i | (b2 << 14);
            return i2;
        }
        i |= (b2 & 127) << 14;
        i3 = i2 + 1;
        b = bArr[i2];
        if (b >= (byte) 0) {
            zzbpr.zzfld = i | (b << 21);
            return i3;
        }
        i |= (b & 127) << 21;
        i2 = i3 + 1;
        b2 = bArr[i3];
        if (b2 >= (byte) 0) {
            zzbpr.zzfld = i | (b2 << 28);
            return i2;
        }
        i |= (b2 & 127) << 28;
        while (true) {
            i3 = i2 + 1;
            if (bArr[i2] >= (byte) 0) {
                zzbpr.zzfld = i;
                return i3;
            }
            i2 = i3;
        }
    }

    static int zzb(byte[] bArr, int i, zzbpr zzbpr) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzbpr.zzfle = j;
            return i2;
        }
        long j2 = j & 127;
        i = i2 + 1;
        byte b = bArr[i2];
        long j3 = j2 | (((long) (b & 127)) << 7);
        int i3 = 7;
        while (b < (byte) 0) {
            i2 = i + 1;
            byte b2 = bArr[i];
            i3 += 7;
            j3 |= ((long) (b2 & 127)) << i3;
            int i4 = i2;
            b = b2;
            i = i4;
        }
        zzbpr.zzfle = j3;
        return i;
    }

    static int zzg(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16));
    }

    static long zzh(byte[] bArr, int i) {
        return (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48)) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    static double zzi(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzh(bArr, i));
    }

    static float zzj(byte[] bArr, int i) {
        return Float.intBitsToFloat(zzg(bArr, i));
    }

    static int zzc(byte[] bArr, int i, zzbpr zzbpr) throws zzbrl {
        i = zza(bArr, i, zzbpr);
        int i2 = zzbpr.zzfld;
        if (i2 < 0) {
            throw zzbrl.zzand();
        } else if (i2 == 0) {
            zzbpr.zzflf = "";
            return i;
        } else {
            zzbpr.zzflf = new String(bArr, i, i2, zzbrf.UTF_8);
            return i + i2;
        }
    }

    static int zzd(byte[] bArr, int i, zzbpr zzbpr) throws zzbrl {
        i = zza(bArr, i, zzbpr);
        int i2 = zzbpr.zzfld;
        if (i2 < 0) {
            throw zzbrl.zzand();
        } else if (i2 == 0) {
            zzbpr.zzflf = "";
            return i;
        } else {
            zzbpr.zzflf = zzbuc.zzo(bArr, i, i2);
            return i + i2;
        }
    }

    static int zze(byte[] bArr, int i, zzbpr zzbpr) throws zzbrl {
        i = zza(bArr, i, zzbpr);
        int i2 = zzbpr.zzfld;
        if (i2 < 0) {
            throw zzbrl.zzand();
        } else if (i2 > bArr.length - i) {
            throw zzbrl.zzanc();
        } else if (i2 == 0) {
            zzbpr.zzflf = zzbpu.zzfli;
            return i;
        } else {
            zzbpr.zzflf = zzbpu.zzi(bArr, i, i2);
            return i + i2;
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzbrk<?> zzbrk, zzbpr zzbpr) {
        zzbre zzbre = (zzbre) zzbrk;
        i2 = zza(bArr, i2, zzbpr);
        zzbre.zzfo(zzbpr.zzfld);
        while (i2 < i3) {
            int zza = zza(bArr, i2, zzbpr);
            if (i != zzbpr.zzfld) {
                break;
            }
            i2 = zza(bArr, zza, zzbpr);
            zzbre.zzfo(zzbpr.zzfld);
        }
        return i2;
    }

    static int zza(byte[] bArr, int i, zzbrk<?> zzbrk, zzbpr zzbpr) throws IOException {
        zzbre zzbre = (zzbre) zzbrk;
        i = zza(bArr, i, zzbpr);
        int i2 = zzbpr.zzfld + i;
        while (i < i2) {
            i = zza(bArr, i, zzbpr);
            zzbre.zzfo(zzbpr.zzfld);
        }
        if (i == i2) {
            return i;
        }
        throw zzbrl.zzanc();
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzbtv zzbtv, zzbpr zzbpr) throws zzbrl {
        if ((i >>> 3) == 0) {
            throw zzbrl.zzanf();
        }
        int i4 = i & 7;
        if (i4 != 5) {
            switch (i4) {
                case 0:
                    int zzb = zzb(bArr, i2, zzbpr);
                    zzbtv.zzc(i, Long.valueOf(zzbpr.zzfle));
                    return zzb;
                case 1:
                    zzbtv.zzc(i, Long.valueOf(zzh(bArr, i2)));
                    return i2 + 8;
                case 2:
                    i2 = zza(bArr, i2, zzbpr);
                    i3 = zzbpr.zzfld;
                    if (i3 < 0) {
                        throw zzbrl.zzand();
                    } else if (i3 > bArr.length - i2) {
                        throw zzbrl.zzanc();
                    } else {
                        if (i3 == 0) {
                            zzbtv.zzc(i, zzbpu.zzfli);
                        } else {
                            zzbtv.zzc(i, zzbpu.zzi(bArr, i2, i3));
                        }
                        return i2 + i3;
                    }
                case 3:
                    zzbtv zzapa = zzbtv.zzapa();
                    int i5 = (i & -8) | 4;
                    i4 = 0;
                    while (i2 < i3) {
                        int zza = zza(bArr, i2, zzbpr);
                        i2 = zzbpr.zzfld;
                        if (i2 != i5) {
                            i4 = i2;
                            i2 = zza(i2, bArr, zza, i3, zzapa, zzbpr);
                        } else {
                            i4 = i2;
                            i2 = zza;
                            if (i2 <= i3 || r0 != i5) {
                                throw zzbrl.zzanj();
                            }
                            zzbtv.zzc(i, zzapa);
                            return i2;
                        }
                    }
                    if (i2 <= i3) {
                    }
                    throw zzbrl.zzanj();
                default:
                    throw zzbrl.zzanf();
            }
        }
        zzbtv.zzc(i, Integer.valueOf(zzg(bArr, i2)));
        return i2 + 4;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzbpr zzbpr) throws zzbrl {
        if ((i >>> 3) == 0) {
            throw zzbrl.zzanf();
        }
        int i4 = i & 7;
        if (i4 == 5) {
            return i2 + 4;
        }
        switch (i4) {
            case 0:
                return zzb(bArr, i2, zzbpr);
            case 1:
                return i2 + 8;
            case 2:
                return zza(bArr, i2, zzbpr) + zzbpr.zzfld;
            case 3:
                i = (i & -8) | 4;
                i4 = 0;
                while (i2 < i3) {
                    i2 = zza(bArr, i2, zzbpr);
                    i4 = zzbpr.zzfld;
                    if (i4 != i) {
                        i2 = zza(i4, bArr, i2, i3, zzbpr);
                    } else if (i2 > i3 && r0 == i) {
                        return i2;
                    } else {
                        throw zzbrl.zzanj();
                    }
                }
                if (i2 > i3) {
                }
                throw zzbrl.zzanj();
            default:
                throw zzbrl.zzanf();
        }
    }
}
