package view;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.*;
import java.awt.*;

public class ClockView {
    private JFrame frame;
    private JLabel currentTimeLabel, currentDateLabel;
    private JPanel buttonPanel;
    private JButton changeModeButton, readyToSetButton, setButton;
    private JTextField timeInput, dateInput;
    private Border defaultBorder = BorderFactory.createEmptyBorder();
    private Border highlightBorder = BorderFactory.createLineBorder(Color.GREEN, 2);


    public ClockView() {
        frame = new JFrame("Clock State Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2));

        currentTimeLabel = new JLabel("Current Time: ");
        currentDateLabel = new JLabel("Current Date: ");

        timeInput = new JTextField(10);
        dateInput = new JTextField(10);

        mainPanel.add(currentTimeLabel);
        mainPanel.add(currentDateLabel);
        mainPanel.add(timeInput);
        mainPanel.add(dateInput);
        
        frame.add(mainPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel();

        changeModeButton = new JButton("ChangeMode");
        readyToSetButton = new JButton("ReadyToSet");
        setButton = new JButton("Set");

        buttonPanel.add(changeModeButton);
        buttonPanel.add(readyToSetButton);
        buttonPanel.add(setButton);
        
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }
    public void highlightTime() {
        timeInput.setBorder(highlightBorder);
        dateInput.setBorder(defaultBorder);
        timeInput.repaint();  // Repaint the JTextField
    }

    public void highlightDate() {
        dateInput.setBorder(highlightBorder);
        timeInput.setBorder(defaultBorder);
        dateInput.repaint();  // Repaint the JTextField
    }

    public void resetHighlights() {
        timeInput.setBorder(defaultBorder);
        dateInput.setBorder(defaultBorder);
        timeInput.repaint();  // Repaint the JTextField
        dateInput.repaint();  // Repaint the JTextField
    }

    public void display() {
        frame.setVisible(true);
    }

    public void setCurrentTimeDisplay(String time) {
        currentTimeLabel.setText("Current Time: " + time);
    }

    public void setCurrentDateDisplay(String date) {
        currentDateLabel.setText("Current Date: " + date);
    }

    public JButton getChangeModeButton() {
        return changeModeButton;
    }

    public JButton getReadyToSetButton() {
        return readyToSetButton;
    }
    public JFrame getFrame() {
        return frame;
    }

    public JButton getSetButton() {
        return setButton;
    }

    public String getTimeInput() {
        return timeInput.getText();
    }

    public String getDateInput() {
        return dateInput.getText();
    }
}
