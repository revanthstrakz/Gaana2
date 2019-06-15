package com.helpshift.widget;

import com.helpshift.widget.TextWidget.TextWidgetError;

public class c extends TextWidget {
    private final int c;

    c(int i) {
        this.c = i;
    }

    public void a() {
        if (d().length() == 0) {
            a(TextWidgetError.EMPTY);
        } else if (a.matcher(d()).matches()) {
            a(TextWidgetError.ONLY_SPECIAL_CHARACTERS);
        } else if (d().length() < this.c) {
            a(TextWidgetError.LESS_THAN_MINIMUM_LENGTH);
        } else {
            a(null);
        }
    }
}
