package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapters.slideAdapter;

public class SliderActivity extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout dotsLayout;
    ImageView[] dots;
    int SPlash_OUT_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        viewPager = findViewById(R.id.viewPager);

        dotsLayout = findViewById(R.id.splash);
        slideAdapter adapter = new slideAdapter(this, true);
        viewPager.setAdapter(adapter);
        createDots(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                if (position == 2) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent home_intent = new Intent(SliderActivity.this, MainActivity.class);
                            startActivity(home_intent);
                            finish();
                        }
                    }, SPlash_OUT_TIME);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    void createDots(int currentPosition) {
        if (dotsLayout != null)
            dotsLayout.removeAllViews();
        dots = new ImageView[3];
        for (int i = 0; i < 3; i++) {
            dots[i] = new ImageView(this);
            if (i == currentPosition) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots));
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_ots));

            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            dotsLayout.addView(dots[i], params);
        }


    }
}
