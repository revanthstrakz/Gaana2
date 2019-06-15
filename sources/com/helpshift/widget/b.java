package com.helpshift.widget;

import com.helpshift.conversation.activeconversation.message.ConversationFooterState;

public class b extends j {
    private ConversationFooterState a = ConversationFooterState.NONE;

    public ConversationFooterState a() {
        return this.a;
    }

    public void a(ConversationFooterState conversationFooterState) {
        if (this.a != conversationFooterState) {
            this.a = conversationFooterState;
            e();
        }
    }
}
