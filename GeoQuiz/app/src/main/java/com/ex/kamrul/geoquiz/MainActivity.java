package com.ex.kamrul.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton,mFalseButton,mCheatButton;
    private ImageButton mNextButton,mPrevButton;
    private TextView mTextView;

    private static final String TAG="test";
    private static final String KEY_INDEX="index";
    private static final String KEY_CHEAT="index_CHEAT";
    private static final int REQUEST_CODE_CHEAT=0;


    private int mCurrentIndex=0;
    private boolean mIsCheater;
    boolean[] cheatingList;
    private Question[] mQuestions=new Question[]{
            new Question(R.string.q_africa,false),
            new Question(R.string.q_asia,true),
            new Question(R.string.q_bd,false),
            new Question(R.string.q_ocean,true),
            new Question(R.string.q_usa,false),

    };


    private void updateQuestion(){
        //Log.d(TAG,"Updating question TAG for question #"+mCurrentIndex,new Exception());
        int question=mQuestions[mCurrentIndex].getTextResID();
        mTextView.setText(question);
    }




    private void checkAnswer(boolean userPressedTrue)
    {
        boolean answerIsTrue=mQuestions[mCurrentIndex].isAnswerTrue();
        int messageResID=0;
        if(cheatingList[mCurrentIndex])
            messageResID=R.string.Judgment_toast;
        else if(userPressedTrue==answerIsTrue)
            messageResID=R.string.Correct;
        else
            messageResID=R.string.Incorrect;

        Toast.makeText(MainActivity.this,messageResID,Toast.LENGTH_SHORT).show();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"oncreate");
        mTrueButton=(Button)findViewById(R.id.true_button);
        mFalseButton=(Button)findViewById(R.id.false_button);
        mNextButton=(ImageButton)findViewById(R.id.next);
        mPrevButton=(ImageButton)findViewById(R.id.prev);
        mTextView=(TextView)findViewById(R.id.textview);
        mCheatButton=(Button)findViewById(R.id.cheat_button);

        if(savedInstanceState!=null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
            cheatingList=savedInstanceState.getBooleanArray(KEY_CHEAT);

        }
        if(cheatingList==null)
            cheatingList=new boolean[6];
        updateQuestion();

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });


        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestions.length;
                mIsCheater=false;
                updateQuestion();

            }
        });
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex-1)%mQuestions.length;
                mIsCheater=cheatingList[mCurrentIndex];
                updateQuestion();
            }
        });





        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answerIsTrue=mQuestions[mCurrentIndex].isAnswerTrue();
                Intent i=cheatAnswer.newIntent(MainActivity.this,answerIsTrue);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"on SaveInstaceState");
        outState.putInt(KEY_INDEX,mCurrentIndex);
        outState.putBooleanArray(KEY_CHEAT,cheatingList);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK)
        {
            return;
        }

        if(requestCode==REQUEST_CODE_CHEAT)
        {
            if(data==null)
            {
                return ;
            }
            mIsCheater=cheatAnswer.wasAnswerShown(data);
            cheatingList[mCurrentIndex]=mIsCheater;
        }
    }
}
