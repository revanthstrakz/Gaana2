package com.gaana.models;

import com.gaana.R;
import com.gaana.application.GaanaApplication;
import java.io.Serializable;

public class AppUpdateData implements Serializable {
    public static final String HARD_UPDATE_DEFAULT_MSG;
    public static final String SOFT_UPDATE_DEFAULT_MSG;
    private static final long serialVersionUID = 1;
    private String app_ver;
    private long lastUpdatedTime;
    private String msg;
    private String upd_flag;

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<p><big><b>");
        stringBuilder.append(GaanaApplication.getContext().getResources().getString(R.string.su_new_version_available));
        stringBuilder.append("</b></big></p>");
        stringBuilder.append(GaanaApplication.getInstance().getResources().getString(R.string.su_update_now_latest));
        SOFT_UPDATE_DEFAULT_MSG = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append("<p><big><b>");
        stringBuilder.append(GaanaApplication.getContext().getResources().getString(R.string.hu_version_not_supported));
        stringBuilder.append("</b></big></p>");
        HARD_UPDATE_DEFAULT_MSG = stringBuilder.toString();
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getUpdatedFlag() {
        return this.upd_flag;
    }

    public void setUpdatedFlag(String str) {
        this.upd_flag = str;
    }

    public String getAppVer() {
        return this.app_ver;
    }

    public void setAppVer(String str) {
        this.app_ver = str;
    }

    public long getLastUpdatedTime() {
        return this.lastUpdatedTime;
    }

    public void setLastUpdatedTime(long j) {
        this.lastUpdatedTime = j;
    }
}
