package com.fasterxml.jackson.core;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class JsonParser implements Closeable {
    protected int a;

    public enum Feature {
        AUTO_CLOSE_SOURCE(true),
        ALLOW_COMMENTS(false),
        ALLOW_UNQUOTED_FIELD_NAMES(false),
        ALLOW_SINGLE_QUOTES(false),
        ALLOW_UNQUOTED_CONTROL_CHARS(false),
        ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
        ALLOW_NUMERIC_LEADING_ZEROS(false),
        ALLOW_NON_NUMERIC_NUMBERS(false);
        
        private final boolean _defaultState;

        public static int collectDefaults() {
            Feature[] values = values();
            int i = 0;
            int length = values.length;
            int i2 = 0;
            while (i < length) {
                Feature feature = values[i];
                if (feature.enabledByDefault()) {
                    i2 |= feature.getMask();
                }
                i++;
            }
            return i2;
        }

        private Feature(boolean z) {
            this._defaultState = z;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    public abstract JsonToken a() throws IOException, JsonParseException;

    public abstract JsonParser b() throws IOException, JsonParseException;

    public abstract JsonToken c();

    public abstract void close() throws IOException;

    public abstract String d() throws IOException, JsonParseException;

    public abstract JsonLocation e();

    public abstract String f() throws IOException, JsonParseException;

    public abstract int i() throws IOException, JsonParseException;

    public abstract long j() throws IOException, JsonParseException;

    public abstract BigInteger k() throws IOException, JsonParseException;

    public abstract float l() throws IOException, JsonParseException;

    public abstract double m() throws IOException, JsonParseException;

    public abstract BigDecimal n() throws IOException, JsonParseException;

    protected JsonParser() {
    }

    public boolean a(Feature feature) {
        return (feature.getMask() & this.a) != 0;
    }

    public byte g() throws IOException, JsonParseException {
        int i = i();
        if (i >= -128 && i <= 255) {
            return (byte) i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Numeric value (");
        stringBuilder.append(f());
        stringBuilder.append(") out of range of Java byte");
        throw a(stringBuilder.toString());
    }

    public short h() throws IOException, JsonParseException {
        int i = i();
        if (i >= -32768 && i <= 32767) {
            return (short) i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Numeric value (");
        stringBuilder.append(f());
        stringBuilder.append(") out of range of Java short");
        throw a(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public JsonParseException a(String str) {
        return new JsonParseException(str, e());
    }
}
