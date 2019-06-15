package com.google.ads.interactivemedia.v3.api;

import java.util.List;

public interface StreamManager extends BaseManager {
    double getContentTimeForStreamTime(double d);

    List<CuePoint> getCuePoints();

    CuePoint getPreviousCuePointForStreamTime(double d);

    String getStreamId();

    double getStreamTimeForContentTime(double d);
}
