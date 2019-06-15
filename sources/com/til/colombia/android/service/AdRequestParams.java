package com.til.colombia.android.service;

import java.io.Serializable;

public class AdRequestParams implements Serializable {
    private AdListener adListener;
    private transient ColombiaAdManager adManager;
    private boolean isVideoAutoPlay;
    private ItemResponse response;

    @Deprecated
    public boolean isWebViewEnabled() {
        return false;
    }

    @Deprecated
    public AdRequestParams setWebViewEnabled(boolean z) {
        return this;
    }

    public AdListener getAdListener() {
        return this.adListener;
    }

    public AdRequestParams setAdListener(AdListener adListener) {
        this.adListener = adListener;
        return this;
    }

    public ItemResponse getResponse() {
        return this.response;
    }

    public AdRequestParams setResponse(ItemResponse itemResponse) {
        this.response = itemResponse;
        return this;
    }

    public ColombiaAdManager getAdManager() {
        return this.adManager;
    }

    public AdRequestParams setVideoAutoPlay(boolean z) {
        this.isVideoAutoPlay = z;
        return this;
    }

    public boolean isVideoAutoPlay() {
        return this.isVideoAutoPlay;
    }

    public AdRequestParams setAdManager(ColombiaAdManager colombiaAdManager) {
        this.adManager = colombiaAdManager;
        return this;
    }
}
