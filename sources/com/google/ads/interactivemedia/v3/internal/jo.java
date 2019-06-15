package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import com.google.ads.interactivemedia.v3.api.player.AdProgressProvider;
import com.google.ads.interactivemedia.v3.impl.data.b;
import com.google.ads.interactivemedia.v3.impl.data.p;

public interface jo extends AdErrorListener, AdProgressProvider {
    void a();

    void a(b bVar);

    void a(jc.b bVar, p pVar);

    void a(boolean z);

    void b();

    boolean b(jc.b bVar, p pVar);

    void c();

    void d();

    void e();

    boolean f();
}
