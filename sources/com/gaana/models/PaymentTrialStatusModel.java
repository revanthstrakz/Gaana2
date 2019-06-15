package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class PaymentTrialStatusModel extends BusinessObject {
    private static final long serialVersionUID = 1;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private String result;
    @SerializedName("status")
    private int status;

    public int getStatus() {
        return this.status;
    }

    public String getResult() {
        return this.result;
    }

    public String getMessage() {
        return this.message;
    }
}
