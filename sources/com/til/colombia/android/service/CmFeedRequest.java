package com.til.colombia.android.service;

import com.comscore.streaming.Constants;

public final class CmFeedRequest {
    public Builder a;

    public static final class Builder {
        private String appVer;
        private String auds;
        private String cId;
        private String itemId;
        private String lang;
        private int pageNo;
        private String referer;
        private int refresh;
        private Long slotId;
        private long timeout = Constants.HEARTBEAT_STAGE_ONE_INTERVAL;

        public final CmFeedRequest build() {
            return new CmFeedRequest(this, (byte) 0);
        }

        public Builder(Long l, int i, int i2) {
            this.slotId = l;
            this.pageNo = i;
            this.refresh = i2;
        }

        public final Builder addAuds(String str) {
            this.auds = str;
            return this;
        }

        public final Builder addLanguage(String str) {
            this.lang = str;
            return this;
        }

        public final Builder addCategoryId(String str) {
            this.cId = str;
            return this;
        }

        public final Builder addReferer(String str) {
            this.referer = str;
            return this;
        }

        public final Builder setReqItemId(String str) {
            this.itemId = str;
            return this;
        }

        public final Builder setAppVer(String str) {
            this.appVer = str;
            return this;
        }

        public final Builder setTimeout(long j) {
            this.timeout = j;
            return this;
        }
    }

    /* synthetic */ CmFeedRequest(Builder builder, byte b) {
        this(builder);
    }

    private CmFeedRequest(Builder builder) {
        this.a = builder;
    }

    public final long a() {
        return this.a.slotId.longValue();
    }

    public final int b() {
        return this.a.pageNo;
    }

    public final int c() {
        return this.a.refresh;
    }

    public final String d() {
        return this.a.auds;
    }

    public final String e() {
        return this.a.lang;
    }

    public final String f() {
        return this.a.appVer;
    }

    public final String g() {
        return this.a.cId;
    }

    public final String h() {
        return this.a.referer;
    }

    public final String i() {
        return this.a.itemId;
    }

    /* Access modifiers changed, original: protected|final */
    public final long j() {
        return this.a.timeout;
    }
}
