package com.gaana.models;

import java.io.Serializable;
import java.util.ArrayList;

public class UserJourney implements Serializable {
    private ArrayList<Journey> data;
    private UserInfo u_info;

    public static class Journey {
        private String f_id;
        private String f_idx;
        private String f_n;
        private String subType;
        private String t_id;
        private String t_idx;
        private String t_n;
        private String ts;
        private String type;

        public String getSubType() {
            return this.subType;
        }

        public void setSubType(String str) {
            this.subType = str;
        }

        public String getF_idx() {
            return this.f_idx;
        }

        public void setF_idx(String str) {
            this.f_idx = str;
        }

        public String getT_idx() {
            return this.t_idx;
        }

        public void setT_idx(String str) {
            this.t_idx = str;
        }

        public String getFId() {
            return this.f_id;
        }

        public void setFId(String str) {
            this.f_id = str;
        }

        public String getFN() {
            return this.f_n;
        }

        public void setFN(String str) {
            this.f_n = str;
        }

        public String getTId() {
            return this.t_id;
        }

        public void setTId(String str) {
            this.t_id = str;
        }

        public String getTN() {
            return this.t_n;
        }

        public void setTN(String str) {
            this.t_n = str;
        }

        public String getTS() {
            return this.ts;
        }

        public void setTS(String str) {
            this.ts = str;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }
    }

    public static class UserInfo {
        private String nw;
        private String ram;
        private String type;
        private String u_dob;
        private String u_gender;
        private String u_id;
        private String u_login_type;
        private String u_type;

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getUId() {
            return this.u_id;
        }

        public void setUId(String str) {
            this.u_id = str;
        }

        public String getNw() {
            return this.nw;
        }

        public void setNw(String str) {
            this.nw = str;
        }

        public String getU_id() {
            return this.u_id;
        }

        public void setU_id(String str) {
            this.u_id = str;
        }

        public String getU_type() {
            return this.u_type;
        }

        public void setU_type(String str) {
            this.u_type = str;
        }

        public String getU_dob() {
            return this.u_dob;
        }

        public void setU_dob(String str) {
            this.u_dob = str;
        }

        public String getU_gender() {
            return this.u_gender;
        }

        public void setU_gender(String str) {
            this.u_gender = str;
        }

        public String getU_login_type() {
            return this.u_login_type;
        }

        public void setU_login_type(String str) {
            this.u_login_type = str;
        }

        public String getRam() {
            return this.ram;
        }

        public void setRam(String str) {
            this.ram = str;
        }
    }

    public ArrayList<Journey> getData() {
        return this.data;
    }

    public void setData(ArrayList<Journey> arrayList) {
        this.data = arrayList;
    }

    public UserInfo getUInfo() {
        return this.u_info;
    }

    public void setUInfo(UserInfo userInfo) {
        this.u_info = userInfo;
    }
}
