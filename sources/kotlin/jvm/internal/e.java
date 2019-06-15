package kotlin.jvm.internal;

public class e {
    public String a(Lambda lambda) {
        return a((b) lambda);
    }

    public String a(b bVar) {
        String obj = bVar.getClass().getGenericInterfaces()[0].toString();
        return obj.startsWith("kotlin.jvm.functions.") ? obj.substring("kotlin.jvm.functions.".length()) : obj;
    }
}
