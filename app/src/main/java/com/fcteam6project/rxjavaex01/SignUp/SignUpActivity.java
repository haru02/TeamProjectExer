package com.fcteam6project.rxjavaex01.SignUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.fcteam6project.rxjavaex01.MainActivity;
import com.fcteam6project.rxjavaex01.R;

public class SignUpActivity extends AppCompatActivity implements SignUpViewInterface{

    private EditText etEmail;
    private EditText etPassword;
    private CheckBox cbMale;
    private CheckBox cbFemale;
    private ExpandableListView elYear;
    private ImageView tvClose;
    private Button btnSave;
    private SignUpViewInterface presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = (EditText)findViewById(R.id.editTextId);
        etPassword = (EditText)findViewById(R.id.editTextPassword);
        cbMale = (CheckBox)findViewById(R.id.checkBoxMale);
        cbFemale = (CheckBox)findViewById(R.id.checkBoxFemale);
        elYear = (ExpandableListView)findViewById(R.id.exListYear);
        tvClose = (ImageView)findViewById(R.id.imageViewClose);
        btnSave = (Button)findViewById(R.id.buttonSignUpSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String pw = etPassword.getText().toString();

            }
        });

        // 회원가입 Activity를 닫는다
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHome();
            }
        });
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
