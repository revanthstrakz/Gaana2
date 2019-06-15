package com.payu.custombrowser.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.view.View;
import android.widget.ArrayAdapter;
import com.payu.custombrowser.Bank;
import com.payu.custombrowser.d.d;
import com.payu.custombrowser.upiintent.Payment;
import com.payu.custombrowser.util.CBConstant;
import com.payu.custombrowser.util.CBUtil;
import java.util.HashMap;

public class CustomBrowserConfig implements Parcelable {
    public static final Creator<CustomBrowserConfig> CREATOR = new Creator<CustomBrowserConfig>() {
        /* renamed from: a */
        public CustomBrowserConfig createFromParcel(Parcel parcel) {
            return new CustomBrowserConfig(parcel);
        }

        /* renamed from: a */
        public CustomBrowserConfig[] newArray(int i) {
            return new CustomBrowserConfig[i];
        }
    };
    public static final int DISABLE = -1;
    public static final int ENABLE = 0;
    public static final int FAIL_MODE = 2;
    public static final int FALSE = -1;
    private static View N = null;
    public static final int STOREONECLICKHASH_MODE_NONE = 0;
    public static final int STOREONECLICKHASH_MODE_SERVER = 1;
    public static final int TRUE = 0;
    public static final int WARN_MODE = 1;
    private String A;
    private String B;
    private String C;
    private String D;
    private int E;
    private int F;
    private int G;
    private ArrayAdapter H;
    private int I;
    private String J;
    private int K;
    private transient ReviewOrderBundle L;
    private int M;
    private int O;
    private int P;
    private int Q;
    private String R;
    private String a;
    private int b;
    private int c;
    private int d;
    private String e;
    private String f;
    private String g;
    private int h;
    private int i;
    private int j;
    private int k = 1;
    private int l;
    private int m;
    private String n;
    private String o;
    private String p;
    private String q;
    private int r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public int describeContents() {
        return 0;
    }

    public String getPayUOptionPaymentHash() {
        return this.a;
    }

    public void setPayUOptionPaymentHash(String str) {
        this.a = str;
    }

    public ArrayAdapter getCbMenuAdapter() {
        return this.H;
    }

    public void setCbMenuAdapter(ArrayAdapter arrayAdapter) {
        this.H = arrayAdapter;
    }

    public int getCbDrawerCustomMenu() {
        return this.O;
    }

    public void setCbDrawerCustomMenu(int i) {
        this.O = i;
    }

    public View getToolBarView() {
        return N;
    }

    public void setToolBarView(View view) {
        N = view;
    }

    private CustomBrowserConfig() {
    }

    protected CustomBrowserConfig(Parcel parcel) {
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readInt();
        this.s = parcel.readString();
        this.t = parcel.readString();
        this.u = parcel.readString();
        this.v = parcel.readString();
        this.w = parcel.readString();
        this.x = parcel.readString();
        this.y = parcel.readString();
        this.z = parcel.readString();
        this.A = parcel.readString();
        this.B = parcel.readString();
        this.C = parcel.readString();
        this.D = parcel.readString();
        this.E = parcel.readInt();
        this.F = parcel.readInt();
        this.G = parcel.readInt();
        this.M = parcel.readInt();
        this.I = parcel.readInt();
        this.J = parcel.readString();
        this.K = parcel.readInt();
        this.O = parcel.readInt();
        this.P = parcel.readInt();
        this.Q = parcel.readInt();
        this.R = parcel.readString();
    }

    public CustomBrowserConfig(@Size(max = 6, min = 6) @NonNull String str, @NonNull String str2) {
        this.e = str2;
        this.f = str;
        this.r = d.surepay_logo;
        this.s = "Internet Restored";
        this.t = "You can now resume the transaction";
        this.v = "No Internet Found";
        this.w = "We could not detect internet on your device";
        this.y = "Transaction Verified";
        this.z = "The bank has verified this transaction and we are good to go.";
        this.B = "Transaction Status Unknown";
        this.C = "The bank could not verify the transaction at this time.";
        this.m = 0;
        this.E = 1;
        this.F = 1800000;
        this.G = 5000;
        this.M = -1;
        this.I = -1;
        this.K = -1;
        this.P = 1;
        this.Q = -1;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeInt(this.r);
        parcel.writeString(this.s);
        parcel.writeString(this.t);
        parcel.writeString(this.u);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        parcel.writeString(this.x);
        parcel.writeString(this.y);
        parcel.writeString(this.z);
        parcel.writeString(this.A);
        parcel.writeString(this.B);
        parcel.writeString(this.C);
        parcel.writeString(this.D);
        parcel.writeInt(this.E);
        parcel.writeInt(this.F);
        parcel.writeInt(this.G);
        parcel.writeInt(this.M);
        parcel.writeInt(this.I);
        parcel.writeString(this.J);
        parcel.writeInt(this.K);
        parcel.writeInt(this.O);
        parcel.writeInt(this.P);
        parcel.writeInt(this.Q);
        parcel.writeString(this.R);
    }

