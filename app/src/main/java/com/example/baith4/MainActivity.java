package com.example.baith4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private ArrayList<String> randomNumbers;
    private Button btnStart;
    private TextView txtTime;
    private long startTime = 0L;
    private Handler handler = new Handler();
    private int wronganswer=0,trueanswer=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //// Button start
        btnStart=findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        wronganswer=0;
        trueanswer=0;
        //// TXT Time
        txtTime=findViewById(R.id.txtTime);
        if (startTime == 0L) {
            startTime = SystemClock.uptimeMillis();
            handler.postDelayed(updateTimer, 0);
        } else {
            startTime += (SystemClock.uptimeMillis() - startTime);
            handler.postDelayed(updateTimer, 0);
        }
        //// Tạo gridview vàddoor dữ liệu
        gridView = findViewById(R.id.gridview);
        randomNumbers = generateRandomNumbers(16);///số lượng số random
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, randomNumbers);
        gridView.setAdapter(adapter);
        //// Click vào ô
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer selectedNumber = Integer.valueOf(randomNumbers.get(position));
                boolean isSmallest = checkIfSmallest(selectedNumber);
                ////Check Min
                if (isSmallest) {
                    trueanswer+=1;///Kiểm tra số câu đúng
                    randomNumbers.set(position,"");
                    adapter.notifyDataSetChanged();//// Update data
                    if (trueanswer==randomNumbers.size()){
                        Intent intent = new Intent(MainActivity.this, ResultWinActivity.class);
                        intent.putExtra("mykey",txtTime.getText());
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Bạn chọn sai", Toast.LENGTH_SHORT).show();
                    wronganswer+=1;
                     if (wronganswer==3){
                     Intent intent= new Intent(MainActivity.this, ResultLoseActivity.class);
                     startActivity(intent);
                        }
                }
            }
        });
    }
    });
    }
    ///// Time Running
    private Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            long elapsedTime = SystemClock.uptimeMillis() - startTime;
            int minutes = (int) (elapsedTime % 3600000) / 60000;
            int seconds = (int) (elapsedTime % 60000) / 1000;
            String timeString = String.format("%02d:%02d", minutes, seconds);
            txtTime.setText(timeString);
            handler.postDelayed(this, 1000);
        }
    };
    ///Tạo arraylist số Random
    private ArrayList<String> generateRandomNumbers(int count) {
        ArrayList<String> numbers = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            // Tạo số ngẫu nhiên trong khoảng từ 1 đến 100
            int randomNumber = rand.nextInt(100) + 1;
           if (numbers.contains(String.valueOf(randomNumber))) i-=1; else numbers.add(randomNumber+"");
        }
        return numbers;
    }
    private boolean checkIfSmallest(int selectedNumber) {
        int min = 100;
        for (String number : randomNumbers) {
            if (number!="")
            if (Integer.valueOf(number) < min) {
                min = Integer.valueOf(number);
            }
        }
        return selectedNumber == min;
    }
}