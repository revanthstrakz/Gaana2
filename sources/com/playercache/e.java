package com.playercache;

import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.b;
import androidx.work.g;
import androidx.work.g.a;
import androidx.work.k;
import com.constants.Constants;
import com.utilities.d;

public class e {
    private static e a;
    private static final g b = ((g) ((a) ((a) new a(TrackCacheWorker.class).a("CACHE_WORK_TAG")).a(new b(new b.a().a(NetworkType.CONNECTED).a()))).e());

    public static e a() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    public void b() {
        k.a().a("CACHE_WORK_TAG", ExistingWorkPolicy.KEEP, b);
    }

    public boolean c() {
        if (Constants.K == 1 && d.g()) {
            return true;
        }
        return false;
    }
}
