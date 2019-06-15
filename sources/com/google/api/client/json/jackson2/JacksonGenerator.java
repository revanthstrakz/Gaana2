package com.google.api.client.json.jackson2;

import com.google.api.client.json.JsonGenerator;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

final class JacksonGenerator extends JsonGenerator {
    private final JacksonFactory factory;
    private final com.fasterxml.jackson.core.JsonGenerator generator;

    public JacksonFactory getFactory() {
        return this.factory;
    }

    JacksonGenerator(JacksonFactory jacksonFactory, com.fasterxml.jackson.core.JsonGenerator jsonGenerator) {
        this.factory = jacksonFactory;
        this.generator = jsonGenerator;
    }

    public void flush() throws IOException {
        this.generator.flush();
    }

    public void close() throws IOException {
        this.generator.close();
    }

    public void writeBoolean(boolean z) throws IOException {
        this.generator.a(z);
    }

    public void writeEndArray() throws IOException {
        this.generator.d();
    }

    public void writeEndObject() throws IOException {
        this.generator.f();
    }

    public void writeFieldName(String str) throws IOException {
        this.generator.a(str);
    }

    public void writeNull() throws IOException {
        this.generator.g();
    }

    public void writeNumber(int i) throws IOException {
        this.generator.b(i);
    }

    public void writeNumber(long j) throws IOException {
        this.generator.a(j);
    }

    public void writeNumber(BigInteger bigInteger) throws IOException {
        this.generator.a(bigInteger);
    }

    public void writeNumber(double d) throws IOException {
        this.generator.a(d);
    }

    public void writeNumber(float f) throws IOException {
        this.generator.a(f);
    }

    public void writeNumber(BigDecimal bigDecimal) throws IOException {
        this.generator.a(bigDecimal);
    }

    public void writeNumber(String str) throws IOException {
        this.generator.d(str);
    }

    public void writeStartArray() throws IOException {
        this.generator.c();
    }

    public void writeStartObject() throws IOException {
        this.generator.e();
    }

    public void writeString(String str) throws IOException {
        this.generator.b(str);
    }

    public void enablePrettyPrint() throws IOException {
        this.generator.b();
    }
}
