package com.til.colombia.android.utils;

import com.til.colombia.android.service.CmEntity;
import java.util.HashMap;
import java.util.Map;

public class CmFeedUtil {
    private Map<String, CmEntity> entityMap = new HashMap();

    public void putCmEntity(CmEntity cmEntity) {
        this.entityMap.put(cmEntity.getUid(), cmEntity);
    }

    public CmEntity getCmEntity(String str) {
        return (CmEntity) this.entityMap.get(str);
    }

    public void remove(String str) {
        try {
            this.entityMap.remove(str);
        } catch (Exception unused) {
        }
    }

    public void clear() {
        this.entityMap.clear();
    }
}
