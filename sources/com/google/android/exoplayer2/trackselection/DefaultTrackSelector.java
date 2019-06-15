package com.google.android.exoplayer2.trackselection;

import android.content.Context;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector.MappedTrackInfo;
import com.google.android.exoplayer2.trackselection.TrackSelection.Factory;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;

public class DefaultTrackSelector extends MappingTrackSelector {
    private static final float FRACTION_TO_CONSIDER_FULLSCREEN = 0.98f;
    private static final int[] NO_TRACKS = new int[0];
    private static final int WITHIN_RENDERER_CAPABILITIES_BONUS = 1000;
    private final Factory adaptiveTrackSelectionFactory;
    private final AtomicReference<Parameters> parametersReference;

    private static final class AudioConfigurationTuple {
        public final int channelCount;
        @Nullable
        public final String mimeType;
        public final int sampleRate;

        public AudioConfigurationTuple(int i, int i2, @Nullable String str) {
            this.channelCount = i;
            this.sampleRate = i2;
            this.mimeType = str;
        }

        public boolean equals(@Nullable Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AudioConfigurationTuple audioConfigurationTuple = (AudioConfigurationTuple) obj;
            if (!(this.channelCount == audioConfigurationTuple.channelCount && this.sampleRate == audioConfigurationTuple.sampleRate && TextUtils.equals(this.mimeType, audioConfigurationTuple.mimeType))) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return (31 * ((this.channelCount * 31) + this.sampleRate)) + (this.mimeType != null ? this.mimeType.hashCode() : 0);
        }
    }

    protected static final class AudioTrackScore implements Comparable<AudioTrackScore> {
        private final int bitrate;
        private final int channelCount;
        private final int defaultSelectionFlagScore;
        private final int matchLanguageScore;
        private final Parameters parameters;
        private final int sampleRate;
        private final int withinRendererCapabilitiesScore;

        public AudioTrackScore(Format format, Parameters parameters, int i) {
            this.parameters = parameters;
            this.withinRendererCapabilitiesScore = DefaultTrackSelector.isSupported(i, false);
            this.matchLanguageScore = DefaultTrackSelector.formatHasLanguage(format, parameters.preferredAudioLanguage);
            i = 1;
            if ((format.selectionFlags & 1) == 0) {
                i = 0;
            }
            this.defaultSelectionFlagScore = i;
            this.channelCount = format.channelCount;
            this.sampleRate = format.sampleRate;
            this.bitrate = format.bitrate;
        }

        public int compareTo(@NonNull AudioTrackScore audioTrackScore) {
            if (this.withinRendererCapabilitiesScore != audioTrackScore.withinRendererCapabilitiesScore) {
                return DefaultTrackSelector.compareInts(this.withinRendererCapabilitiesScore, audioTrackScore.withinRendererCapabilitiesScore);
            }
            if (this.matchLanguageScore != audioTrackScore.matchLanguageScore) {
                return DefaultTrackSelector.compareInts(this.matchLanguageScore, audioTrackScore.matchLanguageScore);
            }
            if (this.defaultSelectionFlagScore != audioTrackScore.defaultSelectionFlagScore) {
                return DefaultTrackSelector.compareInts(this.defaultSelectionFlagScore, audioTrackScore.defaultSelectionFlagScore);
            }
            if (this.parameters.forceLowestBitrate) {
                return DefaultTrackSelector.compareInts(audioTrackScore.bitrate, this.bitrate);
            }
            int i = 1;
            if (this.withinRendererCapabilitiesScore != 1) {
                i = -1;
            }
            if (this.channelCount != audioTrackScore.channelCount) {
                return i * DefaultTrackSelector.compareInts(this.channelCount, audioTrackScore.channelCount);
            }
            if (this.sampleRate != audioTrackScore.sampleRate) {
                return i * DefaultTrackSelector.compareInts(this.sampleRate, audioTrackScore.sampleRate);
            }
            return i * DefaultTrackSelector.compareInts(this.bitrate, audioTrackScore.bitrate);
        }
    }

    public static final class Parameters implements Parcelable {
        public static final Creator<Parameters> CREATOR = new Creator<Parameters>() {
            public Parameters createFromParcel(Parcel parcel) {
                return new Parameters(parcel);
            }

            public Parameters[] newArray(int i) {
                return new Parameters[i];
            }
        };
        public static final Parameters DEFAULT = new Parameters();
        public final boolean allowMixedMimeAdaptiveness;
        public final boolean allowNonSeamlessAdaptiveness;
        public final int disabledTextTrackSelectionFlags;
        public final boolean exceedRendererCapabilitiesIfNecessary;
        public final boolean exceedVideoConstraintsIfNecessary;
        public final boolean forceHighestSupportedBitrate;
        public final boolean forceLowestBitrate;
        public final int maxVideoBitrate;
        public final int maxVideoFrameRate;
        public final int maxVideoHeight;
        public final int maxVideoWidth;
        @Nullable
        public final String preferredAudioLanguage;
        @Nullable
        public final String preferredTextLanguage;
        private final SparseBooleanArray rendererDisabledFlags;
        public final boolean selectUndeterminedTextLanguage;
        private final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides;
        public final int tunnelingAudioSessionId;
        public final int viewportHeight;
        public final boolean viewportOrientationMayChange;
        public final int viewportWidth;

        public int describeContents() {
            return 0;
        }

        private Parameters() {
            this(new SparseArray(), new SparseBooleanArray(), null, null, false, 0, false, false, false, true, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, true, true, Integer.MAX_VALUE, Integer.MAX_VALUE, true, 0);
        }

        Parameters(SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray, SparseBooleanArray sparseBooleanArray, @Nullable String str, @Nullable String str2, boolean z, int i, boolean z2, boolean z3, boolean z4, boolean z5, int i2, int i3, int i4, int i5, boolean z6, boolean z7, int i6, int i7, boolean z8, int i8) {
            this.selectionOverrides = sparseArray;
            this.rendererDisabledFlags = sparseBooleanArray;
            this.preferredAudioLanguage = Util.normalizeLanguageCode(str);
            this.preferredTextLanguage = Util.normalizeLanguageCode(str2);
            this.selectUndeterminedTextLanguage = z;
            this.disabledTextTrackSelectionFlags = i;
            this.forceLowestBitrate = z2;
            this.forceHighestSupportedBitrate = z3;
            this.allowMixedMimeAdaptiveness = z4;
            this.allowNonSeamlessAdaptiveness = z5;
            this.maxVideoWidth = i2;
            this.maxVideoHeight = i3;
            this.maxVideoFrameRate = i4;
            this.maxVideoBitrate = i5;
            this.exceedVideoConstraintsIfNecessary = z6;
            this.exceedRendererCapabilitiesIfNecessary = z7;
            this.viewportWidth = i6;
            this.viewportHeight = i7;
            this.viewportOrientationMayChange = z8;
            this.tunnelingAudioSessionId = i8;
        }

        Parameters(Parcel parcel) {
            this.selectionOverrides = readSelectionOverrides(parcel);
            this.rendererDisabledFlags = parcel.readSparseBooleanArray();
            this.preferredAudioLanguage = parcel.readString();
            this.preferredTextLanguage = parcel.readString();
            this.selectUndeterminedTextLanguage = Util.readBoolean(parcel);
            this.disabledTextTrackSelectionFlags = parcel.readInt();
            this.forceLowestBitrate = Util.readBoolean(parcel);
            this.forceHighestSupportedBitrate = Util.readBoolean(parcel);
            this.allowMixedMimeAdaptiveness = Util.readBoolean(parcel);
            this.allowNonSeamlessAdaptiveness = Util.readBoolean(parcel);
            this.maxVideoWidth = parcel.readInt();
            this.maxVideoHeight = parcel.readInt();
            this.maxVideoFrameRate = parcel.readInt();
            this.maxVideoBitrate = parcel.readInt();
            this.exceedVideoConstraintsIfNecessary = Util.readBoolean(parcel);
            this.exceedRendererCapabilitiesIfNecessary = Util.readBoolean(parcel);
            this.viewportWidth = parcel.readInt();
            this.viewportHeight = parcel.readInt();
            this.viewportOrientationMayChange = Util.readBoolean(parcel);
            this.tunnelingAudioSessionId = parcel.readInt();
        }

