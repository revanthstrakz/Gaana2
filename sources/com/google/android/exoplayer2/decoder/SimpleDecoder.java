package com.google.android.exoplayer2.decoder;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayDeque;

public abstract class SimpleDecoder<I extends DecoderInputBuffer, O extends OutputBuffer, E extends Exception> implements Decoder<I, O, E> {
    private int availableInputBufferCount;
    private final I[] availableInputBuffers;
    private int availableOutputBufferCount;
    private final O[] availableOutputBuffers;
    private final Thread decodeThread;
    private I dequeuedInputBuffer;
    private E exception;
    private boolean flushed;
    private final Object lock = new Object();
    private final ArrayDeque<I> queuedInputBuffers = new ArrayDeque();
    private final ArrayDeque<O> queuedOutputBuffers = new ArrayDeque();
    private boolean released;
    private int skippedOutputBufferCount;

    public abstract I createInputBuffer();

    public abstract O createOutputBuffer();

    public abstract E createUnexpectedDecodeException(Throwable th);

    @Nullable
    public abstract E decode(I i, O o, boolean z);

    protected SimpleDecoder(I[] iArr, O[] oArr) {
        this.availableInputBuffers = iArr;
        int i = 0;
        this.availableInputBufferCount = iArr.length;
        for (int i2 = 0; i2 < this.availableInputBufferCount; i2++) {
            this.availableInputBuffers[i2] = createInputBuffer();
        }
        this.availableOutputBuffers = oArr;
        this.availableOutputBufferCount = oArr.length;
        while (i < this.availableOutputBufferCount) {
            this.availableOutputBuffers[i] = createOutputBuffer();
            i++;
        }
        this.decodeThread = new Thread() {
            public void run() {
                SimpleDecoder.this.run();
            }
        };
        this.decodeThread.start();
    }

    /* Access modifiers changed, original: protected|final */
    public final void setInitialInputBufferSize(int i) {
        int i2 = 0;
        Assertions.checkState(this.availableInputBufferCount == this.availableInputBuffers.length);
        DecoderInputBuffer[] decoderInputBufferArr = this.availableInputBuffers;
        int length = decoderInputBufferArr.length;
        while (i2 < length) {
            decoderInputBufferArr[i2].ensureSpaceForWrite(i);
            i2++;
        }
    }

    public final I dequeueInputBuffer() throws Exception {
        DecoderInputBuffer decoderInputBuffer;
        synchronized (this.lock) {
            maybeThrowException();
            Assertions.checkState(this.dequeuedInputBuffer == null);
            if (this.availableInputBufferCount == 0) {
                decoderInputBuffer = null;
            } else {
                DecoderInputBuffer[] decoderInputBufferArr = this.availableInputBuffers;
                int i = this.availableInputBufferCount - 1;
                this.availableInputBufferCount = i;
                decoderInputBuffer = decoderInputBufferArr[i];
            }
            this.dequeuedInputBuffer = decoderInputBuffer;
            decoderInputBuffer = this.dequeuedInputBuffer;
        }
        return decoderInputBuffer;
    }

    public final void queueInputBuffer(I i) throws Exception {
        synchronized (this.lock) {
            maybeThrowException();
            Assertions.checkArgument(i == this.dequeuedInputBuffer);
            this.queuedInputBuffers.addLast(i);
            maybeNotifyDecodeLoop();
            this.dequeuedInputBuffer = null;
        }
    }

    public final O dequeueOutputBuffer() throws Exception {
        synchronized (this.lock) {
            maybeThrowException();
            if (this.queuedOutputBuffers.isEmpty()) {
                return null;
            }
            OutputBuffer outputBuffer = (OutputBuffer) this.queuedOutputBuffers.removeFirst();
            return outputBuffer;
        }
    }

    /* Access modifiers changed, original: protected */
    public void releaseOutputBuffer(O o) {
        synchronized (this.lock) {
            releaseOutputBufferInternal(o);
            maybeNotifyDecodeLoop();
        }
    }

