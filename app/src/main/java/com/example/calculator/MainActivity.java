/*
 * Name : Hamid Abdolrahimi
 * ID# : 145059192
 * Section : MAP524NSA
 * I have modified this file after the recording of the video. Modification is related to validation
 * part in order to check the result to be in the range of Integer type. Therefore, there is a little
 * differences between Calculator.java file in the video and submitted file
 * */

package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
 ArrayList<Integer> calculator_btns; // operator and operand buttons
Calculator cal;
TextView text;
LinearLayout advance;
Button btn_1;
Button btn_2;
Button btn_3;
Button btn_4;
Button btn_5;
Button btn_6;
Button btn_7;
Button btn_8;
Button btn_9;
Button btn_0;
Button btn_clear;
Button btn_sum;
Button btn_minus;
Button btn_multiply;
Button btn_division;
Button btn_mode;
Button btn_reminder;
Button btn_pow;
Button btn_max;
Button btn_min;
Button btn_equal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       cal = new Calculator();
       calculator_btns = new ArrayList<Integer>();
       advance = findViewById(R.id.advance_layout);
       advance.setVisibility(View.INVISIBLE);
       text = findViewById(R.id.result);
         btn_1 = findViewById(R.id.btn1);
         calculator_btns.add(R.id.btn1);
         btn_2 = findViewById(R.id.btn2);
        calculator_btns.add(R.id.btn2);
         btn_3 = findViewById(R.id.btn3);
        calculator_btns.add(R.id.btn3);
         btn_4 = findViewById(R.id.btn4);
        calculator_btns.add(R.id.btn4);
         btn_5 = findViewById(R.id.btn5);
        calculator_btns.add(R.id.btn5);
         btn_6 = findViewById(R.id.btn6);
        calculator_btns.add(R.id.btn6);
         btn_7 = findViewById(R.id.btn7);
        calculator_btns.add(R.id.btn7);
         btn_8 = findViewById(R.id.btn8);
        calculator_btns.add(R.id.btn8);
         btn_9 = findViewById(R.id.btn9);
        calculator_btns.add(R.id.btn9);
         btn_0 = findViewById(R.id.btn0);
        calculator_btns.add(R.id.btn0);
         btn_clear = findViewById(R.id.btnClear);
         btn_sum = findViewById(R.id.btnSum);
        calculator_btns.add(R.id.btnSum);
         btn_minus = findViewById(R.id.btnMinus);
        calculator_btns.add(R.id.btnMinus);
         btn_multiply = findViewById(R.id.btnMultiply);
        calculator_btns.add(R.id.btnMultiply);
         btn_division = findViewById(R.id.btnDivision);
        calculator_btns.add(R.id.btnDivision);
         btn_mode = findViewById(R.id.mode_btn);
         btn_reminder = findViewById(R.id.btnReminder);
        calculator_btns.add(R.id.btnReminder);
         btn_pow = findViewById(R.id.btnPow);
        calculator_btns.add(R.id.btnPow);
         btn_max = findViewById(R.id.btnMax);
        calculator_btns.add(R.id.btnMax);
         btn_min = findViewById(R.id.btnMin);
        calculator_btns.add(R.id.btnMin);
        btn_equal = findViewById(R.id.btn_equal);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_mode.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_sum.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_division.setOnClickListener(this);
        btn_reminder.setOnClickListener(this);
        btn_pow.setOnClickListener(this);
        btn_max.setOnClickListener(this);
        btn_min.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       int id = view.getId();
        Button btn = (Button) view;
       if(calculator_btns.contains(id))
       {
           if(cal.elements.isEmpty() && btn.getText().toString().equals("-"))
           {
               cal.push("0");
               cal.push(btn.getText().toString());
           }
           else
               cal.push(btn.getText().toString());
           text.setText(cal.elementsToString());
       }else if(id == R.id.mode_btn)
       {
           cal.calculator_mode = (cal.calculator_mode == 0 ? 1 : 0);
           if(cal.calculator_mode == 0)
           {
             btn.setText("ADVANCE");
             advance.setVisibility(View.INVISIBLE);
           }
           else
           {
               btn.setText("STANDARD");
               advance.setVisibility(View.VISIBLE);
           }
       }
       else if(id == R.id.btnClear)
       {
           cal.deleteFromElements();
           text.setText(cal.elementsToString());
       }else
       {
           int res;
           try {
               res = cal.calculate();
               text.setText(Integer.toString(res));
           }catch(ArithmeticException e)
           {
               text.setText(e.getMessage());
           }
       }

    }
}