        public final boolean getRendererDisabled(int i) {
            return this.rendererDisabledFlags.get(i);
        }

        public final boolean hasSelectionOverride(int i, TrackGroupArray trackGroupArray) {
            Map map = (Map) this.selectionOverrides.get(i);
            return map != null && map.containsKey(trackGroupArray);
        }

        @Nullable
        public final SelectionOverride getSelectionOverride(int i, TrackGroupArray trackGroupArray) {
            Map map = (Map) this.selectionOverrides.get(i);
            return map != null ? (SelectionOverride) map.get(trackGroupArray) : null;
        }

        public ParametersBuilder buildUpon() {
            return new ParametersBuilder(this);
        }

        public boolean equals(@Nullable Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Parameters parameters = (Parameters) obj;
            if (!(this.selectUndeterminedTextLanguage == parameters.selectUndeterminedTextLanguage && this.disabledTextTrackSelectionFlags == parameters.disabledTextTrackSelectionFlags && this.forceLowestBitrate == parameters.forceLowestBitrate && this.forceHighestSupportedBitrate == parameters.forceHighestSupportedBitrate && this.allowMixedMimeAdaptiveness == parameters.allowMixedMimeAdaptiveness && this.allowNonSeamlessAdaptiveness == parameters.allowNonSeamlessAdaptiveness && this.maxVideoWidth == parameters.maxVideoWidth && this.maxVideoHeight == parameters.maxVideoHeight && this.maxVideoFrameRate == parameters.maxVideoFrameRate && this.exceedVideoConstraintsIfNecessary == parameters.exceedVideoConstraintsIfNecessary && this.exceedRendererCapabilitiesIfNecessary == parameters.exceedRendererCapabilitiesIfNecessary && this.viewportOrientationMayChange == parameters.viewportOrientationMayChange && this.viewportWidth == parameters.viewportWidth && this.viewportHeight == parameters.viewportHeight && this.maxVideoBitrate == parameters.maxVideoBitrate && this.tunnelingAudioSessionId == parameters.tunnelingAudioSessionId && TextUtils.equals(this.preferredAudioLanguage, parameters.preferredAudioLanguage) && TextUtils.equals(this.preferredTextLanguage, parameters.preferredTextLanguage) && areRendererDisabledFlagsEqual(this.rendererDisabledFlags, parameters.rendererDisabledFlags) && areSelectionOverridesEqual(this.selectionOverrides, parameters.selectionOverrides))) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            int i;
            int i2 = ((((((((((((((((((((((((((((((this.selectUndeterminedTextLanguage * 31) + this.disabledTextTrackSelectionFlags) * 31) + this.forceLowestBitrate) * 31) + this.forceHighestSupportedBitrate) * 31) + this.allowMixedMimeAdaptiveness) * 31) + this.allowNonSeamlessAdaptiveness) * 31) + this.maxVideoWidth) * 31) + this.maxVideoHeight) * 31) + this.maxVideoFrameRate) * 31) + this.exceedVideoConstraintsIfNecessary) * 31) + this.exceedRendererCapabilitiesIfNecessary) * 31) + this.viewportOrientationMayChange) * 31) + this.viewportWidth) * 31) + this.viewportHeight) * 31) + this.maxVideoBitrate) * 31) + this.tunnelingAudioSessionId) * 31;
            int i3 = 0;
            if (this.preferredAudioLanguage == null) {
                i = 0;
            } else {
                i = this.preferredAudioLanguage.hashCode();
            }
            int i4 = 31 * (i2 + i);
            if (this.preferredTextLanguage != null) {
                i3 = this.preferredTextLanguage.hashCode();
            }
            return i4 + i3;
        }

        public void writeToParcel(Parcel parcel, int i) {
            writeSelectionOverridesToParcel(parcel, this.selectionOverrides);
            parcel.writeSparseBooleanArray(this.rendererDisabledFlags);
            parcel.writeString(this.preferredAudioLanguage);
            parcel.writeString(this.preferredTextLanguage);
            Util.writeBoolean(parcel, this.selectUndeterminedTextLanguage);
            parcel.writeInt(this.disabledTextTrackSelectionFlags);
            Util.writeBoolean(parcel, this.forceLowestBitrate);
            Util.writeBoolean(parcel, this.forceHighestSupportedBitrate);
            Util.writeBoolean(parcel, this.allowMixedMimeAdaptiveness);
            Util.writeBoolean(parcel, this.allowNonSeamlessAdaptiveness);
            parcel.writeInt(this.maxVideoWidth);
            parcel.writeInt(this.maxVideoHeight);
            parcel.writeInt(this.maxVideoFrameRate);
            parcel.writeInt(this.maxVideoBitrate);
            Util.writeBoolean(parcel, this.exceedVideoConstraintsIfNecessary);
            Util.writeBoolean(parcel, this.exceedRendererCapabilitiesIfNecessary);
            parcel.writeInt(this.viewportWidth);
            parcel.writeInt(this.viewportHeight);
            Util.writeBoolean(parcel, this.viewportOrientationMayChange);
            parcel.writeInt(this.tunnelingAudioSessionId);
        }

        private static SparseArray<Map<TrackGroupArray, SelectionOverride>> readSelectionOverrides(Parcel parcel) {
            int readInt = parcel.readInt();
            SparseArray sparseArray = new SparseArray(readInt);
            for (int i = 0; i < readInt; i++) {
                int readInt2 = parcel.readInt();
                int readInt3 = parcel.readInt();
                HashMap hashMap = new HashMap(readInt3);
                for (int i2 = 0; i2 < readInt3; i2++) {
                    hashMap.put((TrackGroupArray) parcel.readParcelable(TrackGroupArray.class.getClassLoader()), (SelectionOverride) parcel.readParcelable(SelectionOverride.class.getClassLoader()));
                }
                sparseArray.put(readInt2, hashMap);
            }
            return sparseArray;
        }

        private static void writeSelectionOverridesToParcel(Parcel parcel, SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray) {
            int size = sparseArray.size();
            parcel.writeInt(size);
            for (int i = 0; i < size; i++) {
                int keyAt = sparseArray.keyAt(i);
                Map map = (Map) sparseArray.valueAt(i);
                int size2 = map.size();
                parcel.writeInt(keyAt);
                parcel.writeInt(size2);
                for (Entry entry : map.entrySet()) {
                    parcel.writeParcelable((Parcelable) entry.getKey(), 0);
                    parcel.writeParcelable((Parcelable) entry.getValue(), 0);
                }
            }
        }

        private static boolean areRendererDisabledFlagsEqual(SparseBooleanArray sparseBooleanArray, SparseBooleanArray sparseBooleanArray2) {
            int size = sparseBooleanArray.size();
            if (sparseBooleanArray2.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (sparseBooleanArray2.indexOfKey(sparseBooleanArray.keyAt(i)) < 0) {
                    return false;
                }
            }
            return true;
        }

        private static boolean areSelectionOverridesEqual(SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray, SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray2) {
            int size = sparseArray.size();
            if (sparseArray2.size() != size) {
                return false;
            }
            int i = 0;
            while (i < size) {
                int indexOfKey = sparseArray2.indexOfKey(sparseArray.keyAt(i));
                if (indexOfKey < 0 || !areSelectionOverridesEqual((Map) sparseArray.valueAt(i), (Map) sparseArray2.valueAt(indexOfKey))) {
                    return false;
                }
                i++;
            }
            return true;
        }

