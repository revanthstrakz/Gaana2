package com.google.api.client.util.store;

import java.io.IOException;

public final class DataStoreUtils {
    public static String toString(DataStore<?> dataStore) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('{');
            Object obj = 1;
            for (String str : dataStore.keySet()) {
                if (obj != null) {
                    obj = null;
                } else {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(str);
                stringBuilder.append('=');
                stringBuilder.append(dataStore.get(str));
            }
            stringBuilder.append('}');
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private DataStoreUtils() {
    }
}
