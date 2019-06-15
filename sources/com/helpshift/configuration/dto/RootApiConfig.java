package com.helpshift.configuration.dto;

import com.helpshift.common.c;
import java.util.Map;

public class RootApiConfig {
    public final Boolean a;
    public final Boolean b;
    public final Boolean c;
    public final Boolean d;
    public final Boolean e;
    public final Boolean f;
    public final EnableContactUs g;
    public final String h;
    public final Boolean i;
    public final Boolean j;

    public enum EnableContactUs {
        ALWAYS(0),
        NEVER(1),
        AFTER_VIEWING_FAQS(2),
        AFTER_MARKING_ANSWER_UNHELPFUL(3);
        
        private final int value;

        private EnableContactUs(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static EnableContactUs fromInt(int i) {
            for (EnableContactUs enableContactUs : values()) {
                if (enableContactUs.getValue() == i) {
                    return enableContactUs;
                }
            }
            return null;
        }
    }

    public static class a {
        private Boolean a;
        private Boolean b;
        private Boolean c;
        private Boolean d;
        private Boolean e;
        private Boolean f;
        private EnableContactUs g;
        private String h;
        private Boolean i;
        private Boolean j;

        public a a(Map<String, Object> map) {
            if (map.get("enableContactUs") instanceof Integer) {
                this.g = EnableContactUs.fromInt(((Integer) map.get("enableContactUs")).intValue());
            }
            if (map.get("gotoConversationAfterContactUs") instanceof Boolean) {
                this.a = (Boolean) map.get("gotoConversationAfterContactUs");
            } else if (map.get("gotoCoversationAfterContactUs") instanceof Boolean) {
                this.a = (Boolean) map.get("gotoCoversationAfterContactUs");
            }
            if (map.get("requireEmail") instanceof Boolean) {
                this.b = (Boolean) map.get("requireEmail");
            }
            if (map.get("hideNameAndEmail") instanceof Boolean) {
                this.c = (Boolean) map.get("hideNameAndEmail");
            }
            if (map.get("enableFullPrivacy") instanceof Boolean) {
                this.d = (Boolean) map.get("enableFullPrivacy");
            }
            if (map.get("showSearchOnNewConversation") instanceof Boolean) {
                this.e = (Boolean) map.get("showSearchOnNewConversation");
            }
            if (map.get("showConversationResolutionQuestion") instanceof Boolean) {
                this.f = (Boolean) map.get("showConversationResolutionQuestion");
            }
            if (map.get("conversationPrefillText") instanceof String) {
                String str = (String) map.get("conversationPrefillText");
                if (!c.a(str)) {
                    this.h = str;
                }
            }
            if (map.get("showConversationInfoScreen") instanceof Boolean) {
                this.i = (Boolean) map.get("showConversationInfoScreen");
            }
            if (map.get("enableTypingIndicator") instanceof Boolean) {
                this.j = (Boolean) map.get("enableTypingIndicator");
            }
            return this;
        }

        public RootApiConfig a() {
            return new RootApiConfig(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
        }
    }

    public RootApiConfig(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, EnableContactUs enableContactUs, String str, Boolean bool7, Boolean bool8) {
        this.g = enableContactUs;
        this.a = bool;
        this.b = bool2;
        this.c = bool3;
        this.h = str;
        this.d = bool4;
        this.e = bool5;
        this.f = bool6;
        this.i = bool7;
        this.j = bool8;
    }
}
