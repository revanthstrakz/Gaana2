package com.gaana.lrc;

import java.util.List;

public interface ILrcView {

    public interface LrcViewListener {
        void onLrcClicked();

        void onLrcScrollStateChanged(boolean z);

        void onLrcSeeked(int i, LrcRow lrcRow);
    }

    void seekLrcToTime(long j);

    void setListener(LrcViewListener lrcViewListener);

    void setLrc(List<LrcRow> list);
}
