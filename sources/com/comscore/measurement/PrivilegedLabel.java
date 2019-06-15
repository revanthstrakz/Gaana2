package com.comscore.measurement;

public class PrivilegedLabel extends Label {
    private Boolean a = Boolean.valueOf(true);

    public PrivilegedLabel(String str, String str2, Boolean bool) {
        super(str, str2, bool);
    }

    public Boolean getPrivileged() {
        return this.a;
    }
}
