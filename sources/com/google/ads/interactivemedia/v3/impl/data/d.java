package com.google.ads.interactivemedia.v3.impl.data;

final class d extends a {
    private final String appState;
    private final String eventId;
    private final long nativeTime;
    private final boolean nativeViewAttached;
    private final m nativeViewBounds;
    private final boolean nativeViewHidden;
    private final m nativeViewVisibleBounds;
    private final double nativeVolume;
    private final String queryId;
    private final String vastEvent;

    static final class a implements com.google.ads.interactivemedia.v3.impl.data.a.a {
        private String appState;
        private String eventId;
        private Long nativeTime;
        private Boolean nativeViewAttached;
        private m nativeViewBounds;
        private Boolean nativeViewHidden;
        private m nativeViewVisibleBounds;
        private Double nativeVolume;
        private String queryId;
        private String vastEvent;

        a() {
        }

        public com.google.ads.interactivemedia.v3.impl.data.a.a queryId(String str) {
            if (str == null) {
                throw new NullPointerException("Null queryId");
            }
            this.queryId = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.a.a eventId(String str) {
            if (str == null) {
                throw new NullPointerException("Null eventId");
            }
            this.eventId = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.a.a vastEvent(String str) {
            if (str == null) {
                throw new NullPointerException("Null vastEvent");
            }
            this.vastEvent = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.a.a appState(String str) {
            if (str == null) {
                throw new NullPointerException("Null appState");
            }
            this.appState = str;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.a.a nativeTime(long j) {
            this.nativeTime = Long.valueOf(j);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.a.a nativeVolume(double d) {
            this.nativeVolume = Double.valueOf(d);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.a.a nativeViewAttached(boolean z) {
            this.nativeViewAttached = Boolean.valueOf(z);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.a.a nativeViewHidden(boolean z) {
            this.nativeViewHidden = Boolean.valueOf(z);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.a.a nativeViewBounds(m mVar) {
            if (mVar == null) {
                throw new NullPointerException("Null nativeViewBounds");
            }
            this.nativeViewBounds = mVar;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.a.a nativeViewVisibleBounds(m mVar) {
            if (mVar == null) {
                throw new NullPointerException("Null nativeViewVisibleBounds");
            }
            this.nativeViewVisibleBounds = mVar;
            return this;
        }

        public a build() {
            String str = "";
            if (this.queryId == null) {
                str = String.valueOf(str).concat(" queryId");
            }
            if (this.eventId == null) {
                str = String.valueOf(str).concat(" eventId");
            }
            if (this.vastEvent == null) {
                str = String.valueOf(str).concat(" vastEvent");
            }
            if (this.appState == null) {
                str = String.valueOf(str).concat(" appState");
            }
            if (this.nativeTime == null) {
                str = String.valueOf(str).concat(" nativeTime");
            }
            if (this.nativeVolume == null) {
                str = String.valueOf(str).concat(" nativeVolume");
            }
            if (this.nativeViewAttached == null) {
                str = String.valueOf(str).concat(" nativeViewAttached");
            }
            if (this.nativeViewHidden == null) {
                str = String.valueOf(str).concat(" nativeViewHidden");
            }
            if (this.nativeViewBounds == null) {
                str = String.valueOf(str).concat(" nativeViewBounds");
            }
            if (this.nativeViewVisibleBounds == null) {
                str = String.valueOf(str).concat(" nativeViewVisibleBounds");
            }
            if (str.isEmpty()) {
                return new d(this.queryId, this.eventId, this.vastEvent, this.appState, this.nativeTime.longValue(), this.nativeVolume.doubleValue(), this.nativeViewAttached.booleanValue(), this.nativeViewHidden.booleanValue(), this.nativeViewBounds, this.nativeViewVisibleBounds);
            }
            String str2 = "Missing required properties:";
            str = String.valueOf(str);
            throw new IllegalStateException(str.length() != 0 ? str2.concat(str) : new String(str2));
        }
    }

    private d(String str, String str2, String str3, String str4, long j, double d, boolean z, boolean z2, m mVar, m mVar2) {
        this.queryId = str;
        this.eventId = str2;
        this.vastEvent = str3;
        this.appState = str4;
        this.nativeTime = j;
        this.nativeVolume = d;
        this.nativeViewAttached = z;
        this.nativeViewHidden = z2;
        this.nativeViewBounds = mVar;
        this.nativeViewVisibleBounds = mVar2;
    }

    public String queryId() {
        return this.queryId;
    }

    public String eventId() {
        return this.eventId;
    }

    public String vastEvent() {
        return this.vastEvent;
    }

    public String appState() {
        return this.appState;
    }

    public long nativeTime() {
        return this.nativeTime;
    }

    public double nativeVolume() {
        return this.nativeVolume;
    }

    public boolean nativeViewAttached() {
        return this.nativeViewAttached;
    }

    public boolean nativeViewHidden() {
        return this.nativeViewHidden;
    }

    public m nativeViewBounds() {
        return this.nativeViewBounds;
    }

    public m nativeViewVisibleBounds() {
        return this.nativeViewVisibleBounds;
    }

    public String toString() {
        String str = this.queryId;
        String str2 = this.eventId;
        String str3 = this.vastEvent;
        String str4 = this.appState;
        long j = this.nativeTime;
        double d = this.nativeVolume;
        boolean z = this.nativeViewAttached;
        boolean z2 = this.nativeViewHidden;
        String valueOf = String.valueOf(this.nativeViewBounds);
        String valueOf2 = String.valueOf(this.nativeViewVisibleBounds);
        StringBuilder stringBuilder = new StringBuilder((((((229 + String.valueOf(str).length()) + String.valueOf(str2).length()) + String.valueOf(str3).length()) + String.valueOf(str4).length()) + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length());
        stringBuilder.append("ActivityMonitorData{queryId=");
        stringBuilder.append(str);
        stringBuilder.append(", eventId=");
        stringBuilder.append(str2);
        stringBuilder.append(", vastEvent=");
        stringBuilder.append(str3);
        stringBuilder.append(", appState=");
        stringBuilder.append(str4);
        stringBuilder.append(", nativeTime=");
        stringBuilder.append(j);
        stringBuilder.append(", nativeVolume=");
        stringBuilder.append(d);
        stringBuilder.append(", nativeViewAttached=");
        stringBuilder.append(z);
        stringBuilder.append(", nativeViewHidden=");
        stringBuilder.append(z2);
        stringBuilder.append(", nativeViewBounds=");
        stringBuilder.append(valueOf);
        stringBuilder.append(", nativeViewVisibleBounds=");
        stringBuilder.append(valueOf2);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (!(this.queryId.equals(aVar.queryId()) && this.eventId.equals(aVar.eventId()) && this.vastEvent.equals(aVar.vastEvent()) && this.appState.equals(aVar.appState()) && this.nativeTime == aVar.nativeTime() && Double.doubleToLongBits(this.nativeVolume) == Double.doubleToLongBits(aVar.nativeVolume()) && this.nativeViewAttached == aVar.nativeViewAttached() && this.nativeViewHidden == aVar.nativeViewHidden() && this.nativeViewBounds.equals(aVar.nativeViewBounds()) && this.nativeViewVisibleBounds.equals(aVar.nativeViewVisibleBounds()))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 1237;
        int hashCode = (((((((((((((this.queryId.hashCode() ^ 1000003) * 1000003) ^ this.eventId.hashCode()) * 1000003) ^ this.vastEvent.hashCode()) * 1000003) ^ this.appState.hashCode()) * 1000003) ^ ((int) ((this.nativeTime >>> 32) ^ this.nativeTime))) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.nativeVolume) >>> 32) ^ Double.doubleToLongBits(this.nativeVolume)))) * 1000003) ^ (this.nativeViewAttached ? 1231 : 1237)) * 1000003;
        if (this.nativeViewHidden) {
            i = 1231;
        }
        return ((((hashCode ^ i) * 1000003) ^ this.nativeViewBounds.hashCode()) * 1000003) ^ this.nativeViewVisibleBounds.hashCode();
    }
}
