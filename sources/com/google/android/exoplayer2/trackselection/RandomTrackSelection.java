package com.google.android.exoplayer2.trackselection;

import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunkIterator;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import java.util.List;
import java.util.Random;

public final class RandomTrackSelection extends BaseTrackSelection {
    private final Random random;
    private int selectedIndex;

    public static final class Factory implements com.google.android.exoplayer2.trackselection.TrackSelection.Factory {
        private final Random random;

        public Factory() {
            this.random = new Random();
        }

        public Factory(int i) {
            this.random = new Random((long) i);
        }

        public RandomTrackSelection createTrackSelection(TrackGroup trackGroup, BandwidthMeter bandwidthMeter, int... iArr) {
            return new RandomTrackSelection(trackGroup, iArr, this.random);
        }
    }

    @Nullable
    public Object getSelectionData() {
        return null;
    }

    public int getSelectionReason() {
        return 3;
    }

    public RandomTrackSelection(TrackGroup trackGroup, int... iArr) {
        super(trackGroup, iArr);
        this.random = new Random();
        this.selectedIndex = this.random.nextInt(this.length);
    }

    public RandomTrackSelection(TrackGroup trackGroup, int[] iArr, long j) {
        this(trackGroup, iArr, new Random(j));
    }

    public RandomTrackSelection(TrackGroup trackGroup, int[] iArr, Random random) {
        super(trackGroup, iArr);
        this.random = random;
        this.selectedIndex = random.nextInt(this.length);
    }

    public void updateSelectedTrack(long j, long j2, long j3, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr) {
        j = SystemClock.elapsedRealtime();
        int i = 0;
        int i2 = 0;
        int i3 = i2;
        while (i2 < this.length) {
            if (!isBlacklisted(i2, j)) {
                i3++;
            }
            i2++;
        }
        this.selectedIndex = this.random.nextInt(i3);
        if (i3 != this.length) {
            i2 = 0;
            while (i < this.length) {
                if (!isBlacklisted(i, j)) {
                    int i4 = i2 + 1;
                    if (this.selectedIndex == i2) {
                        this.selectedIndex = i;
                        return;
                    }
                    i2 = i4;
                }
                i++;
            }
        }
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }
}
