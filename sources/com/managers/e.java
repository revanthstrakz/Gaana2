package com.managers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gaana.ColombiaAdScroller;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.EntityInfo;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.library.controls.CrossFadeImageView;
import com.managers.ColombiaManager.a;
import com.managers.ColombiaManager.b;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.o;
import com.services.d;
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
import com.til.colombia.android.service.ColombiaBannerView;
import com.til.colombia.android.service.ColombiaNativeSponsoredAdView;
import com.til.colombia.android.service.Item;
import com.til.colombia.android.service.ItemResponse;
import java.util.List;

public class e {
    public static String A = "0";
    public static String B = "0";
    public static String C = "0";
    public static String D = "0";
    public static String E = "0";
    public static String F = "0";
    public static String G = "0";
    public static String H = "0";
    public static String I = "0";
    public static String J = "0";
    public static String K = "0";
    public static String L = "0";
    public static String M = "0";
    public static String N = "0";
    public static String O = "";
    public static String P = "";
    public static String Q = "";
    public static String R = "";
    public static int S = -1;
    public static String T = "";
    public static String U = "";
    public static int V = -1;
    public static int W = -1;
    public static int X = -1;
    public static int Y = -1;
    public static int Z = -1;
    public static long a = 0;
    public static int aa = -1;
    public static int ab = -1;
    public static int ac = -1;
    public static int ad = -1;
    public static int ae = -1;
    public static int af = -1;
    public static int ag = -1;
    public static int ah = -1;
    public static int ai = -1;
    public static int aj = -1;
    public static int ak = -1;
    public static int al = -1;
    public static int am = -1;
    public static int an = -1;
    public static int ao = -1;
    public static int ap = -1;
    public static int aq = -1;
    public static int ar = -1;
    public static int as = -1;
    public static int at = -1;
    public static int au = -1;
    public static int av = -1;
    private static e aw = null;
    private static b ax = null;
    public static long b = 0;
    public static long c = 0;
    public static long d = 0;
    public static long e = 0;
    public static long f = 0;
    public static long g = 0;
    public static long h = 0;
    public static long i = 0;
    public static long j = 0;
    public static long k = 0;
    public static long l = 0;
    public static long m = 0;
    public static long n = 0;
    public static long o = 0;
    public static long p = 0;
    public static long q = 0;
    public static long r = 0;
    public static long s = 0;
    public static long t = 0;
    public static long u = 0;
    public static long v = 0;
    public static long w = 0;
    public static long x = 0;
    public static String y = "0";
    public static String z = "0";
    private long ay = 0;

    public static e a() {
        if (aw == null) {
            synchronized (ColombiaManager.class) {
                if (aw == null) {
                    aw = new e();
                }
            }
        }
        return aw;
    }

    private e() {
    }

    public void a(b bVar) {
        ax = bVar;
    }

