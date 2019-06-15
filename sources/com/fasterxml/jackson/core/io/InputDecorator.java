package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;

public abstract class InputDecorator implements Serializable {
    private static final long serialVersionUID = 1;

    public abstract InputStream a(c cVar, InputStream inputStream) throws IOException;

    public abstract Reader a(c cVar, Reader reader) throws IOException;
}
