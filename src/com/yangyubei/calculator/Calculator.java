package com.yangyubei.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

public class Calculator {
    // myframe: the main window of program.
    JFrame myframe = new JFrame("Calculator");

    // set initial value to make project safe
    String num1 = "";
    String num2 = "";
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

    class Listner_int implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get event source, and get entered data.
            String ss = ((JButton) e.getSource()).getText();

            // read stored operand and add to vt
            store = (JButton) e.getSource();
            vt.add(store);

            if (s1_write_dir ==1 ){
                if (s3_clear_num1==1){
                    num1 = "";
                    // recover s5
                    s5_control_dot = 1;
                }
                num1 = num1 +ss;
                s3_clear_num1 +=1;
                //display result
                result_TextField.setText(num1);

            } else if (s1_write_dir==2){
                if (s4_clear_num2==1){
                    num2 = "";
                    // recover s5
                    s5_control_dot = 1;
                }
                num2 = num2 +ss;
                s4_clear_num2 +=1;
                //display result
                result_TextField.setText(num2);
            }

        }
    }

    class Listner_decimal implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            store = (JButton) e.getSource();
            vt.add(store);

            if (s5_control_dot==1){
                String ss2 = ((JButton) e.getSource()).getText();

                if (s1_write_dir ==1 ){
                    if (s3_clear_num1==1){
                        num1 = "";
                        // recover s5
                        s5_control_dot = 1;
                    }
                    num1 = num1 +ss2;
                    s3_clear_num1 +=1;
                    //display result
                    result_TextField.setText(num1);

                } else if (s1_write_dir==2){
                    if (s4_clear_num2==1){
                        num2 = "";
                        // recover s5
                        s5_control_dot = 1;
                    }
                    num2 = num2 +ss2;
                    s4_clear_num2 +=1;
                    //display result
                    result_TextField.setText(num2);
                }
            }
        }
    }

    class Listner_operand implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String ss2 = ((JButton) e.getSource()).getText();
            store = (JButton) e.getSource();
            vt.add(store);

            if (s2_num_operand == 1){
                s1_write_dir = 2;
                s5_control_dot = 1;
                operand = ss2;
                s2_num_operand += 1;
            } else{
                int a = vt.size();
                JButton c = (JButton) vt.get(a-2);
                if (!(c.getText().equals("+"))
                        && !(c.getText().equals("-"))
                        && !(c.getText().equals("*"))
                        && !(c.getText().equals("/")))
                {
                    cal();
                    num1 = result;
                    s1_write_dir = 1;
                    s5_control_dot = 1;
                    s4_clear_num2 = 1;
                    operand = ss2;
                }
                s2_num_operand +=1;
            }
        }
    }

    class Listner_result implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            store = (JButton) e.getSource();
            vt.add(store);
            cal();

            s1_write_dir = 1;
            s2_num_operand = 1;
            s3_clear_num1 = 1;
            s4_clear_num2 = 1;

            num1 = result;
        }
    }

    class Listner_clear implements ActionListener{

        @SuppressWarnings("unchcked")
        public void actionPerformed(ActionEvent e) {
            store = (JButton) e.getSource();
            vt.add(store);

            s1_write_dir = 1;
            s2_num_operand = 1;
            s3_clear_num1 = 1;
            s4_clear_num2 = 1;
            s5_control_dot = 1;

            num1 = "";
            num2 = "";
            operand = "+";
            result = "";
            result_TextField.setText(result);
            vt.clear();
        }
    }

    public void cal(){
        double a2;
        double b2;

        String op = operand;
        if (op.equals("")){
            result_TextField.setText("Please Input Operators");
        }else {
            if (num1.equals("."))
                num1 = "0.0";
            if (num2.equals("."))
                num2 = "0.0";

            a2 = Double.valueOf(num1).doubleValue();
            b2 = Double.valueOf(num2).doubleValue();

            double result2 = 0;

            if (op.equals("+")) {
                result2 = a2 + b2;
            }
            if (op.equals("-")) {
                result2 = a2 - b2;
            }
            if (op.equals("*")) {
                BigDecimal m1 = new BigDecimal(Double.toString(a2));
                BigDecimal m2 = new BigDecimal(Double.toString(b2));
                result2 = m1.multiply(m2).doubleValue();
            }
            if (op.equals("/")){
                if (b2 == 0) {
                    result2 = 0;
                } else {
                    result2 = a2 / b2;
                }
            }

            result = ((new Double(result2)).toString());
            result_TextField.setText(result);
        }
    }

    public Calculator(){
        // set where this window appears in the screen
        myframe.setLocation(300, 200);
        // set window cannot be resize
        myframe.setResizable(true);

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

        Listner_result lr = new Listner_result();
        b_result.addActionListener(lr);

        Listner_int li = new Listner_int();
        b0.addActionListener(li);
        b1.addActionListener(li);
        b2.addActionListener(li);
        b3.addActionListener(li);
        b4.addActionListener(li);
        b5.addActionListener(li);
        b6.addActionListener(li);
        b7.addActionListener(li);
        b8.addActionListener(li);
        b9.addActionListener(li);

        Listner_operand lo = new Listner_operand();
        b_add.addActionListener(lo);
        b_minus.addActionListener(lo);
        b_multi.addActionListener(lo);
        b_divide.addActionListener(lo);

        Listner_clear lc = new Listner_clear();
        clear_Button.addActionListener(lc);

        Listner_decimal ld = new Listner_decimal();
        b_dot.addActionListener(ld);

        myframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }
}




