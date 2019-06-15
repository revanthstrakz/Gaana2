package com.google.android.exoplayer2.upstream.cache;

import android.support.annotation.NonNull;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.upstream.cache.Cache.Listener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public final class CachedRegionTracker implements Listener {
    public static final int CACHED_TO_END = -2;
    public static final int NOT_CACHED = -1;
    private static final String TAG = "CachedRegionTracker";
    private final Cache cache;
    private final String cacheKey;
    private final ChunkIndex chunkIndex;
    private final Region lookupRegion = new Region(0, 0);
    private final TreeSet<Region> regions = new TreeSet();

    private static class Region implements Comparable<Region> {
        public long endOffset;
        public int endOffsetIndex;
        public long startOffset;

        public Region(long j, long j2) {
            this.startOffset = j;
            this.endOffset = j2;
        }

        public int compareTo(@NonNull Region region) {
            if (this.startOffset < region.startOffset) {
                return -1;
            }
            return this.startOffset == region.startOffset ? 0 : 1;
        }
    }

    public void onSpanTouched(Cache cache, CacheSpan cacheSpan, CacheSpan cacheSpan2) {
    }

    public CachedRegionTracker(Cache cache, String str, ChunkIndex chunkIndex) {
        this.cache = cache;
        this.cacheKey = str;
        this.chunkIndex = chunkIndex;
        synchronized (this) {
            Iterator descendingIterator = cache.addListener(str, this).descendingIterator();
            while (descendingIterator.hasNext()) {
                mergeSpan((CacheSpan) descendingIterator.next());
            }
        }
    }

    public void release() {
        this.cache.removeListener(this.cacheKey, this);
    }

    /* JADX WARNING: Missing block: B:23:0x0067, code skipped:
            return -1;
     */
    public synchronized int getRegionEndTimeMs(long r10) {
        /*
        r9 = this;
        monitor-enter(r9);
        r0 = r9.lookupRegion;	 Catch:{ all -> 0x0068 }
        r0.startOffset = r10;	 Catch:{ all -> 0x0068 }
        r0 = r9.regions;	 Catch:{ all -> 0x0068 }
        r1 = r9.lookupRegion;	 Catch:{ all -> 0x0068 }
        r0 = r0.floor(r1);	 Catch:{ all -> 0x0068 }
        r0 = (com.google.android.exoplayer2.upstream.cache.CachedRegionTracker.Region) r0;	 Catch:{ all -> 0x0068 }
        r1 = -1;
        if (r0 == 0) goto L_0x0066;
    L_0x0012:
        r2 = r0.endOffset;	 Catch:{ all -> 0x0068 }
        r4 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1));
        if (r4 > 0) goto L_0x0066;
    L_0x0018:
        r10 = r0.endOffsetIndex;	 Catch:{ all -> 0x0068 }
        if (r10 != r1) goto L_0x001d;
    L_0x001c:
        goto L_0x0066;
    L_0x001d:
        r10 = r0.endOffsetIndex;	 Catch:{ all -> 0x0068 }
        r11 = r9.chunkIndex;	 Catch:{ all -> 0x0068 }
        r11 = r11.length;	 Catch:{ all -> 0x0068 }
        r11 = r11 + -1;
        if (r10 != r11) goto L_0x003f;
    L_0x0027:
        r1 = r0.endOffset;	 Catch:{ all -> 0x0068 }
        r11 = r9.chunkIndex;	 Catch:{ all -> 0x0068 }
        r11 = r11.offsets;	 Catch:{ all -> 0x0068 }
        r3 = r11[r10];	 Catch:{ all -> 0x0068 }
        r11 = r9.chunkIndex;	 Catch:{ all -> 0x0068 }
        r11 = r11.sizes;	 Catch:{ all -> 0x0068 }
        r11 = r11[r10];	 Catch:{ all -> 0x0068 }
        r5 = (long) r11;
        r7 = r3 + r5;
        r11 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1));
        if (r11 != 0) goto L_0x003f;
    L_0x003c:
        r10 = -2;
        monitor-exit(r9);
        return r10;
    L_0x003f:
        r11 = r9.chunkIndex;	 Catch:{ all -> 0x0068 }
        r11 = r11.durationsUs;	 Catch:{ all -> 0x0068 }
        r1 = r11[r10];	 Catch:{ all -> 0x0068 }
        r3 = r0.endOffset;	 Catch:{ all -> 0x0068 }
        r11 = r9.chunkIndex;	 Catch:{ all -> 0x0068 }
        r11 = r11.offsets;	 Catch:{ all -> 0x0068 }
        r5 = r11[r10];	 Catch:{ all -> 0x0068 }
        r7 = r3 - r5;
        r1 = r1 * r7;
        r11 = r9.chunkIndex;	 Catch:{ all -> 0x0068 }
        r11 = r11.sizes;	 Catch:{ all -> 0x0068 }
        r11 = r11[r10];	 Catch:{ all -> 0x0068 }
        r3 = (long) r11;	 Catch:{ all -> 0x0068 }
        r1 = r1 / r3;
        r11 = r9.chunkIndex;	 Catch:{ all -> 0x0068 }
        r11 = r11.timesUs;	 Catch:{ all -> 0x0068 }
        r10 = r11[r10];	 Catch:{ all -> 0x0068 }
        r3 = r10 + r1;
        r10 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r3 = r3 / r10;
        r10 = (int) r3;
        monitor-exit(r9);
        return r10;
    L_0x0066:
        monitor-exit(r9);
        return r1;
    L_0x0068:
        r10 = move-exception;
        monitor-exit(r9);
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CachedRegionTracker.getRegionEndTimeMs(long):int");
    }

    public synchronized void onSpanAdded(Cache cache, CacheSpan cacheSpan) {
        mergeSpan(cacheSpan);
    }

    /* JADX WARNING: Missing block: B:18:0x006c, code skipped:
            return;
     */
    public synchronized void onSpanRemoved(com.google.android.exoplayer2.upstream.cache.Cache r9, com.google.android.exoplayer2.upstream.cache.CacheSpan r10) {
        /*
        r8 = this;
        monitor-enter(r8);
        r9 = new com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region;	 Catch:{ all -> 0x006d }
        r0 = r10.position;	 Catch:{ all -> 0x006d }
        r2 = r10.position;	 Catch:{ all -> 0x006d }
        r4 = r10.length;	 Catch:{ all -> 0x006d }
        r6 = r2 + r4;
        r9.<init>(r0, r6);	 Catch:{ all -> 0x006d }
        r10 = r8.regions;	 Catch:{ all -> 0x006d }
        r10 = r10.floor(r9);	 Catch:{ all -> 0x006d }
        r10 = (com.google.android.exoplayer2.upstream.cache.CachedRegionTracker.Region) r10;	 Catch:{ all -> 0x006d }
        if (r10 != 0) goto L_0x0021;
    L_0x0018:
        r9 = "CachedRegionTracker";
        r10 = "Removed a span we were not aware of";
        com.google.android.exoplayer2.util.Log.e(r9, r10);	 Catch:{ all -> 0x006d }
        monitor-exit(r8);
        return;
    L_0x0021:
        r0 = r8.regions;	 Catch:{ all -> 0x006d }
        r0.remove(r10);	 Catch:{ all -> 0x006d }
        r0 = r10.startOffset;	 Catch:{ all -> 0x006d }
        r2 = r9.startOffset;	 Catch:{ all -> 0x006d }
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 >= 0) goto L_0x004d;
    L_0x002e:
        r0 = new com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region;	 Catch:{ all -> 0x006d }
        r1 = r10.startOffset;	 Catch:{ all -> 0x006d }
        r3 = r9.startOffset;	 Catch:{ all -> 0x006d }
        r0.<init>(r1, r3);	 Catch:{ all -> 0x006d }
        r1 = r8.chunkIndex;	 Catch:{ all -> 0x006d }
        r1 = r1.offsets;	 Catch:{ all -> 0x006d }
        r2 = r0.endOffset;	 Catch:{ all -> 0x006d }
        r1 = java.util.Arrays.binarySearch(r1, r2);	 Catch:{ all -> 0x006d }
        if (r1 >= 0) goto L_0x0046;
    L_0x0043:
        r1 = -r1;
        r1 = r1 + -2;
    L_0x0046:
        r0.endOffsetIndex = r1;	 Catch:{ all -> 0x006d }
        r1 = r8.regions;	 Catch:{ all -> 0x006d }
        r1.add(r0);	 Catch:{ all -> 0x006d }
    L_0x004d:
        r0 = r10.endOffset;	 Catch:{ all -> 0x006d }
        r2 = r9.endOffset;	 Catch:{ all -> 0x006d }
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 <= 0) goto L_0x006b;
    L_0x0055:
        r0 = new com.google.android.exoplayer2.upstream.cache.CachedRegionTracker$Region;	 Catch:{ all -> 0x006d }
        r1 = r9.endOffset;	 Catch:{ all -> 0x006d }
        r3 = 1;
        r5 = r1 + r3;
        r1 = r10.endOffset;	 Catch:{ all -> 0x006d }
        r0.<init>(r5, r1);	 Catch:{ all -> 0x006d }
        r9 = r10.endOffsetIndex;	 Catch:{ all -> 0x006d }
        r0.endOffsetIndex = r9;	 Catch:{ all -> 0x006d }
        r9 = r8.regions;	 Catch:{ all -> 0x006d }
        r9.add(r0);	 Catch:{ all -> 0x006d }
    L_0x006b:
        monitor-exit(r8);
        return;
    L_0x006d:
        r9 = move-exception;
        monitor-exit(r8);
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CachedRegionTracker.onSpanRemoved(com.google.android.exoplayer2.upstream.cache.Cache, com.google.android.exoplayer2.upstream.cache.CacheSpan):void");
    }

    private void mergeSpan(CacheSpan cacheSpan) {
        Region region = new Region(cacheSpan.position, cacheSpan.position + cacheSpan.length);
        Region region2 = (Region) this.regions.floor(region);
        Region region3 = (Region) this.regions.ceiling(region);
        boolean regionsConnect = regionsConnect(region2, region);
        if (regionsConnect(region, region3)) {
            if (regionsConnect) {
                region2.endOffset = region3.endOffset;
                region2.endOffsetIndex = region3.endOffsetIndex;
            } else {
                region.endOffset = region3.endOffset;
                region.endOffsetIndex = region3.endOffsetIndex;
                this.regions.add(region);
            }
            this.regions.remove(region3);
        } else if (regionsConnect) {
            region2.endOffset = region.endOffset;
            int i = region2.endOffsetIndex;
            while (i < this.chunkIndex.length - 1) {
                int i2 = i + 1;
                if (this.chunkIndex.offsets[i2] > region2.endOffset) {
                    break;
                }
                i = i2;
            }
            region2.endOffsetIndex = i;
        } else {
            int binarySearch = Arrays.binarySearch(this.chunkIndex.offsets, region.endOffset);
            if (binarySearch < 0) {
                binarySearch = (-binarySearch) - 2;
            }
            region.endOffsetIndex = binarySearch;
            this.regions.add(region);
        }
    }

    private boolean regionsConnect(Region region, Region region2) {
        return (region == null || region2 == null || region.endOffset != region2.startOffset) ? false : true;
    }
}
