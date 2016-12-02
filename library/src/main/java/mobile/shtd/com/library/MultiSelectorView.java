package mobile.shtd.com.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 */

public class MultiSelectorView extends SelectorView {

    private List<Integer> mSelectedPos;

    private boolean bSelectNone;

    private OnItemClickListener mListener;

    public MultiSelectorView(Context context) {
        super(context);
        mSelectedPos = new ArrayList<>();
    }

    public MultiSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiSelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void clickItem(int pos) {
        if (!bSelectNone && mSelectedPos.size() == 1) {
            if (mSelectedPos.get(0) != pos) {
                mSelectedPos.add(pos);
            }
        } else {
            if (mSelectedPos.contains(pos)) {
                mSelectedPos.remove(mSelectedPos.indexOf(pos));
            } else {
                mSelectedPos.add(pos);
            }
        }

        updateSelectedChildBg(mSelectedPos);
        if (mListener != null) {
            mListener.OnClick(this, pos, mSelectedPos.contains(pos));
        }
    }


    private void updateSelectedChildBg(List<Integer> pos) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (pos.contains(i)) {
                ((TextView) view).setTextColor(mTextSelectedColor);
                view.setBackground(mItemSelectedDrawable);
            } else {
                ((TextView) view).setTextColor(mTextUnselectedColor);
                view.setBackground(mItemUnselectedDrawable);
            }
        }
    }


    public MultiSelectorView setSelectPos(Integer[] pos) {
        mSelectedPos = new ArrayList<>();
        if (pos != null) {
            for (int p : pos) {
                if (p < mTitles.length) {
                    mSelectedPos.add(p);
                }
            }
        }
        updateSelectedChildBg(mSelectedPos);
        return this;
    }

    public Integer[] getSelectPos() {
        Collections.sort(mSelectedPos);
        return mSelectedPos.toArray(new Integer[mSelectedPos.size()]);
    }

    public MultiSelectorView setSelectNone(boolean selectNone) {
        bSelectNone = selectNone;
        return this;
    }

    public MultiSelectorView setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
        return this;
    }

}
