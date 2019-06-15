package com.comscore.metrics;

public enum EventType {
    VIEW,
    HIDDEN;

    public String toString() {
        return super.toString().toLowerCase();
    }
}
