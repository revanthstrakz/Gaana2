package com.comscore.measurement;

class a implements Runnable {
    final /* synthetic */ Measurement a;
    final /* synthetic */ MeasurementDispatcher b;

    a(MeasurementDispatcher measurementDispatcher, Measurement measurement) {
        this.b = measurementDispatcher;
        this.a = measurement;
    }

    public void run() {
        this.b.b(this.a);
    }
}
