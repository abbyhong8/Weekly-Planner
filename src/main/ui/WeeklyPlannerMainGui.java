package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;

//design a home page for the weekly planner application;

public class WeeklyPlannerMainGui extends JFrame implements ActionListener {
    private static final int BUTTON_POSITION = 40;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 30;
    private static final String VISIT_MONDAY = "VISIT_MONDAY";
    private static final String VISIT_TUESDAY = "VISIT_TUESDAY";
    private static final String VISIT_WEDNESDAY = "VISIT_WEDNESDAY";
    private static final String VISIT_THURSDAY = "VISIT_THURSDAY";
    private static final String VISIT_FRIDAY = "VISIT_FRIDAY";
    private static final String VISIT_SATURDAY = "VISIT_SATURDAY";
    private static final String VISIT_SUNDAY = "VISIT_SUNDAY";
    private static final String SHOW_UNCOMPLETED_TASKS = "SHOW_UNCOMPLETED_TASKS";
    private static final String EMPTY_WEEK_ACTION = "EMPTY_WEEK_ACTION";
    private static final String LOAD_WEEK = "LOAD_WEEK";
    private static final String SAVE_WEEK = "SAVE_WEEK";
   // private static final String START_NEW_WEEK = "EMPTY_LIST_ACTION";
    private static final String QUIT_APP_ACTION = "QUIT";
    //private final Week week = new Week();
    private Week week;
    private Day day;
    private Task task;
    private WeekGui weekGui;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private DefaultTableModel table;
    private JLabel background;
    private Week ww;
    private static final String JSON_STORE = "./data/week.json";


