package com.example.computer.Action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.computer.R;

/**
 * 关于界面 有作者信息
 *
 * @author tmnt
 */
public class About_Activity extends Activity {

    private ImageButton getback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        getback = (ImageButton) findViewById(R.id.getback);

        getback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(About_Activity.this, NextActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

}
