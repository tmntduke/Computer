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
import com.example.computer.DAO.DB_Helper;
import com.example.computer.Model.AnotherAnswer;
import com.example.computer.Model.Questions;
import com.example.computer.R;
import com.example.computer.Utilts.Utils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AnswerFragment extends Fragment implements View.OnClickListener {

    public static final String ANSWER = "answer";
    public static final String POTISION = "potision";
    public static final String TYPE = "type";
    public static final String KIND = "kind";
    public static final String ANOTHER = "another";


    private boolean a1, a2, a3, a4;

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
    Button mBtn2;
    @InjectView(R.id.btn_3)
    Button mBtn3;
    @InjectView(R.id.count)
    TextView mCount;
    private int type;
    private ArrayList<Questions> mQuestionses;
    private int position;
    private int kind;

    private ArrayList<Questions> wrongCount;

    private static boolean isClickItem = true;

    private int answer;
    private DB_Helper db_Hleper;

    private static final String TAG = "AnswerFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt(TYPE);
        mQuestionses = (ArrayList<Questions>) getArguments().getSerializable(ANSWER);

        position = getArguments().getInt(POTISION);
        kind = getArguments().getInt(KIND);


        //Log.i(TAG, "onCreate: " + type);
        db_Hleper = new DB_Helper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        if (mQuestionses.size() == 0) {
            view = inflater.inflate(R.layout.null_lay, container);
        } else {

            view = inflater.inflate(R.layout.next_1_1, container, false);
            ButterKnife.inject(this, view);

            if (kind == 2) {
                mCount.setText((position + 1) + "/" + "20");
            } else {
                mCount.setText(mQuestionses.get(position).get_id() + "/" + mQuestionses.size());
            }

            mTv1.setText(mQuestionses.get(position).getQuestion());
            BitmapDrawable drawable = new BitmapDrawable(Utils.readBitMap(getActivity(), R.drawable.background7));
            mTv1.setBackground(drawable);

            mTv2.setText(mQuestionses.get(position).getOptionA());
            mTv3.setText(mQuestionses.get(position).getOptionB());
            if (type == 1) {

                mTv4.setText(mQuestionses.get(position).getOptionC());
                mTv5.setText(mQuestionses.get(position).getOptionD());

            } else if (type == 0) {
                mTv4.setVisibility(View.GONE);
                mTv5.setVisibility(View.GONE);
            }

            if (kind != 2) {
                mBtn3.setVisibility(View.GONE);
            } else if (kind == 2 && position == 19) {

                mBtn3.setOnClickListener((v) -> {
                    wrongCount = db_Hleper.queryAllWrong();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("hint");
                    builder.setMessage("wrong:  " + wrongCount.size() + "   turn to other view");
                    Log.i(TAG, "onCreateView: " + wrongCount.size());
                    builder.setPositiveButton("yes", ((dialog1, which) -> {
                        Intent intent = new Intent(getActivity(), AgainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }));
                    AlertDialog dialog = builder.create();
                    dialog.show();
                });

            } else if (kind == 2 && position != 19) {
                mBtn3.setVisibility(View.GONE);
            }
        }

        return view;
    }

    /**
     * 根据用户答案，进行相应的提示
     *
     * @param useranswer 用户的答案
     */
    public void show(int useranswer) {

        SharedPreferences.Editor editor = getActivity().getSharedPreferences("clickItem", Context.MODE_PRIVATE).edit();


        if (kind != 2 && mQuestionses.get(position).getAnswer() == useranswer) {
            isClickItem = false;
            Utils.showToast(getActivity(), "right");
        } else if (kind != 2 && (mQuestionses.get(position).getAnswer() != useranswer)) {
            isClickItem = false;
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("hint");
            builder.setMessage("answer is " + getAnswer(mQuestionses.get(position).getAnswer()));
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (kind == 2 && (mQuestionses.get(position).getAnswer() != useranswer) && !a1 && !a2 && !a3 && !a4) {
            isClickItem = false;
            Log.i(TAG, "show: " + mQuestionses.get(position));
            //wrongCount.add(mQuestionses.get(position));
            db_Hleper.addWrong(mQuestionses.get(position));
        } else if (kind == 2 && (mQuestionses.get(position).getAnswer() == useranswer)) {
            isClickItem = false;
        }

        editor.putBoolean("click", isClickItem);
        editor.commit();
    }

    public boolean isClickItem() {
        return isClickItem;
    }


    /**
     * 创建fragment实例
     *
     * @param questionses 将问题集合传入
     * @param position    当前问题页面
     * @param type        问题的类型
     * @return
     */
    public static AnswerFragment newInstance(ArrayList<Questions> questionses, int position, int type, int questionKind) {
        Bundle bundle = new Bundle();

        bundle.putSerializable(POTISION, position);
        bundle.putSerializable(TYPE, type);
        bundle.putSerializable(KIND, questionKind);

        bundle.putSerializable(ANSWER, questionses);

        AnswerFragment fragmentCrime = new AnswerFragment();
        fragmentCrime.setArguments(bundle);
        return fragmentCrime;
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

    /**
     * 判断是否点击了其他的按钮 并改变点击的选项
     *
     * @param v
     * @param isClickItem
     * @param f
     */
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

    /**
     * 将答案代码转换为英文
     *
     * @param answer
     * @return
     */
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


}
