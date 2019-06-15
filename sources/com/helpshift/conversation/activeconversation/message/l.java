package com.helpshift.conversation.activeconversation.message;

public class l extends j {
    public boolean a;

    public l(String str, String str2, String str3, String str4) {
        super(str2, str3, str4, true, MessageType.REQUEST_FOR_REOPEN);
        this.i = str;
    }

    public boolean a() {
        return this.a;
    }
}
