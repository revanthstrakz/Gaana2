package com.til.colombia.android.service;

import com.til.colombia.android.commons.USER_ACTION;

@Deprecated
public interface MediaItemListener extends ItemListener {
    void onMediaItemClicked(Item item);

    void onMediaItemClosed(Item item, USER_ACTION user_action);

    void onMediaItemCompleted(Item item, int i);

    void onMediaItemDisplayed(Item item);

    void onMediaItemError(Item item, Exception exception);

    void onMediaItemSkipEnabled(Item item);
}
