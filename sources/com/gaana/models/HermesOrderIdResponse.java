package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class HermesOrderIdResponse implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("error_message")
    private String error_message;
    @SerializedName("iframe_title")
    private String iframe_title;
    @SerializedName("iframe_url")
    private String iframe_url;
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private String result;
    @SerializedName("status")
    private String status;
    @SerializedName("uts")
    private String user_token_status;

    public String getErrorMessage() {
        return this.error_message;
    }

    public String getResult() {
        return this.result;
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public String getIFrameUrl() {
        return this.iframe_url;
    }

    public String getIFrameTitle() {
        return this.iframe_title;
    }

    public String getUserTokenStatus() {
        return this.user_token_status;
    }
}
