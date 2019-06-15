package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration.Builder;
import java.util.List;
import java.util.Map;

final class k extends TestingConfiguration {
    private final boolean disableExperiments;
    private final boolean enableMonitorAppLifecycle;
    private final Map<String, Object> extraParams;
    private final List<Long> forceExperimentIds;
    private final boolean forceTvMode;
    private final boolean ignoreStrictModeFalsePositives;
    private final boolean useTestStreamManager;
    private final boolean useVideoElementMock;
    private final float videoElementMockDuration;

    static final class a implements Builder {
        private Boolean disableExperiments;
        private Boolean enableMonitorAppLifecycle;
        private Map<String, Object> extraParams;
        private List<Long> forceExperimentIds;
        private Boolean forceTvMode;
        private Boolean ignoreStrictModeFalsePositives;
        private Boolean useTestStreamManager;
        private Boolean useVideoElementMock;
        private Float videoElementMockDuration;

        a() {
        }

        public Builder disableExperiments(boolean z) {
            this.disableExperiments = Boolean.valueOf(z);
            return this;
        }

        public Builder useVideoElementMock(boolean z) {
            this.useVideoElementMock = Boolean.valueOf(z);
            return this;
        }

        public Builder videoElementMockDuration(float f) {
            this.videoElementMockDuration = Float.valueOf(f);
            return this;
        }

        public Builder useTestStreamManager(boolean z) {
            this.useTestStreamManager = Boolean.valueOf(z);
            return this;
        }

        public Builder enableMonitorAppLifecycle(boolean z) {
            this.enableMonitorAppLifecycle = Boolean.valueOf(z);
            return this;
        }

        public Builder forceExperimentIds(List<Long> list) {
            this.forceExperimentIds = list;
            return this;
        }

        public Builder forceTvMode(boolean z) {
            this.forceTvMode = Boolean.valueOf(z);
            return this;
        }

        public Builder ignoreStrictModeFalsePositives(boolean z) {
            this.ignoreStrictModeFalsePositives = Boolean.valueOf(z);
            return this;
        }

        public Builder extraParams(Map<String, Object> map) {
            this.extraParams = map;
            return this;
        }

        public TestingConfiguration build() {
            String str = "";
            if (this.disableExperiments == null) {
                str = String.valueOf(str).concat(" disableExperiments");
            }
            if (this.useVideoElementMock == null) {
                str = String.valueOf(str).concat(" useVideoElementMock");
            }
            if (this.videoElementMockDuration == null) {
                str = String.valueOf(str).concat(" videoElementMockDuration");
            }
            if (this.useTestStreamManager == null) {
                str = String.valueOf(str).concat(" useTestStreamManager");
            }
            if (this.enableMonitorAppLifecycle == null) {
                str = String.valueOf(str).concat(" enableMonitorAppLifecycle");
            }
            if (this.forceTvMode == null) {
                str = String.valueOf(str).concat(" forceTvMode");
            }
            if (this.ignoreStrictModeFalsePositives == null) {
                str = String.valueOf(str).concat(" ignoreStrictModeFalsePositives");
            }
            if (str.isEmpty()) {
                return new k(this.disableExperiments.booleanValue(), this.useVideoElementMock.booleanValue(), this.videoElementMockDuration.floatValue(), this.useTestStreamManager.booleanValue(), this.enableMonitorAppLifecycle.booleanValue(), this.forceExperimentIds, this.forceTvMode.booleanValue(), this.ignoreStrictModeFalsePositives.booleanValue(), this.extraParams);
            }
            String str2 = "Missing required properties:";
            str = String.valueOf(str);
            throw new IllegalStateException(str.length() != 0 ? str2.concat(str) : new String(str2));
        }
    }

    private k(boolean z, boolean z2, float f, boolean z3, boolean z4, List<Long> list, boolean z5, boolean z6, Map<String, Object> map) {
        this.disableExperiments = z;
        this.useVideoElementMock = z2;
        this.videoElementMockDuration = f;
        this.useTestStreamManager = z3;
        this.enableMonitorAppLifecycle = z4;
        this.forceExperimentIds = list;
        this.forceTvMode = z5;
        this.ignoreStrictModeFalsePositives = z6;
        this.extraParams = map;
    }

