package com.helpshift.common.platform;

import com.helpshift.cif.a.a;
import com.helpshift.cif.dto.CustomIssueFieldDTO;
import java.io.Serializable;
import java.util.ArrayList;

public class c implements a {
    private o a;

    public c(o oVar) {
        this.a = oVar;
    }

    public void a(ArrayList<CustomIssueFieldDTO> arrayList) {
        Serializable arrayList2;
        if (arrayList2 == null || arrayList2.size() <= 0) {
            arrayList2 = null;
        }
        this.a.a("key_custom_issue_field_storage", arrayList2);
    }

    public ArrayList<CustomIssueFieldDTO> a() {
        Object b = this.a.b("key_custom_issue_field_storage");
        return b instanceof ArrayList ? (ArrayList) b : null;
    }
}
