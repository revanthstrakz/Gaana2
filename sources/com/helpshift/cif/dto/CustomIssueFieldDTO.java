package com.helpshift.cif.dto;

import java.io.Serializable;

public class CustomIssueFieldDTO implements Serializable {
    public final String a;
    public final String b;
    public final String[] c;

    public CustomIssueFieldDTO(String str, String str2, String[] strArr) {
        this.a = str;
        this.b = str2;
        this.c = strArr;
    }
}
