package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.analytics.HitBuilders.ExceptionBuilder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzco;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;

@VisibleForTesting
public class ExceptionReporter implements UncaughtExceptionHandler {
    private final UncaughtExceptionHandler zzrg;
    private final Tracker zzrh;
    private final Context zzri;
    private ExceptionParser zzrj;
    private GoogleAnalytics zzrk;

    public ExceptionReporter(Tracker tracker, UncaughtExceptionHandler uncaughtExceptionHandler, Context context) {
        if (tracker == null) {
            throw new NullPointerException("tracker cannot be null");
        } else if (context == null) {
            throw new NullPointerException("context cannot be null");
        } else {
            Object obj;
            this.zzrg = uncaughtExceptionHandler;
            this.zzrh = tracker;
            this.zzrj = new StandardExceptionParser(context, new ArrayList());
            this.zzri = context.getApplicationContext();
            String str = "ExceptionReporter created, original handler is ";
            if (uncaughtExceptionHandler == null) {
                obj = "null";
            } else {
                obj = uncaughtExceptionHandler.getClass().getName();
            }
            String valueOf = String.valueOf(obj);
            zzco.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    public ExceptionParser getExceptionParser() {
        return this.zzrj;
    }

    public void setExceptionParser(ExceptionParser exceptionParser) {
        this.zzrj = exceptionParser;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        Object obj = "UncaughtException";
        if (this.zzrj != null) {
            obj = this.zzrj.getDescription(thread != null ? thread.getName() : null, th);
        }
        String str = "Reporting uncaught exception: ";
        String valueOf = String.valueOf(obj);
        zzco.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        this.zzrh.send(new ExceptionBuilder().setDescription(obj).setFatal(true).build());
        if (this.zzrk == null) {
            this.zzrk = GoogleAnalytics.getInstance(this.zzri);
        }
        GoogleAnalytics googleAnalytics = this.zzrk;
        googleAnalytics.dispatchLocalHits();
        googleAnalytics.zzl().zzcc().zzbt();
        if (this.zzrg != null) {
            zzco.v("Passing exception to the original handler");
            this.zzrg.uncaughtException(thread, th);
        }
    }

    /* Access modifiers changed, original: final */
    public final UncaughtExceptionHandler zzp() {
        return this.zzrg;
    }
}
