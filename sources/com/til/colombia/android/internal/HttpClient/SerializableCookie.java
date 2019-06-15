package com.til.colombia.android.internal.HttpClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpCookie;

public class SerializableCookie implements Serializable {
    private static final long serialVersionUID = 2692761033530013901L;
    private transient HttpCookie clientCookie;
    private final transient HttpCookie cookie;

    public SerializableCookie(HttpCookie httpCookie) {
        this.cookie = httpCookie;
    }

    public HttpCookie getCookie() {
        return this.clientCookie != null ? this.clientCookie : this.cookie;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.cookie.getName());
        objectOutputStream.writeObject(this.cookie.getValue());
        objectOutputStream.writeObject(this.cookie.getComment());
        objectOutputStream.writeObject(this.cookie.getDomain());
        objectOutputStream.writeObject(Long.valueOf(this.cookie.getMaxAge()));
        objectOutputStream.writeObject(this.cookie.getPath());
        objectOutputStream.writeInt(this.cookie.getVersion());
        objectOutputStream.writeBoolean(this.cookie.getSecure());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.clientCookie = new HttpCookie((String) objectInputStream.readObject(), (String) objectInputStream.readObject());
        this.clientCookie.setComment((String) objectInputStream.readObject());
        this.clientCookie.setDomain((String) objectInputStream.readObject());
        this.clientCookie.setMaxAge(((Long) objectInputStream.readObject()).longValue());
        this.clientCookie.setPath((String) objectInputStream.readObject());
        this.clientCookie.setVersion(objectInputStream.readInt());
        this.clientCookie.setSecure(objectInputStream.readBoolean());
    }
}
