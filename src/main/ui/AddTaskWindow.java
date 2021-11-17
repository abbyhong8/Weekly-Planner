package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class AddTaskWindow extends JFrame implements ActionListener {
    private JsonWriter jsonWriter;
    JTextField newTask;
    WeekGui weekGui;
    Day day;
    Week week;
    private static final String ADD_TASK = "ADD_TASK";

    public AddTaskWindow(WeekGui weekGui, Week week,Day day) {
        super("Add a Task");
        this.weekGui = weekGui;
        this.week = week;
        this.day = day;
        setSize(500,300);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        this.setAddButtions();
        setVisible(true);


    }

    //set the buttons for add task
    private void setAddButtions() {
        JLabel addTaskName = new JLabel("new Task:");
        addTaskName.setBounds(180,100,100,30);
        add(addTaskName);
        addTaskName.setForeground(Color.black);

        newTask = new JTextField(80);
        newTask.setBounds(185,110,400,30);
        add(newTask);


        JButton addButton = new JButton("Add the Task");
        addButton.setBounds(250,250,50,20);
        add(addButton);
        addButton.setActionCommand(ADD_TASK);
        addButton.addActionListener(this);
        addButton.setForeground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(ADD_TASK)) {
            String task = newTask.getText();
            Task newtask = new Task(task);
            day.addTask(newtask);
            try {
                jsonWriter.open();
                jsonWriter.write(week);
                jsonWriter.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

        }

    }
}
