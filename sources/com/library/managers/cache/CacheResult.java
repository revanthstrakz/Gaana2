package com.library.managers.cache;

public class CacheResult {
    String mMessage;
    int mResponse;

    public CacheResult(int i) {
        this.mResponse = i;
    }

    public Boolean isSuccess() {
        return Boolean.valueOf(this.mResponse == 0);
    }

    public String getMessage() {
        switch (this.mResponse) {
            case 0:
                this.mMessage = "";
                break;
            case 1:
                this.mMessage = "Insufficient storage.";
                break;
            case 2:
                this.mMessage = "SD Card is not available. Please try later.";
                break;
            case 3:
                this.mMessage = "";
                break;
        }
        return this.mMessage;
    }
}
