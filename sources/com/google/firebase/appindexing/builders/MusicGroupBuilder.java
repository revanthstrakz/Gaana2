package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;
import com.moengage.ActionMapperConstants;

public final class MusicGroupBuilder extends IndexableBuilder<MusicGroupBuilder> {
    MusicGroupBuilder() {
        super("MusicGroup");
    }

    public final MusicGroupBuilder setGenre(@NonNull String str) {
        return (MusicGroupBuilder) put("genre", str);
    }

    public final MusicGroupBuilder setTrack(@NonNull MusicRecordingBuilder... musicRecordingBuilderArr) {
        return (MusicGroupBuilder) put(ActionMapperConstants.KEY_TRACK, (IndexableBuilder[]) musicRecordingBuilderArr);
    }

    public final MusicGroupBuilder setAlbum(@NonNull MusicAlbumBuilder... musicAlbumBuilderArr) {
        return (MusicGroupBuilder) put("album", (IndexableBuilder[]) musicAlbumBuilderArr);
    }
}
