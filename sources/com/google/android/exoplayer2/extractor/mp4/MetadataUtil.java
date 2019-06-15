package com.google.android.exoplayer2.extractor.mp4;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

final class MetadataUtil {
    private static final String LANGUAGE_UNDEFINED = "und";
    private static final int PICTURE_TYPE_FRONT_COVER = 3;
    private static final int SHORT_TYPE_ALBUM = Util.getIntegerCodeForString("alb");
    private static final int SHORT_TYPE_ARTIST = Util.getIntegerCodeForString("ART");
    private static final int SHORT_TYPE_COMMENT = Util.getIntegerCodeForString("cmt");
    private static final int SHORT_TYPE_COMPOSER_1 = Util.getIntegerCodeForString("com");
    private static final int SHORT_TYPE_COMPOSER_2 = Util.getIntegerCodeForString("wrt");
    private static final int SHORT_TYPE_ENCODER = Util.getIntegerCodeForString("too");
    private static final int SHORT_TYPE_GENRE = Util.getIntegerCodeForString("gen");
    private static final int SHORT_TYPE_LYRICS = Util.getIntegerCodeForString("lyr");
    private static final int SHORT_TYPE_NAME_1 = Util.getIntegerCodeForString("nam");
    private static final int SHORT_TYPE_NAME_2 = Util.getIntegerCodeForString("trk");
    private static final int SHORT_TYPE_YEAR = Util.getIntegerCodeForString("day");
    private static final String[] STANDARD_GENRES = new String[]{"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};
    private static final String TAG = "MetadataUtil";
    private static final int TYPE_ALBUM_ARTIST = Util.getIntegerCodeForString("aART");
    private static final int TYPE_COMPILATION = Util.getIntegerCodeForString("cpil");
    private static final int TYPE_COVER_ART = Util.getIntegerCodeForString("covr");
    private static final int TYPE_DISK_NUMBER = Util.getIntegerCodeForString("disk");
    private static final int TYPE_GAPLESS_ALBUM = Util.getIntegerCodeForString("pgap");
    private static final int TYPE_GENRE = Util.getIntegerCodeForString("gnre");
    private static final int TYPE_GROUPING = Util.getIntegerCodeForString("grp");
    private static final int TYPE_INTERNAL = Util.getIntegerCodeForString(InternalFrame.ID);
    private static final int TYPE_RATING = Util.getIntegerCodeForString("rtng");
    private static final int TYPE_SORT_ALBUM = Util.getIntegerCodeForString("soal");
    private static final int TYPE_SORT_ALBUM_ARTIST = Util.getIntegerCodeForString("soaa");
    private static final int TYPE_SORT_ARTIST = Util.getIntegerCodeForString("soar");
    private static final int TYPE_SORT_COMPOSER = Util.getIntegerCodeForString("soco");
    private static final int TYPE_SORT_TRACK_NAME = Util.getIntegerCodeForString("sonm");
    private static final int TYPE_TEMPO = Util.getIntegerCodeForString("tmpo");
    private static final int TYPE_TRACK_NUMBER = Util.getIntegerCodeForString("trkn");
    private static final int TYPE_TV_SHOW = Util.getIntegerCodeForString("tvsh");
    private static final int TYPE_TV_SORT_SHOW = Util.getIntegerCodeForString("sosn");

    private MetadataUtil() {
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:172:0x01b5=Splitter:B:172:0x01b5, B:168:0x01ab=Splitter:B:168:0x01ab, B:164:0x018c=Splitter:B:164:0x018c} */
    @android.support.annotation.Nullable
    public static com.google.android.exoplayer2.metadata.Metadata.Entry parseIlstElement(com.google.android.exoplayer2.util.ParsableByteArray r5) {
        /*
        r0 = r5.getPosition();
        r1 = r5.readInt();
        r0 = r0 + r1;
        r1 = r5.readInt();
        r2 = r1 >> 24;
        r2 = r2 & 255;
        r3 = 169; // 0xa9 float:2.37E-43 double:8.35E-322;
        if (r2 == r3) goto L_0x0106;
    L_0x0015:
        r3 = 65533; // 0xfffd float:9.1831E-41 double:3.23776E-319;
        if (r2 != r3) goto L_0x001c;
    L_0x001a:
        goto L_0x0106;
    L_0x001c:
        r2 = TYPE_GENRE;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x0028;
    L_0x0020:
        r1 = parseStandardGenreAttribute(r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x0028:
        r2 = TYPE_DISK_NUMBER;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x0036;
    L_0x002c:
        r2 = "TPOS";
        r1 = parseIndexAndCountAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x0036:
        r2 = TYPE_TRACK_NUMBER;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x0044;
    L_0x003a:
        r2 = "TRCK";
        r1 = parseIndexAndCountAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x0044:
        r2 = TYPE_TEMPO;	 Catch:{ all -> 0x01bf }
        r3 = 0;
        r4 = 1;
        if (r1 != r2) goto L_0x0054;
    L_0x004a:
        r2 = "TBPM";
        r1 = parseUint8Attribute(r1, r2, r5, r4, r3);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x0054:
        r2 = TYPE_COMPILATION;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x0062;
    L_0x0058:
        r2 = "TCMP";
        r1 = parseUint8Attribute(r1, r2, r5, r4, r4);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x0062:
        r2 = TYPE_COVER_ART;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x006e;
    L_0x0066:
        r1 = parseCoverArt(r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x006e:
        r2 = TYPE_ALBUM_ARTIST;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x007c;
    L_0x0072:
        r2 = "TPE2";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x007c:
        r2 = TYPE_SORT_TRACK_NAME;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x008a;
    L_0x0080:
        r2 = "TSOT";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x008a:
        r2 = TYPE_SORT_ALBUM;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x0098;
    L_0x008e:
        r2 = "TSO2";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x0098:
        r2 = TYPE_SORT_ARTIST;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x00a6;
    L_0x009c:
        r2 = "TSOA";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x00a6:
        r2 = TYPE_SORT_ALBUM_ARTIST;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x00b4;
    L_0x00aa:
        r2 = "TSOP";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x00b4:
        r2 = TYPE_SORT_COMPOSER;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x00c2;
    L_0x00b8:
        r2 = "TSOC";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x00c2:
        r2 = TYPE_RATING;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x00d0;
    L_0x00c6:
        r2 = "ITUNESADVISORY";
        r1 = parseUint8Attribute(r1, r2, r5, r3, r3);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x00d0:
        r2 = TYPE_GAPLESS_ALBUM;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x00de;
    L_0x00d4:
        r2 = "ITUNESGAPLESS";
        r1 = parseUint8Attribute(r1, r2, r5, r3, r4);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x00de:
        r2 = TYPE_TV_SORT_SHOW;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x00ec;
    L_0x00e2:
        r2 = "TVSHOWSORT";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x00ec:
        r2 = TYPE_TV_SHOW;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x00fa;
    L_0x00f0:
        r2 = "TVSHOW";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x00fa:
        r2 = TYPE_INTERNAL;	 Catch:{ all -> 0x01bf }
        if (r1 != r2) goto L_0x018c;
    L_0x00fe:
        r1 = parseInternalAttribute(r5, r0);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x0106:
        r2 = 16777215; // 0xffffff float:2.3509886E-38 double:8.2890456E-317;
        r2 = r2 & r1;
        r3 = SHORT_TYPE_COMMENT;	 Catch:{ all -> 0x01bf }
        if (r2 != r3) goto L_0x0116;
    L_0x010e:
        r1 = parseCommentAttribute(r1, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x0116:
        r3 = SHORT_TYPE_NAME_1;	 Catch:{ all -> 0x01bf }
        if (r2 == r3) goto L_0x01b5;
    L_0x011a:
        r3 = SHORT_TYPE_NAME_2;	 Catch:{ all -> 0x01bf }
        if (r2 != r3) goto L_0x0120;
    L_0x011e:
        goto L_0x01b5;
    L_0x0120:
        r3 = SHORT_TYPE_COMPOSER_1;	 Catch:{ all -> 0x01bf }
        if (r2 == r3) goto L_0x01ab;
    L_0x0124:
        r3 = SHORT_TYPE_COMPOSER_2;	 Catch:{ all -> 0x01bf }
        if (r2 != r3) goto L_0x012a;
    L_0x0128:
        goto L_0x01ab;
    L_0x012a:
        r3 = SHORT_TYPE_YEAR;	 Catch:{ all -> 0x01bf }
        if (r2 != r3) goto L_0x0138;
    L_0x012e:
        r2 = "TDRC";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x0138:
        r3 = SHORT_TYPE_ARTIST;	 Catch:{ all -> 0x01bf }
        if (r2 != r3) goto L_0x0146;
    L_0x013c:
        r2 = "TPE1";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x0146:
        r3 = SHORT_TYPE_ENCODER;	 Catch:{ all -> 0x01bf }
        if (r2 != r3) goto L_0x0154;
    L_0x014a:
        r2 = "TSSE";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x0154:
        r3 = SHORT_TYPE_ALBUM;	 Catch:{ all -> 0x01bf }
        if (r2 != r3) goto L_0x0162;
    L_0x0158:
        r2 = "TALB";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x0162:
        r3 = SHORT_TYPE_LYRICS;	 Catch:{ all -> 0x01bf }
        if (r2 != r3) goto L_0x0170;
    L_0x0166:
        r2 = "USLT";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x0170:
        r3 = SHORT_TYPE_GENRE;	 Catch:{ all -> 0x01bf }
        if (r2 != r3) goto L_0x017e;
    L_0x0174:
        r2 = "TCON";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x017e:
        r3 = TYPE_GROUPING;	 Catch:{ all -> 0x01bf }
        if (r2 != r3) goto L_0x018c;
    L_0x0182:
        r2 = "TIT1";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x018c:
        r2 = "MetadataUtil";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01bf }
        r3.<init>();	 Catch:{ all -> 0x01bf }
        r4 = "Skipped unknown metadata entry: ";
        r3.append(r4);	 Catch:{ all -> 0x01bf }
        r1 = com.google.android.exoplayer2.extractor.mp4.Atom.getAtomTypeString(r1);	 Catch:{ all -> 0x01bf }
        r3.append(r1);	 Catch:{ all -> 0x01bf }
        r1 = r3.toString();	 Catch:{ all -> 0x01bf }
        com.google.android.exoplayer2.util.Log.d(r2, r1);	 Catch:{ all -> 0x01bf }
        r1 = 0;
        r5.setPosition(r0);
        return r1;
    L_0x01ab:
        r2 = "TCOM";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x01b5:
        r2 = "TIT2";
        r1 = parseTextAttribute(r1, r2, r5);	 Catch:{ all -> 0x01bf }
        r5.setPosition(r0);
        return r1;
    L_0x01bf:
        r1 = move-exception;
        r5.setPosition(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.MetadataUtil.parseIlstElement(com.google.android.exoplayer2.util.ParsableByteArray):com.google.android.exoplayer2.metadata.Metadata$Entry");
    }

    @Nullable
    private static TextInformationFrame parseTextAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            return new TextInformationFrame(str, null, parsableByteArray.readNullTerminatedString(readInt - 16));
        }
        str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to parse text attribute: ");
        stringBuilder.append(Atom.getAtomTypeString(i));
        Log.w(str, stringBuilder.toString());
        return null;
    }

    @Nullable
    private static CommentFrame parseCommentAttribute(int i, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            String readNullTerminatedString = parsableByteArray.readNullTerminatedString(readInt - 16);
            return new CommentFrame("und", readNullTerminatedString, readNullTerminatedString);
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to parse comment attribute: ");
        stringBuilder.append(Atom.getAtomTypeString(i));
        Log.w(str, stringBuilder.toString());
        return null;
    }

    @Nullable
    private static Id3Frame parseUint8Attribute(int i, String str, ParsableByteArray parsableByteArray, boolean z, boolean z2) {
        int parseUint8AttributeValue = parseUint8AttributeValue(parsableByteArray);
        if (z2) {
            parseUint8AttributeValue = Math.min(1, parseUint8AttributeValue);
        }
        if (parseUint8AttributeValue >= 0) {
            Id3Frame textInformationFrame;
            if (z) {
                textInformationFrame = new TextInformationFrame(str, null, Integer.toString(parseUint8AttributeValue));
            } else {
                textInformationFrame = new CommentFrame("und", str, Integer.toString(parseUint8AttributeValue));
            }
            return textInformationFrame;
        }
        str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Failed to parse uint8 attribute: ");
        stringBuilder.append(Atom.getAtomTypeString(i));
        Log.w(str, stringBuilder.toString());
        return null;
    }

    @Nullable
    private static TextInformationFrame parseIndexAndCountAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data && readInt >= 22) {
            parsableByteArray.skipBytes(10);
            readInt = parsableByteArray.readUnsignedShort();
            if (readInt > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(readInt);
                String stringBuilder2 = stringBuilder.toString();
                int readUnsignedShort = parsableByteArray.readUnsignedShort();
                if (readUnsignedShort > 0) {
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(stringBuilder2);
                    stringBuilder3.append("/");
                    stringBuilder3.append(readUnsignedShort);
                    stringBuilder2 = stringBuilder3.toString();
                }
                return new TextInformationFrame(str, null, stringBuilder2);
            }
        }
        str = TAG;
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Failed to parse index/count attribute: ");
        stringBuilder4.append(Atom.getAtomTypeString(i));
        Log.w(str, stringBuilder4.toString());
        return null;
    }

    @Nullable
    private static TextInformationFrame parseStandardGenreAttribute(ParsableByteArray parsableByteArray) {
        int parseUint8AttributeValue = parseUint8AttributeValue(parsableByteArray);
        String str = (parseUint8AttributeValue <= 0 || parseUint8AttributeValue > STANDARD_GENRES.length) ? null : STANDARD_GENRES[parseUint8AttributeValue - 1];
        if (str != null) {
            return new TextInformationFrame("TCON", null, str);
        }
        Log.w(TAG, "Failed to parse standard genre code");
        return null;
    }

    @Nullable
    private static ApicFrame parseCoverArt(ParsableByteArray parsableByteArray) {
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            int parseFullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
            String str = parseFullAtomFlags == 13 ? "image/jpeg" : parseFullAtomFlags == 14 ? "image/png" : null;
            if (str == null) {
                String str2 = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unrecognized cover art flags: ");
                stringBuilder.append(parseFullAtomFlags);
                Log.w(str2, stringBuilder.toString());
                return null;
            }
            parsableByteArray.skipBytes(4);
            byte[] bArr = new byte[(readInt - 16)];
            parsableByteArray.readBytes(bArr, 0, bArr.length);
            return new ApicFrame(str, null, 3, bArr);
        }
        Log.w(TAG, "Failed to parse cover art attribute");
        return null;
    }

    @Nullable
    private static Id3Frame parseInternalAttribute(ParsableByteArray parsableByteArray, int i) {
        int i2 = -1;
        int i3 = i2;
        String str = null;
        String str2 = str;
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            if (readInt2 == Atom.TYPE_mean) {
                str = parsableByteArray.readNullTerminatedString(readInt - 12);
            } else if (readInt2 == Atom.TYPE_name) {
                str2 = parsableByteArray.readNullTerminatedString(readInt - 12);
            } else {
                if (readInt2 == Atom.TYPE_data) {
                    i2 = position;
                    i3 = readInt;
                }
                parsableByteArray.skipBytes(readInt - 12);
            }
        }
        if (str == null || str2 == null || i2 == -1) {
            return null;
        }
        parsableByteArray.setPosition(i2);
        parsableByteArray.skipBytes(16);
        return new InternalFrame(str, str2, parsableByteArray.readNullTerminatedString(i3 - 16));
    }

    private static int parseUint8AttributeValue(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            return parsableByteArray.readUnsignedByte();
        }
        Log.w(TAG, "Failed to parse uint8 attribute value");
        return -1;
    }
}
