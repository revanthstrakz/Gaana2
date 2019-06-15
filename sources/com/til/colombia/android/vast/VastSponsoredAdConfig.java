package com.til.colombia.android.vast;

import java.io.Serializable;

public class VastSponsoredAdConfig implements Serializable {
    private int adFreeDuration;
    private VastSponsoredCompanion postAudioCompanion;
    private VastSponsoredCompanion preAudioCompanion;

    public VastSponsoredCompanion getPreAudioCompanion() {
        return this.preAudioCompanion;
    }

    public void setPreAudioCompanion(VastSponsoredCompanion vastSponsoredCompanion) {
        this.preAudioCompanion = vastSponsoredCompanion;
    }

    public VastSponsoredCompanion getPostAudioCompanion() {
        return this.postAudioCompanion;
    }

    public void setPostAudioCompanion(VastSponsoredCompanion vastSponsoredCompanion) {
        this.postAudioCompanion = vastSponsoredCompanion;
    }

    public int getAdFreeDuration() {
        return this.adFreeDuration;
    }

    public void setAdFreeDuration(int i) {
        this.adFreeDuration = i;
    }

    public boolean isPreConfigPresent() {
        if (this.preAudioCompanion == null || this.preAudioCompanion.getCompanionResource() == null) {
            return false;
        }
        return true;
    }

    public boolean isPostConfigPresent() {
        if (this.postAudioCompanion == null) {
            return false;
        }
        if (this.postAudioCompanion.getCompanionResource() == null && this.postAudioCompanion.getAudioSrc() == null) {
            return false;
        }
        return true;
    }
}
