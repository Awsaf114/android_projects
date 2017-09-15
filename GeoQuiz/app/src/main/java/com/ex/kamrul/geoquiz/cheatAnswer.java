package com.ex.kamrul.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class cheatAnswer extends AppCompatActivity {

    private boolean manswerIsTrue;
    private TextView mTextView,mapi_level;
    private Button mButton;


    public static final String EXTRA_ANSWER_IS_TRUE="com.ex.kamrul.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_IS_SHOWN="com.ex.kamrul.geoquiz.answer_is_shown";
    public static Intent newIntent(Context packageContext,boolean answerIsTrue)
    {
        Intent intent=new Intent(packageContext,cheatAnswer.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return intent;
    }


    public static boolean wasAnswerShown(Intent result)
    {
        return result.getBooleanExtra(cheatAnswer.EXTRA_ANSWER_IS_SHOWN,false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat_answer);
        manswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mTextView=(TextView)findViewById(R.id.answer_text_view);
        mapi_level=(TextView)findViewById(R.id.api_level);
        mButton=(Button)findViewById(R.id.show_answer_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(manswerIsTrue)
                    mTextView.setText(R.string.True_button);
                else
                    mTextView.setText(R.string.False_button);
                setAnswerShownResult(true);
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                    int cx = mButton.getWidth() / 2;
                    int cy = mButton.getHeight() / 2;
                    float radius = mButton.getWidth();
                    Animator anim = ViewAnimationUtils.createCircularReveal(mButton, cx, cy, radius, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationCancel(Animator animation) {
                            super.onAnimationEnd(animation);
                            mTextView.setVisibility(View.VISIBLE);
                            mButton.setVisibility(View.INVISIBLE);
                        }

                    });

                    anim.start();
                }
            }
        });

        mapi_level.setText(Integer.toString(Build.VERSION.SDK_INT));
    }

    private void setAnswerShownResult(boolean isAnswerShown)
    {
        Intent data=new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }
}
