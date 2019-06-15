package com.facebook.accountkit.internal;

public enum Feature {
    EMAIL_ENABLED(0, 1),
    PHONE_NUMBER_ENABLED(1, 1),
    CALLBACK_BUTTON_ALTERNATE_TEXT(2, 1);
    
    private int defaultValue;
    private int prefKey;

    private Feature(int i, int i2) {
        this.prefKey = i;
        this.defaultValue = i2;
    }

    /* Access modifiers changed, original: 0000 */
    public int getPrefKey() {
        return this.prefKey;
    }

    /* Access modifiers changed, original: 0000 */
    public int getDefaultValue() {
        return this.defaultValue;
    }
}
