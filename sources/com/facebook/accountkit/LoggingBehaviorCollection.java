package com.facebook.accountkit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class LoggingBehaviorCollection {
    private final HashSet<LoggingBehavior> loggingBehaviors = new HashSet(Collections.singleton(LoggingBehavior.DEVELOPER_ERRORS));

    public void add(LoggingBehavior loggingBehavior) {
        synchronized (this.loggingBehaviors) {
            this.loggingBehaviors.add(loggingBehavior);
        }
    }

    public void clear() {
        synchronized (this.loggingBehaviors) {
            this.loggingBehaviors.clear();
        }
    }

    public Set<LoggingBehavior> get() {
        Set unmodifiableSet;
        synchronized (this.loggingBehaviors) {
            unmodifiableSet = Collections.unmodifiableSet(new HashSet(this.loggingBehaviors));
        }
        return unmodifiableSet;
    }

    public boolean isEnabled(LoggingBehavior loggingBehavior) {
        boolean contains;
        synchronized (this.loggingBehaviors) {
            contains = this.loggingBehaviors.contains(loggingBehavior);
        }
        return contains;
    }

    public void remove(LoggingBehavior loggingBehavior) {
        synchronized (this.loggingBehaviors) {
            this.loggingBehaviors.remove(loggingBehavior);
        }
    }
}
