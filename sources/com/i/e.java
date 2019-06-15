package com.i;

import com.gaana.models.BusinessObject;

public class e {

    public interface a {
        void onDataRetrieved(Object obj, boolean z);

        void onErrorResponse(BusinessObject businessObject);
    }

    public interface b {
        void onDataRetrieved(Object obj, int i, boolean z);
    }
}
