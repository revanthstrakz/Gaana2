package com.exoplayer2.upstream.cache;

import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.constants.Constants;
import com.exoplayer2.TrackSpan;
import com.gaana.application.GaanaApplication;
import java.io.File;
import java.util.Comparator;
import java.util.TreeSet;

public final class m implements b, Comparator<d> {
    private static q c;
    private final long a;
    private final TreeSet<d> b = new TreeSet(this);
    private long d;
    private long e;

    class a implements Comparator<TrackSpan> {
        a() {
        }

        /* renamed from: a */
        public int compare(TrackSpan trackSpan, TrackSpan trackSpan2) {
            return trackSpan.b() < trackSpan2.b() ? -1 : 1;
        }
    }

    public void a() {
    }

    public m(long j) {
        this.a = j;
        Constants.ef = new TreeSet(new a());
    }

    public void a(Cache cache, String str, long j, long j2) {
        a(cache, j2);
    }

    public void a(Cache cache, d dVar) {
        this.b.add(dVar);
        this.d += dVar.c;
        a(cache, 0);
    }

    public void b(Cache cache, d dVar) {
        this.b.remove(dVar);
        this.d -= dVar.c;
    }

    public void a(Cache cache, d dVar, d dVar2) {
        b(cache, dVar);
        a(cache, dVar2);
    }

    /* renamed from: a */
    public int compare(d dVar, d dVar2) {
        if (dVar.f - dVar2.f == 0) {
            return dVar.compareTo(dVar2);
        }
        return dVar.f < dVar2.f ? -1 : 1;
    }

    private void a(Cache cache, long j) {
        while (this.e + j > this.a) {
            try {
                Log.v("media_cache", "remove Track - required");
                b();
            } catch (Exception unused) {
                d();
            }
        }
    }

    public void a(File file) {
        if (c == null) {
            c = new q(file);
            c.a();
        }
    }

    public void a(TrackSpan trackSpan) {
        c.b(trackSpan);
        c();
        c.b();
    }

    private void c() {
        File[] listFiles = new File(ContextCompat.getExternalFilesDirs(GaanaApplication.getContext(), null)[0].getAbsolutePath(), "media_cache").listFiles();
        long j = 0;
        if (listFiles != null) {
            long j2 = 0;
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    File[] listFiles2 = file.listFiles();
                    if (listFiles2 != null) {
                        long j3 = j2;
                        int i = 0;
                        while (i < listFiles2.length) {
                            i++;
                            j3 += listFiles2[i].length();
                        }
                        j2 = j3;
                    }
                }
            }
            j = j2;
        }
        this.e = j;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("calculateCacheSize : ");
        stringBuilder.append(this.e);
        Log.v("media_cache", stringBuilder.toString());
    }

    public void b() {
        long d = c.d();
        if (d != -1) {
            c.b();
            this.d -= d;
            this.e -= d;
        } else {
            d();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Track removed - size freed : ");
        stringBuilder.append(d);
        Log.v("media_cache", stringBuilder.toString());
        if (this.e < 0) {
            this.e = 0;
        }
    }

    private void d() {
        c.c();
        if (!Constants.ef.isEmpty()) {
            TrackSpan trackSpan = (TrackSpan) Constants.ef.last();
            Constants.ef = new TreeSet(new a());
            c.a(trackSpan);
        }
        c.b();
        this.d = 0;
        this.e = 0;
    }
}
