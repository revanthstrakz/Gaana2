package android.support.v4.app;

import android.arch.lifecycle.e;
import android.arch.lifecycle.k;
import android.arch.lifecycle.l;
import android.arch.lifecycle.p;
import android.arch.lifecycle.q;
import android.arch.lifecycle.q.a;
import android.arch.lifecycle.r;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

class LoaderManagerImpl extends LoaderManager {
    static boolean DEBUG = false;
    static final String TAG = "LoaderManager";
    private boolean mCreatingLoader;
    @NonNull
    private final e mLifecycleOwner;
    @NonNull
    private final LoaderViewModel mLoaderViewModel;

    public static class LoaderInfo<D> extends k<D> implements OnLoadCompleteListener<D> {
        @Nullable
        private final Bundle mArgs;
        private final int mId;
        private e mLifecycleOwner;
        @NonNull
        private final Loader<D> mLoader;
        private LoaderObserver<D> mObserver;
        private Loader<D> mPriorLoader;

        LoaderInfo(int i, @Nullable Bundle bundle, @NonNull Loader<D> loader, @Nullable Loader<D> loader2) {
            this.mId = i;
            this.mArgs = bundle;
            this.mLoader = loader;
            this.mPriorLoader = loader2;
            this.mLoader.registerListener(i, this);
        }

        /* Access modifiers changed, original: 0000 */
        @NonNull
        public Loader<D> getLoader() {
            return this.mLoader;
        }

        /* Access modifiers changed, original: protected */
        public void onActive() {
            if (LoaderManagerImpl.DEBUG) {
                String str = LoaderManagerImpl.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("  Starting: ");
                stringBuilder.append(this);
                Log.v(str, stringBuilder.toString());
            }
            this.mLoader.startLoading();
        }

        /* Access modifiers changed, original: protected */
        public void onInactive() {
            if (LoaderManagerImpl.DEBUG) {
                String str = LoaderManagerImpl.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("  Stopping: ");
                stringBuilder.append(this);
                Log.v(str, stringBuilder.toString());
            }
            this.mLoader.stopLoading();
        }

        /* Access modifiers changed, original: 0000 */
        @MainThread
        @NonNull
        public Loader<D> setCallback(@NonNull e eVar, @NonNull LoaderCallbacks<D> loaderCallbacks) {
            LoaderObserver loaderObserver = new LoaderObserver(this.mLoader, loaderCallbacks);
            observe(eVar, loaderObserver);
            if (this.mObserver != null) {
                removeObserver(this.mObserver);
            }
            this.mLifecycleOwner = eVar;
            this.mObserver = loaderObserver;
            return this.mLoader;
        }

