package com.example.baith4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultWinActivity extends AppCompatActivity {
private TextView txtWin,txtThoigian,txtTimeResult;
private Button btnReplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_win);
        txtThoigian=findViewById(R.id.txtThoigian);
        txtWin=findViewById(R.id.txtWin);
        txtTimeResult=findViewById(R.id.txtTimeResult);
        btnReplay=findViewById(R.id.btnPlayAgain);
        Intent myintent= getIntent();
        String str= myintent.getStringExtra("mykey");
        txtTimeResult.setText(str);
        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultWinActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}