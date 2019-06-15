package com.google.android.exoplayer2.extractor.mkv;

import android.support.annotation.Nullable;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekMap.Unseekable;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.TrackOutput.CryptoData;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class MatroskaExtractor implements Extractor {
    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_ASS = "S_TEXT/ASS";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP2 = "A_MPEG/L2";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_THEORA = "V_THEORA";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    public static final ExtractorsFactory FACTORY = MatroskaExtractor$$Lambda$0.$instance;
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    private static final int FOURCC_COMPRESSION_DIVX = 1482049860;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_COLOUR = 21936;
    private static final int ID_COLOUR_PRIMARIES = 21947;
    private static final int ID_COLOUR_RANGE = 21945;
    private static final int ID_COLOUR_TRANSFER = 21946;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_FLAG_DEFAULT = 136;
    private static final int ID_FLAG_FORCED = 21930;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_LUMNINANCE_MAX = 21977;
    private static final int ID_LUMNINANCE_MIN = 21978;
    private static final int ID_MASTERING_METADATA = 21968;
    private static final int ID_MAX_CLL = 21948;
    private static final int ID_MAX_FALL = 21949;
    private static final int ID_NAME = 21358;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    private static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    private static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    private static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    private static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    private static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    private static final int ID_PROJECTION = 30320;
    private static final int ID_PROJECTION_PRIVATE = 30322;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_STEREO_MODE = 21432;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    private static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    private static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = new byte[]{(byte) 68, (byte) 105, (byte) 97, (byte) 108, (byte) 111, (byte) 103, (byte) 117, (byte) 101, (byte) 58, (byte) 32, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44};
    private static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    private static final byte[] SSA_TIMECODE_EMPTY = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
    private static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    private static final long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    private static final byte[] SUBRIP_PREFIX = new byte[]{(byte) 49, (byte) 10, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44, (byte) 48, (byte) 48, (byte) 48, (byte) 32, (byte) 45, (byte) 45, (byte) 62, (byte) 32, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44, (byte) 48, (byte) 48, (byte) 48, (byte) 10};
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final byte[] SUBRIP_TIMECODE_EMPTY = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
    private static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    private static final long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final String TAG = "MatroskaExtractor";
    private static final int TRACK_TYPE_AUDIO = 2;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    private static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private long blockDurationUs;
    private int blockFlags;
    private int blockLacingSampleCount;
    private int blockLacingSampleIndex;
    private int[] blockLacingSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private boolean sampleRead;
    private boolean sampleSeenReferenceBlock;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final ParsableByteArray subtitleSample;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    private static final class Track {
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        public int audioBitDepth;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange;
        public int colorSpace;
        public int colorTransfer;
        public CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public DrmInitData drmInitData;
        public boolean flagDefault;
        public boolean flagForced;
        public boolean hasColorInfo;
        public boolean hasContentEncryption;
        public int height;
        private String language;
        public int maxContentLuminance;
        public int maxFrameAverageLuminance;
        public float maxMasteringLuminance;
        public float minMasteringLuminance;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX;
        public float primaryBChromaticityY;
        public float primaryGChromaticityX;
        public float primaryGChromaticityY;
        public float primaryRChromaticityX;
        public float primaryRChromaticityY;
        public byte[] projectionData;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int stereoMode;
        @Nullable
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public float whitePointChromaticityX;
        public float whitePointChromaticityY;
        public int width;

        private Track() {
            this.width = -1;
            this.height = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.projectionData = null;
            this.stereoMode = -1;
            this.hasColorInfo = false;
            this.colorSpace = -1;
            this.colorTransfer = -1;
            this.colorRange = -1;
            this.maxContentLuminance = 1000;
            this.maxFrameAverageLuminance = 200;
            this.primaryRChromaticityX = -1.0f;
            this.primaryRChromaticityY = -1.0f;
            this.primaryGChromaticityX = -1.0f;
            this.primaryGChromaticityY = -1.0f;
            this.primaryBChromaticityX = -1.0f;
            this.primaryBChromaticityY = -1.0f;
            this.whitePointChromaticityX = -1.0f;
            this.whitePointChromaticityY = -1.0f;
            this.maxMasteringLuminance = -1.0f;
            this.minMasteringLuminance = -1.0f;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0;
            this.seekPreRollNs = 0;
            this.flagDefault = true;
            this.language = "eng";
        }

        /* JADX WARNING: Missing block: B:102:0x01d4, code skipped:
            r12 = r1;
            r18 = r2;
            r15 = -1;
     */
        /* JADX WARNING: Missing block: B:117:0x0258, code skipped:
            r12 = r1;
            r15 = 4096;
            r18 = -1;
     */
        /* JADX WARNING: Missing block: B:119:0x0266, code skipped:
            r12 = r1;
     */
        /* JADX WARNING: Missing block: B:122:0x02b4, code skipped:
            r12 = r1;
            r15 = r2;
            r2 = r3;
            r18 = -1;
     */
        /* JADX WARNING: Missing block: B:127:0x02fe, code skipped:
            r12 = r1;
            r2 = r3;
     */
        /* JADX WARNING: Missing block: B:128:0x0300, code skipped:
            r15 = -1;
            r18 = r15;
     */
        /* JADX WARNING: Missing block: B:136:0x031d, code skipped:
            r12 = r1;
            r15 = -1;
            r18 = r15;
     */
        /* JADX WARNING: Missing block: B:137:0x0321, code skipped:
            r2 = null;
     */
        /* JADX WARNING: Missing block: B:138:0x0322, code skipped:
            r1 = r0.flagDefault | 0;
     */
        /* JADX WARNING: Missing block: B:139:0x0327, code skipped:
            if (r0.flagForced == false) goto L_0x032b;
     */
        /* JADX WARNING: Missing block: B:140:0x0329, code skipped:
            r3 = 2;
     */
        /* JADX WARNING: Missing block: B:141:0x032b, code skipped:
            r3 = 0;
     */
        /* JADX WARNING: Missing block: B:142:0x032c, code skipped:
            r1 = r1 | r3;
     */
        /* JADX WARNING: Missing block: B:143:0x0331, code skipped:
            if (com.google.android.exoplayer2.util.MimeTypes.isAudio(r12) == false) goto L_0x0354;
     */
        /* JADX WARNING: Missing block: B:144:0x0333, code skipped:
            r1 = com.google.android.exoplayer2.Format.createAudioSampleFormat(java.lang.Integer.toString(r28), r12, null, -1, r15, r0.channelCount, r0.sampleRate, r18, r2, r0.drmInitData, r1, r0.language);
            r8 = 1;
     */
        /* JADX WARNING: Missing block: B:146:0x0358, code skipped:
            if (com.google.android.exoplayer2.util.MimeTypes.isVideo(r12) == false) goto L_0x03fd;
     */
        /* JADX WARNING: Missing block: B:148:0x035c, code skipped:
            if (r0.displayUnit != 0) goto L_0x0374;
     */
        /* JADX WARNING: Missing block: B:150:0x0360, code skipped:
            if (r0.displayWidth != -1) goto L_0x0365;
     */
        /* JADX WARNING: Missing block: B:151:0x0362, code skipped:
            r1 = r0.width;
     */
        /* JADX WARNING: Missing block: B:152:0x0365, code skipped:
            r1 = r0.displayWidth;
     */
        /* JADX WARNING: Missing block: B:153:0x0367, code skipped:
            r0.displayWidth = r1;
     */
        /* JADX WARNING: Missing block: B:154:0x036b, code skipped:
            if (r0.displayHeight != -1) goto L_0x0370;
     */
        /* JADX WARNING: Missing block: B:155:0x036d, code skipped:
            r1 = r0.height;
     */
        /* JADX WARNING: Missing block: B:156:0x0370, code skipped:
            r1 = r0.displayHeight;
     */
        /* JADX WARNING: Missing block: B:157:0x0372, code skipped:
            r0.displayHeight = r1;
     */
        /* JADX WARNING: Missing block: B:158:0x0374, code skipped:
            r1 = -1.0f;
     */
        /* JADX WARNING: Missing block: B:159:0x0378, code skipped:
            if (r0.displayWidth == -1) goto L_0x038b;
     */
        /* JADX WARNING: Missing block: B:161:0x037c, code skipped:
            if (r0.displayHeight == -1) goto L_0x038b;
     */
        /* JADX WARNING: Missing block: B:162:0x037e, code skipped:
            r1 = ((float) (r0.height * r0.displayWidth)) / ((float) (r0.width * r0.displayHeight));
     */
        /* JADX WARNING: Missing block: B:163:0x038b, code skipped:
            r21 = r1;
     */
        /* JADX WARNING: Missing block: B:164:0x038f, code skipped:
            if (r0.hasColorInfo == false) goto L_0x03a0;
     */
        /* JADX WARNING: Missing block: B:165:0x0391, code skipped:
            r10 = new com.google.android.exoplayer2.video.ColorInfo(r0.colorSpace, r0.colorRange, r0.colorTransfer, getHdrStaticInfo());
     */
        /* JADX WARNING: Missing block: B:166:0x03a0, code skipped:
            r24 = r10;
     */
        /* JADX WARNING: Missing block: B:167:0x03aa, code skipped:
            if ("htc_video_rotA-000".equals(r0.name) == false) goto L_0x03af;
     */
        /* JADX WARNING: Missing block: B:168:0x03ac, code skipped:
            r20 = r6;
     */
        /* JADX WARNING: Missing block: B:170:0x03b7, code skipped:
            if ("htc_video_rotA-090".equals(r0.name) == false) goto L_0x03bc;
     */
        /* JADX WARNING: Missing block: B:171:0x03b9, code skipped:
            r6 = 90;
     */
        /* JADX WARNING: Missing block: B:173:0x03c4, code skipped:
            if ("htc_video_rotA-180".equals(r0.name) == false) goto L_0x03c9;
     */
        /* JADX WARNING: Missing block: B:174:0x03c6, code skipped:
            r6 = 180;
     */
        /* JADX WARNING: Missing block: B:176:0x03d1, code skipped:
            if ("htc_video_rotA-270".equals(r0.name) == false) goto L_0x03d6;
     */
        /* JADX WARNING: Missing block: B:177:0x03d3, code skipped:
            r6 = 270;
     */
        /* JADX WARNING: Missing block: B:178:0x03d6, code skipped:
            r20 = -1;
     */
        /* JADX WARNING: Missing block: B:179:0x03d8, code skipped:
            r1 = com.google.android.exoplayer2.Format.createVideoSampleFormat(java.lang.Integer.toString(r28), r12, null, -1, r15, r0.width, r0.height, -1.0f, r2, r20, r21, r0.projectionData, r0.stereoMode, r24, r0.drmInitData);
            r8 = 2;
     */
        /* JADX WARNING: Missing block: B:181:0x0403, code skipped:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_SUBRIP.equals(r12) == false) goto L_0x0413;
     */
        /* JADX WARNING: Missing block: B:182:0x0405, code skipped:
            r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(java.lang.Integer.toString(r28), r12, r1, r0.language, r0.drmInitData);
     */
        /* JADX WARNING: Missing block: B:184:0x0419, code skipped:
            if (com.google.android.exoplayer2.util.MimeTypes.TEXT_SSA.equals(r12) == false) goto L_0x0449;
     */
        /* JADX WARNING: Missing block: B:185:0x041b, code skipped:
            r2 = new java.util.ArrayList(2);
            r2.add(com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.access$300());
            r2.add(r0.codecPrivate);
            r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(java.lang.Integer.toString(r28), r12, null, -1, r1, r0.language, -1, r0.drmInitData, Long.MAX_VALUE, r2);
     */
        /* JADX WARNING: Missing block: B:187:0x044f, code skipped:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_VOBSUB.equals(r12) != false) goto L_0x046a;
     */
        /* JADX WARNING: Missing block: B:189:0x0457, code skipped:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_PGS.equals(r12) != false) goto L_0x046a;
     */
        /* JADX WARNING: Missing block: B:191:0x045f, code skipped:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_DVBSUBS.equals(r12) == false) goto L_0x0462;
     */
        /* JADX WARNING: Missing block: B:193:0x0469, code skipped:
            throw new com.google.android.exoplayer2.ParserException("Unexpected MIME type.");
     */
        /* JADX WARNING: Missing block: B:194:0x046a, code skipped:
            r1 = com.google.android.exoplayer2.Format.createImageSampleFormat(java.lang.Integer.toString(r28), r12, null, -1, r1, r2, r0.language, r0.drmInitData);
     */
        /* JADX WARNING: Missing block: B:195:0x047f, code skipped:
            r0.output = r27.track(r0.number, r8);
            r0.output.format(r1);
     */
        /* JADX WARNING: Missing block: B:196:0x048e, code skipped:
            return;
     */
        public void initializeOutput(com.google.android.exoplayer2.extractor.ExtractorOutput r27, int r28) throws com.google.android.exoplayer2.ParserException {
            /*
            r26 = this;
            r0 = r26;
            r1 = r0.codecId;
            r2 = r1.hashCode();
            r3 = 4;
            r4 = 8;
            r5 = 1;
            r6 = 0;
            r7 = 2;
            r8 = 3;
            r9 = -1;
            switch(r2) {
                case -2095576542: goto L_0x0155;
                case -2095575984: goto L_0x014b;
                case -1985379776: goto L_0x0140;
                case -1784763192: goto L_0x0135;
                case -1730367663: goto L_0x012a;
                case -1482641358: goto L_0x011f;
                case -1482641357: goto L_0x0114;
                case -1373388978: goto L_0x010a;
                case -933872740: goto L_0x00ff;
                case -538363189: goto L_0x00f4;
                case -538363109: goto L_0x00e9;
                case -425012669: goto L_0x00dd;
                case -356037306: goto L_0x00d1;
                case 62923557: goto L_0x00c5;
                case 62923603: goto L_0x00b9;
                case 62927045: goto L_0x00ad;
                case 82338133: goto L_0x00a2;
                case 82338134: goto L_0x0097;
                case 99146302: goto L_0x008b;
                case 444813526: goto L_0x007f;
                case 542569478: goto L_0x0073;
                case 725957860: goto L_0x0067;
                case 738597099: goto L_0x005b;
                case 855502857: goto L_0x0050;
                case 1422270023: goto L_0x0044;
                case 1809237540: goto L_0x0039;
                case 1950749482: goto L_0x002d;
                case 1950789798: goto L_0x0021;
                case 1951062397: goto L_0x0015;
                default: goto L_0x0013;
            };
        L_0x0013:
            goto L_0x015f;
        L_0x0015:
            r2 = "A_OPUS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x001d:
            r1 = 11;
            goto L_0x0160;
        L_0x0021:
            r2 = "A_FLAC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x0029:
            r1 = 21;
            goto L_0x0160;
        L_0x002d:
            r2 = "A_EAC3";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x0035:
            r1 = 16;
            goto L_0x0160;
        L_0x0039:
            r2 = "V_MPEG2";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x0041:
            r1 = r7;
            goto L_0x0160;
        L_0x0044:
            r2 = "S_TEXT/UTF8";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x004c:
            r1 = 24;
            goto L_0x0160;
        L_0x0050:
            r2 = "V_MPEGH/ISO/HEVC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x0058:
            r1 = 7;
            goto L_0x0160;
        L_0x005b:
            r2 = "S_TEXT/ASS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x0063:
            r1 = 25;
            goto L_0x0160;
        L_0x0067:
            r2 = "A_PCM/INT/LIT";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x006f:
            r1 = 23;
            goto L_0x0160;
        L_0x0073:
            r2 = "A_DTS/EXPRESS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x007b:
            r1 = 19;
            goto L_0x0160;
        L_0x007f:
            r2 = "V_THEORA";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x0087:
            r1 = 9;
            goto L_0x0160;
        L_0x008b:
            r2 = "S_HDMV/PGS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x0093:
            r1 = 27;
            goto L_0x0160;
        L_0x0097:
            r2 = "V_VP9";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x009f:
            r1 = r5;
            goto L_0x0160;
        L_0x00a2:
            r2 = "V_VP8";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x00aa:
            r1 = r6;
            goto L_0x0160;
        L_0x00ad:
            r2 = "A_DTS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x00b5:
            r1 = 18;
            goto L_0x0160;
        L_0x00b9:
            r2 = "A_AC3";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x00c1:
            r1 = 15;
            goto L_0x0160;
        L_0x00c5:
            r2 = "A_AAC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x00cd:
            r1 = 12;
            goto L_0x0160;
        L_0x00d1:
            r2 = "A_DTS/LOSSLESS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x00d9:
            r1 = 20;
            goto L_0x0160;
        L_0x00dd:
            r2 = "S_VOBSUB";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x00e5:
            r1 = 26;
            goto L_0x0160;
        L_0x00e9:
            r2 = "V_MPEG4/ISO/AVC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x00f1:
            r1 = 6;
            goto L_0x0160;
        L_0x00f4:
            r2 = "V_MPEG4/ISO/ASP";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x00fc:
            r1 = r3;
            goto L_0x0160;
        L_0x00ff:
            r2 = "S_DVBSUB";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x0107:
            r1 = 28;
            goto L_0x0160;
        L_0x010a:
            r2 = "V_MS/VFW/FOURCC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x0112:
            r1 = r4;
            goto L_0x0160;
        L_0x0114:
            r2 = "A_MPEG/L3";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x011c:
            r1 = 14;
            goto L_0x0160;
        L_0x011f:
            r2 = "A_MPEG/L2";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x0127:
            r1 = 13;
            goto L_0x0160;
        L_0x012a:
            r2 = "A_VORBIS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x0132:
            r1 = 10;
            goto L_0x0160;
        L_0x0135:
            r2 = "A_TRUEHD";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x013d:
            r1 = 17;
            goto L_0x0160;
        L_0x0140:
            r2 = "A_MS/ACM";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x0148:
            r1 = 22;
            goto L_0x0160;
        L_0x014b:
            r2 = "V_MPEG4/ISO/SP";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x0153:
            r1 = r8;
            goto L_0x0160;
        L_0x0155:
            r2 = "V_MPEG4/ISO/AP";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x015f;
        L_0x015d:
            r1 = 5;
            goto L_0x0160;
        L_0x015f:
            r1 = r9;
        L_0x0160:
            r2 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
            r10 = 0;
            switch(r1) {
                case 0: goto L_0x031b;
                case 1: goto L_0x0318;
                case 2: goto L_0x0315;
                case 3: goto L_0x0304;
                case 4: goto L_0x0304;
                case 5: goto L_0x0304;
                case 6: goto L_0x02eb;
                case 7: goto L_0x02d7;
                case 8: goto L_0x02be;
                case 9: goto L_0x02bb;
                case 10: goto L_0x02aa;
                case 11: goto L_0x0269;
                case 12: goto L_0x025e;
                case 13: goto L_0x0256;
                case 14: goto L_0x0253;
                case 15: goto L_0x024f;
                case 16: goto L_0x024b;
                case 17: goto L_0x0240;
                case 18: goto L_0x023c;
                case 19: goto L_0x023c;
                case 20: goto L_0x0238;
                case 21: goto L_0x022f;
                case 22: goto L_0x01da;
                case 23: goto L_0x01a6;
                case 24: goto L_0x01a2;
                case 25: goto L_0x019e;
                case 26: goto L_0x0194;
                case 27: goto L_0x0190;
                case 28: goto L_0x016e;
                default: goto L_0x0166;
            };
        L_0x0166:
            r1 = new com.google.android.exoplayer2.ParserException;
            r2 = "Unrecognized codec identifier.";
            r1.<init>(r2);
            throw r1;
        L_0x016e:
            r1 = "application/dvbsubs";
            r2 = new byte[r3];
            r3 = r0.codecPrivate;
            r3 = r3[r6];
            r2[r6] = r3;
            r3 = r0.codecPrivate;
            r3 = r3[r5];
            r2[r5] = r3;
            r3 = r0.codecPrivate;
            r3 = r3[r7];
            r2[r7] = r3;
            r3 = r0.codecPrivate;
            r3 = r3[r8];
            r2[r8] = r3;
            r2 = java.util.Collections.singletonList(r2);
            goto L_0x0266;
        L_0x0190:
            r1 = "application/pgs";
            goto L_0x031d;
        L_0x0194:
            r1 = "application/vobsub";
            r2 = r0.codecPrivate;
            r2 = java.util.Collections.singletonList(r2);
            goto L_0x0266;
        L_0x019e:
            r1 = "text/x-ssa";
            goto L_0x031d;
        L_0x01a2:
            r1 = "application/x-subrip";
            goto L_0x031d;
        L_0x01a6:
            r1 = "audio/raw";
            r2 = r0.audioBitDepth;
            r2 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r2);
            if (r2 != 0) goto L_0x01d4;
        L_0x01b0:
            r1 = "audio/x-unknown";
            r2 = "MatroskaExtractor";
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "Unsupported PCM bit depth: ";
            r3.append(r4);
            r4 = r0.audioBitDepth;
            r3.append(r4);
            r4 = ". Setting mimeType to ";
            r3.append(r4);
            r3.append(r1);
            r3 = r3.toString();
            com.google.android.exoplayer2.util.Log.w(r2, r3);
            goto L_0x031d;
        L_0x01d4:
            r12 = r1;
            r18 = r2;
            r15 = r9;
            goto L_0x0321;
        L_0x01da:
            r1 = "audio/raw";
            r2 = new com.google.android.exoplayer2.util.ParsableByteArray;
            r3 = r0.codecPrivate;
            r2.<init>(r3);
            r2 = parseMsAcmCodecPrivate(r2);
            if (r2 == 0) goto L_0x0215;
        L_0x01e9:
            r2 = r0.audioBitDepth;
            r2 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r2);
            if (r2 != 0) goto L_0x01d4;
        L_0x01f1:
            r1 = "audio/x-unknown";
            r2 = "MatroskaExtractor";
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "Unsupported PCM bit depth: ";
            r3.append(r4);
            r4 = r0.audioBitDepth;
            r3.append(r4);
            r4 = ". Setting mimeType to ";
            r3.append(r4);
            r3.append(r1);
            r3 = r3.toString();
            com.google.android.exoplayer2.util.Log.w(r2, r3);
            goto L_0x031d;
        L_0x0215:
            r1 = "audio/x-unknown";
            r2 = "MatroskaExtractor";
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "Non-PCM MS/ACM is unsupported. Setting mimeType to ";
            r3.append(r4);
            r3.append(r1);
            r3 = r3.toString();
            com.google.android.exoplayer2.util.Log.w(r2, r3);
            goto L_0x031d;
        L_0x022f:
            r1 = "audio/flac";
            r2 = r0.codecPrivate;
            r2 = java.util.Collections.singletonList(r2);
            goto L_0x0266;
        L_0x0238:
            r1 = "audio/vnd.dts.hd";
            goto L_0x031d;
        L_0x023c:
            r1 = "audio/vnd.dts";
            goto L_0x031d;
        L_0x0240:
            r1 = "audio/true-hd";
            r2 = new com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker;
            r2.<init>();
            r0.trueHdSampleRechunker = r2;
            goto L_0x031d;
        L_0x024b:
            r1 = "audio/eac3";
            goto L_0x031d;
        L_0x024f:
            r1 = "audio/ac3";
            goto L_0x031d;
        L_0x0253:
            r1 = "audio/mpeg";
            goto L_0x0258;
        L_0x0256:
            r1 = "audio/mpeg-L2";
        L_0x0258:
            r12 = r1;
            r15 = r2;
            r18 = r9;
            goto L_0x0321;
        L_0x025e:
            r1 = "audio/mp4a-latm";
            r2 = r0.codecPrivate;
            r2 = java.util.Collections.singletonList(r2);
        L_0x0266:
            r12 = r1;
            goto L_0x0300;
        L_0x0269:
            r1 = "audio/opus";
            r2 = 5760; // 0x1680 float:8.071E-42 double:2.846E-320;
            r3 = new java.util.ArrayList;
            r3.<init>(r8);
            r11 = r0.codecPrivate;
            r3.add(r11);
            r11 = java.nio.ByteBuffer.allocate(r4);
            r12 = java.nio.ByteOrder.nativeOrder();
            r11 = r11.order(r12);
            r12 = r0.codecDelayNs;
            r11 = r11.putLong(r12);
            r11 = r11.array();
            r3.add(r11);
            r4 = java.nio.ByteBuffer.allocate(r4);
            r11 = java.nio.ByteOrder.nativeOrder();
            r4 = r4.order(r11);
            r11 = r0.seekPreRollNs;
            r4 = r4.putLong(r11);
            r4 = r4.array();
            r3.add(r4);
            goto L_0x02b4;
        L_0x02aa:
            r1 = "audio/vorbis";
            r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
            r3 = r0.codecPrivate;
            r3 = parseVorbisCodecPrivate(r3);
        L_0x02b4:
            r12 = r1;
            r15 = r2;
            r2 = r3;
            r18 = r9;
            goto L_0x0322;
        L_0x02bb:
            r1 = "video/x-unknown";
            goto L_0x031d;
        L_0x02be:
            r1 = new com.google.android.exoplayer2.util.ParsableByteArray;
            r2 = r0.codecPrivate;
            r1.<init>(r2);
            r1 = parseFourCcPrivate(r1);
            r2 = r1.first;
            r2 = (java.lang.String) r2;
            r1 = r1.second;
            r1 = (java.util.List) r1;
            r12 = r2;
            r15 = r9;
            r18 = r15;
            r2 = r1;
            goto L_0x0322;
        L_0x02d7:
            r1 = "video/hevc";
            r2 = new com.google.android.exoplayer2.util.ParsableByteArray;
            r3 = r0.codecPrivate;
            r2.<init>(r3);
            r2 = com.google.android.exoplayer2.video.HevcConfig.parse(r2);
            r3 = r2.initializationData;
            r2 = r2.nalUnitLengthFieldLength;
            r0.nalUnitLengthFieldLength = r2;
            goto L_0x02fe;
        L_0x02eb:
            r1 = "video/avc";
            r2 = new com.google.android.exoplayer2.util.ParsableByteArray;
            r3 = r0.codecPrivate;
            r2.<init>(r3);
            r2 = com.google.android.exoplayer2.video.AvcConfig.parse(r2);
            r3 = r2.initializationData;
            r2 = r2.nalUnitLengthFieldLength;
            r0.nalUnitLengthFieldLength = r2;
        L_0x02fe:
            r12 = r1;
            r2 = r3;
        L_0x0300:
            r15 = r9;
            r18 = r15;
            goto L_0x0322;
        L_0x0304:
            r1 = "video/mp4v-es";
            r2 = r0.codecPrivate;
            if (r2 != 0) goto L_0x030d;
        L_0x030a:
            r2 = r10;
            goto L_0x0266;
        L_0x030d:
            r2 = r0.codecPrivate;
            r2 = java.util.Collections.singletonList(r2);
            goto L_0x0266;
        L_0x0315:
            r1 = "video/mpeg2";
            goto L_0x031d;
        L_0x0318:
            r1 = "video/x-vnd.on2.vp9";
            goto L_0x031d;
        L_0x031b:
            r1 = "video/x-vnd.on2.vp8";
        L_0x031d:
            r12 = r1;
            r15 = r9;
            r18 = r15;
        L_0x0321:
            r2 = r10;
        L_0x0322:
            r1 = r0.flagDefault;
            r1 = r1 | r6;
            r3 = r0.flagForced;
            if (r3 == 0) goto L_0x032b;
        L_0x0329:
            r3 = r7;
            goto L_0x032c;
        L_0x032b:
            r3 = r6;
        L_0x032c:
            r1 = r1 | r3;
            r3 = com.google.android.exoplayer2.util.MimeTypes.isAudio(r12);
            if (r3 == 0) goto L_0x0354;
        L_0x0333:
            r11 = java.lang.Integer.toString(r28);
            r13 = 0;
            r14 = -1;
            r3 = r0.channelCount;
            r4 = r0.sampleRate;
            r6 = r0.drmInitData;
            r7 = r0.language;
            r16 = r3;
            r17 = r4;
            r19 = r2;
            r20 = r6;
            r21 = r1;
            r22 = r7;
            r1 = com.google.android.exoplayer2.Format.createAudioSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22);
            r8 = r5;
            goto L_0x047f;
        L_0x0354:
            r3 = com.google.android.exoplayer2.util.MimeTypes.isVideo(r12);
            if (r3 == 0) goto L_0x03fd;
        L_0x035a:
            r1 = r0.displayUnit;
            if (r1 != 0) goto L_0x0374;
        L_0x035e:
            r1 = r0.displayWidth;
            if (r1 != r9) goto L_0x0365;
        L_0x0362:
            r1 = r0.width;
            goto L_0x0367;
        L_0x0365:
            r1 = r0.displayWidth;
        L_0x0367:
            r0.displayWidth = r1;
            r1 = r0.displayHeight;
            if (r1 != r9) goto L_0x0370;
        L_0x036d:
            r1 = r0.height;
            goto L_0x0372;
        L_0x0370:
            r1 = r0.displayHeight;
        L_0x0372:
            r0.displayHeight = r1;
        L_0x0374:
            r1 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r3 = r0.displayWidth;
            if (r3 == r9) goto L_0x038b;
        L_0x037a:
            r3 = r0.displayHeight;
            if (r3 == r9) goto L_0x038b;
        L_0x037e:
            r1 = r0.height;
            r3 = r0.displayWidth;
            r1 = r1 * r3;
            r1 = (float) r1;
            r3 = r0.width;
            r4 = r0.displayHeight;
            r3 = r3 * r4;
            r3 = (float) r3;
            r1 = r1 / r3;
        L_0x038b:
            r21 = r1;
            r1 = r0.hasColorInfo;
            if (r1 == 0) goto L_0x03a0;
        L_0x0391:
            r1 = r26.getHdrStaticInfo();
            r10 = new com.google.android.exoplayer2.video.ColorInfo;
            r3 = r0.colorSpace;
            r4 = r0.colorRange;
            r5 = r0.colorTransfer;
            r10.<init>(r3, r4, r5, r1);
        L_0x03a0:
            r24 = r10;
            r1 = "htc_video_rotA-000";
            r3 = r0.name;
            r1 = r1.equals(r3);
            if (r1 == 0) goto L_0x03af;
        L_0x03ac:
            r20 = r6;
            goto L_0x03d8;
        L_0x03af:
            r1 = "htc_video_rotA-090";
            r3 = r0.name;
            r1 = r1.equals(r3);
            if (r1 == 0) goto L_0x03bc;
        L_0x03b9:
            r6 = 90;
            goto L_0x03ac;
        L_0x03bc:
            r1 = "htc_video_rotA-180";
            r3 = r0.name;
            r1 = r1.equals(r3);
            if (r1 == 0) goto L_0x03c9;
        L_0x03c6:
            r6 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
            goto L_0x03ac;
        L_0x03c9:
            r1 = "htc_video_rotA-270";
            r3 = r0.name;
            r1 = r1.equals(r3);
            if (r1 == 0) goto L_0x03d6;
        L_0x03d3:
            r6 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
            goto L_0x03ac;
        L_0x03d6:
            r20 = r9;
        L_0x03d8:
            r11 = java.lang.Integer.toString(r28);
            r13 = 0;
            r14 = -1;
            r1 = r0.width;
            r3 = r0.height;
            r18 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r4 = r0.projectionData;
            r5 = r0.stereoMode;
            r6 = r0.drmInitData;
            r16 = r1;
            r17 = r3;
            r19 = r2;
            r22 = r4;
            r23 = r5;
            r25 = r6;
            r1 = com.google.android.exoplayer2.Format.createVideoSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25);
            r8 = r7;
            goto L_0x047f;
        L_0x03fd:
            r3 = "application/x-subrip";
            r3 = r3.equals(r12);
            if (r3 == 0) goto L_0x0413;
        L_0x0405:
            r2 = java.lang.Integer.toString(r28);
            r3 = r0.language;
            r4 = r0.drmInitData;
            r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(r2, r12, r1, r3, r4);
            goto L_0x047f;
        L_0x0413:
            r3 = "text/x-ssa";
            r3 = r3.equals(r12);
            if (r3 == 0) goto L_0x0449;
        L_0x041b:
            r2 = new java.util.ArrayList;
            r2.<init>(r7);
            r3 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.SSA_DIALOGUE_FORMAT;
            r2.add(r3);
            r3 = r0.codecPrivate;
            r2.add(r3);
            r11 = java.lang.Integer.toString(r28);
            r13 = 0;
            r14 = -1;
            r3 = r0.language;
            r17 = -1;
            r4 = r0.drmInitData;
            r19 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r15 = r1;
            r16 = r3;
            r18 = r4;
            r21 = r2;
            r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18, r19, r21);
            goto L_0x047f;
        L_0x0449:
            r3 = "application/vobsub";
            r3 = r3.equals(r12);
            if (r3 != 0) goto L_0x046a;
        L_0x0451:
            r3 = "application/pgs";
            r3 = r3.equals(r12);
            if (r3 != 0) goto L_0x046a;
        L_0x0459:
            r3 = "application/dvbsubs";
            r3 = r3.equals(r12);
            if (r3 == 0) goto L_0x0462;
        L_0x0461:
            goto L_0x046a;
        L_0x0462:
            r1 = new com.google.android.exoplayer2.ParserException;
            r2 = "Unexpected MIME type.";
            r1.<init>(r2);
            throw r1;
        L_0x046a:
            r11 = java.lang.Integer.toString(r28);
            r13 = 0;
            r14 = -1;
            r3 = r0.language;
            r4 = r0.drmInitData;
            r15 = r1;
            r16 = r2;
            r17 = r3;
            r18 = r4;
            r1 = com.google.android.exoplayer2.Format.createImageSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18);
        L_0x047f:
            r2 = r0.number;
            r3 = r27;
            r2 = r3.track(r2, r8);
            r0.output = r2;
            r2 = r0.output;
            r2.format(r1);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track.initializeOutput(com.google.android.exoplayer2.extractor.ExtractorOutput, int):void");
        }

        public void outputPendingSampleMetadata() {
            if (this.trueHdSampleRechunker != null) {
                this.trueHdSampleRechunker.outputPendingSampleMetadata(this);
            }
        }

        public void reset() {
            if (this.trueHdSampleRechunker != null) {
                this.trueHdSampleRechunker.reset();
            }
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.put((byte) 0);
            wrap.putShort((short) ((int) ((this.primaryRChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryRChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) (this.maxMasteringLuminance + 0.5f)));
            wrap.putShort((short) ((int) (this.minMasteringLuminance + 0.5f)));
            wrap.putShort((short) this.maxContentLuminance);
            wrap.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long readLittleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (readLittleEndianUnsignedInt == 1482049860) {
                    return new Pair(MimeTypes.VIDEO_H263, null);
                }
                if (readLittleEndianUnsignedInt == 826496599) {
                    int position = parsableByteArray.getPosition() + 20;
                    byte[] bArr = parsableByteArray.data;
                    while (position < bArr.length - 4) {
                        if (bArr[position] == (byte) 0 && bArr[position + 1] == (byte) 0 && bArr[position + 2] == (byte) 1 && bArr[position + 3] == (byte) 15) {
                            return new Pair(MimeTypes.VIDEO_VC1, Collections.singletonList(Arrays.copyOfRange(bArr, position, bArr.length)));
                        }
                        position++;
                    }
                    throw new ParserException("Failed to find FourCC VC1 initialization data");
                }
                Log.w(MatroskaExtractor.TAG, "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair(MimeTypes.VIDEO_UNKNOWN, null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing FourCC private data");
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            try {
                if (bArr[0] != (byte) 2) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                int i = 0;
                int i2 = 1;
                while (bArr[i2] == (byte) -1) {
                    i += 255;
                    i2++;
                }
                int i3 = i2 + 1;
                i += bArr[i2];
                i2 = 0;
                while (bArr[i3] == (byte) -1) {
                    i2 += 255;
                    i3++;
                }
                int i4 = i3 + 1;
                i2 += bArr[i3];
                if (bArr[i4] != (byte) 1) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, i4, bArr2, 0, i);
                i4 += i;
                if (bArr[i4] != (byte) 3) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                i4 += i2;
                if (bArr[i4] != (byte) 5) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] bArr3 = new byte[(bArr.length - i4)];
                System.arraycopy(bArr, i4, bArr3, 0, bArr.length - i4);
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(bArr2);
                arrayList.add(bArr3);
                return arrayList;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                boolean z = true;
                if (readLittleEndianUnsignedShort == 1) {
                    return true;
                }
                if (readLittleEndianUnsignedShort != MatroskaExtractor.WAVE_FORMAT_EXTENSIBLE) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                if (!(parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits())) {
                    z = false;
                }
                return z;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }
    }

    private static final class TrueHdSampleRechunker {
        private int blockFlags;
        private int chunkSize;
        private boolean foundSyncframe;
        private int sampleCount;
        private final byte[] syncframePrefix = new byte[10];
        private long timeUs;

        public void reset() {
            this.foundSyncframe = false;
        }

        public void startSample(ExtractorInput extractorInput, int i, int i2) throws IOException, InterruptedException {
            if (!this.foundSyncframe) {
                extractorInput.peekFully(this.syncframePrefix, 0, 10);
                extractorInput.resetPeekPosition();
                if (Ac3Util.parseTrueHdSyncframeAudioSampleCount(this.syncframePrefix) != 0) {
                    this.foundSyncframe = true;
                    this.sampleCount = 0;
                } else {
                    return;
                }
            }
            if (this.sampleCount == 0) {
                this.blockFlags = i;
                this.chunkSize = 0;
            }
            this.chunkSize += i2;
        }

        public void sampleMetadata(Track track, long j) {
            if (this.foundSyncframe) {
                int i = this.sampleCount;
                this.sampleCount = i + 1;
                if (i == 0) {
                    this.timeUs = j;
                }
                if (this.sampleCount >= 16) {
                    track.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, track.cryptoData);
                    this.sampleCount = 0;
                }
            }
        }

        public void outputPendingSampleMetadata(Track track) {
            if (this.foundSyncframe && this.sampleCount > 0) {
                track.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, track.cryptoData);
                this.sampleCount = 0;
            }
        }
    }

    private final class InnerEbmlReaderOutput implements EbmlReaderOutput {
        public int getElementType(int i) {
            switch (i) {
                case MatroskaExtractor.ID_TRACK_TYPE /*131*/:
                case MatroskaExtractor.ID_FLAG_DEFAULT /*136*/:
                case MatroskaExtractor.ID_BLOCK_DURATION /*155*/:
                case MatroskaExtractor.ID_CHANNELS /*159*/:
                case MatroskaExtractor.ID_PIXEL_WIDTH /*176*/:
                case MatroskaExtractor.ID_CUE_TIME /*179*/:
                case MatroskaExtractor.ID_PIXEL_HEIGHT /*186*/:
                case MatroskaExtractor.ID_TRACK_NUMBER /*215*/:
                case MatroskaExtractor.ID_TIME_CODE /*231*/:
                case MatroskaExtractor.ID_CUE_CLUSTER_POSITION /*241*/:
                case MatroskaExtractor.ID_REFERENCE_BLOCK /*251*/:
                case MatroskaExtractor.ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
                case MatroskaExtractor.ID_DOC_TYPE_READ_VERSION /*17029*/:
                case MatroskaExtractor.ID_EBML_READ_VERSION /*17143*/:
                case MatroskaExtractor.ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
                case MatroskaExtractor.ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
                case MatroskaExtractor.ID_CONTENT_ENCODING_ORDER /*20529*/:
                case MatroskaExtractor.ID_CONTENT_ENCODING_SCOPE /*20530*/:
                case MatroskaExtractor.ID_SEEK_POSITION /*21420*/:
                case MatroskaExtractor.ID_STEREO_MODE /*21432*/:
                case MatroskaExtractor.ID_DISPLAY_WIDTH /*21680*/:
                case MatroskaExtractor.ID_DISPLAY_UNIT /*21682*/:
                case MatroskaExtractor.ID_DISPLAY_HEIGHT /*21690*/:
                case MatroskaExtractor.ID_FLAG_FORCED /*21930*/:
                case MatroskaExtractor.ID_COLOUR_RANGE /*21945*/:
                case MatroskaExtractor.ID_COLOUR_TRANSFER /*21946*/:
                case MatroskaExtractor.ID_COLOUR_PRIMARIES /*21947*/:
                case MatroskaExtractor.ID_MAX_CLL /*21948*/:
                case MatroskaExtractor.ID_MAX_FALL /*21949*/:
                case MatroskaExtractor.ID_CODEC_DELAY /*22186*/:
                case MatroskaExtractor.ID_SEEK_PRE_ROLL /*22203*/:
                case MatroskaExtractor.ID_AUDIO_BIT_DEPTH /*25188*/:
                case MatroskaExtractor.ID_DEFAULT_DURATION /*2352003*/:
                case MatroskaExtractor.ID_TIMECODE_SCALE /*2807729*/:
                    return 2;
                case 134:
                case 17026:
                case MatroskaExtractor.ID_NAME /*21358*/:
                case MatroskaExtractor.ID_LANGUAGE /*2274716*/:
                    return 3;
                case 160:
                case MatroskaExtractor.ID_TRACK_ENTRY /*174*/:
                case MatroskaExtractor.ID_CUE_TRACK_POSITIONS /*183*/:
                case MatroskaExtractor.ID_CUE_POINT /*187*/:
                case 224:
                case MatroskaExtractor.ID_AUDIO /*225*/:
                case MatroskaExtractor.ID_CONTENT_ENCRYPTION_AES_SETTINGS /*18407*/:
                case MatroskaExtractor.ID_SEEK /*19899*/:
                case MatroskaExtractor.ID_CONTENT_COMPRESSION /*20532*/:
                case MatroskaExtractor.ID_CONTENT_ENCRYPTION /*20533*/:
                case MatroskaExtractor.ID_COLOUR /*21936*/:
                case MatroskaExtractor.ID_MASTERING_METADATA /*21968*/:
                case MatroskaExtractor.ID_CONTENT_ENCODING /*25152*/:
                case MatroskaExtractor.ID_CONTENT_ENCODINGS /*28032*/:
                case MatroskaExtractor.ID_PROJECTION /*30320*/:
                case MatroskaExtractor.ID_SEEK_HEAD /*290298740*/:
                case 357149030:
                case MatroskaExtractor.ID_TRACKS /*374648427*/:
                case MatroskaExtractor.ID_SEGMENT /*408125543*/:
                case MatroskaExtractor.ID_EBML /*440786851*/:
                case MatroskaExtractor.ID_CUES /*475249515*/:
                case MatroskaExtractor.ID_CLUSTER /*524531317*/:
                    return 1;
                case MatroskaExtractor.ID_BLOCK /*161*/:
                case MatroskaExtractor.ID_SIMPLE_BLOCK /*163*/:
                case MatroskaExtractor.ID_CONTENT_COMPRESSION_SETTINGS /*16981*/:
                case MatroskaExtractor.ID_CONTENT_ENCRYPTION_KEY_ID /*18402*/:
                case MatroskaExtractor.ID_SEEK_ID /*21419*/:
                case MatroskaExtractor.ID_CODEC_PRIVATE /*25506*/:
                case MatroskaExtractor.ID_PROJECTION_PRIVATE /*30322*/:
                    return 4;
                case MatroskaExtractor.ID_SAMPLING_FREQUENCY /*181*/:
                case MatroskaExtractor.ID_DURATION /*17545*/:
                case MatroskaExtractor.ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
                case MatroskaExtractor.ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
                case MatroskaExtractor.ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
                case MatroskaExtractor.ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
                case MatroskaExtractor.ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
                case MatroskaExtractor.ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
                case MatroskaExtractor.ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
                case MatroskaExtractor.ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
                case MatroskaExtractor.ID_LUMNINANCE_MAX /*21977*/:
                case MatroskaExtractor.ID_LUMNINANCE_MIN /*21978*/:
                    return 5;
                default:
                    return 0;
            }
        }

        public boolean isLevel1Element(int i) {
            return i == 357149030 || i == MatroskaExtractor.ID_CLUSTER || i == MatroskaExtractor.ID_CUES || i == MatroskaExtractor.ID_TRACKS;
        }

        private InnerEbmlReaderOutput() {
        }

        public void startMasterElement(int i, long j, long j2) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i, j, j2);
        }

        public void endMasterElement(int i) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i);
        }

        public void integerElement(int i, long j) throws ParserException {
            MatroskaExtractor.this.integerElement(i, j);
        }

        public void floatElement(int i, double d) throws ParserException {
            MatroskaExtractor.this.floatElement(i, d);
        }

        public void stringElement(int i, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i, str);
        }

        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException, InterruptedException {
            MatroskaExtractor.this.binaryElement(i, i2, extractorInput);
        }
    }

    public void release() {
    }

    public MatroskaExtractor() {
        this(0);
    }

    public MatroskaExtractor(int i) {
        this(new DefaultEbmlReader(), i);
    }

    MatroskaExtractor(EbmlReader ebmlReader, int i) {
        this.segmentContentPosition = -1;
        this.timecodeScale = C.TIME_UNSET;
        this.durationTimecode = C.TIME_UNSET;
        this.durationUs = C.TIME_UNSET;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.reader = ebmlReader;
        this.reader.init(new InnerEbmlReaderOutput());
        boolean z = true;
        if ((i & 1) != 0) {
            z = false;
        }
        this.seekForCuesEnabled = z;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return new Sniffer().sniff(extractorInput);
    }

    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
    }

    public void seek(long j, long j2) {
        this.clusterTimecodeUs = C.TIME_UNSET;
        int i = 0;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetSample();
        while (i < this.tracks.size()) {
            ((Track) this.tracks.valueAt(i)).reset();
            i++;
        }
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        int i = 0;
        this.sampleRead = false;
        boolean z = true;
        while (z && !this.sampleRead) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        while (i < this.tracks.size()) {
            ((Track) this.tracks.valueAt(i)).outputPendingSampleMetadata();
            i++;
        }
        return -1;
    }

    /* Access modifiers changed, original: 0000 */
    public void startMasterElement(int i, long j, long j2) throws ParserException {
        if (i == 160) {
            this.sampleSeenReferenceBlock = false;
        } else if (i == ID_TRACK_ENTRY) {
            this.currentTrack = new Track();
        } else if (i == ID_CUE_POINT) {
            this.seenClusterPositionForCurrentCuePoint = false;
        } else if (i == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1;
        } else if (i == ID_CONTENT_ENCRYPTION) {
            this.currentTrack.hasContentEncryption = true;
        } else if (i == ID_MASTERING_METADATA) {
            this.currentTrack.hasColorInfo = true;
        } else if (i == ID_CONTENT_ENCODING) {
        } else {
            if (i != ID_SEGMENT) {
                if (i == ID_CUES) {
                    this.cueTimesUs = new LongArray();
                    this.cueClusterPositions = new LongArray();
                } else if (i != ID_CLUSTER || this.sentSeekMap) {
                } else {
                    if (!this.seekForCuesEnabled || this.cuesContentPosition == -1) {
                        this.extractorOutput.seekMap(new Unseekable(this.durationUs));
                        this.sentSeekMap = true;
                        return;
                    }
                    this.seekForCues = true;
                }
            } else if (this.segmentContentPosition == -1 || this.segmentContentPosition == j) {
                this.segmentContentPosition = j;
                this.segmentContentSize = j2;
            } else {
                throw new ParserException("Multiple Segment elements not supported");
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void endMasterElement(int i) throws ParserException {
        if (i != 160) {
            if (i == ID_TRACK_ENTRY) {
                if (isCodecSupported(this.currentTrack.codecId)) {
                    this.currentTrack.initializeOutput(this.extractorOutput, this.currentTrack.number);
                    this.tracks.put(this.currentTrack.number, this.currentTrack);
                }
                this.currentTrack = null;
            } else if (i != ID_SEEK) {
                if (i != ID_CONTENT_ENCODING) {
                    if (i != ID_CONTENT_ENCODINGS) {
                        if (i == 357149030) {
                            if (this.timecodeScale == C.TIME_UNSET) {
                                this.timecodeScale = 1000000;
                            }
                            if (this.durationTimecode != C.TIME_UNSET) {
                                this.durationUs = scaleTimecodeToUs(this.durationTimecode);
                            }
                        } else if (i != ID_TRACKS) {
                            if (i == ID_CUES && !this.sentSeekMap) {
                                this.extractorOutput.seekMap(buildSeekMap());
                                this.sentSeekMap = true;
                            }
                        } else if (this.tracks.size() == 0) {
                            throw new ParserException("No valid tracks were found");
                        } else {
                            this.extractorOutput.endTracks();
                        }
                    } else if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                        throw new ParserException("Combining encryption and compression is not supported");
                    }
                } else if (this.currentTrack.hasContentEncryption) {
                    if (this.currentTrack.cryptoData == null) {
                        throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
                    }
                    this.currentTrack.drmInitData = new DrmInitData(new SchemeData(C.UUID_NIL, MimeTypes.VIDEO_WEBM, this.currentTrack.cryptoData.encryptionKey));
                }
            } else if (this.seekEntryId == -1 || this.seekEntryPosition == -1) {
                throw new ParserException("Mandatory element SeekID or SeekPosition not found");
            } else if (this.seekEntryId == ID_CUES) {
                this.cuesContentPosition = this.seekEntryPosition;
            }
        } else if (this.blockState == 2) {
            if (!this.sampleSeenReferenceBlock) {
                this.blockFlags |= 1;
            }
            commitSampleToOutput((Track) this.tracks.get(this.blockTrackNumber), this.blockTimeUs);
            this.blockState = 0;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void integerElement(int i, long j) throws ParserException {
        boolean z = false;
        Track track;
        StringBuilder stringBuilder;
        switch (i) {
            case ID_TRACK_TYPE /*131*/:
                this.currentTrack.type = (int) j;
                return;
            case ID_FLAG_DEFAULT /*136*/:
                track = this.currentTrack;
                if (j == 1) {
                    z = true;
                }
                track.flagDefault = z;
                return;
            case ID_BLOCK_DURATION /*155*/:
                this.blockDurationUs = scaleTimecodeToUs(j);
                return;
            case ID_CHANNELS /*159*/:
                this.currentTrack.channelCount = (int) j;
                return;
            case ID_PIXEL_WIDTH /*176*/:
                this.currentTrack.width = (int) j;
                return;
            case ID_CUE_TIME /*179*/:
                this.cueTimesUs.add(scaleTimecodeToUs(j));
                return;
            case ID_PIXEL_HEIGHT /*186*/:
                this.currentTrack.height = (int) j;
                return;
            case ID_TRACK_NUMBER /*215*/:
                this.currentTrack.number = (int) j;
                return;
            case ID_TIME_CODE /*231*/:
                this.clusterTimecodeUs = scaleTimecodeToUs(j);
                return;
            case ID_CUE_CLUSTER_POSITION /*241*/:
                if (!this.seenClusterPositionForCurrentCuePoint) {
                    this.cueClusterPositions.add(j);
                    this.seenClusterPositionForCurrentCuePoint = true;
                    return;
                }
                return;
            case ID_REFERENCE_BLOCK /*251*/:
                this.sampleSeenReferenceBlock = true;
                return;
            case ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
                if (j != 3) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ContentCompAlgo ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case ID_DOC_TYPE_READ_VERSION /*17029*/:
                if (j < 1 || j > 2) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("DocTypeReadVersion ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case ID_EBML_READ_VERSION /*17143*/:
                if (j != 1) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("EBMLReadVersion ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
                if (j != 5) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ContentEncAlgo ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
                if (j != 1) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("AESSettingsCipherMode ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case ID_CONTENT_ENCODING_ORDER /*20529*/:
                if (j != 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ContentEncodingOrder ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case ID_CONTENT_ENCODING_SCOPE /*20530*/:
                if (j != 1) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ContentEncodingScope ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case ID_SEEK_POSITION /*21420*/:
                this.seekEntryPosition = j + this.segmentContentPosition;
                return;
            case ID_STEREO_MODE /*21432*/:
                i = (int) j;
                if (i == 3) {
                    this.currentTrack.stereoMode = 1;
                    return;
                } else if (i != 15) {
                    switch (i) {
                        case 0:
                            this.currentTrack.stereoMode = 0;
                            return;
                        case 1:
                            this.currentTrack.stereoMode = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    this.currentTrack.stereoMode = 3;
                    return;
                }
            case ID_DISPLAY_WIDTH /*21680*/:
                this.currentTrack.displayWidth = (int) j;
                return;
            case ID_DISPLAY_UNIT /*21682*/:
                this.currentTrack.displayUnit = (int) j;
                return;
            case ID_DISPLAY_HEIGHT /*21690*/:
                this.currentTrack.displayHeight = (int) j;
                return;
            case ID_FLAG_FORCED /*21930*/:
                track = this.currentTrack;
                if (j == 1) {
                    z = true;
                }
                track.flagForced = z;
                return;
            case ID_COLOUR_RANGE /*21945*/:
                switch ((int) j) {
                    case 1:
                        this.currentTrack.colorRange = 2;
                        return;
                    case 2:
                        this.currentTrack.colorRange = 1;
                        return;
                    default:
                        return;
                }
            case ID_COLOUR_TRANSFER /*21946*/:
                i = (int) j;
                if (i != 1) {
                    if (i == 16) {
                        this.currentTrack.colorTransfer = 6;
                        return;
                    } else if (i != 18) {
                        switch (i) {
                            case 6:
                            case 7:
                                break;
                            default:
                                return;
                        }
                    } else {
                        this.currentTrack.colorTransfer = 7;
                        return;
                    }
                }
                this.currentTrack.colorTransfer = 3;
                return;
            case ID_COLOUR_PRIMARIES /*21947*/:
                this.currentTrack.hasColorInfo = true;
                i = (int) j;
                if (i == 1) {
                    this.currentTrack.colorSpace = 1;
                    return;
                } else if (i != 9) {
                    switch (i) {
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            this.currentTrack.colorSpace = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    this.currentTrack.colorSpace = 6;
                    return;
                }
            case ID_MAX_CLL /*21948*/:
                this.currentTrack.maxContentLuminance = (int) j;
                return;
            case ID_MAX_FALL /*21949*/:
                this.currentTrack.maxFrameAverageLuminance = (int) j;
                return;
            case ID_CODEC_DELAY /*22186*/:
                this.currentTrack.codecDelayNs = j;
                return;
            case ID_SEEK_PRE_ROLL /*22203*/:
                this.currentTrack.seekPreRollNs = j;
                return;
            case ID_AUDIO_BIT_DEPTH /*25188*/:
                this.currentTrack.audioBitDepth = (int) j;
                return;
            case ID_DEFAULT_DURATION /*2352003*/:
                this.currentTrack.defaultSampleDurationNs = (int) j;
                return;
            case ID_TIMECODE_SCALE /*2807729*/:
                this.timecodeScale = j;
                return;
            default:
                return;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void floatElement(int i, double d) {
        if (i == ID_SAMPLING_FREQUENCY) {
            this.currentTrack.sampleRate = (int) d;
        } else if (i != ID_DURATION) {
            switch (i) {
                case ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
                    this.currentTrack.primaryRChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
                    this.currentTrack.primaryRChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
                    this.currentTrack.primaryGChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
                    this.currentTrack.primaryGChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
                    this.currentTrack.primaryBChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
                    this.currentTrack.primaryBChromaticityY = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
                    this.currentTrack.whitePointChromaticityX = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
                    this.currentTrack.whitePointChromaticityY = (float) d;
                    return;
                case ID_LUMNINANCE_MAX /*21977*/:
                    this.currentTrack.maxMasteringLuminance = (float) d;
                    return;
                case ID_LUMNINANCE_MIN /*21978*/:
                    this.currentTrack.minMasteringLuminance = (float) d;
                    return;
                default:
                    return;
            }
        } else {
            this.durationTimecode = (long) d;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void stringElement(int i, String str) throws ParserException {
        if (i == 134) {
            this.currentTrack.codecId = str;
        } else if (i != 17026) {
            if (i == ID_NAME) {
                this.currentTrack.name = str;
            } else if (i == ID_LANGUAGE) {
                this.currentTrack.language = str;
            }
        } else if (!DOC_TYPE_WEBM.equals(str) && !DOC_TYPE_MATROSKA.equals(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("DocType ");
            stringBuilder.append(str);
            stringBuilder.append(" not supported");
            throw new ParserException(stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x020d A:{SYNTHETIC, SKIP} */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01ea  */
    public void binaryElement(int r24, int r25, com.google.android.exoplayer2.extractor.ExtractorInput r26) throws java.io.IOException, java.lang.InterruptedException {
        /*
        r23 = this;
        r0 = r23;
        r1 = r24;
        r2 = r25;
        r3 = r26;
        r4 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        r5 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        r6 = 4;
        r7 = 0;
        r8 = 1;
        if (r1 == r4) goto L_0x009a;
    L_0x0011:
        if (r1 == r5) goto L_0x009a;
    L_0x0013:
        r4 = 16981; // 0x4255 float:2.3795E-41 double:8.3897E-320;
        if (r1 == r4) goto L_0x008b;
    L_0x0017:
        r4 = 18402; // 0x47e2 float:2.5787E-41 double:9.092E-320;
        if (r1 == r4) goto L_0x007b;
    L_0x001b:
        r4 = 21419; // 0x53ab float:3.0014E-41 double:1.05824E-319;
        if (r1 == r4) goto L_0x005c;
    L_0x001f:
        r4 = 25506; // 0x63a2 float:3.5742E-41 double:1.26016E-319;
        if (r1 == r4) goto L_0x004d;
    L_0x0023:
        r4 = 30322; // 0x7672 float:4.249E-41 double:1.4981E-319;
        if (r1 == r4) goto L_0x003e;
    L_0x0027:
        r2 = new com.google.android.exoplayer2.ParserException;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Unexpected id: ";
        r3.append(r4);
        r3.append(r1);
        r1 = r3.toString();
        r2.<init>(r1);
        throw r2;
    L_0x003e:
        r1 = r0.currentTrack;
        r4 = new byte[r2];
        r1.projectionData = r4;
        r1 = r0.currentTrack;
        r1 = r1.projectionData;
        r3.readFully(r1, r7, r2);
        goto L_0x02c0;
    L_0x004d:
        r1 = r0.currentTrack;
        r4 = new byte[r2];
        r1.codecPrivate = r4;
        r1 = r0.currentTrack;
        r1 = r1.codecPrivate;
        r3.readFully(r1, r7, r2);
        goto L_0x02c0;
    L_0x005c:
        r1 = r0.seekEntryIdBytes;
        r1 = r1.data;
        java.util.Arrays.fill(r1, r7);
        r1 = r0.seekEntryIdBytes;
        r1 = r1.data;
        r6 = r6 - r2;
        r3.readFully(r1, r6, r2);
        r1 = r0.seekEntryIdBytes;
        r1.setPosition(r7);
        r1 = r0.seekEntryIdBytes;
        r1 = r1.readUnsignedInt();
        r1 = (int) r1;
        r0.seekEntryId = r1;
        goto L_0x02c0;
    L_0x007b:
        r1 = new byte[r2];
        r3.readFully(r1, r7, r2);
        r2 = r0.currentTrack;
        r3 = new com.google.android.exoplayer2.extractor.TrackOutput$CryptoData;
        r3.<init>(r8, r1, r7, r7);
        r2.cryptoData = r3;
        goto L_0x02c0;
    L_0x008b:
        r1 = r0.currentTrack;
        r4 = new byte[r2];
        r1.sampleStrippedBytes = r4;
        r1 = r0.currentTrack;
        r1 = r1.sampleStrippedBytes;
        r3.readFully(r1, r7, r2);
        goto L_0x02c0;
    L_0x009a:
        r4 = r0.blockState;
        r9 = 8;
        if (r4 != 0) goto L_0x00bf;
    L_0x00a0:
        r4 = r0.varintReader;
        r10 = r4.readUnsignedVarint(r3, r7, r8, r9);
        r4 = (int) r10;
        r0.blockTrackNumber = r4;
        r4 = r0.varintReader;
        r4 = r4.getLastLength();
        r0.blockTrackNumberLength = r4;
        r10 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r0.blockDurationUs = r10;
        r0.blockState = r8;
        r4 = r0.scratch;
        r4.reset();
    L_0x00bf:
        r4 = r0.tracks;
        r10 = r0.blockTrackNumber;
        r4 = r4.get(r10);
        r4 = (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track) r4;
        if (r4 != 0) goto L_0x00d5;
    L_0x00cb:
        r1 = r0.blockTrackNumberLength;
        r1 = r2 - r1;
        r3.skipFully(r1);
        r0.blockState = r7;
        return;
    L_0x00d5:
        r10 = r0.blockState;
        if (r10 != r8) goto L_0x028c;
    L_0x00d9:
        r10 = 3;
        r0.readScratch(r3, r10);
        r11 = r0.scratch;
        r11 = r11.data;
        r12 = 2;
        r11 = r11[r12];
        r13 = 6;
        r11 = r11 & r13;
        r11 = r11 >> r8;
        r14 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        if (r11 != 0) goto L_0x00ff;
    L_0x00eb:
        r0.blockLacingSampleCount = r8;
        r6 = r0.blockLacingSampleSizes;
        r6 = ensureArrayCapacity(r6, r8);
        r0.blockLacingSampleSizes = r6;
        r6 = r0.blockLacingSampleSizes;
        r11 = r0.blockTrackNumberLength;
        r2 = r2 - r11;
        r2 = r2 - r10;
        r6[r7] = r2;
        goto L_0x0222;
    L_0x00ff:
        if (r1 == r5) goto L_0x0109;
    L_0x0101:
        r1 = new com.google.android.exoplayer2.ParserException;
        r2 = "Lacing only supported in SimpleBlocks.";
        r1.<init>(r2);
        throw r1;
    L_0x0109:
        r0.readScratch(r3, r6);
        r15 = r0.scratch;
        r15 = r15.data;
        r15 = r15[r10];
        r15 = r15 & r14;
        r15 = r15 + r8;
        r0.blockLacingSampleCount = r15;
        r15 = r0.blockLacingSampleSizes;
        r5 = r0.blockLacingSampleCount;
        r5 = ensureArrayCapacity(r15, r5);
        r0.blockLacingSampleSizes = r5;
        if (r11 != r12) goto L_0x0132;
    L_0x0122:
        r5 = r0.blockTrackNumberLength;
        r2 = r2 - r5;
        r2 = r2 - r6;
        r5 = r0.blockLacingSampleCount;
        r2 = r2 / r5;
        r5 = r0.blockLacingSampleSizes;
        r6 = r0.blockLacingSampleCount;
        java.util.Arrays.fill(r5, r7, r6, r2);
        goto L_0x0222;
    L_0x0132:
        if (r11 != r8) goto L_0x016b;
    L_0x0134:
        r5 = r7;
        r10 = r5;
    L_0x0136:
        r11 = r0.blockLacingSampleCount;
        r11 = r11 - r8;
        if (r5 >= r11) goto L_0x015d;
    L_0x013b:
        r11 = r0.blockLacingSampleSizes;
        r11[r5] = r7;
    L_0x013f:
        r6 = r6 + r8;
        r0.readScratch(r3, r6);
        r11 = r0.scratch;
        r11 = r11.data;
        r13 = r6 + -1;
        r11 = r11[r13];
        r11 = r11 & r14;
        r13 = r0.blockLacingSampleSizes;
        r15 = r13[r5];
        r15 = r15 + r11;
        r13[r5] = r15;
        if (r11 == r14) goto L_0x013f;
    L_0x0155:
        r11 = r0.blockLacingSampleSizes;
        r11 = r11[r5];
        r10 = r10 + r11;
        r5 = r5 + 1;
        goto L_0x0136;
    L_0x015d:
        r5 = r0.blockLacingSampleSizes;
        r11 = r0.blockLacingSampleCount;
        r11 = r11 - r8;
        r13 = r0.blockTrackNumberLength;
        r2 = r2 - r13;
        r2 = r2 - r6;
        r2 = r2 - r10;
        r5[r11] = r2;
        goto L_0x0222;
    L_0x016b:
        if (r11 != r10) goto L_0x0275;
    L_0x016d:
        r5 = r7;
        r10 = r5;
    L_0x016f:
        r11 = r0.blockLacingSampleCount;
        r11 = r11 - r8;
        if (r5 >= r11) goto L_0x0215;
    L_0x0174:
        r11 = r0.blockLacingSampleSizes;
        r11[r5] = r7;
        r6 = r6 + 1;
        r0.readScratch(r3, r6);
        r11 = r0.scratch;
        r11 = r11.data;
        r15 = r6 + -1;
        r11 = r11[r15];
        if (r11 != 0) goto L_0x018f;
    L_0x0187:
        r1 = new com.google.android.exoplayer2.ParserException;
        r2 = "No valid varint length mask found";
        r1.<init>(r2);
        throw r1;
    L_0x018f:
        r16 = 0;
        r11 = r7;
    L_0x0192:
        if (r11 >= r9) goto L_0x01e1;
    L_0x0194:
        r18 = 7 - r11;
        r18 = r8 << r18;
        r12 = r0.scratch;
        r12 = r12.data;
        r12 = r12[r15];
        r12 = r12 & r18;
        if (r12 == 0) goto L_0x01db;
    L_0x01a2:
        r6 = r6 + r11;
        r0.readScratch(r3, r6);
        r12 = r0.scratch;
        r12 = r12.data;
        r16 = r15 + 1;
        r12 = r12[r15];
        r12 = r12 & r14;
        r15 = r18 ^ -1;
        r12 = r12 & r15;
        r7 = (long) r12;
        r21 = r7;
        r7 = r16;
        r16 = r21;
    L_0x01b9:
        if (r7 >= r6) goto L_0x01cd;
    L_0x01bb:
        r16 = r16 << r9;
        r8 = r0.scratch;
        r8 = r8.data;
        r12 = r7 + 1;
        r7 = r8[r7];
        r7 = r7 & r14;
        r7 = (long) r7;
        r19 = r16 | r7;
        r7 = r12;
        r16 = r19;
        goto L_0x01b9;
    L_0x01cd:
        if (r5 <= 0) goto L_0x01e1;
    L_0x01cf:
        r11 = r11 * 7;
        r11 = r11 + r13;
        r7 = 1;
        r11 = r7 << r11;
        r19 = r11 - r7;
        r7 = r16 - r19;
        goto L_0x01e3;
    L_0x01db:
        r11 = r11 + 1;
        r7 = 0;
        r8 = 1;
        r12 = 2;
        goto L_0x0192;
    L_0x01e1:
        r7 = r16;
    L_0x01e3:
        r11 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r15 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1));
        if (r15 < 0) goto L_0x020d;
    L_0x01ea:
        r11 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r15 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1));
        if (r15 <= 0) goto L_0x01f2;
    L_0x01f1:
        goto L_0x020d;
    L_0x01f2:
        r7 = (int) r7;
        r8 = r0.blockLacingSampleSizes;
        if (r5 != 0) goto L_0x01f8;
    L_0x01f7:
        goto L_0x01ff;
    L_0x01f8:
        r11 = r0.blockLacingSampleSizes;
        r12 = r5 + -1;
        r11 = r11[r12];
        r7 = r7 + r11;
    L_0x01ff:
        r8[r5] = r7;
        r7 = r0.blockLacingSampleSizes;
        r7 = r7[r5];
        r10 = r10 + r7;
        r5 = r5 + 1;
        r7 = 0;
        r8 = 1;
        r12 = 2;
        goto L_0x016f;
    L_0x020d:
        r1 = new com.google.android.exoplayer2.ParserException;
        r2 = "EBML lacing sample size out of range.";
        r1.<init>(r2);
        throw r1;
    L_0x0215:
        r5 = r0.blockLacingSampleSizes;
        r7 = r0.blockLacingSampleCount;
        r8 = 1;
        r7 = r7 - r8;
        r8 = r0.blockTrackNumberLength;
        r2 = r2 - r8;
        r2 = r2 - r6;
        r2 = r2 - r10;
        r5[r7] = r2;
    L_0x0222:
        r2 = r0.scratch;
        r2 = r2.data;
        r5 = 0;
        r2 = r2[r5];
        r2 = r2 << r9;
        r5 = r0.scratch;
        r5 = r5.data;
        r6 = 1;
        r5 = r5[r6];
        r5 = r5 & r14;
        r2 = r2 | r5;
        r5 = r0.clusterTimecodeUs;
        r7 = (long) r2;
        r7 = r0.scaleTimecodeToUs(r7);
        r10 = r5 + r7;
        r0.blockTimeUs = r10;
        r2 = r0.scratch;
        r2 = r2.data;
        r5 = 2;
        r2 = r2[r5];
        r2 = r2 & r9;
        if (r2 != r9) goto L_0x024a;
    L_0x0248:
        r2 = 1;
        goto L_0x024b;
    L_0x024a:
        r2 = 0;
    L_0x024b:
        r6 = r4.type;
        if (r6 == r5) goto L_0x0261;
    L_0x024f:
        r6 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        if (r1 != r6) goto L_0x025f;
    L_0x0253:
        r6 = r0.scratch;
        r6 = r6.data;
        r6 = r6[r5];
        r5 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r6 = r6 & r5;
        if (r6 != r5) goto L_0x025f;
    L_0x025e:
        goto L_0x0261;
    L_0x025f:
        r5 = 0;
        goto L_0x0262;
    L_0x0261:
        r5 = 1;
    L_0x0262:
        if (r2 == 0) goto L_0x0267;
    L_0x0264:
        r7 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        goto L_0x0268;
    L_0x0267:
        r7 = 0;
    L_0x0268:
        r2 = r5 | r7;
        r0.blockFlags = r2;
        r2 = 2;
        r0.blockState = r2;
        r2 = 0;
        r0.blockLacingSampleIndex = r2;
        r2 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        goto L_0x028d;
    L_0x0275:
        r1 = new com.google.android.exoplayer2.ParserException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Unexpected lacing value: ";
        r2.append(r3);
        r2.append(r11);
        r2 = r2.toString();
        r1.<init>(r2);
        throw r1;
    L_0x028c:
        r2 = r5;
    L_0x028d:
        if (r1 != r2) goto L_0x02b8;
    L_0x028f:
        r1 = r0.blockLacingSampleIndex;
        r2 = r0.blockLacingSampleCount;
        if (r1 >= r2) goto L_0x02b4;
    L_0x0295:
        r1 = r0.blockLacingSampleSizes;
        r2 = r0.blockLacingSampleIndex;
        r1 = r1[r2];
        r0.writeSampleData(r3, r4, r1);
        r1 = r0.blockTimeUs;
        r5 = r0.blockLacingSampleIndex;
        r6 = r4.defaultSampleDurationNs;
        r5 = r5 * r6;
        r5 = r5 / 1000;
        r5 = (long) r5;
        r7 = r1 + r5;
        r0.commitSampleToOutput(r4, r7);
        r1 = r0.blockLacingSampleIndex;
        r2 = 1;
        r1 = r1 + r2;
        r0.blockLacingSampleIndex = r1;
        goto L_0x028f;
    L_0x02b4:
        r1 = 0;
        r0.blockState = r1;
        goto L_0x02c0;
    L_0x02b8:
        r1 = 0;
        r2 = r0.blockLacingSampleSizes;
        r1 = r2[r1];
        r0.writeSampleData(r3, r4, r1);
    L_0x02c0:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.binaryElement(int, int, com.google.android.exoplayer2.extractor.ExtractorInput):void");
    }

    private void commitSampleToOutput(Track track, long j) {
        Track track2 = track;
        if (track2.trueHdSampleRechunker != null) {
            track2.trueHdSampleRechunker.sampleMetadata(track2, j);
        } else {
            long j2 = j;
            if (CODEC_ID_SUBRIP.equals(track2.codecId)) {
                commitSubtitleSample(track2, SUBRIP_TIMECODE_FORMAT, 19, 1000, SUBRIP_TIMECODE_EMPTY);
            } else if (CODEC_ID_ASS.equals(track2.codecId)) {
                commitSubtitleSample(track2, SSA_TIMECODE_FORMAT, 21, 10000, SSA_TIMECODE_EMPTY);
            }
            track2.output.sampleMetadata(j2, this.blockFlags, this.sampleBytesWritten, 0, track2.cryptoData);
        }
        this.sampleRead = true;
        resetSample();
    }

    private void resetSample() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = (byte) 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }

    private void readScratch(ExtractorInput extractorInput, int i) throws IOException, InterruptedException {
        if (this.scratch.limit() < i) {
            if (this.scratch.capacity() < i) {
                this.scratch.reset(Arrays.copyOf(this.scratch.data, Math.max(this.scratch.data.length * 2, i)), this.scratch.limit());
            }
            extractorInput.readFully(this.scratch.data, this.scratch.limit(), i - this.scratch.limit());
            this.scratch.setLimit(i);
        }
    }

    private void writeSampleData(ExtractorInput extractorInput, Track track, int i) throws IOException, InterruptedException {
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i);
        } else if (CODEC_ID_ASS.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i);
        } else {
            TrackOutput trackOutput = track.output;
            boolean z = true;
            if (!this.sampleEncodingHandled) {
                if (track.hasContentEncryption) {
                    this.blockFlags &= -1073741825;
                    int i2 = 128;
                    if (!this.sampleSignalByteRead) {
                        extractorInput.readFully(this.scratch.data, 0, 1);
                        this.sampleBytesRead++;
                        if ((this.scratch.data[0] & 128) == 128) {
                            throw new ParserException("Extension bit is set in signal byte");
                        }
                        this.sampleSignalByte = this.scratch.data[0];
                        this.sampleSignalByteRead = true;
                    }
                    if ((this.sampleSignalByte & 1) == 1) {
                        boolean z2 = (this.sampleSignalByte & 2) == 2;
                        this.blockFlags |= 1073741824;
                        if (!this.sampleInitializationVectorRead) {
                            extractorInput.readFully(this.encryptionInitializationVector.data, 0, 8);
                            this.sampleBytesRead += 8;
                            this.sampleInitializationVectorRead = true;
                            byte[] bArr = this.scratch.data;
                            if (!z2) {
                                i2 = 0;
                            }
                            bArr[0] = (byte) (i2 | 8);
                            this.scratch.setPosition(0);
                            trackOutput.sampleData(this.scratch, 1);
                            this.sampleBytesWritten++;
                            this.encryptionInitializationVector.setPosition(0);
                            trackOutput.sampleData(this.encryptionInitializationVector, 8);
                            this.sampleBytesWritten += 8;
                        }
                        if (z2) {
                            if (!this.samplePartitionCountRead) {
                                extractorInput.readFully(this.scratch.data, 0, 1);
                                this.sampleBytesRead++;
                                this.scratch.setPosition(0);
                                this.samplePartitionCount = this.scratch.readUnsignedByte();
                                this.samplePartitionCountRead = true;
                            }
                            int i3 = this.samplePartitionCount * 4;
                            this.scratch.reset(i3);
                            extractorInput.readFully(this.scratch.data, 0, i3);
                            this.sampleBytesRead += i3;
                            short s = (short) ((this.samplePartitionCount / 2) + 1);
                            i2 = (6 * s) + 2;
                            if (this.encryptionSubsampleDataBuffer == null || this.encryptionSubsampleDataBuffer.capacity() < i2) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i2);
                            }
                            this.encryptionSubsampleDataBuffer.position(0);
                            this.encryptionSubsampleDataBuffer.putShort(s);
                            i3 = 0;
                            int i4 = i3;
                            while (i3 < this.samplePartitionCount) {
                                int readUnsignedIntToInt = this.scratch.readUnsignedIntToInt();
                                if (i3 % 2 == 0) {
                                    this.encryptionSubsampleDataBuffer.putShort((short) (readUnsignedIntToInt - i4));
                                } else {
                                    this.encryptionSubsampleDataBuffer.putInt(readUnsignedIntToInt - i4);
                                }
                                i3++;
                                i4 = readUnsignedIntToInt;
                            }
                            i3 = (i - this.sampleBytesRead) - i4;
                            if (this.samplePartitionCount % 2 == 1) {
                                this.encryptionSubsampleDataBuffer.putInt(i3);
                            } else {
                                this.encryptionSubsampleDataBuffer.putShort((short) i3);
                                this.encryptionSubsampleDataBuffer.putInt(0);
                            }
                            this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i2);
                            trackOutput.sampleData(this.encryptionSubsampleData, i2);
                            this.sampleBytesWritten += i2;
                        }
                    }
                } else if (track.sampleStrippedBytes != null) {
                    this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
                }
                this.sampleEncodingHandled = true;
            }
            i += this.sampleStrippedBytes.limit();
            if (CODEC_ID_H264.equals(track.codecId) || CODEC_ID_H265.equals(track.codecId)) {
                byte[] bArr2 = this.nalLength.data;
                bArr2[0] = (byte) 0;
                bArr2[1] = (byte) 0;
                bArr2[2] = (byte) 0;
                int i5 = track.nalUnitLengthFieldLength;
                int i6 = 4 - track.nalUnitLengthFieldLength;
                while (this.sampleBytesRead < i) {
                    if (this.sampleCurrentNalBytesRemaining == 0) {
                        readToTarget(extractorInput, bArr2, i6, i5);
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    } else {
                        this.sampleCurrentNalBytesRemaining -= readToOutput(extractorInput, trackOutput, this.sampleCurrentNalBytesRemaining);
                    }
                }
            } else {
                if (track.trueHdSampleRechunker != null) {
                    if (this.sampleStrippedBytes.limit() != 0) {
                        z = false;
                    }
                    Assertions.checkState(z);
                    track.trueHdSampleRechunker.startSample(extractorInput, this.blockFlags, i);
                }
                while (this.sampleBytesRead < i) {
                    readToOutput(extractorInput, trackOutput, i - this.sampleBytesRead);
                }
            }
            if (CODEC_ID_VORBIS.equals(track.codecId)) {
                this.vorbisNumPageSamples.setPosition(0);
                trackOutput.sampleData(this.vorbisNumPageSamples, 4);
                this.sampleBytesWritten += 4;
            }
        }
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i) throws IOException, InterruptedException {
        int length = bArr.length + i;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.data = Arrays.copyOf(bArr, length + i);
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.data, 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.data, bArr.length, i);
        this.subtitleSample.reset(length);
    }

    private void commitSubtitleSample(Track track, String str, int i, long j, byte[] bArr) {
        setSampleDuration(this.subtitleSample.data, this.blockDurationUs, str, i, j, bArr);
        track.output.sampleData(this.subtitleSample, this.subtitleSample.limit());
        this.sampleBytesWritten += this.subtitleSample.limit();
    }

    private static void setSampleDuration(byte[] bArr, long j, String str, int i, long j2, byte[] bArr2) {
        Object obj;
        Object obj2;
        if (j == C.TIME_UNSET) {
            obj = bArr2;
            obj2 = obj;
        } else {
            long j3 = j - (((long) (((int) (j / 3600000000L)) * 3600)) * 1000000);
            long j4 = j3 - (((long) (((int) (j3 / 60000000)) * 60)) * 1000000);
            int i2 = (int) ((j4 - (((long) ((int) (j4 / 1000000))) * 1000000)) / j2);
            obj2 = Util.getUtf8Bytes(String.format(Locale.US, str, new Object[]{Integer.valueOf(r3), Integer.valueOf(r0), Integer.valueOf(r1), Integer.valueOf(i2)}));
            obj = bArr2;
        }
        System.arraycopy(obj2, 0, bArr, i, obj.length);
    }

    private void readToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int min = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + min, i2 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, min);
        }
        this.sampleBytesRead += i2;
    }

    private int readToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws IOException, InterruptedException {
        int min;
        int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft > 0) {
            min = Math.min(i, bytesLeft);
            trackOutput.sampleData(this.sampleStrippedBytes, min);
        } else {
            min = trackOutput.sampleData(extractorInput, i, false);
        }
        this.sampleBytesRead += min;
        this.sampleBytesWritten += min;
        return min;
    }

    private SeekMap buildSeekMap() {
        if (this.segmentContentPosition == -1 || this.durationUs == C.TIME_UNSET || this.cueTimesUs == null || this.cueTimesUs.size() == 0 || this.cueClusterPositions == null || this.cueClusterPositions.size() != this.cueTimesUs.size()) {
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
            return new Unseekable(this.durationUs);
        }
        int i;
        int size = this.cueTimesUs.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i2 = 0;
        for (i = 0; i < size; i++) {
            jArr3[i] = this.cueTimesUs.get(i);
            jArr[i] = this.segmentContentPosition + this.cueClusterPositions.get(i);
        }
        while (true) {
            i = size - 1;
            if (i2 < i) {
                i = i2 + 1;
                iArr[i2] = (int) (jArr[i] - jArr[i2]);
                jArr2[i2] = jArr3[i] - jArr3[i2];
                i2 = i;
            } else {
                iArr[i] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i]);
                jArr2[i] = this.durationUs - jArr3[i];
                this.cueTimesUs = null;
                this.cueClusterPositions = null;
                return new ChunkIndex(iArr, jArr, jArr2, jArr3);
            }
        }
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        } else if (!this.sentSeekMap || this.seekPositionAfterBuildingCues == -1) {
            return false;
        } else {
            positionHolder.position = this.seekPositionAfterBuildingCues;
            this.seekPositionAfterBuildingCues = -1;
            return true;
        }
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        if (this.timecodeScale == C.TIME_UNSET) {
            throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
        }
        return Util.scaleLargeTimestamp(j, this.timecodeScale, 1000);
    }

    private static boolean isCodecSupported(String str) {
        return CODEC_ID_VP8.equals(str) || CODEC_ID_VP9.equals(str) || CODEC_ID_MPEG2.equals(str) || CODEC_ID_MPEG4_SP.equals(str) || CODEC_ID_MPEG4_ASP.equals(str) || CODEC_ID_MPEG4_AP.equals(str) || CODEC_ID_H264.equals(str) || CODEC_ID_H265.equals(str) || CODEC_ID_FOURCC.equals(str) || CODEC_ID_THEORA.equals(str) || CODEC_ID_OPUS.equals(str) || CODEC_ID_VORBIS.equals(str) || CODEC_ID_AAC.equals(str) || CODEC_ID_MP2.equals(str) || CODEC_ID_MP3.equals(str) || CODEC_ID_AC3.equals(str) || CODEC_ID_E_AC3.equals(str) || CODEC_ID_TRUEHD.equals(str) || CODEC_ID_DTS.equals(str) || CODEC_ID_DTS_EXPRESS.equals(str) || CODEC_ID_DTS_LOSSLESS.equals(str) || CODEC_ID_FLAC.equals(str) || CODEC_ID_ACM.equals(str) || CODEC_ID_PCM_INT_LIT.equals(str) || CODEC_ID_SUBRIP.equals(str) || CODEC_ID_ASS.equals(str) || CODEC_ID_VOBSUB.equals(str) || CODEC_ID_PGS.equals(str) || CODEC_ID_DVBSUB.equals(str);
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length * 2, i)];
    }
}
