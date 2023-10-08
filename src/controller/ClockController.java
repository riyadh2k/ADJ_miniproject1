package controller;

import model.Clock;
import view.ClockView;
import enums.Event;
import enums.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ClockController {
    private Clock model;
    private ClockView view;

    public ClockController(Clock model, ClockView view) {
        this.model = model;
        this.view = view;

        this.view.setCurrentTimeDisplay(model.getCurrentTime());
        this.view.setCurrentDateDisplay(model.getCurrentDate());

        this.view.getChangeModeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = model.handleEvent(Event.ChangeMode);
                updateViewBasedOnState(result);
            }
        });

        this.view.getReadyToSetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = model.handleEvent(Event.ReadyToSet);
                updateViewBasedOnState(result);
            }
        });

        this.view.getSetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.getCurrentState() == State.SET_TIME) {
                    if (!view.getTimeInput().matches("^\\d{2}:\\d{2}:\\d{2}$")) {
                        JOptionPane.showMessageDialog(view.getFrame(), "Time should be in HH:mm:ss format!", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        String[] timeParts = view.getTimeInput().split(":");
                        int hour = Integer.parseInt(timeParts[0]);
                        int minute = Integer.parseInt(timeParts[1]);
                        int second = Integer.parseInt(timeParts[2]);
                        model.setLocalTime(hour, minute, second);
                        view.setCurrentTimeDisplay(model.getCurrentTime());
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(view.getFrame(), "Invalid time values. Ensure hour is between 0-23, and minute & second are between 0-59.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if(model.getCurrentState() == State.SET_DATE) {
                    if (!view.getDateInput().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                        JOptionPane.showMessageDialog(view.getFrame(), "Date should be in YYYY-MM-DD format!", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        String[] dateParts = view.getDateInput().split("-");
                        int year = Integer.parseInt(dateParts[0]);
                        int month = Integer.parseInt(dateParts[1]);
                        int day = Integer.parseInt(dateParts[2]);
                        model.setLocalDate(year, month, day);
                        view.setCurrentDateDisplay(model.getCurrentDate());
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(view.getFrame(), "Invalid date values.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        
        view.display();
    }

    private void updateViewBasedOnState(String result) {
        switch (model.getCurrentState()) {
            case DISPLAY_TIME:
                view.setCurrentTimeDisplay(result);
                view.resetHighlights();
                break;
            case DISPLAY_DATE:
                view.setCurrentDateDisplay(result);
                view.resetHighlights();
                break;
            case SET_TIME:
                view.highlightTime();
                break;
            case SET_DATE:
                view.highlightDate();
                break;
        }
    }

    public void displayView() {
        view.display();
    }
}
