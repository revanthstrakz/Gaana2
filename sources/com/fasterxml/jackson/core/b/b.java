package com.fasterxml.jackson.core.b;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.a.a;
import com.fasterxml.jackson.core.d;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.c;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

public abstract class b extends a {
    protected static final int[] g = com.fasterxml.jackson.core.io.b.f();
    protected final c h;
    protected int[] i = g;
    protected int j;
    protected CharacterEscapes k;
    protected d l = DefaultPrettyPrinter.a;

    public b(c cVar, int i, com.fasterxml.jackson.core.b bVar) {
        super(i, bVar);
        this.h = cVar;
        if (a(Feature.ESCAPE_NON_ASCII)) {
            a(127);
        }
    }

    public JsonGenerator a(int i) {
        if (i < 0) {
            i = 0;
        }
        this.j = i;
        return this;
    }

    public JsonGenerator a(CharacterEscapes characterEscapes) {
        this.k = characterEscapes;
        if (characterEscapes == null) {
            this.i = g;
        } else {
            this.i = characterEscapes.a();
        }
        return this;
    }

    public JsonGenerator a(d dVar) {
        this.l = dVar;
        return this;
    }
}
