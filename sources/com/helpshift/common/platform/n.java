package com.helpshift.common.platform;

import com.helpshift.analytics.b.a;
import com.helpshift.cif.dto.CustomIssueFieldDTO;
import com.helpshift.meta.dto.BreadCrumbDTO;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface n {
    String a(String str, String str2);

    String a(Collection collection);

    String a(Map<String, Object> map);

    Object b(Map<String, Object> map);

    String b(List<a> list);

    Object c(List<BreadCrumbDTO> list);

    Object c(Map<String, Serializable> map);

    Object d(List<com.helpshift.meta.dto.a> list);

    Object e(List<CustomIssueFieldDTO> list);
}
