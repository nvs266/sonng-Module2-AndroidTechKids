package com.example.sonnv.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvCurrent;
    TextView tvLast;

    boolean isDouble;
    boolean isCalculated;

    Button btLastOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCurrent = (TextView) findViewById(R.id.tv_result);
        tvLast = (TextView) findViewById(R.id.tv_beforeResult);
        init();
    }

    private void init() {
        tvCurrent.setText("0");
        tvLast.setText("0");
        isCalculated = true;
        if (btLastOperator != null) {
            btLastOperator.setBackgroundResource(R.drawable.custom_button);
            btLastOperator = null;
        }
        isDouble = false;
    }

    @Override
    public void onClick(View view) {
        Button button = null;
        try {
            button = (Button) view;
        } catch (Exception ex) {
        }

        if (button != null) {
            switch (view.getId()) {
                case R.id.bt_del:
                    init();
                    break;
                case R.id.bt_Number:
                    if (isCalculated) {
                        tvCurrent.setText("");
                    }
                    if (tvCurrent.getText().toString().length() <= 16) {
                        tvCurrent.setText(tvCurrent.getText() + button.getText().toString());
                    }
                    isCalculated = false;
                    break;
                case R.id.bt_dot:
                    if ((!isDouble && !isCalculated) || tvCurrent.getText().toString().equals("0")) {
                        tvCurrent.setText(tvCurrent.getText() + ".");
                        isCalculated = false;
                        isDouble = true;
                    }
                    break;
                case R.id.bt_operator:
                    if (button.getText().toString().equals("-")) {
                        if (tvCurrent.getText().toString().equals("-")) break;
                        else if (Double.parseDouble(tvCurrent.getText().toString()) == 0) {
                            tvCurrent.setText("-");
                            isCalculated = false;
                            break;
                        }
                    }
                    if (btLastOperator != null) {
                        String result = fmt(calculate());
                        tvLast.setText("" + result);
                        btLastOperator.setBackgroundResource(R.drawable.custom_button);
                    } else {
                        tvLast.setText(tvCurrent.getText());
                     }
                    btLastOperator = button;
                    btLastOperator.setBackgroundResource(R.drawable.custom_pressed_button);
                    isCalculated = true;
                    tvCurrent.setText("0");
                    isDouble = false;
                    break;
                case R.id.bt_equal:
                    if (btLastOperator != null) {
                        String result = fmt(calculate());
                        tvCurrent.setScrollBarSize(20);
                        tvLast.setText(tvCurrent.getText());
                        tvCurrent.setText(result);
                        btLastOperator.setBackgroundResource(R.drawable.custom_button);
                        btLastOperator = null;
                        isDouble = false;
                        isCalculated = true;
                    }
                    break;
                case R.id.bt_back:
                    break;
                default:
                    break;
            }
        } else {
            if (view.getId() == R.id.bt_back && !isCalculated) {
                if (tvCurrent.getText().toString().length() > 1) {
                    tvCurrent.setText(tvCurrent.getText().subSequence(0, tvCurrent.getText().length() - 1));
                } else if (tvCurrent.getText().toString().length() == 1) {
                    tvCurrent.setText("0");
                    isCalculated = true;
                }
            }
        }
    }

    private double calculate() {
        double numOne = Double.parseDouble(tvLast.getText().toString());
        double numTwo = Double.parseDouble(tvCurrent.getText().toString());

        switch (btLastOperator.getText().toString()) {
            case "+":
                return (numOne + numTwo);
            case "-":
                return (numOne - numTwo);
            case "x":
                return (numOne * numTwo);
            case "/":
                if (numTwo == 0) {
                    init();
                    return 0;
                }
                return (numOne / numTwo);
            default:
                return 0;
        }
    }

    private String fmt(double d) {
        if (d == (long) d) {
            return String.format("%d", (long) d);
        } else {
            int t = String.format("%s", d).indexOf('E');
            if (t != -1) {
                String result = String.format("%s", d);
                String s = "";
                for (int i = t; i < result.length(); i++) {
                    s += result.charAt(i);
                }
                int j = 16 - s.length();
                if (j >= t) j = t - 1;
                s = result.substring(0, j) + s;
                return s;
            }
            return String.format("%s", d);
        }
    }
}
