package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class GaEventsConfig {
    public static String ABTESTING_CATEGORY_KEY = "A/B Testing";
    public static String ABTESTING_PREFERENCE_KEY = "ABTESTING_PREFERENCE_KEY";
    public static String IN_APP_ACTION_APIRESPONSE_KEY = "API Response";
    public static String IN_APP_ACTION_RESPONSE_KEY = "Response";
    public static String IN_APP_CATEGORY_KEY = "Custom in-app";
    @SerializedName("A/B Testing")
    private ABTesting abTesting;
    @SerializedName("Custom in-app")
    private CustomInApp customInApp;
    @SerializedName("status")
    private int status;

    public class ABTesting {
        @SerializedName("master")
        private int master = 1;

        public int getMaster() {
            return this.master;
        }

        public void setMaster(int i) {
            this.master = i;
        }
    }

    public class CustomInApp {
        @SerializedName("API Response")
        private int apiResponse = 1;
        @SerializedName("master")
        private int master = 1;
        @SerializedName("Response")
        private int response = 1;

        public int getMaster() {
            return this.master;
        }

        public void setMaster(int i) {
            this.master = i;
        }

        public int getApiResponse() {
            return this.apiResponse;
        }

        public void setApiResponse(int i) {
            this.apiResponse = i;
        }

        public int getResponse() {
            return this.response;
        }

        public void setResponse(int i) {
            this.response = i;
        }
    }

    public ABTesting getAbTesting() {
        return this.abTesting;
    }

    public void setAbTesting(ABTesting aBTesting) {
        this.abTesting = aBTesting;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public CustomInApp getCustomInApp() {
        return this.customInApp;
    }

    public void setCustomInApp(CustomInApp customInApp) {
        this.customInApp = customInApp;
    }
}
