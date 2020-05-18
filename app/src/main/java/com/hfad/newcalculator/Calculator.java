package com.hfad.newcalculator;

import android.util.Log;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Stack;

public class Calculator {
    private List<Object> expression;
    Calculator(List<Object> operands){
        this.expression = operands;
    }
    public String calculate(){
        Stack<BigDecimal> numberOutput = new Stack<>();
        while(!expression.isEmpty()){
            if(expression.get(0) instanceof BigDecimal){
                numberOutput.push((BigDecimal) expression.get(0));
                expression.remove(0);
            }else{
                BigDecimal number1 = numberOutput.peek();
                numberOutput.pop();
                BigDecimal number2 = numberOutput.peek();
                numberOutput.pop();
                if(expression.get(0).equals('+'))
                    numberOutput.push(number2.add(number1).round(MathContext.DECIMAL32));
                else if(expression.get(0).equals('-'))
                    numberOutput.push(number2.subtract(number1).round(MathContext.DECIMAL32));
                else if(expression.get(0).equals('*'))
                    numberOutput.push(number2.multiply(number1).round(MathContext.DECIMAL32));
                else if(expression.get(0).equals('/')){
                    if(number1.equals(new BigDecimal( 0))){
                        return "error";
                    }
                    numberOutput.push(number2.divide(number1, MathContext.DECIMAL32));
                }
                expression.remove(0);
            }
        }
        return numberOutput.get(0).toString();
    }
}
