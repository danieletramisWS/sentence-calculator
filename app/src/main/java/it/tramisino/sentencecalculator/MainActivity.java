package it.tramisino.sentencecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextYears, editTextMonths;
    TextView textResult;
    Spinner spinnerIncrease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindElements();
    }

    private void bindElements() {
        editTextYears = (EditText) findViewById(R.id.editTextYears);
        editTextMonths = (EditText) findViewById(R.id.editTextMonths);
        spinnerIncrease = (Spinner) findViewById(R.id.spinnerIncrease);
        textResult = (TextView) findViewById(R.id.textResult);
    }

    public void onCalculateClick(View view) {
        try {
            String yearsStr = editTextYears.getText().toString();
            Integer years = Integer.parseInt(yearsStr);
            String monthsStr = editTextMonths.getText().toString();
            Integer months = !monthsStr.equals("") ? Integer.parseInt(monthsStr) : 0;
            String increaseStr = spinnerIncrease.getSelectedItem().toString();
            Double increase = 0D;

            if (years < 0) {
                throw new Exception(getApplicationContext().getResources().getString(R.string.invalid_years));
            }

            if (months < 0 || months > 12) {
                throw new Exception(getApplicationContext().getResources().getString(R.string.invalid_months));
            }

            switch (increaseStr) {
                case "1/2": increase = 0.5; break;
                case "1/4": increase = 0.25; break;
                case "2/3": increase = 0.6666666666;
            }

            int totalMonths = years * 12 + months;
            double result = increase * (totalMonths) + totalMonths;

            long resultYears = Math.round(Math.floor(result / 12));
            long resultMonths = Math.round(result % 12);

            textResult.setText(String.format("%s anni e %s mesi", resultYears, resultMonths));

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();
        }
    }

    public void onCancelClick(View view) {
        resetView();
    }

    private void resetView() {
        editTextYears.setText("");
        editTextMonths.setText("");
        spinnerIncrease.setSelection(0);
        textResult.setText("");
    }
}