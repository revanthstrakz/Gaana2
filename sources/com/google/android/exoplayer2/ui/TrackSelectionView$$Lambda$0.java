package com.google.android.exoplayer2.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class TrackSelectionView$$Lambda$0 implements OnClickListener {
    private final TrackSelectionView arg$1;

    TrackSelectionView$$Lambda$0(TrackSelectionView trackSelectionView) {
        this.arg$1 = trackSelectionView;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.applySelection();
    }
}
