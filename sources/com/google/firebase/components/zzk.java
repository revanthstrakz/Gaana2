package com.google.firebase.components;

import com.google.firebase.inject.Provider;

final /* synthetic */ class zzk implements Provider {
    private final ComponentFactory zza;
    private final ComponentContainer zzb;

    private zzk(ComponentFactory componentFactory, ComponentContainer componentContainer) {
        this.zza = componentFactory;
        this.zzb = componentContainer;
    }

    public static Provider zza(ComponentFactory componentFactory, ComponentContainer componentContainer) {
        return new zzk(componentFactory, componentContainer);
    }

    public final Object get() {
        return this.zza.create(this.zzb);
    }
}
