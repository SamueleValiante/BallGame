package com.example.BallGame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    private CircleView circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        circleView = findViewById(R.id.circleView);

        circleView.piazzaOstacoli(6);

        Button left = findViewById(R.id.left);
        Button right = findViewById(R.id.right);
        Button up = findViewById(R.id.top);
        Button down = findViewById(R.id.bottom);
        Button reset = findViewById(R.id.reset);

        left.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                circleView.move(-20, 0);
            }
        });

        right.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                circleView.move(20, 0);
            }
        });

        up.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                circleView.move(0, -20);
            }
        });

        down.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                circleView.move(0, 20);
            }
        });

        reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                circleView.reset();
            }
        });
    }
}