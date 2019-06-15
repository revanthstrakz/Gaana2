package com.gaana.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.constants.Constants;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.fragments.RadioDetailsMaterialListing;
import com.gaana.R;
import com.gaana.ads.base.ILifeCycleAwareCustomView;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.managers.ColombiaManager.ADSTATUS;
import com.managers.ap;

public class UpgradeHomeView extends BaseItemView implements OnClickListener {
    private int adPosition = -1;
    private int adServer = -1;
    private long adUnitCode = -1;
    private ADSTATUS adstatus;
    private String dfpAdCode = null;
    private PublisherAdView dfpAdView = null;
    private ILifeCycleAwareCustomView lifeCycleAwareAdView;
    private a mDynamicView;
    private BaseGaanaFragment mFragment;
    private int madiation = -1;
    private String sectionName;

    public UpgradeHomeView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mFragment = baseGaanaFragment;
    }

    public UpgradeHomeView(Context context, BaseGaanaFragment baseGaanaFragment, String str) {
        super(context, baseGaanaFragment);
        this.mFragment = baseGaanaFragment;
        this.sectionName = str;
    }

    public UpgradeHomeView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mFragment = baseGaanaFragment;
        this.mDynamicView = aVar;
    }

    public UpgradeHomeView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar, String str) {
        super(context, baseGaanaFragment);
        this.mFragment = baseGaanaFragment;
        this.mDynamicView = aVar;
        this.sectionName = str;
    }

    public long getAdUnitCode() {
        return this.adUnitCode;
    }

    public void setAdUnitCode(long j) {
        this.adUnitCode = j;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (ap.a().b(this.mContext)) {
            return new ItemAdViewHolder(getNewView(0, viewGroup));
        }
        return new ItemAdViewHolder(getEmptyLayout());
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        if (!(this.mFragment instanceof RadioDetailsMaterialListing)) {
            return LayoutInflater.from(this.mContext).inflate(R.layout.view_native_ad_dfp_colombia, viewGroup, false);
        }
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.view_native_radio_ad, viewGroup, false);
        if (!Constants.l) {
            return inflate;
        }
        inflate.setBackgroundColor(this.mContext.getResources().getColor(R.color.white));
        return inflate;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (ap.a().b(this.mContext)) {
            return getPopulatedView(i, viewHolder.itemView, viewGroup, null);
        }
        viewHolder.itemView.setVisibility(8);
        return viewHolder.itemView;
    }

    public a getDynamicView() {
        return this.mDynamicView;
    }

    private boolean shouldGetFreshAd(int i) {
        return this.adstatus == null || this.adstatus == ADSTATUS.FAILED || this.adstatus == ADSTATUS.REFRESH;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0080  */
    public android.view.View getPopulatedView(int r28, android.view.View r29, android.view.ViewGroup r30, com.gaana.models.BusinessObject r31) {
        /*
        r27 = this;
        r0 = r27;
        r1 = r28;
        r2 = r29;
        r3 = r30;
        r4 = r31;
        r5 = com.gaana.application.GaanaApplication.getInstance();
        r5.updateMetadata();
        r0.adPosition = r1;
        r5 = r27.shouldGetFreshAd(r28);
        r6 = 2131297587; // 0x7f090533 float:1.8213123E38 double:1.053000919E-314;
        r7 = 0;
        if (r5 == 0) goto L_0x0344;
    L_0x001d:
        r5 = r0.mFragment;
        r5 = r5.getActivity();
        if (r5 == 0) goto L_0x0034;
    L_0x0025:
        r5 = r0.mFragment;
        r5 = r5.getActivity();
        r5 = (com.gaana.GaanaActivity) r5;
        r5 = r5.isSlidingPanelExpanded();
        if (r5 == 0) goto L_0x0034;
    L_0x0033:
        return r2;
    L_0x0034:
        r5 = 1;
        if (r4 == 0) goto L_0x0087;
    L_0x0037:
        r8 = r4 instanceof com.gaana.models.Radios.Radio;
        if (r8 == 0) goto L_0x0087;
    L_0x003b:
        r8 = r4;
        r8 = (com.gaana.models.Radios.Radio) r8;
        r9 = r8.getType();
        r10 = com.constants.c.d.c;
        r9 = r9.equalsIgnoreCase(r10);
        if (r9 == 0) goto L_0x0068;
    L_0x004a:
        r9 = "GAANA_MIRCHI_320x100";
        r0.sectionName = r9;
        r9 = 5;
        r10 = com.managers.e.ai;
        r0.adServer = r10;
        r10 = com.managers.e.ak;
        r0.madiation = r10;
        r10 = com.managers.e.E;
        r0.dfpAdCode = r10;
        r10 = r8.getAdCompaignPosition();
        if (r10 <= 0) goto L_0x0066;
    L_0x0061:
        r10 = r8.getAdCompaignPosition();
        goto L_0x007a;
    L_0x0066:
        r10 = r5;
        goto L_0x007a;
    L_0x0068:
        r9 = "GAANA_RADIO_320x100";
        r0.sectionName = r9;
        r9 = 6;
        r10 = com.managers.e.ai;
        r0.adServer = r10;
        r10 = com.managers.e.ak;
        r0.madiation = r10;
        r10 = com.managers.e.G;
        r0.dfpAdCode = r10;
        goto L_0x0066;
    L_0x007a:
        r8 = r8.getGaana_ad();
        if (r8 != r5) goto L_0x0084;
    L_0x0080:
        r8 = com.managers.e.H;
        r0.dfpAdCode = r8;
    L_0x0084:
        r13 = r9;
        r11 = r10;
        goto L_0x0096;
    L_0x0087:
        r9 = 4;
        r8 = com.managers.e.ag;
        r0.adServer = r8;
        r8 = com.managers.e.ah;
        r0.madiation = r8;
        r8 = com.managers.e.D;
        r0.dfpAdCode = r8;
        r11 = r5;
        r13 = r9;
    L_0x0096:
        r8 = com.managers.ColombiaManager.ADSTATUS.LOADING;
        r0.adstatus = r8;
        r6 = r2.findViewById(r6);
        if (r6 != 0) goto L_0x00d5;
    L_0x00a0:
        r2 = r0.mFragment;
        r2 = r2 instanceof com.fragments.RadioDetailsMaterialListing;
        if (r2 == 0) goto L_0x00c8;
    L_0x00a6:
        r2 = r0.mContext;
        r2 = android.view.LayoutInflater.from(r2);
        r6 = 2131493609; // 0x7f0c02e9 float:1.8610703E38 double:1.0530977665E-314;
        r2 = r2.inflate(r6, r3, r7);
        r3 = com.constants.Constants.l;
        if (r3 == 0) goto L_0x00d5;
    L_0x00b7:
        r3 = r0.mContext;
        r3 = r3.getResources();
        r6 = 2131100202; // 0x7f06022a float:1.7812779E38 double:1.0529033977E-314;
        r3 = r3.getColor(r6);
        r2.setBackgroundColor(r3);
        goto L_0x00d5;
    L_0x00c8:
        r2 = r0.mContext;
        r2 = android.view.LayoutInflater.from(r2);
        r6 = 2131493607; // 0x7f0c02e7 float:1.8610699E38 double:1.0530977655E-314;
        r2 = r2.inflate(r6, r3, r7);
    L_0x00d5:
        r3 = r0.mDynamicView;
        if (r3 == 0) goto L_0x02ce;
    L_0x00d9:
        r3 = r0.mDynamicView;
        r3 = r3.w();
        r3 = android.text.TextUtils.isEmpty(r3);
        if (r3 != 0) goto L_0x02ce;
    L_0x00e5:
        r3 = r0.mDynamicView;
        r3 = r3.q();
        r3 = android.text.TextUtils.isEmpty(r3);
        if (r3 == 0) goto L_0x00f3;
    L_0x00f1:
        goto L_0x02ce;
    L_0x00f3:
        r3 = r0.mDynamicView;
        r3 = r3.w();
        r6 = "dfp";
        r3 = r3.equalsIgnoreCase(r6);
        if (r3 == 0) goto L_0x0216;
    L_0x0101:
        r1 = new com.google.android.gms.ads.doubleclick.PublisherAdView;
        r3 = r0.mContext;
        r1.<init>(r3);
        r0.dfpAdView = r1;
        r1 = new com.gaana.ads.publisher.LifeCycleAwarePublisherAdView;
        r1.<init>();
        r0.lifeCycleAwareAdView = r1;
        r1 = r0.lifeCycleAwareAdView;
        r3 = r0.dfpAdView;
        r1.wrap(r3);
        r1 = r0.mFragment;
        if (r1 == 0) goto L_0x012f;
    L_0x011c:
        r1 = r0.mFragment;
        r1 = r1.isAdded();
        if (r1 == 0) goto L_0x012f;
    L_0x0124:
        r1 = r0.mFragment;
        r1 = r1.getLifecycle();
        r3 = r0.lifeCycleAwareAdView;
        r1.a(r3);
    L_0x012f:
        r1 = r0.mDynamicView;
        r1 = r1.q();
        r1 = r1.trim();
        r0.dfpAdCode = r1;
        r13 = new com.gaana.view.UpgradeHomeView$2;
        r13.<init>(r2);
        r1 = com.managers.ColombiaAdViewManager.a();
        r1.d();
        r1 = com.managers.ColombiaAdViewManager.a();
        r1.e();
        r1 = r0.mDynamicView;
        r1 = r1.j();
        if (r1 == 0) goto L_0x01bb;
    L_0x0156:
        r3 = "ad_size";
        r1 = r1.get(r3);
        r1 = (java.lang.String) r1;
        r3 = new com.gaana.models.AdsUJData;
        r3.<init>();
        r4 = r0.sectionName;
        r3.setSectionName(r4);
        r4 = r0.dfpAdCode;
        r3.setAdUnitCode(r4);
        r4 = "dfp";
        r3.setAdType(r4);
        r4 = r0.mDynamicView;
        if (r4 == 0) goto L_0x017f;
    L_0x0176:
        r4 = r0.mDynamicView;
        r4 = r4.y();
        r3.setSectionId(r4);
    L_0x017f:
        r4 = android.text.TextUtils.isEmpty(r1);
        if (r4 != 0) goto L_0x01a3;
    L_0x0185:
        r8 = com.managers.ColombiaAdViewManager.a();
        r9 = r0.mContext;
        r10 = r2;
        r10 = (android.widget.LinearLayout) r10;
        r11 = r0.dfpAdView;
        r12 = r0.dfpAdCode;
        r14 = java.lang.Integer.parseInt(r1);
        r15 = r0.sectionName;
        r1 = new com.gaana.models.AdsUJData[r5];
        r1[r7] = r3;
        r16 = r1;
        r8.a(r9, r10, r11, r12, r13, r14, r15, r16);
        goto L_0x0378;
    L_0x01a3:
        r8 = com.managers.ColombiaAdViewManager.a();
        r9 = r0.mContext;
        r10 = r2;
        r10 = (android.widget.LinearLayout) r10;
        r11 = r0.dfpAdView;
        r12 = r0.dfpAdCode;
        r14 = r0.sectionName;
        r15 = new com.gaana.models.AdsUJData[r5];
        r15[r7] = r3;
        r8.a(r9, r10, r11, r12, r13, r14, r15);
        goto L_0x0378;
    L_0x01bb:
        r1 = new com.gaana.models.AdsUJData;
        r1.<init>();
        r3 = r0.sectionName;
        r1.setSectionName(r3);
        r3 = r0.dfpAdCode;
        r1.setAdUnitCode(r3);
        r3 = "dfp";
        r1.setAdType(r3);
        r3 = r0.mDynamicView;
        if (r3 == 0) goto L_0x01dc;
    L_0x01d3:
        r3 = r0.mDynamicView;
        r3 = r3.y();
        r1.setSectionId(r3);
    L_0x01dc:
        r3 = r0.mFragment;
        r3 = r3 instanceof com.dynamicview.DynamicOccasionFragment;
        if (r3 == 0) goto L_0x01fe;
    L_0x01e2:
        r8 = com.managers.ColombiaAdViewManager.a();
        r9 = r0.mContext;
        r10 = r2;
        r10 = (android.widget.LinearLayout) r10;
        r11 = r0.dfpAdView;
        r12 = r0.dfpAdCode;
        r14 = 100;
        r15 = r0.sectionName;
        r3 = new com.gaana.models.AdsUJData[r5];
        r3[r7] = r1;
        r16 = r3;
        r8.a(r9, r10, r11, r12, r13, r14, r15, r16);
        goto L_0x0378;
    L_0x01fe:
        r8 = com.managers.ColombiaAdViewManager.a();
        r9 = r0.mContext;
        r10 = r2;
        r10 = (android.widget.LinearLayout) r10;
        r11 = r0.dfpAdView;
        r12 = r0.dfpAdCode;
        r14 = r0.sectionName;
        r15 = new com.gaana.models.AdsUJData[r5];
        r15[r7] = r1;
        r8.a(r9, r10, r11, r12, r13, r14, r15);
        goto L_0x0378;
    L_0x0216:
        r3 = r0.mDynamicView;
        r3 = r3.w();
        r5 = "columbia";
        r3 = r3.equalsIgnoreCase(r5);
        if (r3 == 0) goto L_0x0378;
    L_0x0224:
        r3 = r0.mFragment;
        r3 = r3.getClass();
        r17 = r3.getSimpleName();
        r3 = new com.gaana.models.AdsUJData;
        r3.<init>();
        r5 = r0.sectionName;
        r3.setSectionName(r5);
        r5 = r0.dfpAdCode;
        r3.setAdUnitCode(r5);
        r5 = "colombia";
        r3.setAdType(r5);
        r5 = r0.mDynamicView;
        if (r5 == 0) goto L_0x024f;
    L_0x0246:
        r5 = r0.mDynamicView;
        r5 = r5.y();
        r3.setSectionId(r5);
    L_0x024f:
        r5 = r0.mDynamicView;
        r5 = r5.q();
        r6 = "[-+]?\\d*\\.?\\d+";
        r5 = r5.matches(r6);
        if (r5 != 0) goto L_0x026a;
    L_0x025d:
        r5 = r0.mDynamicView;
        r5 = r5.q();
        r5 = java.lang.Long.parseLong(r5);
        r0.setAdUnitCode(r5);
    L_0x026a:
        r5 = r0.mFragment;
        r5 = r5 instanceof com.fragments.RadioDetailsMaterialListing;
        if (r5 == 0) goto L_0x027d;
    L_0x0270:
        r5 = com.managers.ColombiaManager.b();
        r6 = "RM";
        r4 = r31.getBusinessObjId();
        r5.a(r6, r4);
    L_0x027d:
        r4 = r0.mFragment;
        r4 = r4 instanceof com.dynamicview.DynamicOccasionFragment;
        if (r4 == 0) goto L_0x0295;
    L_0x0283:
        r4 = r0.mFragment;
        r4 = (com.dynamicview.DynamicOccasionFragment) r4;
        r4 = r4.b();
        if (r4 == 0) goto L_0x0295;
    L_0x028d:
        r4 = com.managers.ColombiaManager.b();
        r5 = 0;
        r4.a(r5, r5);
    L_0x0295:
        r18 = com.managers.an.a();
        r19 = "ad";
        r20 = "";
        r21 = r3.getSectionId();
        r22 = "ad_load";
        r23 = "";
        r24 = "start";
        r25 = r3.getSectionIndex();
        r26 = r3.getAdUnitCode();
        r18.e(r19, r20, r21, r22, r23, r24, r25, r26);
        r10 = com.managers.ColombiaManager.b();
        r12 = r0.mContext;
        r14 = r27.getAdUnitCode();
        r4 = new com.gaana.view.UpgradeHomeView$3;
        r4.<init>(r1, r3);
        r1 = r0.sectionName;
        r16 = r2;
        r18 = r4;
        r19 = r1;
        r10.a(r11, r12, r13, r14, r16, r17, r18, r19);
        goto L_0x0378;
    L_0x02ce:
        r1 = r0.mContext;
        r1 = (com.gaana.GaanaActivity) r1;
        r1 = r1.getCurrentFragment();
        r1 = r1 instanceof com.fragments.RevampedDetailListing;
        if (r1 != 0) goto L_0x02e6;
    L_0x02da:
        r1 = r0.mContext;
        r1 = (com.gaana.GaanaActivity) r1;
        r1 = r1.getCurrentFragment();
        r1 = r1 instanceof com.fragments.RadioDetailsMaterialListing;
        if (r1 == 0) goto L_0x0378;
    L_0x02e6:
        r1 = r0.dfpAdCode;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x0378;
    L_0x02ee:
        r1 = r0.dfpAdCode;
        r3 = "0";
        r1 = r1.equalsIgnoreCase(r3);
        if (r1 != 0) goto L_0x0378;
    L_0x02f8:
        r1 = new com.google.android.gms.ads.doubleclick.PublisherAdView;
        r3 = r0.mContext;
        r1.<init>(r3);
        r0.dfpAdView = r1;
        r1 = new com.gaana.ads.publisher.LifeCycleAwarePublisherAdView;
        r1.<init>();
        r0.lifeCycleAwareAdView = r1;
        r1 = r0.lifeCycleAwareAdView;
        r3 = r0.dfpAdView;
        r1.wrap(r3);
        r1 = r0.mFragment;
        if (r1 == 0) goto L_0x0326;
    L_0x0313:
        r1 = r0.mFragment;
        r1 = r1.isAdded();
        if (r1 == 0) goto L_0x0326;
    L_0x031b:
        r1 = r0.mFragment;
        r1 = r1.getLifecycle();
        r3 = r0.lifeCycleAwareAdView;
        r1.a(r3);
    L_0x0326:
        r8 = com.managers.ColombiaAdViewManager.a();
        r9 = r0.mContext;
        r10 = r2;
        r10 = (android.widget.LinearLayout) r10;
        r11 = r0.dfpAdView;
        r1 = r0.dfpAdCode;
        r12 = r1.trim();
        r13 = new com.gaana.view.UpgradeHomeView$1;
        r13.<init>(r2);
        r14 = r0.sectionName;
        r15 = new com.gaana.models.AdsUJData[r7];
        r8.a(r9, r10, r11, r12, r13, r14, r15);
        goto L_0x0378;
    L_0x0344:
        if (r2 == 0) goto L_0x0378;
    L_0x0346:
        r1 = r2.findViewById(r6);
        if (r1 == 0) goto L_0x0378;
    L_0x034c:
        r1 = r2.findViewById(r6);
        r1 = r1.getVisibility();
        if (r1 == 0) goto L_0x0378;
    L_0x0356:
        r1 = r0.adstatus;
        r3 = com.managers.ColombiaManager.ADSTATUS.LOADED;
        if (r1 != r3) goto L_0x0378;
    L_0x035c:
        r1 = r2.findViewById(r6);
        r3 = r0.mContext;
        r3 = r3.getResources();
        r4 = 2131165269; // 0x7f070055 float:1.794475E38 double:1.052935545E-314;
        r3 = r3.getDimension(r4);
        r3 = (int) r3;
        r1.setPadding(r7, r3, r7, r7);
        r1 = r2.findViewById(r6);
        r1.setVisibility(r7);
    L_0x0378:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.UpgradeHomeView.getPopulatedView(int, android.view.View, android.view.ViewGroup, com.gaana.models.BusinessObject):android.view.View");
    }

    public void setIsToBeRefreshed(boolean z) {
        if (z) {
            this.adstatus = ADSTATUS.REFRESH;
            if (this.mFragment != null && this.adPosition != -1) {
                this.mFragment.notifyItemChanged(this.adPosition);
            }
        }
    }

    public void onClick(View view) {
        super.onClick(view);
    }

    public void setFirstCall(boolean z) {
        if (this.lifeCycleAwareAdView != null) {
            this.lifeCycleAwareAdView.destroy();
            if (this.mFragment != null) {
                this.mFragment.getLifecycle().b(this.lifeCycleAwareAdView);
            }
        }
    }
}