        private static boolean areSelectionOverridesEqual(Map<TrackGroupArray, SelectionOverride> map, Map<TrackGroupArray, SelectionOverride> map2) {
            if (map2.size() != map.size()) {
                return false;
            }
            for (Entry entry : map.entrySet()) {
                TrackGroupArray trackGroupArray = (TrackGroupArray) entry.getKey();
                if (map2.containsKey(trackGroupArray)) {
                    if (!Util.areEqual(entry.getValue(), map2.get(trackGroupArray))) {
                    }
                }
                return false;
            }
            return true;
        }
    }

    public static final class ParametersBuilder {
        private boolean allowMixedMimeAdaptiveness;
        private boolean allowNonSeamlessAdaptiveness;
        private int disabledTextTrackSelectionFlags;
        private boolean exceedRendererCapabilitiesIfNecessary;
        private boolean exceedVideoConstraintsIfNecessary;
        private boolean forceHighestSupportedBitrate;
        private boolean forceLowestBitrate;
        private int maxVideoBitrate;
        private int maxVideoFrameRate;
        private int maxVideoHeight;
        private int maxVideoWidth;
        @Nullable
        private String preferredAudioLanguage;
        @Nullable
        private String preferredTextLanguage;
        private final SparseBooleanArray rendererDisabledFlags;
        private boolean selectUndeterminedTextLanguage;
        private final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides;
        private int tunnelingAudioSessionId;
        private int viewportHeight;
        private boolean viewportOrientationMayChange;
        private int viewportWidth;

        public ParametersBuilder() {
            this(Parameters.DEFAULT);
        }

        private ParametersBuilder(Parameters parameters) {
            this.selectionOverrides = cloneSelectionOverrides(parameters.selectionOverrides);
            this.rendererDisabledFlags = parameters.rendererDisabledFlags.clone();
            this.preferredAudioLanguage = parameters.preferredAudioLanguage;
            this.preferredTextLanguage = parameters.preferredTextLanguage;
            this.selectUndeterminedTextLanguage = parameters.selectUndeterminedTextLanguage;
            this.disabledTextTrackSelectionFlags = parameters.disabledTextTrackSelectionFlags;
            this.forceLowestBitrate = parameters.forceLowestBitrate;
            this.forceHighestSupportedBitrate = parameters.forceHighestSupportedBitrate;
            this.allowMixedMimeAdaptiveness = parameters.allowMixedMimeAdaptiveness;
            this.allowNonSeamlessAdaptiveness = parameters.allowNonSeamlessAdaptiveness;
            this.maxVideoWidth = parameters.maxVideoWidth;
            this.maxVideoHeight = parameters.maxVideoHeight;
            this.maxVideoFrameRate = parameters.maxVideoFrameRate;
            this.maxVideoBitrate = parameters.maxVideoBitrate;
            this.exceedVideoConstraintsIfNecessary = parameters.exceedVideoConstraintsIfNecessary;
            this.exceedRendererCapabilitiesIfNecessary = parameters.exceedRendererCapabilitiesIfNecessary;
            this.viewportWidth = parameters.viewportWidth;
            this.viewportHeight = parameters.viewportHeight;
            this.viewportOrientationMayChange = parameters.viewportOrientationMayChange;
            this.tunnelingAudioSessionId = parameters.tunnelingAudioSessionId;
        }

        public ParametersBuilder setPreferredAudioLanguage(String str) {
            this.preferredAudioLanguage = str;
            return this;
        }

        public ParametersBuilder setPreferredTextLanguage(String str) {
            this.preferredTextLanguage = str;
            return this;
        }

        public ParametersBuilder setSelectUndeterminedTextLanguage(boolean z) {
            this.selectUndeterminedTextLanguage = z;
            return this;
        }

        public ParametersBuilder setDisabledTextTrackSelectionFlags(int i) {
            this.disabledTextTrackSelectionFlags = i;
            return this;
        }

        public ParametersBuilder setForceLowestBitrate(boolean z) {
            this.forceLowestBitrate = z;
            return this;
        }

        public ParametersBuilder setForceHighestSupportedBitrate(boolean z) {
            this.forceHighestSupportedBitrate = z;
            return this;
        }

        public ParametersBuilder setAllowMixedMimeAdaptiveness(boolean z) {
            this.allowMixedMimeAdaptiveness = z;
            return this;
        }

        public ParametersBuilder setAllowNonSeamlessAdaptiveness(boolean z) {
            this.allowNonSeamlessAdaptiveness = z;
            return this;
        }

        public ParametersBuilder setMaxVideoSizeSd() {
            return setMaxVideoSize(1279, 719);
        }

        public ParametersBuilder clearVideoSizeConstraints() {
            return setMaxVideoSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        public ParametersBuilder setMaxVideoSize(int i, int i2) {
            this.maxVideoWidth = i;
            this.maxVideoHeight = i2;
            return this;
        }

        public ParametersBuilder setMaxVideoFrameRate(int i) {
            this.maxVideoFrameRate = i;
            return this;
        }

        public ParametersBuilder setMaxVideoBitrate(int i) {
            this.maxVideoBitrate = i;
            return this;
        }

        public ParametersBuilder setExceedVideoConstraintsIfNecessary(boolean z) {
            this.exceedVideoConstraintsIfNecessary = z;
            return this;
        }

        public ParametersBuilder setExceedRendererCapabilitiesIfNecessary(boolean z) {
            this.exceedRendererCapabilitiesIfNecessary = z;
            return this;
        }

        public ParametersBuilder setViewportSizeToPhysicalDisplaySize(Context context, boolean z) {
            Point physicalDisplaySize = Util.getPhysicalDisplaySize(context);
            return setViewportSize(physicalDisplaySize.x, physicalDisplaySize.y, z);
        }

        public ParametersBuilder clearViewportSizeConstraints() {
            return setViewportSize(Integer.MAX_VALUE, Integer.MAX_VALUE, true);
        }

        public ParametersBuilder setViewportSize(int i, int i2, boolean z) {
            this.viewportWidth = i;
            this.viewportHeight = i2;
            this.viewportOrientationMayChange = z;
            return this;
        }

        public final ParametersBuilder setRendererDisabled(int i, boolean z) {
            if (this.rendererDisabledFlags.get(i) == z) {
                return this;
            }
            if (z) {
                this.rendererDisabledFlags.put(i, true);
            } else {
                this.rendererDisabledFlags.delete(i);
            }
            return this;
        }

        public final ParametersBuilder setSelectionOverride(int i, TrackGroupArray trackGroupArray, SelectionOverride selectionOverride) {
            Map map = (Map) this.selectionOverrides.get(i);
            if (map == null) {
                map = new HashMap();
                this.selectionOverrides.put(i, map);
            }
            if (map.containsKey(trackGroupArray) && Util.areEqual(map.get(trackGroupArray), selectionOverride)) {
                return this;
            }
            map.put(trackGroupArray, selectionOverride);
            return this;
        }

        public final ParametersBuilder clearSelectionOverride(int i, TrackGroupArray trackGroupArray) {
            Map map = (Map) this.selectionOverrides.get(i);
            if (map == null || !map.containsKey(trackGroupArray)) {
                return this;
            }
            map.remove(trackGroupArray);
            if (map.isEmpty()) {
                this.selectionOverrides.remove(i);
            }
            return this;
        }

        public final ParametersBuilder clearSelectionOverrides(int i) {
            Map map = (Map) this.selectionOverrides.get(i);
            if (map == null || map.isEmpty()) {
                return this;
            }
            this.selectionOverrides.remove(i);
            return this;
        }

        public final ParametersBuilder clearSelectionOverrides() {
            if (this.selectionOverrides.size() == 0) {
                return this;
            }
            this.selectionOverrides.clear();
            return this;
        }

        public ParametersBuilder setTunnelingAudioSessionId(int i) {
            if (this.tunnelingAudioSessionId == i) {
                return this;
            }
            this.tunnelingAudioSessionId = i;
            return this;
        }

        public Parameters build() {
            SparseArray sparseArray = this.selectionOverrides;
            SparseBooleanArray sparseBooleanArray = this.rendererDisabledFlags;
            String str = this.preferredAudioLanguage;
            String str2 = this.preferredTextLanguage;
            boolean z = this.selectUndeterminedTextLanguage;
            int i = this.disabledTextTrackSelectionFlags;
            boolean z2 = this.forceLowestBitrate;
            boolean z3 = this.forceHighestSupportedBitrate;
            boolean z4 = this.allowMixedMimeAdaptiveness;
            boolean z5 = this.allowNonSeamlessAdaptiveness;
            int i2 = this.maxVideoWidth;
            int i3 = this.maxVideoHeight;
            int i4 = this.maxVideoFrameRate;
            int i5 = this.maxVideoBitrate;
            int i6 = i5;
            return new Parameters(sparseArray, sparseBooleanArray, str, str2, z, i, z2, z3, z4, z5, i2, i3, i4, i6, this.exceedVideoConstraintsIfNecessary, this.exceedRendererCapabilitiesIfNecessary, this.viewportWidth, this.viewportHeight, this.viewportOrientationMayChange, this.tunnelingAudioSessionId);
        }

        private static SparseArray<Map<TrackGroupArray, SelectionOverride>> cloneSelectionOverrides(SparseArray<Map<TrackGroupArray, SelectionOverride>> sparseArray) {
            SparseArray sparseArray2 = new SparseArray();
            for (int i = 0; i < sparseArray.size(); i++) {
                sparseArray2.put(sparseArray.keyAt(i), new HashMap((Map) sparseArray.valueAt(i)));
            }
            return sparseArray2;
        }
    }

