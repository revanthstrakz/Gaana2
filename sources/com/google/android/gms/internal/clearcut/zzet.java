package com.google.android.gms.internal.clearcut;

final class zzet {
    static String zzc(zzbb zzbb) {
        zzeu zzeu = new zzeu(zzbb);
        StringBuilder stringBuilder = new StringBuilder(zzeu.size());
        for (int i = 0; i < zzeu.size(); i++) {
            String str;
            byte zzj = zzeu.zzj(i);
            if (zzj == (byte) 34) {
                str = "\\\"";
            } else if (zzj == (byte) 39) {
                str = "\\'";
            } else if (zzj != (byte) 92) {
                switch (zzj) {
                    case (byte) 7:
                        str = "\\a";
                        break;
                    case (byte) 8:
                        str = "\\b";
                        break;
                    case (byte) 9:
                        str = "\\t";
                        break;
                    case (byte) 10:
                        str = "\\n";
                        break;
                    case (byte) 11:
                        str = "\\v";
                        break;
                    case (byte) 12:
                        str = "\\f";
                        break;
                    case (byte) 13:
                        str = "\\r";
                        break;
                    default:
                        char c;
                        if (zzj < (byte) 32 || zzj > (byte) 126) {
                            stringBuilder.append('\\');
                            stringBuilder.append((char) (((zzj >>> 6) & 3) + 48));
                            stringBuilder.append((char) (((zzj >>> 3) & 7) + 48));
                            c = (char) (48 + (zzj & 7));
                        } else {
                            c = (char) zzj;
                        }
                        stringBuilder.append(c);
                        continue;
                }
            } else {
                str = "\\\\";
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
}
