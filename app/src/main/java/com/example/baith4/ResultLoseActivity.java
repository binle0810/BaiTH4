package com.example.baith4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultLoseActivity extends AppCompatActivity {
    private Button btnReplay;
    private TextView txtLose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtLose=findViewById(R.id.txtLose);
        btnReplay=findViewById(R.id.btnReplay);
        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultLoseActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}