    public static final class SelectionOverride implements Parcelable {
        public static final Creator<SelectionOverride> CREATOR = new Creator<SelectionOverride>() {
            public SelectionOverride createFromParcel(Parcel parcel) {
                return new SelectionOverride(parcel);
            }

            public SelectionOverride[] newArray(int i) {
                return new SelectionOverride[i];
            }
        };
        public final int groupIndex;
        public final int length;
        public final int[] tracks;

        public int describeContents() {
            return 0;
        }

        public SelectionOverride(int i, int... iArr) {
            this.groupIndex = i;
            this.tracks = Arrays.copyOf(iArr, iArr.length);
            this.length = iArr.length;
            Arrays.sort(this.tracks);
        }

        SelectionOverride(Parcel parcel) {
            this.groupIndex = parcel.readInt();
            this.length = parcel.readByte();
            this.tracks = new int[this.length];
            parcel.readIntArray(this.tracks);
        }

        public boolean containsTrack(int i) {
            for (int i2 : this.tracks) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return (31 * this.groupIndex) + Arrays.hashCode(this.tracks);
        }

        public boolean equals(@Nullable Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SelectionOverride selectionOverride = (SelectionOverride) obj;
            if (!(this.groupIndex == selectionOverride.groupIndex && Arrays.equals(this.tracks, selectionOverride.tracks))) {
                z = false;
            }
            return z;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.groupIndex);
            parcel.writeInt(this.tracks.length);
            parcel.writeIntArray(this.tracks);
        }
    }

    private static int compareFormatValues(int i, int i2) {
        return i == -1 ? i2 == -1 ? 0 : -1 : i2 == -1 ? 1 : i - i2;
    }

    private static int compareInts(int i, int i2) {
        return i > i2 ? 1 : i2 > i ? -1 : 0;
    }

    protected static boolean isSupported(int i, boolean z) {
        i &= 7;
        return i == 4 || (z && i == 3);
    }

    public DefaultTrackSelector() {
        this(new AdaptiveTrackSelection.Factory());
    }

    @Deprecated
    public DefaultTrackSelector(BandwidthMeter bandwidthMeter) {
        this(new AdaptiveTrackSelection.Factory(bandwidthMeter));
    }

    public DefaultTrackSelector(Factory factory) {
        this.adaptiveTrackSelectionFactory = factory;
        this.parametersReference = new AtomicReference(Parameters.DEFAULT);
    }

    public void setParameters(Parameters parameters) {
        Assertions.checkNotNull(parameters);
        if (!((Parameters) this.parametersReference.getAndSet(parameters)).equals(parameters)) {
            invalidate();
        }
    }

    public void setParameters(ParametersBuilder parametersBuilder) {
        setParameters(parametersBuilder.build());
    }

    public Parameters getParameters() {
        return (Parameters) this.parametersReference.get();
    }

    public ParametersBuilder buildUponParameters() {
        return getParameters().buildUpon();
    }

    @Deprecated
    public final void setRendererDisabled(int i, boolean z) {
        setParameters(buildUponParameters().setRendererDisabled(i, z));
    }

    @Deprecated
    public final boolean getRendererDisabled(int i) {
        return getParameters().getRendererDisabled(i);
    }

    @Deprecated
    public final void setSelectionOverride(int i, TrackGroupArray trackGroupArray, SelectionOverride selectionOverride) {
        setParameters(buildUponParameters().setSelectionOverride(i, trackGroupArray, selectionOverride));
    }

    @Deprecated
    public final boolean hasSelectionOverride(int i, TrackGroupArray trackGroupArray) {
        return getParameters().hasSelectionOverride(i, trackGroupArray);
    }

    @Nullable
    @Deprecated
    public final SelectionOverride getSelectionOverride(int i, TrackGroupArray trackGroupArray) {
        return getParameters().getSelectionOverride(i, trackGroupArray);
    }

    @Deprecated
    public final void clearSelectionOverride(int i, TrackGroupArray trackGroupArray) {
        setParameters(buildUponParameters().clearSelectionOverride(i, trackGroupArray));
    }

    @Deprecated
    public final void clearSelectionOverrides(int i) {
        setParameters(buildUponParameters().clearSelectionOverrides(i));
    }

    @Deprecated
    public final void clearSelectionOverrides() {
        setParameters(buildUponParameters().clearSelectionOverrides());
    }

    @Deprecated
    public void setTunnelingAudioSessionId(int i) {
        setParameters(buildUponParameters().setTunnelingAudioSessionId(i));
    }

