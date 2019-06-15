package com.gaana.models;

import com.google.gson.annotations.SerializedName;

public class LiveCricketData extends BusinessObject {
    @SerializedName("data")
    private Data data;
    @SerializedName("match_end")
    private int match_end;
    @SerializedName("message")
    private String message;
    @SerializedName("poll_time")
    private String poll_time;
    @SerializedName("status")
    private String status;

    public static class Data extends BusinessObject {
        private static final long serialVersionUID = 1;
        @SerializedName("batsmen1_info")
        private String batsmen1_info;
        @SerializedName("batsmen1_name")
        private String batsmen1_name;
        @SerializedName("batsmen2_info")
        private String batsmen2_info;
        @SerializedName("batsmen2_name")
        private String batsmen2_name;
        @SerializedName("batting_team_number")
        private String batting_team_number;
        @SerializedName("batting_team_overs")
        private String batting_team_overs;
        @SerializedName("match_detail")
        private String match_detail;
        @SerializedName("match_name")
        private String match_name;
        @SerializedName("onstrike_batsmen_number")
        private int onstrike_batsmen_number;
        @SerializedName("team1_info")
        private String team1_info;
        @SerializedName("team2_info")
        private String team2_info;

        public String getMatch_name() {
            return this.match_name;
        }

        public void setMatch_name(String str) {
            this.match_name = str;
        }

        public String getMatch_detail() {
            return this.match_detail;
        }

        public void setMatch_detail(String str) {
            this.match_detail = str;
        }

        public String getTeam1_info() {
            return this.team1_info;
        }

        public void setTeam1_info(String str) {
            this.team1_info = str;
        }

        public String getTeam2_info() {
            return this.team2_info;
        }

        public void setTeam2_info(String str) {
            this.team2_info = str;
        }

        public String getBatting_team_number() {
            return this.batting_team_number;
        }

        public void setBatting_team_number(String str) {
            this.batting_team_number = str;
        }

        public String getBatting_team_overs() {
            return this.batting_team_overs;
        }

        public void setBatting_team_overs(String str) {
            this.batting_team_overs = str;
        }

        public String getBatsmen1_name() {
            return this.batsmen1_name;
        }

        public void setBatsmen1_name(String str) {
            this.batsmen1_name = str;
        }

        public String getBatsmen1_info() {
            return this.batsmen1_info;
        }

        public void setBatsmen1_info(String str) {
            this.batsmen1_info = str;
        }

        public String getBatsmen2_name() {
            return this.batsmen2_name;
        }

        public void setBatsmen2_name(String str) {
            this.batsmen2_name = str;
        }

        public String getBatsmen2_info() {
            return this.batsmen2_info;
        }

        public void setBatsmen2_info(String str) {
            this.batsmen2_info = str;
        }

        public int getOnstrike_batsmen_number() {
            return this.onstrike_batsmen_number;
        }

        public void setOnstrike_batsmen_number(int i) {
            this.onstrike_batsmen_number = i;
        }
    }

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

    public int getMatch_end() {
        return this.match_end;
    }

    public void setMatch_end(int i) {
        this.match_end = i;
    }

    public String getPoll_time() {
        return this.poll_time;
    }

    public void setPoll_time(String str) {
        this.poll_time = str;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
