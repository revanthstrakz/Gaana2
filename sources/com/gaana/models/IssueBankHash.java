package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class IssueBankHash extends BusinessObject implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("card_hash")
    public String card_hash;

    public String getCard_hash() {
        return this.card_hash;
    }

    public void setCard_hash(String str) {
        this.card_hash = str;
    }
}
