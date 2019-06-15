package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public abstract class gp<T> {
    public abstract T read(hx hxVar) throws IOException;

    public abstract void write(hz hzVar, T t) throws IOException;

    public final void toJson(Writer writer, T t) throws IOException {
        write(new hz(writer), t);
    }

    public final gp<T> nullSafe() {
        return new gp<T>() {
            public void write(hz hzVar, T t) throws IOException {
                if (t == null) {
                    hzVar.f();
                } else {
                    gp.this.write(hzVar, t);
                }
            }

            public T read(hx hxVar) throws IOException {
                if (hxVar.f() != hy.NULL) {
                    return gp.this.read(hxVar);
                }
                hxVar.j();
                return null;
            }
        };
    }

    public final String toJson(T t) {
        StringWriter stringWriter = new StringWriter();
        try {
            toJson(stringWriter, t);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final gf toJsonTree(T t) {
        try {
            hm hmVar = new hm();
            write(hmVar, t);
            return hmVar.a();
        } catch (IOException e) {
            throw new gg(e);
        }
    }

    public final T fromJson(Reader reader) throws IOException {
        return read(new hx(reader));
    }

    public final T fromJson(String str) throws IOException {
        return fromJson(new StringReader(str));
    }

    public final T fromJsonTree(gf gfVar) {
        try {
            return read(new hl(gfVar));
        } catch (IOException e) {
            throw new gg(e);
        }
    }
}
