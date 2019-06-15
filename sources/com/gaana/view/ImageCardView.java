package com.gaana.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.constants.Constants.ACTION_TYPE;
import com.constants.Constants.VIEW_SIZE;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.DynamicOccasionFragment;
import com.dynamicview.DynamicViewManager;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.dynamicview.c;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.fragments.CreateNewPlaylistFragment;
import com.fragments.PersonaDedicationFragment;
import com.fragments.SettingsDetailFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.WebViewActivity;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.login.FbLoginErrorDialog;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.UserInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.RadioMoods;
import com.gaana.models.TextCardItem;
import com.gaana.models.User.LoginType;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.PopupWindowView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.e;
import com.managers.u;
import com.services.d;
import com.services.f;
import com.services.l;
import com.services.l.ag;
import com.services.l.as;
import com.services.l.r;
import com.services.l.s;
import com.utilities.Util;
import java.util.Map;

public class ImageCardView extends BaseItemView {
    private int imageDownloadStatus = -1;
    private boolean isFirstCall = true;
    private boolean isViewDestroyed = false;
    private Dialog mDialog;
    private a mDynamicView;
    private BaseGaanaFragment mFragment;
    private boolean mIsRefreshed = false;

    public static class ImageCardViewHolder extends ViewHolder {
        public LinearLayout llImgParentLayout;
        public LinearLayout llParentLayout;

        public ImageCardViewHolder(View view, boolean z) {
            super(view);
            if (z) {
                this.llImgParentLayout = (LinearLayout) view.findViewById(R.id.ll_img_parent);
                this.llParentLayout = (LinearLayout) view.findViewById(R.id.llParentLayout);
            }
        }
    }

    private void displaySocialFragment() {
    }

