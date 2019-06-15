package com.google.ads.interactivemedia.v3.internal;

public final class hk implements gq {
    private final gy a;

    public hk(gy gyVar) {
        this.a = gyVar;
    }

    public <T> gp<T> a(fz fzVar, hw<T> hwVar) {
        gs gsVar = (gs) hwVar.a().getAnnotation(gs.class);
        if (gsVar == null) {
            return null;
        }
        return a(this.a, fzVar, hwVar, gsVar);
    }

    /* Access modifiers changed, original: 0000 */
    public gp<?> a(gy gyVar, fz fzVar, hw<?> hwVar, gs gsVar) {
        gp<?> gpVar;
        Object a = gyVar.a(hw.b(gsVar.a())).a();
        if (a instanceof gp) {
            gpVar = (gp) a;
        } else if (a instanceof gq) {
            gpVar = ((gq) a).a(fzVar, hwVar);
        } else {
            boolean z = a instanceof gm;
            if (z || (a instanceof ge)) {
                ge geVar = null;
                gm gmVar = z ? (gm) a : null;
                if (a instanceof ge) {
                    geVar = (ge) a;
                }
                gp<?> hsVar = new hs(gmVar, geVar, fzVar, hwVar, null);
            } else {
                throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference.");
            }
        }
        return gpVar != null ? gpVar.nullSafe() : gpVar;
    }
}
