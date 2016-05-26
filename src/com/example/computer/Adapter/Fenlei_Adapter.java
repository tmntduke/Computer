package com.example.computer.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.computer.R;
import com.example.computer.Adapter.MainAdapter.ViewHolder;

public class Fenlei_Adapter extends BaseAdapter{

	private Context context;
	private String menu[];
	
	public Fenlei_Adapter(Context context, String menu[]) {
		super();
		this.context = context;
		this.menu = menu;
	}

	@Override
	public int getCount() {

		return menu.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return  arg0%menu.length;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0%menu.length;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		View view;
		ViewHolder viewHolder;
		if (convertView==null) {
			 view =  LayoutInflater.from(context).inflate(R.layout.fenlei_list, null);
			 viewHolder=new ViewHolder();
			 view.setTag(viewHolder);
		}
		else {
			view=convertView;
			viewHolder=(ViewHolder) view.getTag();
		}
		
		
		
		viewHolder.textView= (TextView) view.findViewById(R.id.random_menu);
		viewHolder.textView.setText(menu[arg0]);
		return view;
	}
	
	class ViewHolder{
		
		TextView textView;
	}

}