    public ImageCardView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mDynamicView = aVar;
        this.mFragment = baseGaanaFragment;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.imageDownloadStatus = -1;
        View newView = getNewView(R.layout.image_card_view, viewGroup);
        checkForTrialCard(newView);
        return new ImageCardViewHolder(newView, true);
    }

    private void checkForTrialCard(View view) {
        if (this.mDynamicView != null && this.mDynamicView.f() == ACTION_TYPE.TRIAL_CARD.getNumVal()) {
            view.setVisibility(8);
            checkForTrialCard();
        }
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        return LayoutInflater.from(this.mContext).inflate(i, viewGroup, false);
    }

    private static void checkForTrialCard() {
        Constants.Y = false;
        if (!ap.a().d() && Constants.ap > 0 && Constants.aq > 0 && Constants.ar > 0) {
            String b = d.a().b("PREFERENCE_TRIAL_CARD_RESET_SESSION_TIMESTAMP", "", false);
            if (TextUtils.isEmpty(b)) {
                d.a().b("PREFERENCE_TRIAL_CARD_RESET_SESSION_TIMESTAMP", false);
                d.a().a("PREFERENCE_TRIAL_CARD_RESET_SESSION_TIMESTAMP", Constants.as, false);
            } else if (!(TextUtils.isEmpty(Constants.as) || b.equalsIgnoreCase(Constants.as))) {
                d.a().b("PREFERENCE_TRIAL_CARD_FIRST_SESSION", false);
                d.a().a("PREFERENCE_TRIAL_CARD_FIRST_SESSION", 0, false);
                d.a().b("PREFERENCE_TRIAL_CARD_FIRST_PHASE_SHOWN", false);
                d.a().a("PREFERENCE_TRIAL_CARD_FIRST_PHASE_SHOWN", false, false);
                d.a().b("PREFERENCE_TRIAL_CARD_SESSION_FREQ_SHOWN", false);
                d.a().a("PREFERENCE_TRIAL_CARD_SESSION_FREQ_SHOWN", 0, false);
                d.a().b("PREFERENCE_TRIAL_CARD_RESET_SESSION_TIMESTAMP", false);
                d.a().a("PREFERENCE_TRIAL_CARD_RESET_SESSION_TIMESTAMP", Constants.as, false);
            }
            int b2 = d.a().b("PREFERENCE_TRIAL_CARD_FIRST_SESSION", 0, false);
            boolean b3 = d.a().b("PREFERENCE_TRIAL_CARD_FIRST_PHASE_SHOWN", false, false);
            if (d.a().b("PREFERENCE_TRIAL_CARD_SESSION_FREQ_SHOWN", 0, false) >= Constants.ar) {
                return;
            }
            if (b3) {
                if (GaanaApplication.sessionHistoryCount > b2 && GaanaApplication.sessionHistoryCount - b2 == Constants.aq) {
                    checkEligibleForTrial();
                }
            } else if (b2 == 0 || b2 <= Constants.ap - 1) {
                checkEligibleForTrial();
            }
        }
    }

    private static void checkEligibleForTrial() {
        LoginManager.getInstance().checkTrialAvailability(GaanaApplication.getContext(), new s() {
            /* JADX WARNING: Missing block: B:5:0x0018, code skipped:
            if (r0.getResult().equalsIgnoreCase("Yes") != false) goto L_0x0027;
     */
            public void onRetreivalComplete(com.gaana.models.BusinessObject r6) {
                /*
                r5 = this;
                r0 = r6 instanceof com.gaana.models.BasicResponse;
                r1 = 0;
                if (r0 == 0) goto L_0x001a;
            L_0x0005:
                r0 = r6;
                r0 = (com.gaana.models.BasicResponse) r0;
                r2 = r0.getResult();
                if (r2 == 0) goto L_0x001a;
            L_0x000e:
                r0 = r0.getResult();
                r2 = "Yes";
                r0 = r0.equalsIgnoreCase(r2);
                if (r0 != 0) goto L_0x0027;
            L_0x001a:
                r0 = r6 instanceof com.gaana.models.TrialProductFeature;
                if (r0 == 0) goto L_0x0100;
            L_0x001e:
                r0 = r6;
                r0 = (com.gaana.models.TrialProductFeature) r0;
                r0 = r0.getIs_trial();
                if (r0 == 0) goto L_0x0100;
            L_0x0027:
                r0 = 1;
                com.constants.Constants.Y = r0;
                r2 = r6 instanceof com.gaana.models.TrialProductFeature;
                if (r2 == 0) goto L_0x006b;
            L_0x002e:
                r2 = com.managers.ap.a();
                r2 = r2.d();
                if (r2 != 0) goto L_0x006b;
            L_0x0038:
                r6 = (com.gaana.models.TrialProductFeature) r6;
                r2 = r6.getPg_product();
                if (r2 == 0) goto L_0x005e;
            L_0x0040:
                r2 = r6.getPg_product();
                r2 = r2.getP_id();
                if (r2 == 0) goto L_0x005e;
            L_0x004a:
                r2 = com.managers.u.a();
                r3 = "GaanaPlus Card";
                r4 = "View";
                r6 = r6.getPg_product();
                r6 = r6.getP_id();
                r2.a(r3, r4, r6);
                goto L_0x006b;
            L_0x005e:
                r6 = com.managers.u.a();
                r2 = "GaanaPlus Card";
                r3 = "View";
                r4 = "";
                r6.a(r2, r3, r4);
            L_0x006b:
                r6 = com.constants.Constants.X;
                if (r6 != 0) goto L_0x0102;
            L_0x006f:
                com.constants.Constants.X = r0;
                r6 = com.services.d.a();
                r2 = "PREFERENCE_TRIAL_CARD_FIRST_SESSION";
                r6 = r6.b(r2, r1, r1);
                r2 = com.services.d.a();
                r3 = "PREFERENCE_TRIAL_CARD_FIRST_PHASE_SHOWN";
                r2 = r2.b(r3, r1, r1);
                if (r2 != 0) goto L_0x00c7;
            L_0x0087:
                r2 = com.constants.Constants.ap;
                r2 = r2 - r0;
                if (r6 != r2) goto L_0x00b3;
            L_0x008c:
                r6 = com.services.d.a();
                r2 = "PREFERENCE_TRIAL_CARD_FIRST_PHASE_SHOWN";
                r6.b(r2, r1);
                r6 = com.services.d.a();
                r2 = "PREFERENCE_TRIAL_CARD_FIRST_PHASE_SHOWN";
                r6.a(r2, r0, r1);
                r6 = com.services.d.a();
                r2 = "PREFERENCE_TRIAL_CARD_FIRST_SESSION";
                r6.b(r2, r1);
                r6 = com.services.d.a();
                r2 = "PREFERENCE_TRIAL_CARD_FIRST_SESSION";
                r3 = com.gaana.application.GaanaApplication.sessionHistoryCount;
                r6.a(r2, r3, r1);
                goto L_0x00e2;
            L_0x00b3:
                r2 = com.services.d.a();
                r3 = "PREFERENCE_TRIAL_CARD_FIRST_SESSION";
                r2.b(r3, r1);
                r2 = com.services.d.a();
                r3 = "PREFERENCE_TRIAL_CARD_FIRST_SESSION";
                r6 = r6 + r0;
                r2.a(r3, r6, r1);
                goto L_0x00e2;
            L_0x00c7:
                r2 = com.gaana.application.GaanaApplication.sessionHistoryCount;
                r2 = r2 - r6;
                r6 = com.constants.Constants.aq;
                if (r2 != r6) goto L_0x00e2;
            L_0x00ce:
                r6 = com.services.d.a();
                r2 = "PREFERENCE_TRIAL_CARD_FIRST_SESSION";
                r6.b(r2, r1);
                r6 = com.services.d.a();
                r2 = "PREFERENCE_TRIAL_CARD_FIRST_SESSION";
                r3 = com.gaana.application.GaanaApplication.sessionHistoryCount;
                r6.a(r2, r3, r1);
            L_0x00e2:
                r6 = com.services.d.a();
                r2 = "PREFERENCE_TRIAL_CARD_SESSION_FREQ_SHOWN";
                r6 = r6.b(r2, r1, r1);
                r2 = com.services.d.a();
                r3 = "PREFERENCE_TRIAL_CARD_SESSION_FREQ_SHOWN";
                r2.b(r3, r1);
                r2 = com.services.d.a();
                r3 = "PREFERENCE_TRIAL_CARD_SESSION_FREQ_SHOWN";
                r6 = r6 + r0;
                r2.a(r3, r6, r1);
                goto L_0x0102;
            L_0x0100:
                com.constants.Constants.Y = r1;
            L_0x0102:
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.ImageCardView$AnonymousClass1.onRetreivalComplete(com.gaana.models.BusinessObject):void");
            }

            public void onErrorResponse(BusinessObject businessObject) {
                Constants.Y = false;
            }
        }, "&source=gaanaplus_card");
    }

    public void downloadImage(final ViewHolder viewHolder, final int i) {
        if (this.imageDownloadStatus < 0 || this.mIsRefreshed) {
            this.imageDownloadStatus = 0;
            this.mIsRefreshed = false;
            viewHolder.itemView.setTag(Integer.valueOf(i));
            viewHolder.itemView.setOnClickListener(this);
            if (!TextUtils.isEmpty(this.mDynamicView.d())) {
                i.a().a(this.mDynamicView.d(), new r() {
                    public void onErrorResponse(VolleyError volleyError) {
                    }

                    public void onSuccessfulResponse(final Bitmap bitmap) {
                        if (ImageCardView.this.mDynamicView.m().equals(DynamicViewType.text_card.name()) && (ImageCardView.this.mFragment instanceof DynamicOccasionFragment) && !TextUtils.isEmpty(ImageCardView.this.mDynamicView.l())) {
                            URLManager uRLManager = new URLManager();
                            uRLManager.a(ImageCardView.this.mDynamicView.l());
                            uRLManager.a(TextCardItem.class);
                            i.a().a(new s() {
                                public void onErrorResponse(BusinessObject businessObject) {
                                }

                                public void onRetreivalComplete(BusinessObject businessObject) {
                                    if (businessObject instanceof TextCardItem) {
                                        String message = ((TextCardItem) businessObject).getMessage();
                                        if (!TextUtils.isEmpty(message)) {
                                            ImageCardView.this.updateImage(bitmap, viewHolder, i, message);
                                        }
                                    }
                                }
                            }, uRLManager);
                            return;
                        }
                        ImageCardView.this.updateImage(bitmap, viewHolder, i, "");
                    }
                });
            }
        }
    }

    private void updateImage(Bitmap bitmap, ViewHolder viewHolder, int i, String str) {
        int i2;
        StringBuilder stringBuilder;
        String str2;
        StringBuilder stringBuilder2;
        StringBuilder stringBuilder3;
        View relativeLayout = new RelativeLayout(this.mContext);
        View crossFadeImageView = new CrossFadeImageView(this.mContext);
        crossFadeImageView.setAdjustViewBounds(true);
        crossFadeImageView.setShowLoadingState(true);
        crossFadeImageView.setScaleType(ScaleType.CENTER_CROP);
        int cardHeight = getCardHeight(this.mContext, this.mDynamicView.e());
        int b = d.a().b();
        crossFadeImageView.setPadding(this.mContext.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin), 0, this.mContext.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin), 0);
        crossFadeImageView.setLayoutParams(new LayoutParams(b, cardHeight));
        crossFadeImageView.setImageBitmap(bitmap);
        if (!TextUtils.isEmpty(str)) {
            TextView textView = new TextView(this.mContext);
            textView.setText(str);
            textView.setSingleLine(false);
            textView.setMaxLines(4);
            if (Constants.l) {
                textView.setTextColor(getResources().getColor(R.color.black));
            } else {
                textView.setTextColor(getResources().getColor(R.color.white));
            }
            textView.setLineSpacing(TypedValue.applyDimension(0, 9.0f, getResources().getDisplayMetrics()), 1.0f);
            if (com.utilities.d.j()) {
                textView.setLetterSpacing(0.08f);
            }
            textView.setTextSize(0, getResources().getDimension(R.dimen.citrus_textsize));
            textView.setGravity(17);
            int i3 = b / 8;
            int measuredHeight = crossFadeImageView.getMeasuredHeight() / 6;
            textView.setEllipsize(TruncateAt.END);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(i3, measuredHeight, i3, measuredHeight);
            layoutParams.addRule(13);
            textView.setLayoutParams(layoutParams);
            relativeLayout.addView(crossFadeImageView);
            relativeLayout.addView(textView);
        }
        ImageCardViewHolder imageCardViewHolder = (ImageCardViewHolder) viewHolder;
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) imageCardViewHolder.itemView.getLayoutParams();
        layoutParams2.height = cardHeight;
        layoutParams2.width = b;
        layoutParams2.bottomMargin = (int) this.mContext.getResources().getDimension(R.dimen.bw_section_vert_padding_half);
        layoutParams2.topMargin = (int) this.mContext.getResources().getDimension(R.dimen.bw_section_vert_padding_half);
        imageCardViewHolder.llImgParentLayout.setLayoutParams(layoutParams2);
        imageCardViewHolder.llImgParentLayout.removeAllViews();
        imageCardViewHolder.llImgParentLayout.setBackgroundColor(0);
        if (this.mDynamicView.f() != ACTION_TYPE.FB_LOGIN_CARD.getNumVal()) {
            LinearLayout linearLayout = imageCardViewHolder.llImgParentLayout;
            if (TextUtils.isEmpty(str)) {
                relativeLayout = crossFadeImageView;
            }
            linearLayout.addView(relativeLayout);
        } else if (GaanaApplication.getInstance().getCurrentUser() == null || !GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
            imageCardViewHolder.llImgParentLayout.addView(crossFadeImageView);
        } else {
            RecyclerView.LayoutParams layoutParams3 = (RecyclerView.LayoutParams) imageCardViewHolder.itemView.getLayoutParams();
            layoutParams3.height = 0;
            layoutParams3.width = 0;
            layoutParams3.bottomMargin = 0;
            layoutParams3.topMargin = 0;
            imageCardViewHolder.llImgParentLayout.setLayoutParams(layoutParams2);
        }
        String f = DynamicViewManager.a().f();
        String j = c.a().j();
        if ((this.mFragment instanceof DynamicHomeFragment) && !TextUtils.isEmpty(f)) {
            i2 = i + 1;
            if (f.substring(i, i2).equalsIgnoreCase("1")) {
                u a;
                if (this.mDynamicView.l() != null && this.mDynamicView.l().contains("occasion")) {
                    j = this.mDynamicView.l();
                    j = j.substring(j.lastIndexOf("/") + 1, j.length());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(f.substring(0, i));
                    stringBuilder.append('0');
                    stringBuilder.append(f.substring(i2));
                    DynamicViewManager.a().a(stringBuilder.toString());
                    a = u.a();
                    str2 = ((BaseActivity) this.mContext).currentScreen;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(j);
                    stringBuilder2.append("_impression");
                    a.b(str2, stringBuilder2.toString());
                    return;
                } else if (this.mDynamicView.l() != null && this.mDynamicView.l().contains("persona")) {
                    j = this.mDynamicView.l();
                    j = j.substring(j.lastIndexOf("/") + 1, j.length());
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(f.substring(0, i));
                    stringBuilder.append('0');
                    stringBuilder.append(f.substring(i2));
                    DynamicViewManager.a().a(stringBuilder.toString());
                    a = u.a();
                    str2 = ((BaseActivity) this.mContext).currentScreen;
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(j);
                    stringBuilder2.append("_impression");
                    a.b(str2, stringBuilder2.toString());
                    return;
                } else if (this.mDynamicView.l() != null && this.mDynamicView.l().contains(LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK)) {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(f.substring(0, i));
                    stringBuilder3.append('0');
                    stringBuilder3.append(f.substring(i2));
                    DynamicViewManager.a().a(stringBuilder3.toString());
                    u.a().a(((BaseActivity) this.mContext).currentScreen, "Home_FB_Live_impression", this.mDynamicView.p());
                    return;
                } else {
                    return;
                }
            }
        }
        if ((this.mFragment instanceof DynamicOccasionFragment) && !TextUtils.isEmpty(j)) {
            i2 = i + 1;
            if (!j.substring(i, i2).equalsIgnoreCase("1")) {
                return;
            }
            u a2;
            if (this.mDynamicView.l() != null && this.mDynamicView.l().contains("occasion") && this.mDynamicView.f() == ACTION_TYPE.OCCASSION.getNumVal()) {
                f = this.mDynamicView.l();
                f = f.substring(f.lastIndexOf("/") + 1, f.length());
                stringBuilder = new StringBuilder();
                stringBuilder.append(j.substring(0, i));
                stringBuilder.append('0');
                stringBuilder.append(j.substring(i2));
                c.a().a(stringBuilder.toString());
                a2 = u.a();
                str2 = ((BaseActivity) this.mContext).currentScreen;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(f);
                stringBuilder2.append("_impression");
                a2.b(str2, stringBuilder2.toString());
            } else if (this.mDynamicView.l() != null && this.mDynamicView.l().contains("persona") && this.mDynamicView.f() == ACTION_TYPE.DEDICATION.getNumVal()) {
                f = this.mDynamicView.l();
                f = f.substring(f.lastIndexOf("/") + 1, f.length());
                stringBuilder = new StringBuilder();
                stringBuilder.append(j.substring(0, i));
                stringBuilder.append('0');
                stringBuilder.append(j.substring(i2));
                c.a().a(stringBuilder.toString());
                a2 = u.a();
                str2 = ((BaseActivity) this.mContext).currentScreen;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(f);
                stringBuilder2.append("_impression");
                a2.b(str2, stringBuilder2.toString());
            } else if (this.mDynamicView.l() != null && this.mDynamicView.l().contains(LoginManager.SSO_SOCIAL_LOGIN_TYPE_FACEBOOK) && this.mDynamicView.f() == ACTION_TYPE.FB_LIVE.getNumVal()) {
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(f.substring(0, i));
                stringBuilder3.append('0');
                stringBuilder3.append(f.substring(i2));
                DynamicViewManager.a().a(stringBuilder3.toString());
                u.a().a(((BaseActivity) this.mContext).currentScreen, "Occasion_FB_Live_impression", this.mDynamicView.p());
            }
        }
    }

    public void downloadImage(final ViewHolder viewHolder, final int i, String str) {
        if (this.imageDownloadStatus < 0 || this.mIsRefreshed) {
            this.imageDownloadStatus = 0;
            this.mIsRefreshed = false;
            viewHolder.itemView.setTag(Integer.valueOf(i));
            viewHolder.itemView.setOnClickListener(this);
            if (!TextUtils.isEmpty(str)) {
                i.a().a(str, new r() {
                    public void onErrorResponse(VolleyError volleyError) {
                    }

                    public void onSuccessfulResponse(Bitmap bitmap) {
                        CrossFadeImageView crossFadeImageView = new CrossFadeImageView(ImageCardView.this.mContext);
                        crossFadeImageView.setAdjustViewBounds(true);
                        crossFadeImageView.setShowLoadingState(true);
                        crossFadeImageView.setScaleType(ScaleType.CENTER_CROP);
                        int cardHeight = ImageCardView.getCardHeight(ImageCardView.this.mContext, ImageCardView.this.mDynamicView.e());
                        int b = d.a().b();
                        crossFadeImageView.setPadding(ImageCardView.this.mContext.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin), 0, ImageCardView.this.mContext.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin), 0);
                        crossFadeImageView.setLayoutParams(new LayoutParams(b, cardHeight));
                        crossFadeImageView.setImageBitmap(bitmap);
                        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) ((ImageCardViewHolder) viewHolder).itemView.getLayoutParams();
                        layoutParams.height = cardHeight;
                        layoutParams.width = b;
                        layoutParams.bottomMargin = (int) ImageCardView.this.mContext.getResources().getDimension(R.dimen.bw_section_vert_padding_half);
                        layoutParams.topMargin = (int) ImageCardView.this.mContext.getResources().getDimension(R.dimen.bw_section_vert_padding_half);
                        ((ImageCardViewHolder) viewHolder).llImgParentLayout.setLayoutParams(layoutParams);
                        ((ImageCardViewHolder) viewHolder).llImgParentLayout.removeAllViews();
                        ((ImageCardViewHolder) viewHolder).llImgParentLayout.setBackgroundColor(0);
                        ((ImageCardViewHolder) viewHolder).llImgParentLayout.addView(crossFadeImageView);
                        String f = DynamicViewManager.a().f();
                        String j = c.a().j();
                        StringBuilder stringBuilder;
                        StringBuilder stringBuilder2;
                        String stringBuilder3;
                        if ((ImageCardView.this.mFragment instanceof DynamicHomeFragment) && !TextUtils.isEmpty(f) && f.substring(i, i + 1).equalsIgnoreCase("1")) {
                            u a;
                            if (ImageCardView.this.mDynamicView.l() != null && ImageCardView.this.mDynamicView.l().contains("occasion")) {
                                j = ImageCardView.this.mDynamicView.l();
                                j = j.substring(j.lastIndexOf("/") + 1, j.length());
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(f.substring(0, i));
                                stringBuilder.append('0');
                                stringBuilder.append(f.substring(i + 1));
                                DynamicViewManager.a().a(stringBuilder.toString());
                                a = u.a();
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("OP_");
                                stringBuilder2.append(((BaseActivity) ImageCardView.this.mContext).currentScreen);
                                stringBuilder3 = stringBuilder2.toString();
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(j);
                                stringBuilder.append("_impression");
                                a.b(stringBuilder3, stringBuilder.toString());
                            } else if (ImageCardView.this.mDynamicView.l() != null && ImageCardView.this.mDynamicView.l().contains("persona")) {
                                j = ImageCardView.this.mDynamicView.l();
                                j = j.substring(j.lastIndexOf("/") + 1, j.length());
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(f.substring(0, i));
                                stringBuilder.append('0');
                                stringBuilder.append(f.substring(i + 1));
                                DynamicViewManager.a().a(stringBuilder.toString());
                                a = u.a();
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("OP_");
                                stringBuilder2.append(((BaseActivity) ImageCardView.this.mContext).currentScreen);
                                stringBuilder3 = stringBuilder2.toString();
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(j);
                                stringBuilder.append("_impression");
                                a.b(stringBuilder3, stringBuilder.toString());
                            }
                        } else if (!(ImageCardView.this.mFragment instanceof DynamicOccasionFragment) || TextUtils.isEmpty(j) || !j.substring(i, i + 1).equalsIgnoreCase("1")) {
                        } else {
                            u a2;
                            if (ImageCardView.this.mDynamicView.l() != null && ImageCardView.this.mDynamicView.l().contains("occasion") && ImageCardView.this.mDynamicView.f() == ACTION_TYPE.OCCASSION.getNumVal()) {
                                f = ImageCardView.this.mDynamicView.l();
                                f = f.substring(f.lastIndexOf("/") + 1, f.length());
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(j.substring(0, i));
                                stringBuilder.append('0');
                                stringBuilder.append(j.substring(i + 1));
                                c.a().a(stringBuilder.toString());
                                a2 = u.a();
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("OP_");
                                stringBuilder2.append(((BaseActivity) ImageCardView.this.mContext).currentScreen);
                                stringBuilder3 = stringBuilder2.toString();
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(f);
                                stringBuilder.append("_impression");
                                a2.b(stringBuilder3, stringBuilder.toString());
                            } else if (ImageCardView.this.mDynamicView.l() != null && ImageCardView.this.mDynamicView.l().contains("persona") && ImageCardView.this.mDynamicView.f() == ACTION_TYPE.DEDICATION.getNumVal()) {
                                f = ImageCardView.this.mDynamicView.l();
                                f = f.substring(f.lastIndexOf("/") + 1, f.length());
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(j.substring(0, i));
                                stringBuilder.append('0');
                                stringBuilder.append(j.substring(i + 1));
                                c.a().a(stringBuilder.toString());
                                a2 = u.a();
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("OP_");
                                stringBuilder2.append(((BaseActivity) ImageCardView.this.mContext).currentScreen);
                                stringBuilder3 = stringBuilder2.toString();
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(f);
                                stringBuilder.append("_impression");
                                a2.b(stringBuilder3, stringBuilder.toString());
                            }
                        }
                    }
                });
            }
        }
    }

    public a getDynamicView() {
        return this.mDynamicView;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        int f = this.mDynamicView.f();
        this.isViewDestroyed = false;
        if (f == ACTION_TYPE.SOCIAL_FEED.getNumVal() && !this.mAppState.getCurrentUser().isSocialEnabled()) {
            ((ImageCardViewHolder) viewHolder).llImgParentLayout.setVisibility(8);
        } else if (f == ACTION_TYPE.TRIAL_CARD.getNumVal() && (ap.a().d() || !Constants.Y)) {
            ImageCardViewHolder imageCardViewHolder = (ImageCardViewHolder) viewHolder;
            imageCardViewHolder.llImgParentLayout.removeAllViews();
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) imageCardViewHolder.itemView.getLayoutParams();
            layoutParams.width = 0;
            layoutParams.height = 0;
            imageCardViewHolder.llImgParentLayout.setLayoutParams(layoutParams);
            imageCardViewHolder.itemView.setVisibility(8);
            imageCardViewHolder.llImgParentLayout.setVisibility(8);
        } else if (viewHolder instanceof ImageCardViewHolder) {
            ImageCardViewHolder imageCardViewHolder2 = (ImageCardViewHolder) viewHolder;
            imageCardViewHolder2.itemView.setVisibility(0);
            imageCardViewHolder2.llImgParentLayout.setVisibility(0);
            downloadImage(viewHolder, i);
        }
        if (f == ACTION_TYPE.DEEPLINK.getNumVal()) {
            String a = Util.a(this.mDynamicView.j());
            if (!TextUtils.isEmpty(a)) {
                u.a().a(a, "View", this.mDynamicView.k());
            }
        }
        return null;
    }

    public void setIsToBeRefreshed(boolean z) {
        this.mIsRefreshed = z;
    }

    public void setPositionToBeRefreshed(int i) {
        if (this.mFragment != null) {
            this.mFragment.notifyItemChanged(i);
        }
    }

    public void onClick(View view) {
        String h;
        final int intValue = ((Integer) view.getTag()).intValue();
        int f = this.mDynamicView.f();
        if (this.mFragment instanceof DynamicOccasionFragment) {
            h = c.a().h();
            if (!TextUtils.isEmpty(h)) {
                u a = u.a();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("OP_");
                stringBuilder.append(h);
                h = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append(f);
                stringBuilder.append("_Click");
                a.b(h, stringBuilder.toString());
            }
        }
        String a2;
        StringBuilder stringBuilder2;
        if (f == ACTION_TYPE.DEEPLINK.getNumVal()) {
            Util.a(this.mDynamicView.l(), getMandatoryLogin(this.mDynamicView.j()), getInAppWeb(this.mDynamicView.j()), this.mContext);
            a2 = Util.a(this.mDynamicView.j());
            if (!TextUtils.isEmpty(a2)) {
                u.a().a(a2, "Click", this.mDynamicView.k());
            }
            if (this.mDynamicView.l() != null && this.mDynamicView.l().contains("voiceassistant")) {
                u.a().b("VoiceInteraction", "VoiceCard_Click");
            }
        } else if (f == ACTION_TYPE.DEDICATION.getNumVal()) {
            if (this.mDynamicView.l() != null && this.mDynamicView.l().contains("personas")) {
                a2 = this.mDynamicView.l();
                a2 = a2.substring(a2.lastIndexOf("/") + 1, a2.length());
                u a3 = u.a();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("OP_");
                stringBuilder2.append(((BaseActivity) this.mContext).currentScreen);
                h = stringBuilder2.toString();
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(a2);
                stringBuilder3.append("_Click");
                a3.b(h, stringBuilder3.toString());
            }
            BaseGaanaFragment personaDedicationFragment = new PersonaDedicationFragment();
            try {
                if (!TextUtils.isEmpty(this.mDynamicView.q())) {
                    e.q = Long.parseLong(this.mDynamicView.q());
                }
            } catch (Exception unused) {
            }
            ((GaanaActivity) this.mContext).displayFragment(personaDedicationFragment);
        } else if (f == ACTION_TYPE.ONE_TOUCH_RADIO.getNumVal()) {
            playRadio();
        } else if (f == ACTION_TYPE.ONE_TOUCH_DIALOG.getNumVal()) {
            handleOneTouchRadio();
        } else if (f == ACTION_TYPE.JUKE_LANDING_PAGE.getNumVal()) {
            handleJukeLandingPage();
        } else if (f == ACTION_TYPE.TRIAL_CARD.getNumVal()) {
            u.a().b("Trial activation card", "Attempted");
            Util.b(this.mContext, "Trial_card", new as() {
                public void onTrialSuccess() {
                    if (ImageCardView.this.mFragment != null && ImageCardView.this.mContext != null) {
                        ImageCardView.this.mFragment.notifyItemRemoved(intValue);
                    }
                }
            });
        } else if (f == ACTION_TYPE.OCCASSION.getNumVal()) {
            if (this.mDynamicView.l() != null && this.mDynamicView.l().contains("occasion")) {
                a2 = this.mDynamicView.l();
                String substring = a2.substring(a2.lastIndexOf("/") + 1, a2.length());
                u a4 = u.a();
                String str = ((BaseActivity) this.mContext).currentScreen;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(substring);
                stringBuilder2.append("_Click");
                a4.b(str, stringBuilder2.toString());
                an.a().a("click", "en", this.mDynamicView.y(), an.a().a(an.a().a), substring, "oc", "", "");
            }
            fetchOccasionData(this.mDynamicView.l(), this.mDynamicView.g());
        } else if (f != ACTION_TYPE.HEADER.getNumVal()) {
            if (f == ACTION_TYPE.CREATE_PLAYLIST.getNumVal()) {
                createNewPlaylist();
            } else if (f == ACTION_TYPE.SOCIAL_FEED.getNumVal()) {
                displaySocialFragment();
            } else if (f == ACTION_TYPE.FB_LOGIN_CARD.getNumVal()) {
                LoginManager.getInstance().login((Activity) this.mContext, LoginType.FB, new IOnLoginCompleted() {
                    public void onLoginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle) {
                        if (login_status == LOGIN_STATUS.LOGIN_SUCCEDED) {
                            if (ImageCardView.this.mContext != null && !((Activity) ImageCardView.this.mContext).isFinishing()) {
                                ((GaanaActivity) ImageCardView.this.mContext).updateSidebarUserDetails();
                                ImageCardView.this.displaySocialFragment();
                            }
                        } else if (login_status == LOGIN_STATUS.LOGIN_EMAIL_MISSING_FB) {
                            if (ImageCardView.this.mContext != null && !((Activity) ImageCardView.this.mContext).isFinishing()) {
                                FbLoginErrorDialog fbLoginErrorDialog = new FbLoginErrorDialog(ImageCardView.this.mContext);
                                fbLoginErrorDialog.setOnLoginCompletedListener(this);
                                fbLoginErrorDialog.show();
                            }
                        } else if (login_status == LOGIN_STATUS.LOGIN_MANDATORY_FIELD_MISSING && ImageCardView.this.mContext != null && !((Activity) ImageCardView.this.mContext).isFinishing()) {
                            new f(ImageCardView.this.mContext).a(ImageCardView.this.mContext.getResources().getString(R.string.mandatory_field_missing));
                        }
                    }
                });
            } else if (f == ACTION_TYPE.SHARE_CARD.getNumVal()) {
                if (this.mFragment instanceof DynamicOccasionFragment) {
                    ((DynamicOccasionFragment) this.mFragment).a(false);
                }
            } else if (f == ACTION_TYPE.FB_LIVE.getNumVal()) {
                Map j = this.mDynamicView.j();
                if (j == null || j.size() <= 0) {
                    a2 = this.mDynamicView.l();
                    if (!TextUtils.isEmpty(a2) && a2.contains("facebook.com")) {
                        openWebViewActivity(a2, "Fb Live");
                    }
                } else if (j.containsKey("fb_live_url")) {
                    a2 = (String) j.get("fb_live_url");
                    if (!TextUtils.isEmpty(a2) && a2.contains("facebook.com")) {
                        openWebViewActivity(a2, "Fb Live");
                    }
                }
            } else if (f == ACTION_TYPE.THEME_SETTINGS.getNumVal()) {
                u.a().b("Browse", "appthemecardclick");
                Bundle bundle = new Bundle();
                bundle.putInt("KEY_SETTINGS", 141);
                BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                settingsDetailFragment.setArguments(bundle);
                ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
            }
        }
    }

    private String getMandatoryLogin(Map<String, String> map) {
        if (!map.containsKey("login_flag")) {
            return null;
        }
        String str = (String) map.get("login_flag");
        return str.contains(".") ? str.split("\\.")[0] : str;
    }

    private String getInAppWeb(Map<String, String> map) {
        if (!map.containsKey("app_url_view")) {
            return null;
        }
        String str = (String) map.get("app_url_view");
        return str.contains(".") ? str.split("\\.")[0] : str;
    }

    private void openWebViewActivity(String str, String str2) {
        if (!Util.j(this.mFragment.getActivity()) || this.mAppState.isAppInOfflineMode()) {
            ap.a().f(this.mContext);
        } else if (!TextUtils.isEmpty(str) && URLUtil.isValidUrl(str)) {
            String str3 = "Home";
            if (this.mFragment instanceof DynamicOccasionFragment) {
                str3 = "Occasion";
            }
            u a = u.a();
            if (!TextUtils.isEmpty(this.mDynamicView.p())) {
                str3 = this.mDynamicView.p();
            }
            a.b(str3, "FB live Clicked");
            Intent intent = new Intent(this.mContext, WebViewActivity.class);
            intent.putExtra("EXTRA_WEBVIEW_URL", str);
            intent.putExtra("EXTRA_SHOW_ACTIONBAR", true);
            intent.putExtra("EXTRA_SHOW_ACTIONBAR2", true);
            intent.putExtra("title", str2);
            this.mContext.startActivity(intent);
        }
    }

    private void playRadio() {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getResources().getString(R.string.error_msg_feature_not_available_offline_prefix));
        } else if (!Util.j(this.mContext)) {
            ap.a().f(this.mContext);
        } else if (Constants.cY) {
            JukeSessionManager.getErrorDialog(this.mContext, 0, new OnButtonClickListener() {
                public void onNegativeButtonClick() {
                }

                public void onPositiveButtonClick() {
                    JukeSessionManager.getInstance().stopJukeSession(new s() {
                        public void onErrorResponse(BusinessObject businessObject) {
                        }

                        public void onRetreivalComplete(BusinessObject businessObject) {
                            if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                                ImageCardView.this.playRadio();
                            }
                        }
                    });
                }
            });
        } else {
            String str = "0";
            if (this.mAppState.getCurrentUser().getLoginStatus() && this.mAppState.getCurrentUser().getUserProfile() != null) {
                str = this.mAppState.getCurrentUser().getUserProfile().getUserId();
            }
            String l = this.mDynamicView.l();
            BusinessObject businessObject = new BusinessObject();
            businessObject.setBusinessObjId(str);
            if (this.mFragment instanceof DynamicOccasionFragment) {
                str = c.a().h();
                businessObject.setName(str);
                GaanaApplication gaanaApplication = this.mAppState;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("_");
                stringBuilder.append(this.mDynamicView.p());
                gaanaApplication.setPlayoutSectionName(stringBuilder.toString());
            }
            ad.a(this.mContext).a(l, SOURCE_TYPE.ONE_TOUCH_RADIO.ordinal(), businessObject);
        }
    }

    private void fetchOccasionData(final String str, final String str2) {
        if (!Util.j(this.mContext) || this.mAppState.isAppInOfflineMode()) {
            aj.a().a(this.mContext, getResources().getString(R.string.error_download_no_internet));
        } else {
            c.a().a(new ag() {
                public void onOccasionResponse() {
                    ImageCardView.this.mIsRefreshed = false;
                    BaseGaanaFragment dynamicOccasionFragment = new DynamicOccasionFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("OCCASION_URL", str);
                    bundle.putString("OCCASION_REFRESH_INTERVAL", str2);
                    dynamicOccasionFragment.setArguments(bundle);
                    ((GaanaActivity) ImageCardView.this.mContext).displayFragment(dynamicOccasionFragment);
                }

                public void onOccasionError() {
                    aj.a().a(ImageCardView.this.mContext, ImageCardView.this.getResources().getString(R.string.error_download_no_internet));
                }
            }, str, str2, this.mIsRefreshed);
        }
    }

    public String getDeeplinkUrl() {
        String str = null;
        if (!Util.j(this.mFragment.getActivity()) || GaanaApplication.getInstance().isAppInOfflineMode()) {
            ap.a().f(this.mContext);
            return null;
        }
        if (this.mDynamicView.j() != null && this.mDynamicView.j().containsKey("web_link")) {
            str = (String) this.mDynamicView.j().get("web_link");
        }
        return str;
    }

    public void createNewPlaylist() {
        u.a().b("Playlist", "Create Playlist");
        ((BaseActivity) this.mContext).checkSetLoginStatus(new l.ad() {
            public void onLoginSuccess() {
                ((GaanaActivity) ImageCardView.this.mContext).displayFragment(CreateNewPlaylistFragment.a("", true));
            }
        }, this.mContext.getResources().getString(R.string.LOGIN_LAUNCHED_FOR_ADD_TO_PLAYLIST));
    }

    public void handleOneTouchRadio() {
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getResources().getString(R.string.error_msg_feature_not_available_offline_prefix));
        } else if (Util.j(this.mContext)) {
            ((BaseActivity) this.mContext).sendGAEvent("RadioScreen", "One Touch Radio", "RadioScreen - One Touch Radio");
            an.a().a("click", "en", this.mDynamicView.y(), an.a().a(an.a().a), "", BusinessObjectType.RadioMoods.toString(), "", "");
            this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.ONE_TOUCH.name());
            URLManager uRLManager = new URLManager();
            uRLManager.a("https://api.gaana.com/home/one-touch-moods");
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.a(BusinessObjectType.RadioMoods);
            if (this.isFirstCall) {
                this.isFirstCall = false;
                createLoadingView(getContext().getString(R.string.starting_one_touch_radio), this.mContext);
                i.a().a(new s() {
                    public void onRetreivalComplete(BusinessObject businessObject) {
                        ImageCardView.this.dismissDialog();
                        if (!ImageCardView.this.isViewDestroyed && businessObject != null) {
                            ImageCardView.this.isFirstCall = true;
                            RadioMoods radioMoods = (RadioMoods) businessObject;
                            if (radioMoods.getArrListItem() != null && radioMoods.getArrListItem().size() > 0) {
                                PopupWindowView.getInstance(ImageCardView.this.mContext, ImageCardView.this.mFragment).contextOneTouchPopup(radioMoods);
                            }
                        }
                    }

                    public void onErrorResponse(BusinessObject businessObject) {
                        ImageCardView.this.dismissDialog();
                        ImageCardView.this.isFirstCall = true;
                        if (!ImageCardView.this.isViewDestroyed) {
                            aj.a().a(ImageCardView.this.mContext, ImageCardView.this.mContext.getResources().getString(R.string.err_retry));
                        }
                    }
                }, uRLManager);
            }
        } else {
            ap.a().f(this.mContext);
        }
    }

    public void handleJukeLandingPage() {
        u.a().a("PartyHub", "Entry", "Card");
        ((GaanaActivity) this.mContext).changeFragment(R.id.LeftPartyHub, null, null);
    }

    private void createLoadingView(String str, Context context) {
        if (context != null) {
            try {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setLayoutParams(new LayoutParams(-2, -2));
                LayoutInflater.from(context).inflate(R.layout.view_loading_radio, linearLayout, true);
                if (str != null) {
                    ((TextView) linearLayout.findViewById(R.id.tvTrackName)).setText(str);
                } else {
                    ((TextView) linearLayout.findViewById(R.id.tvTrackName)).setText(this.mContext.getResources().getString(R.string.loading));
                }
                if (this.mDialog != null && this.mDialog.isShowing()) {
                    this.mDialog.dismiss();
                    this.mDialog = null;
                }
                this.mDialog = new Dialog(context, R.style.dialog_transparent_bg);
                this.mDialog.setContentView(linearLayout);
                this.mDialog.setCancelable(true);
                this.mDialog.show();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    private void dismissDialog() {
        try {
            if (this.mDialog != null && this.mDialog.isShowing()) {
                this.mDialog.dismiss();
                this.mDialog = null;
            }
        } catch (Exception unused) {
        }
    }

    public void setFirstCall(boolean z) {
        this.isFirstCall = z;
    }

    public static int getCardHeight(Context context, int i) {
        if (i == VIEW_SIZE.CARD_SMALL.getNumVal()) {
            return context.getResources().getDimensionPixelSize(R.dimen.img_card_height_small);
        }
        if (i == VIEW_SIZE.CARD_MEDIUM.getNumVal()) {
            return context.getResources().getDimensionPixelSize(R.dimen.img_card_height_medium);
        }
        if (i == VIEW_SIZE.CARD_LARGE.getNumVal()) {
            return context.getResources().getDimensionPixelSize(R.dimen.img_card_height_large);
        }
        if (i == VIEW_SIZE.CARD_LARGE_NEW.getNumVal()) {
            return context.getResources().getDimensionPixelSize(R.dimen.img_card_height_large_new);
        }
        if (i == VIEW_SIZE.CARD_LARGE_XL.getNumVal()) {
            return context.getResources().getDimensionPixelSize(R.dimen.img_card_height_xl);
        }
        if (i == VIEW_SIZE.CARD_LARGE_XXL.getNumVal()) {
            return context.getResources().getDimensionPixelSize(R.dimen.img_card_height_xxl);
        }
        int numVal = VIEW_SIZE.CARD_SOCIAL.getNumVal();
        return -2;
    }
}
