package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.ParsingLoadable.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class FilteringManifestParser<T extends FilterableManifest<T>> implements Parser<T> {
    private final Parser<T> parser;
    private final List<StreamKey> streamKeys;

    public FilteringManifestParser(Parser<T> parser, List<StreamKey> list) {
        this.parser = parser;
        this.streamKeys = list;
    }

    public T parse(Uri uri, InputStream inputStream) throws IOException {
        FilterableManifest filterableManifest = (FilterableManifest) this.parser.parse(uri, inputStream);
        return (this.streamKeys == null || this.streamKeys.isEmpty()) ? filterableManifest : (FilterableManifest) filterableManifest.copy(this.streamKeys);
    }
}
