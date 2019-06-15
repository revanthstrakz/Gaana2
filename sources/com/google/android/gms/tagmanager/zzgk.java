package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ShowFirstParty
@VisibleForTesting
public final class zzgk extends zzgh {
    private static final String ID = zza.UNIVERSAL_ANALYTICS.toString();
    private static final String zzbhh = zzb.ACCOUNT.toString();
    private static final String zzbhi = zzb.ANALYTICS_PASS_THROUGH.toString();
    private static final String zzbhj = zzb.ENABLE_ECOMMERCE.toString();
    private static final String zzbhk = zzb.ECOMMERCE_USE_DATA_LAYER.toString();
    private static final String zzbhl = zzb.ECOMMERCE_MACRO_DATA.toString();
    private static final String zzbhm = zzb.ANALYTICS_FIELDS.toString();
    private static final String zzbhn = zzb.TRACK_TRANSACTION.toString();
    private static final String zzbho = zzb.TRANSACTION_DATALAYER_MAP.toString();
    private static final String zzbhp = zzb.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static final List<String> zzbhq = Arrays.asList(new String[]{ProductAction.ACTION_DETAIL, ProductAction.ACTION_CHECKOUT, ProductAction.ACTION_CHECKOUT_OPTION, "click", ProductAction.ACTION_ADD, ProductAction.ACTION_REMOVE, ProductAction.ACTION_PURCHASE, ProductAction.ACTION_REFUND});
    private static final Pattern zzbhr = Pattern.compile("dimension(\\d+)");
    private static final Pattern zzbhs = Pattern.compile("metric(\\d+)");
    private static Map<String, String> zzbht;
    private static Map<String, String> zzbhu;
    private final DataLayer zzazp;
    private final Set<String> zzbhv;
    private final zzgf zzbhw;

