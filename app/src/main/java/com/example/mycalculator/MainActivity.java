package com.example.mycalculator;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn_clear,
            btn_plus, btn_minus, btn_equal, btn_mul, btn_div, btn_dot;
    TextView text_display;

    // This is to evaluate the math expression

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_dot = (Button) findViewById(R.id.btn_dot);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        text_display = (TextView) findViewById(R.id.textview_input_display);

        setClickListeners();
    }

    private void setClickListeners() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                addNumber("0");
                break;
            case R.id.btn1:
                addNumber("1");
                break;
            case R.id.btn2:
                addNumber("2");
                break;
            case R.id.btn3:
                addNumber("3");
                break;
            case R.id.btn4:
                addNumber("4");
                break;
            case R.id.btn5:
                addNumber("5");
                break;
            case R.id.btn6:
                addNumber("6");
                break;
            case R.id.btn7:
                addNumber("7");
                break;
            case R.id.btn8:
                addNumber("8");
                break;
            case R.id.btn9:
                addNumber("9");
                break;
            case R.id.btn_plus:
                addNumber("+");
                break;
            case R.id.btn_minus:
                addNumber("-");
                break;
            case R.id.btn_mul:
                addNumber("*");
                break;
            case R.id.btn_div:
                addNumber("/");
                break;
            case R.id.btn_dot:
                addNumber(".");
                break;
            case R.id.btn_equal:
                String result = null;
                try {
                    result = evaluate(text_display.getText().toString());
                    text_display.setText(result);
                } catch (Exception e) {
                    text_display.setText("Error");
                }
                break;
            case R.id.btn_clear:
                clear_display();
                break;
        }
    }

    private String evaluate(String text) throws Exception {
        Character op = '+';
        text = text + "+";
        System.out.println("text " + text);
        StringBuilder n = new StringBuilder();
        BigDecimal result = BigDecimal.ZERO;
        Stack<BigDecimal> numbers = new Stack<>();
        for (int i = 0; i < text.length(); i++) {
            Character item = text.charAt(i);
            System.out.println("char at "+ i + ": " + item);
            if (Character.isDigit(item) || item == '.') {
                n = n.append(item);
                continue;
            }

            // To account for negative operations (e.g "5*-2", 5/-2)
            if (i < text.length() - 1 && (!Character.isDigit(text.charAt(i-1)) || i == 0)) {
                if (item == '-') {
                    n = n.append(item);
                    continue;
                }
            }

            if (!Character.isDigit(item)) {
                BigDecimal num = BigDecimal.ZERO;
                if (n.length() > 0) {
                    num = new BigDecimal(n.toString());
                    System.out.println("num " + num);
                    n.setLength(0);
                }

                switch (op) {
                    case '+':
                        numbers.push(num);
                        break;
                    case '-':
                        numbers.push(num.negate());
                        break;
                    case '*':
                        numbers.push(num.multiply((BigDecimal) numbers.pop()));
                        break;
                    case '/':
                        if (num == BigDecimal.ZERO) {
                            throw new ArithmeticException("Division by zero");
                        }
                        BigDecimal first = (BigDecimal) numbers.pop();
                        numbers.push(first.divide(num, 10, RoundingMode.HALF_UP));
                }
                op = item;
            }
        }

        for (BigDecimal no : numbers) {
            result = result.add(no);
        }
        return result.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    private void addNumber(String number) {
        text_display.setText(text_display.getText() + number);
    }

    private void clear_display() {
        text_display.setText("");
    }
}
