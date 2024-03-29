package com.google.android.exoplayer2.text.pgs;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.zip.Inflater;

public final class PgsDecoder extends SimpleSubtitleDecoder {
    private static final byte INFLATE_HEADER = (byte) 120;
    private static final int SECTION_TYPE_BITMAP_PICTURE = 21;
    private static final int SECTION_TYPE_END = 128;
    private static final int SECTION_TYPE_IDENTIFIER = 22;
    private static final int SECTION_TYPE_PALETTE = 20;
    private final ParsableByteArray buffer = new ParsableByteArray();
    private final CueBuilder cueBuilder = new CueBuilder();
    private final ParsableByteArray inflatedBuffer = new ParsableByteArray();
    private Inflater inflater;

    private static final class CueBuilder {
        private final ParsableByteArray bitmapData = new ParsableByteArray();
        private int bitmapHeight;
        private int bitmapWidth;
        private int bitmapX;
        private int bitmapY;
        private final int[] colors = new int[256];
        private boolean colorsSet;
        private int planeHeight;
        private int planeWidth;

        private void parsePaletteSection(ParsableByteArray parsableByteArray, int i) {
            if (i % 5 == 2) {
                parsableByteArray.skipBytes(2);
                Arrays.fill(this.colors, 0);
                int i2 = i / 5;
                int i3 = 0;
                while (i3 < i2) {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    int readUnsignedByte4 = parsableByteArray.readUnsignedByte();
                    int readUnsignedByte5 = parsableByteArray.readUnsignedByte();
                    double d = (double) readUnsignedByte2;
                    double d2 = (double) (readUnsignedByte3 - 128);
                    int i4 = (int) ((1.402d * d2) + d);
                    int i5 = readUnsignedByte;
                    double d3 = (double) (readUnsignedByte4 - 128);
                    readUnsignedByte2 = (int) ((d - (0.34414d * d3)) - (0.71414d * d2));
                    int i6 = (int) (d + (1.772d * d3));
                    this.colors[i5] = Util.constrainValue(i6, 0, 255) | ((Util.constrainValue(readUnsignedByte2, 0, 255) << 8) | ((readUnsignedByte5 << 24) | (Util.constrainValue(i4, 0, 255) << 16)));
                    i3++;
                    i6 = 0;
                }
                this.colorsSet = true;
            }
        }

        private void parseBitmapSection(ParsableByteArray parsableByteArray, int i) {
            if (i >= 4) {
                int readUnsignedInt24;
                parsableByteArray.skipBytes(3);
                i -= 4;
                if (((128 & parsableByteArray.readUnsignedByte()) != 0 ? 1 : null) != null) {
                    if (i >= 7) {
                        readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
                        if (readUnsignedInt24 >= 4) {
                            this.bitmapWidth = parsableByteArray.readUnsignedShort();
                            this.bitmapHeight = parsableByteArray.readUnsignedShort();
                            this.bitmapData.reset(readUnsignedInt24 - 4);
                            i -= 7;
                        } else {
                            return;
                        }
                    }
                    return;
                }
                int position = this.bitmapData.getPosition();
                readUnsignedInt24 = this.bitmapData.limit();
                if (position < readUnsignedInt24 && i > 0) {
                    i = Math.min(i, readUnsignedInt24 - position);
                    parsableByteArray.readBytes(this.bitmapData.data, position, i);
                    this.bitmapData.setPosition(position + i);
                }
            }
        }

        private void parseIdentifierSection(ParsableByteArray parsableByteArray, int i) {
            if (i >= 19) {
                this.planeWidth = parsableByteArray.readUnsignedShort();
                this.planeHeight = parsableByteArray.readUnsignedShort();
                parsableByteArray.skipBytes(11);
                this.bitmapX = parsableByteArray.readUnsignedShort();
                this.bitmapY = parsableByteArray.readUnsignedShort();
            }
        }

