package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class gy {
    private final Map<Type, gb<?>> a;

    public gy(Map<Type, gb<?>> map) {
        this.a = map;
    }

    public <T> hd<T> a(hw<T> hwVar) {
        final Type b = hwVar.b();
        Class a = hwVar.a();
        final gb gbVar = (gb) this.a.get(b);
        if (gbVar != null) {
            return new hd<T>() {
                public T a() {
                    return gbVar.a(b);
                }
            };
        }
        gbVar = (gb) this.a.get(a);
        if (gbVar != null) {
            return new hd<T>() {
                public T a() {
                    return gbVar.a(b);
                }
            };
        }
        hd a2 = a(a);
        if (a2 != null) {
            return a2;
        }
        a2 = a(b, a);
        if (a2 != null) {
            return a2;
        }
        return b(b, a);
    }

    private <T> hd<T> a(Class<? super T> cls) {
        try {
            final Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new hd<T>() {
                public T a() {
                    StringBuilder stringBuilder;
                    try {
                        return declaredConstructor.newInstance(null);
                    } catch (InstantiationException e) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed to invoke ");
                        stringBuilder.append(declaredConstructor);
                        stringBuilder.append(" with no args");
                        throw new RuntimeException(stringBuilder.toString(), e);
                    } catch (InvocationTargetException e2) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Failed to invoke ");
                        stringBuilder.append(declaredConstructor);
                        stringBuilder.append(" with no args");
                        throw new RuntimeException(stringBuilder.toString(), e2.getTargetException());
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    }
                }
            };
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    private <T> hd<T> a(final Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new hd<T>() {
                    public T a() {
                        return new TreeSet();
                    }
                };
            }
            if (EnumSet.class.isAssignableFrom(cls)) {
                return new hd<T>() {
                    public T a() {
                        StringBuilder stringBuilder;
                        if (type instanceof ParameterizedType) {
                            Type type = ((ParameterizedType) type).getActualTypeArguments()[0];
                            if (type instanceof Class) {
                                return EnumSet.noneOf((Class) type);
                            }
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Invalid EnumSet type: ");
                            stringBuilder.append(type.toString());
                            throw new gg(stringBuilder.toString());
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Invalid EnumSet type: ");
                        stringBuilder.append(type.toString());
                        throw new gg(stringBuilder.toString());
                    }
                };
            }
            if (Set.class.isAssignableFrom(cls)) {
                return new hd<T>() {
                    public T a() {
                        return new LinkedHashSet();
                    }
                };
            }
            if (Queue.class.isAssignableFrom(cls)) {
                return new hd<T>() {
                    public T a() {
                        return new ArrayDeque();
                    }
                };
            }
            return new hd<T>() {
                public T a() {
                    return new ArrayList();
                }
            };
        } else if (!Map.class.isAssignableFrom(cls)) {
            return null;
        } else {
            if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                return new hd<T>() {
                    public T a() {
                        return new ConcurrentSkipListMap();
                    }
                };
            }
            if (ConcurrentMap.class.isAssignableFrom(cls)) {
                return new hd<T>() {
                    public T a() {
                        return new ConcurrentHashMap();
                    }
                };
            }
            if (SortedMap.class.isAssignableFrom(cls)) {
                return new hd<T>() {
                    public T a() {
                        return new TreeMap();
                    }
                };
            }
            if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(hw.a(((ParameterizedType) type).getActualTypeArguments()[0]).a())) {
                return new hd<T>() {
                    public T a() {
                        return new hc();
                    }
                };
            }
            return new hd<T>() {
                public T a() {
                    return new LinkedHashMap();
                }
            };
        }
    }

    private <T> hd<T> b(final Type type, final Class<? super T> cls) {
        return new hd<T>() {
            private final hg d = hg.a();

            public T a() {
                try {
                    return this.d.a(cls);
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unable to invoke no-args constructor for ");
                    stringBuilder.append(type);
                    stringBuilder.append(". ");
                    stringBuilder.append("Register an InstanceCreator with Gson for this type may fix this problem.");
                    throw new RuntimeException(stringBuilder.toString(), e);
                }
            }
        };
    }

    public String toString() {
        return this.a.toString();
    }
}
