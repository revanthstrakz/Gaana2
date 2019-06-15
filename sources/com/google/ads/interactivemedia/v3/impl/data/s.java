package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.ki;

@ki(a = l.class)
public abstract class s {

    public static abstract class a {
        public abstract s build();

        public abstract a volume(float f);

        public a volumePercentage(int i) {
            return volume(((float) i) / 100.0f);
        }
    }

    public abstract float volume();

    public static a builder() {
        return new a();
    }
}
