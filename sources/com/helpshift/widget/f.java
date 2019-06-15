package com.helpshift.widget;

import com.helpshift.widget.TextWidget.TextWidgetError;

public class f extends TextWidget {
    public void a() {
        if (d().length() == 0) {
            a(TextWidgetError.EMPTY);
        } else if (a.matcher(d()).matches()) {
            a(TextWidgetError.ONLY_SPECIAL_CHARACTERS);
        } else {
            a(null);
        }
    }
}
