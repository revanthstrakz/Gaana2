package com.payu.india.d;

import com.payu.india.Model.MerchantWebService;
import com.payu.india.c.c;

public class a extends c {
    private MerchantWebService c;
    private StringBuilder d;

    private a() {
    }

    public a(MerchantWebService merchantWebService) {
        this.c = merchantWebService;
    }

    public com.payu.india.Model.PostData c() {
        /*
        r13 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r13.d = r0;
        r0 = r13.d;
        r1 = "udid";
        r2 = r13.b();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "imei";
        r2 = r13.a();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.c;
        r0 = r0.b();
        if (r0 != 0) goto L_0x0034;
    L_0x002d:
        r0 = "Mandatory param key is missing";
        r0 = r13.d(r0);
        return r0;
    L_0x0034:
        r0 = r13.d;
        r1 = "key";
        r2 = r13.c;
        r2 = r2.b();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.c;
        r0 = r0.c();
        if (r0 != 0) goto L_0x0054;
    L_0x004d:
        r0 = "Mandatory param hash is missing";
        r0 = r13.d(r0);
        return r0;
    L_0x0054:
        r0 = r13.d;
        r1 = "hash";
        r2 = r13.c;
        r2 = r2.c();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.c;
        r0 = r0.a();
        if (r0 == 0) goto L_0x0e4d;
    L_0x006d:
        r0 = com.payu.india.c.b.b;
        r1 = r13.c;
        r1 = r1.a();
        r0 = r0.contains(r1);
        if (r0 != 0) goto L_0x007d;
    L_0x007b:
        goto L_0x0e4d;
    L_0x007d:
        r0 = r13.d;
        r1 = "command";
        r2 = r13.c;
        r2 = r2.a();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.c;
        r0 = r0.a();
        r1 = r0.hashCode();
        r2 = -1;
        r3 = 4;
        r4 = 5;
        r5 = 3;
        r6 = 6;
        r7 = 0;
        r8 = 2;
        r9 = 1;
        switch(r1) {
            case -1642953460: goto L_0x01f1;
            case -1629601947: goto L_0x01e7;
            case -1611920419: goto L_0x01dd;
            case -1575164929: goto L_0x01d2;
            case -1566387441: goto L_0x01c7;
            case -1510829279: goto L_0x01bc;
            case -1101190534: goto L_0x01b1;
            case -999218376: goto L_0x01a6;
            case -963056352: goto L_0x019b;
            case -612906866: goto L_0x018f;
            case -606489918: goto L_0x0183;
            case -395237378: goto L_0x0177;
            case -373410353: goto L_0x016c;
            case -369257104: goto L_0x0160;
            case -232071064: goto L_0x0154;
            case -74815623: goto L_0x0148;
            case -39017295: goto L_0x013c;
            case 398411622: goto L_0x0130;
            case 699655800: goto L_0x0124;
            case 777801508: goto L_0x0119;
            case 856253253: goto L_0x010d;
            case 881381098: goto L_0x0101;
            case 924438902: goto L_0x00f6;
            case 1668556369: goto L_0x00ea;
            case 1703878268: goto L_0x00df;
            case 1844660402: goto L_0x00d3;
            case 1901129290: goto L_0x00c8;
            case 1976307512: goto L_0x00bc;
            case 2000756420: goto L_0x00b0;
            case 2082265152: goto L_0x00a5;
            default: goto L_0x00a3;
        };
    L_0x00a3:
        goto L_0x01fc;
    L_0x00a5:
        r1 = "verify_payment";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x00ad:
        r0 = r5;
        goto L_0x01fd;
    L_0x00b0:
        r1 = "getEmiAmountAccordingToInterest";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x00b8:
        r0 = 29;
        goto L_0x01fd;
    L_0x00bc:
        r1 = "get_transaction_info";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x00c4:
        r0 = 19;
        goto L_0x01fd;
    L_0x00c8:
        r1 = "vas_for_mobile_sdk";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x00d0:
        r0 = r9;
        goto L_0x01fd;
    L_0x00d3:
        r1 = "cod_settled";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x00db:
        r0 = 11;
        goto L_0x01fd;
    L_0x00df:
        r1 = "cancel_refund_transaction";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x00e7:
        r0 = r4;
        goto L_0x01fd;
    L_0x00ea:
        r1 = "udf_update";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x00f2:
        r0 = 13;
        goto L_0x01fd;
    L_0x00f6:
        r1 = "get_merchant_ibibo_codes";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x00fe:
        r0 = r8;
        goto L_0x01fd;
    L_0x0101:
        r1 = "create_invoice";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x0109:
        r0 = 14;
        goto L_0x01fd;
    L_0x010d:
        r1 = "get_hashes";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x0115:
        r0 = 27;
        goto L_0x01fd;
    L_0x0119:
        r1 = "check_action_status";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x0121:
        r0 = r6;
        goto L_0x01fd;
    L_0x0124:
        r1 = "get_user_cards";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x012c:
        r0 = 21;
        goto L_0x01fd;
    L_0x0130:
        r1 = "delete_store_card_cvv";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x0138:
        r0 = 25;
        goto L_0x01fd;
    L_0x013c:
        r1 = "getNetbankingStatus";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x0144:
        r0 = 16;
        goto L_0x01fd;
    L_0x0148:
        r1 = "get_TDR";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x0150:
        r0 = 12;
        goto L_0x01fd;
    L_0x0154:
        r1 = "check_offer_details";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x015c:
        r0 = 28;
        goto L_0x01fd;
    L_0x0160:
        r1 = "delete_user_card";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x0168:
        r0 = 24;
        goto L_0x01fd;
    L_0x016c:
        r1 = "check_payment";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x0174:
        r0 = r3;
        goto L_0x01fd;
    L_0x0177:
        r1 = "mobileHashTestWs";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x017f:
        r0 = 26;
        goto L_0x01fd;
    L_0x0183:
        r1 = "save_user_card";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x018b:
        r0 = 22;
        goto L_0x01fd;
    L_0x018f:
        r1 = "getIssuingBankStatus";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x0197:
        r0 = 17;
        goto L_0x01fd;
    L_0x019b:
        r1 = "cod_verify";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x01a3:
        r0 = 9;
        goto L_0x01fd;
    L_0x01a6:
        r1 = "get_Transaction_Details";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x01ae:
        r0 = 18;
        goto L_0x01fd;
    L_0x01b1:
        r1 = "update_requests";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x01b9:
        r0 = 8;
        goto L_0x01fd;
    L_0x01bc:
        r1 = "cod_cancel";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x01c4:
        r0 = 10;
        goto L_0x01fd;
    L_0x01c7:
        r1 = "edit_user_card";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x01cf:
        r0 = 23;
        goto L_0x01fd;
    L_0x01d2:
        r1 = "check_isDomestic";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x01da:
        r0 = 20;
        goto L_0x01fd;
    L_0x01dd:
        r1 = "payment_related_details_for_mobile_sdk";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x01e5:
        r0 = r7;
        goto L_0x01fd;
    L_0x01e7:
        r1 = "capture_transaction";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x01ef:
        r0 = 7;
        goto L_0x01fd;
    L_0x01f1:
        r1 = "check_offer_status";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x01fc;
    L_0x01f9:
        r0 = 15;
        goto L_0x01fd;
    L_0x01fc:
        r0 = r2;
    L_0x01fd:
        r1 = 5012; // 0x1394 float:7.023E-42 double:2.4763E-320;
        r10 = 5002; // 0x138a float:7.009E-42 double:2.4713E-320;
        switch(r0) {
            case 0: goto L_0x0e1c;
            case 1: goto L_0x0dbd;
            case 2: goto L_0x0d9b;
            case 3: goto L_0x0d79;
            case 4: goto L_0x0d57;
            case 5: goto L_0x0cf5;
            case 6: goto L_0x0cd3;
            case 7: goto L_0x0c91;
            case 8: goto L_0x0bcf;
            case 9: goto L_0x0b6d;
            case 10: goto L_0x0b0b;
            case 11: goto L_0x0aa9;
            case 12: goto L_0x0a87;
            case 13: goto L_0x08fb;
            case 14: goto L_0x09a7;
            case 15: goto L_0x07c1;
            case 16: goto L_0x0792;
            case 17: goto L_0x0763;
            case 18: goto L_0x0721;
            case 19: goto L_0x06df;
            case 20: goto L_0x06b0;
            case 21: goto L_0x068e;
            case 22: goto L_0x0508;
            case 23: goto L_0x03a2;
            case 24: goto L_0x0354;
            case 25: goto L_0x0306;
            case 26: goto L_0x02e4;
            case 27: goto L_0x02c2;
            case 28: goto L_0x0228;
            case 29: goto L_0x0206;
            default: goto L_0x0204;
        };
    L_0x0204:
        goto L_0x0e3c;
    L_0x0206:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0215;
    L_0x020e:
        r0 = "Mandatory param var1 is missing";
        r0 = r13.d(r0);
        return r0;
    L_0x0215:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0228:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0237;
    L_0x0230:
        r0 = "Mandatory param var1 is missing";
        r0 = r13.d(r0);
        return r0;
    L_0x0237:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.c;
        r0 = r0.e();
        if (r0 != 0) goto L_0x0257;
    L_0x0250:
        r0 = "Mandatory param var2 is missing";
        r0 = r13.d(r0);
        return r0;
    L_0x0257:
        r0 = r13.d;
        r1 = "var2";
        r2 = r13.c;
        r2 = r2.e();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.c;
        r0 = r0.f();
        if (r0 != 0) goto L_0x0277;
    L_0x0270:
        r0 = "Mandatory param var3 is missing";
        r0 = r13.d(r0);
        return r0;
    L_0x0277:
        r0 = r13.d;
        r1 = "var3";
        r2 = r13.c;
        r2 = r2.f();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var4";
        r2 = r13.c;
        r2 = r2.g();
        if (r2 != 0) goto L_0x0297;
    L_0x0294:
        r2 = "";
        goto L_0x029d;
    L_0x0297:
        r2 = r13.c;
        r2 = r2.g();
    L_0x029d:
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var5";
        r2 = r13.c;
        r2 = r2.h();
        if (r2 != 0) goto L_0x02b3;
    L_0x02b0:
        r2 = "";
        goto L_0x02b9;
    L_0x02b3:
        r2 = r13.c;
        r2 = r2.h();
    L_0x02b9:
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x02c2:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x02d1;
    L_0x02ca:
        r0 = "Mandatory param var1 is missing";
        r0 = r13.d(r0);
        return r0;
    L_0x02d1:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x02e4:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x02f3;
    L_0x02ec:
        r0 = "Mandatory param var1 is missing";
        r0 = r13.d(r0);
        return r0;
    L_0x02f3:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0306:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0315;
    L_0x030e:
        r0 = "Mandatory param var1 is missing should be the user credentials and it should be merchant_key:unique_user_id.";
        r0 = r13.d(r0);
        return r0;
    L_0x0315:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.c;
        r0 = r0.e();
        if (r0 == 0) goto L_0x034d;
    L_0x032e:
        r0 = r13.c;
        r0 = r0.e();
        r0 = r0.length();
        if (r0 <= r8) goto L_0x034d;
    L_0x033a:
        r0 = r13.d;
        r1 = "var2";
        r2 = r13.c;
        r2 = r2.e();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x034d:
        r0 = "Mandatory param var2 is missing var2should be the card token, you get the card token when you store/fetch a card";
        r0 = r13.d(r0);
        return r0;
    L_0x0354:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0363;
    L_0x035c:
        r0 = "Mandatory param var1 is missing should be the user credentials and it should be merchant_key:unique_user_id.";
        r0 = r13.d(r0);
        return r0;
    L_0x0363:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.c;
        r0 = r0.e();
        if (r0 == 0) goto L_0x039b;
    L_0x037c:
        r0 = r13.c;
        r0 = r0.e();
        r0 = r0.length();
        if (r0 <= r8) goto L_0x039b;
    L_0x0388:
        r0 = r13.d;
        r1 = "var2";
        r2 = r13.c;
        r2 = r2.e();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x039b:
        r0 = "Mandatory param var2 is missing var2should be the card token, you get the card token when you store/fetch a card";
        r0 = r13.d(r0);
        return r0;
    L_0x03a2:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x03b1;
    L_0x03aa:
        r0 = "Mandatory param var1 is missing var1should be the user credentials and it should be merchant_key:unique_user_id.";
        r0 = r13.d(r0);
        return r0;
    L_0x03b1:
        r0 = r13.d;
        r2 = "var1";
        r3 = r13.c;
        r3 = r3.d();
        r2 = r13.b(r2, r3);
        r0.append(r2);
        r0 = r13.c;
        r0 = r0.e();
        if (r0 == 0) goto L_0x0501;
    L_0x03ca:
        r0 = r13.c;
        r0 = r0.e();
        r0 = r0.length();
        if (r0 >= r9) goto L_0x03d8;
    L_0x03d6:
        goto L_0x0501;
    L_0x03d8:
        r0 = r13.d;
        r2 = "var2";
        r3 = r13.c;
        r3 = r3.e();
        r2 = r13.b(r2, r3);
        r0.append(r2);
        r0 = r13.c;
        r0 = r0.f();
        if (r0 == 0) goto L_0x04fa;
    L_0x03f1:
        r0 = r13.c;
        r0 = r0.f();
        r0 = r0.length();
        if (r0 >= r9) goto L_0x03ff;
    L_0x03fd:
        goto L_0x04fa;
    L_0x03ff:
        r0 = r13.d;
        r2 = "var3";
        r3 = r13.c;
        r3 = r3.f();
        r2 = r13.b(r2, r3);
        r0.append(r2);
        r0 = r13.d;
        r2 = "var4";
        r3 = r13.c;
        r3 = r3.g();
        if (r3 != 0) goto L_0x041f;
    L_0x041c:
        r3 = "CC";
        goto L_0x0425;
    L_0x041f:
        r3 = r13.c;
        r3 = r3.g();
    L_0x0425:
        r2 = r13.b(r2, r3);
        r0.append(r2);
        r0 = r13.d;
        r2 = "var5";
        r3 = r13.c;
        r3 = r3.h();
        if (r3 != 0) goto L_0x043b;
    L_0x0438:
        r3 = "CC";
        goto L_0x0441;
    L_0x043b:
        r3 = r13.c;
        r3 = r3.h();
    L_0x0441:
        r2 = r13.b(r2, r3);
        r0.append(r2);
        r0 = r13.c;
        r0 = r0.i();
        if (r0 == 0) goto L_0x04f3;
    L_0x0450:
        r0 = r13.c;
        r0 = r0.i();
        r0 = r0.length();
        if (r0 >= r9) goto L_0x045e;
    L_0x045c:
        goto L_0x04f3;
    L_0x045e:
        r0 = r13.d;
        r2 = "var6";
        r3 = r13.c;
        r3 = r3.i();
        r2 = r13.b(r2, r3);
        r0.append(r2);
        r0 = r13.c;
        r0 = r0.j();
        if (r0 == 0) goto L_0x04ec;
    L_0x0477:
        r0 = r13.c;
        r0 = r0.j();
        r0 = r13.a(r0);
        r0 = r0.booleanValue();
        if (r0 != 0) goto L_0x0488;
    L_0x0487:
        goto L_0x04ec;
    L_0x0488:
        r0 = r13.d;
        r2 = "var7";
        r3 = r13.c;
        r3 = r3.j();
        r2 = r13.b(r2, r3);
        r0.append(r2);
        r0 = r13.c;	 Catch:{ NumberFormatException -> 0x04e5 }
        r0 = r0.k();	 Catch:{ NumberFormatException -> 0x04e5 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x04e5 }
        r2 = r13.c;	 Catch:{ NumberFormatException -> 0x04de }
        r2 = r2.l();	 Catch:{ NumberFormatException -> 0x04de }
        r2 = java.lang.Integer.parseInt(r2);	 Catch:{ NumberFormatException -> 0x04de }
        r0 = r13.a(r0, r2);
        if (r0 == 0) goto L_0x04d7;
    L_0x04b3:
        r0 = r13.d;
        r1 = "var8";
        r2 = r13.c;
        r2 = r2.k();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var9";
        r2 = r13.c;
        r2 = r2.l();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x04d7:
        r0 = " It seems the card is expired!";
        r0 = r13.a(r1, r0);
        return r0;
    L_0x04de:
        r0 = " Invalid year, year should be 4 digit YYYY format";
        r0 = r13.a(r10, r0);
        return r0;
    L_0x04e5:
        r0 = " Invalid month, it should be two digit number range from 01 to 12 MM format";
        r0 = r13.a(r10, r0);
        return r0;
    L_0x04ec:
        r0 = "Mandatory param var7 is missing var7 should be a valid credit / debit card number";
        r0 = r13.d(r0);
        return r0;
    L_0x04f3:
        r0 = "Mandatory param var6 is missing var6 should be name on card";
        r0 = r13.d(r0);
        return r0;
    L_0x04fa:
        r0 = "Mandatory param var3 is missing var3should be the card name (nickname of the card)";
        r0 = r13.d(r0);
        return r0;
    L_0x0501:
        r0 = "Mandatory param var2 is missing var2should be the card token, you get the card token when you store/fetch a card";
        r0 = r13.d(r0);
        return r0;
    L_0x0508:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0517;
    L_0x0510:
        r0 = "Mandatory param var1 is missing var1should be the user credentials and it should be merchant_key:unique_user_id.";
        r0 = r13.d(r0);
        return r0;
    L_0x0517:
        r0 = r13.d;
        r2 = "var1";
        r3 = r13.c;
        r3 = r3.d();
        r2 = r13.b(r2, r3);
        r0.append(r2);
        r0 = r13.c;
        r0 = r0.e();
        if (r0 == 0) goto L_0x0687;
    L_0x0530:
        r0 = r13.c;
        r0 = r0.e();
        r0 = r0.length();
        if (r0 >= r9) goto L_0x053e;
    L_0x053c:
        goto L_0x0687;
    L_0x053e:
        r0 = r13.d;
        r2 = "var2";
        r3 = r13.c;
        r3 = r3.e();
        r2 = r13.b(r2, r3);
        r0.append(r2);
        r0 = r13.c;
        r0 = r0.f();
        if (r0 == 0) goto L_0x0680;
    L_0x0557:
        r0 = r13.c;
        r0 = r0.f();
        r2 = "CC";
        r0 = r0.contentEquals(r2);
        if (r0 != 0) goto L_0x0567;
    L_0x0565:
        goto L_0x0680;
    L_0x0567:
        r0 = r13.d;
        r2 = "var3";
        r3 = r13.c;
        r3 = r3.f();
        r2 = r13.b(r2, r3);
        r0.append(r2);
        r0 = r13.c;
        r0 = r0.g();
        if (r0 == 0) goto L_0x0679;
    L_0x0580:
        r0 = r13.c;
        r0 = r0.g();
        r2 = "CC";
        r0 = r0.contentEquals(r2);
        if (r0 != 0) goto L_0x0590;
    L_0x058e:
        goto L_0x0679;
    L_0x0590:
        r0 = r13.d;
        r2 = "var4";
        r3 = r13.c;
        r3 = r3.g();
        r2 = r13.b(r2, r3);
        r0.append(r2);
        r0 = r13.c;
        r0 = r0.h();
        if (r0 == 0) goto L_0x0672;
    L_0x05a9:
        r0 = r13.c;
        r0 = r0.h();
        r0 = r0.length();
        if (r0 >= r9) goto L_0x05b7;
    L_0x05b5:
        goto L_0x0672;
    L_0x05b7:
        r0 = r13.d;
        r2 = "var5";
        r3 = r13.c;
        r3 = r3.h();
        r2 = r13.b(r2, r3);
        r0.append(r2);
        r0 = r13.c;
        r0 = r0.i();
        if (r0 == 0) goto L_0x066b;
    L_0x05d0:
        r0 = r13.c;
        r0 = r0.i();
        r0 = r13.a(r0);
        r0 = r0.booleanValue();
        if (r0 != 0) goto L_0x05e2;
    L_0x05e0:
        goto L_0x066b;
    L_0x05e2:
        r0 = r13.d;
        r2 = "var6";
        r3 = r13.c;
        r3 = r3.i();
        r2 = r13.b(r2, r3);
        r0.append(r2);
        r0 = r13.c;	 Catch:{ NumberFormatException -> 0x0664 }
        r0 = r0.j();	 Catch:{ NumberFormatException -> 0x0664 }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0664 }
        r2 = r13.c;	 Catch:{ NumberFormatException -> 0x065d }
        r2 = r2.k();	 Catch:{ NumberFormatException -> 0x065d }
        r2 = java.lang.Integer.parseInt(r2);	 Catch:{ NumberFormatException -> 0x065d }
        r0 = r13.a(r0, r2);
        if (r0 == 0) goto L_0x0656;
    L_0x060d:
        r0 = r13.d;
        r1 = "var7";
        r2 = r13.c;
        r2 = r2.j();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var8";
        r2 = r13.c;
        r2 = r2.k();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.c;
        r0 = r0.m();
        if (r0 == 0) goto L_0x0e3c;
    L_0x0637:
        r0 = r13.c;
        r0 = r0.m();
        r0 = r0.length();
        if (r0 <= 0) goto L_0x0e3c;
    L_0x0643:
        r0 = r13.d;
        r1 = "var10";
        r2 = r13.c;
        r2 = r2.m();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0656:
        r0 = " It seems the card is expired!";
        r0 = r13.a(r1, r0);
        return r0;
    L_0x065d:
        r0 = " Invalid year, year should be 4 digit YYYY format";
        r0 = r13.a(r10, r0);
        return r0;
    L_0x0664:
        r0 = " Invalid month, it should be two digit number range from 01 to 12 MM format";
        r0 = r13.a(r10, r0);
        return r0;
    L_0x066b:
        r0 = "Mandatory param var6 is missing var6 should be a valid credit / debit card number";
        r0 = r13.d(r0);
        return r0;
    L_0x0672:
        r0 = "Mandatory param var5 is missing var5 should be name on card";
        r0 = r13.d(r0);
        return r0;
    L_0x0679:
        r0 = "Mandatory param var4 is missing var4should be the card type; please use CC as card type";
        r0 = r13.d(r0);
        return r0;
    L_0x0680:
        r0 = "Mandatory param var3 is missing var3should be the card mode; please use CC as card mode";
        r0 = r13.d(r0);
        return r0;
    L_0x0687:
        r0 = "Mandatory param var2 is missing var2should be the card name (nickname of the card)";
        r0 = r13.d(r0);
        return r0;
    L_0x068e:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x069d;
    L_0x0696:
        r0 = "Mandatory param var1 is missing var1should be the user credentials and it should be merchant_key:unique_user_id.";
        r0 = r13.d(r0);
        return r0;
    L_0x069d:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x06b0:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 == 0) goto L_0x06d8;
    L_0x06b8:
        r0 = r13.c;
        r0 = r0.d();
        r0 = r0.length();
        if (r0 >= r6) goto L_0x06c5;
    L_0x06c4:
        goto L_0x06d8;
    L_0x06c5:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x06d8:
        r0 = "Mandatory param var1 is missing var1number or the card bin (first 6 digit of the card)";
        r0 = r13.d(r0);
        return r0;
    L_0x06df:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x06ee;
    L_0x06e7:
        r0 = "Mandatory param var1 is missing var1should be the from date in YYYY-MM-DD hh:mm:ss format";
        r0 = r13.d(r0);
        return r0;
    L_0x06ee:
        r0 = r13.c;
        r0 = r0.e();
        if (r0 != 0) goto L_0x06fd;
    L_0x06f6:
        r0 = "Mandatory param var2 is missing var2should be the till date in YYYY-MM-DD hh:mm:ss format.";
        r0 = r13.d(r0);
        return r0;
    L_0x06fd:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var2";
        r2 = r13.c;
        r2 = r2.e();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0721:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0730;
    L_0x0729:
        r0 = "Mandatory param var1 is missing var1should be the from date in YYYY-MM-DD format";
        r0 = r13.d(r0);
        return r0;
    L_0x0730:
        r0 = r13.c;
        r0 = r0.e();
        if (r0 != 0) goto L_0x073f;
    L_0x0738:
        r0 = "Mandatory param var2 is missing var2till date in YYYY-MM-DD format.";
        r0 = r13.d(r0);
        return r0;
    L_0x073f:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var2";
        r2 = r13.c;
        r2 = r2.e();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0763:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 == 0) goto L_0x078b;
    L_0x076b:
        r0 = r13.c;
        r0 = r0.d();
        r0 = r0.length();
        if (r0 == r6) goto L_0x0778;
    L_0x0777:
        goto L_0x078b;
    L_0x0778:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x078b:
        r0 = "Mandatory param var1 is missing var1number or the card bin (first 6 digit of the card)";
        r0 = r13.d(r0);
        return r0;
    L_0x0792:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 == 0) goto L_0x07ba;
    L_0x079a:
        r0 = r13.c;
        r0 = r0.d();
        r0 = r0.length();
        if (r0 >= r9) goto L_0x07a7;
    L_0x07a6:
        goto L_0x07ba;
    L_0x07a7:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x07ba:
        r0 = "Mandatory param var1 is missing var1should be the bank code for one bank, default for getting all banks";
        r0 = r13.d(r0);
        return r0;
    L_0x07c1:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x07d0;
    L_0x07c9:
        r0 = "Mandatory param var1 is missing var1 should be offer key example : offer@1234 ";
        r0 = r13.d(r0);
        return r0;
    L_0x07d0:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var2";
        r2 = r13.c;
        r2 = r2.e();
        if (r2 != 0) goto L_0x07f0;
    L_0x07ed:
        r2 = "";
        goto L_0x07f6;
    L_0x07f0:
        r2 = r13.c;
        r2 = r2.e();
    L_0x07f6:
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var3";
        r2 = r13.c;
        r2 = r2.f();
        if (r2 != 0) goto L_0x080c;
    L_0x0809:
        r2 = "";
        goto L_0x0812;
    L_0x080c:
        r2 = r13.c;
        r2 = r2.f();
    L_0x0812:
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var4";
        r2 = r13.c;
        r2 = r2.g();
        if (r2 != 0) goto L_0x0828;
    L_0x0825:
        r2 = "";
        goto L_0x082e;
    L_0x0828:
        r2 = r13.c;
        r2 = r2.g();
    L_0x082e:
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var5";
        r2 = r13.c;
        r2 = r2.h();
        if (r2 != 0) goto L_0x0844;
    L_0x0841:
        r2 = "";
        goto L_0x084a;
    L_0x0844:
        r2 = r13.c;
        r2 = r2.h();
    L_0x084a:
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var6";
        r2 = r13.c;
        r2 = r2.i();
        if (r2 != 0) goto L_0x0860;
    L_0x085d:
        r2 = "";
        goto L_0x0866;
    L_0x0860:
        r2 = r13.c;
        r2 = r2.i();
    L_0x0866:
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var7";
        r2 = r13.c;
        r2 = r2.j();
        if (r2 != 0) goto L_0x087c;
    L_0x0879:
        r2 = "";
        goto L_0x0882;
    L_0x087c:
        r2 = r13.c;
        r2 = r2.j();
    L_0x0882:
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var8";
        r2 = r13.c;
        r2 = r2.k();
        if (r2 != 0) goto L_0x0898;
    L_0x0895:
        r2 = "";
        goto L_0x089e;
    L_0x0898:
        r2 = r13.c;
        r2 = r2.k();
    L_0x089e:
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var9";
        r2 = r13.c;
        r2 = r2.l();
        if (r2 != 0) goto L_0x08b4;
    L_0x08b1:
        r2 = "";
        goto L_0x08ba;
    L_0x08b4:
        r2 = r13.c;
        r2 = r2.l();
    L_0x08ba:
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var10";
        r2 = r13.c;
        r2 = r2.m();
        if (r2 != 0) goto L_0x08d0;
    L_0x08cd:
        r2 = "";
        goto L_0x08d6;
    L_0x08d0:
        r2 = r13.c;
        r2 = r2.m();
    L_0x08d6:
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var11";
        r2 = r13.c;
        r2 = r2.n();
        if (r2 != 0) goto L_0x08ec;
    L_0x08e9:
        r2 = "";
        goto L_0x08f2;
    L_0x08ec:
        r2 = r13.c;
        r2 = r2.n();
    L_0x08f2:
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x08fb:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x090a;
    L_0x0903:
        r0 = "Mandatory param var1 is missing var1should be the Transaction id (txnid)";
        r0 = r13.d(r0);
        return r0;
    L_0x090a:
        r0 = r13.d;
        r1 = "var1";
        r10 = r13.c;
        r10 = r10.d();
        r1 = r13.b(r1, r10);
        r0.append(r1);
        r0 = r13.d;
        r1 = r13.c;
        r1 = r1.e();
        if (r1 != 0) goto L_0x0928;
    L_0x0925:
        r1 = "";
        goto L_0x0934;
    L_0x0928:
        r1 = "var2";
        r10 = r13.c;
        r10 = r10.e();
        r1 = r13.b(r1, r10);
    L_0x0934:
        r0.append(r1);
        r0 = r13.d;
        r1 = r13.c;
        r1 = r1.f();
        if (r1 != 0) goto L_0x0944;
    L_0x0941:
        r1 = "";
        goto L_0x0950;
    L_0x0944:
        r1 = "var3";
        r10 = r13.c;
        r10 = r10.f();
        r1 = r13.b(r1, r10);
    L_0x0950:
        r0.append(r1);
        r0 = r13.d;
        r1 = r13.c;
        r1 = r1.g();
        if (r1 != 0) goto L_0x0960;
    L_0x095d:
        r1 = "";
        goto L_0x096c;
    L_0x0960:
        r1 = "var4";
        r10 = r13.c;
        r10 = r10.g();
        r1 = r13.b(r1, r10);
    L_0x096c:
        r0.append(r1);
        r0 = r13.d;
        r1 = r13.c;
        r1 = r1.h();
        if (r1 != 0) goto L_0x097c;
    L_0x0979:
        r1 = "";
        goto L_0x0988;
    L_0x097c:
        r1 = "var5";
        r10 = r13.c;
        r10 = r10.h();
        r1 = r13.b(r1, r10);
    L_0x0988:
        r0.append(r1);
        r0 = r13.d;
        r1 = r13.c;
        r1 = r1.i();
        if (r1 != 0) goto L_0x0998;
    L_0x0995:
        r1 = "";
        goto L_0x09a4;
    L_0x0998:
        r1 = "var6";
        r10 = r13.c;
        r10 = r10.i();
        r1 = r13.b(r1, r10);
    L_0x09a4:
        r0.append(r1);
    L_0x09a7:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x09b6;
    L_0x09af:
        r0 = "Mandatory param var1 is missing";
        r0 = r13.d(r0);
        return r0;
    L_0x09b6:
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0a7e }
        r1 = r13.c;	 Catch:{ JSONException -> 0x0a7e }
        r1 = r1.d();	 Catch:{ JSONException -> 0x0a7e }
        r0.<init>(r1);	 Catch:{ JSONException -> 0x0a7e }
        r1 = new java.lang.String[r6];	 Catch:{ JSONException -> 0x0a7e }
        r6 = "amount";
        r1[r7] = r6;	 Catch:{ JSONException -> 0x0a7e }
        r6 = "txnid";
        r1[r9] = r6;	 Catch:{ JSONException -> 0x0a7e }
        r6 = "productinfo";
        r1[r8] = r6;	 Catch:{ JSONException -> 0x0a7e }
        r6 = "firstname";
        r1[r5] = r6;	 Catch:{ JSONException -> 0x0a7e }
        r6 = "email";
        r1[r3] = r6;	 Catch:{ JSONException -> 0x0a7e }
        r6 = "phone";
        r1[r4] = r6;	 Catch:{ JSONException -> 0x0a7e }
        r6 = r1.length;	 Catch:{ JSONException -> 0x0a7e }
        r10 = r7;
    L_0x09dd:
        if (r10 >= r6) goto L_0x0a6b;
    L_0x09df:
        r11 = r1[r10];	 Catch:{ JSONException -> 0x0a7e }
        r12 = r0.getString(r11);	 Catch:{ JSONException -> 0x0a7e }
        if (r12 == 0) goto L_0x0a50;
    L_0x09e7:
        r12 = r0.getString(r11);	 Catch:{ JSONException -> 0x0a7e }
        r12 = r12.length();	 Catch:{ JSONException -> 0x0a7e }
        if (r12 >= r9) goto L_0x09f3;
    L_0x09f1:
        goto L_0x0a50;
    L_0x09f3:
        r12 = r11.hashCode();	 Catch:{ JSONException -> 0x0a7e }
        switch(r12) {
            case -1491000803: goto L_0x0a2d;
            case -1413853096: goto L_0x0a23;
            case 96619420: goto L_0x0a19;
            case 106642798: goto L_0x0a0f;
            case 110812421: goto L_0x0a05;
            case 133788987: goto L_0x09fb;
            default: goto L_0x09fa;
        };	 Catch:{ JSONException -> 0x0a7e }
    L_0x09fa:
        goto L_0x0a37;
    L_0x09fb:
        r12 = "firstname";
        r12 = r11.equals(r12);	 Catch:{ JSONException -> 0x0a7e }
        if (r12 == 0) goto L_0x0a37;
    L_0x0a03:
        r12 = r5;
        goto L_0x0a38;
    L_0x0a05:
        r12 = "txnid";
        r12 = r11.equals(r12);	 Catch:{ JSONException -> 0x0a7e }
        if (r12 == 0) goto L_0x0a37;
    L_0x0a0d:
        r12 = r9;
        goto L_0x0a38;
    L_0x0a0f:
        r12 = "phone";
        r12 = r11.equals(r12);	 Catch:{ JSONException -> 0x0a7e }
        if (r12 == 0) goto L_0x0a37;
    L_0x0a17:
        r12 = r4;
        goto L_0x0a38;
    L_0x0a19:
        r12 = "email";
        r12 = r11.equals(r12);	 Catch:{ JSONException -> 0x0a7e }
        if (r12 == 0) goto L_0x0a37;
    L_0x0a21:
        r12 = r3;
        goto L_0x0a38;
    L_0x0a23:
        r12 = "amount";
        r12 = r11.equals(r12);	 Catch:{ JSONException -> 0x0a7e }
        if (r12 == 0) goto L_0x0a37;
    L_0x0a2b:
        r12 = r7;
        goto L_0x0a38;
    L_0x0a2d:
        r12 = "productinfo";
        r12 = r11.equals(r12);	 Catch:{ JSONException -> 0x0a7e }
        if (r12 == 0) goto L_0x0a37;
    L_0x0a35:
        r12 = r8;
        goto L_0x0a38;
    L_0x0a37:
        r12 = r2;
    L_0x0a38:
        switch(r12) {
            case 0: goto L_0x0a3c;
            case 1: goto L_0x0a4d;
            case 2: goto L_0x0a4d;
            case 3: goto L_0x0a4d;
            case 4: goto L_0x0a4d;
            default: goto L_0x0a3b;
        };
    L_0x0a3b:
        goto L_0x0a4d;
    L_0x0a3c:
        r11 = r0.getString(r11);	 Catch:{ NumberFormatException -> 0x0a44 }
        java.lang.Double.parseDouble(r11);	 Catch:{ NumberFormatException -> 0x0a44 }
        goto L_0x0a4d;
    L_0x0a44:
        r0 = 5003; // 0x138b float:7.01E-42 double:2.472E-320;
        r1 = " Amount should be a Double value example 5.00";
        r0 = r13.a(r0, r1);	 Catch:{ JSONException -> 0x0a7e }
        return r0;
    L_0x0a4d:
        r10 = r10 + 1;
        goto L_0x09dd;
    L_0x0a50:
        r0 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0a7e }
        r0.<init>();	 Catch:{ JSONException -> 0x0a7e }
        r1 = "Mandatory param ";
        r0.append(r1);	 Catch:{ JSONException -> 0x0a7e }
        r0.append(r11);	 Catch:{ JSONException -> 0x0a7e }
        r1 = " is missing for creating an Invoice";
        r0.append(r1);	 Catch:{ JSONException -> 0x0a7e }
        r0 = r0.toString();	 Catch:{ JSONException -> 0x0a7e }
        r0 = r13.d(r0);	 Catch:{ JSONException -> 0x0a7e }
        return r0;
    L_0x0a6b:
        r0 = r13.d;	 Catch:{ JSONException -> 0x0a7e }
        r1 = "var1";
        r2 = r13.c;	 Catch:{ JSONException -> 0x0a7e }
        r2 = r2.d();	 Catch:{ JSONException -> 0x0a7e }
        r1 = r13.b(r1, r2);	 Catch:{ JSONException -> 0x0a7e }
        r0.append(r1);	 Catch:{ JSONException -> 0x0a7e }
        goto L_0x0e3c;
    L_0x0a7e:
        r0 = 5014; // 0x1396 float:7.026E-42 double:2.477E-320;
        r1 = "var1  should be a stringified JSON object; It seems there is an exception while parsing JSON";
        r0 = r13.a(r0, r1);
        return r0;
    L_0x0a87:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0a96;
    L_0x0a8f:
        r0 = "Mandatory param var1 is missing var1should be the Payu id (mihpayid) of the transaction which was given by payu.";
        r0 = r13.d(r0);
        return r0;
    L_0x0a96:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0aa9:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0ab8;
    L_0x0ab1:
        r0 = "Mandatory param var1 is missing var1should be the Payu id (mihpayid) of the transaction which was given by payu.";
        r0 = r13.d(r0);
        return r0;
    L_0x0ab8:
        r0 = r13.c;
        r0 = r0.e();
        if (r0 != 0) goto L_0x0ac7;
    L_0x0ac0:
        r0 = "Mandatory param var2 is missing var2should be the Token ID(unique token from merchant)";
        r0 = r13.d(r0);
        return r0;
    L_0x0ac7:
        r0 = r13.c;
        r0 = r0.f();
        if (r0 != 0) goto L_0x0ad6;
    L_0x0acf:
        r0 = "5001 var3 Amount should be a Double value example 5.00";
        r0 = r13.d(r0);
        return r0;
    L_0x0ad6:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var2";
        r2 = r13.c;
        r2 = r2.e();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var3";
        r2 = r13.c;
        r2 = r2.f();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0b0b:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0b1a;
    L_0x0b13:
        r0 = "Mandatory param var1 is missing var1should be the Payu id (mihpayid) of the transaction which was given by payu.";
        r0 = r13.d(r0);
        return r0;
    L_0x0b1a:
        r0 = r13.c;
        r0 = r0.e();
        if (r0 != 0) goto L_0x0b29;
    L_0x0b22:
        r0 = "Mandatory param var2 is missing var2should be the Token ID(unique token from merchant)";
        r0 = r13.d(r0);
        return r0;
    L_0x0b29:
        r0 = r13.c;
        r0 = r0.f();
        if (r0 != 0) goto L_0x0b38;
    L_0x0b31:
        r0 = "Mandatory param var3 is missing var3 Amount should be a Double value example 5.00";
        r0 = r13.d(r0);
        return r0;
    L_0x0b38:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var2";
        r2 = r13.c;
        r2 = r2.e();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var3";
        r2 = r13.c;
        r2 = r2.f();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0b6d:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0b7c;
    L_0x0b75:
        r0 = "Mandatory param var1 is missing var1should be the Payu id (mihpayid) of the transaction which was given by payu.";
        r0 = r13.d(r0);
        return r0;
    L_0x0b7c:
        r0 = r13.c;
        r0 = r0.e();
        if (r0 != 0) goto L_0x0b8b;
    L_0x0b84:
        r0 = "Mandatory param var2 is missing var2should be the Token ID(unique token from merchant)";
        r0 = r13.d(r0);
        return r0;
    L_0x0b8b:
        r0 = r13.c;
        r0 = r0.f();
        if (r0 != 0) goto L_0x0b9a;
    L_0x0b93:
        r0 = "Mandatory param var3 is missing var3 Amount should be a Double value example 5.00";
        r0 = r13.d(r0);
        return r0;
    L_0x0b9a:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var2";
        r2 = r13.c;
        r2 = r2.e();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var3";
        r2 = r13.c;
        r2 = r2.f();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0bcf:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0bde;
    L_0x0bd7:
        r0 = "Mandatory param var1 is missing var1should be the Payu id (mihpayid) of the transaction which was given by payu.";
        r0 = r13.d(r0);
        return r0;
    L_0x0bde:
        r0 = r13.c;
        r0 = r0.e();
        if (r0 != 0) goto L_0x0bed;
    L_0x0be6:
        r0 = "Mandatory param var2 is missing var2should be the Request ID which you get while cancel_refund_transaction api";
        r0 = r13.d(r0);
        return r0;
    L_0x0bed:
        r0 = r13.c;
        r0 = r0.f();
        if (r0 != 0) goto L_0x0bfc;
    L_0x0bf5:
        r0 = "Mandatory param var3 is missing var3should be the Bank Ref Id for the requested transaction.";
        r0 = r13.d(r0);
        return r0;
    L_0x0bfc:
        r0 = r13.c;
        r0 = r0.g();
        if (r0 != 0) goto L_0x0c0b;
    L_0x0c04:
        r0 = "Mandatory param var4 is missing var4 Amount should be a Double value example 5.00";
        r0 = r13.d(r0);
        return r0;
    L_0x0c0b:
        r0 = r13.c;
        r0 = r0.h();
        if (r0 != 0) goto L_0x0c1a;
    L_0x0c13:
        r0 = "Mandatory param var5 is missing var5should be the Action (cancel/capture/refund)";
        r0 = r13.d(r0);
        return r0;
    L_0x0c1a:
        r0 = r13.c;
        r0 = r0.i();
        if (r0 != 0) goto L_0x0c29;
    L_0x0c22:
        r0 = "Mandatory param var6 is missing var6 should be new status to be set";
        r0 = r13.d(r0);
        return r0;
    L_0x0c29:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var2";
        r2 = r13.c;
        r2 = r2.e();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var3";
        r2 = r13.c;
        r2 = r2.f();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var4";
        r2 = r13.c;
        r2 = r2.g();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var5";
        r2 = r13.c;
        r2 = r2.h();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var6";
        r2 = r13.c;
        r2 = r2.i();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0c91:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0ca0;
    L_0x0c99:
        r0 = "Mandatory param var1 is missing var1should be the Payu id (mihpayid) of the transaction which was given by payu.";
        r0 = r13.d(r0);
        return r0;
    L_0x0ca0:
        r0 = r13.c;
        r0 = r0.e();
        if (r0 != 0) goto L_0x0caf;
    L_0x0ca8:
        r0 = "Mandatory param var2 is missing should be the Token ID(unique token from merchant)";
        r0 = r13.d(r0);
        return r0;
    L_0x0caf:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var2";
        r2 = r13.c;
        r2 = r2.e();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0cd3:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0ce2;
    L_0x0cdb:
        r0 = "Mandatory param var1 is missing var1should be the Request ID which you get while cancel_refund_transaction api";
        r0 = r13.d(r0);
        return r0;
    L_0x0ce2:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0cf5:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0d04;
    L_0x0cfd:
        r0 = "Mandatory param var1 is missing var1should be the Payu id (mihpayid) of the transaction which was given by payu.";
        r0 = r13.d(r0);
        return r0;
    L_0x0d04:
        r0 = r13.c;
        r0 = r0.e();
        if (r0 != 0) goto L_0x0d13;
    L_0x0d0c:
        r0 = "Mandatory param var2 is missing var2should be the Token ID(unique token from merchant)";
        r0 = r13.d(r0);
        return r0;
    L_0x0d13:
        r0 = r13.c;
        r0 = r0.f();
        if (r0 != 0) goto L_0x0d22;
    L_0x0d1b:
        r0 = "Mandatory param var3 is missing var3  should contain the amount which needs to be refunded. Please note that both partial and full refunds are allowed.";
        r0 = r13.d(r0);
        return r0;
    L_0x0d22:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var2";
        r2 = r13.c;
        r2 = r2.e();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = "var3";
        r2 = r13.c;
        r2 = r2.f();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0d57:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0d66;
    L_0x0d5f:
        r0 = "Mandatory param var1 is missing var1should be the Payu id (mihpayid) of the transaction which was given by payu.";
        r0 = r13.d(r0);
        return r0;
    L_0x0d66:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0d79:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0d88;
    L_0x0d81:
        r0 = "Mandatory param var1 is missing var1should be the Transaction id (txnid)if you want to verify more than one transaction please separate them by pipe : ex 6234567|45678987|4567876 ";
        r0 = r13.d(r0);
        return r0;
    L_0x0d88:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0d9b:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0daa;
    L_0x0da3:
        r0 = "Mandatory param var1 is missing var1 Please send var1 as 'default'";
        r0 = r13.d(r0);
        return r0;
    L_0x0daa:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0dbd:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0dcc;
    L_0x0dc5:
        r0 = "Mandatory param var1 is missing var1 Please send var1 as 'default'";
        r0 = r13.d(r0);
        return r0;
    L_0x0dcc:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
        r0 = r13.d;
        r1 = r13.c;
        r1 = r1.e();
        if (r1 != 0) goto L_0x0df0;
    L_0x0de7:
        r1 = "var2";
        r2 = "default";
    L_0x0deb:
        r1 = r13.b(r1, r2);
        goto L_0x0df9;
    L_0x0df0:
        r1 = "var2";
        r2 = r13.c;
        r2 = r2.e();
        goto L_0x0deb;
    L_0x0df9:
        r0.append(r1);
        r0 = r13.d;
        r1 = r13.c;
        r1 = r1.e();
        if (r1 != 0) goto L_0x0e0f;
    L_0x0e06:
        r1 = "var3";
        r2 = "default";
    L_0x0e0a:
        r1 = r13.b(r1, r2);
        goto L_0x0e18;
    L_0x0e0f:
        r1 = "var3";
        r2 = r13.c;
        r2 = r2.f();
        goto L_0x0e0a;
    L_0x0e18:
        r0.append(r1);
        goto L_0x0e3c;
    L_0x0e1c:
        r0 = r13.c;
        r0 = r0.d();
        if (r0 != 0) goto L_0x0e2b;
    L_0x0e24:
        r0 = "Mandatory param var1 is missing var1 should be user_credentials (merchant_key:unique_user_id.) to get the merchant information and stored card or default to get only the merchant information";
        r0 = r13.d(r0);
        return r0;
    L_0x0e2b:
        r0 = r13.d;
        r1 = "var1";
        r2 = r13.c;
        r2 = r2.d();
        r1 = r13.b(r1, r2);
        r0.append(r1);
    L_0x0e3c:
        r0 = "SUCCESS";
        r1 = r13.d;
        r1 = r1.toString();
        r1 = r13.e(r1);
        r0 = r13.a(r7, r0, r1);
        return r0;
    L_0x0e4d:
        r0 = "Mandatory param command is missing";
        r0 = r13.d(r0);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.payu.india.d.a.c():com.payu.india.Model.PostData");
    }
}
