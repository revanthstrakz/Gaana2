package com.til.colombia.android.network;

public enum ErrorCode {
    INVALID_REQUEST("Invalid request"),
    INTERNAL_ERROR("An internal error occurred while fetching"),
    CONNECTION_ERROR("Socket timeout exception"),
    NETWORK_ERROR("Network failure. Check your connection"),
    ADE_ERROR("Failed response from server"),
    VAST_PARSE_ERROR("Failed to download or parse vast / No media files"),
    MEDIA_DOWNLOAD_ERROR("Failed to download media files");
    
    private String error;

    private ErrorCode(String str) {
        this.error = str;
    }

    public final String toString() {
        return this.error;
    }

    public final void setMessage(String str) {
        this.error = str;
    }
}