    /* Access modifiers changed, original: protected|final */
    public final Pair<RendererConfiguration[], TrackSelection[]> selectTracks(MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2) throws ExoPlaybackException {
        Parameters parameters = (Parameters) this.parametersReference.get();
        int rendererCount = mappedTrackInfo.getRendererCount();
        TrackSelection[] selectAllTracks = selectAllTracks(mappedTrackInfo, iArr, iArr2, parameters);
        for (int i = 0; i < rendererCount; i++) {
            if (parameters.getRendererDisabled(i)) {
                selectAllTracks[i] = null;
            } else {
                TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(i);
                if (parameters.hasSelectionOverride(i, trackGroups)) {
                    SelectionOverride selectionOverride = parameters.getSelectionOverride(i, trackGroups);
                    if (selectionOverride == null) {
                        selectAllTracks[i] = null;
                    } else if (selectionOverride.length == 1) {
                        selectAllTracks[i] = new FixedTrackSelection(trackGroups.get(selectionOverride.groupIndex), selectionOverride.tracks[0]);
                    } else {
                        selectAllTracks[i] = ((Factory) Assertions.checkNotNull(this.adaptiveTrackSelectionFactory)).createTrackSelection(trackGroups.get(selectionOverride.groupIndex), getBandwidthMeter(), selectionOverride.tracks);
                    }
                }
            }
        }
        RendererConfiguration[] rendererConfigurationArr = new RendererConfiguration[rendererCount];
        for (int i2 = 0; i2 < rendererCount; i2++) {
            int i3 = (parameters.getRendererDisabled(i2) || (mappedTrackInfo.getRendererType(i2) != 6 && selectAllTracks[i2] == null)) ? 0 : 1;
            rendererConfigurationArr[i2] = i3 != 0 ? RendererConfiguration.DEFAULT : null;
        }
        maybeConfigureRenderersForTunneling(mappedTrackInfo, iArr, rendererConfigurationArr, selectAllTracks, parameters.tunnelingAudioSessionId);
        return Pair.create(rendererConfigurationArr, selectAllTracks);
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Missing block: B:27:0x0098, code skipped:
            r8 = r3;
            r16 = r4;
            r17 = r5;
            r1 = r13;
            r13 = r2;
     */
    /* JADX WARNING: Missing block: B:42:0x00e4, code skipped:
            r3 = r8;
            r2 = r13;
     */
    /* JADX WARNING: Missing block: B:43:0x00e6, code skipped:
            r4 = r16;
            r5 = r17;
     */
    /* JADX WARNING: Missing block: B:44:0x00ea, code skipped:
            r12 = r12 + 1;
            r13 = r1;
     */
    public com.google.android.exoplayer2.trackselection.TrackSelection[] selectAllTracks(com.google.android.exoplayer2.trackselection.MappingTrackSelector.MappedTrackInfo r21, int[][][] r22, int[] r23, com.google.android.exoplayer2.trackselection.DefaultTrackSelector.Parameters r24) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
        r20 = this;
        r6 = r20;
        r7 = r21;
        r9 = r24;
        r10 = r21.getRendererCount();
        r11 = new com.google.android.exoplayer2.trackselection.TrackSelection[r10];
        r12 = 0;
        r0 = r12;
        r13 = r0;
        r14 = r13;
    L_0x0010:
        if (r13 >= r10) goto L_0x0045;
    L_0x0012:
        r1 = 2;
        r2 = r7.getRendererType(r13);
        if (r1 != r2) goto L_0x0042;
    L_0x0019:
        r15 = 1;
        if (r0 != 0) goto L_0x0035;
    L_0x001c:
        r1 = r7.getTrackGroups(r13);
        r2 = r22[r13];
        r3 = r23[r13];
        r5 = r6.adaptiveTrackSelectionFactory;
        r0 = r6;
        r4 = r9;
        r0 = r0.selectVideoTrack(r1, r2, r3, r4, r5);
        r11[r13] = r0;
        r0 = r11[r13];
        if (r0 == 0) goto L_0x0034;
    L_0x0032:
        r0 = r15;
        goto L_0x0035;
    L_0x0034:
        r0 = r12;
    L_0x0035:
        r1 = r7.getTrackGroups(r13);
        r1 = r1.length;
        if (r1 <= 0) goto L_0x003e;
    L_0x003d:
        goto L_0x003f;
    L_0x003e:
        r15 = r12;
    L_0x003f:
        r1 = r14 | r15;
        r14 = r1;
    L_0x0042:
        r13 = r13 + 1;
        goto L_0x0010;
    L_0x0045:
        r0 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r13 = -1;
        r15 = 0;
        r5 = r0;
        r2 = r13;
        r4 = r2;
        r3 = r15;
    L_0x004d:
        if (r12 >= r10) goto L_0x00ef;
    L_0x004f:
        r0 = r7.getRendererType(r12);
        switch(r0) {
            case 1: goto L_0x00a0;
            case 2: goto L_0x0098;
            case 3: goto L_0x006b;
            default: goto L_0x0056;
        };
    L_0x0056:
        r8 = r3;
        r16 = r4;
        r17 = r5;
        r1 = r13;
        r13 = r2;
        r2 = r7.getTrackGroups(r12);
        r3 = r22[r12];
        r0 = r6.selectOtherTrack(r0, r2, r3, r9);
        r11[r12] = r0;
        goto L_0x00e4;
    L_0x006b:
        r0 = r7.getTrackGroups(r12);
        r1 = r22[r12];
        r0 = r6.selectTextTrack(r0, r1, r9);
        if (r0 == 0) goto L_0x0098;
    L_0x0077:
        r1 = r0.second;
        r1 = (java.lang.Integer) r1;
        r1 = r1.intValue();
        if (r1 <= r5) goto L_0x0098;
    L_0x0081:
        if (r4 == r13) goto L_0x0085;
    L_0x0083:
        r11[r4] = r15;
    L_0x0085:
        r1 = r0.first;
        r1 = (com.google.android.exoplayer2.trackselection.TrackSelection) r1;
        r11[r12] = r1;
        r0 = r0.second;
        r0 = (java.lang.Integer) r0;
        r0 = r0.intValue();
        r5 = r0;
        r4 = r12;
        r1 = r13;
        goto L_0x00ea;
    L_0x0098:
        r8 = r3;
        r16 = r4;
        r17 = r5;
        r1 = r13;
        r13 = r2;
        goto L_0x00e4;
    L_0x00a0:
        r1 = r7.getTrackGroups(r12);
        r16 = r22[r12];
        r17 = r23[r12];
        if (r14 == 0) goto L_0x00ad;
    L_0x00aa:
        r18 = r15;
        goto L_0x00b1;
    L_0x00ad:
        r0 = r6.adaptiveTrackSelectionFactory;
        r18 = r0;
    L_0x00b1:
        r0 = r6;
        r13 = r2;
        r2 = r16;
        r8 = r3;
        r3 = r17;
        r16 = r4;
        r4 = r9;
        r17 = r5;
        r5 = r18;
        r0 = r0.selectAudioTrack(r1, r2, r3, r4, r5);
        if (r0 == 0) goto L_0x00e3;
    L_0x00c5:
        if (r8 == 0) goto L_0x00d1;
    L_0x00c7:
        r1 = r0.second;
        r1 = (com.google.android.exoplayer2.trackselection.DefaultTrackSelector.AudioTrackScore) r1;
        r1 = r1.compareTo(r8);
        if (r1 <= 0) goto L_0x00e3;
    L_0x00d1:
        r1 = -1;
        if (r13 == r1) goto L_0x00d6;
    L_0x00d4:
        r11[r13] = r15;
    L_0x00d6:
        r2 = r0.first;
        r2 = (com.google.android.exoplayer2.trackselection.TrackSelection) r2;
        r11[r12] = r2;
        r0 = r0.second;
        r0 = (com.google.android.exoplayer2.trackselection.DefaultTrackSelector.AudioTrackScore) r0;
        r3 = r0;
        r2 = r12;
        goto L_0x00e6;
    L_0x00e3:
        r1 = -1;
    L_0x00e4:
        r3 = r8;
        r2 = r13;
    L_0x00e6:
        r4 = r16;
        r5 = r17;
    L_0x00ea:
        r12 = r12 + 1;
        r13 = r1;
        goto L_0x004d;
    L_0x00ef:
        return r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.selectAllTracks(com.google.android.exoplayer2.trackselection.MappingTrackSelector$MappedTrackInfo, int[][][], int[], com.google.android.exoplayer2.trackselection.DefaultTrackSelector$Parameters):com.google.android.exoplayer2.trackselection.TrackSelection[]");
    }

    /* Access modifiers changed, original: protected */
    @Nullable
    public TrackSelection selectVideoTrack(TrackGroupArray trackGroupArray, int[][] iArr, int i, Parameters parameters, @Nullable Factory factory) throws ExoPlaybackException {
        TrackSelection selectAdaptiveVideoTrack = (parameters.forceHighestSupportedBitrate || parameters.forceLowestBitrate || factory == null) ? null : selectAdaptiveVideoTrack(trackGroupArray, iArr, i, parameters, factory, getBandwidthMeter());
        return selectAdaptiveVideoTrack == null ? selectFixedVideoTrack(trackGroupArray, iArr, parameters) : selectAdaptiveVideoTrack;
    }

