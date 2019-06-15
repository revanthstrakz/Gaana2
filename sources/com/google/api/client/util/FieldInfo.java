package com.google.api.client.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.WeakHashMap;

public class FieldInfo {
    private static final Map<Field, FieldInfo> CACHE = new WeakHashMap();
    private final Field field;
    private final boolean isPrimitive;
    private final String name;

    public static FieldInfo of(Enum<?> enumR) {
        try {
            FieldInfo of = of(enumR.getClass().getField(enumR.name()));
            Preconditions.checkArgument(of != null, "enum constant missing @Value or @NullValue annotation: %s", enumR);
            return of;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: Missing block: B:30:0x006a, code skipped:
            return r2;
     */
    public static com.google.api.client.util.FieldInfo of(java.lang.reflect.Field r5) {
        /*
        r0 = 0;
        if (r5 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = CACHE;
        monitor-enter(r1);
        r2 = CACHE;	 Catch:{ all -> 0x006b }
        r2 = r2.get(r5);	 Catch:{ all -> 0x006b }
        r2 = (com.google.api.client.util.FieldInfo) r2;	 Catch:{ all -> 0x006b }
        r3 = r5.isEnumConstant();	 Catch:{ all -> 0x006b }
        if (r2 != 0) goto L_0x0069;
    L_0x0015:
        if (r3 != 0) goto L_0x0021;
    L_0x0017:
        r4 = r5.getModifiers();	 Catch:{ all -> 0x006b }
        r4 = java.lang.reflect.Modifier.isStatic(r4);	 Catch:{ all -> 0x006b }
        if (r4 != 0) goto L_0x0069;
    L_0x0021:
        if (r3 == 0) goto L_0x003f;
    L_0x0023:
        r2 = com.google.api.client.util.Value.class;
        r2 = r5.getAnnotation(r2);	 Catch:{ all -> 0x006b }
        r2 = (com.google.api.client.util.Value) r2;	 Catch:{ all -> 0x006b }
        if (r2 == 0) goto L_0x0032;
    L_0x002d:
        r0 = r2.value();	 Catch:{ all -> 0x006b }
        goto L_0x0053;
    L_0x0032:
        r2 = com.google.api.client.util.NullValue.class;
        r2 = r5.getAnnotation(r2);	 Catch:{ all -> 0x006b }
        r2 = (com.google.api.client.util.NullValue) r2;	 Catch:{ all -> 0x006b }
        if (r2 == 0) goto L_0x003d;
    L_0x003c:
        goto L_0x0053;
    L_0x003d:
        monitor-exit(r1);	 Catch:{ all -> 0x006b }
        return r0;
    L_0x003f:
        r2 = com.google.api.client.util.Key.class;
        r2 = r5.getAnnotation(r2);	 Catch:{ all -> 0x006b }
        r2 = (com.google.api.client.util.Key) r2;	 Catch:{ all -> 0x006b }
        if (r2 != 0) goto L_0x004b;
    L_0x0049:
        monitor-exit(r1);	 Catch:{ all -> 0x006b }
        return r0;
    L_0x004b:
        r0 = r2.value();	 Catch:{ all -> 0x006b }
        r2 = 1;
        r5.setAccessible(r2);	 Catch:{ all -> 0x006b }
    L_0x0053:
        r2 = "##default";
        r2 = r2.equals(r0);	 Catch:{ all -> 0x006b }
        if (r2 == 0) goto L_0x005f;
    L_0x005b:
        r0 = r5.getName();	 Catch:{ all -> 0x006b }
    L_0x005f:
        r2 = new com.google.api.client.util.FieldInfo;	 Catch:{ all -> 0x006b }
        r2.<init>(r5, r0);	 Catch:{ all -> 0x006b }
        r0 = CACHE;	 Catch:{ all -> 0x006b }
        r0.put(r5, r2);	 Catch:{ all -> 0x006b }
    L_0x0069:
        monitor-exit(r1);	 Catch:{ all -> 0x006b }
        return r2;
    L_0x006b:
        r5 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x006b }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.util.FieldInfo.of(java.lang.reflect.Field):com.google.api.client.util.FieldInfo");
    }

    FieldInfo(Field field, String str) {
        String str2;
        this.field = field;
        if (str == null) {
            str2 = null;
        } else {
            str2 = str.intern();
        }
        this.name = str2;
        this.isPrimitive = Data.isPrimitive(getType());
    }

    public Field getField() {
        return this.field;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getType() {
        return this.field.getType();
    }

    public Type getGenericType() {
        return this.field.getGenericType();
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.field.getModifiers());
    }

    public boolean isPrimitive() {
        return this.isPrimitive;
    }

    public Object getValue(Object obj) {
        return getFieldValue(this.field, obj);
    }

    public void setValue(Object obj, Object obj2) {
        setFieldValue(this.field, obj, obj2);
    }

    public ClassInfo getClassInfo() {
        return ClassInfo.of(this.field.getDeclaringClass());
    }

    public <T extends Enum<T>> T enumValue() {
        return Enum.valueOf(this.field.getDeclaringClass(), this.field.getName());
    }

    public static Object getFieldValue(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void setFieldValue(Field field, Object obj, Object obj2) {
        if (Modifier.isFinal(field.getModifiers())) {
            Object fieldValue = getFieldValue(field, obj);
            if (obj2 == null) {
                if (fieldValue == null) {
                    return;
                }
            } else if (obj2.equals(fieldValue)) {
                return;
            }
            String valueOf = String.valueOf(String.valueOf(fieldValue));
            String valueOf2 = String.valueOf(String.valueOf(obj2));
            String valueOf3 = String.valueOf(String.valueOf(field.getName()));
            String valueOf4 = String.valueOf(String.valueOf(obj.getClass().getName()));
            StringBuilder stringBuilder = new StringBuilder((((48 + valueOf.length()) + valueOf2.length()) + valueOf3.length()) + valueOf4.length());
            stringBuilder.append("expected final value <");
            stringBuilder.append(valueOf);
            stringBuilder.append("> but was <");
            stringBuilder.append(valueOf2);
            stringBuilder.append("> on ");
            stringBuilder.append(valueOf3);
            stringBuilder.append(" field in ");
            stringBuilder.append(valueOf4);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        try {
            field.set(obj, obj2);
        } catch (SecurityException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
}
