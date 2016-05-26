package com.example.computer.Action;

import com.example.computer.Adapter.Fenlei_Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.computer.R;

public class SortActivity extends Activity{
	
	private Button backBut;
	private Fenlei_Adapter adapter;
	private ListView menuList;
	
	private String random_menu[] = { 
			"全部试题", 
			"机动车安全形式相关试题", 
			"自动挡汽车安全行驶相关知识",
			"在特殊道路上安全行驶试题", 
			"夜间行车注意事项",
			"行车之中遇到突发事件试题",
			"车辆安全行驶试题",
			"恶劣天安全气行驶试题" };
	private Intent randomIntent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fenlei);
		
		backBut = (Button) findViewById(R.id.random_getback);
		menuList = (ListView) findViewById(R.id.random_list);
		adapter = new Fenlei_Adapter(this, random_menu);
		menuList.setAdapter(adapter);
		
		backBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				finish();
			}
		});
		
		menuList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				randomIntent = new Intent(SortActivity.this,FenleiActivity.class);
				switch (position) {
				case 0:
					randomIntent.putExtra("flag1", 6);
					startActivity(randomIntent);
					break;
				case 1:
					randomIntent.putExtra("flag1", 7);
					startActivity(randomIntent);
					break;
				case 2:
					randomIntent.putExtra("flag1", 8);
					startActivity(randomIntent);
					break;
				case 3:
					randomIntent.putExtra("flag", 9);
					startActivity(randomIntent);
					break;
				case 4:
					randomIntent.putExtra("flag1", 10);
					startActivity(randomIntent);
					break;
				case 5:
					randomIntent.putExtra("flag1", 11);
					startActivity(randomIntent);
					break;
				case 6:
					randomIntent.putExtra("flag", 12);
					startActivity(randomIntent);
					break;
				case 7:
					randomIntent.putExtra("flag1", 13);
					startActivity(randomIntent);
					break;
				
				default:
					break;
				}
				SortActivity.this.finish();
			}
		});
	}
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//返回按键
    	// TODO Auto-generated method stub
    	
    	if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
            Intent intent = new Intent(SortActivity.this,NextActivity.class);
            startActivity(intent);
			return true;
		}
		return super.onKeyDown(keyCode, event);
    }
}
