package com.fasterxml.jackson.core.a;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.b;
import com.fasterxml.jackson.core.b.d;
import com.fasterxml.jackson.core.c;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.IOException;

public abstract class a extends JsonGenerator {
    protected b b;
    protected int c;
    protected boolean d;
    protected d e = d.g();
    protected boolean f;

    public abstract void e(String str) throws IOException, JsonGenerationException;

    public abstract void i();

    protected a(int i, b bVar) {
        this.c = i;
        this.b = bVar;
        this.d = a(Feature.WRITE_NUMBERS_AS_STRINGS);
    }

    public final boolean a(Feature feature) {
        return (feature.getMask() & this.c) != 0;
    }

    public JsonGenerator b() {
        if (a() != null) {
            return this;
        }
        return a((c) new DefaultPrettyPrinter());
    }

    public final d h() {
        return this.e;
    }

    public void close() throws IOException {
        this.f = true;
    }

    /* Access modifiers changed, original: protected */
    public void f(String str) throws JsonGenerationException {
        throw new JsonGenerationException(str);
    }

    /* Access modifiers changed, original: protected */
    public void j() {
        throw new RuntimeException("Internal error: should never end up through this code path");
    }
}
