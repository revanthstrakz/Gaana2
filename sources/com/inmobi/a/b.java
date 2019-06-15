package com.inmobi.a;

import com.google.api.client.http.HttpMethods;
import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.utilities.uid.d;

public final class b extends c {
    private int a;
    private int b;

    b(String str, int i, int i2, d dVar) {
        super(HttpMethods.POST, str, true, dVar);
        this.a = i;
        this.b = i2;
    }
}
