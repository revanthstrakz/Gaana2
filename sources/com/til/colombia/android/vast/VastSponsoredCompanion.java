package com.til.colombia.android.vast;

import android.view.View;
import com.til.colombia.android.vast.VastCompanionResource.Type;
import java.io.Serializable;

public class VastSponsoredCompanion implements Serializable {
    private String audioSrc;
    private VastCompanionResource companionResource;

    public String getAudioSrc() {
        return this.audioSrc;
    }

    public void setAudioSrc(String str) {
        this.audioSrc = str;
    }

    public VastCompanionResource getCompanionResource() {
        return this.companionResource;
    }

    public String getStaticCompanionRes() {
        return (this.companionResource == null || this.companionResource.getType() != Type.STATIC_RESOURCE) ? null : this.companionResource.getResource();
    }

    public void setCompanionResource(VastCompanionResource vastCompanionResource) {
        this.companionResource = vastCompanionResource;
    }

    public void initialize(View view) {
        if (this.companionResource != null) {
            this.companionResource.initializeVastResourceView(view);
        }
    }
}
