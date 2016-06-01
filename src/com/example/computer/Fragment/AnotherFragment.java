package com.example.computer.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.computer.Action.AgainActivity;
import com.example.computer.Model.AnotherAnswer;
import com.example.computer.Model.Questions;
import com.example.computer.R;
import com.example.computer.Utilts.Utils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by tmnt on 2016/5/31.
 */
public class AnotherFragment extends Fragment {
    @InjectView(R.id.count)
    TextView mCount;
    @InjectView(R.id.tv1)
    TextView mTv1;
    @InjectView(R.id.tv2)
    TextView mTv2;
    @InjectView(R.id.tv3)
    TextView mTv3;
    @InjectView(R.id.tv4)
    TextView mTv4;
    @InjectView(R.id.tv5)
    TextView mTv5;
    @InjectView(R.id.btn_3)
    Button mBtn3;

    public static final String AOTHERANSWER = "answer";
    public static final String AOTHERPOTISION = "potision";
    public static final String AOTHERTYPE = "type";
    public static final String AOTHERKIND = "kind";
    public static final String ANOTHER = "another";

    private boolean a1, a2, a3, a4;

    private int type;
    private ArrayList<AnotherAnswer> mQuestionses;
    private int position;
    private int kind;

    private static final String TAG = "AnotherFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt(AOTHERTYPE);
        mQuestionses = (ArrayList<AnotherAnswer>) getArguments().getSerializable(ANOTHER);

        position = getArguments().getInt(AOTHERPOTISION);
        kind = getArguments().getInt(AOTHERKIND);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;


        view = inflater.inflate(R.layout.next_1_1, null, false);
        ButterKnife.inject(this, view);


        mTv1.setText(mQuestionses.get(position).getQuestion());
        BitmapDrawable drawable = new BitmapDrawable(Utils.readBitMap(getActivity(), R.drawable.background7));
        mTv1.setBackground(drawable);

        mTv2.setText(mQuestionses.get(position).getOptionA());
        mTv3.setText(mQuestionses.get(position).getOptionB());
        mTv4.setText(mQuestionses.get(position).getOptionC());
        mTv5.setText(mQuestionses.get(position).getOptionD());

        if (kind != 2) {
            mBtn3.setVisibility(View.GONE);
        } else if (kind == 2 && position != 19) {
            mBtn3.setVisibility(View.GONE);
        }


        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.btn_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv2:

                isClickOtherAnswer(mTv2, !a1 && !a2 && !a3 && !a4, 1);
                a1 = true;

//                if (!a1 && !a2 && !a3 && !a4) {
//                    changeBackground(mTv2);
//                    show(1);
//                    a1 = true;
//                } else {
//                    Utils.showToast(getActivity(), "you already click other answer");
//                }

                break;
            case R.id.tv3:
                isClickOtherAnswer(mTv3, !a1 & !a2 && !a3 && !a4, 2);
                a2 = true;
//                if (!a1 & !a2 && !a3 && !a4) {
//                    changeBackground(mTv3);
//                    show(2);
//                    a2 = true;
//                } else {
//                    Utils.showToast(getActivity(), "you already click other answer");
//                }

                break;
            case R.id.tv4:
                isClickOtherAnswer(mTv4, !a1 & !a2 && !a3 && !a4, 3);
                a3 = true;

//                changeBackground(mTv4);
//                show(3);
                break;
            case R.id.tv5:
                isClickOtherAnswer(mTv5, !a1 & !a2 && !a3 && !a4, 4);
                a4 = true;
//                changeBackground(mTv5);
//                show(4);
                break;

            case R.id.btn_3:
                break;
        }
    }

    public void isClickOtherAnswer(View v, boolean isClickItem, int f) {
        if (isClickItem) {
            changeBackground(v);
            show(f);

        } else {
            Utils.showToast(getActivity(), "you already click other answer");
        }
    }

    public void changeBackground(View v) {
        if (kind == 2) {
            v.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
    }

    public String getAnswer(int answer) {
        String a = null;
        switch (answer) {
            case 1:
                a = "A";
                break;
            case 2:
                a = "B";
                break;
            case 3:
                a = "C";
                break;
            case 4:
                a = "D";
                break;

        }
        return a;
    }

    public void show(int useranswer) {

        if (mQuestionses.get(position).getAnswer() == useranswer) {

            Utils.showToast(getActivity(), "right");
        } else if ((mQuestionses.get(position).getAnswer() != useranswer)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("hint");
            builder.setMessage("answer is " + getAnswer(mQuestionses.get(position).getAnswer()));
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    public static AnotherFragment newInstance(ArrayList<AnotherAnswer> questionses, int position, int type, int questionKind) {
        Bundle bundle = new Bundle();

        bundle.putSerializable(AOTHERPOTISION, position);
        bundle.putSerializable(AOTHERTYPE, type);
        bundle.putSerializable(AOTHERKIND, questionKind);

        bundle.putSerializable(ANOTHER, questionses);

        AnotherFragment fragmentCrime = new AnotherFragment();
        fragmentCrime.setArguments(bundle);
        return fragmentCrime;
    }
}
