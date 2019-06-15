package com.helpshift.common.platform;

import com.helpshift.meta.a.a;
import com.helpshift.meta.dto.BreadCrumbDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class i implements a {
    private o a;

    public i(o oVar) {
        this.a = oVar;
    }

    public ArrayList<BreadCrumbDTO> a() {
        Object b = this.a.b("key_bread_crumb_storage");
        return b != null ? (ArrayList) b : null;
    }

    public void a(HashMap<String, Serializable> hashMap) {
        this.a.a("key_custom_meta_storage", (Serializable) hashMap);
    }

    public HashMap<String, Serializable> b() {
        Object b = this.a.b("key_custom_meta_storage");
        return b != null ? (HashMap) b : null;
    }
}
