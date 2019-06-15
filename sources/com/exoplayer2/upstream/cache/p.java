package com.exoplayer2.upstream.cache;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class p extends d {
    private static final Pattern g = Pattern.compile("^(.+)\\.(\\d+)\\.(\\d+)\\.v1\\.exo$", 32);
    private static final Pattern h = Pattern.compile("^(.+)\\.(\\d+)\\.(\\d+)\\.v2\\.exo$", 32);
    private static final Pattern i = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.v3\\.exo$", 32);

    public static File a(File file, int i, long j, long j2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append(".");
        stringBuilder.append(j);
        stringBuilder.append(".");
        stringBuilder.append(j2);
        stringBuilder.append(".v3.exo");
        return new File(file, stringBuilder.toString());
    }

    public static p a(String str, long j) {
        return new p(str, j, -1, C.TIME_UNSET, null);
    }

    public static p b(String str, long j) {
        return new p(str, j, -1, C.TIME_UNSET, null);
    }

    public static p a(String str, long j, long j2) {
        return new p(str, j, j2, C.TIME_UNSET, null);
    }

    @Nullable
    public static p a(File file, h hVar) {
        CharSequence name = file.getName();
        if (!name.endsWith(".v3.exo")) {
            file = b(file, hVar);
            if (file == null) {
                return null;
            }
            name = file.getName();
        }
        File file2 = file;
        Matcher matcher = i.matcher(name);
        if (!matcher.matches()) {
            return null;
        }
        p pVar;
        long length = file2.length();
        String a = hVar.a(Integer.parseInt(matcher.group(1)));
        if (a == null) {
            pVar = null;
        } else {
            p pVar2 = new p(a, Long.parseLong(matcher.group(2)), length, Long.parseLong(matcher.group(3)), file2);
        }
        return pVar;
    }

    @Nullable
    private static File b(File file, h hVar) {
        String name = file.getName();
        Matcher matcher = h.matcher(name);
        if (matcher.matches()) {
            name = Util.unescapeFileName(matcher.group(1));
            if (name == null) {
                return null;
            }
        }
        matcher = g.matcher(name);
        if (!matcher.matches()) {
            return null;
        }
        name = matcher.group(1);
        File a = a(file.getParentFile(), hVar.c(name), Long.parseLong(matcher.group(2)), Long.parseLong(matcher.group(3)));
        if (file.renameTo(a)) {
            return a;
        }
        return null;
    }

    private p(String str, long j, long j2, long j3, @Nullable File file) {
        super(str, j, j2, j3, file);
    }

    public p a(int i) {
        Assertions.checkState(this.d);
        long currentTimeMillis = System.currentTimeMillis();
        return new p(this.a, this.b, this.c, currentTimeMillis, a(this.e.getParentFile(), i, this.b, currentTimeMillis));
    }
}
