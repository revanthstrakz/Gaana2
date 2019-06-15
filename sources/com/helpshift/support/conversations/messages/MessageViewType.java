package com.helpshift.support.conversations.messages;

public enum MessageViewType {
    USER_TEXT_MESSAGE(10),
    ADMIN_TEXT_MESSAGE(20),
    USER_SCREENSHOT_ATTACHMENT(30),
    ADMIN_ATTACHMENT_IMAGE(40),
    ADMIN_ATTACHMENT_GENERIC(50),
    ADMIN_REQUEST_ATTACHMENT(60),
    REQUESTED_APP_REVIEW(70),
    REQUEST_FOR_REOPEN(80),
    ACCEPTED_APP_REVIEW(90),
    CONFIRMATION_REJECTED(100),
    CONVERSATION_FOOTER(110),
    AGENT_TYPING_FOOTER(120);
    
    public final int key;

    private MessageViewType(int i) {
        this.key = i;
    }
}
