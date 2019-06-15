package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException;
import com.google.android.gms.wallet.WalletConstants;
import java.io.IOException;

public class DefaultLoadErrorHandlingPolicy implements LoadErrorHandlingPolicy {
    private static final int DEFAULT_BEHAVIOR_MIN_LOADABLE_RETRY_COUNT = -1;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT = 3;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT_PROGRESSIVE_LIVE = 6;
    public static final long DEFAULT_TRACK_BLACKLIST_MS = 60000;
    private final int minimumLoadableRetryCount;

    public DefaultLoadErrorHandlingPolicy() {
        this(-1);
    }

    public DefaultLoadErrorHandlingPolicy(int i) {
        this.minimumLoadableRetryCount = i;
    }

    public long getBlacklistDurationMsFor(int i, long j, IOException iOException, int i2) {
        boolean z = iOException instanceof InvalidResponseCodeException;
        j = C.TIME_UNSET;
        if (!z) {
            return C.TIME_UNSET;
        }
        i = ((InvalidResponseCodeException) iOException).responseCode;
        if (i == 404 || i == WalletConstants.ERROR_CODE_INVALID_TRANSACTION) {
            j = 60000;
        }
        return j;
    }

    public long getRetryDelayMsFor(int i, long j, IOException iOException, int i2) {
        if (iOException instanceof ParserException) {
            return C.TIME_UNSET;
        }
        return (long) Math.min((i2 - 1) * 1000, 5000);
    }

    public int getMinimumLoadableRetryCount(int i) {
        if (this.minimumLoadableRetryCount != -1) {
            return this.minimumLoadableRetryCount;
        }
        return i == 7 ? 6 : 3;
    }
}
