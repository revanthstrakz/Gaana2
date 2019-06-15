package com.helpshift.conversation.states;

import java.util.HashMap;
import java.util.Map;

public enum ConversationCSATState {
    NONE(0),
    SUBMITTED_NOT_SYNCED(1),
    SUBMITTED_SYNCED(2);
    
    private static final Map<Integer, ConversationCSATState> map = null;
    private final int value;

    static {
        map = new HashMap();
        ConversationCSATState[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            ConversationCSATState conversationCSATState = values[i];
            map.put(Integer.valueOf(conversationCSATState.value), conversationCSATState);
            i++;
        }
    }

    private ConversationCSATState(int i) {
        this.value = i;
    }

    public static ConversationCSATState fromInt(int i) {
        ConversationCSATState conversationCSATState = (ConversationCSATState) map.get(Integer.valueOf(i));
        return conversationCSATState == null ? NONE : conversationCSATState;
    }

    public int getValue() {
        return this.value;
    }
}
