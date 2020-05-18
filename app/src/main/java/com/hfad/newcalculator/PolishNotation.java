package com.hfad.newcalculator;

import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolishNotation {
    private List<Object> expression;
    PolishNotation(List<Object> expression){
        this.expression = expression;
    }
    List<Object> toPolishNotation(){
        boolean unaryOperatorPossibility = true;
        boolean unaryOperator = false;
        List<Object> polishNotationExpression = new ArrayList<>();
        Stack<Object> output = new Stack<>();
        Priority priority = new Priority();
        while(!expression.isEmpty()){
            if(expression.get(0) instanceof BigDecimal){
                unaryOperatorPossibility = false;
                BigDecimal value = (BigDecimal) expression.get(0);
                if(unaryOperator){
                    value = value.multiply(BigDecimal.valueOf(-1));
                    unaryOperator = false;
                }
                polishNotationExpression.add(value);
            }else{
                Character operator = (Character) expression.get(0);
                if (operator == '-' && unaryOperatorPossibility) {
                    unaryOperator = true;
                    unaryOperatorPossibility = false;
                }
                else{
                    unaryOperatorPossibility = false;
                    if (operator == '(') unaryOperatorPossibility = true;
                    if(output.empty()){
                        output.push(operator);
                    }
                    else if (priority.getPriority(operator) == 0) {
                        while (priority.getPriority(output.peek()) != 1) {
                            polishNotationExpression.add(output.peek());
                            output.pop();
                        }
                        output.pop();
                    } else if (priority.getPriority(operator) == 1)
                        output.push(operator);
                    else if (priority.getPriority(operator) > priority.getPriority(output.peek()))
                        output.push(operator);
                    else if (priority.getPriority(operator) <= priority.getPriority(output.peek())){
                        polishNotationExpression.add(output.peek());
                        output.pop();
                        output.push(operator);
                    }
                }
            }
            expression.remove(0);
        }
        while(!output.empty()){
            polishNotationExpression.add(output.peek());
            output.pop();
        }
        return polishNotationExpression;
    }
}