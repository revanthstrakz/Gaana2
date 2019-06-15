package com.til.colombia.android.vast;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.a.d;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.network.n;
import com.til.colombia.android.service.AdSize;
import com.til.colombia.android.service.VASTHelper;
import java.io.Serializable;
import java.util.List;

public class VastCompanionAdConfig implements Serializable {
    private static final long serialVersionUID = 0;
    private final AdSize adSize;
    private final String mClickThroughUrl;
    private final List<VastTrackingEvent> mClickTrackers;
    private final List<VastTrackingEvent> mCreativeViewTrackers;
    private final int mHeight;
    private final VastCompanionResource mVastCompanionResource;
    private final int mWidth;

    public VastCompanionAdConfig(int i, int i2, VastCompanionResource vastCompanionResource, String str, List<VastTrackingEvent> list, List<VastTrackingEvent> list2) {
        this.mWidth = i;
        this.mHeight = i2;
        this.mVastCompanionResource = vastCompanionResource;
        this.mClickThroughUrl = str;
        this.mClickTrackers = list;
        this.mCreativeViewTrackers = list2;
        this.adSize = new AdSize(i, i2);
    }

    public VastCompanionAdConfig(int i, int i2, VastCompanionResource vastCompanionResource, String str, List<VastTrackingEvent> list, List<VastTrackingEvent> list2, AdSize adSize) {
        this.mWidth = i;
        this.mHeight = i2;
        this.mVastCompanionResource = vastCompanionResource;
        this.mClickThroughUrl = str;
        this.mClickTrackers = list;
        this.mCreativeViewTrackers = list2;
        this.adSize = adSize;
    }

    public AdSize getAdSize() {
        return this.adSize;
    }

    public void addClickTrackers(List<VastTrackingEvent> list) {
        if (list != null) {
            this.mClickTrackers.addAll(list);
        }
    }

    public void addCreativeViewTrackers(List<VastTrackingEvent> list) {
        if (list != null) {
            this.mCreativeViewTrackers.addAll(list);
        }
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public VastCompanionResource getVastResource() {
        return this.mVastCompanionResource;
    }

    public String getClickThroughUrl() {
        return this.mClickThroughUrl;
    }

    public List<VastTrackingEvent> getClickTrackers() {
        return this.mClickTrackers;
    }

    public List<VastTrackingEvent> getCreativeViewTrackers() {
        return this.mCreativeViewTrackers;
    }

    /* Access modifiers changed, original: 0000 */
    public void handleImpression() {
        n.a(VASTHelper.getTrackingEventUris(this.mCreativeViewTrackers), 5, "Vast Companion impressed.");
    }

    public void handleClick(Context context, String str) {
        if (context != null && !(context instanceof Activity)) {
            str = this.mVastCompanionResource.getCorrectClickThroughUrl(this.mClickThroughUrl, str);
            if (!TextUtils.isEmpty(str)) {
                try {
                    n.a(context, Uri.parse(d.a(str)));
                } catch (Exception e) {
                    Log.a(i.f, "", e);
                }
            }
        }
    }
}
