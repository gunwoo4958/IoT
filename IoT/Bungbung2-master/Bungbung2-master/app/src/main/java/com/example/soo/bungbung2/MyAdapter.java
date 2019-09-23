package com.example.soo.bungbung2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Kwanwoo on 2016-09-05.
 */
public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private int mResource;
    Bitmap bitmap;
    ImageView imageView;
    static Double xx;
    static Double yy;
    private ArrayList<Contents> mItems = new ArrayList<Contents>();
    int i=0;
    public MyAdapter(Context context, int resource, ArrayList<Contents> items) {
        mContext = context;
        mItems = items;
        mResource = resource;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent,false);
        }
//        // Set Text 01
//        TextView category = (TextView) convertView.findViewById(R.id.category);
//        category.setText("카테고리: "+mItems.get(position).category);
//
//        // Set Text 02
//        TextView title = (TextView) convertView.findViewById(R.id.title);
//        title.setText("제목: "+mItems.get(position).title);
//
//        // Set Text 02
//        TextView content = (TextView) convertView.findViewById(R.id.content);
//        content.setText("내용: "+mItems.get(position).content);
//
//        // Set Text 02
//        TextView price = (TextView) convertView.findViewById(R.id.price);
//        price.setText("학번,번호: "+mItems.get(position).price);
        Button location=(Button)convertView.findViewById(R.id.button2);
        final Button rent=(Button)convertView.findViewById(R.id.button5);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,Location.class);
                mContext.getApplicationContext().startActivity(intent);
            }
        });
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

    if(i%2==0){
        rent.setText("반납하기");
    }
    else {
        rent.setText("대여하기");
    }
i++;


            }
        });


        TextView gps=(TextView) convertView.findViewById(R.id.gpsvalue);
        gps.setText(mItems.get(position).x);
        TextView gps2=(TextView) convertView.findViewById(R.id.gpsvalue2);
        gps2.setText(mItems.get(position).y);
        Intent intent=new Intent(mContext,MyAdapter.class);
        intent.putExtra("x",mItems.get(position).x);
        intent.putExtra("y",mItems.get(position).y);
        xx= Double.valueOf(mItems.get(position).x);
        yy= Double.valueOf(mItems.get(position).y);


        return convertView;
    }
}

