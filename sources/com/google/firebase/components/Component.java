package com.google.firebase.components;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@KeepForSdk
public final class Component<T> {
    private final Set<Class<? super T>> zza;
    private final Set<Dependency> zzb;
    private final int zzc;
    private final ComponentFactory<T> zzd;
    private final Set<Class<?>> zze;

    /* renamed from: com.google.firebase.components.Component$1 */
    public static /* synthetic */ class AnonymousClass1<T> {
        private final T zza;
        private final zze<T> zzb;

        public static AnonymousClass1<Context> zza(Context context) {
            return new AnonymousClass1(context, new zzd());
        }

        @VisibleForTesting
        private AnonymousClass1(T t, zze<T> zze) {
            this.zza = t;
            this.zzb = zze;
        }

        public List<ComponentRegistrar> zza() {
            return AnonymousClass1.zzb(this.zzb.zza(this.zza));
        }

        private static List<ComponentRegistrar> zzb(List<String> list) {
            ArrayList arrayList = new ArrayList();
            for (String cls : list) {
                try {
                    Class cls2 = Class.forName(cls);
                    if (ComponentRegistrar.class.isAssignableFrom(cls2)) {
                        arrayList.add((ComponentRegistrar) cls2.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                    } else {
                        Log.w("ComponentDiscovery", String.format("Class %s is not an instance of %s", new Object[]{cls, "com.google.firebase.components.ComponentRegistrar"}));
                    }
                } catch (ClassNotFoundException e) {
                    Log.w("ComponentDiscovery", String.format("Class %s is not an found.", new Object[]{cls}), e);
                } catch (IllegalAccessException e2) {
                    Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", new Object[]{cls}), e2);
                } catch (InstantiationException e3) {
                    Log.w("ComponentDiscovery", String.format("Could not instantiate %s.", new Object[]{cls}), e3);
                } catch (NoSuchMethodException e4) {
                    Log.w("ComponentDiscovery", String.format("Could not instantiate %s", new Object[]{cls}), e4);
                } catch (InvocationTargetException e5) {
                    Log.w("ComponentDiscovery", String.format("Could not instantiate %s", new Object[]{cls}), e5);
                }
            }
            return arrayList;
        }

        static List<Component<?>> zza(List<Component<?>> list) {
            zzg zzg;
            HashMap hashMap = new HashMap(list.size());
            for (Component component : list) {
                zzg = new zzg(component);
                for (Class put : component.zza()) {
                    if (hashMap.put(put, zzg) != null) {
                        throw new IllegalArgumentException(String.format("Multiple components provide %s.", new Object[]{(Class) r2.next()}));
                    }
                }
            }
            for (zzg zzg2 : hashMap.values()) {
                for (Dependency dependency : zzg2.zzb().zzb()) {
                    if (dependency.zzc()) {
                        zzg zzg3 = (zzg) hashMap.get(dependency.zza());
                        if (zzg3 != null) {
                            zzg2.zza(zzg3);
                            zzg3.zzb(zzg2);
                        }
                    }
                }
            }
            Set<zzg> hashSet = new HashSet(hashMap.values());
            Set zza = AnonymousClass1.zza((Set) hashSet);
            ArrayList arrayList = new ArrayList();
            while (!zza.isEmpty()) {
                zzg = (zzg) zza.iterator().next();
                zza.remove(zzg);
                arrayList.add(zzg.zzb());
                for (zzg zzg4 : zzg.zza()) {
                    zzg4.zzc(zzg);
                    if (zzg4.zzc()) {
                        zza.add(zzg4);
                    }
                }
            }
            if (arrayList.size() == list.size()) {
                Collections.reverse(arrayList);
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (zzg zzg5 : hashSet) {
                if (!(zzg5.zzc() || zzg5.zzd())) {
                    arrayList2.add(zzg5.zzb());
                }
            }
            throw new DependencyCycleException(arrayList2);
        }

        private static Set<zzg> zza(Set<zzg> set) {
            HashSet hashSet = new HashSet();
            for (zzg zzg : set) {
                if (zzg.zzc()) {
                    hashSet.add(zzg);
                }
            }
            return hashSet;
        }
    }

    @KeepForSdk
    public static class Builder<T> {
        private final Set<Class<? super T>> zza;
        private final Set<Dependency> zzb;
        private int zzc;
        private ComponentFactory<T> zzd;
        private Set<Class<?>> zze;

        /* synthetic */ Builder(Class cls, Class[] clsArr, byte b) {
            this(cls, clsArr);
        }

        private Builder(Class<T> cls, Class<? super T>... clsArr) {
            this.zza = new HashSet();
            this.zzb = new HashSet();
            int i = 0;
            this.zzc = 0;
            this.zze = new HashSet();
            Preconditions.checkNotNull(cls, "Null interface");
            this.zza.add(cls);
            int length = clsArr.length;
            while (i < length) {
                Preconditions.checkNotNull(clsArr[i], "Null interface");
                i++;
            }
            Collections.addAll(this.zza, clsArr);
        }

        @KeepForSdk
        public Builder<T> add(Dependency dependency) {
            Preconditions.checkNotNull(dependency, "Null dependency");
            Preconditions.checkArgument(this.zza.contains(dependency.zza()) ^ 1, "Components are not allowed to depend on interfaces they themselves provide.");
            this.zzb.add(dependency);
            return this;
        }

        @KeepForSdk
        public Builder<T> alwaysEager() {
            return zza(1);
        }

        @KeepForSdk
        public Builder<T> eagerInDefaultApp() {
            return zza(2);
        }

        @KeepForSdk
        public Builder<T> publishes(Class<?> cls) {
            this.zze.add(cls);
            return this;
        }

        private Builder<T> zza(int i) {
            Preconditions.checkState(this.zzc == 0, "Instantiation type has already been set.");
            this.zzc = i;
            return this;
        }

        @KeepForSdk
        public Builder<T> factory(ComponentFactory<T> componentFactory) {
            this.zzd = (ComponentFactory) Preconditions.checkNotNull(componentFactory, "Null factory");
            return this;
        }

        @KeepForSdk
        public Component<T> build() {
            Preconditions.checkState(this.zzd != null, "Missing required property: factory.");
            return new Component(new HashSet(this.zza), new HashSet(this.zzb), this.zzc, this.zzd, this.zze, (byte) 0);
        }
    }

    static /* synthetic */ Object zza(Object obj) {
        return obj;
    }

    static /* synthetic */ Object zzb(Object obj) {
        return obj;
    }

    /* synthetic */ Component(Set set, Set set2, int i, ComponentFactory componentFactory, Set set3, byte b) {
        this(set, set2, i, componentFactory, set3);
    }

    private Component(Set<Class<? super T>> set, Set<Dependency> set2, int i, ComponentFactory<T> componentFactory, Set<Class<?>> set3) {
        this.zza = Collections.unmodifiableSet(set);
        this.zzb = Collections.unmodifiableSet(set2);
        this.zzc = i;
        this.zzd = componentFactory;
        this.zze = Collections.unmodifiableSet(set3);
    }

    public final Set<Class<? super T>> zza() {
        return this.zza;
    }

    public final Set<Dependency> zzb() {
        return this.zzb;
    }

    public final ComponentFactory<T> zzc() {
        return this.zzd;
    }

    public final Set<Class<?>> zzd() {
        return this.zze;
    }

    public final boolean zze() {
        return this.zzc == 1;
    }

    public final boolean zzf() {
        return this.zzc == 2;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("Component<");
        stringBuilder.append(Arrays.toString(this.zza.toArray()));
        stringBuilder.append(">{");
        stringBuilder.append(this.zzc);
        stringBuilder.append(", deps=");
        stringBuilder.append(Arrays.toString(this.zzb.toArray()));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @KeepForSdk
    public static <T> Builder<T> builder(Class<T> cls) {
        return new Builder(cls, new Class[0], (byte) 0);
    }

    @KeepForSdk
    public static <T> Builder<T> builder(Class<T> cls, Class<? super T>... clsArr) {
        return new Builder(cls, clsArr, (byte) 0);
    }

    @KeepForSdk
    @Deprecated
    public static <T> Component<T> of(Class<T> cls, T t) {
        return builder(cls).factory(zzb.zza(t)).build();
    }

    @KeepForSdk
    @SafeVarargs
    public static <T> Component<T> of(T t, Class<T> cls, Class<? super T>... clsArr) {
        return builder(cls, clsArr).factory(zzc.zza(t)).build();
    }
}
