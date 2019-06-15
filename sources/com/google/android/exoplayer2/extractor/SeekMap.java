package com.google.android.exoplayer2.extractor;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.Assertions;

public interface SeekMap {

    public static final class SeekPoints {
        public final SeekPoint first;
        public final SeekPoint second;

        public SeekPoints(SeekPoint seekPoint) {
            this(seekPoint, seekPoint);
        }

        public SeekPoints(SeekPoint seekPoint, SeekPoint seekPoint2) {
            this.first = (SeekPoint) Assertions.checkNotNull(seekPoint);
            this.second = (SeekPoint) Assertions.checkNotNull(seekPoint2);
        }

        public String toString() {
            String str;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(this.first);
            if (this.first.equals(this.second)) {
                str = "";
            } else {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", ");
                stringBuilder2.append(this.second);
                str = stringBuilder2.toString();
            }
            stringBuilder.append(str);
            stringBuilder.append("]");
            return stringBuilder.toString();
        }

        public boolean equals(@Nullable Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SeekPoints seekPoints = (SeekPoints) obj;
            if (!(this.first.equals(seekPoints.first) && this.second.equals(seekPoints.second))) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return (31 * this.first.hashCode()) + this.second.hashCode();
        }
    }

    public static final class Unseekable implements SeekMap {
        private final long durationUs;
        private final SeekPoints startSeekPoints;

        public boolean isSeekable() {
            return false;
        }

        public Unseekable(long j) {
            this(j, 0);
        }

        public Unseekable(long j, long j2) {
            this.durationUs = j;
            this.startSeekPoints = new SeekPoints(j2 == 0 ? SeekPoint.START : new SeekPoint(0, j2));
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public SeekPoints getSeekPoints(long j) {
            return this.startSeekPoints;
        }
    }

    long getDurationUs();

    SeekPoints getSeekPoints(long j);

    boolean isSeekable();
}
