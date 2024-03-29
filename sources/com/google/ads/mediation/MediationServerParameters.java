package com.google.ads.mediation;

import com.google.android.gms.internal.ads.zzbbd;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Deprecated
public class MediationServerParameters {

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Parameter {
        String name();

        boolean required() default true;
    }

    public static final class MappingException extends Exception {
        public MappingException(String str) {
            super(str);
        }
    }

    public void load(Map<String, String> map) throws MappingException {
        String str;
        StringBuilder stringBuilder;
        String str2;
        HashMap hashMap = new HashMap();
        for (Field field : getClass().getFields()) {
            Parameter parameter = (Parameter) field.getAnnotation(Parameter.class);
            if (parameter != null) {
                hashMap.put(parameter.name(), field);
            }
        }
        if (hashMap.isEmpty()) {
            zzbbd.zzeo("No server options fields detected. To suppress this message either add a field with the @Parameter annotation, or override the load() method.");
        }
        for (Entry entry : map.entrySet()) {
            Field field2 = (Field) hashMap.remove(entry.getKey());
            if (field2 != null) {
                try {
                    field2.set(this, entry.getValue());
                } catch (IllegalAccessException unused) {
                    str = (String) entry.getKey();
                    stringBuilder = new StringBuilder(49 + String.valueOf(str).length());
                    stringBuilder.append("Server option \"");
                    stringBuilder.append(str);
                    stringBuilder.append("\" could not be set: Illegal Access");
                    zzbbd.zzeo(stringBuilder.toString());
                } catch (IllegalArgumentException unused2) {
                    str = (String) entry.getKey();
                    stringBuilder = new StringBuilder(43 + String.valueOf(str).length());
                    stringBuilder.append("Server option \"");
                    stringBuilder.append(str);
                    stringBuilder.append("\" could not be set: Bad Type");
                    zzbbd.zzeo(stringBuilder.toString());
                }
            } else {
                str2 = (String) entry.getKey();
                str = (String) entry.getValue();
                StringBuilder stringBuilder2 = new StringBuilder((31 + String.valueOf(str2).length()) + String.valueOf(str).length());
                stringBuilder2.append("Unexpected server option: ");
                stringBuilder2.append(str2);
                stringBuilder2.append(" = \"");
                stringBuilder2.append(str);
                stringBuilder2.append("\"");
                zzbbd.zzdn(stringBuilder2.toString());
            }
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        for (Field field3 : hashMap.values()) {
            if (((Parameter) field3.getAnnotation(Parameter.class)).required()) {
                str2 = "Required server option missing: ";
                String valueOf = String.valueOf(((Parameter) field3.getAnnotation(Parameter.class)).name());
                zzbbd.zzeo(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                if (stringBuilder3.length() > 0) {
                    stringBuilder3.append(", ");
                }
                stringBuilder3.append(((Parameter) field3.getAnnotation(Parameter.class)).name());
            }
        }
        if (stringBuilder3.length() > 0) {
            str = "Required server option(s) missing: ";
            String valueOf2 = String.valueOf(stringBuilder3.toString());
            throw new MappingException(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
        }
    }
}
