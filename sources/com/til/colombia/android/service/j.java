package com.til.colombia.android.service;

final class j implements Runnable {
    final /* synthetic */ Exception a;
    final /* synthetic */ AdRequestResponse b;

    j(AdRequestResponse adRequestResponse, Exception exception) {
        this.b = adRequestResponse;
        this.a = exception;
    }

    public final void run() {
        if (this.b.adListener != null) {
            this.b.adListener.onItemRequestFailed(this.b.colombiaAdRequest, this.a);
        }
    }
}
