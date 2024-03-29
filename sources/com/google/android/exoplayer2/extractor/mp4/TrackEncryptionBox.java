package com.google.android.exoplayer2.extractor.mp4;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.TrackOutput.CryptoData;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;

public final class TrackEncryptionBox {
    private static final String TAG = "TrackEncryptionBox";
    public final CryptoData cryptoData;
    public final byte[] defaultInitializationVector;
    public final boolean isEncrypted;
    public final int perSampleIvSize;
    @Nullable
    public final String schemeType;

    public TrackEncryptionBox(boolean z, @Nullable String str, int i, byte[] bArr, int i2, int i3, @Nullable byte[] bArr2) {
        int i4 = 0;
        int i5 = i == 0 ? 1 : 0;
        if (bArr2 == null) {
            i4 = 1;
        }
        Assertions.checkArgument(i4 ^ i5);
        this.isEncrypted = z;
        this.schemeType = str;
        this.perSampleIvSize = i;
        this.defaultInitializationVector = bArr2;
        this.cryptoData = new CryptoData(schemeToCryptoMode(str), bArr, i2, i3);
    }

    private static int schemeToCryptoMode(@Nullable String str) {
        if (str == null) {
            return 1;
        }
        int i = -1;
        int hashCode = str.hashCode();
        if (hashCode != 3046605) {
            if (hashCode != 3046671) {
                if (hashCode != 3049879) {
                    if (hashCode == 3049895 && str.equals(C.CENC_TYPE_cens)) {
                        i = 1;
                    }
                } else if (str.equals(C.CENC_TYPE_cenc)) {
                    i = 0;
                }
            } else if (str.equals(C.CENC_TYPE_cbcs)) {
                i = 3;
            }
        } else if (str.equals(C.CENC_TYPE_cbc1)) {
            i = 2;
        }
        switch (i) {
            case 0:
            case 1:
                return 1;
            case 2:
            case 3:
                return 2;
            default:
                String str2 = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unsupported protection scheme type '");
                stringBuilder.append(str);
                stringBuilder.append("'. Assuming AES-CTR crypto mode.");
                Log.w(str2, stringBuilder.toString());
                return 1;
        }
    }
}
