package com.models;

import com.gaana.models.BusinessObject;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class BankCodeList extends BusinessObject {
    @SerializedName("payu_nb_codes")
    private ArrayList<BankCode> a;
    @SerializedName("payu_popular")
    private ArrayList<PopularBankCode> b;

    public static class BankCode extends BusinessObject {
        @SerializedName("bank_code")
        private String a;
        @SerializedName("bank_name")
        private String b;

        public void a(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }

        public void b(String str) {
            this.b = str;
        }

        public String b() {
            return this.b;
        }
    }

    public static class PopularBankCode extends BusinessObject {
        @SerializedName("bank_code")
        private String a;
        @SerializedName("bank_name")
        private String b;
        @SerializedName("bank_image")
        private String c;

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }
    }

    public ArrayList<BankCode> a() {
        return this.a;
    }

    public ArrayList<PopularBankCode> b() {
        return this.b;
    }
}