    @Nullable
    private static TrackSelection selectAdaptiveVideoTrack(TrackGroupArray trackGroupArray, int[][] iArr, int i, Parameters parameters, Factory factory, BandwidthMeter bandwidthMeter) throws ExoPlaybackException {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        Parameters parameters2 = parameters;
        int i2 = parameters2.allowNonSeamlessAdaptiveness ? 24 : 16;
        boolean z = parameters2.allowMixedMimeAdaptiveness && (i & i2) != 0;
        int i3 = 0;
        while (i3 < trackGroupArray2.length) {
            TrackGroup trackGroup = trackGroupArray2.get(i3);
            TrackGroup trackGroup2 = trackGroup;
            int[] adaptiveVideoTracksForGroup = getAdaptiveVideoTracksForGroup(trackGroup, iArr[i3], z, i2, parameters2.maxVideoWidth, parameters2.maxVideoHeight, parameters2.maxVideoFrameRate, parameters2.maxVideoBitrate, parameters2.viewportWidth, parameters2.viewportHeight, parameters2.viewportOrientationMayChange);
            if (adaptiveVideoTracksForGroup.length > 0) {
                return ((Factory) Assertions.checkNotNull(factory)).createTrackSelection(trackGroup2, bandwidthMeter, adaptiveVideoTracksForGroup);
            }
            BandwidthMeter bandwidthMeter2 = bandwidthMeter;
            i3++;
            trackGroupArray2 = trackGroupArray;
        }
        return null;
    }

    private static int[] getAdaptiveVideoTracksForGroup(TrackGroup trackGroup, int[] iArr, boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z2) {
        TrackGroup trackGroup2 = trackGroup;
        if (trackGroup2.length < 2) {
            return NO_TRACKS;
        }
        List viewportFilteredTrackIndices = getViewportFilteredTrackIndices(trackGroup2, i6, i7, z2);
        if (viewportFilteredTrackIndices.size() < 2) {
            return NO_TRACKS;
        }
        String str;
        if (z) {
            str = null;
        } else {
            HashSet hashSet = new HashSet();
            String str2 = null;
            int i8 = 0;
            int i9 = i8;
            while (i8 < viewportFilteredTrackIndices.size()) {
                String str3 = trackGroup2.getFormat(((Integer) viewportFilteredTrackIndices.get(i8)).intValue()).sampleMimeType;
                if (hashSet.add(str3)) {
                    String str4 = str3;
                    int adaptiveVideoTrackCountForMimeType = getAdaptiveVideoTrackCountForMimeType(trackGroup2, iArr, i, str3, i2, i3, i4, i5, viewportFilteredTrackIndices);
                    if (adaptiveVideoTrackCountForMimeType > i9) {
                        i9 = adaptiveVideoTrackCountForMimeType;
                        str2 = str4;
                    }
                }
                i8++;
            }
            str = str2;
        }
        filterAdaptiveVideoTrackCountForMimeType(trackGroup2, iArr, i, str, i2, i3, i4, i5, viewportFilteredTrackIndices);
        return viewportFilteredTrackIndices.size() < 2 ? NO_TRACKS : Util.toArray(viewportFilteredTrackIndices);
    }

