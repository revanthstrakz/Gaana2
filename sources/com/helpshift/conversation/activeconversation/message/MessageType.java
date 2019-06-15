package com.helpshift.conversation.activeconversation.message;

public enum MessageType {
    USER_TEXT("mobile_text"),
    ADMIN_TEXT("admin_text"),
    ACCEPTED_APP_REVIEW("accepted_app_review"),
    REQUESTED_APP_REVIEW("request_app_review"),
    FOLLOWUP_ACCEPTED("followup_accepted"),
    FOLLOWUP_REJECTED("followup_rejected"),
    CONFIRMATION_ACCEPTED("confirmation_accepted"),
    CONFIRMATION_REJECTED("confirmation_rejected"),
    SCREENSHOT("screenshot"),
    REQUESTED_SCREENSHOT("request_screenshot"),
    ADMIN_ATTACHMENT("admin_attachment"),
    ADMIN_IMAGE_ATTACHMENT("admin_image_attachment"),
    REQUEST_FOR_REOPEN("request_for_reopen");
    
    private String value;

    private MessageType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public static MessageType fromValue(String str) {
        for (MessageType messageType : values()) {
            if (messageType.getValue().equals(str)) {
                return messageType;
            }
        }
        return null;
    }
}
