package com.google.android.exoplayer2.extractor.mkv;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;

final class Sniffer {
    private static final int ID_EBML = 440786851;
    private static final int SEARCH_LENGTH = 1024;
    private int peekLength;
    private final ParsableByteArray scratch = new ParsableByteArray(8);

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        ExtractorInput extractorInput2 = extractorInput;
        long length = extractorInput.getLength();
        int i = (length > -1 ? 1 : (length == -1 ? 0 : -1));
        long j = PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        if (i != 0 && length <= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            j = length;
        }
        i = (int) j;
        extractorInput2.peekFully(this.scratch.data, 0, 4);
        long readUnsignedInt = this.scratch.readUnsignedInt();
        this.peekLength = 4;
        while (true) {
            boolean z = true;
            if (readUnsignedInt != 440786851) {
                int i2 = this.peekLength + 1;
                this.peekLength = i2;
                if (i2 == i) {
                    return false;
                }
                extractorInput2.peekFully(this.scratch.data, 0, 1);
                readUnsignedInt = ((readUnsignedInt << 8) & -256) | ((long) (this.scratch.data[0] & 255));
            } else {
                readUnsignedInt = readUint(extractorInput);
                long j2 = (long) this.peekLength;
                if (readUnsignedInt == Long.MIN_VALUE || (length != -1 && j2 + readUnsignedInt >= length)) {
                    return false;
                }
                while (true) {
                    long j3 = j2 + readUnsignedInt;
                    if (((long) this.peekLength) >= j3) {
                        if (((long) this.peekLength) != j3) {
                            z = false;
                        }
                        return z;
                    } else if (readUint(extractorInput) == Long.MIN_VALUE) {
                        return false;
                    } else {
                        length = readUint(extractorInput);
                        if (length < 0 || length > 2147483647L) {
                            return false;
                        }
                        if (length != 0) {
                            int i3 = (int) length;
                            extractorInput2.advancePeekPosition(i3);
                            this.peekLength += i3;
                        }
                    }
                }
                return false;
            }
        }
    }

    private long readUint(ExtractorInput extractorInput) throws IOException, InterruptedException {
        int i = 0;
        extractorInput.peekFully(this.scratch.data, 0, 1);
        int i2 = this.scratch.data[0] & 255;
        if (i2 == 0) {
            return Long.MIN_VALUE;
        }
        int i3 = 128;
        int i4 = 0;
        while ((i2 & i3) == 0) {
            i3 >>= 1;
            i4++;
        }
        i2 &= i3 ^ -1;
        extractorInput.peekFully(this.scratch.data, 1, i4);
        while (i < i4) {
            i++;
            i2 = (this.scratch.data[i] & 255) + (i2 << 8);
        }
        this.peekLength += i4 + 1;
        return (long) i2;
    }
}
