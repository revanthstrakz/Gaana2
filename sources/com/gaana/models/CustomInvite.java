package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class CustomInvite implements Serializable {
    private long lastUpdatedTime;
    @SerializedName("promoText")
    private PromoText[] promoText;

    public class PromoText implements Serializable {
        @SerializedName("textMessage")
        private TextMsg[] textMessage;
        @SerializedName("text_type")
        private String text_type;

        public String getText_type() {
            return this.text_type;
        }

        public TextMsg[] getTextMessage() {
            return this.textMessage;
        }
    }

    public class TextMsg implements Serializable {
        @SerializedName("text")
        private String text;
        @SerializedName("text_font")
        private String text_font;

        public String getText() {
            return this.text;
        }

        public String getText_font() {
            return this.text_font;
        }
    }

    public PromoText[] getPromoText() {
        return this.promoText;
    }

    public long getLastUpdatedTime() {
        return this.lastUpdatedTime;
    }

    public void setLastUpdatedTime(long j) {
        this.lastUpdatedTime = j;
    }
}
