package com.inmobi.a;

import com.google.api.client.http.HttpMethods;
import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.utilities.uid.d;

public class j extends c {
    private static final String c = "j";
    int a;
    int b;

    j(String str, int i, int i2, d dVar, k kVar) {
        super(HttpMethods.POST, str, true, dVar);
        this.a = i;
        this.b = i2;
        this.o.put("payload", kVar.a().toString());
    }
}
