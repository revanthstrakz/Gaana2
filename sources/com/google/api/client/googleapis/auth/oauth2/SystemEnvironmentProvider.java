package com.google.api.client.googleapis.auth.oauth2;

class SystemEnvironmentProvider {
    static final SystemEnvironmentProvider INSTANCE = new SystemEnvironmentProvider();

    SystemEnvironmentProvider() {
    }

    /* Access modifiers changed, original: 0000 */
    public String getEnv(String str) {
        return System.getenv(str);
    }
}
