package com.custom_card_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class d {
    @SerializedName("on_app_launch")
    @Expose
    private Integer a;
    @SerializedName("first_song_play")
    @Expose
    private Integer b;
    @SerializedName("interval_for_display")
    @Expose
    private Integer c;
    @SerializedName("frequency_cap")
    @Expose
    private Integer d;
    @SerializedName("flush_card")
    @Expose
    private List<String> e;

    public Integer a() {
        return this.a;
    }

    public Integer b() {
        return this.b;
    }

    public Integer c() {
        return this.c;
    }

    public Integer d() {
        return this.d;
    }

    public List<String> e() {
        return this.e;
    }
}
