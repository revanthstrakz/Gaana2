package com.til.colombia.android.vast;

import java.io.Serializable;

public class VastTrackingEvent implements Serializable {
    private static final long serialVersionUID = 10;
    private boolean isRepeatable;
    private boolean isTracked;
    private String url;

    VastTrackingEvent(String str, boolean z, boolean z2) {
        this.url = str;
        this.isRepeatable = z;
        this.isTracked = z2;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isRepeatable() {
        return this.isRepeatable;
    }

    public boolean isTracked() {
        return this.isTracked;
    }

    public void setIsTracked(boolean z) {
        this.isTracked = z;
    }
}
