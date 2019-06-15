package com.google.api.client.json.jackson2;

import com.google.api.client.json.JsonParser;
import com.google.api.client.json.JsonToken;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

final class JacksonParser extends JsonParser {
    private final JacksonFactory factory;
    private final com.fasterxml.jackson.core.JsonParser parser;

    public JacksonFactory getFactory() {
        return this.factory;
    }

    JacksonParser(JacksonFactory jacksonFactory, com.fasterxml.jackson.core.JsonParser jsonParser) {
        this.factory = jacksonFactory;
        this.parser = jsonParser;
    }

    public void close() throws IOException {
        this.parser.close();
    }

    public JsonToken nextToken() throws IOException {
        return JacksonFactory.convert(this.parser.a());
    }

    public String getCurrentName() throws IOException {
        return this.parser.d();
    }

    public JsonToken getCurrentToken() {
        return JacksonFactory.convert(this.parser.c());
    }

    public JsonParser skipChildren() throws IOException {
        this.parser.b();
        return this;
    }

    public String getText() throws IOException {
        return this.parser.f();
    }

    public byte getByteValue() throws IOException {
        return this.parser.g();
    }

    public float getFloatValue() throws IOException {
        return this.parser.l();
    }

    public int getIntValue() throws IOException {
        return this.parser.i();
    }

    public short getShortValue() throws IOException {
        return this.parser.h();
    }

    public BigInteger getBigIntegerValue() throws IOException {
        return this.parser.k();
    }

    public BigDecimal getDecimalValue() throws IOException {
        return this.parser.n();
    }

    public double getDoubleValue() throws IOException {
        return this.parser.m();
    }

    public long getLongValue() throws IOException {
        return this.parser.j();
    }
}
