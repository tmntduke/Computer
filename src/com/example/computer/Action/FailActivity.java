package com.example.computer.Action;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.computer.R;

public class FailActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fail);
        init();
    }

	private void init() {
		// TODO Auto-generated method stub
//		try {
//			Thread.sleep(7000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Intent intent = new Intent(FailActivity.this,NextActivity.class);
		startActivity(intent);
	}
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//返回按键
    	// TODO Auto-generated method stub
    	
    	if (keyCode == KeyEvent.KEYCODE_BACK
				|| keyCode == KeyEvent.KEYCODE_HOME) {
            Intent intent = new Intent(FailActivity.this,NextActivity.class);
            startActivity(intent);
			return true;
		}
		return super.onKeyDown(keyCode, event);
    }
}
