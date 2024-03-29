package com.google.android.exoplayer2.text.cea;

import android.support.annotation.NonNull;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

abstract class CeaDecoder implements SubtitleDecoder {
    private static final int NUM_INPUT_BUFFERS = 10;
    private static final int NUM_OUTPUT_BUFFERS = 2;
    private final ArrayDeque<CeaInputBuffer> availableInputBuffers = new ArrayDeque();
    private final ArrayDeque<SubtitleOutputBuffer> availableOutputBuffers;
    private CeaInputBuffer dequeuedInputBuffer;
    private long playbackPositionUs;
    private long queuedInputBufferCount;
    private final PriorityQueue<CeaInputBuffer> queuedInputBuffers;

    private static final class CeaInputBuffer extends SubtitleInputBuffer implements Comparable<CeaInputBuffer> {
        private long queuedInputBufferCount;

        private CeaInputBuffer() {
        }

        public int compareTo(@NonNull CeaInputBuffer ceaInputBuffer) {
            int i = -1;
            if (isEndOfStream() != ceaInputBuffer.isEndOfStream()) {
                if (isEndOfStream()) {
                    i = 1;
                }
                return i;
            }
            long j = this.timeUs - ceaInputBuffer.timeUs;
            if (j == 0) {
                long j2 = this.queuedInputBufferCount - ceaInputBuffer.queuedInputBufferCount;
                if (j2 == 0) {
                    return 0;
                }
                j = j2;
            }
            if (j > 0) {
                i = 1;
            }
            return i;
        }
    }

    private final class CeaOutputBuffer extends SubtitleOutputBuffer {
        private CeaOutputBuffer() {
        }

        public final void release() {
            CeaDecoder.this.releaseOutputBuffer(this);
        }
    }

    public abstract Subtitle createSubtitle();

    public abstract void decode(SubtitleInputBuffer subtitleInputBuffer);

    public abstract String getName();

    public abstract boolean isNewSubtitleDataAvailable();

    public void release() {
    }

    public CeaDecoder() {
        int i = 0;
        for (int i2 = 0; i2 < 10; i2++) {
            this.availableInputBuffers.add(new CeaInputBuffer());
        }
        this.availableOutputBuffers = new ArrayDeque();
        while (i < 2) {
            this.availableOutputBuffers.add(new CeaOutputBuffer());
            i++;
        }
        this.queuedInputBuffers = new PriorityQueue();
    }

    public void setPositionUs(long j) {
        this.playbackPositionUs = j;
    }

    public SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        Assertions.checkState(this.dequeuedInputBuffer == null);
        if (this.availableInputBuffers.isEmpty()) {
            return null;
        }
        this.dequeuedInputBuffer = (CeaInputBuffer) this.availableInputBuffers.pollFirst();
        return this.dequeuedInputBuffer;
    }

    public void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        Assertions.checkArgument(subtitleInputBuffer == this.dequeuedInputBuffer);
        if (subtitleInputBuffer.isDecodeOnly()) {
            releaseInputBuffer(this.dequeuedInputBuffer);
        } else {
            CeaInputBuffer ceaInputBuffer = this.dequeuedInputBuffer;
            long j = this.queuedInputBufferCount;
            this.queuedInputBufferCount = j + 1;
            ceaInputBuffer.queuedInputBufferCount = j;
            this.queuedInputBuffers.add(this.dequeuedInputBuffer);
        }
        this.dequeuedInputBuffer = null;
    }

    public SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        if (this.availableOutputBuffers.isEmpty()) {
            return null;
        }
        while (!this.queuedInputBuffers.isEmpty() && ((CeaInputBuffer) this.queuedInputBuffers.peek()).timeUs <= this.playbackPositionUs) {
            CeaInputBuffer ceaInputBuffer = (CeaInputBuffer) this.queuedInputBuffers.poll();
            SubtitleOutputBuffer subtitleOutputBuffer;
            if (ceaInputBuffer.isEndOfStream()) {
                subtitleOutputBuffer = (SubtitleOutputBuffer) this.availableOutputBuffers.pollFirst();
                subtitleOutputBuffer.addFlag(4);
                releaseInputBuffer(ceaInputBuffer);
                return subtitleOutputBuffer;
            }
            decode(ceaInputBuffer);
            if (isNewSubtitleDataAvailable()) {
                Subtitle createSubtitle = createSubtitle();
                if (!ceaInputBuffer.isDecodeOnly()) {
                    subtitleOutputBuffer = (SubtitleOutputBuffer) this.availableOutputBuffers.pollFirst();
                    subtitleOutputBuffer.setContent(ceaInputBuffer.timeUs, createSubtitle, Long.MAX_VALUE);
                    releaseInputBuffer(ceaInputBuffer);
                    return subtitleOutputBuffer;
                }
            }
            releaseInputBuffer(ceaInputBuffer);
        }
        return null;
    }

    private void releaseInputBuffer(CeaInputBuffer ceaInputBuffer) {
        ceaInputBuffer.clear();
        this.availableInputBuffers.add(ceaInputBuffer);
    }

    /* Access modifiers changed, original: protected */
    public void releaseOutputBuffer(SubtitleOutputBuffer subtitleOutputBuffer) {
        subtitleOutputBuffer.clear();
        this.availableOutputBuffers.add(subtitleOutputBuffer);
    }

    public void flush() {
        this.queuedInputBufferCount = 0;
        this.playbackPositionUs = 0;
        while (!this.queuedInputBuffers.isEmpty()) {
            releaseInputBuffer((CeaInputBuffer) this.queuedInputBuffers.poll());
        }
        if (this.dequeuedInputBuffer != null) {
            releaseInputBuffer(this.dequeuedInputBuffer);
            this.dequeuedInputBuffer = null;
        }
    }
}
