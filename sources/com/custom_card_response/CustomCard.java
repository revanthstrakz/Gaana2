package com.custom_card_response;

import com.gaana.models.BusinessObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomCard extends BusinessObject {
    @SerializedName("rulesConfiguration")
    @Expose
    private e a;
    @SerializedName("cardDetails")
    @Expose
    private a b;

    public e a() {
        return this.a;
    }

    public a b() {
        return this.b;
    }
}
