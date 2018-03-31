package com.example.vignesh.gpacalc;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context)
    {
        this.context = context;
    }
    //Arrays
    public int[] slide_image ={
      R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo
    };
    public String[] desc = {
            "Calculate your Anna University GPA with Accurate Results for Both 2013 and 2017 Regulation",
            "Calculate your Anna University CGPA with Accurate Results for Both 2013 and 2017 Regulation",
            "Save your GPA/CGPA both internally and as a PDF,Share your results with your friends"
    };
    @Override
    public int getCount() {
        return slide_image.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ConstraintLayout) object;
    }
    @Override
    public Object instantiateItem(ViewGroup container,int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container,false);
        ImageView slideImageView = (ImageView) view.findViewById(R.id.imageView);
        TextView descrip = (TextView) view.findViewById(R.id.description);
        slideImageView.setImageResource(slide_image[position]);
        descrip.setText(desc[position]);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position,Object object)
    {
        container.removeView((ConstraintLayout)object);
    }
}