        public Cue build() {
            if (this.planeWidth == 0 || this.planeHeight == 0 || this.bitmapWidth == 0 || this.bitmapHeight == 0 || this.bitmapData.limit() == 0 || this.bitmapData.getPosition() != this.bitmapData.limit() || !this.colorsSet) {
                return null;
            }
            this.bitmapData.setPosition(0);
            int[] iArr = new int[(this.bitmapWidth * this.bitmapHeight)];
            int i = 0;
            while (i < iArr.length) {
                int i2;
                int readUnsignedByte = this.bitmapData.readUnsignedByte();
                if (readUnsignedByte != 0) {
                    i2 = i + 1;
                    iArr[i] = this.colors[readUnsignedByte];
                } else {
                    readUnsignedByte = this.bitmapData.readUnsignedByte();
                    if (readUnsignedByte != 0) {
                        if ((readUnsignedByte & 64) == 0) {
                            i2 = readUnsignedByte & 63;
                        } else {
                            i2 = ((readUnsignedByte & 63) << 8) | this.bitmapData.readUnsignedByte();
                        }
                        if ((readUnsignedByte & 128) == 0) {
                            readUnsignedByte = 0;
                        } else {
                            readUnsignedByte = this.colors[this.bitmapData.readUnsignedByte()];
                        }
                        i2 += i;
                        Arrays.fill(iArr, i, i2, readUnsignedByte);
                    }
                }
                i = i2;
            }
            return new Cue(Bitmap.createBitmap(iArr, this.bitmapWidth, this.bitmapHeight, Config.ARGB_8888), ((float) this.bitmapX) / ((float) this.planeWidth), 0, ((float) this.bitmapY) / ((float) this.planeHeight), 0, ((float) this.bitmapWidth) / ((float) this.planeWidth), ((float) this.bitmapHeight) / ((float) this.planeHeight));
        }

        public void reset() {
            this.planeWidth = 0;
            this.planeHeight = 0;
            this.bitmapX = 0;
            this.bitmapY = 0;
            this.bitmapWidth = 0;
            this.bitmapHeight = 0;
            this.bitmapData.reset(0);
            this.colorsSet = false;
        }
    }

    public PgsDecoder() {
        super("PgsDecoder");
    }

    /* Access modifiers changed, original: protected */
    public Subtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.buffer.reset(bArr, i);
        maybeInflateData(this.buffer);
        this.cueBuilder.reset();
        ArrayList arrayList = new ArrayList();
        while (this.buffer.bytesLeft() >= 3) {
            Cue readNextSection = readNextSection(this.buffer, this.cueBuilder);
            if (readNextSection != null) {
                arrayList.add(readNextSection);
            }
        }
        return new PgsSubtitle(Collections.unmodifiableList(arrayList));
    }

    private void maybeInflateData(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() > 0 && parsableByteArray.peekUnsignedByte() == 120) {
            if (this.inflater == null) {
                this.inflater = new Inflater();
            }
            if (Util.inflate(parsableByteArray, this.inflatedBuffer, this.inflater)) {
                parsableByteArray.reset(this.inflatedBuffer.data, this.inflatedBuffer.limit());
            }
        }
    }

    private static Cue readNextSection(ParsableByteArray parsableByteArray, CueBuilder cueBuilder) {
        int limit = parsableByteArray.limit();
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int position = parsableByteArray.getPosition() + readUnsignedShort;
        Cue cue = null;
        if (position > limit) {
            parsableByteArray.setPosition(limit);
            return null;
        }
        if (readUnsignedByte != 128) {
            switch (readUnsignedByte) {
                case 20:
                    cueBuilder.parsePaletteSection(parsableByteArray, readUnsignedShort);
                    break;
                case 21:
                    cueBuilder.parseBitmapSection(parsableByteArray, readUnsignedShort);
                    break;
                case 22:
                    cueBuilder.parseIdentifierSection(parsableByteArray, readUnsignedShort);
                    break;
            }
        }
        cue = cueBuilder.build();
        cueBuilder.reset();
        parsableByteArray.setPosition(position);
        return cue;
    }
}
