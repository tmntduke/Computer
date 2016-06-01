package com.example.computer.Action;

import com.example.computer.Adapter.Fenlei_Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.computer.R;
import com.example.computer.Utilts.Constants;
import com.example.computer.Utilts.Utils;

public class SortActivity extends Activity {

    private Button backBut;
    private Fenlei_Adapter adapter;
    private ListView menuList;
    private boolean isMoblie;
    AlertDialog dialog2;
    AlertDialog dialog1;

    private String random_menu[] = {
            "java",
            "python",
            "c#",
            "other"
    };
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

                if (Utils.isConnected(SortActivity.this)) {


                    if (Utils.getNetWorkStatus(SortActivity.this) == 3 || Utils.getNetWorkStatus(SortActivity.this) == 4) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SortActivity.this);
                        builder.setTitle("warn");
                        builder.setMessage("you are  open the MobileConnected,are you continue");
                        builder.setPositiveButton("yes", ((dialog, which) -> {
                            isMoblie = true;
                        }));
                        dialog1 = builder.create();
                        dialog1.show();

                    } else if (Utils.isWifiConnected(SortActivity.this)) {
                        isMoblie = true;
                        randomIntent = new Intent(SortActivity.this, FenleiActivity.class);
                        switch (position) {
                            case 0:
                                turnNext(6);
                                break;
                            case 1:
                                turnNext(7);
                                break;
                            case 2:
                                turnNext(8);
                                break;
                            case 3:
                                turnNext(9);
                                break;

                            default:
                                break;
                        }

                    }

                    randomIntent = new Intent(SortActivity.this, FenleiActivity.class);
                    switch (position) {
                        case 0:
                            turnNext(6);
                            break;
                        case 1:
                            turnNext(7);
                            break;
                        case 2:
                            turnNext(8);
                            break;
                        case 3:
                            turnNext(9);
                            break;
                        case 4:
                            turnNext(10);
                            break;

                        default:
                            break;
                    }


                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SortActivity.this);
                    builder.setTitle("warn");
                    builder.setMessage("you are not open the network,click turn to setting");
                    builder.setPositiveButton("turn", ((dialog, which) -> {
                        Utils.openSetting(SortActivity.this);
                    }));
                    dialog2 = builder.create();
                    dialog2.show();
                }

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog1 != null) {
            dialog1.dismiss();
        } else if (dialog2 != null) {
            dialog2.dismiss();
        }


    }

    public void turnNext(int flag) {
        if (isMoblie) {
            randomIntent.putExtra("flag1", flag);
            startActivity(randomIntent);
        }

    }

}
