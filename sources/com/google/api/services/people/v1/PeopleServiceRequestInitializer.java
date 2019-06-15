package com.google.api.services.people.v1;

import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.googleapis.services.json.CommonGoogleJsonClientRequestInitializer;
import java.io.IOException;

public class PeopleServiceRequestInitializer extends CommonGoogleJsonClientRequestInitializer {
    /* Access modifiers changed, original: protected */
    public void initializePeopleServiceRequest(PeopleServiceRequest<?> peopleServiceRequest) throws IOException {
    }

    public PeopleServiceRequestInitializer(String str) {
        super(str);
    }

    public PeopleServiceRequestInitializer(String str, String str2) {
        super(str, str2);
    }

    public final void initializeJsonRequest(AbstractGoogleJsonClientRequest<?> abstractGoogleJsonClientRequest) throws IOException {
        super.initializeJsonRequest(abstractGoogleJsonClientRequest);
        initializePeopleServiceRequest((PeopleServiceRequest) abstractGoogleJsonClientRequest);
    }
}
