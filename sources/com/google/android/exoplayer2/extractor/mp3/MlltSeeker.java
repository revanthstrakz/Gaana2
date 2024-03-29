package com.google.android.exoplayer2.extractor.mp3;

import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.SeekMap.SeekPoints;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.metadata.id3.MlltFrame;
import com.google.android.exoplayer2.util.Util;

final class MlltSeeker implements Seeker {
    private final long durationUs;
    private final long[] referencePositions;
    private final long[] referenceTimesMs;

    public long getDataEndPosition() {
        return -1;
    }

    public boolean isSeekable() {
        return true;
    }

    public static MlltSeeker create(long j, MlltFrame mlltFrame) {
        long j2 = 0;
        int i = 1;
        int length = mlltFrame.bytesDeviations.length;
        int i2 = 1 + length;
        long[] jArr = new long[i2];
        long[] jArr2 = new long[i2];
        jArr[0] = j;
        jArr2[0] = 0;
        while (i <= length) {
            int i3 = i - 1;
            long j3 = j + ((long) (mlltFrame.bytesBetweenReference + mlltFrame.bytesDeviations[i3]));
            long j4 = j2 + ((long) (mlltFrame.millisecondsBetweenReference + mlltFrame.millisecondsDeviations[i3]));
            jArr[i] = j3;
            jArr2[i] = j4;
            i++;
            j2 = j4;
            j = j3;
        }
        return new MlltSeeker(jArr, jArr2);
    }

    private MlltSeeker(long[] jArr, long[] jArr2) {
        this.referencePositions = jArr;
        this.referenceTimesMs = jArr2;
        this.durationUs = C.msToUs(jArr2[jArr2.length - 1]);
    }

    public SeekPoints getSeekPoints(long j) {
        Pair linearlyInterpolate = linearlyInterpolate(C.usToMs(Util.constrainValue(j, 0, this.durationUs)), this.referenceTimesMs, this.referencePositions);
        return new SeekPoints(new SeekPoint(C.msToUs(((Long) linearlyInterpolate.first).longValue()), ((Long) linearlyInterpolate.second).longValue()));
    }

    public long getTimeUs(long j) {
        return C.msToUs(((Long) linearlyInterpolate(j, this.referencePositions, this.referenceTimesMs).second).longValue());
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    private static Pair<Long, Long> linearlyInterpolate(long j, long[] jArr, long[] jArr2) {
        int binarySearchFloor = Util.binarySearchFloor(jArr, j, true, true);
        long j2 = jArr[binarySearchFloor];
        long j3 = jArr2[binarySearchFloor];
        binarySearchFloor++;
        if (binarySearchFloor == jArr.length) {
            return Pair.create(Long.valueOf(j2), Long.valueOf(j3));
        }
        long j4 = jArr[binarySearchFloor];
        return Pair.create(Long.valueOf(j), Long.valueOf(((long) ((j4 == j2 ? 0.0d : (((double) j) - ((double) j2)) / ((double) (j4 - j2))) * ((double) (jArr2[binarySearchFloor] - j3)))) + j3));
    }
}
