package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import java.io.IOException;
import java.nio.charset.Charset;

final class cr {
    private static final int a = ft.c("ID3");
    private static final Charset[] b = new Charset[]{Charset.forName("ISO-8859-1"), Charset.forName("UTF-16LE"), Charset.forName("UTF-16BE"), Charset.forName("UTF-8")};

    public static cg a(cd cdVar) throws IOException, InterruptedException {
        fp fpVar = new fp(10);
        cg cgVar = null;
        int i = 0;
        while (true) {
            cdVar.c(fpVar.a, 0, 10);
            fpVar.c(0);
            if (fpVar.j() != a) {
                cdVar.a();
                cdVar.c(i);
                return cgVar;
            }
            int f = fpVar.f();
            int f2 = fpVar.f();
            int f3 = fpVar.f();
            int r = fpVar.r();
            if (cgVar == null && a(f, f2, f3, r)) {
                byte[] bArr = new byte[r];
                cdVar.c(bArr, 0, r);
                cgVar = a(new fp(bArr), f, f3);
            } else {
                cdVar.c(r);
            }
            i += r + 10;
        }
    }

    private static boolean a(int i, int i2, int i3, int i4) {
        return i2 != 255 && i >= 2 && i <= 4 && i4 <= 3145728 && ((i != 2 || ((i3 & 63) == 0 && (i3 & 64) == 0)) && ((i != 3 || (i3 & 31) == 0) && (i != 4 || (i3 & 15) == 0)));
    }

    private static cg a(fp fpVar, int i, int i2) {
        b(fpVar, i, i2);
        fpVar.c(0);
        if (i != 3 || (i2 & 64) == 0) {
            if (i == 4 && (i2 & 64) != 0) {
                if (fpVar.b() < 4) {
                    return null;
                }
                i2 = fpVar.r();
                if (i2 < 6 || i2 > fpVar.b() + 4) {
                    return null;
                }
                fpVar.c(i2);
            }
        } else if (fpVar.b() < 4) {
            return null;
        } else {
            i2 = fpVar.s();
            if (i2 > fpVar.b()) {
                return null;
            }
            if (i2 >= 6) {
                fpVar.d(2);
                int s = fpVar.s();
                fpVar.c(4);
                fpVar.b(fpVar.c() - s);
                if (fpVar.b() < i2) {
                    return null;
                }
            }
            fpVar.d(i2);
        }
        while (true) {
            Pair a = a(i, fpVar);
            if (a == null) {
                return null;
            }
            if (((String) a.first).length() > 3) {
                cg a2 = cg.a(((String) a.first).substring(3), (String) a.second);
                if (a2 != null) {
                    return a2;
                }
            }
        }
    }

    private static Pair<String, String> a(int i, fp fpVar) {
        Pair<String, String> pair;
        int i2;
        while (true) {
            pair = null;
            if (i != 2) {
                if (fpVar.b() >= 10) {
                    String a = fpVar.a(4, Charset.forName(C.ASCII_NAME));
                    if (!a.equals("\u0000\u0000\u0000\u0000")) {
                        int r = i == 4 ? fpVar.r() : fpVar.s();
                        if (r != 0 && r <= fpVar.b() - 2) {
                            int g = fpVar.g();
                            int i3 = ((i != 4 || (g & 12) == 0) && (i != 3 || (g & PsExtractor.AUDIO_STREAM) == 0)) ? 0 : 1;
                            if (i3 == 0 && a.equals(CommentFrame.ID)) {
                                i2 = r;
                                break;
                            }
                            i2 = r;
                            fpVar.d(i2);
                        } else {
                            return null;
                        }
                    }
                    return null;
                }
                return null;
            } else if (fpVar.b() < 6) {
                return null;
            } else {
                String a2 = fpVar.a(3, Charset.forName(C.ASCII_NAME));
                if (a2.equals("\u0000\u0000\u0000")) {
                    return null;
                }
                i2 = fpVar.j();
                if (i2 == 0 || i2 > fpVar.b()) {
                    return null;
                }
                if (a2.equals("COM")) {
                    break;
                }
                fpVar.d(i2);
            }
        }
        i = fpVar.f();
        if (i < 0 || i >= b.length) {
            return null;
        }
        String[] split = fpVar.a(i2 - 1, b[i]).split("\u0000");
        if (split.length == 2) {
            pair = Pair.create(split[0], split[1]);
        }
        return pair;
    }