    public boolean disableExperiments() {
        return this.disableExperiments;
    }

    public boolean useVideoElementMock() {
        return this.useVideoElementMock;
    }

    public float videoElementMockDuration() {
        return this.videoElementMockDuration;
    }

    public boolean useTestStreamManager() {
        return this.useTestStreamManager;
    }

    public boolean enableMonitorAppLifecycle() {
        return this.enableMonitorAppLifecycle;
    }

    public List<Long> forceExperimentIds() {
        return this.forceExperimentIds;
    }

    public boolean forceTvMode() {
        return this.forceTvMode;
    }

    public boolean ignoreStrictModeFalsePositives() {
        return this.ignoreStrictModeFalsePositives;
    }

    public Map<String, Object> extraParams() {
        return this.extraParams;
    }

    public String toString() {
        boolean z = this.disableExperiments;
        boolean z2 = this.useVideoElementMock;
        float f = this.videoElementMockDuration;
        boolean z3 = this.useTestStreamManager;
        boolean z4 = this.enableMonitorAppLifecycle;
        String valueOf = String.valueOf(this.forceExperimentIds);
        boolean z5 = this.forceTvMode;
        boolean z6 = this.ignoreStrictModeFalsePositives;
        String valueOf2 = String.valueOf(this.extraParams);
        StringBuilder stringBuilder = new StringBuilder((268 + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length());
        stringBuilder.append("TestingConfiguration{disableExperiments=");
        stringBuilder.append(z);
        stringBuilder.append(", useVideoElementMock=");
        stringBuilder.append(z2);
        stringBuilder.append(", videoElementMockDuration=");
        stringBuilder.append(f);
        stringBuilder.append(", useTestStreamManager=");
        stringBuilder.append(z3);
        stringBuilder.append(", enableMonitorAppLifecycle=");
        stringBuilder.append(z4);
        stringBuilder.append(", forceExperimentIds=");
        stringBuilder.append(valueOf);
        stringBuilder.append(", forceTvMode=");
        stringBuilder.append(z5);
        stringBuilder.append(", ignoreStrictModeFalsePositives=");
        stringBuilder.append(z6);
        stringBuilder.append(", extraParams=");
        stringBuilder.append(valueOf2);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TestingConfiguration)) {
            return false;
        }
        TestingConfiguration testingConfiguration = (TestingConfiguration) obj;
        if (!(this.disableExperiments == testingConfiguration.disableExperiments() && this.useVideoElementMock == testingConfiguration.useVideoElementMock() && Float.floatToIntBits(this.videoElementMockDuration) == Float.floatToIntBits(testingConfiguration.videoElementMockDuration()) && this.useTestStreamManager == testingConfiguration.useTestStreamManager() && this.enableMonitorAppLifecycle == testingConfiguration.enableMonitorAppLifecycle() && (!this.forceExperimentIds != null ? testingConfiguration.forceExperimentIds() != null : !this.forceExperimentIds.equals(testingConfiguration.forceExperimentIds())) && this.forceTvMode == testingConfiguration.forceTvMode() && this.ignoreStrictModeFalsePositives == testingConfiguration.ignoreStrictModeFalsePositives() && (!this.extraParams != null ? testingConfiguration.extraParams() != null : !this.extraParams.equals(testingConfiguration.extraParams())))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 1237;
        int i2 = 0;
        int floatToIntBits = ((((((((((((((this.disableExperiments ? 1231 : 1237) ^ 1000003) * 1000003) ^ (this.useVideoElementMock ? 1231 : 1237)) * 1000003) ^ Float.floatToIntBits(this.videoElementMockDuration)) * 1000003) ^ (this.useTestStreamManager ? 1231 : 1237)) * 1000003) ^ (this.enableMonitorAppLifecycle ? 1231 : 1237)) * 1000003) ^ (this.forceExperimentIds == null ? 0 : this.forceExperimentIds.hashCode())) * 1000003) ^ (this.forceTvMode ? 1231 : 1237)) * 1000003;
        if (this.ignoreStrictModeFalsePositives) {
            i = 1231;
        }
        floatToIntBits = (floatToIntBits ^ i) * 1000003;
        if (this.extraParams != null) {
            i2 = this.extraParams.hashCode();
        }
        return floatToIntBits ^ i2;
    }
}
