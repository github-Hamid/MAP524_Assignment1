package com.example.calculator;




import android.util.Log;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Calculator {
    public ArrayList<String> elements;
    public ArrayList<String> operators;
    public int calculator_mode; //0 for standard and 1 for advance
    //Constructor
    public Calculator() {

        elements = new ArrayList<String>();
        operators = new ArrayList<String>(){{add("+");add("-");add("*");add("/");add("%");
        add("POW");add("MAX");add("MIN");}};
        calculator_mode = 0;
    }


    //add operation or operator to elements
    public void push(String str) {
        elements.add(str);
    }


    //delete last operation or operator from elements
    public void deleteFromElements() {

        if (!elements.isEmpty())
        {
            elements.remove(elements.size() - 1);
            if(elements.size() == 1 && elements.get(0).equals("0") )
                elements.clear();
        }

    }

    // convert elements to string
    public String elementsToString() {
        String text = "";
        if(!elements.isEmpty())
        {
            for (String i : elements) {
                text += i + " ";
            }
        }

        return text;
    }

    // calculate the result
    public int calculate() throws ArithmeticException {
        int result = 0, firstOperand, secondOperand;
        String operation;
        if(this.isValid())
        {

            while(elements.size() >= 3)
            {
                firstOperand =  Integer.parseInt(elements.get(0));
                operation = elements.get(1);
                secondOperand = Integer.parseInt(elements.get(2));
                switch (operation)
                {
                    case "+" : {
                        if(firstOperand + secondOperand > Integer.MAX_VALUE || firstOperand + secondOperand < Integer.MIN_VALUE)
                        {
                            elements.clear();
                            throw new ArithmeticException("Error result is out of range!");
                        }
                        result = firstOperand + secondOperand; break;
                    }
                    case "*" : {
                        if(firstOperand * secondOperand > Integer.MAX_VALUE || firstOperand * secondOperand < Integer.MIN_VALUE)
                        {
                            elements.clear();
                            throw new ArithmeticException("Error result is out of range!");
                        }
                        result = firstOperand * secondOperand; break;
                    }
                    case "-" : {
                        if(firstOperand - secondOperand > Integer.MAX_VALUE || firstOperand - secondOperand < Integer.MIN_VALUE)
                        {
                            elements.clear();
                            throw new ArithmeticException("Error result is out of range!");
                        }
                        result = firstOperand - secondOperand; break;
                    }
                    case "/" : {
                        if(secondOperand == 0)
                        {
                            elements.clear();
                            throw new ArithmeticException("Error!, second operand in division" +
                                    " cannot be zero");
                        }
                        result = firstOperand / secondOperand; break;
                    }
                    case "%" : {result = firstOperand % secondOperand;break;}
                    case "POW" : {
                        if(Math.pow(firstOperand, secondOperand) > Integer.MAX_VALUE || Math.pow(firstOperand, secondOperand) < Integer.MIN_VALUE)
                        {
                            elements.clear();
                            throw new ArithmeticException("Error result is out of range!");
                        }
                        result = (int) Math.pow(firstOperand, secondOperand);break;
                    }
                    case "MAX" : {result = Math.max(firstOperand, secondOperand);break;}
                    case "MIN" : {result = Math.min(firstOperand, secondOperand); break;}
                }
                elements.remove(0);
                elements.remove(0);
                elements.remove(0);
                elements.add(0, Integer.toString(result));

            }
        }
        elements.clear();
        return result;
    }

    // check if elements are valid
    public boolean isValid() throws ArithmeticException {
        boolean status = true;
        if(!elements.isEmpty() && elements.size() >= 3)
        {

            String[] arr = new String[elements.size()];
            arr =  this.elements.toArray(arr);
            for (int i = 0; i < arr.length; i++) {
                if (i % 2 == 0) {
                    status = Pattern.matches("[0-9]",arr[i]);
                    if(!status)
                    {
                        elements.clear();
                        throw new ArithmeticException("Error! Not an operation");
                    }

                } else {
                    status = operators.contains(arr[i]);
                    if(!status)
                    {
                        elements.clear();
                        throw new ArithmeticException("Error! Not an operation");
                    }

                }
            }
        }
        else
        {
            if(elements.isEmpty())
            {
                throw new ArithmeticException("Error! no input is entered.");
            }
            else {
                elements.clear();
                throw new ArithmeticException("Error! At least one operator and two operands " +
                        "are required.");
            }
        }
        return status;

    }
}
