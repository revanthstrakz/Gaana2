package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;

final class o implements OnClickListener {
    final /* synthetic */ AdView a;

    o(AdView adView) {
        this.a = adView;
    }

    public final void onClick(View view) {
        try {
            if (this.a.item != null) {
                boolean z = false;
                if (this.a.item.getItemType() == ITEM_TYPE.PRODUCT || this.a.item.getItemType() == ITEM_TYPE.CONTENT || this.a.item.getItemType() == ITEM_TYPE.APP || this.a.item.getItemType() == ITEM_TYPE.VIDEO || this.a.item.getItemType() == ITEM_TYPE.BANNER) {
                    z = ((NativeItem) this.a.item).onClick();
                }
                bi.a();
                bi.a(this.a.item, z);
            }
        } catch (Exception unused) {
        }
    }
}
