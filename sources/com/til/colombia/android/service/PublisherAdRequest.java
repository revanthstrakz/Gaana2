package com.til.colombia.android.service;

public final class PublisherAdRequest {
    private Builder builder;

    public static final class Builder {
        private AdListener adListener;
        private Long adUnitId;
        private int positionId;
        private String requestCode;
        private String sectionId;

        public final PublisherAdRequest build() {
            return new PublisherAdRequest(this);
        }

        public Builder(Long l, int i, String str, ItemListener itemListener) {
            this.adUnitId = l;
            this.positionId = i;
            this.sectionId = str;
            this.adListener = new af(itemListener);
        }

        public Builder(Long l, int i, String str, AdListener adListener) {
            this.adUnitId = l;
            this.positionId = i;
            this.sectionId = str;
            this.adListener = adListener;
        }

        public final Builder setRequestCode(String str) {
            this.requestCode = str;
            return this;
        }

        public final int getPositionId() {
            return this.positionId;
        }

        private String getRequestCode() {
            return this.requestCode;
        }

        private Long getAdUnitId() {
            return this.adUnitId;
        }

        private AdListener getAdListener() {
            return this.adListener;
        }

        private String getSectionId() {
            return this.sectionId;
        }
    }

    private PublisherAdRequest(Builder builder) {
        this.builder = builder;
    }

    public final String getRequestCode() {
        return this.builder.getRequestCode();
    }

    public final Long getAdUnitId() {
        return this.builder.getAdUnitId();
    }

    public final String getSectionId() {
        return this.builder.getSectionId();
    }

    public final int getPositionId() {
        return this.builder.getPositionId();
    }

    public final AdListener getAdListener() {
        return this.builder.getAdListener();
    }
}
