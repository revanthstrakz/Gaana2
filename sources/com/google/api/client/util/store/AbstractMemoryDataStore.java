package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Maps;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AbstractMemoryDataStore<V extends Serializable> extends AbstractDataStore<V> {
    HashMap<String, byte[]> keyValueMap = Maps.newHashMap();
    private final Lock lock = new ReentrantLock();

    /* Access modifiers changed, original: 0000 */
    public void save() throws IOException {
    }

    protected AbstractMemoryDataStore(DataStoreFactory dataStoreFactory, String str) {
        super(dataStoreFactory, str);
    }

    public final Set<String> keySet() throws IOException {
        this.lock.lock();
        try {
            Set<String> unmodifiableSet = Collections.unmodifiableSet(this.keyValueMap.keySet());
            return unmodifiableSet;
        } finally {
            this.lock.unlock();
        }
    }

    public final Collection<V> values() throws IOException {
        this.lock.lock();
        try {
            ArrayList newArrayList = Lists.newArrayList();
            for (byte[] deserialize : this.keyValueMap.values()) {
                newArrayList.add(IOUtils.deserialize(deserialize));
            }
            Collection<V> unmodifiableList = Collections.unmodifiableList(newArrayList);
            return unmodifiableList;
        } finally {
            this.lock.unlock();
        }
    }

    public final V get(String str) throws IOException {
        if (str == null) {
            return null;
        }
        this.lock.lock();
        try {
            V deserialize = IOUtils.deserialize((byte[]) this.keyValueMap.get(str));
            return deserialize;
        } finally {
            this.lock.unlock();
        }
    }

    public final DataStore<V> set(String str, V v) throws IOException {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(v);
        this.lock.lock();
        try {
            this.keyValueMap.put(str, IOUtils.serialize(v));
            save();
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public DataStore<V> delete(String str) throws IOException {
        if (str == null) {
            return this;
        }
        this.lock.lock();
        try {
            this.keyValueMap.remove(str);
            save();
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public final DataStore<V> clear() throws IOException {
        this.lock.lock();
        try {
            this.keyValueMap.clear();
            save();
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean containsKey(String str) throws IOException {
        if (str == null) {
            return false;
        }
        this.lock.lock();
        try {
            boolean containsKey = this.keyValueMap.containsKey(str);
            return containsKey;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean containsValue(V v) throws IOException {
        if (v == null) {
            return false;
        }
        this.lock.lock();
        try {
            byte[] serialize = IOUtils.serialize(v);
            for (byte[] equals : this.keyValueMap.values()) {
                if (Arrays.equals(serialize, equals)) {
                    return true;
                }
            }
            this.lock.unlock();
            return false;
        } finally {
            this.lock.unlock();
        }
    }

    public boolean isEmpty() throws IOException {
        this.lock.lock();
        try {
            boolean isEmpty = this.keyValueMap.isEmpty();
            return isEmpty;
        } finally {
            this.lock.unlock();
        }
    }

    public int size() throws IOException {
        this.lock.lock();
        try {
            int size = this.keyValueMap.size();
            return size;
        } finally {
            this.lock.unlock();
        }
    }

    public String toString() {
        return DataStoreUtils.toString(this);
    }
}
