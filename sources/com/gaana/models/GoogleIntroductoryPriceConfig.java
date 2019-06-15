package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class GoogleIntroductoryPriceConfig {
    @SerializedName("intro_config")
    private IntroConfig intro_config;
    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private boolean status;

    public class IntroConfig {
        @SerializedName("intro_cta_text")
        private String intro_cta_text;
        @SerializedName("intro_duration")
        private String intro_duration;
        @SerializedName("intro_eligible_msg")
        private String intro_eligible_msg;
        @SerializedName("intro_ineligible_msg")
        private String intro_ineligible_msg;
        @SerializedName("intro_p_id")
        private String intro_p_id;
        @SerializedName("intro_plan_id")
        private String intro_plan_id;
        @SerializedName("intro_tag")
        private String intro_tag;
        @SerializedName("intro_title")
        private String intro_title;
        @SerializedName("intro_tnc_link")
        private String intro_tnc_link;
        @SerializedName("introductory_description")
        private String introductory_description;
        @SerializedName("introductory_offer_expire_msg")
        private String introductory_offer_expire_msg;
        @SerializedName("is_intro_eligible")
        private int is_intro_eligible;
        @SerializedName("is_introductory")
        private String is_introductory;

        public String getIs_introductory() {
            return this.is_introductory;
        }

        public void setIs_introductory(String str) {
            this.is_introductory = str;
        }

        public String getIntro_eligible_msg() {
            return this.intro_eligible_msg;
        }

        public void setIntro_eligible_msg(String str) {
            this.intro_eligible_msg = str;
        }

        public String getIntro_ineligible_msg() {
            return this.intro_ineligible_msg;
        }

        public void setIntro_ineligible_msg(String str) {
            this.intro_ineligible_msg = str;
        }

        public String getIntroductory_offer_expire_msg() {
            return this.introductory_offer_expire_msg;
        }

        public void setIntroductory_offer_expire_msg(String str) {
            this.introductory_offer_expire_msg = str;
        }

        public String getIntroductory_description() {
            return this.introductory_description;
        }

        public void setIntroductory_description(String str) {
            this.introductory_description = str;
        }

        public String getIntro_tag() {
            return this.intro_tag;
        }

        public void setIntro_tag(String str) {
            this.intro_tag = str;
        }

        public String getIntro_title() {
            return this.intro_title;
        }

        public void setIntro_title(String str) {
            this.intro_title = str;
        }

        public String getIntro_duration() {
            return this.intro_duration;
        }

        public void setIntro_duration(String str) {
            this.intro_duration = str;
        }

        public int getIs_intro_eligible() {
            return this.is_intro_eligible;
        }

        public void setIs_intro_eligible(int i) {
            this.is_intro_eligible = i;
        }

        public String getIntro_cta_text() {
            return this.intro_cta_text;
        }

        public void setIntro_cta_text(String str) {
            this.intro_cta_text = str;
        }

        public String getIntro_tnc_link() {
            return this.intro_tnc_link;
        }

        public void setIntro_tnc_link(String str) {
            this.intro_tnc_link = str;
        }

        public String getIntro_p_id() {
            return this.intro_p_id;
        }

        public void setIntro_p_id(String str) {
            this.intro_p_id = str;
        }

        public String getIntro_plan_id() {
            return this.intro_plan_id;
        }

        public void setIntro_plan_id(String str) {
            this.intro_plan_id = str;
        }
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean z) {
        this.status = z;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public IntroConfig getIntro_config() {
        return this.intro_config;
    }

    public void setIntro_config(IntroConfig introConfig) {
        this.intro_config = introConfig;
    }
}