    public void a(Builder builder, int i, Context context, int i2, int i3, long j, View view, boolean z, String str, a aVar, String str2) {
        long j2;
        Builder builder2 = builder;
        switch (i2) {
            case 5:
                j2 = g;
                break;
            case 6:
                j2 = h;
                break;
            default:
                switch (i2) {
                    case 22:
                        j2 = s;
                        break;
                    case 23:
                        j2 = r;
                        break;
                    case 24:
                        j2 = u;
                        break;
                    case 25:
                        j2 = t;
                        break;
                    case 26:
                        j2 = v;
                        break;
                    case 27:
                        j2 = w;
                        break;
                    case 28:
                        j2 = x;
                        break;
                    default:
                        j2 = j;
                        break;
                }
        }
        if (j2 >= 1) {
            builder2.enabledGoogleAdFormats(new DFP_ITEM_TYPE[]{DFP_ITEM_TYPE.APP});
            builder2.addCustomAudience("GUL", GaanaApplication.getInstance().getSongLanguagesString());
            builder2.addCustomAudience("SectionName", str2);
            final Context context2 = context;
            final View view2 = view;
            final a aVar2 = aVar;
            final int i4 = i2;
            final int i5 = i3;
            final int i6 = i;
            final boolean z2 = z;
            try {
                Colombia.getNativeAds(builder2.addRequest(Long.valueOf(j2), i, str, new AdListener() {
                    private boolean i = false;
                    private long j = 0;
                    private boolean k = false;

                    public boolean onItemClick(Item item) {
                        return false;
                    }

                    public void onMediaItemDisplayed(Item item) {
                    }

                    public void onMediaItemSkipEnabled(Item item) {
                    }

                    public void onMediaItemClicked(Item item) {
                        if (item != null && item.getItemType() == ITEM_TYPE.VIDEO_INCENTIVE && e.ax != null) {
                            if (GaanaMusicService.t() || f.v().w()) {
                                if (f.v().w()) {
                                    f.v().F();
                                    this.k = true;
                                }
                                this.i = true;
                                e.ax.a();
                            }
                            if (GaanaMusicService.t() || GaanaMusicService.s().l()) {
                                e.ax.d();
                            }
                        }
                    }

                    public void onMediaItemClosed(Item item, USER_ACTION user_action) {
                        if (item != null && item.getItemType() == ITEM_TYPE.VIDEO_INCENTIVE) {
                            if (e.ax != null && GaanaMusicService.s().l()) {
                                e.ax.c();
                                if (this.i) {
                                    this.i = false;
                                    if (this.k) {
                                        e.ax.b();
                                        this.k = false;
                                    } else {
                                        o.c(GaanaApplication.getContext(), PauseReasons.MEDIA_BUTTON_TAP);
                                    }
                                }
                            }
                            if (this.j > 0 && context2 != null) {
                                Intent intent = new Intent(context2, GaanaActivity.class);
                                intent.setFlags(71303168);
                                context2.startActivity(intent);
                            }
                        }
                    }

                    public void onMediaItemCompleted(Item item, int i) {
                        if (item != null && item.getItemType() == ITEM_TYPE.VIDEO_INCENTIVE) {
                            this.j = (long) i;
                            d.a().a(System.currentTimeMillis(), "PREFERENCE_KEY_AD_FREE_SESSION_START_TIME", false);
                            d.a().a(this.j, "PREFERENCE_KEY_AD_FREE_SESSION_DURATION_TIME", false);
                        }
                    }

                    public void onMediaItemError(Item item, Exception exception) {
                        if (item != null && item.getItemType() == ITEM_TYPE.VIDEO_INCENTIVE && e.ax != null && GaanaMusicService.s().l()) {
                            e.ax.c();
                            if (this.i) {
                                this.i = false;
                                if (this.k) {
                                    e.ax.b();
                                    this.k = false;
                                    return;
                                }
                                o.c(GaanaApplication.getContext(), PauseReasons.MEDIA_BUTTON_TAP);
                            }
                        }
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d8  */
                    public void onItemLoaded(com.til.colombia.android.service.ColombiaAdRequest r13, com.til.colombia.android.service.ItemResponse r14) {
                        /*
                        r12 = this;
                        r0 = r14.getPaidItems();
                        r1 = r0.size();
                        r2 = 1;
                        if (r1 <= r2) goto L_0x001b;
                    L_0x000b:
                        r0 = r5;
                        if (r0 != 0) goto L_0x0010;
                    L_0x000f:
                        return;
                    L_0x0010:
                        r0 = com.managers.e.this;
                        r1 = r4;
                        r2 = r5;
                        r0.a(r1, r13, r14, r2);
                        goto L_0x00d3;
                    L_0x001b:
                        r13 = r14.getOrganicItems();
                        r1 = 0;
                        r2 = 0;
                        if (r0 == 0) goto L_0x0031;
                    L_0x0023:
                        r3 = r0.size();
                        if (r3 <= 0) goto L_0x0031;
                    L_0x0029:
                        r13 = r0.get(r2);
                        r13 = (com.til.colombia.android.service.Item) r13;
                    L_0x002f:
                        r8 = r13;
                        goto L_0x0041;
                    L_0x0031:
                        if (r13 == 0) goto L_0x0040;
                    L_0x0033:
                        r0 = r13.size();
                        if (r0 <= 0) goto L_0x0040;
                    L_0x0039:
                        r13 = r13.get(r2);
                        r13 = (com.til.colombia.android.service.Item) r13;
                        goto L_0x002f;
                    L_0x0040:
                        r8 = r1;
                    L_0x0041:
                        r13 = r5;
                        if (r13 != 0) goto L_0x004f;
                    L_0x0045:
                        r13 = r6;
                        if (r13 == 0) goto L_0x004f;
                    L_0x0049:
                        r13 = r6;
                        r13.onItemLoaded(r8);
                        return;
                    L_0x004f:
                        r13 = r5;
                        r0 = 2131297587; // 0x7f090533 float:1.8213123E38 double:1.053000919E-314;
                        r13 = r13.findViewById(r0);
                        r13 = (android.widget.LinearLayout) r13;
                        if (r8 == 0) goto L_0x00d4;
                    L_0x005c:
                        if (r13 != 0) goto L_0x0060;
                    L_0x005e:
                        goto L_0x00d4;
                    L_0x0060:
                        r13.setPadding(r2, r2, r2, r2);
                        r1 = 8;
                        r13.setVisibility(r1);
                        r1 = r8.getAdNetwork();
                        r3 = com.til.colombia.android.service.ColombiaAdManager.AD_NTWK.COLOMBIA;
                        if (r1 != r3) goto L_0x0083;
                    L_0x0070:
                        r3 = com.managers.e.this;
                        r4 = r7;
                        r5 = r8;
                        r6 = r9;
                        r7 = r4;
                        r9 = r10;
                        r11 = r6;
                        r10 = r13;
                        r3.a(r4, r5, r6, r7, r8, r9, r10, r11);
                        goto L_0x00cb;
                    L_0x0083:
                        r1 = r8.getImageUrl();
                        r3 = ".jpg";
                        r1 = r1.contains(r3);
                        if (r1 != 0) goto L_0x00a7;
                    L_0x008f:
                        r1 = r8.getImageUrl();
                        r3 = ".png";
                        r1 = r1.contains(r3);
                        if (r1 != 0) goto L_0x00a7;
                    L_0x009b:
                        r1 = r8.getImageUrl();
                        r3 = "jpeg";
                        r1 = r1.contains(r3);
                        if (r1 == 0) goto L_0x00cb;
                    L_0x00a7:
                        r1 = new com.views.ColombiaMediationAdView;
                        r3 = r4;
                        r1.<init>(r3);
                        r3 = com.views.ColombiaMediationAdView.AdViewType.M_320x250;
                        r1 = r1.a(r8, r3);
                        r0 = r13.findViewById(r0);
                        r0 = (android.widget.LinearLayout) r0;
                        r3 = 16;
                        r13.setPadding(r3, r3, r3, r2);
                        r0.removeAllViews();
                        r0.addView(r1);
                        r0.setVisibility(r2);
                        r1.setVisibility(r2);
                    L_0x00cb:
                        com.til.colombia.android.service.Colombia.recordImpression(r14, r13);	 Catch:{ ColombiaException -> 0x00cf }
                        goto L_0x00d3;
                    L_0x00cf:
                        r13 = move-exception;
                        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r13);
                    L_0x00d3:
                        return;
                    L_0x00d4:
                        r13 = r6;
                        if (r13 == 0) goto L_0x00dd;
                    L_0x00d8:
                        r13 = r6;
                        r13.onItemRequestFailed(r1);
                    L_0x00dd:
                        return;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.managers.e$AnonymousClass1.onItemLoaded(com.til.colombia.android.service.ColombiaAdRequest, com.til.colombia.android.service.ItemResponse):void");
                    }

                    public void onItemRequestFailed(ColombiaAdRequest colombiaAdRequest, Exception exception) {
                        if (aVar2 != null) {
                            aVar2.onItemRequestFailed(exception);
                        }
                        if (view2 != null) {
                            LinearLayout linearLayout = (LinearLayout) view2.findViewById(R.id.llNativeAdSlot);
                            if (linearLayout != null) {
                                linearLayout.removeAllViews();
                                linearLayout.setPadding(0, 0, 0, 0);
                                linearLayout.setVisibility(8);
                            }
                        }
                    }
                }).addReferer("https://api.gaana.com/").addMediaCacheFlags(new MEDIA_CACHE_FLAG[]{MEDIA_CACHE_FLAG.ALL}).enableRecordManualImpression(true).downloadImageBitmap(true).build());
            } catch (ColombiaException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public void a(View view, Context context, com.gaana.models.Item item) {
        String str;
        List entityInfo = item.getEntityInfo();
        CharSequence charSequence = null;
        if (entityInfo != null) {
            int size = entityInfo.size();
            str = null;
            for (int i = 0; i < size; i++) {
                if (((EntityInfo) entityInfo.get(i)).getKey().equals("tracker_adcode_ctn")) {
                    charSequence = (String) ((EntityInfo) entityInfo.get(i)).getValue();
                } else if (((EntityInfo) entityInfo.get(i)).getKey().equals("tracker_adcode_dfp")) {
                    str = (String) ((EntityInfo) entityInfo.get(i)).getValue();
                }
            }
        } else {
            str = null;
        }
        if (!TextUtils.isEmpty(charSequence)) {
            a().a(view, context, Long.parseLong(charSequence));
        } else if (!TextUtils.isEmpty(str)) {
            ColombiaAdViewManager.a().a(view, context, str);
        }
    }

    public void a(final View view, final Context context, long j) {
        ColombiaManager.b().a(context, j, new a() {
            public void onItemRequestFailed(Exception exception) {
            }

            public void onItemLoaded(Item item) {
                AdView adView = new AdView(context);
                ((FrameLayout) view).addView(adView);
                adView.commitItem(item);
            }
        });
    }

    private void a(Context context, ColombiaAdRequest colombiaAdRequest, ItemResponse itemResponse, View view) {
        if (itemResponse != null && itemResponse.getPaidItems().size() != 0) {
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.llNativeAdSlot);
            ColombiaAdScroller colombiaAdScroller = new ColombiaAdScroller(context, "", "");
            linearLayout.removeAllViews();
            linearLayout.addView(colombiaAdScroller.getCarouselView());
            linearLayout.setVisibility(0);
            view.setVisibility(0);
            colombiaAdScroller.setColombiaResponse(itemResponse);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:88:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x021e  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x021e  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0224  */
    public void a(int r19, int r20, int r21, android.content.Context r22, com.til.colombia.android.service.Item r23, boolean r24, android.widget.LinearLayout r25, com.managers.ColombiaManager.a r26) {
        /*
        r18 = this;
        r8 = r18;
        r0 = r19;
        r1 = r20;
        r2 = r23;
        r6 = r25;
        r7 = r26;
        r3 = "layout_inflater";
        r4 = r22;
        r3 = r4.getSystemService(r3);
        r3 = (android.view.LayoutInflater) r3;
        r5 = r23.getItemType();
        r10 = 0;
        if (r0 != 0) goto L_0x014e;
    L_0x001d:
        r12 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.CONTENT;
        if (r5 != r12) goto L_0x014a;
    L_0x0021:
        r12 = 2131493002; // 0x7f0c008a float:1.8609472E38 double:1.0530974666E-314;
        r3 = r3.inflate(r12, r10);
        r3 = (com.til.colombia.android.service.AdView) r3;
        r12 = 2131296713; // 0x7f0901c9 float:1.821135E38 double:1.053000487E-314;
        r12 = r3.findViewById(r12);
        r12 = (com.library.controls.CrossFadeImageView) r12;
        r13 = 2131297411; // 0x7f090483 float:1.8212766E38 double:1.053000832E-314;
        r13 = r3.findViewById(r13);
        r13 = (android.widget.ImageView) r13;
        r14 = 2131296712; // 0x7f0901c8 float:1.8211348E38 double:1.0530004865E-314;
        r14 = r3.findViewById(r14);
        r14 = (android.widget.TextView) r14;
        r15 = 2131296711; // 0x7f0901c7 float:1.8211346E38 double:1.053000486E-314;
        r15 = r3.findViewById(r15);
        r15 = (android.widget.LinearLayout) r15;
        r9 = r22.getResources();
        r10 = 2131165547; // 0x7f07016b float:1.7945314E38 double:1.0529356824E-314;
        r9 = r9.getDimension(r10);
        r11 = r22.getResources();
        r10 = r11.getDimension(r10);
        if (r24 == 0) goto L_0x0096;
    L_0x0063:
        if (r13 == 0) goto L_0x0096;
    L_0x0065:
        r11 = com.managers.s.a();
        r11 = r11.b();
        if (r11 == 0) goto L_0x008c;
    L_0x006f:
        r11 = com.constants.Constants.dt;
        if (r11 == 0) goto L_0x008c;
    L_0x0073:
        r11 = com.constants.Constants.dt;
        r16 = r3;
        r3 = com.i.i.a();
        r11 = r11.getOverlaySquareArtwork();
        r17 = r9;
        r9 = new com.managers.e$3;
        r9.<init>(r13);
        r3.a(r11, r9);
        r3 = 8;
        goto L_0x00a1;
    L_0x008c:
        r16 = r3;
        r17 = r9;
        r3 = 8;
        r13.setVisibility(r3);
        goto L_0x00a1;
    L_0x0096:
        r16 = r3;
        r17 = r9;
        r3 = 8;
        if (r13 == 0) goto L_0x00a1;
    L_0x009e:
        r13.setVisibility(r3);
    L_0x00a1:
        r3 = com.constants.Constants.VIEW_SIZE.SCROLL_RECTANGLE;
        r3 = r3.getNumVal();
        if (r1 == r3) goto L_0x0115;
    L_0x00a9:
        r3 = com.constants.Constants.VIEW_SIZE.SCROLL_RECTANGLE_WITHOUT_TXT;
        r3 = r3.getNumVal();
        if (r1 != r3) goto L_0x00b2;
    L_0x00b1:
        goto L_0x0115;
    L_0x00b2:
        r3 = com.constants.Constants.VIEW_SIZE.SCROLL_BIG_SQAUE;
        r3 = r3.getNumVal();
        if (r1 == r3) goto L_0x0101;
    L_0x00ba:
        r3 = com.constants.Constants.VIEW_SIZE.SCROLL_BIG_SQAUE_WITHOUT_TXT;
        r3 = r3.getNumVal();
        if (r1 != r3) goto L_0x00c3;
    L_0x00c2:
        goto L_0x0101;
    L_0x00c3:
        r3 = com.constants.Constants.VIEW_SIZE.SCROLL_MEDIUM_SQAUE;
        r3 = r3.getNumVal();
        if (r1 != r3) goto L_0x00df;
    L_0x00cb:
        r1 = r22.getResources();
        r3 = 2131165646; // 0x7f0701ce float:1.7945515E38 double:1.0529357313E-314;
        r9 = r1.getDimension(r3);
        r1 = r22.getResources();
        r10 = r1.getDimension(r3);
        goto L_0x012b;
    L_0x00df:
        r3 = com.constants.Constants.VIEW_SIZE.SCROLL_RECTANGLE_DISCOVER;
        r3 = r3.getNumVal();
        if (r1 != r3) goto L_0x00fe;
    L_0x00e7:
        r1 = r22.getResources();
        r3 = 2131165677; // 0x7f0701ed float:1.7945578E38 double:1.0529357466E-314;
        r9 = r1.getDimension(r3);
        r1 = r22.getResources();
        r3 = 2131165678; // 0x7f0701ee float:1.794558E38 double:1.052935747E-314;
        r10 = r1.getDimension(r3);
        goto L_0x012b;
    L_0x00fe:
        r9 = r17;
        goto L_0x012b;
    L_0x0101:
        r1 = r22.getResources();
        r3 = 2131165687; // 0x7f0701f7 float:1.7945598E38 double:1.0529357515E-314;
        r9 = r1.getDimension(r3);
        r1 = r22.getResources();
        r10 = r1.getDimension(r3);
        goto L_0x012b;
    L_0x0115:
        r1 = r22.getResources();
        r3 = 2131165689; // 0x7f0701f9 float:1.7945602E38 double:1.0529357525E-314;
        r9 = r1.getDimension(r3);
        r1 = r22.getResources();
        r3 = 2131165690; // 0x7f0701fa float:1.7945604E38 double:1.052935753E-314;
        r10 = r1.getDimension(r3);
    L_0x012b:
        r1 = r12.getLayoutParams();
        r1 = (android.widget.RelativeLayout.LayoutParams) r1;
        r3 = (int) r10;
        r1.width = r3;
        r4 = (int) r9;
        r1.height = r4;
        r1 = r14.getLayoutParams();
        r1 = (android.widget.RelativeLayout.LayoutParams) r1;
        r1.width = r3;
        r1 = r15.getLayoutParams();
        r1 = (android.widget.RelativeLayout.LayoutParams) r1;
        r1.width = r3;
        r10 = r16;
        goto L_0x0182;
    L_0x014a:
        r4 = 0;
        r10 = 0;
        goto L_0x021c;
    L_0x014e:
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.APP;
        if (r5 != r1) goto L_0x015f;
    L_0x0152:
        r1 = 2131492989; // 0x7f0c007d float:1.8609445E38 double:1.05309746E-314;
        r4 = 0;
        r1 = r3.inflate(r1, r4);
        r10 = r1;
        r10 = (com.til.colombia.android.service.AdView) r10;
        goto L_0x021c;
    L_0x015f:
        r4 = 0;
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.VIDEO_INCENTIVE;
        if (r5 != r1) goto L_0x0170;
    L_0x0164:
        r1 = 2131492993; // 0x7f0c0081 float:1.8609454E38 double:1.053097462E-314;
        r1 = r3.inflate(r1, r4);
        r10 = r1;
        r10 = (com.til.colombia.android.service.ColombiaNativeSponsoredAdView) r10;
        goto L_0x021c;
    L_0x0170:
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.GENERAL;
        if (r5 != r1) goto L_0x01d7;
    L_0x0174:
        r1 = 25;
        if (r0 != r1) goto L_0x0185;
    L_0x0178:
        r1 = 2131493000; // 0x7f0c0088 float:1.8609468E38 double:1.0530974657E-314;
        r1 = r3.inflate(r1, r4);
        r10 = r1;
        r10 = (com.til.colombia.android.service.AdView) r10;
    L_0x0182:
        r4 = 0;
        goto L_0x021c;
    L_0x0185:
        r1 = 27;
        if (r0 == r1) goto L_0x01cb;
    L_0x0189:
        r1 = 28;
        if (r0 == r1) goto L_0x01cb;
    L_0x018d:
        r1 = 30;
        if (r0 != r1) goto L_0x0192;
    L_0x0191:
        goto L_0x01cb;
    L_0x0192:
        r1 = 24;
        if (r0 == r1) goto L_0x01bf;
    L_0x0196:
        r1 = 22;
        if (r0 == r1) goto L_0x01bf;
    L_0x019a:
        r1 = 23;
        if (r0 == r1) goto L_0x01bf;
    L_0x019e:
        r1 = 26;
        if (r0 != r1) goto L_0x01a3;
    L_0x01a2:
        goto L_0x01bf;
    L_0x01a3:
        r1 = 4;
        if (r0 != r1) goto L_0x01b3;
    L_0x01a6:
        r1 = 2131492995; // 0x7f0c0083 float:1.8609458E38 double:1.053097463E-314;
        r4 = 0;
        r1 = r3.inflate(r1, r4);
        r10 = r1;
        r10 = (com.til.colombia.android.service.AdView) r10;
        goto L_0x021c;
    L_0x01b3:
        r4 = 0;
        r1 = 2131492991; // 0x7f0c007f float:1.860945E38 double:1.053097461E-314;
        r1 = r3.inflate(r1, r4);
        r10 = r1;
        r10 = (com.til.colombia.android.service.AdView) r10;
        goto L_0x021c;
    L_0x01bf:
        r4 = 0;
        r1 = 2131492992; // 0x7f0c0080 float:1.8609452E38 double:1.0530974617E-314;
        r1 = r3.inflate(r1, r4);
        r10 = r1;
        r10 = (com.til.colombia.android.service.AdView) r10;
        goto L_0x021c;
    L_0x01cb:
        r4 = 0;
        r1 = 2131493001; // 0x7f0c0089 float:1.860947E38 double:1.053097466E-314;
        r1 = r3.inflate(r1, r4);
        r10 = r1;
        r10 = (com.til.colombia.android.service.AdView) r10;
        goto L_0x021c;
    L_0x01d7:
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.CONTENT;
        if (r5 != r1) goto L_0x021b;
    L_0x01db:
        r1 = 23;
        if (r0 != r1) goto L_0x01ea;
    L_0x01df:
        r1 = 2131492999; // 0x7f0c0087 float:1.8609466E38 double:1.053097465E-314;
        r1 = r3.inflate(r1, r4);
        r10 = r1;
        r10 = (com.til.colombia.android.service.AdView) r10;
        goto L_0x021c;
    L_0x01ea:
        r1 = 24;
        if (r0 == r1) goto L_0x0210;
    L_0x01ee:
        r1 = 22;
        if (r0 == r1) goto L_0x0210;
    L_0x01f2:
        r1 = 26;
        if (r0 != r1) goto L_0x01f7;
    L_0x01f6:
        goto L_0x0210;
    L_0x01f7:
        r1 = 4;
        if (r0 != r1) goto L_0x0205;
    L_0x01fa:
        r1 = 2131492994; // 0x7f0c0082 float:1.8609456E38 double:1.0530974627E-314;
        r1 = r3.inflate(r1, r4);
        r10 = r1;
        r10 = (com.til.colombia.android.service.AdView) r10;
        goto L_0x021c;
    L_0x0205:
        r1 = 2131492990; // 0x7f0c007e float:1.8609447E38 double:1.0530974607E-314;
        r1 = r3.inflate(r1, r4);
        r10 = r1;
        r10 = (com.til.colombia.android.service.AdView) r10;
        goto L_0x021c;
    L_0x0210:
        r1 = 2131492998; // 0x7f0c0086 float:1.8609464E38 double:1.0530974647E-314;
        r1 = r3.inflate(r1, r4);
        r10 = r1;
        r10 = (com.til.colombia.android.service.AdView) r10;
        goto L_0x021c;
    L_0x021b:
        r10 = r4;
    L_0x021c:
        if (r10 != 0) goto L_0x0224;
    L_0x021e:
        if (r7 == 0) goto L_0x0223;
    L_0x0220:
        r7.onItemRequestFailed(r4);
    L_0x0223:
        return;
    L_0x0224:
        r1 = 8;
        r10.setVisibility(r1);
        r1 = r23.getItemType();
        r3 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.APP;
        if (r1 != r3) goto L_0x0238;
    L_0x0231:
        r1 = r10;
        r1 = (com.til.colombia.android.service.AdView) r1;
        r8.g(r2, r1);
        goto L_0x0298;
    L_0x0238:
        r1 = r23.getItemType();
        r3 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.VIDEO_INCENTIVE;
        if (r1 != r3) goto L_0x0247;
    L_0x0240:
        r1 = r10;
        r1 = (com.til.colombia.android.service.ColombiaNativeSponsoredAdView) r1;
        r8.a(r2, r1);
        goto L_0x0298;
    L_0x0247:
        r1 = r23.getItemType();
        r3 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.GENERAL;
        if (r1 != r3) goto L_0x0261;
    L_0x024f:
        r1 = 25;
        if (r0 != r1) goto L_0x025a;
    L_0x0253:
        r1 = r10;
        r1 = (com.til.colombia.android.service.AdView) r1;
        r8.a(r2, r1);
        goto L_0x0298;
    L_0x025a:
        r1 = r10;
        r1 = (com.til.colombia.android.service.AdView) r1;
        r8.f(r2, r1);
        goto L_0x0298;
    L_0x0261:
        r1 = r23.getItemType();
        r3 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.CONTENT;
        if (r1 != r3) goto L_0x0298;
    L_0x0269:
        r1 = 23;
        if (r0 != r1) goto L_0x0274;
    L_0x026d:
        r1 = r10;
        r1 = (com.til.colombia.android.service.AdView) r1;
        r8.c(r2, r1);
        goto L_0x0298;
    L_0x0274:
        r1 = 24;
        if (r0 == r1) goto L_0x0292;
    L_0x0278:
        r1 = 22;
        if (r0 == r1) goto L_0x0292;
    L_0x027c:
        r1 = 26;
        if (r0 != r1) goto L_0x0281;
    L_0x0280:
        goto L_0x0292;
    L_0x0281:
        r1 = 4;
        if (r0 != r1) goto L_0x028b;
    L_0x0284:
        r1 = r10;
        r1 = (com.til.colombia.android.service.AdView) r1;
        r8.e(r2, r1);
        goto L_0x0298;
    L_0x028b:
        r1 = r10;
        r1 = (com.til.colombia.android.service.AdView) r1;
        r8.d(r2, r1);
        goto L_0x0298;
    L_0x0292:
        r1 = r10;
        r1 = (com.til.colombia.android.service.AdView) r1;
        r8.b(r2, r1);
    L_0x0298:
        r25.removeAllViews();
        r6.addView(r10);
        r1 = 24;
        if (r0 == r1) goto L_0x02ae;
    L_0x02a2:
        r1 = 23;
        if (r0 == r1) goto L_0x02ae;
    L_0x02a6:
        r1 = 22;
        if (r0 == r1) goto L_0x02ae;
    L_0x02aa:
        r1 = 26;
        if (r0 != r1) goto L_0x02bf;
    L_0x02ae:
        r1 = com.til.colombia.android.service.ColombiaAdManager.ITEM_TYPE.CONTENT;
        if (r5 != r1) goto L_0x02b9;
    L_0x02b2:
        r1 = 2131231957; // 0x7f0804d5 float:1.808001E38 double:1.0529684933E-314;
        r6.setBackgroundResource(r1);
        goto L_0x02bf;
    L_0x02b9:
        r1 = 17170445; // 0x106000d float:2.461195E-38 double:8.483327E-317;
        r6.setBackgroundResource(r1);
    L_0x02bf:
        r1 = 0;
        r10.setVisibility(r1);
        if (r7 == 0) goto L_0x02cb;
    L_0x02c5:
        r7.onItemLoaded(r2);
    L_0x02c8:
        r2 = 24;
        goto L_0x02cf;
    L_0x02cb:
        r6.setVisibility(r1);
        goto L_0x02c8;
    L_0x02cf:
        if (r0 != r2) goto L_0x0300;
    L_0x02d1:
        r0 = com.services.d.a();
        r2 = "PREFERENCE_KEY_HOME_SPONSOR_AD";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "";
        r3.append(r4);
        r4 = java.lang.System.currentTimeMillis();
        r3.append(r4);
        r3 = r3.toString();
        r0.a(r2, r3, r1);
        r9 = new com.managers.e$4;
        r0 = com.constants.Constants.dA;
        r0 = r0 * 1000;
        r2 = (long) r0;
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0 = r9;
        r1 = r8;
        r0.<init>(r2, r4, r6, r7);
        r9.start();
    L_0x0300:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.managers.e.a(int, int, int, android.content.Context, com.til.colombia.android.service.Item, boolean, android.widget.LinearLayout, com.managers.ColombiaManager$a):void");
    }

    private void a(Item item, AdView adView) {
        adView.setHeadlineView(adView.findViewById(R.id.leftdrawer_sponsor_small_braught_text));
        adView.setAdvertiserView(adView.findViewById(R.id.leftdrawer_sponsor_company_text));
        adView.setImageView(adView.findViewById(R.id.sponsor_tryal_image_ad));
        adView.setBannerView((ColombiaBannerView) adView.findViewById(R.id.banner));
        ImageView imageView = (ImageView) adView.getImageView();
        if (imageView != null) {
            imageView.setImageBitmap(item.getImage());
        }
        TextView textView = (TextView) adView.getHeadlineView();
        String title = item.getTitle();
        if (!(title == null || title.isEmpty())) {
            textView.setText(item.getTitle());
        }
        textView = (TextView) adView.getAdvertiserView();
        title = item.getBrandText();
        if (!(title == null || title.isEmpty())) {
            textView.setText(title);
        }
        adView.commitItem(item);
    }

    private void b(Item item, AdView adView) {
        adView.setHeadlineView(adView.findViewById(R.id.leftdrawer_sponsor_small_braught_text));
        adView.setAdvertiserView(adView.findViewById(R.id.leftdrawer_sponsor_company_text));
        adView.setCallToActionView(adView.findViewById(R.id.leftdrawer_sponsor_button));
        TextView textView = (TextView) adView.getHeadlineView();
        String title = item.getTitle();
        if (!(title == null || title.isEmpty())) {
            textView.setText(item.getTitle());
        }
        textView = (TextView) adView.getAdvertiserView();
        title = item.getBrandText();
        if (!(title == null || title.isEmpty())) {
            textView.setText(title);
        }
        Button button = (Button) adView.getCallToActionView();
        title = item.getCtaText();
        if (!(title == null || title.isEmpty())) {
            button.setText(title);
        }
        adView.commitItem(item);
    }

    private void c(Item item, AdView adView) {
        adView.setHeadlineView(adView.findViewById(R.id.leftdrawer_sponsor_small_braught_text));
        adView.setAdvertiserView(adView.findViewById(R.id.leftdrawer_sponsor_company_text));
        adView.setCallToActionView(adView.findViewById(R.id.leftdrawer_sponsor_button));
        TextView textView = (TextView) adView.getHeadlineView();
        String title = item.getTitle();
        if (!(title == null || title.isEmpty())) {
            textView.setText(item.getTitle());
        }
        textView = (TextView) adView.getAdvertiserView();
        title = item.getBrandText();
        if (!(title == null || title.isEmpty())) {
            textView.setText(title);
        }
        adView.commitItem(item);
    }

    private void d(Item item, AdView adView) {
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
        if (item.getImage() != null) {
            crossFadeImageView.setImageBitmap(item.getImage());
            crossFadeImageView.setScaleType(ScaleType.FIT_XY);
        }
        textView = (TextView) adView.getAttributionTextView();
        title = item.getAdAttributionText();
        if (!(title == null || title.isEmpty())) {
            textView.setText(title);
        }
        if (item.thirdPartyAd() != null) {
            textView.setTextColor(Color.parseColor("#FEFEFE"));
        }
        textView = (TextView) adView.getAdvertiserView();
        title = item.getBrandText();
        if (!(title == null || title.isEmpty())) {
            textView.setText(title);
        }
        adView.commitItem(item);
        if (item.thirdPartyAd() == null) {
            adView.getColombiaView().setBackgroundResource(R.drawable.colombia_icon);
        }
    }

    private void e(Item item, AdView adView) {
        adView.setHeadlineView(adView.findViewById(R.id.colom_ad_headLine));
        adView.setImageView(adView.findViewById(R.id.colom_ad_image));
        adView.setAttributionTextView(adView.findViewById(R.id.colom_ad_text));
        Button button = (Button) adView.findViewById(R.id.ctaText);
        if (TextUtils.isEmpty(item.getCtaText())) {
            button.setVisibility(8);
        } else {
            button.setVisibility(0);
            button.setText(item.getCtaText());
            adView.setCallToActionView(button);
        }
        TextView textView = (TextView) adView.getHeadlineView();
        String title = item.getTitle();
        if (!(title == null || title.isEmpty())) {
            textView.setText(item.getTitle());
        }
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) adView.getImageView();
        if (item.getImage() != null) {
            crossFadeImageView.setImageBitmap(item.getImage());
            crossFadeImageView.setScaleType(ScaleType.FIT_XY);
        }
        textView = (TextView) adView.getAttributionTextView();
        title = item.getAdAttributionText();
        if (!(title == null || title.isEmpty())) {
            textView.setText(title);
        }
        if (item.thirdPartyAd() != null) {
            textView.setTextColor(Color.parseColor("#FEFEFE"));
        }
        adView.commitItem(item);
    }

    private void f(Item item, AdView adView) {
        adView.setBannerView((ColombiaBannerView) adView.findViewById(R.id.native_banner_view));
        adView.commitItem(item);
    }

    private void g(Item item, AdView adView) {
        adView.setHeadlineView(adView.findViewById(R.id.colom_ad_headLine));
        adView.setIconView(adView.findViewById(R.id.colom_ad_image));
        adView.setCallToActionView(adView.findViewById(R.id.colom_install_app_button));
        adView.setAttributionTextView(adView.findViewById(R.id.colom_ad_text));
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) adView.getIconView();
        if (item.getImage() != null) {
            crossFadeImageView.setImageBitmap(item.getImage());
            crossFadeImageView.setScaleType(ScaleType.FIT_XY);
        }
        Button button = (Button) adView.getCallToActionView();
        String ctaText = item.getCtaText();
        if (!(ctaText == null || ctaText.isEmpty())) {
            button.setText(ctaText);
        }
        TextView textView = (TextView) adView.getHeadlineView();
        ctaText = item.getTitle();
        if (!(ctaText == null || ctaText.isEmpty())) {
            textView.setText(item.getTitle());
        }
        if (!TextUtils.isEmpty(item.getBrand())) {
            ((TextView) adView.findViewById(R.id.colom_ad_brand_text_title)).setText(item.getBrand());
        }
        textView = (TextView) adView.getAttributionTextView();
        ctaText = item.getAdAttributionText();
        if (!(ctaText == null || ctaText.isEmpty())) {
            textView.setText(ctaText);
        }
        if (item.thirdPartyAd() != null) {
            textView.setTextColor(Color.parseColor("#FEFEFE"));
        }
        adView.commitItem(item);
    }

    private void a(Item item, ColombiaNativeSponsoredAdView colombiaNativeSponsoredAdView) {
        colombiaNativeSponsoredAdView.setHeadlineView(colombiaNativeSponsoredAdView.findViewById(R.id.colom_ad_headLine));
        colombiaNativeSponsoredAdView.setIconView(colombiaNativeSponsoredAdView.findViewById(R.id.colom_ad_image));
        colombiaNativeSponsoredAdView.setCallToActionView(colombiaNativeSponsoredAdView.findViewById(R.id.colom_install_app_button));
        colombiaNativeSponsoredAdView.setAttributionTextView(colombiaNativeSponsoredAdView.findViewById(R.id.colom_ad_text));
        CrossFadeImageView crossFadeImageView = (CrossFadeImageView) colombiaNativeSponsoredAdView.getIconView();
        if (item.getImage() != null) {
            crossFadeImageView.setImageBitmap(item.getImage());
            crossFadeImageView.setScaleType(ScaleType.FIT_XY);
        }
        Button button = (Button) colombiaNativeSponsoredAdView.getCallToActionView();
        String ctaText = item.getCtaText();
        if (!(ctaText == null || ctaText.isEmpty())) {
            button.setText(ctaText);
        }
        TextView textView = (TextView) colombiaNativeSponsoredAdView.getHeadlineView();
        ctaText = item.getTitle();
        if (!(ctaText == null || ctaText.isEmpty())) {
            textView.setText(item.getTitle());
        }
        textView = (TextView) colombiaNativeSponsoredAdView.getAttributionTextView();
        ctaText = item.getAdAttributionText();
        if (!(ctaText == null || ctaText.isEmpty())) {
            textView.setText(ctaText);
        }
        if (item.thirdPartyAd() != null) {
            textView.setTextColor(Color.parseColor("#FEFEFE"));
        }
        colombiaNativeSponsoredAdView.setItem(item);
        colombiaNativeSponsoredAdView.commit();
    }
}