    public String getPostURL() {
        return this.o;
    }

    public void setPostURL(String str) {
        this.o = str;
    }

    public String getPayuPostData() {
        return this.p;
    }

    public void setPayuPostData(String str) {
        this.p = str;
        HashMap dataFromPostData = new CBUtil().getDataFromPostData(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Product info: ");
        stringBuilder.append((String) dataFromPostData.get("productinfo"));
        stringBuilder.append("\nAmount: ");
        stringBuilder.append((String) dataFromPostData.get("amount"));
        String stringBuilder2 = stringBuilder.toString();
        if (this.u == null) {
            setSurePayNotificationGoodNetWorkBody(stringBuilder2);
        }
        if (this.x == null) {
            setSurePayNotificationPoorNetWorkBody(stringBuilder2);
        }
        if (this.A == null) {
            setSurePayNotificationTransactionVerifiedBody(stringBuilder2);
        }
        if (this.D == null) {
            setSurePayNotificationTransactionNotVerifiedBody(stringBuilder2);
        }
        if (dataFromPostData.get(CBConstant.KEY) != null) {
            setMerchantKey(Bank.keyAnalytics == null ? (String) dataFromPostData.get(CBConstant.KEY) : Bank.keyAnalytics);
        }
    }

    public int getEnableSurePay() {
        return this.m;
    }

    public void setEnableSurePay(@IntRange(from = 0, to = 3) int i) {
        if (i > 3) {
            i = 3;
        }
        this.m = i;
    }

    public int getMerchantSMSPermission() {
        return this.l;
    }

    public void setMerchantSMSPermission(boolean z) {
        this.l = z;
    }

    public int getEnableWebFlow() {
        return this.P;
    }

    public void setEnableWebFlow(Payment payment, boolean z) {
        payment.setWebFlowSupported(z);
    }

    public int getMagicretry() {
        return this.k;
    }

    public void setmagicRetry(boolean z) {
        this.k = z;
    }

    public int getStoreOneClickHash() {
        return this.j;
    }

    public void setStoreOneClickHash(int i) {
        this.j = i;
    }

    public String getMerchantCheckoutActivityPath() {
        return this.n;
    }

    public void setMerchantCheckoutActivityPath(String str) {
        this.n = str;
    }

    public int getDisableBackButtonDialog() {
        return this.i;
    }

    public void setDisableBackButtonDialog(boolean z) {
        this.i = z;
    }

    public int getViewPortWideEnable() {
        return this.b;
    }

    public void setViewPortWideEnable(boolean z) {
        this.b = z;
    }

    public int getAutoApprove() {
        return this.c;
    }

    public void setAutoApprove(boolean z) {
        this.c = z;
    }

    public String getTransactionID() {
        return this.e;
    }

    public int getAutoSelectOTP() {
        return this.d;
    }

    public void setAutoSelectOTP(boolean z) {
        this.d = z;
    }

    public String getMerchantKey() {
        return this.f;
    }

    public void setMerchantKey(String str) {
        this.f = Bank.keyAnalytics;
        if (this.f == null || this.f.trim().length() < 1) {
            this.f = str;
            Bank.keyAnalytics = str;
        }
    }

    public String getSdkVersionName() {
        return this.g;
    }

    public void setSdkVersionName(String str) {
        this.g = str;
    }

    public int getShowCustombrowser() {
        return this.h;
    }

    public void setShowCustombrowser(boolean z) {
        this.h = z;
    }

    public String getSurePayNotificationGoodNetworkTitle() {
        return this.s;
    }

    public void setSurePayNotificationGoodNetworkTitle(String str) {
        this.s = str;
    }

    public String getSurePayNotificationGoodNetWorkHeader() {
        return this.t;
    }

    public void setSurePayNotificationGoodNetWorkHeader(String str) {
        this.t = str;
    }

    public String getSurePayNotificationGoodNetWorkBody() {
        return this.u;
    }

    public void setSurePayNotificationGoodNetWorkBody(String str) {
        this.u = str;
    }

    public String getSurePayNotificationPoorNetWorkTitle() {
        return this.v;
    }

    public void setSurePayNotificationPoorNetWorkTitle(String str) {
        this.v = str;
    }

    public String getSurePayNotificationPoorNetWorkHeader() {
        return this.w;
    }

    public void setSurePayNotificationPoorNetWorkHeader(String str) {
        this.w = str;
    }

    public String getSurePayNotificationPoorNetWorkBody() {
        return this.x;
    }

    public void setSurePayNotificationPoorNetWorkBody(String str) {
        this.x = str;
    }

    public String getSurePayNotificationTransactionVerifiedTitle() {
        return this.y;
    }

    public void setSurePayNotificationTransactionVerifiedTitle(String str) {
        this.y = str;
    }

    public String getSurePayNotificationTransactionVerifiedHeader() {
        return this.z;
    }

    public void setSurePayNotificationTransactionVerifiedHeader(String str) {
        this.z = str;
    }

    public String getSurePayNotificationTransactionVerifiedBody() {
        return this.A;
    }

    public void setSurePayNotificationTransactionVerifiedBody(String str) {
        this.A = str;
    }

    public String getSurePayNotificationTransactionNotVerifiedTitle() {
        return this.B;
    }

    public void setSurePayNotificationTransactionNotVerifiedTitle(String str) {
        this.B = str;
    }

    public String getSurePayNotificationTransactionNotVerifiedHeader() {
        return this.C;
    }

    public void setSurePayNotificationTransactionNotVerifiedHeader(String str) {
        this.C = str;
    }

    public String getSurePayNotificationTransactionNotVerifiedBody() {
        return this.D;
    }

    public void setSurePayNotificationTransactionNotVerifiedBody(String str) {
        this.D = str;
    }

    public int getSurePayNotificationIcon() {
        return this.r;
    }

    public void setSurePayNotificationIcon(int i) {
        this.r = i;
    }

    public int getSurePayMode() {
        return this.E;
    }

    public void setSurePayMode(int i) {
        this.E = i;
    }

    public int getInternetRestoredWindowTTL() {
        return this.G;
    }

    public void setInternetRestoredWindowTTL(int i) {
        this.G = i;
    }

    public String getReviewOrderButtonText() {
        return this.J;
    }

    public void setReviewOrderButtonText(@Size(max = 16) @NonNull String str) {
        if (str == null) {
            throw new RuntimeException("ReviewOrderButtonText cannot be null");
        } else if (str.length() > 16) {
            throw new RuntimeException("ReviewOrderButtonText size should be less than 16");
        } else {
            this.J = str;
        }
    }

    public int getReviewOrderButtonTextColor() {
        return this.K;
    }

    public void setReviewOrderButtonTextColor(@ColorRes int i) {
        this.K = i;
    }

    public int getEnableReviewOrder() {
        return this.I;
    }

    public void setEnableReviewOrder(int i) {
        this.I = i;
    }

    public int getSurePayBackgroundTTL() {
        return this.F;
    }

    public void setSurePayBackgroundTTL(int i) {
        this.F = i;
    }

    public int getReviewOrderCustomView() {
        return this.M;
    }

    public void setReviewOrderCustomView(@LayoutRes int i) {
        this.M = i;
    }

    public ReviewOrderBundle getReviewOrderDefaultViewData() {
        return this.L;
    }

    public void setReviewOrderDefaultViewData(ReviewOrderBundle reviewOrderBundle) {
        this.L = reviewOrderBundle;
    }

    public int getGmsProviderUpdatedStatus() {
        return this.Q;
    }

    public void setGmsProviderUpdatedStatus(int i) {
        this.Q = i;
    }

    public String getHtmlData() {
        return this.q;
    }

    public void setHtmlData(String str) {
        this.q = str;
    }

    public String getSurepayS2Surl() {
        return this.R;
    }

    public void setSurepayS2Surl(String str) {
        this.R = str;
    }
}
