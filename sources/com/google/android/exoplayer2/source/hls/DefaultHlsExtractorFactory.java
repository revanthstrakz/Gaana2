package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.ts.Ac3Extractor;
import com.google.android.exoplayer2.extractor.ts.AdtsExtractor;
import com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.io.EOFException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class DefaultHlsExtractorFactory implements HlsExtractorFactory {
    public static final String AAC_FILE_EXTENSION = ".aac";
    public static final String AC3_FILE_EXTENSION = ".ac3";
    public static final String CMF_FILE_EXTENSION_PREFIX = ".cmf";
    public static final String EC3_FILE_EXTENSION = ".ec3";
    public static final String M4_FILE_EXTENSION_PREFIX = ".m4";
    public static final String MP3_FILE_EXTENSION = ".mp3";
    public static final String MP4_FILE_EXTENSION = ".mp4";
    public static final String MP4_FILE_EXTENSION_PREFIX = ".mp4";
    public static final String VTT_FILE_EXTENSION = ".vtt";
    public static final String WEBVTT_FILE_EXTENSION = ".webvtt";
    private final int payloadReaderFactoryFlags;

    public DefaultHlsExtractorFactory() {
        this(0);
    }

    public DefaultHlsExtractorFactory(int i) {
        this.payloadReaderFactoryFlags = i;
    }

    public Pair<Extractor, Boolean> createExtractor(Extractor extractor, Uri uri, Format format, List<Format> list, DrmInitData drmInitData, TimestampAdjuster timestampAdjuster, Map<String, List<String>> map, ExtractorInput extractorInput) throws InterruptedException, IOException {
        if (extractor == null) {
            extractor = createExtractorByFileExtension(uri, format, list, drmInitData, timestampAdjuster);
            extractorInput.resetPeekPosition();
            if (sniffQuietly(extractor, extractorInput)) {
                return buildResult(extractor);
            }
            if (!(extractor instanceof WebvttExtractor)) {
                WebvttExtractor webvttExtractor = new WebvttExtractor(format.language, timestampAdjuster);
                if (sniffQuietly(webvttExtractor, extractorInput)) {
                    return buildResult(webvttExtractor);
                }
            }
            if (!(extractor instanceof AdtsExtractor)) {
                AdtsExtractor adtsExtractor = new AdtsExtractor();
                if (sniffQuietly(adtsExtractor, extractorInput)) {
                    return buildResult(adtsExtractor);
                }
            }
            if (!(extractor instanceof Ac3Extractor)) {
                Ac3Extractor ac3Extractor = new Ac3Extractor();
                if (sniffQuietly(ac3Extractor, extractorInput)) {
                    return buildResult(ac3Extractor);
                }
            }
            if (!(extractor instanceof Mp3Extractor)) {
                Mp3Extractor mp3Extractor = new Mp3Extractor(0, 0);
                if (sniffQuietly(mp3Extractor, extractorInput)) {
                    return buildResult(mp3Extractor);
                }
            }
            if (!(extractor instanceof FragmentedMp4Extractor)) {
                List list2;
                if (list != null) {
                    list2 = list;
                } else {
                    list2 = Collections.emptyList();
                }
                FragmentedMp4Extractor fragmentedMp4Extractor = new FragmentedMp4Extractor(0, timestampAdjuster, null, drmInitData, list2);
                if (sniffQuietly(fragmentedMp4Extractor, extractorInput)) {
                    return buildResult(fragmentedMp4Extractor);
                }
            }
            if (!(extractor instanceof TsExtractor)) {
                TsExtractor createTsExtractor = createTsExtractor(this.payloadReaderFactoryFlags, format, list, timestampAdjuster);
                if (sniffQuietly(createTsExtractor, extractorInput)) {
                    return buildResult(createTsExtractor);
                }
            }
            return buildResult(extractor);
        } else if ((extractor instanceof TsExtractor) || (extractor instanceof FragmentedMp4Extractor)) {
            return buildResult(extractor);
        } else {
            if (extractor instanceof WebvttExtractor) {
                return buildResult(new WebvttExtractor(format.language, timestampAdjuster));
            }
            if (extractor instanceof AdtsExtractor) {
                return buildResult(new AdtsExtractor());
            }
            if (extractor instanceof Ac3Extractor) {
                return buildResult(new Ac3Extractor());
            }
            if (extractor instanceof Mp3Extractor) {
                return buildResult(new Mp3Extractor());
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected previousExtractor type: ");
            stringBuilder.append(extractor.getClass().getSimpleName());
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private Extractor createExtractorByFileExtension(Uri uri, Format format, List<Format> list, DrmInitData drmInitData, TimestampAdjuster timestampAdjuster) {
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment == null) {
            lastPathSegment = "";
        }
        if (MimeTypes.TEXT_VTT.equals(format.sampleMimeType) || lastPathSegment.endsWith(WEBVTT_FILE_EXTENSION) || lastPathSegment.endsWith(VTT_FILE_EXTENSION)) {
            return new WebvttExtractor(format.language, timestampAdjuster);
        }
        if (lastPathSegment.endsWith(AAC_FILE_EXTENSION)) {
            return new AdtsExtractor();
        }
        if (lastPathSegment.endsWith(AC3_FILE_EXTENSION) || lastPathSegment.endsWith(EC3_FILE_EXTENSION)) {
            return new Ac3Extractor();
        }
        if (lastPathSegment.endsWith(".mp3")) {
            return new Mp3Extractor(0, 0);
        }
        if (!lastPathSegment.endsWith(".mp4") && !lastPathSegment.startsWith(M4_FILE_EXTENSION_PREFIX, lastPathSegment.length() - 4) && !lastPathSegment.startsWith(".mp4", lastPathSegment.length() - 5) && !lastPathSegment.startsWith(CMF_FILE_EXTENSION_PREFIX, lastPathSegment.length() - 5)) {
            return createTsExtractor(this.payloadReaderFactoryFlags, format, list, timestampAdjuster);
        }
        if (list == null) {
            list = Collections.emptyList();
        }
        return new FragmentedMp4Extractor(0, timestampAdjuster, null, drmInitData, list);
    }

    private static TsExtractor createTsExtractor(int i, Format format, List<Format> list, TimestampAdjuster timestampAdjuster) {
        List list2;
        i |= 16;
        if (list2 != null) {
            i |= 32;
        } else {
            list2 = Collections.singletonList(Format.createTextSampleFormat(null, MimeTypes.APPLICATION_CEA608, 0, null));
        }
        String str = format.codecs;
        if (!TextUtils.isEmpty(str)) {
            if (!MimeTypes.AUDIO_AAC.equals(MimeTypes.getAudioMediaMimeType(str))) {
                i |= 2;
            }
            if (!MimeTypes.VIDEO_H264.equals(MimeTypes.getVideoMediaMimeType(str))) {
                i |= 4;
            }
        }
        return new TsExtractor(2, timestampAdjuster, new DefaultTsPayloadReaderFactory(i, list2));
    }

    private static Pair<Extractor, Boolean> buildResult(Extractor extractor) {
        boolean z = (extractor instanceof AdtsExtractor) || (extractor instanceof Ac3Extractor) || (extractor instanceof Mp3Extractor);
        return new Pair(extractor, Boolean.valueOf(z));
    }

    private static boolean sniffQuietly(Extractor extractor, ExtractorInput extractorInput) throws InterruptedException, IOException {
        try {
            boolean sniff = extractor.sniff(extractorInput);
            return sniff;
        } catch (EOFException unused) {
            return false;
        } finally {
            extractorInput.resetPeekPosition();
        }
    }
}
