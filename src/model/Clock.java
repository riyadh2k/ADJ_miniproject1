package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import enums.State;
import enums.Event;

public class Clock {
    private State currentState;
    private LocalTime localTime;
    private LocalDate localDate;

    public Clock() {
        this.currentState = State.DISPLAY_TIME;
        this.localTime = LocalTime.now();
        this.localDate = LocalDate.now();
    }

    public String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return localTime.format(formatter);
    }


    public String getCurrentDate() {
        return localDate.toString();
    }

    public void setLocalTime(int hour, int minute, int second) {
        this.localTime = LocalTime.of(hour, minute, second);
    }

    public void setLocalDate(int year, int month, int day) {
        this.localDate = LocalDate.of(year, month, day);
    }

    public State getCurrentState() {
        return currentState;
    }

public String handleEvent(Event event) {
    switch (currentState) {
        case DISPLAY_TIME:
            if (event == Event.ChangeMode) {
                currentState = State.DISPLAY_DATE;
                return getCurrentDate();
            } else if (event == Event.ReadyToSet) {
                currentState = State.SET_TIME;
                return "Enter new time";
            }
            break;
        case DISPLAY_DATE:
            if (event == Event.ChangeMode) {
                currentState = State.DISPLAY_TIME;
                return getCurrentTime();
            } else if (event == Event.ReadyToSet) {
                currentState = State.SET_DATE;
                return "Enter new date";
            }
            break;
        case SET_TIME:
            if (event == Event.ReadyToSet) {
                currentState = State.DISPLAY_TIME;
                return getCurrentTime();
            }
            break;
        case SET_DATE:
            if (event == Event.ReadyToSet) {
                currentState = State.DISPLAY_DATE;
                return getCurrentDate();
            }
            break;
    }
    return "Action not allowed in the current state";
}
}
