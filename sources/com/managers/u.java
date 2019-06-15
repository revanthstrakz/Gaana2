package com.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.constants.Constants;
import com.gaana.application.GaanaApplication;
import com.gaana.login.LoginManager;
import com.gaana.login.UserInfo;
import com.gaana.models.Languages;
import com.gaana.models.Languages.Language;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.AppViewBuilder;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder;
import com.google.android.gms.analytics.HitBuilders.TimingBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.i.d;
import com.services.n;
import com.utilities.Util;
import com.utilities.f;
import com.utilities.g;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class u {
    private static u a;
    private static GoogleAnalytics b;
    private static Tracker c;
    private static Tracker d;
    private final HashMap<String, HashMap<String, Integer>> e;
    private String f = null;
    private String g = null;
    private String h = null;
    private Context i = GaanaApplication.getContext();

    private u() {
        a("Gaana-App - -", Constants.cz);
        this.e = new HashMap();
    }

    public static u a() {
        if (a == null) {
            synchronized (u.class) {
                if (a == null) {
                    a = new u();
                }
            }
        }
        return a;
    }

    public void a(String str, String str2) {
        b = GoogleAnalytics.getInstance(this.i);
        if (Constants.b) {
            b.getLogger().setLogLevel(0);
        }
        this.f = str;
        this.g = str2;
        if (c == null) {
            j();
        }
        PreferenceManager.getDefaultSharedPreferences(this.i).registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                if (str.equals("trackingPreference")) {
                    GoogleAnalytics.getInstance(u.this.i).setAppOptOut(sharedPreferences.getBoolean(str, false));
                }
            }
        });
    }

    public synchronized Tracker b() {
        return c;
    }

    private void j() {
        if (c == null) {
            c = b.newTracker(Constants.bY);
            c.setAppName(this.f);
            c.setAppVersion(this.g);
            c.enableAdvertisingIdCollection(true);
            d = b.newTracker(Constants.bY);
            d.setAppName(this.f);
            d.setAppVersion(this.g);
            d.setSampleRate(25.0d);
            if (this.h == null) {
                d.a(new Runnable() {
                    public void run() {
                        try {
                            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(u.this.i.getApplicationContext());
                            if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                                u.this.h = Secure.getString(u.this.i.getContentResolver(), "android_id");
                            } else {
                                u.this.h = advertisingIdInfo.getId();
                            }
                        } catch (Exception unused) {
                            u.this.h = Secure.getString(u.this.i.getContentResolver(), "android_id");
                        }
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                if (!TextUtils.isEmpty(u.this.h) && u.this.b() != null) {
                                    u.this.b().set("&uid", u.this.h);
                                }
                            }
                        });
                    }
                });
            }
        }
    }

    public void b(String str, String str2) {
        a(str, str2, null);
    }

    public void a(String str) {
        if (str != "" && str != null && b() != null) {
            b().setScreenName(str);
            b().send(new AppViewBuilder().build());
            b().setScreenName(null);
        }
    }

    public void a(String str, String str2, String str3) {
        HashMap hashMap = (HashMap) this.e.get(str);
        if (!(hashMap == null || hashMap.isEmpty())) {
            if (((Integer) hashMap.get("MASTER")).intValue() != 0) {
                Integer num = (Integer) hashMap.get(str2);
                if (num != null && num.intValue() == 0) {
                    return;
                }
            }
            return;
        }
        if (!(str == "" || str == null || b() == null)) {
            b().send(new EventBuilder().setCategory(str).setAction(str2).setLabel(str3).build());
        }
    }

    public void a(String str, String str2, int i) {
        HashMap hashMap = (HashMap) this.e.get(str);
        if (hashMap == null) {
            hashMap = new HashMap();
            hashMap.put(str2, Integer.valueOf(i));
            this.e.put(str, hashMap);
            return;
        }
        hashMap.put(str2, Integer.valueOf(i));
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6) {
        if (str != "" && str != null && b() != null) {
            b().send(((EventBuilder) ((EventBuilder) ((EventBuilder) new EventBuilder().setCategory(str).setAction(str2).setLabel(str3).setCustomDimension(14, str4)).setCustomDimension(15, str5)).setCustomDimension(16, str6)).build());
        }
    }

    public void a(String str, long j, String str2, String str3) {
        if (d != null) {
            d.send(new TimingBuilder().setCategory(str).setValue(j).setVariable(str2).setLabel(str3).build());
        }
    }

    public void a(int i, String str) {
        if (b() != null) {
            b().send(((EventBuilder) new EventBuilder().setCustomDimension(i, str)).build());
        }
    }

    public void b(String str, String str2, String str3) {
        if (c != null) {
            c.setScreenName(str);
            c.send(((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) new ScreenViewBuilder().setCustomDimension(8, str2)).setCustomDimension(9, str3)).setCustomDimension(7, f())).setCustomDimension(6, g())).setCustomDimension(4, e())).setCustomDimension(12, g.c())).setCustomDimension(18, Util.l(this.i))).setCustomDimension(19, Util.V())).setCustomDimension(22, k())).build());
        }
    }

    public void a(String str, String str2, String str3, String str4) {
        if (c != null) {
            c.setScreenName(str);
            c.send(((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) new ScreenViewBuilder().setCustomDimension(8, str2)).setCustomDimension(9, str3)).setCustomDimension(7, f())).setCustomDimension(6, g())).setCustomDimension(4, e())).setCustomDimension(10, str4)).setCustomDimension(12, g.c())).setCustomDimension(18, Util.l(this.i))).setCustomDimension(19, Util.V())).setCustomDimension(22, k())).build());
        }
    }

    public void c(final String str, final String str2, final String str3) {
        d.a(new Runnable() {
            public void run() {
                String str = "";
                Object a = n.a(com.services.d.a().c("PREFERENCE_LANGUAGE_SETTINGS", false));
                if (a instanceof Languages) {
                    Languages languages = (Languages) a;
                    if (languages.getArrListBusinessObj() != null) {
                        Iterator it = languages.getArrListBusinessObj().iterator();
                        while (it.hasNext()) {
                            Language language = (Language) it.next();
                            if (language.isPrefered() == 1) {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(str);
                                stringBuilder.append(language.getLanguage());
                                stringBuilder.append(",");
                                str = stringBuilder.toString();
                            }
                        }
                        if (!TextUtils.isEmpty(str)) {
                            str = str.substring(0, str.length() - 1);
                        }
                    }
                }
                final String b = f.b(u.this.i);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        if (u.c != null) {
                            u.c.setScreenName(str);
                            u.c.send(((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) ((ScreenViewBuilder) new ScreenViewBuilder().setCustomDimension(8, str2)).setCustomDimension(20, str)).setCustomDimension(21, b)).setCampaignParamsFromUrl(str3)).build());
                            if (u.d != null) {
                                u.d.setScreenName(str);
                                u.d.send(((ScreenViewBuilder) new ScreenViewBuilder().setCustomDimension(8, str2)).build());
                            }
                        }
                    }
                });
            }
        });
    }

    public void b(String str) {
        if (c != null) {
            c.send(((EventBuilder) new EventBuilder().setCustomDimension(26, str)).build());
        }
    }

    public void c() {
        if (c != null) {
            c.send(((EventBuilder) ((EventBuilder) ((EventBuilder) ((EventBuilder) new EventBuilder().setCustomDimension(7, f())).setCustomDimension(6, g())).setCustomDimension(4, e())).setCustomDimension(22, k())).build());
        }
    }

    public void d() {
        a(7, f());
    }

    public String e() {
        UserInfo currentUser = ((GaanaApplication) this.i.getApplicationContext()).getCurrentUser();
        String str = "Not logged in";
        if (currentUser == null || !currentUser.getLoginStatus() || currentUser.getLoginType() == null) {
            return str;
        }
        switch (currentUser.getLoginType().ordinal()) {
            case 0:
                return "Facebook";
            case 1:
                return "Email";
            case 2:
                return "Google";
            case 3:
                return "Mobile_No";
            default:
                return str;
        }
    }

    public String f() {
        GaanaApplication gaanaApplication = (GaanaApplication) this.i.getApplicationContext();
        String str = "Free";
        try {
            StringBuilder stringBuilder;
            if (gaanaApplication.getCurrentUser().getUserSubscriptionData().getExpiryDate().getTime() - Calendar.getInstance().getTimeInMillis() < 0) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("expired:");
                stringBuilder.append(gaanaApplication.getCurrentUser().getUserSubscriptionData().getSubscriptionType());
                return stringBuilder.toString();
            }
            if (gaanaApplication.getCurrentUser().getUserSubscriptionData().getAccountType() != 3) {
                if (gaanaApplication.getCurrentUser().getUserSubscriptionData().getAccountType() == 2) {
                }
                return str;
            }
            if (gaanaApplication.getCurrentUser().getUserSubscriptionData().getProductProperties().isDownloadEnabled()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("gaanaplus:");
                stringBuilder.append(gaanaApplication.getCurrentUser().getUserSubscriptionData().getSubscriptionType());
                String stringBuilder2 = stringBuilder.toString();
                try {
                    if (ap.a().f()) {
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("gaanaplusmini:");
                        stringBuilder3.append(gaanaApplication.getCurrentUser().getUserSubscriptionData().getSubscriptionType());
                        str = stringBuilder3.toString();
                        return str;
                    }
                } catch (Exception unused) {
                }
                return stringBuilder2;
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("noads:");
            stringBuilder.append(gaanaApplication.getCurrentUser().getUserSubscriptionData().getSubscriptionType());
            return stringBuilder.toString();
        } catch (Exception unused2) {
        }
    }

    public String g() {
        UserInfo currentUser = ((GaanaApplication) this.i.getApplicationContext()).getCurrentUser();
        String str = "Not logged in";
        return (currentUser == null || !currentUser.getLoginStatus() || currentUser.getUserProfile() == null) ? str : currentUser.getUserProfile().getUserId();
    }

    public void a(ProductItem productItem, int i) {
        ScreenViewBuilder screenViewBuilder = (ScreenViewBuilder) ((ScreenViewBuilder) new ScreenViewBuilder().addProduct(new Product().setId(TextUtils.isEmpty(productItem.getItem_id()) ? "EmptyId" : productItem.getItem_id()).setName(productItem.getDesc()).setPosition(i))).setProductAction(new ProductAction(ProductAction.ACTION_DETAIL));
        c.setScreenName("ProductView");
        c.send(screenViewBuilder.build());
    }

    public void a(ProductItem productItem, String str) {
        Product name = new Product().setId(str).setName(productItem.getDesc());
        ScreenViewBuilder screenViewBuilder = (ScreenViewBuilder) ((ScreenViewBuilder) new ScreenViewBuilder().addProduct(name)).setProductAction(new ProductAction(ProductAction.ACTION_ADD));
        c.setScreenName("ProductCart");
        c.send(screenViewBuilder.build());
    }

    public void a(ProductItem productItem, String str, String str2, int i) {
        Product position = new Product().setId(str2).setName(str).setPosition(i);
        if (TextUtils.isEmpty(productItem.getIs_trial()) || !productItem.getIs_trial().equalsIgnoreCase("Y")) {
            str2 = productItem.getP_payment_mode();
        } else {
            str2 = "trial";
        }
        ScreenViewBuilder screenViewBuilder = (ScreenViewBuilder) ((ScreenViewBuilder) new ScreenViewBuilder().addProduct(position)).setProductAction(new ProductAction(ProductAction.ACTION_CHECKOUT).setCheckoutStep(1).setCheckoutOptions(str2).setTransactionRevenue(Double.valueOf(productItem.getP_cost()).doubleValue()));
        c.setScreenName("ProductCheckoutStep1");
        c.send(screenViewBuilder.build());
    }

    public void a(ProductItem productItem, String str, String str2, String str3, String str4) {
        Product quantity = new Product().setId(str).setName(str2).setPrice(Double.valueOf(productItem.getP_cost()).doubleValue()).setCouponCode(str4).setQuantity(1);
        if (TextUtils.isEmpty(productItem.getIs_trial()) || !productItem.getIs_trial().equalsIgnoreCase("Y")) {
            str2 = productItem.getP_payment_mode();
        } else {
            str2 = "trial";
        }
        ScreenViewBuilder screenViewBuilder = (ScreenViewBuilder) ((ScreenViewBuilder) new ScreenViewBuilder().addProduct(quantity)).setProductAction(new ProductAction(ProductAction.ACTION_PURCHASE).setTransactionId(str3).setTransactionAffiliation(str2).setTransactionRevenue(Double.valueOf(productItem.getP_cost()).doubleValue()));
        c.setScreenName("Transaction");
        if (!TextUtils.isEmpty(productItem.getP_curr_code())) {
            c.set("&cu", productItem.getP_curr_code());
        }
        c.send(screenViewBuilder.build());
    }

    public void b(ProductItem productItem, String str) {
        Product name = new Product().setId(str).setName(productItem.getDesc());
        ScreenViewBuilder screenViewBuilder = (ScreenViewBuilder) ((ScreenViewBuilder) new ScreenViewBuilder().addProduct(name)).setProductAction(new ProductAction(ProductAction.ACTION_REMOVE));
        c.setScreenName("ProductRemove");
        c.send(screenViewBuilder.build());
    }

    private String k() {
        String str = InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
        return (LoginManager.getInstance().getUserInfo() == null || !LoginManager.getInstance().getUserInfo().isSocialEnabled()) ? str : "true";
    }
}
