package com.fcteam6project.rxjavaex01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fcteam6project.rxjavaex01.SignUp.SignUpActivity;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.text.DateFormat;
import java.util.Date;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    TextView tvHashTag;
    String TAG = "RXjava";
    boolean flag= false;
    String hashtag = "";
    int hashtagStartIndex;
    int hashtagEndIndex;
    int hashtagLastIndex=0;
    String hashtagSum ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<TextViewTextChangeEvent> mainContextObs = RxTextView.textChangeEvents((EditText)findViewById(R.id.editTextMainContent));
        tvHashTag = (TextView)findViewById(R.id.textViewHashTag);

        mainContextObs.filter(s -> {
            flag= false;
            // 두 번째 hashtag는 첫 번째 hashtagLastIndex부터 다시 위치를 계산함
            String fullSentence =s.text().toString().substring(hashtagLastIndex);
            // 공백+#부터를 hashtag로 인식한다(+1은 공백임)
            hashtagStartIndex = fullSentence.indexOf(" #")+1;
            if(hashtagStartIndex > 1){
                hashtagEndIndex = fullSentence.substring(hashtagStartIndex).indexOf(32)+hashtagStartIndex;
                if(hashtagEndIndex>hashtagStartIndex){
                    hashtag = fullSentence.substring(hashtagStartIndex, hashtagEndIndex);
                    hashtagLastIndex += hashtagEndIndex;
                    // subscribe 표시를 위한 flag 설정
                    Log.i(TAG, "has "+flag);
                    flag = true;
                }
            }
            return flag;
        } )
        .subscribe(
                result -> {
                    hashtagSum=hashtagSum+"  "+hashtag;
                    tvHashTag.setText(hashtagSum);
                    hashtagEndIndex=-1;
                    hashtagStartIndex=-1;
                }

        );

        TextView tvDate = (TextView)findViewById(R.id.textViewDate);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        tvDate.setText(currentDateTimeString);

        TextView tvSignUp = (TextView)findViewById(R.id.textViewMainContentSignUp);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}
