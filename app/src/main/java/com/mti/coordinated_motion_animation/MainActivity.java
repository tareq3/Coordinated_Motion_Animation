package com.mti.coordinated_motion_animation;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
/*
 activity_main + cardview in gradle
 */
public class MainActivity extends AppCompatActivity {

    public static final float LARGE_SCALE = 1.5f;
    private boolean symmetric = true;
    private boolean small = true;
    private CardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card = (CardView) findViewById(R.id.card);
    }

    public void changeSize(View view) {
        Interpolator interpolator = AnimationUtils.loadInterpolator(this, android.R
                .interpolator.fast_out_slow_in);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(card, View.SCALE_X, (small ? LARGE_SCALE : 1f));
        scaleX.setInterpolator(interpolator);
        scaleX.setDuration(symmetric ? 600L : 200L);

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(card, View.SCALE_Y, (small ? LARGE_SCALE : 1f));
        scaleY.setInterpolator(interpolator);
        scaleY.setDuration(600L);
        scaleX.start();
        scaleY.start();

        // toggle the state so that we switch between large/small and symmetric/asymmetric
        small = !small;
        if (small) {
            symmetric = !symmetric;
        }
    }
}
