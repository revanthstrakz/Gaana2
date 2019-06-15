package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.upstream.DataSource.Factory;

final /* synthetic */ class DummyDataSource$$Lambda$0 implements Factory {
    static final Factory $instance = new DummyDataSource$$Lambda$0();

    private DummyDataSource$$Lambda$0() {
    }

    public DataSource createDataSource() {
        return new DummyDataSource();
    }
}
