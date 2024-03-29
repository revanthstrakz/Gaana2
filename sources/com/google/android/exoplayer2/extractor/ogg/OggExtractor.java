package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;

public class OggExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = OggExtractor$$Lambda$0.$instance;
    private static final int MAX_VERIFICATION_BYTES = 8;
    private ExtractorOutput output;
    private StreamReader streamReader;
    private boolean streamReaderInitialized;

    public void release() {
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        try {
            return sniffInternal(extractorInput);
        } catch (ParserException unused) {
            return false;
        }
    }

    public void init(ExtractorOutput extractorOutput) {
        this.output = extractorOutput;
    }

    public void seek(long j, long j2) {
        if (this.streamReader != null) {
            this.streamReader.seek(j, j2);
        }
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        if (this.streamReader == null) {
            if (sniffInternal(extractorInput)) {
                extractorInput.resetPeekPosition();
            } else {
                throw new ParserException("Failed to determine bitstream type");
            }
        }
        if (!this.streamReaderInitialized) {
            TrackOutput track = this.output.track(0, 1);
            this.output.endTracks();
            this.streamReader.init(this.output, track);
            this.streamReaderInitialized = true;
        }
        return this.streamReader.read(extractorInput, positionHolder);
    }

    private boolean sniffInternal(ExtractorInput extractorInput) throws IOException, InterruptedException {
        OggPageHeader oggPageHeader = new OggPageHeader();
        if (!oggPageHeader.populate(extractorInput, true) || (oggPageHeader.type & 2) != 2) {
            return false;
        }
        int min = Math.min(oggPageHeader.bodySize, 8);
        ParsableByteArray parsableByteArray = new ParsableByteArray(min);
        extractorInput.peekFully(parsableByteArray.data, 0, min);
        if (FlacReader.verifyBitstreamType(resetPosition(parsableByteArray))) {
            this.streamReader = new FlacReader();
        } else if (VorbisReader.verifyBitstreamType(resetPosition(parsableByteArray))) {
            this.streamReader = new VorbisReader();
        } else if (!OpusReader.verifyBitstreamType(resetPosition(parsableByteArray))) {
            return false;
        } else {
            this.streamReader = new OpusReader();
        }
        return true;
    }

    private static ParsableByteArray resetPosition(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(0);
        return parsableByteArray;
    }
}
