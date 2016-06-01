package com.example.computer;


import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;


import com.example.computer.Action.NextActivity;
import com.example.computer.Action.RegisterActivity;
import com.example.computer.DAO.DB_Helper;
import com.example.computer.Utilts.Utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 登陆界面
 *
 * @author tmnt
 */
public class MainActivity extends Activity implements OnClickListener {
    private Button btn1, btn2;
    private EditText editText1, editText2;
    private DB_Helper helper;

    /**
     * 主界面运行效果
     * 声明控件
     * 跳转到开始界面
     * 对控件进行监听
     * xml布局为main
     * Called when the activity is first created.
     */

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);


        btn2 = (Button) findViewById(R.id.register);
        btn2.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        helper = new DB_Helper(getApplicationContext());
        helper.openConn();

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn1) {
            if (helper.query(editText1.getText().toString(), editText2.getText().toString())) {
                Intent intent1 = new Intent(MainActivity.this, NextActivity.class);
                Utils.showToast(MainActivity.this, "登陆成功");
                startActivity(intent1);
            } else {
                editText1.setText("");
                editText2.setText("");
                Utils.showToast(MainActivity.this, "用户名或密码错误");
            }
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//返回按键
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK
                || keyCode == KeyEvent.KEYCODE_HOME) {
            showExitGameAlert();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showExitGameAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.exit_view, null, false);
        Button exit = (Button) view.findViewById(R.id.exit_view_dialog);
        Button cancel = (Button) view.findViewById(R.id.exit_view_dialog_revoked);

        builder.setView(view);
        AlertDialog dialog = builder.create();

        dialog.show();
        exit.setOnClickListener((V) -> {
            dialog.dismiss();
            finish();
        });
        cancel.setOnClickListener((V) -> {
        });

    }


}