package com.facebook.accountkit.internal;

import android.support.annotation.NonNull;
import android.util.Log;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.LoggingBehavior;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

final class ConsoleLogger {
    private static final String LOG_TAG_BASE = "AccountKitSDK.";
    private static final String NEWLINE = "\n";
    private final LoggingBehavior behavior;
    private StringBuilder contents = new StringBuilder();
    private final String tag;

    public static void log(LoggingBehavior loggingBehavior, String str, String str2) {
        log(loggingBehavior, 3, str, str2);
    }

    public static void log(LoggingBehavior loggingBehavior, String str, String str2, Object... objArr) {
        log(loggingBehavior, 3, str, String.format(str2, objArr));
    }

    public static void log(LoggingBehavior loggingBehavior, int i, String str, String str2, Object... objArr) {
        log(loggingBehavior, i, str, String.format(str2, objArr));
    }

    private static void log(LoggingBehavior loggingBehavior, int i, String str, String str2) {
        if (AccountKit.getLoggingBehaviors().isEnabled(loggingBehavior)) {
            if (!str.startsWith(LOG_TAG_BASE)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(LOG_TAG_BASE);
                stringBuilder.append(str);
                str = stringBuilder.toString();
            }
            Log.println(i, str, str2);
            if (loggingBehavior == LoggingBehavior.DEVELOPER_ERRORS) {
                ThrowableExtension.printStackTrace(new Exception());
            }
        }
    }

    ConsoleLogger(LoggingBehavior loggingBehavior, @NonNull String str) {
        this.behavior = loggingBehavior;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LOG_TAG_BASE);
        stringBuilder.append(str);
        this.tag = stringBuilder.toString();
    }

    public void log() {
        log(this.behavior, 3, this.tag, this.contents.toString());
        this.contents = new StringBuilder();
    }

    /* Access modifiers changed, original: 0000 */
    public void appendLine(String str) {
        if (shouldLog()) {
            StringBuilder stringBuilder = this.contents;
            stringBuilder.append(str);
            stringBuilder.append(NEWLINE);
        }
    }

    private void append(String str, Object... objArr) {
        if (shouldLog()) {
            this.contents.append(String.format(str, objArr));
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void appendKeyValue(String str, Object obj) {
        append("  %s:\t%s\n", str, obj);
    }

    private boolean shouldLog() {
        return AccountKit.getLoggingBehaviors().isEnabled(this.behavior);
    }
}
