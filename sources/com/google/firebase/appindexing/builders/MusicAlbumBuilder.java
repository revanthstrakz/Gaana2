package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;
import com.moengage.ActionMapperConstants;

public final class MusicAlbumBuilder extends IndexableBuilder<MusicAlbumBuilder> {
    MusicAlbumBuilder() {
        super("MusicAlbum");
    }

    public final MusicAlbumBuilder setByArtist(@NonNull MusicGroupBuilder musicGroupBuilder) {
        return (MusicAlbumBuilder) put("byArtist", musicGroupBuilder);
    }

    public final MusicAlbumBuilder setNumTracks(int i) {
        return (MusicAlbumBuilder) put("numTracks", (long) i);
    }

    public final MusicAlbumBuilder setTrack(@NonNull MusicRecordingBuilder... musicRecordingBuilderArr) {
        return (MusicAlbumBuilder) put(ActionMapperConstants.KEY_TRACK, (IndexableBuilder[]) musicRecordingBuilderArr);
    }
}
