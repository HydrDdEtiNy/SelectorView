package mobile.shtd.com.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 */

public class SingleSelectorView extends SelectorView {
    public static int SELECT_NONE = -1;

    private int mSelectedPos;

    private boolean bSelectNone;

    private OnItemClickListener mListener;

    public SingleSelectorView(Context context) {
        super(context);
    }

    public SingleSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SingleSelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void clickItem(int pos) {
        if (bSelectNone) {
            if (pos == mSelectedPos) {
                mSelectedPos = SELECT_NONE;
            } else {
                mSelectedPos = pos;
            }
        } else {
            mSelectedPos = pos;
        }

        updateSelectedChildBg(mSelectedPos);
        if (mListener != null) {
            mListener.OnClick(this, mSelectedPos, mSelectedPos == SELECT_NONE);
        }
    }


    private void updateSelectedChildBg(int pos) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (i == pos) {
                ((TextView) view).setTextColor(mTextSelectedColor);
                view.setBackground(mItemSelectedDrawable);
            } else {
                ((TextView) view).setTextColor(mTextUnselectedColor);
                view.setBackground(mItemUnselectedDrawable);
            }
        }
    }

    public SingleSelectorView setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
        return this;
    }

    public SingleSelectorView setSelectPos(int pos) {
        if (pos < mTitles.length) {
            mSelectedPos = pos;
            updateSelectedChildBg(mSelectedPos);
        } else {
            throw new IndexOutOfBoundsException("position out of bounds");
        }
        return this;
    }

    public int getSelectPos() {
        return mSelectedPos;
    }

    public SingleSelectorView setSelectNone(boolean selectNone) {
        bSelectNone = selectNone;
        return this;
    }
}
