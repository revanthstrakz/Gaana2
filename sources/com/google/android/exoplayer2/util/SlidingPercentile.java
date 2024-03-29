package com.google.android.exoplayer2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SlidingPercentile {
    private static final Comparator<Sample> INDEX_COMPARATOR = SlidingPercentile$$Lambda$0.$instance;
    private static final int MAX_RECYCLED_SAMPLES = 5;
    private static final int SORT_ORDER_BY_INDEX = 1;
    private static final int SORT_ORDER_BY_VALUE = 0;
    private static final int SORT_ORDER_NONE = -1;
    private static final Comparator<Sample> VALUE_COMPARATOR = SlidingPercentile$$Lambda$1.$instance;
    private int currentSortOrder = -1;
    private final int maxWeight;
    private int nextSampleIndex;
    private int recycledSampleCount;
    private final Sample[] recycledSamples = new Sample[5];
    private final ArrayList<Sample> samples = new ArrayList();
    private int totalWeight;

    private static class Sample {
        public int index;
        public float value;
        public int weight;

        private Sample() {
        }
    }

    public SlidingPercentile(int i) {
        this.maxWeight = i;
    }

    public void addSample(int i, float f) {
        int i2;
        Sample sample;
        ensureSortedByIndex();
        if (this.recycledSampleCount > 0) {
            Sample[] sampleArr = this.recycledSamples;
            i2 = this.recycledSampleCount - 1;
            this.recycledSampleCount = i2;
            sample = sampleArr[i2];
        } else {
            sample = new Sample();
        }
        i2 = this.nextSampleIndex;
        this.nextSampleIndex = i2 + 1;
        sample.index = i2;
        sample.weight = i;
        sample.value = f;
        this.samples.add(sample);
        this.totalWeight += i;
        while (this.totalWeight > this.maxWeight) {
            i = this.totalWeight - this.maxWeight;
            Sample sample2 = (Sample) this.samples.get(0);
            if (sample2.weight <= i) {
                this.totalWeight -= sample2.weight;
                this.samples.remove(0);
                if (this.recycledSampleCount < 5) {
                    Sample[] sampleArr2 = this.recycledSamples;
                    int i3 = this.recycledSampleCount;
                    this.recycledSampleCount = i3 + 1;
                    sampleArr2[i3] = sample2;
                }
            } else {
                sample2.weight -= i;
                this.totalWeight -= i;
            }
        }
    }

    public float getPercentile(float f) {
        ensureSortedByValue();
        f *= (float) this.totalWeight;
        int i = 0;
        int i2 = 0;
        while (i < this.samples.size()) {
            Sample sample = (Sample) this.samples.get(i);
            i2 += sample.weight;
            if (((float) i2) >= f) {
                return sample.value;
            }
            i++;
        }
        return this.samples.isEmpty() ? Float.NaN : ((Sample) this.samples.get(this.samples.size() - 1)).value;
    }

    private void ensureSortedByIndex() {
        if (this.currentSortOrder != 1) {
            Collections.sort(this.samples, INDEX_COMPARATOR);
            this.currentSortOrder = 1;
        }
    }

    private void ensureSortedByValue() {
        if (this.currentSortOrder != 0) {
            Collections.sort(this.samples, VALUE_COMPARATOR);
            this.currentSortOrder = 0;
        }
    }
}
