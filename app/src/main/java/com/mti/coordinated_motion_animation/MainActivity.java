package com.mti.coordinated_motion_animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
/*
 * activity_main + dimen + cradview diclaration in gradle

 */


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animateViewsIn();
    }

    private void animateViewsIn() {
        ViewGroup root = (ViewGroup) findViewById(R.id.root);
        int count = root.getChildCount();
        float offset = getResources().getDimensionPixelSize(R.dimen.offset_y); //300dp
       //Interpolator defines frame between animation time interval.
        //In simple words type of animation
        Interpolator interpolator =
                AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in);

        // loop over the children setting an increasing translation y but the same animation
        // duration + interpolation
        for (int i = 0; i < count; i++) {
            View view = root.getChildAt(i);

            view.setVisibility(View.VISIBLE); //at Start
            view.setTranslationY(offset); //at start
            view.setAlpha(0.85f);
            // then animate back to natural position
            view.animate()
                    .translationY(0f) //at end
                    .alpha(1f)  //at end
                    .setInterpolator(interpolator) //animation type
                    .setDuration(1000L)
                    .start();
            // increase the offset distance for the next view
            offset *= 1.5f;
        }
    }

    public void containerClick(View view) {
        animateViewsIn(); //change this line with your own code
    }
}
