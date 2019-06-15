package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class UpdatePaymentResponse implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private String result;
    @SerializedName("status")
    private String status;
    @SerializedName("validupto")
    private String validUpTo;

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public String getResult() {
        return this.result;
    }

    public String getValidUpTo() {
        return this.validUpTo;
    }
}
