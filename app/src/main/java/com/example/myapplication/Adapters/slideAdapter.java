package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.example.myapplication.R;

public class slideAdapter extends PagerAdapter {
    LayoutInflater inflater ;
    Context context ;
    boolean is ;
int [] photos= {
R.drawable.slide3,
R.drawable.slide2,
R.drawable.slide1
};
String [] Titles= {
        "title 1",
        "title 2",
        "title 3"
};

    public slideAdapter(Context context,boolean slider ) {
        this.context = context;
        is=slider;
    }

    String [] contents= {

        "Content of the slide number 1",
        "Content of the slide number 2",
        "just wait  3 seconds",

};
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if(is) {
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.slide, container, false);
            TextView slideTitle = view.findViewById(R.id.title);
            ImageView slideImage = view.findViewById(R.id.slideimage);
            TextView slideContent = view.findViewById(R.id.content);
            slideImage.setImageResource(photos[position]);
            slideTitle.setText(Titles[position]);
            slideContent.setText(contents[position]);
            container.addView(view, position);
            return view;
        }
        else {
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.slide_categories, container, false);
            ImageView slideImage = view.findViewById(R.id.cat_slide_image);
            slideImage.setImageResource(photos[position]);
            container.addView(view, position);
            return view;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==object);
    }
}
