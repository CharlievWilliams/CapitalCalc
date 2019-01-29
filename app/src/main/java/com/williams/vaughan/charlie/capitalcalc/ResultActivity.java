package com.williams.vaughan.charlie.capitalcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Receive String from previous activity
        Intent intent = getIntent();
        String compoundSumDisplay = intent.getStringExtra(CalculatorActivity.COMPOUND_DISPLAY);
        String calculationPeriodDisplay = intent.getStringExtra(CalculatorActivity.YEAR_DISPLAY);

        // Locate TextView and setText with the String
        TextView timeTextView = findViewById(R.id.displayTotalTimePeriodTextView);
        TextView sumTextView = findViewById(R.id.displayTotalTextView);
        timeTextView.setText(calculationPeriodDisplay);
        sumTextView.setText(compoundSumDisplay);

        configureReturnButton();
    }

    private void configureReturnButton() {
        Button splashButton = findViewById(R.id.splashButton);
        splashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
