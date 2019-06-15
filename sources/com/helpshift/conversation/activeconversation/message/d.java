package com.helpshift.conversation.activeconversation.message;

import com.helpshift.common.domain.network.c;
import com.helpshift.common.domain.network.e;
import com.helpshift.common.domain.network.f;
import com.helpshift.common.domain.network.h;
import com.helpshift.common.domain.network.k;
import com.helpshift.common.domain.network.l;

public abstract class d extends j {
    public abstract void a(String str, String str2);

    d(String str, String str2, String str3, boolean z, MessageType messageType) {
        super(str, str2, str3, z, messageType);
    }

    /* Access modifiers changed, original: 0000 */
    public h a_(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/issues/");
        stringBuilder.append(str);
        stringBuilder.append("/messages/");
        return new f(new e(new c(new l(new k(stringBuilder.toString(), this.t, this.u), this.u))));
    }
}