    private static int getAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i, @Nullable String str, int i2, int i3, int i4, int i5, List<Integer> list) {
        int i6 = 0;
        int i7 = 0;
        while (i6 < list.size()) {
            int intValue = ((Integer) list.get(i6)).intValue();
            if (isSupportedAdaptiveVideoTrack(trackGroup.getFormat(intValue), str, iArr[intValue], i, i2, i3, i4, i5)) {
                i7++;
            }
            i6++;
        }
        return i7;
    }

    private static void filterAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i, @Nullable String str, int i2, int i3, int i4, int i5, List<Integer> list) {
        List<Integer> list2 = list;
        for (int size = list.size() - 1; size >= 0; size--) {
            int intValue = ((Integer) list2.get(size)).intValue();
            if (!isSupportedAdaptiveVideoTrack(trackGroup.getFormat(intValue), str, iArr[intValue], i, i2, i3, i4, i5)) {
                list2.remove(size);
            }
        }
    }

    private static boolean isSupportedAdaptiveVideoTrack(Format format, @Nullable String str, int i, int i2, int i3, int i4, int i5, int i6) {
        if (!isSupported(i, false) || (i & i2) == 0) {
            return false;
        }
        if (str != null && !Util.areEqual(format.sampleMimeType, str)) {
            return false;
        }
        if (format.width != -1 && format.width > i3) {
            return false;
        }
        if (format.height != -1 && format.height > i4) {
            return false;
        }
        if (format.frameRate != -1.0f && format.frameRate > ((float) i5)) {
            return false;
        }
        if (format.bitrate == -1 || format.bitrate <= i6) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Missing block: B:44:0x00a5, code skipped:
            if (compareFormatValues(r2.bitrate, r10) < 0) goto L_0x00a7;
     */
    @android.support.annotation.Nullable
    private static com.google.android.exoplayer2.trackselection.TrackSelection selectFixedVideoTrack(com.google.android.exoplayer2.source.TrackGroupArray r21, int[][] r22, com.google.android.exoplayer2.trackselection.DefaultTrackSelector.Parameters r23) {
        /*
        r0 = r21;
        r1 = r23;
        r3 = -1;
        r9 = r3;
        r10 = r9;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
    L_0x000b:
        r11 = r0.length;
        if (r5 >= r11) goto L_0x00ee;
    L_0x000f:
        r11 = r0.get(r5);
        r12 = r1.viewportWidth;
        r13 = r1.viewportHeight;
        r14 = r1.viewportOrientationMayChange;
        r12 = getViewportFilteredTrackIndices(r11, r12, r13, r14);
        r14 = r22[r5];
        r15 = r10;
        r10 = r9;
        r9 = r8;
        r8 = r7;
        r7 = r6;
        r6 = 0;
    L_0x0025:
        r2 = r11.length;
        if (r6 >= r2) goto L_0x00df;
    L_0x0029:
        r2 = r14[r6];
        r4 = r1.exceedRendererCapabilitiesIfNecessary;
        r2 = isSupported(r2, r4);
        if (r2 == 0) goto L_0x00d4;
    L_0x0033:
        r2 = r11.getFormat(r6);
        r4 = java.lang.Integer.valueOf(r6);
        r4 = r12.contains(r4);
        r18 = 1;
        if (r4 == 0) goto L_0x0077;
    L_0x0043:
        r4 = r2.width;
        if (r4 == r3) goto L_0x004d;
    L_0x0047:
        r4 = r2.width;
        r3 = r1.maxVideoWidth;
        if (r4 > r3) goto L_0x0077;
    L_0x004d:
        r3 = r2.height;
        r4 = -1;
        if (r3 == r4) goto L_0x0058;
    L_0x0052:
        r3 = r2.height;
        r4 = r1.maxVideoHeight;
        if (r3 > r4) goto L_0x0077;
    L_0x0058:
        r3 = r2.frameRate;
        r4 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1));
        if (r3 == 0) goto L_0x0069;
    L_0x0060:
        r3 = r2.frameRate;
        r4 = r1.maxVideoFrameRate;
        r4 = (float) r4;
        r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1));
        if (r3 > 0) goto L_0x0077;
    L_0x0069:
        r3 = r2.bitrate;
        r4 = -1;
        if (r3 == r4) goto L_0x0074;
    L_0x006e:
        r3 = r2.bitrate;
        r4 = r1.maxVideoBitrate;
        if (r3 > r4) goto L_0x0077;
    L_0x0074:
        r3 = r18;
        goto L_0x0078;
    L_0x0077:
        r3 = 0;
    L_0x0078:
        if (r3 != 0) goto L_0x007f;
    L_0x007a:
        r4 = r1.exceedVideoConstraintsIfNecessary;
        if (r4 != 0) goto L_0x007f;
    L_0x007e:
        goto L_0x00d4;
    L_0x007f:
        if (r3 == 0) goto L_0x0083;
    L_0x0081:
        r4 = 2;
        goto L_0x0085;
    L_0x0083:
        r4 = r18;
    L_0x0085:
        r0 = r14[r6];
        r19 = r8;
        r8 = 0;
        r0 = isSupported(r0, r8);
        if (r0 == 0) goto L_0x0092;
    L_0x0090:
        r4 = r4 + 1000;
    L_0x0092:
        if (r4 <= r9) goto L_0x0097;
    L_0x0094:
        r17 = r18;
        goto L_0x0099;
    L_0x0097:
        r17 = r8;
    L_0x0099:
        if (r4 != r9) goto L_0x00c8;
    L_0x009b:
        r8 = r1.forceLowestBitrate;
        if (r8 == 0) goto L_0x00ad;
    L_0x009f:
        r0 = r2.bitrate;
        r0 = compareFormatValues(r0, r10);
        if (r0 >= 0) goto L_0x00aa;
    L_0x00a7:
        r17 = r18;
        goto L_0x00c8;
    L_0x00aa:
        r17 = 0;
        goto L_0x00c8;
    L_0x00ad:
        r8 = r2.getPixelCount();
        if (r8 == r15) goto L_0x00b8;
    L_0x00b3:
        r8 = compareFormatValues(r8, r15);
        goto L_0x00be;
    L_0x00b8:
        r8 = r2.bitrate;
        r8 = compareFormatValues(r8, r10);
    L_0x00be:
        if (r0 == 0) goto L_0x00c5;
    L_0x00c0:
        if (r3 == 0) goto L_0x00c5;
    L_0x00c2:
        if (r8 <= 0) goto L_0x00aa;
    L_0x00c4:
        goto L_0x00a7;
    L_0x00c5:
        if (r8 >= 0) goto L_0x00aa;
    L_0x00c7:
        goto L_0x00a7;
    L_0x00c8:
        if (r17 == 0) goto L_0x00d6;
    L_0x00ca:
        r10 = r2.bitrate;
        r15 = r2.getPixelCount();
        r9 = r4;
        r8 = r6;
        r7 = r11;
        goto L_0x00d8;
    L_0x00d4:
        r19 = r8;
    L_0x00d6:
        r8 = r19;
    L_0x00d8:
        r6 = r6 + 1;
        r0 = r21;
        r3 = -1;
        goto L_0x0025;
    L_0x00df:
        r19 = r8;
        r5 = r5 + 1;
        r6 = r7;
        r8 = r9;
        r9 = r10;
        r10 = r15;
        r7 = r19;
        r0 = r21;
        r3 = -1;
        goto L_0x000b;
    L_0x00ee:
        if (r6 != 0) goto L_0x00f3;
    L_0x00f0:
        r16 = 0;
        goto L_0x00fa;
    L_0x00f3:
        r2 = new com.google.android.exoplayer2.trackselection.FixedTrackSelection;
        r2.<init>(r6, r7);
        r16 = r2;
    L_0x00fa:
        return r16;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.selectFixedVideoTrack(com.google.android.exoplayer2.source.TrackGroupArray, int[][], com.google.android.exoplayer2.trackselection.DefaultTrackSelector$Parameters):com.google.android.exoplayer2.trackselection.TrackSelection");
    }

    /* Access modifiers changed, original: protected */
    @Nullable
    public Pair<TrackSelection, AudioTrackScore> selectAudioTrack(TrackGroupArray trackGroupArray, int[][] iArr, int i, Parameters parameters, @Nullable Factory factory) throws ExoPlaybackException {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        Parameters parameters2 = parameters;
        Factory factory2 = factory;
        Object obj = null;
        AudioTrackScore audioTrackScore = null;
        int i2 = -1;
        int i3 = i2;
        int i4 = 0;
        while (i4 < trackGroupArray2.length) {
            TrackGroup trackGroup = trackGroupArray2.get(i4);
            int[] iArr2 = iArr[i4];
            int i5 = i3;
            AudioTrackScore audioTrackScore2 = audioTrackScore;
            int i6 = i2;
            for (i2 = 0; i2 < trackGroup.length; i2++) {
                if (isSupported(iArr2[i2], parameters2.exceedRendererCapabilitiesIfNecessary)) {
                    AudioTrackScore audioTrackScore3 = new AudioTrackScore(trackGroup.getFormat(i2), parameters2, iArr2[i2]);
                    if (audioTrackScore2 == null || audioTrackScore3.compareTo(audioTrackScore2) > 0) {
                        i6 = i4;
                        i5 = i2;
                        audioTrackScore2 = audioTrackScore3;
                    }
                }
            }
            i4++;
            i2 = i6;
            audioTrackScore = audioTrackScore2;
            i3 = i5;
        }
        if (i2 == -1) {
            return null;
        }
        TrackGroup trackGroup2 = trackGroupArray2.get(i2);
        if (!(parameters2.forceHighestSupportedBitrate || parameters2.forceLowestBitrate || factory2 == null)) {
            int[] adaptiveAudioTracks = getAdaptiveAudioTracks(trackGroup2, iArr[i2], parameters2.allowMixedMimeAdaptiveness);
            if (adaptiveAudioTracks.length > 0) {
                obj = factory2.createTrackSelection(trackGroup2, getBandwidthMeter(), adaptiveAudioTracks);
            }
        }
        if (obj == null) {
            obj = new FixedTrackSelection(trackGroup2, i3);
        }
        return Pair.create(obj, Assertions.checkNotNull(audioTrackScore));
    }

    private static int[] getAdaptiveAudioTracks(TrackGroup trackGroup, int[] iArr, boolean z) {
        HashSet hashSet = new HashSet();
        int i = 0;
        Object obj = null;
        int i2 = 0;
        int i3 = i2;
        while (i2 < trackGroup.length) {
            Format format = trackGroup.getFormat(i2);
            AudioConfigurationTuple audioConfigurationTuple = new AudioConfigurationTuple(format.channelCount, format.sampleRate, z ? null : format.sampleMimeType);
            if (hashSet.add(audioConfigurationTuple)) {
                int adaptiveAudioTrackCount = getAdaptiveAudioTrackCount(trackGroup, iArr, audioConfigurationTuple);
                if (adaptiveAudioTrackCount > i3) {
                    i3 = adaptiveAudioTrackCount;
                    obj = audioConfigurationTuple;
                }
            }
            i2++;
        }
        if (i3 <= 1) {
            return NO_TRACKS;
        }
        int[] iArr2 = new int[i3];
        int i4 = 0;
        while (i < trackGroup.length) {
            if (isSupportedAdaptiveAudioTrack(trackGroup.getFormat(i), iArr[i], (AudioConfigurationTuple) Assertions.checkNotNull(obj))) {
                int i5 = i4 + 1;
                iArr2[i4] = i;
                i4 = i5;
            }
            i++;
        }
        return iArr2;
    }

    private static int getAdaptiveAudioTrackCount(TrackGroup trackGroup, int[] iArr, AudioConfigurationTuple audioConfigurationTuple) {
        int i = 0;
        int i2 = 0;
        while (i < trackGroup.length) {
            if (isSupportedAdaptiveAudioTrack(trackGroup.getFormat(i), iArr[i], audioConfigurationTuple)) {
                i2++;
            }
            i++;
        }
        return i2;
    }

    private static boolean isSupportedAdaptiveAudioTrack(Format format, int i, AudioConfigurationTuple audioConfigurationTuple) {
        if (!isSupported(i, false) || format.channelCount != audioConfigurationTuple.channelCount || format.sampleRate != audioConfigurationTuple.sampleRate) {
            return false;
        }
        if (audioConfigurationTuple.mimeType == null || TextUtils.equals(audioConfigurationTuple.mimeType, format.sampleMimeType)) {
            return true;
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    @Nullable
    public Pair<TrackSelection, Integer> selectTextTrack(TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) throws ExoPlaybackException {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        Parameters parameters2 = parameters;
        int i = 0;
        int i2 = i;
        int i3 = i2;
        TrackGroup trackGroup = null;
        while (i < trackGroupArray2.length) {
            TrackGroup trackGroup2 = trackGroupArray2.get(i);
            int[] iArr2 = iArr[i];
            int i4 = i3;
            i3 = i2;
            TrackGroup trackGroup3 = trackGroup;
            for (int i5 = 0; i5 < trackGroup2.length; i5++) {
                if (isSupported(iArr2[i5], parameters2.exceedRendererCapabilitiesIfNecessary)) {
                    int i6;
                    Format format = trackGroup2.getFormat(i5);
                    int i7 = format.selectionFlags & (parameters2.disabledTextTrackSelectionFlags ^ -1);
                    boolean z = (i7 & 1) != 0;
                    boolean z2 = (i7 & 2) != 0;
                    boolean formatHasLanguage = formatHasLanguage(format, parameters2.preferredTextLanguage);
                    if (formatHasLanguage || (parameters2.selectUndeterminedTextLanguage && formatHasNoLanguage(format))) {
                        int i8 = z ? 8 : !z2 ? 6 : 4;
                        i6 = i8 + formatHasLanguage;
                    } else if (z) {
                        i6 = 3;
                    } else if (z2) {
                        i6 = formatHasLanguage(format, parameters2.preferredAudioLanguage) ? 2 : 1;
                    }
                    if (isSupported(iArr2[i5], false)) {
                        i6 += 1000;
                    }
                    if (i6 > i4) {
                        i3 = i5;
                        trackGroup3 = trackGroup2;
                        i4 = i6;
                    }
                }
            }
            i++;
            trackGroup = trackGroup3;
            i2 = i3;
            i3 = i4;
        }
        if (trackGroup == null) {
            return null;
        }
        return Pair.create(new FixedTrackSelection(trackGroup, i2), Integer.valueOf(i3));
    }

    /* Access modifiers changed, original: protected */
    @Nullable
    public TrackSelection selectOtherTrack(int i, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) throws ExoPlaybackException {
        TrackGroup trackGroup = null;
        int i2 = 0;
        int i3 = i2;
        int i4 = i3;
        while (i2 < trackGroupArray.length) {
            TrackGroup trackGroup2 = trackGroupArray.get(i2);
            int[] iArr2 = iArr[i2];
            int i5 = i4;
            i4 = i3;
            TrackGroup trackGroup3 = trackGroup;
            for (int i6 = 0; i6 < trackGroup2.length; i6++) {
                if (isSupported(iArr2[i6], parameters.exceedRendererCapabilitiesIfNecessary)) {
                    int i7 = 1;
                    if (((trackGroup2.getFormat(i6).selectionFlags & 1) != 0 ? 1 : false) != 0) {
                        i7 = 2;
                    }
                    if (isSupported(iArr2[i6], false)) {
                        i7 += 1000;
                    }
                    if (i7 > i5) {
                        i4 = i6;
                        trackGroup3 = trackGroup2;
                        i5 = i7;
                    }
                }
            }
            i2++;
            trackGroup = trackGroup3;
            i3 = i4;
            i4 = i5;
        }
        if (trackGroup == null) {
            return null;
        }
        return new FixedTrackSelection(trackGroup, i3);
    }

    private static void maybeConfigureRenderersForTunneling(MappedTrackInfo mappedTrackInfo, int[][][] iArr, RendererConfiguration[] rendererConfigurationArr, TrackSelection[] trackSelectionArr, int i) {
        if (i != 0) {
            int i2;
            int i3 = 0;
            int i4 = 0;
            int i5 = -1;
            int i6 = i5;
            while (i4 < mappedTrackInfo.getRendererCount()) {
                int rendererType = mappedTrackInfo.getRendererType(i4);
                TrackSelection trackSelection = trackSelectionArr[i4];
                if ((rendererType == 1 || rendererType == 2) && trackSelection != null && rendererSupportsTunneling(iArr[i4], mappedTrackInfo.getTrackGroups(i4), trackSelection)) {
                    if (rendererType == 1) {
                        if (i5 == -1) {
                            i5 = i4;
                        }
                    } else if (i6 == -1) {
                        i6 = i4;
                    }
                    i2 = 0;
                    break;
                }
                i4++;
            }
            i2 = 1;
            if (!(i5 == -1 || i6 == -1)) {
                i3 = 1;
            }
            if ((i2 & i3) != 0) {
                RendererConfiguration rendererConfiguration = new RendererConfiguration(i);
                rendererConfigurationArr[i5] = rendererConfiguration;
                rendererConfigurationArr[i6] = rendererConfiguration;
            }
        }
    }

    private static boolean rendererSupportsTunneling(int[][] iArr, TrackGroupArray trackGroupArray, TrackSelection trackSelection) {
        if (trackSelection == null) {
            return false;
        }
        int indexOf = trackGroupArray.indexOf(trackSelection.getTrackGroup());
        for (int i = 0; i < trackSelection.length(); i++) {
            if ((iArr[indexOf][trackSelection.getIndexInTrackGroup(i)] & 32) != 32) {
                return false;
            }
        }
        return true;
    }

    protected static boolean formatHasNoLanguage(Format format) {
        return TextUtils.isEmpty(format.language) || formatHasLanguage(format, C.LANGUAGE_UNDETERMINED);
    }

    protected static boolean formatHasLanguage(Format format, @Nullable String str) {
        return str != null && TextUtils.equals(str, Util.normalizeLanguageCode(format.language));
    }

    private static List<Integer> getViewportFilteredTrackIndices(TrackGroup trackGroup, int i, int i2, boolean z) {
        ArrayList arrayList = new ArrayList(trackGroup.length);
        int i3 = 0;
        for (int i4 = 0; i4 < trackGroup.length; i4++) {
            arrayList.add(Integer.valueOf(i4));
        }
        if (i == Integer.MAX_VALUE || i2 == Integer.MAX_VALUE) {
            return arrayList;
        }
        int i5 = Integer.MAX_VALUE;
        while (i3 < trackGroup.length) {
            Format format = trackGroup.getFormat(i3);
            if (format.width > 0 && format.height > 0) {
                Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(z, i, i2, format.width, format.height);
                int i6 = format.width * format.height;
                if (format.width >= ((int) (((float) maxVideoSizeInViewport.x) * FRACTION_TO_CONSIDER_FULLSCREEN)) && format.height >= ((int) (((float) maxVideoSizeInViewport.y) * FRACTION_TO_CONSIDER_FULLSCREEN)) && i6 < i5) {
                    i5 = i6;
                }
            }
            i3++;
        }
        if (i5 != Integer.MAX_VALUE) {
            for (i = arrayList.size() - 1; i >= 0; i--) {
                i2 = trackGroup.getFormat(((Integer) arrayList.get(i)).intValue()).getPixelCount();
                if (i2 == -1 || i2 > i5) {
                    arrayList.remove(i);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Missing block: B:7:0x000c, code skipped:
            if (r1 != r3) goto L_0x0012;
     */
    private static android.graphics.Point getMaxVideoSizeInViewport(boolean r3, int r4, int r5, int r6, int r7) {
        /*
        if (r3 == 0) goto L_0x000f;
    L_0x0002:
        r3 = 0;
        r0 = 1;
        if (r6 <= r7) goto L_0x0008;
    L_0x0006:
        r1 = r0;
        goto L_0x0009;
    L_0x0008:
        r1 = r3;
    L_0x0009:
        if (r4 <= r5) goto L_0x000c;
    L_0x000b:
        r3 = r0;
    L_0x000c:
        if (r1 == r3) goto L_0x000f;
    L_0x000e:
        goto L_0x0012;
    L_0x000f:
        r2 = r5;
        r5 = r4;
        r4 = r2;
    L_0x0012:
        r3 = r6 * r4;
        r0 = r7 * r5;
        if (r3 < r0) goto L_0x0022;
    L_0x0018:
        r3 = new android.graphics.Point;
        r4 = com.google.android.exoplayer2.util.Util.ceilDivide(r0, r6);
        r3.<init>(r5, r4);
        return r3;
    L_0x0022:
        r5 = new android.graphics.Point;
        r3 = com.google.android.exoplayer2.util.Util.ceilDivide(r3, r7);
        r5.<init>(r3, r4);
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.getMaxVideoSizeInViewport(boolean, int, int, int, int):android.graphics.Point");
    }
}
