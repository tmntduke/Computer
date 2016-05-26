package com.example.computer.Fragment;

import android.app.AlertDialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.computer.DAO.DB_Helper;
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
    @InjectView(R.id.btn_1)
    Button mBtn1;
    @InjectView(R.id.btn_2)
    Button mBtn2;
    @InjectView(R.id.btn_3)
    Button mBtn3;
    @InjectView(R.id.count)
    TextView mCount;
    private int type;
    private ArrayList<Questions> mQuestionses;
    private int position;

    private int answer;
    private DB_Helper db_Hleper;

    private static final String TAG = "AnswerFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt(TYPE);
        mQuestionses = (ArrayList<Questions>) getArguments().getSerializable(ANSWER);
        position = getArguments().getInt(POTISION);

        //Log.i(TAG, "onCreate: " + type);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.next_1_1, container, false);
        ButterKnife.inject(this, view);

        mCount.setText(mQuestionses.get(position).get_id() + "/" + mQuestionses.size());
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


        return view;
    }

    public void show(int useranswer) {
        if (mQuestionses.get(position).getAnswer() == useranswer) {
            Utils.showToast(getActivity(), "right");
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("hint");
            builder.setMessage("answer is " + getAnswer(mQuestionses.get(position).getAnswer()));
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    /**
     * 创建fragment实例
     *
     * @param questionses 将问题集合传入
     * @param position    当前问题页面
     * @param type        问题的类型
     * @return
     */
    public static AnswerFragment newInstance(ArrayList<Questions> questionses, int position, int type) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ANSWER, questionses);
        bundle.putSerializable(POTISION, position);
        bundle.putSerializable(TYPE, type);
        AnswerFragment fragmentCrime = new AnswerFragment();
        fragmentCrime.setArguments(bundle);
        return fragmentCrime;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick({R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.btn_1, R.id.btn_2, R.id.btn_3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv2:
                show(1);
                break;
            case R.id.tv3:
                show(2);
                break;
            case R.id.tv4:
                show(3);
                break;
            case R.id.tv5:
                show(4);
                break;
            case R.id.btn_1:
                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                break;
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


}
