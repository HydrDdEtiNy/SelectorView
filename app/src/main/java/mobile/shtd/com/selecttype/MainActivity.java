package mobile.shtd.com.selecttype;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yuan.library.selector.Shape;

import mobile.shtd.com.library.MultiSelectorView;
import mobile.shtd.com.library.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MultiSelectorView selectorView = (MultiSelectorView) findViewById(R.id.select);
        String[] strings = new String[]{"a", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9"};

        Drawable drawable3 = new Shape.ShapeBuilder().strokeColor(Color.BLACK).corner(5).build().createGradientDrawable(Color.RED);
        Drawable drawable2 = new Shape.ShapeBuilder().strokeColor(Color.BLACK).corner(5).build().createGradientDrawable(Color.BLACK);

        selectorView.paramBuilder().setItems(strings).setTextColor(Color.WHITE, getResources().getColor(R.color.colorPrimary))
                .setItemDrawable(drawable3, drawable2).setMargin(30).setItemHeight(100).build().show();

        selectorView.setSelectNone(true).setSelectPos(new Integer[]{5, 2, 0, 1}).setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnClick(View view, int pos, boolean selected) {
                String a = "";
                for (int i : ((MultiSelectorView) view).getSelectPos()) {
                    a = a + i;
                }
                Toast.makeText(MainActivity.this, a, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
