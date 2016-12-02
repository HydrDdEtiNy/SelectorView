package mobile.shtd.com.library;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 */
abstract class SelectorView extends ViewGroup {

    protected int mMaxNumber = 2;

    protected int mMarginHorizon = 1;

    protected int mMarginVertical = 1;

    protected int mHeight = 50;

    protected String[] mTitles;

    protected Drawable mItemSelectedDrawable;

    protected Drawable mItemUnselectedDrawable;

    protected int mTextSelectedColor;

    protected int mTextUnselectedColor;

    protected int mTextSize;

    public SelectorView(Context context) {
        this(context, null);
    }

    public SelectorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, child.getPaddingLeft() + child.getPaddingRight(), (width - getPaddingLeft() - getPaddingRight() - (mMarginHorizon * (mMaxNumber - 1))) / mMaxNumber);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, child.getPaddingTop() + child.getPaddingBottom(), mHeight);

            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }

        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? width : width + getPaddingLeft() + getPaddingRight(), (childCount / mMaxNumber + (childCount % mMaxNumber == 0 ? 0 : 1)) * (mHeight + mMarginVertical) - mMarginVertical + getPaddingTop() + getPaddingBottom());

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = getPaddingLeft();
        int top = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int width = childView.getMeasuredWidth();
            int height = childView.getMeasuredHeight();
            int line = i / mMaxNumber;
            int pos = i % mMaxNumber;
            if (pos == 0) {
                left = getPaddingLeft();
            }
            top = height * line + childView.getPaddingTop() + line * mMarginVertical + getPaddingTop();
            childView.layout(left, top, left + width, top + height);
            left += width + mMarginHorizon;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    protected abstract void clickItem(int pos);

    public Builder paramBuilder() {
        return new Builder();
    }

    public class Builder {

        private int maxNumber = 2;

        private int marginHorizon = 1;

        private int marginVertical = 1;

        private int height = 50;

        private String[] titles;

        private Drawable itemSelectedDrawable;

        private Drawable itemUnselectedDrawable;

        private int textSelectedColor;

        private int textUnselectedColor;

        private int textSize=12;


        public Builder setItems(String[] titles) {
            this.titles = titles;
            return this;
        }

        public Builder setMargin(int margin) {
            this.marginHorizon = margin;
            this.marginVertical = margin;
            return this;
        }

        public Builder setMarginHorizon(int margin) {
            this.marginHorizon = margin;
            return this;
        }

        public Builder setMarginVertical(int margin) {
            this.marginVertical = margin;
            return this;
        }

        public Builder setMaxNumber(int maxNumber) {
            this.maxNumber = maxNumber;
            return this;
        }

        public Builder setItemHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setTextSize(int size) {
            this.textSize = size;
            return this;
        }

        public Builder setItemDrawable(Drawable selectedDrawable, Drawable unselectedDrawable) {
            this.itemSelectedDrawable = selectedDrawable;
            this.itemUnselectedDrawable = unselectedDrawable;
            return this;
        }

        public Builder setTextColor(int selectedColor, int unselectedColor) {
            this.textSelectedColor = selectedColor;
            this.textUnselectedColor = unselectedColor;
            return this;
        }

        public Builder build() {
            mMaxNumber = maxNumber;
            mMarginHorizon = marginHorizon;
            mMarginVertical = marginVertical;
            mHeight = height;
            mTitles = titles;
            mItemSelectedDrawable = itemSelectedDrawable;
            mItemUnselectedDrawable = itemUnselectedDrawable;
            mTextSelectedColor = textSelectedColor;
            mTextUnselectedColor = textUnselectedColor;
            mTextSize = textSize;
            return this;
        }

        public void show() {
            removeAllViews();
            for (int i = 0; i < mTitles.length; i++) {
                final int pos = i;
                String text = mTitles[i];
                final TextView textView = new TextView(getContext());
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setSingleLine();
                textView.setBackground(mItemUnselectedDrawable);
                textView.setText(text);
                textView.setSingleLine(true);
                textView.setHeight(mHeight);
                textView.setTextColor(mTextSelectedColor);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(4, 0, 4, 0);
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickItem(pos);
                    }
                });
                addView(textView);
            }
        }
    }


}
