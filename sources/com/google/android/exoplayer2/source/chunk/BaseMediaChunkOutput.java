package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.extractor.DummyTrackOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper.TrackOutputProvider;
import com.google.android.exoplayer2.util.Log;

public final class BaseMediaChunkOutput implements TrackOutputProvider {
    private static final String TAG = "BaseMediaChunkOutput";
    private final SampleQueue[] sampleQueues;
    private final int[] trackTypes;

    public BaseMediaChunkOutput(int[] iArr, SampleQueue[] sampleQueueArr) {
        this.trackTypes = iArr;
        this.sampleQueues = sampleQueueArr;
    }

    public TrackOutput track(int i, int i2) {
        for (i = 0; i < this.trackTypes.length; i++) {
            if (i2 == this.trackTypes[i]) {
                return this.sampleQueues[i];
            }
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unmatched track of type: ");
        stringBuilder.append(i2);
        Log.e(str, stringBuilder.toString());
        return new DummyTrackOutput();
    }

    public int[] getWriteIndices() {
        int[] iArr = new int[this.sampleQueues.length];
        for (int i = 0; i < this.sampleQueues.length; i++) {
            if (this.sampleQueues[i] != null) {
                iArr[i] = this.sampleQueues[i].getWriteIndex();
            }
        }
        return iArr;
    }

    public void setSampleOffsetUs(long j) {
        for (SampleQueue sampleQueue : this.sampleQueues) {
            if (sampleQueue != null) {
                sampleQueue.setSampleOffsetUs(j);
            }
        }
    }
}
