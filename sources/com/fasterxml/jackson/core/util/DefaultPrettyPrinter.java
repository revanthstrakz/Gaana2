package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.c;
import com.fasterxml.jackson.core.d;
import com.fasterxml.jackson.core.io.SerializedString;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class DefaultPrettyPrinter implements c, Serializable {
    public static final SerializedString a = new SerializedString(" ");
    private static final long serialVersionUID = -5512586643324525213L;
    protected a b;
    protected a c;
    protected final d d;
    protected boolean e;
    protected transient int f;

    public interface a {
        void a(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException;

        boolean a();
    }

    public static class FixedSpaceIndenter implements a, Serializable {
        public static FixedSpaceIndenter a = new FixedSpaceIndenter();
        private static final long serialVersionUID = 1;

        public boolean a() {
            return true;
        }

        public void a(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
            jsonGenerator.a(' ');
        }
    }

    public static class Lf2SpacesIndenter implements a, Serializable {
        public static Lf2SpacesIndenter a = new Lf2SpacesIndenter();
        static final String b;
        static final char[] c = new char[64];
        private static final long serialVersionUID = 1;

        public boolean a() {
            return false;
        }

        static {
            String property;
            try {
                property = System.getProperty("line.separator");
            } catch (Throwable unused) {
                property = null;
            }
            if (property == null) {
                property = "\n";
            }
            b = property;
            Arrays.fill(c, ' ');
        }

        public void a(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
            jsonGenerator.c(b);
            if (i > 0) {
                i += i;
                while (i > 64) {
                    jsonGenerator.a(c, 0, 64);
                    i -= c.length;
                }
                jsonGenerator.a(c, 0, i);
            }
        }
    }

    public DefaultPrettyPrinter() {
        this(a);
    }

    public DefaultPrettyPrinter(d dVar) {
        this.b = FixedSpaceIndenter.a;
        this.c = Lf2SpacesIndenter.a;
        this.e = true;
        this.f = 0;
        this.d = dVar;
    }

    public void a(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        if (this.d != null) {
            jsonGenerator.b(this.d);
        }
    }

    public void b(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.a('{');
        if (!this.c.a()) {
            this.f++;
        }
    }

    public void h(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        this.c.a(jsonGenerator, this.f);
    }

    public void d(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        if (this.e) {
            jsonGenerator.c(" : ");
        } else {
            jsonGenerator.a(':');
        }
    }

    public void c(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.a(',');
        this.c.a(jsonGenerator, this.f);
    }

    public void a(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
        if (!this.c.a()) {
            this.f--;
        }
        if (i > 0) {
            this.c.a(jsonGenerator, this.f);
        } else {
            jsonGenerator.a(' ');
        }
        jsonGenerator.a('}');
    }

    public void e(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        if (!this.b.a()) {
            this.f++;
        }
        jsonGenerator.a('[');
    }

    public void g(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        this.b.a(jsonGenerator, this.f);
    }

    public void f(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.a(',');
        this.b.a(jsonGenerator, this.f);
    }

    public void b(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
        if (!this.b.a()) {
            this.f--;
        }
        if (i > 0) {
            this.b.a(jsonGenerator, this.f);
        } else {
            jsonGenerator.a(' ');
        }
        jsonGenerator.a(']');
    }
}
