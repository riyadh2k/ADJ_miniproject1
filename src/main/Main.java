package main;

import model.Clock;
import view.ClockView;
import controller.ClockController;

public class Main {
    public static void main(String[] args) {
        Clock model = new Clock();
        ClockView view = new ClockView();
        ClockController controller = new ClockController(model, view);
        controller.displayView();
    }
}
