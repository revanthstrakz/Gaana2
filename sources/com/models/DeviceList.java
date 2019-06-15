package com.models;

import com.gaana.models.BusinessObject;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class DeviceList extends BusinessObject {
    @SerializedName("results")
    private ArrayList<Device> a;

    public static class Device extends BusinessObject {
        private static final long serialVersionUID = 1;
        protected boolean a = false;
        @SerializedName("device_id")
        private String b;
        @SerializedName("device_name")
        private String c;
        @SerializedName("current_device")
        private String d;
        @SerializedName("total_track")
        private String e;

        public boolean a() {
            return this.a;
        }

        public void a(boolean z) {
            this.a = z;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public String d() {
            return this.d;
        }

        public String e() {
            return this.e;
        }
    }

    public ArrayList<Device> a() {
        return this.a;
    }
}
