package android.support.constraint.solver.widgets;

import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor.Strength;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import java.util.ArrayList;

public class ConstraintWidget {
    protected static final int ANCHOR_BASELINE = 4;
    protected static final int ANCHOR_BOTTOM = 3;
    protected static final int ANCHOR_LEFT = 0;
    protected static final int ANCHOR_RIGHT = 1;
    protected static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    protected ArrayList<ConstraintAnchor> mAnchors;
    ConstraintAnchor mBaseline;
    int mBaselineDistance;
    ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    protected float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    private int mDrawHeight;
    private int mDrawWidth;
    private int mDrawX;
    private int mDrawY;
    int mHeight;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    boolean mHorizontalWrapVisited;
    boolean mIsHeightWrapContent;
    boolean mIsWidthWrapContent;
    ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    protected ConstraintAnchor[] mListAnchors;
    protected DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    protected ConstraintWidget[] mListNextVisibleWidget;
    int mMatchConstraintDefaultHeight;
    int mMatchConstraintDefaultWidth;
    int mMatchConstraintMaxHeight;
    int mMatchConstraintMaxWidth;
    int mMatchConstraintMinHeight;
    int mMatchConstraintMinWidth;
    float mMatchConstraintPercentHeight;
    float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    protected int mMinHeight;
    protected int mMinWidth;
    protected int mOffsetX;
    protected int mOffsetY;
    ConstraintWidget mParent;
    ResolutionDimension mResolutionHeight;
    ResolutionDimension mResolutionWidth;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    ConstraintAnchor mRight;
    boolean mRightHasCentered;
    ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    float[] mWeight;
    int mWidth;
    private int mWrapHeight;
    private int mWrapWidth;
    protected int mX;
    protected int mY;

    public enum ContentAlignment {
        BEGIN,
        MIDDLE,
        END,
        TOP,
        VERTICAL_MIDDLE,
        BOTTOM,
        LEFT,
        RIGHT
    }

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public void connectedTo(ConstraintWidget constraintWidget) {
    }

    public void resolve() {
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int i) {
        this.mMaxDimension[0] = i;
    }

