package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;


public class WeekGui extends JFrame implements ActionListener {

    private JsonWriter jsonWriter;
    private Day day;
    private Week week;
    private DefaultTableModel taskList;
    private JTable taskTable;
    private static final String MARK_COMPLETED = "MARK_COMPLETED";
    private static final String ADD_TASK = "ADD_TASK";

    public WeekGui(Week week, Day day) {
        this.day = day;
        this.week = week;
        final String[] taskLabels = new String[] {
                "Number",
                "Name",
                "Completion"
        };
        taskList = new DefaultTableModel(null, taskLabels) {};
        taskTable = new JTable(taskList);
        this.showTasks(day);
        add(new JScrollPane(taskTable));
        setTitle("Tasks for this day");
        setSize(700,700);
        this.showButtons();
        setVisible(true);

    }

    //illustrate the tasks in the table
    private void showTasks(Day day) {
        for (int i = 0; i < day.checkNumber(); i++) {
            Task task = day.getTask(i);
            Object[] taskRow = new Object[] {
                    i + 1,
                    task.getWork(),
                    task.isComplete(),
            };
            taskList.addRow(taskRow);
        }

    }

    //show the buttons to add or mark complete for this task
    private void showButtons() {
        JButton addTaskButton = new JButton("Add a task");
        add(addTaskButton);
        addTaskButton.setActionCommand(ADD_TASK);
        addTaskButton.addActionListener(this);
        addTaskButton.setForeground(Color.blue);

        JButton markCompletedButton = new JButton("Mark this task completed");
        add(markCompletedButton);
        markCompletedButton.setActionCommand(MARK_COMPLETED);
        markCompletedButton.setBounds(300,300,50,20);
        markCompletedButton.addActionListener(this);
        markCompletedButton.setForeground(Color.blue);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(ADD_TASK)) {
            new AddTaskWindow(this, this.week, this.day);
        } else if (action.equals(MARK_COMPLETED)) {
            int i = taskTable.getSelectedRow();
            Task task = day.getTask(i);
            task.markComplete();
            taskTable.setValueAt((Object) day.getTask(i).isComplete(), i, 2);
            try {
                jsonWriter.open();
                jsonWriter.write(this.week);
                jsonWriter.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }

    }
}

//    private static final String SHOW_UNCOMPLETED_TASKS = "SHOW_UNCOMPLETED_TASKS";
//
//    public WeekGui(Week week) {
//        this.week = week;
//        JTabbedPane tabbedPane = new JTabbedPane();
//
//        initiate1(JComponent day1);
//        initiate2(JComponent day2);
//        initiate2(JComponent day2);
//        initiate2(JComponent day2);
//        initiate2(JComponent day2);
//        initiate2(JComponent day2);
//        initiate2(JComponent day2);
//
//        JComponent panel1 = showTasksInDay(week.getDays()[0]);
//        tabbedPane.addTab("Monday", icon, panel1,
//                "Does nothing");
//        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
//
//        this.setChoices();
//
//        setVisible(true);
//    }
//
//    protected JComponent showTasksInDay(Day day) {
//        JPanel panel = new JPanel(false);
//        JLabel filler = new JLabel(text);
//        filler.setHorizontalAlignment(JLabel.CENTER);
//        panel.setLayout(new GridLayout(1, 1));
//        panel.add(filler);
//        return panel;
//    }
//
//
//    public void printTable() {
//        for (int i = 0; i < week.size(); i++) {
//            for (int n = 0; n < day.checkNumber(); n++) {
//                Task task = day.getTask(n);
//                Object[] tableCol = new Object[] {
//                        task.getWork(),
//                };
//
//            }
//            tableModel.addColumn("");
//
//        }
//
//    }
//
//    public void setChoices() {}
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}
