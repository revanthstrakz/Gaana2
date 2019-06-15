package com.google.ads.interactivemedia.v3.impl.data;

final class l extends s {
    private final float volume;

    static final class a extends com.google.ads.interactivemedia.v3.impl.data.s.a {
        private Float volume;

        a() {
        }

        public com.google.ads.interactivemedia.v3.impl.data.s.a volume(float f) {
            this.volume = Float.valueOf(f);
            return this;
        }

        public s build() {
            String str = "";
            if (this.volume == null) {
                str = String.valueOf(str).concat(" volume");
            }
            if (str.isEmpty()) {
                return new l(this.volume.floatValue());
            }
            String str2 = "Missing required properties:";
            str = String.valueOf(str);
            throw new IllegalStateException(str.length() != 0 ? str2.concat(str) : new String(str2));
        }
    }

    private l(float f) {
        this.volume = f;
    }

    public float volume() {
        return this.volume;
    }

    public String toString() {
        float f = this.volume;
        StringBuilder stringBuilder = new StringBuilder(40);
        stringBuilder.append("VolumeUpdateData{volume=");
        stringBuilder.append(f);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof s)) {
            return false;
        }
        if (Float.floatToIntBits(this.volume) != Float.floatToIntBits(((s) obj).volume())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.volume) ^ 1000003;
    }
}
