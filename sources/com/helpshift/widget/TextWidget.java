package com.helpshift.widget;

import java.util.regex.Pattern;

public abstract class TextWidget extends j {
    static final Pattern a = Pattern.compile("\\W+");
    static final Pattern b = Pattern.compile("[a-zA-Z0-9\\+\\._%\\-\\+]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{1,62})+");
    private String c;
    private TextWidgetError d;

    public enum TextWidgetError {
        EMPTY,
        LESS_THAN_MINIMUM_LENGTH,
        ONLY_SPECIAL_CHARACTERS,
        INVALID_EMAIL
    }

    public void a(String str) {
        if (!d().equals(str)) {
            this.c = str;
            if (c() != null) {
                a(null);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(TextWidgetError textWidgetError) {
        this.d = textWidgetError;
        e();
    }

    public TextWidgetError c() {
        return this.d;
    }

    public String d() {
        return this.c == null ? "" : this.c.trim();
    }
}
