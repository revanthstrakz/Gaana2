package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class PayUHash extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("message")
    private String message;
    @SerializedName("order_dtl")
    private OrderDetails orderDetail;
    @SerializedName("si_enabled_banks")
    ArrayList<SiEnabledBankName> si_enabled_banks;
    @SerializedName("Status")
    private String status;

    public class SiEnabledBankName implements Serializable {
        private static final long serialVersionUID = 1;
        @SerializedName("bank_name")
        public String bank_name;

        public String getBank_name() {
            return this.bank_name;
        }

        public void setBank_name(String str) {
            this.bank_name = str;
        }
    }

    public static class OrderDetails extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("amount")
        private String amount;
        @SerializedName("curl")
        private String curl;
        @SerializedName("delete_hash")
        private String delete_hash;
        @SerializedName("email")
        private String email;
        @SerializedName("firstname")
        private String firstname;
        @SerializedName("furl")
        private String furl;
        @SerializedName("hash")
        private String hash;
        @SerializedName("key")
        private String key;
        @SerializedName("offer_key")
        private String offerKey;
        @SerializedName("productInfo")
        private String productInfo;
        @SerializedName("si")
        private int si;
        @SerializedName("si_enabled_banks")
        ArrayList<SiEnabledBankName> si_enabled_banks;
        @SerializedName("store_card")
        private int store_card;
        @SerializedName("store_hash")
        private String store_hash;
        @SerializedName("surl")
        private String surl;
        @SerializedName("txnid")
        private String txnid;
        @SerializedName("user_credentials")
        private String user_credentials;

        public String getKey() {
            return this.key;
        }

        public void setKey(String str) {
            this.key = str;
        }

        public String getTxnid() {
            return this.txnid;
        }

        public void setTxnid(String str) {
            this.txnid = str;
        }

        public String getAmount() {
            return this.amount;
        }

        public void setAmount(String str) {
            this.amount = str;
        }

        public String getProductInfo() {
            return this.productInfo;
        }

        public void setProductInfo(String str) {
            this.productInfo = str;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String str) {
            this.email = str;
        }

        public String getFirstname() {
            return this.firstname;
        }

        public void setFirstname(String str) {
            this.firstname = str;
        }

        public String getHash() {
            return this.hash;
        }

        public void setHash(String str) {
            this.hash = str;
        }

        public String getSurl() {
            return this.surl;
        }

        public void setSurl(String str) {
            this.surl = str;
        }

        public String getFurl() {
            return this.furl;
        }

        public void setFurl(String str) {
            this.furl = str;
        }

        public String getCurl() {
            return this.curl;
        }

        public void setCurl(String str) {
            this.curl = str;
        }

        public String getUser_credentials() {
            return this.user_credentials;
        }

        public void setUser_credentials(String str) {
            this.user_credentials = str;
        }

        public int getStore_card() {
            return this.store_card;
        }

        public void setStore_card(int i) {
            this.store_card = i;
        }

        public String getStore_hash() {
            return this.store_hash;
        }

        public void setStore_hash(String str) {
            this.store_hash = str;
        }

        public int getSi() {
            return this.si;
        }

        public void setSi(int i) {
            this.si = i;
        }

        public String getDelete_hash() {
            return this.delete_hash;
        }

        public void setDelete_hash(String str) {
            this.delete_hash = str;
        }

        public ArrayList<SiEnabledBankName> getSi_enabled_banks() {
            return this.si_enabled_banks;
        }

        public void setSi_enabled_banks(ArrayList<SiEnabledBankName> arrayList) {
            this.si_enabled_banks = arrayList;
        }

        public String getOfferKey() {
            return this.offerKey;
        }
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public OrderDetails getOrderDetail() {
        return this.orderDetail;
    }

    public ArrayList<SiEnabledBankName> getSIEnabledBanks() {
        return this.si_enabled_banks;
    }
}
