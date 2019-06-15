package com.helpshift.cif;

import com.helpshift.cif.dto.CustomIssueFieldDTO;
import com.helpshift.common.c;
import com.helpshift.common.domain.e;
import com.helpshift.common.domain.f;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.common.platform.n;
import com.helpshift.common.platform.p;
import com.helpshift.util.l;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class a {
    private e a;
    private com.helpshift.cif.a.a b;
    private n c;

    public a(e eVar, p pVar) {
        this.a = eVar;
        this.b = pVar.i();
        this.c = pVar.n();
    }

    public void a(final Map<String, String[]> map) {
        this.a.b(new f() {
            public void a() {
                a.this.b.a(a.this.b(map));
            }
        });
    }

    public Object a() {
        ArrayList a = this.b.a();
        if (a == null || a.size() == 0) {
            return null;
        }
        Object e;
        try {
            e = this.c.e(a);
        } catch (RootAPIException e2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Exception when jsonify data : ");
            stringBuilder.append(e2.getMessage());
            l.c("Helpshift_CIF_DM", stringBuilder.toString());
            e = null;
        }
        return e;
    }

    private ArrayList<CustomIssueFieldDTO> b(Map<String, String[]> map) {
        if (map == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : map.keySet()) {
            if (!c.a(str)) {
                String[] strArr = (String[]) map.get(str);
                if (strArr != null) {
                    if (strArr.length >= 2) {
                        String str2 = strArr[0];
                        if (!c.a(str2)) {
                            arrayList.add(new CustomIssueFieldDTO(str, str2, (String[]) Arrays.copyOfRange(strArr, 1, strArr.length)));
                        }
                    }
                }
            }
        }
        return arrayList;
    }
}
