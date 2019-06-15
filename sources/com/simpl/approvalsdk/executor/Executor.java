package com.simpl.approvalsdk.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Executor {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static ExecutorService get() {
        return executor;
    }
}
