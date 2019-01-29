package com.williams.vaughan.charlie.capitalcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    // Declaration of variables
    int principalAmount, radioId, calculationPeriod, monthlyContribution;
    double annualInterestRate, compoundSum;
    EditText principalAmountInput, annualInterestRateInput, calculationPeriodInput, monthlyContributionInput;
    RadioGroup radioGroup;
    Switch aSwitch;

    // Public variable for transport via Intent
    public static final String COMPOUND_DISPLAY = "capitalcalc.compoundDisplay";
    public static final String YEAR_DISPLAY = "capitalcalc.YearDisplay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Create button and on click listener
        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Find the EditTexts and RadioGroup
                principalAmountInput = findViewById(R.id.principalAmountEditText);
                calculationPeriodInput = findViewById(R.id.calculationPeriodEditText);
                annualInterestRateInput = findViewById(R.id.annualInterestRateEditText);
                monthlyContributionInput = findViewById(R.id.depositAmountEditText);
                radioGroup = findViewById(R.id.radioGroup);
                aSwitch = findViewById(R.id.monthlyContributionActivationSwitch);

                    // Convert strings to ints / doubles and save to variables
                    principalAmount = Integer.parseInt(principalAmountInput.getText().toString());
                    calculationPeriod = Integer.parseInt(calculationPeriodInput.getText().toString());
                    annualInterestRate = Double.parseDouble(annualInterestRateInput.getText().toString());
                    monthlyContribution = Integer.parseInt(monthlyContributionInput.getText().toString());
                    radioId = radioGroup.getCheckedRadioButtonId();

                    // Convert to int and double variables
                    double actualPercentage = annualInterestRate / 100;

                    // If monthly contributions is checked
                    if (aSwitch.isChecked() && principalAmountInput != null && calculationPeriodInput != null && annualInterestRateInput != null && monthlyContributionInput != null) {
                        // Monthly contribution calculation depending on the compound frequency and contribution amount
                        if (radioId == R.id.monthlyRadioButton) // Monthly
                        {
                            compoundSum = monthlyContributionCalculation(calculationPeriod, actualPercentage, principalAmount, 12, monthlyContribution);

                            // Round to 2dp and convert back to string, then concatenate
                            String compoundSumDisplay = Double.toString(Math.round(compoundSum * 100.0) / 100.0);
                            compoundSumDisplay = "£" + compoundSumDisplay;
                            String calculationPeriodDisplay = Integer.toString(calculationPeriod);
                            calculationPeriodDisplay = "After " + calculationPeriodDisplay + " years you will have";

                            // Move to final activity and transport the String
                            Intent intent = new Intent(CalculatorActivity.this, ResultActivity.class);
                            intent.putExtra(COMPOUND_DISPLAY, compoundSumDisplay);
                            intent.putExtra(YEAR_DISPLAY, calculationPeriodDisplay);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), "Compounding interval must be monthly for regular contribution calculation", Toast.LENGTH_LONG).show();
                        }
                    }
                    // If monthly contributions is not checked
                    else if (principalAmountInput != null && calculationPeriodInput != null && annualInterestRateInput != null) {
                        // Standard calculation depending on the compound frequency
                        if (radioId == R.id.dailyRadioButton) // Daily
                        {
                            compoundSum = standardCalculation(calculationPeriod, actualPercentage, principalAmount, 365);
                        } else if (radioId == R.id.monthlyRadioButton) // Monthly
                        {
                            compoundSum = standardCalculation(calculationPeriod, actualPercentage, principalAmount, 12);
                        } else if (radioId == R.id.quarterlyRadioButton) // Quarterly
                        {
                            compoundSum = standardCalculation(calculationPeriod, actualPercentage, principalAmount, 4);
                        } else if (radioId == R.id.biannuallyRadioButton) // Biannually
                        {
                            compoundSum = standardCalculation(calculationPeriod, actualPercentage, principalAmount, 2);
                        } else if (radioId == R.id.annuallyRadioButton) // Annually
                        {
                            compoundSum = standardCalculation(calculationPeriod, actualPercentage, principalAmount, 1);
                        }

                        // Round to 2dp and convert back to string, then concatenate
                        String compoundSumDisplay = Double.toString(Math.round(compoundSum * 100.0) / 100.0);
                        compoundSumDisplay = "£" + compoundSumDisplay;
                        String calculationPeriodDisplay = Integer.toString(calculationPeriod);
                        calculationPeriodDisplay = "After " + calculationPeriodDisplay + " years you will have";

                        // Move to final activity and transport the String
                        Intent intent = new Intent(CalculatorActivity.this, ResultActivity.class);
                        intent.putExtra(COMPOUND_DISPLAY, compoundSumDisplay);
                        intent.putExtra(YEAR_DISPLAY, calculationPeriodDisplay);
                        startActivity(intent);
                    }
                    // If not everything is selected then make a toast
                    else {
                        Toast.makeText(getApplicationContext(), "Please fill in all sections",
                                Toast.LENGTH_LONG).show();
                    }


                }

        });
    }

    // Standard Calculation Function
    public static double standardCalculation(int calculationPeriod, double actualPercentage, int principalAmount, int compoundFrequency) {
        // Compound interest formula: V = P(1+r/n)^(nt)
        return principalAmount * Math.pow(1 + (actualPercentage / compoundFrequency), (compoundFrequency * calculationPeriod));
    }

    // Monthly Contribution Calculation Function
    public static double monthlyContributionCalculation(int calculationPeriod, double actualPercentage, int principalAmount, int compoundFrequency, int monthlyContribution) {
        // Future value of a series formula: PMT(((1+r/n)^(nt)-1)(r/n))
        double stepOne, stepTwo, stepThree, stepFour, stepFive, stepSix, stepSeven;

        stepOne = 1 + (actualPercentage / compoundFrequency);
        stepTwo = compoundFrequency * calculationPeriod;
        stepThree = Math.pow(stepOne, stepTwo);
        stepFour = stepThree - 1;
        stepFive = actualPercentage / compoundFrequency;
        stepSix = principalAmount * stepThree;
        stepSeven = monthlyContribution * stepFour / stepFive;
        return stepSix + stepSeven;
    }


}
