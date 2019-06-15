package com.til.colombia.android.service;

import com.til.colombia.android.commons.USER_ACTION;

final class af extends AdListener {
    private ItemListener a;

    af(ItemListener itemListener) {
        this.a = itemListener;
    }

    public final void onItemLoaded(ColombiaAdRequest colombiaAdRequest, ItemResponse itemResponse) {
        this.a.onItemLoaded(colombiaAdRequest, itemResponse);
    }

    public final void onItemRequestFailed(ColombiaAdRequest colombiaAdRequest, Exception exception) {
        this.a.onItemRequestFailed(colombiaAdRequest, exception);
    }

    public final void onMediaItemDisplayed(Item item) {
        if (this.a != null && (this.a instanceof MediaItemListener)) {
            ((MediaItemListener) this.a).onMediaItemDisplayed(item);
        }
    }

    public final void onMediaItemClicked(Item item) {
        if (this.a != null && (this.a instanceof MediaItemListener)) {
            ((MediaItemListener) this.a).onMediaItemClicked(item);
        }
    }

    public final void onMediaItemClosed(Item item, USER_ACTION user_action) {
        if (this.a != null && (this.a instanceof MediaItemListener)) {
            ((MediaItemListener) this.a).onMediaItemClosed(item, user_action);
        }
    }

    public final void onMediaItemCompleted(Item item, int i) {
        if (this.a != null && (this.a instanceof MediaItemListener)) {
            ((MediaItemListener) this.a).onMediaItemCompleted(item, i);
        }
    }

    public final void onMediaItemError(Item item, Exception exception) {
        if (this.a != null && (this.a instanceof MediaItemListener)) {
            ((MediaItemListener) this.a).onMediaItemError(item, exception);
        }
    }

    public final void onMediaItemSkipEnabled(Item item) {
        if (this.a != null && (this.a instanceof MediaItemListener)) {
            ((MediaItemListener) this.a).onMediaItemSkipEnabled(item);
        }
    }
}
