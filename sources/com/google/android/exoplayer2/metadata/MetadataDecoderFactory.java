package com.google.android.exoplayer2.metadata;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.MimeTypes;

public interface MetadataDecoderFactory {
    public static final MetadataDecoderFactory DEFAULT = new MetadataDecoderFactory() {
        public boolean supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return MimeTypes.APPLICATION_ID3.equals(str) || MimeTypes.APPLICATION_EMSG.equals(str) || MimeTypes.APPLICATION_SCTE35.equals(str);
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x004c  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0040  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x004c  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0040  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x004c  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0040  */
        public com.google.android.exoplayer2.metadata.MetadataDecoder createDecoder(com.google.android.exoplayer2.Format r3) {
            /*
            r2 = this;
            r3 = r3.sampleMimeType;
            r0 = r3.hashCode();
            r1 = -1248341703; // 0xffffffffb597d139 float:-1.1311269E-6 double:NaN;
            if (r0 == r1) goto L_0x002a;
        L_0x000b:
            r1 = 1154383568; // 0x44ce7ed0 float:1651.9629 double:5.70341263E-315;
            if (r0 == r1) goto L_0x0020;
        L_0x0010:
            r1 = 1652648887; // 0x62816bb7 float:1.1936958E21 double:8.165170397E-315;
            if (r0 == r1) goto L_0x0016;
        L_0x0015:
            goto L_0x0034;
        L_0x0016:
            r0 = "application/x-scte35";
            r3 = r3.equals(r0);
            if (r3 == 0) goto L_0x0034;
        L_0x001e:
            r3 = 2;
            goto L_0x0035;
        L_0x0020:
            r0 = "application/x-emsg";
            r3 = r3.equals(r0);
            if (r3 == 0) goto L_0x0034;
        L_0x0028:
            r3 = 1;
            goto L_0x0035;
        L_0x002a:
            r0 = "application/id3";
            r3 = r3.equals(r0);
            if (r3 == 0) goto L_0x0034;
        L_0x0032:
            r3 = 0;
            goto L_0x0035;
        L_0x0034:
            r3 = -1;
        L_0x0035:
            switch(r3) {
                case 0: goto L_0x004c;
                case 1: goto L_0x0046;
                case 2: goto L_0x0040;
                default: goto L_0x0038;
            };
        L_0x0038:
            r3 = new java.lang.IllegalArgumentException;
            r0 = "Attempted to create decoder for unsupported format";
            r3.<init>(r0);
            throw r3;
        L_0x0040:
            r3 = new com.google.android.exoplayer2.metadata.scte35.SpliceInfoDecoder;
            r3.<init>();
            return r3;
        L_0x0046:
            r3 = new com.google.android.exoplayer2.metadata.emsg.EventMessageDecoder;
            r3.<init>();
            return r3;
        L_0x004c:
            r3 = new com.google.android.exoplayer2.metadata.id3.Id3Decoder;
            r3.<init>();
            return r3;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.MetadataDecoderFactory$AnonymousClass1.createDecoder(com.google.android.exoplayer2.Format):com.google.android.exoplayer2.metadata.MetadataDecoder");
        }
    };

    MetadataDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
