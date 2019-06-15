package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;

public abstract class OutputDecorator implements Serializable {
    private static final long serialVersionUID = 1;

    public abstract OutputStream a(c cVar, OutputStream outputStream) throws IOException;

    public abstract Writer a(c cVar, Writer writer) throws IOException;
}
