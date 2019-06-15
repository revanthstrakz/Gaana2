package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.location.zzbh;

@VisibleForTesting
public interface Geofence {
    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1;

    @VisibleForTesting
    public static final class Builder {
        private String zzad = null;
        private int zzae = 0;
        private long zzaf = Long.MIN_VALUE;
        private short zzag = (short) -1;
        private double zzah;
        private double zzai;
        private float zzaj;
        private int zzak = 0;
        private int zzal = -1;

        public final Geofence build() {
            if (this.zzad == null) {
                throw new IllegalArgumentException("Request ID not set.");
            } else if (this.zzae == 0) {
                throw new IllegalArgumentException("Transitions types not set.");
            } else if ((this.zzae & 4) != 0 && this.zzal < 0) {
                throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
            } else if (this.zzaf == Long.MIN_VALUE) {
                throw new IllegalArgumentException("Expiration not set.");
            } else if (this.zzag == (short) -1) {
                throw new IllegalArgumentException("Geofence region not set.");
            } else if (this.zzak >= 0) {
                return new zzbh(this.zzad, this.zzae, (short) 1, this.zzah, this.zzai, this.zzaj, this.zzaf, this.zzak, this.zzal);
            } else {
                throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
            }
        }

        public final Builder setCircularRegion(double d, double d2, float f) {
            this.zzag = (short) 1;
            this.zzah = d;
            this.zzai = d2;
            this.zzaj = f;
            return this;
        }

        public final Builder setExpirationDuration(long j) {
            if (j < 0) {
                this.zzaf = -1;
                return this;
            }
            this.zzaf = SystemClock.elapsedRealtime() + j;
            return this;
        }

        public final Builder setLoiteringDelay(int i) {
            this.zzal = i;
            return this;
        }

        public final Builder setNotificationResponsiveness(int i) {
            this.zzak = i;
            return this;
        }

        public final Builder setRequestId(String str) {
            this.zzad = str;
            return this;
        }

        public final Builder setTransitionTypes(int i) {
            this.zzae = i;
            return this;
        }
    }

    String getRequestId();
}
