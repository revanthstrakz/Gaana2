package com.appsflyer.a;

import java.util.Scanner;

public class b {
    private String a;
    private String b;
    private String c;
    private String d;

    public b(String str, String str2, String str3) {
        this.c = str;
        this.b = str2;
        this.a = str3;
    }

    public b(char[] cArr) {
        Scanner scanner = new Scanner(new String(cArr));
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if (nextLine.startsWith("url=")) {
                this.c = nextLine.substring(4).trim();
            } else if (nextLine.startsWith("version=")) {
                this.a = nextLine.substring(8).trim();
            } else if (nextLine.startsWith("data=")) {
                this.b = nextLine.substring(5).trim();
            }
        }
        scanner.close();
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }
}
