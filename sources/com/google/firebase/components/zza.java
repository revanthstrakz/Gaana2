package com.google.firebase.components;

import com.google.firebase.inject.Provider;

abstract class zza implements ComponentContainer {
    zza() {
    }

    public <T> T get(Class<T> cls) {
        Provider provider = getProvider(cls);
        if (provider == null) {
            return null;
        }
        return provider.get();
    }
}
