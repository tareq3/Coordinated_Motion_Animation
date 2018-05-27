package com.mti.coordinated_motion_animation;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.view.View;

 /*This Animation need sdk version 21 or lillipop*/
public class CurvedMotionDetail extends AppCompatActivity {

    public static final String EXTRA_COLOR = "EXTRA_COLOR";
    public static final String EXTRA_CURVE = "EXTRA_CURVE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curved_motion_detail);

        // tint the circle to match the launching activity
        View avatar = findViewById(R.id.avatar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            avatar.setBackgroundTintList(
                    ColorStateList.valueOf(getIntent().getIntExtra(EXTRA_COLOR, 0xffff00ff))); // getting Extra parameters from previous activity and changing the color of avater

        }

        // check if we should used curved motion and load an appropriate transition
        boolean curve = getIntent().getBooleanExtra(EXTRA_CURVE, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this)
                    .inflateTransition(curve ? R.transition.curve : R.transition.move)); //if the parameter says curve then we will use curve else move animation
        }
    }
}
