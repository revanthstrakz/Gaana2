package com.helpshift.conversation.activeconversation.message;

public abstract class i extends c {
    public String b;
    public String h;

    i(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, boolean z, MessageType messageType) {
        super(str, str2, str3, i, str7, str4, str5, z, messageType);
        this.b = str6;
    }

    public void a(j jVar) {
        super.a(jVar);
        if (jVar instanceof i) {
            this.b = ((i) jVar).b;
        }
    }
}
