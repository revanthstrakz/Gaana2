package com.google.android.exoplayer2.upstream.cache;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.cache.Cache.CacheException;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import java.io.EOFException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class CacheUtil {
    public static final int DEFAULT_BUFFER_SIZE_BYTES = 131072;
    public static final CacheKeyFactory DEFAULT_CACHE_KEY_FACTORY = CacheUtil$$Lambda$0.$instance;

    public static class CachingCounters {
        public volatile long alreadyCachedBytes;
        public volatile long contentLength = -1;
        public volatile long newlyCachedBytes;

        public long totalCachedBytes() {
            return this.alreadyCachedBytes + this.newlyCachedBytes;
        }
    }

    public static String generateKey(Uri uri) {
        return uri.toString();
    }

    public static String getKey(DataSpec dataSpec) {
        return dataSpec.key != null ? dataSpec.key : generateKey(dataSpec.uri);
    }

    public static void getCached(DataSpec dataSpec, Cache cache, CachingCounters cachingCounters) {
        long j;
        Cache cache2;
        DataSpec dataSpec2 = dataSpec;
        CachingCounters cachingCounters2 = cachingCounters;
        String key = getKey(dataSpec);
        long j2 = dataSpec2.absoluteStreamPosition;
        if (dataSpec2.length != -1) {
            j = dataSpec2.length;
            cache2 = cache;
        } else {
            cache2 = cache;
            j = cache2.getContentLength(key);
        }
        cachingCounters2.contentLength = j;
        cachingCounters2.alreadyCachedBytes = 0;
        cachingCounters2.newlyCachedBytes = 0;
        long j3 = j2;
        long j4 = j;
        while (j4 != 0) {
            j2 = cache2.getCachedLength(key, j3, j4 != -1 ? j4 : Long.MAX_VALUE);
            if (j2 > 0) {
                cachingCounters2.alreadyCachedBytes += j2;
            } else {
                j2 = -j2;
                if (j2 == Long.MAX_VALUE) {
                    return;
                }
            }
            j = j3 + j2;
            if (j4 == -1) {
                j2 = 0;
            }
            j3 = j;
            j4 -= j2;
        }
    }

    public static void cache(DataSpec dataSpec, Cache cache, DataSource dataSource, @Nullable CachingCounters cachingCounters, @Nullable AtomicBoolean atomicBoolean) throws IOException, InterruptedException {
        cache(dataSpec, cache, new CacheDataSource(cache, dataSource), new byte[131072], null, 0, cachingCounters, atomicBoolean, false);
    }

    public static void cache(DataSpec dataSpec, Cache cache, CacheDataSource cacheDataSource, byte[] bArr, PriorityTaskManager priorityTaskManager, int i, @Nullable CachingCounters cachingCounters, @Nullable AtomicBoolean atomicBoolean, boolean z) throws IOException, InterruptedException {
        DataSpec dataSpec2 = dataSpec;
        Cache cache2 = cache;
        CachingCounters cachingCounters2 = cachingCounters;
        Assertions.checkNotNull(cacheDataSource);
        Assertions.checkNotNull(bArr);
        if (cachingCounters2 != null) {
            getCached(dataSpec2, cache2, cachingCounters2);
        } else {
            cachingCounters2 = new CachingCounters();
        }
        CachingCounters cachingCounters3 = cachingCounters2;
        String key = getKey(dataSpec);
        long j = dataSpec2.absoluteStreamPosition;
        long contentLength = dataSpec2.length != -1 ? dataSpec2.length : cache2.getContentLength(key);
        while (true) {
            long j2 = 0;
            if (contentLength != 0) {
                long j3;
                throwExceptionIfInterruptedOrCancelled(atomicBoolean);
                long cachedLength = cache2.getCachedLength(key, j, contentLength != -1 ? contentLength : Long.MAX_VALUE);
                if (cachedLength > 0) {
                    j3 = cachedLength;
                } else {
                    long j4 = -cachedLength;
                    j3 = j4;
                    if (readAndDiscard(dataSpec2, j, j4, cacheDataSource, bArr, priorityTaskManager, i, cachingCounters3, atomicBoolean) < j3) {
                        if (z && contentLength != -1) {
                            throw new EOFException();
                        }
                        return;
                    }
                }
                long j5 = j + j3;
                if (contentLength != -1) {
                    j2 = j3;
                }
                j = j5;
                contentLength -= j2;
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0091 A:{Splitter:B:3:0x000d, ExcHandler: all (r0_0 'th' java.lang.Throwable)} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:29:0x0091, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:30:0x0092, code skipped:
            r1 = r0;
            com.google.android.exoplayer2.util.Util.closeQuietly(r28);
     */
    private static long readAndDiscard(com.google.android.exoplayer2.upstream.DataSpec r23, long r24, long r26, com.google.android.exoplayer2.upstream.DataSource r28, byte[] r29, com.google.android.exoplayer2.util.PriorityTaskManager r30, int r31, com.google.android.exoplayer2.upstream.cache.CacheUtil.CachingCounters r32, java.util.concurrent.atomic.AtomicBoolean r33) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r2 = r28;
        r3 = r29;
        r4 = r32;
        r6 = r23;
    L_0x0008:
        if (r30 == 0) goto L_0x000d;
    L_0x000a:
        r30.proceed(r31);
    L_0x000d:
        throwExceptionIfInterruptedOrCancelled(r33);	 Catch:{ PriorityTooLowException -> 0x0097, all -> 0x0091 }
        r13 = new com.google.android.exoplayer2.upstream.DataSpec;	 Catch:{ PriorityTooLowException -> 0x0097, all -> 0x0091 }
        r8 = r6.uri;	 Catch:{ PriorityTooLowException -> 0x0097, all -> 0x0091 }
        r9 = r6.httpMethod;	 Catch:{ PriorityTooLowException -> 0x0097, all -> 0x0091 }
        r10 = r6.httpBody;	 Catch:{ PriorityTooLowException -> 0x0097, all -> 0x0091 }
        r11 = r6.position;	 Catch:{ PriorityTooLowException -> 0x0097, all -> 0x0091 }
        r14 = r11 + r24;
        r11 = r6.absoluteStreamPosition;	 Catch:{ PriorityTooLowException -> 0x0097, all -> 0x0091 }
        r16 = r14 - r11;
        r19 = -1;
        r14 = r6.key;	 Catch:{ PriorityTooLowException -> 0x0097, all -> 0x0091 }
        r7 = r6.flags;	 Catch:{ PriorityTooLowException -> 0x0097, all -> 0x0091 }
        r18 = r7 | 2;
        r7 = r13;
        r11 = r24;
        r5 = r13;
        r21 = r14;
        r13 = r16;
        r15 = r19;
        r17 = r21;
        r7.<init>(r8, r9, r10, r11, r13, r15, r17, r18);	 Catch:{ PriorityTooLowException -> 0x0097, all -> 0x0091 }
        r6 = r2.open(r5);	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        r8 = r4.contentLength;	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        r10 = -1;
        r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r12 != 0) goto L_0x004d;
    L_0x0043:
        r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
        if (r8 == 0) goto L_0x004d;
    L_0x0047:
        r8 = r5.absoluteStreamPosition;	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        r12 = r8 + r6;
        r4.contentLength = r12;	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
    L_0x004d:
        r6 = 0;
    L_0x004f:
        r8 = (r6 > r26 ? 1 : (r6 == r26 ? 0 : -1));
        if (r8 == 0) goto L_0x008b;
    L_0x0053:
        throwExceptionIfInterruptedOrCancelled(r33);	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        r8 = 0;
        r9 = (r26 > r10 ? 1 : (r26 == r10 ? 0 : -1));
        if (r9 == 0) goto L_0x0065;
    L_0x005b:
        r9 = r3.length;	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        r12 = (long) r9;	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        r10 = r26 - r6;
        r9 = java.lang.Math.min(r12, r10);	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        r9 = (int) r9;	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        goto L_0x0066;
    L_0x0065:
        r9 = r3.length;	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
    L_0x0066:
        r8 = r2.read(r3, r8, r9);	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        r9 = -1;
        if (r8 != r9) goto L_0x007c;
    L_0x006d:
        r8 = r4.contentLength;	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        r10 = -1;
        r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r12 != 0) goto L_0x008b;
    L_0x0075:
        r8 = r5.absoluteStreamPosition;	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        r10 = r8 + r6;
        r4.contentLength = r10;	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        goto L_0x008b;
    L_0x007c:
        r10 = -1;
        r8 = (long) r8;	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        r12 = r6 + r8;
        r6 = r4.newlyCachedBytes;	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        r10 = r6 + r8;
        r4.newlyCachedBytes = r10;	 Catch:{ PriorityTooLowException -> 0x008f, all -> 0x0091 }
        r6 = r12;
        r10 = -1;
        goto L_0x004f;
    L_0x008b:
        com.google.android.exoplayer2.util.Util.closeQuietly(r28);
        return r6;
    L_0x008f:
        r6 = r5;
        goto L_0x0097;
    L_0x0091:
        r0 = move-exception;
        r1 = r0;
        com.google.android.exoplayer2.util.Util.closeQuietly(r28);
        throw r1;
    L_0x0097:
        com.google.android.exoplayer2.util.Util.closeQuietly(r28);
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CacheUtil.readAndDiscard(com.google.android.exoplayer2.upstream.DataSpec, long, long, com.google.android.exoplayer2.upstream.DataSource, byte[], com.google.android.exoplayer2.util.PriorityTaskManager, int, com.google.android.exoplayer2.upstream.cache.CacheUtil$CachingCounters, java.util.concurrent.atomic.AtomicBoolean):long");
    }

    public static void remove(Cache cache, String str) {
        for (CacheSpan removeSpan : cache.getCachedSpans(str)) {
            try {
                cache.removeSpan(removeSpan);
            } catch (CacheException unused) {
            }
        }
    }

    private static void throwExceptionIfInterruptedOrCancelled(AtomicBoolean atomicBoolean) throws InterruptedException {
        if (Thread.interrupted() || (atomicBoolean != null && atomicBoolean.get())) {
            throw new InterruptedException();
        }
    }

    private CacheUtil() {
    }
}
