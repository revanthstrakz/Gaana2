package com.til.colombia.android.service;

import com.til.colombia.android.commons.USER_ACTION;

public abstract class AdListener {
    public boolean onItemClick(Item item) {
        return false;
    }

    public abstract void onItemLoaded(ColombiaAdRequest colombiaAdRequest, ItemResponse itemResponse);

    public void onItemRequestFailed(ColombiaAdRequest colombiaAdRequest, Exception exception) {
    }

    public void onMediaItemClicked(Item item) {
    }

    public void onMediaItemClosed(Item item, USER_ACTION user_action) {
    }

    public void onMediaItemCompleted(Item item, int i) {
    }

    public void onMediaItemDisplayed(Item item) {
    }

    public void onMediaItemError(Item item, Exception exception) {
    }

    public void onMediaItemSkipEnabled(Item item) {
    }
}