    public void setMaxHeight(int i) {
        this.mMaxDimension[1] = i;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        this.mWeight[0] = 0.0f;
        this.mWeight[1] = 0.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMaxDimension[0] = Integer.MAX_VALUE;
        this.mMaxDimension[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        if (this.mResolutionWidth != null) {
            this.mResolutionWidth.reset();
        }
        if (this.mResolutionHeight != null) {
            this.mResolutionHeight.reset();
        }
    }

    public void resetResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().reset();
        }
    }

    public void updateResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().update();
        }
    }

    public void analyze(int i) {
        Optimizer.analyze(i, this);
    }

    public boolean isFullyResolved() {
        if (this.mLeft.getResolutionNode().state == 1 && this.mRight.getResolutionNode().state == 1 && this.mTop.getResolutionNode().state == 1 && this.mBottom.getResolutionNode().state == 1) {
            return true;
        }
        return false;
    }

    public ResolutionDimension getResolutionWidth() {
        if (this.mResolutionWidth == null) {
            this.mResolutionWidth = new ResolutionDimension();
        }
        return this.mResolutionWidth;
    }

    public ResolutionDimension getResolutionHeight() {
        if (this.mResolutionHeight == null) {
            this.mResolutionHeight = new ResolutionDimension();
        }
        return this.mResolutionHeight;
    }

    public ConstraintWidget() {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.mLeft = new ConstraintAnchor(this, Type.LEFT);
        this.mTop = new ConstraintAnchor(this, Type.TOP);
        this.mRight = new ConstraintAnchor(this, Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList();
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{0.0f, 0.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mListNextVisibleWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        addAnchors();
    }

    public ConstraintWidget(int i, int i2, int i3, int i4) {
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.mLeft = new ConstraintAnchor(this, Type.LEFT);
        this.mTop = new ConstraintAnchor(this, Type.TOP);
        this.mRight = new ConstraintAnchor(this, Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
        this.mCenter = new ConstraintAnchor(this, Type.CENTER);
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
        this.mAnchors = new ArrayList();
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mType = null;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{0.0f, 0.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mListNextVisibleWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.mX = i;
        this.mY = i2;
        this.mWidth = i3;
        this.mHeight = i4;
        addAnchors();
        forceUpdateDrawPosition();
    }

    public ConstraintWidget(int i, int i2) {
        this(0, 0, i, i2);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public boolean isRootContainer() {
        return (this instanceof ConstraintWidgetContainer) && (this.mParent == null || !(this.mParent instanceof ConstraintWidgetContainer));
    }

    public boolean isInsideConstraintLayout() {
        ConstraintWidget parent = getParent();
        if (parent == null) {
            return false;
        }
        while (parent != null) {
            if (parent instanceof ConstraintWidgetContainer) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public boolean hasAncestor(ConstraintWidget constraintWidget) {
        ConstraintWidget parent = getParent();
        if (parent == constraintWidget) {
            return true;
        }
        if (parent == constraintWidget.getParent()) {
            return false;
        }
        while (parent != null) {
            if (parent == constraintWidget || parent == constraintWidget.getParent()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public WidgetContainer getRootWidgetContainer() {
        ConstraintWidget constraintWidget = this;
        while (constraintWidget.getParent() != null) {
            constraintWidget = constraintWidget.getParent();
        }
        return constraintWidget instanceof WidgetContainer ? (WidgetContainer) constraintWidget : null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i) {
        immediateConnect(Type.CENTER, constraintWidget, Type.CENTER, i, 0);
        this.mCircleConstraintAngle = f;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(".left");
        createObjectVariable.setName(stringBuilder.toString());
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(".top");
        createObjectVariable2.setName(stringBuilder2.toString());
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(".right");
        createObjectVariable3.setName(stringBuilder2.toString());
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str);
        stringBuilder2.append(".bottom");
        createObjectVariable4.setName(stringBuilder2.toString());
        if (this.mBaselineDistance > 0) {
            SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(this.mBaseline);
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(".baseline");
            createObjectVariable5.setName(stringBuilder2.toString());
        }
    }

    public String toString() {
        StringBuilder stringBuilder;
        String stringBuilder2;
        StringBuilder stringBuilder3 = new StringBuilder();
        if (this.mType != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("type: ");
            stringBuilder.append(this.mType);
            stringBuilder.append(" ");
            stringBuilder2 = stringBuilder.toString();
        } else {
            stringBuilder2 = "";
        }
        stringBuilder3.append(stringBuilder2);
        if (this.mDebugName != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("id: ");
            stringBuilder.append(this.mDebugName);
            stringBuilder.append(" ");
            stringBuilder2 = stringBuilder.toString();
        } else {
            stringBuilder2 = "";
        }
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append("(");
        stringBuilder3.append(this.mX);
        stringBuilder3.append(", ");
        stringBuilder3.append(this.mY);
        stringBuilder3.append(") - (");
        stringBuilder3.append(this.mWidth);
        stringBuilder3.append(" x ");
        stringBuilder3.append(this.mHeight);
        stringBuilder3.append(") wrap: (");
        stringBuilder3.append(this.mWrapWidth);
        stringBuilder3.append(" x ");
        stringBuilder3.append(this.mWrapHeight);
        stringBuilder3.append(")");
        return stringBuilder3.toString();
    }

    /* Access modifiers changed, original: 0000 */
    public int getInternalDrawX() {
        return this.mDrawX;
    }

    /* Access modifiers changed, original: 0000 */
    public int getInternalDrawY() {
        return this.mDrawY;
    }

    public int getInternalDrawRight() {
        return this.mDrawX + this.mDrawWidth;
    }

    public int getInternalDrawBottom() {
        return this.mDrawY + this.mDrawHeight;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int i = this.mWidth;
        if (this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i;
        }
        if (this.mMatchConstraintDefaultWidth == 1) {
            i = Math.max(this.mMatchConstraintMinWidth, i);
        } else if (this.mMatchConstraintMinWidth > 0) {
            i = this.mMatchConstraintMinWidth;
            this.mWidth = i;
        } else {
            i = 0;
        }
        return (this.mMatchConstraintMaxWidth <= 0 || this.mMatchConstraintMaxWidth >= i) ? i : this.mMatchConstraintMaxWidth;
    }

    public int getOptimizerWrapHeight() {
        int i = this.mHeight;
        if (this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i;
        }
        if (this.mMatchConstraintDefaultHeight == 1) {
            i = Math.max(this.mMatchConstraintMinHeight, i);
        } else if (this.mMatchConstraintMinHeight > 0) {
            i = this.mMatchConstraintMinHeight;
            this.mHeight = i;
        } else {
            i = 0;
        }
        return (this.mMatchConstraintMaxHeight <= 0 || this.mMatchConstraintMaxHeight >= i) ? i : this.mMatchConstraintMaxHeight;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    public int getDrawWidth() {
        return this.mDrawWidth;
    }

    public int getDrawHeight() {
        return this.mDrawHeight;
    }

    public int getDrawBottom() {
        return getDrawY() + this.mDrawHeight;
    }

    public int getDrawRight() {
        return getDrawX() + this.mDrawWidth;
    }

    /* Access modifiers changed, original: protected */
    public int getRootX() {
        return this.mX + this.mOffsetX;
    }

    /* Access modifiers changed, original: protected */
    public int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int i) {
        this.mX = i;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public void setGoneMargin(Type type, int i) {
        switch (type) {
            case LEFT:
                this.mLeft.mGoneMargin = i;
                return;
            case TOP:
                this.mTop.mGoneMargin = i;
                return;
            case RIGHT:
                this.mRight.mGoneMargin = i;
                return;
            case BOTTOM:
                this.mBottom.mGoneMargin = i;
                return;
            default:
                return;
        }
    }

    public void updateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mX + this.mWidth;
        int i4 = this.mY + this.mHeight;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = i4 - i2;
    }

    public void forceUpdateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mX + this.mWidth;
        int i4 = this.mY + this.mHeight;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = i4 - i2;
    }

    public void setDrawOrigin(int i, int i2) {
        this.mDrawX = i - this.mOffsetX;
        this.mDrawY = i2 - this.mOffsetY;
        this.mX = this.mDrawX;
        this.mY = this.mDrawY;
    }

    public void setDrawX(int i) {
        this.mDrawX = i - this.mOffsetX;
        this.mX = this.mDrawX;
    }

    public void setDrawY(int i) {
        this.mDrawY = i - this.mOffsetY;
        this.mY = this.mDrawY;
    }

    public void setDrawWidth(int i) {
        this.mDrawWidth = i;
    }

    public void setDrawHeight(int i) {
        this.mDrawHeight = i;
    }

    public void setWidth(int i) {
        this.mWidth = i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        this.mMatchConstraintMaxWidth = i3;
        this.mMatchConstraintPercentWidth = f;
        if (f < 1.0f && this.mMatchConstraintDefaultWidth == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        this.mMatchConstraintMaxHeight = i3;
        this.mMatchConstraintPercentHeight = f;
        if (f < 1.0f && this.mMatchConstraintDefaultHeight == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0089  */
    public void setDimensionRatio(java.lang.String r9) {
        /*
        r8 = this;
        r0 = 0;
        if (r9 == 0) goto L_0x008e;
    L_0x0003:
        r1 = r9.length();
        if (r1 != 0) goto L_0x000b;
    L_0x0009:
        goto L_0x008e;
    L_0x000b:
        r1 = -1;
        r2 = r9.length();
        r3 = 44;
        r3 = r9.indexOf(r3);
        r4 = 0;
        r5 = 1;
        if (r3 <= 0) goto L_0x0037;
    L_0x001a:
        r6 = r2 + -1;
        if (r3 >= r6) goto L_0x0037;
    L_0x001e:
        r6 = r9.substring(r4, r3);
        r7 = "W";
        r7 = r6.equalsIgnoreCase(r7);
        if (r7 == 0) goto L_0x002c;
    L_0x002a:
        r1 = r4;
        goto L_0x0035;
    L_0x002c:
        r4 = "H";
        r4 = r6.equalsIgnoreCase(r4);
        if (r4 == 0) goto L_0x0035;
    L_0x0034:
        r1 = r5;
    L_0x0035:
        r4 = r3 + 1;
    L_0x0037:
        r3 = 58;
        r3 = r9.indexOf(r3);
        if (r3 < 0) goto L_0x0075;
    L_0x003f:
        r2 = r2 - r5;
        if (r3 >= r2) goto L_0x0075;
    L_0x0042:
        r2 = r9.substring(r4, r3);
        r3 = r3 + r5;
        r9 = r9.substring(r3);
        r3 = r2.length();
        if (r3 <= 0) goto L_0x0084;
    L_0x0051:
        r3 = r9.length();
        if (r3 <= 0) goto L_0x0084;
    L_0x0057:
        r2 = java.lang.Float.parseFloat(r2);	 Catch:{ NumberFormatException -> 0x0084 }
        r9 = java.lang.Float.parseFloat(r9);	 Catch:{ NumberFormatException -> 0x0084 }
        r3 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1));
        if (r3 <= 0) goto L_0x0084;
    L_0x0063:
        r3 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1));
        if (r3 <= 0) goto L_0x0084;
    L_0x0067:
        if (r1 != r5) goto L_0x006f;
    L_0x0069:
        r9 = r9 / r2;
        r9 = java.lang.Math.abs(r9);	 Catch:{ NumberFormatException -> 0x0084 }
        goto L_0x0085;
    L_0x006f:
        r2 = r2 / r9;
        r9 = java.lang.Math.abs(r2);	 Catch:{ NumberFormatException -> 0x0084 }
        goto L_0x0085;
    L_0x0075:
        r9 = r9.substring(r4);
        r2 = r9.length();
        if (r2 <= 0) goto L_0x0084;
    L_0x007f:
        r9 = java.lang.Float.parseFloat(r9);	 Catch:{ NumberFormatException -> 0x0084 }
        goto L_0x0085;
    L_0x0084:
        r9 = r0;
    L_0x0085:
        r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1));
        if (r0 <= 0) goto L_0x008d;
    L_0x0089:
        r8.mDimensionRatio = r9;
        r8.mDimensionRatioSide = r1;
    L_0x008d:
        return;
    L_0x008e:
        r8.mDimensionRatio = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.setDimensionRatio(java.lang.String):void");
    }

    public void setDimensionRatio(float f, int i) {
        this.mDimensionRatio = f;
        this.mDimensionRatioSide = i;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setWrapWidth(int i) {
        this.mWrapWidth = i;
    }

    public void setWrapHeight(int i) {
        this.mWrapHeight = i;
    }

    public void setDimension(int i, int i2) {
        this.mWidth = i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
        this.mHeight = i2;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        i3 -= i;
        i4 -= i2;
        this.mX = i;
        this.mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i3 < this.mWidth) {
            i3 = this.mWidth;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i4 < this.mHeight) {
            i4 = this.mHeight;
        }
        this.mWidth = i3;
        this.mHeight = i4;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHorizontalDimension(int i, int i2) {
        this.mX = i;
        this.mWidth = i2 - i;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.mY = i;
        this.mHeight = i2 - i;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i) {
        if (i >= 0) {
            this.mContainerItemSkip = i;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(Type type, ConstraintWidget constraintWidget, Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, Strength.STRONG, 0, true);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, int i2) {
        connect(constraintAnchor, constraintAnchor2, i, Strength.STRONG, i2);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        connect(constraintAnchor, constraintAnchor2, i, Strength.STRONG, 0);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, Strength strength, int i2) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i, strength, i2);
        }
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2, int i) {
        connect(type, constraintWidget, type2, i, Strength.STRONG);
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2) {
        connect(type, constraintWidget, type2, 0, Strength.STRONG);
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2, int i, Strength strength) {
        connect(type, constraintWidget, type2, i, strength, 0);
    }

    public void connect(Type type, ConstraintWidget constraintWidget, Type type2, int i, Strength strength, int i2) {
        Type type3 = type;
        ConstraintWidget constraintWidget2 = constraintWidget;
        Type type4 = type2;
        int i3 = i2;
        int i4 = 0;
        ConstraintAnchor anchor;
        ConstraintAnchor anchor2;
        ConstraintAnchor anchor3;
        if (type3 == Type.CENTER) {
            ConstraintWidget constraintWidget3;
            Strength strength2;
            int i5;
            Type type5;
            if (type4 == Type.CENTER) {
                int i6;
                anchor = getAnchor(Type.LEFT);
                anchor2 = getAnchor(Type.RIGHT);
                ConstraintAnchor anchor4 = getAnchor(Type.TOP);
                ConstraintAnchor anchor5 = getAnchor(Type.BOTTOM);
                int i7 = 1;
                if ((anchor == null || !anchor.isConnected()) && (anchor2 == null || !anchor2.isConnected())) {
                    constraintWidget3 = constraintWidget2;
                    strength2 = strength;
                    i5 = i3;
                    connect(Type.LEFT, constraintWidget3, Type.LEFT, 0, strength2, i5);
                    connect(Type.RIGHT, constraintWidget3, Type.RIGHT, 0, strength2, i5);
                    i6 = 1;
                } else {
                    i6 = 0;
                }
                if ((anchor4 == null || !anchor4.isConnected()) && (anchor5 == null || !anchor5.isConnected())) {
                    constraintWidget3 = constraintWidget2;
                    strength2 = strength;
                    i5 = i3;
                    connect(Type.TOP, constraintWidget3, Type.TOP, 0, strength2, i5);
                    connect(Type.BOTTOM, constraintWidget3, Type.BOTTOM, 0, strength2, i5);
                } else {
                    i7 = 0;
                }
                if (i6 != 0 && i7 != 0) {
                    getAnchor(Type.CENTER).connect(constraintWidget2.getAnchor(Type.CENTER), 0, i3);
                } else if (i6 != 0) {
                    getAnchor(Type.CENTER_X).connect(constraintWidget2.getAnchor(Type.CENTER_X), 0, i3);
                } else if (i7 != 0) {
                    getAnchor(Type.CENTER_Y).connect(constraintWidget2.getAnchor(Type.CENTER_Y), 0, i3);
                }
            } else if (type4 == Type.LEFT || type4 == Type.RIGHT) {
                constraintWidget3 = constraintWidget2;
                type5 = type4;
                strength2 = strength;
                i5 = i3;
                connect(Type.LEFT, constraintWidget3, type5, 0, strength2, i5);
                connect(Type.RIGHT, constraintWidget3, type5, 0, strength2, i5);
                getAnchor(Type.CENTER).connect(constraintWidget.getAnchor(type2), 0, i3);
            } else if (type4 == Type.TOP || type4 == Type.BOTTOM) {
                constraintWidget3 = constraintWidget2;
                type5 = type4;
                strength2 = strength;
                i5 = i3;
                connect(Type.TOP, constraintWidget3, type5, 0, strength2, i5);
                connect(Type.BOTTOM, constraintWidget3, type5, 0, strength2, i5);
                getAnchor(Type.CENTER).connect(constraintWidget.getAnchor(type2), 0, i3);
            }
        } else if (type3 == Type.CENTER_X && (type4 == Type.LEFT || type4 == Type.RIGHT)) {
            anchor = getAnchor(Type.LEFT);
            anchor2 = constraintWidget.getAnchor(type2);
            anchor3 = getAnchor(Type.RIGHT);
            anchor.connect(anchor2, 0, i3);
            anchor3.connect(anchor2, 0, i3);
            getAnchor(Type.CENTER_X).connect(anchor2, 0, i3);
        } else if (type3 == Type.CENTER_Y && (type4 == Type.TOP || type4 == Type.BOTTOM)) {
            anchor = constraintWidget.getAnchor(type2);
            getAnchor(Type.TOP).connect(anchor, 0, i3);
            getAnchor(Type.BOTTOM).connect(anchor, 0, i3);
            getAnchor(Type.CENTER_Y).connect(anchor, 0, i3);
        } else if (type3 == Type.CENTER_X && type4 == Type.CENTER_X) {
            getAnchor(Type.LEFT).connect(constraintWidget2.getAnchor(Type.LEFT), 0, i3);
            getAnchor(Type.RIGHT).connect(constraintWidget2.getAnchor(Type.RIGHT), 0, i3);
            getAnchor(Type.CENTER_X).connect(constraintWidget.getAnchor(type2), 0, i3);
        } else if (type3 == Type.CENTER_Y && type4 == Type.CENTER_Y) {
            getAnchor(Type.TOP).connect(constraintWidget2.getAnchor(Type.TOP), 0, i3);
            getAnchor(Type.BOTTOM).connect(constraintWidget2.getAnchor(Type.BOTTOM), 0, i3);
            getAnchor(Type.CENTER_Y).connect(constraintWidget.getAnchor(type2), 0, i3);
        } else {
            anchor2 = getAnchor(type);
            anchor3 = constraintWidget.getAnchor(type2);
            if (anchor2.isValidConnection(anchor3)) {
                ConstraintAnchor anchor6;
                if (type3 == Type.BASELINE) {
                    anchor = getAnchor(Type.TOP);
                    anchor6 = getAnchor(Type.BOTTOM);
                    if (anchor != null) {
                        anchor.reset();
                    }
                    if (anchor6 != null) {
                        anchor6.reset();
                    }
                } else {
                    if (type3 == Type.TOP || type3 == Type.BOTTOM) {
                        anchor6 = getAnchor(Type.BASELINE);
                        if (anchor6 != null) {
                            anchor6.reset();
                        }
                        anchor6 = getAnchor(Type.CENTER);
                        if (anchor6.getTarget() != anchor3) {
                            anchor6.reset();
                        }
                        anchor = getAnchor(type).getOpposite();
                        anchor6 = getAnchor(Type.CENTER_Y);
                        if (anchor6.isConnected()) {
                            anchor.reset();
                            anchor6.reset();
                        }
                    } else if (type3 == Type.LEFT || type3 == Type.RIGHT) {
                        anchor6 = getAnchor(Type.CENTER);
                        if (anchor6.getTarget() != anchor3) {
                            anchor6.reset();
                        }
                        anchor = getAnchor(type).getOpposite();
                        anchor6 = getAnchor(Type.CENTER_X);
                        if (anchor6.isConnected()) {
                            anchor.reset();
                            anchor6.reset();
                        }
                    }
                    i4 = i;
                }
                anchor2.connect(anchor3, i4, strength, i3);
                anchor3.getOwner().connectedTo(anchor2.getOwner());
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
        if (!(this instanceof ConstraintWidgetContainer)) {
            if (getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (getWidth() == getWrapWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                } else if (getWidth() > getMinWidth()) {
                    setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
            }
            if (getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                if (getHeight() == getWrapHeight()) {
                    setVerticalDimensionBehaviour(DimensionBehaviour.WRAP_CONTENT);
                } else if (getHeight() > getMinHeight()) {
                    setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
            }
        }
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() == null || !(getParent() instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            ConstraintAnchor anchor = getAnchor(Type.LEFT);
            ConstraintAnchor anchor2 = getAnchor(Type.RIGHT);
            ConstraintAnchor anchor3 = getAnchor(Type.TOP);
            ConstraintAnchor anchor4 = getAnchor(Type.BOTTOM);
            ConstraintAnchor anchor5 = getAnchor(Type.CENTER);
            ConstraintAnchor anchor6 = getAnchor(Type.CENTER_X);
            ConstraintAnchor anchor7 = getAnchor(Type.CENTER_Y);
            if (constraintAnchor == anchor5) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor.reset();
                    anchor2.reset();
                }
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor6) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                    anchor.reset();
                    anchor2.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor7) {
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor || constraintAnchor == anchor2) {
                if (anchor.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor5.reset();
                }
            } else if ((constraintAnchor == anchor3 || constraintAnchor == anchor4) && anchor3.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                anchor5.reset();
            }
            constraintAnchor.reset();
        }
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i = 0; i < size; i++) {
                ((ConstraintAnchor) this.mAnchors.get(i)).reset();
            }
        }
    }

    public void resetAnchors(int i) {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintAnchor constraintAnchor = (ConstraintAnchor) this.mAnchors.get(i2);
                if (i == constraintAnchor.getConnectionCreator()) {
                    if (constraintAnchor.isVerticalAnchor()) {
                        setVerticalBiasPercent(DEFAULT_BIAS);
                    } else {
                        setHorizontalBiasPercent(DEFAULT_BIAS);
                    }
                    constraintAnchor.reset();
                }
            }
        }
    }

    public void disconnectWidget(ConstraintWidget constraintWidget) {
        ArrayList anchors = getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) anchors.get(i);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget) {
                constraintAnchor.reset();
            }
        }
    }

    public void disconnectUnlockedWidget(ConstraintWidget constraintWidget) {
        ArrayList anchors = getAnchors();
        int size = anchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) anchors.get(i);
            if (constraintAnchor.isConnected() && constraintAnchor.getTarget().getOwner() == constraintWidget && constraintAnchor.getConnectionCreator() == 2) {
                constraintAnchor.reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(Type type) {
        switch (type) {
            case LEFT:
                return this.mLeft;
            case TOP:
                return this.mTop;
            case RIGHT:
                return this.mRight;
            case BOTTOM:
                return this.mBottom;
            case BASELINE:
                return this.mBaseline;
            case CENTER:
                return this.mCenter;
            case CENTER_X:
                return this.mCenterX;
            case CENTER_Y:
                return this.mCenterY;
            case NONE:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
        if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public boolean isInHorizontalChain() {
        return (this.mLeft.mTarget != null && this.mLeft.mTarget.mTarget == this.mLeft) || (this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight);
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintWidget constraintWidget3;
            ConstraintAnchor anchor = constraintWidget.getAnchor(Type.LEFT);
            if (anchor == null) {
                anchor = null;
            } else {
                anchor = anchor.getTarget();
            }
            if (anchor == null) {
                constraintWidget3 = null;
            } else {
                constraintWidget3 = anchor.getOwner();
            }
            if (constraintWidget3 == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor constraintAnchor;
            if (constraintWidget3 == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = constraintWidget3.getAnchor(Type.RIGHT).getTarget();
            }
            if (constraintAnchor == null || constraintAnchor.getOwner() == constraintWidget) {
                constraintWidget = constraintWidget3;
            } else {
                constraintWidget2 = constraintWidget;
            }
        }
        return constraintWidget2;
    }

    public boolean isInVerticalChain() {
        return (this.mTop.mTarget != null && this.mTop.mTarget.mTarget == this.mTop) || (this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom);
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintWidget constraintWidget3;
            ConstraintAnchor anchor = constraintWidget.getAnchor(Type.TOP);
            if (anchor == null) {
                anchor = null;
            } else {
                anchor = anchor.getTarget();
            }
            if (anchor == null) {
                constraintWidget3 = null;
            } else {
                constraintWidget3 = anchor.getOwner();
            }
            if (constraintWidget3 == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor constraintAnchor;
            if (constraintWidget3 == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = constraintWidget3.getAnchor(Type.BOTTOM).getTarget();
            }
            if (constraintAnchor == null || constraintAnchor.getOwner() == constraintWidget) {
                constraintWidget = constraintWidget3;
            } else {
                constraintWidget2 = constraintWidget;
            }
        }
        return constraintWidget2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:143:0x02ac  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02a2  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x02bc  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x02b2  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0326  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02fd  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0330  */
    public void addToSolver(android.support.constraint.solver.LinearSystem r42) {
        /*
        r41 = this;
        r15 = r41;
        r14 = r42;
        r0 = r15.mLeft;
        r21 = r14.createObjectVariable(r0);
        r0 = r15.mRight;
        r13 = r14.createObjectVariable(r0);
        r0 = r15.mTop;
        r12 = r14.createObjectVariable(r0);
        r0 = r15.mBottom;
        r11 = r14.createObjectVariable(r0);
        r0 = r15.mBaseline;
        r10 = r14.createObjectVariable(r0);
        r0 = r15.mParent;
        r1 = 8;
        r9 = 1;
        r8 = 0;
        if (r0 == 0) goto L_0x00e5;
    L_0x002a:
        r0 = r15.mParent;
        if (r0 == 0) goto L_0x003a;
    L_0x002e:
        r0 = r15.mParent;
        r0 = r0.mListDimensionBehaviors;
        r0 = r0[r8];
        r2 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r0 != r2) goto L_0x003a;
    L_0x0038:
        r0 = r9;
        goto L_0x003b;
    L_0x003a:
        r0 = r8;
    L_0x003b:
        r2 = r15.mParent;
        if (r2 == 0) goto L_0x004b;
    L_0x003f:
        r2 = r15.mParent;
        r2 = r2.mListDimensionBehaviors;
        r2 = r2[r9];
        r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r2 != r3) goto L_0x004b;
    L_0x0049:
        r2 = r9;
        goto L_0x004c;
    L_0x004b:
        r2 = r8;
    L_0x004c:
        r3 = r15.mLeft;
        r3 = r3.mTarget;
        if (r3 == 0) goto L_0x005c;
    L_0x0052:
        r3 = r15.mLeft;
        r3 = r3.mTarget;
        r3 = r3.mTarget;
        r4 = r15.mLeft;
        if (r3 == r4) goto L_0x006c;
    L_0x005c:
        r3 = r15.mRight;
        r3 = r3.mTarget;
        if (r3 == 0) goto L_0x0075;
    L_0x0062:
        r3 = r15.mRight;
        r3 = r3.mTarget;
        r3 = r3.mTarget;
        r4 = r15.mRight;
        if (r3 != r4) goto L_0x0075;
    L_0x006c:
        r3 = r15.mParent;
        r3 = (android.support.constraint.solver.widgets.ConstraintWidgetContainer) r3;
        r3.addChain(r15, r8);
        r3 = r9;
        goto L_0x0076;
    L_0x0075:
        r3 = r8;
    L_0x0076:
        r4 = r15.mTop;
        r4 = r4.mTarget;
        if (r4 == 0) goto L_0x0086;
    L_0x007c:
        r4 = r15.mTop;
        r4 = r4.mTarget;
        r4 = r4.mTarget;
        r5 = r15.mTop;
        if (r4 == r5) goto L_0x0096;
    L_0x0086:
        r4 = r15.mBottom;
        r4 = r4.mTarget;
        if (r4 == 0) goto L_0x009f;
    L_0x008c:
        r4 = r15.mBottom;
        r4 = r4.mTarget;
        r4 = r4.mTarget;
        r5 = r15.mBottom;
        if (r4 != r5) goto L_0x009f;
    L_0x0096:
        r4 = r15.mParent;
        r4 = (android.support.constraint.solver.widgets.ConstraintWidgetContainer) r4;
        r4.addChain(r15, r9);
        r4 = r9;
        goto L_0x00a0;
    L_0x009f:
        r4 = r8;
    L_0x00a0:
        if (r0 == 0) goto L_0x00bd;
    L_0x00a2:
        r5 = r15.mVisibility;
        if (r5 == r1) goto L_0x00bd;
    L_0x00a6:
        r5 = r15.mLeft;
        r5 = r5.mTarget;
        if (r5 != 0) goto L_0x00bd;
    L_0x00ac:
        r5 = r15.mRight;
        r5 = r5.mTarget;
        if (r5 != 0) goto L_0x00bd;
    L_0x00b2:
        r5 = r15.mParent;
        r5 = r5.mRight;
        r5 = r14.createObjectVariable(r5);
        r14.addGreaterThan(r5, r13, r8, r9);
    L_0x00bd:
        if (r2 == 0) goto L_0x00de;
    L_0x00bf:
        r5 = r15.mVisibility;
        if (r5 == r1) goto L_0x00de;
    L_0x00c3:
        r5 = r15.mTop;
        r5 = r5.mTarget;
        if (r5 != 0) goto L_0x00de;
    L_0x00c9:
        r5 = r15.mBottom;
        r5 = r5.mTarget;
        if (r5 != 0) goto L_0x00de;
    L_0x00cf:
        r5 = r15.mBaseline;
        if (r5 != 0) goto L_0x00de;
    L_0x00d3:
        r5 = r15.mParent;
        r5 = r5.mBottom;
        r5 = r14.createObjectVariable(r5);
        r14.addGreaterThan(r5, r11, r8, r9);
    L_0x00de:
        r7 = r2;
        r16 = r3;
        r22 = r4;
        r2 = r0;
        goto L_0x00eb;
    L_0x00e5:
        r2 = r8;
        r7 = r2;
        r16 = r7;
        r22 = r16;
    L_0x00eb:
        r0 = r15.mWidth;
        r3 = r15.mMinWidth;
        if (r0 >= r3) goto L_0x00f3;
    L_0x00f1:
        r0 = r15.mMinWidth;
    L_0x00f3:
        r3 = r15.mHeight;
        r4 = r15.mMinHeight;
        if (r3 >= r4) goto L_0x00fb;
    L_0x00f9:
        r3 = r15.mMinHeight;
    L_0x00fb:
        r4 = r15.mListDimensionBehaviors;
        r4 = r4[r8];
        r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r4 == r5) goto L_0x0105;
    L_0x0103:
        r4 = r9;
        goto L_0x0106;
    L_0x0105:
        r4 = r8;
    L_0x0106:
        r5 = r15.mListDimensionBehaviors;
        r5 = r5[r9];
        r6 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r5 == r6) goto L_0x0110;
    L_0x010e:
        r5 = r9;
        goto L_0x0111;
    L_0x0110:
        r5 = r8;
    L_0x0111:
        r6 = r15.mDimensionRatioSide;
        r15.mResolvedDimensionRatioSide = r6;
        r6 = r15.mDimensionRatio;
        r15.mResolvedDimensionRatio = r6;
        r6 = r15.mDimensionRatio;
        r17 = 0;
        r6 = (r6 > r17 ? 1 : (r6 == r17 ? 0 : -1));
        r9 = -1;
        if (r6 <= 0) goto L_0x0176;
    L_0x0122:
        r6 = r15.mVisibility;
        if (r6 == r1) goto L_0x0176;
    L_0x0126:
        r1 = r15.mListDimensionBehaviors;
        r1 = r1[r8];
        r6 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r1 != r6) goto L_0x013b;
    L_0x012e:
        r1 = r15.mListDimensionBehaviors;
        r6 = 1;
        r1 = r1[r6];
        r6 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r1 != r6) goto L_0x013b;
    L_0x0137:
        r15.setupDimensionRatio(r2, r7, r4, r5);
        goto L_0x016f;
    L_0x013b:
        r1 = r15.mListDimensionBehaviors;
        r1 = r1[r8];
        r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r1 != r4) goto L_0x014d;
    L_0x0143:
        r15.mResolvedDimensionRatioSide = r8;
        r0 = r15.mResolvedDimensionRatio;
        r1 = r15.mHeight;
        r1 = (float) r1;
        r0 = r0 * r1;
        r0 = (int) r0;
        goto L_0x016f;
    L_0x014d:
        r1 = r15.mListDimensionBehaviors;
        r4 = 1;
        r1 = r1[r4];
        r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        if (r1 != r5) goto L_0x016f;
    L_0x0156:
        r15.mResolvedDimensionRatioSide = r4;
        r1 = r15.mDimensionRatioSide;
        if (r1 != r9) goto L_0x0163;
    L_0x015c:
        r1 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3 = r15.mResolvedDimensionRatio;
        r1 = r1 / r3;
        r15.mResolvedDimensionRatio = r1;
    L_0x0163:
        r1 = r15.mResolvedDimensionRatio;
        r3 = r15.mWidth;
        r3 = (float) r3;
        r1 = r1 * r3;
        r1 = (int) r1;
        r17 = r0;
        r25 = r1;
        goto L_0x0173;
    L_0x016f:
        r17 = r0;
        r25 = r3;
    L_0x0173:
        r24 = 1;
        goto L_0x017c;
    L_0x0176:
        r17 = r0;
        r25 = r3;
        r24 = r8;
    L_0x017c:
        if (r24 == 0) goto L_0x0189;
    L_0x017e:
        r0 = r15.mResolvedDimensionRatioSide;
        if (r0 == 0) goto L_0x0186;
    L_0x0182:
        r0 = r15.mResolvedDimensionRatioSide;
        if (r0 != r9) goto L_0x0189;
    L_0x0186:
        r18 = 1;
        goto L_0x018b;
    L_0x0189:
        r18 = r8;
    L_0x018b:
        r0 = r15.mListDimensionBehaviors;
        r0 = r0[r8];
        r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r0 != r1) goto L_0x0199;
    L_0x0193:
        r0 = r15 instanceof android.support.constraint.solver.widgets.ConstraintWidgetContainer;
        if (r0 == 0) goto L_0x0199;
    L_0x0197:
        r6 = 1;
        goto L_0x019a;
    L_0x0199:
        r6 = r8;
    L_0x019a:
        r0 = r15.mCenter;
        r0 = r0.isConnected();
        r19 = 1;
        r23 = r0 ^ 1;
        r0 = r15.mHorizontalResolution;
        r5 = 2;
        r26 = 0;
        if (r0 == r5) goto L_0x0229;
    L_0x01ab:
        r0 = r15.mParent;
        if (r0 == 0) goto L_0x01b9;
    L_0x01af:
        r0 = r15.mParent;
        r0 = r0.mRight;
        r0 = r14.createObjectVariable(r0);
        r4 = r0;
        goto L_0x01bb;
    L_0x01b9:
        r4 = r26;
    L_0x01bb:
        r0 = r15.mParent;
        if (r0 == 0) goto L_0x01c9;
    L_0x01bf:
        r0 = r15.mParent;
        r0 = r0.mLeft;
        r0 = r14.createObjectVariable(r0);
        r3 = r0;
        goto L_0x01cb;
    L_0x01c9:
        r3 = r26;
    L_0x01cb:
        r0 = r15.mListDimensionBehaviors;
        r20 = r0[r8];
        r1 = r15.mLeft;
        r0 = r15.mRight;
        r9 = r15.mX;
        r28 = r11;
        r11 = r15.mMinWidth;
        r5 = r15.mMaxDimension;
        r30 = r5[r8];
        r5 = r15.mHorizontalBiasPercent;
        r31 = r13;
        r13 = r15.mMatchConstraintDefaultWidth;
        r32 = r13;
        r13 = r15.mMatchConstraintMinWidth;
        r33 = r13;
        r13 = r15.mMatchConstraintMaxWidth;
        r34 = r13;
        r13 = r15.mMatchConstraintPercentWidth;
        r35 = r0;
        r0 = r15;
        r36 = r1;
        r1 = r14;
        r29 = r5;
        r5 = r20;
        r37 = r7;
        r7 = r36;
        r8 = r35;
        r38 = r10;
        r10 = r17;
        r27 = r28;
        r39 = r12;
        r12 = r30;
        r30 = r13;
        r28 = r31;
        r17 = r32;
        r19 = r33;
        r20 = r34;
        r13 = r29;
        r14 = r18;
        r15 = r16;
        r16 = r17;
        r17 = r19;
        r18 = r20;
        r19 = r30;
        r20 = r23;
        r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);
        r15 = r41;
        goto L_0x0233;
    L_0x0229:
        r37 = r7;
        r38 = r10;
        r27 = r11;
        r39 = r12;
        r28 = r13;
    L_0x0233:
        r0 = r15.mVerticalResolution;
        r1 = 2;
        if (r0 != r1) goto L_0x0239;
    L_0x0238:
        return;
    L_0x0239:
        r0 = r15.mListDimensionBehaviors;
        r14 = 1;
        r0 = r0[r14];
        r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (r0 != r1) goto L_0x0248;
    L_0x0242:
        r0 = r15 instanceof android.support.constraint.solver.widgets.ConstraintWidgetContainer;
        if (r0 == 0) goto L_0x0248;
    L_0x0246:
        r6 = r14;
        goto L_0x0249;
    L_0x0248:
        r6 = 0;
    L_0x0249:
        if (r24 == 0) goto L_0x0257;
    L_0x024b:
        r0 = r15.mResolvedDimensionRatioSide;
        if (r0 == r14) goto L_0x0254;
    L_0x024f:
        r0 = r15.mResolvedDimensionRatioSide;
        r1 = -1;
        if (r0 != r1) goto L_0x0257;
    L_0x0254:
        r16 = r14;
        goto L_0x0259;
    L_0x0257:
        r16 = 0;
    L_0x0259:
        r0 = r15.mBaselineDistance;
        if (r0 <= 0) goto L_0x0298;
    L_0x025d:
        r0 = r15.mBaseline;
        r0 = r0.getResolutionNode();
        r0 = r0.state;
        if (r0 != r14) goto L_0x0275;
    L_0x0267:
        r0 = r15.mBaseline;
        r0 = r0.getResolutionNode();
        r13 = r42;
        r0.addResolvedValue(r13);
        r12 = r39;
        goto L_0x029c;
    L_0x0275:
        r13 = r42;
        r0 = r41.getBaselineDistance();
        r1 = 6;
        r2 = r38;
        r12 = r39;
        r13.addEquality(r2, r12, r0, r1);
        r0 = r15.mBaseline;
        r0 = r0.mTarget;
        if (r0 == 0) goto L_0x029c;
    L_0x0289:
        r0 = r15.mBaseline;
        r0 = r0.mTarget;
        r0 = r13.createObjectVariable(r0);
        r3 = 0;
        r13.addEquality(r2, r0, r3, r1);
        r20 = r3;
        goto L_0x029e;
    L_0x0298:
        r12 = r39;
        r13 = r42;
    L_0x029c:
        r20 = r23;
    L_0x029e:
        r0 = r15.mParent;
        if (r0 == 0) goto L_0x02ac;
    L_0x02a2:
        r0 = r15.mParent;
        r0 = r0.mBottom;
        r0 = r13.createObjectVariable(r0);
        r4 = r0;
        goto L_0x02ae;
    L_0x02ac:
        r4 = r26;
    L_0x02ae:
        r0 = r15.mParent;
        if (r0 == 0) goto L_0x02bc;
    L_0x02b2:
        r0 = r15.mParent;
        r0 = r0.mTop;
        r0 = r13.createObjectVariable(r0);
        r3 = r0;
        goto L_0x02be;
    L_0x02bc:
        r3 = r26;
    L_0x02be:
        r0 = r15.mListDimensionBehaviors;
        r5 = r0[r14];
        r7 = r15.mTop;
        r8 = r15.mBottom;
        r9 = r15.mY;
        r11 = r15.mMinHeight;
        r0 = r15.mMaxDimension;
        r17 = r0[r14];
        r10 = r15.mVerticalBiasPercent;
        r2 = r15.mMatchConstraintDefaultHeight;
        r1 = r15.mMatchConstraintMinHeight;
        r0 = r15.mMatchConstraintMaxHeight;
        r14 = r15.mMatchConstraintPercentHeight;
        r18 = r0;
        r0 = r15;
        r19 = r1;
        r1 = r13;
        r23 = r2;
        r2 = r37;
        r26 = r10;
        r10 = r25;
        r25 = r12;
        r12 = r17;
        r13 = r26;
        r26 = r14;
        r14 = r16;
        r15 = r22;
        r16 = r23;
        r17 = r19;
        r19 = r26;
        r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);
        if (r24 == 0) goto L_0x0326;
    L_0x02fd:
        r6 = 6;
        r7 = r41;
        r0 = r7.mResolvedDimensionRatioSide;
        r1 = 1;
        if (r0 != r1) goto L_0x0316;
    L_0x0305:
        r5 = r7.mResolvedDimensionRatio;
        r6 = 6;
        r0 = r42;
        r1 = r27;
        r2 = r25;
        r3 = r28;
        r4 = r21;
        r0.addRatio(r1, r2, r3, r4, r5, r6);
        goto L_0x0328;
    L_0x0316:
        r5 = r7.mResolvedDimensionRatio;
        r0 = r42;
        r1 = r28;
        r2 = r21;
        r3 = r27;
        r4 = r25;
        r0.addRatio(r1, r2, r3, r4, r5, r6);
        goto L_0x0328;
    L_0x0326:
        r7 = r41;
    L_0x0328:
        r0 = r7.mCenter;
        r0 = r0.isConnected();
        if (r0 == 0) goto L_0x0350;
    L_0x0330:
        r0 = r7.mCenter;
        r0 = r0.getTarget();
        r0 = r0.getOwner();
        r1 = r7.mCircleConstraintAngle;
        r2 = 1119092736; // 0x42b40000 float:90.0 double:5.529052754E-315;
        r1 = r1 + r2;
        r1 = (double) r1;
        r1 = java.lang.Math.toRadians(r1);
        r1 = (float) r1;
        r2 = r7.mCenter;
        r2 = r2.getMargin();
        r3 = r42;
        r3.addCenterPoint(r7, r0, r1, r2);
    L_0x0350:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.addToSolver(android.support.constraint.solver.LinearSystem):void");
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mMatchConstraintDefaultWidth == 0) {
            this.mMatchConstraintDefaultWidth = 3;
        }
        if (this.mMatchConstraintDefaultHeight == 0) {
            this.mMatchConstraintDefaultHeight = 3;
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z3 && !z4) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z3 && z4) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && !(this.mLeft.isConnected() && this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && !(this.mTop.isConnected() && this.mBottom.isConnected() && this.mLeft.isConnected() && this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z && !z2) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z && z2) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide != -1) {
            return;
        }
        if (this.mMatchConstraintMinWidth > 0 && this.mMatchConstraintMinHeight == 0) {
            this.mResolvedDimensionRatioSide = 0;
        } else if (this.mMatchConstraintMinWidth != 0 || this.mMatchConstraintMinHeight <= 0) {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        } else {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:97:0x01ea  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02c7  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02af  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02db  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02ca  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x02de  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02e7  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02e7  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02e7  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02e7  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02e7  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e7  */
    private void applyConstraints(android.support.constraint.solver.LinearSystem r31, boolean r32, android.support.constraint.solver.SolverVariable r33, android.support.constraint.solver.SolverVariable r34, android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour r35, boolean r36, android.support.constraint.solver.widgets.ConstraintAnchor r37, android.support.constraint.solver.widgets.ConstraintAnchor r38, int r39, int r40, int r41, int r42, float r43, boolean r44, boolean r45, int r46, int r47, int r48, float r49, boolean r50) {
        /*
        r30 = this;
        r0 = r30;
        r10 = r31;
        r11 = r33;
        r12 = r34;
        r1 = r41;
        r2 = r42;
        r13 = r37;
        r9 = r10.createObjectVariable(r13);
        r8 = r38;
        r7 = r10.createObjectVariable(r8);
        r6 = r37.getTarget();
        r6 = r10.createObjectVariable(r6);
        r14 = r38.getTarget();
        r14 = r10.createObjectVariable(r14);
        r20 = r14;
        r14 = r10.graphOptimizer;
        r15 = 1;
        if (r14 == 0) goto L_0x0069;
    L_0x0030:
        r14 = r37.getResolutionNode();
        r14 = r14.state;
        r11 = 1;
        if (r14 != r11) goto L_0x0069;
    L_0x0039:
        r14 = r38.getResolutionNode();
        r14 = r14.state;
        if (r14 != r11) goto L_0x0069;
    L_0x0041:
        r1 = android.support.constraint.solver.LinearSystem.getMetrics();
        if (r1 == 0) goto L_0x0051;
    L_0x0047:
        r1 = android.support.constraint.solver.LinearSystem.getMetrics();
        r2 = r1.resolvedWidgets;
        r5 = r2 + r15;
        r1.resolvedWidgets = r5;
    L_0x0051:
        r1 = r37.getResolutionNode();
        r1.addResolvedValue(r10);
        r1 = r38.getResolutionNode();
        r1.addResolvedValue(r10);
        if (r45 != 0) goto L_0x0068;
    L_0x0061:
        if (r32 == 0) goto L_0x0068;
    L_0x0063:
        r1 = 0;
        r2 = 6;
        r10.addGreaterThan(r12, r7, r1, r2);
    L_0x0068:
        return;
    L_0x0069:
        r11 = android.support.constraint.solver.LinearSystem.getMetrics();
        if (r11 == 0) goto L_0x007e;
    L_0x006f:
        r11 = android.support.constraint.solver.LinearSystem.getMetrics();
        r1 = r11.nonresolvedWidgets;
        r22 = r6;
        r21 = r7;
        r6 = r1 + r15;
        r11.nonresolvedWidgets = r6;
        goto L_0x0082;
    L_0x007e:
        r22 = r6;
        r21 = r7;
    L_0x0082:
        r1 = r37.isConnected();
        r2 = r38.isConnected();
        r6 = r0.mCenter;
        r6 = r6.isConnected();
        if (r1 == 0) goto L_0x0094;
    L_0x0092:
        r7 = 1;
        goto L_0x0095;
    L_0x0094:
        r7 = 0;
    L_0x0095:
        if (r2 == 0) goto L_0x0099;
    L_0x0097:
        r7 = r7 + 1;
    L_0x0099:
        if (r6 == 0) goto L_0x009d;
    L_0x009b:
        r7 = r7 + 1;
    L_0x009d:
        if (r44 == 0) goto L_0x00a1;
    L_0x009f:
        r14 = 3;
        goto L_0x00a3;
    L_0x00a1:
        r14 = r46;
    L_0x00a3:
        r15 = android.support.constraint.solver.widgets.ConstraintWidget.AnonymousClass1.$SwitchMap$android$support$constraint$solver$widgets$ConstraintWidget$DimensionBehaviour;
        r16 = r35.ordinal();
        r15 = r15[r16];
        switch(r15) {
            case 1: goto L_0x00ae;
            case 2: goto L_0x00ae;
            case 3: goto L_0x00ae;
            case 4: goto L_0x00b0;
            default: goto L_0x00ae;
        };
    L_0x00ae:
        r15 = 0;
        goto L_0x00b1;
    L_0x00b0:
        r15 = 1;
    L_0x00b1:
        r11 = r0.mVisibility;
        r8 = 8;
        if (r11 != r8) goto L_0x00ba;
    L_0x00b7:
        r8 = 0;
        r11 = 0;
        goto L_0x00bd;
    L_0x00ba:
        r8 = r40;
        r11 = r15;
    L_0x00bd:
        if (r50 == 0) goto L_0x00de;
    L_0x00bf:
        if (r1 != 0) goto L_0x00cb;
    L_0x00c1:
        if (r2 != 0) goto L_0x00cb;
    L_0x00c3:
        if (r6 != 0) goto L_0x00cb;
    L_0x00c5:
        r12 = r39;
        r10.addEquality(r9, r12);
        goto L_0x00de;
    L_0x00cb:
        if (r1 == 0) goto L_0x00de;
    L_0x00cd:
        if (r2 != 0) goto L_0x00de;
    L_0x00cf:
        r12 = r37.getMargin();
        r24 = r2;
        r23 = r6;
        r6 = r22;
        r2 = 6;
        r10.addEquality(r9, r6, r12, r2);
        goto L_0x00e5;
    L_0x00de:
        r24 = r2;
        r23 = r6;
        r6 = r22;
        r2 = 6;
    L_0x00e5:
        if (r11 != 0) goto L_0x011e;
    L_0x00e7:
        if (r36 == 0) goto L_0x0109;
    L_0x00e9:
        r25 = r11;
        r12 = r21;
        r2 = 0;
        r11 = 3;
        r10.addEquality(r12, r9, r2, r11);
        r2 = r41;
        if (r2 <= 0) goto L_0x00fb;
    L_0x00f6:
        r11 = 6;
        r10.addGreaterThan(r12, r9, r2, r11);
        goto L_0x00fc;
    L_0x00fb:
        r11 = 6;
    L_0x00fc:
        r8 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r26 = r6;
        r6 = r42;
        if (r6 >= r8) goto L_0x0115;
    L_0x0105:
        r10.addLowerThan(r12, r9, r6, r11);
        goto L_0x0115;
    L_0x0109:
        r26 = r6;
        r25 = r11;
        r12 = r21;
        r11 = r2;
        r2 = r41;
        r10.addEquality(r12, r9, r8, r11);
    L_0x0115:
        r2 = r47;
        r11 = r48;
        r6 = r14;
        r0 = r20;
        goto L_0x01f4;
    L_0x011e:
        r26 = r6;
        r25 = r11;
        r12 = r21;
        r2 = r41;
        r6 = -2;
        r11 = r47;
        if (r11 != r6) goto L_0x012f;
    L_0x012b:
        r11 = r48;
        r2 = r8;
        goto L_0x0132;
    L_0x012f:
        r2 = r11;
        r11 = r48;
    L_0x0132:
        if (r11 != r6) goto L_0x0135;
    L_0x0134:
        r11 = r8;
    L_0x0135:
        if (r2 <= 0) goto L_0x0147;
    L_0x0137:
        if (r32 == 0) goto L_0x013e;
    L_0x0139:
        r6 = 6;
        r10.addGreaterThan(r12, r9, r2, r6);
        goto L_0x0142;
    L_0x013e:
        r6 = 6;
        r10.addGreaterThan(r12, r9, r2, r6);
    L_0x0142:
        r8 = java.lang.Math.max(r8, r2);
        goto L_0x0148;
    L_0x0147:
        r6 = 6;
    L_0x0148:
        if (r11 <= 0) goto L_0x0159;
    L_0x014a:
        if (r32 == 0) goto L_0x0152;
    L_0x014c:
        r6 = 1;
        r10.addLowerThan(r12, r9, r11, r6);
        r6 = 6;
        goto L_0x0155;
    L_0x0152:
        r10.addLowerThan(r12, r9, r11, r6);
    L_0x0155:
        r8 = java.lang.Math.min(r8, r11);
    L_0x0159:
        r6 = 1;
        if (r14 != r6) goto L_0x0172;
    L_0x015c:
        if (r32 == 0) goto L_0x0164;
    L_0x015e:
        r6 = 6;
        r10.addEquality(r12, r9, r8, r6);
        goto L_0x01da;
    L_0x0164:
        if (r45 == 0) goto L_0x016c;
    L_0x0166:
        r6 = 4;
        r10.addEquality(r12, r9, r8, r6);
        goto L_0x01da;
    L_0x016c:
        r6 = 1;
        r10.addEquality(r12, r9, r8, r6);
        goto L_0x01da;
    L_0x0172:
        r6 = 2;
        if (r14 != r6) goto L_0x01da;
    L_0x0175:
        r6 = r37.getType();
        r27 = r14;
        r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        if (r6 == r14) goto L_0x01a7;
    L_0x017f:
        r6 = r37.getType();
        r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        if (r6 != r14) goto L_0x0188;
    L_0x0187:
        goto L_0x01a7;
    L_0x0188:
        r6 = r0.mParent;
        r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT;
        r6 = r6.getAnchor(r14);
        r6 = r10.createObjectVariable(r6);
        r14 = r0.mParent;
        r28 = r6;
        r6 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT;
        r6 = r14.getAnchor(r6);
        r6 = r10.createObjectVariable(r6);
        r17 = r6;
        r18 = r28;
        goto L_0x01c3;
    L_0x01a7:
        r6 = r0.mParent;
        r14 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP;
        r6 = r6.getAnchor(r14);
        r6 = r10.createObjectVariable(r6);
        r14 = r0.mParent;
        r0 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM;
        r0 = r14.getAnchor(r0);
        r0 = r10.createObjectVariable(r0);
        r17 = r0;
        r18 = r6;
    L_0x01c3:
        r14 = r31.createRow();
        r0 = r20;
        r6 = r27;
        r15 = r12;
        r16 = r9;
        r19 = r49;
        r14 = r14.createRowDimensionRatio(r15, r16, r17, r18, r19);
        r10.addConstraint(r14);
        r25 = 0;
        goto L_0x01dd;
    L_0x01da:
        r6 = r14;
        r0 = r20;
    L_0x01dd:
        if (r25 == 0) goto L_0x01f4;
    L_0x01df:
        r14 = 2;
        if (r7 == r14) goto L_0x01f4;
    L_0x01e2:
        if (r44 != 0) goto L_0x01f4;
    L_0x01e4:
        r8 = java.lang.Math.max(r2, r8);
        if (r11 <= 0) goto L_0x01ee;
    L_0x01ea:
        r8 = java.lang.Math.min(r11, r8);
    L_0x01ee:
        r14 = 6;
        r10.addEquality(r12, r9, r8, r14);
        r25 = 0;
    L_0x01f4:
        if (r50 == 0) goto L_0x02ed;
    L_0x01f6:
        if (r45 == 0) goto L_0x01fa;
    L_0x01f8:
        goto L_0x02ed;
    L_0x01fa:
        r4 = 5;
        if (r1 != 0) goto L_0x020e;
    L_0x01fd:
        if (r24 != 0) goto L_0x020e;
    L_0x01ff:
        if (r23 != 0) goto L_0x020e;
    L_0x0201:
        if (r32 == 0) goto L_0x020a;
    L_0x0203:
        r5 = 0;
        r14 = r34;
        r10.addGreaterThan(r14, r12, r5, r4);
        goto L_0x021a;
    L_0x020a:
        r2 = 6;
    L_0x020b:
        r3 = 0;
        goto L_0x02e5;
    L_0x020e:
        r5 = 0;
        r14 = r34;
        if (r1 == 0) goto L_0x021e;
    L_0x0213:
        if (r24 != 0) goto L_0x021e;
    L_0x0215:
        if (r32 == 0) goto L_0x021a;
    L_0x0217:
        r10.addGreaterThan(r14, r12, r5, r4);
    L_0x021a:
        r3 = r5;
        r2 = 6;
        goto L_0x02e5;
    L_0x021e:
        if (r1 != 0) goto L_0x0233;
    L_0x0220:
        if (r24 == 0) goto L_0x0233;
    L_0x0222:
        r1 = r38.getMargin();
        r1 = -r1;
        r2 = 6;
        r10.addEquality(r12, r0, r1, r2);
        if (r32 == 0) goto L_0x021a;
    L_0x022d:
        r8 = r33;
        r10.addGreaterThan(r9, r8, r5, r4);
        goto L_0x021a;
    L_0x0233:
        r7 = 1;
        r8 = r33;
        if (r1 == 0) goto L_0x021a;
    L_0x0238:
        if (r24 == 0) goto L_0x021a;
    L_0x023a:
        if (r25 == 0) goto L_0x0297;
    L_0x023c:
        if (r32 == 0) goto L_0x0246;
    L_0x023e:
        r1 = r41;
        if (r1 != 0) goto L_0x0246;
    L_0x0242:
        r1 = 6;
        r10.addGreaterThan(r12, r9, r5, r1);
    L_0x0246:
        if (r6 != 0) goto L_0x0271;
    L_0x0248:
        if (r11 > 0) goto L_0x0250;
    L_0x024a:
        if (r2 <= 0) goto L_0x024d;
    L_0x024c:
        goto L_0x0250;
    L_0x024d:
        r1 = 6;
        r3 = 0;
        goto L_0x0252;
    L_0x0250:
        r3 = r7;
        r1 = 4;
    L_0x0252:
        r5 = r37.getMargin();
        r6 = r26;
        r10.addEquality(r9, r6, r5, r1);
        r5 = r38.getMargin();
        r5 = -r5;
        r10.addEquality(r12, r0, r5, r1);
        if (r11 > 0) goto L_0x026a;
    L_0x0265:
        if (r2 <= 0) goto L_0x0268;
    L_0x0267:
        goto L_0x026a;
    L_0x0268:
        r11 = 0;
        goto L_0x026b;
    L_0x026a:
        r11 = r7;
    L_0x026b:
        r16 = r3;
        r15 = r4;
        r7 = r11;
        r11 = r6;
        goto L_0x02ad;
    L_0x0271:
        r11 = r26;
        if (r6 != r7) goto L_0x0279;
    L_0x0275:
        r16 = r7;
        r15 = 6;
        goto L_0x02ad;
    L_0x0279:
        r1 = 3;
        if (r6 != r1) goto L_0x0294;
    L_0x027c:
        if (r44 != 0) goto L_0x0280;
    L_0x027e:
        r1 = 6;
        goto L_0x0281;
    L_0x0280:
        r1 = 4;
    L_0x0281:
        r2 = r37.getMargin();
        r10.addEquality(r9, r11, r2, r1);
        r2 = r38.getMargin();
        r2 = -r2;
        r10.addEquality(r12, r0, r2, r1);
        r15 = r4;
        r16 = r7;
        goto L_0x02ad;
    L_0x0294:
        r15 = r4;
        r7 = 0;
        goto L_0x02ab;
    L_0x0297:
        r11 = r26;
        if (r32 == 0) goto L_0x02aa;
    L_0x029b:
        r1 = r37.getMargin();
        r10.addGreaterThan(r9, r11, r1, r4);
        r1 = r38.getMargin();
        r1 = -r1;
        r10.addLowerThan(r12, r0, r1, r4);
    L_0x02aa:
        r15 = r4;
    L_0x02ab:
        r16 = 0;
    L_0x02ad:
        if (r7 == 0) goto L_0x02c7;
    L_0x02af:
        r4 = r37.getMargin();
        r17 = r38.getMargin();
        r1 = r10;
        r2 = r9;
        r3 = r11;
        r5 = r43;
        r6 = r0;
        r7 = r12;
        r14 = r8;
        r8 = r17;
        r14 = r9;
        r9 = r15;
        r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9);
        goto L_0x02c8;
    L_0x02c7:
        r14 = r9;
    L_0x02c8:
        if (r16 == 0) goto L_0x02db;
    L_0x02ca:
        r1 = r37.getMargin();
        r2 = 6;
        r10.addGreaterThan(r14, r11, r1, r2);
        r1 = r38.getMargin();
        r1 = -r1;
        r10.addLowerThan(r12, r0, r1, r2);
        goto L_0x02dc;
    L_0x02db:
        r2 = 6;
    L_0x02dc:
        if (r32 == 0) goto L_0x020b;
    L_0x02de:
        r1 = r14;
        r0 = r33;
        r3 = 0;
        r10.addGreaterThan(r1, r0, r3, r2);
    L_0x02e5:
        if (r32 == 0) goto L_0x02ec;
    L_0x02e7:
        r0 = r34;
        r10.addGreaterThan(r0, r12, r3, r2);
    L_0x02ec:
        return;
    L_0x02ed:
        r1 = r9;
        r0 = r34;
        r2 = 6;
        r3 = 0;
        r4 = r33;
        r5 = 2;
        if (r7 >= r5) goto L_0x02ff;
    L_0x02f7:
        if (r32 == 0) goto L_0x02ff;
    L_0x02f9:
        r10.addGreaterThan(r1, r4, r3, r2);
        r10.addGreaterThan(r0, r12, r3, r2);
    L_0x02ff:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.applyConstraints(android.support.constraint.solver.LinearSystem, boolean, android.support.constraint.solver.SolverVariable, android.support.constraint.solver.SolverVariable, android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour, boolean, android.support.constraint.solver.widgets.ConstraintAnchor, android.support.constraint.solver.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, int, int, int, float, boolean):void");
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        setFrame(linearSystem.getObjectVariableValue(this.mLeft), linearSystem.getObjectVariableValue(this.mTop), linearSystem.getObjectVariableValue(this.mRight), linearSystem.getObjectVariableValue(this.mBottom));
    }
}
