package com.til.colombia.android.vast;

import com.til.colombia.android.service.AdSize;
import java.util.List;

public class IconConfig extends VastCompanionAdConfig {
    public IconConfig(int i, int i2, VastCompanionResource vastCompanionResource, String str, List<VastTrackingEvent> list, List<VastTrackingEvent> list2) {
        super(i, i2, vastCompanionResource, str, list, list2);
    }

    public IconConfig(int i, int i2, VastCompanionResource vastCompanionResource, String str, List<VastTrackingEvent> list, List<VastTrackingEvent> list2, AdSize adSize) {
        super(i, i2, vastCompanionResource, str, list, list2, adSize);
    }
}
