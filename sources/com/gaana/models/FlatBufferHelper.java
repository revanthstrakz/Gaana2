package com.gaana.models;

import com.gaana.models.flatbuffer.EntityInfo;
import com.gaana.models.flatbuffer.GaanaEntity;
import com.gaana.models.flatbuffer.GaanaEntityResponse;
import java.nio.ByteBuffer;

public class FlatBufferHelper {
    public static Object getModelData(byte[] bArr, Class<?> cls) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (cls == Items.class) {
            return new Items(GaanaEntityResponse.getRootAsGaanaEntityResponse(wrap));
        }
        if (cls == Item.class) {
            return new Item(GaanaEntity.getRootAsGaanaEntity(wrap));
        }
        return cls == EntityInfo.class ? new EntityInfo(EntityInfo.getRootAsEntityInfo(wrap)) : null;
    }
}
