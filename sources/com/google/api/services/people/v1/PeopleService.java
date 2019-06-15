package com.google.api.services.people.v1;

import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.google.api.services.people.v1.model.BatchGetContactGroupsResponse;
import com.google.api.services.people.v1.model.ContactGroup;
import com.google.api.services.people.v1.model.CreateContactGroupRequest;
import com.google.api.services.people.v1.model.Empty;
import com.google.api.services.people.v1.model.GetPeopleResponse;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.ListContactGroupsResponse;
import com.google.api.services.people.v1.model.ModifyContactGroupMembersRequest;
import com.google.api.services.people.v1.model.ModifyContactGroupMembersResponse;
import com.google.api.services.people.v1.model.Person;
import com.google.api.services.people.v1.model.UpdateContactGroupRequest;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class PeopleService extends AbstractGoogleJsonClient {
    public static final String DEFAULT_BASE_URL = "https://people.googleapis.com/";
    public static final String DEFAULT_BATCH_PATH = "batch";
    public static final String DEFAULT_ROOT_URL = "https://people.googleapis.com/";
    public static final String DEFAULT_SERVICE_PATH = "";

    public class ContactGroups {

        public class Members {

            public class Modify extends PeopleServiceRequest<ModifyContactGroupMembersResponse> {
                private static final String REST_PATH = "v1/{+resourceName}/members:modify";
                private final Pattern RESOURCE_NAME_PATTERN = Pattern.compile("^contactGroups/[^/]+$");
                @Key
                private String resourceName;

                protected Modify(String str, ModifyContactGroupMembersRequest modifyContactGroupMembersRequest) {
                    super(PeopleService.this, HttpMethods.POST, REST_PATH, modifyContactGroupMembersRequest, ModifyContactGroupMembersResponse.class);
                    this.resourceName = (String) Preconditions.checkNotNull(str, "Required parameter resourceName must be specified.");
                    if (!PeopleService.this.getSuppressPatternChecks()) {
                        Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^contactGroups/[^/]+$");
                    }
                }

                public Modify set$Xgafv(String str) {
                    return (Modify) super.set$Xgafv(str);
                }

                public Modify setAccessToken(String str) {
                    return (Modify) super.setAccessToken(str);
                }

                public Modify setAlt(String str) {
                    return (Modify) super.setAlt(str);
                }

                public Modify setBearerToken(String str) {
                    return (Modify) super.setBearerToken(str);
                }

                public Modify setCallback(String str) {
                    return (Modify) super.setCallback(str);
                }

                public Modify setFields(String str) {
                    return (Modify) super.setFields(str);
                }

                public Modify setKey(String str) {
                    return (Modify) super.setKey(str);
                }

                public Modify setOauthToken(String str) {
                    return (Modify) super.setOauthToken(str);
                }

                public Modify setPp(Boolean bool) {
                    return (Modify) super.setPp(bool);
                }

                public Modify setPrettyPrint(Boolean bool) {
                    return (Modify) super.setPrettyPrint(bool);
                }

                public Modify setQuotaUser(String str) {
                    return (Modify) super.setQuotaUser(str);
                }

                public Modify setUploadType(String str) {
                    return (Modify) super.setUploadType(str);
                }

                public Modify setUploadProtocol(String str) {
                    return (Modify) super.setUploadProtocol(str);
                }

                public String getResourceName() {
                    return this.resourceName;
                }

                public Modify setResourceName(String str) {
                    if (!PeopleService.this.getSuppressPatternChecks()) {
                        Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^contactGroups/[^/]+$");
                    }
                    this.resourceName = str;
                    return this;
                }

                public Modify set(String str, Object obj) {
                    return (Modify) super.set(str, obj);
                }
            }

            public Modify modify(String str, ModifyContactGroupMembersRequest modifyContactGroupMembersRequest) throws IOException {
                Modify modify = new Modify(str, modifyContactGroupMembersRequest);
                PeopleService.this.initialize(modify);
                return modify;
            }
        }

        public class BatchGet extends PeopleServiceRequest<BatchGetContactGroupsResponse> {
            private static final String REST_PATH = "v1/contactGroups:batchGet";
            @Key
            private Integer maxMembers;
            @Key
            private java.util.List<String> resourceNames;

            protected BatchGet() {
                super(PeopleService.this, HttpMethods.GET, REST_PATH, null, BatchGetContactGroupsResponse.class);
            }

            public HttpResponse executeUsingHead() throws IOException {
                return super.executeUsingHead();
            }

            public HttpRequest buildHttpRequestUsingHead() throws IOException {
                return super.buildHttpRequestUsingHead();
            }

            public BatchGet set$Xgafv(String str) {
                return (BatchGet) super.set$Xgafv(str);
            }

            public BatchGet setAccessToken(String str) {
                return (BatchGet) super.setAccessToken(str);
            }

            public BatchGet setAlt(String str) {
                return (BatchGet) super.setAlt(str);
            }

            public BatchGet setBearerToken(String str) {
                return (BatchGet) super.setBearerToken(str);
            }

            public BatchGet setCallback(String str) {
                return (BatchGet) super.setCallback(str);
            }

            public BatchGet setFields(String str) {
                return (BatchGet) super.setFields(str);
            }

            public BatchGet setKey(String str) {
                return (BatchGet) super.setKey(str);
            }

            public BatchGet setOauthToken(String str) {
                return (BatchGet) super.setOauthToken(str);
            }

            public BatchGet setPp(Boolean bool) {
                return (BatchGet) super.setPp(bool);
            }

            public BatchGet setPrettyPrint(Boolean bool) {
                return (BatchGet) super.setPrettyPrint(bool);
            }

            public BatchGet setQuotaUser(String str) {
                return (BatchGet) super.setQuotaUser(str);
            }

            public BatchGet setUploadType(String str) {
                return (BatchGet) super.setUploadType(str);
            }

            public BatchGet setUploadProtocol(String str) {
                return (BatchGet) super.setUploadProtocol(str);
            }

            public Integer getMaxMembers() {
                return this.maxMembers;
            }

            public BatchGet setMaxMembers(Integer num) {
                this.maxMembers = num;
                return this;
            }

            public java.util.List<String> getResourceNames() {
                return this.resourceNames;
            }

            public BatchGet setResourceNames(java.util.List<String> list) {
                this.resourceNames = list;
                return this;
            }

            public BatchGet set(String str, Object obj) {
                return (BatchGet) super.set(str, obj);
            }
        }

        public class Create extends PeopleServiceRequest<ContactGroup> {
            private static final String REST_PATH = "v1/contactGroups";

            protected Create(CreateContactGroupRequest createContactGroupRequest) {
                super(PeopleService.this, HttpMethods.POST, REST_PATH, createContactGroupRequest, ContactGroup.class);
            }

            public Create set$Xgafv(String str) {
                return (Create) super.set$Xgafv(str);
            }

            public Create setAccessToken(String str) {
                return (Create) super.setAccessToken(str);
            }

            public Create setAlt(String str) {
                return (Create) super.setAlt(str);
            }

            public Create setBearerToken(String str) {
                return (Create) super.setBearerToken(str);
            }

            public Create setCallback(String str) {
                return (Create) super.setCallback(str);
            }

            public Create setFields(String str) {
                return (Create) super.setFields(str);
            }

            public Create setKey(String str) {
                return (Create) super.setKey(str);
            }

            public Create setOauthToken(String str) {
                return (Create) super.setOauthToken(str);
            }

            public Create setPp(Boolean bool) {
                return (Create) super.setPp(bool);
            }

            public Create setPrettyPrint(Boolean bool) {
                return (Create) super.setPrettyPrint(bool);
            }

            public Create setQuotaUser(String str) {
                return (Create) super.setQuotaUser(str);
            }

            public Create setUploadType(String str) {
                return (Create) super.setUploadType(str);
            }

            public Create setUploadProtocol(String str) {
                return (Create) super.setUploadProtocol(str);
            }

            public Create set(String str, Object obj) {
                return (Create) super.set(str, obj);
            }
        }

        public class Delete extends PeopleServiceRequest<Empty> {
            private static final String REST_PATH = "v1/{+resourceName}";
            private final Pattern RESOURCE_NAME_PATTERN = Pattern.compile("^contactGroups/[^/]+$");
            @Key
            private Boolean deleteContacts;
            @Key
            private String resourceName;

            protected Delete(String str) {
                super(PeopleService.this, HttpMethods.DELETE, REST_PATH, null, Empty.class);
                this.resourceName = (String) Preconditions.checkNotNull(str, "Required parameter resourceName must be specified.");
                if (!PeopleService.this.getSuppressPatternChecks()) {
                    Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^contactGroups/[^/]+$");
                }
            }

            public Delete set$Xgafv(String str) {
                return (Delete) super.set$Xgafv(str);
            }

            public Delete setAccessToken(String str) {
                return (Delete) super.setAccessToken(str);
            }

            public Delete setAlt(String str) {
                return (Delete) super.setAlt(str);
            }

            public Delete setBearerToken(String str) {
                return (Delete) super.setBearerToken(str);
            }

            public Delete setCallback(String str) {
                return (Delete) super.setCallback(str);
            }

            public Delete setFields(String str) {
                return (Delete) super.setFields(str);
            }

            public Delete setKey(String str) {
                return (Delete) super.setKey(str);
            }

            public Delete setOauthToken(String str) {
                return (Delete) super.setOauthToken(str);
            }

            public Delete setPp(Boolean bool) {
                return (Delete) super.setPp(bool);
            }

            public Delete setPrettyPrint(Boolean bool) {
                return (Delete) super.setPrettyPrint(bool);
            }

            public Delete setQuotaUser(String str) {
                return (Delete) super.setQuotaUser(str);
            }

            public Delete setUploadType(String str) {
                return (Delete) super.setUploadType(str);
            }

            public Delete setUploadProtocol(String str) {
                return (Delete) super.setUploadProtocol(str);
            }

            public String getResourceName() {
                return this.resourceName;
            }

            public Delete setResourceName(String str) {
                if (!PeopleService.this.getSuppressPatternChecks()) {
                    Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^contactGroups/[^/]+$");
                }
                this.resourceName = str;
                return this;
            }

            public Boolean getDeleteContacts() {
                return this.deleteContacts;
            }

            public Delete setDeleteContacts(Boolean bool) {
                this.deleteContacts = bool;
                return this;
            }

            public Delete set(String str, Object obj) {
                return (Delete) super.set(str, obj);
            }
        }

        public class Get extends PeopleServiceRequest<ContactGroup> {
            private static final String REST_PATH = "v1/{+resourceName}";
            private final Pattern RESOURCE_NAME_PATTERN = Pattern.compile("^contactGroups/[^/]+$");
            @Key
            private Integer maxMembers;
            @Key
            private String resourceName;

            protected Get(String str) {
                super(PeopleService.this, HttpMethods.GET, REST_PATH, null, ContactGroup.class);
                this.resourceName = (String) Preconditions.checkNotNull(str, "Required parameter resourceName must be specified.");
                if (!PeopleService.this.getSuppressPatternChecks()) {
                    Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^contactGroups/[^/]+$");
                }
            }

            public HttpResponse executeUsingHead() throws IOException {
                return super.executeUsingHead();
            }

            public HttpRequest buildHttpRequestUsingHead() throws IOException {
                return super.buildHttpRequestUsingHead();
            }

            public Get set$Xgafv(String str) {
                return (Get) super.set$Xgafv(str);
            }

            public Get setAccessToken(String str) {
                return (Get) super.setAccessToken(str);
            }

            public Get setAlt(String str) {
                return (Get) super.setAlt(str);
            }

            public Get setBearerToken(String str) {
                return (Get) super.setBearerToken(str);
            }

            public Get setCallback(String str) {
                return (Get) super.setCallback(str);
            }

            public Get setFields(String str) {
                return (Get) super.setFields(str);
            }

            public Get setKey(String str) {
                return (Get) super.setKey(str);
            }

            public Get setOauthToken(String str) {
                return (Get) super.setOauthToken(str);
            }

            public Get setPp(Boolean bool) {
                return (Get) super.setPp(bool);
            }

            public Get setPrettyPrint(Boolean bool) {
                return (Get) super.setPrettyPrint(bool);
            }

            public Get setQuotaUser(String str) {
                return (Get) super.setQuotaUser(str);
            }

            public Get setUploadType(String str) {
                return (Get) super.setUploadType(str);
            }

            public Get setUploadProtocol(String str) {
                return (Get) super.setUploadProtocol(str);
            }

            public String getResourceName() {
                return this.resourceName;
            }

            public Get setResourceName(String str) {
                if (!PeopleService.this.getSuppressPatternChecks()) {
                    Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^contactGroups/[^/]+$");
                }
                this.resourceName = str;
                return this;
            }

            public Integer getMaxMembers() {
                return this.maxMembers;
            }

            public Get setMaxMembers(Integer num) {
                this.maxMembers = num;
                return this;
            }

            public Get set(String str, Object obj) {
                return (Get) super.set(str, obj);
            }
        }

        public class List extends PeopleServiceRequest<ListContactGroupsResponse> {
            private static final String REST_PATH = "v1/contactGroups";
            @Key
            private Integer pageSize;
            @Key
            private String pageToken;
            @Key
            private String syncToken;

            protected List() {
                super(PeopleService.this, HttpMethods.GET, REST_PATH, null, ListContactGroupsResponse.class);
            }

            public HttpResponse executeUsingHead() throws IOException {
                return super.executeUsingHead();
            }

            public HttpRequest buildHttpRequestUsingHead() throws IOException {
                return super.buildHttpRequestUsingHead();
            }

            public List set$Xgafv(String str) {
                return (List) super.set$Xgafv(str);
            }

            public List setAccessToken(String str) {
                return (List) super.setAccessToken(str);
            }

            public List setAlt(String str) {
                return (List) super.setAlt(str);
            }

            public List setBearerToken(String str) {
                return (List) super.setBearerToken(str);
            }

            public List setCallback(String str) {
                return (List) super.setCallback(str);
            }

            public List setFields(String str) {
                return (List) super.setFields(str);
            }

            public List setKey(String str) {
                return (List) super.setKey(str);
            }

            public List setOauthToken(String str) {
                return (List) super.setOauthToken(str);
            }

            public List setPp(Boolean bool) {
                return (List) super.setPp(bool);
            }

            public List setPrettyPrint(Boolean bool) {
                return (List) super.setPrettyPrint(bool);
            }

            public List setQuotaUser(String str) {
                return (List) super.setQuotaUser(str);
            }

            public List setUploadType(String str) {
                return (List) super.setUploadType(str);
            }

            public List setUploadProtocol(String str) {
                return (List) super.setUploadProtocol(str);
            }

            public String getPageToken() {
                return this.pageToken;
            }

            public List setPageToken(String str) {
                this.pageToken = str;
                return this;
            }

            public Integer getPageSize() {
                return this.pageSize;
            }

            public List setPageSize(Integer num) {
                this.pageSize = num;
                return this;
            }

            public String getSyncToken() {
                return this.syncToken;
            }

            public List setSyncToken(String str) {
                this.syncToken = str;
                return this;
            }

            public List set(String str, Object obj) {
                return (List) super.set(str, obj);
            }
        }

        public class Update extends PeopleServiceRequest<ContactGroup> {
            private static final String REST_PATH = "v1/{+resourceName}";
            private final Pattern RESOURCE_NAME_PATTERN = Pattern.compile("^contactGroups/[^/]+$");
            @Key
            private String resourceName;

            protected Update(String str, UpdateContactGroupRequest updateContactGroupRequest) {
                super(PeopleService.this, HttpMethods.PUT, REST_PATH, updateContactGroupRequest, ContactGroup.class);
                this.resourceName = (String) Preconditions.checkNotNull(str, "Required parameter resourceName must be specified.");
                if (!PeopleService.this.getSuppressPatternChecks()) {
                    Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^contactGroups/[^/]+$");
                }
            }

            public Update set$Xgafv(String str) {
                return (Update) super.set$Xgafv(str);
            }

            public Update setAccessToken(String str) {
                return (Update) super.setAccessToken(str);
            }

            public Update setAlt(String str) {
                return (Update) super.setAlt(str);
            }

            public Update setBearerToken(String str) {
                return (Update) super.setBearerToken(str);
            }

            public Update setCallback(String str) {
                return (Update) super.setCallback(str);
            }

            public Update setFields(String str) {
                return (Update) super.setFields(str);
            }

            public Update setKey(String str) {
                return (Update) super.setKey(str);
            }

            public Update setOauthToken(String str) {
                return (Update) super.setOauthToken(str);
            }

            public Update setPp(Boolean bool) {
                return (Update) super.setPp(bool);
            }

            public Update setPrettyPrint(Boolean bool) {
                return (Update) super.setPrettyPrint(bool);
            }

            public Update setQuotaUser(String str) {
                return (Update) super.setQuotaUser(str);
            }

            public Update setUploadType(String str) {
                return (Update) super.setUploadType(str);
            }

            public Update setUploadProtocol(String str) {
                return (Update) super.setUploadProtocol(str);
            }

            public String getResourceName() {
                return this.resourceName;
            }

            public Update setResourceName(String str) {
                if (!PeopleService.this.getSuppressPatternChecks()) {
                    Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^contactGroups/[^/]+$");
                }
                this.resourceName = str;
                return this;
            }

            public Update set(String str, Object obj) {
                return (Update) super.set(str, obj);
            }
        }

        public BatchGet batchGet() throws IOException {
            BatchGet batchGet = new BatchGet();
            PeopleService.this.initialize(batchGet);
            return batchGet;
        }

        public Create create(CreateContactGroupRequest createContactGroupRequest) throws IOException {
            Create create = new Create(createContactGroupRequest);
            PeopleService.this.initialize(create);
            return create;
        }

        public Delete delete(String str) throws IOException {
            Delete delete = new Delete(str);
            PeopleService.this.initialize(delete);
            return delete;
        }

        public Get get(String str) throws IOException {
            Get get = new Get(str);
            PeopleService.this.initialize(get);
            return get;
        }

        public List list() throws IOException {
            List list = new List();
            PeopleService.this.initialize(list);
            return list;
        }

        public Update update(String str, UpdateContactGroupRequest updateContactGroupRequest) throws IOException {
            Update update = new Update(str, updateContactGroupRequest);
            PeopleService.this.initialize(update);
            return update;
        }

        public Members members() {
            return new Members();
        }
    }

    public class People {

        public class Connections {

            public class List extends PeopleServiceRequest<ListConnectionsResponse> {
                private static final String REST_PATH = "v1/{+resourceName}/connections";
                private final Pattern RESOURCE_NAME_PATTERN = Pattern.compile("^people/[^/]+$");
                @Key
                private Integer pageSize;
                @Key
                private String pageToken;
                @Key
                private String personFields;
                @Key("requestMask.includeField")
                private String requestMaskIncludeField;
                @Key
                private Boolean requestSyncToken;
                @Key
                private String resourceName;
                @Key
                private String sortOrder;
                @Key
                private String syncToken;

                protected List(String str) {
                    super(PeopleService.this, HttpMethods.GET, REST_PATH, null, ListConnectionsResponse.class);
                    this.resourceName = (String) Preconditions.checkNotNull(str, "Required parameter resourceName must be specified.");
                    if (!PeopleService.this.getSuppressPatternChecks()) {
                        Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^people/[^/]+$");
                    }
                }

                public HttpResponse executeUsingHead() throws IOException {
                    return super.executeUsingHead();
                }

                public HttpRequest buildHttpRequestUsingHead() throws IOException {
                    return super.buildHttpRequestUsingHead();
                }

                public List set$Xgafv(String str) {
                    return (List) super.set$Xgafv(str);
                }

                public List setAccessToken(String str) {
                    return (List) super.setAccessToken(str);
                }

                public List setAlt(String str) {
                    return (List) super.setAlt(str);
                }

                public List setBearerToken(String str) {
                    return (List) super.setBearerToken(str);
                }

                public List setCallback(String str) {
                    return (List) super.setCallback(str);
                }

                public List setFields(String str) {
                    return (List) super.setFields(str);
                }

                public List setKey(String str) {
                    return (List) super.setKey(str);
                }

                public List setOauthToken(String str) {
                    return (List) super.setOauthToken(str);
                }

                public List setPp(Boolean bool) {
                    return (List) super.setPp(bool);
                }

                public List setPrettyPrint(Boolean bool) {
                    return (List) super.setPrettyPrint(bool);
                }

                public List setQuotaUser(String str) {
                    return (List) super.setQuotaUser(str);
                }

                public List setUploadType(String str) {
                    return (List) super.setUploadType(str);
                }

                public List setUploadProtocol(String str) {
                    return (List) super.setUploadProtocol(str);
                }

                public String getResourceName() {
                    return this.resourceName;
                }

                public List setResourceName(String str) {
                    if (!PeopleService.this.getSuppressPatternChecks()) {
                        Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^people/[^/]+$");
                    }
                    this.resourceName = str;
                    return this;
                }

                public String getSortOrder() {
                    return this.sortOrder;
                }

                public List setSortOrder(String str) {
                    this.sortOrder = str;
                    return this;
                }

                public Boolean getRequestSyncToken() {
                    return this.requestSyncToken;
                }

                public List setRequestSyncToken(Boolean bool) {
                    this.requestSyncToken = bool;
                    return this;
                }

                public String getPageToken() {
                    return this.pageToken;
                }

                public List setPageToken(String str) {
                    this.pageToken = str;
                    return this;
                }

                public Integer getPageSize() {
                    return this.pageSize;
                }

                public List setPageSize(Integer num) {
                    this.pageSize = num;
                    return this;
                }

                public String getRequestMaskIncludeField() {
                    return this.requestMaskIncludeField;
                }

                public List setRequestMaskIncludeField(String str) {
                    this.requestMaskIncludeField = str;
                    return this;
                }

                public String getSyncToken() {
                    return this.syncToken;
                }

                public List setSyncToken(String str) {
                    this.syncToken = str;
                    return this;
                }

                public String getPersonFields() {
                    return this.personFields;
                }

                public List setPersonFields(String str) {
                    this.personFields = str;
                    return this;
                }

                public List set(String str, Object obj) {
                    return (List) super.set(str, obj);
                }
            }

            public List list(String str) throws IOException {
                List list = new List(str);
                PeopleService.this.initialize(list);
                return list;
            }
        }

        public class CreateContact extends PeopleServiceRequest<Person> {
            private static final String REST_PATH = "v1/people:createContact";
            @Key
            private String parent;

            protected CreateContact(Person person) {
                super(PeopleService.this, HttpMethods.POST, REST_PATH, person, Person.class);
            }

            public CreateContact set$Xgafv(String str) {
                return (CreateContact) super.set$Xgafv(str);
            }

            public CreateContact setAccessToken(String str) {
                return (CreateContact) super.setAccessToken(str);
            }

            public CreateContact setAlt(String str) {
                return (CreateContact) super.setAlt(str);
            }

            public CreateContact setBearerToken(String str) {
                return (CreateContact) super.setBearerToken(str);
            }

            public CreateContact setCallback(String str) {
                return (CreateContact) super.setCallback(str);
            }

            public CreateContact setFields(String str) {
                return (CreateContact) super.setFields(str);
            }

            public CreateContact setKey(String str) {
                return (CreateContact) super.setKey(str);
            }

            public CreateContact setOauthToken(String str) {
                return (CreateContact) super.setOauthToken(str);
            }

            public CreateContact setPp(Boolean bool) {
                return (CreateContact) super.setPp(bool);
            }

            public CreateContact setPrettyPrint(Boolean bool) {
                return (CreateContact) super.setPrettyPrint(bool);
            }

            public CreateContact setQuotaUser(String str) {
                return (CreateContact) super.setQuotaUser(str);
            }

            public CreateContact setUploadType(String str) {
                return (CreateContact) super.setUploadType(str);
            }

            public CreateContact setUploadProtocol(String str) {
                return (CreateContact) super.setUploadProtocol(str);
            }

            public String getParent() {
                return this.parent;
            }

            public CreateContact setParent(String str) {
                this.parent = str;
                return this;
            }

            public CreateContact set(String str, Object obj) {
                return (CreateContact) super.set(str, obj);
            }
        }

        public class DeleteContact extends PeopleServiceRequest<Empty> {
            private static final String REST_PATH = "v1/{+resourceName}:deleteContact";
            private final Pattern RESOURCE_NAME_PATTERN = Pattern.compile("^people/[^/]+$");
            @Key
            private String resourceName;

            protected DeleteContact(String str) {
                super(PeopleService.this, HttpMethods.DELETE, REST_PATH, null, Empty.class);
                this.resourceName = (String) Preconditions.checkNotNull(str, "Required parameter resourceName must be specified.");
                if (!PeopleService.this.getSuppressPatternChecks()) {
                    Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^people/[^/]+$");
                }
            }

            public DeleteContact set$Xgafv(String str) {
                return (DeleteContact) super.set$Xgafv(str);
            }

            public DeleteContact setAccessToken(String str) {
                return (DeleteContact) super.setAccessToken(str);
            }

            public DeleteContact setAlt(String str) {
                return (DeleteContact) super.setAlt(str);
            }

            public DeleteContact setBearerToken(String str) {
                return (DeleteContact) super.setBearerToken(str);
            }

            public DeleteContact setCallback(String str) {
                return (DeleteContact) super.setCallback(str);
            }

            public DeleteContact setFields(String str) {
                return (DeleteContact) super.setFields(str);
            }

            public DeleteContact setKey(String str) {
                return (DeleteContact) super.setKey(str);
            }

            public DeleteContact setOauthToken(String str) {
                return (DeleteContact) super.setOauthToken(str);
            }

            public DeleteContact setPp(Boolean bool) {
                return (DeleteContact) super.setPp(bool);
            }

            public DeleteContact setPrettyPrint(Boolean bool) {
                return (DeleteContact) super.setPrettyPrint(bool);
            }

            public DeleteContact setQuotaUser(String str) {
                return (DeleteContact) super.setQuotaUser(str);
            }

            public DeleteContact setUploadType(String str) {
                return (DeleteContact) super.setUploadType(str);
            }

            public DeleteContact setUploadProtocol(String str) {
                return (DeleteContact) super.setUploadProtocol(str);
            }

            public String getResourceName() {
                return this.resourceName;
            }

            public DeleteContact setResourceName(String str) {
                if (!PeopleService.this.getSuppressPatternChecks()) {
                    Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^people/[^/]+$");
                }
                this.resourceName = str;
                return this;
            }

            public DeleteContact set(String str, Object obj) {
                return (DeleteContact) super.set(str, obj);
            }
        }

        public class Get extends PeopleServiceRequest<Person> {
            private static final String REST_PATH = "v1/{+resourceName}";
            private final Pattern RESOURCE_NAME_PATTERN = Pattern.compile("^people/[^/]+$");
            @Key
            private String personFields;
            @Key("requestMask.includeField")
            private String requestMaskIncludeField;
            @Key
            private String resourceName;

            protected Get(String str) {
                super(PeopleService.this, HttpMethods.GET, REST_PATH, null, Person.class);
                this.resourceName = (String) Preconditions.checkNotNull(str, "Required parameter resourceName must be specified.");
                if (!PeopleService.this.getSuppressPatternChecks()) {
                    Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^people/[^/]+$");
                }
            }

            public HttpResponse executeUsingHead() throws IOException {
                return super.executeUsingHead();
            }

            public HttpRequest buildHttpRequestUsingHead() throws IOException {
                return super.buildHttpRequestUsingHead();
            }

            public Get set$Xgafv(String str) {
                return (Get) super.set$Xgafv(str);
            }

            public Get setAccessToken(String str) {
                return (Get) super.setAccessToken(str);
            }

            public Get setAlt(String str) {
                return (Get) super.setAlt(str);
            }

            public Get setBearerToken(String str) {
                return (Get) super.setBearerToken(str);
            }

            public Get setCallback(String str) {
                return (Get) super.setCallback(str);
            }

            public Get setFields(String str) {
                return (Get) super.setFields(str);
            }

            public Get setKey(String str) {
                return (Get) super.setKey(str);
            }

            public Get setOauthToken(String str) {
                return (Get) super.setOauthToken(str);
            }

            public Get setPp(Boolean bool) {
                return (Get) super.setPp(bool);
            }

            public Get setPrettyPrint(Boolean bool) {
                return (Get) super.setPrettyPrint(bool);
            }

            public Get setQuotaUser(String str) {
                return (Get) super.setQuotaUser(str);
            }

            public Get setUploadType(String str) {
                return (Get) super.setUploadType(str);
            }

            public Get setUploadProtocol(String str) {
                return (Get) super.setUploadProtocol(str);
            }

            public String getResourceName() {
                return this.resourceName;
            }

            public Get setResourceName(String str) {
                if (!PeopleService.this.getSuppressPatternChecks()) {
                    Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^people/[^/]+$");
                }
                this.resourceName = str;
                return this;
            }

            public String getPersonFields() {
                return this.personFields;
            }

            public Get setPersonFields(String str) {
                this.personFields = str;
                return this;
            }

            public String getRequestMaskIncludeField() {
                return this.requestMaskIncludeField;
            }

            public Get setRequestMaskIncludeField(String str) {
                this.requestMaskIncludeField = str;
                return this;
            }

            public Get set(String str, Object obj) {
                return (Get) super.set(str, obj);
            }
        }

        public class GetBatchGet extends PeopleServiceRequest<GetPeopleResponse> {
            private static final String REST_PATH = "v1/people:batchGet";
            @Key
            private String personFields;
            @Key("requestMask.includeField")
            private String requestMaskIncludeField;
            @Key
            private List<String> resourceNames;

            protected GetBatchGet() {
                super(PeopleService.this, HttpMethods.GET, REST_PATH, null, GetPeopleResponse.class);
            }

            public HttpResponse executeUsingHead() throws IOException {
                return super.executeUsingHead();
            }

            public HttpRequest buildHttpRequestUsingHead() throws IOException {
                return super.buildHttpRequestUsingHead();
            }

            public GetBatchGet set$Xgafv(String str) {
                return (GetBatchGet) super.set$Xgafv(str);
            }

            public GetBatchGet setAccessToken(String str) {
                return (GetBatchGet) super.setAccessToken(str);
            }

            public GetBatchGet setAlt(String str) {
                return (GetBatchGet) super.setAlt(str);
            }

            public GetBatchGet setBearerToken(String str) {
                return (GetBatchGet) super.setBearerToken(str);
            }

            public GetBatchGet setCallback(String str) {
                return (GetBatchGet) super.setCallback(str);
            }

            public GetBatchGet setFields(String str) {
                return (GetBatchGet) super.setFields(str);
            }

            public GetBatchGet setKey(String str) {
                return (GetBatchGet) super.setKey(str);
            }

            public GetBatchGet setOauthToken(String str) {
                return (GetBatchGet) super.setOauthToken(str);
            }

            public GetBatchGet setPp(Boolean bool) {
                return (GetBatchGet) super.setPp(bool);
            }

            public GetBatchGet setPrettyPrint(Boolean bool) {
                return (GetBatchGet) super.setPrettyPrint(bool);
            }

            public GetBatchGet setQuotaUser(String str) {
                return (GetBatchGet) super.setQuotaUser(str);
            }

            public GetBatchGet setUploadType(String str) {
                return (GetBatchGet) super.setUploadType(str);
            }

            public GetBatchGet setUploadProtocol(String str) {
                return (GetBatchGet) super.setUploadProtocol(str);
            }

            public String getPersonFields() {
                return this.personFields;
            }

            public GetBatchGet setPersonFields(String str) {
                this.personFields = str;
                return this;
            }

            public String getRequestMaskIncludeField() {
                return this.requestMaskIncludeField;
            }

            public GetBatchGet setRequestMaskIncludeField(String str) {
                this.requestMaskIncludeField = str;
                return this;
            }

            public List<String> getResourceNames() {
                return this.resourceNames;
            }

            public GetBatchGet setResourceNames(List<String> list) {
                this.resourceNames = list;
                return this;
            }

            public GetBatchGet set(String str, Object obj) {
                return (GetBatchGet) super.set(str, obj);
            }
        }

        public class UpdateContact extends PeopleServiceRequest<Person> {
            private static final String REST_PATH = "v1/{+resourceName}:updateContact";
            private final Pattern RESOURCE_NAME_PATTERN = Pattern.compile("^people/[^/]+$");
            @Key
            private String resourceName;
            @Key
            private String updatePersonFields;

            protected UpdateContact(String str, Person person) {
                super(PeopleService.this, HttpMethods.PATCH, REST_PATH, person, Person.class);
                this.resourceName = (String) Preconditions.checkNotNull(str, "Required parameter resourceName must be specified.");
                if (!PeopleService.this.getSuppressPatternChecks()) {
                    Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^people/[^/]+$");
                }
            }

            public UpdateContact set$Xgafv(String str) {
                return (UpdateContact) super.set$Xgafv(str);
            }

            public UpdateContact setAccessToken(String str) {
                return (UpdateContact) super.setAccessToken(str);
            }

            public UpdateContact setAlt(String str) {
                return (UpdateContact) super.setAlt(str);
            }

            public UpdateContact setBearerToken(String str) {
                return (UpdateContact) super.setBearerToken(str);
            }

            public UpdateContact setCallback(String str) {
                return (UpdateContact) super.setCallback(str);
            }

            public UpdateContact setFields(String str) {
                return (UpdateContact) super.setFields(str);
            }

            public UpdateContact setKey(String str) {
                return (UpdateContact) super.setKey(str);
            }

            public UpdateContact setOauthToken(String str) {
                return (UpdateContact) super.setOauthToken(str);
            }

            public UpdateContact setPp(Boolean bool) {
                return (UpdateContact) super.setPp(bool);
            }

            public UpdateContact setPrettyPrint(Boolean bool) {
                return (UpdateContact) super.setPrettyPrint(bool);
            }

            public UpdateContact setQuotaUser(String str) {
                return (UpdateContact) super.setQuotaUser(str);
            }

            public UpdateContact setUploadType(String str) {
                return (UpdateContact) super.setUploadType(str);
            }

            public UpdateContact setUploadProtocol(String str) {
                return (UpdateContact) super.setUploadProtocol(str);
            }

            public String getResourceName() {
                return this.resourceName;
            }

            public UpdateContact setResourceName(String str) {
                if (!PeopleService.this.getSuppressPatternChecks()) {
                    Preconditions.checkArgument(this.RESOURCE_NAME_PATTERN.matcher(str).matches(), "Parameter resourceName must conform to the pattern ^people/[^/]+$");
                }
                this.resourceName = str;
                return this;
            }

            public String getUpdatePersonFields() {
                return this.updatePersonFields;
            }

            public UpdateContact setUpdatePersonFields(String str) {
                this.updatePersonFields = str;
                return this;
            }

            public UpdateContact set(String str, Object obj) {
                return (UpdateContact) super.set(str, obj);
            }
        }

        public CreateContact createContact(Person person) throws IOException {
            CreateContact createContact = new CreateContact(person);
            PeopleService.this.initialize(createContact);
            return createContact;
        }

        public DeleteContact deleteContact(String str) throws IOException {
            DeleteContact deleteContact = new DeleteContact(str);
            PeopleService.this.initialize(deleteContact);
            return deleteContact;
        }

        public Get get(String str) throws IOException {
            Get get = new Get(str);
            PeopleService.this.initialize(get);
            return get;
        }

        public GetBatchGet getBatchGet() throws IOException {
            GetBatchGet getBatchGet = new GetBatchGet();
            PeopleService.this.initialize(getBatchGet);
            return getBatchGet;
        }

        public UpdateContact updateContact(String str, Person person) throws IOException {
            UpdateContact updateContact = new UpdateContact(str, person);
            PeopleService.this.initialize(updateContact);
            return updateContact;
        }

        public Connections connections() {
            return new Connections();
        }
    }

    public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {
        public Builder(HttpTransport httpTransport, JsonFactory jsonFactory, HttpRequestInitializer httpRequestInitializer) {
            super(httpTransport, jsonFactory, "https://people.googleapis.com/", "", httpRequestInitializer, false);
            setBatchPath(PeopleService.DEFAULT_BATCH_PATH);
        }

        public PeopleService build() {
            return new PeopleService(this);
        }

        public Builder setRootUrl(String str) {
            return (Builder) super.setRootUrl(str);
        }

        public Builder setServicePath(String str) {
            return (Builder) super.setServicePath(str);
        }

        public Builder setBatchPath(String str) {
            return (Builder) super.setBatchPath(str);
        }

        public Builder setHttpRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
            return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
        }

        public Builder setApplicationName(String str) {
            return (Builder) super.setApplicationName(str);
        }

        public Builder setSuppressPatternChecks(boolean z) {
            return (Builder) super.setSuppressPatternChecks(z);
        }

        public Builder setSuppressRequiredParameterChecks(boolean z) {
            return (Builder) super.setSuppressRequiredParameterChecks(z);
        }

        public Builder setSuppressAllChecks(boolean z) {
            return (Builder) super.setSuppressAllChecks(z);
        }

        public Builder setPeopleServiceRequestInitializer(PeopleServiceRequestInitializer peopleServiceRequestInitializer) {
            return (Builder) super.setGoogleClientRequestInitializer((GoogleClientRequestInitializer) peopleServiceRequestInitializer);
        }

        public Builder setGoogleClientRequestInitializer(GoogleClientRequestInitializer googleClientRequestInitializer) {
            return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
        }
    }

    static {
        boolean z = GoogleUtils.MAJOR_VERSION.intValue() == 1 && GoogleUtils.MINOR_VERSION.intValue() >= 15;
        Preconditions.checkState(z, "You are currently running with version %s of google-api-client. You need at least version 1.15 of google-api-client to run version 1.23.0 of the People API library.", GoogleUtils.VERSION);
    }

    public PeopleService(HttpTransport httpTransport, JsonFactory jsonFactory, HttpRequestInitializer httpRequestInitializer) {
        this(new Builder(httpTransport, jsonFactory, httpRequestInitializer));
    }

    PeopleService(Builder builder) {
        super(builder);
    }

    /* Access modifiers changed, original: protected */
    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
        super.initialize(abstractGoogleClientRequest);
    }

    public ContactGroups contactGroups() {
        return new ContactGroups();
    }

    public People people() {
        return new People();
    }
}
