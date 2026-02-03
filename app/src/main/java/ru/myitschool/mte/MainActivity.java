package ru.myitschool.mte;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.myitschool.mte.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable timer;
    private int counter = 1;
    private boolean isRunning = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        timer = new Runnable() {
            @Override
            public void run() {
                if (counter == 3) {
                    counter = 1;
                } else {
                    counter++;
                }

                binding.numIteration.setText(String.valueOf(counter));

                if (isRunning) {
                    handler.postDelayed(this, 1000);
                }

            }
        };

        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();
            }
        });

        binding.buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer();
            }
        });


    }

    private void startTimer() {
        if (isRunning) {
            return;
        }

        isRunning = true;
        counter = 1;
        binding.numIteration.setText(String.valueOf(counter));
        handler.postDelayed(timer, 1000);
    }

    private void stopTimer() {
        isRunning = false;
        handler.removeCallbacks(timer);
    }
}

