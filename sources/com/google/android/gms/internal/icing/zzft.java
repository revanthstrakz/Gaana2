package com.google.android.gms.internal.icing;

final class zzft {
    static String zzd(zzce zzce) {
        zzfu zzfu = new zzfu(zzce);
        StringBuilder stringBuilder = new StringBuilder(zzfu.size());
        for (int i = 0; i < zzfu.size(); i++) {
            byte zzk = zzfu.zzk(i);
            if (zzk == (byte) 34) {
                stringBuilder.append("\\\"");
            } else if (zzk == (byte) 39) {
                stringBuilder.append("\\'");
            } else if (zzk != (byte) 92) {
                switch (zzk) {
                    case (byte) 7:
                        stringBuilder.append("\\a");
                        break;
                    case (byte) 8:
                        stringBuilder.append("\\b");
                        break;
                    case (byte) 9:
                        stringBuilder.append("\\t");
                        break;
                    case (byte) 10:
                        stringBuilder.append("\\n");
                        break;
                    case (byte) 11:
                        stringBuilder.append("\\v");
                        break;
                    case (byte) 12:
                        stringBuilder.append("\\f");
                        break;
                    case (byte) 13:
                        stringBuilder.append("\\r");
                        break;
                    default:
                        if (zzk >= (byte) 32 && zzk <= (byte) 126) {
                            stringBuilder.append((char) zzk);
                            break;
                        }
                        stringBuilder.append('\\');
                        stringBuilder.append((char) (((zzk >>> 6) & 3) + 48));
                        stringBuilder.append((char) (((zzk >>> 3) & 7) + 48));
                        stringBuilder.append((char) (48 + (zzk & 7)));
                        break;
                        break;
                }
            } else {
                stringBuilder.append("\\\\");
            }
        }
        return stringBuilder.toString();
    }
}
