package com.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.gaana.R;
import com.gaana.view.BaseItemView;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.library.controls.CrossFadeImageView;
import com.til.colombia.android.service.ColombiaAdManager.AD_NTWK;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.service.FbAdView;
import com.til.colombia.android.service.GoogleAppAdView;
import com.til.colombia.android.service.GoogleContentAdView;
import com.til.colombia.android.service.Item;

public class ColombiaMediationAdView extends BaseItemView {
    private Context a;

    public enum AdViewType {
        M_320x100,
        M_320x250,
        M_320x60
    }

    public ColombiaMediationAdView(Context context) {
        super(context, null);
        this.a = context;
    }

    public ColombiaMediationAdView(Context context, AttributeSet attributeSet) {
        super(context, null, attributeSet);
        this.a = context;
    }

    public View a(Item item, AdViewType adViewType) {
        View view = null;
        switch (adViewType) {
            case M_320x100:
                view = getNewView(R.layout.col_med_adview_320x100, null);
                break;
            case M_320x250:
                view = getNewView(R.layout.col_med_adview_320x250, null);
                ((CrossFadeImageView) view.findViewById(R.id.adImage)).bindImage(item.getImageUrl(), ScaleType.FIT_XY);
                ((CrossFadeImageView) view.findViewById(R.id.adLogo)).bindImage(item.getIconUrl());
                if (TextUtils.isEmpty(item.getCtaText())) {
                    ((TextView) view.findViewById(R.id.ctaText)).setVisibility(8);
                } else {
                    ((TextView) view.findViewById(R.id.ctaText)).setText(item.getCtaText());
                    ((TextView) view.findViewById(R.id.ctaText)).setVisibility(0);
                }
                ((TextView) view.findViewById(R.id.title)).setText(item.getTitle());
                if (!TextUtils.isEmpty(item.getBodyText())) {
                    ((TextView) view.findViewById(R.id.description)).setText(item.getBodyText());
                    ((TextView) view.findViewById(R.id.description)).setVisibility(0);
                    break;
                }
                ((TextView) view.findViewById(R.id.description)).setVisibility(8);
                break;
            case M_320x60:
                view = getNewView(R.layout.colombia_detail_list_content_med_ad, null);
                ((CrossFadeImageView) view.findViewById(R.id.colom_ad_image)).bindImage(item.getImageUrl(), ScaleType.FIT_XY);
                ((TextView) view.findViewById(R.id.colom_ad_headLine)).setText(item.getTitle());
                ((TextView) view.findViewById(R.id.colom_ad_text)).setText(item.getBodyText());
                ((TextView) view.findViewById(R.id.ctaText)).setText(item.getCtaText());
                break;
        }
        return a(item, view);
    }

    private ViewGroup a(Item item, View view) {
        ViewGroup fbAdView;
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        if (item.getAdNetwork() == AD_NTWK.FACEBOOK) {
            if (item.getItemType() == ITEM_TYPE.APP) {
                fbAdView = new FbAdView(this.a);
                fbAdView.setLayoutParams(layoutParams);
                fbAdView.addView(view);
                fbAdView.commitItem(item);
                return fbAdView;
            }
        } else if (item.getAdNetwork() != AD_NTWK.GOOGLE) {
            return new LinearLayout(this.a);
        } else {
            ViewGroup googleAppAdView;
            if (item.getItemType() == ITEM_TYPE.APP) {
                NativeAppInstallAdView nativeAppInstallAdView = new NativeAppInstallAdView(this.a);
                nativeAppInstallAdView.addView(view);
                nativeAppInstallAdView.setLayoutParams(layoutParams);
                googleAppAdView = new GoogleAppAdView(this.a);
                googleAppAdView.addView(nativeAppInstallAdView);
                googleAppAdView.setCallToActionView(view.findViewById(R.id.ctaText));
                googleAppAdView.setGoogleView(nativeAppInstallAdView);
                googleAppAdView.setLayoutParams(layoutParams);
                googleAppAdView.commitItem(item);
            } else if (item.getItemType() == ITEM_TYPE.CONTENT) {
                NativeContentAdView nativeContentAdView = new NativeContentAdView(this.a);
                nativeContentAdView.addView(view);
                googleAppAdView = new GoogleContentAdView(this.a);
                googleAppAdView.addView(nativeContentAdView);
                googleAppAdView.setCallToActionView(view.findViewById(R.id.ctaText));
                googleAppAdView.setGoogleView(nativeContentAdView);
                googleAppAdView.setLayoutParams(layoutParams);
                googleAppAdView.commitItem(item);
            }
            fbAdView = googleAppAdView;
            return fbAdView;
        }
        fbAdView = null;
        return fbAdView;
    }
}
