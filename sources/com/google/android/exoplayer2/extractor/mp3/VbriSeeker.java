package com.google.android.exoplayer2.extractor.mp3;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.extractor.MpegAudioHeader;
import com.google.android.exoplayer2.extractor.SeekMap.SeekPoints;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

final class VbriSeeker implements Seeker {
    private static final String TAG = "VbriSeeker";
    private final long dataEndPosition;
    private final long durationUs;
    private final long[] positions;
    private final long[] timesUs;

    public boolean isSeekable() {
        return true;
    }

    @Nullable
    public static VbriSeeker create(long j, long j2, MpegAudioHeader mpegAudioHeader, ParsableByteArray parsableByteArray) {
        long j3 = j;
        MpegAudioHeader mpegAudioHeader2 = mpegAudioHeader;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.skipBytes(10);
        int readInt = parsableByteArray.readInt();
        if (readInt <= 0) {
            return null;
        }
        int i = mpegAudioHeader2.sampleRate;
        long scaleLargeTimestamp = Util.scaleLargeTimestamp((long) readInt, 1000000 * ((long) (i >= 32000 ? 1152 : 576)), (long) i);
        readInt = parsableByteArray.readUnsignedShort();
        i = parsableByteArray.readUnsignedShort();
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        parsableByteArray2.skipBytes(2);
        long j4 = j2 + ((long) mpegAudioHeader2.frameSize);
        long[] jArr = new long[readInt];
        long[] jArr2 = new long[readInt];
        int i2 = 0;
        long j5 = j2;
        while (i2 < readInt) {
            int readUnsignedByte;
            int i3 = i;
            jArr[i2] = (((long) i2) * scaleLargeTimestamp) / ((long) readInt);
            jArr2[i2] = Math.max(j5, j4);
            switch (readUnsignedShort) {
                case 1:
                    readUnsignedByte = parsableByteArray.readUnsignedByte();
                    break;
                case 2:
                    readUnsignedByte = parsableByteArray.readUnsignedShort();
                    break;
                case 3:
                    readUnsignedByte = parsableByteArray.readUnsignedInt24();
                    break;
                case 4:
                    readUnsignedByte = parsableByteArray.readUnsignedIntToInt();
                    break;
                default:
                    return null;
            }
            i2++;
            j5 += (long) (readUnsignedByte * i3);
            i = i3;
        }
        if (!(j3 == -1 || j3 == j5)) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("VBRI data size mismatch: ");
            stringBuilder.append(j3);
            stringBuilder.append(", ");
            stringBuilder.append(j5);
            Log.w(str, stringBuilder.toString());
        }
        return new VbriSeeker(jArr, jArr2, scaleLargeTimestamp, j5);
    }

    private VbriSeeker(long[] jArr, long[] jArr2, long j, long j2) {
        this.timesUs = jArr;
        this.positions = jArr2;
        this.durationUs = j;
        this.dataEndPosition = j2;
    }

    public SeekPoints getSeekPoints(long j) {
        int binarySearchFloor = Util.binarySearchFloor(this.timesUs, j, true, true);
        SeekPoint seekPoint = new SeekPoint(this.timesUs[binarySearchFloor], this.positions[binarySearchFloor]);
        if (seekPoint.timeUs >= j || binarySearchFloor == this.timesUs.length - 1) {
            return new SeekPoints(seekPoint);
        }
        binarySearchFloor++;
        return new SeekPoints(seekPoint, new SeekPoint(this.timesUs[binarySearchFloor], this.positions[binarySearchFloor]));
    }

    public long getTimeUs(long j) {
        return this.timesUs[Util.binarySearchFloor(this.positions, j, true, true)];
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public long getDataEndPosition() {
        return this.dataEndPosition;
    }
}