        /* Access modifiers changed, original: 0000 */
        public void markForRedelivery() {
            e eVar = this.mLifecycleOwner;
            LoaderObserver loaderObserver = this.mObserver;
            if (eVar != null && loaderObserver != null) {
                super.removeObserver(loaderObserver);
                observe(eVar, loaderObserver);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean isCallbackWaitingForData() {
            boolean z = false;
            if (!hasActiveObservers()) {
                return false;
            }
            if (!(this.mObserver == null || this.mObserver.hasDeliveredData())) {
                z = true;
            }
            return z;
        }

        public void removeObserver(@NonNull l<D> lVar) {
            super.removeObserver(lVar);
            this.mLifecycleOwner = null;
            this.mObserver = null;
        }

        /* Access modifiers changed, original: 0000 */
        @MainThread
        public Loader<D> destroy(boolean z) {
            if (LoaderManagerImpl.DEBUG) {
                String str = LoaderManagerImpl.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("  Destroying: ");
                stringBuilder.append(this);
                Log.v(str, stringBuilder.toString());
            }
            this.mLoader.cancelLoad();
            this.mLoader.abandon();
            LoaderObserver loaderObserver = this.mObserver;
            if (loaderObserver != null) {
                removeObserver(loaderObserver);
                if (z) {
                    loaderObserver.reset();
                }
            }
            this.mLoader.unregisterListener(this);
            if ((loaderObserver == null || loaderObserver.hasDeliveredData()) && !z) {
                return this.mLoader;
            }
            this.mLoader.reset();
            return this.mPriorLoader;
        }

        public void onLoadComplete(@NonNull Loader<D> loader, @Nullable D d) {
            if (LoaderManagerImpl.DEBUG) {
                String str = LoaderManagerImpl.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onLoadComplete: ");
                stringBuilder.append(this);
                Log.v(str, stringBuilder.toString());
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                setValue(d);
                return;
            }
            if (LoaderManagerImpl.DEBUG) {
                Log.w(LoaderManagerImpl.TAG, "onLoadComplete was incorrectly called on a background thread");
            }
            postValue(d);
        }

        public void setValue(D d) {
            super.setValue(d);
            if (this.mPriorLoader != null) {
                this.mPriorLoader.reset();
                this.mPriorLoader = null;
            }
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append("LoaderInfo{");
            stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
            stringBuilder.append(" #");
            stringBuilder.append(this.mId);
            stringBuilder.append(" : ");
            DebugUtils.buildShortClassTag(this.mLoader, stringBuilder);
            stringBuilder.append("}}");
            return stringBuilder.toString();
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.mId);
            printWriter.print(" mArgs=");
            printWriter.println(this.mArgs);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.mLoader);
            Loader loader = this.mLoader;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("  ");
            loader.dump(stringBuilder.toString(), fileDescriptor, printWriter, strArr);
            if (this.mObserver != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.mObserver);
                LoaderObserver loaderObserver = this.mObserver;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str);
                stringBuilder2.append("  ");
                loaderObserver.dump(stringBuilder2.toString(), printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(getLoader().dataToString(getValue()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(hasActiveObservers());
        }
    }

    static class LoaderObserver<D> implements l<D> {
        @NonNull
        private final LoaderCallbacks<D> mCallback;
        private boolean mDeliveredData = false;
        @NonNull
        private final Loader<D> mLoader;

        LoaderObserver(@NonNull Loader<D> loader, @NonNull LoaderCallbacks<D> loaderCallbacks) {
            this.mLoader = loader;
            this.mCallback = loaderCallbacks;
        }

        public void onChanged(@Nullable D d) {
            if (LoaderManagerImpl.DEBUG) {
                String str = LoaderManagerImpl.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("  onLoadFinished in ");
                stringBuilder.append(this.mLoader);
                stringBuilder.append(": ");
                stringBuilder.append(this.mLoader.dataToString(d));
                Log.v(str, stringBuilder.toString());
            }
            this.mCallback.onLoadFinished(this.mLoader, d);
            this.mDeliveredData = true;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean hasDeliveredData() {
            return this.mDeliveredData;
        }

        /* Access modifiers changed, original: 0000 */
        @MainThread
        public void reset() {
            if (this.mDeliveredData) {
                if (LoaderManagerImpl.DEBUG) {
                    String str = LoaderManagerImpl.TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("  Resetting: ");
                    stringBuilder.append(this.mLoader);
                    Log.v(str, stringBuilder.toString());
                }
                this.mCallback.onLoaderReset(this.mLoader);
            }
        }

        public String toString() {
            return this.mCallback.toString();
        }

        public void dump(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print("mDeliveredData=");
            printWriter.println(this.mDeliveredData);
        }
    }

    static class LoaderViewModel extends p {
        private static final a FACTORY = new a() {
            @NonNull
            public <T extends p> T create(@NonNull Class<T> cls) {
                return new LoaderViewModel();
            }
        };
        private SparseArrayCompat<LoaderInfo> mLoaders = new SparseArrayCompat();

        LoaderViewModel() {
        }

        @NonNull
        static LoaderViewModel getInstance(r rVar) {
            return (LoaderViewModel) new q(rVar, FACTORY).a(LoaderViewModel.class);
        }

        /* Access modifiers changed, original: 0000 */
        public void putLoader(int i, @NonNull LoaderInfo loaderInfo) {
            this.mLoaders.put(i, loaderInfo);
        }

        /* Access modifiers changed, original: 0000 */
        public <D> LoaderInfo<D> getLoader(int i) {
            return (LoaderInfo) this.mLoaders.get(i);
        }

        /* Access modifiers changed, original: 0000 */
        public void removeLoader(int i) {
            this.mLoaders.remove(i);
        }

        /* Access modifiers changed, original: 0000 */
        public boolean hasRunningLoaders() {
            int size = this.mLoaders.size();
            for (int i = 0; i < size; i++) {
                if (((LoaderInfo) this.mLoaders.valueAt(i)).isCallbackWaitingForData()) {
                    return true;
                }
            }
            return false;
        }

        /* Access modifiers changed, original: 0000 */
        public void markForRedelivery() {
            int size = this.mLoaders.size();
            for (int i = 0; i < size; i++) {
                ((LoaderInfo) this.mLoaders.valueAt(i)).markForRedelivery();
            }
        }

        /* Access modifiers changed, original: protected */
        public void onCleared() {
            super.onCleared();
            int size = this.mLoaders.size();
            for (int i = 0; i < size; i++) {
                ((LoaderInfo) this.mLoaders.valueAt(i)).destroy(true);
            }
            this.mLoaders.clear();
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.mLoaders.size() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("    ");
                String stringBuilder2 = stringBuilder.toString();
                for (int i = 0; i < this.mLoaders.size(); i++) {
                    LoaderInfo loaderInfo = (LoaderInfo) this.mLoaders.valueAt(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.mLoaders.keyAt(i));
                    printWriter.print(": ");
                    printWriter.println(loaderInfo.toString());
                    loaderInfo.dump(stringBuilder2, fileDescriptor, printWriter, strArr);
                }
            }
        }
    }

    LoaderManagerImpl(@NonNull e eVar, @NonNull r rVar) {
        this.mLifecycleOwner = eVar;
        this.mLoaderViewModel = LoaderViewModel.getInstance(rVar);
    }

    @MainThread
    @NonNull
    private <D> Loader<D> createAndInstallLoader(int i, @Nullable Bundle bundle, @NonNull LoaderCallbacks<D> loaderCallbacks, @Nullable Loader<D> loader) {
        try {
            this.mCreatingLoader = true;
            Loader onCreateLoader = loaderCallbacks.onCreateLoader(i, bundle);
            if (!onCreateLoader.getClass().isMemberClass() || Modifier.isStatic(onCreateLoader.getClass().getModifiers())) {
                LoaderInfo loaderInfo = new LoaderInfo(i, bundle, onCreateLoader, loader);
                if (DEBUG) {
                    String str = TAG;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("  Created new loader ");
                    stringBuilder.append(loaderInfo);
                    Log.v(str, stringBuilder.toString());
                }
                this.mLoaderViewModel.putLoader(i, loaderInfo);
                return loaderInfo.setCallback(this.mLifecycleOwner, loaderCallbacks);
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Object returned from onCreateLoader must not be a non-static inner member class: ");
            stringBuilder2.append(onCreateLoader);
            throw new IllegalArgumentException(stringBuilder2.toString());
        } finally {
            this.mCreatingLoader = false;
        }
    }

    @MainThread
    @NonNull
    public <D> Loader<D> initLoader(int i, @Nullable Bundle bundle, @NonNull LoaderCallbacks<D> loaderCallbacks) {
        if (this.mCreatingLoader) {
            throw new IllegalStateException("Called while creating a loader");
        } else if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("initLoader must be called on the main thread");
        } else {
            LoaderInfo loader = this.mLoaderViewModel.getLoader(i);
            if (DEBUG) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("initLoader in ");
                stringBuilder.append(this);
                stringBuilder.append(": args=");
                stringBuilder.append(bundle);
                Log.v(str, stringBuilder.toString());
            }
            if (loader == null) {
                return createAndInstallLoader(i, bundle, loaderCallbacks, null);
            }
            if (DEBUG) {
                String str2 = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("  Re-using existing loader ");
                stringBuilder2.append(loader);
                Log.v(str2, stringBuilder2.toString());
            }
            return loader.setCallback(this.mLifecycleOwner, loaderCallbacks);
        }
    }