    public zzgk(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new zzgf(context));
    }

    @VisibleForTesting
    private zzgk(Context context, DataLayer dataLayer, zzgf zzgf) {
        super(ID, new String[0]);
        this.zzazp = dataLayer;
        this.zzbhw = zzgf;
        this.zzbhv = new HashSet();
        this.zzbhv.add("");
        this.zzbhv.add("0");
        this.zzbhv.add(InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
    }

    private static boolean zzc(Map<String, zzp> map, String str) {
        zzp zzp = (zzp) map.get(str);
        if (zzp == null) {
            return false;
        }
        return zzgj.zzg(zzp).booleanValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:70:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x005c  */
    public final void zze(java.util.Map<java.lang.String, com.google.android.gms.internal.measurement.zzp> r11) {
        /*
        r10 = this;
        r0 = r10.zzbhw;
        r1 = "_GTM_DEFAULT_TRACKER_";
        r0 = r0.zzec(r1);
        r1 = "collect_adid";
        r1 = zzc(r11, r1);
        r0.enableAdvertisingIdCollection(r1);
        r1 = zzbhj;
        r1 = zzc(r11, r1);
        r2 = 0;
        r3 = 0;
        if (r1 == 0) goto L_0x02c4;
    L_0x001b:
        r1 = new com.google.android.gms.analytics.HitBuilders$ScreenViewBuilder;
        r1.<init>();
        r4 = zzbhm;
        r4 = r11.get(r4);
        r4 = (com.google.android.gms.internal.measurement.zzp) r4;
        r4 = r10.zzj(r4);
        r1.setAll(r4);
        r5 = zzbhk;
        r5 = zzc(r11, r5);
        if (r5 == 0) goto L_0x0046;
    L_0x0037:
        r11 = r10.zzazp;
        r5 = "ecommerce";
        r11 = r11.get(r5);
        r5 = r11 instanceof java.util.Map;
        if (r5 == 0) goto L_0x0059;
    L_0x0043:
        r11 = (java.util.Map) r11;
        goto L_0x005a;
    L_0x0046:
        r5 = zzbhl;
        r11 = r11.get(r5);
        r11 = (com.google.android.gms.internal.measurement.zzp) r11;
        r11 = com.google.android.gms.tagmanager.zzgj.zzh(r11);
        r5 = r11 instanceof java.util.Map;
        if (r5 == 0) goto L_0x0059;
    L_0x0056:
        r11 = (java.util.Map) r11;
        goto L_0x005a;
    L_0x0059:
        r11 = r3;
    L_0x005a:
        if (r11 == 0) goto L_0x02bc;
    L_0x005c:
        r5 = "&cu";
        r4 = r4.get(r5);
        r4 = (java.lang.String) r4;
        if (r4 != 0) goto L_0x006e;
    L_0x0066:
        r4 = "currencyCode";
        r4 = r11.get(r4);
        r4 = (java.lang.String) r4;
    L_0x006e:
        if (r4 == 0) goto L_0x0075;
    L_0x0070:
        r5 = "&cu";
        r1.set(r5, r4);
    L_0x0075:
        r4 = "impressions";
        r4 = r11.get(r4);
        r5 = r4 instanceof java.util.List;
        if (r5 == 0) goto L_0x00c0;
    L_0x007f:
        r4 = (java.util.List) r4;
        r4 = r4.iterator();
    L_0x0085:
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x00c0;
    L_0x008b:
        r5 = r4.next();
        r5 = (java.util.Map) r5;
        r6 = zzg(r5);	 Catch:{ RuntimeException -> 0x00a1 }
        r7 = "list";
        r5 = r5.get(r7);	 Catch:{ RuntimeException -> 0x00a1 }
        r5 = (java.lang.String) r5;	 Catch:{ RuntimeException -> 0x00a1 }
        r1.addImpression(r6, r5);	 Catch:{ RuntimeException -> 0x00a1 }
        goto L_0x0085;
    L_0x00a1:
        r5 = move-exception;
        r6 = "Failed to extract a product from DataLayer. ";
        r5 = r5.getMessage();
        r5 = java.lang.String.valueOf(r5);
        r7 = r5.length();
        if (r7 == 0) goto L_0x00b7;
    L_0x00b2:
        r5 = r6.concat(r5);
        goto L_0x00bc;
    L_0x00b7:
        r5 = new java.lang.String;
        r5.<init>(r6);
    L_0x00bc:
        com.google.android.gms.tagmanager.zzdi.e(r5);
        goto L_0x0085;
    L_0x00c0:
        r4 = "promoClick";
        r4 = r11.containsKey(r4);
        if (r4 == 0) goto L_0x00d9;
    L_0x00c8:
        r3 = "promoClick";
        r3 = r11.get(r3);
        r3 = (java.util.Map) r3;
        r4 = "promotions";
        r3 = r3.get(r4);
        r3 = (java.util.List) r3;
        goto L_0x00f1;
    L_0x00d9:
        r4 = "promoView";
        r4 = r11.containsKey(r4);
        if (r4 == 0) goto L_0x00f1;
    L_0x00e1:
        r3 = "promoView";
        r3 = r11.get(r3);
        r3 = (java.util.Map) r3;
        r4 = "promotions";
        r3 = r3.get(r4);
        r3 = (java.util.List) r3;
    L_0x00f1:
        if (r3 == 0) goto L_0x0186;
    L_0x00f3:
        r3 = r3.iterator();
    L_0x00f7:
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x016f;
    L_0x00fd:
        r4 = r3.next();
        r4 = (java.util.Map) r4;
        r5 = new com.google.android.gms.analytics.ecommerce.Promotion;	 Catch:{ RuntimeException -> 0x0150 }
        r5.<init>();	 Catch:{ RuntimeException -> 0x0150 }
        r6 = "id";
        r6 = r4.get(r6);	 Catch:{ RuntimeException -> 0x0150 }
        r6 = (java.lang.String) r6;	 Catch:{ RuntimeException -> 0x0150 }
        if (r6 == 0) goto L_0x0119;
    L_0x0112:
        r6 = java.lang.String.valueOf(r6);	 Catch:{ RuntimeException -> 0x0150 }
        r5.setId(r6);	 Catch:{ RuntimeException -> 0x0150 }
    L_0x0119:
        r6 = "name";
        r6 = r4.get(r6);	 Catch:{ RuntimeException -> 0x0150 }
        r6 = (java.lang.String) r6;	 Catch:{ RuntimeException -> 0x0150 }
        if (r6 == 0) goto L_0x012a;
    L_0x0123:
        r6 = java.lang.String.valueOf(r6);	 Catch:{ RuntimeException -> 0x0150 }
        r5.setName(r6);	 Catch:{ RuntimeException -> 0x0150 }
    L_0x012a:
        r6 = "creative";
        r6 = r4.get(r6);	 Catch:{ RuntimeException -> 0x0150 }
        r6 = (java.lang.String) r6;	 Catch:{ RuntimeException -> 0x0150 }
        if (r6 == 0) goto L_0x013b;
    L_0x0134:
        r6 = java.lang.String.valueOf(r6);	 Catch:{ RuntimeException -> 0x0150 }
        r5.setCreative(r6);	 Catch:{ RuntimeException -> 0x0150 }
    L_0x013b:
        r6 = "position";
        r4 = r4.get(r6);	 Catch:{ RuntimeException -> 0x0150 }
        r4 = (java.lang.String) r4;	 Catch:{ RuntimeException -> 0x0150 }
        if (r4 == 0) goto L_0x014c;
    L_0x0145:
        r4 = java.lang.String.valueOf(r4);	 Catch:{ RuntimeException -> 0x0150 }
        r5.setPosition(r4);	 Catch:{ RuntimeException -> 0x0150 }
    L_0x014c:
        r1.addPromotion(r5);	 Catch:{ RuntimeException -> 0x0150 }
        goto L_0x00f7;
    L_0x0150:
        r4 = move-exception;
        r5 = "Failed to extract a promotion from DataLayer. ";
        r4 = r4.getMessage();
        r4 = java.lang.String.valueOf(r4);
        r6 = r4.length();
        if (r6 == 0) goto L_0x0166;
    L_0x0161:
        r4 = r5.concat(r4);
        goto L_0x016b;
    L_0x0166:
        r4 = new java.lang.String;
        r4.<init>(r5);
    L_0x016b:
        com.google.android.gms.tagmanager.zzdi.e(r4);
        goto L_0x00f7;
    L_0x016f:
        r3 = "promoClick";
        r3 = r11.containsKey(r3);
        if (r3 == 0) goto L_0x017f;
    L_0x0177:
        r3 = "&promoa";
        r4 = "click";
        r1.set(r3, r4);
        goto L_0x0187;
    L_0x017f:
        r2 = "&promoa";
        r3 = "view";
        r1.set(r2, r3);
    L_0x0186:
        r2 = 1;
    L_0x0187:
        if (r2 == 0) goto L_0x02bc;
    L_0x0189:
        r2 = zzbhq;
        r2 = r2.iterator();
    L_0x018f:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x02bc;
    L_0x0195:
        r3 = r2.next();
        r3 = (java.lang.String) r3;
        r4 = r11.containsKey(r3);
        if (r4 == 0) goto L_0x018f;
    L_0x01a1:
        r11 = r11.get(r3);
        r11 = (java.util.Map) r11;
        r2 = "products";
        r2 = r11.get(r2);
        r2 = (java.util.List) r2;
        if (r2 == 0) goto L_0x01e8;
    L_0x01b1:
        r2 = r2.iterator();
    L_0x01b5:
        r4 = r2.hasNext();
        if (r4 == 0) goto L_0x01e8;
    L_0x01bb:
        r4 = r2.next();
        r4 = (java.util.Map) r4;
        r4 = zzg(r4);	 Catch:{ RuntimeException -> 0x01c9 }
        r1.addProduct(r4);	 Catch:{ RuntimeException -> 0x01c9 }
        goto L_0x01b5;
    L_0x01c9:
        r4 = move-exception;
        r5 = "Failed to extract a product from DataLayer. ";
        r4 = r4.getMessage();
        r4 = java.lang.String.valueOf(r4);
        r6 = r4.length();
        if (r6 == 0) goto L_0x01df;
    L_0x01da:
        r4 = r5.concat(r4);
        goto L_0x01e4;
    L_0x01df:
        r4 = new java.lang.String;
        r4.<init>(r5);
    L_0x01e4:
        com.google.android.gms.tagmanager.zzdi.e(r4);
        goto L_0x01b5;
    L_0x01e8:
        r2 = "actionField";
        r2 = r11.containsKey(r2);	 Catch:{ RuntimeException -> 0x029e }
        if (r2 == 0) goto L_0x0295;
    L_0x01f0:
        r2 = "actionField";
        r11 = r11.get(r2);	 Catch:{ RuntimeException -> 0x029e }
        r11 = (java.util.Map) r11;	 Catch:{ RuntimeException -> 0x029e }
        r2 = new com.google.android.gms.analytics.ecommerce.ProductAction;	 Catch:{ RuntimeException -> 0x029e }
        r2.<init>(r3);	 Catch:{ RuntimeException -> 0x029e }
        r3 = "id";
        r3 = r11.get(r3);	 Catch:{ RuntimeException -> 0x029e }
        if (r3 == 0) goto L_0x020c;
    L_0x0205:
        r3 = java.lang.String.valueOf(r3);	 Catch:{ RuntimeException -> 0x029e }
        r2.setTransactionId(r3);	 Catch:{ RuntimeException -> 0x029e }
    L_0x020c:
        r3 = "affiliation";
        r3 = r11.get(r3);	 Catch:{ RuntimeException -> 0x029e }
        if (r3 == 0) goto L_0x021b;
    L_0x0214:
        r3 = java.lang.String.valueOf(r3);	 Catch:{ RuntimeException -> 0x029e }
        r2.setTransactionAffiliation(r3);	 Catch:{ RuntimeException -> 0x029e }
    L_0x021b:
        r3 = "coupon";
        r3 = r11.get(r3);	 Catch:{ RuntimeException -> 0x029e }
        if (r3 == 0) goto L_0x022a;
    L_0x0223:
        r3 = java.lang.String.valueOf(r3);	 Catch:{ RuntimeException -> 0x029e }
        r2.setTransactionCouponCode(r3);	 Catch:{ RuntimeException -> 0x029e }
    L_0x022a:
        r3 = "list";
        r3 = r11.get(r3);	 Catch:{ RuntimeException -> 0x029e }
        if (r3 == 0) goto L_0x0239;
    L_0x0232:
        r3 = java.lang.String.valueOf(r3);	 Catch:{ RuntimeException -> 0x029e }
        r2.setProductActionList(r3);	 Catch:{ RuntimeException -> 0x029e }
    L_0x0239:
        r3 = "option";
        r3 = r11.get(r3);	 Catch:{ RuntimeException -> 0x029e }
        if (r3 == 0) goto L_0x0248;
    L_0x0241:
        r3 = java.lang.String.valueOf(r3);	 Catch:{ RuntimeException -> 0x029e }
        r2.setCheckoutOptions(r3);	 Catch:{ RuntimeException -> 0x029e }
    L_0x0248:
        r3 = "revenue";
        r3 = r11.get(r3);	 Catch:{ RuntimeException -> 0x029e }
        if (r3 == 0) goto L_0x025b;
    L_0x0250:
        r3 = zzn(r3);	 Catch:{ RuntimeException -> 0x029e }
        r3 = r3.doubleValue();	 Catch:{ RuntimeException -> 0x029e }
        r2.setTransactionRevenue(r3);	 Catch:{ RuntimeException -> 0x029e }
    L_0x025b:
        r3 = "tax";
        r3 = r11.get(r3);	 Catch:{ RuntimeException -> 0x029e }
        if (r3 == 0) goto L_0x026e;
    L_0x0263:
        r3 = zzn(r3);	 Catch:{ RuntimeException -> 0x029e }
        r3 = r3.doubleValue();	 Catch:{ RuntimeException -> 0x029e }
        r2.setTransactionTax(r3);	 Catch:{ RuntimeException -> 0x029e }
    L_0x026e:
        r3 = "shipping";
        r3 = r11.get(r3);	 Catch:{ RuntimeException -> 0x029e }
        if (r3 == 0) goto L_0x0281;
    L_0x0276:
        r3 = zzn(r3);	 Catch:{ RuntimeException -> 0x029e }
        r3 = r3.doubleValue();	 Catch:{ RuntimeException -> 0x029e }
        r2.setTransactionShipping(r3);	 Catch:{ RuntimeException -> 0x029e }
    L_0x0281:
        r3 = "step";
        r11 = r11.get(r3);	 Catch:{ RuntimeException -> 0x029e }
        if (r11 == 0) goto L_0x029a;
    L_0x0289:
        r11 = zzo(r11);	 Catch:{ RuntimeException -> 0x029e }
        r11 = r11.intValue();	 Catch:{ RuntimeException -> 0x029e }
        r2.setCheckoutStep(r11);	 Catch:{ RuntimeException -> 0x029e }
        goto L_0x029a;
    L_0x0295:
        r2 = new com.google.android.gms.analytics.ecommerce.ProductAction;	 Catch:{ RuntimeException -> 0x029e }
        r2.<init>(r3);	 Catch:{ RuntimeException -> 0x029e }
    L_0x029a:
        r1.setProductAction(r2);	 Catch:{ RuntimeException -> 0x029e }
        goto L_0x02bc;
    L_0x029e:
        r11 = move-exception;
        r2 = "Failed to extract a product action from DataLayer. ";
        r11 = r11.getMessage();
        r11 = java.lang.String.valueOf(r11);
        r3 = r11.length();
        if (r3 == 0) goto L_0x02b4;
    L_0x02af:
        r11 = r2.concat(r11);
        goto L_0x02b9;
    L_0x02b4:
        r11 = new java.lang.String;
        r11.<init>(r2);
    L_0x02b9:
        com.google.android.gms.tagmanager.zzdi.e(r11);
    L_0x02bc:
        r11 = r1.build();
        r0.send(r11);
        return;
    L_0x02c4:
        r1 = zzbhi;
        r1 = zzc(r11, r1);
        if (r1 == 0) goto L_0x02dc;
    L_0x02cc:
        r1 = zzbhm;
        r11 = r11.get(r1);
        r11 = (com.google.android.gms.internal.measurement.zzp) r11;
        r11 = r10.zzj(r11);
        r0.send(r11);
        return;
    L_0x02dc:
        r1 = zzbhn;
        r1 = zzc(r11, r1);
        if (r1 == 0) goto L_0x0479;
    L_0x02e4:
        r1 = "transactionId";
        r1 = r10.zzeh(r1);
        if (r1 != 0) goto L_0x02f2;
    L_0x02ec:
        r11 = "Cannot find transactionId in data layer.";
        com.google.android.gms.tagmanager.zzdi.e(r11);
        return;
    L_0x02f2:
        r4 = new java.util.ArrayList;
        r4.<init>();
        r5 = zzbhm;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r5 = r11.get(r5);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r5 = (com.google.android.gms.internal.measurement.zzp) r5;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r5 = r10.zzj(r5);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r6 = "&t";
        r7 = "transaction";
        r5.put(r6, r7);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r6 = zzbho;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r6 = r11.get(r6);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r6 = (com.google.android.gms.internal.measurement.zzp) r6;	 Catch:{ IllegalArgumentException -> 0x0472 }
        if (r6 == 0) goto L_0x0319;
    L_0x0314:
        r6 = zzi(r6);	 Catch:{ IllegalArgumentException -> 0x0472 }
        goto L_0x0350;
    L_0x0319:
        r6 = zzbht;	 Catch:{ IllegalArgumentException -> 0x0472 }
        if (r6 != 0) goto L_0x034e;
    L_0x031d:
        r6 = new java.util.HashMap;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r6.<init>();	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = "transactionId";
        r8 = "&ti";
        r6.put(r7, r8);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = "transactionAffiliation";
        r8 = "&ta";
        r6.put(r7, r8);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = "transactionTax";
        r8 = "&tt";
        r6.put(r7, r8);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = "transactionShipping";
        r8 = "&ts";
        r6.put(r7, r8);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = "transactionTotal";
        r8 = "&tr";
        r6.put(r7, r8);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = "transactionCurrency";
        r8 = "&cu";
        r6.put(r7, r8);	 Catch:{ IllegalArgumentException -> 0x0472 }
        zzbht = r6;	 Catch:{ IllegalArgumentException -> 0x0472 }
    L_0x034e:
        r6 = zzbht;	 Catch:{ IllegalArgumentException -> 0x0472 }
    L_0x0350:
        r6 = r6.entrySet();	 Catch:{ IllegalArgumentException -> 0x0472 }
        r6 = r6.iterator();	 Catch:{ IllegalArgumentException -> 0x0472 }
    L_0x0358:
        r7 = r6.hasNext();	 Catch:{ IllegalArgumentException -> 0x0472 }
        if (r7 == 0) goto L_0x0378;
    L_0x035e:
        r7 = r6.next();	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = (java.util.Map.Entry) r7;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r8 = r7.getValue();	 Catch:{ IllegalArgumentException -> 0x0472 }
        r8 = (java.lang.String) r8;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = r7.getKey();	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = (java.lang.String) r7;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = r10.zzeh(r7);	 Catch:{ IllegalArgumentException -> 0x0472 }
        zzd(r5, r8, r7);	 Catch:{ IllegalArgumentException -> 0x0472 }
        goto L_0x0358;
    L_0x0378:
        r4.add(r5);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r5 = "transactionProducts";
        r6 = r10.zzazp;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r5 = r6.get(r5);	 Catch:{ IllegalArgumentException -> 0x0472 }
        if (r5 != 0) goto L_0x0386;
    L_0x0385:
        goto L_0x03b2;
    L_0x0386:
        r3 = r5 instanceof java.util.List;	 Catch:{ IllegalArgumentException -> 0x0472 }
        if (r3 != 0) goto L_0x0392;
    L_0x038a:
        r11 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r0 = "transactionProducts should be of type List.";
        r11.<init>(r0);	 Catch:{ IllegalArgumentException -> 0x0472 }
        throw r11;	 Catch:{ IllegalArgumentException -> 0x0472 }
    L_0x0392:
        r3 = r5;
        r3 = (java.util.List) r3;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r3 = r3.iterator();	 Catch:{ IllegalArgumentException -> 0x0472 }
    L_0x0399:
        r6 = r3.hasNext();	 Catch:{ IllegalArgumentException -> 0x0472 }
        if (r6 == 0) goto L_0x03af;
    L_0x039f:
        r6 = r3.next();	 Catch:{ IllegalArgumentException -> 0x0472 }
        r6 = r6 instanceof java.util.Map;	 Catch:{ IllegalArgumentException -> 0x0472 }
        if (r6 != 0) goto L_0x0399;
    L_0x03a7:
        r11 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r0 = "Each element of transactionProducts should be of type Map.";
        r11.<init>(r0);	 Catch:{ IllegalArgumentException -> 0x0472 }
        throw r11;	 Catch:{ IllegalArgumentException -> 0x0472 }
    L_0x03af:
        r3 = r5;
        r3 = (java.util.List) r3;	 Catch:{ IllegalArgumentException -> 0x0472 }
    L_0x03b2:
        if (r3 == 0) goto L_0x045d;
    L_0x03b4:
        r3 = r3.iterator();	 Catch:{ IllegalArgumentException -> 0x0472 }
    L_0x03b8:
        r5 = r3.hasNext();	 Catch:{ IllegalArgumentException -> 0x0472 }
        if (r5 == 0) goto L_0x045d;
    L_0x03be:
        r5 = r3.next();	 Catch:{ IllegalArgumentException -> 0x0472 }
        r5 = (java.util.Map) r5;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r6 = "name";
        r6 = r5.get(r6);	 Catch:{ IllegalArgumentException -> 0x0472 }
        if (r6 != 0) goto L_0x03d2;
    L_0x03cc:
        r11 = "Unable to send transaction item hit due to missing 'name' field.";
        com.google.android.gms.tagmanager.zzdi.e(r11);	 Catch:{ IllegalArgumentException -> 0x0472 }
        return;
    L_0x03d2:
        r6 = zzbhm;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r6 = r11.get(r6);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r6 = (com.google.android.gms.internal.measurement.zzp) r6;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r6 = r10.zzj(r6);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = "&t";
        r8 = "item";
        r6.put(r7, r8);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = "&ti";
        r6.put(r7, r1);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = zzbhp;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = r11.get(r7);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = (com.google.android.gms.internal.measurement.zzp) r7;	 Catch:{ IllegalArgumentException -> 0x0472 }
        if (r7 == 0) goto L_0x03f9;
    L_0x03f4:
        r7 = zzi(r7);	 Catch:{ IllegalArgumentException -> 0x0472 }
        goto L_0x0430;
    L_0x03f9:
        r7 = zzbhu;	 Catch:{ IllegalArgumentException -> 0x0472 }
        if (r7 != 0) goto L_0x042e;
    L_0x03fd:
        r7 = new java.util.HashMap;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7.<init>();	 Catch:{ IllegalArgumentException -> 0x0472 }
        r8 = "name";
        r9 = "&in";
        r7.put(r8, r9);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r8 = "sku";
        r9 = "&ic";
        r7.put(r8, r9);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r8 = "category";
        r9 = "&iv";
        r7.put(r8, r9);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r8 = "price";
        r9 = "&ip";
        r7.put(r8, r9);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r8 = "quantity";
        r9 = "&iq";
        r7.put(r8, r9);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r8 = "currency";
        r9 = "&cu";
        r7.put(r8, r9);	 Catch:{ IllegalArgumentException -> 0x0472 }
        zzbhu = r7;	 Catch:{ IllegalArgumentException -> 0x0472 }
    L_0x042e:
        r7 = zzbhu;	 Catch:{ IllegalArgumentException -> 0x0472 }
    L_0x0430:
        r7 = r7.entrySet();	 Catch:{ IllegalArgumentException -> 0x0472 }
        r7 = r7.iterator();	 Catch:{ IllegalArgumentException -> 0x0472 }
    L_0x0438:
        r8 = r7.hasNext();	 Catch:{ IllegalArgumentException -> 0x0472 }
        if (r8 == 0) goto L_0x0458;
    L_0x043e:
        r8 = r7.next();	 Catch:{ IllegalArgumentException -> 0x0472 }
        r8 = (java.util.Map.Entry) r8;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r9 = r8.getValue();	 Catch:{ IllegalArgumentException -> 0x0472 }
        r9 = (java.lang.String) r9;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r8 = r8.getKey();	 Catch:{ IllegalArgumentException -> 0x0472 }
        r8 = r5.get(r8);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r8 = (java.lang.String) r8;	 Catch:{ IllegalArgumentException -> 0x0472 }
        zzd(r6, r9, r8);	 Catch:{ IllegalArgumentException -> 0x0472 }
        goto L_0x0438;
    L_0x0458:
        r4.add(r6);	 Catch:{ IllegalArgumentException -> 0x0472 }
        goto L_0x03b8;
    L_0x045d:
        r4 = (java.util.ArrayList) r4;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r11 = r4.size();	 Catch:{ IllegalArgumentException -> 0x0472 }
    L_0x0463:
        if (r2 >= r11) goto L_0x0471;
    L_0x0465:
        r1 = r4.get(r2);	 Catch:{ IllegalArgumentException -> 0x0472 }
        r2 = r2 + 1;
        r1 = (java.util.Map) r1;	 Catch:{ IllegalArgumentException -> 0x0472 }
        r0.send(r1);	 Catch:{ IllegalArgumentException -> 0x0472 }
        goto L_0x0463;
    L_0x0471:
        return;
    L_0x0472:
        r11 = move-exception;
        r0 = "Unable to send transaction";
        com.google.android.gms.tagmanager.zzdi.zza(r0, r11);
        return;
    L_0x0479:
        r11 = "Ignoring unknown tag.";
        com.google.android.gms.tagmanager.zzdi.zzab(r11);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzgk.zze(java.util.Map):void");
    }

    private final String zzeh(String str) {
        Object obj = this.zzazp.get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private static void zzd(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private static Product zzg(Map<String, Object> map) {
        String str;
        Product product = new Product();
        Object obj = map.get("id");
        if (obj != null) {
            product.setId(String.valueOf(obj));
        }
        obj = map.get("name");
        if (obj != null) {
            product.setName(String.valueOf(obj));
        }
        obj = map.get("brand");
        if (obj != null) {
            product.setBrand(String.valueOf(obj));
        }
        obj = map.get("category");
        if (obj != null) {
            product.setCategory(String.valueOf(obj));
        }
        obj = map.get("variant");
        if (obj != null) {
            product.setVariant(String.valueOf(obj));
        }
        obj = map.get("coupon");
        if (obj != null) {
            product.setCouponCode(String.valueOf(obj));
        }
        obj = map.get("position");
        if (obj != null) {
            product.setPosition(zzo(obj).intValue());
        }
        obj = map.get(InMobiNetworkValues.PRICE);
        if (obj != null) {
            product.setPrice(zzn(obj).doubleValue());
        }
        obj = map.get("quantity");
        if (obj != null) {
            product.setQuantity(zzo(obj).intValue());
        }
        for (String str2 : map.keySet()) {
            String str22;
            Matcher matcher = zzbhr.matcher(str22);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(str22)));
                } catch (NumberFormatException unused) {
                    str = "illegal number in custom dimension value: ";
                    str22 = String.valueOf(str22);
                    zzdi.zzab(str22.length() != 0 ? str.concat(str22) : new String(str));
                }
            } else {
                matcher = zzbhs.matcher(str22);
                if (matcher.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher.group(1)), zzo(map.get(str22)).intValue());
                    } catch (NumberFormatException unused2) {
                        str = "illegal number in custom metric value: ";
                        str22 = String.valueOf(str22);
                        zzdi.zzab(str22.length() != 0 ? str.concat(str22) : new String(str));
                    }
                }
            }
        }
        return product;
    }

    private static Map<String, String> zzi(zzp zzp) {
        Object zzh = zzgj.zzh(zzp);
        if (!(zzh instanceof Map)) {
            return null;
        }
        Map map = (Map) zzh;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Entry entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private final Map<String, String> zzj(zzp zzp) {
        if (zzp == null) {
            return new HashMap();
        }
        Map zzi = zzi(zzp);
        if (zzi == null) {
            return new HashMap();
        }
        String str = (String) zzi.get("&aip");
        if (str != null && this.zzbhv.contains(str.toLowerCase())) {
            zzi.remove("&aip");
        }
        return zzi;
    }

    private static Double zzn(Object obj) {
        String str;
        String valueOf;
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e) {
                str = "Cannot convert the object to Double: ";
                valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        } else {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            str = "Cannot convert the object to Double: ";
            valueOf = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    private static Integer zzo(Object obj) {
        String str;
        String valueOf;
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e) {
                str = "Cannot convert the object to Integer: ";
                valueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        } else {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            str = "Cannot convert the object to Integer: ";
            valueOf = String.valueOf(obj.toString());
            throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    public final /* bridge */ /* synthetic */ zzp zzc(Map map) {
        return super.zzc(map);
    }

    public final /* bridge */ /* synthetic */ boolean zznk() {
        return super.zznk();
    }

    public final /* bridge */ /* synthetic */ Set zzou() {
        return super.zzou();
    }

    public final /* bridge */ /* synthetic */ String zzot() {
        return super.zzot();
    }
}
