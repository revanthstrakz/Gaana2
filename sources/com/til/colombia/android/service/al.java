package com.til.colombia.android.service;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

final class al extends OnScrollListener {
    final /* synthetic */ ColombiaCarouselAdView a;

    al(ColombiaCarouselAdView colombiaCarouselAdView) {
        this.a = colombiaCarouselAdView;
    }

    public final void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        try {
            int findFirstCompletelyVisibleItemPosition = this.a.linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            i = this.a.linearLayoutManager.findLastVisibleItemPosition();
            if (this.a.itemResponse.isImpressed()) {
                while (findFirstCompletelyVisibleItemPosition < i) {
                    findFirstCompletelyVisibleItemPosition++;
                    this.a.itemResponse.trackItemImpression((Item) this.a.adItems.get(findFirstCompletelyVisibleItemPosition));
                }
            }
        } catch (Exception unused) {
        }
    }
}
