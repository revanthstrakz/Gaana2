package android.arch.persistence.room.b;

import android.database.Cursor;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@RestrictTo({Scope.LIBRARY_GROUP})
public class b {
    public final String a;
    public final Map<String, a> b;
    public final Set<b> c;
    @Nullable
    public final Set<d> d;

    public static class a {
        public final String a;
        public final String b;
        public final int c;
        public final boolean d;
        public final int e;

        public a(String str, String str2, boolean z, int i) {
            this.a = str;
            this.b = str2;
            this.d = z;
            this.e = i;
            this.c = a(str2);
        }

        private static int a(@Nullable String str) {
            if (str == null) {
                return 5;
            }
            str = str.toUpperCase(Locale.US);
            if (str.contains("INT")) {
                return 3;
            }
            if (str.contains("CHAR") || str.contains("CLOB") || str.contains("TEXT")) {
                return 2;
            }
            if (str.contains("BLOB")) {
                return 5;
            }
            return (str.contains("REAL") || str.contains("FLOA") || str.contains("DOUB")) ? 4 : 1;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (VERSION.SDK_INT >= 20) {
                if (this.e != aVar.e) {
                    return false;
                }
            } else if (a() != aVar.a()) {
                return false;
            }
            if (!this.a.equals(aVar.a) || this.d != aVar.d) {
                return false;
            }
            if (this.c != aVar.c) {
                z = false;
            }
            return z;
        }

        public boolean a() {
            return this.e > 0;
        }

