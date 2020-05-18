package com.hfad.newcalculator;

public class Priority {
    public int getPriority(Object character){
        int priority;
        if (character.equals(')')) priority = 0;
        else if (character.equals('(')) priority = 1;
        else if (character.equals('+') || character.equals('-')) priority = 2;
        else priority = 3;
        return priority;
    }
}
