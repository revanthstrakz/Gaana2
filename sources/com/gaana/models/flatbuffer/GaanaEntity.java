package com.gaana.models.flatbuffer;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class GaanaEntity extends Table {
    public static GaanaEntity getRootAsGaanaEntity(ByteBuffer byteBuffer) {
        return getRootAsGaanaEntity(byteBuffer, new GaanaEntity());
    }

    public static GaanaEntity getRootAsGaanaEntity(ByteBuffer byteBuffer, GaanaEntity gaanaEntity) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return gaanaEntity.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.bb = byteBuffer;
    }

    public GaanaEntity __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public String entityId() {
        int __offset = __offset(4);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer entityIdAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public ByteBuffer entityIdInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public String entityType() {
        int __offset = __offset(6);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer entityTypeAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public ByteBuffer entityTypeInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public String seokey() {
        int __offset = __offset(8);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer seokeyAsByteBuffer() {
        return __vector_as_bytebuffer(8, 1);
    }

    public ByteBuffer seokeyInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 1);
    }

    public String name() {
        int __offset = __offset(10);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer nameAsByteBuffer() {
        return __vector_as_bytebuffer(10, 1);
    }

    public ByteBuffer nameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 10, 1);
    }

    public String artwork() {
        int __offset = __offset(12);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer artworkAsByteBuffer() {
        return __vector_as_bytebuffer(12, 1);
    }

    public ByteBuffer artworkInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 12, 1);
    }

    public String artworkMedium() {
        int __offset = __offset(14);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer artworkMediumAsByteBuffer() {
        return __vector_as_bytebuffer(14, 1);
    }

    public ByteBuffer artworkMediumInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 14, 1);
    }

    public int favoriteCount() {
        int __offset = __offset(16);
        return __offset != 0 ? this.bb.getInt(__offset + this.bb_pos) : 0;
    }

    public String premiumContent() {
        int __offset = __offset(18);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer premiumContentAsByteBuffer() {
        return __vector_as_bytebuffer(18, 1);
    }

    public ByteBuffer premiumContentInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 18, 1);
    }

    public int userFavorite() {
        int __offset = __offset(20);
        return __offset != 0 ? this.bb.getInt(__offset + this.bb_pos) : 0;
    }

    public String sapid() {
        int __offset = __offset(22);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer sapidAsByteBuffer() {
        return __vector_as_bytebuffer(22, 1);
    }

    public ByteBuffer sapidInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 22, 1);
    }

    public String atw() {
        int __offset = __offset(24);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer atwAsByteBuffer() {
        return __vector_as_bytebuffer(24, 1);
    }

    public ByteBuffer atwInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 24, 1);
    }

    public String language() {
        int __offset = __offset(26);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer languageAsByteBuffer() {
        return __vector_as_bytebuffer(26, 1);
    }

    public ByteBuffer languageInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 26, 1);
    }

    public EntityInfo entityInfo(int i) {
        return entityInfo(new EntityInfo(), i);
    }

    public EntityInfo entityInfo(EntityInfo entityInfo, int i) {
        int __offset = __offset(28);
        return __offset != 0 ? entityInfo.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb) : null;
    }

    public int entityInfoLength() {
        int __offset = __offset(28);
        return __offset != 0 ? __vector_len(__offset) : 0;
    }

    public String artworks(int i) {
        int __offset = __offset(30);
        return __offset != 0 ? __string(__vector(__offset) + (i * 4)) : null;
    }

    public int artworksLength() {
        int __offset = __offset(30);
        return __offset != 0 ? __vector_len(__offset) : 0;
    }

    public static int createGaanaEntity(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        flatBufferBuilder.startObject(14);
        addArtworks(flatBufferBuilder, i14);
        addEntityInfo(flatBufferBuilder, i13);
        addLanguage(flatBufferBuilder, i12);
        addAtw(flatBufferBuilder, i11);
        addSapid(flatBufferBuilder, i10);
        addUserFavorite(flatBufferBuilder, i9);
        addPremiumContent(flatBufferBuilder, i8);
        addFavoriteCount(flatBufferBuilder, i7);
        addArtworkMedium(flatBufferBuilder, i6);
        addArtwork(flatBufferBuilder, i5);
        addName(flatBufferBuilder, i4);
        addSeokey(flatBufferBuilder, i3);
        addEntityType(flatBufferBuilder, i2);
        addEntityId(flatBufferBuilder, i);
        return endGaanaEntity(flatBufferBuilder);
    }

    public static void startGaanaEntity(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startObject(14);
    }

    public static void addEntityId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addEntityType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addSeokey(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static void addArtwork(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static void addArtworkMedium(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static void addFavoriteCount(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(6, i, 0);
    }

    public static void addPremiumContent(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(7, i, 0);
    }

    public static void addUserFavorite(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(8, i, 0);
    }

    public static void addSapid(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(9, i, 0);
    }

    public static void addAtw(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(10, i, 0);
    }

    public static void addLanguage(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(11, i, 0);
    }

    public static void addEntityInfo(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(12, i, 0);
    }

    public static int createEntityInfoVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startEntityInfoVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addArtworks(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(13, i, 0);
    }

    public static int createArtworksVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startArtworksVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static int endGaanaEntity(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endObject();
    }
}
