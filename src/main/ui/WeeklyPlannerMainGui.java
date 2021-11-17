package ui;

import model.*;
import persistence.JsonReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

//design a home page for the weekly planner application;

public class WeeklyPlannerMainGui extends JFrame implements ActionListener {
    private static final int BUTTON_POSITION = 40;
    private static final int BUTTON_WIDTH = 70;
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
   // private static final String START_NEW_WEEK = "EMPTY_LIST_ACTION";
    private static final String QUIT_APP_ACTION = "QUIT";
    //private final Week week = new Week();
    private Week week;
    private Day day;
    private Task task;
    private WeekGui weekGui;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/week.json";

    public WeeklyPlannerMainGui() {
        super("Weekly planner");
        week = new Week();
        this.setWindow();
        jsonReader = new JsonReader(JSON_STORE);
     //   this.setBackgroundImage();
        try {
            week = jsonReader.read();
        } catch (IOException e) {
            System.out.println("unable to read from file");
        }
        this.showButtons(week);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //EFFECTS: Set the dimension of the window
    private void setWindow() {
        setPreferredSize(new Dimension(550, 550));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);
    }

//    private void setBackgroundImage() {
//        try {
//            BufferedImage backgroundImage = ImageIO.read(new File("src/main/ui/images/background.jpg"));
//            setContentPane(new Background(backgroundImage));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    private void showButtons(Week week) {
        // JLabel chooseOption = new JLabel("choose an option: ", JLabel.);
//        chooseOption.setBounds(26, 10, 300, 20);
//        add(chooseOption);
//        chooseOption.setForeground(Color.black);
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

        JButton quitAppButton = new JButton("Quit TodoList Application");
        quitAppButton.setBounds(300, 240, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(quitAppButton);
        quitAppButton.setActionCommand(QUIT_APP_ACTION);
        quitAppButton.addActionListener(this);
        quitAppButton.setForeground(Color.black);
    }

