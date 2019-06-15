package com.til.colombia.android.service;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;

public class ContentView extends FrameLayout {
    private CmItem cmItem;
    private OnScrollChangedListener layoutScrollListener = new bk(this);

    public ContentView(@NonNull Context context) {
        super(context);
    }

    public ContentView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ContentView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
    }

    @RequiresApi(api = 21)
    public ContentView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        super(context, attributeSet, i, i2);
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.cmItem == null || ((NativeItem) this.cmItem).isImpressed()) {
            try {
                getViewTreeObserver().addOnScrollChangedListener(this.layoutScrollListener);
                return;
            } catch (Exception unused) {
                return;
            }
        }
        bi.a();
        bi.a(this.cmItem);
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            getViewTreeObserver().removeOnScrollChangedListener(this.layoutScrollListener);
        } catch (Exception unused) {
        }
    }

    public void commitItem(CmItem cmItem) {
        this.cmItem = cmItem;
        if (this.cmItem != null && !((NativeItem) this.cmItem).isImpressed() && CmManager.getInstance().isVisible(this)) {
            bi.a();
            bi.a(this.cmItem);
        }
    }

    public void commitItem(String str, String str2) {
        if (CmManager.getInstance().getCmFeedUtil() != null) {
            CmEntity cmEntity = CmManager.getInstance().getCmFeedUtil().getCmEntity(str);
            if (cmEntity != null) {
                for (CmItem cmItem : cmEntity.getCmItems()) {
                    if (cmItem.getUID().equalsIgnoreCase(str2)) {
                        this.cmItem = cmItem;
                        if (!(this.cmItem == null || ((NativeItem) this.cmItem).isImpressed() || !CmManager.getInstance().isVisible(this))) {
                            bi.a();
                            bi.a(this.cmItem);
                        }
                    }
                }
            }
        }
    }
}
