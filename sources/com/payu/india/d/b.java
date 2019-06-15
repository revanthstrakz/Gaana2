package com.payu.india.d;

import android.content.Context;
import com.payu.india.Model.PaymentParams;
import com.payu.india.c.a;
import com.payu.india.c.c;
import java.util.HashMap;

public class b extends c {
    private PaymentParams c;
    private String d;
    private Context e;

    private b() {
    }

    public b(PaymentParams paymentParams, String str) throws Exception {
        this.c = paymentParams;
        this.d = str;
        if (a.a() == null) {
            throw new Exception("Application context not found please set your application context by adding Payu.setInstance(this) from your activity ");
        }
        this.e = a.a().b();
    }

    /* JADX WARNING: Missing block: B:87:0x01d6, code skipped:
            return d("Mandatory param hash is missing");
     */
    /* JADX WARNING: Missing block: B:98:0x0214, code skipped:
            return d("Mandatory param furl is missing");
     */
    /* JADX WARNING: Missing block: B:109:0x0252, code skipped:
            return d("Mandatory param surl is missing");
     */
    /* JADX WARNING: Missing block: B:126:0x02bf, code skipped:
            return d("Mandatory param product info is missing");
     */
    /* JADX WARNING: Missing block: B:153:0x034b, code skipped:
            return d("Mandatory param key is missing");
     */
    public com.payu.india.Model.PostData c() {
        /*
        r18 = this;
        r1 = r18;
        r2 = new com.payu.india.Model.PostData;
        r2.<init>();
        r2 = new java.lang.StringBuffer;
        r2.<init>();
        r3 = com.payu.india.c.b.a;
        r4 = r1.d;
        r3 = r3.contains(r4);
        if (r3 != 0) goto L_0x0027;
    L_0x0016:
        r3 = r1.d;
        r4 = "lazypay";
        r3 = r3.equalsIgnoreCase(r4);
        if (r3 != 0) goto L_0x0027;
    L_0x0020:
        r2 = "Invalid pg!, pg should be any one of CC, EMI, CASH, NB, PAYU_MONEY";
        r2 = r1.d(r2);
        return r2;
    L_0x0027:
        r3 = "device_type";
        r4 = "1";
        r3 = r1.b(r3, r4);
        r2.append(r3);
        r3 = "udid";
        r4 = r18.b();
        r3 = r1.b(r3, r4);
        r2.append(r3);
        r3 = "imei";
        r4 = r18.a();
        r3 = r1.b(r3, r4);
        r2.append(r3);
        r3 = 0;
        r4 = r3;
    L_0x004e:
        r5 = com.payu.india.c.b.d;
        r7 = 2;
        r8 = 7;
        r9 = 6;
        r10 = 5;
        r11 = 4;
        r12 = -1;
        r13 = 5002; // 0x138a float:7.009E-42 double:2.4713E-320;
        r14 = 0;
        r6 = 1;
        r5 = r5.length;
        if (r4 >= r5) goto L_0x0350;
    L_0x005e:
        r5 = com.payu.india.c.b.d;
        r5 = r5[r4];
        r17 = r5.hashCode();
        switch(r17) {
            case -1491000803: goto L_0x00f8;
            case -1413853096: goto L_0x00ee;
            case 106079: goto L_0x00e4;
            case 3154761: goto L_0x00da;
            case 3195150: goto L_0x00cf;
            case 3542044: goto L_0x00c5;
            case 3584858: goto L_0x00ba;
            case 3584859: goto L_0x00af;
            case 3584860: goto L_0x00a4;
            case 3584861: goto L_0x0098;
            case 3584862: goto L_0x008c;
            case 96619420: goto L_0x0081;
            case 110812421: goto L_0x0076;
            case 133788987: goto L_0x006b;
            default: goto L_0x0069;
        };
    L_0x0069:
        goto L_0x0102;
    L_0x006b:
        r7 = "firstname";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0102;
    L_0x0073:
        r5 = r11;
        goto L_0x0103;
    L_0x0076:
        r7 = "txnid";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0102;
    L_0x007e:
        r5 = r6;
        goto L_0x0103;
    L_0x0081:
        r7 = "email";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0102;
    L_0x0089:
        r5 = r10;
        goto L_0x0103;
    L_0x008c:
        r7 = "udf5";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0102;
    L_0x0094:
        r5 = 13;
        goto L_0x0103;
    L_0x0098:
        r7 = "udf4";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0102;
    L_0x00a0:
        r5 = 12;
        goto L_0x0103;
    L_0x00a4:
        r7 = "udf3";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0102;
    L_0x00ac:
        r5 = 11;
        goto L_0x0103;
    L_0x00af:
        r7 = "udf2";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0102;
    L_0x00b7:
        r5 = 10;
        goto L_0x0103;
    L_0x00ba:
        r7 = "udf1";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0102;
    L_0x00c2:
        r5 = 9;
        goto L_0x0103;
    L_0x00c5:
        r7 = "surl";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0102;
    L_0x00cd:
        r5 = r9;
        goto L_0x0103;
    L_0x00cf:
        r7 = "hash";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0102;
    L_0x00d7:
        r5 = 8;
        goto L_0x0103;
    L_0x00da:
        r7 = "furl";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0102;
    L_0x00e2:
        r5 = r8;
        goto L_0x0103;
    L_0x00e4:
        r7 = "key";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0102;
    L_0x00ec:
        r5 = r3;
        goto L_0x0103;
    L_0x00ee:
        r8 = "amount";
        r5 = r5.equals(r8);
        if (r5 == 0) goto L_0x0102;
    L_0x00f6:
        r5 = r7;
        goto L_0x0103;
    L_0x00f8:
        r7 = "productinfo";
        r5 = r5.equals(r7);
        if (r5 == 0) goto L_0x0102;
    L_0x0100:
        r5 = 3;
        goto L_0x0103;
    L_0x0102:
        r5 = r12;
    L_0x0103:
        r7 = 5004; // 0x138c float:7.012E-42 double:2.4723E-320;
        switch(r5) {
            case 0: goto L_0x0320;
            case 1: goto L_0x02f4;
            case 2: goto L_0x02c0;
            case 3: goto L_0x0293;
            case 4: goto L_0x0273;
            case 5: goto L_0x0253;
            case 6: goto L_0x0215;
            case 7: goto L_0x01d7;
            case 8: goto L_0x01aa;
            case 9: goto L_0x018a;
            case 10: goto L_0x016a;
            case 11: goto L_0x014a;
            case 12: goto L_0x012a;
            case 13: goto L_0x010a;
            default: goto L_0x0108;
        };
    L_0x0108:
        goto L_0x034c;
    L_0x010a:
        r5 = r1.c;
        r5 = r5.y();
        if (r5 != 0) goto L_0x0119;
    L_0x0112:
        r2 = "UDF5 should not be null, it can be empty or string";
        r2 = r1.d(r2);
        return r2;
    L_0x0119:
        r5 = "udf5";
        r6 = r1.c;
        r6 = r6.y();
        r5 = r1.b(r5, r6);
        r2.append(r5);
        goto L_0x034c;
    L_0x012a:
        r5 = r1.c;
        r5 = r5.x();
        if (r5 != 0) goto L_0x0139;
    L_0x0132:
        r2 = "UDF4 should not be null, it can be empty or string";
        r2 = r1.d(r2);
        return r2;
    L_0x0139:
        r5 = "udf4";
        r6 = r1.c;
        r6 = r6.x();
        r5 = r1.b(r5, r6);
        r2.append(r5);
        goto L_0x034c;
    L_0x014a:
        r5 = r1.c;
        r5 = r5.w();
        if (r5 != 0) goto L_0x0159;
    L_0x0152:
        r2 = "UDF3 should not be null, it can be empty or string";
        r2 = r1.d(r2);
        return r2;
    L_0x0159:
        r5 = "udf3";
        r6 = r1.c;
        r6 = r6.w();
        r5 = r1.b(r5, r6);
        r2.append(r5);
        goto L_0x034c;
    L_0x016a:
        r5 = r1.c;
        r5 = r5.v();
        if (r5 != 0) goto L_0x0179;
    L_0x0172:
        r2 = "UDF2 should not be null, it can be empty or string";
        r2 = r1.d(r2);
        return r2;
    L_0x0179:
        r5 = "udf2";
        r6 = r1.c;
        r6 = r6.v();
        r5 = r1.b(r5, r6);
        r2.append(r5);
        goto L_0x034c;
    L_0x018a:
        r5 = r1.c;
        r5 = r5.u();
        if (r5 != 0) goto L_0x0199;
    L_0x0192:
        r2 = "UDF1 should not be null, it can be empty or string";
        r2 = r1.d(r2);
        return r2;
    L_0x0199:
        r5 = "udf1";
        r6 = r1.c;
        r6 = r6.u();
        r5 = r1.b(r5, r6);
        r2.append(r5);
        goto L_0x034c;
    L_0x01aa:
        r5 = r1.c;
        r5 = r5.k();
        if (r5 == 0) goto L_0x01d0;
    L_0x01b2:
        r5 = r1.c;
        r5 = r5.k();
        r5 = r5.length();
        if (r5 >= r6) goto L_0x01bf;
    L_0x01be:
        goto L_0x01d0;
    L_0x01bf:
        r5 = "hash";
        r6 = r1.c;
        r6 = r6.k();
        r5 = r1.b(r5, r6);
        r2.append(r5);
        goto L_0x034c;
    L_0x01d0:
        r2 = "Mandatory param hash is missing";
        r2 = r1.d(r2);
        return r2;
    L_0x01d7:
        r5 = r1.c;
        r5 = r5.j();
        if (r5 == 0) goto L_0x020e;
    L_0x01df:
        r5 = r1.c;
        r5 = r5.j();
        r5 = r5.length();
        if (r5 >= r6) goto L_0x01ec;
    L_0x01eb:
        goto L_0x020e;
    L_0x01ec:
        r5 = "furl=";
        r2.append(r5);	 Catch:{ UnsupportedEncodingException -> 0x0207 }
        r5 = r1.c;	 Catch:{ UnsupportedEncodingException -> 0x0207 }
        r5 = r5.j();	 Catch:{ UnsupportedEncodingException -> 0x0207 }
        r6 = "UTF-8";
        r5 = java.net.URLEncoder.encode(r5, r6);	 Catch:{ UnsupportedEncodingException -> 0x0207 }
        r2.append(r5);	 Catch:{ UnsupportedEncodingException -> 0x0207 }
        r5 = "&";
        r2.append(r5);	 Catch:{ UnsupportedEncodingException -> 0x0207 }
        goto L_0x034c;
    L_0x0207:
        r2 = "furl should be something like https://www.payu.in/txnstatus";
        r2 = r1.a(r7, r2);
        return r2;
    L_0x020e:
        r2 = "Mandatory param furl is missing";
        r2 = r1.d(r2);
        return r2;
    L_0x0215:
        r5 = r1.c;
        r5 = r5.i();
        if (r5 == 0) goto L_0x024c;
    L_0x021d:
        r5 = r1.c;
        r5 = r5.i();
        r5 = r5.length();
        if (r5 >= r6) goto L_0x022a;
    L_0x0229:
        goto L_0x024c;
    L_0x022a:
        r5 = "surl=";
        r2.append(r5);	 Catch:{ UnsupportedEncodingException -> 0x0245 }
        r5 = r1.c;	 Catch:{ UnsupportedEncodingException -> 0x0245 }
        r5 = r5.i();	 Catch:{ UnsupportedEncodingException -> 0x0245 }
        r6 = "UTF-8";
        r5 = java.net.URLEncoder.encode(r5, r6);	 Catch:{ UnsupportedEncodingException -> 0x0245 }
        r2.append(r5);	 Catch:{ UnsupportedEncodingException -> 0x0245 }
        r5 = "&";
        r2.append(r5);	 Catch:{ UnsupportedEncodingException -> 0x0245 }
        goto L_0x034c;
    L_0x0245:
        r2 = "surl should be something like https://www.payu.in/txnstatus";
        r2 = r1.a(r7, r2);
        return r2;
    L_0x024c:
        r2 = "Mandatory param surl is missing";
        r2 = r1.d(r2);
        return r2;
    L_0x0253:
        r5 = r1.c;
        r5 = r5.h();
        if (r5 != 0) goto L_0x0262;
    L_0x025b:
        r2 = "Mandatory param email is missing";
        r2 = r1.d(r2);
        return r2;
    L_0x0262:
        r5 = "email";
        r6 = r1.c;
        r6 = r6.h();
        r5 = r1.b(r5, r6);
        r2.append(r5);
        goto L_0x034c;
    L_0x0273:
        r5 = r1.c;
        r5 = r5.g();
        if (r5 != 0) goto L_0x0282;
    L_0x027b:
        r2 = "Mandatory param firstname is missing";
        r2 = r1.d(r2);
        return r2;
    L_0x0282:
        r5 = "firstname";
        r6 = r1.c;
        r6 = r6.g();
        r5 = r1.b(r5, r6);
        r2.append(r5);
        goto L_0x034c;
    L_0x0293:
        r5 = r1.c;
        r5 = r5.f();
        if (r5 == 0) goto L_0x02b9;
    L_0x029b:
        r5 = r1.c;
        r5 = r5.f();
        r5 = r5.length();
        if (r5 >= r6) goto L_0x02a8;
    L_0x02a7:
        goto L_0x02b9;
    L_0x02a8:
        r5 = "productinfo";
        r6 = r1.c;
        r6 = r6.f();
        r5 = r1.b(r5, r6);
        r2.append(r5);
        goto L_0x034c;
    L_0x02b9:
        r2 = "Mandatory param product info is missing";
        r2 = r1.d(r2);
        return r2;
    L_0x02c0:
        java.lang.Double.valueOf(r14);
        r5 = r1.c;	 Catch:{ NumberFormatException -> 0x02ed, NullPointerException -> 0x02e4 }
        if (r5 == 0) goto L_0x02d1;
    L_0x02c7:
        r5 = r1.c;	 Catch:{ NumberFormatException -> 0x02ed, NullPointerException -> 0x02e4 }
        r5 = r5.e();	 Catch:{ NumberFormatException -> 0x02ed, NullPointerException -> 0x02e4 }
        r14 = java.lang.Double.parseDouble(r5);	 Catch:{ NumberFormatException -> 0x02ed, NullPointerException -> 0x02e4 }
    L_0x02d1:
        java.lang.Double.valueOf(r14);	 Catch:{ NumberFormatException -> 0x02ed, NullPointerException -> 0x02e4 }
        r5 = "amount";
        r6 = r1.c;
        r6 = r6.e();
        r5 = r1.b(r5, r6);
        r2.append(r5);
        goto L_0x034c;
    L_0x02e4:
        r2 = 5003; // 0x138b float:7.01E-42 double:2.472E-320;
        r3 = " Amount should be a Double value example 5.00";
        r2 = r1.a(r2, r3);
        return r2;
    L_0x02ed:
        r2 = " Amount should be a Double value example 5.00";
        r2 = r1.a(r13, r2);
        return r2;
    L_0x02f4:
        r5 = r1.c;
        r5 = r5.d();
        if (r5 == 0) goto L_0x0319;
    L_0x02fc:
        r5 = r1.c;
        r5 = r5.d();
        r5 = r5.length();
        if (r5 >= r6) goto L_0x0309;
    L_0x0308:
        goto L_0x0319;
    L_0x0309:
        r5 = "txnid";
        r6 = r1.c;
        r6 = r6.d();
        r5 = r1.b(r5, r6);
        r2.append(r5);
        goto L_0x034c;
    L_0x0319:
        r2 = "Mandatory param txnid is missing";
        r2 = r1.d(r2);
        return r2;
    L_0x0320:
        r5 = r1.c;
        r5 = r5.c();
        if (r5 == 0) goto L_0x0345;
    L_0x0328:
        r5 = r1.c;
        r5 = r5.c();
        r5 = r5.length();
        if (r5 >= r6) goto L_0x0335;
    L_0x0334:
        goto L_0x0345;
    L_0x0335:
        r5 = "key";
        r6 = r1.c;
        r6 = r6.c();
        r5 = r1.b(r5, r6);
        r2.append(r5);
        goto L_0x034c;
    L_0x0345:
        r2 = "Mandatory param key is missing";
        r2 = r1.d(r2);
        return r2;
    L_0x034c:
        r4 = r4 + 1;
        goto L_0x004e;
    L_0x0350:
        r4 = r1.c;
        r4 = r4.m();
        if (r4 == 0) goto L_0x0367;
    L_0x0358:
        r4 = "phone";
        r5 = r1.c;
        r5 = r5.m();
        r4 = r1.b(r4, r5);
        r2.append(r4);
    L_0x0367:
        r4 = r1.c;
        r4 = r4.l();
        if (r4 == 0) goto L_0x037c;
    L_0x036f:
        r4 = "offer_key";
        r5 = r1.c;
        r5 = r5.l();
        r4 = r1.b(r4, r5);
        goto L_0x037e;
    L_0x037c:
        r4 = "";
    L_0x037e:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.n();
        if (r4 == 0) goto L_0x0396;
    L_0x0389:
        r4 = "lastname";
        r5 = r1.c;
        r5 = r5.n();
        r4 = r1.b(r4, r5);
        goto L_0x0398;
    L_0x0396:
        r4 = "";
    L_0x0398:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.o();
        if (r4 == 0) goto L_0x03b0;
    L_0x03a3:
        r4 = "address1";
        r5 = r1.c;
        r5 = r5.o();
        r4 = r1.b(r4, r5);
        goto L_0x03b2;
    L_0x03b0:
        r4 = "";
    L_0x03b2:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.p();
        if (r4 == 0) goto L_0x03ca;
    L_0x03bd:
        r4 = "address2";
        r5 = r1.c;
        r5 = r5.p();
        r4 = r1.b(r4, r5);
        goto L_0x03cc;
    L_0x03ca:
        r4 = "";
    L_0x03cc:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.q();
        if (r4 == 0) goto L_0x03e4;
    L_0x03d7:
        r4 = "city";
        r5 = r1.c;
        r5 = r5.q();
        r4 = r1.b(r4, r5);
        goto L_0x03e6;
    L_0x03e4:
        r4 = "";
    L_0x03e6:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.r();
        if (r4 == 0) goto L_0x03fe;
    L_0x03f1:
        r4 = "state";
        r5 = r1.c;
        r5 = r5.r();
        r4 = r1.b(r4, r5);
        goto L_0x0400;
    L_0x03fe:
        r4 = "";
    L_0x0400:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.s();
        if (r4 == 0) goto L_0x0418;
    L_0x040b:
        r4 = "country";
        r5 = r1.c;
        r5 = r5.s();
        r4 = r1.b(r4, r5);
        goto L_0x041a;
    L_0x0418:
        r4 = "";
    L_0x041a:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.t();
        if (r4 == 0) goto L_0x0432;
    L_0x0425:
        r4 = "zipcode";
        r5 = r1.c;
        r5 = r5.t();
        r4 = r1.b(r4, r5);
        goto L_0x0434;
    L_0x0432:
        r4 = "";
    L_0x0434:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.z();
        if (r4 == 0) goto L_0x044c;
    L_0x043f:
        r4 = "codurl";
        r5 = r1.c;
        r5 = r5.z();
        r4 = r1.b(r4, r5);
        goto L_0x044e;
    L_0x044c:
        r4 = "";
    L_0x044e:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.A();
        if (r4 == 0) goto L_0x0466;
    L_0x0459:
        r4 = "drop_category";
        r5 = r1.c;
        r5 = r5.A();
        r4 = r1.b(r4, r5);
        goto L_0x0468;
    L_0x0466:
        r4 = "";
    L_0x0468:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.B();
        if (r4 == 0) goto L_0x0480;
    L_0x0473:
        r4 = "enforce_paymethod";
        r5 = r1.c;
        r5 = r5.B();
        r4 = r1.b(r4, r5);
        goto L_0x0482;
    L_0x0480:
        r4 = "";
    L_0x0482:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.C();
        if (r4 == 0) goto L_0x049a;
    L_0x048d:
        r4 = "custom_note";
        r5 = r1.c;
        r5 = r5.C();
        r4 = r1.b(r4, r5);
        goto L_0x049c;
    L_0x049a:
        r4 = "";
    L_0x049c:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.D();
        if (r4 == 0) goto L_0x04b4;
    L_0x04a7:
        r4 = "note_category";
        r5 = r1.c;
        r5 = r5.D();
        r4 = r1.b(r4, r5);
        goto L_0x04b6;
    L_0x04b4:
        r4 = "";
    L_0x04b6:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.E();
        if (r4 == 0) goto L_0x04ce;
    L_0x04c1:
        r4 = "shipping_firstname";
        r5 = r1.c;
        r5 = r5.E();
        r4 = r1.b(r4, r5);
        goto L_0x04d0;
    L_0x04ce:
        r4 = "";
    L_0x04d0:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.F();
        if (r4 == 0) goto L_0x04e8;
    L_0x04db:
        r4 = "shipping_lastname";
        r5 = r1.c;
        r5 = r5.F();
        r4 = r1.b(r4, r5);
        goto L_0x04ea;
    L_0x04e8:
        r4 = "";
    L_0x04ea:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.G();
        if (r4 == 0) goto L_0x0502;
    L_0x04f5:
        r4 = "shipping_address1";
        r5 = r1.c;
        r5 = r5.G();
        r4 = r1.b(r4, r5);
        goto L_0x0504;
    L_0x0502:
        r4 = "";
    L_0x0504:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.H();
        if (r4 == 0) goto L_0x051c;
    L_0x050f:
        r4 = "shipping_address2";
        r5 = r1.c;
        r5 = r5.H();
        r4 = r1.b(r4, r5);
        goto L_0x051e;
    L_0x051c:
        r4 = "";
    L_0x051e:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.I();
        if (r4 == 0) goto L_0x0536;
    L_0x0529:
        r4 = "shipping_city";
        r5 = r1.c;
        r5 = r5.I();
        r4 = r1.b(r4, r5);
        goto L_0x0538;
    L_0x0536:
        r4 = "";
    L_0x0538:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.J();
        if (r4 == 0) goto L_0x0550;
    L_0x0543:
        r4 = "shipping_state";
        r5 = r1.c;
        r5 = r5.J();
        r4 = r1.b(r4, r5);
        goto L_0x0552;
    L_0x0550:
        r4 = "";
    L_0x0552:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.K();
        if (r4 == 0) goto L_0x056a;
    L_0x055d:
        r4 = "shipping_county";
        r5 = r1.c;
        r5 = r5.K();
        r4 = r1.b(r4, r5);
        goto L_0x056c;
    L_0x056a:
        r4 = "";
    L_0x056c:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.L();
        if (r4 == 0) goto L_0x0584;
    L_0x0577:
        r4 = "shipping_zipcode";
        r5 = r1.c;
        r5 = r5.L();
        r4 = r1.b(r4, r5);
        goto L_0x0586;
    L_0x0584:
        r4 = "";
    L_0x0586:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.M();
        if (r4 == 0) goto L_0x059e;
    L_0x0591:
        r4 = "shipping_phone";
        r5 = r1.c;
        r5 = r5.M();
        r4 = r1.b(r4, r5);
        goto L_0x05a0;
    L_0x059e:
        r4 = "";
    L_0x05a0:
        r2.append(r4);
        r4 = r1.d;
        r5 = "EMI";
        r4 = r4.contentEquals(r5);
        if (r4 != 0) goto L_0x05c7;
    L_0x05ad:
        r4 = r1.c;
        r4 = r4.aa();
        if (r4 == 0) goto L_0x05c2;
    L_0x05b5:
        r4 = "subvention_amount";
        r5 = r1.c;
        r5 = r5.aa();
        r4 = r1.b(r4, r5);
        goto L_0x05c4;
    L_0x05c2:
        r4 = "";
    L_0x05c4:
        r2.append(r4);
    L_0x05c7:
        r18.d();
        r4 = r1.d;
        r5 = r4.hashCode();
        switch(r5) {
            case -40437580: goto L_0x0621;
            case 2144: goto L_0x0616;
            case 2484: goto L_0x060b;
            case 68769: goto L_0x0600;
            case 82953: goto L_0x05f5;
            case 116014: goto L_0x05ea;
            case 2061107: goto L_0x05df;
            case 1351375534: goto L_0x05d4;
            default: goto L_0x05d3;
        };
    L_0x05d3:
        goto L_0x062c;
    L_0x05d4:
        r5 = "PAYU_MONEY";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x062c;
    L_0x05dc:
        r16 = r11;
        goto L_0x062e;
    L_0x05df:
        r5 = "CASH";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x062c;
    L_0x05e7:
        r16 = 3;
        goto L_0x062e;
    L_0x05ea:
        r5 = "upi";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x062c;
    L_0x05f2:
        r16 = r9;
        goto L_0x062e;
    L_0x05f5:
        r5 = "TEZ";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x062c;
    L_0x05fd:
        r16 = r8;
        goto L_0x062e;
    L_0x0600:
        r5 = "EMI";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x062c;
    L_0x0608:
        r16 = r7;
        goto L_0x062e;
    L_0x060b:
        r5 = "NB";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x062c;
    L_0x0613:
        r16 = r6;
        goto L_0x062e;
    L_0x0616:
        r5 = "CC";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x062c;
    L_0x061e:
        r16 = r3;
        goto L_0x062e;
    L_0x0621:
        r5 = "lazypay";
        r4 = r4.equals(r5);
        if (r4 == 0) goto L_0x062c;
    L_0x0629:
        r16 = r10;
        goto L_0x062e;
    L_0x062c:
        r16 = r12;
    L_0x062e:
        r4 = 5005; // 0x138d float:7.013E-42 double:2.473E-320;
        switch(r16) {
            case 0: goto L_0x0a9c;
            case 1: goto L_0x0a58;
            case 2: goto L_0x07ee;
            case 3: goto L_0x07aa;
            case 4: goto L_0x0785;
            case 5: goto L_0x0720;
            case 6: goto L_0x0682;
            case 7: goto L_0x063e;
            default: goto L_0x0633;
        };
    L_0x0633:
        r4 = "SUCCESS";
        r2 = r2.toString();
        r2 = r1.a(r3, r4, r2);
        return r2;
    L_0x063e:
        r4 = "bankcode";
        r5 = "TEZ";
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "pg";
        r5 = "upi";
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.b();
        r4 = android.text.TextUtils.isEmpty(r4);
        if (r4 != 0) goto L_0x0673;
    L_0x0660:
        r4 = "vpa";
        r5 = r1.c;
        r5 = r5.b();
        r5 = r5.trim();
        r4 = r1.b(r4, r5);
        r2.append(r4);
    L_0x0673:
        r4 = "SUCCESS";
        r2 = r2.toString();
        r2 = r1.e(r2);
        r2 = r1.a(r3, r4, r2);
        return r2;
    L_0x0682:
        r4 = r1.c;
        r4 = r4.b();
        r5 = 5022; // 0x139e float:7.037E-42 double:2.481E-320;
        if (r4 != 0) goto L_0x0693;
    L_0x068c:
        r2 = "virtual address is null";
        r2 = r1.a(r5, r2);
        return r2;
    L_0x0693:
        r4 = r1.c;
        r4 = r4.b();
        r4 = r4.trim();
        r4 = r4.length();
        if (r4 != 0) goto L_0x06aa;
    L_0x06a3:
        r2 = "virtual address is empty";
        r2 = r1.a(r5, r2);
        return r2;
    L_0x06aa:
        r4 = r1.c;
        r4 = r4.b();
        r4 = r4.trim();
        r4 = r4.length();
        r6 = 50;
        if (r4 <= r6) goto L_0x06c3;
    L_0x06bc:
        r2 = "virtual address length should be less then 50 charaters";
        r2 = r1.a(r5, r2);
        return r2;
    L_0x06c3:
        r4 = r1.c;
        r4 = r4.b();
        r4 = r4.trim();
        r6 = ".+@.+";
        r6 = java.util.regex.Pattern.compile(r6);
        r4 = r6.matcher(r4);
        r4 = r4.matches();
        if (r4 != 0) goto L_0x06e4;
    L_0x06dd:
        r2 = "INVALID VPA";
        r2 = r1.a(r5, r2);
        return r2;
    L_0x06e4:
        r4 = "bankcode";
        r5 = "upi";
        r5 = r5.toLowerCase();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "pg";
        r5 = "upi";
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "vpa";
        r5 = r1.c;
        r5 = r5.b();
        r5 = r5.trim();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "SUCCESS";
        r2 = r2.toString();
        r2 = r1.e(r2);
        r2 = r1.a(r3, r4, r2);
        return r2;
    L_0x0720:
        r4 = "bankcode";
        r5 = "";
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "pg";
        r5 = "";
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = r1.c;	 Catch:{ Exception -> 0x0762 }
        r4 = r4.a();	 Catch:{ Exception -> 0x0762 }
        if (r4 == 0) goto L_0x0767;
    L_0x073e:
        r4 = r1.c;	 Catch:{ Exception -> 0x0762 }
        r4 = r4.a();	 Catch:{ Exception -> 0x0762 }
        r5 = "";
        r4 = r4.equalsIgnoreCase(r5);	 Catch:{ Exception -> 0x0762 }
        if (r4 != 0) goto L_0x0767;
    L_0x074c:
        r4 = "notifyurl";
        r5 = r1.c;	 Catch:{ Exception -> 0x0762 }
        r5 = r5.a();	 Catch:{ Exception -> 0x0762 }
        r6 = "UTF-8";
        r5 = java.net.URLEncoder.encode(r5, r6);	 Catch:{ Exception -> 0x0762 }
        r4 = r1.b(r4, r5);	 Catch:{ Exception -> 0x0762 }
        r2.append(r4);	 Catch:{ Exception -> 0x0762 }
        goto L_0x0767;
    L_0x0762:
        r0 = move-exception;
        r4 = r0;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r4);
    L_0x0767:
        r4 = "enforce_paymethod";
        r5 = "lazypay";
        r5 = r5.toUpperCase();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "SUCCESS";
        r2 = r2.toString();
        r2 = r1.e(r2);
        r2 = r1.a(r3, r4, r2);
        return r2;
    L_0x0785:
        r4 = "bankcode";
        r5 = "PAYUW";
        r5 = r5.toLowerCase();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "pg";
        r5 = "wallet";
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "SUCCESS";
        r2 = r2.toString();
        r2 = r1.a(r3, r4, r2);
        return r2;
    L_0x07aa:
        r5 = "pg";
        r7 = "CASH";
        r5 = r1.b(r5, r7);
        r2.append(r5);
        r5 = r1.c;
        if (r5 == 0) goto L_0x07e7;
    L_0x07b9:
        r5 = r1.c;
        r5 = r5.X();
        if (r5 == 0) goto L_0x07e7;
    L_0x07c1:
        r5 = r1.c;
        r5 = r5.X();
        r5 = r5.length();
        if (r5 <= r6) goto L_0x07e7;
    L_0x07cd:
        r4 = "bankcode";
        r5 = r1.c;
        r5 = r5.X();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "SUCCESS";
        r2 = r2.toString();
        r2 = r1.a(r3, r4, r2);
        return r2;
    L_0x07e7:
        r2 = "Invalid bank code please verify";
        r2 = r1.a(r4, r2);
        return r2;
    L_0x07ee:
        r4 = r1.c;
        r4 = r4.X();
        if (r4 == 0) goto L_0x0a51;
    L_0x07f6:
        r4 = r1.c;
        r4 = r4.X();
        r4 = r4.length();
        if (r4 <= r6) goto L_0x0a51;
    L_0x0802:
        r4 = "pg";
        r5 = "EMI";
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "bankcode";
        r5 = r1.c;
        r5 = r5.X();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.aa();
        if (r4 == 0) goto L_0x0876;
    L_0x0824:
        java.lang.Double.valueOf(r14);
        r4 = 5021; // 0x139d float:7.036E-42 double:2.4807E-320;
        r5 = r1.c;	 Catch:{ NumberFormatException -> 0x086f, NullPointerException -> 0x0868 }
        r5 = r5.aa();	 Catch:{ NumberFormatException -> 0x086f, NullPointerException -> 0x0868 }
        r7 = java.lang.Double.parseDouble(r5);	 Catch:{ NumberFormatException -> 0x086f, NullPointerException -> 0x0868 }
        r5 = java.lang.Double.valueOf(r7);	 Catch:{ NumberFormatException -> 0x086f, NullPointerException -> 0x0868 }
        r7 = r5.doubleValue();
        r9 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1));
        if (r9 < 0) goto L_0x0861;
    L_0x083f:
        r7 = r5.doubleValue();
        r5 = r1.c;
        r5 = r5.e();
        r9 = java.lang.Double.parseDouble(r5);
        r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1));
        if (r5 > 0) goto L_0x0861;
    L_0x0851:
        r4 = "subvention_amount";
        r5 = r1.c;
        r5 = r5.aa();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        goto L_0x0876;
    L_0x0861:
        r2 = "Subvention Amount should be positive and less than or equal to the transaction amount.";
        r2 = r1.a(r4, r2);
        return r2;
    L_0x0868:
        r2 = "Subvention Amount should be a Double value example 5.00";
        r2 = r1.a(r4, r2);
        return r2;
    L_0x086f:
        r2 = "Subvention Amount should be a Double value example 5.00";
        r2 = r1.a(r13, r2);
        return r2;
    L_0x0876:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "";
        r4.append(r5);
        r5 = r1.c;
        r5 = r5.P();
        r4.append(r5);
        r4 = r4.toString();
        r4 = r1.a(r4);
        r4 = r4.booleanValue();
        if (r4 == 0) goto L_0x0a48;
    L_0x0897:
        r4 = "ccnum";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r7 = "";
        r5.append(r7);
        r7 = r1.c;
        r7 = r7.P();
        r5.append(r7);
        r5 = r5.toString();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "";
        r4.append(r5);
        r5 = r1.c;
        r5 = r5.P();
        r4.append(r5);
        r4 = r4.toString();
        r4 = r1.c(r4);
        r5 = "SMAE";
        r4 = r4.contentEquals(r5);
        if (r4 != 0) goto L_0x09a2;
    L_0x08da:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "";
        r4.append(r5);
        r5 = r1.c;
        r5 = r5.P();
        r4.append(r5);
        r4 = r4.toString();
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r7 = "";
        r5.append(r7);
        r7 = r1.c;
        r7 = r7.Q();
        r5.append(r7);
        r5 = r5.toString();
        r4 = r1.a(r4, r5);
        if (r4 == 0) goto L_0x0999;
    L_0x090e:
        r4 = "ccvv";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r7 = "";
        r5.append(r7);
        r7 = r1.c;
        r7 = r7.Q();
        r5.append(r7);
        r5 = r5.toString();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = r1.c;	 Catch:{ NumberFormatException -> 0x0992 }
        r4 = r4.R();	 Catch:{ NumberFormatException -> 0x0992 }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ NumberFormatException -> 0x0992 }
        r5 = r1.c;	 Catch:{ NumberFormatException -> 0x0992 }
        r5 = r5.S();	 Catch:{ NumberFormatException -> 0x0992 }
        r5 = java.lang.Integer.parseInt(r5);	 Catch:{ NumberFormatException -> 0x0992 }
        r4 = r1.a(r4, r5);	 Catch:{ NumberFormatException -> 0x0992 }
        if (r4 == 0) goto L_0x0989;
    L_0x0948:
        r4 = "ccexpyr";
        r5 = new java.lang.StringBuilder;	 Catch:{ NumberFormatException -> 0x0992 }
        r5.<init>();	 Catch:{ NumberFormatException -> 0x0992 }
        r7 = "";
        r5.append(r7);	 Catch:{ NumberFormatException -> 0x0992 }
        r7 = r1.c;	 Catch:{ NumberFormatException -> 0x0992 }
        r7 = r7.S();	 Catch:{ NumberFormatException -> 0x0992 }
        r5.append(r7);	 Catch:{ NumberFormatException -> 0x0992 }
        r5 = r5.toString();	 Catch:{ NumberFormatException -> 0x0992 }
        r4 = r1.b(r4, r5);	 Catch:{ NumberFormatException -> 0x0992 }
        r2.append(r4);	 Catch:{ NumberFormatException -> 0x0992 }
        r4 = "ccexpmon";
        r5 = new java.lang.StringBuilder;	 Catch:{ NumberFormatException -> 0x0992 }
        r5.<init>();	 Catch:{ NumberFormatException -> 0x0992 }
        r7 = "";
        r5.append(r7);	 Catch:{ NumberFormatException -> 0x0992 }
        r7 = r1.c;	 Catch:{ NumberFormatException -> 0x0992 }
        r7 = r7.R();	 Catch:{ NumberFormatException -> 0x0992 }
        r5.append(r7);	 Catch:{ NumberFormatException -> 0x0992 }
        r5 = r5.toString();	 Catch:{ NumberFormatException -> 0x0992 }
        r4 = r1.b(r4, r5);	 Catch:{ NumberFormatException -> 0x0992 }
        r2.append(r4);	 Catch:{ NumberFormatException -> 0x0992 }
        goto L_0x09a2;
    L_0x0989:
        r2 = 5012; // 0x1394 float:7.023E-42 double:2.4763E-320;
        r3 = " It seems the card is expired!";
        r2 = r1.a(r2, r3);	 Catch:{ NumberFormatException -> 0x0992 }
        return r2;
    L_0x0992:
        r2 = " It seems the card is expired!";
        r2 = r1.a(r13, r2);
        return r2;
    L_0x0999:
        r2 = 5009; // 0x1391 float:7.019E-42 double:2.475E-320;
        r3 = " Invalid cvv, please verify";
        r2 = r1.a(r2, r3);
        return r2;
    L_0x09a2:
        r4 = r1.c;
        r4 = r4.T();
        if (r4 != 0) goto L_0x09b3;
    L_0x09aa:
        r4 = "ccname";
        r5 = "PayuUser";
    L_0x09ae:
        r4 = r1.b(r4, r5);
        goto L_0x09bc;
    L_0x09b3:
        r4 = "ccname";
        r5 = r1.c;
        r5 = r5.T();
        goto L_0x09ae;
    L_0x09bc:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.O();
        if (r4 != r6) goto L_0x0a39;
    L_0x09c7:
        r4 = r1.c;
        r4 = r4.N();
        if (r4 == 0) goto L_0x0a32;
    L_0x09cf:
        r4 = r1.c;
        r4 = r4.U();
        if (r4 != 0) goto L_0x09e0;
    L_0x09d7:
        r4 = "card_name";
        r5 = "PayuUser";
    L_0x09db:
        r4 = r1.b(r4, r5);
        goto L_0x09e9;
    L_0x09e0:
        r4 = "name_on_card";
        r5 = r1.c;
        r5 = r5.U();
        goto L_0x09db;
    L_0x09e9:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.N();
        if (r4 == 0) goto L_0x0a01;
    L_0x09f4:
        r4 = "user_credentials";
        r5 = r1.c;
        r5 = r5.N();
        r4 = r1.b(r4, r5);
        goto L_0x0a03;
    L_0x0a01:
        r4 = "";
    L_0x0a03:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.O();
        if (r4 != r6) goto L_0x0a2c;
    L_0x0a0e:
        r4 = "store_card";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "";
        r5.append(r6);
        r6 = r1.c;
        r6 = r6.O();
        r5.append(r6);
        r5 = r5.toString();
        r4 = r1.b(r4, r5);
        goto L_0x0a2e;
    L_0x0a2c:
        r4 = "";
    L_0x0a2e:
        r2.append(r4);
        goto L_0x0a39;
    L_0x0a32:
        r2 = " Card can not be stored!, user_credentials is missing!";
        r2 = r1.d(r2);
        return r2;
    L_0x0a39:
        r4 = "SUCCESS";
        r2 = r2.toString();
        r2 = r1.e(r2);
        r2 = r1.a(r3, r4, r2);
        return r2;
    L_0x0a48:
        r2 = 5008; // 0x1390 float:7.018E-42 double:2.4743E-320;
        r3 = " Invalid card number, Failed while applying Luhn";
        r2 = r1.a(r2, r3);
        return r2;
    L_0x0a51:
        r2 = "Please provide valid email details";
        r2 = r1.d(r2);
        return r2;
    L_0x0a58:
        r5 = r1.c;
        r5 = r5.X();
        if (r5 == 0) goto L_0x0a95;
    L_0x0a60:
        r5 = r1.c;
        r5 = r5.X();
        r5 = r5.length();
        if (r5 <= r6) goto L_0x0a95;
    L_0x0a6c:
        r4 = "pg";
        r5 = "NB";
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "bankcode";
        r5 = r1.c;
        r5 = r5.X();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "SUCCESS";
        r2 = r2.toString();
        r2 = r1.e(r2);
        r2 = r1.a(r3, r4, r2);
        return r2;
    L_0x0a95:
        r2 = "Invalid bank code please verify";
        r2 = r1.a(r4, r2);
        return r2;
    L_0x0a9c:
        r4 = "pg";
        r5 = "CC";
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "bankcode";
        r5 = "CC";
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.P();
        if (r4 == 0) goto L_0x0ca1;
    L_0x0aba:
        r4 = r1.c;
        r4 = r4.P();
        r4 = r1.a(r4);
        r4 = r4.booleanValue();
        if (r4 == 0) goto L_0x0ca1;
    L_0x0aca:
        r4 = "ccnum";
        r5 = r1.c;
        r5 = r5.P();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.P();
        r4 = r1.c(r4);
        r5 = "SMAE";
        r4 = r4.contentEquals(r5);
        if (r4 != 0) goto L_0x0b68;
    L_0x0aeb:
        r4 = r1.c;
        r4 = r4.P();
        r5 = r1.c;
        r5 = r5.Q();
        r4 = r1.a(r4, r5);
        if (r4 == 0) goto L_0x0b5f;
    L_0x0afd:
        r4 = "ccvv";
        r5 = r1.c;
        r5 = r5.Q();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = r1.c;	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        r4 = r4.R();	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        r5 = r1.c;	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        r5 = r5.S();	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        r5 = java.lang.Integer.parseInt(r5);	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        r4 = r1.a(r4, r5);	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        if (r4 == 0) goto L_0x0b46;
    L_0x0b26:
        r4 = "ccexpyr";
        r5 = r1.c;	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        r5 = r5.S();	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        r4 = r1.b(r4, r5);	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        r2.append(r4);	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        r4 = "ccexpmon";
        r5 = r1.c;	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        r5 = r5.R();	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        r4 = r1.b(r4, r5);	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        r2.append(r4);	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        goto L_0x0bc7;
    L_0x0b46:
        r2 = 5012; // 0x1394 float:7.023E-42 double:2.4763E-320;
        r3 = " It seems the card is expired!";
        r2 = r1.a(r2, r3);	 Catch:{ NumberFormatException -> 0x0b58, Exception -> 0x0b4f }
        return r2;
    L_0x0b4f:
        r2 = 5001; // 0x1389 float:7.008E-42 double:2.471E-320;
        r3 = " It seems the card is expired!";
        r2 = r1.a(r2, r3);
        return r2;
    L_0x0b58:
        r2 = " It seems the card is expired!";
        r2 = r1.a(r13, r2);
        return r2;
    L_0x0b5f:
        r2 = 5009; // 0x1391 float:7.019E-42 double:2.475E-320;
        r3 = " Invalid cvv, please verify";
        r2 = r1.a(r2, r3);
        return r2;
    L_0x0b68:
        r4 = r1.c;
        r4 = r4.P();
        r5 = r1.c;
        r5 = r5.Q();
        r4 = r1.a(r4, r5);
        if (r4 == 0) goto L_0x0b89;
    L_0x0b7a:
        r4 = "ccvv";
        r5 = r1.c;
        r5 = r5.Q();
        r4 = r1.b(r4, r5);
        r2.append(r4);
    L_0x0b89:
        r4 = r1.c;	 Catch:{ Exception -> 0x0bc2 }
        r4 = r4.R();	 Catch:{ Exception -> 0x0bc2 }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ Exception -> 0x0bc2 }
        r5 = r1.c;	 Catch:{ Exception -> 0x0bc2 }
        r5 = r5.S();	 Catch:{ Exception -> 0x0bc2 }
        r5 = java.lang.Integer.parseInt(r5);	 Catch:{ Exception -> 0x0bc2 }
        r4 = r1.a(r4, r5);	 Catch:{ Exception -> 0x0bc2 }
        if (r4 == 0) goto L_0x0bc7;
    L_0x0ba3:
        r4 = "ccexpyr";
        r5 = r1.c;	 Catch:{ Exception -> 0x0bc2 }
        r5 = r5.S();	 Catch:{ Exception -> 0x0bc2 }
        r4 = r1.b(r4, r5);	 Catch:{ Exception -> 0x0bc2 }
        r2.append(r4);	 Catch:{ Exception -> 0x0bc2 }
        r4 = "ccexpmon";
        r5 = r1.c;	 Catch:{ Exception -> 0x0bc2 }
        r5 = r5.R();	 Catch:{ Exception -> 0x0bc2 }
        r4 = r1.b(r4, r5);	 Catch:{ Exception -> 0x0bc2 }
        r2.append(r4);	 Catch:{ Exception -> 0x0bc2 }
        goto L_0x0bc7;
    L_0x0bc2:
        r0 = move-exception;
        r4 = r0;
        com.google.devtools.build.android.desugar.runtime.ThrowableExtension.printStackTrace(r4);
    L_0x0bc7:
        r4 = r1.c;
        r4 = r4.T();
        if (r4 == 0) goto L_0x0be6;
    L_0x0bcf:
        r4 = r1.c;
        r4 = r4.T();
        r4 = r4.trim();
        r4 = r4.length();
        if (r4 <= 0) goto L_0x0be6;
    L_0x0bdf:
        r4 = r1.c;
        r4 = r4.T();
        goto L_0x0be8;
    L_0x0be6:
        r4 = "PayuUser";
    L_0x0be8:
        r5 = r1.c;
        r5 = r5.U();
        if (r5 == 0) goto L_0x0bf7;
    L_0x0bf0:
        r5 = r1.c;
        r5 = r5.U();
        goto L_0x0bf8;
    L_0x0bf7:
        r5 = r4;
    L_0x0bf8:
        r7 = "ccname";
        r4 = r1.b(r7, r4);
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.O();
        if (r4 != r6) goto L_0x0c92;
    L_0x0c09:
        r4 = r1.c;
        r4 = r4.N();
        if (r4 == 0) goto L_0x0c8b;
    L_0x0c11:
        r4 = "card_name";
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.N();
        if (r4 == 0) goto L_0x0c2f;
    L_0x0c22:
        r4 = "user_credentials";
        r5 = r1.c;
        r5 = r5.N();
        r4 = r1.b(r4, r5);
        goto L_0x0c31;
    L_0x0c2f:
        r4 = "";
    L_0x0c31:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.O();
        if (r4 != r6) goto L_0x0c5a;
    L_0x0c3c:
        r4 = "store_card";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r7 = "";
        r5.append(r7);
        r7 = r1.c;
        r7 = r7.O();
        r5.append(r7);
        r5 = r5.toString();
        r4 = r1.b(r4, r5);
        goto L_0x0c5c;
    L_0x0c5a:
        r4 = "";
    L_0x0c5c:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.Y();
        if (r4 != r6) goto L_0x0c85;
    L_0x0c67:
        r4 = "one_click_checkout";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "";
        r5.append(r6);
        r6 = r1.c;
        r6 = r6.Y();
        r5.append(r6);
        r5 = r5.toString();
        r4 = r1.b(r4, r5);
        goto L_0x0c87;
    L_0x0c85:
        r4 = "";
    L_0x0c87:
        r2.append(r4);
        goto L_0x0c92;
    L_0x0c8b:
        r2 = " Card can not be stored!, user_credentials is missing!";
        r2 = r1.d(r2);
        return r2;
    L_0x0c92:
        r4 = "SUCCESS";
        r2 = r2.toString();
        r2 = r1.e(r2);
        r2 = r1.a(r3, r4, r2);
        return r2;
    L_0x0ca1:
        r4 = r1.c;
        r4 = r4.V();
        if (r4 == 0) goto L_0x0df0;
    L_0x0ca9:
        r4 = r1.c;
        r4 = r4.N();
        if (r4 == 0) goto L_0x0de7;
    L_0x0cb1:
        r4 = "user_credentials";
        r5 = r1.c;
        r5 = r5.N();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = "store_card_token";
        r5 = r1.c;
        r5 = r5.V();
        r4 = r1.b(r4, r5);
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.W();
        if (r4 == 0) goto L_0x0d21;
    L_0x0cd7:
        r4 = r1.c;
        r4 = r4.W();
        r4 = r1.c(r4);
        r5 = "SMAE";
        r4 = r4.contentEquals(r5);
        if (r4 != 0) goto L_0x0d21;
    L_0x0ce9:
        r4 = r1.c;
        r4 = r4.Q();
        if (r4 != 0) goto L_0x0d00;
    L_0x0cf1:
        r4 = r1.c;
        r4 = r4.Z();
        if (r4 != 0) goto L_0x0d00;
    L_0x0cf9:
        r2 = " Invalid cvv, please verify";
        r2 = r1.d(r2);
        return r2;
    L_0x0d00:
        r4 = r1.c;
        r4 = r4.R();
        r4 = java.lang.Integer.parseInt(r4);
        r5 = r1.c;
        r5 = r5.S();
        r5 = java.lang.Integer.parseInt(r5);
        r4 = r1.a(r4, r5);
        if (r4 != 0) goto L_0x0d21;
    L_0x0d1a:
        r2 = " It seems the card is expired!";
        r2 = r1.d(r2);
        return r2;
    L_0x0d21:
        r4 = r1.c;
        r4 = r4.Z();
        if (r4 != 0) goto L_0x0d47;
    L_0x0d29:
        r4 = r1.c;
        r4 = r4.Q();
        if (r4 == 0) goto L_0x0d3e;
    L_0x0d31:
        r4 = "ccvv";
        r5 = r1.c;
        r5 = r5.Q();
    L_0x0d39:
        r4 = r1.b(r4, r5);
        goto L_0x0d43;
    L_0x0d3e:
        r4 = "ccvv";
        r5 = "123";
        goto L_0x0d39;
    L_0x0d43:
        r2.append(r4);
        goto L_0x0d56;
    L_0x0d47:
        r4 = "card_merchant_param";
        r5 = r1.c;
        r5 = r5.Z();
        r4 = r1.b(r4, r5);
        r2.append(r4);
    L_0x0d56:
        r4 = r1.c;
        r4 = r4.R();
        if (r4 == 0) goto L_0x0d6b;
    L_0x0d5e:
        r4 = "ccexpmon";
        r5 = r1.c;
        r5 = r5.R();
    L_0x0d66:
        r4 = r1.b(r4, r5);
        goto L_0x0d70;
    L_0x0d6b:
        r4 = "ccexpmon";
        r5 = "12";
        goto L_0x0d66;
    L_0x0d70:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.S();
        if (r4 == 0) goto L_0x0d88;
    L_0x0d7b:
        r4 = "ccexpyr";
        r5 = r1.c;
        r5 = r5.S();
    L_0x0d83:
        r4 = r1.b(r4, r5);
        goto L_0x0d8d;
    L_0x0d88:
        r4 = "ccexpmon";
        r5 = "2080";
        goto L_0x0d83;
    L_0x0d8d:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.T();
        if (r4 != 0) goto L_0x0da1;
    L_0x0d98:
        r4 = "ccname";
        r5 = "PayuUser";
    L_0x0d9c:
        r4 = r1.b(r4, r5);
        goto L_0x0daa;
    L_0x0da1:
        r4 = "ccname";
        r5 = r1.c;
        r5 = r5.T();
        goto L_0x0d9c;
    L_0x0daa:
        r2.append(r4);
        r4 = r1.c;
        r4 = r4.Y();
        if (r4 != r6) goto L_0x0dd3;
    L_0x0db5:
        r4 = "one_click_checkout";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "";
        r5.append(r6);
        r6 = r1.c;
        r6 = r6.Y();
        r5.append(r6);
        r5 = r5.toString();
        r4 = r1.b(r4, r5);
        goto L_0x0dd5;
    L_0x0dd3:
        r4 = "";
    L_0x0dd5:
        r2.append(r4);
        r4 = "SUCCESS";
        r2 = r2.toString();
        r2 = r1.e(r2);
        r2 = r1.a(r3, r4, r2);
        return r2;
    L_0x0de7:
        r2 = 5013; // 0x1395 float:7.025E-42 double:2.4768E-320;
        r3 = "should be the user credentials and it should be merchant_key:unique_user_id.";
        r2 = r1.a(r2, r3);
        return r2;
    L_0x0df0:
        r2 = 5008; // 0x1390 float:7.018E-42 double:2.4743E-320;
        r3 = " Invalid card number, Failed while applying Luhn";
        r2 = r1.a(r2, r3);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.payu.india.d.b.c():com.payu.india.Model.PostData");
    }

    public void d() {
        c cVar = new c();
        HashMap hashMap = new HashMap();
        hashMap.put("transactionID", this.c.d());
        hashMap.put("keyAnalytics", this.c.c());
        hashMap.put("paymentMode", this.d);
        hashMap.put("sdkVersion", "4.4.3");
        c.a("com.payu.custombrowser.Bank", hashMap, "Version");
        cVar.a(this.e, this.c.c(), this.c.d());
    }
}
