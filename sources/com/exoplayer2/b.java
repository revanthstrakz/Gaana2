package com.exoplayer2;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Util;

public class b implements LoadControl {
    private final DefaultAllocator a;
    private final long b;
    private long c;
    private final long d;
    private final long e;
    private final int f;
    private final boolean g;
    private final PriorityTaskManager h;
    private final long i;
    private final boolean j;
    private int k;
    private boolean l;

    public static final class a {
        private DefaultAllocator a = null;
        private int b = 3000;
        private int c = 5000;
        private int d = 1000;
        private int e = 3000;
        private int f = -1;
        private boolean g = true;
        private PriorityTaskManager h = null;
        private int i = 0;
        private boolean j = false;
        private boolean k;

        public a a(int i) {
            Assertions.checkState(this.k ^ 1);
            this.f = i;
            return this;
        }

        public a a(boolean z) {
            Assertions.checkState(this.k ^ 1);
            this.g = z;
            return this;
        }

        public b a() {
            this.k = true;
            if (this.a == null) {
                this.a = new DefaultAllocator(true, 65536);
            }
            return new b(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
        }
    }

    public b() {
        this(new DefaultAllocator(true, 65536));
    }

    @Deprecated
    public b(DefaultAllocator defaultAllocator) {
        this(defaultAllocator, 3000, 5000, 1000, 3000, -1, true);
    }

    @Deprecated
    public b(DefaultAllocator defaultAllocator, int i, int i2, int i3, int i4, int i5, boolean z) {
        this(defaultAllocator, i, i2, i3, i4, i5, z, null);
    }

    @Deprecated
    public b(DefaultAllocator defaultAllocator, int i, int i2, int i3, int i4, int i5, boolean z, PriorityTaskManager priorityTaskManager) {
        this(defaultAllocator, i, i2, i3, i4, i5, z, priorityTaskManager, 0, false);
    }

    protected b(DefaultAllocator defaultAllocator, int i, int i2, int i3, int i4, int i5, boolean z, PriorityTaskManager priorityTaskManager, int i6, boolean z2) {
        a(i3, 0, "bufferForPlaybackMs", "0");
        a(i4, 0, "bufferForPlaybackAfterRebufferMs", "0");
        a(i, i3, "minBufferMs", "bufferForPlaybackMs");
        a(i, i4, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
        a(i2, i, "maxBufferMs", "minBufferMs");
        a(i6, 0, "backBufferDurationMs", "0");
        this.a = defaultAllocator;
        this.b = C.msToUs((long) i);
        this.c = C.msToUs((long) i2);
        this.d = C.msToUs((long) i3);
        this.e = C.msToUs((long) i4);
        this.f = i5;
        this.g = z;
        this.h = priorityTaskManager;
        this.i = C.msToUs((long) i6);
        this.j = z2;
    }

    public void a(boolean z) {
        if (z) {
            this.c = 600000000;
        } else {
            this.c = 5000000;
        }
    }

    public void onPrepared() {
        b(false);
    }

    public void onTracksSelected(Renderer[] rendererArr, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        this.k = this.f == -1 ? a(rendererArr, trackSelectionArray) : this.f;
        this.a.setTargetBufferSize(this.k);
    }

    public void onStopped() {
        b(true);
    }

    public void onReleased() {
        b(true);
    }

    public Allocator getAllocator() {
        return this.a;
    }

    public long getBackBufferDurationUs() {
        return this.i;
    }

    public boolean retainBackBufferFromKeyframe() {
        return this.j;
    }

    public boolean shouldContinueLoading(long j, float f) {
        boolean z = true;
        boolean z2 = this.a.getTotalBytesAllocated() >= this.k;
        boolean z3 = this.l;
        long j2 = this.b;
        if (f > 1.0f) {
            j2 = Math.min(Util.getMediaDurationForPlayoutDuration(j2, f), this.c);
        }
        if (j < j2) {
            if (!this.g && z2) {
                z = false;
            }
            this.l = z;
        } else if (j >= this.c || z2) {
            this.l = false;
        }
        if (!(this.h == null || this.l == z3)) {
            if (this.l) {
                this.h.add(0);
            } else {
                this.h.remove(0);
            }
        }
        return this.l;
    }

    public boolean shouldStartPlayback(long j, float f, boolean z) {
        j = Util.getPlayoutDurationForMediaDuration(j, f);
        long j2 = z ? this.e : this.d;
        return j2 <= 0 || j >= j2 || (!this.g && this.a.getTotalBytesAllocated() >= this.k);
    }

    /* Access modifiers changed, original: protected */
    public int a(Renderer[] rendererArr, TrackSelectionArray trackSelectionArray) {
        int i = 0;
        int i2 = 0;
        while (i < rendererArr.length) {
            if (trackSelectionArray.get(i) != null) {
                i2 += Util.getDefaultBufferSize(rendererArr[i].getTrackType());
            }
            i++;
        }
        return i2;
    }

    private void b(boolean z) {
        this.k = 0;
        if (this.h != null && this.l) {
            this.h.remove(0);
        }
        this.l = false;
        if (z) {
            this.a.reset();
        }
    }

    private static void a(int i, int i2, String str, String str2) {
        boolean z = i >= i2;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" cannot be less than ");
        stringBuilder.append(str2);
        Assertions.checkArgument(z, stringBuilder.toString());
    }
}
