package com.google.android.gms.internal.measurement;

import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import java.nio.ByteBuffer;

abstract class zzxn {
    zzxn() {
    }

    public abstract int zzb(int i, byte[] bArr, int i2, int i3);

    public abstract int zzb(CharSequence charSequence, byte[] bArr, int i, int i2);

    public abstract void zzb(CharSequence charSequence, ByteBuffer byteBuffer);

    public abstract String zzh(byte[] bArr, int i, int i2) throws zzuv;

    /* Access modifiers changed, original: final */
    public final boolean zzf(byte[] bArr, int i, int i2) {
        return zzb(0, bArr, i, i2) == 0;
    }

    static void zzc(CharSequence charSequence, ByteBuffer byteBuffer) {
        char charAt;
        char charAt2;
        StringBuilder stringBuilder;
        int length = charSequence.length();
        int position = byteBuffer.position();
        int i = 0;
        while (i < length) {
            try {
                charAt = charSequence.charAt(i);
                if (charAt >= 128) {
                    break;
                }
                byteBuffer.put(position + i, (byte) charAt);
                i++;
            } catch (IndexOutOfBoundsException unused) {
                length = byteBuffer.position() + Math.max(i, (position - byteBuffer.position()) + 1);
                charAt2 = charSequence.charAt(i);
                stringBuilder = new StringBuilder(37);
                stringBuilder.append("Failed writing ");
                stringBuilder.append(charAt2);
                stringBuilder.append(" at index ");
                stringBuilder.append(length);
                throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
            }
        }
        if (i == length) {
            byteBuffer.position(position + i);
            return;
        }
        position += i;
        while (i < length) {
            charAt = charSequence.charAt(i);
            int i2;
            if (charAt < 128) {
                byteBuffer.put(position, (byte) charAt);
            } else if (charAt < 2048) {
                i2 = position + 1;
                try {
                    byteBuffer.put(position, (byte) (PsExtractor.AUDIO_STREAM | (charAt >>> 6)));
                    byteBuffer.put(i2, (byte) ((63 & charAt) | 128));
                    position = i2;
                } catch (IndexOutOfBoundsException unused2) {
                    position = i2;
                    length = byteBuffer.position() + Math.max(i, (position - byteBuffer.position()) + 1);
                    charAt2 = charSequence.charAt(i);
                    stringBuilder = new StringBuilder(37);
                    stringBuilder.append("Failed writing ");
                    stringBuilder.append(charAt2);
                    stringBuilder.append(" at index ");
                    stringBuilder.append(length);
                    throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
                }
            } else if (charAt < 55296 || 57343 < charAt) {
                i2 = position + 1;
                byteBuffer.put(position, (byte) (224 | (charAt >>> 12)));
                position = i2 + 1;
                byteBuffer.put(i2, (byte) (((charAt >>> 6) & 63) | 128));
                byteBuffer.put(position, (byte) ((charAt & 63) | 128));
            } else {
                i2 = i + 1;
                if (i2 != length) {
                    try {
                        char charAt3 = charSequence.charAt(i2);
                        if (Character.isSurrogatePair(charAt, charAt3)) {
                            i = Character.toCodePoint(charAt, charAt3);
                            int i3 = position + 1;
                            try {
                                byteBuffer.put(position, (byte) (PsExtractor.VIDEO_STREAM_MASK | (i >>> 18)));
                                position = i3 + 1;
                                byteBuffer.put(i3, (byte) (((i >>> 12) & 63) | 128));
                                i3 = position + 1;
                                byteBuffer.put(position, (byte) (((i >>> 6) & 63) | 128));
                                byteBuffer.put(i3, (byte) ((63 & i) | 128));
                                position = i3;
                                i = i2;
                            } catch (IndexOutOfBoundsException unused3) {
                                position = i3;
                                i = i2;
                                length = byteBuffer.position() + Math.max(i, (position - byteBuffer.position()) + 1);
                                charAt2 = charSequence.charAt(i);
                                stringBuilder = new StringBuilder(37);
                                stringBuilder.append("Failed writing ");
                                stringBuilder.append(charAt2);
                                stringBuilder.append(" at index ");
                                stringBuilder.append(length);
                                throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
                            }
                        }
                        i = i2;
                    } catch (IndexOutOfBoundsException unused4) {
                        i = i2;
                        length = byteBuffer.position() + Math.max(i, (position - byteBuffer.position()) + 1);
                        charAt2 = charSequence.charAt(i);
                        stringBuilder = new StringBuilder(37);
                        stringBuilder.append("Failed writing ");
                        stringBuilder.append(charAt2);
                        stringBuilder.append(" at index ");
                        stringBuilder.append(length);
                        throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
                    }
                }
                throw new zzxp(i, length);
            }
            i++;
            position++;
        }
        byteBuffer.position(position);
    }
}
