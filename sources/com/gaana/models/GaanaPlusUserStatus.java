package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class GaanaPlusUserStatus extends BusinessObject implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("is_gaana_plus_allow")
    private String is_gaana_plus_allow;
    @SerializedName("is_gaana_plus_user")
    private String is_gaana_plus_user;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public String getIsGaanaPlusUser() {
        return this.is_gaana_plus_user;
    }

    public String getIsGaanaPlusAllow() {
        return this.is_gaana_plus_allow;
    }
}
