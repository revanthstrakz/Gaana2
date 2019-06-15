package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class SimplEligibility extends BusinessObject {
    @SerializedName("amount_in_rs")
    private int amount_in_rs;
    @SerializedName("due_by")
    private String due_by;
    @SerializedName("is_simpl_eligible")
    private int is_simpl_eligible;
    @SerializedName("message")
    private String message;
    @SerializedName("redirection_url")
    private String redirection_url;
    @SerializedName("status")
    private int status;

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public int getIsSimplEligible() {
        return this.is_simpl_eligible;
    }

    public int getAmount() {
        return this.amount_in_rs;
    }

    public String getRedirectionUrl() {
        return this.redirection_url;
    }
}
