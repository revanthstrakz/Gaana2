package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Date;

public final class DigitalDocumentBuilder extends IndexableBuilder<DigitalDocumentBuilder> {
    DigitalDocumentBuilder() {
        super("DigitalDocument");
    }

    DigitalDocumentBuilder(String str) {
        super(str);
    }

    public final DigitalDocumentBuilder setText(@NonNull String str) {
        return (DigitalDocumentBuilder) put(MimeTypes.BASE_TYPE_TEXT, str);
    }

    public final DigitalDocumentBuilder setAuthor(@NonNull PersonBuilder... personBuilderArr) {
        return (DigitalDocumentBuilder) put("author", (IndexableBuilder[]) personBuilderArr);
    }

    public final DigitalDocumentBuilder setDateCreated(@NonNull Date date) {
        return (DigitalDocumentBuilder) put("dateCreated", date.getTime());
    }

    public final DigitalDocumentBuilder setDateModified(@NonNull Date date) {
        return (DigitalDocumentBuilder) put("dateModified", date.getTime());
    }

    public final DigitalDocumentBuilder setHasDigitalDocumentPermission(@NonNull DigitalDocumentPermissionBuilder... digitalDocumentPermissionBuilderArr) {
        return (DigitalDocumentBuilder) put("hasDigitalDocumentPermission", (IndexableBuilder[]) digitalDocumentPermissionBuilderArr);
    }
}
