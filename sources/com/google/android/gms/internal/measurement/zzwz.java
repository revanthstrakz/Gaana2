package com.google.android.gms.internal.measurement;

final class zzwz {
    static String zzd(zzte zzte) {
        zzxa zzxa = new zzxa(zzte);
        StringBuilder stringBuilder = new StringBuilder(zzxa.size());
        for (int i = 0; i < zzxa.size(); i++) {
            byte zzam = zzxa.zzam(i);
            if (zzam == (byte) 34) {
                stringBuilder.append("\\\"");
            } else if (zzam == (byte) 39) {
                stringBuilder.append("\\'");
            } else if (zzam != (byte) 92) {
                switch (zzam) {
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
                        if (zzam >= (byte) 32 && zzam <= (byte) 126) {
                            stringBuilder.append((char) zzam);
                            break;
                        }
                        stringBuilder.append('\\');
                        stringBuilder.append((char) (((zzam >>> 6) & 3) + 48));
                        stringBuilder.append((char) (((zzam >>> 3) & 7) + 48));
                        stringBuilder.append((char) (48 + (zzam & 7)));
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
