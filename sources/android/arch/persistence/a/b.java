package android.arch.persistence.a;

import android.database.Cursor;
import android.database.SQLException;
import android.util.Pair;
import java.io.Closeable;
import java.util.List;

public interface b extends Closeable {
    f a(String str);

    Cursor a(e eVar);

    void a();

    Cursor b(String str);

    void b();

    void c();

    void c(String str) throws SQLException;

    boolean d();

    boolean e();

    String f();

    List<Pair<String, String>> g();
}
