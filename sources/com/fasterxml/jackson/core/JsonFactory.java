package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.b.e;
import com.fasterxml.jackson.core.b.f;
import com.fasterxml.jackson.core.b.h;
import com.fasterxml.jackson.core.c.a;
import com.fasterxml.jackson.core.c.b;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.core.io.c;
import com.fasterxml.jackson.core.io.i;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Writer;
import java.lang.ref.SoftReference;

public class JsonFactory implements Serializable {
    protected static final int a = Feature.collectDefaults();
    protected static final int b = com.fasterxml.jackson.core.JsonParser.Feature.collectDefaults();
    protected static final int c = com.fasterxml.jackson.core.JsonGenerator.Feature.collectDefaults();
    protected static final ThreadLocal<SoftReference<BufferRecycler>> d = new ThreadLocal();
    private static final d o = DefaultPrettyPrinter.a;
    private static final long serialVersionUID = 8726401676402117450L;
    protected final transient b e;
    protected final transient a f;
    protected b g;
    protected int h;
    protected int i;
    protected int j;
    protected CharacterEscapes k;
    protected InputDecorator l;
    protected OutputDecorator m;
    protected d n;

    public enum Feature {
        INTERN_FIELD_NAMES(true),
        CANONICALIZE_FIELD_NAMES(true);
        
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

        public boolean enabledIn(int i) {
            return (i & getMask()) != 0;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    public JsonFactory() {
        this(null);
    }

    public JsonFactory(b bVar) {
        this.e = b.a();
        this.f = a.a();
        this.h = a;
        this.i = b;
        this.j = c;
        this.n = o;
        this.g = bVar;
    }

    /* Access modifiers changed, original: protected */
    public Object readResolve() {
        return new JsonFactory(this.g);
    }

    public final boolean a(Feature feature) {
        return (feature.getMask() & this.h) != 0;
    }

    public final JsonFactory a(com.fasterxml.jackson.core.JsonGenerator.Feature feature, boolean z) {
        return z ? a(feature) : b(feature);
    }

    public JsonFactory a(com.fasterxml.jackson.core.JsonGenerator.Feature feature) {
        this.j = feature.getMask() | this.j;
        return this;
    }

    public JsonFactory b(com.fasterxml.jackson.core.JsonGenerator.Feature feature) {
        this.j = (feature.getMask() ^ -1) & this.j;
        return this;
    }

    public JsonParser a(InputStream inputStream) throws IOException, JsonParseException {
        c a = a((Object) inputStream, false);
        if (this.l != null) {
            inputStream = this.l.a(a, inputStream);
        }
        return a(inputStream, a);
    }

    public JsonParser a(Reader reader) throws IOException, JsonParseException {
        c a = a((Object) reader, false);
        if (this.l != null) {
            reader = this.l.a(a, reader);
        }
        return a(reader, a);
    }

    public JsonParser a(String str) throws IOException, JsonParseException {
        Reader stringReader = new StringReader(str);
        c a = a((Object) stringReader, true);
        if (this.l != null) {
            stringReader = this.l.a(a, stringReader);
        }
        return a(stringReader, a);
    }

    public JsonGenerator a(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        c a = a((Object) outputStream, false);
        a.a(jsonEncoding);
        if (jsonEncoding == JsonEncoding.UTF8) {
            if (this.m != null) {
                outputStream = this.m.a(a, outputStream);
            }
            return a(outputStream, a);
        }
        Writer a2 = a(outputStream, jsonEncoding, a);
        if (this.m != null) {
            a2 = this.m.a(a, a2);
        }
        return a(a2, a);
    }

    public JsonGenerator a(Writer writer) throws IOException {
        c a = a((Object) writer, false);
        if (this.m != null) {
            writer = this.m.a(a, writer);
        }
        return a(writer, a);
    }

    /* Access modifiers changed, original: protected */
    public JsonParser a(InputStream inputStream, c cVar) throws IOException, JsonParseException {
        return b(inputStream, cVar);
    }

    /* Access modifiers changed, original: protected */
    @Deprecated
    public JsonParser b(InputStream inputStream, c cVar) throws IOException, JsonParseException {
        return new com.fasterxml.jackson.core.b.a(cVar, inputStream).a(this.i, this.g, this.f, this.e, a(Feature.CANONICALIZE_FIELD_NAMES), a(Feature.INTERN_FIELD_NAMES));
    }

    /* Access modifiers changed, original: protected */
    public JsonParser a(Reader reader, c cVar) throws IOException, JsonParseException {
        return b(reader, cVar);
    }

    /* Access modifiers changed, original: protected */
    @Deprecated
    public JsonParser b(Reader reader, c cVar) throws IOException, JsonParseException {
        return new e(cVar, this.i, reader, this.g, this.e.a(a(Feature.CANONICALIZE_FIELD_NAMES), a(Feature.INTERN_FIELD_NAMES)));
    }

    /* Access modifiers changed, original: protected */
    public JsonGenerator a(Writer writer, c cVar) throws IOException {
        return b(writer, cVar);
    }

    /* Access modifiers changed, original: protected */
    @Deprecated
    public JsonGenerator b(Writer writer, c cVar) throws IOException {
        h hVar = new h(cVar, this.j, this.g, writer);
        if (this.k != null) {
            hVar.a(this.k);
        }
        d dVar = this.n;
        if (dVar != o) {
            hVar.a(dVar);
        }
        return hVar;
    }

    /* Access modifiers changed, original: protected */
    @Deprecated
    public JsonGenerator a(OutputStream outputStream, c cVar) throws IOException {
        f fVar = new f(cVar, this.j, this.g, outputStream);
        if (this.k != null) {
            fVar.a(this.k);
        }
        d dVar = this.n;
        if (dVar != o) {
            fVar.a(dVar);
        }
        return fVar;
    }

    /* Access modifiers changed, original: protected */
    public Writer a(OutputStream outputStream, JsonEncoding jsonEncoding, c cVar) throws IOException {
        if (jsonEncoding == JsonEncoding.UTF8) {
            return new i(cVar, outputStream);
        }
        return new OutputStreamWriter(outputStream, jsonEncoding.getJavaName());
    }

    /* Access modifiers changed, original: protected */
    public c a(Object obj, boolean z) {
        return new c(a(), obj, z);
    }

    public BufferRecycler a() {
        BufferRecycler bufferRecycler;
        SoftReference softReference = (SoftReference) d.get();
        if (softReference == null) {
            bufferRecycler = null;
        } else {
            bufferRecycler = (BufferRecycler) softReference.get();
        }
        if (bufferRecycler != null) {
            return bufferRecycler;
        }
        bufferRecycler = new BufferRecycler();
        d.set(new SoftReference(bufferRecycler));
        return bufferRecycler;
    }
}
