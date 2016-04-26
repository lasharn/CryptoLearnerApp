package com.cryptolearner.mobile.cryptolearner;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lukedeighton.wheelview.WheelView;
import com.lukedeighton.wheelview.adapter.WheelAdapter;

public class DummyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        WheelView wheelView = (WheelView) findViewById(R.id.wheelview);
//        if (wheelView == null) {} else {
//            wheelView.setAdapter(new WheelAdapter() {
//                @Override
//                public Drawable getDrawable(int position) {
//                    Drawable[] drawable = new Drawable[]{
//                            createOvalDrawable(Color.parseColor("#356723")),
//                            //new TextDrawable(String.valueOf(position))
//                    };
//                    return new LayerDrawable(drawable);
//                }
//
//                private Drawable createOvalDrawable(int color) {
//                    ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
//                    shapeDrawable.getPaint().setColor(color);
//                    return shapeDrawable;
//                }
//
//                @Override
//                public Object getItem(int position) {
//                    return null;
//                }
//
//                @Override
//                public int getCount() {
//                    //return the count
//                    return 1;
//                }
//            });
//        }
    }

}
