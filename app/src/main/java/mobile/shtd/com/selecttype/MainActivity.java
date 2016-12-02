package mobile.shtd.com.selecttype;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yuan.library.selector.Shape;

import mobile.shtd.com.library.MultiSelectorView;
import mobile.shtd.com.library.OnItemClickListener;
import mobile.shtd.com.library.SingleSelectorView;

public class MainActivity extends AppCompatActivity {

    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.result);
        SingleSelectorView singleSelectorView = (SingleSelectorView) findViewById(R.id.single_select);

        String[] strings = new String[10];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = "a" + i;
        }

        Drawable drawableSelect = new Shape.ShapeBuilder().corner(ScreenUtils.dip2px(this, 18)).build().createGradientDrawable(getResources().getColor(R.color.blueCommon));
        Drawable drawableUnselect = new Shape.ShapeBuilder().corner(ScreenUtils.dip2px(this, 18)).build().createGradientDrawable(getResources().getColor(R.color.grayc7));

        singleSelectorView.paramBuilder().setItems(strings).setTextColor(Color.WHITE, Color.BLACK)
                .setItemDrawable(drawableSelect, drawableUnselect).setMargin(ScreenUtils.dip2px(this, 18)).setItemHeight(ScreenUtils.dip2px(this, 18))
                .setMaxNumber(3).build().show();

        singleSelectorView.setSelectNone(false).setSelectPos(1).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnClick(View view, int pos, boolean selected) {
                mTv.setText(pos + "");
            }
        });


        MultiSelectorView multiSelectorView = (MultiSelectorView) findViewById(R.id.multi_select);

        multiSelectorView.paramBuilder().setItems(strings).setTextColor(Color.WHITE, Color.BLACK)
                .setItemDrawable(drawableSelect, drawableUnselect).setMarginHorizon(ScreenUtils.dip2px(this, 8)).setMarginVertical(ScreenUtils.dip2px(this, 20))
                .setItemHeight(ScreenUtils.dip2px(this, 36)).setMaxNumber(4).build().show();

        multiSelectorView.setSelectNone(true).setSelectPos(new Integer[]{5, 2, 0, 1}).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnClick(View view, int pos, boolean selected) {
                String result = "";
                for (int i : ((MultiSelectorView) view).getSelectPos()) {
                    result = result + i;
                }
                mTv.setText(result);
            }
        });


    }
}
