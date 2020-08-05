package com.kvolkov.androidlectures;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    Button calcBtn = null;
    Button btn1 = null;
    Button btn2 = null;
    Button btn3 = null;
    Button btn4 = null;
    Button btn5 = null;
    Button btn6 = null;
    Button btn7 = null;
    Button btn8 = null;
    Button btn9 = null;
    Button btn0 = null;
    Button btnminus = null;
    Button btnplus = null;
    TextView operationTv = null;
    TextView resultTv = null;

    View.OnClickListener mBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String value = "";
            switch (view.getId()) {
                case R.id.btn_1: {
                    value = "1";
                    break;
                }
                case R.id.btn_2: {
                    value = "2";
                    break;
                }
                case R.id.btn_3: {
                    value = "3";
                    break;
                }
                case R.id.btn_4: {
                    value = "4";
                    break;
                }
                case R.id.btn_5: {
                    value = "5";
                    break;
                }
                case R.id.btn_6: {
                    value = "6";
                    break;
                }
                case R.id.btn_7: {
                    value = "7";
                    break;
                }
                case R.id.btn_8: {
                    value = "8";
                    break;
                }
                case R.id.btn_9: {
                    value = "9";
                    break;
                }
                case R.id.btn_0: {
                    value = "0";
                    break;
                }
                case R.id.btn_plus: {
                    value = " + ";
                    break;
                }
                case R.id.btn_minus: {
                    value = " - ";
                    break;
                }
            }

            operationTv.setText(operationTv.getText() + value);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calcBtn = (Button) findViewById(R.id.calcBtn);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        btn6 = (Button) findViewById(R.id.btn_6);
        btn7 = (Button) findViewById(R.id.btn_7);
        btn8 = (Button) findViewById(R.id.btn_8);
        btn9 = (Button) findViewById(R.id.btn_9);
        btn0 = (Button) findViewById(R.id.btn_0);
        btnplus = (Button) findViewById(R.id.btn_plus);
        btnminus = (Button) findViewById(R.id.btn_minus);

        operationTv = (TextView) findViewById(R.id.operation);
        resultTv = (TextView) findViewById(R.id.result);

        calcBtn.setOnClickListener(mBtnClickListener);
        btn1.setOnClickListener(mBtnClickListener);
        btn2.setOnClickListener(mBtnClickListener);
        btn3.setOnClickListener(mBtnClickListener);
        btn4.setOnClickListener(mBtnClickListener);
        btn5.setOnClickListener(mBtnClickListener);
        btn6.setOnClickListener(mBtnClickListener);
        btn7.setOnClickListener(mBtnClickListener);
        btn8.setOnClickListener(mBtnClickListener);
        btn9.setOnClickListener(mBtnClickListener);
        btn0.setOnClickListener(mBtnClickListener);
        btnplus.setOnClickListener(mBtnClickListener);
        btnminus.setOnClickListener(mBtnClickListener);

        if (savedInstanceState != null) {
            // restore UI
            String value = savedInstanceState.getString("key", "");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("key", "value");
    }

}
