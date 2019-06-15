package com.google.api.client.json;

import com.google.api.client.json.JsonPolymorphicTypeMap.TypeDef;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sets;
import com.google.api.client.util.Types;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class JsonParser {
    private static WeakHashMap<Class<?>, Field> cachedTypemapFields = new WeakHashMap();
    private static final Lock lock = new ReentrantLock();

    public abstract void close() throws IOException;

    public abstract BigInteger getBigIntegerValue() throws IOException;

    public abstract byte getByteValue() throws IOException;

    public abstract String getCurrentName() throws IOException;

    public abstract JsonToken getCurrentToken();

    public abstract BigDecimal getDecimalValue() throws IOException;

    public abstract double getDoubleValue() throws IOException;

    public abstract JsonFactory getFactory();

    public abstract float getFloatValue() throws IOException;

    public abstract int getIntValue() throws IOException;

    public abstract long getLongValue() throws IOException;

    public abstract short getShortValue() throws IOException;

    public abstract String getText() throws IOException;

    public abstract JsonToken nextToken() throws IOException;

    public abstract JsonParser skipChildren() throws IOException;

    public final <T> T parseAndClose(Class<T> cls) throws IOException {
        return parseAndClose((Class) cls, null);
    }

    @Beta
    public final <T> T parseAndClose(Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            T parse = parse((Class) cls, customizeJsonParser);
            return parse;
        } finally {
            close();
        }
    }

    public final void skipToKey(String str) throws IOException {
        skipToKey(Collections.singleton(str));
    }

    public final String skipToKey(Set<String> set) throws IOException {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
            String text = getText();
            nextToken();
            if (set.contains(text)) {
                return text;
            }
            skipChildren();
            startParsingObjectOrArray = nextToken();
        }
        return null;
    }

    private JsonToken startParsing() throws IOException {
        JsonToken currentToken = getCurrentToken();
        if (currentToken == null) {
            currentToken = nextToken();
        }
        Preconditions.checkArgument(currentToken != null, "no JSON input found");
        return currentToken;
    }

    private JsonToken startParsingObjectOrArray() throws IOException {
        JsonToken startParsing = startParsing();
        switch (startParsing) {
            case START_OBJECT:
                startParsing = nextToken();
                boolean z = startParsing == JsonToken.FIELD_NAME || startParsing == JsonToken.END_OBJECT;
                Preconditions.checkArgument(z, startParsing);
                return startParsing;
            case START_ARRAY:
                return nextToken();
            default:
                return startParsing;
        }
    }

    public final void parseAndClose(Object obj) throws IOException {
        parseAndClose(obj, null);
    }

    @Beta
    public final void parseAndClose(Object obj, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            parse(obj, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final <T> T parse(Class<T> cls) throws IOException {
        return parse((Class) cls, null);
    }

    @Beta
    public final <T> T parse(Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        return parse((Type) cls, false, customizeJsonParser);
    }

    public Object parse(Type type, boolean z) throws IOException {
        return parse(type, z, null);
    }

    @Beta
    public Object parse(Type type, boolean z, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            if (!Void.class.equals(type)) {
                startParsing();
            }
            Object parseValue = parseValue(null, type, new ArrayList(), null, customizeJsonParser, true);
            return parseValue;
        } finally {
            if (z) {
                close();
            }
        }
    }

    public final void parse(Object obj) throws IOException {
        parse(obj, null);
    }

    @Beta
    public final void parse(Object obj, CustomizeJsonParser customizeJsonParser) throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(obj.getClass());
        parse(arrayList, obj, customizeJsonParser);
    }

    private void parse(ArrayList<Type> arrayList, Object obj, CustomizeJsonParser customizeJsonParser) throws IOException {
        if (obj instanceof GenericJson) {
            ((GenericJson) obj).setFactory(getFactory());
        }
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        Class cls = obj.getClass();
        ClassInfo of = ClassInfo.of(cls);
        boolean isAssignableFrom = GenericData.class.isAssignableFrom(cls);
        if (isAssignableFrom || !Map.class.isAssignableFrom(cls)) {
            while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
                String text = getText();
                nextToken();
                if (customizeJsonParser == null || !customizeJsonParser.stopAt(obj, text)) {
                    FieldInfo fieldInfo = of.getFieldInfo(text);
                    if (fieldInfo != null) {
                        if (!fieldInfo.isFinal() || fieldInfo.isPrimitive()) {
                            Field field = fieldInfo.getField();
                            int size = arrayList.size();
                            arrayList.add(field.getGenericType());
                            Object parseValue = parseValue(field, fieldInfo.getGenericType(), arrayList, obj, customizeJsonParser, true);
                            arrayList.remove(size);
                            fieldInfo.setValue(obj, parseValue);
                        } else {
                            throw new IllegalArgumentException("final array/object fields are not supported");
                        }
                    } else if (isAssignableFrom) {
                        ((GenericData) obj).set(text, parseValue(null, null, arrayList, obj, customizeJsonParser, true));
                    } else {
                        if (customizeJsonParser != null) {
                            customizeJsonParser.handleUnrecognizedKey(obj, text);
                        }
                        skipChildren();
                    }
                    startParsingObjectOrArray = nextToken();
                } else {
                    return;
                }
            }
            return;
        }
        parseMap(null, (Map) obj, Types.getMapValueParameter(cls), arrayList, customizeJsonParser);
    }

    public final <T> Collection<T> parseArrayAndClose(Class<?> cls, Class<T> cls2) throws IOException {
        return parseArrayAndClose((Class) cls, (Class) cls2, null);
    }

    @Beta
    public final <T> Collection<T> parseArrayAndClose(Class<?> cls, Class<T> cls2, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            Collection<T> parseArray = parseArray((Class) cls, (Class) cls2, customizeJsonParser);
            return parseArray;
        } finally {
            close();
        }
    }

    public final <T> void parseArrayAndClose(Collection<? super T> collection, Class<T> cls) throws IOException {
        parseArrayAndClose((Collection) collection, (Class) cls, null);
    }

    @Beta
    public final <T> void parseArrayAndClose(Collection<? super T> collection, Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        try {
            parseArray((Collection) collection, (Class) cls, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final <T> Collection<T> parseArray(Class<?> cls, Class<T> cls2) throws IOException {
        return parseArray((Class) cls, (Class) cls2, null);
    }

    @Beta
    public final <T> Collection<T> parseArray(Class<?> cls, Class<T> cls2, CustomizeJsonParser customizeJsonParser) throws IOException {
        Collection newCollectionInstance = Data.newCollectionInstance(cls);
        parseArray(newCollectionInstance, (Class) cls2, customizeJsonParser);
        return newCollectionInstance;
    }

    public final <T> void parseArray(Collection<? super T> collection, Class<T> cls) throws IOException {
        parseArray((Collection) collection, (Class) cls, null);
    }

    @Beta
    public final <T> void parseArray(Collection<? super T> collection, Class<T> cls, CustomizeJsonParser customizeJsonParser) throws IOException {
        parseArray(null, collection, cls, new ArrayList(), customizeJsonParser);
    }

    private <T> void parseArray(Field field, Collection<T> collection, Type type, ArrayList<Type> arrayList, CustomizeJsonParser customizeJsonParser) throws IOException {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray != JsonToken.END_ARRAY) {
            collection.add(parseValue(field, type, arrayList, collection, customizeJsonParser, true));
            startParsingObjectOrArray = nextToken();
        }
    }

    private void parseMap(Field field, Map<String, Object> map, Type type, ArrayList<Type> arrayList, CustomizeJsonParser customizeJsonParser) throws IOException {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
            String text = getText();
            nextToken();
            if (customizeJsonParser == null || !customizeJsonParser.stopAt(map, text)) {
                map.put(text, parseValue(field, type, arrayList, map, customizeJsonParser, true));
                startParsingObjectOrArray = nextToken();
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:209:0x02e0 A:{Catch:{ IllegalArgumentException -> 0x0337 }} */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x02f2 A:{Catch:{ IllegalArgumentException -> 0x0337 }} */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x02ed A:{Catch:{ IllegalArgumentException -> 0x0337 }} */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x01ee A:{Catch:{ IllegalArgumentException -> 0x0337 }} */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x01f9 A:{SKIP, Catch:{ IllegalArgumentException -> 0x0337 }} */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x01f4 A:{Catch:{ IllegalArgumentException -> 0x0337 }} */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x021f A:{RETURN, Catch:{ IllegalArgumentException -> 0x0337 }} */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0216 A:{Catch:{ IllegalArgumentException -> 0x0337 }} */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x01c0 A:{Catch:{ IllegalArgumentException -> 0x0337 }} */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x01bd A:{Catch:{ IllegalArgumentException -> 0x0337 }} */
    private final java.lang.Object parseValue(java.lang.reflect.Field r8, java.lang.reflect.Type r9, java.util.ArrayList<java.lang.reflect.Type> r10, java.lang.Object r11, com.google.api.client.json.CustomizeJsonParser r12, boolean r13) throws java.io.IOException {
        /*
        r7 = this;
        r9 = com.google.api.client.util.Data.resolveWildcardTypeOrTypeVariable(r10, r9);
        r0 = r9 instanceof java.lang.Class;
        r1 = 0;
        if (r0 == 0) goto L_0x000d;
    L_0x0009:
        r0 = r9;
        r0 = (java.lang.Class) r0;
        goto L_0x000e;
    L_0x000d:
        r0 = r1;
    L_0x000e:
        r2 = r9 instanceof java.lang.reflect.ParameterizedType;
        if (r2 == 0) goto L_0x0019;
    L_0x0012:
        r0 = r9;
        r0 = (java.lang.reflect.ParameterizedType) r0;
        r0 = com.google.api.client.util.Types.getRawClass(r0);
    L_0x0019:
        r2 = java.lang.Void.class;
        if (r0 != r2) goto L_0x0021;
    L_0x001d:
        r7.skipChildren();
        return r1;
    L_0x0021:
        r2 = r7.getCurrentToken();
        r3 = com.google.api.client.json.JsonParser.AnonymousClass1.$SwitchMap$com$google$api$client$json$JsonToken;	 Catch:{ IllegalArgumentException -> 0x0337 }
        r4 = r7.getCurrentToken();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r4 = r4.ordinal();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r3 = r3[r4];	 Catch:{ IllegalArgumentException -> 0x0337 }
        r4 = 0;
        r5 = 1;
        switch(r3) {
            case 1: goto L_0x0220;
            case 2: goto L_0x01c3;
            case 3: goto L_0x01c3;
            case 4: goto L_0x0220;
            case 5: goto L_0x0220;
            case 6: goto L_0x019c;
            case 7: goto L_0x019c;
            case 8: goto L_0x00de;
            case 9: goto L_0x00de;
            case 10: goto L_0x0085;
            case 11: goto L_0x003a;
            default: goto L_0x0036;
        };	 Catch:{ IllegalArgumentException -> 0x0337 }
    L_0x0036:
        r9 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x0337 }
        goto L_0x0313;
    L_0x003a:
        if (r0 == 0) goto L_0x0042;
    L_0x003c:
        r11 = r0.isPrimitive();	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r11 != 0) goto L_0x0043;
    L_0x0042:
        r4 = r5;
    L_0x0043:
        r11 = "primitive number field but found a JSON null";
        com.google.api.client.util.Preconditions.checkArgument(r4, r11);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r0 == 0) goto L_0x007c;
    L_0x004a:
        r11 = r0.getModifiers();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r11 = r11 & 1536;
        if (r11 == 0) goto L_0x007c;
    L_0x0052:
        r11 = java.util.Collection.class;
        r11 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r11);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r11 == 0) goto L_0x0067;
    L_0x005a:
        r9 = com.google.api.client.util.Data.newCollectionInstance(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = r9.getClass();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = com.google.api.client.util.Data.nullOf(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x0067:
        r11 = java.util.Map.class;
        r11 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r11);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r11 == 0) goto L_0x007c;
    L_0x006f:
        r9 = com.google.api.client.util.Data.newMapInstance(r0);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = r9.getClass();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = com.google.api.client.util.Data.nullOf(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x007c:
        r9 = com.google.api.client.util.Types.getRawArrayComponentType(r10, r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = com.google.api.client.util.Data.nullOf(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x0085:
        r10 = r7.getText();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r10 = r10.trim();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r11 = java.util.Locale.US;	 Catch:{ IllegalArgumentException -> 0x0337 }
        r10 = r10.toLowerCase(r11);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r11 = java.lang.Float.TYPE;	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r0 == r11) goto L_0x00a3;
    L_0x0097:
        r11 = java.lang.Float.class;
        if (r0 == r11) goto L_0x00a3;
    L_0x009b:
        r11 = java.lang.Double.TYPE;	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r0 == r11) goto L_0x00a3;
    L_0x009f:
        r11 = java.lang.Double.class;
        if (r0 != r11) goto L_0x00bb;
    L_0x00a3:
        r11 = "nan";
        r11 = r10.equals(r11);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r11 != 0) goto L_0x00d5;
    L_0x00ab:
        r11 = "infinity";
        r11 = r10.equals(r11);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r11 != 0) goto L_0x00d5;
    L_0x00b3:
        r11 = "-infinity";
        r10 = r10.equals(r11);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r10 != 0) goto L_0x00d5;
    L_0x00bb:
        if (r0 == 0) goto L_0x00cf;
    L_0x00bd:
        r10 = java.lang.Number.class;
        r10 = r10.isAssignableFrom(r0);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r10 == 0) goto L_0x00cf;
    L_0x00c5:
        if (r8 == 0) goto L_0x00d0;
    L_0x00c7:
        r10 = com.google.api.client.json.JsonString.class;
        r10 = r8.getAnnotation(r10);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r10 == 0) goto L_0x00d0;
    L_0x00cf:
        r4 = r5;
    L_0x00d0:
        r10 = "number field formatted as a JSON string must use the @JsonString annotation";
        com.google.api.client.util.Preconditions.checkArgument(r4, r10);	 Catch:{ IllegalArgumentException -> 0x0337 }
    L_0x00d5:
        r10 = r7.getText();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = com.google.api.client.util.Data.parsePrimitiveValue(r9, r10);	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x00de:
        if (r8 == 0) goto L_0x00e8;
    L_0x00e0:
        r10 = com.google.api.client.json.JsonString.class;
        r10 = r8.getAnnotation(r10);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r10 != 0) goto L_0x00e9;
    L_0x00e8:
        r4 = r5;
    L_0x00e9:
        r10 = "number type formatted as a JSON number cannot use @JsonString annotation";
        com.google.api.client.util.Preconditions.checkArgument(r4, r10);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r0 == 0) goto L_0x0197;
    L_0x00f0:
        r10 = java.math.BigDecimal.class;
        r10 = r0.isAssignableFrom(r10);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r10 == 0) goto L_0x00fa;
    L_0x00f8:
        goto L_0x0197;
    L_0x00fa:
        r10 = java.math.BigInteger.class;
        if (r0 != r10) goto L_0x0103;
    L_0x00fe:
        r9 = r7.getBigIntegerValue();	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x0103:
        r10 = java.lang.Double.class;
        if (r0 == r10) goto L_0x018e;
    L_0x0107:
        r10 = java.lang.Double.TYPE;	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r0 != r10) goto L_0x010d;
    L_0x010b:
        goto L_0x018e;
    L_0x010d:
        r10 = java.lang.Long.class;
        if (r0 == r10) goto L_0x0185;
    L_0x0111:
        r10 = java.lang.Long.TYPE;	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r0 != r10) goto L_0x0117;
    L_0x0115:
        goto L_0x0185;
    L_0x0117:
        r10 = java.lang.Float.class;
        if (r0 == r10) goto L_0x017c;
    L_0x011b:
        r10 = java.lang.Float.TYPE;	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r0 != r10) goto L_0x0120;
    L_0x011f:
        goto L_0x017c;
    L_0x0120:
        r10 = java.lang.Integer.class;
        if (r0 == r10) goto L_0x0173;
    L_0x0124:
        r10 = java.lang.Integer.TYPE;	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r0 != r10) goto L_0x0129;
    L_0x0128:
        goto L_0x0173;
    L_0x0129:
        r10 = java.lang.Short.class;
        if (r0 == r10) goto L_0x016a;
    L_0x012d:
        r10 = java.lang.Short.TYPE;	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r0 != r10) goto L_0x0132;
    L_0x0131:
        goto L_0x016a;
    L_0x0132:
        r10 = java.lang.Byte.class;
        if (r0 == r10) goto L_0x0161;
    L_0x0136:
        r10 = java.lang.Byte.TYPE;	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r0 != r10) goto L_0x013b;
    L_0x013a:
        goto L_0x0161;
    L_0x013b:
        r10 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = java.lang.String.valueOf(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = java.lang.String.valueOf(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r11 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x0337 }
        r12 = 30;
        r13 = r9.length();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r12 = r12 + r13;
        r11.<init>(r12);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r12 = "expected numeric type but got ";
        r11.append(r12);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r11.append(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = r11.toString();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r10.<init>(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        throw r10;	 Catch:{ IllegalArgumentException -> 0x0337 }
    L_0x0161:
        r9 = r7.getByteValue();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = java.lang.Byte.valueOf(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x016a:
        r9 = r7.getShortValue();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = java.lang.Short.valueOf(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x0173:
        r9 = r7.getIntValue();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x017c:
        r9 = r7.getFloatValue();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = java.lang.Float.valueOf(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x0185:
        r9 = r7.getLongValue();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = java.lang.Long.valueOf(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x018e:
        r9 = r7.getDoubleValue();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = java.lang.Double.valueOf(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x0197:
        r9 = r7.getDecimalValue();	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x019c:
        if (r9 == 0) goto L_0x01af;
    L_0x019e:
        r10 = java.lang.Boolean.TYPE;	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r0 == r10) goto L_0x01af;
    L_0x01a2:
        if (r0 == 0) goto L_0x01ad;
    L_0x01a4:
        r10 = java.lang.Boolean.class;
        r10 = r0.isAssignableFrom(r10);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r10 == 0) goto L_0x01ad;
    L_0x01ac:
        goto L_0x01af;
    L_0x01ad:
        r10 = r4;
        goto L_0x01b0;
    L_0x01af:
        r10 = r5;
    L_0x01b0:
        r11 = "expected type Boolean or boolean but got %s";
        r12 = new java.lang.Object[r5];	 Catch:{ IllegalArgumentException -> 0x0337 }
        r12[r4] = r9;	 Catch:{ IllegalArgumentException -> 0x0337 }
        com.google.api.client.util.Preconditions.checkArgument(r10, r11, r12);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = com.google.api.client.json.JsonToken.VALUE_TRUE;	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r2 != r9) goto L_0x01c0;
    L_0x01bd:
        r9 = java.lang.Boolean.TRUE;	 Catch:{ IllegalArgumentException -> 0x0337 }
        goto L_0x01c2;
    L_0x01c0:
        r9 = java.lang.Boolean.FALSE;	 Catch:{ IllegalArgumentException -> 0x0337 }
    L_0x01c2:
        return r9;
    L_0x01c3:
        r13 = com.google.api.client.util.Types.isArray(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r9 == 0) goto L_0x01d8;
    L_0x01c9:
        if (r13 != 0) goto L_0x01d8;
    L_0x01cb:
        if (r0 == 0) goto L_0x01d6;
    L_0x01cd:
        r2 = java.util.Collection.class;
        r2 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r2);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r2 == 0) goto L_0x01d6;
    L_0x01d5:
        goto L_0x01d8;
    L_0x01d6:
        r2 = r4;
        goto L_0x01d9;
    L_0x01d8:
        r2 = r5;
    L_0x01d9:
        r3 = "expected collection or array type but got %s";
        r5 = new java.lang.Object[r5];	 Catch:{ IllegalArgumentException -> 0x0337 }
        r5[r4] = r9;	 Catch:{ IllegalArgumentException -> 0x0337 }
        com.google.api.client.util.Preconditions.checkArgument(r2, r3, r5);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r12 == 0) goto L_0x01eb;
    L_0x01e4:
        if (r8 == 0) goto L_0x01eb;
    L_0x01e6:
        r11 = r12.newInstanceForArray(r11, r8);	 Catch:{ IllegalArgumentException -> 0x0337 }
        goto L_0x01ec;
    L_0x01eb:
        r11 = r1;
    L_0x01ec:
        if (r11 != 0) goto L_0x01f2;
    L_0x01ee:
        r11 = com.google.api.client.util.Data.newCollectionInstance(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
    L_0x01f2:
        if (r13 == 0) goto L_0x01f9;
    L_0x01f4:
        r1 = com.google.api.client.util.Types.getArrayComponentType(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        goto L_0x0207;
    L_0x01f9:
        if (r0 == 0) goto L_0x0207;
    L_0x01fb:
        r2 = java.lang.Iterable.class;
        r0 = r2.isAssignableFrom(r0);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r0 == 0) goto L_0x0207;
    L_0x0203:
        r1 = com.google.api.client.util.Types.getIterableParameter(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
    L_0x0207:
        r9 = com.google.api.client.util.Data.resolveWildcardTypeOrTypeVariable(r10, r1);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r0 = r7;
        r1 = r8;
        r2 = r11;
        r3 = r9;
        r4 = r10;
        r5 = r12;
        r0.parseArray(r1, r2, r3, r4, r5);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r13 == 0) goto L_0x021f;
    L_0x0216:
        r9 = com.google.api.client.util.Types.getRawArrayComponentType(r10, r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = com.google.api.client.util.Types.toArray(r11, r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x021f:
        return r11;
    L_0x0220:
        r2 = com.google.api.client.util.Types.isArray(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r2 = r2 ^ r5;
        r3 = "expected object or map type but got %s";
        r6 = new java.lang.Object[r5];	 Catch:{ IllegalArgumentException -> 0x0337 }
        r6[r4] = r9;	 Catch:{ IllegalArgumentException -> 0x0337 }
        com.google.api.client.util.Preconditions.checkArgument(r2, r3, r6);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r13 == 0) goto L_0x0235;
    L_0x0230:
        r13 = getCachedTypemapFieldFor(r0);	 Catch:{ IllegalArgumentException -> 0x0337 }
        goto L_0x0236;
    L_0x0235:
        r13 = r1;
    L_0x0236:
        if (r0 == 0) goto L_0x023f;
    L_0x0238:
        if (r12 == 0) goto L_0x023f;
    L_0x023a:
        r11 = r12.newInstanceForObject(r11, r0);	 Catch:{ IllegalArgumentException -> 0x0337 }
        goto L_0x0240;
    L_0x023f:
        r11 = r1;
    L_0x0240:
        if (r0 == 0) goto L_0x024c;
    L_0x0242:
        r2 = java.util.Map.class;
        r2 = com.google.api.client.util.Types.isAssignableToOrFrom(r0, r2);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r2 == 0) goto L_0x024c;
    L_0x024a:
        r2 = r5;
        goto L_0x024d;
    L_0x024c:
        r2 = r4;
    L_0x024d:
        if (r13 == 0) goto L_0x0255;
    L_0x024f:
        r11 = new com.google.api.client.json.GenericJson;	 Catch:{ IllegalArgumentException -> 0x0337 }
        r11.<init>();	 Catch:{ IllegalArgumentException -> 0x0337 }
        goto L_0x0265;
    L_0x0255:
        if (r11 != 0) goto L_0x0265;
    L_0x0257:
        if (r2 != 0) goto L_0x0261;
    L_0x0259:
        if (r0 != 0) goto L_0x025c;
    L_0x025b:
        goto L_0x0261;
    L_0x025c:
        r11 = com.google.api.client.util.Types.newInstance(r0);	 Catch:{ IllegalArgumentException -> 0x0337 }
        goto L_0x0265;
    L_0x0261:
        r11 = com.google.api.client.util.Data.newMapInstance(r0);	 Catch:{ IllegalArgumentException -> 0x0337 }
    L_0x0265:
        r3 = r10.size();	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r9 == 0) goto L_0x026e;
    L_0x026b:
        r10.add(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
    L_0x026e:
        if (r2 == 0) goto L_0x0295;
    L_0x0270:
        r2 = com.google.api.client.util.GenericData.class;
        r2 = r2.isAssignableFrom(r0);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r2 != 0) goto L_0x0295;
    L_0x0278:
        r2 = java.util.Map.class;
        r0 = r2.isAssignableFrom(r0);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r0 == 0) goto L_0x0286;
    L_0x0280:
        r0 = com.google.api.client.util.Types.getMapValueParameter(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r6 = r0;
        goto L_0x0287;
    L_0x0286:
        r6 = r1;
    L_0x0287:
        if (r6 == 0) goto L_0x0295;
    L_0x0289:
        r2 = r11;
        r2 = (java.util.Map) r2;	 Catch:{ IllegalArgumentException -> 0x0337 }
        r0 = r7;
        r1 = r8;
        r3 = r6;
        r4 = r10;
        r5 = r12;
        r0.parseMap(r1, r2, r3, r4, r5);	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r11;
    L_0x0295:
        r7.parse(r10, r11, r12);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r9 == 0) goto L_0x029d;
    L_0x029a:
        r10.remove(r3);	 Catch:{ IllegalArgumentException -> 0x0337 }
    L_0x029d:
        if (r13 != 0) goto L_0x02a0;
    L_0x029f:
        return r11;
    L_0x02a0:
        r9 = r11;
        r9 = (com.google.api.client.json.GenericJson) r9;	 Catch:{ IllegalArgumentException -> 0x0337 }
        r12 = r13.getName();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = r9.get(r12);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r9 == 0) goto L_0x02af;
    L_0x02ad:
        r12 = r5;
        goto L_0x02b0;
    L_0x02af:
        r12 = r4;
    L_0x02b0:
        r0 = "No value specified for @JsonPolymorphicTypeMap field";
        com.google.api.client.util.Preconditions.checkArgument(r12, r0);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = r9.toString();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r12 = com.google.api.client.json.JsonPolymorphicTypeMap.class;
        r12 = r13.getAnnotation(r12);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r12 = (com.google.api.client.json.JsonPolymorphicTypeMap) r12;	 Catch:{ IllegalArgumentException -> 0x0337 }
        r12 = r12.typeDefinitions();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r13 = r12.length;	 Catch:{ IllegalArgumentException -> 0x0337 }
        r0 = r4;
    L_0x02c7:
        if (r0 >= r13) goto L_0x02dd;
    L_0x02c9:
        r2 = r12[r0];	 Catch:{ IllegalArgumentException -> 0x0337 }
        r3 = r2.key();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r3 = r3.equals(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r3 == 0) goto L_0x02da;
    L_0x02d5:
        r1 = r2.ref();	 Catch:{ IllegalArgumentException -> 0x0337 }
        goto L_0x02dd;
    L_0x02da:
        r0 = r0 + 1;
        goto L_0x02c7;
    L_0x02dd:
        r2 = r1;
        if (r2 == 0) goto L_0x02e1;
    L_0x02e0:
        r4 = r5;
    L_0x02e1:
        r12 = "No TypeDef annotation found with key: ";
        r9 = java.lang.String.valueOf(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r13 = r9.length();	 Catch:{ IllegalArgumentException -> 0x0337 }
        if (r13 == 0) goto L_0x02f2;
    L_0x02ed:
        r9 = r12.concat(r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        goto L_0x02f7;
    L_0x02f2:
        r9 = new java.lang.String;	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9.<init>(r12);	 Catch:{ IllegalArgumentException -> 0x0337 }
    L_0x02f7:
        com.google.api.client.util.Preconditions.checkArgument(r4, r9);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9 = r7.getFactory();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r11 = r9.toString(r11);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r0 = r9.createJsonParser(r11);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r0.startParsing();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r1 = r8;
        r3 = r10;
        r9 = r0.parseValue(r1, r2, r3, r4, r5, r6);	 Catch:{ IllegalArgumentException -> 0x0337 }
        return r9;
    L_0x0313:
        r10 = java.lang.String.valueOf(r2);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r10 = java.lang.String.valueOf(r10);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r11 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x0337 }
        r12 = 27;
        r13 = r10.length();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r12 = r12 + r13;
        r11.<init>(r12);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r12 = "unexpected JSON node type: ";
        r11.append(r12);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r11.append(r10);	 Catch:{ IllegalArgumentException -> 0x0337 }
        r10 = r11.toString();	 Catch:{ IllegalArgumentException -> 0x0337 }
        r9.<init>(r10);	 Catch:{ IllegalArgumentException -> 0x0337 }
        throw r9;	 Catch:{ IllegalArgumentException -> 0x0337 }
    L_0x0337:
        r9 = move-exception;
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r11 = r7.getCurrentName();
        if (r11 == 0) goto L_0x034b;
    L_0x0343:
        r12 = "key ";
        r10.append(r12);
        r10.append(r11);
    L_0x034b:
        if (r8 == 0) goto L_0x035c;
    L_0x034d:
        if (r11 == 0) goto L_0x0354;
    L_0x034f:
        r11 = ", ";
        r10.append(r11);
    L_0x0354:
        r11 = "field ";
        r10.append(r11);
        r10.append(r8);
    L_0x035c:
        r8 = new java.lang.IllegalArgumentException;
        r10 = r10.toString();
        r8.<init>(r10, r9);
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.json.JsonParser.parseValue(java.lang.reflect.Field, java.lang.reflect.Type, java.util.ArrayList, java.lang.Object, com.google.api.client.json.CustomizeJsonParser, boolean):java.lang.Object");
    }

    private static Field getCachedTypemapFieldFor(Class<?> cls) {
        Object obj = null;
        if (cls == null) {
            return null;
        }
        lock.lock();
        try {
            if (cachedTypemapFields.containsKey(cls)) {
                Field field = (Field) cachedTypemapFields.get(cls);
                return field;
            }
            for (FieldInfo field2 : ClassInfo.of(cls).getFieldInfos()) {
                Field field3 = field2.getField();
                JsonPolymorphicTypeMap jsonPolymorphicTypeMap = (JsonPolymorphicTypeMap) field3.getAnnotation(JsonPolymorphicTypeMap.class);
                if (jsonPolymorphicTypeMap != null) {
                    Preconditions.checkArgument(obj == null, "Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", cls);
                    Preconditions.checkArgument(Data.isPrimitive(field3.getType()), "Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", cls, field3.getType());
                    TypeDef[] typeDefinitions = jsonPolymorphicTypeMap.typeDefinitions();
                    HashSet newHashSet = Sets.newHashSet();
                    Preconditions.checkArgument(typeDefinitions.length > 0, "@JsonPolymorphicTypeMap must have at least one @TypeDef");
                    for (TypeDef key : typeDefinitions) {
                        Preconditions.checkArgument(newHashSet.add(key.key()), "Class contains two @TypeDef annotations with identical key: %s", typeDefinitions[r7].key());
                    }
                    obj = field3;
                }
            }
            cachedTypemapFields.put(cls, obj);
            lock.unlock();
            return obj;
        } finally {
            lock.unlock();
        }
    }
}
