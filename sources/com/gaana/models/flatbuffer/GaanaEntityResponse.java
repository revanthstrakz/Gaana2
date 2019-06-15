package com.gaana.models.flatbuffer;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class GaanaEntityResponse extends Table {
    public static GaanaEntityResponse getRootAsGaanaEntityResponse(ByteBuffer byteBuffer) {
        return getRootAsGaanaEntityResponse(byteBuffer, new GaanaEntityResponse());
    }

    public static GaanaEntityResponse getRootAsGaanaEntityResponse(ByteBuffer byteBuffer, GaanaEntityResponse gaanaEntityResponse) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return gaanaEntityResponse.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.bb = byteBuffer;
    }

    public GaanaEntityResponse __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public String count() {
        int __offset = __offset(4);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer countAsByteBuffer() {
        return __vector_as_bytebuffer(4, 1);
    }

    public ByteBuffer countInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 4, 1);
    }

    public int status() {
        int __offset = __offset(6);
        return __offset != 0 ? this.bb.getInt(__offset + this.bb_pos) : 0;
    }

    public String entityDescription() {
        int __offset = __offset(8);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer entityDescriptionAsByteBuffer() {
        return __vector_as_bytebuffer(8, 1);
    }

    public ByteBuffer entityDescriptionInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 8, 1);
    }

    public String userTokenStatus() {
        int __offset = __offset(10);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer userTokenStatusAsByteBuffer() {
        return __vector_as_bytebuffer(10, 1);
    }

    public ByteBuffer userTokenStatusInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 10, 1);
    }

    public String error() {
        int __offset = __offset(12);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer errorAsByteBuffer() {
        return __vector_as_bytebuffer(12, 1);
    }

    public ByteBuffer errorInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 12, 1);
    }

    public String seokey() {
        int __offset = __offset(14);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer seokeyAsByteBuffer() {
        return __vector_as_bytebuffer(14, 1);
    }

    public ByteBuffer seokeyInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 14, 1);
    }

    public String pageTitle() {
        int __offset = __offset(16);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer pageTitleAsByteBuffer() {
        return __vector_as_bytebuffer(16, 1);
    }

    public ByteBuffer pageTitleInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 16, 1);
    }

    public int entityParentId() {
        int __offset = __offset(18);
        return __offset != 0 ? this.bb.getInt(__offset + this.bb_pos) : 0;
    }

    public String hv() {
        int __offset = __offset(20);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer hvAsByteBuffer() {
        return __vector_as_bytebuffer(20, 1);
    }

    public ByteBuffer hvInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 20, 1);
    }

    public GaanaEntity entities(int i) {
        return entities(new GaanaEntity(), i);
    }

    public GaanaEntity entities(GaanaEntity gaanaEntity, int i) {
        int __offset = __offset(22);
        return __offset != 0 ? gaanaEntity.__assign(__indirect(__vector(__offset) + (i * 4)), this.bb) : null;
    }

    public int entitiesLength() {
        int __offset = __offset(22);
        return __offset != 0 ? __vector_len(__offset) : 0;
    }

    public String subTitle() {
        int __offset = __offset(24);
        return __offset != 0 ? __string(__offset + this.bb_pos) : null;
    }

    public ByteBuffer subTitleAsByteBuffer() {
        return __vector_as_bytebuffer(24, 1);
    }

    public ByteBuffer subTitleInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 24, 1);
    }

    public static int createGaanaEntityResponse(FlatBufferBuilder flatBufferBuilder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        flatBufferBuilder.startObject(11);
        addSubTitle(flatBufferBuilder, i11);
        addEntities(flatBufferBuilder, i10);
        addHv(flatBufferBuilder, i9);
        addEntityParentId(flatBufferBuilder, i8);
        addPageTitle(flatBufferBuilder, i7);
        addSeokey(flatBufferBuilder, i6);
        addError(flatBufferBuilder, i5);
        addUserTokenStatus(flatBufferBuilder, i4);
        addEntityDescription(flatBufferBuilder, i3);
        addStatus(flatBufferBuilder, i2);
        addCount(flatBufferBuilder, i);
        return endGaanaEntityResponse(flatBufferBuilder);
    }

    public static void startGaanaEntityResponse(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startObject(11);
    }

    public static void addCount(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(0, i, 0);
    }

    public static void addStatus(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(1, i, 0);
    }

    public static void addEntityDescription(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static void addUserTokenStatus(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static void addError(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static void addSeokey(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static void addPageTitle(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static void addEntityParentId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(7, i, 0);
    }

    public static void addHv(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(8, i, 0);
    }

    public static void addEntities(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(9, i, 0);
    }

    public static int createEntitiesVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startEntitiesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addSubTitle(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(10, i, 0);
    }

    public static int endGaanaEntityResponse(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endObject();
    }
}