    public final void flush() {
        synchronized (this.lock) {
            this.flushed = true;
            this.skippedOutputBufferCount = 0;
            if (this.dequeuedInputBuffer != null) {
                releaseInputBufferInternal(this.dequeuedInputBuffer);
                this.dequeuedInputBuffer = null;
            }
            while (!this.queuedInputBuffers.isEmpty()) {
                releaseInputBufferInternal((DecoderInputBuffer) this.queuedInputBuffers.removeFirst());
            }
            while (!this.queuedOutputBuffers.isEmpty()) {
                ((OutputBuffer) this.queuedOutputBuffers.removeFirst()).release();
            }
        }
    }

    public void release() {
        synchronized (this.lock) {
            this.released = true;
            this.lock.notify();
        }
        try {
            this.decodeThread.join();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    private void maybeThrowException() throws Exception {
        if (this.exception != null) {
            throw this.exception;
        }
    }

    private void maybeNotifyDecodeLoop() {
        if (canDecodeBuffer()) {
            this.lock.notify();
        }
    }

    private void run() {
        while (decode()) {
            try {
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /* JADX WARNING: Missing block: B:15:0x0035, code skipped:
            if (r1.isEndOfStream() == false) goto L_0x003c;
     */
    /* JADX WARNING: Missing block: B:16:0x0037, code skipped:
            r3.addFlag(4);
     */
    /* JADX WARNING: Missing block: B:18:0x0040, code skipped:
            if (r1.isDecodeOnly() == false) goto L_0x0047;
     */
    /* JADX WARNING: Missing block: B:19:0x0042, code skipped:
            r3.addFlag(Integer.MIN_VALUE);
     */
    /* JADX WARNING: Missing block: B:21:?, code skipped:
            r6.exception = decode(r1, r3, r4);
     */
    /* JADX WARNING: Missing block: B:22:0x004e, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:23:0x004f, code skipped:
            r6.exception = createUnexpectedDecodeException(r0);
     */
    /* JADX WARNING: Missing block: B:24:0x0056, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:25:0x0057, code skipped:
            r6.exception = createUnexpectedDecodeException(r0);
     */
    private boolean decode() throws java.lang.InterruptedException {
        /*
        r6 = this;
        r0 = r6.lock;
        monitor-enter(r0);
    L_0x0003:
        r1 = r6.released;	 Catch:{ all -> 0x0096 }
        if (r1 != 0) goto L_0x0013;
    L_0x0007:
        r1 = r6.canDecodeBuffer();	 Catch:{ all -> 0x0096 }
        if (r1 != 0) goto L_0x0013;
    L_0x000d:
        r1 = r6.lock;	 Catch:{ all -> 0x0096 }
        r1.wait();	 Catch:{ all -> 0x0096 }
        goto L_0x0003;
    L_0x0013:
        r1 = r6.released;	 Catch:{ all -> 0x0096 }
        r2 = 0;
        if (r1 == 0) goto L_0x001a;
    L_0x0018:
        monitor-exit(r0);	 Catch:{ all -> 0x0096 }
        return r2;
    L_0x001a:
        r1 = r6.queuedInputBuffers;	 Catch:{ all -> 0x0096 }
        r1 = r1.removeFirst();	 Catch:{ all -> 0x0096 }
        r1 = (com.google.android.exoplayer2.decoder.DecoderInputBuffer) r1;	 Catch:{ all -> 0x0096 }
        r3 = r6.availableOutputBuffers;	 Catch:{ all -> 0x0096 }
        r4 = r6.availableOutputBufferCount;	 Catch:{ all -> 0x0096 }
        r5 = 1;
        r4 = r4 - r5;
        r6.availableOutputBufferCount = r4;	 Catch:{ all -> 0x0096 }
        r3 = r3[r4];	 Catch:{ all -> 0x0096 }
        r4 = r6.flushed;	 Catch:{ all -> 0x0096 }
        r6.flushed = r2;	 Catch:{ all -> 0x0096 }
        monitor-exit(r0);	 Catch:{ all -> 0x0096 }
        r0 = r1.isEndOfStream();
        if (r0 == 0) goto L_0x003c;
    L_0x0037:
        r0 = 4;
        r3.addFlag(r0);
        goto L_0x0069;
    L_0x003c:
        r0 = r1.isDecodeOnly();
        if (r0 == 0) goto L_0x0047;
    L_0x0042:
        r0 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r3.addFlag(r0);
    L_0x0047:
        r0 = r6.decode(r1, r3, r4);	 Catch:{ RuntimeException -> 0x0056, OutOfMemoryError -> 0x004e }
        r6.exception = r0;	 Catch:{ RuntimeException -> 0x0056, OutOfMemoryError -> 0x004e }
        goto L_0x005d;
    L_0x004e:
        r0 = move-exception;
        r0 = r6.createUnexpectedDecodeException(r0);
        r6.exception = r0;
        goto L_0x005d;
    L_0x0056:
        r0 = move-exception;
        r0 = r6.createUnexpectedDecodeException(r0);
        r6.exception = r0;
    L_0x005d:
        r0 = r6.exception;
        if (r0 == 0) goto L_0x0069;
    L_0x0061:
        r0 = r6.lock;
        monitor-enter(r0);
        monitor-exit(r0);	 Catch:{ all -> 0x0066 }
        return r2;
    L_0x0066:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0066 }
        throw r1;
    L_0x0069:
        r4 = r6.lock;
        monitor-enter(r4);
        r0 = r6.flushed;	 Catch:{ all -> 0x0093 }
        if (r0 == 0) goto L_0x0074;
    L_0x0070:
        r3.release();	 Catch:{ all -> 0x0093 }
        goto L_0x008e;
    L_0x0074:
        r0 = r3.isDecodeOnly();	 Catch:{ all -> 0x0093 }
        if (r0 == 0) goto L_0x0083;
    L_0x007a:
        r0 = r6.skippedOutputBufferCount;	 Catch:{ all -> 0x0093 }
        r0 = r0 + r5;
        r6.skippedOutputBufferCount = r0;	 Catch:{ all -> 0x0093 }
        r3.release();	 Catch:{ all -> 0x0093 }
        goto L_0x008e;
    L_0x0083:
        r0 = r6.skippedOutputBufferCount;	 Catch:{ all -> 0x0093 }
        r3.skippedOutputBufferCount = r0;	 Catch:{ all -> 0x0093 }
        r6.skippedOutputBufferCount = r2;	 Catch:{ all -> 0x0093 }
        r0 = r6.queuedOutputBuffers;	 Catch:{ all -> 0x0093 }
        r0.addLast(r3);	 Catch:{ all -> 0x0093 }
    L_0x008e:
        r6.releaseInputBufferInternal(r1);	 Catch:{ all -> 0x0093 }
        monitor-exit(r4);	 Catch:{ all -> 0x0093 }
        return r5;
    L_0x0093:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0093 }
        throw r0;
    L_0x0096:
        r1 = move-exception;
        monitor-exit(r0);	 Catch:{ all -> 0x0096 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.decoder.SimpleDecoder.decode():boolean");
    }

    private boolean canDecodeBuffer() {
        return !this.queuedInputBuffers.isEmpty() && this.availableOutputBufferCount > 0;
    }

    private void releaseInputBufferInternal(I i) {
        i.clear();
        DecoderInputBuffer[] decoderInputBufferArr = this.availableInputBuffers;
        int i2 = this.availableInputBufferCount;
        this.availableInputBufferCount = i2 + 1;
        decoderInputBufferArr[i2] = i;
    }

    private void releaseOutputBufferInternal(O o) {
        o.clear();
        OutputBuffer[] outputBufferArr = this.availableOutputBuffers;
        int i = this.availableOutputBufferCount;
        this.availableOutputBufferCount = i + 1;
        outputBufferArr[i] = o;
    }
}
