package com.library.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class DeserializerObjectInputStream extends ObjectInputStream {
    public DeserializerObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    /* Access modifiers changed, original: protected */
    public ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        ObjectStreamClass readClassDescriptor = super.readClassDescriptor();
        try {
            ObjectStreamClass lookup = ObjectStreamClass.lookup(Class.forName(readClassDescriptor.getName()));
            if (lookup != null) {
                long serialVersionUID = lookup.getSerialVersionUID();
                long serialVersionUID2 = readClassDescriptor.getSerialVersionUID();
                if (serialVersionUID2 != serialVersionUID) {
                    StringBuffer stringBuffer = new StringBuffer("Overriding serialized class version mismatch: ");
                    stringBuffer.append("local serialVersionUID = ");
                    stringBuffer.append(serialVersionUID);
                    stringBuffer.append(" stream serialVersionUID = ");
                    stringBuffer.append(serialVersionUID2);
                    InvalidClassException invalidClassException = new InvalidClassException(stringBuffer.toString());
                    readClassDescriptor = lookup;
                }
            }
            return readClassDescriptor;
        } catch (ClassNotFoundException unused) {
            return readClassDescriptor;
        }
    }
}
