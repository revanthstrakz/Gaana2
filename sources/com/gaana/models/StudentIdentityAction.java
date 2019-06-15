package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class StudentIdentityAction extends BusinessObject implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("verified")
    public int verified;
    @SerializedName("webview_url")
    public String webview_url;

    public int getVerified() {
        return this.verified;
    }

    public void setVerified(int i) {
        this.verified = i;
    }

    public String getWebview_url() {
        return this.webview_url;
    }

    public void setWebview_url(String str) {
        this.webview_url = str;
    }
}
