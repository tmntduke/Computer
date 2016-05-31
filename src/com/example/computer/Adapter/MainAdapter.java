package com.example.computer.Adapter;


import android.content.Context;

import com.example.computer.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * listView的自定义适配器
 *
 * @author tmnt
 */
public class MainAdapter extends BaseAdapter {

    private Context context;
    private int logo[];
    private String name[];
    private String introduce[];

    public MainAdapter(Context context, int[] logo, String[] name,
                       String[] introduce) {
        super();
        this.context = context;
        this.logo = logo;
        this.name = name;
        this.introduce = introduce;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return logo.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position % logo.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);


        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.main_listview_layout, null);
            viewHolder = new ViewHolder();

            viewHolder.imageView = (ImageView) view.findViewById(R.id.main_listview_imageview);
            viewHolder.textView = (TextView) view.findViewById(R.id.main_listview_textview01);
            viewHolder.introduceText = (TextView) view.findViewById(R.id.main_listview_textview02);
            viewHolder.pass = (ImageView) view.findViewById(R.id.main_listview_imageview_pass);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource(logo[position]);
        viewHolder.textView.setText(name[position]);
        viewHolder.introduceText.setText(introduce[position]);
        view.getBackground().setAlpha(120);

        return view;
    }

    class ViewHolder {
        ImageView imageView, pass;
        TextView textView, introduceText;
    }

}