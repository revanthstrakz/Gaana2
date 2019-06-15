package com.custom_card_response;

import com.gaana.models.GoogleIntroductoryPriceConfig.IntroConfig;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class e {
    @SerializedName("display")
    @Expose
    private d a;
    @SerializedName("date_range")
    @Expose
    private c b;
    @SerializedName("client")
    @Expose
    private b c;
    @SerializedName("intro_config")
    @Expose
    private IntroConfig d;

    public d a() {
        return this.a;
    }

    public c b() {
        return this.b;
    }

    public b c() {
        return this.c;
    }

    public IntroConfig d() {
        return this.d;
    }
}
