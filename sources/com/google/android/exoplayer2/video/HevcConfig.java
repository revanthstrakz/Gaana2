package com.google.android.exoplayer2.video;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;
import java.util.List;

public final class HevcConfig {
    @Nullable
    public final List<byte[]> initializationData;
    public final int nalUnitLengthFieldLength;

    public static HevcConfig parse(ParsableByteArray parsableByteArray) throws ParserException {
        try {
            int i;
            int readUnsignedShort;
            List list;
            parsableByteArray.skipBytes(21);
            int readUnsignedByte = parsableByteArray.readUnsignedByte() & 3;
            int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition();
            int i2 = 0;
            int i3 = i2;
            while (i2 < readUnsignedByte2) {
                parsableByteArray.skipBytes(1);
                int readUnsignedShort2 = parsableByteArray.readUnsignedShort();
                i = i3;
                for (i3 = 0; i3 < readUnsignedShort2; i3++) {
                    readUnsignedShort = parsableByteArray.readUnsignedShort();
                    i += 4 + readUnsignedShort;
                    parsableByteArray.skipBytes(readUnsignedShort);
                }
                i2++;
                i3 = i;
            }
            parsableByteArray.setPosition(position);
            byte[] bArr = new byte[i3];
            i2 = 0;
            i = i2;
            while (i2 < readUnsignedByte2) {
                parsableByteArray.skipBytes(1);
                readUnsignedShort = parsableByteArray.readUnsignedShort();
                int i4 = i;
                for (i = 0; i < readUnsignedShort; i++) {
                    int readUnsignedShort3 = parsableByteArray.readUnsignedShort();
                    System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, bArr, i4, NalUnitUtil.NAL_START_CODE.length);
                    i4 += NalUnitUtil.NAL_START_CODE.length;
                    System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), bArr, i4, readUnsignedShort3);
                    i4 += readUnsignedShort3;
                    parsableByteArray.skipBytes(readUnsignedShort3);
                }
                i2++;
                i = i4;
            }
            if (i3 == 0) {
                list = null;
            } else {
                list = Collections.singletonList(bArr);
            }
            return new HevcConfig(list, readUnsignedByte + 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParserException("Error parsing HEVC config", e);
        }
    }

    private HevcConfig(@Nullable List<byte[]> list, int i) {
        this.initializationData = list;
        this.nalUnitLengthFieldLength = i;
    }
}
