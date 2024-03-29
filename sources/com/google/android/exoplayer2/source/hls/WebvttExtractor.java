package com.google.android.exoplayer2.source.hls;

import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap.Unseekable;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.text.webvtt.WebvttParserUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebvttExtractor implements Extractor {
    private static final int HEADER_MAX_LENGTH = 9;
    private static final int HEADER_MIN_LENGTH = 6;
    private static final Pattern LOCAL_TIMESTAMP = Pattern.compile("LOCAL:([^,]+)");
    private static final Pattern MEDIA_TIMESTAMP = Pattern.compile("MPEGTS:(\\d+)");
    private final String language;
    private ExtractorOutput output;
    private byte[] sampleData = new byte[1024];
    private final ParsableByteArray sampleDataWrapper = new ParsableByteArray();
    private int sampleSize;
    private final TimestampAdjuster timestampAdjuster;

    public void release() {
    }

    public WebvttExtractor(String str, TimestampAdjuster timestampAdjuster) {
        this.language = str;
        this.timestampAdjuster = timestampAdjuster;
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        extractorInput.peekFully(this.sampleData, 0, 6, false);
        this.sampleDataWrapper.reset(this.sampleData, 6);
        if (WebvttParserUtil.isWebvttHeaderLine(this.sampleDataWrapper)) {
            return true;
        }
        extractorInput.peekFully(this.sampleData, 6, 3, false);
        this.sampleDataWrapper.reset(this.sampleData, 9);
        return WebvttParserUtil.isWebvttHeaderLine(this.sampleDataWrapper);
    }

    public void init(ExtractorOutput extractorOutput) {
        this.output = extractorOutput;
        extractorOutput.seekMap(new Unseekable(C.TIME_UNSET));
    }

    public void seek(long j, long j2) {
        throw new IllegalStateException();
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        int length = (int) extractorInput.getLength();
        if (this.sampleSize == this.sampleData.length) {
            this.sampleData = Arrays.copyOf(this.sampleData, ((length != -1 ? length : this.sampleData.length) * 3) / 2);
        }
        int read = extractorInput.read(this.sampleData, this.sampleSize, this.sampleData.length - this.sampleSize);
        if (read != -1) {
            this.sampleSize += read;
            if (length == -1 || this.sampleSize != length) {
                return 0;
            }
        }
        processSample();
        return -1;
    }

    private void processSample() throws ParserException {
        ParsableByteArray parsableByteArray = new ParsableByteArray(this.sampleData);
        WebvttParserUtil.validateWebvttHeaderLine(parsableByteArray);
        long j = 0;
        long j2 = j;
        while (true) {
            String readLine = parsableByteArray.readLine();
            if (TextUtils.isEmpty(readLine)) {
                Matcher findNextCueHeader = WebvttParserUtil.findNextCueHeader(parsableByteArray);
                if (findNextCueHeader == null) {
                    buildTrackOutput(0);
                    return;
                }
                long parseTimestampUs = WebvttParserUtil.parseTimestampUs(findNextCueHeader.group(1));
                long adjustTsTimestamp = this.timestampAdjuster.adjustTsTimestamp(TimestampAdjuster.usToPts((parseTimestampUs + j) - j2));
                TrackOutput buildTrackOutput = buildTrackOutput(adjustTsTimestamp - parseTimestampUs);
                this.sampleDataWrapper.reset(this.sampleData, this.sampleSize);
                buildTrackOutput.sampleData(this.sampleDataWrapper, this.sampleSize);
                buildTrackOutput.sampleMetadata(adjustTsTimestamp, 1, this.sampleSize, 0, null);
                return;
            } else if (readLine.startsWith("X-TIMESTAMP-MAP")) {
                Matcher matcher = LOCAL_TIMESTAMP.matcher(readLine);
                StringBuilder stringBuilder;
                if (matcher.find()) {
                    Matcher matcher2 = MEDIA_TIMESTAMP.matcher(readLine);
                    if (matcher2.find()) {
                        j2 = WebvttParserUtil.parseTimestampUs(matcher.group(1));
                        j = TimestampAdjuster.ptsToUs(Long.parseLong(matcher2.group(1)));
                    } else {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("X-TIMESTAMP-MAP doesn't contain media timestamp: ");
                        stringBuilder.append(readLine);
                        throw new ParserException(stringBuilder.toString());
                    }
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append("X-TIMESTAMP-MAP doesn't contain local timestamp: ");
                stringBuilder.append(readLine);
                throw new ParserException(stringBuilder.toString());
            }
        }
    }

    private TrackOutput buildTrackOutput(long j) {
        TrackOutput track = this.output.track(0, 3);
        track.format(Format.createTextSampleFormat(null, MimeTypes.TEXT_VTT, null, -1, 0, this.language, null, j));
        this.output.endTracks();
        return track;
    }
}
