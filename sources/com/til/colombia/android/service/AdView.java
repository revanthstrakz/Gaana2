package com.til.colombia.android.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.facebook.ads.AdChoicesView;
import com.til.colombia.android.commons.CommonUtil;
import com.til.colombia.android.commons.a.h;
import com.til.colombia.android.internal.g;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.utils.b;

public class AdView extends FrameLayout {
    private View attrView;
    private View bannerImgView;
    private ColombiaBannerView bannerView;
    private View brandView;
    private OnClickListener clickListener = new o(this);
    private ImageView colombiaView;
    private Context context;
    private ImageView criteoIconView;
    private View ctaView;
    private View descView;
    private AdChoicesView fbIconView;
    private View iconView;
    private View imageView;
    private Item item;
    private OnScrollChangedListener layoutScrollListener = new n(this);
    private View priceView;
    private View rating;
    private View titleView;

    public AdView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public AdView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    public AdView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        this.context = context;
    }

    @RequiresApi(api = 21)
    public AdView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        super(context, attributeSet, i, i2);
        this.context = context;
    }

    @Deprecated
    public View getHeadlineView() {
        return getTitleView();
    }

    @Deprecated
    public View setHeadlineView(View view) {
        return setTitleView(view);
    }

    public View getTitleView() {
        return this.titleView;
    }

    public View setTitleView(View view) {
        this.titleView = view;
        this.titleView.setOnClickListener(this.clickListener);
        return this.titleView;
    }

    @Deprecated
    public View getBodyView() {
        return getDescriptionView();
    }

    @Deprecated
    public View setBodyView(View view) {
        return setDescriptionView(view);
    }

    public View getDescriptionView() {
        return this.descView;
    }

    public View setDescriptionView(View view) {
        this.descView = view;
        this.descView.setOnClickListener(this.clickListener);
        return this.descView;
    }

    @Deprecated
    public View getAdvertiserView() {
        return getBrandView();
    }

    @Deprecated
    public View setAdvertiserView(View view) {
        return setBrandView(view);
    }

    public View getBrandView() {
        return this.brandView;
    }

    public View setBrandView(View view) {
        this.brandView = view;
        this.brandView.setOnClickListener(this.clickListener);
        return this.brandView;
    }

    public View getPriceView() {
        return this.priceView;
    }

    public View setPriceView(View view) {
        this.priceView = view;
        this.priceView.setOnClickListener(this.clickListener);
        return this.priceView;
    }

    public View getRating() {
        return this.rating;
    }

    public View setRating(View view) {
        this.rating = view;
        this.rating.setOnClickListener(this.clickListener);
        return this.rating;
    }

    public View getAttributionTextView() {
        return this.attrView;
    }

    public View setAttributionTextView(View view) {
        this.attrView = view;
        this.attrView.setOnClickListener(this.clickListener);
        return this.attrView;
    }

    public ImageView getColombiaView() {
        return this.colombiaView;
    }

    public View setColombiaView(ImageView imageView) {
        this.colombiaView = imageView;
        this.colombiaView.setOnClickListener(this.clickListener);
        return this.colombiaView;
    }

    public View getIconView() {
        return this.iconView;
    }

    public View setIconView(View view) {
        this.iconView = view;
        this.iconView.setOnClickListener(this.clickListener);
        return this.iconView;
    }

    public View getImageView() {
        return this.imageView;
    }

    public View setImageView(View view) {
        this.imageView = view;
        this.imageView.setOnClickListener(this.clickListener);
        return this.imageView;
    }

    public View getCallToActionView() {
        return this.ctaView;
    }

    public View setCallToActionView(View view) {
        this.ctaView = view;
        this.ctaView.setOnClickListener(this.clickListener);
        return this.ctaView;
    }

    public ColombiaBannerView getBannerView() {
        return this.bannerView;
    }

    public View setBannerView(ColombiaBannerView colombiaBannerView) {
        this.bannerView = colombiaBannerView;
        return this.bannerView;
    }

    public View getBannerImageView() {
        return this.bannerImgView;
    }

    public View setBannerImageView(View view) {
        this.bannerImgView = view;
        this.bannerImgView.setOnClickListener(this.clickListener);
        return this.bannerImgView;
    }

    public void setOnClickListener(@Nullable OnClickListener onClickListener) {
        super.setOnClickListener(this.clickListener);
    }

    public void commitItem(CmItem cmItem) {
        this.item = (Item) cmItem;
        setOnClickListener(this.clickListener);
        if (!bi.a().a(((NativeItem) this.item).getItemResponse(), this.item, this)) {
            if (!(this.item == null || ((NativeItem) this.item).isImpressed() || !CmManager.getInstance().isVisible(this))) {
                bi.a();
                bi.a(this.item);
            }
            getViewTreeObserver().addOnScrollChangedListener(this.layoutScrollListener);
        }
    }

    public void commitItem(Item item) {
        this.item = item;
        if (this.item.thirdPartyAd() == null) {
            if (((NativeItem) this.item).getNetworkId().equalsIgnoreCase(g.g)) {
                hideColombiaIcon();
                showCriteoIcon();
            } else {
                hideCriteoIcon();
                showColombiaIcon();
            }
            if (this.item.getItemType() == ITEM_TYPE.GENERAL && getBannerView() != null) {
                getBannerView().setNativeAd(this.item);
            }
            setOnClickListener(this.clickListener);
            if (!bi.a().a(((NativeItem) this.item).getItemResponse(), this.item, this)) {
                if (!(this.item == null || ((NativeItem) this.item).isImpressed() || !CmManager.getInstance().isVisible(this))) {
                    bi.a();
                    bi.a(this.item);
                }
                getViewTreeObserver().addOnScrollChangedListener(this.layoutScrollListener);
            }
        }
    }

    public void commitItem(String str, String str2) {
        if (CmManager.getInstance().getCmFeedUtil() != null) {
            CmEntity cmEntity = CmManager.getInstance().getCmFeedUtil().getCmEntity(str);
            if (cmEntity != null) {
                for (CmItem cmItem : cmEntity.getCmItems()) {
                    if (cmItem.getUID().equalsIgnoreCase(str2)) {
                        this.item = (Item) cmItem;
                    }
                }
            }
        }
        setOnClickListener(this.clickListener);
        if (!bi.a().a(((NativeItem) this.item).getItemResponse(), this.item, this)) {
            if (!(this.item == null || ((NativeItem) this.item).isImpressed() || !CmManager.getInstance().isVisible(this))) {
                bi.a();
                bi.a(this.item);
            }
            getViewTreeObserver().addOnScrollChangedListener(this.layoutScrollListener);
        }
    }

    private void showCriteoIcon() {
        if (((NativeItem) this.item).getNetworkId().equalsIgnoreCase(g.g)) {
            if (this.criteoIconView == null) {
                this.criteoIconView = new ImageView(this.context);
                LayoutParams layoutParams = new LayoutParams(CommonUtil.b(15.0f, this.context), CommonUtil.b(15.0f, this.context));
                layoutParams.gravity = 53;
                layoutParams.topMargin = CommonUtil.b(2.0f, this.context);
                layoutParams.rightMargin = CommonUtil.b(2.0f, this.context);
                addView(this.criteoIconView, layoutParams);
                Bitmap a = h.a(((NativeItem) this.item).getAdChoiceImageUrl());
                if (a != null) {
                    this.criteoIconView.setImageBitmap(a);
                } else {
                    new b().a(this.criteoIconView, ((NativeItem) this.item).getAdChoiceImageUrl());
                }
                this.criteoIconView.setOnClickListener(new p(this));
                return;
            }
            this.criteoIconView.setVisibility(0);
        }
    }

    private void hideCriteoIcon() {
        if (this.criteoIconView != null) {
            this.criteoIconView.setVisibility(8);
        }
    }

    private void hideColombiaIcon() {
        if (this.colombiaView != null) {
            this.colombiaView.setVisibility(8);
        }
    }

    private void showColombiaIcon() {
        if (this.colombiaView != null) {
            this.colombiaView.setVisibility(0);
        }
    }
}
