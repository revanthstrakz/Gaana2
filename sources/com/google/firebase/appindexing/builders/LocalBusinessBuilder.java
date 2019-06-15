package com.google.firebase.appindexing.builders;

import android.support.annotation.NonNull;

public final class LocalBusinessBuilder extends IndexableBuilder<LocalBusinessBuilder> {
    LocalBusinessBuilder() {
        super("LocalBusiness");
    }

    LocalBusinessBuilder(String str) {
        super(str);
    }

    public final LocalBusinessBuilder setTelephone(@NonNull String str) {
        return (LocalBusinessBuilder) put("telephone", str);
    }

    public final LocalBusinessBuilder setPriceRange(@NonNull String str) {
        return (LocalBusinessBuilder) put("priceRange", str);
    }

    public final LocalBusinessBuilder setAddress(@NonNull PostalAddressBuilder postalAddressBuilder) {
        return (LocalBusinessBuilder) put(AccountKitGraphConstants.EMAIL_ADDRESS_KEY, postalAddressBuilder);
    }

    public final LocalBusinessBuilder setAggregateRating(@NonNull AggregateRatingBuilder aggregateRatingBuilder) {
        return (LocalBusinessBuilder) put("aggregateRating", aggregateRatingBuilder);
    }

    public final LocalBusinessBuilder setGeo(@NonNull GeoShapeBuilder geoShapeBuilder) {
        return (LocalBusinessBuilder) put("geo", geoShapeBuilder);
    }
}
