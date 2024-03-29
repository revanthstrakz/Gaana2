package com.google.api.client.googleapis.testing.auth.oauth2;

import com.facebook.AccessToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.testing.TestUtils;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Beta
public class MockTokenServerTransport extends MockHttpTransport {
    static final String EXPECTED_GRANT_TYPE = "urn:ietf:params:oauth:grant-type:jwt-bearer";
    static final JsonFactory JSON_FACTORY = new JacksonFactory();
    Map<String, String> clients;
    Map<String, String> refreshTokens;
    Map<String, String> serviceAccounts;
    final String tokenServerUrl;

    public MockTokenServerTransport() {
        this(GoogleOAuthConstants.TOKEN_SERVER_URL);
    }

    public MockTokenServerTransport(String str) {
        this.serviceAccounts = new HashMap();
        this.clients = new HashMap();
        this.refreshTokens = new HashMap();
        this.tokenServerUrl = str;
    }

    public void addServiceAccount(String str, String str2) {
        this.serviceAccounts.put(str, str2);
    }

    public void addClient(String str, String str2) {
        this.clients.put(str, str2);
    }

    public void addRefreshToken(String str, String str2) {
        this.refreshTokens.put(str, str2);
    }

    public LowLevelHttpRequest buildRequest(String str, String str2) throws IOException {
        return str2.equals(this.tokenServerUrl) ? new MockLowLevelHttpRequest(str2) {
            public LowLevelHttpResponse execute() throws IOException {
                Object obj;
                Map parseQuery = TestUtils.parseQuery(getContentAsString());
                String str = (String) parseQuery.get("client_id");
                String str2;
                if (str != null) {
                    if (MockTokenServerTransport.this.clients.containsKey(str)) {
                        String str3 = (String) parseQuery.get("client_secret");
                        str = (String) MockTokenServerTransport.this.clients.get(str);
                        if (str3 == null || !str3.equals(str)) {
                            throw new IOException("Client secret not found.");
                        }
                        str2 = (String) parseQuery.get("refresh_token");
                        if (MockTokenServerTransport.this.refreshTokens.containsKey(str2)) {
                            obj = (String) MockTokenServerTransport.this.refreshTokens.get(str2);
                        } else {
                            throw new IOException("Refresh Token not found.");
                        }
                    }
                    throw new IOException("Client ID not found.");
                } else if (parseQuery.containsKey("grant_type")) {
                    if (MockTokenServerTransport.EXPECTED_GRANT_TYPE.equals((String) parseQuery.get("grant_type"))) {
                        JsonWebSignature parse = JsonWebSignature.parse(MockTokenServerTransport.JSON_FACTORY, (String) parseQuery.get("assertion"));
                        str = parse.getPayload().getIssuer();
                        if (MockTokenServerTransport.this.serviceAccounts.containsKey(str)) {
                            str = (String) MockTokenServerTransport.this.serviceAccounts.get(str);
                            str2 = (String) parse.getPayload().get("scope");
                            if (str2 == null || str2.length() == 0) {
                                throw new IOException("Scopes not found.");
                            }
                            obj = str;
                        } else {
                            throw new IOException("Service Account Email not found as issuer.");
                        }
                    }
                    throw new IOException("Unexpected Grant Type.");
                } else {
                    throw new IOException("Unknown token type.");
                }
                GenericJson genericJson = new GenericJson();
                genericJson.setFactory(MockTokenServerTransport.JSON_FACTORY);
                genericJson.put("access_token", obj);
                genericJson.put(AccessToken.EXPIRES_IN_KEY, (Object) Integer.valueOf(3600000));
                genericJson.put("token_type", (Object) "Bearer");
                return new MockLowLevelHttpResponse().setContentType(Json.MEDIA_TYPE).setContent(genericJson.toPrettyString());
            }
        } : super.buildRequest(str, str2);
    }
}
