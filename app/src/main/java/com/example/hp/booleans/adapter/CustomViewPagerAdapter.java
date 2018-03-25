package com.example.hp.booleans.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.booleans.FetchData.Data;
import com.example.hp.booleans.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 24-03-2018.
 */

public class CustomViewPagerAdapter extends PagerAdapter {
    ArrayList<Data> arrayList;
    Context context;
    LayoutInflater layoutInflater;

    public CustomViewPagerAdapter(Context context, ArrayList<Data> data) {
        this.context = context;
        arrayList = data;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.view_list, container, false);
        String imgUrl = arrayList.get(position).getImage_url();
        String title = (arrayList.get(position).getTitle());
        ImageView imageView = (ImageView) itemView.findViewById(R.id.view_imageView);
        TextView textView = (TextView)itemView.findViewById(R.id.view_textview);
        textView.setText(title);
        textView.setPaintFlags(textView.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        Picasso.get().load(imgUrl).into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}
