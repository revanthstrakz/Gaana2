package com.payu.india.c;

import com.payu.custombrowser.util.CBConstant;
import java.util.HashSet;
import java.util.Set;

public interface b {
    public static final Set<String> a = new HashSet();
    public static final Set<String> b = new HashSet();
    public static final String[] c = new String[]{"CC", "EMI", "CASH", "NB", "PAYU_MONEY"};
    public static final String[] d = new String[]{CBConstant.KEY, CBConstant.TXN_ID, "amount", "productinfo", "firstname", "email", CBConstant.SURL, CBConstant.FURL, CBConstant.HASH, "udf1", "udf2", "udf3", "udf4", "udf5"};
    public static final a e = new a();

    public static class a {
        static {
            b.a.add("CC");
            b.a.add("EMI");
            b.a.add("CASH");
            b.a.add("NB");
            b.a.add("PAYU_MONEY");
            b.a.add("upi");
            b.a.add("TEZ");
            b.b.add("verify_payment");
            b.b.add("check_payment");
            b.b.add("cancel_refund_transaction");
            b.b.add("check_action_status");
            b.b.add("capture_transaction");
            b.b.add("update_requests");
            b.b.add("cod_verify");
            b.b.add("cod_cancel");
            b.b.add("cod_settled");
            b.b.add("get_TDR");
            b.b.add("udf_update");
            b.b.add("create_invoice");
            b.b.add("check_offer_status");
            b.b.add("getNetbankingStatus");
            b.b.add("getIssuingBankStatus");
            b.b.add("get_Transaction_Details");
            b.b.add("get_transaction_info");
            b.b.add("check_isDomestic");
            b.b.add("get_user_cards");
            b.b.add("save_user_card");
            b.b.add("edit_user_card");
            b.b.add("delete_user_card");
            b.b.add("get_merchant_ibibo_codes");
            b.b.add("vas_for_mobile_sdk");
            b.b.add(CBConstant.PAYMENT_RELATED_DETAILS_FOR_MOBILE_SDK);
            b.b.add("mobileHashTestWs");
            b.b.add("get_hashes");
            b.b.add("check_offer_details");
            b.b.add("getEmiAmountAccordingToInterest");
            b.b.add("merchant_cvv_data");
            b.b.add("delete_store_card_cvv");
        }

        a() {
        }
    }
}
