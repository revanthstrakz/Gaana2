package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class PollData extends BusinessObject {
    @SerializedName("match_end")
    private String match_end;
    @SerializedName("message")
    private String message;
    @SerializedName("poll_time")
    private String poll_time;
    @SerializedName("status")
    private String status;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getMatch_end() {
        return this.match_end;
    }

    public void setMatch_end(String str) {
        this.match_end = str;
    }

    public String getPoll_time() {
        return this.poll_time;
    }

    public void setPoll_time(String str) {
        this.poll_time = str;
    }
}
