package com.managers;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.helpshift.common.platform.network.c;
import com.library.controls.CrossFadeImageView;
import com.managers.ColombiaManager.a;
import com.til.colombia.android.commons.MEDIA_CACHE_FLAG;
import com.til.colombia.android.commons.USER_ACTION;
import com.til.colombia.android.internal.ColombiaException;
import com.til.colombia.android.service.AdListener;
import com.til.colombia.android.service.AdView;
import com.til.colombia.android.service.Colombia;
import com.til.colombia.android.service.ColombiaAdManager.DFP_ITEM_TYPE;
import com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE;
import com.til.colombia.android.service.ColombiaAdRequest;
import com.til.colombia.android.service.ColombiaAdRequest.Builder;
import com.til.colombia.android.service.Item;

public class t {
    private static t a;
    private c b = null;

    public void a(String str, String str2) {
        this.b = new c(str, str2);
    }

    public static t a() {
        if (a == null) {
            synchronized (ColombiaManager.class) {
                if (a == null) {
                    a = new t();
                }
            }
        }
        return a;
    }

    private t() {
    }

    public void a(boolean z, boolean z2, Builder builder, int i, Context context, long j, View view, View view2, String str, a aVar, String str2) {
        Builder builder2 = builder;
        if (j != 0) {
            builder2.enabledGoogleAdFormats(new DFP_ITEM_TYPE[]{DFP_ITEM_TYPE.APP});
            builder2.addCustomAudience("GUL", GaanaApplication.getInstance().getSongLanguagesString());
            builder2.addCustomAudience("SectionName", str2);
            final View view3 = view;
            final a aVar2 = aVar;
            final Context context2 = context;
            final boolean z3 = z;
            final boolean z4 = z2;
            final View view4 = view2;
            final String str3 = str;
            try {
                Colombia.getNativeAds(builder2.addRequest(Long.valueOf(j), i, str, new AdListener() {
                    public boolean onItemClick(Item item) {
                        return false;
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

                    /* JADX WARNING: Removed duplicated region for block: B:22:0x006d  */
                    public void onItemLoaded(com.til.colombia.android.service.ColombiaAdRequest r13, com.til.colombia.android.service.ItemResponse r14) {
                        /*
                        r12 = this;
                        r13 = r14.getPaidItems();
                        r0 = r14.getOrganicItems();
                        r1 = 0;
                        r2 = 0;
                        if (r13 == 0) goto L_0x001a;
                    L_0x000c:
                        r3 = r13.size();
                        if (r3 <= 0) goto L_0x001a;
                    L_0x0012:
                        r13 = r13.get(r2);
                        r13 = (com.til.colombia.android.service.Item) r13;
                    L_0x0018:
                        r7 = r13;
                        goto L_0x002a;
                    L_0x001a:
                        if (r0 == 0) goto L_0x0029;
                    L_0x001c:
                        r13 = r0.size();
                        if (r13 <= 0) goto L_0x0029;
                    L_0x0022:
                        r13 = r0.get(r2);
                        r13 = (com.til.colombia.android.service.Item) r13;
                        goto L_0x0018;
                    L_0x0029:
                        r7 = r1;
                    L_0x002a:
                        r13 = r10;
                        r0 = 2131297587; // 0x7f090533 float:1.8213123E38 double:1.053000919E-314;
                        r13 = r13.findViewById(r0);
                        r8 = r13;
                        r8 = (android.widget.LinearLayout) r8;
                        if (r7 == 0) goto L_0x0069;
                    L_0x0038:
                        if (r8 != 0) goto L_0x003b;
                    L_0x003a:
                        goto L_0x0069;
                    L_0x003b:
                        r8.setPadding(r2, r2, r2, r2);
                        r13 = 8;
                        r8.setVisibility(r13);
                        r3 = com.managers.t.this;
                        r4 = r12;
                        r5 = r13;
                        r6 = r14;
                        r13 = r15;
                        r9 = r13;
                        r9 = (com.til.colombia.android.service.AdView) r9;
                        r10 = r11;
                        r11 = r16;
                        r3.a(r4, r5, r6, r7, r8, r9, r10, r11);
                        r13 = r10;	 Catch:{ ColombiaException -> 0x0064 }
                        r0 = 2131296717; // 0x7f0901cd float:1.8211359E38 double:1.053000489E-314;
                        r13 = r13.findViewById(r0);	 Catch:{ ColombiaException -> 0x0064 }
                        com.til.colombia.android.service.Colombia.recordImpression(r14, r13);	 Catch:{ ColombiaException -> 0x0064 }
                        goto L_0x0068;
                    L_0x0064:
                        r13 = move-exception;
                        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r13);
                    L_0x0068:
                        return;
                    L_0x0069:
                        r13 = r11;
                        if (r13 == 0) goto L_0x0072;
                    L_0x006d:
                        r13 = r11;
                        r13.onItemRequestFailed(r1);
                    L_0x0072:
                        return;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.managers.t$AnonymousClass1.onItemLoaded(com.til.colombia.android.service.ColombiaAdRequest, com.til.colombia.android.service.ItemResponse):void");
                    }

                    public void onItemRequestFailed(ColombiaAdRequest colombiaAdRequest, Exception exception) {
                        if (aVar2 != null) {
                            aVar2.onItemRequestFailed(exception);
                        }
                        LinearLayout linearLayout = (LinearLayout) view3.findViewById(R.id.llNativeAdSlot);
                        if (linearLayout != null) {
                            linearLayout.removeAllViews();
                            linearLayout.setPadding(0, 0, 0, 0);
                            linearLayout.setVisibility(8);
                        }
                    }
                }).addReferer("https://api.gaana.com/").addMediaCacheFlags(new MEDIA_CACHE_FLAG[]{MEDIA_CACHE_FLAG.ALL}).enableRecordManualImpression(true).downloadImageBitmap(true).build());
            } catch (ColombiaException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    private void a(Context context, boolean z, boolean z2, Item item, LinearLayout linearLayout, AdView adView, a aVar, String str) {
        ITEM_TYPE itemType = item.getItemType();
        if (adView == null || !(itemType == ITEM_TYPE.CONTENT || itemType == ITEM_TYPE.APP)) {
            if (aVar != null) {
                aVar.onItemRequestFailed(null);
            }
            return;
        }
        adView.setVisibility(8);
        linearLayout.removeAllViews();
        linearLayout.addView(adView);
        if (z) {
            a(z, z2, item, adView, str);
        } else {
            a(item, adView);
        }
        adView.setVisibility(0);
        if (aVar != null) {
            aVar.onItemLoaded(item);
        }
        linearLayout.setVisibility(0);
    }

    private void a(Item item, AdView adView) {
        adView.setHeadlineView(adView.findViewById(R.id.colom_ad_headLine));
        adView.setImageView(adView.findViewById(R.id.colom_ad_image));
        adView.setColombiaView((ImageView) adView.findViewById(R.id.nativeColomIcon));
        TextView textView = (TextView) adView.getHeadlineView();
        String brand = item.getBrand();
        if (!TextUtils.isEmpty(brand)) {
            textView.setText(brand);
        }
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) adView.getImageView();
        crossFadeImageView.setImageBitmap(item.getImage());
        crossFadeImageView.setScaleType(ScaleType.FIT_XY);
        ((TextView) adView.findViewById(R.id.colom_ad_text)).setText(item.getTitle());
        if (TextUtils.isEmpty(item.getCtaText())) {
            adView.findViewById(R.id.ctaText).setVisibility(8);
        } else {
            Button button = (Button) adView.findViewById(R.id.ctaText);
            button.setVisibility(0);
            button.setText(item.getCtaText());
            adView.setCallToActionView(button);
        }
        adView.commitItem(item);
        if (item.thirdPartyAd() == null) {
            adView.getColombiaView().setBackgroundResource(R.drawable.colombia_icon);
        }
    }

    private void a(boolean z, boolean z2, Item item, AdView adView, String str) {
        adView.setHeadlineView(adView.findViewById(R.id.colom_ad_headLine));
        adView.setImageView(adView.findViewById(R.id.colom_ad_image));
        adView.setAdvertiserView(adView.findViewById(R.id.colom_ad_brand));
        adView.setAttributionTextView(adView.findViewById(R.id.colom_ad_text));
        adView.setColombiaView((ImageView) adView.findViewById(R.id.nativeColomIcon));
        TextView textView = (TextView) adView.getHeadlineView();
        String title = item.getTitle();
        if (!(title == null || title.isEmpty())) {
            textView.setText(item.getTitle());
        }
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) adView.getImageView();
        if (!z2 || str.equalsIgnoreCase("GaanaSpecialDetailsMaterialListing") || item.getImage() == null) {
            crossFadeImageView.setVisibility(8);
        } else {
            crossFadeImageView.setImageBitmap(item.getImage());
            crossFadeImageView.setScaleType(ScaleType.FIT_XY);
        }
        textView = (TextView) adView.getAttributionTextView();
        String adAttributionText = item.getAdAttributionText();
        if (!(adAttributionText == null || adAttributionText.isEmpty())) {
            textView.setText(adAttributionText);
        }
        if (item.thirdPartyAd() != null) {
            textView.setTextColor(Color.parseColor("#FEFEFE"));
        }
        textView = (TextView) adView.getAdvertiserView();
        adAttributionText = item.getBrandText();
        if (!(adAttributionText == null || adAttributionText.isEmpty())) {
            textView.setText(adAttributionText);
        }
        adView.commitItem(item);
        if (item.thirdPartyAd() == null) {
            adView.getColombiaView().setBackgroundResource(R.drawable.colombia_icon);
        }
    }
}
