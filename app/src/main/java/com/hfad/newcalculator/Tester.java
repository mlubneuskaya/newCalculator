package com.hfad.newcalculator;


import android.util.Log;

import java.util.Stack;

import static java.lang.Character.isDigit;

public class Tester {
    private StringBuilder expression;
    private Character addition;
    private char lastCharacter;
    Tester(String expression, String addition){
        this.expression = new StringBuilder(expression);
        if (expression.length() != 0){
            lastCharacter = expression.charAt(expression.length()-1);
        }
        this.addition = addition.charAt(0);
    }
    Tester(String expression){
        this.expression = new StringBuilder(expression);
    }
    public String test(){
        if(expression.length() == 0){
            if(addition == '.'){
                return expression.append("0.").toString();
            }
            if(addition != '-' && !isDigit(addition) && addition != '('){
                return expression.toString();
            }
        }
        if(!checkSymbols()){
            return addition.toString();
        }
        else if(addition == '('){
            if(lastCharacter == ')' || isDigit(lastCharacter)){
                return expression.append("*").append(addition).toString();
            }
            if(lastCharacter == '.'){
                return expression.toString();
            }
        }
        else if(addition == ')'){
            if((lastCharacter != ')' && !isDigit(lastCharacter))
                    ||checkParentheses()){
                return expression.toString();
            }
        }
        else if(addition == '.'){
            if(!checkDot()){
                return expression.toString();
            }
            if(!isDigit(lastCharacter)){
            if(lastCharacter == ')')
                return expression.append("*0.").toString();
            else return expression.append("0.").toString();
            }
        }
        else if(!isDigit(addition)){
            if(!isDigit(lastCharacter)){
                if(addition == '-' && lastCharacter == '-'){
                    return expression.toString();
                }
                if(expression.length() == 1 || lastCharacter == '('){
                    return expression.toString();
                }
                if(lastCharacter != ')'){
                    expression.deleteCharAt(expression.length()-1);
                }
            }
        }
        else if(isDigit(addition)){
            if(lastCharacter == ')'){
                return expression.append("*").append(addition).toString();
            }
        }
        expression.append(addition);
        return expression.toString();
    }
    public boolean checkExpression(){
        if(expression.length() == 0)
            return true;
        return isDigit(expression.charAt(expression.length() - 1))
                || expression.charAt(expression.length() - 1) == ')';
    }
    public boolean checkParentheses(){
        Stack<Integer> parentheses = new Stack<>();
        for(int i = 0; i < expression.length(); i++){
            if(expression.charAt(i)=='('){
                parentheses.push(1);
            }
            if(expression.charAt(i)==')'){
                parentheses.pop();
            }
        }
        return parentheses.empty();
    }
    private boolean checkSymbols(){
        for(int i = 0; i < expression.length(); i++){
            if(!isDigit(expression.charAt(i)) && expression.charAt(i) != '+'
                    && expression.charAt(i) != '-' && expression.charAt(i) != '*'
                    && expression.charAt(i) != '/' && expression.charAt(i) != '.'
                    && expression.charAt(i) != '(' && expression.charAt(i) != ')'){
                return false;
            }
        }
        return true;
    }
    private boolean checkDot(){
        int i = expression.length()-1;
        boolean correct = true;
        while(i>=0){
            if(!isDigit(expression.charAt(i))){
                if(expression.charAt(i) == '.'){
                    correct = false;
                    break;
                }
                else{
                    break;
                }
            }
            i--;
        }
        return correct;
    }
}
