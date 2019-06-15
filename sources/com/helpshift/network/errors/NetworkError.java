package com.helpshift.network.errors;

import com.helpshift.common.domain.network.j;

public class NetworkError extends Exception {
    private Integer a = j.b;

    public NetworkError(Integer num) {
        this.a = num;
    }

    public NetworkError(Integer num, String str) {
        super(str);
        this.a = num;
    }

    public NetworkError(String str, Throwable th) {
        super(str, th);
    }

    public NetworkError(Integer num, Throwable th) {
        super(th);
        this.a = num;
    }

    public NetworkError(Throwable th) {
        super(th);
    }

    public Integer a() {
        return this.a;
    }
}
