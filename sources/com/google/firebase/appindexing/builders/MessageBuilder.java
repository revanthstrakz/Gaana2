package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.common.internal.Preconditions;
import com.payu.custombrowser.util.CBConstant;
import java.util.Date;

public final class MessageBuilder extends IndexableBuilder<MessageBuilder> {
    MessageBuilder() {
        super("Message");
    }

    MessageBuilder(String str) {
        super(str);
    }

    public final MessageBuilder setText(@NonNull String str) {
        return (MessageBuilder) put(MimeTypes.BASE_TYPE_TEXT, str);
    }

    public final MessageBuilder setDateSent(@NonNull Date date) {
        Preconditions.checkNotNull(date);
        return (MessageBuilder) put("dateSent", date.getTime());
    }

    public final MessageBuilder setDateReceived(@NonNull Date date) {
        Preconditions.checkNotNull(date);
        return (MessageBuilder) put("dateReceived", date.getTime());
    }

    public final MessageBuilder setDateRead(@NonNull Date date) {
        Preconditions.checkNotNull(date);
        return (MessageBuilder) put("dateRead", date.getTime());
    }

    public final MessageBuilder setSender(@NonNull PersonBuilder personBuilder) {
        return (MessageBuilder) put(CBConstant.SENDER, personBuilder);
    }

    public final MessageBuilder setRecipient(@NonNull PersonBuilder... personBuilderArr) {
        return (MessageBuilder) put("recipient", (IndexableBuilder[]) personBuilderArr);
    }

    public final MessageBuilder setMessageAttachment(@NonNull IndexableBuilder<?>... indexableBuilderArr) {
        return (MessageBuilder) put("messageAttachment", (IndexableBuilder[]) indexableBuilderArr);
    }

    public final MessageBuilder setIsPartOf(@NonNull ConversationBuilder... conversationBuilderArr) {
        return (MessageBuilder) put("isPartOf", (IndexableBuilder[]) conversationBuilderArr);
    }
}
