package controller;

import model.Clock;
import view.ClockView;
import enums.Event;
import enums.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClockController {
    private Clock model;
    private ClockView view;

    public ClockController(Clock model, ClockView view) {
        this.model = model;
        this.view = view;

        // Initialize the view with the current time and date
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
                    // Extract hour, minute, second from the time input
                    String[] timeParts = view.getTimeInput().split(":");
                    int hour = Integer.parseInt(timeParts[0]);
                    int minute = Integer.parseInt(timeParts[1]);
                    int second = Integer.parseInt(timeParts[2]);
                    model.setLocalTime(hour, minute, second);
                    view.setCurrentTimeDisplay(model.getCurrentTime());
                } else if(model.getCurrentState() == State.SET_DATE) {
                    // Extract year, month, day from the date input
                    String[] dateParts = view.getDateInput().split("-");
                    int year = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]);
                    int day = Integer.parseInt(dateParts[2]);
                    model.setLocalDate(year, month, day);
                    view.setCurrentDateDisplay(model.getCurrentDate());
                }
            }
        });
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
                System.out.println("Highlighting Time"); // Debug print statement
                view.highlightTime();
                break;
            case SET_DATE:
                System.out.println("Highlighting Date"); // Debug print statement
                view.highlightDate();
                break;
        }
    }

    public void displayView() {
        view.display();
    }
}
