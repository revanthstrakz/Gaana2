package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.TrackIdGenerator;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class Id3Reader implements ElementaryStreamReader {
    private static final int ID3_HEADER_SIZE = 10;
    private static final String TAG = "Id3Reader";
    private final ParsableByteArray id3Header = new ParsableByteArray(10);
    private TrackOutput output;
    private int sampleBytesRead;
    private int sampleSize;
    private long sampleTimeUs;
    private boolean writingSample;

    public void seek() {
        this.writingSample = false;
    }

    public void createTracks(ExtractorOutput extractorOutput, TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 4);
        this.output.format(Format.createSampleFormat(trackIdGenerator.getFormatId(), MimeTypes.APPLICATION_ID3, null, -1, null));
    }

    public void packetStarted(long j, int i) {
        if ((i & 4) != 0) {
            this.writingSample = true;
            this.sampleTimeUs = j;
            this.sampleSize = 0;
            this.sampleBytesRead = 0;
        }
    }

    public void consume(ParsableByteArray parsableByteArray) {
        if (this.writingSample) {
            int bytesLeft = parsableByteArray.bytesLeft();
            if (this.sampleBytesRead < 10) {
                int min = Math.min(bytesLeft, 10 - this.sampleBytesRead);
                System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), this.id3Header.data, this.sampleBytesRead, min);
                if (this.sampleBytesRead + min == 10) {
                    this.id3Header.setPosition(0);
                    if (73 == this.id3Header.readUnsignedByte() && 68 == this.id3Header.readUnsignedByte() && 51 == this.id3Header.readUnsignedByte()) {
                        this.id3Header.skipBytes(3);
                        this.sampleSize = 10 + this.id3Header.readSynchSafeInt();
                    } else {
                        Log.w(TAG, "Discarding invalid ID3 tag");
                        this.writingSample = false;
                        return;
                    }
                }
            }
            bytesLeft = Math.min(bytesLeft, this.sampleSize - this.sampleBytesRead);
            this.output.sampleData(parsableByteArray, bytesLeft);
            this.sampleBytesRead += bytesLeft;
        }
    }

    public void packetFinished() {
        if (this.writingSample && this.sampleSize != 0 && this.sampleBytesRead == this.sampleSize) {
            this.output.sampleMetadata(this.sampleTimeUs, 1, this.sampleSize, 0, null);
            this.writingSample = false;
        }
    }
}
