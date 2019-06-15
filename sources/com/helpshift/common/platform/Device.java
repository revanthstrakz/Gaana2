package com.helpshift.common.platform;

import com.helpshift.meta.dto.b;
import java.util.Locale;

public interface Device {

    public enum PermissionState {
        AVAILABLE,
        UNAVAILABLE,
        REQUESTABLE
    }

    public enum PermissionType {
        READ_STORAGE,
        WRITE_STORAGE
    }

    PermissionState a(PermissionType permissionType);

    String a();

    void a(Locale locale);

    String b();

    String c();

    int d();

    String e();

    String f();

    String g();

    String h();

    String i();

    Locale j();

    String k();

    String l();

    String m();

    String n();

    String o();

    String p();

    String q();

    String r();

    b s();
}
