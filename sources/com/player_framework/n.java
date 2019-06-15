package com.player_framework;

import com.constants.Constants.ErrorType;

public interface n {

    public interface a {
        void onPlayNext(boolean z, boolean z2);

        void onPlayPrevious(boolean z, boolean z2);
    }

    public interface b extends n {
        void seekTo(int i);
    }

    void displayErrorDialog(String str, ErrorType errorType);

    void displayErrorToast(String str, int i);

    void onPlayNext(boolean z, boolean z2);

    void onPlayPrevious(boolean z, boolean z2);

    void onPlayerPause();

    void onPlayerPlay();

    void onPlayerRepeatReset(boolean z);

    void onPlayerResume();

    void onPlayerStop();

    void onStreamingQualityChanged(int i);
}
