package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.c;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GlideException extends Exception {
    private static final StackTraceElement[] a = new StackTraceElement[0];
    private final List<Throwable> b;
    private c c;
    private DataSource d;
    private Class<?> e;

    private static final class a implements Appendable {
        private final Appendable a;
        private boolean b = true;

        @NonNull
        private CharSequence a(@Nullable CharSequence charSequence) {
            return charSequence == null ? "" : charSequence;
        }

        a(Appendable appendable) {
            this.a = appendable;
        }

        public Appendable append(char c) throws IOException {
            boolean z = false;
            if (this.b) {
                this.b = false;
                this.a.append("  ");
            }
            if (c == 10) {
                z = true;
            }
            this.b = z;
            this.a.append(c);
            return this;
        }

        public Appendable append(@Nullable CharSequence charSequence) throws IOException {
            charSequence = a(charSequence);
            return append(charSequence, 0, charSequence.length());
        }

        public Appendable append(@Nullable CharSequence charSequence, int i, int i2) throws IOException {
            charSequence = a(charSequence);
            boolean z = false;
            if (this.b) {
                this.b = false;
                this.a.append("  ");
            }
            if (charSequence.length() > 0 && charSequence.charAt(i2 - 1) == 10) {
                z = true;
            }
            this.b = z;
            this.a.append(charSequence, i, i2);
            return this;
        }
    }

    public Throwable fillInStackTrace() {
        return this;
    }

    public GlideException(String str) {
        this(str, Collections.emptyList());
    }

    public GlideException(String str, Throwable th) {
        this(str, Collections.singletonList(th));
    }

    public GlideException(String str, List<Throwable> list) {
        super(str);
        setStackTrace(a);
        this.b = list;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(c cVar, DataSource dataSource) {
        a(cVar, dataSource, null);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(c cVar, DataSource dataSource, Class<?> cls) {
        this.c = cVar;
        this.d = dataSource;
        this.e = cls;
    }

    public List<Throwable> a() {
        return this.b;
    }

    public List<Throwable> b() {
        List arrayList = new ArrayList();
        a((Throwable) this, arrayList);
        return arrayList;
    }

    public void a(String str) {
        List b = b();
        int size = b.size();
        int i = 0;
        while (i < size) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Root cause (");
            int i2 = i + 1;
            stringBuilder.append(i2);
            stringBuilder.append(" of ");
            stringBuilder.append(size);
            stringBuilder.append(")");
            Log.i(str, stringBuilder.toString(), (Throwable) b.get(i));
            i = i2;
        }
    }

    private void a(Throwable th, List<Throwable> list) {
        if (th instanceof GlideException) {
            for (Throwable a : ((GlideException) th).a()) {
                a(a, (List) list);
            }
            return;
        }
        list.add(th);
    }

    public void printStackTrace() {
        ThrowableExtension.printStackTrace((Throwable) this, System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        a((Appendable) printStream);
    }

    public void printStackTrace(PrintWriter printWriter) {
        a((Appendable) printWriter);
    }

    private void a(Appendable appendable) {
        a((Throwable) this, appendable);
        a(a(), new a(appendable));
    }

    public String getMessage() {
        StringBuilder stringBuilder;
        String stringBuilder2;
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(super.getMessage());
        if (this.e != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(", ");
            stringBuilder.append(this.e);
            stringBuilder2 = stringBuilder.toString();
        } else {
            stringBuilder2 = "";
        }
        stringBuilder3.append(stringBuilder2);
        if (this.d != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(", ");
            stringBuilder.append(this.d);
            stringBuilder2 = stringBuilder.toString();
        } else {
            stringBuilder2 = "";
        }
        stringBuilder3.append(stringBuilder2);
        if (this.c != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(", ");
            stringBuilder.append(this.c);
            stringBuilder2 = stringBuilder.toString();
        } else {
            stringBuilder2 = "";
        }
        stringBuilder3.append(stringBuilder2);
        return stringBuilder3.toString();
    }

    private static void a(Throwable th, Appendable appendable) {
        try {
            appendable.append(th.getClass().toString()).append(": ").append(th.getMessage()).append(10);
        } catch (IOException unused) {
            throw new RuntimeException(th);
        }
    }

    private static void a(List<Throwable> list, Appendable appendable) {
        try {
            b(list, appendable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void b(List<Throwable> list, Appendable appendable) throws IOException {
        int size = list.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            appendable.append("Cause (").append(String.valueOf(i2)).append(" of ").append(String.valueOf(size)).append("): ");
            Throwable th = (Throwable) list.get(i);
            if (th instanceof GlideException) {
                ((GlideException) th).a(appendable);
            } else {
                a(th, appendable);
            }
            i = i2;
        }
    }
}
