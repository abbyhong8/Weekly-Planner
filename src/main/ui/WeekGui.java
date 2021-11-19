package ui;

import model.*;
import persistence.*;
import sun.nio.cs.ext.MacRoman;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import static java.awt.Color.white;

//a window for a specific day in a week
public class WeekGui extends JFrame implements ActionListener {

    //private JsonWriter jsonWriter;
    private Day day;
    private Week week;
    private DefaultTableModel taskList;
    private JTable taskTable;
    private WeeklyPlannerMainGui mainGui;
    private BufferedImage image;
    private static final String JSON_STORE = "./data/week.json";
    private static final String MARK_COMPLETED = "MARK_COMPLETED";
    private static final String ADD_TASK = "ADD_TASK";

    public WeekGui(WeeklyPlannerMainGui mainGui, Week week, Day day) {
        this.day = day;
        this.week = week;
        this.mainGui = mainGui;
        final String[] taskLabels = new String[] {
                "Number",
                "Name",
                "Completion"
        };
        taskList = new DefaultTableModel(null, taskLabels) {};
        taskTable = new JTable(taskList);
       // jsonWriter = new JsonWriter(JSON_STORE);
        setSize(900,550);
       // setLayout(new FlowLayout());
        this.showTasks(day);
       // JScrollPane panel = new JScrollPane(taskTable);
        JPanel panelForTable = new JPanel();
        this.showButtons();

       // panelForTable.setBounds(1,1,600,700);
        add(panelForTable);
        panelForTable.add(new JScrollPane(taskTable));
        setTitle("Tasks for this day");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

       // pack();
        setVisible(true);
        JLabel image = new JLabel(new ImageIcon(this.addImage(day)));
        image.setBounds(400,600,100,50);
        panelForTable.add(image);
        panelForTable.setBackground(Color.white);


    }

    //EFFECTS: add image for the corresponding day
    private BufferedImage addImage(Day day) {
        if (day.getDayNumInWeek() == 0) {
            try {
                image = ImageIO.read(new File("./data/monday.png"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (day.getDayNumInWeek() == 1) {
            try {
                image = ImageIO.read(new File("./data/images.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (day.getDayNumInWeek() == 2) {
            try {
                image = ImageIO.read(new File("./data/wed.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            testRestDay(day);
        }
        return image;


    }

    //EFFECTS: find correspond day for Thursday to Sunday
    private BufferedImage testRestDay(Day day) {
        if (day.getDayNumInWeek() == 3) {
            try {
                image = ImageIO.read(new File("./data/thu.jpeg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (day.getDayNumInWeek() == 4) {
            try {
                image = ImageIO.read(new File("./data/fri.jpeg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (day.getDayNumInWeek() == 5) {
            try {
                image = ImageIO.read(new File("./data/SAT.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            sunday(day);
        }
        return image;

    }

    //EFFECTS:find the image for sunday
    public BufferedImage sunday(Day day) {
        if (day.getDayNumInWeek() == 6) {
            try {
                image = ImageIO.read(new File("./data/sunday.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    //EFFECTS:illustrate the tasks in the table
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
    public void showButtons() {

        JButton addTaskButton = new JButton("+ Add a task");
        addTaskButton.setBounds(100,440,100,40);
        add(addTaskButton);
        addTaskButton.setActionCommand(ADD_TASK);
        //ImageIcon addBottom =
        //addTaskButton.setIcon(new ImageIcon("/data/bottom.png"));
        Color bottom1 = new Color(219,248,209);
        addTaskButton.addActionListener(this);
        //addTaskButton.setLocation(200,200);
        addTaskButton.setForeground(Color.ORANGE);
        addTaskButton.setBackground(bottom1);
        addTaskButton.setVisible(true);



        JButton markCompletedButton = new JButton("Mark this task completed");
        markCompletedButton.setBounds(240,440,200,40);
        add(markCompletedButton);
        markCompletedButton.setActionCommand(MARK_COMPLETED);
        //markCompletedButton.setLocation(300,200);
        markCompletedButton.addActionListener(this);
        markCompletedButton.setForeground(Color.PINK);
        markCompletedButton.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals(ADD_TASK)) {
            new AddTaskWindow(this.mainGui, this, this.week, this.day);

        } else if (action.equals(MARK_COMPLETED)) {
            int i = taskTable.getSelectedRow();
            Task task = day.getTask(i);
            task.markComplete();
            taskTable.setValueAt((Object) day.getTask(i).isComplete(), i, 2);

        }
        mainGui.repaint();

    }
}
