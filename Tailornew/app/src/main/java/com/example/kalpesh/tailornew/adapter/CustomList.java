package com.example.kalpesh.tailornew.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kalpesh.tailornew.R;

public class CustomList extends ArrayAdapter<String> {
    private String[] urls;
    private Bitmap[] bitmaps;
    private Activity context;
   // private String[] ITEMNAME;
    //private String[] BASICRATE;

    public CustomList(Activity context, String[] urls, Bitmap[] bitmaps){//},String[] ITEMNAME,String[] BASICRATE) {
        super(context, R.layout.list_item, urls);
        this.context = context;
        this.urls= urls;
        this.bitmaps= bitmaps;
      //  this.ITEMNAME=ITEMNAME;
       // this.BASICRATE=BASICRATE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_item, null, true);
       // TextView itemname = (TextView) listViewItem.findViewById(R.id.tv_itemname);
       // TextView basicrate=(TextView)listViewItem.findViewById(R.id.tv_rate);
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imgpic);

        //textViewURL.setText(urls[position]);
        //image.setImageBitmap(Bitmap.createScaledBitmap(bitmaps[position],100,50,false));
       // itemname.setText(ITEMNAME[position]);
        //basicrate.setText(BASICRATE[position]);
        image.setImageBitmap(bitmaps[position]);
        return  listViewItem;
    }

}
