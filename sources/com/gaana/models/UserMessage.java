package com.gaana.models;

public class UserMessage extends BusinessObject {
    private static final long serialVersionUID = 1;
    private String emptyMsg;

    public String getEmptyMsg() {
        return this.emptyMsg;
    }

    public void setEmptyMsg(String str) {
        this.emptyMsg = str;
    }
}
