package com.gaana.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PaymentConfig implements Serializable {
    private static final long serialVersionUID = 1;
    @SerializedName("paid_trial")
    private PaidTrial paidTrial;
    @SerializedName("status")
    private int status = 0;
    @SerializedName("uts")
    private int uts;

    public class PaidTrial implements Serializable {
        private static final long serialVersionUID = 1;
        @SerializedName("is_gaana_trial")
        private boolean isFreeTrial = false;
        @SerializedName("trial")
        private boolean isPaidTrial = false;
        @SerializedName("pg_gateway")
        private String pgGateway;

        public void setIsFreeTrial(boolean z) {
            this.isFreeTrial = z;
        }

        public boolean isFreeTrial() {
            return this.isFreeTrial;
        }

        public void setPgGateway(String str) {
            this.pgGateway = str;
        }

        public String getPgGateway() {
            return this.pgGateway;
        }

        public void setIsPaidTrial(boolean z) {
            this.isPaidTrial = z;
        }

        public boolean isPaidTrial() {
            return this.isPaidTrial;
        }
    }

    public void setUts(int i) {
        this.uts = i;
    }

    public int getUts() {
        return this.uts;
    }

    public void setPaidTrial(PaidTrial paidTrial) {
        this.paidTrial = paidTrial;
    }

    public PaidTrial getPaidTrial() {
        return this.paidTrial;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getStatus() {
        return this.status;
    }
}
