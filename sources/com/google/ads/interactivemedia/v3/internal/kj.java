package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

public class kj implements fv {
    public boolean a(Class<?> cls) {
        return false;
    }

    public boolean a(fw fwVar) {
        ki kiVar = (ki) fwVar.a().getAnnotation(ki.class);
        return kiVar != null && Arrays.asList(kiVar.b()).contains(fwVar.b());
    }
}
