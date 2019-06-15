package com.helpshift.widget;

import com.helpshift.widget.TextWidget.TextWidgetError;

public class d extends TextWidget {
    private boolean c;

    public void a(boolean z) {
        this.c = z;
    }

    public boolean a() {
        return this.c;
    }

    public void b() {
        if (d().length() == 0) {
            if (a()) {
                a(TextWidgetError.EMPTY);
            } else {
                a(null);
            }
        } else if (!b.matcher(d()).matches()) {
            a(TextWidgetError.INVALID_EMAIL);
        }
    }
}
