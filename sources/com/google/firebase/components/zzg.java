package com.google.firebase.components;

import java.util.HashSet;
import java.util.Set;

final class zzg {
    private final Component<?> zza;
    private final Set<zzg> zzb = new HashSet();
    private final Set<zzg> zzc = new HashSet();

    zzg(Component<?> component) {
        this.zza = component;
    }

    /* Access modifiers changed, original: final */
    public final void zza(zzg zzg) {
        this.zzb.add(zzg);
    }

    /* Access modifiers changed, original: final */
    public final void zzb(zzg zzg) {
        this.zzc.add(zzg);
    }

    /* Access modifiers changed, original: final */
    public final Set<zzg> zza() {
        return this.zzb;
    }

    /* Access modifiers changed, original: final */
    public final void zzc(zzg zzg) {
        this.zzc.remove(zzg);
    }

    /* Access modifiers changed, original: final */
    public final Component<?> zzb() {
        return this.zza;
    }

    /* Access modifiers changed, original: final */
    public final boolean zzc() {
        return this.zzc.isEmpty();
    }

    /* Access modifiers changed, original: final */
    public final boolean zzd() {
        return this.zzb.isEmpty();
    }
}
