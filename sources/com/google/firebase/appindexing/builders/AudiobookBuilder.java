package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;

public class AudiobookBuilder extends IndexableBuilder<AudiobookBuilder> {
    AudiobookBuilder() {
        super("Audiobook");
    }

    public AudiobookBuilder setAuthor(@NonNull PersonBuilder... personBuilderArr) {
        return (AudiobookBuilder) put("author", (IndexableBuilder[]) personBuilderArr);
    }

    public AudiobookBuilder setReadBy(@NonNull PersonBuilder... personBuilderArr) {
        return (AudiobookBuilder) put("readBy", (IndexableBuilder[]) personBuilderArr);
    }
}
