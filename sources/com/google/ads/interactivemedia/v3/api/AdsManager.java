package com.google.ads.interactivemedia.v3.api;

import java.util.List;

public interface AdsManager extends BaseManager {
    void clicked();

    void discardAdBreak();

    void focusSkipButton();

    List<Float> getAdCuePoints();

    void pause();

    void requestNextAdBreak();

    void resume();

    void skip();

    void start();
}
