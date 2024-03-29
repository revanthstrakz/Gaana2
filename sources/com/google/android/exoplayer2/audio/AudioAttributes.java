package com.google.android.exoplayer2.audio;

import android.annotation.TargetApi;
import android.support.annotation.Nullable;

public final class AudioAttributes {
    public static final AudioAttributes DEFAULT = new Builder().build();
    @Nullable
    private android.media.AudioAttributes audioAttributesV21;
    public final int contentType;
    public final int flags;
    public final int usage;

    public static final class Builder {
        private int contentType = 0;
        private int flags = 0;
        private int usage = 1;

        public Builder setContentType(int i) {
            this.contentType = i;
            return this;
        }

        public Builder setFlags(int i) {
            this.flags = i;
            return this;
        }

        public Builder setUsage(int i) {
            this.usage = i;
            return this;
        }

        public AudioAttributes build() {
            return new AudioAttributes(this.contentType, this.flags, this.usage);
        }
    }

    private AudioAttributes(int i, int i2, int i3) {
        this.contentType = i;
        this.flags = i2;
        this.usage = i3;
    }

    @TargetApi(21)
    public android.media.AudioAttributes getAudioAttributesV21() {
        if (this.audioAttributesV21 == null) {
            this.audioAttributesV21 = new android.media.AudioAttributes.Builder().setContentType(this.contentType).setFlags(this.flags).setUsage(this.usage).build();
        }
        return this.audioAttributesV21;
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AudioAttributes audioAttributes = (AudioAttributes) obj;
        if (!(this.contentType == audioAttributes.contentType && this.flags == audioAttributes.flags && this.usage == audioAttributes.usage)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (31 * (((527 + this.contentType) * 31) + this.flags)) + this.usage;
    }
}
