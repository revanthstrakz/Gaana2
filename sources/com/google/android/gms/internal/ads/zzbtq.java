package com.google.android.gms.internal.ads;

final class zzbtq {
    static String zzaq(zzbpu zzbpu) {
        zzbtr zzbtr = new zzbtr(zzbpu);
        StringBuilder stringBuilder = new StringBuilder(zzbtr.size());
        for (int i = 0; i < zzbtr.size(); i++) {
            byte zzem = zzbtr.zzem(i);
            if (zzem == (byte) 34) {
                stringBuilder.append("\\\"");
            } else if (zzem == (byte) 39) {
                stringBuilder.append("\\'");
            } else if (zzem != (byte) 92) {
                switch (zzem) {
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
                        if (zzem >= (byte) 32 && zzem <= (byte) 126) {
                            stringBuilder.append((char) zzem);
                            break;
                        }
                        stringBuilder.append('\\');
                        stringBuilder.append((char) (((zzem >>> 6) & 3) + 48));
                        stringBuilder.append((char) (((zzem >>> 3) & 7) + 48));
                        stringBuilder.append((char) (48 + (zzem & 7)));
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
