package com.fasterxml.jackson.core.a;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;

public abstract class c extends JsonParser {
    protected JsonToken K;

    public abstract JsonToken a() throws IOException, JsonParseException;

    public abstract String f() throws IOException, JsonParseException;

    public abstract void t() throws JsonParseException;

    protected c() {
    }

    public JsonToken c() {
        return this.K;
    }

    public JsonParser b() throws IOException, JsonParseException {
        if (this.K == JsonToken.START_OBJECT || this.K == JsonToken.START_ARRAY) {
            int i = 1;
            while (true) {
                JsonToken a = a();
                if (a != null) {
                    switch (a) {
                        case START_OBJECT:
                        case START_ARRAY:
                            i++;
                            break;
                        case END_OBJECT:
                        case END_ARRAY:
                            i--;
                            if (i != 0) {
                                break;
                            }
                            return this;
                        default:
                            break;
                    }
                }
                t();
                return this;
            }
        }
        return this;
    }

    /* Access modifiers changed, original: protected */
    public void b(int i, String str) throws JsonParseException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected character (");
        stringBuilder.append(c(i));
        stringBuilder.append(")");
        String stringBuilder2 = stringBuilder.toString();
        if (str != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(stringBuilder2);
            stringBuilder.append(": ");
            stringBuilder.append(str);
            stringBuilder2 = stringBuilder.toString();
        }
        d(stringBuilder2);
    }

    /* Access modifiers changed, original: protected */
    public void C() throws JsonParseException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" in ");
        stringBuilder.append(this.K);
        c(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public void c(String str) throws JsonParseException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected end-of-input");
        stringBuilder.append(str);
        d(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public void D() throws JsonParseException {
        c(" in a value");
    }

    /* Access modifiers changed, original: protected */
    public void b(int i) throws JsonParseException {
        i = (char) i;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Illegal character (");
        stringBuilder.append(c(i));
        stringBuilder.append("): only regular white space (\\r, \\n, \\t) is allowed between tokens");
        d(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public void c(int i, String str) throws JsonParseException {
        if (!a(Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i >= 32) {
            i = (char) i;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Illegal unquoted character (");
            stringBuilder.append(c(i));
            stringBuilder.append("): has to be escaped using backslash to be included in ");
            stringBuilder.append(str);
            d(stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: protected */
    public char a(char c) throws JsonProcessingException {
        if (a(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {
            return c;
        }
        if (c == '\'' && a(Feature.ALLOW_SINGLE_QUOTES)) {
            return c;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unrecognized character escape ");
        stringBuilder.append(c((int) c));
        d(stringBuilder.toString());
        return c;
    }

    protected static final String c(int i) {
        char c = (char) i;
        StringBuilder stringBuilder;
        if (Character.isISOControl(c)) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("(CTRL-CHAR, code ");
            stringBuilder2.append(i);
            stringBuilder2.append(")");
            return stringBuilder2.toString();
        } else if (i > 255) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("'");
            stringBuilder.append(c);
            stringBuilder.append("' (code ");
            stringBuilder.append(i);
            stringBuilder.append(" / 0x");
            stringBuilder.append(Integer.toHexString(i));
            stringBuilder.append(")");
            return stringBuilder.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append("'");
            stringBuilder.append(c);
            stringBuilder.append("' (code ");
            stringBuilder.append(i);
            stringBuilder.append(")");
            return stringBuilder.toString();
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void d(String str) throws JsonParseException {
        throw a(str);
    }

    /* Access modifiers changed, original: protected|final */
    public final void a(String str, Throwable th) throws JsonParseException {
        throw b(str, th);
    }

    /* Access modifiers changed, original: protected|final */
    public final void E() {
        throw new RuntimeException("Internal error: this code path should never get executed");
    }

    /* Access modifiers changed, original: protected|final */
    public final JsonParseException b(String str, Throwable th) {
        return new JsonParseException(str, e(), th);
    }
}
