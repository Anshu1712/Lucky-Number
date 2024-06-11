package com.example.luckynumber;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Result extends AppCompatActivity {

    TextView textView, result;
    ImageButton imageButton;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);
        result = findViewById(R.id.result);
        imageButton = findViewById(R.id.imageButton);


        //get data from previous activity
        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");
        textView.setText(userName + " your lucky number is :");
        //Generating Random Number
        int randomNum = generateRandomNumber();
        result.setText("" + randomNum);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName, randomNum);
            }
        });
    }

    public int generateRandomNumber() {
        Random random = new Random();
        int upper_limit = 1000;
        return random.nextInt(upper_limit);
    }

    public void shareData(String userName, int randomNum) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, userName + "Got Lucky Today");
        intent.putExtra(Intent.EXTRA_TEXT, userName + " Your lucky number is " + randomNum);
        startActivity(Intent.createChooser(intent, "Share Using"));
    }
}