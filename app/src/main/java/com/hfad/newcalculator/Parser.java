package com.hfad.newcalculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

import static java.lang.Character.isDigit;
import java.lang.Double;
import static java.lang.Math.pow;

public class Parser {
    private String Expression;
    Parser(String Expression){
        this.Expression = Expression;
    }
    List<Object> parse(){
        List<Object> ExpressionLine = new ArrayList<>();
        for(int i = 0; i < Expression.length(); i++){
            if(isDigit(Expression.charAt(i))){
                double number = 0;
                while(i < Expression.length() && isDigit(Expression.charAt(i))){
                    number = number * 10 + Double.parseDouble(String.valueOf(Expression.charAt(i)));
                    i++;
                }
                if (i < Expression.length() && Expression.charAt(i) == '.') {
                    i++;
                    int n = 0;
                    while (i < Expression.length() && isDigit(Expression.charAt(i))) {
                        n++;
                        number = number + Double.parseDouble(String.valueOf(Expression.charAt(i)))/pow(10, n);
                        i++;
                    }
                }
                BigDecimal value = new BigDecimal(number);
                ExpressionLine.add(value);
            }
            if(i != Expression.length()){
                Character operation = Expression.charAt(i);
                ExpressionLine.add(operation);
            }
        }
        return ExpressionLine;
    }
}