    public WeeklyPlannerMainGui(Week week) {
        super("Weekly planner");

        this.setWindow();
      //  initiateWeek(week);
        this.week = week;
        this.showButtons(week);
        setBackgroundImage();
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    //EFFECTS: set a background image for this frame
    private void setBackgroundImage() {
        //JPanel background = new JPanel();
        ImageIcon image = new ImageIcon(String.valueOf(new File("./data/back.jpeg")));
        background = new JLabel("", image, JLabel.CENTER);
        background.setBounds(0,0,770,600);
        add(background);

    }

    //EFFECTS: initiate a new week
    private void initiateWeek(Week week) {
       // week = new Week();
        Day day1 = new Day(0);
        Day day2 = new Day(1);
        Day day3 = new Day(2);
        Day day4 = new Day(3);
        Day day5 = new Day(4);
        Day day6 = new Day(5);
        Day day7 = new Day(6);
        week.addDay(day1);
        week.addDay(day2);
        week.addDay(day3);
        week.addDay(day4);
        week.addDay(day5);
        week.addDay(day6);
        week.addDay(day7);
    }

    //EFFECTS: Set the dimension of the window
    private void setWindow() {
        setPreferredSize(new Dimension(770, 600));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }

//    //return the week in the gui
//    public Week getWeek() {
//        return week;
//    }



    // set the bottoms in the main window
    private void showButtons(Week week) {
;
        initiateMonday(week);
        initiateTuesday(week);
        initiateWednesday(week);
        initiateThursday(week);
        initiateFriday(week);
        initiateSaturday(week);
        initiateSunday(week);

        JButton emptyListButton = new JButton("Start a New Week");
        emptyListButton.setBounds(250, 400, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(emptyListButton);
        emptyListButton.setActionCommand(EMPTY_WEEK_ACTION);
        emptyListButton.addActionListener(this);
        emptyListButton.setForeground(Color.black);

        initiateCompletion();

        JButton quitAppButton = new JButton("Quit");
        quitAppButton.setBounds(50, 400, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(quitAppButton);
        quitAppButton.setActionCommand(QUIT_APP_ACTION);
        quitAppButton.addActionListener(this);
        quitAppButton.setForeground(Color.black);

        setSave();
        setLoad();
    }

    //EFFECTS: initiate save bottom
    public void setSave() {
        JButton saveBottom = new JButton("Save the week");
        saveBottom.setBounds(350, 450, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(saveBottom);
        saveBottom.setActionCommand(SAVE_WEEK);
        saveBottom.addActionListener(this);
        saveBottom.setForeground(Color.black);
    }

    //EFFECTS: initiate load bottom
    public void setLoad() {
        JButton loadBottom = new JButton("Load the week");
        loadBottom.setBounds(150, 450, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(loadBottom);
        loadBottom.setActionCommand(LOAD_WEEK);
        loadBottom.addActionListener(this);
        loadBottom.setForeground(Color.black);
    }

    //EFFECTS: initiate checkCompletion bottom
    public void initiateCompletion() {
        JButton uncompletedTasks = new JButton("Show uncompleted tasks");
        uncompletedTasks.setBounds(430, 400, BUTTON_WIDTH + 50, BUTTON_HEIGHT);
        add(uncompletedTasks);
        uncompletedTasks.setActionCommand(SHOW_UNCOMPLETED_TASKS);
        uncompletedTasks.addActionListener(this);
        uncompletedTasks.setForeground(Color.black);
    }

    //EFFECTS: initiate Monday bottom
    public void initiateMonday(Week week) {
        JButton day1 = new JButton("Monday");
        day1.setBounds(20, BUTTON_POSITION, 100, BUTTON_HEIGHT);
        add(day1);
        day1.setActionCommand(VISIT_MONDAY);
        day1.addActionListener(this);
        day1.setForeground(Color.black);
        JTable day1Task = new JTable(printFewEvents(week.getDays()[0]), null);
       //add(day1Task);
        day1Task.setVisible(true);
        //day1Task.setBackground();
        day1Task.setBounds(20, 80, 100,300);
        add(day1Task);
        day1Task.setForeground(Color.black);

    }

    //EFFECTS: print few events for the day

    public DefaultTableModel printFewEvents(Day n) {
       // DefaultTableModel table = new DefaultTableModel();
        final String[] column = new String[] {"Task"};
        table = new DefaultTableModel(null, column);
        //ArrayList<String> works = new ArrayList<>();
        if (n.checkNumber() < 5 && n.checkNumber() > 0) {
            for (int i = 0; i < n.checkNumber(); i++) {
                Task task = n.getTask(i);
                Object[] tableRow = new Object[] {
                       task.getWork()
                };
                table.addRow(tableRow);
            }
        } else if (n.checkNumber() >= 5) {
            for (int k = 0; k < 5; k++) {
                Task task = n.getTask(k);
                Object[] tableRow = new Object[] {
                        task.getWork()
                };
                table.addRow(tableRow);
            }
        }
        return table;
    }

    //EFFECTS: initiate Tuesday bottom
    public void initiateTuesday(Week week) {
        JButton day2 = new JButton("Tuesday");
        day2.setBounds(140, BUTTON_POSITION, 100, BUTTON_HEIGHT);
        add(day2);
        day2.setActionCommand(VISIT_TUESDAY);
        day2.addActionListener(this);
        day2.setForeground(Color.black);
        JTable day2Task = new JTable(printFewEvents(week.getDays()[1]));
        day2Task.setBounds(140, 80, 80,300);
        day2Task.setVisible(true);
        add(day2Task);
        day2Task.setForeground(Color.black);
    }

    //EFFECTS: initiate Wednesday bottom
    public void initiateWednesday(Week week) {
        JButton day3 = new JButton("Wednesday");
        day3.setBounds(240, BUTTON_POSITION, 100, BUTTON_HEIGHT);
        add(day3);
        day3.setActionCommand(VISIT_WEDNESDAY);
        day3.addActionListener(this);
        day3.setForeground(Color.black);
        JTable day3Task = new JTable(printFewEvents(week.getDays()[2]));
        day3Task.setBounds(240, 80, 80,300);
        day3Task.setVisible(true);
        add(day3Task);
        day3Task.setForeground(Color.black);
    }

    //EFFECTS: initiate Thursday bottom
    public void initiateThursday(Week week) {
        JButton day4 = new JButton("Thursday");
        day4.setBounds(340, BUTTON_POSITION, 100, BUTTON_HEIGHT);
        add(day4);
        day4.setActionCommand(VISIT_THURSDAY);
        day4.addActionListener(this);
        day4.setForeground(Color.black);
        JTable day4Task = new JTable(printFewEvents(week.getDays()[3]));
        day4Task.setBounds(340, 80, 80,300);
        day4Task.setVisible(true);
        add(day4Task);
        day4Task.setForeground(Color.black);
    }

    //EFFECTS: initiate Friday bottom
    public void initiateFriday(Week week) {
        JButton day5 = new JButton("Friday");
        day5.setBounds(440, BUTTON_POSITION, 100, BUTTON_HEIGHT);
        add(day5);
        day5.setActionCommand(VISIT_FRIDAY);
        day5.addActionListener(this);
        day5.setForeground(Color.black);
        JTable day5Task = new JTable(printFewEvents(week.getDays()[4]));
        day5Task.setBounds(440, 80, 80,300);
        add(day5Task);
        day5Task.setVisible(true);
        day5Task.setForeground(Color.black);
    }

    //EFFECTS: initiate Saturday bottom
    public void initiateSaturday(Week week) {
        JButton day6 = new JButton("Saturday");
        day6.setBounds(540, BUTTON_POSITION, 100, BUTTON_HEIGHT);
        add(day6);
        day6.setActionCommand(VISIT_SATURDAY);
        day6.addActionListener(this);
        day6.setForeground(Color.black);
        JTable day6Task = new JTable(printFewEvents(week.getDays()[5]));
        day6Task.setBounds(540, 80, 80,300);
        add(day6Task);
        day6Task.setVisible(true);
        day6Task.setForeground(Color.black);
    }

    //EFFECTS: initiate Sunday bottom
    public void initiateSunday(Week week) {
        JButton day7 = new JButton("Sunday");
        day7.setBounds(640, BUTTON_POSITION, 100, BUTTON_HEIGHT);
        add(day7);
        day7.setActionCommand(VISIT_SUNDAY);
        day7.addActionListener(this);
        day7.setForeground(Color.black);
        JTable day7Task = new JTable(printFewEvents(week.getDays()[6]));
        day7Task.setBounds(640, 80, 80,300);
        add(day7Task);
        day7Task.setVisible(true);
        day7Task.setForeground(Color.black);
    }


    //EFFECTS: create an empty week
//    public void initiateNewWeek(Week week) {
//        initiateMonday(week);
//        initiateTuesday(week);
//        initiateWednesday(week);
//        initiateThursday(week);
//        initiateFriday(week);
//        initiateSaturday(week);
//        initiateSunday(week);
//    }




    //EFFECTS: save the week
    public void saveWeek() {
        jsonWriter = new JsonWriter(JSON_STORE);
        try {
            jsonWriter.open();
            jsonWriter.write(week);
            jsonWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println("unable to write document");;
        }
    }

    //EFFECTS: load the week
    public void loadWeek() {
        jsonReader = new JsonReader(JSON_STORE);
        try {
            ww = jsonReader.read();
            //showButtons(week);
            this.dispose();
            new WeeklyPlannerMainGui(ww);
//            repaint();
        } catch (IOException ex) {
            System.out.println("unable to read document");
        }
    }

    //EFFECTS: return the number of uncompleted tasks
    public String checkCompletion(Week week) {
        Integer i = week.returnAllIncomplete();
        return i.toString();
    }

    //EFFECTS: helper function to choose a day in week
    public void chooseDay(ActionEvent e) {
        String myChoice = e.getActionCommand();
        if (myChoice.equals(VISIT_MONDAY)) {
            weekGui = new WeekGui(this, week, week.getDays()[0]);
        } else if (myChoice.equals(VISIT_TUESDAY)) {
            weekGui = new WeekGui(this, week, week.getDays()[1]);
        } else if (myChoice.equals(VISIT_WEDNESDAY)) {
            weekGui = new WeekGui(this, week, week.getDays()[2]);
        } else if (myChoice.equals(VISIT_THURSDAY)) {
            weekGui = new WeekGui(this, week, week.getDays()[3]);
        } else if (myChoice.equals(VISIT_FRIDAY)) {
            weekGui = new WeekGui(this, week, week.getDays()[4]);
        } else if (myChoice.equals(VISIT_SATURDAY)) {
            weekGui = new WeekGui(this, week, week.getDays()[5]);
        } else if (myChoice.equals(VISIT_SUNDAY)) {
            weekGui = new WeekGui(this, week, week.getDays()[6]);
        }


    }

    //print all the event log
    public void printEventLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String myChoice = e.getActionCommand();
        chooseDay(e);
        if (myChoice.equals(SHOW_UNCOMPLETED_TASKS)) {

            ImageIcon image = new ImageIcon("./data/icon.png");
            JOptionPane.showMessageDialog(null,
                            "You have " + checkCompletion(week) + " tasks to finish!", null,
                    0, image);

        } else if (myChoice.equals(EMPTY_WEEK_ACTION)) {
            ww = new Week();
            //initiateWeek(ww);
            initiateWeek(ww);
            new WeeklyPlannerMainGui(ww);
        } else if (myChoice.equals(SAVE_WEEK)) {
            saveWeek();
        } else if (myChoice.equals(LOAD_WEEK)) {
            loadWeek();

        } else if (myChoice.equals(QUIT_APP_ACTION)) {
            printEventLog();


//            week.quit();
//            for (int i = 0; i <= 6; i++) {
//                week.getDays()[i].quitDay();
//            }
            System.exit(0);
        }
    }


}
