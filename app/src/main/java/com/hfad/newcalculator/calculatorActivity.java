package com.hfad.newcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class calculatorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

    }
    public void onClickAdd(View view){
        Button button = (Button) view;
        TextView expressionLine = findViewById(R.id.expression);
        String expression = expressionLine.getText().toString();
        Tester tester = new Tester(expression, button.getText().toString());
        String newExpression = tester.test();
        expressionLine.setText(newExpression);
    }
    public void onClickErase(View view){
        TextView expressionLine = findViewById(R.id.expression);
        if (expressionLine.getText().toString().isEmpty()){
            expressionLine.setText("");
            return;
        }
        StringBuilder newExpression = new StringBuilder(expressionLine.getText());
        newExpression.deleteCharAt(newExpression.length()-1);
        expressionLine.setText(newExpression);
    }
    public void onClickClear(View view){
        TextView expressionLine = findViewById(R.id.expression);
        expressionLine.setText("");
    }
    public void onClickCalculate(View view){
        TextView expressionLine = findViewById(R.id.expression);
        String expression = expressionLine.getText().toString();
        Tester tester = new Tester(expression);
        if(!tester.checkParentheses() || !tester.checkExpression()){
            expressionLine.setText(expression);
            return;
        }
        Parser parser = new Parser(expression);
        PolishNotation polishNotation = new PolishNotation(parser.parse());
        Calculator calculator = new Calculator(polishNotation.toPolishNotation());
        expressionLine.setText(calculator.calculate());
    }
}