    //EFFECTS: initiate checkCompletion bottom
    public void initiateCompletion() {
        JButton uncompletion = new JButton("SHOW_UNCOMPLETED_TASKS");
        uncompletion.setBounds(190, 240, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(uncompletion);
        uncompletion.setActionCommand(VISIT_TUESDAY);
        uncompletion.addActionListener(this);
        uncompletion.setForeground(Color.black);
    }

    //EFFECTS: initiate Monday bottom
    public void initiateMonday(Week week) {
        JButton day1 = new JButton("Monday");
        day1.setBounds(20, BUTTON_POSITION, 70, BUTTON_HEIGHT);
        add(day1);
        day1.setActionCommand(VISIT_MONDAY);
        day1.addActionListener(this);
        day1.setForeground(Color.black);
        JLabel day1Task = new JLabel(printFewEvents(week.getDays()[0]));
        day1Task.setBounds(20, 80, 70,300);
        add(day1Task);
        day1Task.setForeground(Color.black);
    }

    //EFFECTS: print few events for the day

    public String printFewEvents(Day n) {
        ArrayList<String> works = new ArrayList<>();
        if (n.checkNumber() < 5 && n.checkNumber() > 0) {
            for (int i = 0; i < n.checkNumber(); i++) {
                works.add(n.getTask(i).getWork());
            }
        } else if (n.checkNumber() >= 5) {
            for (int k = 0; k <= 4; k++) {
                works.add(n.getTask(k).getWork());
            }
        }
        return works.toString();
    }

    //EFFECTS: initiate Tuesday bottom
    public void initiateTuesday(Week week) {
        JButton day2 = new JButton("Tuesday");
        day2.setBounds(100, BUTTON_POSITION, 70, BUTTON_HEIGHT);
        add(day2);
        day2.setActionCommand(VISIT_TUESDAY);
        day2.addActionListener(this);
        day2.setForeground(Color.black);
        JLabel day2Task = new JLabel(printFewEvents(week.getDays()[1]));
        day2Task.setBounds(100, 80, 70,300);
        add(day2Task);
        day2Task.setForeground(Color.black);
    }

    //EFFECTS: initiate Wednesday bottom
    public void initiateWednesday(Week week) {
        JButton day3 = new JButton("Wednesday");
        day3.setBounds(180, BUTTON_POSITION, 70, BUTTON_HEIGHT);
        add(day3);
        day3.setActionCommand(VISIT_WEDNESDAY);
        day3.addActionListener(this);
        day3.setForeground(Color.black);
        JLabel day3Task = new JLabel(printFewEvents(week.getDays()[2]));
        day3Task.setBounds(180, 80, 70,300);
        add(day3Task);
        day3Task.setForeground(Color.black);
    }

    //EFFECTS: initiate Thursday bottom
    public void initiateThursday(Week week) {
        JButton day4 = new JButton("Thursday");
        day4.setBounds(260, BUTTON_POSITION, 70, BUTTON_HEIGHT);
        add(day4);
        day4.setActionCommand(VISIT_THURSDAY);
        day4.addActionListener(this);
        day4.setForeground(Color.black);
        JLabel day4Task = new JLabel(printFewEvents(week.getDays()[3]));
        day4Task.setBounds(260, 80, 70,300);
        add(day4Task);
        day4Task.setForeground(Color.black);
    }

    //EFFECTS: initiate Friday bottom
    public void initiateFriday(Week week) {
        JButton day5 = new JButton("Friday");
        day5.setBounds(340, BUTTON_POSITION, 70, BUTTON_HEIGHT);
        add(day5);
        day5.setActionCommand(VISIT_FRIDAY);
        day5.addActionListener(this);
        day5.setForeground(Color.black);
        JLabel day5Task = new JLabel(printFewEvents(week.getDays()[4]));
        day5Task.setBounds(340, 80, 70,300);
        add(day5Task);
        day5Task.setForeground(Color.black);
    }

    //EFFECTS: initiate Saturday bottom
    public void initiateSaturday(Week week) {
        JButton day6 = new JButton("Saturday");
        day6.setBounds(420, BUTTON_POSITION, 70, BUTTON_HEIGHT);
        add(day6);
        day6.setActionCommand(VISIT_SATURDAY);
        day6.addActionListener(this);
        day6.setForeground(Color.black);
        JLabel day6Task = new JLabel(printFewEvents(week.getDays()[5]));
        day6Task.setBounds(420, 80, 70,300);
        add(day6Task);
        day6Task.setForeground(Color.black);
    }

    //EFFECTS: initiate Sunday bottom
    public void initiateSunday(Week week) {
        JButton day7 = new JButton("Sunday");
        day7.setBounds(500, BUTTON_POSITION, 70, BUTTON_HEIGHT);
        add(day7);
        day7.setActionCommand(VISIT_SUNDAY);
        day7.addActionListener(this);
        day7.setForeground(Color.black);
        JLabel day7Task = new JLabel(printFewEvents(week.getDays()[6]));
        day7Task.setBounds(500, 80, 70,300);
        add(day7Task);
        day7Task.setForeground(Color.black);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        String myChoice = e.getActionCommand();
        if (myChoice.equals(VISIT_MONDAY)) {
            weekGui = new WeekGui(week, week.getDays()[0]);
        } else if (myChoice.equals(VISIT_TUESDAY)) {
            weekGui = new WeekGui(week, week.getDays()[1]);
        } else if (myChoice.equals(VISIT_WEDNESDAY)) {
            weekGui = new WeekGui(week, week.getDays()[2]);
        } else if (myChoice.equals(VISIT_THURSDAY)) {
            weekGui = new WeekGui(week, week.getDays()[3]);
        } else if (myChoice.equals(VISIT_FRIDAY)) {
            weekGui = new WeekGui(week, week.getDays()[4]);
        } else if (myChoice.equals(VISIT_SATURDAY)) {
            weekGui = new WeekGui(week, week.getDays()[5]);
        } else if (myChoice.equals(VISIT_SUNDAY)) {
            weekGui = new WeekGui(week, week.getDays()[6]);
        } else if (myChoice.equals(SHOW_UNCOMPLETED_TASKS)) {
            JOptionPane.showMessageDialog(null, "1");

        } else if (myChoice.equals(QUIT_APP_ACTION)) {
            weekGui.dispose();
            dispose();
        }
    }
}