    private static boolean b(fp fpVar, int i, int i2) {
        int i3 = 0;
        if (i != 4) {
            if ((i2 & 128) != 0) {
                byte[] bArr = fpVar.a;
                i2 = bArr.length;
                while (true) {
                    int i4 = i3 + 1;
                    if (i4 >= i2) {
                        break;
                    }
                    if ((bArr[i3] & 255) == 255 && bArr[i4] == (byte) 0) {
                        System.arraycopy(bArr, i3 + 2, bArr, i4, (i2 - i3) - 2);
                        i2--;
                    }
                    i3 = i4;
                }
                fpVar.b(i2);
            }
        } else if (a(fpVar, false)) {
            b(fpVar, false);
        } else if (!a(fpVar, true)) {
            return false;
        } else {
            b(fpVar, true);
        }
        return true;
    }

    private static boolean a(fp fpVar, boolean z) {
        fpVar.c(0);
        while (fpVar.b() >= 10 && fpVar.m() != 0) {
            long k = fpVar.k();
            if (!z) {
                if ((k & 8421504) != 0) {
                    return false;
                }
                k = (((k & 127) | (((k >> 8) & 127) << 7)) | (((k >> 16) & 127) << 14)) | (((k >> 24) & 127) << 21);
            }
            if (k > ((long) (fpVar.b() - 2))) {
                return false;
            }
            if ((1 & fpVar.g()) != 0 && fpVar.b() < 4) {
                return false;
            }
            fpVar.d((int) k);
        }
        return true;
    }

    private static void b(fp fpVar, boolean z) {
        fpVar.c(0);
        byte[] bArr = fpVar.a;
        while (fpVar.b() >= 10 && fpVar.m() != 0) {
            int d;
            int s = z ? fpVar.s() : fpVar.r();
            int g = fpVar.g();
            if ((g & 1) != 0) {
                d = fpVar.d();
                System.arraycopy(bArr, d + 4, bArr, d, fpVar.b() - 4);
                s -= 4;
                d = g & -2;
                fpVar.b(fpVar.c() - 4);
            } else {
                d = g;
            }
            if ((d & 2) != 0) {
                int d2 = fpVar.d() + 1;
                int i = d2;
                int i2 = s;
                s = 0;
                while (true) {
                    s++;
                    if (s >= i2) {
                        break;
                    }
                    if ((bArr[d2 - 1] & 255) == 255 && bArr[d2] == (byte) 0) {
                        d2++;
                        i2--;
                    }
                    int i3 = i + 1;
                    int i4 = d2 + 1;
                    bArr[i] = bArr[d2];
                    i = i3;
                    d2 = i4;
                }
                fpVar.b(fpVar.c() - (d2 - i));
                System.arraycopy(bArr, d2, bArr, i, fpVar.b() - d2);
                d &= -3;
                s = i2;
            }
            if (d != g || z) {
                g = fpVar.d() - 6;
                a(bArr, g, s);
                bArr[g + 4] = (byte) (d >> 8);
                bArr[g + 5] = (byte) (d & 255);
            }
            fpVar.d(s);
        }
    }

    private static void a(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) ((i2 >> 21) & 127);
        bArr[i + 1] = (byte) ((i2 >> 14) & 127);
        bArr[i + 2] = (byte) ((i2 >> 7) & 127);
        bArr[i + 3] = (byte) (i2 & 127);
    }
}
