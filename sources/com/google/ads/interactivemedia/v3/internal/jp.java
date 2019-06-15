package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.DefaultRenderersFactory;

public class jp {
    private final Context a;
    private final String b;
    private final Uri c;

    public jp(Context context, String str, Uri uri) {
        this.a = context;
        this.b = str;
        this.c = uri;
    }

    public bq[] a(iy iyVar, Handler handler) {
        ev evVar = new ev(65536);
        cf cfVar = new cf(this.c, new ex(this.a, null, this.b, true), evVar, 16777216, handler, iyVar.d(), 0, new cc[0]);
        bi biVar = new bi(this.a, cfVar, bf.a, 1, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, handler, iyVar.e(), 50);
        be beVar = new be((bn) cfVar, bf.a, null, true, handler, iyVar.f(), bs.a(this.a), 3);
        return new bq[]{biVar, beVar};
    }
}