        public int hashCode() {
            return (31 * ((((this.a.hashCode() * 31) + this.c) * 31) + (this.d ? 1231 : 1237))) + this.e;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Column{name='");
            stringBuilder.append(this.a);
            stringBuilder.append('\'');
            stringBuilder.append(", type='");
            stringBuilder.append(this.b);
            stringBuilder.append('\'');
            stringBuilder.append(", affinity='");
            stringBuilder.append(this.c);
            stringBuilder.append('\'');
            stringBuilder.append(", notNull=");
            stringBuilder.append(this.d);
            stringBuilder.append(", primaryKeyPosition=");
            stringBuilder.append(this.e);
            stringBuilder.append('}');
            return stringBuilder.toString();
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class b {
        @NonNull
        public final String a;
        @NonNull
        public final String b;
        @NonNull
        public final String c;
        @NonNull
        public final List<String> d;
        @NonNull
        public final List<String> e;

        public b(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull List<String> list, @NonNull List<String> list2) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = Collections.unmodifiableList(list);
            this.e = Collections.unmodifiableList(list2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            if (this.a.equals(bVar.a) && this.b.equals(bVar.b) && this.c.equals(bVar.c) && this.d.equals(bVar.d)) {
                return this.e.equals(bVar.e);
            }
            return false;
        }

        public int hashCode() {
            return (31 * ((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode())) + this.e.hashCode();
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ForeignKey{referenceTable='");
            stringBuilder.append(this.a);
            stringBuilder.append('\'');
            stringBuilder.append(", onDelete='");
            stringBuilder.append(this.b);
            stringBuilder.append('\'');
            stringBuilder.append(", onUpdate='");
            stringBuilder.append(this.c);
            stringBuilder.append('\'');
            stringBuilder.append(", columnNames=");
            stringBuilder.append(this.d);
            stringBuilder.append(", referenceColumnNames=");
            stringBuilder.append(this.e);
            stringBuilder.append('}');
            return stringBuilder.toString();
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    static class c implements Comparable<c> {
        final int a;
        final int b;
        final String c;
        final String d;

        c(int i, int i2, String str, String str2) {
            this.a = i;
            this.b = i2;
            this.c = str;
            this.d = str2;
        }

        /* renamed from: a */
        public int compareTo(@NonNull c cVar) {
            int i = this.a - cVar.a;
            return i == 0 ? this.b - cVar.b : i;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public static class d {
        public final String a;
        public final boolean b;
        public final List<String> c;

        public d(String str, boolean z, List<String> list) {
            this.a = str;
            this.b = z;
            this.c = list;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            d dVar = (d) obj;
            if (this.b != dVar.b || !this.c.equals(dVar.c)) {
                return false;
            }
            if (this.a.startsWith("index_")) {
                return dVar.a.startsWith("index_");
            }
            return this.a.equals(dVar.a);
        }

        public int hashCode() {
            int hashCode;
            if (this.a.startsWith("index_")) {
                hashCode = "index_".hashCode();
            } else {
                hashCode = this.a.hashCode();
            }
            return (31 * ((hashCode * 31) + this.b)) + this.c.hashCode();
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Index{name='");
            stringBuilder.append(this.a);
            stringBuilder.append('\'');
            stringBuilder.append(", unique=");
            stringBuilder.append(this.b);
            stringBuilder.append(", columns=");
            stringBuilder.append(this.c);
            stringBuilder.append('}');
            return stringBuilder.toString();
        }
    }

    public b(String str, Map<String, a> map, Set<b> set, Set<d> set2) {
        Set set3;
        this.a = str;
        this.b = Collections.unmodifiableMap(map);
        this.c = Collections.unmodifiableSet(set);
        if (set2 == null) {
            set3 = null;
        } else {
            set3 = Collections.unmodifiableSet(set2);
        }
        this.d = set3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (!this.a == null ? this.a.equals(bVar.a) : bVar.a == null) {
            return false;
        }
        if (!this.b == null ? this.b.equals(bVar.b) : bVar.b == null) {
            return false;
        }
        if (!this.c == null ? this.c.equals(bVar.c) : bVar.c == null) {
            return false;
        }
        if (this.d == null || bVar.d == null) {
            return true;
        }
        return this.d.equals(bVar.d);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = 31 * (((this.a != null ? this.a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0));
        if (this.c != null) {
            i = this.c.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TableInfo{name='");
        stringBuilder.append(this.a);
        stringBuilder.append('\'');
        stringBuilder.append(", columns=");
        stringBuilder.append(this.b);
        stringBuilder.append(", foreignKeys=");
        stringBuilder.append(this.c);
        stringBuilder.append(", indices=");
        stringBuilder.append(this.d);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public static b a(android.arch.persistence.a.b bVar, String str) {
        return new b(str, c(bVar, str), b(bVar, str), d(bVar, str));
    }

    private static Set<b> b(android.arch.persistence.a.b bVar, String str) {
        HashSet hashSet = new HashSet();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PRAGMA foreign_key_list(`");
        stringBuilder.append(str);
        stringBuilder.append("`)");
        Cursor b = bVar.b(stringBuilder.toString());
        try {
            int columnIndex = b.getColumnIndex("id");
            int columnIndex2 = b.getColumnIndex("seq");
            int columnIndex3 = b.getColumnIndex("table");
            int columnIndex4 = b.getColumnIndex("on_delete");
            int columnIndex5 = b.getColumnIndex("on_update");
            List<c> a = a(b);
            int count = b.getCount();
            int i = 0;
            while (i < count) {
                int i2;
                b.moveToPosition(i);
                if (b.getInt(columnIndex2) != 0) {
                    i2 = columnIndex;
                } else {
                    int i3 = b.getInt(columnIndex);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (c cVar : a) {
                        i2 = columnIndex;
                        if (cVar.a == i3) {
                            arrayList.add(cVar.c);
                            arrayList2.add(cVar.d);
                        }
                        columnIndex = i2;
                    }
                    i2 = columnIndex;
                    ArrayList arrayList3 = arrayList2;
                    hashSet.add(new b(b.getString(columnIndex3), b.getString(columnIndex4), b.getString(columnIndex5), arrayList, arrayList3));
                }
                i++;
                columnIndex = i2;
            }
            b.close();
            return hashSet;
        } catch (Throwable th) {
            Throwable th2 = th;
            b.close();
        }
    }

    private static List<c> a(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("id");
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex("from");
        int columnIndex4 = cursor.getColumnIndex("to");
        int count = cursor.getCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < count; i++) {
            cursor.moveToPosition(i);
            arrayList.add(new c(cursor.getInt(columnIndex), cursor.getInt(columnIndex2), cursor.getString(columnIndex3), cursor.getString(columnIndex4)));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static Map<String, a> c(android.arch.persistence.a.b bVar, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PRAGMA table_info(`");
        stringBuilder.append(str);
        stringBuilder.append("`)");
        Cursor b = bVar.b(stringBuilder.toString());
        HashMap hashMap = new HashMap();
        try {
            if (b.getColumnCount() > 0) {
                int columnIndex = b.getColumnIndex("name");
                int columnIndex2 = b.getColumnIndex("type");
                int columnIndex3 = b.getColumnIndex("notnull");
                int columnIndex4 = b.getColumnIndex("pk");
                while (b.moveToNext()) {
                    String string = b.getString(columnIndex);
                    hashMap.put(string, new a(string, b.getString(columnIndex2), b.getInt(columnIndex3) != 0, b.getInt(columnIndex4)));
                }
            }
            b.close();
            return hashMap;
        } catch (Throwable th) {
            b.close();
        }
    }

    @Nullable
    private static Set<d> d(android.arch.persistence.a.b bVar, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PRAGMA index_list(`");
        stringBuilder.append(str);
        stringBuilder.append("`)");
        Cursor b = bVar.b(stringBuilder.toString());
        try {
            int columnIndex = b.getColumnIndex("name");
            int columnIndex2 = b.getColumnIndex("origin");
            int columnIndex3 = b.getColumnIndex("unique");
            if (!(columnIndex == -1 || columnIndex2 == -1)) {
                if (columnIndex3 != -1) {
                    HashSet hashSet = new HashSet();
                    while (b.moveToNext()) {
                        if ("c".equals(b.getString(columnIndex2))) {
                            String string = b.getString(columnIndex);
                            boolean z = true;
                            if (b.getInt(columnIndex3) != 1) {
                                z = false;
                            }
                            d a = a(bVar, string, z);
                            if (a == null) {
                                b.close();
                                return null;
                            }
                            hashSet.add(a);
                        }
                    }
                    b.close();
                    return hashSet;
                }
            }
            b.close();
            return null;
        } catch (Throwable th) {
            b.close();
        }
    }

    @Nullable
    private static d a(android.arch.persistence.a.b bVar, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PRAGMA index_xinfo(`");
        stringBuilder.append(str);
        stringBuilder.append("`)");
        Cursor b = bVar.b(stringBuilder.toString());
        try {
            int columnIndex = b.getColumnIndex("seqno");
            int columnIndex2 = b.getColumnIndex("cid");
            int columnIndex3 = b.getColumnIndex("name");
            if (!(columnIndex == -1 || columnIndex2 == -1)) {
                if (columnIndex3 != -1) {
                    TreeMap treeMap = new TreeMap();
                    while (b.moveToNext()) {
                        if (b.getInt(columnIndex2) >= 0) {
                            int i = b.getInt(columnIndex);
                            treeMap.put(Integer.valueOf(i), b.getString(columnIndex3));
                        }
                    }
                    ArrayList arrayList = new ArrayList(treeMap.size());
                    arrayList.addAll(treeMap.values());
                    d dVar = new d(str, z, arrayList);
                    b.close();
                    return dVar;
                }
            }
            b.close();
            return null;
        } catch (Throwable th) {
            b.close();
        }
    }
}
