package com.til.colombia.android.service;

@Deprecated
public interface ItemListener {
    void onItemLoaded(ColombiaAdRequest colombiaAdRequest, ItemResponse itemResponse);

    void onItemRequestFailed(ColombiaAdRequest colombiaAdRequest, Exception exception);
}
