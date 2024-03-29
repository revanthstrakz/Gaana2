package com.google.android.exoplayer2.metadata.scte35;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.metadata.MetadataDecoder;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.nio.ByteBuffer;

public final class SpliceInfoDecoder implements MetadataDecoder {
    private static final int TYPE_PRIVATE_COMMAND = 255;
    private static final int TYPE_SPLICE_INSERT = 5;
    private static final int TYPE_SPLICE_NULL = 0;
    private static final int TYPE_SPLICE_SCHEDULE = 4;
    private static final int TYPE_TIME_SIGNAL = 6;
    private final ParsableByteArray sectionData = new ParsableByteArray();
    private final ParsableBitArray sectionHeader = new ParsableBitArray();
    private TimestampAdjuster timestampAdjuster;

    public Metadata decode(MetadataInputBuffer metadataInputBuffer) {
        if (this.timestampAdjuster == null || metadataInputBuffer.subsampleOffsetUs != this.timestampAdjuster.getTimestampOffsetUs()) {
            this.timestampAdjuster = new TimestampAdjuster(metadataInputBuffer.timeUs);
            this.timestampAdjuster.adjustSampleTimestamp(metadataInputBuffer.timeUs - metadataInputBuffer.subsampleOffsetUs);
        }
        ByteBuffer byteBuffer = metadataInputBuffer.data;
        byte[] array = byteBuffer.array();
        int limit = byteBuffer.limit();
        this.sectionData.reset(array, limit);
        this.sectionHeader.reset(array, limit);
        this.sectionHeader.skipBits(39);
        long readBits = (((long) this.sectionHeader.readBits(1)) << 32) | ((long) this.sectionHeader.readBits(32));
        this.sectionHeader.skipBits(20);
        limit = this.sectionHeader.readBits(12);
        int readBits2 = this.sectionHeader.readBits(8);
        TimeSignalCommand timeSignalCommand = null;
        this.sectionData.skipBytes(14);
        if (readBits2 == 0) {
            timeSignalCommand = new SpliceNullCommand();
        } else if (readBits2 != 255) {
            switch (readBits2) {
                case 4:
                    timeSignalCommand = SpliceScheduleCommand.parseFromSection(this.sectionData);
                    break;
                case 5:
                    timeSignalCommand = SpliceInsertCommand.parseFromSection(this.sectionData, readBits, this.timestampAdjuster);
                    break;
                case 6:
                    timeSignalCommand = TimeSignalCommand.parseFromSection(this.sectionData, readBits, this.timestampAdjuster);
                    break;
            }
        } else {
            timeSignalCommand = PrivateCommand.parseFromSection(this.sectionData, limit, readBits);
        }
        if (timeSignalCommand == null) {
            return new Metadata(new Entry[0]);
        }
        return new Metadata(timeSignalCommand);
    }
}
