package com.helpshift.conversation.dto;

import java.util.HashMap;
import java.util.Map;

public enum ConversationStatus {
    NEW(0),
    IN_PROGRESS(1),
    RESOLUTION_REQUESTED(2),
    REJECTED(3),
    RESOLUTION_ACCEPTED(101),
    RESOLUTION_REJECTED(102),
    ARCHIVED(103),
    UNKNOWN(-1);
    
    private static final Map<Integer, ConversationStatus> map = null;
    private final int value;

    static {
        map = new HashMap();
        ConversationStatus[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            ConversationStatus conversationStatus = values[i];
            map.put(Integer.valueOf(conversationStatus.value), conversationStatus);
            i++;
        }
    }

    private ConversationStatus(int i) {
        this.value = i;
    }

    public static ConversationStatus fromInt(int i) {
        ConversationStatus conversationStatus = (ConversationStatus) map.get(Integer.valueOf(i));
        return conversationStatus == null ? UNKNOWN : conversationStatus;
    }

    public int getValue() {
        return this.value;
    }
}
