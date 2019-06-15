package com.google.android.now;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.now.INowAuthService.Stub;
import java.io.IOException;
import java.io.InterruptedIOException;

public class NowAuthService {
    static final String AUTH_SERVICE_ACTION = "com.google.android.now.NOW_AUTH_SERVICE";
    private static final Intent AUTH_SERVICE_INTENT = new Intent(AUTH_SERVICE_ACTION).setPackage("com.google.android.googlequicksearchbox");
    static final int ERROR_DISABLED = 3;
    static final int ERROR_HAVE_TOKEN_ALREADY = 2;
    static final int ERROR_TOO_MANY_REQUESTS = 1;
    static final int ERROR_UNAUTHORIZED = 0;
    static final String EXTRA_ACCESS_TOKEN = "access-token";
    static final String EXTRA_AUTH_CODE = "auth-code";
    static final String EXTRA_ERROR = "error";
    static final String EXTRA_NEXT_RETRY_TIMESTAMP_MILLIS = "next-retry-timestamp-millis";
    private static final String TAG = "NowAuthService";
    static boolean sThreadCheckDisabled;

    public static class DisabledException extends Exception {
    }

    public static class HaveTokenAlreadyException extends Exception {
        private final String mAccessToken;

        public HaveTokenAlreadyException(String str) {
            this.mAccessToken = str;
        }

        public String getAccessToken() {
            return this.mAccessToken;
        }
    }

    public static class TooManyRequestsException extends Exception {
        private final long mNextRetryTimestampMillis;

        public TooManyRequestsException(long j) {
            this.mNextRetryTimestampMillis = j;
        }

        public long getNextRetryTimestampMillis() {
            return this.mNextRetryTimestampMillis;
        }
    }

    public static class UnauthorizedException extends Exception {
    }

    public static String getAuthCode(Context context, String str) throws IOException, UnauthorizedException, TooManyRequestsException, HaveTokenAlreadyException, DisabledException {
        checkNotMainThread();
        BlockingServiceConnection blockingServiceConnection = new BlockingServiceConnection();
        if (context.bindService(AUTH_SERVICE_INTENT, blockingServiceConnection, 1)) {
            try {
                Bundle authCode = Stub.asInterface(blockingServiceConnection.getService()).getAuthCode(str, context.getPackageName());
                if (authCode == null) {
                    throw new IOException("Unexpected response from Google Now app");
                } else if (authCode.containsKey("error")) {
                    int i = authCode.getInt("error");
                    switch (i) {
                        case 0:
                            throw new UnauthorizedException();
                        case 1:
                            throw new TooManyRequestsException(authCode.getLong(EXTRA_NEXT_RETRY_TIMESTAMP_MILLIS));
                        case 2:
                            throw new HaveTokenAlreadyException(authCode.getString(EXTRA_ACCESS_TOKEN));
                        case 3:
                            throw new DisabledException();
                        default:
                            str = TAG;
                            StringBuilder stringBuilder = new StringBuilder(26);
                            stringBuilder.append("Unknown error: ");
                            stringBuilder.append(i);
                            Log.e(str, stringBuilder.toString());
                            stringBuilder = new StringBuilder(49);
                            stringBuilder.append("Unexpected error from Google Now app: ");
                            stringBuilder.append(i);
                            throw new IOException(stringBuilder.toString());
                    }
                } else {
                    str = authCode.getString(EXTRA_AUTH_CODE);
                    blockingServiceConnection.unbindServiceIfConnected(context);
                    return str;
                }
            } catch (RemoteException e) {
                throw new IOException("Call to Google Now app failed", e);
            } catch (InterruptedException e2) {
                Log.w(TAG, "Interrupted", e2);
                throw new InterruptedIOException("Interrupted while contacting Google Now app");
            } catch (Throwable th) {
                blockingServiceConnection.unbindServiceIfConnected(context);
            }
        } else {
            throw new IOException("Failed to contact Google Now app");
        }
    }

    private static void checkNotMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper() && !sThreadCheckDisabled) {
            throw new IllegalStateException("Cannot call this API from the main thread");
        }
    }
}
