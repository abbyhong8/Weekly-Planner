package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

//a pop up window for adding a task
public class AddTaskWindow extends JFrame implements ActionListener {
    protected JsonWriter jsonWriter;
    JTextField newTask;
    WeekGui weekGui;
    Day day;
    Week week;
    private WeeklyPlannerMainGui mainGui;
    private static final String ADD_TASK = "ADD_TASK";
    private static final String JSON_STORE = "./data/week.json";

    public AddTaskWindow(WeeklyPlannerMainGui mainGui, WeekGui weekGui, Week week,Day day) {
        super("Add a Task");
        this.mainGui = mainGui;
        this.weekGui = weekGui;
        this.week = week;
        this.day = day;
        setPreferredSize(new Dimension(500, 300));
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        jsonWriter = new JsonWriter(JSON_STORE);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
        this.setAddButtions();
        Color color = new Color(217,210,233);
        getContentPane().setBackground(color);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);


    }

    //set the buttons for add task
    private void setAddButtions() {
        JLabel addTaskName = new JLabel("New Task:");
        addTaskName.setBounds(40,80,100,30);
        add(addTaskName);
        addTaskName.setForeground(Color.black);

        newTask = new JTextField(80);
        newTask.setBounds(50,110,350,30);
        add(newTask);



        JButton addButton = new JButton("Add the Task");
        addButton.setBounds(200,220,100,20);
        add(addButton);
        addButton.setActionCommand(ADD_TASK);
        addButton.addActionListener(this);
        addButton.setForeground(Color.darkGray);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(ADD_TASK)) {
            String task = newTask.getText();
            Task addTask = new Task(task);
            this.day.addTask(addTask);
            weekGui.dispose();
            week.addDay(day);
            mainGui.dispose();
            new WeeklyPlannerMainGui(week);

            new WeekGui(mainGui, week, day);
            dispose();

            ;

        }

    }
}
