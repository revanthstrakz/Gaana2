package com.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.analytics.AppsFlyer;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.login.UserSubscriptionData.ProductInfo;
import com.gaana.models.BusinessObject;
import com.gaana.models.GoogleIntroductoryPriceConfig;
import com.gaana.models.PaymentProductDetailModel;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.models.Products;
import com.gaana.models.UpdatePaymentResponse;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.i;
import com.iabutils.IabHelper;
import com.iabutils.IabHelper.IabAsyncInProgressException;
import com.services.l.af;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PurchaseGoogleManager {
    private static PurchaseGoogleManager c;
    private static f i;
    private static ArrayList<ProductItem> j;
    public a a = null;
    com.iabutils.IabHelper.c b = new com.iabutils.IabHelper.c() {
        public void a(com.iabutils.a aVar, com.iabutils.b bVar) {
            ai.a();
            if (bVar == null || PurchaseGoogleManager.c == null || PurchaseGoogleManager.c.b() == null || PurchaseGoogleManager.c.b().size() == 0) {
                if (PurchaseGoogleManager.this.a != null) {
                    PurchaseGoogleManager.this.a.onFailure(GaanaApplication.getContext().getString(R.string.google_product_detail_not_found));
                    PurchaseGoogleManager.this.a.onInventoryQueryCompeleted(aVar, bVar);
                }
                return;
            }
            PurchaseGoogleManager.this.g = bVar;
            String str = null;
            for (int i = 0; i < PurchaseGoogleManager.j.size(); i++) {
                ProductItem productItem = (ProductItem) PurchaseGoogleManager.j.get(i);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("gaana_plus_0");
                stringBuilder.append(productItem.getP_id());
                String stringBuilder2 = stringBuilder.toString();
                if (!TextUtils.isEmpty(productItem.getP_id()) && productItem.getP_id().length() > 2) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("gaana_plus_");
                    stringBuilder.append(productItem.getP_id());
                    stringBuilder2 = stringBuilder.toString();
                }
                if (bVar.c(stringBuilder2)) {
                    PurchaseGoogleManager.this.a(bVar.b(stringBuilder2), productItem);
                } else if (PurchaseGoogleManager.this.h && str == null) {
                    str = GaanaApplication.getContext().getString(R.string.not_purchased_product);
                }
            }
            if (PurchaseGoogleManager.this.a != null) {
                if (str != null) {
                    PurchaseGoogleManager.this.a.onFailure(str);
                }
                PurchaseGoogleManager.this.a.onInventoryQueryCompeleted(aVar, bVar);
            }
        }
    };
    private boolean d = false;
    private IabHelper e;
    private GaanaApplication f = GaanaApplication.getInstance();
    private com.iabutils.b g = null;
    private boolean h = false;
    private HashMap<String, c> k = new HashMap();
    private ProductItem l;
    private String m;
    private String n;
    private String o;
    private UpdatePaymentResponse p = null;
    private int q;
    private Handler r;
    private com.iabutils.IabHelper.c s = new com.iabutils.IabHelper.c() {
        public void a(com.iabutils.a aVar, com.iabutils.b bVar) {
            Context a = ai.a();
            boolean z = false;
            if (aVar.c() || bVar == null) {
                if (a instanceof BaseActivity) {
                    ((BaseActivity) a).mDialog.a(a.getString(R.string.app_name), a.getResources().getString(R.string.error_msg_failed_to_query_inventory), Boolean.valueOf(false), new com.services.f.b() {
                        public void onCancelListner() {
                        }

                        public void onOkListner(String str) {
                        }
                    });
                }
                if (PurchaseGoogleManager.this.a != null) {
                    PurchaseGoogleManager.this.a.onFailure(GaanaApplication.getContext().getString(R.string.failed_to_query));
                }
                return;
            }
            try {
                if (!bVar.c(PurchaseGoogleManager.this.m)) {
                    z = true;
                } else if (!(PurchaseGoogleManager.this.f.getCurrentUser().getUserSubscriptionData().getAccountType() == 3 || ap.a().u())) {
                    com.iabutils.c b = bVar.b(PurchaseGoogleManager.this.m);
                    if (PurchaseGoogleManager.this.a(b)) {
                        PurchaseGoogleManager.this.a(b, PurchaseGoogleManager.this.l);
                    } else {
                        if (PurchaseGoogleManager.this.a != null) {
                            PurchaseGoogleManager.this.a.onFailure(GaanaApplication.getContext().getString(R.string.already_purchased_gaana));
                        }
                        return;
                    }
                }
                if (z && PurchaseGoogleManager.j != null && PurchaseGoogleManager.this.a(PurchaseGoogleManager.this.e)) {
                    IabHelper e;
                    ProductInfo productInfo = PurchaseGoogleManager.this.f.getCurrentUser().getUserSubscriptionData().getProductInfo();
                    Object obj = null;
                    if (!(!ap.a().e() || productInfo == null || TextUtils.isEmpty(productInfo.getGoogle_skuid()))) {
                        obj = new ArrayList();
                        obj.add(productInfo.getGoogle_skuid());
                    }
                    if (a instanceof BaseActivity) {
                        e = PurchaseGoogleManager.this.e;
                        IabHelper iabHelper = e;
                        iabHelper.a((Activity) a, PurchaseGoogleManager.this.m, "subs", obj, 710, PurchaseGoogleManager.this.t, PurchaseGoogleManager.this.j());
                    }
                    e = PurchaseGoogleManager.this.e;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Purchase upgrade/down grade flow ");
                    if (obj != null) {
                        obj = obj.toString();
                    }
                    stringBuilder.append(obj);
                    e.c(stringBuilder.toString());
                }
            } catch (IllegalStateException e2) {
                ThrowableExtension.printStackTrace(e2);
            } catch (Exception e3) {
                ThrowableExtension.printStackTrace(e3);
            }
        }
    };
    private com.iabutils.IabHelper.a t = new com.iabutils.IabHelper.a() {
        public void a(com.iabutils.a aVar, com.iabutils.c cVar) {
            String str = null;
            PurchaseGoogleManager.this.e = null;
            Context a = ai.a();
            StringBuilder stringBuilder;
            String string;
            if (cVar == null || cVar.c() == null) {
                str = GaanaApplication.getContext().getString(R.string.google_purchase_finished);
            } else if (aVar.c()) {
                if (!aVar.a().contains("User canceled")) {
                    if (PurchaseGoogleManager.this.q < 1) {
                        PurchaseGoogleManager.this.q = PurchaseGoogleManager.this.q + 1;
                        PurchaseGoogleManager.this.a(true);
                        return;
                    } else if (PurchaseGoogleManager.this.l != null) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(PurchaseGoogleManager.this.l.getP_payment_mode());
                        stringBuilder2.append("-");
                        stringBuilder2.append(PurchaseGoogleManager.this.l.getP_id());
                        String stringBuilder3 = stringBuilder2.toString();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Google Purchase Error!!<");
                        stringBuilder.append(aVar.a());
                        stringBuilder.append(">");
                        u.a().a("SubscriptionPayment", stringBuilder3, stringBuilder.toString());
                    }
                }
                PurchaseGoogleManager.this.q = 0;
                string = GaanaApplication.getContext().getString(R.string.error_occured_purchasing_susbscription);
                if (!(string == null || PurchaseGoogleManager.this.a == null)) {
                    PurchaseGoogleManager.this.a.onFailure(string);
                }
                return;
            } else if (cVar.c().equals(PurchaseGoogleManager.this.m)) {
                PurchaseGoogleManager.this.q = 0;
                StringBuilder stringBuilder4;
                if (!PurchaseGoogleManager.this.a(cVar)) {
                    if (PurchaseGoogleManager.this.l != null) {
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(PurchaseGoogleManager.this.l.getP_payment_mode());
                        stringBuilder4.append("-");
                        stringBuilder4.append(PurchaseGoogleManager.this.l.getP_id());
                        string = stringBuilder4.toString();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Payload verification Error<");
                        stringBuilder.append(cVar.d());
                        stringBuilder.append("!=");
                        stringBuilder.append(PurchaseGoogleManager.this.j());
                        stringBuilder.append(">");
                        u.a().a("SubscriptionPayment", string, stringBuilder.toString());
                    }
                    string = GaanaApplication.getContext().getString(R.string.error_purchasing_authentication_failed);
                    if (!(string == null || PurchaseGoogleManager.this.a == null)) {
                        PurchaseGoogleManager.this.a.onFailure(string);
                    }
                    return;
                } else if (PurchaseGoogleManager.this.l == null) {
                    str = GaanaApplication.getContext().getString(R.string.google_product_null);
                } else if (PurchaseGoogleManager.this.d) {
                    str = GaanaApplication.getContext().getString(R.string.google_purchase_error);
                } else if (Util.j(GaanaApplication.getContext())) {
                    PurchaseGoogleManager.this.a(cVar, PurchaseGoogleManager.this.l);
                    boolean equalsIgnoreCase = PurchaseGoogleManager.this.l.getP_cost_curr().equalsIgnoreCase("Rs.");
                    u.a().a(PurchaseGoogleManager.this.l, PurchaseGoogleManager.this.n, PurchaseGoogleManager.this.o, cVar.b(), PurchaseGoogleManager.this.l.getCouponCode());
                    MoEngage.getInstance().reportOnPaymentCompletedGoogle(PurchaseGoogleManager.this.l, cVar, PurchaseGoogleManager.this.f.getCurrentUser());
                    AppsFlyer.getInstance().reportPurchaseCompleted(PurchaseGoogleManager.this.l, "GOOGLE_PLAY");
                    if (a instanceof BaseActivity) {
                        ((BaseActivity) a).sendPaymentGAEvent(PurchaseGoogleManager.this.l, "Success");
                    }
                    Constants.a(PurchaseGoogleManager.this.l);
                    PurchaseGoogleManager.this.d = true;
                } else {
                    if (PurchaseGoogleManager.this.l != null) {
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(PurchaseGoogleManager.this.l.getP_payment_mode());
                        stringBuilder4.append("-");
                        stringBuilder4.append(PurchaseGoogleManager.this.l.getP_id());
                        u.a().a("SubscriptionPayment", stringBuilder4.toString(), "Network Error after Google Purchase");
                    }
                    string = GaanaApplication.getContext().getString(R.string.network_error);
                    if (!(string == null || PurchaseGoogleManager.this.a == null)) {
                        PurchaseGoogleManager.this.a.onFailure(string);
                    }
                    return;
                }
            } else {
                str = GaanaApplication.getContext().getString(R.string.sku_not_matched);
            }
            if (!(str == null || PurchaseGoogleManager.this.a == null)) {
                PurchaseGoogleManager.this.a.onFailure(str);
            }
        }
    };

    public enum SubscriptionPurchaseType {
        SUBSCRIBED_GAANAPLUS_PURCHASED,
        SUBSCRIBED_ACCOUNT_LINKED,
        SUBSCRIBED_TRIAL,
        SUBSCRIBED_ERROR,
        SUBSCRIBED_DEVICELINKING_FAILED,
        SUBSCRIBED_EXPIRED,
        SUBSCRIBED_GAANAPLUS_ALREADY
    }

    public interface a {
        void onFailure(String str);

        void onInventoryQueryCompeleted(com.iabutils.a aVar, com.iabutils.b bVar);

        void onProductsQueryCompleted();

        void onPurchaseFinished(SubscriptionPurchaseType subscriptionPurchaseType);
    }

    interface b {
        void a(GoogleIntroductoryPriceConfig googleIntroductoryPriceConfig);
    }

    public class c {
        private boolean b;
        private String c;
        private long d;
        private String e;
        private long f;

        public boolean a() {
            return this.b;
        }

        public void a(boolean z) {
            this.b = z;
        }

        public String b() {
            return this.c;
        }

        public void a(String str) {
            this.c = str;
        }

        public String c() {
            return this.e;
        }

        public void b(String str) {
            this.e = str;
        }

        public long d() {
            return this.d;
        }

        public void a(long j) {
            this.d = j;
        }

        public long e() {
            return this.f;
        }

        public void b(long j) {
            this.f = j;
        }
    }

    public interface d {
        void onGoolgeProductPriceQueryConpleted(c cVar);
    }

    public interface e {
        void a(UpdatePaymentResponse updatePaymentResponse);
    }

    public static class f {
        private String a = null;
        private String b = null;
        private boolean c = false;
        private Class<?> d = Products.class;
        private ArrayList<String> e = null;
        private String f = null;

        public String a() {
            return this.a;
        }

        public void a(String str) {
            this.a = str;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public boolean c() {
            return this.c;
        }

        public void a(boolean z) {
            this.c = z;
        }

        public ArrayList<String> d() {
            return this.e;
        }

        public void a(ArrayList<String> arrayList) {
            this.e = arrayList;
        }

        public String e() {
            return this.f;
        }

        public void c(String str) {
            this.f = str;
        }
    }

    public static PurchaseGoogleManager a(Context context, a aVar) {
        if (c == null) {
            synchronized (PurchaseGoogleManager.class) {
                if (c == null) {
                    c = new PurchaseGoogleManager(context);
                }
            }
        }
        c.a = aVar;
        return c;
    }

    public static PurchaseGoogleManager a(Context context, a aVar, boolean z) {
        if (c == null) {
            synchronized (PurchaseGoogleManager.class) {
                if (c == null) {
                    c = new PurchaseGoogleManager(context);
                }
            }
        }
        c.a = aVar;
        c.h = z;
        if (z) {
            c.i();
        }
        return c;
    }

    public static PurchaseGoogleManager a(Context context) {
        if (c == null) {
            synchronized (PurchaseGoogleManager.class) {
                if (c == null) {
                    c = new PurchaseGoogleManager(context);
                }
            }
        }
        return c;
    }

    public static PurchaseGoogleManager a(String str) {
        return c;
    }

    private PurchaseGoogleManager(Context context) {
        j = new ArrayList();
        a();
    }

    public void a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.bf);
        stringBuilder.append(Constants.bg);
        stringBuilder.append(Constants.bh);
        stringBuilder.append(Constants.bi);
        stringBuilder.append(Constants.bj);
        stringBuilder.append(Constants.bk);
        String stringBuilder2 = stringBuilder.toString();
        String l = Util.l(GaanaApplication.getContext());
        i = new f();
        i.a(false);
        i.b(stringBuilder2);
        i.a("https://api.gaana.com/gaanaplusservice.php?");
        i.c(l);
    }

    public boolean a(int i, int i2, Intent intent) {
        return this.e != null ? this.e.a(i, i2, intent) ^ 1 : false;
    }

    public ArrayList<ProductItem> b() {
        return j;
    }

    public void a(final boolean z) {
        final IabHelper iabHelper = new IabHelper(GaanaApplication.getContext(), i.b());
        iabHelper.a(i.c());
        iabHelper.a(new com.iabutils.IabHelper.b() {
            public void a(com.iabutils.a aVar) {
                if (aVar.b()) {
                    try {
                        PurchaseGoogleManager.this.e = iabHelper;
                        iabHelper.a(z, null, PurchaseGoogleManager.i.d(), PurchaseGoogleManager.this.s);
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                        if (PurchaseGoogleManager.this.a != null) {
                            PurchaseGoogleManager.this.a.onFailure(GaanaApplication.getContext().getString(R.string.failed_to_connect_google_playstore));
                        }
                    }
                    return;
                }
                Context a = ai.a();
                if (!(a == null || ((Activity) a).isFinishing())) {
                    ((BaseActivity) a).mDialog.a("Gaana", a.getResources().getString(R.string.error_msg_failed_to_query_inventory), Boolean.valueOf(false), new com.services.f.b() {
                        public void onCancelListner() {
                        }

                        public void onOkListner(String str) {
                        }
                    });
                }
                if (PurchaseGoogleManager.this.a != null) {
                    PurchaseGoogleManager.this.a.onFailure(GaanaApplication.getContext().getString(R.string.failed_to_connect_google_playstore));
                }
            }
        });
    }

    private void i() {
        final Context a = ai.a();
        if (a instanceof BaseActivity) {
            ((BaseActivity) a).showProgressDialog(Boolean.valueOf(true), a.getString(R.string.loading));
            URLManager uRLManager = new URLManager();
            uRLManager.a("https://api.gaana.com/gaanaplusservice.php?type=get_google_products");
            uRLManager.a(PaymentProductDetailModel.class);
            uRLManager.b(Boolean.valueOf(false));
            uRLManager.i(false);
            i.a().a(new af() {
                public void onRetreivalComplete(Object obj) {
                    if (obj instanceof PaymentProductDetailModel) {
                        PaymentProductDetailModel paymentProductDetailModel = (PaymentProductDetailModel) obj;
                        if (!(paymentProductDetailModel == null || paymentProductDetailModel.getProduct_desc() == null)) {
                            PurchaseGoogleManager.j = PurchaseGoogleManager.this.a(paymentProductDetailModel.getProduct_desc().getPayment_options());
                        }
                        if (PurchaseGoogleManager.this.a != null) {
                            PurchaseGoogleManager.this.a.onProductsQueryCompleted();
                        }
                        if (PurchaseGoogleManager.j != null && PurchaseGoogleManager.i != null) {
                            ArrayList arrayList = new ArrayList();
                            for (int i = 0; i < PurchaseGoogleManager.j.size(); i++) {
                                ProductItem productItem = (ProductItem) PurchaseGoogleManager.j.get(i);
                                StringBuilder stringBuilder;
                                if (TextUtils.isEmpty(productItem.getP_id()) || productItem.getP_id().length() <= 2) {
                                    stringBuilder = new StringBuilder();
                                    stringBuilder.append("gaana_plus_0");
                                    stringBuilder.append(productItem.getP_id());
                                    arrayList.add(stringBuilder.toString());
                                } else {
                                    stringBuilder = new StringBuilder();
                                    stringBuilder.append("gaana_plus_");
                                    stringBuilder.append(productItem.getP_id());
                                    arrayList.add(stringBuilder.toString());
                                }
                            }
                            PurchaseGoogleManager.i.a(arrayList);
                            final IabHelper iabHelper = new IabHelper(a, PurchaseGoogleManager.i.b());
                            iabHelper.a(PurchaseGoogleManager.i.c());
                            iabHelper.a(new com.iabutils.IabHelper.b() {
                                public void a(com.iabutils.a aVar) {
                                    if (aVar.b()) {
                                        try {
                                            iabHelper.a(true, null, PurchaseGoogleManager.i.d(), PurchaseGoogleManager.this.b);
                                        } catch (Exception unused) {
                                        }
                                        return;
                                    }
                                    Context a = ai.a();
                                    if (!(a == null || ((Activity) a).isFinishing())) {
                                        ((BaseActivity) a).mDialog.a(a.getString(R.string.app_name), a.getResources().getString(R.string.error_msg_failed_to_query_inventory), Boolean.valueOf(false), new com.services.f.b() {
                                            public void onCancelListner() {
                                            }

                                            public void onOkListner(String str) {
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    aj.a().a(GaanaApplication.getContext(), GaanaApplication.getContext().getResources().getString(R.string.error_download_no_internet));
                }
            }, uRLManager);
        }
    }

    public ArrayList<ProductItem> a(ArrayList<ProductItem> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        ArrayList<ProductItem> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ProductItem productItem = (ProductItem) it.next();
            if ("android".equalsIgnoreCase(productItem.getP_payment_mode())) {
                arrayList2.add(productItem);
            }
        }
        return arrayList2;
    }

    public void c() {
        if (this.e != null) {
            try {
                this.e.a();
            } catch (IabAsyncInProgressException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        this.e = null;
        c = null;
        i = null;
        j = null;
    }

    private boolean a(com.iabutils.c cVar) {
        return cVar.d().equalsIgnoreCase(j());
    }

    private String j() {
        if (!this.f.getCurrentUser().getLoginStatus()) {
            return "";
        }
        String email = this.f.getCurrentUser().getUserProfile().getEmail();
        if (this.f.getCurrentUser().getUserProfile().getUserId() != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(email);
            stringBuilder.append(this.f.getCurrentUser().getUserProfile().getUserId());
            email = stringBuilder.toString();
        }
        return email;
    }

    public void a(ProductItem productItem, String str, String str2) {
        this.l = productItem;
        this.n = str;
        this.o = str2;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("gaana_plus_0");
        stringBuilder.append(this.l.getP_id());
        this.m = stringBuilder.toString();
        if (!TextUtils.isEmpty(this.l.getP_id()) && this.l.getP_id().length() > 2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("gaana_plus_");
            stringBuilder.append(this.l.getP_id());
            this.m = stringBuilder.toString();
        }
        k();
    }

    private void a(com.iabutils.c cVar, ProductItem productItem) {
        String b = cVar.b();
        String e = cVar.e();
        this.l = productItem;
        a(b, e, a(1));
    }

    private e a(int i) {
        return i == 1 ? new e() {
            public void a(UpdatePaymentResponse updatePaymentResponse) {
                Context a = ai.a();
                if (updatePaymentResponse != null && updatePaymentResponse.getStatus().equalsIgnoreCase("true")) {
                    if (PurchaseGoogleManager.this.a != null) {
                        PurchaseGoogleManager.this.a.onPurchaseFinished(SubscriptionPurchaseType.SUBSCRIBED_GAANAPLUS_PURCHASED);
                    }
                    PurchaseGoogleManager.this.a("success", PurchaseGoogleManager.this.l.getP_id());
                } else if (updatePaymentResponse != null && updatePaymentResponse.getStatus().equalsIgnoreCase(InternalLogger.EVENT_PARAM_EXTRAS_FALSE)) {
                    if (a instanceof BaseActivity) {
                        ((BaseActivity) a).hideProgressDialog();
                    }
                    PurchaseGoogleManager.this.a("failure", PurchaseGoogleManager.this.l.getP_id());
                    ap.a().a(a, updatePaymentResponse.getMessage());
                } else if (updatePaymentResponse == null) {
                    PurchaseGoogleManager.this.a("failure", PurchaseGoogleManager.this.l.getP_id());
                    if (a instanceof BaseActivity) {
                        BaseActivity baseActivity = (BaseActivity) a;
                        baseActivity.hideProgressDialog();
                        baseActivity.mDialog = new com.services.f(a);
                        baseActivity.mDialog.a(a.getString(R.string.app_name), a.getResources().getString(R.string.error_msg_gaana_plus_update_payment_failed), Boolean.valueOf(false), a.getString(R.string.try_again), null, new com.services.f.b() {
                            public void onCancelListner() {
                            }

                            public void onOkListner(String str) {
                                PurchaseGoogleManager.this.a(PurchaseGoogleManager.this.g.b(PurchaseGoogleManager.this.m), PurchaseGoogleManager.this.l);
                            }
                        });
                    }
                }
            }
        } : null;
    }

    public void a(final String str, final d dVar) {
        if (!this.k.containsKey(str)) {
            final IabHelper iabHelper = new IabHelper(GaanaApplication.getContext(), i.b());
            iabHelper.a(i.c());
            iabHelper.a(new com.iabutils.IabHelper.b() {
                public void a(com.iabutils.a aVar) {
                    if (aVar.b()) {
                        try {
                            ArrayList arrayList = new ArrayList();
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("gaana_plus_0");
                            stringBuilder.append(str);
                            arrayList.add(stringBuilder.toString());
                            if (TextUtils.isEmpty(str) || str.length() <= 2) {
                                PurchaseGoogleManager.i.a(arrayList);
                            } else {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("gaana_plus_");
                                stringBuilder.append(str);
                                arrayList.add(stringBuilder.toString());
                            }
                            iabHelper.a(true, null, PurchaseGoogleManager.i.d(), new com.iabutils.IabHelper.c() {
                                public void a(com.iabutils.a aVar, com.iabutils.b bVar) {
                                    c cVar = null;
                                    if (aVar.c()) {
                                        if (dVar != null) {
                                            dVar.onGoolgeProductPriceQueryConpleted(null);
                                        }
                                        return;
                                    }
                                    PurchaseGoogleManager.this.g = bVar;
                                    com.iabutils.b n = PurchaseGoogleManager.this.g;
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("gaana_plus_0");
                                    stringBuilder.append(str);
                                    com.iabutils.e a = n.a(stringBuilder.toString());
                                    if (!TextUtils.isEmpty(str) && str.length() > 2) {
                                        n = PurchaseGoogleManager.this.g;
                                        stringBuilder = new StringBuilder();
                                        stringBuilder.append("gaana_plus_");
                                        stringBuilder.append(str);
                                        a = n.a(stringBuilder.toString());
                                    }
                                    if (!(a == null || TextUtils.isEmpty(a.b()))) {
                                        cVar = new c();
                                        cVar.a(TextUtils.isEmpty(a.c()) ^ 1);
                                        cVar.a(a.b());
                                        cVar.a(a.d());
                                        cVar.b(a.c());
                                        cVar.b(a.e());
                                        PurchaseGoogleManager.this.k.put(str, cVar);
                                    }
                                    if (dVar != null) {
                                        dVar.onGoolgeProductPriceQueryConpleted(cVar);
                                    }
                                }
                            });
                        } catch (IllegalStateException e) {
                            ThrowableExtension.printStackTrace(e);
                        } catch (Exception e2) {
                            ThrowableExtension.printStackTrace(e2);
                        }
                        return;
                    }
                    if (dVar != null) {
                        dVar.onGoolgeProductPriceQueryConpleted(null);
                    }
                }
            });
        } else if (dVar != null) {
            dVar.onGoolgeProductPriceQueryConpleted((c) this.k.get(str));
        }
    }

    public boolean a(IabHelper iabHelper) {
        return iabHelper == null ? false : iabHelper.b();
    }

    private void k() {
        a(true);
    }

    private void a(String str, String str2, final e eVar) {
        Context a = ai.a();
        if (a instanceof BaseActivity) {
            ((BaseActivity) a).showProgressDialog(Boolean.valueOf(false), a.getString(R.string.updating));
        }
        String a2 = i.a();
        HashMap a3 = a(str, str2, this.l, c.h);
        URLManager uRLManager = new URLManager();
        uRLManager.c(1);
        uRLManager.a(a3);
        uRLManager.b(Boolean.valueOf(true));
        uRLManager.a(a2);
        uRLManager.a(Priority.HIGH);
        uRLManager.a(UpdatePaymentResponse.class);
        uRLManager.d(1);
        uRLManager.i(false);
        i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
                if (obj != null) {
                    PurchaseGoogleManager.this.p = (UpdatePaymentResponse) obj;
                } else {
                    PurchaseGoogleManager.this.p = null;
                }
                eVar.a(PurchaseGoogleManager.this.p);
            }

            public void onErrorResponse(BusinessObject businessObject) {
                if (businessObject.getVolleyError() != null) {
                    PurchaseGoogleManager.this.a.onFailure(GaanaApplication.getContext().getString(R.string.gaana_subscription_update_failed));
                }
            }
        }, uRLManager);
    }

    private HashMap<String, String> a(String str, String str2, ProductItem productItem, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", "updatepayment");
        hashMap.put("productid", productItem.getP_id());
        hashMap.put("deviceid", i.e());
        hashMap.put("paymentamount", productItem.getP_cost());
        hashMap.put("paymentid", str);
        hashMap.put("paymentmethod", LoginManager.TAG_SUBTYPE_GOOGLE);
        hashMap.put("googletoken", str2);
        hashMap.put(AccountKitGraphConstants.TOKEN_RESPONSE_TYPE, this.f.getCurrentUser().getAuthToken());
        if (z) {
            hashMap.put("is_restore_purchase", "1");
        }
        if (this.f.getCurrentUser().getUserProfile() != null) {
            hashMap.put("userid", this.f.getCurrentUser().getUserProfile().getUserId());
        }
        return hashMap;
    }

    private void a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/gaanaplusservice_nxtgen.php?type=app_intro_postbak&p_status=");
        stringBuilder.append(str);
        stringBuilder.append("&pr_id=");
        stringBuilder.append(str2);
        str = stringBuilder.toString();
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.a(String.class);
        uRLManager.a(Priority.HIGH);
        i.a().a(new af() {
            public void onRetreivalComplete(Object obj) {
            }

            public void onErrorResponse(BusinessObject businessObject) {
                Log.e("Error", "businessObject");
            }
        }, uRLManager);
    }

    public void d() {
        this.r = new Handler();
        a(new b() {
            public void a(GoogleIntroductoryPriceConfig googleIntroductoryPriceConfig) {
                if (GaanaApplication.getInstance().getmIntroductoryPriceConfig() == null && googleIntroductoryPriceConfig != null) {
                    GaanaApplication.getInstance().setmIntroductoryPriceConfig(googleIntroductoryPriceConfig);
                }
            }
        });
    }

    private void a(final b bVar) {
        URLManager uRLManager = new URLManager();
        uRLManager.a("https://api.gaana.com/gaanaplusservice_nxtgen.php?type=gplus_app_intro");
        uRLManager.a(GoogleIntroductoryPriceConfig.class);
        uRLManager.a(Priority.HIGH);
        i.a().a(new af() {
            public void onRetreivalComplete(final Object obj) {
                PurchaseGoogleManager.this.r.post(new Runnable() {
                    public void run() {
                        if (obj instanceof GoogleIntroductoryPriceConfig) {
                            bVar.a((GoogleIntroductoryPriceConfig) obj);
                        } else {
                            bVar.a(null);
                        }
                    }
                });
            }

            public void onErrorResponse(BusinessObject businessObject) {
                PurchaseGoogleManager.this.r.post(new Runnable() {
                    public void run() {
                        bVar.a(null);
                    }
                });
            }
        }, uRLManager);
    }

    public GoogleIntroductoryPriceConfig e() {
        return GaanaApplication.getInstance().getmIntroductoryPriceConfig();
    }
}