    @MainThread
    @NonNull
    public <D> Loader<D> restartLoader(int i, @Nullable Bundle bundle, @NonNull LoaderCallbacks<D> loaderCallbacks) {
        if (this.mCreatingLoader) {
            throw new IllegalStateException("Called while creating a loader");
        } else if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("restartLoader must be called on the main thread");
        } else {
            if (DEBUG) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("restartLoader in ");
                stringBuilder.append(this);
                stringBuilder.append(": args=");
                stringBuilder.append(bundle);
                Log.v(str, stringBuilder.toString());
            }
            LoaderInfo loader = this.mLoaderViewModel.getLoader(i);
            Loader loader2 = null;
            if (loader != null) {
                loader2 = loader.destroy(false);
            }
            return createAndInstallLoader(i, bundle, loaderCallbacks, loader2);
        }
    }

    @MainThread
    public void destroyLoader(int i) {
        if (this.mCreatingLoader) {
            throw new IllegalStateException("Called while creating a loader");
        } else if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("destroyLoader must be called on the main thread");
        } else {
            if (DEBUG) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("destroyLoader in ");
                stringBuilder.append(this);
                stringBuilder.append(" of ");
                stringBuilder.append(i);
                Log.v(str, stringBuilder.toString());
            }
            LoaderInfo loader = this.mLoaderViewModel.getLoader(i);
            if (loader != null) {
                loader.destroy(true);
                this.mLoaderViewModel.removeLoader(i);
            }
        }
    }

    @Nullable
    public <D> Loader<D> getLoader(int i) {
        if (this.mCreatingLoader) {
            throw new IllegalStateException("Called while creating a loader");
        }
        LoaderInfo loader = this.mLoaderViewModel.getLoader(i);
        return loader != null ? loader.getLoader() : null;
    }

    /* Access modifiers changed, original: 0000 */
    public void markForRedelivery() {
        this.mLoaderViewModel.markForRedelivery();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("LoaderManager{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" in ");
        DebugUtils.buildShortClassTag(this.mLifecycleOwner, stringBuilder);
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.mLoaderViewModel.dump(str, fileDescriptor, printWriter, strArr);
    }

    public boolean hasRunningLoaders() {
        return this.mLoaderViewModel.hasRunningLoaders();
    }
}
