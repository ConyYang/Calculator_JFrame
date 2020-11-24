package com.yangyubei.calculator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Calculator {
    // myframe: the main window of program.
    JFrame myframe = new JFrame("Calculator");

    // set initial value to make project safe
    String num1 = "0";
    String num2 = "0";
    String operand = "+";
    String result = "";

    // set state switch
    int s1_write_dir = 1; //1: num1 ->num2; 2: num2 -> num1
    int s2_num_operand = 1; //1: one time operand; >1: multi-times operand
    int s3_clear_num1 = 1; //1: num1 can be clear; !=1: num1 cannot be clear
    int s4_clear_num2 = 1; //1: num2 can be clear; !=1: num2 cannot be clear
    int s5_control_dot = 1; //1: can enter dot; !=1: Ignore the entered dot

    // Set store as a cache, record if continue press operand key
    JButton store;

    // save previous entered operands
    @SuppressWarnings("rawtypes")
    Vector vt = new Vector(20,10);

    // result_TextField: textfield to display operation and cal result
    JTextField result_TextField = new JTextField(result, 20);

    // Clear Button
    JButton clear_Button = new JButton("Clear");

    // Number Button
    JButton b0 = new JButton("0");
    JButton b1 = new JButton("1");
    JButton b2 = new JButton("2");
    JButton b3 = new JButton("3");
    JButton b4 = new JButton("4");
    JButton b5 = new JButton("5");
    JButton b6 = new JButton("6");
    JButton b7 = new JButton("7");
    JButton b8 = new JButton("8");
    JButton b9 = new JButton("9");

    // Operation Button
    JButton b_dot = new JButton(".");
    JButton b_add = new JButton("+");
    JButton b_minus = new JButton("-");
    JButton b_multi = new JButton("*");
    JButton b_divide = new JButton("/");

    // result button
    JButton b_result = new JButton("=");

    // Create JPanel
    JPanel pan1 = new JPanel();
    JPanel pan2 = new JPanel();
    
    public Calculator(){
        // set where this window appears in the screen
        myframe.setLocation(300, 200);
        // set window cannot be resize
        myframe.setResizable(false);

        // set pan1 layout
        pan1.setLayout(new GridLayout(4, 4, 5, 5));

        // set pan1 buttons
        pan1.add(b7);
        pan1.add(b8);
        pan1.add(b9);
        pan1.add(b_divide);

        pan1.add(b4);
        pan1.add(b5);
        pan1.add(b6);
        pan1.add(b_multi);

        pan1.add(b1);
        pan1.add(b2);
        pan1.add(b3);
        pan1.add(b_minus);

        pan1.add(b0);
        pan1.add(b_dot);
        pan1.add(b_result);
        pan1.add(b_add);

        // set pan1 border
        pan1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // set pan2 layout
        pan2.setLayout(new BorderLayout());
        pan2.add(result_TextField, BorderLayout.WEST);
        pan2.add(clear_Button, BorderLayout.EAST);

        // Add pan1 and pan2 to JPanel
        // frame.getContentPane() will return the default JPanel in JFrame
        myframe.getContentPane().setLayout(new BorderLayout());
        myframe.getContentPane().add(pan1, BorderLayout.CENTER);
        myframe.getContentPane().add(pan2, BorderLayout.NORTH);

        myframe.pack();
        myframe.setVisible(true);

    }
}


